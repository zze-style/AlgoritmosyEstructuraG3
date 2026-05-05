package ejer4;

public class TestHybrid {
    public static void main(String[] args) throws ExceptionIsEmpty {
        PriorityQueueHybrid<Entry> pqh = new PriorityQueueHybrid<>(3);

        pqh.enqueue(new Entry("A", 5), 2);
        pqh.enqueue(new Entry("B", 1), 2);
        pqh.enqueue(new Entry("C", 3), 1);
        pqh.enqueue(new Entry("D", 3), 2);

        System.out.println("Estado interno:");
        System.out.println(pqh);

        System.out.println("Orden de salida:");
        while (!pqh.isEmpty()) {
            System.out.println("  " + pqh.dequeue());
        }
        // Esperado: B(1), D(3), A(5), C(3)
    }
}
