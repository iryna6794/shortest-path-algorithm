package com.path.search;

import java.util.*;

public class BFSSolver implements Solver {

    @Override
    public Map<Integer, Node> findShortestPaths(Map<Integer, Hub> hubs, int source, List<Integer> searchedHubs) {
        Queue<Node> nodeQueue = new LinkedList<>();
        Map<Integer, Node> resultNodeMap = new HashMap<>();
        Set<List<Integer>> visited = new HashSet<>();

        Hub hub = hubs.get(source);
        searchPath(hub, new Node(source, 0, List.of(hub.getNumber())), searchedHubs, resultNodeMap, nodeQueue);

        while (!nodeQueue.isEmpty()) {
            Node node = nodeQueue.poll();
            if (visited.contains(node.path)) {
                continue;
            } else {
                visited.add(node.path);
            }
            searchPath(hubs.get(node.hubNumber), node, searchedHubs, resultNodeMap, nodeQueue);
        }
        return resultNodeMap;
    }

    private void searchPath(Hub parent,
                            Node parentNode,
                            List<Integer> searchedHubs,
                            Map<Integer, Node> resultNodeMap,
                            Queue<Node> nodeQueue) {
        if (searchedHubs.size() == resultNodeMap.size()) {
            OptionalInt maxOp = resultNodeMap.values().stream()
                    .mapToInt(node -> node.distance)
                    .max();
            if (maxOp.isPresent() && parentNode.distance >= maxOp.getAsInt()) {
                return;
            }
        }

        if (parent != null) {
            for (Vertex vertex : parent.getVertices()) {

                List<Integer> pathList = parentNode.path;
                int neighbor = vertex.getNeighbor();

                if (pathList.contains(neighbor)) {
                    continue;
                }

                List<Integer> newPathList = new ArrayList<>(parentNode.path);
                newPathList.add(neighbor);

                Node path = new Node(parent.getNumber(), parentNode.distance + vertex.getLength(), newPathList);

                if (searchedHubs.contains(neighbor)) {
                    Node storedNode = resultNodeMap.get(neighbor);
                    if (storedNode == null || path.distance < storedNode.distance) {
                        resultNodeMap.put(neighbor, path);
                    }
                }
                nodeQueue.add(new Node(neighbor, path.distance, path.path));
            }
        }
    }

}
