package dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import models.Producto;

public class OperacionesProductos {

    private Connection connection;

    public OperacionesProductos(Connection connection) {
        this.connection = connection;
    }

    // Método para crear un producto 
    public boolean crearProducto(String nombre, String descripcion, double precio, int cantidad, String imagen) {
        String sql = "INSERT INTO producto (nombre, descripcion, precio, cantidad, imagen) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombre);
            statement.setString(2, descripcion);
            statement.setDouble(3, precio);
            statement.setInt(4, cantidad);
            statement.setString(5, imagen);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "Este producto ya existente, cambia el nombre", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }

    // Método para listar todos los productos 
    public List<Producto> listarProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT nombre, descripcion, precio, cantidad, imagen FROM producto";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                double precio = resultSet.getDouble("precio");
                int cantidad = resultSet.getInt("cantidad");
                String imagen = resultSet.getString("imagen");
                Producto producto = new Producto(nombre, precio, descripcion, cantidad, imagen);
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    // Método para actualizar un producto 
    public boolean actualizarProducto(String nombre, String descripcion, double precio, int cantidad, String imagen) {
        String sql = "UPDATE producto SET descripcion = ?, precio = ?, cantidad = ?, imagen = ? WHERE nombre = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, descripcion);
            statement.setDouble(2, precio);
            statement.setInt(3, cantidad);
            statement.setString(4, imagen);
            statement.setString(5, nombre);
            
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "Este nombre de producto ya existe, cambia el nombre", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un producto
    public boolean eliminarProducto(String nombre) {
        String sql = "DELETE FROM producto WHERE nombre = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombre);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public String encodeImageToBase64(File image) {
    	
    	String base64Image = "";
    	
    	try {
			byte[] imagenBytes = Files.readAllBytes(image.toPath());
			
			base64Image = Base64.getEncoder().encodeToString(imagenBytes);
			
			
		} catch (IOException e) {
			// TODO: handle exception
		}
    	
    	return base64Image;
    }
    
    public BufferedImage decodeBase64ToImage(String base64Image) {
    	
    	if (base64Image.contains(",")) {
			base64Image = base64Image.split(",")[1];
		}
    	
    	BufferedImage image = null;
    	byte[] imageBytes = Base64.getDecoder().decode(base64Image);
    	
    	try (ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);){
			
			
			image = ImageIO.read(bis);
			
		} catch (IOException e) {
			// TODO: handle exception
		}
    	
    	return image;
    }
    
    public static String imageToBase64String(ImageIcon imageIcon) {
        if (imageIcon == null || imageIcon.getImage() == null) {
            return null; // Si no hay imagen, retornar null o manejar error
        }

        // Convertir ImageIcon a BufferedImage
        BufferedImage bufferedImage = new BufferedImage(
            imageIcon.getIconWidth(),
            imageIcon.getIconHeight(),
            BufferedImage.TYPE_INT_RGB
        );
        bufferedImage.getGraphics().drawImage(imageIcon.getImage(), 0, 0, null);

        // Convertir BufferedImage a array de bytes
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "jpg", outputStream); // Especificar formato (jpg, png, etc.)
            byte[] imageBytes = outputStream.toByteArray();

            // Codificar los bytes en una cadena Base64
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}



