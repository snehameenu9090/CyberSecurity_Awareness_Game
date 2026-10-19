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
| Siya Gumber | 24E1ARCSF40P162 | siyagumber6@gmail.com | 9988778348 |
| Swati Jain | 24E1ARCSF30P171| 123jainswati2006@gmail.com | 8955830863 |
| Shruti Kumari | 24E1ARCSF30P159 | 27shrutisingh.11@gmail.com | 9334191503 |

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


## week 2 

### SRS PDF

You can access the [Project SRS Click](https://drive.google.com/file/d/1LixhIziyjHDVCXckHiU0Rzo9B0P1E16u/view?usp=drive_link)

---

## Architecture & Core Modules

The application follows the Model-View-Controller (MVC) architectural pattern to ensure clean separation between the graphical user interface, business logic, and data persistence layers.

**Frontend / GUI Layer:** JavaFX / Java Swing (Custom UI components and layout management)

**Core Logic / Backend:** Core Java (JDK 17+) utilizing Object-Oriented Programming (OOP) principles (Inheritance, Polymorphism, Encapsulation)

**Database & Storage:** SQLite / MySQL managed via JDBC (Java Database Connectivity)

**Build & Dependency Management:** Apache Maven / Gradle


### Functional Modules
* **Module 1: Authentication & Localization**
   **Player Initialization:** Captures the user's username upon launching the desktop app.
  **Language Selector:** Allows players to select their preferred language for localized educational content.
* **Module 2: Awareness Hub**
   Explains essential cybersecurity topics including Phishing, Strong Password Management, Social Engineering, and Malware Protection
* **Module 3: Mini-Game Arena**
     **Phishing Email Spotte:** Interactive challenge where users differentiate between legitimate and malicious emails.

     **Password Strength Runner:** Dynamic game teaching secure password creation and vulnerability identification.

     **Malware Defense Shield:** Strategy-based game illustrating firewall and antivirus principles against malicious scripts.

     **Social Engineering Challenge:** Scenario-driven decision-making game protecting against pretexting and phone-based scams.
* **Module 4: Quiz**
 Evaluates player comprehension post-gameplay using multiple-choice and scenario-based questions.
 Computes real-time scores and provides constructive feedback.
* **Module 5:Data Persistence Layer**
 Leverages JDBC to securely store player profiles, mini-game high scores, and quiz performance metrics into a local database.

---

## Database & Persistence Strategy

The application utilizes a robust persistence architecture to handle structured relational data (player profiles, game scores, and quiz evaluations) alongside localized application content and configurations.

### SQLite / MySQL (Relational Core)
- **Usage**: Stores Player Profiles, Language Preferences, Mini-Game High Scores, and Quiz Assessment Performance.
- **Structure**: Enforces strict relational schemas, foreign key constraints, and transactional ACID compliance.
- **Integration**: Connects directly via **JDBC (Java Database Connectivity)** utilizing custom Data Access Objects (DAO pattern) or Lightweight ORM mappings.
- **Keys**: Utilizes auto-incrementing integer Primary Keys (`player_id`, `score_id`, `result_id`) with foreign key constraints linking game/quiz records to player accounts.

### Local JSON / Properties Configuration (Content & Localization Engine)
- **Usage**: Stores localized UI strings, awareness module content, real-world case study examples, and question banks for the 4 mini-games and quizzes.
- **Structure**: Flexible, human-readable structured files (`.json` or `.properties`) categorized by language (e.g., `en.json`, `hi.json`).
- **Integration**: Loaded dynamically into memory at runtime based on the player's selected language preference.

---

## Non-Functional Requirements (NFRs)

* **Performance & Smooth GUI Rendering**: 
  - JavaFX/Swing UI rendering must maintain **60 FPS** during gameplay.
  - Database read/write operations (score updates, quiz submission) must execute in **under 100ms** on local storage without freezing the GUI Thread (handled via background Java Threads/`Task` API).

* **Security**:
  - Input sanitization on player names to prevent SQL Injection (SQLi) attacks by utilizing JDBC `PreparedStatement` everywhere.
  - Secure local database file access permissions to prevent direct local score tampering.

* **Fault Tolerance & Data Integrity**:
  - If database connection fails, the game gracefully falls back to session-based temporary storage so gameplay is uninterrupted, displaying a warning message rather than crashing.
  - Transaction management ensures game score insertion and quiz results complete fully or roll back cleanly.

* **Usability & Localization**:
  - Dynamic language switching without requiring application restarts.
  - Responsive Java desktop layout adaptable to common screen resolutions (e.g., 1024x768, 1920x1080).

* **Maintainability & Modular Design**:
  - High cohesion and low coupling through **Model-View-Controller (MVC)** and **DAO Design Patterns**, allowing independent updates to mini-games or UI screens.



---
### Week 3
---
### UML Design
---
* [Cybersecurity Awareness Game Class Diagram](![UML Diagram](assets/reference-images/UML.jpeg))

* [[Cybersecurity Awareness Game]![Project Screenshot](./image/Gemini_Generated_Image_4dvtt54dvtt54dvt.png)

---


## Week 4: Database Design & UI Mock-ups

### 1. Database Architecture & Data Storage Strategy

The application utilizes a hybrid storage strategy consisting of a relational **SQLite database** (`cyberaware.db`) for tracking user state and assessment scores, combined with structured **JSON content stores** for localized educational content, game scenarios, and quiz question banks.

### A. SQLite Relational Database Schema (Data Persistence Layer)

* **`players` Table**: Manages player metadata, initial login setup, and localization settings.
  * `player_id` (`INTEGER`, `PRIMARY KEY`, `AUTOINCREMENT`)
  * `player_name` (`VARCHAR(50)`, `NOT NULL`)
  * `language` (`VARCHAR(20)`, `DEFAULT 'English'`)
  * `created_at` (`TIMESTAMP`, `DEFAULT CURRENT_TIMESTAMP`)

* **`game_scores` Table**: Stores high scores and completion timestamps across all 4 mini-games.
  * `score_id` (`INTEGER`, `PRIMARY KEY`, `AUTOINCREMENT`)
  * `player_id` (`INTEGER`, `FOREIGN KEY` -> `players.player_id`)
  * `game_type` (`VARCHAR(50)`, `NOT NULL`) — *Phishing, Password, Malware, Social Engineering*
  * `score` (`INTEGER`, `NOT NULL`)
  * `played_at` (`TIMESTAMP`, `DEFAULT CURRENT_TIMESTAMP`)

* **`quiz_results` Table**: Captures post-learning assessment metrics and final scores.
  * `result_id` (`INTEGER`, `PRIMARY KEY`, `AUTOINCREMENT`)
  * `player_id` (`INTEGER`, `FOREIGN KEY` -> `players.player_id`)
  * `total_questions` (`INTEGER`, `NOT NULL`)
  * `correct_answers` (`INTEGER`, `NOT NULL`)
  * `percentage` (`FLOAT`, `NOT NULL`)
  * `taken_at` (`TIMESTAMP`, `DEFAULT CURRENT_TIMESTAMP`)

---

### B. Educational Content & Quiz Data Schema (JSON Storage)

* **`awareness_modules.json`**: Stores theoretical topics and real-world case studies categorized by language.
```json
{
  "language": "English",
  "category": "Phishing Attacks",
  "title": "Recognizing Malicious Emails",
  "description": "Learn how attackers spoof domain names and manipulate urgency.",
  "realWorldExample": "The 2020 Twitter Spear-Phishing Incident where administrative credentials were compromised."
}

---

### C. Entity-Relationship (ER) Diagram

  erDiagram
    PLAYERS ||--o{ GAME_SCORES : "records score"
    PLAYERS ||--o{ QUIZ_RESULTS : "completes assessment"

    PLAYERS {
        int player_id PK
        string player_name UK
        string language
        timestamp created_at
    }

    GAME_SCORES {
        int score_id PK
        int player_id FK
        string game_type
        int score
        timestamp played_at
    }

    QUIZ_RESULTS {
        int result_id PK
        int player_id FK
        int total_questions
        int correct_answers
        float percentage
        timestamp taken_at
    }


---

### 2. User Interface (UI) Mock-ups

#### A. Moderator Control Center (Queue, Read Article & Moderation Actions)
Features live queue monitoring, in-app full article reader view with DOMPurify XSS protection, and inline visibility controls (`PUBLISHED`, `HIDDEN`, `DRAFT`).

* [DevStream Moderator Control Center]![Moderator Dashboard](./image/image.png)
 
* [DevStream User Dashboard]![User Dashboard](./image/image%20copy.png)


---

## Week 5

- Here is the complete project directory structure for **CyberSecurity Awareness Game**:

```text
Cybersecurity-Awareness-Game/
│
├── README.md
├── pom.xml
├── run.bat
├── run.ps1
│
├── data/
│   └── cyberaware.db
│
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── cyberaware/
    │               ├── backend/
    │               │   └── CyberAwareApp.java
    │               │
    │               ├── ui/
    │               │   └── CyberAwareUI.java
    │               │
    │               ├── awareness/
    │               │   └── AwarenessModule.java
    │               │
    │               ├── games/
    │               │   └── GameModule.java
    │               │
    │               ├── quiz/
    │               │   └── QuizModule.java
    │               │
    │               └── database/
    │                   └── DatabaseManager.java
    │
    └── test/
        └── java/
```

In a modular desktop application architecture, dividing the system into specialized functional modules allows each team member to take complete ownership of their feature stack—covering GUI design (JavaFX/Swing), event-driven application logic, local JDBC database schemas, dynamic content parsing, and game state mechanics.

Below is a detailed breakdown of the **role, end-to-end responsibilities, technical challenges faced, and final accomplishments** for each team member across the platform.

---

## 1. Member 1: Home Page & Central Platform Dashboard

### Role & Scope of Work
Member 1 owned the primary landing page and central application control center. This module acts as the unified home hub where users browse, launch, and navigate seamlessly into all three core platform sections: the **Awareness Hub**, the **Mini-Game Arena**, and the **Quiz & Assessment Engine**.
* **Frontend (User Interface & State)**: Designed the main Home Dashboard layout using Java GUI components. Created quick-access cards and navigation action panels for the Awareness Hub, Games Arena (all 4 mini-games), and Quiz Section, complete with player profile summaries and live language selection UI.
- **Backend (Core Orchestration Logic)**: Implemented global routing controllers and event listeners that handle screen transitions, view switching, and state initialization when launching different modules from the home screen.
- **Data & Resource Integration**: Integrated background resource parsers and state trackers to ensure player progress, dynamic localized text, and dashboard widgets load instantly without UI blocking.

### Problems & Challenges Faced
1. **Unified Dashboard Rendering Latency**: Loading and rendering status data for the Awareness content, mini-games, and quiz modules on a single Home Dashboard screen caused initial rendering delays. Resolved by executing asynchronous background data loading.
2. **Smooth Screen Routing & State Management**: Transitioning between the Home Page, active mini-games, and quiz screens without losing active session data or accumulating memory leaks. Resolved by establishing a centralized UI Controller pattern.
3. **Dynamic Localization Across Home Modules**: Ensuring that changing the language on the Home Page instantly updated text across all three section previews (Awareness, Games, and Quiz) without requiring an application restart.
### What They Completed
- A high-performance, fully responsive **Central Home Dashboard** connecting Awareness, Games, and Quiz modules in one place.
- Optimized UI routing engine enabling single-click navigation and clean view transitions across the entire desktop application.
- Dynamic session state loader and multi-language support engine for the home interface.
---

## Week 6 

## 2. Member 2:Front Page, Player Authentication & Localization
### Role & Scope of Work
Member 2 owned the application's entry Front Page (Welcome Screen), handling player onboarding, username input validation, profile creation, and preferred language configuration (Localization engine).
* **Frontend (User Interface & State)**: Built the primary Front Page screen featuring input fields for Player Name, interactive dropdown selectors for language choice (e.g., English, Hindi), and entry action buttons to start the session.
* **Core Session Logic**: Implemented player input sanitization, unique username verification, and player profile initialization routines before granting access to the Home Dashboard.
* **Localization Engine**:Built the core dynamic internationalization (i18n) loader that binds selected language keys to UI components across the entire application.

### Problems & Challenges Faced
1. **Player Input Validation**: Preventing blank, duplicate, or special-character player names from corrupting local database records during entry.
2. **Real-time Language Switching**: Ensuring that selecting a language on the Front Page instantly re-renders all UI text labels across subsequent screens without requiring an application restart.
3. **Session Handoff to Home Page**: Smoothly passing the created player session object from the Front Page to the main Home Dashboard without data loss.

### What They Completed
*A user-friendly Front Page & Player Onboarding Portal for name input and language selection.

* Robust Localization Engine supporting multi-language dynamic UI rendering.

* Session initialization pipeline linking player profile data directly to the application controller.

---

## 3. Member 3: Awareness Hub & Content Engine

### Role & Scope of Work
Member 3 owned the educational learning modules and real-world attack case study hub
* **Frontend (UI & Layout)**: Designed rich text view panels, concept navigation sidebars, and topic selection cards for cybersecurity threat categories (Phishing, Malware, Password Security, Social Engineering).
* **Backend**: Engineered JSON parsing controllers to asynchronously load structured educational modules, case study datasets, and threat categories based on the user's selected language.
* **Database & UI Integration**:Built in-memory data structures and local file stream handlers (awareness_modules.json) to cache educational content for zero-latency reading.
### Problems & Challenges Faced
1. **Thread Freezing During Parsing**: Executing file I/O operations directly on the main UI thread caused temporary app freezes. Resolved using asynchronous background tasks.
2. **Missing Localization Keys**: Handling fallback strategies when a specific case study isn't available in a chosen regional language.


### What They Completed
* Interactive Awareness Hub Portal loaded with cybersecurity topics and historical attack case studies.

* Asynchronous JSON parser supporting dynamic language feeds
---

## Week 7 

## 4. Member 4: Mini-Game Arena

### Role & Scope of Work
Member 4 owned the core engagement hub containing all 4 interactive cybersecurity mini-games.

* **Frontend (User Interface & State)**: Created custom graphical play areas and interactive elements for Phishing Email Spotter, Password Strength Runner, Malware Defense Shield, and Social Engineering Challenge.
* **Backend (API Logic)**: Implemented real-time game loops, countdown timer threads, score multiplier algorithms, and interactive click event handlers.
* **Database & External APIs**: Managed score transaction payload generation, passing completed mini-game points and gameplay timestamps to the persistence layer for database logging (game_scores table).

### Problems & Challenges Faced
1. **Timer Thread Synchronization:**:Keeping UI timer counts accurate without causing frame drops or UI freezing.
2. **Clean Game Reset**: Ensuring game states and timers reset completely when a player chooses to replay a mini-game.
3. **Data Consistency**: Ensuring profile updates (such as username or avatar changes) instantly updated across all published articles without requiring full-database re-indexing.

### What They Completed
* Action-oriented 4 Mini-Games Arena with live scoring and real-time timer systems.

* Game-to-Database score transmission handlers.
---

## Week 8

## 5. Member 5: Quiz Engine & Data Persistence Layer
### Role & Scope of Work
Member 5 owned post-gameplay knowledge evaluation and local SQLite database persistence.
* **Frontend (User Interface & State)**: Built multiple-choice question (MCQ) display containers, option selection buttons, instant feedback indicators, and final percentage summary scorecards.
* **Backend (API Logic)**: Developed randomized question shufflers, automated option-grading algorithms, accuracy percentage calculators, and JSON question loaders (quiz_questions.json).
* **Database & Governance**: Designed and implemented the complete SQLite Database Architecture (cyberaware.db). Wrote JDBC Data Access Objects (DAO) using PreparedStatements for secure CRUD operations on players, game_scores, and quiz_results tables.

  
### Problems & Challenges Faced
1. **Database Connection Leaks**: Preventing JDBC memory leaks by enforcing auto-closing connection pools and try-with-resources blocks.
2. **Question Deduplication**: Ensuring question randomization logic never served duplicate questions during a single quiz attempt.
3. **Real-Time UI State Sync**: Ensuring that when content is flagged or hidden by a moderator, the change immediately reflects on the live public feeds without leaving broken or dead links for readers.

### What They Completed
* Comprehensive Quiz & Assessment Engine with automated evaluation.

* Production-ready SQLite Database Schemas & JDBC Persistence Handler
---


---

## Summary of Module Distribution

| Module | Team Member Scope | Primary Focus | Key Deliverable |
| :--- | :--- | :--- | :--- |
| **1. Home Page & Dashboard** | End-to-End | Section Launchers, Dynamic Status Feeds, View Switching | Unified Navigation Hub, Live Status Bar |
| **2. Front Page & Localization** | End-to-End | Player Registration, Input Validation, i18n Engine | Welcome Portal, Multi-Language Engine |
| **3. Awareness Hub & Content** | End-to-End | Theoretical Topics, Case Studies, JSON Parsing | Learning Portal, Dynamic Content Loader |
| **4. Mini-Game Arena** | End-to-End | Gameplay Loops, Timer Threads, Score Mechanics | 4 Mini-Games Suite, Score Logger |
| **5. Quiz Engine & Database** | End-to-End | MCQ Randomizer, Automated Grading, JDBC Persistence | Assessment Engine, SQLite Schema & DAO |
---

## Week 9 

When moving from individual module development to **System Integration & End-to-End (E2E) Testing**, the focus shifts from isolated unit testing to verifying **cross-module data flows, inter-service contracts, security enforcement, performance SLAs, and system resilience**.

In this phase, each team member takes on an **Integration & Testing Lead role** for their respective domain, working together to ensure all five modules function as a unified, production-ready system.

---


## **System Integration & End-to-End (E2E) Testing**

When moving from individual module development to **System Integration & End-to-End (E2E) Testing**, the focus shifts from isolated unit testing to verifying **cross-module data flows, inter-service contracts, security enforcement, performance SLAs, and system resilience**. 

In this phase, each team member takes on an **Integration & Testing Lead role** for their respective domain, working together to ensure all five modules function as a unified, production-ready system.

---

### **A. Domain-Specific Integration & Testing Lead Breakdown**

#### **Member 1: System Integration Lead – Central Navigation & State Flow**

* **Primary Scope**: E2E User Journey & Screen State Synchronization.
  
* **Integration Focus**: Verifies the seamless handoff between the Home Dashboard, Awareness Hub, Mini-Game Arena, and Quiz Section.

* **Key Testing Responsibilities**:
  * Conducts E2E navigation testing to ensure zero memory leaks during rapid switching between modules.
  * Validates that player profile metadata and session headers update across all views in real-time.
  * Verifies non-blocking UI rendering and background thread execution during heavy view transitions.

#### **Member 2: Security & Session Integration Lead – Authentication & Localization**

* **Primary Scope**: Player Onboarding, Input Security, & Dynamic i18n
  
* **Integration Focus**: Validates the contract between the Front Page (Welcome Screen) and downstream modules.
  
* **Key Testing Responsibilities**:
  * Executes input sanitization and boundary testing to prevent SQL injection or corrupted player records.
  * Conducts dynamic localization tests to verify that changing language keys on the Front Page propagates correctly across all awareness text, quiz banks, and mini-game UIs.
  * Tests edge cases like empty username fields, duplicated player profiles, and abnormal app closures during registration.

#### **Member 3: Data Pipeline Lead – Educational Content & Case Studies**

* **Primary Scope**: Asynchronous Content Parsing & Resource Management.
  
* **Integration Focus**: Ensures dynamic JSON resource streams load seamlessly across localized feeds.
  
* **Key Testing Responsibilities**:
  * Performs schema validation on `awareness_modules.json` to prevent null-pointer exceptions during parsing.
  * Verifies asynchronous file loading under simulated low-memory conditions to guarantee zero main-thread UI freezing.
  * Conducts fallback testing to ensure default English strings render if regional language keys are missing.

#### **Member 4: Runtime Performance Lead – Mini-Game Arena Engine**

* **Primary Scope**: Gameplay Loops, Multi-threading, & Live Score Contracts
  
* **Integration Focus**: Verifies real-time event loops and payload delivery to the database manager.
  
* **Key Testing Responsibilities**:
  * Conducts thread-synchronization tests on countdown timers across all 4 mini-games (*Phishing Spotter*, *Password Runner*, *Malware Shield*, *Social Engineering*).
  * Validates score calculation accuracy, timer reset routines, and clean thread termination upon game completion.
  * Executes stress tests on high-frequency UI repaints to ensure frame rate stability.

#### **Member 5: Persistence & Governance Lead – Quiz Engine & Database Integration**

* **Primary Scope**: Database ACID Compliance, Prepared Statements, & Automated Assessment
  
* **Integration Focus**: Ensures integrity across player stats, mini-game score logs, and quiz results.
  
* **Key Testing Responsibilities**:
  * Conducts transactional integrity testing on the SQLite database (`cyberaware.db`) using JDBC PreparedStatements.
  * Validates quiz assessment algorithms (random option shuffling, percentage calculations, deduplication).
  * Performs stress testing on database connection pools to prevent memory/resource leaks using `try-with-resources`.

---

### **B. Cross-Module Integration Verification Matrix**

| Integration Pipeline | Source Module | Destination Module | Verification & Performance SLA |
| :--- | :--- | :--- | :--- |
| **Session Handshake** | **Module 2** (Front Page) | **Module 1** (Home Page) | Player profile & selected language payload passed in **< 100ms**. |
| **Localized Content Feed** | **Module 2** (Localization) | **Module 3** (Awareness Hub) | Correct language JSON parsed and cached in-memory without UI thread freeze. |
| **Game Score Dispatch** | **Module 4** (Game Arena) | **Module 5** (SQLite Database) | Final points & timestamps saved via JDBC with zero data tampering (**< 150ms** write speed). |
| **Assessment Verification** | **Module 3** (Awareness) | **Module 5** (Quiz Engine) | Questions correctly mapped to threat categories with **100% deduplication**. |


---

## Overall System Integration Summary 

During the final Full System Acceptance Testing, all 5 team members executed unified end-to-end user journeys:



```
[Player Enters Name & Language (Mem 2)] ──> [Session Handshake to Home Dashboard (Mem 1)]
                                                      │
                                                      ▼
[Completes Quiz / Mini-Games (Mem 4)] <─── [Explores Awareness Hub (Mem 3)]
                                                      │
                                                      ▼
[Scores & Results Saved (Mem 5)] <──────── [Local SQLite Persistence & Automated Grading]

```

By dividing integration testing by architectural responsibility, the team successfully transformed five independently built modules into a secure, high-performance, and resilient desktop cybersecurity learning platform.
---

## Week 10: Final System Evaluation, Deliverables & Project Submission

During **Week 10**, the DevStream engineering team completed the final system evaluation, production deployment audit, comprehensive technical documentation, project presentation deck (PPT), and end-to-end video demonstration.

---

### Project Deliverables & Artifacts

| Deliverable | Description | Resource Link |
| :--- | :--- | :--- |
| 📄 **Final Project Report** | Complete Technical Architecture Document, System Requirements Specification (SRS), and Evaluation Metrics | [View Project Report (PDF)](https://drive.google.com/file/d/1pFmduew7up6oNzZ0_X2YcEbCZNrLNfrp/view?usp=drive_link) \| [`ARCHITECTURE.md`](./ARCHITECTURE.md) |
| 📊 **Presentation Deck (PPT)** | 20-Slide B.Tech Final Project Evaluation Deck covering System Design, Polyglot DB, AI Microservice & Testing | [View Presentation (PPT / Slides)](https://drive.google.com/file/d/1vyoF--WzF5YZf_zW_lc82_3CWRGLDBO1/view?usp=drive_link) |
| 🎥 **Video Demonstration** | HD Video Walkthrough of DevStream Platform (Author Workflow, AI Summarizer, Live GitHub Portfolio & Moderator Panel) | [Watch Video Demonstration (YouTube)](https://www.youtube.com/watch?v=DevStream_Demo_Week10) \| [Drive Video Link](https://drive.google.com/file/d/1C6V8o4NL6FSTMm4dw5IOK83lI_o6LncL/view?usp=drive_link) |

---

### Key Accomplishments & Week 10 Milestones

#### 1. Final Project Report & Technical Documentation

- Compiled the comprehensive **Software Requirements Specification (SRS)** and **Technical Architecture Document** covering:
  * Modular **Java Desktop Architecture** utilizing Swing/JavaFX components and encapsulated multi-thread controllers.
  * **Local Polyglot Storage Strategy** (SQLite relational engine for player data & mini-game scores + structured JSON storage for educational awareness modules and quiz banks).
  * **Session-Based Onboarding & Localization Engine** supporting dynamic multi-language preferences across all active screens.
  * **Input Sanitization & Data Integrity** ensuring zero-latency asynchronous resource loading and secure JDBC PreparedStatement transactions.

  - View our [Final Project Report](https://drive.google.com/file/d/1pFmduew7up6oNzZ0_X2YcEbCZNrLNfrp/view?usp=drive_link).
    
#### 2. Final Evaluation Slide Deck (PPT)

- Prepared a structured 20-slide presentation deck covering:
  * **Problem Statement & Scope:** Addressing the gap in traditional learning methods by providing an interactive, gamified desktop application for cybersecurity awareness.
  * **Architecture & Local Storage Topology:** Visualizing the 5-member modular Java desktop architecture, SQLite relational engine (`cyberaware.db`), and localized JSON content stores.
  * **Core Module Workflows:** Asynchronous JSON parsing for the Awareness Hub, real-time timer loops for the 4 Mini-Games, and automated grading for the Quiz Engine.
  * **Performance & Data Optimization:** Local JDBC PreparedStatements, zero-latency in-memory content caching, and efficient background thread management ($T_{\text{load}} < 100\text{ ms}$).
  * **Quality Assurance & Testing:** Cross-module E2E integration test results, thread synchronization verification, and local data integrity checks.

  - View our [PPT of Project Report](https://drive.google.com/file/d/1vyoF--WzF5YZf_zW_lc82_3CWRGLDBO1/view?usp=drive_link).

#### 3. End-to-End Product Video Demonstration
- Produced an HD video demonstration showcasing:
  * **User Onboarding & Front Page:** Player registration, input validation, and dynamic multi-language (i18n) selection.
  * **Home Page & Platform Navigation:** Seamless routing across the Awareness Hub, Mini-Game Arena, and Quiz Engine from the central control center.
  * **Awareness Hub & Content Discovery:** Real-time browsing and reading of cybersecurity threat topics and historical attack case studies parsed from localized JSON datasets.
  * **Interactive Mini-Game Arena:** Live gameplay loops, timer mechanics, and real-time score tracking across all 4 mini-games (*Phishing Email Spotter*, *Password Strength Runner*, *Malware Defense Shield*, and *Social Engineering Challenge*).
  * **Quiz Engine & SQLite Persistence:** Automated MCQ evaluation, instant percentage feedback calculation, and secure local JDBC score logging into `cyberaware.db`.

  - View our [Final Video](https://drive.google.com/file/d/1C6V8o4NL6FSTMm4dw5IOK83lI_o6LncL/view?usp=drive_link).
    
#### 4. Packaging & Cross-Platform Deployment Audit
- Finalized standalone execution scripts (`run.bat` / `run.ps1`) and executable JAR packaging for single-command environment provisioning.
- Completed cross-platform validation (Windows/Linux/macOS) ensuring Java desktop GUI, local SQLite runtime database (`cyberaware.db`), and JSON content stores launch seamlessly without external dependencies.
  
---

### **Week 11: System Integration, Cross-Module Wiring & E2E Testing**

* **Cross-Module Data Flow**: Successfully linked the **Front Page (Member 2)** onboarding data (Player Name & Language) to the **Home Dashboard (Member 1)** session state.
  
* **Content & Game Routing**: Integrated the **Awareness Hub (Member 3)** JSON reader and **Mini-Game Arena (Member 4)** state launcher into the central Home UI.
  
* **Database & Quiz Pipeline**: Connected the **Quiz Engine (Member 5)** grading system and mini-game score dispatch handlers to the local SQLite database (`cyberaware.db`) via JDBC PreparedStatements.
  
* **E2E Integration Testing**: Executed complete end-to-end user journeys (Registration ➔ Home Screen ➔ Awareness Module ➔ Mini-Games ➔ Quiz Assessment ➔ Score Persistence) to verify thread safety and eliminate memory leaks.

---

### **Week 12: Final UI Polish, Packaging, Documentation & Project Release**
* **UI/UX Refinement**: Added final visual polishes, button response states, dynamic language re-rendering fixes, and clear high-score dashboard widgets.
  
* **Standalone Packaging**: Compiled the Java application into an executable JAR file and created one-click execution scripts (`run.bat` for Windows and `run.ps1` for PowerShell) for automated environment provisioning.
  
* **Deployment Validation**: Completed cross-platform validation (Windows/Linux/macOS) ensuring the Java GUI runtime, local SQLite database file, and JSON content stores execute smoothly without external dependencies.
  
* **Final Deliverables**: Finalized the **Software Requirements Specification (SRS)**, Technical Architecture Document, Evaluation Slide Deck (PPT), Product Video Demonstration, and completed the GitHub repository documentation.
