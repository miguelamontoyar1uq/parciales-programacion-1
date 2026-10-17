package co.edu.uniquindio.poo.model;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OrdenServicio {
    private final String codigoOrden;
    private final LocalDate fechaCreacion;
    private  final Cliente cliente;
    private String observacionesGenerales;
    private EstadoOrden estadoOrden;
    private List<DetalleServicio> listaDetalles;

    public OrdenServicio(String codigoOrden, LocalDate fechaCreacion,
                         Cliente cliente, String observacionesGenerales, EstadoOrden estadoOrden) {
        this.codigoOrden = codigoOrden;
        this.fechaCreacion = fechaCreacion;
        this.cliente = cliente;
        this.observacionesGenerales = observacionesGenerales;
        this.estadoOrden = estadoOrden;
        listaDetalles= new ArrayList<>();
    }

    public String getCodigoOrden() {
        return codigoOrden;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getObservacionesGenerales() {
        return observacionesGenerales;
    }

    public void setObservacionesGenerales(String observacionesGenerales) {
        this.observacionesGenerales = observacionesGenerales;
    }

    public EstadoOrden getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(EstadoOrden estadoOrden) {
        this.estadoOrden = estadoOrden;
    }

    public List<DetalleServicio> getListaDetalles() {
        return Collections.unmodifiableList(listaDetalles);
    }
    public boolean agregarDetalle(DetalleServicio detalleServicio){
        Optional<DetalleServicio> buscado = buscarDetalle(detalleServicio.getCodigoDetalle());
        if(buscado.isPresent()){
            return false;
        }
        listaDetalles.add(detalleServicio);
        return true;
    }

    //Buscar
    public Optional<DetalleServicio> buscarDetalle(String codigoDetalle){
        return listaDetalles.stream().filter(detalle -> detalle.getCodigoDetalle().equals(codigoDetalle)).findFirst();
    }
    public boolean finalizar(){
        if(listaDetalles.isEmpty() || !listaDetalles.stream().allMatch(detalle -> detalle.getEstado() == EstadoDetalle.FINALIZADA)){
            return false;
        }
        estadoOrden = EstadoOrden.FINALIZADA;
        return true;
    }
    public double calcularValorTotal() {
        double total = 0;
        for (DetalleServicio detalle : listaDetalles) {
            total += detalle.calcularValor();
        }
        return total;
    }


    @Override
    public String toString() {
        return "OrdenServicio{" +
                "codigoOrden='" + codigoOrden + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", cliente=" + cliente +
                ", observacionesGenerales='" + observacionesGenerales + '\'' +
                ", estadoOrden=" + estadoOrden +
                ", listaDetalles=" + listaDetalles +
                '}';
    }
}



