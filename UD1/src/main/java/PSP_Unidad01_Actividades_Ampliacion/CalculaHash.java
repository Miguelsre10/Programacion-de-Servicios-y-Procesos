package PSP_Unidad01_Actividades_Ampliacion;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CalculaHash {

    public static void main(String[] args) {
        //java CalculaHash comares
        //System.out.println(args.length);
        //System.out.println(args[0]);

        if(args.length > 0 ){
            String hash;
            try {
                hash = sha256(args[0]);
            } catch (NoSuchAlgorithmException e) {
                hash ="";
                throw new RuntimeException(e);
            }
            System.out.println(hash);
        }

    }

    /**
     * Funcion que calcula el hash de una cadena dada
     * @param data cadena de la cual calcular el hash
     * @return el hash de la cadena
     * @throws NoSuchAlgorithmException
     */
    private static String sha256(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(data.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

}
