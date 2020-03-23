package result.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbconnection {
	public static Connection con = null;
	public static Connection dbcon() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			
		    }
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			
			e.printStackTrace();
		}
		return con;
	}
}
