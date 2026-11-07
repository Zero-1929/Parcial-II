package uniquindio.edu.co.PuntoIII;

/**
 * Logica de seleccion de paquetes para una jornada de reparto.
 * Todo el procesamiento y la reorganizacion se hace con recursividad:
 * no se utilizan for, while ni do-while.
 */
public final class SeleccionadorPaquetes {

    private SeleccionadorPaquetes() {
    }

    /**
     * Selecciona los paquetes que puede transportar el vehiculo.
     * Primero considera los urgentes y luego los normales, respetando
     * en cada grupo el orden de registro. Los paquetes seleccionados se
     * retiran de la cola de pendientes.
     *
     * @param pendientes cola de paquetes pendientes (orden de registro)
     * @param capacidad  capacidad disponible del vehiculo en kg
     * @return nueva cola con los paquetes seleccionados, en el orden en
     *         que fueron elegidos (urgentes primero, luego normales)
     */
    public static Cola<Paquete> seleccionarPaquetes(Cola<Paquete> pendientes, int capacidad) {
        Cola<Paquete> seleccionados = new Cola<>();

        if (pendientes == null || capacidad <= 0) {
            return seleccionados;
        }

        Cola<Paquete> todos = new Cola<>();
        Cola<Paquete> urgentes = new Cola<>();
        Cola<Paquete> normales = new Cola<>();

        clasificar(pendientes, todos, urgentes, normales, pendientes.tamanio());

        int capacidadRestante = seleccionarPorTipo(urgentes, seleccionados, capacidad);
        seleccionarPorTipo(normales, seleccionados, capacidadRestante);

        reconstruirPendientes(todos, seleccionados, pendientes);

        return seleccionados;
    }

    /**
     * Devuelve una copia de la cola sin alterar el orden de la original.
     */
    public static <T> Cola<T> copiar(Cola<T> original) {
        Cola<T> copia = new Cola<>();
        copiarRotando(original, copia, original.tamanio());
        return copia;
    }

    /**
     * Devuelve una cola con el orden en que se evaluarian los paquetes:
     * primero los urgentes y despues los normales, cada grupo en su
     * orden de registro. No modifica la cola recibida.
     */
    public static Cola<Paquete> ordenDeEvaluacion(Cola<Paquete> cola) {
        Cola<Paquete> copia = copiar(cola);
        Cola<Paquete> todos = new Cola<>();
        Cola<Paquete> urgentes = new Cola<>();
        Cola<Paquete> normales = new Cola<>();

        clasificar(copia, todos, urgentes, normales, copia.tamanio());

        Cola<Paquete> orden = new Cola<>();
        volcar(urgentes, orden);
        volcar(normales, orden);
        return orden;
    }

    /**
     * Indica si un paquete con el codigo dado esta en la cola.
     * Rota la cola completa para no perder su contenido ni su orden.
     */
    public static boolean contiene(Cola<Paquete> cola, String codigo) {
        if (cola == null || codigo == null) {
            return false;
        }
        return contieneRotando(cola, codigo, cola.tamanio());
    }

    /**
     * Suma de los pesos de los paquetes de la cola sin alterar su orden.
     */
    public static int pesoTotal(Cola<Paquete> cola) {
        if (cola == null) {
            return 0;
        }
        return pesoTotalRotando(cola, cola.tamanio());
    }

    private static void clasificar(Cola<Paquete> origen,
                                   Cola<Paquete> todos,
                                   Cola<Paquete> urgentes,
                                   Cola<Paquete> normales,
                                   int restantes) {
        if (restantes == 0) {
            return;
        }
        Paquete paquete = origen.desencolar();
        todos.encolar(paquete);
        if (paquete.esUrgente()) {
            urgentes.encolar(paquete);
        } else {
            normales.encolar(paquete);
        }
        clasificar(origen, todos, urgentes, normales, restantes - 1);
    }

    private static int seleccionarPorTipo(Cola<Paquete> porProcesar,
                                          Cola<Paquete> seleccionados,
                                          int capacidad) {
        if (porProcesar.estaVacia()) {
            return capacidad;
        }
        Paquete paquete = porProcesar.desencolar();
        if (paquete.getPeso() <= capacidad) {
            seleccionados.encolar(paquete);
            return seleccionarPorTipo(porProcesar, seleccionados, capacidad - paquete.getPeso());
        }
        return seleccionarPorTipo(porProcesar, seleccionados, capacidad);
    }

    private static void reconstruirPendientes(Cola<Paquete> todos,
                                              Cola<Paquete> seleccionados,
                                              Cola<Paquete> pendientes) {
        if (todos.estaVacia()) {
            return;
        }
        Paquete paquete = todos.desencolar();
        if (!contiene(seleccionados, paquete.getCodigo())) {
            pendientes.encolar(paquete);
        }
        reconstruirPendientes(todos, seleccionados, pendientes);
    }

    private static <T> void copiarRotando(Cola<T> original, Cola<T> copia, int restantes) {
        if (restantes == 0) {
            return;
        }
        T dato = original.desencolar();
        original.encolar(dato);
        copia.encolar(dato);
        copiarRotando(original, copia, restantes - 1);
    }

    private static void volcar(Cola<Paquete> origen, Cola<Paquete> destino) {
        if (origen.estaVacia()) {
            return;
        }
        destino.encolar(origen.desencolar());
        volcar(origen, destino);
    }

    private static boolean contieneRotando(Cola<Paquete> cola, String codigo, int restantes) {
        if (restantes == 0) {
            return false;
        }
        Paquete paquete = cola.desencolar();
        boolean esEste = paquete.getCodigo().equals(codigo);
        cola.encolar(paquete);
        boolean enElResto = contieneRotando(cola, codigo, restantes - 1);
        return esEste || enElResto;
    }

    private static int pesoTotalRotando(Cola<Paquete> cola, int restantes) {
        if (restantes == 0) {
            return 0;
        }
        Paquete paquete = cola.desencolar();
        cola.encolar(paquete);
        return paquete.getPeso() + pesoTotalRotando(cola, restantes - 1);
    }
}
