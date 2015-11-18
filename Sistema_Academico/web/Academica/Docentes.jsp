<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<ui:composition xmlns="http://www.w3.org/1999/xhtml" xmlns:h="http://java.sun.com/jsf/html"
                xmlns:f="http://java.sun.com/jsf/core" xmlns:ui="http://java.sun.com/jsf/facelets" xmlns:a4j="http://richfaces.org/a4j"
                xmlns:rich="http://richfaces.org/rich"></ui:composition>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link href="../css/formulario.css" rel="stylesheet" media="screen"/>
            <title>Mantenimiento de Docentes</title>
            <style> 
                .index{z-index: 900000 !important;
                       background-color: #FFFFFF !important;
                       border-radius: 5px;

                }

                .odd-row {
                    background-color: #ECF3FE;
                }
                .even-row {
                    background-color: #FCFFFE;
                }
                .active-row {
                    background-color: #FFEBDA;
                }
            </style>
            <script type="text/javascript">
                function getRightTop(ref) {
                    var position = new Object();
                    position.top = 0; //ref.offsetTop;
                    position.left = 0; // ref.offsetLeft+ref.clientWidth+6;
                    return position;
                }

                function validar(e) {
                    tecla = (document.all) ? e.keyCode : e.which;
                    if (tecla == 8 || tecla == 0 || tecla == 9)
                        return true;
                    patron = /\d/;
                    te = String.fromCharCode(tecla);
                    //alert(tecla);
                    return patron.test(te);
                }
                function validarLetras(e) {
                    tecla = (document.all) ? e.keyCode : e.which;
                    if (tecla == 8 || tecla == 9)
                        return true;
                    patron = /[A-Za-z\s]/;
                    te = String.fromCharCode(tecla);
                    return patron.test(te);
                }

            </script> 
    </head>
    <body>
        <f:view >
            <jsp:include page="../TablaSistema/Menu.jsp"></jsp:include>

            <rich:panel style=" width : 100%; background:#FFF; border:1px !important;" >
                <f:facet name="FACULTADES">
                </f:facet>

                <h:form id="form1" style="width:100%">

                    <table width="100%" style="font-size:10px; font-family:verdana">
                        <tr >
                            <td width="20%" colspan="2" ><strong>MANTENIMIENTO DE DOCENTES</strong>
                            </td>

                            <td width="30%"></td>
                            <td width="30%" colspan="4" align="right">
                                <h:graphicImage value="/Imagenes/new-32.png" style="border-width: 0px;">
                                    <a4j:support event="onclick" actionListener="#{managedBeanDocente.limpiarData}"
                                                 oncomplete="#{managedBeanDocente.oncomplete}" reRender="fDocente,iMensaje"/>
                                </h:graphicImage>                         

                            </td>
                            <td width="30%"></td>
                           
                            <td width="30%" align="right"><h:graphicImage value="/Imagenes/printer-32.png" title="Imprimir Listado"/>
                            </td>
                            <td align=right width="50%">
                                <a4j:commandButton type="button" id="buscar"  value="Buscar" actionListener="#{managedBeanDocente.buscarDocenteEvento}" image="/Imagenes/search-32.png"  title="Buscar"
                                                   reRender="form1,barra" oncomplete="#{managedBeanDocente.oncomplete}"/>

                            </td>
                        </tr>
                        <tr>
                            <td colspan="10" width="100%"> 
                                <hr width="100%" size="1"></td>
                        </tr>
                        <tr>
                            <td width="20%" colspan="2">

                            </td>
                            <td width="30%"></td>
                            <td width="50%"></td>
                        </tr>
                        <tr>
                        </tr>
                        <tr style=" height : 24px;">
                            <td width="10%" >Codigo:
                            </td>
                            <td width="30%">
                                <h:inputText id="txtCodigo" value="#{managedBeanDocente.doc_dni_w}" style="width : 180px;" maxlength="10" onkeypress="return validar(event);"/>
                            </td>

                            <td width="10%"><h:outputText value="Facultades" rendered="#{managedBeanDocente.b_cursosA==1?true:false}"/></td>
                            <td width="30%">
                                <h:selectOneMenu id="FacultadFiltro" value="#{managedBeanDocente.b_facultad2}" rendered="#{managedBeanDocente.b_cursosA==1?true:false}">
                                    <f:selectItems value="#{managedBeanDocente.comboFacultades2}" />
                                    <a4j:support event="onchange"  reRender="form1"/>
                                </h:selectOneMenu>
                            </td>
                             <td width="10%"><h:outputText value="Semestres" rendered="#{managedBeanDocente.b_disponibilidadA==1?true:false}"/></td>    
                            <td width="30%">
                                 <h:selectOneMenu id="semestre_f" value="#{managedBeanDocente.sem_id}" rendered="#{managedBeanDocente.b_disponibilidadA==1?true:false}">
                                    <f:selectItems value="#{managedBeanDocente.comboSemestres}"/>
                                    <a4j:support event="onchange"  reRender="form1"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%">Nombre:
                            </td>
                            <td width="30%">
                                <h:inputText id="txtnombre" value="#{managedBeanDocente.doc_nombre_completo_w}" style="width : 180px;" onkeypress="return validarLetras(event);"/></td>
                            <td width="10%"><h:outputText value="Especialidades" rendered="#{managedBeanDocente.b_cursosA==1?true:false}"/></td>
                            <td width="30%">
                                <h:selectOneMenu  id="EspecialidadFiltro"  value="#{managedBeanDocente.b_especialidad2}" rendered="#{managedBeanDocente.b_cursosA==1?true:false}">
                                    <f:selectItems value="#{managedBeanDocente.comboEspecialidades2}" />
                                    <a4j:support event="onchange"  reRender="CiclosFiltro"/>
                                </h:selectOneMenu>
                            </td>
                            <td width="10%"><h:outputText value="Turno" rendered="#{managedBeanDocente.b_disponibilidadA==1?true:false}"/></td> 
                            <td width="30%">
                                <h:selectOneMenu value="#{managedBeanDocente.turno_id2}" id="Turnos2" rendered="#{managedBeanDocente.b_disponibilidadA==1?true:false}">
                                    <f:selectItems value="#{managedBeanDocente.comboTurnos2}"/>
                                    <a4j:support event="onchange" reRender="form1"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%">Estado asignación de disponibilidad:
                            </td>
                            <td width="30%">
                                <h:selectOneMenu  id="cboDisponibilidadA"  value="#{managedBeanDocente.b_disponibilidadA}" >
                                    <f:selectItems value="#{managedBeanDocente.cboDisponilidadA}" />
                                    <a4j:support event="onchange" action="#{managedBeanDocente.limpiarCombos2}" reRender="form1"/>
                                </h:selectOneMenu>
                            </td>
                            <td width="10%"><h:outputText value="Ciclos" rendered="#{managedBeanDocente.b_cursosA==1?true:false}"/></td>
                            <td  width="30%">
                                <h:selectOneMenu  id="CiclosFiltro"  value="#{managedBeanDocente.b_ciclos2}" rendered="#{managedBeanDocente.b_cursosA==1?true:false}">
                                    <f:selectItems value="#{managedBeanDocente.comboCiclos2}" />
                                    <a4j:support event="onchange"  reRender="form1"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%">Estado asignación de cursos: </td>
                            <td width="30%">
                                <h:selectOneMenu  id="cboCursosA"  value="#{managedBeanDocente.b_cursosA}" >
                                    <f:selectItems value="#{managedBeanDocente.cboCursosA}" />
                                    <a4j:support event="onchange" action="#{managedBeanDocente.limpiarCombos}" reRender="form1"/>
                                </h:selectOneMenu>
                            </td>
                            <td width="10%"><h:outputText value="Cursos" rendered="#{managedBeanDocente.b_cursosA==1?true:false}"/></td>
                            <td width="30%">
                                <h:selectOneMenu  id="CursosFiltro"  value="#{managedBeanDocente.b_cursos2}"  rendered="#{managedBeanDocente.b_cursosA==1?true:false}">
                                    <f:selectItems value="#{managedBeanDocente.comboCursos2}" />
                                    <a4j:support event="onchange"  reRender="form1"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <rich:simpleTogglePanel switchType="ajax" label="Reportes" height="90px" opened="false">
                                    <table>
                                        <tr>
                                            <td>
                                                <h:outputText value="Docentes Sin Asignación de Cursos"/>
                                            </td>
                                            <td>
                   
                                                <h:graphicImage value="/Imagenes/actions/mime_pdf.png" style="border-width: 0px;cursor: pointer" title="Docentes Sin Asignación de Cursos " >
                                                    <a4j:support event="onclick" actionListener="#{managedBeanDocente.ImprimirReporteCursosNoAsignado}" oncomplete="javascript:Richfaces.showModalPanel('mp13')" reRender="tituloNoDis1,reporteNoDis1"/>
                                                </h:graphicImage>
                                            </td>
                                            <td>
                                                <h:outputText value="Docentes Con Asignación de Cursos"/>
                                            </td>
                                            <td>
                                                <h:graphicImage value="/Imagenes/actions/mime_pdf.png" style="border-width: 0px;cursor: pointer" title="Docentes Con Asignación de Cursos">
                                                    <a4j:support event="onclick" actionListener="#{managedBeanDocente.ImprimirReporteCursosAsignado}" oncomplete="javascript:Richfaces.showModalPanel('mp13')" reRender="tituloNoDis1,reporteNoDis1"/>
                                                </h:graphicImage>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <h:outputText value="Docentes Con Disponibilidad"/>
                                            </td>
                                            <td>
                                                <h:graphicImage value="/Imagenes/actions/mime_pdf.png" style="border-width: 0px;cursor: pointer" title="Docentes Con Disponibilidad">
                                                    <a4j:support event="onclick" actionListener="#{managedBeanDocente.ImprimirReporteDocentesConDisponibilidad}" oncomplete="javascript:Richfaces.showModalPanel('mp13')" reRender="tituloNoDis1,reporteNoDis1"/>
                                                </h:graphicImage>
                                            </td>
                                            <td>
                                                <h:outputText value="Docentes Sin Disponibilidad"/>
                                            </td>
                                            <td>
                                                <h:graphicImage value="/Imagenes/actions/mime_pdf.png" style="border-width: 0px;cursor: pointer" title="Docentes Sin Disponibilidad">
                                                    <a4j:support event="onclick" actionListener="#{managedBeanDocente.ImprimirReporteDocentesSinDisponibilidad}" oncomplete="javascript:Richfaces.showModalPanel('mp13')" reRender="tituloNoDis1,reporteNoDis1"/>
                                                </h:graphicImage>
                                            </td>
                                        </tr>
                                    </table>

                                </rich:simpleTogglePanel>
                            </td>
                            <td></td><td></td>
                        </tr>
                        <tr>
                            <td></td><td></td><td></td><td></td>
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

                    <a4j:region  id = "innerRegion" >         
                        <h:outputText value="#{managedBeanDocente.mensaje}" id="iMensaje" style="color:red"/>
                        <rich:dataTable id="tablaMaster" rows="10"
                                        onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                        onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                        cellpadding="0" cellspacing="0"
                                        width="100%" border="0" value="#{managedBeanDocente.listaDocente}" var="doc">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Id" />
                                </f:facet>
                                <h:outputText value="#{doc.id}"/>
                                <f:param value="#{doc.id}" id="p_id2"/>
                                <f:param id="p_id" value="#{doc.id}"/>
                                <f:param id="p_doc_detalle" value="#{doc.docNombre}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Codigo" />
                                </f:facet>
                                <h:outputText value="#{doc.docDni}"  />
                            </h:column>

                            <h:column >
                                <f:facet name="header">
                                    <h:outputText value="Nombre" />
                                </f:facet>

                                <h:outputText value="#{doc.docNombre}" />
                            </h:column>

                            <h:column >
                                <f:facet name="header">
                                    <h:outputText value="Correo"/>
                                </f:facet>

                                <h:outputText value="#{doc.docCorreo}" >

                                </h:outputText>


                            </h:column>
                            <h:column >
                                <f:facet name="header">
                                    <h:outputText value="Telefono"/>
                                </f:facet>

                                <h:outputText value="#{doc.docTelefono}" >

                                </h:outputText>


                            </h:column>
                            <h:column >
                                <f:facet name="header">
                                    <h:outputText value="Situacion" />
                                </f:facet>

                                <h:outputText value="#{doc.docSituacion}">

                                </h:outputText>
                            </h:column>
                            <h:column >
                                <f:facet name="header">
                                    <h:outputText value="Periodo de Inicio" />
                                </f:facet>

                                <h:outputText value="#{doc.docPeriodoInicio}" >
                                    <f:convertDateTime pattern="dd/MM/yyyy"/>
                                </h:outputText>
                            </h:column>

                            <h:column >
                                <f:facet name="header">
                                    <h:outputText value="Sexo" />
                                </f:facet>
                                <h:outputText value="#{doc.docSexo}" >
                                </h:outputText>
                            </h:column>
                            <h:column >
                                <f:facet name="header">
                                    <h:outputText value="Nacimiento" />
                                </f:facet>
                                <h:outputText value="#{doc.docNacimiento}" >

                                </h:outputText>
                            </h:column>
                            <h:column >
                                <f:facet name="header">
                                    <h:outputText value="Residencia" />
                                </f:facet>

                                <h:outputText value="#{doc.docResidencia}" >

                                </h:outputText>
                            </h:column>

                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Horario"/>
                                </f:facet>
                                <a4j:commandButton image="#{doc.imagen_horario}"
                                                   title="Horario"
                                                   actionListener="#{managedBeanDocente.cargarHorario}"
                                                   oncomplete="#{managedBeanDocente.oncomplete}"
                                                   reRender="formHoraria,horPabellon,horAula"/>
                            </h:column>

                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Edit" />
                                </f:facet>
                                <p align="right">
                                    <a4j:commandButton id="btnEditar" image="/Imagenes/actions/editpaste.png" actionListener="#{managedBeanDocente.buscarDocentes}"
                                                       oncomplete="#{managedBeanDocente.oncomplete}" reRender="fDocente,p_id,rDistrito,rProvincia,rDepartamento,nDistrito,nProvincia,nDepartamento,iMensaje,tbEstPagDetEnlaz"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Eli" />
                                    </f:facet>
                                    <p align="right">
                                        <h:commandButton  image="/Imagenes/actions/no.png"  title="Eliminar" 
                                                          actionListener="#{managedBeanDocente.eliminarDocente}"
                                                          onclick="javascript:return (confirm('¿Esta realmente seguro de Eliminar?'));"/>

                                    </h:column>
                                </rich:dataTable>
                                <table style=" width : 100%;">
                                    <tr>
                                        <td colspan="5" style=" width : 100%">
                                            <p align="right"/>
                                            <rich:datascroller id="barra" align="right"  for="tablaMaster" maxPages="8"  style=" width : 100%;"/>
                                        </td>
                                    </tr>
                                </table>
                            </a4j:region>        
                        </h:form>
                    </rich:panel>
                    <a4j:region  id = "innerRegion" >      
                        <rich:modalPanel id="mpDocente" width="1100" height="620" >
                            <f:facet name="header">
                                <h:outputText value="Registro de Docentes" />
                            </f:facet>
                            <f:facet name="controls">
                                <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpDocente')" title="Cerrar" id="enviar"/>
                            </f:facet>
                            <h:form id="fDocente">
                                <table align="center" width="1000px" >
                                    <tr>
                                        <td align="right" colspan="4">
                                            <a4j:commandButton   image="/Imagenes/actions/filesave.png" title="Guardar" actionListener="#{managedBeanDocente.guardarDatos}" oncomplete="#{managedBeanDocente.oncomplete}"/>
                                        </td>
                                        <td>
                                            <h:graphicImage value="/Imagenes/actions/mime_pdf.png" style="border-width: 0px;cursor: pointer" title="Historial Cursos Asignados " rendered="#{managedBeanDocente.docente.id >0}">
                                                <a4j:support event="onclick" actionListener="#{managedBeanDocente.ImprimirHistorialDocente}" oncomplete="javascript:Richfaces.showModalPanel('mp12')" reRender="tituloNoDis,reporteNoDis"/>
                                            </h:graphicImage>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><h:outputText value="*DNI"/></td>
                                        <td><h:inputText id="i_dni" value="#{managedBeanDocente.docente.docDni}" onkeypress="return validar(event);" maxlength="8"/>
                                            <f:param id="p_id" value="#{managedBeanDocente.docente.id}"/>
                                            <f:param id="p_doc_detalle" value="#{managedBeanDocente.docente.docNombres}"/>
                                        </td>
                                        <td><h:outputText value="Nac. Departamento"/></td>
                                        <td colspan="2">
                                            <h:selectOneMenu id="nDepartamento" value="#{managedBeanDocente.w_depa_naci}">
                                                <f:selectItems value="#{managedBeanDocente.cboDepartamentos_n}" />
                                                <a4j:support event="onchange" reRender="nProvincia,nDistrito"/>
                                            </h:selectOneMenu>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><h:outputText value="*Paterno"/></td>
                                        <td><h:inputText id="i_paterno" value="#{managedBeanDocente.docente.docAppaterno}" onkeypress="return validarLetras(event);" /></td>
                                        <td><h:outputText value="Nac. Provincia"/></td>
                                        <td colspan="2">
                                            <h:selectOneMenu  id="nProvincia"  value="#{managedBeanDocente.w_prov_naci}" >
                                                <f:selectItems value="#{managedBeanDocente.cboProvincia_n}" />
                                                <a4j:support event="onchange" reRender="nDistrito"/>
                                            </h:selectOneMenu>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><h:outputText value="*Materno"/></td>
                                        <td><h:inputText id="i_materno" value="#{managedBeanDocente.docente.docApmaterno}"  onkeypress="return validarLetras(event);" /></td>
                                        <td><h:outputText value="Nac. Distrito"/></td>
                                        <td colspan="2">
                                            <h:selectOneMenu  id="nDistrito"  value="#{managedBeanDocente.docente.docNacimiento}" >
                                                <f:selectItems value="#{managedBeanDocente.cboDistrito_n}" />
                                            </h:selectOneMenu>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><h:outputText value="*Nombres"/></td>
                                        <td colspan="4">
                                            <h:inputText id="i_nombres" value="#{managedBeanDocente.docente.docNombres}"  onkeypress="return validarLetras(event);" />
                                        </td>

                                    </tr>

                                    <tr>
                                        <td><h:outputText value="Fecha Inicio"/></td>
                                        <td>
                                            <a4j:outputPanel id="calendar" layout="block">
                                                <rich:calendar  id="iCreacion"  value="#{managedBeanDocente.docente.docPeriodoInicio}" datePattern="dd/MM/yyyy"  />
                                            </a4j:outputPanel>
                                        </td>
                                        <td><h:outputText value="Res. Departamento"/></td>
                                        <td colspan="2">
                                            <h:selectOneMenu id="rDepartamento" value="#{managedBeanDocente.w_depa_resi}" >
                                                <f:selectItems value="#{managedBeanDocente.cboDepartamentos_r}" />
                                                <a4j:support event="onchange" reRender="rProvincia,rDistrito"/>
                                            </h:selectOneMenu>
                                        </td>

                                    </tr>

                                    <tr>
                                        <td><h:outputText value="*Telefono"/></td>
                                        <td><h:inputText label="Telefono" id="iTelefono" value="#{managedBeanDocente.docente.docTelefono}" style="width : 180px;" rendered="true" onkeypress="return validar(event);" /></td>
                                        <td><h:outputText value="Res. Provincia"/></td>
                                        <td colspan="2">
                                            <h:selectOneMenu  id="rProvincia"  value="#{managedBeanDocente.w_prov_resi}" >
                                                <f:selectItems value="#{managedBeanDocente.cboProvincia_r}" />
                                                <a4j:support event="onchange" reRender="rDistrito"/>
                                            </h:selectOneMenu>
                                        </td>
                                    </tr>
                                    <tr>

                                        <td><h:outputText value="*Correo"/></td>
                                        <td><h:inputText label="E-Mail" id="iCorreo" value="#{managedBeanDocente.docente.docCorreo}" onkeyup="return#{managedBeanDocente.docente.docCorreo}" style="width : 180px;" rendered="true" > 

                                            </h:inputText></td>
                                        <td><h:outputText value="Res. Distrito"/></td>
                                        <td colspan="2">
                                            <h:selectOneMenu  id="rDistrito"  value="#{managedBeanDocente.docente.docResidencia}">
                                                <f:selectItems value="#{managedBeanDocente.cboDistrito_r}" />
                                            </h:selectOneMenu>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><h:outputText value="*Sexo"/></td>
                                        <td>
                                            <h:selectOneRadio id="iSexo" value="#{managedBeanDocente.docente.docSexo}">
                                                <f:selectItems value="#{managedBeanDocente.rdoSexo}" />
                                            </h:selectOneRadio>
                                        </td>
                                        <td><h:outputText value="Situacion"/></td>
                                        <td colspan="2">
                                            <h:selectOneMenu id="iSituacion" value="#{managedBeanDocente.docente.docSituacion}" >
                                                <f:selectItems value="#{managedBeanDocente.cboSituacion}"/>
                                            </h:selectOneMenu>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td><h:outputText value="Tipo Docente"/></td>
                                        <td>
                                            <h:selectOneMenu id="iTipoDocente" value="#{managedBeanDocente.docente.docTipo}" >
                                                <f:selectItems value="#{managedBeanDocente.cboTipoDocente}"/>
                                            </h:selectOneMenu>
                                        </td>
                                        <td></td>
                                        <td></td>
                                    </tr>

                                    <tr>
                                        <td><h:outputText value="Facultad"/></td>
                                        <td>
                                            <h:selectOneMenu id="iFacultad" value="#{managedBeanDocente.docente.docFacId}" >
                                                <f:selectItems value="#{managedBeanDocente.b_cboFacultades}"/>
                                            </h:selectOneMenu>
                                        </td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td colspan="5"><strong>
                                                <h:outputText value="Lineamiento de Docente" style="text-size:24px;"/></strong></td>
                                    </tr>
                                    <tr>
                                        <td>Facultad:</td>
                                        <td colspan="2">
                                            <a4j:region id="Pro2">
                                                <h:selectOneMenu id="Facultad" value="#{managedBeanDocente.b_facultad}" style="width:240px">
                                                    <f:selectItems value="#{managedBeanDocente.comboFacultades}" />
                                                    <a4j:support event="onchange"  reRender="Especialidad"/>
                                                </h:selectOneMenu>

                                            </a4j:region>
                                        </td>
                                        <td></td>
                                        <td></td>

                                    </tr>

                                    <tr>
                                        <td>Especialidad:</td>
                                        <td colspan="4">
                                            <h:selectOneMenu  id="Especialidad"  value="#{managedBeanDocente.b_especialidad}"  style="width:220px">
                                                <f:selectItems value="#{managedBeanDocente.comboEspecialidades}" />
                                                <a4j:support event="onchange"  reRender="Ciclos"/>
                                            </h:selectOneMenu>
                                        </td>

                                    </tr>

                                    <tr>
                                        <td>Ciclos:</td>
                                        <td colspan="4">
                                            <h:selectOneMenu  id="Ciclos"  value="#{managedBeanDocente.b_ciclos}"  style="width:180px">
                                                <f:selectItems value="#{managedBeanDocente.comboCiclos}" />
                                                <a4j:support event="onchange"  reRender="Cursos"/>
                                            </h:selectOneMenu>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>Cursos:</td>
                                        <td colspan="4">
                                            <h:selectOneMenu  id="Cursos"  value="#{managedBeanDocente.b_cursos}"  style="width:220px">
                                                <f:selectItems value="#{managedBeanDocente.comboCursos}" />
                                                <a4j:support event="onchange"  reRender="Cursos,tbBtnEnlazar"/>
                                            </h:selectOneMenu>
                                        </td>

                                    </tr>
                                    <tr>
                                        <td>Asignación:</td>
                                        <td colspan="4">
                                            <h:selectOneMenu  id="Asignacion"  value="#{managedBeanDocente.b_asignacion}"  style="width:220px">
                                                <f:selectItems value="#{managedBeanDocente.cmbAsignacion}" />
                                            </h:selectOneMenu>
                                        </td>
                                        <td colspan="2">
                                            <a4j:commandButton image="/Imagenes/actions/edit_add.png"
                                                               title="Agregar"
                                                               actionListener="#{managedBeanDocente.agregarCurso}"
                                                               oncomplete="#{managedBeanDocente.oncomplete}"
                                                               reRender="barra,tblCurDoc" />

                                        </td>
                                        <td colspan="2" align="right">
                                            <a4j:commandButton image="/Imagenes/actions/filesave.png"
                                                               title="Guardar"
                                                               actionListener="#{managedBeanDocente.guardarCursos}"
                                                               oncomplete="#{managedBeanDocente.oncomplete}"
                                                               reRender="fDocente" />
                                        </td>
                                    </tr>    

                                    <tr>
                                        <td colspan="5">
                                            <rich:datascroller id="barra" align="right"  for="tblCurDoc" maxPages="4"  style=" width : 100%;"/>
                                            <rich:dataTable id="tblCurDoc" var="curDoc" width="100%"  rows="4"
                                                            value="#{managedBeanDocente.nLstDocenteCurso}"
                                                            onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                            onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'">
                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Id"/>
                                                    </f:facet>
                                                    <h:outputText value="#{curDoc.curdoc_id}" />
                                                </rich:column>
                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Docente"/>
                                                    </f:facet>
                                                    <h:outputText value="#{curDoc.acDocente.docNombre}"/>
                                                </rich:column>
                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Curso"/>
                                                    </f:facet>
                                                    <h:outputText value="#{curDoc.acCurso.curNombre}" rendered="#{curDoc.view_bool}"/>
                                                    <h:selectOneMenu value="#{curDoc.acCurso.id}" rendered="#{curDoc.editable_bool}">
                                                        <f:selectItems value="#{curDoc.cursos}"/>
                                                    </h:selectOneMenu>
                                                </rich:column>
                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Especialidad"/>
                                                    </f:facet>
                                                    <h:outputText value="#{curDoc.acCurso.esp.espNombre}"/>

                                                </rich:column>
                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Estado Asignacion"/>
                                                    </f:facet>
                                                    <h:outputText value="#{curDoc.estadoAsignacion}" rendered="#{curDoc.view_bool}"/>
                                                    <h:selectOneMenu value="#{curDoc.v_asignacion}" rendered="#{curDoc.editable_bool}">
                                                        <f:selectItems value="#{curDoc.cmbAsignacion}"/>
                                                    </h:selectOneMenu>
                                                </rich:column>


                                                <rich:column style="text-align: center;">
                                                    <f:facet name="header">
                                                        <h:outputText value="Editar"/>
                                                    </f:facet>
                                                    <a4j:commandButton actionListener="#{curDoc.edit}"
                                                                       image="/Imagenes/actions/klipper_dock.png" title="Editar"
                                                                       rendered="#{curDoc.view_bool}" reRender="tblCurDoc, barra"/>
                                                    <a4j:commandButton actionListener="#{curDoc.aceptar}" oncomplete="#{curDoc.oncomplete}"
                                                                       image="/Imagenes/actions/fileexport.png" title="Aceptar"
                                                                       rendered="#{curDoc.editable_bool}" reRender="tblCurDoc, barra"/>
                                                    <a4j:commandButton actionListener="#{curDoc.cancelar}"
                                                                       image="/Imagenes/actions/fileclose.png" title="Cancelar"
                                                                       rendered="#{curDoc.editable_bool}" reRender="tblCurDoc, barra"/>
                                                </rich:column>

                                                <f:param id="p_hor_pos" value="#{curDoc.posicion}"/>
                                                <rich:column style="text-align: center;">
                                                    <f:facet name="header">
                                                        <h:outputText value="Eliminar"/>
                                                    </f:facet>
                                                    <f:param id="id_hor_delete" value="#{curDoc.curdoc_id}"/>
                                                    <a4j:commandButton image="/Imagenes/actions/edit_remove.png"
                                                                       title="Eliminar"
                                                                       actionListener="#{managedBeanDocente.removerCurso}"
                                                                       reRender="tblCurDoc,barra"/>
                                                </rich:column>
                                            </rich:dataTable>
                                        </td>
                                    </tr>
                                </table>
                            </h:form>
                        </rich:modalPanel>
                    </a4j:region>      

                    <rich:modalPanel id="mpConfirmarEnlazar" width="300" height="100" zindex="3000" >
                        <f:facet name="header">
                            <h:outputText value="Confirmar Enlace de Docentes"/>
                        </f:facet>
                        <f:facet name="controls">
                            <h:panelGroup>
                                <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                                onclick="Richfaces.hideModalPanel('mpConfirmarEnlazar')" title="Cerrar"/>
                            </h:panelGroup>
                        </f:facet>
                        <h:form id="frmConfirmEnlaceEstPagDet">
                            <table style="width: 100%;height: 100%;">
                                <tr>
                                    <th colspan="2">
                                        <h:outputText value="¿Seguro que desea asignar como curso al grupo de lineamiento para este docente?" />
                                    </th> 
                                </tr>
                                <tr>
                                    <th>
                                        <f:param id="p_id02" value="#{managedBeanDocente.docente.id}"/>
                                        <a4j:commandButton actionListener="#{managedBeanDocente.enlazarCursoDocente}" 
                                                           reRender="tbEstPagDetEnlaz, masterScroll" oncomplete="#{managedBeanDocente.oncomplete}"
                                                           value="Enlazar"
                                                           title="Enlazar" style="width: 100px; height: 30px;" />

                                    </th>
                                    <th>
                                        <a4j:commandButton onclick="Richfaces.hideModalPanel('mpConfirmarEnlazar')"
                                                           value="Cancelar"
                                                           title="Cancelar" style="width: 100px; height: 30px;"/>
                                    </th>
                                </tr>
                            </table>
                        </h:form>
                    </rich:modalPanel>

                    <rich:modalPanel id="mpHoraria" minWidth="600" height="400"
                                     resizeable="true" zindex="3000" style="overflow-y: scroll">
                        <f:facet name="header">
                            <h:outputText value="Horario"/>
                        </f:facet>
                        <f:facet name="controls">
                            <h:panelGroup>
                                <h:graphicImage value="/Imagenes/actions/stop.png"
                                                style="cursor:pointer" title="Cerrar"
                                                onclick="Richfaces.hideModalPanel('mpHoraria')"/>
                            </h:panelGroup>
                        </f:facet>
                        <h:form id="formHoraria">
                            <table width="100%" style="font-size:12px; font-family:verdana">
                                <tr>
                                    <td colspan="2" align="right">
                                        <a4j:commandButton image="/Imagenes/actions/filesave.png"
                                                           title="Guardar"
                                                           actionListener="#{managedBeanDocente.Insertar}"
                                                           oncomplete="#{managedBeanDocente.oncomplete}"
                                                           reRender="tablaHorario"
                                                           >
                                            <a4j:support event="oncomplete" reRender="form1,barra,tablaMaster,sisevaluacion,tablaHorario"/>
                                        </a4j:commandButton>
                                        <h:inputHidden value="#{managedBeanDocente.id_doc_horario}" id="id_doc_horario"/>
                                    </td>
                                </tr>
                                <tr><td colspan="2"><hr width="100%"/></td></tr>
                                <tr>
                                    <td class="tdLabel" style="width: 100px;"><h:outputText value="Docente"/></td>
                                    <td><h:outputText value="#{managedBeanDocente.doc_id}"/></td>
                                    <td><h:outputText value="#{managedBeanDocente.doc_detalle}"/></td>
                                </tr>

                                <tr>
                                    <td width="20%">Turno:</td>
                                    <td><h:selectOneMenu value="#{managedBeanDocente.turno_id}" id="turnos">
                                            <f:selectItems value="#{managedBeanDocente.comboTurnos}"/>
                                            <a4j:support event="onchange" action="#{managedBeanDocente.setearTurno}" reRender="tablaHorario,aulas,tipos,docentes"/>
                                        </h:selectOneMenu></td>
                                </tr>

                                <tr><td colspan="2"><hr width="100%"/></td></tr>
                                <tr>
                                    <td colspan="4" align="center" valign="top">

                                        <rich:effect  name="hideDiv"  for="contentDiv" type="Fade" />
                                        <rich:effect  name="showDiv"  for="contentDiv" type="Appear" />
                                        <div id="contentDiv">
                                            <rich:panel id="sisevaluacion">
                                                <rich:dataTable id="tablaHorario" rows="10"
                                                                onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                                onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                                cellpadding="0" cellspacing="0" width="100%" border="0"
                                                                value="#{managedBeanDocente.tablaHorario}" var="horario">

                                                    <f:param value="#{managedBeanDocente.turno_id}" id="ho_turno"/>
                                                    <f:param value="#{managedBeanDocente.doc_id}" id="ho_docente"/>
                                                    <f:param value="#{horario.id_hor}" id="param_id_turno"/>
                                                    <f:param value="#{horario.listaHorario}" id="ho_horarios"/>
                                                    <f:param value="#{horario.ide}" id="ho_ide"/>

                                                    <h:column>
                                                        <f:facet name="header">
                                                            <h:outputText value="Id" />
                                                        </f:facet>
                                                        <h:outputText value="#{horario.id_hor}"/>
                                                    </h:column>
                                                    <h:column>
                                                        <f:facet name="header">
                                                            <h:outputText value="Hora Inicio" />
                                                        </f:facet>
                                                        <h:outputText value="#{horario.inicio_hor}"/>
                                                    </h:column>
                                                    <h:column>
                                                        <f:facet name="header">
                                                            <h:outputText value="Hora Fin" />
                                                        </f:facet>
                                                        <h:outputText value="#{horario.fin_hor}"/>
                                                    </h:column>
                                                    <h:column>
                                                        <f:facet name="header">
                                                            <h:outputText value="Lunes" />
                                                        </f:facet>
                                                        <f:param value="#{horario.indice_id_lun}" id="param_identificador_lunes"/>
                                                        <h:inputHidden value="#{horario.indice_id_lun}"/>
                                                        <rich:panel id="panelLunes" style="#{horario.bColorCelda_Lunes}">
                                                            <h:outputText value="#{horario.lunes_hor}" id="lunes" escape="false"/>
                                                            <a4j:support actionListener="#{horario.seleccionarLunes}"
                                                                         immediate="true" event="onclick" reRender="tablaHorario"
                                                                         oncomplete="#{horario.oncomplete}"/>
                                                        </rich:panel>
                                                    </h:column>
                                                    <h:column>
                                                        <f:facet name="header">
                                                            <h:outputText value="Martes" />
                                                        </f:facet>
                                                        <f:param value="#{horario.indice_id_mar}" id="param_identificador_martes"/>
                                                        <h:inputHidden value="#{horario.indice_id_mar}"/>
                                                        <rich:panel id="panelMartes" style="#{horario.bColorCelda_Martes}">
                                                            <h:outputText value="#{horario.martes_hor}" id="martes" escape="false"/>
                                                            <a4j:support actionListener="#{horario.seleccionarMartes}"
                                                                         immediate="true" event="onclick"  reRender="tablaHorario"
                                                                         oncomplete="#{horario.oncomplete}"/>
                                                        </rich:panel>
                                                    </h:column>
                                                    <h:column>
                                                        <f:facet name="header">
                                                            <h:outputText value="Miercoles" />
                                                        </f:facet>
                                                        <f:param value="#{horario.indice_id_mie}" id="param_identificador_miercoles"/>
                                                        <h:inputHidden value="#{horario.indice_id_mie}"/>
                                                        <rich:panel id="panelMiercoles" style="#{horario.bColorCelda_Miercoles}">
                                                            <h:outputText value="#{horario.miercoles_hor}" id="miercoles" escape="false"/>
                                                            <a4j:support actionListener="#{horario.seleccionarMiercoles}"
                                                                         immediate="true" event="onclick"  reRender="tablaHorario"
                                                                         oncomplete="#{horario.oncomplete}"/>
                                                        </rich:panel>
                                                    </h:column>
                                                    <h:column>
                                                        <f:facet name="header">
                                                            <h:outputText value="Jueves" />
                                                        </f:facet>
                                                        <f:param value="#{horario.indice_id_jue}" id="param_identificador_jueves"/>
                                                        <h:inputHidden value="#{horario.indice_id_jue}"/>
                                                        <rich:panel id="panelJueves" style="#{horario.bColorCelda_Jueves}">
                                                            <h:outputText value="#{horario.jueves_hor}" id="jueves" escape="false"/>
                                                            <a4j:support actionListener="#{horario.seleccionarJueves}"
                                                                         immediate="true" event="onclick"  reRender="tablaHorario"
                                                                         oncomplete="#{horario.oncomplete}"/>
                                                        </rich:panel>
                                                    </h:column>
                                                    <h:column>
                                                        <f:facet name="header">
                                                            <h:outputText value="Viernes" />
                                                        </f:facet>
                                                        <f:param value="#{horario.indice_id_vie}" id="param_identificador_viernes"/>
                                                        <h:inputHidden value="#{horario.indice_id_vie}"/>
                                                        <rich:panel id="panelViernes" style="#{horario.bColorCelda_Viernes}">
                                                            <h:outputText value="#{horario.viernes_hor}" id="viernes" escape="false"/>
                                                            <a4j:support actionListener="#{horario.seleccionarViernes}"
                                                                         immediate="true" event="onclick"  reRender="tablaHorario"
                                                                         oncomplete="#{horario.oncomplete}"/>
                                                        </rich:panel>
                                                    </h:column>
                                                    <h:column>
                                                        <f:facet name="header">
                                                            <h:outputText value="Sabado" />
                                                        </f:facet>
                                                        <rich:panel id="panelSabado" style="#{horario.bColorCelda_Sabado}">
                                                            <f:param value="#{horario.indice_id_sab}" id="param_identificador_sabado"/>
                                                            <h:inputHidden value="#{horario.indice_id_sab}"/>
                                                            <h:outputText value="#{horario.sabado_hor}" id="sabado" escape="false"/>
                                                            <a4j:support actionListener="#{horario.seleccionarSabado}"
                                                                         immediate="true" event="onclick"  reRender="tablaHorario"
                                                                         oncomplete="#{horario.oncomplete}"/>
                                                        </rich:panel>
                                                    </h:column>
                                                    <h:column>
                                                        <f:facet name="header">
                                                            <h:outputText value="Domingo" />
                                                        </f:facet>
                                                        <rich:panel id="panelDomingo" style="#{horario.bColorCelda_Domingo}">
                                                            <f:param value="#{horario.indice_id_dom}" id="param_identificador_domingo"/>
                                                            <h:inputHidden value="#{horario.indice_id_dom}"/>
                                                            <h:outputText value="#{horario.domingo_hor}" id="domingo" escape="false"/>
                                                            <a4j:support actionListener="#{horario.seleccionarDomingo}"
                                                                         immediate="true" event="onclick"  reRender="tablaHorario"
                                                                         oncomplete="#{horario.oncomplete}"/>
                                                        </rich:panel>
                                                    </h:column>
                                                </rich:dataTable>
                                            </rich:panel>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </h:form>
                    </rich:modalPanel>
                    <rich:modalPanel id="mp12" width="700" height="490">
                        <f:facet name="header">
                            <h:outputText id="tituloNoDis" value="#{managedBeanDocente.tituloReporte}" />
                        </f:facet>
                        <f:facet name="controls">
                            <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp12')" title="Cerrar"/>
                        </f:facet>
                        <h:form>
                            <table>
                                <tr>
                                    <td align="center" valign="middle">
                                        <rich:panel>
                                            <a4j:mediaOutput id="reporteNoDis" uriAttribute="data" style="width : 650px; height: 420px;" element="object" standby="cargando..." createContent="#{managedBeanDocente.cargarReporteHistorialDocente}"  cacheable="false" mimeType="application/pdf" value="#{managedBeanDocente.valorRep}"/>
                                        </rich:panel>
                                    </td>
                                </tr>
                            </table>
                        </h:form>
                    </rich:modalPanel>
                    <rich:modalPanel id="mp13" width="700" height="490">
                        <f:facet name="header">
                            <h:outputText id="tituloNoDis1" value="#{managedBeanDocente.tituloReporte}" />
                        </f:facet>
                        <f:facet name="controls">
                            <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp13')" title="Cerrar"/>
                        </f:facet>
                        <h:form>
                            <table>
                                <tr>
                                    <td align="center" valign="middle">
                                        <rich:panel>
                                            <a4j:mediaOutput id="reporteNoDis1" uriAttribute="data" style="width : 650px; height: 420px;" element="object" standby="cargando..." createContent="#{managedBeanDocente.cargarReportePdf}"  cacheable="false" mimeType="application/pdf" value="#{managedBeanDocente.valorRep}"/>
                                        </rich:panel>
                                    </td>
                                </tr>
                            </table>
                        </h:form>
                    </rich:modalPanel>                    
                                        

                </f:view>
                </body>
                </html>  


