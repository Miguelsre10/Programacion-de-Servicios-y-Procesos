public class Reproductor implements Runnable{
    private BufferVideo buffer;
    private boolean finContenido = false;

    public Reproductor(BufferVideo buffer){
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(!finContenido) {
            Frame f = buffer.retirar();
            if(f != null){
                System.err.println("Reproduciendo: " + f.toString());
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                finContenido = true;
            }
        }
        buffer.vaciar();
    }
}
