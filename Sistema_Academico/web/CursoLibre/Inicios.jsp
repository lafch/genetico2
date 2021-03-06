<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Horarios y Secciones</title>
    </head>
    <body>
        <f:view>
            <jsp:include page="../TablaSistema/Menu.jsp"/>
            <rich:panel>
                <h:form id="form1">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0"
                           style="font-size:10px; font-family:verdana;">
                        <tr>
                            <td width="20%"><strong>INICIO - HORARIOS Y SECCIONES</strong></td>
                            <td align="right">
                                <rich:spacer width="8px"/>

                                <h:commandButton type="button" title="Refrescar"
                                                 action="#{managerInicio.cargarArbol}"
                                                 image="/Imagenes/actions/reload.png"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="6"><hr width="100%"/></td>
                        </tr>
                        <tr>
                            <td style="font-size:10px; font-family:verdana;vertical-align:top;">
                                <% /*<rich:tree value="#{managerSeccion.arbol}"
                                            ajaxSubmitSelection="true"
                                            switchType="ajax"
                                            style="font-size:10px; font-family:verdana"
                                            var="item"
                                            nodeSelectListener="#{managerSeccion.seleccion}"
                                            reRender="masterDetalle, masterTable, masterScroll"/>*/%>
                                <rich:tree style="width:300px" nodeSelectListener="#{managerInicio.seleccionarElemento}"
                                           reRender="dtInicios" ajaxSubmitSelection="true"  switchType="client"
                                           value="#{managerInicio.treeNode}" var="item"  >
                                    <rich:treeNode>
                                        <h:outputText value="#{item}" escape="false"/>
                                    </rich:treeNode>
                                </rich:tree>
                            </td>
                            <td  style="vertical-align:top;" colspan="5">
                                <table width="100%"  cellspacing="0" border="0" cellpadding="0">
                                    <tr>
                                        <td>
                                            <!--aqui la cabecera-->
                                        </td>
                                        <td align="right">
                                            <h:graphicImage value="/Imagenes/actions/filenew.png" style="cursor: pointer;"
                                                            title="Nueva seccion" width="16">
                                                <a4j:support event="onclick"
                                                             actionListener="#{managerInicio.nuevoInicio}"
                                                             oncomplete="#{managerInicio.oncomplete}"
                                                             reRender="formInicioN"/>
                                            </h:graphicImage>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <rich:dataTable value="#{managerInicio.listaInicio}" var="inicio" id="dtInicios" width="800" >
                                                <rich:column>
                                                    <f:facet name="header"><h:outputText value="Id"/> </f:facet>
                                                    <h:outputText value="#{inicio.iniId}"/>
                                                    <f:param id="p_ini_id" value="#{inicio.iniId}" />
                                                </rich:column>
                                                <rich:column>
                                                    <f:facet name="header"><h:outputText value="Codigo"/> </f:facet>
                                                    <h:outputText value="#{inicio.iniCodigo}"/>
                                                </rich:column>
                                                <rich:column>
                                                    <f:facet name="header"><h:outputText value="Descripcion"/> </f:facet>
                                                    <h:outputText value="#{inicio.iniDescripcion}"/>
                                                </rich:column>
                                                <rich:column>
                                                    <f:facet name="header"><h:outputText value="Fecha Inicio"/> </f:facet>
                                                    <h:outputText value="#{inicio.iniFechaInicio}"><f:convertDateTime pattern="dd/MM/yyyy"/></h:outputText>
                                                </rich:column>
                                                <rich:column>
                                                    <f:facet name="header"><h:outputText value="Fecha fin"/> </f:facet>
                                                    <h:outputText value="#{inicio.iniFechaFin}"><f:convertDateTime pattern="dd/MM/yyyy"/></h:outputText>
                                                </rich:column>
                                                <rich:column>
                                                    <f:facet name="header"><h:outputText value="Aperturado"/> </f:facet>
                                                    <h:outputText value="#{inicio.iniAperturado == 1 ? 'si' : 'no' }"/>
                                                </rich:column>
                                                <rich:column>
                                                    <f:facet name="header"><h:outputText value="Local"/> </f:facet>
                                                    <h:outputText value="#{inicio.acLocal.locDes}"/>
                                                </rich:column>
                                                <rich:column>
                                                    <f:facet name="header"><h:outputText value="Turno"/> </f:facet>
                                                    <h:outputText value="#{inicio.iniTurno}"/>
                                                </rich:column>
                                                <rich:column>
                                                    <f:facet name="header"><h:outputText value=""/> </f:facet>
                                                    <h:graphicImage value="/Imagenes/actions/editpaste.png" alt="Editar">
                                                        <a4j:support event="onclick" actionListener="#{managerInicio.seleccionarInicioPorId}"
                                                                     oncomplete="#{managerInicio.oncomplete}" reRender="formInicioN" />
                                                    </h:graphicImage>
                                                </rich:column>
                                                <rich:column>
                                                    <f:facet name="header"><h:outputText value=""/> </f:facet>
                                                    <h:graphicImage value="/Imagenes/actions/add_.gif" alt="agregar secciones">
                                                        <a4j:support event="onclick" actionListener="#{managerInicio.nuevoInicioSeccion}"
                                                                     oncomplete="#{managerInicio.oncomplete}" reRender="formInicio,dtSeccion" />
                                                    </h:graphicImage>
                                                </rich:column>
                                            </rich:dataTable>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:panel>

            <rich:modalPanel id="mpInicio" minWidth="400" height="300"
                             autosized="true" zindex="2000">
                <f:facet name="header">
                    <h:outputText value="Nuevo Inicio" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png"
                                    style="cursor:pointer" title="Cerrar"
                                    onclick="Richfaces.hideModalPanel('mpInicio')" />
                </f:facet>
                <h:form id="formInicioN">
                    <table align="center" width="630px">
                        <tr>
                            <td colspan="3" align="right">
                                <a4j:commandButton value="guardar" actionListener="#{managerInicio.guardarInicios}"
                                                   oncomplete="#{managerInicio.oncomplete}" reRender="dtInicios"  />
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Codigo"/></td>
                            <td><h:inputText value="#{managerInicio.beanInicio.iniCodigo}" maxlength="6"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Descripcion"/></td>
                            <td><h:inputText value="#{managerInicio.beanInicio.iniDescripcion}"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Turno"/></td>
                            <td>
                                <h:selectOneMenu value="#{managerInicio.beanInicio.iniTurno}">
                                    <f:selectItems value="#{managerInicio.cboTurno}"/>
                                </h:selectOneMenu>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><h:outputText value="fecha"/></td>
                            <td colspan="2">
                                del : <rich:calendar datePattern="dd/MM/yyyy" value="#{managerInicio.beanInicio.iniFechaInicio}" /> al :
                                <rich:calendar datePattern="dd/MM/yyyy" value="#{managerInicio.beanInicio.iniFechaFin}" />
                            </td>
                        </tr>

                        <tr>
                            <td><h:outputText value="Sede"/></td>
                            <td>
                                <h:selectOneMenu value="#{managerInicio.beanInicio.acLocal.id}">
                                    <f:selectItems value="#{managerInicio.cboLocal}"/>
                                </h:selectOneMenu></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Resolucion"/></td>
                            <td colspan="2"><h:inputText value="#{managerInicio.beanInicio.iniResolucion}" />
                                <h:outputText value=" Nro. horas " /> <h:inputText value="#{managerInicio.beanInicio.iniNroHoras}" size="10"/>
                            </td>

                        </tr>
                        <tr>
                            <td><h:outputText value="Apertura"/></td>
                            <td colspan="2"><h:selectBooleanCheckbox value="#{managerInicio.w_apertura}" />
                            </td>

                        </tr>
                    </table>

                </h:form>
            </rich:modalPanel>

            <rich:modalPanel id="mpInicioDetalle" width="800"
                             autosized="true" zindex="2000">
                <f:facet name="header">
                    <h:outputText value="Nuevo Inicio" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png"
                                    style="cursor:pointer" title="Cerrar"
                                    onclick="Richfaces.hideModalPanel('mpInicioDetalle')" />
                </f:facet>
                <h:form id="formInicio">
                    <table width="100%" align="center">
                        <tr>
                            <td colspan="2" align="right">
                                <h:graphicImage value="/Imagenes/actions/filesave.png" alt="Guardar" style="cursor:point;">
                                    <a4j:support event="onclick" actionListener="#{managerInicio.guardarSecciones}"/>
                                </h:graphicImage>
                            </td>
                        </tr>
                        <tr>
                            <td align="center" colspan="2"><h:outputText value="INICIO: "/>
                                <h:outputText value="#{managerInicio.beanInicio.iniDescripcion}"/></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center"><h:outputText value="F. Inicio: "/>
                                <h:outputText value="#{managerInicio.beanInicio.iniFechaInicio}">
                                    <f:convertDateTime pattern="dd/MM/yyyy"/>
                                </h:outputText> <h:outputText value=" al "/>
                                <h:outputText value="#{managerInicio.beanInicio.iniFechaFin}">
                                    <f:convertDateTime pattern="dd/MM/yyyy"/>
                                </h:outputText>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><rich:separator width="100%" height="2"/><br/> </td>
                        </tr>
                        <tr>
                            <td>
                                <rich:dataTable value="#{managerInicio.listaCursos}" var="listaI">
                                    <rich:column>
                                        <h:outputText value="#{listaI.arbDescripcion}" />
                                        <f:param id="p_cur_id" value="#{listaI.arbId}" />
                                    </rich:column>
                                    <rich:column>
                                        <h:graphicImage value="/Imagenes/actions/secciona.png" alt="agregar">
                                            <a4j:support event="onclick" actionListener="#{managerInicio.nuevaSeccion}"
                                                         reRender="formSeccion"
                                                         oncomplete="#{managerInicio.oncomplete}" />
                                        </h:graphicImage>
                                    </rich:column>
                                </rich:dataTable>

                            </td>
                            <td valign="top">

                                <rich:dataTable value="#{managerInicio.listaSeccion}" var="listaS" id="dtSeccion">
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="Id"/> </f:facet>
                                        <h:outputText value="#{listaS.secId}" />
                                        <f:param id="p_sec_id" value="#{listaS.secId}" />
                                        <f:param id="p_curso" value="#{listaS.clArbolAcademico.arbDescripcion}" />
                                        <f:param id="p_sec_nombre" value="#{listaS.secNombre}" />
                                        <f:param id="p_loc_id" value="#{listaS.locId}" />
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="Curso"/> </f:facet>
                                        <h:outputText value="#{listaS.clArbolAcademico.arbDescripcion}" />
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="Codigo"/> </f:facet>
                                        <h:outputText value="#{listaS.secCodigo}" />
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="Descripcion"/> </f:facet>
                                        <h:outputText value="#{listaS.secNombre}" />
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="fec. Ini"/> </f:facet>
                                        <h:outputText value="#{listaS.secFinicio}">
                                            <f:convertDateTime pattern="dd/MM/yyyy"/>
                                        </h:outputText>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="fec. Fin"/> </f:facet>
                                        <h:outputText value="#{listaS.secFfin}">
                                            <f:convertDateTime pattern="dd/MM/yyyy"/>
                                        </h:outputText>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header"><h:outputText value="Hor."/> </f:facet>
                                         <a4j:commandButton image="#{listaS.v_imagen_horario}"
                                                                       title="Horario"
                                                                       actionListener="#{managerInicio.cargarHorario}"
                                                                       oncomplete="#{managerInicio.oncomplete}"
                                                                       reRender="formHoraria,horPabellon,horAula"/>
                                    </rich:column>
                                     <rich:column>
                                        <f:facet name="header"><h:outputText value=""/> </f:facet>
                                         <a4j:commandButton image="/Imagenes/actions/icon_edit.png"
                                                                       title="Editar"
                                                                       actionListener="#{managerInicio.editarSeccion}"
                                                                       oncomplete="#{managerInicio.oncomplete}"
                                                                       reRender="formSeccion"/>
                                    </rich:column>

                                    
                                </rich:dataTable>
                            </td>
                        </tr>




                    </table>
                </h:form>
            </rich:modalPanel>

            <rich:modalPanel id="mpSeccion" minWidth="500"
                             autosized="true" zindex="2000">
                <f:facet name="header">
                    <h:outputText value="Mantenimiento seccion" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png"
                                    style="cursor:pointer" title="Cerrar"
                                    onclick="Richfaces.hideModalPanel('mpSeccion')" />
                </f:facet>
                <h:form id="formSeccion">
                    <table width="100%" style="font-size:12px; font-family:verdana">
                        <tr>
                            <td align="right" colspan="2">
                                <a4j:commandButton image="/Imagenes/actions/filesave.png"
                                                   actionListener="#{managerInicio.agregarSeccion}"
                                                   oncomplete="#{managerInicio.oncomplete}"
                                                   reRender="formInicio,dtSeccion" alt="agregar"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="tdLabel"><h:outputText value="C�digo"/></td>
                            <td><h:inputText id="secCodigo" value="#{managerInicio.seccion.secCodigo}"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Nombre"/></td>
                            <td><h:inputText id="secNombre" value="#{managerInicio.seccion.secNombre}" style="width: 300px;"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Local"/></td>
                            <td>
                                <h:selectOneMenu id="setLocal" value="#{managerInicio.seccion.locId}" >
                                    <f:selectItems value="#{managerInicio.cboLocal}" />
                                </h:selectOneMenu> 

                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Vac. M�nimas"/></td>
                            <td><h:inputText id="secVacMin" value="#{managerInicio.seccion.secVacMin}"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Vac. M�ximas"/></td>
                            <td><h:inputText id="secVacMax" value="#{managerInicio.seccion.secVacMax}"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Inicio de Clases"/></td>
                            <td><rich:calendar id="secIni" value="#{managerInicio.seccion.secFinicio}"
                                           showWeeksBar="false" datePattern="dd-MM-yyyy"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Fin de Clases"/></td>
                            <td><rich:calendar id="secFin" value="#{managerInicio.seccion.secFfin}"
                                           showWeeksBar="false" datePattern="dd-MM-yyyy"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Inicio de Matr�cula"/></td>
                            <td><rich:calendar id="secIniMat" value="#{managerInicio.seccion.secFinicioMatricula}"
                                           showWeeksBar="false" datePattern="dd-MM-yyyy"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Fin de Matr�cula"/></td>
                            <td><rich:calendar id="secFinMat" value="#{managerInicio.seccion.secFfinMatricula}"
                                           showWeeksBar="false" datePattern="dd-MM-yyyy"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Turno"/></td>
                            <td>
                                <h:selectOneMenu id="secTurnos" value="#{managerInicio.seccion.plaId}">
                                    <f:selectItems value="#{managerInicio.cboTurno}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>

            <!-- cargar horarios -->

            <rich:modalPanel id="mpHoraria" minWidth="1000"
                             autosized="true" zindex="3000">
                <f:facet name="header">
                    <h:outputText value="Horario"/>
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
                                                   title="Guardar"
                                                   actionListener="#{managerInicio.guardarHorarios}"
                                                   oncomplete="#{managerInicio.oncomplete}"/>
                            </td>
                        </tr>
                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                       
                        <tr>
                            <td class="tdLabel"><h:outputText value="Curso"/></td>
                            <td><h:outputText value="#{managerInicio.h_curso}"/></td>
                        </tr>
                        
                        <tr>
                            <td class="tdLabel"><h:outputText value="Secci�n"/></td>
                            <td><h:outputText value="#{managerInicio.h_sec_descripcion}"/></td>
                        </tr>
                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                                <% /* <tr>
                                            <td class="tdLabel"><h:outputText value="Sede"/></td>
                                            <td>
                                            <h:selectOneMenu id="horSede" value="#{managerSeccion.horaria.w_sed_id}">
                                            <f:selectItems value="#{managerSeccion.horaria.cboSede}"/>
                                            <a4j:support event="onchange" reRender="horPabellon,horAula" />
                                            </h:selectOneMenu>
                                    </td>
                                    </tr>*/%>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Pabellon"/></td>
                            <td>
                                <h:selectOneMenu id="horPabellon" value="#{managerInicio.horaria.w_pab_id}">
                                    <f:selectItems value="#{managerInicio.horaria.cboPabellon}"/>
                                    <a4j:support event="onchange" reRender="horAula" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Aula"/></td>
                            <td>
                                <h:selectOneMenu id="horAula" value="#{managerInicio.horaria.acAula.id}">
                                    <f:selectItems value="#{managerInicio.horaria.aulas}"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Docente"/></td>
                            <td>
                                <h:selectOneMenu id="horDocente" value="#{managerInicio.horaria.acDocente.id}">
                                    <f:selectItems value="#{managerInicio.horaria.profesores}"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Tipo de Clase"/></td>
                            <td>
                                <h:selectOneMenu id="horTipoClase" value="#{managerInicio.horaria.horTipoClase}">
                                    <f:selectItems value="#{managerInicio.horaria.tipoClase}"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="D�a"/></td>
                            <td>
                                <h:selectOneMenu id="horDia" value="#{managerInicio.horaria.horDia}">
                                    <f:selectItems value="#{managerInicio.horaria.dias}"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Inicio"/></td>
                            <td>
                                <h:panelGroup>
                                    <h:inputText id="horIniH" value="#{managerInicio.horaria.inicio_h}" maxlength="2" required="true" style="width: 20px;"/>
                                    <h:outputLabel value=":"/>
                                    <h:inputText id="horIniM" value="#{managerInicio.horaria.inicio_m}" maxlength="2" required="true" style="width: 20px;"/>
                                    <h:selectOneMenu id="horIniMeridianos" value="#{managerInicio.horaria.meridiano_inicio}">
                                        <f:selectItems value="#{managerInicio.horaria.meridianos}"/>
                                    </h:selectOneMenu>
                                </h:panelGroup>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Fin"/></td>
                            <td>
                                <h:panelGroup>
                                    <h:inputText id="horFinH" value="#{managerInicio.horaria.fin_h}" maxlength="2" required="true" style="width: 20px;"/>
                                    <h:outputLabel value=":"/>
                                    <h:inputText id="horFinM" value="#{managerInicio.horaria.fin_m}" maxlength="2" required="true" style="width: 20px;"/>
                                    <h:selectOneMenu id="horFinMeridianos" value="#{managerInicio.horaria.meridiano_fin}">
                                        <f:selectItems value="#{managerInicio.horaria.meridianos}"/>
                                    </h:selectOneMenu>
                                </h:panelGroup>
                                <rich:spacer width="20px"/>

                                <a4j:commandButton image="/Imagenes/actions/edit_add.png"
                                                   title="Agregar"
                                                   actionListener="#{managerInicio.agregarHorario}"
                                                   oncomplete="#{managerInicio.oncomplete}"
                                                   reRender="formHoraria"/>
                            </td>
                        </tr>
                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                        <tr>
                            <td colspan="4" align="center" valign="top">
                                <rich:dataTable id="tablaDetalle" var="hor" width="100%"
                                                value="#{managerInicio.nListaHoraria}"
                                                onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'">
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Id"/>
                                        </f:facet>
                                        <h:outputText value="#{hor.horId}"/>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="D�a"/>
                                        </f:facet>
                                        <h:outputText value="#{hor.v_hor_dia}" rendered="#{hor.view_bool}"/>

                                        <h:selectOneMenu value="#{hor.horDia}" rendered="#{hor.editable_bool}">
                                            <f:selectItems value="#{managerInicio.horaria.dias}"/>
                                        </h:selectOneMenu>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Inicio"/>
                                        </f:facet>
                                        <h:outputText value="#{hor.horHini}" rendered="#{hor.view_bool}">
                                            <f:convertDateTime pattern="hh:mm a"/>
                                        </h:outputText>

                                        <h:panelGroup>
                                            <h:inputText value="#{hor.inicio_h}" maxlength="2" required="true" style="width: 20px;" rendered="#{hor.editable_bool}"/>
                                            <h:outputLabel value=":" rendered="#{hor.editable_bool}"/>
                                            <h:inputText value="#{hor.inicio_m}" maxlength="2" required="true" style="width: 20px;" rendered="#{hor.editable_bool}"/>
                                            <h:selectOneMenu value="#{hor.meridiano_inicio}" rendered="#{hor.editable_bool}">
                                                <f:selectItems value="#{managerInicio.horaria.meridianos}"/>
                                            </h:selectOneMenu>
                                        </h:panelGroup>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Fin"/>
                                        </f:facet>
                                        <h:outputText value="#{hor.horHfin}" rendered="#{hor.view_bool}">
                                            <f:convertDateTime pattern="hh:mm a"/>
                                        </h:outputText>

                                        <h:panelGroup>
                                            <h:inputText value="#{hor.fin_h}" maxlength="2" required="true" style="width: 20px;" rendered="#{hor.editable_bool}"/>
                                            <h:outputLabel value=":" rendered="#{hor.editable_bool}"/>
                                            <h:inputText value="#{hor.fin_m}" maxlength="2" required="true" style="width: 20px;" rendered="#{hor.editable_bool}"/>
                                            <h:selectOneMenu value="#{hor.meridiano_fin}" rendered="#{hor.editable_bool}">
                                                <f:selectItems value="#{managerInicio.horaria.meridianos}"/>
                                            </h:selectOneMenu>
                                        </h:panelGroup>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Aula"/>
                                        </f:facet>
                                        <h:outputText value="#{hor.acAula.aulDes}" rendered="#{hor.view_bool}"/>

                                        <h:selectOneMenu value="#{hor.w_sed_id_s}" rendered="#{hor.editable_bool}" id="iSedes">
                                            <f:selectItems value="#{hor.cboSede_s}"/>
                                            <a4j:support event="onchange" reRender="iPabes,iAulas" />
                                        </h:selectOneMenu><br />

                                        <h:selectOneMenu value="#{hor.w_pab_id_s}" rendered="#{hor.editable_bool}" id="iPabes">
                                            <f:selectItems value="#{hor.cboPabellon_s}"/>
                                            <a4j:support event="onchange" reRender="iAulas" />
                                        </h:selectOneMenu><br />

                                        <h:selectOneMenu value="#{hor.acAula.id}" rendered="#{hor.editable_bool}" id="iAulas">
                                            <f:selectItems value="#{hor.cboAula_s}"/>
                                        </h:selectOneMenu>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Docente"/>
                                        </f:facet>
                                        <h:outputText value="#{hor.acDocente.docNombre}" rendered="#{hor.view_bool}"/>

                                        <h:selectOneMenu value="#{hor.acDocente.id}" rendered="#{hor.editable_bool}">
                                            <f:selectItems value="#{managerInicio.horaria.profesores}"/>
                                        </h:selectOneMenu>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Tipo de Clase"/>
                                        </f:facet>
                                        <h:outputText value="#{hor.v_hor_tipo_clase}" rendered="#{hor.view_bool}"/>

                                        <h:selectOneMenu value="#{hor.horTipoClase}" rendered="#{hor.editable_bool}">
                                            <f:selectItems value="#{managerInicio.horaria.tipoClase}"/>
                                        </h:selectOneMenu>
                                    </rich:column>

                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Editar"/>
                                        </f:facet>
                                        <a4j:commandButton actionListener="#{hor.edit}"
                                                           image="/Imagenes/actions/klipper_dock.png" title="Editar"
                                                           rendered="#{hor.view_bool}" reRender="tablaDetalle,iSedes,iPabes,iAulas"/>
                                        <a4j:commandButton actionListener="#{hor.aceptar}"
                                                           image="/Imagenes/actions/fileexport.png" title="Aceptar"
                                                           rendered="#{hor.editable_bool}" reRender="tablaDetalle"/>
                                        <a4j:commandButton actionListener="#{hor.cancelar}"
                                                           image="/Imagenes/actions/fileclose.png" title="Cancelar"
                                                           rendered="#{hor.editable_bool}" reRender="tablaDetalle,iSedes,iPabes,iAulas"/>
                                    </rich:column>

                                    
                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Eliminar"/>
                                        </f:facet>
                                        <f:param id="id_hor_delete" value="#{hor.horId}"/>
                                        <a4j:commandButton image="/Imagenes/actions/edit_remove.png"
                                                           title="Eliminar"
                                                           actionListener="#{managerInicio.removerHorario}"
                                                           reRender="tablaDetalle"/>
                                        <f:param id="p_hor_pos" value="#{hor.posicion}"/>
                                    </rich:column>
                                </rich:dataTable>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>


        </f:view>
    </body>
</html>
