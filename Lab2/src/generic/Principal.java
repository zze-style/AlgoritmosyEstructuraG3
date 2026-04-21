	package generic;

public class Principal {
	public static void main(String[] args) {
		System.out.println("Chocolatina");
		Bolsa < Chocolatina > bolsaCho = new Bolsa<>(6);
		
		Chocolatina c = new Chocolatina("milka");
		Chocolatina c1 = new Chocolatina("milka");
		Chocolatina c2 = new Chocolatina("ferrero");
		bolsaCho.add(c);
		bolsaCho.add(c1);
		bolsaCho.add(c2);
		for (Chocolatina chocolatina: bolsaCho) {
			System.out.println(chocolatina.getMarca());
			}
		System.out.println("Golosina");
		Bolsa < Golosina > bolsago = new Bolsa<>(6);
		Golosina g = new Golosina("Golosina 1", 15);
		Golosina g1 = new Golosina("Golosina 2",20);
		Golosina g2 = new Golosina("Golosina 3",50);
		bolsago.add(g);
		bolsago.add(g1);
		bolsago.add(g2);
		for (Golosina golosina: bolsago) {
			System.out.println(golosina.getNombre());
			}
		}
}

