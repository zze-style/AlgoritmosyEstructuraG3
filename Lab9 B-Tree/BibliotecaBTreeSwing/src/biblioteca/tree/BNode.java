package biblioteca.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BNode<E extends Comparable<E>> {
    private static int nextId = 1;

    final ArrayList<E> keys;
    final ArrayList<BNode<E>> children;
    int count;
    private final int id;

    BNode(int order) {
        keys = new ArrayList<>(Collections.nCopies(order, null));
        children = new ArrayList<>(Collections.nCopies(order + 1, null));
        id = nextId++;
    }

    boolean isFull(int maxKeys) {
        return count == maxKeys;
    }

    boolean searchNode(E key, int[] position) {
        int i = 0;
        while (i < count && key.compareTo(keys.get(i)) > 0) {
            i++;
        }
        position[0] = i;
        return i < count && key.compareTo(keys.get(i)) == 0;
    }

    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public List<E> getKeys() {
        return Collections.unmodifiableList(keys.subList(0, count));
    }

    public List<BNode<E>> getChildren() {
        return Collections.unmodifiableList(children.subList(0, count + 1));
    }

    public boolean isLeaf() {
        return children.get(0) == null;
    }

    @Override
    public String toString() {
        return "Nodo " + id + " " + getKeys();
    }
}
