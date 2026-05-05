package actividad2;

import actividad1.ExceptionIsEmpty;

public class DequeLink<E> implements Deque<E> {
	private Node<E> first;
    private Node<E> last;

    public DequeLink() {
        first = null;
        last = null;
    }

    public void addFirst(E x) {
        Node<E> n = new Node<>(x);

        if (isEmpty()) {
            first = last = n;
        } else {
            n.next = first;
            first = n;
        }
    }

    public void addLast(E x) {
        Node<E> n = new Node<>(x);

        if (isEmpty()) {
            first = last = n;
        } else {
            last.next = n;
            last = n;
        }
    }

    public E removeFirst() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Deque vacío");

        E aux = first.data;
        first = first.next;

        if (first == null)
            last = null;

        return aux;
    }

    public E removeLast() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Deque vacío");

        E aux = last.data;

        if (first == last) {
            first = last = null;
        } else {
            Node<E> temp = first;
            while (temp.next != last) {
                temp = temp.next;
            }
            temp.next = null;
            last = temp;
        }
        return aux;
    }

    public E getFirst() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Deque vacío");

        return first.data;
    }

    public E getLast() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Deque vacío");

        return last.data;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public String toString() {
        String s = "";
        Node<E> temp = first;

        while (temp != null) {
            s += temp.data + " ";
            temp = temp.next;
        }
        return s;
    }
}