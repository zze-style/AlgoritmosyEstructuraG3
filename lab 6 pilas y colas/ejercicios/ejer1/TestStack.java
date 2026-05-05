package ejer1;

public class TestStack {
    public static void main(String[] args) throws ExceptionIsEmpty {
        StackLink<Integer> pila = new StackLink<>();

        pila.push(10);
        pila.push(20);
        pila.push(30);

        System.out.println("Pila inicial: " + pila);
        System.out.println("Tope: " + pila.top());
        System.out.println("Pop: " + pila.pop());
        System.out.println("Pila tras pop: " + pila);
        System.out.println("Pop: " + pila.pop());
        System.out.println("Pila tras pop: " + pila);
    }
}

