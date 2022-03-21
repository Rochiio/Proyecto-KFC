package repositories;

import models.productos.Postre;
import models.productos.Producto;
import models.productos.enums.TipoPostre;
import models.venta.LineaVenta;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineaVentaRepositoryTest {
    LineaVentaRepository lineaVentaRepository = new LineaVentaRepository();
    Producto producto = new Postre("KitKat",6.70f, TipoPostre.helado);


    @Test
    void addLineaVenta() {
        var nuevaLinea = new LineaVenta(producto,5);
        var lineadd = lineaVentaRepository.addLineaVenta(nuevaLinea.getId(),nuevaLinea);
        Assertions.assertAll(
                () -> assertNotNull(lineadd),
                () -> assertEquals(1,lineaVentaRepository.size())
        );
    }

    @Test
    void removeLinea() {
        var nuevaLinea = new LineaVenta(producto,5);
        var lineadd = lineaVentaRepository.addLineaVenta(nuevaLinea.getId(),nuevaLinea);
        var eliminado = lineaVentaRepository.removeLinea(lineadd.getId());
        Assertions.assertAll(
                () -> assertNotNull(eliminado),
                () -> assertEquals(0, lineaVentaRepository.size()),
                () -> assertEquals(eliminado.getProducto(),producto)
        );
    }

    @Test
    void existProductInLinea() {
        var nuevaLinea = new LineaVenta(producto,5);
        var lineadd = lineaVentaRepository.addLineaVenta(nuevaLinea.getId(),nuevaLinea);
        var exist = lineaVentaRepository.existProductInLinea("KitKat");
        Assertions.assertAll(
                () -> assertNotNull(exist),
                () -> assertEquals(exist.getProducto(),producto),
                () -> assertEquals(lineadd.getTotalLinea(),exist.getTotalLinea()),
                () -> assertEquals(lineadd.getCantidad(),exist.getCantidad())
        );
    }

    @Test
    void modificarCantidad() {
        var nuevaLinea = new LineaVenta(producto,5);
        var lineadd = lineaVentaRepository.addLineaVenta(nuevaLinea.getId(),nuevaLinea);
        var modificado = lineaVentaRepository.modificarCantidad(lineadd,8);
        Assertions.assertAll(
                () -> assertNotNull(modificado),
                () -> assertEquals(8,modificado.getCantidad()),
                () -> assertEquals(lineadd.getProducto(),modificado.getProducto()),
                () -> assertEquals(lineadd.getId(),modificado.getId())
        );
    }
}