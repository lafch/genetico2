<%-- 
    Document   : MantenimientoMedio
    Created on : 25-abr-2011, 14:54:14
    Author     : Richard R B
--%>

<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>:Mantenimiento Medio:</title>
    </head>
    <f:view>
    <body>
        <jsp:include page="../TablaSistema/Menu.jsp"/>
        <rich:panel>
            <h:form>
            <table width="100%">
                <tr>
                    <td align="left">
                        <h:outputText value="MANTENIMIENTO DE MEDIOS DE PUBLICIDAD" style="font-weight: bold;"/>
                    </td>
                    <td>
                         <h:graphicImage value="/Imagenes/actions/filenew.png" style="cursor: pointer;" title="Nuevo">
                                                <a4j:support event="onclick" actionListener="#{managerMedio.nuevoRegistro}"
                                                             oncomplete="#{managerMedio.oncomplete}"
                                                             reRender="form2"/>
                        </h:graphicImage>
                        <rich:spacer width="8px"/>
                        <a4j:commandButton image="/Imagenes/actions/viewmag.png" reRender="dtLista"
                                           actionListener="#{managerMedio.listarMediosDePublicidad}" title="Buscar"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <rich:separator width="100%" height="2"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h:outputText value="Descripcion Medio :"/><rich:spacer width="10px"/>
                        <h:inputText id="iDescripcionMedio" value="#{managerMedio.w_medio_descripcion}" size="100"/>
                    </td>
                    <td></td>
                </tr>
            </table>
                        
                        <rich:dataTable value="#{managerMedio.listaMedioPublicidad}"
                                        align="center" id="dtLista" var="lista" width="700">
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Id"/>
                                </f:facet>
                                <h:outputText value="#{lista.idPublicidad}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Descripciion"/>
                                </f:facet>
                                <h:outputText value="#{lista.descripcion}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Orden"/>
                                </f:facet>
                                <h:outputText value="#{lista.orden}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value=""/>
                                </f:facet>
                                <f:param id="p_med_id" value="#{lista.idPublicidad}" />
                                <h:graphicImage value="/Imagenes/actions/editpaste.png" style="cursor: pointer;" title="Editar">
                                                <a4j:support event="onclick" actionListener="#{managerMedio.seleccionarMedio}"
                                                             oncomplete="#{managerMedio.oncomplete}"
                                                             reRender="form2"/>
                                </h:graphicImage>
                            </rich:column>
                        </rich:dataTable>

            </h:form>
        </rich:panel>

        <rich:modalPanel id="mpMedio" autosized="true" zindex="3000" width="550" height="550">
                <f:facet name="header">
                    <h:outputText value="Mantenimiento de Medio"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpMedio')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="form2">
                    <table width="100%">
                        <tr>
                            <td colspan="3" align="right">
                                <a4j:commandButton image="/Imagenes/actions/filesave.png" 
                                                   alt="Grabar" title="Grabar" oncomplete="#{managerMedio.oncomplete}"
                                                   actionListener="#{managerMedio.guardarRegistros}" reRender="dtLista" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <rich:separator width="100%" height="1"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h:outputText value="Descripcion" />
                            </td>
                            <td>
                                <h:inputText value="#{managerMedio.medioPublicidad.descripcion}" size="60"/>
                            </td>
                            <td></td>
                        </tr>
                         <tr>
                            <td>
                                <h:outputText value="Orden" />
                            </td>
                            <td>
                                <rich:inputNumberSpinner value="#{managerMedio.medioPublicidad.orden}" maxValue="200"/>
                            </td>
                            <td></td>
                         </tr>

                        <tr>
                            <td colspan="3"><rich:separator width="100%" height="2"/> </td>
                        </tr>
                        <tr>
                            <td>
                                <h:outputText value="Descripcion"/>
                            </td>
                            <td>
                                <h:inputText id="iDetalleDesc" value="#{managerMedio.medioPublicidaDet.pubDetDes}" size="60"/>
                            </td>
                            <td>
                                
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h:outputText value="Orden"/>
                            </td>
                            <td>
                                <rich:inputNumberSpinner id="iDetelleOrden" value="#{managerMedio.medioPublicidaDet.pubDetOrd}" maxValue="200"/>
                            </td>
                            <td align="right">
                                <h:graphicImage value="/Imagenes/actions/edit_add.png" style="cursor: pointer;" title="Agregar">
                                                <% /*<a4j:support event="onclick" actionListener="#{managerMedio.agregarTemporal}"
                                                             reRender="dtDetalle,iDetalleDesc,iDetelleOrden" oncomplete="#{managerMedio.oncomplete}"/> */%>
                                </h:graphicImage>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                               
                            </td>
                        </tr>
                      
                    </table>
                </h:form>
        </rich:modalPanel>
    </body>
    </f:view>
</html>
