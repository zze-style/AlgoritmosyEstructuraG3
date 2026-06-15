package transporte.graph;

public class Edge<E> {
    protected Vertex<E> destination;
    private int weight;

    public Edge(Vertex<E> destination) {
        this(destination, 1);
    }

    public Edge(Vertex<E> destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex<E> getDestination() { return destination; }
    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }

    @Override
    public String toString() { return destination.toString(); }
}