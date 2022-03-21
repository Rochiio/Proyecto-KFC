package models.productos;

import repositories.IngredientesProductoRepository;

public class Burger extends Producto{
    private IngredientesProductoRepository ingredientesRepository;

    /**
     * Constructor.
     * @param nombre nombre del producto.
     * @param precio precio del producto.
     */
    public Burger(String nombre, float precio) {
        super(nombre, precio);
        ingredientesRepository = new IngredientesProductoRepository();
    }


    /**
     * Quitar ingrediente del producto.
     * @param id numero de ingrediente a quitar.
     * @return la burger sin el ingrediente.
     */
    public Burger quitarIngrediente(String id){
        ingredientesRepository.quitarIngrediente(id);
        return this;
    }


    /**
     * Añadir los ingredientes del producto.
     * @param ingrediente ingrediente a añadir.
     */
    public void addIngrediente(Ingrediente ingrediente){
        ingredientesRepository.addIngrediente(ingrediente);
    }


    @Override
    public String toString() {
        return "Burger{" + (super.toString()) +
                "Ingredientes: " + ingredientesRepository +
                '}';
    }
}
