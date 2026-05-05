package ejer3;

public class TestMultiPriorityQueue {
    public static void main(String[] args) throws ExceptionIsEmpty {
        MultiPriorityQueue<String> mpq = new MultiPriorityQueue<>(3, 10);

        mpq.enqueue("A", 0);  // prioridad baja
        mpq.enqueue("B", 2);  // prioridad alta
        mpq.enqueue("C", 1);  // prioridad media
        mpq.enqueue("D", 2);  // prioridad alta

        System.out.println("Estado inicial:");
        System.out.println(mpq);

        System.out.println("Orden de salida:");
        while (!mpq.isEmpty()) {
            System.out.println("  " + mpq.dequeue());
        }
        // Esperado: B, D, C, A
    }
}