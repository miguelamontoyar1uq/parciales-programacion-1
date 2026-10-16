package co.edu.uniquindio.poo.model;

public class Repuesto {
    private final  String codigo;
    private String nombre;
    private String descripcion;
    private double precioUnitario;
    private int cantidadDisponible;
    private final String categoriaCompatibilidad;

    public Repuesto(String codigo, String nombre, String descripcion, double precioUnitario, int cantidadDisponible, String categoriaCompatibilidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.cantidadDisponible = cantidadDisponible;
        this.categoriaCompatibilidad = categoriaCompatibilidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getCategoriaCompatibilidad() {
        return categoriaCompatibilidad;
    }

    @Override
    public String toString() {
        return "Repuesto{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precioUnitario=" + precioUnitario +
                ", cantidadDisponible=" + cantidadDisponible +
                ", categoriaCompatibilidad='" + categoriaCompatibilidad + '\'' +
                '}';
    }
}

