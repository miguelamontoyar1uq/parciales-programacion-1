package co.edu.uniquindio.poo.model;

import java.util.ArrayList;

public class Reserva {
    private String codigoReserva;
    private String fechaReserva;
    private int numeroNoches;
    private int cantidadHuespedes;
    private String estadoReserva; // pendiente, confirmada o finalizada
    private String metodoPago; // efectivo, tarjeta o transferencia bancaria
    private double valorTotal;
    private Huesped huesped;
    private ArrayList<Habitacion> listaHabitaciones;

    public Reserva(String codigoReserva, String fechaReserva, int numeroNoches, int cantidadHuespedes,
                   String estadoReserva, String metodoPago, Huesped huesped) {
        this.codigoReserva = codigoReserva;
        this.fechaReserva = fechaReserva;
        this.numeroNoches = numeroNoches;
        this.cantidadHuespedes = cantidadHuespedes;
        this.estadoReserva = estadoReserva;
        this.metodoPago = metodoPago;
        this.huesped = huesped;
        this.listaHabitaciones = new ArrayList<>();
        this.valorTotal = 0;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public int getNumeroNoches() {
        return numeroNoches;
    }

    public String getEstadoReserva() {
        return estadoReserva;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public ArrayList<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }

    public void agregarHabitacion(Habitacion habitacion) {
        listaHabitaciones.add(habitacion);
        if (estadoReserva.equalsIgnoreCase("Confirmada")) {
            habitacion.setEstado("Reservada");
        }
        calcularValorTotal();
    }

    public void calcularValorTotal() {
        double sumaPrecioNoches = 0;
        for (Habitacion aux : listaHabitaciones) {
            sumaPrecioNoches += aux.getPrecioNoche();
        }
        this.valorTotal = sumaPrecioNoches * numeroNoches * listaHabitaciones.size();
    }

    public boolean esCapicua() {
        if (codigoReserva == null){
            return false;
        }
        String invertida = "";
        for (int i = codigoReserva.length() - 1; i >= 0; i--) {
            invertida+= codigoReserva.charAt(i);
        }
        return codigoReserva.equals(invertida);
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "codigo='" + codigoReserva + '\'' +
                ", fecha='" + fechaReserva + '\'' +
                ", valorTotal=" + valorTotal +
                '}';
    }

}
