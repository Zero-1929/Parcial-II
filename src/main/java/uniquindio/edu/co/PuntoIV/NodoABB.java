package uniquindio.edu.co.PuntoIV;


public class NodoABB {

    int codigo;
    NodoABB izquierdo;
    NodoABB derecho;

    // Crea un nodo con su codigo y sin hijos.
    public NodoABB(int codigo) {
        this.codigo = codigo;
        this.izquierdo = null;
        this.derecho = null;
    }
}
