package uniquindio.edu.co.Punto1;

/**
 * Implementación de una pila genérica mediante nodos enlazados.
 *
 * La pila trabaja bajo el principio LIFO:
 * Last In, First Out (último en entrar, primero en salir).
 *
 * @param <T> tipo de elementos almacenados.
 */
public class Pila<T> {

    private Nodo<T> cima;
    private int tam;

    /**
     * Constructor de la pila.
     * Inicialmente la pila está vacía.
     */
    public Pila() {
        cima = null;
        tam = 0;
    }

    /**
     * Agrega un elemento en la cima de la pila.
     *
     * @param dato elemento que se desea insertar.
     */
    public void apilar(T dato) {

        Nodo<T> nuevoNodo = new Nodo<>(dato);

        nuevoNodo.setSiguiente(cima);

        cima = nuevoNodo;

        tam++;
    }

    /**
     * Elimina el nodo que se encuentra en la cima.
     *
     * @return nodo eliminado o null si la pila está vacía.
     */
    public Nodo<T> desapilar() {

        Nodo<T> auxiliar = cima;

        if (cima != null) {

            cima = cima.getSiguiente();

            // Se desconecta el nodo retirado.
            auxiliar.setSiguiente(null);

            tam--;
        }

        return auxiliar;
    }

    /**
     * Comprueba si la pila está vacía.
     *
     * @return true si está vacía, false en caso contrario.
     */
    public boolean estaVacia() {
        return cima == null;
    }

    /**
     * Consulta el dato que está en la cima
     * sin eliminarlo.
     *
     * @return dato de la cima o null.
     */
    public T verCima() {

        if (cima == null) {
            return null;
        }

        return cima.getDato();
    }

    /**
     * Obtiene la referencia al nodo de la cima.
     *
     * Permite recorrer la pila sin retirar sus elementos.
     */
    public Nodo<T> getCima() {
        return cima;
    }

    /**
     * Obtiene la cantidad de elementos de la pila.
     */
    public int getTam() {
        return tam;
    }

    /**
     * Muestra el contenido de la pila
     * desde la cima hasta el fondo.
     *
     * @return contenido de la pila en formato String.
     */
    public String mostrar() {

        String listaString = "[";

        if (cima == null) {

            listaString += " Null";

        } else {

            Nodo<T> auxiliar = cima;

            while (auxiliar != null) {

                listaString += " " + auxiliar.getDato();

                auxiliar = auxiliar.getSiguiente();
            }
        }

        listaString += " ]";

        return listaString;
    }
}

