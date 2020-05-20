<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="pl.data.DataLogic"%>
<%@page import="pl.resources.Osoba"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1250">
        <title>JSP Page</title>
    </head>
    <body>
    <body bgcolor="#e0e0e0">

        <h1>Autor projektu: </h1>
        <p>Fabian Trubic</p>

        <h1>Aktualna data: </h1>

        <%

            java.util.Date date = new java.util.Date();
            out.println("<p>" + date + "</p>");
            
            DataLogic logic = new pl.data.DataLogic();
            java.sql.Connection connection = logic.connectDatabase();
            getServletContext().setAttribute("Connection", connection);

            try {
                String sQuery = "SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 't_uzytkownik'";
                java.sql.ResultSet wynikZapytania;
                wynikZapytania = connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery(sQuery);
                wynikZapytania.first();

                out.println("<h5>");
                out.println("Dostepne kolumny: ");
                out.println("</h5>");
                out.println("<p>");

                for (int i = 0; i < wynikZapytania.getRow(); i++) {

                    out.println(" " + wynikZapytania.getString("COLUMN_NAME") + ",");

                    wynikZapytania.next();

                }
                out.println("</p>");

            } catch (Exception ex) {
                String sKomunikat = ex.toString();
                System.out.println(ex);
            }

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
                <%    
                    
                    try {
                        String sQuery = "SELECT * FROM t_uzytkownik";
                        java.sql.ResultSet wynikZapytania;
                        wynikZapytania = connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery(sQuery);
                        wynikZapytania.first();

                        for (int i = 0; i < wynikZapytania.getRow(); i++) {
                            out.println("<tr>");
                            out.println("<td>" + wynikZapytania.getString("id") + "</td>");
                            out.println("<td>" + wynikZapytania.getString("imie") + "</td>");
                            out.println("<td>" + wynikZapytania.getString("nazwisko") + "</td>");
                            out.println("<td>" + wynikZapytania.getString("opis") + "</td>");
                            out.println("<td>"
                                    + "<form method=\"post\" action=\"DeleteServlet\">"
                                    + "<button style=\"background-color:#ff6347;border-radius:5px;border:none;\" name=\"Usun\" value=\"" + wynikZapytania.getString("id") + "\">Usun<input hidden type=\"submit\"></button>"
                                    + "</form>"
                                    + "</td>");
                            out.println("</tr>");
                            wynikZapytania.next();

                        }

                    } catch (Exception ex) {
                        String sKomunikat = ex.toString();
                        System.out.println(ex);
                    }

                     
                %>

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
</body>
</html>
