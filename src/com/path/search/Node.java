package com.path.search;

import java.util.List;
import java.util.Objects;

public class Node implements Comparable<Node> {
    final int hubNumber;
    final int distance;
    final List<Integer> path;

    Node(int hubNumber, int distance, List<Integer> path) {
        this.hubNumber = hubNumber;
        this.distance = distance;
        this.path = path;
    }

    @Override
    public int compareTo(Node other) {
        int cmp = Integer.compare(this.distance, other.distance);
        if (cmp != 0) return cmp;
        return Integer.compare(this.hubNumber, other.hubNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return hubNumber == node.hubNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hubNumber);
    }
}