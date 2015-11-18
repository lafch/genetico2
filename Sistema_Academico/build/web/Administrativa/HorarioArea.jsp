<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Configuración de Inicios</title>
    </head>
    <body>
        <f:view>
            <h:form id="form1">
                <jsp:include page="../TablaSistema/Menu.jsp"/>
                <rich:panel>
                    <rich:dataTable value="#{managerHorarioArea}" var="hor">
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Nro"/>
                            </f:facet>
                            <h:outputText value="#{hor.b_are_nombre}"/>
                        </rich:column>
                    </rich:dataTable>
                </rich:panel>
            </h:form>
        </f:view>
    </body>
</html>