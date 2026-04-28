package listaenlasada;

public class Ejer4 {
	public static <T> Node<T> insertarAlFinal(Node<T> head, T x) {
        Node<T> newNode = new Node<>(x);

        if (head == null) {
            return newNode;
        }

        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
        return head;
    }

    public static <T> int contarNodos(Node<T> head) {
        int count = 0;
        Node<T> current = head;

        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }

    public static void main(String[] args) {
        Node<Integer> head = null;

        head = insertarAlFinal(head, 10);
        head = insertarAlFinal(head, 20);
        head = insertarAlFinal(head, 30);
        head = insertarAlFinal(head, 40);

        System.out.println("Total de nodos: " + contarNodos(head)); // 4
    }
}
