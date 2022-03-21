package utils.vista;

import com.diogonunes.jcolor.Attribute;
import controllers.ProductosController;
import controllers.VentasController;
import exceptions.ProductosException;
import exceptions.VentasException;
import models.productos.*;
import utils.InputProductos;
import utils.InputsVenta;
import utils.Menus;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Pantalla {
    private static Pantalla instance = null;
    private static final ProductosController productosController = ProductosController.getInstance();
    private static final VentasController ventaController = VentasController.getInstance();
    Scanner scanner = new Scanner(System.in);

    /**
     * Singleton
     * @return siempre la misma pantalla
     */
    public static Pantalla getInstance() {
        if (instance == null) {
            instance = new Pantalla();
        }
        return instance;
    }


    /**
     * Repeticion programa
     */
    public void programa(){
        int finalizar;
        do {
            finalizar= Menus.menuPrincipal();
            switchMenuPrincipal(finalizar);
        }while(finalizar != 0);

    }


    /**
     * Switch de casos del menú principal
     * @param caso opcion elegida
     */
    private void switchMenuPrincipal(int caso) {
        switch (caso){
            case 0: System.out.println(colorize("\n Adiós, hasta pronto 👋", Attribute.CYAN_TEXT()));
                break;
            case 1: menuProductos();
                break;
            case 2: menuCompra();
                break;
        }
    }


    /**
     * Menú de gestión de productos.
     */
    private void menuProductos() {
        int opcion;
        do {
             opcion = Menus.menuProductos();
            switchMenuProdcutos(opcion);
        }while(opcion != 0);
    }


    /**
     * Casos de la gestión de prodcutos.
     * @param opcion número de caso elegido.
     */
    private void switchMenuProdcutos(int opcion) {
        switch (opcion){
            case 0: System.out.println(colorize("Cerrando el gestor de productos ....",Attribute.GREEN_BACK()));
                break;
            case 1: addProduct();
                break;
            case 2: eliminarProduct();
                break;
            case 3: modificarProduct();
                break;
            case 4: mostrarProducts();
                break;
        }
    }


    /**
     * Menú de compra y sus casos
     */
    private void menuCompra() {
        int opcion;
        do {
            opcion = Menus.menuCompra();
            switchMenuCompra(opcion);
        }while(opcion != 0);
    }


    /**
     * Casos de la compra de prodcutos.
     * @param opcion número de caso elegido.
     */
    private void switchMenuCompra(int opcion) {
        switch (opcion){
            case 0: System.out.println(colorize("Saliendo del carro....",Attribute.GREEN_BACK()));
                break;
            case 1: verCarro();
                break;
            case 2: addCarro();
                break;
            case 3: eliminarDelCarro();
                break;
            case 4: modificarCantidadProductCarro();
                break;
            case 5: mostrarProducts();
                break;
            case 6: mostrarTotalActualCompra();
                break;
            case 7: finalizarCompra();
                break;
            case 8: mostrarTodasComprasRealizadas();
                break;
        }
    }


    //-----------------------------------------------CARRO-----------------------------------------------------

    /**
     * Modificar la cantidad de un producto del carro.
     */
    private void modificarCantidadProductCarro() {
        var nombre = InputProductos.pedirNombre("Dime el nombre del producto a cambiar cantidad del carro: ");
            try {
                var linea = ventaController.existeLineaDeVenta(nombre);
                var cantidad= InputsVenta.pedirCantidad();
                var modificado = ventaController.modificacionCantidad(linea, cantidad);
                System.out.println("Cantidad modificada correctamente: " + modificado);
            } catch (ProductosException | VentasException e) {
                System.out.println(e.getMessage());
            }
    }


    /**
     * Finalizar la venta
     */
    private void finalizarCompra() {
        String venta = null;
            try {
                venta = ventaController.finalizarVenta();
            } catch (VentasException e) {
                System.out.println(e.getMessage());
            }
        System.out.println(venta + "\n");
    }


    /**
     * Mostrar las ventas realizadas.
     */
    private void mostrarTodasComprasRealizadas() {
        var ventas = ventaController.mostrarVentasRealizadas();
        if(ventas.length()==0){
            System.out.println(colorize("No hay ninguna venta realizada",Attribute.BRIGHT_CYAN_TEXT()));
        }else{
            System.out.println(ventas);
        }
    }


    /**
     * Eliminar del carro
     */
    private void eliminarDelCarro() {
        var nombre = InputProductos.pedirNombre("Dime el nombre del producto a eliminar del carro: ");
        try {
            var linea = ventaController.eliminarLineaDeVenta(nombre);
            System.out.println("Eliminado correctamente \n" +linea);
        } catch (ProductosException | VentasException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Añadir producto al carro.
     */
    private void addCarro() {
        var nombre = InputProductos.pedirNombre("Dime el nombre del producto a añadir al carro: ");
        try {
            var linea = ventaController.crearLineaDeVenta(nombre);
            System.out.println("Añadido correctamente \n" +linea);
        } catch (ProductosException | VentasException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Mostrar el total actual de la compra.
     */
    private void mostrarTotalActualCompra() {
        var total = ventaController.verTotalActual();
        System.out.print("El total actual es de : ");
        System.out.printf("%.2f",total);
        System.out.println("\n");
    }


    /**
     * Ver el carro actual.
     */
    private void verCarro() {
        try {
            var venta=ventaController.verVenta();
            System.out.println(venta);
        } catch (VentasException e) {
            System.out.println(e.getMessage());
        }
    }


    //-----------------------------------------------PRODUCTOS-----------------------------------------------------


    /**
     * Añadir producto
     */
    private void addProduct() {
        var producto = InputProductos.creacionProducto();
        try {
            var creado = productosController.addProducto(producto);
                casosImprimir(creado);
        } catch (ProductosException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Eliminar producto
     */
    private void eliminarProduct() {
        var producto = InputProductos.pedirNombre("Dime el nombre del producto a eliminar:");
        try {
            var eliminado = productosController.removeProducto(producto);
                System.out.print("Producto eliminado ");
                casosImprimir(eliminado);
        } catch (ProductosException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Modificar producto
     */
    private void modificarProduct() {
        var producto = InputProductos.pedirNombre("Dime el nombre del producto a modificar:");
            try {
                var modificar = productosController.getProductoByName(producto);
                System.out.println("Nombre actual " + modificar.getNombre() + " escriba el nuevo nombre");
                var nombre = scanner.nextLine().trim();
                var precio = InputProductos.pedirPrecio(modificar.getNombre());
                var modificado = productosController.modificarDatos(modificar, nombre, precio);
                        System.out.print("Producto modificado ");
                        casosImprimir(modificado);
            } catch (ProductosException e) {
                System.out.println(e.getMessage());
            }
    }


    /**
     * Mostrar los productos que hay.
     */
    private void mostrarProducts() {
        System.out.println(productosController.mostrarProductos());
    }


    /**
     * Como se van a mostrar los productos dependiendo de que clase sean.
     * @param producto producto a saber de que clase es instancia.
     */
    private void casosImprimir(Producto producto) {
        if (producto instanceof Bebida){
            System.out.println(producto);
        }else if (producto instanceof Postre){
            System.out.println(producto);
        }else if (producto instanceof Burger){
            System.out.println(producto);
        }else if (producto instanceof Pollo){
            System.out.println(producto);
        }
    }
}
