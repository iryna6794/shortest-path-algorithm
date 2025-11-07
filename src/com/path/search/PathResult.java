package com.path.search;

import java.util.List;
import java.util.Objects;

public class PathResult implements Comparable<PathResult> {
    final int number;
    final int distance;
    final List<Integer> path;

    PathResult(int number, int distance, List<Integer> path) {
        this.number = number;
        this.distance = distance;
        this.path = path;
    }

    @Override
    public int compareTo(PathResult other) {
        int cmp = Integer.compare(this.distance, other.distance);
        if (cmp != 0) return cmp;
        return Integer.compare(this.number, other.number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PathResult pathResult = (PathResult) o;
        return number == pathResult.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}