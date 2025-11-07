package com.path.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class TestSolver {
    private static final String ROOT_PATH = "test/testdata";

    public static void main(String[] args) {
        final List<Integer> destinations = List.of(7, 37, 59, 82, 99, 115, 133, 165, 188, 197);

        System.out.println("\nTesting BFS:\n");
        LocalDateTime startBFS = LocalDateTime.now();
        new TestSolver(new BFSSolver()).test(destinations);
        System.out.println("Total time for BFS: " + Duration.between(startBFS, LocalDateTime.now()).toMillis() + " ms");

        System.out.println("\nTesting Dijkstra:\n");
        LocalDateTime startDijkstra = LocalDateTime.now();
        new TestSolver(new DijkstraSolver()).test(destinations);
        System.out.println("Total time for Dijkstra: " + Duration.between(startDijkstra, LocalDateTime.now()).toMillis() + " ms");

        System.out.println("\nTesting Dijkstra with TreeSet:\n");
        LocalDateTime startDijkstraTreeSet = LocalDateTime.now();
        new TestSolver(new DijkstraTreeSetSolver()).test(destinations);
        System.out.println("Total time for Dijkstra with TreeSet: " + Duration.between(startDijkstraTreeSet, LocalDateTime.now()).toMillis() + " ms");
    }

    private final Solver solver;

    public TestSolver(Solver solver) {
        this.solver = solver;
    }

    public void test(List<Integer> destinations) {
        getSearchPaths().forEach(path -> {
            System.out.print("test " + path);
            runTestForCase(path, destinations);
        });
    }

    private static List<String> getSearchPaths() {
        List<String> searchPaths = new ArrayList<>();
        File inputDir = new File(ROOT_PATH);
        String prefix = "input_random_";
        String[] files = inputDir.list();
        if (files != null) {
            for (String fileName : files) {
                if (fileName.startsWith(prefix)) {
                    searchPaths.add(fileName.replace(prefix, "").replace(".txt", ""));
                }
            }
        }
        return searchPaths;
    }

    private void runTestForCase(String searchPath, List<Integer> destinations) {
        Map<Integer, Vertex> vertexes = readInputFromFile(String.format("%s/input_random_%s.txt", ROOT_PATH, searchPath));
        String expected = readOutputsFromFile(String.format("%s/output_random_%s.txt", ROOT_PATH, searchPath));
        Map<Integer, List<Integer>> expectedPaths = readPathsFromFile(String.format("%s/paths_random_%s.txt", ROOT_PATH, searchPath), destinations);

        test(vertexes, expected, expectedPaths, destinations);
    }

    private void test(Map<Integer, Vertex> vertexes, String expected, Map<Integer, List<Integer>> expectedPaths, List<Integer> destinations) {
        LocalDateTime startTime = LocalDateTime.now();

        Result shostestPathResult = getResult(vertexes, destinations);

        LocalDateTime endTime = LocalDateTime.now();
        System.out.println(": " + Duration.between(startTime, endTime).toMillis() + " ms");

        if (!expected.equals(shostestPathResult.result)) {
            System.err.println("Failed for expected " + expected + ", but got " + shostestPathResult.result);
        }

        expectedPaths.forEach((hubNumber, paths) -> {
            PathResult pathResult = shostestPathResult.shortestPaths.get(hubNumber);
            if (pathResult == null) {
                System.err.println("No path found for hub " + hubNumber);
            } else if (!pathResult.path.equals(paths)) {
                System.err.println("Path mismatch for hub " + hubNumber + ", expected " + paths + ", but got " + pathResult.path);
            }
        });
    }

    private Result getResult(Map<Integer, Vertex> vertexes, List<Integer> destinations) {
        Map<Integer, PathResult> shortestPaths = solver.findShortestPaths(vertexes, 1, destinations);
        String result = getResultList(shortestPaths, destinations);
        return new Result(shortestPaths, result);
    }

    static class Result {
        public final Map<Integer, PathResult> shortestPaths;
        public final String result;

        public Result(Map<Integer, PathResult> shortestPaths, String result) {
            this.shortestPaths = shortestPaths;
            this.result = result;
        }
    }

    public static String getResultList(Map<Integer, PathResult> shortestPaths, List<Integer> destinations) {
        return destinations.stream()
                .map(shortestPaths::get)
                .filter(Objects::nonNull)
                .map(node -> node.distance)
                .map(String::valueOf)
                .collect(joining(","));
    }

    private static String readOutputsFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<Integer, List<Integer>> readPathsFromFile(String fileName, List<Integer> searched) {
        Map<Integer, List<Integer>> paths = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.trim().split("=>");
                int hubNumber = Integer.parseInt(row[0].trim());
                if (searched.contains(hubNumber)) {
                    paths.put(
                            hubNumber,
                            Arrays.stream(row[2].trim().split(","))
                                    .map(String::trim)
                                    .map((Integer::valueOf))
                                    .collect(Collectors.toList())
                    );
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return paths;
    }

    private static Map<Integer, Vertex> readInputFromFile(String fileName) {
        Map<Integer, Vertex> hubs = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.trim().split("\\s+");
                int hubNumber = Integer.parseInt(tokens[0]);
                Vertex vertex = new Vertex(hubNumber);
                getVertexesWithMinLength(tokens).forEach((neighbor, length) -> vertex.addVertice(new Edge(neighbor, length)));
                hubs.put(hubNumber, vertex);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return hubs;
    }

    private static Map<Integer, Integer> getVertexesWithMinLength(String[] tokens) {
        Map<Integer, Integer> duplicates = new HashMap<>();

        for (int i = 1; i < tokens.length; i++) {
            String[] vertex = tokens[i].split(",");
            int neighbor = Integer.parseInt(vertex[0]);
            int length = Integer.parseInt(vertex[1]);

            Integer storedLength = duplicates.get(neighbor);
            if (storedLength == null) {
                duplicates.put(neighbor, length);
            } else if (length < storedLength) {
                duplicates.put(neighbor, length);
            }
        }
        return duplicates;
    }
}
