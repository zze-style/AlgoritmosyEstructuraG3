package listaenlasada;

public class Ejer6 {
	public static <T> ListLinked<T> concatenarListas(ListLinked<T> lista1, ListLinked<T> lista2) {
		ListLinked<T> nueva = new ListLinked<>();
		Node<T> current = lista1.first;
		while (current != null) {
			nueva.insertLast(current.value);
			current = current.next;
		}
		current = lista2.first;
		while (current != null) {
			nueva.insertLast(current.value);
			current = current.next;
		}
		return nueva;
	}

	//Prueba
	public static void main(String[] args) {
		ListLinked<String> l1 = new ListLinked<>();
		ListLinked<String> l2 = new ListLinked<>();
		l1.insertLast("A"); l1.insertLast("B");
		l2.insertLast("C"); l2.insertLast("D");

		ListLinked<String> resultado = concatenarListas(l1, l2);
		System.out.println("Concatenada:");
		resultado.print(); // A B C D
	}
}
