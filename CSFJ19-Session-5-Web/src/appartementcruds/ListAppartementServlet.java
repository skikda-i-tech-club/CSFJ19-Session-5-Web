package appartementcruds;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.Appartement;

@WebServlet("/listAppartement")
public class ListAppartementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListAppartementServlet() {
        super();
    }    

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String errorString = null;
        @SuppressWarnings("unchecked")
		List<Appartement> appartement = (List<Appartement>) request.getAttribute("appartement");
        request.setAttribute("errorString", errorString);
        request.setAttribute("appartement", appartement);
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/Appartement/listAppartementView.jsp");
        dispatcher.forward(request, response);		
	}
}