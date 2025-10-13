package com.path.search;

public class Vertex {
    private final int neighbor;
    private final int length;

    public Vertex(int neighbor, int length) {
        this.neighbor = neighbor;
        this.length = length;
    }

    public int getNeighbor() {
        return neighbor;
    }

    public int getLength() {
        return length;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;
        return neighbor == vertex.neighbor && length == vertex.length;
    }

    @Override
    public int hashCode() {
        int result = neighbor;
        result = 31 * result + length;
        return result;
    }
}
