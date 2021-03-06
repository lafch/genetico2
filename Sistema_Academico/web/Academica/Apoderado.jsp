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
        <title>Mantenimiento de Apoderados</title>
        <script type="text/javascript">
            function getRightTop(ref) {
                var position = new Object();
                position.top = 0; //ref.offsetTop;
                position.left =0; // ref.offsetLeft+ref.clientWidth+6;
                return position;
            }
        </script>
    </head>
    <f:view >
        <body>

            <jsp:include page="../TablaSistema/Menu.jsp"/>
            <rich:panel>
                <h:form id="form1" style="width:100%" >
                    <table width="100%" style="font-size:10px; font-family:verdana"  cellspacing="0" border="0" cellpadding="0">
                        <tr>
                            <td width="20%" colspan="2" ><strong>MANTENIMIENTO DE APODERADOS</strong></td>
                            <td width="30%" align="right"> </td>
                            <td width="30%" align="right">
                                <h:graphicImage value="/Imagenes/actions/filenew.png" width="16"
                                                style="border-width: 0px;cursor: pointer;">
                                    <a4j:support event="onclick" onsubmit="hideDiv({duration:0.1});"
                                                 actionListener="#{managerApoderado.nuevoApoderado}"
                                                 reRender="idni,ipaterno,imaterno,inombres,itelefono,icelular,idireccion, iDepartamento, iprovincia, idistrito,itipofamilia"
                                                 oncomplete="#{managerApoderado.oncomplete}"
                                                 />
                                </h:graphicImage>
                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right">
                                <h:graphicImage value="/Imagenes/actions/fileprint.png"
                                                style="cursor: pointer;"
                                                title="Imprimir Listado"/>
                            </td>
                            <td align=right width="50%">
                                <a4j:commandButton id="buscar" 
                                                   image="/Imagenes/actions/viewmag.png"
                                                   title="Buscar"
                                                   actionListener="#{managerApoderado.buscarTodos}"
                                                   reRender="dtApoderado" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="7"><hr width="100%" size="1"></td>
                        </tr>
                        <tr>
                            <td width="20%" colspan="6"></td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputText value="DNI"/></td>
                            <td width="30%">
                                <h:inputText value="#{managerApoderado.w_apo_dni}" styleClass="dr-spnr-i rich-spinner-input"  style="width : 180px;"/>
                            </td>
                            <td width="10%" align="right">Nombres:</td>
                            <td><h:inputText value="#{managerApoderado.w_apo_nombre}"  style="width : 180px;"/></td>
                        </tr>
                        <tr>
                            <td width="10%">Ap. Paterno:</td>
                            <td width="30%">
                                <h:inputText value="#{managerApoderado.w_apo_paterno}"  style="width : 180px;"/>
                            </td>
                            <td width="10%" align="right"></td>
                            <td>

                            </td>
                        </tr>
                        <tr>
                            <td width="10%">Ap. Materno:</td>
                            <td><h:inputText value="#{managerApoderado.w_apo_materno}" style="width : 180px;"/></td>
                            <td  align="right"></td>
                            <td>

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

                <h:form>
                    <table width="95%">
                        <tr>
                            <td>&nbsp; </td>
                            <td>&nbsp; </td>
                        </tr>
                        <tr>
                            <td colspan="2">

                                <rich:dataTable width="100%" value="#{managerApoderado.listaApoderado}" var="apo" id="dtApoderado">
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Id"/>
                                        </f:facet>
                                        <h:outputText value="#{apo.apoId}"/>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Apellidos y Nombres"/>
                                        </f:facet>
                                        <h:outputText value="#{apo.apoAppaterno} #{apo.apoApmaterno} #{apo.apoNombres}"/>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="DNI"/>
                                        </f:facet>
                                        <h:outputText value="#{apo.apoDni}"/>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Telefono"/>
                                        </f:facet>
                                        <h:outputText value="#{apo.apoTelefono}"/>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Celular"/>
                                        </f:facet>
                                        <h:outputText value="#{apo.apoCelular}"/>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Direccion"/>
                                        </f:facet>
                                        <h:outputText value="#{apo.apoDireccion}"/>
                                    </rich:column>

                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Tipo Fam."/>
                                        </f:facet>
                                        <h:outputText value="#{apo.tipoFamiliar}"/>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value=""/>
                                        </f:facet>
                                        <a4j:commandButton actionListener="#{managerApoderado.buscarAlumnosporApoderado}" image="/Imagenes/actions/mantenimiento.png"
                                                           oncomplete="#{managerApoderado.oncomplete}" reRender="dtAlumno,iDato,dtAlumnoT,dsAlumnoT" />
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value=""/>
                                        </f:facet>
                                        <a4j:commandButton actionListener="#{managerApoderado.seleccionarApoderado}" image="/Imagenes/actions/add_.gif"
                                                           reRender="idni,ipaterno,imaterno,inombres,itelefono,icelular,idireccion, iDepartamento, iprovincia, idistrito,itipofamilia"
                                                           oncomplete="#{managerApoderado.oncomplete}" />
                                        <f:param id="p_apo_id" value="#{apo.apoId}"/>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value=""/>
                                        </f:facet>
                                        <h:commandButton  image="/Imagenes/actions/no.png"  title="Eliminar"
                                                          actionListener="#{managerApoderado.eliminarSeleccion}"
                                                          onclick="javascript:return (confirm('�Esta realmente seguro de Eliminar?'));"/>
                                    </rich:column>
                                </rich:dataTable>
                            </td>
                        </tr>
                    </table>
                </h:form>

            </rich:panel>

            <rich:modalPanel  id="mpApoderado" minHeight="400" minWidth="370" height="400" width="370" zindex="2000"  onshow="showDiv();">
                <rich:effect  name="hideDiv"  for="contentDiv" type="Fade" />
                <rich:effect  name="showDiv"  for="contentDiv" type="Appear" />
                <div id="contentDiv">
                    <f:facet name="header">
                        <h:outputText value="Registro Apoderado" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpApoderado')" title="Cerrar"/>
                    </f:facet>
                    <h:form>
                        <table align="center" border="0" width="350px">
                            <tr>
                                <td></td>
                                <td colspan="2" align="right"><a4j:commandButton image="/Imagenes/actions/filesave.png"  title="Guardar"
                                                   actionListener="#{managerApoderado.guardarDatos}"
                                                   oncomplete="#{managerApoderado.oncomplete}" reRender="dtApoderado" /></td>

                            </tr>
                            <tr>
                                <td width="120px" align="left"><h:outputText value="DNI"/><h:outputText value="*" style="color:red" /> </td>
                                <td width="185px" align="left"><h:inputText style="width : 180px;"  value="#{managerApoderado.apoderado.apoDni}" id="idni"/> </td>
                                <td width="5px">&nbsp;</td>
                            </tr>
                            <tr>
                                <td><h:outputText value="Ap. Paterno"/> <h:outputText value="*" style="color:red" />  </td>
                                <td><h:inputText value="#{managerApoderado.apoderado.apoAppaterno}" style="width : 180px;"  id="ipaterno"/> </td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td><h:outputText value="Ap. Materno" /><h:outputText value="*" style="color:red" /> </td>
                                <td><h:inputText value="#{managerApoderado.apoderado.apoApmaterno}" style="width : 180px;" id="imaterno"/> </td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td><h:outputText value="Nombres"/><h:outputText value="*" style="color:red" /> </td>
                                <td><h:inputText value="#{managerApoderado.apoderado.apoNombres}" style="width : 180px;" id="inombres"/> </td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td><h:outputText value="Telefono"/><h:outputText value="*" style="color:red" /> </td>
                                <td><h:inputText value="#{managerApoderado.apoderado.apoTelefono}" style="width : 180px;" id="itelefono"/> </td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td><h:outputText value="Celular"/> </td>
                                <td><h:inputText value="#{managerApoderado.apoderado.apoCelular}" style="width : 180px;" id="icelular"/> </td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td><h:outputText value="Direccion"/> </td>
                                <td><h:inputText value="#{managerApoderado.apoderado.apoDireccion}" style="width : 180px;" id="idireccion"/></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td><h:outputText value="Tipo Fam."/><h:outputText value="*" style="color:red" /> </td>
                                <td>
                                    <h:selectOneMenu id="itipofamilia" value="#{managerApoderado.apoderado.tipoFamiliar}">
                                        <f:selectItems value="#{managerApoderado.cboTipoFamiliar}" />
                                    </h:selectOneMenu></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td><h:outputText value="Departamento"/> </td>
                                <td> <h:selectOneMenu id="iDepartamento" value="#{managerApoderado.w_departamento}" style="width : 180px;">
                                        <f:selectItems value="#{managerApoderado.cboDepartamentos_n}" />
                                        <a4j:support event="onchange" reRender="iprovincia,idistrito"/>
                                    </h:selectOneMenu> </td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td><h:outputText value="Provincia"/> </td>
                                <td> <h:selectOneMenu id="iprovincia" value="#{managerApoderado.w_provincia}" style="width : 180px;">
                                        <f:selectItems value="#{managerApoderado.cboProvincia_n}" />
                                        <a4j:support event="onchange" reRender="idistrito"/>
                                    </h:selectOneMenu> </td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td><h:outputText value="Distrito"/> </td>
                                <td> <h:selectOneMenu id="idistrito" value="#{managerApoderado.apoderado.apoDistrito}" style="width : 180px;">
                                        <f:selectItems value="#{managerApoderado.cboDistrito_n}" />
                                    </h:selectOneMenu> </td>
                                <td>&nbsp;</td>
                            </tr>

                        </table>
                    </h:form>
                </div>
            </rich:modalPanel>

            <rich:modalPanel  id="mpAlumno" minHeight="400" minWidth="400" height="450" width="450" zindex="2000"  onshow="showDiv();">
                <rich:effect  name="hideDiv"  for="contentDiv2" type="Fade" />
                <rich:effect  name="showDiv"  for="contentDiv2" type="Appear" />
                <div id="contentDiv2">
                    <f:facet name="header">
                        <h:outputText value="Registro Alumno" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpAlumno')" title="Cerrar"/>
                    </f:facet>
                    <h:form>
                        <table align="center" width="100%">
                            <tr>
                                <td align="left"><h:outputText value="Datos" /></td>
                                <td align="left"><h:inputText id="iDato" value="#{managerApoderado.datoAlumno}" style="width : 220px;"/> </td>
                                <td align="right"><a4j:commandButton image="/Imagenes/actions/viewmag.png" actionListener="#{managerApoderado.buscarAlumnoPorDato}" reRender="dtAlumnoT,dsAlumnoT"  /> </td>
                            </tr>
                            
                            <tr>
                                <td colspan="3" width="200px" valign="top">
                                    <rich:dataTable value="#{managerApoderado.listaAlumnoTem}" rows="5" var="aluT" id="dtAlumnoT" width="430">
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Id"/>
                                            </f:facet>
                                            <h:outputText value="#{aluT.aux_id}"/>
                                        </rich:column>

                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Codigo"/>
                                            </f:facet>
                                            <h:outputText value="#{aluT.aux_codigo}"/>
                                        </rich:column>

                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Apellidos y nombres"/>
                                            </f:facet>
                                            <h:outputText value="#{aluT.aux_paterno} #{aluT.aux_materno} #{aluT.aux_nombres}"/>
                                            <f:param id="p_aux_id" value="#{aluT.aux_id}" />
                                            <f:param id="p_aux_codigo" value="#{aluT.aux_codigo}" />
                                            <f:param id="p_aux_paterno" value="#{aluT.aux_paterno}" />
                                            <f:param id="p_aux_materno" value="#{aluT.aux_materno}" />
                                            <f:param id="p_aux_nombre" value="#{aluT.aux_nombres}" />
                                            <f:param id="p_indice" value="#{aluT.indice}" />
                                        </rich:column>
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="P"/>
                                            </f:facet>
                                            <a4j:commandButton image="/Imagenes/actions/edit_add.png"
                                                               actionListener="#{managerApoderado.pasarDatos}"
                                                               reRender="dtAlumno,dtAlumnoT,iDato,dsAlumnoT " />
                                        </rich:column>
                                    </rich:dataTable>
                                </td>

                            </tr>
                            <tr>

                                <td colspan="3" align="right">
                                    <rich:datascroller id="dsAlumnoT" align="right"  for="dtAlumnoT" maxPages="8"  style=" width : 100%;"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" align="right">
                                    <rich:separator height="2" width="100%" />
                                    <br />
                                    <a4j:commandButton image="/Imagenes/actions/filesave.png" oncomplete="#{managerApoderado.oncomplete}"
                                                       actionListener="#{managerApoderado.guardarAlumnosApo}" />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3">
                                    <rich:dataTable value="#{managerApoderado.listaAlumno}" var="alu" id="dtAlumno" width="430" >
                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Id"/>
                                            </f:facet>
                                            <h:outputText value="#{alu.aux_id}"/>
                                        </rich:column>

                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Codigo"/>
                                            </f:facet>
                                            <h:outputText value="#{alu.aux_codigo}"/>
                                        </rich:column>

                                        <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value="Apellidos y nombres"/>
                                            </f:facet>
                                            <h:outputText value="#{alu.aux_paterno} #{alu.aux_materno} #{alu.aux_nombres}"/>
                                        </rich:column>
                                         <rich:column>
                                            <f:facet name="header">
                                                <h:outputText value=""/>
                                            </f:facet>
                                             <f:param id="p_aux_idbak" value="#{alu.aux_id}" />
                                             <a4j:commandButton image="/Imagenes/actions/cross.gif" 
                                                                actionListener="#{managerApoderado.quitarDatos}"
                                                                oncomplete="#{managerApoderado.oncomplete}" reRender="dtAlumno" />
                                        </rich:column>
                                    </rich:dataTable>

                                </td>
                            </tr>
                        </table>

                    </h:form>
                </div>
            </rich:modalPanel>

        </body>
    </f:view>
</html>  

