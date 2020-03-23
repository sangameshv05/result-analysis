package result.bean;

public class student_info {
	String USN,name,result, usn,usn1,revalusn,captcha;
	int j,k,revalsemester;
	int semester;
	float sgpa,percentage;
	public student_info() {
		
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public student_info(String usn,String name,int semester ,float sgpa,float percentage,String result) {
		this.USN=usn;
		this.name=name;
		this.semester=semester;
		this.sgpa=sgpa;
		this.percentage=percentage;
		this.result=result;
	}
	
	public String getUSN() {return USN;}
	public String getname() {return name;}
	public int getsemester() {return semester;}
	public float getsgpa() {return sgpa;}
	public float getpercentage() {return percentage;}
	public String getresult() {return result;}
	
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void setusn(String usn) {
		this.usn=usn;
	}
	public String getusn() {
		return usn;
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public student_info(String usn,int k,int j) {
		usn1=usn;
		this.k=k;
		this.j=j;
		}
	public String getusn1() {
		return usn1;
	}
	public int getk() {
		return k;
	}
	public int getj() {
		return j;
	}

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	String sc,sn,g;
	int in,ex,tot,credit,point,gp;
	
	
	public student_info(String sc,String sn,int in ,int ex,int tot,int credit,int point ,int gp,String g) {
		this.sc=sc;
		this.sn=sn;
		this.in=in;
		this.ex=ex;
		this.tot=tot;
		this.credit=credit;
		this.point=point;
		this.gp=gp;
		this.g=g;
	}
	public int getin(){
		return in;
	}
	public int getex(){
		return ex;
		}
	public int gettot(){
		return tot;
	}
	public int getcredit(){
		return credit;
	}
	public int getpoint(){
		return point;
	}
	public int getgp(){
		return gp;
	}
	public String getsc(){
		return sc;
	}
	public String getsn(){
		return sn;
	}public String getg(){
		return g;
	}
	
	public student_info(String usn,String captcha) {
		this.revalusn=usn;
		
		
		this.captcha=captcha;
		
		
		
	}
	
	public String getrevalusn() {
		return revalusn;
	}
	
	public String getcaptcha() {
		return captcha;
	}
	
	public void setrevalsemester(int semester) {
		this.revalsemester=semester;
	}
	public int getrevalsemester() {
		return revalsemester;
	}
	String name1;
	public void setname1(String name) {
		 name1=name;
	}
	
	public String getname1() {
		return name1;
	}
	
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------
}
