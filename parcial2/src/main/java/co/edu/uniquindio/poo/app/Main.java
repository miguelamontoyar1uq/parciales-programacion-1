package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.Taller;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Taller taller = new Taller("TechRepair","carrera 25 calle 22 detras " +
                "del burdel","231","3507823456");
        int opcion;
        do {
            opcion= Integer.parseInt(JOptionPane.showInputDialog("BIENVENIDO al sistema de " + taller.getNombre()))


        } while(opcion!=5);

    }
}