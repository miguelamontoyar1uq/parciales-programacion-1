package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.*;

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
                    crearCliente(taller);
                    break;
                case 2:

                    break;

                    case 3:
                    crearTecnico(taller);
                    break;
                case 4:
                    registarRepuesto(taller);
                    break;

            }


        } while(opcion!=5);

    }
    private static void crearCliente(Taller taller){
        String nombre= Repositorio.ingresarTexto("Por favor ingrese el nombre" +
                " del cliente");

        String documento= Repositorio.ingresarTexto("Por favor ingrese el " +
                "el documento del cliente");

        String telefono = Repositorio.ingresarTexto("Por favor ingrese el " +
                        "telefono del cliente");

        String direccion= Repositorio.ingresarTexto("Por favor ingrese la " +
                "direccion del cliente");

        String correo= Repositorio.ingresarTexto("Por favor ingrese el " +
                "correo electronico del cliente");

        Cliente cliente= new Cliente(nombre,documento,telefono,correo,
                direccion);
        if(taller.crearCliente(cliente)){
            Repositorio.mostrarMensaje("Cliente registrado correctamente");
        }else{
            Repositorio.mostrarMensaje("Ya existe un cliente con ese documento");
        }

    }
    private static void crearTecnico(Taller taller){
        String nombre= Repositorio.ingresarTexto("por favor ingrese el nombre" +
                " del tecnico a regustrar");
        String codigo= Repositorio.ingresarTexto("por favor ingrese el codigo" +
                " a asingar al tecnico");
        String documentoIdentidad= Repositorio.ingresarTexto("por favor " +
                "ingrese el docuemnto del tecnico ");
        String opcion = Repositorio.ingresarTexto("Seleccione la especialidad:\n" +
                "1. Computadores\n2. Dispositivos móviles\n3. Soporte general");
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

        Tecnico tecnico = new Tecnico(codigo, documentoIdentidad, nombre,
                especialidad, EstadoLaboralTecnico.ACTIVO);
        if(taller.crearTecnico(tecnico)){
            Repositorio.mostrarMensaje("Técnico registrado correctamente");
        }else{
            Repositorio.mostrarMensaje("Ya existe un técnico con ese código");
        }
    }
    private static void registarRepuesto(Taller taller){
        String nombre=Repositorio.ingresarTexto("por favor ingrese el nombre " +
                "del repuesto");
        String descripcion=Repositorio.ingresarTexto("por favor ingrese la " +
                "descripcion del repuesto");
        String codigo=Repositorio.ingresarTexto("por favor ingrese el codigo " +
                "del repuesto");
        String categoriaCompatibilidad=Repositorio.ingresarTexto("por favor " +
                "ingrese la categoria de compatibilidad");
        double precio = Repositorio.ingresarNumeroDecimal("por favor ingrese " +
                "el valor unitario del repuesto");
        int cantidad = Repositorio.ingresarEntero("por favor ingrese la " +
                "cantidad de repuestos");

        Repuesto repuesto = new Repuesto(codigo, nombre, descripcion, precio,
                cantidad, categoriaCompatibilidad);
        if(taller.crearRepuesto(repuesto)){
            Repositorio.mostrarMensaje("Repuesto registrado correctamente");
        }else{
            Repositorio.mostrarMensaje("No se pudo registrar: el código ya existe, o el precio o la cantidad no son válidos");
        }
    }
    }
