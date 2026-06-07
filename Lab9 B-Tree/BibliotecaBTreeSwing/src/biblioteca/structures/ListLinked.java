package biblioteca.structures;

import java.util.ArrayList;
import java.util.List;

public class ListLinked<T> {
    private Node<T> first;

    public void insertLast(T value) {
        Node<T> newNode = new Node<>(value);
        if (first == null) {
            first = newNode;
            return;
        }
        Node<T> current = first;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    public List<T> toList() {
        List<T> values = new ArrayList<>();
        Node<T> current = first;
        while (current != null) {
            values.add(current.value);
            current = current.next;
        }
        return values;
    }

    public int length() {
        int count = 0;
        Node<T> current = first;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}
