package AVLTree;

public class Nodo<T extends Comparable<T>> {
	protected T dato;
	protected Nodo<T> izquierdo;
	protected Nodo<T> derecho;
	private boolean height; 

	public Nodo(T dato) {
	    this.dato = dato;
	    this.izquierdo = null;
	    this.derecho = null;
	 }
} 