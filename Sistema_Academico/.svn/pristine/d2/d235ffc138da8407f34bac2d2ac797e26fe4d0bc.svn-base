<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Aignarr Cursos A Plan Curricular</title>
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
                    <table width="100%" style="font-size:10px; font-family:verdana">
                        <tr >
                            <td width="20%" colspan="2" ><strong>AGREGAR CURSOS A PLAN CURRICULAR</strong>
                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right">


                                <h:graphicImage value="/Imagenes/actions/filenew.png" style="border-width: 0px;">
                                    <a4j:support oncomplete="javascript:Richfaces.showModalPanel('mp',{width:450, top:130})" event="onclick" action="#{managerPlanCurricularCurso.Nuevo}" reRender="iId,iCodigo,iDescripcion,iResolucion,iCreacion,iEspecialidad,iFacultad" />
                                </h:graphicImage>



                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right"><h:graphicImage value="/Imagenes/actions/fileprint.png" title="Imprimir Listado"/>
                            </td>

                            <td align=right width="50%">
                                <h:commandButton  type="button" id="buscar" value="" action="#{managerPlanCurricularCurso.Seleccionar}" image="/Imagenes/actions/viewmag.png"  title="Buscar"/>


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
                                <a4j:region id="Pro2">
                                    <h:selectOneMenu id="Facultad" value="#{managerPlanCurricularCurso.b_facultad}">
                                        <f:selectItems value="#{managerPlanCurricularCurso.comboFacultades_s}" />
                                        <a4j:support event="onchange"  action="#{managerPlanCurricularCurso.setearFacultad_s}" reRender="Especialidad"/>
                                    </h:selectOneMenu>

                                </a4j:region>
                            </td>

                        </tr>

                        <tr>
                            <td width="10%">Especialidad:</td>
                            <td width="70%">
                                <h:selectOneMenu  id="Especialidad"  value="#{managerPlanCurricularCurso.b_especialidad}">
                                    <f:selectItems value="#{managerPlanCurricularCurso.comboEspecialidades_s}" />
                                </h:selectOneMenu>
                            </td>
                            <td width="20%"></td>
                        </tr>
                        <tr style=" height : 24px;">
                            <td width="10%" >Codigo:
                            </td>
                            <td width="30%">
                                <h:inputText id="txtCodigo" value="#{managerPlanCurricularCurso.b_codigo}" style="width : 180px;" maxlength="10"/></td>
                            <td width="50%">

                            </td>
                        </tr>
                        <tr>
                            <td width="10%">Nombre:
                            </td>
                            <td width="30%">
                                <h:inputText value="#{managerPlanCurricularCurso.b_descripcion}" style="width : 180px;"/></td>
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
                                    width="100%" border="0" value="#{managerPlanCurricularCurso.listaPlanCurricular}" var="planCurricular">




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


                            <h:outputText value="#{planCurricular.facultad_descripcion_y}" >
                            </h:outputText>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Especialidad" />
                            </f:facet>


                            <h:outputText value="#{planCurricular.especialidad_descripcion_y}" >
                            </h:outputText>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Edit" />
                            </f:facet>
                            <p align="right">

                                <h:graphicImage value="/Imagenes/actions/editpaste.png" style="border-width: 0px;">
                                    <a4j:support oncomplete="javascript:Richfaces.showModalPanel('mp',{width:450, top:130})" event="onclick" actionListener="#{planCurricular.EditarFila}" action="#{managerPlanCurricularCurso.setearFacultad}"  reRender="iId,iCodigo,iCreacion,iDescripcion,iResolucion,iFacultad,iEspecialidad"/>
                                </h:graphicImage>


                                <f:param id="p_id" value="#{planCurricular.b_id}" />
                                <f:param id="p_codigo" value="#{planCurricular.b_codigo}" />
                                <f:param id="p_descripcion"   value="#{planCurricular.b_descripcion}" />
                                <f:param id="p_facultad"   value="#{planCurricular.b_facultad}" />
                                <f:param id="p_facultad_d"   value="#{planCurricular.facultad_descripcion_y}" />
                                <f:param id="p_especialidad"   value="#{planCurricular.b_especialidad}" />
                                <f:param id="p_especialidad_d"   value="#{planCurricular.especialidad_descripcion_y}" />
                                <f:param id="p_resolucion"   value="#{planCurricular.b_resolucion}" />
                                <f:param id="p_creacion"   value="#{planCurricular.b_creacion}" />
                            </p>
                        </h:column>

                        <h:column >
                            <f:facet name="header" >
                                <h:outputText value="Cursos" />
                            </f:facet>
                            <p align="right">
                                <h:graphicImage title="Asignar Cursos" value="/Imagenes/actions/pencil.png" style="border-width: 0px;">
                                    <a4j:support oncomplete="javascript:Richfaces.showModalPanel('mp1',{width:800, top:90})" event="onclick" actionListener="#{planCurricular.EditarFila}"  action="#{planCurricular.Limpiar}" reRender="oId,oCodigo,oCreacion,tablaGrupos,nombre1,oDescripcion,oResolucion,nombre4,oFacultad,oEspecialidad,scroll1"/>

                                </h:graphicImage>

                            </p>
                            <f:param id="cursos"  value="#{planCurricular.b_id}" />
                        </h:column>

                        <h:column >
                            <f:facet name="header" >
                                <h:outputText value="Delete" />
                            </f:facet>
                            <p align="right">
                                <h:commandButton  image="/Imagenes/actions/no.png"  title="Eliminar"  actionListener="#{managerPlanCurricularCurso.EliminarFila}" action="#{managerPlanCurricularCurso.Seleccionar}"  onclick="javascript:return (confirm('¿Esta realmente seguro de Eliminar?'));"/>
                            </p>
                            <f:param id="delete"  value="#{planCurricular.b_id}" />
                        </h:column>

                    </rich:dataTable>

                </h:form>


                <rich:modalPanel  id="mp" minHeight="400" minWidth="450" height="400" width="500" zindex="2000">

                    <f:facet name="header">

                        <h:outputText value="Registro de Plan Curricular" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp')" title="Cerrar"/>
                    </f:facet>

                    <h:form>
                        <table width="100%" style="font-size:10px; font-family:verdana">
                            <tr >
                                <td align="right" colspan="4">
                                    <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"  actionListener="#{managerPlanCurricularCurso.Enviar1}" action="#{managerPlanCurricularCurso.Seleccionar}" reRender="tablaMaster" oncomplete="#{managerPlanCurricularCurso.oncomplete}"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3"><hr size="1">
                                </td>
                            </tr>
                            <tr>
                                <td width="30%" >Facultad:</td>

                                <td width="70%">
                                    <h:inputHidden id="iId" value="#{managerPlanCurricularCurso.b_id_u}"/>

                                    <a4j:region id="ProvProc">
                                        <h:selectOneMenu id="iFacultad" value="#{managerPlanCurricularCurso.b_facultad_u}">
                                            <f:selectItems value="#{managerPlanCurricularCurso.comboFacultades}" />
                                            <a4j:support event="onchange"  action="#{managerPlanCurricularCurso.setearFacultad}" reRender="iEspecialidad"/>
                                        </h:selectOneMenu>

                                    </a4j:region>


                                </td>

                            </tr>
                            <tr>
                                <td width="30%">Especialidad:</td>
                                <td width="70%">
                                    <h:selectOneMenu  id="iEspecialidad"  value="#{managerPlanCurricularCurso.b_especialidad_u}">
                                        <f:selectItems value="#{managerPlanCurricularCurso.comboEspecialidades}" />
                                    </h:selectOneMenu>
                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td width="30%">Codigo de Plan:</td>
                                <td width="70%">
                                    <h:inputText label="Codigo" id="iCodigo" value="#{managerPlanCurricularCurso.b_codigo_i}" style="width : 180px;" required="true" rendered="true"/>
                                    <rich:message for="iCodigo">
                                        <f:facet name="passedMarker">
                                            <h:graphicImage  value="/Imagenes/actions/ok.png" />
                                        </f:facet>
                                        <f:facet name="errorMarker">
                                            <h:graphicImage value="/Imagenes/actions/no.png" />
                                        </f:facet>
                                    </rich:message>
                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td width="30%">Descripcion:</td>
                                <td width="70%">
                                    <h:inputTextarea label="Resolucion" id="iDescripcion" value="#{managerPlanCurricularCurso.b_descripcion_i}" style="width : 180px;" required="true" rendered="true"/>
                                    <rich:message for="iDescripcion">
                                        <f:facet name="passedMarker">
                                            <h:graphicImage  value="/Imagenes/actions/ok.png" />
                                        </f:facet>
                                        <f:facet name="errorMarker">
                                            <h:graphicImage value="/Imagenes/actions/no.png" />
                                        </f:facet>
                                    </rich:message>
                                </td>
                                <td width="20%">

                                </td>
                            </tr>
                            <tr>
                                <td width="30%">Fecha de Creación:</td>
                                <td width="70%">
                                    <a4j:outputPanel id="calendar" layout="block">
                                        <rich:calendar  id="iCreacion"  value="#{managerPlanCurricularCurso.b_creacion_u}" datePattern="dd/MM/yyyy"  requiredMessage="true"/>
                                    </a4j:outputPanel>
                                    <rich:message for="iCreacion">
                                        <f:facet name="passedMarker">
                                            <h:graphicImage  value="/Imagenes/actions/ok.png" />
                                        </f:facet>
                                        <f:facet name="errorMarker">
                                            <h:graphicImage value="/Imagenes/actions/no.png" />
                                        </f:facet>
                                    </rich:message>
                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td width="30%">Resolución Rectoral:</td>
                                <td width="70%">
                                    <h:inputText label="Resolucion" id="iResolucion" value="#{managerPlanCurricularCurso.b_resolucion_u}" style="width : 180px;" required="true" rendered="true"/>
                                    <rich:message for="iResolucion">
                                        <f:facet name="passedMarker">
                                            <h:graphicImage  value="/Imagenes/actions/ok.png" />
                                        </f:facet>
                                        <f:facet name="errorMarker">
                                            <h:graphicImage value="/Imagenes/actions/no.png" />
                                        </f:facet>
                                    </rich:message>
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

                <rich:modalPanel  id="mp1" minHeight="600" minWidth="800" height="600" width="800" zindex="2000">

                    <f:facet name="header">

                        <h:outputText value="ASIGNACION DE CURSOS" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp1')" title="Cerrar"/>
                    </f:facet>
                    <h:form>
                        <table width="100%" style="font-size:10px; font-family:verdana">
                            <tr >
                                <td align="right" colspan="4">
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3"><hr size="1">
                                </td>
                            </tr>
                            <tr>
                                <td width="30%" >Facultad:</td>
                                <td width="70%">
                                    <h:inputHidden id="oId" value="#{managerPlanCurricularCurso.b_id_u}"/>
                                    <h:outputText id="oFacultad" value="#{managerPlanCurricularCurso.facultad_descripcion}"/>
                                </td>
                            </tr>
                            <tr>
                                <td width="30%">Especialidad:</td>
                                <td width="70%">
                                    <h:outputText id="oEspecialidad" value="#{managerPlanCurricularCurso.especialidad_descripcion}"/>
                                </td>

                            </tr>
                            <tr>
                                <td width="30%">Codigo de Plan:</td>
                                <td width="70%">
                                    <h:outputText id="oCodigo" value="#{managerPlanCurricularCurso.b_codigo_i}" style="width : 180px;" rendered="true"/>

                                </td>

                            </tr>
                            <tr>
                                <td width="30%">Descripcion:</td>
                                <td width="70%">
                                    <h:outputText id="oDescripcion" value="#{managerPlanCurricularCurso.b_descripcion_i}" style="width : 180px;" rendered="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td width="30%">Fecha de Creación:</td>
                                <td width="70%">
                                    <h:outputText id="oCreacion" value="#{managerPlanCurricularCurso.b_creacion_descripcion}"  rendered="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td width="30%">Resolución Rectoral:</td>
                                <td width="70%">
                                    <h:outputText id="oResolucion" value="#{managerPlanCurricularCurso.b_resolucion_u}" style="width : 180px;" rendered="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4"><hr size="1">
                                </td>
                            </tr>
                            <tr>
                                <td colspan="5">
                                    <table width="100%" style="font-size:10px; font-family:verdana">
                                        <tr>
                                            <td width="30%">Periodo:
                                            </td>
                                            <td width="30%">
                                                <h:selectOneMenu id="nombre4" value="#{managerPlanCurricularCurso.ciclo}" >
                                                    <f:selectItems value="#{managerPlanCurricularCurso.combo_semestre}"/>
                                                </h:selectOneMenu>
                                            </td>
                                            <td width="30%">Codigo:
                                            </td>
                                            <td width="30%">
                                                <h:inputText id="codigo1" style="width : 80px;" value="#{managerPlanCurricularCurso.codigo1}" maxlength="10"/>
                                            </td>
                                            <td width="30%">Curso:
                                            </td>
                                            <td width="30%">
                                                <h:selectOneMenu id="nombre1" value="#{managerPlanCurricularCurso.nombre1}" >
                                                    <f:selectItems value="#{managerPlanCurricularCurso.combo_cursos}" />
                                                </h:selectOneMenu>
                                            </td>
                                            <td width="30%">Creditos:
                                            </td>
                                            <td width="30%">
                                                <h:inputText id="creditos1" style="width : 30px;" value="#{managerPlanCurricularCurso.creditos1}" maxlength="2"/>
                                            </td>
                                            <td width="30%">Tipo:
                                            </td>
                                            <td width="30%">
                                                <h:selectOneMenu id="tipo1" value="#{managerPlanCurricularCurso.tipo1}" >
                                                    <f:selectItems value="#{managerPlanCurricularCurso.combo_tipos}" />
                                                </h:selectOneMenu>
                                            </td>
                                            <td width="30%"></td>
                                            <td width="10%"><a4j:commandButton   image="/Imagenes/actions/edit_add.png"  title="Agregar"  action="#{managerPlanCurricularCurso.AgregarFila}" reRender="tablaGrupos,nombre1,scroll1" oncomplete="#{managerPlanCurricularCurso.oncomplete}"/>

                                            </td>
                                            <td>

                                            </td>
                                        </tr>

                                    </table>
                                </td>
                            </tr>
                        </table>
                    </h:form>
                    <h:form id="form2">
                        <table style="font-size:10px; font-family:verdana">
                            <tr>
                                <td colspan="8" width="100%">
                                    <rich:datascroller id="scroll1" align="right"  for="tablaGrupos" maxPages="10"  style=" width : 100%;"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="8" width="100%">
                                    <rich:dataTable id="tablaGrupos" rows="10" onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                    cellpadding="0" cellspacing="0"
                                                    width="100%" border="0" value="#{managerPlanCurricularCurso.listaCursos}" var="cursos">

                                        <h:column>
                                            <f:facet name="header">
                                                <h:outputText value="Id" />
                                            </f:facet>
                                            <h:outputText value="#{cursos.b_id}"/>
                                        </h:column>

                                        <h:column>
                                            <f:facet name="header">
                                                <h:outputText value="Ciclo" />
                                            </f:facet>
                                            <h:outputText value="#{cursos.ciclo}"/>
                                        </h:column>

                                        <h:column>
                                            <f:facet name="header">
                                                <h:outputText value="Codigo" />
                                            </f:facet>
                                            <h:outputText value="#{cursos.b_codigo1}"/>
                                        </h:column>

                                        <h:column>
                                            <f:facet name="header">
                                                <h:outputText value="Nombre" />
                                            </f:facet>
                                            <h:outputText value="#{cursos.b_codigo}"/>
                                        </h:column>

                                        <h:column>
                                            <f:facet name="header">
                                                <h:outputText value="Tipo" />
                                            </f:facet>
                                            <h:outputText value="#{cursos.b_creditos}"/>
                                        </h:column>
                                        <h:column>
                                            <f:facet name="header">
                                                <h:outputText value="Creditos" />
                                            </f:facet>
                                            <h:outputText value="#{cursos.b_tipos}"/>
                                        </h:column>

                                        <h:column>
                                            <f:facet name="header">
                                                <h:outputText value="Quitar" />
                                            </f:facet>
                                            <f:param id="p_id1" value="#{cursos.b_id}" />
                                            <a4j:commandButton  image="/Imagenes/actions/edit_remove.png"  title="Eliminar"  actionListener="#{managerPlanCurricularCurso.QuitarFila}" reRender="tablaGrupos"/>
                                        </h:column>
                                    </rich:dataTable>




                                </td>
                                <td>
                                </td>
                                <td>
                                </td>
                            </tr>
                        </table>
                    </h:form>
                </rich:modalPanel>

            </rich:panel>

        </f:view>
    </body>
</html>  

