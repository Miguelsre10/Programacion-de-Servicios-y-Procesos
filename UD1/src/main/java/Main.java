import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Main {

    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("ipconfig");
            Process p = pb.start();
            Charset charset = Charset.forName("Cp850");
            InputStream is = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, charset);
            BufferedReader br = new BufferedReader(isr);

            String line ;
            while( (line = br.readLine()) != null){
                //System.out.println(line);
                if(line.contains("Dirección IPv4")){
                    //System.out.println("La he encontrado");
                    String[] partes = line.split(":");
                    //System.out.println(partes[0]);
                    System.out.println(partes[1].trim());
                }


            }
            p.waitFor();

        }
        catch (IOException | InterruptedException e){
            System.err.println(e.getMessage());
        }
    }
}
