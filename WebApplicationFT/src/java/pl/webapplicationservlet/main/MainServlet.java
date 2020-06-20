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
    DataLogic dl;
    CRUD crud;

    public MainServlet() throws SQLException, ClassNotFoundException {
        dl = new DataLogic();
        crud = dl.connectDatabase();
        
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
            System.out.println(request.getSession().getAttribute("list"));
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

        if (crud == null) {
            try {
                crud = dl.connectDatabase();
            } catch (Exception ex) {
                request.getSession().setAttribute("message", ex);
                request.getSession().setAttribute("link", "https://localhost:8443/WebApplicationFT/baza.jsp");
                response.sendRedirect("errorPage.jsp");
            }
        }
        if (method.contains("add")) {

            try {

                crud.addUser(request.getParameter("Id"), request.getParameter("Opis"), request.getParameter("Imie"), request.getParameter("Nazwisko"));
                request.getSession().setAttribute("message", "Pomyslnie dodano uzytkownika");
                request.getSession().setAttribute("link", "https://localhost:8443/WebApplicationFT/baza.jsp");
                response.sendRedirect("successPage.jsp");

            } catch (SQLException ex) {
                request.getSession().setAttribute("message", ex);
                request.getSession().setAttribute("link", "https://localhost:8443/WebApplicationFT/baza.jsp");
                response.sendRedirect("errorPage.jsp");
            }
        } else if (method.contains("delete")) {

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

        } else if (method.contains("edit")) {
            request.getSession().setAttribute("link", "https://localhost:8443/WebApplicationFT/edit.jsp");
            String id = request.getParameter("Edytuj");
            try {
                request.getSession().setAttribute("editOsoba", crud.getUser(id));
                response.sendRedirect("edit.jsp");
            } catch (SQLException ex) {
                request.getSession().setAttribute("message", ex);
                request.getSession().setAttribute("link", "https://localhost:8443/WebApplicationFT/baza.jsp");
                response.sendRedirect("errorPage.jsp");

            }

            //crud.deleteUser(request.getParameter("Usun"));
            //response.sendRedirect("errorPage.jsp");
        } else if (method.contains("update")) {
            try {
                crud.editUser(request.getParameter("id"), request.getParameter("Imie"), request.getParameter("Nazwisko"), request.getParameter("Opis"));
                request.getSession().setAttribute("message", "Pomyslnie edytowano uzytkownika");
                request.getSession().setAttribute("link", "https://localhost:8443/WebApplicationFT/baza.jsp");
                response.sendRedirect("successPage.jsp");
            } catch (SQLException ex) {
                request.getSession().setAttribute("message", ex);
                request.getSession().setAttribute("link", "https://localhost:8443/WebApplicationFT/baza.jsp");
                response.sendRedirect("errorPage.jsp");
            }
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
