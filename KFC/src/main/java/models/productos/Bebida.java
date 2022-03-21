package models.productos;

public class Bebida extends Producto{
    private boolean esGaseosa;

    /**
     * Constructor.
     * @param esGaseosa boolean para saber si la bebida tiene gas.
     * @param nombre nombre del producto.
     * @param precio precio del producto.
     */
    public Bebida(String nombre, float precio, boolean esGaseosa) {
        super(nombre, precio);
        this.esGaseosa = esGaseosa;
    }


    //Getter & Setter
    public boolean isEsGaseosa() {
        return esGaseosa;
    }

    public void setEsGaseosa(boolean esGaseosa) {
        this.esGaseosa = esGaseosa;
    }


    @Override
    public String toString() {
        return "Bebida{" + (super.toString()) +
                "esGaseosa: " + esGaseosa +
                '}';
    }
}
