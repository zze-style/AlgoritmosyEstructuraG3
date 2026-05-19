package avlapp;

import arboles.Arbol;
import arboles.AVLTree;
import java.util.Arrays;

public class ComparisonResult {
    public static String compare(int[] values) {
        Arbol<Integer> bst = new Arbol<>();
        AVLTree<Integer> avl = new AVLTree<>();

        for (int value : values) {
            bst.insertar(value);
            avl.insertar(value);
        }

        return "Datos: " + Arrays.toString(values)
                + "\nBST InOrden: " + bst.inOrdenLista()
                + "\nAVL InOrden: " + avl.inOrdenLista()
                + "\nAltura BST: " + bst.altura()
                + "\nAltura AVL: " + avl.altura()
                + "\nArea BST: " + bst.areaBST()
                + "\nArea AVL: " + avl.areaBST()
                + "\nBusqueda 30 en BST: " + bst.buscar(30)
                + "\nBusqueda 30 en AVL: " + avl.buscar(30);
    }
}
