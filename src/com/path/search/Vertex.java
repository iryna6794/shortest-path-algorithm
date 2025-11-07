package com.path.search;

import java.util.HashSet;
import java.util.Set;

public class Vertex {
    private final int number;
    private final Set<Edge> neighbours = new HashSet<>();

    public Vertex(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public Set<Edge> getNeighbours() {
        return neighbours;
    }

    public void addVertice(Edge vertice) {
        neighbours.add(vertice);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;
        return number == vertex.number && neighbours.equals(vertex.neighbours);
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + neighbours.hashCode();
        return result;
    }
}
