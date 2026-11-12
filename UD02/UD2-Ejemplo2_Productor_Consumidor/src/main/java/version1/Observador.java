package version1;

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
            synchronized (cola){
                System.out.println(cola.toString());
            }

            try {
                Thread.sleep(r.nextInt(200));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
