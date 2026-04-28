package ejer8;

public class MainSpotify {
    public static void main(String[] args) {
        ColaReproduccion<Cancion> cola = new ColaReproduccion<>();

        cola.agregarCancion(new Cancion("Bohemian Rhapsody", "Queen",       354));
        cola.agregarCancion(new Cancion("Blinding Lights",   "The Weeknd",  200));
        cola.agregarCancion(new Cancion("Shape of You",      "Ed Sheeran",  234));
        cola.agregarCancion(new Cancion("Levitating",        "Dua Lipa",    203));
        cola.agregarCancion(new Cancion("Bad Guy",           "Billie Eilish",194));
        cola.agregarCancion(new Cancion("Watermelon Sugar",  "Harry Styles",174));

        System.out.println("=== Cola de Reproduccion Inicial ===");
        cola.mostrarCola();

        System.out.println("\n--- Avanzando 3 canciones ---");
        for (int i = 0; i < 3; i++) {
            Cancion c = (Cancion) cola.reproducirSiguiente();
            if (c != null) System.out.println("Reproduciendo: " + c);
        }

        System.out.println("\n--- Retrocediendo 1 cancion ---");
        Cancion anterior = (Cancion) cola.reproducirAnterior();
        if (anterior != null) System.out.println("Reproduciendo: " + anterior);

        System.out.println("\n=== Mezclando... ===");
        cola.mezclar();
        cola.mostrarCola();

        int total = cola.duracionTotal();
        System.out.printf("\nDuracion total: %d:%02d%n", total / 60, total % 60);
    }
}
