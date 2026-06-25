package Hash.ejercicios;

public class Session {
	
    private String token;
    private String username;
    private String role;
    private long expiresAt; // timestamp Unix en milisegundos

    public Session(String token, String username, String role, long expiresAt) {
        this.token = token;
        this.username = username;
        this.role = role;
        this.expiresAt = expiresAt;
    }

    public String getToken() { return token; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
    public long getExpiresAt() { return expiresAt; }

    public boolean isExpired(long now) { return now > expiresAt; }

    @Override
    public String toString() {
        return "Session{token=" + token + ", user=" + username
                + ", role=" + role + ", expiresAt=" + expiresAt + "}";
    }
} 