package com.path.search;

import java.util.List;
import java.util.Map;

public interface Solver {
    Map<Integer, Node> findShortestPaths(Map<Integer, Hub> hubs, int source, List<Integer> searchedHubs);
}
