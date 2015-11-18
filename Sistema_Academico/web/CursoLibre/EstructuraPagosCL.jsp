<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Configuraci�n de Estructuras de Pago</title>
        <script type="text/javascript">
            function printObjectsSelected(sgcomponent, iIdConcepto, iConcepto) {
                iIdConcepto.value = sgcomponent.getSelectedItems().pluck('au_conpag_id');
                iConcepto.innerHTML = sgcomponent.getSelectedItems().pluck('au_concepto');
            }
        </script>
    </head>
    <body>
        <f:view>
            <jsp:include page="../TablaSistema/Menu.jsp"/>
            <rich:panel>

                <h:form id="form1">
                    <table style="font-size:13px; font-family:verdana; width: 100%">
                        <tr>
                            <td colspan="2"><h:outputText value="MANTENIMIENTO DE ESTRUCTURAS DE PAGOS" style="font-weight: bold;"/></td>
                            <td align="right">
                                <rich:spacer width="8px"/>

                                <a4j:commandButton image="/Imagenes/actions/filenew.png" title="Nuevo"
                                                   actionListener="#{managerCLEstructuraPago.nuevo}"
                                                   oncomplete="#{managerCLEstructuraPago.oncomplete}"
                                                   reRender="iArea, iModulo,iNombre,iAbreviatura,iSugConcepto,suggestionConcepto,iIdConcepto,iConcepto,iDesc,iDescImp,iMonto,tablaDetalle, iOrden"/>
                                <rich:spacer width="8px"/>

                                <h:commandButton image="/Imagenes/actions/viewmag.png"
                                                 title="Buscar"
                                                 action="#{managerCLEstructuraPago.seleccionar}"/>
                                <rich:spacer width="8px"/>
                                <a4j:commandButton image="/Imagenes/actions/enlace.png" title="Enlazar Estructuras de Pago Detalle"
                                                   actionListener="#{managerCLEstructuraPago.prepararEnlaceEstPagDet}" reRender="frmEnlaceEstPagDet"
                                                   oncomplete="#{managerCLEstructuraPago.oncomplete}" style="width:20px;height:20px;"/>

                            </td>
                        </tr>
                        <tr>
                            <td colspan="10"><hr size="1"></td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputText value="Descripci�n"/></td>
                            <td colspan="9" align="left"><h:inputText id="b_nombre" value="#{managerCLEstructuraPago.b_nombre}" style="width: 300px;"/></td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputText value="Centro"/></td>
                            <td>
                                <h:selectOneMenu value="#{managerCLEstructuraPago.centroId}">
                                    <f:selectItems value="#{managerCLEstructuraPago.cboCentros}" />
                                    <a4j:support event="onchange" reRender="cboAreas,v_mod"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputText value="Area"/></td>
                            <td>
                                <h:selectOneMenu id="cboAreas" value="#{managerCLEstructuraPago.b_are_id}">
                                    <f:selectItems value="#{managerCLEstructuraPago.areas}" />
                                    <a4j:support event="onchange" reRender="v_mod"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputText value="M�dulo"/></td>
                            <td>
                                <h:selectOneMenu id="v_mod" value="#{managerCLEstructuraPago.b_mod_id}">
                                    <f:selectItems value="#{managerCLEstructuraPago.modulos}" />
                                    <a4j:support event="onchange" reRender="v_cur"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputText value="Curso"/></td>
                            <td>
                                <h:selectOneMenu id="v_cur" value="#{managerCLEstructuraPago.b_cur_id}">
                                    <f:selectItems value="#{managerCLEstructuraPago.cursos}" />
                                    <a4j:support event="onchange" reRender="v_tall"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputText value="Taller"/></td>
                            <td>
                                <h:selectOneMenu id="v_tall" value="#{managerCLEstructuraPago.b_tall_id}">
                                    <f:selectItems value="#{managerCLEstructuraPago.talleres}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                    </table>
                </h:form>
                <h:form id="form3">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-size:10px; font-family:verdana">
                        <tr>
                            <td colspan="10" align="center" valign="bottom" width="100%">
                                <div style="width: 100%">
                                    <p align="right"/>
                                    <rich:datascroller id="paginacion" align="right" for="tablaMaster" maxPages="10" style="width: 100%;"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="10" valign="top" align="center">
                                <rich:dataTable id="tablaMaster"
                                                onRowMouseOver="this.style.backgroundColor='#D7D7D7'"
                                                onRowMouseOut="this.style.backgroundColor='#FFFFFF'"
                                                cellpadding="0"
                                                cellspacing="0"
                                                width="100%"
                                                rows="10"
                                                value="#{managerCLEstructuraPago.estructuras}"
                                                var="est">
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Id" />
                                        </f:facet>
                                        <h:outputText value="#{est.estpag_id}"/>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="M�dulo" />
                                        </f:facet>
                                        <h:outputText value="#{est.modulo}"/>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Descripci�n" />
                                        </f:facet>
                                        <h:outputText value="#{est.estpag_nombre}"/>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Abreviatura" />
                                        </f:facet>
                                        <h:outputText value="#{est.estpag_abrev}"/>
                                    </rich:column>

                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Ver"/>
                                        </f:facet>
                                        <f:param id="id_ver" value="#{est.estpag_id}"/>
                                        <a4j:commandButton image="#{est.verDetImagen}" title="#{est.verDetTitulo}"
                                                           actionListener="#{managerCLEstructuraPago.mostrarDetalle}"
                                                           reRender="tablaMaster"/>
                                    </rich:column>

                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Editar"/>
                                        </f:facet>
                                        <f:param id="id_update" value="#{est.estpag_id}"/>
                                        <a4j:commandButton image="/Imagenes/actions/icon_edit.png"
                                                           title="Modificar"
                                                           actionListener="#{managerCLEstructuraPago.actualizarEstructura}"
                                                           oncomplete="#{managerCLEstructuraPago.oncomplete}"
                                                           reRender="iArea,iModulo,iCurso,iTaller,iNombre,iAbreviatura,iSugConcepto,suggestionConcepto,iIdConcepto,iConcepto,iDesc,iDescImp,iMonto,tablaDetalle,pGrupo,iTipoEstructura,cboCursosRel"/>
                                    </rich:column>

                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Eliminar" />
                                        </f:facet>
                                        <f:param id="id_delete" value="#{est.estpag_id}"/>
                                        <a4j:commandButton image="/Imagenes/actions/delete.gif"
                                                           title="Eliminar"
                                                           actionListener="#{managerCLEstructuraPago.eliminarEstructura}"
                                                           action="#{managerCLEstructuraPago.seleccionar}"
                                                           oncomplete="#{managerCLEstructuraPago.oncomplete}"
                                                           reRender="tablaMaster,paginacion"/>
                                    </rich:column>

                                    <rich:subTable id="detalleMaster" value="#{est.detalle}" var="det"
                                                   onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                   onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                   rendered="#{est.verDetalle}">
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Id" />
                                            </f:facet>
                                            <h:outputText value="#{det.estpagdet_id}" />
                                        </rich:column>

                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Descripci�n" />
                                            </f:facet>
                                            <h:outputText value="#{det.estpagdet_nombre}" />
                                        </rich:column>

                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Concepto" />
                                            </f:facet>
                                            <h:outputText value="#{det.concepto}" />
                                        </rich:column>

                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Cur. Rel." />
                                            </f:facet>
                                            <h:outputText rendered="#{not empty det.inArbCurRel}" value="#{det.inArbCurRel.arbDescripcion}" />
                                        </rich:column>

                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Monto" />
                                            </f:facet>
                                            <h:outputText value="#{det.estpagdet_monto}" />
                                        </rich:column>
                                        <rich:column colspan="3" />
                                    </rich:subTable>
                                </rich:dataTable>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:panel>

            <rich:modalPanel id="mpUpdate" autosized="true" zindex="3000" width="550">
                <f:facet name="header">
                    <h:outputText value="Estructura de Pagos"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mpUpdate')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="form2">
                    <table style="font-size:12px; font-family:verdana; width: 100%">
                        <tr>
                            <td colspan="2" align="right">
                                <a4j:commandButton image="/Imagenes/actions/filesave.png"
                                                   title="Guardar"
                                                   actionListener="#{managerCLEstructuraPago.guardarEstructura}"
                                                   oncomplete="#{managerCLEstructuraPago.oncomplete}" reRender="form3,tablaMaster,detalleMaster" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr size="1"></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="left">
                                <h:outputText value="ESTRUCTURA DE PAGOS" style="font-weight: bold;"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="height: 5px;"></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center">
                                <h:selectOneRadio id="iTipoEstructura" value="#{managerCLEstructuraPago.i_tipo_estructura}">
                                    <f:selectItem id="iPropio" itemLabel="Propio" itemValue="068001" />
                                    <f:selectItem id="iLibre" itemLabel="Libre" itemValue="068002" />
                                    <a4j:support event="onchange" actionListener="#{managerCLEstructuraPago.definirTipo}" reRender="pGrupo"/>
                                </h:selectOneRadio><br />
                                <h:panelGroup id="pGrupo">
                                    <h:panelGroup id="pSubGrupo" rendered="#{managerCLEstructuraPago.w_ocultar_modulo}">
                                        <table width="100%">
                                            <tr>
                                                <td><h:outputText value="Area"/>
                                                </td>
                                                <td>
                                                    <h:selectOneMenu id="iArea" value="#{managerCLEstructuraPago.i_are_id}">
                                                        <f:selectItems value="#{managerCLEstructuraPago.i_areas}" />
                                                        <a4j:support event="onchange" reRender="iModulo,cboCursosRel"/>
                                                    </h:selectOneMenu>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><h:outputText value="M�dulo"/>
                                                </td>
                                                <td>
                                                    <h:selectOneMenu id="iModulo" value="#{managerCLEstructuraPago.i_mod_id}" >
                                                        <f:selectItems value="#{managerCLEstructuraPago.i_modulos}" />
                                                        <a4j:support event="onchange" reRender="iCurso,cboCursosRel"/>
                                                    </h:selectOneMenu>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><h:outputText value="Cursos"/>
                                                </td>
                                                <td>
                                                    <h:selectOneMenu id="iCurso" value="#{managerCLEstructuraPago.i_cur_id}" >
                                                        <f:selectItems value="#{managerCLEstructuraPago.i_cursos}" />
                                                        <a4j:support event="onchange" reRender="iTaller,cboCursosRel"/>
                                                    </h:selectOneMenu>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><h:outputText value="Talleres"/>
                                                </td>
                                                <td>
                                                    <h:selectOneMenu id="iTaller" value="#{managerCLEstructuraPago.i_tall_id}" >
                                                        <f:selectItems value="#{managerCLEstructuraPago.i_talleres}" />
                                                        <a4j:support event="onchange" reRender="cboCursosRel"/>
                                                    </h:selectOneMenu>
                                                </td>
                                            </tr>                                         
                                        </table>
                                    </h:panelGroup>
                                </h:panelGroup>
                            </td>

                        </tr>
                        <tr>
                            <td><h:outputText value="Nombre"/>
                            </td>
                            <td>
                                <h:inputText id="iNombre" value="#{managerCLEstructuraPago.i_estpag_nombre}" style="width: 350px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Abreviatura"/>
                            </td>
                            <td>
                                <h:inputText id="iAbreviatura" value="#{managerCLEstructuraPago.i_estpag_abrev}"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr size="1"></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="left">
                                <h:outputText value="DETALLE" style="font-weight: bold;"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="height: 5px;"></td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Concepto"/>
                            </td>
                            <td colspan="3" align="left">
                                <h:inputText id="iSugConcepto" value="#{managerCLEstructuraPago.sug_concepto}" style="width:200px;" />
                                <rich:suggestionbox id="suggestionConcepto"
                                                    for="iSugConcepto" nothingLabel="Concepto No Encontrado"
                                                    suggestionAction="#{managerCLEstructuraPago.autocomplete}" var="cp"
                                                    tokens=","
                                                    height="150" width="350" cellpadding="2"
                                                    cellspacing="2" shadowOpacity="4" shadowDepth="4"
                                                    minChars="2" rules="none" zindex="3500"
                                                    usingSuggestObjects="true"
                                                    onobjectchange="printObjectsSelected(#{rich:component('suggestionConcepto')},#{rich:element('iIdConcepto')},#{rich:element('iConcepto')});"
                                                    >
                                    <h:column>
                                        <h:outputText value="#{cp.au_concepto}" style="font-style:bold"/>
                                    </h:column>
                                </rich:suggestionbox>
                                <h:inputHidden id="iIdConcepto" value="#{managerCLEstructuraPago.i_conpag_id}"/>
                                <h:outputText id="iConcepto" value="#{managerCLEstructuraPago.i_conpag_des}" style="font-weight: bold;"/>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Descripci�n"/>
                            </td>
                            <td>
                                <h:inputText id="iDesc" value="#{managerCLEstructuraPago.i_estpagdet_des}" style="width: 300px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Descripci�n Auxiliar"/>
                            </td>
                            <td>
                                <h:inputText id="iDescImp" value="#{managerCLEstructuraPago.estpagdet_nomimp}" style="width: 300px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Taller relacionado"/>
                            </td>
                            <td>
                                <h:selectOneMenu id="cboCursosRel" value="#{managerCLEstructuraPago.idCursoRelacionado}">
                                    <f:selectItems value="#{managerCLEstructuraPago.cboCursosRelacionados}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Monto"/>
                            </td>
                            <td>
                                <h:inputText id="iMonto" value="#{managerCLEstructuraPago.i_estpagdet_monto}" style="width: 300px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Nro Orden"/>
                            </td>
                            <td>
                                <rich:inputNumberSpinner minValue="1" maxValue="30" value="#{managerCLEstructuraPago.i_estpagdet_orden}" id="iOrden" style="display:block;float:left;margin-right:45px;"/>
                                <rich:spacer width="50px"/>
                                <a4j:commandButton actionListener="#{managerCLEstructuraPago.agregarDetalle}" reRender="tablaDetalle,iOrden" oncomplete="#{managerCLEstructuraPago.oncomplete}" image="/Imagenes/actions/edit_add.png" title="Agregar"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="height: 10px;"></td>
                        </tr>
                        <tr>
                            <td colspan="2" valign="top">
                                <div style="overflow-y:auto; height: 190px">
                                    <rich:dataTable id="tablaDetalle"
                                                    width="100%"
                                                    value="#{managerCLEstructuraPago.i_detalle}"
                                                    var="det">
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Id"/>
                                            </f:facet>
                                            <h:outputText value="#{det.i_estpagdet_id}"/>
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Concepto"/>
                                            </f:facet>
                                            <h:outputText value="#{det.i_conpag_des}"/>
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Descripci�n"/>
                                            </f:facet>
                                            <h:outputText value="#{det.i_estpagdet_des}" rendered="#{det.detalleVer}"/>
                                            <h:inputText value="#{det.e_estpagdet_des}" rendered="#{det.detalleEditar}"/>
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Descripci�n Auxiliar"/>
                                            </f:facet>
                                            <h:outputText value="#{det.estpagdet_nomimp}" rendered="#{det.detalleVer}"/>
                                            <h:inputText value="#{det.e_estpagdet_nomimp}" rendered="#{det.detalleEditar}"/>
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Monto"/>
                                            </f:facet>
                                            <h:outputText value="#{det.i_estpagdet_monto}" rendered="#{det.detalleVer}"/>
                                            <h:inputText value="#{det.e_estpagdet_monto}" rendered="#{det.detalleEditar}" style="width: 50px;"/>
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Cur. Rel."/>
                                            </f:facet>
                                            <h:outputText value="#{det.inArbCurRel.arbDescripcion}" rendered="#{det.detalleVer}"/>
                                            <h:selectOneMenu id="cboCursosRel" value="#{det.inArbCurRel.arbId}" rendered="#{det.detalleEditar}" >
                                                <f:selectItems value="#{managerCLEstructuraPago.cboCursosRelacionados}" />
                                            </h:selectOneMenu>
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Orden"/>
                                            </f:facet>
                                            <h:outputText value="#{det.i_estpagdet_orden}" rendered="#{det.detalleVer}"/>
                                            <rich:inputNumberSpinner minValue="1" maxValue="30" value="#{det.e_estpagdet_orden}" rendered="#{det.detalleEditar}"/>
                                        </rich:column>
                                        <rich:column style="text-align: center;">
                                            <f:facet name="header">
                                                <h:outputText value="Editar"/>
                                            </f:facet>
                                            <f:param id="id_det_update" value="#{det.i_estpagdet_id}"/>
                                            <a4j:commandButton actionListener="#{managerCLEstructuraPago.editarDetalle}"
                                                               reRender="tablaDetalle"
                                                               image="/Imagenes/actions/klipper_dock.png"
                                                               title="Editar"
                                                               rendered="#{det.detalleVer}"/>
                                            <a4j:commandButton image="/Imagenes/actions/fileexport.png"
                                                               title="Grabar"
                                                               actionListener="#{managerCLEstructuraPago.guardarDetalle}"
                                                               reRender="tablaDetalle"
                                                               oncomplete="#{managerCLEstructuraPago.oncomplete}"
                                                               rendered="#{det.detalleEditar}"/>
                                            <a4j:commandButton image="/Imagenes/actions/fileclose.png"
                                                               title="Cancelar"
                                                               actionListener="#{managerCLEstructuraPago.cancelarDetalle}"
                                                               reRender="tablaDetalle"
                                                               rendered="#{det.detalleEditar}"/>
                                        </rich:column>
                                        <rich:column style="text-align: center;">
                                            <f:facet name="header">
                                                <h:outputText value="Eliminar"/>
                                            </f:facet>
                                            <f:param id="id_det_delete" value="#{det.i_estpagdet_id}"/>
                                            <a4j:commandButton actionListener="#{managerCLEstructuraPago.eliminarDetalle}" 
                                                               reRender="tablaDetalle"
                                                               oncomplete="#{managerCLEstructuraPago.oncomplete}"
                                                               image="/Imagenes/actions/edit_remove.png"
                                                               title="Eliminar"/>
                                        </rich:column>
                                    </rich:dataTable>
                                </div>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>

            <rich:modalPanel id="mpEnlaceEstPagDet" autosized="true" zindex="3000" width="860">
                <f:facet name="header">
                    <h:outputText value="Enlazar Estructuras de Pagos Detalle"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mpEnlaceEstPagDet')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="frmEnlaceEstPagDet">
                    <table>
                        <tr>
                            <td>
                                <h:panelGrid columns="2" id="tbCbosDetIni">
                                    <f:facet name="header">
                                        <h:outputText value="ESTRUCTURA DE PAGO DETALLE INICIAL" />
                                    </f:facet>
                                    <h:outputText value="CENTRO INI. : " />
                                    <h:selectOneMenu style="width:200px" value="#{managerCLEstructuraPago.centroIniId}">
                                        <f:selectItems value="#{managerCLEstructuraPago.cboCentros}" />
                                        <a4j:support event="onchange" reRender="cboAreaIni"/>
                                    </h:selectOneMenu>
                                    <h:outputText value="�REA INI. : " />
                                    <h:selectOneMenu id="cboAreaIni" style="width:200px" value="#{managerCLEstructuraPago.areaIniId}">
                                        <f:selectItems value="#{managerCLEstructuraPago.cboAreasIni}" />
                                        <a4j:support event="onchange" reRender="cboModIni"/>
                                    </h:selectOneMenu>
                                    <h:outputText value="M�DULO INI. : " />
                                    <h:selectOneMenu id="cboModIni" style="width:200px" value="#{managerCLEstructuraPago.modIniId}">
                                        <f:selectItems value="#{managerCLEstructuraPago.cboModsIni}" />
                                        <a4j:support event="onchange" reRender="cboCurIni"/>
                                    </h:selectOneMenu>
                                    <h:outputText value="CURSO INI. : " />
                                    <h:selectOneMenu id="cboCurIni" style="width:200px" value="#{managerCLEstructuraPago.curIniId}">
                                        <f:selectItems value="#{managerCLEstructuraPago.cboCursIni}" />
                                        <a4j:support event="onchange" reRender="cboTallIni"/>
                                    </h:selectOneMenu>
                                    <h:outputText value="TALLER INI. : " />
                                    <h:selectOneMenu id="cboTallIni" style="width:200px" value="#{managerCLEstructuraPago.tallIniId}">
                                        <f:selectItems value="#{managerCLEstructuraPago.cboTallsIni}" />
                                        <a4j:support event="onchange" actionListener="#{managerCLEstructuraPago.listarEnlaces}" reRender="pnlTbDet,cboEstPagIni"/>
                                    </h:selectOneMenu>

                                    <h:outputText value="ESTRUCTURA DE PAGO INI. : " />
                                    <h:selectOneMenu id="cboEstPagIni" style="width:200px" value="#{managerCLEstructuraPago.estPagIniId}">
                                        <f:selectItems value="#{managerCLEstructuraPago.cboEstPagIni}" />
                                        <a4j:support event="onchange" actionListener="#{managerCLEstructuraPago.listarEnlaces}" reRender="pnlTbDet,cboEstPagDetIni"/>
                                    </h:selectOneMenu>
                                    <h:outputText value="ESTRUCTURA DE PAGO DETALLE INI. : " />
                                    <h:selectOneMenu id="cboEstPagDetIni" style="width:200px" value="#{managerCLEstructuraPago.estPagDetIniId}">
                                        <f:selectItems value="#{managerCLEstructuraPago.cboEstPagDetIni}" />
                                        <a4j:support event="onchange" reRender="tbBtnEnlazar"/>
                                    </h:selectOneMenu>
                                </h:panelGrid>
                            </td>
                            <td>
                                <h:panelGrid columns="2"  id="tbCbosDetCont">
                                    <f:facet name="header">
                                        <h:outputText value="ESTRUCTURA DE PAGO DETALLE CONTINUACI�N" />
                                    </f:facet>
                                    <h:outputText value="CENTRO CONT. : " />
                                    <h:selectOneMenu style="width:200px" value="#{managerCLEstructuraPago.centroContId}">
                                        <f:selectItems value="#{managerCLEstructuraPago.cboCentros}" />
                                        <a4j:support event="onchange" reRender="cboAreaCont"/>
                                    </h:selectOneMenu>
                                    <h:outputText value="�REA CONT. : " />
                                    <h:selectOneMenu id="cboAreaCont" style="width:200px" value="#{managerCLEstructuraPago.areaContId}">
                                        <f:selectItems value="#{managerCLEstructuraPago.cboAreasCont}" />
                                        <a4j:support event="onchange" reRender="cboModCont"/>
                                    </h:selectOneMenu>
                                    <h:outputText value="M�DULO CONT. : " />
                                    <h:selectOneMenu id="cboModCont" style="width:200px" value="#{managerCLEstructuraPago.modContId}">
                                        <f:selectItems value="#{managerCLEstructuraPago.cboModsCont}" />
                                        <a4j:support event="onchange" reRender="cboCurCont"/>
                                    </h:selectOneMenu>
                                    <h:outputText value="CURSO CONT. : " />
                                    <h:selectOneMenu id="cboCurCont" style="width:200px" value="#{managerCLEstructuraPago.curContId}">
                                        <f:selectItems value="#{managerCLEstructuraPago.cboCursCont}" />
                                         <a4j:support event="onchange" reRender="cboTallCont"/>
                                    </h:selectOneMenu>
                                    <h:outputText value="TALLER CONT. : " />
                                    <h:selectOneMenu id="cboTallCont" style="width:200px" value="#{managerCLEstructuraPago.tallContId}">
                                        <f:selectItems value="#{managerCLEstructuraPago.cboTallsCont}" />
                                        <a4j:support event="onchange" reRender="cboEstPagCont"/>
                                    </h:selectOneMenu>
                                    
                                    <h:outputText value="ESTRUCTURA DE PAGO CONT. : " />
                                    <h:selectOneMenu id="cboEstPagCont" style="width:200px" value="#{managerCLEstructuraPago.estPagContId}">
                                        <f:selectItems value="#{managerCLEstructuraPago.cboEstPagCont}" />
                                        <a4j:support event="onchange" reRender="cboEstPagDetCont"/>
                                    </h:selectOneMenu>
                                    <h:outputText value="ESTRUCTURA DE PAGO DETALLE CONT. : " />
                                    <h:selectOneMenu id="cboEstPagDetCont" style="width:200px" value="#{managerCLEstructuraPago.estPagDetContId}">
                                        <f:selectItems value="#{managerCLEstructuraPago.cboEstPagDetCont}" />
                                        <a4j:support event="onchange" reRender="tbBtnEnlazar"/>
                                    </h:selectOneMenu>
                                </h:panelGrid>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="2">
                                <h:panelGrid style="width:100%" id="tbBtnEnlazar">
                                    <a4j:commandButton reRender="pnlTbDet" rendered="#{managerCLEstructuraPago.estPagDetIniId ne 0 && managerCLEstructuraPago.estPagDetContId ne 0}"
                                                       onclick="Richfaces.showModalPanel('mpConfirmarEnlazar')"
                                                       value="Enlazar"
                                                       title="Enlazar"  style="width: 100px; height: 30px;"/>
                                </h:panelGrid>
                            </th>
                        </tr>
                        <tr>
                            <th colspan="2">
                                <h:panelGroup id="pnlTbDet">
                                    <rich:datascroller id="masterScroll" for="tbEstPagDetEnlaz"
                                                       align="right" maxPages="30" rendered="#{not empty managerCLEstructuraPago.lstEstPagDetEnlazadas}"/>
                                    <rich:spacer height="20px" />
                                    <rich:dataTable style="width:100%" rows="5" id="tbEstPagDetEnlaz" rendered="#{not empty managerCLEstructuraPago.lstEstPagDetEnlazadas}" value="#{managerCLEstructuraPago.lstEstPagDetEnlazadas}" var="epdsec">
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="MOD. INI."/>
                                            </f:facet>
                                            <h:outputText rendered="#{not empty epdsec.estpagdetIni.clEstructuraPagos && not empty epdsec.estpagdetIni.clEstructuraPagos.clArbolAcademico}" value="#{epdsec.estpagdetIni.clEstructuraPagos.clArbolAcademico.arbDescripcion}" />
                                            <br /><h:outputText value="(#{epdsec.estpagdetSecId})" style="color:#fff;"/>
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="CUR. INI."/>
                                            </f:facet>
                                            <h:outputText rendered="#{not empty epdsec.estpagdetIni.clArbolAcadCurso}" value="#{epdsec.estpagdetIni.clArbolAcadCurso.arbDescripcion}" />
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="EST. PAG. INI."/>
                                            </f:facet>
                                            <h:outputText rendered="#{not empty epdsec.estpagdetIni.clEstructuraPagos}" value="#{epdsec.estpagdetIni.clEstructuraPagos.estpagNombre}" />
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="EST. PAG. DET. INI"/>
                                            </f:facet>
                                            <h:outputText value="#{epdsec.estpagdetIni.estpagdetNombre}" />
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="MONTO INI"/>
                                            </f:facet>
                                            <h:outputText value="s/. #{epdsec.estpagdetIni.estpagdetMonto}" />
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="MOD. CONT."/>
                                            </f:facet>
                                            <h:outputText rendered="#{not empty epdsec.estpagdetCont.clEstructuraPagos && not empty epdsec.estpagdetCont.clEstructuraPagos.clArbolAcademico}" value="#{epdsec.estpagdetCont.clEstructuraPagos.clArbolAcademico.arbDescripcion}" />
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="CUR. CONT."/>
                                            </f:facet>
                                            <h:outputText rendered="#{not empty epdsec.estpagdetCont.clArbolAcadCurso}" value="#{epdsec.estpagdetCont.clArbolAcadCurso.arbDescripcion}" />
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="EST. PAG. CONT."/>
                                            </f:facet>
                                            <h:outputText rendered="#{not empty epdsec.estpagdetCont.clEstructuraPagos}" value="#{epdsec.estpagdetCont.clEstructuraPagos.estpagNombre}" />
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="EST. PAG. DET. CONT"/>
                                            </f:facet>
                                            <h:outputText value="#{epdsec.estpagdetCont.estpagdetNombre}" />
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="MONTO CONT."/>
                                            </f:facet>
                                            <h:outputText value="s/. #{epdsec.estpagdetCont.estpagdetMonto}" />
                                        </rich:column>
                                    </rich:dataTable>
                                </h:panelGroup>
                            </th>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="mpConfirmarEnlazar" width="300" height="100" zindex="3000" >
                <f:facet name="header">
                    <h:outputText value="Confirmar Enlace de Estructuras de Pagos Detalle"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mpConfirmarEnlazar')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="frmConfirmEnlaceEstPagDet">
                    <table style="width: 100%;height: 100%;">
                        <tr>
                            <th colspan="2">
                                <h:outputText value="�Seguro que desea asignar la continuaci�n?" />
                            </th> 
                        </tr>
                        <tr>
                            <th>
                                <a4j:commandButton actionListener="#{managerCLEstructuraPago.enlazarEstPagDet}" 
                                                   reRender="frmEnlaceEstPagDet" oncomplete="#{managerCLEstructuraPago.oncomplete}"
                                                   value="Enlazar"
                                                   title="Enlazar" style="width: 100px; height: 30px;" />

                            </th>
                            <th>
                                <a4j:commandButton onclick="Richfaces.hideModalPanel('mpConfirmarEnlazar')"
                                                   value="Cancelar"
                                                   title="Cancelar" style="width: 100px; height: 30px;"/>
                            </th>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>
        </f:view>
    </body>
</html>