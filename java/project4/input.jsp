<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
input, textarea, button {
  background-color: black;
  color: lightgreen;
  scrollbar-color: black black;
  border-color: black;
  overflow: auto;
}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<br>

<form action='/project4/add' method='get' id='form1'>
      
   
        
    <textarea id='command' name='command' rows='10'  cols='50'><%= request.getAttribute("command") %></textarea>
     <br><br><input type='submit' form='form1' value='Execute Command'></button>
        
     <input type='submit'  name='reset' form='form1' value = 'Reset Form'></button></form>
<br>
All execution results will appear below.

<hr>
<br>
Database Results:
<br>
<br>
</body>
</html>