package Ejercicio1;

import Ejercicio3.*;
import generic.*;

public class TestGen {
	
	static <T extends Comparable<T>> boolean exist(T[] arreglo, T elemento) {
		for(T busqueda:arreglo) {
			if (busqueda != null && busqueda.equals(elemento)){
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
		
		Cajoneria<Golosina> miCajoneria = new Cajoneria<>(6);

        // golosinas agregadas a la caja
        miCajoneria.add(new Caja<>("Rojo"));
        miCajoneria.add(new Caja<>("Azul"));
        miCajoneria.add(new Caja<>("Verde"));
        miCajoneria.add(new Caja<>("Amarillo"));
        miCajoneria.add(new Caja<>("Blanco"));

        // llenado de cajas
        miCajoneria.iterator().next().setContenido(new Golosina("Chocolate", 50.5));
        
        
        Caja<Golosina> c2 = new Caja<>("Azul");
        c2.setContenido(new Golosina("Caramelo", 5.0));
        miCajoneria.add(c2); 

        //  existencia por nombre y peso
        System.out.println("GOLOSINA REPORTE");
        System.out.println(miCajoneria.toString());

        Golosina busqueda = new Golosina("Chocolate", 50.5);
        System.out.println("Buscando Chocolate de 50.5g...");
        System.out.println(miCajoneria.search(busqueda));

        //eliminar
        System.out.println("PRUEBA DE METODO DELETE");
        Golosina borrada = miCajoneria.delete(new Golosina("Caramelo", 5.0));
        
        if (borrada != null) {
            System.out.println("Se eliminó con éxito: " + borrada);
        } else {
            System.out.println("No se pudo eliminar, la golosina no existe.");
        }

        System.out.println("REPORTE FINAL");
        System.out.println(miCajoneria.toString());
        
        Cajoneria<Chocolatina> misChocolates = new Cajoneria<>(5);
        
        System.out.println("-----------------------------------------------------");
        //EJERCICIO 6 IMPLEMENTACIÓN DE CONTADOR
        Chocolatina ch1 = new Chocolatina("Sublime");
        Chocolatina ch2 = new Chocolatina("Sublime");

        misChocolates.add(new Caja<>("Azul"));
        misChocolates.add(new Caja<>("Rojo"));

        // llenado de cajas
        misChocolates.getLista().get(0).setContenido(ch1);
        misChocolates.getLista().get(1).setContenido(ch2);

        System.out.println("Cantidad de Sublimes: " + misChocolates.contar(new Chocolatina("Sublime"))); 
        
        misChocolates.delete(new Chocolatina("Sublime")); 
        System.out.println("Luego de eliminar uno, quedan: " + misChocolates.contar(new Chocolatina("Sublime"))); 
        
        System.out.println("------------------------------------------------------");
        //PRUEBA CON CHOCOLATINA, EJERCICIO 7
        
        Chocolatina c1 = new Chocolatina("Sublime");
        Chocolatina c2_cho = new Chocolatina("Sublime");
        Chocolatina c3 = new Chocolatina("Snickers");

        Caja<Chocolatina> caja1 = new Caja<>("Azul");
        caja1.setContenido(c1);

        Caja<Chocolatina> caja2 = new Caja<>("Rojo");
        caja2.setContenido(c2_cho);

        Caja<Chocolatina> caja3 = new Caja<>("Amarillo");
        caja3.setContenido(c3);

        misChocolates.add(caja1);
        misChocolates.add(caja2);
        misChocolates.add(caja3);

	    System.out.println("REPORTE DE CHOCOLATINAS");
	    System.out.println(misChocolates.toString()); 
	
	    int conteo = misChocolates.contar(new Chocolatina("Sublime")); 
	    System.out.println("Ocurrencias de Sublime: " + conteo);
	
	    System.out.println("Resultado búsqueda: " + misChocolates.search(new Chocolatina("Snickers")));
	
	    Chocolatina eliminada = misChocolates.delete(new Chocolatina("Sublime"));
	    System.out.println("Se eliminó: " + eliminada);
	    System.out.println("Nuevo conteo de Sublime: " + misChocolates.contar(new Chocolatina("Sublime")));
	}
}