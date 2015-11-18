<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Matricula de Cursos Libres</title>
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
                                        <td align="left"><h:outputText value="MATRICULA DE ALUMNOS POR SECCION" style="font-weight: bold;"/></td>
                                    </tr>

                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="10">
                                <h:form id="form2">
                                    <table width="100%" style="font-size:10px; font-family:verdana;"
                                           cellpadding="0" cellspacing="0" border="0">

                                        <tr style="height: 25px" align="left">
                                            <td class="tdLabel2">Area</td>
                                            <td>
                                                <h:selectOneMenu id="iArea" value="#{managerCLMatriculaSeccion.i_are_id}">
                                                    <f:selectItems value="#{managerCLMatriculaSeccion.i_areas}"/>
                                                    <a4j:support event="onchange" reRender="iModulo" />
                                                </h:selectOneMenu>
                                            </td>
                                        </tr>
                                        <tr style="height: 25px" align="left">
                                            <td class="tdLabel2">M�dulo</td>
                                            <td>
                                                <h:selectOneMenu id="iModulo" value="#{managerCLMatriculaSeccion.i_mod_id}">
                                                    <f:selectItems value="#{managerCLMatriculaSeccion.i_modulos}"/>
                                                    <a4j:support event="onchange" reRender="iCurso, resultados" actionListener="#{managerCLMatriculaSeccion.MostrarCursos}"/>
                                                </h:selectOneMenu>
                                            </td>
                                        </tr>
                                        <tr style="height: 25px" align="left">
                                            <td class="tdLabel2">Curso</td>
                                            <td>
                                                <h:selectOneMenu id="iCurso" value="#{managerCLMatriculaSeccion.i_cur_id}">
                                                    <f:selectItems value="#{managerCLMatriculaSeccion.i_cursos}"/>
                                                    <a4j:support event="onchange" reRender="resultados" actionListener="#{managerCLMatriculaSeccion.MostrarCursos}"/>
                                                </h:selectOneMenu>
                                            </td>
                                        </tr>
                                        <!--<tr style="height: 25px" align="left">
                                            <td class="tdLabel2">Tipo de Pago</td>
                                            <td>
                                              <%--  <h:selectOneMenu id="iEstPago" value="#{managerCLMatriculaSeccion.i_estpag_id}">
                                                    <f:selectItems value="#{managerCLMatriculaSeccion.i_estructuras}"/>
                                                </h:selectOneMenu> --%>
                                            </td>
                                        </tr>-->
                                        <tr style="height: 20px">
                                            <td colspan="2"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">
                                                <h:panelGroup id="resultados">
                                                    <h:outputText value="#{managerCLMatriculaSeccion.mensaje_error}" style="color: #FF0000; font-weight: bold;" rendered="#{managerCLMatriculaSeccion.verMensaje}"/>
                                                    <h:outputText value="#{managerCLMatriculaSeccion.i_modulo}" style="font-weight: bold;" rendered="#{managerCLMatriculaSeccion.verCursos}"/>
                                                    <br/>
                                                    <rich:dataTable id="tablaCursos"
                                                                    onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                                    cellpadding="0"
                                                                    cellspacing="0"
                                                                    width="100%"
                                                                    border="0"
                                                                    value="#{managerCLMatriculaSeccion.secciones}"
                                                                    var="sec" rendered="#{managerCLMatriculaSeccion.verCursos}">
                                                        <rich:column>
                                                            <f:param id="p_sec_detalle" value="#{sec.i_curso}"/>
                                                            <f:facet name="header">
                                                                <h:outputText value="Curso"/>
                                                            </f:facet>
                                                            <h:outputText value="#{sec.i_curso}"/>
                                                        </rich:column>
                                                        <rich:column>
                                                            <f:param id="p_sec_id2" value="#{sec.i_sec_id}"/>
                                                            <f:facet name="header">
                                                                <h:outputText value="Secci�n"/>
                                                            </f:facet>
                                                            <h:outputText value="#{sec.i_seccion}"/>
                                                        </rich:column>
                                                        <rich:column>
                                                            <f:facet name="header">
                                                                <h:outputText value="Inicio"/>
                                                            </f:facet>
                                                            <h:outputText value="#{sec.i_inicio}">
                                                                <f:convertDateTime pattern="dd-MM-yyyy"/>
                                                            </h:outputText>
                                                        </rich:column>
                                                        <rich:column>
                                                            <f:facet name="header">
                                                                <h:outputText value="Fin"/>
                                                            </f:facet>
                                                            <h:outputText value="#{sec.i_fin}">
                                                                <f:convertDateTime pattern="dd-MM-yyyy"/>
                                                            </h:outputText>
                                                        </rich:column>

                                                        <rich:column>
                                                            <f:facet name="header">
                                                                <h:outputText value="Matriculados"/>
                                                            </f:facet>
                                                            <p align="center">
                                                                <f:param id="p_sec_id" value="#{sec.i_sec_id}"/>
                                                                <f:param id="p_tal_id" value="#{sec.i_tal_id}"/>
                                                                <a4j:commandLink title="Matriculados"
                                                                     value="#{sec.i_nro_mat}"
                                                                     actionListener="#{managerSeccion.cargarMatriculados}"
                                                                     oncomplete="javascript:Richfaces.showModalPanel('mpMatriculados')"
                                                                     reRender="formMatriculados"/>
                                                                <%--<a4j:commandLink title="Matriculados"
                                                                                 value="#{sec.i_nro_mat}"
                                                                                 actionListener="#{managerCLMatriculaSeccion.cargarMatriculados}"
                                                                                 oncomplete="javascript:Richfaces.showModalPanel('mpMatriculados')"
                                                                                 reRender="formMatriculados" style="#{sec.i_nro_mat_style}"/>
                                                                 <h:outputText id="nroMat" value="#{sec.i_nro_mat}" style="#{sec.i_nro_mat_style}" />--%>

                                                                <%--<rich:toolTip for="toltipMat" value="#{sec.i_tt_message}" rendered="#{sec.i_ver_message}"/>
                                                                <rich:toolTip for="nroMat" value="#{sec.i_tt_message}" rendered="#{sec.i_ver_message}"/>--%>
                                                            </p>
                                                        </rich:column>

                                                        <rich:column>
                                                            <f:facet name="header">
                                                                <h:outputText value="Pre-Matriculados"/>
                                                            </f:facet>
                                                            <p align="center">

                                                                <f:param id="pre_sec_id" value="#{sec.i_sec_id}"/>
                                                                <a4j:commandLink title="Pre-Matriculados"
                                                                                 value="#{sec.pre_i_nro_mat}"
                                                                                 actionListener="#{managerCLMatriculaSeccion.cargarPreMatriculados}"
                                                                                 oncomplete="javascript:Richfaces.showModalPanel('mpPreMatriculados')"
                                                                                 reRender="formPreMatriculados"/>
                                                                <%--<h:outputText id="nroMat" value="#{sec.pre_i_nro_mat}" style="#{sec.i_nro_mat_style}" /> --%>
                                                                <%--<rich:toolTip for="toltipMat" value="#{sec.i_tt_message}" style="#{sec.i_nro_mat_style}" rendered="#{sec.i_ver_message}"/>--%>
                                                            </p>
                                                        </rich:column>

                                                        <rich:column>
                                                            <f:facet name="header">
                                                                <h:outputText value="Monto Base"/>
                                                            </f:facet>
                                                            <h:outputText value="#{sec.i_monto_base}"/>
                                                        </rich:column>
                                                        <rich:column>
                                                            <f:facet name="header">
                                                                <h:outputText value="Monto a Pagar"/>
                                                            </f:facet>

                                                            <span>
                                                                <h:outputText value="#{sec.i_monto_pagar}" style="align: left;" rendered="#{sec.i_ver}"/>

                                                            </span>
                                                        </rich:column>

                                                    </rich:dataTable>

                                                </h:panelGroup>


                                            </td>
                                        </tr>
                                    </table>
                                </h:form>

                            </td>
                        </tr>

                        <tr>
                            <td colspan="10" height="10px"></td>
                        </tr>
                    </table>
                </h:form>


                <rich:modalPanel id="mpMatriculados" width="550"
                                 autosized="true" zindex="3000">
                    <f:facet name="header">
                        <h:outputText value="Matriculados"/>
                    </f:facet>
                    <f:facet name="controls">
                        <h:panelGroup>
                            <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                            onclick="Richfaces.hideModalPanel('mpMatriculados')" title="Cerrar"/>
                        </h:panelGroup>
                    </f:facet>
                    
                    
                    <h:form id="formMatriculados">
                    <table width="100%" style="font-size:12px; font-family:verdana">
                        <tr>
                            <td colspan="2" align="right">
                                <a4j:commandButton image="/Imagenes/actions/filesave.png"
                                                   title="Guardar"
                                                   actionListener="#{managerSeccion.guardarMatriculados}"
                                                   oncomplete="#{managerSeccion.oncomplete}"
                                                   reRender="masterTable, masterScroll"/>
                            </td>
                        </tr>
                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                        <tr>
                            <td class="tdLabel" style="width: 80px;"><h:outputText value="M�dulo"/></td>
                            <td><h:outputText id="matModulo" value="#{managerSeccion.mod_nombre}"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Curso"/></td>
                            <td><h:outputText id="matCurso" value="#{managerSeccion.cur_nombre}"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Taller"/></td>
                            <td><h:outputText id="matTaller" value="#{managerSeccion.talape_nombre}"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Secci�n"/></td>
                            <td><h:outputText value="#{managerSeccion.sec_detalle}"/></td>
                        </tr>
                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Taller destino"/></td>
                            <td>
                                <h:selectOneMenu id="cb_taller" value="#{managerSeccion.tal_dest_id}"
                                                 style="#{managerSeccion.style}"
                                                 disabled="#{managerSeccion.totalMatriculados == 0}">
                                    <f:selectItems value="#{managerSeccion.tal_dest_desc}" />
                                    <a4j:support event="onchange" reRender="cb_sec, cb_estpag" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Seccion destino"/></td>
                            <td>
                                <h:selectOneMenu id="cb_sec" value="#{managerSeccion.sec_dest_id}"
                                                 style="#{managerSeccion.style}"
                                                 disabled="#{managerSeccion.totalMatriculados == 0}">
                                    <f:selectItems value="#{managerSeccion.sec_dest_desc}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Tipo de pago"/></td>
                            <td>
                                <h:selectOneMenu id="cb_estpag" value="#{managerSeccion.estpag_dest_id}"
                                                 style="#{managerSeccion.style}"
                                                 disabled="#{managerSeccion.totalMatriculados == 0}">
                                    <f:selectItems value="#{managerSeccion.estpag_dest_desc}"/>
                                    <a4j:support event="onchange" reRender="cb_estpagdet" />
                                </h:selectOneMenu>
                            </td>
                        </tr>

                        <tr>
                            <td class="tdLabel"><h:outputText value="Monto del pago"/></td>
                            <td>
                                <h:selectOneMenu id="cb_estpagdet" value="#{managerSeccion.estpagdet_dest_id}"
                                                 style="#{managerSeccion.style}"
                                                 disabled="#{managerSeccion.totalMatriculados == 0}">
                                    <f:selectItems value="#{managerSeccion.estpagdet_dest_desc}"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>

                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                        <tr>
                            <td colspan="2" align="center" valign="top" width="100%">
                                <h:panelGroup rendered="#{managerSeccion.totalMatriculados == 0}" styleClass="message error">
                                    <h:outputText value="No hay alumnos matriculados"/>
                                </h:panelGroup>
                                <h:panelGroup rendered="#{managerSeccion.totalMatriculados != 0}" id="resultados">
                                    <h:outputText value="Relaci�n de Alumnos matriculados"/>
                                    <div style="overflow: auto; height: 350px;">
                                        <rich:dataTable id="matriculados" width="650"
                                                        onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                        onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                        value="#{managerSeccion.listaAlumnosMatriculados}" var="alu">
                                            <rich:column style="text-align: center;">
                                                <f:facet name="header">
                                                    <h:outputText value="Nro."/>
                                                </f:facet>
                                                <h:outputText value="#{alu.aluContador}"/>
                                            </rich:column>

                                            <rich:column>
                                                <f:facet name="header">
                                                    <h:outputText value="C�digo"/>
                                                </f:facet>
                                                <h:outputText value="#{alu.aluCodigo}"/>
                                            </rich:column>

                                            <rich:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Apellidos"/>
                                                </f:facet>
                                                <h:outputText value="#{alu.aluAppaterno} #{alu.aluApmaterno}"/>
                                            </rich:column>

                                            <rich:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Nombres"/>
                                                </f:facet>
                                                <h:outputText value="#{alu.aluNombres}"/>
                                            </rich:column>
                                            <rich:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Telefono"/>
                                                </f:facet>
                                                <h:outputText value="#{alu.aluCelular} - #{alu.aluTelefono}"/>
                                            </rich:column>
                                            <rich:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Correo"/>
                                                </f:facet>
                                                <h:outputText value="#{alu.aluMail}"/>
                                            </rich:column>
                                            <rich:column style="text-align: center;">
                                                <f:facet name="header">
                                                    <a4j:commandLink value="Matricular"
                                                         actionListener="#{managerSeccion.SeleccionMultiple}"
                                                         reRender="resultados"
                                                         rendered="#{managerSeccion.totalMatriculados != 0}" style="font-size: 11px;"/>
                                                    
                                                </f:facet>
                                                <h:selectBooleanCheckbox value="#{alu.aluMatriculado}"/>
                                            </rich:column>
                                        </rich:dataTable>
                                    </div>
                                </h:panelGroup>
                            </td>
                        </tr>
                    </table>
                </h:form>
                    
                    
                </rich:modalPanel>


                <%--Muestra listado de alumnos pre matriculados--%>
                <rich:modalPanel id="mpPreMatriculados" width="550"
                                 autosized="true" zindex="3000">
                    <f:facet name="header">
                        <h:outputText value="Pre-Matriculados"/>
                    </f:facet>
                    <f:facet name="controls">
                        <h:panelGroup>
                            <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                            onclick="Richfaces.hideModalPanel('mpPreMatriculados')" title="Cerrar"/>
                        </h:panelGroup>
                    </f:facet>
                    <h:form id="formPreMatriculados">
                        <table width="100%" style="font-size:12px; font-family:verdana">

                            <tr><td colspan="2"><hr width="100%"/></td></tr>
                            <tr>
                                <td class="tdLabel" style="width: 80px;"><h:outputText value="M�dulo"/></td>
                                <td><h:outputText id="prematModulo" value="#{managerCLMatriculaSeccion.mod_nombre}"/></td>
                            </tr>
                            <tr>
                                <td class="tdLabel"><h:outputText value="Curso"/></td>
                                <td><h:outputText id="prematCurso" value="#{managerCLMatriculaSeccion.cur_nombre}"/></td>
                            </tr>
                            <tr>
                                <td class="tdLabel"><h:outputText value="Taller"/></td>
                                <td><h:outputText id="prematTaller" value="#{managerCLMatriculaSeccion.talape_nombre}"/></td>
                            </tr>
                            <tr>
                                <td class="tdLabel"><h:outputText value="Secci�n"/></td>
                                <td><h:outputText id="prematSeccion" value="#{managerCLMatriculaSeccion.sec_detalle}"/></td>
                            </tr>
                            <tr><td colspan="2"><hr width="100%"/></td></tr>

                            <tr><td colspan="2"><hr width="100%"/></td></tr>
                            <tr>
                                <td colspan="4" align="center" valign="top" width="100%">
                                    <h:panelGroup rendered="#{managerCLMatriculaSeccion.totalMatriculados == 0}" styleClass="message error">
                                        <h:outputText value="No hay alumnos matriculados"/>
                                    </h:panelGroup>
                                    <h:panelGroup rendered="#{managerCLMatriculaSeccion.totalMatriculados != 0}">
                                        <h:outputText value="Relaci�n de Alumnos pre-matriculados"/>
                                        <div style="overflow: auto; height: 350px;">
                                            <rich:dataTable id="prematriculados" width="508"
                                                            onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                            onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                            value="#{managerCLMatriculaSeccion.listaAlumnosMatriculados}" var="alu">
                                                <rich:column style="text-align: center;">
                                                    <f:facet name="header">
                                                        <h:outputText value="Nro."/>
                                                    </f:facet>
                                                    <h:outputText value="#{alu.aluContador}"/>
                                                </rich:column>

                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="C�digo"/>
                                                    </f:facet>
                                                    <h:outputText value="#{alu.aluCodigo}"/>
                                                </rich:column>

                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Apellidos"/>
                                                    </f:facet>
                                                    <h:outputText value="#{alu.aluAppaterno} #{alu.aluApmaterno}"/>
                                                </rich:column>

                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Nombres"/>
                                                    </f:facet>
                                                    <h:outputText value="#{alu.aluNombres}"/>
                                                </rich:column>

                                            </rich:dataTable>
                                        </div>
                                    </h:panelGroup>
                                </td>
                            </tr>
                        </table>
                    </h:form>
                </rich:modalPanel>
                <%-- Cerramos listado de alumnos pre matriculados --%>

            </rich:panel>

            <jsp:include flush="false" page="modal/ModalAlumno.jsp" />

        </f:view>
    </body>
</html>