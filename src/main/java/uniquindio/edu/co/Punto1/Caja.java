package uniquindio.edu.co.Punto1;

/**
 * Representa una caja almacenada en el depósito.
 *
 * Cada caja tiene un código identificador
 * y un valor numérico entero.
 */
public class Caja {

    private String codigo;
    private int valor;

    /**
     * Constructor de la caja.
     *
     * @param codigo código identificador.
     * @param valor valor numérico de la caja.
     */
    public Caja(String codigo, int valor) {

        this.codigo = codigo;
        this.valor = valor;
    }

    /**
     * Obtiene el código de la caja.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Obtiene el valor de la caja.
     */
    public int getValor() {
        return valor;
    }

    /**
     * Representación textual de la caja.
     */
    @Override
    public String toString() {

        return codigo + ":" + valor;
    }
}

