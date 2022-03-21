package repositories;

import models.venta.Venta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VentasRealizadasRepositoryTest {
    VentasRealizadasRepository ventasRealizadasRepository = VentasRealizadasRepository.getInstance();

    @Test
    void addVentaRealizada() {
        Venta venta = Venta.getInstance();
        venta.setTotal(15.85f);
        var result = ventasRealizadasRepository.addVentaRealizada(venta.toString());
            Assertions.assertAll(
                    () -> assertNotNull(result),
                    () -> assertEquals(1,ventasRealizadasRepository.size())
            );
    }
}