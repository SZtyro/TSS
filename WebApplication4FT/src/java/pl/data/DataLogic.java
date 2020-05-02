/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fabian
 */
public class DataLogic extends HttpServlet {

    private static Connection newConnection(String url, String userName,String password)throws SQLException{
        
        String driverMySQL = "cpm.mysql.jdbc.Driver";
        String driverMSSQL = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String driver = driverMSSQL;
        try{
         Class.forName(driver);
         Connection connection = DriverManager.getConnection(url,userName,password);
         return connection;
         
        }catch(ClassNotFoundException ex){
            throw new SQLException("Nie znaleziono klasy dla sterownika: " + driver);
        }
        
        
    }
    
    public static java.sql.Connection connectDatabase()
  {
    java.sql.Connection databaseConnection=null; 
    
    String sUser="tomcatuser";
    String sPassword="tomcat";
    String sHost="155.158.112.31";
    String sURL="jdbc:sqlserver://"+sHost+":1433;databaseName=tomcat";
    try
    {
    
    databaseConnection = newConnection(sURL,sUser,sPassword);    
    }
    catch(java.sql.SQLException ex)
    {
    ex.getSQLState();
    }
    return databaseConnection;
  }
  public static java.sql.Connection getConnectionFromContext(String serwerType) throws SQLException
  {
   
    try
    {
    
    javax.naming.Context initContext = new javax.naming.InitialContext();
    javax.naming.Context envContext  = (javax.naming.Context)initContext.lookup("java:/comp/env");
    javax.sql.DataSource ds_mysql = (javax.sql.DataSource)envContext.lookup("jdbc/bazaTestowaMySQL");
    javax.sql.DataSource ds_mssql = (javax.sql.DataSource)envContext.lookup("jdbc/bazaTestowaMSSQL");
    java.sql.Connection connection; 
    if (serwerType.equals("mysql"))
    {
    connection = ds_mysql.getConnection();  
    return connection;
    }
    if (serwerType.equals("mssql"))
    {    
    connection = ds_mssql.getConnection();  
    return connection;
    }
    return null;
    }
    catch(Exception ex)
    {
      throw new SQLException("Nie pobrano połączenia z kontekstu");
    }      
  } 
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DataLogic</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DataLogic at " + request.getContextPath() + "</h1>");
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
