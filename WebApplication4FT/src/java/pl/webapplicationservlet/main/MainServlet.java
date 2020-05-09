package pl.webapplicationservlet.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.data.DataLogic;
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

            out.println("<title>Zadanie 4</title>");
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
        java.sql.Connection connection = (java.sql.Connection) getServletContext().getAttribute("Connection");
        try {
            //a.remove(Integer.parseInt((String)request.getParameter("Usun")) );
            String sql = "INSERT INTO t_uzytkownik (id,dane,opis,imie,nazwisko) VALUES("
                    + request.getParameter("Id") + ",null,'"
                    + request.getParameter("Opis") + "','"
                    + request.getParameter("Imie") + "','"
                    + request.getParameter("Nazwisko") + "')";
            
            System.out.println(sql);
            connection.createStatement().execute(sql);

            out.println("<html>");
            out.println("<body style=\"background-color:#2ECC71;text-align:center;\">");
            out.println("<div style=\"color:white;font-weight:600;margin:30px;font-size:50px;\">Pomyslnie dodano uzytkownika!</div>");
            out.println("<form "
                    + "action=\"https://localhost:8443/WebApplication4FT\">"
                    + "<input style=\"margin:20px;color:white;font-weight:700;width:100px;height:50px;background-color:#273746;border-radius:5px;border:none;\"  type=\"submit\" value=\"Powrot\"></form>");
            out.println("</body>");
            
        } catch (SQLException ex) {
            out.println("<html>");
            out.println("<body style=\"background-color:#E74C3C;text-align:center;\">");
            out.println("<div style=\"color:white;font-weight:600;margin:30px;font-size:50px;\">Blad: " + ex + "</div>");
            out.println("<form "
                    + "action=\"https://localhost:8443/WebApplication4FT\">"
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
