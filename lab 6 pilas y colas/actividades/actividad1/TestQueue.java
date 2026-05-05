package actividad1;

public class TestQueue {
	public static void main(String[] args) throws ExceptionIsEmpty {
		
		 Queue<Integer> q1 = new QueueArray<>(5);
	        q1.enqueue(10);
	        q1.enqueue(20);
	        q1.enqueue(30);

	        System.out.println(q1);
	        System.out.println("Front: " + q1.front());
	        System.out.println("Dequeue: " + q1.dequeue());
	        System.out.println(q1);

	        Queue<String> q2 = new QueueArray<>(5);
	        q2.enqueue("Hola");
	        q2.enqueue("Mundo");

	        System.out.println(q2);
	}
} 