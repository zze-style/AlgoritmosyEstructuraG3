package biblioteca.ui;

import biblioteca.domain.Libro;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

class LibroTableModel extends AbstractTableModel {
    private final String[] columns = {"ISBN", "Titulo", "Autor", "Anio", "Categoria"};
    private List<Libro> libros = new ArrayList<>();

    void setLibros(List<Libro> libros) {
        this.libros = new ArrayList<>(libros);
        fireTableDataChanged();
    }

    Libro getLibroAt(int row) {
        return libros.get(row);
    }

    @Override
    public int getRowCount() {
        return libros.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Libro libro = libros.get(rowIndex);
        switch (columnIndex) {
            case 0: return libro.getIsbn();
            case 1: return libro.getTitulo();
            case 2: return libro.getAutor();
            case 3: return libro.getAnio();
            case 4: return libro.getCategoria();
            default: return "";
        }
    }
}
