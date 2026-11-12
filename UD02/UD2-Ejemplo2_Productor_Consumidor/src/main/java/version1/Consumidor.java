package version1;

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
            synchronized (cola){
                Integer d;
                while( (d = cola.get()) == null){
                    try {
                        cola.wait();
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                }
                cola.notify();
            }

            try {
                Thread.sleep(r.nextInt(500));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
