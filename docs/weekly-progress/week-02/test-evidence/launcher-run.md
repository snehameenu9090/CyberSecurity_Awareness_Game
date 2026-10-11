# Week 2 Launcher and Screenshot Evidence

## Launcher check

- **Command:** Run the repository's existing `run.bat` from the project root.
- **Configured compile:** `javac --release 17` compiles `com.cyberaware.backend.CyberAwareApp` and source dependencies.
- **Observed result:** The launcher printed `Starting CYBERSECURITY AWARENESS GAME...` and the process returned exit code 0.
- **Warnings:** SLF4J did not find `StaticLoggerBinder` and selected its no-operation logger. SQLite JDBC emitted Java restricted native-access warnings. The warnings were recorded, not fixed.
- **Evidence:** [launcher-output.txt](./launcher-output.txt).

The launch message occurs after the compile command succeeds in the batch file. This supports that the configured compile step reported no error in this run. It does not establish that all UI or gameplay behavior passed.

## Welcome-screen capture

- **Method:** Launch the already compiled application in a fresh temporary working directory with the project class output and runtime JARs on the classpath, then capture the visible Swing window.
- **Observed window:** `CYBERSECURITY AWARENESS GAME`.
- **Observed screen:** Welcome form with the project title, introductory text, empty player-name field, English language choice, and Start button.
- **Data isolation:** The fresh working directory created its own temporary `data/cyberaware.db`; no existing local player name was shown. Temporary run data was removed after capture.
- **Image:** [welcome-screen.png](../screenshots/welcome-screen.png).

The screenshot demonstrates initial frame rendering only. It does not demonstrate a submitted player name, dashboard navigation, lesson or quiz behavior, game rounds, score writes, or persistence.

## Test scope

No automated test suite was run. Dashboard navigation, lesson rendering, quiz validation/scoring, mini-game interaction, player-name edge cases, language persistence, SQLite writes, and in-memory fallback remain untested.
