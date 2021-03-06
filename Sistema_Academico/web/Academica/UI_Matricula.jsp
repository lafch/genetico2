<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>.:Matricula:.</title>
    </head>
    <f:view>

        <body>
            <jsp:include page="../TablaSistema/Menu.jsp"></jsp:include>
            <rich:panel style=" width : 100%;" >
                <f:facet name="FACULTADES">
                </f:facet>
                <h:form id="form1" style="width:100%">
                    <table width="100%" style="font-size:10px; font-family:verdana" cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            <td width="20%" colspan="2" ><strong>MATRICULA</strong></td>
                            <td width="30%">
                            </td>
                            <td width="30%"></td>
                            <td width="30%" colspan="4" align="right">
                                <h:graphicImage value="/Imagenes/actions/fileprint.png" title="Imprimir Listado"/></td>
                            <td align=right colspan="3" width="50%">

                                <a4j:commandButton id="buscar" value="" action="#{mBMatricula.buscarEstudiantes}" image="/Imagenes/actions/viewmag.png" title="Buscar" reRender="tablaMaster"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="10"><hr width="100%" size="1"></td>
                        </tr>

                        <tr><td></td>
                        </tr>
                        <tr>
                            <td width="10%">C�digo:</td>
                            <td width="30%">
                                <h:inputText value="#{mBMatricula.w_codigo}" style="width : 180px;"/>
                            </td>
                            <td width="20%"></td>
                            <td>Facultad:</td>
                            <td>
                                <h:selectOneMenu id="FacuBuscar" value="#{mBMatricula.w_facId}"  style="width : 180px;">
                                    <f:selectItems value="#{mBMatricula.cboFacultad}" />
                                    <a4j:support event="onchange"  reRender="EspeBuscar"/>
                                </h:selectOneMenu>
                            </td>
                            <td width="20%" colspan="2"></td>
                        </tr>
                        <tr>
                            <td width="10%">Ap. Paterno:</td>
                            <td width="30%">
                                <h:inputText value="#{mBMatricula.w_paterno}" style="width : 180px;"/>
                            </td>
                            <td width="20%"></td>
                            <td >Especialidad:</td>
                            <td>
                                <h:selectOneMenu id="EspeBuscar" value="#{mBMatricula.w_espId}"  style="width : 180px;">
                                    <f:selectItems value="#{mBMatricula.cboEspecialidad}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%">Ap. Materno:</td>
                            <td><h:inputText value="#{mBMatricula.w_materno}" style="width : 180px;"/></td>
                            <td width="20%"></td>
                            <td>Semestre:</td>
                            <td>
                                <h:selectOneMenu id="SemBuscar" value="#{mBMatricula.w_semId}"  style="width : 180px;">
                                    <f:selectItems value="#{mBMatricula.cboSemestre}" />
                                </h:selectOneMenu>
                            </td>
                            <td width="20%" colspan="2"></td>
                        </tr>
                        <tr>
                            <td width="10%">Nombres:</td>
                            <td><h:inputText value="#{mBMatricula.w_nombres}" style="width : 180px;"/></td>
                            <td width="20%" colspan="4"></td>
                        </tr>

                        <tr>
                            <td colspan="10" width="100%">
                                <hr width="100%" size="1">
                            </td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                        </tr>
                    </table>

                    <rich:dataTable id="tablaMaster" rows="10"
                                    onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                    cellpadding="0" cellspacing="0" rowKeyVar="row"
                                    width="100%" border="0" value="#{mBMatricula.listaAlumnos}" var="Alu">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id" />
                            </f:facet>
                            <h:outputText value="#{Alu.acAlumno.id}"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Cod." />
                            </f:facet>
                            <h:outputText value="#{Alu.acAlumno.aluCodigo}"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Apellido P/M y Nombres" />
                            </f:facet>
                            <h:outputText value="#{Alu.acAlumno.aluAppaterno} #{Alu.acAlumno.aluApmaterno} #{Alu.acAlumno.aluNombres}"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Esp. Actual" />
                            </f:facet>
                            <h:outputText value="#{Alu.acAlumno.esp.espNombre}"/>
                        </h:column>
                        <h:column ><f:facet name="header"><h:outputText value="Tipo" /></f:facet>
                            <h:outputText value="#{Alu.w_tipo_alumno}"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header"><h:outputText value="Registra" /></f:facet>
                            <h:outputText value="#{Alu.w_semestre}"/>
                        </h:column>
                        <h:column ><f:facet name="header"><h:outputText value="" /></f:facet>
                            <%/*
                                 * <h:graphicImage value="#{Alu.imagen}"
                                 * style="border-width: 0px;" height="16"
                                 * title="ver cursos"> <a4j:support
                                 * event="onclick" actionListener="#{Alu.Ver}"
                                 * reRender="iId,tablaMaster"/>
                                 * </h:graphicImage>
                                 */%>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="" />
                            </f:facet>
                            <a4j:commandButton rendered="#{Alu.w_ver_deuda}" image="/Imagenes/actions/warning.png" title="Ver Detalles" 
                                               action="#{mBMatricula.verDetalleDeuda}" oncomplete="#{mBMatricula.oncomplete}" reRender="formDeuda">
                                <f:setPropertyActionListener target="#{mBMatricula.p_indice_alumno}" value="#{row}" />
                            </a4j:commandButton>
                            <a4j:commandButton rendered="#{!Alu.w_ver_deuda and Alu.w_visualizar}" image="#{Alu.w_imagen}"  title="Rectificar Matricula" action="#{mBMatricula.abrirMatricula}" oncomplete="#{mBMatricula.oncomplete}" reRender="formMatricula">
                                <f:setPropertyActionListener target="#{mBMatricula.p_indice_alumno}" value="#{row}" />
                            </a4j:commandButton>

                        </h:column>
                        <h:column>
                            <f:facet name="header"><h:outputText value="" /></f:facet>
                            <h:graphicImage value="/Imagenes/actions/fileprint.png" rendered="#{Alu.w_ver_reporte}" style="border-width: 0px;cursor: pointer" title="Imprimir Ficha">
                                <a4j:support event="onclick" action="#{mBMatricula.ImprimirFicha}" oncomplete="#{mBMatricula.oncomplete}" reRender="titulo,reporte">
                                    <f:setPropertyActionListener  target="#{mBMatricula.p_mat_id}" value="#{Alu.acMatricula.id}"/>
                                </a4j:support>
                            </h:graphicImage>
                        </h:column>
                        <h:column>
                            <f:facet name="header"><h:outputText value="" /></f:facet>
                            <h:graphicImage value="/Imagenes/actions/fileprint.png" rendered="#{Alu.w_ver_reporte}" style="border-width: 0px;cursor: pointer;" title="Imprimir Consolidado">
                                <a4j:support event="onclick" actionListener="#{mBMatricula.ImprimirConsolidado}" oncomplete="#{mBMatricula.oncomplete}" reRender="titulo,reporte">
                                    <f:setPropertyActionListener  target="#{mBMatricula.p_mat_id}" value="#{Alu.acMatricula.id}"/>
                                </a4j:support>
                            </h:graphicImage>
                        </h:column>
                        <h:column>
                            <f:facet name="header"><h:outputText value="" /></f:facet>
                            <a4j:commandButton action="#{mBAlumnoEstado.abrirReserva}" rendered="#{Alu.w_estado eq '075001'}" image="/Imagenes/actions/reservar.gif" title="Reservar Matricula" reRender="fEstadoAlu" oncomplete="#{mBAlumnoEstado.oncomplete}">
                                <f:setPropertyActionListener target="#{mBAlumnoEstado.p_alu_id}" value="#{Alu.acAlumno.id}"/>
                                <f:setPropertyActionListener target="#{mBAlumnoEstado.p_sem_id}" value="#{mBMatricula.w_semId}"/>
                            </a4j:commandButton><rich:spacer width="5"/>
                            <a4j:commandButton action="#{mBAlumnoEstado.abrirRetiroCiclo}" rendered="#{Alu.w_estado eq '075002'}" image="/Imagenes/actions/retirociclo.png" title="Retiro del Ciclo" reRender="fEstadoAlu" oncomplete="#{mBAlumnoEstado.oncomplete}">
                                <f:setPropertyActionListener target="#{mBAlumnoEstado.p_alu_id}" value="#{Alu.acAlumno.id}"/>
                                <f:setPropertyActionListener target="#{mBAlumnoEstado.p_sem_id}" value="#{mBMatricula.w_semId}"/>
                            </a4j:commandButton><rich:spacer width="5"/>
                            <a4j:commandButton action="#{mBAlumnoEstado.abrirRetiroUniversitario}" rendered="#{Alu.w_estado eq '075003'}" image="/Imagenes/actions/retirouch.png" title="Retiro UCH" reRender="fEstadoAlu" oncomplete="#{mBAlumnoEstado.oncomplete}">
                                <f:setPropertyActionListener target="#{mBAlumnoEstado.p_alu_id}" value="#{Alu.acAlumno.id}"/>
                                <f:setPropertyActionListener target="#{mBAlumnoEstado.p_sem_id}" value="#{mBMatricula.w_semId}"/>
                            </a4j:commandButton>
                            <h:panelGroup rendered="#{!Alu.w_visualizar}">
                                <a4j:commandButton action="#{mBAlumnoEstado.abrirReserva}" image="/Imagenes/actions/reservar.gif" title="Reservar Matricula" reRender="fEstadoAlu" oncomplete="#{mBAlumnoEstado.oncomplete}">
                                    <f:setPropertyActionListener target="#{mBAlumnoEstado.p_alu_id}" value="#{Alu.acAlumno.id}"/>
                                    <f:setPropertyActionListener target="#{mBAlumnoEstado.p_sem_id}" value="#{mBMatricula.w_semId}"/>
                                </a4j:commandButton><rich:spacer width="5"/>
                                <a4j:commandButton action="#{mBAlumnoEstado.abrirRetiroCiclo}" image="/Imagenes/actions/retirociclo.png" title="Retiro del Ciclo" reRender="fEstadoAlu" oncomplete="#{mBAlumnoEstado.oncomplete}">
                                    <f:setPropertyActionListener target="#{mBAlumnoEstado.p_alu_id}" value="#{Alu.acAlumno.id}"/>
                                    <f:setPropertyActionListener target="#{mBAlumnoEstado.p_sem_id}" value="#{mBMatricula.w_semId}"/>
                                </a4j:commandButton><rich:spacer width="5"/>
                                <a4j:commandButton action="#{mBAlumnoEstado.abrirRetiroUniversitario}" image="/Imagenes/actions/retirouch.png" title="Retiro UCH" reRender="fEstadoAlu" oncomplete="#{mBAlumnoEstado.oncomplete}">
                                    <f:setPropertyActionListener target="#{mBAlumnoEstado.p_alu_id}" value="#{Alu.acAlumno.id}"/>
                                    <f:setPropertyActionListener target="#{mBAlumnoEstado.p_sem_id}" value="#{mBMatricula.w_semId}"/>
                                </a4j:commandButton>
                            </h:panelGroup>
                        </h:column>                      
                    </rich:dataTable>
                </h:form>
            </rich:panel>
            <rich:modalPanel id="mp7" width="700" height="490">
                <f:facet name="header">
                    <h:outputText id="titulo" value="#{mBMatricula.tituloReporte}" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp7')" title="Cerrar"/>
                </f:facet>
                <h:form>
                    <table>
                        <tr>
                            <td align="center" valign="middle">
                                <rich:panel>
                                    <a4j:mediaOutput id="reporte" uriAttribute="data" style="width : 650px; height: 420px;" element="object" standby="cargando..." createContent="#{mBMatricula.cargarReporte}" mimeType="application/pdf" value="#{mBMatricula.valorRep}"/>
                                </rich:panel>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>


            <rich:modalPanel  id="mpMatricula" minHeight="580" minWidth="750" height="580" width="750" zindex="2000">
                <f:facet name="header">
                    <h:outputText value="Rectificacion de Matricula" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpMatricula')" title="Cerrar"/>
                </f:facet>
                <h:form id="formMatricula">
                    <div id="contentDiv1">
                        <table width="100%" style="font-size:10px; font-family:verdana" cellpadding="0" cellspacing="0" border="0">
                            <tr>
                                <td align="right" colspan="4">
                                    <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"  actionListener="#{managerMatricula.Matricular}" action="#{managerMatricula.Seleccionar}" reRender="tablaMaster, barra, dupHorario" oncomplete="#{managerMatricula.oncomplete}"/>
                                    <%/*
                                         * <a4j:commandButton
                                         * image="/Imagenes/actions/filesave.png"
                                         * title="Guardar"
                                         * action="#{managerMatricula.Matricular}"
                                         * reRender="tablaMaster, barra"
                                         * oncomplete="#{managerMatricula.oncomplete}"/>
                                         */%>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="5"><hr size="1">
                                </td>
                            </tr>
                            <tr>
                                <td>Semestre:</td>
                                <td><h:outputText id="semestre_vigente1"  style=" width : 180px;" value="#{mBMatricula.objBeanAlumno.acMatricula.semId}" />
                                </td>
                                <td align="right" width="20%" rowspan="4" colspan="4">
                                    <a4j:mediaOutput id="foto1" style="width : 100px;" element="img" cacheable="false" rendered="true" standby="cargando..."  createContent="#{mBMatricula.imagen}"  mimeType="image/png" value="#{mBMatricula.p_indice_alumno}"/>
                                </td>
                            </tr>
                            <tr>
                                <td width="30%" >Codigo:</td>
                                <td width="70%">
                                    <h:outputText id="iCodigo1"  style=" width : 180px;" value="#{mBMatricula.objBeanAlumno.acAlumno.aluCodigo}" />
                                </td>
                            </tr>
                            <tr>
                                <td width="30%" >Apellido Paterno:</td>
                                <td width="70%">
                                    <h:outputText id="iPaterno1"  style=" width : 180px;" value="#{mBMatricula.objBeanAlumno.acAlumno.aluAppaterno}" />
                                </td>
                                <td align="right" width="20%">
                                </td>
                            </tr>
                            <tr>
                                <td width="30%" >Apellido Materno:</td>
                                <td width="70%">
                                    <h:outputText id="iMaterno1"  style=" width : 180px;" value="#{mBMatricula.objBeanAlumno.acAlumno.aluApmaterno}" />
                                </td>
                                <td align="right" width="20%">
                                </td>
                            </tr>
                            <tr>
                                <td width="30%" >Nombres:</td>
                                <td width="70%">
                                    <h:outputText  id="iNombre1"  style=" width : 180px;" value="#{mBMatricula.objBeanAlumno.acAlumno.aluNombres}" />
                                </td>
                                <td align="right" width="20%">
                                </td>
                            </tr>
                            <tr>
                                <td colspan="5" width="100%">
                                    <%/*
                                         * <a4j:commandButton
                                         * action="#{managerMatricula.cursosAbiertos}"/>
                                         */%>
                                    <rich:dataTable id="tablaCursos1" onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                    cellpadding="0" cellspacing="0" width="100%" border="0"
                                                    value="#{mBMatricula.listaCursosMatriculars}" var="Mat" rowKeyVar="row" >
                                        <h:column id="cId">
                                            <f:facet name="header">
                                                <h:outputText value="Nro" />
                                            </f:facet>
                                            <h:outputText value="#{row}"/>
                                        </h:column>
                                        <h:column id="cCod">
                                            <f:facet name="header">
                                                <h:outputText value="Cod." />
                                            </f:facet>
                                            <h:outputText value="#{Mat.plancurCodigo}"/>
                                        </h:column>
                                        <h:column >
                                            <f:facet name="header">
                                                <h:outputText value="Nom." />
                                            </f:facet>
                                            <h:outputText value="#{Mat.curNombre}"/>
                                        </h:column>
                                        <h:column >
                                            <f:facet name="header">
                                                <h:outputText value="Ciclo." />
                                            </f:facet>
                                            <h:outputText value="#{Mat.ciclo}"/>
                                        </h:column>
                                        <h:column >
                                            <f:facet name="header">
                                                <h:outputText value="Creditos" />
                                            </f:facet>
                                            <h:outputText value="#{Mat.plancurCredito}"/>
                                        </h:column>
                                        <h:column >
                                            <f:facet name="header">
                                                <h:outputText value="Tipo" />
                                            </f:facet>
                                            <h:outputText value="#{Mat.planTipo}"/>
                                        </h:column>
                                        <h:column >
                                            <f:facet name="header">
                                                <h:outputText value="Seccion" />
                                            </f:facet>
                                            <h:selectOneMenu value="#{Mat.secId}" id="seccion">
                                                <f:selectItems value="#{Mat.cboSecciones}"/>
                                            </h:selectOneMenu>
                                        </h:column>
                                    </rich:dataTable>
                                </td>
                                <td>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="5">
                                    <hr size="1">
                                </td>
                            </tr>
                        </table>
                    </div>
                </h:form>
            </rich:modalPanel>

            <rich:modalPanel id="mpDeuda" minHeight="390" minWidth="600" width="600" height="390">
                <f:facet name="header">
                    <h:outputText value="Detalles de la Deuda" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpDeuda')" title="Cerrar"/>
                </f:facet>
                <h:form id="formDeuda">
                    <table width="100%" cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            <td colspan="2"><hr width="100%" size="1"></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center">
                                <b>Detalles de la Deuda</b>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr width="100%" size="1"></td>
                        </tr>
                        <tr>
                            <td>Codigo de Alumno:</td>
                            <td><h:outputText id="d_codigo_alu" value="#{mBMatricula.objBeanAlumno.acAlumno.aluCodigo}"/></td>
                        </tr>
                        <tr>
                            <td>Nombre del Alumno:</td>
                            <td><h:outputText id="d_nombre_alu" value="#{mBMatricula.objBeanAlumno.acAlumno.aluAppaterno} #{mBMatricula.objBeanAlumno.acAlumno.aluApmaterno} #{mBMatricula.objBeanAlumno.acAlumno.aluNombres}"/></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <rich:datascroller id="tablaDeudaBar" align="right" for="tablaDeuda" maxPages="8" style=" width : 100%;"/>
                                <rich:dataTable id="tablaDeuda" rows="10"
                                                onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                cellpadding="0" cellspacing="0"
                                                width="100%" border="0" value="#{mBMatricula.listarDeudas}" var="tarifa">
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Semestre" />
                                        </f:facet>
                                        <h:outputText value="#{tarifa.estpagdet.estpag.sem.semNombre}"/>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Concepto" />
                                        </f:facet>
                                        <h:outputText value="#{tarifa.estpagdet.estpagdetNombre}"/>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="F. de Pago" />
                                        </f:facet>
                                        <h:outputText value="#{tarifa.alutarFechaPago}"/>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="F. de Prorroga" />
                                        </f:facet>
                                        <h:outputText value="#{tarifa.alutarFechaProrroga}"/>
                                    </h:column>
                                </rich:dataTable>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>
            
            <rich:modalPanel  id="mpEstadoAlu" autosized="true" zindex="100">
                    <f:facet name="header">
                        <h:outputText value="Estado Alumno" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpEstadoAlu')" title="Cerrar"/>
                    </f:facet>
                    <h:form id="fEstadoAlu">
                        <table>
                            <tr>
                                <td align="right" colspan="2">
                                    <a4j:commandButton image="/Imagenes/actions/no.png" oncomplete="#{mBAlumnoEstado.oncomplete}" 
                                                       action="#{mBAlumnoEstado.eliminarEstado}" title="Eliminar" alt="Eliminar"
                                                       reRender="tablaMaster" rendered="#{mBAlumnoEstado.flagDelete}" />
                                    <rich:spacer width="10"/>
                                    <a4j:commandButton image="/Imagenes/actions/filesave.png" oncomplete="#{mBAlumnoEstado.oncomplete}" 
                                                       actionListener="#{mBAlumnoEstado.grabarEstado}"
                                                        reRender="tablaMaster" />
                                </td>
                            </tr>
                            <tr>
                                <td align="center" colspan="2">
                                    <h:outputText value="#{mBAlumnoEstado.w_stado_alumno}" styleClass="cabeceraAr" />
                                </td>
                            </tr>
                            <tr>
                                <td><h:outputText value="Alumno"/> </td>
                                <td><h:outputText value="#{mBAlumnoEstado.objAcAlumnoEstado.acAlumno.aluAppaterno} #{mBAlumnoEstado.objAcAlumnoEstado.acAlumno.aluApmaterno} #{mBAlumnoEstado.objAcAlumnoEstado.acAlumno.aluNombres}" /></td>
                            </tr>
                            <tr>
                                <td><h:outputText value="Semestre"/> </td>
                                <td><h:outputText value="#{mBAlumnoEstado.objAcAlumnoEstado.acSemestre.semNombre}"/> </td>
                            </tr>
                            <tr>
                                <td><h:outputText value="Resoluci�n"/> </td>
                                <td><h:inputText value="#{mBAlumnoEstado.objAcAlumnoEstado.aluestResolucion}"/> </td>
                            </tr>
                            <tr>
                                <td><h:outputText value="Reservas" rendered="#{mBAlumnoEstado.p_visualizar}"/> </td>
                                <td>
                                    <rich:inputNumberSpinner value="#{mBAlumnoEstado.objAcAlumnoEstado.aluestPeriodos}" maxValue="3" minValue="1"  rendered="#{mBAlumnoEstado.p_visualizar}" />
                                    <h:selectManyCheckbox value="#{mBAlumnoEstado.w_sem_ids}">
                                        <f:selectItems value="#{mBAlumnoEstado.checkSemestres}"/> 
                                    </h:selectManyCheckbox>
                                </td>
                            </tr>
                            <tr>
                                <td valign="top"><h:outputText value="Observacion"/> </td>
                                <td><h:inputTextarea value="#{mBAlumnoEstado.objAcAlumnoEstado.aluestObservacion}" cols="30" rows="3"/> </td>
                            </tr>
                            <tr>
                                <td><h:outputText value="Archivo"/> </td>
                                <td>
                                    <h:outputText value="#{mBAlumnoEstado.archivoUp.name}" styleClass="fuente"/><rich:spacer width="10" />
                                    <h:commandButton image="/Imagenes/actions/descargar.png" value="D" actionListener="#{mBAlumnoEstado.descargarArchivo}" rendered="#{mBAlumnoEstado.archivoUp.length>5}" /><rich:spacer width="5" />
                                    <a4j:commandButton image="/Imagenes/actions/no.gif" value="E" action="#{mBAlumnoEstado.eleiminarArchivo}" reRender="fEstadoAlu" rendered="#{mBAlumnoEstado.archivoUp.length>5}" />
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <h:panelGroup  rendered="#{mBAlumnoEstado.archivoUp.length<=5}">
                                    <rich:fileUpload id="iFile"
                                                     maxFilesQuantity="2" 
                                                     immediateUpload="true"
                                                     addControlLabel="Agregar"
                                                     uploadControlLabel="Subir" 
                                                     clearControlLabel="Limpiar"
                                                     clearAllControlLabel="Limpiar Todos" 
                                                     fileUploadListener="#{mBAlumnoEstado.listenerResolucion}">
                                        <a4j:support event="onuploadcomplete" reRender="fEstadoAlu" />
                                    </rich:fileUpload>
                                    </h:panelGroup>
                                </td>
                            </tr>
                        </table>
                                    
                    </h:form>
                </rich:modalPanel>
        </body>
    </f:view>
</html>