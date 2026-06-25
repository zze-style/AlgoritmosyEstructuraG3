package Hash.ejercicios;

public class Ejercicio2 {
	
    static final int M = 7;

    public static void main(String[] args) {
        int[] valores = {10, 17, 24, 31, 4};

        System.out.println("=== SONDEO LINEAL ===");
        int[] tablaLineal = new int[M];
        for (int i = 0; i < M; i++) tablaLineal[i] = -1;
        for (int v : valores) insertarLineal(tablaLineal, v);
        imprimir(tablaLineal);

        System.out.println("\n=== SONDEO CUADRATICO ===");
        int[] tablaCuadratica = new int[M];
        for (int i = 0; i < M; i++) tablaCuadratica[i] = -1;
        for (int v : valores) insertarCuadratico(tablaCuadratica, v);
        imprimir(tablaCuadratica);
    }

    static void insertarLineal(int[] tabla, int v) {
        int h = v % M;
        int saltos = 0;
        int idx = h;
        while (tabla[idx] != -1) {
            idx = (idx + 1) % M;
            saltos++;
        }
        tabla[idx] = v;
        System.out.println(v + ": hash=" + h + ", saltos=" + saltos + ", posicion final=" + idx);
    }

    static void insertarCuadratico(int[] tabla, int v) {
        int h = v % M;
        int i = 0;
        int idx = h;
        while (tabla[idx] != -1) {
            i++;
            idx = (h + i * i) % M;
        }
        tabla[idx] = v;
        System.out.println(v + ": hash=" + h + ", saltos=" + i + ", posicion final=" + idx);
    }

    static void imprimir(int[] tabla) {
        for (int i = 0; i < M; i++) {
            System.out.println("[" + i + "]: " + (tabla[i] == -1 ? "vacio" : tabla[i]));
        }
    }
} 