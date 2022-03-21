package repositories;

import models.productos.Ingrediente;

import java.util.TreeMap;

public class IngredientesProductoRepository extends TreeMap<Integer, Ingrediente> {
    //Contador para clave de los ingredientes.
    private static int contador=0;


    /**
     * Añadir producto.
     * @param ingredient ingrediente.
     */
    public void addIngrediente(Ingrediente ingredient){
        this.put(++contador,ingredient);
    }


    /**
     * Quitar ingredientes
     * @param id numero ingrediente a quitar.
     * @return ingrediente quitado.
     */
    public Ingrediente quitarIngrediente(String id){
        return this.remove(id);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{ ");
            for (Integer key: this.keySet()) {
                sb.append(this.get(key)).append(" ");
            }
        sb.append(" }");
        return sb.toString();
    }
}
