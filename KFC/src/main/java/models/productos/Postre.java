package models.productos;

import models.productos.enums.TipoPostre;

public class Postre extends Producto{
    private TipoPostre tipo;

    /**
     * Constructor.
     * @param tipo tipo de postre que es.
     * @param nombre nombre del producto.
     * @param precio precio del producto.
     */
    public Postre(String nombre, float precio, TipoPostre tipo) {
        super(nombre, precio);
        this.tipo = tipo;
    }


    //Getter and Setter
    public TipoPostre getTipo() {
        return tipo;
    }

    public void setTipo(TipoPostre tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Postre{" + (super.toString()) +
                "tipo de postre:" + tipo +
                '}';
    }
}
