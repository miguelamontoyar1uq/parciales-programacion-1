package co.edu.uniquindio.poo.model;

import java.util.ArrayList;

public class Hotel {
    private String nombreComercial;
    private String nit;
    private String direccion;
    private String telefono;

    private ArrayList<Huesped> listaHuespedes;
    private Habitacion[] listaHabitaciones;
    private Reserva[] listaReservas;
    private char[][] matrizOcupacion;

    public Hotel(String nombreComercial, String nit, String direccion, String telefono,
                 Habitacion[] listaHabitaciones, Reserva[] listaReservas, char[][] matrizOcupacion) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.listaHuespedes = new ArrayList<>();
        this.listaHabitaciones = listaHabitaciones;
        this.listaReservas = listaReservas;
        this.matrizOcupacion = matrizOcupacion;
    }

    public void registrarHuesped(Huesped huesped) {
        listaHuespedes.add(huesped);
    }

    public String consultarHuesped(String telefono) {
        for (Huesped aux : listaHuespedes) {
            if (aux.getTelefono().equals(telefono)) {
                String msj = "Huésped Encontrado:\n";
                msj += "Nombre: " + aux.getNombre() + "\n";
                msj += "Documento: " + aux.getDocumento() + "\n";
                msj += "Ciudad: " + aux.getCiudad() + "\n";
                msj += "Reservas realizadas:\n";
                for (Reserva r : aux.getListaReservas()) {
                    msj += " - Código: " + r.getCodigoReserva() + " | Fecha: " + r.getFechaReserva() + "\n";
                }
                return msj;
            }
        }
        return "El huésped con teléfono "+telefono+" no se encuentra registrado.";
    }

    public String reportarDisponibilidad() {
        int disponibles = 0;
        int ocupadas = 0;
        int mantenimiento = 0;

        Habitacion mayorPrecio = null;
        Habitacion menorPrecio = null;

        for (Habitacion aux : listaHabitaciones) {
            if (aux != null) {
                if (aux.getEstado().equalsIgnoreCase("Disponible")) {
                    disponibles++;
                } else if (aux.getEstado().equalsIgnoreCase("Ocupada")) {
                    ocupadas++;
                } else if (aux.getEstado().equalsIgnoreCase("Mantenimiento")) {
                    mantenimiento++;
                }

                if (mayorPrecio == null || aux.getPrecioNoche() > mayorPrecio.getPrecioNoche()) {
                    mayorPrecio = aux;
                }
                if (menorPrecio == null || aux.getPrecioNoche() < menorPrecio.getPrecioNoche()) {
                    menorPrecio = aux;
                }
            }
        }

        String reporte = "Control de Disponibilidad:\n";
        reporte += "Disponibles: " + disponibles + "\n";
        reporte += "Ocupadas: " + ocupadas + "\n";
        reporte += "En Mantenimiento: " + mantenimiento + "\n";
        reporte += "Habitación mayor precio: " + (mayorPrecio != null ? mayorPrecio.getNumero() + " ($" + mayorPrecio.getPrecioNoche() + ")" : "N/A") + "\n";
        reporte += "Habitación menor precio: " + (menorPrecio != null ? menorPrecio.getNumero() + " ($" + menorPrecio.getPrecioNoche() + ")" : "N/A");

        return reporte;
    }

    public String reportarOcupacionSemanal() {
        if (matrizOcupacion == null) return "Matriz no definida.";

        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        int numDias = matrizOcupacion[0].length;
        int[] ocupacionDia = new int[numDias];
        int totalSemana = 0;

        for (int i = 0; i < matrizOcupacion.length; i++) {
            for (int j = 0; j < numDias; j++) {
                if (matrizOcupacion[i][j] == 'O' || matrizOcupacion[i][j] == 'o') {
                    ocupacionDia[j]++;
                    totalSemana++;
                }
            }
        }

        int maxOcup = -1;
        int minOcup = 9999;
        int posMax = 0;
        int posMin = 0;

        for (int j = 0; j < numDias; j++) {
            if (ocupacionDia[j] > maxOcup) {
                maxOcup = ocupacionDia[j];
                posMax = j;
            }
            if (ocupacionDia[j] < minOcup) {
                minOcup = ocupacionDia[j];
                posMin = j;
            }
        }

        String resultado = "Matriz de Ocupación Semanal:\n";
        resultado += "Día mayor ocupación: " + dias[posMax] + " (" + maxOcup + " habitaciones)\n";
        resultado += "Día menor ocupación: " + dias[posMin] + " (" + minOcup + " habitaciones)\n";
        resultado += "Total ocupadas en la semana: " + totalSemana;

        return resultado;
    }

    public ArrayList<Reserva> obtenerReservasEspeciales() {
        ArrayList<Reserva> especiales = new ArrayList<>();
        for (Reserva aux : listaReservas) {
            if (aux != null && aux.esCapicua()) {
                especiales.add(aux);
            }
        }
        return especiales;
    }

    public double calcularIngresosFecha(String fecha) {
        double acum = 0;
        for (Reserva aux : listaReservas) {
            if (aux != null && aux.getFechaReserva().equals(fecha)) {
                acum += aux.getValorTotal();
            }
        }
        return acum;
    }
}