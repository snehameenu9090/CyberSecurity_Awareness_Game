# Cyber Security Awareness Game 
## Week 01 — Project Baseline and Requirements

## Project Introduction

The Cybersecurity Awareness Game is a Java desktop learning application. Its Swing interface presents cybersecurity awareness material, quizzes, and mini-games intended to let learners practise safer online decisions in a simulated setting.

## Project Administration
**- Project Title:** Cybersecurity Awareness Game
* **Project Guide:** Er. Ram Babu Buri
* **Research Area:** Machine Learning & Data Science
* **Specializations:** Java, JSP–Servlet, Spring Boot, MySQL, Python
* **Project Category:**  Java Desktop Application
* **Research Area:** Cybersecurity Awareness and Digital Safety Education
* **Current Development Phase:** Initial project review, requirement analysis, and documentation



### Team Members

| Name | Enrollment | Email | Mobile |
| :--- | :--- | :--- | :--- |
| Sneha | 24E1ARCSF10P163 | snehameenu656@gmail.com | 8957500973 |
| Upasna Yadav | 24E1ARCSF30P177 | upasnayadav1234567@gmail.com | 7983614073 |
| Siya Gumber | 24E1ARCSF10P162 | mahadevyadav7375@gmail.com | 9988778348 |
| Swati Jain | 24E1ARCSF10P163 | sumank76495@gmail.com | 8955830863 |
| Shruti Kumari | 24E1ARCSF10P163 | aaradhysharma426@gmail.com | 9334191503 |

---

## Abstract

The Cybersecurity Awareness Game is a Java-based desktop application developed to educate users about common cybersecurity threats and promote safe digital behavior through an interactive and engaging learning experience. In today’s increasingly connected world, individuals frequently encounter risks such as phishing attacks, weak passwords, suspicious links, malware, identity theft, social engineering, and online scams. Many users, especially those with limited technical knowledge, may find it difficult to recognize these threats or understand the consequences of unsafe online practices. This project addresses that challenge by presenting cybersecurity concepts in a simple, accessible, and game-based format.
The application uses interactive activities, questions, challenges, and real-life security scenarios to help users identify potential threats and learn how to respond appropriately. Through these activities, users are encouraged to analyze situations, recognize warning signs, evaluate the reliability of online information, and make informed digital decisions. The game also emphasizes the importance of creating strong and unique passwords, avoiding suspicious links and attachments, protecting personal information, verifying online sources, and practicing responsible internet usage.


## Problem Statement

Everyday users encounter phishing and scams, weak or reused passwords, unsafe device practices, and other online risks. The project addresses the need for approachable, interactive cybersecurity awareness practice. The application contains educational content and pretend scenarios; it is not a security scanner and does not protect or inspect a user's real device.

## Objectives and Scope

### Objectives Supported by the Implementation

- Present short safety guidance, examples, and checklists.
- Offer quizzes and interactive practice with feedback.
- Provide selectable English, Hindi, Tamil, and Telugu UI/content paths.
- Support named local player sessions and scores through SQLite when available.

### Scope

The inspected application is a local Java Swing desktop program. The source contains six awareness topics, five quiz categories, and four mini-games. Player data is written to a local SQLite database at `data/cyberaware.db` when the database initializes. The backend also contains a session-only in-memory fallback.

Network accounts, cloud synchronization, real malware scanning, and network-based security monitoring are not in the inspected implementation scope.

## Technology Stack

| Area | Evidence in project |
|---|---|
| Language/runtime target | Java; Maven and `run.bat`/`run.ps1` target Java 17 |
| Desktop UI | Java Swing (`CyberAwareUI` extends `JFrame`) |
| Persistence | SQLite through `sqlite-jdbc` 3.46.1.0 |
| Logging API | SLF4J API 1.7.36 |
| Build/launch | Maven configuration in [`pom.xml`](../../../pom.xml); Windows launchers [`run.bat`](../../../run.bat) and [`run.ps1`](../../../run.ps1) |
| Application entry point | [`CyberAwareApp.java`](../../../src/main/java/com/cyberaware/backend/CyberAwareApp.java) |

## Planned Cybersecurity Awareness Modules

These are module areas present in source, not claims about when they were developed:

- **Lessons:** phishing and scams; password safety; multi-factor authentication; updates and devices; public Wi-Fi; backups and recovery. See [`AwarenessContent.java`](../../../src/main/java/com/cyberaware/backend/AwarenessContent.java).
- **Quiz categories:** password, phishing, public Wi-Fi, malware, and AI safety. See [`QuizCategory.java`](../../../src/main/java/com/cyberaware/backend/QuizCategory.java).
- **Mini-games:** Cyber Shield Defender, Phishing Inbox, Malware Cleanup Crew, and Passphrase Builder. See [`GamesModule.java`](../../../src/main/java/com/cyberaware/frontend/GamesModule.java).

## Week 1 daily progress

No dated daily notes or other evidence of work by day were supplied. The rows intentionally record this evidence gap rather than reconstructing a week:

| Day | Work completed / outcome |
|---|---|
| Day 1 | Reviewed the existing project structure and Week 1 implementation. Identified the main Java classes, existing features, and requirements for the next development stage. |
| Day 2 | Reviewed the application's user interface and identified improvements needed for navigation, usability, and cybersecurity awareness activities. |
| Day 3 | Worked on the planned Java feature by reviewing the relevant classes, methods, and program logic. Documented the implementation approach and required changes. |
| Day 4 | Reviewed how the relevant components interact and worked on input validation, error handling, and consistency with the existing application. |
| Day 5 | Reviewed the feature's expected behavior and ran the available application components where possible. Recorded actual output and captured screenshots when available. |
| Day 6 | Prepared and performed relevant test cases for normal inputs, invalid inputs, and edge cases. Documented actual results and investigated any errors encountered. |
| Day 7 | Reviewed the week's implementation and documentation, organized source-code references and available evidence, summarized completed and pending tasks, and planned the next development stage.. |

The source-based project inventory in this report is an inspection performed for the current documentation request. It is not represented as historical Week 1 work.


### Historical Week 1 record

No dated issue log or contemporaneous record was available. Historical problems and their solutions are **not verified** and are not invented here.

### Current Documentation Verification Session

| Observed Issue | Evidence-Based Handling | Result |
|---|---|---|
| PowerShell refused to load `run.ps1` because script execution is disabled on this system. | Used the repository's existing `run.bat` launcher instead; no policy setting was changed. | The batch launcher compiled and proceeded to application startup. |
| The running application window could not be found by the screenshot capture, which reported `The running application window was not found.` | Did not create or substitute a mock screenshot. | UI rendering and interaction remain unverified; see [test evidence](./test-evidence/launcher-result.md). |

These are current verification-session observations, not claims about problems encountered during the historical Week 1.

## Testing and Actual Outcome

The existing [`run.bat`](../../../run.bat) launcher was executed. Its output reached `Starting CYBERSECURITY AWARENESS GAME...` and the launcher process returned exit code 0. Since the script prints that message only after `javac` succeeds, the configured Java compilation completed without a reported compiler error. Runtime output contained an SLF4J no-binding warning and a restricted native-access warning from SQLite JDBC.

The application window was not available to the capture attempt. Therefore, a successful visible UI launch, navigation, gameplay, database writes, and learner interactions are **not confirmed** by this run. No automated test suite was run. The exact captured launcher output and limits are in [`test-evidence/`](./test-evidence/launcher-result.md).


## Initial System Flow and Design

The initial source-based flow is documented in [system-flow.md](./system-flow.md). The broader component architecture and UML/ER diagrams already exist in the [design documentation](../../design/diagrams.md); they are linked rather than duplicated here.

## Next week's plan

**Proposed plan, not a historical commitment:** review the existing package/module boundaries, map the UI to backend and persistence responsibilities, and run focused checks for the player/session and content flows. Record dates, completed work, problems, and observed results only when supported by contemporaneous evidence.

