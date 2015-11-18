<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Interfaz con el banco</title>
    </head>
    <body>
        <f:view>
            <jsp:include page="../TablaSistema/Menu.jsp"></jsp:include>
            <rich:panel style=" width : 100%;" >
                <h:form id="formUpload" style="width:100%">
                    <table style="font-size:13px; font-family:verdana; width: 100%">
                        <tr>
                            <td><strong>PROCESAR INFORMACI&Oacute;N DEL BANCO</strong></td>
                            <td align="right">
                                <rich:spacer width="8px"/>

                                <h:commandButton action="#{managerBanco.limpiarTodo}"
                                                 image="/Imagenes/actions/limpiar.png"
                                                 title="Limpiar todo el contenido"/>
                                <rich:spacer width="8px"/>

                                <a4j:commandButton id="banVistaPrevia" title="Vista previa"
                                                   image="/Imagenes/actions/viewmag.png"
                                                   actionListener="#{managerBanco.invocarReporte}"
                                                   reRender="reporte"
                                                   oncomplete="Richfaces.showModalPanel('mpReporteBanco',{top:100})"/>
                                <rich:spacer width="8px"/>

                                <h:commandButton action="#{managerBanco.generarArchivoDescargable}"
                                                 image="/Imagenes/actions/download.png"
                                                 title="Descargar archivo"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr width="100%"/></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <center>
                                    <h:panelGrid columns="3">
                                        <rich:fileUpload id="banUpload" acceptedTypes="txt"
                                                         maxFilesQuantity="1" listHeight="58px"
                                                         fileUploadListener="#{managerBanco.cargarArchivo}">
                                            <a4j:support event="onuploadcomplete" reRender="info, banProceso" />
                                        </rich:fileUpload>

                                        <h:panelGroup id="info" layout="block">
                                            <f:facet name="header">
                                                <h:outputText value="Archivo subido"/>
                                            </f:facet>
                                            <h:panelGrid columns="1">
                                                <h:outputLabel value="Archivo: " style="font-weight: bold;"/>
                                                <h:outputText value="#{managerBanco.file_nombre}"/>
                                            </h:panelGrid>

                                            <a4j:commandButton id="banProceso" value="Procesar archivo"
                                                               disabled="#{managerBanco.disable_proceso}"
                                                               action="#{managerBanco.procesarArchivo}"
                                                               reRender="bancoOutput, info"/>
                                        </h:panelGroup>
                                        <h:panelGroup>
                                            <h:panelGrid columns="2">
                                                <h:selectBooleanCheckbox value="#{managerBanco.genera_comprobante}">
                                                    <a4j:support event="onclick" action="#{managerBanco.seleccionarEfecto}"
                                                                 oncomplete="#{managerBanco.efecto}" reRender="contentPanel" />
                                                </h:selectBooleanCheckbox>
                                                <h:outputText value="Generar comprobantes"/>
                                            </h:panelGrid>
                                            <rich:effect  name="hideDiv"  for="contentDiv" type="BlindUp" />
                                            <rich:effect  name="showDiv"  for="contentDiv" type="Appear" />
                                            <div id="contentDiv">
                                                <rich:panel id="contentPanel" header="Comprobantes - Boleta">
                                                    <h:panelGrid columns="2">
                                                        <h:outputText value="Serie" />
                                                        <h:selectOneMenu value="#{managerBanco.serie}">
                                                            <f:selectItems value="#{managerBanco.series}"/>
                                                        </h:selectOneMenu>

                                                        <h:outputText value="Numero de Inicio"/>
                                                        <h:inputText id="nroinicio" value="#{managerBanco.nroInicio}" maxlength="7"/>
                                                    </h:panelGrid>
                                                </rich:panel>
                                            </div>
                                        </h:panelGroup>
                                    </h:panelGrid>
                                </center>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <h:panelGroup id="bancoOutput">
                                    <rich:panel rendered="#{managerBanco.rendered}">
                                        <rich:panel bodyClass="message #{managerBanco.body_class}">
                                            <h:outputText value="#{managerBanco.mensaje}" escape="false" />
                                            <h:panelGroup rendered="#{managerBanco.totalErrores != 0}">
                                                <rich:dataTable id="tablaError"  rows="10" onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                                onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                                cellpadding="0" cellspacing="0" width="98%" align="center"
                                                                border="0" style="font-weight: normal;"
                                                                value="#{managerBanco.errores}" var="error">

                                                    <rich:column style="text-align: center;">
                                                        <f:facet name="header">
                                                            <h:outputText value="Nro de linea" />
                                                        </f:facet>
                                                        <h:outputText value="#{error.banentNumeroLinea}"/>
                                                    </rich:column>
                                                    <rich:column style="text-align: left;">
                                                        <f:facet name="header">
                                                            <h:outputText value="Comentario" />
                                                        </f:facet>
                                                        <h:outputText value="#{error.banentComentario}"
                                                                      escape="false"/>
                                                    </rich:column>
                                                    <rich:column>
                                                        <f:facet name="header">
                                                            <h:outputText value="Numero de Operacion" />
                                                        </f:facet>
                                                        <h:outputText value="#{error.banentNroOperacion}"/>
                                                    </rich:column>

                                                    <f:facet name="footer">
                                                        <rich:datascroller id="scrollError" for="tablaError" maxPages="10" align="center"/>
                                                    </f:facet>
                                                </rich:dataTable><br />
                                            </h:panelGroup>
                                        </rich:panel>
                                        <h:panelGroup rendered="#{managerBanco.totalDataBanco != 0}">
                                            <div align="right"><br />
                                                <a4j:commandButton value="Salvar informacion procesada"
                                                                   styleClass="message" title="Guardar"
                                                                   reRender="formSalvar"
                                                                   disabled="#{managerBanco.totalDataBanco == 0}"
                                                                   actionListener="#{managerBanco.validaComprobante}"
                                                                   oncomplete="#{managerBanco.oncomplete}" /><hr />
                                            </div>
                                            <rich:dataTable id="tablaBanco"  rows="10" onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                            onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                            cellpadding="0" cellspacing="0" width="100%" border="0"
                                                            value="#{managerBanco.respuestas}" var="respuesta">

                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Alumno" />
                                                    </f:facet>
                                                    <h:outputText value="#{respuesta.banentNomAlumno}"/>
                                                </rich:column>

                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Concepto de pago" />
                                                    </f:facet>
                                                    <h:outputText value="#{respuesta.banentConceptoPago}"
                                                                  escape="false"/>
                                                </rich:column>

                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Monto 1" />
                                                    </f:facet>
                                                    <h:outputText value="#{respuesta.banentMonto1}">
                                                        <f:convertNumber pattern="0.00"/>
                                                    </h:outputText>
                                                </rich:column>

                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Monto 2" />
                                                    </f:facet>
                                                    <h:outputText value="#{respuesta.banentMonto2}">
                                                        <f:convertNumber pattern="0.00"/>
                                                    </h:outputText>
                                                </rich:column>

                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Monto 3" />
                                                    </f:facet>
                                                    <h:outputText value="#{respuesta.banentMonto3}">
                                                        <f:convertNumber pattern="0.00"/>
                                                    </h:outputText>
                                                </rich:column>

                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Monto 4" />
                                                    </f:facet>
                                                    <h:outputText value="#{respuesta.banentMonto4}">
                                                        <f:convertNumber pattern="0.00"/>
                                                    </h:outputText>
                                                </rich:column>

                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Monto 5" />
                                                    </f:facet>
                                                    <h:outputText value="#{respuesta.banentMonto5}">
                                                        <f:convertNumber pattern="0.00"/>
                                                    </h:outputText>
                                                </rich:column>

                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Mora" />
                                                    </f:facet>
                                                    <h:outputText value="#{respuesta.banentMora}">
                                                        <f:convertNumber pattern="0.00"/>
                                                    </h:outputText>
                                                </rich:column>

                                                 <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="MONTOS" />
                                                    </f:facet>
                                                    <h:outputText value="#{respuesta.banentMontoTotal}" />
                                                     <h:selectBooleanCheckbox  value="#{respuesta.banentHabilitar}" />
                                                </rich:column>

                                                <rich:column style="text-align: center;">
                                                    <f:facet name="header">
                                                        <h:outputText value="Fecha de movimiento"/>
                                                    </f:facet>
                                                    <h:outputText value="#{respuesta.banentFechaMovimiento}">
                                                        <f:convertDateTime pattern="dd / MM / yyyy"/>
                                                    </h:outputText>
                                                </rich:column>

                                                <rich:column style="text-align: center;">
                                                    <f:facet name="header">
                                                        <h:outputText value="Fecha de pago" />
                                                    </f:facet>
                                                    <h:outputText value="#{respuesta.banentFechaPago}">
                                                        <f:convertDateTime pattern="dd / MM / yyyy"/>
                                                    </h:outputText>
                                                </rich:column>

                                                <rich:column style="text-align: center;">
                                                    <f:facet name="header">
                                                        <h:outputText value="Numero de Operacion" />
                                                    </f:facet>
                                                    <h:outputText value="#{respuesta.banentNroOperacion}"/>
                                                </rich:column>

                                                <f:facet name="footer">
                                                    <rich:datascroller id="scrollBanco" for="tablaBanco" maxPages="10" align="center"/>
                                                </f:facet>
                                            </rich:dataTable>
                                        </h:panelGroup>
                                    </rich:panel>
                                </h:panelGroup>
                            <td>
                        </tr>
                    </table>
                </h:form>
            </rich:panel>

            <rich:modalPanel  id="mpSalvar" autosized="true"
                              height="100" zindex="2000">
                <f:facet name="header">
                    <h:outputText value="Salvar informacion"/>
                </f:facet>
                <h:form id="formSalvar">
                    <table style="font-size:13px; font-family:verdana; width: 100%">
                        <tr>
                            <td colspan="2">
                                <h:outputText value="<center>Se salvaran todos los cambios<br>
                                              Se borraran todos los datos temporales</center>"
                                              escape="false" styleClass="message"/>
                            </td>
                        </tr>
                        <tr>
                            <td width="30%"><h:outputText value="Seleccione la fecha para los comprobantes"/></td>
                            <td><rich:calendar datePattern="dd-MM-yyyy"
                                           value="#{managerBanco.fecha_comprobantes}" /></td>
                        </tr>
                        <tr>
                            <td align="center" colspan="2">
                                <h:commandButton value="Aceptar"
                                                 action="#{managerBanco.guardarInformacion}"/>
                                <rich:spacer width="20"/>
                                <a4j:commandButton value="Cancelar"
                                                   onclick="Richfaces.hideModalPanel('mpSalvar')"/>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>

            <rich:modalPanel  id="mpReporteBanco" autosized="true"
                              minWidth="500" zindex="2000">
                <f:facet name="header">
                    <h:outputText value="Vista previa - Informaci&oacute;n de Respuesta del Banco"
                                  escape="false"/>
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png"
                                    style="cursor:pointer" title="Cerrar"
                                    onclick="Richfaces.hideModalPanel('mpReporteBanco')"/>
                </f:facet>
                <h:form id="fromReporteBanco">
                    <rich:panel>
                        <a4j:mediaOutput id="reporte" uriAttribute="data"
                                         style="width : 650px; height: 500px;"
                                         element="object" standby="cargando..."
                                         createContent="#{managerBanco.reportePagosBanco}"
                                         mimeType="application/pdf"
                                         value="#{managerBanco.report_value}"/>
                    </rich:panel>
                </h:form>
            </rich:modalPanel>
        </f:view>
    </body>
</html>
