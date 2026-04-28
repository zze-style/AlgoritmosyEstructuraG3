package listaenlasada;

public class GestorDeTareas<T extends Comparable<T>> {
    private ListLinked<T> lista;

    public GestorDeTareas() {
        lista = new ListLinked<>();
    }

    public void agregarTarea(T tarea) {
        lista.insertLast(tarea);
    }

    public boolean eliminarTarea(T tarea) {
        return lista.removeNode(tarea);
    }

    public boolean contieneTarea(T tarea) {
        return lista.search(tarea);
    }

    public void imprimirTareas() {
        lista.print();
    }

    public int contarTareas() {
        return lista.length();
    }

    // Retorna el elemento con menor valor segun compareTo (mayor prioridad)
    public T obtenerTareaMasPrioritaria() {
        if (lista.isEmptyList()) return null;
        Node<T> current = lista.first;
        T minimo = current.value;
        current = current.next;
        while (current != null) {
            if (current.value.compareTo(minimo) < 0) minimo = current.value;
            current = current.next;
        }
        return minimo;
    }

    public void invertirTareas() {
        lista.reverse();
    }

    // Transfiere las tareas cuyo estado es "completada" a una nueva lista
    public ListLinked<T> separarCompletadas() {
        ListLinked<T> completadas = new ListLinked<>();
        Node<T> current = lista.first;
        while (current != null) {
            Node<T> siguiente = current.next;
            if (current.value instanceof Tarea) {
                Tarea t = (Tarea) current.value;
                if (t.getEstado().equals("completada")) {
                    completadas.insertLast(current.value);
                    lista.removeNode(current.value);
                }
            }
            current = siguiente;
        }
        return completadas;
    }
}