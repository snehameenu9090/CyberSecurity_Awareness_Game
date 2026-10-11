# Week 1 Execution Evidence

## Environment and method

- Repository launcher: [`run.bat`](../../../../run.bat)
- Application entry point: `com.cyberaware.backend.CyberAwareApp`
- Build target: Java 17 (`javac --release 17` in the launcher)
- Result file: [launcher-output.txt](./launcher-output.txt)

## Observed result

The launcher printed `Starting CYBERSECURITY AWARENESS GAME...` after its compile step and completed with exit code 0. The batch file exits on a compiler error before printing that start message, so this run has no reported compile failure.

The runtime output contained:

- An SLF4J warning that no `StaticLoggerBinder` was found; it selected the no-operation logger.
- A Java restricted native-access warning associated with `org.sqlite.SQLiteJDBCLoader`.

## Limits and unverified checks

The screenshot capture looked for a window titled `CYBERSECURITY AWARENESS GAME` and reported exactly: `The running application window was not found.` No screenshot was created. The launcher process itself returned 0, but a visible window could not be confirmed in this environment; therefore UI launch, navigation, lessons, quizzes, mini-games, and player persistence are not recorded as passing tests.

No automated test suite was run. Database state changes, player data, game scoring, and language selection were not tested. This evidence records only the launcher output and exit status.
