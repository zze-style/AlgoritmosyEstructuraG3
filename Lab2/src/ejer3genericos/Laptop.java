package ejer3genericos;

public class Laptop implements Cargable {
    private String marca;
    private double consumoVatios;
    private int nivelBateria;

    public Laptop(String marca, double consumoVatios, int nivelBateria) {
        this.marca = marca;
        this.consumoVatios = consumoVatios;
        this.nivelBateria = nivelBateria;
    }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

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
        Laptop that = (Laptop) obj;
        return marca.equals(that.marca);
    }
}