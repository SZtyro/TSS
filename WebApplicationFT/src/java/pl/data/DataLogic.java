/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import pl.resources.Osoba;

/**
 *
 * @author Fabian
 */
public class DataLogic {

    public ArrayList<Osoba> lista = new ArrayList();
    public java.sql.Connection connection;

    public DataLogic() {
        //lista = new ArrayList();
        fetchData();
    }

    private static Connection newConnection(String url, String userName, String password) throws SQLException {

        String driverMySQL = "cpm.mysql.jdbc.Driver";
        String driverMSSQL = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String driver = driverMSSQL;
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, userName, password);
            return connection;

        } catch (ClassNotFoundException ex) {
            throw new SQLException("Nie znaleziono klasy dla sterownika: " + driver);
        }

    }

    public java.sql.Connection connectDatabase() {
        java.sql.Connection databaseConnection = null;

        String sUser = "tomcatuser";
        String sPassword = "tomcat";
        String sHost = "155.158.112.31";
        String sURL = "jdbc:sqlserver://" + sHost + ":1433;databaseName=tomcat";
        try {

            databaseConnection = newConnection(sURL, sUser, sPassword);
        } catch (java.sql.SQLException ex) {
            ex.getSQLState();
        }
        connection = databaseConnection;
        return databaseConnection;
    }

    public static java.sql.Connection getConnectionFromContext(String serwerType) throws SQLException {

        try {

            javax.naming.Context initContext = new javax.naming.InitialContext();
            javax.naming.Context envContext = (javax.naming.Context) initContext.lookup("java:/comp/env");
            javax.sql.DataSource ds_mysql = (javax.sql.DataSource) envContext.lookup("jdbc/bazaTestowaMySQL");
            javax.sql.DataSource ds_mssql = (javax.sql.DataSource) envContext.lookup("jdbc/bazaTestowaMSSQL");
            java.sql.Connection connection;
            if (serwerType.equals("mysql")) {
                connection = ds_mysql.getConnection();
                return connection;
            }
            if (serwerType.equals("mssql")) {
                connection = ds_mssql.getConnection();
                return connection;
            }
            return null;
        } catch (Exception ex) {
            throw new SQLException("Nie pobrano połączenia z kontekstu");
        }
    }

    public void fetchData() {
        java.sql.Connection connection = connectDatabase();
        //getServletContext().setAttribute("Connection", connection);
        try {
            String sQuery = "SELECT * FROM t_uzytkownik";
            java.sql.ResultSet wynikZapytania;
            wynikZapytania = connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery(sQuery);
            wynikZapytania.first();
            lista.clear();
            for (int i = 0; i < wynikZapytania.getRow(); i++) {

                lista.add(new Osoba(Integer.parseInt(wynikZapytania.getString("id")),
                        wynikZapytania.getString("imie"),
                        wynikZapytania.getString("nazwisko"),
                        wynikZapytania.getString("opis")));
//                out.println("<tr>");
//                out.println("<td>" + wynikZapytania.getString("id") + "</td>");
//                out.println("<td>" + wynikZapytania.getString("imie") + "</td>");
//                out.println("<td>" + wynikZapytania.getString("nazwisko") + "</td>");
//                out.println("<td>" + wynikZapytania.getString("opis") + "</td>");
//                out.println("<td>"
//                        + "<form method=\"post\" action=\"DeleteServlet\">"
//                        + "<button style=\"background-color:#ff6347;border-radius:5px;border:none;\" name=\"Usun\" value=\"" + wynikZapytania.getString("id") + "\">Usun<input hidden type=\"submit\"></button>"
//                        + "</form>"
//                        + "</td>");
//                out.println("</tr>");
                wynikZapytania.next();
                //System.out.println(i);
//
            }
        } catch (Exception ex) {
            String sKomunikat = ex.toString();
            System.out.println(ex);
        }
    }

}
