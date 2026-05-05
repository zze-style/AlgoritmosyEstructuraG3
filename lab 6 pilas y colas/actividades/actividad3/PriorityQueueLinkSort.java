package actividad3;

import actividad1.ExceptionIsEmpty;

public class PriorityQueueLinkSort<E, N extends Comparable<N>> implements PriorityQueue<E, N> {
	class EntryNode {
        E data;
        N priority;

        EntryNode(E data, N priority) {
            this.data = data;
            this.priority = priority;
        }
    }

    private Node<EntryNode> first;
    private Node<EntryNode> last;

    public PriorityQueueLinkSort() {
        first = null;
        last = null;
    }

    public void enqueue(E x, N pr) {
        EntryNode entry = new EntryNode(x, pr);
        Node<EntryNode> newNode = new Node<>(entry);

        if (isEmpty()) {
            first = last = newNode;
            return;
        }

        if (pr.compareTo(first.getData().priority) > 0) {
            newNode.setNext(first);
            first = newNode;
            return;
        }

        Node<EntryNode> current = first;

        while (current.getNext() != null &&
               pr.compareTo(current.getNext().getData().priority) <= 0) {
            current = current.getNext();
        }

        newNode.setNext(current.getNext());
        current.setNext(newNode);

        if (newNode.getNext() == null)
            last = newNode;
    }

    public E front() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Cola vacía");

        return first.getData().data;
    }

    public E back() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Cola vacía");

        return last.getData().data;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public String toString() {
        String s = "";
        Node<EntryNode> temp = first;

        while (temp != null) {
            s += "(" + temp.getData().data + "," + temp.getData().priority + ") ";
            temp = temp.getNext();
        }
        return s;
    }
    
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Cola vacía");

        E aux = this.first.getData().data;
        this.first = this.first.getNext();

        if (this.first == null)
            this.last = null;

        return aux;
    }
}