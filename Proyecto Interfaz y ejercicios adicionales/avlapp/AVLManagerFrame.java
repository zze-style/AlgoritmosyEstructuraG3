package avlapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class AVLManagerFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private final Map<String, AVLManager> managers = new LinkedHashMap<>();
    private final JComboBox<String> domainCombo;
    private final JTextField valueField = new JTextField(8);
    private final JTextArea outputArea = new JTextArea(12, 60);
    private final AVLTreePanel treePanel = new AVLTreePanel();

    public AVLManagerFrame() {
        super("Gestor AVL - tickets, productos y turnos");
        managers.put("Tickets", new AVLManager("Ticket"));
        managers.put("Productos", new AVLManager("Producto"));
        managers.put("Turnos de clinica", new AVLManager("Turno"));

        domainCombo = new JComboBox<>(managers.keySet().toArray(new String[0]));
        outputArea.setEditable(false);
        outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));

        setLayout(new BorderLayout(10, 10));
        setContentPane(buildContent());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(980, 720);
        setLocationRelativeTo(null);
        refreshReport();
    }

    private JPanel buildContent() {
        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        root.setBackground(new Color(241, 245, 249));

        root.add(buildHeader(), BorderLayout.NORTH);
        root.add(new JScrollPane(treePanel), BorderLayout.CENTER);
        root.add(buildOutput(), BorderLayout.SOUTH);
        return root;
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new GridLayout(2, 1, 6, 6));
        header.setOpaque(false);

        JLabel statement = new JLabel("Sistema AVL para administrar codigos unicos con insercion, busqueda, eliminacion, recorridos y comparacion BST vs AVL.");
        statement.setFont(statement.getFont().deriveFont(Font.BOLD, 14f));

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        controls.setOpaque(false);
        controls.add(new JLabel("Modulo:"));
        controls.add(domainCombo);
        controls.add(new JLabel("Codigo:"));
        controls.add(valueField);
        controls.add(button("Insertar", this::insertValue));
        controls.add(button("Buscar", this::searchValue));
        controls.add(button("Eliminar", this::deleteValue));
        controls.add(button("Recorridos", this::refreshReport));
        controls.add(button("Demo PDF", this::loadPdfDemo));
        controls.add(button("Comparar", this::compareTrees));

        domainCombo.addActionListener(event -> refreshReport());
        header.add(statement);
        header.add(controls);
        return header;
    }

    private JScrollPane buildOutput() {
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Resultado"));
        return scrollPane;
    }

    private JButton button(String text, Runnable action) {
        JButton button = new JButton(text);
        button.addActionListener(event -> action.run());
        return button;
    }

    private void insertValue() {
        runWithCode(code -> currentManager().insert(code));
    }

    private void searchValue() {
        runWithCode(code -> currentManager().search(code));
    }

    private void deleteValue() {
        runWithCode(code -> currentManager().delete(code));
    }

    private void runWithCode(Operation operation) {
        try {
            int code = Integer.parseInt(valueField.getText().trim());
            outputArea.setText(operation.execute(code) + "\n\n" + currentManager().report());
            treePanel.setTree(currentManager().getTree());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un numero entero valido.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void refreshReport() {
        AVLManager manager = currentManager();
        outputArea.setText(manager.report());
        treePanel.setTree(manager.getTree());
    }

    private void loadPdfDemo() {
        int[] values = {30, 10, 20, 40, 50, 25};
        AVLManager manager = currentManager();
        StringBuilder log = new StringBuilder("Demo de la secuencia del PDF:\n");
        for (int value : values) {
            if (!manager.getTree().buscar(value)) {
                log.append(manager.insert(value)).append('\n');
            }
        }
        log.append("\n").append(manager.search(20));
        log.append("\n").append(manager.search(60));
        int[] deletes = {10, 40, 30};
        for (int value : deletes) {
            log.append('\n').append(manager.delete(value));
        }
        log.append("\n\n").append(manager.report());
        outputArea.setText(log.toString());
        treePanel.setTree(manager.getTree());
    }

    private void compareTrees() {
        int[] ascending = {10, 20, 30, 40, 50, 60, 70};
        int[] descending = {70, 60, 50, 40, 30, 20, 10};
        outputArea.setText("Comparacion con orden ascendente\n"
                + ComparisonResult.compare(ascending)
                + "\n\nComparacion con orden descendente\n"
                + ComparisonResult.compare(descending));
    }

    private AVLManager currentManager() {
        return managers.get((String) domainCombo.getSelectedItem());
    }

    public static void showWindow() {
        SwingUtilities.invokeLater(() -> new AVLManagerFrame().setVisible(true));
    }

    private interface Operation {
        String execute(int code);
    }
}
