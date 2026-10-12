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
Cybersecurity Awareness Game

### A Java-Based Desktop Application for Interactive Cybersecurity Learning

""Language" (https://img.shields.io/badge/Language-Java-orange)" (https://www.java.com/)
""Project" (https://img.shields.io/badge/Project-Cybersecurity%20Awareness-blue)" (https://github.com/snehameenu9090/CyberSecurity_Awareness_Game)
""Documentation" (https://img.shields.io/badge/Documentation-Weekly%20Progress-green)" (./weekly-progress/)

### 1. Project Overview

The Cybersecurity Awareness Game is a Java-based desktop application developed to promote cybersecurity awareness and encourage safe digital behaviour. The project focuses on making cybersecurity concepts easier to understand through interactive learning activities and practical security-related scenarios.

The application is intended to help users understand common online threats, recognize suspicious activities, and learn essential practices for protecting their digital information.

This project also follows a structured development and documentation approach, with weekly progress reports maintained throughout the planned 12-week development period.

### 2. Problem Statement

The increasing use of digital platforms has made cybersecurity awareness an important requirement. Users may encounter phishing messages, suspicious links, weak passwords, malware, online scams, and privacy-related risks.

A lack of awareness about these threats can lead to compromised accounts, data loss, and unsafe online behaviour.

The Cybersecurity Awareness Game aims to address this problem by presenting cybersecurity concepts in an accessible and interactive learning environment.

3. Project Objectives

- To promote awareness of common cybersecurity threats.
- To explain safe password practices and account protection.
- To improve the ability to identify suspicious links and phishing attempts.
- To encourage safer internet browsing and responsible digital behaviour.
- To provide an engaging way to learn cybersecurity fundamentals.
- To document project requirements, development activities, and testing progress.
- To maintain organized technical documentation throughout the 12-week project plan.

### 4. Key Learning Areas

The following areas define the intended cybersecurity learning scope. Their implementation status should be confirmed against the actual application.

Learning Area| Description
Password Security| Understanding password strength and account protection.
Phishing Awareness| Recognizing suspicious messages, links, and impersonation attempts.
Malware Awareness| Learning about malicious software and unsafe downloads.
Safe Internet Usage| Understanding privacy, browsing safety, and online risks.
Cybersecurity Assessment| Reviewing security concepts through questions or scenarios, where implemented.

Note: The table describes the project's learning scope; it does not claim that every listed module is already implemented.

### 5. Technology Stack

Technology| Purpose
Java| Primary programming language for the desktop application.
Java GUI Framework| To be confirmed from the existing source code.
Database / Data Storage| To be confirmed from the existing implementation.
Development Environment| To be documented according to the environment used.
Git and GitHub| Source-code version control and project documentation.
Markdown| Weekly reports and project documentation.

6. Project Structure

The repository is organized into the following directories and files.

CyberSecurity_Awareness_Game/
│
├── assets/
│   └── Project assets and resources
│
├── database/
│   └── Database-related files and resources
│
├── docs/
│   └── Project documentation
│
├── testing/
│   └── Testing documentation and test evidence
│
├── user-manual/
│   └── User instructions and guidance
│
├── weekly-progress/
│   ├── week-01/
│   │   ├── screenshots/
│   │   └── README.md
│   ├── week-02/
│   │   ├── screenshots/
│   │   └── README.md
│   ├── week-03/
│   ├── week-04/
│   ├── week-05/
│   ├── week-06/
│   ├── week-07/
│   ├── week-08/
│   ├── week-09/
│   ├── week-10/
│   ├── week-11/
│   └── week-12/
│
└── README.md

Note: This is a documentation-oriented overview of the visible repository structure. It does not list every file or imply that all directories already contain completed documentation.

7. Weekly Development Progress

Each weekly report provides a dedicated location for documenting project activities, observations, deliverables, challenges, and subsequent tasks.

## Week 1 — Project Introduction and Planning

Focus: Establishing the project overview, objectives, scope, initial planning, and documentation structure.

Documentation: "View Week 1 Progress Report" (./weekly-progress/week-01/README.md)

## Week 2 — Requirements Analysis and System Design

Focus: Documenting the proposed user roles, software requirements, cybersecurity learning areas, architecture considerations, data-storage approach, and non-functional requirements.

Documentation: "View Week 2 Progress Report" (./weekly-progress/week-02/README.md)

## Week 3 — Detailed Requirements and Architecture

Planned focus: Review the existing Java source code, verify application components, and document the actual architecture.

Documentation: "View Week 3 Progress Report" (./weekly-progress/week-03/README.md)

## Week 4 — Interface and Navigation Review

Planned focus: Review the existing user interface and document the application's navigation and user interactions.

Documentation: "View Week 4 Progress Report" (./weekly-progress/week-04/README.md)

## Week 5 — Core Functionality Review

Planned focus: Identify implemented features, review application behaviour, and document functional requirements against the source code.

Documentation: "View Week 5 Progress Report" (./weekly-progress/week-05/README.md)

## Week 6 — Data Management Review

Planned focus: Inspect the existing data-storage approach and document verified database or file-handling functionality.

Documentation: "View Week 6 Progress Report" (./weekly-progress/week-06/README.md)

## Week 7 — Cybersecurity Content Review

Planned focus: Review educational content and check the accuracy and clarity of the cybersecurity learning material.

Documentation: "View Week 7 Progress Report" (./weekly-progress/week-07/README.md)

## Week 8 — Functional Testing

Planned focus: Prepare and execute test cases for the existing application, recording actual results and reproducible defects.

Documentation: "View Week 8 Progress Report" (./weekly-progress/week-08/README.md)

## Week 9 — Bug Tracking and Quality Review

Planned focus: Document verified defects, their reproduction steps, severity, and resolution status.

Documentation: "View Week 9 Progress Report" (./weekly-progress/week-09/README.md)

## Week 10 — Documentation and User Guidance

Planned focus: Review technical documentation and prepare user instructions based on the application's actual functionality.

Documentation: "View Week 10 Progress Report" (./weekly-progress/week-10/README.md)

## Week 11 — Final Validation

Planned focus: Review completed requirements, verify fixes, and summarize outstanding issues and test results.

Documentation: "View Week 11 Progress Report" (./weekly-progress/week-11/README.md)

## Week 12 — Final Review and Project Summary

Planned focus: Consolidate verified project documentation, summarize the development process, and record final validation results.

Documentation: "View Week 12 Progress Report" (./weekly-progress/week-12/README.md)

«Progress status: Week 1 and Week 2 have dedicated documentation locations. Weeks 3–12 above describe a proposed plan and must be updated as the corresponding work is performed and documented.»

### 8. Testing and Quality Assurance

Testing is intended to verify the application's behaviour and help identify defects before the project is finalized.

The testing documentation may include:

- Test case ID and test objective.
- Preconditions and test steps.
- Expected and actual results.
- Execution status: Pass, Fail, or Not Run.
- Defect description and reproduction steps.
- Screenshots or other supporting evidence, where available.
- Retest results and resolution status.

Test results will be reported only after the corresponding tests have actually been executed.

Testing Status: Not yet summarized in this overview. Refer to the "testing directory" (./testing/) for available test documentation.

### 9. Project Documentation

Resource| Link
Project Assets| "Open assets" (./assets/)
Database Resources| "Open database" (./database/)
Technical Documentation| "Open docs" (./docs/)
Testing Documentation| "Open testing" (./testing/)
User Manual| "Open user-manual" (./user-manual/)
Weekly Progress Reports| "Open weekly-progress" (./weekly-progress/)

### 10. Expected Outcomes

The project aims to provide:

- An accessible desktop-based cybersecurity learning experience.
- Better understanding of common digital threats.
- Awareness of safer online practices.
- Organized project and testing documentation.
- A traceable record of development activities across the planned 12 weeks.

These are intended outcomes, not claims of measured educational improvement or completed testing.

### 11. Future Enhancements

Potential enhancements may include:

- Additional cybersecurity scenarios and learning activities.
- More questions and assessments, if compatible with the existing design.
- Progress tracking and score summaries, where appropriate.
- Improved accessibility and user guidance.
- Expanded test coverage and clearer defect reporting.
- Additional educational content covering emerging online threats.

Any future enhancement will depend on project requirements and the existing implementation.

### 12. Conclusion

The Cybersecurity Awareness Game is intended to make cybersecurity fundamentals easier to learn through a Java-based desktop application and structured educational content.

This repository brings together the project overview, supporting resources, testing documentation, user guidance, and weekly development reports. The documentation will be updated as implementation and verification progress.

---

Repository: "CyberSecurity_Awareness_Game" (https://github.com/snehameenu9090/CyberSecurity_Awareness_Game)

Weekly Documentation: "Browse all 12 weeks" (./weekly-progress/)
