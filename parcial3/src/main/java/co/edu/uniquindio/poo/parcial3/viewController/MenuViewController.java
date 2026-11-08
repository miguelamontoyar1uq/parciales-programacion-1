package co.edu.uniquindio.poo.parcial3.viewController;

import co.edu.uniquindio.poo.parcial3.Aplicacion;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class MenuViewController extends VBox {

    private Aplicacion aplicacion;

    public MenuViewController(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;

        setAlignment(Pos.CENTER);
        setSpacing(30);
        setStyle(Estilos.FONDO);

        Label titulo = Estilos.titulo("Sistema de Gestión\nPanadería El Trigal");
        titulo.setTextAlignment(TextAlignment.CENTER);

        Button btnEmpleados = Estilos.boton("Gestión de Empleados", Estilos.AZUL, "white");
        btnEmpleados.setOnAction(e -> this.aplicacion.mostrarEmpleados());

        Button btnPedidos = Estilos.boton("Gestión de Pedidos", Estilos.AZUL, "white");
        btnPedidos.setOnAction(e -> this.aplicacion.mostrarPedidos());

        Button btnSalir = Estilos.boton("Salir", Estilos.GRIS_AZULADO, "black");
        btnSalir.setOnAction(e -> Platform.exit());

        getChildren().addAll(titulo, btnEmpleados, btnPedidos, btnSalir);
    }
}
