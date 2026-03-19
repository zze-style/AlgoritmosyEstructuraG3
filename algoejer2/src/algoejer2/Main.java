package algoejer2;

public class Main {
	public static void main(String[] args) {
		try {
			MatrizMinera mina = new MatrizMinera("datos.txt");

			int k = 2; 
			mina.analizarRegion(k);

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
