<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Matricula</title>
       <!-- <script type="text/javascript" language="JavaScript" src="../script/script.js"></script>-->
       <style>
        .fuente{
            font-size: 12px;
            font-weight: bold;
            color: red;
        }
        .cabeceraAr{
            font-size: 12px;
            font-weight: bold;
        }
    </style>
    <script>
        
        function onclickSeleccionar(){
            //alert('entro al metodo');
            document.getElementById('form1:buscar').onclick();
            alert('entro al metodo');
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
                    <table width="100%" style="font-size:10px; font-family:verdana" cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            <td width="20%" colspan="2" ><strong>MATRICULA</strong></td>
                            <td width="30%">
                            </td>
                            <%--<td width="30%" align="right">
                                <h:graphicImage value="/Imagenes/actions/filenew.png" style="border-width: 0px;" width="16">
                                    <a4j:support event="onclick" onsubmit="hideDiv({duration:0.1});" oncomplete="Richfaces.showModalPanel('mp',{width:100, top:130})" action="#{managerMatricula.Nuevo}" reRender="iId,iCodigo,iPaterno,iMaterno,iNombre,iDni,iMail,iIdTipo,iIdModalidad,iIdEstado,iIdFacIng,iIdEspIng,iIdFacAct,iIdEspAct,iSemestre,semestre_vigente,iIdPlanIng,iIdPlanAct" />
                                </h:graphicImage>
                            </td>--%>
                            <td width="30%" align=right>
                              
                            </td>
                            <td width="30%" colspan="4" align="right">

                                  <rich:spacer width="10"/>
                                <h:graphicImage value="/Imagenes/actions/fileprint.png" title="Imprimir Listado"/></td>
                            <td align=right colspan="3" width="50%">
                                <h:commandButton type="button" id="buscar" value="" action="#{managerMatricula.Seleccionar}" image="/Imagenes/actions/viewmag.png" title="Buscar"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="10"><hr width="100%" size="1"></td>
                        </tr>
                        <%--<tr>
                            <td width="20%" colspan="6">
                                <h:commandButton value="imprime" action="#{managerMatricula.Imprimir}"/>
                                <a4j:commandButton action="#{managerMatricula.Imprimir}" value="imprime con ajax">
                                </a4j:commandButton>
                            </td>
                                <td>
                            </td>
                        </tr>--%>
                        <tr><td></td>
                        </tr>
                        <tr>
                            <td width="10%">Código:</td>
                            <td width="30%">
                                <h:inputText value="#{managerMatricula.b_codigo}" style="width : 180px;"/>
                            </td>
                            <td width="20%"></td>
                            <td>Facultad:</td>
                            <td>
                                <h:selectOneMenu id="FacuBuscar" value="#{managerMatricula.b_id_fac_act}"  style="width : 180px;">
                                    <f:selectItems value="#{managerMatricula.comboFacultad_buscar}" />
                                </h:selectOneMenu>
                            </td>
                            <td width="20%" colspan="2"></td>
                        </tr>
                        <tr>
                            <td width="10%">Ap. Paterno:</td>
                            <td width="30%">
                                <h:inputText value="#{managerMatricula.b_paterno}" style="width : 180px;"/>
                            </td>
                            <td width="20%"></td>
                            <td >Especialidad:</td>
                            <td>
                                <h:selectOneMenu id="EspeBuscar" value="#{managerMatricula.b_id_esp_act}"  style="width : 180px;">
                                    <f:selectItems value="#{managerMatricula.comboEspecialidad_buscar}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%">Ap. Materno:</td>
                            <td><h:inputText value="#{managerMatricula.b_materno}" style="width : 180px;"/></td>
                            <td width="20%"></td>
                            <td>Semestre:</td>
                            <td>
                                <h:selectOneMenu id="SemBuscar" value="#{managerMatricula.semestre_Listado}"  style="width : 180px;">
                                    <f:selectItems value="#{managerMatricula.comboSemestres_Listado}" />
                                    <a4j:support event="onchange" action="#{managerMatricula.Seleccionar}" reRender="tablaMaster,mp"/>
                                </h:selectOneMenu>
                            </td>
                            <td width="20%" colspan="2"></td>
                        </tr>
                        <tr>
                            <td width="10%">Nombres:</td>
                            <td><h:inputText value="#{managerMatricula.b_nombre}" style="width : 180px;"/></td>
                            <td width="20%" colspan="4"></td>
                        </tr>

                        <tr>
                            <td colspan="10" width="100%">
                                <hr width="100%" size="1">
                            </td>
                        </tr>
                        <tr>
                            <td>
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
                                    width="100%" border="0" value="#{managerMatricula.listaAlumno}" var="Alu">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id" />
                            </f:facet>
                            <h:outputText value="#{Alu.b_id}"/>
                            <f:param value="#{Alu.flag_ver}" id="flag"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Cod." />
                            </f:facet>
                            <h:outputText value="#{Alu.b_codigo}" rendered="#{Alu.view}"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Apellido P/M y Nombres" />
                            </f:facet>
                            <h:outputText value="#{Alu.b_paterno} #{Alu.b_materno} #{Alu.b_nombre}" rendered="#{Alu.view}"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Esp. Actual" />
                            </f:facet>
                            <h:outputText value="#{Alu.b_esp_act}" rendered="#{Alu.view}"/>
                        </h:column>
                        <h:column ><f:facet name="header"><h:outputText value="Tipo" /></f:facet>
                            <h:outputText value="#{Alu.b_tipo}"/>
                        </h:column>
                        <h:column >
                            <f:facet name="header"><h:outputText value="Registra" /></f:facet>
                            <h:outputText value="#{Alu.b_periodo}"/>
                        </h:column>
                        <h:column ><f:facet name="header"><h:outputText value="" /></f:facet>
                            <h:graphicImage value="#{Alu.imagen}" style="border-width: 0px;" height="16" title="ver cursos">
                                <a4j:support event="onclick" actionListener="#{Alu.Ver}" reRender="iId,tablaMaster"/>
                            </h:graphicImage>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="" />
                            </f:facet>
                            <p align="right">

                                <a4j:commandButton rendered="#{Alu.verDeuda}" image="/Imagenes/actions/warning.png" title="Ver Detalles" actionListener="#{managerMatricula.VerDetalleDeuda}" oncomplete="#{managerMatricula.d_oncomplete}" reRender="d_codigo_alu,d_nombre_alu,tablaDeuda,tablaDeudaBar"/>
                                <a4j:commandButton rendered="#{Alu.verMatricular and Alu.w_tipo_estado == '0'}" image="/Imagenes/actions/matricular.gif" title="Matricular" action="#{managerMatricula.cursosAbiertos}" actionListener="#{managerMatricula.EditarFila}" oncomplete="#{managerMatricula.oncomplete}" reRender="iId,iSem_,iCodigo,iPaterno,iMaterno,iNombre,semestre_vigente,tablaCursos,foto,iDocumentos"/>

                                <f:param id="p_id" value="#{Alu.b_id}" />
                                <f:param id="p_codigo"   value="#{Alu.b_codigo}" />
                                <f:param id="p_paterno"   value="#{Alu.b_paterno}" />
                                <f:param id="p_materno"   value="#{Alu.b_materno}" />
                                <f:param id="p_nombre"   value="#{Alu.b_nombre}" />
                                <f:param id="p_dni"   value="#{Alu.b_dni}" />
                                <f:param id="p_mail"   value="#{Alu.b_mail}" />
                                <f:param id="p_id_tipo"   value="#{Alu.b_tipo}" />
                                <f:param id="p_id_modalidad"   value="#{Alu.b_id_modalidad}" />
                                <f:param id="p_id_estado"   value="#{Alu.b_estado}" />
                                <f:param id="p_id_fac_ing"   value="#{Alu.b_id_fac_ing}" />
                                <f:param id="p_id_esp_ing"   value="#{Alu.b_id_esp_ing}" />
                                <f:param id="p_id_fac_act"   value="#{Alu.b_id_fac_act}" />
                                <f:param id="p_id_esp_act"   value="#{Alu.b_id_esp_act}" />
                                <f:param id="p_id_semestre"   value="#{Alu.b_id_semestre}" />
                                <f:param id="semestre_vigente"   value="#{Alu.semestre_vigente}" />
                                <f:param id="p_plan_ing"   value="#{Alu.b_plan_ing}" />
                                <f:param id="p_plan_act"   value="#{Alu.b_plan_act}" />
                                <f:param id="p_id_plan_ing"   value="#{Alu.b_id_plan_ing}" />
                                <f:param id="p_id_plan_act"   value="#{Alu.b_id_plan_act}" />
                                <f:param id="p_id_matricula"   value="#{Alu.matricu_id}" />
                                <f:param id="p_mat_tipo"   value="#{Alu.b_mat_tipo}" />
                            </p>
                        </h:column>
                        <h:column>
                            <f:facet name="header"><h:outputText value="" /></f:facet>
                            <a4j:commandButton rendered="#{Alu.verDeuda}" image="/Imagenes/actions/warning.png" title="Ver Detalles" actionListener="#{managerMatricula.VerDetalleDeuda}" oncomplete="#{managerMatricula.d_oncomplete}" reRender="d_codigo_alu,d_nombre_alu,tablaDeuda,tablaDeudaBar"/>
                            <a4j:commandButton rendered="#{Alu.verRectificar and Alu.w_tipo_estado == '0'}" image="/Imagenes/actions/rectificacion.gif"  title="Rectificar Matricula"  action="#{managerMatricula.rectificarMatricula}" actionListener="#{managerMatricula.EditarFila}" oncomplete="#{managerMatricula.oncomplete}"  reRender="iId1,iSem_1,iCodigo1,iPaterno1,iMaterno1,iNombre1,semestre_vigente1,tablaCursos1,foto1,iDocumentos1"/>
                            
                            
                            <a4j:commandButton action="#{mBAlumnoEstado.abrirReserva}" rendered="#{Alu.w_tipo_estado == '1'}" image="/Imagenes/actions/reservar.gif" title="Reservar Matricula" reRender="fEstadoAlu" oncomplete="#{mBAlumnoEstado.oncomplete}">
                                <f:setPropertyActionListener target="#{mBAlumnoEstado.p_alu_id}" value="#{Alu.b_id}"/>
                                <f:setPropertyActionListener target="#{mBAlumnoEstado.p_sem_id}" value="#{managerMatricula.semestre_Listado}"/>
                            </a4j:commandButton><rich:spacer width="5"/>
                            <a4j:commandButton action="#{mBAlumnoEstado.abrirRetiroCiclo}" rendered="#{Alu.w_tipo_estado == '2'}" image="/Imagenes/actions/retirociclo.png" title="Retiro del Ciclo" reRender="fEstadoAlu" oncomplete="#{mBAlumnoEstado.oncomplete}">
                                <f:setPropertyActionListener target="#{mBAlumnoEstado.p_alu_id}" value="#{Alu.b_id}"/>
                                <f:setPropertyActionListener target="#{mBAlumnoEstado.p_sem_id}" value="#{managerMatricula.semestre_Listado}"/>
                            </a4j:commandButton><rich:spacer width="5"/>
                            <a4j:commandButton action="#{mBAlumnoEstado.abrirRetiroUniversitario}" rendered="#{Alu.w_tipo_estado == '3'}" image="/Imagenes/actions/retirouch.png" title="Renuncia" reRender="fEstadoAlu" oncomplete="#{mBAlumnoEstado.oncomplete}">
                                <f:setPropertyActionListener target="#{mBAlumnoEstado.p_alu_id}" value="#{Alu.b_id}"/>
                                <f:setPropertyActionListener target="#{mBAlumnoEstado.p_sem_id}" value="#{managerMatricula.semestre_Listado}"/>
                            </a4j:commandButton>
                            
                        </h:column>
                        <h:column>
                            <f:facet name="header"><h:outputText value="" /></f:facet>
                            <h:commandButton image="/Imagenes/actions/anular.gif" rendered="#{Alu.verEliminar}" title="Eliminar Matricula" actionListener="#{managerMatricula.EliminarMatricula}" action="#{managerMatricula.Seleccionar}"  onclick="javascript:return (confirm('¿Esta realmente seguro de Eliminar?'));"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header"><h:outputText value="" /></f:facet>
                            <h:graphicImage value="/Imagenes/actions/fileprint.png" rendered="#{Alu.verImprimir1}" style="border-width: 0px;cursor: pointer" title="Imprimir Ficha">
                                <a4j:support event="onclick" actionListener="#{managerMatricula.ImprimirFicha}" oncomplete="javascript:Richfaces.showModalPanel('mp7')" reRender="titulo,reporte"/>
                            </h:graphicImage>
                        </h:column>
                        <h:column>
                            <f:facet name="header"><h:outputText value="" /></f:facet>
                            <h:graphicImage value="/Imagenes/actions/fileprint.png" rendered="#{Alu.verImprimir2}" style="border-width: 0px;cursor: pointer;" title="Imprimir Consolidado">
                                <a4j:support event="onclick" actionListener="#{managerMatricula.ImprimirConsolidado}" oncomplete="javascript:Richfaces.showModalPanel('mp7')" reRender="titulo,reporte"/>
                            </h:graphicImage>
                        </h:column>
                        <h:column>
                            <f:facet name="header"><h:outputText value="" /></f:facet>
                            <a4j:commandButton rendered="#{Alu.verDeuda}" image="/Imagenes/actions/warning.png" title="Ver Detalles" actionListener="#{managerMatricula.VerDetalleDeuda}" oncomplete="#{managerMatricula.d_oncomplete}" reRender="d_codigo_alu,d_nombre_alu,tablaDeuda,tablaDeudaBar"/>
                            <% /*<h:commandButton rendered="#{Alu.verReservar}" image="/Imagenes/actions/reservar.gif" title="Reservar Matricula" actionListener="#{managerMatricula.ReservarMatricula}" action="#{managerMatricula.Seleccionar}" onclick="javascript:return (confirm('¿Esta realmente seguro de Reservar la Matricula?'));"/>*/%>
                            <a4j:commandButton action="#{mBAlumnoEstado.abrirReserva}" rendered="#{Alu.w_tipo_estado == '0'}" image="/Imagenes/actions/reservar.gif" title="Reservar Matricula" reRender="fEstadoAlu" oncomplete="#{mBAlumnoEstado.oncomplete}">
                                <f:setPropertyActionListener target="#{mBAlumnoEstado.p_alu_id}" value="#{Alu.b_id}"/>
                                <f:setPropertyActionListener target="#{mBAlumnoEstado.p_sem_id}" value="#{managerMatricula.semestre_Listado}"/>
                            </a4j:commandButton><rich:spacer width="5"/>
                            <a4j:commandButton action="#{mBAlumnoEstado.abrirRetiroCiclo}" rendered="#{Alu.w_tipo_estado == '0'}" image="/Imagenes/actions/retirociclo.png" title="Retiro del Ciclo" reRender="fEstadoAlu" oncomplete="#{mBAlumnoEstado.oncomplete}">
                                <f:setPropertyActionListener target="#{mBAlumnoEstado.p_alu_id}" value="#{Alu.b_id}"/>
                                <f:setPropertyActionListener target="#{mBAlumnoEstado.p_sem_id}" value="#{managerMatricula.semestre_Listado}"/>
                            </a4j:commandButton><rich:spacer width="5"/>
                            <a4j:commandButton action="#{mBAlumnoEstado.abrirRetiroUniversitario}" rendered="#{Alu.w_tipo_estado == '0'}" image="/Imagenes/actions/retirouch.png" title="Renuncia" reRender="fEstadoAlu" oncomplete="#{mBAlumnoEstado.oncomplete}">
                                <f:setPropertyActionListener target="#{mBAlumnoEstado.p_alu_id}" value="#{Alu.b_id}"/>
                                <f:setPropertyActionListener target="#{mBAlumnoEstado.p_sem_id}" value="#{managerMatricula.semestre_Listado}"/>
                            </a4j:commandButton>
                        </h:column>
                        <h:column>
                            <f:facet name="header"><h:outputText value="" /></f:facet>
                            <a4j:commandButton rendered="#{Alu.verAlerta}" image="/Imagenes/actions/warning_y.gif" title="Ver Detalles"/>
                        </h:column>
                        <rich:subTable value="#{Alu.detalle_s}" var="det" rendered="#{Alu.ver}" id="subtable"
                                       onRowMouseOver="this.style.backgroundColor='#F8F8F8'"
                                       onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Cod. Curso" />
                                </f:facet>
                                <h:outputText value="#{det.detalle_id}" />
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Cod. Curso" />
                                </f:facet>
                                <h:outputText value="#{det.detalle_codigoCurso}" />
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Nom. Curso" />
                                </f:facet>
                                <h:outputText value="#{det.detalle_nombreCurso}" />
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Nom. Seccion" />
                                </f:facet>
                                <h:outputText value="#{det.detalle_codigoSeccion}" />
                            </h:column>
                        </rich:subTable>
                    </rich:dataTable>
                </h:form>
                <rich:modalPanel  id="mp" minHeight="580" minWidth="750" height="580" width="750" zindex="2000"  onshow="showDiv();">
                    <rich:effect  name="hideDiv"  for="contentDiv" type="Fade" />
                    <rich:effect  name="showDiv"  for="contentDiv" type="Appear" />
                    <f:facet name="header">
                        <h:outputText value="Registro de Matricula" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp')" title="Cerrar"/>
                    </f:facet>
                    <h:form>
                        <div id="contentDiv">
                            <table width="100%" style="font-size:10px; font-family:verdana" cellpadding="0" cellspacing="0" border="0">
                                <tr >
                                    <td align="right" colspan="4">
                                        <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"  actionListener="#{managerMatricula.Matricular}" action="#{managerMatricula.Seleccionar}" reRender="tablaMaster, barra, dupHorario" oncomplete="#{managerMatricula.oncomplete}"/>
                                        <%/*
                                             * <a4j:commandButton
                                             * image="/Imagenes/actions/filesave.png"
                                             * title="Guardar"
                                             * action="#{managerMatricula.Matricular}"
                                             * reRender="tablaMaster, barra"
                                             * oncomplete="#{managerMatricula.oncomplete}"/>
                                             */%>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="5"><hr size="1">
                                    </td>
                                </tr>
                                <tr>
                                    <td>Semestre Seleccionado:</td>
                                    <td><h:outputText id="semestre_vigente"  style=" width : 180px;" value="#{managerMatricula.semestre_seleccionado}" />
                                    </td>
                                    <td align="right" width="20%" rowspan="4" colspan="4">
                                        <a4j:mediaOutput id="foto" style="width : 100px;" element="img" cacheable="false" rendered="true" standby="cargando..."  createContent="#{managerMatricula.imagen}"  mimeType="image/png" value="#{managerMatricula.b_id_alumno}"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%" >Codigo:</td>
                                    <td width="70%">
                                        <%/*
                                             * <h:inputText id="iId"
                                             * value="#{managerMatricula.b_id_u}"/>
                                             */%>
                                        <h:inputHidden id="iId" value="#{managerMatricula.b_id_alumno}"/>
                                        <h:inputHidden id="iSem_" value="#{managerMatricula.b_id_semestre}"/>
                                        <h:outputText id="iCodigo"  style=" width : 180px;" value="#{managerMatricula.b_codigo_alumno}" />
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%" >Apellido Paterno:</td>
                                    <td width="70%">
                                        <h:outputText id="iPaterno"  style=" width : 180px;" value="#{managerMatricula.b_paterno_alumno}" />
                                    </td>
                                    <td align="right" width="20%">
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%" >Apellido Materno:</td>
                                    <td width="70%">
                                        <h:outputText id="iMaterno"  style=" width : 180px;" value="#{managerMatricula.b_materno_alumno}" />
                                    </td>
                                    <td align="right" width="20%">
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%" >Nombres:</td>
                                    <td width="70%">
                                        <h:outputText  id="iNombre"  style=" width : 180px;" value="#{managerMatricula.b_nombre_alumno}" />
                                    </td>
                                    <td align="right" width="20%">
                                    </td>
                                </tr>
                                <tr>
                                    <td>Documentos Entregados:</td>
                                        <td>
                                            <h:selectManyCheckbox  id="iDocumentos" disabled="true" value="#{managerMatricula.documentos}" style="font-size: 10px">
                                                <f:selectItems value="#{managerMatricula.comboDocumentos}"/>
                                            </h:selectManyCheckbox>
                                        </td>
                                </tr>
                                <tr>
                                    <td colspan="5" width="100%">
                                        <%/*
                                             * <a4j:commandButton
                                             * action="#{managerMatricula.cursosAbiertos}"/>
                                             */%>
                                             <div style="overflow: auto;height: 400px">
                                             <rich:dataTable  id="tablaCursos" onRowMouseOver="this.style.backgroundColor='#F1F1F1'" onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'" cellpadding="0" cellspacing="0" width="100%" border="0" value="#{managerMatricula.listaCursos}" var="Mat">
                                            <h:column id="cId">
                                                <f:facet name="header">
                                                    <h:outputText value="Id" />
                                                </f:facet>
                                                <h:outputText value="#{Mat.mat_det_id}"/>
                                            </h:column>
                                            <h:column id="cCod">
                                                <f:facet name="header">
                                                    <h:outputText value="Cod." />
                                                </f:facet>
                                                <h:outputText value="#{Mat.mat_det_codigo}"/>
                                            </h:column>
                                            <h:column >
                                                <f:facet name="header">
                                                    <h:outputText value="Nom." />
                                                </f:facet>
                                                <h:outputText value="#{Mat.mat_det_nombre}"/>
                                            </h:column>
                                            <h:column >
                                                <f:facet name="header">
                                                    <h:outputText value="Ciclo." />
                                                </f:facet>
                                                <h:outputText value="#{Mat.mat_det_ciclo}"/>
                                            </h:column>
                                            <h:column >
                                                <f:facet name="header">
                                                    <h:outputText value="Creditos" />
                                                </f:facet>
                                                <h:outputText value="#{Mat.mat_det_creditos}"/>
                                            </h:column>
                                            <h:column >
                                                <f:facet name="header">
                                                    <h:outputText value="Tipo" />
                                                </f:facet>
                                                <h:outputText value="#{Mat.mat_det_tipo}"/>
                                            </h:column>
                                            <h:column >
                                                <f:facet name="header">
                                                    <h:outputText value="Seccion" />
                                                </f:facet>
                                                <h:selectOneMenu value="#{Mat.seccion}" id="seccion">
                                                    <f:selectItems value="#{Mat.comboSecciones}"/>
                                                </h:selectOneMenu>
                                            </h:column>
                                        </rich:dataTable>
                                             </div>
                                    </td>
                                    <td>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="5">
                                        <hr size="1">
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </h:form>
                </rich:modalPanel>

                <rich:modalPanel id="mp7" width="700" height="490">
                    <f:facet name="header">
                        <h:outputText id="titulo" value="#{managerMatricula.tituloReporte}" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp7')" title="Cerrar"/>
                    </f:facet>
                    <h:form>
                        <table>
                            <tr>
                                <td align="center" valign="middle">
                                    <rich:panel>
                                        <a4j:mediaOutput id="reporte" uriAttribute="data" style="width : 650px; height: 420px;" element="object" standby="cargando..." createContent="#{managerMatricula.cargarReporte}" mimeType="application/pdf" value="#{managerMatricula.valorRep}"/>
                                    </rich:panel>
                                </td>
                            </tr>
                        </table>
                    </h:form>
                </rich:modalPanel>

                <rich:modalPanel id="mp6" minHeight="390" minWidth="600" width="600" height="390">
                    <f:facet name="header">
                        <h:outputText value="Detalles de la Deuda" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp6')" title="Cerrar"/>
                    </f:facet>
                    <h:form>
                        <table width="100%" cellpadding="0" cellspacing="0" border="0">
                            <tr>
                                <td colspan="2"><hr width="100%" size="1"></td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center">
                                    <b>Detalles de la Deuda</b>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2"><hr width="100%" size="1"></td>
                            </tr>
                            <tr>
                                <td>Codigo de Alumno:</td>
                                <td><h:outputText id="d_codigo_alu" value="#{managerMatricula.d_codigo_alu}"/></td>
                            </tr>
                            <tr>
                                <td>Nombre del Alumno:</td>
                                <td><h:outputText id="d_nombre_alu" value="#{managerMatricula.d_nombre_alu}"/></td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <rich:datascroller id="tablaDeudaBar" align="right" for="tablaDeuda" maxPages="8" style=" width : 100%;"/>
                                    <rich:dataTable id="tablaDeuda" rows="10"
                                                    onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                    cellpadding="0" cellspacing="0"
                                                    width="100%" border="0" value="#{managerMatricula.d_detalle}" var="ddeu">
                                        <h:column>
                                            <f:facet name="header">
                                                <h:outputText value="Semestre" />
                                            </f:facet>
                                            <h:outputText value="#{ddeu.d_semestre}"/>
                                        </h:column>
                                        <h:column>
                                            <f:facet name="header">
                                                <h:outputText value="Concepto" />
                                            </f:facet>
                                            <h:outputText value="#{ddeu.d_concepto_pag}"/>
                                        </h:column>
                                        <h:column>
                                            <f:facet name="header">
                                                <h:outputText value="F. de Pago" />
                                            </f:facet>
                                            <h:outputText value="#{ddeu.d_fecha_pag}"/>
                                        </h:column>
                                        <h:column>
                                            <f:facet name="header">
                                                <h:outputText value="F. de Prorroga" />
                                            </f:facet>
                                            <h:outputText value="#{ddeu.d_fecha_pro}"/>
                                        </h:column>
                                    </rich:dataTable>
                                </td>
                            </tr>
                        </table>
                    </h:form>
                </rich:modalPanel>

                <rich:modalPanel  id="mp1" minHeight="580" minWidth="750" height="580" width="750" zindex="2000">
                    <f:facet name="header">
                        <h:outputText value="Rectificacion de Matricula" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp1')" title="Cerrar"/>
                    </f:facet>
                    <h:form>
                        <div id="contentDiv1">
                            <table width="100%" style="font-size:10px; font-family:verdana" cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td align="right" colspan="4">
                                        <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"  actionListener="#{managerMatricula.Matricular}" action="#{managerMatricula.Seleccionar}" reRender="tablaMaster, barra, dupHorario" oncomplete="#{managerMatricula.oncomplete}"/>
                                        <%/*
                                             * <a4j:commandButton
                                             * image="/Imagenes/actions/filesave.png"
                                             * title="Guardar"
                                             * action="#{managerMatricula.Matricular}"
                                             * reRender="tablaMaster, barra"
                                             * oncomplete="#{managerMatricula.oncomplete}"/>
                                             */%>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="5"><hr size="1">
                                    </td>
                                </tr>
                                <tr>
                                    <td>Semestre:</td>
                                    <td><h:outputText id="semestre_vigente1"  style=" width : 180px;" value="#{managerMatricula.semestre_actual}" />
                                    </td>
                                    <td align="right" width="20%" rowspan="4" colspan="4">
                                        <a4j:mediaOutput id="foto1" style="width : 100px;" element="img" cacheable="false" rendered="true" standby="cargando..."  createContent="#{managerMatricula.imagen}"  mimeType="image/png" value="#{managerMatricula.b_id_alumno}"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%" >Codigo:</td>
                                    <td width="70%">
                                        <%/*
                                             * <h:inputText id="iId"
                                             * value="#{managerMatricula.b_id_u}"/>
                                             */%>
                                        <h:inputHidden id="iId1" value="#{managerMatricula.b_id_alumno}"/>
                                        <h:inputHidden id="iSem_1" value="#{managerMatricula.b_id_semestre}"/>
                                        <h:outputText id="iCodigo1"  style=" width : 180px;" value="#{managerMatricula.b_codigo_alumno}" />
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%" >Apellido Paterno:</td>
                                    <td width="70%">
                                        <h:outputText id="iPaterno1"  style=" width : 180px;" value="#{managerMatricula.b_paterno_alumno}" />
                                    </td>
                                    <td align="right" width="20%">
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%" >Apellido Materno:</td>
                                    <td width="70%">
                                        <h:outputText id="iMaterno1"  style=" width : 180px;" value="#{managerMatricula.b_materno_alumno}" />
                                    </td>
                                    <td align="right" width="20%">
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%" >Nombres:</td>
                                    <td width="70%">
                                        <h:outputText  id="iNombre1"  style=" width : 180px;" value="#{managerMatricula.b_nombre_alumno}" />
                                    </td>
                                    <td align="right" width="20%">
                                    </td>
                                </tr>
                                <tr>
                                    <td>Documentos Entregados:</td>
                                        <td>
                                            <h:selectManyCheckbox  id="iDocumentos1" disabled="true" value="#{managerMatricula.documentos}" style="font-size: 10px">
                                                <f:selectItems value="#{managerMatricula.comboDocumentos}"/>
                                            </h:selectManyCheckbox>
                                        </td>
                                </tr>
                                <tr>
                                    <td colspan="5" width="100%">
                                        <%/*
                                             * <a4j:commandButton
                                             * action="#{managerMatricula.cursosAbiertos}"/>
                                             */%>
                                             <div style="overflow: auto;height: 400px">
                                        <rich:dataTable id="tablaCursos1" onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                        onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                        cellpadding="0" cellspacing="0" width="100%" border="0"
                                                        value="#{managerMatricula.listaCursos}" var="Mat">
                                            <h:column id="cId">
                                                <f:facet name="header">
                                                    <h:outputText value="Id" />
                                                </f:facet>
                                                <h:outputText value="#{Mat.mat_det_id}"/>
                                            </h:column>
                                            <h:column id="cCod">
                                                <f:facet name="header">
                                                    <h:outputText value="Cod." />
                                                </f:facet>
                                                <h:outputText value="#{Mat.mat_det_codigo}"/>
                                            </h:column>
                                            <h:column >
                                                <f:facet name="header">
                                                    <h:outputText value="Nom." />
                                                </f:facet>
                                                <h:outputText value="#{Mat.mat_det_nombre}"/>
                                            </h:column>
                                            <h:column >
                                                <f:facet name="header">
                                                    <h:outputText value="Ciclo." />
                                                </f:facet>
                                                <h:outputText value="#{Mat.mat_det_ciclo}"/>
                                            </h:column>
                                            <h:column >
                                                <f:facet name="header">
                                                    <h:outputText value="Creditos" />
                                                </f:facet>
                                                <h:outputText value="#{Mat.mat_det_creditos}"/>
                                            </h:column>
                                            <h:column >
                                                <f:facet name="header">
                                                    <h:outputText value="Tipo" />
                                                </f:facet>
                                                <h:outputText value="#{Mat.mat_det_tipo}"/>
                                            </h:column>
                                            <h:column >
                                                <f:facet name="header">
                                                    <h:outputText value="Seccion" />
                                                </f:facet>
                                                <h:selectOneMenu value="#{Mat.seccion}" id="seccion">
                                                    <f:selectItems value="#{Mat.comboSecciones}"/>
                                                </h:selectOneMenu>
                                            </h:column>
                                        </rich:dataTable>
                                                 </div>
                                    </td>
                                    <td>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="5">
                                        <hr size="1">
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </h:form>
                </rich:modalPanel>


                <rich:modalPanel  id="dupHorario" autosized="true" height="300" width="700" zindex="2000">
                    <f:facet name="header">
                        <h:outputText value="Cruce de Horario" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('dupHorario')" title="Cerrar"/>
                    </f:facet>
                    <h:form>
                        <rich:dataTable value="#{managerMatricula.listaDuplicadoHor}" var="dup" align="center">
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Nro" />
                                </f:facet>
                                <h:outputText value="#{dup.dup_id}"/>
                            </rich:column>

                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="curso" />
                                </f:facet>
                                <h:outputText value="#{dup.dup_nombreCurso}"/>
                            </rich:column>

                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="cruce horario" />
                                </f:facet>
                                <h:outputText value="#{dup.dup_nombreCursos}" escape="false" />
                            </rich:column>
                        </rich:dataTable>

                    </h:form>
                </rich:modalPanel>

                <rich:modalPanel  id="mpAlerta" autosized="true" zindex="2000">
                    <f:facet name="header">
                        <h:outputText value="Solicitu" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('dupHorario')" title="Cerrar"/>
                    </f:facet>
                    <h:form>
                        <table>
                            <tr>
                                <td><h:outputText value="" /></td>
                            </tr>
                        </table>
                    </h:form>
                </rich:modalPanel>
                <rich:modalPanel  id="mpEstadoAlu" autosized="true" zindex="100">
                    <f:facet name="header">
                        <h:outputText value="Estado Alumno" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpEstadoAlu')" title="Cerrar"/>
                    </f:facet>
                    <h:form id="fEstadoAlu">
                        <table>
                            <tr>
                                <td align="right" colspan="2">
                                    <a4j:commandButton image="/Imagenes/actions/no.png" oncomplete="#{mBAlumnoEstado.oncomplete}" 
                                                       action="#{mBAlumnoEstado.eliminarEstado}" title="Eliminar" alt="Eliminar"
                                                       reRender="tablaMaster,fRecargar" rendered="#{mBAlumnoEstado.flagDelete}" />
                                    <rich:spacer width="10"/>
                                    <a4j:commandButton image="/Imagenes/actions/filesave.png" oncomplete="#{mBAlumnoEstado.oncomplete}" 
                                                       actionListener="#{mBAlumnoEstado.grabarEstado}"
                                                        reRender="tablaMaster,fRecargar" />
                                </td>
                            </tr>
                            <tr>
                                <td align="center" colspan="2">
                                    <h:outputText value="#{mBAlumnoEstado.w_stado_alumno}" styleClass="cabeceraAr" />
                                </td>
                            </tr>
                            <tr>
                                <td><h:outputText value="Alumno"/> </td>
                                <td><h:outputText value="#{mBAlumnoEstado.objAcAlumnoEstado.acAlumno.aluAppaterno} #{mBAlumnoEstado.objAcAlumnoEstado.acAlumno.aluApmaterno} #{mBAlumnoEstado.objAcAlumnoEstado.acAlumno.aluNombres}" /></td>
                            </tr>
                            <tr>
                                <td><h:outputText value="Semestre"/> </td>
                                <td><h:outputText value="#{mBAlumnoEstado.objAcAlumnoEstado.acSemestre.semNombre}"/> </td>
                            </tr>
                            <tr>
                                <td><h:outputText value="Resolución"/> </td>
                                <td><h:inputText value="#{mBAlumnoEstado.objAcAlumnoEstado.aluestResolucion}"/> </td>
                            </tr>
                            <tr>
                                <td><h:outputText value="Reservas" rendered="#{mBAlumnoEstado.p_visualizar}"/> </td>
                                <td>
                                    <%/* <rich:inputNumberSpinner value="#{mBAlumnoEstado.objAcAlumnoEstado.aluestPeriodos}" maxValue="3" minValue="1"  rendered="#{mBAlumnoEstado.p_visualizar}" />*/%>
                                    <h:selectManyCheckbox value="#{mBAlumnoEstado.w_sem_ids}" rendered="#{mBAlumnoEstado.p_visualizar}">
                                        <f:selectItems value="#{mBAlumnoEstado.checkSemestres}"/> 
                                    </h:selectManyCheckbox>
                                </td>
                            </tr>
                            <tr>
                                <td valign="top"><h:outputText value="Observacion"/> </td>
                                <td><h:inputTextarea value="#{mBAlumnoEstado.objAcAlumnoEstado.aluestObservacion}" cols="30" rows="3"/> </td>
                            </tr>
                            <tr>
                                <td><h:outputText value="Archivo"/> </td>
                                <td>
                                    <h:outputText value="#{mBAlumnoEstado.archivoUp.name}" styleClass="fuente"/><rich:spacer width="10" />
                                    <h:commandButton image="/Imagenes/actions/descargar.png" value="D" actionListener="#{mBAlumnoEstado.descargarArchivo}" rendered="#{mBAlumnoEstado.archivoUp.length>5}" /><rich:spacer width="5" />
                                    <a4j:commandButton image="/Imagenes/actions/no.gif" value="E" action="#{mBAlumnoEstado.eleiminarArchivo}" reRender="fEstadoAlu" rendered="#{mBAlumnoEstado.archivoUp.length>5}" />
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <h:panelGroup  rendered="#{mBAlumnoEstado.archivoUp.length<=5}">
                                    <rich:fileUpload id="iFile"
                                                     maxFilesQuantity="2" 
                                                     immediateUpload="true"
                                                     addControlLabel="Agregar"
                                                     uploadControlLabel="Subir" 
                                                     clearControlLabel="Limpiar"
                                                     clearAllControlLabel="Limpiar Todos" 
                                                     fileUploadListener="#{mBAlumnoEstado.listenerResolucion}">
                                        <a4j:support event="onuploadcomplete" reRender="fEstadoAlu" />
                                    </rich:fileUpload>
                                    </h:panelGroup>
                                </td>
                            </tr>
                        </table>
                                    
                    </h:form>
                </rich:modalPanel>
                            
                            <rich:modalPanel  id="mpRecargar" height="100" width="300" zindex="100">
                                <f:facet name="header">
                                    <h:outputText value="Confirmar" />
                                </f:facet>
                                <f:facet name="controls">
                                   <% /* <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpEstadoAlu')" title="Cerrar"/>*/ %>
                                </f:facet>
                                <h:form id="fRecargar">
                                    <table align="center" height="30">
                                        <tr>
                                            <td align="center">
                                                <h:outputText value="#{mBAlumnoEstado.w_mensaje}"/><br />
                                                <h:commandButton action="#{managerMatricula.Seleccionar}" value="OK" />
                                            </td>
                                        </tr>
                                    </table>
                                </h:form>
                            </rich:modalPanel>

                  <%/*  <rich:modalPanel id="mpMostrar" width="700" height="490" zindex="1000">
                    <f:facet name="header">
                        <h:outputText id="iResolucion" value="Resolución" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpMostrar')" title="Cerrar"/>
                    </f:facet>
                    <h:form id="fMostrar">
                        <table>
                            <tr>
                                <td align="center" valign="middle">
                                    <rich:panel>
                                        <a4j:mediaOutput id="iReporte" uriAttribute="data" style="width : 650px; height: 420px;" element="object" standby="cargando..." createContent="#{mBAlumnoEstado.mostrarArchivo}" mimeType="application/pdf" value="12345"/>
                                    </rich:panel>
                                </td>
                            </tr>
                        </table>
                    </h:form>
                </rich:modalPanel>*/ %>

            </rich:panel>
        </f:view>
    </body>
</html>