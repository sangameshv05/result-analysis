package result.bean;

public class teacher_info {
	
	public teacher_info() {
		
	}
	String subjectcode,name,classname,s_usn,e_usn,year,password;
	public void setsubjectcode(String subjectcode) {
		this.subjectcode=subjectcode;
	}
	public void setname(String name) {
		this.name=name;
	}
	public void setclassname(String classname) {
		this.classname=classname;
	}
	public void sets_usn(String s_usn) {
		this.s_usn=s_usn;
	}
	public void sete_usn(String e_usn) {
		this.e_usn=e_usn;
	}
	public void setyear(String year) {
		this.year=year;
	}
	public void setpassword(String password) {
		this.password=password;
	}
	public String getsubjectcode() {
		return subjectcode;
	}
	public String getname() {
		return name;
	}
	public String getclassname(){
		return classname;
	}
	public String gets_usn() {
		return s_usn;
	}
	public String gete_usn() {
		return e_usn;
	}
	public String getyear() {
		return year;
	}
	public String getpassword() {
		return password;
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------------
	String sc,tn,c;
	int p,f,a;
	float pr;
	public teacher_info(String sc,String tn,String c,int p,int f,int a,float pr) {
		this.sc=sc;
		this.tn=tn;
		this.c=c;
		this.p=p;
		this.f=f;
		this.a=a;
		this.pr=pr;
	}
	public String getsc(){
		return sc;
	}
	public String gettn(){
		return tn;
	}
	public String getc(){
		return c;
	}
	public int getp(){
		return p;
	}
	public int getf(){
		return f;
	}
	public int geta(){
		return a;
	}
	public float getpr(){
		return pr;
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------------
	String usn,sc1,sn,grade,name1;
	int in,ex,tot;
	public teacher_info(String usn,String sc,String sn,int in,int ex,int tot,String grade,String name) {
		this.usn=usn;
		this.sc1=sc;
		this.sn=sn;
		this.in=in;
		this.ex=ex;
		this.tot=tot;
		this.grade=grade;
		name1=name;
	}
	public String getusn() {
		return usn;
	}
	public String getsc1() {
		return sc1;
	}
	public String getsn() {
		return sn;
	}
	public String getgrade() {
		return grade;
	}
	public int getin() {
		return in;
	}
	public int getex() {
		return ex;
	}
	public int gettot() {
		return tot;
	}
	public String getname1() {
		return name1;
	}
}
