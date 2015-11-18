<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Personalizar Pago por Alumno</title>
        <script>
            function validar(e) {
                tecla = (document.all)?e.keyCode:e.which;
                if (tecla==8 || tecla==0) return true;
                patron = /\d/;
                te = String.fromCharCode(tecla);
                //alert(tecla);
                return patron.test(te);
            }
        </script>
    </head>
    <body>
        <f:view>
            <jsp:include page="../TablaSistema/Menu.jsp"/>
            <rich:panel>
                <h:form id="form1">
                    <table style="font-size:13px; font-family:verdana; width: 100%">
                        <tr>
                            <td width="100%" colspan="10">
                                <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                    <tr>
                                        <td align="left"><h:outputText value="MATRICULA DE ALUMNOS" style="font-weight: bold;"/></td>
                                        <td align="right">
                                            <rich:spacer width="8px"/>

                                            
                                            <rich:spacer width="8px"/>


                                            <rich:spacer width="8px"/>

                                            <a4j:commandButton image="/Imagenes/actions/viewmag.png"
                                                               action="#{managerCLMatricula.SeleccionarEdicionPago}"
                                                               title="Buscar" reRender="form3" />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="10"><hr size="1"></td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputText value="Codigo :"/></td>
                            <td colspan="9" align="left"><h:inputText id="b_codigo" value="#{managerCLMatricula.b_codigo}" style="width: 200px;"/></td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputText value="Ap. Paterno :"/></td>
                            <td colspan="9" align="left"><h:inputText id="b_appaterno" value="#{managerCLMatricula.b_appaterno}" style="width: 200px;"/></td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputText value="Ap. Materno :"/></td>
                            <td colspan="9" align="left"><h:inputText id="b_apmaterno" value="#{managerCLMatricula.b_apmaterno}" style="width: 200px;"/></td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputText value="Nombres :"/></td>
                            <td colspan="9" align="left"><h:inputText id="b_nombres" value="#{managerCLMatricula.b_nombres}" style="width: 200px;"/></td>
                        </tr>
                        <tr>
                            <td colspan="10" height="10px"></td>
                        </tr>
                    </table>
                </h:form>
                <h:form id="form3">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-size:10px; font-family:verdana">
                        <tr>
                            <td colspan="10" valign="top" align="center">
                                <rich:datascroller id="paginacion" for="tablaMaster"
                                                   align="right" maxPages="10"/>
                                <rich:dataTable id="tablaMaster" value="#{managerCLMatricula.matriculas}"
                                                onRowMouseOver="this.style.backgroundColor='#D7D7D7'"
                                                onRowMouseOut="this.style.backgroundColor='#FFFFFF'"
                                                cellpadding="0" cellspacing="0"
                                                width="100%" rows="10" var="mat">

                                    <rich:column style="text-align: center;" colspan="1">
                                        <f:facet name="header">
                                            <h:outputText value="Código" />
                                        </f:facet>
                                        <h:outputText value="#{mat.m_alu_codigo}"/>
                                    </rich:column>

                                    <rich:column colspan="4">
                                        <f:facet name="header">
                                            <h:outputText value="Alumno" />
                                        </f:facet>
                                        <h:outputText value="#{mat.m_alu_appaterno} #{mat.m_alu_apmaterno} #{mat.m_alu_nombres}"/>
                                    </rich:column>

                                    <rich:column style="text-align: center;" colspan="2">
                                        <f:facet name="header">
                                            <h:outputText value="Ver" />
                                        </f:facet>
                                        <f:param id="m_alu_id" value="#{mat.m_alu_id}"/>
                                        <a4j:commandButton image="#{mat.m_imagen_mostrar}" title="#{mat.m_texto_mostrar}"
                                                           actionListener="#{managerCLMatricula.mostrarDetalle}"
                                                           reRender="tablaMaster, paginacion"/>
                                    </rich:column>

                                    <rich:column style="text-align: center;" colspan="2">
                                        <f:facet name="header">
                                            <h:outputText value="Ver Deudas" />
                                        </f:facet>
                                        <f:param id="mmm" value="#{mat.m_alu_id}"/>
                                        <a4j:commandButton image="#{mat.m_imagen_deuda}"
                                                           actionListener="#{managerCLMatricula.mostrarDeudaAlumno}"
                                                           oncomplete="#{managerCLMatricula.oncomplete}"
                                                           reRender="formDeuda,tabladeudas"
                                                           />
                                    </rich:column>

                                    <rich:subTable id="subtable" value="#{mat.matriculas}" var="det"
                                                   onRowMouseOver="this.style.backgroundColor='#F8F8F8'"
                                                   onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                   rendered="#{mat.verDetalle}">
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Fecha de Matrícula" />
                                            </f:facet>
                                            <h:outputText value="#{det.m_mat_fecha}">
                                                <f:convertDateTime pattern="dd-MM-yyyy hh:mm a"/>
                                            </h:outputText>
                                        </rich:column>

                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Tipo"/>
                                            </f:facet>
                                            <h:outputText value="#{det.m_tipo}"/>
                                        </rich:column>

                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Taller"/>
                                            </f:facet>
                                            <h:outputText value="#{det.m_taller}" escape="false"/>
                                        </rich:column>

                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Sección"/>
                                            </f:facet>
                                            <h:outputText value="#{det.m_seccion}" escape="false"/>
                                        </rich:column>

                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Fecha de Inicio"/>
                                            </f:facet>
                                            <h:outputText value="#{det.m_inicio}" escape="false"/>
                                            <rich:toolTip>
                                                <span style="white-space:nowrap">
                                                    <h:outputText value="#{det.w_estructurapago}"/>
                                                </span>
                                            </rich:toolTip>
                                        </rich:column>
                                        
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Fecha de Pago" />
                                            </f:facet>
                                            <h:outputText value="#{det.m_fechaPago}">
                                                <f:convertDateTime pattern="dd-MM-yyyy"/>
                                            </h:outputText>
                                        </rich:column>

                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Monto"/>
                                            </f:facet>
                                            <h:outputText value="#{det.m_monto_total}" escape="false"/>
                                            <rich:toolTip>
                                                <span style="white-space:nowrap">
                                                    <h:outputText value="#{det.m_monto_total}"/>
                                                </span>
                                            </rich:toolTip>
                                        </rich:column>
                                    </rich:subTable>
                                </rich:dataTable>
                            </td>
                        </tr>
                    </table>
                </h:form>
                            <rich:modalPanel id="mpDeuda" width="600" height="400" autosized="true" zindex="4000">
                <f:facet name="header">
                    <h:outputText value=" Historial de Deuda"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mpDeuda')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>



                <h:form id="formDeuda">
                    <h:panelGrid columns="2">
                        <h:outputText value="CODIGO" style="font-size: 12px; font-weight: bold;"/>
                        <h:outputText  id= "valor1" value="#{managerCLMatricula.deu_alu_codigo}" style="font-size: 12px; font-weight: bold;"/>
                        <h:outputText value="NOMBRE" style="font-size: 12px; font-weight: bold;"/>
                        <h:outputText  id= "valor2" value="#{managerCLMatricula.deu_name_lumno}" style="font-size: 12px; font-weight: bold;"/>
                    </h:panelGrid>

                    <h:panelGroup rendered="#{managerCLMatricula.cantCursoDeuda == 0}" styleClass="message error">
                        <h:outputText value="No tiene Deudas"/>
                    </h:panelGroup>
                    <h:panelGroup rendered="#{managerCLMatricula.cantCursoDeuda != 0}">
                        <% /*
                            <rich:dataTable id="tabladeudas"
                            onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                            onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                            cellpadding="0"
                            cellspacing="0"
                            width="100%"
                            border="0"
                            value="#{managerCLMatricula.listDeudas}"
                            var="deud">
                            <rich:column>
                            <f:facet name="header">
                            <h:outputText value="Curso"/>
                            </f:facet>
                            <h:outputText value="#{deud.deu_modulo}" style="#{deud.style_tarifa}" />
                            </rich:column>
                            <rich:column>
                            <f:facet name="header">
                            <h:outputText value="Concepto"/>
                            </f:facet>
                            <h:outputText value="#{deud.deu_curso}" style="#{deud.style_tarifa}"/>
                            </rich:column>
                            <rich:column>
                            <f:facet name="header">
                            <h:outputText value="Monto a pagar"/>
                            </f:facet>
                            <h:outputText value="#{deud.deu_montopagar}">
                            </h:outputText>
                            </rich:column>
                            <rich:column>
                            <f:facet name="header">
                            <h:outputText value="Monto pagado"/>
                            </f:facet>
                            <h:outputText value="#{deud.deu_montopagado}"/>
                            </rich:column>
                            <rich:column>
                            <f:facet name="header">
                            <h:outputText value="Saldo"/>
                            </f:facet>
                            <h:outputText value="#{deud.deu_saldo}"/>
                       </rich:column>
                       </rich:dataTable> */%>
                        <rich:dataTable id="tabladeudas"
                                        onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                        onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                        cellpadding="0"
                                        cellspacing="0"
                                        width="100%"
                                        border="0"
                                        value="#{managerCLMatricula.listaDeudaAlumno}"
                                        var="deud">
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Curso"/>
                                </f:facet>
                                <h:outputText value="#{deud.deu_modulo}" style="#{deud.alutar_style}" />
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Concepto"/>
                                </f:facet>
                                <h:outputText value="#{deud.deu_curso}" style="#{deud.alutar_style}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Monto a pagar"/>
                                </f:facet>
                                <h:outputText value="#{deud.deu_montopagar}">
                                </h:outputText>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Monto pagado"/>
                                </f:facet>
                                <h:outputText value="#{deud.deu_montopagado}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Saldo"/>
                                </f:facet>
                                <h:outputText value="#{deud.deu_saldo}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Fecha"/>
                                </f:facet>
                                <h:outputText value="#{deud.alumnoTarifa.alutarFechaProrroga}">
                                    <f:convertDateTime pattern="dd / MM / yyyy"/>
                                </h:outputText>
                                <f:param id="alutarId" value="#{deud.alumnoTarifa.alutarId}" />
                                <f:param id="alutarPro" value="#{deud.alutar_fecha}" />
                            </rich:column>
                            <rich:column>
                                <a4j:commandButton image="/Imagenes/actions/editpaste.png" oncomplete="#{managerCLMatricula.oncomplete}"
                                                   reRender="frmProrroga" actionListener="#{managerCLMatricula.abrirProrroga}"/>
                            </rich:column>
                        </rich:dataTable>
                    </h:panelGroup>
                </h:form>

            </rich:modalPanel>

            </rich:panel>
            <jsp:include flush="false" page="modal/ModalAlumno.jsp" />

        </f:view>

    </body>
</html>
