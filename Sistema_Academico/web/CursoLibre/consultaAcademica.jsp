<%-- 
    Document   : consultaAcademica
    Created on : 01-sep-2011, 16:56:12
    Author     : Richard Rondinel
--%>
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
                color: #004080;
            }

            .textoChiko{
                font-size: 11px;
                font-family: sans-serif;
                color: #004080;

            }
            .fuentePagina{
                color: #004080;
            }

        </style>
    </head>
    <f:view>
        <body>
            <jsp:include page="../TablaSistema/Menu.jsp"/>
            <rich:panel>
                <h:form>
                    <div style="width: 100%" align="center">
                        <table width="100%">
                            <tr>
                                <td width="15%"></td>
                                <td align="center" width="70%">
                                    <h:outputText value="Consulta Academica - Alumno" styleClass="textoCabecera"/>
                                </td>
                                <td align="right" width="15%">
                                </td>
                            </tr>
                        </table>
                        <br />
                        <h:outputText value="Alumno : " styleClass="textoNegrita" />
                        <h:inputText value="#{managerConsulAca.suggestion}" size="100" id="txt_dato" /><br />
                        <rich:suggestionbox id="suggestion"
                                            for="txt_dato" nothingLabel="Alumno No Encontrado"
                                            suggestionAction="#{managerConsulAca.autocomplete}" var="alu_sugerido"
                                            height="150" width="400" cellpadding="2"
                                            cellspacing="2" shadowOpacity="1" shadowDepth="1"
                                            minChars="3" rules="none" zindex="3500"
                                            usingSuggestObjects="true"
                                            >
                            <h:column>
                                <h:outputText value="#{alu_sugerido.w_alu_datos}" style="font-weight: bold;" />
                            </h:column>
                            <h:column>
                                <h:outputText value="#{alu_sugerido.w_alu_codigo}" style="font-weight: bold;" />
                            </h:column>
                            <a4j:support ajaxSingle="true" event="onselect"
                                         actionListener="#{managerConsulAca.seleccionarAlumno}" reRender="pDatos,iModulo,btninfo,dtSeccion" />
                        </rich:suggestionbox>
                        <rich:spacer height="10"/>
                        <rich:separator height="2" width="100%" />
                        <rich:spacer height="10"/>
                        <table width="100%" class="fuentePagina">
                            <tr>
                                <td width="50%" valign="top">
                                    <rich:panel style="width:100%" id="pDatos">
                                        <f:param id="iAluId" value="#{managerConsulAca.w_alu_id}"/>
                                        <a4j:commandLink   id="btninfo" value="#{managerConsulAca.w_men_deuda}"
                                                           actionListener="#{managerConsulAca.mostrarDeudaAlumno}" style="font-style:bold;font-size:14px;color:red"
                                                           oncomplete="#{managerConsulAca.oncomplete}" reRender="formDeuda"/>
                                        <table width="90%" border="0" align="center">
                                            <tr class="alto">
                                                <td width="150"><h:outputText value="Apellidos"  styleClass="textoNegrita" /></td>
                                                <td colspan="3"><h:outputText value="#{managerConsulAca.alumno.aluAppaterno} #{managerConsulAca.alumno.aluApmaterno}"  styleClass="texto" /></td>
                                                <td width="86" rowspan="5">&nbsp;</td>
                                            </tr>
                                            <tr class="alto">
                                                <td><h:outputText value="Nombres"  styleClass="textoNegrita" /></td>
                                                <td colspan="3"><h:outputText value="#{managerConsulAca.alumno.aluNombres}"  styleClass="texto"/> </td>
                                            </tr>
                                            <tr class="alto">
                                                <td><h:outputText value="Codigo" styleClass="textoNegrita" /></td>
                                                <td colspan="3"><h:outputText value="#{managerConsulAca.alumno.aluCodigo}" styleClass="texto"/></td>
                                            </tr>
                                            <tr class="alto">
                                                <td><h:outputText value="Clave" styleClass="textoNegrita" /></td>
                                                <td colspan="3"><h:outputText value="#{managerConsulAca.alumno.aluPassword}" styleClass="texto"/></td>
                                            </tr>
                                            <tr class="alto">
                                                <td>
                                                    <h:outputText value="Modulo" styleClass="textoNegrita" />
                                                </td>
                                                <td colspan="4">
                                                    <h:selectOneMenu id="iModulo" style="width:250px" value="#{managerConsulAca.w_mod_id}" styleClass="texto">
                                                        <f:selectItems value="#{managerConsulAca.cboModulo}"/>
                                                        <a4j:support event="onchange" reRender="pDetalle,dtSeccion"
                                                                     actionListener="#{managerConsulAca.traerSeccionesMatriculadas}"/>
                                                    </h:selectOneMenu>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                        </table>

                                    </rich:panel>
                                </td>
                                <td valign="top">
                                    <rich:panel style="width:100%" id="pDetalle">
                                        <rich:dataTable value="#{managerConsulAca.lstSecciones}" var="seccion"  rendered="#{not managerConsulAca.esProgAux}" id="dtSeccion" width="100%">
                                            <f:facet name="header">
                                                <h:outputText value="#{managerConsulAca.w_cabeceraModulo}" styleClass="textoNegrita" />
                                            </f:facet>
                                            <rich:column>
                                                <f:facet name="header"><h:outputText value="Nro"/> </f:facet>
                                                <h:outputText value="#{seccion.b_contador}"/>
                                                <span style="color: #fff;"><h:outputText value="#{seccion.secId}"/></span>
                                            </rich:column>
                                            <rich:column>
                                                <f:facet name="header"><h:outputText value="Curso"/> </f:facet>
                                                <h:outputText value="#{seccion.b_sec_curso}"/>
                                                <rich:toolTip rendered="#{not empty seccion.b_doc_nombre}">
                                                    <span style="white-space:nowrap">
                                                        <h:outputText value="#{seccion.b_doc_nombre}"/>
                                                    </span>
                                                </rich:toolTip>
                                            </rich:column>
                                            <rich:column>
                                                <f:facet name="header"><h:outputText value="Nota"/> </f:facet>
                                                <h:outputText value="#{seccion.b_sec_nota}" style="#{seccion.b_estyloNota}"/>
                                            </rich:column>
                                            <rich:column>
                                                <f:facet name="header"><h:outputText value="Fecha Inicio"/> </f:facet>
                                                <h:outputText value="#{seccion.b_fecha_ini}">
                                                    <f:convertDateTime pattern="dd / MM / yyyy"/>
                                                </h:outputText>
                                            </rich:column>
                                            <rich:column>
                                                <f:facet name="header"><h:outputText value="Fecha Fin"/> </f:facet>
                                                <h:outputText value="#{seccion.b_fecha_fin}">
                                                    <f:convertDateTime pattern="dd / MM / yyyy"/>
                                                </h:outputText>
                                            </rich:column>
                                            <rich:column>
                                                <f:facet name="header"><h:outputText value="Sede"/> </f:facet>
                                                <h:outputText value="#{seccion.b_sede}"/>
                                            </rich:column>
                                            <rich:column style="text-align:center;">
                                                <f:facet name="header"><h:outputText value="Horario"/> </f:facet>
                                                <a4j:commandButton rendered="#{not empty seccion.lstHorarioSeccion}" actionListener="#{managerConsulAca.mostrarHorarioSeccion}"
                                                                   image="/Imagenes/actions/calendar.gif" oncomplete="#{managerConsulAca.oncomplete}" reRender="mp_tbHorarioSeccion">
                                                    <a4j:actionparam name="secId" value="#{seccion.secId}" />
                                                </a4j:commandButton>
                                                <a4j:commandButton rendered="#{empty seccion.lstHorarioSeccion}" disabled="true" image="/Imagenes/actions/calendar_gris.png" />
                                            </rich:column>
                                        </rich:dataTable>

                                        <rich:dataTable value="#{managerConsulAca.lstSecciones}" var="seccion" id="dtSeccionProgAux" rendered="#{managerConsulAca.esProgAux}" width="100%">
                                            <f:facet name="header">
                                                <h:outputText value="#{managerConsulAca.w_cabeceraModulo}" styleClass="textoNegrita" />
                                            </f:facet>
                                            <rich:column>
                                                <f:facet name="header"><h:outputText value="Nro"/> </f:facet>
                                                <h:outputText value="#{seccion.b_contador}"/>
                                                <span style="color: #fff;"><h:outputText value="#{seccion.secId}"/></span>
                                            </rich:column>
                                            <rich:column>
                                                <f:facet name="header"><h:outputText value="Nivel"/> </f:facet>
                                                <h:outputText value="#{seccion.b_sec_modulo}"/>
                                                <rich:toolTip  rendered="#{not empty seccion.b_doc_nombre}">
                                                    <span style="white-space:nowrap">
                                                        <h:outputText value="#{seccion.b_doc_nombre}"/>
                                                    </span>
                                                </rich:toolTip>
                                            </rich:column>
                                            <rich:column>
                                                <f:facet name="header"><h:outputText value="Asignatura"/> </f:facet>
                                                <h:outputText value="#{seccion.b_sec_curso}"/>
                                                <rich:toolTip  rendered="#{not empty seccion.b_doc_nombre}">
                                                    <span style="white-space:nowrap">
                                                        <h:outputText value="#{seccion.b_doc_nombre}"/>
                                                    </span>
                                                </rich:toolTip>
                                            </rich:column>
                                            <rich:column>
                                                <f:facet name="header"><h:outputText value="Nota"/> </f:facet>
                                                <h:outputText value="#{seccion.b_sec_nota}" style="#{seccion.b_estyloNota}"/>
                                            </rich:column>
                                            <rich:column>
                                                <f:facet name="header"><h:outputText value="Sede"/> </f:facet>
                                                <h:outputText value="#{seccion.b_sede}"/>
                                            </rich:column>
                                        </rich:dataTable>
                                    </rich:panel>
                                </td>

                            </tr>
                            <tr>
                                <td align="right">
                                    <div style="width: 100%">
                                        <h:selectOneRadio id="radio" layout="lineDirection" value="#{managerConsulAca.w_rad_id}">
                                            <f:selectItems value="#{managerConsulAca.rdoOpciones}" />
                                        </h:selectOneRadio>


                                    </div>
                                </td>
                                <td align="left">
                                    <h:graphicImage value="/Imagenes/actions/printer.png" alt="Reporte" title="Reporte" width="50" height="50">
                                        <a4j:support event="onclick" actionListener="#{managerConsulAca.ImprimirFicha}" oncomplete="#{managerConsulAca.oncomplete}" reRender="titulo,reporte,mpEstablecerCurso"/>
                                    </h:graphicImage>
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
                                                         createContent="#{managerConsulAca.cargarReporte}"
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
        <rich:modalPanel id="mpEstablecerFechas" width="300" height="180">
            <f:facet name="header">
                <h:outputText id="tituloFechas" value="Establecer Fechas" />
            </f:facet>
            <f:facet name="controls">
                <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpEstablecerFechas')" title="Cerrar"/>
            </f:facet>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Fecha de Inicio : " />
                    <rich:calendar value="#{managerConsulAca.fechaInicio}" />
                    <h:outputText value="Fecha de Fin : " />
                    <rich:calendar value="#{managerConsulAca.fechaFin}" />
                    <h:panelGroup />
                    <h:graphicImage value="/Imagenes/actions/printer.png" alt="Reporte" title="Reporte" width="50" height="50">
                        <a4j:support event="onclick" actionListener="#{managerConsulAca.continuarImpresionModular}" oncomplete="#{managerConsulAca.oncomplete}" reRender="tituloFechas,reporte"/>
                    </h:graphicImage>
                </h:panelGrid>
            </h:form>
        </rich:modalPanel>
        
        <rich:modalPanel id="mpEstablecerCurso" width="250" height="180">
            <f:facet name="header">
                <h:outputText id="tituloCurso" value="Elegir Curso" />
            </f:facet>
            <f:facet name="controls">
                <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpEstablecerCurso')" title="Cerrar"/>
            </f:facet>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Elegir Curso : " />
                    <h:selectOneMenu value="#{managerConsulAca.w_cur_id}" >
                        <f:selectItems value="#{managerConsulAca.cboCursos}" />
                    </h:selectOneMenu>
                    <h:panelGroup />
                    <h:graphicImage value="/Imagenes/actions/printer.png" alt="Reporte" title="Reporte" width="50" height="50">
                        <a4j:support event="onclick" actionListener="#{managerConsulAca.continuarImpresionModular}" oncomplete="#{managerConsulAca.oncomplete}" reRender="tituloFechas,reporte"/>
                    </h:graphicImage>
                </h:panelGrid>
            </h:form>
        </rich:modalPanel>

        <rich:modalPanel id="mpHorarioSeccion" width="750" height="180">
            <f:facet name="header">
                <h:outputText id="titulo" value="Horario" />
            </f:facet>
            <f:facet name="controls">
                <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpHorarioSeccion')" title="Cerrar"/>
            </f:facet>
            <rich:dataTable width="100%" id="mp_tbHorarioSeccion" value="#{managerConsulAca.lstBeanHorarioSeccion}" 
                            var="horario" onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                            onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'">
                <rich:column>
                    <f:facet name="header"><h:outputText value="Nro"/> </f:facet>
                    <h:outputText value="#{horario.horId}"/>
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Día"/>
                    </f:facet>
                    <h:outputText value="#{horario.v_hor_dia}"/>
                </rich:column>
                <rich:column>
                    <f:facet name="header"><h:outputText value="Inicio"/> </f:facet>
                    <h:outputText value="#{horario.horHini}">
                        <f:convertDateTime pattern="hh:mm a"/>
                    </h:outputText>
                </rich:column>
                <rich:column>
                    <f:facet name="header"><h:outputText value="Fin"/> </f:facet>
                    <h:outputText value="#{horario.horHfin}">
                        <f:convertDateTime pattern="hh:mm a"/>
                    </h:outputText>
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Aula"/>
                    </f:facet>
                    <h:outputText value="#{horario.acAula.aulDes}"/>
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Docente"/>
                    </f:facet>
                    <h:outputText value="#{horario.acDocente.docNombre}"/>
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Tipo de Clase"/>
                    </f:facet>
                    <h:outputText value="#{horario.v_hor_tipo_clase}"/>
                </rich:column>

            </rich:dataTable>
        </rich:modalPanel>

        <rich:modalPanel id="mpDeuda" width="600" height="400" autosized="true" zindex="4000">
            <f:facet name="header">
                <h:outputText value=" Historial de Deuda"/>
            </f:facet>
            <f:facet name="controls">
                <h:panelGroup>
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('mpDeuda')" title="Cerrar"/>
                </h:panelGroup>
            </f:facet>

            <h:form id="formDeuda">
                <h:panelGrid columns="2">
                    <h:outputText value="CODIGO" style="font-size: 12px; font-weight: bold;"/>
                    <h:outputText  id= "valor1" value="#{managerConsulAca.aluCodigoDeuda}" style="font-size: 12px; font-weight: bold;"/>
                    <h:outputText value="NOMBRE" style="font-size: 12px; font-weight: bold;"/>
                    <h:outputText  id= "valor2" value="#{managerConsulAca.aluNombreDeuda}" style="font-size: 12px; font-weight: bold;"/>
                </h:panelGrid>
                <rich:dataTable id="tabladeudas"
                                onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                cellpadding="0"
                                cellspacing="0"
                                width="100%"
                                border="0"
                                value="#{managerConsulAca.lstDeudasAlumno}"
                                var="deud">
                    <rich:column>
                        <f:facet name="header">
                            <h:outputText value="Curso"/>
                        </f:facet>
                        <h:outputText value="#{deud.deu_modulo}" style="#{deud.alutar_style}" />
                    </rich:column>
                    <rich:column>
                        <f:facet name="header">
                            <h:outputText value="Concepto"/>
                        </f:facet>
                        <h:outputText value="#{deud.deu_curso}" style="#{deud.alutar_style}"/>
                    </rich:column>
                    <rich:column>
                        <f:facet name="header">
                            <h:outputText value="Monto a pagar"/>
                        </f:facet>
                        <h:outputText value="#{deud.deu_montopagar}">
                        </h:outputText>
                    </rich:column>
                    <rich:column>
                        <f:facet name="header">
                            <h:outputText value="Monto pagado"/>
                        </f:facet>
                        <h:outputText value="#{deud.deu_montopagado}"/>
                    </rich:column>
                    <rich:column>
                        <f:facet name="header">
                            <h:outputText value="Saldo"/>
                        </f:facet>
                        <h:outputText value="#{deud.deu_saldo}"/>
                    </rich:column>
                    <rich:column>
                        <f:facet name="header">
                            <h:outputText value="Fecha"/>
                        </f:facet>
                        <h:outputText value="#{deud.alumnoTarifa.alutarFechaProrroga}">
                            <f:convertDateTime pattern="dd / MM / yyyy"/>
                        </h:outputText>
                    </rich:column>
                </rich:dataTable>
            </h:form>

        </rich:modalPanel>

    </f:view>
</html>
