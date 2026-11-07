package uniquindio.edu.co.PuntoIII;

import java.util.NoSuchElementException;

/**
 * Cola propia y generica, implementada con nodos enlazados.
 * Respeta el orden FIFO (frente -> final).
 *
 * @param <T> tipo de dato almacenado
 */
public class Cola<T> {

    private static class Nodo<T> {
        private final T dato;
        private Nodo<T> siguiente;

        private Nodo(T dato) {
            this.dato = dato;
        }
    }

    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamanio;

    public Cola() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

    /**
     * Agrega un elemento al final de la cola.
     */
    public void encolar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (fin == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
        tamanio++;
    }

    /**
     * Retira y devuelve el elemento del frente.
     */
    public T desencolar() {
        if (estaVacia()) {
            throw new NoSuchElementException("La cola esta vacia.");
        }
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        tamanio--;
        return dato;
    }

    /**
     * Devuelve el elemento del frente sin retirarlo.
     */
    public T frente() {
        if (estaVacia()) {
            throw new NoSuchElementException("La cola esta vacia.");
        }
        return frente.dato;
    }

    public boolean estaVacia() {
        return tamanio == 0;
    }

    public int tamanio() {
        return tamanio;
    }

    @Override
    public String toString() {
        return "[" + texto(frente) + "]";
    }

    private String texto(Nodo<T> nodo) {
        if (nodo == null) {
            return "";
        }
        if (nodo.siguiente == null) {
            return String.valueOf(nodo.dato);
        }
        return nodo.dato + " -> " + texto(nodo.siguiente);
    }
}
