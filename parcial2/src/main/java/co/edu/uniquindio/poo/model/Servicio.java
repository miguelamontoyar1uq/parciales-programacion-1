package co.edu.uniquindio.poo.model;

public abstract class Servicio {
    protected String codigo;
    protected String nombre;
    protected String descripcion;
    protected float tarifaBase;

    public Servicio(String codigo, String nombre, String descripcion, float tarifaBase) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tarifaBase = tarifaBase;
    }
    public abstract float calcularCosto();

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

