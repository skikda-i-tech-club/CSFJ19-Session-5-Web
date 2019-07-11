package appartementcruds;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.Appartement;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet("/createAppartement")
public class CreateAppartementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateAppartementServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/Appartement/createAppartementView.jsp");
        dispatcher.forward(request, response);
	}

 	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Connection conn = MyUtils.getStoredConnection(request);
	String errorString = null; 
	boolean ce=false;
	boolean asc=false;
		
	String Description = request.getParameter("Description");
    String Ascenseur = request.getParameter("Ascenseur");
    String Cuisine_equipe = request.getParameter("Cuisine_equipe");
    String Num_bi = request.getParameter("Num_bi");
    int Loc_etage=Integer.valueOf(request.getParameter("Loc_etage"));
    
    if(Cuisine_equipe.equals("true")) ce=true; else ce=false;
    if(Ascenseur.equals("true")) asc=true; else asc=false;
    
    if(Description=="" || Loc_etage==0 || Ascenseur=="" || Cuisine_equipe=="" || Num_bi=="") {
    	errorString="Un ou plusieurs champs vide !";
    	request.setAttribute("errorString", errorString);
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/Appartement/createAppartementView.jsp");
		dispatcher.forward(request, response);
    }else {	 
    	try {
    		DBUtils.insertAppartement(conn, 
    		new Appartement( Num_bi, Description, Loc_etage,  asc,  ce));
        } catch (SQLException|IllegalArgumentException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } 
        if (errorString != null) {
        	request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
		}else {
            response.sendRedirect(request.getContextPath() + "/createAppartement");
        }
 	}
 	}
 }