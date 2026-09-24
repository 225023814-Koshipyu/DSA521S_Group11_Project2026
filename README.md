# DSA521S Group Mini-Project 2026

## NUST Campus Service Centre Simulation

This Java project implements the required queue, singly linked list, stack,
array-processing tasks, four sorting algorithms, sorting experiment, and the
integrated service-centre menu. The required structures and sorting algorithms
are student implementations; Java's built-in `Queue`, `Stack`, `LinkedList`,
and sorting methods are not used.

## Group identification - complete before submission

- Group number: **GROUP XX**
- Member 1: **224036998 - ILEKA SAMWEL**
- Member 2: **225019388 - NAMUPALA PINEAS**
- Member 3: **225023814 - KOSHIPYU JOSUA**
- Member 4: **224057626 - ISMAEL LASARUS**
- Member 5: **226122409 - ANGULA JOSEPH**

- Submitted by: **225023814 - KOSHIPYU JOSUA**
- GitHub repository: **https://github.com/225023814-Koshipyu/DSA521S_Group11_Project2026**

## Run in VS Code

1. Install a JDK (Java 8 or newer) and the VS Code Extension Pack for Java.
2. Open this `dsa521s-service-centre` folder in VS Code.
3. Press `Ctrl+Shift+B` to compile the project.
4. Open **Run and Debug** and select one of these configurations:
   - **Campus Service Centre** - interactive Part D menu.
   - **Postfix Stack Demo** - independent Part A3 exercise.
   - **Full Evidence Demo** - Parts A-C plus an integration smoke test.
   - **Project Tests** - automated verification.

If `javac` is not on `PATH`, select the JDK in VS Code with
`Java: Configure Java Runtime` and then rerun the build task.

## Run from a terminal

```powershell
New-Item -ItemType Directory -Force bin | Out-Null
javac -encoding UTF-8 -d bin src/*.java
java -cp bin ServiceCentreApp
```

Other useful commands:

```powershell
java -cp bin PostfixDemo
java -cp bin DemoRunner
java -cp bin ProjectTests
java -cp bin SortingExperiment docs/experiment-results.csv
```

## Project map

| File | Purpose |
|---|---|
| `Student.java` | Shared student record model |
| `StudentQueue.java` | Circular-array FIFO queue |
| `StudentLinkedList.java` | Custom singly linked list |
| `DoubleStack.java` | Array stack and postfix evaluator |
| `DailyStatistics.java` | Manual service-time array traversal |
| `SortingAlgorithms.java` | Selection, insertion, merge, and quick sort |
| `SortingExperiment.java` | Fair seeded experiment for 20, 50, 100, and 500 values |
| `ServiceCentreApp.java` | Integrated menu program |
| `PostfixDemo.java` | Standalone stack exercise |
| `DemoRunner.java` | Reproducible demonstrations and evidence |
| `ProjectTests.java` | Dependency-free automated tests |
