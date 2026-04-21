package algo4;
import java.util.*;

public class SubconjuntoRestringido {

    static int[] arr;
    static int n;
    static int objetivo;
    static Set<Integer> obligatorios = new HashSet<>();

    // Precomputa los elementos obligatorios (multiplos de 3)
    static void calcularObligatorios() {
        int suma = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] % 3 == 0) {
                obligatorios.add(i);
                suma += arr[i];
            }
        }
        // Si la suma de obligatorios ya supera el objetivo, no hay solucion
        if (suma > objetivo) {
            System.out.println("false");
            System.exit(0);
        }
        // El objetivo que falta cubrir con los opcionales
        objetivo -= suma;
    }

    // Backtracking: busca si los elementos opcionales suman exactamente 'restante'
    static boolean backtrack(int idx, int restante) {
        if (restante == 0) return true;
        if (idx >= n || restante < 0) return false;

        // Saltar obligatorios (ya estan incluidos)
        if (obligatorios.contains(idx)) {
            return backtrack(idx + 1, restante);
        }

        // Restriccion: si arr[idx] es par y el siguiente tambien es par, no se puede elegir
        boolean puedeElegirse = true;
        if (arr[idx] % 2 == 0 && idx + 1 < n && arr[idx + 1] % 2 == 0) {
            puedeElegirse = false;
        }

        // Incluir arr[idx] (si puede)
        if (puedeElegirse) {
            if (backtrack(idx + 1, restante - arr[idx])) return true;
        }

        // No incluir arr[idx]
        return backtrack(idx + 1, restante);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        objetivo = sc.nextInt();

        calcularObligatorios();
        System.out.println(backtrack(0, objetivo));
    }
}