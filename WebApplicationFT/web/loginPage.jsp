
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Zaloguj sie</h1>
        <div clas="col-sm-12" style="overflow: hidden;">    
            <div class="login-box col-sm-6 col-xs-12">    
                <form method="post" action="j_security_check">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Login</label>
                        <input type="text" class="form-control" id="login" name="j_username" value="ide">
                    </div>
                   
                    <div class="form-group">
                        <label for="exampleInputPassword1">Hasło</label>
                        <input type="password" class="form-control" id="password" name="j_password" value="dkfPiGzO">
                    </div>
                    <button type="submit" class="btn btn-primary">Zatwierdź</button>
                </form>
            </div>
        </div>
    </body>
</html>
