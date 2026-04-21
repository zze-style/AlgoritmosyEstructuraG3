package Ejercicio9;
import java.util.ArrayList;

public class PowerStation<T extends Cargable> {
    private ArrayList<T> dispositivos;

    public PowerStation() {
        dispositivos = new ArrayList<>();
    }

    public void conectar(T dispositivo) {
        dispositivos.add(dispositivo);
    }

    public double calcularConsumoTotal() {
        double total = 0;
        for (T d : dispositivos) {
            total += d.getConsumoVatios();
        }
        return total;
    }

    public int buscarDispositivo(T prototipo) {
        for (int i = 0; i < dispositivos.size(); i++) {
            if (dispositivos.get(i).equals(prototipo)) {
                return i;
            }
        }
        return -1;
    }
 // Método adicional: cargar todos los dispositivos
    public void cargarTodos(int cantidad) {
        for (T d : dispositivos) {
            d.cargar(cantidad);
        }
    }

    public void mostrarReporte() {
        System.out.println("Posicion\tConsumo (W)\tBateria (%)");
        for (int i = 0; i < dispositivos.size(); i++) {
            System.out.println(i + "\t\t" + dispositivos.get(i).getConsumoVatios() + "\t\t" + dispositivos.get(i).getNivelBateria());
        }
    }
}