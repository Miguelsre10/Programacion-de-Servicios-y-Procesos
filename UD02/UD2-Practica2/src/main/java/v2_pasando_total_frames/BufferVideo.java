package v2_pasando_total_frames;

import java.util.ArrayList;

public class BufferVideo {

    private final int capacidadMaxima;
    private final ArrayList<Frame> lista;

    public BufferVideo(int max){
        this.capacidadMaxima = max;
        lista = new ArrayList<>();
    }

    // si no se declara tamaño el tamaño del buffer es 1
    public BufferVideo(){this(1);}

    public synchronized void depositar(Frame frame){
        while(isFull()){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        lista.add(frame);
        notifyAll();
    }

    public synchronized Frame retirar(){
        while (isEmpty()){
            try {
                //retirar solo sera ejecutada por el reproductor, por eso mostramos
                // el BUFFERING cuando no hay mas frames
                System.out.println("BUFFERING...");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Frame frame = lista.removeFirst();
        notifyAll();
        return frame;
    }

    public synchronized boolean isEmpty(){
        return lista.isEmpty();
    }

    public synchronized boolean isFull(){
        return lista.size() >= capacidadMaxima;
    }

    @Override
    public String toString() {
        return "BufferVideo{" +
                "capacidadMaxima=" + capacidadMaxima +
                ", lista=" + lista +
                '}';
    }

}
