package biblioteca.ui;

import biblioteca.domain.Libro;
import biblioteca.tree.BNode;

import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

class BTreePanel extends JPanel {
    private BNode<Libro> root;

    BTreePanel() {
        setBackground(new Color(248, 250, 252));
        setPreferredSize(new Dimension(980, 420));
    }

    void setRoot(BNode<Libro> root) {
        this.root = root;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics.create();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (root == null) {
            g.setColor(new Color(100, 116, 139));
            g.drawString("El arbol B esta vacio.", 24, 32);
            g.dispose();
            return;
        }
        drawNode(g, root, getWidth() / 2, 42, Math.max(190, getWidth() / 4));
        g.dispose();
    }

    private void drawNode(Graphics2D g, BNode<Libro> node, int x, int y, int spread) {
        String label = buildLabel(node);
        FontMetrics metrics = g.getFontMetrics();
        int width = Math.max(88, metrics.stringWidth(label) + 24);
        int height = 34;
        int left = x - width / 2;

        List<BNode<Libro>> children = node.getChildren();
        int visibleChildren = 0;
        for (BNode<Libro> child : children) {
            if (child != null) visibleChildren++;
        }
        int childIndex = 0;
        for (BNode<Libro> child : children) {
            if (child == null) continue;
            int childX = x + (childIndex - (visibleChildren - 1) / 2) * spread;
            if (visibleChildren % 2 == 0) {
                childX += spread / 2;
            }
            int childY = y + 86;
            g.setColor(new Color(148, 163, 184));
            g.setStroke(new BasicStroke(1.4f));
            g.drawLine(x, y + height, childX, childY);
            drawNode(g, child, childX, childY, Math.max(72, spread / 2));
            childIndex++;
        }

        g.setColor(new Color(15, 23, 42));
        g.fillRoundRect(left, y, width, height, 8, 8);
        g.setColor(new Color(226, 232, 240));
        g.drawRoundRect(left, y, width, height, 8, 8);
        g.setColor(Color.WHITE);
        g.drawString(label, x - metrics.stringWidth(label) / 2, y + 22);
    }

    private String buildLabel(BNode<Libro> node) {
        StringBuilder builder = new StringBuilder("N").append(node.getId()).append(": ");
        List<Libro> keys = node.getKeys();
        for (int i = 0; i < keys.size(); i++) {
            if (i > 0) builder.append(" | ");
            builder.append(shortIsbn(keys.get(i).getIsbn()));
        }
        return builder.toString();
    }

    private String shortIsbn(String isbn) {
        return isbn.length() <= 5 ? isbn : isbn.substring(isbn.length() - 5);
    }
}
