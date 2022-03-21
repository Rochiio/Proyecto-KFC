package exceptions;

import com.diogonunes.jcolor.Attribute;

import static com.diogonunes.jcolor.Ansi.colorize;

public class VentasException extends Exception{
    public VentasException(String mensaje) {
        super(colorize(mensaje, Attribute.RED_TEXT()));
    }
}
