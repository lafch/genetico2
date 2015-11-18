<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Mantenimiento de Estructura de Pagos</title>
        <script type="text/javascript">
            function getRightTop(ref) {
                var position = new Object();
                position.top = 0; //ref.offsetTop;
                position.left =0; // ref.offsetLeft+ref.clientWidth+6;
                return position;
            }
        </script> 
    </head>
    <body>
        <f:view >
            <jsp:include page="../TablaSistema/Menu.jsp"></jsp:include>
            <rich:panel style=" width : 100%;" >
                <f:facet name="FACULTADES">
                </f:facet>
                <h:form id="form1" style="width:100%">
                    <table width="100%" style="font-size:10px; font-family:verdana"  border="0" cellspacing="0" cellpadding="0">
                        <tr >
                            <td width="20%" colspan="2" ><strong>ESTRUCTURA DE PAGOS</strong>
                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right">
                                <a4j:region id="Region">
                                    <h:graphicImage value="/Imagenes/actions/filenew.png" style="border-width: 0px;cursor: pointer;" title="Nuevo">
                                        <a4j:support oncomplete="javascript:Richfaces.showModalPanel('mp',{width:600, top:80})" event="onclick" action="#{managerEstructuraPago.Nuevo}" reRender="iId1,iFac1,iEsp1,iSem1,iSem2,SemeD,iDes1,iDes2,FacuI,EspeI,SemeI,monto_det,sem_det,fecha_det,desc_det,tablaSecundaria"/>
                                    </h:graphicImage>
                                </a4j:region>

                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right">
                                <h:graphicImage value="/Imagenes/actions/fileprint.png"
                                                style="cursor: pointer;"
                                                title="Imprimir Listado"/>
                            </td>
                            <td align=right width="50%">
                                <h:commandButton type="button" id="buscar" value="" action="#{managerEstructuraPago.Seleccionar}" image="/Imagenes/actions/viewmag.png"  title="Buscar"/>
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
                        <tr>
                            <td width="10%">Facultad:</td>
                            <td >
                                <h:selectOneMenu id="FacuBuscar" value="#{managerEstructuraPago.c_id_facu}"  style=" width : 180px;">
                                    <f:selectItems value="#{managerEstructuraPago.comboFacultad_buscar}" />
                                    <a4j:support event="onchange"  action="#{managerEstructuraPago.setearFacultad_buscar}" reRender="EspeBuscar"/>
                                </h:selectOneMenu>

                            </td>
                            <td colspan="4"></td>
                        </tr>
                        <tr>
                            <td>Especialidad:</td>
                            <td>
                                <h:selectOneMenu id="EspeBuscar" value="#{managerEstructuraPago.c_id_espe}" style=" width : 180px;">
                                    <f:selectItems value="#{managerEstructuraPago.comboEspecialidad_buscar}" />
                                </h:selectOneMenu>
                            </td>
                            <td width="20%" colspan="4"></td>
                        </tr>
                        <tr>
                            <td>Semestre:</td>
                            <td>
                                <h:selectOneMenu id="Semestre" value="#{managerEstructuraPago.c_id_semestre}" style=" width : 180px;">
                                    <f:selectItems value="#{managerEstructuraPago.comboSemestre}"/>
                                </h:selectOneMenu>
                            </td>
                            <td width="20%" colspan="4"></td>
                        </tr>
                        <tr>
                            <td></td><td></td><td>
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
                                <rich:datascroller id="barra" align="right"  for="tablaMaster" maxPages="8"  style=" width : 100%;"/>
                            </td>
                        </tr>
                    </table>
                    <rich:dataTable id="tablaMaster" rows="10"
                                    onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                    cellpadding="0" cellspacing="0"
                                    width="100%" border="0" value="#{managerEstructuraPago.tablaSisEva}" var="siseva">
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Id" />
                            </f:facet>
                            <h:outputText value="#{siseva.id_s}"/>
                            <f:param value="#{siseva.flag_ver}" id="flag"/>
                            <h:inputHidden value="#{siseva.flag_ver}" />
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Nombre" />
                            </f:facet>
                            <h:outputText value="#{siseva.codigo_s}" />
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Facultad" />
                            </f:facet>
                            <h:outputText value="#{siseva.fac_s}" />
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Especialidad" />
                            </f:facet>
                            <h:outputText value="#{siseva.esp_s}" />
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Semestre Ejecución" />
                            </f:facet>
                            <h:outputText value="#{siseva.sem_s}" />
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Semestre Destino" />
                            </f:facet>
                            <h:outputText value="#{siseva.sem_d}" />
                        </rich:column>
                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Publicado" />
                            </f:facet>
                            <h:outputText value="#{siseva.publicado_s}" />
                        </rich:column>
                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="Edit" />
                            </f:facet>
                            <a4j:region id="ProvProc" >
                                <h:graphicImage value="#{siseva.ima_edit}" style="border-width: 0px;cursor: pointer;" height="16" >
                                    <a4j:support oncomplete="#{siseva.disabled_edit}" event="onclick" actionListener="#{managerEstructuraPago.Editar}"
                                                 reRender="iId1,iFac1,iEsp1,iSem1,iSem2,iDes1,iDes2,FacuI,EspeI,SemeI,SemeD,monto_det,sem_det,fecha_det,desc_det,tablaSecundaria"/>
                                </h:graphicImage>
                            </a4j:region>

                            <f:param id="view_s" value="#{siseva.view}" />
                            <f:param id="id_s" value="#{siseva.id_s}" />
                            <f:param id="fac_s" value="#{siseva.fac_s}" />
                            <f:param id="esp_s"   value="#{siseva.esp_s}" />
                            <f:param id="fac_id_s" value="#{siseva.fac_id_s}" />
                            <f:param id="esp_id_s"   value="#{siseva.esp_id_s}" />
                            <f:param id="sem_s"   value="#{siseva.sem_s}" />
                            <f:param id="sem_id_s"   value="#{siseva.sem_id_s}" />
                            <f:param id="des_s"   value="#{siseva.codigo_s}" />
                            <f:param id="sem_d"   value="#{siseva.sem_d}" />
                            <f:param id="sem_id_d"   value="#{siseva.sem_id_d}" />
                        </rich:column>
                        <rich:column style="text-align: center;">
                            <f:facet name="header" >
                                <h:outputText value="Ver" />
                            </f:facet>
                            <h:graphicImage value="#{siseva.imagen}" style="border-width: 0px;cursor: pointer;">
                                <a4j:support event="onclick" actionListener="#{siseva.Ver}" reRender="tablaMaster"/>
                            </h:graphicImage>
                        </rich:column>
                        <rich:column style="text-align: center;">
                            <f:facet name="header" >
                                <h:outputText value="Publicar" />
                            </f:facet>
                            <h:commandButton  image="#{siseva.ima_publi}"  title="Publicar"
                                              disabled="#{siseva.disabled_publi}"
                                              actionListener="#{managerEstructuraPago.Publicar}"
                                              action="#{managerEstructuraPago.Seleccionar}"
                                              onclick="javascript:return (confirm('¿Esta realmente seguro de publicar esta Estructura de Pago?'));"/>
                        </rich:column>

                        <rich:column style="text-align: center;">
                            <f:facet name="header" >
                                <h:outputText value="Delete" />
                            </f:facet>
                            <h:commandButton  image="#{siseva.ima_delete}"  title="Eliminar"
                                              disabled="#{siseva.disabled_delete}"
                                              actionListener="#{managerEstructuraPago.Eliminar}"
                                              action="#{managerEstructuraPago.Seleccionar}"
                                              onclick="javascript:return (confirm('¿Esta realmente seguro de Eliminar?'));"/>
                        </rich:column>

                        <rich:subTable value="#{siseva.detalle_s}" var="det" rendered="#{siseva.ver}" id="subtable"
                                       onRowMouseOver="this.style.backgroundColor='#F8F8F8'"
                                       onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'">
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Descripción" />
                                </f:facet>
                                <h:outputText value="#{det.nombre_det}" />
                            </rich:column>

                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Codigo de Con. Pago" />
                                </f:facet>
                                <h:outputText value="#{det.codigo_ref_det}" />
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Con. Pago Id" />
                                </f:facet>
                                <h:outputText value="#{det.con_pag_det}" />
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Monto de Referencia" />
                                </f:facet>
                                <h:outputText value="#{det.monto_ref_det}" />
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Fecha Pago" />
                                </f:facet>
                                <h:outputText value="#{det.fecha_det}" />
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Monto a Pagar" />
                                </f:facet>
                                <h:outputText value="#{det.monto_det}" />
                            </rich:column>
                            <rich:column colspan="5"/>
                        </rich:subTable>
                    </rich:dataTable>
                </h:form>

                <rich:modalPanel  id="mp" minHeight="450" minWidth="800" height="450" width="600" zindex="2000">

                    <f:facet name="header">
                        <h:outputText value="Registro de Estructura de Pagos" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp')" title="Cerrar"/>
                    </f:facet>

                    <h:form>
                        <table width="100%" style="font-size:10px; font-family:verdana">
                            <tr >
                                <td align="right" colspan="4">
                                    <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"  actionListener="#{managerEstructuraPago.Insertar}" action="#{managerEstructuraPago.Seleccionar}" reRender="tablaMaster, barra" oncomplete="#{managerEstructuraPago.oncomplete}"/>

                                </td>
                            </tr>
                            <tr>
                                <td colspan="3"><hr size="1">
                                </td>
                            </tr>
                            <tr>
                                <td width="30%" >Facultad:</td>
                                <td width="70%">
                                    <h:inputHidden id="iId1" value="#{managerEstructuraPago.id_i}"/>
                                    <h:inputHidden id="iFac1"  value="#{managerEstructuraPago.fac_i}" />
                                    <a4j:region id="FACU_I">
                                        <h:selectOneMenu id="FacuI" value="#{managerEstructuraPago.fac_id_i}">
                                            <f:selectItems value="#{managerEstructuraPago.comboFacultad_i}" />
                                            <a4j:support event="onchange"  action="#{managerEstructuraPago.setearFacultad_i}" reRender="EspeI"/>
                                        </h:selectOneMenu>
                                    </a4j:region>
                                </td>
                                <td align="right" width="20%">
                                </td>
                            </tr>
                            <tr>
                                <td width="30%">Especialidad:</td>
                                <td width="70%">
                                    <h:inputHidden id="iEsp1" value="#{managerEstructuraPago.esp_i}" />
                                    <h:selectOneMenu id="EspeI" value="#{managerEstructuraPago.esp_id_i}">
                                        <f:selectItems value="#{managerEstructuraPago.comboEspecialidad_i}"/>
                                    </h:selectOneMenu>
                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td width="30%">Semestre Ejecuci&oacute;n:</td>
                                <td width="70%">
                                    <h:inputHidden id="iSem1" value="#{managerEstructuraPago.sem_i}" />
                                    <h:selectOneMenu id="SemeI" value="#{managerEstructuraPago.sem_id_i}">
                                        <f:selectItems value="#{managerEstructuraPago.comboSemestre_i}"/>
                                    </h:selectOneMenu>
                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td width="30%">Semestre Destino:</td>
                                <td width="70%">
                                    <h:inputHidden id="iSem2" value="#{managerEstructuraPago.sem_d}" />
                                    <h:selectOneMenu id="SemeD" value="#{managerEstructuraPago.sem_id_d}">
                                        <f:selectItems value="#{managerEstructuraPago.comboSemestre_i}"/>
                                    </h:selectOneMenu>
                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td width="30%">Nombre</td>
                                <td width="70%">
                                    <h:inputHidden id="iDes1" value="#{managerEstructuraPago.des_i}" />
                                    <h:inputTextarea id="iDes2" value="#{managerEstructuraPago.des_i}" />
                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td colspan="4"><hr size="1"></td>
                            </tr>
                            <tr>
                                <td colspan="4">
                                    <table>
                                        <tr>
                                            <td>Concepto de Pago</td>
                                            <td>Monto a Pagar</td>
                                            <td>Fecha de Pago</td>
                                            <td>Descripcion</td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <h:selectOneMenu id="sem_det" value="#{managerEstructuraPago.con_pag_id_det}">
                                                    <f:selectItems value="#{managerEstructuraPago.comboConceptoPago}"/>
                                                </h:selectOneMenu>
                                            </td>
                                            <td><h:inputText id="monto_det" value="#{managerEstructuraPago.monto_det}"/>
                                            </td>
                                            <td>
                                                <a4j:outputPanel id="calendar" layout="block">
                                                    <rich:calendar  id="fecha_det"  value="#{managerEstructuraPago.fecha_det}" datePattern="yyyy-MM-dd"  />
                                                </a4j:outputPanel>
                                            </td>
                                            <td><h:inputText id="desc_det" value="#{managerEstructuraPago.nombre_det}"/>
                                            </td>
                                            <td><a4j:commandButton image="/Imagenes/actions/edit_add.png"  action="#{managerEstructuraPago.Agregar}" reRender="tablaSecundaria,ScrollerSecundaria" />
                                            </td>
                                            <td>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">
                                    <rich:datascroller id="ScrollerSecundaria" for="tablaSecundaria" maxPages="8"  />
                                    <rich:dataTable id="tablaSecundaria" rows="10" onRowMouseOver="this.style.backgroundColor='#F1F1F1'" onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                    cellpadding="0" cellspacing="0" width="100%" border="0" value="#{managerEstructuraPago.tablaSisEvaDetalle}" var="siseva_detalle">
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Id" />
                                            </f:facet>
                                            <h:outputText value="#{siseva_detalle.id_det}"/><br>
                                            <h:inputHidden value="#{siseva_detalle.con_pag_id_det}"/>
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Descripción" />
                                            </f:facet>
                                            <h:outputText value="#{siseva_detalle.nombre_det}" rendered="#{siseva_detalle.view}"/>
                                            <h:inputText value="#{siseva_detalle.nombre_det}"  rendered="#{siseva_detalle.editable}" style=" width : 146px;"/>
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Codigo Con. Pag. " />
                                            </f:facet>
                                            <h:outputText value="#{siseva_detalle.codigo_ref_det}" rendered="#{siseva_detalle.view}"/>
                                            <h:inputText value="#{siseva_detalle.codigo_ref_det}"  rendered="#{siseva_detalle.editable}" style=" width : 146px;"/>
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Con Pago" />
                                            </f:facet>
                                            <h:outputText value="#{siseva_detalle.con_pag_det}" rendered="#{siseva_detalle.view}"/>
                                            <h:inputText value="#{siseva_detalle.con_pag_det}"  rendered="#{siseva_detalle.editable}"/>
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Monto de Referencia" />
                                            </f:facet>
                                            <h:outputText value="#{siseva_detalle.monto_ref_det}" rendered="#{siseva_detalle.view}"/>
                                            <h:inputText value="#{siseva_detalle.monto_ref_det}"  rendered="#{siseva_detalle.editable}"/>
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Fecha de Pago" />
                                            </f:facet>
                                            <h:outputText value="#{siseva_detalle.fecha_det}"  rendered="#{siseva_detalle.view}">
                                                <f:convertDateTime pattern="yyyy-MM-dd"/>
                                            </h:outputText>
                                            <h:inputText value="#{siseva_detalle.monto_ref_det}"  rendered="#{siseva_detalle.editable}">
                                                <f:convertDateTime pattern="yyyy-MM-dd"/>
                                            </h:inputText>
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Monto a Pagar" />
                                            </f:facet>
                                            <h:outputText value="#{siseva_detalle.monto_det}" />
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Eliminar" />
                                            </f:facet>
                                            <f:param id="id_det" value="#{siseva_detalle.id_det}"/>
                                            <f:param id="codigo_det" value="#{siseva_detalle.codigo_det}"/>
                                            <f:param id="nombre_det" value="#{siseva_detalle.nombre_det}"/>
                                            <f:param id="peso_det" value="#{siseva_detalle.peso_det}"/>
                                            <a4j:commandButton image="/Imagenes/actions/edit_remove.png"  actionListener="#{managerEstructuraPago.Quitar}" reRender="tablaSecundaria" />
                                        </rich:column>
                                    </rich:dataTable>
                                </td>
                            </tr>
                        </table>
                    </h:form>
                </rich:modalPanel>

            </rich:panel>

        </f:view>
    </body>
</html>  
