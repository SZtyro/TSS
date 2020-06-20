
<%@page import="pl.data.CRUD"%>
<%@page import="pl.data.DataLogic"%>
<%@page import="pl.resources.Osoba"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1250">
        <title>JSP Page</title>
        
    </head>

    <body bgcolor="#e0e0e0" >



        <h1>Autor projektu: </h1>
        <p>Fabian Trubic</p>

        <%
            //java.util.Date date = new java.util.Date();
            //out.println("<p>" + date + "</p>");
            //DataLogic logic = new DataLogic();
            //CRUD crud = new CRUD();
            //crud.setLogic(logic);
            //ArrayList<Osoba> li = crud.fetchData();
            //logic.fetchData();
            

            //request.setAttribute("connection", logic.connection);
            //request.setAttribute("logic", logic);
            //request.setAttribute("list", li);
            //request.setAttribute("logic", logic);
        %>
       
        <form  method="get" action="mainServlet">
            <input type="submit" value="Baza danych">
        </form>
        <br>
        <a href="https://localhost:8443/WebApplicationFT/webresources/users"><button>Rest JSON</button></a>
        <br>
        <br>
        <a href="https://localhost:8443/WebApplicationFT/ws.jsp"><button>WS</button></a>
        
        
        
    </body>
</html>
