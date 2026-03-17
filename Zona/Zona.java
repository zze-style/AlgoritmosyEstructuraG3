package Zona;

class Zona {  // Representa una región minera
    private String mineral;
    private double cantidad;
    private double pureza;

    public Zona(String mineral, double cantidad, double pureza) {
        this.mineral = mineral;
        this.cantidad = cantidad;
        this.pureza = pureza;
    }

    public double calcularValorEconomico() {
        return cantidad * pureza;
    }

    public String obtenerMineral() {
        return mineral;
    }

    public String toString() {
        return "[" + mineral + ", cantidad: " + cantidad + ", pureza: " + pureza + "]";
    }
}