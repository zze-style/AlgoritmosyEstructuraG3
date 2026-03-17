package PyPOOEje1;

public class ContainerRect {
	private Rectangulo[] rectangulos;
	private double[] distancias;
	private double[] areas;
	private int n;
	
	private static int recorrido = 0;
	
	public ContainerRect(int n) {
		this.rectangulos = new Rectangulo[n];
		this.distancias = new double[n];
		this.areas = new double[n];
		this.n = n;
	}
	
	public void addRectangulo(Rectangulo r) {
		if(recorrido < n) {
			rectangulos[recorrido] = r;
			
			double d = Coordenada.distancia(r.getEsquina1(), r.getEsquina2());
			distancias[recorrido] = d;
			
			double base = Math.abs(r.getEsquina2().getX()- r.getEsquina1().getX());
			double altura = Math.abs(r.getEsquina2().getY() - r.getEsquina1().getY());
			
			this.areas[recorrido] = base * altura;
			recorrido++;
		} else {
			System.out.println("Se ha alcanzado la capacidad máxima, ya no se pude guardar");
		}
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-12s %-25s %-12s %-12s\n","Rectangulo", "Coordenadas", "Distancia", "Area"));
		
		for(int i = 0; i<recorrido; i++) {
			String fila = String.format("%-12d %-25s %-12.3f %-12.2f\n", (i + 1), rectangulos[i].toString(), distancias[i], areas[i]);
			sb.append(fila);
		}
		return sb.toString();
	}
	
}
