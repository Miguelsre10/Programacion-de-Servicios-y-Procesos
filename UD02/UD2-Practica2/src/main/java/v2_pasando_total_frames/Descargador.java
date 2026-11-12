package v2_pasando_total_frames;

import java.util.Random;

public class Descargador implements Runnable{
    private String nombre;
    private Origen origen;
    private BufferVideo bufferVideo;

    private boolean finContenido = false;

    public Descargador(String name, Origen origen, BufferVideo buffer){
        this.nombre = name;
        this.origen = origen;
        this.bufferVideo = buffer;
    }


    @Override
    public void run() {
        Random r = new Random();
        while(!finContenido){
            try {
                Thread.sleep(r.nextInt(150,800));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Frame frame = origen.siguienteFrame(this.nombre);
            if(frame != null){
                bufferVideo.depositar(frame);
                System.out.println("Descargador: " + frame.getDescargador());
                System.out.println("Buffer: " + bufferVideo.toString());
            }
            else {
                finContenido = true;
            }
        }

    }
}
