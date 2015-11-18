<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Reportes</title>
           <script type="text/javascript">
                function printObjectsSelected(sgcomponent,codigoAL) {
                    codigoAL.value=sgcomponent.getSelectedItems().pluck('w_codigo');
                }
            </script>
    </head>
    <body>
        <f:view>
            <jsp:include page="../TablaSistema/Menu.jsp"/>
            <rich:panel>
                <h:form id="form1">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-size:10px; font-family:verdana">
                        <tr>
                            <td width="100%" colspan="10">
                                <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                    <tr>
                                        <td align="left"><h:outputText value="Reportes de Cursos Libres" style="font-weight: bold;"/></td>
                                        <td align="right">
                                            <h:commandButton type="button" id="refrescar" value="" action="#{managerReportes.setListReport}" image="/Imagenes/actions/reload.png"  title="Refrescar"/>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr style="height: 20px;">
                            <td colspan="10"><hr size="1"></td>
                        </tr>
                    </table>
                    </h:form>

         <h:form id="form2">

          <%--  <rich:datascroller id="paginacion" for="masterTable" align="right" maxPages="10"/>   --%>
          <rich:dataTable id="masterTable" width="100%" border="0"  rows="10"
                            onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                            onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                            cellpadding="0" cellspacing="0"
                            value="#{managerReportes.listReporte}" var="rep">
               <rich:column>
                   <f:facet name="header">
                      <h:outputText value="Id"/>
                   </f:facet>
                   <h:outputText value="#{rep.idReporte}"/>
               </rich:column>
              <rich:column filterBy="#{rep.descripcion}" filterEvent="onkeyup" filterValue="#{managerReportes.descripcion}">
                   <f:facet name="header">
                       <h:outputText value="Reporte"/>
                   </f:facet>
                   <h:outputText value="#{rep.descripcion}"/>
               </rich:column>
               <rich:column width="50 px">
                   <f:facet name="header">
                        <h:outputText value="Visualizar"/>
                   </f:facet>
                   <f:param id="p_param_id" value="#{rep.idReporte}"/>
                   <h:graphicImage value="/Imagenes/actions/viewmag.png" style="border-width: 0px;cursor: pointer" title=" Visualizar parametros necesarios">
                         <a4j:support event="onclick" actionListener="#{managerReportes.showParameterReport}" oncomplete="#{managerReportes.oncomplete}" reRender="mpParametro"/>
                   </h:graphicImage>
                </rich:column>
                 <f:facet name="footer">
                     <rich:datascroller id="paginacion" renderIfSinglePage="false" align="right" maxPages="10"/>
                </f:facet>
             </rich:dataTable>
          </h:form> 
        </rich:panel>
            

        <rich:modalPanel id="mpParametro" autosized="true" width="500" zindex="4000">
                <f:facet name="header">
                    <h:outputText value="Ingresar Parametro"/>
                </f:facet>

                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mpParametro')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>

                <h:form id="form3">
                    <h:panelGrid columns="2">
                       <h:outputText value="Reporte" style="display:block;width:200px;" />
                       <h:outputText value="#{managerReportes.pop_descripcion}"/>
                       <h:outputText value="Parametros necesarios" style="font-weight: bold" />
                       <h:panelGroup />
                       <h:panelGroup>
                         <h:outputText value="F. Inicio" rendered="#{managerReportes.pop_fini && managerReportes.idReporte != 167}"/>
                         <h:outputText value="Inicios del " rendered="#{managerReportes.pop_fini && managerReportes.idReporte == 167}"/>
                       </h:panelGroup>
                       <h:panelGroup>
                          <h:outputText value="F. Fin" rendered="#{managerReportes.pop_ffin && managerReportes.idReporte != 167}" />
                          <h:outputText value="al" rendered="#{managerReportes.pop_fini && managerReportes.idReporte == 167}"/>
                       </h:panelGroup>
                       <rich:calendar id="fecha_ini" value="#{managerReportes.fecha_ini}" datePattern="dd/MM/yyyy" rendered="#{managerReportes.pop_fini}"/>
                       <rich:calendar id="fecha_fin" value="#{managerReportes.fecha_fin}" datePattern="dd/MM/yyyy" rendered="#{managerReportes.pop_ffin}"/>
                       <h:outputText value="Matriculados del" rendered="#{managerReportes.pop_fini_rango_eval}"/>
                       <h:outputText value="al" rendered="#{managerReportes.pop_ffin_rango_eval}" />
                       <rich:calendar id="fecha_ini_rango_eval" value="#{managerReportes.fecha_ini_rango_eval}" datePattern="dd/MM/yyyy" rendered="#{managerReportes.pop_fini_rango_eval}"/>
                       <rich:calendar id="fecha_fin_rango_eval" value="#{managerReportes.fecha_fin_rango_eval}" datePattern="dd/MM/yyyy" rendered="#{managerReportes.pop_ffin_rango_eval}"/>
                       <h:outputText  value="F. Impresion" rendered="#{managerReportes.pop_fimpresion}"/>
                       <rich:calendar id="fecha_imp" value="#{managerReportes.fecha_imp}" datePattern="dd/MM/yyyy" rendered="#{managerReportes.pop_fimpresion}"/>
                    </h:panelGrid>

                      <table width="80%" border="0" cellpadding="0" cellspacing="0" style="font-size:10px; font-family:verdana">
                         <tr>
                            <td align="left">
                              <h:outputText value="Alumno" rendered="#{managerReportes.pop_alumno}"/>
                            </td>
                            <td align="left">
                               <h:outputText id="alu_codigo" value="Codigo" rendered="#{managerReportes.pop_alumno}"/>
                            </td>
                        </tr>
                        <tr>
                            <td align="left">
                                <h:inputText id="nombreAL" style="width: 400px;" size="250" value="#{managerReportes.suggestion}" rendered="#{managerReportes.pop_alumno}"/>
                                <rich:suggestionbox id="suggestion"
                                                    for="nombreAL" nothingLabel="Alumno No Encontrado"
                                                    suggestionAction="#{managerReportes.autocomplete}" var="pub"
                                                    height="150" width="400" cellpadding="2"
                                                    cellspacing="2" shadowOpacity="1" shadowDepth="1"
                                                    minChars="3" rules="none" zindex="4500"
                                                    usingSuggestObjects="true"
                                                    reRender="codigoAL"
                                                    onobjectchange="printObjectsSelected(#{rich:component('suggestion')},#{rich:element('codigoAL')});" >
                                    <h:column>
                                        <h:outputText value="#{pub.w_datos}" style="font-weight: bold;" />
                                    </h:column>
                                    <h:column>
                                        <h:outputText value="#{pub.w_codigo}" style="font-weight: bold;" />
                                    </h:column>
                                    </rich:suggestionbox>
                                </td>
                                <td align="left">
                                    <h:inputText id="codigoAL" value="#{managerReportes.codigo_alumno}" rendered="#{managerReportes.pop_alumno}"/>
                                </td>
                         </tr>
                    </table>
                    <br/>
                   <h:panelGrid id="gridsec" columns="2">
                       <h:outputText value="Centro" rendered="#{managerReportes.pop_centro}"/>
                       <h:selectOneMenu id="iCentro" value="#{managerReportes.i_centro_id}" rendered="#{managerReportes.pop_centro}">
                           <f:selectItems value="#{managerReportes.i_centros}"/>
                           <a4j:support event="onchange" reRender="iArea,iModulo,iTaller,iSeccion"/>
                       </h:selectOneMenu>
                       <h:outputText value="Area" rendered="#{managerReportes.pop_area}"/>
                       <h:selectOneMenu id="iArea" value="#{managerReportes.i_are_id}" rendered="#{managerReportes.pop_area}">
                           <f:selectItems value="#{managerReportes.i_areas}"/>
                           <a4j:support event="onchange" reRender="iModulo,iTaller,iSeccion"/>
                       </h:selectOneMenu>
                       <h:outputText value="Modulo" rendered="#{managerReportes.pop_modulo}"/>
                       <h:selectOneMenu id="iModulo" value="#{managerReportes.i_mod_id}" rendered="#{managerReportes.pop_modulo}">
                             <f:selectItems value="#{managerReportes.i_modulos}"/>
                             <a4j:support event="onchange" reRender="iCurso,iSeccion"/>
                        </h:selectOneMenu>
                       <h:outputText value="Curso" rendered="#{managerReportes.pop_curso}"/>
                       <h:selectOneMenu id="iCurso" value="#{managerReportes.i_cur_id}" rendered="#{managerReportes.pop_curso}">
                           <f:selectItems value="#{managerReportes.i_cursos}"/>
                             <a4j:support event="onchange" reRender="iTaller,iSeccion"/>
                        </h:selectOneMenu>
                       <h:outputText value="Debe Nota" rendered="#{managerReportes.pop_debe_nota}"/>
                       <h:selectOneMenu id="iDebeNota" value="#{managerReportes.debeNota}" rendered="#{managerReportes.pop_debe_nota}" >
                           <f:selectItem itemLabel="Debe nota" itemValue="1"/>
                           <f:selectItem itemLabel="Ya ingresó nota" itemValue="2"/>
                        </h:selectOneMenu>
                       <h:outputText value="Docente" rendered="#{managerReportes.pop_docentes}" />
                       <h:selectOneMenu id="iDocente" value="#{managerReportes.idDocente}"  rendered="#{managerReportes.pop_docentes}">
                           <f:selectItems value="#{managerReportes.docentes}"/>
                        </h:selectOneMenu>
                       <h:outputText value="Taller" rendered="#{managerReportes.pop_taller}" />
                       <h:selectOneMenu id="iTaller" value="#{managerReportes.i_tal_id}" rendered="#{managerReportes.pop_taller}">
                           <f:selectItems value="#{managerReportes.i_talleres}"/>
                             <a4j:support event="onchange" reRender="iSeccion"/>
                        </h:selectOneMenu>
                       <%--<h:outputText value="Sede" rendered="#{managerReportes.pop_sede}" />
                       <h:selectOneMenu id="iSede" value="#{managerReportes.i_sed_id}" rendered="#{managerReportes.pop_sede}">
                           <f:selectItems value="#{managerReportes.i_sedes}"/>
                           <a4j:support event="onchange" reRender="iSeccion" />
                        </h:selectOneMenu>--%>
                       <h:outputText value="Seccion" rendered="#{managerReportes.pop_seccion}" />
                       <h:selectOneMenu id="iSeccion" value="#{managerReportes.i_sec_id}" rendered="#{managerReportes.pop_seccion}">
                           <f:selectItems value="#{managerReportes.i_secciones}"/>
                             <a4j:support event="onchange" />
                        </h:selectOneMenu>
                    </h:panelGrid>
                    <div align="right" style="margin-right: 50px;">
                     <rich:separator height="2px"/>
                     <h:graphicImage value="/Imagenes/actions/page_white_acrobat.png" style="border-width: 0px;cursor: pointer;margin-right:20px" title="Vizualizar Reporte">
                         <a4j:support event="onclick" actionListener="#{managerReportes.ImprimirFicha}" oncomplete="#{managerReportes.oncomplete}" reRender="titulo,reporte"/>
                     </h:graphicImage>
                     
                     <%--
                     <h:commandButton action="#{managerReportes.generarArchivoDescargable}" rendered="#{managerReportes.image}"
                                      image="/Imagenes/actions/page_white_excel.png"
                                      title="Descargar archivo" />
                     --%>
                     <h:commandButton action="#{managerReportes.generarArchivoDescargablePDF}" style="margin-top:10px; margin-right:10px"
                                      image="/Imagenes/actions/mime_pdf.png"
                                      title="Descargar archivo(*.pdf)" />
                     <h:commandButton action="#{managerReportes.exportToExcel}" style="margin-top:10px; margin-right:10px"
                                      image="/Imagenes/actions/mime_xls.png"
                                      title="Descargar archivo(*.excel)" />
                    </div>
                </h:form>
          </rich:modalPanel>

          <rich:modalPanel id="mp7" width="830" height="600"  zindex="4500">
                <f:facet name="header">
                    <h:outputText id="titulo" value="#{managerReportes.tituloReporte}" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/title-close.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp7')" title="Cerrar"/>
                </f:facet>
                <a4j:form>
                   <table>
                      <tr>
                         <td align="center" valign="middle">
                         <rich:panel style="width : 790px; height:580px;" >
                            <a4j:mediaOutput id="reporte" uriAttribute="data" style="width : 750px; height:550px;" element="object" standby="cargando..." createContent="#{managerReportes.cargarReporte}" mimeType="application/pdf" value="#{managerReportes.valorRep}"/>
                         </rich:panel>
                         </td>
                       </tr>
                     </table>
                  </a4j:form>
         </rich:modalPanel>
        </f:view>
    </body>
</html>
