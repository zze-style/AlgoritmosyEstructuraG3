package btree;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA DE ÁRBOL B (Orden 4) ===\n");

        // Creamos un Árbol B de orden 4 (Máximo 3 claves por nodo)
        BTree<Integer> arbol = new BTree<>(4);

        System.out.println("¿El árbol está vacío?: " + arbol.isEmpty());
        System.out.println("----------------------------------------");

        // Valores a insertar en un orden que forzará divisiones
        int[] valores = {10, 20, 5, 15, 30, 25, 4, 3, 2, 1};

        System.out.println("Insertando valores de forma secuencial...");
        for (int valor : valores) {
            System.out.println("> Insertando: " + valor);
            arbol.insert(valor);
        }

        System.out.println("\n----------------------------------------");
        System.out.println("Estructura actual del Árbol B (Recorrido BFS/Nivel por Nivel):");
        System.out.println("----------------------------------------");
        System.out.println(arbol.toString());

        System.out.println("----------------------------------------");
        System.out.println("Intentando insertar un duplicado (15):");
        arbol.insert(15); // Debería disparar el mensaje de "Item duplicado"

        System.out.println("\n¿El árbol está vacío?: " + arbol.isEmpty());
    }
}
