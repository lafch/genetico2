<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Configuración de Planes Curriculares</title>
    </head>
    <body>
        <f:view>
            <jsp:include page="../TablaSistema/Menu.jsp"/>
            <rich:panel>
                <h:form id="form1">
                    <table style="font-size:13px; font-family:verdana; width: 100%">
                        <tr>
                            <td width="100%" colspan="10">
                                <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                    <tr>
                                        <td align="left"><h:outputText value="MANTENIMIENTO DE PLANES CURRICULARES" style="font-weight: bold;"/></td>
                                        <td align="right">
                                            <rich:spacer width="8px"/>

                                            <h:graphicImage value="/Imagenes/actions/filenew.png" title="Nuevo" style="cursor: pointer;">
                                                <a4j:support event="onclick"
                                                             actionListener="#{managerCLPlancurricular.Nuevo}"
                                                             oncomplete="#{managerCLPlancurricular.oncomplete}"
                                                             reRender="iCodigo,iDescripcion,iArea,iModulop,iVigente"/>
                                            </h:graphicImage>
                                            <rich:spacer width="8px"/>

                                            <a4j:commandButton image="/Imagenes/actions/viewmag.png"
                                                               title="Buscar" oncomplete="#{managerCLPlancurricular.oncomplete2}"
                                                               actionListener="#{managerCLPlancurricular.Seleccionar}"
                                                               reRender="tablaMaster, paginacion"/>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="10"><hr size="1"></td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputText value="Descripción :"/></td>
                            <td colspan="9" align="left"><h:inputText id="b_descripcion" value="#{managerCLPlancurricular.b_descripcion}" style="width: 300px;"/></td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputText value="Area :"/></td>
                            <td>
                                <h:selectOneMenu value="#{managerCLPlancurricular.b_are_id}">
                                    <f:selectItems value="#{managerCLPlancurricular.b_areas}" />
                                    <a4j:support event="onchange" reRender="iModulo" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputText value="Módulo :"/></td>
                            <td>
                                <h:selectOneMenu id="iModulo" value="#{managerCLPlancurricular.b_mod_id}">
                                    <f:selectItems value="#{managerCLPlancurricular.b_modulos}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                    </table>
                </h:form>
                <table width="100%">
                    <tr>
                        <td align="center">
                            <h:form id="form3">
                                <rich:datascroller id="paginacion" align="right" for="tablaMaster"
                                                   maxPages="10" style="width : 80%;"/>
                                <rich:spacer height="20px"/>
                                <rich:dataTable id="tablaMaster"
                                                onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                onRowMouseOut="this.style.backgroundColor='#FFFFFF'"
                                                cellpadding="0" cellspacing="0"
                                                width="80%" rows="10" value="#{managerCLPlancurricular.planes}"
                                                var="plan">

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Id" />
                                        </f:facet>
                                        <h:outputText value="#{plan.pla_id}"/>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Código" />
                                        </f:facet>
                                        <h:outputText value="#{plan.pla_codigo}"/>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Descripción" />
                                        </f:facet>
                                        <h:outputText value="#{plan.pla_descripcion}"/>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Modulo" />
                                        </f:facet>
                                        <h:outputText value="#{plan.mod_descripcion}"/>
                                    </rich:column>

                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Vigente" />
                                        </f:facet>
                                        <h:graphicImage value="#{plan.imagen_pla_vigente}"/>
                                    </rich:column>

                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Actual" />
                                        </f:facet>
                                        <h:graphicImage value="#{plan.imagen_pla_actual}"/>
                                    </rich:column>

                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Editar"/>
                                        </f:facet>
                                        <f:param id="id_update" value="#{plan.pla_id}"/>
                                        <a4j:commandButton image="/Imagenes/actions/icon_edit.png"
                                                           title="Modificar"
                                                           actionListener="#{managerCLPlancurricular.ActualizarPlanCurricular}"
                                                           oncomplete="#{managerCLPlancurricular.oncomplete}"
                                                           reRender="iCodigo,iDescripcion,iArea,iModulop,iVigente"/>
                                    </rich:column>

                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Eliminar" />
                                        </f:facet>
                                        <f:param id="id_delete" value="#{plan.pla_id}"/>
                                        <h:commandButton image="/Imagenes/actions/delete.gif"
                                                         title="Eliminar"
                                                         actionListener="#{managerCLPlancurricular.EliminarPlanCurricular}"
                                                         onclick="javascript:return (confirm('¿Esta realmente seguro de Eliminar?'));"/>
                                    </rich:column>
                                </rich:dataTable>
                            </h:form>
                        </td>
                    </tr>
                </table>
            </rich:panel>

            <rich:modalPanel id="mpUpdate" autosized="true" zindex="3000" width="700">
                <f:facet name="header">
                    <h:outputText value="Plan Curricular"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpUpdate')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="form2">
                    <table style="font-size:12px; font-family:verdana; width: 100%;">
                        <tr>
                            <td colspan="2" align="right">
                                <a4j:commandButton image="/Imagenes/actions/filesave.png"
                                                   title="Guardar"
                                                   actionListener="#{managerCLPlancurricular.GuardarPlanCurricular}"
                                                   oncomplete="#{managerCLPlancurricular.oncomplete}"
                                                   reRender="tablaMaster,paginacion"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr size="1"></td>
                        </tr>
                        <tr>
                            <td style="width: 80px;"><h:outputText value="Código"/>
                            </td>
                            <td>
                                <h:inputText id="iCodigo" value="#{managerCLPlancurricular.i_pla_codigo}"/>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Descripción"/>
                            </td>
                            <td>
                                <h:inputText id="iDescripcion" value="#{managerCLPlancurricular.i_pla_descripcion}" style="width: 300px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Area"/>
                            </td>
                            <td>
                                <h:selectOneMenu id="iArea" value="#{managerCLPlancurricular.i_are_id}"
                                                 disabled="#{managerCLPlancurricular.edit_disable}">
                                    <f:selectItems value="#{managerCLPlancurricular.i_areas}" />
                                    <a4j:support event="onchange" reRender="iModulop" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Módulo"/>
                            </td>
                            <td>
                                <h:selectOneMenu id="iModulop" value="#{managerCLPlancurricular.i_mod_id}"
                                                 disabled="#{managerCLPlancurricular.edit_disable}">
                                    <f:selectItems value="#{managerCLPlancurricular.i_modulos}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Estado"/>
                            </td>
                            <td>
                                <h:selectOneMenu id="iVigente" value="#{managerCLPlancurricular.i_pla_vigente}">
                                    <f:selectItems value="#{managerCLPlancurricular.i_vigentes}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>
        </f:view>
    </body>
</html>