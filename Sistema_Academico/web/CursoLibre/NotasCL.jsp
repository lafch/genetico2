<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Seguimiento de notas</title>
    </head>
    <body>
        <f:view>
            <jsp:include page="../TablaSistema/Menu.jsp"></jsp:include>
            <rich:panel style=" width : 100%;" >
                <f:facet name="SEGUIMIENTO NOTAS CL"></f:facet>
                <h:form id="masterForm">
                    <table width="100%" style="font-size:10px; font-family:verdana">
                        <tr>
                            <td width="20%" colspan="2" ><strong>LISTADO DE NOTAS - CURSOS LIBRES</strong>
                            </td>
                            <td width="30%"></td>
                            <td width="70%" align=right>
                                <h:outputText id="talNombre" value="#{managerCLNota.mod_nombre} - #{managerCLNota.talape_nombre}"/>
                            </td>
                            <td align=right>
                                <table>
                                    <tr>
                                        <td>
                                            <h:commandButton type="button" title="Refrescar"
                                                             action="#{managerCLNota.cargarArbol}"
                                                             image="/Imagenes/actions/reload.png" />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="10"><hr size="1"></td>
                        </tr>
                        <tr>
                            <td style="font-size:10px; font-family:verdana;vertical-align:top;">
                                <rich:tree value="#{managerCLNota.arbol}"
                                           ajaxSubmitSelection="true"
                                           switchType="ajax"
                                           style="font-size:10px; font-family:verdana"
                                           var="item"
                                           nodeSelectListener="#{managerCLNota.seleccion}"
                                           reRender="talNombre,masterScroll, masterTable"/>
                            </td>
                            <td  style="vertical-align:top;" colspan="5">
                                <table width="100%"  cellspacing="0" border="0" cellpadding="0">
                                    <tr>
                                        <td>
                                            <rich:datascroller id="masterScroll" for="masterTable"
                                                               align="right" maxPages="30"/>
                                            <rich:dataTable id="masterTable" onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                            onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                            cellpadding="0" cellspacing="0" width="100%" border="0" rows="10"
                                                            value="#{managerCLNota.listaTalleres}" var="notascl">

                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Id de Seccion"/>
                                                    </f:facet>
                                                    <h:outputText value="#{notascl.sec_id}"/>
                                                </rich:column>
                                                <rich:column sortable="true" sortExpression="#{notascl.sec_cod}" >
                                                    <f:facet name="header">
                                                        <h:outputText value="Codigo de Seccion"/>
                                                    </f:facet>
                                                    <h:outputText value="#{notascl.sec_cod}"/>
                                                </rich:column>
                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Nombre de Seccion"/>
                                                    </f:facet>
                                                    <h:outputText value="#{notascl.sec_nom}"/>
                                                </rich:column>
                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Fecha de Inicio"/>
                                                    </f:facet>
                                                    <h:outputText value="#{notascl.sec_fini}">
                                                        <f:convertDateTime pattern="dd  'de'  MMMMMM  'del'  yyyy" />
                                                    </h:outputText>
                                                </rich:column>
                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Fecha de Fin"/>
                                                    </f:facet>
                                                    <h:outputText value="#{notascl.sec_ffin}">
                                                        <f:convertDateTime pattern="dd  'de'  MMMMMM  'del'  yyyy" />
                                                    </h:outputText>
                                                </rich:column>
                                                <rich:column style="text-align: center;">
                                                    <f:facet name="header">
                                                        <h:outputText value="Matriculados"/>
                                                    </f:facet>
                                                    <h:outputText value="#{notascl.mat_num}"/>
                                                </rich:column>
                                                <rich:column style="text-align: center;">
                                                    <f:facet name="header">
                                                        <h:outputText value="Imprimir"/>
                                                    </f:facet>
                                                    <a4j:commandButton image="/Imagenes/actions/fileprint.png"
                                                                       title="Imprimir notas de la seccion"
                                                                       actionListener="#{managerCLNota.imprimir}"
                                                                       reRender="reporte"
                                                                       oncomplete="Richfaces.showModalPanel('mpRepNotasCL', {top:80});"/>
                                                </rich:column>
                                                <rich:column style="text-align: center;">
                                                    <f:facet name="header">
                                                        <h:outputText value="Ingresar Notas"/>
                                                    </f:facet>
                                                    <f:param id="p_talape_id" value="#{notascl.talape_id}"/>
                                                    <f:param id="p_sec_id" value="#{notascl.sec_id}"/>
                                                    <a4j:commandButton image="/Imagenes/actions/notas.png"
                                                                       title="Muestra las notas del curso"
                                                                       actionListener="#{managerCLNota.asignarNotas}"
                                                                       reRender="panelNotas, mp_talape_id, mp_sec_id, mp_talape_nombre"
                                                                       oncomplete="Richfaces.showModalPanel('mpNotasCL', {top:80});"/>
                                                </rich:column>
                                            </rich:dataTable>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:panel>

            <rich:modalPanel id="mpNotasCL" style="overflow-y:auto" zindex="4000"
                             minWidth="630" minHeight="390" >
                <f:facet name="header">
                    <h:outputText value="Actualizacion de notas"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mpNotasCL')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="formNotasCL">
                    <table width="100%">
                        <tr>
                            <td align=right colspan="2">
                                <a4j:commandButton image="/Imagenes/actions/filesave.png" title="Guardar"
                                                   oncomplete="#{managerCLNota.oncomplete}"
                                                   actionListener="#{managerCLNota.guardarNotas}"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <hr width="100%" size="1">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" align="right">
                                <h:inputHidden value="#{managerCLNota.p_talape_id}" id="mp_talape_id"/>
                                <h:inputHidden value="#{managerCLNota.p_sec_id}" id="mp_sec_id"/>
                                <h:outputText value="#{managerCLNota.talape_nombre}" id="mp_talape_nombre"
                                              style="font-style: italic;font-weight: bold;"/>
                            </td>
                        <tr>
                            <td>
                                <div style="overflow: auto;">
                                    <h:panelGrid id="panelNotas" binding="#{managerCLNota.htmlPanel}"
                                                 columns="#{managerCLNota.nro_columnas}"
                                                 border="0" cellpadding="0" cellspacing="0" width="100%"/>
                                </div>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>

            <rich:modalPanel id="mpRepNotasCL" zindex="4000"
                             autosized="true">
                <f:facet name="header">
                    <h:outputText id="titulo" value="Registro de Notas" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('mpRepNotasCL')" title="Cerrar"/>
                </f:facet>
                <h:form id="fromRepEstPag">
                    <table>
                        <tr>
                            <td align="center" valign="middle">
                                <rich:panel>
                                    <a4j:mediaOutput id="reporte" uriAttribute="data"
                                                     style="width : 650px; height: 420px;"
                                                     element="object" standby="cargando..."
                                                     createContent="#{managerCLNota.cargarReporte}"
                                                     mimeType="application/pdf"
                                                     value="#{managerCLNota.p_rep_value}"/>
                                </rich:panel>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>
        </f:view>
    </body>
</html>
