package Actividad01;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String[] args) {
        try {
            Jugador j = new Jugador(2);
            DatagramSocket socket = new DatagramSocket();
            String posicion = "PLAYER#"+j.getId()+"#"+j.getX()+"#"+j.getY();
            byte[] datos = posicion.getBytes(StandardCharsets.UTF_8);
            InetAddress ipservidor = InetAddress.getByName("localhost");
            DatagramPacket paquete =new DatagramPacket(
                    datos,
                    datos.length,
                    ipservidor,
                    5000
            );
            socket.send(paquete);


            ArrayList<Jugador> listaJugadores = new ArrayList<>();
            // Manejamos la recepcion de la informacion de otros jugadores con otro hilo
            Thread receptor = new Thread(() -> {

                while (true) {

                    try {
                        byte[] buffer = new byte[1024];

                        DatagramPacket recibido = new DatagramPacket(
                                buffer,
                                buffer.length
                        );

                        socket.receive(recibido);

                        String mensaje = new String(
                                recibido.getData(),
                                0,
                                recibido.getLength(),
                                StandardCharsets.UTF_8
                        );

                        String[] datosRecibidos = mensaje.split("#");

                        if (datosRecibidos.length != 4 ||
                                !datosRecibidos[0].equals("PLAYER")) {
                            continue;
                        }

                        String id = datosRecibidos[1];
                        int x = Integer.parseInt(datosRecibidos[2]);
                        int y = Integer.parseInt(datosRecibidos[3]);

                        // Ignoramos nuestro propio jugador
                        if (id.equals(j.getId())) {
                            continue;
                        }

                        Jugador encontrado = null;

                        // Buscamos si ya conocemos al jugador
                        for (Jugador jugador : listaJugadores) {

                            if (jugador.getId().equals(id)) {
                                encontrado = jugador;
                                break;
                            }
                        }

                        // Si no existe, lo añadimos
                        if (encontrado == null) {

                            Jugador nuevo = new Jugador(
                                    id,
                                    x,
                                    y,
                                    recibido.getAddress(),
                                    recibido.getPort()
                            );

                            listaJugadores.add(nuevo);

                            System.out.println("Nuevo jugador recibido: " + id);

                        } else {

                            // Si existe, actualizamos su posición
                            encontrado.setX(x);
                            encontrado.setY(y);

                        }

                        // Mostramos los jugadores recibidos
                        synchronized (listaJugadores) {
                            for (Jugador jugador : listaJugadores) {
                                System.out.println(jugador);
                            }
                        }

                    } catch (Exception e) {
                        System.err.println("Error recibiendo: " + e.getMessage());
                    }
                }
            });

            receptor.start();

            Scanner sc = new Scanner(System.in);
            //se ha establecido la conexion al servidor y se ha enviado la posicion inicial
            int x = j.getX();
            int y = j.getY();
            while(true){
                String tecla = sc.next().trim();
                if(tecla.contains("a")){
                    long count = tecla.chars()
                            .filter(ch -> ch == 'a')
                            .count();
                    j.setX(x-=count);
                }
                if(tecla.contains("d")){
                    long count = tecla.chars()
                            .filter(ch -> ch == 'd')
                            .count();
                    j.setX(x+=count);
                }
                if(tecla.contains("s")){
                    long count = tecla.chars()
                            .filter(ch -> ch == 's')
                            .count();
                    j.setY(y-=count);
                }
                if(tecla.contains("w")) {
                    long count = tecla.chars()
                            .filter(ch -> ch == 'w')
                            .count();
                    j.setY(y+=count);
                }
                posicion = "PLAYER#"+j.getId()+"#"+j.getX()+"#"+j.getY();
                datos = posicion.getBytes(StandardCharsets.UTF_8);
                paquete =new DatagramPacket(
                        datos,
                        datos.length,
                        ipservidor,
                        5000
                );
                socket.send(paquete);
                sc.nextLine();
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        };
    }
}
