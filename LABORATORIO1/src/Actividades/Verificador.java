package Actividades;

public class Verificador {
     // Verifica si hay intersección en ambos ejes simultáneamente.
    public static boolean esSobrePos(Rectangulo r1, Rectangulo r2) {

        return (r1.getEsquina1().getX() < r2.getEsquina2().getX() &&
                r1.getEsquina2().getX() > r2.getEsquina1().getX() &&
                r1.getEsquina1().getY() < r2.getEsquina2().getY() &&
                r1.getEsquina2().getY() > r2.getEsquina1().getY());
    }
    // Verifica si un borde de r1 coincide exactamente con un borde de r2.
    public static boolean esJunto(Rectangulo r1, Rectangulo r2) {

        boolean tocaX = r1.getEsquina2().getX() == r2.getEsquina1().getX() ||
                        r2.getEsquina2().getX() == r1.getEsquina1().getX();

        boolean tocaY = r1.getEsquina2().getY() == r2.getEsquina1().getY() ||
                        r2.getEsquina2().getY() == r1.getEsquina1().getY();
        
    // Verifica que estén alineados en el otro eje para que realmente se "toquen".
        boolean rangoY = r1.getEsquina1().getY() <= r2.getEsquina2().getY() &&
                         r1.getEsquina2().getY() >= r2.getEsquina1().getY();

        boolean rangoX = r1.getEsquina1().getX() <= r2.getEsquina2().getX() &&
                         r1.getEsquina2().getX() >= r2.getEsquina1().getX();

        return (tocaX && rangoY) || (tocaY && rangoX);
    }

    public static boolean esDisjunto(Rectangulo r1, Rectangulo r2) {
        return !esSobrePos(r1, r2) && !esJunto(r1, r2);
    }
}
