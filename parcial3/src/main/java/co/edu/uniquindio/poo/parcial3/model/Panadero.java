package co.edu.uniquindio.poo.parcial3.model;

public abstract class Panadero extends Empleado {

    public Panadero(String codigo, int minutosDisponibles, String nombre, String documento) {
        super(codigo, minutosDisponibles, nombre, documento);
    }

    public abstract int calcularMinutos(TipoPan tipoPan, int cantidad);
}
