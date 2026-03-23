package algoejer2;

public class Zona {
	private String mineral;
	private double cantidad;
	private double pureza;

	public Zona(String mineral, double cantidad, double pureza) {
		this.mineral = mineral;
		this.cantidad = cantidad;
		this.pureza = pureza;
	}

	                             // Regla de negocio: Calcula el valor multiplicando cantidad por pureza.
	public double getValorEconomico() {
		return cantidad * pureza;
	}

	public String getMineral() {
		return mineral;
	}

	@Override
	public String toString() {   // Formato visual para mostrar la información de la zona.
		return mineral + " | Cant: " + cantidad + " | Pureza: " + pureza;
	}
}
