public class Main {
    public static void main(String[] args) {
        Fondo tecno = new Fondo("Tecnologia", 10000);
        Fondo energia = new Fondo("Energia", 10000);
        Fondo salud = new Fondo("Salud", 10000);

        System.out.println(tecno.getNombre() + ": " +tecno.getSaldo());
        System.out.println(energia.getNombre() + ": " +energia.getSaldo());
        System.out.println(salud.getNombre() + ": " +salud.getSaldo());
        System.out.println("Saldo total: " + ( tecno.getSaldo() + energia.getSaldo() + salud.getSaldo()) );

        for(int i = 0; i <1000 ; i++) {
            /*Thread tr1 = new Thread(new Transferencia(tecno, salud, 700));
            Thread tr2 = new Thread(new Transferencia(salud, energia, 700));
            Thread tr3 = new Thread(new Transferencia(tecno, energia, 300));
            Thread tr4 = new Thread(new Transferencia(salud, tecno, 500));
            Thread tr5 = new Thread(new Transferencia(energia, tecno, 100));
            Thread tr6 = new Thread(new Transferencia(energia, salud, 100));

            tr1.start();
            tr2.start();
            tr3.start();
            tr4.start();
            tr5.start();
            tr6.start();*/
            Thread tra = new Thread(new Transferencia(energia, salud, 100));
            Thread trb = new Thread(new Transferencia(salud, energia, 100));
            tra.start();
            trb.start();


            try {
                /*tr1.join();
                tr2.join();
                tr3.join();
                tr4.join();
                tr5.join();
                tr6.join();*/
                tra.join();
                trb.join();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        /*tr1.start();
        tr2.start();
        tr3.start();
        tr4.start();
        tr5.start();
        tr6.start();

        try {
            tr1.join();
            tr2.join();
            tr3.join();
            tr4.join();
            tr5.join();
            tr6.join();



            System.out.println("------------------------------------------\nTranferencia Realizada\n");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/

        System.out.println(tecno.getNombre() + ": " +tecno.getSaldo());
        System.out.println(energia.getNombre() + ": " +energia.getSaldo());
        System.out.println(salud.getNombre() + ": " +salud.getSaldo());
        System.out.println("Saldo total: " + ( tecno.getSaldo() + energia.getSaldo() + salud.getSaldo()) );

    }
}
