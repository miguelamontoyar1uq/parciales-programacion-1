package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.model.DetalleRepuesto;
import co.edu.uniquindio.poo.model.EstadoDetalle;
import co.edu.uniquindio.poo.model.Servicio;
import co.edu.uniquindio.poo.model.Tecnico;

import java.util.ArrayList;
import java.util.List;

public class DetalleServicio {
    private String codigoDetalle;
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


}