<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Mantenimiento de Turnos</title>
        <script type="text/javascript">
            function getRightTop(ref) {
                var position = new Object();
                position.top = 0; //ref.offsetTop;
                position.left = 0; // ref.offsetLeft+ref.clientWidth+6;
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
            <rich:panel style="width : 100%; background:#FFF; border:1px !important;" >
                <f:facet name="FACULTADES">
                </f:facet>
                <h:form id="form1" style="width:100%">
                    <table width="100%" style="font-size:10px; font-family:verdana"  cellspacing="0" border="0" cellpadding="0">
                        <tr >
                            <td width="20%" colspan="2" ><strong>LISTADO TURNOS</strong>
                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right">
                                <a4j:region id="Region">
                                    <h:graphicImage value="/Imagenes/new-32.png" style="border-width: 0px;">
                                        <a4j:support oncomplete="javascript:Richfaces.showModalPanel('mp',{width:450, top:130})" event="onclick" action="#{managerTurno.Nuevo}" reRender="iId,iCodigo,iNombre,iPeriodos,iInicio_h,iInicio_m,iInicio_meridiano,iVigente,iSemestre,iTiempo" />
                                    </h:graphicImage>
                                </a4j:region>
                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right"><h:graphicImage value="/Imagenes/printer-32.png" title="Imprimir Listado"/>
                            </td>
                            <td align=right width="50%">
                                <h:commandButton type="button" id="buscar" value="" action="#{managerTurno.Seleccionar}" image="/Imagenes/search-32.png"  title="Buscar"/>
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
                            <td width="10%" >Codigo:
                            </td>
                            <td width="30%">
                                <h:inputText id="txtCodigo" value="#{managerTurno.codigo_f}" style="width : 180px;" maxlength="10"/></td>
                            <td width="50%">
                            </td>
                        </tr>
                        <tr>
                            <td width="10%">Nombre:
                            </td>
                            <td width="30%">
                                <h:inputText id="txtNombre" value="#{managerTurno.nombre_f}" style="width : 180px;" maxlength="50"/></td>
                            <td width="50%"></td>
                        </tr>
                        <tr>
                            <td width="10%">Semestre:
                            </td>
                            <td width="30%">
                                <h:selectOneMenu value="#{managerTurno.semestre_f}" style="width : 180px;">
                                    <f:selectItems value="#{managerTurno.combo_semestres_f}"/>
                                </h:selectOneMenu>
                            </td>
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
                <h:form style=" width : 100%;" id="form4">
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
                                    width="100%" border="0" value="#{managerTurno.tablaTurno}" var="turno">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id" />
                            </f:facet>
                            <h:outputText value="#{turno.id_s}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Codigo" />
                            </f:facet>
                            <h:outputText value="#{turno.codigo_s}" />
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Nombre" />
                            </f:facet>
                            <h:outputText value="#{turno.nombre_s}" />
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Periodo" />
                            </f:facet>
                            <h:outputText value="#{turno.periodo_s}" />
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Inicio" />
                            </f:facet>
                            <h:outputText value="#{turno.inicio_s}" />
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Vigente" />
                            </f:facet>
                            <h:outputText value="#{turno.vigente_s}" />
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Semestre" />
                            </f:facet>
                            <h:outputText value="#{turno.semestre_s}" />
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Tiempo" />
                            </f:facet>
                            <h:outputText value="#{turno.tiempo_s}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Edit" />
                            </f:facet>
                            <p align="right">
                                <a4j:region id="ProvProc">
                                    <h:graphicImage value="/Imagenes/actions/editpaste.png" style="border-width: 0px;">
                                        <a4j:support oncomplete="javascript:Richfaces.showModalPanel('mp',{width:450, top:130})" event="onclick" actionListener="#{turno.Editar}" reRender="iId,iCodigo,iNombre,iPeriodos,iInicio_h,iInicio_m,iInicio_meridiano,iVigente,iSemestre,iTiempo"/>
                                    </h:graphicImage>
                                </a4j:region>
                            </p>
                            <f:param id="id_s" value="#{turno.id_s}" />
                            <f:param id="codigo_s" value="#{turno.codigo_s}" />
                            <f:param id="nombre_s"   value="#{turno.nombre_s}" />
                            <f:param id="periodos_s"   value="#{turno.periodo_s}" />
                            <f:param id="inicio_s"   value="#{turno.inicio_s}" />
                            <f:param id="vigente_s"   value="#{turno.vigente_s}" />
                            <f:param id="semestre_s"   value="#{turno.semestre_s}" />
                            <f:param id="tiempo_s"   value="#{turno.tiempo_s}" />
                        </h:column>
                        <h:column >
                            <f:facet name="header" >
                                <h:outputText value="Ver" />
                            </f:facet>
                            <p align="right">
                                <h:graphicImage value="/Imagenes/actions/color_line.png" style="border-width: 0px;">
                                    <a4j:support oncomplete="javascript:Richfaces.showModalPanel('mp1',{width:550, top:130})" event="onclick" actionListener="#{turno.Editar}" reRender="iCodigo1,iNombre1,iPeriodos1,iInicio_h1,iInicio_m1,iInicio_meridiano1,iVigente1,iSemestre1,iTiempo1,tablaSecundaria,scroller2"/>
                                </h:graphicImage>
                            </p>
                        </h:column>
                        <h:column >
                            <f:facet name="header" >
                                <h:outputText value="Delete" />
                            </f:facet>
                            <p align="right">
                                <h:commandButton  image="/Imagenes/actions/no.png"  title="Eliminar"  actionListener="#{managerTurno.Eliminar}" action="#{managerTurno.Seleccionar}"  onclick="javascript:return (confirm('¿Esta realmente seguro de Eliminar?'));"/>
                            </p>
                        </h:column>
                    </rich:dataTable>
                </h:form>


                <rich:modalPanel  id="mp" minHeight="370" minWidth="450" height="370" width="500" zindex="2000">

                    <f:facet name="header">

                        <h:outputText value="Registro de Cursos" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp')" title="Cerrar"/>
                    </f:facet>

                    <h:form>
                        <table width="100%" style="font-size:10px; font-family:verdana">
                            <tr >
                                <td align="right" colspan="4">
                                    <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"  actionListener="#{managerTurno.Insertar}" action="#{managerTurno.Seleccionar}" reRender="tablaMaster"  oncomplete="#{managerTurno.oncomplete}"/>

                                </td>
                            </tr>
                            <tr>
                                <td colspan="3"><hr size="1">
                                </td>
                            </tr>
                            <tr>
                                <td width="30%" >Codigo:</td>
                                <td width="70%">
                                    <h:inputHidden id="iId" value="#{managerTurno.id_i}"/>
                                    <h:inputText label="Codigo" id="iCodigo"  style=" width : 180px;" value="#{managerTurno.codigo_i}" required="true"  maxlength="10"/>
                                    <rich:message for="iCodigo">
                                        <f:facet name="passedMarker">
                                            <h:graphicImage  value="/Imagenes/actions/ok.png" />
                                        </f:facet>
                                        <f:facet name="errorMarker">
                                            <h:graphicImage value="/Imagenes/actions/no.png" />
                                        </f:facet>
                                    </rich:message>


                                </td>
                                <td align="right" width="20%">
                                </td>
                            </tr>
                            <tr>
                                <td width="30%">Nombre:</td>
                                <td width="70%">
                                    <h:inputText id="iNombre" label="Nombre" value="#{managerTurno.nombre_i}" style="width : 180px;"  required="true"/>
                                    <rich:message for="iNombre">
                                        <f:facet name="passedMarker">
                                            <h:graphicImage  value="/Imagenes/actions/ok.png" />
                                        </f:facet>
                                        <f:facet name="errorMarker">
                                            <h:graphicImage value="/Imagenes/actions/no.png" />
                                        </f:facet>
                                    </rich:message>
                                </td>
                                <td width="20%"></td>
                            </tr>

                            <tr>
                                <td width="30%">Nro de Periodos:</td>
                                <td width="70%">
                                    <rich:inputNumberSpinner id="iPeriodos" value="#{managerTurno.periodo_i}" style="width : 20px;">
                                    </rich:inputNumberSpinner>
                                </td>
                                <td width="20%"></td>
                            </tr>

                            <tr>
                                <td width="30%">Inicio:</td>
                                <td width="70%">
                                    <h:inputText id="iInicio_h" value="#{managerTurno.inicio_h_i}" style="width : 40px;" onkeypress="return validar(event);"/>
                                    <h:outputLabel value=":"></h:outputLabel>
                                    <h:inputText id="iInicio_m" value="#{managerTurno.inicio_m_i}" style="width : 40px;" onkeypress="return validar(event);"/>
                                    <h:selectOneMenu id="iInicio_meridiano" value="#{managerTurno.inicio_meridiano_i}" style="width : 40px;">
                                        <f:selectItems value="#{managerTurno.combo_meridianos}"/>
                                    </h:selectOneMenu>
                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td width="30%">Vigente:</td>
                                <td width="70%">
                                    <h:selectBooleanCheckbox id="iVigente" value="#{managerTurno.vigente_i}" />
                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td width="30%">Semestre:</td>
                                <td width="70%">
                                    <h:selectOneMenu id="iSemestre" value="#{managerTurno.semestre_i}" style="width : 180px;">
                                        <f:selectItems value="#{managerTurno.combo_semestres}"/>
                                    </h:selectOneMenu>
                                </td>
                            </tr>
                            <tr>
                                <td width="30%">Tiempo:</td>
                                <td width="70%">
                                    <h:inputText id="iTiempo" label="Tiempo" value="#{managerTurno.tiempo_i}" style="width : 100px;"  required="true" onkeypress="return validar(event);"/>
                                    <h:outputLabel value=" min" for=""></h:outputLabel>
                                    </td>
                                </tr>


                                <tr>
                                    <td colspan="4"><hr size="1">
                                    </td>
                                </tr>
                            </table>
                    </h:form>
                </rich:modalPanel>




                <rich:modalPanel  id="mp1" minHeight="550" minWidth="550" height="550" width="550" zindex="2000">

                    <f:facet name="header">

                        <h:outputText value="Detalle de Turno" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp1')" title="Cerrar"/>
                    </f:facet>

                    <h:form id="form2">
                        <table width="100%" style="font-size:10px; font-family:verdana">
                            <tr >
                                <td align="right" colspan="4">
                                    <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"  action="#{managerTurno.Seleccionar}" reRender="tablaMaster" oncomplete="Richfaces.hideModalPanel('mp')"/>

                                </td>
                            </tr>
                            <tr>
                                <td colspan="3"><hr size="1">
                                </td>
                            </tr>
                            <tr>
                                <td width="30%" >Codigo:</td>
                                <td width="70%">
                                    <%/*<h:outputText id="iId1" value="#{managerTurno.id_i}"/>		*/%>
                                    <h:outputText id="iCodigo1"  style=" width : 180px;" value="#{managerTurno.codigo_i}" />
                                </td>
                                <td align="right" width="20%">
                                </td>
                            </tr>
                            <tr>
                                <td width="30%">Nombre:</td>
                                <td width="70%">
                                    <h:outputText id="iNombre1" value="#{managerTurno.nombre_i}" style="width : 180px;"/>
                                </td>
                                <td width="20%"></td>
                            </tr>

                            <tr>
                                <td width="30%">Nro de Periodos:</td>
                                <td width="70%">
                                    <h:outputText id="iPeriodos1" value="#{managerTurno.periodo_i}" style="width : 20px;" />

                                </td>
                                <td width="20%"></td>
                            </tr>

                            <tr>
                                <td width="30%">Inicio:</td>
                                <td width="70%">
                                    <h:outputText id="iInicio_h1" value="#{managerTurno.inicio_h_i}" style="width : 40px;"/>
                                    <h:outputLabel value=":"></h:outputLabel>
                                    <h:outputText id="iInicio_m1" value="#{managerTurno.inicio_m_i}" style="width : 40px;"/>
                                    <h:outputText id="iInicio_meridiano1" value="#{managerTurno.inicio_meridiano_i}" style="width : 40px;"/>
                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td width="30%">Vigente:</td>
                                <td width="70%">
                                    <h:outputText id="iVigente1" value="#{managerTurno.vigente_i}" />
                                </td>
                                <td width="20%"></td>
                            </tr>
                            <tr>
                                <td width="30%">Semestre:</td>
                                <td width="70%">
                                    <h:outputText id="iSemestre1" value="#{managerTurno.semestre_i}" style="width : 180px;"/>
                                </td>
                            </tr>
                            <tr>
                                <td width="30%">Tiempo:</td>
                                <td width="70%">
                                    <h:outputText id="iTiempo1" value="#{managerTurno.tiempo_i}" style="width : 100px;"/>
                                    <h:outputLabel value=" min"></h:outputLabel>
                                    </td>
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
                                    <td colspan="5">
                                        <p align="right"/>
                                    <rich:datascroller id="scroller2" align="right"  for="tablaSecundaria" maxPages="8"  style=" width : 100%;"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">
                                    <rich:dataTable id="tablaSecundaria" rows="10" onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                    cellpadding="0" cellspacing="0"
                                                    width="100%" border="0" value="#{managerTurno.tablaTurnoDetalle}" var="turno_detalle">
                                        <h:column>
                                            <f:facet name="header">
                                                <h:outputText value="Id" />
                                            </f:facet>
                                            <h:outputText value="#{turno_detalle.id_det}"/>
                                        </h:column>
                                        <h:column>
                                            <f:facet name="header">
                                                <h:outputText value="Hora Inicio" />
                                            </f:facet>
                                            <h:outputText value="#{turno_detalle.inicio_det}"/>
                                        </h:column>
                                        <h:column>
                                            <f:facet name="header">
                                                <h:outputText value="Hora Fin" />
                                            </f:facet>
                                            <h:outputText value="#{turno_detalle.fin_det}"/>
                                        </h:column>
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
