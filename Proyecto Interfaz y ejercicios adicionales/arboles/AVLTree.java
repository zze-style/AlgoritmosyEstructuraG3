package arboles;
import listaenlasada.*;


public class AVLTree<T extends Comparable<T>> extends Arbol<T> {
    @Override
    public void insertar(T dato) {
        if (buscar(dato)) {
            throw new IllegalArgumentException("El valor " + dato + " ya existe.");
        }
        raiz = insertarAVL(raiz, dato);
    }

    private Nodo<T> insertarAVL(Nodo<T> actual, T dato) {
        if (actual == null) {
            return new Nodo<>(dato);
        }

        if (dato.compareTo(actual.dato) < 0) {
            actual.izquierdo = insertarAVL(actual.izquierdo, dato);
        } else if (dato.compareTo(actual.dato) > 0) {
            actual.derecho = insertarAVL(actual.derecho, dato);
        }

        actualizarAltura(actual);
        return balancear(actual);
    }

    @Override
    public void remove(T dato) {
        raiz = eliminarAVL(raiz, dato);
    }

    private Nodo<T> eliminarAVL(Nodo<T> actual, T dato) {
        if (actual == null) {
            return null;
        }

        if (dato.compareTo(actual.dato) < 0) {
            actual.izquierdo = eliminarAVL(actual.izquierdo, dato);
        } else if (dato.compareTo(actual.dato) > 0) {
            actual.derecho = eliminarAVL(actual.derecho, dato);
        } else {
            if (actual.izquierdo == null || actual.derecho == null) {
                return actual.izquierdo != null ? actual.izquierdo : actual.derecho;
            }

            Nodo<T> sucesor = obtenerMenor(actual.derecho);
            actual.dato = sucesor.dato;
            actual.derecho = eliminarAVL(actual.derecho, sucesor.dato);
        }

        actualizarAltura(actual);
        return balancear(actual);
    }

    private Nodo<T> balancear(Nodo<T> nodo) {
        int balance = factorBalance(nodo);

        if (balance > 1) {
            if (factorBalance(nodo.izquierdo) < 0) {
                nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
            }
            return rotacionDerecha(nodo);
        }

        if (balance < -1) {
            if (factorBalance(nodo.derecho) > 0) {
                nodo.derecho = rotacionDerecha(nodo.derecho);
            }
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    private int factorBalance(Nodo<T> nodo) {
        return nodo == null ? 0 : alturaNodo(nodo.izquierdo) - alturaNodo(nodo.derecho);
    }

    private Nodo<T> rotacionDerecha(Nodo<T> y) {
        Nodo<T> x = y.izquierdo;
        Nodo<T> subarbolMovido = x.derecho;

        x.derecho = y;
        y.izquierdo = subarbolMovido;

        actualizarAltura(y);
        actualizarAltura(x);
        return x;
    }

    private Nodo<T> rotacionIzquierda(Nodo<T> x) {
        Nodo<T> y = x.derecho;
        Nodo<T> subarbolMovido = y.izquierdo;

        y.izquierdo = x;
        x.derecho = subarbolMovido;

        actualizarAltura(x);
        actualizarAltura(y);
        return y;
    }

    public ListLinked<T> bfsRecursivo() {
        ListLinked<T> datos = new ListLinked<>();
        for (int nivel = 0; nivel <= altura(); nivel++) {
            recolectarNivel(raiz, nivel, datos);
        }
        return datos;
    }

    public ListLinked<ListLinked<T>> bfsPorNiveles() {
        ListLinked<ListLinked<T>> niveles = new ListLinked<>();
        for (int nivel = 0; nivel <= altura(); nivel++) {
            ListLinked<T> datos = new ListLinked<>();
            recolectarNivel(raiz, nivel, datos);
            niveles.insertLast(datos);
        }
        return niveles;
    }

    private void recolectarNivel(Nodo<T> nodo, int nivel, ListLinked<T> datos) {
        if (nodo == null) {
            return;
        }
        if (nivel == 0) {
            datos.insertLast(nodo.dato);
            return;
        }
        recolectarNivel(nodo.izquierdo, nivel - 1, datos);
        recolectarNivel(nodo.derecho, nivel - 1, datos);
    }
}
