package Hash;

public class TestHash {
	
	public static void main(String[] args) {

		Register[] datos = {
		new Register(27, "Juan"),
		new Register(18, "Maria"),
		new Register(29, "Carlos"),
		new Register(28, "Ana"),
		new Register(39, "Luis"),
		new Register(13, "Pedro"),
		new Register(16, "Sofia")
		};

		// ==========================
		// PRUEBA SONDEO LINEAL
		// ==========================
		System.out.println("===== HASH CERRADO - SONDEO LINEAL =====");
		HashC hashLineal = new HashC(11);

		for (Register r : datos) {
		hashLineal.insertLineal(r);
		}

		System.out.println("\nTabla final (Lineal):");
		hashLineal.printTable();

		System.out.println("\nBusqueda:");
		Register encontrado1 = hashLineal.search(29);
		System.out.println("Buscar clave 29 -> " +
		(encontrado1 != null ? encontrado1 : "No encontrado"));

		System.out.println("\nEliminar clave 29");
		hashLineal.delete(29);

		System.out.println("\nTabla despues de eliminar:");
		hashLineal.printTable();


		// ==========================
		// PRUEBA SONDEO CUADRATICO
		// ==========================
		System.out.println("\n\n===== HASH CERRADO - SONDEO CUADRATICO =====");
		HashC hashCuadratico = new HashC(11);

		for (Register r : datos) {
		hashCuadratico.insertCuadratico(r);
		}

		System.out.println("\nTabla final (Cuadratico):");
		hashCuadratico.printTable();

		System.out.println("\nBusqueda:");
		Register encontrado2 = hashCuadratico.search(29);
		System.out.println("Buscar clave 29 -> " +
		(encontrado2 != null ? encontrado2 : "No encontrado"));

		System.out.println("\nEliminar clave 29");
		hashCuadratico.delete(29);

		System.out.println("\nTabla despues de eliminar:");
		hashCuadratico.printTable();
		}
	} 