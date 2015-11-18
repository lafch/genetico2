<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.: Mantenimiento - Estructura :.</title>
    </head>
    <f:view>
        <body>
            <h:form>
                <% /*<h:outputText value="#{managerMantenimientoEstruc.w_cabecera}" id="iCabecera" />
                            <rich:dataTable value="#{managerMantenimientoEstruc.listaArbolAcademico}"
                            var="lista" id="dtLista">
                            <rich:column>
                            <f:facet name="header">
                            <h:outputText value="Id"/>
                            </f:facet>
                            <h:outputText value="#{lista.arbId}"/>
                            </rich:column>
                            <rich:column>
                            <f:facet name="header">
                            <h:outputText value="Descripcion"/>
                            </f:facet>
                            <h:outputText value="#{lista.arbDescripcion}"/>
                            </rich:column>
                            <rich:column>
                            <f:facet name="header"><h:outputText value=""/> </f:facet>
                            <f:param value="#{lista.arbId}" id="p_arb_id"/>
                            <a4j:commandButton value="e" actionListener="#{managerMantenimientoEstruc.seleccionarEstructura}"
                            reRender="dtLista,iCabecera"/>
                            </rich:column>
                            </rich:dataTable>
                            <br />
                            <a4j:commandButton value="retroceder" actionListener="#{managerMantenimientoEstruc.retrocederEstructura}"
                        reRender="dtLista,iCabecera"/>*/%>

                <rich:tree style="width:300px" nodeSelectListener="#{managerMantenimientoEstruc.seleccionarElemento}"
                           reRender="selectedNode" ajaxSubmitSelection="true"  switchType="client"
                           value="#{managerMantenimientoEstruc.treeNode}" var="item"  >
                    <rich:treeNode>
                        <h:outputText value="#{item}" escape="false"/>
                    </rich:treeNode>
                </rich:tree>

                <h:outputText escape="false" value="Selected Node: #{managerMantenimientoEstruc.nombreNodo}" id="selectedNode" />


            </h:form>
        </body>
    </f:view>
</html>
