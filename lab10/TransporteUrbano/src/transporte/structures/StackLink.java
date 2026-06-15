package transporte.structures;

public class StackLink<E> implements Stack<E> {
    private Node<E> top;

    public StackLink() {
        this.top = null;
    }

    @Override
    public void push(E x) {
        Node<E> newNode = new Node<>(x);
        newNode.setNext(top);
        top = newNode;
    }

    @Override
    public E pop() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Pila vacia");
        E dato = top.getValue();
        top = top.getNext();
        return dato;
    }

    @Override
    public E top() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Pila vacia");
        return top.getValue();
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Pila vacia";
        StringBuilder sb = new StringBuilder("Tope -> [");
        Node<E> current = top;
        while (current != null) {
            sb.append(current.getValue());
            if (current.getNext() != null) sb.append(", ");
            current = current.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}
