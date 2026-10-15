package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.EstadoOrden;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdenServicio {
    private String codigoOrden;
    private Date fechaCreacion;
    private Cliente cliente;
    private String observacionesGenerales;
    private EstadoOrden estadoOrden;
    private List<DetalleServicio> listaDetalles;

    public OrdenServicio(String codigoOrden, Date fechaCreacion, Cliente cliente, String observacionesGenerales, EstadoOrden estadoOrden) {
        this.codigoOrden = codigoOrden;
        this.fechaCreacion = fechaCreacion;
        this.cliente = cliente;
        this.observacionesGenerales = observacionesGenerales;
        this.estadoOrden = estadoOrden;
        listaDetalles= new ArrayList<>();
    }
}



