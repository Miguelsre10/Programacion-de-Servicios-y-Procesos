import java.util.Random;

public class GeneradorADN  implements Runnable{
    private String nombre;
    private String[] letras = new String[]{"A", "T", "C", "G"};
    private BandejaADN bandejaADN;

    public GeneradorADN(String nombre, BandejaADN bandeja) {
        this.nombre = nombre;
        this.bandejaADN = bandeja;
    }

    @Override
    public void run() {
        Random r = new Random();
        while (true){
            String f = "";
            for (int i = 0; i < 8; i++) {
                f += letras[r.nextInt(0,4)];
            }
            bandejaADN.depositar(f);
            System.out.println("["+nombre+"] Fragmento generado: " + f);
        }
    }

}
