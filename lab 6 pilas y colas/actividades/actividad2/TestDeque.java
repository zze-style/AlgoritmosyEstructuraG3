package actividad2;

import actividad1.ExceptionIsEmpty;

public class TestDeque {
    public static void main(String[] args) throws ExceptionIsEmpty {
        DequeLink<String> deque = new DequeLink<>();

        deque.addLast("B");
        deque.addLast("C");
        deque.addFirst("A");
        System.out.println("Deque inicial: " + deque);
        System.out.println("Primero: " + deque.getFirst());
        System.out.println("Ultimo: " + deque.getLast());

        System.out.println("removeFirst: " + deque.removeFirst());
        System.out.println("Deque: " + deque);

        System.out.println("removeLast: " + deque.removeLast());
        System.out.println("Deque: " + deque);
    }
}