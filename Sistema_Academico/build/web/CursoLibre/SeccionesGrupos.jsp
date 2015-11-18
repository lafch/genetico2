<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Agrupación de Secciones</title>
    </head>
    <body>
        <f:view>
            <jsp:include page="../TablaSistema/Menu.jsp"/>
            <rich:panel>
                <h:form id="frmControles">
                    <table style="font-size:13px; font-family:verdana; width: 100%">
                        <tr>
                            <td width="100%" colspan="10">
                                <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                    <tr>
                                        <td align="left"><h:outputText value="MANTENIMIENTO AGRUPACIÓN DE SECCIONES" style="font-weight: bold;"/></td>
                                            <rich:spacer width="8px"/>

                                            <h:graphicImage value="/Imagenes/actions/filenew.png" title="Nuevo" style="cursor: pointer;">
                                                <a4j:support event="onclick"  reRender="mpMantSecGrupo"
                                                             actionListener="#{managerClSeccionesGrupos.nuevoGrupo}"
                                                             oncomplete="#{managerClSeccionesGrupos.oncomplete}"/>
                                            </h:graphicImage>
                                            <rich:spacer width="8px"/>

                                            <h:commandButton image="/Imagenes/actions/viewmag.png"
                                                             title="Buscar"
                                                             action="#{managerClSeccionesGrupos.listar}"/>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="10"><hr size="1"></td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputText value="Descripción :"/></td>
                            <td colspan="9" align="left"><h:inputText id="b_descripcion" value="#{managerClSeccionesGrupos.descripcion}" style="width: 300px;"/></td>
                        </tr>
                    </table>
                </h:form>

                <h:form id="frmListaGrupos">
                    <rich:datascroller id="paginacion" align="right" for="tbListaGrupos" maxPages="10" style="width : 100%;"/>
                    <rich:spacer height="20px"/>
                    <rich:dataTable id="tbListaGrupos"
                                    onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                    onRowMouseOut="this.style.backgroundColor='#FFFFFF'"
                                    cellpadding="0"
                                    cellspacing="0"
                                    width="100%"
                                    rows="10"
                                    value="#{managerClSeccionesGrupos.lstSeccionesGrupos}"
                                    var="secGrup">
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Id" />
                            </f:facet>
                            <h:outputText value="#{secGrup.secGrupId}"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Grupo" />
                            </f:facet>
                            <h:outputText value="#{secGrup.nomSecGrupo}"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Fecha de Inicio"/>
                            </f:facet>
                            <h:outputText value="#{secGrup.fechaInicio}">
                                <f:convertDateTime pattern="dd '/' MM '/' yyyy" />
                            </h:outputText>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Fecha de Fin"/>
                            </f:facet>
                            <h:outputText value="#{secGrup.fechaFin}">
                                <f:convertDateTime pattern="dd '/' MM '/' yyyy" />
                            </h:outputText>
                        </rich:column>
                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Secciones"/>
                            </f:facet>
                            <f:param id="idSecGrupDet" value="#{secGrup.secGrupId}"/>
                            <a4j:commandButton image="#{secGrup.imagenDetalle}"
                                               title="#{secGrup.tituloDetalle}"
                                               actionListener="#{managerClSeccionesGrupos.mostrarSecciones}"
                                               reRender="tbListaGrupos"/>
                        </rich:column>
                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Editar"/>
                            </f:facet>
                            <f:param id="idSecGrup" value="#{secGrup.secGrupId}"/>
                            <a4j:commandButton image="/Imagenes/actions/icon_edit.png"
                                               title="Modificar" reRender="mpMantSecGrupo"
                                               actionListener="#{managerClSeccionesGrupos.actualizarGrupo}"
                                               oncomplete="#{managerClSeccionesGrupos.oncomplete}"/>
                        </rich:column>
                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Eliminar" />
                            </f:facet>
                            <f:param id="idSecGrupDel" value="#{secGrup.secGrupId}"/>
                            <a4j:commandButton image="/Imagenes/actions/no.png"
                                               title="Eliminar Grupo"
                                               actionListener="#{managerClSeccionesGrupos.eliminarSeccionGrupo}"
                                               oncomplete="#{managerClSeccionesGrupos.oncomplete}"
                                               action="#{managerClSeccionesGrupos.listar}"
                                               reRender="tbListaGrupos"/>
                        </rich:column>
                        <rich:subTable id="detalleMaster" value="#{secGrup.lstSecciones}" var="seccion"
                                       onRowMouseOver="this.style.backgroundColor='#F8F8F8'"
                                       onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                       rendered="#{secGrup.verDetalle}">
                            <rich:column>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Id" />
                                </f:facet>
                                <h:outputText value="#{seccion.secId}" />
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Código" />
                                </f:facet>
                                <h:outputText value="#{seccion.secCodigo}" />
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Área / Módulo / Curso / Taller " />
                                </f:facet>
                                <h:outputText value="#{seccion.nomArea} / #{seccion.nomModulo} / #{seccion.nomCurso} / #{seccion.nomTaller}" />
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Nom. Sección" />
                                </f:facet>
                                <h:outputText value="#{seccion.secNombre}" />
                            </rich:column>
                            <rich:column style="text-align: center;">
                                <f:param id="id_grupo_detalle" value="#{secGrup.secGrupId}"/>
                                <f:param id="id_detalle_delete" value="#{seccion.secId}"/>
                                <a4j:commandButton actionListener="#{managerClSeccionesGrupos.eliminarSeccionDetalle}"
                                                   reRender="tbListaGrupos"
                                                   oncomplete="#{managerClSeccionesGrupos.oncomplete}"
                                                   image="/Imagenes/actions/delete.gif"
                                                   title="Quitar sección del grupo"/>
                            </rich:column>
                        </rich:subTable>
                    </rich:dataTable>
                </h:form>

            </rich:panel>

            <rich:modalPanel id="mpMantSecGrupo" minWidth="650" minHeight="550" height="600"  width="900"
                             autosized="true" zindex="2000">
                <f:facet name="header">
                    <h:outputText value="#{managerClSeccionesGrupos.titModalCrearModifSecGrup}" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png"
                                    style="cursor:pointer" title="Cerrar"
                                    onclick="Richfaces.hideModalPanel('mpMantSecGrupo')" />
                </f:facet>
                <h:form id="formMantSecGrupo">
                    <table width="100%" style="font-size:12px; font-family:verdana">
                        <tr>
                            <td align="right" colspan="2">
                                <a4j:commandButton image="/Imagenes/actions/filesave.png"
                                                   actionListener="#{managerClSeccionesGrupos.insertarActualizarSeccionGrupo}"
                                                   oncomplete="#{managerClSeccionesGrupos.oncomplete}"
                                                   reRender="detalleMaster, tbListaGrupos"/>
                            </td>
                        </tr>
                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                        <tr>
                            <td width="110px" class="tdLabel"><h:outputText value="Nom. Grupo : "/></td>
                            <td>
                                <h:inputText value="#{managerClSeccionesGrupos.clSecGrupo.nomSecGrupo}"  />
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Fecha Inicio : "/></td>
                            <td>
                                <rich:calendar value="#{managerClSeccionesGrupos.clSecGrupo.fechaInicio}" />
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Fecha Fin : "/></td>
                            <td>
                                <rich:calendar value="#{managerClSeccionesGrupos.clSecGrupo.fechaFin}" />
                            </td>
                        </tr>
                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Área : "/></td>
                            <td>
                                <h:selectOneMenu id="seAreas" value="#{managerClSeccionesGrupos.areaId}" >
                                    <f:selectItems value="#{managerClSeccionesGrupos.lstAreas}" />
                                    <a4j:support event="onchange" reRender="formMantSecGrupo" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Módulo : "/></td>
                            <td>
                                <h:selectOneMenu id="seModulos" value="#{managerClSeccionesGrupos.modId}" >
                                    <f:selectItems value="#{managerClSeccionesGrupos.lstModulos}" />
                                    <a4j:support event="onchange" reRender="formMantSecGrupo" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Curso : "/></td>
                            <td>
                                <h:selectOneMenu id="seCursos" value="#{managerClSeccionesGrupos.curId}" >
                                    <f:selectItems value="#{managerClSeccionesGrupos.lstCursos}" />
                                    <a4j:support event="onchange" reRender="formMantSecGrupo" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel"><h:outputText value="Taller : "/></td>
                            <td>
                                <h:selectOneMenu id="seTalleres" value="#{managerClSeccionesGrupos.tallId}" >
                                    <f:selectItems value="#{managerClSeccionesGrupos.lstTalleres}" />
                                    <a4j:support event="onchange" action="#{managerClSeccionesGrupos.listarSeccXTall}" reRender="tbSeccXAdd,pagSecXAdd,pagSecXGrupo,tbSeccsXGrupo" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <rich:dataTable id="tbSeccXAdd"
                                                onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                onRowMouseOut="this.style.backgroundColor='#FFFFFF'"
                                                cellpadding="0"
                                                cellspacing="0"
                                                width="100%"
                                                rows="5"
                                                value="#{managerClSeccionesGrupos.lstSeccXAdd}"
                                                var="secXAdd">
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Id" />
                                        </f:facet>
                                        <h:outputText value="#{secXAdd.secId}"/>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Código" />
                                        </f:facet>
                                        <h:outputText value="#{secXAdd.secCodigo}" />
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Nom. Sección" />
                                        </f:facet>
                                        <h:outputText value="#{secXAdd.secNombre}" />
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Fecha Inicio" />
                                        </f:facet>
                                        <h:outputText value="#{secXAdd.secFinicio}" >
                                            <f:convertDateTime pattern="dd '/' MM '/' yyyy" />
                                        </h:outputText>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Fecha Fin" />
                                        </f:facet>
                                        <h:outputText value="#{secXAdd.secFfin}" >
                                            <f:convertDateTime pattern="dd '/' MM '/' yyyy" />
                                        </h:outputText>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Seleccionar" />
                                        </f:facet>

                                        <f:param name="idSecAGrup" id="idSecAGrup" value="#{secXAdd.secId}"/>
                                        <a4j:commandButton actionListener="#{managerClSeccionesGrupos.agregarSeccAGrupo}"
                                                           reRender="tbSeccXAdd,pagSecXAdd,tbSeccsXGrupo,pagSecXGrupo"
                                                           image="/Imagenes/actions/edit_add.png"
                                                           title="Agregar a Grupo">
                                        </a4j:commandButton>
                                    </rich:column>

                                </rich:dataTable>
                                <rich:datascroller id="pagSecXAdd" align="right" for="tbSeccXAdd" maxPages="10" style="width : 100%;"/>
                            </td>
                        </tr>
                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                        <tr>
                            <td colspan="2">
                                <rich:dataTable id="tbSeccsXGrupo"
                                                onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                onRowMouseOut="this.style.backgroundColor='#FFFFFF'"
                                                cellpadding="0"
                                                cellspacing="0"
                                                width="100%"
                                                rows="5"
                                                value="#{managerClSeccionesGrupos.lstSeccXGrupo}"
                                                var="secXGrup">
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Id. Sección" />
                                        </f:facet>
                                        <h:outputText value="#{secXGrup.secId}"/>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Area" />
                                        </f:facet>
                                        <h:outputText value="#{secXGrup.nomArea}"/>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Mod." />
                                        </f:facet>
                                        <h:outputText value="#{secXGrup.nomModulo}"/>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Cur." />
                                        </f:facet>
                                        <h:outputText value="#{secXGrup.nomCurso}"/>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Tal." />
                                        </f:facet>
                                        <h:outputText value="#{secXGrup.nomTaller}"/>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Seccion." />
                                        </f:facet>
                                        <h:outputText value="#{secXGrup.secNombre}"/>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="F. Inicio" />
                                        </f:facet>
                                        <h:outputText value="#{secXGrup.secFinicio}">
                                            <f:convertDateTime pattern="dd '/' MM '/' yyyy" />
                                        </h:outputText>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="F. Fin" />
                                        </f:facet>
                                        <h:outputText value="#{secXGrup.secFfin}">
                                            <f:convertDateTime pattern="dd '/' MM '/' yyyy" />
                                        </h:outputText>
                                    </rich:column>
                                </rich:dataTable>
                                <rich:datascroller id="pagSecXGrupo" align="right" for="tbSeccsXGrupo" maxPages="10" style="width : 100%;"/>
                            </td>
                        </tr>
                    </table>

                </h:form>
            </rich:modalPanel>
        </f:view>
    </body>
</html>
