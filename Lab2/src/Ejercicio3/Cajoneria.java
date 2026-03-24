package Ejercicio3;
import java.util.ArrayList;
import java.util.Iterator;

public class Cajoneria <T> implements Iterable<Caja<T>>{
	private ArrayList<Caja<T>> lista = new ArrayList<>();
	private int tope;
	
	public Cajoneria(int tope) {
		this.tope = tope;
	}
	
	public void add(Caja<T> objeto) {
		if (lista.size() < tope) {
			lista.add(objeto);
		} else {
			throw new RuntimeException("Cajonería llena");
		}
	}
	
	public String search(T elemento) {
		for(int i = 0; i < lista.size(); i++) {
			Caja <T> caja = lista.get(i);
			if (caja.getContenido() != null && caja.getContenido().equals(elemento)) {
				return "Elemento encontrado en la posición: " + (i + 1) + ", Color de caja: "+ caja.getColor();
			}
		}
		return "No se encontró el elemento";
	}
	
	public T delete(T elemento) {
		for (Caja<T> caja: lista) {
			if (caja.getContenido() != null && caja.getContenido().equals(elemento)) {
				T objetoEliminado = caja.getContenido();
				caja.setContenido(null);
				return objetoEliminado;
			}
		}
		return null;
	}
	
	public int contar(T elemento) {
		int contador = 0;
		for (Caja<T> caja : lista) {
			if (caja.getContenido() != null && caja.getContenido().equals(elemento)) {
				contador++;
			}
		}
		return contador;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-10s %-15s %-20s\n", "Posición", "Color Caja", "Objeto"));
		
		for (int i = 0; i < lista.size(); i++) {
			Caja<T> caja = lista.get(i);
			String datosObjeto = (caja.getContenido() != null) ? caja.getContenido().toString() : "Vacío";
			
			sb.append(String.format("%-10d %-15s %-20s\n", (i + 1), caja.getColor(), datosObjeto));
		}
		return sb.toString();
	}
	
	@Override
	public Iterator<Caja<T>> iterator(){
		return lista.iterator();
	}
	
	public ArrayList<Caja<T>> getLista() {
	    return this.lista;
	
	}
}
