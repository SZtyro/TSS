
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

    <body bgcolor="#e0e0e0">



        <h1>Autor projektu: </h1>
        <p>Fabian Trubic</p>


        <h1>Aktualna data: </h1>
        <%
            java.util.Date date = new java.util.Date();
            out.println("<p>" + date + "</p>");
            DataLogic logic = new DataLogic();
            logic.fetchData();
            ArrayList<Osoba> li = logic.lista;

            request.setAttribute("connection", logic.connection);
            request.setAttribute("logic", logic);
            request.setAttribute("list", li);
            request.setAttribute("logic", logic);
        %>



        <table style="border: solid; width: 70%;margin: 0 auto" >
            <tr>
                <th>#</th>
                <th>Imie</th>
                <th>Nazwisko</th>
                <th>Opis</th>
                <th>Akcje</th>
            </tr>
            <tbody>

                <c:forEach var="i" items="${list}" varStatus="loop">
                    <tr>                   
                        <td><c:out value="${i.id}"/> </td>
                        <td><c:out value="${i.imie}"/> </td>
                        <td><c:out value="${i.nazwisko}"/> </td>
                        <td><c:out value="${i.opis}"/> </td>
                        <td>
                            <form method="post" action="DeleteServlet">
                                <button style="background-color:#ff6347;border-radius:5px;border:none;" 
                                        name="Usun" value="${i.id}">Usun
                                    <input hidden type="submit">
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>


            </tbody>
        </table>
        <br>
        <form method="post" action="mainServlet">
            <input type="text" name="Id" value="Podaj id">
            <input type="text" name="Imie" value="Podaj imie">
            <input type="text" name="Nazwisko" value="Podaj nazwisko">
            <input type="text" name="Opis" value="Podaj opis">
            <input type="submit" value="Dodaj osobe">
        </form>
        <br>
        <h1>Komunikacja z WS</h1>
        <p id="messageID"></p>
   
        <button onclick="doSend()"> Generuj wykres </button>
        <div id="chartContainer" style="width: 70%;margin: 0 auto">

        </div>
    </body>
</html>
