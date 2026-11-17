# Princeton Algorithms, Part I (COS 226)

Java implementations of data structures, sorting algorithms, and geometric search problems from Princeton University's *
*Algorithms, Part I**, taught by Robert Sedgewick and Kevin Wayne.

**Progress:** Completed Part I (Weeks 1–5).

## Assignments & Implementations

| Week  | Assignment                 | Core Algorithms & Data Structures                                                |
|-------|----------------------------|----------------------------------------------------------------------------------|
| **1** | Percolation                | Union-Find (`WeightedQuickUnionUF`), Monte Carlo simulation, backwash mitigation |
| **2** | Deques & Randomized Queues | Doubly-linked lists, resizing arrays, generic iterators, Knuth shuffle           |
| **3** | Collinear Points           | Mergesort, Quicksort, custom `Comparator` implementations, slope sorting         |
| **4** | 8-Puzzle                   | A* search, binary heap priority queues (`MinPQ`), Manhattan & Hamming heuristics |
| **5** | Kd-Trees                   | 2D-tree spatial partitioning, range search, nearest-neighbor pruning             |

## Repository Structure

The repository contains Java source files for the course assignments:

- **Percolation:** `Percolation.java`, `PercolationStats.java`
- **Deques & Randomized Queues:** `Deque.java`, `RandomizedQueue.java`, `Permutation.java`, , `CircularDeque.java`
- **Collinear Points:** `Point.java`, `BruteCollinearPoints.java`, `FastCollinearPoints.java`
- **8-Puzzle:** `Board.java`, `Solver.java`
- **Kd-Trees:** `PointSET.java`, `KdTree.java`

Only Java source files are tracked. Third-party binaries and proprietary course test data are excluded.

## Compilation & Setup

These implementations depend on Princeton's standard library (`algs4.jar`).

1. Download `algs4.jar` from the [Princeton Algorithms website](https://algs4.cs.princeton.edu/code/).
2. Compile with the library on your classpath:

   ```bash
   javac -cp .:algs4.jar Percolation.java
   ```