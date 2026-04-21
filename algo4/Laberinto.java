package algo4;

public class Laberinto {

    static int[][] laberinto;
    static int[][] solucion;
    static int filas, columnas;
    static int[] dFila = {-1, 1, 0, 0}; // arriba, abajo
    static int[] dCol  = {0, 0, -1, 1}; // izquierda, derecha

    static boolean esValido(int f, int c) {
        return f >= 0 && f < filas && c >= 0 && c < columnas
               && laberinto[f][c] == 0 && solucion[f][c] == 0;
    }

    static boolean resolver(int f, int c) {
        // Caso base: llegamos al destino
        if (f == filas - 1 && c == columnas - 1) {
            solucion[f][c] = 1;
            return true;
        }
        if (!esValido(f, c)) return false;

        solucion[f][c] = 1; // Marcar la celda como parte del camino

        // Explorar las 4 direcciones
        for (int d = 0; d < 4; d++) {
            if (resolver(f + dFila[d], c + dCol[d])) return true;
        }

        solucion[f][c] = 0; // Backtracking: desmarcar
        return false;
    }

    static void imprimirSolucion() {
        for (int[] fila : solucion) {
            for (int v : fila) System.out.print(v + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Ejemplo 1
        laberinto = new int[][]{{0,0,1},{1,0,1},{1,0,0}};
        filas = 3; columnas = 3;
        solucion = new int[filas][columnas];
        System.out.println("Ejemplo 1: " + resolver(0, 0));
        if (resolver(0, 0)) imprimirSolucion();

        // Ejemplo 2
        laberinto = new int[][]{{0,1},{1,0}};
        filas = 2; columnas = 2;
        solucion = new int[filas][columnas];
        System.out.println("Ejemplo 2: " + resolver(0, 0));
    }
}