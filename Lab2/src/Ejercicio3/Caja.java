package Ejercicio3;

public class Caja <T>{
	private String color;
	private T contenido;

	public Caja(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}
	
	public T getContenido() {
		return contenido;
	}
	
	public void setContenido(T contenido) {
		this.contenido = contenido;
	}
}
