package graph;

import ejer1.ExceptionIsEmpty;
import java.util.ArrayList;

public class TestGraphEjer1 {
    public static void main(String[] args) throws ExceptionIsEmpty {
        GraphLink<String> g = new GraphLink<>();
        g.insertVertex("Arequipa");
        g.insertVertex("Cusco");
        g.insertVertex("Puno");
        g.insertVertex("Moquegua");
        g.insertVertex("Tacna");
        g.insertEdgeWeight("Arequipa", "Cusco", 510);
        g.insertEdgeWeight("Arequipa", "Moquegua", 230);
        g.insertEdgeWeight("Moquegua", "Tacna", 160);
        g.insertEdgeWeight("Cusco", "Puno", 390);
        g.insertEdgeWeight("Puno", "Tacna", 420);
        g.insertEdgeWeight("Arequipa", "Puno", 320);

        System.out.println("Grafo:\n" + g);
        System.out.println("¿Es conexo? " + g.isConexo());

        ArrayList<String> ruta = g.shortPath("Arequipa", "Tacna");
        System.out.println("Ruta BFS (Arequipa->Tacna): " + ruta);

        var stack = g.dijkstra("Arequipa", "Tacna");
        System.out.print("Ruta Dijkstra (Arequipa->Tacna): ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }
}