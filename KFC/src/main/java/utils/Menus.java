package utils;

import com.diogonunes.jcolor.Attribute;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Menus {
    private static Scanner in = new Scanner(System.in);

    /**
     * Menu principal para elegir la opcion.
     * @return opcion elegida.
     */
    public static int menuPrincipal(){
        var regex ="[0-2]+";
        var numero="";

            do {
                System.out.println(colorize("\nElige una opci贸n", Attribute.TEXT_COLOR(200)));
                System.out.println(" -1 Gesti贸n de Productos \n -2 Ir a Carro馃洅 \n -0 Salir");
                numero=in.nextLine().trim();
            }while(!numero.matches(regex));

        return Integer.parseInt(numero);
    }


    /**
     * Menu de gesti贸n de productos para elegir la opcion.
     * @return opcion elegida.
     */
    public static int menuProductos(){
        var regex ="[0-4]+";
        var numero="";

            do {
                System.out.println(colorize("\nElige una opci贸n", Attribute.TEXT_COLOR(200)));
                System.out.println("-1 Crear producto \n" +
                        "-2 Eliminar producto \n" +
                        "-3 Modificar producto \n" +
                        "-4 Mostrar productos \n" +
                        "-0 Salir del Gestor de Productos");
                numero=in.nextLine().trim();
            }while(!numero.matches(regex));

        return Integer.parseInt(numero);
    }


    /**
     * Menu de compra para elegir la opcion.
     * @return opcion elegida.
     */
    public static int menuCompra(){
        var regex ="[0-8]+";
        var numero="";

            do {
                System.out.println(colorize("\nElige una opci贸n", Attribute.TEXT_COLOR(200)));
                System.out.println("-1 Ver carro \n" +
                        "-2 A帽adir al carro \n" +
                        "-3 Eliminar del carro \n" +
                        "-4 Modificar cantidad \n" +
                        "-5 Mostrar productos \n" +
                        "-6 Mostrar total compra actual \n" +
                        "-7 Finalizar compra \n" +
                        "-8 Ver todas las compras realizadas \n" +
                        "-0 Salir del carro");
                numero=in.nextLine().trim();
            }while(!numero.matches(regex));

        return Integer.parseInt(numero);
    }
}
