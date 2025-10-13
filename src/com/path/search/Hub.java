package com.path.search;

import java.util.HashSet;
import java.util.Set;

public class Hub {
    private final int number;
    private final Set<Vertex> vertices = new HashSet<>();

    public Hub(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public Set<Vertex> getVertices() {
        return vertices;
    }

    public void addVertice(Vertex vertice) {
        vertices.add(vertice);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Hub hub = (Hub) o;
        return number == hub.number && vertices.equals(hub.vertices);
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + vertices.hashCode();
        return result;
    }
}
