package transporte.service;

import transporte.domain.SolicitudViaje;
import transporte.graph.GraphLink;
import transporte.structures.*;

import java.util.List;
import java.util.Optional;

public class TransporteService {
    private GraphLink<String> red;
    private final QueueArray<SolicitudViaje> solicitudes;
    private final ListLinked<String> historial;

    public TransporteService(int capSolicitudes) {
        red = new GraphLink<>();
        solicitudes = new QueueArray<>(capSolicitudes);
        historial = new ListLinked<>();
    }

    public void agregarParadero(String nombre) {
        red.insertVertex(nombre);
        log("Paradero agregado: " + nombre);
    }

    public void agregarRuta(String a, String b, int minutos) {
        red.insertEdge(a, b, minutos);
        log("Ruta " + a + " <-> " + b + " (" + minutos + " min)");
    }

    public boolean eliminarRuta(String a, String b) {
        boolean r = red.removeEdge(a, b);
        log(r ? "Ruta eliminada: " + a + "-" + b
               : "No se encontro ruta: " + a + "-" + b);
        return r;
    }

    public Optional<List<String>> rutaMasRapida(String origen,
                                                 String destino) {
        List<String> path = red.dijkstra(origen, destino);
        if (path == null || path.isEmpty()) {
            log("Sin ruta: " + origen + " -> " + destino);
            return Optional.empty();
        }
        log("Ruta calculada: " + path);
        return Optional.of(path);
    }

    public boolean redConectada() {
        return red.isConexo();
    }

    public List<String> adyacentes(String paradero) {
        return red.adjacentVertices(paradero);
    }

    public void registrarSolicitud(String pasajero,
                                   String origen, String destino) {
        solicitudes.enqueue(new SolicitudViaje(pasajero, origen, destino));
        log("Solicitud: " + pasajero + " (" + origen + "->" + destino + ")");
    }

    public SolicitudViaje atenderSolicitud() throws ExceptionIsEmpty {
        SolicitudViaje s = solicitudes.dequeue();
        log("Atendido: " + s);
        return s;
    }

    public ListLinked<SolicitudViaje> listarSolicitudes() {
        return solicitudes.toList();
    }

    public ListLinked<String> getHistorial() {
        return historial;
    }

    public GraphLink<String> getRed() { return red; }

    private void log(String msg) { historial.insertLast(msg); }
}