# Cybersecurity Awareness Game

A Java Swing desktop project for cybersecurity awareness training.

## 1. Start from the frontend

The UI is the first layer users interact with. It includes the home screen, lessons, quizzes, games, and theme.

```text
src/main/java/com/cyberaware/frontend/
├── CyberAwareUI.java
├── AwarenessModule.java
├── QuizModule.java
├── GamesModule.java
├── ArcadeGamePanel.java
├── MalwareCleanupGame.java
├── PassphraseBuilderGame.java
├── PhishingSortGame.java
├── MiniGamePanel.java
├── UiText.java
├── UiTheme.java
└── ...
```

This layer decides what the user sees first and how the gameplay flow works.

## 2. Then the backend

Once the frontend triggers an action, the backend handles session logic, scoring, content data, and player state.

```text
src/main/java/com/cyberaware/backend/
├── CyberAwareApp.java
├── PlayerDatabase.java
├── PlayerProfile.java
├── AwarenessTopic.java
├── AwarenessContent.java
├── QuizQuestion.java
├── QuizQuestionBank.java
├── QuizCategory.java
├── StudyLanguage.java
├── GameEntity.java
└── ...
```

This layer does the real application work after the user interacts with the UI.

## 3. Database layer last

The database layer stores persistent player data and session details after the app logic runs.

```text
database/
├── schema.sql

data/
├── cyberaware.db
```

This is the storage layer used by the backend. The frontend does not directly manage the database.

## Project structure in sequential order

```text
CyberSecurity_Awareness_Game/
├── src/main/java/com/cyberaware/frontend/   # UI and user interaction
├── src/main/java/com/cyberaware/backend/    # Logic, content, score, session handling
├── database/                                # Schema reference
├── data/                                    # SQLite database file
├── user-manual/                             # Setup and user guide
├── assets/                                  # Project assets
├── swing_and_applet_programs/               # Extra Java Swing/Applet examples
├── .vscode/                                 # IDE settings
├── .gitignore
├── pom.xml
├── run.bat
├── run.ps1
├── README.md
└── target/                                  # Compiled output
```

## Execution flow

1. User opens the app from the frontend.
2. Frontend calls backend logic.
3. Backend reads or updates player and score data.
4. Database stores the final player information.

## Run the project

### Windows

```powershell
./run.bat
```

### PowerShell

```powershell
./run.ps1
```

### Maven

```powershell
mvn package
```

## Requirements

- JDK 17 or newer
- Windows desktop environment for the launcher
- Java Swing support

## Notes

- The frontend comes first because the user starts from the UI.
- The backend follows because it processes actions and logic.
- The database is the last layer because it saves data after the application flow is executed.
- The app is designed for learning and awareness training, not for real device protection.
