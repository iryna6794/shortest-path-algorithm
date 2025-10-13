# Basic shortest-path algorithm implementations

## Algorithms Implemented

### 1. Breadth-First Search (BFS)
- **Class:** BSFSolver
- **Description:** Finds the shortest path in an unweighted graph using a queue (LinkedList). Expands nodes in the order they are discovered.

### 2. Dijkstra’s Algorithm (Binary Heap)
- **Class:** DijkstraSolver
- **Description:** Finds the shortest path in a weighted graph using a priority queue (PriorityQueue). Always expands the node with the smallest known distance. Efficient for weighted graphs.

### 3. Dijkstra’s Algorithm (Ordered Queue)
- **Class:** DijkstraTreeSetSolver
- **Description:** Uses a TreeSet with a custom comparator to maintain nodes ordered by distance. Provides similar asymptotic complexity to a binary heap, but uses a different data structure.
