package com.cyberaware.backend;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class PlayerDatabase {
    private static final Logger LOGGER = Logger.getLogger(PlayerDatabase.class.getName());
    private final String jdbcUrl;
    private final boolean inMemory;
    private PlayerProfile fallbackProfile;
    private long nextFallbackId = 1L;

    public PlayerDatabase() throws SQLException {
        this(Path.of("data", "cyberaware.db"));
    }

    public PlayerDatabase(Path databaseFile) throws SQLException {
        boolean sqliteReady = false;
        String url = null;
        try {
            Class.forName("org.sqlite.JDBC");
            Path absoluteFile = databaseFile.toAbsolutePath().normalize();
            Path parent = absoluteFile.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            url = "jdbc:sqlite:" + absoluteFile;
            try (Connection conn = DriverManager.getConnection(url);
                 Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("""
                        CREATE TABLE IF NOT EXISTS players (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            display_name TEXT NOT NULL,
                            name_key TEXT NOT NULL UNIQUE,
                            total_points INTEGER NOT NULL DEFAULT 0 CHECK(total_points >= 0),
                            language TEXT NOT NULL,
                            last_played TEXT NOT NULL
                        )
                        """);
            }
            sqliteReady = true;
        } catch (Throwable t) {
            LOGGER.log(Level.WARNING, "SQLite JDBC driver not available or failed to initialize: " + t.getMessage()
                    + ". Running with in-memory session.", t);
            sqliteReady = false;
        }
        this.inMemory = !sqliteReady;
        this.jdbcUrl = url;
    }

    public synchronized Optional<PlayerProfile> mostRecentPlayer() throws SQLException {
        if (inMemory) {
            return Optional.ofNullable(fallbackProfile);
        }
        String sql = """
                SELECT id, display_name, total_points, language
                FROM players
                ORDER BY last_played DESC, id DESC
                LIMIT 1
                """;
        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {
            return result.next() ? Optional.of(readProfile(result)) : Optional.empty();
        }
    }

    public synchronized PlayerProfile openPlayer(String name, StudyLanguage language) throws SQLException {
        String normalizedName = name.trim();
        if (inMemory) {
            if (fallbackProfile != null && fallbackProfile.name().equalsIgnoreCase(normalizedName)) {
                fallbackProfile = new PlayerProfile(fallbackProfile.id(), normalizedName, fallbackProfile.totalPoints(), language);
            } else {
                fallbackProfile = new PlayerProfile(nextFallbackId++, normalizedName, 0, language);
            }
            return fallbackProfile;
        }
        String nameKey = normalizedName.toLowerCase(Locale.ROOT);
        try (Connection connection = connect()) {
            connection.setAutoCommit(false);
            try {
                long playerId = findPlayerId(connection, nameKey);
                if (playerId == 0) {
                    try (PreparedStatement insert = connection.prepareStatement(
                            "INSERT INTO players(display_name, name_key, language, last_played) "
                                    + "VALUES(?, ?, ?, CURRENT_TIMESTAMP)")) {
                        insert.setString(1, normalizedName);
                        insert.setString(2, nameKey);
                        insert.setString(3, language.name());
                        insert.executeUpdate();
                    }
                    playerId = findPlayerId(connection, nameKey);
                } else {
                    try (PreparedStatement update = connection.prepareStatement(
                            "UPDATE players SET display_name = ?, language = ?, "
                                    + "last_played = CURRENT_TIMESTAMP WHERE id = ?")) {
                        update.setString(1, normalizedName);
                        update.setString(2, language.name());
                        update.setLong(3, playerId);
                        update.executeUpdate();
                    }
                }
                PlayerProfile profile = findPlayer(connection, playerId)
                        .orElseThrow(() -> new SQLException("Saved player could not be reloaded."));
                connection.commit();
                return profile;
            } catch (SQLException | RuntimeException exception) {
                connection.rollback();
                throw exception;
            }
        }
    }

    public synchronized void updateLanguage(long playerId, StudyLanguage language) throws SQLException {
        if (inMemory) {
            if (fallbackProfile != null && fallbackProfile.id() == playerId) {
                fallbackProfile = new PlayerProfile(playerId, fallbackProfile.name(), fallbackProfile.totalPoints(), language);
            }
            return;
        }
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE players SET language = ? WHERE id = ?")) {
            statement.setString(1, language.name());
            statement.setLong(2, playerId);
            if (statement.executeUpdate() != 1) {
                throw new SQLException("The active player record could not be updated.");
            }
        }
    }

    public synchronized int addPoints(long playerId, int points) throws SQLException {
        if (inMemory) {
            if (fallbackProfile != null && fallbackProfile.id() == playerId) {
                int updatedPoints = Math.addExact(fallbackProfile.totalPoints(), points);
                fallbackProfile = new PlayerProfile(playerId, fallbackProfile.name(), updatedPoints, fallbackProfile.language());
                return updatedPoints;
            }
            return points;
        }
        try (Connection connection = connect()) {
            connection.setAutoCommit(false);
            try {
                int currentPoints;
                try (PreparedStatement select = connection.prepareStatement(
                        "SELECT total_points FROM players WHERE id = ?")) {
                    select.setLong(1, playerId);
                    try (ResultSet result = select.executeQuery()) {
                        if (!result.next()) throw new SQLException("The active player record was not found.");
                        currentPoints = result.getInt(1);
                    }
                }
                int updatedPoints;
                try {
                    updatedPoints = Math.addExact(currentPoints, points);
                } catch (ArithmeticException exception) {
                    throw new SQLException("The player's score exceeds the supported range.", exception);
                }
                try (PreparedStatement update = connection.prepareStatement(
                        "UPDATE players SET total_points = ? WHERE id = ?")) {
                    update.setInt(1, updatedPoints);
                    update.setLong(2, playerId);
                    if (update.executeUpdate() != 1) {
                        throw new SQLException("The active player score could not be saved.");
                    }
                }
                connection.commit();
                return updatedPoints;
            } catch (SQLException | RuntimeException exception) {
                connection.rollback();
                throw exception;
            }
        }
    }

    private void createSchema() throws SQLException {
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS players (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        display_name TEXT NOT NULL,
                        name_key TEXT NOT NULL UNIQUE,
                        total_points INTEGER NOT NULL DEFAULT 0 CHECK(total_points >= 0),
                        language TEXT NOT NULL,
                        last_played TEXT NOT NULL
                    )
                    """);
        }
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(jdbcUrl);
    }

    private static long findPlayerId(Connection connection, String nameKey) throws SQLException {
        try (PreparedStatement query = connection.prepareStatement(
                "SELECT id FROM players WHERE name_key = ?")) {
            query.setString(1, nameKey);
            try (ResultSet result = query.executeQuery()) {
                return result.next() ? result.getLong(1) : 0;
            }
        }
    }

    private static Optional<PlayerProfile> findPlayer(Connection connection, long playerId) throws SQLException {
        try (PreparedStatement query = connection.prepareStatement(
                "SELECT id, display_name, total_points, language FROM players WHERE id = ?")) {
            query.setLong(1, playerId);
            try (ResultSet result = query.executeQuery()) {
                return result.next() ? Optional.of(readProfile(result)) : Optional.empty();
            }
        }
    }

    private static PlayerProfile readProfile(ResultSet result) throws SQLException {
        String languageName = result.getString("language");
        StudyLanguage language;
        try {
            language = StudyLanguage.valueOf(languageName);
        } catch (IllegalArgumentException exception) {
            throw new SQLException("The saved player language is not supported: " + languageName, exception);
        }
        return new PlayerProfile(result.getLong("id"), result.getString("display_name"),
                result.getInt("total_points"), language);
    }
}
