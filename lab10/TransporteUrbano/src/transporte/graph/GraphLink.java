package transporte.graph;

import transporte.structures.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphLink<V> implements Graph<V> {

    protected ListLinked<AdjList<V>> adjLists;

    public GraphLink() {
        adjLists = new ListLinked<>();
    }
    
    public ListLinked<AdjList<V>> getAdjLists() {
    	return this.adjLists;
    }

    public AdjList<V> findAdj(V data) {
        Node<AdjList<V>> curr = adjLists.getFirst();

        while (curr != null) {
            if (curr.getValue().getVertex().getData().equals(data)) {
                return curr.getValue();
            }
            curr = curr.getNext();
        }

        return null;
    }

    @Override
    public void insertVertex(V data) {
        if (findVertex(data) == null){
            Vertex<V> v = new Vertex<>(data);
            adjLists.insertLast(new AdjList<>(v));
        }
    }
    
    private AdjList<V> findVertex(V data) {
        Node<AdjList<V>> current = adjLists.getFirst();
        while (current != null) {
            if (current.getValue().getVertex().getData().equals(data))
                return current.getValue();
            current = current.getNext();
        }
        return null;
    }

    @Override
    public void insertEdge(V origin, V destination, int weight) {
        AdjList<V> a = findAdj(origin);
        AdjList<V> b = findAdj(destination);

        if (a == null || b == null) return;

        a.getEdges().insertLast(new Edge<>(b.getVertex(), weight));
        b.getEdges().insertLast(new Edge<>(a.getVertex(), weight));
    }

    @Override
    public boolean removeVertex(V data) {

        AdjList<V> adj = findAdj(data);

        if (adj == null) return false;

        // Eliminar aristas que apuntan al vértice
        Node<AdjList<V>> curr = adjLists.getFirst();

        while (curr != null) {
            removeEdgeFrom(curr.getValue(), data);
            curr = curr.getNext();
        }

        adjLists.removeNode(adj);

        return true;
    }

    @Override
    public boolean removeEdge(V origin, V destination) {

        AdjList<V> adjA = findAdj(origin);
        AdjList<V> adjB = findAdj(destination);

        if (adjA == null || adjB == null) {
            return false;
        }

        boolean r1 = removeEdgeFrom(adjA, destination);
        boolean r2 = removeEdgeFrom(adjB, origin);

        return r1 && r2;
    }

    private boolean removeEdgeFrom(AdjList<V> adj, V dest) {

        Node<Edge<V>> curr = adj.getEdges().getFirst();
        Node<Edge<V>> prev = null;

        while (curr != null) {

            if (curr.getValue().getDestination().getData().equals(dest)) {

                if (prev == null) {
                    adj.getEdges().removeFirst();
                } else {
                    prev.setNext(curr.getNext());
                }

                return true;
            }

            prev = curr;
            curr = curr.getNext();
        }

        return false;
    }

    @Override
    public boolean searchVertex(V data) {
        return findAdj(data) != null;
    }

    @Override
    public boolean searchEdge(V origin, V destination) {

        AdjList<V> adj = findAdj(origin);

        if (adj == null) return false;

        Node<Edge<V>> curr = adj.getEdges().getFirst();

        while (curr != null) {

            if (curr.getValue().getDestination().getData().equals(destination)) {
                return true;
            }

            curr = curr.getNext();
        }

        return false;
    }

    @Override
    public List<V> adjacentVertices(V data) {

        List<V> result = new ArrayList<>();

        AdjList<V> adj = findAdj(data);

        if (adj == null) return result;

        Node<Edge<V>> curr = adj.getEdges().getFirst();

        while (curr != null) {
            result.add(curr.getValue().getDestination().getData());
            curr = curr.getNext();
        }

        return result;
    }

    @Override
    public boolean isConexo() {

        if (adjLists.getFirst() == null) {
            return true;
        }

        V start = adjLists.getFirst()
                .getValue()
                .getVertex()
                .getData();

        Map<V, Boolean> visited = new HashMap<>();
        QueueArray<V> queue = new QueueArray<>(200);

        queue.enqueue(start);
        visited.put(start, true);

        while (!queue.isEmpty()) {

            V cur;

            try {
                cur = queue.dequeue();
            } catch (ExceptionIsEmpty e) {
                break;
            }

            for (V n : adjacentVertices(cur)) {

                if (!visited.containsKey(n)) {
                    visited.put(n, true);
                    queue.enqueue(n);
                }
            }
        }

        Node<AdjList<V>> curr = adjLists.getFirst();

        while (curr != null) {

            if (!visited.containsKey(
                    curr.getValue().getVertex().getData())) {
                return false;
            }

            curr = curr.getNext();
        }

        return true;
    }

    @Override
    public List<V> dijkstra(V src, V dst) {

        List<V> vertices = new ArrayList<>();

        Node<AdjList<V>> cur = adjLists.getFirst();

        while (cur != null) {
            vertices.add(cur.getValue().getVertex().getData());
            cur = cur.getNext();
        }

        Map<V, Integer> dist = new HashMap<>();
        Map<V, V> prev = new HashMap<>();
        Map<V, Boolean> done = new HashMap<>();

        for (V v : vertices) {
            dist.put(v, Integer.MAX_VALUE);
            prev.put(v, null);
            done.put(v, false);
        }

        dist.put(src, 0);

        for (int i = 0; i < vertices.size(); i++) {

            V u = null;

            for (V v : vertices) {
                if (!done.get(v)
                        && (u == null || dist.get(v) < dist.get(u))) {
                    u = v;
                }
            }

            if (u == null ||
                    dist.get(u) == Integer.MAX_VALUE) {
                break;
            }

            done.put(u, true);

            if (u.equals(dst)) {
                break;
            }

            AdjList<V> adj = findAdj(u);

            Node<Edge<V>> edge =
                    adj.getEdges().getFirst();

            while (edge != null) {

                V nb = edge.getValue()
                        .getDestination()
                        .getData();

                int nd = dist.get(u)
                        + edge.getValue().getWeight();

                if (nd < dist.get(nb)) {
                    dist.put(nb, nd);
                    prev.put(nb, u);
                }

                edge = edge.getNext();
            }
        }

        List<V> path = new ArrayList<>();

        V step = dst;

        while (step != null) {
            path.add(0, step);
            step = prev.get(step);
        }

        if (path.isEmpty()
                || !path.get(0).equals(src)) {
            return new ArrayList<>();
        }

        System.out.println(
                "Costo Dijkstra de "
                        + src + " a "
                        + dst + ": "
                        + dist.get(dst)
        );

        return path;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        Node<AdjList<V>> curr =
                adjLists.getFirst();

        while (curr != null) {

            AdjList<V> adj =
                    curr.getValue();

            sb.append(adj.getVertex())
                    .append(" -> ");

            Node<Edge<V>> edge =
                    adj.getEdges().getFirst();

            while (edge != null) {

                sb.append(edge.getValue()
                        .getDestination())
                        .append("(")
                        .append(edge.getValue()
                                .getWeight())
                        .append(") ");

                edge = edge.getNext();
            }

            sb.append("\n");

            curr = curr.getNext();
        }

        return sb.toString();
    }
}