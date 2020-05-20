/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import pl.data.DataLogic;
import pl.resources.Osoba;

/**
 *
 * @author Emix
 */
public class DeleteServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DeleteServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //ArrayList<Osoba> a = (ArrayList<Osoba>) getServletConfig().getServletContext().getAttribute("lista");
        PrintWriter out = response.getWriter();
        java.sql.Connection connection = new DataLogic().connectDatabase();
        try {
            //a.remove(Integer.parseInt((String)request.getParameter("Usun")) );
            //System.out.println(request.getParameter("Usun"));
            connection.createStatement().execute("DELETE FROM t_uzytkownik WHERE id=" +(String) request.getParameter("Usun"));
                out.println("<html>");
                out.println("<body style=\"background-color:#2ECC71;text-align:center;\">");
                out.println("<div style=\"color:white;font-weight:600;margin:30px;font-size:50px;\">Pomyslnie usunieto uzytkownika!</div>");
                out.println("<form action=\"https://localhost:8443/WebApplicationFT\"><input "
                        + "style=\"margin:20px;color:white;font-weight:700;width:100px;height:50px;background-color:#273746;border-radius:5px;border:none;\""
                        + " type=\"submit\" value=\"Powrot\"></form>");
                out.println("</body>");
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
