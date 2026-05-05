package ejer3;

public class MultiPriorityQueue<E> {
    private QueueArray<E>[] queues;
    private int levels;

    @SuppressWarnings("unchecked")
    public MultiPriorityQueue(int levels, int capacityPerLevel) {
        this.levels = levels;
        queues = new QueueArray[levels];
        for (int i = 0; i < levels; i++) {
            queues[i] = new QueueArray<>(capacityPerLevel);
        }
    }

    // priority: 0 = menor, levels-1 = mayor
    public void enqueue(E x, int priority) {
        if (priority < 0 || priority >= levels) {
            System.out.println("Prioridad invalida: " + priority);
            return;
        }
        queues[priority].enqueue(x);
    }

    public E dequeue() throws ExceptionIsEmpty {
        // Recorrer desde prioridad mas alta a mas baja
        for (int i = levels - 1; i >= 0; i--) {
            if (!queues[i].isEmpty()) {
                return queues[i].dequeue();
            }
        }
        throw new ExceptionIsEmpty("Todas las colas estan vacias");
    }

    public boolean isEmpty() {
        for (int i = 0; i < levels; i++) {
            if (!queues[i].isEmpty()) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = levels - 1; i >= 0; i--) {
            sb.append("Nivel ").append(i).append(": ")
              .append(queues[i]).append("\n");
        }
        return sb.toString();
    }
}
