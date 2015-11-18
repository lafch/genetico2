<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<%          HttpSession sesion = request.getSession();
            if (sesion.getAttribute("usuario") == null) {
%>
<jsp:forward page="../index1.jsp"/>
<%          }
%>
<script>
    function  abrirModal(){
//        Richfaces.hideModalPanel('panelModalSincrono').show();
        Richfaces.showModalPanel('panelModalSincrono');
        return false;
    }
    
    function cerrarModal(){
//        Richfaces.hideModalPanel('panelModalSincrono').hide();
        Richfaces.hideModalPanel('panelModalSincrono');
        return false;
    }
    
</script>
    
<f:subview id="subview">
    <a4j:loadStyle src="resource:///css/formulario.css"/>
    <a4j:loadStyle src="resource:///css/richStyle.xcss"/>
    <link rel="icon" type="image/x_icon" href="/Sistema_Academico/Imagenes/actions/pinguino.gif"/>
    <h:form id="formTool">
        <a4j:region>
            <rich:toolBar binding="#{managerUsuario.menu.toolBar}" width="100%" styleClass="menu"/>
        </a4j:region>

            
 
    </h:form>
    
</f:subview>

    <a4j:status  id="commonstatus"  onstart="return abrirModal()"  onstop="return cerrarModal()"/>
    <rich:modalPanel  id="panelModalSincrono" style="background:#FFF;" height="300" zindex="2500">
        <table style="width:100%">
            <tr>
                <td style="text-align: center"><h:graphicImage
                        value="/Imagenes/actions/cargando01.gif" style="text-align:center" />
                </td>
            </tr>
        </table>
    </rich:modalPanel>  