<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Modulo de Informes - Clientes Matriculados</title>
        <style type="text/css">
            #tb_header  th {
                text-align: left;
                background-color: #CFE1FA;
            }
            #tb_header{
                width: 70%;
                margin: auto;
            }
            select{
                width: 70%;
            }
        </style>
    </head>
    <body>
        <f:view>
            <jsp:include page="../TablaSistema/Menu.jsp"/>
            <h1>Fecha de Registro</h1>
            <rich:separator style="width:'80%'" />
            &nbsp;
            <rich:panel id="pContenedor">
                <h:form id="form1">
                    <div align="center">

                        <h:outputText value="Fecha del Registro "/> 
                        <rich:calendar value="#{managerInfoMatricula.w_fechaIni}" datePattern="dd-MM-yyyy" id="iFechaInicio" /><span>  al  </span>
                        <rich:calendar value="#{managerInfoMatricula.w_fechaFin}" datePattern="dd-MM-yyyy" id="iFechaFin"  />
                        <table align="center" width="900px">
                            <tr>
                                <td><h:outputText value="Centro"/> </td>
                                <td>
                                    <h:selectOneMenu value="#{managerInfoMatricula.centroId}" id="wCentro" style="width:200px">
                                        <f:selectItems value="#{managerInfoMatricula.cboCentro}" />
                                        <a4j:support event="onchange" reRender="form1"/>
                                    </h:selectOneMenu>
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td><h:outputText value="Area"/> </td>
                                <td>
                                    <h:selectOneMenu value="#{managerInfoMatricula.iAreaId}" id="wArea" style="width:250px">
                                        <f:selectItems value="#{managerInfoMatricula.cboArea}" />
                                        <a4j:support event="onchange" reRender="wModulo"/>
                                    </h:selectOneMenu>
                                </td>
                                <td><h:outputText value="Modulo"/></td>
                                <td>
                                    <h:selectOneMenu value="#{managerInfoMatricula.iModId}" id="wModulo" style="width:250px">
                                        <f:selectItems value="#{managerInfoMatricula.cboModulo}" />
                                        <a4j:support event="onchange" reRender="wCurso"/>
                                    </h:selectOneMenu>
                                </td>
                                <td><h:outputText value="Curso"/> </td>
                                <td>
                                    <h:selectOneMenu value="#{managerInfoMatricula.iCursoId}" id="wCurso" style="width:250px">
                                        <f:selectItems value="#{managerInfoMatricula.cboCurso}" />
                                    </h:selectOneMenu>
                                </td>
                            </tr>
                        </table>
                        <a4j:commandButton value="enviar" actionListener="#{managerInfoMatricula.verMatriculados}" image="/Imagenes/actions/viewmag.png" reRender="dtListado" />
                    </table>
                </div>
                <div align="center" style="margin-top: 10px">


                    <rich:dataTable value="#{managerInfoMatricula.w_listausuarios}" var="lista" id="dtListado" width="750">
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Nro" />
                            </f:facet>
                            <h:outputText value="#{lista.w_contador}"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Usuario" />
                            </f:facet>
                            <h:outputText value="#{lista.w_usuUsuario}"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="T. Matriculados" />
                            </f:facet>
                            <h:outputText value="#{lista.w_cantiMatriculado}"/>
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Detalle M." />
                            </f:facet>
                            <f:param value="#{lista.w_usuId}" id="p_usuId" />
                            <a4j:commandButton image="/Imagenes/actions/user.png"
                                               actionListener="#{managerInfoMatricula.listadoMatriculados}"
                                               reRender="dtListaAlumno,dsListaAlumno,form3"
                                               oncomplete="#{managerInfoMatricula.oncomplete}" />
                        </rich:column>
                       <%-- <rich:column>
                            <f:facet name="header">
                                <h:outputText value="T. Informes" />
                            </f:facet>
                            <h:outputText value="#{lista.w_cantiInformes}"/>
                        </rich:column>--%>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Detalle I." />
                            </f:facet>
                            <a4j:commandButton image="/Imagenes/actions/user_gris.png"
                                               actionListener="#{managerInfoMatricula.listadoInformes}"
                                               reRender="dtListaAlumno,dsListaAlumno,form3"
                                               oncomplete="#{managerInfoMatricula.oncomplete}" />
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="T. Informes2" />
                            </f:facet>
                            <h:outputText value="#{lista.w_cantiAlumnos}"/>
                        </rich:column>

                    </rich:dataTable>
                </div>

            </h:form>
        </rich:panel>

        <rich:modalPanel id="mpListaAlumno" autosized="true">
            <f:facet name="header">
                <h:outputText value="Listado Detallado"/>
            </f:facet>

            <f:facet name="controls">
                <h:panelGroup>
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpListaAlumno')" title="Cerrar"/>
                </h:panelGroup>
            </f:facet>
            <h:form id="form3">
                <div align="center">
                    <rich:datascroller for="dtListaAlumno" maxPages="50" id="dsListaAlumno" style="width: 650px" reRender="dtListaAlumno"  />
                    <rich:dataTable value="#{managerInfoMatricula.listadoAlumnos}" var="alu" id="dtListaAlumno" width="650" rows="10">
                        <rich:column >
                            <f:facet name="header">
                                <h:outputText value="Nro"/>
                            </f:facet>
                            <h:outputText value="#{alu.contador}"/>
                        </rich:column>

                        <rich:column >
                            <f:facet name="header">
                                <h:outputText value="Apellido y nombres"/>
                            </f:facet>
                            <h:outputText value="#{alu.nomAlum}"/>
                        </rich:column>
                        <rich:column >
                            <f:facet name="header">
                                <h:outputText value="Telefonos"/>
                            </f:facet>
                            <h:outputText value="#{alu.telAlum}"/>
                        </rich:column>
                        <rich:column >
                            <f:facet name="header">
                                <h:outputText value="Area"/>
                            </f:facet>
                            <h:outputText value="#{alu.sDesArea}"/>
                        </rich:column>
                        <rich:column >
                            <f:facet name="header">
                                <h:outputText value="Modulo"/>
                            </f:facet>
                            <h:outputText value="#{alu.des_modulo}"/>
                        </rich:column>
                        <rich:column >
                            <f:facet name="header">
                                <h:outputText value="Curso"/>
                            </f:facet>
                            <h:outputText value="#{alu.sDesCurso}"/>
                        </rich:column>
                        <rich:column >
                            <f:facet name="header">
                                <h:outputText value="F. Registro"/>
                            </f:facet>
                            <h:outputText value="#{alu.sFechaRegistro}">
                                <f:convertDateTime pattern="dd-MM-yyyy"/>
                            </h:outputText>
                        </rich:column>
                        <rich:column >
                            <f:facet name="header">
                                <h:outputText value="Estado"/>
                            </f:facet>
                            <h:outputText value="#{alu.estado}"/>
                        </rich:column>
                    </rich:dataTable>
                </div>
            </h:form>

        </rich:modalPanel>

    </f:view>
</body>
</html>