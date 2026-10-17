package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.Taller;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Taller taller = new Taller("TechRepair","carrera 25 calle 22 detras " +
                "del burdel","231","3507823456");
        int opcion;
        do {
            opcion= Integer.parseInt(JOptionPane.showInputDialog("BIENVENIDO " +
                    "al sistema de " + taller.getNombre()+ "Seleccione la " +
                    "opcion deseada" +
                    " \n1.Registrar Cliente" +"\n2.Registar Equipo"+"\n3" +
                    ".Registar tecnico" + "\n4.Registar Repuesto"+ "\5.Crear " +
                    "Orden Servicio"));
            switch (opcion) {

                case 1:
                    break;

            }


        } while(opcion!=5);

    }
    private static void crearCliente(Taller taller){
        String nombre


    }
}