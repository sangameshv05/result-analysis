package result.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import result.bean.teacher_info;
import result.dao.teacherdao;


@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	teacher_info ti,ti1,ti2,ti3;
	teacherdao td,td1,td2,td3;
	int rs,rs1;
	ResultSet rs2;
	PrintWriter pw;
	String sc,cn;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
				
		pw=response.getWriter();
		response.setContentType("text/html");

		ti=new teacher_info();
		String sc1=request.getParameter("code");
		String pass=request.getParameter("pass");
		ti.setsubjectcode(sc1);
		ti.setpassword(pass);
		if(sc1!=null && pass!=null) {
				
		td=new teacherdao();
		rs=td.updatedata(ti);
				
		if(rs!=0) 
		{
			response.sendRedirect("register.html");
		}
		else {
			   pw.println("<meta http-equiv='refresh' content='3;URL=update.html'>");//redirects after 3 seconds
			   pw.println("<p style='color:red;'>User or password incorrect!</p>");
			  return;
			}
		}
	
		sc=request.getParameter("subject code");
		String tn=request.getParameter("teacher name");
		cn=request.getParameter("class name");
		String su=request.getParameter("s usn");
		String eu=request.getParameter("e usn");
		String password=request.getParameter("password");
			
		ti1=new teacher_info();
		ti1.setclassname(cn);
		ti1.setsubjectcode(sc);
		td1=new teacherdao();
		rs2=td1.teacher(ti1);
			
		if(rs2.next()) {
			pw.print("<script language='JavaScript'>alert('subject_code is already registerd');");
	        pw.println("window.location.href = "+"\"http://localhost:8080/Result/percentage.html;\"");
	        pw.println("</script>");
			return;
		}
				
		ti2=new teacher_info();
		ti2.setclassname(cn);
		ti2.sets_usn(su);
		ti2.sete_usn(eu);
		ti2.setsubjectcode(sc);
		td2=new teacherdao();
		rs1=td2.updatemarks(ti2);
				
		ti3=new teacher_info();
		ti3.setclassname(cn);
		ti3.setname(tn);
		ti3.setpassword(password);
		ti3.setsubjectcode(sc);
		td3=new teacherdao();
		td3.register(ti3);
		
		}
		catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.println("registration is successful");
		pw.println("<br>");
		
		pw.print("<a href=http://localhost:8080/Result/total_percentage?class_name="+cn +"&subject_code="+sc+">calculate result</a>");
		pw.print("<script language='JavaScript'>alert('REGISTRATION SUCCESSFUL');");
        pw.println("window.location.href = \"http://localhost:8080/Result/total_percentage?class_name="+cn +"&subject_code="+sc+"\"");
        pw.println("</script>");
		
	}
}
