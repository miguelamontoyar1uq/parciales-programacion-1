package co.edu.uniquindio.poo.parcial3.viewController;

import co.edu.uniquindio.poo.parcial3.Aplicacion;
import co.edu.uniquindio.poo.parcial3.controller.PedidosController;
import co.edu.uniquindio.poo.parcial3.model.Pedido;
import co.edu.uniquindio.poo.parcial3.model.TipoPan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PedidosViewController extends VBox {

    private Aplicacion aplicacion;
    private PedidosController pedidosController;
    private ObservableList<Pedido> listaPedidos;

    private TextField txtCodigo;
    private TextField txtNombreCliente;
    private ComboBox<TipoPan> cmbTipoPan;
    private TextField txtCantidad;
    private TableView<Pedido> tablaPedidos;

    public PedidosViewController(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;
        this.pedidosController = aplicacion.getPanaderiaController().getPedidosController();
        this.listaPedidos = FXCollections.observableArrayList();

        this.txtCodigo = Estilos.campoTexto();
        this.txtNombreCliente = Estilos.campoTexto();
        this.txtCantidad = Estilos.campoTexto();
        this.cmbTipoPan = new ComboBox<>();
        this.tablaPedidos = new TableView<>();

        construirVista();
        configurarTabla();
        configurarEventos();
        actualizarTabla();
    }

    private void construirVista() {
        setSpacing(10);
        setStyle(Estilos.FONDO);

        // Título + botón Regresar
        Button btnRegresar = Estilos.botonRegresar();
        btnRegresar.setOnAction(e -> aplicacion.mostrarMenu());
        StackPane encabezado = new StackPane(Estilos.titulo("Gestión de Pedidos"), btnRegresar);
        StackPane.setAlignment(btnRegresar, Pos.TOP_LEFT);

        // Formulario
        cmbTipoPan.setItems(FXCollections.observableArrayList(pedidosController.obtenerTiposPan()));
        Estilos.estilizarCampo(cmbTipoPan);

        GridPane formulario = new GridPane();
        formulario.setHgap(10);
        formulario.setVgap(8);
        ColumnConstraints colEtiquetas = new ColumnConstraints();
        colEtiquetas.setMinWidth(230);
        colEtiquetas.setPrefWidth(230);
        ColumnConstraints colCampos = new ColumnConstraints();
        colCampos.setHgrow(Priority.ALWAYS);
        formulario.getColumnConstraints().addAll(colEtiquetas, colCampos);

        formulario.addRow(0, Estilos.etiqueta("Código:"), txtCodigo);
        formulario.addRow(1, Estilos.etiqueta("Nombre Cliente:"), txtNombreCliente);
        formulario.addRow(2, Estilos.etiqueta("Tipo Pan:"), cmbTipoPan);
        formulario.addRow(3, Estilos.etiqueta("Cantidad:"), txtCantidad);

        // Botones de arriba
        Button btnAsignar = Estilos.boton("Asignar Pedido", Estilos.AZUL, "white");
        btnAsignar.setOnAction(e -> asignarPedido());
        Button btnAgregar = Estilos.boton("Agregar Pedido", Estilos.AZUL, "white");
        btnAgregar.setOnAction(e -> agregarPedido());
        HBox botonesSuperiores = new HBox(40, btnAsignar, btnAgregar);
        botonesSuperiores.setAlignment(Pos.CENTER);
        botonesSuperiores.setPadding(new Insets(6, 0, 6, 0));

        // Botones de abajo
        Button btnLimpiar = Estilos.boton("Limpiar", Estilos.AZUL, "white");
        btnLimpiar.setOnAction(e -> limpiarCampos());
        Button btnCancelar = Estilos.boton("Cancelar Pedido", Estilos.ROJO, "white");
        btnCancelar.setOnAction(e -> cancelarPedido());
        HBox botonesInferiores = new HBox(40, btnLimpiar, btnCancelar);
        botonesInferiores.setAlignment(Pos.CENTER);
        botonesInferiores.setPadding(new Insets(6, 0, 0, 0));

        VBox.setVgrow(tablaPedidos, Priority.ALWAYS);
        getChildren().addAll(encabezado, formulario, botonesSuperiores, tablaPedidos, botonesInferiores);
    }

    private void configurarTabla() {
        Estilos.estilizarTabla(tablaPedidos);
        tablaPedidos.getColumns().add(Estilos.<Pedido>columna("Código", p -> p.getCodigo()));
        tablaPedidos.getColumns().add(Estilos.<Pedido>columna("Nombre Cliente", p -> p.getNombreCliente()));
        tablaPedidos.getColumns().add(Estilos.<Pedido>columna("Tipo Pan", p -> p.getTipoPan().name()));
        tablaPedidos.getColumns().add(Estilos.<Pedido>columna("Cantidad", p -> String.valueOf(p.getCantidad())));
        tablaPedidos.getColumns().add(Estilos.<Pedido>columna("Estado", p -> p.getEstado().name()));
        tablaPedidos.getColumns().add(Estilos.<Pedido>columna("Código Empleado",
                p -> p.getCodigoEmpleadoAsignado() == null ? "-" : p.getCodigoEmpleadoAsignado()));
        tablaPedidos.getColumns().add(Estilos.<Pedido>columna("Minutos Reservados",
                p -> String.valueOf(p.getMinutosReservados())));
        tablaPedidos.setItems(listaPedidos);
    }

    private void configurarEventos() {
        // Al seleccionar una fila, se muestran sus datos (solo lectura)
        tablaPedidos.getSelectionModel().selectedItemProperty().addListener((obs, anterior, seleccionado) -> {
            if (seleccionado != null) {
                mostrarPedido(seleccionado);
            }
        });
    }

    private void agregarPedido() {
        if (tablaPedidos.getSelectionModel().getSelectedItem() != null) {
            Alertas.mostrarAdvertencia("Hay un pedido seleccionado. Presione Limpiar para registrar uno nuevo.");
            return;
        }
        try {
            pedidosController.registrarPedido(txtCodigo.getText(), txtNombreCliente.getText(),
                    cmbTipoPan.getValue(), leerCantidad());
            actualizarTabla();
            limpiarCampos();
            Alertas.mostrarInformacion("Pedido registrado correctamente (queda PENDIENTE)");
        } catch (IllegalArgumentException | IllegalStateException e) {
            Alertas.mostrarError(e.getMessage());
        }
    }

    private void asignarPedido() {
        Pedido seleccionado = tablaPedidos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            Alertas.mostrarAdvertencia("Seleccione un pedido de la tabla");
            return;
        }
        try {
            pedidosController.asignarPedido(seleccionado.getCodigo());
            Pedido asignado = pedidosController.buscarPedido(seleccionado.getCodigo());
            actualizarTabla();
            limpiarCampos();
            Alertas.mostrarInformacion("Pedido asignado al empleado " + asignado.getCodigoEmpleadoAsignado()
                    + " (" + asignado.getMinutosReservados() + " minutos reservados)");
        } catch (IllegalArgumentException | IllegalStateException e) {
            Alertas.mostrarError(e.getMessage());
        }
    }

    private void cancelarPedido() {
        Pedido seleccionado = tablaPedidos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            Alertas.mostrarAdvertencia("Seleccione un pedido de la tabla");
            return;
        }
        if (!Alertas.confirmar("¿Cancelar el pedido " + seleccionado.getCodigo() + "?")) {
            return;
        }
        try {
            pedidosController.cancelarPedido(seleccionado.getCodigo());
            actualizarTabla();
            limpiarCampos();
            Alertas.mostrarInformacion("Pedido cancelado correctamente");
        } catch (IllegalArgumentException | IllegalStateException e) {
            Alertas.mostrarError(e.getMessage());
        }
    }

    private void limpiarCampos() {
        tablaPedidos.getSelectionModel().clearSelection();
        txtCodigo.clear();
        txtNombreCliente.clear();
        cmbTipoPan.setValue(null);
        txtCantidad.clear();
        txtCodigo.setDisable(false);
        txtNombreCliente.setDisable(false);
        cmbTipoPan.setDisable(false);
        txtCantidad.setDisable(false);
    }

    private void mostrarPedido(Pedido pedido) {
        txtCodigo.setText(pedido.getCodigo());
        txtNombreCliente.setText(pedido.getNombreCliente());
        cmbTipoPan.setValue(pedido.getTipoPan());
        txtCantidad.setText(String.valueOf(pedido.getCantidad()));

        // El modelo no permite editar un pedido, así que los campos quedan bloqueados
        txtCodigo.setDisable(true);
        txtNombreCliente.setDisable(true);
        cmbTipoPan.setDisable(true);
        txtCantidad.setDisable(true);
    }

    private void actualizarTabla() {
        listaPedidos.setAll(pedidosController.obtenerPedidos());
        tablaPedidos.refresh();
    }

    private int leerCantidad() {
        try {
            return Integer.parseInt(txtCantidad.getText().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("La cantidad debe ser un número entero");
        }
    }
}
