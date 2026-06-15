package transporte.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SolicitudViaje {
    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("HH:mm:ss");
    private final String pasajero;
    private final String origen;
    private final String destino;
    private final LocalDateTime hora;

    public SolicitudViaje(String pasajero, String origen, String destino) {
        this.pasajero = pasajero;
        this.origen = origen;
        this.destino = destino;
        this.hora = LocalDateTime.now();
    }

    public String getPasajero() { return pasajero; }
    public String getOrigen()   { return origen; }
    public String getDestino()  { return destino; }

    @Override
    public String toString() {
        return hora.format(FMT) + " | " + pasajero
                + " | " + origen + " -> " + destino;
    }
}