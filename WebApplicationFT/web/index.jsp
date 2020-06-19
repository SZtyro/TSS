
<%@page import="pl.data.CRUD"%>
<%@page import="pl.data.DataLogic"%>
<%@page import="pl.resources.Osoba"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1250">
        <title>JSP Page</title>
        <script src="js/canvasjs.min.js"></script>
        <script language="javascript" type="text/javascript">

            var wsUri = getRootUri() + "/WebApplicationFT/endpoint";
            function getRootUri() {
                var wsUri = (location.protocol === "http:" ? "ws://" : "wss://");
                return wsUri + (document.location.hostname === "" ?
                        "localhost" :
                        document.location.hostname) + ":" +
                        (document.location.port === "" ? "8080" :
                                document.location.port);
            }

            function init() {
                output = document.getElementById("output");
                initWebSocket();
            }
            function initWebSocket() {
                websocket = new WebSocket(wsUri);
                websocket.onopen = function (evt) {
                    onOpen(evt);
                };
                websocket.onmessage = function (evt) {
                    onMessage(evt);
                };
                websocket.onerror = function (evt) {
                    onError(evt);
                };
               
            }
            ;

            function onOpen(evt) {
                writeToScreen("connected");
            }
            function onMessage(evt) {
                writeToScreen(evt.data);
                var json = JSON.parse(evt.data);
                var d = [];
                for(i=0; i<json.length ;i++){
                    d.push({y:json[i]});
                    
                }
                var chart = new CanvasJS.Chart("chartContainer", {
                    title: {
                        text: "Losowe liczby"
                    },
                    data: [
                        {
                            // Change type to "doughnut", "line", "splineArea", etc.
                            type: "column",
                            dataPoints: d
                        }
                    ]
                });
                chart.render();
            }
            function onError(evt) {
                writeToScreen("error" + evt.data);
            }
            function doSend(msg) {
                writeToScreen("sent" + msg);
                websocket.send(msg);
                
            }
            function writeToScreen(msg) {
                var pre = document.getElementById("messageID");
                pre.innerHTML = msg;
                //pre.value = msg;

            }
            window.addEventListener("load", init, false);
           
        </script>
    </head>

    <body bgcolor="#e0e0e0" >



        <h1>Autor projektu: </h1>
        <p>Fabian Trubic</p>

        <%
            //java.util.Date date = new java.util.Date();
            //out.println("<p>" + date + "</p>");
            //DataLogic logic = new DataLogic();
            //CRUD crud = new CRUD();
            //crud.setLogic(logic);
            //ArrayList<Osoba> li = crud.fetchData();
            //logic.fetchData();
            

            //request.setAttribute("connection", logic.connection);
            //request.setAttribute("logic", logic);
            //request.setAttribute("list", li);
            //request.setAttribute("logic", logic);
        %>
        <!--
        <br>
        <h1>Logowanie do bazy:</h1>
        <p><i><strong>host:</strong> 155.158.112.31 <strong>user:</strong> tomcatuser <strong>password:</strong> tomcat</i></p>
        <br>
        <form method="post" action="mainServlet?method=login">
            <input type="text" name="host" value="155.158.112.31">
            <input type="text" name="user" value="tomcatuser">
            <input type="password" name="password" value="tomcat">      
            <input type="submit" value="Zaloguj">
        </form>
        <br>
        -->
        <form  method="get" action="mainServlet">
            <input type="submit" value="Baza danych">
        </form>
        
        <br>
        <h1>Komunikacja z WS</h1>
        <p id="messageID"></p>
   
        <button onclick="doSend()"> Generuj wykres </button>
        <div id="chartContainer" style="width: 70%;margin: 0 auto">

        </div>
    </body>
</html>
