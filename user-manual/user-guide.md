# User Guide

## Start a session

Launch the application, choose an available study language, and enter a non-blank player name when prompted. Existing names are matched using a normalized, case-insensitive key. When SQLite is available, the application restores the most recently played profile and stores player language and points locally.

## Explore the learning activities

- **Awareness toolkit:** Read the six topics, examples, and recommended safety actions.
- **Quiz:** Choose among password safety, phishing, public Wi-Fi, malware, and AI safety. Review the feedback after answering.
- **Game arena:** Choose one of four practice games:
  - **Cyber Shield Defender:** Move using arrow keys or A/D, collect backup stars, avoid phishing threats, and use Space to firewall malware.
  - **Phishing Inbox:** Drag simulated messages into Spam or Safe Inbox after checking their clues.
  - **Malware Cleanup Crew:** Inspect a simulated app grid and quarantine suspicious downloads without removing trusted apps.
  - **Passphrase Builder:** Select four word tiles in the timed exercise. Do not reuse the sample phrase as a real password.

## Progress and privacy

Points are associated with the active player. The SQLite file is stored locally in `data/cyberaware.db` by default. The inspected project does not include online accounts or cloud score synchronization. If SQLite is unavailable, session state is temporary.

## Safe use

All messages, apps, and security events inside the games are simulations. Do not enter real credentials or personal information into examples, and do not use generated sample phrases for real accounts.
