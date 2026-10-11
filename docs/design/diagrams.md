# Design Diagrams

The diagram source files use Mermaid and are editable text. GitHub renders the Mermaid blocks below when this Markdown page is viewed there. Standalone PNG exports are not included: no local Mermaid-to-PNG rendering tool or supplied diagram images were available during this organization. The blocks here provide a rendered GitHub view while keeping the corresponding `.mmd` source files editable.

## Entity-relationship diagram

Source: [`ER-Diagram.mmd`](./ER-Diagram.mmd). This diagram reflects the single `players` table in the Java-created SQLite schema.

```mermaid
erDiagram
    PLAYERS {
        INTEGER id PK
        TEXT display_name
        TEXT name_key UK
        INTEGER total_points
        TEXT language
        TEXT last_played
    }
```

## Use-case diagram

Source: [`Use-Case-Diagram.mmd`](./Use-Case-Diagram.mmd). Learner activities and local storage are based on the existing UI, README, and player persistence code.

```mermaid
flowchart LR
    Learner([Learner / Player])
    subgraph Game["Cybersecurity Awareness Game"]
        Language[Select study language]
        Session[Start or resume named player]
        Lessons[Read awareness lessons]
        Quiz[Take a topic quiz]
        Games[Play an educational mini-game]
        Feedback[Review feedback and points]
    end
    Store[(Local player store)]
    Learner --> Language
    Learner --> Session
    Learner --> Lessons
    Learner --> Quiz
    Learner --> Games
    Quiz --> Feedback
    Games --> Feedback
    Session <--> Store
    Feedback --> Store
```

## Class diagram

Source: [`Class-Diagram.mmd`](./Class-Diagram.mmd). The class names and module relations correspond to the current Java packages; the diagram is intentionally limited to documented dependencies.

```mermaid
classDiagram
    class CyberAwareUI
    class AwarenessModule
    class GamesModule
    class QuizModule
    class CyberAwareApp
    class PlayerDatabase
    class PlayerProfile
    class StudyLanguage
    class AwarenessContent
    class AwarenessTopic
    class QuizQuestionBank
    class QuizCategory
    class QuizQuestion
    class MiniGamePanel
    class ArcadeGamePanel
    class PhishingSortGame
    class MalwareCleanupGame
    class PassphraseBuilderGame
    CyberAwareUI --> CyberAwareApp : coordinates
    CyberAwareUI --> AwarenessModule : presents
    CyberAwareUI --> GamesModule : presents
    CyberAwareUI --> QuizModule : presents
    CyberAwareApp --> PlayerDatabase : persists player state
    PlayerDatabase ..> PlayerProfile : returns
    PlayerProfile --> StudyLanguage : stores
    AwarenessModule ..> AwarenessContent : reads
    AwarenessContent ..> AwarenessTopic : supplies
    QuizModule ..> QuizQuestionBank : reads
    QuizQuestionBank ..> QuizCategory : groups questions
    QuizQuestionBank ..> QuizQuestion : supplies
    GamesModule --> MiniGamePanel : routes to games
    MiniGamePanel <|-- ArcadeGamePanel
    MiniGamePanel <|-- PhishingSortGame
    MiniGamePanel <|-- MalwareCleanupGame
    MiniGamePanel <|-- PassphraseBuilderGame
```

## Activity diagram

Source: [`Activity-Diagram.mmd`](./Activity-Diagram.mmd). Startup, player-name validation, persistence fallback, and activity selection are represented from the existing entry point and application code.

```mermaid
flowchart TD
    Start([Launch application]) --> Init[Initialize player database]
    Init --> Ready{SQLite initialized?}
    Ready -- Yes --> Load[Load most recently played profile]
    Ready -- No --> Memory[Use in-memory player state]
    Load --> Choose[Choose or confirm study language]
    Memory --> Choose
    Choose --> Name[Enter player name]
    Name --> Valid{Name non-blank?}
    Valid -- No --> Name
    Valid -- Yes --> Session[Start or resume player session]
    Session --> Activity{Choose learning activity}
    Activity --> Lessons[Read awareness lessons]
    Activity --> Quiz[Answer topic quiz]
    Activity --> Game[Play a mini-game]
    Quiz --> Feedback[Show result and feedback]
    Game --> Feedback
    Lessons --> Activity
    Feedback --> Save[Update points when applicable]
    Save --> More{Continue learning?}
    More -- Yes --> Activity
    More -- No --> End([Close application])
```

## Sequence diagram

Source: [`Sequence-Diagram.mmd`](./Sequence-Diagram.mmd). This describes player opening and score persistence as implemented through `CyberAwareApp` and `PlayerDatabase`.

```mermaid
sequenceDiagram
    actor Learner
    participant UI as CyberAwareUI
    participant App as CyberAwareApp
    participant DB as PlayerDatabase
    participant SQLite as Local SQLite
    Learner->>UI: Enter player name and start
    UI->>App: startSession(name)
    App->>DB: openPlayer(name, language)
    DB->>SQLite: Find normalized name key
    alt Player exists
        SQLite-->>DB: Existing player record
        DB->>SQLite: Update display name, language, last_played
    else New player
        DB->>SQLite: Insert player record
        SQLite-->>DB: New player identifier
    end
    DB-->>App: PlayerProfile
    App-->>UI: Session ready
    UI-->>Learner: Show learning interface
    Learner->>UI: Complete activity
    UI->>App: addPoints(points)
    App->>DB: addPoints(playerId, points)
    DB->>SQLite: Read and update total_points
    SQLite-->>DB: Updated score
    DB-->>App: Updated total
    App-->>UI: Refresh score
```
