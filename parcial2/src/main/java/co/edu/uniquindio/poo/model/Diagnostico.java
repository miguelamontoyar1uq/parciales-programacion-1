package co.edu.uniquindio.poo.model;

public class Diagnostico extends Servicio {
    private static final double COSTO_FIJO=35000;

    public Diagnostico(String codigo, String nombre, String descripcion, double tarifaBase) {
        super(codigo, nombre, descripcion, tarifaBase);
    }

    @Override
    public double calcularCosto() {
        return 0;
    }
}
