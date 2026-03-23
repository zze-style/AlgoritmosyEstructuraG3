package algoejer2;

public class Main {
	public static void main(String[] args) {
		try {
			
			// Crea el objeto mina y carga los datos desde el archivo especificado.
			MatrizMinera mina = new MatrizMinera("datos.txt");

			int k = 2;         // Tamaño de la subregión a analizar.
			mina.analizarRegion(k);

		} catch (Exception e) {
			
			// Captura errores de lectura o de formato numérico en el archivo.
			System.out.println("Error: " + e.getMessage());
		}
	}
}
