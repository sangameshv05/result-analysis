package result.dao;

import java.sql.*;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import result.bean.admin_info;
import result.bean.student_info;
import result.connection.dbconnection;

//-----------------------------CONNECTION AND ADMIN VALIDATION-------------------------------------------------------------------------------------------------

public class admindao {
	static Connection con = null;
	static Scanner sc = new Scanner(System.in);
	
	static String revalusn;
	static String revalcaptcha;

	public admindao() {
		con = dbconnection.dbcon();
	}

	public ResultSet check(admin_info ai) {
		String uname = ai.getname();
		String pass = ai.getpassword();
		ResultSet rs = null;

		try {
			Statement st = con.createStatement();
			rs = st.executeQuery("select * from admin where name='" + uname + "'and password='" + pass + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;
	}
	
	
	//-------------------------ADD NEW RESULT----------------------------------------------------------------------------

	public void result() throws SQLException, InterruptedException {
			
		int p = 0,i = 0;
		String j2, USN;
		List<WebElement> a = null;

		System.out.println("enter semester");
		int semester = sc.nextInt();

		System.out.println("enter usn");
		String usn = sc.next();

		int m = Integer.parseInt(usn.substring(7));
		String j1 = usn.substring(0, 7);
		p++;

		System.out.println("total number of students");
		int t = sc.nextInt();

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Sangamesh V\\Downloads\\app\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		if (semester % 2 == 0)
			driver.get("https://results.vtu.ac.in/_CBCS/index.php");
		else
			driver.get("https://results.vtu.ac.in/_CBCS/index.php");

		PreparedStatement st1 = con.prepareStatement("insert into student_info (usn,name,semester) values (?,?,?)");
		PreparedStatement st = con.prepareStatement("insert into marks (usn,subject_code,subject_name,internal,external,total,grade) values(?,?,?,?,?,?,?)");
		PreparedStatement st2 = con.prepareStatement("insert into subject_info(subject_code,subject_name,semester) values (?,?,?)");
		PreparedStatement st4 = con.prepareStatement("select * from subject_info where subject_code=?");
		PreparedStatement ps = con.prepareStatement("update marks set external=?,total=?,grade=? where usn=? and subject_code=?");


		do {
			try {

				System.out.println("Enter CAPTCHA : ");
				String captcha = sc.next();

				j2 = String.format("%03d", m);
				USN = j1 + j2;
				m++;

				driver.findElement(By.name("lns")).sendKeys(USN);
				TimeUnit.SECONDS.sleep(10);

				a = driver.findElements(By.className("divTableCell"));
				List<WebElement> we = driver.findElements(By.className("col-md-12"));

				String na = we.get(4).getText();
				String name = na.substring(na.lastIndexOf(':') + 1);

				String code = a.get(6).getText();

				int sem = Integer.parseInt(code.substring(code.length() - 2, code.length() - 1));

				st1.setString(1, USN);
				st1.setString(2, name);
				st1.setInt(3, sem);

				st1.execute();

				for ( i = 6; i < a.size() - 5; i++) {
					if (i == a.size()-5)
						break;

					st.setString(1, USN);
					ps.setString(4, USN);

					st.setString(2, a.get(i).getText());
					ps.setString(5,a.get(i).getText() );
					st2.setString(1, a.get(i).getText());
					st4.setString(1, a.get(i).getText());

					st.setString(3, a.get(++i).getText());
					st2.setString(2, a.get(i).getText());

					try {
						st.setInt(4, Integer.parseInt(a.get(++i).getText()));
					} 
					
					catch (NumberFormatException e) {
						i=i+3;
						continue;
					}

					st.setInt(5, Integer.parseInt(a.get(++i).getText()));
					
					st.setInt(6, Integer.parseInt(a.get(++i).getText()));
					
					st.setString(7, (a.get(++i).getText()));
					
					st2.setInt(3, sem);
					

					ResultSet rs4 = st4.executeQuery();
					try {
					if (rs4.next()) {
						st.execute();
					} else {
						st2.execute();
						st.execute();
					}
					}
					catch(java.sql.SQLIntegrityConstraintViolationException ex) {
						i=i-2;
						ps.setInt(1, Integer.parseInt(a.get(i).getText()));
						ps.setInt(2, Integer.parseInt(a.get(++i).getText()));
						ps.setString(3, (a.get(++i).getText()));
						ps.execute();
						continue;
						
					}
				}
				driver.navigate().back();

				while (t != p) {
					j2 = String.format("%03d", m);
					USN = j1 + j2;
					m++;
					p++;
					TimeUnit.SECONDS.sleep(1);

					driver.findElement(By.name("lns")).sendKeys(USN);

					driver.findElement(By.name("captchacode")).sendKeys(captcha);
					TimeUnit.SECONDS.sleep(2);

					a = driver.findElements(By.className("divTableCell"));
					List<WebElement> we1 = driver.findElements(By.className("col-md-12"));

					String na1 = we1.get(4).getText();
					String name1 = na1.substring(na1.lastIndexOf(':') + 1);

					String code1 = a.get(6).getText();

					int sem1 = Integer.parseInt(code1.substring(code1.length() - 2, code1.length() - 1));

					st1.setString(1, USN);
					st1.setString(2, name1);
					st1.setInt(3, sem1);
					st1.execute();

					for ( i = 6; i < a.size() - 5; i++) {
						if (i == a.size()-5)
							break;

						st.setString(1, USN);
						ps.setString(4, USN);
						st.setString(2, a.get(i).getText());
						ps.setString(5,a.get(i).getText() );
						st2.setString(1, a.get(i).getText());
						st4.setString(1, a.get(i).getText());

						st.setString(3, a.get(++i).getText());
						st2.setString(2, a.get(i).getText());

						try {
							st.setInt(4, Integer.parseInt(a.get(++i).getText()));
						} catch (NumberFormatException e) {
							i=i+3;
							continue;
						}
						st.setInt(5, Integer.parseInt(a.get(++i).getText()));
						
						st.setInt(6, Integer.parseInt(a.get(++i).getText()));
						
						st.setString(7, (a.get(++i).getText()));
						

						st2.setInt(3, sem1);
						
						ResultSet rs4 = st4.executeQuery();
						try {
						if (rs4.next()) {
							st.execute();
						} else {
							st2.execute();
							st.execute();
						}
						}catch(java.sql.SQLIntegrityConstraintViolationException ex) {
							i=i-2;
							ps.setInt(1, Integer.parseInt(a.get(i).getText()));
							ps.setInt(2, Integer.parseInt(a.get(++i).getText()));
							ps.setString(3, (a.get(++i).getText()));
							ps.execute();
							continue;
							
						}
					}

					driver.navigate().back();
					}
			} catch (org.openqa.selenium.UnhandledAlertException ex) {
				p++;
				continue;
			}
			

		} while (t > p);
		driver.close();
	}
	public void info(student_info si) {
		revalusn=si.getrevalusn();
		revalcaptcha=si.getcaptcha();		
	}
	
	public void reval() {
		
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Sangamesh V\\Downloads\\app\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		PreparedStatement ps = null;
			
			try {
				ps = con.prepareStatement("update marks set external=?,total=?,grade=? where usn=? and subject_code=?");
							
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
//						Dimension d=new Dimension(-800,850);
//						driver.manage().window().setSize(d);
						driver.get("https://results.vtu.ac.in/vitavicbcsrevaljj19/index.php");
						
						System.setProperty("webdriver.chrome.driver","C:\\Users\\Sangamesh V\\Downloads\\app\\chromedriver_win32\\chromedriver.exe");
						
						WebDriver driver1 = new ChromeDriver();
//						Dimension d1=new Dimension(800,850);
//						driver1.manage().window().setSize(d1);
						driver1.get("http://localhost:8080/Result/reval.html");
						
						TimeUnit.SECONDS.sleep(30);
								
						driver.findElement(By.name("lns")).sendKeys(revalusn);
						TimeUnit.SECONDS.sleep(1);
						driver.findElement(By.name("captchacode")).sendKeys(revalcaptcha);
						driver.findElement(By.id("submit")).click();
						
						List<WebElement> a = driver.findElements(By.className("divTableCell"));
						
						
						for (int i = 6; i < a.size() - 5; i++) {
							if (i == a.size() - 5)
								break;
							ps.setString(4, revalusn);
							ps.setString(5, a.get(i).getText());
							try {
								i=i+2;
								ps.setInt(1, Integer.parseInt(a.get(i).getText()));
							}
							catch (NumberFormatException e) {
								i=i+2;
								continue;
							}
							int total =Integer.parseInt(a.get(i).getText()) ;
							i=i+2;
							total=total+Integer.parseInt(a.get(i).getText());
							ps.setInt(2, total);
							ps.setString(3, a.get(++i).getText());
							
							ps.execute();
						}
						driver.close();
					}
							
				catch (SQLException | InterruptedException |org.openqa.selenium.UnhandledAlertException ex) {
								ex.printStackTrace();
				}
			}
	
	//-------------------------------------------------SGPA CALCULATION -------------------------------------------------------------------------------------

	
	static int credit1[];
	static int gp[];
	static int tgp[];
	static int gd, tot, marks, totmarks;

	static void sgpa(ResultSet rs2, int t) {

		int credit;
		float sgpa1 = 0;
		gd = 0;
		tot = 0;
		totmarks = 0;
		try {
			if (!rs2.next()) {

				System.out.println("no data");
			} else {
				credit1 = new int[t];
				gp = new int[t];
				tgp = new int[t];
				int i = 0;

				do{
					
					try {
						credit = rs2.getInt(1);
						credit1[i] = credit;
						marks = rs2.getInt(2);
						totmarks += marks;

						tot = tot + credit;
						if( rs2.getString(4).contains("KK")||rs2.getString(4).contains("CPH")) 
						//{
						//if(credit==1)
							marks = marks * 2;
						//}
						if(credit==0) {
							gp[i]=0;
							tgp[i]=0;
							continue;
						}
							
						if (rs2.getString(3).equals("F") || rs2.getString(3).equals("A") || rs2.getString(3).equals("X")) {
							
							gp[i] = 0;
							
							tgp[i] = 0;
							i++;
							continue;
						}
						if (marks > 0 && marks < 10) {
							gd = gd + 1 * credit;
							gp[i] = 1;
							tgp[i] = gp[i] * credit1[i];
							i++;
						} else if (marks >= 10 && marks < 20) {
							gd = gd + 2 * credit;
							gp[i] = 2;
							tgp[i] = gp[i] * credit1[i];
							i++;
						} else if (marks >= 20 && marks < 30) {
							gd = gd + 3 * credit;
							gp[i] = 3;
							tgp[i] = gp[i] * credit1[i];
							i++;
						} else if (marks >= 30 && marks < 40) {
							gd = gd + 4 * credit;
							gp[i] = 4;
							tgp[i] = gp[i] * credit1[i];
							i++;
						} else if (marks >= 40 && marks < 50) {
							gd = gd + 5 * credit;
							gp[i] = 5;
							tgp[i] = gp[i] * credit1[i];
							i++;
						} else if (marks >= 50 && marks < 60) {
							gd = gd + 6 * credit;
							gp[i] = 6;
							tgp[i] = gp[i] * credit1[i];
							i++;
						} else if (marks >= 60 && marks < 70) {
							gd = gd + 7 * credit;
							gp[i] = 7;
							tgp[i] = gp[i] * credit1[i];
							i++;
						} else if (marks >= 70 && marks < 80) {
							gd = gd + 8 * credit;
							gp[i] = 8;
							tgp[i] = gp[i] * credit1[i];
							i++;
						} else if (marks >= 80 && marks < 90) {
							gd = gd + 9 * credit;
							gp[i] = 9;
							tgp[i] = gp[i] * credit1[i];
							i++;
						} else if (marks >= 90 && marks <= 100) {
							gd = gd + 10 * credit;
							gp[i] = 10;
							tgp[i] = gp[i] * credit1[i];
							i++;
						}
						
					} catch (NumberFormatException | SQLException e) {

						e.printStackTrace();
					}
				} while (rs2.next());
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
//		return sgpa1;
	}
	
	public void sgpa1() {
		try {
			ResultSet rs1;
			Statement st1 = con.createStatement();
			PreparedStatement st2 = con.prepareStatement(
					"update student_info set total_grade_points=?,total_marks=? ,total_credits=? where usn=? and semester=?");
			ResultSet rs = st1.executeQuery("select usn,semester from student_info");
			PreparedStatement st = con.prepareStatement(
					"select s.credit,total,grade,m.subject_code\r\n" + "from marks m,subject_info s,student_info si\r\n"
							+ "where m.subject_code=s.subject_code and m.usn=? and si.semester=? and s.semester=?\r\n"
							+ "group by m.subject_code");
			PreparedStatement ps1 = con.prepareStatement(
					("update marks set credit=?,point=?,grade_point=? where usn=? and subject_code=?"));
			PreparedStatement st3 = con.prepareStatement(
					"select count(distinct m.subject_code) from marks m,subject_info si where m.subject_code=si.subject_code and si.semester=? and usn=?");
			if (!rs.next()) {
				System.out.println("no data");
			} else {
				int t = 0;
				
				do {
					st3.setInt(1, rs.getInt(2));
					st3.setString(2, rs.getString(1));
					ResultSet rs3 = st3.executeQuery();
					if (rs3.next()) 
						t = rs3.getInt(1);

					st.setString(1, rs.getString(1));
					st.setInt(2, rs.getInt(2));
					st.setInt(3, rs.getInt(2));

					rs1 = st.executeQuery();

					sgpa(rs1, t);
					
					for (int i = credit1.length-1; i >= 0; i--) {
//						if (credit1[i] == 0)
//							continue;
						ps1.setInt(1, credit1[i]);
						ps1.setInt(2, gp[i]);
						ps1.setInt(3, tgp[i]);
						ps1.setString(4, rs.getString(1));

						if (rs1.previous()) {
							ps1.setString(5, rs1.getString(4));
						}
						ps1.execute();
					}
					st2.setInt(1, gd);
					st2.setInt(2, totmarks);
					st2.setFloat(3, tot);
					st2.setString(4, rs.getString(1));
					st2.setInt(5, rs.getInt(2));

					st2.execute();
				} while (rs.next());
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	//-----------------------------------------ADD SUBJECT CODE-------------------------------------------------------------------------------------------------

	public void addcredits() throws SQLException {
		Dictionary<String, Integer> sc = new Hashtable<String, Integer>();
		sc.put("17MAT31", 4);
		sc.put("17MAT41", 4);
		sc.put("17CS32", 4);
		sc.put("17CS33", 4);
		sc.put("17CS34", 4);
		sc.put("17CS35", 3);
		sc.put("17CS36", 4);
		sc.put("17CSL37", 2);
		sc.put("17CSL38", 2);
		sc.put("17CS42", 3);
		sc.put("17CS43", 4);
		sc.put("17CS44", 4);
		sc.put("17CS45", 4);
		sc.put("17CS46", 4);
		sc.put("17CSL47", 2);
		sc.put("17CSL48", 2);
		sc.put("17CED24", 4);
		sc.put("17CHE22", 4);
		sc.put("17CIV13", 4);
		sc.put("17CPH49", 1);
		sc.put("17ELE15", 4);
		sc.put("17KKK39", 1);
		sc.put("17KKM39", 1);
		sc.put("17PCD23", 4);
		sc.put("17MAT21", 4);
		
		sc.put("15MAT31", 4);
		sc.put("15MAT41", 4);
		sc.put("15CS32", 4);
		sc.put("15CS33", 4);
		sc.put("15CS34", 4);
		sc.put("15CS35", 3);
		sc.put("15CS36", 4);
		sc.put("15CSL37", 2);
		sc.put("15CSL38", 2);
		sc.put("15CS42", 3);
		sc.put("15CS43", 4);
		sc.put("15CS44", 4);
		sc.put("15CS45", 4);
		sc.put("15CS46", 4);
		sc.put("15CSL47", 2);
		sc.put("15CSL48", 2);
		
		sc.put("15CS51", 4);
		sc.put("15CS52", 4);
		sc.put("15CS53", 4);
		sc.put("15CS54", 4);
		sc.put("15CS551", 3);
		sc.put("15CS552", 3);
		sc.put("15CS553", 3);
		sc.put("15CS554", 3);
		sc.put("15CS561", 3);
		sc.put("15CS562", 3);
		sc.put("15CS563", 3);
		sc.put("15CS564", 3);
		sc.put("15CS565", 3);
		sc.put("15CSL57", 2);
		sc.put("15CSL58", 2);
		
		sc.put("15CS61", 4);
		sc.put("15CS62", 4);
		sc.put("15CS63", 4);
		sc.put("15CS64", 4);
		sc.put("15CS651", 3);
		sc.put("15CS652", 3);
		sc.put("15CS653", 3);
		sc.put("15CS654", 3);
		sc.put("15CS661", 3);
		sc.put("15CS662", 3);
		sc.put("15CS663", 3);
		sc.put("15CS664", 3);
		sc.put("15CS665", 3);
		sc.put("15CS666", 3);
		sc.put("15CSL67", 2);
		sc.put("15CSL68", 2);
		
		sc.put("17CS51", 4);
		sc.put("17CS52", 4);
		sc.put("17CS53", 4);
		sc.put("17CS54", 4);
		sc.put("17CS551", 3);
		sc.put("17CS552", 3);
		sc.put("17CS553", 3);
		sc.put("17CS554", 3);
		sc.put("17CS561", 3);
		sc.put("17CS562", 3);
		sc.put("17CS563", 3);
		sc.put("17CS564", 3);
		sc.put("17CS565", 3);
		sc.put("17CSL57", 2);
		sc.put("17CSL58", 2);
		
		sc.put("18MAT11", 4);
		sc.put("18MAT21", 4);
		sc.put("18PHY12", 4);
		sc.put("18PHY22", 4);
		sc.put("18ELE13", 3);
		sc.put("18ELE23", 3);
		sc.put("18CIV14", 3);
		sc.put("18CIV24", 3);
		sc.put("18EGDL15", 3);
		sc.put("18EGDL25", 3);
		sc.put("18PHYL16", 1);
		sc.put("18PHYL26", 1);
		sc.put("18ELEL17", 1);
		sc.put("18ELEL27", 1);
		sc.put("18EGH18", 1);
		sc.put("18CHE12", 4);
		sc.put("18CHE22", 4);
		sc.put("18CPS13", 3);
		sc.put("18CPS23", 3);
		sc.put("18ELN14", 3);
		sc.put("18ELN24", 3);
		sc.put("18ME15", 3);
		sc.put("18ME25", 3);
		sc.put("18CHEL16", 1);
		sc.put("18CHEL26", 1);
		sc.put("18CPL17", 1);
		sc.put("18CPL27", 1);
		sc.put("18EGH28", 1);
		
		sc.put("17MATDIP31",0);
		sc.put("17MATDIP41",0);

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select subject_code from subject_info");
		PreparedStatement ps = con.prepareStatement("update subject_info set credit=? where subject_code=?");
		String code;
		int credit;
		while (rs.next()) 
		{
			code = rs.getString(1);
			credit = (int) sc.get(code);
			ps.setInt(1, credit);
			ps.setString(2, rs.getString(1));
			ps.execute();
		}

	}

}
