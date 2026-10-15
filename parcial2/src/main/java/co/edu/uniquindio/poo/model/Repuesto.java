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

    public String getCodigo() { return codigo; }
    public double getPrecioUnitario() { return precioUnitario; }
    public int getCantidadDisponible() { return cantidadDisponible; }

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

