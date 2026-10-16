package co.edu.uniquindio.poo.model;

public class Escritorio extends EquipoTecnologico {
    private String procesador;
    private String ram;
    private String almacenamiento;
    private boolean graficaDedicada;

    public Escritorio(String codigoRegistro, String numeroSerie, String marca, String modelo, String anioFabricacion, String estadoFisico, String procesador, String ram, String almacenamiento, boolean graficaDedicada) {
        super(codigoRegistro, numeroSerie, marca, modelo, anioFabricacion, estadoFisico);
        this.procesador = procesador;
        this.ram = ram;
        this.almacenamiento = almacenamiento;
        this.graficaDedicada = graficaDedicada;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(String almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public boolean isGraficaDedicada() {
        return graficaDedicada;
    }

    public void setGraficaDedicada(boolean graficaDedicada) {
        this.graficaDedicada = graficaDedicada;
    }

    @Override
    public String toString() {
        return "Escritorio{" +
                "estadoFisico='" + estadoFisico + '\'' +
                ", procesador='" + procesador + '\'' +
                ", ram='" + ram + '\'' +
                ", almacenamiento='" + almacenamiento + '\'' +
                ", graficaDedicada=" + graficaDedicada +
                ", codigoRegistro='" + codigoRegistro + '\'' +
                ", marca='" + marca + '\'' +
                ", numeroSerie='" + numeroSerie + '\'' +
                ", modelo='" + modelo + '\'' +
                ", anioFabricacion='" + anioFabricacion + '\'' +
                '}';
    }
}

