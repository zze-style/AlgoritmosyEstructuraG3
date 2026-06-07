package biblioteca.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BTree<E extends Comparable<E>> {
    private BNode<E> root;
    private final int order;
    private boolean up;
    private boolean inserted;
    private boolean removed;
    private boolean shrink;
    private BNode<E> splitRight;

    public BTree(int order) {
        if (order < 3) {
            throw new IllegalArgumentException("El orden del arbol B debe ser al menos 3.");
        }
        this.order = order;
    }

    public boolean insert(E key) {
        up = false;
        inserted = true;
        E median = push(root, key);
        if (up) {
            BNode<E> newRoot = new BNode<>(order);
            newRoot.count = 1;
            newRoot.keys.set(0, median);
            newRoot.children.set(0, root);
            newRoot.children.set(1, splitRight);
            root = newRoot;
        }
        return inserted;
    }

    private E push(BNode<E> current, E key) {
        int[] position = new int[1];
        if (current == null) {
            up = true;
            splitRight = null;
            return key;
        }
        if (current.searchNode(key, position)) {
            up = false;
            inserted = false;
            return null;
        }
        E median = push(current.children.get(position[0]), key);
        if (up) {
            if (current.isFull(order - 1)) {
                median = splitNode(current, median, position[0]);
            } else {
                up = false;
                putNode(current, median, splitRight, position[0]);
            }
        }
        return median;
    }

    private void putNode(BNode<E> current, E key, BNode<E> rightChild, int position) {
        for (int i = current.count - 1; i >= position; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.children.set(i + 2, current.children.get(i + 1));
        }
        current.keys.set(position, key);
        current.children.set(position + 1, rightChild);
        current.count++;
    }

    private E splitNode(BNode<E> current, E key, int position) {
        BNode<E> rightChild = splitRight;
        int medianPosition = position <= order / 2 ? order / 2 : order / 2 + 1;
        splitRight = new BNode<>(order);

        for (int i = medianPosition; i < order - 1; i++) {
            splitRight.keys.set(i - medianPosition, current.keys.get(i));
            splitRight.children.set(i - medianPosition + 1, current.children.get(i + 1));
            current.keys.set(i, null);
            current.children.set(i + 1, null);
        }
        splitRight.count = (order - 1) - medianPosition;
        current.count = medianPosition;

        if (position <= order / 2) {
            putNode(current, key, rightChild, position);
        } else {
            putNode(splitRight, key, rightChild, position - medianPosition);
        }

        E median = current.keys.get(current.count - 1);
        splitRight.children.set(0, current.children.get(current.count));
        current.keys.set(current.count - 1, null);
        current.children.set(current.count, null);
        current.count--;
        return median;
    }

    public Optional<E> search(E key) {
        return searchRec(root, key);
    }

    private Optional<E> searchRec(BNode<E> current, E key) {
        if (current == null) {
            return Optional.empty();
        }
        int[] position = new int[1];
        if (current.searchNode(key, position)) {
            return Optional.of(current.keys.get(position[0]));
        }
        return searchRec(current.children.get(position[0]), key);
    }

    public boolean remove(E key) {
        if (root == null) {
            return false;
        }
        shrink = false;
        removed = false;
        removeRec(root, key);
        if (root != null && root.count == 0) {
            root = root.children.get(0);
        }
        return removed;
    }

    private void removeRec(BNode<E> current, E key) {
        if (current == null) {
            shrink = false;
            return;
        }
        int[] position = new int[1];
        boolean found = current.searchNode(key, position);

        if (found) {
            removed = true;
            if (current.isLeaf()) {
                removeKeyAt(current, position[0]);
                shrink = true;
            } else {
                BNode<E> successorNode = getSuccessorNode(current, position[0]);
                E successor = successorNode.keys.get(0);
                current.keys.set(position[0], successor);
                removeRec(current.children.get(position[0] + 1), successor);
                if (shrink) {
                    fixDeficiency(current, position[0] + 1);
                }
            }
        } else {
            removeRec(current.children.get(position[0]), key);
            if (shrink) {
                fixDeficiency(current, position[0]);
            }
        }
    }

    private void removeKeyAt(BNode<E> node, int position) {
        for (int i = position; i < node.count - 1; i++) {
            node.keys.set(i, node.keys.get(i + 1));
        }
        node.keys.set(node.count - 1, null);
        node.count--;
    }

    private BNode<E> getSuccessorNode(BNode<E> current, int position) {
        BNode<E> node = current.children.get(position + 1);
        while (node.children.get(0) != null) {
            node = node.children.get(0);
        }
        return node;
    }

    private void fixDeficiency(BNode<E> parent, int childPosition) {
        int minKeys = minKeys();
        BNode<E> child = parent.children.get(childPosition);
        if (child != null && child.count >= minKeys) {
            shrink = false;
            return;
        }
        if (childPosition > 0) {
            BNode<E> leftSibling = parent.children.get(childPosition - 1);
            if (leftSibling != null && leftSibling.count > minKeys) {
                redistributeFromLeft(parent, childPosition);
                shrink = false;
                return;
            }
        }
        if (childPosition < parent.count) {
            BNode<E> rightSibling = parent.children.get(childPosition + 1);
            if (rightSibling != null && rightSibling.count > minKeys) {
                redistributeFromRight(parent, childPosition);
                shrink = false;
                return;
            }
        }
        if (childPosition > 0) {
            mergeNodes(parent, childPosition - 1);
        } else if (childPosition < parent.count) {
            mergeNodes(parent, childPosition);
        }
        shrink = parent.count < minKeys;
    }

    private int minKeys() {
        return (int) Math.ceil(order / 2.0) - 1;
    }

    private void redistributeFromLeft(BNode<E> parent, int childPosition) {
        BNode<E> child = parent.children.get(childPosition);
        BNode<E> leftSibling = parent.children.get(childPosition - 1);
        for (int i = child.count; i > 0; i--) {
            child.keys.set(i, child.keys.get(i - 1));
            child.children.set(i + 1, child.children.get(i));
        }
        child.children.set(1, child.children.get(0));
        child.keys.set(0, parent.keys.get(childPosition - 1));
        child.children.set(0, leftSibling.children.get(leftSibling.count));
        child.count++;
        parent.keys.set(childPosition - 1, leftSibling.keys.get(leftSibling.count - 1));
        leftSibling.keys.set(leftSibling.count - 1, null);
        leftSibling.children.set(leftSibling.count, null);
        leftSibling.count--;
    }

    private void redistributeFromRight(BNode<E> parent, int childPosition) {
        BNode<E> child = parent.children.get(childPosition);
        BNode<E> rightSibling = parent.children.get(childPosition + 1);
        child.keys.set(child.count, parent.keys.get(childPosition));
        child.children.set(child.count + 1, rightSibling.children.get(0));
        child.count++;
        parent.keys.set(childPosition, rightSibling.keys.get(0));
        for (int i = 0; i < rightSibling.count - 1; i++) {
            rightSibling.keys.set(i, rightSibling.keys.get(i + 1));
            rightSibling.children.set(i, rightSibling.children.get(i + 1));
        }
        rightSibling.children.set(rightSibling.count - 1, rightSibling.children.get(rightSibling.count));
        rightSibling.keys.set(rightSibling.count - 1, null);
        rightSibling.children.set(rightSibling.count, null);
        rightSibling.count--;
    }

    private void mergeNodes(BNode<E> parent, int separatorPosition) {
        BNode<E> left = parent.children.get(separatorPosition);
        BNode<E> right = parent.children.get(separatorPosition + 1);
        left.keys.set(left.count, parent.keys.get(separatorPosition));
        left.children.set(left.count + 1, right.children.get(0));
        left.count++;
        for (int i = 0; i < right.count; i++) {
            left.keys.set(left.count, right.keys.get(i));
            left.children.set(left.count + 1, right.children.get(i + 1));
            left.count++;
        }
        for (int i = separatorPosition; i < parent.count - 1; i++) {
            parent.keys.set(i, parent.keys.get(i + 1));
            parent.children.set(i + 1, parent.children.get(i + 2));
        }
        parent.keys.set(parent.count - 1, null);
        parent.children.set(parent.count, null);
        parent.count--;
    }

    public List<E> inOrder() {
        List<E> values = new ArrayList<>();
        inOrder(root, values);
        return values;
    }

    private void inOrder(BNode<E> node, List<E> values) {
        if (node == null) {
            return;
        }
        int i = 0;
        while (i < node.count) {
            inOrder(node.children.get(i), values);
            values.add(node.keys.get(i));
            i++;
        }
        inOrder(node.children.get(i), values);
    }

    public List<E> range(E min, E max) {
        if (min.compareTo(max) > 0) {
            throw new IllegalArgumentException("El ISBN inicial no puede ser mayor al ISBN final.");
        }
        List<E> values = new ArrayList<>();
        range(root, min, max, values);
        return values;
    }

    private void range(BNode<E> node, E min, E max, List<E> values) {
        if (node == null) {
            return;
        }
        int i = 0;
        while (i < node.count) {
            if (node.keys.get(i).compareTo(min) >= 0) {
                range(node.children.get(i), min, max, values);
            }
            if (node.keys.get(i).compareTo(min) >= 0 && node.keys.get(i).compareTo(max) <= 0) {
                values.add(node.keys.get(i));
            }
            if (node.keys.get(i).compareTo(max) > 0) {
                return;
            }
            i++;
        }
        range(node.children.get(i), min, max, values);
    }

    public int height() {
        int height = 0;
        BNode<E> current = root;
        while (current != null && current.children.get(0) != null) {
            height++;
            current = current.children.get(0);
        }
        return root == null ? -1 : height;
    }

    public int size() {
        return countKeys(root);
    }

    private int countKeys(BNode<E> node) {
        if (node == null) {
            return 0;
        }
        int total = node.count;
        for (int i = 0; i <= node.count; i++) {
            total += countKeys(node.children.get(i));
        }
        return total;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int getOrder() {
        return order;
    }

    public BNode<E> getRoot() {
        return root;
    }
}
