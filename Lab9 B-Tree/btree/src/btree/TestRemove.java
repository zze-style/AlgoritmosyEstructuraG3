package btree;

public class TestRemove {
    public static void main(String[] args) {
        BTree<Integer> bt = new BTree<>(4);
        int[] datos = {50, 20, 70, 10, 30, 60, 80, 25, 27, 26, 65, 75, 85, 5};
        for (int d : datos) bt.insert(d);

        System.out.println("Arbol inicial:");
        System.out.println(bt.toString());

        System.out.println("Eliminar 25 (nodo con mas del minimo):");
        bt.remove(25);
        System.out.println(bt.toString());
        System.out.println("Eliminar 5 (puede requerir fusion):");
        bt.remove(5);
        System.out.println(bt.toString());
        System.out.println("Eliminar 50 (nodo interno):");
        bt.remove(50);
        System.out.println(bt.toString());

        System.out.println("Buscar 99 (inexistente):");
        bt.remove(99);
    }
}