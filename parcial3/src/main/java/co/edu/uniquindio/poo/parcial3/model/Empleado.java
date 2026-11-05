package co.edu.uniquindio.poo.parcial3.model;

public abstract class Empleado {

    private String codigo;
    private int minutosDisponibles;
    private String nombre;
    private String documento;

    public Empleado(String codigo, int minutosDisponibles, String nombre, String documento) {
        this.codigo = codigo;
        this.minutosDisponibles = minutosDisponibles;
        this.nombre = nombre;
        this.documento = documento;
    }

    public void descontarMinutos(int minutos) {
        this.minutosDisponibles -= minutos;
    }

    public void devolverMinutos(int minutos) {
        this.minutosDisponibles += minutos;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getMinutosDisponibles() {
        return minutosDisponibles;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setMinutosDisponibles(int minutosDisponibles) {
        this.minutosDisponibles = minutosDisponibles;
    }
}
