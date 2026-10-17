package co.edu.uniquindio.poo.model;

public abstract class EquipoTecnologico {
    protected final String codigoRegistro;
    protected final String numeroSerie;
    protected final String marca;
    protected String modelo;
    protected String yearFabricacion;
    protected String estadoFisico;

    public EquipoTecnologico(String codigoRegistro, String numeroSerie, String marca, String modelo, String yearFabricacion, String estadoFisico) {
        this.codigoRegistro = codigoRegistro;
        this.numeroSerie = numeroSerie;
        this.marca = marca;
        this.modelo = modelo;
        this.yearFabricacion = yearFabricacion;
        this.estadoFisico = estadoFisico;
    }

    public String getCodigoRegistro() {
        return codigoRegistro;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getYearFabricacion() {
        return yearFabricacion;
    }

    public void setYearFabricacion(String yearFabricacion) {
        this.yearFabricacion = yearFabricacion;
    }

    public String getEstadoFisico() {
        return estadoFisico;
    }

    public void setEstadoFisico(String estadoFisico) {
        this.estadoFisico = estadoFisico;
    }

    @Override
    public String toString() {
        return "EquipoTecnologico{" +
                "codigoRegistro='" + codigoRegistro + '\'' +
                ", numeroSerie='" + numeroSerie + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", yearFabricacion='" + yearFabricacion + '\'' +
                ", estadoFisico='" + estadoFisico + '\'' +
                '}';
    }
}
