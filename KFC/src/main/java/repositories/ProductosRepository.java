package repositories;

import exceptions.ProductosException;
import models.productos.Producto;

import java.util.TreeMap;

public class ProductosRepository extends TreeMap<Integer, Producto> {
    //Instancia
    public static ProductosRepository instance = null;


    /**
     * Instancia de la clase.
     * @return misma instancia.
     */
    public static ProductosRepository getInstance(){
        if(instance == null){
            instance = new ProductosRepository();
        }
        return instance;
    }


    /**
     * Añadir producto.
     * @param id clave del producto.
     * @param producto producto.
     * @return el producto añadido.
     */
    public Producto addProducto(Integer id, Producto producto){
        this.put(id,producto);
        return producto;
    }


    /**
     * Eliminar producto.
     * @param id clave por la que se borra.
     * @return el producto o nulo si no existe.
     */
    public Producto removeProducto(Integer id){
        return this.remove(id);
    }



    /**
     * Saber si un producto existe por su nombre.
     * @param nombre nombre del producto.
     * @return null si no existe y el producto si existe.
     */
    public Producto existByName(String nombre){
        for (Integer key : this.keySet()){
            if(this.get(key).getNombre().equalsIgnoreCase(nombre)){
                return this.get(key);
            }
        }
        return null;
    }


    /**
     * Modificar datos del producto.
     * @param modificar producto modificado
     * @param nombre nombre nuevo.
     * @param precio precio nuevo.
     * @return el producto modificado.
     */
    public Producto modificarProductos(Producto modificar, String nombre, float precio) throws ProductosException {
        var exist = existByName(nombre);
            if (exist == null){
                this.get(modificar.getId()).setNombre((nombre.isEmpty()? modificar.getNombre(): nombre));
                this.get(modificar.getId()).setPrecio(precio);
            }else if (exist.getId()!=modificar.getId()){
                throw new ProductosException("Ya existe otro producto con el nombre " +nombre);
            }
        return modificar;
    }


    /**
     * Mostrar todos los productos.
     * @return String de todos los productos.
     */
    public String mostrarProductos(){
        StringBuilder sb = new StringBuilder();

        for (Integer key: this.keySet()) {
            sb.append("-").append(key).append(" : ").append(this.get(key).toString()).append("\n");
        }
        return sb.toString();
    }



}
