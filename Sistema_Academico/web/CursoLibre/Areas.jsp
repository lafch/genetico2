<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Configuracion de Areas</title>
    </head>
    <body>
        <f:view>

            <jsp:include page="../TablaSistema/Menu.jsp"/>
            <rich:panel>
                <h:form id="form1">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-size:10px; font-family:verdana">
                        <tr>
                            <td width="100%" colspan="10">
                                <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                    <tr>
                                        <td align="left"><h:outputText value="MANTENIMIENTO DE AREAS" style="font-weight: bold;"/></td>
                                        <td align="right">
                                            <rich:spacer width="8px"/>

                                            <h:graphicImage value="/Imagenes/actions/filenew.png" style="cursor: pointer;" title="Nuevo">
                                                <a4j:support event="onclick" actionListener="#{managerCLArea.Nuevo}" 
                                                             oncomplete="#{managerCLArea.oncomplete}"
                                                             reRender="iCodArea,iCodDescripcion,cboInstitucion"/>
                                            </h:graphicImage>
                                            <rich:spacer width="8px"/>

                                            <h:commandButton image="/Imagenes/actions/viewmag.png" action="#{managerCLArea.Seleccionar}" title="Buscar"/>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr style="height: 20px;">
                            <td colspan="10"><hr size="1"></td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputText value="Descripción :"/></td>
                            <td colspan="9" align="left"><h:inputText id="b_descripcion" value="#{managerCLArea.b_descripcion}" style="width: 300px;"/></td>
                        </tr>
                        <tr style="height: 20px;">
                            <td colspan="10"><hr size="1"></td>
                        </tr>
                    </table>
                </h:form>
                <h:form id="form3">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-size:10px; font-family:verdana">
                        <tr>
                            <td colspan="10" align="center" valign="bottom" width="100%">
                                <div style="width: 90%">
                                    <p align="right"/>
                                    <rich:datascroller id="paginacion" align="right" for="tablaMaster" maxPages="10" style="width : 100%;"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="10" valign="top" align="center">
                                <rich:dataTable id="tablaMaster" width="90%" rows="10"
                                                onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                onRowMouseOut="this.style.backgroundColor='#FFFFFF'"
                                                cellpadding="0" cellspacing="0"
                                                value="#{managerCLArea.areas}" var="area">
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Id" />
                                        </f:facet>
                                        <h:outputText value="#{area.are_id}"/>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Código" />
                                        </f:facet>
                                        <h:outputText value="#{area.are_codigo}" rendered="#{area.ver}"/>
                                        <h:inputText value="" rendered="#{area.editar}"/>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Descripción" />
                                        </f:facet>
                                        <h:outputText value="#{area.are_descripcion}" rendered="#{area.ver}"/>
                                        <h:inputText value="" rendered="#{area.editar}"/>
                                    </rich:column>
                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Editar"/>
                                        </f:facet>
                                        <f:param id="id_update" value="#{area.are_id}"/>
                                        <a4j:commandButton image="/Imagenes/actions/editpaste.png"
                                                           title="Modificar"
                                                           actionListener="#{managerCLArea.ActualizarArea}"
                                                           oncomplete="#{managerCLArea.oncomplete}"
                                                           reRender="iCodArea,iCodDescripcion, cboInstitucion"/>
                                    </rich:column>
                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Eliminar" />
                                        </f:facet>
                                        <f:param id="id_delete" value="#{area.are_id}"/>
                                        <a4j:commandButton image="/Imagenes/actions/no.png"
                                                           title="Eliminar"
                                                           actionListener="#{managerCLArea.EliminarArea}"
                                                           action="#{managerCLArea.Seleccionar}"
                                                           oncomplete="#{managerCLArea.message}"
                                                           reRender="tablaMaster"
                                                           />
                                    </rich:column>
                                    <rich:column style="text-align: center;" rendered="#{managerCLArea.usuId == 1}">
                                        <f:facet name="header">
                                            <h:outputText value="Ocultar de Arbol" />
                                        </f:facet>
                                        <f:param id="id_ocultar_arbol" value="#{area.are_id}"/>
                                        <a4j:commandButton image="/Imagenes/actions/#{area.img_oculto}"
                                                           title="#{area.title_img_oculto}"
                                                           actionListener="#{managerCLArea.ocultarArbol}"
                                                           oncomplete="#{managerCLArea.message}"
                                                           reRender="tablaMaster"
                                                           />
                                    </rich:column>
                                </rich:dataTable>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:panel>

            <rich:modalPanel id="mp1" autosized="true" zindex="3000" width="450" height="120">
                <f:facet name="header">
                    <h:outputText value="Area"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp1')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="form2">
                    <table style="font-size:12px; font-family:verdana; width: 100%">
                        <tr>
                            <td colspan="2" align="right">
                                <a4j:commandButton image="/Imagenes/actions/filesave.png"
                                                   title="Guardar"
                                                   actionListener="#{managerCLArea.GuardarArea}"
                                                   action="#{managerCLArea.Seleccionar}"
                                                   oncomplete="#{managerCLArea.oncomplete}" 
                                                   reRender="tablaMaster,paginacion, cboInstitucion"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr size="1"></td>
                        </tr>
                        <tr>
                            <td style="width: 70px;"><h:outputText value="Institución"/>
                            </td>
                            <td>
                                <h:selectOneMenu id="cboInstitucion" value="#{managerCLArea.iInstitucionId}" 
                                                 disabled="#{managerCLArea.edit_disable}">
                                    <f:selectItems value="#{managerCLArea.cboInstitucion}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 70px;"><h:outputText value="Codigo"/>
                            </td>
                            <td>
                                <h:inputText id="iCodArea" value="#{managerCLArea.i_are_codigo}"/>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Descripcion"/>
                            </td>
                            <td>
                                <h:inputText id="iCodDescripcion" value="#{managerCLArea.i_are_descripcion}" style="width: 100%;"/>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>
        </f:view>
    </body>
</html>