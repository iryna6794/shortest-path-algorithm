# Basic shortest-path algorithm implementations

## Algorithms Implemented

### 1. Breadth-First Search (BFS)
- **Class:** BSFSolver
- **Description:** Finds the shortest path in a weighted graph using a queue (LinkedList). Expands nodes in the order they are discovered.

### 2. Dijkstra’s Algorithm (Binary Heap)
- **Class:** DijkstraSolver
- **Description:** Finds the shortest path in a weighted graph using a priority queue (PriorityQueue). Always expands the pathResult with the smallest known distance. Efficient for weighted graphs.

### 3. Dijkstra’s Algorithm (Ordered Queue)
- **Class:** DijkstraTreeSetSolver
- **Description:** Uses a TreeSet with a custom comparator to maintain nodes ordered by distance. Provides similar asymptotic complexity to a binary heap, but uses a different data structure.

## Benchmark Results

| Algorithm                  | Mode   | Samples | Avg Score | Error   | Units | Min    | Max    | Stdev  | 99.9% CI           |
|---------------------------|--------|---------|-----------|---------|-------|--------|--------|--------|--------------------|
| **BFS**                   | thrpt  | 25      | 0.091     | 0.002   | ops/s | 0.086  | 0.097  | 0.002  | [0.090, 0.093]     |
| **Dijkstra (Binary Heap)**| thrpt  | 25      | 167.668   | 10.815  | ops/s | 138.707| 192.410| 14.438 | [156.853, 178.484] |
| **Dijkstra (TreeSet)**    | thrpt  | 25      | 141.007   | 4.922   | ops/s | 130.044| 156.754| 6.571  | [136.085, 145.930] |

**Legend:**
- **Mode:** Measurement mode (throughput = operations per second)
- **Samples:** Number of measurement samples
- **Avg Score:** Average throughput
- **Error:** Error margin
- **Units:** Measurement units
- **Min/Max:** Minimum and maximum observed values
- **Stdev:** Standard deviation
- **99.9% CI:** 99.9% confidence interval

---

**Raw JMH Output:**
```
Benchmark                                  Mode  Cnt    Score    Error  Units
Benchmark.benchmarkBFS              thrpt   25    0.091 ±  0.002  ops/s
Benchmark.benchmarkDijkstra         thrpt   25  167.668 ± 10.815  ops/s
Benchmark.benchmarkDijkstraTreeSet  thrpt   25  141.007 ±  4.922  ops/s
```
