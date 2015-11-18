<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Mantenimiento de Facultades</title>
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
                            <td width="20%" colspan="2" ><strong>MANTENIMIENTO DE FACULTADES</strong>
                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right">

                                <a href="javascript:Richfaces.showModalPanel('mp',{width:450, top:130})">
                                    <h:graphicImage value="/Imagenes/actions/filenew.png" style="text-decoration:none;border-width: 0px;border-color: #FFFFFF;"/>
                                </a>


                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right"><h:graphicImage value="/Imagenes/actions/fileprint.png" title="Imprimir Listado"/>
                            </td>

                            <td align=right width="50%">
                                <h:commandButton type="button" id="buscar" value="" action="#{managerFacultad.Seleccionar}" image="/Imagenes/actions/viewmag.png"  title="Buscar"/>


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
                        </tr>
                        <tr style=" height : 24px;">
                            <td width="10%" >Codigo:
                            </td>
                            <td width="30%">
                                <h:inputText id="txtCodigo" value="#{managerFacultad.b_codigo}" style="width : 180px;" maxlength="10"/></td>
                            <td width="50%"></td>
                        </tr>
                        <tr>
                            <td width="10%">Nombre:
                            </td>
                            <td width="30%">
                                <h:inputText value="#{managerFacultad.b_nombre}" style="width : 180px;"/></td>
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
                                    width="100%" border="0" value="#{managerFacultad.listaFacultad}" var="facultad">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id" />
                            </f:facet>
                            <h:outputText value="#{facultad.b_id}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Codigo" />
                            </f:facet>
                            <h:outputText value="#{facultad.b_codigo}" rendered="#{facultad.view}" />
                            <h:inputText value="#{facultad.b_codigo}" rendered="#{facultad.editable}" maxlength="10"/>
                        </h:column>

                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Nombre" />
                            </f:facet>

                            <h:outputText value="#{facultad.b_nombre}" rendered="#{facultad.view}"/>
                            <h:inputText value="#{facultad.b_nombre}" rendered="#{facultad.editable}"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Resolucion" />
                            </f:facet>

                            <h:outputText value="#{facultad.b_resolucion}" rendered="#{facultad.view}">

                            </h:outputText>
                            <h:inputText value="#{facultad.b_resolucion}" rendered="#{facultad.editable}"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Fecha Creacion"/>
                            </f:facet>

                            <h:outputText value="#{facultad.b_creacion}" rendered="#{facultad.view}">
                                <f:convertDateTime pattern="dd/MM/yyyy"/>
                            </h:outputText>
                            <a4j:outputPanel id="calendar" layout="block">
                                <rich:calendar value="#{facultad.b_creacion}"  datePattern="dd/MM/yyyy"   rendered="#{facultad.visible}" style=" width : 212px;"/>
                            </a4j:outputPanel>
                            <a4j:support event="onclick"  reRender="calendar"/>
                        </h:column>


                        <h:column>
                            <f:facet name="header"><h:outputText value="Edit" /></f:facet>
                            <p align="right">
                                <h:commandButton actionListener="#{facultad.EditarFila}"  value="" image="/Imagenes/actions/klipper_dock.png" title="Editar" rendered="#{facultad.view}"/>
                                <f:param id="id" value="#{facultad.b_id}" />
                                <f:param id="codigo" value="#{facultad.b_codigo}" />
                                <f:param id="nombre"   value="#{facultad.b_nombre}" />
                                <f:param id="resolucion"   value="#{facultad.b_resolucion}" />
                                <f:param id="creacion"   value="#{facultad.b_creacion}" />
                                <a4j:commandButton actionListener="#{facultad.Editar}"  image="/Imagenes/actions/filesave.png" value=""  title="Guardar" rendered="#{facultad.editable2}" reRender="tablaMaster, barra"/>
                                <h:commandButton actionListener="#{facultad.Cancelar}"  value="" image="/Imagenes/actions/fileclose.png" title="Cancelar" rendered="#{facultad.editable}"/>
                            </p>
                        </h:column>

                        <h:column >
                            <f:facet name="header" >
                                <h:outputText value="Delete" />
                            </f:facet>
                            <p align="right">
                                <h:commandButton  image="/Imagenes/actions/no.png"  title="Eliminar"  actionListener="#{managerFacultad.EliminarFila}" action="#{managerFacultad.Seleccionar}"  onclick="javascript:return (confirm('¿Esta realmente seguro de Eliminar?'));"/>
                            </p>
                            <f:param id="delete"  value="#{facultad.b_id}" />
                        </h:column>

                    </rich:dataTable>

                </h:form>


                <rich:modalPanel  id="mp" minHeight="370" minWidth="450" height="370" width="500" zindex="2000">

                    <f:facet name="header">

                        <h:outputText value="Registro de Facultad" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp')" title="Cerrar"/>
                    </f:facet>

                    <h:form>
                        <table width="100%" style="font-size:10px; font-family:verdana">
                            <tr >
                                <td align="right" colspan="4">
                                    <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"  actionListener="#{managerFacultad.Enviar1}" action="#{managerFacultad.Seleccionar}" reRender="tablaMaster, barra" oncomplete="#{managerFacultad.oncomplete}" style=" width : 16px;"/>

                                </td>
                            </tr>
                            <tr>
                                <td colspan="3"><hr size="1">
                                </td>
                            </tr>
                            <tr>
                                <td width="30%" >*Codigo:</td>
                                <td width="70%">
                                    <h:inputText id="codigo"  style=" width : 180px;" value="#{managerFacultad.b_codigo_i}" maxlength="10"/>


                                </td>
                                <td align="right" width="20%">
                                </td>
                            </tr>
                            <tr>
                                <td width="30%">*Nombre:</td>
                                <td width="70%">
                                    <h:inputText id="txtNombre"  value="#{managerFacultad.b_nombre_i}" style="width : 180px;"  />

                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td width="30%">*Fecha de Creacion:</td>
                                <td width="70%">
                                    <a4j:outputPanel id="calendar" layout="block">
                                        <rich:calendar  id="txtCreacion"  value="#{managerFacultad.b_creacion}" datePattern="dd/MM/yyyy"  />
                                    </a4j:outputPanel>

                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td width="30%">*Resolucion Rectoral:</td>
                                <td width="70%">
                                    <h:inputText  id="txtResolucion" value="#{managerFacultad.b_resolucion}" style="width : 180px;"/>

                                </td>
                                <td width="20%">

                                </td>
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
