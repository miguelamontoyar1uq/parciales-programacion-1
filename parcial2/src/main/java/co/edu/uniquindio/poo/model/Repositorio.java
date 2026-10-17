package co.edu.uniquindio.poo.model;

import javax.swing.JOptionPane;

/*
Repositorio para la reutilización de funciones usando JOptionPane
*/

public class Repositorio {

    // Función para ingresar una cadena de texto
    public static String ingresarTexto(String mensaje) {
        return JOptionPane.showInputDialog(null, mensaje);
    }

    // Función para ingresar un número entero
    public static int ingresarEntero(String mensaje) {
        String entrada = JOptionPane.showInputDialog(null, mensaje);
        return Integer.parseInt(entrada);
    }

    // Función para ingresar un número decimal (double)
    public static double ingresarNumeroDecimal(String mensaje) {
        String entrada = JOptionPane.showInputDialog(null, mensaje);
        return Double.parseDouble(entrada);
    }

    // Función para ingresar un valor booleano mediante botones (Sí / No)
    public static boolean ingresarValorBooleano(String mensaje, String title) {
        int opcion = JOptionPane.showConfirmDialog(
                null,
                mensaje, title,
                JOptionPane.YES_NO_OPTION
        );
        return opcion == JOptionPane.YES_OPTION;
    }

    // Función para ingresar un byte
    public static byte ingresarByte(String mensaje) {
        String entrada = JOptionPane.showInputDialog(null, mensaje);
        return Byte.parseByte(entrada);
    }

    // Función para ingresar un float
    public static float ingresarFloat(String mensaje) {
        String entrada = JOptionPane.showInputDialog(null, mensaje);
        return Float.parseFloat(entrada);
    }

    // Función auxilar para mostrar mensajes de alerta o información al usuario
    public static void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}