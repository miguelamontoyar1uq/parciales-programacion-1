package co.edu.uniquindio.poo.parcial3.model;

import java.util.ArrayList;
import java.util.List;

public class Panaderia {

    private String nombre;
    private List<Empleado> listaEmpleados;
    private List<Pedido> listaPedidos;

    public Panaderia(String nombre) {
        this.nombre = nombre;
        this.listaEmpleados = new ArrayList<>();
        this.listaPedidos = new ArrayList<>();
    }

    // ---------- CRUD de Empleado ----------

    public void registrarEmpleado(Empleado empleado) {
        if (empleado == null) {
            throw new IllegalArgumentException("El empleado no puede ser nulo");
        }
        if (existeEmpleado(empleado.getCodigo())) {
            throw new IllegalArgumentException("Ya existe un empleado con el código " + empleado.getCodigo());
        }
        listaEmpleados.add(empleado);
    }

    public Empleado buscarEmpleado(String codigo) {
        for (Empleado e : listaEmpleados) {
            if (e.getCodigo().equals(codigo)) {
                return e;
            }
        }
        return null;
    }

    public void actualizarEmpleado(String codigo, String nombre, String documento, int minutosDisponibles) {
        Empleado e = buscarEmpleado(codigo);
        if (e == null) {
            throw new IllegalArgumentException("No existe el empleado " + codigo);
        }
        e.setNombre(nombre);
        e.setDocumento(documento);
        e.setMinutosDisponibles(minutosDisponibles);
    }

    public void eliminarEmpleado(String codigo) {
        Empleado e = buscarEmpleado(codigo);
        if (e == null) {
            throw new IllegalArgumentException("No existe el empleado " + codigo);
        }
        listaEmpleados.remove(e);
    }

    private boolean existeEmpleado(String codigo) {
        return buscarEmpleado(codigo) != null;
    }

    // ---------- Funcionalidades de Pedido ----------

    /** 1. Registrar un pedido: queda PENDIENTE y no reserva minutos. */
    public void registrarPedido(String codigo, String nombreCliente, TipoPan tipoPan, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
        }
        if (tipoPan == null) {
            throw new IllegalArgumentException("El tipo de pan es obligatorio");
        }
        if (existePedido(codigo)) {
            throw new IllegalArgumentException("Ya existe un pedido con el código " + codigo);
        }
        listaPedidos.add(new Pedido(codigo, nombreCliente, tipoPan, cantidad));
    }

    /** 2. Asignar un pedido pendiente al panadero que tarde menos (empate: el primero de la lista). */
    public void asignarPedido(String codigo) {
        Pedido pedido = buscarPedido(codigo); // IllegalArgumentException si no existe
        if (pedido.getEstado() != EstadoPedido.PENDIENTE) {
            throw new IllegalStateException("El pedido " + codigo + " no está pendiente");
        }

        Panadero seleccionado = null;
        int menorTiempo = Integer.MAX_VALUE;

        for (Empleado e : listaEmpleados) {
            if (e instanceof Panadero panadero) {
                int tiempo = panadero.calcularMinutos(pedido.getTipoPan(), pedido.getCantidad());
                if (tiempo <= panadero.getMinutosDisponibles() && tiempo < menorTiempo) {
                    seleccionado = panadero;
                    menorTiempo = tiempo;
                }
            }
        }

        if (seleccionado == null) {
            throw new IllegalStateException("Ningún panadero tiene tiempo suficiente para el pedido " + codigo);
        }

        seleccionado.descontarMinutos(menorTiempo);
        pedido.asignar(seleccionado.getCodigo(), menorTiempo);
    }

    /** 3. Cancelar un pedido según su estado actual. */
    public void cancelarPedido(String codigo) {
        Pedido pedido = buscarPedido(codigo); // IllegalArgumentException si no existe

        switch (pedido.getEstado()) {
            case CANCELADO -> throw new IllegalStateException("El pedido " + codigo + " ya está cancelado");
            case ASIGNADO -> {
                Empleado empleado = buscarEmpleado(pedido.getCodigoEmpleadoAsignado());
                if (empleado != null) {
                    empleado.devolverMinutos(pedido.getMinutosReservados());
                }
                pedido.cancelar();
            }
            case PENDIENTE -> pedido.cancelar();
        }
    }

    public Pedido buscarPedido(String codigo) {
        for (Pedido p : listaPedidos) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        throw new IllegalArgumentException("No existe el pedido " + codigo);
    }

    private boolean existePedido(String codigo) {
        for (Pedido p : listaPedidos) {
            if (p.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    // ---------- Getters ----------

    public String getNombre() {
        return nombre;
    }

    public List<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }
}
