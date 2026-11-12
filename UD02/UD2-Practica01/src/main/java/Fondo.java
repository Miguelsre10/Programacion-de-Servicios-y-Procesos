public class Fondo {

    private final String nombre;
    private int saldo;

    public Fondo(String nombre, int saldo){
        this.nombre = nombre;
        this.saldo = saldo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSaldo() {
        return saldo;
    }

    public void ingresar(int cantidad){
        this.saldo += cantidad;
    }

    public void retirar(int cantidad){
        this.saldo -= cantidad;
        //bool¿?
    }
}
