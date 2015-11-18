<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Mantenimiento de Plan Curricular</title>
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
                            <td width="20%" colspan="2" ><strong>PLAN CURRICULAR</strong>
                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right">


                                <h:graphicImage value="/Imagenes/actions/filenew.png" style="border-width: 0px;">
                                    <a4j:support onsubmit="hideDiv({duration:0.1});" oncomplete="javascript:Richfaces.showModalPanel('mp',{width:450, top:130})" event="onclick" action="#{managerPlanCurricular.Nuevo}" reRender="iId,iCodigo,iDescripcion,iResolucion,iCreacion,iEspecialidad,iFacultad" />
                                </h:graphicImage>



                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right"><h:graphicImage value="/Imagenes/actions/fileprint.png" title="Imprimir Listado"/>
                            </td>

                            <td align=right width="50%">
                                <h:commandButton type="button" id="buscar" value="" action="#{managerPlanCurricular.Seleccionar}" image="/Imagenes/actions/viewmag.png"  title="Buscar"/>


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
                        <tr>
                            <td width="10%">Facultad:</td>
                            <td width="70%">

                                <h:selectOneMenu id="Facultad" value="#{managerPlanCurricular.b_facultad}"  style=" width : 180px;">
                                    <f:selectItems value="#{managerPlanCurricular.comboFacultades_s}" />
                                    <a4j:support event="onchange"  action="#{managerPlanCurricular.setearFacultad_s}" reRender="Especialidad"/>
                                </h:selectOneMenu>


                            </td>

                        </tr>

                        <tr>
                            <td width="10%">Especialidad:</td>
                            <td width="70%">
                                <h:selectOneMenu  id="Especialidad"  value="#{managerPlanCurricular.b_especialidad}"  style=" width : 180px;">
                                    <f:selectItems value="#{managerPlanCurricular.comboEspecialidades_s}" />
                                </h:selectOneMenu>
                            </td>
                            <td width="20%"></td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td width="10%" >Codigo:
                            </td>
                            <td width="30%">
                                <h:inputText id="txtCodigo" value="#{managerPlanCurricular.b_codigo}" style="width : 180px;" maxlength="10"/></td>
                            <td width="50%">

                            </td>
                        </tr>
                        <tr>
                            <td width="10%">Nombre:
                            </td>
                            <td width="30%">
                                <h:inputText value="#{managerPlanCurricular.b_descripcion}" style="width : 180px;"/></td>
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
                                <rich:datascroller align="right"  for="tablaMaster" maxPages="8"  style=" width : 100%;"/>
                            </td>
                        </tr>
                    </table>

                    <rich:dataTable id="tablaMaster" rows="10"
                                    onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                    cellpadding="0" cellspacing="0"
                                    width="100%" border="0" value="#{managerPlanCurricular.listaPlanCurricular}" var="planCurricular">




                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id" />
                            </f:facet>
                            <h:outputText value="#{planCurricular.b_id}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Codigo" />
                            </f:facet>
                            <h:outputText value="#{planCurricular.b_codigo}"  />
                        </h:column>

                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Descripcion" />
                            </f:facet>

                            <h:outputText value="#{planCurricular.b_descripcion}" />
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Resolucion" />
                            </f:facet>

                            <h:outputText value="#{planCurricular.b_resolucion}" >

                            </h:outputText>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Fecha Creacion"/>
                            </f:facet>

                            <h:outputText value="#{planCurricular.b_creacion}" >
                                <f:convertDateTime pattern="dd/MM/yyyy"/>
                            </h:outputText>


                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Facultad" />
                            </f:facet>

                            <h:outputText value="#{planCurricular.facultad_descripcion}" >

                            </h:outputText>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Especialidad" />
                            </f:facet>

                            <h:outputText value="#{planCurricular.especialidad_descripcion}" >

                            </h:outputText>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Edit" />
                            </f:facet>
                            <p align="right">

                                <h:graphicImage value="/Imagenes/actions/editpaste.png" style="border-width: 0px;">
                                    <a4j:support onsubmit="hideDiv({duration:0.1});" oncomplete="javascript:Richfaces.showModalPanel('mp',{width:450, top:130})" event="onclick" actionListener="#{planCurricular.EditarFila}" action="#{managerPlanCurricular.setearFacultad}"  reRender="iId,iCodigo,iCreacion,iDescripcion,iResolucion,iFacultad,iEspecialidad"/>
                                </h:graphicImage>


                                <f:param id="p_id" value="#{planCurricular.b_id}" />
                                <f:param id="p_codigo" value="#{planCurricular.b_codigo}" />
                                <f:param id="p_descripcion"   value="#{planCurricular.b_descripcion}" />
                                <f:param id="p_facultad"   value="#{planCurricular.b_facultad}" />
                                <f:param id="p_especialidad"   value="#{planCurricular.b_especialidad}" />
                                <f:param id="p_resolucion"   value="#{planCurricular.b_resolucion}" />
                                <f:param id="p_creacion"   value="#{planCurricular.b_creacion}" />
                            </p>
                        </h:column>
                        <h:column >
                            <f:facet name="header" >
                                <h:outputText value="Delete" />
                            </f:facet>
                            <p align="right">
                                <h:commandButton  image="/Imagenes/actions/no.png"  title="Eliminar"  actionListener="#{managerPlanCurricular.EliminarFila}" action="#{managerPlanCurricular.Seleccionar}"  onclick="javascript:return (confirm('¿Esta realmente seguro de Eliminar?'));"/>
                            </p>
                            <f:param id="delete"  value="#{planCurricular.b_id}" />
                        </h:column>

                    </rich:dataTable>

                </h:form>


                <rich:modalPanel  id="mp" minHeight="400" minWidth="450" height="400" width="500" zindex="2000" onshow="showDiv();">
                    <rich:effect  name="hideDiv"  for="contentDiv" type="BlindUp" />
                    <rich:effect  name="showDiv"  for="contentDiv" type="BlindDown" />
                    <div id="contentDiv">
                        <f:facet name="header">

                            <h:outputText value="Registro de Plan Curricular" />
                        </f:facet>
                        <f:facet name="controls">
                            <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp')" title="Cerrar"/>
                        </f:facet>

                        <h:form>
                            <table width="100%" style="font-size:10px; font-family:verdana"  border="0" cellspacing="0" cellpadding="0">
                                <tr >
                                    <td align="right" colspan="4">
                                        <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"  actionListener="#{managerPlanCurricular.Enviar1}" action="#{managerPlanCurricular.Seleccionar}" reRender="tablaMaster"  oncomplete="#{managerPlanCurricular.oncomplete}"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3"><hr size="1">
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%" >Facultad:</td>
                                    <td width="70%">
                                        <h:inputHidden id="iId" value="#{managerPlanCurricular.b_id_u}"/>


                                        <h:selectOneMenu id="iFacultad" value="#{managerPlanCurricular.b_facultad_u}"  style=" width : 180px;">
                                            <f:selectItems value="#{managerPlanCurricular.comboFacultades}" />
                                            <a4j:support event="onchange"  action="#{managerPlanCurricular.setearFacultad}" reRender="iEspecialidad"/>
                                        </h:selectOneMenu>




                                    </td>

                                </tr>
                                <tr>
                                    <td width="30%">Especialidad:</td>
                                    <td width="70%">
                                        <h:selectOneMenu  id="iEspecialidad"  value="#{managerPlanCurricular.b_especialidad_u}"  style=" width : 180px;">
                                            <f:selectItems value="#{managerPlanCurricular.comboEspecialidades}" />
                                        </h:selectOneMenu>
                                    </td>
                                    <td width="20%"></td>
                                </tr>
                                <tr>
                                    <td width="30%">Codigo de Plan:</td>
                                    <td width="70%">
                                        <h:inputText label="Codigo" id="iCodigo" value="#{managerPlanCurricular.b_codigo_i}" style="width : 180px;" rendered="true"/>
                                    </td>
                                    <td width="20%"></td>
                                </tr>
                                <tr>
                                    <td width="30%">Descripcion:</td>
                                    <td width="70%">
                                        <h:inputTextarea label="Resolucion" id="iDescripcion" value="#{managerPlanCurricular.b_descripcion_i}" style="width : 180px;" rendered="true"/>
                                    </td>
                                    <td width="20%">

                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%">Fecha de Creación:</td>
                                    <td width="70%">
                                        <a4j:outputPanel id="calendar" layout="block">
                                            <rich:calendar  id="iCreacion"  value="#{managerPlanCurricular.b_creacion_u}" datePattern="dd/MM/yyyy"  />
                                        </a4j:outputPanel>
                                    </td>
                                    <td width="20%"></td>
                                </tr>
                                <tr>
                                    <td width="30%">Resolución Rectoral:</td>
                                    <td width="70%">
                                        <h:inputText label="Resolucion" id="iResolucion" value="#{managerPlanCurricular.b_resolucion_u}" style="width : 180px;" rendered="true"/>
                                    </td>
                                    <td width="20%"></td>
                                </tr>
                                <tr>
                                    <td colspan="4"><hr size="1">
                                    </td>
                                </tr>
                            </table>
                        </h:form>
                    </div>
                </rich:modalPanel>
            </rich:panel>

        </f:view>
    </body>
</html>  

