<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Mantenimiento de Cursos</title>
        <script type="text/javascript">
            function getRightTop(ref) {
                var position = new Object();
                position.top = 0; //ref.offsetTop;
                position.left =0; // ref.offsetLeft+ref.clientWidth+6;
                return position;
            }
            function validar(e) {
                    tecla = (document.all) ? e.keyCode : e.which;
                    if (tecla == 8 || tecla == 0 || tecla == 9)
                        return true;
                    patron = /\d/;
                    te = String.fromCharCode(tecla);
                    //alert(tecla);
                    return patron.test(te);
                }
                function validarLetras(e) {
                    tecla = (document.all) ? e.keyCode : e.which;
                    if (tecla == 8 || tecla == 9)
                        return true;
                    patron = /[A-Za-z\s]/;
                    te = String.fromCharCode(tecla);
                    return patron.test(te);
                }
        </script> 
    </head>
    <body>
        <f:view >
            <jsp:include page="../TablaSistema/Menu.jsp"></jsp:include>
            <f:facet name="FACULTADES">
            </f:facet>
            <h:form id="form1" style="width:100%">
                <table width="100%" style="font-size:10px; font-family:verdana"  cellspacing="0" border="0" cellpadding="0">
                    <tr >
                        <td width="20%" colspan="2" ><strong>LISTADO CURSOS</strong>
                        </td>
                        <td width="30%"></td>
                        <td width="30%" align="right">

                            <a4j:region id="Region">

                                <h:graphicImage value="/Imagenes/new-32.png" style="border-width: 0px;">
                                    <a4j:support onsubmit="hideDiv({duration:0.1});" oncomplete="javascript:Richfaces.showModalPanel('mp',{width:450, top:130})" event="onclick" action="#{managerCurso.Nuevo}" reRender="iId,iCodigo,iFacultad,iEspecialidad,iNombre" />
                                </h:graphicImage>

                            </a4j:region>



                        </td>
                        <td width="30%"></td>
                        <td width="30%" align="right"><h:graphicImage value="/Imagenes/printer-32.png" title="Imprimir Listado"/>
                        </td>

                        <td align=right width="50%">
                            <h:commandButton type="button" id="buscar" value="" action="#{managerCurso.Seleccionar}" image="/Imagenes/search-32.png"  title="Buscar"/>


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
                        <td width="90%">
                            <a4j:region id="Pro2">
                                <h:selectOneMenu id="Facultad" value="#{managerCurso.b_facultad}" style="width:180px">
                                    <f:selectItems value="#{managerCurso.comboFacultades_s}" />
                                    <a4j:support event="onchange"  reRender="Especialidad"/>
                                </h:selectOneMenu>

                            </a4j:region>
                        </td>

                    </tr>

                    <tr>
                        <td width="10%">Especialidad:</td>
                        <td width="90%">
                            <h:selectOneMenu  id="Especialidad"  value="#{managerCurso.b_especialidad}"  style="width:180px">
                                <f:selectItems value="#{managerCurso.comboEspecialidades_s}" />
                            </h:selectOneMenu>
                        </td>
                        <td width="20%"></td>
                    </tr>
                    <tr style=" height : 24px;">
                        <td width="10%" >Codigo:
                        </td>
                        <td width="30%">
                            <h:inputText id="txtCodigo" value="#{managerCurso.b_codigo}" style="width : 180px;" maxlength="20"/></td>
                        <td width="50%">

                        </td>
                    </tr>
                    <tr>
                        <td width="10%">Nombre:
                        </td>
                        <td width="30%">
                            <h:inputText id="txtNombre" value="#{managerCurso.b_nombre}" style="width : 180px;" maxlength="50"/></td>
                        <td width="50%"></td>
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
                <table style=" width : 100%;"  cellspacing="0" border="0">
                    <tr>
                        <td colspan="5" style=" width : 100%">
                            <p align="right"/>
                            <rich:datascroller align="right" for="cursos" maxPages="8" id="barra"/>
                        </td>
                    </tr>
                </table>

                <rich:dataTable id="cursos" var="cursos" rows="10"
                                onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                cellpadding="0" cellspacing="0"
                                width="100%" border="0" value="#{managerCurso.listaCursos}"  >
                    <rich:column>
                        <f:facet name="header">
                            <h:outputText value="Id" />
                        </f:facet>
                        <h:outputText value="#{cursos.b_id}"/>

                    </rich:column>
                    <rich:column>
                        <f:facet name="header">
                            <h:outputText value="Codigo" />
                        </f:facet>
                        <h:outputText value="#{cursos.b_codigo}" />

                    </rich:column>

                    <rich:column >
                        <f:facet name="header">
                            <h:outputText value="Nombre" />
                        </f:facet>

                        <h:outputText value="#{cursos.b_nombre}" />

                    </rich:column>



                    <rich:column >
                        <f:facet name="header">
                            <h:outputText value="Facultad" />
                        </f:facet>

                        <h:outputText value="#{cursos.b_facultad_descripcion}" />

                    </rich:column>





                    <rich:column >
                        <f:facet name="header">
                            <h:outputText value="Especialidad" />
                        </f:facet>

                        <h:outputText value="#{cursos.b_especialidad_descripcion}" />

                    </rich:column>
                    
                    <rich:column >
                        <f:facet name="header">
                            <h:outputText value="Horas Laboratorio" />
                        </f:facet>

                        <h:outputText value="#{cursos.b_curhorlab}" />

                    </rich:column>
                    
                    <rich:column >
                        <f:facet name="header">
                            <h:outputText value="Horas Teoria" />
                        </f:facet>

                        <h:outputText value="#{cursos.b_curhorteo}" />

                    </rich:column>



                    <rich:column>
                        <f:facet name="header">
                            <h:outputText value="Edit" />
                        </f:facet>
                        <p align="right">


                            <a4j:region id="ProvProc">

                                <h:graphicImage value="/Imagenes/actions/editpaste.png" style="border-width: 0px;">
                                    <a4j:support oncomplete="javascript:Richfaces.showModalPanel('mp',{width:450, top:130})" onsubmit="hideDiv({duration:0.1});" event="onclick" actionListener="#{managerCurso.EditarFila}"  reRender="iId,iCodigo,iFacultad,iEspecialidad,iNombre,iHorLab,iHorTeo"/>

                                </h:graphicImage>

                            </a4j:region>
                            <f:param id="p_id" value="#{cursos.b_id}" />
                            <f:param id="p_codigo" value="#{cursos.b_codigo}" />
                            <f:param id="p_nombre"   value="#{cursos.b_nombre}" />
                            <f:param id="p_facultad"   value="#{cursos.b_facultad}" />
                            <f:param id="p_especialidad"   value="#{cursos.b_especialidad}" />
                            <f:param id="p_horlab"   value="#{cursos.b_curhorlab}" />
                            <f:param id="p_horteo"   value="#{cursos.b_curhorteo}" />
                            

                        </p>
                    </rich:column>
                    <rich:column >
                        <f:facet name="header" >
                            <h:outputText value="Delete" />
                        </f:facet>
                        <p align="right">
                            <h:commandButton  image="/Imagenes/actions/no.png"  title="Eliminar"  actionListener="#{managerCurso.EliminarFila}" action="#{managerCurso.Seleccionar}"  onclick="javascript:return (confirm('¿Esta realmente seguro de Eliminar?'));"/>
                        </p>
                        <f:param id="delete"  value="#{cursos.b_id}" />
                    </rich:column>

                </rich:dataTable>

            </h:form>


            <rich:modalPanel  id="mp" minHeight="370" minWidth="450" height="370" width="500" zindex="2000"  onshow="showDiv();">
                <rich:effect  name="hideDiv"  for="contentDiv" type="BlindUp" />
                <rich:effect  name="showDiv"  for="contentDiv" type="BlindDown" />
                <div id="contentDiv">
                    <f:facet name="header">

                        <h:outputText value="Registro de Cursos" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp')" title="Cerrar"/>
                    </f:facet>

                    <h:form>
                        <table width="100%" style="font-size:10px; font-family:verdana"  cellspacing="0" border="0">
                            <tr >
                                <td align="right" colspan="4">
                                    <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"  actionListener="#{managerCurso.Enviar}" action="#{managerCurso.Seleccionar}" reRender="cursos" oncomplete="#{managerCurso.oncomplete}"/>

                                </td>
                            </tr>
                            <tr>
                                <td colspan="3"><hr size="1">
                                </td>
                            </tr>
                            <tr>
                                <td width="30%" >*Codigo:</td>
                                <td width="70%">
                                    <h:inputHidden id="iId" value="#{managerCurso.b_id_u}"/>
                                    <h:inputText id="iCodigo"  style=" width : 180px;" value="#{managerCurso.b_codigo_u}"  maxlength="20"/>
                                </td>
                                <td align="right" width="20%">
                                </td>
                            </tr>
                            <tr>
                                <td width="30%">*Nombre:</td>
                                <td width="70%">
                                    <h:inputText id="iNombre" value="#{managerCurso.b_nombre_u}" style="width : 180px;" maxlength="50"/>
                                </td>
                                <td width="20%"></td>
                            </tr>

                            <tr>
                                <td width="30%">*Facultad:</td>
                                <td width="70%">
                                    <a4j:region id="Pro">
                                        <h:selectOneMenu id="iFacultad" value="#{managerCurso.b_facultad_u}"  style="width:180px">
                                            <f:selectItems value="#{managerCurso.comboFacultades}" />
                                            <a4j:support event="onchange" reRender="iEspecialidad"/>
                                        </h:selectOneMenu>
                                    </a4j:region>
                                </td>
                                <td width="20%"></td>
                            </tr>

                            <tr>
                                <td width="30%">*Especialidad:</td>
                                <td width="70%">
                                    <h:selectOneMenu  id="iEspecialidad"  value="#{managerCurso.b_especialidad_u}"  style="width:180px">
                                        <f:selectItems value="#{managerCurso.comboEspecialidades}" />
                                    </h:selectOneMenu>
                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td width="30%">Hora de Lab:</td>
                                <td width="70%">
                                    <h:inputText id="iHorLab" value="#{managerCurso.b_curhorlab}" style="width : 180px;" maxlength="2" onkeypress="return validar(event);"/>
                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td width="30%">*Hora de Teo:</td>
                                <td width="70%">
                                    <h:inputText id="iHorTeo" value="#{managerCurso.b_curhorteo}" style="width : 180px;" maxlength="2" onkeypress="return validar(event);"/>
                                </td>
                                <td width="20%"></td>
                            </tr>
                           


                            <tr>
                                <td colspan="4"><hr size="1">
                                </td>
                            </tr>
                        </table>
                    </h:form>
                </div>
            </rich:modalPanel>


        </f:view>
    </body>
</html>  
