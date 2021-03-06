<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@taglib uri="http://richfaces.org/rich" prefix="rich"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            function printObjectsSelected_u(sgcomponent, idAL, nombreAL, codigoAL) {
                idAL.value = sgcomponent.getSelectedItems().pluck('id');
                nombreAL.innerHTML = sgcomponent.getSelectedItems().pluck('docNombre');
                codigoAL.innerHTML = sgcomponent.getSelectedItems().pluck('docCodigo');
            }
        </script>
    </head>
    <body>
        <f:view>
        <body>
            <jsp:include page="../TablaSistema/Menu.jsp"></jsp:include>
            <rich:panel style="width:100%;">
                <f:facet name="PRORROGA DE FECHA DE INGRESO DE NOTAS"></f:facet>
                <h:form id="form1" style="width:100%">
                    <table style="font-size:13px; font-family:verdana; width: 100%">
                        <tr>
                            <td width="100%" colspan="2" ><strong>PRORROGA DE INGRESO DE NOTAS</strong>
                            </td>
                            <td align="rigth">
                            </td>
                            <td>
                                <a4j:commandButton image="/Imagenes/actions/viewmag.png"  actionListener="#{managerProrrogaIngresoNotasCL.mostrarSecciones}"
                                                   reRender="fromTableAsisDocCL, scrollTableAsisDocCL, tableAsisDocCL" />
                            </td>

                        </tr>
                        <tr>
                            <td colspan="6" width="100%">
                                <hr width="100%" size="1"></td>
                        </tr>
                        <tr style=" height : 24px;">

                            <td width="10%" >Docente:</td>
                            <td>
                                <h:inputText id="txt_dato" value="#{managerProrrogaIngresoNotasCL.suggestion}"
                                             style="width: 300px; font-size: 12px;" maxlength="10"/>
                                <rich:suggestionbox id="suggestion"
                                                    for="txt_dato" nothingLabel="Docente No Encontrado"
                                                    suggestionAction="#{managerProrrogaIngresoNotasCL.autocomplete}" var="asis"
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
                <h:form id="fromTableAsisDocCL" style="width: 100%">
                    <table style="width: 100%">
                        <tr>
                            <td align="right">
                                <rich:datascroller id="scrollTableAsisDocCL" for="tableAsisDocCL"
                                                   align="rigth" maxPages="20"/>
                            </td>
                        </tr>
                    </table>
                    <rich:dataTable id="tableAsisDocCL" rows="10"
                                    onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                    cellpadding="0" cellspacing="0"
                                    width="100%" border="0"
                                    value="#{managerProrrogaIngresoNotasCL.lstSecciones}"
                                    var="ses" >
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Id" />
                            </f:facet>
                            <h:outputText value="#{ses.expr10}" />
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Seccion" />
                            </f:facet>
                            <h:outputText value="#{ses.expr5}" />
                        </rich:column>
                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Fecha Inicio" />
                            </f:facet>
                            <h:outputText value="#{ses.expr6}" />
                        </rich:column>
                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Fecha Prorroga" />
                            </f:facet>
                            <h:outputText value="#{ses.expr11}" />
                        </rich:column>

                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Fecha de Extension"/>
                            </f:facet>
                            <f:param id="sec_id" value="#{ses.expr3}"/>
                            <f:param id="sec_fprorroga" value="#{ses.expr11}"/>
                            <f:param id="sec_obsprorroga" value="#{ses.expr12}"/>
                            <a4j:commandButton image="/Imagenes/actions/editpaste.png" oncomplete="#{managerProrrogaIngresoNotasCL.oncomplete}"
                                               reRender="frmProrroga" actionListener="#{managerProrrogaIngresoNotasCL.abrirProrroga}"/>
                        </rich:column>
                    </rich:dataTable>
                </h:form>
            </rich:panel>
            <rich:modalPanel id="mpEdicion" width="250" height="200" autosized="true" zindex="4000">
                <f:facet name="header">
                    <h:outputText value="Modificar fecha"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mpEdicion')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <a4j:form id="frmProrroga">
                    <rich:calendar value="#{managerProrrogaIngresoNotasCL.fechaProrroga}" datePattern="yyyy-MM-dd" /><br /><br />
                    <h:outputText value="Indique observación" /><br />
                    <h:inputTextarea value="#{managerProrrogaIngresoNotasCL.sObsProrroga}" cols="6" style="width:230px" />
                    <div style="margin-left: 200px; width: 100% " >
                        <a4j:commandButton image="/Imagenes/actions/filesave.png" actionListener="#{managerProrrogaIngresoNotasCL.guardarProrroga}"
                                           oncomplete="#{managerProrrogaIngresoNotasCL.oncomplete}" reRender="fromTableAsisDocCL, scrollTableAsisDocCL, tableAsisDocCL"/>
                    </div>
                </a4j:form>
            </rich:modalPanel>
        </f:view>
    </body>
</html>
