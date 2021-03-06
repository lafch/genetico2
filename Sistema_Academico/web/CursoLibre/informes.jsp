<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<html>
    <head>
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Modulo de Informes</title>
        <script type="text/javascript">
            function printObjectsSelected_u(sgcomponent, idAL, codigoAL, nombreAL) {
                idAL.value = sgcomponent.getSelectedItems().pluck('w_publico_id');
                nombreAL.innerHTML = sgcomponent.getSelectedItems().pluck('w_datos');
                codigoAL.innerHTML = sgcomponent.getSelectedItems().pluck('w_codigo');
            }
            function printObjectsSelected_T(sgcomponent, idTaller, descripcionTaller) {
                idTaller.value = sgcomponent.getSelectedItems().pluck('w_idAperturado');
                descripcionTaller.innerHTML = sgcomponent.getSelectedItems().pluck('w_descripcionTaller');
            }
            function validar(e) {
                tecla = (document.all) ? e.keyCode : e.which;
                if (tecla == 8 || tecla == 0)
                    return true;
                patron = /\d/;
                te = String.fromCharCode(tecla);
                //alert(tecla);
                return patron.test(te);
            }
            function cargarModal() {
                Richfaces.showModalPanel('mpAviso');
            }
        </script>
        <style type="text/css">

            .tooltip {
                background-color:transparent;
                border-width:3px;
                padding:10px;
            }
            .tooltip-text {
                width:350px;
                height:80px;
                border-width:2px;
                text-align:center;
                display: table-cell;
                vertical-align: middle;
            }
            .tooltipData {
                font-weight: bold;
            }
            .ocultarBtnBuscar{
                visibility: hidden;
            }
        </style>
    </head>
    <body>
    <f:view>
        <jsp:include page="../TablaSistema/Menu.jsp"/>

        <rich:panel id="pContenedor">
            <a4j:form>
                <table width="100%">
                    <tr>
                        <td>
                            <h1>Informe - Consultas</h1>
                        </td>
                        <td align="right">
                    </td>
                    </tr>

                </table>
                <table>
                    <tr>
                        <td>


                    <h:inputText id="txt_dato" value="#{managerInformes.w_suggestion}"
                                 style="width: 300px; font-size: 12px;" maxlength="10"/>
                    <rich:suggestionbox id="suggestion"
                                        for="txt_dato" nothingLabel="Alumno No Encontrado"
                                        suggestionAction="#{managerInformes.autocomplete}" var="alu_sugerido"
                                        height="150" width="400" cellpadding="2"
                                        cellspacing="2" shadowOpacity="1" shadowDepth="1"
                                        minChars="5" rules="none" zindex="3500"
                                        usingSuggestObjects="true"
                                        reRender="pDatosPersonales"
                                        onobjectchange="printObjectsSelected_u(#{rich:component('suggestion')},#{rich:element('idAL')},#{rich:element('codigoAL')},#{rich:element('nombreAL')});" >
                        <h:column>
                            <h:outputText value="#{alu_sugerido.w_datos}" style="font-weight: bold;" />
                        </h:column>
                        <h:column>
                            <h:outputText value="#{alu_sugerido.w_publico_id}" style="font-weight: bold;" />
                        </h:column>
                    </rich:suggestionbox>

                    <h:inputHidden id="idAL" value="#{managerInformes.w_publico_id}"/>
                    <br />
                    <h:outputText id="nombreAL" value="#{managerInformes.w_datos}"/>&nbsp;&nbsp;-&nbsp;&nbsp;
                    <h:outputText id="codigoAL" value="#{managerInformes.w_codigo}"/>

                    <a4j:commandButton id="btnBuscar" title="Buscar alumno" image="/Imagenes/actions/viewmag.png" actionListener="#{managerInformes.buscarAlumno}" oncomplete="#{managerInformes.oncomplete}"  reRender="pDatosPersonales,tbConsultRealiz" />
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <h:graphicImage value="/Imagenes/actions/filenew.png" style="cursor: pointer;"
                                    title="Nuevo alumno" width="16">
                        <a4j:support event="onclick"  oncomplete="Richfaces.showModalPanel('mpUpdate',{top:100})"
                                     action="#{managerCLAlumno.nuevo}"
                                     reRender="falumno"/>
                    </h:graphicImage>
                    <% /*
                         * <a4j:commandButton id="btnNueAlu"
                         * title="Nuevo Alumno"
                         * image="/Imagenes/actions/filenew.png"
                         * actionListener="#{managerInformes.nuevoAlumno}"
                         * oncomplete="#{managerInformes.oncomplete}"
                         * reRender="mpNuevoAlumno" />
                         */%>
                    </td>
                    <td>&nbsp;</td>
                    </tr>


                </table>
                <table style="width: 100%;margin: 0;padding: 0">
                    <tr>
                        <td style="width: 50%;margin: 0;padding: 0;top: 2px" valign="top">
                            <rich:panel id="pDatosPersonales" header="Datos personales" style="width:99%;height: 280px;">
                                <table id="tDatos" style="width:100%;">
                                    <tr>
                                        <td style="width:15%;">Codigo : </td>
                                        <td>
                                    <h:outputText value="#{managerInformes.alumnocl.aluCodigo }" />
                            </td>
                        </tr>
                        <tr>
                            <td>Paterno : </td>
                            <td>
                        <h:outputText value="#{managerInformes.alumnocl.aluAppaterno}" />
                        </td>
                        </tr>
                        <tr>
                            <td>Materno : </td>
                            <td>
                        <h:outputText value="#{managerInformes.alumnocl.aluApmaterno}" />
                        </td>
                        </tr>
                        <tr>
                            <td>Nombres : </td>
                            <td>
                        <h:outputText value="#{managerInformes.alumnocl.aluNombres}" />
                        </td>
                        </tr>
                        <tr>
                            <td>Distrito : </td>
                            <td>
                        <h:outputText value="#{managerInformes.distrito}" />
                        </td>
                        </tr>
                        <tr>
                            <td>Telefono Fijo: </td>
                            <td>
                        <h:outputText value="#{managerInformes.alumnocl.aluTelefono }" />
                        </td>
                        </tr>
                        <tr>
                            <td>Telefono Celular : </td>
                            <td>
                        <h:outputText value="#{managerInformes.alumnocl.aluCelular}" />
                        </td>
                        </tr>
                        <tr>
                            <td>Correo : </td>
                            <td>
                        <h:outputText value="#{managerInformes.alumnocl.aluMail}" />
                        </td>
                        </tr>
                        <tr>
                            <td>
                        <a4j:commandButton id="btnEditAlu" title="Editar datos de Alumno" image="/Imagenes/actions/editpaste.png" actionListener="#{managerInformes.editarAlumno}" oncomplete="#{managerInformes.oncomplete}" reRender="mpNuevoAlumno" />
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a4j:commandButton id="btnNuevCons" title="Ingresar Consulta" image="/Imagenes/actions/academico.png" actionListener="#{managerInformes.agregarNuevaConsulta}" oncomplete="#{managerInformes.oncomplete}" reRender="pConsultasRealizadas,frmNuevaConsulta" />
                        </td>
                        <td>&nbsp;</td>
                        </tr>
                    </table>
                </rich:panel>
                </td>
                <td style="margin: 0;padding: 0;vertical-align: top">
                    <rich:panel id="pConsultasRealizadas" header="Consultas Realizadas" style="width: 100%; height: 280px; overflow: auto;">

                        <rich:dataTable id="tbConsultRealiz" width="100%" 
                                        onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                        onRowMouseOut="this.style.backgroundColor='#FFFFFF'"
                                        cellpadding="0" cellspacing="0"
                                        value="#{managerInformes.listabeanConsultaPublico}" var="consulta">
                            <rich:column>
                            <f:facet name="header"> <h:outputText value="Nro"/> </f:facet>
                            <h:outputText value="#{consulta.con_contador}" />
                            <f:param id="p_cons_id" value="#{consulta.conId}"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header"> <h:outputText value="ID"/> </f:facet>
                            <h:outputText value="#{consulta.conId}" />
                        </rich:column>

                        <rich:column>
                            <f:facet name="header"> <h:outputText value="Area"/> </f:facet>
                            <h:outputText value="#{consulta.area}" />
                            <%-- <h:outputText value="#{consulta.clModulo.modDescripcion}" />--%>

                        </rich:column>

                        <rich:column>
                            <f:facet name="header"> <h:outputText value="Modulo / Curso"/> </f:facet>
                            <h:outputText value="#{consulta.modulo} / #{consulta.curso}" />
                            <%-- <h:outputText value="#{consulta.clModulo.modDescripcion}" />--%>

                        </rich:column>

                        <rich:column>
                            <f:facet name="header"> <h:outputText value="Fec. Registro"/> </f:facet>
                            <h:outputText value="#{consulta.conFechaContacto}">
                                <f:convertDateTime pattern="dd/MM/yyyy" />
                            </h:outputText>

                        </rich:column>

                        <rich:column>
                            <f:facet name="header"> <h:outputText value="Usuario"/> </f:facet>
                            <h:outputText value="#{consulta.con_usuario}" />
                        </rich:column>

                        <rich:column>
                            <f:facet name="header"> <h:outputText value="Procedencia"/> </f:facet>
                            <h:outputText value="#{consulta.con_tipo_porcedencia}" />
                        </rich:column>

                        <rich:column>
                            <f:facet name="header"> <h:outputText value="Matricular"/> </f:facet>
                            <a4j:commandButton image="#{consulta.img_matricula}" title="#{consulta.con_title}"
                                               rendered="#{consulta.conEstadoMatricula==0 && empty consulta.espId}"
                                               actionListener="#{managerInformes.MostrarCursos}" oncomplete="#{managerInformes.oncomplete}"
                                               reRender="mpRegistroMatricula,resultados,tipoPagoMat,pgEstructuras,controles,moduloMat"/>
                            <h:graphicImage value="/Imagenes/actions/ok.png" rendered="#{consulta.conEstadoMatricula!=0 && empty consulta.espId}" title="#{consulta.con_title}" />
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Obs." />
                            </f:facet>
                            <f:param id="consulta_id" value="#{consulta.conId}"/>
                            <f:param id="p_mod_id" value="#{consulta.clArbolAcademico.arbId}" />
                            <a4j:commandButton image="/Imagenes/actions/viewmag.png" title="Observar"
                                               oncomplete="#{managerInformes.oncomplete}" actionListener="#{managerInformes.mostrarObervacion}" reRender="txtaEdicionConsulta">

                            </a4j:commandButton>
                        </rich:column>

                        <rich:column>

                            <f:facet name="header">
                                <h:outputText value="Rep." />
                            </f:facet>
                            <f:param id="p_consulta_id" value="#{consulta.conId}"/>
                            <f:param id="p_alu_id" value="#{consulta.clAlumno.aluId}"/>

                            <a4j:commandButton image="/Imagenes/actions/fileprint.png" title="Reporte" alt="Reporte"
                                               oncomplete="#{managerConsulAca.oncomplete}" rendered="#{consulta.conEstadoMatricula!=0 }" actionListener="#{managerConsulAca.ImprimirReporte}" reRender="mp7,reporte" />

                        </rich:column>
                        <rich:column style="text-align: center;">
                            <f:facet name="header" >
                                <h:outputText value="Eliminar" />
                            </f:facet>
                            <a4j:commandButton  image="/Imagenes/actions/delete.gif"
                                                style="height:14"  title="Eliminar"
                                                onclick="if (!confirm('Esta seguro de eliminar la consulta')) {
                    return false;
                }"
                                                actionListener="#{managerInformes.eliminarConsulta}"
                                                reRender="pDatosPersonales,tbConsultRealiz" oncomplete="#{managerConsulAca.oncomplete}"
                                                rendered="#{consulta.conEstadoMatricula==0 }"
                                                />
                        </rich:column>


                    </rich:dataTable>
                </rich:panel>
                </td>
                </tr>
                </table>

            </a4j:form>
        </rich:panel>
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
                                <rich:panel style="width : 799px; height:580px;" >
                            <a4j:mediaOutput id="reporte" uriAttribute="data" style="width : 760px; height:550px;"
                                             element="object" standby="cargando..."
                                             createContent="#{managerConsulAca.cargarReporte}"
                                             mimeType="application/pdf"
                                             value="1"/>
                        </rich:panel>
                        </td>
                        </tr>
                    </table>
                </a4j:form>
            </rich:modalPanel>
        </a4j:form>


        <!-- MODALPANEL INGRESO/EDICION DE NUEVO ALUMNO-->
        <rich:modalPanel id="mpNuevoAlumno" autosized="true" keepVisualState="false" >
            <f:facet name="header">
                <h:outputText value="Nuevo Alumno"/>
            </f:facet>
            <f:facet name="controls">
                <h:panelGroup>
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpNuevoAlumno')" title="Cerrar"/>
                </h:panelGroup>
            </f:facet>
            <a4j:form id="frmNueAlu">
                <table id="tbNuevoAlu" style="width:100%;">
                    <tr>
                        <td>Paterno : </td>
                        <td>
                    <h:inputText value="#{managerInformes.alumnocl_edit.aluAppaterno}" />
                    </td>
                    </tr>
                    <tr>
                        <td>Materno : </td>
                        <td>
                    <h:inputText value="#{managerInformes.alumnocl_edit.aluApmaterno}" />
                    </td>
                    </tr>
                    <tr>
                        <td>Nombres : </td>
                        <td>
                    <h:inputText value="#{managerInformes.alumnocl_edit.aluNombres}" />
                    </td>
                    </tr>
                    <tr>
                        <td>Fecha Nacimiento : </td>
                        <td>
                            <rich:calendar id="iFechaNacimiento" datePattern="dd-MM-yyyy" style="width : 200px;"
                                           value="#{managerInformes.alumnocl_edit.aluFechaNacimiento}" />
                        </td>
                    </tr>
                    <tr>
                        <td><h:outputText value="Sexo"/></td>
                    <td>
                    <h:selectOneMenu  id="iSexo"  value="#{managerInformes.alumnocl_edit.aluSexo}" style="width : 180px;" tabindex="11">
                        <f:selectItems value="#{managerInformes.cboSexo}" />
                    </h:selectOneMenu>
                    </td>
                    </tr>
                    <tr>
                        <td>DNI : </td>
                        <td>
                    <h:inputText value="#{managerInformes.alumnocl_edit.aluDni}" />
                    </td>
                    </tr>

                    <tr>
                        <td>Direccion : </td>
                        <td>
                    <h:inputText value="#{managerInformes.alumnocl_edit.aluDireccion}" />
                    </td>
                    </tr>

                    <tr>
                        <td>Departamento : </td>
                        <td>
                    <h:selectOneMenu id="iDepartamento" value="#{managerInformes.w_departamento}">
                        <f:selectItems value="#{managerInformes.cboDepartamento}" />
                        <a4j:support event="onchange" reRender="iProvincia,iDistrito" />
                    </h:selectOneMenu>
                    </td>
                    </tr>
                    <tr>
                        <td>Provincia : </td>
                        <td>
                    <h:selectOneMenu id="iProvincia" value="#{managerInformes.w_provincia}">
                        <f:selectItems value="#{managerInformes.cboProvincia}" />
                        <a4j:support event="onchange" reRender="iDistrito" />
                    </h:selectOneMenu>
                    </td>
                    </tr>
                    <tr>
                        <td>Distrito : </td>
                        <td>
                    <h:selectOneMenu id="iDistrito" value="#{managerInformes.alumnocl_edit.aluDistrito}">
                        <f:selectItems value="#{managerInformes.cboDistrito}" />
                    </h:selectOneMenu>
                    </td>
                    </tr>
                    <tr>
                        <td>Telefono Fijo: </td>
                        <td>
                    <h:inputText value="#{managerInformes.alumnocl_edit.aluTelefono }" />
                    </td>
                    </tr>
                    <tr>
                        <td>Telefono Celular : </td>
                        <td>
                    <h:inputText value="#{managerInformes.alumnocl_edit.aluCelular}" />
                    </td>
                    </tr>
                    <tr>
                        <td>Correo : </td>
                        <td>
                    <h:inputText value="#{managerInformes.alumnocl_edit.aluMail}" />
                    </td>
                    </tr>
                    <tr>
                        <td>
                    <a4j:commandButton image="/Imagenes/actions/filesave.png"
                                       actionListener="#{managerInformes.guardarDatosAlu}" oncomplete="#{managerInformes.oncomplete}" reRender="pContenedor" />

                    </td>
                    <td>&nbsp;</td>
                    </tr>
                </table>
            </a4j:form>
        </rich:modalPanel>
        <!-- FIN MODALPANEL INGRESO DE NUEVO ALUMNO-->

        <!-- MODALPANEL INGRESO DE NUEVA CONSULTA-->
        <rich:modalPanel id="mpNuevaConsulta"  autosized="true" width="600" height="590" >
            <f:facet name="header">
                <h:outputText value="Nueva consulta" />
            </f:facet>
            <f:facet name="controls">
                <h:panelGroup>
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpNuevaConsulta')" />
                </h:panelGroup>
            </f:facet>
            <a4j:form id="frmNuevaConsulta">
                <div style="overflow-y: auto;height: 590px;width: 830px">
                    <table id="tbNuevaConsulta">
                        <tr>
                            <td width="10%"><h:outputText value="Instituci�n :"/></td>
                        <td>
                        <h:selectOneMenu value="#{managerInformes.iInstitucionId}" style="width:166px;">
                            <f:selectItems value="#{managerInformes.cboInstitucion}" />
                            <a4j:support event="onchange" reRender="frmNuevaConsulta, iArea"/>
                        </h:selectOneMenu>
                        </td>
                        <td >
                        <h:outputText rendered="#{'078004' ne managerInformes.iInstitucionId}" value="Horarios : "/>
                        </td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputText rendered="#{'078004' eq managerInformes.iInstitucionId}" value="Espec.:"/></td>
                        <td>
                        <h:selectOneMenu style="width:166px" rendered="#{'078004' eq managerInformes.iInstitucionId}"  id="iEspecialidad" value="#{managerInformes.w_esp_id}">
                            <f:selectItems value="#{managerInformes.b_especialidades}" />
                            <a4j:support event="onchange" />
                        </h:selectOneMenu>
                        </td>
                        <td rowspan="6" style="vertical-align: top;">
                            <rich:dataTable rendered="#{'078004' ne managerInformes.iInstitucionId}" id="tbPlantilla" width="100%" rows="10"
                                            onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                            onRowMouseOut="this.style.backgroundColor='#FFFFFF'"
                                            cellpadding="0" cellspacing="0"
                                            value="#{managerInformes.listaDetallePlantilla}" var="listaPlantilla">
                                <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Dia" />
                                </f:facet>
                                <h:outputText value="#{listaPlantilla.pladetDia}" />
                            </rich:column>

                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="H. inicio" />
                                </f:facet>
                                <h:outputText value="#{listaPlantilla.paldetHoraIni}" >
                                    <f:convertDateTime pattern="hh:mm"/>
                                </h:outputText>
                            </rich:column>

                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="H. Fin" />
                                </f:facet>
                                <h:outputText value="#{listaPlantilla.pladetHoraFin}">
                                    <f:convertDateTime pattern="hh:mm"/>
                                </h:outputText>
                            </rich:column>

                        </rich:dataTable>
                        </td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputText rendered="#{'078004' ne managerInformes.iInstitucionId}" value="Area :"/></td>
                        <td>
                        <h:selectOneMenu rendered="#{'078004' ne managerInformes.iInstitucionId}"  style="width:166px;"  id="iArea" value="#{managerInformes.w_are_id}">
                            <f:selectItems value="#{managerInformes.b_areas}" />
                            <a4j:support event="onchange" reRender="iModulo"/>
                        </h:selectOneMenu>
                        </td>
                        </tr>

                        <tr>
                            <td>
                        <h:outputText rendered="#{'078004' ne managerInformes.iInstitucionId}" value="Modulo : "/>
                        </td>
                        <td>
                        <h:selectOneMenu rendered="#{'078004' ne managerInformes.iInstitucionId}"  style="width:166px;" id="iModulo" value="#{managerInformes.w_mod_id}">
                            <f:selectItems value="#{managerInformes.b_modulos}" />
                            <a4j:support event="onchange" reRender="cboCurso"/>
                        </h:selectOneMenu>
                        </td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputText rendered="#{'078004' ne managerInformes.iInstitucionId}" value="Curso :"/></td>
                        <td>
                        <h:selectOneMenu rendered="#{'078004' ne managerInformes.iInstitucionId}"  style="width:166px;" id="cboCurso" value="#{managerInformes.icurId}">
                            <f:selectItems value="#{managerInformes.cboCursoCons}" />
                        </h:selectOneMenu>
                        </td>
                        </tr>
                        <tr>
                            <td><h:outputText rendered="#{'078004' ne managerInformes.iInstitucionId}" value="Sede : "/></td>
                        <td>
                        <h:selectOneMenu rendered="#{'078004' ne managerInformes.iInstitucionId}"  style="width:166px;" id="iSede" value="#{managerInformes.consultaPublico.acLocal.id}">
                            <f:selectItems value="#{managerInformes.cboLocal}" />
                        </h:selectOneMenu>
                        </td>
                        </tr>
                        <tr>
                            <td>Turno : </td>
                            <td>
                        <h:selectOneMenu id="iPlantilla" value="#{managerInformes.consultaPublico.plaId}"  style="width:166px;">
                            <f:selectItems value="#{managerInformes.cboPlantilla}" />
                            <a4j:support event="onchange" reRender="tbPlantilla"/>
                        </h:selectOneMenu>
                        </td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                            <th colspan="4">
                        <hr style="width: 100%" />
                        </th>
                        </tr>
                        <tr>
                            <td>Medio : </td>
                            <td>
                        <h:panelGrid columns="2">
                            <h:selectManyCheckbox  id="iMedio" layout="pageDirection" value="#{managerInformes.lstMedSelec}">
                                <f:selectItems value="#{managerInformes.cboMedio}" />
                                <a4j:support event="onchange" reRender="iMedioDet"/>
                            </h:selectManyCheckbox>
                        </h:panelGrid>
                        </td>
                        <td>
                        <h:panelGrid columns="2">
                            <h:selectManyCheckbox  id="iMedioC2" layout="pageDirection" value="#{managerInformes.lstMedSelecC2}">
                                <f:selectItems value="#{managerInformes.cboMedio2}" />
                                <a4j:support event="onchange" reRender="iMedioDet"/>
                            </h:selectManyCheckbox>
                        </h:panelGrid>
                        </td>
                        <td>
                        <h:panelGrid columns="2">
                            <h:selectManyCheckbox  id="iMedioC3" layout="pageDirection" value="#{managerInformes.lstMedSelecC3}">
                                <f:selectItems value="#{managerInformes.cboMedio3}" />
                                <a4j:support event="onchange" reRender="iMedioDet"/>
                            </h:selectManyCheckbox>
                        </h:panelGrid>
                        </td>
                        </tr>

                        <tr>
                            <td>Web Centros : </td>
                            <td colspan="3">
                        <h:panelGrid columns="2">
                            <h:selectManyCheckbox  id="iWebCentros" layout="lineDirection" value="#{managerInformes.lstWebCentrosSelec}">
                                <f:selectItems value="#{managerInformes.cboWebCentros}" />
                                <a4j:support event="onchange" reRender="iWebCentrosDet"/>
                            </h:selectManyCheckbox>
                        </h:panelGrid>
                        </td>
                        </tr>

                        <tr>
                            <td>Peri�dicos : </td>
                            <td colspan="3">
                        <h:panelGrid columns="2">
                            <h:selectManyCheckbox  id="iPeriodicos" layout="lineDirection" value="#{managerInformes.lstPeriodicosSelec}">
                                <f:selectItems value="#{managerInformes.cboPeriodicos}" />
                                <a4j:support event="onchange" reRender="iPeriodicosDet"/>
                            </h:selectManyCheckbox>
                        </h:panelGrid>
                        </td>
                        </tr>
                        <%/*
                             * <tr> <td>Detalle Medio : </td> <td> <h:selectOneMenu
                             * id="iMedioDet"
                             * value="#{managerInformes.consultaPublico.detmedId}">
                             * <f:selectItems value="#{managerInformes.cboMedioDetalle}"
                             * /> </h:selectOneMenu> </td> </tr> <tr> <td>Obs. Medio:
                             * </td> <td> <h:inputText
                             * value="#{managerInformes.consultaPublico.conObservacionMedio}"
                             * /> </td> </tr>
                             */%>

                        <tr>
                            <th colspan="4">
                        <hr style="width: 100%" />
                        </th>
                        </tr>

                        <%--<tr>
                                    <td>Persona a recomendar
                                        <h:inputText id="txtRecomendar" value="#{managerInformes.alumnocl_edit.aluMail}" /> </td>
                                    <td>
                                       Tel�fono o Correo 
                                       <h:inputText id="txtCorreoTelf" value="#{managerInformes.alumnocl_edit.aluMail}" />
                                    </td>
                                    <td>Grado de Instrucci�n
                                        <h:selectOneMenu style="width:166px;" id="iGrado" >
                                            <f:selectItems value="#{managerInformes.cboGradoInstruccion}" />
                                        </h:selectOneMenu></td>
                                    <td>Ocupaci�n Actual
                                        <h:selectOneMenu style="width:166px;" id="iOcupacion" >
                                            <f:selectItems value="#{managerInformes.cboOcupacion}" />
                                        </h:selectOneMenu></td>
                                </tr>--%>

                        <%-- PARA AGREGAR INFORMACION REFERENCIAL--%>
                        <tr><td colspan="4">
                                <rich:dataTable value="#{managerInformes.listarReferencial}" var="referencial" id="dtReferencial" width="800">
                                    <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Id" />
                                </f:facet>
                                <h:outputText value="#{referencial.idReferencial}" />
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Persona a Recomendar" />
                                </f:facet>
                                <h:inputText value="#{referencial.personaRecomendar}" />
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Correo o Telefono" />
                                </f:facet>
                                <h:inputText value="#{referencial.correoTelefono}" />
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Grado de Instruccion" />
                                </f:facet>
                                <h:selectOneMenu style="width:166px;" id="iGrado" value="#{referencial.gradoInstruccion}" >
                                    <f:selectItems value="#{managerInformes.cboGradoInstruccion}" />
                                </h:selectOneMenu>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Ocupacion Actual" />
                                </f:facet>
                                <h:selectOneMenu style="width:166px;" value="#{referencial.ocupacionActual}" id="iOcupacion" >
                                    <f:selectItems value="#{managerInformes.cboOcupacion}" />
                                </h:selectOneMenu>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <a4j:commandButton image="/Imagenes/actions/edit_add.png" reRender="dtReferencial" actionListener="#{managerInformes.agregarReferencialdt}"/>
                                </f:facet>
                                <f:param id="p_ref_id" value="#{referencial.idReferencial}" />
                                <a4j:commandButton image="/Imagenes/actions/no.png" reRender="dtReferencial" actionListener="#{managerInformes.quitarReferencial}"/>
                            </rich:column>
                        </rich:dataTable>
                        </td></tr>
                        <%-- CERRAMOS PARA AGREGAR INFORMACION REFERENCIAL--%>

                        <tr>
                            <th colspan="4">
                        <hr style="width: 100%" />
                        </th>
                        </tr>
                        <tr>
                            <td>Fecha de contacto: </td>
                            <td colspan="3">
                                <rich:calendar id="iFechaContacto" datePattern="dd-MM-yyyy" style="width : 200px;"
                                               value="#{managerInformes.consultaPublico.conFechaContacto}" />
                            </td>
                        </tr>
                        <tr>
                            <td>Prioridad: </td>
                            <td colspan="3">
                        <h:selectOneRadio id="iPrioridad" value="#{managerInformes.consultaPublico.sConPrioridad}">
                            <f:selectItem id="ialto" itemLabel="alto" itemValue="079001" />
                            <f:selectItem id="imedio" itemLabel="medio" itemValue="079002" />
                            <f:selectItem id="ibajo" itemLabel="bajo" itemValue="079003" />
                        </h:selectOneRadio>

                        </td>
                        </tr>
                        <tr>
                            <td>Procedencia: </td>
                            <td colspan="3">
                        <h:selectOneRadio id="iProcedencia" value="#{managerInformes.consultaPublico.conProcedencia}">
                            <f:selectItem id="iPresencial" itemLabel="Presencial" itemValue="064001" />
                            <f:selectItem id="iTelefonico" itemLabel="Telefonico" itemValue="064002" />
                            <f:selectItem id="iweb" itemLabel="WEB" itemValue="064003" />
                            <f:selectItem id="iCampo" itemLabel="CAMPO" itemValue="064004" />
                        </h:selectOneRadio>

                        </td>
                        </tr>
                        <tr>
                            <td>Observacion: </td>
                            <td colspan="3">
                        <h:inputTextarea id="iObservacionConsulta" value="#{managerInformes.consultaPublico.conObservacionRegistro}" />
                        </td>
                        </tr>
                        <tr>
                            <th>
                        <a4j:commandButton image="/Imagenes/actions/filesave.png"
                                           actionListener="#{managerInformes.guardarConsultaPublicoNew}" oncomplete="#{managerInformes.oncomplete}" reRender="tbConsultRealiz,frmNuevaConsulta" />
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <a4j:commandButton image="/Imagenes/actions/button_cancel.png" actionListener="#{managerInformes.cancelarConsulta}" oncomplete="#{managerInformes.oncomplete}" reRender="frmNuevaConsulta"/>
                        </th>
                        </tr>
                    </table>
                </div>
            </a4j:form>
        </rich:modalPanel>
        <!-- FIN MODALPANEL INGRESO DE NUEVA CONSULTA-->

        <!-- MODALPANEL EDICI?N DE OBSERVACI?N -->
        <rich:modalPanel id="mpObservacion" autosized="true" keepVisualState="false">
            <f:facet name="header">
                <h:outputText value="Observaci?n"/>
            </f:facet>

            <f:facet name="controls">
                <h:panelGroup>
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpObservacion')" title="Cerrar"/>
                </h:panelGroup>
            </f:facet>
            <a4j:form id="form2">




                <h:panelGroup layout="block" styleClass="scrolls">

                    <f:param id="consulta_id_edit" value="#{managerInformes.w_consulta_id_edit}" />
                    <h:inputTextarea value="#{managerInformes.w_obs_consulta_editar}" style="width:190px;height:100px" id="txtaEdicionConsulta" />

                </h:panelGroup>
                <a4j:commandButton image="/Imagenes/actions/filesave.png"
                                   actionListener="#{managerInformes.actualizarObsConsulta}" oncomplete="#{managerInformes.oncomplete}" reRender="pConsultasRealizadas"/>

            </a4j:form>

        </rich:modalPanel>
        <!-- FIN MODALPANEL EDICI?N DE OBSERVACI?N -->

        <!-- MODALPANEL REGISTRAR MATRICULA -->
        <rich:modalPanel id="mpRegistroMatricula" height="550" width="850" >
            <f:facet name="header">
                <h:outputText value="Matricular"/>
            </f:facet>
            <f:facet name="controls">
                <h:panelGroup>
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('mpRegistroMatricula')" title="Cerrar"/>
                </h:panelGroup>
            </f:facet>
            <a4j:form id="form3">
                <div style="overflow-x:hidden; overflow-y:auto; height: 500px; width: 800px;">
                    <table width="800px" style="font-size:10px; font-family:verdana;"
                           cellpadding="0" cellspacing="0" border="0" align="center">
                        <tr>
                            <td colspan="2" style="text-align:right">
                                <div style="margin-right: 80px">
                                    <a4j:commandButton image="/Imagenes/actions/filesave.png"
                                                       title="Matricular"
                                                       actionListener="#{managerInformes.MatricularSeleccionados}"
                                                       oncomplete="#{managerInformes.oncomplete}" reRender="tbConsultRealiz" />
                                </div>

                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <h3>
                                    <h:outputText value="#{managerInformes.alumnocl.aluAppaterno} #{managerInformes.alumnocl.aluApmaterno}, #{managerInformes.alumnocl.aluNombres}" />
                                </h3>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr size="1" /></td>
                        </tr>
                        <tr>
                            <td width="200px">Modulo : </td>
                            <td>
                        <h:outputText id="moduloMat" value="#{managerInformes.i_modulo_mat_text}" />
                        </td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr size="1" /></td>
                        </tr>
                        <tr>
                            <td>
                                Curso : 
                            </td>
                            <td>
                        <h:selectOneMenu id="iCurso" value="#{managerInformes.w_cur_id}">
                            <f:selectItems value="#{managerInformes.b_cursos}" />
                            <a4j:support event="onchange" reRender="iTaller"/>
                        </h:selectOneMenu>
                        </td>
                        </tr>
                        <tr>
                            <td>
                                Taller : 
                            </td>
                            <td>
                        <h:selectOneMenu id="iTaller" value="#{managerInformes.w_tall_id}">
                            <f:selectItems value="#{managerInformes.b_taller}" />
                            <a4j:support event="onchange" actionListener="#{managerInformes.CargarCursos}" 
                                         reRender="resultados"/>
                        </h:selectOneMenu>
                        </td>
                        </tr>
                        <tr>
                            <td colspan="2"> </td>
                        </tr>
                        <tr>
                            <td>Turno : </td>
                            <td>
                        <h:selectOneMenu id="iTurno2" value="#{managerInformes.w_pla_id}" >
                            <f:selectItems value="#{managerInformes.cboPlantilla}"  />
                        </h:selectOneMenu>
                        </td>
                        </tr> 
                        <% /*<tr>
                            <td>Tipo de Pago : </td>
                            <td>
                        <h:selectOneMenu id="tipoPagoMat" value="#{managerInformes.i_estpag_id}" >
                            <f:selectItems value="#{managerInformes.cboEstrPagMat}"  />
                            <a4j:support event="onchange" reRender="dtEstructuraDetalle" actionListener="#{managerInformes.listarEstructuraDetalle}" />
                        </h:selectOneMenu><br /><br />
                        </td>
                        </tr>
                        <tr>
                        */%>

                        <% /*
                                                    <td colspan="2" align="center">

                                
                                                             * <div style="float: left"> <h:outputText
                                                             * value="El pago por el curso se llevara a cabo
                                                             * cada "/> </div> <div style="float: left " >
                                                             * <h:inputText id="iDiaCobrar"
                                                             * value="#{managerInformes.w_diaCobrar}"
                                                             * style="width:60px" /> </div> <div
                                                             * style="float: left " > <h:outputText
                                                             * value="dias"/> </div>
                                                             */%>
                        <tr>
                            <td>
                        <h:outputText value="El pago por el curso se llevara a cabo cada "/> &nbsp;&nbsp;
                        <h:inputText id="iDiaCobrar" value="#{managerInformes.w_diaCobrar}" style="width:60px"  maxlength="2" onkeypress="return validar(event);" /> &nbsp;&nbsp;
                        </td>
                        <td>
                        <h:outputText value="dias,  Iniciando el "/>
                        <rich:calendar value="#{managerInformes.fecha_inicio}" datePattern="dd/MM/yyyy" id="iFechaInicio" style="width:160px;" />
                        </td>
                        </tr>
                        <% /*
                        <tr>
                            <td></td>
                            <td>
                        <h:panelGroup id="controles">
                            <h:outputText value="#{managerInformes.w_textoPago}" id="iTextoPago"/> &nbsp;&nbsp;&nbsp;
                            <a4j:commandButton image="/Imagenes/actions/down.png"
                                               actionListener="#{managerInformes.mostrarEstructura}"
                                               reRender="controles,pgEstructuras" rendered="#{managerInformes.w_ocultar_boton}"/>

                            <a4j:commandButton image="/Imagenes/actions/up.png"
                                               actionListener="#{managerInformes.ocultarEstructura}"
                                               reRender="controles,pgEstructuras" rendered="#{managerInformes.w_ocultar_estructura}" />
                        </h:panelGroup>
                        <h:panelGroup id="pgEstructuras">

                            <rich:dataTable id="dtEstructuraDetalle" value="#{managerInformes.listaEstructuraDetalle}" 
                                            var="estructura" width="500" rendered="#{managerInformes.w_ocultar_estructura}" >
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
                        </td>
                        </tr>
                        */%>
                        <!-- POR EVALUAR -->
                        <tr style="height: 20px">
                            <td colspan="2"></td>
                        </tr>
                        <tr style="height: 20px">
                            <td colspan="2"></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                        <h:panelGroup id="resultados">
                            <h:outputText value="#{managerInformes.i_mensaje_error}" style="color: #FF0000; font-weight: bold;" rendered="#{managerInformes.i_ver_message}"/>
                            <h:outputText value="#{managerInformes.i_modulo_mat_text}" style="font-weight: bold;" rendered="#{managerInformes.i_ver_cursos}"/>
                            <br/>
                            <rich:dataTable id="tablaCursos"
                                            onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                            onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                            cellpadding="0"
                                            cellspacing="0"
                                            width="100%"
                                            border="0"
                                            value="#{managerInformes.secciones}"
                                            var="sec" rendered="#{managerInformes.i_ver_cursos}">
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
                                <% /*
                                     * <rich:column> <f:facet name="header">
                                     * <h:outputText value="Monto Base"/>
                                     * </f:facet> <h:outputText
                                     * value="#{sec.i_monto_base}"/>
                                     * </rich:column> <rich:column> <f:facet
                                     * name="header"> <h:outputText
                                     * value="Monto a Pagar"/> </f:facet>
                                     * <f:param id="p_sec_id"
                                     * value="#{sec.i_sec_id}"/> <span>
                                     * <h:outputText
                                     * value="#{sec.i_monto_pagar}"
                                     * style="align: left;"
                                     * rendered="#{sec.i_ver}"/>
                                     * <h:inputText
                                     * value="#{sec.i_monto_editar}"
                                     * style="font-size: 10px; width: 40px;"
                                     * rendered="#{sec.i_editar}"/>
                                     * <a4j:commandButton
                                     * image="/Imagenes/actions/klipper_dock.png"
                                     * title="Editar"
                                     * actionListener="#{managerInformes.EditarMonto}"
                                     * reRender="resultados,iMontoCanbiar,iObservacion"
                                     * style="align: right;"
                                     * oncomplete="javascript:Richfaces.showModalPanel('mpDetalleMonto')"
                                     * /> <%/* <a4j:commandButton
                                     * image="/Imagenes/actions/filesave.png"
                                     * title="Grabar"
                                     * actionListener="#{managerInformes.GuardarMonto}"
                                     * reRender="resultados" style="align:
                                     * right;" rendered="#{sec.i_editar}"/>
                                     * <a4j:commandButton
                                     * image="/Imagenes/actions/fileclose.png"
                                     * title="Cancelar"
                                     * actionListener="#{managerInformes.CancelarMonto}"
                                     * reRender="resultados" style="align:
                                     * right;" rendered="#{sec.i_editar}" />
                                     * </span> </rich:column>
                                     */%>
                                <rich:column>
                                    <f:facet name="header">
                                        <h:outputText value="Seleccionar"/>
                                    </f:facet>
                                    <h:selectBooleanCheckbox value="#{sec.i_agregar}"/>
                                </rich:column>
                            </rich:dataTable>
                            <br/>
                            <%/*
                                 * <span style="text-align: right;">
                                 * <a4j:commandLink value="Deseleccionar
                                 * Todos"
                                 * actionListener="#{managerInformes.DeseleccionarTodos}"
                                 * reRender="resultados"
                                 * rendered="#{managerInformes.i_ver_cursos}"
                                 * style="font-size: 11px;"/> <rich:spacer
                                 * width="10px"
                                 * rendered="#{managerInformes.i_ver_cursos}"/>
                                 * <a4j:commandLink value="Seleccionar
                                 * Todos"
                                 * actionListener="#{managerInformes.SeleccionarTodos}"
                                 * reRender="resultados"
                                 * rendered="#{managerInformes.i_ver_cursos}"
                                 * style="font-size: 11px;"/> <rich:spacer
                                 * width="10px"
                                 * rendered="#{managerInformes.i_ver_cursos}"/>
                                 * </span>
                                 */%>
                        </h:panelGroup>
                        </td>
                        </tr>

                    </table>
                </div>
            </a4j:form>
        </rich:modalPanel>
        <!-- FIN MODALPANEL REGISTRAR MATRICULA -->

        <rich:modalPanel id="mpDetalleMonto" autosized="true" >
            <f:facet name="header">
                <h:outputText value="Observacion del Monto"/>
            </f:facet>
            <f:facet name="controls">
                <h:panelGroup>
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('mpDetalleMonto')" title="Cerrar"/>
                </h:panelGroup>
            </f:facet>

            <a4j:form>
                <h:outputLabel for="iMontoCanbiar" value="Monto a Pagar" />
                <h:inputText value="#{managerInformes.w_monto_cambiar}" style="width:100px" id="iMontoCanbiar"  /><br>
                <h:outputLabel for="iObservacion" value="Escriba una observacion" />
                <h:inputTextarea style="width: 220px; height:180px; " value="#{managerInformes.w_observacion_monto}" id="iObservacion" />
                <a4j:commandButton image="/Imagenes/actions/filesave.png"
                                   title="Grabar"
                                   actionListener="#{managerInformes.GuardarMonto}"
                                   reRender="resultados" oncomplete="#{managerInformes.oncomplete}" />
            </a4j:form>
        </rich:modalPanel>

        <rich:modalPanel id="mpAviso" height="200" width="200" >
            <f:facet name="header">
                <h:outputText value="alerta"/>
            </f:facet>
            <f:facet name="controls">
                <h:panelGroup>
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('mpAviso')" title="Cerrar"/>
                </h:panelGroup>
            </f:facet>
            <a4j:form id="fAviso">
                <h:outputText value="#{managerInformes.w_mensaje_actual}" /><br />
                <div align="right">
                    <h:commandLink  value="revisar" action="#{managerFecha.irFechaContacto}" />
                </div>
            </a4j:form>
        </rich:modalPanel>
        <h:outputText rendered="#{managerInformes.w_visualizar}">
            <script>
            cargarModal();
            </script>
        </h:outputText>
        <jsp:include flush="false" page="modal/ModalAlumno.jsp" />
    </f:view>
</body>

</html>