package co.edu.uniquindio.poo.parcial3.model;

public class PanaderoArtesanal extends Panadero {

    private static final int PANES_POR_BANDEJA = 12;
    private static final int MINUTOS_TRADICIONAL = 30;
    private static final int MINUTOS_INTEGRAL = 40;

    public PanaderoArtesanal(String codigo, int minutosDisponibles, String nombre, String documento) {
        super(codigo, minutosDisponibles, nombre, documento);
    }

    @Override
    public int calcularMinutos(TipoPan tipoPan, int cantidad) {
        int bandejas = (cantidad + PANES_POR_BANDEJA - 1) / PANES_POR_BANDEJA;
        int minutosPorBandeja = (tipoPan == TipoPan.TRADICIONAL) ? MINUTOS_TRADICIONAL : MINUTOS_INTEGRAL;
        return bandejas * minutosPorBandeja;
    }
}
