package models.venta;

import repositories.LineaVentaRepository;

public class Venta {
    private static Venta instance = null;
    private float total=0.0f;
    private final LineaVentaRepository lineasRepository;


    /**
     * Singleton.
     * @return siempre la misma venta.
     */
    public static Venta getInstance() {
        if (instance == null) {
            instance = new Venta();
        }
     return instance;
    }


    /**
     * Constructor
     */
    private Venta() {
        lineasRepository = new LineaVentaRepository();
    }


    //Getter & Setter
    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    /**
     * Añadir linea de venta
     * @param linea linea a añadir
     * @return la linea añadida.
     */
    public LineaVenta addLinea(LineaVenta linea){
        LineaVenta fin = lineasRepository.addLineaVenta(linea.getId(), linea);
        totalActualVenta(fin);
        return fin;
    }


    /**
     * Eliminar una linea de venta.
     * @param linea linea a eliminar
     * @return la linea eliminada
     */
    public LineaVenta eliminarLinea(LineaVenta linea){
        var eliminado = lineasRepository.removeLinea(linea.getId());
        this.total -= linea.getTotalLinea();

        return eliminado;
    }


    /**
     * Saber si ya existe en la venta ese producto.
     * @param nombre nombre del producto.
     * @return null si no existe o la linea si existe.
     */
    public LineaVenta existProduct(String nombre){
        return lineasRepository.existProductInLinea(nombre);
    }


    /**
     * Calcular el total.
     * @param linea linea de venta a añadir al total.
     */
    private void totalActualVenta(LineaVenta linea){
        this.total += linea.getTotalLinea();
    }


    /**
     * Para saber cuántas líneas de venta tenemos.
     * @return tamaño.
     */
    public int tamLineasVenta(){
        return lineasRepository.size();
    }


    /**
     * Modificar cantidad de un producto.
     * @param linea nueva linea de venta.
     * @param cantidad nueva cantidad.
     * @return la nueva linea de venta.
     */
    public LineaVenta modificarCant(LineaVenta linea, int cantidad){
        var lineaAntigua = lineasRepository.existProductInLinea(linea.getProducto().getNombre());
        this.total-= lineaAntigua.getTotalLinea();
        var nueva = lineasRepository.modificarCantidad(linea, cantidad);
        this.total+=nueva.getTotalLinea();
        return nueva;
    }

    /**
     * Para reiniciar el carro cuando finalizamos una venta
     */
    public void reiniciarCarro(){
        lineasRepository.limpiarCarro();
        this.total=0;
    }


    @Override
    public String toString() {
        return "Venta \n" +
                "Líneas de la venta: \n" + lineasRepository.mostrarLineas() +
                "total de la venta: " + total + " }";
    }
}
