package algoejer2;
import java.io.*;
import java.util.*;

public class MatrizMinera {
	private Zona[][] matriz;
	private int filas;
	private int columnas;

	public MatrizMinera(String archivo) throws IOException {
		cargarArchivo(archivo);
	}
	
 // Lee el archivo .txt y llena la matriz de objetos Zona.
	private void cargarArchivo(String archivo) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivo));
		String[] dimensiones = br.readLine().split(" ");   // Lee dimensiones en la primera línea.
		filas = Integer.parseInt(dimensiones[0]);
		columnas = Integer.parseInt(dimensiones[1]);

		matriz = new Zona[filas][columnas];

		int i = 0, j = 0;
		String linea;

		while ((linea = br.readLine()) != null) {
			String[] datos = linea.split(" ");
			String mineral = datos[0];
			double cantidad = Double.parseDouble(datos[1]);
			double pureza = Double.parseDouble(datos[2]);

			matriz[i][j] = new Zona(mineral, cantidad, pureza);

			j++;
			if (j == columnas) {                         // Control manual del salto de fila en la matriz.
				j = 0;
				i++;
			}
		}

		br.close();
	}
	
 // Algoritmo de ventana deslizante para encontrar la subregión k x k más valiosa.
	public void analizarRegion(int k) {
		double maxValor = Double.MIN_VALUE;
		int mejorFila = 0;
		int mejorCol = 0;
		
 // Ciclos externos: Recorren los posibles puntos de inicio (esquina superior izquierda).
		for (int i = 0; i <= filas - k; i++) {
			for (int j = 0; j <= columnas - k; j++) {

				double suma = 0;
				
 // Ciclos internos: Suman el valor económico dentro del área k x k.
				for (int x = i; x < i + k; x++) {
					for (int y = j; y < j + k; y++) {
						suma += matriz[x][y].getValorEconomico();
					}
				}

 // Si la suma actual supera el récord, guardamos la posición y el valor.
				if (suma > maxValor) {
					maxValor = suma;
					mejorFila = i;
					mejorCol = j;
				}
			}
		}

		mostrarResultado(mejorFila, mejorCol, k, maxValor);
	} 
	
 // Procesa la región ganadora para mostrar detalles y el mineral predominante.
	private void mostrarResultado(int fila, int col, int k, double valor) {
		System.out.println("Valor máximo: " + valor);
		System.out.println("Posición inicial: (" + fila + ", " + col + ")");

 // Mapa para contabilizar la frecuencia de cada mineral en la subregión.		
		Map<String, Integer> contadorMinerales = new HashMap<>();

		System.out.println("\nZonas de la región:");

		for (int i = fila; i < fila + k; i++) {
			for (int j = col; j < col + k; j++) {
				Zona z = matriz[i][j];
				System.out.println(z);

 // Suma 1 al contador del mineral actual.				
				contadorMinerales.put(
						z.getMineral(),
						contadorMinerales.getOrDefault(z.getMineral(), 0) + 1
						);
			}
		}
 // Identifica el mineral con el conteo más alto en el Map.
		// Mineral predominante
		String predominante = "";
		int max = 0;

		for (String m : contadorMinerales.keySet()) {
			if (contadorMinerales.get(m) > max) {
				max = contadorMinerales.get(m);
				predominante = m;
			}
		}

		System.out.println("\nMineral predominante: " + predominante);
	}
}
