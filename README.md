# User Fraud check

This module provides a REST API to check if data associated with a user record 
has a risk level of being fraud.

All high-level documentation is in the [wiki](https://github.com/JairAviles/UserFraudChecker/wiki) directory. Check out there for 
acceptance criteria, architecture diagrams, and project structure. 

## Getting started

You'll need to install the following software:
- [Java 21 JDK](https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/downloads-list.html)
- [Gradle](https://gradle.org/install/)

Suggested to manage through [sdkman.](https://sdkman.io/)

### Initial setup

When you first check out the project, run:

Non-blocking: 
```bash
# check you have installed the JDK and Gradle

javac -version

gradle -version

```

Build the project:

```bash
# refresh state of dependencies
./gradlew -U

# build the project
./gradlew build

# run the project
./gradlew bootRun
```

Tests are written with JUnit5 and can be run with:

```bash
# run with supress all warning messages flag
./gradlew test --warning-mode none
```

