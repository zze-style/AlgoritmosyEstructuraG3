package transporte.ui;

import transporte.domain.SolicitudViaje;
import transporte.service.TransporteService;
import transporte.structures.ExceptionIsEmpty;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Optional;

public class TransporteFrame extends JFrame {
    private final TransporteService service;
    private final GraphPanel graphPanel = new GraphPanel();
    private final JTextArea logArea = new JTextArea();
    private JTextField paraderoField, rutaAField, rutaBField, pesoField;
    private JTextField pasajeroField, origenField, destinoField;
    private final DefaultListModel<String> queueModel = new DefaultListModel<>();
    private final JLabel statusLabel = new JLabel();

    public TransporteFrame(TransporteService service) {
        this.service = service;
        setTitle("Sistema de Rutas de Transporte Urbano - Grafo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 680);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));
        add(buildHeader(), BorderLayout.NORTH);
        add(buildTabs(), BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
        seedData();
        refresh();
    }

    private JPanel buildHeader() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createEmptyBorder(8,12,4,12));
        JLabel t = new JLabel("Sistema de rutas de transporte urbano con Grafo");
        t.setFont(t.getFont().deriveFont(Font.BOLD, 18f));
        JLabel s = new JLabel("Gestiona paraderos, rutas y solicitudes de viaje.");
        s.setForeground(new Color(71,85,105));
        p.add(t, BorderLayout.NORTH);
        p.add(s, BorderLayout.SOUTH);
        return p;
    }

    private JTabbedPane buildTabs() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Red de paraderos", buildRedPanel());
        tabs.addTab("Solicitudes",      buildSolicitudesPanel());
        tabs.addTab("Grafo visual",     buildGraphTab());
        tabs.addTab("Historial",        new JScrollPane(logArea));
        logArea.setEditable(false);
        return tabs;
    }

    private JPanel buildRedPanel() {
        JPanel p = new JPanel(new BorderLayout(8,8));
        p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JPanel forms = new JPanel(new GridLayout(1,2,8,0));

        JPanel fvPane = new JPanel(new GridBagLayout());
        fvPane.setBorder(BorderFactory.createTitledBorder("Agregar paradero"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; gbc.insets.set(3,3,3,3);
        gbc.weightx=1; gbc.gridx=0; gbc.gridy=0;
        fvPane.add(new JLabel("Nombre:"), gbc); gbc.gridy=1;
        paraderoField = new JTextField(); fvPane.add(paraderoField, gbc);
        gbc.gridy=2;
        JButton btnParadero = new JButton("Agregar");
        btnParadero.addActionListener(e -> addParadero());
        fvPane.add(btnParadero, gbc);

        JPanel fePane = new JPanel(new GridBagLayout());
        fePane.setBorder(BorderFactory.createTitledBorder("Gestionar ruta"));
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill=GridBagConstraints.HORIZONTAL; gbc2.insets.set(3,3,3,3);
        gbc2.weightx=1; gbc2.gridx=0;
        gbc2.gridy=0; fePane.add(new JLabel("Paradero A:"), gbc2);
        gbc2.gridy=1; rutaAField = new JTextField(); fePane.add(rutaAField,gbc2);
        gbc2.gridy=2; fePane.add(new JLabel("Paradero B:"), gbc2);
        gbc2.gridy=3; rutaBField = new JTextField(); fePane.add(rutaBField,gbc2);
        gbc2.gridy=4; fePane.add(new JLabel("Minutos:"), gbc2);
        gbc2.gridy=5; pesoField = new JTextField(); fePane.add(pesoField,gbc2);
        gbc2.gridy=6;
        JButton btnRuta = new JButton("Agregar ruta");
        btnRuta.addActionListener(e2 -> addRuta());
        fePane.add(btnRuta, gbc2);
        gbc2.gridy=7;
        JButton btnElim = new JButton("Eliminar ruta");
        btnElim.addActionListener(e2 -> elimRuta());
        fePane.add(btnElim, gbc2);
        gbc2.gridy=8;
        JButton btnDijk = new JButton("Ruta mas rapida");
        btnDijk.addActionListener(e2 -> calcDijkstra());
        fePane.add(btnDijk, gbc2);
        gbc2.gridy=9;
        JButton btnConex = new JButton("¿Red conectada?");
        btnConex.addActionListener(e2 -> checkConexo());
        fePane.add(btnConex, gbc2);

        forms.add(fvPane); forms.add(fePane);
        p.add(forms, BorderLayout.EAST);
        p.add(new JScrollPane(graphPanel), BorderLayout.CENTER);
        return p;
    }

    private JPanel buildSolicitudesPanel() {
        JPanel p = new JPanel(new BorderLayout(8,8));
        p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pasajeroField = new JTextField(14);
        origenField   = new JTextField(10);
        destinoField  = new JTextField(10);
        JButton enc = new JButton("Encolar");
        enc.addActionListener(e -> encolarSolicitud());
        JButton ate = new JButton("Atender siguiente");
        ate.addActionListener(e -> atenderSolicitud());
        form.add(new JLabel("Pasajero:")); form.add(pasajeroField);
        form.add(new JLabel("Origen:"));  form.add(origenField);
        form.add(new JLabel("Destino:")); form.add(destinoField);
        form.add(enc); form.add(ate);
        JList<String> qList = new JList<>(queueModel);
        p.add(form, BorderLayout.NORTH);
        p.add(new JScrollPane(qList), BorderLayout.CENTER);
        return p;
    }

    private JPanel buildGraphTab() {
        JPanel p = new JPanel(new BorderLayout());
        p.add(new JScrollPane(graphPanel), BorderLayout.CENTER);
        return p;
    }

    private void seedData() {
        service.agregarParadero("Plaza de Armas");
        service.agregarParadero("Cayma");
        service.agregarParadero("Miraflores");
        service.agregarParadero("Yanahuara");
        service.agregarParadero("Cercado");
        service.agregarRuta("Plaza de Armas", "Cayma", 12);
        service.agregarRuta("Plaza de Armas", "Miraflores", 8);
        service.agregarRuta("Cayma", "Yanahuara", 5);
        service.agregarRuta("Miraflores", "Cercado", 7);
        service.agregarRuta("Yanahuara", "Cercado", 10);
    }

    private void addParadero() {
        String n = paraderoField.getText().trim();
        if (n.isEmpty()) { showError("Ingresa un nombre."); return; }
        service.agregarParadero(n);
        paraderoField.setText("");
        refresh();
    }

    private void addRuta() {
        try {
            service.agregarRuta(rutaAField.getText().trim(),
                                rutaBField.getText().trim(),
                                Integer.parseInt(pesoField.getText().trim()));
            refresh();
        } catch (Exception e) { showError(e.getMessage()); }
    }

    private void elimRuta() {
        boolean r = service.eliminarRuta(
            rutaAField.getText().trim(), rutaBField.getText().trim());
        showInfo(r ? "Ruta eliminada." : "No se encontro la ruta.");
        refresh();
    }

    private void calcDijkstra() {
        Optional<List<String>> opt =
            service.rutaMasRapida(rutaAField.getText().trim(),
                                  rutaBField.getText().trim());
        showInfo(opt.map(r -> "Ruta: " + r).orElse("Sin ruta disponible."));
    }

    private void checkConexo() {
        showInfo(service.redConectada()
            ? "La red esta completamente conectada."
            : "La red NO esta completamente conectada.");
    }

    private void encolarSolicitud() {
        service.registrarSolicitud(pasajeroField.getText(),
                                   origenField.getText(),
                                   destinoField.getText());
        refresh();
    }

    private void atenderSolicitud() {
        try {
            SolicitudViaje s = service.atenderSolicitud();
            showInfo("Atendido: " + s);
            refresh();
        } catch (Exception e) { showError(e.getMessage()); }
    }

    private void refresh() {

        graphPanel.setGraph(service.getRed());

        queueModel.clear();

        int totalSolicitudes = 0;
        
        transporte.structures.Node<SolicitudViaje> currentSolicitud = service.listarSolicitudes().getFirst();
        
        while (currentSolicitud != null) {
        	SolicitudViaje s = currentSolicitud.getValue();
        	queueModel.addElement(s.toString());
        	totalSolicitudes++;
        	currentSolicitud = currentSolicitud.getNext();
        }

        StringBuilder sb = new StringBuilder();

        transporte.structures.Node<String> currentHistorial = service.getHistorial().getFirst();
        
        while (currentHistorial != null) {
        	String h = currentHistorial.getValue();
        	sb.append("- ").append(h).append('\n');
        	currentHistorial = currentHistorial.getNext();
        }

        logArea.setText(sb.toString());

        statusLabel.setText(
                "  Paraderos: "
                        + countVertices()
                        + " | Solicitudes en cola: "
                        + totalSolicitudes
                        + " | Red conectada: "
                        + service.redConectada()
        );
    }

    private int countVertices() {

        int c = 0;

        transporte.structures.Node<
                transporte.graph.AdjList<String>> n =
                service.getRed()
                        .getAdjLists()
                        .getFirst();

        while (n != null) {
            c++;
            n = n.getNext();
        }

        return c;
    }

    private void showInfo(String m) {
        JOptionPane.showMessageDialog(this, m, "Info",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String m) {
        JOptionPane.showMessageDialog(this, m, "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}