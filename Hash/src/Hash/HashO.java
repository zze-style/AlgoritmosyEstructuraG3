package Hash;

import listaenlasada.ListLinked;
import listaenlasada.Node;

/**
 * Clase que implementa una tabla hash con encadenamiento (listas enlazadas).
 * Reutiliza ListLinked<E> del repositorio del grupo en vez de java.util.LinkedList.
 */
public class HashO {
    private ListLinked<Register>[] table; // Arreglo de listas enlazadas
    private int size;

    @SuppressWarnings("unchecked")
    public HashO(int size) {
        this.size = size;
        this.table = new ListLinked[size];
        for (int i = 0; i < size; i++) {
            table[i] = new ListLinked<>();
        }
    }

    private int hash(int key) {
        return key % size;
    }

    public void insert(Register reg) {
        int idx = hash(reg.getKey());
        table[idx].insertLast(reg);
    }

    public Register search(int key) {
        int idx = hash(key);
        Node<Register> current = table[idx].first;
        while (current != null) {
            if (current.value.getKey() == key) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public void delete(int key) {
        int idx = hash(key);
        Register found = search(key);
        if (found != null) {
            table[idx].removeNode(found);
        }
    }

    public void printTable() {
        for (int i = 0; i < size; i++) {
            StringBuilder sb = new StringBuilder("[" + i + "]: ");
            Node<Register> current = table[i].first;
            while (current != null) {
                sb.append(current.value);
                if (current.next != null) sb.append(" -> ");
                current = current.next;
            }
            if (table[i].first == null) sb.append("vacio");
            System.out.println(sb);
        }
    }
} 