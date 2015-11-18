<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Mantenimiento de Roles</title>
         <link href="../css/componentes.css" rel="stylesheet" media="screen"/>
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
                            <td width="20%" colspan="2"><strong>MANTENIMIENTO DE ROLES</strong>
                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right">
                                <h:graphicImage value="/Imagenes/new-32.png" style="border-width: 0px;cursor: pointer;">
                                    <a4j:support oncomplete="javascript:Richfaces.showModalPanel('mp',{width:450, top:130})" 
                                                 event="onclick" action="#{managerRol.Nuevo}" 
                                                 reRender="DESCRIPCION_i" />
                                </h:graphicImage>
                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right">
                                <h:graphicImage value="/Imagenes/printer-32.png" title="Imprimir Listado"/>
                            </td>
                            <td align=right width="50%">
                                <a4j:commandButton type="button" id="buscar" title="Buscar"
                                                   action="#{managerRol.Seleccionar}"
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
                            <td width="10%">Rol:
                            </td>
                            <td width="50%">
                                <h:selectOneMenu value="#{managerRol.rol_id}" >
                                    <f:selectItems value="#{managerRol.cboRol}" />
                                    
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
                                                width="70%" value="#{managerRol.listaRol}" var="rol">
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Id" />
                                        </f:facet>
                                        <h:outputText value="#{rol.rol_id}"/>
                                        <f:param id="p_id" value="#{rol.rol_id}"/>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Rol" />
                                        </f:facet>
                                        <h:outputText value="#{rol.rol_tipo}" rendered="#{rol.view}" />
                                        <h:inputText value="#{rol.rol_tipo}" rendered="#{rol.editable}" maxlength="45"/>
                                    </rich:column>

                                    <rich:column >
                                        <f:facet name="header">
                                            <h:outputText value="Rol Activo" />
                                        </f:facet>
                                        <h:outputText value="#{rol.rol_activo_desc}" rendered="#{rol.view}" />

                                        <h:selectOneMenu value="#{rol.rol_activo}" rendered="#{rol.editable}">
                                            <f:selectItems value="#{rol.cboActivo}" />
                                        </h:selectOneMenu>
                                    </rich:column>

                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Menus Asignados" />
                                        </f:facet>
                                        <a4j:commandButton image="#{rol.v_imagen_horario}"
                                                               title="Horario"
                                                               actionListener="#{managerRol.cargarVista}"
                                                               oncomplete="#{managerRol.oncomplete}"
                                                               reRender="formHoraria,horPabellon,horAula"/>

                                    </rich:column>

                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Edit" />
                                        </f:facet>

                                        <h:commandButton actionListener="#{rol.EditarFila}" value="" image="/Imagenes/actions/klipper_dock.png" title="Editar" rendered="#{rol.view}"/>
                                        <f:param id="id" value="#{rol.rol_id}" />
                                        <f:param id="desc" value="#{rol.rol_tipo}" />
                                        <f:param id="activo"   value="#{rol.rol_activo}" />


                                        <a4j:commandButton actionListener="#{rol.Editar}"  image="/Imagenes/actions/filesave.png" value=""  title="Guardar" rendered="#{rol.editable2}" reRender="tablaMaster, barra" oncomplete="#{rol.oncomplete}"/>
                                        <h:commandButton actionListener="#{rol.Cancelar}" image="/Imagenes/actions/fileclose.png"   value=""  title="Cancelar" rendered="#{rol.editable}"/>
                                    </rich:column>

                                    <rich:column style="text-align: center;">
                                        <f:facet name="header" >
                                            <h:outputText value="Delete" />
                                        </f:facet>
                                        <a4j:commandButton  image="/Imagenes/actions/delete.gif"  title="Eliminar"
                                                          actionListener="#{managerRol.EliminarFila}"
                                                          action="#{managerRol.Seleccionar}"  
                                                          onclick="javascript:return (confirm('¿Esta realmente seguro de Eliminar este registro?'));"   oncomplete="#{managerMenuMant.oncomplete}" reRender="tablaMaster, barra"/>
                                        <f:param id="delete"  value="#{rol.rol_id}" />
                                    </rich:column>
                                </rich:dataTable>
                            </h:form>
                        </td>
                    </tr>
                </table>
            </rich:panel>

            <rich:modalPanel  id="mp" minHeight="120" minWidth="200" height="120" width="200" zindex="2000" styleClass="fondo">
                <f:facet name="header">
                    <h:outputText value="Registro de Rol" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('mp')" title="Cerrar"/>
                </f:facet>

                <h:form>
                    <table width="100%" style="font-size:10px; font-family:verdana" onload="document.getElementById('DESCRIPCION_i').focus()" class="fondo">
                        <tr >
                            <td align="right" colspan="2">
                                <a4j:commandButton   image="/Imagenes/save-32.png"  title="Guardar" 
                                                     actionListener="#{managerRol.Enviar1}"
                                                     reRender="tablaMaster"
                                                     oncomplete="#{managerRol.oncomplete}"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr size="1"></td>
                        </tr>

                        <tr>
                            <td class="tdLabel">*DESCRIPCION:</td>
                            <td>
                                <h:inputText id="DESCRIPCION_i" label="DESCRIPCION" value="#{managerRol.rol_tipo}" style="width : 180px;" onkeypress="return validarLetras(event);"/>
                            </td>
                        </tr>

                        <tr>
                            <td colspan="2"><hr size="1"></td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>
            
            <rich:modalPanel id="mpHoraria" minWidth="428" height="400"
                             resizeable="true" zindex="3000" style="overflow-y: auto">
                <f:facet name="header">
                    <h:outputText value="Arbol de Asignacion"/>
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
                                                   title="Asignar nuevo menu"
                                                   actionListener="#{managerRol.asignarMenuRol}"
                                                   oncomplete="#{managerRol.oncomplete}"
                                                   reRender="tree,tablaMaster, barra" rendered="#{managerRol.idMenu01>0}"
                                                   />
                            </td>
                        </tr>
                        <tr>
                            <td width="30%">Menu Nivel 01:
                            </td>
                            <td width="70%">
                                <h:selectOneMenu value="#{managerRol.idMenu01}"  >
                                    <f:selectItems value="#{managerRol.cboMenu01}" />
                                    <a4j:support event="onchange" reRender="formHoraria"/>
                                </h:selectOneMenu></td>
                        </tr>
                        <tr>
                            <td width="30%"><h:outputLabel value="Menu Nivel 02" rendered="#{managerRol.vista02}"/>
                            </td>
                            <td width="70%">
                                <h:selectOneMenu id="menu02" value="#{managerRol.idMenu02}" rendered="#{managerRol.vista02}">
                                    <f:selectItems value="#{managerRol.cboMenu02}" />
                                    <a4j:support event="onchange" reRender="formHoraria"/>
                                </h:selectOneMenu></td>
                           
                        </tr>
                        <tr>
                            <td width="30%"><h:outputLabel value="Menu Nivel 03" rendered="#{managerRol.vista03}"/>
                            </td>
                            <td width="70%">
                                
                                <h:selectOneMenu id="menu03" value="#{managerRol.idMenu03}" rendered="#{managerRol.vista03}">
                                    <f:selectItems value="#{managerRol.cboMenu03}" />
                                    <a4j:support event="onchange" reRender="formHoraria"/>
                                </h:selectOneMenu></td>
                            
                        </tr>
                        <tr>
                            <td width="30%"><h:outputLabel value="Menu Nivel 04" rendered="#{managerRol.vista04}"/>
                            </td>
                            <td width="70%">
                                <h:selectOneMenu id="menu04" value="#{managerRol.idMenu04}" rendered="#{managerRol.vista04}">
                                    <f:selectItems value="#{managerRol.cboMenu04}" />
                                </h:selectOneMenu></td>
                            
                        </tr>
                        <tr>
                            <td colspan="2"><hr width="100%"/></td>
                        </tr>
                        <tr>
                            <td>
                            <rich:tree id="tree" style="width:100%"  value="#{managerRol.root}" var="item">
							<rich:treeNode>
                                                            <h:selectBooleanCheckbox value="#{item.seleccionado}"  >	  
                                                                <a4j:support event="onclick" actionListener="#{managerRol.marcar(item)}" reRender="formHoraria" />
								</h:selectBooleanCheckbox>
								<h:outputText value="#{item.menDescripcion} #{item.seleccionado}" escape="false" />
							</rich:treeNode>
			   </rich:tree>    
                            </td>
                            <td style="text-align:end; vertical-align:sub; width:164px !important; display:block;">
                                
                                <a4j:commandButton   image="/Imagenes/save-32.png"  title="Guardar Rol" 
                                                     actionListener="#{managerRol.validarPermisoRol}"
                                                     reRender="tree"
                                                     oncomplete="#{managerRol.oncomplete}"/>
                            </td>
                        </tr>
                        
                    </table>
                </h:form>
            </rich:modalPanel>


        </f:view>
    </body>
</html>
