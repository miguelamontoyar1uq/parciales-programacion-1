package co.edu.uniquindio.poo.model;

public class Portatil extends EquipoTecnologico {
    private String tamanioPantalla;
    private String ram;
    private String almacenamiento;
    private boolean bateriaExtraible;

    public Portatil(String codigoRegistro, String numeroSerie, String marca, String modelo, String anioFabricacion, String estadoFisico, String tamanioPantalla, String ram, String almacenamiento, boolean bateriaExtraible) {
        super(codigoRegistro, numeroSerie, marca, modelo, anioFabricacion, estadoFisico);
        this.tamanioPantalla = tamanioPantalla;
        this.ram = ram;
        this.almacenamiento = almacenamiento;
        this.bateriaExtraible = bateriaExtraible;
    }

    public String getTamanioPantalla() {
        return tamanioPantalla;
    }

    public void setTamanioPantalla(String tamanioPantalla) {
        this.tamanioPantalla = tamanioPantalla;
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

    public boolean isBateriaExtraible() {
        return bateriaExtraible;
    }

    public void setBateriaExtraible(boolean bateriaExtraible) {
        this.bateriaExtraible = bateriaExtraible;
    }

    @Override
    public String toString() {
        return "Portatil{" +
                "tamanioPantalla='" + tamanioPantalla + '\'' +
                ", ram='" + ram + '\'' +
                ", almacenamiento='" + almacenamiento + '\'' +
                ", bateriaExtraible=" + bateriaExtraible +
                '}';
    }
}
