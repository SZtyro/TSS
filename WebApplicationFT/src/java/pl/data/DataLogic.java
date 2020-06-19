package pl.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 *
 * @author Fabian
 */
public class DataLogic {

    //public ArrayList<Osoba> lista = new ArrayList();
    public java.sql.Connection connection;

    public DataLogic() {
        //lista = new ArrayList();
        //connectDatabase();
    }

    public CRUD connectDatabase() throws SQLException, ClassNotFoundException {
        java.sql.Connection databaseConnection = null;

        String sUser = "tomcatuser";
        String sPassword = "tomcat";
        String sHost = "155.158.112.31";
        String sURL = "jdbc:sqlserver://" + sHost + ":1433;databaseName=tomcat";
        String driverMSSQL = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String driver = driverMSSQL;

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(sURL,sUser, sPassword);
        return new CRUD(connection);

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

}
