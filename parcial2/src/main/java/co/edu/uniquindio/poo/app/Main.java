package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Taller taller = new Taller("TechRepair", "carrera 25 calle 22 detras del burdel", "231", "3507823456");

        // Clientes iniciales
        taller.crearCliente(new Cliente("Carlos Pérez", "1001", "3101234567", "carlos@gmail.com", "Calle 10 #12-34"));
        taller.crearCliente(new Cliente("María Rodríguez", "1002", "3209876543", "maria@gmail.com", "Carrera 15 #45-67"));
        taller.crearCliente(new Cliente("Juan Gómez", "1003", "3005554433", "juan@gmail.com", "Avenida 19 #8-90"));

        // Técnicos iniciales
        taller.crearTecnico(new Tecnico("TEC-001", "7001", "Ana Martínez", EspecialidadTecnico.COMPUTADORES, EstadoLaboralTecnico.ACTIVO));
        taller.crearTecnico(new Tecnico("TEC-002", "7002", "Luis Hernández", EspecialidadTecnico.DISPOSITIVOS_MOVILES, EstadoLaboralTecnico.ACTIVO));
        taller.crearTecnico(new Tecnico("TEC-003", "7003", "Pedro Sánchez", EspecialidadTecnico.SOPORTE_GENERAL, EstadoLaboralTecnico.ACTIVO));

        // Repuestos iniciales
        taller.crearRepuesto(new Repuesto("REP-001", "Pantalla LCD 15.6", "Pantalla de repuesto para portátil", 250000.0, 10, "COMPUTADORES"));
        taller.crearRepuesto(new Repuesto("REP-002", "Batería iPhone 11", "Batería Li-Ion 3110 mAh", 120000.0, 3, "DISPOSITIVOS_MOVILES"));
        taller.crearRepuesto(new Repuesto("REP-003", "Disco SSD 500GB", "Unidad de estado sólido SATA", 180000.0, 15, "COMPUTADORES"));

        // Equipos iniciales
        taller.crearEquipo("1001", new Portatil("EQ-001", "SN-PORT-01", "Lenovo", "ThinkPad T14", "2022", "Bueno", "14.0", "16GB", "512GB SSD", false));
        taller.crearEquipo("1002", new Escritorio("EQ-002", "SN-ESC-01", "HP", "Pavilion Gaming", "2021", "Regular", "Intel i7", "32GB", "1TB SSD", true));
        taller.crearEquipo("1003", new Telefono("EQ-003", "SN-TEL-01", "Samsung", "Galaxy S21", "2023", "Pantalla fisurada", "Android 13", "128GB", "4000 mAh", true));

        // Servicios iniciales
        taller.crearServicio(new Diagnostico("SRV-001", "Diagnóstico", "Identificación de fallas del equipo", 0));
        taller.crearServicio(new MantenimientoPreventivo("SRV-002", "Mantenimiento preventivo", "Limpieza, revisión y optimización", 0));
        taller.crearServicio(new ReparacionCorrectiva("SRV-003", "Reparación correctiva", "Solución de fallas identificadas", 0));

        // Órdenes iniciales
        taller.crearOrden("ORD-001", "1001", "Revisión urgente de encendido");
        taller.agregarDetalleAOrden("ORD-001", "DET-001", "EQ-001", "SRV-001", "El portátil no enciende tras descarga");
        taller.asignarTecnico("ORD-001", "DET-001", "TEC-001");
        taller.registrarHorasDetalle("ORD-001", "DET-001", 2);
        taller.actualizarEstadoDetalle("ORD-001", "DET-001", EstadoDetalle.FINALIZADA);

        taller.crearOrden("ORD-002", "1002", "Mantenimiento general programado");
        taller.agregarDetalleAOrden("ORD-002", "DET-002", "EQ-002", "SRV-002", "Limpieza de ventiladores y cambio de pasta térmica");
        taller.asignarTecnico("ORD-002", "DET-002", "TEC-003");
        taller.registrarHorasDetalle("ORD-002", "DET-002", 3);
        taller.actualizarEstadoDetalle("ORD-002", "DET-002", EstadoDetalle.EN_PROCESO);

        taller.crearOrden("ORD-003", "1003", "Cambio de batería por degradación");
        taller.agregarDetalleAOrden("ORD-003", "DET-003", "EQ-003", "SRV-003", "Batería hinchada y corta duración");
        taller.asignarTecnico("ORD-003", "DET-003", "TEC-002");
        taller.registrarHorasDetalle("ORD-003", "DET-003", 4);
        taller.registrarRepuestoUtilizado("ORD-003", "DET-003", "REP-002", 1);
        taller.actualizarEstadoDetalle("ORD-003", "DET-003", EstadoDetalle.FINALIZADA);

        int opcion;
        do {
            opcion = Repositorio.ingresarEntero("MENÚ TECHREPAIR\n" +
                    "1. Registrar cliente\n" +
                    "2. Registrar equipo\n" +
                    "3. Registrar técnico\n" +
                    "4. Registrar repuesto\n" +
                    "5. Crear orden de servicio\n" +
                    "6. Agregar detalle a una orden\n" +
                    "7. Asignar técnico\n" +
                    "8. Registrar repuestos utilizados\n" +
                    "9. Ver valor de una orden\n" +
                    "10. Registrar horas de un detalle\n" +
                    "11. Actualizar estado de un detalle\n" +
                    "12. Finalizar una orden\n" +
                    "13. Consultar órdenes de un cliente\n" +
                    "14. Consultar trabajos de un técnico\n" +
                    "15. Consultar equipos pendientes\n" +
                    "16. Reporte de servicios\n" +
                    "17. Reporte de técnicos\n" +
                    "18. Inventario crítico\n" +
                    "19. Registrar servicio\n" +
                    "0. Salir");
            switch (opcion) {

                case 1:
                    crearCliente(taller);
                    break;
                case 2:
                    crearEquipo(taller);
                    break;
                case 3:
                    crearTecnico(taller);
                    break;
                case 4:
                    registarRepuesto(taller);
                    break;
                case 5:
                    crearOrden(taller);
                    break;
                case 6:
                    agregarDetalle(taller);
                    break;
                case 7:
                    asignarTecnico(taller);
                    break;
                case 8:
                    registrarRepuestoUtilizado(taller);
                    break;
                case 9:
                    verValorOrden(taller);
                    break;
                case 10:
                    registrarHoras(taller);
                    break;
                case 11:
                    actualizarEstadoDetalle(taller);
                    break;
                case 12:
                    finalizarOrden(taller);
                    break;
                case 13:
                    consultarOrdenesCliente(taller);
                    break;
                case 14:
                    consultarTrabajosTecnico(taller);
                    break;
                case 15:
                    consultarEquiposPendientes(taller);
                    break;
                case 16:
                    reporteServicios(taller);
                    break;
                case 17:
                    reporteTecnicos(taller);
                    break;
                case 18:
                    consultarInventarioCritico(taller);
                    break;
                case 19:
                    crearServicio(taller);
                    break;
            }

        } while (opcion != 0);
    }

    private static void crearCliente(Taller taller) {
        String nombre = Repositorio.ingresarTexto("Por favor ingrese el nombre del cliente");
        String documento = Repositorio.ingresarTexto("Por favor ingrese el documento del cliente");
        String telefono = Repositorio.ingresarTexto("Por favor ingrese el teléfono del cliente");
        String direccion = Repositorio.ingresarTexto("Por favor ingrese la dirección del cliente");
        String correo = Repositorio.ingresarTexto("Por favor ingrese el correo electrónico del cliente");

        Cliente cliente = new Cliente(nombre, documento, telefono, correo, direccion);
        if (taller.crearCliente(cliente)) {
            Repositorio.mostrarMensaje("Cliente registrado correctamente");
        } else {
            Repositorio.mostrarMensaje("Ya existe un cliente con ese documento");
        }
    }

    private static void crearTecnico(Taller taller) {
        String nombre = Repositorio.ingresarTexto("Por favor ingrese el nombre del técnico a registrar");
        String codigo = Repositorio.ingresarTexto("Por favor ingrese el código a asignar al técnico");
        String documentoIdentidad = Repositorio.ingresarTexto("Por favor ingrese el documento del técnico");
        String opcion = Repositorio.ingresarTexto("Seleccione la especialidad:\n1. Computadores\n2. Dispositivos móviles\n3. Soporte general");
        EspecialidadTecnico especialidad;
        switch (opcion) {
            case "1":
                especialidad = EspecialidadTecnico.COMPUTADORES;
                break;
            case "2":
                especialidad = EspecialidadTecnico.DISPOSITIVOS_MOVILES;
                break;
            case "3":
                especialidad = EspecialidadTecnico.SOPORTE_GENERAL;
                break;
            default:
                Repositorio.mostrarMensaje("Opción no válida");
                return;
        }

        Tecnico tecnico = new Tecnico(codigo, documentoIdentidad, nombre, especialidad, EstadoLaboralTecnico.ACTIVO);
        if (taller.crearTecnico(tecnico)) {
            Repositorio.mostrarMensaje("Técnico registrado correctamente");
        } else {
            Repositorio.mostrarMensaje("Ya existe un técnico con ese código");
        }
    }

    private static void registarRepuesto(Taller taller) {
        String nombre = Repositorio.ingresarTexto("Por favor ingrese el nombre del repuesto");
        String descripcion = Repositorio.ingresarTexto("Por favor ingrese la descripción del repuesto");
        String codigo = Repositorio.ingresarTexto("Por favor ingrese el código del repuesto");
        String categoriaCompatibilidad = Repositorio.ingresarTexto("Por favor ingrese la categoría de compatibilidad");
        double precio = Repositorio.ingresarNumeroDecimal("Por favor ingrese el valor unitario del repuesto");
        int cantidad = Repositorio.ingresarEntero("Por favor ingrese la cantidad de repuestos");

        Repuesto repuesto = new Repuesto(codigo, nombre, descripcion, precio, cantidad, categoriaCompatibilidad);
        if (taller.crearRepuesto(repuesto)) {
            Repositorio.mostrarMensaje("Repuesto registrado correctamente");
        } else {
            Repositorio.mostrarMensaje("No se pudo registrar: el código ya existe, o el precio o la cantidad no son válidos");
        }
    }

    private static void crearEquipo(Taller taller) {
        String documento = Repositorio.ingresarTexto("Documento del cliente dueño del equipo");
        String codigoRegistro = Repositorio.ingresarTexto("Código de registro del equipo");
        String numeroSerie = Repositorio.ingresarTexto("Número de serie");
        String marca = Repositorio.ingresarTexto("Marca");
        String modelo = Repositorio.ingresarTexto("Modelo");
        String year = Repositorio.ingresarTexto("Año de fabricación");
        String estadoFisico = Repositorio.ingresarTexto("Estado físico del equipo");

        String opcion = Repositorio.ingresarTexto("Seleccione la categoría:\n1. Portátil\n2. Escritorio\n3. Teléfono");
        EquipoTecnologico equipo;
        switch (opcion) {
            case "1":
                String pantalla = Repositorio.ingresarTexto("Tamaño de pantalla en pulgadas");
                String ramPortatil = Repositorio.ingresarTexto("Memoria RAM");
                String almacenamientoPortatil = Repositorio.ingresarTexto("Capacidad de almacenamiento");
                boolean bateria = Repositorio.ingresarValorBooleano("¿Tiene batería extraíble?", "Batería");
                equipo = new Portatil(codigoRegistro, numeroSerie, marca, modelo, year, estadoFisico, pantalla, ramPortatil, almacenamientoPortatil, bateria);
                break;
            case "2":
                String procesador = Repositorio.ingresarTexto("Tipo de procesador");
                String ramEscritorio = Repositorio.ingresarTexto("Memoria RAM");
                String almacenamientoEscritorio = Repositorio.ingresarTexto("Capacidad de almacenamiento");
                boolean grafica = Repositorio.ingresarValorBooleano("¿Tiene tarjeta gráfica dedicada?", "Gráfica");
                equipo = new Escritorio(codigoRegistro, numeroSerie, marca, modelo, year, estadoFisico, procesador, ramEscritorio, almacenamientoEscritorio, grafica);
                break;
            case "3":
                String sistema = Repositorio.ingresarTexto("Sistema operativo");
                String almacenamientoTelefono = Repositorio.ingresarTexto("Capacidad de almacenamiento");
                String capacidadBateria = Repositorio.ingresarTexto("Capacidad de la batería en mAh");
                boolean cincoG = Repositorio.ingresarValorBooleano("¿Es compatible con 5G?", "5G");
                equipo = new Telefono(codigoRegistro, numeroSerie, marca, modelo, year, estadoFisico, sistema, almacenamientoTelefono, capacidadBateria, cincoG);
                break;
            default:
                Repositorio.mostrarMensaje("Opción no válida");
                return;
        }

        if (taller.crearEquipo(documento, equipo)) {
            Repositorio.mostrarMensaje("Equipo registrado correctamente");
        } else {
            Repositorio.mostrarMensaje("No se pudo registrar: el cliente no existe o el código de registro ya está usado");
        }
    }

    private static void crearOrden(Taller taller) {
        String codigoOrden = Repositorio.ingresarTexto("Código de la orden");
        String documento = Repositorio.ingresarTexto("Documento del cliente");
        String observaciones = Repositorio.ingresarTexto("Observaciones generales");

        if (taller.crearOrden(codigoOrden, documento, observaciones)) {
            Repositorio.mostrarMensaje("Orden creada correctamente");
        } else {
            Repositorio.mostrarMensaje("No se pudo crear: el cliente no existe o el código de la orden ya está usado");
        }
    }

    private static void agregarDetalle(Taller taller) {
        String codigoOrden = Repositorio.ingresarTexto("Código de la orden");
        String codigoDetalle = Repositorio.ingresarTexto("Código del detalle");
        String codigoEquipo = Repositorio.ingresarTexto("Código de registro del equipo");
        String codigoServicio = Repositorio.ingresarTexto("Código del servicio:\nSRV-001 Diagnóstico\nSRV-002 Mantenimiento preventivo\nSRV-003 Reparación correctiva");
        String falla = Repositorio.ingresarTexto("Falla reportada");

        if (taller.agregarDetalleAOrden(codigoOrden, codigoDetalle, codigoEquipo, codigoServicio, falla)) {
            Repositorio.mostrarMensaje("Detalle agregado correctamente");
        } else {
            Repositorio.mostrarMensaje("No se pudo agregar: revise la orden, el equipo, el servicio o que el código del detalle no esté repetido");
        }
    }

    private static void asignarTecnico(Taller taller) {
        String codigoOrden = Repositorio.ingresarTexto("Código de la orden");
        String codigoDetalle = Repositorio.ingresarTexto("Código del detalle");
        String codigoTecnico = Repositorio.ingresarTexto("Código del técnico");

        if (taller.asignarTecnico(codigoOrden, codigoDetalle, codigoTecnico)) {
            Repositorio.mostrarMensaje("Técnico asignado correctamente");
        } else {
            Repositorio.mostrarMensaje("No se pudo asignar: revise la orden, el detalle y el técnico, que debe estar activo y autorizado para ese equipo");
        }
    }

    private static void registrarRepuestoUtilizado(Taller taller) {
        String codigoOrden = Repositorio.ingresarTexto("Código de la orden");
        String codigoDetalle = Repositorio.ingresarTexto("Código del detalle");
        String codigoRepuesto = Repositorio.ingresarTexto("Código del repuesto");
        int cantidad = Repositorio.ingresarEntero("Cantidad a usar");

        if (taller.registrarRepuestoUtilizado(codigoOrden, codigoDetalle, codigoRepuesto, cantidad)) {
            Repositorio.mostrarMensaje("Repuesto registrado correctamente");
        } else {
            Repositorio.mostrarMensaje("No se pudo registrar: el detalle debe ser una reparación correctiva y debe haber existencias suficientes");
        }
    }

    private static void registrarHoras(Taller taller) {
        String codigoOrden = Repositorio.ingresarTexto("Código de la orden");
        String codigoDetalle = Repositorio.ingresarTexto("Código del detalle");
        int horas = Repositorio.ingresarEntero("Horas de trabajo");

        if (taller.registrarHorasDetalle(codigoOrden, codigoDetalle, horas)) {
            Repositorio.mostrarMensaje("Horas registradas correctamente");
        } else {
            Repositorio.mostrarMensaje("No se pudo registrar: revise la orden, el detalle y que las horas sean mayores que 0");
        }
    }

    private static void verValorOrden(Taller taller) {
        String codigoOrden = Repositorio.ingresarTexto("Código de la orden");
        Optional<OrdenServicio> orden = taller.buscarOrden(codigoOrden);
        if (orden.isEmpty()) {
            Repositorio.mostrarMensaje("La orden no existe");
            return;
        }
        String texto = "Orden " + codigoOrden + "\n";
        for (DetalleServicio detalle : orden.get().getListaDetalles()) {
            texto += detalle.getCodigoDetalle() + " - " + detalle.getServicio().getNombre() + ": $" + detalle.calcularValor() + "\n";
        }
        texto += "TOTAL: $" + orden.get().calcularValorTotal();
        Repositorio.mostrarMensaje(texto);
    }

    private static void actualizarEstadoDetalle(Taller taller) {
        String codigoOrden = Repositorio.ingresarTexto("Código de la orden");
        String codigoDetalle = Repositorio.ingresarTexto("Código del detalle");
        String opcion = Repositorio.ingresarTexto("Seleccione el nuevo estado:\n1. Pendiente\n2. En proceso\n3. Finalizada");
        EstadoDetalle nuevoEstado;
        switch (opcion) {
            case "1":
                nuevoEstado = EstadoDetalle.PENDIENTE;
                break;
            case "2":
                nuevoEstado = EstadoDetalle.EN_PROCESO;
                break;
            case "3":
                nuevoEstado = EstadoDetalle.FINALIZADA;
                break;
            default:
                Repositorio.mostrarMensaje("Opción no válida");
                return;
        }

        if (taller.actualizarEstadoDetalle(codigoOrden, codigoDetalle, nuevoEstado)) {
            Repositorio.mostrarMensaje("Estado actualizado correctamente");
        } else {
            Repositorio.mostrarMensaje("No se pudo actualizar: revise la orden, el detalle o que el cambio de estado sea permitido");
        }
    }

    private static void finalizarOrden(Taller taller) {
        String codigoOrden = Repositorio.ingresarTexto("Código de la orden");

        if (taller.finalizarOrden(codigoOrden)) {
            Repositorio.mostrarMensaje("Orden finalizada correctamente");
        } else {
            Repositorio.mostrarMensaje("No se pudo finalizar: la orden no existe o todavía tiene detalles sin finalizar");
        }
    }

    private static void consultarOrdenesCliente(Taller taller) {
        String documento = Repositorio.ingresarTexto("Documento del cliente");
        List<OrdenServicio> ordenes = taller.consultarOrdenesCliente(documento);
        if (ordenes.isEmpty()) {
            Repositorio.mostrarMensaje("El cliente no tiene órdenes registradas o no existe");
            return;
        }
        Optional<Cliente> cliente = taller.buscarCliente(documento);
        String nombreCliente = cliente.isPresent() ? cliente.get().getNombre() : "";
        String texto = "Órdenes del cliente " + nombreCliente + " (" + documento + ")\n";
        for (OrdenServicio orden : ordenes) {
            texto += orden.getCodigoOrden() + " - Total: $" + orden.calcularValorTotal() + "\n";
        }
        Repositorio.mostrarMensaje(texto);
    }

    private static void consultarTrabajosTecnico(Taller taller) {
        String codigo = Repositorio.ingresarTexto("Código del técnico");
        Map<EstadoDetalle, List<DetalleServicio>> trabajos = taller.consultarTrabajosTecnico(codigo);
        if (trabajos.isEmpty()) {
            Repositorio.mostrarMensaje("El técnico no tiene trabajos asignados o no existe");
            return;
        }
        Optional<Tecnico> tecnico = taller.buscarTecnico(codigo);
        String nombreTecnico = tecnico.isPresent() ? tecnico.get().getNombre() : "";
        String texto = "Trabajos del técnico " + nombreTecnico + " (" + codigo + ")\n";
        for (Map.Entry<EstadoDetalle, List<DetalleServicio>> grupo : trabajos.entrySet()) {
            texto += "\n" + grupo.getKey() + ":\n";
            for (DetalleServicio detalle : grupo.getValue()) {
                EquipoTecnologico eq = detalle.getEquipo();
                texto += detalle.getCodigoDetalle() + " - " + detalle.getServicio().getNombre()
                        + " - Equipo: " + eq.getMarca() + " " + eq.getModelo() + " (" + eq.getCodigoRegistro() + ")\n";
            }
        }
        Repositorio.mostrarMensaje(texto);
    }

    private static void consultarEquiposPendientes(Taller taller) {
        List<EquipoTecnologico> equipos = taller.hallarEquiposPendientes();
        if (equipos.isEmpty()) {
            Repositorio.mostrarMensaje("No hay equipos pendientes");
            return;
        }
        String texto = "EQUIPOS PENDIENTES\n";
        for (EquipoTecnologico equipo : equipos) {
            texto += equipo.getMarca() + " " + equipo.getModelo() + " (Código: " + equipo.getCodigoRegistro() + ")\n";
        }
        Repositorio.mostrarMensaje(texto);
    }

    private static void reporteServicios(Taller taller) {
        Map<String, Long> servicios = taller.contarServiciosSolicitados();
        Map<String, Double> valores = taller.calcularTotalFacturadoPorServicio();
        if (servicios.isEmpty()) {
            Repositorio.mostrarMensaje("No hay servicios registrados en órdenes");
            return;
        }
        String texto = "REPORTE DE SERVICIOS\n";
        for (Map.Entry<String, Long> servicio : servicios.entrySet()) {
            String nombreServicio = servicio.getKey();
            texto += nombreServicio + " - Cantidad: " + servicio.getValue()
                    + " - Total facturado: $" + valores.get(nombreServicio) + "\n";
        }
        Repositorio.mostrarMensaje(texto);
    }

    private static void reporteTecnicos(Taller taller) {
        Map<String, Long> intervenciones = taller.contarIntervencionesPorTecnico();
        Map<String, Double> valores = taller.valorAtendidoPorTecnico();
        if (intervenciones.isEmpty()) {
            Repositorio.mostrarMensaje("Ningún técnico tiene intervenciones finalizadas");
            return;
        }
        String texto = "REPORTE DE TÉCNICOS\n";
        for (Map.Entry<String, Long> tecnico : intervenciones.entrySet()) {
            texto += tecnico.getKey() + " - Intervenciones: " + tecnico.getValue()
                    + " - Valor atendido: $" + valores.get(tecnico.getKey()) + "\n";
        }
        Repositorio.mostrarMensaje(texto);
    }

    private static void consultarInventarioCritico(Taller taller) {
        List<Repuesto> repuestos = taller.consultarInventarioCritico();
        if (repuestos.isEmpty()) {
            Repositorio.mostrarMensaje("No hay repuestos en inventario crítico");
            return;
        }
        String texto = "INVENTARIO CRÍTICO\n";
        for (Repuesto repuesto : repuestos) {
            texto += repuesto.getNombre() + " (Código: " + repuesto.getCodigo() + ") - Disponibles: " + repuesto.getCantidadDisponible() + "\n";
        }
        Repositorio.mostrarMensaje(texto);
    }

    private static void crearServicio(Taller taller) {
        String codigo = Repositorio.ingresarTexto("Código del servicio");
        String nombre = Repositorio.ingresarTexto("Nombre del servicio");
        String descripcion = Repositorio.ingresarTexto("Descripción del servicio");
        double tarifaBase = Repositorio.ingresarNumeroDecimal("Tarifa base");

        String opcion = Repositorio.ingresarTexto("Tipo de servicio:\n" +
                "1. Diagnóstico\n2. Mantenimiento preventivo\n3. Reparación correctiva");
        Servicio servicio;
        switch (opcion) {
            case "1":
                servicio = new Diagnostico(codigo, nombre, descripcion, tarifaBase);
                break;
            case "2":
                servicio = new MantenimientoPreventivo(codigo, nombre, descripcion, tarifaBase);
                break;
            case "3":
                servicio = new ReparacionCorrectiva(codigo, nombre, descripcion, tarifaBase);
                break;
            default:
                Repositorio.mostrarMensaje("Opción no válida");
                return;
        }

        if (taller.crearServicio(servicio)) {
            Repositorio.mostrarMensaje("Servicio registrado correctamente");
        } else {
            Repositorio.mostrarMensaje("Ya existe un servicio con ese código");
        }
    }
}