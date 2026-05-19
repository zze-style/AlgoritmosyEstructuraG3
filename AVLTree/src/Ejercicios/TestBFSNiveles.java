package Ejercicios;

import AVLTree.AVLTree;

public class TestBFSNiveles {

	public static void main(String[] args) {
		AVLTree<Integer> avl = new AVLTree<>();
	    int[] datos = {50, 30, 70, 20, 40, 60, 80, 10, 25, 65};
		for (int d : datos) avl.insertar(d);
		
		avl.bfsPorNiveles();
		// Nivel 0: 50
		// Nivel 1: 30 70
		// Nivel 2: 20 40 60 80
		// Nivel 3: 10 25 65
	}
}  