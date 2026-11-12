import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ClienteUDP {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            String mensaje = "Un mensaje";

            byte[] datos = mensaje.getBytes(StandardCharsets.UTF_8);
            InetAddress ipservidor = InetAddress.getByName("localhost");
            DatagramPacket paquete =new DatagramPacket(
                    datos,
                    datos.length,
                    ipservidor,
                    5000
            );
            socket.send(paquete);
            System.out.println("Paquete enviado");
            byte[] buffer = new byte[1024];

            DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);

            socket.receive(respuesta);

            String texto = new String(
                    respuesta.getData(),
                    0,
                    respuesta.getLength(),
                    StandardCharsets.UTF_8
            );
            System.out.println("Respuesta del servidor: '" + texto+"'");

            socket.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
