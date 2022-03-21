package controllers;

import exceptions.ProductosException;
import models.productos.creator.CreatorProductos;
import models.productos.Producto;
import repositories.ProductosRepository;

public class ProductosController {
    //Instancia
    private static ProductosController instance = null;
    //Repositorios necesarios
    private ProductosRepository productosRepository;
    //Clases
    private CreatorProductos creadorProductos;


    /**
     * Instancia.
     * @return siempre el mismo productos controller.
     */
    public static ProductosController getInstance(){
        if(instance == null){
            instance = new ProductosController();
        }
        return instance;
    }


    /**
     * Constructor.
     */
    private ProductosController() {
        productosRepository = new ProductosRepository();
        creadorProductos= new CreatorProductos();
        initProductos();
    }


    /**
     * Creamos todos los productos que hay iniciales.
     */
    private void initProductos() {
        productosRepository = creadorProductos.ponerProductos();
    }


    /**
     * Añadir producto.
     * @param producto producto a añadir.
     * @throws ProductosException si ya existe ese producto
     */
    public Producto addProducto(Producto producto) throws ProductosException {
        var exists=productosRepository.existByName(producto.getNombre());
        Producto enviar;

            if (exists==null){
                enviar = productosRepository.addProducto(producto.getId(),producto);
            }else {
                throw new ProductosException("Ya existe un producto con el nombre " + producto.getNombre());
            }
        return enviar;
    }


    /**
     * Eliminar un producto.
     * @param nombre nombre del producto que queremos eliminar.
     * @return producto eliminado o excepcion.
     */
    public Producto removeProducto(String nombre) throws ProductosException {
        var exists=productosRepository.existByName(nombre);
        Producto eliminado;
            if (exists!=null){
                eliminado = productosRepository.removeProducto(exists.getId());
            }else {
                throw new ProductosException("No existe el producto " + nombre);
            }
        return eliminado;
    }


    /**
     * Coger un producto por su nombre
     * @param nombre nombre del producto.
     * @return el producto.
     * @throws ProductosException si no existe ese producto.
     */
    public Producto getProductoByName(String nombre) throws ProductosException{
        var exists=productosRepository.existByName(nombre);
        if (exists==null){
            throw new ProductosException("No existe el producto " + nombre);
        }
        return exists;
    }


    /**
     * Añadir los datos modificados.
     * @param modificar producto modificado
     * @param nombre
     * @param precio
     *
     * @return
     */
    public Producto modificarDatos(Producto modificar, String nombre, float precio) throws ProductosException {
        var modificado = productosRepository.modificarProductos(modificar, nombre, precio);
        return modificado;
    }

    /**
     * Mostrar todos los productos que hay.
     * @return String de prodcutos.
     */
    public String mostrarProductos(){
       return productosRepository.mostrarProductos();
    }



}
