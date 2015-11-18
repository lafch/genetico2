<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Asistencia docente del Centro de Producción</title>
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
            <f:facet name="ASISTENCIA DOCENTE DEL CENTRO DE PRODUCCIÓN"></f:facet>
            <h:form id="form1" style="width:100%">
                <table style="font-size:13px; font-family:verdana; width: 100%">
                    <tr>
                        <td width="100%" colspan="2" ><strong>ASISTENCIA DOCENTE DEL CENTRO DE PRODUCCIÓN</strong>
                        </td>
                        <td align="rigth">

                    </td>
                    
                    <td>
                    <a4j:commandButton image="/Imagenes/actions/viewmag.png" 
                                       actionListener="#{managerAsistenciaDocenteCL.mostrarAsistenciaDiaria}"
                                       reRender="fromTableAsisDoc, scrollTableAsisDoc, tableAsisDoc,codigoAL,idAL,nombreAL,txt_dato,suggestion,seCentro" />
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
                                                    suggestionAction="#{managerAsistenciaDocenteCL.autocomplete2}" var="asis"
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
                        <td>
                    <h:outputText value="Centro"/>
                    </td>
                    <td>
                   
                    <h:selectOneMenu id="seCentro"  value="#{managerAsistenciaDocenteCL.asisDocenteCL.centro}" style="width : 380px;" tabindex="11">
                        <f:selectItems value="#{managerAsistenciaDocenteCL.asisDocenteCL.cboCentros}" />
                    </h:selectOneMenu>

                    </td>
                    </tr>
                            
                    <tr style=" height : 24px;">
                            <td colspan="6">
                                <i><h:outputText id="codigoAL" value="#{managerAsistenciaDocenteCL.w_doc_codigo}"/> - <h:outputText id="nombreAL" value="#{managerAsistenciaDocenteCL.w_doc_nombre}"/></i>
                                <h:inputHidden id="idAL" value="#{managerAsistenciaDocenteCL.w_doc_id}"/>
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
                                value="#{managerAsistenciaDocenteCL.b_listar_Sessiones}"
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
                        <h:outputText value="#{ses.b_doc_nombre}" />
                    </rich:column>
                    
                    <rich:column style="text-align: center;">
                        <f:facet name="header">
                            <h:outputText value="Centro" />
                        </f:facet>
                        <h:outputText value="#{ses.b_centro}" />
                    </rich:column>
                    
                    <rich:column style="text-align: center;">
                        <f:facet name="header">
                            <h:outputText value="Seccion" />
                        </f:facet>
                        <h:outputText value="#{ses.b_seccion}" />
                    </rich:column>

                    <rich:column>
                        <f:facet name="header">
                            <h:outputText value="Curso" />
                        </f:facet>
                        <h:outputText value="#{ses.b_curso}" />
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
                            <f:param id="p_cur_nombre" value="#{ses.b_curso}"/>
                            <f:param id="p_sec_nombre" value="#{ses.b_seccion}" />
                    </rich:column>
                    
                    <rich:column style="text-align: center;">
                        <f:facet name="header">
                            <h:outputText value="Hra. Ingreso" />
                        </f:facet>
                        <h:outputText value="#{ses.b_ses_horaIngreso}" >
                            <f:convertDateTime pattern="hh:mm:ss a" />
                        </h:outputText>
                    </rich:column>
                    
                    <rich:column style="text-align: center;">
                        <f:facet name="header">
                            <h:outputText value="Hra. salida" />
                        </f:facet>
                        <h:outputText value="#{ses.b_ses_horaSalida}" >
                            <f:convertDateTime pattern="hh:mm:ss a" />
                        </h:outputText>
                    </rich:column>
                    
                    <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Observación" />
                            </f:facet>
                            <a4j:commandButton image="/Imagenes/actions/matricula.png"
                                               title="Ingresar Observacion" rendered="#{ses.b_visualizar}"
                                               actionListener="#{managerAsistenciaDocenteCL.seleccionarMarcaObservacionDocente}"
                                               oncomplete="#{managerAsistenciaDocenteCL.oncompleteADD}"
                                               reRender="a_doc_nombre, a_sec_nombre, a_estasis, a_cur_nombre,a_fecha_reg, a_obs" />
                        </rich:column>

                </rich:dataTable>
            </h:form>
        </rich:panel>
        <rich:modalPanel id="mpAsisDocenteDiaria" zindex="4000"
                         autosized="true" minWidth="400">
            <f:facet name="header">
                <h:outputText value="Asistencia Docente"/>
            </f:facet>
            <f:facet name="controls">
                <h:panelGroup>
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('mpAsisDocenteDiaria')" title="Cerrar"/>
                </h:panelGroup>
            </f:facet>
            <h:form id="form2">
                <table width="100%">
                    <tr>
                        <td align=right colspan="2">
                    <a4j:commandButton image="/Imagenes/actions/filesave.png" title="Guardar"
                                       oncomplete="#{managerAsistenciaDocenteCL.oncompleteADD}"
                                       actionListener="#{managerAsistenciaDocenteCL.guardarObservacionAsistencia}"
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
                    <h:outputText value="#{managerAsistenciaDocenteCL.asisDocenteCL.doc_nombre}" id="a_doc_nombre"/>
                    <f:param id="p_ses_id" value="#{managerAsistenciaDocenteCL.asisDocenteCL.ses_id}"/>
                    <f:param id="p_doc_id" value="#{managerAsistenciaDocenteCL.asisDocenteCL.doc_id}"/>
                    <f:param id="p_sesefec_id" value="#{managerAsistenciaDocenteCL.asisDocenteCL.sesefeasisdoc_id}"/>
                    </td>
                    </tr>
                    
                    <tr style=" height : 24px;">
                            <td>Estado de asistencia docente:</td>
                            <td>
                                <h:selectOneMenu value="#{managerAsistenciaDocenteCL.asisDocenteCL.estasis_code_diario}" style="width : 220px;" id="a_estasis">
                                    <f:selectItems value="#{managerAsistenciaDocenteCL.asisDocenteCL.estasisdiario}"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        
                    
                    <tr style=" height : 24px;">
                        <td><h:outputText value="Seccion" /></td>
                    <td>
                    <h:outputText value="#{managerAsistenciaDocenteCL.asisDocenteCL.sec_nombre}" id="a_sec_nombre"/>
                    </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td><h:outputText value="Curso" /></td>
                    <td>
                    <h:outputText value="#{managerAsistenciaDocenteCL.asisDocenteCL.cur_nombre}" id="a_cur_nombre"/>
                    </td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td><h:outputText value="Fecha Registro" /></td>
                    <td>
                    <h:outputText value="#{managerAsistenciaDocenteCL.asisDocenteCL.sesefeasisdoc_fecha}" id="a_fecha_reg">
                        <f:convertDateTime pattern="dd  'de'  MMMMMM  'del'  yyyy" />
                    </h:outputText>
                    </td>
                    </tr>
                    
                    <tr style=" height : 24px;">
                            <td><h:outputText value="Observación" /></td>
                            <td colspan="2">
                                <h:inputTextarea id="a_obs" cols="20" rows="2"
                                                 value="#{managerAsistenciaDocenteCL.asisDocenteCL.sesObservacion}" />
                            </td>
                        </tr>
                        
                </table>
            </h:form>
        </rich:modalPanel>
        
    </body>
</f:view>
</html>
