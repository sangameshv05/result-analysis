package result.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import result.bean.student_info;
//import result.bean.studentresultset;
import result.dao.studentdao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	String usn,result;
	ResultSet rs;
	PrintWriter pw;
	
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   ArrayList<student_info> al=new ArrayList<student_info>();
	   pw=response.getWriter();

	   student_info si=new student_info();
	   usn=request.getParameter("usn");
	   si.setusn(usn);
	   studentdao sd=new studentdao();
	   rs=sd.studentresult(si);
	   try {
		if(rs.next()==false) {
			pw.print("<script language='JavaScript'>alert('invalid usn');");
	        pw.println("window.location.href = "+"\"http://localhost:8080/Result/index.html;\"");
	        pw.println("</script>");
			  
		   }
		else
		{
		do{
			result="<a href="+"http://localhost:8080/Result/Result?sem="+ rs.getInt(3)+ "&usn="+rs.getString(1)+ "&marks="+rs.getInt(5)+ "&credit="+rs.getInt(4)+" >"+"result"+"</a>";    
			al.add(new student_info(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getFloat(6), rs.getFloat(7),result ));
		}while(rs.next());
		 request.setAttribute("data", al);
		 RequestDispatcher  rd= request.getRequestDispatcher("studentresult.jsp"); 
		   rd.forward(request, response); 
		}
		
	}
	   catch (SQLException  e) {
		
		e.printStackTrace();
	}
	  

	}
}
