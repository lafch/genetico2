<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Edicion de Estructura de Pagos por Bloques</title>
    </head>
    <body>
        <f:view>
            <jsp:include page="../TablaSistema/Menu.jsp"></jsp:include>
            <rich:panel style=" width : 100%;" >
                <f:facet name="ESTRUCTURA DE PAGO BLOQUE"></f:facet>
                <h:form id="formInput" style="width:100%">
                    <table style="font-size:13px; font-family:verdana; width: 100%">
                        <tr>
                            <td width="26%" colspan="2" ><strong>EDICI&Oacute;N DE LAS ESTRUCTURAS DE PAGO POR BLOQUES</strong></td>
                            <td width="13%"></td>
                            <td width="51%" align="right"></td>
                            <td align=right width="10%">
                                <a4j:commandButton type="button" id="buscar" value="" title="Buscar"
                                                   image="/Imagenes/actions/viewmag.png"
                                                   style="border-width: 0px;"
                                                   action="#{managerEdicionEstructPagoBloque.buscar}"
                                                   reRender="fFechaPag, fFechaPro,fMon, mFechaPag, mFechaPro,mMon, lEsp, lSem, lSeming"
                                                   oncomplete="#{managerEdicionEstructPagoBloque.oncomplete}"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="7" width="100%"><hr width="100%" size="1" />
                        </tr>
                        <tr>
                            <td width="26%" colspan="2" />
                            <td width="14%" colspan="2" />
                            <td width="60%" />
                        </tr>
                        <tr style="height : 20px;">
                            <td width="13%">Semestre Destino: </td>
                            <td width="27%">
                                <h:outputLabel value="#{managerEdicionEstructPagoBloque.p_sems}" style="width : 180px;" />
                            </td>
                            <td width="13%">Especialidad:</td>
                            <td width="27%">
                                <h:selectOneMenu value="#{managerEdicionEstructPagoBloque.p_esp}">
                                    <f:selectItems value="#{managerEdicionEstructPagoBloque.p_esps}"/>
                                </h:selectOneMenu>
                            </td>
                            <td width="20%"/>
                        </tr>
                        <tr style="height : 20px;">
                            <td width="13%">Semestre ingreso: </td>
                            <td width="27%">
                                <h:selectOneMenu value="#{managerEdicionEstructPagoBloque.p_seming}" style="width : 180px;">
                                    <f:selectItems value="#{managerEdicionEstructPagoBloque.p_semings}"/>
                                </h:selectOneMenu>
                            </td>
                            <td width="13%"/><td width="27%"/><td width="20%"/>
                        </tr>
                        <tr style="height : 20px;">
                            <td width="13%" style="vertical-align: middle;">Filtros: </td>
                            <td width="27%">
                                <rich:panel>
                                    <h:selectManyCheckbox value="#{managerEdicionEstructPagoBloque.p_filtro}">
                                        <f:selectItems value="#{managerEdicionEstructPagoBloque.p_filtros}"/>
                                    </h:selectManyCheckbox>
                                </rich:panel>
                            </td>
                            <td width="13%"/><td width="27%"/><td width="20%"/>
                        </tr>
                        <tr style="height : 20px;">
                            <td width="13%" style="vertical-align: middle;">Modificar: </td>
                            <td width="27%">
                                <rich:panel>
                                    <h:selectManyCheckbox value="#{managerEdicionEstructPagoBloque.p_modif}">
                                        <f:selectItems value="#{managerEdicionEstructPagoBloque.p_modifs}"/>
                                    </h:selectManyCheckbox>
                                </rich:panel>
                            </td>
                            <td width="13%"/><td width="27%"/><td width="20%"/>
                        </tr>
                        <tr>
                            <td colspan="7" width="100%"><hr width="100%" size="1"></td>
                        </tr>
                        <tr><td width="13%"/><td width="27%"/><td width="13%"/><td width="27%"/><td width="20%"/></tr>
                        <tr align="center">
                            <td colspan="4">
                                <h:outputText id="rstl" escape="false"
                                              style="width: 100%; height: 50px; font-size: 12px; font-weight: bold;"
                                              value="#{managerEdicionEstructPagoBloque.resultado}"/>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:panel>

            <rich:modalPanel  id="mpBloque" minWidth="700"
                              autosized="true"
                              zindex="2000" onshow="showDiv();">
                <rich:effect  name="hideDiv"  for="contentDivBloque" type="Fade" />
                <rich:effect  name="showDiv"  for="contentDivBloque" type="Appear" />
                <div id="contentDivBloque">
                    <f:facet name="header">
                        <h:outputText value="Ingreso de datos a actualizar" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png"
                                        style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mpBloque')"
                                        title="Cerrar"/>
                    </f:facet>
                    <h:form>
                        <table width="100%">
                            <tr>
                                <td width="50%"></td>
                                <td align="right">
                                    <a4j:commandButton
                                        action="#{managerEdicionEstructPagoBloque.validarActualizacion}"
                                        image="/Imagenes/actions/filesave.png"
                                        title="Guardar"
                                        oncomplete="#{managerEdicionEstructPagoBloque.oncomplete}"
                                        reRender="rstl"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3">
                                    <rich:separator align="center" height="3px"/>
                                </td>
                            </tr>
                            <tr style="height : 20px;">
                                <td><b>Especialidad</b> -
                                    <h:outputLabel id="lEsp"
                                                   value="#{managerEdicionEstructPagoBloque.especialidad}"/>
                                </td>
                            </tr>
                            <tr style="height : 20px;">
                                <td><b>Semestre destino</b> -
                                    <h:outputLabel id="lSem"
                                                   value="#{managerEdicionEstructPagoBloque.p_sems}"/>
                                </td>
                            </tr>
                            <tr style="height : 20px;">
                                <td><b>Semestre ingreso</b> -
                                    <h:outputLabel id="lSeming"
                                                   value="#{managerEdicionEstructPagoBloque.semestreIngreso}"/>
                                </td>
                            </tr>
                            <tr style="height : 10px;" />
                            <tr>
                                <td>
                                    <rich:panel header="Datos para buscar">
                                        <h:panelGrid columns="2">
                                            <h:outputLabel value="Fecha de pago"/>
                                            <rich:calendar id="fFechaPag" datePattern="dd-MM-yyyy"
                                                           disabled="#{managerEdicionEstructPagoBloque.f_disable[0]}"
                                                           value="#{managerEdicionEstructPagoBloque.f_fecha_pago}"/>

                                            <h:outputLabel value="Fecha de prorroga"/>
                                            <rich:calendar id="fFechaPro" datePattern="dd-MM-yyyy"
                                                           disabled="#{managerEdicionEstructPagoBloque.f_disable[1]}"
                                                           value="#{managerEdicionEstructPagoBloque.f_fecha_prorroga}"/>

                                            <h:outputLabel value="Monto"/>
                                            <h:inputText id="fMon" disabled="#{managerEdicionEstructPagoBloque.f_disable[2]}"
                                                         value="#{managerEdicionEstructPagoBloque.f_monto}"/>
                                        </h:panelGrid>
                                    </rich:panel>
                                </td>
                                <td>
                                    <rich:panel header="Datos a modificar">
                                        <h:panelGrid columns="2">
                                            <h:outputLabel value="Fecha de pago"/>
                                            <rich:calendar id="mFechaPag" datePattern="dd-MM-yyyy"
                                                           disabled="#{managerEdicionEstructPagoBloque.m_disable[0]}"
                                                           value="#{managerEdicionEstructPagoBloque.m_fecha_pago}"/>

                                            <h:outputLabel value="Fecha de prorroga"/>
                                            <rich:calendar id="mFechaPro" datePattern="dd-MM-yyyy"
                                                           disabled="#{managerEdicionEstructPagoBloque.m_disable[1]}"
                                                           value="#{managerEdicionEstructPagoBloque.m_fecha_prorroga}"/>

                                            <h:outputLabel value="Monto"/>
                                            <h:inputText id="mMon" disabled="#{managerEdicionEstructPagoBloque.m_disable[2]}"
                                                         value="#{managerEdicionEstructPagoBloque.m_monto}"/>
                                        </h:panelGrid>
                                    </rich:panel>
                                </td>
                            </tr>
                        </table>
                    </h:form>
                </div>
            </rich:modalPanel>
        </f:view>
    </body>
</html>
