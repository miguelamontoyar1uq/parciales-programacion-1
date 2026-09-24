package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Bienvenido al sistema de gestión de StayPlus");

        Habitacion h1 = new Habitacion("101", "Individual", 1, 2, 100000.0, "Disponible");
        Habitacion h2 = new Habitacion("102", "Doble", 1, 3, 180000.0, "Ocupada");
        Habitacion h3 = new Habitacion("201", "Suite", 2, 4, 350000.0, "Mantenimiento");
        Habitacion[] habitaciones = {h1, h2, h3};

        Huesped huesped1 = new Huesped("10941234", "Carlos Pérez", (byte) 30, "3101234567", "Armenia");

        Reserva r1 = new Reserva("1221", "22/09/2026", 2, 2, "Confirmada", "Tarjeta", huesped1);
        r1.agregarHabitacion(h1);

        Reserva r2 = new Reserva("4567", "22/09/2026", 1, 1, "Confirmada", "Efectivo", huesped1);
        r2.agregarHabitacion(h2);

        Reserva[] reservas = {r1, r2};
        huesped1.getListaReservas().add(r1);
        huesped1.getListaReservas().add(r2);

        char[][] matrizOcupacion = {
                {'O', 'O', 'D', 'D', 'O', 'O', 'D'},
                {'D', 'O', 'O', 'O', 'O', 'O', 'D'},
                {'D', 'D', 'D', 'D', 'D', 'D', 'D'}
        };

        Hotel hotel = new Hotel("StayPlus", "900123456-1", "Calle 10 # 15-20", "6067400000", habitaciones, reservas, matrizOcupacion);
        hotel.registrarHuesped(huesped1);

        int opcion;
        do {
            opcion = Integer.valueOf(JOptionPane.showInputDialog(null, "Por favor, seleccione una opción: ---Menu--- \n" +
                    "1. Consultar huésped por teléfono.\n" +
                    "2. Control de disponibilidad de habitaciones.\n" +
                    "3. Matriz de ocupación del hotel.\n" +
                    "4. Consultar número especial de reserva (Capicúa).\n" +
                    "5. Consultar ingresos del hotel por fecha.\n" +
                    "0. Salir."));

            switch (opcion) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Gracias por usar nuestro sistema.");
                    break;
                case 1:
                    String tel = JOptionPane.showInputDialog(null, "Ingrese el número de teléfono:");
                    String resHuesped = hotel.consultarHuesped(tel);
                    JOptionPane.showMessageDialog(null, resHuesped);
                    break;
                case 2:
                    String resDispo = hotel.reportarDisponibilidad();
                    JOptionPane.showMessageDialog(null, resDispo);
                    break;
                case 3:
                    String resMatriz = hotel.reportarOcupacionSemanal();
                    JOptionPane.showMessageDialog(null, resMatriz);
                    break;
                case 4:
                    ArrayList<Reserva> especiales = hotel.obtenerReservasEspeciales();
                    String msjEspeciales = "Reservas Especiales (Capicúa):\n";
                    for (Reserva aux : especiales) {
                        msjEspeciales += "Código: " + aux.getCodigoReserva() + " - Huésped: " + aux.getHuesped().getNombre() + "\n";
                    }
                    JOptionPane.showMessageDialog(null, msjEspeciales);
                    break;
                case 5:
                    String fecha = JOptionPane.showInputDialog(null, "Ingrese la fecha a consultar (DD/MM/AAAA):");
                    double total = hotel.calcularIngresosFecha(fecha);
                    JOptionPane.showMessageDialog(null, "Total ingresos para el " + fecha + ": $" + total);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
                    break;
            }
        } while (opcion != 0);
    }
}