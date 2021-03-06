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
        <style>
            .fut td{
                font-family: sans-serif;
                font-size: 16px;

            }
        </style>
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
                                                   action="#{mBRevisarSoli.buscar}"
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
                            <td colspan="5">
                                <h:outputText value="Fec. Inicio" /><rich:spacer width="15"/>
                                <rich:calendar value="#{mBRevisarSoli.w_fecha}" datePattern="dd/MM/yyyy" id="iFecha"/>
                                <rich:spacer width="40"/>
                                <h:outputText value="Fec. Fin" /><rich:spacer width="15"/>
                                <rich:calendar value="#{mBRevisarSoli.w_fecha_fin}" datePattern="dd/MM/yyyy" id="iFechaFin"/>
                                <rich:spacer width="40"/>
                                <h:outputText value="Estado" /><rich:spacer width="15"/>
                                <h:selectOneMenu value="#{mBRevisarSoli.w_estado}" id="iEstadow">
                                    <f:selectItems value="#{mBRevisarSoli.cboEstadow}"/>
                                </h:selectOneMenu>
                            </td>
                            <td colspan="2"></td>
                        </tr>
                    </table>

                    <table aling="center">
                        <tr>
                            <td>
                                <rich:dataTable value="#{mBRevisarSoli.listaSolicitudPersona}" var="lista" rowKeyVar="row" 
                                                id="dtLista" rows="12" width="600">
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="Nro" /> </f:facet>
                                        <h:outputText value="#{row+1}" />
                                    </rich:column>                                    
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="Alumno" /> </f:facet>
                                        <h:outputText value="#{lista.bean_alumno}" />
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="Solicitu" /> </f:facet>
                                        <h:outputText value="#{lista.tsSolicitudPersona.tsSolicitud.solDescripcion}" />
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="Fec. Solicitada" /> </f:facet>
                                        <h:outputText value="#{lista.tsSolicitudPersona.solperFechaRegistro}">
                                            <f:convertDateTime pattern="dd / MM / yyyy"/>
                                        </h:outputText>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="Estado" /> </f:facet>
                                        <h:outputText value="#{lista.bean_estado}"/>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="" /> </f:facet>
                                        <h:graphicImage value="/Imagenes/actions/revisar.png" alt="Revisar" title="Revisar">
                                            <a4j:support event="onclick" action="#{mBRevisarSoli.seleccionar}" 
                                                         oncomplete="#{mBRevisarSoli.oncomplete}" reRender="fModal,iEstado">
                                                <f:setPropertyActionListener target="#{mBRevisarSoli.p_indice}" value="#{row}" />
                                            </a4j:support>
                                        </h:graphicImage>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="" /> </f:facet>
                                        <h:graphicImage value="/Imagenes/actions/revisarFut.png" alt="Visualizar Fut" title="Visualizar Fut">
                                            <a4j:support event="onclick" action="#{mBRevisarSoli.seleccionarFut}" 
                                                         oncomplete="#{mBRevisarSoli.oncomplete}" reRender="fDetalle,dtFile" >
                                                <f:setPropertyActionListener target="#{mBRevisarSoli.p_solper_id}" value="#{lista.tsSolicitudPersona.solperId}" />
                                                <f:setPropertyActionListener target="#{mBRevisarSoli.p_indice}" value="#{row}" />
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
            <rich:modalPanel id="mpModal" autosized="true" zindex="1000">
                <f:facet name="header">
                    <h:outputText value="Solicitud Estudiante"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpModal')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <a4j:form id="fModal">
                    <table aling="center" width="300px">
                        <tr>
                            <td colspan="2" align="right">
                                <a4j:commandButton image="/Imagenes/actions/filesave.png" action="#{mBRevisarSoli.grabar}" 
                                                   oncomplete="#{mBRevisarSoli.oncomplete}" reRender="dtLista,fModal" />

                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Estudiante" style="fon" /> </td>
                            <td><h:outputText value="#{mBRevisarSoli.objbBeanRevisarSoli.bean_alumno}"/> </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Solicitud"/> </td>
                            <td><h:outputText value="#{mBRevisarSoli.objbBeanRevisarSoli.tsSolicitudPersona.tsSolicitud.solDescripcion}"/> </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Fec. Solicitud"/> </td>
                            <td><h:outputText value="#{mBRevisarSoli.objbBeanRevisarSoli.tsSolicitudPersona.solperFechaRegistro}">
                                    <f:convertDateTime pattern="dd / MM / yyyy"/>
                                </h:outputText> </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Estado"/> </td>
                            <td>
                                <h:selectOneMenu value="#{mBRevisarSoli.objbBeanRevisarSoli.tsSolicitudPersona.solperAprovada}" id="iEstado">
                                    <f:selectItems value="#{mBRevisarSoli.cboEstado}" />
                                    <f:validateDoubleRange minimum="1"/>
                                </h:selectOneMenu>
                                <rich:message for="iEstado">
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
                            <td><h:outputText value="Observacion"/> </td>
                            <td>
                                <h:inputTextarea value="#{mBRevisarSoli.objbBeanRevisarSoli.tsSolicitudPersona.solperObservacion}" cols="30" rows="4" />
                            </td>
                        </tr>
                    </table>      
                </a4j:form>
            </rich:modalPanel>
            <rich:modalPanel id="mpDetalle" autosized="true" zindex="2000" onshow="showDiv();">
                <rich:effect name="hideDiv" for="contentDivA" type="Fade" />
                <rich:effect name="showDiv" for="contentDivA" type="Appear" />
                <div id="contentDivX">
                    <f:facet name="header">
                        <h:outputText value="Subir archivos" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png"
                                        style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mpDetalle')" title="Cerrar" />
                    </f:facet>
                    <h:form id="fDetalle">
                        <div
                            style="border: 1px solid; width: 701px; background-color: white;" class="fut">
                            <table width="700px" align="center" border="0" class="fut">
                                <tr>
                                    <td width="150px"><rich:spacer width="1" /></td>
                                    <td width="150px"><rich:spacer width="1" /></td>
                                    <td valign="top" width="150px" align="right"><h:outputText
                                            value="SOLICITO: " /></td>
                                    <td colspan="3" width="300px"><h:outputText  value="#{mBRevisarSoli.objFut.futSolicito}" style="font-weight: bold;" /></td>
                                </tr>
                                <tr>
                                    <td colspan="6"><h:outputText value="Sr. (Dr., Mg., Lic.)" />
                                        <rich:spacer width="20" /> <h:outputText value="#{mBRevisarSoli.objFut.futDirigido}" style="font-weight: bold;" /></td>
                                </tr>
                                <tr>
                                    <td colspan="6">
                                        <h:outputText value="Yo #{mBRevisarSoli.objbBeanRevisarSoli.bean_alumno}" style="font-weight: bold;"/> 
                                        <h:outputText value=" con N� Matr�cula "/> 
                                        <h:outputText value="#{mBRevisarSoli.objbBeanRevisarSoli.bean_codigo}" style="font-weight: bold;"/> 
                                        <h:outputText value=" alumno de la Carrera profesional de "/>
                                        <h:outputText value="#{mBRevisarSoli.objbBeanRevisarSoli.bean_especialidad}," style="font-weight: bold;" />
                                        <rich:spacer width="20" />
                                        <h:outputText value="Ciclo" />
                                        <rich:spacer width="10" />
                                        <h:outputText value="#{mBRevisarSoli.objFut.futCiclo}" style="font-weight: bold;" />,
                                        <rich:spacer width="20" />
                                        <h:outputText value="Turno" />
                                        <rich:spacer width="10" />
                                        <h:outputText value="#{mBRevisarSoli.objFut.futTurno}" style="font-weight: bold;" />,
                                        <rich:spacer width="20" />
                                        <h:outputText value="e-mail" />
                                        <rich:spacer width="10" />
                                        <h:outputText value="#{mBRevisarSoli.objFut.futEmail}" style="font-weight: bold;" />
                                    </td>

                                </tr>
                                <tr>
                                    <td colspan="6"><h:outputText
                                            value="con el debido respeto m e presento y expongo lo siguiente:" />
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="6"><h:outputText
                                            value="#{mBRevisarSoli.objFut.futExpongo}" style="font-weight: bold;" /></td>
                                </tr>
                                <tr>
                                    <td colspan="6"><h:outputText
                                            value="Le solicito se sirva indicar a quien corresponda atender mi solicitud, para la cual cumplo
                                            con adjuntar los docuemtos exigidos en el TUPA." /><br /></br/>
                                        <h:outputText value="POR TANTO:" /></td>
                                </tr>
                                <tr>
                                    <td><rich:spacer width="1" /></td>
                                    <td colspan="5"><h:outputText
                                            value="S�rvase acceder a mi pedido por ser de justicia" /></td>
                                </tr>
                                <tr>
                                    <td><rich:spacer width="1" /></td>
                                    <td><rich:spacer width="1" /></td>
                                    <td colspan="4"><h:outputText value="Los Olivos, " />
                                        <h:outputText value="#{mBRevisarSoli.objFut.futDia}" style="font-weight: bold;" />
                                        <%/*
                                             * <h:outputText value=" de " />
                                             * <h:outputText
                                             * value="#{mbSolicitud.wObjFut.fut_mes}"
                                             * /> <h:outputText value=" del " />
                                             * <h:outputText
                                             * value="#{mbSolicitud.wObjFut.fut_anio}" />
                                             */%>

                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="6"><rich:spacer height="60" /> 
                                        <h:outputText value="Archivos adjuntos" />
                                    </td>

                                </tr>
                                <tr>
                                    <td colspan="6">
                                        <rich:dataTable
                                            value="#{mBRevisarSoli.listaArchivos}" var="lista"
                                            rowKeyVar="row" id="dtFile">
                                            <rich:column>
                                                <h:outputText value="#{row + 1}" />
                                            </rich:column>
                                            <rich:column>
                                                <h:outputText value="#{lista.name}" />
                                            </rich:column>
                                            <rich:column>
                                                <f:param id="p_indice_file" value="#{row}"/>
                                                <h:commandButton image="/Imagenes/actions/descargar.png" actionListener="#{mBRevisarSoli.descargarArchivo}" />
                                                
                                            </rich:column>
                                        </rich:dataTable>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </h:form>
                </div>
            </rich:modalPanel>

        </body>
    </f:view>
</html>  

