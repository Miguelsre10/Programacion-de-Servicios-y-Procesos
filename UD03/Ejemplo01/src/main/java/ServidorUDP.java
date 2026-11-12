import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class ServidorUDP {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(5000);

            while(true){
                byte[] buffer = new byte[1024];
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);

                //esperamos que el servidor reciba algo
                System.out.println("\nServidor esperando en puerto 5000");
                socket.receive(paquete);

                //llega el paquete del cliente
                String mensaje = new String(
                        paquete.getData(),
                        0,
                        paquete.getLength(),
                        StandardCharsets.UTF_8
                );
                InetAddress ip = paquete.getAddress();
                int puerto = paquete.getPort();
                System.out.println("\"El mensaje ha sido recibido\nMensaje: \"" + mensaje + "\"");
                System.out.println("Direccion IP: " + ip + ":" + puerto);

                //Frabricar la respuesta al cliente
                String respuesta = "ECO: " + mensaje;
                byte[] datos = respuesta.getBytes(StandardCharsets.UTF_8);

                DatagramPacket paqueteRespuesta = new DatagramPacket(
                        datos,
                        datos.length,
                        ip,
                        puerto
                );
                socket.send(paqueteRespuesta);
                //socket.close();
            }

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
