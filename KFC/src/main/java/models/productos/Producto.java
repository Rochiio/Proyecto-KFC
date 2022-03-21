package models.productos;

import utils.Formateo;

public abstract class Producto {
    private static int contador=0;
    private final int id;
    private String nombre;
    private float precio;


    /**
     * Constructor.
     * @param nombre nombre del producto.
     * @param precio precio del producto.
     */
    public Producto(String nombre, float precio) {
        this.id = ++contador;
        this.nombre = nombre;
        this.precio = precio;
    }


    //Getters & Setters
    public int getId() {return id;}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "nombre: " + nombre + ", precio: " + Formateo.parseMoney(precio) + ", ";
    }
}
