public class Origen {

    private int siguente = 1;
    private int totalFrames;

    public Origen(int siguente, int totalFrames) {
        this.siguente = siguente;
        this.totalFrames = totalFrames;
    }

    public Origen(int totalFrames){
        this(1,totalFrames);
    }

    public synchronized Frame siguienteFrame(String nombreDescargador){
        if(siguente > totalFrames ){
            return null;
        }
        Frame f = new Frame(siguente, nombreDescargador);
        siguente++;
        return f;
    }


}
