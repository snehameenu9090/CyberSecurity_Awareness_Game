# Week 01 — Requirements and Scope Baseline

> Requirements below are derived from the inspected source and project configuration. Source presence does not prove runtime behavior; verification status is recorded separately.

## Functional requirements

| ID | Requirement | Source evidence |
|---|---|---|
| FR-01 | The application provides a Swing desktop welcome screen and navigation into learning activities. | [`CyberAwareUI.java`](../../../src/main/java/com/cyberaware/frontend/CyberAwareUI.java) |
| FR-02 | A learner can choose English, Hindi, Tamil, or Telugu from the selectable study languages. | [`StudyLanguage.java`](../../../src/main/java/com/cyberaware/backend/StudyLanguage.java) |
| FR-03 | The application presents guidance, examples, and checklists for phishing/scams, passwords, MFA, updates/devices, public Wi-Fi, and backups/recovery. | [`AwarenessContent.java`](../../../src/main/java/com/cyberaware/backend/AwarenessContent.java), [`AwarenessModule.java`](../../../src/main/java/com/cyberaware/frontend/AwarenessModule.java) |
| FR-04 | The learner can access quiz categories for passwords, phishing, public Wi-Fi, malware, and AI safety. | [`QuizCategory.java`](../../../src/main/java/com/cyberaware/backend/QuizCategory.java), [`QuizModule.java`](../../../src/main/java/com/cyberaware/frontend/QuizModule.java) |
| FR-05 | The application includes Cyber Shield Defender, Phishing Inbox, Malware Cleanup Crew, and Passphrase Builder activities. | [`GamesModule.java`](../../../src/main/java/com/cyberaware/frontend/GamesModule.java) |
| FR-06 | The application rejects a blank player name when starting a session. | [`CyberAwareApp.java`](../../../src/main/java/com/cyberaware/backend/CyberAwareApp.java) |
| FR-07 | The application stores or reopens local player profiles, study language, and points using SQLite when available. | [`PlayerDatabase.java`](../../../src/main/java/com/cyberaware/backend/PlayerDatabase.java) |
| FR-08 | If SQLite initialization fails, the player database implementation provides in-memory session state. | [`PlayerDatabase.java`](../../../src/main/java/com/cyberaware/backend/PlayerDatabase.java) |
| FR-09 | The application prevents negative points and rejects score arithmetic overflow. | [`CyberAwareApp.java`](../../../src/main/java/com/cyberaware/backend/CyberAwareApp.java), [`PlayerDatabase.java`](../../../src/main/java/com/cyberaware/backend/PlayerDatabase.java) |

## Non-functional requirements and constraints

| ID | Requirement / constraint | Basis |
|---|---|---|
| NFR-01 | Compile against the Java 17 language/API target. | Maven compiler configuration and the batch/PowerShell launcher `javac --release 17` argument. |
| NFR-02 | Provide a graphical desktop environment for the Swing UI. | The main UI is a `JFrame`; graphical interaction is required. |
| NFR-03 | Keep player storage local to the machine in the inspected flow. | The database path is `data/cyberaware.db`; no remote service is configured in the inspected startup/persistence code. |
| NFR-04 | Keep the application usable for the current session if SQLite cannot initialize. | In-memory fallback is implemented; persistence across launches is not implied by fallback mode. |
| NFR-05 | Do not use real passwords or real malware as game inputs. | The game descriptions identify simulated/demo activities; actual app safety must still be checked through user testing. |

Usability, accessibility, performance, and full translation coverage have no measured targets in the inspected project and are not asserted as verified qualities.

## In-scope module inventory

- Six awareness topics: phishing/scams, passwords, multi-factor authentication, updates/devices, public Wi-Fi, and backups/recovery.
- Five quiz topics: password, phishing, public Wi-Fi, malware, and AI safety.
- Four mini-games: Cyber Shield Defender, Phishing Inbox, Malware Cleanup Crew, and Passphrase Builder.
- Four selectable study languages: English, Hindi, Tamil, and Telugu.
- Local named player/session and score persistence, with an in-memory fallback path.

## Out of scope in the inspected implementation

- Network authentication, cloud profiles, or cross-device synchronization.
- Scanning or cleaning a real device.
- Detecting real phishing messages or interacting with real email.
- A claim that the project meets a particular accessibility, performance, or security certification.

## Verification status

These requirements are a code/configuration inventory, not a completed acceptance test. During this documentation task, the existing batch launcher returned exit code 0 after the compile step and emitted startup messages. The UI could not be confirmed through a visible window or screenshot. See [the test evidence](./test-evidence/launcher-result.md).

## Navigation

- [Week 01 report](./README.md)
- [System flow](./system-flow.md)
- [Project SRS](../../srs/SRS.md)
- [Project architecture](../../design/system-architecture.md)
