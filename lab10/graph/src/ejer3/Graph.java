package ejer3;

import java.util.List;

public interface Graph<E> {
    // Insertar un vertice con el dato data
    void insertVertex(E data);

    // Insertar una arista (no dirigida) entre origin y destination con peso weight
    void insertEdge(E origin, E destination, int weight);

    // Eliminar el vertice con el dato data y todas sus aristas
    boolean removeVertex(E data);

    // Eliminar la arista entre origin y destination
    boolean removeEdge(E origin, E destination);

    // Buscar un vertice; retorna true si existe
    boolean searchVertex(E data);

    // Buscar una arista entre origin y destination; retorna true si existe
    boolean searchEdge(E origin, E destination);

    // Retorna la lista de vertices adyacentes al vertice data
    List<E> adjacentVertices(E data);

    // Retorna true si el grafo es conexo
    boolean isConexo();

    // Dijkstra: retorna la lista con la ruta de menor costo de src a dst
    List<E> dijkstra(E src, E dst);
}