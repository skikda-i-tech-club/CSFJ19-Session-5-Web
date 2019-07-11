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
import utils.DBUtils;
import utils.MyUtils;

@WebServlet("/deleteAppartement")
public class DeleteAppartementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteAppartementServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);
		 
        String Num_Bi = request.getParameter("num_bi");
        String errorString = null;
        try {
            DBUtils.deleteAppartement(conn, Num_Bi);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } 
         
        if (errorString != null) {
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/listAppartement");
        }
	}
}