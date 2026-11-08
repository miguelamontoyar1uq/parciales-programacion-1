package co.edu.uniquindio.poo.parcial3.controller;

import co.edu.uniquindio.poo.parcial3.model.Panaderia;
import co.edu.uniquindio.poo.parcial3.model.Pedido;
import co.edu.uniquindio.poo.parcial3.model.TipoPan;

import java.util.List;

public class PedidosController {

    private Panaderia panaderia;

    public PedidosController(Panaderia panaderia) {
        this.panaderia = panaderia;
    }

    public List<TipoPan> obtenerTiposPan() {
        return List.of(TipoPan.values());
    }

    public List<Pedido> obtenerPedidos() {
        return panaderia.getListaPedidos();
    }

    public Pedido buscarPedido(String codigo) {
        return panaderia.buscarPedido(codigo);
    }

    public void registrarPedido(String codigo, String nombreCliente, TipoPan tipoPan, int cantidad) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código es obligatorio");
        }
        if (nombreCliente == null || nombreCliente.isBlank()) {
            throw new IllegalArgumentException("El nombre del cliente es obligatorio");
        }
        panaderia.registrarPedido(codigo.trim(), nombreCliente.trim(), tipoPan, cantidad);
    }

    public void asignarPedido(String codigo) {
        panaderia.asignarPedido(codigo);
    }

    public void cancelarPedido(String codigo) {
        panaderia.cancelarPedido(codigo);
    }
}
