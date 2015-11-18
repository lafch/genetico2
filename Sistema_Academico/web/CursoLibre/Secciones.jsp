<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Horarios y Secciones</title>
        <script>
            function validar(e) {
                tecla = (document.all)?e.keyCode:e.which;
                if (tecla==8 || tecla==0) return true;
                patron = /\d/;
                te = String.fromCharCode(tecla);
                //alert(tecla);
                return patron.test(te);
            }
        </script>
    </head>
    <body>
        <f:view>
            <jsp:include page="../TablaSistema/Menu.jsp"/>
            <rich:panel>
                <h:form id="form1">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0"
                           style="font-size:10px; font-family:verdana;">
                        <tr>
                            <td width="20%"><strong>HORARIOS Y SECCIONES</strong></td>
                            <td align="right">
                                <rich:spacer width="8px"/>

                                <h:commandButton type="button" title="Refrescar"
                                                 action="#{managerSeccion.cargarArbol}"
                                                 image="/Imagenes/actions/reload.png"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="6"><hr width="100%"/></td>
                        </tr>
                        <tr>
                            <td style="font-size:10px; font-family:verdana;vertical-align:top;">
                                <% /*
                                     * <rich:tree
                                     * value="#{managerSeccion.arbol}"
                                     * ajaxSubmitSelection="true"
                                     * switchType="ajax" style="font-size:10px;
                                     * font-family:verdana" var="item"
                                     * nodeSelectListener="#{managerSeccion.seleccion}"
                                     * reRender="masterDetalle, masterTable,
                                     * masterScroll"/>
                                     */%>
                                <rich:tree style="width:300px" nodeSelectListener="#{managerSeccion.seleccionarElemento}"
                                           reRender="masterDetalle, masterTable, masterScroll" ajaxSubmitSelection="true"  switchType="client"
                                           value="#{managerSeccion.treeNode}" var="item"  >
                                    <rich:treeNode>
                                        <h:outputText value="#{item}" escape="false"/>
                                    </rich:treeNode>
                                </rich:tree>
                            </td>
                            <td  style="vertical-align:top;" colspan="5">
                                <table width="100%"  cellspacing="0" border="0" cellpadding="0">
                                    <tr>
                                        <td>
                                            <h:panelGroup id="masterDetalle">
                                                <h:outputText value="Modulo2: #{managerSeccion.mod_nombre}"/><br />
                                                <h:outputText value="Curso: #{managerSeccion.cur_nombre}"/><br />
                                                <h:outputText value="Taller: #{managerSeccion.talape_nombre}"/>
                                            </h:panelGroup>
                                        </td>
                                        <td align="right">
                                            <h:graphicImage value="/Imagenes/actions/filenew.png" style="cursor: pointer;"
                                                            title="Nueva seccion" width="16">
                                                <a4j:support event="onclick"
                                                             actionListener="#{managerSeccion.seleccionarSeccion}"
                                                             oncomplete="#{managerSeccion.oncomplete}"
                                                             reRender="formSeccion"/>
                                            </h:graphicImage>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <rich:datascroller id="masterScroll" for="masterTable"
                                                               align="right" maxPages="30"/>
                                            <rich:dataTable id="masterTable" width="100%" border="0" rows="10"
                                                            onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                            onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                            cellpadding="0" cellspacing="0"
                                                            value="#{managerSeccion.listaSecciones}" var="seccl">

                                                <f:param id="p_sec_id" value="#{seccl.secId}"/>
                                                <f:param id="p_loc_id" value="#{seccl.locId}"/>
                                                <f:param id="p_sec_detalle" value="#{seccl.secCodigo} | #{seccl.secNombre}"/>
                                                <%//<f:param id="p_tal_id" value="#{seccl.clTallerAperturado.clTaller.talId}" /> %>
                                                <f:param id="p_tal_id" value="#{seccl.clArbolAperturado.clArbolAcademico.arbId}" />
                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Id"/>
                                                    </f:facet>
                                                    <h:outputText value="#{seccl.secId}"/>
                                                </rich:column>
                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Sede"/>
                                                    </f:facet>
                                                    <h:outputText value="#{seccl.w_sede}"/>
                                                </rich:column>
                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Codigo"/>
                                                    </f:facet>
                                                    <h:outputText value="#{seccl.secCodigo}"/>
                                                </rich:column>
                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Nombre"/>
                                                    </f:facet>
                                                    <h:outputText value="#{seccl.secNombre}"/>
                                                    <rich:toolTip>
                                                        <span style="white-space:nowrap">
                                                            <h:outputText value="#{seccl.w_profesor}"/>
                                                        </span>
                                                    </rich:toolTip>
                                                </rich:column>
                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Fecha de Inicio"/>
                                                    </f:facet>
                                                    <h:outputText value="#{seccl.secFinicio}">
                                                        <f:convertDateTime pattern="dd '/' MM '/' yyyy" />
                                                    </h:outputText>
                                                </rich:column>
                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Fecha de Fin"/>
                                                    </f:facet>
                                                    <h:outputText value="#{seccl.secFfin}">
                                                        <f:convertDateTime pattern="dd '/' MM '/' yyyy" />
                                                    </h:outputText>
                                                </rich:column>
                                                <rich:column style="text-align: center;">
                                                    <f:facet name="header">
                                                        <h:outputText value="Editar"/>
                                                    </f:facet>
                                                    <a4j:commandButton image="/Imagenes/actions/editpaste.png"
                                                                       actionListener="#{managerSeccion.seleccionarSeccion}"
                                                                       oncomplete="javascript:Richfaces.showModalPanel('mpSeccion')"
                                                                       reRender="formSeccion,setLocal,secTurnos"  />
                                                </rich:column>
                                              <%--  <rich:column style="text-align: center;">
                                                    <f:facet name="header">
                                                        <h:outputText value="Asignauras"/>
                                                    </f:facet>
                                                    <a4j:commandButton image="/Imagenes/actions/asignaturas.png"
                                                                       actionListener="#{managerSeccion.seleccionarSeccion}"
                                                                       oncomplete="javascript:Richfaces.showModalPanel('mpAsignatura')"
                                                                       reRender="formAsignatura,setLocal,secTurnos"  />
                                                </rich:column> --%>
                                                <rich:column style="text-align: center;">
                                                    <f:facet name="header">
                                                        <h:outputText value="Horario"/>
                                                    </f:facet>
                                                    <a4j:commandButton image="#{seccl.v_imagen_horario}"
                                                                       title="Horario"
                                                                       actionListener="#{managerSeccion.cargarHorario}"
                                                                       oncomplete="#{managerSeccion.oncomplete}"
                                                                       reRender="formHoraria,horPabellon,horAula"/>
                                                </rich:column>
                                                <rich:column style="text-align: center;">
                                                    <f:facet name="header">
                                                        <h:outputText value="Matriculados"/>
                                                    </f:facet>
                                                    <f:param id="par_sec_id" value="#{sec.i_sec_id}"/>
                                                    <a4j:commandLink title="Matriculados"
                                                                     value="#{seccl.cantMatriculados}"
                                                                     actionListener="#{managerSeccion.cargarMatriculados}"
                                                                     oncomplete="javascript:Richfaces.showModalPanel('mpMatriculados')"
                                                                     reRender="formMatriculados"/>
                                                </rich:column>
                                                <rich:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Pre-Matriculados"/>
                                                    </f:facet>
                                                    <p align="center">
                                                        <f:param id="pre_sec_id" value="#{sec.i_sec_id}"/>
                                                        <a4j:commandLink title="Pre-Matriculados"
                                                                         value="#{seccl.cantPreMatriculados}"
                                                                         actionListener="#{managerCLMatriculaSeccion.cargarPreMatriculados}"
                                                                         oncomplete="javascript:Richfaces.showModalPanel('mpPreMatriculados')"
                                                                         reRender="formPreMatriculados"/>
                                                    </p>
                                                </rich:column>
                                                <rich:column style="text-align: center;">
                                                    <f:facet name="header">
                                                        <h:outputText value="Eliminar"/>
                                                    </f:facet>
                                                    <a4j:commandButton image="/Imagenes/actions/delete.gif"
                                                                       actionListener="#{managerSeccion.verificarEliminacion}"
                                                                       oncomplete="#{managerSeccion.oncomplete}"
                                                                       reRender="formEliminar"/>
                                                </rich:column>
                                                <rich:column>
                                                    <f:facet name="header"><h:outputText value="" /></f:facet>
                                                    <h:graphicImage value="/Imagenes/actions/fileprint.png" style="border-width: 0px;cursor: pointer" title="Imprimir Listado ">
                                                        <a4j:support event="onclick" actionListener="#{managerSeccion.ImprimirFicha}" oncomplete="javascript:Richfaces.showModalPanel('mp7')" reRender="titulo,reporte"/>
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

            <rich:modalPanel id="mpSeccion" minWidth="650" width="750"
                             autosized="true" zindex="2000">
                <f:facet name="header">
                    <h:outputText value="Nueva seccion" />
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
                                                   actionListener="#{managerSeccion.insertarActualizarSeccion}"
                                                   oncomplete="#{managerSeccion.oncomplete}"
                                                   reRender="masterDetalle, masterScroll, masterTable"/>
                            </td>
                        </tr>
                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                        <tr>
                            <td width="110px" class="tdLabel"><h:outputText value="Modulo"/></td>
                            <td><h:outputText value="#{managerSeccion.mod_nombre}"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Curso"/></td>
                            <td><h:outputText value="#{managerSeccion.cur_nombre}"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Taller aperturado"/></td>
                            <td><h:outputText value="#{managerSeccion.talape_nombre}"/></td>
                        </tr>
                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="C�digo"/></td>
                            <td><h:inputText id="secCodigo" value="#{managerSeccion.seccion.secCodigo}"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Nombre"/></td>
                            <td><h:inputText id="secNombre" value="#{managerSeccion.seccion.secNombre}" style="width: 300px;"/></td>
                        </tr>
                        <%/*
                             * <tr> <td class="tdLabel"><h:outputText
                             * value="Grupo"/></td> <td> <h:selectOneMenu
                             * id="setGrupo"
                             * value="#{managerSeccion.seccion.secGrupoId}" >
                             * <f:selectItems
                             * value="#{managerSeccion.seccion.cboSeccionGrupo}"
                             * /> </h:selectOneMenu> </td> </tr>
                             */%>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Local"/></td>
                            <td>
                                <h:selectOneMenu id="setLocal" value="#{managerSeccion.seccion.locId}" >
                                    <f:selectItems value="#{managerSeccion.seccion.cboLocal}" />
                                </h:selectOneMenu>

                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Vac. M�nimas"/></td>
                            <td><h:inputText id="secVacMin" value="#{managerSeccion.seccion.secVacMin}"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Vac. M�ximas"/></td>
                            <td><h:inputText id="secVacMax" value="#{managerSeccion.seccion.secVacMax}"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Inicio de Clases"/></td>
                            <td><rich:calendar id="secIni" value="#{managerSeccion.seccion.secFinicio}"
                                           showWeeksBar="false" datePattern="dd-MM-yyyy"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Fin de Clases"/></td>
                            <td><rich:calendar id="secFin" value="#{managerSeccion.seccion.secFfin}"
                                           showWeeksBar="false" datePattern="dd-MM-yyyy"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Inicio de Matr�cula"/></td>
                            <td><rich:calendar id="secIniMat" value="#{managerSeccion.seccion.secFinicioMatricula}"
                                           showWeeksBar="false" datePattern="dd-MM-yyyy"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Fin de Matr�cula"/></td>
                            <td><rich:calendar id="secFinMat" value="#{managerSeccion.seccion.secFfinMatricula}"
                                           showWeeksBar="false" datePattern="dd-MM-yyyy"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Turno"/></td>
                            <td>
                                <h:selectOneMenu id="secTurnos" value="#{managerSeccion.seccion.plaId}">
                                    <f:selectItems value="#{managerSeccion.seccion.turnos}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Tipo"  rendered="#{not managerSeccion.esInformes}"/></td>
                            <td>
                                <h:selectOneMenu id="seTiposSeccion" value="#{managerSeccion.tipoSeccion}" rendered="#{not managerSeccion.esInformes}">
                                    <f:selectItem itemLabel="Inicio" itemValue="1"/>
                                    <f:selectItem itemLabel="Continuaci�n" itemValue="2"/>
                                    <a4j:support event="onchange" reRender="pnlCboSecPre,pnlLblSecPre,pnlTbSeccPre" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel">
                                <h:panelGroup id="pnlLblSecPre">
                                    <h:outputText rendered="#{managerSeccion.tipoSeccion==2}" id="lblSecPre" value="Secciones Predecesoras"/>
                                </h:panelGroup>
                            </td>
                            <td class="tdLabel">
                                <h:panelGroup id="pnlCboSecPre">
                                    <h:panelGrid  rendered="#{managerSeccion.tipoSeccion==2}" >
                                        <h:selectOneMenu id="seCentrosPre" value="#{managerSeccion.idCentroPre}">
                                            <f:selectItems value="#{managerSeccion.cboCentrosPre}" />
                                            <a4j:support event="onchange" reRender="pnlCboSecPre" />
                                        </h:selectOneMenu>
                                        <h:selectOneMenu id="seAreasPre" value="#{managerSeccion.idAreaPre}">
                                            <f:selectItems value="#{managerSeccion.cboAreasPre}" />
                                            <a4j:support event="onchange" reRender="pnlCboSecPre" />
                                        </h:selectOneMenu>
                                        <h:selectOneMenu id="seModulosPre" value="#{managerSeccion.idModuloPre}">
                                            <f:selectItems value="#{managerSeccion.cboModulosPre}" />
                                            <a4j:support event="onchange" reRender="pnlCboSecPre" />
                                        </h:selectOneMenu>
                                        <h:selectOneMenu id="seCursosPre" value="#{managerSeccion.idCursoPre}">
                                            <f:selectItems value="#{managerSeccion.cboCursosPre}" />
                                            <a4j:support event="onchange" reRender="pnlCboSecPre" />
                                        </h:selectOneMenu>
                                        <h:panelGroup>
                                            <h:selectOneMenu id="seSeccionPre" value="#{managerSeccion.idSeccionPre}">
                                                <f:selectItems value="#{managerSeccion.cboSeccionesPre}" />
                                                <a4j:support event="onchange" reRender="pnlCboSecPre" />
                                            </h:selectOneMenu>
                                            <a4j:commandButton rendered="#{managerSeccion.idSeccionPre!=0}" image="/Imagenes/actions/edit_add.png"
                                                               actionListener="#{managerSeccion.addSecPre}"
                                                               reRender="pnlCboSecPre,pnlTbSeccPre"/>
                                        </h:panelGroup>
                                    </h:panelGrid>
                                </h:panelGroup>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <h:panelGroup id="pnlTbSeccPre">
                                    <rich:dataTable rendered="#{managerSeccion.tipoSeccion==2 && not empty managerSeccion.lstSeccionesPrecedentes}" id="tbSecPred" value="#{managerSeccion.lstSeccionesPrecedentes}" var="sec">
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="ID"/>
                                            </f:facet>
                                            <h:outputText value="#{sec.secId}" />
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="�REA / M�DULO / CURSO"/>
                                            </f:facet>
                                            <h:outputText value="#{sec.clArbolAcademico.arbAcadPadre.arbAcadPadre.arbAcadPadre.arbDescripcion}" />
                                            <h:outputText value=" / " />
                                            <h:outputText value="#{sec.clArbolAcademico.arbAcadPadre.arbAcadPadre.arbDescripcion}" />
                                            <h:outputText value=" / " />
                                            <h:outputText value="#{sec.clArbolAcademico.arbAcadPadre.arbDescripcion}" />
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="NOMBRE"/>
                                            </f:facet>
                                            <h:outputText value="#{sec.secNombre}" />
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="F. INICIO"/>
                                            </f:facet>
                                            <h:outputText value="#{sec.secFinicio}">
                                                <f:convertDateTime pattern="dd '/' MM '/' yyyy" />
                                            </h:outputText>
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="F. FIN"/>
                                            </f:facet>
                                            <h:outputText value="#{sec.secFfin}">
                                                <f:convertDateTime pattern="dd '/' MM '/' yyyy" />
                                            </h:outputText>
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="QUITAR"/>
                                            </f:facet>
                                            <a4j:commandButton image="/Imagenes/actions/edit_remove.png"
                                                               actionListener="#{managerSeccion.delSecPre}"
                                                               reRender="pnlTbSeccPre">
                                                <f:param name="id_sec_pre_del" value="#{sec.secId}" />
                                            </a4j:commandButton>
                                        </rich:column>
                                    </rich:dataTable>
                                </h:panelGroup>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>

            <rich:modalPanel id="mpEliminar" width="250" height="100"
                             autosized="true" zindex="2000">
                <f:facet name="header">
                    <h:outputText value="Eliminar seccion" />
                </f:facet>
                <h:form id="formEliminar">
                    <h:panelGroup layout="block" style="text-align: center;font-size: 12px;">
                        <h:outputText  escape="false"
                                       value="Se eliminara la seccion <br/><b>#{managerSeccion.sec_detalle}</b><br/><br/>"/>

                        <a4j:commandButton title="Aceptar"
                                           image="/Imagenes/actions/button_accept.png"
                                           actionListener="#{managerSeccion.eliminarSeccion}"
                                           oncomplete="Richfaces.hideModalPanel('mpEliminar')"
                                           reRender="masterScroll, masterTable"/>
                        <rich:spacer width="50px"/>

                        <a4j:commandButton title="Cancelar"
                                           image="/Imagenes/actions/button_cancel.png"
                                           oncomplete="Richfaces.hideModalPanel('mpEliminar')"/>
                    </h:panelGroup>
                </h:form>
            </rich:modalPanel>

            <rich:modalPanel id="mpHoraria" minWidth="1000" height="600"
                             resizeable="true" zindex="3000" style="overflow-y: scroll">
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
                                                   actionListener="#{managerSeccion.guardarHorarios}"
                                                   oncomplete="#{managerSeccion.oncomplete}"
                                                   reRender="masterTable, masterScroll"/>
                            </td>
                        </tr>
                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                        <tr>
                            <td class="tdLabel" style="width: 100px;"><h:outputText value="M�dulo"/></td>
                            <td><h:outputText value="#{managerSeccion.mod_nombre}"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Curso"/></td>
                            <td><h:outputText value="#{managerSeccion.cur_nombre}"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Taller"/></td>
                            <td><h:outputText value="#{managerSeccion.talape_nombre}"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Secci�n"/></td>
                            <td><h:outputText value="#{managerSeccion.sec_detalle}"/></td>
                        </tr>
                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Pabellon"/></td>D�a
                            <td>
                                <h:selectOneMenu id="horPabellon" value="#{managerSeccion.horaria.w_pab_id}">
                                    <f:selectItems value="#{managerSeccion.horaria.cboPabellon}"/>
                                    <a4j:support event="onchange" reRender="horAula" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Aula"/></td>
                            <td>
                                <h:selectOneMenu id="horAula" value="#{managerSeccion.horaria.acAula.id}">
                                    <f:selectItems value="#{managerSeccion.horaria.aulas}"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Docente"/></td>
                            <td>
                                <h:selectOneMenu id="horDocente" value="#{managerSeccion.horaria.acDocente.id}">
                                    <f:selectItems value="#{managerSeccion.horaria.profesores}"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Asignatura" rendered="#{managerSeccion.esAreaModular}"/></td>
                            <td>
                                <h:selectOneMenu id="cboAsignatura"  rendered="#{managerSeccion.esAreaModular}" value="#{managerSeccion.horaria.clSisEvaDetDocente.sisevaDetalleId}">
                                    <f:selectItems value="#{managerSeccion.horaria.cboSisEvaDet}"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Tipo de Clase"/></td>
                            <td>
                                <h:selectOneMenu id="horTipoClase" value="#{managerSeccion.horaria.horTipoClase}">
                                    <f:selectItems value="#{managerSeccion.horaria.tipoClase}"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="D�a"/></td>
                            <td>
                                <h:selectManyCheckbox value="#{managerSeccion.horaria.lstDiasCod}" layout="lineDirection">
                                    <f:selectItems value="#{managerSeccion.horaria.lstSeDias}" />
                                </h:selectManyCheckbox>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Inicio"/></td>
                            <td>
                                <h:panelGroup>
                                    <h:inputText id="horIniH" value="#{managerSeccion.horaria.inicio_h}" maxlength="2" required="true" style="width: 20px;"/>
                                    <h:outputLabel value=":"/>
                                    <h:inputText id="horIniM" value="#{managerSeccion.horaria.inicio_m}" maxlength="2" required="true" style="width: 20px;"/>
                                    <h:selectOneMenu id="horIniMeridianos" value="#{managerSeccion.horaria.meridiano_inicio}">
                                        <f:selectItems value="#{managerSeccion.horaria.meridianos}"/>
                                    </h:selectOneMenu>
                                </h:panelGroup>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Fin"/></td>
                            <td>
                                <h:panelGroup>
                                    <h:inputText id="horFinH" value="#{managerSeccion.horaria.fin_h}" maxlength="2" required="true" style="width: 20px;"/>
                                    <h:outputLabel value=":"/>
                                    <h:inputText id="horFinM" value="#{managerSeccion.horaria.fin_m}" maxlength="2" required="true" style="width: 20px;"/>
                                    <h:selectOneMenu id="horFinMeridianos" value="#{managerSeccion.horaria.meridiano_fin}">
                                        <f:selectItems value="#{managerSeccion.horaria.meridianos}"/>
                                    </h:selectOneMenu>
                                </h:panelGroup>
                                <rich:spacer width="20px"/>

                                <a4j:commandButton image="/Imagenes/actions/edit_add.png"
                                                   title="Agregar"
                                                   actionListener="#{managerSeccion.agregarHorario}"
                                                   oncomplete="#{managerSeccion.oncomplete}"
                                                   reRender="formHoraria"/>
                            </td>
                        </tr>
                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                        <tr>
                            <td colspan="4" align="center" valign="top">
                                <rich:dataTable id="tablaDetalle" var="hor" width="100%"
                                                value="#{managerSeccion.nListaHoraria}"
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
                                            <f:selectItems value="#{managerSeccion.horaria.dias}"/>
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
                                                <f:selectItems value="#{managerSeccion.horaria.meridianos}"/>
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
                                                <f:selectItems value="#{managerSeccion.horaria.meridianos}"/>
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
                                            <f:selectItems value="#{managerSeccion.horaria.profesores}"/>
                                        </h:selectOneMenu>
                                    </rich:column>
                                    <rich:column rendered="#{managerSeccion.esAreaModular}" >
                                        <f:facet name="header">
                                            <h:outputText value="Asignatura"/>
                                        </f:facet>
                                        <h:outputText value="#{hor.clSisEvaDetDocente.sisevaDetalleNombre}" rendered="#{hor.view_bool}"/>
                                        <h:selectOneMenu value="#{hor.clSisEvaDetDocente.sisevaDetalleId}" rendered="#{hor.editable_bool}">
                                            <f:selectItems value="#{managerSeccion.horaria.cboSisEvaDet}"/>
                                        </h:selectOneMenu>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Tipo de Clase"/>
                                        </f:facet>
                                        <h:outputText value="#{hor.v_hor_tipo_clase}" rendered="#{hor.view_bool}"/>

                                        <h:selectOneMenu value="#{hor.horTipoClase}" rendered="#{hor.editable_bool}">
                                            <f:selectItems value="#{managerSeccion.horaria.tipoClase}"/>
                                        </h:selectOneMenu>
                                    </rich:column>

                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Editar"/>
                                        </f:facet>
                                        <a4j:commandButton actionListener="#{hor.edit}"
                                                           image="/Imagenes/actions/klipper_dock.png" title="Editar"
                                                           rendered="#{hor.view_bool}" reRender="tablaDetalle,iSedes,iPabes,iAulas"/>
                                        <a4j:commandButton actionListener="#{hor.aceptar}" oncomplete="#{hor.oncomplete}"
                                                           image="/Imagenes/actions/fileexport.png" title="Aceptar"
                                                           rendered="#{hor.editable_bool}" reRender="tablaDetalle"/>
                                        <a4j:commandButton actionListener="#{hor.cancelar}"
                                                           image="/Imagenes/actions/fileclose.png" title="Cancelar"
                                                           rendered="#{hor.editable_bool}" reRender="tablaDetalle,iSedes,iPabes,iAulas"/>
                                    </rich:column>

                                    <f:param id="p_hor_pos" value="#{hor.posicion}"/>
                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Eliminar"/>
                                        </f:facet>
                                        <f:param id="id_hor_delete" value="#{hor.horId}"/>
                                        <a4j:commandButton image="/Imagenes/actions/edit_remove.png"
                                                           title="Eliminar"
                                                           actionListener="#{managerSeccion.removerHorario}"
                                                           reRender="tablaDetalle"/>
                                    </rich:column>
                                </rich:dataTable>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>

            <rich:modalPanel id="mpMatriculados" width="900"
                             zindex="3000" height="600" style="overflow-y: scroll">
                <f:facet name="header">
                    <h:outputText value="Matriculados"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mpMatriculados')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="formMatriculados">
                    <table width="100%" style="font-size:12px; font-family:verdana">
                        <tr>
                            <td colspan="2" align="right">
                                <h:graphicImage value="/Imagenes/actions/fileprint.png" style="border-width: 0px;cursor: pointer" title="Imprimir Matriculados ">
                                    <a4j:support event="onclick" actionListener="#{managerSeccion.ImprimirFichaMat}" oncomplete="javascript:Richfaces.showModalPanel('mp8');" reRender="tituloMat,reporteMat"/>
                                </h:graphicImage>
                                <a4j:commandButton image="/Imagenes/actions/filesave.png"
                                                   title="Guardar"
                                                   actionListener="#{managerSeccion.guardarMatriculados}"
                                                   oncomplete="#{managerSeccion.oncomplete}"
                                                   reRender="masterTable, masterScroll"/>
                            </td>
                        </tr>
                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                        <tr>
                            <td class="tdLabel" style="width: 80px;"><h:outputText value="M�dulo"/></td>
                            <td><h:outputText id="matModulo" value="#{managerSeccion.mod_nombre}"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Curso"/></td>
                            <td><h:outputText id="matCurso" value="#{managerSeccion.cur_nombre}"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Taller"/></td>
                            <td><h:outputText id="matTaller" value="#{managerSeccion.talape_nombre}"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Secci�n"/></td>
                            <td><h:outputText value="#{managerSeccion.sec_detalle}"/></td>
                        </tr>
                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                    </table>
                    <table>
                        <tr>
                            <td style="vertical-align:top !important; ">
                                <table>
                                    <tr>
                                        <td>Modo: </td>
                                        <td>
                                            <h:selectOneRadio disabled="#{managerSeccion.totalMatriculados == 0}" id="iModo" value="#{managerSeccion.modo_id}">
                                                <f:selectItem id="ipreMatricula" itemLabel="Pre-Matricula" itemValue="1" />
                                                <f:selectItem id="icambiarSeccion" itemLabel="Cambiar Seccion" itemValue="2" />
                                                <a4j:support event="onchange" reRender="formMatriculados"/>
                                            </h:selectOneRadio>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="tdLabel"><h:outputText value="Modulo destino"/></td>
                                        <td>
                                            <h:selectOneMenu id="cb_modulo" value="#{managerSeccion.mod_dest_id}"
                                                             style="#{managerSeccion.style} width:200px !important;"
                                                             disabled="#{managerSeccion.totalMatriculados == 0}">
                                                <f:selectItems value="#{managerSeccion.mod_dest_desc}" />
                                                <a4j:support event="onchange" reRender="cb_taller" />
                                            </h:selectOneMenu>
                                        </td>

                                    </tr>
                                    <tr>
                                        <td class="tdLabel"><h:outputText value="Taller destino"/></td>
                                        <td>
                                            <h:selectOneMenu id="cb_taller" value="#{managerSeccion.tal_dest_id}"
                                                             style="#{managerSeccion.style} width:200px !important;"
                                                             disabled="#{managerSeccion.totalMatriculados == 0}">
                                                <f:selectItems value="#{managerSeccion.tal_dest_desc}" />
                                                <a4j:support event="onchange" reRender="cb_sec,cb_estpag" />
                                            </h:selectOneMenu>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="tdLabel"><h:outputText value="Seccion destino"/></td>
                                        <td>
                                            <h:selectOneMenu  id="cb_sec" value="#{managerSeccion.sec_dest_id}"
                                                              style="#{managerSeccion.style} width:200px !important;"
                                                              disabled="#{managerSeccion.totalMatriculados == 0}">
                                                <f:selectItems value="#{managerSeccion.sec_dest_desc}" />
                                            </h:selectOneMenu>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="tdLabel"><h:outputText rendered="#{'1' eq managerSeccion.modo_id}" value="Tipo de pago"/></td>
                                        <td>
                                            <h:selectOneMenu rendered="#{'1' eq managerSeccion.modo_id}" id="cb_estpag" value="#{managerSeccion.estpag_dest_id}"
                                                             style="#{managerSeccion.style} width:200px !important;"
                                                             disabled="#{managerSeccion.totalMatriculados == 0}">
                                                <f:selectItems value="#{managerSeccion.estpag_dest_desc}"/>
                                                <a4j:support event="onchange" reRender="pgMontoPago,controles" actionListener="#{managerSeccion.cargarEstructuraPagoDetalle}"/>
                                            </h:selectOneMenu>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td>
                                <table>
                                    <tr>
                                        <td><h:outputText rendered="#{'1' eq managerSeccion.modo_id}" value="Monto del pago"/></td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <h:panelGrid id="pgMontoPago">
                                                <h:panelGroup layout="block" style="height=400px;">
                                                    <h:selectOneMenu  rendered="#{'1' eq managerSeccion.modo_id}" id="cb_estpagdet" value="#{managerSeccion.estpagdet_dest_id}"
                                                                     style="#{managerSeccion.style} width:200px !important;"
                                                                     disabled="#{managerSeccion.totalMatriculados == 0}">
                                                        <f:selectItems value="#{managerSeccion.estpagdet_dest_desc}"/>
                                                    </h:selectOneMenu>
                                                </h:panelGroup>
                                            </h:panelGrid>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                    <table style="width: 100%;">
                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                        <tr>
                            <td colspan="2" align="center" valign="top" width="100%">
                                <h:panelGroup rendered="#{managerSeccion.totalMatriculados == 0}" styleClass="message error">
                                    <h:outputText value="No hay alumnos matriculados"/>
                                </h:panelGroup>
                                <h:panelGroup rendered="#{managerSeccion.totalMatriculados != 0}" id="resultados">
                                    <h:outputText value="Relaci�n de Alumnos matriculados"/>
                                    <div style="overflow: auto; height: 350px;width: 100%;">
                                        <rich:dataTable id="matriculados" style="width:100%;"
                                                        onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                        onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                        value="#{managerSeccion.listaAlumnosMatriculados}" var="alu">
                                            <rich:column style="text-align: center;">
                                                <f:facet name="header">
                                                    <h:outputText value="Nro."/>
                                                </f:facet>
                                                <h:outputText value="#{alu.aluContador}"/>
                                            </rich:column>
                                            <rich:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Apellidos"/>
                                                </f:facet>
                                                <h:outputText value="#{alu.aluAppaterno} #{alu.aluApmaterno}" style="#{alu.alu_style}"/>
                                            </rich:column>

                                            <rich:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Nombres"/>
                                                </f:facet>
                                                <h:outputText value="#{alu.aluNombres}" style="#{alu.alu_style}"/>
                                            </rich:column>
                                            <rich:column>
                                                <f:facet name="header">
                                                    <h:outputText value="F. de Matricula"/>
                                                </f:facet>
                                                <h:outputText value="#{alu.fechaMatricula}"/>
                                            </rich:column>
                                            <rich:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Telefono"/>
                                                </f:facet>
                                                <h:outputText value="#{alu.aluCelular} - #{alu.aluTelefono}"/>
                                            </rich:column>
                                            <rich:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Correo"/>
                                                </f:facet>
                                                <h:outputText value="#{alu.aluMail}"/>
                                            </rich:column>
                                            <rich:column style="text-align: center;" colspan="2">
                                                <f:facet name="header">
                                                    <h:outputText value="Ver Deudas" />
                                                </f:facet>
                                                <f:param id="mmm" value="#{alu.aluId}"/>   
                                                <a4j:commandButton image="/Imagenes/actions/deuda.png"
                                                                   actionListener="#{managerCLMatricula.mostrarDeudaAlumno}"
                                                                   oncomplete="#{managerCLMatricula.oncomplete}"
                                                                   reRender="formDeuda,tabladeudas"
                                                                   />
                                            </rich:column>
                                            <rich:column style="text-align: center;">
                                                <f:facet name="header">
                                                    <a4j:commandLink value="Matricular"
                                                                     actionListener="#{managerSeccion.SeleccionMultiple}"
                                                                     reRender="resultados"
                                                                     rendered="#{managerSeccion.totalMatriculados != 0}" style="font-size: 11px;"/>

                                                </f:facet>
                                                <h:selectBooleanCheckbox value="#{alu.aluMatriculado}"/>
                                            </rich:column>
                                            <rich:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Obs."/>
                                                </f:facet>
                                                <h:outputText value="#{alu.alu_obs}"/>
                                            </rich:column>
                                        </rich:dataTable>
                                    </div>
                                </h:panelGroup>
                            </td>
                        </tr>
                    </table>
                </h:form>

            </rich:modalPanel>

            <%--Muestra listado de alumnos pre matriculados--%>
            <rich:modalPanel id="mpPreMatriculados" width="550"
                             autosized="true" zindex="3000">
                <f:facet name="header">
                    <h:outputText value="Pre-Matriculados"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mpPreMatriculados')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="formPreMatriculados">
                    <table width="100%" style="font-size:12px; font-family:verdana">

                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                        <tr>
                            <td class="tdLabel" style="width: 80px;"><h:outputText value="M�dulo"/></td>
                            <td><h:outputText id="prematModulo" value="#{managerSeccion.mod_nombre}"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Curso"/></td>
                            <td><h:outputText id="prematCurso" value="#{managerSeccion.cur_nombre}"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Taller"/></td>
                            <td><h:outputText id="prematTaller" value="#{managerSeccion.talape_nombre}"/></td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Secci�n"/></td>
                            <td><h:outputText id="prematSeccion" value="#{managerCLMatriculaSeccion.sec_detalle}"/></td>
                        </tr>
                        <tr><td colspan="2"><hr width="100%"/></td></tr>

                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                        <tr>
                            <td colspan="4" align="center" valign="top" width="100%">
                                <h:panelGroup rendered="#{managerCLMatriculaSeccion.totalMatriculados == 0}" styleClass="message error">
                                    <h:outputText value="No hay alumnos matriculados"/>
                                </h:panelGroup>
                                <h:panelGroup rendered="#{managerCLMatriculaSeccion.totalMatriculados != 0}">
                                    <h:outputText value="Relaci�n de Alumnos pre-matriculados"/>
                                    <div style="overflow: auto; height: 350px;">
                                        <rich:dataTable id="prematriculados" width="520"
                                                        onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                        onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                        value="#{managerCLMatriculaSeccion.listaAlumnosMatriculados}" var="alu">
                                            <rich:column style="text-align: center;">
                                                <f:facet name="header">
                                                    <h:outputText value="Nro."/>
                                                </f:facet>
                                                <h:outputText value="#{alu.aluContador}"/>
                                            </rich:column>
                                            <rich:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Apellidos"/>
                                                </f:facet>
                                                <h:outputText value="#{alu.aluAppaterno} #{alu.aluApmaterno}"/>
                                            </rich:column>

                                            <rich:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Nombres"/>
                                                </f:facet>
                                                <h:outputText value="#{alu.aluNombres}"/>
                                            </rich:column>
                                            <rich:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Telefono"/>
                                                </f:facet>
                                                <h:outputText value="#{alu.aluCelular} - #{alu.aluTelefono}"/>
                                            </rich:column>
                                            <rich:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Correo"/>
                                                </f:facet>
                                                <h:outputText value="#{alu.aluMail}"/>
                                            </rich:column>

                                        </rich:dataTable>
                                    </div>
                                </h:panelGroup>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>


            <rich:modalPanel id="mpDeuda" width="600" height="400" autosized="true" zindex="4000">
                <f:facet name="header">
                    <h:outputText value=" Historial de Deuda"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mpDeuda')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="formDeuda">
                    <h:panelGrid columns="2">
                        <h:outputText value="CODIGO" style="font-size: 12px; font-weight: bold;"/>
                        <h:outputText  id= "valor1" value="#{managerCLMatricula.deu_alu_codigo}" style="font-size: 12px; font-weight: bold;"/>
                        <h:outputText value="NOMBRE" style="font-size: 12px; font-weight: bold;"/>
                        <h:outputText  id= "valor2" value="#{managerCLMatricula.deu_name_lumno}" style="font-size: 12px; font-weight: bold;"/>
                    </h:panelGrid>

                    <h:panelGroup rendered="#{managerCLMatricula.cantCursoDeuda == 0}" styleClass="message error">
                        <h:outputText value="No tiene Deudas"/>
                    </h:panelGroup>
                    <h:panelGroup rendered="#{managerCLMatricula.cantCursoDeuda != 0}">

                        <rich:dataTable id="tabladeudas"
                                        onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                        onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                        cellpadding="0"
                                        cellspacing="0"
                                        width="100%"
                                        border="0"
                                        value="#{managerCLMatricula.listaDeudaAlumno}"
                                        var="deud">
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Curso"/>
                                </f:facet>
                                <h:outputText value="#{deud.deu_modulo}" style="#{deud.alutar_style}" />
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Concepto"/>
                                </f:facet>
                                <h:outputText value="#{deud.deu_curso}" style="#{deud.alutar_style}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Monto a pagar"/>
                                </f:facet>
                                <h:outputText value="#{deud.deu_montopagar}">
                                </h:outputText>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Monto pagado"/>
                                </f:facet>
                                <h:outputText value="#{deud.deu_montopagado}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Saldo"/>
                                </f:facet>
                                <h:outputText value="#{deud.deu_saldo}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Fecha"/>
                                </f:facet>
                                <h:outputText value="#{deud.alumnoTarifa.alutarFechaProrroga}">
                                    <f:convertDateTime pattern="dd / MM / yyyy"/>
                                </h:outputText>
                                <f:param id="alutarId" value="#{deud.alumnoTarifa.alutarId}" />
                                <f:param id="alutarPro" value="#{deud.alutar_fecha}" />
                            </rich:column>
                            <rich:column>
                                <a4j:commandButton image="/Imagenes/actions/editpaste.png" oncomplete="#{managerCLMatricula.oncomplete}"
                                                   reRender="frmProrroga" actionListener="#{managerCLMatricula.abrirProrroga}"/>
                            </rich:column>
                        </rich:dataTable>
                    </h:panelGroup>
                </h:form>

            </rich:modalPanel>
            <rich:modalPanel id="mpEdicion" width="250" height="200" autosized="true" zindex="4000">
                <f:facet name="header">
                    <h:outputText value="Modificar fecha"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mpEdicion')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <a4j:form id="frmProrroga">
                    <rich:calendar value="#{managerCLMatricula.w_fechaProrroga}" datePattern="dd/MM/yyyy" /><br /><br />
                    <h:outputText value="Indique observaci�n" /><br />
                    <h:inputTextarea value="#{managerCLMatricula.w_observacionFecha}" cols="6" style="width:230px" />
                    <div style="margin-left: 200px; width: 100% " >
                        <a4j:commandButton image="/Imagenes/actions/filesave.png" actionListener="#{managerCLMatricula.guardarProrroga}"
                                           reRender="formDeuda,tabladeudas,btninfo" oncomplete="#{managerCLMatricula.oncomplete}"/>
                    </div>
                </a4j:form>
            </rich:modalPanel>
            <rich:modalPanel id="mp7" width="700" height="490">
                <f:facet name="header">
                    <h:outputText id="titulo" value="#{managerSeccion.tituloReporte}" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp7')" title="Cerrar"/>
                </f:facet>
                <h:form>
                    <table>
                        <tr>
                            <td align="center" valign="middle">
                                <rich:panel>
                                    <a4j:mediaOutput id="reporte" uriAttribute="data" style="width : 650px; height: 420px;" element="object" standby="cargando..." createContent="#{managerSeccion.cargarReporte}" mimeType="application/pdf" value="#{managerSeccion.valorRep}"/>
                                </rich:panel>
                            </td>
                        </tr>
                    </table>
                </h:form>

            </rich:modalPanel>
            <rich:modalPanel id="mp8" width="700" height="490" zindex="4000">
                <f:facet name="header">
                    <h:outputText id="tituloMat" value="#{managerSeccion.tituloReporte}" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp8')" title="Cerrar"/>
                </f:facet>
                <h:form>
                    <table>
                        <tr>
                            <td align="center" valign="middle">
                                <rich:panel>
                                    <a4j:mediaOutput id="reporteMat" uriAttribute="data" style="width : 650px; height: 420px;" element="object" standby="cargando..." createContent="#{managerSeccion.cargarReporteMat}" mimeType="application/pdf" value="#{managerSeccion.valorRep}"/>
                                </rich:panel>
                            </td>
                        </tr>
                    </table>
                </h:form>

            </rich:modalPanel>
                   
        </f:view>
    </body>
</html>