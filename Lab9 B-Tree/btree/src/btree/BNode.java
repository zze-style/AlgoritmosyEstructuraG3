package btree;

import java.util.ArrayList;

public class BNode<E> {
    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;
    private static int contadorId = 0;
    private int idNode;

    public BNode(int n) {
        this.keys = new ArrayList<>(n);
        this.childs = new ArrayList<>(n + 1);
        this.count = 0;
        this.idNode = ++contadorId;
        for (int i = 0; i < n; i++) {
            this.keys.add(null);
            this.childs.add(null);
        }
        this.childs.add(null); // n+1 hijos
    }

    // Verifica si el nodo esta lleno (tiene maxKeys claves)
    public boolean nodeFull(int maxKeys) {
        return this.count == maxKeys;
    }

    // Verifica si el nodo esta vacio
    public boolean nodeEmpty() {
        return this.count == 0;
    }

    // Busca la clave cl en el nodo actual.
    // Si la encuentra retorna true y coloca la posicion en pos[0].
    // Si no la encuentra retorna false y coloca en pos[0] el indice
    // del hijo por donde se debe descender.
    @SuppressWarnings("unchecked")
    public boolean searchNode(E cl, int[] pos) {
        int i = 0;
        Comparable<E> cmpKey = (Comparable<E>) cl;
        while (i < this.count && cmpKey.compareTo(this.keys.get(i)) > 0) {
            i++;
        }
        if (i < this.count && cmpKey.compareTo(this.keys.get(i)) == 0) {
            pos[0] = i;
            return true; // encontrado
        }
        pos[0] = i; // posicion del hijo por donde descender
        return false;
    }

    public int getIdNode() {
        return idNode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Id:").append(idNode).append(" | keys: ");
        for (int i = 0; i < this.count; i++) {
            if (i > 0) sb.append(", ");
            sb.append(this.keys.get(i));
        }
        sb.append("]");
        return sb.toString();
    }
}
