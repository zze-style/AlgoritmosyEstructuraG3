package biblioteca.app;

import biblioteca.domain.Libro;
import biblioteca.service.BibliotecaService;

import java.nio.file.Paths;

public class AppSmokeTest {
    public static void main(String[] args) throws Exception {
        BibliotecaService service = new BibliotecaService(4, 5);
        service.cargarDesdeArchivo(Paths.get("data", "biblioteca.txt"));

        assertTrue(service.totalLibros() == 8, "Debe cargar 8 libros.");
        assertTrue(service.buscarPorIsbn("9780132350884").isPresent(), "Debe encontrar Clean Code.");
        assertTrue(service.buscarRango("9780130000000", "9780600000000").size() == 6, "Debe listar rango esperado.");
        assertTrue(service.registrarLibro(new Libro("9781111111111", "Estructuras de Datos", "Equipo G3", 2026, "Algoritmos")), "Debe insertar libro nuevo.");
        assertTrue(!service.registrarLibro(new Libro("9781111111111", "Duplicado", "Equipo G3", 2026, "Algoritmos")), "Debe rechazar duplicado.");
        service.registrarSolicitud("Ana Torres", "9781111111111");
        assertTrue(service.listarSolicitudes().size() == 1, "Debe encolar solicitud.");
        service.atenderSolicitud();
        assertTrue(service.listarSolicitudes().isEmpty(), "Debe atender solicitud.");
        assertTrue(service.eliminarLibro("9781111111111"), "Debe eliminar libro insertado.");

        System.out.println("OK - prueba de humo completada.");
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
