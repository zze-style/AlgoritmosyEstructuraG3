package Ejercicios;

import AVLTree.AVLTree;

public class TestBFS {

	public static void main(String[] args) {
		AVLTree<Integer> avl1 = new AVLTree<>();
		int[] datos1 = {50, 30, 70, 20, 40, 60, 80, 10, 25, 65};
		for (int d : datos1) avl1.insertar(d);
		
		System.out.println("Arbol 1:");
		avl1.inOrden();
		System.out.println("Altura: " + avl1.altura());
		avl1.bfsRecursivo(); // Esperado : 50 30 70 20 40 60 80 10 25 65
		
		 AVLTree<Integer> avl2 = new AVLTree<>();
		 int[] datos2 = {40, 20, 60, 10, 30, 50, 70};
		 for (int d : datos2) avl2.insertar(d);
		
		 System.out.println("\nArbol 2:");
		 avl2.inOrden();
		 avl2.bfsRecursivo(); // Esperado : 40 20 60 10 30 50 70
	}
} 