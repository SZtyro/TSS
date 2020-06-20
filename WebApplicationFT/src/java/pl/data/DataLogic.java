package pl.data;


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
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

   public CRUD getConnectionFromContext() {
        
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds_mssql = (DataSource)envContext.lookup("jdbc/bazaTestowaMSSQL");
            
            connection = ds_mssql.getConnection();        
        } catch(Exception ex) {
            ex.printStackTrace();
        }   
        
        return new CRUD(connection);
    }

    public void closeConnection() throws SQLException{
        connection.close();
    }
}
