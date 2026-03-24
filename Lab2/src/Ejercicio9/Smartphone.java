package Ejercicio9;

public class Smartphone implements Cargable {
    private String modelo;
    private double consumoVatios;
    private int nivelBateria;

    public Smartphone(String modelo, double consumoVatios, int nivelBateria) {
        this.modelo = modelo;
        this.consumoVatios = consumoVatios;
        this.nivelBateria = nivelBateria;
    }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    @Override
    public double getConsumoVatios() { return consumoVatios; }
    public void setConsumoVatios(double consumoVatios) { this.consumoVatios = consumoVatios; }

    @Override
    public int getNivelBateria() { return nivelBateria; }

    @Override
    public void cargar(int cantidad) {
        nivelBateria += cantidad;
        if (nivelBateria > 100) nivelBateria = 100;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Smartphone that = (Smartphone) obj;
        return modelo.equals(that.modelo);
    }
}