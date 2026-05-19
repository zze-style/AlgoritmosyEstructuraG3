package actividad1;

public class QueueArray<T> {
    private final Object[] data;
    private int front;
    private int rear;
    private int size;

    public QueueArray(int capacity) {
        data = new Object[capacity];
    }

    public void enqueue(T value) {
        if (size == data.length) {
            throw new IllegalStateException("La cola esta llena.");
        }
        data[rear] = value;
        rear = (rear + 1) % data.length;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola esta vacia.");
        }
        T value = (T) data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        return value;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
