package sameArea;

public class Arbol<T extends Comparable<T>> {
	Nodo<T> raiz;
	public void insertar(T dato) {
        raiz = insertarRec(raiz, dato);
    }

    private Nodo<T> insertarRec(Nodo<T> nodo, T dato) {
        if (nodo == null)
            return new Nodo<>(dato);

        if (dato.compareTo(nodo.dato) < 0)
            nodo.izquierdo = insertarRec(nodo.izquierdo, dato);

        else
            nodo.derecho = insertarRec(nodo.derecho, dato);

        return nodo;
    }

    public int areaBST() {
        return contarNodos(raiz);
    }

    private int contarNodos(Nodo<T> nodo) {

        if (nodo == null)
            return 0;

        return 1 + contarNodos(nodo.izquierdo)
                 + contarNodos(nodo.derecho);
    }
}