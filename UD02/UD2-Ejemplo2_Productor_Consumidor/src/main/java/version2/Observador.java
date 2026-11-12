package version2;

import java.util.Random;

public class Observador implements Runnable{
    private Cola cola;

    public Observador(Cola cola){
        this.cola = cola;
    }

    @Override
    public void run() {
        Random r = new Random();
        while(true){
            System.out.println(cola.toString());

            try {
                Thread.sleep(r.nextInt(50));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
