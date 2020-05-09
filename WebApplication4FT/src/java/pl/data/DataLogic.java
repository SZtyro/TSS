/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.data;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


/**
 *
 * @author Fabian
 */
public class DataLogic {

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
    
    
   

   

}
