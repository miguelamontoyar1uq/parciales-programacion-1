package co.edu.uniquindio.poo.model;

public abstract class Servicio {
    protected String codigo;
    protected String nombre;
    protected String descripcion;
    protected double tarifaBase;

    public Servicio(String codigo, String nombre, String descripcion,
                    double tarifaBase) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tarifaBase = tarifaBase;
    }
    public abstract double calcularCosto();

    @Override
    public String toString() {
        return "Servicios{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tarifaBase=" + tarifaBase +
                '}';
    }
}

