<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Configuracion de Módulos y Cursos</title>
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
                                        <td align="left"><h:outputText value="MANTENIMIENTO DE MODULOS Y CURSOS" style="font-weight: bold;"/></td>
                                        <td align="right">
                                            <rich:spacer width="8px"/>

                                            <h:graphicImage value="/Imagenes/actions/filenew.png" title="Nuevo" style="cursor: pointer;">
                                                <a4j:support event="onclick" 
                                                             actionListener="#{managerCLModulo.Nuevo}"
                                                             oncomplete="#{managerCLModulo.oncomplete}"
                                                             reRender="iArea,iCodigo,iDescripcion,btnVerAgregar,pnlCursos"/>
                                            </h:graphicImage>
                                            <rich:spacer width="8px"/>
                                            
                                            <h:commandButton image="/Imagenes/actions/viewmag.png"
                                                             title="Buscar"
                                                             action="#{managerCLModulo.Seleccionar}"/>
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
                            <td colspan="9" align="left"><h:inputText id="b_descripcion" value="#{managerCLModulo.b_descripcion}" style="width: 300px;"/></td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputText value="Centro :"/></td>
                            <td>
                                <h:selectOneMenu value="#{managerCLModulo.centroId}">
                                    <f:selectItems value="#{managerCLModulo.centros}" />
                                    <a4j:support event="onchange" reRender="cboAreas"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputText value="Area :"/></td>
                            <td>
                                <h:selectOneMenu id="cboAreas" value="#{managerCLModulo.b_are_id}">
                                    <f:selectItems value="#{managerCLModulo.b_areas}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                    </table>
                </h:form>
                <h:form id="form3">
                    <rich:datascroller id="paginacion" align="right" for="tablaMaster" maxPages="10" style="width : 100%;"/>
                    <rich:spacer height="20px"/>
                    <rich:dataTable id="tablaMaster"
                                    onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                    onRowMouseOut="this.style.backgroundColor='#FFFFFF'"
                                    cellpadding="0"
                                    cellspacing="0"
                                    width="100%"
                                    rows="10"
                                    value="#{managerCLModulo.modulos}"
                                    var="modulo">
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Id" />
                            </f:facet>
                            <h:outputText value="#{modulo.mod_id}"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Código" />
                            </f:facet>
                            <h:outputText value="#{modulo.mod_codigo}"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Descripción" />
                            </f:facet>
                            <h:outputText value="#{modulo.mod_descripcion}"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Área" />
                            </f:facet>
                            <h:outputText value="#{modulo.are_descripcion}"/>
                        </rich:column>
                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Cursos"/>
                            </f:facet>
                            <f:param id="id_detalle" value="#{modulo.mod_id}"/>
                            <a4j:commandButton image="#{modulo.imagenDetalle}"
                                               title="#{modulo.tituloDetalle}"
                                               actionListener="#{managerCLModulo.MostrarCursos}"
                                               reRender="tablaMaster"/>
                        </rich:column>
                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Editar"/>
                            </f:facet>
                            <f:param id="id_update" value="#{modulo.mod_id}"/>
                            <a4j:commandButton image="/Imagenes/actions/icon_edit.png"
                                               title="Modificar"
                                               actionListener="#{managerCLModulo.ActualizarModulo}"
                                               oncomplete="#{managerCLModulo.oncomplete}"
                                               reRender="iArea,iCodigo,iDescripcion,btnVerAgregar,pnlCursos"/>
                        </rich:column>
                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Eliminar" />
                            </f:facet>
                            <f:param id="id_delete" value="#{modulo.mod_id}"/>
                            <a4j:commandButton image="/Imagenes/actions/no.png"
                                               title="Eliminar"
                                               actionListener="#{managerCLModulo.EliminarModulo}"
                                               oncomplete="#{managerCLModulo.message}"
                                               action="#{managerCLModulo.Seleccionar}"
                                               reRender="tablaMaster"/>
                        </rich:column>
                        <rich:column style="text-align: center;" rendered="#{managerCLModulo.usuId == 1}">
                            <f:facet name="header">
                                <h:outputText value="Ocultar de Arbol" />
                            </f:facet>
                            <f:param id="id_ocultar_arbol" value="#{modulo.mod_id}"/>
                            <a4j:commandButton image="/Imagenes/actions/#{modulo.img_oculto}"
                                               title="#{modulo.title_img_oculto}"
                                               actionListener="#{managerCLModulo.ocultarArbol}"
                                               oncomplete="#{managerCLModulo.message}"
                                               reRender="tablaMaster"
                                               />
                        </rich:column>
                        <rich:subTable id="detalleMaster" value="#{modulo.cursos}" var="curso"
                                       onRowMouseOver="this.style.backgroundColor='#F8F8F8'"
                                       onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                       rendered="#{modulo.verDetalle}">
                            <rich:column>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Id" />
                                </f:facet>
                                <h:outputText value="#{curso.cur_id}" />
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Código" />
                                </f:facet>
                                <h:outputText value="#{curso.cur_codigo}" rendered="#{curso.cursoVer}"/>
                                <h:inputText value="#{curso.cur_codigo_edit}" style="width: 300px;" rendered="#{curso.cursoEditar}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Nombre" />
                                </f:facet>
                                <h:outputText value="#{curso.cur_nombre}" rendered="#{curso.cursoVer}"/>
                                <h:inputText value="#{curso.cur_nombre_edit}" style="width: 300px;" rendered="#{curso.cursoEditar}"/>
                            </rich:column>
                            <rich:column style="text-align: center;" rendered="#{managerCLModulo.usuId == 1}">
                                <f:facet name="header">
                                    <h:outputText value="Ocultar de Arbol (Curso)" />
                                </f:facet>
                                <f:param id="id_ocultar_arbol_cur" value="#{curso.cur_id}"/>
                                <a4j:commandButton image="/Imagenes/actions/#{curso.img_oculto}"
                                                   title="#{curso.title_img_oculto}"
                                                   actionListener="#{managerCLModulo.ocultarArbolCurso}"
                                                   oncomplete="#{managerCLModulo.message}"
                                                   reRender="tablaMaster"
                                                   />
                            </rich:column>
                            <rich:column style="text-align: center;">
                                <f:param id="id_detalle_update" value="#{curso.cur_id}"/>
                                <a4j:commandButton actionListener="#{managerCLModulo.EditarDetalle}"
                                                   reRender="tablaMaster"
                                                   image="/Imagenes/actions/klipper_dock.png"
                                                   title="Editar"
                                                   rendered="#{curso.cursoVer}"/>
                                <a4j:commandButton image="/Imagenes/actions/filesave.png"
                                                   title="Grabar"
                                                   actionListener="#{managerCLModulo.GuardarDetalle}"
                                                   reRender="tablaMaster"
                                                   oncomplete="#{managerCLModulo.message}"
                                                   rendered="#{curso.cursoEditar}"/>
                                <a4j:commandButton image="/Imagenes/actions/fileclose.png"
                                                   title="Cancelar"
                                                   actionListener="#{managerCLModulo.CancelarDetalle}"
                                                   reRender="tablaMaster"
                                                   rendered="#{curso.cursoEditar}"/>
                            </rich:column>
                            <rich:column style="text-align: center;">
                                <f:param id="id_detalle_delete" value="#{curso.cur_id}"/>
                                <a4j:commandButton actionListener="#{managerCLModulo.EliminarDetalle}"
                                                   reRender="tablaMaster"
                                                   oncomplete="#{managerCLModulo.message}"
                                                   image="/Imagenes/actions/delete.gif"
                                                   title="Eliminar"/>
                            </rich:column>
                        </rich:subTable>
                    </rich:dataTable>
                </h:form>
            </rich:panel>

            <rich:modalPanel id="mp1" autosized="true" zindex="3000" width="600">
                <f:facet name="header">
                    <h:outputText value="Módulo y Curso"/>
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
                                                   actionListener="#{managerCLModulo.GuardarModulo}"
                                                   action="#{managerCLModulo.Seleccionar}"
                                                   oncomplete="#{managerCLModulo.oncomplete}"
                                                   reRender="tablaMaster,paginacion"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr size="1"></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="left">
                                <h:outputText value="MODULO" style="font-weight: bold;"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="height: 5px;"></td>
                        </tr>
                        <tr>
                            <td style="width: 70px;"><h:outputText value="Área"/>
                            </td>
                            <td>
                                <h:selectOneMenu id="iArea" value="#{managerCLModulo.i_are_id}">
                                    <f:selectItems value="#{managerCLModulo.i_areas}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Código"/>
                            </td>
                            <td>
                                <h:inputText id="iCodigo" value="#{managerCLModulo.i_mod_codigo}"/>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Descripción"/>
                            </td>
                            <td>
                                <h:inputText id="iDescripcion" value="#{managerCLModulo.i_mod_descripcion}" style="width: 300px;"/>
                                <rich:spacer width="20px"/>
                                <a4j:commandButton id="btnVerAgregar" actionListener="#{managerCLModulo.MostrarAgregarCursos}" reRender="btnVerAgregar,pnlCursos" image="#{managerCLModulo.imagenAgregar}" title="#{managerCLModulo.tituloAgregar}"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr size="1"></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <h:panelGroup id="pnlCursos">
                                    <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                        <tr>
                                            <td colspan="2" align="left">
                                                <h:outputText value="CURSO" style="font-weight: bold;" rendered="#{managerCLModulo.verAgregar}"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="height: 5px;"></td>
                                        </tr>
                                        <tr>
                                            <td><h:outputText value="Código" rendered="#{managerCLModulo.verAgregar}"/>
                                            </td>
                                            <td>
                                                <h:inputText id="iCurCodigo" value="#{managerCLModulo.i_cur_codigo}" rendered="#{managerCLModulo.verAgregar}"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><h:outputText value="Nombre" rendered="#{managerCLModulo.verAgregar}"/>
                                            </td>
                                            <td>
                                                <h:inputText id="iCurNombre" value="#{managerCLModulo.i_cur_nombre}" style="width: 300px;" rendered="#{managerCLModulo.verAgregar}"/>
                                                <rich:spacer width="20px" rendered="#{managerCLModulo.verAgregar}"/>
                                                <a4j:commandButton actionListener="#{managerCLModulo.AgregarCurso}" reRender="iCurCodigo,iCurNombre,tablaDetalle" oncomplete="#{managerCLModulo.message}" image="/Imagenes/actions/edit_add.png" title="Agregar" rendered="#{managerCLModulo.verAgregar}"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="height: 10px;"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" valign="top">
                                                <rich:dataTable id="tablaDetalle"
                                                                width="100%"
                                                                value="#{managerCLModulo.i_cursos}"
                                                                var="detalle" rendered="#{managerCLModulo.verAgregar}">
                                                    <rich:column>
                                                        <f:facet name="header">
                                                            <h:outputText value="Id"/>
                                                        </f:facet>
                                                        <h:outputText value="#{detalle.i_cur_id}"/>
                                                    </rich:column>
                                                    <rich:column>
                                                        <f:facet name="header">
                                                            <h:outputText value="Código"/>
                                                        </f:facet>
                                                        <h:outputText value="#{detalle.i_cur_codigo}" rendered="#{detalle.cursoVer}"/>
                                                        <h:inputText value="#{detalle.e_cur_codigo}" rendered="#{detalle.cursoEditar}"/>
                                                    </rich:column>
                                                    <rich:column>
                                                        <f:facet name="header">
                                                            <h:outputText value="Nombre"/>
                                                        </f:facet>
                                                        <h:outputText value="#{detalle.i_cur_nombre}" rendered="#{detalle.cursoVer}"/>
                                                        <h:inputText value="#{detalle.e_cur_nombre}" rendered="#{detalle.cursoEditar}"/>
                                                    </rich:column>
                                                    <rich:column>
                                                        <f:facet name="header">
                                                            <h:outputText value="Editar"/>
                                                        </f:facet>
                                                        <f:param id="id_cur_update" value="#{detalle.i_cur_id}"/>
                                                        <a4j:commandButton actionListener="#{managerCLModulo.EditarCurso}"
                                                                           reRender="tablaDetalle"
                                                                           image="/Imagenes/actions/klipper_dock.png"
                                                                           title="Editar"
                                                                           rendered="#{detalle.cursoVer}"/>
                                                        <a4j:commandButton image="/Imagenes/actions/filesave.png"
                                                                           title="Grabar"
                                                                           actionListener="#{managerCLModulo.GuardarCurso}"
                                                                           reRender="tablaDetalle"
                                                                           oncomplete="#{managerCLModulo.message}"
                                                                           rendered="#{detalle.cursoEditar}"/>
                                                        <a4j:commandButton image="/Imagenes/actions/fileclose.png"
                                                                           title="Cancelar"
                                                                           actionListener="#{managerCLModulo.CancelarCurso}"
                                                                           reRender="tablaDetalle"
                                                                           rendered="#{detalle.cursoEditar}"/>
                                                    </rich:column>
                                                    <rich:column>
                                                        <f:facet name="header">
                                                            <h:outputText value="Eliminar"/>
                                                        </f:facet>
                                                        <f:param id="id_cur_delete" value="#{detalle.i_cur_id}"/>
                                                        <a4j:commandButton actionListener="#{managerCLModulo.QuitarCurso}"
                                                                           reRender="tablaDetalle"
                                                                           oncomplete="#{managerCLModulo.message}"
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