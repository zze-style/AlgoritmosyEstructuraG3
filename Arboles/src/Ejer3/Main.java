package sameArea;

public class Main {
	
	public static boolean sameArea(Arbol<?> a, Arbol<?> b) {
        return a.areaBST() == b.areaBST();
    }

    public static void main(String[] args) {

        Arbol<Integer> a1 = new Arbol<>();
        Arbol<Integer> a2 = new Arbol<>();

        a1.insertar(10);
        a1.insertar(5);
        a1.insertar(15);

        a2.insertar(20);
        a2.insertar(8);
        a2.insertar(30);

        System.out.println(sameArea(a1, a2));
    }
}