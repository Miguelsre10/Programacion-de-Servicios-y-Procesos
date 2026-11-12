package v2_pasando_total_frames;

public class Reproductor implements Runnable{
    private BufferVideo buffer;
    private int totalFrames;
    private int cont = 0;

    public Reproductor(BufferVideo buffer, int totalFrames){
        this.buffer = buffer;
        this.totalFrames = totalFrames;
    }

    @Override
    public void run() {
        while(cont < totalFrames) {
            Frame f = buffer.retirar();
            cont++;
            System.err.println("Reproduciendo: " + f.toString());
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
