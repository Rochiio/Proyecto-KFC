package repositories;

import models.productos.Producto;
import models.venta.LineaVenta;

import java.util.TreeMap;

public class LineaVentaRepository extends TreeMap<Integer, LineaVenta> {

    /**
     * Añadir linea.
     * @param id clave de la linea.
     * @param linea nueva linea de venta.
     */
    public LineaVenta addLineaVenta(Integer id, LineaVenta linea){
        this.put(id, linea);
        return linea;
    }


    /**
     * Eliminar linea.
     * @param id clave por la que se borra.
     * @return la linea o nulo si no existe.
     */
    public LineaVenta removeLinea(Integer id){
        return this.remove(id);
    }


    /**
     * Saber si un producto ya existe.
     * @param nombre nombre del producto.
     * @return null si no existe y la linea si existe.
     */
    public LineaVenta existProductInLinea(String nombre){
        for (Integer key : this.keySet()){
            if(this.get(key).getProducto().getNombre().equalsIgnoreCase(nombre)){
                return this.get(key);
            }
        }
        return null;
    }


    /**
     * Para cuando hemos finalizado una compra, vaciar el carro y poner de nuevo el contador a 0.
     */
    public void limpiarCarro(){
        for (Integer key : this.keySet()){
            this.remove(key);
        }
        //Para reiniciar las lineas de venta, que vuelvan a empezar desde 0.
        LineaVenta.contador=0;
    }


    /**
     * Mostrar todas las lineas.
     * @return String de todas las lineas.
     */
    public String mostrarLineas(){
        StringBuilder sb = new StringBuilder();

        for (Integer key: this.keySet()) {
            sb.append("-").append(key).append(" : ").append(this.get(key).toString()).append("\n");
        }
        return sb.toString();
    }

    public LineaVenta modificarCantidad(LineaVenta linea, int cantidad) {
        this.get(linea.getId()).setCantidad(cantidad);
        return this.get(linea.getId());
    }
}
