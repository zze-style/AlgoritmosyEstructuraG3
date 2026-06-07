package biblioteca.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SolicitudPrestamo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final String estudiante;
    private final String isbn;
    private final LocalDateTime fechaRegistro;

    public SolicitudPrestamo(String estudiante, String isbn) {
        if (estudiante == null || estudiante.trim().isEmpty()) {
            throw new IllegalArgumentException("El estudiante es obligatorio.");
        }
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("El ISBN es obligatorio.");
        }
        this.estudiante = estudiante.trim();
        this.isbn = isbn.trim();
        this.fechaRegistro = LocalDateTime.now();
    }

    public String getEstudiante() {
        return estudiante;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return fechaRegistro.format(FORMATTER) + " - " + estudiante + " solicita " + isbn;
    }
}
