package filter;
 
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import connection.ConnUtils;
import utils.MyUtils;
 
@WebFilter(filterName = "jdbcFilter", urlPatterns = { "/*" })
public class JDBCFilter implements Filter {
 
    public JDBCFilter() {
    }
  
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException, NullPointerException {
            Connection conn = null;
            try {
                conn = ConnUtils.getConnection();
                conn.setAutoCommit(false);
                MyUtils.storeConnection(request, conn);
                chain.doFilter(request, response);
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
                ConnUtils.rollbackQuietly(conn);
                throw new ServletException();
            } finally {
                ConnUtils.closeQuietly(conn);
            }
    } 
}