package Ejercicio1;

public class TestGen {
	
	static <T extends Comparable<T>> boolean exist(T[] arreglo, T elemento) {
		for(T busqueda:arreglo) {
			if (busqueda != null && elemento.equals(elemento)){
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		
		String[] v = {"Perez", "Sanchez"};
		Integer[] w = {12,34,56 };
		
		System.out.println(exist(v,"Sanchez"));
		System.out.println(exist(w, 34));
		//System.out.println(exist(w,"Salas"));
	}
}
