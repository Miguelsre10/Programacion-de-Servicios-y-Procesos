import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Main2 {
    public static void main(String[] args) {
        String sitio = "www.google.com";
        try {
            ProcessBuilder pb = new ProcessBuilder("ping", sitio);
            Process p = pb.start();
            Charset charset = Charset.forName("Cp850");

            BufferedReader br = new BufferedReader( new InputStreamReader(p.getInputStream(), charset) );
            String line ;
            Boolean c=false;
            while( (line = br.readLine()) != null){
                //System.out.println(line);
                if (line.contains("perdidos = 0")){
                    //System.out.println("Conexion realizada con exito");
                    c = true;
                }
            }

            p.waitFor();
            if (c){
                System.out.println("Conexion realizada con exito");
            }
            else {
                System.out.println("No se encontro el sitio");
            }
        }

        catch (IOException | InterruptedException e){
            System.err.println(e.getMessage());
        }
    }
}
