package result.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import result.bean.student_info;
import result.dao.admindao;

@WebServlet("/reval")
public class reval extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usn=request.getParameter("usn");
		String captcha=request.getParameter("captcha");
			
		student_info si=new student_info(usn,captcha);
		admindao ad=new admindao();
		ad.info(si);
		response.sendRedirect("index.html");
	}
}
