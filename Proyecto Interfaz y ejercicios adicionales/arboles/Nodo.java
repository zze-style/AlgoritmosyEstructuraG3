package arboles;

public class Nodo<T extends Comparable<T>> {
    protected T dato;
    protected Nodo<T> izquierdo;
    protected Nodo<T> derecho;
    protected int altura;

    public Nodo(T dato) {
        this.dato = dato;
        this.altura = 0;
    }

    public T getDato() {
        return dato;
    }

    public Nodo<T> getIzquierdo() {
        return izquierdo;
    }

    public Nodo<T> getDerecho() {
        return derecho;
    }
}
