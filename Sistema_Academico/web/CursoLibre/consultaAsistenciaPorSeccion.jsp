<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:Sistema Academico :.</title>
        <style type="text/css">
            .textoNegrita{
                font-size: 14px;
                font-family: sans-serif;
                color: #5581BC;

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

            .colorfila{
                background-color: #fff;
            }
            .colorfila:hover{
                background-color: aliceblue;
            }

        </style>
    </head>
    <f:view>
        <body>
            <jsp:include page="../TablaSistema/Menu.jsp"/>
            <rich:panel>
                <h:form>
                    <div style="width: 100%;" align="left">
                        <table width="100%">
                            <tr>
                                <td width="15%"></td>
                                <td align="center" width="70%">
                                    <h:outputText value="Consulta Asistencia Por Seccion" styleClass="textoCabecera"/>
                                </td>
                                <td align="right" width="15%">
                                </td>
                            </tr>
                        </table>

                        <rich:spacer height="10"/>
                        <rich:separator height="2" width="100%" />
                        <table width="80%" cellspacing="5px">
                            <tr>
                                <td width="40%" valign="top">
                                    <rich:panel style="width:100%;background-color: #FFF; border-radius: 5px;" id="pDatos">
                                        <table width="100%" border="0" align="center" style="font-size: 14px;">
                                            <tr class="alto">
                                                <td><h:outputText value="Sede" styleClass="textoNegrita" /></td>
                                            </tr>
                                            <tr>
                                                <td >
                                                    <h:selectOneMenu id="iSede" style="width:250px" value="#{managerClAsistencia.w_sed_id}" styleClass="selector">
                                                        <f:selectItems value="#{managerClAsistencia.cboSede}" />
                                                        <a4j:support event="onchange" actionListener="#{managerClAsistencia.seleccionarSeccion}" reRender="iAno,iArea,iModulo,iCurso,iSeccion,iInicio,iFin,dtAlumno,pDetalle"/>
                                                    </h:selectOneMenu>
                                                </td>
                                            </tr>
                                            <tr class="alto">
                                                <td><h:outputText value="Año" styleClass="textoNegrita" /></td> 
                                            </tr>
                                            <tr>
                                                <td >
                                                    <h:selectOneMenu id="iAno" style="width:250px" value="#{managerClAsistencia.w_ano_id}" styleClass="selector">
                                                        <f:selectItems value="#{managerClAsistencia.cboAno}" />
                                                        <a4j:support event="onchange" actionListener="#{managerClAsistencia.seleccionarSeccion}" reRender="iArea,iModulo,iCurso,iSeccion,iInicio,iFin,dtAlumno,pDetalle"/>
                                                    </h:selectOneMenu>
                                                </td>
                                            </tr>
                                            <tr class="alto">
                                                <td><h:outputText value="Área" styleClass="textoNegrita" /></td>
                                            </tr>
                                            <tr>
                                                <td >
                                                    <h:selectOneMenu id="iArea" style="width:250px" value="#{managerClAsistencia.w_area_id}" styleClass="selector">
                                                        <f:selectItems value="#{managerClAsistencia.cboArea}" />
                                                        <a4j:support event="onchange" actionListener="#{managerClAsistencia.cambioArea}" reRender="iModulo,iCurso,iSeccion,iInicio,iFin,dtAlumno,pDetalle"/>
                                                    </h:selectOneMenu>
                                                </td>
                                            </tr>
                                            <tr class="alto">
                                                <td><h:outputText value="Modulo" styleClass="textoNegrita" /></td>
                                            </tr>
                                            <tr>
                                                <td >
                                                    <h:selectOneMenu id="iModulo" style="width:250px" value="#{managerClAsistencia.w_mod_id}" styleClass="textoChiko">
                                                        <f:selectItems value="#{managerClAsistencia.cboModulo}" />
                                                        <a4j:support event="onchange" actionListener="#{managerClAsistencia.cambioModulo}" reRender="iCurso,iSeccion,iInicio,iFin,dtAlumno,pDetalle"/>
                                                    </h:selectOneMenu>
                                                </td>
                                            </tr>
                                            <tr class="alto">
                                                <td><h:outputText value="Curso" styleClass="textoNegrita" /></td>
                                            </tr>
                                            <tr>
                                                <td >
                                                    <h:selectOneMenu id="iCurso" style="width:250px" value="#{managerClAsistencia.w_cur_id}" styleClass="selector">
                                                        <f:selectItems value="#{managerClAsistencia.cboCurso}" />
                                                        <a4j:support event="onchange" actionListener="#{managerClAsistencia.cambioCurso}" reRender="iSeccion,iInicio,iFin,dtAlumno,pDetalle"/>
                                                    </h:selectOneMenu>
                                                </td>
                                            </tr>
                                            <tr class="alto">
                                                <td><h:outputText value="Seccion" styleClass="textoNegrita" /></td>
                                            </tr>
                                            <tr>
                                                <td >
                                                    <h:selectOneMenu id="iSeccion" style="width:250px" value="#{managerClAsistencia.w_sec_id}" styleClass="selector">
                                                        <f:selectItems value="#{managerClAsistencia.cboSeccion}" />
                                                        <a4j:support event="onchange" reRender="tbListado"
                                                                     actionListener="#{managerClAsistencia.listaAsistenciaPorSeccion}" oncomplete="javascript:changeColor()" />
                                                    </h:selectOneMenu>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td width="10%">
                                                    <h:outputText value="Exportar"/>
                                                </td>
                                                <td colspan="9" align="left">
                                                    <h:graphicImage value="/Imagenes/actions/mime_pdf.png" alt="Descargar archivo(PDF)" title="Descargar archivo(PDF)" style="cursor:pointer;">
                                                        <a4j:support event="onclick" actionListener="#{managerClAsistencia.imprimirFicha}" oncomplete="javascript:Richfaces.showModalPanel('mpReporte')" reRender="reporte"/>
                                                    </h:graphicImage>
                                                    <rich:spacer width="20" />
                                                    <h:commandButton action="#{managerClAsistencia.exportToExcel}" style="margin-top:10px; margin-right:10px"
                                                                     image="/Imagenes/actions/mime_xls.png"
                                                                     title="Descargar archivo(Excel)" alt="Descargar archivo(Excel)" />
                                                </td>
                                            </tr>



                                        </table>

                                    </rich:panel>
                                </td>

                                <td align="center">
                                    <div style="width: 900px; overflow: auto; background-color: #FFF; padding: 5px; border-radius: 5px; height: 450px;">
                                        <h:panelGroup id="tbListado">
                                            <table cellspacing="0px">
                                                <tr style=" background-color: #365F91; color: #FFF; border-radius: 5px;">
                                                    <td style="padding: 5px; ">Nro</td>
                                                    <td style="padding: 5px; ">Asistencia Personal</td>
                                                    <td style="padding: 5px;">Codigo</td>
                                                    <td style="padding: 5px;">Nombres</td>
                                                    <a4j:repeat  value="#{managerClAsistencia.lstSesiones}" var="nota">
                                                        <td style="padding: 5px;">
                                                            <h:outputText value="#{nota.expr1}" />
                                                        </td>
                                                    </a4j:repeat>
                                                </tr>
                                                <a4j:repeat  value="#{managerClAsistencia.lstAlumnos}" var="alumnos"> 
                                                    <tr class="colorfila">
                                                        <td><h:outputText value="#{managerClAsistencia.numero}"></h:outputText></td>
                                                            <td style="padding: 5px;">
                                                            <a4j:commandButton actionListener="#{managerClAsistencia.listaAsistenciaPorAlumno}" 
                                                                               oncomplete="Richfaces.showModalPanel('mpAsistenciaPorAlumno')" 
                                                                               image="/Imagenes/actions/check.png"
                                                                               reRender="formAsistenciaPorAlumno">
                                                                <f:param name="p_alum_id" value="#{alumnos.alumId}"/>
                                                            </a4j:commandButton>
                                                        </td>    
                                                        <td style="padding: 5px;">
                                                            <h:outputText value="#{alumnos.alumCodigo}" />
                                                        </td>
                                                        <td style="padding: 5px;">
                                                            <h:outputText value="#{alumnos.alumNom}" />
                                                        </td>
                                                        <a4j:repeat  value="#{alumnos.lstAsistencias}" var="asis">
                                                            <td style="padding: 5px; font: bold; text-align: center;">
                                                                <h:outputText value="#{asis}" />
                                                            </td>
                                                        </a4j:repeat>
                                                    </tr>
                                                </a4j:repeat>
                                            </table>
                                        </h:panelGroup>

                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </h:form>
                <rich:modalPanel id="mpAsistenciaPorAlumno" minWidth="1000" height="450"
                                 resizeable="true" zindex="3000" style="overflow-y:scroll; overflow-x:scroll;">
                    <f:facet name="header">
                        <h:outputText value="Asistencia Por Alumno"/>
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage  value="/Imagenes/actions/stop.png"
                                         style="cursor:pointer" title="Cerrar"
                                         onclick="Richfaces.hideModalPanel('mpAsistenciaPorAlumno')"/>
                    </f:facet>
                    <h:form id="formAsistenciaPorAlumno">
                        <table cellspacing="0px">
                            <tr>
                                <td width="10%">
                                    <h:outputText value="Exportar"/>
                                </td>
                                <td colspan="9" align="left">
                                    <h:graphicImage value="/Imagenes/actions/mime_pdf.png" alt="Descargar archivo(PDF)" title="Descargar archivo(PDF)" style="cursor:pointer;">
                                        <a4j:support event="onclick" actionListener="#{managerClAsistencia.imprimirFichaAlumno}" oncomplete="javascript:Richfaces.showModalPanel('mpReporteAlumno')" reRender="reporteAlumno"/>
                                    </h:graphicImage>
                                    <rich:spacer width="20" />
                                    <h:commandButton action="#{managerClAsistencia.exportToExcelAlumno}" style="margin-top:10px; margin-right:10px"
                                                     image="/Imagenes/actions/mime_xls.png"
                                                     title="Descargar archivo(Excel)" alt="Descargar archivo(Excel)" />
                                </td>
                            </tr>
                            <tr style=" background-color: #365F91; color: #FFF; border-radius: 5px;">
                                <td style="padding: 5px; ">Nro</td>
                                <td style="padding: 5px;">Codigo</td>
                                <td style="padding: 5px;">Nombres</td>
                                <a4j:repeat  value="#{managerClAsistencia.lstSesiones}" var="nota">
                                    <td style="padding: 5px;">
                                        <h:outputText value="#{nota.expr1}" />
                                    </td>
                                </a4j:repeat>
                            </tr>
                            <a4j:repeat  value="#{managerClAsistencia.lstAsisAlumno}" var="alumno">
                                <tr class="colorfila">
                                    <td><h:outputText value="#{managerClAsistencia.numero}"></h:outputText></td>   
                                        <td style="padding: 5px;">
                                        <h:outputText value="#{alumno.alumCodigo}" />
                                    </td>
                                    <td style="padding: 5px;">
                                        <h:outputText value="#{alumno.alumNom}" />
                                    </td>
                                    <a4j:repeat  value="#{alumno.lstAsistencias}" var="asis">
                                        <td style="padding: 5px; text-align: center;">
                                            <h:outputText value="#{asis}" style="#{alumno.estilo}" />
                                        </td>
                                    </a4j:repeat>
                                </tr>
                            </a4j:repeat>
                        </table>
                    </h:form>
                </rich:modalPanel>
                <rich:modalPanel id="mpReporte" width="830" height="600"  zindex="4500">
                    <f:facet name="header">
                        <h:outputText id="titulo" value="REPORTE ASISTENCIAS POR SECCION" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/title-close.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpReporte')" title="Cerrar"/>
                    </f:facet>
                    <a4j:form>
                        <table>
                            <tr>
                                <td align="center" valign="middle">
                                    <rich:panel style="width : 790px; height:580px;" >
                                        <a4j:mediaOutput id="reporte"
                                                         uriAttribute="data"
                                                         style="width : 750px; height:550px;"
                                                         element="object"
                                                         standby="cargando..." 
                                                         createContent="#{managerClAsistencia.cargarReporte}"
                                                         mimeType="application/pdf"
                                                         value="ficha"/>
                                    </rich:panel>
                                </td>
                            </tr>
                        </table>
                    </a4j:form>
                </rich:modalPanel>
                
                <rich:modalPanel id="mpReporteAlumno" width="830" height="600"  zindex="4500">
                    <f:facet name="header">
                        <h:outputText id="titulo2" value="REPORTE ASISTENCIAS POR ALUMNO" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/title-close.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpReporteAlumno')" title="Cerrar"/>
                    </f:facet>
                    <a4j:form>
                        <table>
                            <tr>
                                <td align="center" valign="middle">
                                    <rich:panel style="width : 790px; height:580px;" >
                                        <a4j:mediaOutput id="reporteAlumno"
                                                         uriAttribute="data"
                                                         style="width : 750px; height:550px;"
                                                         element="object"
                                                         standby="cargando..." 
                                                         createContent="#{managerClAsistencia.cargarReporteAlumno}"
                                                         mimeType="application/pdf"
                                                         value="ficha"/>
                                    </rich:panel>
                                </td>
                            </tr>
                        </table>
                    </a4j:form>
                </rich:modalPanel>

            </rich:panel>
        </body>
    </f:view>
</html>