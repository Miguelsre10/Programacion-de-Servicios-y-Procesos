package version2;

import java.util.Random;

public class Consumidor implements Runnable{
    private Cola cola;

    public Consumidor(Cola cola){
        this.cola = cola;
    }

    @Override
    public void run(){
        Random r = new Random();
        for (int i = 0; i < 1000; i++){
            Integer d = cola.get();

            try {
                Thread.sleep(r.nextInt(110));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
