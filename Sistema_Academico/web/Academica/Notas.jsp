<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Seguimiento de notas</title>
        <script type="text/javascript">
            window.onload = function() {
                document.getElementById('form1:bTipPro2').selectedIndex = 0;
            }
        </script>
    </head>
    <body>
        <f:view>
            <jsp:include page="../TablaSistema/Menu.jsp"></jsp:include>
            <rich:panel style=" width : 100%;" >
                <h:form id="form1" style="width:100%">
                    <table width="100%" style="font-size:10px; font-family:verdana">
                        <tr>
                            <td width="20%" colspan="2" ><strong>LISTADO DE NOTAS</strong>
                            </td>
                            <td width="30%"></td>
                            <td align=right width="10%">
                                <table>
                                    <tr>
                                        <td>
                                        </td>
                                        <td>
                                            <h:commandButton type="button" id="buscar" value="" action="#{managerNotas.cargarArbol}" image="/Imagenes/actions/reload.png"  title="Refrescar"/>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="6"><hr width="100%"/></td>
                        </tr>
                        <tr>
                            <td style="vertical-align:top;" >
                                <rich:tree value="#{managerNotas.arbol}" ajaxSubmitSelection="true"  switchType="ajax"
                                           nodeSelectListener="#{managerNotas.Seleccion}" var="item" reRender="o_curso,tablaMaster,curNombre"/>
                            </td>
                            <td  style="vertical-align:top;" colspan="5">
                                <table width="100%"  cellspacing="0" border="0" cellpadding="0">
                                    <tr style="height: 25px;">
                                        <td align="right"><h:outputText value="#{managerNotas.cur_ape_nombre}" id="o_curso"/></td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <rich:dataTable id="tablaMaster" rows="20" onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                            onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                            cellpadding="0" cellspacing="0" width="100%" border="0" value="#{managerNotas.listaNotas}" var="notas1">

                                                <rich:column sortable="true" sortExpression="#{notas1.codigo_sec}" >
                                                    <f:facet name="header">
                                                        <h:outputText value="Codigo de Seccion"/>
                                                    </f:facet>
                                                    <h:outputText value="#{notas1.codigo_sec}"/>
                                                </rich:column>
                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Nombre de Seccion"/>
                                                    </f:facet>
                                                    <h:outputText value="#{notas1.nombre_sec}"/>
                                                </rich:column>
                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Matriculados"/>
                                                    </f:facet>
                                                    <h:outputText value="#{notas1.vacantes_sec}"/>
                                                </rich:column>
                                                <rich:column style="text-align: center;">
                                                    <f:facet name="header">
                                                        <h:outputText value="Print"/>
                                                    </f:facet>
                                                    <h:graphicImage value="/Imagenes/actions/fileprint.png" 
                                                                    style="border-width: 0px;width:16px;height:16px;cursor:pointer;" title="Imprimir Todos los cursos">
                                                        <a4j:support event="onclick" reRender="id_nombre_sec,pdfView" actionListener="#{managerNotas.PrepararPdf}"  />
                                                    </h:graphicImage>
                                                    <f:param id="pdf" value="#{notas1.codigo_sec}"/>
                                                </rich:column>
                                                <rich:column style="text-align: center;">
                                                    <f:facet name="header">
                                                        <h:outputText value="Ingresar Notas"/>
                                                    </f:facet>
                                                    <f:param value="#{notas1.id_sec}" id="id_seccion"/>
                                                    <f:param value="#{notas1.nombre_sec}" id="id_nombre_sec"/>
                                                    <f:param value="#{notas1.curape_id}" id="id_cur_ape"/>
                                                    <a4j:commandButton image="/Imagenes/actions/notas.png"
                                                                       actionListener="#{managerNotas.AsignarNotasOpc}"
                                                                       oncomplete="Richfaces.showModalPanel('mp',{width:800, top:40});"
                                                                       reRender="id_sec,id_curape,cur_nombre,bTipPro2,dtAlumno,formNotas"/>
                                                </rich:column>
                                                <%--
                                                            <rich:column>
                                                            <f:facet name="header">
                                                            <h:outputText value="Promediar"/>
                                                            </f:facet>
                                                            <h:graphicImage value="/Imagenes/actions/calc.png"  style="border-width: 0px;width:18px;height:18px" title="Calcular Promedios">
                                                            <a4j:support event="onclick" actionListener="#{managerNotas.GenerarPromedios}" oncomplete="javascript:alert('Se generaron los promedios correctamente');" />
                                                            </h:graphicImage>


                                                            </rich:column>
                                                --%>
                                                <rich:column style="text-align: center;">
                                                    <f:facet name="header">
                                                        <h:outputText value="Acta Princ."/>
                                                    </f:facet>
                                                    <h:graphicImage value="/Imagenes/actions/calc.gif" 
                                                                    style="border-width: 0px;width:16px;height:16px;cursor:pointer;"
                                                                    title="Acta Princ.">
                                                        <a4j:support event="onclick" actionListener="#{managerNotas.AsignarNotasActas}" oncomplete="Richfaces.showModalPanel('mp2',{width:900, top:40});" reRender="id_sec1,id_curape1,cur_nombre1,PanelActa"/>
                                                    </h:graphicImage>
                                                </rich:column>

                                                <rich:column style="text-align: center;">
                                                    <f:facet name="header">
                                                        <h:outputText value="Acta Sust."/>
                                                    </f:facet>
                                                    <h:graphicImage value="/Imagenes/actions/academico.png" 
                                                                    style="border-width: 0px;width:16px;height:16px;cursor:pointer;"
                                                                    title="Act.Susti">
                                                        <a4j:support event="onclick" actionListener="#{managerNotas.LlenarActa}" oncomplete="Richfaces.showModalPanel('mp3',{width:650, top:40});" reRender="id_sec0,curso_nombre,nombre_acta_final,codigo_acta_final,numero_acta_final,tablaActaFinal,barra1"/>
                                                    </h:graphicImage>
                                                </rich:column>
                                                <rich:column style="text-align: center;">
                                                    <f:facet name="header">
                                                        <h:outputText value="bloqueo web"/>
                                                    </f:facet>
                                                    <a4j:commandButton actionListener="#{managerNotas.SeleccionarSec}" image="/Imagenes/actions/bloqueo.png"
                                                                       reRender="dtimporte,w_sec_idbak" onclick="Richfaces.showModalPanel('mpbloquea')"/>

                                                </rich:column>
                                                <rich:column style="text-align: center;">
                                                    <f:facet name="header">
                                                        <h:outputText value="Imp. Nota"/>
                                                    </f:facet>
                                                    <a4j:commandButton actionListener="#{managerNotas.seleccionarImporte}" image="/Imagenes/actions/importar18.png"
                                                                       reRender="radio,w_sec_idbak2" onclick="Richfaces.showModalPanel('mpimporta')"/>

                                                </rich:column>
                                            </rich:dataTable>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <% /*    <a4j:mediaOutput id="pdfView" codetype="application/pdf" type="application/pdf" element="object"   mimeType="application/pdf" cacheable="false" rendered="true" standby="cargando..."  createContent="#{managerNotas.crearPdf}"  value="#{managerNotas.nombre_sec1}" /> */%>
                        </tr>
                    </table>

                </h:form>

                <rich:modalPanel  id="mp" minHeight="480" minWidth="750" height="480" width="750" zindex="2000">

                    <f:facet name="header">
                        <h:outputText value="Registro de Notas" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp')" title="Cerrar"/>
                    </f:facet>
                    <h:form id="formNotas">
                        <table>
                            <tr>
                                <td colspan="2">
                                    <h:outputText value="ETAPA DE EVALUACION:" style="font-size: 15px; font-weight: bold;" />
                                    <rich:spacer width="10"/>
                                    <h:selectOneMenu id="bTipPro2" value="#{managerNotas.etapaSisEva}" style="background-color: #F9F9F9; text-decoration: none; font-weight: bold;" >
                                        <a4j:support event="onchange" actionListener="#{managerNotas.listarColumnas}" reRender="dtAlumno"/>
                                        <f:selectItems value="#{managerNotas.etapas}"/>
                                    </h:selectOneMenu>
                                </td>
                            </tr>
                        </table>
                        <table width="100%" >
                            <tr>
                                <td align="right" colspan="4">
                                    <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"  actionListener="#{managerNotas.grabarNotas}" oncomplete="javascript:alert('Se guardo el registro correctamente');Richfaces.hideModalPanel('mp')"/>
                                </td>
                            </tr>
                            <tr><td colspan="3"><hr width="100%" /></td></tr>
                            <tr>
                                <td colspan="4" align="right">
                                    <h:inputHidden value="#{managerNotas.id_cur_ape}" id="id_curape"/>
                                    <h:inputHidden value="#{managerNotas.id_sec_horario}" id="id_sec"/>
                                    <h:outputText value="#{managerNotas.cur_nombre}" id="cur_nombre"/></td>
                            </tr>
                            <tr>
                                <td colspan="4">
                                    <div style="height:390px; overflow: auto;">
                                        <%-- <h:panelGrid id="PanelNotas" binding="#{managerNotas.htmlPanel}" columns="#{managerNotas.nro_columnas}" border="0" cellpadding="0" cellspacing="0" width="100%"/>--%>
                                        <rich:dataTable id="dtAlumno"
                                                        value="#{managerNotas.lstAlumnosNotas}"
                                                        onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                        onRowMouseOut="this.style.backgroundColor='#F9F9F9'"
                                                        cellpadding="0" cellspacing="0"
                                                        width="100%"
                                                        var="row"
                                                        style="background-color: #F9F9F9;" styleClass="texto" >
                                            <rich:columns value="#{managerNotas.lstColumnsNotasAux}"
                                                          var="column"
                                                          index="index"
                                                          begin="1"
                                                          sortOrder="#{column.ordering}"  >
                                                <f:facet name="header">
                                                    <h:outputText value="#{column.name}"/>
                                                </f:facet>
                                                <h:outputText value="#{row[index]}"  rendered="#{index<3}"  />
                                                <h:inputText value="#{row[index] }" rendered="#{index > 2 }" style="width:30px;margin:auto;display:block;" disabled="#{column.name=='PF' or column.name=='PPI' or column.name=='PPII' or column.name=='PPIII' or (column.name=='SUSTI' and managerNotas.etapaSisEva=='092001')  }" />
                                            </rich:columns>
                                        </rich:dataTable>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </h:form>

                </rich:modalPanel>


                <rich:modalPanel  id="mp2" minHeight="480" minWidth="750" height="480" width="750" zindex="2000">

                    <f:facet name="header">

                        <h:outputText value="Registro de Acta" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp2')" title="Cerrar"/>
                    </f:facet>
                    <h:form id="form">
                        <table width="100%"  cellspacing="0" border="0" cellpadding="0">
                            <tr>
                                <td align="right" colspan="4">
                                    <%  /*<a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"  actionListener="#{managerNotas.InsertarActa}" oncomplete="javascript:alert('Se guardo el registro correctamente');Richfaces.hideModalPanel('mp2')"/>*/%>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4" align="left">
                                    <h:inputHidden value="#{managerNotas.id_cur_ape}" id="id_curape1"/>
                                    <h:inputHidden value="#{managerNotas.id_sec_horario}" id="id_sec1"/>
                                    <h:outputText value="#{managerNotas.cur_nombre}" id="cur_nombre1"/></td>
                            </tr>
                            <tr>
                                <td>Nombre Acta
                                </td>
                                <td><h:inputText value="#{managerNotas.nombre_acta}" />
                                </td>
                            </tr>
                            <tr>
                                <td>Codigo Acta
                                </td>
                                <td><h:inputText value="#{managerNotas.codigo_acta}" />
                                </td>
                            </tr>
                            <tr>
                                <td>Numero de Acta
                                </td>
                                <td><h:inputText value="#{managerNotas.numero_acta}" />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">
                                    <div style="height:390px; overflow: auto;">
                                        <h:panelGrid id="PanelActa" binding="#{managerNotas.htmlPanelActa}" columns="#{managerNotas.nro_columnas}" border="0" cellpadding="0" cellspacing="0" width="100%"/>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </h:form>

                </rich:modalPanel>



                <rich:modalPanel  id="mp3" minHeight="500" minWidth="650" height="500" width="650" zindex="2000">

                    <f:facet name="header">

                        <h:outputText value="Listado de Acta" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp3')" title="Cerrar"/>
                    </f:facet>
                    <h:form id="form4">
                        <table width="100%"  cellspacing="0" border="0" cellpadding="0">
                            <tr>
                                <td align="right">
                                    <%/*<a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"  actionListener="#{managerNotas.InsertarActa}" oncomplete="javascript:alert('Se guardo el registro correctamente');Richfaces.hideModalPanel('mp')"/>*/%>

                                </td>
                                <td>
                                </td>
                                <td>
                                </td>
                                <td align="right"><a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"  actionListener="#{managerNotas.InsertarSustitutorios}" oncomplete="javascript:alert('Se guardo el registro correctamente');Richfaces.hideModalPanel('mp3')"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="left">
                                    <h:inputHidden value="#{managerNotas.id_sec_horario}" id="id_sec0"/>
                                    <h:outputText value="#{managerNotas.curso_nombre}" id="curso_nombre"/></td>
                            </tr>
                            <tr>
                                <td>Nombre Acta
                                </td>
                                <td align="left"><h:outputText value="#{managerNotas.nombre_acta_final}" id="nombre_acta_final"/>
                                </td>
                            </tr>
                            <tr>
                                <td>Codigo Acta
                                </td>
                                <td align="left"><h:outputText value="#{managerNotas.codigo_acta_final}" id="codigo_acta_final"/>
                                </td>
                            </tr>
                            <tr>
                                <td>Numero de Acta
                                </td>
                                <td align="left"><h:outputText value="#{managerNotas.numero_acta_final}" id="numero_acta_final"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">
                                    <rich:datascroller boundaryControls="hide" id="barra1" align="right"  for="tablaActaFinal" maxPages="8"  style=" width : 100%;"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">

                                    <%/*<rich:scrollableDataTable  frozenColCount="1" hideWhenScrolling="true"  ignoreDupResponses="true" rows="18" id="tablaActaFinal" sortMode="single" width="600px" height="400px" value="#{managerNotas.listaActas}" var="acta" >    */%>
                                    <rich:dataTable id="tablaActaFinal" rows="10" onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                    cellpadding="0" cellspacing="0" width="100%" border="0" value="#{managerNotas.listaActas}" var="acta">

                                        <rich:column  sortExpression="#{acta.alumno_codigo_aFinal}" width="60px">
                                            <f:facet name="header">
                                                <h:outputText value="Cod."/>
                                            </f:facet>
                                            <h:outputText value="#{acta.alumno_codigo_aFinal}"/>
                                        </rich:column>
                                        <rich:column sortExpression="#{acta.alumno_nombre_aFinal}" width="300px">
                                            <f:facet name="header">
                                                <h:outputText value="Alumno"/>
                                            </f:facet>
                                            <h:outputText value="#{acta.alumno_nombre_aFinal}"/>
                                        </rich:column>

                                        <rich:column sortExpression="#{acta.prom_actividades1}" width="30px">
                                            <f:facet name="header">
                                                <h:outputText value="PAct1."/>
                                            </f:facet>
                                            <h:outputText value="#{acta.prom_actividades1}"/>
                                        </rich:column>

                                        <rich:column sortExpression="#{acta.parciales}" width="30px">
                                            <f:facet name="header">
                                                <h:outputText value="Parc."/>
                                            </f:facet>
                                            <h:outputText value="#{acta.parciales}"/>
                                        </rich:column>

                                        <rich:column sortExpression="#{acta.prom_actividades2}" width="30px">
                                            <f:facet name="header">
                                                <h:outputText value="PAct2."/>
                                            </f:facet>
                                            <h:outputText value="#{acta.prom_actividades2}"/>
                                        </rich:column>

                                        <rich:column sortExpression="#{acta.finales}" width="30px">
                                            <f:facet name="header">
                                                <h:outputText value="Fin."/>
                                            </f:facet>
                                            <h:outputText value="#{acta.finales}"/>
                                        </rich:column>

                                        <rich:column sortExpression="#{acta.final_finales}" width="30px">
                                            <f:facet name="header">
                                                <h:outputText value="PFinal"/>
                                            </f:facet>
                                            <h:outputText value="#{acta.final_finales}"/>
                                        </rich:column>

                                        <h:column>		
                                            <f:facet name="header"><h:outputText value="Edit" /></f:facet>
                                                <p align="right">
                                                <h:outputText value="#{acta.sustitutorio}" rendered="#{acta.view}"/>
                                                <h:inputText value="#{acta.sustitutorio}" rendered="#{acta.editable}" style="width:30px" />
                                                <a4j:commandButton actionListener="#{acta.EditarFila}"  value="" image="/Imagenes/actions/klipper_dock.png" title="Editar" rendered="#{acta.view}" reRender="tablaActaFinal, barra1"/>
                                                <a4j:commandButton actionListener="#{acta.Editar}"  image="/Imagenes/actions/filesave.png" value=""  title="Guardar" rendered="#{acta.editable}" reRender="tablaActaFinal, barra1" oncomplete="#{acta.oncomplete}"/>
                                                <a4j:commandButton actionListener="#{acta.Cancelar}"  value="" image="/Imagenes/actions/fileclose.png" title="Cancelar" rendered="#{acta.editable}" reRender="tablaActaFinal, barra1"/>
                                            </p>
                                        </h:column>
                                    </rich:dataTable>

                                </td>
                            </tr>
                        </table>
                    </h:form>

                </rich:modalPanel>


            </rich:panel>

            <rich:modalPanel  id="mpbloquea" autosized="true" zindex="2000"  onshow="showDiv();">
                <rich:effect  name="hideDiv"  for="contentDiv" type="Fade" />
                <rich:effect  name="showDiv"  for="contentDiv" type="Appear" />
                <f:facet name="header">
                    <h:outputText value="Bloquear Nota" />
                </f:facet>
                <f:facet name="controls">

                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpbloquea')" title="Cerrar"/>
                </f:facet>

                <h:form id="formblo">
                    <table>
                        <tr>
                            <td align="right">
                                <a4j:commandButton image="/Imagenes/actions/filesave.png" actionListener="#{managerNotas.bloquearNotas}"
                                                   oncomplete="#{managerNotas.oncomplete}" />
                            </td>
                        </tr>
                        <tr>
                            <td>

                                <h:inputHidden id="w_sec_idbak" value="#{managerNotas.w_sec_id}"/>

                                <rich:dataTable value="#{managerNotas.w_sistema_evaluacion}" var="importe" id="dtimporte">
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Codigo"/>
                                        </f:facet>
                                        <h:outputText value="#{importe.w_siseva_per_codigo}"/>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Descripcion"/>
                                        </f:facet>
                                        <h:outputText value="#{importe.w_siseva_per_descripcion}"/>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Marcar"/>
                                        </f:facet>
                                        <h:selectBooleanCheckbox value="#{importe.w_marcar}"/>

                                        <f:param id="w_curape_id" value="#{importe.w_curape_id}"/>
                                        <f:param id="w_siseva_per_id" value="#{importe.w_siseva_per_id}"/>
                                    </rich:column>


                                </rich:dataTable>
                            </td>
                        </tr>

                    </table>
                </h:form>
            </rich:modalPanel>

            <rich:modalPanel  id="mpimporta" autosized="true" zindex="2000"  onshow="showDiv();">
                <rich:effect  name="hideDiv"  for="contentDiv" type="Fade" />
                <rich:effect  name="showDiv"  for="contentDiv" type="Appear" />
                <f:facet name="header">
                    <h:outputText value="Importar Notas" />
                </f:facet>
                <f:facet name="controls">

                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpimporta')" title="Cerrar"/>
                </f:facet>

                <h:form id="formimportar">
                    <table align="center" width="100%">
                        <tr>
                            <td align="right">
                                <a4j:commandButton title="Importar Notas" image="/Imagenes/actions/filesave.png" actionListener="#{managerNotas.guardarImporte}"
                                                   oncomplete="#{managerNotas.oncomplete}" />
                                <rich:separator height="1"/>
                            </td>
                        </tr>
                        <tr>
                            <td>

                                <h:inputHidden id="w_sec_idbak2" value="#{managerNotas.w_sec_id}"/>

                                <% /*<rich:dataTable value="#{managerNotas.w_sistema_evaluacion}" var="importe" id="dtimporteNota">
                                     <rich:column>
                                     <f:facet name="header">
                                     <h:outputText value="Codigo"/>
                                     </f:facet>
                                     <h:outputText value="#{importe.w_siseva_per_codigo}"/>
                                     </rich:column>
                                     <rich:column>
                                     <f:facet name="header">
                                     <h:outputText value="Descripcion"/>
                                     </f:facet>
                                     <h:outputText value="#{importe.w_siseva_per_descripcion}"/>
                                     <h:outputText value="#{importe.w_mensaje}"/>

                                     </rich:column>
                                     <rich:column>
                                     <f:facet name="header">
                                     <h:outputText value="Marcar"/>
                                     </f:facet>
                                     <h:selectBooleanCheckbox value="#{importe.w_marcar}"/>

                                     <f:param id="w_curape_id2" value="#{importe.w_curape_id}"/>
                                     <f:param id="w_siseva_per_id2" value="#{importe.w_siseva_per_id}"/>
                                     </rich:column>
                                     <rich:column>
                                     <f:facet name="header">
                                     <h:outputText value="Marcar"/>
                                     </f:facet>
                                     </rich:column>
                                     </rich:dataTable> */%>
                                <h:selectOneRadio id="radio" layout="pageDirection" value="#{managerNotas.w_siseva_per_id}">
                                    <f:selectItems value="#{managerNotas.cbo_radio}" />
                                </h:selectOneRadio>
                                <br />
                                <rich:separator height="1"/>
                                <div style="text-align: right" align="right">
                                    vista previa
                                    <a4j:commandButton title="Vista Previa" image="/Imagenes/actions/viewmag.png" actionListener="#{managerNotas.vistaPrevia}" reRender="aluNota, aluNota2"
                                                       oncomplete="#{managerNotas.oncomplete}"/>
                                </div>
                            </td>
                        </tr>

                    </table>
                </h:form>
            </rich:modalPanel>


            <rich:modalPanel  id="mpvista"  zindex="2000"  onshow="showDiv();" width="900" height="600">
                <rich:effect  name="hideDiv"  for="contentDiv" type="Fade" />
                <rich:effect  name="showDiv"  for="contentDiv" type="Appear" />
                <f:facet name="header">
                    <h:outputText value="Vista Previa" />
                </f:facet>
                <f:facet name="controls">

                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpvista')" title="Cerrar"/>
                </f:facet>

                <h:form id="formvista">
                    <table width="95%" align="center">
                        <tr>
                            <td width="50%" align="left">
                                <div style="text-align: center; width: 100%; font-size: 14px; font-weight: bold">
                                    <h:outputText value="Nota Principal"/>
                                </div>
                                <div style="height: 500px; width: 400px; overflow-x: hidden; overflow-y: auto">
                                    <rich:dataTable   value="#{managerNotas.listarAlumnoNota}" var="listaAluNota" id="aluNota" width="390px">
                                        <rich:column style="height:10px; font-size:9px">
                                            <f:facet name="header"><h:outputText value="Nro"/></f:facet>
                                            <h:outputText value="#{listaAluNota.alu_conta}"/>
                                        </rich:column>
                                        <rich:column style="height:10px; font-size:9px">
                                            <f:facet name="header"><h:outputText value="Codigo"/></f:facet>
                                            <h:outputText value="#{listaAluNota.alu_codigo}"/>
                                        </rich:column>
                                        <rich:column style="height:10px; font-size:9px">
                                            <f:facet name="header"><h:outputText value="Apellidos y nombres"/></f:facet>
                                            <h:outputText value="#{listaAluNota.alu_datos}"/>
                                        </rich:column>
                                        <rich:column style="height:10px; font-size:9px">
                                            <f:facet name="header"><h:outputText value="Nota"/></f:facet>
                                            <h:outputText value="#{listaAluNota.alu_nota}"/>
                                        </rich:column>
                                    </rich:dataTable>
                                </div>
                            </td>
                            <td width="50%" align="right">
                                <div style="text-align: center; width: 100%; font-size: 14px; font-weight: bold">
                                    <h:outputText value="Nota Temporal"/>
                                </div>
                                <div style="height: 500px; width: 400px; overflow-x: hidden; overflow-y: auto">
                                    <rich:dataTable   value="#{managerNotas.listarAlumnoNota}" var="listaAluNota2" id="aluNota2" width="390px">
                                        <rich:column style="height:10px; font-size:9px">
                                            <f:facet name="header"><h:outputText value="Nro"/></f:facet>
                                            <h:outputText value="#{listaAluNota2.alu_conta}"/>
                                        </rich:column>
                                        <rich:column style="height:10px; font-size:9px">
                                            <f:facet name="header"><h:outputText value="Codigo"/></f:facet>
                                            <h:outputText value="#{listaAluNota2.alu_codigo}"/>
                                        </rich:column>
                                        <rich:column style="height:10px; font-size:9px">
                                            <f:facet name="header"><h:outputText value="Apellidos y nombres"/></f:facet>
                                            <h:outputText value="#{listaAluNota2.alu_datos}"/>
                                        </rich:column>
                                        <rich:column style="height:10px; font-size:9px">
                                            <f:facet name="header"><h:outputText value="Nota"/></f:facet>
                                            <h:outputText value="#{listaAluNota2.alu_notaT}"/>
                                        </rich:column>
                                    </rich:dataTable>
                                </div>

                            </td>

                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>
        </f:view>
    </body>	
</html>  
