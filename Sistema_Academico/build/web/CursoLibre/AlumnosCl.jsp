<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Inscripcion de Alumnos - CL</title>
    </head>
    <f:view>
        <body onload="hideOkDiv();hideErrorDiv();">
            <jsp:include page="../TablaSistema/Menu.jsp"/>
            <rich:panel>
                <h:form id="form1" style="width:100%">
                    <table width="100%" style="font-size:10px; font-family:verdana;"  cellspacing="0" border="0" cellpadding="0">
                        <tr>
                            <td width="20%" colspan="2"><strong>MANTENIMIENTO DE ALUMNOS</strong></td>
                            <td width="30%" align="right"/>
                                <rich:spacer width="8px"/>

                                <h:graphicImage value="/Imagenes/actions/filenew.png" style="cursor: pointer;"
                                                title="Nuevo alumno" width="16">
                                    <a4j:support event="onclick"  oncomplete="Richfaces.showModalPanel('mpUpdate',{top:100})"
                                                 action="#{managerCLAlumno.nuevo}"
                                                 reRender="falumno"/>
                                </h:graphicImage>
                                <rich:spacer width="8px"/>

                                <h:graphicImage value="/Imagenes/actions/pencil.png" style="cursor: pointer;"
                                                title="Alumno de la universidad" width="16">
                                    <a4j:support event="onclick"  oncomplete="Richfaces.showModalPanel('mpUniv',{top:150})"
                                                 action="#{managerCLAlumno.nuevoUniv}"
                                                 reRender="univCodigo, univ_pate, univ_mate, univ_nomb, univ_esp, univOk, univError, univ_imagen"/>
                                </h:graphicImage>
                                <rich:spacer width="8px"/>

                                <a4j:commandButton id="buscar" action="#{managerCLAlumno.seleccionar}"
                                                   image="/Imagenes/actions/viewmag.png" title="Buscar"
                                                   oncomplete="#{managerCLAlumno.oncomplete}"
                                                   reRender="tablaMaster,barra" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="7"><hr width="100%" size="1"></td>
                        </tr>
                        <tr>
                        </tr>
                        <tr style="height: 20px">
                            <td width="10%"><h:outputText value="C�digo:"/></td>
                            <td width="30%">
                                <h:inputText styleClass="dr-spnr-i rich-spinner-input" value="#{managerCLAlumno.b_codigo}" style="width : 180px;"/>
                            </td>
                            <td width="10%" align="right"></td>
                            <td></td>
                        </tr>
                        <tr style="height: 20px">
                            <td width="10%">Ap. Paterno:</td>
                            <td width="30%">
                                <h:inputText value="#{managerCLAlumno.b_paterno}" style="width : 180px;"/>
                            </td>
                            <td width="10%" align="right"></td>
                            <td>

                            </td>
                        </tr>
                        <tr style="height: 20px">
                            <td width="10%">Ap. Materno:</td>
                            <td><h:inputText value="#{managerCLAlumno.b_materno}" style="width : 180px;"/></td>
                            <td  align="right"></td>
                            <td>

                            </td>
                        </tr>
                        <tr style="height: 20px">
                            <td width="10%">Nombres:</td>
                            <td><h:inputText value="#{managerCLAlumno.b_nombre}" style="width : 180px;"/></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td colspan="7" width="100%"><hr width="100%" size="1"></td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                        </tr>
                    </table>
                </h:form>
                <h:form style=" width : 100%;">
                    <table style=" width : 100%;"  cellspacing="0" border="0" cellpadding="0">
                        <tr>
                            <td colspan="5" style=" width : 100%">
                                <p align="right"/>
                                <rich:datascroller id="barra" align="right"  for="tablaMaster" maxPages="8"  style=" width : 100%;"/>
                            </td>
                        </tr>
                    </table>
                    <rich:dataTable id="tablaMaster"  rows="10" onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                    cellpadding="0" cellspacing="0" width="100%" border="0" value="#{managerCLAlumno.listaAlumno}" var="Alu">
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Id" />
                            </f:facet>
                            <h:outputText value="#{Alu.alu_id}"/>
                            <f:param id="p_alu_id" value="#{Alu.alu_id}"/>
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Codigo" />
                            </f:facet>
                            <h:outputText value="#{Alu.alu_codigo}"/>
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Apellidos y Nombres" />
                            </f:facet>
                            <h:outputText value="#{Alu.alu_appaterno} #{Alu.alu_apmaterno} #{Alu.alu_nombres}"/>
                            <f:param id="p_alu_paterno" value="#{Alu.alu_appaterno}"/>
                            <f:param id="p_alu_materno" value="#{Alu.alu_apmaterno}"/>
                            <f:param id="p_alu_nombres" value="#{Alu.alu_nombres}"/>
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Unidad" />
                            </f:facet>
                            <h:outputText value="#{Alu.b_unidad}"/>
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Forma Pago" />
                            </f:facet>
                            <h:outputText value="#{Alu.b_forma_pago}"/>
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Procedencia" />
                            </f:facet>
                            <h:outputText value="#{Alu.b_procedencia}"/>
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Parentesco" />
                            </f:facet>
                            <h:outputText value="#{Alu.b_parentesco}"/>
                        </rich:column>

                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Editar" />
                            </f:facet>
                            <a4j:commandButton image="/Imagenes/actions/editpaste.png" title="Editar"
                                               oncomplete="javascript:Richfaces.showModalPanel('mpUpdate',{top:100})"
                                               actionListener="#{managerCLAlumno.editarAlumno}"
                                               reRender="falumno"
                                               immediate="true" />
                        </rich:column>

                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Enlazar" />
                            </f:facet>
                            <a4j:commandButton image="/Imagenes/actions/enlazar.png" title="Enlazar con la universidad"
                                               oncomplete="javascript:Richfaces.showModalPanel('mpEnlace',{top:100})"
                                               actionListener="#{managerCLAlumno.enlaceUniversidad}"
                                               reRender="enlForm, tablaEnlace"
                                               immediate="true" />
                        </rich:column>

                        <rich:column style="text-align: center;">
                            <f:facet name="header" >
                                <h:outputText value="Eliminar" />
                            </f:facet>
                            <h:commandButton  image="/Imagenes/actions/delete.gif"
                                              style="height:14"  title="Eliminar"
                                              actionListener="#{managerCLAlumno.eliminarAlumno}"
                                              action="#{managerCLAlumno.seleccionar}"
                                              onclick="javascript:return (confirm('�Esta realmente seguro de Eliminar?'));"/>
                        </rich:column>
                    </rich:dataTable>
                </h:form>
            </rich:panel>

            <jsp:include flush="false" page="modal/ModalAlumno.jsp" />

            <rich:modalPanel  id="mpUniv" autosized="true"
                              minWidth="500" zindex="2000"
                              onhide="hideOkDiv();hideErrorDiv();">
                <f:facet name="header">
                    <h:outputText value="Registro Alumno" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png"
                                    style="cursor:pointer" title="Cerrar"
                                    onclick="Richfaces.hideModalPanel('mpUniv')"/>
                </f:facet>
                <h:form id="univFind">
                    <table width="100%" style="font-size:10px; font-family:verdana" cellspacing="0" border="0">
                        <tr style="height: 45px">
                            <td><h:outputLabel value="Ingrese codigo de la universidad"/></td>
                            <td><h:inputText id="univCodigo" value="#{managerCLAlumno.univ_codigo}" maxlength="8"/></td>
                            <td><a4j:commandButton value="Buscar alumno" 
                                               action="#{managerCLAlumno.buscarUniversidad}"
                                               oncomplete="#{managerCLAlumno.oncomplete}"
                                               reRender="univ_pate, univ_mate, univ_nomb, univ_esp, univ_imagen, univOk, univError" />
                            </td>
                        </tr>
                    </table>
                </h:form>
                <h:form id="univForm">
                    <rich:effect  name="hideOkDiv"  for="divOk" type="BlindUp" />
                    <rich:effect  name="showOkDiv"  for="divOk" type="Appear" />
                    <div id="divOk">
                        <rich:panel id="univOk">
                            <table width="100%" style="font-size:10px; font-family:verdana" cellspacing="0" border="0">
                                <tr>
                                    <td colspan="3" align="right">
                                        <a4j:commandButton image="/Imagenes/actions/filesave.png" title="Guardar"
                                                           oncomplete="#{managerCLAlumno.oncomplete}"
                                                           action="#{managerCLAlumno.salvarAlumnoUniversidad}"/>
                                    </td>
                                </tr>
                                <tr><td colspan="3"><hr width="100%" /></tr>
                                <tr>
                                    <td width="105px" class="tdLabel"><h:outputText value="Apellido paterno:" /></td>
                                    <td><h:outputLabel id="univ_pate" value="#{managerCLAlumno.univ_alumno.aluAppaterno}" /></td>
                                    <td rowspan="4">
                                        <a4j:outputPanel id="univ_imagen" ajaxRendered="true" layout="block">
                                            <a4j:mediaOutput style="width : 100px;" element="img" cacheable="false"
                                                             rendered="true" standby="cargando..."
                                                             createContent="#{managerCLAlumno.imagen}"
                                                             mimeType="image/png" value="#{managerCLAlumno.univ_alumno.aluCodigo}">
                                                <f:param value="#{managerCLAlumno.timeStamp}" name="time"/>
                                            </a4j:mediaOutput>
                                        </a4j:outputPanel>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="tdLabel"><h:outputText value="Apellido materno:" /></td>
                                    <td><h:outputLabel id="univ_mate" value="#{managerCLAlumno.univ_alumno.aluApmaterno}" /></td>
                                </tr>
                                <tr>
                                    <td class="tdLabel"><h:outputText value="Nombre:" /></td>
                                    <td><h:outputLabel id="univ_nomb" value="#{managerCLAlumno.univ_alumno.aluNombres}" /></td>
                                </tr>
                                <tr>
                                    <td class="tdLabel"><h:outputText value="Especialidad:" /></td>
                                    <td><h:outputLabel id="univ_esp" value="#{managerCLAlumno.univ_alumno.esp.espNombre}" /></td>
                                </tr>
                            </table>
                        </rich:panel>
                    </div>
                    <rich:effect  name="hideErrorDiv"  for="divError" type="BlindUp" />
                    <rich:effect  name="showErrorDiv"  for="divError" type="Appear" />
                    <div id="divError">
                        <rich:panel id="univError">
                            <h:outputText value="No se encuentra algun alumno con dicho codigo."
                                          style="font-weight: bold;font-size: 16px;color: #cc0000;"/>
                        </rich:panel>
                    </div>
                </h:form>
            </rich:modalPanel>

            <rich:modalPanel  id="mpEnlace" autosized="true"
                              minWidth="500" zindex="2000">
                <f:facet name="header">
                    <h:outputText value="Relacionar Alumno con universidad" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png"
                                    style="cursor:pointer" title="Cerrar"
                                    onclick="Richfaces.hideModalPanel('mpEnlace')"/>
                </f:facet>
                <h:form id="enlForm">
                    <table width="100%" style="font-size:10px; font-family:verdana;" cellspacing="0" border="0">
                        <tr>
                            <td class="tdLabel"><h:outputText value="Codigo:"/></td>
                            <td><h:outputText value="#{managerCLAlumno.enl_alumno.aluCodigo}"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Nombre:"/></td>
                            <td><h:outputText value="#{managerCLAlumno.enl_alumno.aluAppaterno} #{managerCLAlumno.enl_alumno.aluApmaterno}, #{managerCLAlumno.enl_alumno.aluNombres}"/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr width="100%"/></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <rich:dataTable id="tablaEnlace"  rows="10" onRowMouseOver="this.style.backgroundColor='#999999'"
                                                onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                cellpadding="0" cellspacing="0" width="100%" border="0" value="#{managerCLAlumno.listaEnlace}" var="alu">
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Id" />
                                        </f:facet>
                                        <h:outputText value="#{alu.id}"/>
                                        <f:param id="enl_alu_id" value="#{alu.id}" />
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Codigo" />
                                        </f:facet>
                                        <h:outputText value="#{alu.aluCodigo}"/>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Ap. paterno" />
                                        </f:facet>
                                        <h:outputText value="#{alu.aluAppaterno}"/>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Ap. Materno" />
                                        </f:facet>
                                        <h:outputText value="#{alu.aluApmaterno}"/>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Nombre" />
                                        </f:facet>
                                        <h:outputText value="#{alu.aluNombres}"/>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Especialidad" />
                                        </f:facet>
                                        <h:outputText value="#{alu.esp.espNombre}"/>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Foto" />
                                        </f:facet>
                                        <a4j:outputPanel id="pan_imagen" ajaxRendered="true" layout="block">
                                            <a4j:mediaOutput style="width : 100px;" element="img" cacheable="false"
                                                             rendered="true" standby="cargando..."
                                                             createContent="#{managerCLAlumno.imagen}"
                                                             mimeType="image/png" value="#{alu.aluCodigo}">
                                                <f:param value="#{managerCLAlumno.timeStamp}" name="time"/>
                                            </a4j:mediaOutput>
                                        </a4j:outputPanel>
                                    </rich:column>

                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Aceptar" />
                                        </f:facet>
                                        <h:commandButton image="/Imagenes/actions/button_ok.png"
                                                         title="Guardar"
                                                         actionListener="#{managerCLAlumno.relacionarUniv}"
                                                         action="#{managerCLAlumno.seleccionar}"
                                                         onclick="javascript:return (confirm('�Es correcto el alumno seleccionado?'));"/>
                                    </rich:column>
                                </rich:dataTable>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr width="100%"/></td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>
        </body>
    </f:view>
</html>