/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pl.resources.Osoba;

/**
 *
 * @author fabix
 */
public class CRUD {

    private Connection connection;

    public CRUD(Connection conn) {
        this.connection = conn;
    }

    public ArrayList<Osoba> fetchData() throws SQLException {
        //java.sql.Connection connection = connectDatabase();
        //getServletContext().setAttribute("Connection", connection);
        try {
            String sQuery = "SELECT * FROM t_uzytkownik";
            java.sql.ResultSet wynikZapytania;
            wynikZapytania = connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery(sQuery);
            wynikZapytania.first();
            ArrayList<Osoba> toReturn = new ArrayList<Osoba>();
            for (int i = 0; i < wynikZapytania.getRow(); i++) {

                toReturn.add(new Osoba(Integer.parseInt(
                        wynikZapytania.getString("id")),
                        wynikZapytania.getString("imie"),
                        wynikZapytania.getString("nazwisko"),
                        wynikZapytania.getString("opis")));
                wynikZapytania.next();
                //System.out.println(i);

            }
            return toReturn;
        } catch (Exception ex) {
            String sKomunikat = ex.toString();
            System.out.println(ex);
            return null;
        }

    }

    public void addUser(String id, String opis, String imie, String nazwisko) throws SQLException {
        String sql = "INSERT INTO t_uzytkownik (id,dane,opis,imie,nazwisko) VALUES("
                + id + ",null,'"
                + opis + "','"
                + imie + "','"
                + nazwisko + "')";

        System.out.println(sql);
        connection.createStatement().execute(sql);

    }

    public void deleteUser(String id) throws SQLException {
        connection.createStatement().execute("DELETE FROM t_uzytkownik WHERE id=" + id);

    }

    public void editUser(String id, String imie, String nazwisko,String opis) throws SQLException {
        String sql = "UPDATE t_uzytkownik SET imie='" + imie + "', nazwisko='" + nazwisko + "', opis='" + opis + "' WHERE id=" + id;
        connection.createStatement().execute(sql);
        System.out.println(sql);
    }

    public Osoba getUser(String id) throws SQLException {
        String sQuery = "SELECT * FROM t_uzytkownik where id=" + id;
        java.sql.ResultSet wynikZapytania;
        Statement st = connection.createStatement();
        st.execute(sQuery);
        ResultSet rs = st.getResultSet();
        rs.next();

        Osoba toReturn = new Osoba(
                rs.getInt("id"),
                rs.getString("imie"),
                rs.getString("nazwisko"),
                rs.getString("opis")
        );

        return toReturn;
    }
}
