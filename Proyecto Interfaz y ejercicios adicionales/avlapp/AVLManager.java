package avlapp;

import arboles.AVLTree;
import listaenlasada.*;

public class AVLManager {
    private final AVLTree<Integer> tree = new AVLTree<>();
    private final String itemName;

    public AVLManager(String itemName) {
        this.itemName = itemName;
    }

    public String insert(int code) {
        tree.insertar(code);
        return itemName + " " + code + " registrado.";
    }

    public String search(int code) {
        return tree.buscar(code)
                ? itemName + " " + code + " encontrado."
                : itemName + " " + code + " no encontrado.";
    }

    public String delete(int code) {
        if (!tree.buscar(code)) {
            return itemName + " " + code + " no existe.";
        }
        tree.remove(code);
        return itemName + " " + code + " eliminado.";
    }

    public String report() {
        return "InOrden: " + tree.inOrdenLista()
                + "\nPreOrden: " + tree.preOrdenLista()
                + "\nPostOrden: " + tree.postOrdenLista()
                + "\nBFS: " + tree.bfsRecursivo()
                + "\nBFS por niveles: " + formatLevels(tree.bfsPorNiveles())
                + "\nAltura: " + tree.altura()
                + "\nCantidad: " + tree.contarNodos()
                + "\nHojas: " + tree.contarHojas()
                + "\nArea BST: " + tree.areaBST()
                + "\nBST valido: " + tree.isValidBST();
    }

    private String formatLevels(ListLinked<ListLinked<Integer>> levels) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < levels.length(); i++) {
            if (i > 0) {
                builder.append(" | ");
            }
            builder.append("Nivel ").append(i).append(": ").append(levels.get(i));
        }
        return builder.toString();
    }

    public AVLTree<Integer> getTree() {
        return tree;
    }
}
