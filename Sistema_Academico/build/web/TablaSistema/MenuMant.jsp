<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Mantenimiento de Aulas</title>
         <link href="../css/componentes.css" rel="stylesheet" media="screen"/>
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
            
            
            $(document).ready(function() {
	$(".numerico").on('keypress', function(e) {
		tamano(e,this);
		var cadena=numerico();
		var k = String.fromCharCode(e.which);
		if(cadena.indexOf(k)==-1){
			e.preventDefault();
		}
	});
	
	$(".decimal").on('keypress', function(e) {
		tamano(e,this);
		var cadena=decimal();
		var valor=$(this).val();
		var k = String.fromCharCode(e.which);
		if(k=="." && valor.indexOf(k)!=-1){
			e.preventDefault();
		}
		if(cadena.indexOf(k)==-1){
			e.preventDefault();
		}
	});
});


function numerico(){
	return "0123456789";
}
function decimal(){
	return "0123456789.";
}
function tamano(e,cod){
	var cadenaAnt = $(cod).val();
	var tamId = $(cod).attr("id").split(",");
	var longitud;
	if(tamId.length==2){
		longitud=tamId[1];
		if(cadenaAnt.length>=longitud){
			e.preventDefault();
		}
	}
}
        </script> 
    </head>
    <body>
        <f:view >
            <jsp:include page="../TablaSistema/Menu.jsp"></jsp:include>
            <rich:panel style=" width : 100%; background:#FFF; border:1px !important;">
                <f:facet name="FACULTADES">
                </f:facet>
                <h:form id="form1" style="width:100%">
                    <table width="100%" style="font-size:10px; font-family:verdana">
                        <tr >
                            <td width="20%" colspan="2"><strong>MANTENIMIENTO DE MENU</strong>
                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right">
                                <h:graphicImage value="/Imagenes/new-32.png" style="border-width: 0px;cursor: pointer;">
                                    <a4j:support oncomplete="javascript:Richfaces.showModalPanel('mp',{width:450, top:130})" 
                                                 event="onclick" action="#{managerMenuMant.Nuevo}" 
                                                 reRender="CODIGO_i,DESCRIPCION_i,nPadre_i,nivel_i,accion_i" />
                                </h:graphicImage>
                            </td>
                            <td width="30%"></td>
                            <td width="30%" align="right">
                                <h:graphicImage value="/Imagenes/printer-32.png" title="Imprimir Listado"/>
                            </td>
                            <td align=right width="50%">
                                <a4j:commandButton type="button" id="buscar" title="Buscar"
                                                   action="#{managerMenuMant.Seleccionar}"
                                                   image="/Imagenes/search-32.png"
                                                   reRender="tablaMaster, barra"/>
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
                            <td width="10%">Menu Nivel 01:
                            </td>
                            <td width="50%">
                                <h:selectOneMenu value="#{managerMenuMant.iIdMenu01}"  >
                                    <f:selectItems value="#{managerMenuMant.cboMenu01}" />
                                    <a4j:support event="onchange" reRender="form1"/>
                                </h:selectOneMenu></td>
                            <td width="50%"></td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputLabel value="Menu Nivel 02" rendered="#{managerMenuMant.vista02}"/>
                            </td>
                            <td width="50%">
                                <h:selectOneMenu id="menu02" value="#{managerMenuMant.iIdMenu02}" rendered="#{managerMenuMant.vista02}">
                                    <f:selectItems value="#{managerMenuMant.cboMenu02}" />
                                    <a4j:support event="onchange" reRender="form1"/>
                                </h:selectOneMenu></td>
                            <td width="50%"></td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputLabel value="Menu Nivel 03" rendered="#{managerMenuMant.vista03}"/>
                            </td>
                            <td width="50%">
                                
                                <h:selectOneMenu id="menu03" value="#{managerMenuMant.iIdMenu03}" rendered="#{managerMenuMant.isVista03()}">
                                    <f:selectItems value="#{managerMenuMant.cboMenu03}" />
                                    <a4j:support event="onchange" reRender="form1"/>
                                </h:selectOneMenu></td>
                            <td width="50%"></td>
                        </tr>
                        <tr>
                            <td width="10%"><h:outputLabel value="Menu Nivel 04" rendered="#{managerMenuMant.vista04}"/>
                            </td>
                            <td width="50%">
                                <h:selectOneMenu id="menu04" value="#{managerMenuMant.iIdMenu04}" rendered="#{managerMenuMant.vista04}">
                                    <f:selectItems value="#{managerMenuMant.cboMenu04}" />
                                   
                                </h:selectOneMenu></td>
                            <td width="50%"></td>
                        </tr>
                        <tr>
                            <td></td><td></td><td></td><td></td>
                        </tr>
                        <tr>
                            <td colspan="7" width="100%">
                                <hr width="100%" size="1">
                            </td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                        </tr>
                    </table>
                </h:form>
                <table width="100%">
                    <tr>
                        <td align="center">
                            <h:form id="frmLstAulas">
                                <rich:datascroller id="barra" align="right"  for="tablaMaster"
                                                   maxPages="8"  style=" width : 70%;"/>
                                <rich:spacer height="20px"/>
                                <rich:dataTable id="tablaMaster" rows="10"
                                                onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                cellpadding="0" cellspacing="0" border="0"
                                                width="70%" value="#{managerMenuMant.listaMenu}" var="menu">
                                    <rich:column style="#{menu.bColorNivel}">
                                        <f:facet name="header">
                                            <h:outputText value="Id" />
                                        </f:facet>
                                        <h:outputText value="#{menu.b_id}"/>
                                    </rich:column>

                                    <rich:column style="#{menu.bColorNivel}">
                                        <f:facet name="header">
                                            <h:outputText value="Codigo" />
                                        </f:facet>
                                        <h:outputText value="#{menu.bMenCodigo}" rendered="#{menu.view}" />
                                        <h:inputText value="#{menu.bMenCodigo}" rendered="#{menu.editable}" maxlength="45"/>
                                    </rich:column>

                                    <rich:column style="#{menu.bColorNivel}">
                                        <f:facet name="header">
                                            <h:outputText value="Descripcion" />
                                        </f:facet>
                                        <h:outputText value="#{menu.bMenDesc}" rendered="#{menu.view}" />
                                        <h:inputText value="#{menu.bMenDesc}" rendered="#{menu.editable}" maxlength="45"/>
                                    </rich:column>

                                    <rich:column style="#{menu.bColorNivel}">
                                        <f:facet name="header">
                                            <h:outputText value="Accion" />
                                        </f:facet>
                                        <h:outputText value="#{menu.bMenAccion}" rendered="#{menu.view}" />

                                        <h:selectOneMenu value="#{menu.bMenAccion}" rendered="#{menu.editable}">
                                            <f:selectItems value="#{menu.cboPagina}" />
                                        </h:selectOneMenu>
                                    </rich:column>

                                    <rich:column style="#{menu.bColorNivel}">
                                        <f:facet name="header">
                                            <h:outputText value="Padre" />
                                        </f:facet>
                                        <h:outputText value="#{menu.bDesPadreMenu}" rendered="#{menu.view}" />

                                        <h:selectOneMenu value="#{menu.bIdPadreMenu}" rendered="#{menu.editable}">
                                            <f:selectItems value="#{menu.cboPadreMenu}" />
                                            <a4j:support event="onchange" reRender="nivel_s"/>
                                        </h:selectOneMenu>
                                    </rich:column>

                                    <rich:column style="#{menu.bColorNivel}">
                                        <f:facet name="header">
                                            <h:outputText value="Nivel" />
                                        </f:facet>
                                        <h:outputText  value="#{menu.sMenNivel}" rendered="#{menu.view}"/>

                                        <h:selectOneMenu id="nivel_s" value="#{menu.sMenNivel}" rendered="#{menu.editable}">
                                            <f:selectItems value="#{menu.cboOpcion}" />
                                        </h:selectOneMenu>
                                    </rich:column>

                                    <rich:column style="#{menu.bColorNivel}">
                                        <f:facet name="header">
                                            <h:outputText value="Estado" />
                                        </f:facet>
                                        <h:outputText  value="#{menu.sEstado}"/>

                                    </rich:column>

                                    <rich:column style="text-align: center;">
                                        <f:facet name="header">
                                            <h:outputText value="Edit" />
                                        </f:facet>

                                        <h:commandButton actionListener="#{menu.EditarFila}" value="" image="/Imagenes/actions/klipper_dock.png" title="Editar" rendered="#{menu.view}"/>
                                        <f:param id="id" value="#{menu.b_id}" />
                                        <f:param id="codigo" value="#{menu.bMenCodigo}" />
                                        <f:param id="desc"   value="#{menu.bMenDesc}" />
                                        <f:param id="accion"   value="#{menu.bMenAccion}" />
                                        <f:param id="idpadre"   value="#{menu.bIdPadreMenu}" /> 
                                        <f:param id="nivel"   value="#{menu.sMenNivel}" />
                                        <f:param id="estado" value="#{menu.sEstado}" />


                                        <a4j:commandButton actionListener="#{menu.Editar}"  image="/Imagenes/actions/filesave.png" value=""  title="Guardar" rendered="#{menu.editable2}" reRender="tablaMaster, barra" oncomplete="#{menu.oncomplete}"/>
                                        <h:commandButton actionListener="#{menu.Cancelar}" image="/Imagenes/actions/fileclose.png"   value=""  title="Cancelar" rendered="#{menu.editable}"/>
                                    </rich:column>

                                    <rich:column style="text-align: center;">
                                        <f:facet name="header" >
                                            <h:outputText value="Delete" />
                                        </f:facet>
                                        <a4j:commandButton  image="/Imagenes/actions/delete.gif"  title="Eliminar"
                                                          actionListener="#{managerMenuMant.EliminarFila}"
                                                          action="#{managerMenuMant.Seleccionar}"  
                                                          onclick="javascript:return (confirm('¿Esta realmente seguro de Eliminar?'));"   oncomplete="#{managerMenuMant.oncomplete}" reRender="tablaMaster, barra"/>
                                        <f:param id="delete"  value="#{menu.b_id}" />
                                    </rich:column>
                                </rich:dataTable>
                            </h:form>
                        </td>
                    </tr>
                </table>
            </rich:panel>

            <rich:modalPanel  id="mp" minHeight="170" minWidth="650" height="200" width="500" zindex="2000">
                <f:facet name="header">
                    <h:outputText value="Registro de Menu" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('mp')" title="Cerrar"/>
                </f:facet>

                <h:form>
                    <table width="100%" style="font-size:10px; font-family:verdana" onload="document.getElementById('DESCRIPCION_i').focus()">
                        <tr >
                            <td align="right" colspan="2">
                                <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar" 
                                                     actionListener="#{managerMenuMant.Enviar1}"
                                                     reRender="tablaMaster"
                                                     oncomplete="#{managerMenuMant.oncomplete}"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr size="1"></td>
                        </tr>
                        <tr>
                            <td class="tdLabel" width="22%">*CODIGO:</td>
                            <td>
                                <h:inputText label="CODIGO" id="CODIGO_i"  style=" width : 180px;" value="#{managerMenuMant.bMenCodigo}"  maxlength="45" disabled="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel">*DESCRIPCION:</td>
                            <td>
                                <h:inputText id="DESCRIPCION_i" label="DESCRIPCION" value="#{managerMenuMant.bMenDesc}" style="width : 180px;" onkeypress="return validarLetras(event);"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="tdLabel">*MENU PADRE:</td>
                            <td>
                                <h:selectOneMenu  id="nPadre_i" value="#{managerMenuMant.bIdPadreMenu}">
                                    <f:selectItems value="#{managerMenuMant.cboPadreMenu}" />
                                    <a4j:support event="onchange" reRender="nivel_i"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdLabel">*NIVEL:</td>
                            <td>
                                <h:selectOneMenu id="nivel_i" value="#{managerMenuMant.sMenNivel}" >
                                    <f:selectItems value="#{managerMenuMant.cboOpcion}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>

                        <tr>
                            <td class="tdLabel">*ACCION:</td>
                            <td>
                                <h:selectOneMenu id="accion_i" value="#{managerMenuMant.bMenAccion}">
                                    <f:selectItems value="#{managerMenuMant.cboPagina}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr size="1"></td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>


        </f:view>
    </body>
</html>
