
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

    <body bgcolor="#e0e0e0">

        <h1>Autor projektu: </h1>
        <p>Fabian Trubic</p>


        <h1>Aktualna data: </h1>
        <%
            java.util.Date date = new java.util.Date();
            out.println("<p>" + date + "</p>");
            DataLogic logic = new DataLogic();
            logic.fetchData();
            ArrayList<Osoba> li = logic.lista;
            
            request.setAttribute("connection", logic.connection);
            request.setAttribute("logic", logic);
            request.setAttribute("list", li);
            request.setAttribute("logic", logic);
        %>



        <table style="border: solid; width: 40%" >
            <tr>
                <th>#</th>
                <th>Imie</th>
                <th>Nazwisko</th>
                <th>Opis</th>
                <th>Akcje</th>
            </tr>
            <tbody>

                <c:forEach var="i" items="${list}" varStatus="loop">
                    <tr>                   
                        <td><c:out value="${i.id}"/> </td>
                        <td><c:out value="${i.imie}"/> </td>
                        <td><c:out value="${i.nazwisko}"/> </td>
                        <td><c:out value="${i.opis}"/> </td>
                        <td>
                            <form method="post" action="DeleteServlet">
                                <button style="background-color:#ff6347;border-radius:5px;border:none;" 
                                        name="Usun" value="${i.id}">Usun
                                    <input hidden type="submit">
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>


            </tbody>
        </table>
        <br>
        <form method="post" action="mainServlet">
            <input type="text" name="Id" value="Podaj id">
            <input type="text" name="Imie" value="Podaj imie">
            <input type="text" name="Nazwisko" value="Podaj nazwisko">
            <input type="text" name="Opis" value="Podaj opis">
            <input type="submit" value="Dodaj osobe">
        </form>

    </body>
</html>
