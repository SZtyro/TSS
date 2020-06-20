
<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1250">
        <title>Baza danych</title>
    </head>
    <style>
        tr:hover{
            background-color: #707070;
        }
        
    </style>
    <body>
        <form method="get" action="mainServlet">
            <input type="submit" value="Odswiez baze">
        </form>

        <table style="border: solid; width: 70%;margin: 0 auto" >
            <tr>
                <th>#</th>
                <th>Imie</th>
                <th>Nazwisko</th>
                <th>Opis</th>
                <th>Akcje</th>
            </tr>
            <tbody>

                <c:forEach var="i" items="${list}" varStatus="loop">

                    <tr style="height:40px;">                   
                        <td><c:out value="${i.id}"/> </td>
                        <td><c:out value="${i.imie}"/> </td>
                        <td><c:out value="${i.nazwisko}"/> </td>
                        <td><c:out value="${i.opis}"/> </td>
                        <td >
                            <div style="display: flex; ">
                                <form method="post" action="mainServlet?method=delete" style="width:95%">
                                    <button style="background-color:#ff6347;border-radius:5px;border:none;height:40px;width:95%" 
                                            name="Usun" value="${i.id}">Usun
                                        <input hidden type="submit">
                                    </button>
                                </form>
                                <form method="post" action="mainServlet?method=edit" style="width:95%">
                                    <button style="background-color:#34abeb;border-radius:5px;border:none;height:40px;width:95%" 
                                            name="Edytuj" value="${i.id}">Edytuj
                                        <input hidden type="submit">
                                    </button>
                                </form>
                            </div>

                        </td>
                    </tr>
                </c:forEach>


            </tbody>
        </table>
        <br>
        <form method="post" action="mainServlet?method=add">
            <input type="text" name="Id" value="Podaj id">
            <input type="text" name="Imie" value="Podaj imie">
            <input type="text" name="Nazwisko" value="Podaj nazwisko">
            <input type="text" name="Opis" value="Podaj opis">
            <input type="submit" value="Dodaj osobe">
        </form>

    </body>
</html>
