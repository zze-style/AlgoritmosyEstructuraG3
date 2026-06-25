package Hash.ejercicios;

public class Ejercicio1 {
	
    public static void main(String[] args) {
        int M = 11;
        int[] tabla = new int[M];
        for (int i = 0; i < M; i++) tabla[i] = -1; // posicion vacia

        int[] valores = {3, 14, 25, 36, 47, 58};

        for (int v : valores) {
            int idx = v % M;
            System.out.println("h(" + v + ") = " + v + " % " + M + " = " + idx);
            if (tabla[idx] == -1) {
                tabla[idx] = v;
            } else {
                System.out.println("  -> COLISION: la posicion " + idx
                        + " ya contiene " + tabla[idx] + ". Se sobrescribe (sin tratamiento).");
                tabla[idx] = v;
            }
        }

        System.out.println("\nEstado final de la tabla:");
        for (int i = 0; i < M; i++) {
            System.out.println("[" + i + "]: " + (tabla[i] == -1 ? "vacio" : tabla[i]));
        }
    }
}  