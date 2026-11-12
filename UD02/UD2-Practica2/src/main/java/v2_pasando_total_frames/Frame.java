package v2_pasando_total_frames;

public class Frame {
    private final int numero;
    private final String descargador;

    public Frame(int numero, String descargador){
        this.numero = numero;
        this.descargador = descargador;
    }

    public int getNumero() {
        return numero;
    }

    public String getDescargador() {
        return descargador;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "numero=" + numero +
                ", descargador='" + descargador + '\'' +
                '}';
    }
}
