package co.edu.uniquindio.poo.parcial3.controller;

import co.edu.uniquindio.poo.parcial3.model.*;

import java.util.List;

public class EmpleadosController {

    public static final String PANADERO_ARTESANAL = "Panadero Artesanal";
    public static final String PANADERO_INDUSTRIAL = "Panadero Industrial";
    public static final String CAJERO = "Cajero";

    private Panaderia panaderia;

    public EmpleadosController(Panaderia panaderia) {
        this.panaderia = panaderia;
    }

    public List<String> obtenerTipos() {
        return List.of(PANADERO_ARTESANAL, PANADERO_INDUSTRIAL, CAJERO);
    }

    public List<Empleado> obtenerEmpleados() {
        return panaderia.getListaEmpleados();
    }

    public void agregarEmpleado(String tipo, String codigo, String nombre, String documento,
                                int minutosDisponibles, String cajaAsignada) {
        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException("Seleccione el tipo de empleado");
        }
        validarDatos(codigo, nombre, documento, minutosDisponibles);

        codigo = codigo.trim();
        nombre = nombre.trim();
        documento = documento.trim();

        Empleado empleado;
        switch (tipo) {
            case PANADERO_ARTESANAL -> empleado = new PanaderoArtesanal(codigo, minutosDisponibles, nombre, documento);
            case PANADERO_INDUSTRIAL -> empleado = new PanaderoIndustrial(codigo, minutosDisponibles, nombre, documento);
            case CAJERO -> {
                if (cajaAsignada == null || cajaAsignada.isBlank()) {
                    throw new IllegalArgumentException("La caja asignada es obligatoria para un cajero");
                }
                empleado = new Cajero(codigo, minutosDisponibles, nombre, documento, cajaAsignada.trim());
            }
            default -> throw new IllegalArgumentException("Tipo de empleado no válido");
        }
        panaderia.registrarEmpleado(empleado);
    }

    public void actualizarEmpleado(String codigo, String nombre, String documento, int minutosDisponibles) {
        validarDatos(codigo, nombre, documento, minutosDisponibles);
        panaderia.actualizarEmpleado(codigo.trim(), nombre.trim(), documento.trim(), minutosDisponibles);
    }

    public void eliminarEmpleado(String codigo) {
        panaderia.eliminarEmpleado(codigo);
    }

    /** Nombre legible del tipo de empleado (para mostrarlo en la tabla). */
    public String obtenerTipo(Empleado empleado) {
        if (empleado instanceof PanaderoArtesanal) {
            return PANADERO_ARTESANAL;
        }
        if (empleado instanceof PanaderoIndustrial) {
            return PANADERO_INDUSTRIAL;
        }
        if (empleado instanceof Cajero) {
            return CAJERO;
        }
        return "Empleado";
    }

    /** Caja asignada si es cajero; cadena vacía en cualquier otro caso. */
    public String obtenerCaja(Empleado empleado) {
        if (empleado instanceof Cajero cajero) {
            return cajero.getCajaAsignada();
        }
        return "";
    }

    private void validarDatos(String codigo, String nombre, String documento, int minutosDisponibles) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código es obligatorio");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (documento == null || documento.isBlank()) {
            throw new IllegalArgumentException("El documento es obligatorio");
        }
        if (minutosDisponibles < 0) {
            throw new IllegalArgumentException("Los minutos disponibles no pueden ser negativos");
        }
    }
}
