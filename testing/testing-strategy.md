# Testing Strategy

## Purpose

This strategy outlines checks for the Java Swing application, its local SQLite persistence, and its education flows. It is a proposed validation plan, not a record that tests have already been executed.

## Test levels

| Level | Focus |
|---|---|
| Build check | Compile with Java 17 through Maven and the Windows launcher |
| Backend checks | Validate player-name handling, normalized lookup, language updates, score bounds, and database fallback |
| UI checks | Launch the Swing app and navigate lessons, games, language selection, and quizzes |
| Persistence checks | Confirm new/reopened player behavior and score/language persistence across launches |
| Documentation checks | Validate repository-relative links and supplied screenshot references |

## Test environment

Use JDK 17 or newer, Maven or a supported Windows shell, and a graphical desktop session for UI tests. Use a disposable database path for persistence checks so that existing local player files remain untouched.

## Test data and safety

- Do not commit real player names or local `.db` files.
- Use non-sensitive test names and a disposable database.
- Do not use real passwords, accounts, phishing URLs, or live malware.
- Distinguish a test not run from a test failure.

## Entry and exit criteria

Begin UI checks after a successful compile and with a graphical environment available. A test is complete only when its actual result, environment, and pass/fail status are recorded in the [test report](./test-report.md).

## Current status

No tests were run during this documentation-only change. See [test cases](./test-cases.md) for planned checks.
