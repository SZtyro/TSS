
<%@page import="pl.resources.Osoba"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
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
        %>


        <table style="border: solid; width: 40%" >
            <tr>

                <th>Imie</th>
                <th>Wiek</th>
            </tr>
            <tbody>
                <%
           
           ArrayList<Osoba> a = (ArrayList<Osoba>)getServletConfig().getServletContext().getAttribute("lista");
           int i = 0;
                   if(a != null)
                   for(Osoba item:a){
                       out.println("<tr>");
                       out.println("<td>" + item.imie + "</td>");
                       out.println("<td>" + item.wiek + "</td>");
                       out.println("<td>"
                               + "<form method=\"post\" action=\"DeleteServlet\">"
                               + "<button style=\"background-color:#ff6347;border-radius:5px;border:none;\" name=\"Usun\" value=\"" + i  +"\">Usun<input hidden type=\"submit\"></button>"
                               + "</form>"
                               + "</td>");
                       out.println("</tr>");
                       i++;
                   }
                
                %>

            </tbody>
        </table>
        <br>
        <form method="post" action="mainServlet">
            <input type="text" name="Imie" value="Podaj imie">
            <input type="text" name="Wiek" value="Podaj wiek">
            <input type="submit" value="Dodaj osobe">
        </form>
    </body>
</body>
</html>
