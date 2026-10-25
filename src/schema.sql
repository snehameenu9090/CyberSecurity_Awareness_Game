-- Reference schema for the local SQLite player store.
-- PlayerDatabase currently creates this table directly in Java.
CREATE TABLE IF NOT EXISTS players (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    display_name TEXT NOT NULL,
    name_key TEXT NOT NULL UNIQUE,
    total_points INTEGER NOT NULL DEFAULT 0 CHECK (total_points >= 0),
    language TEXT NOT NULL,
    last_played TEXT NOT NULL
);
