package biblioteca.domain;

import java.util.Objects;

public class Libro implements Comparable<Libro> {
    private final String isbn;
    private final String titulo;
    private final String autor;
    private final int anio;
    private final String categoria;

    public Libro(String isbn, String titulo, String autor, int anio, String categoria) {
        this.isbn = requireText(isbn, "ISBN");
        this.titulo = requireText(titulo, "Titulo");
        this.autor = requireText(autor, "Autor");
        this.anio = anio;
        this.categoria = requireText(categoria, "Categoria");
    }

    public static Libro clave(String isbn) {
        return new Libro(isbn, "Busqueda", "N/A", 0, "N/A");
    }

    private static String requireText(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " es obligatorio.");
        }
        return value.trim();
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnio() {
        return anio;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public int compareTo(Libro other) {
        return this.isbn.compareTo(other.isbn);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Libro)) return false;
        Libro other = (Libro) obj;
        return Objects.equals(isbn, other.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        return isbn + " | " + titulo + " | " + autor + " | " + anio + " | " + categoria;
    }
}
