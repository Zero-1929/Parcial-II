package uniquindio.edu.co.Punto1;

/**
 *
 *
 * Contiene las pruebas de retiro de cajas y evaluación
 * de la expresión aritmética.
 */
public class MainPunto1 {

    public static void main(String[] args) {

        // ==========================================
        // 1. CREAR LA PILA Y AGREGAR LAS CAJAS
        // ==========================================

        Pila<Caja> pilaCajas = new Pila<>();

        pilaCajas.apilar(new Caja("C1", 2));
        pilaCajas.apilar(new Caja("C2", 4));
        pilaCajas.apilar(new Caja("C3", 6));
        pilaCajas.apilar(new Caja("C4", 8));
        pilaCajas.apilar(new Caja("C5", 3));
        pilaCajas.apilar(new Caja("C6", 9));
        pilaCajas.apilar(new Caja("C7", 5));

        System.out.println("===== PILA INICIAL =====");

        System.out.println(pilaCajas.mostrar());


        // ==========================================
        // 2. RETIRAR LAS CAJAS SOLICITADAS
        // ==========================================

        System.out.println("\n===== RETIRO DE CAJAS =====");

        // Primera llamada: retirar C3.
        boolean resultado1 =
                OperacionesCajas.retirarCaja(pilaCajas, "C3");

        System.out.println("\nRetirar C3: " + resultado1);

        System.out.println("Pila: " + pilaCajas.mostrar());


        // Segunda llamada: retirar C4.
        boolean resultado2 =
                OperacionesCajas.retirarCaja(pilaCajas, "C4");

        System.out.println("\nRetirar C4: " + resultado2);

        System.out.println("Pila: " + pilaCajas.mostrar());


        // Tercera llamada: retirar C7.
        boolean resultado3 =
                OperacionesCajas.retirarCaja(pilaCajas, "C7");

        System.out.println("\nRetirar C7: " + resultado3);

        System.out.println("Pila: " + pilaCajas.mostrar());


        // ==========================================
        // 3. OBTENER LOS VALORES DE LAS CUATRO CAJAS
        // ==========================================

        Nodo<Caja> auxiliar = pilaCajas.getCima();

        double valorPrimera =
                auxiliar.getDato().getValor();

        auxiliar = auxiliar.getSiguiente();

        double valorSegunda =
                auxiliar.getDato().getValor();

        auxiliar = auxiliar.getSiguiente();

        double valorTercera =
                auxiliar.getDato().getValor();

        auxiliar = auxiliar.getSiguiente();

        double valorCuarta =
                auxiliar.getDato().getValor();


        // ==========================================
        // 4. CALCULAR LAS VARIABLES A, B, C Y D
        // ==========================================

        double a = valorPrimera;

        double b = valorSegunda / a;

        double c = valorTercera + a;

        double d = (valorCuarta + c) * a;


        System.out.println("\n===== VALORES DE LAS VARIABLES =====");

        System.out.println("A = " + a);

        System.out.println(
                "B = " + valorSegunda + " / " + a + " = " + b
        );

        System.out.println(
                "C = " + valorTercera + " + " + a + " = " + c
        );

        System.out.println(
                "D = (" + valorCuarta + " + " + c
                        + ") * " + a + " = " + d
        );


        // ==========================================
        // 5. CONVERTIR LA EXPRESIÓN A POSTFIJA
        // ==========================================

        String expresionInfija = "(A+B)*C-(A-B)*D";

        String expresionPostfija =
                EvaluadorExpresiones.convertirAPostfija(
                        expresionInfija
                );


        System.out.println("\n===== CONVERSIÓN DE LA EXPRESIÓN =====");

        System.out.println("Infija: " + expresionInfija);

        System.out.println("Postfija: " + expresionPostfija);


        // ==========================================
        // 6. REEMPLAZAR LAS VARIABLES POR SUS VALORES
        // ==========================================

        String postfijaNumerica =
                EvaluadorExpresiones.reemplazarVariables(
                        expresionPostfija,
                        a,
                        b,
                        c,
                        d
                );

        System.out.println("\n===== EXPRESIÓN CON VALORES =====");

        System.out.println(postfijaNumerica);


        // ==========================================
        // 7. EVALUAR LA EXPRESIÓN CON UNA PILA PROPIA
        // ==========================================

        double resultado =
                EvaluadorExpresiones.evaluarPostfija(
                        postfijaNumerica
                );


        // ==========================================
        // 8. MOSTRAR EL RESULTADO FINAL
        // ==========================================

        System.out.println("\n===== RESULTADO FINAL =====");

        System.out.println("Resultado = " + resultado);
    }
}
