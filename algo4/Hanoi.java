package algo4;

public class Hanoi {

    public static void torresHanoi(int discos, int torre1, int torre2, int torre3) {
        // Caso base: solo un disco
        if (discos == 1) {
            System.out.println("Mover disco de torre " + torre1 + " a torre " + torre3);
        } else {
            // Mover n-1 discos de origen a auxiliar
            torresHanoi(discos - 1, torre1, torre3, torre2);
            // Mover el disco mayor a destino
            System.out.println("Mover disco de torre " + torre1 + " a torre " + torre3);
            // Mover n-1 discos de auxiliar a destino
            torresHanoi(discos - 1, torre2, torre1, torre3);
        }
    }

    public static void main(String[] args) {
        torresHanoi(3, 1, 2, 3);
    }
}