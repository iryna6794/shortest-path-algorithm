package com.path.search;

import java.util.List;
import java.util.Map;

public interface Solver {
    Map<Integer, PathResult> findShortestPaths(Map<Integer, Vertex> hubs, int number, List<Integer> searchedHubs);
}
