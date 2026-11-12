import java.io.*;
import java.nio.charset.Charset;
public class EjemploSort {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("sort");
            Process p = pb.start();
            // Enviar datos al stdin del proceso
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream(), Charset.forName("Cp850")));
            bw.write("pera"); bw.newLine();
            bw.write("manzana"); bw.newLine();
            bw.write("naranja"); bw.newLine();
            bw.close(); // Importante: indica al proceso que no habrá más datos
            // Leer el stdout del proceso
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), Charset.forName("Cp850")));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            p.waitFor();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}