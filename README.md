# Cybersecurity Awareness Game

A Java Swing desktop learning game that introduces practical cybersecurity habits through short lessons, quizzes, and interactive mini-games. The project is intended for learners and for college project evaluation.

## Problem statement

People regularly encounter phishing, weak or reused passwords, unsafe networks, malware, and account recovery problems. This project turns selected safety guidance into short, approachable lessons and practice activities.

## Objectives

- Present practical cybersecurity guidance with examples and checklists.
- Let learners practise identifying suspicious messages and unsafe choices.
- Offer quizzes and four short games with feedback.
- Support selectable English, Hindi, Tamil, and Telugu content.
- Save a player's display name, language, and points locally when SQLite is available.

## Features

- Six awareness topics with guidance, real-world examples, and checklists.
- Five quiz topics: passwords, phishing, public Wi-Fi, malware, and AI safety.
- Four mini-games: Cyber Shield Defender, Phishing Inbox, Malware Cleanup Crew, and Passphrase Builder.
- Player sessions and points saved in a local SQLite database.
- Keyboard and mouse interaction, results, explanations, and retry flows.
- Separate Swing and Applet practical examples exist in the local project tree; they are not part of this documentation-only publication.

## Technology stack

| Area | Technology |
|---|---|
| Language/runtime | Java 17 |
| Desktop UI | Java Swing |
| Build | Maven (`pom.xml`) or the included Windows launch scripts |
| Persistence | SQLite through Xerial SQLite JDBC 3.46.1.0 |
| Logging API dependency | SLF4J API 1.7.36 |
| Diagram sources | Mermaid (`.mmd`) |

## Architecture overview

The project separates Swing presentation modules from application/session coordination and backend content/model/database code. See the [system architecture](./docs/design/system-architecture.md) and [diagram gallery](./docs/design/diagrams.md).

```text
src/main/java/com/cyberaware/
├── frontend/  Swing window, lessons, games, quiz, and theme
└── backend/   application/session, content, models, and SQLite persistence
```

## Database

The application opens `data/cyberaware.db` by default and creates the `players` table if needed. Player records include a display name, case-insensitive name key, total points, selected language, and last-played timestamp. If SQLite cannot initialize, the current session uses in-memory player state instead.

The repository also contains an ignored `backend/data/cyberaware.db`; it is retained, but the current `PlayerDatabase` constructor does not select that path by default. Database files are local user data and are not committed. The schema is documented in [`database/schema.sql`](./database/schema.sql) and [`database-design.md`](./docs/design/database-design.md); the application currently creates the table in Java rather than loading that SQL file.

## Requirements

- Java Development Kit (JDK) 17 or newer.
- Maven for Maven commands, or a Windows environment for the included `.bat` / `.ps1` launchers.
- Internet access on first launch of the standalone Windows scripts if their ignored `target/lib/` dependencies have not already been downloaded.
- A graphical desktop environment to use the Swing application.

## Setup and run

### Windows launch scripts

From the repository root, run either `run.bat` or `run.ps1`. The scripts compile the main application and start `com.cyberaware.backend.CyberAwareApp`. On first use, they download the SQLite JDBC and SLF4J API JARs to the ignored `target/lib/` directory.

### Maven

```powershell
mvn package
```

Maven resolves dependencies from `pom.xml` and creates the JAR. The JAR does not bundle its runtime dependencies; use `run.bat` or `run.ps1` on Windows to compile and launch with the required classpath, or launch the JAR with its dependencies explicitly on the classpath.

## Screenshots

No project screenshots were included in the inspected files. Add approved screenshots under [`assets/screenshots/`](./assets/screenshots/) and link them here with relative paths.

## Testing summary

No automated test sources or historical test report were present in the inspected project tree. Tests have not been run as part of this documentation organization; see the [testing strategy](./testing/testing-strategy.md), [test cases](./testing/test-cases.md), and [test report](./testing/test-report.md) for a transparent status.

## Limitations and future scope

- Content is educational and does not replace professional security advice.
- The local SQLite store keeps player data on the current machine; there is no account sync or multi-device leaderboard.
- The standalone launch scripts target Windows; portability of those scripts has not been tested.
- The historical 12-week activity log, dated daily work, and screenshot evidence are not available yet. Reports remain drafts until confirmed.
- This documentation-only publication does not include the Java source, build files, launch scripts, or standalone practical examples; those remain in the local project folder.
- Potential future work includes accessibility review, automated UI/database tests, additional lessons, backup/export for local scores, and confirmed screenshots.

## Documentation

- [Documentation index](./docs/README.md)
- [Software Requirements Specification](./docs/srs/SRS.md)
- [System architecture](./docs/design/system-architecture.md)
- [Database design](./docs/design/database-design.md)
- [Rendered diagram gallery and editable Mermaid sources](./docs/design/diagrams.md)
- [Installation guide](./user-manual/installation-guide.md)
- [User guide](./user-manual/user-guide.md)
- [Testing documentation](./testing/testing-strategy.md)
- [Weekly progress navigation](./docs/weekly-progress/README.md)

## 12-week progress

The focus and deliverables below are **suggested planning categories, not verified project history**. Each report is independently maintained and marked **Draft — Needs Confirmation** until the project's daily records are supplied.

| Week | Main Focus | Deliverables | Status | Open Report |
|---|---|---|---|---|
| 01 | Scope and baseline review | Confirmed requirements and starting inventory (to verify) | Draft — Needs Confirmation | [Week 01](./weekly-progress/week-01/README.md) |
| 02 | Application structure | Architecture and module notes (to verify) | Draft — Needs Confirmation | [Week 02](./weekly-progress/week-02/README.md) |
| 03 | Awareness lessons | Lesson content and presentation (to verify) | Draft — Needs Confirmation | [Week 03](./weekly-progress/week-03/README.md) |
| 04 | Quiz content and flow | Quiz categories, questions, and feedback (to verify) | Draft — Needs Confirmation | [Week 04](./weekly-progress/week-04/README.md) |
| 05 | Phishing Inbox game | Drag-and-drop scenario and guidance (to verify) | Draft — Needs Confirmation | [Week 05](./weekly-progress/week-05/README.md) |
| 06 | Malware Cleanup game | App-review scenario and feedback (to verify) | Draft — Needs Confirmation | [Week 06](./weekly-progress/week-06/README.md) |
| 07 | Passphrase Builder game | Timed passphrase practice (to verify) | Draft — Needs Confirmation | [Week 07](./weekly-progress/week-07/README.md) |
| 08 | Cyber Shield Defender game | Arcade controls and safety prompts (to verify) | Draft — Needs Confirmation | [Week 08](./weekly-progress/week-08/README.md) |
| 09 | Localization | Selectable languages and translated UI/content (to verify) | Draft — Needs Confirmation | [Week 09](./weekly-progress/week-09/README.md) |
| 10 | Local player persistence | SQLite profiles, languages, and scores (to verify) | Draft — Needs Confirmation | [Week 10](./weekly-progress/week-10/README.md) |
| 11 | Integration and quality | Integrated experience and test evidence (to verify) | Draft — Needs Confirmation | [Week 11](./weekly-progress/week-11/README.md) |
| 12 | Evaluation and handover | Final review, user guidance, and evidence (to verify) | Draft — Needs Confirmation | [Week 12](./weekly-progress/week-12/README.md) |
