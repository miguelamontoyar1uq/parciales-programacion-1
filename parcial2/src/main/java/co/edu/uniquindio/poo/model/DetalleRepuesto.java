package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.model.Repuesto;

public class DetalleRepuesto {
    private Repuesto repuesto;
    private int cantidad;
    private double precioAplicado;

    public DetalleRepuesto(Repuesto repuesto, int cantidad) {
        this.repuesto = repuesto;
        this.cantidad = cantidad;
        this.precioAplicado = repuesto.getPrecioUnitario();
    }

   }