package controllers;

import exceptions.ProductosException;
import exceptions.VentasException;
import models.venta.LineaVenta;
import models.venta.Venta;
import repositories.VentasRealizadasRepository;
import utils.InputsVenta;

public class VentasController {
    private static VentasController instance = null;
    private final ProductosController productosController = ProductosController.getInstance();
    private final VentasRealizadasRepository ventasRealizadasRepository = VentasRealizadasRepository.getInstance();
    private final Venta venta = Venta.getInstance();


    /**
     * Singleton.
     * @return misma instancia de Ventas Controller.
     */
    public static VentasController getInstance(){
        if(instance == null){
            instance = new VentasController();
        }
        return instance;
    }


    /**
     * Crear la línea de venta.
     * @return linea de venta
     */
    public LineaVenta crearLineaDeVenta(String nombre) throws ProductosException, VentasException {
        LineaVenta mostrar;
            var producto = productosController.getProductoByName(nombre);
            var cantidad = InputsVenta.pedirCantidad();

            var existe=venta.existProduct(nombre);
                if (existe==null) {
                     var linea = new LineaVenta(producto, cantidad);
                     mostrar = venta.addLinea(linea);
                }else  {
                    throw new VentasException("Este producto ya existe en el carro");
                }

       return mostrar;
    }


    /**
     * Eliminar la línea de venta.
     * @return linea de venta
     */
    public LineaVenta eliminarLineaDeVenta(String nombre) throws ProductosException, VentasException {
        LineaVenta eliminado;
        var producto = productosController.getProductoByName(nombre);
        var existe=venta.existProduct(producto.getNombre());

            if (existe!=null) {
                eliminado = venta.eliminarLinea(existe);
            }else  {
                throw new VentasException("Este producto no existe en el carro");
            }

        return eliminado;
    }


    /**
     * Si existe la linea de venta
     * @param nombre nombre del producto.
     * @return la linea de venta.
     * @throws ProductosException no exista el producto.
     * @throws VentasException no esté el producto en la venta.
     */
    public LineaVenta existeLineaDeVenta(String nombre) throws ProductosException, VentasException {
        var producto = productosController.getProductoByName(nombre);
        var existe=venta.existProduct(producto.getNombre());
        if (existe==null){
            throw new VentasException("No hay el producto " + nombre + " en el carro");
        }
        return existe;
    }


    /**
     * Modificamos la cantidad.
     * @param linea linea a modificar.
     * @param cantidad nueva cantidad.
     * @return la linea modificada
     */
    public LineaVenta modificacionCantidad(LineaVenta linea, int cantidad) {
        return venta.modificarCant(linea, cantidad);
    }


    /**
     * Mostrar las ventas ya realizadas anteriormente.
     * @return String de las ventas
     */
    public String mostrarVentasRealizadas(){
        return ventasRealizadasRepository.mostrarVentas();
    }


    /**
     * Ver la venta actual
     * @return string de la venta.
     */
    public String verVenta() throws VentasException {
        var tam= venta.tamLineasVenta();
            if (tam==0){
                throw new VentasException("No hay nada en el carro");
            }
        return venta.toString();
    }


    /**
     * Ver el total de la compra actual.
     * @return el total.
     */
    public float verTotalActual(){
        return venta.getTotal();
    }


    /**
     * Finalizamos la venta.
     * @return string de la venta.
     * @throws VentasException si no hay nada en el carro.
     */
    public String finalizarVenta() throws VentasException {
        var tam= venta.tamLineasVenta();
        String ventaAdd;
            if (tam==0){
                throw new VentasException("No hay nada en el carro");
            }else {
                 ventaAdd = ventasRealizadasRepository.addVentaRealizada(venta.toString());
                //Ahora como siempre tenemos la misma venta, reiniciamos el carro, como por ejemplo aliexpress
                venta.reiniciarCarro();
            }
        return ventaAdd;
    }



}
