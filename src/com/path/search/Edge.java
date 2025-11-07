package com.path.search;

public class Edge {
    private final int number;
    private final int length;

    public Edge(int number, int length) {
        this.number = number;
        this.length = length;
    }

    public int getNumber() {
        return number;
    }

    public int getLength() {
        return length;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;
        return number == edge.number && length == edge.length;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + length;
        return result;
    }
}
