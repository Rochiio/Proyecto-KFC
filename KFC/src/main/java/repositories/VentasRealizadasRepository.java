package repositories;


import java.util.TreeMap;

public class VentasRealizadasRepository extends TreeMap<Integer, String> {
    //Instancia
    public static VentasRealizadasRepository instance = null;
    private static int contador = 0;



    /**
     * Instancia de la clase.
     * @return misma instancia.
     */
    public static VentasRealizadasRepository getInstance(){
        if(instance == null){
            instance = new VentasRealizadasRepository();
        }
        return instance;
    }


    /**
     * Añadir venta a ventas realizadas.
     * @param venta venta añadida.
     * @return la venta que se ha añadido.
     */
    public String addVentaRealizada(String venta){
        this.put(++contador,venta);
        return venta;
    }


    /**
     * Mostrar las ventas realizadas.
     * @return String de las ventas.
     */
    public String mostrarVentas(){
        StringBuilder sb = new StringBuilder();

        for (Integer key: this.keySet()) {
            sb.append("-").append(key).append(" : ").append(this.get(key)).append("\n");
        }
        return sb.toString();
    }
}
