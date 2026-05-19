package Ejercicios;

import AVLTree.AVLTree;

public class TestInsercionEliminacion {

	public static void main(String[] args) {
		AVLTree <Integer> avl = new AVLTree<>();
		// Inserciones que provocan distintos tipos de desbalance
		System.out.println("=== FASE DE INSERCION === ");
		int[] inserciones = {50, 30, 70, 20, 40, 10, 25, 60, 80,};
		
		for (int v : inserciones) {
		    avl.insertar(v);
		    System.out.print ("Insertar " + v + " -> InOrden: ");
		    avl.inOrden();
		}
		System.out.println ("Altura tras inserciones: " + avl.altura());
		
		// Eliminaciones que pueden provocar rotaciones
		System.out.println("\n === FASE DE ELIMINACION === ");
		int[] eliminaciones = {10, 80, 50};
		for (int v : eliminaciones) {
		    avl.remove(v);
		    System.out.print("Eliminar " + v + " -> InOrden: ");
		    avl.inOrden();
		    System.out.println("Altura: " + avl.altura());
		}
		
		System.out.println ("\nArbol final InOrden: ");
		avl.inOrden();
	}
}