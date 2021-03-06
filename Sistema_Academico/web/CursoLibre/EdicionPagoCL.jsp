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
                                            <h:outputText value="C�digo" />
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
                                                           reRender="formDeuda,tabladeudas,mpEditar"
                                                           />
                                    </rich:column>

                                    <rich:subTable id="subtable" value="#{mat.matriculas}" var="det"
                                                   onRowMouseOver="this.style.backgroundColor='#F8F8F8'"
                                                   onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                   rendered="#{mat.verDetalle}">
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Fecha de Matr�cula" />
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
                                                <h:outputText value="Secci�n"/>
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
                                                <h:outputText value="Fecha de Prorroga" />
                                            </f:facet>
                                            <h:outputText value="#{det.m_fechaProrroga}">
                                                <f:convertDateTime pattern="dd-MM-yyyy"/>
                                            </h:outputText>
                                        </rich:column>
                                        
                                        <f:param id="alutar_id" value="#{det.m_alutarId}" />
                                        <f:param id="matTipo" value="#{det.m_mat_tipo}" />
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

                                       <%-- <rich:column style="text-align: center;">
                                            <f:facet name="header">
                                                <h:outputText value="Modificar Monto"/>
                                            </f:facet>
                                            <f:param id="p_mat_id_delete" value="#{det.m_mat_id}"/>
                                            <f:param id="p_alu_id_delete" value="#{mat.m_alu_id}"/>
                                            <a4j:commandButton image="#{det.sImagenMod}"
                                                               disabled="#{det.sModificable}"
                                                               title="Cambiar Monto"
                                                               actionListener="#{managerCLMatricula.cambiarMonto}"
                                                               oncomplete="#{managerCLMatricula.oncomplete}"
                                                               reRender="amMensaje"/>
                                        </rich:column>--%>
                                        
                                        <!--EDITAR FECHA PRORROGA-->
                           <rich:column style="text-align: center;">
                                <f:facet name="header">
                                <h:outputText value="Editar" />
                                </f:facet>
                                <f:param id="alutar_monto" value="#{det.m_monto_total}"/>
                                <f:param id="alutar_pago" value="#{det.m_fechaPago}"/>
                                <f:param id="alutar_prorroga" value="#{det.m_fechaProrroga}"/>
                               
                                <a4j:commandButton image="#{det.sImagenMod}"
                                                   style="border-width: 0px;"
                                                   title="Editar estructura"
                                                   disabled="#{det.sModificable}"
                                                   reRender="mpEditar,fromEditar,mod_monto, mod_pago, mod_prorroga"
                                                   actionListener="#{managerCLMatricula.salvaEditar}"
                                                   oncomplete="Richfaces.showModalPanel('mpEditar',{top:200})"/>
                            </rich:column>
                                         <!--CERRAMOS EDITAR FECHA PRORROGA-->
                                        
                                        <rich:column style="text-align: center;">
                                            <f:facet name="header">
                                                <h:outputText value="Cerrar Pago"/>
                                            </f:facet>
                                            <f:param id="p_mat_id_cerrar" value="#{det.m_mat_id}"/>
                                            <h:graphicImage  value="/Imagenes/actions/cerrado.png" rendered="#{not det.cerrable}"/>
                                            <h:commandButton rendered="#{det.cerrable}"
                                                image="/Imagenes/actions/abierto.png"
                                                title="Cerrar Pago"
                                                actionListener="#{managerCLMatricula.cerrarPago}"
                                                onclick="javascript:return (confirm('�Esta realmente seguro de Cerrar el pago?'));"/>
                                        </rich:column>
                                         
                                       <rich:column style="text-align: center;">
                                                <f:facet name="header">
                                                    <h:outputText value="Habilitar Matricula"/>
                                                </f:facet>
                                            
                                                <f:param id="p_mat_id_habilitar" value="#{det.m_mat_id}"/>
                                                <h:graphicImage  value="/Imagenes/actions/desactivar.png" rendered="#{not det.habilitar}"/>
                                                <h:commandButton rendered="#{det.habilitar}"
                                                    image="/Imagenes/actions/activar.png"
                                                    title="Habilitar matricula"
                                                    actionListener="#{managerCLMatricula.HabilitarMatricula}"
                                                    onclick="javascript:return (confirm('�Esta realmente seguro de habilitar la matricula?'));"/>
                                        </rich:column>

                                    </rich:subTable>
                                </rich:dataTable>
                            </td>
                        </tr>
                    </table>
                </h:form>

            </rich:panel>


            <!-- Panel Modal::: REALIZAR MATRICULA -->
            <rich:modalPanel id="mpMatricula" autosized="true" width="900" zindex="4000">
                <f:facet name="header">
                    <h:outputText value="Matricular"/>
                </f:facet>
                <f:facet name="controls">

                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mpMatricula')" title="Cerrar"/>
                    </h:panelGroup>

                </f:facet>


                <h:form id="form2">
                    <div align="right">
                        <a4j:commandButton image="/Imagenes/actions/filesave.png" id="btngrabar"
                                           title="Matricular" style="align:right"
                                           actionListener="#{managerCLMatricula.MatricularSeleccionados}"
                                           action="#{managerCLMatricula.SeleccionarEdicionPago}"
                                           oncomplete="#{managerCLMatricula.oncomplete}"
                                           reRender="tablaMaster,paginacion" />
                    </div>
                    <br/><rich:separator/>
                    <h:panelGrid columns="2">
                        <h:outputText value="Codigo" style="font-size: 12px; font-weight: bold;"/>
                        <h:panelGroup>
                            <h:outputText id="codigoAL" value="#{managerCLMatricula.i_alu_codigo}"/>
                            <h:inputHidden id="idAL" value="#{managerCLMatricula.i_alu_id}"/>
                        </h:panelGroup>
                        <h:outputText value="Alumno Libre" style="font-size: 12px; font-weight: bold;"/>
                        <h:panelGroup>
                            <h:outputText id="nombreAL" value="#{managerCLMatricula.i_alu_nombre}"/>
                            <rich:spacer width="20"/>
                            <f:param id="mmm" value="#{managerCLMatricula.i_alu_id}"/>
                            <a4j:commandLink   id="btninfo" value="#{managerCLMatricula.deu_mostrarmensajedeu}"
                                               actionListener="#{managerCLMatricula.mostrarDeudaAlumno}" style="font-style:bold;font-size:14px;color:red"
                                               oncomplete="#{managerCLMatricula.oncomplete}"
                                               reRender="formDeuda,tabladeudas" />
                        </h:panelGroup>
                        <h:outputText value="Area" style="font-size: 12px; font-weight: bold;"/>
                        <h:selectOneMenu id="iArea" value="#{managerCLMatricula.i_are_id}">
                            <f:selectItems value="#{managerCLMatricula.i_areas}"/>
                            <a4j:support event="onchange" reRender="iModulo, iEstPago" />
                        </h:selectOneMenu>
                        <h:outputText value="Modulo" style="font-size: 12px; font-weight: bold;"/>
                        <h:selectOneMenu id="iModulo" value="#{managerCLMatricula.i_mod_id}">
                            <f:selectItems value="#{managerCLMatricula.i_modulos}"/>
                            <a4j:support event="onchange" reRender="iCurso, iEstPago,resultados" actionListener="#{managerCLMatricula.MostrarCursos}"/>
                        </h:selectOneMenu>

                        <h:outputText value="Curso" style="font-size: 12px; font-weight: bold;"/>
                        <h:selectOneMenu id="iCurso" value="#{managerCLMatricula.i_cur_id}">
                            <f:selectItems value="#{managerCLMatricula.i_cursos}"/>
                            <a4j:support event="onchange" reRender="resultados" actionListener="#{managerCLMatricula.MostrarCursos}"/>
                        </h:selectOneMenu>

                        <h:outputText value="Tipo de Pago" style="font-size: 12px; font-weight: bold;"/>
                        <h:selectOneMenu id="iEstPago" value="#{managerCLMatricula.i_estpag_id}">
                            <f:selectItems value="#{managerCLMatricula.i_estructuras}"/>
                            <a4j:support event="onchange" reRender="pgEstructuras" actionListener="#{managerCLMatricula.cargarEstructuraPagoDetalle}" />
                        </h:selectOneMenu>

                    </h:panelGrid>
                    <h:panelGroup id="controles">
                        <h:outputText value="El pago por el curso se llevara a cabo cada "/> &nbsp;&nbsp;
                        <h:inputText id="iDiaCobrar" value="#{managerCLMatricula.w_diaCobrar}" style="width:60px"  maxlength="2" onkeypress="return validar(event);" /> &nbsp;&nbsp;
                        <h:outputText value="dias,  Iniciando el "/>
                        <rich:calendar value="#{managerCLMatricula.fecha_inicio}" datePattern="dd/MM/yyyy" id="iFechaInicio" style="width:160px;" />
                        <br />
                        <h:outputText value="#{managerCLMatricula.w_textoPago}" id="iTextoPago"/> &nbsp;&nbsp;&nbsp;
                        <a4j:commandButton image="/Imagenes/actions/down.png"
                                           actionListener="#{managerCLMatricula.mostrarEstructura}"
                                           reRender="controles,pgEstructuras" rendered="#{managerCLMatricula.w_ocultar_boton}"/>

                        <a4j:commandButton image="/Imagenes/actions/up.png"
                                           actionListener="#{managerCLMatricula.ocultarEstructura}"
                                           reRender="controles,pgEstructuras" rendered="#{managerCLMatricula.w_ocultar_estructura}" />
                    </h:panelGroup>
                    <h:panelGroup id="pgEstructuras">

                        <rich:dataTable id="dtEstructuraDetalle" value="#{managerCLMatricula.listaEstructuraDetalle}"
                                        var="estructura" width="500" rendered="#{managerCLMatricula.w_ocultar_estructura}" >
                            <rich:column>
                                <f:facet name="header"><h:outputText value="Nro" /> </f:facet>
                                <h:outputText value="#{estructura.estpagdetOrden}" />
                            </rich:column>

                            <rich:column>
                                <f:facet name="header"><h:outputText value="Concepto" /> </f:facet>
                                <h:outputText value="#{estructura.estpagdetNombre}" />
                            </rich:column>

                            <rich:column>
                                <f:facet name="header"><h:outputText value="Monto" /> </f:facet>
                                <h:outputText value="#{estructura.estpagdetMonto}" />
                            </rich:column>
                            <rich:column>
                                <f:facet name="header"><h:outputText value="fecha" /> </f:facet>
                                <h:outputText value="#{estructura.estpagdetFechaPago}">
                                    <f:convertDateTime pattern="dd/MM/yyyy" />
                                </h:outputText>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header"><h:outputText value="" /> </f:facet>
                                <h:selectBooleanCheckbox value="#{estructura.est_verificar}" />
                            </rich:column>

                        </rich:dataTable>
                    </h:panelGroup>
                    <br/>

                    <h:panelGroup id="resultados">
                        <h:outputText value="#{managerCLMatricula.mensaje_error}" style="color: #FF0000; font-weight: bold;" rendered="#{managerCLMatricula.verMensaje}"/>
                        <h:outputText value="#{managerCLMatricula.i_modulo}" style="font-weight: bold;" rendered="#{managerCLMatricula.verCursos}"/>
                        <br/>
                        <div style="overflow-x:hidden; overflow-y:auto; height: 260px; width: 800px;">
                            <rich:dataTable id="tablaCursos"
                                            onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                            onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                            cellpadding="0"
                                            cellspacing="0"
                                            width="100%"
                                            border="0"
                                            value="#{managerCLMatricula.secciones}"
                                            var="sec" rendered="#{managerCLMatricula.verCursos}">
                                <rich:column>
                                    <f:facet name="header">
                                        <h:outputText value="Curso"/>
                                    </f:facet>
                                    <h:outputText value="#{sec.i_curso}"/>
                                </rich:column>
                                <rich:column>
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
                                        <h:outputText value="Matriculados"/>
                                    </f:facet>
                                    <p align="center">
                                        <h:outputText id="nroMat" value="#{sec.i_nro_mat}" style="#{sec.i_nro_mat_style}"/>
                                        <rich:toolTip for="nroMat" value="#{sec.i_tt_message}" rendered="#{sec.i_ver_message}"/>
                                    </p>
                                </rich:column>

                                <rich:column>
                                    <f:facet name="header">
                                        <h:outputText value="Pre-Matriculados"/>
                                    </f:facet>
                                    <p align="center">
                                        <h:outputText id="nropreMat" value="#{sec.pre_i_nro_mat}" style="#{sec.i_nro_mat_style}"/>
                                        <rich:toolTip for="nropreMat" value="#{sec.i_tt_message}" rendered="#{sec.i_ver_message}"/>
                                    </p>
                                </rich:column>
                                <% /*
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
                                    <f:param id="p_sec_id" value="#{sec.i_sec_id}"/>
                                    <span>
                                    <h:outputText value="#{sec.i_monto_pagar}" style="align: left;" rendered="#{sec.i_ver}"/>
                                    <h:inputText value="#{sec.i_monto_editar}" style="font-size: 10px; width: 40px;" rendered="#{sec.i_editar}"/>
                                    <a4j:commandButton image="/Imagenes/actions/klipper_dock.png"
                                    title="Editar"
                                    actionListener="#{managerCLMatricula.EditarMonto}"
                                    reRender="resultados"
                                    style="align: right;"
                                    rendered="#{sec.i_ver}"/>
                                    <a4j:commandButton image="/Imagenes/actions/filesave.png"
                                    title="Grabar"
                                    actionListener="#{managerCLMatricula.GuardarMonto}"
                                    reRender="resultados"
                                    style="align: right;"
                                    rendered="#{sec.i_editar}"/>
                                    <a4j:commandButton image="/Imagenes/actions/fileclose.png"
                                    title="Cancelar"
                                    actionListener="#{managerCLMatricula.CancelarMonto}"
                                    reRender="resultados"
                                    style="align: right;"
                                    rendered="#{sec.i_editar}"/>
                                    </span>
                                            </rich:column> */%>
                                <rich:column style="text-align: center;">
                                    <f:facet name="header">
                                        <a4j:commandLink value="Matricular"
                                                         actionListener="#{managerCLMatricula.SeleccionMultiple}"
                                                         reRender="resultados"
                                                         rendered="#{managerCLMatricula.verCursos}" style="font-size: 11px;"/>
                                    </f:facet>
                                    <h:selectBooleanCheckbox value="#{sec.i_agregar}" />
                                </rich:column>
                            </rich:dataTable>
                        </div>

                    </h:panelGroup>


                </h:form>
            </rich:modalPanel>


            <!-- Panel Modal::: ANULAR MATRICULA -->
            <rich:modalPanel id="mp2" width="350" height="100" autosized="true" zindex="4000">
                <f:facet name="header">
                    <h:outputText value="Anular Matricula"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mp2')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="form4">
                    <table width="100%" cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            <td align="center" valign="middle" colspan="2">
                                <h:outputText id="amMensaje" value="#{managerCLMatricula.mensajeEliminar}" style="font-size: 12px; font-weight: bold;"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" height="20px"></td>
                        </tr>
                        <tr>
                            <td align="center" valign="middle">
                                <a4j:commandButton image="/Imagenes/actions/button_accept.png"
                                                   title="Aceptar"
                                                   actionListener="#{managerCLMatricula.AnularMatricula}"
                                                   action="#{managerCLMatricula.SeleccionarEdicionPago}"
                                                   oncomplete="#{managerCLMatricula.oncomplete}"
                                                   reRender="tablaMaster,paginacion"/>
                            </td>
                            <td align="center" valign="middle">
                                <a4j:commandButton image="/Imagenes/actions/button_cancel.png"
                                                   title="Cancelar"
                                                   oncomplete="Richfaces.hideModalPanel('mp2')"/>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>



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
            
            <rich:modalPanel id="mpEdicion" width="250" height="200" autosized="true" zindex="4000">
                <f:facet name="header">
                    <h:outputText value="Modificar fecha"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mpEdicion')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <a4j:form id="frmProrroga">
                    <rich:calendar value="#{managerCLMatricula.w_fechaProrroga}" datePattern="dd/MM/yyyy" /><br /><br />
                    <h:outputText value="Indique observaci�n" /><br />
                    <h:inputTextarea value="#{managerCLMatricula.w_observacionFecha}" cols="6" style="width:230px" />
                    <div style="margin-left: 200px; width: 100% " >
                        <a4j:commandButton image="/Imagenes/actions/filesave.png" actionListener="#{managerCLMatricula.guardarProrroga}"
                                           reRender="tabladeudas,btninfo" oncomplete="#{managerCLMatricula.oncomplete}"/>
                    </div>
                </a4j:form>
            </rich:modalPanel>

            <rich:modalPanel id="mpEdicionMonto" width="250" height="100" autosized="true" zindex="4000">
                <f:facet name="header">
                    <h:outputText value="Modificar Monto"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mpEdicionMonto')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <a4j:form id="frmMonto">
                    <h:outputText value="Monto Nuevo: " /><br />
                    <h:inputText value="#{managerCLMatricula.w_montoNuevo}" style="width: 230px;"/>
                    <div style="margin-left: 200px; width: 100% " >
                        <a4j:commandButton image="/Imagenes/actions/filesave.png" actionListener="#{managerCLMatricula.guardarAlutarMonto}"
                                           action="#{managerCLMatricula.SeleccionarEdicionPago}"
                                           reRender="tablaMaster, subtable" oncomplete="#{managerCLMatricula.oncomplete}"/>
                    </div>
                </a4j:form>
            </rich:modalPanel>
                    
                    <!--EDICION DE FECHA PRORROGA-->
                    <rich:modalPanel  id="mpEditar" minWidth="350"
                              autosized="true" zindex="2000">
                <f:facet name="header">
                    <h:outputText value="Editar datos del alumno Tarifa" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png"
                                    style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('mpEditar')"
                                    title="Cerrar"/>
                </f:facet>
                <center>
                    <h:form id="fromEditar">
                        <table width="100%">
                            <tr>
                                <td width="40%"></td>
                                <td align="right">
                                    <a4j:commandButton
                                        image="/Imagenes/actions/filesave.png"
                                        title="Guardar"
                                        action="#{managerCLMatricula.editar}"
                                        reRender="tablaMaster, paginacion, subtable"
                                        oncomplete="#{managerCLMatricula.oncomplete}" />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3"><hr width="100%"/></td>
                            </tr>
                            <tr style="height: 30px;">
                                <td><h:outputLabel styleClass="label-mpNuevoEstPagAlu" value="Monto:" /></td>
                                <td><h:inputText id="mod_monto" style="width : 180px;"
                                             value="#{managerCLMatricula.modal_monto}" />
                                </td>
                            </tr>
                            <tr style="height: 30px;">
                                <td><h:outputLabel styleClass="label-mpNuevoEstPagAlu" value="Fecha de pago:" /></td>
                                <td><rich:calendar id="mod_pago" showWeeksBar="false"
                                               datePattern="dd-MM-yyyy"  
                                               value="#{managerCLMatricula.modal_pago}"/>
                                </td>
                            </tr>
                            <tr style="height: 30px;">
                                <td><h:outputLabel styleClass="label-mpNuevoEstPagAlu" value="Fecha de Prorroga:" /></td>
                                <td><rich:calendar id="mod_prorroga" datePattern="dd-MM-yyyy" showWeeksBar="false" style="width : 200px;"
                                               value="#{managerCLMatricula.modal_prorroga}" />
                                </td>
                            </tr>
                        </table>
                    </h:form>
                </center>
            </rich:modalPanel>
                    
                    
            <jsp:include flush="false" page="modal/ModalAlumno.jsp" />

        </f:view>

    </body>
</html>