package inventario.proyectoADRA2;

import java.sql.Connection;

import database.Connectionbd;
import views.Inicio;

public class App 
{
    public static void main( String[] args )
    {
    	Connectionbd conexionbd = new Connectionbd();
		Connection conexion = conexionbd.connect();
    	Inicio inicio = new Inicio(conexion);
    	inicio.setLocationRelativeTo(null);
    	inicio.setVisible(true);

    }
}
