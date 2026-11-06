module co.edu.uniquindio.poo.parcial3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;
    opens co.edu.uniquindio.poo.parcial3 to javafx.fxml;
    opens co.edu.uniquindio.poo.parcial3.viewcontroller to javafx.fxml;
    opens co.edu.uniquindio.poo.parcial3.model to org.junit.platform.commons;
    exports co.edu.uniquindio.poo.parcial3;
    exports co.edu.uniquindio.poo.parcial3.controller;
    exports co.edu.uniquindio.poo.parcial3.model;
    exports co.edu.uniquindio.poo.parcial3.viewcontroller;
}