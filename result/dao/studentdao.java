package result.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import result.bean.student_info;
import result.connection.dbconnection;;

public class studentdao {
	Connection con=null;
	String usn,usn1;
	PreparedStatement st,ps;
	ResultSet rs,ps1;
	float a=0;
	int b=0,k,j;
	
	public studentdao() {
		con=dbconnection.dbcon();
	}
	
	public ResultSet  studentresult(student_info si) {
		usn=si.getusn();
		try {
			st=con.prepareStatement("select * from student_info where usn=?");
			st.setString(1,usn);
			rs=st.executeQuery();
			} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet studentresultsheet(student_info si) {
		usn1=si.getusn1();
		k=si.getk();
		j=si.getj();
		try {
			ps=con.prepareStatement("select *\r\n" + 
					"from marks m\r\n" + 
					"where usn=? and m.subject_code like ? group by usn");
				if(Integer.parseInt(usn1.substring(7))>=400 && (k==3 ||k==4) && j==0) {
				
				String x=Integer.toString(k);
				String y=Integer.toString(j);
				String z="__%DIP".concat(x).concat("1").concat("%");
				
			
				ps.setString(1,usn1);
				ps.setString(2,z);
				ps1=ps.executeQuery();
				
				}
				
				
				else {
					String y;
					if(Integer.parseInt(usn1.substring(7))>=400) {
						y=Integer.toString(j);
						
					}
					else {
						y=Integer.toString(++j);
					}
						
				String x=Integer.toString(k);
				String z="__%".concat(x).concat(y).concat("%");
				
			
				ps.setString(1,usn1);
				ps.setString(2,z);
				ps1=ps.executeQuery();
				}
			
				
			
			
			} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		return ps1;
		}
}
