package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectionbd {
    private static final String URL = "jdbc:mysql://localhost:3306/inventario?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "11022003";
    private static Connection connect = null;
    
    
    public Connection connect()  {
    	try {
			if (connect == null || connect.isClosed()) {
      
			    try {
			        connect = DriverManager.getConnection(URL, USER, PASSWORD);
			        System.out.println("Database connection successful.");
			    }catch (SQLException e) {
					e.printStackTrace();
				}
			   
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	 return connect;
    }

    // Método main para probar la conexión
    public static void main(String[] args) {
        // Crear una instancia de la clase Connectionbd
        Connectionbd connectionbd = new Connectionbd();

        // Intentar conectar a la base de datos
        Connection connection = connectionbd.connect();

        // Verificar si la conexión fue exitosa
        if (connection != null) {
            System.out.println("Conexión establecida con éxito.");
            // Aquí podrías realizar otras operaciones si lo deseas

            // Recuerda cerrar la conexión al final
            try {
                connection.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión.");
                e.printStackTrace(); // Añadir traza de la excepción para más información
            }
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }
    }
}

