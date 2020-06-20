
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit</title>
    </head>
    <body>
        <h1>Edytuj uzytkownika ${editOsoba.id} </h1>
        <br>
        <form method="post" action="mainServlet?method=update">
            <input type="hidden" name="id" value="${editOsoba.id}">
            <input type="text" name="Imie" value="${editOsoba.imie}">
            <input type="text" name="Nazwisko" value="${editOsoba.nazwisko}">
            <input type="text" name="Opis" value="${editOsoba.opis}">
            <input type="submit" value="Edytuj osobe">
        </form>
    </body>
</html>
