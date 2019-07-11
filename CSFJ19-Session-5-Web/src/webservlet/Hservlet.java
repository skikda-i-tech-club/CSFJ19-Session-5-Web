package webservlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ressource.UserAccount;
import utils.DBUtils;
import utils.MyUtils;
 
public class Hservlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
 
   public Hservlet() {
       super();
   }
 
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {   
	   
       RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/accueil.jsp");
        
       dispatcher.forward(request, response);   
   }
 
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       String userName = request.getParameter("username");
       String password = request.getParameter("password");
       System.out.println(userName+" ; "+password);
       UserAccount user = null;
       boolean hasError = false;
       String errorString = null;

       UserAccount natureuser = null;
       Connection conn = MyUtils.getStoredConnection(request);

       if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
           hasError = true;
           errorString = "Veuillez introduire le Nom et le mot de passe Utilisateur!";
       } else {
           try {
               user = DBUtils.findUser(conn, userName, password);

               if (user == null) {
                   hasError = true;
                   errorString = "Nom ou mot de passe Utilisateur invalide";
               }
           } catch (SQLException e) {
               e.printStackTrace();
               hasError = true;
               errorString = e.getMessage();
           }
       }
       if (hasError) {

           request.setAttribute("errorString", errorString);

           RequestDispatcher dispatcher 
                   //= this.getServletContext().getRequestDispatcher("/WEB-INF/views/accueil.jsp");
           = this.getServletContext().getRequestDispatcher("/Error.jsp");

           dispatcher.forward(request, response);
       }

       else {
           MyUtils.storeLoginedUser(request.getSession(), user);
           
           try {
               natureuser = DBUtils.findCompteType(conn, userName,password);
           }catch (SQLException e) {
               e.printStackTrace();
               hasError = true;
               errorString = e.getMessage();
           }
           String nature=natureuser.getNature();
           
           System.out.println(nature);
           
           if(nature.equals("Admin")) {
        	   response.sendRedirect(request.getContextPath() + "/home");
           }else {
               response.sendRedirect(request.getContextPath() + "/homec");
           }
       }
    }
}