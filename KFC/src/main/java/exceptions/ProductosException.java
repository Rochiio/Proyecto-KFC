package exceptions;

import com.diogonunes.jcolor.Attribute;

import static com.diogonunes.jcolor.Ansi.colorize;

public class ProductosException extends Exception {

    public ProductosException(String mensaje) {
        super(colorize(mensaje, Attribute.RED_TEXT()));
    }
}
