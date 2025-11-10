package com.path.search;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@State(Scope.Benchmark)
public class Benchmark {
    private static final String ROOT_PATH = "test/testdata";
    private static final List<Map<Integer, Vertex>> vertexStorage = new ArrayList<>();
    private final List<Integer> destinations = Arrays.asList(7, 37, 59, 82, 99, 115, 133, 165, 188, 197);

    static {
        before();
    }

    public static void before() {
        List<String> searchPaths = getSearchPaths();
        searchPaths.forEach(path ->
                vertexStorage.add(readInputFromFile(String.format("%s/input_random_%s.txt", ROOT_PATH, path)))
        );
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void benchmarkBFS() {
        vertexStorage.forEach(vertexes ->
                new BFSSolver().findShortestPaths(vertexes, 1, destinations));
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void benchmarkDijkstra() {
        vertexStorage.forEach(vertexes ->
                new DijkstraSolver().findShortestPaths(vertexes, 1, destinations));
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void benchmarkDijkstraTreeSet() {
        vertexStorage.forEach(vertexes ->
                new DijkstraTreeSetSolver().findShortestPaths(vertexes, 1, destinations));
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

