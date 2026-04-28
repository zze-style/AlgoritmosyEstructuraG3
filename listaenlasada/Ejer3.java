package listaenlasada;

public class Ejer3 {
	public static <T> Node<T> insertarAlFinal(Node<T> head, T valor) {
	    Node<T> newNode = new Node<>(valor);
	    if (head == null) return newNode; // lista vacia: el nuevo nodo es la cabeza
	    Node<T> current = head;
	    while (current.next != null) current = current.next;
	    current.next = newNode;
	    return head;
	}

	// Prueba
	public static void main(String[] args) {
	    Node<String> head = null;
	    head = insertarAlFinal(head, "Alpha");
	    head = insertarAlFinal(head, "Beta");
	    head = insertarAlFinal(head, "Gamma");

	    // Imprimir manualmente
	    Node<String> current = head;
	    while (current != null) {
	        System.out.println(current.value);
	        current = current.next;
	    }
	}
}
