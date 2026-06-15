package ejer3;

public class Vertex<E> {
    private E data;

    public Vertex(E data) {
        this.data = data;
    }

    public E getData() { return data; }

    public void setData(E data) { this.data = data; }

    @Override
    public String toString() { return data.toString(); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vertex)) return false;
        Vertex<?> other = (Vertex<?>) obj;
        return data.equals(other.data);
    }
}