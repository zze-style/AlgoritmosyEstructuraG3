package Hash;

/**
 * Implementacion de tabla hash cerrada
 * con sondeo lineal y cuadratico.
 */
public class HashC {

    private static class Element {
        Register register;
        boolean isAvailable;
        boolean isDeleted;

        public Element() {
            register = null;
            isAvailable = true;
            isDeleted = false;
        }
    }

    private Element[] table;
    private int size;

    public HashC(int size) {
        this.size = size;
        table = new Element[size];

        for (int i = 0; i < size; i++) {
            table[i] = new Element();
        }
    }

    private int hash(int key) {
        return key % size;
    }

    // ==========================
    // INSERCION LINEAL
    // ==========================
    public void insertLineal(Register reg) {
        int h = hash(reg.getKey());
        int saltos = 0;
        int idx = h;

        while (!table[idx].isAvailable) {
            idx = (idx + 1) % size;
            saltos++;

            if (saltos >= size) {
                System.out.println("Tabla llena.");
                return;
            }
        }

        table[idx].register = reg;
        table[idx].isAvailable = false;
        table[idx].isDeleted = false;

        System.out.println(reg.getKey()
                + ": hash=" + h
                + ", saltos=" + saltos
                + ", posicion final=" + idx);
    }

    // ==========================
    // INSERCION CUADRATICA
    // ==========================
    public void insertCuadratico(Register reg) {
        int h = hash(reg.getKey());
        int i = 0;
        int idx = h;

        while (!table[idx].isAvailable) {
            i++;

            if (i >= size) {
                System.out.println("Tabla llena.");
                return;
            }

            idx = (h + i * i) % size;
        }

        table[idx].register = reg;
        table[idx].isAvailable = false;
        table[idx].isDeleted = false;

        System.out.println(reg.getKey()
                + ": hash=" + h
                + ", saltos=" + i
                + ", posicion final=" + idx);
    }

    // ==========================
    // BUSQUEDA
    // ==========================
    public Register search(int key) {
        int idx = hash(key);
        int posInit = idx;

        do {
            if (table[idx].isAvailable && !table[idx].isDeleted) {
                return null;
            }

            if (!table[idx].isAvailable &&
                table[idx].register != null &&
                table[idx].register.getKey() == key) {
                return table[idx].register;
            }

            idx = (idx + 1) % size;
        } while (idx != posInit);

        return null;
    }

    // ==========================
    // ELIMINACION
    // ==========================
    public void delete(int key) {
        int idx = hash(key);
        int posInit = idx;

        do {
            if (table[idx].isAvailable && !table[idx].isDeleted) {
                break;
            }

            if (!table[idx].isAvailable &&
                table[idx].register != null &&
                table[idx].register.getKey() == key) {

                table[idx].register = null;
                table[idx].isAvailable = true;
                table[idx].isDeleted = true;
                return;
            }

            idx = (idx + 1) % size;
        } while (idx != posInit);

        System.out.println("No se encontro la clave " + key);
    }

    // ==========================
    // IMPRIMIR TABLA
    // ==========================
    public void printTable() {
        for (int i = 0; i < size; i++) {
            if (!table[i].isAvailable) {
                System.out.println("[" + i + "]: " + table[i].register);
            } else if (table[i].isDeleted) {
                System.out.println("[" + i + "]: <ELIMINADO>");
            } else {
                System.out.println("[" + i + "]: vacio");
            }
        }
    }
} 