package Ejercicios;

import AVLTree.AVLTree;
import AVLTree.Arbol;

public class ComparacionBSTvsAVL {
	public static void main(String[] args) {
		// === CASO 1: Insercion en orden ascendente ( degenerante para BST ) ===
		System.out.println("=== CASO 1: Orden ascendente ===");
		Arbol<Integer> bst1 = new Arbol<>();
		
		AVLTree<Integer> avl1 = new AVLTree<>();
		
	    int[] datos1 = {10, 20, 30, 40, 50, 60, 70};
		for (int d : datos1) {
		    bst1.insertar(d);
		    avl1.insertar(d);
		}
		
		System.out.println("BST InOrden: " + bst1.toString());
		System.out.println("BST Altura: " + bst1.altura()); // O(n) = 6
		
		System.out.println("AVL InOrden: " + avl1.toString());
		System.out.println("AVL Altura: " + avl1.altura()); // O(log n) = 2
		
		// === CASO 2: Insercion en orden descendente ===
		System.out.println("\n === CASO 2: Orden descendente ===");
		Arbol<Integer> bst2 = new Arbol<>();
		AVLTree<Integer> avl2 = new AVLTree<>();
		
		int[] datos2 = {70, 60, 50, 40, 30, 20, 10};
		for (int d : datos2) {
		    bst2.insertar(d);
		    avl2.insertar(d);
		}
		
		System.out.println("BST Altura: " + bst2.altura()); // O(n) = 6
		System.out.println("AVL Altura: " + avl2.altura()); // O(log n) = 2
		
		// === BUSQUEDA comparativa ===
	    System.out.println("\n === Busqueda del valor 30 === ");
		System.out.println("BST buscar(30): " + bst1.buscar(30));
		System.out.println("AVL buscar(30): " + avl1.buscar(30));
	}
}