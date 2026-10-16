package co.edu.uniquindio.poo.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Taller {
    private final String nombre;
    private final String direccion;
    private final String nit;
    private final String telefono;
    private ArrayList<Cliente> listaClientes;
    private ArrayList <Servicio> listaServicios;
    private ArrayList <OrdenServicio> listaOrdenesServicio;
    private ArrayList <Tecnico> listaTecnicos;
    private ArrayList <Repuesto> listaRepuestos;

    public Taller(String nombre, String direccion, String nit, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.nit = nit;
        this.telefono = telefono;
        listaClientes = new ArrayList<>();
        listaOrdenesServicio=new ArrayList<>();
        listaServicios=new ArrayList<>();
        listaTecnicos=new ArrayList<>();
        listaRepuestos=new ArrayList<>();

    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNit() {
        return nit;
    }

    public String getTelefono() {
        return telefono;
    }

    public List<Cliente> getListaClientes() {
        return Collections.unmodifiableList(listaClientes);
    }


    public List<Servicio> getListaServicios() {
        return Collections.unmodifiableList(listaServicios);
    }


    public List<OrdenServicio> getListaOrdenesServicio() {
        return Collections.unmodifiableList(listaOrdenesServicio);
    }



    public List<Tecnico> getListaTecnicos() {
        return Collections.unmodifiableList(listaTecnicos);
    }

    public List<Repuesto> getListaRepuestos() {
        return Collections.unmodifiableList(listaRepuestos);
    }


    @Override
    public String toString() {
        return "Taller{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", nit='" + nit + '\'' +
                ", telefono='" + telefono + '\'' +
                ", listaClientes=" + listaClientes +
                ", listaServicios=" + listaServicios +
                ", listaOrdenesServicio=" + listaOrdenesServicio +
                ", listaTecnicos=" + listaTecnicos +
                ", listaRepuestos=" + listaRepuestos +
                '}';
    }
}
