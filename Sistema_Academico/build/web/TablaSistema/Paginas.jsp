<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Mantenimiento de Paginas</title>
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
            <rich:panel style="width : 100%; background:#FFF; border:0px !important;" >
                <h:form id="form1" style="width:100%">
                    <table width="100%" style="font-size:10px; font-family:verdana">
                        <tr >
                            <td width="20%" colspan="2"><strong>MANTENIMIENTO DE PAGINAS</strong>
                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right">
                                <h:graphicImage value="/Imagenes/new-32.png" style="border-width: 0px;cursor: pointer;">
                                    <a4j:support oncomplete="javascript:Richfaces.showModalPanel('mp',{width:450, top:130})" 
                                                 event="onclick" action="#{managerPagina.Nuevo}" 
                                                 reRender="descripcion_i,url_i" />
                                </h:graphicImage>
                            </td>
                            <td width="30%"></td>
             
                            <td align=right width="50%">
                                <a4j:commandButton type="button" id="buscar" title="Buscar"
                                                   action="#{managerPagina.Seleccionar}"
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
                            <td width="10%">Descripcion Pagina:
                            </td>
                            <td width="30%">
                                <h:inputText value="#{managerPagina.b_des}" style="width : 180px;"/></td>
                            <td width="50%"></td>
                        </tr>
                        <tr>
                            <td width="10%">Url Pagina:
                            </td>
                            <td width="30%">
                                <h:inputText value="#{managerPagina.b_des_pagina}" style="width : 180px;"/></td>
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
                                <h:form id="frmLstPaginas">
                                    <rich:datascroller id="barra" align="right"  for="tablaMaster"
                                                       maxPages="8"  style=" width : 70%;"/>
                                    <rich:spacer height="20px"/>
                                    <rich:dataTable id="tablaMaster" rows="10"
                                                    onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                    cellpadding="0" cellspacing="0" border="0"
                                                    width="70%" value="#{managerPagina.listaPagina}" var="pagina">
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Id" />
                                            </f:facet>
                                            <h:outputText value="#{pagina.b_id}"/>
                                        </rich:column>

                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Descripcion" />
                                            </f:facet>
                                            <h:outputText value="#{pagina.b_des}" rendered="#{pagina.view}" />
                                            <h:inputText value="#{pagina.b_des}" rendered="#{pagina.editable}" maxlength="45"/>
                                        </rich:column>

                                        <rich:column style="text-align: center;">
                                            <f:facet name="header">
                                                <h:outputText value="URL" />
                                            </f:facet>
                                            <h:outputText value="#{pagina.b_url}" rendered="#{pagina.view}"/>
                                            <h:inputText value="#{pagina.b_url}" rendered="#{pagina.editable}" onkeypress="return validar(event);"/>
                                        </rich:column>

                                        <rich:column style="text-align: center;">
                                            <f:facet name="header">
                                                <h:outputText value="Edit" />
                                            </f:facet>
                                            <h:commandButton actionListener="#{pagina.EditarFila}" value="" image="/Imagenes/actions/klipper_dock.png" title="Editar" rendered="#{pagina.view}"/>

                                            <f:param id="id" value="#{pagina.b_id}" />
                                            <f:param id="descripcion" value="#{pagina.b_des}" />
                                            <f:param id="url"   value="#{pagina.b_url}" />
                                            <a4j:commandButton actionListener="#{pagina.Editar}"  image="/Imagenes/actions/filesave.png" value=""  title="Guardar" rendered="#{pagina.editable2}" reRender="tablaMaster, barra" oncomplete="#{pagina.oncomplete}"/>
                                            <h:commandButton actionListener="#{pagina.Cancelar}"  value="" image="/Imagenes/actions/fileclose.png" title="Cancelar" rendered="#{pagina.editable}"/>
                                        </rich:column>
                                        
                                        <rich:column style="text-align: center;">
                                            <f:facet name="header" >
                                                <h:outputText value="Delete" />
                                            </f:facet>
                                            <h:commandButton  image="/Imagenes/actions/delete.gif"  title="Eliminar"
                                                              actionListener="#{managerPagina.EliminarFila}"
                                                              action="#{managerPagina.Seleccionar}"  
                                                              onclick="javascript:return (confirm('¿Esta realmente seguro de Eliminar?'));"/>
                                            <f:param id="delete"  value="#{pagina.b_id}" />
                                        </rich:column>
                                    </rich:dataTable>
                                </h:form>
                            </td>
                        </tr>
                    </table>
            </rich:panel>

            <rich:modalPanel  id="mp" minHeight="170" minWidth="650" height="200" width="500" zindex="2000">
                <f:facet name="header">
                    <h:outputText value="Registro de Paginas" />
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
                                                     actionListener="#{managerPagina.Enviar1}"
                                                     reRender="tablaMaster"
                                                     oncomplete="#{managerPagina.oncomplete}"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr size="1"></td>
                        </tr>
                        <tr>
                            <td class="tdLabel" width="22%">*Descripcion:</td>
                            <td>
                                <h:inputText label="Descripcion" id="descripcion_i"  style=" width : 180px;" value="#{managerPagina.b_des_i}"  maxlength="45"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel">*Pagina:</td>
                            <td>
                                <h:inputText id="url_i" label="Pagina" value="#{managerPagina.b_url}" style="width : 180px;" onkeypress="return validar(event);"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr size="1"></td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>

        </f:view>
    </body>
</html>
