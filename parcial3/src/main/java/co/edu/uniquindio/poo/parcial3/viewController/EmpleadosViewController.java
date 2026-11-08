package co.edu.uniquindio.poo.parcial3.viewController;

import co.edu.uniquindio.poo.parcial3.Aplicacion;
import co.edu.uniquindio.poo.parcial3.controller.EmpleadosController;
import co.edu.uniquindio.poo.parcial3.model.Empleado;
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

public class EmpleadosViewController extends VBox {

    private Aplicacion aplicacion;
    private EmpleadosController empleadosController;
    private ObservableList<Empleado> listaEmpleados;

    private TextField txtDocumento;
    private TextField txtNombre;
    private TextField txtCodigo;
    private TextField txtMinutos;
    private ComboBox<String> cmbTipo;
    private TextField txtCaja;
    private TableView<Empleado> tablaEmpleados;

    public EmpleadosViewController(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;
        this.empleadosController = aplicacion.getPanaderiaController().getEmpleadosController();
        this.listaEmpleados = FXCollections.observableArrayList();

        this.txtDocumento = Estilos.campoTexto();
        this.txtNombre = Estilos.campoTexto();
        this.txtCodigo = Estilos.campoTexto();
        this.txtMinutos = Estilos.campoTexto();
        this.txtCaja = Estilos.campoTexto();
        this.cmbTipo = new ComboBox<>();
        this.tablaEmpleados = new TableView<>();

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
        StackPane encabezado = new StackPane(Estilos.titulo("Gestión de Empleados"), btnRegresar);
        StackPane.setAlignment(btnRegresar, Pos.TOP_LEFT);

        // Formulario
        cmbTipo.setItems(FXCollections.observableArrayList(empleadosController.obtenerTipos()));
        Estilos.estilizarCampo(cmbTipo);
        txtCaja.setDisable(true);

        GridPane formulario = new GridPane();
        formulario.setHgap(10);
        formulario.setVgap(8);
        ColumnConstraints colEtiquetas = new ColumnConstraints();
        colEtiquetas.setMinWidth(230);
        colEtiquetas.setPrefWidth(230);
        ColumnConstraints colCampos = new ColumnConstraints();
        colCampos.setHgrow(Priority.ALWAYS);
        formulario.getColumnConstraints().addAll(colEtiquetas, colCampos);

        formulario.addRow(0, Estilos.etiqueta("Documento:"), txtDocumento);
        formulario.addRow(1, Estilos.etiqueta("Nombre:"), txtNombre);
        formulario.addRow(2, Estilos.etiqueta("Código:"), txtCodigo);
        formulario.addRow(3, Estilos.etiqueta("Minutos Disponibles:"), txtMinutos);
        formulario.addRow(4, Estilos.etiqueta("Tipo:"), cmbTipo);
        formulario.addRow(5, Estilos.etiqueta("Caja Asignada:"), txtCaja);

        // Botones de arriba
        Button btnActualizar = Estilos.boton("Actualizar Empleado", Estilos.AZUL, "white");
        btnActualizar.setOnAction(e -> actualizarEmpleado());
        Button btnAgregar = Estilos.boton("Agregar Empleado", Estilos.AZUL, "white");
        btnAgregar.setOnAction(e -> agregarEmpleado());
        HBox botonesSuperiores = new HBox(40, btnActualizar, btnAgregar);
        botonesSuperiores.setAlignment(Pos.CENTER);
        botonesSuperiores.setPadding(new Insets(6, 0, 6, 0));

        // Botones de abajo
        Button btnLimpiar = Estilos.boton("Limpiar", Estilos.AZUL, "white");
        btnLimpiar.setOnAction(e -> limpiarCampos());
        Button btnEliminar = Estilos.boton("Eliminar", Estilos.ROJO, "white");
        btnEliminar.setOnAction(e -> eliminarEmpleado());
        HBox botonesInferiores = new HBox(40, btnLimpiar, btnEliminar);
        botonesInferiores.setAlignment(Pos.CENTER);
        botonesInferiores.setPadding(new Insets(6, 0, 0, 0));

        VBox.setVgrow(tablaEmpleados, Priority.ALWAYS);
        getChildren().addAll(encabezado, formulario, botonesSuperiores, tablaEmpleados, botonesInferiores);
    }

    private void configurarTabla() {
        Estilos.estilizarTabla(tablaEmpleados);
        tablaEmpleados.getColumns().add(Estilos.<Empleado>columna("Documento", e -> e.getDocumento()));
        tablaEmpleados.getColumns().add(Estilos.<Empleado>columna("Nombre", e -> e.getNombre()));
        tablaEmpleados.getColumns().add(Estilos.<Empleado>columna("Código", e -> e.getCodigo()));
        tablaEmpleados.getColumns().add(Estilos.<Empleado>columna("Minutos Disponibles",
                e -> String.valueOf(e.getMinutosDisponibles())));
        tablaEmpleados.getColumns().add(Estilos.<Empleado>columna("Tipo", e -> empleadosController.obtenerTipo(e)));
        tablaEmpleados.setItems(listaEmpleados);
    }

    private void configurarEventos() {
        // La caja solo se habilita cuando el tipo elegido es Cajero
        cmbTipo.valueProperty().addListener((obs, anterior, nuevo) ->
                txtCaja.setDisable(!EmpleadosController.CAJERO.equals(nuevo)));

        // Al seleccionar una fila, se muestran sus datos en el formulario
        tablaEmpleados.getSelectionModel().selectedItemProperty().addListener((obs, anterior, seleccionado) -> {
            if (seleccionado != null) {
                mostrarEmpleado(seleccionado);
            }
        });
    }

    private void agregarEmpleado() {
        if (tablaEmpleados.getSelectionModel().getSelectedItem() != null) {
            Alertas.mostrarAdvertencia("Hay un empleado seleccionado. Presione Limpiar para agregar uno nuevo.");
            return;
        }
        try {
            empleadosController.agregarEmpleado(cmbTipo.getValue(), txtCodigo.getText(), txtNombre.getText(),
                    txtDocumento.getText(), leerMinutos(), txtCaja.getText());
            actualizarTabla();
            limpiarCampos();
            Alertas.mostrarInformacion("Empleado agregado correctamente");
        } catch (IllegalArgumentException | IllegalStateException e) {
            Alertas.mostrarError(e.getMessage());
        }
    }

    private void actualizarEmpleado() {
        Empleado seleccionado = tablaEmpleados.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            Alertas.mostrarAdvertencia("Seleccione un empleado de la tabla");
            return;
        }
        try {
            empleadosController.actualizarEmpleado(seleccionado.getCodigo(), txtNombre.getText(),
                    txtDocumento.getText(), leerMinutos());
            actualizarTabla();
            limpiarCampos();
            Alertas.mostrarInformacion("Empleado actualizado correctamente");
        } catch (IllegalArgumentException | IllegalStateException e) {
            Alertas.mostrarError(e.getMessage());
        }
    }

    private void eliminarEmpleado() {
        Empleado seleccionado = tablaEmpleados.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            Alertas.mostrarAdvertencia("Seleccione un empleado de la tabla");
            return;
        }
        if (!Alertas.confirmar("¿Eliminar al empleado " + seleccionado.getNombre() + "?")) {
            return;
        }
        try {
            empleadosController.eliminarEmpleado(seleccionado.getCodigo());
            actualizarTabla();
            limpiarCampos();
            Alertas.mostrarInformacion("Empleado eliminado correctamente");
        } catch (IllegalArgumentException | IllegalStateException e) {
            Alertas.mostrarError(e.getMessage());
        }
    }

    private void limpiarCampos() {
        tablaEmpleados.getSelectionModel().clearSelection();
        txtDocumento.clear();
        txtNombre.clear();
        txtCodigo.clear();
        txtMinutos.clear();
        txtCaja.clear();
        cmbTipo.setValue(null);
        txtCodigo.setDisable(false);
        cmbTipo.setDisable(false);
    }

    private void mostrarEmpleado(Empleado empleado) {
        txtDocumento.setText(empleado.getDocumento());
        txtNombre.setText(empleado.getNombre());
        txtCodigo.setText(empleado.getCodigo());
        txtMinutos.setText(String.valueOf(empleado.getMinutosDisponibles()));
        cmbTipo.setValue(empleadosController.obtenerTipo(empleado));
        txtCaja.setText(empleadosController.obtenerCaja(empleado));

        // Código, tipo y caja no se pueden modificar (el modelo no lo permite)
        txtCodigo.setDisable(true);
        cmbTipo.setDisable(true);
        txtCaja.setDisable(true);
    }

    private void actualizarTabla() {
        listaEmpleados.setAll(empleadosController.obtenerEmpleados());
        tablaEmpleados.refresh();
    }

    private int leerMinutos() {
        try {
            return Integer.parseInt(txtMinutos.getText().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Los minutos disponibles deben ser un número entero");
        }
    }
}
