package algo4;

public class ModaFuerzaBruta {

    public static int moda(int[] arr) {
        int n = arr.length;
        int moda = arr[0];
        int maxFrecuencia = 0;

        for (int i = 0; i < n; i++) {
            int contador = 0;

            // Contar cuántas veces aparece arr[i]
            for (int j = 0; j < n; j++) {
                if (arr[i] == arr[j]) {
                    contador++;
                }
            }

            // Actualizar la moda
            if (contador > maxFrecuencia) {
                maxFrecuencia = contador;
                moda = arr[i];
            }
        }

        return moda;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 2, 4, 2, 5};

        int resultado = moda(arr);

        System.out.println("La moda es: " + resultado);
    }
}