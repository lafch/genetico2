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
        <title>Mantenimiento de Alumnos</title>
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
            <jsp:include page="../TablaSistema/Menu.jsp"/>
            <rich:panel>
                <h:form id="form1" style="width:100%" >

                    <f:facet name="FACULTADES">
                    </f:facet>

                    <table width="100%" style="font-size:10px; font-family:verdana"  cellspacing="0" border="0" cellpadding="0">
                        <tr>
                            <td width="20%" colspan="2" ><strong>MANTENIMIENTO DE ALUMNOS</strong></td>
                            <td width="30%" align="right"> </td>
                            <td width="30%" align="right">
                                <h:graphicImage value="/Imagenes/actions/filenew.png" width="16"
                                                style="border-width: 0px;cursor: pointer;">
                                    <a4j:support event="onclick" onsubmit="hideDiv({duration:0.1});"
                                                 oncomplete="Richfaces.showModalPanel('mp',{width:100, top:40})"
                                                 action="#{managerAlumno.Nuevo}"
                                                 reRender="iId,iCodigo,iPaterno,iMaterno,iNombre,iDni,iMail,
                                                 iIdTipo,iIdModalidad,iIdEstado,iIdFacIng,iIdEspIng,
                                                 iIdFacAct,iIdEspAct,iSemestre,semestre_vigente,iIdPlanIng,
                                                 iIdPlanAct,iMonPag" />
                                </h:graphicImage>
                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right">
                                <h:graphicImage value="/Imagenes/actions/fileprint.png"
                                                style="cursor: pointer;"
                                                title="Imprimir Listado"/>
                            </td>
                            <td align=right width="50%">
                                <a4j:commandButton id="buscar" action="#{managerAlumno.Seleccionar}"
                                                   image="/Imagenes/actions/viewmag.png" title="Buscar"
                                                   reRender="tablaMaster,barra" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="7"><hr width="100%" size="1"></td>
                        </tr>
                        <tr>
                            <td width="20%" colspan="6"></td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputText value="C�digo:"/></td>
                            <td width="30%">
                                <h:inputText styleClass="dr-spnr-i rich-spinner-input" value="#{managerAlumno.b_codigo}" style="width : 180px;"/>
                            </td>
                            <td width="10%" align="right">Nombres:</td>
                            <td><h:inputText value="#{managerAlumno.b_nombre}" style="width : 180px;"/></td>
                        </tr>
                        <tr>
                            <td width="10%">Ap. Paterno:</td>
                            <td width="30%">
                                <h:inputText value="#{managerAlumno.b_paterno}" style="width : 180px;"/>
                            </td>
                            <td width="10%" align="right">Facultad Actual:</td>
                            <td>
                                <h:selectOneMenu id="FacuBuscar" value="#{managerAlumno.b_id_fac_act}" style="width : 180px;">
                                    <f:selectItems value="#{managerAlumno.comboFacultad_buscar}" />
                                    <a4j:support event="onchange"  reRender="FacuBuscar,EspeBuscar"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%">Ap. Materno:</td>
                            <td><h:inputText value="#{managerAlumno.b_materno}" style="width : 180px;"/></td>
                            <td  align="right">Especialidad Actual:</td>
                            <td>
                                <h:selectOneMenu id="EspeBuscar" value="#{managerAlumno.b_id_esp_act}" style="width : 180px;">
                                    <f:selectItems value="#{managerAlumno.comboEspecialidad_buscar}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="7" width="100%"><hr width="100%" size="1"></td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                        </tr>
                    </table>
                </h:form>
                <h:form style=" width : 100%;">
                    <table style=" width : 100%;"  cellspacing="0" border="0" cellpadding="0">
                        <tr>
                            <td colspan="5" style=" width : 100%">
                                <p align="right"/>
                                <rich:datascroller id="barra" align="right"  for="tablaMaster" maxPages="8"  style=" width : 100%;"/>
                            </td>
                        </tr>
                    </table>
                    <rich:dataTable id="tablaMaster"  rows="10" onRowMouseOver="this.style.backgroundColor='#F1F1F1'" onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'" cellpadding="0" cellspacing="0" width="100%" border="0" value="#{managerAlumno.listaAlumno}" var="Alu">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id" />
                            </f:facet>
                            <h:outputText value="#{Alu.b_id}"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Cod." />
                            </f:facet>
                            <h:outputText value="#{Alu.b_codigo}" rendered="#{Alu.view}"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Ape y Nom" />
                            </f:facet>
                            <h:outputText value="#{Alu.b_paterno} #{Alu.b_materno} #{Alu.b_nombre}" rendered="#{Alu.view}"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Especialidad" />
                            </f:facet>
                            <h:outputText value="#{Alu.b_esp_act}"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Correo" />
                            </f:facet>
                            <h:outputText value="#{Alu.b_mail}"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Tipo" />
                            </f:facet>
                            <h:outputText value="#{Alu.b_tipo}"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Procedencia" />
                            </f:facet>
                            <h:outputText value="#{Alu.b_procedencia}"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="" />
                            </f:facet>
                            <h:graphicImage value="/Imagenes/actions/fileprint.png" style="border-width: 0px;" height="16" title="Imprimir">
                                <a4j:support event="onclick" onsubmit="hideDiv2({duration:0.1});" actionListener="#{managerAlumno.imprimirAsistencia}"  oncomplete="javascript:Richfaces.showModalPanel('mp5',{width:100, top:80})" reRender="pId,pCodigo,reporte" />
                            </h:graphicImage>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="" />
                            </f:facet>
                            <h:graphicImage value="/Imagenes/actions/calendar.gif" style="border-width: 0px;" height="16" title="Asistencia">
                                <a4j:support event="onclick" onsubmit="hideDiv2({duration:0.1});" actionListener="#{managerAlumno.MostrarAsistencia}"  oncomplete="javascript:Richfaces.showModalPanel('mp4',{width:100, top:80})" reRender="aId,aCodigo,aCodigo1,aPaterno,aMaterno,aNombre,aMes,aAnio,tablaAsistencia" />

                            </h:graphicImage>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="" />
                            </f:facet>
                            <h:graphicImage value="/Imagenes/actions/editpaste.png" style="border-width: 0px;" height="14" title="Editar">
                                <a4j:support event="onclick" onsubmit="hideDiv({duration:0.1});"  oncomplete="javascript:Richfaces.showModalPanel('mp',{width:100, top:40})" actionListener="#{managerAlumno.EditarFila}"  reRender="iId,iCodigo,iPaterno,iMaterno,iNombre,iDni,iMail,iIdTipo,iIdModalidad,iIdEstado,iIdFacIng,iIdEspIng,iIdFacAct,iIdEspAct,iSemestre,semestre_vigente,iIdPlanIng,iIdPlanAct,imagen,iProcedencia,iFam,iTipoFamiliar,iFormaPago,iAluUnidad,iMonPag,iCategoria" immediate="true" />
                            </h:graphicImage>

                        </h:column>
                        <%--
                                    <h:column >
                                    <f:facet name="header" >
                                    <h:outputText value="" />
                                    </f:facet>
                                    <p align="center">
                                    <a4j:commandButton   image="#{Alu.imagenGEP}"  title="Generar EP"  actionListener="#{managerAlumno.InsertarEP}" action="#{managerAlumno.Seleccionar}" reRender="tablaMaster, barra" disabled="#{Alu.disabledGEP}" oncomplete="#{managerAlumno.oncompleteGEP}"/>
                                    </p>
                                    </h:column>  ver
                        --%>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="" />
                            </f:facet>
                            <p align="right">
                                <%--<h:graphicImage value="/Imagenes/actions/add_.gif" height="14" style="border-width: 0px;" title="Adicionar Datos">
                                    <a4j:support event="onclick" onsubmit="hideDiv2({duration:0.1});"  oncomplete="javascript:Richfaces.showModalPanel('mp1',{width:100, top:80})" actionListener="#{managerAlumno.EditarFila2}"  reRender="iId2,datos,Colegio,iSexo,iCorreo,TelefonoCelular,iTipoColegio,iTipoDocumento,iNumeroDocumento,iCreacion,comboDistritoLima,iPreparacion,iAcademia,AcademiaOtros,iAniosPrep,iMesesPrep,iTelefono,direccionLima,nDepartamento,nProvincia,nDistrito,rDepartamento,rProvincia,rDistrito,Documentos,iUltimaFecha" immediate="true"/>
                                </h:graphicImage>--%>
                                <f:param id="p_id" value="#{Alu.b_id}" />
                                <f:param id="p_codigo"   value="#{Alu.b_codigo}" />
                                <f:param id="p_paterno"   value="#{Alu.b_paterno}" />
                                <f:param id="p_materno"   value="#{Alu.b_materno}" />
                                <f:param id="p_nombre"   value="#{Alu.b_nombre}" />
                                <f:param id="p_dni"   value="#{Alu.b_dni}" />
                                <f:param id="p_mail"   value="#{Alu.b_mail}" />
                                <f:param id="p_id_tipo"   value="#{Alu.b_id_tipo}" />
                                <f:param id="p_id_modalidad"   value="#{Alu.b_id_modalidad}" />
                                <f:param id="p_id_estado"   value="#{Alu.b_id_estado}" />
                                <f:param id="p_id_fac_ing"   value="#{Alu.b_id_fac_ing}" />
                                <f:param id="p_id_esp_ing"   value="#{Alu.b_id_esp_ing}" />
                                <f:param id="p_id_fac_act"   value="#{Alu.b_id_fac_act}" />
                                <f:param id="p_id_esp_act"   value="#{Alu.b_id_esp_act}" />
                                <f:param id="p_id_semestre"   value="#{Alu.b_id_semestre}" />
                                <f:param id="semestre_vigente"   value="#{Alu.semestre_vigente}" />
                                <f:param id="p_id_plan_ing"   value="#{Alu.b_id_plan_ing}" />
                                <f:param id="p_id_plan_act"   value="#{Alu.b_id_plan_act}" />
                                <f:param id="p_plan_ing"   value="#{Alu.b_plan_ing}" />
                                <f:param id="p_plan_act"   value="#{Alu.b_plan_act}" />
                                <f:param id="p_procedencia"   value="#{Alu.b_procedencia_id}" />
                                <f:param id="p_alu_familiar"   value="#{Alu.b_alu_familiar}" />
                                <f:param id="p_alu_tipo_familiar"   value="#{Alu.b_alu_tipo_familiar}" />
                                <f:param id="p_alu_forma_pago"   value="#{Alu.b_alu_forma_pago}" />
                                <f:param id="p_alu_unidad"   value="#{Alu.b_alu_unidad}" />
                                <f:param id="p_alu_monto_pago"   value="#{Alu.b_alu_monto_pago}" />
                            </p>

                        </h:column>
                        <h:column >
                            <f:facet name="header" >
                                <h:outputText value="" />
                            </f:facet>
                            <p align="right"><h:commandButton  image="/Imagenes/actions/no.png" style="height:14"  title="Eliminar" actionListener="#{managerAlumno.EliminarFila}" action="#{managerAlumno.Seleccionar}"  onclick="javascript:return (confirm('�Esta realmente seguro de Eliminar?'));"/></p>
                            <f:param id="delete"  value="#{Alu.b_id}" />
                        </h:column>
                    </rich:dataTable>
                </h:form>

                <rich:modalPanel  id="mp" minHeight="400" minWidth="550" height="500" width="500" zindex="2000"  onshow="showDiv();">
                    <rich:effect  name="hideDiv"  for="contentDiv" type="Fade" />
                    <rich:effect  name="showDiv"  for="contentDiv" type="Appear" />
                    <div id="contentDiv">
                        <f:facet name="header">
                            <h:outputText value="Registro Alumno" />
                        </f:facet>
                        <f:facet name="controls">
                            <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp')" title="Cerrar"/>
                        </f:facet>
                        <h:form>
                            <table width="100%" style="font-size:10px; font-family:verdana" cellspacing="0" border="0">
                                <tr >
                                    <td>
                                    </td>
                                    <td align="right" colspan="4"><a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"  actionListener="#{managerAlumno.Insertar}" action="#{managerAlumno.Seleccionar}" reRender="tablaMaster, barra" oncomplete="#{managerAlumno.oncomplete}"/></td>
                                </tr>
                                <tr>
                                    <td colspan="3"><hr size="1">
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%">
                                        <h:inputHidden id="iId" value="#{managerAlumno.b_id_alumno}"/>
                                        <h:inputHidden id="iCodigo"  value="#{managerAlumno.b_codigo_alumno}"  />
                                    </td>
                                    <td align="right" width="20%">
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%" >*Ap Paterno:</td>
                                    <td width="70%">
                                        <h:inputText id="iPaterno"  style=" width : 180px;" value="#{managerAlumno.b_paterno_alumno}" />
                                    </td>
                                    <td align="right" width="20%" rowspan="6">
                                        <a4j:outputPanel id="imagen" ajaxRendered="true" layout="block">
                                            <a4j:mediaOutput style="width : 100px;" element="img" cacheable="false" rendered="true" standby="cargando..."  createContent="#{managerAlumno.imagen}"  mimeType="image/png" value="#{managerAlumno.b_id_alumno}"/>
                                        </a4j:outputPanel>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%" >*Ap Materno:</td>
                                    <td width="70%">
                                        <h:inputText id="iMaterno"  style=" width : 180px;" value="#{managerAlumno.b_materno_alumno}"/>
                                    </td>
                                    <td align="right" width="20%">
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%" >*Nombres:</td>
                                    <td width="70%">
                                        <h:inputText  id="iNombre"  style=" width : 180px;" value="#{managerAlumno.b_nombre_alumno}"  />
                                    </td>
                                    <td align="right" width="20%">
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%" >DNI:</td>
                                    <td width="70%">
                                        <h:inputText id="iDni"  style=" width : 180px;" value="#{managerAlumno.b_dni_alumno}" />
                                    </td>
                                    <td align="right" width="20%">
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%" >E-Mail:</td>
                                    <td width="70%">
                                        <h:inputText id="iMail"  style=" width : 180px;" value="#{managerAlumno.b_mail_alumno}" />
                                    </td>
                                    <td align="right" width="20%">
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%">*Tipo:</td>
                                    <td width="70%">
                                        <h:selectOneMenu id="iIdTipo" value="#{managerAlumno.b_id_tipo_alumno}"  style="width : 180px;">
                                            <f:selectItems value="#{managerAlumno.comboCatalogo_tipo}"/>
                                        </h:selectOneMenu>
                                    </td>
                                    <td width="20%"></td>
                                </tr>
                                <tr>
                                    <td width="30%">*Modalidad:</td>
                                    <td width="70%">
                                        <h:selectOneMenu id="iIdModalidad" value="#{managerAlumno.b_id_modalidad_alumno}"  style="width : 180px;">
                                            <f:selectItems value="#{managerAlumno.comboCatalogo_moda}"/>
                                        </h:selectOneMenu>
                                    </td>
                                    <td width="20%"></td>
                                </tr>
                                <tr>
                                    <td width="30%">*Estado:</td>
                                    <td width="70%">
                                        <h:selectOneMenu id="iIdEstado" value="#{managerAlumno.b_id_estado_alumno}"  style="width : 180px;">
                                            <f:selectItems value="#{managerAlumno.comboCatalogo_estado}"/>
                                        </h:selectOneMenu>
                                    </td>
                                    <td width="20%"></td>
                                </tr>
                                <tr>
                                    <td width="30%" >*Fac Ingreso:</td>
                                    <td width="70%">
                                        <a4j:region id="fac_ing1">
                                            <h:selectOneMenu id="iIdFacIng" value="#{managerAlumno.b_id_fac_ing_alumno}"  style="width : 180px;">
                                                <f:selectItems value="#{managerAlumno.comboFacultades_ingreso}" />
                                                <a4j:support event="onchange"  reRender="iIdFacIng,iIdEspIng,iIdPlanIng"/>
                                            </h:selectOneMenu>
                                        </a4j:region>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%">*Esp Ingreso:</td>
                                    <td width="70%">
                                        <a4j:region id="fac_ing2">
                                            <h:selectOneMenu  id="iIdEspIng"  value="#{managerAlumno.b_id_esp_ing_alumno}"  style="width : 180px;">
                                                <f:selectItems value="#{managerAlumno.comboEspecialidades_ingreso}" />
                                                <a4j:support event="onchange"  reRender="iIdEspIng,iIdPlanIng"/>
                                            </h:selectOneMenu>
                                        </a4j:region>
                                    </td>
                                    <td width="20%"></td>
                                </tr>
                                <tr>
                                    <td width="30%">*Plan Ingreso:</td>
                                    <td width="70%">
                                        <a4j:region id="fac_ing3">
                                            <h:selectOneMenu id="iIdPlanIng" value="#{managerAlumno.b_id_plan_ing_alumno}"  style="width : 180px;">
                                                <f:selectItems value="#{managerAlumno.comboPlan_ingreso}"/>
                                            </h:selectOneMenu>
                                        </a4j:region>
                                    </td>
                                    <td width="20%"></td>
                                </tr>
                                <tr>
                                    <td width="30%">*Sem Ingreso:</td>
                                    <td width="70%">
                                        <h:selectOneMenu id="iSemestre" value="#{managerAlumno.b_id_semestre_alumno}"  style="width : 180px;">
                                            <f:selectItems value="#{managerAlumno.comboSemestre}"/>
                                        </h:selectOneMenu>
                                    </td>
                                    <td width="20%"></td>
                                </tr>
                                <tr>
                                    <td width="30%">*Sem Actual:</td>
                                    <td width="70%"><h:outputText id="semestre_vigente"  style=" width : 180px; color:#008000" value="#{managerAlumno.semestre_actual}" /></td>
                                </tr>
                                <tr>
                                    <td width="30%" >*Fac Actual:</td>
                                    <td width="70%">
                                        <a4j:region id="fac_act1">
                                            <h:selectOneMenu id="iIdFacAct" value="#{managerAlumno.b_id_fac_act_alumno}"  style="width : 180px;">
                                                <f:selectItems value="#{managerAlumno.comboFacultades_actual}" />
                                                <a4j:support event="onchange"  reRender="iIdFacAct,iIdEspAct,iIdPlanAct"/>
                                            </h:selectOneMenu>
                                        </a4j:region>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%">*Esp Actual:</td>
                                    <td width="70%">
                                        <a4j:region id="fac_act2">
                                            <h:selectOneMenu  id="iIdEspAct"  value="#{managerAlumno.b_id_esp_act_alumno}"  style="width : 180px;">
                                                <f:selectItems value="#{managerAlumno.comboEspecialidades_actual}" />
                                                <a4j:support event="onchange" reRender="iIdEspAct,iIdPlanAct"/>
                                            </h:selectOneMenu>
                                        </a4j:region>
                                    </td>
                                    <td width="20%"></td>
                                </tr>
                                <tr>
                                    <td width="30%">*Plan Actual:</td>
                                    <td width="70%">
                                        <a4j:region id="fac_act3">
                                            <h:selectOneMenu id="iIdPlanAct" value="#{managerAlumno.b_id_plan_act_alumno}"  style="width : 180px;">
                                                <f:selectItems value="#{managerAlumno.comboPlan_actual}"/>
                                            </h:selectOneMenu>
                                        </a4j:region>
                                    </td>
                                    <td width="20%"></td>
                                </tr>
                                <tr>
                                    <td width="30%">*Categoria:</td>
                                    <td width="70%">
                                        <h:selectOneMenu id="iCategoria" value="#{managerAlumno.w_cate_codigo}" style="width : 180px;">
                                            <f:selectItems value="#{managerAlumno.comboCategoria}"/>
                                            <a4j:support event="onchange" reRender="iProcedencia"/>
                                        </h:selectOneMenu>
                                    </td>

                                    <td width="20%"></td>
                                </tr>
                                <tr>
                                    <td width="30%">*Procedencia:</td>
                                    <td width="70%">
                                        <h:selectOneMenu id="iProcedencia" value="#{managerAlumno.b_procedencia}" style="width : 180px;">
                                            <f:selectItems value="#{managerAlumno.comboProcedencia}"/>
                                        </h:selectOneMenu>
                                    </td>

                                    <td width="20%"></td>
                                </tr>

                                <tr>
                                    <td width="30%" >Familiar:</td>
                                    <td width="70%">
                                        <h:inputText id="iFam"  style=" width : 180px;" value="#{managerAlumno.b_alu_familiar}" />
                                    </td>
                                    <td align="right" width="20%">
                                    </td>
                                </tr>

                                <tr>
                                    <td width="30%">Tipo Familiar:</td>
                                    <td width="70%">
                                        <h:selectOneMenu id="iTipoFamiliar" value="#{managerAlumno.b_alu_tipo_familiar}" style="width : 180px;">
                                            <f:selectItems value="#{managerAlumno.comboTipoFamiliar}"/>
                                        </h:selectOneMenu>
                                    </td>

                                    <td width="20%"></td>
                                </tr>

                                <tr>
                                    <td width="30%">Forma de Pago:</td>
                                    <td width="70%">
                                        <h:selectOneMenu id="iFormaPago" value="#{managerAlumno.b_alu_forma_pago}" style="width : 180px;">
                                            <f:selectItems value="#{managerAlumno.comboFormaPago}"/>
                                        </h:selectOneMenu>
                                    </td>

                                    <td width="20%"></td>
                                </tr>
                                <tr>
                                    <td width="30%">Unidad:</td>
                                    <td width="70%">
                                        <h:selectOneMenu id="iAluUnidad" value="#{managerAlumno.b_alu_unidad}" style="width : 180px;">
                                            <f:selectItems value="#{managerAlumno.comboUnidad}"/>
                                        </h:selectOneMenu>
                                    </td>


                                    <td width="20%"></td>
                                </tr>
                                <tr>
                                    <td width="30%" >Monto de Pago:</td>
                                    <td width="70%">
                                        <h:inputText id="iMonPag" disabled="true"  style=" width : 180px;" value="#{managerAlumno.b_alu_monto_pago}"/>
                                    </td>
                                    <td align="right" width="20%">
                                    </td>
                                </tr>

                            </table>
                        </h:form>
                    </div>
                </rich:modalPanel>
                <rich:modalPanel  id="mp1" minHeight="450" minWidth="780" height="450" width="780" zindex="2000" onshow="showDiv2();">
                    <rich:effect  name="hideDiv2"  for="contentDiv1" type="Fade" />
                    <rich:effect  name="showDiv2"  for="contentDiv1" type="Appear" />
                    <div id="contentDiv1">
                        <f:facet name="header">
                            <h:outputText value="Datos de Alumno" />
                        </f:facet>
                        <f:facet name="controls">
                            <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp1')" title="Cerrar"/>
                        </f:facet>
                        <h:form>
                            <a4j:region id="region">
                                <table width="100%" style="font-size:10px; font-family:verdana"  cellspacing="0" border="0">
                                    <tr>
                                        <td colspan="2">DATOS PERSONALES<h:inputHidden id="iId2" value="#{managerAlumno.b_id_alumno}"/></td>
                                        <td COLSPAN="2" align="right">
                                            <h:outputText value="#{managerAlumno.b_paterno_alumno} #{managerAlumno.b_materno_alumno} #{managerAlumno.b_nombre_alumno}" id="datos"/>
                                            <a4j:commandButton action="#{managerAlumno.guardar}" image="/Imagenes/actions/filesave.png" title="Guardar" oncomplete="javascript:alert('Se guardo correctamente.');Richfaces.hideModalPanel('mp1')"/>
                                        </td>
                                        <td >
                                        </td>
                                    </tr>
                                    <tr><td colspan="4"><hr size="1"></td></tr>
                                    <tr>
                                        <td>Nom. Colegio:</td>
                                        <td>
                                            <h:inputText label="Colegio" value="#{managerAlumno.b_colegio}" style="width : 180px;" id="Colegio"  rendered="true" tabindex="1" />
                                        </td>
                                        <td>Documentos Entregados:</td>
                                        <td>
                                            <h:selectManyCheckbox  id="Documentos" value="#{managerAlumno.documentos}" style="font-size: 10px">
                                                <f:selectItems value="#{managerAlumno.comboDocumentos}"/>
                                            </h:selectManyCheckbox>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>

                                        </td>
                                        <td colspan="2">Ultima fecha de entrega de Doc. &nbsp;&nbsp;&nbsp;
                                            <rich:calendar datePattern="dd/MM/yyyy" value="#{managerAlumno.w_ultima_fecha}" id="iUltimaFecha" />
                                        </td>

                                    </tr>
                                    <tr>
                                        <td >Tipo Colegio:</td>
                                        <td>
                                            <h:selectOneRadio id="iTipoColegio" value="#{managerAlumno.tipoColegio}" tabindex="2">
                                                <f:selectItems value="#{managerAlumno.comboTipoColegio}"/>
                                            </h:selectOneRadio>
                                        </td>
                                        <td>Col. Departamento:</td>
                                        <td>
                                            <h:selectOneMenu id="rDepartamento" value="#{managerAlumno.departamentos1}" style="width : 180px;" tabindex="13">
                                                <f:selectItems value="#{managerAlumno.comboDepartamentos1}" />
                                                <a4j:support event="onchange"  reRender="rProvincia,rDistrito"/>
                                            </h:selectOneMenu>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="20%">Sexo:</td>
                                        <td width="80%">
                                            <h:selectOneRadio id="iSexo" value="#{managerAlumno.sexo}" tabindex="3">
                                                <f:selectItems value="#{managerAlumno.combosexo}" />
                                            </h:selectOneRadio>
                                        </td>
                                        <td>Col. Provincia:
                                        </td>
                                        <td>
                                            <h:selectOneMenu  id="rProvincia"  value="#{managerAlumno.provincias1}" style="width : 180px;" tabindex="14" >
                                                <f:selectItems value="#{managerAlumno.comboProvincias1}" />
                                                <a4j:support event="onchange"  reRender="rDistrito"/>
                                            </h:selectOneMenu>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>E-Mail:</td>
                                        <td width="70%">
                                            <h:inputText id="iCorreo" value="#{managerAlumno.b_correo}" style="width : 180px;" rendered="true" tabindex="4"/>
                                        </td>
                                        <td>Col. Distrito:</td>
                                        <td>
                                            <h:selectOneMenu  id="rDistrito"  value="#{managerAlumno.distrito1}" style="width : 180px;" tabindex="15" >
                                                <f:selectItems value="#{managerAlumno.comboDistrito1}" />
                                            </h:selectOneMenu>
                                        </td>
                                        <td>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Celular</td>
                                        <td>
                                            <h:inputText id="TelefonoCelular" value="#{managerAlumno.b_telefono_celular}" label="Telefono Celular" style="width : 180px;" tabindex="7"/>
                                        </td>
                                        <td>Tipo Preparaci�n:</td>
                                        <td><h:selectOneMenu id="iPreparacion" value="#{managerAlumno.preparacion}" style="width : 180px;" tabindex="16">
                                                <f:selectItems value="#{managerAlumno.comboPreparacion}"/>
                                            </h:selectOneMenu>
                                        </td>
                                        <td>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Tipo Doc.:</td>
                                        <td>
                                            <h:selectOneMenu id="iTipoDocumento" value="#{managerAlumno.tipoDocumento}" style="width : 180px;" tabindex="6" >
                                                <f:selectItems value="#{managerAlumno.comboTipoDocumento}"/>
                                            </h:selectOneMenu>
                                        </td>
                                        <td>Nom. Academia:
                                        </td>
                                        <td>
                                            <h:selectOneMenu id="iAcademia" value="#{managerAlumno.academias}" style="width : 180px;" tabindex="17">
                                                <f:selectItems value="#{managerAlumno.comboAcademias}"/>
                                            </h:selectOneMenu>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Num. Doc:</td>
                                        <td>
                                            <h:inputText id="iNumeroDocumento" value="#{managerAlumno.b_numeroDocumento}" style="width : 180px;" rendered="true" />
                                        </td>
                                        <td>Academia(Otros):</td>
                                        <td>
                                            <h:inputText value="#{managerAlumno.b_academiaOtros}" style="width : 180px;" id="AcademiaOtros"  rendered="true" tabindex="24"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="20%">Fec. Nacimiento:</td>
                                        <td width="80%">
                                            <rich:calendar  id="iCreacion"  value="#{managerAlumno.b_nacimiento}" datePattern="dd/MM/yyyy" />
                                        </td>
                                        <td>A�os:</td>
                                        <td>
                                            <table  cellspacing="0" border="0">
                                                <tr>
                                                    <td>
                                                        <h:selectOneMenu id="iAniosPrep" value="#{managerAlumno.aniosPreparacion}"  tabindex="19"  style="width : 40px;">
                                                            <f:selectItems value="#{managerAlumno.comboAniosPreparacion}"/>
                                                        </h:selectOneMenu>
                                                    </td>
                                                    <td>
                                                    </td>
                                                    <td style="font-size: 10px">Meses:</td>
                                                    <td><h:selectOneMenu id="iMesesPrep" value="#{managerAlumno.mesesPreparacion}" tabindex="20"  style="width : 40px;">
                                                            <f:selectItems value="#{managerAlumno.comboMesesPreparacion}"/>
                                                        </h:selectOneMenu>
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td>
                                        </td>
                                        <td>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Nac. Departamento:</td>
                                        <td>
                                            <h:selectOneMenu id="nDepartamento" value="#{managerAlumno.departamentos}" style="width : 180px;" tabindex="9">
                                                <f:selectItems value="#{managerAlumno.comboDepartamentos}" />
                                                <a4j:support event="onchange"  reRender="nProvincia,nDistrito"/>
                                            </h:selectOneMenu>
                                        </td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>Nac. Provincia:</td>
                                        <td>
                                            <h:selectOneMenu  id="nProvincia"  value="#{managerAlumno.provincias}" style="width : 180px;" tabindex="10">
                                                <f:selectItems value="#{managerAlumno.comboProvincias}" />
                                                <a4j:support event="onchange"  reRender="nDistrito" />
                                            </h:selectOneMenu>
                                        </td>
                                        <td>Tel�f.:</td>
                                        <td style="font-size: 10px">
                                            <h:inputText label="Telefono" id="iTelefono" value="#{managerAlumno.b_telefono_fijo}" style="width : 180px;" rendered="true" tabindex="28"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Nac. Distrito:</td>
                                        <td>
                                            <h:selectOneMenu  id="nDistrito"  value="#{managerAlumno.distrito}" style="width : 180px;" tabindex="11">
                                                <f:selectItems value="#{managerAlumno.comboDistrito}" />
                                            </h:selectOneMenu>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Dist. Lima</td>
                                        <td>
                                            <h:selectOneMenu  id="comboDistritoLima"  value="#{managerAlumno.distritoLima}" style="width : 180px;" tabindex="22">
                                                <f:selectItems value="#{managerAlumno.comboDistritoLima}" />
                                            </h:selectOneMenu>
                                        </td>
                                        <td>Dir. en Lima:</td>
                                        <td>
                                            <h:inputText id="direccionLima" value="#{managerAlumno.b_direccion_lima}" style="width : 180px;" tabindex="30"/>
                                        </td>
                                    </tr>
                                </table>
                            </a4j:region>
                        </h:form>
                    </div>
                </rich:modalPanel>
                <rich:modalPanel  id="mp4" minHeight="300" minWidth="550" height="300" width="500" zindex="2000"  onshow="showDiv();">
                    <rich:effect  name="hideDiv"  for="contentDivA" type="Fade" />
                    <rich:effect  name="showDiv"  for="contentDivA" type="Appear" />
                    <div id="contentDivA">
                        <f:facet name="header">
                            <h:outputText value="Asistencia de Alumno" />
                        </f:facet>
                        <f:facet name="controls">
                            <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp4')" title="Cerrar"/>
                        </f:facet>
                        <h:form>
                            <table width="100%" style="font-size:10px; font-family:verdana" cellspacing="0" border="0">
                                <tr >
                                    <td>
                                        <a4j:commandButton value="<<" actionListener="#{managerAlumno.disminuyeAnio}" action="#{managerAlumno.evaluaAsistencia}" reRender="tablaAsistencia,aMes,aAnio"/>
                                        <a4j:commandButton value="<" actionListener="#{managerAlumno.disminuyeMes}" action="#{managerAlumno.evaluaAsistencia}" reRender="tablaAsistencia,aMes,aAnio"/>
                                        <a4j:commandButton value=">" actionListener="#{managerAlumno.aumentaMes}" action="#{managerAlumno.evaluaAsistencia}" reRender="tablaAsistencia,aMes,aAnio"/>
                                        <a4j:commandButton value=">>" actionListener="#{managerAlumno.aumentaAnio}" action="#{managerAlumno.evaluaAsistencia}" reRender="tablaAsistencia,aMes,aAnio"/>
                                    </td>
                                    <td align="right" colspan="4">
                                        <h:inputHidden id="aId" value="#{managerAlumno.b_id_alumno}"/>
                                        <h:inputHidden id="aCodigo"  value="#{managerAlumno.b_codigo_alumno}"/>
                                        <h:outputText id="aCodigo1"  value="#{managerAlumno.b_codigo_alumno}- "/>
                                        <h:outputText id="aPaterno"  value="#{managerAlumno.b_paterno_alumno}- "/>
                                        <h:outputText id="aMaterno"  value="#{managerAlumno.b_materno_alumno}- "/>
                                        <h:outputText id="aNombre"  value="#{managerAlumno.b_nombre_alumno}- "/>
                                        <h:outputText id="aMes" value="#{managerAlumno.b_mes}- "/>
                                        <h:outputText id="aAnio" value="#{managerAlumno.b_anio} "/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="8">
                                        <rich:dataTable id="tablaAsistencia"  rows="10" onRowMouseOver="this.style.backgroundColor='#F1F1F1'" onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'" cellpadding="0" cellspacing="0" width="100%" border="0" value="#{managerAlumno.listaAsistencia}" var="Asistencia">
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Lunes" />
                                                </f:facet>
                                                <h:outputText value="#{Asistencia.a_lunes}" style="#{Asistencia.e_lunes}" title="#{Asistencia.h_lunes}"/>
                                            </h:column>
                                            <h:column >
                                                <f:facet name="header">
                                                    <h:outputText value="Martes"/>
                                                </f:facet>
                                                <h:outputText value="#{Asistencia.a_martes}" style="#{Asistencia.e_martes}" title="#{Asistencia.h_martes}"/>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Miercoles"/>
                                                </f:facet>
                                                <h:outputText value="#{Asistencia.a_miercoles}" style="#{Asistencia.e_miercoles}"  title="#{Asistencia.h_miercoles}"/>
                                            </h:column>

                                            <h:column >
                                                <f:facet name="header">
                                                    <h:outputText value="Jueves" title="#{Asistencia.h_jueves}"/>
                                                </f:facet>
                                                <h:outputText value="#{Asistencia.a_jueves}" style="#{Asistencia.e_jueves}" title="#{Asistencia.h_jueves}"/>
                                            </h:column>

                                            <h:column >
                                                <f:facet name="header">
                                                    <h:outputText value="Viernes" title="#{Asistencia.h_viernes}"/>
                                                </f:facet>
                                                <h:outputText value="#{Asistencia.a_viernes}" style="#{Asistencia.e_viernes}" title="#{Asistencia.h_viernes}"/>
                                            </h:column>

                                            <h:column >
                                                <f:facet name="header">
                                                    <h:outputText value="Sabado" title="#{Asistencia.h_sabado}"/>
                                                </f:facet>
                                                <h:outputText value="#{Asistencia.a_sabado}" style="#{Asistencia.e_sabado}" title="#{Asistencia.h_sabado}"/>
                                            </h:column>
                                            <h:column >

                                                <f:facet name="header">
                                                    <h:outputText value="Domingo" title="#{Asistencia.h_domingo}"/>
                                                </f:facet>
                                                <h:outputText value="#{Asistencia.a_domingo}" style="#{Asistencia.e_domingo}" title="#{Asistencia.h_domingo}"/>
                                            </h:column>
                                        </rich:dataTable>
                                    </td>
                                </tr>
                            </table>
                        </h:form>
                    </div>
                </rich:modalPanel>


                <rich:modalPanel  id="mp5" minHeight="400" minWidth="550" height="450" width="500" zindex="2000"  onshow="showDiv();">
                    <rich:effect  name="hideDiv"  for="contentDivA" type="Fade" />
                    <rich:effect  name="showDiv"  for="contentDivA" type="Appear" />
                    <div id="contentDivX">
                        <f:facet name="header">
                            <h:outputText value="Asistencia de Alumno" />
                        </f:facet>
                        <f:facet name="controls">
                            <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp5')" title="Cerrar"/>
                        </f:facet>
                        <h:form>
                            <table width="100%" style="font-size:10px; font-family:verdana" cellspacing="0" border="0">
                                <tr>
                                    <td>
                                        <h:inputText id="pId" value="#{managerAlumno.b_id_alumno}"/>
                                        <h:inputText id="pCodigo"  value="#{managerAlumno.b_codigo_alumno}"/>
                                    </td>
                                </tr>
                                <tr >
                                    <td>
                                        <a4j:mediaOutput  style="width : 500px;height: 380px"
                                                          element="object" type="application/pdf"
                                                          cacheable="false" rendered="true"
                                                          standby="cargando..."
                                                          createContent="#{managerAlumno.reporte}"
                                                          mimeType="application/pdf"
                                                          value="#{managerAlumno.b_id_alumno}"/>
                                    </td>
                                </tr>
                            </table>

                        </div>
                    </h:form>
                </rich:modalPanel>
            </rich:panel>
        </f:view>
    </body>
</html>

