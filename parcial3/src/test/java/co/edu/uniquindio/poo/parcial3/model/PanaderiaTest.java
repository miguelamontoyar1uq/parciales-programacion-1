package co.edu.uniquindio.poo.parcial3.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase para probar los registros, asignaciones y cancelaciones relacionados con la panadería
 * @author Miguel Angel Montoya
 * @author Andrés Felipe Barrios
 * @fecha: 6-11-2026
 *
 * Licencia GNU/GPL V3.0
 */

class PanaderiaTest {

    private Panaderia panaderia;

    @BeforeEach
    void preparar() {
        panaderia = new Panaderia("El Trigal");
    }

    // REGISTRAR
    @Test
    void registrarPedidoValido() {
        Panadero ana = new PanaderoArtesanal("E1", 100, "Ana", "111");
        panaderia.registrarEmpleado(ana);

        panaderia.registrarPedido("P01", "Café Central", TipoPan.TRADICIONAL, 25);

        Pedido pedido = panaderia.buscarPedido("P01");
        assertEquals(EstadoPedido.PENDIENTE, pedido.getEstado());
        assertEquals(25, pedido.getCantidad());
        assertEquals(100, ana.getMinutosDisponibles());
    }

    @Test
    void registrarPedidoConCodigoDuplicadoDeUnPedidoCancelado() {
        panaderia.registrarPedido("P01", "Café Central", TipoPan.TRADICIONAL, 25);
        panaderia.cancelarPedido("P01");

        assertThrows(IllegalArgumentException.class,
                () -> panaderia.registrarPedido("P01", "Otro Cliente", TipoPan.INTEGRAL, 10));

        assertEquals(1, panaderia.getListaPedidos().size());
        assertEquals(EstadoPedido.CANCELADO, panaderia.buscarPedido("P01").getEstado());
    }

    @Test
    void registrarPedidoConCantidadCero() {
        assertThrows(IllegalArgumentException.class,
                () -> panaderia.registrarPedido("P01", "Café Central", TipoPan.TRADICIONAL, 0));

        assertTrue(panaderia.getListaPedidos().isEmpty());
    }

    // ASIGNAR
    @Test
    void asignarPedidoAlPanaderoQueTardaMenos() {
        Panadero ana = new PanaderoArtesanal("E1", 100, "Ana", "111");
        Panadero carlos = new PanaderoIndustrial("E2", 45, "Carlos", "222");
        panaderia.registrarEmpleado(ana);
        panaderia.registrarEmpleado(carlos);
        panaderia.registrarPedido("P01", "Café Central", TipoPan.TRADICIONAL, 25);

        assertEquals(90, ana.calcularMinutos(TipoPan.TRADICIONAL, 25));
        assertEquals(45, carlos.calcularMinutos(TipoPan.TRADICIONAL, 25));

        panaderia.asignarPedido("P01");

        Pedido pedido = panaderia.buscarPedido("P01");
        assertEquals(EstadoPedido.ASIGNADO, pedido.getEstado());
        assertEquals("E2", pedido.getCodigoEmpleadoAsignado());
        assertEquals(45, pedido.getMinutosReservados());
        assertEquals(0, carlos.getMinutosDisponibles());
        assertEquals(100, ana.getMinutosDisponibles());
    }

    @Test
    void asignarPedidoEnEmpateSeleccionaAlPrimeroDeLaLista() {
        Panadero ana = new PanaderoArtesanal("E1", 80, "Ana", "111");
        Panadero diana = new PanaderoArtesanal("E2", 80, "Diana", "222");
        Panadero carlos = new PanaderoIndustrial("E3", 54, "Carlos", "333");
        panaderia.registrarEmpleado(ana);
        panaderia.registrarEmpleado(diana);
        panaderia.registrarEmpleado(carlos);
        panaderia.registrarPedido("P01", "Restaurante Sol", TipoPan.INTEGRAL, 13);

        assertEquals(55, carlos.calcularMinutos(TipoPan.INTEGRAL, 13));
        assertEquals(80, ana.calcularMinutos(TipoPan.INTEGRAL, 13));
        assertEquals(80, diana.calcularMinutos(TipoPan.INTEGRAL, 13));

        panaderia.asignarPedido("P01");

        Pedido pedido = panaderia.buscarPedido("P01");
        assertEquals("E1", pedido.getCodigoEmpleadoAsignado());
        assertEquals(80, pedido.getMinutosReservados());
        assertEquals(0, ana.getMinutosDisponibles());
        assertEquals(80, diana.getMinutosDisponibles());
        assertEquals(54, carlos.getMinutosDisponibles());
    }

    @Test
    void asignarPedidoSinTiempoSuficienteLanzaExcepcion() {
        Panadero carlos = new PanaderoIndustrial("E1", 44, "Carlos", "111");
        Cajero lucia = new Cajero("E2", 120, "Lucía", "222", "Caja 1");
        panaderia.registrarEmpleado(carlos);
        panaderia.registrarEmpleado(lucia);
        panaderia.registrarPedido("P01", "Café Central", TipoPan.TRADICIONAL, 25);

        assertThrows(IllegalStateException.class, () -> panaderia.asignarPedido("P01"));

        assertEquals(EstadoPedido.PENDIENTE, panaderia.buscarPedido("P01").getEstado());
        assertEquals(44, carlos.getMinutosDisponibles());
        assertEquals(120, lucia.getMinutosDisponibles());
    }

    // CANCELAR

    @Test
    void cancelarPedidoAsignadoDevuelveSoloLosMinutosDeEsePedido() {
        Panadero carlos = new PanaderoIndustrial("E1", 120, "Carlos", "111");
        panaderia.registrarEmpleado(carlos);
        panaderia.registrarPedido("P01", "Café Central", TipoPan.TRADICIONAL, 25);
        panaderia.registrarPedido("P02", "Restaurante Sol", TipoPan.TRADICIONAL, 25);
        panaderia.asignarPedido("P01");
        panaderia.asignarPedido("P02");

        assertEquals(45, panaderia.buscarPedido("P01").getMinutosReservados());
        assertEquals(45, panaderia.buscarPedido("P02").getMinutosReservados());
        assertEquals(30, carlos.getMinutosDisponibles());

        panaderia.cancelarPedido("P01");

        assertEquals(75, carlos.getMinutosDisponibles());
        assertEquals(EstadoPedido.CANCELADO, panaderia.buscarPedido("P01").getEstado());
        assertEquals(EstadoPedido.ASIGNADO, panaderia.buscarPedido("P02").getEstado());
    }

    @Test
    void cancelarPedidoPendienteConservaLosMinutos() {
        Panadero ana = new PanaderoArtesanal("E1", 100, "Ana", "111");
        panaderia.registrarEmpleado(ana);
        panaderia.registrarPedido("P01", "Café Central", TipoPan.TRADICIONAL, 25);

        panaderia.cancelarPedido("P01");

        assertEquals(EstadoPedido.CANCELADO, panaderia.buscarPedido("P01").getEstado());
        assertEquals(1, panaderia.getListaPedidos().size());
        assertEquals(100, ana.getMinutosDisponibles());
    }

    @Test
    void cancelarPedidoYaCanceladoLanzaExcepcionYDevuelveMinutosUnaSolaVez() {
        Panadero carlos = new PanaderoIndustrial("E1", 100, "Carlos", "111");
        panaderia.registrarEmpleado(carlos);
        panaderia.registrarPedido("P01", "Café Central", TipoPan.TRADICIONAL, 25);
        panaderia.asignarPedido("P01");
        assertEquals(55, carlos.getMinutosDisponibles());

        panaderia.cancelarPedido("P01");
        assertEquals(100, carlos.getMinutosDisponibles());

        assertThrows(IllegalStateException.class, () -> panaderia.cancelarPedido("P01"));

        assertEquals(100, carlos.getMinutosDisponibles());
        assertEquals(EstadoPedido.CANCELADO, panaderia.buscarPedido("P01").getEstado());
    }
}