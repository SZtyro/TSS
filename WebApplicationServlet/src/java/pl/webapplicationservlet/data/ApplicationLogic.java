/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.webapplicationservlet.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mariusz
 */
public class ApplicationLogic {
//metody logiki aplikacji
private static Connection makeNewConnection(String sUrl,String sUsername, String sPassword) 
          throws SQLException {
    //przyklad tworzenia polaczenia z baza mysql z poziomu aplikacji web
    //sterownik dystrybuowany z aplikacją
    String sDriverMySQL="com.mysql.jdbc.Driver";
    String sDriverMSSQL="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String sDriver=sDriverMSSQL;
    try {
      // Załaduj sterownik bazy danych, jeżeli jeszcze
      // nie został załadowany.
      Class.forName(sDriver);
      // Nawiąż połączenie sieciowe z bazą danych.
      Connection connection =
        DriverManager.getConnection(sUrl, sUsername, sPassword);
      return(connection);
    } catch(ClassNotFoundException cnfe) 
    {
      // Upraszczam blok try/catch dla osób korzystających z
      // tego kodu i obsługują tylko jeden typ wyjątków.
      throw new SQLException("Nie znaleziono klasy dla sterownika: " +
                             sDriver);
    }
  }    
    public static java.sql.Connection connectDatabase()
  {
    java.sql.Connection databaseConnection=null; 
    //tu mozna zainicjować pulę połączeń, a pozniej tylko przy wywolaniu servletu
    //bedzie pobierać polączenie z puli, bez konieczności ponownego łączenia z bazą
    //bedzie to działało szybciej
    //polaczenie jdbc:odbc
    String sUser="tomcatuser";
    String sPassword="tomcat";
    //String sHost="127.0.0.1";
    //String sHost="169.254.57.215"; // lokalny - wirtualny
    String sHost="155.158.112.31"; //zdalny serwer
    //String sURL="jdbc:odbc:sklep";
    //String sURL="jdbc:mysql://"+sHost+"/bazatest?characterEncoding=latin2";
    String sURL="jdbc:sqlserver://"+sHost+":1433;databaseName=tomcat";
    try
    {
    //pulaPolaczen = new ConnectionPool("com.mysql.jdbc.Driver", sURL,
    //                                sDataBasedUser, sDataBasePassword, 10,
    //                                50, true);
    databaseConnection=makeNewConnection(sURL,sUser,sPassword);    
    }
    catch(java.sql.SQLException ex)
    {
    ex.getSQLState();
    }
    return databaseConnection;
  }
  public static java.sql.Connection getConnectionFromContext(String serwerType) throws SQLException
  {
    //przyklad pobierania polaczenia z bazą z puli polaczen zdefiniowanych
    //w pliku konfiguracyjnym context.xml na serwerze Tomcat
    try
    {
    //ustalanie kontekstu Java Namin Directory Interface
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
