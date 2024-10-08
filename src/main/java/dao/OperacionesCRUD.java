package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Producto;

public class OperacionesCRUD {
    private Connection connection;

    
    public OperacionesCRUD(Connection connection) {
        this.connection = connection;
    }

    
    public boolean createProducto(String nombre, String descripcion, double precio, int cantidad) {
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

    //
    public List<Producto> readProductos() {
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

    // UPDATE: Actualizar un producto por nombre
    public boolean updateProducto(String nombre, String descripcion, double precio, int cantidad) {
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

    // DELETE: Eliminar un producto por nombre
    public boolean deleteProducto(String nombre) {
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


