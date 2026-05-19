package Ejercicios;

import AVLTree.AVLTree;

public class TestPreOrden {

    public static void main(String[] args) {
        // Arbol 1
        AVLTree<Integer> avl1 = new AVLTree<>();
        int[] d1 = {10, 20, 30, 40, 50};
        for (int d : d1) {
            avl1.insertar(d);
        }

        System.out.print("AVL1 PreOrden: ");
        avl1.preOrden();

        // Arbol 2
        AVLTree<Integer> avl2 = new AVLTree<>();
        int[] d2 = {30, 10, 20, 50, 40};
        for (int d : d2) {
            avl2.insertar(d);
        }

        System.out.print("AVL2 PreOrden: ");
        avl2.preOrden();

        // InOrden
        System.out.print("AVL2 InOrden: ");
        avl2.inOrden();

        // PostOrden
        System.out.print("AVL2 PostOrden: ");
        avl2.postOrden();
    }
}