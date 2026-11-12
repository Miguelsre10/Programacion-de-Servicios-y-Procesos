package version2;

import java.util.ArrayList;

public class Cola {

    private final int maximo;
    private final ArrayList<Integer> lista;

    public Cola(){
        this(1);
    }

    public Cola(int maximo){
        this.maximo = maximo;
        lista = new ArrayList<>();
    }

    public synchronized boolean put(Integer dato){
        while(isFull()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        lista.add(dato);
        notify();
        return true;
    }

    public synchronized Integer get(){
        while(isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Integer i = lista.removeFirst();
        notify();
        return i;
    }

    public synchronized boolean isEmpty(){
        return lista.isEmpty();
    }

    public synchronized boolean isFull(){
        return lista.size() >= maximo;
    }

    public synchronized String toString(){
        return lista.toString() + "[" + lista.size() + "/" + maximo + "]";
    }
}
