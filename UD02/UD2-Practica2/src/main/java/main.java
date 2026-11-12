public class main {
    public static void main(String[] args) {
        try {
            Origen origen = new Origen(50);
            BufferVideo buffer = new BufferVideo(10);

            Descargador d1 = new Descargador("Descargador1", origen, buffer);
            Descargador d2 = new Descargador("Descargador2", origen, buffer);
            /*Descargador d3 = new Descargador("Descargador3", origen, buffer);
            Descargador d4 = new Descargador("Descargador4", origen, buffer);
            Descargador d5 = new Descargador("Descargador5", origen, buffer);
            Descargador d6 = new Descargador("Descargador6", origen, buffer);*/
            Reproductor r = new Reproductor(buffer);

            Thread threadD1 = new Thread(d1);
            Thread threadD2 = new Thread(d2);
            /*Thread threadD3 = new Thread(d3);
            Thread threadD4 = new Thread(d4);
            Thread threadD5 = new Thread(d5);
            Thread threadD6 = new Thread(d6);
            Thread threadR = new Thread(r);*/

            threadD1.start();
            threadD2.start();
            /*threadD3.start();
            threadD4.start();
            threadD5.start();
            threadD6.start();
            threadR.start();*/

            threadD1.join();
            threadD2.join();
            /*threadD3.join();
            threadD4.join();
            threadD5.join();
            threadD6.join();
            threadR.join();*/

            System.out.println("Se ha terminado la reproduccion del contenido");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
