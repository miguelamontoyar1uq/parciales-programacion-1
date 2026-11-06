package co.edu.uniquindio.poo.parcial3.model;

public class Cajero extends Empleado {

    private String cajaAsignada;

    public Cajero(String codigo, int minutosDisponibles, String nombre, String documento, String cajaAsignada) {
        super(codigo, minutosDisponibles, nombre, documento);
        this.cajaAsignada = cajaAsignada;
    }

    public String getCajaAsignada() {
        return cajaAsignada;
    }

    public void setCajaAsignada(String cajaAsignada) {
        this.cajaAsignada = cajaAsignada;
    }
}
