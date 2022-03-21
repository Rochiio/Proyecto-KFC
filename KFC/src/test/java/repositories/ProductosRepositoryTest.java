package repositories;

import exceptions.ProductosException;
import models.productos.Bebida;
import models.productos.Postre;
import models.productos.Producto;
import models.productos.enums.TipoPostre;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductosRepositoryTest {
    private ProductosRepository productosRepository;
    Producto producto = new Postre("KitKat",6.70f, TipoPostre.helado);

    @BeforeEach
    void setUp() {
        productosRepository=ProductosRepository.getInstance();
    }

    @AfterEach
    void eliminar(){
        productosRepository.removeProducto(producto.getId());
    }

    @Test
    void addProducto() {
        var result = productosRepository.addProducto(producto.getId(),producto);
        Assertions.assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(producto, productosRepository.existByName("KitKat")),
                () -> assertEquals(1,productosRepository.size())
        );
    }

    @Test
    void removeProducto() {
       var result = productosRepository.addProducto(producto.getId(),producto);
       var eliminado = productosRepository.removeProducto(result.getId());
       Assertions.assertAll(
               () -> assertNotNull(eliminado),
               () -> assertEquals(producto.getId(),eliminado.getId()),
               () -> assertEquals(producto.getNombre(),eliminado.getNombre()),
               () -> assertEquals(producto.getPrecio(),eliminado.getPrecio())
       );
    }

    @Test
    void existByName() {
        var resultado = productosRepository.addProducto(producto.getId(),producto);
        var exist = productosRepository.existByName("KitKat");
        Assertions.assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals(resultado.getId(),exist.getId()),
                () -> assertEquals(resultado.getNombre(),exist.getNombre()),
                () -> assertEquals(resultado.getPrecio(),exist.getPrecio())
        );
    }

    @Test
    void modificarProductos() {
        var result = productosRepository.addProducto(producto.getId(),producto);
        var nuevo = result;
        var nuevoName="Choco";      var precio=3.50f;
        nuevo.setNombre(nuevoName);     nuevo.setPrecio(precio);
        Assertions.assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(nuevo,productosRepository.modificarProductos(result,nuevoName,precio))
        );
    }


    @Test
    void modificarProductosException() {
        Producto producto2 = new Bebida("Pepe", 2.50f, true);
        var result = productosRepository.addProducto(producto.getId(), producto);
        productosRepository.addProducto(producto2.getId(), producto2);

        Exception myException = null;
            try {
                productosRepository.modificarProductos(result,"Pepe",2.3f);
            } catch (ProductosException e) {
                myException = e;
            }
        Assertions.assertTrue(myException.getMessage().contains("Ya existe otro producto con el nombre "));
    }


}