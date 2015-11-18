<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Mantenimiento de Sistemas de Evaluacion</title>
    </head>
    <body>
        <f:view >
            <jsp:include page="../TablaSistema/Menu.jsp"></jsp:include>
            <rich:panel style=" width : 100%;" >
                <f:facet name="FACULTADES">
                </f:facet>
                <h:form id="form1" style="width:100%">
                    <table width="100%" style="font-size:10px; font-family:verdana">
                        <tr>
                            <td width="20%" colspan="2" ><strong>LISTADO SISTEMAS DE EVALUACION</strong>
                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right">
                                <a4j:region id="Region">
                                    <h:graphicImage value="/Imagenes/actions/filenew.png" style="border-width: 0px;">
                                        <a4j:support  oncomplete="Richfaces.showModalPanel('mp');" event="onclick" action="#{managerSistemaEvaluacion.Nuevo}" reRender="iId1,iCodigo1,iNombre1,iVigente1,codigo_det,nombre_det,peso_det,tablaSecundaria,barra" />
                                    </h:graphicImage>
                                </a4j:region>
                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right"><h:graphicImage value="/Imagenes/actions/fileprint.png" title="Imprimir Listado"/>
                            </td>
                            <td align=right width="50%">
                                <h:commandButton type="button" id="buscar" value="" action="#{managerSistemaEvaluacion.Seleccionar}" image="/Imagenes/actions/viewmag.png"  title="Buscar"/>
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
                        <tr style=" height : 24px;">
                            <td width="10%" >Descripcion:
                            </td>
                            <td width="30%">
                                <h:inputText id="txtCodigo" value="#{managerSistemaEvaluacion.nombre_f}" style="width : 180px;" maxlength="10"/></td>
                            <td width="50%">
                            </td>
                        </tr>
                        <tr>
                            <td>
                            </td><td></td><td>
                            </td><td></td>
                        </tr>
                        <tr>
                            <td colspan="7" width="100%">
                                <hr width="100%" size="1">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="8">
                            </td>
                        </tr>
                    </table>
                </h:form>
                <h:form style=" width : 100%;">
                    <table style=" width : 100%;">
                        <tr>
                            <td colspan="5" style=" width : 100%">
                                <p align="right"/>
                                <rich:datascroller id="scroller" align="right"  for="tablaMaster" maxPages="8"  style=" width : 100%;"/>
                            </td>
                        </tr>
                    </table>
                    <rich:dataTable id="tablaMaster" rows="10"
                                    onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                    cellpadding="0" cellspacing="0"
                                    width="100%" border="1" value="#{managerSistemaEvaluacion.tablaSisEva}" var="siseva">
                        <h:column>
                            <f:param id="id_siseva_tmp" value="#{siseva.id_s}"/>
                            <f:facet name="header">
                                <h:outputText value="Id" />
                            </f:facet>
                            <h:outputText value="#{siseva.id_s}"/>
                            <f:param value="#{siseva.flag_ver}" id="flag"/>
                            <h:inputHidden value="#{siseva.flag_ver}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Codigo" />
                            </f:facet>
                            <h:outputText value="#{siseva.codigo_s}" />
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Nombre" />
                            </f:facet>
                            <h:outputText value="#{siseva.nombre_s}" />
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Creacion" />
                            </f:facet>
                            <h:outputText value="#{siseva.creacion_s}" />
                        </h:column>
                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Vigente" />
                            </f:facet>
                            <h:graphicImage value="#{siseva.sisevaImgVigente}"/>
                        </rich:column>
                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Mostrar Plantilla" />
                            </f:facet>
                            <a4j:commandButton image="/Imagenes/actions/klipper_dock.png" title="Ver Plantilla"
                                               actionListener="#{managerSistemaEvaluacion.mostrarPlantilla}"
                                               reRender="tbClSisEvaPerPlant" oncomplete="#{managerSistemaEvaluacion.oncomplete}"/>
                        </rich:column>

                        <h:column >
                            <f:facet name="header" >
                                <h:outputText value="Ver" />
                            </f:facet>
                            <p align="right">
                                <h:graphicImage value="#{siseva.imagen}" style="border-width: 0px;cursor:pointer;">
                                    <a4j:support event="onclick" actionListener="#{siseva.Ver}" reRender="tablaMaster"/>
                                </h:graphicImage>
                            </p>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Editar" />
                            </f:facet>
                            <p align="right">
                                <a4j:region id="ProvProc">
                                    <h:graphicImage value="/Imagenes/actions/editpaste.png" style="border-width: 0px;cursor:pointer;">
                                        <a4j:support   oncomplete="javascript:Richfaces.showModalPanel('mp',{width:600, top:80})" event="onclick" actionListener="#{managerSistemaEvaluacion.Editar}" reRender="iId1,iCodigo1,iNombre1,iVigente1,tablaSecundaria,barra"/>
                                    </h:graphicImage>
                                </a4j:region>
                            </p>
                            <f:param id="id_s" value="#{siseva.id_s}" />
                            <f:param id="codigo_s" value="#{siseva.codigo_s}" />
                            <f:param id="nombre_s"   value="#{siseva.nombre_s}" />
                            <f:param id="creacion_s"   value="#{siseva.creacion_s}" />
                            <f:param id="vigente_s"   value="#{siseva.vigente_s}" />
                        </h:column>
                        <h:column >
                            <f:facet name="header" >
                                <h:outputText value="Eliminar" />
                            </f:facet>
                            <p align="right">
                                <h:commandButton  image="/Imagenes/actions/no.png"  title="Eliminar"  actionListener="#{managerSistemaEvaluacion.Eliminar}" action="#{managerSistemaEvaluacion.Seleccionar}"  onclick="javascript:return (confirm('¿Esta realmente seguro de Eliminar?'));"/>
                            </p>
                        </h:column>
                        <rich:subTable value="#{siseva.detalle_s}" var="det" rendered="#{siseva.ver}" id="subtable"
                                       onRowMouseOver="this.style.backgroundColor='#F8F8F8'"
                                       onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'">
                            <%/*<h:column>
                                 <f:facet name="header" >
                                 <h:outputText value="" />
                                 </f:facet>
                                 <h:outputText value=""/>
                                 </h:column>
                                 */%>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Cod. Evaluacion" />
                                </f:facet>
                                <h:outputText value="#{det.codigo_det}" />
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Nom. Evaluacion" />
                                </f:facet>
                                <h:outputText value="#{det.nombre_det}" />
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Pes. Evaluacion" />
                                </f:facet>
                                <h:outputText value="#{det.peso_det}" />
                            </h:column>
                        </rich:subTable>
                    </rich:dataTable>
                </h:form>
            </rich:panel>

            <rich:modalPanel  id="mp" autosized="true" zindex="3000" width="700">
                <f:facet name="header">
                    <h:outputText value="Registro de Sistemas de Evaluacion" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp')" title="Cerrar"/>
                </f:facet>

                <h:form>
                    <table width="100%" style="font-size:10px; font-family:verdana">
                        <tr >
                            <td align="right" colspan="4">
                                <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"  actionListener="#{managerSistemaEvaluacion.Insertar}" action="#{managerSistemaEvaluacion.Seleccionar}" reRender="tablaMaster" oncomplete="javascript:alert('Se guardo el registro correctamente');Richfaces.hideModalPanel('mp')"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3"><hr size="1">
                            </td>
                        </tr>
                        <tr>
                            <td width="30%" >Codigo:</td>
                            <td width="70%">
                                <h:inputHidden id="iId1" value="#{managerSistemaEvaluacion.id_i}"/>
                                <h:inputText id="iCodigo1"  style=" width : 180px;" value="#{managerSistemaEvaluacion.codigo_i}" />
                            </td>
                            <td align="right" width="20%">
                            </td>
                        </tr>
                        <tr>
                            <td width="30%">Nombre:</td>
                            <td width="70%">
                                <h:inputText id="iNombre1" value="#{managerSistemaEvaluacion.nombre_i}" style="width : 180px;"/>
                            </td>
                            <td width="20%"></td>
                        </tr>
                        <tr>
                            <td width="30%">Vigente:</td>
                            <td width="70%">
                                <h:inputText id="iVigente1" value="#{managerSistemaEvaluacion.vigente_i}" style="width : 180px;"/>
                            </td>
                            <td width="20%"></td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                            <td>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4"><hr size="1">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <table>
                                    <tr>
                                        <td>Codigo de Evaluacion
                                        </td>
                                        <td>Nombre de Evaluacion
                                        </td>
                                        <td>Peso de Evaluacion
                                        </td>
                                        <td>
                                            Agrupar
                                        </td>
                                        <td>
                                            Tipo de Nota
                                        </td>
                                        <td>
                                            Etapa
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><h:inputText id="codigo_det" value="#{managerSistemaEvaluacion.codigo_det}"/>
                                        </td>
                                        <td><h:inputText id="nombre_det" value="#{managerSistemaEvaluacion.nombre_det}"/>
                                        </td>
                                        <td><h:inputText id="peso_det" value="#{managerSistemaEvaluacion.peso_det}"/>
                                        </td>
                                        <td><h:inputText id="agrupar_det" value="#{managerSistemaEvaluacion.agruparSisEva}"/></td>
                                        <td>
                                            <h:selectOneMenu id="horTipoClase" value="#{managerSistemaEvaluacion.SETipoNota}">
                                                <f:selectItems value="#{managerSistemaEvaluacion.tipoNota}"/>
                                            </h:selectOneMenu>
                                        </td>
                                        <td>
                                            <h:selectOneMenu id="ESemestre" value="#{managerSistemaEvaluacion.SESemestreEtapa}">
                                                <f:selectItems value="#{managerSistemaEvaluacion.semestreEtapa}"/>
                                            </h:selectOneMenu>
                                        </td>
                                        <td><a4j:commandButton image="/Imagenes/actions/edit_add.png"  action="#{managerSistemaEvaluacion.Agregar}" reRender="tablaSecundaria,barra" />
                                        </td>
                                        <td>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">                               
                                <h:form style=" width : 100%;">
                                    <rich:datascroller id="barra" align="right"  for="tablaSecundaria" maxPages="8"  style=" width : 100%;"/>

                                    <rich:dataTable id="tablaSecundaria" rows="10"
                                                    onRowMouseOver="this.style.backgroundColor='#F1F1F1'" 
                                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                    cellpadding="0" cellspacing="0" width="100%" border="0"  
                                                    value="#{managerSistemaEvaluacion.tablaSisEvaDetalle}" var="siseva_detalle" >
                                        <h:column>
                                            <f:facet name="header">
                                                <h:outputText value="Id" />
                                            </f:facet>
                                            <h:outputText value="#{siseva_detalle.id_det}"/>
                                        </h:column>
                                        <h:column>
                                            <f:facet name="header">
                                                <h:outputText value="Codigo de Sistema " />
                                            </f:facet>
                                            <h:outputText value="#{siseva_detalle.codigo_det}" rendered="#{siseva_detalle.view}"/>
                                            <h:inputText value="#{siseva_detalle.codigo_det}"  rendered="#{siseva_detalle.editable}"/>
                                        </h:column>
                                        <h:column>
                                            <f:facet name="header">
                                                <h:outputText value="Nombre de Sistema" />
                                            </f:facet>
                                            <h:outputText value="#{siseva_detalle.nombre_det}" rendered="#{siseva_detalle.view}"/>
                                            <h:inputText value="#{siseva_detalle.nombre_det}"  rendered="#{siseva_detalle.editable}"/>
                                        </h:column>
                                        <h:column>
                                            <f:facet name="header">
                                                <h:outputText value="Peso" />
                                            </f:facet>
                                            <h:outputText value="#{siseva_detalle.peso_det}" rendered="#{siseva_detalle.view}"/>
                                            <h:inputText value="#{siseva_detalle.peso_det}"  rendered="#{siseva_detalle.editable}"/>
                                        </h:column>
                                        <h:column >
                                            <f:facet name="header">
                                                <h:outputText value="Activo" />
                                            </f:facet>
                                            <h:outputText value="#{siseva_detalle.activo_det}" />
                                        </h:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Tipo Nota"/>
                                            </f:facet>
                                            <h:outputText value="#{siseva_detalle.v_tipo_nota}" rendered="#{siseva_detalle.view_bool}"/>

                                            <h:selectOneMenu value="#{siseva_detalle.SETipoNota}" rendered="#{siseva_detalle.editable_bool}">
                                                <f:selectItems value="#{managerSistemaEvaluacion.tipoNota}"/>
                                            </h:selectOneMenu>
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Etapa Semestre"/>
                                            </f:facet>
                                            <h:outputText value="#{siseva_detalle.v_semestre_etapa}" rendered="#{siseva_detalle.view_bool}"/>

                                            <h:selectOneMenu value="#{siseva_detalle.SESemestreEtapa}" rendered="#{siseva_detalle.editable_bool}">
                                                <f:selectItems value="#{managerSistemaEvaluacion.semestreEtapa}"/>
                                            </h:selectOneMenu>
                                        </rich:column>
                                        <h:column>
                                            <f:facet name="header">
                                                <h:outputText value="Agrupar" />
                                            </f:facet>

                                            <h:outputText value="#{siseva_detalle.agruparSisEva}" rendered="#{siseva_detalle.view_bool}"/>
                                            <h:inputText value="#{siseva_detalle.agruparSisEva}"  rendered="#{siseva_detalle.editable}"/>
                                        </h:column>
                                        <h:column>
                                            <f:facet name="header">
                                                <h:outputText value="Edit"/>
                                            </f:facet>
                                            <a4j:commandButton actionListener="#{siseva_detalle.Edit}" value="" image="/Imagenes/actions/klipper_dock.png" title="Editar" rendered="#{siseva_detalle.view_bool}" reRender="tablaSecundaria"/>
                                            <a4j:commandButton actionListener="#{siseva_detalle.Aceptar}" image="/Imagenes/actions/fileexport.png" title="Aceptar" rendered="#{siseva_detalle.editable_bool}" reRender="tablaSecundaria"/>
                                            <a4j:commandButton actionListener="#{siseva_detalle.Cancelar}" image="/Imagenes/actions/fileclose.png" title="Cancelar" rendered="#{siseva_detalle.editable_bool}" reRender="tablaSecundaria"/>
                                        </h:column>
                                        <h:column>
                                            <f:facet name="header">
                                                <h:outputText value="Eliminar" />
                                            </f:facet>
                                            <f:param id="id_det2" value="#{siseva_detalle.id_det}"/>
                                            <f:param id="codigo_det2" value="#{siseva_detalle.codigo_det}"/>
                                            <f:param id="nombre_det2" value="#{siseva_detalle.nombre_det}"/>
                                            <f:param id="peso_det2" value="#{siseva_detalle.peso_det}"/>
                                            <a4j:commandButton image="/Imagenes/actions/edit_remove.png"  actionListener="#{managerSistemaEvaluacion.Quitar}" reRender="tablaSecundaria" />
                                        </h:column>
                                    </rich:dataTable>
                                </h:form>
                            </td>
                        </tr>
                    </table>

                </h:form>
            </rich:modalPanel>


            <rich:modalPanel id="mpClSisEvaPerPlantilla"
                             minWidth="650" height="400" 
                             zindex="3000" style="overflow-y:scroll">
                <f:facet name="header">
                    <h:outputText value="Plantilla de Sistema de Eval. Personalizado"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" 
                                        onclick="Richfaces.hideModalPanel('mpClSisEvaPerPlantilla')" title="Cerrar"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="frmClSisEvaPerPlantilla">
                    <h:panelGrid width="100%">
                        <rich:dataTable id="tbClSisEvaPerPlant" width="100%" border="0"
                                        onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                        onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                        cellpadding="0" cellspacing="0"
                                        value="#{managerSistemaEvaluacion.lstClSisEvaPerPlant}" var="sepp">
                            <f:param id="id_siseva_per_plant" value="#{sepp.Id}"/>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Id"/>
                                </f:facet>
                                <h:outputText value="#{sepp.id}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="S.E. Detalle"/>
                                </f:facet>
                                <h:outputText value="#{sepp.sisevaDetalle.sisevaDetalleNombre}(#{sepp.sisevaDetalle.sisevaDetallePeso*100}%)"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="S.E. Personalizado"/>
                                </f:facet>
                                <h:outputText value="#{sepp.sisevaPerNombre}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Peso"/>
                                </f:facet>
                                <h:outputText value="#{sepp.sisevaPerPeso}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Orden"/>
                                </f:facet>
                                <h:outputText value="#{sepp.sisevaPerOrden}"/>
                            </rich:column>

                        </rich:dataTable>
                    </h:panelGrid>
                </h:form>
            </rich:modalPanel>               
        </f:view>
    </body>
</html>  
