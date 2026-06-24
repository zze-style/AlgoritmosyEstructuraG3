package Hash.ejercicios;

import Hash.HashO;
import Hash.Register; 

public class Ejercicio3 {
	
    public static void main(String[] args) {
        HashO tabla = new HashO(7);

        tabla.insert(new Register(10, "Juan"));
        tabla.insert(new Register(17, "Ana"));
        tabla.insert(new Register(24, "Luis"));
        tabla.insert(new Register(31, "Rosa"));
        tabla.insert(new Register(5, "Pedro"));
        tabla.insert(new Register(12, "Carla"));

        System.out.println("=== Estado final de la tabla ===");
        tabla.printTable();

        System.out.println("\nBusqueda de la clave 24: " + tabla.search(24));

        tabla.delete(17);
        System.out.println("\n=== Tabla despues de eliminar la clave 17 ===");
        tabla.printTable();
    }
} 