package biblioteca.ui;

import biblioteca.domain.Libro;
import biblioteca.domain.SolicitudPrestamo;
import biblioteca.service.BibliotecaService;
import biblioteca.structures.ExceptionIsEmpty;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Optional;

public class BibliotecaFrame extends JFrame {
    private final BibliotecaService service;
    private final LibroTableModel tableModel = new LibroTableModel();
    private final BTreePanel treePanel = new BTreePanel();
    private final DefaultListModel<String> queueModel = new DefaultListModel<>();
    private final JTextArea historyArea = new JTextArea();
    private final JLabel statusLabel = new JLabel();

    private JTextField isbnField;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField yearField;
    private JTextField categoryField;
    private JTextField searchField;
    private JTextField rangeFromField;
    private JTextField rangeToField;
    private JTextField studentField;
    private JTextField requestIsbnField;

    public BibliotecaFrame(BibliotecaService service) {
        this.service = service;
        setTitle("Biblioteca universitaria - Indice B-tree");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1120, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));
        add(buildHeader(), BorderLayout.NORTH);
        add(buildTabs(), BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
        refreshAll();
    }

    private JPanel buildHeader() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 14, 6, 14));
        JLabel title = new JLabel("Gestion de catalogo y prestamos con arbol B");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20f));
        JLabel subtitle = new JLabel("Simula busquedas por ISBN, rangos y cola de atencion de una biblioteca real.");
        subtitle.setForeground(new Color(71, 85, 105));
        panel.add(title, BorderLayout.NORTH);
        panel.add(subtitle, BorderLayout.SOUTH);
        return panel;
    }

    private JTabbedPane buildTabs() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Catalogo", buildCatalogPanel());
        tabs.addTab("Prestamos", buildLoanPanel());
        tabs.addTab("Arbol B", buildTreePanel());
        tabs.addTab("Historial", new JScrollPane(historyArea));
        historyArea.setEditable(false);
        return tabs;
    }

    private JPanel buildCatalogPanel() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTable table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
        table.setRowHeight(24);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel right = new JPanel(new BorderLayout(8, 8));
        right.add(buildBookForm(), BorderLayout.NORTH);
        right.add(buildSearchPanel(), BorderLayout.CENTER);
        panel.add(right, BorderLayout.EAST);
        return panel;
    }

    private JPanel buildBookForm() {
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Registrar libro"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets.set(2, 2, 2, 2);
        gbc.weightx = 1;

        isbnField = addField(form, gbc, 0, "ISBN");
        titleField = addField(form, gbc, 1, "Titulo");
        authorField = addField(form, gbc, 2, "Autor");
        yearField = addField(form, gbc, 3, "Anio");
        categoryField = addField(form, gbc, 4, "Categoria");

        JButton addButton = new JButton("Registrar");
        addButton.addActionListener(e -> registerBook());
        JButton deleteButton = new JButton("Eliminar por ISBN");
        deleteButton.addActionListener(e -> deleteBook());

        gbc.gridx = 0;
        gbc.gridy = 10;
        form.add(addButton, gbc);
        gbc.gridy = 11;
        form.add(deleteButton, gbc);
        return form;
    }

    private JTextField addField(JPanel panel, GridBagConstraints gbc, int row, String label) {
        gbc.gridx = 0;
        gbc.gridy = row * 2;
        panel.add(new JLabel(label), gbc);
        JTextField field = new JTextField(18);
        gbc.gridy = row * 2 + 1;
        panel.add(field, gbc);
        return field;
    }

    private JPanel buildSearchPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 6, 6));
        panel.setBorder(BorderFactory.createTitledBorder("Consultas"));

        searchField = new JTextField();
        JButton searchButton = new JButton("Buscar ISBN");
        searchButton.addActionListener(e -> searchBook());

        rangeFromField = new JTextField();
        rangeToField = new JTextField();
        JButton rangeButton = new JButton("Buscar rango");
        rangeButton.addActionListener(e -> searchRange());
        JButton allButton = new JButton("Ver todo");
        allButton.addActionListener(e -> refreshAll());

        panel.add(new JLabel("ISBN exacto"));
        panel.add(searchField);
        panel.add(searchButton);
        panel.add(new JLabel("Desde ISBN"));
        panel.add(rangeFromField);
        panel.add(new JLabel("Hasta ISBN"));
        panel.add(rangeToField);
        panel.add(rangeButton);
        panel.add(allButton);
        return panel;
    }

    private JPanel buildLoanPanel() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        studentField = new JTextField(18);
        requestIsbnField = new JTextField(16);
        JButton enqueueButton = new JButton("Encolar solicitud");
        enqueueButton.addActionListener(e -> enqueueRequest());
        JButton attendButton = new JButton("Atender siguiente");
        attendButton.addActionListener(e -> attendRequest());

        form.add(new JLabel("Estudiante"));
        form.add(studentField);
        form.add(new JLabel("ISBN"));
        form.add(requestIsbnField);
        form.add(enqueueButton);
        form.add(attendButton);

        JList<String> queueList = new JList<>(queueModel);
        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(queueList), BorderLayout.CENTER);
        return panel;
    }

    private JPanel buildTreePanel() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel note = new JLabel("Cada nodo muestra sus ultimos digitos de ISBN para visualizar divisiones y fusiones.", SwingConstants.LEFT);
        note.setForeground(new Color(71, 85, 105));
        panel.add(note, BorderLayout.NORTH);
        panel.add(new JScrollPane(treePanel), BorderLayout.CENTER);
        return panel;
    }

    private void registerBook() {
        try {
            Libro libro = new Libro(
                    isbnField.getText(),
                    titleField.getText(),
                    authorField.getText(),
                    Integer.parseInt(yearField.getText().trim()),
                    categoryField.getText());
            boolean inserted = service.registrarLibro(libro);
            showInfo(inserted ? "Libro registrado." : "El ISBN ya existe.");
            clearBookFields();
            refreshAll();
        } catch (RuntimeException ex) {
            showError(ex.getMessage());
        }
    }

    private void deleteBook() {
        String isbn = isbnField.getText().trim();
        if (isbn.isEmpty()) {
            isbn = searchField.getText().trim();
        }
        if (isbn.isEmpty()) {
            showError("Ingresa un ISBN en el formulario o en busqueda.");
            return;
        }
        boolean removed = service.eliminarLibro(isbn);
        showInfo(removed ? "Libro eliminado." : "No se encontro el ISBN.");
        refreshAll();
    }

    private void searchBook() {
        String isbn = searchField.getText().trim();
        if (isbn.isEmpty()) {
            showError("Ingresa un ISBN para buscar.");
            return;
        }
        Optional<Libro> found = service.buscarPorIsbn(isbn);
        tableModel.setLibros(found.map(java.util.Collections::singletonList).orElseGet(java.util.Collections::emptyList));
        refreshSharedViews();
        showInfo(found.isPresent() ? "Libro encontrado." : "Sin resultados.");
    }

    private void searchRange() {
        try {
            List<Libro> result = service.buscarRango(rangeFromField.getText().trim(), rangeToField.getText().trim());
            tableModel.setLibros(result);
            refreshSharedViews();
            showInfo(result.size() + " libro(s) encontrados en el rango.");
        } catch (RuntimeException ex) {
            showError(ex.getMessage());
        }
    }

    private void enqueueRequest() {
        try {
            service.registrarSolicitud(studentField.getText(), requestIsbnField.getText());
            studentField.setText("");
            requestIsbnField.setText("");
            refreshAll();
        } catch (RuntimeException ex) {
            showError(ex.getMessage());
        }
    }

    private void attendRequest() {
        try {
            SolicitudPrestamo solicitud = service.atenderSolicitud();
            showInfo("Solicitud atendida: " + solicitud);
            refreshAll();
        } catch (ExceptionIsEmpty ex) {
            showError(ex.getMessage());
        }
    }

    private void refreshAll() {
        tableModel.setLibros(service.listarLibros());
        refreshSharedViews();
    }

    private void refreshSharedViews() {
        treePanel.setRoot(service.getRaiz());
        queueModel.clear();
        for (SolicitudPrestamo solicitud : service.listarSolicitudes()) {
            queueModel.addElement(solicitud.toString());
        }
        StringBuilder history = new StringBuilder();
        for (String event : service.listarHistorial()) {
            history.append("- ").append(event).append('\n');
        }
        historyArea.setText(history.toString());
        statusLabel.setText("  Orden: " + service.ordenArbol()
                + " | Libros: " + service.totalLibros()
                + " | Altura: " + service.alturaArbol()
                + " | Solicitudes en cola: " + service.listarSolicitudes().size());
    }

    private void clearBookFields() {
        isbnField.setText("");
        titleField.setText("");
        authorField.setText("");
        yearField.setText("");
        categoryField.setText("");
    }

    private void showInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "Biblioteca", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
