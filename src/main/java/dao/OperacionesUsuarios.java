package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import security.CifradodeContraseñas;

public class OperacionesUsuarios {

    private Connection connection;

    public OperacionesUsuarios(Connection connection) {
        this.connection = connection;
    } 

    public void incluirUsuario(String nombre, String contraseña, boolean isAdministrador) {
        String sql = "INSERT INTO usuario (nombre, contraseña, isAdministrador) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
     
            String contraseñaHasheada = CifradodeContraseñas.generarHash(contraseña);
            
            stmt.setString(1, nombre);
            stmt.setString(2, contraseñaHasheada);  
            stmt.setBoolean(3, isAdministrador);
            stmt.executeUpdate();
            
            System.out.println("Usuario agregado: " + nombre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Método para eliminar un usuario 
    public void eliminarUsuario(String nombre) {
        String sql = "DELETE FROM usuario WHERE nombre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuario eliminado: " + nombre);
            } else {
                System.out.println("Usuario no encontrado: " + nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para editar un usuario por su nombre 
    public void editarUsuario(String nombre, String nuevaContraseña, boolean isAdministrador) {
        String sql = "UPDATE usuario SET contraseña = ?, isAdministrador = ? WHERE nombre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nuevaContraseña);
            stmt.setBoolean(2, isAdministrador);
            stmt.setString(3, nombre);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuario actualizado: " + nombre);
            } else {
                System.out.println("Usuario no encontrado: " + nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Métodos de login de usuario
    public int verificarUsuario(String nombre, String contraseña) {
        String sql = "SELECT contraseña FROM usuario WHERE nombre = ?";
        int tipoUsuario = 2;  
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String contraseñaAlmacenada = rs.getString("contraseña");
                    String contraseñaHasheada = CifradodeContraseñas.generarHash(contraseña);
                    
                    if (contraseñaHasheada.equals(contraseñaAlmacenada)) {
                        if (esAdministrador(nombre)) {
                            //System.out.println(nombre + " es administrador.");
                            tipoUsuario = 0;
                        } else {
                            //System.out.println(nombre + " no es administrador.");
                            tipoUsuario = 1;
                        }
                    } else {
                        //System.out.println("Contraseña incorrecta para el usuario: " + nombre);
                    }
                } else {
                    //System.out.println("Usuario no encontrado: " + nombre);
                    tipoUsuario = 3;  
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tipoUsuario;
    }


    // Método para verificar si un usuario es administrador
    private boolean esAdministrador(String nombre) {
        String sql = "SELECT isAdministrador FROM usuario WHERE nombre = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean("isAdministrador"); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }
}


