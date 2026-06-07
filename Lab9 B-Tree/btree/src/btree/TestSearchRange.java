package btree;

public class TestSearchRange {
    public static void main(String[] args) {
        BTree<Integer> bt = new BTree<>(4);
        int[] datos = {50, 20, 70, 10, 30, 60, 80, 25, 27, 26, 65, 75, 85, 5};
        for (int d : datos) bt.insert(d);

        bt.searchRange(20, 40);   // 20 25 26 27 30
        bt.searchRange(60, 80);   // 60 65 70 75 80
        bt.searchRange(1, 5);     // 5
        bt.searchRange(90, 100);  // (vacio - rango inexistente)
        bt.searchRange(50, 30);   // Rango invalido: min > max
    }
}
