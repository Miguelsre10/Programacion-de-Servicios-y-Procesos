package Actividad01;

import java.net.InetAddress;

public class Jugador {
    private String id;
    private int  x;
    private int y;

    InetAddress ip;
    int puerto;

    public Jugador(int id){
        this.id = "jugador"+(id);
        this.x = 0;
        this.y = 0;
    }

    public Jugador(String id, int x, int y, InetAddress ip, int puerto) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.ip = ip;
        this.puerto = puerto;
    }

    public String getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public InetAddress getIp() {
        return ip;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id='" + id + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", ip=" + ip +
                ", puerto=" + puerto +
                '}';
    }
}
