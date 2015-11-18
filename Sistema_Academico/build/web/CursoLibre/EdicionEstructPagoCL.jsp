<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Edicion de Estructura de Pagos</title>
        <script type="text/javascript">
            function printObjectsSelected_estpag(sgcomponent,idAL,nombreAL,codigoAL) {
                idAL.value=sgcomponent.getSelectedItems().pluck('b_alu_id');
                nombreAL.innerHTML=sgcomponent.getSelectedItems().pluck('b_alumno');
                codigoAL.innerHTML=sgcomponent.getSelectedItems().pluck('b_codigo');
            }
        </script>
    </head>
    <body>
        <f:view>
            <jsp:include page="../TablaSistema/Menu.jsp"></jsp:include>
            <rich:panel style=" width : 100%;" >
                <f:facet name="ESTRUCTURA DE PAGO ALUMNO CL"></f:facet>
                <h:form id="formInput" style="width:100%">
                    <table style="font-size:13px; font-family:verdana; width: 100%">
                        <tr>
                            <td width="20%" colspan="2" ><strong>EDICION DE LAS ESTRUCTURAS DE PAGO DE LOS ALUMNOS DE CURSOS LIBRES</strong></td>
                            <td width="13%"></td>
                            <td width="47%" align="right"></td>
                            <td width="10%" align="right">
                               <% /* <h:graphicImage value="/Imagenes/actions/filenew.png" style="border-width: 0px;cursor: pointer;">
                                    <a4j:support event="onclick"
                                                 oncomplete="Richfaces.showModalPanel('mpEstructuraNuevo',{top:100})"
                                                 action="#{managerCLEditEstPag.nuevo}"
                                                 reRender="iAlumno,iArea, iDestino, iPlantilla, modelInput" />
                                </h:graphicImage>*/ %>
                            </td>
                            <td align=right width="10%">
                                <a4j:commandButton type="button" id="buscar" value="" title="Buscar" reRender="tableData"
                                                   action="#{managerCLEditEstPag.validarBusqueda}"
                                                   oncomplete="#{managerCLEditEstPag.oncomplete}"
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
                                <h:inputText value="#{managerCLEditEstPag.b_codigo}" style="width : 180px;"/>
                            </td>
                            <td width="13%">Area: </td>
                            <td width="27%">
                                <h:selectOneMenu value="#{managerCLEditEstPag.parametroArea}">
                                    <f:selectItems value="#{managerCLEditEstPag.parametroAreas}" />
                                    <a4j:support event="onchange" reRender="v_mod" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr style="height : 20px;">
                            <td width="13%" >Apellido paterno: </td>
                            <td width="27%">
                                <h:inputText value="#{managerCLEditEstPag.b_paterno}" style="width : 180px;" />
                            </td>
                            <td width="13%">Modulo: </td>
                            <td width="27%">
                                <h:selectOneMenu id="v_mod" value="#{managerCLEditEstPag.parametroModulo}">
                                    <f:selectItems value="#{managerCLEditEstPag.parametroModulos}"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr style="height : 20px;">
                            <td width="13%" >Apellido materno: </td>
                            <td width="27%">
                                <h:inputText value="#{managerCLEditEstPag.b_materno}" style="width: 180px;" />
                            </td>
                        </tr>
                        <tr style="height : 20px;">
                            <td width="13%" >Nombres: </td>
                            <td width="27%">
                                <h:inputText value="#{managerCLEditEstPag.b_nombre}" style="width: 180px;" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="7" width="100%"><hr width="100%" size="1"></td>
                        </tr>
                    </table>
                </h:form>
                <h:form id="tableData" style=" width : 100%;">
                    <rich:datascroller id="scrollTableMaster" for="tablaMaster" align="right" maxPages="8"/>
                    <rich:spacer height="23px"/>
                    <rich:dataTable id="tablaMaster" rows="10"
                                    onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                    cellpadding="0" cellspacing="0"
                                    width="100%" border="0" value="#{managerCLEditEstPag.listaEstructura}"
                                    var="estructPag">
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Id" />
                            </f:facet>
                            <h:outputText value="#{estructPag.b_alu_id}"/>
                        </rich:column>
                        <rich:column style="text-align: center;">
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
                        <rich:column colspan="2">
                            <f:facet name="header">
                                <h:outputText value="Modulo" />
                            </f:facet>
                            <h:outputText value="#{estructPag.b_modulo}" />
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
                            <f:facet name="header" >
                                <h:outputText value="Eliminar" />
                            </f:facet>
                            <f:param id="estpag_alutarIds" value="#{estructPag.b_alutarIds}"/>
                            <a4j:commandButton image="/Imagenes/actions/delete.gif"
                                               title="Eliminar"
                                               actionListener="#{managerCLEditEstPag.elimParam}"
                                               oncomplete="Richfaces.showModalPanel('mpEliminar',{width:300, top:200})"
                                               style="cursor: pointer;"/>
                        </rich:column>
                        <rich:subTable id="subTable" value="#{estructPag.subListaEstructura}"
                                       onRowMouseOver="this.style.backgroundColor='#F8F8F8'"
                                       onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                       var="subEstruct" rendered="#{estructPag.mostrar}">
                            <rich:column />
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
                                                   title="#{subEstruct.b_sub_title}"
                                                   reRender="mod_msj"
                                                   actionListener="#{managerCLEditEstPag.salvaParam}"
                                                   oncomplete="Richfaces.showModalPanel('mpCandado',{top:200});"/>
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
                                                   actionListener="#{managerCLEditEstPag.salvaEditar}"
                                                   oncomplete="Richfaces.showModalPanel('mpEditar',{top:120})"/>
                            </rich:column>
                        </rich:subTable>
                    </rich:dataTable>
                </h:form>
            </rich:panel>

            <rich:modalPanel id="mpEstructuraNuevo" minWidth="650"
                             autosized="true" zindex="2000">
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
                            <td width="160px" >
                                <h:outputLabel value="Alumno"/>
                            </td>
                            <td>
                                <h:inputText id="iAlumno" value="#{managerCLEditEstPag.sugAlumno}" style="width: 270px;"/>
                                <rich:suggestionbox id="suggestionEst" var="alum"
                                                    for="iAlumno" nothingLabel="Alumno No Encontrado"
                                                    suggestionAction="#{managerCLEditEstPag.autocomplete}"
                                                    height="150" width="400" cellpadding="2"
                                                    cellspacing="2" shadowOpacity="4" shadowDepth="4"
                                                    minChars="3" rules="none" zindex="3500"
                                                    usingSuggestObjects="true"
                                                    reRender="iPlantilla, iMatricula"  onsubmit=""
                                                    onobjectchange="printObjectsSelected_estpag(#{rich:component('suggestionEst')},#{rich:element('idAL')},#{rich:element('nombreAL')},#{rich:element('codigoAL')});">

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
                            <td colspan="2">
                                <h:outputText id="codigoAL" value="#{managerCLEditEstPag.sugCodigo}"/> -
                                <h:outputText id="nombreAL" value="#{managerCLEditEstPag.sugAlumno}"/>
                                <h:inputHidden id="idAL" value="#{managerCLEditEstPag.modal_id}"/>
                            </td>
                        </tr>
                        <tr style="height : 30px;">
                            <td>
                                <h:outputLabel value="Area para el modulo"/>
                            </td>
                            <td>
                                <h:selectOneMenu id="iArea" value="#{managerCLEditEstPag.modalArea}" >
                                    <f:selectItems value="#{managerCLEditEstPag.modalAreas}"/>
                                    <a4j:support event="onchange" reRender="iDestino" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr style="height : 30px;">
                            <td>
                                <h:outputLabel value="Modulo destino"/>
                            </td>
                            <td>
                                <h:selectOneMenu id="iDestino" value="#{managerCLEditEstPag.modalModulo}" >
                                    <f:selectItems value="#{managerCLEditEstPag.modalModulos}"/>
                                    <a4j:support event="onchange" reRender="iPlantilla"
                                                 action="#{managerCLEditEstPag.parametrosPlantilla}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr style="height : 30px;">
                            <td>
                                <h:outputLabel value="Plantilla de Estructura de Pago"/>
                            </td>
                            <td>
                                <h:selectOneMenu id="iPlantilla" style="width: 240px;"
                                                 value="#{managerCLEditEstPag.modalPlantilla}">
                                    <f:selectItems value="#{managerCLEditEstPag.modalPlantillas}"/>
                                    <a4j:support event="onchange" reRender="iTaller"
                                                 action="#{managerCLEditEstPag.parametrosTaller}"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr style="height : 30px;">
                            <td>
                                <h:outputLabel value="Taller Aperturado"/>
                            </td>
                            <td>
                                <h:selectOneMenu id="iTaller" style="width: 270px;"
                                                 value="#{managerCLEditEstPag.modalTaller}">
                                    <f:selectItems value="#{managerCLEditEstPag.modalTalleres}"/>
                                    <a4j:support event="onchange" reRender="iMatricula"
                                                 action="#{managerCLEditEstPag.parametrosMatricula}"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr style="height : 30px;">
                            <td>
                                <h:outputLabel value="Matriculas del alumno"/>
                            </td>
                            <td>
                                <h:selectOneMenu id="iMatricula" style="width: 270px;"
                                                 value="#{managerCLEditEstPag.modalMatricula}">
                                    <f:selectItems value="#{managerCLEditEstPag.modalMatriculas}"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr width="100%"/></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <center>
                                    <a4j:commandButton value="Generar estructuras"
                                                       actionListener="#{managerCLEditEstPag.validarEstructuras}"
                                                       oncomplete="#{managerCLEditEstPag.oncomplete}"/>
                                </center>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>

            <rich:modalPanel  id="mpCandado"
                              height="150" minWidth="300"
                              autosized="true" zindex="2000">
                <f:facet name="header">
                    <h:outputText value="Abrir/cerar candado" />
                </f:facet>
                <center>
                    <h:form id="fromCandado">
                        <h:outputLabel>
                            <h2>Esta seguro de <h:outputText id="mod_msj" value="#{managerCLEditEstPag.b_sub_msj}"/></h2>
                        </h:outputLabel>
                        <a4j:commandButton value="Aceptar" reRender="tableData"
                                           oncomplete="#{managerCLEditEstPag.oncomplete}"
                                           action="#{managerCLEditEstPag.actualizarEstado}"/>
                        <rich:spacer width="20px" />
                        <a4j:commandButton value="Cancelar" onclick="Richfaces.hideModalPanel('mpCandado')" />
                    </h:form>
                </center>
            </rich:modalPanel>

            <rich:modalPanel  id="mpEliminar" autosized="true"
                              height="130" width="300" zindex="2000">
                <f:facet name="header">
                    <h:outputText value="Eliminar" />
                </f:facet>
                <center>
                    <h:form id="fromEliminar">
                        <h:outputLabel>
                            <h2>Esta seguro de eliminar las estructuras generadas?</h2>
                        </h:outputLabel>
                        <a4j:commandButton value="Aceptar" reRender="tableData"
                                           oncomplete="#{managerCLEditEstPag.oncomplete}"
                                           action="#{managerCLEditEstPag.eliminarEstructura}"/>
                        <rich:spacer width="20px" />
                        <h:commandButton value="Cancelar" onclick="Richfaces.hideModalPanel('mpEliminar')" />
                    </h:form>
                </center>
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
                                        action="#{managerCLEditEstPag.editar}"
                                        reRender="tablaMaster, scrollTableMaster, subTable"
                                        oncomplete="#{managerCLEditEstPag.oncomplete}"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2"><hr width="100%"/></td>
                            </tr>
                            <tr style="height: 30px;">
                                <td><h:outputLabel styleClass="label-mpNuevoEstPagAlu" value="Monto:" /></td>
                                <td><h:inputText id="mod_monto" style="width : 180px;"
                                             value="#{managerCLEditEstPag.modal_monto}" />
                                </td>
                            </tr>
                            <tr style="height: 30px;">
                                <td><h:outputLabel styleClass="label-mpNuevoEstPagAlu" value="Fecha de pago:" /></td>
                                <td><rich:calendar id="mod_pago" datePattern="dd-MM-yyyy" style="width : 200px;"
                                               value="#{managerCLEditEstPag.modal_pago}"/>
                                </td>
                            </tr>
                            <tr style="height: 30px;">
                                <td><h:outputLabel styleClass="label-mpNuevoEstPagAlu" value="Fecha de Prorroga:" /></td>
                                <td><rich:calendar id="mod_prorroga" datePattern="dd-MM-yyyy" style="width : 200px;"
                                               value="#{managerCLEditEstPag.modal_prorroga}" />
                                </td>
                            </tr>
                        </table>
                    </h:form>
                </center>
            </rich:modalPanel>
        </f:view>
    </body>
</html>
