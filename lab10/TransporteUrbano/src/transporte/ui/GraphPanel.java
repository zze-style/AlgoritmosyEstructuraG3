package transporte.ui;

import transporte.graph.AdjList;
import transporte.graph.Edge;
import transporte.graph.GraphLink;
import transporte.structures.Node;

import javax.swing.JPanel;
import java.awt.*;
import java.util.*;

class GraphPanel extends JPanel {

    private GraphLink<String> graph;

    GraphPanel() {
        setBackground(new Color(248, 250, 252));
        setPreferredSize(new Dimension(600, 400));
    }

    void setGraph(GraphLink<String> g) {
        this.graph = g;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);

        if (graph == null) return;

        Graphics2D g = (Graphics2D) g0.create();

        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        // Obtener vértices
        java.util.List<String> verts = new ArrayList<>();

        Node<AdjList<String>> cur =
                graph.getAdjLists().getFirst();

        while (cur != null) {

            verts.add(
                    cur.getValue()
                            .getVertex()
                            .getData()
            );

            cur = cur.getNext();
        }

        int n = verts.size();

        Map<String, Point> pos = new HashMap<>();

        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        int r = 130;

        // Posiciones circulares
        for (int i = 0; i < n; i++) {

            double angle =
                    2 * Math.PI * i / n
                            - Math.PI / 2;

            pos.put(
                    verts.get(i),
                    new Point(
                            (int) (cx + r * Math.cos(angle)),
                            (int) (cy + r * Math.sin(angle))
                    )
            );
        }

        // Dibujar aristas
        Set<String> drawn = new HashSet<>();

        cur = graph.getAdjLists().getFirst();

        while (cur != null) {

            String u = cur.getValue()
                    .getVertex()
                    .getData();

            Node<Edge<String>> e =
                    cur.getValue()
                            .getEdges()
                            .getFirst();

            while (e != null) {

                String v = e.getValue()
                        .getDestination()
                        .getData();

                String key =
                        u.compareTo(v) < 0
                                ? u + "-" + v
                                : v + "-" + u;

                if (!drawn.contains(key)) {

                    drawn.add(key);

                    Point pu = pos.get(u);
                    Point pv = pos.get(v);

                    g.setColor(
                            new Color(100, 116, 139)
                    );

                    g.setStroke(
                            new BasicStroke(1.5f)
                    );

                    g.drawLine(
                            pu.x, pu.y,
                            pv.x, pv.y
                    );

                    int mx = (pu.x + pv.x) / 2;
                    int my = (pu.y + pv.y) / 2;

                    g.setColor(
                            new Color(239, 68, 68)
                    );

                    g.setFont(
                            new Font(
                                    "Arial",
                                    Font.PLAIN,
                                    10
                            )
                    );

                    g.drawString(
                            e.getValue()
                                    .getWeight() + "m",
                            mx,
                            my
                    );
                }

                e = e.getNext();
            }

            cur = cur.getNext();
        }

        // Dibujar vértices
        for (Map.Entry<String, Point> entry
                : pos.entrySet()) {

            Point p = entry.getValue();

            g.setColor(
                    new Color(30, 64, 175)
            );

            g.fillOval(
                    p.x - 18,
                    p.y - 18,
                    36,
                    36
            );

            g.setColor(Color.WHITE);

            g.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            11
                    )
            );

            FontMetrics fm =
                    g.getFontMetrics();

            String lbl =
                    entry.getKey();

            g.drawString(
                    lbl,
                    p.x - fm.stringWidth(lbl) / 2,
                    p.y + fm.getAscent() / 2 - 1
            );
        }

        g.dispose();
    }
}