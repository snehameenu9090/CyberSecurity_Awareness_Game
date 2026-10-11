# Installation Guide

## Prerequisites

- JDK 17 or newer installed and available on `PATH`.
- A graphical desktop environment.
- For a Maven build, Maven installed. For the Windows runner, an internet connection is needed on first launch if the ignored dependency JARs are not already cached in `target/lib/`.

## Run on Windows

1. Obtain the project files.
2. Open the project root folder.
3. Run `run.bat` or `run.ps1`.
4. If this is the first launch and the local JARs are absent, allow the script to download SQLite JDBC and SLF4J API dependencies.
5. The script compiles the application and opens the Swing window.

## Build with Maven

From the project root:

```powershell
mvn package
```

Maven downloads dependencies declared in `pom.xml` as needed and creates the JAR. Runtime dependencies are not bundled into that JAR. On Windows, use `run.bat` or `run.ps1` to compile and launch with the required classpath; otherwise, launch the JAR with its dependencies explicitly on the classpath.

## Local data

The main application uses `data/cyberaware.db` relative to its working directory. It creates the folder and player table when needed. Keep this local database private; it is ignored by Git. If the database cannot be initialized, the current session uses in-memory state and will not retain it across launches.

## Troubleshooting

| Symptom | Check |
|---|---|
| `javac` or `java` not found | Install a JDK 17+ and add its `bin` directory to `PATH`. |
| Dependency download fails | Check the internet connection and retry the launcher, or use Maven to resolve dependencies. |
| Window does not open | Confirm that the environment has a graphical desktop and review launcher output. |
| Player data is not retained | Confirm that the project working directory is writable and that SQLite initialization succeeded. |
