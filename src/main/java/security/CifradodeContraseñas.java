package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CifradodeContraseñas {

	private static final String CLAVE_SECRETA = "claveUnica";

    public static String generarHash(String contraseña) {
        byte[] contraseñaCifrada = null;

        try {
            
            MessageDigest digestor = MessageDigest.getInstance("SHA-256");
            digestor.update(CLAVE_SECRETA.getBytes());

    
            contraseñaCifrada = digestor.digest(contraseña.getBytes());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return Base64.getEncoder().encodeToString(contraseñaCifrada);
    }

    
    
    // Método para verificar el hash
    public static boolean verificarHash(String contraseña, String hashAlmacenado) {
        String hashGenerado = generarHash(contraseña);
        
        return hashGenerado.equals(hashAlmacenado);
    }
}


