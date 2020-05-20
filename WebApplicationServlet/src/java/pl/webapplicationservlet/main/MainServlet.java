/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.webapplicationservlet.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.webapplicationservlet.data.*;

/**
 *
 * @author mariusz
 */
public class MainServlet extends HttpServlet {

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
    String sDataUruchomienia="";
    java.sql.Connection databaseConnection;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    //przyklad zapytania do bazy w funkcji processRequest
    //ze zrodla danych stworznego w aplikacji
    /*
    String sQuery="SELECT * FROM t_dane";
    try
    {
    java.sql.ResultSet wynikZapytania;    
    wynikZapytania=databaseConnection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery(sQuery);
    wynikZapytania.last();
    int iIloscWierszyMSSQL=wynikZapytania.getRow();
    int iIloscKolumn=wynikZapytania.getMetaData().getColumnCount();
    }
    catch(Exception ex)
    {
    String sKomunikat=ex.toString();    
    }
    */
    
    /*
    //Przyklad zapytania ze zrodla stowroznego w kontekscie servera
    java.sql.ResultSet wynikZapytania;
    try
    {
    //przykład pobrania danych z bazy mysql    
    wynikZapytania=getConnectionFromContext("mysql").createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from t_sprawozdania");
    wynikZapytania.last();
    int iIloscWierszyMySQL=wynikZapytania.getRow();
    int iIloscKolumn=wynikZapytania.getMetaData().getColumnCount();
    //przykład pobrania danych z bazy mssql 
    wynikZapytania=getConnectionFromContext("mssql").createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from t_dane");        
    wynikZapytania.last();
    int iIloscWierszyMSSQL=wynikZapytania.getRow();
    int iIloscKolumn=wynikZapytania.getMetaData().getColumnCount();
    }
    catch(SQLException ex)
    {
    String sBlad=ex.toString();
    }
     */
        ApplicationData listaUzytkownikow = (ApplicationData) getServletContext().getAttribute("sListaUzytkownikow");         
        String sNextSessionUser="";
        javax.servlet.http.HttpSession  sesja;
        sesja=request.getSession(false);//sprawcz czy jest sesja ale nie tworzy nowej
        if (sesja==null)
        {
        sesja = request.getSession(true);//sprawdza czy jest i jak nie ma tworzy nową sesję
        sNextSessionUser=listaUzytkownikow.getNextUser();
        sesja.setAttribute("uzytkownik", sNextSessionUser);
        }
        else
        {
        sNextSessionUser=(String)sesja.getAttribute("uzytkownik");    
        }
        javax.servlet.http.Cookie [] cookies = request.getCookies();
    
        response.setContentType("text/html;charset=UTF-8");
        
        javax.servlet.http.Cookie cookie1 = new javax.servlet.http.Cookie("param1","wartosc1");
        cookie1.setMaxAge(20);
        javax.servlet.http.Cookie cookie2 = new javax.servlet.http.Cookie("param2","wartosc2");        
        response.addCookie(cookie1);
        cookie2.setMaxAge(10);
        response.addCookie(cookie2);        
        PrintWriter out = response.getWriter();
        
        try {
            /*
             * tresc strony HTML tworzona przez servlet
             */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet mainServlet</title>");  
            out.println("<script type=\"text/javascript\" src=\"javascript/util.js\">");
            out.println("</script>");
  
            out.println("<title>Edycja danych sprawozdania</title>");
            out.println("</head>");            
            out.println("</head>");
            out.println("<body background=\"images/edit.png\">");
            out.println("<h1>Servlet mainServlet w sciezce " + request.getContextPath() + "</h1>");
            out.println("<h1>Servlet uruchomiono :  " + sDataUruchomienia + "</h1>");   
            out.println("<table border=\"5\" background=\"images/header.png\">");
            out.println("<TR>");
            out.println("<TH>");
            out.println("<A href=\"utilServlet\">1000001</A>");
            out.println("</TH>"); 
            out.println("<TD>");
            out.println("kowalski");            
            out.println("</TD>");  
            out.println("<TD>");
            out.println("jan");            
            out.println("</TD>");             
            out.println("<TD>");
            out.println("katowice");            
            out.println("</TD>");  
            out.println("</TR>");  
            out.println("<TR>"); 
            out.println("<TH>");             
            out.println("<A href=\"utilServlet\">1000002</A>");
            out.println("</TH>"); 
            out.println("<TD>");
            out.println("nowak");            
            out.println("</TD>");  
            out.println("<TD>");
            out.println("tomasz");            
            out.println("</TD>");             
            out.println("<TD>");
            out.println("sosnowiec");            
            out.println("</TD>");  
            out.println("</TR>");             
            out.println("</table>");  
            out.println("<A align=\"center\" href=\"index.jsp\">Strona formularza</A></br>");  

            out.println("<input type='text' name='ZatrudnienieBO' value='0' style='background-color:#FFFFFF' size='20' onMouseover=\"showTip(this,event,'Podpowiedz jak wypełnić pole');\" onMouseout=\"hideTip();\"> </br>");           
            out.println("<input type='text' name='ZatrudnienieBO' value='0' style='background-color:#FFFFFF' size='20' onMouseover=\"alert('Opis...');\" onMouseout=\"hideTip();\"> </br>");           
            out.println("<div id='obszarOkna' style='position:absolute;visibility:hidden;clip:rect(0 350 50 0);width:350px;background-color:gold;z-index:10'></div>");
            
            String sWersjaAplikacji =(String)getServletContext().getAttribute("sWersjaAplikacji");
            
            out.println(sWersjaAplikacji);
            
            
            out.println("<h1>Witaj "+sNextSessionUser+"</h1>"); 

           
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
            out.println("</table>"); 
            
            out.println("</body>");
            out.println("</html>");
            //imie nazwisko , czerwone, pogrubione, wysrodkowane , 40 czcionka
            listaUzytkownikow.addMember("Uzytkownik1");
            getServletContext().setAttribute("sListaUzytkownikow", listaUzytkownikow);
       java.util.Enumeration<String> attributeNames = request.getAttributeNames();
       while (attributeNames.hasMoreElements())
       {
        String sNazwaParametru = attributeNames.nextElement();   
       }
        
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
    /*
    @Override    
    public void init(ServletConfig servletConfig)
    {
    //inicjalizacja głównego servletu + parametry pobrane z ustawien serwera 
    //z pliku web.xml Tomcata
    String sParameter=servletConfig.getInitParameter("");
    }   
    */
  
    @Override    
    public void init()
    {
    ////Przyk�ad utworzenia zmiennych globalnych (w kontek�cie aplikacji )
    ApplicationData listaUzytkownikow=new  ApplicationData(); 
    listaUzytkownikow.addMember("osoba 1");
    listaUzytkownikow.addMember("osoba 2");
    listaUzytkownikow.addMember("osoba 3");
    listaUzytkownikow.addMember("osoba 3");
    
    getServletContext().setAttribute("sWersjaAplikacji", "20130212.01");
    getServletContext().setAttribute("sListaUzytkownikow", listaUzytkownikow);    
    //inicjalizacja głównego servletu  
    //inicjalizacja jdbc/activemq/inne
        
    // nawiazanie polaczenia na poziomie aplikacj Web w meotodzie init servletu
    //i zapis do zmiennej instancyjnej databaseConnection serwletu 
    databaseConnection=ApplicationLogic.connectDatabase(); 
    
    //Przykład utworzenia zmiannych globalnych (w instancji klasy)
    java.util.Calendar kalendarzData= java.util.Calendar.getInstance();
    java.util.Date dataUruchomienia=kalendarzData.getTime();
    sDataUruchomienia=dataUruchomienia.toString();

    }
    
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
    @Override
    public void destroy()
    {
    sDataUruchomienia=null;
    try
    {
    databaseConnection.close();       
    }
    catch(Exception ex)
    {
    String sBlad=ex.toString();    
    }
    }

}
