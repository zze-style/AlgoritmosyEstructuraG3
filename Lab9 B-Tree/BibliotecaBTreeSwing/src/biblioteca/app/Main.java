package biblioteca.app;

import biblioteca.service.BibliotecaService;
import biblioteca.ui.BibliotecaFrame;

import javax.swing.SwingUtilities;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BibliotecaService service = new BibliotecaService(4, 20);
            try {
                Path dataPath = Paths.get("data", "biblioteca.txt");
                service.cargarDesdeArchivo(dataPath);
            } catch (Exception ex) {
                System.err.println("No se pudo cargar data/biblioteca.txt: " + ex.getMessage());
            }
            new BibliotecaFrame(service).setVisible(true);
        });
    }
}
