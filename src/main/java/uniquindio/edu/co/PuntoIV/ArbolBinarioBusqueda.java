package uniquindio.edu.co.PuntoIV;



public class ArbolBinarioBusqueda {

    private NodoABB raiz;

    // Inserta un codigo en el arbol.
    public void insertar(int codigo) {
        raiz = insertarRecursivo(raiz, codigo);
    }

    // Busca recursivamente la posicion de insercion.
    private NodoABB insertarRecursivo(
            NodoABB nodo, int codigo) {

        if (nodo == null) {
            return new NodoABB(codigo);
        }

        if (codigo < nodo.codigo) {
            nodo.izquierdo =
                    insertarRecursivo(nodo.izquierdo, codigo);

        } else if (codigo > nodo.codigo) {
            nodo.derecho =
                    insertarRecursivo(nodo.derecho, codigo);
        }

        // Si el codigo existe, no se inserta.
        return nodo;
    }

    // Cuenta los equipos cuyos codigos estan dentro del rango.
    public int contarEquiposEnRango(
            NodoABB nodo, int inferior, int superior) {

        if (nodo == null || inferior > superior) {
            return 0;
        }

        // Si el codigo es menor, solo busca a la derecha.
        if (nodo.codigo < inferior) {
            return contarEquiposEnRango(
                    nodo.derecho, inferior, superior);
        }

        // Si el codigo es mayor, solo busca a la izquierda.
        if (nodo.codigo > superior) {
            return contarEquiposEnRango(
                    nodo.izquierdo, inferior, superior);
        }

        // El codigo esta dentro del rango.
        return 1
                + contarEquiposEnRango(
                nodo.izquierdo, inferior, superior)
                + contarEquiposEnRango(
                nodo.derecho, inferior, superior);
    }

    // Permite consultar el rango directamente desde la raiz.
    public int contarEquiposEnRango(
            int inferior, int superior) {

        return contarEquiposEnRango(
                raiz, inferior, superior);
    }

    // Elimina un codigo del arbol.
    public void eliminar(int codigo) {
        raiz = eliminarRecursivo(raiz, codigo);
    }

    // Busca y elimina el nodo indicado.
    private NodoABB eliminarRecursivo(
            NodoABB nodo, int codigo) {

        if (nodo == null) {
            return null;
        }

        if (codigo < nodo.codigo) {
            nodo.izquierdo =
                    eliminarRecursivo(nodo.izquierdo, codigo);

        } else if (codigo > nodo.codigo) {
            nodo.derecho =
                    eliminarRecursivo(nodo.derecho, codigo);

        } else {

            // Caso 1 y 2: nodo sin hijos o con un solo hijo.
            if (nodo.izquierdo == null) {
                return nodo.derecho;
            }

            if (nodo.derecho == null) {
                return nodo.izquierdo;
            }

            // Caso 3: nodo con dos hijos.
            // Se reemplaza por el sucesor inorden.
            NodoABB sucesor = buscarMinimo(nodo.derecho);
            nodo.codigo = sucesor.codigo;

            nodo.derecho = eliminarRecursivo(
                    nodo.derecho, sucesor.codigo);
        }

        return nodo;
    }

    // Busca el menor valor de un subarbol.
    private NodoABB buscarMinimo(NodoABB nodo) {
        if (nodo.izquierdo == null) {
            return nodo;
        }

        return buscarMinimo(nodo.izquierdo);
    }

    // Recorrido izquierda, raiz, derecha.
    public void inorden(NodoABB nodo) {
        if (nodo == null) {
            return;
        }

        inorden(nodo.izquierdo);
        System.out.print(nodo.codigo + " ");
        inorden(nodo.derecho);
    }

    // Recorrido raiz, izquierda, derecha.
    public void preorden(NodoABB nodo) {
        if (nodo == null) {
            return;
        }

        System.out.print(nodo.codigo + " ");
        preorden(nodo.izquierdo);
        preorden(nodo.derecho);
    }

    // Recorrido izquierda, derecha, raiz.
    public void postorden(NodoABB nodo) {
        if (nodo == null) {
            return;
        }

        postorden(nodo.izquierdo);
        postorden(nodo.derecho);
        System.out.print(nodo.codigo + " ");
    }

    // Calcula recursivamente la altura del arbol.
    private int altura(NodoABB nodo) {
        if (nodo == null) {
            return 0;
        }

        int izquierda = altura(nodo.izquierdo);
        int derecha = altura(nodo.derecho);

        return 1 + Math.max(izquierda, derecha);
    }

    // Muestra los nodos de un nivel determinado.
    private void mostrarNivel(NodoABB nodo, int nivel) {
        if (nodo == null) {
            return;
        }

        if (nivel == 1) {
            System.out.print(nodo.codigo + " ");
            return;
        }

        mostrarNivel(nodo.izquierdo, nivel - 1);
        mostrarNivel(nodo.derecho, nivel - 1);
    }

    // Recorre todos los niveles de forma recursiva.
    private void recorrerNiveles(
            NodoABB nodo, int nivel, int alturaTotal) {

        if (nivel > alturaTotal) {
            return;
        }

        mostrarNivel(nodo, nivel);

        recorrerNiveles(nodo, nivel + 1, alturaTotal);
    }

    // Realiza el recorrido por amplitud sin utilizar cola.
    public void amplitud() {
        recorrerNiveles(raiz, 1, altura(raiz));
    }

    // Devuelve la raiz del arbol.
    public NodoABB getRaiz() {
        return raiz;
    }
}

