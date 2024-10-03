package dao;

import models.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperacionesProductos {
    private Connection connection;

    
    public OperacionesProductos(Connection connection) {
        this.connection = connection;
    }

    
    public boolean crearProducto(String nombre, String descripcion, double precio, int cantidad) {
        String sql = "INSERT INTO productos (nombre, descripcion, precio, cantidad) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombre);
            statement.setString(2, descripcion);
            statement.setDouble(3, precio);
            statement.setInt(4, cantidad);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar el producto.");
            e.printStackTrace();
            return false;
        }
    }

   
    public List<Producto> listarProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT nombre, descripcion, precio, cantidad FROM productos";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                double precio = resultSet.getDouble("precio");
                int cantidad = resultSet.getInt("cantidad");

                Producto producto = new Producto(nombre, precio, descripcion, cantidad);
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los productos.");
            e.printStackTrace();
        }
        return productos;
    }


    public boolean actualizarProducto(String nombre, String descripcion, double precio, int cantidad) {
        String sql = "UPDATE productos SET descripcion = ?, precio = ?, cantidad = ? WHERE nombre = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, descripcion);
            statement.setDouble(2, precio);
            statement.setInt(3, cantidad);
            statement.setString(4, nombre);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el producto.");
            e.printStackTrace();
            return false;
        }
    }

   
    public boolean eliminarProducto(String nombre) {
        String sql = "DELETE FROM productos WHERE nombre = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombre);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto.");
            e.printStackTrace();
            return false;
        }
    }
}


