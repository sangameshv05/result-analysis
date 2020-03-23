package result.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import result.connection.dbconnection;


@WebServlet("/subjectcode")
public class subjectcode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		ArrayList<String> ar=new ArrayList<String>();
		Connection con=dbconnection.dbcon();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select subject_code from subject_info");
		PreparedStatement ps = con.prepareStatement("update subject_info set credit=? where subject_code=?");

		Enumeration<String> code= request.getParameterNames();
		while(code.hasMoreElements()) {
			String code1=request.getParameter(code.nextElement());
			ar.add(code1);
		}
				
		int i=1;
		String code1;
		String  credit;
			while (rs.next()) {
				
				code1 = rs.getString(1);
				
				credit = ar.get(i);
				ps.setInt(1,Integer.parseInt(credit));
				
				ps.setString(2, rs.getString(1));
				ps.execute();
				i+=2;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
