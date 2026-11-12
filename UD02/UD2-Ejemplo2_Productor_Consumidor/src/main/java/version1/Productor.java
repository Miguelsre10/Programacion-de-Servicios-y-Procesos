package version1;

import java.util.Random;

public class Productor implements Runnable{

    private Cola cola;

    public Productor(Cola cola){
        this.cola = cola;
    }

    @Override
    public void run(){
        Random r = new Random();
        for (int i = 0; i < 1000; i++){
            synchronized (cola){
                while(!cola.put(i)){
                    try {
                        cola.wait();
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                }
                cola.notify();
            }

            try {
                Thread.sleep(r.nextInt(400));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
