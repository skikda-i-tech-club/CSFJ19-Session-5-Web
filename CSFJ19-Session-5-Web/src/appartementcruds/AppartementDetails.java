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

@WebServlet("/AppDet")
public class AppartementDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AppartementDetails() {
        super();
    }

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
 
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/Appartement/appartementView.jsp");
        dispatcher.forward(request, response);
	}
}