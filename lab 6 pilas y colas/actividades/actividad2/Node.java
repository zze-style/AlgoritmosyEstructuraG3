package actividad2;

public class Node<E> {
	E data;
    Node<E> next;

    Node(E data) {
        this.data = data;
        this.next = null;
    }
}