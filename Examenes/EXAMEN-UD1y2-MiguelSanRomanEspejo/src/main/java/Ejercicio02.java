public class Ejercicio02 {
    public static void main(String[] args) {
        try {
            BandejaADN bandejaADN = new BandejaADN(2);

            GeneradorADN generadorADN1 = new GeneradorADN("SECUENCIADOR 1", bandejaADN);
            GeneradorADN generadorADN2 = new GeneradorADN("SECUENCIADOR 2", bandejaADN);
            AnalizadorADN analizadorADN = new AnalizadorADN("Analizador", bandejaADN);

            Thread thGen1 = new Thread(generadorADN1);
            Thread thGen2 = new Thread(generadorADN2);

            Thread thAnaliz = new Thread(analizadorADN);

            thGen1.start();
            thGen2.start();
            thAnaliz.start();

            thGen1.join();
            thGen2.join();
            thAnaliz.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
