<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Mantenimiento de Locales</title>
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
                            <td width="20%" colspan="2" ><strong>MANTENIMIENTO DE LOCALES</strong>
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
                                <h:commandButton type="button" id="buscar" value="" action="#{managerLocal.Seleccionar}" image="/Imagenes/actions/viewmag.png"  title="Buscar"/>


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
                            <td width="10%">Nombre:
                            </td>
                            <td width="30%">
                                <h:inputText value="#{managerLocal.b_des}" style="width : 180px;"/></td>
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
                                    width="100%" border="0" value="#{managerLocal.listaLocal}" var="local">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id" />
                            </f:facet>
                            <h:outputText value="#{local.b_id}"/>
                        </h:column>


                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Descripcion" />
                            </f:facet>

                            <h:outputText value="#{local.b_des}" rendered="#{local.view}"/>
                            <h:inputText value="#{local.b_des}" rendered="#{local.editable}"  maxlength="45"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Edit" />
                            </f:facet>
                            <p align="right">
                                <h:commandButton actionListener="#{local.EditarFila}"  value="" image="/Imagenes/actions/klipper_dock.png" title="Editar" rendered="#{local.view}"/>

                                <f:param id="id" value="#{local.b_id}" />
                                <f:param id="descripcion" value="#{local.b_des}" />

                                <a4j:commandButton actionListener="#{local.Editar}"   image="/Imagenes/actions/filesave.png" value=""  title="Guardar" rendered="#{local.editable2}" reRender="tablaMaster, barra" oncomplete="#{local.oncomplete}" style=" width : 16px; height : 16px;"/>
                                <h:commandButton   actionListener="#{local.Cancelar}" image="/Imagenes/actions/fileclose.png" value=""  title="Cancelar"  rendered="#{local.editable}"/>

                            </p>
                        </h:column>
                        <h:column >
                            <f:facet name="header" >
                                <h:outputText value="Delete" />
                            </f:facet>
                            <p align="right">
                                <h:commandButton  image="/Imagenes/actions/no.png"  title="Eliminar"  actionListener="#{managerLocal.EliminarFila}" action="#{managerLocal.Seleccionar}"  onclick="javascript:return (confirm('¿Esta realmente seguro de Eliminar?'));"/>

                            </p>
                            <f:param id="delete"  value="#{local.b_id}" />
                        </h:column>

                    </rich:dataTable>

                </h:form>


                <rich:modalPanel  id="mp" minHeight="370" minWidth="450" height="370" width="500" zindex="2000">

                    <f:facet name="header">

                        <h:outputText value="Registro de Local" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp')" title="Cerrar"/>
                    </f:facet>

                    <h:form>
                        <table width="100%" style="font-size:10px; font-family:verdana">
                            <tr >
                                <td align="right" colspan="4">
                                    <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"  actionListener="#{managerLocal.Enviar1}" action="#{managerLocal.Seleccionar}" reRender="tablaMaster, barra" oncomplete="#{managerLocal.oncomplete}"/>

                                </td>
                            </tr>
                            <tr>
                                <td colspan="3"><hr size="1">
                                </td>
                            </tr>
                            <tr>
                                <td width="30%" >Descripcion:*</td>
                                <td width="70%">
                                    <h:inputTextarea id="codigo"  style=" width : 180px;" value="#{managerLocal.b_des_i}"/>


                                </td>
                                <td align="right" width="20%">
                                </td>
                            </tr>

                        </table>
                    </h:form>
                </rich:modalPanel>
            </rich:panel>

        </f:view>
    </body>
</html>  

