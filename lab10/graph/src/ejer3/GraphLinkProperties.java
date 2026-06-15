package ejer3;

import listaenlasada.Node;
import graph.AdjList;
import graph.Edge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphLinkProperties<V> extends GraphLink<V> {

    // Retorna el número de vértices del grafo
    public int countVertices() {

        int count = 0;

        Node<AdjList<V>> cur = adjLists.getFirst();

        while (cur != null) {
            count++;
            cur = cur.getNext();
        }

        return count;
    }

    // Retorna el número de aristas del grafo
    // (contando cada una una sola vez)
    public int countEdges() {

        int total = 0;

        Node<AdjList<V>> cur = adjLists.getFirst();

        while (cur != null) {

            Node<Edge<V>> e =
                    cur.getValue()
                            .getEdges()
                            .getFirst();

            while (e != null) {
                total++;
                e = e.getNext();
            }

            cur = cur.getNext();
        }

        // Grafo no dirigido: cada arista aparece dos veces
        return total / 2;
    }

    // Retorna el grado de cada vértice
    public Map<V, Integer> degreeMap() {

        Map<V, Integer> degrees =
                new HashMap<>();

        Node<AdjList<V>> cur =
                adjLists.getFirst();

        while (cur != null) {

            int degree = 0;

            Node<Edge<V>> e =
                    cur.getValue()
                            .getEdges()
                            .getFirst();

            while (e != null) {
                degree++;
                e = e.getNext();
            }

            degrees.put(
                    cur.getValue()
                            .getVertex()
                            .getData(),
                    degree
            );

            cur = cur.getNext();
        }

        return degrees;
    }

    /**
     * Verifica condición necesaria
     * de isomorfismo
     */
    public boolean isomorfo(
            GraphLinkProperties<V> other
    ) {

        if (this.countVertices()
                != other.countVertices()) {
            return false;
        }

        if (this.countEdges()
                != other.countEdges()) {
            return false;
        }

        List<Integer> seq1 =
                new ArrayList<>(
                        this.degreeMap().values()
                );

        List<Integer> seq2 =
                new ArrayList<>(
                        other.degreeMap().values()
                );

        Collections.sort(seq1);
        Collections.sort(seq2);

        return seq1.equals(seq2);
    }

    /**
     * Verifica condición de Euler
     * para planaridad
     */
    public boolean isPlano() {

        int V = countVertices();
        int E = countEdges();

        if (V < 3) {
            return true;
        }

        return E <= (3 * V - 6);
    }

    /**
     * Verifica si es
     * autocomplementario
     */
    public boolean autoComplementario() {

        // Obtener todos los vértices
        List<V> vertices =
                new ArrayList<>();

        Node<AdjList<V>> cur =
                adjLists.getFirst();

        while (cur != null) {

            vertices.add(
                    cur.getValue()
                            .getVertex()
                            .getData()
            );

            cur = cur.getNext();
        }

        // Construir complemento
        GraphLinkProperties<V>
                complemento =
                new GraphLinkProperties<>();

        for (V v : vertices) {
            complemento.insertVertex(v);
        }

        for (int i = 0;
             i < vertices.size();
             i++) {

            for (int j = i + 1;
                 j < vertices.size();
                 j++) {

                V u = vertices.get(i);
                V w = vertices.get(j);

                if (!this.searchEdge(u, w)) {
                    complemento.insertEdge(
                            u, w, 1
                    );
                }
            }
        }

        // Comparar secuencia
        // de grados
        return this.isomorfo(
                complemento
        );
    }
}