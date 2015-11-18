<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:Sistema Academico :.</title>
        <style type="text/css">
            .textoNegrita{
                font-size: 14px;
                font-family: sans-serif;
                color: #004080;
                font-weight: bold;

            }
            .texto{
                font-size: 14px;
                font-family: sans-serif;
                color: #004080;
            }
            .textoCabecera{
                font-size: 18px;
                font-family: sans-serif;
                font-weight: bold;
                color: #004080;

            }
            .alto{
                height: 25px;
            }
            .selector{
                font-size: 12px;
                font-family: sans-serif;
                border-radius: 5px 5px 5px 5px;
            }

            .textoChiko{
                font-size: 11px;
                font-family: sans-serif;
                color: #004080;
            }
            .fuentePagina{
                color: #004080;
            }

            select::-ms-expand {
                display: none; /* hide default arrow in IE10 */
            }
            select {
                color: #004080;
                width: 32%; /* Set width. Do not set height else it will fail in IE8-10. Use padding for height. */
                color: #333;
                font-weight: normal;
                font-size: 1em;
                line-height:1.2em;
                margin: 0 0 10px;
                padding: 4px 0; /* use this to set a specific height for your dropdown (DO NOT use the attribute 'height') */
                border: 0 none;
                cursor: pointer;
                text-indent: 0.01px;
                text-overflow: "";
                background: url('../Imagenes/down.png') no-repeat 100% 4px #fff; /* add your own arrow image */
                background-image:none; /* turn bg image for IE7 */
                background: url("../Imagenes/down.png") no-repeat 100% 0px #fff; /* fallback bg image*/
                background: url("../Imagenes/down.png") no-repeat 100% 0px, -webkit-linear-gradient(top, #DFDFDF, #DFDFDF);
                background: url("../Imagenes/down.png") no-repeat 100% 0px, -moz-linear-gradient(top, #DFDFDF, #DFDFDF);
                background: url('../Imagenes/down.png') no-repeat 100% 0px, -ms-linear-gradient(top, #DFDFDF, #DFDFDF);
                background: url('../Imagenes/down.png') no-repeat 100% 0px, -o-linear-gradient(top, #DFDFDF, #DFDFDF);
                background: url('../Imagenes/down.png') no-repeat 100% 0px, linear-gradient(top, #DFDFDF, #DFDFDF);

            }
            select option {
                background: #fff; /* style the dropdown bg color */
            }

            .MarcoLeyenda{
                border: 1px solid;
                border-color:#004080;
                padding: 3px;
                width: 85%;
                border-radius: 5px 5px 5px 5px;
            }

            .everyThirdDay{
                background-color:gray;

            }
            .weekendBold{
                font-weight:bold;
                font-style: italic;
            }
        </style>

        <script type="text/javascript">
            function disablementFunction(day) {
                var choosenDate = $("fConsulta:secIni").component.getSelectedDate();
                var diff = day.date.getTime() - choosenDate.getTime();

                if (diff > 0) {
                    return true;
                }
                else {
                    return false;
                }
            }
            function disabledClassesProv(day) {
                var choosenDate = $("fConsulta:secIni").component.getSelectedDate();
                var diff = day.date.getTime() - choosenDate.getTime();
                if (diff > 0)
                    return "";
                else
                    return 'rich-calendar-boundary-dates';

            }

            function disablementFunction2(day) {
                var choosenDate = $("fConsulta:secFin").component.getSelectedDate();
                   // alert(choosenDate);
                    var diff = choosenDate.getTime() - day.date.getTime();

                    if (diff >= 0) {
                        return true;
                    }
                    else {
                        return false;
                    }
                
            }
            function disabledClassesProv2(day) {
                var choosenDate = $("fConsulta:secFin").component.getSelectedDate();

                    var diff = choosenDate.getTime() - day.date.getTime();
                    if (diff >=0){
                        
                    }else{
                        return 'rich-calendar-boundary-dates';
                    }
                
            }
        </script>
    </head>
    <f:view>
        <body>
            <jsp:include page="../TablaSistema/Menu.jsp"/>
            <rich:panel>
                <h:form id="fConsulta">
                    <div style="width: 100%" align="center" class="fondo">
                        <table width="100%">
                            <tr>
                                <td width="15%"></td>
                                <td align="center" width="70%">
                                    <h:outputText value="Consulta Academica - Curso" styleClass="textoCabecera"/>
                                </td>
                                <td align="right" width="15%">
                                </td>
                            </tr>
                        </table>

                        <rich:spacer height="10"/>
                        <rich:separator height="2" width="100%" />

                        <table width="100%" >
                            <tr>
                                <td width="40%" valign="top">
                                    <rich:panel style="width:100%; background-color: #FFFFFF;" id="pDatos">
                                        <table width="100%" border="0" align="center" style="font-size: 14px;">
                                            <tr class="alto">
                                                <td><h:outputText value="Sede" styleClass="textoNegrita" /></td>
                                                <td colspan="2">
                                                    <h:selectOneMenu id="iSede" style="width:250px" value="#{managerConsulCurso.w_sed_id}" styleClass="selector" >
                                                        <f:selectItems value="#{managerConsulCurso.cboSede}" />
                                                        <a4j:support event="onchange" actionListener="#{managerConsulCurso.seleccionarSeccion}" reRender="iAno,iArea,iModulo,iCurso,iSeccion,iInicio,iFin,dtAlumno,pDetalle,iHorario"/>
                                                    </h:selectOneMenu>
                                                </td>
                                            </tr>
                                            <tr class="alto">
                                                <td><h:outputText value="Año" styleClass="textoNegrita" /></td>
                                                <td colspan="2">
                                                    <h:selectOneMenu id="iAno" style="width:250px" value="#{managerConsulCurso.w_ano_id}" styleClass="selector">
                                                        <f:selectItems value="#{managerConsulCurso.cboAno}" />
                                                        <a4j:support event="onchange" actionListener="#{managerConsulCurso.seleccionarSeccion}" reRender="iArea,iModulo,iCurso,iSeccion,iInicio,iFin,dtAlumno,pDetalle,iHorario"/>
                                                    </h:selectOneMenu>
                                                </td>
                                            </tr>
                                            <tr class="alto">
                                                <td width="20%">
                                                    <h:outputText value="Institución"  styleClass="textoNegrita" />
                                                </td>
                                                <td colspan="2" align="left">
                                                    <h:selectOneMenu value="#{managerConsulCurso.instId}" styleClass="selector" style="width:250px" >
                                                        <f:selectItems value="#{managerConsulCurso.cboInstituciones}" />
                                                        <a4j:support event="onchange" reRender="iArea,iModulo,iCurso,iTaller,iSeccion,iInicio,iFin,dtAlumno,pDetalle,iHorario"/>
                                                    </h:selectOneMenu>
                                                </td>
                                            </tr>
                                            <tr class="alto">
                                                <td><h:outputText value="Área" styleClass="textoNegrita" /></td>
                                                <td colspan="2">
                                                    <h:selectOneMenu id="iArea" style="width:450px" value="#{managerConsulCurso.w_area_id}" styleClass="selector">
                                                        <f:selectItems value="#{managerConsulCurso.cboArea}" />
                                                        <a4j:support event="onchange"  reRender="pglblAsignatura,pgseAsignatura,iModulo,iCurso,iSeccion,iInicio,iFin,dtAlumno,pDetalle,iHorario"/>
                                                    </h:selectOneMenu>
                                                </td>
                                            </tr>
                                            <tr class="alto">
                                                <td><h:outputText value="Modulo" styleClass="textoNegrita" /></td>
                                                <td colspan="2">
                                                    <h:selectOneMenu id="iModulo" style="width:450px" value="#{managerConsulCurso.w_mod_id}"  styleClass="selector">
                                                        <f:selectItems value="#{managerConsulCurso.cboModulo}" />
                                                        <a4j:support event="onchange"  reRender="iCurso,iSeccion,iInicio,iFin,dtAlumno,pDetalle,iHorario"/>
                                                    </h:selectOneMenu>
                                                </td>
                                            </tr>
                                            <tr class="alto">
                                                <td><h:outputText value="Curso" styleClass="textoNegrita" /></td>
                                                <td colspan="2">
                                                    <h:selectOneMenu id="iCurso" style="width:450px" value="#{managerConsulCurso.w_cur_id}" styleClass="selector">
                                                        <f:selectItems value="#{managerConsulCurso.cboCurso}" />
                                                        <a4j:support event="onchange"  reRender="iTaller,iSeccion,iInicio,iFin,dtAlumno,pDetalle,iHorario"/>
                                                    </h:selectOneMenu>
                                                    <%--actionListener="#{managerConsulCurso.cambioCurso}"--%>
                                                </td>
                                            </tr>
                                            <tr class="alto">
                                                <td> <h:outputText value="Taller" styleClass="textoNegrita"/></td>
                                                <td colspan="2">
                                                    <h:selectOneMenu id="iTaller" style="width:450px" value="#{managerConsulCurso.w_tall_id}" styleClass="selector">
                                                        <f:selectItems value="#{managerConsulCurso.cboTaller}"/>
                                                        <a4j:support event="onchange"  reRender="iSeccion,iInicio,iFin,dtAlumno,pDetalle,iHorario"/>
                                                    </h:selectOneMenu>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><h:outputText value="F. Inicio" styleClass="textoNegrita"/></td>
                                                <td><rich:calendar id="secIni" value="#{managerConsulCurso.secFinicio}"
                                                               showWeeksBar="false" datePattern="dd-MM-yyyy" isDayEnabled="disablementFunction2" dayStyleClass="disabledClassesProv2">
                                                        <a4j:support event="onchange"  reRender="secFin"/>
                                                    </rich:calendar>

                                                </td>
                                                <td>
                                                    <h:outputText value="F. Fin " styleClass="textoNegrita"/>
                                                    <rich:calendar id="secFin" value="#{managerConsulCurso.secFfin}"
                                                                   showWeeksBar="false" datePattern="dd-MM-yyyy" isDayEnabled="disablementFunction" dayStyleClass="disabledClassesProv">
                                                        <a4j:support event="onchange"  reRender="secIni,iSeccion"/>
                                                    </rich:calendar>
                                                    <span style="text-align:left;"><a4j:commandButton image="/Imagenes/actions/viewmag.png" title="Buscar" alt="Buscar"
                                                                       actionListener="#{managerConsulCurso.cboSeccion}" reRender="iSeccion"/></span>
                                                </td>
                                            </tr>
                                            <tr class="alto">
                                                <td><h:outputText value="Seccion" styleClass="textoNegrita" /></td>
                                                <td colspan="2">
                                                    <h:selectOneMenu id="iSeccion" style="width:450px" value="#{managerConsulCurso.w_sec_id}" styleClass="selector">
                                                        <f:selectItems value="#{managerConsulCurso.cboSeccion}" />
                                                        <a4j:support event="onchange" reRender="iInicio,iFin,dtAlumno,pDetalle,iHorario,seAsignatura"
                                                                     actionListener="#{managerConsulCurso.seleccionarSeccion}" oncomplete="javascript:changeColor()"/>
                                                    </h:selectOneMenu>
                                                </td>
                                            </tr>
                                            <tr class="alto">
                                                <td>
                                                    <h:panelGroup id="pglblAsignatura">
                                                        <h:outputText id="lblAsignatura" value="Asignatura" styleClass="textoNegrita" rendered="#{managerConsulCurso.esProgAux}" />
                                                    </h:panelGroup>
                                                </td>
                                                <td colspan="2">
                                                    <h:panelGroup id="pgseAsignatura">
                                                        <h:selectOneMenu id="seAsignatura" style="width:450px" value="#{managerConsulCurso.idAsignatura}" rendered="#{managerConsulCurso.esProgAux}" styleClass="selector">
                                                            <f:selectItems value="#{managerConsulCurso.cboAsignaturas}" />
                                                            <a4j:support event="onchange" actionListener="#{managerConsulCurso.seleccionarSeccion}" reRender="iInicio,iFin,dtAlumno,pDetalle,iHorario"/>
                                                        </h:selectOneMenu>
                                                    </h:panelGroup>
                                                </td>
                                            </tr>

                                            <tr class="alto">

                                                <td><h:outputText value="Horario"  styleClass="textoNegrita" /></td>

                                                <td>
                                                    <div class="MarcoLeyenda">
                                                        <h:outputText value="#{managerConsulCurso.sec_horario}" id="iHorario" escape="false"  styleClass="textoChiko" />
                                                    </div>
                                                </td>
                                                <td><h:outputText value="Inicio"  styleClass="textoNegrita" /><br>
                                                    <h:outputText styleClass="textoChiko" value="#{managerConsulCurso.w_fecha_ini}" id="iInicio">
                                                        <f:convertDateTime pattern="dd / MM / yyyy" />
                                                    </h:outputText><br>
                                                    <h:outputText value="Fin"  styleClass="textoNegrita" /><br>
                                                    <h:outputText styleClass="textoChiko" value="#{managerConsulCurso.w_fecha_fin}" id="iFin">
                                                        <f:convertDateTime pattern="dd / MM / yyyy" />
                                                    </h:outputText>
                                                </td>
                                            </tr>
                                            <tr><td></td></tr>
                                            <tr class="alto">
                                                <td><h:outputText value="Reportes"  styleClass="textoNegrita" /></td>
                                                <td>

                                                    <h:selectOneRadio id="radio" layout="pageDirection" value="#{managerConsulCurso.w_rad_id}" styleClass="textoChiko">
                                                        <f:selectItems value="#{managerConsulCurso.rdoOpciones}" />
                                                    </h:selectOneRadio>

                                                </td>
                                                <td align="left">
                                                    <h:graphicImage value="/Imagenes/actions/printer.png" alt="Reporte" title="Reporte" width="50" height="50">
                                                        <a4j:support event="onclick" actionListener="#{managerConsulCurso.ImprimirFicha}" oncomplete="#{managerConsulCurso.oncomplete}" reRender="titulo,reporte"/>
                                                    </h:graphicImage>
                                                </td>

                                            </tr>

                                        </table>

                                    </rich:panel>
                                </td>
                                <td valign="top">
                                    <rich:panel style="width:100%" id="pDetalle">
                                        <div align="center">
                                            <h:outputText  styleClass="textoNegrita" value="<b>CURSO : </b>&nbsp; #{managerConsulCurso.w_curso} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-     <b>DOCENTE : </B> #{managerConsulCurso.w_docente}" escape="false" /><br />
                                            <br />
                                            <h:outputText style="color:#EC1E28;" rendered="#{not managerConsulCurso.contieneSisEva}" value="La sección NO contiene un sistema de evaluación" />
                                            <br />
                                        </div>
                                        <div style="height: 300px ; overflow-x:hidden; overflow-y:scroll;">
                                            <rich:dataTable id="dtAlumno"
                                                            value="#{managerConsulCurso.c_dataColumna}"
                                                            onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                            onRowMouseOut="this.style.backgroundColor='#F9F9F9'"
                                                            cellpadding="0" cellspacing="0"
                                                            width="100%"
                                                            var="row"
                                                            style="background-color: #F9F9F9;" styleClass="texto">
                                                <rich:columns value="#{managerConsulCurso.c_cabeceraColumna}"
                                                              var="column"
                                                              index="index"
                                                              begin="2"
                                                              sortOrder="#{column.ordering}">
                                                    <f:facet name="header">
                                                        <h:outputText value="#{column.name}" styleClass="textoNegrita"/>
                                                    </f:facet>
                                                    <h:outputText value="#{row[index]}" rendered="#{index <= 2}" styleClass="texto" />
                                                    <h:inputText value="#{row[index] }" style="width:30px;color:#{row[index]>= 13.0?'blue':'red'} " rendered="#{index > 2}" readonly="#{not managerConsulCurso.esRegistrosAcad}" styleClass="txtNota" />
                                                </rich:columns>
                                            </rich:dataTable>
                                        </div>


                                        <div style="margin-top: 20px" align="center">
                                            <a4j:commandButton value="Guardar" rendered="#{managerConsulCurso.esRegistrosAcad}" actionListener="#{managerConsulCurso.guardarNotas}" reRender="dtAlumno,iInicio,iFin,dtAlumno,pDetalle,iHorario"/> &nbsp;&nbsp;&nbsp;
                                            <a4j:commandButton value="Cancelar" rendered="#{managerConsulCurso.esRegistrosAcad}" actionListener="#{managerConsulCurso.seleccionarSeccion}" reRender="dtAlumno,iInicio,iFin,dtAlumno,pDetalle,iHorario" />
                                        </div>
                                    </rich:panel>
                                </td>

                            </tr>
                        </table>
                    </div>
                </h:form>

            </rich:panel>
            <a4j:form>
                <rich:modalPanel id="mp7" width="830" height="600"  zindex="4500">
                    <f:facet name="header">
                        <h:outputText id="titulo" value="Reporte" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/title-close.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp7')" title="Cerrar"/>
                    </f:facet>
                    <a4j:form>
                        <table>
                            <tr>
                                <td align="center" valign="middle">
                                    <rich:panel style="width : 790px; height:580px;" >
                                        <a4j:mediaOutput id="reporte" uriAttribute="data" style="width : 750px; height:550px;"
                                                         element="object" standby="cargando..."
                                                         createContent="#{managerConsulCurso.cargarReporte}"
                                                         mimeType="application/pdf"
                                                         value="1"/>
                                    </rich:panel>
                                </td>
                            </tr>
                        </table>
                    </a4j:form>
                </rich:modalPanel>
            </a4j:form>
        </body>
    </f:view>
</html>
