package com.path.search;

import java.util.*;

public class DijkstraSolver implements Solver {

    @Override
    public Map<Integer, Node> findShortestPaths(Map<Integer, Hub> hubs, int source, List<Integer> targets) {
        Map<Integer, Node> shortestPaths = new HashMap<>();
        Map<Integer, Integer> minDistances = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.distance));
        Set<Integer> visited = new HashSet<>();

        queue.add(new Node(source, 0, List.of(source)));
        minDistances.put(source, 0);

        while (!queue.isEmpty() && shortestPaths.size() < targets.size()) {
            Node current = queue.poll();
            int hubNumber = current.hubNumber;
            int currentDist = current.distance;
            List<Integer> currentPath = current.path;

            if (visited.contains(hubNumber)) {
                continue;
            }

            visited.add(hubNumber);

            if (targets.contains(hubNumber)) {
                shortestPaths.put(hubNumber, new Node(hubNumber, currentDist, currentPath));
            }

            Hub hub = hubs.get(hubNumber);
            if (hub == null) {
                continue;
            }

            for (Vertex vertex : hub.getVertices()) {
                int neighbor = vertex.getNeighbor();
                int newDist = currentDist + vertex.getLength();
                if (!minDistances.containsKey(neighbor) || newDist < minDistances.get(neighbor)) {
                    minDistances.put(neighbor, newDist);
                    List<Integer> newPath = new ArrayList<>(currentPath);
                    newPath.add(neighbor);
                    queue.add(new Node(neighbor, newDist, newPath));
                }
            }
        }
        return shortestPaths;
    }
}
