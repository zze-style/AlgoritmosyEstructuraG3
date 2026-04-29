package ejer8;

public class ColaReproduccion<T> {
	private ListLinkedDoble<T> listad = new ListLinkedDoble<>();

    public void agregarCancion(T cancion) {
        listad.insertLast(cancion);
    }

    public T reproducirSiguiente() {
        return listad.next();
    }

    public T reproducirAnterior() {
        return listad.prev();
    }

    public void mezclar() {
        listad.shuffle();
    }

    public void mostrarCola() {
        listad.print();
    }

    // Método específico para Cancion
    public int duracionTotal() {
        int total = 0;
        NodeDoble<T> temp = listad.head;

        while (temp != null) {
            if (temp.value instanceof Cancion) {
                total += ((Cancion) temp.value).getDuracion();
            }
            temp = temp.next;
        }

        return total;
    }
}