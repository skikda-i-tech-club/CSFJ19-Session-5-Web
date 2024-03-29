package webservlet;
import java.io.IOException; 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet(urlPatterns = { "/contact"})
public class Contact extends HttpServlet {
   private static final long serialVersionUID = 1L;
 
   public Contact() {
       super();
   }
 
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/contact.jsp");
        
       dispatcher.forward(request, response);   
   }
}