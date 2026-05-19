package arboles;

public class Main {

	public static void main(String[] args) {
		Arbol<Integer> arbol = new Arbol<>();
		
		arbol.insertar(50);
		arbol.insertar(30);
		arbol.insertar(70);
		arbol.insertar(20);
		arbol.insertar(40);
		arbol.insertar(60);
		arbol.insertar(80);
		arbol.insertar(80);
	
		arbol.inOrden(); 
		arbol.preOrden(); 
		arbol.postOrden(); 
		
		System.out.println("Buscar 40: " + arbol.buscar(40)); 
		System.out.println("Buscar 100: " + arbol.buscar(100)); 
		System.out.println("Buscar 80: " + arbol.buscar(80)); 
		
		System.out.println("Altura: " + arbol.altura());
		System.out.println("Nodos hoja: " + arbol.contarHojas());
		System.out.println("Minimo: " + arbol.minimo());
		System.out.println("Maximo: " + arbol.maximo());
		System.out.println("Es BST valido : " + arbol.isValidBST());
		
		arbol.remove(20); 
		arbol.remove(30);
		arbol.remove(50); 
		
		System.out.println ("Despues de eliminar 20, 30, 50: ");
		arbol.inOrden();
		Arbol<Integer> arbol2 = new Arbol<>();
		int[] vals = {15, 8, 22, 5, 12, 18, 30};
		for (int v : vals) arbol2.insertar(v);
	    arbol2.buscarRango(8, 20);
		arbol2.imprimirDescendente();
		arbol2.areaBST();
		
		System.out.println ("Vacio: " + arbol.isEmpty());
	}
}