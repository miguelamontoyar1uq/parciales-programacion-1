package co.edu.uniquindio.poo.model;

public class MantenimientoPreventivo extends Servicio {
    private static final double TARIFA_PORTATIL=60000, TARIFA_ESCRITORIO=
            70000, TARIFA_CELULAR=40000;

    public MantenimientoPreventivo(String codigo, String nombre, String descripcion, double tarifaBase) {
        super(codigo, nombre, descripcion, tarifaBase);
    }

    @Override
    public double calcularCosto(DetalleServicio detalle) {
        if(detalle.getEquipo() instanceof Portatil){
            return TARIFA_PORTATIL;
        }
        if(detalle.getEquipo() instanceof Escritorio){
            return TARIFA_ESCRITORIO;
        }
        if(detalle.getEquipo() instanceof Telefono){
            return TARIFA_CELULAR;
        }
        return 0;
    }
}



