package PSP_Unidad01_Actividades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Locale;

public class Actividad05_taskkill {
    public static void main(String[] args) {
        String proceso = "chrome.exe";
        try {
            ProcessBuilder pb = new ProcessBuilder("taskkill ", "/IM", proceso, "/f");
            Process p = pb.start();
            Charset charset = Charset.forName("Cp850");
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), charset));

            int nc= 0, ne = 0;
            boolean close = false;
            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);
                if(line.contains("ERROR: no se encontró el proceso")){
                    //close = false;
                }
                if(line.toLowerCase().contains("correcto")){
                    nc++;
                    close = true;
                }
                if(line.toLowerCase().contains("error")){
                    ne++;
                    close = true;
                }
            }

            p.waitFor();
            if(close){
                if(nc > 0) {
                    System.out.println("Se han cerrado " + nc + " procesos");
                }
                if(ne > 0) {
                    System.out.println("No se han podido cerrar " + ne + " procesos");
                }
            }
            else {
                System.out.println("No se ha encontrado ningun proceso de nombre " + proceso);
            }
        }
        catch (IOException | InterruptedException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
