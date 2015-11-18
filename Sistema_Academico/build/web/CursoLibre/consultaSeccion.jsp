
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
                                </td>
                            </tr>
                        </table>
                        <rich:spacer height="10"/>
                        <rich:separator height="2" width="100%" />
                        <rich:spacer height="30"/>
                        <table width="90%" align="center">
                            <tr>
                                <td><h:outputText value="Sede" styleClass="textoNegrita" /></td>
                                <td>
                                    <h:selectOneMenu id="iSede" style="width:250px" value="#{managerConsulSeccion.w_sed_id}" styleClass="selector">
                                        <f:selectItems value="#{managerConsulSeccion.cboSede}" />
                                        <a4j:support event="onchange" actionListener="#{managerConsulSeccion.cambioSede}" reRender="iAno,iArea,iModulo,iCurso"/>
                                    </h:selectOneMenu>
                                </td>
                                <td align="right"><h:outputText value="A�o" styleClass="textoNegrita"/></td>
                                <td>
                                    <h:selectOneMenu id="iAno" style="width:250px" value="#{managerConsulSeccion.w_ano_id}" styleClass="selector">
                                        <f:selectItems value="#{managerConsulSeccion.cboAno}" />
                                        <a4j:support event="onchange" actionListener="#{managerConsulSeccion.cambioAnio}" reRender="iArea,iModulo,iCurso"/>
                                    </h:selectOneMenu>
                                </td>
                                <td align="right"><h:outputText value="Mes" styleClass="textoNegrita"/></td>
                                <td>
                                    <h:selectOneMenu id="iMes" value="#{managerConsulSeccion.w_mes}" styleClass="selector">
                                        <f:selectItems value="#{managerConsulSeccion.cboMes}" />
                                        <a4j:support event="onchange" actionListener="#{managerConsulSeccion.seleccionarListaSecciones}"/>
                                    </h:selectOneMenu>
                                </td>
                                <td align="right"><h:outputText value="Dias de Clases" styleClass="textoNegrita"/></td>
                                <td>
                                    <h:selectOneMenu id="iDia" style="width:100px" value="#{managerConsulSeccion.w_dia_id}" styleClass="selector">
                                        <f:selectItems value="#{managerConsulSeccion.cboDia}" />
                                        <a4j:support event="onchange" actionListener="#{managerConsulSeccion.seleccionarListaSecciones}"/>
                                    </h:selectOneMenu>
                                </td>
                            </tr>
                            <tr>
                                <td align="right"><h:outputText value="Area" styleClass="textoNegrita"/></td>
                                <td>
                                    <h:selectOneMenu id="iArea" style="width:250px" value="#{managerConsulSeccion.w_area_id}" styleClass="selector">
                                        <f:selectItems value="#{managerConsulSeccion.cboArea}" />
                                        <a4j:support event="onchange" actionListener="#{managerConsulSeccion.seleccionarListaSecciones}" reRender="iModulo,iCurso"/>
                                    </h:selectOneMenu>
                                </td>
                                <td align="right"><h:outputText value="Modulo" styleClass="textoNegrita"/></td>
                                <td>
                                    <h:selectOneMenu id="iModulo" style="width:250px" value="#{managerConsulSeccion.w_mod_id}" styleClass="textoChiko">
                                        <f:selectItems value="#{managerConsulSeccion.cboModulo}" />
                                        <a4j:support event="onchange" actionListener="#{managerConsulSeccion.seleccionarListaSecciones}" reRender="iCurso"/>
                                    </h:selectOneMenu>
                                </td>
                                <td align="right"><h:outputText value="Curso" styleClass="textoNegrita"/></td>
                                <td>
                                    <h:selectOneMenu id="iCurso" style="width:250px" value="#{managerConsulSeccion.w_cur_id}" styleClass="selector">
                                        <f:selectItems value="#{managerConsulSeccion.cboCurso}" />
                                        <a4j:support event="onchange" actionListener="#{managerConsulSeccion.seleccionarListaSecciones}"/>
                                    </h:selectOneMenu>
                                </td>
                                <td align="right"><h:outputText value="" styleClass="textoNegrita"/></td>
                                <td>
                                    <%/*<h:selectOneMenu id="iArea" style="width:250px" value="#{managerConsulCurso.w_area_id}" styleClass="selector">
                                        <f:selectItems value="#{managerConsulCurso.cboArea}" />
                                        <a4j:support event="onchange" actionListener="#{managerConsulCurso.cambioArea}" reRender="iModulo,iCurso"/>
                                        </h:selectOneMenu>*/%>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="8">
                                    <rich:separator width="100%" height="2"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="8" align="center">

                                </td>
                            </tr>
                            <tr>
                                <td colspan="7" align="center">
                                    <h:selectOneRadio id="radio" layout="lineDirection" value="#{managerConsulModulo.w_rad_id}"  styleClass="texto">
                                        <f:selectItems value="#{managerConsulModulo.rdoOpciones}" />
                                    </h:selectOneRadio>

                                </td>
                                <td>
                                    <h:graphicImage value="/Imagenes/actions/printer.png" alt="Reporte" title="Reporte" width="50" height="50">

                                    </h:graphicImage>
                                </td>
                            </tr>
                        </table>
                        <br />
                        <%/*<rich:dataTable id="dtSeccion"
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
                            </rich:dataTable>*/%>

                    </div>
                </h:form>

            </rich:panel>
        </body>
    </f:view>
</html>
