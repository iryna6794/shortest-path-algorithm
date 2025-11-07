package com.path.search;

import java.util.*;

public class DijkstraSolver implements Solver {

    @Override
    public Map<Integer, PathResult> findShortestPaths(Map<Integer, Vertex> vertexes, int number, List<Integer> destinations) {
        Map<Integer, PathResult> shortestPaths = new HashMap<>();
        Map<Integer, Integer> minDistances = new HashMap<>();
        PriorityQueue<PathResult> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.distance));
        Set<Integer> visited = new HashSet<>();

        queue.add(new PathResult(number, 0, List.of(number)));
        minDistances.put(number, 0);

        while (!queue.isEmpty() && shortestPaths.size() < destinations.size()) {
            PathResult current = queue.poll();
            int currentNumber = current.number;
            int currentDist = current.distance;
            List<Integer> currentPath = current.path;

            if (visited.contains(currentNumber)) {
                continue;
            }

            visited.add(currentNumber);

            if (destinations.contains(currentNumber)) {
                shortestPaths.put(currentNumber, new PathResult(currentNumber, currentDist, currentPath));
            }

            Vertex vertex = vertexes.get(currentNumber);
            if (vertex == null) {
                continue;
            }

            for (Edge edge : vertex.getNeighbours()) {
                int neighbor = edge.getNumber();
                int newDist = currentDist + edge.getLength();
                if (!minDistances.containsKey(neighbor) || newDist < minDistances.get(neighbor)) {
                    minDistances.put(neighbor, newDist);
                    List<Integer> newPath = new ArrayList<>(currentPath);
                    newPath.add(neighbor);
                    queue.add(new PathResult(neighbor, newDist, newPath));
                }
            }
        }
        return shortestPaths;
    }
}
