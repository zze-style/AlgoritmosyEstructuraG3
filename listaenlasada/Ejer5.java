package listaenlasada;

public class Ejer5 {
	public static <T> boolean sonIguales(ListLinked<T> lista1, ListLinked<T> lista2) {
	    Node<T> c1 = lista1.first;
	    Node<T> c2 = lista2.first;
	    while (c1 != null && c2 != null) {
	        if (!c1.value.equals(c2.value)) return false;
	        c1 = c1.next;
	        c2 = c2.next;
	    }
	    // Ambas deben terminar al mismo tiempo (mismo tamano)
	    return c1 == null && c2 == null;
	}

	// Prueba
	public static void main(String[] args) {
	    ListLinked<Integer> a = new ListLinked<>();
	    ListLinked<Integer> b = new ListLinked<>();
	    ListLinked<Integer> c = new ListLinked<>();

	    a.insertLast(1); a.insertLast(2); a.insertLast(3);
	    b.insertLast(1); b.insertLast(2); b.insertLast(3);
	    c.insertLast(1); c.insertLast(2); c.insertLast(4);

	    System.out.println("a == b: " + sonIguales(a, b)); // true
	    System.out.println("a == c: " + sonIguales(a, c)); // false
	}
}
