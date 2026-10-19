package co.edu.uniquindio.poo.model;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Taller {
    private final String nombre;
    private final String direccion;
    private final String nit;
    private final String telefono;
    private Map<String, Cliente> listaClientes;
    private ArrayList<Servicio> listaServicios;
    private ArrayList<OrdenServicio> listaOrdenesServicio;
    private ArrayList<Tecnico> listaTecnicos;
    private Map<String, Repuesto> listaRepuestos;

    public Taller(String nombre, String direccion, String nit, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.nit = nit;
        this.telefono = telefono;
        listaClientes = new HashMap<>();
        listaOrdenesServicio = new ArrayList<>();
        listaServicios = new ArrayList<>();
        listaTecnicos = new ArrayList<>();
        listaRepuestos = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNit() {
        return nit;
    }

    public String getTelefono() {
        return telefono;
    }

    public List<Cliente> getListaClientes() {
        return Collections.unmodifiableList(new ArrayList<>(listaClientes.values()));
    }

    public List<Servicio> getListaServicios() {
        return Collections.unmodifiableList(listaServicios);
    }

    public List<OrdenServicio> getListaOrdenesServicio() {
        return Collections.unmodifiableList(listaOrdenesServicio);
    }

    public List<Tecnico> getListaTecnicos() {
        return Collections.unmodifiableList(listaTecnicos);
    }

    public List<Repuesto> getListaRepuestos() {
        return Collections.unmodifiableList(new ArrayList<>(listaRepuestos.values()));
    }

    // =========================================================================
    // CRUD CLIENTE
    // =========================================================================

    // Funcionalidad 1: Registrar cliente
    public boolean crearCliente(Cliente cliente) {
        Optional<Cliente> buscado = buscarCliente(cliente.getDocumento());
        if (buscado.isPresent()) {
            return false;
        }

        listaClientes.put(cliente.getDocumento(), cliente);
        return true;
    }

    // Buscar cliente
    public Optional<Cliente> buscarCliente(String documento) {
        return Optional.ofNullable(listaClientes.get(documento));
    }

    // Actualizar cliente
    public boolean actualizarCliente(String documento, String telefono, String correo, String direccion) {
        Optional<Cliente> buscado = buscarCliente(documento);
        if (buscado.isEmpty()) {
            return false;
        }
        Cliente cliente = buscado.get();
        cliente.setTelefono(telefono);
        cliente.setCorreo(correo);
        cliente.setDireccion(direccion);
        return true;
    }

    // Eliminar cliente
    public boolean eliminarCliente(String documento) {
        Optional<Cliente> buscado = buscarCliente(documento);
        if (buscado.isEmpty()) {
            return false;
        }
        listaClientes.remove(documento);
        return true;
    }

    // =========================================================================
    // CRUD TÉCNICO
    // =========================================================================

    // Funcionalidad 3: Registrar técnico
    public boolean crearTecnico(Tecnico tecnico) {
        Optional<Tecnico> buscado = buscarTecnico(tecnico.getCodigo());
        if (buscado.isPresent()) {
            return false;
        }
        listaTecnicos.add(tecnico);
        return true;
    }

    // Buscar técnico
    public Optional<Tecnico> buscarTecnico(String codigo) {
        return listaTecnicos.stream().filter(tecnico -> tecnico.getCodigo().equals(codigo)).findFirst();
    }

    // Actualizar técnico
    public boolean actualizarTecnico(String codigo, String nombre, EspecialidadTecnico especialidad, EstadoLaboralTecnico estadoLaboral) {
        Optional<Tecnico> buscado = buscarTecnico(codigo);
        if (buscado.isEmpty()) {
            return false;
        }
        Tecnico tecnico = buscado.get();
        tecnico.setNombre(nombre);
        tecnico.setEspecialidadTecnico(especialidad);
        tecnico.setEstadoLaboralTecnico(estadoLaboral);
        return true;
    }

    // Eliminar técnico
    public boolean eliminarTecnico(String codigo) {
        Optional<Tecnico> buscado = buscarTecnico(codigo);
        if (buscado.isEmpty()) {
            return false;
        }
        return listaTecnicos.remove(buscado.get());
    }

    // =========================================================================
    // CRUD REPUESTO
    // =========================================================================

    // Funcionalidad 4: Registrar repuesto
    public boolean crearRepuesto(Repuesto repuesto) {
        if (repuesto.getCantidadDisponible() < 0 || repuesto.getPrecioUnitario() <= 0) {
            return false;
        }
        Optional<Repuesto> buscado = buscarRepuesto(repuesto.getCodigo());
        if (buscado.isPresent()) {
            return false;
        }
        listaRepuestos.put(repuesto.getCodigo(), repuesto);
        return true;
    }

    // Buscar repuesto
    public Optional<Repuesto> buscarRepuesto(String codigo) {
        return Optional.ofNullable(listaRepuestos.get(codigo));
    }

    // Actualizar repuesto
    public boolean actualizarRepuesto(String codigo, String nombre, String descripcion, double precioUnitario) {
        Optional<Repuesto> buscado = buscarRepuesto(codigo);
        if (buscado.isEmpty() || precioUnitario <= 0) {
            return false;
        }
        Repuesto repuesto = buscado.get();
        repuesto.setNombre(nombre);
        repuesto.setDescripcion(descripcion);
        repuesto.setPrecioUnitario(precioUnitario);
        return true;
    }

    // Eliminar repuesto
    public boolean eliminarRepuesto(String codigo) {
        Optional<Repuesto> buscado = buscarRepuesto(codigo);
        if (buscado.isEmpty()) {
            return false;
        }
        listaRepuestos.remove(codigo);
        return true;
    }

    // =========================================================================
    // CRUD EQUIPO TECNOLÓGICO
    // =========================================================================

    // Funcionalidad 2: Registrar equipo
    public boolean crearEquipo(String documento, EquipoTecnologico equipo) {
        Optional<Cliente> cliente = buscarCliente(documento);
        if (cliente.isEmpty()) {
            return false;
        }
        Optional<EquipoTecnologico> buscado = buscarEquipo(equipo.getCodigoRegistro());
        if (buscado.isPresent()) {
            return false;
        }
        cliente.get().agregarEquipo(equipo);
        return true;
    }

    // Buscar equipo
    public Optional<EquipoTecnologico> buscarEquipo(String codigoRegistro) {
        return listaClientes.values().stream().flatMap(cliente -> cliente.getListaEquipos().stream()).filter(equipo -> equipo.getCodigoRegistro().equals(codigoRegistro)).findFirst();

        // mano ahi use esa mierda de flat map. ya que pues cada cliente tiene
        // una lista de equipos asi que eso extrae la lista de cada cliente y
        // las une en solo un flujo para comparar de una
    }

    // Actualizar equipo
    public boolean actualizarEquipo(String codigoRegistro, String modelo, String estadoFisico) {
        Optional<EquipoTecnologico> buscado = buscarEquipo(codigoRegistro);
        if (buscado.isEmpty()) {
            return false;
        }
        EquipoTecnologico equipo = buscado.get();
        equipo.setModelo(modelo);
        equipo.setEstadoFisico(estadoFisico);
        return true;
    }

    // =========================================================================
    // CRUD ORDEN DE SERVICIO
    // =========================================================================

    // Funcionalidad 5: Crear orden de servicio
    public boolean crearOrden(String codigoOrden, String documento, String observaciones) {
        Optional<Cliente> cliente = buscarCliente(documento);
        if (cliente.isEmpty()) {
            return false;
        }
        Optional<OrdenServicio> buscada = buscarOrden(codigoOrden);
        if (buscada.isPresent()) {
            return false;
        }
        OrdenServicio orden = new OrdenServicio(codigoOrden, LocalDate.now(), cliente.get(), observaciones, EstadoOrden.ABIERTA);
        listaOrdenesServicio.add(orden);
        return true;
    }

    // Buscar orden
    public Optional<OrdenServicio> buscarOrden(String codigoOrden) {
        return listaOrdenesServicio.stream().filter(orden -> orden.getCodigoOrden().equals(codigoOrden)).findFirst();
    }

    // =========================================================================
    // CRUD SERVICIO
    // =========================================================================

    // Crear servicio
    public boolean crearServicio(Servicio servicio) {
        Optional<Servicio> buscado = buscarServicio(servicio.getCodigo());
        if (buscado.isPresent()) {
            return false;
        }
        listaServicios.add(servicio);
        return true;
    }

    // Buscar servicio
    public Optional<Servicio> buscarServicio(String codigo) {
        return listaServicios.stream().filter(servicio -> servicio.getCodigo().equals(codigo)).findFirst();
    }

    // Actualizar servicio
    public boolean actualizarServicio(String codigo, String nombre, String descripcion, double tarifaBase) {
        Optional<Servicio> buscado = buscarServicio(codigo);
        if (buscado.isEmpty()) {
            return false;
        }
        Servicio servicio = buscado.get();
        servicio.setNombre(nombre);
        servicio.setDescripcion(descripcion);
        servicio.setTarifaBase(tarifaBase);
        return true;
    }

    // Eliminar servicio
    public boolean eliminarServicio(String codigo) {
        Optional<Servicio> buscado = buscarServicio(codigo);
        if (buscado.isEmpty()) {
            return false;
        }
        return listaServicios.remove(buscado.get());
    }

    // =========================================================================
    // OPERACIONES Y DETALLES DE ÓRDENES (FUNCIONALIDADES 6 A 12)
    // =========================================================================

    // Funcionalidad 6: Agregar detalle a una orden
    public boolean agregarDetalleAOrden(String codigoOrden, String codigoDetalle, String codigoEquipo, String codigoServicio, String falla) {
        Optional<OrdenServicio> orden = buscarOrden(codigoOrden);
        if (orden.isEmpty()) {
            return false;
        }
        // este if valida si la orden existe
        Optional<EquipoTecnologico> equipo = orden.get().getCliente().getListaEquipos().stream().filter(e -> e.getCodigoRegistro().equals(codigoEquipo)).findFirst();
        if (equipo.isEmpty()) {
            return false;
        }
        // esre valida si el equipo pertenece a este cliente
        Optional<Servicio> servicio = buscarServicio(codigoServicio);
        if (servicio.isEmpty()) {
            return false;
        }
        // este valida si el servicio existe
        DetalleServicio detalle = new DetalleServicio(codigoDetalle, equipo.get(), servicio.get(), falla);
        return orden.get().agregarDetalle(detalle);
    }

    // Funcionalidad 7: Asignar técnico
    public boolean asignarTecnico(String codigoOrden, String codigoDetalle, String codigoTecnico) {
        Optional<OrdenServicio> orden = buscarOrden(codigoOrden);
        if (orden.isEmpty()) {
            return false;
            // valida si existe la orden
        }
        Optional<DetalleServicio> detalle = orden.get().buscarDetalle(codigoDetalle);
        if (detalle.isEmpty()) {
            return false;
            //valida si existe el detalle en la orden
        }
        Optional<Tecnico> tecnico = buscarTecnico(codigoTecnico);
        if (tecnico.isEmpty()) {
            return false;
            // valida si existe el tecnico
        }
        return detalle.get().asignarTecnico(tecnico.get());
    }

    // Funcionalidad 8: Registrar repuestos utilizados
    public boolean registrarRepuestoUtilizado(String codigoOrden, String codigoDetalle, String codigoRepuesto, int cantidad) {
        Optional<OrdenServicio> orden = buscarOrden(codigoOrden);
        if (orden.isEmpty()) {
            return false;
            // valida si existe la orden
        }
        Optional<DetalleServicio> detalle = orden.get().buscarDetalle(codigoDetalle);
        if (detalle.isEmpty()) {
            return false;
            //valida si existe el detalle en esa orden
        }
        if (!(detalle.get().getServicio() instanceof ReparacionCorrectiva)) {
            return false;
            // valida si es una reparacion correctiva, es la unica que usa
            // repuestos
        }
        Optional<Repuesto> repuesto = buscarRepuesto(codigoRepuesto);
        if (repuesto.isEmpty()) {
            return false;
            // verifica si existe el repuesto
        }
        return detalle.get().agregarRepuesto(repuesto.get(), cantidad);
    }

    // Nota: La Funcionalidad 9 (Calcular valor de un detalle) se ejecuta en DetalleServicio.calcularValor()
    // y la Funcionalidad 10 (Calcular valor total de la orden) en OrdenServicio.calcularValorTotal().

    // Funcionalidad 11: Actualizar estado de un detalle
    public boolean actualizarEstadoDetalle(String codigoOrden, String codigoDetalle, EstadoDetalle nuevoEstado) {
        Optional<OrdenServicio> orden = buscarOrden(codigoOrden);
        if (orden.isEmpty()) {
            return false;
            // si la orden no existe, no se puede continuar
        }
        Optional<DetalleServicio> detalle = orden.get().buscarDetalle(codigoDetalle);
        if (detalle.isEmpty()) {
            return false;
            // si el detalle no existe dentro de esa orden, no se puede continuar
        }
        return detalle.get().actualizarEstado(nuevoEstado);
    }

    // Horas de trabajo
    public boolean registrarHorasDetalle(String codigoOrden, String codigoDetalle, int horas) {
        Optional<OrdenServicio> orden = buscarOrden(codigoOrden);
        if (orden.isEmpty()) {
            return false;
            //valida que la orden existe
        }
        Optional<DetalleServicio> detalle = orden.get().buscarDetalle(codigoDetalle);
        if (detalle.isEmpty()) {
            return false;
            //valida que existe ese detalle en la orden
        }
        return detalle.get().registrarHoras(horas);
    }

    // Funcionalidad 12: Finalizar una orden
    public boolean finalizarOrden(String codigoOrden) {
        Optional<OrdenServicio> orden = buscarOrden(codigoOrden);
        if (orden.isEmpty()) {
            return false;
        }
        return orden.get().finalizar();
        //si la orden no existe no se puede finalizar
    }

    // =========================================================================
    // CONSULTAS Y REPORTES (FUNCIONALIDADES 13 A 18)
    // =========================================================================

    // Funcionalidad 13: Consultar órdenes de un cliente
    public List<OrdenServicio> consultarOrdenesCliente(String documento) {
        return listaOrdenesServicio.stream().filter(ordenServicio -> ordenServicio.getCliente().getDocumento().equals(documento)).toList();
    }

    // Funcionalidad 14: Consultar trabajos de un técnico
    public Map<EstadoDetalle, List<DetalleServicio>> consultarTrabajosTecnico(String codigo) {
        return listaOrdenesServicio.stream().flatMap(ordenServicio -> ordenServicio.getListaDetalles().stream()).filter(detalleServicio -> detalleServicio.getTecnicoAsignado() != null && detalleServicio.getTecnicoAsignado().getCodigo().equals(codigo)).collect(Collectors.groupingBy(detalleServicio -> detalleServicio.getEstado()));
        // mano jajaa el codigo se me cayo por no poner una condcion que
        // valide que el detalle ya tenga un tecnico asignado.
        //.collect le dice a java que agrupe
        //Collectors.groupingBy esta parte tica decirle el criterio bajo el cual lo hara
        // Map<TipoDatoCriterio, List<ObjetoAgrupado>>
        // Clave (K): El criterio por el que agrupas (Código, Ciudad, Categoría, Edad, etc.)
        // Valor (V): La lista con los objetos que pertenecen a ese grupo
    }

    // Funcionalidad 15: Consultar equipos pendientes
    public List<EquipoTecnologico> hallarEquiposPendientes(){
        return listaOrdenesServicio.stream().flatMap(ordenServicio -> ordenServicio.getListaDetalles().stream().filter(detalleServicio -> detalleServicio.getEstado()== EstadoDetalle.PENDIENTE || detalleServicio.getEstado() == EstadoDetalle.EN_PROCESO)).map(detalle -> detalle.getEquipo()).distinct().toList();

        //ahi use el flatMap otra vez por lo que cada elemento de esa lista
        // tiene otra lista. tambien toco usar el map para sacar los  equipos
        // del detalle y el distinct es para evitar repetidos
    }

    // Funcionalidad 16: Generar reporte de servicios
    public Map<String, Long> contarServiciosSolicitados() {
        return listaOrdenesServicio.stream().flatMap(orden -> orden.getListaDetalles().stream()).collect(Collectors.groupingBy(detalle -> detalle.getServicio().getNombre(), Collectors.counting()));
    }

    public Map<String, Double> calcularTotalFacturadoPorServicio() {
        return listaOrdenesServicio.stream().flatMap(orden -> orden.getListaDetalles().stream()).collect(Collectors.groupingBy(detalle -> detalle.getServicio().getNombre(), Collectors.summingDouble(detalle -> detalle.calcularValor())));
    }

    // Funcionalidad 17: Generar reporte de técnicos
    public Map<String, Long> contarIntervencionesPorTecnico() {
        return listaOrdenesServicio.stream().flatMap(orden -> orden.getListaDetalles().stream()).filter(detalle -> detalle.getTecnicoAsignado() != null && detalle.getEstado() == EstadoDetalle.FINALIZADA).collect(Collectors.groupingBy(detalle -> detalle.getTecnicoAsignado().getNombre(), Collectors.counting()));
    }

    public Map<String, Double> valorAtendidoPorTecnico() {
        return listaOrdenesServicio.stream().flatMap(orden -> orden.getListaDetalles().stream()).filter(detalle -> detalle.getTecnicoAsignado() != null && detalle.getEstado() == EstadoDetalle.FINALIZADA).collect(Collectors.groupingBy(detalle -> detalle.getTecnicoAsignado().getNombre(), Collectors.summingDouble(detalle -> detalle.calcularValor())));
    }

    // Funcionalidad 18: Consultar inventario crítico
    public List<Repuesto> consultarInventarioCritico() {
        return listaRepuestos.values().stream().filter(repuesto -> repuesto.getCantidadDisponible() < 5).toList();
    }

    @Override
    public String toString() {
        return "Taller{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", nit='" + nit + '\'' +
                ", telefono='" + telefono + '\'' +
                ", listaClientes=" + listaClientes +
                ", listaServicios=" + listaServicios +
                ", listaOrdenesServicio=" + listaOrdenesServicio +
                ", listaTecnicos=" + listaTecnicos +
                ", listaRepuestos=" + listaRepuestos +
                '}';
    }
}