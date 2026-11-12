import javax.sound.sampled.Line;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Ejercicio01 {
    public static void main(String[] args) {
        String line ;
        String[] proceso_mas_consumo = new String[5];
        try {

            ProcessBuilder pb = new ProcessBuilder("tasklist","/FO","CSV","/NH");
            Process p = pb.start();
            Charset charset = Charset.forName("Cp850");
            BufferedReader br = new BufferedReader( new InputStreamReader(p.getInputStream(), charset) );

            int contador = 0;
            float max_consumo = 0f;


            while( (line = br.readLine()) != null) {
                contador++;

                line = line.replace("\"", "");
                line = line.replace(" KB", "");
                String[] datos = line.split(",");
                float consumo = Float.parseFloat(datos[4].replace(".",""));

                if( consumo > max_consumo ) {
                    max_consumo = consumo;
                    proceso_mas_consumo = datos;
                }
            }
            p.waitFor();

            System.out.println("Numero de procesos: " + contador);
            System.out.println("El proceso que mas consume es:");
            System.out.println(proceso_mas_consumo[0] + "\tPID: " + proceso_mas_consumo[1] + "\tConsumo: " + proceso_mas_consumo[4] + " KB");
        }
        catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}
