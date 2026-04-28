package listaenlasada;

class SortedListLinked<T extends Comparable<T>> extends ListLinked<T> {

    public void insertOrden(T x) {
        Node<T> newNode = new Node<>(x);

        // Caso 1: lista vacia o nuevo elemento es menor que el primero
        if (first == null || x.compareTo(first.value) <= 0) {
            newNode.next = first;
            first = newNode;
            return;
        }

        // Caso 2: encontrar posicion correcta
        Node<T> current = first;
        while (current.next != null && current.next.value.compareTo(x) < 0) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
    }
}

public class Ejer7 {
	// Prueba con tareas ordenadas por prioridad
	public static void main(String[] args) {
	    SortedListLinked<Tarea> listaOrdenada = new SortedListLinked<>();
	    listaOrdenada.insertOrden(new Tarea("Documentar API",    3, "pendiente"));
	    listaOrdenada.insertOrden(new Tarea("Deploy produccion", 1, "pendiente"));
	    listaOrdenada.insertOrden(new Tarea("Code review",       2, "pendiente"));
	    listaOrdenada.insertOrden(new Tarea("Corregir bug",      1, "completada"));

	    System.out.println("=== Lista ordenada por prioridad ===");
	    listaOrdenada.print();
	}
}
