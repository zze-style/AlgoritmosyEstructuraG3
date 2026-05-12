package Ejer4;

public class Main {

	public static void main(String[] args) {
		Arbol<Integer> bst = new Arbol<>();

        bst.insertar(10);
        bst.insertar(5);
        bst.insertar(15);

        System.out.println("¿Es BST valido?");
        System.out.println(bst.isValidBST());

        System.out.println("\nArbol parenthesized:");
        bst.parenthesize();
	}
}