package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	 public static Connection getFirebirdConnection() throws ClassNotFoundException, SQLException {
		 String password="";
		 String user="root";
	     return getFirebirdConnection(user, password);
	 }
	  
	 public static Connection getFirebirdConnection(String userName, String password) throws SQLException,
	 ClassNotFoundException {
	     Class.forName("com.mysql.cj.jdbc.Driver");
	     //SET GLOBAL time_zone = '+1:00'
	     String connectionURL = "jdbc:mysql://localhost:3306/aidb2?"
	     		+ "useUnicode=true&"
	     		+ "useJDBCCompliantTimezoneShift=true"
	     		+ "&useLegacyDatetimeCode=false&"
	     		+ "serverTimezone=UTC";
	     Connection conn = DriverManager.getConnection(connectionURL, userName,password);
	     return conn;
	 }
}