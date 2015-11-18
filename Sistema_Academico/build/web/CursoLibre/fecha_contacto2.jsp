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
        </style>
    </head>
    <body>
        <f:view>
            <jsp:include page="../TablaSistema/Menu.jsp"/>
            <h1>Fecha de Contacto de Clientes</h1>
            <rich:separator style="width:'80%'" />
            &nbsp;
            <rich:panel id="pContenedor">
                <h:form id="form1">

                    <table align="center" width="80%">
                        <tr>
                            <th class="dr-table-subheadercell rich-table-subheadercell">&Aacute;rea : </th>
                            <td>
                                <h:selectOneMenu id="w_cboAreas" value="#{managerFechaContacto.w_area_id}">
                                    <f:selectItems value="#{managerFechaContacto.cboAreas}" />
                                    <a4j:support event="onchange" reRender="w_cboModulos,w_cboTalleres,pReporte,dsReporte"/>
                                </h:selectOneMenu>
                            </td>
                            <th class="dr-table-subheadercell rich-table-subheadercell">M&oacute;dulo : </th>
                            <td>
                                <h:selectOneMenu id="w_cboModulos" value="#{managerFechaContacto.w_modulo_id}">
                                    <f:selectItems value="#{managerFechaContacto.cboModulos}" />
                                    <a4j:support event="onchange" reRender="w_cboTalleres"/>
                                </h:selectOneMenu>
                            </td>
                            <th class="dr-table-subheadercell rich-table-subheadercell">Taller : </th>
                            <td>
                                <h:selectOneMenu id="w_cboTalleres" value="#{managerFechaContacto.w_taller_id}">
                                    <f:selectItems value="#{managerFechaContacto.cboTalleres}" />
                                    <a4j:support event="onchange" reRender="w_cboHorarios"/>
                                </h:selectOneMenu>
                            </td>

                        </tr>
                        <tr>
                            <th class="dr-table-subheadercell rich-table-subheadercell">Horario : </th>
                            <td>
                                <h:selectOneMenu id="w_cboHorarios" value="#{managerFechaContacto.w_horario_id}">
                                    <f:selectItems value="#{managerFechaContacto.cboHorarios}" />
                                </h:selectOneMenu>
                            </td>
                            <th class="dr-table-subheadercell rich-table-subheadercell">Fecha de contacto del : </th>
                            <td>
                                <rich:calendar id="txtFIni" value="#{managerFechaContacto.fechContactoIni}" >
                                    <a4j:support event="onchange" reRender="pReporte,dsReporte"/>
                                </rich:calendar>
                            </td>
                            <th>al : </th>
                            <td>
                                <rich:calendar id="txtFFin" value="#{managerFechaContacto.fechContactoFin}" />
                                <a4j:support event="onchange" reRender="pReporte,dsReporte"/>
                            </td>

                        </tr>
                        <tr>
                            <th class="dr-table-subheadercell rich-table-subheadercell">Usuario : </th>
                            <td>
                                <h:selectOneMenu id="w_cboUsuarios" value="#{managerFechaContacto.w_usuario_id}">
                                    <f:selectItems value="#{managerFechaContacto.cboUsuario}" />
                                </h:selectOneMenu>
                            </td>
                            <th>
                                <h:outputText value="Procedencia:"/>
                            </th>
                            <td colspan="2">
                                <h:selectOneMenu id="w_cboProcedencia" value="#{managerFechaContacto.w_procedencia}">
                                    <f:selectItems value="#{managerFechaContacto.cboProcedencia}" />
                                </h:selectOneMenu>
                            </td>
                            <td><a4j:commandButton id="btnExportar" title="Exportar fecha de contactos" image="/Imagenes/actions/excel.jpg" actionListener="#{managerFechaContacto.exportarExcel}" oncomplete="#{managerFechaContacto.oncomplete}"  /></td>
                        </tr>
                        <tr><td colspan="6"><a4j:commandButton id="btnFiltrar" reRender="pReporte,dsReporte" title="Filtrar" image="/Imagenes/actions/viewmag.png" actionListener="#{managerFechaContacto.filtrar}" oncomplete="#{managerFechaContacto.oncomplete}"  /></td></tr>
                        <tr><td colspan="6">
                        <a4j:commandButton image="/Imagenes/actions/fileprint.png"
                                                                       title="Imprimir notas de la seccion"
                                                                      
                                                                       reRender="reporte"
                                                                       oncomplete="Richfaces.showModalPanel('mpRepNotasCL', {top:80});"/>
                        </td>

                    </table>
                </h:form>
                <h:form>
                    <rich:datascroller style="width:100%" for="tbReporte" reRender="tbReporte" maxPages="10" id="dsReporte" />
                    <rich:panel id="pReporte" style="width:100%" >
                        <rich:dataTable style="width:100%" id="tbReporte" width="100%" rows="10"
                                        onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                        onRowMouseOut="this.style.backgroundColor='#FFFFFF'"
                                        cellpadding="0" cellspacing="0"
                                        value="#{managerFechaContacto.listadoRpt}" var="consulta">
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Nro." />
                                </f:facet>
                                <f:param id="consulta_id" value="#{consulta.consulta_id}"/>
                                <h:outputText value="#{consulta.numOrden}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Apellidos y nombres" />
                                </f:facet>
                                <h:outputText value="#{consulta.nombreAlumno}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Área" />
                                </f:facet>
                                <h:outputText value="#{consulta.area}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Modulo" />
                                </f:facet>
                                <h:outputText value="#{consulta.modulo}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Curso" />
                                </f:facet>
                                <h:outputText value="#{consulta.curso}"/>
                            </rich:column>
                            <rich:column width="250" >
                                <f:facet name="header">
                                    <h:outputText value="Horario" />
                                </f:facet>
                                <h:outputText value="#{consulta.horario}" escape="false"  />
                                    <rich:toolTip followMouse="true" showDelay="100" styleClass="tooltip">
                                        <span  style="white-space:nowrap">
                                       <h:outputText value="#{consulta.detHorario}" escape="false"  />
                                        </span>
                                    </rich:toolTip>

                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Proced." />
                                </f:facet>
                                <h:outputText value="#{consulta.procedencia}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Tlf. Celular" />
                                </f:facet>
                                <h:outputText value="#{consulta.tlfCelular}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Tlf. Fijo" />
                                </f:facet>
                                <h:outputText value="#{consulta.tlfFijo}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Fecha de registro" />
                                </f:facet>
                                <h:outputText value="#{consulta.fechReg}" >
                                    <f:convertDateTime pattern="dd-MM-yyyy" />
                                </h:outputText>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Fecha de contacto" />
                                </f:facet>
                                <h:outputText value="#{consulta.fechContac}" >
                                    <f:convertDateTime pattern="dd-MM-yyyy" />
                                </h:outputText>
                                <a4j:commandButton image="/Imagenes/actions/editpaste.png" reRender="mpCambioFechaContacto" oncomplete="#{managerFechaContacto.oncomplete}" actionListener="#{managerFechaContacto.mostrarCambioFecha}" />
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Matric." />
                                </f:facet>
                                <h:outputText value="#{consulta.matriculado}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Obs." />
                                </f:facet>
                                <a4j:commandButton image="/Imagenes/actions/academico.png" title="Editar observación"
                                                   oncomplete="#{managerFechaContacto.oncomplete}" actionListener="#{managerFechaContacto.mostrarObervacion}" reRender="txtaEdicionConsulta">
                                    <rich:toolTip>
                                        <span style="white-space:nowrap" >
                                            <h:outputText value="#{consulta.observacion}" />
                                        </span>
                                    </rich:toolTip>
                                </a4j:commandButton>
                            </rich:column>
                             <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Det." />
                                </f:facet>
                                <a4j:commandButton image="/Imagenes/actions/rectificacion.gif" title="ver detalle"
                                                   actionListener="#{managerFechaContacto.seleccionarDetalle}" reRender="form6"
                                                   oncomplete="#{managerFechaContacto.oncomplete}" />
                            </rich:column>
                        </rich:dataTable>
                    </rich:panel>
                </h:form>
            </rich:panel>
            <rich:modalPanel id="mpCambioFechaContacto" autosized="true">
                <f:facet name="header">
                    <h:outputText value="Cambiar Fecha de Contacto"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mpCambioFechaContacto')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="form2">
                    <table>
                        <tr><th>Fecha de contacto : </th><td><rich:calendar value="#{managerFechaContacto.fechaContacEdit}" /></td></tr>
                        <tr><th colspan="2"><a4j:commandButton id="btnGuardarFechContac" value="Guardar" oncomplete="#{managerFechaContacto.oncomplete}" actionListener="#{managerFechaContacto.editarFechaContacto}" reRender="pReporte" /></th></tr>
                    </table>
                </h:form>
            </rich:modalPanel>
            <!-- MODALPANEL EDICIÓN DE OBSERVACIÓN -->
            <rich:modalPanel id="mpObservacion" autosized="true">
                <f:facet name="header">
                    <h:outputText value="Observación"/>
                </f:facet>

                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpObservacion')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="form3">
                    <h:panelGroup layout="block" styleClass="scrolls">

                        <f:param id="consulta_id_edit" value="#{managerFechaContacto.w_consulta_id}" />
                        <h:inputTextarea value="#{managerFechaContacto.w_obs_consulta_editar}" style="width:190px;height:100px" id="txtaEdicionConsulta" />

                    </h:panelGroup>
                    <a4j:commandButton image="/Imagenes/actions/filesave.png"
                                       actionListener="#{managerFechaContacto.actualizarObsConsulta}" oncomplete="#{managerFechaContacto.oncomplete}" reRender="pReporte"/>
                </h:form>

            </rich:modalPanel>
            <!-- FIN MODALPANEL EDICIÓN DE OBSERVACIÓN -->


           <!-- MODAL PARA REPORTE .. -->
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
                                                     style="width : 700px; height: 420px;"
                                                     element="object" standby="cargando..."
                                                     createContent="#{managerFechaContacto.cargarReporte}"
                                                     mimeType="application/pdf"
                                                     />
                                </rich:panel>
                            </td>
                        </tr>
                    </table>
                </h:form>
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
                    <rich:dataTable id="dtConsultaDetalle" value="#{managerFechaContacto.listarDetalle}" var="lista" width="550">
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

        </f:view>
    </body>
</html>