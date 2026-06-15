package ejer3;

public class TestProperties {
    public static void main(String[] args) {
        // Grafo 1: ciclo C4
        GraphLinkProperties<Integer> g1 = new GraphLinkProperties<>();
        for (int i = 1; i <= 4; i++) g1.insertVertex(i);
        g1.insertEdge(1, 2, 1); g1.insertEdge(2, 3, 1);
        g1.insertEdge(3, 4, 1); g1.insertEdge(4, 1, 1);

        // Grafo 2: otro ciclo C4 (diferente etiquetado)
        GraphLinkProperties<Integer> g2 = new GraphLinkProperties<>();
        for (int i = 5; i <= 8; i++) g2.insertVertex(i);
        g2.insertEdge(5, 6, 1); g2.insertEdge(6, 7, 1);
        g2.insertEdge(7, 8, 1); g2.insertEdge(8, 5, 1);

        System.out.println("G1 isomorfo a G2: " + g1.isomorfo(g2));
        System.out.println("G1 es plano: " + g1.isPlano());
        System.out.println("G1 es conexo: " + g1.isConexo());
        System.out.println("G1 es autocomplementario: "
                + g1.autoComplementario());

        // Grafo K5 (no plano: 10 aristas > 3*5-6=9)
        GraphLinkProperties<Integer> k5 = new GraphLinkProperties<>();
        for (int i = 1; i <= 5; i++) k5.insertVertex(i);
        for (int i = 1; i <= 5; i++)
            for (int j = i + 1; j <= 5; j++)
                k5.insertEdge(i, j, 1);
        System.out.println("\nK5 es plano: " + k5.isPlano());
        System.out.println("K5 aristas: " + k5.countEdges()
                + " (limite: " + (3*5-6) + ")");
    }
}