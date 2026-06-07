package btree;

public class TestSearch {
    public static void main(String[] args) {
        BTree<Integer> bt = new BTree<>(4);
        int[] datos = {50, 20, 70, 10, 30, 60, 80, 25, 27, 26, 65, 75, 85, 5};
        for (int d : datos) bt.insert(d);

        System.out.println("Buscar en hoja (extremo inicial): " + bt.search(5));
        System.out.println("Buscar en hoja (extremo final):   " + bt.search(85));
        System.out.println("Buscar en raiz:                   " + bt.search(50));
        System.out.println("Buscar dato inexistente:          " + bt.search(99));
        System.out.println("Buscar en nodo interno:           " + bt.search(20));  
    }
}