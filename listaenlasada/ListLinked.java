package listaenlasada;

public class ListLinked<T> {
    protected Node<T> first;

    public ListLinked() {
        first = null;
    }

    public boolean isEmptyList() {
        return first == null;
    }

    public void insertFirst(T x) {
        Node<T> newNode = new Node<>(x);
        newNode.next = first;
        first = newNode;
    }

    public void insertLast(T x) {
        Node<T> newNode = new Node<>(x);
        if (first == null) { first = newNode; return; }
        Node<T> current = first;
        while (current.next != null) current = current.next;
        current.next = newNode;
    }

    public boolean removeNode(T x) {
        if (first == null) return false;
        if (first.value.equals(x)) { first = first.next; return true; }
        Node<T> current = first;
        while (current.next != null) {
            if (current.next.value.equals(x)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean search(T x) {
        Node<T> current = first;
        while (current != null) {
            if (current.value.equals(x)) return true;
            current = current.next;
        }
        return false;
    }

    public int length() {
        int count = 0;
        Node<T> current = first;
        while (current != null) { count++; current = current.next; }
        return count;
    }

    public void print() {
        Node<T> current = first;
        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
    }

    public void reverse() {
        Node<T> prev = null, current = first, next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        first = prev;
    }
}