package utils;

import java.text.NumberFormat;
import java.util.Locale;

public class Formateo {
    public static String parseMoney(Float price){
        Locale locale = Locale.forLanguageTag("es");
        return NumberFormat.getCurrencyInstance(locale).format(price);
    }
}
