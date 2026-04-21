package generic;

public class Golosina {
	private String nombre;
	private double peso;
	public Golosina(String nombre, double peso) {
		this.nombre = nombre;
		this.peso = peso;
	}
		
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Golosina otro = (Golosina) obj;
		return Double.compare(otro.peso, peso) == 0 && nombre.equals(otro.nombre);
	}
	
	public String getNombre(){
		return this.nombre;
	}
	public void setNombre(String nombre){ 
		this.nombre =nombre;
	}
	public double getPeso() {
		return this.peso;
	}
	public void setPeso(double peso){
		this.peso=peso;
	}
	
	@Override
    public String toString() {
        return nombre + " (" + peso + "g)";
    }
}
