<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Asistencia docente</title>
        <script type="text/javascript">
            function printObjectsSelected_u(sgcomponent,idAL,nombreAL,codigoAL) {
                /*codigo original*/
                /*idAL.value=sgcomponent.getSelectedItems().pluck('b_doc_id');
                nombreAL.innerHTML=sgcomponent.getSelectedItems().pluck('b_doc_nombre');
                codigoAL.innerHTML=sgcomponent.getSelectedItems().pluck('b_doc_codigo');*/
                
                idAL.value = sgcomponent.getSelectedItems().pluck('id');
                nombreAL.innerHTML = sgcomponent.getSelectedItems().pluck('docNombre');
                codigoAL.innerHTML = sgcomponent.getSelectedItems().pluck('docCodigo');
            }
            /*function printObjectsSelected_ha(sgcomponent2,ha_doc,nombreAL2,codigoAL2) {
                ha_doc.value=sgcomponent2.getSelectedItems().pluck('b_doc_id');
                ha_doc.innerHTML=sgcomponent2.getSelectedItems().pluck('b_doc_id');
                nombreAL2.innerHTML=sgcomponent2.getSelectedItems().pluck('b_doc_nombre');
                codigoAL2.innerHTML=sgcomponent2.getSelectedItems().pluck('b_doc_codigo');
            }*/
        </script>
    </head>
    <f:view>
        <body>
            <jsp:include page="../TablaSistema/Menu.jsp"></jsp:include>
            <rich:panel style="width:100%;">
                <f:facet name="ASISTENCIA DOCENTE"></f:facet>
                <h:form id="form1" style="width:100%">
                    <table style="font-size:13px; font-family:verdana; width: 100%">
                        <tr>
                            <td width="100%" colspan="2" ><strong>MANTENIMIENTO ASISTENCIA</strong>
                            </td>
                            <td align="rigth">
                            </td>
                            <td>
                                <h:graphicImage value="/Imagenes/actions/filenew.png" style="cursor:pointer;" alt="Nueva Sesion" title="Nueva Sesion">
                                    <a4j:support  event="onclick"
                                                  oncomplete="Richfaces.showModalPanel('mpNuevaSesion')"
                                                  action="#{managerSEAsistenciaDocente.limpiar}"
                                                  reRender="form4, n_doc, n_fecha_ses,n_ho_reg, n_mi_reg, n_ho_sal,
                                                  n_mi_sal, n_tipasis, n_tipses, n_especialidad, n_seccion, n_curso"/>
                                </h:graphicImage>
                            </td>
                            
                            <td>
                                <h:graphicImage value="/Imagenes/actions/add_.gif" style="cursor:pointer;" alt="Hora adicional" title="Hora adicional">
                                    <a4j:support  event="onclick"
                                                  oncomplete="Richfaces.showModalPanel('mpHoraAdicional')"
                                                  action="#{managerSEAsistenciaDocente.limpiarAdicional}"
                                                  reRender="form5, ha_doc, ha_fecha_ses,ha_ho_reg, ha_mi_reg, ha_ho_sal,
                                                  ha_mi_sal, ha_tipasis, ha_tipses, ha_especialidad"/>
                                </h:graphicImage>
                                
                            </td>
                            
                            <td>
                                <a4j:commandButton image="/Imagenes/actions/viewmag.png" title="Buscar"
                                                   actionListener="#{managerSEAsistenciaDocente.mostrarSessionAsistencia}"
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
                    <h:inputText id="txt_dato" value="#{managerSEAsistenciaDocente.suggestion}"
                                 style="width: 300px; font-size: 12px;" maxlength="10"/>
                    <rich:suggestionbox id="suggestion"
                                        for="txt_dato" nothingLabel="Docente No Encontrado"
                                        suggestionAction="#{managerSEAsistenciaDocente.autocomplete}" var="asis"
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
                                               value="#{managerSEAsistenciaDocente.b_session_fecha_ini}"
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
                                               value="#{managerSEAsistenciaDocente.b_session_fecha_fin}"
                                               inputStyle="width: 120px; font-size: 12px;"
                                               showWeeksBar="false"
                                               datePattern="yyyy-MM-dd"/></td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td width="10%">Tipo de asistencia:</td>
                            <td>
                                <h:selectOneMenu value="#{managerSEAsistenciaDocente.b_tipasis_code}" style="width : 180px;">
                                    <f:selectItems value="#{managerSEAsistenciaDocente.b_tipasis}"/>
                                </h:selectOneMenu>
                            </td>
                            <td></td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td colspan="6">
                                <i><h:outputText id="codigoAL" value="#{managerSEAsistenciaDocente.w_doc_codigo}"/> - <h:outputText id="nombreAL" value="#{managerSEAsistenciaDocente.w_doc_nombre}"/></i>
                                <h:inputHidden id="idAL" value="#{managerSEAsistenciaDocente.w_doc_id}"/>
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
                                    value="#{managerSEAsistenciaDocente.b_listar_Sessiones}"
                                    var="ses" >
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Id" />
                            </f:facet>
                            <h:outputText value="#{ses.b_ses_id}" />
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Docente" />
                            </f:facet>
                            <h:outputText value="#{ses.doc_nombre}" />
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Fecha" />
                            </f:facet>
                            <h:outputText value="#{ses.b_fecha_registro}">
                                <f:convertDateTime pattern="EEEEE dd  'de'  MMMMMM  'del'  yyyy"/>
                            </h:outputText>
                        </rich:column>

                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Seccion" />
                            </f:facet>
                            <h:outputText value="#{ses.b_sec_nombre}" />
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Curso" />
                            </f:facet>
                            <h:outputText value="#{ses.b_cur_nombre}" />
                        </rich:column>

                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Hra. Inicio" />
                            </f:facet>
                            <h:outputText value="#{ses.b_ses_horaIni}" >
                                <f:convertDateTime pattern="hh:mm:ss a" />
                            </h:outputText>
                        </rich:column>

                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Hra. Fin" />
                            </f:facet>
                            <h:outputText value="#{ses.b_ses_horaFin}" >
                                <f:convertDateTime pattern="hh:mm:ss a" />
                            </h:outputText>
                            <f:param id="w_ses_id" value="#{ses.b_ses_id}"/>
                            <f:param id="w_doc_id" value="#{ses.b_doc_id}"/>
                            <f:param id="p_hora_inicio" value="#{ses.b_ses_horaIni}"/>
                            <f:param id="p_hora_fin" value="#{ses.b_ses_horaFin}"/>
                            <f:param id="p_cur_nombre" value="#{ses.b_cur_nombre}"/>
                            <f:param id="p_sec_nombre" value="#{ses.b_sec_nombre}" />
                        </rich:column>

                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Editar" />
                            </f:facet>
                            <a4j:commandButton image="/Imagenes/actions/editpaste.png"
                                               title="Editar session del docente"
                                               actionListener="#{managerSEAsistenciaDocente.seleccionarSesion}"
                                               oncomplete="#{managerSEAsistenciaDocente.oncomplete}"
                                               reRender="s_docente, s_cambio, s_curso, s_especialidad, s_seccion, s_inicio, s_fin,mpSessionAsis" />
                        </rich:column>

                        <rich:column style="text-align: center;">
                                            <f:facet name="header">
                                                <h:outputText value="Eliminar"/>
                                            </f:facet>
                                           <h:commandButton image="/Imagenes/actions/no.png" title="Eliminar"

                                                             actionListener="#{managerSEAsistenciaDocente.eliminarSesion}"

                                                   onclick="javascript:return (confirm('�Esta realmente seguro de Eliminar?'));"
/>
                                            <%--<a4j:commandButton image="/Imagenes/actions/no.png" title="Eliminar"
                                                   actionListener="#{managerSEAsistenciaDocente.eliminarSesion}"
                                                   oncomplete="#{managerSEAsistenciaDocente.oncomplete}"
                                                   reRender="fromTableAsisDoc, scrollTableAsisDoc, tableAsisDoc"
                                                   />--%>
                            <f:param id="delete"  value="#{ses.b_ses_id}" />
                                        </rich:column>

                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Marcar sesion" />
                            </f:facet>
                            <a4j:commandButton image="/Imagenes/actions/matricula.png"
                                               title="Marcar asistencia del docente"
                                               actionListener="#{managerSEAsistenciaDocente.seleccionarMarcaDocente}"
                                               oncomplete="#{managerSEAsistenciaDocente.oncomplete}"
                                               reRender="a_doc_nombre, a_sec_nombre, a_estasis, a_cur_nombre,a_fecha_reg,a_ho_reg, a_reg_lbl, a_sal_lbl, a_mi_reg, a_ho_sal, a_mi_sal, a_obs" />
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
                                                   oncomplete="#{managerSEAsistenciaDocente.oncomplete}"
                                                   actionListener="#{managerSEAsistenciaDocente.guardarMarcaAsistencia}"
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
                                <h:outputText value="#{managerSEAsistenciaDocente.asisDocente.doc_nombre}" id="a_doc_nombre"/>
                                <f:param id="p_ses_id" value="#{managerSEAsistenciaDocente.asisDocente.ses_id}"/>
                                <f:param id="p_doc_id" value="#{managerSEAsistenciaDocente.asisDocente.doc_id}"/>
                                <f:param id="p_sesefec_id" value="#{managerSEAsistenciaDocente.asisDocente.sesefeasisdoc_id}"/>
                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td>Estado de asistencia docente:</td>
                            <td>
                                <h:selectOneMenu value="#{managerSEAsistenciaDocente.asisDocente.estasis_code}" style="width : 220px;" id="a_estasis">
                                    <f:selectItems value="#{managerSEAsistenciaDocente.asisDocente.estasis}"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td><h:outputText value="Seccion" /></td>
                            <td>
                                <h:outputText value="#{managerSEAsistenciaDocente.asisDocente.sec_nombre}" id="a_sec_nombre"/>
                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td><h:outputText value="Curso" /></td>
                            <td>
                                <h:outputText value="#{managerSEAsistenciaDocente.asisDocente.cur_nombre}" id="a_cur_nombre"/>
                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td><h:outputText value="Fecha Registro" /></td>
                            <td>
                                <h:outputText value="#{managerSEAsistenciaDocente.asisDocente.sesefeasisdoc_fecha}" id="a_fecha_reg">
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
                                            <h:outputLabel id="a_reg_lbl" value="#{managerSEAsistenciaDocente.asisDocente.registro}">
                                                <f:convertDateTime pattern="HH:mm" />
                                            </h:outputLabel>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><rich:inputNumberSpinner id="a_ho_reg" value="#{managerSEAsistenciaDocente.asisDocente.reg_hora}"
                                                                 minValue="0" maxValue="23" inputStyle="width : 20px;" /></td>
                                        <td> : </td>
                                        <td><rich:inputNumberSpinner id="a_mi_reg" value="#{managerSEAsistenciaDocente.asisDocente.reg_min}"
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
                                            <h:outputLabel id="a_sal_lbl" value="#{managerSEAsistenciaDocente.asisDocente.salida}">
                                                <f:convertDateTime pattern="HH:mm" />
                                            </h:outputLabel>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><rich:inputNumberSpinner id="a_ho_sal" value="#{managerSEAsistenciaDocente.asisDocente.sal_hora}"
                                                                 minValue="0" maxValue="23" inputStyle="width : 20px;" /></td>
                                        <td> : </td>
                                        <td><rich:inputNumberSpinner id="a_mi_sal" value="#{managerSEAsistenciaDocente.asisDocente.sal_min}"
                                                                 minValue="0" maxValue="59" inputStyle="width : 20px;" /></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td><h:outputText value="Observaci�n" /></td>
                            <td colspan="2">
                                <h:inputTextarea id="a_obs" cols="20" rows="2"
                                                 value="#{managerSEAsistenciaDocente.asisDocente.sesefeasisdoc_obs}" />
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
                                <f:param id="p_ses_id" value="#{managerSEAsistenciaDocente.b_ses_id}"/>
                                <f:param id="p_cambio" value="#{managerSEAsistenciaDocente.s_doc_id}"/>
                                <f:param id="p_doc_ant" value="#{managerSEAsistenciaDocente.w_doc_id}"/>
                                <f:param id="p_inicio" value="#{managerSEAsistenciaDocente.b_hora_Ini_2}"/>
                                <f:param id="p_fin" value="#{managerSEAsistenciaDocente.b_hora_Fin_2}"/>

                                <a4j:commandButton image="/Imagenes/actions/filesave.png" title="Guardar"
                                                   actionListener="#{managerSEAsistenciaDocente.actualizarSesion}"
                                                   oncomplete="#{managerSEAsistenciaDocente.oncomplete}"
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
                                <h:outputText value="#{managerSEAsistenciaDocente.b_doc_nombre}" id="s_docente"/>
                                <h:outputText value="#{managerSEAsistenciaDocente.b_doc_id}" id="s_doc_ant" rendered="false"/>
                                <h:outputText value="#{managerSEAsistenciaDocente.b_ses_id}" id="s_ses_id" rendered="false"/>
                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td>
                                <h:outputText value="Docente Reemplazo"/>
                            </td>
                            <td>
                                <h:selectOneMenu id="s_cambio"  value="#{managerSEAsistenciaDocente.s_doc_id}"
                                                 style="width : 280px;" tabindex="11">
                                    <f:selectItems value="#{managerSEAsistenciaDocente.cboDocente}" />
                                </h:selectOneMenu>

                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td>
                                <h:outputText value="Especialidad"/>
                            </td>
                            <td>
                                <h:outputText id="s_especialidad" value="#{managerSEAsistenciaDocente.b_esp_nombre}" />
                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td>
                                <h:outputText value="Seccion"/>
                            </td>
                            <td>
                                <h:outputText id="s_seccion" value="#{managerSEAsistenciaDocente.b_sec_nombre}"/>
                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td>
                                <h:outputText value="Curso"/>
                            </td>
                            <td>
                                <h:outputText id="s_curso" value="#{managerSEAsistenciaDocente.b_cur_nombre}"/>
                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td>
                                <h:outputText value="F. Inicio"/>
                            </td>
                            <td>
                                <rich:calendar id="s_inicio" datePattern="dd-MM-yyyy hh:mm a" showApplyButton="true"
                                               value="#{managerSEAsistenciaDocente.b_hora_Ini_2}" />
                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td>
                                <h:outputText value="F. Fin"/>
                            </td>
                            <td>
                                <rich:calendar id="s_fin" datePattern="dd-MM-yyyy hh:mm a" showApplyButton="true"
                                               value="#{managerSEAsistenciaDocente.b_hora_Fin_2}" />
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
                                                   action="#{managerSEAsistenciaDocente.validarNuevaSesion}"
                                                   oncomplete="#{managerSEAsistenciaDocente.oncomplete}"/>
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
                                <h:selectOneMenu id="n_doc" value="#{managerSEAsistenciaDocente.nuevAsisDocente.n_doc_id}" style="width : 270px;">
                                    <f:selectItems value="#{managerSEAsistenciaDocente.nuevAsisDocente.n_doc}"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td>
                                <h:outputText value="Especialidad"/>
                            </td>
                            <td>
                                <h:selectOneMenu id="n_especialidad"  value="#{managerSEAsistenciaDocente.nuevAsisDocente.n_especialidad_code}" style="width : 380px;" tabindex="11">
                                    <f:selectItems value="#{managerSEAsistenciaDocente.nuevAsisDocente.n_especialidad}" />
                                    <a4j:support event="onchange"  reRender="n_seccion, n_curso" />
                                </h:selectOneMenu>

                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td>
                                <h:outputText value="Seccion"/>
                            </td>
                            <td>
                                <h:selectOneMenu id="n_seccion"  value="#{managerSEAsistenciaDocente.nuevAsisDocente.n_seccion_code}" style="width : 180px;" tabindex="11">
                                    <f:selectItems value="#{managerSEAsistenciaDocente.nuevAsisDocente.n_seccion}" />
                                    <a4j:support event="onchange"  reRender="n_curso" />
                                </h:selectOneMenu>

                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td>Curso:</td>
                            <td colspan="2">
                                <h:selectOneMenu id="n_curso"  value="#{managerSEAsistenciaDocente.nuevAsisDocente.n_curso_seccion}" style="width : 310px;" tabindex="11">
                                    <f:selectItems value="#{managerSEAsistenciaDocente.nuevAsisDocente.n_curso}" />
                                </h:selectOneMenu>

                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td>Fecha de la sesion</td>
                            <td>
                                <rich:calendar id="n_fecha_ses"
                                               value="#{managerSEAsistenciaDocente.nuevAsisDocente.n_fsesion}"
                                               showWeeksBar="false"
                                               datePattern="yyyy-MM-dd"/>
                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td>Fecha Inicio:</td>
                            <td>
                                <%--<rich:calendar id="n_fecha_ini"
                                               value="#{managerSEAsistenciaDocente.nuevAsisDocente.n_fsesion_ini}"
                                               showWeeksBar="false" showApplyButton="true"
                                               datePattern="yyyy-MM-dd HH:mm"/>--%>
                                <table>
                                    <tr>
                                        <td><rich:inputNumberSpinner id="n_ho_reg" value="#{managerSEAsistenciaDocente.nuevAsisDocente.reg_hora}"
                                                                 minValue="0" maxValue="23" inputStyle="width : 20px;" /></td>
                                        <td> : </td>
                                        <td><rich:inputNumberSpinner id="n_mi_reg" value="#{managerSEAsistenciaDocente.nuevAsisDocente.reg_min}"
                                                                 minValue="0" maxValue="59" inputStyle="width : 20px;" /></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td>Fecha Fin:</td>
                            <td>
                                <%--<rich:calendar id="n_fecha_fin"
                                               value="#{managerSEAsistenciaDocente.nuevAsisDocente.n_fsesion_fin}"
                                               showWeeksBar="false" showApplyButton="true"
                                               datePattern="yyyy-MM-dd HH:mm"/>--%>
                                <table>
                                    <tr>
                                        <td><rich:inputNumberSpinner id="n_ho_sal" value="#{managerSEAsistenciaDocente.nuevAsisDocente.sal_hora}"
                                                                 minValue="0" maxValue="23" inputStyle="width : 20px;" /></td>
                                        <td> : </td>
                                        <td><rich:inputNumberSpinner id="n_mi_sal" value="#{managerSEAsistenciaDocente.nuevAsisDocente.sal_min}"
                                                                 minValue="0" maxValue="59" inputStyle="width : 20px;" /></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td>Tipo de asistencia:</td>
                            <td>
                                <h:selectOneMenu id="n_tipasis" value="#{managerSEAsistenciaDocente.nuevAsisDocente.n_tipasis_code}" style="width : 180px;">
                                    <f:selectItems value="#{managerSEAsistenciaDocente.nuevAsisDocente.n_tipasis}"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td>Tipo de sesion:</td>
                            <td>
                                <h:selectOneMenu id="n_tipses" value="#{managerSEAsistenciaDocente.nuevAsisDocente.n_tipses_code}" style="width : 180px;">
                                    <f:selectItems value="#{managerSEAsistenciaDocente.nuevAsisDocente.n_tipses}"/>
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
                                                   action="#{managerSEAsistenciaDocente.validarHoraAdicional}"
                                                   oncomplete="#{managerSEAsistenciaDocente.oncomplete}"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <hr width="100%" size="1">
                            </td>
                        </tr>
                        
                        <%--<tr style=" height : 24px;">
                            <td width="10%" >Docente:</td>
                            <td>
                                <h:inputText id="txt_dato2" value="#{managerSEAsistenciaDocente.suggestionha}"
                                             style="width: 300px; font-size: 12px;" maxlength="10"/>
                                <rich:suggestionbox id="suggestion2"
                                                    for="txt_dato2" nothingLabel="Docente No Encontrado"
                                                    suggestionAction="#{managerSEAsistenciaDocente.autocompleteha}" var="asis2"
                                                    height="150" width="400" cellpadding="2"
                                                    cellspacing="2" shadowOpacity="0" shadowDepth="0"
                                                    minChars="3" rules="none" zindex="6500"
                                                    usingSuggestObjects="true"
                                                    onobjectchange="printObjectsSelected_ha(#{rich:component('suggestion2')},#{rich:element('ha_doc')},#{rich:element('codigoAL2')},#{rich:element('nombreAL2')});">
                                    <h:column>
                                        <h:outputText value="#{asis2.b_doc_nombre}" style="font-weight: bold;" />
                                    </h:column>
                                    <h:column>
                                        <h:outputText value="#{asis2.b_doc_codigo}" style="font-weight: bold;" />
                                    </h:column>
                                    <h:column>
                                        <h:outputText value="#{asis2.b_doc_id}" style="font-weight: bold;" />
                                    </h:column>
                                </rich:suggestionbox>
                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td colspan="6">
                                <i><h:outputText id="codigoAL2" value="#{managerSEAsistenciaDocente.w_doc_codigo}"/> - <h:outputText id="nombreAL2" value="#{managerSEAsistenciaDocente.w_doc_nombre}"/></i>
                                <h:inputHidden id="ha_doc" value="#{managerSEAsistenciaDocente.w_doc_id}"/>
                            </td>
                            <td></td>
                        </tr>--%>
                        
                        <tr style=" height : 24px;">
                            <td width="50%" >Docente:</td>
                            <td>
                                
                                <h:selectOneMenu id="ha_doc2" value="#{managerSEAsistenciaDocente.nuevaHoraAdicional.n_doc_id}" style="width : 270px;">
                                    <f:selectItems value="#{managerSEAsistenciaDocente.nuevaHoraAdicional.n_doc}"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td>
                                <h:outputText value="Especialidad"/>
                            </td>
                            <td>
                                <h:selectOneMenu id="ha_especialidad"  value="#{managerSEAsistenciaDocente.nuevaHoraAdicional.n_especialidad_code}" style="width : 380px;" tabindex="11">
                                    <f:selectItems value="#{managerSEAsistenciaDocente.nuevaHoraAdicional.n_especialidad}" />
                                    <a4j:support event="onchange"  reRender="ha_seccion, ha_curso" />
                                </h:selectOneMenu>

                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td>
                                <h:outputText value="Seccion"/>
                            </td>
                            <td>
                                <h:selectOneMenu id="ha_seccion"  value="#{managerSEAsistenciaDocente.nuevaHoraAdicional.n_seccion_code}" style="width : 180px;" tabindex="11">
                                    <f:selectItems value="#{managerSEAsistenciaDocente.nuevaHoraAdicional.n_seccion}" />
                                    <a4j:support event="onchange"  reRender="ha_curso" />
                                </h:selectOneMenu>

                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td>Curso:</td>
                            <td colspan="2">
                                <h:selectOneMenu id="ha_curso"  value="#{managerSEAsistenciaDocente.nuevaHoraAdicional.n_curso_seccion}" style="width : 310px;" tabindex="11">
                                    <f:selectItems value="#{managerSEAsistenciaDocente.nuevaHoraAdicional.n_curso}" />
                                </h:selectOneMenu>

                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td>Fecha de la sesion</td>
                            <td>
                                <rich:calendar id="ha_fecha_ses"
                                               value="#{managerSEAsistenciaDocente.nuevaHoraAdicional.n_fsesion}"
                                               showWeeksBar="false"
                                               datePattern="yyyy-MM-dd"/>
                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td>Fecha Inicio:</td>
                            <td>
                               
                                <table>
                                    <tr>
                                        <td><rich:inputNumberSpinner id="ha_ho_reg" value="#{managerSEAsistenciaDocente.nuevaHoraAdicional.reg_hora}"
                                                                 minValue="0" maxValue="23" inputStyle="width : 20px;" /></td>
                                        <td> : </td>
                                        <td><rich:inputNumberSpinner id="ha_mi_reg" value="#{managerSEAsistenciaDocente.nuevaHoraAdicional.reg_min}"
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
                                        <td><rich:inputNumberSpinner id="ha_ho_sal" value="#{managerSEAsistenciaDocente.nuevaHoraAdicional.sal_hora}"
                                                                 minValue="0" maxValue="23" inputStyle="width : 20px;" /></td>
                                        <td> : </td>
                                        <td><rich:inputNumberSpinner id="ha_mi_sal" value="#{managerSEAsistenciaDocente.nuevaHoraAdicional.sal_min}"
                                                                 minValue="0" maxValue="59" inputStyle="width : 20px;" /></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td>Tipo de asistencia:</td>
                            <td>
                                <h:selectOneMenu id="ha_tipasis" value="#{managerSEAsistenciaDocente.nuevaHoraAdicional.n_tipasis_code}" style="width : 180px;">
                                    <f:selectItems value="#{managerSEAsistenciaDocente.nuevaHoraAdicional.n_tipasis}"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td>Tipo de sesion:</td>
                            <td>
                                <h:selectOneMenu id="ha_tipses" value="#{managerSEAsistenciaDocente.nuevaHoraAdicional.n_tipses_code}" style="width : 180px;">
                                    <f:selectItems value="#{managerSEAsistenciaDocente.nuevaHoraAdicional.n_tipses}"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>
        </body>
    </f:view>
</html>
