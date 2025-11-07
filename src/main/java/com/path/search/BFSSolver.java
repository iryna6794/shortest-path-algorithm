package com.path.search;

import java.util.*;

public class BFSSolver implements Solver {

    @Override
    public Map<Integer, PathResult> findShortestPaths(Map<Integer, Vertex> vertexes, int number, List<Integer> destinations) {
        Queue<PathResult> pathResultQueue = new LinkedList<>();
        Map<Integer, PathResult> pathResultMap = new HashMap<>();
        Set<List<Integer>> visitedPaths = new HashSet<>();

        Vertex vertex = vertexes.get(number);
        searchPath(vertex, new PathResult(number, 0, Arrays.asList(vertex.getNumber())), destinations, pathResultMap, pathResultQueue);

        while (!pathResultQueue.isEmpty()) {
            PathResult pathResult = pathResultQueue.poll();
            if (visitedPaths.contains(pathResult.path)) {
                continue;
            } else {
                visitedPaths.add(pathResult.path);
            }
            searchPath(vertexes.get(pathResult.number), pathResult, destinations, pathResultMap, pathResultQueue);
        }
        return pathResultMap;
    }

    private void searchPath(Vertex parent,
                            PathResult parentPathResult,
                            List<Integer> destinations,
                            Map<Integer, PathResult> resultNodeMap,
                            Queue<PathResult> pathResultQueue) {
        if (destinations.size() == resultNodeMap.size()) {
            OptionalInt maxOp = resultNodeMap.values().stream()
                    .mapToInt(node -> node.distance)
                    .max();
            if (maxOp.isPresent() && parentPathResult.distance >= maxOp.getAsInt()) {
                return;
            }
        }

        if (parent != null) {
            for (Edge edge : parent.getNeighbours()) {

                List<Integer> pathList = parentPathResult.path;
                int number = edge.getNumber();

                if (pathList.contains(number)) {
                    continue;
                }

                List<Integer> newPathList = new ArrayList<>(parentPathResult.path);
                newPathList.add(number);

                PathResult newPath = new PathResult(parent.getNumber(), parentPathResult.distance + edge.getLength(), newPathList);

                if (destinations.contains(number)) {
                    PathResult storedPathResult = resultNodeMap.get(number);
                    if (storedPathResult == null || newPath.distance < storedPathResult.distance) {
                        resultNodeMap.put(number, newPath);
                    }
                }
                pathResultQueue.add(new PathResult(number, newPath.distance, newPath.path));
            }
        }
    }

}
