package Hash.ejercicios;

import listaenlasada.ListLinked;
import listaenlasada.Node;

public class SessionCache {
    private ListLinked<Session>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public SessionCache(int size) {
        this.size = size;
        this.table = new ListLinked[size];
        for (int i = 0; i < size; i++) table[i] = new ListLinked<>();
    }

    private int hash(String token) {
        int h = Math.abs(token.hashCode());
        return h % size;
    }

    public void login(String token, String username, String role, long ttlMs) {
        long now = System.currentTimeMillis();
        Session s = new Session(token, username, role, now + ttlMs);
        table[hash(token)].insertLast(s);
    }

    public Session validate(String token) {
        int idx = hash(token);
        long now = System.currentTimeMillis();
        Node<Session> current = table[idx].first;
        while (current != null) {
            if (current.value.getToken().equals(token)) {
                return current.value.isExpired(now) ? null : current.value;
            }
            current = current.next;
        }
        return null;
    }

    public void logout(String token) {
        int idx = hash(token);
        Node<Session> current = table[idx].first;
        while (current != null) {
            if (current.value.getToken().equals(token)) {
                table[idx].removeNode(current.value);
                return;
            }
            current = current.next;
        }
    }

    public void cleanExpired() {
        long now = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            // Se recolectan primero las sesiones vencidas y luego se eliminan,
            // para no modificar la lista mientras se recorre.
            ListLinked<Session> vencidas = new ListLinked<>();
            Node<Session> current = table[i].first;
            while (current != null) {
                if (current.value.isExpired(now)) {
                    vencidas.insertLast(current.value);
                }
                current = current.next;
            }
            Node<Session> v = vencidas.first;
            while (v != null) {
                table[i].removeNode(v.value);
                v = v.next;
            }
        }
    }

    public int countActive() {
        long now = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < size; i++) {
            Node<Session> current = table[i].first;
            while (current != null) {
                if (!current.value.isExpired(now)) count++;
                current = current.next;
            }
        }
        return count;
    }
} 