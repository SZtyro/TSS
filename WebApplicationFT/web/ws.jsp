

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
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
            //output = document.getElementById("output");
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
            for (i = 0; i < json.length; i++) {
                d.push({y: json[i]});

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
    <body>
        <h1>Generowanie wykresu przez WS</h1>
        <br>
        <h5>Wiadomosc z ws:</h5>
        <p id="messageID"></p>
   
        
        <div id="chartContainer" style="width: 70%;margin: 0 auto">

        </div>
    </body>
</html>
