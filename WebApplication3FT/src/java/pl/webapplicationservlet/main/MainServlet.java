
package pl.webapplicationservlet.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.resources.Osoba;

public class MainServlet extends HttpServlet {

    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        try {
            /*
             * tresc strony HTML tworzona przez servlet
             */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet mainServlet</title>");
            out.println("<script type=\"text/javascript\" src=\"javascript/util.js\">");
            out.println("</script>");

            out.println("<title>Zadanie 3</title>");
            out.println("</head>");
            out.println("</head>");

            out.println("<h1>Nie wyslano zadnych danych!</h1>");

        } catch (Exception ex) {

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ArrayList<Osoba> li = new ArrayList<Osoba>();
        li.add(new Osoba(request.getParameter("Imie"), Integer.parseInt(request.getParameter("Wiek"))));
        getServletContext().setAttribute("lista", li);
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<body>");
        out.println("Dodano uzytkownika!");
        out.println("<form action=\"http://localhost:8080/WebApplication3FT\"><input type=\"submit\" value=\"Powrot\"></form>");
        out.println("</body>");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    public void destroy() {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
        
    }

    @Override
    public void init() {

    }

}
