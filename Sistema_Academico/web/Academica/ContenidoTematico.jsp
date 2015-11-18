<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Asignar Contenido Tematico al Curso</title>
    </head>
    <body>
        <f:view>
            <jsp:include page="../TablaSistema/Menu.jsp"></jsp:include>
            <rich:panel>
                <h:form>
                    <table width="100%"  style="font-size:10px; font-family:verdana"  cellspacing="0" border="0" cellpadding="0">
                        <tr>
                            <td colspan="2"><strong>MANTENIMIENTO DE TEMÁTICAS</strong></td>
                            <td width="4%">&nbsp;</td>
                            <td width="11%">&nbsp;</td>
                            <td width="16%">&nbsp;</td>
                            <td width="10%">&nbsp;</td>
                            <td width="7%">&nbsp;</td>
                            <td width="22%" align="right">
                                <h:commandButton type="button" id="buscar" value="" action="#{managerUnidadTematica.Seleccionar}" image="/Imagenes/actions/viewmag.png"  title="Buscar" style=" width : 16px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="8" width="100%">
                                <hr width="100%" size="1">
                            </td>
                        </tr>
                        <tr>
                            <td width="13%" height="25" align="left">Facultad</td>
                            <td width="17%" align="left">
                                <h:selectOneMenu id="Facultad" value="#{managerUnidadTematica.b_facultad}"  style=" width : 180px;">
                                    <f:selectItems value="#{managerUnidadTematica.comboFacultades_s}" />
                                    <a4j:support event="onchange"  action="#{managerUnidadTematica.setearFacultad_s}" reRender="Especialidad,PCurricular,Ciclo"/>
                                </h:selectOneMenu>
                            </td>
                            <td>&nbsp;</td>
                            <td align="left">Especialidad</td>
                            <td align="left">
                                <h:selectOneMenu  id="Especialidad"  value="#{managerUnidadTematica.b_especialidad}" style=" width : 180px;">
                                    <f:selectItems value="#{managerUnidadTematica.comboEspecialidades_s}" />
                                    <a4j:support event="onchange"  action="#{managerUnidadTematica.setearEspecialidad_s}" reRender="PCurricular,Ciclo"/>
                                </h:selectOneMenu>
                            </td>
                            <td colspan="3">&nbsp;</td>
                        </tr>
                        <tr>
                            <td align="left">Plan Curricular</td>
                            <td align="left">
                                <h:selectOneMenu  id="PCurricular"  value="#{managerUnidadTematica.b_pcurricular}" style=" width : 180px;">
                                    <f:selectItems value="#{managerUnidadTematica.comboPlanCurricular_s}" />
                                    <a4j:support event="onchange"  action="#{managerUnidadTematica.setearPCurricular_s}" reRender="Ciclo"/>
                                </h:selectOneMenu>
                            </td>
                            <td>&nbsp;</td>
                            <td align="left">Ciclo</td>
                            <td align="left">
                                <h:selectOneMenu  id="Ciclo"  value="#{managerUnidadTematica.b_ciclo}">
                                    <f:selectItems value="#{managerUnidadTematica.comboCiclo_s}" />
                                </h:selectOneMenu>
                            </td>
                            <td colspan="3">&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="8" width="100%">
                                <hr width="100%" size="1">
                            </td>
                        </tr>
                    </table>

                </h:form>
                <h:form>
                    <table style=" width : 100%;"  cellspacing="0" border="0" cellpadding="0">
                        <tr>
                            <td colspan="5" style=" width : 100%">
                                <p align="right"/>
                                <rich:datascroller id="barra" align="right"  for="tablaMaster" maxPages="8"  style=" width : 100%;"/>
                            </td>
                        </tr>
                    </table>
                    <rich:dataTable id="tablaMaster" rows="10" onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                    cellpadding="0" cellspacing="0"
                                    width="100%" border="0" value="#{managerUnidadTematica.listaDatos}" var="cursos">

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
                                <h:outputText value="Nombre" />
                            </f:facet>
                            <h:outputText value="#{cursos.nombre}"/>
                        </h:column>

                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Creditos" />
                            </f:facet>
                            <h:outputText value="#{cursos.creditos}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Tipo" />
                            </f:facet>
                            <h:outputText value="#{cursos.tipo}"/>
                        </h:column>

                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Asignar Temas" />
                            </f:facet>
                            <p align="center">

                                <h:graphicImage value="/Imagenes/actions/pencil.png" style="border-width: 0px;" height="16">
                                    <a4j:support event="onclick" actionListener="#{managerUnidadTematica.Asignar}" reRender="pc_Id,tablaDetalle,scrolldetalle,pc_Titulo,pc_Des,pc_Vigente,pc_Fac,pc_Esp,pc_Des1,pc_Ciclo,pc_Curso,sema1" oncomplete="javascript:Richfaces.showModalPanel('nuevo',{width:500, top:8})" />
                                </h:graphicImage>

                            </p>
                            <f:param id="p_id" value="#{cursos.b_id}" />

                        </h:column>
                    </rich:dataTable>
                </h:form>

                <rich:modalPanel  id="nuevo" minHeight="540" minWidth="600" height="550" width="570" zindex="2000">

                    <f:facet name="header">

                        <h:outputText value="ASIGNACION DE CONTENIDO TEMATICO" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('nuevo')" title="Cerrar" />
                    </f:facet>

                    <h:form>
                        <table width="100%" style="font-size:10px; font-family:verdana">
                            <tr>
                                <td align="right" colspan="4">
                                    <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar" action="#{managerUnidadTematica.Guardar}" reRender="pc_Id,tablaDetalle,scrolldetalle,pc_Titulo,pc_Des,pc_Vigente,pc_Fac,pc_Esp,pc_Des1,pc_Ciclo,pc_Curso" oncomplete="#{managerUnidadTematica.mensajes}"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="10"><hr size="1">
                                </td>
                            </tr>

                            <tr>
                                <td width="20%" >Especialidad:</td>
                                <td width="40%">
                                    <h:outputText id="pc_Esp" value="#{managerUnidadTematica.especialidad_descripcion}"/>
                                </td>
                                <td width="20%">Ciclo:</td>
                                <td width="40%"><h:outputText id="pc_Ciclo" value="#{managerUnidadTematica.ciclo_descripcion}"/></td>
                            </tr>
                            <tr>
                                <td width="20%" >Plan Curricular:</td>
                                <td width="40%">
                                    <h:outputText id="pc_Des1" value="#{managerUnidadTematica.plan_descripcion}"/>
                                </td>
                                <td width="20%">Curso:</td>
                                <td><h:outputText id="pc_Curso" value="#{managerUnidadTematica.curso_descripcion}"/></td>
                            </tr>



                            <h:inputHidden id="pc_Titulo1" value="#{managerUnidadTematica.ct_titulo_u}"/>
                        </table>

                        <table width="100%" border="0" style="font-size:10px; font-family:verdana"  cellspacing="0" cellpadding="0">

                            <tr>
                                <td width="120">Titulo</td>
                                <td align="left" colspan="6">
                                    <h:inputText id="pc_Titulo" value="#{managerUnidadTematica.ct_titulo_u}" style="width : 100%;" maxlength="30" />
                                </td>
                                <td></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>Descripción</td>
                                <td align="left" colspan="6">
                                    <h:inputTextarea id="pc_Des" style="width : 100%;" value="#{managerUnidadTematica.ct_descripcion_u}" />
                                </td>
                                <td>
                                </td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>Vigente</td>
                                <td align="left">
                                    <h:selectBooleanCheckbox id="pc_Vigente" value="#{managerUnidadTematica.ct_vigente_u}" style="width : 180px;"/>
                                </td>
                                <td>
                                </td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="10"><hr size="1">
                                </td>
                            </tr>
                            <tr>
                                <td>Contenido de la Semana
                                    <h:outputText value="#{managerUnidadTematica.d_semana1}" id="sema1"/>
                                </td>
                                <td colspan="6">
                                    <h:inputTextarea value="#{managerUnidadTematica.d_contenido}" id="areacontenido" style="width : 100%"/>
                                </td>
                                <td>
                                    <a4j:commandButton image="/Imagenes/actions/edit_add.png"  action="#{managerUnidadTematica.Agregar}" reRender="tablaDetalle,scrolldetalle,areacontenido,sema1" title="Adicionar Contenido de Semana"/>
                                </td>
                                <td>
                                    <a4j:commandButton image="/Imagenes/actions/edit_remove.png"  action="#{managerUnidadTematica.Quitar}" reRender="tablaDetalle,scrolldetalle,areacontenido,sema1" title="Eliminar Semana"/>
                                </td>
                            </tr>
                        </table>

                    </h:form>
                    <h:form>
                        <table style=" width : 100%;">
                            <tr>
                                <td colspan="5" style=" width : 100%">
                                    <p align="right"/>
                                    <rich:datascroller id="scrolldetalle" align="right"  for="tablaDetalle" maxPages="5"  style=" width : 100%;"/>
                                </td>
                            </tr>
                        </table>
                        <rich:dataTable id="tablaDetalle" rows="6" onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                        onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                        cellpadding="0" cellspacing="0"
                                        width="100%" border="0" value="#{managerUnidadTematica.listaDatosDetalle}" var="detalle">

                            <h:inputHidden value="#{detalle.d_id}"/>

                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Sem." />
                                </f:facet>
                                <h:outputText value="#{detalle.d_semana}" style=" width : 40px"/>
                            </h:column>


                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Contenido" />
                                </f:facet>
                                <h:inputTextarea value="#{detalle.d_contenido}" rendered="#{detalle.editable}"/>
                                <h:outputText value="#{detalle.d_contenido}" rendered="#{detalle.ver}" id="txtCont"/>
                            </h:column>

                            <h:inputHidden value="#{detalle.d_contem_id}"/>


                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="" />
                                </f:facet>
                                <a4j:commandButton value="" image="/Imagenes/actions/klipper_dock.png" title="Editar"  actionListener="#{detalle.EditarFilaDetalle}" reRender="tablaDetalle,scrolldetalle" style=" width : 16px;" rendered="#{detalle.editableb1}"/>

                                <a4j:commandButton actionListener="#{detalle.GEditar}"  image="/Imagenes/actions/filesave.png" value=""  title="Guardar" rendered="#{detalle.editableb2}" reRender="tablaDetalle,scrolldetalle" />
                                <a4j:commandButton actionListener="#{detalle.Cancelar}"  value="" image="/Imagenes/actions/no.png" title="Cancelar" rendered="#{detalle.editableb2}" reRender="tablaDetalle,scrolldetalle,txtCont"/>
                            </h:column>


                        </rich:dataTable>
                    </h:form>
                </rich:modalPanel>
            </rich:panel>
        </f:view>
    </body>

</html>		
