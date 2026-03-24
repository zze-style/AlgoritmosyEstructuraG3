package eje2gene;

public class IntercambioGenerico {

    /**
     * Método genérico que intercambia dos elementos dentro de un arreglo.
     * 
     * @param <T> Tipo genérico
     * @param arreglo Arreglo de elementos
     * @param i índice 1
     * @param j índice 2
     */
    public static <T> void intercambiar(T[] arreglo, int i, int j) {

        if (arreglo == null) {
            throw new IllegalArgumentException("El arreglo no puede ser nulo");
        }

        if (i < 0 || j < 0 || i >= arreglo.length || j >= arreglo.length) {
            throw new IndexOutOfBoundsException("Índices fuera de rango");
        }

        T temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
    }
}