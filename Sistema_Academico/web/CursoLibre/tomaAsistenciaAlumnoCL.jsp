<%-- 
    Document   : tomaAsistenciaAlumnoCL
    Created on : 02-jul-2013, 16:56:06
    Author     : JTEJADA
--%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de asistencia de estudiantes</title>
    </head>
    <f:view>
        <body>
            <jsp:include page="../TablaSistema/Menu.jsp"></jsp:include>
            <rich:panel>
                <h:form id="frmFiltroListadoAsistencias" style="width:100%">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-size:10px; font-family:verdana">
                        <tr>
                            <td width="100%" colspan="10">
                                <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                    <tr>
                                        <td align="left"><h:outputText value="REGISTRO DE ASISTENCIAS DE ESTUDIANTES" style="font-weight: bold;"/></td>
                                        <td align="right">
                                            <rich:spacer width="8px"/>
                                            <h:commandButton image="/Imagenes/actions/viewmag.png" title="Buscar"/>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr style="height: 20px;">
                            <td colspan="10">
                                <hr size="1" />
                            </td>
                        </tr>
                        <tr>
                            <td width="10%">
                                <h:outputText value="Código :" />
                            </td>
                            <td colspan="9" align="left">
                                <h:inputText value="#{managerRegistroAsistenciaAlumnoCL.codAlumnoFiltro}" />
                            </td>
                        </tr>
                        <tr>
                            <td width="10%">
                                <h:outputText value="Fecha Inicio :" />
                            </td>
                            <td colspan="9" align="left">
                                <rich:calendar value="#{managerRegistroAsistenciaAlumnoCL.fechaInicioFiltro}" />
                            </td>
                        </tr>
                        <tr>
                            <td width="10%">
                                <h:outputText value="Fecha Fin :" />
                            </td>
                            <td colspan="9" align="left">
                                <rich:calendar value="#{managerRegistroAsistenciaAlumnoCL.fechaFinFiltro}" />
                            </td>
                        </tr>
                        <tr style="height: 20px;">
                            <td colspan="10">
                                <hr size="1" />
                            </td>
                        </tr>
                    </table>
                </h:form>
                <h:form id="frmListadoAsistencias">
                    <rich:datascroller id="paginacion" align="right" for="tbListadoAsistencias"  maxPages="10" style="width : 100%;"/>
                    <rich:spacer height="20px"/>
                    <rich:dataTable id="tbListadoAsistencias"
                                    onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                    onRowMouseOut="this.style.backgroundColor='#FFFFFF'"
                                    cellpadding="0"
                                    cellspacing="0"
                                    width="100%"
                                    rows="10" 
                                    value="#{managerRegistroAsistenciaAlumnoCL.lstAsistencias}" 
                                    var="regAsist" >
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="#" />
                            </f:facet>
                            <h:outputText value="#{regAsist.expr13}"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Fecha" />
                            </f:facet>
                            <h:outputText value="#{regAsist.expr9}"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Inicio" />
                            </f:facet>
                            <h:outputText value="#{regAsist.expr10}"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Fin" />
                            </f:facet>
                            <h:outputText value="#{regAsist.expr11}"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Docente" />
                            </f:facet>
                            <h:outputText value="#{regAsist.expr5}"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Docente" />
                            </f:facet>
                            <h:outputText value="#{regAsist.expr7}"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Curso" />
                            </f:facet>
                            <h:outputText value="#{regAsist.expr8}"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Módulo" />
                            </f:facet>
                            <h:outputText value="#{regAsist.expr6}"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Asistencia" />
                            </f:facet>
                            <h:outputText value="#{regAsist.expr12}"/>
                        </rich:column>
                    </rich:dataTable>
                </h:form>
            </rich:panel>
        </body>
    </f:view>
</html>
