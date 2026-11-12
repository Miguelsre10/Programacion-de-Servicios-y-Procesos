public class AnalizadorADN implements Runnable{
    private String nombre;
    private BandejaADN bandejaADN;

    public AnalizadorADN(String nombre, BandejaADN bandeja) {
        this.nombre = nombre;
        this.bandejaADN = bandeja;
    }

    @Override
    public void run() {
        while (true){
            String f = bandejaADN.extaer();
            String salida = "["+nombre+"] Fragmento recibido: " + f;

            int contador_A = 0;
            int contador_C = 0;
            int contador_G = 0;
            int contador_T = 0;
            //contamos las repeticiones
            for (int i = 0; i < f.length(); i++) {
                if (f.charAt(i) == 'A') {
                    contador_A++;
                }
                if (f.charAt(i) == 'C') {
                    contador_C  ++;
                }
                if (f.charAt(i) == 'G') {
                    contador_G++;
                }
                if (f.charAt(i) == 'T') {
                    contador_T++;
                }
            }

            //concatenamos el analisis a la salida
            salida += " (A:" + contador_A + ", C:" + contador_C + ", G:" + contador_G + ", T:"+ contador_T + ")";

            System.out.println(salida);
        }
    }
}
