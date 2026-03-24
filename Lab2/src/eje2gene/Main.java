package eje2gene;

public class Main {
	/**
     * Método genérico para mostrar arreglos
     */
    public static <T> void mostrarArreglo(T[] arreglo) {
        for (T elemento : arreglo) {
            System.out.print(elemento + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {

        // ===============================
        // CASO 1: String
        // ===============================
        System.out.println("=== Arreglo de String ===");
        String[] letras = {"A", "B", "C", "D"};

        mostrarArreglo(letras);
        IntercambioGenerico.intercambiar(letras, 1, 3);
        mostrarArreglo(letras);
        IntercambioGenerico.intercambiar(letras, 0, 2);
        mostrarArreglo(letras);


        // ===============================
        // CASO 2: Integer
        // ===============================
        System.out.println("\n=== Arreglo de Integer ===");
        Integer[] numeros = {10, 20, 30, 40};

        mostrarArreglo(numeros);
        IntercambioGenerico.intercambiar(numeros, 0, 2);
        mostrarArreglo(numeros);


        // ===============================
        // CASO 3: Objetos personalizados
        // ===============================
        System.out.println("\n=== Arreglo de Personas ===");
        Persona[] personas = {
            new Persona("Juan", 25),
            new Persona("Ana", 22),
            new Persona("Luis", 30)
        };

        mostrarArreglo(personas);
        IntercambioGenerico.intercambiar(personas, 0, 2);
        mostrarArreglo(personas);
    }    
}