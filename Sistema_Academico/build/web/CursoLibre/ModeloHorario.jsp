<%-- 
    Document   : ModeloHorario
    Created on : 25-abr-2011, 14:54:14
    Author     : Richa R B
--%>

<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>:Modelo Horario:</title>
    </head>
    <f:view>
    <body>
        <jsp:include page="../TablaSistema/Menu.jsp"/>
        <rich:panel>
            <h:form>
            <table width="100%">
                <tr>
                    <td align="left">
                        <h:outputText value="Modelo horario" style="font-weight: bold;"/>
                    </td>
                    <td>
                         <h:graphicImage value="/Imagenes/actions/filenew.png" style="cursor: pointer;" title="Nuevo">
                                                <a4j:support event="onclick" actionListener="#{managerPlantilla.nuevaPlantilla}"
                                                             oncomplete="#{managerPlantilla.oncomplete}"
                                                             reRender="form2"/>
                        </h:graphicImage>
                        <rich:spacer width="8px"/>
                        <a4j:commandButton image="/Imagenes/actions/viewmag.png" reRender="dtLista"
                                           actionListener="#{managerPlantilla.buscarPlantillaHorario}" title="Buscar"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <rich:separator width="100%" height="2"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h:outputText value="Descripcion Horario:"/><rich:spacer width="10px"/>
                        <h:inputText id="iDescripcionHorario" value="#{managerPlantilla.w_pla_descripcion}" size="100"/>
                    </td>
                    <td></td>
                </tr>
            </table>
                        <rich:dataTable value="#{managerPlantilla.listaPlantillaHora}" var="lista" id="dtLista" align="center" width="500">
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Id" />
                                </f:facet>
                                <h:outputText value="#{lista.plantilla.plaId}"/>
                            </rich:column>

                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Descripcion" />
                                </f:facet>
                                <h:outputText value="#{lista.plantilla.plaDescripcion} "/>
                            </rich:column>

                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Descripcion" />
                                </f:facet>
                                <h:outputText value="#{lista.v_descripcion_turno}"/>
                            </rich:column>

                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="" />
                                </f:facet>
                                <f:param id="p_plan_id" value="#{lista.plantilla.plaId}" />
                                <h:graphicImage value="/Imagenes/actions/editpaste.png" style="cursor: pointer;" title="Editar">
                                                <a4j:support event="onclick" actionListener="#{managerPlantilla.editarPlantilla}"
                                                             oncomplete="#{managerPlantilla.oncomplete}"
                                                             reRender="form2"/>
                                </h:graphicImage>
                            </rich:column>

                        </rich:dataTable>

            </h:form>
        </rich:panel>

         <rich:modalPanel id="mpPlantilla" autosized="true" zindex="3000" width="550" height="550">
                <f:facet name="header">
                    <h:outputText value="Mantenimiento de Plantilla"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpPlantilla')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="form2">
                    <table width="100%">
                        <tr>
                            <td colspan="3" align="right">
                                <a4j:commandButton image="/Imagenes/actions/filesave.png"
                                                   alt="Grabar" title="Grabar" oncomplete="#{managerPlantilla.oncomplete}"
                                                   actionListener="#{managerPlantilla.guardarPlantilla}" reRender="dtLista" />
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
                                <h:inputText value="#{managerPlantilla.plantilla.plaDescripcion}" size="60"/>
                            </td>
                            <td></td>
                        </tr>
                         <tr>
                            <td>
                                <h:outputText value="Turno" />
                            </td>
                            <td>
                               <%//<rich:inputNumberSpinner value="#{managerPlantilla.plantilla.palTurno}" maxValue="200"/>%>
                               <h:selectOneMenu value="#{managerPlantilla.plantilla.palTurno}">
                                   <f:selectItems value="#{managerPlantilla.cboTurno}"/>
                               </h:selectOneMenu>
                            </td>
                            <td></td>
                         </tr>
                         <tr>
                            <td>
                                <h:outputText value="Fecha Creacion" />
                            </td>
                            <td>
                                <rich:calendar datePattern="dd/MM/yyyy" value="#{managerPlantilla.plantilla.plaFechaCreacion}"/>
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
                                <h:inputText id="iDetalleDesc" value="" size="60"/>
                            </td>
                            <td>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h:outputText value="Orden"/>
                            </td>
                            <td>
                                <rich:inputNumberSpinner id="iDetelleOrden" value="" maxValue="200"/>
                            </td>
                            <td align="right">
                                <h:graphicImage value="/Imagenes/actions/edit_add.png" style="cursor: pointer;" title="Agregar">
                                                <a4j:support event="onclick" actionListener="#{managerMedio.agregarTemporal}"
                                                             reRender="dtDetalle,iDetalleDesc,iDetelleOrden" oncomplete="#{managerMedio.oncomplete}"/>
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
