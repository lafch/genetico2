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
                                    <h:outputText value="Consulta Academica - Inicios" styleClass="textoCabecera"/>
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
                                    <h:selectOneMenu id="iSede" value="#{managerConsulInicio.w_sed_id}" styleClass="selector">
                                        <f:selectItems value="#{managerConsulInicio.cboSede}" />
                                        <a4j:support event="onchange" reRender="iAno,iMes,iModulo,iCurso"/>
                                    </h:selectOneMenu>
                                </td>
                                <td align="right"><h:outputText value="Año"  styleClass="textoNegrita"/></td>
                                <td>
                                    <h:selectOneMenu id="iAno" value="#{managerConsulInicio.w_ano_id}" styleClass="selector">
                                        <f:selectItems value="#{managerConsulInicio.cboAno}" />
                                        <a4j:support event="onchange" reRender="iMes,iModulo,iCurso,dtSecciones"
                                                     actionListener="#{managerConsulInicio.seleccionarSecciones}"/>
                                    </h:selectOneMenu>
                                </td>
                                <td align="right"><h:outputText value="Mes" styleClass="textoNegrita"/></td>
                                <td>
                                    <h:selectOneMenu id="iMes" value="#{managerConsulInicio.w_mes}" styleClass="selector">
                                        <f:selectItems value="#{managerConsulInicio.cboMes}" />
                                        <a4j:support event="onchange" reRender="iModulo,iCurso,dtSecciones"
                                                     actionListener="#{managerConsulInicio.seleccionarSecciones}"/>
                                    </h:selectOneMenu>
                                </td>
                            </tr>
                            <tr>
                                <td align="right"><h:outputText value="Modulo" styleClass="textoNegrita"/></td>
                                <td>
                                    <h:selectOneMenu id="iModulo" value="#{managerConsulInicio.w_mod_id}" styleClass="selector">
                                        <f:selectItems value="#{managerConsulInicio.cboModulo}" />
                                        <a4j:support event="onchange" reRender="iCurso,dtSecciones"
                                                     actionListener="#{managerConsulInicio.seleccionarSecciones}"/>
                                    </h:selectOneMenu>
                                </td>
                                <td align="right"><h:outputText value="Curso" styleClass="textoNegrita"/></td>
                                <td>
                                    <h:selectOneMenu id="iCurso" value="#{managerConsulInicio.w_cur_id}" styleClass="selector">
                                        <f:selectItems value="#{managerConsulInicio.cboCurso}" />
                                        <a4j:support event="onchange" reRender="dtSecciones"
                                                     actionListener="#{managerConsulInicio.seleccionarSecciones}"/>
                                    </h:selectOneMenu>
                                </td>
                                <td align="right"><!--<h:outputText value="Docente" styleClass="textoNegrita"/>--></td>
                                <td>
                                    <!--<h:selectOneMenu id="iDocente" value="#{managerConsulInicio.doc_id}" styleClass="selector" >
                                        <f:selectItems value="#{managerConsulInicio.cboDocente}"/>
                                    </h:selectOneMenu>-->
                                </td>
                            </tr>
                            <tr>
                                <td colspan="6">
                                    <rich:separator width="100%" height="2"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="6" align="center">
                                    <rich:dataTable value="#{managerConsulInicio.listarSecciones}" var="lista" id="dtSecciones" width="90%">
                                        <rich:column>
                                            <f:facet name="header"><h:outputText value="Nro"/> </f:facet>
                                            <h:outputText value="#{lista.sec_contador}"/>
                                        </rich:column>
                                        <rich:column sortBy="#{lista.sec_curso}">
                                            <f:facet name="header"><h:outputText value="Curso"/> </f:facet>
                                            <h:outputText value="#{lista.sec_curso}"/>
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header"><h:outputText value="Seccion"/> </f:facet>
                                            <h:outputText value="#{lista.secNombre}"/>
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header"><h:outputText value="Horario"/> </f:facet>
                                            <h:outputText value="#{lista.sec_horario_corto}" escape="false"/>
                                            <rich:toolTip>
                                                <span style="white-space:nowrap">
                                                    <h:outputText value="#{lista.sec_horario}" escape="false"/>
                                                </span>
                                            </rich:toolTip>
                                        </rich:column>
                                        <rich:column sortBy="#{lista.secFinicio}">
                                            <f:facet name="header"><h:outputText value="Fec. Inicio"/> </f:facet>
                                            <h:outputText value="#{lista.secFinicio}">
                                                <f:convertDateTime pattern="dd / MM / yyyy"/>
                                            </h:outputText>
                                        </rich:column>
                                        <rich:column sortBy="#{lista.secFfin}">
                                            <f:facet name="header"><h:outputText value="Fec. Fin"/> </f:facet>
                                            <h:outputText value="#{lista.secFfin}">
                                                <f:convertDateTime pattern="dd / MM / yyyy"/>
                                            </h:outputText>
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header"><h:outputText value="Matriculados"/> </f:facet>
                                            <h:outputText value="#{lista.sec_canti_matricula}"/>
                                        </rich:column>
                                    </rich:dataTable>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="5" align="center">
                                    <h:selectOneRadio id="radio" layout="lineDirection" value="#{managerConsulAca.w_rad_id}">
                                        <f:selectItems value="#{managerConsulInicio.rdoOpciones}" />
                                    </h:selectOneRadio>

                                </td>
                                <td>
                                    <h:graphicImage value="/Imagenes/actions/printer.png" alt="Reporte" title="Reporte" width="50" height="50">
                                        <a4j:support event="onclick" actionListener="#{managerConsulInicio.ImprimirFicha}" oncomplete="#{managerConsulInicio.oncomplete}" reRender="titulo,reporte"/>
                                    </h:graphicImage>
                                </td>
                            </tr>
                        </table>
                    </div>
                </h:form>
                <a4j:form>

                    <rich:modalPanel id="mp7" width="830" height="600"  zindex="4500">
                        <f:facet name="header">
                            <h:outputText id="titulo" value="Reporte" />
                        </f:facet>
                        <f:facet name="controls">
                            <h:graphicImage value="/Imagenes/actions/title-close.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp7')" title="Cerrar"/>
                        </f:facet>
                        <a4j:form>
                            <table>
                                <tr>
                                    <td align="center" valign="middle">
                                        <rich:panel style="width : 790px; height:580px;" >
                                            <a4j:mediaOutput id="reporte" uriAttribute="data" style="width : 750px; height:550px;"
                                                             element="object" standby="cargando..."
                                                             createContent="#{managerConsulInicio.cargarReporte}"
                                                             mimeType="application/pdf"
                                                             value="1"/>
                                        </rich:panel>
                                    </td>
                                </tr>
                            </table>
                        </a4j:form>
                    </rich:modalPanel>
                </a4j:form>

            </rich:panel>
        </body>
    </f:view>
</html>
