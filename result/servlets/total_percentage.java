package result.servlets;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import result.bean.teacher_info;
import result.dao.teacherdao;


@WebServlet("/total_percentage")
public class total_percentage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			teacher_info ti=new teacher_info();
			teacherdao td=new teacherdao();
			
			String cn=request.getParameter("class_name");
			
			ti.setclassname(cn);
			String sc=request.getParameter("subject_code");
			
			ti.setsubjectcode(sc);
			td.getresult(ti);
			
			response.sendRedirect("percentage.html");
		}
}


