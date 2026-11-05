package co.edu.uniquindio.poo.parcial3.model;

public class PanaderoIndustrial extends Panadero {

    private static final int PANES_POR_LOTE = 30;
    private static final int MINUTOS_PREPARACION = 20;
    private static final int MINUTOS_TRADICIONAL = 25;
    private static final int MINUTOS_INTEGRAL = 35;

    public PanaderoIndustrial(String codigo, int minutosDisponibles, String nombre, String documento) {
        super(codigo, minutosDisponibles, nombre, documento);
    }

    @Override
    public int calcularMinutos(TipoPan tipoPan, int cantidad) {
        int lotes = (cantidad + PANES_POR_LOTE - 1) / PANES_POR_LOTE;
        int minutosPorLote = (tipoPan == TipoPan.TRADICIONAL) ? MINUTOS_TRADICIONAL : MINUTOS_INTEGRAL;
        return MINUTOS_PREPARACION + lotes * minutosPorLote;
    }
}
