package Hash.ejercicios;

public class TestSessionCache {
	
    public static void main(String[] args) throws InterruptedException {
        SessionCache cache = new SessionCache(16);

        // (1) Tres usuarios inician sesion con tokens y expiraciones distintas
        cache.login("abc123", "hsebastian", "ESTUDIANTE", 5000);   // expira en 5s
        cache.login("xyz789", "mjoaquin",   "ESTUDIANTE", 60000);  // expira en 60s
        cache.login("qwe456", "lroman",     "DOCENTE",    60000);  // expira en 60s

        Thread.sleep(6000); // se simula el paso del tiempo: abc123 ya expiro

        // (2) Se validan los tokens
        System.out.println("Validar abc123 (deberia haber expirado): " + cache.validate("abc123"));
        System.out.println("Validar xyz789 (deberia ser valido): " + cache.validate("xyz789"));
        System.out.println("Validar qwe456 (deberia ser valido): " + cache.validate("qwe456"));

        // (3) Un usuario cierra sesion explicitamente
        cache.logout("qwe456");
        System.out.println("Validar qwe456 tras logout: " + cache.validate("qwe456"));

        // (4) Limpieza de sesiones vencidas
        cache.cleanExpired();
        System.out.println("Sesiones activas tras cleanExpired(): " + cache.countActive());
    }
}