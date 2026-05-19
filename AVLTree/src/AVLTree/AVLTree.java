package AVLTree;

public class AVLTree<T extends Comparable<T>> extends Arbol<T> {
	 
	private boolean height; // indicador de cambio de altura

	public AVLTree() {
	    super();
	    this.height = false;
	}
	
	@Override
	public void insertar(T dato) {
	    this.height = false;
        this.raiz = insertar(dato, (NodeAVL<T>) this.raiz); 
    }
	
	 private NodeAVL<T> insertar(T x, NodeAVL<T> nodo) {

	        NodeAVL<T> fat = nodo;

	        if (nodo == null) {
	            this.height = true;
	            fat = new NodeAVL<>(x);

	        } else {

	            int resC = nodo.dato.compareTo(x);

	            if (resC == 0)
	                throw new RuntimeException(x + " ya existe");

	            // DERECHA
	            if (resC < 0) {

	                fat.derecho = insertar(x, (NodeAVL<T>) nodo.derecho);

	                if (this.height) {

	                    switch (fat.bf) {

	                    case -1:
	                        fat.bf = 0;
	                        this.height = false;
	                        break;

	                    case 0:
	                        fat.bf = 1;
	                        break;

	                    case 1:
	                        fat = balanceToLeft(fat);
	                        this.height = false;
	                        break;
	                    }
	                }

	            } else {

	                // IZQUIERDA
	                fat.izquierdo = insertar(x, (NodeAVL<T>) nodo.izquierdo);

	                if (this.height) {

	                    switch (fat.bf) {

	                    case 1:
	                        fat.bf = 0;
	                        this.height = false;
	                        break;

	                    case 0:
	                        fat.bf = -1;
	                        break;

	                    case -1:
	                        fat = balanceToRight(fat);
	                        this.height = false;
	                        break;
	                    }
	                }
	            }
	        }

	        return fat;
	    }

	    private NodeAVL<T> balanceToLeft(NodeAVL<T> node) {

	        NodeAVL<T> hijo = (NodeAVL<T>) node.derecho;

	        switch (hijo.bf) {

	        case 1:

	            node.bf = 0;
	            hijo.bf = 0;

	            node = rotateSL(node);
	            break;

	        case -1:

	            NodeAVL<T> nieto = (NodeAVL<T>) hijo.izquierdo;

	            switch (nieto.bf) {

	            case -1:
	                node.bf = 0;
	                hijo.bf = 1;
	                break;

	            case 0:
	                node.bf = 0;
	                hijo.bf = 0;
	                break;

	            case 1:
	                node.bf = -1;
	                hijo.bf = 0;
	                break;
	            }

	            nieto.bf = 0;

	            node.derecho = rotateSR(hijo);
	            node = rotateSL(node);
	        }

	        return node;
	    }

	    private NodeAVL<T> balanceToRight(NodeAVL<T> node) {

	        NodeAVL<T> hijo = (NodeAVL<T>) node.izquierdo;

	        switch (hijo.bf) {

	        case -1:

	            node.bf = 0;
	            hijo.bf = 0;

	            node = rotateSR(node);
	            break;

	        case 1:

	            NodeAVL<T> nieto = (NodeAVL<T>) hijo.derecho;

	            switch (nieto.bf) {

	            case 1:
	                node.bf = 0;
	                hijo.bf = -1;
	                break;

	            case 0:
	                node.bf = 0;
	                hijo.bf = 0;
	                break;

	            case -1:
	                node.bf = 1;
	                hijo.bf = 0;
	                break;
	            }

	            nieto.bf = 0;

	            node.izquierdo = rotateSL(hijo);
	            node = rotateSR(node);
	        }

	        return node;
	    }

	    private NodeAVL<T> rotateSL(NodeAVL<T> node) {

	        NodeAVL<T> p = (NodeAVL<T>) node.derecho;

	        node.derecho = p.izquierdo;
	        p.izquierdo = node;

	        return p;
	    }

	    private NodeAVL<T> rotateSR(NodeAVL<T> node) {

	        NodeAVL<T> p = (NodeAVL<T>) node.izquierdo;

	        node.izquierdo = p.derecho;
	        p.derecho = node;

	        return p;
	    }

	public void inOrden() {
		inOrden(this.raiz);
        System.out.println();
		
	}
	
	private void inOrden(Nodo<T> r) {
		
        if (r != null) {
            inOrden(r.izquierdo);
            System.out.print(r.dato + " ");
            inOrden(r.derecho);
        }
    }


	public int altura() {
        return altura(this.raiz);
    }

    private int altura(Nodo<T> r) {

        if (r == null)
            return 0;

        int izq = altura(r.izquierdo);
        int der = altura(r.derecho);

        return Math.max(izq, der) + 1;
    }

    public boolean buscar(T dato) {
        return buscar(this.raiz, dato);
    }

    private boolean buscar(Nodo<T> nodo, T dato) {

        if (nodo == null)
            return false;

        int cmp = dato.compareTo(nodo.dato);

        if (cmp == 0)
            return true;

        if (cmp < 0)
            return buscar(nodo.izquierdo, dato);

        return buscar(nodo.derecho, dato);
    }
    
    @Override
    public void remove(T dato) {
        this.height = false;
        this.raiz = remove(dato, (NodeAVL<T>) this.raiz);
    }
    
    protected Nodo<T> remove(T x, NodeAVL<T> nodo) {
        if (nodo == null) {
            System.out.println(x + "no encontrado.");
            return null;
        }
    
    NodeAVL<T> fat = nodo;
    int resC = nodo.dato.compareTo(x);
    
    if (resC < 0) {
        // Eliminar por la derecha
        fat.derecho = remove(x, (NodeAVL<T>) nodo.derecho);
        if (this.height) {
            switch (fat.bf) {
            case 1: fat.bf = 0; this.height = true; break;
            case 0: fat.bf = -1; this.height = false; break;
            case -1: fat = balanceToRight(fat); break;
            }
        }
    } else if (resC > 0) {
        // Eliminar por la izquierda
        fat.izquierdo = remove(x, (NodeAVL<T>) nodo.izquierdo);
        if (this.height) {
            switch (fat.bf) {
            case -1: fat.bf = 0; this.height = true; break;
            case 0: fat.bf = 1; this.height = false; break;
            case 1: fat = balanceToLeft(fat); break;
            }
        }
    } else {
        // Nodo encontrado : aplicar casos BST
        if (fat.izquierdo == null && fat.derecho == null) {
            // Caso 1: nodo hoja
            this.height = true;
            return null;
        } else if (fat.izquierdo == null) {
            // Caso 2: solo hijo derecho
            this.height = true;
            return fat.derecho;
        } else if (fat.derecho == null) {
            // Caso 2: solo hijo izquierdo
            this.height = true;
            return fat.izquierdo;
        } else {
            // Caso 3: dos hijos -> usar sucesor inorden
            NodeAVL<T> sucesor = (NodeAVL<T>) obtenerMenor (fat.derecho);
            fat.dato = sucesor.dato;
            fat.derecho = remove(sucesor.dato, (NodeAVL<T>) fat.derecho);
            if (this.height) {
                switch (fat.bf) {
                case -1: fat.bf = 0; this.height = true; break;
                case 0: fat.bf = -1; this.height = false;
                break;
                case 1: fat = balanceToLeft(fat); break;
                }
            }
        }
    }
    return fat;
    }

	private NodeAVL<T> obtenerMenor(Nodo<T> nodo) {
		while (nodo.izquierdo != null) {
	        nodo = nodo.izquierdo;
	    }
	    return (NodeAVL<T>) nodo;
	}   
	
	public void preOrden() {
	    preOrden(this.raiz);
	    System.out.println();
	}

	private void preOrden(Nodo<T> nodo) {

	    if (nodo != null) {
	        System.out.print(nodo.dato + " ");
	        preOrden(nodo.izquierdo);
	        preOrden(nodo.derecho);
	    }
	}
	
	public void postOrden() {
	    postOrden(this.raiz);
	    System.out.println();
	}

	private void postOrden(Nodo<T> nodo) {

	    if (nodo != null) {
	        postOrden(nodo.izquierdo);
	        postOrden(nodo.derecho);
	        System.out.print(nodo.dato + " ");
	    }
	}
}