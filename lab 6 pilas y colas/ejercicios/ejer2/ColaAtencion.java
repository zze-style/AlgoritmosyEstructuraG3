package ejer2;

public class ColaAtencion {
    public static void main(String[] args) throws ExceptionIsEmpty {
        QueueArray<Integer> cola = new QueueArray<>(5);

        // Encolar clientes 101 a 105
        cola.enqueue(101);
        cola.enqueue(102);
        cola.enqueue(103);
        cola.enqueue(104);
        cola.enqueue(105);

        // Intentar encolar cliente 106 (cola llena)
        cola.enqueue(106); // imprime "Cola llena"

        // Desencolar 2 clientes
        System.out.println("Atendiendo cliente: " + cola.dequeue()); // 101
        System.out.println("Atendiendo cliente: " + cola.dequeue()); // 102

        // Mostrar cliente al frente
        System.out.println("Cliente en frente: " + cola.front());    // 103

        // Encolar 2 clientes mas (verifica comportamiento circular)
        cola.enqueue(106);
        cola.enqueue(107);

        // Desencolar todos
        while (!cola.isEmpty()) {
            System.out.println("Atendiendo cliente: " + cola.dequeue());
        }

        // Intentar desencolar con cola vacia
        try {
            cola.dequeue();
        } catch (ExceptionIsEmpty e) {
            System.out.println("Cola vacia");
        }
    }
}
