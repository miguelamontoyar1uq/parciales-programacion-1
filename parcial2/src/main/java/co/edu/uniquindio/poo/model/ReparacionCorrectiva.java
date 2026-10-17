package co.edu.uniquindio.poo.model;

public class ReparacionCorrectiva extends Servicio {
    public static final double MANO_DE_OBRA=45000;

    public ReparacionCorrectiva(String codigo, String nombre, String descripcion, double tarifaBase) {
        super(codigo, nombre, descripcion, tarifaBase);
    }

    @Override
    public double calcularCosto(DetalleServicio detalle) {
        double costoManoObra = MANO_DE_OBRA * detalle.getHorasTrabajo();
        double costoRepuestos = 0;
        for(DetalleRepuesto repuesto : detalle.getRepuestosUtilizados()){
            costoRepuestos += repuesto.calcularSubtotal();
        }
        return costoManoObra + costoRepuestos;
    }
}

