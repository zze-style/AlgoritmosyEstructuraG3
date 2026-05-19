package Ejercicios;

import AVLTree.AVLTree;

public class TestEliminacionAVL {

	public static void main(String[] args) throws Exception {
		AVLTree <Integer> avl = new AVLTree<>();
		int[] datos = {40, 20, 60, 10, 30, 50, 70, 26};
		for (int d : datos) avl.insertar(d);
		
	    System.out.println ("Arbol inicial:");
		
		avl.inOrden();
		System.out.println("Altura: " + avl.altura());
		
		// Eliminacion de nodo hoja
		System.out.println("\nEliminar 10 (nodo hoja - Caso 1):");
		avl.remove(10);
		avl.inOrden();
		
		// Eliminacion de nodo con un hijo
		System.out.println("\nEliminar 20 (nodo con un hijo - Caso 2):");
		avl.remove(20);
		avl.inOrden();
		
		 // Eliminacion de nodo con dos hijos
		System.out.println("\nEliminar 40 (nodo con dos hijos -Caso 3):");
		avl.remove(40);
		avl.inOrden();
		
		System.out.println("\nAltura final: " + avl.altura());
	}
} 