package AVLTree;

public class Arbol<T extends Comparable<T>> {
	protected Nodo<T> raiz;

    public Arbol() {
        this.raiz = null;
    }

    public void insertar(T dato) {
    	raiz = insertarRec(raiz, dato);
    }
    
    private Nodo<T> insertarRec(Nodo<T> nodo, T dato) {

        if (nodo == null)
            return new Nodo<>(dato);

        int cmp = dato.compareTo(nodo.dato);

        if (cmp < 0) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, dato);

        } else if (cmp > 0) {
            nodo.derecho = insertarRec(nodo.derecho, dato);
        }

        return nodo;
    }
    
    public boolean buscar(T dato) {
        return buscarRec(raiz, dato);
    }

    private boolean buscarRec(Nodo<T> nodo, T dato) {

        if (nodo == null)
            return false;

        int cmp = dato.compareTo(nodo.dato);

        if (cmp == 0)
            return true;

        if (cmp < 0)
            return buscarRec(nodo.izquierdo, dato);

        return buscarRec(nodo.derecho, dato);
    }

    public int altura() {
        return alturaRec(raiz);
    }

    private int alturaRec(Nodo<T> nodo) {

        if (nodo == null)
            return -1;

        int izq = alturaRec(nodo.izquierdo);
        int der = alturaRec(nodo.derecho);

        return Math.max(izq, der) + 1;
    }
    
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        inOrden(raiz, sb);

        return sb.toString();
    }
    
    public void bfsPorNiveles() {
    	int h = altura();
    	System.out.println("Recorrido por niveles (BFS):");
    	for (int nivel = 0; nivel <= h; nivel++) {
    		
    	    System.out.print("Nivel " + nivel + ": ");
    	    imprimirNivelExplicito(raiz, nivel);
    	    System.out.println();
    	 }
    }
    	
    private void imprimirNivelExplicito(Nodo<T> nodo, int nivel) {
    	if (nodo == null) return;
    	if (nivel == 0) {
    	    System.out.print(nodo.dato + " ");
    	} else {
    	    imprimirNivelExplicito(nodo.izquierdo, nivel - 1);
    	    imprimirNivelExplicito(nodo.derecho, nivel - 1);
    	}
    }

    private void inOrden(Nodo<T> nodo, StringBuilder sb) {

        if (nodo != null) {
            inOrden(nodo.izquierdo, sb);
            sb.append(nodo.dato).append(" ");
            inOrden(nodo.derecho, sb);
        }
    }
    
    public void remove(T dato) {
    }
    
    public void bfsRecursivo() {
    	System.out.print("BFS: ");
    	int h = altura();
    	for (int nivel = 0; nivel <= h; nivel++) {
    	    imprimirNivel(raiz, nivel);
    	}
    	System.out.println();
    }
    	
    // Metodo auxiliar : imprime los nodos del nivel indicado
    private void imprimirNivel(Nodo<T> nodo, int nivel) {
    	if (nodo == null) return;
    	if (nivel == 0) {
    	    System.out.print(nodo.dato + " ");
    	} else {
    	    imprimirNivel(nodo.izquierdo, nivel - 1);
    	    imprimirNivel(nodo.derecho, nivel - 1);
    	}
    }
}