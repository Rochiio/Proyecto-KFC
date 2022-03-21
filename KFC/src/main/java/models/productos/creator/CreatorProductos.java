package models.productos.creator;

import models.productos.*;
import models.productos.enums.TipoPostre;
import models.productos.enums.TipoSalsa;
import repositories.ProductosRepository;

public class CreatorProductos {
    private final ProductosRepository repository = ProductosRepository.getInstance();


    /**
     * Para crear todos los productos iniciales
     * @return repository de productos.
     */
    public ProductosRepository ponerProductos(){
        Producto p;

        //Bebidas
        p = new Bebida("CocaCola",3.0f,true);
        repository.addProducto(p.getId(),p);
        p = new Bebida("Fanta",3.0f,true);
        repository.addProducto(p.getId(),p);
        p = new Bebida("Agua",2.5f,false);

        //Pollo
        repository.addProducto(p.getId(),p);
        p = new Pollo("Alitas",4.0f,true);
        repository.addProducto(p.getId(),p);
        p = new Pollo("Tiras",4.5f,false);
        repository.addProducto(p.getId(),p);

        //Hamburguesas
        p = new Burger("Bacon Cheese Burger",9.0f);
        ((Burger)p).addIngrediente(new Ingrediente("Bacon"));
        ((Burger)p).addIngrediente(new Ingrediente("Queso"));
        ((Burger)p).addIngrediente(new Ingrediente("Pollo"));
        ((Burger)p).addIngrediente(new Ingrediente("BBQ"));
        ((Burger)p).addIngrediente(new Ingrediente("Mayonesa Pimentada"));
        repository.addProducto(p.getId(),p);
        p = new Burger("La Sobrada Burger",12.5f);
        ((Burger)p).addIngrediente(new Ingrediente("Doble pollo"));
        ((Burger)p).addIngrediente(new Ingrediente("Doble queso"));
        ((Burger)p).addIngrediente(new Ingrediente("Bacon"));
        ((Burger)p).addIngrediente(new Ingrediente("Lechuga"));
        repository.addProducto(p.getId(),p);

        //Salsas
        p = new Salsa("Miel y Mostaza",1.2f);
        repository.addProducto(p.getId(),p);
        p = new Salsa("Barbacoa", 1.0f);
        repository.addProducto(p.getId(),p);

        //Postres
        p = new Postre("Helado de chocolate",5.6f, TipoPostre.helado);
        repository.addProducto(p.getId(),p);
        p = new Postre("Oreo Shake",7.0f,TipoPostre.batido);
        repository.addProducto(p.getId(),p);

        return repository;
    }






}
