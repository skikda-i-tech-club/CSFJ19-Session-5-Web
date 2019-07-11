package utils;
 
import java.sql.Connection;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import ressource.UserAccount;
 
public class MyUtils {
 
    public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
 
    public static void storeConnection(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_NAME_CONNECTION, conn);
    }
 
    public static Connection getStoredConnection(ServletRequest request) {
        Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
        return conn;
    }
 
    public static void storeLoginedUser(HttpSession session, UserAccount loginedUser) {
        session.setAttribute("loginedUser", loginedUser);
    }
 
    public static UserAccount getLoginedUser(HttpSession session) {
        UserAccount loginedUser = (UserAccount) session.getAttribute("loginedUser");
        return loginedUser;
    }
}