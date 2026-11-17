# Princeton Algorithms, Part I (COS 226)

Java implementations of data structures, sorting algorithms, and geometric search problems from Princeton University's *
*Algorithms, Part I**, taught by Robert Sedgewick and Kevin Wayne.

## Assignments & Implementations

| Week       | Project                        | Core Algorithms & Data Structures                                                          |
|:-----------|:-------------------------------|:-------------------------------------------------------------------------------------------|
| **Week 1** | **Percolation**                | Union-Find (`WeightedQuickUnionUF`), Monte Carlo simulation, backwash mitigation           |
| **Week 2** | **Deques & Randomized Queues** | Doubly-linked lists, resizing arrays, generic iterators, Knuth shuffle                     |
| **Week 3** | **Collinear Points**           | Mergesort, Quicksort, custom `Comparator` implementations, slope sorting                   |
| **Week 4** | **8-Puzzle**                   | A* search algorithm, binary heap priority queues (`MinPQ`), Manhattan & Hamming heuristics |
| **Week 5** | **Kd-Trees**                   | 2D-tree spatial partitioning, range search, nearest-neighbor pruning                       |

## Repository Structure

The repository is organized into core algorithmic implementations and interactive graphical visualizers and clients.

### Core Source Code

- **Percolation:** `Percolation.java`, `PercolationStats.java`
- **Deques & Randomized Queues:** `Deque.java`, `CircularDeque.java`, `RandomizedQueue.java`, `Permutation.java`
- **Collinear Points:** `Point.java`, `LineSegment.java`, `BruteCollinearPoints.java`, `FastCollinearPoints.java`
- **8-Puzzle:** `Board.java`, `Solver.java`
- **Kd-Trees:** `PointSET.java`, `KdTree.java`

### Interactive Clients & Visualizers

- **Collinear Visualizer:** `CollinearPointsClient.java`
- **Kd-Tree Visualizers:** `KdTreeVisualizer.java`, `NearestNeighborVisualizer.java`, `RangeSearchVisualizer.java`

*Note: Third-party binaries such as `algs4.jar` and proprietary course input files are excluded via `.gitignore` to keep
the repository lightweight.*

## Compilation & Execution

This project uses Princeton's standard library (`algs4.jar`), located in the `.lift/` directory.

For convenience, two shell scripts are provided:

- **`jc`** — Compiles Java source files using the Princeton library.
- **`jr`** — Runs compiled Java classes using the same library.

### Usage

Compile a Java source file:

```bash
jc Percolation.java
```

Run the compiled class:

```bash
jr Percolation
```

Both commands automatically include `.lift/algs4.jar` and the current directory in the Java classpath.

### Setup

1. Download `algs4.jar` from the [Princeton Algorithms website](https://algs4.cs.princeton.edu/code/).
2. Place the library inside the `.lift/` directory at the repository root.
3. Make sure the `jc` and `jr` scripts are executable and accessible from your terminal.

