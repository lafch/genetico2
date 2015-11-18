<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                                                   action="#{managerCLEditEstPagBloq.buscar}"
                                                   reRender="fFechaPag, fFechaPro,fMon, mFechaPag, mFechaPro,mMon, lTalape, lSec"
                                                   oncomplete="#{managerCLEditEstPagBloq.oncomplete}"/>
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
                            <td width="13%">Taller Aperturado: </td>
                            <td width="27%">
                                <h:selectOneMenu value="#{managerCLEditEstPagBloq.p_talape}">
                                    <f:selectItems value="#{managerCLEditEstPagBloq.p_talapes}"/>
                                    <a4j:support event="onchange" reRender="i_seccion"
                                                 action="#{managerCLEditEstPagBloq.parametroSeccion}" />
                                </h:selectOneMenu>
                            </td>
                            <td width="20%"/>
                        </tr>
                        <tr style="height : 20px;">
                            <td width="13%">Seccion: </td>
                            <td width="27%">
                                <h:selectOneMenu id="i_seccion" value="#{managerCLEditEstPagBloq.p_sec}" style="width : 220px;">
                                    <f:selectItems value="#{managerCLEditEstPagBloq.p_secs}"/>
                                </h:selectOneMenu>
                            </td>
                            <td width="13%"/><td width="27%"/><td width="20%"/>
                        </tr>
                        <tr style="height : 20px;">
                            <td width="13%" style="vertical-align: top;">Filtros: </td>
                            <td width="27%">
                                <rich:panel>
                                    <h:selectManyCheckbox value="#{managerCLEditEstPagBloq.p_filtro}">
                                        <f:selectItems value="#{managerCLEditEstPagBloq.p_filtros}"/>
                                    </h:selectManyCheckbox>
                                </rich:panel>
                            </td>
                            <td width="13%"/><td width="27%"/><td width="20%"/>
                        </tr>
                        <tr style="height : 20px;">
                            <td width="13%" style="vertical-align: top;">Modificar: </td>
                            <td width="27%">
                                <rich:panel>
                                    <h:selectManyCheckbox value="#{managerCLEditEstPagBloq.p_modif}">
                                        <f:selectItems value="#{managerCLEditEstPagBloq.p_modifs}"/>
                                    </h:selectManyCheckbox>
                                </rich:panel>
                            </td>
                            <td width="13%"/><td width="27%"/><td width="20%"/>
                        </tr>
                        <tr>
                            <td colspan="7" width="100%"><hr width="100%" size="1"></td>
                        </tr>
                        <tr><td width="13%"/><td width="27%"/><td width="13%"/><td width="27%"/><td width="20%"/></tr>
                        <tr style="height : 20px; text-align: center;">
                            <td colspan="6">
                                <h:outputText id="rstl" style="width: 100%; height: 50px; font-size: 12px; font-weight: bold;"
                                              escape="false" value="#{managerCLEditEstPagBloq.resultado}"/>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:panel>

            <rich:modalPanel  id="mpBloque" minWidth="700"
                              autosized="true" zindex="2000">
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
                                    action="#{managerCLEditEstPagBloq.validarActualizacion}"
                                    image="/Imagenes/actions/filesave.png"
                                    title="Guardar"
                                    oncomplete="#{managerCLEditEstPagBloq.oncomplete}"
                                    reRender="rstl"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" width="100%"><hr width="100%" size="1" /></td>
                        </tr>
                        <tr style="height : 20px;">
                            <td><b>Taller aperturado</b> -
                                <h:outputLabel id="lTalape"
                                               value="#{managerCLEditEstPagBloq.tallerAperturado}"/>
                            </td>
                        </tr>
                        <tr style="height : 20px;">
                            <td><b>Secci&oacute;n</b> -
                                <h:outputLabel id="lSec"
                                               value="#{managerCLEditEstPagBloq.seccion}"/>
                            </td>
                        </tr>
                        <tr style="height : 10px;" />
                        <tr>
                            <td>
                                <rich:panel header="Datos para buscar">
                                    <h:panelGrid columns="2">
                                        <h:outputLabel value="Fecha de pago"/>
                                        <rich:calendar id="fFechaPag" datePattern="dd-MM-yyyy"
                                                       disabled="#{managerCLEditEstPagBloq.f_disable[0]}"
                                                       value="#{managerCLEditEstPagBloq.f_fecha_pago}"/>

                                        <h:outputLabel value="Fecha de prorroga"/>
                                        <rich:calendar id="fFechaPro" datePattern="dd-MM-yyyy"
                                                       disabled="#{managerCLEditEstPagBloq.f_disable[1]}"
                                                       value="#{managerCLEditEstPagBloq.f_fecha_prorroga}"/>

                                        <h:outputLabel value="Monto"/>
                                        <h:inputText id="fMon" disabled="#{managerCLEditEstPagBloq.f_disable[2]}"
                                                     value="#{managerCLEditEstPagBloq.f_monto}"/>
                                    </h:panelGrid>
                                </rich:panel>
                            </td>
                            <td>
                                <rich:panel header="Datos a modificar">
                                    <h:panelGrid columns="2">
                                        <h:outputLabel value="Fecha de pago"/>
                                        <rich:calendar id="mFechaPag" datePattern="dd-MM-yyyy"
                                                       disabled="#{managerCLEditEstPagBloq.m_disable[0]}"
                                                       value="#{managerCLEditEstPagBloq.m_fecha_pago}"/>

                                        <h:outputLabel value="Fecha de prorroga"/>
                                        <rich:calendar id="mFechaPro" datePattern="dd-MM-yyyy"
                                                       disabled="#{managerCLEditEstPagBloq.m_disable[1]}"
                                                       value="#{managerCLEditEstPagBloq.m_fecha_prorroga}"/>

                                        <h:outputLabel value="Monto"/>
                                        <h:inputText id="mMon" disabled="#{managerCLEditEstPagBloq.m_disable[2]}"
                                                     value="#{managerCLEditEstPagBloq.m_monto}"/>
                                    </h:panelGrid>
                                </rich:panel>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>
        </f:view>
    </body>
</html>
