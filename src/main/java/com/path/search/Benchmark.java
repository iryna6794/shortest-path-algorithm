package com.path.search;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.path.search.Utils.getSearchPaths;
import static com.path.search.Utils.readInputFromFile;

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
        searchPaths.forEach(
                path -> vertexStorage.add(readInputFromFile(String.format("%s/input_random_%s.txt", ROOT_PATH, path)))
        );
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void benchmarkBFS() {
        vertexStorage.forEach(vertexes -> {
            new BFSSolver().findShortestPaths(vertexes, 1, destinations);
        });
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void benchmarkDijkstra() {
        vertexStorage.forEach(vertexes -> {
            new DijkstraSolver().findShortestPaths(vertexes, 1, destinations);
        });
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void benchmarkDijkstraTreeSet() {
        vertexStorage.forEach(vertexes -> {
            new DijkstraTreeSetSolver().findShortestPaths(vertexes, 1, destinations);
        });
    }
}

