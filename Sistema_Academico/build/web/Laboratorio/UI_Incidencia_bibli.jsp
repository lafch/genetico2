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
                            <td width="20%" colspan="2" ><strong>REGISTRO DE INCIDENCIAS</strong></td>
                            <td width="30%" align="right"> </td>
                            <td width="30%" align="right">

                            </td>

                            <td>

                            </td>
                            <td width="30%" align="right">
                                <h:graphicImage value="/Imagenes/actions/filenew.png" width="16"
                                                style="border-width: 0px;cursor: pointer;">
                                    <a4j:support event="onclick"
                                                 action="#{mBIncidenciaBibli.nuevo}"
                                                 oncomplete="#{mBIncidenciaBibli.oncomplete}"
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
                                                   action="#{mBIncidenciaBibli.buscar}"
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
                                <h:inputText size="40" value="#{mBIncidenciaBibli.w_descripcion}"/>
                            </td>
                            <td colspan="4"></td>
                        </tr>
                    </table>
                </h:form>

                <h:form>
                    <table  aling="center">
                        <tr>
                            <td>
                                <rich:dataTable value="#{mBIncidenciaBibli.listaIncidenciaLabs}" var="lista" rowKeyVar="row" 
                                                id="dtLista" rows="12" width="600">
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="Nro" /> </f:facet>
                                        <h:outputText value="#{row+1}" />
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="codigo" /> </f:facet>
                                        <h:outputText value="#{lista.tbIncidenciaLab.acAlumno.aluCodigo}" />
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="Alumno" /> </f:facet>
                                        <h:outputText value="#{lista.tbIncidenciaLab.acAlumno.aluAppaterno} #{lista.tbIncidenciaLab.acAlumno.aluApmaterno} #{lista.tbIncidenciaLab.acAlumno.aluNombres}" />
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="Libro" /> </f:facet>
                                        <h:outputText value="#{lista.tbIncidenciaLab.inclabEquipo}" />
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="Estado" /> </f:facet>
                                        <h:outputText value="#{lista.tbIncidenciaLab.inclabEstado eq '074001'? 'DEBE':'ENTREGADO'}" />
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="Registro" /> </f:facet>
                                        <h:outputText value="#{lista.tbIncidenciaLab.inclabFechaRegistro}">
                                            <f:convertDateTime pattern="dd / MM / yyyy"/>
                                        </h:outputText>                                    
                                    </rich:column>
                                    <%/*
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="" /> </f:facet>
                                        <h:graphicImage value="/Imagenes/actions/no.png" alt="Eliminar" title="Eliminar">
                                            <a4j:support event="onclick" action="#{mBIncidenciaBibli.abrirDelete}" oncomplete="#{mBIncidenciaBibli.oncomplete}">
                                                <f:setPropertyActionListener target="#{mBIncidenciaBibli.p_indice}" value="#{row}"/>
                                            </a4j:support>
                                        </h:graphicImage>
                                    </rich:column>
                                    */%>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="" /> </f:facet>
                                        <h:graphicImage value="/Imagenes/actions/editpaste.png" alt="Modificar" title="Modificar">
                                            <a4j:support event="onclick" action="#{mBIncidenciaBibli.seleccionar}" 
                                                         reRender="fModal" oncomplete="#{mBIncidenciaBibli.oncomplete}">
                                                <f:setPropertyActionListener target="#{mBIncidenciaBibli.p_indice_inci}" value="#{row}"/>
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
            <rich:modalPanel id="mpIncidencia" autosized="true" zindex="1000">
                <f:facet name="header">
                    <h:outputText value="Registro de Incidencia"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpIncidencia')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <a4j:form id="fModal">
                    <table aling="center" width="300px">
                        <tr>
                            <td colspan="2" align="right">
                               
                                <a4j:commandButton image="/Imagenes/actions/filesave.png" action="#{mBIncidenciaBibli.grabar}" 
                                                   reRender="fModal,dtLista" oncomplete="#{mBIncidenciaBibli.oncomplete}" />
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Alumno" /></td>
                            <td><h:inputText value="#{mBIncidenciaBibli.w_suggestionDato}" size="40" id="iAlumno"> 
                                    
                                </h:inputText>
                                <rich:suggestionbox id="suggestionAlumno"
                                                    for="iAlumno" nothingLabel="Alumno No Encontrado"
                                                    suggestionAction="#{mBIncidenciaBibli.listaSuggestion}" var="listaAlu" 
                                                    height="150" width="400" cellpadding="2"
                                                    cellspacing="2" shadowOpacity="1" shadowDepth="1"
                                                    minChars="5" zindex="3500"

                                                    >
                                                 
                                  

                                    <h:column>
                                        <h:outputText value="#{listaAlu.aluAppaterno} #{listaAlu.aluApmaterno} #{listaAlu.aluNombres}" style="font-weight: bold;" />
                                    </h:column>
                                     <h:column>
                                        <h:outputText value="#{listaAlu.aluCodigo}" style="font-weight: bold;" />
                                    </h:column>
                                    <a4j:support ajaxSingle="true" event="onselect"
                                                 actionListener="#{mBIncidenciaBibli.seleccionarAlumnoSugg}" 
                                                 reRender="iCodigo,iProfesion,iCurso, iCiclo,iTurno,iEquipo,iDescripcion,iEstado" />


                                      
                                </rich:suggestionbox>
                               <% /* <rich:message for="iCodigo">
                                    <f:facet name="errorMarker">
                                        <h:graphicImage url="/Imagenes/actions/error.png"/>
                                    </f:facet>
                                    <f:facet name="passedMarker">
                                        <h:outputText value=""/>
                                    </f:facet>    
                                </rich:message>*/ %>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Codigo" /></td>
                            <td><h:outputText value="#{mBIncidenciaBibli.objBeanIncidenciaLab.tbIncidenciaLab.acAlumno.aluCodigo}" id="iCodigo"> 
                                </h:outputText>
                                <% /*<rich:message for="iDescripcion">
                                    <f:facet name="errorMarker">
                                        <h:graphicImage url="/Imagenes/actions/error.png"/>
                                    </f:facet>
                                    <f:facet name="passedMarker">
                                        <h:outputText value=""/>
                                    </f:facet>    
                                </rich:message>*/ %>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Profesión" /></td>
                            <td>
                                <h:outputText value="#{mBIncidenciaBibli.objBeanIncidenciaLab.tbIncidenciaLab.acAlumno.esp.espNombre}" id="iProfesion" />
                            </td>
                        </tr>
                        <%/*<tr>
                            <td><h:outputText value="Curso" /></td>
                            <td>
                                <h:selectOneMenu value="#{mBIncidenciaBibli.objBeanIncidenciaLab.tbIncidenciaLab.curId}" id="iCurso">
                                    <f:selectItems value="#{mBIncidenciaBibli.cboCursos}" />
                                    <a4j:support event="onchange" action="#{mBIncidenciaBibli.seleccionarCurso}" reRender="iCiclo,iTurno"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>*/%>
                        <%/*
                        <tr>
                            <td><h:outputText value="Ciclo" /></td>
                            <td>
                                <h:outputText value="#{mBIncidenciaBibli.objBeanIncidenciaLab.winc_ciclo}" id="iCiclo" />
                            </td>
                        </tr>
                    * */%>
                        <tr>
                            <td><h:outputText value="Turno de Practicas" /></td>
                            <td>
                                <%/*<h:outputText value="#{mBIncidenciaBibli.objBeanIncidenciaLab.tbIncidenciaLab.acTurno.turNombre}" id="iTurno" />*/%>
                                <h:selectOneMenu value="#{mBIncidenciaBibli.objBeanIncidenciaLab.tbIncidenciaLab.acTurno.id}">
                                    <f:selectItems value="#{mBIncidenciaBibli.cboTurno}"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Estado" /></td>
                            <td>
                                <h:selectOneMenu value="#{mBIncidenciaBibli.objBeanIncidenciaLab.tbIncidenciaLab.inclabEstado}" id="iEstado">
                                    <f:selectItems value="#{mBIncidenciaBibli.cboEstadoLaboratorio}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Registro" /></td>
                            <td>
                                <rich:calendar value="#{mBIncidenciaBibli.objBeanIncidenciaLab.tbIncidenciaLab.inclabFechaRegistro}" datePattern="dd/MM/yyyy" />
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Libro" /></td>
                            <td>
                                <h:inputText value="#{mBIncidenciaBibli.objBeanIncidenciaLab.tbIncidenciaLab.inclabEquipo}" id="iEquipo" size="43" /> 
                            </td>
                        </tr>
                        <tr>
                            <td valing="top"><h:outputText value="Descripcion del Daño" /></td>
                            <td>
                                <h:inputTextarea cols="40" rows="4" id="iDescripcion" value="#{mBIncidenciaBibli.objBeanIncidenciaLab.tbIncidenciaLab.inclabDescripcionDanio}" />
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

