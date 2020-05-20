/*
 * To change this template, choose Tools | Templates
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
public class ApplicationData 
{
int iActualUser=0;    
java.util.List<String> lista=new java.util.ArrayList();
public void addMember(String sData)
{
lista.add(sData);    
}
public java.util.List listMembers()
{
return lista;    
}
public String getNextUser()
{
String sActualUser=lista.get(iActualUser);    
iActualUser++;    
return  sActualUser;
}



}
