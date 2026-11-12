package PSP_Unidad01_Actividades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Actividad02_dir {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd","/c","dir","C:\\");
            Process p = pb.start();
            Charset charset = Charset.forName("Cp850");
            BufferedReader br = new BufferedReader( new InputStreamReader(p.getInputStream(), charset) );
            String line ;
            while( (line = br.readLine()) != null) {
                if (line.contains("bytes libres")){
                    System.out.println(line);
                    String[] s = line.split("\\s+");
                    float space = Float.parseFloat(s[3].replaceAll("\\.",""));
                    System.out.println( (space/1000000000) + " GB de espacio disponible");
                }
            }
            p.waitFor();
        }
        catch (IOException | InterruptedException e){
            System.err.println(e.getMessage());
        }

    }
}
