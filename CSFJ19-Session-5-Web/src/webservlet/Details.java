package webservlet;
import java.io.IOException; 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet(urlPatterns = { "/details"})
public class Details extends HttpServlet {
   private static final long serialVersionUID = 1L;
 
   public Details() {
       super();
   }
 
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/accueil.jsp");
        
       dispatcher.forward(request, response);   
   }
}