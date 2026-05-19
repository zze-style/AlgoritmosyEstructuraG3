package avlapp;

public class AppSmokeTest {
    public static void main(String[] args) {
        AVLManager manager = new AVLManager("Ticket");
        int[] inserts = {30, 10, 20, 40, 50, 25};
        for (int value : inserts) {
            manager.insert(value);
        }

        manager.search(20);
        manager.search(60);
        manager.delete(10);
        manager.delete(40);
        manager.delete(30);

        System.out.println(manager.report());
        System.out.println();
        System.out.println(ComparisonResult.compare(new int[] {10, 20, 30, 40, 50, 60, 70}));
    }
}
