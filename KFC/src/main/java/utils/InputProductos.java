package utils;

import com.diogonunes.jcolor.Attribute;
import models.productos.*;
import models.productos.enums.TipoPostre;

import java.util.Arrays;
import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;

public class InputProductos {
    private static final Scanner in = new Scanner(System.in);


    /**
     * Opciones para tipos de productos a añadir.
     * @return opcion elegida
     */
    private static int opcionProductos(){
        var opcion="";
        var regex= "[1-5]+";
            do {
                System.out.println(colorize("Qué producto quieres añadir:", Attribute.TEXT_COLOR(200)));
                System.out.println("-1 Bebidas \n" +
                        "-2 Postres \n" +
                        "-3 Hamburguesas \n" +
                        "-4 Pollo \n" +
                        "-5 Salsas \n");
                opcion=in.nextLine().trim();
            }while (!opcion.matches(regex));
        return Integer.parseInt(opcion);
    }


    /**
     * Switch de tipo de producto a crear.
     * @return el producto ya creado.
     */
    public static Producto creacionProducto(){
        var option = opcionProductos();
            Producto p = null;
                switch (option){
                    case 1: p=creacionBebida();
                    break;
                    case 2: p=creacionPostre();
                    break;
                    case 3: p=creacionBurger();
                    break;
                    case 4: p=creacionPollo();
                    break;
                    case 5: p=creacionSalsa();
                    break;
                }
      return p;
    }


    /**
     * Para pedir el nombre del producto.
     * @param frase pregunta.
     * @return le nombre.
     */
    public static String pedirNombre(String frase) {
        System.out.println(frase);
        return in.nextLine().trim();
    }


    /**
     * Para pedir el precio del prodcuto.
     * @param nombre nombre del nuevo producto.
     */
    public static float pedirPrecio(String nombre) {
        System.out.println("Dime el precio de " + nombre);
        var precio = 0.0f;
        do {
            try {
                precio = in.nextFloat();
            }catch(Exception e){
                System.out.println(colorize("Error, precio incorrecto, por favor inténtelo de nuevo :)",Attribute.RED_TEXT()));
            }
        }while (precio<1.0f);
        return precio;
    }


    /**
     * Método para crear hamburguesa.
     * @return la nueva burger.
     */
    private static Producto creacionBurger() {
        //Decir el nombre
        String nombre = pedirNombre("Nombre de la nueva burger: ");
        //Decir el precio
        float precio = pedirPrecio(nombre);
        var p = new Burger(nombre,precio);
        //Decir los ingredientes
        p = ponerIngredientes(p);

      return p;
    }


    /**
     * Añadirle ingredientes a nuestra nueva burger.
     * @param p burger a la que le vamos a añadir ingredientes.
     * @return burger terminada.
     */
    private static Burger ponerIngredientes(Burger p) {
        String opcion;  var regex= "[S]|[N]";
        do {
            String nombre = pedirNombre("Añade un nuevo ingrediente: ");
            p.addIngrediente(new Ingrediente(nombre));

                do {
                    System.out.println("Desea añadir otro ingrediente [s/n]");
                    opcion = in.nextLine().trim().toUpperCase();
                }while (!opcion.matches(regex));

        }while(!opcion.equalsIgnoreCase("N"));
        return p;
    }


    /**
     * Método para crear pollo.
     * @return el nuevo pollo.
     */
    private static Producto creacionPollo() {
        //Decir el nombre
        String nombre = pedirNombre("Nombre del pollo: ");
        //Decir el precio
        float precio = pedirPrecio(nombre);
        //Decir si la bebida es gaseosa.
        var esPicante = false;  String opcion;  var regex= "[S]|[N]";
        do {
            System.out.println(nombre + " es un pollo picante [S/N]");
            opcion = in.nextLine().trim().toUpperCase();
        }while(!opcion.matches(regex));
        esPicante = opcion.equals("S");

        return new Pollo(nombre,precio,esPicante);
    }


    /**
     * Método para crear una salsa.
     * @return la nueva salsa.
     */
    private static Producto creacionSalsa() {
        //Decir el nombre
        String nombre = pedirNombre("Nombre de la salsa: ");
        //Decir el precio
        float precio = pedirPrecio(nombre);

        return new Salsa(nombre,precio);
    }


    /**
     * Método para crear un postre.
     * @return el nuevo postre.
     */
    private static Producto creacionPostre() {
        //Decir el nombre
        String nombre = pedirNombre("Nombre del postre: ");
        //Decir el precio
        float precio = pedirPrecio(nombre);
        //Decir el tipo de postre
        TipoPostre tipo;     var regex= "[1-3]+";    String num;
        do {
            System.out.println("Dime que postre es: [1-3]" + (Arrays.toString(TipoPostre.values())));
            num = in.nextLine();
        }while(!num.matches(regex));

            if (Integer.parseInt(num) == 1){
                tipo = TipoPostre.helado;
            }else if (Integer.parseInt(num) == 2){
                tipo = TipoPostre.gofre;
            }else{
                tipo = TipoPostre.batido;
            }

      return new Postre(nombre,precio,tipo);
    }


    /**
     * Método para crear una bebida
     * @return la nueva bebida.
     */
    private static Producto creacionBebida() {
        //Decir el nombre
        String nombre = pedirNombre("Nombre de la bebida: ");
        //Decir el precio
        float precio = pedirPrecio(nombre);
        //Decir si la bebida es gaseosa.
        var esGaseosa = false;  String opcion;  var regex= "[S]|[N]";
        do {
            System.out.println(nombre + " es una bebida gaseosa [S/N]");
            opcion = in.nextLine().trim().toUpperCase();
        }while(!opcion.matches(regex));
        esGaseosa = opcion.equals("S");

     return new Bebida(nombre,precio,esGaseosa);
    }

}
