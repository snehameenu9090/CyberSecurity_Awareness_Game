# Week 02 — Application Structure and Evidence Review

> **Historical evidence limitation:** The repository does not contain dated Week 2 daily notes or Week 2 Java source commits. The seven sections below are an **estimated reconstruction** of the work that can be supported by the available project snapshot, Git history, documentation, and the verification performed for this report. They are not claims that a code change occurred on a specific day. All existing application features described here were present in the source snapshot; no new Week 2 Java feature or source modification is verified. The current GitHub `main` tree does not contain the Java source or build/launcher files, so their paths below are plain text rather than links; they refer to the locally inspected project snapshot.

## 1. Week 2 Introduction and Objective

Week 2's evidence-based objective is to map and verify the application's existing structure after the Week 1 baseline: trace startup into the Swing user interface, explain how the screen modules obtain lesson/quiz/game content, and document how the player/session coordinator reaches local storage. Understanding these boundaries is important before changing or testing the cybersecurity learning experience: it helps keep user-interface behavior, educational rules, and persistence responsibilities in the appropriate components.

This report documents the current implementation and a real compile/start and welcome-screen check. It does not claim that login, registration, a new dashboard, or another feature was newly implemented during Week 2.

### Connection to Week 1

Week 1 established the learning purpose and scope, Java/Swing technology baseline, awareness topics, quiz categories, simulated games, and local player progress. Week 2 continues from that baseline by describing how those existing pieces connect. See the [Week 1 report](../week-01/README.md), [requirements and scope](../week-01/requirements.md), and [system flow](../week-01/system-flow.md).

### Planned work compared with verified implementation

| Area | Intended Week 2 review | What the evidence supports |
|---|---|---|
| Architecture | Map the UI, application/session coordinator, content, games, and persistence. | These components exist in the source snapshot and are described below. |
| Learner flow | Trace launch, welcome, session start, and navigation. | Source shows the flow; a launcher run and visible welcome-screen render were verified. Dashboard navigation was not exercised. |
| Lessons, quizzes, and games | Explain the existing educational modules. | Their source and relationships were inspected. Runtime interactions were not tested during this review. |
| Player persistence | Identify local SQLite storage and fallback behavior. | Both paths are present in source; neither player writes nor fallback behavior was tested. |
| New application feature | Record only code changes supported as Week 2 work. | No Week 2 source-code change is supported by the available Git history/diff. |
| Documentation/evidence | Produce an accurate report and preserve actual execution evidence. | This report and the linked genuine Welcome-screen capture document the architecture review and verification performed for this submission. |

### Acceptance criteria for this report

- Identify the relevant current Java classes, methods, and component boundaries.
- Distinguish source presence from a tested runtime result and from historical development attribution.
- Include seven non-empty daily sections, with the allocation clearly labeled as estimated.
- Report the actual launcher outcome, warnings, screenshot, and functional-test gaps.
- Preserve Java files, Week 1, and other weekly reports.

The source review, launch check, screenshot, and documentation criteria are met. Functional acceptance of navigation, lessons, quizzes, games, and persistence remains incomplete.

### Technology stack verified in project files

| Area | Existing configuration |
|---|---|
| Language | Java |
| Compiler target | Java 17 (`javac --release 17` in `run.bat`; Maven source/target 17 in `pom.xml`) |
| Desktop UI | Swing (`CyberAwareUI` extends `JFrame`) |
| Local persistence | SQLite through `sqlite-jdbc` 3.46.1.0 |
| Logging API | SLF4J API 1.7.36 |
| Build and launch | `pom.xml`, `run.bat`, and `run.ps1` |

## 2. Week 2 Daily Progress

**Day allocation:** These headings are an estimated reconstruction for review, not verified dates. Git history does not support seven dated development events. The source review and launcher/screenshot checks described in this report are verified activities for preparing this submission; they are not assigned as historical code-development events. For each day, "Completed" means the corresponding evidence review is documented, not that a new Java feature was completed.

### Day 1 — Actual Work Completed

**Objective:** Re-establish the Week 1 scope and identify the project baseline from which to assess possible Week 2 changes.

**Work performed:** Reviewed the existing Week 1 report, requirements, and system-flow documentation against the available source snapshot. The Week 1 objective and scope already include a local Swing learning game, awareness topics, quizzes, simulated activities, and local player progress.

**Implementation:** Compared the documented requirements with the project tree and available Git refs. The reviewed source snapshot is commit `9673127` (`Organize cybersecurity awareness game project`, 2026-10-11 16:22 +0530); the Week 1 documentation/template commits occur later that day. There is no dated daily diary establishing Day 1. The current untracked source files were compared by Git blob hash with the `local-complete-project-backup` source snapshot: all 32 inspected build/source/demo paths matched that snapshot exactly.

**Code references:** [Week 1 requirements](../week-01/requirements.md); [Week 1 system flow](../week-01/system-flow.md); `src/main/java/com/cyberaware/backend/CyberAwareApp.java`; `src/main/java/com/cyberaware/frontend/CyberAwareUI.java`.

**Code explanation:** `CyberAwareApp` coordinates player/session state and starts the UI. `CyberAwareUI` is the Swing frame that composes the welcome and dashboard screens. Their current existence is supported by the source snapshot; no method is presented as newly written on Day 1.

**Feature outcome:** The baseline is distinguished from possible later development. The source snapshot already contains the learner entry flow and existing awareness/game/quiz modules; no new Week 2 feature is verified.

**Output:** No day-specific console output or screenshot exists. The current-run output and genuine screen capture are documented on Day 7 and in [test evidence](./test-evidence/).

**Testing:** Scenario: compare the Week 1 scope to source and history. Input: existing documentation, Git refs, and current Java tree. Expected: identify features present at baseline and avoid unsupported change attribution. Actual: matching source files were found in the earlier source snapshot; there is no dated daily log. Result: **PASS for the bounded source/history comparison**; not an application behavior test.

**Problems and solutions:** The main branch does not track the current Java tree, and the source files appear untracked in the working tree. Hash comparison against the available backup source snapshot found no differing blobs among the inspected paths. No source was restored, deleted, or overwritten.

**Completion status:** Completed for baseline review; historical day assignment remains estimated.

**Next step:** Trace the launcher and Java entry point into the Swing welcome screen.

### Day 2 — Actual Work Completed

**Objective:** Explain how the existing project is compiled and how the Java application reaches its initial screen.

**Work performed:** Inspected the Java 17 Maven/launcher configuration, `CyberAwareApp.main()`, and the welcome-screen construction in `CyberAwareUI`. A real launcher execution for this submission is recorded under Day 7; Git does not date it as historical Day 2 development.

**Implementation:** Followed the `run.bat` compile command to the `CyberAwareApp` main class, then traced the Swing event-dispatch startup to app/database construction and `CyberAwareUI` creation.

**Code references:** `pom.xml`; `run.bat`; `src/main/java/com/cyberaware/backend/CyberAwareApp.java` (`main()` and constructor); `src/main/java/com/cyberaware/frontend/CyberAwareUI.java` (constructor and `showWelcome()`); `src/main/java/com/cyberaware/frontend/UiTheme.java`.

**Code explanation:** The launcher compiles with `javac --release 17` and then invokes `com.cyberaware.backend.CyberAwareApp`. The main method schedules initialization with `SwingUtilities.invokeLater`, installs the UI theme, creates the app and frame, and makes the frame visible. The welcome screen includes a player-name field, selectable language, and start action.

**Feature outcome:** The project has an identifiable and reproducible desktop startup path. These capabilities are part of the existing source; the report does not claim they were newly implemented on Day 2.

**Output:** The actual startup output is saved in [`launcher-output.txt`](./test-evidence/launcher-output.txt). The visible welcome screen is [welcome-screen.png](./screenshots/welcome-screen.png).

**Testing:**
- Scenario: run the existing Windows launcher.
- Input: no player input; execute `run.bat`.
- Expected: compile against the Java 17 API target and reach the Java startup command.
- Actual: `Starting CYBERSECURITY AWARENESS GAME...` printed and the launcher exited with code 0. SLF4J no-binding and SQLite native-access warnings were emitted.
- Result: **PASS for compile/start command only**. This is a current verification run, not a dated historical Day 2 result.

**Problems and solutions:** No compile error occurred in the recorded run. The logging/native-access warnings were recorded rather than changed; they did not prevent the launcher from returning exit code 0.

**Completion status:** Completed for entry-point/source review; screen interactions remain untested; historical allocation estimated.

**Next step:** Trace how the welcome screen starts a named player session and how dashboard screens are selected.

### Day 3 — Actual Work Completed

**Objective:** Document the current application/session coordinator and the validation on the learner entry flow.

**Work performed:** Inspected how app startup loads a recent player, how `startSession(String)` handles names, and how the welcome-screen button delegates to the app. This is a current code review; no dated code change is associated with Day 3.

**Implementation:** Followed `CyberAwareApp` construction to `PlayerDatabase`, then followed the welcome button listener through `app.startSession(name.getText())`. Reviewed its null/blank guard and the UI's validation message path.

**Code references:** `src/main/java/com/cyberaware/backend/CyberAwareApp.java` (`CyberAwareApp()`, `startSession(String)`, `restorePlayer(...)`); `src/main/java/com/cyberaware/frontend/CyberAwareUI.java` (`showWelcome()`); `src/main/java/com/cyberaware/backend/PlayerProfile.java`.

**Code explanation:** `CyberAwareApp` initializes `PlayerDatabase` and loads a most-recent profile if available. `startSession` returns `false` for a null or blank name; otherwise it opens a profile and restores the player ID, display name, score, and language. The UI shows an informational dialog for a blank name and moves to the dashboard only when the session starts successfully.

**Feature outcome:** The current code prevents a blank player name from opening a session and connects a valid session to dashboard navigation. The behavior was inspected in code, not interactively tested in this submission.

**Output:** No blank-name dialog output was captured. The screenshot shows the initial form and does not show a submitted name or session.

**Testing:**
- Scenario: submit an empty player name.
- Input: empty string.
- Expected: `startSession` returns `false`, and the welcome UI reports that a name is required.
- Actual: not exercised through the UI or a direct test.
- Result: **NOT RUN**. The guard and message path are verified by source inspection only.

**Problems and solutions:** No input-validation defect or fix is evidenced. The guard is existing implementation and is not recorded as a Week 2 change.

**Completion status:** Partially completed; source path documented, runtime validation remains untested; historical allocation estimated.

**Next step:** Map dashboard construction and its lesson, game, and quiz screen components.

### Day 4 — Actual Work Completed

**Objective:** Explain the existing Swing dashboard and navigation/component boundary.

**Work performed:** Inspected `CyberAwareUI`'s dashboard construction and the module objects it owns. The dashboard registers Home, Awareness, Game Arena, and Quiz cards. This source relationship is verifiable; a dated navigation implementation is not.

**Implementation:** Traced `showDashboard()` to each module's `build()` method and traced destination selection through `showDashboardAndSelect(...)` and the `CardLayout`.

**Code references:** `src/main/java/com/cyberaware/frontend/CyberAwareUI.java` (`showDashboard()`, `showDashboardAndSelect(...)`); `src/main/java/com/cyberaware/frontend/AwarenessModule.java`; `src/main/java/com/cyberaware/frontend/GamesModule.java`; `src/main/java/com/cyberaware/frontend/QuizModule.java`.

```java
screenCards.add(buildHome(), "home");
screenCards.add(awarenessModule.build(), "awareness");
screenCards.add(gamesModule.build(), "games");
screenCards.add(quizModule.build(), "quiz");
```

**Code explanation:** Each screen module returns a Swing `JPanel`. The frame registers panels under named cards and uses `CardLayout` to display the selected screen. This keeps top-level routing in `CyberAwareUI` and the details of lessons, games, and quizzes in their respective module classes.

**Feature outcome:** The existing UI structure provides learner navigation among the main sections. Dashboard navigation itself was not verified through a live interaction.

**Output:** No dashboard screenshot was captured. [welcome-screen.png](./screenshots/welcome-screen.png) only verifies the initial screen.

**Testing:**
- Scenario: start a player and select Awareness, Game Arena, and Quiz.
- Input: valid player name and each sidebar/button destination.
- Expected: each destination card becomes visible.
- Actual: not exercised in the current run.
- Result: **NOT RUN**.

**Problems and solutions:** No navigation issue or corrective change was recorded. Runtime navigation remains a specific follow-up rather than an assumed pass.

**Completion status:** Partially completed; structure review completed, interactive navigation untested; historical allocation estimated.

**Next step:** Trace how awareness and quiz data are supplied to their presentation modules.

### Day 5 — Actual Work Completed

**Objective:** Document the current learning-content and quiz data flow from backend models into Swing views.

**Work performed:** Inspected the existing awareness topic provider, quiz category/question definitions, and their frontend modules. Source supports six awareness topics and five quiz categories; history does not establish that this content was authored or modified during Week 2.

**Implementation:** Followed `AwarenessModule.build()` to `AwarenessContent.allTopics(language)` and `QuizModule` to `QuizQuestionBank.questions(category, language)`. Reviewed how topic sections and quiz choices are rendered.

**Code references:** `src/main/java/com/cyberaware/backend/AwarenessContent.java`; `src/main/java/com/cyberaware/backend/AwarenessTopic.java`; `src/main/java/com/cyberaware/frontend/AwarenessModule.java`; `src/main/java/com/cyberaware/backend/QuizCategory.java`; `src/main/java/com/cyberaware/backend/QuizQuestion.java`; `src/main/java/com/cyberaware/backend/QuizQuestionBank.java`; `src/main/java/com/cyberaware/frontend/QuizModule.java`.

**Code explanation:** Awareness topics contain advice, an example, and a checklist and are rendered as scrollable cards. The quiz bank supplies localized questions for a category. The quiz view tracks the selected answer, requires an answer before continuing, then shows feedback; the source awards 10 points for a correct response.

**Feature outcome:** The existing source supports structured lesson reading and category-based quiz practice. This review does not assert that any lesson or quiz was newly developed in Week 2 or worked in a live interaction test.

**Output:** No lesson text or quiz result was captured at runtime. No lesson/quiz screenshot is available.

**Testing:**
- Scenario: open a lesson, submit a quiz without a selection, and answer a question.
- Input: select a topic/category; first submit with no selection, then select an option.
- Expected: lesson advice/example/checklist render; blank quiz submission prompts for an answer; submitted choice yields correctness feedback.
- Actual: not exercised.
- Result: **NOT RUN**.

**Problems and solutions:** No content-rendering or quiz issue was reproduced, so no fix is claimed. Translation layout and runtime feedback still need testing.

**Completion status:** Partially completed; source/data flow documented, runtime checks outstanding; historical allocation estimated.

**Next step:** Map the game arena and verify which scenarios are simulated rather than real device/email operations.

### Day 6 — Actual Work Completed

**Objective:** Document the existing game panel composition and the player-state/persistence boundary.

**Work performed:** Inspected `GamesModule` and the game panel classes, then traced score callbacks to `CyberAwareApp` and storage operations to `PlayerDatabase`. Also reviewed source guards for player names and points. No evidence dates implementation of these features to Week 2.

**Implementation:** Followed game construction to the injected point-award callback and language supplier; inspected SQLite initialization, the `players` schema, in-memory fallback, player lookup, language updates, and point arithmetic.

**Code references:** `src/main/java/com/cyberaware/frontend/GamesModule.java`; `src/main/java/com/cyberaware/frontend/ArcadeGamePanel.java`; `src/main/java/com/cyberaware/frontend/PhishingSortGame.java`; `src/main/java/com/cyberaware/frontend/MalwareCleanupGame.java`; `src/main/java/com/cyberaware/frontend/PassphraseBuilderGame.java`; `src/main/java/com/cyberaware/frontend/MiniGamePanel.java`; `src/main/java/com/cyberaware/backend/CyberAwareApp.java`; `src/main/java/com/cyberaware/backend/PlayerDatabase.java`; [database schema](../../../database/schema.sql).

**Code explanation:** `GamesModule` creates four game panels and passes a point callback and language supplier. The game panels handle simulated rounds; for example, `MalwareCleanupGame` checks a fixed set of pretend infected app tile indices, not a real device. `PlayerDatabase` uses SQLite at `data/cyberaware.db` when initialized and retains a session-only in-memory profile otherwise. `CyberAwareApp.addPoints` rejects negative awards and requires an active session; database score arithmetic uses overflow checking.

**Feature outcome:** The current design connects simulated learning games to the central score flow and provides local player-state storage with a documented in-memory fallback. Neither gameplay nor storage behavior was tested in the recorded run.

**Output:** No game result or database write output was captured. The Welcome screenshot does not demonstrate these features.

**Testing:**
- Scenario: play a mini-game and award points; separately test local persistence and fallback.
- Input: game controls, valid/negative point values, and a player name; database available/unavailable.
- Expected: game feedback and valid points; negative points rejected; SQLite persists a profile when available; fallback is limited to a session.
- Actual: not exercised.
- Result: **NOT RUN**.

**Problems and solutions:** No gameplay or database failure was induced, so no bug fix is claimed. The warning emitted during app startup is documented as a warning, not a failure or fix.

**Completion status:** Partially completed; code relationships documented, interactive and persistence tests pending; historical allocation estimated.

**Next step:** Review the recorded launcher evidence and consolidate the architecture map and verification gaps.

### Day 7 — Actual Work Completed

**Objective:** Perform the available build/start verification, capture genuine visible output, and summarize what Week 2 evidence supports.

**Work performed:** Ran the existing `run.bat` launcher and separately launched the compiled application from a fresh temporary working directory to capture the Swing welcome screen without exposing the existing local player profile. Inspected the available Git state, remote configuration, Week 1 documentation, and source snapshot history. No Java source was modified.

**Implementation:** The existing launcher compiled its entry point with `javac --release 17` and reached the application startup command. The isolated screenshot run used the compiled classes and runtime JARs with an empty temporary profile database; the visible application window was captured into the Week 2 screenshots folder. The temporary profile data was removed after capture.

**Code references:** `run.bat`; `src/main/java/com/cyberaware/backend/CyberAwareApp.java`; `src/main/java/com/cyberaware/frontend/CyberAwareUI.java`; [launcher output](./test-evidence/launcher-output.txt); [run details](./test-evidence/launcher-run.md); [Welcome screenshot](./screenshots/welcome-screen.png).

**Code explanation:** The launcher reports a compiler failure before printing its startup line; the observed startup line therefore indicates that the compile step did not report an error in that run. A separate visible window demonstrates that the entry point rendered the initial Swing frame. Neither result covers the dashboard or learner interactions.

**Feature outcome:** The project can compile through the configured Java 17-target launcher and render its Welcome screen in the tested environment. This is a verified build/initial-render result, not acceptance of all features.

**Output:**

```text
Starting CYBERSECURITY AWARENESS GAME...
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
WARNING: A restricted method in java.lang.System has been called
WARNING: java.lang.System::load has been called by org.sqlite.SQLiteJDBCLoader
Launcher process result: exit code 0.
```

The full captured warning text, with local absolute paths omitted, is in [launcher-output.txt](./test-evidence/launcher-output.txt). The genuine screenshot is [welcome-screen.png](./screenshots/welcome-screen.png).

**Testing:**
- Scenario: compile/start via `run.bat`, then independently render the application Welcome screen.
- Input: launcher with no learner input; second launch in a clean temporary working directory.
- Expected: successful compile/start and visible Swing welcome window.
- Actual: launcher printed `Starting CYBERSECURITY AWARENESS GAME...` and returned exit code 0; the separate run displayed the Welcome screen captured in the linked image. SLF4J no-binding and SQLite native-access warnings were emitted.
- Result: **PASS for compile/start and visible Welcome-screen rendering only.** No automated suite was run. Dashboard navigation, lesson/quiz/game interactions, player writes, and fallback behavior remain untested.

**Problems and solutions:** The initial screenshot attempt showed the browser instead of the application because the game window was behind it. The window was brought forward and the screenshot recaptured. No Java code or application behavior was changed.

**Completion status:** Completed for this evidence review and startup/render checks; overall functional acceptance remains partial. Day placement is estimated.

**Next step:** Perform focused functional tests of navigation, lessons, quiz answer handling/scoring, each game, player validation, SQLite persistence, and the SQLite fallback; record only observed outcomes.

## 3. Week 2 Feature and Change Summary

### Features verified as present in the existing source snapshot

- Swing welcome screen and dashboard navigation.
- Six awareness topics with advice, examples, and checklists.
- Five quiz categories and question/feedback flow.
- Four simulated mini-games.
- Named local player sessions, language/score fields, SQLite storage, and an in-memory fallback.

These features are present in the source snapshot; the evidence does **not** show that they were newly developed during Week 2. The current source/build files are untracked on `main`, but the 32 inspected source/build/demo paths have Git blob hashes identical to the files in the available `local-complete-project-backup` source snapshot (`9673127`). No source files were changed for this Week 2 report.

### Work completed for this submission

- Reviewed Week 1 scope, current source, Git status/history, configured remote, and available backup source snapshot.
- Compared current source/build/demo blobs against the available baseline; no mismatching file was found in the 32 inspected paths.
- Ran the existing application launcher and observed its output and exit code.
- Captured one genuine screenshot of the running Welcome screen in a clean temporary profile.
- Prepared this source-based report and recorded the limits of the available evidence.

## 4. Testing and Results

| Scenario | Input | Expected | Actual | Result |
|---|---|---|---|---|
| Existing Java launcher compile/start | Run `run.bat`; no learner input | Compile with `--release 17`, then reach app startup | Startup line printed; process exit code 0; SLF4J and SQLite native-access warnings | **PASS** for launcher compile/start only |
| Isolated initial UI rendering | Launch compiled application from a fresh temporary working directory | Welcome screen is visible without displaying an existing saved player | Window titled `CYBERSECURITY AWARENESS GAME` was visible and captured | **PASS** for initial Welcome-screen rendering |
| Blank/valid player name validation | Empty and non-empty names | Blank is rejected; non-empty opens a session | Not exercised in this review | **NOT RUN** |
| Dashboard navigation | Select Home, Awareness, Game Arena, and Quiz | Each chosen panel is shown | Not exercised | **NOT RUN** |
| Lesson/quiz interaction | Open lesson; submit blank and selected quiz answers | Lesson appears; quiz validates selection and displays feedback | Not exercised | **NOT RUN** |
| Game rounds and scoring | Play each activity using its controls | Round feedback/results and scoring appear | Not exercised | **NOT RUN** |
| SQLite persistence and fallback | Open profile, update language/points, restart; make SQLite unavailable | Stored profile restores when SQLite works; fallback is session-only | Not exercised | **NOT RUN** |

No automated test suite was run. The two PASS results above are limited to the stated launcher and initial-render checks.

## 5. Problems, Solutions, and Evidence Limits

| Finding | Evidence-based handling |
|---|---|
| No dated Week 2 daily work log or source-change commits are available. | Day headings are an estimated reconstruction; no source feature is assigned a fabricated implementation date. |
| Java source and build files are untracked on the current `main` branch. | Compared 32 current source/build/demo file blobs with the available backup source snapshot; all matched. No restore or source edit was performed. |
| First screenshot attempt captured the window behind another application. | Brought the actual Swing window forward and recaptured it; see [the screenshot](./screenshots/welcome-screen.png). |
| Startup emitted SLF4J no-binding and SQLite restricted native-access warnings. | Recorded the warnings. They did not prevent the observed compile/start result; they were not changed or represented as fixed. |
| Functional feature paths were not exercised. | Marked them **NOT RUN** and listed them as pending instead of marking source inspection as a passing test. |

## 6. Screenshots and Test Evidence

- [Welcome screen — genuine running application capture](./screenshots/welcome-screen.png)
- [Launcher output](./test-evidence/launcher-output.txt)
- [Launcher and screenshot capture method/limits](./test-evidence/launcher-run.md)

The image shows the Swing Welcome screen in a clean profile. It does not prove that dashboard navigation, lessons, quizzes, games, or persistence work. No screenshot was fabricated.

## 7. Completed, Partial, and Pending Work

**Completed for this report:** Week 1-to-current source comparison; package/component map; explanation of startup, UI composition, content, game, session, and persistence paths; actual launcher compile/start observation; genuine Welcome-screen capture; documentation of warnings and limitations.

**Partially completed:** Verification of runtime behavior. Only the launcher compile/start path and visible Welcome screen were confirmed.

**Pending:** Manual interaction tests for input validation, navigation, language selection, lesson rendering, quiz feedback/scoring, all mini-games, score persistence, and SQLite fallback. Historical day assignment and source provenance beyond the available snapshot also remain unconfirmed.

## 8. Week 3 Development Plan

**Proposed next-week objective:** Begin focused work on the existing awareness-lesson experience without assuming it needs new features before testing. First manually verify dashboard-to-awareness navigation and the six topic cards; then review lesson readability and the language-specific content paths; record actual test inputs/results and capture lesson screenshots only after viewing the running screens. If defects are found, document reproduction steps before changing code and keep any implementation in the relevant lesson module.

## Navigation

- Previous Week: [Week 01](../week-01/README.md)
- Weekly Progress Index: [All weeks](../README.md)
- Main README: [Project overview](../../../README.md)
- Next Week: [Week 03](../../../weekly-progress/week-03/README.md)
