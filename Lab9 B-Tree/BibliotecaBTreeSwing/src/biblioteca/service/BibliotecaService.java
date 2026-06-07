package biblioteca.service;

import biblioteca.domain.Libro;
import biblioteca.domain.SolicitudPrestamo;
import biblioteca.structures.ExceptionIsEmpty;
import biblioteca.structures.ListLinked;
import biblioteca.structures.QueueArray;
import biblioteca.tree.BNode;
import biblioteca.tree.BTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class BibliotecaService {
    private BTree<Libro> catalogo;
    private final QueueArray<SolicitudPrestamo> solicitudes;
    private final ListLinked<String> historial;

    public BibliotecaService(int orden, int capacidadSolicitudes) {
        catalogo = new BTree<>(orden);
        solicitudes = new QueueArray<>(capacidadSolicitudes);
        historial = new ListLinked<>();
    }

    public void cargarDesdeArchivo(Path path) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine();
            if (line == null) {
                return;
            }
            int orden = Integer.parseInt(line.trim());
            catalogo = new BTree<>(orden);
            int loaded = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 5) {
                    continue;
                }
                Libro libro = new Libro(
                        parts[0],
                        parts[1],
                        parts[2],
                        Integer.parseInt(parts[3].trim()),
                        parts[4]);
                if (catalogo.insert(libro)) {
                    loaded++;
                }
            }
            log("Carga inicial: " + loaded + " libros desde " + path.getFileName());
        }
    }

    public boolean registrarLibro(Libro libro) {
        boolean inserted = catalogo.insert(libro);
        log(inserted ? "Registrado libro " + libro.getIsbn() : "Intento duplicado " + libro.getIsbn());
        return inserted;
    }

    public boolean eliminarLibro(String isbn) {
        boolean removed = catalogo.remove(Libro.clave(isbn));
        log(removed ? "Eliminado libro " + isbn : "No se encontro para eliminar " + isbn);
        return removed;
    }

    public Optional<Libro> buscarPorIsbn(String isbn) {
        Optional<Libro> found = catalogo.search(Libro.clave(isbn));
        log(found.isPresent() ? "Busqueda exitosa " + isbn : "Busqueda sin resultado " + isbn);
        return found;
    }

    public List<Libro> buscarRango(String desde, String hasta) {
        List<Libro> values = catalogo.range(Libro.clave(desde), Libro.clave(hasta));
        log("Busqueda por rango [" + desde + ", " + hasta + "]: " + values.size() + " resultado(s)");
        return values;
    }

    public void registrarSolicitud(String estudiante, String isbn) {
        if (!catalogo.search(Libro.clave(isbn)).isPresent()) {
            throw new IllegalArgumentException("No se puede solicitar un ISBN que no existe en el catalogo.");
        }
        SolicitudPrestamo solicitud = new SolicitudPrestamo(estudiante, isbn);
        solicitudes.enqueue(solicitud);
        log("Solicitud en cola: " + estudiante + " -> " + isbn);
    }

    public SolicitudPrestamo atenderSolicitud() throws ExceptionIsEmpty {
        SolicitudPrestamo solicitud = solicitudes.dequeue();
        log("Solicitud atendida: " + solicitud);
        return solicitud;
    }

    public List<Libro> listarLibros() {
        return catalogo.inOrder();
    }

    public List<SolicitudPrestamo> listarSolicitudes() {
        return solicitudes.toList();
    }

    public List<String> listarHistorial() {
        return historial.toList();
    }

    public int totalLibros() {
        return catalogo.size();
    }

    public int alturaArbol() {
        return catalogo.height();
    }

    public int ordenArbol() {
        return catalogo.getOrder();
    }

    public BNode<Libro> getRaiz() {
        return catalogo.getRoot();
    }

    private void log(String message) {
        historial.insertLast(message);
    }
}
