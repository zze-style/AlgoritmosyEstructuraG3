package AVLTree;

public class TestAVL {

	public static void main(String[] args) {
		System.out.println("=== Prueba 1: Sin rotacion ===");
		AVLTree<Integer> avl1 = new AVLTree<>();
		avl1.insertar(50);
		avl1.insertar(30);
		avl1.insertar(70);
	    avl1.inOrden(); // 30 50 70 - arbol balanceado
		
		// --- PRUEBA 2: Rotacion Simple Derecha (RSR) caso II ---
	    System.out.println("\n === Prueba 2: RSR (Izquierda-Izquierda) ===");
		AVLTree<Integer> avl2 = new AVLTree<>();
		avl2.insertar(30);
		avl2.insertar(20);
		avl2.insertar(10); // desbalance en 30, RSR -> raiz =20
		avl2.inOrden(); // 10 20 30
		
		// --- PRUEBA 3: Segunda RSR ---
	    System.out.println("\n === Prueba 3: RSR segunda instancia ===");
		AVLTree<Integer> avl3 = new AVLTree<>();
		avl3.insertar(50);
		avl3.insertar(40);
		avl3.insertar(30); // desbalance en 50 , RSR -> raiz =40
		avl3.inOrden(); // 30 40 50
		
		// --- PRUEBA 4: Rotacion Simple Izquierda (RSL) caso DD---
		System.out.println("\n === Prueba 4: RSL (Derecha-Derecha) ===");
		AVLTree<Integer> avl4 = new AVLTree<>();
		avl4.insertar(10);
		avl4.insertar(20);
		avl4.insertar(30); // desbalance en 10 , RSL -> raiz =20
		avl4.inOrden(); // 10 20 30 
		
		// --- PRUEBA 5: Segunda RSL ---
		System.out.println("\n === Prueba 5: RSL segunda instancia ===");
		AVLTree<Integer> avl5 = new AVLTree<>();
		avl5.insertar(10);
		avl5.insertar(20);
		avl5.insertar(30);
		avl5.insertar(40);
		avl5.insertar(50); // desbalance en 30 , RSL -> raiz =40
		avl5.inOrden(); // 10 20 30 40 50
		
		// --- PRUEBA 6: Rotacion Doble Derecha ( RDR ) caso ID ---
		System.out.println("\n === Prueba 6: RDR (Izquierda-Derecha) ===");
		AVLTree<Integer> avl6 = new AVLTree<>();
		avl6.insertar(30);
		avl6.insertar(10);
		avl6.insertar(20); // desbalance en 30 , RDR -> raiz =20
		avl6.inOrden(); // 10 20 30
		
		// --- PRUEBA 7: Segunda RDR ---
		System.out.println("\n === Prueba 7: RDR segunda instancia ===");
		AVLTree<Integer> avl7 = new AVLTree<>();
		avl7.insertar(50);
		avl7.insertar(20);
		avl7.insertar(35); // desbalance en 50 , RDR -> raiz =35
		avl7.inOrden(); // 20 35 50
		
		 // --- PRUEBA 8: Rotacion Doble Izquierda ( RDL ) caso DI ---
		System.out.println("\n === Prueba 8: RDL (Derecha-Izquierda) ===");
	    AVLTree<Integer> avl8 = new AVLTree<>();
	    avl8.insertar(10);
	    avl8.insertar(30);
		avl8.insertar(20); // desbalance en 10 , RDL -> raiz =20
		avl8.inOrden(); // 10 20 30
				
		// --- PRUEBA 9: RDL segunda instancia ---
		System.out.println("\n === Prueba 9: RDL segunda instancia ===");
		AVLTree<Integer> avl9 = new AVLTree<>();
		avl9.insertar(10);
		avl9.insertar(50);
		avl9.insertar(30); // desbalance en 10 , RDL -> raiz =30
		avl9.inOrden(); // 10 30 50
				
		// --- PRUEBA 10: Secuencia con multiples rotaciones ---
		System.out.println("\n === Prueba 10: Secuencia compleja ===");
		AVLTree<Integer> avl10 = new AVLTree<>();
		int[] datos = {40, 20, 60, 10, 30, 50, 70, 5, 15};
		for (int d : datos) avl10.insertar(d);
		avl10.inOrden(); // 5 10 15 20 30 40 50 60 70
	    System.out.println("Altura: " + avl10.altura());
	}
}