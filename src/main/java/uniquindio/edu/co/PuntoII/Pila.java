package uniquindio.edu.co.PuntoII;

public class Pila<T> {

    private Nodo<T> cima;
    private int tamanio;

    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        Nodo(T dato, Nodo<T> siguiente) {
            this.dato = dato;
            this.siguiente = siguiente;
        }
    }

    // Agrega un elemento a la cima.
    public void apilar(T dato) {
        cima = new Nodo<>(dato, cima);
        tamanio++;
    }

    // Retira y devuelve el elemento de la cima.
    public T desapilar() {
        if (estaVacia()) {
            throw new IllegalStateException("Pila vacia");
        }

        T dato = cima.dato;
        cima = cima.siguiente;
        tamanio--;
        return dato;
    }

    // Consulta el elemento de la cima sin retirarlo.
    public T cima() {
        if (estaVacia()) {
            throw new IllegalStateException("Pila vacia");
        }

        return cima.dato;
    }

    // Verifica si la pila esta vacia.
    public boolean estaVacia() {
        return cima == null;
    }

    // Devuelve la cantidad de elementos.
    public int tamanio() {
        return tamanio;
    }

    // Muestra los elementos desde la cima hasta el fondo.
    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();
        Nodo<T> actual = cima;

        while (actual != null) {
            resultado.append(actual.dato);

            if (actual.siguiente != null) {
                resultado.append(" -> ");
            }

            actual = actual.siguiente;
        }

        return resultado.toString();
    }
}

