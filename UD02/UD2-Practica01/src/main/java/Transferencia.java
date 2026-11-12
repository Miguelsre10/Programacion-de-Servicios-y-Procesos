public class Transferencia implements Runnable{
    private final Fondo origen;
    private final Fondo destino;
    private final int cantidad;

    public Transferencia(Fondo origen, Fondo destino, int cantidad) {
        this.origen = origen;
        this.destino = destino;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {
        Fondo primero;
        Fondo segundo;
        if(origen.getNombre().compareTo(destino.getNombre()) < 0){
            primero = origen;
            segundo = destino;
        }
        else{
            primero = destino;
            segundo = origen;
        }

        synchronized (primero){
            synchronized (segundo){
                if(origen.getSaldo() < cantidad){
                    //no se puede hacer la transferencia
                }
                else {
                    origen.retirar(cantidad);
                    destino.ingresar(cantidad);
                }
            }
        }
    }
}
