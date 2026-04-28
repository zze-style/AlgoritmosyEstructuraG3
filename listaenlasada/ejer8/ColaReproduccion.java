package ejer8;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ColaReproduccion<T> {
    private NodeDoble<T> head;
    private NodeDoble<T> tail;
    private NodeDoble<T> current; // nodo actualmente en reproduccion
    private int size;

    public ColaReproduccion() {
        head = tail = current = null;
        size = 0;
    }

    // Agrega al final de la cola
    public void agregarCancion(T cancion) {
        NodeDoble<T> newNode = new NodeDoble<>(cancion);
        if (head == null) {
            head = tail = current = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // Avanza al siguiente nodo
    public T reproducirSiguiente() {
        if (current == null || current.next == null) {
            System.out.println("No hay siguiente cancion.");
            return null;
        }
        current = current.next;
        return current.value;
    }

    // Retrocede al nodo anterior (O(1) gracias al puntero prev)
    public T reproducirAnterior() {
        if (current == null || current.prev == null) {
            System.out.println("No hay cancion anterior.");
            return null;
        }
        current = current.prev;
        return current.value;
    }

    // Algoritmo Fisher-Yates adaptado a lista doblemente enlazada
    public void mezclar() {
        if (size <= 1) return;
        // Recolectar valores en un ArrayList
        ArrayList<T> valores = new ArrayList<>();
        NodeDoble<T> temp = head;
        while (temp != null) { valores.add(temp.value); temp = temp.next; }
        // Barajar con Fisher-Yates
        Random rnd = new Random();
        for (int i = valores.size() - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            T swap = valores.get(i);
            valores.set(i, valores.get(j));
            valores.set(j, swap);
        }
        // Reasignar valores a los nodos (mantiene la estructura de punteros)
        temp = head;
        for (T val : valores) { temp.value = val; temp = temp.next; }
        current = head;
    }

    // Imprime la cola indicando la cancion actual
    public void mostrarCola() {
        NodeDoble<T> temp = head;
        int num = 1;
        while (temp != null) {
            String marca = (temp == current) ? "► " : "  ";
            System.out.println(marca + num + ". " + temp.value);
            temp = temp.next;
            num++;
        }
    }

    // Calcula la duracion total (requiere que T tenga getDuracion())
    public int duracionTotal() {
        int total = 0;
        NodeDoble<T> temp = head;
        while (temp != null) {
            if (temp.value instanceof Cancion) {
                total += ((Cancion) temp.value).getDuracion();
            }
            temp = temp.next;
        }
        return total;
    }

    public T getCurrentValue() {
        return current != null ? current.value : null;
    }
}