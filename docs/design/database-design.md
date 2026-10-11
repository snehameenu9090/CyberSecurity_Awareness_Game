# Database Design

## Storage overview

The main application uses SQLite through Xerial SQLite JDBC. `PlayerDatabase()` selects `data/cyberaware.db` relative to the current working directory, creates the parent directory when needed, and creates the `players` table if it is absent.

The ignored `backend/data/cyberaware.db` file is also present in the working tree, but it is not the default selected by the current no-argument constructor. Neither database file is part of the schema documentation.

## Entity: `players`

| Column | SQLite type | Rules | Purpose |
|---|---|---|---|
| `id` | INTEGER | Primary key, autoincrement | Internal player identifier |
| `display_name` | TEXT | Not null | Name displayed by the application |
| `name_key` | TEXT | Not null, unique | Trimmed, lower-case key used to find a player without case-sensitive duplicates |
| `total_points` | INTEGER | Not null, default 0, check >= 0 | Accumulated player points |
| `language` | TEXT | Not null | `StudyLanguage` enum name |
| `last_played` | TEXT | Not null | Timestamp updated when a player is opened |

## Relationships and lifecycle

The inspected schema contains one entity and no foreign keys or cross-table relationships. Quiz answers and game-round details are not persisted in this table. A player is inserted the first time a normalized name is used; subsequent sessions update the display name, language, and `last_played` value. Score updates use the player's identifier.

## Source of truth and maintenance

The application currently creates the table with SQL embedded in `PlayerDatabase.java`; it does not execute `database/schema.sql`. The SQL file is a documentation/bootstrap reference and should be kept in sync if the Java schema changes. No schema migration framework was found.

The `.db` files contain local player data and remain ignored by Git.
