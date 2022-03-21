package models.productos;

import models.productos.enums.TipoSalsa;

public class Salsa extends Producto{
    private TipoSalsa tipo;

    /**
     * Constructor.
     * @param nombre nombre del producto.
     * @param precio precio del producto.
     */
    public Salsa(String nombre, float precio) {
        super(nombre, precio);
    }

    @Override
    public String toString() {
        return "Salsa{" + (super.toString()) +
                '}';
    }
}
