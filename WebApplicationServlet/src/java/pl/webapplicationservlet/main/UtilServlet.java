/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.webapplicationservlet.main;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.webapplicationservlet.data.ApplicationData;

/**
 *
 * @author mariusz
 */
public class UtilServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet utilServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet utilServlet at " + request.getContextPath() + "</h1>");
            out.println("<h1> Dane z innego serwletu </h1>");
            
            ApplicationData listaUzytkownikow = (ApplicationData) getServletContext().getAttribute("sListaUzytkownikow"); 
            
            java.util.Iterator i= listaUzytkownikow.listMembers().iterator();

            out.println("<table border=\"5\" background=\"images/header.png\">");
            while(i.hasNext())
            {
            String sLiniaDanych = (String) i.next();
            out.println("<TR>"); 
            out.println("<TD>");            
            out.println(sLiniaDanych);            
            out.println("</TD>");  
            out.println("</TR>"); 
            }
            out.println("<h1>Prezentacja parametrów servletu z pliku web.xml</h1>");            
            String sUserName=getServletConfig().getInitParameter("userName");
            String sUserPass = getServletConfig().getInitParameter("userPass");   
            out.println("<h1>userName="+sUserName+"</h1>");      
            out.println("<h1>userPass="+sUserPass+"</h1>");               
            out.println("</table>"); 
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
