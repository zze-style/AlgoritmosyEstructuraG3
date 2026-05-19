package AVLTree;

public class NodeAVL<T extends Comparable<T>> extends Nodo<T> { 
	protected int bf; // factor de equilibrio
	
	public NodeAVL(T dato) {
	    super(dato);
	    this.bf = 0;
	}
	
	@Override
	public String toString() {
	    return dato + "(bf=" + bf + ")";
	}
}  