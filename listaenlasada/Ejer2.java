package listaenlasada;

public class Ejer2 {
	public static <T> ListLinked<T> invertirLista(ListLinked<T> lista) {
	    ListLinked<T> nueva = new ListLinked<>();
	    Node<T> current = lista.first;
	    while (current != null) {
	        nueva.insertFirst(current.value); // insertar al inicio invierte el orden
	        current = current.next;
	    }
	    return nueva;
	}

	// Prueba
	public static void main(String[] args) {
	    ListLinked<Integer> original = new ListLinked<>();
	    original.insertLast(1);
	    original.insertLast(2);
	    original.insertLast(3);

	    ListLinked<Integer> invertida = invertirLista(original);

	    System.out.print("Original:  "); original.print();
	    System.out.print("Invertida: "); invertida.print();
	}
}
