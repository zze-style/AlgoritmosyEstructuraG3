package Ejer4;

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


    public boolean isValidBST() {
        return validar(raiz, null, null);
    }

    private boolean validar(Nodo<T> nodo, T min, T max) {

        if (nodo == null)
            return true;

        if (min != null && nodo.dato.compareTo(min) <= 0)
            return false;

        if (max != null && nodo.dato.compareTo(max) >= 0)
            return false;

        return validar(nodo.izquierdo, min, nodo.dato) &&
               validar(nodo.derecho, nodo.dato, max);
    }


    public void parenthesize() {
        parenthesizeRec(raiz, 0);
    }

    private void parenthesizeRec(Nodo<T> nodo, int nivel) {

        if (nodo == null)
            return;

        for (int i = 0; i < nivel; i++)
            System.out.print(" ");

        System.out.println(nodo.dato + " (");

        parenthesizeRec(nodo.izquierdo, nivel + 1);
        parenthesizeRec(nodo.derecho, nivel + 1);

        for (int i = 0; i < nivel; i++)
            System.out.print(" ");

        System.out.println(")");
    }
}