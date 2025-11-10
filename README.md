# Basic shortest-path algorithm implementations

## Algorithms Implemented

### 1. Breadth-First Search (BFS)

- **Class:** BSFSolver
- **Description:** Finds the shortest path in a weighted graph using a queue (LinkedList). Expands vertexes in the order
  they are discovered.

### 2. Dijkstra’s Algorithm (Binary Heap)

- **Class:** DijkstraSolver
- **Description:** Finds the shortest path in a weighted graph using a priority queue (PriorityQueue). Always expands
  the vertex with the smallest known distance. Efficient for weighted graphs.

### 3. Dijkstra’s Algorithm (Ordered Queue)

- **Class:** DijkstraTreeSetSolver
- **Description:** Uses a TreeSet with a custom comparator to maintain vertexes ordered by distance. Provides similar
  asymptotic complexity to a binary heap, but uses a different data structure.

## Benchmark Results

| Algorithm                  | Mode  | Samples | Avg Score | Error  | Units | Min     | Max     | Stdev  | 99.9% CI           |
|----------------------------|-------|---------|-----------|--------|-------|---------|---------|--------|--------------------|
| **BFS (LinkedList)**       | thrpt | 25      | 0.087     | 0.002  | ops/s | 0.081   | 0.092   | 0.003  | [0.085, 0.089]     |
| **Dijkstra (Binary Heap)** | thrpt | 25      | 171.038   | 11.522 | ops/s | 137.824 | 195.564 | 15.381 | [159.517, 182.560] |
| **Dijkstra (TreeSet)**     | thrpt | 25      | 125.344   | 13.169 | ops/s | 105.957 | 155.970 | 17.580 | [112.175, 138.512] |

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
Benchmark                            Mode  Cnt    Score    Error  Units
Benchmark.benchmarkBFS              thrpt   25    0.087 ±  0.002  ops/s
Benchmark.benchmarkDijkstra         thrpt   25  171.038 ± 11.522  ops/s
Benchmark.benchmarkDijkstraTreeSet  thrpt   25  125.344 ± 13.169  ops/s
```
