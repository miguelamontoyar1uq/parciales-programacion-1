package co.edu.uniquindio.poo.model;

public class Tecnico {
    private final  String codigo;
    private String nombre;
    private final String documentoIdentidad;
    private EspecialidadTecnico especialidadTecnico;
    private  EstadoLaboralTecnico estadoLaboralTecnico;

    public Tecnico(String codigo, String documentoIdentidad, String nombre, EspecialidadTecnico especialidadTecnico, EstadoLaboralTecnico estadoLaboralTecnico) {
        this.codigo = codigo;
        this.documentoIdentidad = documentoIdentidad;
        this.nombre = nombre;
        this.especialidadTecnico = especialidadTecnico;
        this.estadoLaboralTecnico = estadoLaboralTecnico;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public EspecialidadTecnico getEspecialidadTecnico() {
        return especialidadTecnico;
    }

    public void setEspecialidadTecnico(EspecialidadTecnico especialidadTecnico) {
        this.especialidadTecnico = especialidadTecnico;
    }

    public EstadoLaboralTecnico getEstadoLaboralTecnico() {
        return estadoLaboralTecnico;
    }

    public void setEstadoLaboralTecnico(EstadoLaboralTecnico estadoLaboralTecnico) {
        this.estadoLaboralTecnico = estadoLaboralTecnico;
    }
    public boolean estaActivo(){
        return estadoLaboralTecnico == EstadoLaboralTecnico.ACTIVO;
    }

    public boolean puedeAtender(EquipoTecnologico equipo){
        if(especialidadTecnico == EspecialidadTecnico.SOPORTE_GENERAL){
            return true;
        }
        if(especialidadTecnico == EspecialidadTecnico.COMPUTADORES){
            return equipo instanceof Portatil || equipo instanceof Escritorio;
        }
        if(especialidadTecnico == EspecialidadTecnico.DISPOSITIVOS_MOVILES){
            return equipo instanceof Telefono;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Tecnico{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", documentoIdentidad='" + documentoIdentidad + '\'' +
                ", especialidadTecnico=" + especialidadTecnico +
                ", estadoLaboralTecnico=" + estadoLaboralTecnico +
                '}';
    }
}
