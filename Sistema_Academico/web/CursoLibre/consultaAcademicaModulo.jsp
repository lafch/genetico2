<%-- 
    Document   : consultaAcademica
    Created on : 01-sep-2011, 16:56:12
    Author     : Richard Rondinel
--%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:Sistema Academico :.</title>
        <style>
            .textoNegrita{
                font-size: 14px;
                font-family: sans-serif;
                color: #004080;
                font-weight: bold;
            }
            .texto{
                font-size: 14px;
                font-family: sans-serif;
                color: #004080;
            }
            .textoCabecera{
                font-size: 18px;
                font-family: sans-serif;
                font-weight: bold;
                color: #004080;

            }
            .alto{
                height: 25px;
            }
            .selector{
                font-size: 12px;
                font-family: sans-serif;
                color: #004080;
            }

            .textoChiko{
                font-size: 11px;
                font-family: sans-serif;
                color: #004080;

            }
            .fuentePagina{
                color: #004080;
            }
        </style>
    </head>
    <f:view>
        <body>
            <jsp:include page="../TablaSistema/Menu.jsp"/>
            <rich:panel>
                <h:form>
                    <div style="width: 100%" align="center">
                        <table width="100%">
                            <tr>
                                <td width="15%"></td>
                                <td align="center" width="70%">
                                    <h:outputText value="Consulta Academica - Modulos" styleClass="textoCabecera"/>
                                </td>
                                <td align="right" width="15%">
                                </td>
                            </tr>
                        </table>
                        <rich:spacer height="10"/>
                        <rich:separator height="2" width="100%" />
                        <rich:spacer height="30"/>
                        <table width="90%" align="center">
                            <tr>
                                <td align="right"><h:outputText value="Sede" styleClass="textoNegrita"/></td>
                                <td>
                                    <h:selectOneMenu id="iSede" value="#{managerConsulModulo.w_sed_id}" styleClass="selector">
                                        <f:selectItems value="#{managerConsulModulo.cboSede}" />
                                        <a4j:support event="onchange" reRender="iAno,iMes,iArea,iModulo,iCurso,dtAlumno" actionListener="#{managerConsulModulo.seleccionarListaAlumnos}"/>
                                    </h:selectOneMenu>
                                </td>
                                <td align="right"><h:outputText value="Año" styleClass="textoNegrita"/></td>
                                <td>
                                    <h:selectOneMenu id="iAno" value="#{managerConsulModulo.w_ano_id}" styleClass="selector">
                                        <f:selectItems value="#{managerConsulModulo.cboAno}" />
                                        <a4j:support event="onchange" reRender="iMes,iArea,iModulo,iCurso,dtAlumno" actionListener="#{managerConsulModulo.seleccionarListaAlumnos}"/>
                                    </h:selectOneMenu>
                                </td>
                                <td align="right"><h:outputText value="Mes" styleClass="textoNegrita"/></td>
                                <td>
                                    <h:selectOneMenu id="iMes" value="#{managerConsulModulo.w_mes}" styleClass="selector">
                                        <f:selectItems value="#{managerConsulModulo.cboMes}" />
                                        <a4j:support event="onchange" reRender="iArea,iModulo,iCurso,dtAlumno" actionListener="#{managerConsulModulo.seleccionarListaAlumnos}"/>
                                    </h:selectOneMenu>
                                </td>
                            </tr>
                            <tr>
                                <td align="right"><h:outputText value="Area" styleClass="textoNegrita"/></td>
                                <td>
                                    <h:selectOneMenu id="iArea" value="#{managerConsulModulo.w_are_id}" styleClass="selector">
                                        <f:selectItems value="#{managerConsulModulo.cboArea}" />
                                        <a4j:support event="onchange" reRender="iModulo,iCurso,dtAlumno"
                                                     actionListener="#{managerConsulModulo.limpiarData}" />
                                    </h:selectOneMenu>
                                </td>
                                <td align="right"><h:outputText value="Modulo" styleClass="textoNegrita"/></td>
                                <td>
                                    <h:selectOneMenu id="iModulo" value="#{managerConsulModulo.w_mod_id}" styleClass="selector">
                                        <f:selectItems value="#{managerConsulModulo.cboModulo}" />
                                        <a4j:support event="onchange" reRender="iCurso,dtAlumno"
                                                     actionListener="#{managerConsulModulo.seleccionarListaAlumnos}"/>
                                    </h:selectOneMenu>
                                </td>
                                <td align="right"><h:outputText value="Curso" styleClass="textoNegrita"/></td>
                                <td>
                                    <h:selectOneMenu id="iCurso" value="#{managerConsulModulo.w_cur_id}" styleClass="selector">
                                        <f:selectItems value="#{managerConsulModulo.cboCurso}" />
                                        <a4j:support event="onchange" reRender="dtAlumno"
                                                     actionListener="#{managerConsulModulo.seleccionarListaAlumnos}"/>
                                    </h:selectOneMenu>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="6">
                                    <rich:separator width="100%" height="2"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="6" align="center">

                                </td>
                            </tr>
                            <tr>
                                <td colspan="5" align="center">
                                    <h:selectOneRadio id="radio" layout="lineDirection" value="#{managerConsulModulo.w_rad_id}"  styleClass="texto">
                                        <f:selectItems value="#{managerConsulModulo.rdoOpciones}" />
                                    </h:selectOneRadio>

                                </td>
                                <td>
                                    <h:graphicImage value="/Imagenes/actions/printer.png" alt="Reporte" title="Reporte" width="50" height="50">
                                        <a4j:support event="onclick" actionListener="#{managerConsulModulo.imprimirFicha}" oncomplete="#{managerConsulModulo.oncomplete}" reRender="mpReporte"/>
                                    </h:graphicImage>
                                </td>
                            </tr>
                        </table>
                        <br />
                        <rich:dataTable id="dtAlumno"
                                        value="#{managerConsulModulo.c_dataColumna}"
                                        onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                        onRowMouseOut="this.style.backgroundColor='#F9F9F9'"
                                        cellpadding="0" cellspacing="0"
                                        width="100%"
                                        var="row"
                                        style="background-color: #F9F9F9;">
                            <rich:columns value="#{managerConsulModulo.c_cabeceraColumna}"
                                          var="column"
                                          index="index"
                                          begin="2"
                                          sortOrder="#{column.ordering}" >
                                <f:facet name="header">
                                    <h:outputText title="#{column.toolTip}" value="#{column.name}"/>
                                </f:facet>
                                <h:outputText value="#{row[index]}" escape="false"/>
                            </rich:columns>
                        </rich:dataTable>

                    </div>
                </h:form>

            </rich:panel>
            <a4j:form>

                <rich:modalPanel id="mpReporte" width="830" height="600"  zindex="4500">
                    <f:facet name="header">
                        <h:outputText id="titulo" value="Reporte" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/title-close.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpReporte')" title="Cerrar"/>
                    </f:facet>
                    <a4j:form>
                        <table>
                            <tr>
                                <td align="center" valign="middle">
                                    <rich:panel style="width : 790px; height:580px;" >
                                        <a4j:mediaOutput id="reporte" uriAttribute="data" style="width : 750px; height:550px;"
                                                         element="object" standby="cargando..."
                                                         createContent="#{managerConsulModulo.cargarReporte}"
                                                         mimeType="application/pdf"
                                                         value="1"/>
                                    </rich:panel>
                                </td>
                            </tr>
                        </table>
                    </a4j:form>
                </rich:modalPanel>
            </a4j:form>
        </body>
    </f:view>
</html>
