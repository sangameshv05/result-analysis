<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%> 
<%@page import=" result.bean.teacher_info" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
body{
background-image: url("smarks.jpg");
background-repeat: no-repeat;
background-size: cover;

}

.t1{
	margin:2em 25em;
	border-collapse: collapse;
	width: 700px;
	text-align: center;
}
.t2{
margin:2em 15em;
	border-collapse: collapse;
	width: 900px;
	text-align: center;
}

td,th{
border-bottom: thin solid;

}
a{
margin:2em;
padding: 16px 36px;
background-image:   url("smarks.jpg");
text-decoration: none;
}
a:hover {

	background-color: white;
	
}

</style>
</head>

<body>
<a href="percentage.html">BACK</a>
<table class="t1">
<tr>
<th>SUBJECT CODE</th>
<th>TEACHER NAME</th>
<th>CLASS</th>
<th>PASS</th>
<th>FAIL</th>
<th>ABSENT</th>
<th>PERCENTAGE</th>
</tr>
<%ArrayList<teacher_info> rs=(ArrayList<teacher_info>)request.getAttribute("teacher") ;
for(teacher_info s:rs){%>
<tr>
	<td><%=s.getsc() %></td>
	<td><%=s.gettn() %></td>
	<td><%=s.getc() %></td>
	<td><%=s.getp() %></td>
	<td><%=s.getf() %></td>
	<td><%=s.geta() %></td>
	<td><%=s.getpr() %></td>
</tr>
	
	<%}%>
</table>

<table class="t2" align="center">
<tr>
<th>USN</th>
<th>STUDENT NAME</th>
<th>INTERNAL</th>
<th>EXTERNAL</th>
<th>TOTAL</th>
<th>GRADE</th>
</tr>
<%ArrayList<teacher_info> rs1=(ArrayList<teacher_info>)request.getAttribute("student") ;
for(teacher_info s:rs1){%>

<tr >
	<td><%=s.getusn() %></td>
	<td><%=s.getname1() %></td>
	<td><%=s.getin() %></td>
	<td><%=s.getex() %></td>
	<td><%=s.gettot() %></td>
	
	<td><%=s.getgrade() %></td>
</tr>
	<%}%>



</table>
</body>
</html>