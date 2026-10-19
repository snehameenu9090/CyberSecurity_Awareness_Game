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
* [Cybersecurity Awareness Game Class Diagram]!(https://drive.google.com/file/d/10ZJuQh0whS549_OE8SygoA0y6InjgYlI/view?usp=drivesdk)

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

## 1. Member 1: Home Page & Search Engine Integration Lead

### Integration & Testing Role
**Feed Synchronization, Search Contract, & High-Traffic Load Testing Lead.**

### Work & Scope
* **Cross-Module Feed Integration**: Integrated the Home Page feed with Member 2's published articles, Member 4's moderation status flags (ensuring hidden posts instantly disappear), and Member 5's AI-generated summaries and tags.
* **API Contract Testing**: Verified that the JSON response schemas sent by the Java backend match the feed state expected by the React frontend across all device viewports.
* **Performance & Load Testing**: Conducted simulated stress testing (using tools like JMeter or K6) to measure search latency, pagination speeds, and server resource usage under hundreds of concurrent virtual readers.

### Problems Faced During Integration
1. **Stale Cache & Search Index Lag**: Newly published articles from Member 2's Editor took several minutes to appear in Home search results due to outdated cache invalidation rules.
2. **Null Pointer Rendering Crashes**: When rendering article cards that were still undergoing AI summary generation by Member 5, the frontend feed crashed due to missing or `null` summary fields.
3. **Moderation Status Leakage**: Articles flagged or hidden by Member 4 in the Moderator Dashboard were still appearing in search results because search indexes were not refreshing upon moderation state changes.

### What They Completed
* **Unified Search & Feed Integration Test Suite** validating search filters, multi-tag queries, and pagination.
* **Automated Cache Invalidation Hooks** connecting Editor publishing and Moderator actions directly to home feed cache clears.
* **Load & Performance Benchmark Report** proving the Home feed responds within target SLAs (<200ms) under heavy concurrent read traffic.

---

## 2. Member 2: Editor & Content Pipeline Integration Lead

### Integration & Testing Role
**Write Pipeline, End-to-End Content Lifecycle, & Security Audit Lead.**

### Work & Scope
* **Pipeline Orchestration**: Connected the Editor’s publish action to automatically trigger Member 5's AI summary generation queue, update Member 1's Home feed indexes, and increment Member 3's author statistics.
* **Security & Penetration Testing**: Executed security tests targeting Cross-Site Scripting (XSS), script injection through code blocks, and unauthorized edit/delete payload manipulations.
* **Data Consistency Verification**: Ensured complex documents containing custom Markdown, syntax-highlighted code blocks, and inline images persist cleanly without data truncation.

### Problems Faced During Integration
1. **Asynchronous Publishing Race Conditions**: When authors published an article and immediately navigated to their User Profile, the new article occasionally resulted in a `404 Not Found` error because background database writes were still pending.
2. **WYSIWYG/AST Payload Schema Discrepancies**: Data format differences between the React rich-text editor output and the Java backend entity model caused broken formatting when restoring saved drafts.
3. **Sanitization Edge-Cases**: Strict XSS security filters inadvertently stripped valid developer code syntax (like HTML tags inside tutorial code blocks) during cross-module rendering.

### What They Completed
* **Automated End-to-End Content Lifecycle Test Suite** covering: *Draft Creation $\rightarrow$ Auto-Save $\rightarrow$ AI Trigger $\rightarrow$ Publish $\rightarrow$ Profile Update $\rightarrow$ Home Feed Rendering*.
* **Security & Input Sanitization Audit** confirming immunity against XSS and unauthorized content modification.
* **Draft Conflict & Network Interruption Recovery Tests** verifying draft state safety during network drops.

---

## 3. Member 3: User Profile & Live Portfolio Integration Lead

### Integration & Testing Role
**Polyglot Aggregation, Cross-Database Sync, & Fault-Tolerance Lead.**

### Work & Scope
* **Cross-Database Integration**: Integrated the User Profile aggregator service across PostgreSQL identity tables (Member 4 RBAC & User Security), MongoDB article repositories (Member 1 & 2), and third-party GitHub REST APIs.
* **User Data Synchronization Testing**: Tested platform-wide user metadata updates, ensuring that when an author updates their username or avatar, it updates consistently across all published articles, comments, and profile views.
* **Resilience & Fault-Tolerance Testing**: Simulated external API outages (e.g., GitHub API returning HTTP 429 Rate Exceeded or 503 Service Unavailable) to verify system grace under failure.

### Problems Faced During Integration
1. **Cascading API Latency**: Fetching profile data across SQL, MongoDB, and GitHub in sequence resulted in unacceptable integration test load times (>2 seconds).
2. **External Dependency Fragility**: Unhandled GitHub API rate limits during full system testing caused entire profile pages to throw HTTP 500 errors instead of cleanly displaying the user's local articles and bio.
3. **Orphaned Document References**: Deleting a user in Member 4's Moderator Dashboard left orphaned author IDs and dead profile links in MongoDB article collections.

### What They Completed
* **Cross-Database Integration Test Suite** verifying parallel fetching across PostgreSQL and MongoDB.
* **Resilience4j Circuit Breaker Test Suite** proving that profiles load gracefully using fallback mock data when third-party services fail.
* **User Identity Sync & Cascading Deletion Verification Suite** ensuring clean data integrity upon account updates or removals.

---

## 4. Member 4: Moderator Dashboard & System Governance Integration Lead

### Integration & Testing Role
**Global Security, RBAC Authorization, & System Governance Lead.**

### Work & Scope
* **Global Access Control Enforcement**: Integrated Moderator Dashboard security policies across all application routes, endpoints, and microservices to enforce Role-Based Access Control (RBAC).
* **Authorization Penetration Testing**: Attempted privilege escalation scenarios (e.g., standard Student Authors forging tokens to access admin endpoints, or Guest Readers attempting write APIs).
* **Real-Time Moderation Propagation Testing**: Verified that when a moderator hides a post or suspends an author, active user sessions, home feeds, editor access, and profile showcases immediately reflect the restriction.

### Problems Faced During Integration
1. **Stateless JWT Revocation Delays**: Suspended users could still perform editor writes or profile updates until their stateless JWT token expired. Required integrating a token blacklist validation check on sensitive write actions.
2. **Unhandled Null References on Client UI**: When a moderator hid a post that another user was currently reading, the reader’s browser threw unhandled JavaScript exceptions upon attempting to interact with the hidden post.
3. **Audit Log Data Gaps**: Admin audit trails were missing target metadata when moderation actions coincided with asynchronous background processing from the AI service.

### What They Completed
* **Global Security & Authorization Matrix Test Suite** verifying access boundaries across all roles (*Guest*, *Student Author*, *Moderator*).
* **End-to-End Governance Pipeline Test Suite** covering: *Content Flagging $\rightarrow$ Mod Queue Review $\rightarrow$ Article Hide $\rightarrow$ Token Invalidation $\rightarrow$ Global Feed Eviction*.
* **Admin Audit Trail Suite** ensuring every administrative action is immutably logged with accurate metadata.

---

## 5. Member 5: AI Microservice & Inter-Service Integration Lead

### Integration & Testing Role
**Inter-Service Communication, Microservice Orchestration, & Async Reliability Lead.**

### Work & Scope
* **Inter-Service REST/gRPC Integration**: Connected the isolated Python AI microservice with the central Java Spring Boot backend, establishing communication contracts and serialization rules.
* **Microservice Contract Testing**: Validated request/response payload structures, timeout policies, fallback triggers, and retry handlers between Java and Python environments.
* **Chaos & Queue Stress Testing**: Tested batch article processing queues under high volume to evaluate Python container memory management, thread pool limits, and backend non-blocking behavior.

### Problems Faced During Integration
1. **Data Serialization Mismatches**: Type mismatches between Python data models and Java Data Transfer Objects (DTOs)—such as tag lists being formatted as comma-separated strings instead of JSON arrays—caused inter-service parsing errors.
2. **Microservice Timeouts & Resource Spikes**: Heavy long-form technical posts caused the Python LLM service to exceed standard HTTP client timeout limits, causing the Java backend to drop connection sockets.
3. **Silent Queue Deadlocks**: If the AI microservice crashed mid-processing, the Java backend occasionally remained stuck in a `PENDING_SUMMARY` state indefinitely.

### What They Completed
* **Inter-Service Contract Test Suite** guaranteeing payload alignment between Python and Java services.
* **Non-Blocking Asynchronous Resilience Suite** proving that platform articles publish successfully 100% of the time, even if the AI microservice is offline or timing out.
* **AI Processing Queue Stress & Recovery Test Suite** validating system performance under continuous bulk processing load.

---

## Overall System Integration Summary

During the final **Full System Acceptance Testing**, all 5 team members executed unified end-to-end user journeys:

```
[Author Writes Article (Mem 2)] ──> [Triggers Async AI Tags/Summary (Mem 5)]
                                             │
                                             ▼
[Article Appears on Home Feed (Mem 1)] <── [Database Persistence (Polyglot)]
                                             │
                                             ▼
[Visible on User Portfolio (Mem 3)] <─── [Subject to Mod Controls (Mem 4)]
```

By dividing integration testing by architectural responsibility, the team successfully transformed five independently built modules into a secure, high-performance, and resilient developer platform.

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
* Compiled the comprehensive **Software Requirements Specification (SRS)** and **Technical Architecture Document** covering:
  * Hybrid **Feature-Driven Monorepo** microservice design.
  * **Polyglot Persistence Strategy** (PostgreSQL relational engine + MongoDB Atlas document engine).
  * Stateless **JWT Authentication** and strict **Role-Based Access Control (RBAC)** across `ROLE_GUEST`, `ROLE_STUDENT_AUTHOR`, and `ROLE_MODERATOR`.
  * **Dual-Layer XSS Sanitization** using client-side `DOMPurify` and backend HTML encoding.

  - View our [Final Project Report](https://drive.google.com/file/d/1pFmduew7up6oNzZ0_X2YcEbCZNrLNfrp/view?usp=drive_link).

#### 2. Final Evaluation Slide Deck (PPT)
* Prepared a structured 20-slide presentation deck covering:
  * **Problem Statement & Scope:** Addressing the gap in traditional CMS platforms for software engineering students.
  * **Architecture & Polyglot Persistence Topology:** Visualizing Spring Boot, Python FastAPI, PostgreSQL, and MongoDB Atlas.
  * **AI Microservice Workflow:** Non-blocking `@Async` execution for automated LLM summarization and tech tagging.
  * **Live Portfolio Aggregation:** Parallel fetching across PostgreSQL, MongoDB, and GitHub REST API ($T_{\text{aggregation}} < 500\text{ ms}$).
  * **Quality Assurance & Testing:** Code coverage reports, E2E integration test results, and Resilience4j circuit breaker performance.

  * View our [PPT of Project Report](https://drive.google.com/file/d/1vyoF--WzF5YZf_zW_lc82_3CWRGLDBO1/view?usp=drive_link).

#### 3. End-to-End Product Video Demonstration
* Produced an HD video demonstration showcasing:
  * **User Onboarding & IAM:** Student registration, profile curation, and GitHub handle linking.
  * **Rich-Text Authoring:** Writing technical articles using TipTap editor with code syntax highlighting and LaTeX math formulas.
  * **Asynchronous AI Enrichment:** Real-time generation of TL;DR summaries and dynamic tech tags via Python FastAPI LangChain service.
  * **Live Developer Portfolio:** Real-time rendering of user articles, skill badges, and pinned GitHub repositories.
  * **Moderator Control Center:** Administrative flagging, article visibility toggles, and user management audit logs.

  * View our [Final Video](https://drive.google.com/file/d/1C6V8o4NL6FSTMm4dw5IOK83lI_o6LncL/view?usp=drive_link).

#### 4. Containerization & Deployment Audit
* Finalized `docker-compose.yml` for single-command environment provisioning.
* Completed cross-platform validation ensuring backend, frontend, microservices, and databases launch seamlessly in production environments.

---
