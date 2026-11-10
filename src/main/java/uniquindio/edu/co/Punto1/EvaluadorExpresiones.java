package uniquindio.edu.co.Punto1;

/**
 * Clase encargada de convertir y evaluar expresiones aritméticas.
 *
 * Utiliza la pila propia implementada mediante nodos enlazados.
 */
public class EvaluadorExpresiones {

    /**
     * Convierte una expresión infija a notación postfija.
     *
     * Utiliza el algoritmo Shunting Yard:
     * - Los operandos pasan directamente a la salida.
     * - Los operadores se almacenan en una pila.
     * - Los paréntesis controlan el orden de las operaciones.
     *
     * @param infija expresión original.
     * @return expresión convertida a postfija.
     */
    public static String convertirAPostfija(String infija) {

        Pila<Character> operadores = new Pila<>();

        String salida = "";

        // Recorrer la expresión de izquierda a derecha.
        for (int i = 0; i < infija.length(); i++) {

            char token = infija.charAt(i);

            if (Character.isWhitespace(token)) {
                continue;
            }

            // Si es una letra o número, es un operando.
            if (Character.isLetterOrDigit(token)) {

                salida += token + " ";

            } else if (token == '(') {

                // Apilar el paréntesis de apertura.
                operadores.apilar(token);

            } else if (token == ')') {

                /*
                 * Desapilar operadores hasta encontrar
                 * el paréntesis de apertura.
                 */
                while (!operadores.estaVacia()
                        && operadores.verCima() != '(') {

                    salida += operadores.desapilar().getDato() + " ";
                }

                if (operadores.estaVacia()) {
                    throw new IllegalArgumentException(
                            "Paréntesis desbalanceados.");
                }

                // Retirar el paréntesis de apertura.
                operadores.desapilar();

            } else if (esOperador(token)) {

                /*
                 * Mientras haya un operador con mayor
                 * o igual precedencia en la cima,
                 * se pasa a la expresión de salida.
                 */
                while (!operadores.estaVacia()
                        && operadores.verCima() != '('
                        && precedencia(operadores.verCima())
                        >= precedencia(token)) {

                    salida += operadores.desapilar().getDato() + " ";
                }

                // Apilar el nuevo operador.
                operadores.apilar(token);

            } else {

                throw new IllegalArgumentException(
                        "Token no válido: " + token);
            }
        }

        // Desapilar los operadores que quedaron pendientes.
        while (!operadores.estaVacia()) {

            char operador = operadores.desapilar().getDato();

            if (operador == '(') {
                throw new IllegalArgumentException(
                        "Paréntesis desbalanceados.");
            }

            salida += operador + " ";
        }

        return salida.trim();
    }

    /**
     * Comprueba si un carácter es un operador.
     */
    private static boolean esOperador(char token) {

        return token == '+'
                || token == '-'
                || token == '*'
                || token == '/';
    }

    /**
     * Determina la precedencia de los operadores.
     *
     * Multiplicación y división tienen mayor
     * precedencia que suma y resta.
     */
    private static int precedencia(char operador) {

        if (operador == '*' || operador == '/') {
            return 2;
        }

        if (operador == '+' || operador == '-') {
            return 1;
        }

        return 0;
    }

    /**
     * Sustituye las variables A, B, C y D por sus valores
     * dentro de una expresión postfija.
     *
     * @param postfija expresión con variables.
     * @param a valor de A.
     * @param b valor de B.
     * @param c valor de C.
     * @param d valor de D.
     * @return expresión postfija con valores numéricos.
     */
    public static String reemplazarVariables(
            String postfija,
            double a,
            double b,
            double c,
            double d) {

        String resultado = "";

        String token = "";

        /*
         * Recorrer los caracteres de la expresión.
         * Se utiliza un espacio adicional al final
         * para procesar el último token.
         */
        for (int i = 0; i <= postfija.length(); i++) {

            char caracter;

            if (i == postfija.length()) {
                caracter = ' ';
            } else {
                caracter = postfija.charAt(i);
            }

            if (Character.isWhitespace(caracter)) {

                if (!token.isEmpty()) {

                    // Sustituir cada variable por su valor.
                    if (token.equals("A")) {
                        token = String.valueOf(a);
                    } else if (token.equals("B")) {
                        token = String.valueOf(b);
                    } else if (token.equals("C")) {
                        token = String.valueOf(c);
                    } else if (token.equals("D")) {
                        token = String.valueOf(d);
                    }

                    resultado += token + " ";

                    token = "";
                }

            } else {

                token += caracter;
            }
        }

        return resultado.trim();
    }

    /**
     * Evalúa una expresión en notación postfija.
     *
     * Cuando encuentra un número, lo apila.
     * Cuando encuentra un operador, desapila dos operandos,
     * realiza la operación y apila el resultado.
     *
     * @param postfija expresión numérica en notación postfija.
     * @return resultado de la expresión.
     */
    public static double evaluarPostfija(String postfija) {

        Pila<Double> valores = new Pila<>();

        String token = "";

        System.out.println("\nEvaluación de la expresión postfija:");

        System.out.printf(
                "%-12s %-28s %s%n",
                "Elemento",
                "Operación",
                "Pila (cima a fondo)"
        );

        /*
         * Recorrer los tokens de la expresión.
         * Los espacios permiten identificar cada elemento.
         */
        for (int i = 0; i <= postfija.length(); i++) {

            char caracter;

            if (i == postfija.length()) {
                caracter = ' ';
            } else {
                caracter = postfija.charAt(i);
            }

            if (Character.isWhitespace(caracter)) {

                if (!token.isEmpty()) {

                    /*
                     * Si es un operador, se deben retirar
                     * dos operandos de la pila.
                     */
                    if (token.length() == 1
                            && esOperador(token.charAt(0))) {

                        if (valores.getTam() < 2) {
                            throw new IllegalArgumentException(
                                    "Faltan operandos.");
                        }

                        // Primer desapilado: segundo operando.
                        double op2 =
                                valores.desapilar().getDato();

                        // Segundo desapilado: primer operando.
                        double op1 =
                                valores.desapilar().getDato();

                        // Realizar la operación.
                        double resultado = aplicarOperador(
                                op1,
                                op2,
                                token.charAt(0)
                        );

                        // Guardar el resultado en la pila.
                        valores.apilar(resultado);

                        System.out.printf(
                                "%-12s %-28s %s%n",
                                token,
                                op1 + " " + token + " " + op2
                                        + " = " + resultado,
                                valores.mostrar()
                        );

                    } else {

                        // Si es un número, convertirlo y apilarlo.
                        double numero = Double.parseDouble(token);

                        valores.apilar(numero);

                        System.out.printf(
                                "%-12s %-28s %s%n",
                                token,
                                "Apilar " + numero,
                                valores.mostrar()
                        );
                    }

                    token = "";
                }

            } else {

                token += caracter;
            }
        }

        /*
         * Una expresión postfija válida debe terminar
         * dejando exactamente un elemento en la pila.
         */
        if (valores.getTam() != 1) {

            throw new IllegalArgumentException(
                    "La expresión no es válida.");
        }

        return valores.desapilar().getDato();
    }

    /**
     * Ejecuta la operación matemática correspondiente.
     *
     * @param op1 primer operando.
     * @param op2 segundo operando.
     * @param operador operación que se realizará.
     * @return resultado de la operación.
     */
    private static double aplicarOperador(
            double op1,
            double op2,
            char operador) {

        switch (operador) {

            case '+':
                return op1 + op2;

            case '-':
                return op1 - op2;

            case '*':
                return op1 * op2;

            case '/':

                if (op2 == 0) {
                    throw new ArithmeticException(
                            "No se puede dividir entre cero.");
                }

                return op1 / op2;

            default:
                throw new IllegalArgumentException(
                        "Operador no reconocido.");
        }
    }
}

