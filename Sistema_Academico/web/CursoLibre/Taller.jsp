<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Configuracion de Talleres</title>
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
                                        <td align="left"><h:outputText value="MANTENIMIENTO DE TALLERES" style="font-weight: bold;"/></td>
                                        <td align="right">
                                            <rich:spacer width="8px"/>
                                            
                                            <h:commandButton image="/Imagenes/actions/viewmag.png"
                                                             title="Buscar"
                                                             action="#{managerCLTaller.Seleccionar}"/>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="10"><hr size="1"></td>
                        </tr>
                        <tr>
                            <td colspan="10" height="15px"></td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputText value="Area :"/></td>
                            <td>
                                <h:selectOneMenu value="#{managerCLTaller.b_are_id}">
                                    <f:selectItems value="#{managerCLTaller.b_areas}" />
                                    <a4j:support event="onchange" reRender="v_mod"
                                                 action="#{managerCLTaller.ver_modulos}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputText value="M�dulo :"/></td>
                            <td>
                                <h:selectOneMenu id="v_mod" value="#{managerCLTaller.b_mod_id}">
                                    <f:selectItems value="#{managerCLTaller.b_modulos}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="10" height="20px"></td>
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
                                <rich:dataTable id="tablaMaster"
                                                onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                onRowMouseOut="this.style.backgroundColor='#FFFFFF'"
                                                cellpadding="0"
                                                cellspacing="0"
                                                width="90%"
                                                rows="10"
                                                value="#{managerCLTaller.cursos}"
                                                var="curso">
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Id" />
                                        </f:facet>
                                        <h:outputText value="#{curso.v_cur_id}"/>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="C�digo" />
                                        </f:facet>
                                        <h:outputText value="#{curso.v_cur_codigo}"/>
                                    </rich:column>
                                    <rich:column colspan="3">
                                        <f:facet name="header">
                                            <h:outputText value="Curso" />
                                        </f:facet>
                                        <h:outputText value="#{curso.v_cur_nombre}"/>
                                    </rich:column>
                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Detalle"/>
                                        </f:facet>
                                        <f:param id="id_detalle" value="#{curso.v_cur_id}"/>
                                        <a4j:commandButton image="#{curso.imagenDetalle}"
                                                           title="#{curso.tituloDetalle}"
                                                           actionListener="#{managerCLTaller.MostrarTalleres}"
                                                           reRender="tablaMaster"/>
                                    </rich:column>
                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Talleres"/>
                                        </f:facet>
                                        <f:param id="id_talleres" value="#{curso.v_cur_id}"/>
                                        <a4j:commandButton image="/Imagenes/actions/reservar.gif"
                                                           title="Cargar Talleres"
                                                           actionListener="#{managerCLTaller.CargarTalleres}"
                                                           oncomplete="#{managerCLTaller.oncomplete}"
                                                           reRender="iModulo,iCodigo,iCurso,iPlan,pnlTalleres"/>
                                    </rich:column>
                                    <rich:subTable id="detalleMaster" value="#{curso.talleres}" var="taller"
                                                   onRowMouseOver="this.style.backgroundColor='#F8F8F8'"
                                                   onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                   rendered="#{curso.verDetalle}">
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Id" />
                                            </f:facet>
                                            <h:outputText value="#{taller.tal_id}" />
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="C�digo" />
                                            </f:facet>
                                            <h:outputText value="#{taller.tal_codigo}" rendered="#{taller.tallerVer}"/>
                                            <h:inputText value="#{taller.e_tal_codigo}" style="width: 200px;" rendered="#{taller.tallerEditar}"/>
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Numero" />
                                            </f:facet>
                                            <h:outputText value="#{taller.tal_numero}" rendered="#{taller.tallerVer}"/>
                                            <h:inputText value="#{taller.e_tal_numero}" style="width: 50px;" maxlength="2" rendered="#{taller.tallerEditar}"/>
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Taller" />
                                            </f:facet>
                                            <h:outputText value="#{taller.tal_descripcion}" rendered="#{taller.tallerVer}"/>
                                            <h:inputText value="#{taller.e_tal_descripcion}" style="width: 350px;" rendered="#{taller.tallerEditar}"/>
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Plan Curricular" />
                                            </f:facet>
                                            <h:outputText value="#{taller.pla_descripcion}" />
                                        </rich:column>
                                        <rich:column style="text-align: center;">
                                            <f:param id="id_detalle_update" value="#{taller.tal_id}"/>
                                            <a4j:commandButton actionListener="#{managerCLTaller.EditarDetalle}"
                                                               reRender="tablaMaster"
                                                               image="/Imagenes/actions/klipper_dock.png"
                                                               title="Editar"
                                                               rendered="#{taller.tallerVer}"/>
                                            <a4j:commandButton image="/Imagenes/actions/fileexport.png"
                                                               title="Grabar"
                                                               actionListener="#{managerCLTaller.GuardarDetalle}"
                                                               reRender="tablaMaster"
                                                               oncomplete="#{managerCLTaller.message}"
                                                               rendered="#{taller.tallerEditar}"/>
                                            <a4j:commandButton image="/Imagenes/actions/fileclose.png"
                                                               title="Cancelar"
                                                               actionListener="#{managerCLTaller.CancelarDetalle}"
                                                               reRender="tablaMaster"
                                                               rendered="#{taller.tallerEditar}"/>
                                        </rich:column>
                                        <rich:column style="text-align: center;">
                                            <f:param id="id_detalle_delete" value="#{taller.tal_id}"/>
                                            <a4j:commandButton actionListener="#{managerCLTaller.EliminarDetalle}"
                                                               reRender="tablaMaster"
                                                               oncomplete="#{managerCLTaller.message}"
                                                               image="/Imagenes/actions/delete.gif"
                                                               title="Eliminar"/>
                                        </rich:column>
                                    </rich:subTable>
                                </rich:dataTable>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:panel>

            <rich:modalPanel id="mpTaller" autosized="true" zindex="3000">
                <f:facet name="header">
                    <h:outputText value="Talleres"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpTaller')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="form2">
                    <table style="font-size:12px; font-family:verdana; width: 100%;">
                        <tr>
                            <td colspan="2" align="right">
                                <a4j:commandButton image="/Imagenes/actions/filesave.png"
                                                   title="Guardar"
                                                   actionListener="#{managerCLTaller.GuardarTalleres}"
                                                   action="#{managerCLTaller.Seleccionar}"
                                                   oncomplete="#{managerCLTaller.oncomplete}"
                                                   reRender="tablaMaster,paginacion"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr size="1"></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="left">
                                <h:outputText value="INFORMACI�N" style="font-weight: bold;"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="height: 5px;"></td>
                        </tr>
                        <tr>
                            <td style="width: 90px;"><h:outputText value="M�dulo"/>
                            </td>
                            <td>
                                <h:outputText id="iModulo" value="#{managerCLTaller.i_mod_descripcion}" style="width: 300px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="C�digo"/>
                            </td>
                            <td>
                                <h:outputText id="iCodigo" value="#{managerCLTaller.i_cur_codigo}" style="width: 300px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Curso"/>
                            </td>
                            <td>
                                <h:outputText id="iCurso" value="#{managerCLTaller.i_cur_nombre}" style="width: 300px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Plan Curricular"/></td>
                            <td>
                                <h:outputText id="iPlan" value="#{managerCLTaller.i_pla_descripcion}" style="width: 300px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr size="1"></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <h:panelGroup id="pnlTalleres">
                                    <table style="font-size:12px; font-family:verdana; width: 100%;">
                                        <tr>
                                            <td colspan="2" align="left">
                                                <h:outputText value="TALLER" style="font-weight: bold;"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="height: 5px;"></td>
                                        </tr>
                                        <tr>
                                            <td><h:outputText value="C�digo"/>
                                            </td>
                                            <td>
                                                <h:inputText id="iTalCodigo" value="#{managerCLTaller.i_tal_codigo}"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><h:outputText value="Descripci�n"/>
                                            </td>
                                            <td>
                                                <h:inputText id="iTalDescripcion" value="#{managerCLTaller.i_tal_descripcion}" style="width: 400px;"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><h:outputText value="N�mero"/>
                                            </td>
                                            <td>
                                                <h:inputText id="iTalNumero" value="#{managerCLTaller.i_tal_numero}" style="width: 50px;" maxlength="2"/>
                                                <rich:spacer width="40px"/>
                                                <a4j:commandButton actionListener="#{managerCLTaller.AgregarTaller}" reRender="iTalCodigo,iTalNumero,iTalDescripcion,tablaDetalle" oncomplete="#{managerCLTaller.message}" image="/Imagenes/actions/edit_add.png" title="Agregar"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="height: 10px;"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" valign="top">
                                                <rich:dataTable id="tablaDetalle"
                                                                width="100%"
                                                                value="#{managerCLTaller.i_talleres}"
                                                                var="taller">
                                                    <rich:column>
                                                        <f:facet name="header">
                                                            <h:outputText value="Id"/>
                                                        </f:facet>
                                                        <h:outputText value="#{taller.i_tal_id}"/>
                                                    </rich:column>

                                                    <rich:column>
                                                        <f:facet name="header">
                                                            <h:outputText value="C�digo"/>
                                                        </f:facet>
                                                        <h:outputText value="#{taller.i_tal_codigo}" rendered="#{taller.e_ver}"/>
                                                        <h:inputText value="#{taller.tal_codigo_e}" rendered="#{taller.e_editar}"/>
                                                    </rich:column>

                                                    <rich:column>
                                                        <f:facet name="header">
                                                            <h:outputText value="Descripci�n"/>
                                                        </f:facet>
                                                        <h:outputText value="#{taller.i_tal_descripcion}" rendered="#{taller.e_ver}"/>
                                                        <h:inputText value="#{taller.tal_descripcion_e}" rendered="#{taller.e_editar}"/>
                                                    </rich:column>

                                                    <rich:column>
                                                        <f:facet name="header">
                                                            <h:outputText value="N�mero"/>
                                                        </f:facet>
                                                        <h:outputText value="#{taller.i_tal_numero}" rendered="#{taller.e_ver}"/>
                                                        <h:inputText value="#{taller.tal_numero_e}" maxlength="2" rendered="#{taller.e_editar}" style="width: 50px"/>
                                                    </rich:column>

                                                    <rich:column style="text-align: center;">
                                                        <f:facet name="header">
                                                            <h:outputText value="Editar"/>
                                                        </f:facet>
                                                        <f:param id="id_tal_update" value="#{taller.i_tal_id}"/>
                                                        <a4j:commandButton actionListener="#{managerCLTaller.EditarTaller}"
                                                                           reRender="tablaDetalle"
                                                                           image="/Imagenes/actions/klipper_dock.png"
                                                                           title="Editar"
                                                                           rendered="#{taller.e_ver}"/>
                                                        <a4j:commandButton image="/Imagenes/actions/fileexport.png"
                                                                           title="Grabar"
                                                                           actionListener="#{managerCLTaller.GuardarTaller}"
                                                                           reRender="tablaDetalle"
                                                                           oncomplete="#{managerCLTaller.message}"
                                                                           rendered="#{taller.e_editar}"/>
                                                        <a4j:commandButton image="/Imagenes/actions/fileclose.png"
                                                                           title="Cancelar"
                                                                           actionListener="#{managerCLTaller.CancelarTaller}"
                                                                           reRender="tablaDetalle"
                                                                           rendered="#{taller.e_editar}"/>
                                                    </rich:column>

                                                    <rich:column style="text-align: center;">
                                                        <f:facet name="header">
                                                            <h:outputText value="Eliminar"/>
                                                        </f:facet>
                                                        <f:param id="id_tal_delete" value="#{taller.i_tal_id}"/>
                                                        <a4j:commandButton actionListener="#{managerCLTaller.EliminarTaller}"
                                                                           reRender="tablaDetalle"
                                                                           oncomplete="#{managerCLTaller.message}"
                                                                           image="/Imagenes/actions/delete.gif"
                                                                           title="Eliminar"/>
                                                    </rich:column>
                                                </rich:dataTable>
                                            </td>
                                        </tr>
                                    </table>
                                </h:panelGroup>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>
        </f:view>
    </body>
</html>