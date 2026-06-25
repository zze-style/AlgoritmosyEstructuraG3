package Hash;

public class TestHashO {
    public static void main(String[] args) {
        HashO tabla = new HashO(7);

        tabla.insert(new Register(10, "Juan"));
        tabla.insert(new Register(17, "Ana"));
        tabla.insert(new Register(3, "Luis"));
        tabla.insert(new Register(24, "Pedro"));
        tabla.insert(new Register(11, "Sofia"));

        System.out.println("=== Tabla hash abierta (encadenamiento) ===");
        tabla.printTable();

        System.out.println("\nBusqueda clave 24: " + tabla.search(24));

        tabla.delete(17);
        System.out.println("\n=== Tabla despues de eliminar la clave 17 ===");
        tabla.printTable();
    }
} 