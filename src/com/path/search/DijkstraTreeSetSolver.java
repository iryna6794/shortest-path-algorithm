package com.path.search;

import java.util.*;

public class DijkstraTreeSetSolver implements Solver {

    @Override
    public Map<Integer, Node> findShortestPaths(Map<Integer, Hub> hubs, int source, List<Integer> targets) {
        Map<Integer, Node> shortestPaths = new HashMap<>();
        Map<Integer, Integer> minDistances = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        TreeSet<Node> nodeQueue = new TreeSet<>();
        Map<Integer, Node> nodeMap = new HashMap<>();

        Node startNode = new Node(source, 0, List.of(source));
        nodeQueue.add(startNode);
        nodeMap.put(source, startNode);
        minDistances.put(source, 0);

        while (!nodeQueue.isEmpty() && shortestPaths.size() < targets.size()) {
            Node current = nodeQueue.pollFirst();
            int hubNumber = current.hubNumber;
            int distance = current.distance;
            List<Integer> path = current.path;

            if (visited.contains(hubNumber)) {
                continue;
            }

            visited.add(hubNumber);

            if (targets.contains(hubNumber)) {
                shortestPaths.put(hubNumber, new Node(hubNumber, distance, path));
            }

            Hub hub = hubs.get(hubNumber);
            if (hub == null) {
                continue;
            }

            for (Vertex vertex : hub.getVertices()) {
                int neighbor = vertex.getNeighbor();
                int newDist = distance + vertex.getLength();
                if (!minDistances.containsKey(neighbor) || newDist < minDistances.get(neighbor)) {
                    minDistances.put(neighbor, newDist);
                    List<Integer> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    Node oldNode = nodeMap.get(neighbor);
                    if (oldNode != null) {
                        nodeQueue.remove(oldNode);
                    }
                    Node newNode = new Node(neighbor, newDist, newPath);
                    nodeQueue.add(newNode);
                    nodeMap.put(neighbor, newNode);
                }
            }
        }
        return shortestPaths;
    }

}

