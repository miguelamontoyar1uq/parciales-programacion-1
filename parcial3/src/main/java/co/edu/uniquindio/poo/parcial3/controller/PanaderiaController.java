package co.edu.uniquindio.poo.parcial3.controller;

import co.edu.uniquindio.poo.parcial3.model.*;

public class PanaderiaController {

    private Panaderia panaderia;

    public PanaderiaController() {
        this.panaderia = new Panaderia("El Trigal");
        inicializarDatosPrueba();
    }

    // Datos quemados iniciales
    private void inicializarDatosPrueba() {
        panaderia.registrarEmpleado(new PanaderoArtesanal("E01", 100, "Ana", "111"));
        panaderia.registrarEmpleado(new PanaderoIndustrial("E02", 45, "Carlos", "222"));
    }
    // mano aca no toca usar boolean no ?digo por lo que veo de las
    // excepciones es mejor que quede asi pero por si acaso mirelo

    public void registrarPedido(String codigo, String nombreCliente, TipoPan tipoPan, int cantidad) {
        panaderia.registrarPedido(codigo, nombreCliente, tipoPan, cantidad);
    }

    public void asignarPedido(String codigo) {
        panaderia.asignarPedido(codigo);
    }

    public void cancelarPedido(String codigo) {
        panaderia.cancelarPedido(codigo);
    }
}