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

    ArrayList<Osoba> li = new ArrayList<Osoba>();

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
        int wiek;
        PrintWriter out = response.getWriter();
        try {
            wiek = Integer.parseInt(request.getParameter("Wiek"));
            li.add(new Osoba(request.getParameter("Imie"), wiek));

            getServletContext().setAttribute("lista", li);
            

            out.println("<html>");
            out.println("<body style=\"background-color:#2ECC71;text-align:center;\">");
            out.println("<div style=\"color:white;font-weight:600;margin:30px;font-size:50px;\">Pomyslnie dodano uzytkownika!</div>");
            out.println("<form "                   
                    + "action=\"http://localhost:8080/WebApplication3FT\">"
                    + "<input style=\"margin:20px;color:white;font-weight:700;width:100px;height:50px;background-color:#273746;border-radius:5px;border:none;\"  type=\"submit\" value=\"Powrot\"></form>");
            out.println("</body>");
        } catch (Exception e) {
            out.println("<html>");
            out.println("<body style=\"background-color:#E74C3C;text-align:center;\">");
            out.println("<div style=\"color:white;font-weight:600;margin:30px;font-size:50px;\">Blad: " + e +"</div>");
            out.println("<form "                   
                    + "action=\"http://localhost:8080/WebApplication3FT\">"
                    + "<input style=\"margin:20px;color:white;font-weight:700;width:100px;height:50px;background-color:#273746;border-radius:5px;border:none;\"  type=\"submit\" value=\"Powrot\"></form>");
            out.println("</body>");        
        }

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
