package graph;

import listaenlasada.ListLinked;
import listaenlasada.Node;

import actividad1.QueueArray;
import actividad1.ExceptionIsEmpty;
import ejer1.StackLink;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GraphLink<E>{
    private ListLinked<AdjList<E>> graph;

    public GraphLink() {
        graph = new ListLinked<>();
    }
    
    // Inserta un vertice si no existe previamente
    public void insertVertex(E data) {
        if (findVertex(data) == null) {
            Vertex<E> v = new Vertex<>(data);
            graph.insertLast(new AdjList<>(v));
        }
    }

    // Busca y retorna la lista de adyacencia del vertice con el dato dado
    private AdjList<E> findVertex(E data) {
        Node<AdjList<E>> current = graph.getFirst();
        while (current != null) {
            if (current.getValue().getVertex().getData().equals(data))
                return current.getValue();
            current = current.getNext();
        }
        return null;
    }

    // Inserta arista no dirigida entre origin y destination
    public void insertEdge(E origin, E destination) {
        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);
        if (v1 == null || v2 == null) return;
        v1.getEdges().insertLast(new Edge<>(v2.getVertex()));
        v2.getEdges().insertLast(new Edge<>(v1.getVertex()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<AdjList<E>> current = graph.getFirst();
        while (current != null) {
            AdjList<E> adj = current.getValue();
            sb.append(adj.getVertex()).append(" -> ");
            Node<Edge<E>> edge = adj.getEdges().getFirst();
            while (edge != null) {
                sb.append(edge.getValue()).append(" ");
                edge = edge.getNext();
            }
            sb.append("\n");
            current = current.getNext();
        }
        return sb.toString();
    }
    
 // Inserta arista ponderada no dirigida entre v y z con peso w
    public void insertEdgeWeight(E v, E z, int w) {
        AdjList<E> adjV = findVertex(v);
        AdjList<E> adjZ = findVertex(z);
        if (adjV == null || adjZ == null) return;
        adjV.getEdges().insertLast(new Edge<>(adjZ.getVertex(), w));
        adjZ.getEdges().insertLast(new Edge<>(adjV.getVertex(), w));
    }

    // Devuelve la ruta mas corta (sin pesos, BFS) entre v y z en un ArrayList
    public ArrayList<E> shortPath(E v, E z) {
        if (findVertex(v) == null || findVertex(z) == null) return null;
        // BFS con registro de predecesores
        Map<E, E> parent = new HashMap<>();
        Map<E, Boolean> visited = new HashMap<>();
        QueueArray<E> queue = new QueueArray<>(100);
        queue.enqueue(v);
        visited.put(v, true);
        parent.put(v, null);
        boolean found = false;
        while (!queue.isEmpty() && !found) {
            E current;
            try { current = queue.dequeue(); } catch (ExceptionIsEmpty e) { break; }
            if (current.equals(z)) { found = true; break; }
            AdjList<E> adj = findVertex(current);
            Node<Edge<E>> edge = adj.getEdges().getFirst();
            while (edge != null) {
                E neighbor = edge.getValue().getDestination().getData();
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, true);
                    parent.put(neighbor, current);
                    queue.enqueue(neighbor);
                }
                edge = edge.getNext();
            }
        }
        if (!found) return null;
        // Reconstruir camino
        ArrayList<E> path = new ArrayList<>();
        E step = z;
        while (step != null) {
            path.add(0, step);
            step = parent.get(step);
        }
        return path;
    }

    // Devuelve true si el grafo es conexo (todos los vertices son alcanzables desde el primero)
    public boolean isConexo() {
        if (graph.getFirst() == null) return true;
        E start = graph.getFirst().getValue().getVertex().getData();
        Map<E, Boolean> visited = new HashMap<>();
        QueueArray<E> queue = new QueueArray<>(100);
        queue.enqueue(start);
        visited.put(start, true);
        while (!queue.isEmpty()) {
            E current;
            try { current = queue.dequeue(); } catch (ExceptionIsEmpty e) { break; }
            AdjList<E> adj = findVertex(current);
            Node<Edge<E>> edge = adj.getEdges().getFirst();
            while (edge != null) {
                E neighbor = edge.getValue().getDestination().getData();
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, true);
                    queue.enqueue(neighbor);
                }
                edge = edge.getNext();
            }
        }
        // Verificar que todos los vertices fueron visitados
        Node<AdjList<E>> curr = graph.getFirst();
        while (curr != null) {
            if (!visited.containsKey(curr.getValue().getVertex().getData())) return false;
            curr = curr.getNext();
        }
        return true;
    }

    // Dijkstra: retorna un StackLink con la ruta mas corta de v a w
    public StackLink<E> dijkstra(E v, E w) {
        // Recopilar todos los vertices en una lista
        ArrayList<E> vertices = new ArrayList<>();
        Node<AdjList<E>> curr = graph.getFirst();
        while (curr != null) {
            vertices.add(curr.getValue().getVertex().getData());
            curr = curr.getNext();
        }
        int n = vertices.size();
        Map<E, Integer> dist = new HashMap<>();
        Map<E, E> prev = new HashMap<>();
        Map<E, Boolean> processed = new HashMap<>();
        for (E vertex : vertices) {
            dist.put(vertex, Integer.MAX_VALUE);
            prev.put(vertex, null);
            processed.put(vertex, false);
        }
        dist.put(v, 0);
        // Proceso principal de Dijkstra
        for (int i = 0; i < n; i++) {
            // Seleccionar el vertice no procesado con menor distancia
            E u = null;
            for (E vertex : vertices) {
                if (!processed.get(vertex) &&
                    (u == null || dist.get(vertex) < dist.get(u))) {
                    u = vertex;
                }
            }
            if (u == null || dist.get(u) == Integer.MAX_VALUE) break;
            processed.put(u, true);
            if (u.equals(w)) break;
            // Actualizar distancias de vecinos
            AdjList<E> adj = findVertex(u);
            Node<Edge<E>> edge = adj.getEdges().getFirst();
            while (edge != null) {
                E neighbor = edge.getValue().getDestination().getData();
                int newDist = dist.get(u) + edge.getValue().getWeight();
                if (newDist < dist.get(neighbor)) {
                    dist.put(neighbor, newDist);
                    prev.put(neighbor, u);
                }
                edge = edge.getNext();
            }
        }
        // Reconstruir ruta en un StackLink
        StackLink<E> stack = new StackLink<>();
        E step = w;
        while (step != null) {
            stack.push(step);
            step = prev.get(step);
        }
        System.out.println("Distancia minima de " + v + " a " + w
                + ": " + dist.get(w));
        return stack;
    }
}