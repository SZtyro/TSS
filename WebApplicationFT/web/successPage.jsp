
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error!</title>
    </head>
    <style>
        button{
            margin:40px;
            color:white;
            font-weight:700;
            width:140px;
            height:80px;
            background-color:#273746;
            border-radius:5px;
            border:none; 
        }
    </style>
    <body style="background-color:#2ECC71;text-align:center;">


        <div style="color:white;font-weight:600;margin:30px;font-size:50px;">${message}</div>

        <div style="display:flex; flex-direction: column;">
            <a href="${link}">
                <button>Powrot</button>
            </a>
            <a href="https://localhost:8443/WebApplicationFT">
                <button>Powrot do menu</button>
            </a>
        </div>

    </body>
</html>
