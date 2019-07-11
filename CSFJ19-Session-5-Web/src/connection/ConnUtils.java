package connection;

import java.sql.Connection;
import java.sql.SQLException;


public class ConnUtils {

	public static Connection getConnection() throws ClassNotFoundException, 
	SQLException {
        return DBConnect.getFirebirdConnection();
    }
     
    public static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
        }
    }
 
    public static void rollbackQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
        }
    }
}