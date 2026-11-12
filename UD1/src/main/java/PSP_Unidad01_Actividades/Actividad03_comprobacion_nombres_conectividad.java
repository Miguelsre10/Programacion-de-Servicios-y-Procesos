package PSP_Unidad01_Actividades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Actividad03_comprobacion_nombres_conectividad {
    public static void main(String[] args) {
        String sitio = "www.google.com";
        String ips="";
        try {
            ProcessBuilder pb = new ProcessBuilder("nslookup ",sitio);
            Process p = pb.start();
            Charset charset = Charset.forName("Cp850");
            BufferedReader br = new BufferedReader( new InputStreamReader(p.getInputStream(), charset) );
            String line ;
            boolean ipf = false;
            while( ((line = br.readLine()) != null) && !ipf) {
                line = line.trim();
                if (line.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
                    ipf = true;
                    ips = line;
                    //System.out.println("ips: " + ips);

                }
            }

            ProcessBuilder pb2 = new ProcessBuilder("ping", ips);
            Process p2 = pb2.start();
            BufferedReader br2 = new BufferedReader( new InputStreamReader(p2.getInputStream(), charset) );

            String media="";
            Boolean c=false;
            while( (line = br2.readLine()) != null) {
                if (line.contains("perdidos = 0")){
                    c = true;
                }
                if (line.contains("Media")){
                    String[] partes = line.split(" ");
                    media = partes[(partes.length-1)];
                }
            }
            if (c){
                System.out.println("Conexion a " + sitio + " a traves de " + ips + " realizada con exito");
                System.out.println("El tiempo promedio de los paquetes es de " + media);
            }
            else {
                System.out.println("No se encontro el sitio");
            }


        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
