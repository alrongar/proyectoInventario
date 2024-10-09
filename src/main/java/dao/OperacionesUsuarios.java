package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JOptionPane;

import security.CifradodeContraseñas;

public class OperacionesUsuarios {

    private Connection connection;

    public OperacionesUsuarios(Connection connection) {
        this.connection = connection;
    } 

    // Incluir usuario
    public void incluirUsuario(String nombre, String contraseña, boolean isAdministrador) {
        String sql = "INSERT INTO usuario (nombre, contraseña, isAdministrador) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            String contraseñaHasheada = CifradodeContraseñas.generarHash(contraseña);
            
            stmt.setString(1, nombre);
            stmt.setString(2, contraseñaHasheada); 
            stmt.setBoolean(3, isAdministrador);
            stmt.executeUpdate();
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "Nombre de usuario ya existente", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Eliminar usuario
    public void eliminarUsuario(String nombre) {
        String sql = "DELETE FROM usuario WHERE nombre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Editar usuario
    public void editarUsuario(String nombre, String nuevaContraseña, boolean isAdministrador) {
        String sql = "UPDATE usuario SET contraseña = ?, isAdministrador = ? WHERE nombre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            String contraseñaHasheada = CifradodeContraseñas.generarHash(nuevaContraseña);
            stmt.setString(1, contraseñaHasheada);
            stmt.setBoolean(2, isAdministrador);
            stmt.setString(3, nombre);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Verificar usuario (login)
    public int verificarUsuario(String nombre, String contraseña) {
        String sql = "SELECT contraseña FROM usuario WHERE nombre = ?";
        int tipoUsuario = 2;  // 0: admin, 1: usuario, 2: incorrecto, 3: no encontrado
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String contraseñaAlmacenada = rs.getString("contraseña");
                    if (CifradodeContraseñas.verificarHash(contraseña, contraseñaAlmacenada)) {
                        tipoUsuario = esAdministrador(nombre) ? 0 : 1;
                    } 
                } else {
                    tipoUsuario = 3;  
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tipoUsuario;
    }

    // Verificar si es administrador
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

