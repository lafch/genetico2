<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Asistencia docente del Centro de Producci�n</title>
        <script type="text/javascript">
            function printObjectsSelected_u(sgcomponent, idAL, nombreAL, codigoAL) {
                idAL.value = sgcomponent.getSelectedItems().pluck('id');
                nombreAL.innerHTML = sgcomponent.getSelectedItems().pluck('docNombre');
                codigoAL.innerHTML = sgcomponent.getSelectedItems().pluck('docCodigo');
            }
        </script>
    </head>
    <f:view>
        <body>
            <jsp:include page="../TablaSistema/Menu.jsp"></jsp:include>
            <rich:panel style="width:100%;">
            <f:facet name="ASISTENCIA DOCENTE DEL CENTRO DE PRODUCCI�N"></f:facet>
            <h:form id="form1" style="width:100%">
                <table style="font-size:13px; font-family:verdana; width: 100%">
                    <tr>
                        <td width="100%" colspan="2" ><strong>ASISTENCIA DOCENTE DEL CENTRO DE PRODUCCI�N</strong>
                        </td>
                        <td align="rigth">
                    </td>
                    <td>
                    <h:graphicImage value="/Imagenes/actions/filenew.png" style="cursor:pointer;" title="Nueva Sesion">
                        <a4j:support  event="onclick"
                                      oncomplete="Richfaces.showModalPanel('mpNuevaSesion')"
                                      action="#{managerAsistenciaDocenteCL.limpiar}"
                                      reRender="form4, n_doc, n_fecha_ses,n_ho_reg, n_mi_reg, n_ho_sal,
                                      n_mi_sal, n_tipses, seCentro, seSecciones, seCursos"/>
                    </h:graphicImage>
                    </td>
                    <td>
                    <h:graphicImage value="/Imagenes/actions/add_.gif" style="cursor:pointer;" alt="Hora adicional" title="Hora adicional">
                        <a4j:support  event="onclick"
                                      oncomplete="Richfaces.showModalPanel('mpHoraAdicional')"
                                      action="#{managerSEAsistenciaDocente.limpiarAdicional}"
                                      reRender="form5, ha_doc, ha_fecha_ses,ha_ho_reg, ha_mi_reg, ha_ho_sal,
                                      ha_mi_sal, ha_tipasis, haCentro, haSecciones, haCursos"/>
                    </h:graphicImage>

                    </td>
                    <td>
                    <a4j:commandButton image="/Imagenes/actions/viewmag.png" 
                                       actionListener="#{managerAsistenciaDocenteCL.mostrarSessionAsistencia}"
                                       reRender="fromTableAsisDoc, scrollTableAsisDoc, tableAsisDoc" />
                    </td>
                    </tr>
                    <tr>
                        <td colspan="6" width="100%">
                            <hr width="100%" size="1"></td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td width="10%" >Docente:</td>
                        <td>
                    <h:inputText id="txt_dato" value="#{managerAsistenciaDocenteCL.suggestion}"
                                 style="width: 300px; font-size: 12px;" maxlength="10"/>
                    <rich:suggestionbox id="suggestion"
                                        for="txt_dato" nothingLabel="Docente No Encontrado"
                                        suggestionAction="#{managerAsistenciaDocenteCL.autocomplete}" var="asis"
                                        height="150" width="400" cellpadding="2"
                                        cellspacing="2" shadowOpacity="4" shadowDepth="4"
                                        minChars="3" rules="none" zindex="3500"
                                        usingSuggestObjects="true"
                                        onobjectchange="printObjectsSelected_u(#{rich:component('suggestion')},#{rich:element('idAL')},#{rich:element('codigoAL')},#{rich:element('nombreAL')});">
                        <h:column>
                            <h:outputText value="#{asis.docNombre}" style="font-weight: bold;" />
                        </h:column>
                        <h:column>
                            <h:outputText value="#{asis.docCodigo}" style="font-weight: bold;" />
                        </h:column>
                    </rich:suggestionbox>
                    </td>
                    </tr>
                    
                    <tr style=" height : 24px;">
                        <td width="10%">Fecha de inicio:</td>
                        <td>
                            <rich:calendar id="b_fecha_ini"
                                           value="#{managerAsistenciaDocenteCL.fechaIni}"
                                           inputStyle="width: 120px; font-size: 12px;"
                                           showWeeksBar="false"
                                           datePattern="yyyy-MM-dd"/>
                        </td>
                        <td></td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td width="10%">Fecha fin:</td>
                        <td>
                            <rich:calendar id="b_fecha_fin"
                                           value="#{managerAsistenciaDocenteCL.fechaFin}"
                                           inputStyle="width: 120px; font-size: 12px;"
                                           showWeeksBar="false"
                                           datePattern="yyyy-MM-dd"/></td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td colspan="6">
                            <i><h:outputText id="codigoAL" value="#{managerAsistenciaDocenteCL.docCodigo}"/> - <h:outputText id="nombreAL" value="#{managerAsistenciaDocenteCL.docNombre}"/></i>
                    <h:inputHidden id="idAL" value="#{managerAsistenciaDocenteCL.docId}"/>
                    </td>
                    <td></td>
                    </tr>
                    <tr>
                        <td colspan="6" width="100%"><hr width="100%" size="1"></td>
                    </tr>
                </table>
            </h:form>
            <h:form id="fromTableAsisDoc" style="width: 100%">
                <table style="width: 100%">
                    <tr>
                        <td align="right">
                            <rich:datascroller id="scrollTableAsisDoc" for="tableAsisDoc"
                                               align="rigth" maxPages="20"/>
                        </td>
                    </tr>
                </table>
                <rich:dataTable id="tableAsisDoc" rows="10"
                                onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                cellpadding="0" cellspacing="0"
                                width="100%" border="0"
                                value="#{managerAsistenciaDocenteCL.lstSesiones}"
                                var="ses" >
                    <rich:column>
                        <f:facet name="header">
                            <h:outputText value="Id" />
                        </f:facet>
                        <h:outputText value="#{ses.expr1}" />
                    </rich:column>

                    <rich:column>
                        <f:facet name="header">
                            <h:outputText value="Docente" />
                        </f:facet>
                        <h:outputText value="#{ses.expr2}" />
                    </rich:column>
                    <rich:column>
                        <f:facet name="header">
                            <h:outputText value="Fecha" />
                        </f:facet>
                        <h:outputText value="#{ses.expr3}" />
                    </rich:column>

                    <rich:column style="text-align: center;">
                        <f:facet name="header">
                            <h:outputText value="Seccion" />
                        </f:facet>
                        <h:outputText value="#{ses.expr4}" />
                    </rich:column>

                    <rich:column>
                        <f:facet name="header">
                            <h:outputText value="Curso" />
                        </f:facet>
                        <h:outputText value="#{ses.expr5}" />
                    </rich:column>

                    <rich:column style="text-align: center;">
                        <f:facet name="header">
                            <h:outputText value="Hra. Inicio" />
                        </f:facet>
                        <h:outputText value="#{ses.expr6}" >
                            <f:convertDateTime pattern="hh:mm:ss a" />
                        </h:outputText>
                    </rich:column>

                    <rich:column style="text-align: center;">
                        <f:facet name="header">
                            <h:outputText value="Hra. Fin" />
                        </f:facet>
                        <h:outputText value="#{ses.expr7}" >
                            <f:convertDateTime pattern="hh:mm:ss a" />
                        </h:outputText>
                        <f:param id="w_ses_id" value="#{ses.expr1}"/>
                        <f:param id="w_doc_id" value="#{ses.expr8}"/>
                        <f:param id="p_hora_inicio" value="#{ses.expr6}"/>
                        <f:param id="p_hora_fin" value="#{ses.expr7}"/>
                        <f:param id="p_cur_nombre" value="#{ses.expr5}"/>
                        <f:param id="p_sec_nombre" value="#{ses.expr4}" />
                    </rich:column>

                    <rich:column style="text-align: center;">
                        <f:facet name="header">
                            <h:outputText value="Editar" />
                        </f:facet>
                        <a4j:commandButton image="/Imagenes/actions/editpaste.png"
                                           title="Editar session del docente"
                                           actionListener="#{managerAsistenciaDocenteCL.seleccionarSesion}"
                                           oncomplete="#{managerAsistenciaDocenteCL.oncomplete}"
                                           reRender="s_docente, s_cambio, s_curso, s_especialidad, s_seccion, s_inicio, s_fin,mpSessionAsis" />
                    </rich:column>

                    <rich:column style="text-align: center;">
                        <f:facet name="header">
                            <h:outputText value="Eliminar"/>
                        </f:facet>
                        <h:commandButton image="/Imagenes/actions/no.png" title="Eliminar"

                                         actionListener="#{managerAsistenciaDocenteCL.eliminarSesion}"

                                         onclick="javascript:return (confirm('�Esta realmente seguro de Eliminar?'));"
                                         />
                        <%--<a4j:commandButton image="/Imagenes/actions/no.png" title="Eliminar"
                               actionListener="#{managerAsistenciaDocenteCL.eliminarSesion}"
                               oncomplete="#{managerAsistenciaDocenteCL.oncomplete}"
                               reRender="fromTableAsisDoc, scrollTableAsisDoc, tableAsisDoc"
                               />--%>
                        <f:param id="delete"  value="#{ses.expr1}" />
                    </rich:column>

                    <rich:column style="text-align: center;">
                        <f:facet name="header">
                            <h:outputText value="Marcar sesion" />
                        </f:facet>
                        <a4j:commandButton image="/Imagenes/actions/matricula.png"
                                           title="Marcar asistencia del docente"
                                           actionListener="#{managerAsistenciaDocenteCL.seleccionarMarcaDocente}"
                                           oncomplete="#{managerAsistenciaDocenteCL.oncomplete}"
                                           reRender="a_doc_nombre, a_sec_nombre, a_estasis, a_cur_nombre,a_fecha_reg,a_ho_reg, a_reg_lbl, a_sal_lbl, a_mi_reg, a_ho_sal, a_mi_sal, a_obs" rendered="#{managerUsuario.valida}"/>
                    </rich:column>
                </rich:dataTable>
            </h:form>
        </rich:panel>
        <rich:modalPanel id="mpAsisDocente" zindex="4000"
                         autosized="true" minWidth="400">
            <f:facet name="header">
                <h:outputText value="Asistencia Docente"/>
            </f:facet>
            <f:facet name="controls">
                <h:panelGroup>
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('mpAsisDocente')" title="Cerrar"/>
                </h:panelGroup>
            </f:facet>
            <h:form id="form2">
                <table width="100%">
                    <tr>
                        <td align=right colspan="2">
                    <a4j:commandButton image="/Imagenes/actions/filesave.png" title="Guardar"
                                       oncomplete="#{managerAsistenciaDocenteCL.oncomplete}"
                                       actionListener="#{managerAsistenciaDocenteCL.guardarMarcaAsistencia}"
                                       reRender="fromTableAsisDoc, scrollTableAsisDoc, tableAsisDoc"/>
                    </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr width="100%" size="1">
                        </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>Docente:</td>
                        <td>
                    <h:outputText value="#{managerAsistenciaDocenteCL.asisDocente.doc_nombre}" id="a_doc_nombre"/>
                    <f:param id="p_ses_id" value="#{managerAsistenciaDocenteCL.asisDocente.ses_id}"/>
                    <f:param id="p_doc_id" value="#{managerAsistenciaDocenteCL.asisDocente.doc_id}"/>
                    <f:param id="p_sesefec_id" value="#{managerAsistenciaDocenteCL.asisDocente.sesefeasisdoc_id}"/>
                    </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>Estado de asistencia docente:</td>
                        <td>
                    <h:selectOneMenu value="#{managerAsistenciaDocenteCL.asisDocente.estasis_code}" style="width : 220px;" id="a_estasis">
                        <f:selectItems value="#{managerAsistenciaDocenteCL.asisDocente.estasis}"/>
                    </h:selectOneMenu>
                    </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td><h:outputText value="Seccion" /></td>
                    <td>
                    <h:outputText value="#{managerAsistenciaDocenteCL.asisDocente.sec_nombre}" id="a_sec_nombre"/>
                    </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td><h:outputText value="Curso" /></td>
                    <td>
                    <h:outputText value="#{managerAsistenciaDocenteCL.asisDocente.cur_nombre}" id="a_cur_nombre"/>
                    </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td><h:outputText value="Fecha Registro" /></td>
                    <td>
                    <h:outputText value="#{managerAsistenciaDocenteCL.asisDocente.sesefeasisdoc_fecha}" id="a_fecha_reg">
                        <f:convertDateTime pattern="dd  'de'  MMMMMM  'del'  yyyy" />
                    </h:outputText>
                    </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td><h:outputText value="Hora Inicio" /></td>
                    <td>
                        <table>
                            <tr>
                                <td colspan="3" style="font-size: 12px; font-weight: bold;">
                            <h:outputLabel id="a_reg_lbl" value="#{managerAsistenciaDocenteCL.asisDocente.registro}">
                                <f:convertDateTime pattern="HH:mm" />
                            </h:outputLabel>
                    </td>
                    </tr>
                    <tr>
                        <td><rich:inputNumberSpinner id="a_ho_reg" value="#{managerAsistenciaDocenteCL.asisDocente.reg_hora}"
                                                 minValue="0" maxValue="23" inputStyle="width : 20px;" /></td>
                        <td> : </td>
                        <td><rich:inputNumberSpinner id="a_mi_reg" value="#{managerAsistenciaDocenteCL.asisDocente.reg_min}"
                                                 minValue="0" maxValue="59" inputStyle="width : 20px;" /></td>
                    </tr>
                </table>
                </td>
                </tr>
                <tr style=" height : 24px;">
                    <td><h:outputText value="Hora Fin" /></td>
                <td>
                    <table>
                        <tr>
                            <td colspan="3" style="font-size: 12px; font-weight: bold;">
                        <h:outputLabel id="a_sal_lbl" value="#{managerAsistenciaDocenteCL.asisDocente.salida}">
                            <f:convertDateTime pattern="HH:mm" />
                        </h:outputLabel>
                </td>
                </tr>
                <tr>
                    <td><rich:inputNumberSpinner id="a_ho_sal" value="#{managerAsistenciaDocenteCL.asisDocente.sal_hora}"
                                             minValue="0" maxValue="23" inputStyle="width : 20px;" /></td>
                    <td> : </td>
                    <td><rich:inputNumberSpinner id="a_mi_sal" value="#{managerAsistenciaDocenteCL.asisDocente.sal_min}"
                                             minValue="0" maxValue="59" inputStyle="width : 20px;" /></td>
                </tr>
                </table>
                </td>
                </tr>
                <tr style=" height : 24px;">
                    <td><h:outputText value="Observaci�n" /></td>
                <td colspan="2">
                <h:inputTextarea id="a_obs" cols="20" rows="2"
                                 value="#{managerAsistenciaDocenteCL.asisDocente.sesefeasisdoc_obs}" />
                </td>
                </tr>
                </table>
            </h:form>
        </rich:modalPanel>
        <rich:modalPanel id="mpSessionAsis" zindex="4000"
                         autosized="true" minWidth="400">
            <f:facet name="header">
                <h:outputText value="Actualizar sesion"/>
            </f:facet>
            <f:facet name="controls">
                <h:panelGroup>
                    <h:graphicImage value="/Imagenes/actions/stop.png"
                                    style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('mpSessionAsis')" title="Cerrar"/>
                </h:panelGroup>
            </f:facet>
            <h:form id="form3">
                <table width="100%">
                    <tr>
                        <td align=right colspan="2">
                    <f:param id="p_ses_id" value="#{managerAsistenciaDocenteCL.sesId}"/>
                    <f:param id="p_cambio" value="#{managerAsistenciaDocenteCL.s_doc_id}"/>
                    <f:param id="p_doc_ant" value="#{managerAsistenciaDocenteCL.w_doc_id}"/>
                    <f:param id="p_inicio" value="#{managerAsistenciaDocenteCL.horaIni2}"/>
                    <f:param id="p_fin" value="#{managerAsistenciaDocenteCL.horaFin2}"/>

                    <a4j:commandButton image="/Imagenes/actions/filesave.png" title="Guardar"
                                       actionListener="#{managerAsistenciaDocenteCL.actualizarSesion}"
                                       oncomplete="#{managerAsistenciaDocenteCL.oncomplete}"
                                       reRender="fromTableAsisDoc, scrollTableAsisDoc, tableAsisDoc"/>
                    </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr width="100%" size="1">
                        </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>
                    <h:outputText value="Docente"/>
                    </td>
                    <td>
                    <h:outputText value="#{managerAsistenciaDocenteCL.b_doc_nombre}" id="s_docente"/>
                    <h:outputText value="#{managerAsistenciaDocenteCL.b_doc_id}" id="s_doc_ant" rendered="false"/>
                    <h:outputText value="#{managerAsistenciaDocenteCL.sesId}" id="s_ses_id" rendered="false"/>
                    </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>
                    <h:outputText value="Docente Reemplazo"/>
                    </td>
                    <td>
                    <h:selectOneMenu id="s_cambio"  value="#{managerAsistenciaDocenteCL.s_doc_id}"
                                     style="width : 280px;" tabindex="11">
                        <f:selectItems value="#{managerAsistenciaDocenteCL.cboDocente}" />
                    </h:selectOneMenu>

                    </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>
                    <h:outputText value="Especialidad"/>
                    </td>
                    <td>
                    <h:outputText id="s_especialidad" value="#{managerAsistenciaDocenteCL.espNombre}" />
                    </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>
                    <h:outputText value="Seccion"/>
                    </td>
                    <td>
                    <h:outputText id="s_seccion" value="#{managerAsistenciaDocenteCL.secNombre}"/>
                    </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>
                    <h:outputText value="Curso"/>
                    </td>
                    <td>
                    <h:outputText id="s_curso" value="#{managerAsistenciaDocenteCL.curNombre}"/>
                    </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>
                    <h:outputText value="F. Inicio"/>
                    </td>
                    <td>
                        <rich:calendar id="s_inicio" datePattern="dd-MM-yyyy hh:mm a" showApplyButton="true"
                                       value="#{managerAsistenciaDocenteCL.horaIni2}" />
                    </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>
                    <h:outputText value="F. Fin"/>
                    </td>
                    <td>
                        <rich:calendar id="s_fin" datePattern="dd-MM-yyyy hh:mm a" showApplyButton="true"
                                       value="#{managerAsistenciaDocenteCL.horaFin2}" />
                    </td>
                    </tr>
                </table>
            </h:form>
        </rich:modalPanel>

        <rich:modalPanel id="mpNuevaSesion" zindex="4000"
                         autosized="true" minWidth="500">
            <f:facet name="header">
                <h:outputText value="Nueva sesion"/>
            </f:facet>
            <f:facet name="controls">
                <h:panelGroup>
                    <h:graphicImage value="/Imagenes/actions/stop.png"
                                    style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('mpNuevaSesion')" title="Cerrar"/>
                </h:panelGroup>
            </f:facet>
            <h:form id="form4">
                <table width="100%">
                    <tr>
                        <td align=right colspan="2">
                    <a4j:commandButton image="/Imagenes/actions/filesave.png" title="Guardar"
                                       action="#{managerAsistenciaDocenteCL.validarNuevaSesion}"
                                       oncomplete="#{managerAsistenciaDocenteCL.oncomplete}"/>
                    </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr width="100%" size="1">
                        </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td width="50%" >Docente:</td>
                        <td>
                    <h:selectOneMenu id="n_doc" value="#{managerAsistenciaDocenteCL.nuevAsisDocente.n_doc_id}" style="width : 270px;">
                        <f:selectItems value="#{managerAsistenciaDocenteCL.nuevAsisDocente.n_doc}"/>
                    </h:selectOneMenu>
                    </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>
                    <h:outputText value="Centro"/>
                    </td>
                    <td>
                    <h:selectOneMenu id="seCentro"  value="#{managerAsistenciaDocenteCL.nuevAsisDocente.centro}" style="width : 380px;" tabindex="11">
                        <f:selectItems value="#{managerAsistenciaDocenteCL.nuevAsisDocente.cboCentros}" />
                        <a4j:support event="onchange"  reRender="form4" />
                    </h:selectOneMenu>

                    </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>
                    <h:outputText value="Area"/>
                    </td>
                    <td>
                    <h:selectOneMenu id="seAreas"  value="#{managerAsistenciaDocenteCL.nuevAsisDocente.idArea}" style="width : 380px;" tabindex="11">
                        <f:selectItems value="#{managerAsistenciaDocenteCL.nuevAsisDocente.cboAreas}" />
                        <a4j:support event="onchange"  reRender="form4" />
                    </h:selectOneMenu>

                    </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>
                    <h:outputText value="M�dulos"/>
                    </td>
                    <td>
                    <h:selectOneMenu id="seModulos"  value="#{managerAsistenciaDocenteCL.nuevAsisDocente.idMod}" style="width : 380px;" tabindex="11">
                        <f:selectItems value="#{managerAsistenciaDocenteCL.nuevAsisDocente.cboModulos}" />
                        <a4j:support event="onchange"  reRender="form4" />
                    </h:selectOneMenu>

                    </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>
                    <h:outputText value="Cursos"/>
                    </td>
                    <td>
                    <h:selectOneMenu id="seCursos"  value="#{managerAsistenciaDocenteCL.nuevAsisDocente.idCur}" style="width : 380px;" tabindex="11">
                        <f:selectItems value="#{managerAsistenciaDocenteCL.nuevAsisDocente.cboCursos}" />
                        <a4j:support event="onchange"  reRender="form4" />
                    </h:selectOneMenu>

                    </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>
                    <h:outputText value="Seccion"/>
                    </td>
                    <td>
                    <h:selectOneMenu id="seSecciones"  value="#{managerAsistenciaDocenteCL.nuevAsisDocente.idSec}" style="width : 180px;" tabindex="11">
                        <f:selectItems value="#{managerAsistenciaDocenteCL.nuevAsisDocente.cboSecciones}" />
                        <a4j:support event="onchange"  reRender="form4" />
                    </h:selectOneMenu>

                    </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>Fecha de la sesion</td>
                        <td>
                            <rich:calendar id="n_fecha_ses"
                                           value="#{managerAsistenciaDocenteCL.nuevAsisDocente.n_fsesion}"
                                           showWeeksBar="false"
                                           datePattern="yyyy-MM-dd"/>
                        </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>Hora de Inicio:</td>
                        <td>
                            <%--<rich:calendar id="n_fecha_ini"
                                           value="#{managerAsistenciaDocenteCL.nuevAsisDocente.n_fsesion_ini}"
                                           showWeeksBar="false" showApplyButton="true"
                                           datePattern="yyyy-MM-dd HH:mm"/>--%>
                            <table>
                                <tr>
                                    <td><rich:inputNumberSpinner id="n_ho_reg" value="#{managerAsistenciaDocenteCL.nuevAsisDocente.reg_hora}"
                                                             minValue="0" maxValue="23" inputStyle="width : 20px;" /></td>
                                    <td> : </td>
                                    <td><rich:inputNumberSpinner id="n_mi_reg" value="#{managerAsistenciaDocenteCL.nuevAsisDocente.reg_min}"
                                                             minValue="0" maxValue="59" inputStyle="width : 20px;" /></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>Hora de Fin:</td>
                        <td>
                            <%--<rich:calendar id="n_fecha_fin"
                                           value="#{managerAsistenciaDocenteCL.nuevAsisDocente.n_fsesion_fin}"
                                           showWeeksBar="false" showApplyButton="true"
                                           datePattern="yyyy-MM-dd HH:mm"/>--%>
                            <table>
                                <tr>
                                    <td><rich:inputNumberSpinner id="n_ho_sal" value="#{managerAsistenciaDocenteCL.nuevAsisDocente.sal_hora}"
                                                             minValue="0" maxValue="23" inputStyle="width : 20px;" /></td>
                                    <td> : </td>
                                    <td><rich:inputNumberSpinner id="n_mi_sal" value="#{managerAsistenciaDocenteCL.nuevAsisDocente.sal_min}"
                                                             minValue="0" maxValue="59" inputStyle="width : 20px;" /></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>Tipo de sesion:</td>
                        <td>
                    <h:selectOneMenu id="n_tipses" value="#{managerAsistenciaDocenteCL.nuevAsisDocente.n_tipses_code}" style="width : 180px;">
                        <f:selectItems value="#{managerAsistenciaDocenteCL.nuevAsisDocente.n_tipses}"/>
                    </h:selectOneMenu>
                    </td>
                    </tr>
                </table>
            </h:form>
        </rich:modalPanel>
        <rich:modalPanel id="mpHoraAdicional" zindex="4000"
                         autosized="true" minWidth="500">
            <f:facet name="header">
                <h:outputText value="Ingreso Hora Adicional"/>
            </f:facet>
            <f:facet name="controls">
                <h:panelGroup>
                    <h:graphicImage value="/Imagenes/actions/stop.png"
                                    style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('mpHoraAdicional')" title="Cerrar"/>
                </h:panelGroup>
            </f:facet>
            <h:form id="form5">
                <table width="100%">
                    <tr>
                        <td align=right colspan="2">
                    <a4j:commandButton image="/Imagenes/actions/filesave.png" title="Guardar"
                                       action="#{managerAsistenciaDocenteCL.validarHoraAdicional}"
                                       oncomplete="#{managerAsistenciaDocenteCL.oncomplete}"/>
                    </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr width="100%" size="1">
                        </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td width="50%" >Docente:</td>
                        <td>
                    <h:selectOneMenu id="ha_doc" value="#{managerAsistenciaDocenteCL.nuevaHoraAdicional.n_doc_id}" style="width : 270px;">
                        <f:selectItems value="#{managerAsistenciaDocenteCL.nuevaHoraAdicional.n_doc}"/>
                    </h:selectOneMenu>
                    </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>
                    <h:outputText value="Centro"/>
                    </td>
                    <td>
                    <h:selectOneMenu id="haCentro"  value="#{managerAsistenciaDocenteCL.nuevaHoraAdicional.centro}" style="width : 380px;" tabindex="11">
                        <f:selectItems value="#{managerAsistenciaDocenteCL.nuevaHoraAdicional.cboCentros}" />
                        <a4j:support event="onchange"  reRender="form5" />
                    </h:selectOneMenu>

                    </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>
                    <h:outputText value="Area"/>
                    </td>
                    <td>
                    <h:selectOneMenu id="haAreas"  value="#{managerAsistenciaDocenteCL.nuevaHoraAdicional.idArea}" style="width : 380px;" tabindex="11">
                        <f:selectItems value="#{managerAsistenciaDocenteCL.nuevaHoraAdicional.cboAreas}" />
                        <a4j:support event="onchange"  reRender="form5" />
                    </h:selectOneMenu>

                    </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>
                    <h:outputText value="M�dulos"/>
                    </td>
                    <td>
                    <h:selectOneMenu id="haModulos"  value="#{managerAsistenciaDocenteCL.nuevaHoraAdicional.idMod}" style="width : 380px;" tabindex="11">
                        <f:selectItems value="#{managerAsistenciaDocenteCL.nuevaHoraAdicional.cboModulos}" />
                        <a4j:support event="onchange"  reRender="form5" />
                    </h:selectOneMenu>

                    </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>
                    <h:outputText value="Cursos"/>
                    </td>
                    <td>
                    <h:selectOneMenu id="haCursos"  value="#{managerAsistenciaDocenteCL.nuevaHoraAdicional.idCur}" style="width : 380px;" tabindex="11">
                        <f:selectItems value="#{managerAsistenciaDocenteCL.nuevaHoraAdicional.cboCursos}" />
                        <a4j:support event="onchange"  reRender="form5" />
                    </h:selectOneMenu>

                    </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>
                    <h:outputText value="Seccion"/>
                    </td>
                    <td>
                    <h:selectOneMenu id="haSecciones"  value="#{managerAsistenciaDocenteCL.nuevaHoraAdicional.idSec}" style="width : 180px;" tabindex="11">
                        <f:selectItems value="#{managerAsistenciaDocenteCL.nuevaHoraAdicional.cboSecciones}" />
                        <a4j:support event="onchange"  reRender="form5" />
                    </h:selectOneMenu>

                    </td>
                    <tr style=" height : 24px;">
                        <td>Fecha de la sesion</td>
                        <td>
                            <rich:calendar id="ha_fecha_ses"
                                           value="#{managerAsistenciaDocenteCL.nuevaHoraAdicional.n_fsesion}"
                                           showWeeksBar="false"
                                           datePattern="yyyy-MM-dd"/>
                        </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>Fecha Inicio:</td>
                        <td>

                            <table>
                                <tr>
                                    <td><rich:inputNumberSpinner id="ha_ho_reg" value="#{managerAsistenciaDocenteCL.nuevaHoraAdicional.reg_hora}"
                                                             minValue="0" maxValue="23" inputStyle="width : 20px;" /></td>
                                    <td> : </td>
                                    <td><rich:inputNumberSpinner id="ha_mi_reg" value="#{managerAsistenciaDocenteCL.nuevaHoraAdicional.reg_min}"
                                                             minValue="0" maxValue="59" inputStyle="width : 20px;" /></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>Fecha Fin:</td>
                        <td>

                            <table>
                                <tr>
                                    <td><rich:inputNumberSpinner id="ha_ho_sal" value="#{managerAsistenciaDocenteCL.nuevaHoraAdicional.sal_hora}"
                                                             minValue="0" maxValue="23" inputStyle="width : 20px;" /></td>
                                    <td> : </td>
                                    <td><rich:inputNumberSpinner id="ha_mi_sal" value="#{managerAsistenciaDocenteCL.nuevaHoraAdicional.sal_min}"
                                                             minValue="0" maxValue="59" inputStyle="width : 20px;" /></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td>Tipo de sesion:</td>
                        <td>
                    <h:selectOneMenu id="ha_tipses" value="#{managerAsistenciaDocenteCL.nuevaHoraAdicional.n_tipses_code}" style="width : 180px;">
                        <f:selectItems value="#{managerAsistenciaDocenteCL.nuevaHoraAdicional.n_tipses}"/>
                    </h:selectOneMenu>
                    </td>
                    </tr>
                </table>
            </h:form>
        </rich:modalPanel>
    </body>
</f:view>
</html>
