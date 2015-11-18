<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %> 
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>

<html>
    <head>
        <meta http-equiv="Expires" content="0" >
        <meta http-equiv="Last-Modified" content="0">
        <meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Mantenimiento de Requisito</title>
        <script type="text/javascript">
            function getRightTop(ref) {
                var position = new Object();
                position.top = 0; //ref.offsetTop;
                position.left =0; // ref.offsetLeft+ref.clientWidth+6;
                return position;
            }
        </script>
    </head>
    <f:view >
        <body>

            <jsp:include page="../TablaSistema/Menu.jsp"/>
            <rich:panel>
                <h:form id="form1" style="width:100%" >
                    <table width="100%" style="font-size:10px; font-family:verdana"  cellspacing="0" border="0" cellpadding="0">
                        <tr>
                            <td width="20%" colspan="2" ><strong>MANTENIMIENTO DE SOLICITU</strong></td>
                            <td width="30%" align="right"> </td>
                            <td width="30%" align="right">

                            </td>

                            <td>

                            </td>
                            <td width="30%" align="right">
                                <h:graphicImage value="/Imagenes/actions/filenew.png" width="16"
                                                style="border-width: 0px;cursor: pointer;">
                                    <a4j:support event="onclick"
                                                 action="#{mBSolicitu.nuevo}"
                                                 oncomplete="#{mBSolicitu.oncomplete}"
                                                 reRender="fModal"
                                                 />
                                </h:graphicImage>
                            </td>
                            <td width="30%" align="right">
                                <h:graphicImage value="/Imagenes/actions/fileprint.png"
                                                style="cursor: pointer;"
                                                title="Imprimir Listado"/>
                            </td>
                            <td align=right width="50%">
                                <a4j:commandButton id="buscar" 
                                                   image="/Imagenes/actions/viewmag.png"
                                                   title="Buscar"
                                                   action="#{mBSolicitu.buscar}"
                                                   reRender="dtLista" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="7"><hr width="100%" size="1"></td>
                        </tr>
                        <tr>
                            <td width="20%" colspan="7"></td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <h:outputText value="Descripcion" /><rich:spacer width="15"/>
                                <h:inputText size="40" value="#{mBSolicitu.w_solicitud}"/>
                            </td>
                            <td colspan="4"></td>
                        </tr>
                    </table>
                </h:form>

                <h:form>
                    <table aling="center">
                        <tr>
                            <td>
                                <rich:dataTable value="#{mBSolicitu.listaSolicitu}" var="lista" rowKeyVar="row" 
                                                id="dtLista" rows="12" width="600">
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="Nro" /> </f:facet>
                                        <h:outputText value="#{row+1}" />
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="Descripcion" /> </f:facet>
                                        <h:outputText value="#{lista.solDescripcion}" />
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="Tipo" /> </f:facet>
                                        <h:outputText value="#{lista.solTipo == '048001'? 'ACADEMICO':'ADMINISTRATIVO'}" />
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="Area" /> </f:facet>
                                        <h:outputText value="#{lista.adAreaInstitucion.areinsDescripcion}" />
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="" /> </f:facet>
                                        <h:graphicImage value="/Imagenes/actions/no.png" alt="Eliminar" title="Eliminar">
                                            <a4j:support event="onclick" action="#{mBSolicitu.abrirDelete}" oncomplete="#{mBSolicitu.oncomplete}">
                                                <f:setPropertyActionListener target="#{mBSolicitu.p_indice}" value="#{row}"/>
                                            </a4j:support>
                                        </h:graphicImage>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="" /> </f:facet>
                                        <h:graphicImage value="/Imagenes/actions/editpaste.png" alt="Modificar" title="Modificar">
                                            <a4j:support event="onclick" action="#{mBSolicitu.seleccionar}" 
                                                         reRender="fModal" oncomplete="#{mBSolicitu.oncomplete}">
                                                <f:setPropertyActionListener target="#{mBSolicitu.p_indice}" value="#{row}"/>
                                            </a4j:support>
                                        </h:graphicImage>
                                    </rich:column>
                                    <f:facet name="footer">
                                        <rich:datascroller maxPages="10" reRender="dtLista"/>
                                    </f:facet>
                                </rich:dataTable>
                            </td>

                        </tr>
                    </table>
                </h:form>

            </rich:panel>
            <rich:modalPanel id="mpSolicitu" autosized="true" zindex="1000">
                <f:facet name="header">
                    <h:outputText value="Solicitu"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpSolicitu')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <a4j:form id="fModal">
                    <table aling="center" width="300px">
                        <tr>
                            <td colspan="2" align="right">
                               
                                <a4j:commandButton image="/Imagenes/actions/filesave.png" action="#{mBSolicitu.grabar}" 
                                                   reRender="fModal,dtLista" oncomplete="#{mBSolicitu.oncomplete}" />
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Codigo" /></td>
                            <td><h:inputText value="#{mBSolicitu.objSolicitud.solCodigo}" id="iCodigo" required="true"> 
                                    <f:validateLength minimum="3"/>
                                </h:inputText>
                                <rich:message for="iCodigo">
                                    <f:facet name="errorMarker">
                                        <h:graphicImage url="/Imagenes/actions/error.png"/>
                                    </f:facet>
                                    <f:facet name="passedMarker">
                                        <h:outputText value=""/>
                                    </f:facet>    
                                </rich:message>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Descripcion" /></td>
                            <td><h:inputText value="#{mBSolicitu.objSolicitud.solDescripcion}" id="iDescripcion" required="true"> 
                                    <f:validateLength minimum="3"/>
                                </h:inputText>
                                <rich:message for="iDescripcion">
                                    <f:facet name="errorMarker">
                                        <h:graphicImage url="/Imagenes/actions/error.png"/>
                                    </f:facet>
                                    <f:facet name="passedMarker">
                                        <h:outputText value=""/>
                                    </f:facet>    
                                </rich:message>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Responsable" /></td>
                            <td>
                                <h:selectOneMenu value="#{mBSolicitu.objSolicitud.tsResponsable.respId}" id="iResponsable" required="true" >
                                    <f:selectItems value="#{mBSolicitu.cboResponsable}"/>
                                    <f:validateDoubleRange minimum="1"/>
                                </h:selectOneMenu>
                                <rich:message for="iResponsable">
                                    <f:facet name="errorMarker">
                                        <h:graphicImage url="/Imagenes/actions/error.png"/>
                                    </f:facet>
                                    <f:facet name="passedMarker">
                                        <h:outputText value=""/>
                                    </f:facet>    
                                </rich:message>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Tipo" /></td>
                            <td>
                                <h:selectOneMenu value="#{mBSolicitu.objSolicitud.solTipo}" id="iTipo" required="true" >
                                    <f:selectItems value="#{mBSolicitu.cboTipo}"/>
                                    <f:validateDoubleRange minimum="1"/>
                                </h:selectOneMenu>
                                <rich:message for="iTipo">
                                    <f:facet name="errorMarker">
                                        <h:graphicImage url="/Imagenes/actions/error.png"/>
                                    </f:facet>
                                    <f:facet name="passedMarker">
                                        <h:outputText value=""/>
                                    </f:facet>    
                                </rich:message>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Area" /></td>
                            <td>
                                <h:selectOneMenu value="#{mBSolicitu.objSolicitud.adAreaInstitucion.areinsId}" id="iArea" required="true">
                                    <f:selectItems value="#{mBSolicitu.cboArea}"/>
                                    <f:validateDoubleRange minimum="1"/>
                                </h:selectOneMenu>
                                <rich:message for="iArea">
                                    <f:facet name="errorMarker">
                                        <h:graphicImage url="/Imagenes/actions/error.png"/>
                                    </f:facet>
                                    <f:facet name="passedMarker">
                                        <h:outputText value=""/>
                                    </f:facet>    
                                </rich:message>
                            </td>
                        </tr>
                       
                    </table>
                    <table>
                        <tr>
                            <td width="300px" valign="top">
                                <rich:dataTable value="#{mBSolicitu.listaRequisito}" var="listaR" id="dtListaR" rowKeyVar="row" >
                                    <a4j:support event="onRowDblClick" action="#{mBSolicitu.agregarItem}" reRender="dtListaRS,dtListaR">
                                        <f:setPropertyActionListener target="#{mBSolicitu.p_indice_requi}" value="#{row}"/>
                                    </a4j:support>
                                    <f:facet name="header"><h:outputText value="Requisitos" /> </f:facet>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="Descripcion" /> </f:facet>
                                        <h:outputText value="#{listaR.reqDescripcion}" />
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="" /> </f:facet>
                                        <h:graphicImage value="/Imagenes/actions/edit_add.png" >
                                            <a4j:support event="ondblclick" action="#{mBSolicitu.agregarItem}" reRender="dtListaRS,dtListaR">
                                                <f:setPropertyActionListener target="#{mBSolicitu.p_indice_requi}" value="#{row}"/>
                                            </a4j:support>
                                        </h:graphicImage>
                                    </rich:column>
                                </rich:dataTable>
                            </td>
                            <td>
                                
                            </td>
                            <td width="300px" valign="top">
                                <rich:dataTable value="#{mBSolicitu.listaRequisitoSolicitud}" var="listaRS" id="dtListaRS" rowKeyVar="row" >
                                    <a4j:support event="onRowDblClick" action="#{mBSolicitu.quitarItem}" reRender="dtListaR,dtListaRS">
                                        <f:setPropertyActionListener target="#{mBSolicitu.p_indice_req_sol}" value="#{row}"/>
                                    </a4j:support>
                                    <f:facet name="header"><h:outputText value="Req. Solicitu" /> </f:facet>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="Descripcion" /> </f:facet>
                                        <h:outputText value="#{listaRS.tsRequisito.reqDescripcion}" />
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="" /> </f:facet>
                                        <h:graphicImage value="/Imagenes/actions/no.png" >
                                            <a4j:support event="ondblclick" action="#{mBSolicitu.quitarItem}" reRender="dtListaR,dtListaRS">
                                                <f:setPropertyActionListener target="#{mBSolicitu.p_indice_req_sol}" value="#{row}"/>
                                            </a4j:support>
                                        </h:graphicImage>
                                    </rich:column>
                                </rich:dataTable>
                            </td>
                        </tr>
                    </table>        
                </a4j:form>
            </rich:modalPanel>
            <rich:modalPanel id="mpDelete" zindex="1000">
                <f:facet name="header">
                    <h:outputText value="Eliminar Registro"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpDelete')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <a4j:form>
                    <table align="center">
                        <tr>
                            <td><h:outputText value="Desea Eliminar el Registro?"/> </td>
                        </tr>
                        <tr>
                            <td align="center">
                                <a4j:commandButton value="No" oncomplete="Richfaces.hideModalPanel('mpDelete')" /><rich:spacer width="15" />
                                <a4j:commandButton value="Si" action="#{mBSolicitu.eliminar}"
                                                   oncomplete="#{mBSolicitu.oncomplete}" reRender="dtLista" />
                            </td>
                            
                        </tr>
                    </table>
                </a4j:form>
            </rich:modalPanel>

        </body>
    </f:view>
</html>  

