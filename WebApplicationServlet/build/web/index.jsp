<%-- 
    Document   : index
    Created on : 2013-02-08, 12:10:39
    Author     : mariusz
--%>

<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1250">
        <title>JSP Page</title>
    </head>
    <body>
<body bgcolor="#e0e0e0">
<h1 align="center">
WebApplicationServlet
</h1>

<%
getServletContext().setAttribute("sWersjaAplikacji", "201802028.01");
%>   

<form method="post" action="mainServlet?command=brak" >
<p align="center">Parametr 1
<input type="text" name="user" value="" size="20"></p>
<p  align="center">Parametr 2&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="password" name="pass" value="" size="20"> </p>
<p  align="center">Baza&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%
   out.println("<select  name=\"IDBazy\" >");
   //out.println("<option value='1000000'>Wybierz bazę</option>");
   out.println("<option value='1000001'>Baza testowa</option>");
   out.println("<option value='1000002'>Baza produkcyjna</option>");
   out.println("</select>");
%>
</p>

<p align="center">
<input type="submit" value="Strona główna" size="20"> </p>
</p>
<p align="center">
<%
String sWersjaAplikacji=(String)getServletContext().getAttribute("sWersjaAplikacji");
out.println(sWersjaAplikacji+"</p>");
//out.println("Identyfikator sesji: " +request.getRequestedSessionId());
%>
</p>

</form>
</form>
</body>
    </body>
</html>
