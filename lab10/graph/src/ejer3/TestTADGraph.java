package ejer3;

public class TestTADGraph {
    public static void main(String[] args) {
        GraphLink<String> g = new GraphLink<>();
        g.insertVertex("A"); g.insertVertex("B");
        g.insertVertex("C"); g.insertVertex("D");
        g.insertVertex("E");
        g.insertEdge("A", "B", 4); g.insertEdge("A", "C", 2);
        g.insertEdge("B", "D", 5); g.insertEdge("C", "D", 1);
        g.insertEdge("D", "E", 3);
        System.out.println("Grafo:\n" + g);
        System.out.println("searchVertex(C): " + g.searchVertex("C"));
        System.out.println("searchEdge(A,B): " + g.searchEdge("A", "B"));
        System.out.println("adjacentVertices(A): " + g.adjacentVertices("A"));
        System.out.println("isConexo(): " + g.isConexo());
        System.out.println("dijkstra(A->E): " + g.dijkstra("A", "E"));
        g.removeEdge("A", "B");
        System.out.println("Despues de removeEdge(A,B):\n" + g);
        g.removeVertex("D");
        System.out.println("Despues de removeVertex(D):\n" + g);
    }
}