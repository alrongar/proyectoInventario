package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectionbd {
    private static final String URL = "jdbc:mysql://localhost:3306/inventario?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connect = null;
    
    
    public Connection connect()  {
    	try {
			if (connect == null || connect.isClosed()){
			     connect = DriverManager.getConnection(URL, USER, PASSWORD);
			     
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	 return connect;
    }

}

