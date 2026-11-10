package uniquindio.edu.co.PuntoIV;

public class Main {

    public static void main(String[] args) {

        ArbolBinarioBusqueda arbol =
                new ArbolBinarioBusqueda();

        int[] codigos = {
                41, 20, 52, 16, 6, 13, 12,
                60, 22, 47, 50, 60, 43, 44
        };

        // Insertar todos los codigos.
        for (int codigo : codigos) {
            arbol.insertar(codigo);
        }

        // Mostrar los cuatro recorridos.
        System.out.println("=== RECORRIDOS DEL ABB ===");

        System.out.print("Inorden: ");
        arbol.inorden(arbol.getRaiz());

        System.out.print("\nPreorden: ");
        arbol.preorden(arbol.getRaiz());

        System.out.print("\nPostorden: ");
        arbol.postorden(arbol.getRaiz());

        System.out.print("\nAmplitud: ");
        arbol.amplitud();

        // Consultar los equipos dentro de un rango.
        System.out.println("\n\n=== CONSULTA POR RANGO ===");

        System.out.println("Rango [20, 50]: " +
                arbol.contarEquiposEnRango(20, 50));

        System.out.println("Rango [41, 41]: " +
                arbol.contarEquiposEnRango(41, 41));

        System.out.println("Rango [42, 42]: " +
                arbol.contarEquiposEnRango(42, 42));

        System.out.println("Rango invalido [50, 20]: " +
                arbol.contarEquiposEnRango(50, 20));

        System.out.println("Rango sin equipos [70, 90]: " +
                arbol.contarEquiposEnRango(70, 90));

        // Comprobar el caso de arbol vacio.
        ArbolBinarioBusqueda vacio =
                new ArbolBinarioBusqueda();

        System.out.println("Arbol vacio: " +
                vacio.contarEquiposEnRango(1, 100));

        // Eliminar el nodo 41.
        System.out.println("\n=== ELIMINACION ===");

        arbol.eliminar(41);

        System.out.print("Inorden despues de eliminar 41: ");
        arbol.inorden(arbol.getRaiz());

        System.out.print("\nAmplitud despues de eliminar 41: ");
        arbol.amplitud();
    }
}

