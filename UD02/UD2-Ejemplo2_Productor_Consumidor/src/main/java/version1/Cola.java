package version1;

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

    public boolean put(Integer dato){
        if (!isFull()) {
            lista.add(dato);
            return true;
        }
        return false;
    }

    public Integer get(){
        if(!isEmpty()){
            return lista.removeFirst();
        }
        return null;
    }

    public boolean isEmpty(){
        return lista.isEmpty();
    }

    public boolean isFull(){
        return lista.size() >= maximo;
    }

    public String toString(){
        return lista.toString() + "[" + lista.size() + "/" + maximo + "]";
    }
}
