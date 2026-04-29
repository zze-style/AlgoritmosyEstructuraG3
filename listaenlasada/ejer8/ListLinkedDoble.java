package ejer8;
import java.util.Random;

public class ListLinkedDoble<T> {
    protected NodeDoble<T> head;
    protected NodeDoble<T> tail;
    protected NodeDoble<T> current;
    protected int size;

    public ListLinkedDoble() {
        head = tail = current = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    // Insertar al final
    public void insertLast(T x) {
        NodeDoble<T> newNode = new NodeDoble<>(x);

        if (head == null) {
            head = tail = current = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // Avanzar
    public T next() {
        if (current == null || current.next == null) {
            System.out.println("No hay siguiente.");
            return null;
        }
        current = current.next;
        return current.value;
    }

    // Retroceder
    public T prev() {
        if (current == null || current.prev == null) {
            System.out.println("No hay anterior.");
            return null;
        }
        current = current.prev;
        return current.value;
    }

    // Mostrar lista
    public void print() {
        NodeDoble<T> temp = head;
        int i = 1;

        while (temp != null) {
            String mark = (temp == current) ? "► " : "  ";
            System.out.println(mark + i + ". " + temp.value);
            temp = temp.next;
            i++;
        }
    }

    // Longitud
    public int size() {
        return size;
    }
    
    //Mescla
    public void shuffle() {
        if (size <= 1) return;

        Random rnd = new Random();

        // Recorremos la lista
        NodeDoble<T> iNode = head;
        for (int i = 0; i < size; i++) {

            // Elegir índice aleatorio entre i y size-1
            int j = i + rnd.nextInt(size - i);

            // Obtener nodo en posición j
            NodeDoble<T> jNode = head;
            for (int k = 0; k < j; k++) {
                jNode = jNode.next;
            }

            // Intercambiar valores
            T temp = iNode.value;
            iNode.value = jNode.value;
            jNode.value = temp;

            // Avanzar iNode
            iNode = iNode.next;
        }

        current = head;
    }

    // Obtener actual
    public T getCurrent() {
        return current != null ? current.value : null;
    }
}