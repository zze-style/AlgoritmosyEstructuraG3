package btree;

import java.io.*;

public class Biblioteca {
    private BTree<Libro> arbol;

    public Biblioteca() {
        arbol = null;
    }

    // Carga los libros desde biblioteca.txt y construye el BTree
    public void cargarDesdeArchivo(String ruta) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(ruta));
        String linea = br.readLine();
        if (linea == null) { br.close(); return; }
        int orden = Integer.parseInt(linea.trim());
        arbol = new BTree<>(orden);
        System.out.println("Orden del arbol: " + orden);

        while ((linea = br.readLine()) != null) {
            linea = linea.trim();
            if (linea.isEmpty()) continue;
            String[] partes = linea.split(",");
            if (partes.length < 4) continue;
            String isbn = partes[0].trim();
            String titulo = partes[1].trim();
            String autor = partes[2].trim();
            int anio = Integer.parseInt(partes[3].trim());
            Libro libro = new Libro(isbn, titulo, autor, anio);
            try {
                arbol.insert(libro);
                System.out.println("Insertado: " + libro.getTitulo());
            } catch (Exception e) {
                System.out.println("ISBN duplicado: " + isbn);
            }
        }
        br.close();
    }

    // Busca un libro por ISBN mostrando el camino recorrido
    public void buscarPorISBN(String isbn) {
        Libro clave = new Libro(isbn, "", "", 0);
        boolean encontrado = arbol.search(clave);
        if (!encontrado) System.out.println("ISBN " + isbn + " no encontrado.");
    }

    public void mostrarOrdenado() {
        System.out.println("Libros en orden por ISBN:");
        System.out.println(arbol.toString());
    }

    public void mostrarEstadisticas() {
        System.out.println("Altura del arbol B: " + calcularAltura(arbol.getRoot()));
        System.out.println("Total de libros:    " + contarClaves(arbol.getRoot()));
    }

    // Calcula la altura del arbol B iterativamente
    private int calcularAltura(BNode<Libro> nodo) {
        if (nodo == null) return -1;
        int h = 0;
        BNode<Libro> actual = nodo;
        while (actual.childs.get(0) != null) {
            actual = actual.childs.get(0);
            h++;
        }
        return h;
    }

    private int contarClaves(BNode<Libro> nodo) {
        if (nodo == null) return 0;
        int total = nodo.count;
        for (int i = 0; i <= nodo.count; i++) {
            total += contarClaves(nodo.childs.get(i));
        }
        return total;
    }

    public static void main(String[] args) {
        Biblioteca bib = new Biblioteca();
        try {
            bib.cargarDesdeArchivo("D:\\eclipse\\btree\\src\\btree\\biblioteca.txt");
        } catch (IOException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
            return;
        }
        System.out.println("\n=== BUSQUEDAS ===");
        bib.buscarPorISBN("9780132350884"); // Clean Code - existe
        bib.buscarPorISBN("9780000000000"); // no existe

        System.out.println("\n=== LIBROS ORDENADOS ===");
        bib.mostrarOrdenado();

        System.out.println("\n=== ESTADISTICAS ===");
        bib.mostrarEstadisticas();
    }
}