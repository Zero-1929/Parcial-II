package uniquindio.edu.co.PuntoII;


public class Historial {

    // Obtiene un segmento sin modificar la pila original.
    public static <T> Pila<T> obtenerSegmento(
            Pila<T> original, int inicio, int fin) {

        Pila<T> resultado = new Pila<>();

        // Verificar que las posiciones sean validas.
        if (inicio < 1 || inicio > fin ||
                fin > original.tamanio()) {
            return resultado;
        }

        Pila<T> auxiliar = new Pila<>();
        Pila<T> segmento = new Pila<>();

        int posicion = 1;

        // Recorrer la pila original.
        while (!original.estaVacia()) {
            T elemento = original.desapilar();
            auxiliar.apilar(elemento);

            // Guardar los elementos del segmento.
            if (posicion >= inicio && posicion <= fin) {
                segmento.apilar(elemento);
            }

            posicion++;
        }

        // Restaurar la pila original.
        while (!auxiliar.estaVacia()) {
            original.apilar(auxiliar.desapilar());
        }

        // Invertir el segmento para conservar su orden.
        while (!segmento.estaVacia()) {
            resultado.apilar(segmento.desapilar());
        }

        return resultado;
    }
}
