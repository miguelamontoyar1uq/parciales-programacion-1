package co.edu.uniquindio.poo.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    //Crud Cliente
    //Crear
    public boolean crearCliente(Cliente cliente) {
        Optional<Cliente> buscado=buscarCliente(cliente.getDocumento());
        if(buscado.isPresent()){
            return false;
        }

            listaClientes.add(cliente);
            return true;

    }


    //buscar
    public Optional<Cliente> buscarCliente(String documento){
        return listaClientes.stream().filter(cliente -> cliente.getDocumento().equals(documento)).findFirst();
    }
    //Crud Tecnico
    //Crear
    public boolean crearTecnico(Tecnico tecnico){
        Optional<Tecnico> buscado = buscarTecnico(tecnico.getCodigo());
        if(buscado.isPresent()){
            return false;
        }
        listaTecnicos.add(tecnico);
        return true;
    }

    //Buscar
    public Optional<Tecnico> buscarTecnico (String codigo){
        return listaTecnicos.stream().filter(tecnico -> tecnico.getCodigo().equals(codigo)).findFirst();
    }
//Crud repuesto
    //Crear
    public boolean crearRepuesto(Repuesto repuesto){
        if(repuesto.getCantidadDisponible()<0 || repuesto.getPrecioUnitario()<=0){
            return false;
        }
        Optional<Repuesto> buscado= buscarRepuesto(repuesto.getCodigo());
        if(buscado.isPresent()) {
            return false;
        }
        listaRepuestos.add(repuesto);
        return true;


    }


    //buscar
    public Optional<Repuesto> buscarRepuesto(String codigo){
        return listaRepuestos.stream().filter(repuesto -> repuesto.getCodigo().equals(codigo)).findFirst();
    }
    //Crud equipo
    public Optional<EquipoTecnologico> buscarEquipo (String codigoRegistro) {
        return listaClientes.stream().flatMap(cliente -> cliente.getListaEquipos().stream()).filter(equipo-> equipo.getCodigoRegistro().equals(codigoRegistro)).findFirst();

        // mano hay use esa mierda de flat map. ya que pues cada cliente tiene
        // una lista de equipos asi que eso extrae la lista de cada cliente y
        // las une en solo un flujo para comparar de una
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
