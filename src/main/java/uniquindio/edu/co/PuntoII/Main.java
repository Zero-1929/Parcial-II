package uniquindio.edu.co.PuntoII;


public class Main {

    public static void main(String[] args) {

        Pila<String> acciones = new Pila<>();

        acciones.apilar("A1");
        acciones.apilar("A2");
        acciones.apilar("A3");
        acciones.apilar("A4");
        acciones.apilar("A5");
        acciones.apilar("A6");

        System.out.println("Pila original:");
        System.out.println(acciones);

        Pila<String> segmento =
                Historial.obtenerSegmento(acciones, 2, 4);

        System.out.println("\nSegmento obtenido:");
        System.out.println(segmento);

        System.out.println("\nPila original despues:");
        System.out.println(acciones);

        // Prueba con una sola posicion.
        System.out.println("\nSegmento (3, 3):");
        System.out.println(
                Historial.obtenerSegmento(acciones, 3,3)
        );

        // Prueba con posiciones invalidas.
        System.out.println("\nSegmento (2, 10):");
        System.out.println(
                Historial.obtenerSegmento(acciones, 2,10 )
        );
    }
}
