<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Mantenimiento de Curso Aperturado</title>
        <script type="text/javascript">
            function getRightTop(ref) {
                var position = new Object();
                position.top = 0; //ref.offsetTop;
                position.left = 0; // ref.offsetLeft+ref.clientWidth+6;
                return position;
            }
        </script> 
    </head>
    <body>
        <f:view >
            <jsp:include page="../TablaSistema/Menu.jsp"></jsp:include>
            <rich:panel style=" width : 100%; background:#FFF; border:1px !important;" >
                <f:facet name="FACULTADES">
                </f:facet>
                <h:form id="form1" style="width:100%">
                    <table width="100%" style="font-size:10px; font-family:verdana" cellspacing="0" border="0">
                        <tr >
                            <td width="20%" colspan="2" ><strong>APERTURA DE CURSOS</strong>
                            </td>
                            <td width="30%"></td>
                            <td width="30%" >
                            </td>
                            <%--<td width="30%" align="right"><h:graphicImage value="/Imagenes/actions/fileprint.png" title="Imprimir Listado"/>
                            </td>--%>
                            <td align=right width="50%">
                                <h:commandButton type="button" id="refrescar" value="" action="#{managerCursoAperturado.cargarArbol}" image="/Imagenes/actions/reload.png"  title="Buscar"/>
                            </td>
                            <td>
                                <h:commandButton type="button" id="buscar" value="" action="#{managerCursoAperturado.cargarArbol}" image="/Imagenes/actions/viewmag.png"  title="Buscar"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="8">
                                <hr size="1">
                            </td>
                        </tr>
                    </table>
                </h:form>
                <h:form style=" width : 100%;">
                    <table width="100%"  cellspacing="0" border="0">
                        <tr>
                            <td style=" width : 250px;font-size:10px; font-family:verdana;vertical-align:top;">
                                <rich:tree value="#{managerCursoAperturado.arbol}"  ajaxSubmitSelection="true"
                                           switchType="ajax" nodeSelectListener="#{managerCursoAperturado.Seleccion}"
                                           style="font-size:10px; font-family:verdana"  var="item"
                                           reRender="facultad,semestre_f,tablaMaster"/>
                            </td>
                            <td style="vertical-align:top;" colspan="3" >
                                <table width="100%"  cellspacing="0" border="0">
                                    <tr>
                                        <td>
                                            <h:selectOneMenu id="semestre_f" value="#{managerCursoAperturado.semestre_f}">
                                                <f:selectItems value="#{managerCursoAperturado.comboSemestres}"/>
                                                <a4j:support event="onchange" actionListener="#{managerCursoAperturado.Seleccion}" reRender="tablaMaster"></a4j:support>
                                            </h:selectOneMenu>
                                        </td>
                                        <td align="right">
                                            <h:outputText value="#{managerCursoAperturado.arbol_facultad}-#{managerCursoAperturado.arbol_especialidad}-#{managerCursoAperturado.arbol_plan}-#{managerCursoAperturado.arbol_ciclo}"  id="facultad"/>
                                            <br>
                                        </td>
                                    </tr>
                                </table>
                                <br>
                                <hr size="1">
                                <h:outputText escape="false" value="Cursos del Ciclo Seleccionado:" />
                                <br>
                                <rich:dataTable id="tablaMaster" rows="30"
                                                onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                cellpadding="0" cellspacing="0"
                                                width="100%" border="0" value="#{managerCursoAperturado.listaCursos}" var="cursos">
                                    <%--<h:column >
                                                <f:facet name="header">
                                                <h:outputText value="Id_Curso_Aperturado" />
                                                </f:facet>
                                                <h:outputText value="#{cursos.curape_id_s}" />
                                                </h:column>
                                    --%>
                                    <h:column >
                                        <f:facet name="header">
                                            <h:outputText value="Ciclo" />
                                        </f:facet>
                                        <h:outputText value="#{cursos.cur_ciclo_s}" />
                                    </h:column>

                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Codigo" />
                                        </f:facet>
                                        <h:outputText value="#{cursos.cur_codigo_s}" />
                                    </h:column>
                                    <h:column >
                                        <f:facet name="header">
                                            <h:outputText value="Nombre" />
                                        </f:facet>
                                        <h:outputText value="#{cursos.cur_nombre_s}" />
                                    </h:column>
                                    <h:column >
                                        <f:facet name="header">
                                            <h:outputText value="Semestre" />
                                        </f:facet>
                                        <h:outputText value="#{cursos.cur_semestre_s_d}" />
                                    </h:column>
                                    <h:column >
                                        <f:facet name="header">
                                            <h:outputText value="Aperturado" />
                                        </f:facet>
                                        <h:outputText value="#{cursos.cur_aperturado_s}" />
                                    </h:column>
                                    <h:column >
                                        <f:facet name="header">
                                            <h:outputText value="Sis. Eva" />
                                        </f:facet>
                                        <h:outputText value="#{cursos.cur_evaluacion_s}" />
                                    </h:column>

                                    <h:column >
                                        <f:facet name="header" >
                                            <h:outputText value="" />
                                        </f:facet>
                                        <p align="right">
                                            <h:graphicImage value="#{cursos.cur_candado_s}" style="border-width: 0px;cursor:pointer;">
                                                <a4j:support event="onclick" actionListener="#{managerCursoAperturado.Detallar}" oncomplete="#{managerCursoAperturado.efecto};Richfaces.showModalPanel('mp',{width:700, top:50});" reRender="id,id_curape,codigo,nombre,ciclo,sisevaluacion,promedios,aperturado,idplancur,tablaSecundaria,barra,personal"/>
                                            </h:graphicImage>
                                            <f:param id="p_id_curape" value="#{cursos.curape_id_s}" />
                                            <f:param id="p_id" value="#{cursos.cur_id_s}" />
                                            <f:param id="p_plancur_id" value="#{cursos.plancur_id_s}" />
                                            <f:param id="p_ciclo" value="#{cursos.cur_ciclo_s}" />
                                            <f:param id="p_codigo"   value="#{cursos.cur_codigo_s}" />
                                            <f:param id="p_nombre"   value="#{cursos.cur_nombre_s}" />
                                            <f:param id="p_semestre" value="#{cursos.cur_semestre_s}"/>
                                            <f:param id="p_aperturado" value="#{cursos.cur_aperturado_s}"/>
                                            <f:param id="p_evaluacion" value="#{cursos.cur_evaluacion_id_s}"/>
                                        </p>
                                    </h:column>
                                    <h:column >
                                        <f:facet name="header" >
                                            <h:outputText value="" />
                                        </f:facet>
                                        <p align="right">
                                            <h:graphicImage value="/Imagenes/actions/secciona.png" style="border-width: 0px;cursor:pointer;">
                                                <a4j:support event="onclick" actionListener="#{managerCursoAperturado.Detallar}" oncomplete="Richfaces.showModalPanel('mp2',{width:800, top:50});" reRender="icbSeccion,id1,id_curape1,codigo1,nombre1,ciclo1,idplancur1,sisevaluacion1,aperturado1,tablaSecundaria1, barra2"/>
                                            </h:graphicImage>
                                        </p>
                                    </h:column>
                                    <h:column >
                                        <f:facet name="header" >
                                            <h:outputText value="" />
                                        </f:facet>
                                        <p align="right">
                                            <%/*<a4j:commandButton image="/Imagenes/actions/upload.png" style="border-width: 0px;cursor:pointer;" rendered="#{!cursos.w_descarga}" oncomplete="Richfaces.showModalPanel('mpSubirArchivo')">
                                                 <f:setPropertyActionListener target="#{managerCursoAperturado.cur_codigo_s}" value="#{cursos.cur_codigo_s}"/>
                                                 </a4j:commandButton>
                                                 * */%>
                                            <a4j:commandButton image="/Imagenes/actions/upload.png" style="border-width: 0px;cursor:pointer;" oncomplete="Richfaces.showModalPanel('mpSubirArchivo')">
                                                <f:setPropertyActionListener target="#{managerCursoAperturado.cur_codigo_s}" value="#{cursos.cur_codigo_s}"/>
                                            </a4j:commandButton>
                                            <h:commandButton image="/Imagenes/actions/descargar.png" actionListener="#{managerCursoAperturado.descargarArchivo}"  rendered="#{cursos.w_descarga}" />

                                        </p>
                                    </h:column>
                                </rich:dataTable>
                            </td>
                        </tr>
                    </table>
                </h:form>
                <rich:modalPanel  id="mp" minHeight="450" minWidth="1000" height="450" width="1000" zindex="2000">
                    <f:facet name="header">
                        <h:outputText value="Registro de Apertura" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp')" title="Cerrar"/>
                    </f:facet>
                    <h:form>
                        <table width="100%" style="font-size:10px; font-family:verdana" cellpadding="0" cellspacing="0" border="0">
                            <tr >
                                <td align="right" colspan="4">
                                    <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"  actionListener="#{managerCursoAperturado.Insertar}" action="#{managerCursoAperturado.Seleccion}" reRender="tablaMaster" oncomplete="javascript:alert('Se guardo el registro correctamente');Richfaces.hideModalPanel('mp')"/>
                                </td>
                            </tr>
                            <tr>

                                <td colspan="3"><hr size="1"><h:inputHidden value="#{managerCursoAperturado.id_curape_u}" id="id_curape"/>
                                    <h:inputHidden value="#{managerCursoAperturado.p_plancur_id_i}" id="idplancur"/>
                                    <h:inputHidden value="#{managerCursoAperturado.id_u}" id="id"/>
                                </td>
                            </tr>

                            <tr>
                                <td width="30%" >Ciclo:</td>
                                <td width="70%">
                                    <h:outputText value="#{managerCursoAperturado.ciclo_u}" id="ciclo"/>
                                </td>
                                <td align="right" width="20%">
                                </td>
                            </tr>

                            <tr>
                                <td width="30%">Codigo:</td>
                                <td width="70%">
                                    <h:outputText value="#{managerCursoAperturado.codigo_u}" id="codigo"/>
                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td width="30%">Nombre:</td>
                                <td width="70%">
                                    <h:outputText value="#{managerCursoAperturado.nombre_u}" id="nombre"/>
                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td width="30%">Aperturado:</td>
                                <td width="70%">
                                    <h:selectBooleanCheckbox id="aperturado" value="#{managerCursoAperturado.apertura_i}"/>
                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>

                                <td width="30%">Sistemas de Evaluacion:</td>
                                <td width="70%">
                                    <h:selectOneMenu value="#{managerCursoAperturado.sistemaEvaluacion_i}" id="sisevaluacion">
                                        <f:selectItems value="#{managerCursoAperturado.comboSistemaEvaluacion}"/>
                                        <a4j:support action="#{managerCursoAperturado.setearSistema}" event="onchange" reRender="promedios"/>
                                    </h:selectOneMenu>
                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td>Personalizar:
                                </td>
                                <td>
                                    <h:selectBooleanCheckbox value="#{managerCursoAperturado.personalizado}" id="personal">
                                        <a4j:support event="onclick" action="#{managerCursoAperturado.cambiarEfecto}" 
                                                     actionListener="#{managerCursoAperturado.setearSistema}"
                                                     oncomplete="#{managerCursoAperturado.efecto}" reRender="sisevaluacion,promedios"/>
                                    </h:selectBooleanCheckbox>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <%-- <input type="button" onclick="hideDiv({duration:0.7})" value="Hide" />
                                                <input type="button" onclick="showDiv()" value="Show" />
                                                <rich:effect for="window" event="onload" type="Appear" params="targetId:'contentDiv',duration:0.8,from:0.3,to:1.0" />
                                    --%>
                                    <rich:effect  name="hideDiv"  for="contentDiv" type="Fade" />
                                    <rich:effect  name="showDiv"  for="contentDiv" type="Appear" />
                                    <div id="contentDiv">
                                        <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                            <tr>
                                                <td>
                                                    <fieldset  style="width:350px;">
                                                        <table>
                                                            <tr>

                                                                <td width="20%">Promedio:</td>
                                                                <td width="20%">Codigo:</td>
                                                                <td width="20%">Nombre</td>
                                                                <td width="20%">Peso</td>
                                                                <td width="20%">Semana</td>
                                                                <td width="20%">Orden</td>


                                                                <td width="20%">Agrupar</td>
                                                                <td width="20%">Tipo Nota</td>
                                                                <td width="20%">Etapa Semestre</td>

                                                                <td width="20%"></td>
                                                            </tr>
                                                            <tr>
                                                                <td width="20%">
                                                                    <h:selectOneMenu value="#{managerCursoAperturado.promediosExamenes_i}" id="promedios" style="width : 150px;">
                                                                        <f:selectItems value="#{managerCursoAperturado.comboPromediosExamenes}" />
                                                                    </h:selectOneMenu>
                                                                </td>
                                                                <td width="20%">
                                                                    <h:inputText value="#{managerCursoAperturado.codigo_i}"/>
                                                                </td>
                                                                <td width="20%">
                                                                    <h:inputText value="#{managerCursoAperturado.nombre_i}"/>
                                                                </td>
                                                                <td width="20%">
                                                                    <rich:inputNumberSpinner value="#{managerCursoAperturado.peso_i}" inputStyle="width : 20px;" />
                                                                </td>
                                                                <td width="10%">
                                                                    <h:inputText value="#{managerCursoAperturado.semana_i}" style="width : 50px;"/>
                                                                </td>
                                                                <td width="10%">
                                                                    <rich:inputNumberSpinner value="#{managerCursoAperturado.orden_i}" inputStyle="width : 20px;"/>
                                                                </td>


                                                                <td width="20%">
                                                                    <h:inputText id="agrupar_det" value="#{managerCursoAperturado.agrupar_i}"/>
                                                                </td>
                                                                <td width="20%">
                                                                    <h:selectOneMenu id="horTipoClase" value="#{managerCursoAperturado.SETipoNota}">
                                                                        <f:selectItems value="#{managerCursoAperturado.tipoNota}"/>
                                                                    </h:selectOneMenu>
                                                                </td>
                                                                <td width="10%">
                                                                    <h:selectOneMenu id="ESemestre" value="#{managerCursoAperturado.SESemestreEtapa}">
                                                                        <f:selectItems value="#{managerCursoAperturado.semestreEtapa}"/>
                                                                    </h:selectOneMenu>
                                                                </td>

                                                                <td width="20%">
                                                                    <a4j:commandButton   image="/Imagenes/actions/edit_add.png"  title="Agregar"  action="#{managerCursoAperturado.Agregar}" reRender="tablaSecundaria,barra" oncomplete="#{managerCursoAperturado.alert}"/>
                                                                </td>

                                                            </tr>
                                                        </table>
                                                    </fieldset>
                                                </td>
                                            </tr>




                                            <tr>
                                                <td colspan="7">
                                                    <hr size="1" width="100%">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="7" style=" width : 100%">
                                                    <p align="right"/>
                                                    <rich:datascroller id="barra" align="right"  for="tablaSecundaria" maxPages="8"  style=" width : 100%;"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="7" width="100%">
                                                    <rich:dataTable id="tablaSecundaria" rows="6"
                                                                    onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                                    cellpadding="0" cellspacing="0"
                                                                    width="100%" border="0" value="#{managerCursoAperturado.tablaDetallePromedios}" var="detalle">
                                                        <h:column>
                                                            <f:facet name="header">
                                                                <h:outputText value="Id" />
                                                            </f:facet>
                                                            <h:outputText value="#{detalle.id_det}"/>
                                                        </h:column>
                                                        <%--
                                                                    <h:column>
                                                                    <f:facet name="header">
                                                                    <h:outputText value="siseva_det" />
                                                                    </f:facet>
                                                                    <h:outputText value="#{detalle.siseva_det}"/>
                                                                    </h:column>
                                                        --%>
                                                        <h:column >
                                                            <f:facet name="header">
                                                                <h:outputText value="Promedio" />
                                                            </f:facet>
                                                            <h:outputText value="#{detalle.promediosExamenes_i}" rendered="#{detalle.view}"/>
                                                            <h:selectOneMenu value="#{detalle.promediosExamenes_i}" id="promedios" rendered="#{detalle.editable}" style="width : 120px;">
                                                                <f:selectItems value="#{managerCursoAperturado.comboPromediosExamenes}"/>
                                                            </h:selectOneMenu>
                                                        </h:column>
                                                        <h:column>
                                                            <f:facet name="header">
                                                                <h:outputText value="Cod" />
                                                            </f:facet>
                                                            <h:outputText value="#{detalle.codigo_i}" rendered="#{detalle.view}"/>
                                                            <h:inputText value="#{detalle.codigo_i}"  rendered="#{detalle.editable}" style="width : 40px;"/>
                                                        </h:column>
                                                        <h:column>
                                                            <f:facet name="header">
                                                                <h:outputText value="Nombre" />
                                                            </f:facet>
                                                            <h:outputText value="#{detalle.nombre_i}" rendered="#{detalle.view}" />
                                                            <h:inputText value="#{detalle.nombre_i}"  rendered="#{detalle.editable}"/>
                                                        </h:column>
                                                        <h:column >
                                                            <f:facet name="header">
                                                                <h:outputText value="Peso" />
                                                            </f:facet>
                                                            <h:outputText value="#{detalle.peso_i}" rendered="#{detalle.view}"/>
                                                            <h:inputText value="#{detalle.peso_i}" style="width : 20px;" rendered="#{detalle.editable}"/>
                                                        </h:column>
                                                        <h:column >
                                                            <f:facet name="header">
                                                                <h:outputText value="Sem" />
                                                            </f:facet>
                                                            <h:outputText value="#{detalle.semana_i}" style="width : 20px;" rendered="#{detalle.view}"/>
                                                            <h:inputText value="#{detalle.semana_i}" style="width : 20px;"  rendered="#{detalle.editable}"/>
                                                        </h:column>

                                                        <h:column >
                                                            <f:facet name="header">
                                                                <h:outputText value="Ord" />
                                                            </f:facet>
                                                            <h:outputText value="#{detalle.orden_i}" style="width : 20px;" rendered="#{detalle.view}"/>
                                                            <h:inputText value="#{detalle.orden_i}" style="width : 20px;"  rendered="#{detalle.editable}"/>
                                                        </h:column>

                                                        <h:column>
                                                            <f:facet name="header">
                                                                <h:outputText value="Agrupar" />
                                                            </f:facet>

                                                            <h:outputText value="#{detalle.agrupar_i}" rendered="#{detalle.view}"/>
                                                            <h:inputText value="#{detalle.agrupar_i}"  rendered="#{detalle.editable}"/>
                                                        </h:column>
                                                        <rich:column>
                                                            <f:facet name="header">
                                                                <h:outputText value="Tipo Nota"/>
                                                            </f:facet>
                                                            <h:outputText value="#{detalle.v_tipo_nota}" rendered="#{detalle.view}"/>

                                                            <h:selectOneMenu value="#{detalle.SETipoNota}" rendered="#{detalle.editable}">
                                                                <f:selectItems value="#{detalle.tipoNota}"/>
                                                            </h:selectOneMenu>
                                                        </rich:column>
                                                        <rich:column>
                                                            <f:facet name="header">
                                                                <h:outputText value="Etapa Semestre"/>
                                                            </f:facet>
                                                            <h:outputText value="#{detalle.v_semestre_etapa}" rendered="#{detalle.view}"/>

                                                            <h:selectOneMenu value="#{detalle.SESemestreEtapa}" rendered="#{detalle.editable}">
                                                                <f:selectItems value="#{detalle.semestreEtapa}"/>
                                                            </h:selectOneMenu>
                                                        </rich:column>


                                                        <h:column>
                                                            <f:facet name="header">
                                                                <h:outputText value=""/>
                                                            </f:facet>
                                                            <a4j:commandButton actionListener="#{detalle.Edit}" value="" image="/Imagenes/actions/klipper_dock.png" title="Editar" rendered="#{detalle.view_bool}" reRender="tablaSecundaria,barra"/>
                                                            <a4j:commandButton actionListener="#{detalle.Aceptar}" image="/Imagenes/actions/fileexport.png" title="Aceptar" rendered="#{detalle.editable_bool}" reRender="tablaSecundaria,barra"/>
                                                            <a4j:commandButton actionListener="#{detalle.Cancelar}" image="/Imagenes/actions/fileclose.png" title="Cancelar" rendered="#{detalle.editable_bool}" reRender="tablaSecundaria,barra"/>
                                                        </h:column>
                                                        <h:column >
                                                            <f:facet name="header" >
                                                                <h:outputText value="" />
                                                            </f:facet>
                                                            <f:param value="#{detalle.id_det}" id="id_det"/>
                                                            <p align="right">
                                                                <a4j:commandButton image="/Imagenes/actions/edit_remove.png"  actionListener="#{managerCursoAperturado.Quitar}" title="Quitar" reRender="tablaSecundaria,barra" />
                                                            </p>
                                                        </h:column>
                                                    </rich:dataTable>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </h:form>
                </rich:modalPanel>
                <rich:modalPanel  id="mp2" minHeight="550" minWidth="800" height="550" width="800" zindex="2000">
                    <f:facet name="header">
                        <h:outputText value="Registro de Secciones" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp2')" title="Cerrar"/>
                    </f:facet>
                    <h:form id="hSecciones">
                        <table width="100%" style="font-size:10px; font-family:verdana"  cellspacing="0" border="0">
                            <tr >
                                <td align="right" colspan="4">
                                    <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"  actionListener="#{managerCursoAperturado.Insertar}" action="#{managerCursoAperturado.Seleccion}" reRender="tablaMaster" oncomplete="javascript:alert('Se guardo el registro correctamente');Richfaces.hideModalPanel('mp2')"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3"><hr size="1">
                                    <h:inputHidden value="#{managerCursoAperturado.id_u}" id="id1"/>
                                    <h:inputHidden value="#{managerCursoAperturado.p_plancur_id_i}" id="idplancur1"/>
                                    <h:inputHidden value="#{managerCursoAperturado.id_curape_u}" id="id_curape1"/>
                                </td>
                            </tr>

                            <tr>
                                <td width="30%" >Ciclo:</td>
                                <td width="70%">
                                    <h:outputText value="#{managerCursoAperturado.ciclo_u}" id="ciclo1"/>
                                </td>
                                <td align="right" width="20%">
                                </td>
                            </tr>
                            <tr>
                                <td width="30%">Codigo de Curso:</td>
                                <td width="70%">
                                    <h:outputText value="#{managerCursoAperturado.codigo_u}" id="codigo1"/>
                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td width="30%">Nombre de Curso:</td>
                                <td width="70%">
                                    <h:outputText value="#{managerCursoAperturado.nombre_u}" id="nombre1"/>
                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td width="30%">Aperturado:</td>
                                <td width="70%">
                                    <h:inputText value="#{managerCursoAperturado.apertura_i}" id="aperturado1"/>

                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td width="30%">Sistemas de Evaluacion:</td>
                                <td width="70%">
                                    <h:inputText value="#{managerCursoAperturado.sistemaEvaluacion_i}" id="sisevaluacion1"/>
                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td colspan="8" width="100%">
                                    <%/* <input type="button" onclick="hideDiv({duration:0.7})" value="Hide" />
                                         <input type="button" onclick="showDiv()" value="Show" />
                                         <rich:effect for="window" event="onload" type="Appear" params="targetId:'contentDiv',duration:0.8,from:0.3,to:1.0" />
                                         */%>
                                    <rich:effect  name="hideDiv"  for="contentDiv" type="Fade" />
                                    <rich:effect  name="showDiv"  for="contentDiv" type="Appear" />
                                    <table width="100%"  cellspacing="0" border="0">
                                        <tr>
                                            <td width="25%" colspan="2">Codigo de Seccion:</td>
                                            <!--<td width="25%">Nombre  de Seccion</td>-->
                                            <td width="25%">Numero de Vacantes</td>
                                            <td width="25%"></td>
                                        </tr>
                                        <tr>
                                            <td width="25%" colspan="2">
                                                <!--<h:inputText value="#{managerCursoAperturado.codigo_sec}"/>-->
                                                <h:selectOneMenu value="#{managerCursoAperturado.w_secEspeid}" id="icbSeccion" style="width:150px" >
                                                    <f:selectItems value="#{managerCursoAperturado.cboSeccionEspe}"/>
                                                    <a4j:support event="onchange" actionListener="#{managerCursoAperturado.cargarCboSeccionEspecialidad}" />
                                                </h:selectOneMenu>
                                            </td>
                                            <!--<td width="25%">
                                            <h:inputText value="#{managerCursoAperturado.nombre_sec}" id="iNombreSec"/>
                                        </td>-->
                                            <td width="25%">
                                                <rich:inputNumberSpinner value="#{managerCursoAperturado.vacantes_sec}" style="width : 20px;" >
                                                </rich:inputNumberSpinner>
                                            </td>
                                            <td width="25%">
                                                <a4j:commandButton   image="/Imagenes/actions/edit_add.png"  title="Agregar"  
                                                                     action="#{managerCursoAperturado.Agregar1}" reRender="tablaSecundaria1, barra2" 
                                                                     actionListener="#{managerCursoAperturado.cargarCboSeccionEspecialidad}"
                                                                     oncomplete="#{managerCursoAperturado.oncomplete}" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="5">
                                                <hr size="1" width="100%">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="5" width="100%">
                                                <rich:datascroller id="barra2" align="right"  for="tablaSecundaria1" maxPages="10"  style=" width : 100%;"/>
                                                <rich:dataTable id="tablaSecundaria1" rows="10"
                                                                onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                                onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                                cellpadding="0" cellspacing="0"
                                                                width="100%" border="0" value="#{managerCursoAperturado.tablaSecciones}" var="detalle">
                                                    <h:column>
                                                        <f:facet name="header">
                                                            <h:outputText value="Id" />
                                                        </f:facet>
                                                        <h:outputText value="#{detalle.id_det}"/>
                                                    </h:column>
                                                    <h:column>
                                                        <f:facet name="header">
                                                            <h:outputText value="Codigo" />
                                                        </f:facet>
                                                        <h:outputText value="#{detalle.codigo_sec}" rendered="#{detalle.view}"/>
                                                        <h:inputText value="#{detalle.codigo_sec}"  rendered="#{detalle.editable}"/>
                                                    </h:column>
                                                    <h:column>
                                                        <f:facet name="header">
                                                            <h:outputText value="Nombre" />
                                                        </f:facet>
                                                        <h:outputText value="#{detalle.nombre_sec}" rendered="#{detalle.view}"/>
                                                        <h:inputText value="#{detalle.nombre_sec}"  rendered="#{detalle.editable}"/>
                                                    </h:column>
                                                    <h:column >
                                                        <f:facet name="header">
                                                            <h:outputText value="Vacantes" />
                                                        </f:facet>
                                                        <h:outputText value="#{detalle.vacantes_sec}" rendered="#{detalle.view}"/>
                                                        <h:inputText value="#{detalle.vacantes_sec}" style="width : 20px;" rendered="#{detalle.editable}"/>
                                                    </h:column>
                                                    <h:column >
                                                        <f:facet name="header">
                                                            <h:outputText value="Activo" />
                                                        </f:facet>
                                                        <h:outputText value="#{detalle.activo_i}"/>
                                                    </h:column>
                                                    <h:column>
                                                        <f:facet name="header">
                                                            <h:outputText value="Edit"/>
                                                        </f:facet>
                                                        <a4j:commandButton actionListener="#{detalle.Edit1}" value="" image="/Imagenes/actions/klipper_dock.png" title="Editar" rendered="#{detalle.view_bool}" reRender="tablaSecundaria1"/>
                                                        <a4j:commandButton actionListener="#{detalle.Aceptar1}" image="/Imagenes/actions/fileexport.png" title="Aceptar" rendered="#{detalle.editable_bool}" reRender="tablaSecundaria1"/>
                                                        <a4j:commandButton actionListener="#{detalle.Cancelar1}" image="/Imagenes/actions/fileclose.png" title="Cancelar" rendered="#{detalle.editable_bool}" reRender="tablaSecundaria1"/>
                                                    </h:column>
                                                    <h:column >
                                                        <f:facet name="header" >
                                                            <h:outputText value="" />
                                                        </f:facet>
                                                        <f:param value="#{detalle.id_det}" id="id_det1"/>
                                                        <p align="right">
                                                            <a4j:commandButton image="/Imagenes/actions/edit_remove.png"  actionListener="#{managerCursoAperturado.Quitar1}" reRender="tablaSecundaria1,barra2" />
                                                        </p>
                                                    </h:column>
                                                </rich:dataTable>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </h:form>
                </rich:modalPanel>



            </rich:panel>

            <rich:modalPanel  id="mpSubirArchivo" autosized="true" zindex="100">
                <f:facet name="header">
                    <h:outputText value="Subir Syllabus" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpSubirArchivo')" title="Cerrar"/>
                </f:facet>
                <h:form id="fSyllabus">
                    <table>
                        <tr>
                            <td align="right" colspan="2">
                                <h:panelGroup >
                                    <rich:fileUpload id="iFile" acceptedTypes="pdf, jpg, jpeg, img"
                                                     maxFilesQuantity="2" 
                                                     immediateUpload="true"
                                                     addControlLabel="Agregar"
                                                     uploadControlLabel="Subir" 
                                                     clearControlLabel="Limpiar"
                                                     clearAllControlLabel="Limpiar Todos"                                                       
                                                     fileUploadListener="#{managerCursoAperturado.listenerSyllabus}">
                                        <a4j:support event="onuploadcomplete" actionListener="#{managerCursoAperturado.Seleccion}" reRender="fSyllabus,tablaMaster"/>
                                    </rich:fileUpload>
                                </h:panelGroup>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>

        </f:view>
    </body>
</html>  