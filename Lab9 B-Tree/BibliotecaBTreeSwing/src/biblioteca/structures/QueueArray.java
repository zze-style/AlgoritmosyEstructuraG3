package biblioteca.structures;

import java.util.ArrayList;
import java.util.List;

public class QueueArray<T> {
    private final Object[] data;
    private int front;
    private int rear;
    private int size;

    public QueueArray(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser positiva.");
        }
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

    @SuppressWarnings("unchecked")
    public List<T> toList() {
        List<T> values = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            values.add((T) data[(front + i) % data.length]);
        }
        return values;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}