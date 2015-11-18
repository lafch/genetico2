<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Mantenimiento de Usuarios</title>
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
            <rich:panel style=" width : 100%; background:#FFF; border:1px !important;" >
                <f:facet name="FACULTADES">
                </f:facet>
                <h:form id="form1" style="width:100%">
 
                    <table width="100%" style="font-size:10px; font-family:verdana">
                        <tr >
                            <td width="20%" colspan="2" ><strong>MANTENIMIENTO DE USUARIOS</strong>
                            </td>
                            <td width="30%"></td>
                            <td width="30%"></td>
                            <td width="30%" align="right">
                                <%--<h:graphicImage value="/Imagenes/actions/fileprint.png" title="Imprimir Listado"/>--%>
                                <h:graphicImage value="/Imagenes/new-32.png" style="border-width: 0px;cursor: pointer">
                                    <a4j:support oncomplete="javascript:Richfaces.showModalPanel('mp')"
                                                 event="onclick" action="#{managerUsuario.Nuevo}" reRender="password_u,usuario_u,rol_u" />
                                </h:graphicImage>
                            </td>

                            <td align=right width="50%">
                                <a4j:commandButton type="button" id="buscar"
                                                   action="#{managerUsuario.Seleccionar}"
                                                   image="/Imagenes/search-32.png"
                                                   reRender="formUsuario"
                                                   title="Buscar" status="commonstatus"/>
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
                            <td width="30%">
                                <h:selectOneMenu value="#{managerUsuario.rol_id_f}" >
                                    <f:selectItems value="#{managerUsuario.cboRol}"/>
                                </h:selectOneMenu></td>
                            <td width="50%"></td>
                        </tr>
                         <tr>
                            <td width="10%">Estado:
                            </td>
                            <td width="30%">
                                <h:selectOneMenu value="#{managerUsuario.act_id_f}" >
                                    <f:selectItems value="#{managerUsuario.cboActivo}"/>
                                </h:selectOneMenu></td>
                            <td width="50%"></td>
                        </tr>
                        <tr>
                            <td width="10%">Usuario:
                            </td>
                            <td width="30%">
                                <h:inputText value="#{managerUsuario.bus_usuario}" />
                            <td width="50%"></td>
                        </tr>

                        <tr>
                            <td colspan="7" width="100%">
                                <hr width="100%" size="1">
                            </td>
                        </tr>

                    </table>
                </h:form>
                <h:form id="formUsuario" style=" width : 100%;">
                    
                                
                    <rich:dataTable value="#{managerUsuario.listUsuarios}" var="usu" width="100%" id="tablaMaster" rows="10">
                        <h:column >
                            <h:inputHidden value="#{usu.usu_id}"/>
                            <f:param id="p_id" value="#{usu.rol_id}"/>
                            <f:facet name="header">
                                <h:outputText value="Usuario" />
                            </f:facet>
                            <h:outputText value="#{usu.usu_usu}"/>
                            
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Password" />
                            </f:facet>
                            <h:outputText value="#{usu.usu_psw}" rendered="#{usu.view}" />
                            <h:inputText  value="#{usu.usu_psw}" rendered="#{usu.editable}"  maxlength="12" title="Clave"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Rol" />
                            </f:facet>
                            <h:outputText value="#{usu.rol_nombre}" rendered="#{usu.view}" />
                            <h:selectOneMenu value="#{usu.rol_id}" rendered="#{usu.editable}">
                                <f:selectItems value="#{usu.cboRolE}" />
                            </h:selectOneMenu>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Estado" />
                            </f:facet>
                            <h:outputText value="#{usu.usu_activo_desc}" rendered="#{usu.view}" />
                            <h:selectOneMenu value="#{usu.usu_activo_id}" rendered="#{usu.editable}">
                                <f:selectItems value="#{usu.cboActivoE}" />
                            </h:selectOneMenu>
                        </h:column>
                        
                        <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Permisos" />
                                        </f:facet>
                                        <a4j:commandButton     image="/Imagenes/rol-32.png"
                                                               title="Horario"
                                                               actionListener="#{managerUsuario.cargarVista}"
                                                               oncomplete="#{managerUsuario.oncomplete}"
                                                               reRender="formHoraria,horPabellon,horAula"/>

                       </rich:column>
                       <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Edit" />
                                        </f:facet>

                                        <h:commandButton actionListener="#{usu.EditarFila}" value="" image="/Imagenes/actions/klipper_dock.png" title="Editar" rendered="#{usu.view}"/>
                                        <f:param id="id" value="#{usu.rol_id}" />


                                        <a4j:commandButton actionListener="#{usu.Editar}"  image="/Imagenes/actions/filesave.png" value=""  title="Guardar" rendered="#{usu.editable2}" reRender="formUsuario" oncomplete="#{usu.oncomplete}"/>
                                        <h:commandButton actionListener="#{usu.Cancelar}" image="/Imagenes/actions/fileclose.png"   value=""  title="Cancelar" rendered="#{usu.editable}"/>
                      </rich:column>
                    </rich:dataTable>
                    <rich:spacer height="20px"/>
                    <rich:datascroller id="barra" align="right"  for="tablaMaster" maxPages="8"  style=" width : 100%;"/>
                </h:form>
            </rich:panel>

            <rich:modalPanel  id="mp" autosized="true" zindex="2000"
                              width="500" height="100" >
                <f:facet name="header">
                    <h:outputText value="Registro de Usuarios" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('mp')" title="Cerrar"/>
                </f:facet>
                <h:form>
                    <table width="100%" style="font-size:10px; font-family:verdana;">
                        <tr >
                            <td align="right" colspan="4">
                                <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"
                                                     actionListener="#{managerUsuario.crearUsuario}"
                                                     oncomplete="#{managerUsuario.oncomplete}"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3"><hr size="1"></td>
                        </tr>
                        <tr>
                            <td width="35%" class="tdLabel">Usuario</td>
                            <td width="65%">
                                <h:inputText label="Usuario" id="usuario_u"  style=" width : 180px;" value="#{managerUsuario.usuario_u}"  maxlength="45"/>
                            </td>
                            <td align="right" width="20%">
                            </td>
                        </tr>
                        <tr>
                            <td width="35%" class="tdLabel">Password</td>
                            <td width="65%">
                                <h:inputText id="password_u" label="Password" value="#{managerUsuario.password_u}" style="width : 180px;"/>
                            </td>
                            <td width="20%"></td>
                        </tr>
                        <tr>
                            <td width="35%" class="tdLabel">Rol</td>
                            <td width="65%">
                                <h:selectOneMenu id="rol_u" value="#{managerUsuario.usu_rol_id}">
                                    <f:selectItems value="#{managerUsuario.usu_rol_descripcion}"/>
                                </h:selectOneMenu>
                            </td>
                            <td width="20%"></td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>
            
            <rich:modalPanel id="mpHoraria" minWidth="428" height="400"
                             resizeable="true" zindex="3000" style="overflow-y: auto">
                <f:facet name="header">
                    <h:outputText value="Permisos"/>
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
                            <td>
                            <rich:tree id="tree" style="width:100%"  value="#{managerUsuario.root}" var="item">
							<rich:treeNode>
								<h:outputText value="#{item.menDescripcion}" escape="false" />
							</rich:treeNode>
			   </rich:tree>    
                            </td>
                        </tr>
                        
                    </table>
                </h:form>
            </rich:modalPanel>
        </f:view>
    </body>
</html>
