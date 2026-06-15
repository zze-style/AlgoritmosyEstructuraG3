package graph;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.List;
import java.util.Set;

public class RedCiudadesPeru {

    public static void main(String[] args) {
        // Crear grafo ponderado no dirigido
        Graph<String, DefaultWeightedEdge> grafo =
                new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        // Agregar ciudades (vertices)
        String[] ciudades = {"Arequipa", "Cusco", "Puno",
                             "Tacna", "Moquegua"};
        for (String ciudad : ciudades) {
            grafo.addVertex(ciudad);
        }

        // Agregar carreteras con distancias en km (aristas ponderadas)
        agregarCarretera(grafo, "Arequipa", "Cusco",   510);
        agregarCarretera(grafo, "Arequipa", "Moquegua", 230);
        agregarCarretera(grafo, "Moquegua", "Tacna",   160);
        agregarCarretera(grafo, "Cusco",    "Puno",    390);
        agregarCarretera(grafo, "Puno",     "Tacna",   420);

        // Mostrar lista de ciudades
        System.out.println("=== CIUDADES ===");
        Set<String> verts = grafo.vertexSet();
        for (String c : verts) System.out.println(" - " + c);

        // Mostrar carreteras registradas
        System.out.println("\n=== CARRETERAS ===");
        Set<DefaultWeightedEdge> aristas = grafo.edgeSet();
        for (DefaultWeightedEdge e : aristas) {
            System.out.printf(" %s <--> %s : %.0f km%n",
                    grafo.getEdgeSource(e),
                    grafo.getEdgeTarget(e),
                    grafo.getEdgeWeight(e));
        }

        // Camino mas corto Arequipa -> Tacna con Dijkstra de JGraphT
        System.out.println("\n=== CAMINO MAS CORTO: Arequipa -> Tacna ===");
        DijkstraShortestPath<String, DefaultWeightedEdge> dsp =
                new DijkstraShortestPath<>(grafo);
        var path = dsp.getPath("Arequipa", "Tacna");
        if (path == null) {
            System.out.println("No hay camino.");
        } else {
            List<String> ruta = path.getVertexList();
            System.out.println("Ruta: " + ruta);
            System.out.printf("Costo total: %.0f km%n", path.getWeight());
        }

        // Camino mas corto Cusco -> Tacna
        System.out.println("\n=== CAMINO MAS CORTO: Cusco -> Tacna ===");
        var path2 = dsp.getPath("Cusco", "Tacna");
        if (path2 == null) {
            System.out.println("No hay camino.");
        } else {
            System.out.println("Ruta: " + path2.getVertexList());
            System.out.printf("Costo total: %.0f km%n", path2.getWeight());
        }
    }

    private static void agregarCarretera(
            Graph<String, DefaultWeightedEdge> g,
            String origen, String destino, double distancia) {
        DefaultWeightedEdge e = g.addEdge(origen, destino);
        if (e != null) g.setEdgeWeight(e, distancia);
        System.out.println("[+] Carretera: " + origen
                + " -- " + destino + " (" + distancia + " km)");
    }
}