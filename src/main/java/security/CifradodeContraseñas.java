package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class CifradodeContraseñas {

	public static String generarHash(String contraseñaUsuario) {
        byte[] contraseñaHasheadaBytes = null;

        try {
            MessageDigest digestMensaje = MessageDigest.getInstance("SHA-256");
            String semillaFija = generarSemillaFija();
            digestMensaje.update(semillaFija.getBytes());
            contraseñaHasheadaBytes = digestMensaje.digest(contraseñaUsuario.getBytes());

        } catch (NoSuchAlgorithmException nsaex) {
            nsaex.printStackTrace();
        }

        return Base64.getEncoder().encodeToString(contraseñaHasheadaBytes); // Devuelve la contraseña hasheada
    }

    // Método para generar una semilla fija
    public static String generarSemillaFija() {
        byte[] bytesSemilla = "SemillaFijaParaContraseña".getBytes();
        SecureRandom generadorSeguro = new SecureRandom(bytesSemilla); 
        byte[] semilla = new byte[16];
        generadorSeguro.nextBytes(semilla);
        return Base64.getEncoder().encodeToString(semilla); 
    }

    
    
    // Método para verificar el hash
    public static boolean verificarHash(String contraseña, String hashAlmacenado) {
        String hashGenerado = generarHash(contraseña);
        return hashGenerado.equals(hashAlmacenado);
    }
}


