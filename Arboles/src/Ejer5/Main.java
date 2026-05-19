package Ejer5;

public class Main {

	public static void main(String[] args) {
		Arbol<Integer> inventario = new Arbol<>();

        int[] codigos = {100, 50, 150, 30, 80, 120, 200};

        for (int c : codigos)
            inventario.insertar(c);

        System.out.println("Productos entre 50 y 150:");
        inventario.buscarRango(50, 150);

        System.out.println("\nProductos en hoja:");
        System.out.println(inventario.contarHojas());

        System.out.println("\nOrden descendente:");
        inventario.imprimirDescendente();

	}
}