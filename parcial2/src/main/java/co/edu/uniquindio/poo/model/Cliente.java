package co.edu.uniquindio.poo.model;

public class Cliente {
    private final String nombre;
    private final String documento;
    private String telefono;
    private String correo;
    private String direccion;

    public Cliente(String nombre, String documento, String telefono, String correo, String direccion) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", documento='" + documento + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
