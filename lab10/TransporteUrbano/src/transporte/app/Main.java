package transporte.app;

import transporte.service.TransporteService;
import transporte.ui.TransporteFrame;
import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Inicializar el servicio con capacidad máxima de 50
                TransporteService service = new TransporteService(50);
                
                // Crear ventana pasando el parámetro servicio para su arraqnue
                TransporteFrame ventana = new TransporteFrame(service);
               
                ventana.setVisible(true);
                
            } catch (Exception e) {
                System.err.println("Error al iniciar la aplicación de transportes: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
