package algo4;

public class ViajeRio {

    static final int INF = Integer.MAX_VALUE / 2;

    static int[][] calcularCostosMinimos(int[][] T, int n) {
        int[][] C = new int[n][n];

        // Caso base: misma posicion, costo cero
        for (int i = 0; i < n; i++) C[i][i] = 0;

        // Llenar la tabla de mayor a menor (bottom-up)
        // Longitud del tramo: de 1 a n-1
        for (int len = 1; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                int j = i + len;
                C[i][j] = T[i][j]; // Ir directamente
                // Probar escalas intermedias k entre i+1 y j
                for (int k = i + 1; k < j; k++) {
                    if (T[i][k] != INF && C[k][j] != INF) {
                        C[i][j] = Math.min(C[i][j], T[i][k] + C[k][j]);
                    }
                }
            }
        }
        return C;
    }

    public static void main(String[] args) {
        // Ejemplo: 4 embarcaderos
        // T[i][j] = costo directo de i a j (triangular superior)
        int[][] T = {
            {0,  2,  5,  9},
            {INF, 0,  3,  6},
            {INF, INF, 0,  2},
            {INF, INF, INF, 0}
        };

        int n = 4;
        int[][] C = calcularCostosMinimos(T, n);

        System.out.println("Matriz de costos minimos:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j >= i) System.out.printf("%4d", C[i][j]);
                else        System.out.print("   -");
            }
            System.out.println();
        }
    }
}