package uniquindio.edu.co.PuntoIII;

import java.util.Objects;

/**
 * Paquete pendiente de entrega.
 * Tiene un codigo unico, un tipo de entrega y un peso entero positivo en kg.
 */
public class Paquete {

    private final String codigo;
    private final TipoEntrega tipo;
    private final int peso;

    public Paquete(String codigo, TipoEntrega tipo, int peso) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo no puede ser nulo ni vacio.");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de entrega no puede ser nulo.");
        }
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser un entero positivo.");
        }
        this.codigo = codigo.trim();
        this.tipo = tipo;
        this.peso = peso;
    }

    public String getCodigo() {
        return codigo;
    }

    public TipoEntrega getTipo() {
        return tipo;
    }

    public int getPeso() {
        return peso;
    }

    public boolean esUrgente() {
        return tipo == TipoEntrega.URGENTE;
    }

    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof Paquete)) {
            return false;
        }
        Paquete paquete = (Paquete) otro;
        return codigo.equals(paquete.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return codigo + "(" + tipo + ", " + peso + " kg)";
    }
}
