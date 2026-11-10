package uniquindio.edu.co.Punto1;

/**
 * Clase Nodo genérica.
 *
 * Cada nodo almacena un dato y una referencia al siguiente nodo.
 *
 * @param <T> tipo de dato que almacena el nodo.
 */
public class Nodo<T> {

    private T dato;
    private Nodo<T> siguiente;

    /**
     * Constructor del nodo.
     *
     * @param dato elemento que se almacenará.
     */
    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    /**
     * Obtiene el dato almacenado.
     */
    public T getDato() {
        return dato;
    }

    /**
     * Modifica el dato almacenado.
     */
    public void setDato(T dato) {
        this.dato = dato;
    }

    /**
     * Obtiene la referencia al siguiente nodo.
     */
    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    /**
     * Modifica la referencia al siguiente nodo.
     */
    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * Representación textual del nodo.
     */
    @Override
    public String toString() {
        return "Nodo{" + dato + "}";
    }
}

