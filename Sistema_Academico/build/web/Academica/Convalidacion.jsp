<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Convalidaci&oacute;n de Cursos</title>
    </head>
    <f:view>
        <body>
            <jsp:include page="../TablaSistema/Menu.jsp"></jsp:include>
            <rich:panel style="width:100%;">
                <h:form id="formSearch" style="width:100%">
                    <table style="font-size:13px; font-family:verdana; width: 100%">
                        <tr>
                            <td colspan="2"><strong>CONVALIDACION DE CURSOS</strong></td>
                            <td align="right">
                                <rich:spacer width="8px"/>

                                <a4j:commandButton id="buscar" action="#{managerConvalidacion.buscarCursos}"
                                                   image="/Imagenes/actions/viewmag.png" title="Buscar"
                                                   oncomplete="#{managerConvalidacion.oncomplete}"
                                                   reRender="tableAlumno, scrollAlumno, formAlumno" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3"><hr width="100%"/></td>
                        </tr>
                        <tr>
                            <td width="70px" class="tdLabel"><h:outputText value="Codigo" /></td>
                            <td>
                                <h:inputText value="#{managerConvalidacion.alu_codigo}"/>
                            </td><td />
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Paterno" /></td>
                            <td>
                                <h:inputText value="#{managerConvalidacion.alu_paterno}"/>
                            </td><td />
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Materno" /></td>
                            <td>
                                <h:inputText value="#{managerConvalidacion.alu_materno}"/>
                            </td><td />
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Nombres" /></td>
                            <td>
                                <h:inputText value="#{managerConvalidacion.alu_nombre}"/>
                            </td><td />
                        </tr>
                        <tr>
                            <td colspan="3"><hr width="100%"/></td>
                        </tr>
                    </table>
                </h:form>
                <h:form id="formAlumno" style="width: 100%">
                    <table style="width: 100%">
                        <tr>
                            <td align="right">
                                <rich:datascroller id="scrollAlumno" for="tableAlumno"
                                                   align="right" maxPages="20" />
                            </td>
                        </tr>
                    </table>
                    <rich:dataTable id="tableAlumno" rows="10"  var="alu"
                                    onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                    cellpadding="0" cellspacing="0" width="100%" border="0"
                                    value="#{managerConvalidacion.alumnos}">

                        <f:param id="p_alu_id" value="#{alu.alu_id}"/>
                        <f:param id="p_alu_nom" value="#{alu.alu_codigo} - #{alu.alu_paterno} #{alu.alu_materno} #{alu.alu_nombre}"/>
                        <f:param id="p_esp_id" value="#{alu.esp_id}"/>
                        <f:param id="p_esp_nom" value="#{alu.esp_nombre}"/>
                        <f:param id="p_sem_nom" value="#{alu.sem_ingreso}"/>

                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Id" />
                            </f:facet>
                            <h:outputText value="#{alu.alu_id}"/>
                        </rich:column>

                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Codigo" />
                            </f:facet>
                            <h:outputText value="#{alu.alu_codigo}"/>
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Nombre" />
                            </f:facet>
                            <h:outputText value="#{alu.alu_paterno} #{alu.alu_materno} #{alu.alu_nombre}"/>
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Especialidad" />
                            </f:facet>
                            <h:outputText value="#{alu.esp_nombre}"/>
                        </rich:column>

                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Convalidar" />
                            </f:facet>
                            <a4j:commandButton title="Convalidar cursos"
                                               reRender="formConv, tableConv"
                                               image="/Imagenes/actions/convalidar.png"
                                               oncomplete="Richfaces.showModalPanel('mpConvalidacion')"
                                               actionListener="#{managerConvalidacion.capturarAlumno}"/>
                        </rich:column>

                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Imprimir" />
                            </f:facet>
                            <f:param id="rep_alu_codigo" value="#{alu.alu_codigo}"/>
                            <a4j:commandButton image="/Imagenes/actions/fileprint.png"
                                               title="Imprimir boleta de notas"
                                               reRender="fromReporte, reporte"
                                               actionListener="#{managerConvalidacion.imprimir}"
                                               oncomplete="Richfaces.showModalPanel('mpRepConv',{top:80})"
                                               style="border-width: 0px;"/>

                        </rich:column>
                    </rich:dataTable>
                </h:form>
            </rich:panel>

            <rich:modalPanel id="mpConvalidacion" minWidth="600"
                             zindex="4000" autosized="true">
                <f:facet name="header">
                    <h:outputText value="Convalidacion de Cursos"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png"
                                        style="cursor:pointer" title="Cerrar"
                                        onclick="Richfaces.hideModalPanel('mpConvalidacion')"/>
                    </h:panelGroup>
                </f:facet>
                <a4j:form id="formConv">
                    <table width="100%">
                        <tr>
                            <td colspan="2" align="right">
                                <a4j:commandButton image="/Imagenes/actions/filesave.png" title="Guardar"
                                                   oncomplete="#{managerConvalidacion.oncomplete}"
                                                   reRender="formSearch"
                                                   action="#{managerConvalidacion.guardarConvalidacion}"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr width="100%"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Alumno" /></td>
                            <td><h:outputText value="#{managerConvalidacion.alu_nombre}"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Especialidad"/></td>
                            <td><h:outputText value="#{managerConvalidacion.esp_nombre}"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Semestre Ingreso"/></td>
                            <td><h:outputText value="#{managerConvalidacion.sem_ingreso}"/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr width="100%"/></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div style="height:400px; overflow: auto;">
                                    <rich:dataTable id="tableConv" var="conv"
                                                    onRowMouseOver="this.style.backgroundColor='#D7D7D7'"
                                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                    cellpadding="0" cellspacing="0" width="100%" border="0"
                                                    value="#{managerConvalidacion.convalidaciones}">

                                        <rich:column style="text-align: center;">
                                            <f:facet name="header">
                                                <h:outputText value="Sec. Id" />
                                            </f:facet>
                                            <h:outputText value="#{conv.reg_sec_id}"/>
                                        </rich:column>

                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Seccion" />
                                            </f:facet>
                                            <h:outputText value="#{conv.reg_sec_nombre}"/>
                                        </rich:column>

                                        <rich:column style="text-align: center;">
                                            <f:facet name="header">
                                                <h:outputText value="Plan curso" />
                                            </f:facet>
                                            <h:outputText value="#{conv.reg_plancur_codigo}"/>
                                        </rich:column>

                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Curso" />
                                            </f:facet>
                                            <h:outputText value="#{conv.reg_cur_nombre}"/>
                                        </rich:column>

                                        <rich:column style="text-align: center;">
                                            <f:facet name="header">
                                                <h:outputText value="Activar" />
                                            </f:facet>
                                            <h:selectBooleanCheckbox value="#{conv.activar}">
                                                <a4j:support event="onclick" reRender="panNota"
                                                             focus="txtNota"
                                                             action="#{conv.cambiarEstado}"/>
                                            </h:selectBooleanCheckbox>
                                        </rich:column>

                                        <rich:column style="text-align: center;">
                                            <f:facet name="header">
                                                <h:outputText value="Nota" />
                                            </f:facet>
                                            <h:panelGroup id="panNota">
                                                <h:inputText id="txtNota" value="#{conv.reg_nota}"
                                                             disabled="#{conv.disable}" maxlength="2"
                                                             autocomplete="false"
                                                             style="text-align: center;width: 30px; #{conv.style}"/>
                                            </h:panelGroup>
                                        </rich:column>
                                    </rich:dataTable>
                                </div>
                            </td>
                        </tr>
                    </table>
                </a4j:form>
            </rich:modalPanel>

            <rich:modalPanel id="mpRepConv" zindex="4000" autosized="true">
                <f:facet name="header">
                    <h:outputText id="titulo" value="Boleta de notas" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('mpRepConv')" title="Cerrar"/>
                </f:facet>
                <h:form id="fromReporte">
                    <table>
                        <tr>
                            <td align="center" valign="middle">
                                <rich:panel>
                                    <a4j:mediaOutput id="reporte" uriAttribute="data"
                                                     style="width : 650px; height: 500px;"
                                                     element="object" standby="cargando..."
                                                     createContent="#{managerConvalidacion.reporteBoletaNotas}"
                                                     mimeType="application/pdf"
                                                     value="repote_boleta"/>
                                </rich:panel>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>
        </body>
    </f:view>
</html>
