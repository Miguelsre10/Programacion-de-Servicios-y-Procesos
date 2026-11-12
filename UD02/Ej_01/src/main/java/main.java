import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

public class main {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Tarea("nombre1"));
        Thread t2 = new Thread(new Tarea("nombre2"));
        Thread t3 = new Thread(new Tarea("nombre3"));
        Thread t4 = new Thread(new Tarea("nombre4"));
        Thread t5 = new Thread(new Tarea("nombre5"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            System.out.println("Hemos terminado");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}

class Tarea implements Runnable {
    private final String nombre;

    public Tarea(String nombre){
        this.nombre = nombre;
    }

    @Override
    public void run() {
        for (int i = 0 ; i< 1000 ; i++){
            System.out.println(nombre+": " + i);
            try {
                //Thread.sleep(7000);
                Random r = new Random(System.currentTimeMillis());
                int ms = 2000 + r.nextInt(5000);
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}