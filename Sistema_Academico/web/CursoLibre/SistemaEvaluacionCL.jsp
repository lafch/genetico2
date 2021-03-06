<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Configuracion de Sistemas de Evaluacion</title>
    </head>
    <body>
        <f:view>
            <jsp:include page="../TablaSistema/Menu.jsp"/>
            <rich:panel>
                <h:form id="form1">
                    <table style="font-size:13px; font-family:verdana; width: 100%">
                        <tr>
                            <td colspan="2"><strong>MANTENIMIENTO DE SISTEMAS DE EVALUACION</strong></td>
                            <td align="right">
                                <rich:spacer width="8px"/>

                                <h:graphicImage value="/Imagenes/actions/filenew.png" title="Nuevo" style="cursor: pointer;">
                                    <a4j:support event="onclick"
                                                 actionListener="#{managerCLSisEvaluacion.nuevo}"
                                                 oncomplete="Richfaces.showModalPanel('mpUpdate');"
                                                 reRender="formUpdate"/>
                                </h:graphicImage>
                                <rich:spacer width="8px"/>

                                <a4j:commandButton image="/Imagenes/actions/viewmag.png" title="Buscar"
                                                   actionListener="#{managerCLSisEvaluacion.buscarSistemaEvaluacion}"
                                                   reRender="form3"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3"><hr size="1"></td>
                        </tr>
                        <tr>
                            <td style="width: 80px;"><h:outputText value="Descripci�n"/></td>
                            <td><h:inputText value="#{managerCLSisEvaluacion.p_descripcion}" style="width: 300px;"/></td>
                        </tr>
                        <tr>
                            <td colspan="3"><hr size="1"></td>
                        </tr>
                    </table>
                </h:form>
                <table width="100%">
                    <tr>
                        <td align="center">
                            <h:form id="form3">
                                <rich:datascroller id="paginacion" align="right" for="tablaMaster" 
                                                   maxPages="10" style="width: 80%;"/>
                                <rich:spacer height="20px"/>
                                <rich:dataTable id="tablaMaster" width="80%" rows="10"
                                                onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                onRowMouseOut="this.style.backgroundColor='#FFFFFF'"
                                                cellpadding="0" cellspacing="0"
                                                value="#{managerCLSisEvaluacion.sistemas_evaluacion}"
                                                var="siseva">
                                    <f:param id="id_siseva_tmp" value="#{siseva.sisevaId}"/>
                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Id" />
                                        </f:facet>
                                        <h:outputText value="#{siseva.sisevaId}"/>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Codigo" />
                                        </f:facet>
                                        <h:outputText value="#{siseva.sisevaCodigo}"/>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Nombre" />
                                        </f:facet>
                                        <h:outputText value="#{siseva.sisevaNombre}"/>
                                    </rich:column>

                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Vigente" />
                                        </f:facet>
                                        <h:graphicImage value="#{siseva.sisevaImgVigente}"/>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Fec. creacion" />
                                        </f:facet>
                                        <h:outputText value="#{siseva.sisevaCreacion}" >
                                            <f:convertDateTime pattern="EEEEE dd  'de'  MMMMMM  'del'  yyyy" />
                                        </h:outputText>
                                    </rich:column>

                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Mostrar Plantilla" />
                                        </f:facet>
                                        <a4j:commandButton image="/Imagenes/actions/klipper_dock.png" title="Ver Plantilla"
                                                           actionListener="#{managerCLSisEvaluacion.mostrarPlantilla}"
                                                           reRender="tbClSisEvaPerPlant" oncomplete="#{managerCLSisEvaluacion.oncomplete}"/>
                                    </rich:column>
                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Ver" />
                                        </f:facet>
                                        <a4j:commandButton image="#{siseva.m_imagen_mostrar}" title="#{siseva.m_texto_mostrar}"
                                                           actionListener="#{managerCLSisEvaluacion.mostrarDetalle}"
                                                           reRender="tablaMaster"/>
                                    </rich:column>

                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Editar" />
                                        </f:facet>
                                        <a4j:commandButton image="/Imagenes/actions/editpaste.png" title="Editar"
                                                           actionListener="#{managerCLSisEvaluacion.seleccionarSistemaEvaluacion}"
                                                           oncomplete="Richfaces.showModalPanel('mpUpdate');"
                                                           reRender="formUpdate"/>
                                    </rich:column>

                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Eliminar" />
                                        </f:facet>
                                        <a4j:commandButton image="/Imagenes/actions/delete.gif" title="Eliminar"
                                                           actionListener="#{managerCLSisEvaluacion.eliminarSistemaEvaluacion}"
                                                           oncomplete="#{managerCLSisEvaluacion.oncomplete}"
                                                           reRender="tablaMaster,paginacion"/>
                                    </rich:column>

                                    <rich:subTable  value="#{siseva.siseva_detalles}" var="siseva_det"
                                                    rendered="#{siseva.verDetalle}" id="subtable"
                                                    onRowMouseOver="this.style.backgroundColor='#F8F8F8'"
                                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'">
                                        <f:param id="id_siseva_tmp2" value="#{siseva.sisevaId}"/>
                                        <f:param id="id_sisevadet_tmp" value="#{siseva_det.sisevaDetalleId}"/>
                                        <rich:column style="text-align: center;">
                                            <f:facet name="header">
                                                <h:outputText value="Id" />
                                            </f:facet>
                                            <h:outputText value="#{siseva_det.sisevaDetalleId}"/>
                                        </rich:column>

                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Codigo" />
                                            </f:facet>
                                            <h:outputText value="#{siseva_det.sisevaDetalleCodigo}"/>
                                        </rich:column>

                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Nombre" />
                                            </f:facet>
                                            <h:outputText value="#{siseva_det.sisevaDetalleNombre}"/>
                                        </rich:column>

                                        <rich:column style="text-align: center;">
                                            <f:facet name="header">
                                                <h:outputText value="Peso" />
                                            </f:facet>
                                            <h:outputText value="#{siseva_det.sisevaDetallePeso}"/>
                                        </rich:column>

                                        <rich:column style="text-align: center;">
                                            <f:facet name="header">
                                                <h:outputText value="Sustitutorio" />
                                            </f:facet>
                                            <h:outputText value="#{siseva_det.sisevaDetalleDescSusti}"/>
                                        </rich:column>
                                        <rich:column style="text-align: center;">
                                            <f:facet name="header">
                                                <h:outputText value="Mostrar Plantilla" />
                                            </f:facet>
                                            <a4j:commandButton image="/Imagenes/actions/klipper_dock.png" title="Ver Plantilla"
                                                               actionListener="#{managerCLSisEvaluacion.mostrarPlantillaDetalle}"
                                                               reRender="tbClSisEvaPerPlant" oncomplete="#{managerCLSisEvaluacion.oncomplete}"/>
                                        </rich:column>
                                        <rich:column style="text-align: center;">
                                            <f:facet name="header">
                                                <h:outputText value="Editar" />
                                            </f:facet>
                                            <a4j:commandButton image="/Imagenes/actions/editpaste.png" title="Editar"
                                                               actionListener="#{managerCLSisEvaluacion.seleccionarSistemaEvaluacionDet}"
                                                               oncomplete="Richfaces.showModalPanel('mpUpdateSisEvaDet');"
                                                               reRender="mpUpdateSisEvaDet"/>
                                        </rich:column>
                                        <rich:column />
                                    </rich:subTable>
                                </rich:dataTable>
                            </h:form>
                        </td>
                    </tr>
                </table>
            </rich:panel>

            <rich:modalPanel id="mpUpdate" autosized="true" zindex="3000" width="700">
                <f:facet name="header">
                    <h:outputText value="Sistema de evaluacion"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" 
                                        onclick="Richfaces.hideModalPanel('mpUpdate')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="formUpdate">
                    <table style="font-size:12px; font-family:verdana; width: 100%">
                        <tr>
                            <td colspan="2" align="right">
                                <a4j:commandButton image="/Imagenes/actions/filesave.png"
                                                   title="Guardar"
                                                   actionListener="#{managerCLSisEvaluacion.guardarSisEvaluacion}"
                                                   oncomplete="#{managerCLSisEvaluacion.oncomplete}"
                                                   reRender="tablaMaster,paginacion"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr size="1"></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="left">
                                <h:outputText value="SISTEMA EVALUACION" style="font-weight: bold;"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 60px;"><h:outputText value="C�digo"/></td>
                            <td>
                                <h:inputText value="#{managerCLSisEvaluacion.sis_eva.sisevaCodigo}"/>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Nombre"/></td>
                            <td>
                                <h:inputText value="#{managerCLSisEvaluacion.sis_eva.sisevaNombre}"/>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Vigente"/></td>
                            <td>
                                <h:selectBooleanCheckbox  value="#{managerCLSisEvaluacion.sis_eva.sisevaBoolVigente}"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr size="1"></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <h:outputText value="DETALLE DEL SISTEMA DE EVALUACION" style="font-weight: bold;"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <table width="100%">
                                    <tr>
                                        <td><h:outputText value="Codigo"/></td>
                                        <td><h:outputText value="Nombre"/></td>
                                        <td><h:outputText value="Tipo"/></td>
                                        <td><h:outputText value="Peso"/></td>
                                        <td><h:outputText value="Sustitutorio"/></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td><h:inputText id="i1" value="#{managerCLSisEvaluacion.sis_eva_det.sisevaDetalleCodigo}" /></td>
                                        <td><h:inputText id="i2" value="#{managerCLSisEvaluacion.sis_eva_det.sisevaDetalleNombre}" /></td>
                                        <td>
                                            <h:selectOneMenu value="#{managerCLSisEvaluacion.sis_eva_det.sisevaDetalleTipo}">
                                                <f:selectItem itemValue="1" itemLabel="Normal" />
                                                <f:selectItem itemValue="2" itemLabel="Promedio Final" />
                                            </h:selectOneMenu>
                                            </td>
                                            <td><h:inputText id="i3" value="#{managerCLSisEvaluacion.sis_eva_det.sisevaDetallePeso}" style="width: 40px;"/></td>
                                        <td><h:selectBooleanCheckbox id="i4" value="#{managerCLSisEvaluacion.sis_eva_det.sisevaDetalleBoolSusti}" /></td>
                                        <td>
                                            <a4j:commandButton image="/Imagenes/actions/edit_add.png" title="Agregar"
                                                               actionListener="#{managerCLSisEvaluacion.agregarDetalle}"
                                                               oncomplete="#{managerCLSisEvaluacion.oncomplete}"
                                                               reRender="tableSisEvaDet, i1, i2, i3, i4"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="6">
                                            <rich:dataTable id="tableSisEvaDet" value="#{managerCLSisEvaluacion.n_sis_eva_detalles}"
                                                            var="detalle" width="100%" rows="10"
                                                            cellpadding="0" cellspacing="0"
                                                            onRowMouseOver="this.style.backgroundColor='#F8F8F8'"
                                                            onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'">
                                                <f:param id="p_pos_detalle" value="#{detalle.posicion}" />
                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Id" />
                                                    </f:facet>
                                                    <h:outputText value="#{detalle.sisevaDetalleId}"/>
                                                </rich:column>

                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Codigo" />
                                                    </f:facet>
                                                    <h:outputText value="#{detalle.sisevaDetalleCodigo}" rendered="#{detalle.view}"/>
                                                    <h:inputText value="#{detalle.sisevaDetalleCodigo}" rendered="#{detalle.editable}"/>
                                                </rich:column>

                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Nombre" />
                                                    </f:facet>
                                                    <h:outputText value="#{detalle.sisevaDetalleNombre}" rendered="#{detalle.view}"/>
                                                    <h:inputText value="#{detalle.sisevaDetalleNombre}" rendered="#{detalle.editable}"/>
                                                </rich:column>

                                                <rich:column style="text-align: center;">
                                                    <f:facet name="header">
                                                        <h:outputText value="Peso" />
                                                    </f:facet>
                                                    <h:outputText value="#{detalle.sisevaDetallePeso}" rendered="#{detalle.view}"/>
                                                    <h:inputText value="#{detalle.sisevaDetallePeso}" rendered="#{detalle.editable}" style="width: 40px;"/>
                                                </rich:column>

                                                <rich:column style="text-align: center;">
                                                    <f:facet name="header">
                                                        <h:outputText value="Sustitutorio" />
                                                    </f:facet>
                                                    <h:outputText value="#{detalle.sisevaDetalleDescSusti}" rendered="#{detalle.view}"/>
                                                    <h:selectBooleanCheckbox value="#{detalle.sisevaDetalleBoolSusti}" rendered="#{detalle.editable}"/>
                                                </rich:column>

                                                <rich:column style="text-align: center;">
                                                    <f:facet name="header">
                                                        <h:outputText value="Editar"/>
                                                    </f:facet>
                                                    <a4j:commandButton actionListener="#{detalle.edit}"
                                                                       image="/Imagenes/actions/klipper_dock.png" title="Editar"
                                                                       rendered="#{detalle.view_bool}" reRender="tableSisEvaDet"/>
                                                    <a4j:commandButton actionListener="#{detalle.aceptar}"
                                                                       image="/Imagenes/actions/fileexport.png" title="Aceptar"
                                                                       rendered="#{detalle.editable_bool}" reRender="tableSisEvaDet"/>
                                                    <a4j:commandButton actionListener="#{detalle.cancelar}"
                                                                       image="/Imagenes/actions/fileclose.png" title="Cancelar"
                                                                       rendered="#{detalle.editable_bool}" reRender="tableSisEvaDet"/>
                                                </rich:column>

                                                <rich:column style="text-align: center;">
                                                    <f:facet name="header" >
                                                        <h:outputText value="Quitar" />
                                                    </f:facet>
                                                    <a4j:commandButton image="/Imagenes/actions/edit_remove.png" title="Quitar"
                                                                       actionListener="#{managerCLSisEvaluacion.quitarDetalle}"
                                                                       reRender="tableSisEvaDet"/>
                                                </rich:column>
                                            </rich:dataTable>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>

            <rich:modalPanel id="mpUpdateSisEvaDet" autosized="true" zindex="3000" width="400">
                <f:facet name="header">
                    <h:outputText value="Sistema de evaluacion"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" 
                                        onclick="Richfaces.hideModalPanel('mpUpdateSisEvaDet')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="formUpdateSisEvaDet">
                    <h:panelGrid columns="2">
                        <h:outputText value="C�DIGO : " />
                        <h:inputText value="#{managerCLSisEvaluacion.sis_eva_det.aux_codigo}" />
                        <h:outputText value="NOMRBE : " />
                        <h:inputText value="#{managerCLSisEvaluacion.sis_eva_det.aux_nombre}" />
                        <h:outputText value="PESO : " />
                        <rich:inputNumberSpinner id="c_peso" value="#{managerCLSisEvaluacion.sis_eva_det.aux_peso}" step="0.1" inputStyle="width: 20px;" />
                        <h:outputText value="SUSTITUTORIO : " />
                        <h:selectOneMenu value="#{managerCLSisEvaluacion.sis_eva_det.aux_susti?'1':'0'}">
                            <f:selectItem itemValue="1" itemLabel="SI" />
                            <f:selectItem itemValue="0" itemLabel="NO" />
                        </h:selectOneMenu>
                    </h:panelGrid>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="mpClSisEvaPerPlantilla"
                             minWidth="650" height="400" 
                             zindex="3000" style="overflow-y:scroll">
                <f:facet name="header">
                    <h:outputText value="Plantilla de Sistema de Eval. Personalizado"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" 
                                        onclick="Richfaces.hideModalPanel('mpClSisEvaPerPlantilla')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="frmClSisEvaPerPlantilla">
                    <h:panelGrid width="100%">
                        <rich:dataTable id="tbClSisEvaPerPlant" width="100%" border="0"
                                        onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                        onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                        cellpadding="0" cellspacing="0"
                                        value="#{managerCLSisEvaluacion.lstClSisEvaPerPlant}" var="sepp">
                            <f:param id="id_siseva_per_plant" value="#{sepp.sisevaPerId}"/>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Id"/>
                                </f:facet>
                                <h:outputText value="#{sepp.sisevaPerId}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="S.E. Detalle"/>
                                </f:facet>
                                <h:outputText value="#{sepp.clSisEvaDetalle.sisevaDetalleNombre}(#{sepp.clSisEvaDetalle.sisevaDetallePeso*100}%)"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="S.E. Personalizado"/>
                                </f:facet>
                                <h:outputText value="#{sepp.sisevaPerNombre}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Peso"/>
                                </f:facet>
                                <h:outputText value="#{sepp.sisevaPerPeso}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Orden"/>
                                </f:facet>
                                <h:outputText value="#{sepp.sisevaPerOrden}"/>
                            </rich:column>
                            <rich:column style="text-align: center;">
                                <f:facet name="header" >
                                    <h:outputText value="Quitar" />
                                </f:facet>
                                <a4j:commandButton image="/Imagenes/actions/edit_remove.png" title="Quitar"
                                                   actionListener="#{managerCLSisEvaluacion.quitarSisEvaPerPlant}"
                                                   reRender="tbClSisEvaPerPlant" oncomplete="#{managerCLSisEvaluacion.oncomplete}"/>
                            </rich:column>
                        </rich:dataTable>
                    </h:panelGrid>
                </h:form>
            </rich:modalPanel>
        </f:view>
    </body>
</html>