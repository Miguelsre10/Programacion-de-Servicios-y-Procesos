public class main02 {
    public static void main(String[] args) {
        Contador c = new Contador();
        Thread t1 = new Thread(new Incrementador("t1", c));
        Thread t2 = new Thread(new Incrementador("t2", c));
        Thread t3 = new Thread(new Incrementador("t3", c));
        Thread t4 = new Thread(new Incrementador("t4", c));
        Thread t5 = new Thread(new Incrementador("t5", c));
        Thread t6 = new Thread(new Incrementador("t6", c));
        Thread t7 = new Thread(new Incrementador("t7", c));
        Thread t8 = new Thread(new Incrementador("t8", c));
        Thread t9 = new Thread(new Incrementador("t9", c));
        Thread t10 = new Thread(new Incrementador("t10", c));

        System.out.println("Contdor: " + c.getContador());

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            t7.join();
            t8.join();
            t9.join();
            t10.join();
            System.out.println("Hemos terminado");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Contdor: " + c.getContador());


    }
}

class Contador {

    private int contador;
    public Contador(){
        contador = 0;
    }
    public Contador(int c){
        this.contador = c;
    }
    public synchronized void incrementar(){
        contador++;
    }
    public int getContador(){
        return contador;
    }
}

class Incrementador implements Runnable{
    String nombre;
    Contador contador;
    public Incrementador(String n, Contador c){
        this.nombre = n;
        this.contador = c;
    }

    @Override
    public void run() {
        for (int i = 0 ; i< 555555000 ; i++){
            //synchronized (contador) { // lo bloquea hasta que lo incremente 1000 veces
                contador.incrementar();
            //}
        }
    }
}

