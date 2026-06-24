package Hash.ejercicios;

public class Ejercicio4 {

    enum Estado { EMPTY, OCCUPIED, DELETED }

    static class Entry {
        int key;
        Estado estado;

        Entry() {
            this.estado = Estado.EMPTY;
        }
    }

    static final int M = 7;

    public static void main(String[] args) {

        int[] claves = {5, 12, 19, 26};

        // ==========================
        // TABLA LINEAL
        // ==========================
        System.out.println("===== SONDEO LINEAL =====");

        Entry[] tablaLineal = new Entry[M];
        for (int i = 0; i < M; i++) tablaLineal[i] = new Entry();

        for (int k : claves) insertarLineal(tablaLineal, k);

        System.out.println("\nTabla despues de inserciones:");
        imprimir(tablaLineal);

        eliminarLogico(tablaLineal, 12);
        System.out.println("\nTras eliminar 12:");
        imprimir(tablaLineal);

        System.out.println("\nBuscar 19 -> posicion: " + buscar(tablaLineal, 19));

        insertarLineal(tablaLineal, 33);
        System.out.println("\nTras insertar 33:");
        imprimir(tablaLineal);


        // ==========================
        // TABLA CUADRATICA
        // ==========================
        System.out.println("\n\n===== SONDEO CUADRATICO =====");

        Entry[] tablaCuadratica = new Entry[M];
        for (int i = 0; i < M; i++) tablaCuadratica[i] = new Entry();

        for (int k : claves) insertarCuadratico(tablaCuadratica, k);

        System.out.println("\nTabla despues de inserciones:");
        imprimir(tablaCuadratica);

        eliminarLogico(tablaCuadratica, 12);
        System.out.println("\nTras eliminar 12:");
        imprimir(tablaCuadratica);

        System.out.println("\nBuscar 19 -> posicion: " + buscar(tablaCuadratica, 19));

        insertarCuadratico(tablaCuadratica, 33);
        System.out.println("\nTras insertar 33:");
        imprimir(tablaCuadratica);
    }

    // ==========================
    // INSERCION LINEAL
    // ==========================
    static void insertarLineal(Entry[] tabla, int key) {
        int h = key % M;
        int saltos = 0;
        int idx = h;

        while (tabla[idx].estado == Estado.OCCUPIED) {
            idx = (idx + 1) % M;
            saltos++;

            if (saltos >= M) {
                System.out.println("Tabla llena, no se pudo insertar " + key);
                return;
            }
        }

        tabla[idx].key = key;
        tabla[idx].estado = Estado.OCCUPIED;

        System.out.println(key + ": hash=" + h +
                ", saltos=" + saltos +
                ", posicion final=" + idx);
    }

    // ==========================
    // INSERCION CUADRATICA
    // ==========================
    static void insertarCuadratico(Entry[] tabla, int key) {
        int h = key % M;
        int i = 0;
        int idx = h;

        while (tabla[idx].estado == Estado.OCCUPIED) {
            i++;

            if (i >= M) {
                System.out.println("Tabla llena, no se pudo insertar " + key);
                return;
            }

            idx = (h + i * i) % M;
        }

        tabla[idx].key = key;
        tabla[idx].estado = Estado.OCCUPIED;

        System.out.println(key + ": hash=" + h +
                ", saltos=" + i +
                ", posicion final=" + idx);
    }

    // ==========================
    // BUSQUEDA (LINEAL)
    // ==========================
    static Integer buscar(Entry[] tabla, int key) {
        int idx = key % M;
        int posInit = idx;

        do {
            if (tabla[idx].estado == Estado.OCCUPIED &&
                tabla[idx].key == key) {
                return idx;
            }

            if (tabla[idx].estado == Estado.EMPTY) {
                return null;
            }

            idx = (idx + 1) % M;
        } while (idx != posInit);

        return null;
    }

    // ==========================
    // ELIMINACION LOGICA
    // ==========================
    static void eliminarLogico(Entry[] tabla, int key) {
        Integer idx = buscar(tabla, key);

        if (idx != null) {
            tabla[idx].estado = Estado.DELETED;
        }
    }

    // ==========================
    // IMPRIMIR TABLA
    // ==========================
    static void imprimir(Entry[] tabla) {
        for (int i = 0; i < M; i++) {
            System.out.println("[" + i + "]: " + tabla[i].estado +
                    (tabla[i].estado == Estado.OCCUPIED
                            ? " (" + tabla[i].key + ")"
                            : ""));
        }
    }
}