package ejer4;

public class Entry implements Comparable<Entry> {
    String name;
    int secondary;

    public Entry(String name, int secondary) {
        this.name = name;
        this.secondary = secondary;
    }

    @Override
    public int compareTo(Entry other) {
        return Integer.compare(this.secondary, other.secondary);
    }

    @Override
    public String toString() {
        return "(" + name + "," + secondary + ")";
    }
}
