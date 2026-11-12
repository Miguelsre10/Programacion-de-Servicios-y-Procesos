package Actividad01;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ServidorUDP {
    public static void main(String[] args) {

        try{
            ArrayList<Jugador> listaJugadores = new ArrayList<>();

            DatagramSocket socket = new  DatagramSocket(5000);

            while(true){
                byte[] buffer = new byte[1024];
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);

                //recivimos los datos del jugador
                socket.receive(paquete);
                String mensaje = new String(
                        paquete.getData(),
                        0,
                        paquete.getLength(),
                        StandardCharsets.UTF_8
                );

                InetAddress ip = paquete.getAddress();
                int puerto = paquete.getPort();

                //procesamos los datos del jugador
                String[] datos = mensaje.split("#");
                if (datos.length != 4 || !datos[0].equals("PLAYER")) {
                    continue;
                }
                String id = datos[1];
                int x = Integer.parseInt(datos[2]);
                int y = Integer.parseInt(datos[3]);

                Jugador jugadorEncontrado = null;


                //busca si en la lista hay un jugador con el mismo id
                for (Jugador jugador : listaJugadores) {
                    if (jugador.getId().equals(id)) {
                        jugadorEncontrado = jugador;
                        break;
                    }
                }

                if (jugadorEncontrado == null) {

                    Jugador nuevoJugador = new Jugador(id, x, y, ip, puerto);
                    listaJugadores.add(nuevoJugador);

                    System.out.println("Nuevo jugador: " + id);

                } else {

                    // Si existe, actualizamos su posición
                    jugadorEncontrado.setX(x);
                    jugadorEncontrado.setY(y);
                    jugadorEncontrado.setIp(ip);
                    jugadorEncontrado.setPuerto(puerto);

                }

                //temporal
                //controlamos solo un juagador

                /*if(j == null){
                    j = new Jugador(datos[1],Integer.parseInt(datos[2]),Integer.parseInt(datos[3]));
                }
                else {
                    j.setX(Integer.parseInt(datos[2]));
                    j.setY(Integer.parseInt(datos[3]));
                }*/

                byte[] datosEnvio = mensaje.getBytes(StandardCharsets.UTF_8);
                System.out.println();
                for (Jugador jugador : listaJugadores) {
                    System.out.println(jugador.toString());
                    if (!jugador.getId().equals(id)) {

                        DatagramPacket envio = new DatagramPacket(
                                datosEnvio,
                                datosEnvio.length,
                                jugador.getIp(),
                                jugador.getPuerto()
                        );

                        socket.send(envio);
                    }
                }


            }

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
