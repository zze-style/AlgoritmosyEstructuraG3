package btree;

public class BTree<E extends Comparable<E>> {
    private BNode<E> root;
    private int orden;
    private boolean up;
    private BNode<E> nDes;

    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    // Insercion principal
    public void insert(E cl) {
        up = false;
        E mediana;
        BNode<E> pnew;
        mediana = push(this.root, cl);
        if (up) {
            pnew = new BNode<>(this.orden);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root);
            pnew.childs.set(1, nDes);
            this.root = pnew;
        }
    }

    private E push(BNode<E> current, E cl) {
        int[] pos = new int[1];
        E mediana;
        if (current == null) {
            up = true;
            nDes = null;
            return cl;
        } else {
            boolean fl;
            fl = current.searchNode(cl, pos);
            if (fl) {
                System.out.println("Item duplicado: " + cl);
                up = false;
                return null;
            }
            mediana = push(current.childs.get(pos[0]), cl);
            if (up) {
                if (current.nodeFull(this.orden - 1))
                    mediana = dividedNode(current, mediana, pos[0]);
                else {
                    up = false;
                    putNode(current, mediana, nDes, pos[0]);
                }
            }
            return mediana;
        }
    }

    // Inserta la clave cl y el puntero rd en la posicion k del nodo current
    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
        for (int i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }
        current.keys.set(k, cl);
        current.childs.set(k + 1, rd);
        current.count++;
    }

    // Divide el nodo lleno y retorna la clave mediana
    private E dividedNode(BNode<E> current, E cl, int k) {
        BNode<E> rd = nDes;
        int posMdna;
        posMdna = (k <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1;
        nDes = new BNode<>(this.orden);
        for (int i = posMdna; i < this.orden - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }
        nDes.count = (this.orden - 1) - posMdna;
        current.count = posMdna;
        if (k <= this.orden / 2)
            putNode(current, cl, rd, k);
        else
            putNode(nDes, cl, rd, k - posMdna);
        E median = current.keys.get(current.count - 1);
        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;
        return median;
    }

    // toString: recorre el arbol nivel a nivel (BFS)
    public String toString() {
        if (isEmpty()) return "BTree is empty...";
        return writeTree(this.root);
    }

    private String writeTree(BNode<E> current) {
        if (current == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(current.toString()).append("\n");
        for (int i = 0; i <= current.count; i++) {
            if (current.childs.get(i) != null) {
                sb.append(writeTree(current.childs.get(i)));
            }
        }
        return sb.toString();
    }

    public BNode<E> getRoot() {
        return root;
    }

    public int getOrden() {
        return orden;
    }
    
    public boolean search(E cl) {
        return searchRec(this.root, cl);
    }

    private boolean searchRec(BNode<E> current, E cl) {
        if (current == null) return false;
        int[] pos = new int[1];
        boolean found = current.searchNode(cl, pos);
        if (found) {
            System.out.println(cl + " se encuentra en el nodo "
                    + current.getIdNode()
                    + " en la posicion " + pos[0]);
            return true;
        }
        return searchRec(current.childs.get(pos[0]), cl);
    }
    
    public void searchRange(E min, E max) {
        if (min.compareTo(max) > 0) {
            System.out.println("Rango invalido: min > max");
            return;
        }
        System.out.print("Rango [" + min + ", " + max + "]: ");
        searchRangeRec(this.root, min, max);
        System.out.println();
    }

    private void searchRangeRec(BNode<E> current, E min, E max) {
        if (current == null) return;
        int i = 0;
        while (i < current.count) {
            // Si la clave actual es >= min, explorar hijo izquierdo
            if (current.keys.get(i).compareTo(min) >= 0) {
                searchRangeRec(current.childs.get(i), min, max);
            }
            // Si la clave actual esta en el rango, imprimirla
            if (current.keys.get(i).compareTo(min) >= 0
                    && current.keys.get(i).compareTo(max) <= 0) {
                System.out.print(current.keys.get(i) + " ");
            }
            // Si la clave supera max, no hay nada mas a la derecha
            if (current.keys.get(i).compareTo(max) > 0) {
                return;
            }
            i++;
        }
        // Explorar el ultimo hijo si corresponde
        searchRangeRec(current.childs.get(i), min, max);
    }
    
    private boolean shrink; // indica si el hijo disminuyo su minimo de claves

    public void remove(E cl) {
        shrink = false;
        if (!search(cl)) {
            System.out.println(cl + " no encontrado.");
            return;
        }
        removeRec(this.root, cl);
        // Si la raiz quedo vacia, el arbol decrece en altura
        if (this.root != null && this.root.count == 0) {
            this.root = this.root.childs.get(0);
        }
    }

    @SuppressWarnings("unchecked")
    private void removeRec(BNode<E> current, E cl) {
        if (current == null) return;
        int[] pos = new int[1];
        boolean found = current.searchNode(cl, pos);

        if (found) {
            // Si es nodo hoja: eliminar directamente
            if (current.childs.get(0) == null) {
                // Desplazar claves hacia la izquierda
                for (int i = pos[0]; i < current.count - 1; i++) {
                    current.keys.set(i, current.keys.get(i + 1));
                }
                current.keys.set(current.count - 1, null);
                current.count--;
                shrink = true;
            } else {
                // Nodo interno: reemplazar por sucesor inorden
                BNode<E> sucesorNodo = obtenerSucesor(current, pos[0]);
                E sucesor = sucesorNodo.keys.get(0);
                current.keys.set(pos[0], sucesor);
                removeRec(current.childs.get(pos[0] + 1), sucesor);
                if (shrink) {
                    fixDeficiency(current, pos[0] + 1);
                }
            }
        } else {
            // Descender por el hijo correspondiente
            removeRec(current.childs.get(pos[0]), cl);
            if (shrink) {
                fixDeficiency(current, pos[0]);
            }
        }
    }

    // Obtiene el nodo hoja con el sucesor inorden de keys[pos] en current
    private BNode<E> obtenerSucesor(BNode<E> current, int pos) {
        BNode<E> nodo = current.childs.get(pos + 1);
        while (nodo.childs.get(0) != null) {
            nodo = nodo.childs.get(0);
        }
        return nodo;
    }

    // Repara la deficiencia del hijo en la posicion childPos de current
    private void fixDeficiency(BNode<E> current, int childPos) {
        int minKeys = (this.orden / 2) - 1;
        BNode<E> child = current.childs.get(childPos);
        if (child != null && child.count >= minKeys + 1) {
            shrink = false; // ya tiene suficientes claves
            return;
        }
        // Intentar redistribucion con hermano izquierdo
        if (childPos > 0) {
            BNode<E> leftSibling = current.childs.get(childPos - 1);
            if (leftSibling.count > minKeys) {
                redistributeFromLeft(current, childPos);
                shrink = false;
                return;
            }
        }
        // Intentar redistribucion con hermano derecho
        if (childPos < current.count) {
            BNode<E> rightSibling = current.childs.get(childPos + 1);
            if (rightSibling != null && rightSibling.count > minKeys) {
                redistributeFromRight(current, childPos);
                shrink = false;
                return;
            }
        }
        // Fusionar con hermano izquierdo o derecho
        if (childPos > 0) {
            mergeNodes(current, childPos - 1); // fusionar con izquierdo
        } else {
            mergeNodes(current, childPos);     // fusionar con derecho
        }
        shrink = (current.count < minKeys);
    }

    // Redistribucion desde el hermano izquierdo
    private void redistributeFromLeft(BNode<E> parent, int childPos) {
        BNode<E> child = parent.childs.get(childPos);
        BNode<E> leftSib = parent.childs.get(childPos - 1);
        // Desplazar claves del hijo a la derecha
        for (int i = child.count; i > 0; i--) {
            child.keys.set(i, child.keys.get(i - 1));
            child.childs.set(i + 1, child.childs.get(i));
        }
        child.childs.set(1, child.childs.get(0));
        // Bajar clave separadora del padre al hijo
        child.keys.set(0, parent.keys.get(childPos - 1));
        child.childs.set(0, leftSib.childs.get(leftSib.count));
        child.count++;
        // Subir ultima clave del hermano izquierdo al padre
        parent.keys.set(childPos - 1, leftSib.keys.get(leftSib.count - 1));
        leftSib.keys.set(leftSib.count - 1, null);
        leftSib.childs.set(leftSib.count, null);
        leftSib.count--;
    }

    // Redistribucion desde el hermano derecho
    private void redistributeFromRight(BNode<E> parent, int childPos) {
        BNode<E> child = parent.childs.get(childPos);
        BNode<E> rightSib = parent.childs.get(childPos + 1);
        // Bajar clave separadora del padre al final del hijo
        child.keys.set(child.count, parent.keys.get(childPos));
        child.childs.set(child.count + 1, rightSib.childs.get(0));
        child.count++;
        // Subir primera clave del hermano derecho al padre
        parent.keys.set(childPos, rightSib.keys.get(0));
        // Desplazar claves del hermano derecho a la izquierda
        for (int i = 0; i < rightSib.count - 1; i++) {
            rightSib.keys.set(i, rightSib.keys.get(i + 1));
            rightSib.childs.set(i, rightSib.childs.get(i + 1));
        }
        rightSib.childs.set(rightSib.count - 1, rightSib.childs.get(rightSib.count));
        rightSib.keys.set(rightSib.count - 1, null);
        rightSib.childs.set(rightSib.count, null);
        rightSib.count--;
    }

    // Fusionar el hijo childPos con su hermano derecho
    // La clave separadora en parent.keys[sepPos] desciende al nodo fusionado
    private void mergeNodes(BNode<E> parent, int sepPos) {
        BNode<E> left = parent.childs.get(sepPos);
        BNode<E> right = parent.childs.get(sepPos + 1);
        // Bajar clave separadora del padre al nodo izquierdo
        left.keys.set(left.count, parent.keys.get(sepPos));
        left.childs.set(left.count + 1, right.childs.get(0));
        left.count++;
        // Copiar claves e hijos del nodo derecho al izquierdo
        for (int i = 0; i < right.count; i++) {
            left.keys.set(left.count, right.keys.get(i));
            left.childs.set(left.count + 1, right.childs.get(i + 1));
            left.count++;
        }
        // Eliminar la clave separadora y el hijo derecho del padre
        for (int i = sepPos; i < parent.count - 1; i++) {
            parent.keys.set(i, parent.keys.get(i + 1));
            parent.childs.set(i + 1, parent.childs.get(i + 2));
        }
        parent.keys.set(parent.count - 1, null);
        parent.childs.set(parent.count, null);
        parent.count--;
    }
}