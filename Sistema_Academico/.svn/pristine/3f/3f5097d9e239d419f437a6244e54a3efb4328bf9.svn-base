<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
HttpSession sesionOk = request.getSession();
sesionOk.removeAttribute("usuario");
sesionOk.invalidate();

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <jsp:forward page="/Autenticacion/Login.jsf" />
    </body>	
</html>  
