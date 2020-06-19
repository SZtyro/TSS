
<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1250">
        <title>Baza danych</title>
    </head>

    <body>
        <button>Odswiez</button>
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

                    <tr>                   
                        <td><c:out value="${i.id}"/> </td>
                        <td><c:out value="${i.imie}"/> </td>
                        <td><c:out value="${i.nazwisko}"/> </td>
                        <td><c:out value="${i.opis}"/> </td>
                        <td>
                            <div style="display: flex;">
                                <form method="post" action="mainServlet?method=delete">
                                <button style="background-color:#ff6347;border-radius:5px;border:none;" 
                                        name="Usun" value="${i.id}">Usun
                                    <input hidden type="submit">
                                </button>
                            </form>
                            <form method="post" action="mainServlet?method=edit">
                                <button style="background-color:#ff6347;border-radius:5px;border:none;" 
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
        <form method="post" action="mainServlet?method=post">
            <input type="text" name="Id" value="Podaj id">
            <input type="text" name="Imie" value="Podaj imie">
            <input type="text" name="Nazwisko" value="Podaj nazwisko">
            <input type="text" name="Opis" value="Podaj opis">
            <input type="submit" value="Dodaj osobe">
        </form>

    </body>
</html>
