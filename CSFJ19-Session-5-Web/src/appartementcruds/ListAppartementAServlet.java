package appartementcruds;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Appartement;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet("/listAppartementA")
public class ListAppartementAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListAppartementAServlet() {
        super();
    }    

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);		 
        String errorString = null;
        List<Appartement> appartement = null;
        try {        	
        	appartement = DBUtils.ConsulterListeAppartement(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        request.setAttribute("errorString", errorString);
        request.setAttribute("appartement", appartement);
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/Appartement/listAppartementView.jsp");
        dispatcher.forward(request, response);		
	}
}