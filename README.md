# Maze Solving

The companion code for the Maze chapter: one `MazeSolver` that finds a path
through a rectangular maze four ways — recursive backtracking, an explicit
stack, a queue, and a queue that measures the shortest distance — a JUnit
suite that drives all four against the chapter's traced mazes, and a
reachable-cells practice problem. The `Stack` and `Queue` ADTs from the
previous two chapters are vendored here so the maze code is self-contained.

## Prerequisites

- JDK 17+ (JUnit 6 requires it). The JUnit jar is already vendored in `lib/`;
  there is nothing to download.

## Repository Layout

```plaintext
.
├── lib/
│   └── junit-platform-console-standalone-6.1.0.jar
├── src/
│   ├── main/
│   │   ├── maze/                       # the taught code
│   │   │   ├── MazeSolver.java         #   the four searches (recursion, stack, queue, distance)
│   │   │   └── Main.java               #   demo entry point
│   │   ├── stack/                      # vendored from the Stack chapter
│   │   │   ├── Stack.java
│   │   │   └── ArrayStack.java
│   │   ├── queue/                      # vendored from the Queue chapter
│   │   │   ├── Queue.java
│   │   │   └── LinkedQueue.java
│   │   └── practice/                   # practice problems
│   │       └── ReachableCells.java     #   count cells reachable from the start
│   └── test/
│       ├── maze/
│       │   └── MazeSolverTest.java     #   the four searches against the traced mazes
│       └── practice/
│           └── ReachableCellsTest.java
└── scripts/
    ├── test.sh                         # compile + run every JUnit suite
    └── run.sh                          # compile + run the demo
```

## Run the tests

```bash
./scripts/test.sh
```

This compiles everything and runs the full suite: the four maze searches
against the chapter's traced mazes and edge cases, plus the practice suite.

## Run the demo

```bash
./scripts/run.sh
```

## The maze representation

A maze is a plain `char[][]`. A `'#'` is a wall; any other character is open.
The search starts at the top-left cell `(0, 0)` and looks for the bottom-right
cell. Coordinates are `(row, col)`, zero-indexed from the top-left, with the
row increasing downward. The four searches in `MazeSolver` are implemented
exactly as the lecture pages develop them, using the chapter's own `Stack`
and `Queue` rather than `java.util` collections, because reading them next to
the lecture is the point.
