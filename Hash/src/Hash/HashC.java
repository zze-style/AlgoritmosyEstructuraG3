package Hash;

public class HashC {

    private enum State {
        EMPTY,
        OCCUPIED,
        DELETED
    }

    public enum ProbeType {
        LINEAR,
        QUADRATIC
    }

    private static class Element {
        Register register;
        State state;

        public Element() {
            register = null;
            state = State.EMPTY;
        }
    }

    private Element[] table;
    private int size;
    private ProbeType probeType;

    public HashC(int size, ProbeType probeType) {
        this.size = size;
        this.probeType = probeType;

        table = new Element[size];

        for (int i = 0; i < size; i++) {
            table[i] = new Element();
        }
    }

    private int hash(int key) {
        return Math.abs(key) % size;
    }

    private int probe(int h, int i) {
        switch (probeType) {

            case QUADRATIC:
                return (h + i * i) % size;

            case LINEAR:
            default:
                return (h + i) % size;
        }
    }

    // ==========================
    // INSERCIÓN
    // ==========================
    public boolean insert(Register reg) {

        int h = hash(reg.getKey());
        int firstDeleted = -1;

        for (int i = 0; i < size; i++) {

            int idx = probe(h, i);

            if (table[idx].state == State.OCCUPIED) {

                if (table[idx].register.getKey() == reg.getKey()) {
                    System.out.println("La clave ya existe.");
                    return false;
                }

            } else {

                if (table[idx].state == State.DELETED &&
                    firstDeleted == -1) {

                    firstDeleted = idx;

                } else if (table[idx].state == State.EMPTY) {

                    if (firstDeleted != -1) {
                        idx = firstDeleted;
                    }

                    table[idx].register = reg;
                    table[idx].state = State.OCCUPIED;

                    System.out.println(
                        reg.getKey()
                        + ": hash=" + h
                        + ", saltos=" + i
                        + ", posicion final=" + idx
                    );

                    return true;
                }
            }
        }

        if (firstDeleted != -1) {

            table[firstDeleted].register = reg;
            table[firstDeleted].state = State.OCCUPIED;

            return true;
        }

        System.out.println("Tabla llena.");
        return false;
    }

    // ==========================
    // BÚSQUEDA
    // ==========================
    public Register search(int key) {

        int h = hash(key);

        for (int i = 0; i < size; i++) {

            int idx = probe(h, i);

            if (table[idx].state == State.EMPTY) {
                return null;
            }

            if (table[idx].state == State.OCCUPIED &&
                table[idx].register.getKey() == key) {

                return table[idx].register;
            }
        }

        return null;
    }

    // ==========================
    // ELIMINACIÓN
    // ==========================
    public boolean delete(int key) {

        int h = hash(key);

        for (int i = 0; i < size; i++) {

            int idx = probe(h, i);

            if (table[idx].state == State.EMPTY) {
                return false;
            }

            if (table[idx].state == State.OCCUPIED &&
                table[idx].register.getKey() == key) {

                table[idx].register = null;
                table[idx].state = State.DELETED;

                return true;
            }
        }

        return false;
    }

    // ==========================
    // IMPRIMIR TABLA
    // ==========================
    public void printTable() {

        for (int i = 0; i < size; i++) {

            switch (table[i].state) {

                case OCCUPIED:
                    System.out.println("[" + i + "] -> "
                            + table[i].register);
                    break;

                case DELETED:
                    System.out.println("[" + i + "] -> <ELIMINADO>");
                    break;

                case EMPTY:
                    System.out.println("[" + i + "] -> vacío");
                    break;
            }
        }
    }
} 