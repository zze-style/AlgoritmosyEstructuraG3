package arboles;

public class Nodo <T extends Comparable<T>>  {
	
	T dato;
	Nodo <T> izquierdo;
	Nodo <T> derecho;
	
	public Nodo (T dato) {
		
	    this.dato = dato;
	    this.izquierdo = null;
	    this.derecho = null;
	}
}