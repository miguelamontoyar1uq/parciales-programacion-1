package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 * Sistema de gestión para el hotel StayPlus
 * @author : Miguel Angel Montoya - Andrés Felipe Barrios
 * @Version : 1.1
 * @Fecha : 24/09/26
 */

public class Main {

    public static void main(String[] args) {

        JOptionPane.showMessageDialog(
                null,
                "Bienvenido al sistema de gestión de StayPlus"
        );

        // Habitaciones iniciales
        Habitacion h1 = new Habitacion(
                "101",
                "Individual",
                1,
                2,
                100000.0,
                "Disponible"
        );

        Habitacion h2 = new Habitacion(
                "102",
                "Doble",
                1,
                3,
                180000.0,
                "Ocupada"
        );

        Habitacion h3 = new Habitacion(
                "201",
                "Suite",
                2,
                4,
                350000.0,
                "Mantenimiento"
        );

        Habitacion[] habitaciones = {h1, h2, h3};

        // Huésped inicial
        Huesped huesped1 = new Huesped(
                "10941234",
                "Carlos Pérez",
                (byte) 30,
                "3101234567",
                "Armenia"
        );

        // Reservas iniciales
        Reserva r1 = new Reserva(
                "1221",
                "22/09/2026",
                2,
                2,
                "Confirmada",
                "Tarjeta",
                huesped1
        );

        r1.agregarHabitacion(h1);

        Reserva r2 = new Reserva(
                "4567",
                "22/09/2026",
                1,
                1,
                "Confirmada",
                "Efectivo",
                huesped1
        );

        r2.agregarHabitacion(h2);

        Reserva[] reservas = {r2, null};

        huesped1.getListaReservas().add(r1);
        huesped1.getListaReservas().add(r2);

        // Matriz de ocupación inicial
        char[][] matrizOcupacion = {
                {'O', 'O', 'D', 'D', 'O', 'O', 'D'},
                {'D', 'O', 'O', 'O', 'O', 'O', 'D'},
                {'D', 'D', 'D', 'D', 'D', 'D', 'D'}
        };

        Hotel hotel = new Hotel(
                "StayPlus",
                "900123456-1",
                "Calle 10 # 15-20",
                "6067400000",
                habitaciones,
                reservas,
                matrizOcupacion
        );

        hotel.crearHuesped(huesped1);
        hotel.crearReserva(r1);

        int opcion;

        do {
            opcion = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            null,
                            "Por favor, seleccione una opción:\n\n" +
                                    "1. Registrar huésped\n" +
                                    "2. Buscar huésped\n" +
                                    "3. Actualizar huésped\n" +
                                    "4. Eliminar huésped\n\n" +
                                    "5. Registrar reserva\n" +
                                    "6. Buscar reserva\n" +
                                    "7. Actualizar reserva\n" +
                                    "8. Eliminar reserva\n\n" +
                                    "9. Control de disponibilidad de habitaciones\n" +
                                    "10. Matriz de ocupación del hotel\n" +
                                    "11. Consultar reservas especiales (Capicúa)\n" +
                                    "12. Consultar ingresos por fecha\n\n" +
                                    "0. Salir"
                    )
            );

            switch (opcion) {

                case 1:
                    crearHuesped(hotel);
                    break;

                case 2:
                    consultarHuesped(hotel);
                    break;

                case 3:
                    actualizarHuesped(hotel);
                    break;

                case 4:
                    eliminarHuesped(hotel);
                    break;

                case 5:
                    crearReserva(hotel);
                    break;

                case 6:
                    consultarReserva(hotel);
                    break;

                case 7:
                    actualizarReserva(hotel);
                    break;

                case 8:
                    eliminarReserva(hotel);
                    break;

                case 9:
                    String disponibilidad = hotel.reportarDisponibilidad();
                    JOptionPane.showMessageDialog(null, disponibilidad);
                    break;

                case 10:
                    String matriz = hotel.reportarOcupacionSemanal();
                    JOptionPane.showMessageDialog(null, matriz);
                    break;

                case 11:
                    ArrayList<Reserva> especiales = hotel.obtenerReservasEspeciales();
                    String mensajeEspeciales = "Reservas especiales (Capicúa):\n\n";

                    for (Reserva aux : especiales) {
                        mensajeEspeciales += "Código: " + aux.getCodigoReserva() + " - Huésped: " + aux.getHuesped().getNombre() + "\n";
                    }

                    JOptionPane.showMessageDialog(null, mensajeEspeciales);
                    break;

                case 12:
                    String fecha = JOptionPane.showInputDialog(null, "Ingrese la fecha a consultar (DD/MM/AAAA):");
                    double total = hotel.calcularIngresosFecha(fecha);
                    JOptionPane.showMessageDialog(null, "Total de ingresos para el " + fecha + ": $" + total);
                    break;

                case 0:
                    JOptionPane.showMessageDialog(null, "Gracias por usar nuestro sistema.");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
                    break;
            }

        } while (opcion != 0);
    }

    // Registrar huésped
    private static void crearHuesped(Hotel hotel) {
        String documento = JOptionPane.showInputDialog(null, "Ingrese el documento del huésped:");
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del huésped:");
        byte edad = Byte.parseByte(JOptionPane.showInputDialog(null, "Ingrese la edad del huésped:"));
        String telefono = JOptionPane.showInputDialog(null, "Ingrese el teléfono del huésped:");
        String ciudad = JOptionPane.showInputDialog(null, "Ingrese la ciudad del huésped:");

        Huesped huesped = new Huesped(documento, nombre, edad, telefono, ciudad);
        hotel.crearHuesped(huesped);

        JOptionPane.showMessageDialog(null, "Huésped registrado correctamente.");
    }

    // Buscar huésped
    private static void consultarHuesped(Hotel hotel) {
        String telefono = JOptionPane.showInputDialog(null, "Ingrese el teléfono del huésped:");
        String resultado = hotel.consultarHuesped(telefono);
        JOptionPane.showMessageDialog(null, resultado);
    }

    // Actualizar huésped
    private static void actualizarHuesped(Hotel hotel) {
        String telefonoAntiguo = JOptionPane.showInputDialog(null, "Ingrese el teléfono actual del huésped:");
        String documentoNuevo = JOptionPane.showInputDialog(null, "Ingrese el nuevo documento:");
        String nombreNuevo = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre:");
        byte edadNueva = Byte.parseByte(JOptionPane.showInputDialog(null, "Ingrese la nueva edad:"));
        String telefonoNuevo = JOptionPane.showInputDialog(null, "Ingrese el nuevo teléfono:");
        String ciudadNueva = JOptionPane.showInputDialog(null, "Ingrese la nueva ciudad:");

        boolean actualizado = hotel.actualizarHuesped(telefonoAntiguo, documentoNuevo, nombreNuevo, edadNueva, telefonoNuevo, ciudadNueva);

        if (actualizado) {
            JOptionPane.showMessageDialog(null, "Huésped actualizado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el huésped.");
        }
    }

    // Eliminar huésped
    private static void eliminarHuesped(Hotel hotel) {
        String telefono = JOptionPane.showInputDialog(null, "Ingrese el teléfono del huésped que desea eliminar:");
        boolean eliminado = hotel.eliminarHuesped(telefono);

        if (eliminado) {
            JOptionPane.showMessageDialog(null, "Huésped eliminado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el huésped.");
        }
    }

    // Registrar reserva
    private static void crearReserva(Hotel hotel) {
        String codigo = JOptionPane.showInputDialog(null, "Ingrese el código de la reserva:");
        String fecha = JOptionPane.showInputDialog(null, "Ingrese la fecha de la reserva:");
        int numeroNoches = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de noches:"));
        int cantidadHuespedes = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de huéspedes:"));
        String estado = JOptionPane.showInputDialog(null, "Ingrese el estado de la reserva (Pendiente, Confirmada, Finalizada):");
        String metodoPago = JOptionPane.showInputDialog(null, "Ingrese el método de pago:");
        String telefonoHuesped = JOptionPane.showInputDialog(null, "Ingrese el teléfono del huésped:");

        Huesped huesped = hotel.buscarHuesped(telefonoHuesped);

        if (huesped == null) {
            JOptionPane.showMessageDialog(null, "El huésped no se encuentra registrado.");
            return;
        }

        Reserva reserva = new Reserva(codigo, fecha, numeroNoches, cantidadHuespedes, estado, metodoPago, huesped);
        boolean creada = hotel.crearReserva(reserva);

        if (creada) {
            JOptionPane.showMessageDialog(null, "Reserva registrada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo registrar la reserva (código duplicado o espacio lleno).");
        }
    }

    // Buscar reserva
    private static void consultarReserva(Hotel hotel) {
        String codigo = JOptionPane.showInputDialog(null, "Ingrese el código de la reserva:");
        String resultado = hotel.consultarReserva(codigo);
        JOptionPane.showMessageDialog(null, resultado);
    }

    // Actualizar reserva
    private static void actualizarReserva(Hotel hotel) {
        String codigoAntiguo = JOptionPane.showInputDialog(null, "Ingrese el código actual de la reserva:");
        String fechaNueva = JOptionPane.showInputDialog(null, "Ingrese la nueva fecha:");
        int nochesNuevas = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el nuevo número de noches:"));
        int cantidadNueva = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la nueva cantidad de huéspedes:"));
        String estadoNuevo = JOptionPane.showInputDialog(null, "Ingrese el nuevo estado:");
        String metodoPagoNuevo = JOptionPane.showInputDialog(null, "Ingrese el nuevo método de pago:");

        boolean actualizado = hotel.actualizarReserva(codigoAntiguo, fechaNueva, nochesNuevas, cantidadNueva, estadoNuevo, metodoPagoNuevo);

        if (actualizado) {
            JOptionPane.showMessageDialog(null, "Reserva actualizada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró la reserva.");
        }
    }

    // Eliminar reserva
    private static void eliminarReserva(Hotel hotel) {
        String codigo = JOptionPane.showInputDialog(null, "Ingrese el código de la reserva que desea eliminar:");
        boolean eliminado = hotel.eliminarReserva(codigo);

        if (eliminado) {
            JOptionPane.showMessageDialog(null, "Reserva eliminada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró la reserva.");
        }
    }
}