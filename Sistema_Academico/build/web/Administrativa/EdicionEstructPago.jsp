<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Mantenimiento de Estructura de Pagos de los Alumnos</title>
        <script type="text/javascript">
            function printObjectsSelected_estpag(sgcomponent,idAL,semingAL,espAL,nombreAL,codigoAL) {
                idAL.value=sgcomponent.getSelectedItems().pluck('b_alu_id');
                semingAL.value=sgcomponent.getSelectedItems().pluck('modal_SemIngreso');
                espAL.value=sgcomponent.getSelectedItems().pluck('modal_Especialidad');
                nombreAL.innerHTML=sgcomponent.getSelectedItems().pluck('b_alumno');
                codigoAL.innerHTML=sgcomponent.getSelectedItems().pluck('b_codigo');
            }
        </script>
    </head>
    <body>
        <f:view>
            <jsp:include page="../TablaSistema/Menu.jsp"></jsp:include>
            <rich:panel style=" width : 100%;" >
                <f:facet name="ESTRUCTURA DE PAGO ALUMNO"></f:facet>
                <h:form id="formInput" style="width:100%">
                    <table style="font-size:13px; font-family:verdana; width: 100%">
                        <tr>
                            <td width="20%" colspan="2" ><strong>EDICION DE LAS ESTRUCTURAS DE PAGO DE LOS ALUMNOS</strong></td>
                            <td width="13%"></td>
                            <td width="47%" align="right"></td>
                            <td width="10%" align="right">
                                <h:graphicImage value="/Imagenes/actions/filenew.png" style="border-width: 0px;cursor: pointer;">
                                    <a4j:support event="onclick" onsubmit="hideDiv({duration:0.1});"
                                                 oncomplete="Richfaces.showModalPanel('mpEstructuraNuevo',{width:450, top:120})"
                                                 action="#{managerEdicionEstructPago.nuevo}"
                                                 reRender="iAlumno, iDestino, iPlantilla, modelInput" />
                                </h:graphicImage>
                            </td>
                            <td align=right width="10%">
                                <a4j:commandButton type="button" id="buscar" value="" title="Buscar" reRender="tableData"
                                                   action="#{managerEdicionEstructPago.validarBusqueda}"
                                                   oncomplete="#{managerEdicionEstructPago.oncomplete}"
                                                   image="/Imagenes/actions/viewmag.png"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="7" width="100%"><hr width="100%" size="1" />
                        </tr>
                        <tr>
                            <td width="20%" colspan="2" />
                            <td width="20%" colspan="2" />
                            <td width="60%" />
                        </tr>
                        <tr style="height : 20px;">
                            <td width="13%" >Codigo: </td>
                            <td width="27%">
                                <h:inputText value="#{managerEdicionEstructPago.b_codigo}" style="width : 180px;"/>
                            </td>
                            <td width="13%">Semestre Estructura:</td>
                            <td width="27%">
                                <h:selectOneMenu value="#{managerEdicionEstructPago.parametroSemestre}" style="width : 180px;">
                                    <f:selectItems value="#{managerEdicionEstructPago.parametroSemestres}"/>
                                </h:selectOneMenu>
                            </td>
                            <td width="20%"/>
                        </tr>
                        <tr style="height : 20px;">
                            <td width="13%" >Apellido paterno: </td>
                            <td width="27%">
                                <h:inputText value="#{managerEdicionEstructPago.b_paterno}" style="width : 180px;" />
                            </td>
                            <td width="13%"/><td width="27%"/><td width="20%"/>
                        </tr>
                        <tr style="height : 20px;">
                            <td width="13%" >Apellido materno: </td>
                            <td width="27%">
                                <h:inputText value="#{managerEdicionEstructPago.b_materno}" style="width : 180px;" />
                            </td>
                            <td width="13%"/><td width="27%"/><td width="20%"/>
                        </tr>
                        <tr style="height : 20px;">
                            <td width="13%" >Nombres: </td>
                            <td width="27%">
                                <h:inputText value="#{managerEdicionEstructPago.b_nombre}" style="width : 180px;" />
                            </td>
                            <td width="13%"/><td width="27%"/><td width="20%"/>
                        </tr>
                        <tr>
                            <td colspan="7" width="100%"><hr width="100%" size="1"></td>
                        </tr>
                        <tr><td width="13%"/><td width="27%"/><td width="13%"/><td width="27%"/><td width="20%"/></tr>
                    </table>
                </h:form>
                <h:form id="tableData" style=" width : 100%;">
                    <table style="width: 100%">
                        <tr>
                            <td align="right">
                                <rich:datascroller id="scrollTableMaster" for="tablaMaster"
                                                   align="rigth" maxPages="8"/>
                            </td>
                        </tr>
                    </table>
                    <rich:dataTable id="tablaMaster" rows="10"
                                    onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                    cellpadding="0" cellspacing="0"
                                    width="100%" border="0" value="#{managerEdicionEstructPago.listaEstructura}"
                                    var="estructPag">
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Id" />
                            </f:facet>
                            <h:outputText value="#{estructPag.b_alu_id}"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Codigo" />
                            </f:facet>
                            <h:outputText value="#{estructPag.b_codigo}"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Alumno" />
                            </f:facet>
                            <h:outputText value="#{estructPag.b_alumno}" />
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Especialidad" />
                            </f:facet>
                            <h:outputText value="#{estructPag.b_especialidad}" />
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Semestre Ingreso"/>
                            </f:facet>
                            <h:outputText value="#{estructPag.b_semestreIngreso}" />
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Semestre Estructura"/>
                            </f:facet>
                            <h:outputText value="#{estructPag.b_semestreEstructura}" />
                        </rich:column>
                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Ver" />
                            </f:facet>
                            <f:param id="p_ver" value="#{estructPag.estadoVer}" />
                            <h:graphicImage value="#{estructPag.imagenVer}"
                                            style="border-width: 0px; cursor: pointer;"
                                            title="Ver detalle de la Estructura">
                                <a4j:support event="onclick" reRender="tablaMaster"
                                             actionListener="#{estructPag.mostrarDetalle}" />
                            </h:graphicImage>
                        </rich:column>
                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Imprimir" />
                            </f:facet>
                            <f:param id="alu_codigo" value="#{estructPag.b_codigo}"/>
                            <f:param id="alu_semestre" value="#{estructPag.b_semestreEstructura_id}"/>
                            <a4j:commandButton image="/Imagenes/actions/fileprint.png"
                                               title="Imprimir historial de pago"
                                               reRender="reporte"
                                               actionListener="#{managerEdicionEstructPago.imprimir}"
                                               oncomplete="Richfaces.showModalPanel('mpRepEstPag',{top:80})"
                                               style="border-width: 0px;"/>

                        </rich:column>
                        <rich:column style="text-align: center;">
                            <f:facet name="header" >
                                <h:outputText value="Eliminar" />
                            </f:facet>
                            <f:param id="estpag_alutarIds" value="#{estructPag.b_alutarIds}"/>
                            <a4j:commandButton image="/Imagenes/actions/no.png"
                                               title="Eliminar"
                                               actionListener="#{managerEdicionEstructPago.elimParam}"
                                               oncomplete="Richfaces.showModalPanel('mpEliminar',{width:300, top:200})"
                                               style="cursor: pointer;"/>
                        </rich:column>
                        
                        <rich:subTable id="subTable" value="#{estructPag.subListaEstructura}"
                                       var="subEstruct" rendered="#{estructPag.mostrar}"
                                       onRowMouseOver="this.style.backgroundColor='#F8F8F8'"
                                       onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'">
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Concepto" />
                                </f:facet>
                                <h:outputText value="#{subEstruct.b_sub_concepto}" />
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Monto a pagar" />
                                </f:facet>
                                <h:outputText value="#{subEstruct.b_sub_monto}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Fecha de pago" />
                                </f:facet>
                                <h:outputText value="#{subEstruct.b_sub_fecha_pago}">
                                    <f:convertDateTime pattern="dd  'de'  MMMMMM  'del'  yyyy"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Fecha de prorroga" />
                                </f:facet>
                                <h:outputText value="#{subEstruct.b_sub_fecha_prorroga}">
                                    <f:convertDateTime pattern="dd  'de'  MMMMMM  'del'  yyyy"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="Abrir/Cerrar pago" />
                                </f:facet>
                                <f:param id="alutar_id" value="#{subEstruct.b_sub_alutar_id}" />
                                <f:param id="alutar_estado" value="#{subEstruct.b_sub_alutar_estado}" />
                                <f:param id="mensaje" value="#{subEstruct.b_sub_msj}" />
                                <a4j:commandButton image="#{subEstruct.b_sub_img_candado}"
                                                   style="border-width: 0px;"
                                                   title="#{subEstruct.b_sub_title}"
                                                   reRender="mod_msj"
                                                   actionListener="#{managerEdicionEstructPago.salvaParam}"
                                                   oncomplete="Richfaces.showModalPanel('mpCandado',{width:300, top:200})"/>
                            </rich:column>
                            <rich:column style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="Editar" />
                                </f:facet>
                                <f:param id="alutar_monto" value="#{subEstruct.b_sub_monto}"/>
                                <f:param id="alutar_pago" value="#{subEstruct.b_sub_fecha_pago}"/>
                                <f:param id="alutar_prorroga" value="#{subEstruct.b_sub_fecha_prorroga}"/>
                                <a4j:commandButton image="#{subEstruct.b_sub_img_klipper}"
                                                   style="border-width: 0px;"
                                                   title="Editar estructura"
                                                   disabled="#{subEstruct.b_sub_edit_disabled}"
                                                   reRender="mod_monto, mod_pago, mod_prorroga"
                                                   actionListener="#{managerEdicionEstructPago.salvaEditar}"
                                                   oncomplete="Richfaces.showModalPanel('mpEditar',{top:200})"/>
                            </rich:column>
                            <rich:column colspan="4" style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="Compromisos de pago" />
                                </f:facet>
                                <f:param id="est_desc" value="#{subEstruct.b_sub_concepto}"/>
                                <a4j:commandButton image="#{subEstruct.b_sub_img_calendar}"
                                                   style="border-width: 0px;"
                                                   title="Compromisos de pago"
                                                   disabled="#{subEstruct.b_sub_edit_disabled}"
                                                   reRender="fromProrroga, tablaProrroga, scrollProrroga, proconcep, propago, proprorroga"
                                                   actionListener="#{managerEdicionEstructPago.salvaCompromiso}"
                                                   oncomplete="Richfaces.showModalPanel('mpProrroga',{width:450, top:120})"/>
                            </rich:column>
                            
                            <rich:column style="text-align: center;">
                            <f:facet name="header" >
                                <h:outputText value="Eliminar Pago" />
                            </f:facet>
                            <f:param id="alutar_id_pag" value="#{subEstruct.b_sub_alutar_id}" />
                            <a4j:commandButton image="/Imagenes/actions/no.png"
                                               title="Eliminar"
                                               actionListener="#{managerEdicionEstructPago.elimParamPago}"
                                               oncomplete="Richfaces.showModalPanel('mpEliminarPago',{width:300, top:200})"
                                               style="cursor: pointer;"/>
                        </rich:column>
                            
                        </rich:subTable>
                    </rich:dataTable>
                </h:form>
            </rich:panel>

            <rich:modalPanel id="mpEstructuraNuevo"
                             minHeight="210" minWidth="440"
                             height="210" width="470"
                             zindex="2000" onshow="showDiv();">
                <rich:effect  name="hideDiv"  for="divNuevo" type="Fade" />
                <rich:effect  name="showDiv"  for="divNuevo" type="Appear" />
                <div id="divNuevo">
                    <f:facet name="header">
                        <h:outputText value="Nueva estructura de pago" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png"
                                        style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mpEstructuraNuevo')"
                                        title="Cerrar"/>
                    </f:facet>
                    <h:form id="modelInput">
                        <table width="100%" style="font-size:13px; font-family:verdana">
                            <tr style=" height : 30px;">
                                <td width="50%" >
                                    <h:outputLabel value="Alumno" style="width:200px"/>
                                </td>
                                <td>
                                    <h:inputText id="iAlumno" value="#{managerEdicionEstructPago.sugAlumno}" style="width: 250px;"/>
                                    <rich:suggestionbox id="suggestionEst" var="alum"
                                                        for="iAlumno" nothingLabel="Alumno No Encontrado"
                                                        suggestionAction="#{managerEdicionEstructPago.autocomplete}"
                                                        height="150" width="400" cellpadding="2"
                                                        cellspacing="2" shadowOpacity="4" shadowDepth="4"
                                                        minChars="3" rules="none" zindex="3500"
                                                        usingSuggestObjects="true"
                                                        reRender="iPlantilla"  onsubmit=""
                                                        onobjectchange="printObjectsSelected_estpag(#{rich:component('suggestionEst')},#{rich:element('idAL')},#{rich:element('semingAL')},#{rich:element('espAL')},#{rich:element('nombreAL')},#{rich:element('codigoAL')});">

                                        <h:column>
                                            <h:outputText value="#{alum.b_alumno}" style="font-weight: bold;" />
                                        </h:column>
                                        <h:column>
                                            <h:outputText value="#{alum.b_codigo}" style="font-weight: bold;" />
                                        </h:column>
                                    </rich:suggestionbox>
                                </td>
                            </tr>
                            <tr style="height : 10px; text-align: right;">
                                <td colspan="3">
                                    <h:outputText id="codigoAL" value="#{managerEdicionEstructPago.sugCodigo}"/> -
                                    <h:outputText id="nombreAL" value="#{managerEdicionEstructPago.sugAlumno}"/>
                                    <h:inputHidden id="idAL" value="#{managerEdicionEstructPago.modal_id}"/>
                                    <h:inputHidden id="semingAL" value="#{managerEdicionEstructPago.modal_SemIngreso}"/>
                                    <h:inputHidden id="espAL" value="#{managerEdicionEstructPago.modal_Especialidad}"/>
                                </td>
                            </tr>
                            <tr style="height : 30px;">
                                <td width="50%" >
                                    <h:outputLabel value="Semestre destino"/>
                                </td>
                                <td>
                                    <h:selectOneMenu id="iDestino" style="width: 200px;"
                                                     value="#{managerEdicionEstructPago.modalSemestre}" >
                                        <f:selectItems value="#{managerEdicionEstructPago.modalSemestres}"/>
                                        <a4j:support event="onchange" reRender="iPlantilla"
                                                     action="#{managerEdicionEstructPago.parametrosEstructura}" />
                                    </h:selectOneMenu>
                                </td>
                            </tr>
                            <tr style="height : 30px;">
                                <td width="50%" >
                                    <h:outputLabel value="Plantilla de Estructura de Pago"/>
                                </td>
                                <td>
                                    <h:selectOneMenu id="iPlantilla" style="width: 200px;"
                                                     value="#{managerEdicionEstructPago.modalPlantilla}">
                                        <f:selectItems value="#{managerEdicionEstructPago.modalPlantillas}"/>
                                    </h:selectOneMenu>
                                </td>
                            </tr>
                            <tr style="height: 30px" />
                            <tr style="height: 30px">
                                <td colspan="2">
                                    <a4j:commandButton value="Generar estructuras"
                                                       actionListener="#{managerEdicionEstructPago.validarEstructuras}"
                                                       oncomplete="#{managerEdicionEstructPago.oncomplete}"/>
                                </td>
                            </tr>
                        </table>
                    </h:form>
                </div>
            </rich:modalPanel>

            <rich:modalPanel  id="mpCandado"
                              minHeight="150" minWidth="100"
                              height="150" width="100"
                              zindex="2000" onshow="showDiv();">
                <rich:effect  name="hideDiv"  for="contentDivCandado" type="Fade" />
                <rich:effect  name="showDiv"  for="contentDivCandado" type="Appear" />
                <div id="contentDivCandado">
                    <f:facet name="header">
                        <h:outputText value="Abrir/cerar candado" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png"
                                        style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mpCandado')"
                                        title="Cerrar"/>
                    </f:facet>
                    <center>
                        <h:form id="fromCandado">
                            <h:outputLabel>
                                <h2>Esta seguro de <h:outputText id="mod_msj" value="#{managerEdicionEstructPago.b_sub_msj}"/></h2>
                            </h:outputLabel>
                            <a4j:commandButton value="Aceptar" reRender="tableData"
                                               oncomplete="#{managerEdicionEstructPago.oncomplete}"
                                               action="#{managerEdicionEstructPago.actualizarEstado}"/>
                            <rich:spacer width="20px" />
                            <h:commandButton value="Cancelar" onclick="Richfaces.hideModalPanel('mpCandado')" />
                        </h:form>
                    </center>
                </div>
            </rich:modalPanel>

            <rich:modalPanel  id="mpEliminar"
                              minHeight="150" minWidth="100"
                              height="150" width="100"
                              zindex="2000" onshow="showDiv();">
                <rich:effect  name="hideDiv"  for="contentDivEliminar" type="Fade" />
                <rich:effect  name="showDiv"  for="contentDivEliminar" type="Appear" />
                <div id="contentDivCandado">
                    <f:facet name="header">
                        <h:outputText value="Eliminar" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png"
                                        style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mpEliminar')"
                                        title="Cerrar"/>
                    </f:facet>
                    <center>
                        <h:form id="fromEliminar">
                            <h:outputLabel>
                                <h2>Esta seguro de eliminar las estructuras generadas?</h2>
                            </h:outputLabel>
                            <a4j:commandButton value="Aceptar" reRender="tableData"
                                               oncomplete="#{managerEdicionEstructPago.oncomplete}"
                                               action="#{managerEdicionEstructPago.eliminarEstructura}"/>
                            <rich:spacer width="20px" />
                            <h:commandButton value="Cancelar" onclick="Richfaces.hideModalPanel('mpEliminar')" />
                        </h:form>
                    </center>
                </div>
            </rich:modalPanel>
            
            <rich:modalPanel  id="mpEliminarPago"
                              minHeight="150" minWidth="100"
                              height="150" width="100"
                              zindex="2000" onshow="showDiv();">
                <rich:effect  name="hideDiv"  for="contentDivEliminarPago" type="Fade" />
                <rich:effect  name="showDiv"  for="contentDivEliminarPago" type="Appear" />
                <div id="contentDivEliminarPago">
                    <f:facet name="header">
                        <h:outputText value="Eliminar Pago" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png"
                                        style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mpEliminarPago')"
                                        title="Cerrar"/>
                    </f:facet>
                    <center>
                        <h:form id="fromEliminarPago">
                            <h:outputLabel>
                                <h2>Esta seguro de Eliminar el pago?</h2>
                            </h:outputLabel>
                            <a4j:commandButton value="Aceptar" reRender="tableData"
                                               oncomplete="#{managerEdicionEstructPago.oncomplete}"
                                               action="#{managerEdicionEstructPago.eliminarPago}"/>
                            <rich:spacer width="20px" />
                            <h:commandButton value="Cancelar" onclick="Richfaces.hideModalPanel('mpEliminarPago')" />
                        </h:form>
                    </center>
                </div>
            </rich:modalPanel>

            <rich:modalPanel  id="mpEditar" minWidth="350"
                              autosized="true" zindex="2000">
                <f:facet name="header">
                    <h:outputText value="Editar datos del alumno Tarifa" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png"
                                    style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('mpEditar')"
                                    title="Cerrar"/>
                </f:facet>
                <center>
                    <h:form id="fromEditar">
                        <table width="100%">
                            <tr>
                                <td width="40%"></td>
                                <td align="right">
                                    <a4j:commandButton
                                        image="/Imagenes/actions/filesave.png"
                                        title="Guardar"
                                        action="#{managerEdicionEstructPago.editar}"
                                        reRender="tablaMaster, scrollTableMaster, subTable"
                                        oncomplete="#{managerEdicionEstructPago.oncomplete}"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3"><hr width="100%"/></td>
                            </tr>
                            <tr style="height: 30px;">
                                <td><h:outputLabel styleClass="label-mpNuevoEstPagAlu" value="Monto:" /></td>
                                <td><h:inputText id="mod_monto" style="width : 180px;"
                                             value="#{managerEdicionEstructPago.modal_monto}" />
                                </td>
                            </tr>
                            <tr style="height: 30px;">
                                <td><h:outputLabel styleClass="label-mpNuevoEstPagAlu" value="Fecha de pago:" /></td>
                                <td><rich:calendar id="mod_pago" datePattern="dd-MM-yyyy" style="width : 200px;"
                                               value="#{managerEdicionEstructPago.modal_pago}"/>
                                </td>
                            </tr>
                            <tr style="height: 30px;">
                                <td><h:outputLabel styleClass="label-mpNuevoEstPagAlu" value="Fecha de Prorroga:" /></td>
                                <td><rich:calendar id="mod_prorroga" datePattern="dd-MM-yyyy" style="width : 200px;"
                                               value="#{managerEdicionEstructPago.modal_prorroga}" />
                                </td>
                            </tr>
                        </table>
                    </h:form>
                </center>
            </rich:modalPanel>

            <rich:modalPanel id="mpRepEstPag" zindex="4000" autosized="true">
                <f:facet name="header">
                    <h:outputText id="titulo" value="Historial de Pagos" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" 
                                    onclick="Richfaces.hideModalPanel('mpRepEstPag')" title="Cerrar"/>
                </f:facet>
                <h:form id="fromRepEstPag">
                    <table>
                        <tr>
                            <td align="center" valign="middle">
                                <rich:panel>
                                    <a4j:mediaOutput id="reporte" uriAttribute="data" 
                                                     style="width : 650px; height: 500px;"
                                                     element="object" standby="cargando..."
                                                     createContent="#{managerEdicionEstructPago.reporteMantEstPag}"
                                                     mimeType="application/pdf"
                                                     value="histPag"/>
                                </rich:panel>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>


            <rich:modalPanel  id="mpProrroga"
                              minHeight="450" minWidth="450"
                              height="450" width="450"
                              zindex="2000" onshow="showDiv();">
                <rich:effect  name="hideDiv"  for="contentDivProrroga" type="Fade" />
                <rich:effect  name="showDiv"  for="contentDivProrroga" type="Appear" />
                <div id="contentDivProrroga">
                    <f:facet name="header">
                        <h:outputText value="Editar compromisos de pago" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png"
                                        style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mpProrroga')"
                                        title="Cerrar"/>
                    </f:facet>
                    <h:form id="fromProrrogaActions"  style=" width : 100%;">
                        <table style="width: 100%">
                            <tr>
                                <td width="28%"></td>
                                <td></td>
                                <td align="right">
                                    <a4j:commandButton
                                        action="#{managerEdicionEstructPago.guardarCompromiso}"
                                        oncomplete="#{managerEdicionEstructPago.oncomplete}"
                                        image="/Imagenes/actions/filesave.png"
                                        title="Guardar"
                                        reRender="tablaMaster, scrollTableMaster"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="7" width="100%"><hr width="100%" size="1" />
                            </tr>
                            <tr style="height : 20px;">
                                <td width="13%" >Concepto de pago: </td>
                                <td>
                                    <h:outputLabel id="proconcep"
                                                   value="#{managerEdicionEstructPago.pro_v_desc_concepto}"/>
                                </td>                                
                            </tr>
                            <tr style="height : 20px;">
                                <td width="13%" >Fecha de pago: </td>
                                <td>
                                    <h:outputLabel id="propago"
                                                   value="#{managerEdicionEstructPago.pro_v_fecha_pago}">
                                        <f:convertDateTime pattern="dd  'de'  MMMMMM  'del'  yyyy" />
                                    </h:outputLabel>
                                </td>
                            </tr>
                            <tr style="height : 20px;">
                                <td width="13%" >Fecha de prorroga: </td>
                                <td>
                                    <h:outputLabel id="proprorroga"
                                                   value="#{managerEdicionEstructPago.pro_v_fecha_prorroga}">
                                        <f:convertDateTime pattern="dd  'de'  MMMMMM  'del'  yyyy" />
                                    </h:outputLabel>
                                </td>
                            </tr>
                            <tr style="height : 20px;">
                                <td width="13%" >Fecha de compromiso: </td>
                                <td>
                                    <rich:calendar id="calProrroga" datePattern="dd-MM-yyyy"
                                                   value="#{managerEdicionEstructPago.pro_param_fecha}" />
                                </td>
                            </tr>
                            <tr style="height : 20px;">
                                <td width="13%" >Comentario: </td>
                                <td>
                                    <h:inputTextarea id="comProrroga" rows="3" cols="20" value="#{managerEdicionEstructPago.pro_param_coment}" />
                                </td>
                                <td align="right">
                                    <a4j:commandButton   image="/Imagenes/actions/edit_add.png"  title="Agregar"
                                                         action="#{managerEdicionEstructPago.agregarCompromiso}"
                                                         reRender="tablaProrroga, scrollProrroga, calProrroga, comProrroga"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="7" width="100%"><hr width="100%" size="1" />
                            </tr>
                        </table>
                    </h:form>
                    <h:form id="fromProrroga" style=" width : 100%;">
                        <table style="width: 100%">
                            <tr>
                                <td align="right">
                                    <rich:datascroller id="scrollProrroga" for="tablaProrroga"
                                                       align="rigth" maxPages="3"/>
                                </td>
                            </tr>
                        </table>
                        <rich:dataTable id="tablaProrroga" rows="10"
                                        onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                        onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                        cellpadding="0" cellspacing="0"
                                        width="100%" border="0" value="#{managerEdicionEstructPago.proListaProrrogas}"
                                        var="pro">
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Numero" />
                                </f:facet>
                                <h:outputText value="#{pro.pro_numero}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Fecha" />
                                </f:facet>
                                <h:outputText value="#{pro.pro_fecha}" >
                                    <f:convertDateTime pattern="dd  'de'  MMMMMM  'del'  yyyy" />
                                </h:outputText>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Comentario" />
                                </f:facet>
                                <h:outputText value="#{pro.pro_coment}"/>
                            </rich:column>
                            <rich:column style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="Eliminar" />
                                </f:facet>
                                <f:param id="pos" value="#{pro.pro_pos}" />
                                <f:param id="creac" value="#{pro.pro_creacion}" />
                                <a4j:commandButton
                                    actionListener="#{managerEdicionEstructPago.quitarCompromiso}"
                                    image="#{pro.pro_img_quitar}"
                                    disabled="#{pro.pro_img_disable}"
                                    reRender="tablaProrroga, scrollProrroga"
                                    title="Eliminar"/>
                            </rich:column>
                        </rich:dataTable>
                    </h:form>
                </div>
            </rich:modalPanel>

        </f:view>
    </body>
</html>