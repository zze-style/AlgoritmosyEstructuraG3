package actividad3;

import actividad1.ExceptionIsEmpty;

public class Test {
	public static void main(String[] args) throws ExceptionIsEmpty {
		
		PriorityQueue<String, Integer> pq = new PriorityQueueLinkSort<>();

        pq.enqueue("A", 2);
        pq.enqueue("B", 5);
        pq.enqueue("C", 1);
        pq.enqueue("D", 4);

        System.out.println(pq);

        System.out.println("Front: " + pq.front());
        System.out.println("Back: " + pq.back());

        System.out.println("Dequeue: " + pq.dequeue());
        System.out.println(pq);
    }
}