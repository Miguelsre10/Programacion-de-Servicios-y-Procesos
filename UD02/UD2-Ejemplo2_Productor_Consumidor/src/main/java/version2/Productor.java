package version2;

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
            cola.put(i);//return boolean

            try {
                Thread.sleep(r.nextInt(100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
