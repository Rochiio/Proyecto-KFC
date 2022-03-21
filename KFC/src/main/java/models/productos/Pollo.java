package models.productos;

public class Pollo extends Producto{
    private boolean esPicante;

    /**
     * Constructor.
     * @param esPicante para saber si el pollo es picante.
     * @param nombre nombre del producto.
     * @param precio precio del producto.
     */
    public Pollo(String nombre, float precio, boolean esPicante) {
        super(nombre, precio);
        this.esPicante = esPicante;
    }


    //Getter and Setter
    public boolean isEsPicante() {
        return esPicante;
    }

    public void setEsPicante(boolean esPicante) {
        this.esPicante = esPicante;
    }


    @Override
    public String toString() {
        return "Pollo{" + (super.toString()) +
                "Picante: " + esPicante +
                '}';
    }
}
