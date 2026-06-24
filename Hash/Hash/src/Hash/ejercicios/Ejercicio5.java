package Hash.ejercicios;

public class Ejercicio5 {

    static int M = 7;
    static int[] tabla = new int[M];
    static boolean[] ocupado = new boolean[M];
    static int n = 0;

    public static void main(String[] args) {
        int[] valores = {2, 9, 16, 23, 4, 11};

        for (int v : valores) {
            insertarLineal(v);
            double alpha = (double) n / M;
            System.out.printf("Insertado %d -> factor de carga = %d/%d = %.3f%n", v, n, M, alpha);
            if (alpha > 0.75) {
                System.out.println("  -> alpha supera 0.75: se realiza REHASHING a tamano 17");
                rehash(17);
            }
        }

        System.out.println("\n=== Tabla final ===");
        imprimir();
    }

    static void insertarLineal(int v) {
        int idx = v % M, posInit = idx;
        while (ocupado[idx]) {
            idx = (idx + 1) % M;
            if (idx == posInit) { System.out.println("Tabla llena"); return; }
        }
        tabla[idx] = v;
        ocupado[idx] = true;
        n++;
    }

    static void rehash(int nuevoTamano) {
        int[] viejaTabla = tabla;
        boolean[] viejoOcupado = ocupado;
        int viejoM = M;

        M = nuevoTamano;
        tabla = new int[M];
        ocupado = new boolean[M];
        n = 0;

        for (int i = 0; i < viejoM; i++) {
            if (viejoOcupado[i]) {
                insertarLineal(viejaTabla[i]);
            }
        }
    }

    static void imprimir() {
        for (int i = 0; i < M; i++) {
            System.out.println("[" + i + "]: " + (ocupado[i] ? tabla[i] : "vacio"));
        }
    }
} 