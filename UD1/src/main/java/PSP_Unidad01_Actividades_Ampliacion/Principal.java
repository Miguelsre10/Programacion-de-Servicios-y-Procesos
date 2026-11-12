package PSP_Unidad01_Actividades_Ampliacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce: ");
        String texto = sc.nextLine();
        try {
            ProcessBuilder pb = new ProcessBuilder("java", "CalculaHash.java", texto);
            Process p = pb.start();
            Charset charset = Charset.forName("Cp850");
            BufferedReader br = new BufferedReader( new InputStreamReader(p.getInputStream(), charset) );
            p.waitFor();
            String hash;
            if((hash = br.readLine()) != null){
                System.out.println(hash);
            }
            else {
                System.err.println("No se ha podido calcular el hash de la cadena");
            }



        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
