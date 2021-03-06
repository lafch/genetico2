<%-- 
    Document   : fecha_contactos
    Created on : 04-ago-2011, 16:58:07
    Author     : richard
--%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Modulo de Informes - Fecha de Contacto</title>
        <style type="text/css">
            #tb_header  th {
                text-align: left;
                background-color: #CFE1FA;
            }
            #tb_header{
                width: 70%;
                margin: auto;
            }
            select{
                width: 70%;
            }
            .textoTabla{
                font-size:9px;
            }
        </style>
    </head>
    <f:view>
        <body>
            <jsp:include page="../TablaSistema/Menu.jsp"/>


            <rich:separator style="width:'80%'" />
            <rich:panel id="pContenedor">
                <h:form id="frmBusqueda">
                    <table width="100%">
                        <tr>
                            <td>
                                <h1>Fecha de Contacto de Clientes</h1>
                            </td>
                            <td align="right">
                            </td>
                            <td>
                                <a4j:commandButton image="/Imagenes/actions/viewmag.png" title="Buscar" alt="Buscar"
                                                   actionListener="#{managerFecha.buscarConsultaPublico}" reRender="fLista,dtLista"/>
                            </td>
                        </tr>

                    </table>
                    <table align="center" width="900px">
                        <tr>
                            <td><h:outputText value="Categoría"/></td>
                            <td>
                                <h:selectOneMenu value="#{managerFecha.w_categoria_id}" id="wCategoria" style="width:200px">
                                    <f:selectItems value="#{managerFecha.cboCategoria}" />
                                    <a4j:support event="onchange" reRender="frmBusqueda"/>
                                </h:selectOneMenu>
                            </td>
                            <td>
                                <h:outputText rendered="#{'2' eq managerFecha.w_categoria_id}" value="Especialidad"/>
                                <h:outputText rendered="#{'1' eq managerFecha.w_categoria_id}" value="Centro"/>
                            </td>
                            <td>
                                <h:selectOneMenu rendered="#{'2' eq managerFecha.w_categoria_id}" value="#{managerFecha.w_especialidad_id}" id="wEspecialidad" style="width:200px">
                                    <f:selectItems value="#{managerFecha.cboEspecialidad}" />
                                    <a4j:support event="onchange" reRender="frmBusqueda"/>
                                </h:selectOneMenu>
                                <h:selectOneMenu rendered="#{'1' eq managerFecha.w_categoria_id}" value="#{managerFecha.w_centro_id}" id="wCentro" style="width:200px">
                                    <f:selectItems value="#{managerFecha.cboCentro}" />
                                    <a4j:support event="onchange" reRender="frmBusqueda"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText rendered="#{'1' eq managerFecha.w_categoria_id}" value="Area"/> </td>
                            <td>
                                <h:selectOneMenu rendered="#{'1' eq managerFecha.w_categoria_id}" value="#{managerFecha.w_are_id}" id="wArea" style="width:200px">
                                    <f:selectItems value="#{managerFecha.cboArea}" />
                                    <a4j:support event="onchange" reRender="wModulo"/>
                                </h:selectOneMenu>
                            </td>
                            <td><h:outputText rendered="#{'1' eq managerFecha.w_categoria_id}" value="Modulo"/></td>
                            <td>
                                <h:selectOneMenu rendered="#{'1' eq managerFecha.w_categoria_id}" value="#{managerFecha.w_mod_id}" id="wModulo" style="width:200px">
                                    <f:selectItems value="#{managerFecha.cboModulo}" />
                                    <a4j:support event="onchange" reRender="wCurso"/>
                                </h:selectOneMenu>
                            </td>
                            <td><h:outputText rendered="#{'1' eq managerFecha.w_categoria_id}" value="Curso"/> </td>
                            <td>
                                <h:selectOneMenu rendered="#{'1' eq managerFecha.w_categoria_id}" value="#{managerFecha.iCursoId}" id="wCurso" style="width:200px">
                                    <f:selectItems value="#{managerFecha.cboCurso}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Horario"/> </td>
                            <td>
                                <h:selectOneMenu value="#{managerFecha.w_hor_id}" id="wHorario" style="width:200px">
                                    <f:selectItems value="#{managerFecha.cboHorario}" />
                                </h:selectOneMenu>
                            </td>
                            <td><h:outputText value="Fecha Inicio"/> </td>
                            <td><rich:calendar value="#{managerFecha.w_fecha_ini}" id="wFechaIni" style="width:200px" /> </td>
                            <td><h:outputText value="Fecha Fin"/> </td>
                            <td><rich:calendar value="#{managerFecha.w_fecha_fin}" id="wFechaFin" style="width:200px"/></td>

                        </tr>
                        <tr>
                            <td><h:outputText value="Usuario"/> </td>
                            <td>
                                <h:selectOneMenu value="#{managerFecha.w_usu_id}" id="wUsuario" style="width:200px">
                                    <f:selectItems value="#{managerFecha.cboUsuario}" />
                                </h:selectOneMenu>
                            </td>
                            <td><h:outputText rendered="#{'1' eq managerFecha.w_categoria_id}" value="Matriculado"/> </td>
                            <td>
                                <h:selectOneMenu rendered="#{'1' eq managerFecha.w_categoria_id}" value="#{managerFecha.w_mat_tipo}" id="wmatriculado" style="width:200px">
                                    <f:selectItems value="#{managerFecha.cboMatricula}" />
                                </h:selectOneMenu>
                            </td>
                            <td><h:outputText value="Procedencia"/> </td>
                            <td>
                                <h:selectOneMenu value="#{managerFecha.w_pro_id}" id="wProcedencia" style="width:200px">
                                    <f:selectItems value="#{managerFecha.cboProcedencia}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr><td><h:outputText value="Medio"/></td>
                            <td>
                                <h:selectOneMenu value="#{managerFecha.medioId}" id="cboMedio" style="width:200px">
                                    <f:selectItems value="#{managerFecha.cboMedio}" />
                                    <a4j:support event="onchange" reRender="cboMedioDet"/>
                                </h:selectOneMenu>
                            </td>
                            <td><h:outputText value="Medio Detalle"/></td>
                            <td>
                                <h:selectOneMenu value="#{managerFecha.medioDetId}" id="cboMedioDet" style="width:200px">
                                    <f:selectItems value="#{managerFecha.cboMedioDet}" />
                                </h:selectOneMenu>
                            </td>
                            <td></td>
                            <td colspan="3" align="center">
                                <h:outputText value="Exportar"/>&nbsp;&nbsp;&nbsp;&nbsp;
                                <h:graphicImage value="/Imagenes/actions/mime_pdf.png" alt="Pdf" title="Pdf">
                                    <a4j:support event="onclick" actionListener="#{managerFecha.ImprimirFicha}" oncomplete="javascript:Richfaces.showModalPanel('mp7')" reRender="titulo,reporte"/>
                                </h:graphicImage>&nbsp;&nbsp;&nbsp;&nbsp;
                                <%/*<h:graphicImage value="/Imagenes/actions/mime_xls.png" alt="Excel" title="Excel">
                                </h:graphicImage>*/%>
                                <f:param id="p_param_id" value="1000"/>
                                <h:commandButton action="#{managerFecha.exportToExcel}" style="margin-top:10px; margin-right:10px"
                                      image="/Imagenes/actions/mime_xls.png"
                                      title="Descargar archivo(*.excel)" />
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Prioridad"/></td>
                            <td><h:selectOneMenu value="#{managerFecha.prioridadId}" id="cboPrioridad" style="width:200px">
                                    <f:selectItems value="#{managerFecha.cboPrioridad}" />
                                </h:selectOneMenu>
                            </td>
                            <td colspan="4">&nbsp;</td>
                        </tr>
                    </table>
                </h:form>
                <br />
                <h:form id="fLista">
                    <rich:datascroller id="paginacion" for="dtLista"
                                               align="right" maxPages="10"/>
                    <br/>
                    <rich:dataTable id="dtLista" var="lista" width="1050px" value="#{managerFecha.listaFechaContacto}" align="center" rows="6">
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Nro"/>
                            </f:facet>
                            <h:outputText value="#{lista.numOrden}"/>
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Apellidos y Nombres"/>
                            </f:facet>
                            <h:outputText value="#{lista.nombreAlumno}" styleClass="textoTabla"/>
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Area"/>
                            </f:facet>
                            <h:outputText value="#{lista.area}" styleClass="textoTabla"/>
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Modulo"/>
                            </f:facet>
                            <h:outputText value="#{lista.modulo}" styleClass="textoTabla"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Curso"/>
                            </f:facet>
                            <h:outputText value="#{lista.curso}" styleClass="textoTabla"/>
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Horario"/>
                            </f:facet>
                            <h:outputText value="#{lista.horario}" styleClass="textoTabla"/>
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Procedencia"/>
                            </f:facet>
                            <h:outputText value="#{lista.procedencia}" styleClass="textoTabla"/>
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Telefono" styleClass="textoTabla"/>
                            </f:facet>
                            <h:outputText value="#{lista.tlfFijo}" styleClass="textoTabla"/>
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Celular"/>
                            </f:facet>
                            <h:outputText value="#{lista.tlfCelular}"/>
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Fec. Contacto"/>
                            </f:facet>
                            <h:outputText value="#{lista.fechContac}">
                                <f:convertDateTime pattern="dd / MM / yyyy" />
                            </h:outputText>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Medio"/>
                            </f:facet>
                            <h:outputText value="#{lista.med_medio}">
                                <f:convertDateTime pattern="dd / MM / yyyy" />
                            </h:outputText>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Medio Det."/>
                            </f:facet>
                            <h:outputText value="#{lista.med_medio_detalle}">
                                <f:convertDateTime pattern="dd / MM / yyyy" />
                            </h:outputText>
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Matriculado"/>
                            </f:facet>
                            <h:outputText value="#{lista.matriculado}" styleClass="textoTabla"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Obs."/>
                            </f:facet>
                            <h:outputText value="#{lista.observacion}"/>
                            <f:param value="#{lista.consulta_id}" id="p_consulta_id" />
                        </rich:column>
                        <rich:column width="100">
                            <f:facet name="header">
                                <h:outputText value=""/>
                            </f:facet>
                            <a4j:commandButton image="/Imagenes/actions/icon_edit.png" reRender="formObservacion"
                                               actionListener="#{managerFecha.abrirObservacion}" oncomplete="#{managerFecha.oncomplete}" /> &nbsp;&nbsp;&nbsp;

                            <a4j:commandButton image="/Imagenes/actions/notas.png" reRender="dtConsultaDetalle,form6"
                                               actionListener="#{managerFecha.seleccionarDetalle}" oncomplete="#{managerFecha.oncomplete}" />
                        </rich:column>

                    </rich:dataTable>
                </h:form>

                <rich:modalPanel id="mpObservacion" autosized="true">
                    <f:facet name="header">
                        <h:outputText value="Observación"/>
                    </f:facet>

                    <f:facet name="controls">
                        <h:panelGroup>
                            <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpObservacion')" title="Cerrar"/>
                        </h:panelGroup>
                    </f:facet>
                    <a4j:form id="formObservacion">
                        <h:panelGroup layout="block" styleClass="scrolls">

                            <rich:calendar value="#{managerFecha.detalleConsulta.fecha}" datePattern="dd/MM/yyyy" /> 
                            <h:inputTextarea value="#{managerFecha.detalleConsulta.observacion}" style="width:190px;height:100px" id="txtaEdicionConsulta" />

                        </h:panelGroup>
                        <a4j:commandButton image="/Imagenes/actions/filesave.png"
                                           actionListener="#{managerFecha.actualizarOrservacion}" oncomplete="#{managerFecha.oncomplete}" reRender="fLista,dtLista"/>
                    </a4j:form>

                </rich:modalPanel>

                <rich:modalPanel id="mpConsultaDetalle" autosized="true">
                    <f:facet name="header">
                        <h:outputText value="Cambiar Fecha de Contacto"/>
                    </f:facet>
                    <f:facet name="controls">
                        <h:panelGroup>
                            <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                            onclick="Richfaces.hideModalPanel('mpConsultaDetalle')" title="Cerrar"/>
                        </h:panelGroup>
                    </f:facet>
                    <h:form id="form6">
                        <rich:dataTable id="dtConsultaDetalle" value="#{managerFecha.listarDetalle}" var="lista" width="550">
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value=""/>
                                </f:facet>
                                <h:outputText value="*"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Observacion"/>
                                </f:facet>
                                <h:outputText value="#{lista.observacion}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Fecha"/>
                                </f:facet>
                                <h:outputText value="#{lista.fecha}">
                                    <f:convertDateTime pattern="dd/MM/yyyy"/>
                                </h:outputText>
                            </rich:column>
                        </rich:dataTable>
                    </h:form>
                </rich:modalPanel>
            </rich:panel>

            <rich:modalPanel id="mp7" width="700" height="490">
                <f:facet name="header">
                    <h:outputText id="titulo" value="#{managerFecha.tituloReporte}" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp7')" title="Cerrar"/>
                </f:facet>
                <h:form>
                    <table>
                        <tr>
                            <td align="center" valign="middle">
                                <rich:panel>
                                    <a4j:mediaOutput id="reporte" uriAttribute="data" style="width : 650px; height: 420px;" element="object" standby="cargando..." createContent="#{managerFecha.cargarReporte}" mimeType="application/pdf" value="#{managerFecha.valorRep}"/>
                                </rich:panel>
                            </td>
                        </tr>
                    </table>
                </h:form>

            </rich:modalPanel>
        </body>
    </f:view>
</html>