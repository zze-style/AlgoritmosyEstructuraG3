package listaenlasada;

public class Main {
    public static void main(String[] args) {
        GestorDeTareas<Tarea> gestor = new GestorDeTareas<>();

        // 1. Agregar 5 tareas con distintas prioridades y estados
        gestor.agregarTarea(new Tarea("Diseniar BD",        2, "pendiente"));
        gestor.agregarTarea(new Tarea("Deploy produccion",  1, "pendiente"));
        gestor.agregarTarea(new Tarea("Documentar API",     3, "completada"));
        gestor.agregarTarea(new Tarea("Code review",        2, "pendiente"));
        gestor.agregarTarea(new Tarea("Corregir bug #42",   1, "completada"));

        // 2. Imprimir todas las tareas
        System.out.println("=== Tareas iniciales ===");
        gestor.imprimirTareas();

        // 3. Eliminar una tarea
        gestor.eliminarTarea(new Tarea("Code review", 2, "pendiente"));
        System.out.println("\n=== Despues de eliminar 'Code review' ===");
        gestor.imprimirTareas();

        // 4. Verificar si cierta tarea existe
        Tarea buscar = new Tarea("Deploy produccion", 1, "pendiente");
        System.out.println("\nContiene 'Deploy produccion': "
                           + gestor.contieneTarea(buscar));

        // 5. Tarea mas prioritaria
        System.out.println("Mas prioritaria: "
                           + gestor.obtenerTareaMasPrioritaria());

        // 6. Total de tareas
        System.out.println("Total de tareas: " + gestor.contarTareas());

        // 7. Invertir la lista
        gestor.invertirTareas();
        System.out.println("\n=== Lista invertida ===");
        gestor.imprimirTareas();

        // 8. Separar completadas
        ListLinked<Tarea> completadas = gestor.separarCompletadas();
        System.out.println("\n=== Tareas completadas ===");
        completadas.print();
        System.out.println("\n=== Tareas pendientes restantes ===");
        gestor.imprimirTareas();
    }
}
