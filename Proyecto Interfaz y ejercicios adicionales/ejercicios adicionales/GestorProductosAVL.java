package otro;
import arboles.*;
public class GestorProductosAVL {

    private AVLTree<Integer> arbol;

    public GestorProductosAVL() {
        arbol = new AVLTree<>();
    }

    // Insertar producto
    public void insertarProducto(int codigo) {
        arbol.insertar(codigo);
        System.out.println("Producto " + codigo + " insertado.");
        System.out.print("InOrden: ");
        arbol.inOrden();
    }

    // Buscar producto
    public void buscarProducto(int codigo) {
        boolean encontrado = arbol.buscar(codigo);

        System.out.println("Buscar producto " + codigo + ": "
                + (encontrado ? "EXISTE" : "NO EXISTE"));
    }

    // Eliminar producto
    public void eliminarProducto(int codigo) {
        arbol.remove(codigo);

        System.out.println("Producto " + codigo + " eliminado.");
        System.out.print("InOrden: ");
        arbol.inOrden();
    }

    public static void main(String[] args) {

        GestorProductosAVL almacen = new GestorProductosAVL();

        System.out.println("=== INSERTANDO PRODUCTOS ===");

        int[] productos = {30, 10, 20, 50, 40, 60};

        for (int p : productos) {
            almacen.insertarProducto(p);
        }

        System.out.println("\n=== BUSCANDO PRODUCTOS ===");

        almacen.buscarProducto(40);
        almacen.buscarProducto(99);

        System.out.println("\n=== ELIMINANDO PRODUCTOS ===");

        almacen.eliminarProducto(10);
        almacen.eliminarProducto(50);

        System.out.println("\n=== ESTADO FINAL ===");
        System.out.print("Productos en orden: ");
        almacen.arbol.inOrden();

        System.out.println("Altura final: "
                + almacen.arbol.altura());
    }
}