package version1;

public class main {
    public static void main(String[] args) {
        try {
            Cola cola = new Cola(5);
            Productor productor = new Productor(cola);
            Consumidor consumidor = new Consumidor(cola);
            Observador observador = new Observador(cola);

            Thread threadProductor = new Thread(productor);
            Thread threadConsumidor = new Thread(consumidor);
            Thread threadObservador = new Thread(observador);

            threadConsumidor.start();
            threadProductor.start();
            threadObservador.start();

            threadConsumidor.join();
            threadProductor.join();
            threadObservador.join();

            System.out.println("Hemos terminado la simulacion");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
