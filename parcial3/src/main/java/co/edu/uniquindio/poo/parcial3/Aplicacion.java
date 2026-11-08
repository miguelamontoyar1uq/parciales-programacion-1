package co.edu.uniquindio.poo.parcial3;

import co.edu.uniquindio.poo.parcial3.controller.PanaderiaController;
import co.edu.uniquindio.poo.parcial3.viewController.EmpleadosViewController;
import co.edu.uniquindio.poo.parcial3.viewController.MenuViewController;
import co.edu.uniquindio.poo.parcial3.viewController.PedidosViewController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Aplicacion extends Application {

    private Stage stage;
    private PanaderiaController panaderiaController;

    public Aplicacion() {
        this.panaderiaController = new PanaderiaController();
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("Panadería El Trigal");
        mostrarMenu();
        stage.show();
    }

    public void mostrarMenu() {
        cambiarVista(new MenuViewController(this));
    }

    public void mostrarEmpleados() {
        cambiarVista(new EmpleadosViewController(this));
    }

    public void mostrarPedidos() {
        cambiarVista(new PedidosViewController(this));
    }

    public PanaderiaController getPanaderiaController() {
        return panaderiaController;
    }

    /** Pone la vista en la ventana (la primera vez crea la escena, después solo cambia la raíz). */
    private void cambiarVista(Parent raiz) {
        if (stage.getScene() == null) {
            stage.setScene(new Scene(raiz, 1000, 720));
        } else {
            stage.getScene().setRoot(raiz);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
