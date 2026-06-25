package Hash;

public class Main { 

    public static void main(String[] args) {

        System.out.println("===== TABLA HASH CON SONDEO LINEAL =====");

        HashC hashLineal =
                new HashC(11, HashC.ProbeType.LINEAR);

        hashLineal.insert(new Register(10, "Juan"));
        hashLineal.insert(new Register(21, "Maria"));
        hashLineal.insert(new Register(32, "Pedro"));
        hashLineal.insert(new Register(43, "Ana"));

        hashLineal.printTable();

        System.out.println("\nBuscar clave 21:");
        Register r1 = hashLineal.search(21);

        if (r1 != null)
            System.out.println("Encontrado: " + r1);
        else
            System.out.println("No encontrado");

        System.out.println("\nEliminar clave 21");
        hashLineal.delete(21);

        hashLineal.printTable();

        System.out.println("\nBuscar clave 21 nuevamente:");
        r1 = hashLineal.search(21);

        if (r1 != null)
            System.out.println("Encontrado: " + r1);
        else
            System.out.println("No encontrado");

        System.out.println("\n\n===== TABLA HASH CON SONDEO CUADRÁTICO =====");

        HashC hashCuadratico =
                new HashC(11, HashC.ProbeType.QUADRATIC);

        hashCuadratico.insert(new Register(10, "Juan"));
        hashCuadratico.insert(new Register(21, "Maria"));
        hashCuadratico.insert(new Register(32, "Pedro"));
        hashCuadratico.insert(new Register(43, "Ana"));

        hashCuadratico.printTable();

        System.out.println("\nBuscar clave 32:");
        Register r2 = hashCuadratico.search(32);

        if (r2 != null)
            System.out.println("Encontrado: " + r2);
        else
            System.out.println("No encontrado");

        System.out.println("\nEliminar clave 32");
        hashCuadratico.delete(32);

        hashCuadratico.printTable();

        System.out.println("\nBuscar clave 32 nuevamente:");
        r2 = hashCuadratico.search(32);

        if (r2 != null)
            System.out.println("Encontrado: " + r2);
        else
            System.out.println("No encontrado");
    }
} 