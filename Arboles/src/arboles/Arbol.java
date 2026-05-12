package arboles;

import java.util.LinkedList;
import java.util.Queue;

public class Arbol <T extends Comparable<T>> {
	
	private Nodo<T> raiz;
	public Arbol () {
	    raiz = null;
	}
	
	public boolean isEmpty () {
	    return raiz == null;
	}
	
	public void insertar(T dato) {
	    raiz = insertarRec (raiz, dato);
	}
	
	private Nodo<T> insertarRec(Nodo<T> actual, T dato) {
	    if (actual == null) {
	        return new Nodo<>(dato);
	    }
	    
	    if (dato.compareTo(actual.dato) < 0) {
	        actual.izquierdo = insertarRec(actual.izquierdo, dato);
	    } else if (dato.compareTo (actual.dato) > 0) {
	        actual.derecho = insertarRec(actual.derecho, dato);
	    }
	    return actual;
	}
	
	public boolean buscar (T dato) {
	    return buscarRec (raiz, dato);
	}
	
	private boolean buscarRec (Nodo<T> actual, T dato) {
	    if (actual == null) return false;
	    if (dato.compareTo (actual.dato) == 0) return true;
	    if (dato.compareTo (actual.dato) < 0)
	        return buscarRec (actual.izquierdo, dato);
	    return buscarRec (actual.derecho, dato);
	}
	
	public void remove (T dato) {
		raiz = removeRec(raiz, dato);
    }
	
    private Nodo<T> removeRec(Nodo<T> actual, T dato) {
		if (actual == null) return null;
		if (dato.compareTo (actual.dato) < 0) {
		    actual.izquierdo = removeRec(actual.izquierdo, dato);
		} else if (dato.compareTo (actual.dato) > 0) {
		    actual.derecho = removeRec(actual.derecho, dato);
		} else {
		    if (actual.izquierdo == null && actual.derecho == null)
		        return null;
		    if (actual.izquierdo == null) return actual.derecho;
		    if (actual.derecho == null) return actual.izquierdo;
		 
		    Nodo<T> sucesor = obtenerMenor(actual.derecho);
		    actual.dato = sucesor.dato;
		    actual.derecho = removeRec(actual.derecho, sucesor.dato);
		}
		return actual;
	}
   	
    private Nodo<T> obtenerMenor(Nodo<T> actual) {
		while (actual.izquierdo != null)
		    actual = actual.izquierdo;
		return actual;
	}
		
    private Nodo<T> obtenerMayor(Nodo<T> actual) {
	    while (actual.derecho != null)
		    actual = actual.derecho;
		return actual;
    }	
		
    public T minimo() {
		if (isEmpty()) return null;
		return obtenerMenor(raiz).dato;
	}
		
    public T maximo() {
		 if (isEmpty()) return null;
		 return obtenerMayor(raiz).dato;
	}
	
	public void inOrden() {
		System.out.print("InOrden: ");
		inOrdenRec(raiz);
		System.out.println();
	}
	
	private void inOrdenRec(Nodo<T> nodo) {
		if (nodo != null) {
		    inOrdenRec(nodo.izquierdo);
		    System.out.print(nodo.dato + " ");
		    inOrdenRec(nodo.derecho);
		 }
	}
	
	public void preOrden() {
		System.out.print("PreOrden: ");
		preOrdenRec(raiz);
		System.out.println();
	}
		
	private void preOrdenRec(Nodo<T> nodo) {
		if (nodo != null) {
		    System.out.print(nodo.dato + " ");
		    preOrdenRec(nodo.izquierdo);
		    preOrdenRec(nodo.derecho);
		 }
	}
	
	public void postOrden () {
		System.out.print("PostOrden: ");
		postOrdenRec(raiz);
		System.out.println();
	}
		
	private void postOrdenRec(Nodo<T> nodo) {
		 if (nodo != null) {
		     postOrdenRec(nodo.izquierdo);
		     postOrdenRec(nodo.derecho);
		     System.out.print(nodo.dato + " ");
		 }
	}
	
	public int altura() {
		if (isEmpty()) return -1;
		Queue<Nodo<T>> cola = new LinkedList<>();
		cola.add(raiz);
	    int altura = -1;
		while (!cola.isEmpty()) {
		    int nivel = cola.size();
		    altura ++;
		    for (int i = 0; i < nivel ; i++) {
		        Nodo<T> actual = cola.poll();
		        if (actual.izquierdo != null) cola.add(actual.izquierdo);
		        if (actual.derecho != null) cola.add(actual.derecho);
		    }
		}
		return altura;
    }
	
	public int contarNodos() {
		return contarRec(raiz);
	}
		
    private int contarRec(Nodo<T> nodo) {
		if (nodo == null) return 0;
		return 1 + contarRec(nodo.izquierdo) + contarRec(nodo.derecho);
	}
		
	public int contarHojas() {
		return contarHojasRec (raiz);
	}
		
	private int contarHojasRec(Nodo<T> nodo) {
		if (nodo == null) return 0;
		if (nodo.izquierdo == null && nodo.derecho == null) return 1;
		return contarHojasRec(nodo.izquierdo) + contarHojasRec(nodo.derecho);
	}
	
	public int areaBST() {
		return contarHojas() * altura();
	}	
		
	public boolean isValidBST() {
		 return isValidRec(raiz, null, null);
	}
		
	private boolean isValidRec(Nodo<T> nodo, T min, T max) {
		if (nodo == null) return true;
		if (min != null && nodo.dato.compareTo(min) <= 0) return false;
		if (max != null && nodo.dato.compareTo(max) >= 0) return false;
		return isValidRec(nodo.izquierdo, min, nodo.dato) && isValidRec(nodo.derecho, nodo.dato, max);
	}
	
	public void buscarRango(T min, T max) {
		System.out.print("Rango [" + min + ", " + max + "]: ");
		buscarRangoRec(raiz, min, max);
		System.out.println();
	}
		
	private void buscarRangoRec(Nodo<T> nodo, T min, T max) {
		 if (nodo == null) return;
		 if (nodo.dato.compareTo(min) > 0)
		     buscarRangoRec (nodo.izquierdo, min, max);
		 if (nodo.dato.compareTo(min) >= 0 && nodo.dato.compareTo(max) <= 0)
		     System.out.print(nodo.dato + " ");
		 if (nodo.dato.compareTo (max) < 0)
		     buscarRangoRec (nodo.derecho, min, max);
	}
		
	public void imprimirDescendente() {
		System.out.print("Descendente: ");
		descendenteRec(raiz);
		System.out.println();
	}
		
	private void descendenteRec(Nodo<T> nodo) {
		if (nodo != null) {
		    descendenteRec(nodo.derecho);
		    System.out.print(nodo.dato + " ");
		    descendenteRec(nodo.izquierdo);
		}
	}
	
	public void destroy() {
		raiz = null; 
	}
		
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toStringRec(raiz, sb);
		return sb.toString().trim();
	}
		
	private void toStringRec(Nodo<T> nodo, StringBuilder sb) {
		 if (nodo != null) {
		     toStringRec(nodo.izquierdo, sb);
		     sb.append (nodo.dato).append (" ");
		     toStringRec(nodo.derecho, sb);
		 }
	}
}