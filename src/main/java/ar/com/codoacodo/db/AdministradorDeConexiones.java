package ar.com.codoacodo.db;
import java.sql.Connection;
import java.sql.DriverManager;

import ar.com.codoacodo.db.AdministradorDeConexiones;

public class AdministradorDeConexiones {
	
	// vamos a crear un metodo estatico es decir que no vamos a crear un objeto para usar este metodo
	public static Connection getConnection() {
		String hosts = "localhost";  // 127.0.0.1
		String port = "3307";
		String password = "";
		String username = "root" ;
		String nombredb = "desafio";
		
		// driver de conexion a la base de datos
		String driveClassName = "com.mysql.cj.jdbc.Driver";
		// aplicamos manejo de excepciones que se ve en el curso avanzado
		Connection connection ;
		try {
			// com.mysql.cj.jdbc CONSTRUYE UNA CLASE A PARTIR DE UN OBJETO COMPILADO
			Class.forName(driveClassName);
			//url de conexion
			String url ="jdbc:mysql://"+hosts+":"+port+"/"+nombredb+"?serverTimeZone=UTC&useSSL=false";
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Conecto Bien!!");
		}
		catch(Exception e){
			System.out.println("No conecto!!");
			System.out.println(e.getMessage());
			connection = null;
		}
		
		return connection;
	}

}