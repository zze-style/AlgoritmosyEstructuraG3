package PyPOOEje1;
import java.util.Scanner;

public class Principal {

	public static Rectangulo rectanguloSobre(Rectangulo r1, Rectangulo r2) {

	    double x1 = Math.max(r1.getEsquina1().getX(), r2.getEsquina1().getX());
	    double y1 = Math.max(r1.getEsquina1().getY(), r2.getEsquina1().getY());

	    double x2 = Math.min(r1.getEsquina2().getX(), r2.getEsquina2().getX());
	    double y2 = Math.min(r1.getEsquina2().getY(), r2.getEsquina2().getY());

	    return new Rectangulo(new Coordenada(x1, y1), new Coordenada(x2, y2));
	}

    public static void mostrarRectangulo(String nombre, Rectangulo r) {
        System.out.println("Rectangulo " + nombre + " = " + r);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        ContainerRect contenedor = new ContainerRect(3);
      
        

        System.out.println("Ingrese la esquina del 1er rectángulo:");
        double x1 = sc.nextDouble();
        double y1 = sc.nextDouble();

        System.out.println("Ingrese la esquina opuesta del 1er rectángulo:");
        double x2 = sc.nextDouble();
        double y2 = sc.nextDouble();

        System.out.println("Ingrese la esquina del 2do rectángulo:");
        double x3 = sc.nextDouble();
        double y3 = sc.nextDouble();

        System.out.println("Ingrese la esquina opuesta del 2do rectángulo:");
        double x4 = sc.nextDouble();
        double y4 = sc.nextDouble();

        Rectangulo A = new Rectangulo(new Coordenada(x1, y1), new Coordenada(x2, y2));
        Rectangulo B = new Rectangulo(new Coordenada(x3, y3), new Coordenada(x4, y4));
        
        contenedor.addRectangulo(A);
        contenedor.addRectangulo(B);

        mostrarRectangulo("A", A);
        mostrarRectangulo("B", B);

        if (Verificador.esSobrePos(A, B)) {

            System.out.println("Rectangulos A y B se sobreponen");

            Rectangulo rSobre = rectanguloSobre(A, B);
            contenedor.addRectangulo(rSobre);
            System.out.println("Area de sobreposicion = " + rSobre.calculoArea());

        } else if (Verificador.esJunto(A, B)) {

            System.out.println("Rectangulos A y B se juntan");

        } else {

            System.out.println("Rectangulos A y B son disjuntos");
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("                         CONTENEDOR                       ");
        System.out.println(contenedor.toString());
        sc.close();
    }
}
