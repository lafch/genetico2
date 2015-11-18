<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Mantenimiento de Aulas</title>
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
            <rich:panel style="width : 100%; background:#FFF; border:1px !important;" >
                <f:facet name="FACULTADES">
                </f:facet>
                <h:form id="form1" style="width:100%">
                    <table width="100%" style="font-size:10px; font-family:verdana">
                        <tr >
                            <td width="20%" colspan="2"><strong>MANTENIMIENTO DE AULAS</strong>
                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right">
                                <h:graphicImage value="/Imagenes/new-32.png" style="border-width: 0px;cursor: pointer;">
                                    <a4j:support oncomplete="javascript:Richfaces.showModalPanel('mp',{width:450, top:130})" 
                                                 event="onclick" action="#{managerAula.Nuevo}" 
                                                 reRender="descripcion_i,capacidad_i,local_i,pabellon_i,especialidad_i" />
                                </h:graphicImage>
                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right">
                                <h:graphicImage value="/Imagenes/printer-32.png" title="Imprimir Listado"/>
                            </td>
                            <td align=right width="50%">
                                <a4j:commandButton type="button" id="buscar" title="Buscar"
                                                   action="#{managerAula.Seleccionar}"
                                                   image="/Imagenes/search-32.png"
                                                   reRender="tablaMaster, barra"/>
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
                            <td width="10%">Nombre de Aula:
                            </td>
                            <td width="30%">
                                <h:inputText value="#{managerAula.b_des}" style="width : 180px;"/></td>
                            <td width="50%"></td>
                        </tr>
                        <tr>
                            <td width="10%">Local:
                            </td>
                            <td width="30%">
                                <h:selectOneMenu value="#{managerAula.b_id_local_f}">
                                    <f:selectItems value="#{managerAula.comboLocales_f}" />
                                    <a4j:support event="onchange" reRender="pabellon_f"/>
                                </h:selectOneMenu></td>
                            <td width="50%"></td>
                        </tr>
                        <tr>
                            <td width="10%">Pabellon:
                            </td>
                            <td width="30%">
                                <h:selectOneMenu id="pabellon_f" value="#{managerAula.b_id_pabellon_f}">
                                    <f:selectItems value="#{managerAula.comboPabellones_f}" />
                                </h:selectOneMenu></td>
                            <td width="50%"></td>
                        </tr>
                        <tr>
                            <td width="10%">Especialidad:
                            </td>
                            <td width="30%">
                                <h:selectOneMenu id="especialidad_f" value="#{managerAula.b_id_Especialidad_f}">
                                    <f:selectItems value="#{managerAula.comboEspecialidad_f}" />
                                </h:selectOneMenu></td>
                            <td width="50%"></td>
                        </tr>
                        <tr>
                            <td width="10%">Tipo Aula:
                            </td>
                            <td width="30%">
                                <h:selectOneMenu id="tipo_aula_f" value="#{managerAula.b_id_Tipo_Aula_f}">
                                    <f:selectItems value="#{managerAula.comboTipoAula_f}" />
                                </h:selectOneMenu></td>
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
                    <table width="100%">
                        <tr>
                            <td align="center">
                                <h:form id="frmLstAulas">
                                    <rich:datascroller id="barra" align="right"  for="tablaMaster"
                                                       maxPages="8"  style=" width : 70%;"/>
                                    <rich:spacer height="20px"/>
                                    <rich:dataTable id="tablaMaster" rows="10"
                                                    onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                    cellpadding="0" cellspacing="0" border="0"
                                                    width="70%" value="#{managerAula.listaAula}" var="aula">
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Id" />
                                            </f:facet>
                                            <h:outputText value="#{aula.b_id}"/>
                                            <f:param id="p_id" value="#{aula.b_id}"/>
                                            <f:param id="p_aula_detalle" value="#{aula.b_des}"/>
                                        </rich:column>

                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Descripcion" />
                                            </f:facet>
                                            <h:outputText value="#{aula.b_des}" rendered="#{aula.view}" />
                                            <h:inputText value="#{aula.b_des}" rendered="#{aula.editable}" maxlength="45"/>
                                        </rich:column>

                                        <rich:column style="text-align: center;">
                                            <f:facet name="header">
                                                <h:outputText value="Capacidad" />
                                            </f:facet>
                                            <h:outputText value="#{aula.b_cap}" rendered="#{aula.view}"/>
                                            <h:inputText value="#{aula.b_cap}" rendered="#{aula.editable}" onkeypress="return validar(event);"/>
                                        </rich:column>
                                        
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Tipo Aula" />
                                            </f:facet>
                                            <h:outputText value="#{aula.b_desc_tipo_aula}" rendered="#{aula.view}" />

                                            <h:selectOneMenu value="#{aula.b_id_tipo_aula}" rendered="#{aula.editable}">
                                                <f:selectItems value="#{aula.comboTipoAula}" />
                                            </h:selectOneMenu>
                                        </rich:column>

                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Local" />
                                            </f:facet>
                                            <h:outputText value="#{aula.b_desc_local}" rendered="#{aula.view}" />

                                            <h:selectOneMenu value="#{aula.b_id_local}" rendered="#{aula.editable}">
                                                <f:selectItems value="#{aula.comboLocales}" />
                                                <a4j:support event="onchange" reRender="pabellon_s"/>
                                            </h:selectOneMenu>
                                        </rich:column>

                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Pabellon" />
                                            </f:facet>
                                            <h:outputText  value="#{aula.b_desc_pabellon}" rendered="#{aula.view}"/>

                                            <h:selectOneMenu id="pabellon_s" value="#{aula.b_id_pabellon}" rendered="#{aula.editable}">
                                                <f:selectItems value="#{aula.comboPabellones}" />
                                            </h:selectOneMenu>
                                        </rich:column>

                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Especialidad" />
                                            </f:facet>
                                            <h:outputText  value="#{aula.b_desc_especialidad}" rendered="#{aula.view}"/>

                                            <h:selectOneMenu id="especialidad_s" value="#{aula.b_id_especialidad}" rendered="#{aula.editable}">
                                                <f:selectItems value="#{aula.comboEspecialidad}" />
                                            </h:selectOneMenu>
                                        </rich:column>
                                        <h:column>
                                            <f:facet name="header">
                                                <h:outputText value="Horario"/>
                                            </f:facet>
                                            <a4j:commandButton image="#{aula.v_imagen_horario}"
                                                               title="Horario"
                                                               actionListener="#{managerAula.cargarHorario}"
                                                               oncomplete="#{managerAula.oncomplete}"
                                                               reRender="formHoraria,horPabellon,horAula"/>
                                        </h:column>

                                        <rich:column style="text-align: center;">
                                            <f:facet name="header">
                                                <h:outputText value="Edit" />
                                            </f:facet>
                                            <h:commandButton actionListener="#{aula.EditarFila}" value="" image="/Imagenes/actions/klipper_dock.png" title="Editar" rendered="#{aula.view}"/>

                                            <f:param id="id" value="#{aula.b_id}" />
                                            <f:param id="descripcion" value="#{aula.b_des}" />
                                            <f:param id="capacidad"   value="#{aula.b_cap}" />
                                            <f:param id="pabellon"   value="#{aula.b_id_pabellon}" />
                                            <f:param id="especialidad"   value="#{aula.b_id_especialidad}" />
                                            <f:param id="tipo"   value="#{aula.b_id_tipo_aula}" />

                                            <a4j:commandButton actionListener="#{aula.Editar}"  image="/Imagenes/actions/filesave.png" value=""  title="Guardar" rendered="#{aula.editable2}" reRender="tablaMaster, barra" oncomplete="#{aula.oncomplete}"/>
                                            <h:commandButton actionListener="#{aula.Cancelar}"  value="" image="/Imagenes/actions/fileclose.png" title="Cancelar" rendered="#{aula.editable}"/>
                                        </rich:column>

                                        <rich:column style="text-align: center;">
                                            <f:facet name="header" >
                                                <h:outputText value="Delete" />
                                            </f:facet>
                                            <h:commandButton  image="/Imagenes/actions/delete.gif"  title="Eliminar"
                                                              actionListener="#{managerAula.EliminarFila}"
                                                              action="#{managerAula.Seleccionar}"  
                                                              onclick="javascript:return (confirm('¿Esta realmente seguro de Eliminar?'));"/>
                                            <f:param id="delete"  value="#{aula.b_id}" />
                                        </rich:column>
                                    </rich:dataTable>
                                </h:form>
                            </td>
                        </tr>
                    </table>
            </rich:panel>

            <rich:modalPanel  id="mp" minHeight="350" minWidth="650" height="350" width="500" zindex="2000">
                <f:facet name="header">
                    <h:outputText value="Registro de Aulas" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('mp')" title="Cerrar"/>
                </f:facet>

                <h:form>
                    <table width="100%" style="font-size:10px; font-family:verdana">
                        <tr >
                            <td align="right" colspan="2">
                                <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar" 
                                                     actionListener="#{managerAula.Enviar1}"
                                                     reRender="tablaMaster"
                                                     oncomplete="#{managerAula.oncomplete}"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr size="1"></td>
                        </tr>
                        <tr>
                            <td class="tdLabel" width="22%">*Descripcion:</td>
                            <td>
                                <h:inputText label="Descripcion" id="descripcion_i"  style=" width : 180px;" value="#{managerAula.b_des_i}"  maxlength="45"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel">*Capacidad:</td>
                            <td>
                                <h:inputText id="capacidad_i" label="Capacidad" value="#{managerAula.b_cap}" style="width : 180px;" onkeypress="return validar(event);"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="tdLabel">Local:</td>
                            <td>
                                <h:selectOneMenu id="local_i" value="#{managerAula.b_id_local}">
                                    <f:selectItems value="#{managerAula.comboLocales}" />
                                    <a4j:support event="onchange" reRender="pabellon_i"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel">*Pabellon:</td>
                            <td>
                                <h:selectOneMenu id="pabellon_i" value="#{managerAula.b_id_pabellon}">
                                    <f:selectItems value="#{managerAula.comboPabellones}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>

                        <tr>
                            <td class="tdLabel">*Especialidad:</td>
                            <td>
                                <h:selectOneMenu id="especialidad_i" value="#{managerAula.b_id_especialidad}">
                                    <f:selectItems value="#{managerAula.comboEspecialidad}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel">*Tipo Aula:</td>
                            <td>
                                <h:selectOneMenu id="aula_i" value="#{managerAula.b_id_tipo_aula}">
                                    <f:selectItems value="#{managerAula.comboTipoAula}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr size="1"></td>
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
                                                   actionListener="#{managerAula.guardarHorarios}"
                                                   oncomplete="#{managerAula.oncomplete}"
                                                   reRender="tablaMaster, barra"
                                                   />
                            </td>
                        </tr>
                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                        <tr>
                            <td class="tdLabel" style="width: 100px;"><h:outputText value="Docente"/></td>
                            <td><h:outputText value="#{managerAula.aula_detalle}"/></td>
                        </tr>
                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                        <tr><td colspan="2"><hr width="100%"/></td></tr>

                        <tr>
                            <td class="tdLabel"><h:outputText value="Día"/></td>
                            <td>
                                <h:selectManyCheckbox value="#{managerAula.horaria.lstDiasCod}" layout="lineDirection">
                                    <f:selectItems value="#{managerAula.horaria.lstSeDias}" />
                                </h:selectManyCheckbox>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Inicio"/></td>
                            <td>
                                <h:panelGroup>
                                    <h:inputText id="horIniH" value="#{managerAula.horaria.inicio_h}" maxlength="2" required="true" style="width: 20px;"/>
                                    <h:outputLabel value=":"/>
                                    <h:inputText id="horIniM" value="#{managerAula.horaria.inicio_m}" maxlength="2" required="true" style="width: 20px;"/>
                                    <h:selectOneMenu id="horIniMeridianos" value="#{managerAula.horaria.meridiano_inicio}">
                                        <f:selectItems value="#{managerAula.horaria.meridianos}"/>
                                    </h:selectOneMenu>
                                </h:panelGroup>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Fin"/></td>
                            <td>
                                <h:panelGroup>
                                    <h:inputText id="horFinH" value="#{managerAula.horaria.fin_h}" maxlength="2" required="true" style="width: 20px;"/>
                                    <h:outputLabel value=":"/>
                                    <h:inputText id="horFinM" value="#{managerAula.horaria.fin_m}" maxlength="2" required="true" style="width: 20px;"/>
                                    <h:selectOneMenu id="horFinMeridianos" value="#{managerAula.horaria.meridiano_fin}">
                                        <f:selectItems value="#{managerAula.horaria.meridianos}"/>
                                    </h:selectOneMenu>
                                </h:panelGroup>
                                <rich:spacer width="20px"/>

                                <a4j:commandButton image="/Imagenes/actions/edit_add.png"
                                                   title="Agregar"
                                                   actionListener="#{managerAula.agregarHorario}"
                                                   oncomplete="#{managerAula.oncomplete}"
                                                   reRender="formHoraria"/>
                            </td>
                        </tr>

                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                        <tr>
                            <td colspan="4" align="center" valign="top">
                                <rich:dataTable id="tablaDetalle" var="hor" width="100%"
                                                value="#{managerAula.nListaHoraria}"
                                                onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'">
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Id"/>
                                        </f:facet>
                                        <h:outputText value="#{hor.horId}"/>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Día"/>
                                        </f:facet>
                                        <h:outputText value="#{hor.v_hor_dia}" rendered="#{hor.view_bool}"/>
                                        <h:selectOneMenu value="#{hor.horDia}" rendered="#{hor.editable_bool}">
                                            <f:selectItems value="#{managerAula.horaria.dias}"/>
                                        </h:selectOneMenu>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Inicio"/>
                                        </f:facet>
                                        <h:outputText value="#{hor.horHini}" rendered="#{hor.view_bool}">
                                            <f:convertDateTime pattern="hh:mm a"/>
                                        </h:outputText>

                                        <h:panelGroup>
                                            <h:inputText value="#{hor.inicio_h}" maxlength="2" required="true" style="width: 20px;" rendered="#{hor.editable_bool}"/>
                                            <h:outputLabel value=":" rendered="#{hor.editable_bool}"/>
                                            <h:inputText value="#{hor.inicio_m}" maxlength="2" required="true" style="width: 20px;" rendered="#{hor.editable_bool}"/>
                                            <h:selectOneMenu value="#{hor.meridiano_inicio}" rendered="#{hor.editable_bool}">
                                                <f:selectItems value="#{managerAula.horaria.meridianos}"/>
                                            </h:selectOneMenu>
                                        </h:panelGroup>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Fin"/>
                                        </f:facet>
                                        <h:outputText value="#{hor.horHfin}" rendered="#{hor.view_bool}">
                                            <f:convertDateTime pattern="hh:mm a"/>
                                        </h:outputText>

                                        <h:panelGroup>
                                            <h:inputText value="#{hor.fin_h}" maxlength="2" required="true" style="width: 20px;" rendered="#{hor.editable_bool}"/>
                                            <h:outputLabel value=":" rendered="#{hor.editable_bool}"/>
                                            <h:inputText value="#{hor.fin_m}" maxlength="2" required="true" style="width: 20px;" rendered="#{hor.editable_bool}"/>
                                            <h:selectOneMenu value="#{hor.meridiano_fin}" rendered="#{hor.editable_bool}">
                                                <f:selectItems value="#{managerAula.horaria.meridianos}"/>
                                            </h:selectOneMenu>
                                        </h:panelGroup>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Docente"/>
                                        </f:facet>
                                        <h:outputText value="#{hor.acAula.aulDes}" rendered="#{hor.view_bool}"/>
                                        <h:outputText value="#{hor.acAula.aulDes}" rendered="#{hor.editable_bool}"/>                                            
                                    </rich:column>


                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Editar"/>
                                        </f:facet>
                                        <a4j:commandButton actionListener="#{hor.edit}"
                                                           image="/Imagenes/actions/klipper_dock.png" title="Editar"
                                                           rendered="#{hor.view_bool}" reRender="tablaDetalle"/>
                                        <a4j:commandButton actionListener="#{hor.aceptar}" oncomplete="#{hor.oncomplete}"
                                                           image="/Imagenes/actions/fileexport.png" title="Aceptar"
                                                           rendered="#{hor.editable_bool}" reRender="tablaDetalle"/>
                                        <a4j:commandButton actionListener="#{hor.cancelar}"
                                                           image="/Imagenes/actions/fileclose.png" title="Cancelar"
                                                           rendered="#{hor.editable_bool}" reRender="tablaDetalle"/>
                                    </rich:column>

                                    <f:param id="p_hor_pos" value="#{hor.posicion}"/>
                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Eliminar"/>
                                        </f:facet>
                                        <f:param id="id_hor_delete" value="#{hor.horId}"/>
                                        <a4j:commandButton image="/Imagenes/actions/edit_remove.png"
                                                           title="Eliminar"
                                                           actionListener="#{managerAula.removerHorario}"
                                                           reRender="tablaDetalle"/>
                                    </rich:column>
                                </rich:dataTable>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>
        </f:view>
    </body>
</html>
