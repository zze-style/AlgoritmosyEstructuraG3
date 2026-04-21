package algo4;
public class ModaDivideConquista {

    // Función principal
    public static int moda(int[] arr, int inicio, int fin) {

        // Caso base: un solo elemento
        if (inicio == fin) {
            return arr[inicio];
        }

        int medio = (inicio + fin) / 2;

        // Dividir
        int modaIzquierda = moda(arr, inicio, medio);
        int modaDerecha = moda(arr, medio + 1, fin);

        // Si ambas son iguales
        if (modaIzquierda == modaDerecha) {
            return modaIzquierda;
        }

        // Contar ocurrencias en todo el rango
        int conteoIzq = contar(arr, inicio, fin, modaIzquierda);
        int conteoDer = contar(arr, inicio, fin, modaDerecha);

        // Elegir la más frecuente
        if (conteoIzq > conteoDer) {
            return modaIzquierda;
        } else {
            return modaDerecha;
        }
    }

    // Método de conteo (fuerza bruta)
    public static int contar(int[] arr, int inicio, int fin, int valor) {
        int contador = 0;

        for (int i = inicio; i <= fin; i++) {
            if (arr[i] == valor) {
                contador++;
            }
        }

        return contador;
    }

    // Método main
    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 2, 2, 4, 2, 5};

        int resultado = moda(arr, 0, arr.length - 1);

        System.out.println("La moda es: " + resultado);
    }
}