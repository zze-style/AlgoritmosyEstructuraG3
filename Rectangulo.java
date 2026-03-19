package rectangulos;

public class Rectangulo {

    private Coordenada esquina1;                    // Representa el punto inferior izquierdo (minimos).
    private Coordenada esquina2;                    // Representa el punto superior derecho (maximos).

    public Rectangulo(Coordenada c1, Coordenada c2) {
        
        // Normalizamos las coordenadas para que esquina1 siempre sea la menor y esquina2 la mayor.
        
        double xmin = Math.min(c1.getX(), c2.getX());
        double xmax = Math.max(c1.getX(), c2.getX());

        double ymin = Math.min(c1.getY(), c2.getY());
        double ymax = Math.max(c1.getY(), c2.getY());

        esquina1 = new Coordenada(xmin, ymin);
        esquina2 = new Coordenada(xmax, ymax);
    }

    public Coordenada getEsquina1() {
        return esquina1;
    }

    public Coordenada getEsquina2() {
        return esquina2;
    }

    public void setEsquina1(Coordenada c) {
        esquina1 = c;
    }

    public void setEsquina2(Coordenada c) {
        esquina2 = c;
    }

    public double calculoArea() {                   // Calcula el área multiplicando base por altura.

        double base = esquina2.getX() - esquina1.getX();
        double altura = esquina2.getY() - esquina1.getY();

        return base * altura;
    }
    @Override
    public String toString() {
        return "(" + esquina1 + ", " + esquina2 + ")";
    }
}
