package otro;
import arboles.*;
public class ClinicaTurnosAVL {

    private AVLTree<Integer> arbol;

    public ClinicaTurnosAVL() {
        arbol = new AVLTree<>();
    }

    // Insertar turno
    public void insertarTurno(int turno) {
        arbol.insertar(turno);

        System.out.println("Turno " + turno + " agregado.");
        System.out.print("InOrden: ");
        arbol.inOrden();
    }

    // Buscar turno
    public void buscarTurno(int turno) {

        boolean encontrado = arbol.buscar(turno);

        System.out.println("Buscar turno " + turno + ": "
                + (encontrado ? "ENCONTRADO"
                : "NO ENCONTRADO"));
    }

    // Eliminar turno atendido
    public void eliminarTurno(int turno) {

        arbol.remove(turno);

        System.out.println("Turno " + turno + " atendido y eliminado.");
        System.out.print("InOrden: ");
        arbol.inOrden();
    }

    public static void main(String[] args) {

        ClinicaTurnosAVL clinica = new ClinicaTurnosAVL();

        System.out.println("=== REGISTRANDO TURNOS ===");

        int[] turnos = {20, 10, 30, 40, 50};

        for (int t : turnos) {
            clinica.insertarTurno(t);
        }

        System.out.println("\n=== BUSCANDO TURNOS ===");

        clinica.buscarTurno(30);
        clinica.buscarTurno(99);

        System.out.println("\n=== ELIMINANDO TURNOS ===");

        clinica.eliminarTurno(10);

        System.out.println("\n=== ESTADO FINAL ===");
        System.out.print("Turnos en orden: ");
        clinica.arbol.inOrden();

        System.out.println("Altura final: "
                + clinica.arbol.altura());
    }
}
