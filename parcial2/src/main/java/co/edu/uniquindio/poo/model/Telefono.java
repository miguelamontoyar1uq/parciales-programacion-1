package co.edu.uniquindio.poo.model;

public class Telefono extends EquipoTecnologico{
    private String sistemaOperativo;
    private String almacenamiento;
    private String capacidadBateria;
    private boolean compatible5G;

    public Telefono(String codigoRegistro, String numeroSerie, String marca, String modelo, String anioFabricacion, String estadoFisico, String sistemaOperativo, String almacenamiento, String capacidadBateria, boolean compatible5G) {
        super(codigoRegistro, numeroSerie, marca, modelo, anioFabricacion, estadoFisico);
        this.sistemaOperativo = sistemaOperativo;
        this.almacenamiento = almacenamiento;
        this.capacidadBateria = capacidadBateria;
        this.compatible5G = compatible5G;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public String getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(String almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public String getCapacidadBateria() {
        return capacidadBateria;
    }

    public void setCapacidadBateria(String capacidadBateria) {
        this.capacidadBateria = capacidadBateria;
    }

    public boolean isCompatible5G() {
        return compatible5G;
    }

    public void setCompatible5G(boolean compatible5G) {
        this.compatible5G = compatible5G;
    }

    @Override
    public String toString() {
        return "Telefono{" +
                "sistemaOperativo='" + sistemaOperativo + '\'' +
                ", almacenamiento='" + almacenamiento + '\'' +
                ", capacidadBateria='" + capacidadBateria + '\'' +
                ", compatible5G=" + compatible5G +
                '}';
    }

}
