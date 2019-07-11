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

@WebServlet("/editAppartement")
public class EditAppartementSerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditAppartementSerlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request); 
		String Num_Bi = request.getParameter("num_bi");
		System.out.println(Num_Bi);
        Appartement appartement = null;
        String errorString = null;
        try {
        	appartement = DBUtils.ConsulterAppartement(conn, Num_Bi);
        	
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        if (errorString != null) {
            response.sendRedirect(request.getServletPath() + "/listAppartement");
            return;
        }
        request.setAttribute("errorString", errorString);
        request.setAttribute("appartement", appartement);
 
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/Appartement/editAppartementView.jsp");
        dispatcher.forward(request, response);
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);
		String errorString = null; 
		Appartement appartement=null;
		boolean ce=false;
		boolean asc=false;
		String Description = request.getParameter("Description");
		int Loc_etage=Integer.valueOf(request.getParameter("Loc_etage"));
	    String Ascenseur = request.getParameter("Ascenseur");
	    String Cuisine_equipe = request.getParameter("Cuisine_equipe");
	    String Num_bi = request.getParameter("Num_bi");
	    
        
	    if(Cuisine_equipe.equals("true")) ce=true; else ce=false;
	    if(Ascenseur.equals("true")) asc=true; else asc=false;
	 
	    if(Description=="" || Loc_etage== 0|| Ascenseur=="" || Cuisine_equipe=="" || Num_bi=="") {
	    	errorString="Un ou plusieurs champs vide !";
	    	request.setAttribute("errorString", errorString);
	        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/Appartement/editAppartementView.jsp");
			dispatcher.forward(request, response);
	    }else {	 
	    	try {
	    		appartement=
	    		new Appartement( Num_bi, Description, Integer.valueOf(Loc_etage),  asc,  ce);
	    		DBUtils.updateAppartement(conn, appartement);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        request.setAttribute("errorString", errorString);
        request.setAttribute("appartement", appartement);
 
        if (errorString != null) {
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher 
            = this.getServletContext().getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/listAppartement");
        }
    }
}}