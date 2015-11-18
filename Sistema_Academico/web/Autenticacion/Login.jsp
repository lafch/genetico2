<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich" %>

<%
            HttpSession sesion = request.getSession();
          
%>
<f:view>
    <html>
        <head>
            <title>.:: Sistema Academico UCH ::.</title>
            <meta http-equiv="Expires" content="0">
            <meta http-equiv="Last-Modified" content="0">
            <meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
            <meta http-equiv="Pragma" content="no-cache">
            <link rel="icon" type="image/x_icon" href="/Sistema_Academico/Imagenes/actions/pinguino.gif"/>
        </head>

        <body onload="document.getElementById('formulario:j_username').focus()" class="body-login">
            <a4j:loadStyle src="resource:///css/formulario.css"  />
            <h:form id="formulario" styleClass="formulario">
               
                <div align="center" class="divLogin">
                    <div class="divLoginForm">
                        <h1 class="text-center">
                            <div id="logofull">
                                <img class="img-responsive" src="/Imagenes/logointrafull.png">
                            </div>
                        </h1>
                            <h:inputText id="j_username" size="27" required="true" label="Usuario" value="#{managerUsuario.usu_usu}"/>
                            <h:inputSecret id="j_password"  size="27" required="true" label="Contraseña" value="#{managerUsuario.usu_psw}" />
                            <h:commandButton value="Ingresar" action="#{managerUsuario.checkUser}" styleClass="btn btn-primary btn-lg btn-block" />
                            <br>
                            
                            <div class="text-info text-center"> Área de Sistemas e Informática - sistemas@uch.edu.pe </div>
                    </div>
                </div>
                <div id="footer" class="navbar-fixed-bottom well">
                <div class="container-fluid text-center"> Copyright © 2015 Universidad de Ciencias y Humanidades </div>
                </div>            
            </h:form>
        </body>
    </html>
</f:view>