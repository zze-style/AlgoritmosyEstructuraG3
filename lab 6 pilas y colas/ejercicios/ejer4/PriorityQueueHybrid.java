package ejer4;

public class PriorityQueueHybrid<E extends Comparable<E>> {

    // Nodo interno para la lista ordenada de cada nivel
    private class SortedNode {
        E data;
        SortedNode next;

        SortedNode(E data) {
            this.data = data;
            this.next = null;
        }
    }

    private SortedNode[] heads; // cabeza de cada lista ordenada
    private int levels;

    @SuppressWarnings("unchecked")
    public PriorityQueueHybrid(int levels) {
        this.levels = levels;
        heads = new PriorityQueueHybrid.SortedNode[levels];
        for (int i = 0; i < levels; i++) heads[i] = null;
    }

    // Inserta x en el nivel 'priority', ordenado por valor secundario (compareTo)
    public void enqueue(E x, int priority) {
        if (priority < 0 || priority >= levels) {
            System.out.println("Prioridad invalida");
            return;
        }
        SortedNode newNode = new SortedNode(x);

        // Insertar ordenado dentro del nivel (menor compareTo primero)
        if (heads[priority] == null || x.compareTo(heads[priority].data) <= 0) {
            newNode.next = heads[priority];
            heads[priority] = newNode;
        } else {
            SortedNode current = heads[priority];
            while (current.next != null
                    && current.next.data.compareTo(x) < 0) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    // Extrae el primer elemento del nivel de mayor prioridad no vacio
    public E dequeue() throws ExceptionIsEmpty {
        for (int i = levels - 1; i >= 0; i--) {
            if (heads[i] != null) {
                E dato = heads[i].data;
                heads[i] = heads[i].next;
                return dato;
            }
        }
        throw new ExceptionIsEmpty("Cola hibrida vacia");
    }

    public boolean isEmpty() {
        for (int i = 0; i < levels; i++) {
            if (heads[i] != null) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = levels - 1; i >= 0; i--) {
            sb.append("Nivel ").append(i).append(": ");
            SortedNode current = heads[i];
            while (current != null) {
                sb.append("(").append(current.data).append(")");
                if (current.next != null) sb.append(" -> ");
                current = current.next;
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}