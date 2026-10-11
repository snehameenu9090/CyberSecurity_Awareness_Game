# Week 01 — Initial System Flow

> This flow is derived from the current source and Windows launcher. It describes the code path; it is not a claim that every UI action was exercised during testing.

## Startup and learner flow

```mermaid
flowchart TD
    A[Run run.bat] --> B[Compile CyberAwareApp with javac --release 17]
    B -->|compile succeeds| C[Start CyberAwareApp main]
    C --> D[Schedule startup on Swing event-dispatch thread]
    D --> E[Initialize PlayerDatabase]
    E --> F{SQLite initializes?}
    F -->|yes| G[Create or open local data/cyberaware.db]
    F -->|no| H[Use in-memory session fallback]
    G --> I[Load most recent player when present]
    H --> J[Build Swing welcome UI]
    I --> J
    J --> K[Choose a selectable study language]
    K --> L[Enter a non-blank player name]
    L --> M[Start or open a local player session]
    M --> N[Choose awareness lessons, quiz, or games]
    N --> O[Read lessons / answer quiz questions / play simulated mini-games]
    O --> P[Show feedback and award points where implemented]
    P --> Q[Persist score and language when SQLite is available]
```

## Component responsibilities

| Component | Responsibility in the inspected source |
|---|---|
| `CyberAwareApp` | Entry point and session coordination; creates the player database and starts the Swing UI. |
| `CyberAwareUI` | Welcome screen and navigation between application sections. |
| `AwarenessModule` / `AwarenessContent` | Render educational topics and their advice, examples, and checklists. |
| `QuizModule` / `QuizQuestionBank` | Provide category-based quiz interaction and feedback. |
| `GamesModule` and game panels | Present the four simulated practice games. |
| `PlayerDatabase` | Read/write local SQLite player profiles and points, or retain fallback state in memory. |

## Initial data model

The persisted `players` table contains `id`, `display_name`, unique normalized `name_key`, `total_points`, `language`, and `last_played`. See the existing [database schema](../../../database/schema.sql) and [ER diagram](../../design/ER-Diagram.mmd).

## Related design documents

- [System architecture](../../design/system-architecture.md)
- [Diagram gallery](../../design/diagrams.md)
- [Class diagram](../../design/Class-Diagram.mmd)
- [Sequence diagram](../../design/Sequence-Diagram.mmd)
- [ER diagram](../../design/ER-Diagram.mmd)

## Navigation

- [Week 01 report](./README.md)
- [Requirements](./requirements.md)
