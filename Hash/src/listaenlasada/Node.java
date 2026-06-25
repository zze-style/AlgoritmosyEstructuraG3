package listaenlasada;

public class Node<T> {
    public T value;
    public Node<T> next;

    public Node(T value) {
        this.value = value;
        this.next = null;
    }
    public T getValue() {
    	return this.value;
    }
    public Node<T> getNext() {
        return next;
    }
    public void setNext(Node<T> next) {
    	this.next =next;
    }
}