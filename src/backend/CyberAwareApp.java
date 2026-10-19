package com.cyberaware.backend;

import com.cyberaware.frontend.CyberAwareUI;
import com.cyberaware.frontend.UiTheme;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public final class CyberAwareApp {
    private static final Logger LOGGER = Logger.getLogger(CyberAwareApp.class.getName());

    private final PlayerDatabase playerDatabase;
    private String playerName = "";
    private int totalPoints;
    private StudyLanguage studyLanguage = StudyLanguage.ENGLISH;
    private long playerId;

    public CyberAwareApp() throws SQLException {
        playerDatabase = new PlayerDatabase();
        playerDatabase.mostRecentPlayer().ifPresent(profile -> {
            playerName = profile.name();
            totalPoints = profile.totalPoints();
            studyLanguage = profile.language();
        });
    }

    public boolean startSession(String name) throws SQLException {
        if (name == null || name.isBlank()) {
            return false;
        }
        PlayerProfile profile = playerDatabase.openPlayer(name, studyLanguage);
        restorePlayer(profile);
        return true;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        if (playerName == null || playerName.isBlank()) {
            throw new IllegalArgumentException("Player name cannot be blank.");
        }
        this.playerName = playerName.trim();
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public int getScore() {
        return totalPoints;
    }

    public StudyLanguage getStudyLanguage() {
        return studyLanguage;
    }

    public void setStudyLanguage(StudyLanguage studyLanguage) throws SQLException {
        if (studyLanguage == null) {
            throw new IllegalArgumentException("Study language cannot be null.");
        }
        if (playerId > 0) {
            playerDatabase.updateLanguage(playerId, studyLanguage);
        }
        this.studyLanguage = studyLanguage;
    }

    public void addPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("Points cannot be negative.");
        }
        if (playerId <= 0) {
            throw new IllegalStateException("Start a player session before awarding points.");
        }
        try {
            totalPoints = playerDatabase.addPoints(playerId, points);
        } catch (SQLException exception) {
            LOGGER.log(Level.SEVERE, "Could not save the player's score.", exception);
            JOptionPane.showMessageDialog(null,
                    "Your score could not be saved to the local database.\n"
                            + "Please check that the project data folder is writable.\n\n"
                            + exception.getMessage(),
                    "Database save error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void restorePlayer(PlayerProfile profile) {
        playerId = profile.id();
        playerName = profile.name();
        totalPoints = profile.totalPoints();
        studyLanguage = profile.language();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException e) {
                System.err.println("Could not load the system look and feel: " + e.getMessage());
            }
            UiTheme.install();
            try {
                CyberAwareApp app = new CyberAwareApp();
                new CyberAwareUI(app).setVisible(true);
            } catch (Exception exception) {
                LOGGER.log(Level.SEVERE, "Could not start the application.", exception);
                JOptionPane.showMessageDialog(null,
                        "The application could not be opened:\n\n"
                                + exception.getMessage(),
                        "Application startup error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
