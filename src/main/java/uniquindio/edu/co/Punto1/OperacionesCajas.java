package uniquindio.edu.co.Punto1;

/**
 * Contiene las operaciones para retirar y reorganizar
 * las cajas almacenadas en una pila.
 */
public class OperacionesCajas {

    /**
     * Busca una caja por su código, la retira y reorganiza
     * las cajas restantes según el valor de la caja retirada.
     *
     * Si el valor es par:
     * Primero quedan las posiciones impares y después las pares.
     *
     * Si el valor es impar:
     * Primero quedan las posiciones pares y después las impares.
     *
     * Las posiciones se cuentan desde la cima, comenzando en 1.
     *
     * @param pila pila de cajas que se va a modificar.
     * @param codigo código de la caja que se desea retirar.
     * @return true si encontró y retiró la caja.
     *         false si no encontró la caja o la pila está vacía.
     */
    public static boolean retirarCaja(Pila<Caja> pila, String codigo) {

        // Verificar que la pila y el código sean válidos.
        if (pila == null || pila.estaVacia() || codigo == null) {
            return false;
        }

        // Pila auxiliar para guardar las cajas superiores.
        Pila<Caja> cajasSuperiores = new Pila<>();

        Caja cajaEncontrada = null;

        /*
         * Buscar la caja.
         * Se retiran temporalmente las cajas que están
         * por encima de la caja solicitada.
         */
        while (!pila.estaVacia()) {

            Nodo<Caja> nodoRetirado = pila.desapilar();

            Caja cajaActual = nodoRetirado.getDato();

            // Comprobar si encontramos el código solicitado.
            if (cajaActual.getCodigo().equals(codigo)) {

                cajaEncontrada = cajaActual;

                // No se vuelve a insertar porque debe retirarse.
                break;

            } else {

                // Guardar temporalmente la caja.
                cajasSuperiores.apilar(cajaActual);
            }
        }

        /*
         * Si no se encontró la caja, se devuelve todo
         * a la pila original conservando su orden.
         */
        if (cajaEncontrada == null) {

            while (!cajasSuperiores.estaVacia()) {

                pila.apilar(cajasSuperiores.desapilar().getDato());
            }

            return false;
        }

        /*
         * Restaurar las cajas que estaban encima
         * de la caja retirada.
         */
        while (!cajasSuperiores.estaVacia()) {

            pila.apilar(cajasSuperiores.desapilar().getDato());
        }

        /*
         * Crear dos pilas auxiliares:
         * una para posiciones impares y otra para pares.
         */
        Pila<Caja> posicionesImpares = new Pila<>();

        Pila<Caja> posicionesPares = new Pila<>();

        int posicion = 1;

        /*
         * Recorrer la pila restante desde la cima.
         * Separar las cajas según su posición.
         */
        while (!pila.estaVacia()) {

            Caja cajaActual = pila.desapilar().getDato();

            if (posicion % 2 == 1) {

                // Posición impar.
                posicionesImpares.apilar(cajaActual);

            } else {

                // Posición par.
                posicionesPares.apilar(cajaActual);
            }

            posicion++;
        }

        /*
         * Reconstruir la pila según la paridad
         * del valor de la caja retirada.
         */
        if (cajaEncontrada.getValor() % 2 == 0) {

            // Valor par:
            // Primero posiciones pares (quedan abajo).
            // Después posiciones impares (quedan arriba).

            pasarPilaA(pila, posicionesPares);

            pasarPilaA(pila, posicionesImpares);

        } else {

            // Valor impar:
            // Primero posiciones impares (quedan abajo).
            // Después posiciones pares (quedan arriba).

            pasarPilaA(pila, posicionesImpares);

            pasarPilaA(pila, posicionesPares);
        }

        return true;
    }

    /**
     * Transfiere los elementos de una pila origen
     * hacia una pila destino.
     *
     * Los elementos se pasan mediante desapilar()
     * y apilar(), por lo que su orden se invierte.
     *
     * @param destino pila que recibe los elementos.
     * @param origen pila de la cual se retiran.
     */
    private static void pasarPilaA(
            Pila<Caja> destino,
            Pila<Caja> origen) {

        while (!origen.estaVacia()) {

            destino.apilar(origen.desapilar().getDato());
        }
    }
}

