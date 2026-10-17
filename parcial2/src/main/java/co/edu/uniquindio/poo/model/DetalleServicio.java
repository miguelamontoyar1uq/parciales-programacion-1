package co.edu.uniquindio.poo.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DetalleServicio {
    private  final String codigoDetalle;
    private EquipoTecnologico equipo;
    private Servicio servicio;
    private String fallaReportada;
    private Tecnico tecnicoAsignado;
    private int horasTrabajo;
    private String observacionesTecnicas;
    private EstadoDetalle estado;
    private List<DetalleRepuesto> repuestosUtilizados;

    public DetalleServicio(String codigoDetalle, EquipoTecnologico equipo, Servicio servicio, String fallaReportada) {
        this.codigoDetalle = codigoDetalle;
        this.equipo = equipo;
        this.servicio = servicio;
        this.fallaReportada = fallaReportada;
        this.estado = EstadoDetalle.PENDIENTE;
        this.repuestosUtilizados = new ArrayList<>();
        this.horasTrabajo = 0;
        this.observacionesTecnicas = "";
    }

    public String getCodigoDetalle() {
        return codigoDetalle;
    }

    public EquipoTecnologico getEquipo() {
        return equipo;
    }

    public void setEquipo(EquipoTecnologico equipo) {
        this.equipo = equipo;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public String getFallaReportada() {
        return fallaReportada;
    }

    public void setFallaReportada(String fallaReportada) {
        this.fallaReportada = fallaReportada;
    }

    public Tecnico getTecnicoAsignado() {
        return tecnicoAsignado;
    }

    public void setTecnicoAsignado(Tecnico tecnicoAsignado) {
        this.tecnicoAsignado = tecnicoAsignado;
    }

    public String getObservacionesTecnicas() {
        return observacionesTecnicas;
    }

    public void setObservacionesTecnicas(String observacionesTecnicas) {
        this.observacionesTecnicas = observacionesTecnicas;
    }

    public int getHorasTrabajo() {
        return horasTrabajo;
    }

    public void setHorasTrabajo(int horasTrabajo) {
        this.horasTrabajo = horasTrabajo;
    }

    public EstadoDetalle getEstado() {
        return estado;
    }

    public void setEstado(EstadoDetalle estado) {
        this.estado = estado;
    }

    public List<DetalleRepuesto> getRepuestosUtilizados() {
        return Collections.unmodifiableList(repuestosUtilizados);
    }
    public boolean agregarRepuesto(Repuesto repuesto, int cantidad){
        if(!repuesto.descontar(cantidad)){
            return false;
        }
        repuestosUtilizados.add(new DetalleRepuesto(repuesto, cantidad));
        return true;
    }

    @Override
    public String toString() {
        return "DetalleServicio{" +
                "codigoDetalle='" + codigoDetalle + '\'' +
                ", equipo=" + equipo +
                ", servicio=" + servicio +
                ", fallaReportada='" + fallaReportada + '\'' +
                ", tecnicoAsignado=" + tecnicoAsignado +
                ", horasTrabajo=" + horasTrabajo +
                ", observacionesTecnicas='" + observacionesTecnicas + '\'' +
                ", estado=" + estado +
                ", repuestosUtilizados=" + repuestosUtilizados +
                '}';
    }
}