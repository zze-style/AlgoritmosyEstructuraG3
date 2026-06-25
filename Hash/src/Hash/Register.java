package Hash;

/**
 * Clase que representa un registro con una clave y un nombre.
 */
public class Register {
    private int key;       // Clave que se usara como indice en la tabla hash
    private String name;   // Nombre asociado al registro

    public Register(int key, String name) {
        this.key = key;
        this.name = name;
    }

    public int getKey() { return key; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "(" + key + ", " + name + ")";
    }
}
