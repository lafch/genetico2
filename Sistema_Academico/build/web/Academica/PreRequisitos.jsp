<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Mantenimiento de Pre-REquisitos</title>
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
                <h:form id="form1" style="width:100%">
                    <table width="100%" style="font-size:10px; font-family:verdana" cellspacing="0" border="0" cellpadding="0">
                        <tr>
                            <td width="20%" colspan="2" ><strong>MANTENIMIENTO PRE REQUISITOS</strong></td>
                            <td width="30%"></td>
                            <td width="30%" align="right">

                            </td>
                            <td width="30%" align="right">
                            </td>
                            <td width="30%" align="right"><h:graphicImage value="/Imagenes/actions/fileprint.png" title="Imprimir Listado"/></td>
                            <td align=right width="50%">
                                <h:commandButton type="button" id="buscar" value="" action="#{managerPreRequisito.Seleccionar}" image="/Imagenes/actions/viewmag.png" title="Buscar"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="7"><hr width="100%" size="1"></td>
                        </tr>
                        <tr>
                            <td width="20%" colspan="6"></td>
                        </tr>
                        <tr>
                        </tr>
                        <tr>
                            <td width="10%">Facultad:</td>
                            <td >
                                <h:selectOneMenu id="FacuBuscar" value="#{managerPreRequisito.c_id_facu}"  style="width : 180px;">
                                    <f:selectItems value="#{managerPreRequisito.comboFacultad_buscar}" />
                                    <a4j:support event="onchange"  action="#{managerPreRequisito.setearFacultad_buscar}" reRender="EspeBuscar,PlanBuscar"/>
                                </h:selectOneMenu>
                            </td>
                            <td colspan="4"></td>
                        </tr>
                        <tr>
                            <td>
								Especialidad:
                            </td>
                            <td>
                                <h:selectOneMenu id="EspeBuscar" value="#{managerPreRequisito.c_id_espe}"  style="width : 180px;">
                                    <f:selectItems value="#{managerPreRequisito.comboEspecialidad_buscar}" />
                                    <a4j:support event="onchange"  action="#{managerPreRequisito.setearEspecialidad_buscar}" reRender="PlanBuscar"/>
                                </h:selectOneMenu>
                            </td>
                            <td width="20%" colspan="4"></td>
                        </tr>
                        <tr>
                            <td>
								Plan Curricular:
                            </td>
                            <td>
                                <h:selectOneMenu id="PlanBuscar" value="#{managerPreRequisito.c_id_plan}"  style="width : 180px;">
                                    <f:selectItems value="#{managerPreRequisito.comboPlan_buscar}" />
                                </h:selectOneMenu>
                            </td>
                            <td width="20%" colspan="4"></td>
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
                                <rich:datascroller id="paginacion" align="right"  for="tablaMaster" maxPages="8"  style=" width : 100%;"/>
                            </td>
                        </tr>
                    </table>

                    <rich:dataTable id="tablaMaster" rows="10"
                                    onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                    cellpadding="0" cellspacing="0"
                                    width="100%" border="0" value="#{managerPreRequisito.listaPreRequisitos}" var="Pre">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id" />
                            </f:facet>
                            <h:outputText value="#{Pre.c_id}"/>
                            <f:param value="#{Pre.flag_ver}" id="flag"/>
                            <h:inputHidden value="#{Pre.flag_ver}" />
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Ciclo" />
                            </f:facet>
                            <h:outputText value="#{Pre.c_ciclo}" rendered="#{Pre.view}"/>
                        </h:column>

                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Codigo de Curso" />
                            </f:facet>
                            <h:outputText value="#{Pre.c_codigo}" rendered="#{Pre.view}"/>
                        </h:column>

                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Nombre" />
                            </f:facet>

                            <h:outputText value="#{Pre.c_nombre}" rendered="#{Pre.view}"/>
                        </h:column>

                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Tipo de Curso" />
                            </f:facet>

                            <h:outputText value="#{Pre.c_tipo}" rendered="#{Pre.view}"/>
                        </h:column>

                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="# de Creditos" />
                            </f:facet>

                            <h:outputText value="#{Pre.c_creditos}" rendered="#{Pre.view}"/>
                        </h:column>

                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Facultad" />
                            </f:facet>

                            <h:outputText value="#{Pre.c_facu}" rendered="#{Pre.view}"/>
                        </h:column>


                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Especialidad" />
                            </f:facet>

                            <h:outputText value="#{Pre.c_espe}" rendered="#{Pre.view}"/>
                        </h:column>

                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Plan Curricular" />
                            </f:facet>

                            <h:outputText value="#{Pre.c_plan}" rendered="#{Pre.view}"/>
                        </h:column>


                        <h:column >
                            <f:facet name="header" >
                                <h:outputText value="Ver" />
                            </f:facet>
                            <p align="right">
                                <h:graphicImage value="#{Pre.imagen}" style="border-width: 0px;">
                                    <a4j:support event="onclick" actionListener="#{Pre.Ver}" reRender="tablaMaster"/>
                                </h:graphicImage>

                            </p>
                        </h:column>


                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="" />
                            </f:facet>
                            <p align="right">

                                <!-- aqui se ingresa el edit -->

                                <h:graphicImage value="/Imagenes/actions/pencil.png" style="border-width: 0px;" height="16" title="Asignar Requisitos">
                                    <a4j:support event="onclick" onsubmit="hideDiv({duration:0.1});"  oncomplete="javascript:Richfaces.showModalPanel('mp',{width:100, top:130})" actionListener="#{managerPreRequisito.EditarFila}"  reRender="iValor,iId,iCodigo,iNombre,iCiclo,iIdCiclo,iFacultad,iIdEspecialidad,iEspecialidad,iPlan,iIdPlan,bCiclo,bCurso,tablaGrupos"/>
                                </h:graphicImage>


                                <f:param id="p_valor" value="#{Pre.c_valor}" />
                                <f:param id="p_id" value="#{Pre.c_id}" />
                                <f:param id="p_codigo"   value="#{Pre.c_codigo}" />
                                <f:param id="p_nombre"   value="#{Pre.c_nombre}" />
                                <f:param id="p_ciclo"   value="#{Pre.c_ciclo}" />
                                <f:param id="p_id_ciclo"   value="#{Pre.c_id_ciclo}" />
                                <f:param id="p_especialidad"   value="#{Pre.c_espe}" />
                                <f:param id="p_id_especialidad"   value="#{Pre.c_id_espe}" />
                                <f:param id="p_facultad"   value="#{Pre.c_facu}" />
                                <f:param id="p_plan"   value="#{Pre.c_plan}" />
                                <f:param id="p_id_plan"   value="#{Pre.c_id_plan}" />
                                <!-- fin de  ingresa el edit -->
                            </p>


                            <rich:subTable value="#{Pre.detalle_s}" var="det" rendered="#{Pre.ver}" id="subtable"
                                           onRowMouseOver="this.style.backgroundColor='#F8F8F8'"
                                           onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'">

                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="" />
                                    </f:facet>
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Id Detalle" />
                                    </f:facet>
                                    <h:outputText value="#{det.det_id}" />
                                </h:column>

                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Nombre Pre" />
                                    </f:facet>
                                    <h:outputText value="#{det.det_req}" />
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Ciclo Pre" />
                                    </f:facet>
                                    <h:outputText value="#{det.det_ciclo}" />
                                </h:column>
                                <h:column>

                                </h:column>
                                <h:column>

                                </h:column>
                                <h:column>

                                </h:column>
                                <h:column>

                                </h:column>
                                <h:column>

                                </h:column>
                                <h:column>

                                </h:column>
                                <h:column>

                                </h:column>
                            </rich:subTable>
                        </h:column>
                    </rich:dataTable>
                </h:form>
                <rich:modalPanel  id="mp" minHeight="400" minWidth="550" height="550" width="500" zindex="2000" onshow="showDiv();">
                    <rich:effect  name="hideDiv"  for="contentDiv" type="BlindUp" />
                    <rich:effect  name="showDiv"  for="contentDiv" type="BlindDown" />
                    <div id="contentDiv">
                        <f:facet name="header">

                            <h:outputText value="Registro Pre - Requisitos" />
                        </f:facet>
                        <f:facet name="controls">
                            <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp')" title="Cerrar"/>
                        </f:facet>

                        <h:form>
                            <table width="100%" style="font-size:10px; font-family:verdana">

                                <tr>
                                    <td colspan="3"><hr size="1">
                                    </td>
                                </tr>
                                <tr>

                                    <td width="30%" >
                                        <h:inputHidden id="iValor" value="#{managerPreRequisito.c_valor}"/>
                                        <h:inputHidden id="iId" value="#{managerPreRequisito.c_id}"/>
                                        <h:inputHidden id="iIdEspecialidad" value="#{managerPreRequisito.c_id_espe}"/>
                                        <h:inputHidden id="iIdplan" value="#{managerPreRequisito.c_id_plan}"/>
                                        <h:inputHidden id="iIdCiclo" value="#{managerPreRequisito.c_id_ciclo}"/>
						Codigo:
                                    </td>
                                    <td width="70%">
                                        <h:outputText id="iCodigo"  style=" width : 180px;" value="#{managerPreRequisito.c_codigo}" />
                                    </td>
                                    <td align="right" width="20%">
                                    </td>
                                </tr>

                                <tr>
                                    <td width="30%" >Nombre del Curso:</td>
                                    <td width="70%">
                                        <h:outputText id="iNombre"  style=" width : 180px;" value="#{managerPreRequisito.c_nombre}" />
                                    </td>
                                    <td align="right" width="20%">
                                    </td>
                                </tr>

                                <tr>
                                    <td width="30%" >Ciclo:</td>
                                    <td width="70%">
                                        <h:outputText id="iCiclo"  style=" width : 180px;" value="#{managerPreRequisito.c_ciclo}" />
                                    </td>
                                    <td align="right" width="20%">
                                    </td>
                                </tr>

                                <tr>
                                    <td width="30%" >Especialidad:</td>
                                    <td width="70%">
                                        <h:outputText id="iEspecialidad"  style=" width : 180px;" value="#{managerPreRequisito.c_espe}" />
                                    </td>
                                    <td align="right" width="20%">
                                    </td>
                                </tr>

                                <tr>
                                    <td width="30%" >Plan:</td>
                                    <td width="70%">
                                        <h:outputText id="iPlan"  style=" width : 180px;" value="#{managerPreRequisito.c_plan}" />
                                    </td>
                                    <td align="right" width="20%">
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3"><hr size="1">
                                    </td>
                                </tr>

                                <tr>


                                    <td width="30%" >
						Ciclo:
                                        <a4j:region id="xciclo">
                                            <h:selectOneMenu id="bCiclo" value="#{managerPreRequisito.b_id_ciclo}" >
                                                <f:selectItems value="#{managerPreRequisito.comboCiclo}"/>
                                                <a4j:support event="onchange"  action="#{managerPreRequisito.setearCiclo}" reRender="bCurso"/>
                                            </h:selectOneMenu>
                                        </a4j:region>
                                    </td>
                                    <td width="30%" >
						Curso:
                                        <a4j:region id="xcurso">
                                            <h:selectOneMenu id="bCurso" value="#{managerPreRequisito.b_id_curso}" >
                                                <f:selectItems value="#{managerPreRequisito.comboCurso}"/>
                                            </h:selectOneMenu>
                                        </a4j:region>
                                        <a4j:commandButton   image="/Imagenes/actions/edit_add.png"  title="Agregar"  action="#{managerPreRequisito.AgregarFila}" reRender="tablaGrupos,nombre1,tablaMaster,subtable" oncomplete="#{managerPreRequisito.oncomplete}"/>
                                    </td>

                                    <td width="70%">

                                    </td>
                                    <td align="right" width="20%">
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3">






                                        <table style="font-size:10px; font-family:verdana" width="100%">
                                            <tr>
                                                <td colspan="8" width="100%">
                                                    <rich:dataTable id="tablaGrupos" rows="10"
                                                                    onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                                    cellpadding="0" cellspacing="0"
                                                                    width="100%" border="0" value="#{managerPreRequisito.listaCursos}" var="cursos">

                                                        <h:column>
                                                            <f:facet name="header">
                                                                <h:outputText value="Id" />
                                                            </f:facet>
                                                            <h:outputText value="#{cursos.x_id}"/>
                                                        </h:column>

                                                        <h:column>
                                                            <f:facet name="header">
                                                                <h:outputText value="Id Curso / Plan" />
                                                            </f:facet>
                                                            <h:outputText value="#{cursos.x_id_curso}"/>
                                                        </h:column>

                                                        <h:column>
                                                            <f:facet name="header">
                                                                <h:outputText value="Nombre del Curso" />
                                                            </f:facet>
                                                            <h:outputText value="#{cursos.x_curso}"/>
                                                        </h:column>


                                                        <h:column>
                                                            <f:facet name="header">
                                                                <h:outputText value="Id Curso / Requisito" />
                                                            </f:facet>
                                                            <h:outputText value="#{cursos.x_id_req}"/>
                                                        </h:column>

                                                        <h:column>
                                                            <f:facet name="header">
                                                                <h:outputText value="Nombre del Curso" />
                                                            </f:facet>
                                                            <h:outputText value="#{cursos.x_req}"/>
                                                        </h:column>

                                                        <h:column>
                                                            <f:facet name="header">
                                                                <h:outputText value="Ciclo del Curso" />
                                                            </f:facet>
                                                            <h:outputText value="#{cursos.x_ciclo}"/>
                                                        </h:column>

                                                        <h:column>
                                                            <f:facet name="header">
                                                                <h:outputText value="Quitar" />
                                                            </f:facet>
                                                            <f:param id="p_id1" value="#{cursos.x_id}" />
                                                            <a4j:commandButton  image="/Imagenes/actions/edit_remove.png"  title="Eliminar"  actionListener="#{managerPreRequisito.QuitarFila}" reRender="tablaGrupos,tablaMaster,subtable"/>
                                                        </h:column>
                                                    </rich:dataTable>



                                                </td>
                                            </tr>

                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </h:form>
                    </div>
                </rich:modalPanel>
                <!-- fin  nuevo panel grid -->
            </rich:panel>

        </f:view>
    </body>
</html>  

