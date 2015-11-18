<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Mantenimiento de actividades</title>
    </head>
    <body>
        <f:view >
            <jsp:include page="../TablaSistema/Menu.jsp"></jsp:include>
            <rich:panel style=" width : 100%;" >
                <f:facet name="ACTIVIDADES"></f:facet>
                <h:form id="form1" style="width:100%">
                    <table width="100%" style="font-size:13px; font-family:verdana">
                        <tr>
                            <td width="20%" colspan="2" ><strong>MANTENIMIENTO DE ACTIVIDADES</strong></td>
                            <td width="30%"></td>
                            <td width="30%" align="right"></td>
                            <td width="30%" align="right">
                                <h:graphicImage value="/Imagenes/actions/filenew.png" style="border-width: 0px;cursor: pointer;">
                                    <a4j:support event="onclick" onsubmit="hideDiv({duration:0.1});"
                                                 oncomplete="Richfaces.showModalPanel('mpActividadNuevo',{width:600, top:50})"
                                                 action="#{managerActividad.nuevo}"
                                                 reRender="iTitulo,iFecha,iFechaIni,iFechaFin,iImagen,iPublicado,iAlcance,iDescripcion" />
                                </h:graphicImage>
                            </td>
                            <td width="30%"></td>
                            <td align=right width="50%">
                                <a4j:commandButton type="button" id="buscar" value=""
                                                   title="Buscar" reRender="tableData"
                                                   action="#{managerActividad.mostrarPorFecha}"
                                                   image="/Imagenes/actions/viewmag.png"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="7" width="100%"><hr width="100%" size="1" />
                        </tr>
                        <tr>
                            <td width="20%" colspan="2" />
                            <td width="30%" />
                            <td width="50%"/>
                        </tr>
                        <tr style=" height : 24px;">
                            <td width="10%" >Fecha: </td>
                            <td width="30%"/>
                            <rich:calendar datePattern="dd-MM-yyyy" value="#{managerActividad.paramDate}" />
                            <td width="50%" />
                        </tr>
                        <tr>
                            <td colspan="7" width="100%"><hr width="100%" size="1"></td>
                        </tr>
                    </table>
                </h:form>
                <h:form id="tableData" style=" width : 100%;">
                    <table style="width: 100%">
                        <tr>
                            <td align="right">
                                <rich:datascroller id="scrollTableMaster" for="tablaMaster"
                                                   align="rigth" maxPages="8"/>
                            </td>
                        </tr>
                    </table>

                    <rich:dataTable id="tablaMaster" rows="10"
                                    onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                    cellpadding="0" cellspacing="0"
                                    width="100%" border="0" value="#{managerActividad.listaActividades}" var="actividades">
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Id" />
                            </f:facet>
                            <h:outputText value="#{actividades.b_id}"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Fecha" />
                            </f:facet>
                            <h:outputText value="#{actividades.b_fecha}" >
                                <f:convertDateTime pattern="dd  'de'  MMMMMM  'del'  yyyy"/>
                            </h:outputText>
                        </rich:column>

                        <rich:column >
                            <f:facet name="header">
                                <h:outputText value="Titulo" />
                            </f:facet>
                            <h:outputText value="#{actividades.b_titulo}" />
                        </rich:column>

                        <rich:column >
                            <f:facet name="header">
                                <h:outputText value="Fecha inicio"/>
                            </f:facet>
                            <h:outputText value="#{actividades.b_fechaIni}" >
                                <f:convertDateTime pattern="dd  'de'  MMMMMM  'del'  yyyy  'a' 'las'  hh:mm:ss a" />
                            </h:outputText>
                        </rich:column>
                        <rich:column >
                            <f:facet name="header">
                                <h:outputText value="Fecha fin"/>
                            </f:facet>
                            <h:outputText value="#{actividades.b_fechaFin}" >
                                <f:convertDateTime pattern="dd  'de'  MMMMMM  'del'  yyyy  'a' 'las'  hh:mm:ss a" />
                            </h:outputText>
                        </rich:column>
                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Publicado" />
                            </f:facet>
                            <f:param id="p_id" value="#{actividades.b_id}" />
                            <f:param id="p_est_pub" value="#{actividades.b_estPubl}" />
                            <a4j:commandButton image="#{actividades.imagenPublicado}" title="#{actividades.title}"
                                               actionListener="#{managerActividad.setearParametros}"
                                               oncomplete="Richfaces.showModalPanel('mpPublicar',{width:300, top:200})"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Alcance" />
                            </f:facet>
                            <h:outputText value="#{actividades.b_alcance}" />
                        </rich:column>
                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Editar" />
                            </f:facet>
                            <a4j:commandButton image="/Imagenes/actions/editpaste.png"
                                               actionListener="#{managerActividad.seleccionarActividad}"
                                               oncomplete="javascript:Richfaces.showModalPanel('mpActividadNuevo',{top:80})"
                                               reRender="iTitulo,imgLoaded,iFecha,iFechaIni,iFechaFin,iImagen,iPublicado,iAlcance,iDescripcion"  />
                        </rich:column>
                        <rich:column style="text-align: center;">
                            <f:facet name="header" >
                                <h:outputText value="Eliminar" />
                            </f:facet>
                            <h:commandButton
                                image="/Imagenes/actions/no.png"
                                title="Eliminar"
                                actionListener="#{managerActividad.eliminarActividad}"
                                action="#{managerActividad.mostrarPorFecha}"
                                onclick="javascript:return (confirm('¿Esta realmente seguro de Eliminar?'));"/>
                        </rich:column>
                    </rich:dataTable>
                </h:form>

                <rich:modalPanel  id="mpActividadNuevo" autosized="true"
                                  zindex="2000" minWidth="550">
                    <f:facet name="header">
                        <h:outputText value="Registro de Actividades" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png"
                                        style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mpActividadNuevo')"
                                        title="Cerrar"/>
                    </f:facet>
                    <a4j:form id="formActividad">
                        <table width="100%">
                            <tr>
                                <td colspan="3" width="100%" align="right">
                                    <a4j:commandButton
                                        image="/Imagenes/actions/filesave.png"
                                        title="Guardar"
                                        actionListener="#{managerActividad.manipularDatos}"
                                        reRender="tablaMaster, scrollTableMaster"
                                        oncomplete="#{managerActividad.oncomplete}"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3"><hr width="100%"/></td>
                            </tr>
                            <tr>
                                <td><h:outputLabel value="Titulo"/></td>
                                <td width="65%"><h:inputText id="iTitulo"
                                             value="#{managerActividad.b_titulo}" />
                                </td>
                                <td rowspan="5">
                                    <rich:panel id="imgLoaded">
                                        <div align="center">
                                            <h:outputText value="No hay imagen cargada"
                                                          rendered="#{managerActividad.b_img==null}" />
                                            <a4j:mediaOutput element="img" mimeType="#{managerActividad.b_mime}"
                                                             createContent="#{managerActividad.avatarContacto}"
                                                             value="imgActividad" cacheable="false"
                                                             style="width:100px; height:100px;"
                                                             rendered="#{managerActividad.b_img!=null}">
                                                <f:param value="#{managerActividad.timeStamp}" name="time"/>
                                            </a4j:mediaOutput>
                                            <br /><rich:spacer height="5px"/>
                                            <a4j:commandButton actionListener="#{managerActividad.limpiarImagenActividad}"
                                                               reRender="imgLoaded, formActividad"
                                                               value="Borrar imagen"
                                                               rendered="#{managerActividad.b_img!=null}" />
                                        </div>
                                    </rich:panel>
                                </td>
                            </tr>
                            <tr>
                                <td><h:outputLabel value="Fecha"/></td>
                                <td><rich:calendar id="iFecha" datePattern="dd-MM-yyyy"
                                               value="#{managerActividad.b_fecha}"/></td>
                            </tr>
                            <tr>
                                <td><h:outputLabel value="Fecha inicio"/></td>
                                <td><rich:calendar id="iFechaIni" datePattern="dd-MM-yyyy hh:mm a"
                                               showApplyButton="true"
                                               value="#{managerActividad.b_fechaIni}"/></td>
                            </tr>
                            <tr>
                                <td><h:outputLabel value="Fecha fin"/></td>
                                <td><rich:calendar id="iFechaFin" datePattern="dd-MM-yyyy hh:mm a"
                                               showApplyButton="true"
                                               value="#{managerActividad.b_fechaFin}"/></td>
                            </tr>
                            <tr>
                                <td><h:outputLabel value="tipo"/></td>
                                <td><h:selectOneMenu id="iTipo" value="#{managerActividad.b_tipo}">
                                        <f:selectItems value="#{managerActividad.itemComboTipo}"/>
                                    </h:selectOneMenu></td>
                            </tr>
                            <tr>
                                <td><h:outputLabel value="Imagen"/></td>
                                <td colspan="2"><rich:fileUpload id="iImagen" maxFilesQuantity="1"
                                                 fileUploadListener="#{managerActividad.cargaImagen}"
                                                 listHeight="58px" listWidth="250px"
                                                 immediateUpload="false"
                                                 acceptedTypes="jpg, jpeg, gif">
                                        <a4j:support event="onuploadcomplete" reRender="imgLoaded"/>
                                    </rich:fileUpload></td>
                            </tr>
                            <tr>
                                <td><h:outputLabel value="Publicado" /></td>
                                <td><h:selectBooleanCheckbox id="iPublicado"
                                                         value="#{managerActividad.b_publicado}"/></td>
                            </tr>
                            <tr>
                                <td><h:outputLabel value="Alcance" /></td>
                                <td colspan="2">
                                    <h:selectManyCheckbox id="iAlcance"
                                                          value="#{managerActividad.b_arrayAlcances}">
                                        <f:selectItems value="#{managerActividad.itemCheckAlcance}" />
                                    </h:selectManyCheckbox></td>
                            </tr>
                            <tr>
                                <td><h:outputLabel value="Descripcion"/></td>
                                <td colspan="2"><h:inputTextarea id="iDescripcion" cols="60" rows="5"
                                                 value="#{managerActividad.b_descripcion}"/></td>
                            </tr>
                        </table>
                    </a4j:form>
                </rich:modalPanel>

                <rich:modalPanel  id="mpPublicar"
                                  minHeight="150" minWidth="100"
                                  height="150" width="100"
                                  zindex="2000" onshow="showDiv();">
                    <rich:effect  name="hideDiv"  for="contentDivPublicar" type="Fade" />
                    <rich:effect  name="showDiv"  for="contentDivPublicar" type="Appear" />
                    <div id="contentDivPublicar">
                        <f:facet name="header">
                            <h:outputText value="Publicar la actividad" />
                        </f:facet>
                        <f:facet name="controls">
                            <h:graphicImage value="/Imagenes/actions/stop.png"
                                            style="cursor:pointer"
                                            onclick="Richfaces.hideModalPanel('mpPublicar')"
                                            title="Cerrar"/>
                        </f:facet>
                        <center>
                            <h:form id="formPublicar">
                                <h:outputLabel >
                                    <h2>&iquest;Esta seguro(a) de realizar esta acci&oacute;n?</h2><br>
                                </h:outputLabel>
                                <h:commandButton value="Aceptar" action="#{managerActividad.publicarActividad}"/>
                                <rich:spacer width="20px" />
                                <h:commandButton value="Cancelar" onclick="Richfaces.hideModalPanel('mpPublicar')" />
                            </h:form>
                        </center>
                    </div>
                </rich:modalPanel>
            </rich:panel>
        </f:view>
    </body>
</html>
