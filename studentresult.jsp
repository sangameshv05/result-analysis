<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="java.util.ArrayList"%> 
<%@page import=" result.bean.student_info" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">

body{
	background: url(smarks.jpg) no-repeat center fixed; 
  	background-size: cover;
	

}
table {
	border-collapse:collapse;
	
	margin-left: 25em;
	margin-top: 10em;
	height: 10em;
	text-align: center;
	width: 50em;
	font-size: 18px;	
}
th,td{
	border-bottom:1px solid;
	height: 2em;

	
	}
th{
	color: blue;}
td{
	color: darkolive;
	}
button {
	height: 3em;
	width: 6em;
	 top: 5%;
	 left: 10%;
	 position: absolute;
	 background-image: url("smarks.jpg");
	 color: white;
	 
}
button:hover{
color: white;
}
a{
text-decoration: none;
font-style: normal;
padding: 10px 28px;
background-color: lightblue;
color: blue;
}
a:hover {
	background-color: blue;
	color: white;
}


</style>


</head>

<body>
	<button onclick="window.location.href = 'welcome.html';" style="margin: auto;color: blue;">home</button>
	
	<table  align="center">
	<tr>
	<th>USN</th>
	<th>STUDENT NAME</th>
	<th>SEMESTER</th>
	<th>SGPA</th>
	<th>PERCENTAGE (%)</th>
	<th>RESULT</th>
	</tr>
	<%ArrayList<student_info> rs=(ArrayList<student_info>)request.getAttribute("data") ;
	for(student_info s:rs){%> 
	<tr>
	<td><%=s.getUSN() %></td>
	<td><%=s.getname() %></td>
	<td><%=s.getsemester() %></td>
	<td><%=s.getsgpa() %></td>
	<td><%=s.getpercentage() %></td>
	<td><%=s.getresult() %></td>
	</tr>
	
	<%}%>
	
	
	
	</table>
</body>
</html>