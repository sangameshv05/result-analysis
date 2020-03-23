package result.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import result.bean.teacher_info;
import result.connection.dbconnection;

public class teacherdao {
	
	Connection con=null;
	PreparedStatement ps,ps1,ps2;
	ResultSet rs,rs1,rs2;
	public teacherdao() {
		con=dbconnection.dbcon();
	}
	public ResultSet  classes(teacher_info si) {
		String code=si.getsubjectcode();
		try {
			 ps=con.prepareStatement("select distinct class from teacher where subject_code=?");
			ps.setString(1, code);
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
		
	}
	public ResultSet teacherresult(teacher_info si) {
		String code=si.getsubjectcode();
		
		String cn=si.getclassname();
		
		try {
			 ps1=con.prepareStatement("select t.*\r\n" + 
					"from teacher t\r\n" + 
					"where t.subject_code=? and t.class=?");
			ps1.setString(1, code);
		
			ps1.setString(2, cn);
			
			rs1=ps1.executeQuery();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
	return rs1;
		
	}
	public ResultSet studentresult(teacher_info ti) {
		String code=ti.getsubjectcode();
		String cn=ti.getclassname();
		try {
			 ps2=con.prepareStatement("select *,si.name from marks m,student_info si where m.subject_code=? and si.usn=m.usn and class=? group by m.usn");
			 ps2.setString(1, code);
			 ps2.setString(2, cn);
			 rs2=ps2.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs2;
	}
	public int updatedata(teacher_info ti) {
		String sc=ti.getsubjectcode();
		String pass=ti.getpassword();
		int rs = 0;
		 
		try {
			Statement s1=con.createStatement();
			if(sc!=null && pass!=null) {
				rs=s1.executeUpdate("delete from teacher where subject_code="+"'"+sc+"'"+ "and password="+"'"+pass+"'" );
				System.out.print("executed");
				
			}
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public int register(teacher_info ti) {
		int rs = 0;
		String sc=ti.getsubjectcode();
		 String tn=ti.getname();
		 String cn=ti.getclassname();
		 String password=ti.getpassword();
		 PreparedStatement ps=null;
		try {
			ps=con.prepareStatement("insert into teacher (subject_code,teacher_name,class,password) values (?,?,?,?)");
			ps.setString(1, sc);
			ps.setString(2, tn);
			ps.setString(3,cn );
			ps.setString(4, password);
			rs=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	public int updatemarks(teacher_info ti) {
		String cn=ti.getclassname();
		String su=ti.gets_usn();
		String eu=ti.gete_usn();
		String code=ti.getsubjectcode();
		int rs1 = 0;
		try {
			PreparedStatement p1=con.prepareStatement("update marks set class=? where usn between ? and ? and subject_code=?");
			p1.setString(1, cn);
			p1.setString(2, su);
			p1.setString(3, eu);
			p1.setString(4, code);
			rs1=p1.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs1;
	}
	public ResultSet teacher(teacher_info ti) {
		String sc=ti.getsubjectcode();
		String cn=ti.getclassname();
		PreparedStatement s;
		ResultSet rs = null;
		try {
			s = con.prepareStatement("select * from teacher where subject_code=? and class=?");
			s.setString(1,sc);
			s.setString(2, cn);
			rs=s.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return rs;
	}
	
	String g[]= {"p","f","a"};
		public static float percentage1 (int[]m) {
			float total=0;
			for(int i : m)
				total=total+i;
			total=total-m[m.length-1];
			
			float res = 0;
			res=(m[0]/total)*100;
			
		return res;
			
		}
		
		public boolean getresult(teacher_info ti) {
			String sc=ti.getsubjectcode();
			String cn=ti.getclassname();
			 try {
				 int m1=0;
			    	int m[]=new int[3];
				CallableStatement  st=con.prepareCall("{call analysis(?,?,?,?)}");
				    	
		    		for(String j : g) {
		    
						    st.setString(1,sc);
						    st.setString(2,j);
						    st.registerOutParameter(3, Types.INTEGER);
						    st.setString(4, cn);
						    st.execute();
						    int  k=st.getInt(3);
						    
						    m[m1++]=k;
		    				}
						    
						    float res=percentage1(m);
						    
						    PreparedStatement ps=con.prepareStatement("update teacher set pass=?,fail=?,absent=?,percentage=? where subject_code=? and class=?");
							
						    ps.setInt(1, m[0]);
						    ps.setInt(2, m[1]);
						    ps.setInt(3, m[2]);
						    ps.setFloat(4, res);
						    ps.setString(5, sc);
						    ps.setString(6, cn);
						    
						    ps.execute();
					    		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			return true;
			
		}
		
		public ResultSet pass(String sc,String g) {
			try {
				PreparedStatement ps=con.prepareStatement("select * from marks where grade=? and subject_code=? ");
				ps.setString(1,g);
			
				ps.setString(2, sc);
				ResultSet rs=ps.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return rs;
			
		}
}
