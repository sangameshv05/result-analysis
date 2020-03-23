package result.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import result.dao.admindao;

/**
 * Servlet implementation class newresult
 */
@WebServlet("/revalresult")
public class revalresult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		admindao ad=new admindao();
		
		try {
			
			ad.reval();
			ad.addcredits();
			ad.sgpa1();
			response.sendRedirect("index.html");
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  		
	}
}
