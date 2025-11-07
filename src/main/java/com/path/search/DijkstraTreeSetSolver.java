package com.path.search;

import java.util.*;

public class DijkstraTreeSetSolver implements Solver {

    @Override
    public Map<Integer, PathResult> findShortestPaths(Map<Integer, Vertex> vertexes, int number, List<Integer> distances) {
        Map<Integer, PathResult> shortestPaths = new HashMap<>();
        Map<Integer, Integer> minDistances = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        TreeSet<PathResult> pathResultQueue = new TreeSet<>();
        Map<Integer, PathResult> pathResultMap = new HashMap<>();

        PathResult startPathResult = new PathResult(number, 0, Arrays.asList(number));
        pathResultQueue.add(startPathResult);
        pathResultMap.put(number, startPathResult);
        minDistances.put(number, 0);

        while (!pathResultQueue.isEmpty() && shortestPaths.size() < distances.size()) {
            PathResult current = pathResultQueue.pollFirst();
            int currentNumber = current.number;
            int distance = current.distance;
            List<Integer> path = current.path;

            if (visited.contains(currentNumber)) {
                continue;
            }

            visited.add(currentNumber);

            if (distances.contains(currentNumber)) {
                shortestPaths.put(currentNumber, new PathResult(currentNumber, distance, path));
            }

            Vertex vertex = vertexes.get(currentNumber);
            if (vertex == null) {
                continue;
            }

            for (Edge edge : vertex.getNeighbours()) {
                int neighbor = edge.getNumber();
                int newDist = distance + edge.getLength();
                if (!minDistances.containsKey(neighbor) || newDist < minDistances.get(neighbor)) {
                    minDistances.put(neighbor, newDist);
                    List<Integer> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    PathResult oldPathResult = pathResultMap.get(neighbor);
                    if (oldPathResult != null) {
                        pathResultQueue.remove(oldPathResult);
                    }
                    PathResult newPathResult = new PathResult(neighbor, newDist, newPath);
                    pathResultQueue.add(newPathResult);
                    pathResultMap.put(neighbor, newPathResult);
                }
            }
        }
        return shortestPaths;
    }

}

