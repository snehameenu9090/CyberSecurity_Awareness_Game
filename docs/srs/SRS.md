# Software Requirements Specification

## 1. Introduction and purpose

This Software Requirements Specification (SRS) describes the observable scope and requirements of the Cybersecurity Awareness Game, a Java desktop application for practising everyday cyber-safety decisions. It is based on the inspected source, Maven configuration, and existing project README; proposed requirements are identified as such where implementation evidence is incomplete.

## 2. Scope and objectives

The application provides a Swing interface with educational lessons, quizzes, and four interactive games. It offers selectable English, Hindi, Tamil, and Telugu experiences and stores player names, language preferences, and points in a local SQLite database when available.

Objectives:

1. Explain common cyber-safety risks with practical examples and checklists.
2. Give learners a low-risk way to practise recognition and decision-making.
3. Provide immediate feedback through lessons, quizzes, and game rounds.
4. Retain basic player progress locally between application launches when SQLite is available.

Out of scope: network accounts, cloud synchronization, real security scanning, and replacing professional security guidance.

## 3. User roles and use cases

| Role | Description | Supported use cases |
|---|---|---|
| Learner/player | Uses the desktop application for self-guided learning | Select a supported language; start or resume a named local player; read lessons; play mini-games; take topic quizzes; view score/feedback |
| Maintainer/evaluator | Builds or reviews the project | Launch the Java application; inspect documentation and source; review the local database schema |

The use cases above describe visible capabilities documented by the project. Detailed permissions and administrative functions are not present in the inspected scope.

## 4. Functional requirements

| ID | Requirement |
|---|---|
| FR-01 | The application shall provide a desktop user interface built with Java Swing. |
| FR-02 | The learner shall be able to select English, Hindi, Tamil, or Telugu from the selectable study languages. |
| FR-03 | The application shall present six awareness topics with guidance, examples, and recommended actions. |
| FR-04 | The learner shall be able to select among password, phishing, public Wi-Fi, malware, and AI-safety quiz categories. |
| FR-05 | The application shall provide Cyber Shield Defender, Phishing Inbox, Malware Cleanup Crew, and Passphrase Builder mini-games. |
| FR-06 | The learner shall receive feedback on quiz answers and game outcomes. |
| FR-07 | The application shall accept a non-blank player name to start a session. |
| FR-08 | When SQLite is available, the application shall store player display name, normalized unique name key, language, total points, and last-played timestamp locally. |
| FR-09 | When SQLite initialization fails, the application shall continue with in-memory player state for the current session. |
| FR-10 | The application shall reject negative points and prevent a score from exceeding the integer range. |

## 5. Non-functional requirements

| ID | Requirement |
|---|---|
| NFR-01 | The project shall compile for Java 17 as declared in Maven and the Windows launch scripts. |
| NFR-02 | Player data shall be stored locally; the inspected application flow does not transmit it to a remote service. |
| NFR-03 | Database writes shall use parameterized statements for player values and transactions for multi-step updates. |
| NFR-04 | The user interface shall provide readable feedback and educational explanations for interactive activities. |
| NFR-05 | The selectable languages shall render Indic scripts using an appropriate available font configuration. |
| NFR-06 | The application should remain usable without persistent SQLite storage for the current session, subject to in-memory fallback behavior. |

Usability, accessibility, and performance targets have not been formally measured and require evaluation.

## 6. Hardware and software requirements

### Software

- JDK 17 or newer.
- Maven for the Maven build path; alternatively, use the provided Windows scripts.
- SQLite JDBC and SLF4J dependencies as declared in `pom.xml` (downloaded by Maven or by the Windows scripts).
- A graphical desktop environment for Swing.

### Hardware

The implementation requires a computer capable of running a supported Java 17 desktop runtime. No minimum CPU, memory, display resolution, or storage specification was found in the project; these values are not asserted here.

## 7. Constraints and assumptions

- The main application is a Swing desktop program; the separate `swing_and_applet_programs/` folder contains practical examples and is not the main runtime.
- The normal database path is `data/cyberaware.db`, relative to the working directory.
- The application creates the `players` table itself. `database/schema.sql` is a reference representation, not a migration mechanism.
- A second ignored database exists at `backend/data/cyberaware.db`; it is not the default path selected by the current constructor.
- The Windows scripts compile sources directly and download dependencies on first run.
- The four selectable languages are a subset of the language enum; the SRS describes selectable UI behavior, not every string's translation coverage.
- No historical daily work records, screenshots, or verified test results were supplied during inspection.

## 8. Acceptance criteria

These criteria are based on implemented behavior and are intended to be checked during a future test run:

1. A Java 17 build completes using Maven or the Windows launcher.
2. The Swing application opens in a graphical desktop session.
3. A learner can select one of the four selectable languages and navigate lessons, games, and quiz categories.
4. A non-blank player can start a session; a blank name is not accepted.
5. If SQLite initializes, a player record can be created/reopened and score/language values can be persisted.
6. When SQLite cannot initialize, the application can hold player state in memory for the running session.
7. Quiz and game activities present their implemented feedback/results.

**Verification status:** These criteria have not been executed as part of this documentation change. See [`test-report.md`](../../testing/test-report.md).
