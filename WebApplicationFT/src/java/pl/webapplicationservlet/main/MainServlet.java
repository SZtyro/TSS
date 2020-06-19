package pl.webapplicationservlet.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.data.CRUD;
import pl.data.DataLogic;
import pl.resources.Osoba;

public class MainServlet extends HttpServlet {

    //ArrayList<Osoba> li = new ArrayList<Osoba>();
    DataLogic dl = new DataLogic();
    CRUD crud;

    public MainServlet() throws SQLException, ClassNotFoundException {

    }

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
        try {
            this.crud = dl.connectDatabase();
            request.getSession().setAttribute("list", crud.fetchData());

            //javax.servlet.RequestDispatcher view = request.getRequestDispatcher("baza.jsp");
            response.getWriter().print(request.getSession().getAttribute("list"));
            System.out.println("lllllllllllllllllllllllllllllllllllllllllllllllllll");
            //response.sendRedirect("https://localhost:8443/WebApplicationFT");
            response.sendRedirect("baza.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        PrintWriter out = response.getWriter();

        if (method.contains("post")) {

            try {
                if (crud == null) {
                    request.getSession().setAttribute("message", "Brak polaczenia z baza danych!");
                    request.getSession().setAttribute("link", "https://localhost:8443/WebApplicationFT/baza.jsp");
                    response.sendRedirect("errorPage.jsp");
                } else {
                    crud.addUser(request.getParameter("Id"), request.getParameter("Opis"), request.getParameter("Imie"), request.getParameter("Nazwisko"));
                    request.getSession().setAttribute("message", "Pomyslnie dodano uzytkownika");
                    request.getSession().setAttribute("link", "https://localhost:8443/WebApplicationFT/baza.jsp");
                    response.sendRedirect("successPage.jsp");
                }

            } catch (SQLException ex) {
                request.getSession().setAttribute("message", ex);
                request.getSession().setAttribute("link", "https://localhost:8443/WebApplicationFT/baza.jsp");
                response.sendRedirect("errorPage.jsp");
            }
        } else if (method.contains("delete")) {
            if (crud == null) {
                request.getSession().setAttribute("message", "Brak polaczenia z baza danych!");
                request.getSession().setAttribute("link", "https://localhost:8443/WebApplicationFT/baza.jsp");
                response.sendRedirect("errorPage.jsp");
            } else {
                try {
                    crud.deleteUser(request.getParameter("Usun"));
                    request.getSession().setAttribute("message", "Pomyslnie usunieto uzytkownika");
                    request.getSession().setAttribute("link", "https://localhost:8443/WebApplicationFT/baza.jsp");
                    response.sendRedirect("successPage.jsp");
                } catch (SQLException ex) {
                    request.getSession().setAttribute("message", ex);
                    request.getSession().setAttribute("link", "https://localhost:8443/WebApplicationFT/baza.jsp");
                    response.sendRedirect("errorPage.jsp");
                }
            }

        } else if (method.contains("edit")) {
            request.getSession().setAttribute("message", "wiadomosc panie");
            request.getSession().setAttribute("link", "https://localhost:8443/WebApplicationFT/edit.jsp");
            //response.sendRedirect("edit.jsp");
            response.sendRedirect("errorPage.jsp");
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
