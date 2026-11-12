import java.util.ArrayList;

public class BandejaADN {
    private final int size;
    private final ArrayList<String> fragmentos;

    public BandejaADN(int size) {
        this.size = size;
        fragmentos = new ArrayList<>();
    }
    public synchronized void depositar(String f){
        while(isFull()){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        fragmentos.add(f);
        notifyAll();
    }

    public synchronized String extaer(){
        while (isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        String f = fragmentos.removeFirst();
        notifyAll();
        return f;
    }

    public synchronized boolean isEmpty(){
        return fragmentos.isEmpty();
    }

    public synchronized boolean isFull(){
        return fragmentos.size() >= size;
    }
}
