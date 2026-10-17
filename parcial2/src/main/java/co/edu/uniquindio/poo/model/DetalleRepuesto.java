package co.edu.uniquindio.poo.model;


public class DetalleRepuesto {
    private Repuesto repuesto;
    private int cantidad;
    private double precioAplicado;

    public DetalleRepuesto(Repuesto repuesto, int cantidad) {
        this.repuesto = repuesto;
        this.cantidad = cantidad;
        this.precioAplicado = repuesto.getPrecioUnitario();
    }

    public Repuesto getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(Repuesto repuesto) {
        this.repuesto = repuesto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioAplicado() {
        return precioAplicado;
    }

    public void setPrecioAplicado(double precioAplicado) {
        this.precioAplicado = precioAplicado;
    }
    public double calcularSubtotal(){
        return cantidad * precioAplicado;
    }

    @Override
    public String toString() {
        return "DetalleRepuesto{" +
                "repuesto=" + repuesto +
                ", cantidad=" + cantidad +
                ", precioAplicado=" + precioAplicado +
                '}';
    }
}