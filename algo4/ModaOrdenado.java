package algo4;

import java.util.Arrays;

public class ModaOrdenado {

    public static int moda(int[] arr) {
        int n = arr.length;

        // Ordenar el arreglo
        Arrays.sort(arr);

        int moda = arr[0];
        int maxFrecuencia = 1;

        int actual = arr[0];
        int contador = 1;

        for (int i = 1; i < n; i++) {
            if (arr[i] == actual) {
                contador++;
            } else {
                // Cambia de número
                if (contador > maxFrecuencia) {
                    maxFrecuencia = contador;
                    moda = actual;
                }
                actual = arr[i];
                contador = 1;
            }
        }

        // Verificar el último grupo
        if (contador > maxFrecuencia) {
            moda = actual;
        }

        return moda;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 2, 4, 2, 5};

        int resultado = moda(arr);

        System.out.println("La moda es: " + resultado);
    }
}
