package PyPOOEje1;

public class ContainerRect {
	private Rectangulo[] rectangulos;     // Arreglo para almacenar los objetos rectangulo.
	private double[] distancias;          // Arreglo para las distancias euclidianas (esquina1 a esquina2).
	private double[] areas;               // Arreglo para las áreas calculadas.
	private int n;                        // Capacidad máxima definida por el usuario.
	
	private static int recorrido = 0;     // Atributo de clase (static) para contar rectángulos guardados globalmente.
	
	public ContainerRect(int n) {         // Constructor que inicializa los 3 arreglos con el tamaño 'n'.
		this.rectangulos = new Rectangulo[n];
		this.distancias = new double[n];
		this.areas = new double[n];
		this.n = n;
	}
	
	public void addRectangulo(Rectangulo r) {
		if(recorrido < n) {               // Verifica si aún hay espacio en el contenedor.
			rectangulos[recorrido] = r;

		// Calcula la distancia usando el método estático de la clase Coordenada.
			double d = Coordenada.distancia(r.getEsquina1(), r.getEsquina2());
			distancias[recorrido] = d;
			
			double base = Math.abs(r.getEsquina2().getX()- r.getEsquina1().getX());
			double altura = Math.abs(r.getEsquina2().getY() - r.getEsquina1().getY());
			
	    // Calcula el área y la guarda en su arreglo correspondiente (arreglos paralelos).
			this.areas[recorrido] = base * altura;
			recorrido++;                  // Incrementa el contador global para la siguiente inserción.
		} else {
			System.out.println("Se ha alcanzado la capacidad máxima, ya no se pude guardar");
		}
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		// Formatea el encabezado de la tabla con alineación a la izquierda.
		sb.append(String.format("%-12s %-25s %-12s %-12s\n","Rectangulo", "Coordenadas", "Distancia", "Area"));
		
		for(int i = 0; i<recorrido; i++) {
		// Construye cada fila extrayendo los datos de los tres arreglos simultáneamente.
			String fila = String.format("%-12d %-25s %-12.3f %-12.2f\n", (i + 1), rectangulos[i].toString(), distancias[i], areas[i]);
			sb.append(fila);
		}
		return sb.toString();
	}
	
}
