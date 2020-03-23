package result.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import result.bean.teacher_info;
import result.dao.teacherdao;


@WebServlet("/analysis")
public class analysis extends HttpServlet {
	private static final long serialVersionUID = 1L;
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		ArrayList<teacher_info> al=new ArrayList<teacher_info>();
		ArrayList<teacher_info> al1=new ArrayList<teacher_info>();
		PrintWriter pw=response.getWriter();
		ResultSet rs,rs1,rs2;
		teacher_info ti,ti1,ti2;
		teacherdao td,td1,td2;
		try {			
			String s1=request.getParameter("code");
			
			ti=new teacher_info();
			ti.setsubjectcode(s1);
			
			td=new teacherdao();
			rs=td.classes(ti);
							
			if(rs.next()==false)
			{
				pw.print("<script language='JavaScript'>alert('REGISTER SUBJECT CODE');");
		        pw.println("window.location.href = "+"\"http://localhost:8080/Result/percentage.html;\"");
		        pw.println("</script>");
			}
			else {
				do {
					ti1=new teacher_info();
					ti1.setclassname(rs.getString(1));
					ti1.setsubjectcode(s1);
					
					td1=new teacherdao();
					rs1=td1.teacherresult(ti1);
					
					if(!rs1.next()) 
					{
						
						pw.print("no data");
					}
			
				do{
					al.add(new teacher_info(rs1.getString(1),rs1.getString(2),rs1.getString(3),rs1.getInt(4),rs1.getInt(5),rs1.getInt(6),rs1.getFloat(7)));
					
					ti2=new teacher_info();
					ti2.setclassname(rs.getString(1));
					ti2.setsubjectcode(s1);
					td2=new teacherdao();
					rs2=td2.studentresult(ti2);		
							
				}while(rs1.next()) ;
				
				while(rs2.next()) 
				{
					al1.add(new teacher_info(rs2.getString(1),rs2.getString(2),rs2.getString(3),rs2.getInt(4),rs2.getInt(5),rs2.getInt(6),rs2.getString(10),rs2.getString(14)));

				}
			}while(rs.next());
				request.setAttribute("teacher",al);
				request.setAttribute("student",al1);
				 RequestDispatcher  rd= request.getRequestDispatcher("analysis.jsp"); 
				 rd.forward(request, response);
		}
	}
			catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
}


	
	


