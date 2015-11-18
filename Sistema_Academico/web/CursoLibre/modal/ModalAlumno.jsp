<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<f:subview id="svAlumnoUpdate">
    <a4j:loadStyle src="resource:///css/formulario.css"/>
    <a4j:loadStyle src="resource:///css/richStyle.xcss"/>

    <rich:modalPanel  id="mpUpdate" autosized="true"
                      minHeight="400" minWidth="400"
                      zindex="4000"  onshow="showDiv();">
        <f:facet name="header">
            <h:outputText value="Registro Alumno" />
        </f:facet>
        <f:facet name="controls">
            <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpUpdate')" title="Cerrar"/>
        </f:facet>
        <h:form id="falumno">
            <table width="100%" style="font-size:10px; font-family:verdana" cellspacing="0" border="0">
                <tr>
                    <td colspan="3" align="right">
                        <a4j:commandButton image="/Imagenes/actions/filesave.png"
                                           title="Guardar" 
                                           actionListener="#{managerCLAlumno.guardarDatos}"
                                           oncomplete="#{managerCLAlumno.oncomplete}"
                                           />

                    </td>
                </tr>
                <tr><td colspan="3"><hr width="100%"/></td></tr>
                <tr>
                    <td colspan="3" align="left">
                        <h:outputText value="(*) Campos Obligatorios." style="color: red;"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h:outputText value="Ap. Paterno"/>
                    </td>
                    <td>
                        <h:inputText value="#{managerCLAlumno.alu_appaterno}" id="iPaterno"/>
                        <rich:spacer width="5px"/>
                        <h:outputText value="(*)" style="color: red;"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h:outputText value="Ap. Materno"/>
                    </td>
                    <td>
                        <h:inputText value="#{managerCLAlumno.alu_apmaterno}" id="iMaterno" />
                        <rich:spacer width="5px"/>
                        <h:outputText value="(*)" style="color: red;"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h:outputText value="Nombres"/>
                    </td>
                    <td>
                        <h:inputText value="#{managerCLAlumno.alu_nombres}" id="iNombres" />
                        <rich:spacer width="5px"/>
                        <h:outputText value="(*)" style="color: red;"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h:outputText value="E-mail"/>
                    </td>
                    <td>
                        <h:inputText value="#{managerCLAlumno.alu_mail}" id="iEmail" style="width: 250px;">
                        </h:inputText>
                        <rich:spacer width="5px"/>

                    </td>
                </tr>
                <tr>
                    <td>
                        <h:outputText value="Telefono"/>
                    </td>
                    <td>
                        <h:inputText value="#{managerCLAlumno.alu_telefono}" id="iTelefono"/>

                        <rich:spacer width="5px"/>
                        <h:outputText value="(*)" style="color: red;"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h:outputText value="Celular"/>
                    </td>
                    <td>
                        <h:inputText value="#{managerCLAlumno.alu_celular}" id="iCelular" />
                        <rich:spacer width="5px"/>
                        <h:outputText value="(*)" style="color: red;"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h:outputText value="Direccion"/>
                    </td>
                    <td colspan="2">
                        <h:inputText value="#{managerCLAlumno.alu_direccion}" id="iDireccion" style="width: 250px;"/>
                        <rich:spacer width="5px"/>

                    </td>
                </tr>
                <tr>
                    <td><h:outputText value="Forma pago"/></td>
                    <td>
                        <h:selectOneMenu  id="nFormaPago"  value="#{managerCLAlumno.alu_forma_pago}" style="width : 180px;" tabindex="11">
                            <f:selectItems value="#{managerCLAlumno.cboFormaPago}" />
                        </h:selectOneMenu>
                        <rich:spacer width="5px"/>
                        <h:outputText value="(*)" style="color: red;"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h:outputText value="Fecha Nac."/>
                    </td>
                    <td>
                        <rich:calendar  id="iFechax"  value="#{managerCLAlumno.alu_fecha_nacimiento}" datePattern="dd-MM-yyyy" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <h:outputText value="DNI"/>
                    </td>
                    <td>
                        <h:inputText value="#{managerCLAlumno.alu_dni}" id="iDni"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h:outputText value="Alumno tipo"/>
                    </td>
                    <td>
                        <h:selectOneMenu id="nTipo" value="#{managerCLAlumno.alu_tipo}" style="width : 180px;" tabindex="9">
                            <f:selectItems value="#{managerCLAlumno.cboTipo}" />
                            <a4j:support event="onchange" reRender="falumno"/>
                        </h:selectOneMenu>
                    </td>
                </tr>
                <h:panelGroup rendered='#{"085001" eq managerCLAlumno.alu_tipo}'>
                    <tr>
                        <td><h:outputText value="Detalle Externo" /></td>
                        <td>
                            <h:selectOneMenu value="#{managerCLAlumno.detAluExterno}">
                                <f:selectItems value="#{managerCLAlumno.detallesAluExterno}" />
                            </h:selectOneMenu>
                        </td>
                    </tr>
                </h:panelGroup>
                <h:panelGroup rendered='#{"085002" eq managerCLAlumno.alu_tipo}'>
                    <tr>
                        <td><h:outputText value="Unidad" /></td>
                        <td>
                            <h:selectOneMenu value="#{managerCLAlumno.aluInterUnidad}">
                                <f:selectItems value="#{managerCLAlumno.aluInterUnidades}" />
                            </h:selectOneMenu>
                        </td>
                    </tr>
                </h:panelGroup>
                <h:panelGroup rendered='#{"085002" eq managerCLAlumno.alu_tipo}'>
                    <tr>
                        <td><h:outputText value="Detalle Unidad" /></td>
                        <td>
                            <h:selectOneMenu id="selUnidadDet" value="#{managerCLAlumno.detInterUnidad}">
                                <f:selectItems value="#{managerCLAlumno.detInterUnidades}" />
                            </h:selectOneMenu>
                        </td>
                    </tr>
                </h:panelGroup>
                <tr>
                    <td>
                        <h:outputText value="Departamento"/>
                    </td>
                    <td>
                        <h:selectOneMenu id="nDepartamento" value="#{managerCLAlumno.departamento}" style="width : 180px;" tabindex="9">
                            <f:selectItems value="#{managerCLAlumno.cboDepartamento}" />
                            <a4j:support event="onchange"  reRender="nProvincia, nDistrito"/>
                        </h:selectOneMenu>
                    </td>
                </tr>
                <tr>
                    <td> <h:outputText value="Provincia"/></td>
                    <td>
                        <h:selectOneMenu  id="nProvincia"  value="#{managerCLAlumno.provincia}" style="width : 180px;" tabindex="10">
                            <f:selectItems value="#{managerCLAlumno.cboProvincia}" />
                            <a4j:support event="onchange"  reRender="nDistrito" />
                        </h:selectOneMenu>
                    </td>
                </tr>
                <tr>
                    <td><h:outputText value="Distrito"/></td>
                    <td>
                        <h:selectOneMenu  id="nDistrito"  value="#{managerCLAlumno.alu_distrito}" style="width : 180px;" tabindex="11">
                            <f:selectItems value="#{managerCLAlumno.cboDistrito}" />
                        </h:selectOneMenu>
                        <h:outputText value="(*)" style="color: red;"/>
                    </td>
                </tr>
                <tr>
                    <td><h:outputText value="Sexo"/></td>
                    <td>
                        <h:selectOneMenu  id="nSexo"  value="#{managerCLAlumno.alu_sexo}" style="width : 180px;" tabindex="11">
                            <f:selectItems value="#{managerCLAlumno.cbosexo}" />
                        </h:selectOneMenu>
                    </td>
                </tr>
                <%/*<tr>
                    <td><h:outputText value="Unidad"/></td>
                    <td>
                        <h:selectOneMenu  id="nUnidad"  value="#{managerCLAlumno.alu_unidad}" style="width : 180px;" tabindex="11">
                            <f:selectItems value="#{managerCLAlumno.cboUnidad}" />
                        </h:selectOneMenu>
                    </td>
                </tr>
                <tr>
                    <td><h:outputText value="Procedencia"/></td>
                    <td>
                        <h:selectOneMenu  id="nProcedencia"  value="#{managerCLAlumno.alu_procedencia}" style="width : 180px;" tabindex="11">
                            <f:selectItems value="#{managerCLAlumno.cboProcedencia}" />
                        </h:selectOneMenu>
                    </td>
                </tr> */%>
                <tr>
                    <td><h:outputText value="Parentesco"/></td>
                    <td>
                        <h:selectOneMenu  id="nParentesco"  value="#{managerCLAlumno.alu_parentesco}" style="width : 180px;" tabindex="11">
                            <f:selectItems value="#{managerCLAlumno.cboParentesco}" />
                        </h:selectOneMenu>
                    </td>
                </tr>
                <tr>
                    <td><h:outputText value="Familiar"/></td>
                    <td>
                        <h:inputText value="#{managerCLAlumno.alu_nombre_familiar}" id="nFamiliar"/>
                    </td>
                </tr>
            </table>
        </h:form>
    </rich:modalPanel>
</f:subview>