package utils;

import java.util.Scanner;

public class InputsVenta {
    static Scanner scanner = new Scanner(System.in);

    public static int pedirCantidad(){
        int opcion;
            do {
                try {
                    System.out.println("Que cantidad quieres: ");
                    opcion = scanner.nextInt();
                }catch(Exception e){
                    opcion = 0;
                    scanner.next();
                }
            }while (opcion<1);
        return opcion;
    }
}
