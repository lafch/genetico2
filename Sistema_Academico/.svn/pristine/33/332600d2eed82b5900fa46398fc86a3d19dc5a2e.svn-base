<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Mantenimiento de Conceptos de Pagos</title>
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
                            <td width="20%" colspan="2" ><strong>MANTENIMIENTO DE CONCEPTO DE PAGOS</strong>
                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right">
                                <h:graphicImage value="/Imagenes/actions/filenew.png" style="border-width: 0px;cursor: pointer;" width="16">
                                    <a4j:support event="onclick" oncomplete="javascript:Richfaces.showModalPanel('mp',{width:100, top:130})" 
                                                 action="#{managerConceptoPago.Nuevo}"
                                                 reRender="iId,iCodigo,iDescripcion,iMonto,iTipo,iCreacion" />
                                </h:graphicImage>
                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right"><h:graphicImage value="/Imagenes/actions/fileprint.png" title="Imprimir Listado"/>
                            </td>

                            <td align=right width="50%">
                                <h:commandButton type="button" id="buscar" value="" action="#{managerConceptoPago.Seleccionar}" image="/Imagenes/actions/viewmag.png" title="Buscar"/>
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
                            <td width="10%">Descripcion:
                            </td>
                            <td width="30%">
                                <h:inputText value="#{managerConceptoPago.b_descripcion}" style="width : 180px;"/></td>
                            <td width="50%"></td>
                        </tr>
                        <tr>
                            <td width="10%">Código:
                            </td>
                            <td width="30%">
                                <h:inputText value="#{managerConceptoPago.b_codigo}" style="width : 180px;"/></td>
                            <td width="50%"></td>
                        </tr>
                        <tr>
                            <td width="10%">Tipo:
                            </td>
                            <td width="30%">
                                <h:selectOneMenu id="iTipo" value="#{managerConceptoPago.b_tipo}">
                                    <f:selectItems value="#{managerConceptoPago.comboCatalogo_buscar}"/>
                                </h:selectOneMenu>
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
                                <rich:datascroller id="paginacion" align="right"  for="tablaMaster" maxPages="8"  style=" width : 100%;"/>
                            </td>
                        </tr>
                    </table>
                    <rich:dataTable id="tablaMaster" rows="10" onRowMouseOver="this.style.backgroundColor='#F1F1F1'" onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'" cellpadding="0" cellspacing="0" width="100%" border="0" value="#{managerConceptoPago.listaConceptoPago}" var="ConPago">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id" />
                            </f:facet>
                            <h:outputText value="#{ConPago.b_id}"/>
                        </h:column>


                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Codigo" />
                            </f:facet>
                            <h:outputText value="#{ConPago.b_codigo}" rendered="#{ConPago.view}"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Descripcion" />
                            </f:facet>
                            <h:outputText value="#{ConPago.b_descripcion}" rendered="#{ConPago.view}"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Monto" />
                            </f:facet>
                            <h:outputText value="#{ConPago.b_monto}" rendered="#{ConPago.view}"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Tipo" />
                            </f:facet>
                            <h:outputText value="#{ConPago.b_tipo}" rendered="#{ConPago.view}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Edit" />
                            </f:facet>
                            <p align="right">

                                <!-- aqui se ingresa el edit -->

                                <h:graphicImage value="/Imagenes/actions/editpaste.png" style="border-width: 0px;">
                                    <a4j:support event="onclick" oncomplete="javascript:Richfaces.showModalPanel('mp',{width:100, top:130})" actionListener="#{managerConceptoPago.EditarFila}"  reRender="iId,iCodigo,iDescripcion,iMonto,iTipo,iCreacion"/>
                                </h:graphicImage>
                                <f:param id="p_id" value="#{ConPago.b_id}" />
                                <f:param id="p_codigo"   value="#{ConPago.b_codigo}" />
                                <f:param id="p_descripcion"   value="#{ConPago.b_descripcion}" />
                                <f:param id="p_monto"   value="#{ConPago.b_monto}" />
                                <f:param id="p_tipo"   value="#{ConPago.b_id_tipo}" />
                                <f:param id="p_creacion"   value="#{ConPago.b_creacion}" />
                            </p>
                        </h:column>
                        <h:column >
                            <f:facet name="header" >
                                <h:outputText value="Delete" />
                            </f:facet>
                            <p align="right">
                                <h:commandButton  image="/Imagenes/actions/no.png"  title="Eliminar"  actionListener="#{managerConceptoPago.EliminarFila}" action="#{managerConceptoPago.Seleccionar}"  onclick="javascript:return (confirm('¿Esta realmente seguro de Eliminar?'));"/>
                            </p>
                            <f:param id="delete"  value="#{ConPago.b_id}" />
                        </h:column>
                    </rich:dataTable>

                </h:form>

                <rich:modalPanel id="mp" height="250" width="400" minWidth="400" zindex="2000">
                    <f:facet name="header">
                        <h:outputText value="Registro de conceptos de pago" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer;"
                                        onclick="Richfaces.hideModalPanel('mp')" title="Cerrar"/>
                    </f:facet>

                    <h:form>
                        <table width="100%" style="font-size:10px; font-family:verdana">
                            <tr >
                                <td align="right" colspan="4">
                                    <a4j:commandButton   image="/Imagenes/actions/filesave.png"
                                                         actionListener="#{managerConceptoPago.Insertar}"
                                                         action="#{managerConceptoPago.Seleccionar}"
                                                         reRender="tablaMaster" title="Guardar"
                                                         oncomplete="javascript:alert('Se guardo el registro correctamente');Richfaces.hideModalPanel('mp')"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3"><hr size="1">
                                </td>
                            </tr>
                            <tr>
                                <td width="30%" >Codigo:</td>
                                <td width="70%">
                                    <h:inputHidden id="iId" value="#{managerConceptoPago.b_id_u}"/>

                                    <h:inputText label="Codigo" id="iCodigo"  style=" width : 180px;" value="#{managerConceptoPago.b_codigo_u}" required="true"  maxlength="10"/>
                                    <rich:message for="iCodigo">
                                        <f:facet name="passedMarker">
                                            <h:graphicImage  value="/Imagenes/actions/ok.png" />
                                        </f:facet>
                                        <f:facet name="errorMarker">
                                            <h:graphicImage value="/Imagenes/actions/no.png" />
                                        </f:facet>
                                    </rich:message>
                                </td>
                                <td align="right" width="20%">
                                </td>
                            </tr>
                            <tr>
                                <td width="30%">Descripcion:</td>
                                <td width="70%">
                                    <h:inputTextarea id="iDescripcion" label="Descripcion" value="#{managerConceptoPago.b_descripcion_u}" style="width : 180px;"  required="true"/>

                                    <rich:message for="iDescripcion">
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
                                <td width="30%">Monto:</td>
                                <td width="70%">
                                    <h:inputText id="iMonto" label="Monto" value="#{managerConceptoPago.b_monto_u}" style="width : 180px;"  required="true"/>
                                    <rich:message for="iMonto">
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
                                <td width="30%">Tipo:</td>
                                <td width="70%">
                                    <h:selectOneMenu id="iTipo" value="#{managerConceptoPago.b_id_tipo_u}">
                                        <f:selectItems value="#{managerConceptoPago.comboCatalogo}"/>
                                    </h:selectOneMenu>
                                </td>
                                <td width="20%"><h:inputHidden id="iCreacion" value="#{managerConceptoPago.b_creacion_u}"/></td>
                            </tr>
                            <tr>
                                <td colspan="4"><hr size="1">
                                </td>
                            </tr>
                        </table>
                    </h:form>
                </rich:modalPanel>
            </rich:panel>
        </f:view>
    </body>
</html>  

