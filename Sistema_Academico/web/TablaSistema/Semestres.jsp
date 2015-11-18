<%@ taglib uri="http://richfaces.org/rich" prefix="rich" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Mantenimiento de Semestres</title>
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
            <rich:panel style="width : 100%; background:#FFF; border:0px !important;" >
                <f:facet name="FACULTADES">
                </f:facet>
                <h:form id="form1" style="width:100%">
                    <table width="100%" style="font-size:10px; font-family:verdana" cellpadding="0" cellspacing="0" border="0">
                        <tr >
                            <td width="20%" colspan="2" ><strong>MANTENIMIENTO DE SEMESTRES</strong>
                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right">

                                <a href="javascript:Richfaces.showModalPanel('mp',{width:450, top:130})">
                                    <h:graphicImage value="/Imagenes/new-32.png" style="text-decoration:none;border-width: 0px;border-color: #FFFFFF;"/>
                                </a>


                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right"><h:graphicImage value="/Imagenes/printer-32.png" title="Imprimir Listado"/>
                            </td>

                            <td align=right width="50%">
                                <h:commandButton type="button" id="buscar" value="" action="#{managerSemestre.Seleccionar}" image="/Imagenes/search-32.png"  title="Buscar"/>
                            </td>

                        </tr>
                        <tr style="width: 100%">
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
                                <h:inputText id="txtCodigo" value="#{managerSemestre.b_codigo}" style="width : 180px;" maxlength="10"/></td>
                            <td width="50%"></td>
                        </tr>
                        <tr>
                            <td width="10%">Nombre:
                            </td>
                            <td width="30%">
                                <h:inputText value="#{managerSemestre.b_nombre}" style="width : 180px;"/></td>
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
                    <table style=" width : 100%;"  cellpadding="0" cellspacing="0" border="0">
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
                                    width="100%" border="0" value="#{managerSemestre.listaFacultad}" var="semestre">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id" />
                            </f:facet>
                            <h:outputText value="#{semestre.b_id}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Codigo" />
                            </f:facet>
                            <h:outputText value="#{semestre.b_codigo}" rendered="#{semestre.view}" />
                            <h:inputText value="#{semestre.b_codigo}" rendered="#{semestre.editable}" maxlength="10" style=" width : 100px;"/>
                        </h:column>

                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Nombre" />
                            </f:facet>

                            <h:outputText value="#{semestre.b_nombre}" rendered="#{semestre.view}"/>
                            <h:inputText value="#{semestre.b_nombre}" rendered="#{semestre.editable}"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Vigente" />
                            </f:facet>

                            <h:outputText value="#{semestre.b_actual_d}" rendered="#{semestre.view}">

                            </h:outputText>

                            <h:selectBooleanCheckbox value="#{semestre.b_actual}" rendered="#{semestre.editable}"/>

                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Actual" />
                            </f:facet>

                            <h:outputText value="#{semestre.b_vigente_d}" rendered="#{semestre.view}">

                            </h:outputText>

                            <h:selectBooleanCheckbox value="#{semestre.b_vigente}" rendered="#{semestre.editable}"/>

                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Inicio Semestre"/>
                            </f:facet>

                            <h:outputText value="#{semestre.b_inicio}" rendered="#{semestre.view}">
                                <f:convertDateTime pattern="dd/MM/yyyy"/>
                            </h:outputText>
                            <a4j:outputPanel id="calendar" layout="block">
                                <rich:calendar value="#{semestre.b_inicio}"  datePattern="dd/MM/yyyy"   rendered="#{semestre.visible}"  inputStyle="width : 80px;"/>
                            </a4j:outputPanel>
                            <a4j:support event="onclick"  reRender="calendar"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Fin Semestre"/>
                            </f:facet>

                            <h:outputText value="#{semestre.b_fin}" rendered="#{semestre.view}">
                                <f:convertDateTime pattern="dd/MM/yyyy"/>
                            </h:outputText>
                            <a4j:outputPanel id="calendar2" layout="block">
                                <rich:calendar value="#{semestre.b_fin}"  datePattern="dd/MM/yyyy"   rendered="#{semestre.visible}" inputStyle="width : 80px;" />
                            </a4j:outputPanel>
                            <a4j:support event="onclick"  reRender="calendar2"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Semanas"/>
                            </f:facet>

                            <h:outputText value="#{semestre.numero_semanas}" rendered="#{semestre.view}">
                            </h:outputText>
                            <h:inputText value="#{semestre.numero_semanas}" rendered="#{semestre.editable}" style=" width : 40px;"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Pensiones"/>
                            </f:facet>

                            <h:outputText value="#{semestre.numero_pensiones}" rendered="#{semestre.view}">
                            </h:outputText>
                            <h:inputText value="#{semestre.numero_pensiones}" rendered="#{semestre.editable}" style=" width : 40px;"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Edit" />
                            </f:facet>
                            <p align="right">
                                <a4j:commandButton actionListener="#{semestre.EditarFila}"  value="" image="/Imagenes/actions/klipper_dock.png" title="Editar" rendered="#{semestre.view}" reRender="tablaMaster, barra"/>

                                <f:param id="id" value="#{semestre.b_id}" />
                                <f:param id="codigo" value="#{semestre.b_codigo}" />
                                <f:param id="nombre"   value="#{semestre.b_nombre}" />
                                <f:param id="resolucion"   value="#{semestre.b_vigente}" />
                                <f:param id="inicio"   value="#{semestre.b_inicio}" />
                                <f:param id="fin"   value="#{semestre.b_fin}" />
                                <f:param id="semanas"   value="#{semestre.numero_semanas}" />
                                <f:param id="pensiones"   value="#{semestre.numero_pensiones}" />
                                <f:param id="actual"   value="#{semestre.b_actual}" />

                                <a4j:commandButton actionListener="#{semestre.Editar}"  image="/Imagenes/actions/filesave.png" value=""  title="Guardar" rendered="#{semestre.editable2}" reRender="tablaMaster, barra" oncomplete="#{semestre.oncomplete}"/>
                                <a4j:commandButton actionListener="#{semestre.Cancelar}"  value="" image="/Imagenes/actions/fileclose.png" title="Cancelar" rendered="#{semestre.editable}" reRender="tablaMaster, barra"/>
                            </p>
                        </h:column>
                        <h:column >
                            <f:facet name="header" >
                                <h:outputText value="Delete" />
                            </f:facet>
                            <p align="right">
                                <h:commandButton  image="/Imagenes/actions/no.png"  title="Eliminar"  actionListener="#{managerSemestre.EliminarFila}" action="#{managerSemestre.Seleccionar}"  onclick="javascript:return (confirm('¿Esta realmente seguro de Eliminar?'));"/>
                            </p>
                            <f:param id="delete"  value="#{semestre.b_id}" />
                        </h:column>

                    </rich:dataTable>

                </h:form>


                <rich:modalPanel  id="mp" minHeight="370" minWidth="450" height="370" width="500" zindex="2000">

                    <f:facet name="header">

                        <h:outputText value="Registro de Semestre" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp')" title="Cerrar"/>
                    </f:facet>

                    <h:form>
                        <table width="100%" style="font-size:10px; font-family:verdana"  cellpadding="0" cellspacing="0" border="0">
                            <tr >
                                <td align="right" colspan="4">
                                    <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"  actionListener="#{managerSemestre.Enviar1}" action="#{managerSemestre.Seleccionar}" reRender="tablaMaster, barra" oncomplete="#{managerSemestre.oncomplete}"/>

                                </td>
                            </tr>
                            <tr>
                                <td colspan="3"><hr size="1">
                                </td>
                            </tr>
                            <tr>
                                <td width="30%" >*Codigo:</td>
                                <td width="70%">
                                    <h:inputText  id="codigo"  style=" width : 180px;" value="#{managerSemestre.b_codigo_i}"  maxlength="10"/>


                                </td>
                                <td align="right" width="20%">
                                </td>
                            </tr>
                            <tr>
                                <td width="30%">*Nombre:</td>
                                <td width="70%">
                                    <h:inputText id="txtNombre" value="#{managerSemestre.b_nombre_i}" style="width : 180px;"  />
                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td width="30%">*Inicio de Semestre:</td>
                                <td width="70%">
                                    <a4j:outputPanel id="calendar" layout="block">
                                        <rich:calendar  id="txtInicio"  value="#{managerSemestre.b_inicio}" datePattern="dd/MM/yyyy"  inputStyle="width : 160px;"/>
                                    </a4j:outputPanel>

                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td width="30%">*Fin de Semestre:</td>
                                <td width="70%">
                                    <a4j:outputPanel id="calendar3" layout="block">
                                        <rich:calendar  id="txtFin"  value="#{managerSemestre.b_fin}" datePattern="dd/MM/yyyy"  inputStyle="width : 160px;" />
                                    </a4j:outputPanel>
                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td width="30%">Actual:</td>
                                <td width="70%" align="left">
                                    <h:selectBooleanCheckbox  id="txtResolucion" value="#{managerSemestre.b_vigente}" />
                                </td>
                                <td width="20%">
                                </td>
                            </tr>
                            <tr>
                                <td width="30%">Vigente:</td>
                                <td width="70%" align="left">
                                    <h:selectBooleanCheckbox  id="txtActual" value="#{managerSemestre.b_actual}" />
                                </td>
                                <td width="20%">

                                </td>
                            </tr>
                            <tr>
                                <td colspan="4"><hr size="1">
                                </td>
                            </tr>

                            <tr>
                                <td width="30%">*Numero de Semanas:</td>
                                <td width="70%">
                                    <h:inputText id="txtSemana"  value="#{managerSemestre.numero_semanas_i}" style="width : 180px;" />

                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td width="30%">*Numero de Pensiones:</td>
                                <td width="70%">
                                    <h:inputText id="txtPensiones" value="#{managerSemestre.b_pensiones_i}" style="width : 180px;"  />

                                </td>
                                <td width="20%"></td>
                            </tr>

                        </table>
                    </h:form>
                </rich:modalPanel>
            </rich:panel>

        </f:view>
    </body>
</html>  
