
package co.edu.uniquindio.poo.parcial3.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.util.function.Function;

/** Colores y métodos de ayuda para armar las vistas solo con código JavaFX. */
public class Estilos {

    public static final String FONDO = "-fx-background-color: #F2FFFF; -fx-padding: 20;";
    public static final String AZUL = "#0080CC";
    public static final String ROJO = "#CC0000";
    public static final String GRIS_AZULADO = "#C2CCE3";

    private Estilos() {
    }

    public static Label titulo(String texto) {
        Label label = new Label(texto);
        label.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 40px; -fx-text-fill: #2B0A0A;");
        return label;
    }

    public static Label etiqueta(String texto) {
        Label label = new Label(texto);
        label.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 18px; -fx-text-fill: #2B0A0A;");
        return label;
    }

    public static Button boton(String texto, String colorFondo, String colorTexto) {
        Button boton = new Button(texto);
        boton.setPrefSize(300, 46);
        boton.setStyle("-fx-background-color: " + colorFondo + "; -fx-text-fill: " + colorTexto + ";"
                + "-fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 16px;"
                + "-fx-background-radius: 8; -fx-border-color: #6B7280; -fx-border-radius: 8; -fx-cursor: hand;");
        return boton;
    }

    public static Button botonRegresar() {
        Button boton = new Button("Regresar");
        boton.setStyle("-fx-background-color: #C5E0FF; -fx-text-fill: black; -fx-font-weight: bold;"
                + "-fx-font-size: 14px; -fx-background-radius: 20; -fx-border-color: #1B1B2F;"
                + "-fx-border-width: 2; -fx-border-radius: 20; -fx-cursor: hand;");
        return boton;
    }

    public static void estilizarCampo(Region campo) {
        campo.setStyle("-fx-background-color: #C8D0DA; -fx-background-radius: 0; -fx-font-size: 15px;");
        campo.setMaxWidth(Double.MAX_VALUE);
    }

    public static TextField campoTexto() {
        TextField campo = new TextField();
        estilizarCampo(campo);
        return campo;
    }

    public static <T> TableColumn<T, String> columna(String titulo, Function<T, String> valor) {
        TableColumn<T, String> columna = new TableColumn<>(titulo);
        columna.setCellValueFactory(c -> new SimpleStringProperty(valor.apply(c.getValue())));
        return columna;
    }

    public static <T> void estilizarTabla(TableView<T> tabla) {
        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabla.setStyle("-fx-control-inner-background: #F2FFFF; -fx-table-cell-border-color: #9FAEFF;"
                + "-fx-border-color: #9FAEFF; -fx-border-width: 2;");
        // El encabezado se crea con la "skin", por eso se pinta cuando ésta ya existe
        tabla.skinProperty().addListener((obs, anterior, nueva) -> {
            javafx.scene.Node encabezado = tabla.lookup(".column-header-background");
            if (encabezado != null) {
                encabezado.setStyle("-fx-background-color: #CCD5FF;");
            }
        });
    }
}
