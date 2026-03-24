package ejer3genericos;

public class Main {
    public static void main(String[] args) {
        PowerStation<Cargable> estacion = new PowerStation<>();

        Smartphone s1 = new Smartphone("Samsung S21", 15, 40);
        Smartphone s2 = new Smartphone("iPhone 13", 20, 30);
        Laptop l1 = new Laptop("Dell", 65, 50);

        estacion.conectar(s1);
        estacion.conectar(s2);
        estacion.conectar(l1);

        System.out.println("Consumo total: " + estacion.calcularConsumoTotal() + " W");

        estacion.cargarTodos(20);

        System.out.println("Posicion iPhone: " + estacion.buscarDispositivo(s2));

        estacion.mostrarReporte();
    }
}