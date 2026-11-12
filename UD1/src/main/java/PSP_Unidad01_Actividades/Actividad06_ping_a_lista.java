package PSP_Unidad01_Actividades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Actividad06_ping_a_lista
{
    public static void main(String[] args) {
        String[] sitios ={"www.google.com", "www.wikipedia.org", "www.github.com"};
        try {
            ProcessBuilder pb;
            Process p;
            Charset charset = Charset.forName("Cp850");
            BufferedReader br;
            int cont = 0;
            for (String sitio : sitios){
                pb = new ProcessBuilder("ping", sitio);
                p = pb.start();
                br = new BufferedReader( new InputStreamReader(p.getInputStream(), charset) );

                String line ;
                Boolean c=false;
                while( (line = br.readLine()) != null){
                    //System.out.println(line);
                    if (line.contains("perdidos = 0")){
                        //System.out.println("Conexion realizada con exito");
                        c = true;
                    }
                }
                if (c){
                    System.out.println(sitio + ": Accesible");
                    cont++;
                }
                else {
                    System.out.println(sitio + ": Error en la conexion");
                }

            }

            System.out.println("Se han encontrado " + cont + "/" + sitios.length + " sitios");



        }

        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
