# Cybersecurity Awareness Game

A Java Swing desktop application that teaches practical online-safety habits through short lessons, quizzes, and simulated mini-games.

## Problem statement

Learners encounter phishing, weak or reused passwords, unsafe networks, malware, and account-recovery risks. This project presents selected safety guidance and simulated practice in an approachable desktop experience. It does not scan or protect a real device, inspect real email, or replace professional security advice.

## Objectives and scope

- Present cybersecurity advice with examples and checklists.
- Let learners practise choices in topic quizzes and four educational games.
- Provide feedback and an aggregate player score.
- Offer English, Hindi, Tamil, and Telugu as selectable study languages.
- Store a named player profile and total points locally when SQLite is available.

The inspected application has no network account, cloud synchronization, real malware scanner, or persisted lesson/quiz/game history.

## Main features

- Six awareness topics: phishing/scams, passwords, multi-factor authentication, updates/devices, public Wi-Fi, and backups/recovery.
- Five quiz topics: password safety, phishing, public Wi-Fi, malware, and AI safety.
- Four simulations: Cyber Shield Defender, Phishing Inbox, Malware Cleanup Crew, and Passphrase Builder.
- Named player sessions and aggregate points in SQLite, with an in-memory current-session fallback if SQLite initialization fails.

The features are present in source. Except for the launcher and initial welcome-screen evidence summarized below, feature interactions have not been fully verified.

## Technology stack

| Area | Technology |
|---|---|
| Language/runtime | Java 17 target |
| Desktop UI | Java Swing |
| Build | Maven or included Windows launch scripts |
| Local persistence | SQLite via Xerial SQLite JDBC 3.46.1.0 |
| Logging API | SLF4J API 1.7.36 |
| Editable diagrams | Mermaid |

## Architecture

The frontend package contains the Swing window and awareness, quiz, and game modules. The backend contains session coordination, educational data/models, and SQLite player persistence. See the [system architecture](./docs/design/system-architecture.md), [diagram gallery](./docs/design/diagrams.md), and [Week 3 design report](./docs/weekly-progress/week-03/README.md).

```text
src/main/java/com/cyberaware/
├── frontend/  Swing UI, lessons, quiz, games, and theme
└── backend/   application/session, content, models, and SQLite persistence
```

The SQLite store contains one `players` table with a display name, normalized unique name key, total points, language, and last-played timestamp. Java initializes the table; [`database/schema.sql`](./database/schema.sql) is a reference schema. See [database design](./docs/design/database-design.md) and the [ER diagram](./docs/design/ER-Diagram.mmd).

## Requirements

- JDK 17 or newer.
- A graphical desktop environment for the Swing application.
- For Maven commands: Maven installed.
- For first use of the Windows launchers: network access if the ignored SQLite JDBC and SLF4J JARs are not already present in `target/lib/`.

## Installation and execution

### Windows launcher

From the repository root, run `run.bat` or `run.ps1`. The scripts compile the application with `javac --release 17` and start `com.cyberaware.backend.CyberAwareApp`. They download the two runtime JARs to ignored `target/lib/` if needed.

### Maven build

```powershell
mvn package
```

Maven resolves declared dependencies and packages a JAR. The JAR does not bundle runtime dependencies; run it with those dependencies on the classpath or use the Windows launchers.

Detailed steps and troubleshooting are in the [installation guide](./user-manual/installation-guide.md). Learner controls and safety notes are in the [user guide](./user-manual/user-guide.md).

## Application screenshot

The following genuine image was captured from the running Swing application using a clean temporary profile. It demonstrates the initial welcome screen only; it does not verify player submission, navigation, lessons, quizzes, gameplay, or persistence.

![Cybersecurity Awareness Game welcome screen](./docs/weekly-progress/week-02/screenshots/welcome-screen.png)

See the [capture notes](./docs/weekly-progress/week-02/test-evidence/launcher-run.md) for method and limits.

## Testing summary

Existing launcher evidence records a Java 17-target compile/start run that returned exit code 0. A separate run produced the visible welcome-screen capture. Both records include SLF4J no-binding and SQLite native-access warnings. These results are limited to launcher/initial rendering; lesson, quiz, game, navigation, and database behavior remain unverified. No automated test suite was identified.

A fresh isolated compile during this documentation review also passed with `javac 25.0.4.1 --release 17`. A copied launcher reached its startup message and the Java process remained alive, but no visible window was detected in that re-check; it is not counted as a new UI pass. See the [test report](./testing/test-report.md).

- [Test report](./testing/test-report.md)
- [Manual test cases](./testing/test-cases.md)
- [Testing strategy](./testing/testing-strategy.md)
- [Week 1 launcher evidence](./docs/weekly-progress/week-01/test-evidence/launcher-result.md)
- [Week 2 launcher and screenshot evidence](./docs/weekly-progress/week-02/test-evidence/launcher-run.md)

## Limitations and future improvements

- Educational simulations do not replace professional advice or perform real security actions.
- Local SQLite data is not synchronized; the schema stores aggregate player points but not activity history.
- Windows launch scripts are provided; their portability to other platforms is not established.
- Accessibility, performance, and translation completeness have not been formally evaluated.
- Useful future work includes automated tests, accessibility review, additional confirmed screenshots, and optional export/backup for local scores.

## Documentation

- [Documentation index](./docs/README.md)
- [Software Requirements Specification](./docs/srs/SRS.md)
- [System architecture](./docs/design/system-architecture.md)
- [Design diagrams](./docs/design/diagrams.md)
- [Database design](./docs/design/database-design.md)
- [Installation guide](./user-manual/installation-guide.md)
- [User guide](./user-manual/user-guide.md)
- [Testing documentation](./testing/testing-strategy.md)
- [12-week progress index](./docs/weekly-progress/README.md)

## 12-week progress

Each report separates present source evidence from historical attribution. Because no dated daily development log was found, day allocations are labeled as estimated reconstructions for review; current source presence does not establish the week in which a feature was implemented.

| Week | Focus | Report |
|---|---|---|
| 01 | Project scope and baseline | [Week 01](./docs/weekly-progress/week-01/README.md) |
| 02 | Application structure and evidence | [Week 02](./docs/weekly-progress/week-02/README.md) |
| 03 | Requirements and system design | [Week 03](./docs/weekly-progress/week-03/README.md) |
| 04 | Awareness lessons | [Week 04](./docs/weekly-progress/week-04/README.md) |
| 05 | Phishing-awareness exercise | [Week 05](./docs/weekly-progress/week-05/README.md) |
| 06 | Quiz functionality and validation | [Week 06](./docs/weekly-progress/week-06/README.md) |
| 07 | Scoring and feedback | [Week 07](./docs/weekly-progress/week-07/README.md) |
| 08 | Local database and persistence | [Week 08](./docs/weekly-progress/week-08/README.md) |
| 09 | Player sessions and language | [Week 09](./docs/weekly-progress/week-09/README.md) |
| 10 | Testing and verification | [Week 10](./docs/weekly-progress/week-10/README.md) |
| 11 | Integration and documentation | [Week 11](./docs/weekly-progress/week-11/README.md) |
| 12 | Final verification and project summary | [Week 12](./docs/weekly-progress/week-12/README.md) |

## Week 1: Project Introduction, Objectives & Initial Setup

> **Evidence note:** This is a retrospective, source-based baseline. The available Git history contains documentation commits dated 2026-10-11, but no dated daily development log establishing when individual application features were created. Current source presence is not proof that a feature was completed during Week 1. The day-wise entries below are a **Proposed Development Plan**, not a historical activity record.

### 1. Project Administration

| Item | Verified information |
|---|---|
| Project | Cybersecurity Awareness Game |
| Repository | [snehameenu9090/CyberSecurity_Awareness_Game](https://github.com/snehameenu9090/CyberSecurity_Awareness_Game) |
| Application type | Java desktop learning application |
| Week 1 dates | Not yet verified; no dated Week 1 activity log was found |
| Development branch in this checkout | `main` |
| Project goal | Practise practical online-safety decisions through lessons, quizzes, and simulated games |

### 2. Abstract

The Cybersecurity Awareness Game is a desktop application intended to introduce learners to everyday cybersecurity risks through educational content and simulated activities. The repository source includes awareness lessons, quizzes, games, named player sessions, and local score persistence. These are source-supported capabilities; the available test evidence does not establish that every lesson, quiz, game, or persistence flow has been exercised successfully.

### 3. Problem Statement

People encounter phishing and scams, weak or reused passwords, unsafe network choices, malware lures, and account-security risks. The project provides a low-risk learning environment for understanding safer responses to such situations. Its games are simulations: the application is not a real malware scanner, does not inspect real email, and does not replace professional security advice.

### 4. Project Objectives

- Present practical cybersecurity guidance with examples and recommended actions.
- Give learners simulated opportunities to recognize suspicious situations and practise safer choices.
- Provide quizzes and game feedback to reinforce learning.
- Support selectable study languages and named local player profiles.
- Keep project claims aligned with source evidence and report unverified behavior transparently.

### 5. Scope of the Project

**Included in the inspected source:** a Java Swing desktop interface, six awareness topics, five quiz categories, four simulated games, selectable English/Hindi/Tamil/Telugu study languages, and local SQLite player/score storage with a session-only in-memory fallback if SQLite initialization fails.

**Not included or not established by the inspected implementation:** cloud accounts or synchronization, real device scanning or malware removal, real-email inspection, persisted lesson/game history, accessibility certification, or formal performance targets.

### 6. Technology Stack

| Area | Repository evidence | Status |
|---|---|---|
| Language | Java sources; Maven compiler source/target and Windows launcher specify Java 17 | Java 17 target verified; exact JDK used for historical development is not established |
| GUI framework | Java Swing classes and UI components | Verified in source |
| Database | SQLite through Xerial SQLite JDBC 3.46.1.0; SLF4J API 1.7.36 is also declared | Verified in `pom.xml` and the persistence implementation |
| Build tools | Maven project configuration and `run.bat` / `run.ps1` launchers are present | Verified; IDE/editor used for development is not yet verified |
| IDE/editor | VS Code workspace configuration is present in the local project tree | Configuration exists; actual historical IDE use is not established |
| Testing tools | Manual test documentation and Windows launcher checks are present; no automated test framework or test suite has been identified | Automated testing framework: Not yet verified |

### 7. Game Modules

The following modules are evidenced by the Java source. “Present in source” does not by itself mean that runtime interaction or all acceptance behavior has been tested.

| Module | Objective | Intended learning outcome | Implementation status |
|---|---|---|---|
| Cyber Shield Defender | A simulated arcade activity themed around cyber threats and defensive choices | Recognize that backups, phishing awareness, and protective actions matter | Game implementation and menu entry are present in source; gameplay verification is not established by the available test report |
| Phishing Inbox | Sort simulated email examples into safe and suspicious categories | Notice sender, urgency, and link warning signs before acting | Game implementation and menu entry are present in source; gameplay verification is not established by the available test report |
| Malware Cleanup Crew | Inspect a pretend device and identify simulated unsafe downloads | Treat unexpected or untrusted downloads cautiously; understand that the exercise is not a real scan | Game implementation and menu entry are present in source; gameplay verification is not established by the available test report |
| Passphrase Builder | Assemble a sample passphrase in a word-tile exercise | Practise the idea of long, unique passphrases and avoid reusing a game phrase as a real password | Game implementation and menu entry are present in source; gameplay verification is not established by the available test report |

The accompanying awareness and quiz areas are also source-supported: six lessons cover phishing/scams, password safety, multi-factor authentication, updates/devices, public Wi-Fi, and backups/recovery; quiz categories cover passwords, phishing, public Wi-Fi, malware, and AI safety.

### 8. Week 1: Day-Wise Development Log

No historical day-by-day records were available. The entries below are a **proposed seven-day plan only**; none should be read as completed work or assigned a calendar date.

| Day | Proposed development plan | Historical completion |
|---|---|---|
| Day 1 | Review the repository structure and identify the application entry point and existing documentation. | Not verified |
| Day 2 | Define the cybersecurity awareness problem and intended learner audience. | Not verified |
| Day 3 | Agree on project objectives, boundaries, and non-goals. | Not verified |
| Day 4 | Confirm the Java target, desktop framework, persistence choice, and available build commands from project files. | Not verified |
| Day 5 | Inventory lessons, quiz categories, and game modules from the existing source. | Not verified |
| Day 6 | Draft initial functional scope and a high-level learner/system flow. | Not verified |
| Day 7 | Run an approved baseline build, record only observed outcomes, and prepare a plan for the next week. | Not verified |

### 9. Challenges & Observations

- **Historical attribution is incomplete:** available Git history records documentation changes, not a dated daily development log. No feature is assigned to a specific day or claimed as Week 1 work.
- **Source presence is not a test result:** source confirms the listed modules exist, while the available test report says that gameplay and persistence behaviors have not been fully tested.
- **Testing-tool coverage is limited:** manual test documentation exists, but an automated test framework has not been identified.
- **Evidence-based handling:** this section labels the day-wise schedule as proposed, separates source evidence from runtime verification, and leaves unsupported dates and outcomes unclaimed.

### 10. Week 1 Deliverables

| Classification | Deliverable | Evidence/status |
|---|---|---|
| Completed in the current documentation update | This source-based Week 1 section in the existing root README | Added as documentation now; it is not evidence of historical Week 1 activity |
| Present in the repository; completion week not verified | Java application source, Maven configuration, and Windows launch scripts | Their presence is verified; they are not attributed to Week 1 |
| Present in documentation; historical completion week not verified | Project SRS, architecture/design diagrams, test strategy, manual test cases, and test report | See the [documentation index](./docs/README.md); dates of original creation do not establish Week 1 completion |
| Not available as historical evidence | Dated daily log, Week 1-specific screenshots, and a Week 1 test record | Not verified; no historical claim is made |

### 11. Week 1 Summary

The repository supports describing the application as a Java 17-targeted Swing cybersecurity learning game with SQLite-backed local player data, six awareness topics, five quiz categories, and four simulated games. The available Git history and test documentation do not establish when these features were developed or provide a genuine seven-day Week 1 diary. Accordingly, the daily schedule above is explicitly proposed, and feature implementation is not presented as proof of Week 1 completion.

### 12. Plan for Week 2

Based on the verified project state, the proposed next steps are:

- Map the existing UI, content, game, session, and persistence components to their responsibilities.
- Review the existing architecture and diagrams against the source, correcting documentation only where evidence supports it.
- Run a focused, non-destructive build and record the exact environment, output, and exit status.
- Identify manual checks for the four games, quizzes, language selection, and player persistence; report them as untested until executed.
- Capture genuine screenshots only from the running application, with method and limitations recorded.
