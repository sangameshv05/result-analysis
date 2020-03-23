package result.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import result.bean.student_info;
import result.dao.studentdao;


@WebServlet("/Result")
public class Result extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		   pw.println("<!DOCTYPE html>"); 
	       pw.println("<html>"); 
	       pw.println("<body>");
		ResultSet r = null;
		ArrayList<student_info> al=new ArrayList<student_info>();
		try {
			int k=Integer.parseInt(request.getParameter("sem"));
			String usn=request.getParameter("usn");
			response.setContentType("text/html");
			request.setAttribute("usn", usn);
						
			for(int j=0;j<10;j++) 
			{
				student_info si=new student_info(usn,k,j);
				studentdao sd=new studentdao();
				r=sd.studentresultsheet(si);
				if(r.next()) {
					do {

						al.add(new student_info(r.getString(2),r.getString(3),r.getInt(4),r.getInt(5),r.getInt(6),r.getInt(7),r.getInt(8),r.getInt(9),r.getString(10)));
						
					}while(r.next());
				}
			
				
				else {
					continue;
				}
			}
			 request.setAttribute("result", al);
			 RequestDispatcher  rd= request.getRequestDispatcher("result.jsp"); 
			 request.setAttribute("marks",request.getParameter("marks"));
			 request.setAttribute("credit",request.getParameter("credit"));
				//request.setAttribute("resultset",r);
			 rd.forward(request, response);
			 pw.println("</body>"); 
	         pw.println("</html>"); 
				
	}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	}

}
