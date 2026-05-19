package Ejercicios;

import AVLTree.AVLTree;

public class GestorTicketsAVL {
	private AVLTree<Integer> arbol;
	public GestorTicketsAVL() {
	    arbol = new AVLTree<>();
	}
	
	public void insertarTicket(int id) {
	    arbol.insertar(id);
	    System.out.println("Ticket " + id + " insertado.");
	    System.out.print("InOrden : ");
	    arbol.inOrden();
    }
	
	public void buscarTicket(int id) {
	    boolean encontrado = arbol.buscar(id);
	    System.out.println("Buscar ticket " + id + ": " 
	    + (encontrado ? "ENCONTRADO" : "NO ENCONTRADO"));
	}
	
	public void eliminarTicket(int id) {
	    arbol.remove(id);
	    System.out.println("Ticket " + id + " eliminado.");
	    System.out.print("InOrden: ");
	    arbol.inOrden();
	 }
	
	public static void main(String[] args) {
	    GestorTicketsAVL gestor = new GestorTicketsAVL();
	
	     System.out.println("=== INSERTANDO TICKETS ===");
	     int[] tickets = {30, 10, 20, 40, 50, 25};
	      for (int t : tickets) {
	          gestor.insertarTicket(t);
	      }
	
	     System.out.println("\n === BUSCANDO TICKETS === ");
	     gestor.buscarTicket(20); // Encontrado
	     gestor.buscarTicket(60); // No encontrado
	
	     System.out.println("\n === ELIMINANDO TICKETS === ");
	     gestor.eliminarTicket(10);
	     gestor.eliminarTicket(40);
	     gestor.eliminarTicket(30);
	
	     System.out.println ("\n === ESTADO FINAL === ");
	     System.out.print("InOrden final : ");
	     gestor.arbol.inOrden();
	     System.out.println("Altura final: " + gestor.arbol.altura());
	}
}