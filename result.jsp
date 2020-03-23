<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%> 
    
<%@page import=" result.bean.student_info" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
body {
background: url(marks1.png) no-repeat center fixed; 
  	background-size: cover;}
table{
border-collapse:collapse;

 border-radius: 5px;
height:100px;
position: relative;
border-radius: 1em;
margin: 3em 1em 1em 10em ;
top: 5%;
position: relative;
font-weight: bold;

}

td{
border-bottom:thin solid;
height:50px	;
text-align: center;
color: black;
background-color: white;	


}
th{
height:35px;
background-color: lightgreen;	
}

.button {
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  
  -webkit-transition-duration: 0.4s; /* Safari */
  transition-duration: 0.4s;
  cursor: pointer;
  
    

}

.button1 {
	  padding: 16px 32px;
	  background-color: white; 
	  color: black; 
	  border: 2px solid #4CAF50;
  
  top: 93%;
  right:43%;
   position: absolute;
}

.button1:hover {
  background-color: #4CAF50;
  color: white;
}

.button2 {
padding: 10px 12px;
  background-color: white; 
  color: black; 
  border: 2px solid #4CAF50;
  margin: 4px 2px;
  left: 5%;
  position:absolute;
  
}

.button2:hover {
  background-color: #4CAF50;
  color: white;
}
.button3 {
padding: 10px 12px;
  background-color: white; 
  color: black; 
  border: 2px solid #4CAF50;
  margin: 4px 2px;
  top:2%;
  right: 5%;
  position: absolute;
}

.button3:hover {
  background-color: #4CAF50;
  color: white;
}
p{
	font-size:20px;
	color: darkblack;
    position: relative;
    
 }

</style>
</head>
<body >

<button class="button button2" onclick="window.location.href = 'http://localhost:8080/Result/Hello?usn=<%=request.getAttribute("usn") %>';">go back</button>
<button class="button button3" onclick="window.location.href = 'welcome.html';" style="margin: auto;color: blue; align:left;" >home</button>


<p align="center" >USN:<%=request.getAttribute("usn") %></p>

<table align="center" width="1200">
<tr>
<th>SUBJECT CODE</th>
<th>SUBJECT NAME </th>
<th>INTERNAL</th>
<th>EXTERNAL</th>
<th>TOTAL</th>
<th>CREDIT</th>
<th>POINT</th>
<th>GRADE POINT</th>
<th>GRADE</th>
</tr>

<%ArrayList<student_info> rs=(ArrayList<student_info>)request.getAttribute("result") ;
for(student_info s:rs){%>
<tr>
	<td><%=s.getsc() %></td>
	<td><%=s.getsn() %></td>
	<td><%=s.getin() %></td>
	<td><%=s.getex() %></td>
	<td><%=s.gettot() %></td>
	<td><%=s.getcredit() %></td>
	<td><%=s.getpoint() %></td>
	<td><%=s.getgp() %></td>
	<td><%=s.getg() %></td>
	</tr>
	<%}%>

</table>
<p align="center">TOTAL MARKS:   <%=request.getAttribute("marks") %></p>
<p align="center">TOTAL GRADE POINTS:   <%=request.getAttribute("credit") %></p>
<br>
<button class="button button1" onclick="window.location.href = 'index.html';"   align="center" >check another result</button> 
</body>
</html>