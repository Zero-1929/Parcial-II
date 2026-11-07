package uniquindio.edu.co.PuntoIII;

/**
 * Prueba del Punto III: seleccion de solicitudes para una jornada.
 */
public class Main {

    private static final int CAPACIDAD = 14;

    public static void main(String[] args) {
        Cola<Paquete> colaInicial = construirColaDePrueba();

        System.out.println("=== Punto III: Seleccion de solicitudes ===");
        System.out.println("Cola inicial (frente -> final):");
        System.out.println("  " + colaInicial);

        Cola<Paquete> copiaParaEvaluar = SeleccionadorPaquetes.copiar(colaInicial);

        Cola<Paquete> seleccionados =
                SeleccionadorPaquetes.seleccionarPaquetes(colaInicial, CAPACIDAD);

        System.out.println();
        System.out.println("Paquetes evaluados (urgentes y luego normales):");
        Cola<Paquete> ordenEvaluacion = SeleccionadorPaquetes.ordenDeEvaluacion(copiaParaEvaluar);
        imprimirEvaluaciones(ordenEvaluacion, seleccionados);

        System.out.println();
        System.out.println("Cola retornada (seleccionados en orden):");
        System.out.println("  " + seleccionados);

        System.out.println();
        System.out.println("Cola de pendientes despues de la seleccion:");
        System.out.println("  " + colaInicial);

        int capacidadUsada = SeleccionadorPaquetes.pesoTotal(seleccionados);

        System.out.println();
        System.out.println("Capacidad inicial: " + CAPACIDAD + " kg");
        System.out.println("Capacidad usada: " + capacidadUsada + " kg");
        System.out.println("Capacidad disponible restante: " + (CAPACIDAD - capacidadUsada) + " kg");
    }

    private static Cola<Paquete> construirColaDePrueba() {
        Cola<Paquete> cola = new Cola<>();
        cola.encolar(new Paquete("P1", TipoEntrega.NORMAL, 4));
        cola.encolar(new Paquete("P2", TipoEntrega.URGENTE, 7));
        cola.encolar(new Paquete("P3", TipoEntrega.NORMAL, 3));
        cola.encolar(new Paquete("P4", TipoEntrega.URGENTE, 5));
        cola.encolar(new Paquete("P5", TipoEntrega.NORMAL, 6));
        cola.encolar(new Paquete("P6", TipoEntrega.URGENTE, 4));
        cola.encolar(new Paquete("P7", TipoEntrega.NORMAL, 2));
        return cola;
    }

    private static void imprimirEvaluaciones(Cola<Paquete> orden, Cola<Paquete> seleccionados) {
        if (orden.estaVacia()) {
            return;
        }
        Paquete paquete = orden.desencolar();
        boolean seleccionado = SeleccionadorPaquetes.contiene(seleccionados, paquete.getCodigo());
        System.out.println("  " + paquete + " -> "
                + (seleccionado ? "SELECCIONADO" : "queda PENDIENTE"));
        imprimirEvaluaciones(orden, seleccionados);
    }
}
