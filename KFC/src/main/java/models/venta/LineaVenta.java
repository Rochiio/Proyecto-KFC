package models.venta;

import models.productos.Producto;

public class LineaVenta {
    public static int contador=0;
    private final int id;
    private final Producto producto;
    private int cantidad;
    private float totalLinea;


    /**
     * Constructor
     * @param producto producto de la línea.
     * @param cantidad cantidad de productos que quiere.
     */
    public LineaVenta(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.totalLinea = producto.getPrecio()*cantidad;
        this.id = ++contador;
    }


    //Getter and Setter
    public Producto getProducto() {
        return producto;
    }

    public int getId() {
        return id;
    }

    public float getTotalLinea() {
        return totalLinea;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.totalLinea= this.producto.getPrecio()*this.cantidad;
    }

    @Override
    public String toString() {
        return "LineaVenta{" +
                "Producto: " + producto +
                ", Cantidad: " + cantidad +
                '}';
    }
}
