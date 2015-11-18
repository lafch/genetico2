<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Mantenimiento de Especialidades</title>
        <script type="text/javascript">
            function getRightTop(ref) {
                var position = new Object();
                position.top = 0; //ref.offsetTop;
                position.left =0; // ref.offsetLeft+ref.clientWidth+6;
                return position;
            }
        </script> 
    </head>
    <body>
        <f:view >
            <jsp:include page="../TablaSistema/Menu.jsp"></jsp:include>
            <rich:panel style=" width : 100%;" >
                <f:facet name="FACULTADES">
                </f:facet>
                <h:form id="form1" style="width:100%">
                    <table width="100%" style="font-size:10px; font-family:verdana"  cellspacing="0" border="0" cellpadding="0">
                        <tr >
                            <td width="20%" colspan="2" ><strong>MANTENIMIENTO DE ESPECIALIDADES</strong>
                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right">

                                <a href="javascript:Richfaces.showModalPanel('mp',{width:450, top:130})">
                                    <h:graphicImage value="/Imagenes/actions/filenew.png" style="border-width: 0px;"/>
                                </a>


                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right"><h:graphicImage value="/Imagenes/actions/fileprint.png" title="Imprimir Listado"/>
                            </td>

                            <td align=right width="50%">
                                <h:commandButton type="button" id="buscar" value="" action="#{managerEspecialidad.Seleccionar}" image="/Imagenes/actions/viewmag.png"  title="Buscar"/>


                            </td>

                        </tr>
                        <tr>
                            <td colspan="7" width="100%">
                                <hr width="100%" size="1"></td>
                        </tr>
                        <tr>
                            <td width="20%" colspan="2">

                            </td>
                            <td width="30%"></td>
                            <td width="50%"></td>
                        </tr>
                        <tr>
                            <td>
					Facultad:
                            </td>
                            <td>
                                <h:selectOneMenu id="oFacultad" value="#{managerEspecialidad.facultad}" style="width : 180px;">
                                    <f:selectItems value="#{managerEspecialidad.comboFacultades}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td width="10%" >Codigo:
                            </td>
                            <td width="30%">
                                <h:inputText id="txtCodigo" value="#{managerEspecialidad.b_codigo}" style="width : 180px;" maxlength="10"/></td>
                            <td width="50%">

                            </td>
                        </tr>
                        <tr>
                            <td width="10%">Nombre:
                            </td>
                            <td width="30%">
                                <h:inputText value="#{managerEspecialidad.b_nombre}" style="width : 180px;"/></td>
                            <td width="50%"></td>
                        </tr>
                        <tr>
                            <td></td><td></td><td></td><td></td>
                        </tr>
                        <tr>
                            <td colspan="7" width="100%">
                                <hr width="100%" size="1">
                            </td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                        </tr>
                    </table>
                </h:form>
                <h:form style=" width : 100%;">
                    <table style=" width : 100%;">
                        <tr>
                            <td colspan="5" style=" width : 100%">
                                <p align="right"/>
                                <rich:datascroller id="barra" align="right"  for="tablaMaster" maxPages="8"  style=" width : 100%;"/>
                            </td>
                        </tr>
                    </table>

                    <rich:dataTable id="tablaMaster" rows="10"
                                    onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                    cellpadding="0" cellspacing="0"
                                    width="100%" border="0" value="#{managerEspecialidad.listaEspecialidad}" var="especialidad">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id" />
                            </f:facet>
                            <h:outputText value="#{especialidad.b_id}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Codigo" />
                            </f:facet>
                            <h:outputText value="#{especialidad.b_codigo}" rendered="#{especialidad.view}" />
                            <h:inputText value="#{especialidad.b_codigo}" rendered="#{especialidad.editable}" maxlength="10"/>
                        </h:column>

                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Nombre" />
                            </f:facet>

                            <h:outputText value="#{especialidad.b_nombre}" rendered="#{especialidad.view}"/>
                            <h:inputText value="#{especialidad.b_nombre}" rendered="#{especialidad.editable}"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Resolucion" />
                            </f:facet>

                            <h:outputText value="#{especialidad.b_resolrectoral}" rendered="#{especialidad.view}">

                            </h:outputText>
                            <h:inputText value="#{especialidad.b_resolrectoral}" rendered="#{especialidad.editable}" style=" width : 112px;"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Fecha Creacion"/>
                            </f:facet>

                            <h:outputText value="#{especialidad.b_fechacreacion}" rendered="#{especialidad.view}">
                                <f:convertDateTime pattern="dd/MM/yyyy"/>
                            </h:outputText>
                            <a4j:outputPanel id="calendar" layout="block">
                                <rich:calendar value="#{especialidad.b_fechacreacion}"  datePattern="dd/MM/yyyy"   rendered="#{especialidad.visible}"  style=" width : 132px;"/>
                            </a4j:outputPanel>
                            <a4j:support event="onclick"  reRender="calendar"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Nro Periodos" />
                            </f:facet>

                            <h:outputText value="#{especialidad.b_numero_periodos}" rendered="#{especialidad.view}">

                            </h:outputText>
                            <h:inputText value="#{especialidad.b_numero_periodos}" rendered="#{especialidad.editable}" style=" width : 31px;"/>
                        </h:column>

                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Tipo Periodos" />
                            </f:facet>

                            <h:outputText value="#{especialidad.b_tipo_periodos}" rendered="#{especialidad.view}">

                            </h:outputText>
                            <h:selectOneMenu value="#{especialidad.b_tipo_periodos}" rendered="#{especialidad.editable}">
                                <f:selectItems value="#{especialidad.comboTipos}" />
                            </h:selectOneMenu>
                        </h:column>

                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Facultad" />
                            </f:facet>

                            <h:outputText value="#{especialidad.b_idfacultad}" rendered="#{especialidad.view}">

                            </h:outputText>
                            <h:selectOneMenu value="#{especialidad.b_idfacultad}" rendered="#{especialidad.editable}">
                                <f:selectItems value="#{especialidad.comboFacultades}" />
                            </h:selectOneMenu>
                        </h:column>

                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Edit" />
                            </f:facet>
                            <p align="right">
                                <h:commandButton actionListener="#{especialidad.EditarFila}" value="" image="/Imagenes/actions/klipper_dock.png" title="Editar" rendered="#{especialidad.view}"/>

                                <f:param id="id" value="#{especialidad.b_id}" />
                                <f:param id="codigo" value="#{especialidad.b_codigo}" />
                                <f:param id="nombre"   value="#{especialidad.b_nombre}" />
                                <f:param id="facultad"   value="#{especialidad.b_idfacultad}" />
                                <f:param id="resolucion"   value="#{especialidad.b_resolrectoral}" />
                                <f:param id="creacion"   value="#{especialidad.b_fechacreacion}" />
                                <f:param id="numero_periodos"   value="#{especialidad.b_numero_periodos}" />
                                <f:param id="tipo_periodos"   value="#{especialidad.b_tipo_periodos}" />

                                <a4j:commandButton actionListener="#{especialidad.Editar}"  image="/Imagenes/actions/filesave.png" value=""  title="Guardar" rendered="#{especialidad.editable2}" reRender="tablaMaster, barra" oncomplete="#{especialidad.oncomplete}"/>
                                <h:commandButton actionListener="#{especialidad.Cancelar}"  value="" image="/Imagenes/actions/fileclose.png" title="Cancelar" rendered="#{especialidad.editable}"/>
                            </p>
                        </h:column>
                        <h:column >
                            <f:facet name="header" >
                                <h:outputText value="Delete" />
                            </f:facet>
                            <p align="right">
                                <h:commandButton  image="/Imagenes/actions/no.png"  title="Eliminar"  actionListener="#{managerEspecialidad.EliminarFila}" action="#{managerEspecialidad.Seleccionar}"  onclick="javascript:return (confirm('¿Esta realmente seguro de Eliminar?'));"/>

                            </p>
                            <f:param id="delete"  value="#{especialidad.b_id}" />
                        </h:column>

                    </rich:dataTable>

                </h:form>


                <rich:modalPanel  id="mp" minHeight="370" minWidth="450" height="370" width="500" zindex="2000">

                    <f:facet name="header">

                        <h:outputText value="Registro de Especialidades" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp')" title="Cerrar"/>
                    </f:facet>

                    <h:form>
                        <table width="100%" style="font-size:10px; font-family:verdana">
                            <tr >
                                <td align="right" colspan="4">
                                    <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"  actionListener="#{managerEspecialidad.Enviar1}" action="#{managerEspecialidad.Seleccionar}" reRender="tablaMaster, barra" oncomplete="#{managerEspecialidad.oncomplete}"/>

                                </td>
                            </tr>
                            <tr>
                                <td colspan="3"><hr size="1">
                                </td>
                            </tr>
                            <tr>
                                <td width="30%" >*Codigo:</td>
                                <td width="70%">
                                    <h:inputText id="codigo"  style=" width : 180px;" value="#{managerEspecialidad.b_codigo_i}"  maxlength="10"/>


                                </td>
                                <td align="right" width="20%">
                                </td>
                            </tr>
                            <tr>
                                <td width="30%">*Nombre:</td>
                                <td width="70%">
                                    <h:inputText id="txtNombre" value="#{managerEspecialidad.b_nombre_i}" style="width : 180px;" />

                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td width="30%">*Fecha de Creacion:</td>
                                <td width="70%">
                                    <a4j:outputPanel id="calendar" layout="block">
                                        <rich:calendar  id="txtCreacion"  value="#{managerEspecialidad.b_fechacreacion}" datePattern="dd/MM/yyyy" />
                                    </a4j:outputPanel>

                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td width="30%">*Resolucion Rectoral:</td>
                                <td width="70%">
                                    <h:inputText id="txtResolucion" value="#{managerEspecialidad.b_resolrectoral}" style="width : 180px;" />

                                </td>
                                <td width="20%">

                                </td>
                            </tr>
                            <tr>
                                <td width="30%">*Tipo de Periodo:</td>
                                <td width="70%">
                                    <h:selectOneMenu value="#{managerEspecialidad.b_tipo_periodos}" >
                                        <f:selectItems value="#{managerEspecialidad.comboTipos}" />
                                    </h:selectOneMenu>
                                </td>
                                <td width="20%">

                                </td>
                            </tr>

                            <tr>
                                <td width="30%">*Número de Periodos:</td>
                                <td width="70%">
                                    <h:inputText  id="txtPeriodos" value="#{managerEspecialidad.b_numero_periodos}" style="width : 180px;" />

                                </td>
                                <td width="20%">

                                </td>
                            </tr>

                            <tr>
                                <td width="30%">*Facultad:</td>
                                <td width="70%">
                                    <h:selectOneMenu value="#{managerEspecialidad.b_idfacultad}">
                                        <f:selectItems value="#{managerEspecialidad.comboFacultades}" />
                                    </h:selectOneMenu>
                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td colspan="4"><hr size="1">
                                </td>
                            </tr>
                        </table>
                    </h:form>
                </rich:modalPanel>
            </rich:panel>

        </f:view>
    </body>
</html>  
