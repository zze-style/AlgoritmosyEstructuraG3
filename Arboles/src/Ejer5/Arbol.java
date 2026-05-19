package Ejer5;

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


    public void buscarRango(T min, T max) {
        buscarRangoRec(raiz, min, max);
    }

    private void buscarRangoRec(Nodo<T> nodo, T min, T max) {

        if (nodo == null)
            return;

        if (nodo.dato.compareTo(min) > 0)
            buscarRangoRec(nodo.izquierdo, min, max);

        if (nodo.dato.compareTo(min) >= 0 && nodo.dato.compareTo(max) <= 0)
            System.out.println(nodo.dato);

        if (nodo.dato.compareTo(max) < 0)
            buscarRangoRec(nodo.derecho, min, max);
    }
    

    public int contarHojas() {
        return contarHojasRec(raiz);
    }

    private int contarHojasRec(Nodo<T> nodo) {

        if (nodo == null)
            return 0;

        if (nodo.izquierdo == null && nodo.derecho == null)
            return 1;

        return contarHojasRec(nodo.izquierdo) + contarHojasRec(nodo.derecho);
    }


    public void imprimirDescendente() {
        imprimirDescendenteRec(raiz);
    }

    private void imprimirDescendenteRec(Nodo<T> nodo) {

        if (nodo == null)
            return;

        imprimirDescendenteRec(nodo.derecho);
        System.out.println(nodo.dato);
        imprimirDescendenteRec(nodo.izquierdo);
    }
}