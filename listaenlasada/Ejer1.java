package listaenlasada;

public class Ejer1 {
	public static <T> boolean buscarElemento(ListLinked<T> lista, T valor) {
	    Node<T> current = lista.first; // acceso al nodo cabeza
	    while (current != null) {
	        if (current.value.equals(valor)) return true;
	        current = current.next;
	    }
	    return false;
	}

	// Prueba
	public static void main(String[] args) {
	    ListLinked<String> lista = new ListLinked<>();
	    lista.insertLast("Tarea A");
	    lista.insertLast("Tarea B");
	    lista.insertLast("Tarea C");

	    System.out.println(buscarElemento(lista, "Tarea B")); // true
	    System.out.println(buscarElemento(lista, "Tarea Z")); // false
	}
}
