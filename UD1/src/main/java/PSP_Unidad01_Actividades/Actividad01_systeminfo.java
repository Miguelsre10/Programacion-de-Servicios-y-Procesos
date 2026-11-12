package PSP_Unidad01_Actividades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Actividad01_systeminfo {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("systeminfo");
            Process p = pb.start();
            Charset charset = Charset.forName("Cp850");
            BufferedReader br = new BufferedReader( new InputStreamReader(p.getInputStream(), charset) );
            String line ;
            while( (line = br.readLine()) != null) {
                if (line.contains("Nombre del sistema operativo") || line.contains("Tipo de sistema")
                        || line.contains("Memoria física total")){
                    System.out.println(line);
                }
            }
            p.waitFor();
        }
        catch (IOException | InterruptedException e){
            System.err.println(e.getMessage());
        }
    }
}
