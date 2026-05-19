package avlapp;

import arboles.AVLTree;
import arboles.Nodo;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class AVLTreePanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private AVLTree<Integer> tree;

    public AVLTreePanel() {
        setBackground(new Color(248, 250, 252));
        setPreferredSize(new Dimension(860, 360));
    }

    public void setTree(AVLTree<Integer> tree) {
        this.tree = tree;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics.create();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (tree == null || tree.getRaiz() == null) {
            g.setColor(new Color(71, 85, 105));
            g.drawString("Arbol vacio", 24, 32);
            g.dispose();
            return;
        }

        drawNode(g, tree.getRaiz(), getWidth() / 2, 42, Math.max(getWidth() / 4, 80));
        g.dispose();
    }

    private void drawNode(Graphics2D g, Nodo<Integer> node, int x, int y, int gap) {
        if (node.getIzquierdo() != null) {
            int childX = x - gap;
            int childY = y + 72;
            drawEdge(g, x, y, childX, childY);
            drawNode(g, node.getIzquierdo(), childX, childY, Math.max(gap / 2, 32));
        }
        if (node.getDerecho() != null) {
            int childX = x + gap;
            int childY = y + 72;
            drawEdge(g, x, y, childX, childY);
            drawNode(g, node.getDerecho(), childX, childY, Math.max(gap / 2, 32));
        }

        int radius = 24;
        g.setColor(new Color(14, 116, 144));
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        g.setColor(new Color(8, 47, 73));
        g.setStroke(new BasicStroke(2f));
        g.drawOval(x - radius, y - radius, radius * 2, radius * 2);

        String text = String.valueOf(node.getDato());
        FontMetrics metrics = g.getFontMetrics();
        int textX = x - metrics.stringWidth(text) / 2;
        int textY = y + metrics.getAscent() / 2 - 3;
        g.setColor(Color.WHITE);
        g.drawString(text, textX, textY);
    }

    private void drawEdge(Graphics2D g, int x1, int y1, int x2, int y2) {
        g.setColor(new Color(100, 116, 139));
        g.setStroke(new BasicStroke(2f));
        g.drawLine(x1, y1 + 24, x2, y2 - 24);
    }
}
