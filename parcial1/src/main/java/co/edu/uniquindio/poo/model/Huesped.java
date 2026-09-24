package co.edu.uniquindio.poo.model;

import java.util.ArrayList;

public class Huesped {
    private String documento;
    private String nombre;
    private byte edad;
    private String telefono;
    private String ciudad;
    private ArrayList<Reserva> listaReservas;

    public Huesped(String documento, String nombre, byte edad, String telefono, String ciudad) {
        this.documento = documento;
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.listaReservas = new ArrayList<>();
    }

    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte getEdad() {
        return edad;
    }
    public void setEdad(byte edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public ArrayList<Reserva> getListaReservas() {
        return listaReservas;
    }
    public void setListaReservas(ArrayList<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }

    @Override
    public String toString() {
        return "Huesped{" +
                "documento='" + documento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", telefono='" + telefono + '\'' +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }
}
