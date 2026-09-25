package co.edu.uniquindio.poo.model;

/**
 * Clase Habitacion para el sistema de gestión
 * @author : Miguel Angel Montoya - Andrés Felipe Barrios
 * @Version : 1.1
 * @Fecha : 24/09/26
 */

public class Habitacion {
    private String numero;
    private String tipo; // individual, doble o suite
    private int piso;
    private int capacidadMax;
    private double precioNoche;
    private String estado; // disponible, reservada, ocupada o mantenimiento

    public Habitacion(String numero, String tipo, int piso, int capacidadMax, double precioNoche, String estado) {
        this.numero = numero;
        this.tipo = tipo;
        this.piso = piso;
        this.capacidadMax = capacidadMax;
        this.precioNoche = precioNoche;
        this.estado = estado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "numero='" + numero + '\'' +
                ", tipo='" + tipo + '\'' +
                ", piso=" + piso +
                ", capacidadMax=" + capacidadMax +
                ", precioNoche=" + precioNoche +
                ", estado='" + estado + '\'' +
                '}';
    }
}