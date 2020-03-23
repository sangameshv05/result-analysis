package result.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import result.bean.student_info;
import result.connection.dbconnection;

/**
 * Servlet implementation class class_result
 */
@WebServlet("/class_result")
public class class_result extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList< student_info> al=new ArrayList< student_info>();
		Connection con=dbconnection.dbcon();
		int cn=Integer.parseInt(request.getParameter("year"));
		int sem=Integer.parseInt(request.getParameter("sem"));

		ResultSet rs1;
		try {
			PreparedStatement ps=con.prepareStatement("select usn from marks where year=? group by usn");
			ps.setInt(1, cn);
			rs1=ps.executeQuery();
			String result;
			
			PreparedStatement ps1=con.prepareStatement("select * from student_info where usn=? and semester=?");
				
		while(rs1.next()) {
			ps1.setString(1,rs1.getString(1));
			ps1.setInt(2,sem);
			ResultSet rs=ps1.executeQuery();	
			if(rs.next()) {
				result="<a href="+"http://localhost:8080/Result/Result?sem="+ rs.getInt(3)+ "&usn="+rs.getString(1)+ "&marks="+rs.getInt(5)+ "&credit="+rs.getInt(4)+" >"+"result"+"</a>";    
				al.add(new student_info(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getFloat(6), rs.getFloat(7),result ));
		}
		}
			 request.setAttribute("data", al);
			 RequestDispatcher  rd= request.getRequestDispatcher("studentresult.jsp"); 
			   rd.forward(request, response); 
			
		
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

}
