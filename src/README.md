# Design Pattern Phase 4 - Composite + Singleton Demo

This Java project demonstrates the **Composite** and **Singleton** design patterns using a Mario Kart–themed championship simulation.

---

## Overview
- **Composite Pattern**: Models a hierarchical structure of races, stages, and race types.
  - `Race` -> contains multiple `Stage` objects.
  - `Stage` ->  contains multiple `RaceType` events.
  - `RaceType` ->  acts as a leaf node.
- **Singleton Pattern**: Implemented through `DataRepository`, simulating a shared in-memory database for storing race results and racer data.

---

## Activity Flow
1. **Organizer builds the championship structure** (`Race`, `Stage`, `RaceType`).
2. **Race results are entered** into the shared repository.
3. **System verifies** if the race is official.
4. **Racer’s rank is evaluated** based on total podiums.
5. **Notification** is sent to the racer about promotion or encouragement.

---

## Run Instructions
From the project root:

```bash
# Compile
javac src/Main.java src/Composite/*.java src/Singleton/*.java -d out

# Run
java -cp out Main
