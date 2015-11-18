<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Mantenimiento de horarios</title>
        <style type="text/css">
            .panel-horario{
                width:  100%  !important;
                height: 50px !important;
                border: none;
                display:table;
                white-space: pre; /* CSS 2.0 */
                white-space: pre-wrap; /* CSS 2.1 */
                white-space: pre-line; /* CSS 3.0 */
                white-space: -pre-wrap; /* Opera 4-6 */
                white-space: -o-pre-wrap; /* Opera 7 */
                white-space: -moz-pre-wrap; /* Mozilla */
                white-space: -hp-pre-wrap; /* HP */
                word-wrap: break-word; /* IE 5+ */
            }

            .tblHorGen .dr-table-cell{
                padding: 0px !important;
            }

            .tblHorGen .columna{
                width:  100px  !important;
            }

            .letrasPanel{
                text-transform: capitalize;
            }

            .tooltip {
                background-color: #FFF;
                border-width:3px;
                z-index: 998 !important;
            }

            .menu-list{
                z-index: 999 !important;
            }

            .tooltip-custom-body {
                background-color: orange;
            }

            .tooltip-text {
                width: 350px;
                height: 80px;
                cursor: arrow;
                border-width: 2px;
                text-align: center;
                display: table-cell;
                vertical-align: middle;
            }

            .tooltipData {
                font-weight: bold;
            }

            .buttonLink{
                background-image: url('/Imagenes/search-32.png');
            }

            .btnMensaje{
                background: #0069B4;
                border: 1px solid;
                border-radius: 5px;
                color: #FFF;
                padding: 10px;
                text-decoration: none;
                -webkit-box-shadow: 0px 4px 5px 0px rgba(0,0,0,0.75);
                -moz-box-shadow: 0px 4px 5px 0px rgba(0,0,0,0.75);
                box-shadow: 0px 4px 5px 0px rgba(0,0,0,0.75);
            }

            .btnMensaje img{
                vertical-align: middle;
            }

            .dr-menu-list-border{ 
                border-color:#0069B4 !important; 
                border-radius: 5px !important;
            }
        </style>
    </head>
    <body>
        <f:view>
            <jsp:include page="../TablaSistema/Menu.jsp"></jsp:include>
            <rich:panel style=" width : 100%; background:#FFF; border:0px !important;" id="panelPrincipal" >
                <f:facet name="FACULTADES">
                </f:facet>
                <h:form id="form1" style="width:100%">
                    <table width="100%" style="font-size:10px; font-family:verdana">
                        <tr>
                            <td width="20%" colspan="2"><strong>LISTADO DE HORARIOS</strong></td>
                            <td width="30%"></td>
                            <td align=right width="50%">

                                <h:commandButton type="button" id="buscar"
                                                 action="#{managerHorario.cargarArbol}"
                                                 image="/Imagenes/search-32.png"
                                                 title="Buscar" />
                                <a4j:commandButton image="/Imagenes/calendar-32.png" id="generarHorario"
                                                   actionListener="#{managerHorario.GenerarHorario}"
                                                   oncomplete="Richfaces.showModalPanel('mp',{width:450, top:130});"
                                                   reRender="filtro"
                                                   />


                            </td>
                        </tr>
                        <tr>
                            <td width="30%" colspan="2">Semestre:</td>
                            <td width="70%" colspan="3">
                                <h:selectOneMenu id="iSemestre" value="#{managerHorario.idSemestre}" style="width : 180px;">
                                    <f:selectItems value="#{managerHorario.cboSemestres}"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td width="30%" colspan="2">
                                Facultad:
                            </td>
                            <td width="70%" colspan="3">
                                <h:selectOneMenu id="oFacultad" value="#{managerHorario.idFacultad}" style="width : 180px;">
                                    <f:selectItems value="#{managerHorario.cboFacultadesPrincipal}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr><td colspan="5"><hr width="100%"/></td></tr>
                        <tr>
                            <td style="vertical-align:top;" >
                                <rich:tree value="#{managerHorario.arbol}"
                                           ajaxSubmitSelection="true"
                                           switchType="ajax"
                                           nodeSelectListener="#{managerHorario.Seleccion}"
                                           var="item" reRender="tablaMaster, o_curso" rendered="#{managerHorario.vistaArbolTabla}"/>
                            </td>
                            <td  style="vertical-align:top;" colspan="5">
                                <table width="100%">
                                    <tr style="height: 25px;">
                                        <td align="right"><h:outputText value="#{managerHorario.n_cur_aperturado}" id="o_curso" rendered="#{managerHorario.vistaArbolTabla}"/></td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <rich:dataTable id="tablaMaster" rows="30"
                                                            onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                            onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                            cellpadding="0" cellspacing="0" border="0"
                                                            width="100%" value="#{managerHorario.listaSecciones}" var="secciones" rendered="#{managerHorario.vistaArbolTabla}">
                                                <h:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Id de la seccion"/>
                                                    </f:facet>
                                                    <h:outputText value="#{secciones.id_sec}"/>
                                                </h:column>
                                                <h:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Id del Semestre"/>
                                                    </f:facet>
                                                    <h:outputText value="#{secciones.id_sem}"/>
                                                </h:column>
                                                <h:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Codigo de la seccion"/>
                                                    </f:facet>
                                                    <h:outputText value="#{secciones.codigo_sec}"/>
                                                </h:column>
                                                <h:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Nombre de la seccion"/>
                                                    </f:facet>
                                                    <h:outputText value="#{secciones.nombre_sec}"/>
                                                </h:column>
                                                <h:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Vacantes"/>
                                                    </f:facet>
                                                    <h:outputText value="#{secciones.vacantes_sec}"/>
                                                </h:column>
                                                <h:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Matriculados"/>
                                                    </f:facet>
                                                    <h:outputText value="#{secciones.vacantesOcupadas}"/>
                                                </h:column>
                                                <h:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Asignar Horario"/>
                                                    </f:facet>
                                                    <f:param value="#{secciones.id_sec}" id="id_seccion"/>
                                                    <f:param value="#{secciones.id_sem}" id="id_semestre"/>
                                                    <a4j:commandButton image="/Imagenes/actions/horario.png"
                                                                       actionListener="#{managerHorario.AsignarHorario}"
                                                                       oncomplete="Richfaces.showModalPanel('mpHorarios',{width:800, top:50});"
                                                                       reRender="formHorarios" />
                                                </h:column>
                                            </rich:dataTable>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:panel>

            <rich:modalPanel id="mpHorarios" zindex="3" 
                             minWidth="1300" minHeight="900" style="overflow-y: scroll;background:#FFF;">
                <f:facet name="header">
                    <h:outputText value="Registro de Horario Por Curso" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('mpHorarios')" title="Cerrar"/>
                </f:facet>
                <h:form id="formHorarios">
                    <table width="100%" >
                        <tr>
                            <td align="right">
                                <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"
                                                     actionListener="#{managerHorario.Insertar}"
                                                     oncomplete="#{managerHorario.oncomplete}" reRender="tablaHorario"/>
                                <h:inputHidden value="#{managerHorario.id_sec_horario}" id="id_sec_horario"/>
                                <h:inputHidden value="#{managerHorario.id_sem_horario}" id="id_sem_horario"/>
                            </td>
                        </tr>
                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                        <tr>
                            <td>
                                <table width="100%">
                                    <tr><td>Curso:</td>
                                        <td><h:outputText value="#{managerHorario.n_cur_aperturado}" id="nombreCurso"/></td>
                                    </tr>
                                    <tr>
                                        <td width="20%">Turno:</td>
                                        <td><h:selectOneMenu value="#{managerHorario.turno_id}" id="turnos">
                                                <f:selectItems value="#{managerHorario.comboTurnos}"/>
                                                <a4j:support event="onchange" action="#{managerHorario.setearTurno}" reRender="tablaHorario,aulas,tipos,docentes,formHorarios"/>
                                            </h:selectOneMenu></td>
                                    </tr>
                                    <tr>
                                        <td width="20%">Aula:</td>
                                        <td>
                                            <h:selectOneMenu value="#{managerHorario.aula_id}" id="aulas">
                                                <f:selectItems value="#{managerHorario.comboAulas}"/>
                                                <a4j:support event="onchange" reRender="tablaHorario" />
                                            </h:selectOneMenu>
                                        </td></tr>
                                    <tr><td width="20%">Tipo:</td>
                                        <td>
                                            <h:selectOneMenu value="#{managerHorario.tipo_id}" id="tipos">
                                                <f:selectItems value="#{managerHorario.comboTipos}"/>
                                                <a4j:support event="onchange" reRender="tablaHorario"/>
                                            </h:selectOneMenu>

                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="20%"><h:outputText value="Docente:" rendered="#{managerHorario.turno_id!=0}"/></td>
                                        <td><h:selectOneMenu value="#{managerHorario.docente_id}" id="docentes" rendered="#{managerHorario.turno_id!=0}">
                                                <f:selectItems value="#{managerHorario.comboDocentes}"/>
                                                <a4j:support event="onchange" reRender="tablaHorario,formHorarios" action="#{managerHorario.cargarDisponibilidad}" oncomplete="#{managerHorario.oncomplete}"/>

                                            </h:selectOneMenu>
                                            <a4j:commandButton image="#{managerHorario.imagenDisDocente}" id="btnDisponibilidadHorariaDoc"
                                                               title="Horario"
                                                               actionListener="#{managerHorario.cargarHorarioDisponibilidadPorDocente}"
                                                               oncomplete="#{managerHorario.oncomplete}" rendered="#{managerHorario.turno_id!=0}"
                                                               reRender="formHoraria_disDoc"/>

                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td width="100%" colspan="5">
                                <rich:dataTable id="tablaHorario" rows="10"
                                                onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                cellpadding="0" cellspacing="0" width="100%"  border="0" 
                                                value="#{managerHorario.tablaHorario}" var="horario" >

                                    <f:param value="#{managerHorario.turno_id}" id="ho_turno"/>
                                    <f:param value="#{managerHorario.aula_id}" id="ho_aula"/>
                                    <f:param value="#{managerHorario.tipo_id}" id="ho_tipo"/>
                                    <f:param value="#{managerHorario.docente_id}" id="ho_docente"/>
                                    <f:param value="#{horario.id_hor}" id="param_id_turno"/>
                                    <f:param value="#{horario.listaHorario}" id="ho_horarios"/>
                                    <f:param value="#{horario.ide}" id="ho_ide"/>

                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Id" />
                                        </f:facet>
                                        <h:outputText value="#{horario.id_hor}"/>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Hora Inicio" />
                                        </f:facet>
                                        <h:outputText value="#{horario.inicio_hor}"/>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Hora Fin" />
                                        </f:facet>
                                        <h:outputText value="#{horario.fin_hor}"/>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Lunes" />
                                        </f:facet>
                                        <f:param value="#{horario.indice_id_lun}" id="param_identificador_lunes"/>
                                        <h:inputHidden value="#{horario.indice_id_lun}"/>
                                        <rich:panel id="panelLunes" style="#{horario.bColorCeldaHorario_Lunes}" styleClass="letrasPanel">
                                            <h:outputText value="#{horario.lunes_hor}" id="lunes" escape="false" style="#{horario.bColorCeldaHorario_Lunes}"  />
                                            <a4j:support actionListener="#{horario.seleccionarLunes}"
                                                         immediate="true" event="onclick" reRender="tablaHorario" rendered="#{horario.b_horarioNuevoLunes}"
                                                         oncomplete="#{horario.oncomplete}"/>
                                            <rich:toolTip id="tt1" styleClass="tooltip" layout="block" rendered="#{horario.lunes_hor_tp!=''}">
                                                <h:outputText value="#{horario.lunes_hor_tp}"/>
                                            </rich:toolTip>
                                        </rich:panel>
                                        <rich:contextMenu event="onclick" attachTo="panelLunes" submitMode="ajax" rendered="#{horario.b_horarioEstablecidoLunes==true?(horario.lunes_hor !='' ? managerHorario.b_verdadero : managerHorario.b_falso):horario.b_horarioEstablecidoLunes}" styleClass="menu-list">
                                            <rich:menuItem value="Editar"      actionListener="#{managerHorario.seleccionarLunesEditar}"  oncomplete="#{managerHorario.oncomplete}" reRender="mpTipoClase,formTipoClase"></rich:menuItem>
                                            <rich:menuItem value="Eliminar"    actionListener="#{managerHorario.seleccionarLunesEliminar}"  oncomplete="#{managerHorario.oncomplete}"></rich:menuItem>
                                        </rich:contextMenu>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Martes" style="#{horario.bColorCeldaHorario_Martes}"/>
                                        </f:facet>
                                        <f:param value="#{horario.indice_id_mar}" id="param_identificador_martes"/>
                                        <h:inputHidden value="#{horario.indice_id_mar}"/>
                                        <rich:panel id="panelMartes" style="#{horario.bColorCeldaHorario_Martes}" styleClass="letrasPanel">
                                            <h:outputText value="#{horario.martes_hor}" style="#{horario.bColorCeldaHorario_Martes}" id="martes" escape="false"/>
                                            <a4j:support actionListener="#{horario.seleccionarMartes}" disabled="#{horario.b_horarioEstablecidoMartes}" rendered="#{horario.b_horarioNuevoMartes}"
                                                         immediate="true" event="onclick"  reRender="tablaHorario"
                                                         oncomplete="#{horario.oncomplete}"/>
                                            <rich:contextMenu event="onclick" attachTo="panelMartes" submitMode="ajax" rendered="#{horario.b_horarioEstablecidoMartes==true?(horario.martes_hor !='' ? managerHorario.b_verdadero : managerHorario.b_falso):horario.b_horarioEstablecidoMartes}" styleClass="menu-list">
                                                <rich:menuItem value="Editar"      actionListener="#{managerHorario.seleccionarMartesEditar}"  oncomplete="#{managerHorario.oncomplete}" reRender="mpTipoClase,formTipoClase"></rich:menuItem>
                                                <rich:menuItem value="Eliminar"    actionListener="#{managerHorario.seleccionarMartesEliminar}"  oncomplete="#{managerHorario.oncomplete}"></rich:menuItem>
                                            </rich:contextMenu>
                                            <rich:toolTip id="tt2" styleClass="tooltip" layout="block" rendered="#{horario.martes_hor_tp!=''}">
                                                <h:outputText value="#{horario.martes_hor_tp}"/>
                                            </rich:toolTip>
                                        </rich:panel>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Miercoles" />
                                        </f:facet>
                                        <f:param value="#{horario.indice_id_mie}" id="param_identificador_miercoles" />
                                        <h:inputHidden value="#{horario.indice_id_mie}"/>
                                        <rich:panel id="panelMiercoles" style="#{horario.bColorCeldaHorario_Miercoles}" styleClass="letrasPanel">
                                            <h:outputText value="#{horario.miercoles_hor}" style="#{horario.bColorCeldaHorario_Miercoles}" id="miercoles" escape="false"/>
                                            <a4j:support actionListener="#{horario.seleccionarMiercoles}" disabled="#{horario.b_horarioEstablecidoMiercoles}" rendered="#{horario.b_horarioNuevoMiercoles}"
                                                         immediate="true" event="onclick"  reRender="tablaHorario"
                                                         oncomplete="#{horario.oncomplete}"/>
                                            <rich:contextMenu event="onclick" attachTo="panelMiercoles" submitMode="ajax" rendered="#{horario.b_horarioEstablecidoMiercoles==true?(horario.miercoles_hor !='' ? managerHorario.b_verdadero : managerHorario.b_falso):horario.b_horarioEstablecidoMiercoles}" styleClass="menu-list">
                                                <rich:menuItem value="Editar"      actionListener="#{managerHorario.seleccionarMiercolesEditar}"  oncomplete="#{managerHorario.oncomplete}" reRender="mpTipoClase,formTipoClase"></rich:menuItem>
                                                <rich:menuItem value="Eliminar"    actionListener="#{managerHorario.seleccionarMiercolesEliminar}"  oncomplete="#{managerHorario.oncomplete}"></rich:menuItem>
                                            </rich:contextMenu>
                                            <rich:toolTip id="tt3" styleClass="tooltip" layout="block" rendered="#{horario.miercoles_hor_tp!=''}">
                                                <h:outputText value="#{horario.miercoles_hor_tp}"/>
                                            </rich:toolTip>
                                        </rich:panel>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Jueves" />
                                        </f:facet>
                                        <f:param value="#{horario.indice_id_jue}" id="param_identificador_jueves"/>
                                        <h:inputHidden value="#{horario.indice_id_jue}"/>
                                        <rich:panel id="panelJueves" style="#{horario.bColorCeldaHorario_Jueves}" styleClass="letrasPanel">
                                            <h:outputText value="#{horario.jueves_hor}" style="#{horario.bColorCeldaHorario_Jueves}" id="jueves" escape="false"/>
                                            <a4j:support actionListener="#{horario.seleccionarJueves}" disabled="#{horario.b_horarioEstablecidoJueves}" rendered="#{horario.b_horarioNuevoJueves}"
                                                         immediate="true" event="onclick"  reRender="tablaHorario"
                                                         oncomplete="#{horario.oncomplete}"/>

                                            <rich:contextMenu event="onclick" attachTo="panelJueves" submitMode="ajax" rendered="#{horario.b_horarioEstablecidoJueves==true?(horario.jueves_hor !='' ? managerHorario.b_verdadero : managerHorario.b_falso):horario.b_horarioEstablecidoJueves}" styleClass="menu-list">
                                                <rich:menuItem value="Editar"      actionListener="#{managerHorario.seleccionarJuevesEditar}"  oncomplete="#{managerHorario.oncomplete}" reRender="mpTipoClase,formTipoClase"></rich:menuItem>
                                                <rich:menuItem value="Eliminar"    actionListener="#{managerHorario.seleccionarJuevesEliminar}"  oncomplete="#{managerHorario.oncomplete}"></rich:menuItem>
                                            </rich:contextMenu>
                                            <rich:toolTip id="tt4" styleClass="tooltip" layout="block" rendered="#{horario.jueves_hor_tp!=''}">
                                                <h:outputText value="#{horario.jueves_hor_tp}"/>
                                            </rich:toolTip>
                                        </rich:panel>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Viernes" />
                                        </f:facet>
                                        <f:param value="#{horario.indice_id_vie}" id="param_identificador_viernes"/>
                                        <h:inputHidden value="#{horario.indice_id_vie}"/>
                                        <rich:panel id="panelViernes" style="#{horario.bColorCeldaHorario_Viernes}" styleClass="letrasPanel">
                                            <h:outputText value="#{horario.viernes_hor}" style="#{horario.bColorCeldaHorario_Viernes}" id="viernes" escape="false"/>
                                            <a4j:support actionListener="#{horario.seleccionarViernes}" disabled="#{horario.b_horarioEstablecidoViernes}" rendered="#{horario.b_horarioNuevoViernes}"
                                                         immediate="true" event="onclick"  reRender="tablaHorario"
                                                         oncomplete="#{horario.oncomplete}"/>
                                            <rich:contextMenu event="onclick" attachTo="panelViernes" submitMode="ajax" rendered="#{horario.b_horarioEstablecidoViernes==true?(horario.viernes_hor !='' ? managerHorario.b_verdadero : managerHorario.b_falso):horario.b_horarioEstablecidoViernes}" styleClass="menu-list">
                                                <rich:menuItem value="Editar"      actionListener="#{managerHorario.seleccionarViernesEditar}"  oncomplete="#{managerHorario.oncomplete}" reRender="mpTipoClase,formTipoClase,formHorarios"></rich:menuItem>
                                                <rich:menuItem value="Eliminar"    actionListener="#{managerHorario.seleccionarViernesEliminar}"  oncomplete="#{managerHorario.oncomplete}"></rich:menuItem>
                                            </rich:contextMenu>
                                            <rich:toolTip id="tt5" styleClass="tooltip" layout="block" rendered="#{horario.viernes_hor_tp!=''}">
                                                <h:outputText value="#{horario.viernes_hor_tp}"/>
                                            </rich:toolTip>
                                        </rich:panel>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Sabado" />
                                        </f:facet>
                                        <rich:panel id="panelSabado" style="#{horario.bColorCeldaHorario_Sabado}" styleClass="letrasPanel">
                                            <f:param value="#{horario.indice_id_sab}" id="param_identificador_sabado"/>
                                            <h:inputHidden value="#{horario.indice_id_sab}"/>
                                            <h:outputText value="#{horario.sabado_hor}" style="#{horario.bColorCeldaHorario_Sabado}" id="sabado" escape="false"/>
                                        </rich:panel>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Domingo" style="#{horario.bColorCeldaHorario_Domingo}"/>
                                        </f:facet>
                                        <rich:panel id="panelDomingo" styleClass="letrasPanel" >
                                            <f:param value="#{horario.indice_id_dom}" id="param_identificador_domingo"/>
                                            <h:inputHidden value="#{horario.indice_id_dom}"/>
                                            <h:outputText value="#{horario.domingo_hor}" style="#{horario.bColorCeldaHorario_Sabado}" id="domingo" escape="false"/>
                                        </rich:panel>
                                    </h:column>
                                </rich:dataTable>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>

            <rich:modalPanel id="mpTipoClase" minWidth="100" height="150"
                             resizeable="true" zindex="4" style="overflow-y: scroll">
                <f:facet name="header">
                    <h:outputText value="Tipo de Clase"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png"
                                        style="cursor:pointer" title="Cerrar"
                                        onclick="Richfaces.hideModalPanel('mpTipoClase')"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="formTipoClase">

                    <table width="100px" style="font-size:12px; font-family:verdana">
                        <tr>
                            <td width="20%">Tipo de Clase:</td>
                            <td><h:selectOneMenu value="#{managerHorario.tipo_idEditar}" id="tiposEditar">
                                    <f:selectItems value="#{managerHorario.comboTiposEditar}"/>
                                    <a4j:support event="onchange"  reRender="formTipoClase"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td width="20%"><h:outputText value="Lab. Disponibles" rendered="#{managerHorario.tipo_idEditar=='018003'?true:false}"/></td>
                            <td><h:selectOneMenu value="#{managerHorario.lab_id}" id="labDis" rendered="#{managerHorario.tipo_idEditar=='018003'?true:false}">
                                    <f:selectItems value="#{managerHorario.comboLaboratorio}"/>
                                    <a4j:support event="onchange"  reRender="formTipoClase"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"> <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"
                                                 actionListener="#{managerHorario.InsertarTipoClase}" reRender="tablaHorario"
                                                 oncomplete="#{managerHorario.oncomplete}"/>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel  id="mpEliminarHorario"
                              minHeight="250" minWidth="300"
                              height="250" width="300"
                              zindex="2000" onshow="showDiv();">
                <rich:effect  name="hideDiv"  for="contentDivEliminarPago" type="Fade" />
                <rich:effect  name="showDiv"  for="contentDivEliminarPago" type="Appear" />
                <div id="contentDivEliminarPago">
                    <f:facet name="header">
                        <h:outputText value="Eliminar Horario" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png"
                                        style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mpEliminarHorario')"
                                        title="Cerrar"/>
                    </f:facet>
                    <center>
                        <h:form id="fromEliminarHorario">
                            <h:outputLabel>
                                <h2>Esta seguro de Eliminar ?</h2>
                            </h:outputLabel>
                            <a4j:commandButton value="Aceptar" reRender="tablaHorario"
                                               oncomplete="#{managerHorario.oncomplete}"
                                               actionListener="#{managerHorario.eliminarHorario}"/>
                            <rich:spacer width="20px" />
                            <h:commandButton value="Cancelar" onclick="Richfaces.hideModalPanel('mpEliminarHorario')" />
                        </h:form>
                    </center>
                </div>
            </rich:modalPanel>

            <rich:modalPanel  id="mpTrasladarHorario"
                              minHeight="250" minWidth="300"
                              height="250" width="300"
                              zindex="2000" style="background:#FFFFFF;">
                <div id="contentDivTrasladarHorario">
                    <f:facet name="header">
                        <h:outputText value="Publicar Horario" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png"
                                        style="cursor:pointer"
                                        onclick="Richfaces.hideModalPanel('mpTrasladarHorario')"
                                        title="Cerrar"/>
                    </f:facet>
                    <center>
                        <h:form id="fromTrasladarHorario">
                            <h:outputLabel>
                                <h2>Esta seguro de Publicar este Horario?</h2>
                            </h:outputLabel>
                            <table>
                                <tr>
                                    <td>
                                        <rich:panel id="pnlTrasladar" style="width:150px; heigth:39px; padding: 2px !important; background-color: transparent !important;
                                                    border-color: transparent !important;">
                                            <a4j:htmlCommandLink id="btnTrasladar" value="Publicar Horario" action="#{managerHorario.trasladarHorario}" immediate="true" styleClass="btnMensaje"  > 
                                                <h:graphicImage id="image" value="/Imagenes/trasladar-32.png" title="Publicar Horario"/>
                                            </a4j:htmlCommandLink>
                                            <a4j:support actionListener="#{managerHorario.trasladarHorarioProceso}" immediate="true" event="onclick" oncomplete="#{managerHorario.oncomplete}" reRender="filtro"/>
                                        </rich:panel>
                                    </td>
                                    <td>
                                        
                                        <a4j:htmlCommandLink id="btnCancelarTrasladar" value="Cancelar" onclick="Richfaces.hideModalPanel('mpTrasladarHorario')" styleClass="btnMensaje">
                                            <h:graphicImage id="imageCancelar" value="/Imagenes/cancelar-32.png" title="Cancelar" width="32" height="32"/>
                                        </a4j:htmlCommandLink>
                                    </td>
                                </tr>
                            </table>
                            </h:form>
                    </center>
                </div>
            </rich:modalPanel>

            <rich:modalPanel  id="mp" minWidth="1000" minHeight="600"
                              resizeable="true" zindex="3" style="overflow-y: scroll;">
                <f:facet name="header">
                    <h:outputText value="Generar Horario" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('mp')" title="Cerrar"/>
                </f:facet>

                <h:form id="filtro">
                    <table width="100%" style="font-size:10px; font-family:verdana">
                        <tr>
                            <td>Facultad:</td>
                            <td>
                                <a4j:region id="Pro2">
                                    <h:selectOneMenu id="Facultad" value="#{managerHorario.b_facultad}" style="width:240px">
                                        <f:selectItems value="#{managerHorario.comboFacultades}" />
                                        <a4j:support event="onchange"  reRender="filtro"/>
                                    </h:selectOneMenu>

                                </a4j:region>
                            </td>
                            <td>Semestres:</td>
                            <td>
                                <h:selectOneMenu id="semestre_f" value="#{managerHorario.semestre_f}">
                                    <f:selectItems value="#{managerHorario.comboSemestres}"/>
                                    <a4j:support event="onchange"  reRender="filtro"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>

                        <tr>
                            <td>Especialidad:</td>
                            <td>
                                <h:selectOneMenu  id="Especialidad"  value="#{managerHorario.b_especialidad}"  style="width:220px">
                                    <f:selectItems value="#{managerHorario.comboEspecialidades}" />
                                    <a4j:support event="onchange"  reRender="filtro"/>
                                </h:selectOneMenu>
                            </td>
                            <td width="20%">Turno:</td>
                            <td><h:selectOneMenu value="#{managerHorario.turnoGeneracion_id}" id="Turnos">
                                    <f:selectItems value="#{managerHorario.comboTurnos2}"/>
                                    <a4j:support event="onchange" reRender="filtro"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>

                        <tr>
                            <td>Ciclos:</td>
                            <td>
                                <h:selectOneMenu  id="Ciclos"  value="#{managerHorario.b_ciclos}"  style="width:180px">
                                    <f:selectItems value="#{managerHorario.comboCiclos}" />
                                    <a4j:support event="onchange"  reRender="filtro" action="#{managerHorario.limpiarReporte}"/>
                                </h:selectOneMenu>
                            </td>
                            <td>Aulas:</td>
                            <td>
                                <h:selectOneMenu  id="Aulas"  value="#{managerHorario.b_aulas}"  style="width:180px">
                                    <f:selectItems value="#{managerHorario.comboAulasDisponibles}" />
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td width="20%"><h:outputText value="Generaciones" id="iNombreGeneracion" rendered="#{managerHorario.verGeneracion}"/></td>
                            <td width="80%" colspan="3">
                                <h:selectOneMenu value="#{managerHorario.b_generacion}" id="icbGeneracion" style="width:150px" rendered="#{managerHorario.verGeneracion}">
                                    <f:selectItems value="#{managerHorario.cboGeneraciones}"/>
                                </h:selectOneMenu>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <table>
                                    <tr>
                                        <td><h:outputText value="N° CURSOS NO APERTURADOS:" rendered="#{managerHorario.estadoAperturado}"/></td>
                                        <td><h:outputText value="#{managerHorario.n_NoAperturados}" rendered="#{managerHorario.estadoAperturado}"/></td>
                                        <td><h:graphicImage value="/Imagenes/actions/fileprint.png" style="border-width: 0px;cursor: pointer" title="Imprimir Cursos No Aperturados " rendered="#{managerHorario.estadoAperturado}">
                                                <a4j:support event="onclick" actionListener="#{managerHorario.ImprimirCursoNoApertudo}" oncomplete="#{managerHorario.oncomplete}" reRender="frmReporte,titulo"/>
                                            </h:graphicImage>
                                        </td>
                                        <td><h:outputText value="N° CURSOS APERTURADOS:" rendered="#{managerHorario.estadoAperturado}"/></td>
                                        <td><h:outputText value="#{managerHorario.n_Aperturados}" rendered="#{managerHorario.estadoAperturado}"/></td>
                                        <td><h:graphicImage value="/Imagenes/actions/fileprint.png" style="border-width: 0px;cursor: pointer" title="Imprimir Cursos Aperturados " rendered="#{managerHorario.estadoAperturado}">
                                                <a4j:support event="onclick" actionListener="#{managerHorario.ImprimirCursoApertudo}" oncomplete="#{managerHorario.oncomplete}" reRender="frmReporteApe,tituloMat"/>
                                            </h:graphicImage></td>
                                    </tr>
                                    <tr>
                                        <td><h:outputText value="N° CURSOS NO ASIGNADOS:" rendered="#{managerHorario.estadoAsignado}"/></td>
                                        <td><h:outputText value="#{managerHorario.n_NoAsignado}" rendered="#{managerHorario.estadoAsignado}"/></td>
                                        <td><h:graphicImage value="/Imagenes/actions/fileprint.png" style="border-width: 0px;cursor: pointer" title="Imprimir Cursos No Asignados " rendered="#{managerHorario.estadoAsignado}">
                                                <a4j:support event="onclick" actionListener="#{managerHorario.ImprimirCursoNoAsignado}" oncomplete="#{managerHorario.oncomplete}" reRender="frmReporteNoAsi,tituloNoAsi"/>
                                            </h:graphicImage>
                                        </td>
                                        <td><h:outputText value="N° CURSOS ASIGNADOS:" rendered="#{managerHorario.estadoAsignado}"/></td>
                                        <td><h:outputText value="#{managerHorario.n_Asignado}" rendered="#{managerHorario.estadoAsignado}"/></td>
                                        <td><h:graphicImage value="/Imagenes/actions/fileprint.png" style="border-width: 0px;cursor: pointer" title="Imprimir Cursos Asignados " rendered="#{managerHorario.estadoAsignado}">
                                                <a4j:support event="onclick" actionListener="#{managerHorario.ImprimirCursoAsignado}" oncomplete="#{managerHorario.oncomplete}" reRender="frmReporteAsi,tituloAsi"/>
                                            </h:graphicImage></td>
                                    </tr>
                                    <tr>
                                        <td><h:outputText value="CURSOS SIN DISPONIBILIDAD DE TURNO:" rendered="#{managerHorario.estadoCurDisponible}"/></td>
                                        <td><h:outputText value="#{managerHorario.n_CurDisponibilidad}" rendered="#{managerHorario.estadoCurDisponible}"/></td>
                                        <td><h:graphicImage value="/Imagenes/actions/fileprint.png" style="border-width: 0px;cursor: pointer" title="Imprimir cursos sin disponibilidad de turno " rendered="#{managerHorario.estadoCurDisponible}">
                                                <a4j:support event="onclick" actionListener="#{managerHorario.ImprimirCursosSinDisponibilidad}" oncomplete="#{managerHorario.oncomplete}" reRender="frmReporteDisCur,tituloDisCur"/>
                                            </h:graphicImage>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td><h:outputText value="N° DOCENTES NO DISPONIBLES:" rendered="#{managerHorario.estadoDocDisponible}"/></td>
                                        <td><h:outputText value="#{managerHorario.n_NoDocDisponible}" rendered="#{managerHorario.estadoDocDisponible}"/></td>
                                        <td><h:graphicImage value="/Imagenes/actions/fileprint.png" style="border-width: 0px;cursor: pointer" title="Imprimir docentes no disponibles " rendered="#{managerHorario.estadoDocDisponible}">
                                                <a4j:support event="onclick" actionListener="#{managerHorario.ImprimirNoDocDisponible}" oncomplete="#{managerHorario.oncomplete}" reRender="frmReporteNoDis,tituloNoDis"/>
                                            </h:graphicImage>
                                        </td>
                                        <td><h:outputText value="N° DOCENTES DISPONIBLES:" rendered="#{managerHorario.estadoDocDisponible}"/></td>
                                        <td><h:outputText value="#{managerHorario.n_DocDisponible}" rendered="#{managerHorario.estadoDocDisponible}"/></td>
                                        <td><h:graphicImage value="/Imagenes/actions/fileprint.png" style="border-width: 0px;cursor: pointer" title="Imprimir docentes disponibles " rendered="#{managerHorario.estadoDocDisponible}">
                                                <a4j:support event="onclick" actionListener="#{managerHorario.ImprimirDocDisponible}" oncomplete="#{managerHorario.oncomplete}" reRender="frmReporteDis,tituloDis"/>
                                            </h:graphicImage></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <f:param value="#{managerHorario.turnoDisponibilidad_id}" id="id_turno"/>
                                <f:param value="#{managerHorario.b_ciclos}" id="id_ciclos"/>
                                <f:param value="#{managerHorario.semestre_f}" id="id_semestre"/>
                                <a4j:commandButton id="btnVerPlantilla" value="Generar Horario" actionListener="#{managerHorario.generarHorario}" 
                                                   oncomplete="#{managerHorario.oncomplete}" reRender="sisevaluacion,filtro,tablaHorarioGenerado" 
                                                   rendered="#{managerHorario.b_ciclos !=0 && managerHorario.turnoGeneracion_id !=0 && mangerHorario.b_aulas !=0}"
                                                   />
                            </td>
                            <td>
                                <a4j:commandButton id="btnVerPlantilla2" value="Ver Horario Generado" actionListener="#{managerHorario.mostrarHorario}" 
                                                   oncomplete="#{managerHorario.oncomplete}" reRender="tablaHorarioGenerado,sisevaluacion,tblDocentesGeneracion,panelLunes" 
                                                   rendered="#{managerHorario.verGeneracion}"
                                                   />
                            </td>
                            <td>
                                <a4j:commandButton id="btnTrasladarVentana" value="Trasladar Horario" actionListener="#{managerHorario.trasladarHorarioVentana}" oncomplete="#{managerHorario.oncomplete}"/>
                            </td>
                            <td>

                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <rich:effect  name="hideDiv"  for="contentDiv" type="Fade" />
                                <rich:effect  name="showDiv"  for="contentDiv" type="Appear" />
                                <div id="contentDiv">
                                    <rich:panel id="sisevaluacion">
                                        <rich:dataTable id="tablaHorarioGenerado" rows="10"
                                                        cellpadding="0" cellspacing="0" width="100%" border="0"
                                                        value="#{managerHorario.tablaHorarioGenerado}" var="horario" styleClass="tblHorGen">

                                            <f:param value="#{managerHorario.turnoDisponibilidad_id}" id="ho_turno"/>
                                            <f:param value="#{managerHorario.aula_id}" id="ho_aula"/>
                                            <f:param value="#{managerHorario.docente_id}" id="ho_docente"/>
                                            <f:param value="#{horario.id_hor}" id="param_id_turno"/>
                                            <f:param value="#{horario.listaHorario}" id="ho_horarios"/>
                                            <f:param value="#{horario.ide}" id="ho_ide"/>

                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Id" styleClass="columna"/>
                                                </f:facet>
                                                <h:outputText value="#{horario.id_hor}"/>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Hora Inicio" styleClass="columna" />
                                                </f:facet>
                                                <h:outputText value="#{horario.inicio_hor}"/>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Hora Fin" styleClass="columna"/>
                                                </f:facet>
                                                <h:outputText value="#{horario.fin_hor}"/>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Lunes"  styleClass="columna"/>
                                                </f:facet>
                                                <f:param value="#{horario.indice_id_lun}" id="param_identificador_lunes"/>
                                                <h:inputHidden value="#{horario.indice_id_lun}"/>
                                                <rich:panel id="panelLunes" styleClass="panel-horario" style="#{horario.bColorCeldaGenerado_Lunes !='' ? horario.bColorCeldaGenerado_Lunes : managerHorario.bColorCeldaGenerado}">
                                                    <h:outputText value="#{horario.lunes_hor}" id="lunes" escape="false"/>

                                                </rich:panel>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Martes" styleClass="columna" />
                                                </f:facet>
                                                <f:param value="#{horario.indice_id_mar}" id="param_identificador_martes"/>
                                                <h:inputHidden value="#{horario.indice_id_mar}"/>
                                                <rich:panel id="panelMartes" styleClass="panel-horario" style="#{horario.bColorCeldaGenerado_Martes !='' ? horario.bColorCeldaGenerado_Martes : managerHorario.bColorCeldaGenerado}">
                                                    <h:outputText value="#{horario.martes_hor}" id="martes" escape="false"/>

                                                </rich:panel>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Miercoles" styleClass="columna" />
                                                </f:facet>
                                                <f:param value="#{horario.indice_id_mie}" id="param_identificador_miercoles"/>
                                                <h:inputHidden value="#{horario.indice_id_mie}"/>
                                                <rich:panel id="panelMiercoles" styleClass="panel-horario" style="#{horario.bColorCeldaGenerado_Miercoles !='' ? horario.bColorCeldaGenerado_Miercoles : managerHorario.bColorCeldaGenerado}">
                                                    <h:outputText value="#{horario.miercoles_hor}" id="miercoles" escape="false"/>

                                                </rich:panel>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Jueves" styleClass="columna"/>
                                                </f:facet>
                                                <f:param value="#{horario.indice_id_jue}" id="param_identificador_jueves"/>
                                                <h:inputHidden value="#{horario.indice_id_jue}"/>
                                                <rich:panel id="panelJueves" styleClass="panel-horario" style="#{horario.bColorCeldaGenerado_Jueves !='' ? horario.bColorCeldaGenerado_Jueves : managerHorario.bColorCeldaGenerado}">
                                                    <h:outputText value="#{horario.jueves_hor}" id="jueves" escape="false"/>
                                                </rich:panel>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Viernes"  styleClass="columna"/>
                                                </f:facet>
                                                <f:param value="#{horario.indice_id_vie}" id="param_identificador_viernes"/>
                                                <h:inputHidden value="#{horario.indice_id_vie}"/>
                                                <rich:panel id="panelViernes" styleClass="panel-horario" style="#{horario.bColorCeldaGenerado_Viernes !='' ? horario.bColorCeldaGenerado_Viernes : managerHorario.bColorCeldaGenerado}">
                                                    <h:outputText value="#{horario.viernes_hor}" id="viernes" escape="false"/>
                                                </rich:panel>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Sabado"  styleClass="columna"/>
                                                </f:facet>
                                                <rich:panel id="panelSabado" styleClass="panel-horario" style="#{horario.bColorCeldaGenerado_Sabado !='' ? horario.bColorCeldaGenerado_Sabado : managerHorario.bColorCeldaGenerado}">
                                                    <f:param value="#{horario.indice_id_sab}" id="param_identificador_sabado"/>
                                                    <h:inputHidden value="#{horario.indice_id_sab}"/>
                                                    <h:outputText value="#{horario.sabado_hor}" id="sabado" escape="false"/>
                                                </rich:panel>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Domingo"  styleClass="columna"/>
                                                </f:facet>
                                                <rich:panel id="panelDomingo" styleClass="panel-horario" style="#{horario.bColorCeldaGenerado_Domingo !='' ? horario.bColorCeldaGenerado_Domingo : managerHorario.bColorCeldaGenerado}">
                                                    <f:param value="#{horario.indice_id_dom}" id="param_identificador_domingo"/>
                                                    <h:inputHidden value="#{horario.indice_id_dom}"/>
                                                    <h:outputText value="#{horario.domingo_hor}" id="domingo" escape="false"/>
                                                </rich:panel>
                                            </h:column>
                                        </rich:dataTable>
                                    </rich:panel>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <table style=" width : 100%;">
                                    <tr>
                                        <td colspan="5" style=" width : 100%">
                                            <p align="right"/>
                                            <rich:datascroller id="barra" align="right"  for="tblDocentesGeneracion" maxPages="8"  style=" width : 100%;"/>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <rich:dataTable id="tblDocentesGeneracion" rows="5"
                                                onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                cellpadding="0" cellspacing="0"
                                                width="100%" border="0" value="#{managerHorario.lstDocDisp}" var="doc">
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Id" />
                                        </f:facet>
                                        <h:outputText value="#{doc.id}"/>
                                        <f:param value="#{doc.id}" id="p_id2"/>
                                        <f:param id="p_id" value="#{doc.id}"/>
                                        <f:param id="p_doc_detalle" value="#{doc.docNombre}"/>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="DNI" />
                                        </f:facet>
                                        <h:outputText value="#{doc.docDni}"  />
                                    </h:column>

                                    <h:column >
                                        <f:facet name="header">
                                            <h:outputText value="Nombre" />
                                        </f:facet>

                                        <h:outputText value="#{doc.docNombre}" />
                                    </h:column>

                                    <h:column >
                                        <f:facet name="header">
                                            <h:outputText value="Correo"/>
                                        </f:facet>

                                        <h:outputText value="#{doc.docCorreo}" >

                                        </h:outputText>


                                    </h:column>
                                    <h:column >
                                        <f:facet name="header">
                                            <h:outputText value="Telefono"/>
                                        </f:facet>

                                        <h:outputText value="#{doc.docTelefono}" >

                                        </h:outputText>


                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Horario"/>
                                        </f:facet>
                                        <p align="center">
                                            <a4j:commandButton image="#{doc.imagen_horario}"
                                                               title="Horario"
                                                               actionListener="#{managerHorario.cargarHorarioDisponibilidad}"
                                                               oncomplete="#{managerHorario.oncomplete}"
                                                               reRender="formHoraria_dis"/></p>
                                        </h:column>

                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Cursos" />
                                        </f:facet>
                                        <p align="center">
                                            <a4j:commandButton image="/Imagenes/actions/asignaturas.png" actionListener="#{managerHorario.buscarAsignaturas}"
                                                               oncomplete="#{managerHorario.oncomplete}" reRender="fDocente"/>
                                        </h:column>

                                    </rich:dataTable>


                            </td>
                        </tr>
                        <tr>

                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>
            <!-- listado disponibilidad docente -->
            <rich:modalPanel id="mpHorariaDisponibilidad" minWidth="600" height="400"
                             resizeable="true" zindex="4" style="overflow-y: scroll">
                <f:facet name="header">
                    <h:outputText value="Horario Disponibilidad"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png"
                                        style="cursor:pointer" title="Cerrar"
                                        onclick="Richfaces.hideModalPanel('mpHorariaDisponibilidad')"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="formHoraria_dis">
                    <table width="100%" style="font-size:12px; font-family:verdana">
                        <tr>
                            <td colspan="2" align="right">
                                <h:inputHidden value="#{managerHorario.id_doc_horario}" id="id_doc_horario_dis"/>
                            </td>
                        </tr>
                        <tr><td colspan="2"><hr width="100%"/></td></tr>

                        <tr>
                            <td class="tdLabel" style="width: 100px;"><h:outputText value="Docente"/></td>
                            <td><h:outputText value="#{managerHorario.doc_id}"/></td>
                            <td><h:outputText value="#{managerHorario.doc_detalle}"/></td>
                        </tr>

                        <tr>
                            <td width="20%">Turno:</td>
                            <td><h:selectOneMenu value="#{managerHorario.turnoDisponibilidad_id}" id="turnos_id_dis">
                                    <f:selectItems value="#{managerHorario.cmbTurnoDisponibilidad}"/>
                                    <a4j:support event="onchange" action="#{managerHorario.setearTurnoDisponibilidad}" reRender="tablaHorario_dis"/>
                                </h:selectOneMenu></td>
                        </tr>

                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                        <tr>
                            <td colspan="4" align="center" valign="top">

                                <rich:effect  name="hideDiv"  for="contentDiv" type="Fade" />
                                <rich:effect  name="showDiv"  for="contentDiv" type="Appear" />
                                <div id="contentDiv_dis">
                                    <rich:panel id="sisevaluacion_id">
                                        <rich:dataTable id="tablaHorario_dis" rows="10"
                                                        onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                        onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                        cellpadding="0" cellspacing="0" width="100%" border="0"
                                                        value="#{managerHorario.tablaHorarioDisponibilidad}" var="horario">

                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Id" />
                                                </f:facet>
                                                <h:outputText value="#{horario.id_hor_dis}"/>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Hora Inicio" />
                                                </f:facet>
                                                <h:outputText value="#{horario.inicio_hor_dis}"/>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Hora Fin" />
                                                </f:facet>
                                                <h:outputText value="#{horario.fin_hor_dis}"/>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Lunes" />
                                                </f:facet>
                                                <f:param value="#{horario.indice_id_lun_dis}" id="param_identificador_lunes_dis"/>
                                                <h:inputHidden value="#{horario.indice_id_lun_dis}"/>
                                                <rich:panel id="panelLunes_dis" style="#{horario.bColorCelda_Lunes}">
                                                    <h:outputText value="#{horario.lunes_hor_dis}" id="lunes_dis" escape="false"/>
                                                </rich:panel>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Martes" />
                                                </f:facet>
                                                <f:param value="#{horario.indice_id_mar_dis}" id="param_identificador_martes_dis"/>
                                                <h:inputHidden value="#{horario.indice_id_mar_dis}"/>
                                                <rich:panel id="panelMartes_dis" style="#{horario.bColorCelda_Martes}">
                                                    <h:outputText value="#{horario.martes_hor_dis}" id="martes_dis" escape="false"/>
                                                </rich:panel>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Miercoles" />
                                                </f:facet>
                                                <f:param value="#{horario.indice_id_mie_dis}" id="param_identificador_miercoles_dis"/>
                                                <h:inputHidden value="#{horario.indice_id_mie_dis}"/>
                                                <rich:panel id="panelMiercoles_dis" style="#{horario.bColorCelda_Miercoles}">
                                                    <h:outputText value="#{horario.miercoles_hor_dis}" id="miercoles_dis" escape="false"/>
                                                </rich:panel>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Jueves" />
                                                </f:facet>
                                                <f:param value="#{horario.indice_id_jue_dis}" id="param_identificador_jueves_dis"/>
                                                <h:inputHidden value="#{horario.indice_id_jue_dis}"/>
                                                <rich:panel id="panelJueves_dis" style="#{horario.bColorCelda_Jueves}">
                                                    <h:outputText value="#{horario.jueves_hor_dis}" id="jueves_dis" escape="false"/>
                                                </rich:panel>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Viernes" />
                                                </f:facet>
                                                <f:param value="#{horario.indice_id_vie_dis}" id="param_identificador_viernes_dis"/>
                                                <h:inputHidden value="#{horario.indice_id_vie_dis}"/>
                                                <rich:panel id="panelViernes_dis" style="#{horario.bColorCelda_Viernes}">
                                                    <h:outputText value="#{horario.viernes_hor_dis}" id="viernes_dis" escape="false"/>
                                                </rich:panel>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Sabado" />
                                                </f:facet>
                                                <rich:panel id="panelSabado_dis" style="#{horario.bColorCelda_Sabado}">
                                                    <f:param value="#{horario.indice_id_sab_dis}" id="param_identificador_sabado_dis"/>
                                                    <h:inputHidden value="#{horario.indice_id_sab_dis}"/>
                                                    <h:outputText value="#{horario.sabado_hor_dis}" id="sabado_dis" escape="false"/>
                                                </rich:panel>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Domingo" />
                                                </f:facet>
                                                <rich:panel id="panelDomingo_dis" style="#{horario.bColorCelda_Domingo}">
                                                    <f:param value="#{horario.indice_id_dom_dis}" id="param_identificador_domingo_dis"/>
                                                    <h:inputHidden value="#{horario.indice_id_dom_dis}"/>
                                                    <h:outputText value="#{horario.domingo_hor_dis}" id="domingo_dis" escape="false"/>
                                                </rich:panel>
                                            </h:column>
                                        </rich:dataTable>
                                    </rich:panel>
                                </div>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>
            <!-- listado disponibilidad por Docente -->
            <rich:modalPanel id="mpHorariaDisponibilidadPorDocente" minWidth="600" height="400"
                             resizeable="true" zindex="4" style="overflow-y: scroll">
                <f:facet name="header">
                    <h:outputText value="Horario Disponibilidad"/>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/Imagenes/actions/stop.png"
                                        style="cursor:pointer" title="Cerrar"
                                        onclick="Richfaces.hideModalPanel('mpHorariaDisponibilidadPorDocente')"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="formHoraria_disDoc">
                    <table width="100%" style="font-size:12px; font-family:verdana">
                        <tr>
                            <td colspan="2" align="right">
                                <h:inputHidden value="#{managerHorario.docente_id}" id="id_doc_horario_dis"/>
                            </td>
                        </tr>
                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                        <tr>
                            <td width="20%">Turno:</td>
                            <td><h:selectOneMenu value="#{managerHorario.turnoDisponibilidad_id}" id="turnos_id_disDoc">
                                    <f:selectItems value="#{managerHorario.cmbTurnoDisponibilidad}"/>
                                    <a4j:support event="onchange" action="#{managerHorario.setearTurnoDisponibilidad}" reRender="tablaHorario_disDoc"/>
                                </h:selectOneMenu></td>
                        </tr>

                        <tr><td colspan="2"><hr width="100%"/></td></tr>
                        <tr>
                            <td colspan="4" align="center" valign="top">

                                <rich:effect  name="hideDiv"  for="contentDiv" type="Fade" />
                                <rich:effect  name="showDiv"  for="contentDiv" type="Appear" />
                                <div id="contentDiv_dis">
                                    <rich:panel id="sisevaluacion_idDoc">
                                        <rich:dataTable id="tablaHorario_disDoc" rows="10"
                                                        onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                        onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                                        cellpadding="0" cellspacing="0" width="100%" border="0"
                                                        value="#{managerHorario.tablaHorarioDisponibilidad}" var="horario">

                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Id" />
                                                </f:facet>
                                                <h:outputText value="#{horario.id_hor_dis}"/>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Hora Inicio" />
                                                </f:facet>
                                                <h:outputText value="#{horario.inicio_hor_dis}"/>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Hora Fin" />
                                                </f:facet>
                                                <h:outputText value="#{horario.fin_hor_dis}"/>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Lunes" />
                                                </f:facet>
                                                <f:param value="#{horario.indice_id_lun_dis}" id="param_identificador_lunes_dis"/>
                                                <h:inputHidden value="#{horario.indice_id_lun_dis}"/>
                                                <rich:panel id="panelLunes_dis" style="#{horario.bColorCelda_Lunes}">
                                                    <h:outputText value="#{horario.lunes_hor_dis}" id="lunes_dis" escape="false"/>
                                                </rich:panel>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Martes" />
                                                </f:facet>
                                                <f:param value="#{horario.indice_id_mar_dis}" id="param_identificador_martes_dis"/>
                                                <h:inputHidden value="#{horario.indice_id_mar_dis}"/>
                                                <rich:panel id="panelMartes_dis" style="#{horario.bColorCelda_Martes}">
                                                    <h:outputText value="#{horario.martes_hor_dis}" id="martes_dis" escape="false"/>
                                                </rich:panel>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Miercoles" />
                                                </f:facet>
                                                <f:param value="#{horario.indice_id_mie_dis}" id="param_identificador_miercoles_dis"/>
                                                <h:inputHidden value="#{horario.indice_id_mie_dis}"/>
                                                <rich:panel id="panelMiercoles_dis" style="#{horario.bColorCelda_Miercoles}">
                                                    <h:outputText value="#{horario.miercoles_hor_dis}" id="miercoles_dis" escape="false"/>
                                                </rich:panel>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Jueves" />
                                                </f:facet>
                                                <f:param value="#{horario.indice_id_jue_dis}" id="param_identificador_jueves_dis"/>
                                                <h:inputHidden value="#{horario.indice_id_jue_dis}"/>
                                                <rich:panel id="panelJueves_dis" style="#{horario.bColorCelda_Jueves}">
                                                    <h:outputText value="#{horario.jueves_hor_dis}" id="jueves_dis" escape="false"/>
                                                </rich:panel>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Viernes" />
                                                </f:facet>
                                                <f:param value="#{horario.indice_id_vie_dis}" id="param_identificador_viernes_dis"/>
                                                <h:inputHidden value="#{horario.indice_id_vie_dis}"/>
                                                <rich:panel id="panelViernes_dis" style="#{horario.bColorCelda_Viernes}">
                                                    <h:outputText value="#{horario.viernes_hor_dis}" id="viernes_dis" escape="false"/>
                                                </rich:panel>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Sabado" />
                                                </f:facet>
                                                <rich:panel id="panelSabado_dis" style="#{horario.bColorCelda_Sabado}">
                                                    <f:param value="#{horario.indice_id_sab_dis}" id="param_identificador_sabado_dis"/>
                                                    <h:inputHidden value="#{horario.indice_id_sab_dis}"/>
                                                    <h:outputText value="#{horario.sabado_hor_dis}" id="sabado_dis" escape="false"/>
                                                </rich:panel>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Domingo" />
                                                </f:facet>
                                                <rich:panel id="panelDomingo_dis" style="#{horario.bColorCelda_Domingo}">
                                                    <f:param value="#{horario.indice_id_dom_dis}" id="param_identificador_domingo_dis"/>
                                                    <h:inputHidden value="#{horario.indice_id_dom_dis}"/>
                                                    <h:outputText value="#{horario.domingo_hor_dis}" id="domingo_dis" escape="false"/>
                                                </rich:panel>
                                            </h:column>
                                        </rich:dataTable>
                                    </rich:panel>
                                </div>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>
            <!-- listado cursos asignados a docente -->
            <rich:modalPanel id="mpDocente" minWidth="600" height="300"
                             resizeable="true" zindex="4" >
                <f:facet name="header">
                    <h:outputText value="Listado Cursos Asignados" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpDocente')" title="Cerrar" id="enviar"/>
                </f:facet>
                <h:form id="fDocente">
                    <table align="center" width="100%" >
                        <tr>
                            <td class="tdLabel" style="width: 100px;"><h:outputText value="Docente"/></td>
                            <td><h:outputText value="#{managerHorario.doc_id}"/></td>
                            <td><h:outputText value="#{managerHorario.doc_detalle}"/></td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <rich:datascroller id="barra" align="right"  for="tblCurDoc" maxPages="4"  style=" width : 100%;"/>
                                <rich:dataTable id="tblCurDoc" var="curDoc" width="100%"  rows="4"
                                                value="#{managerHorario.nLstDocenteCurso}"
                                                onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                                onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'">
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Id"/>
                                        </f:facet>
                                        <h:outputText value="#{curDoc.curdoc_id}" />
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Docente"/>
                                        </f:facet>
                                        <h:outputText value="#{curDoc.acDocente.docNombre}"/>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Curso"/>
                                        </f:facet>
                                        <h:outputText value="#{curDoc.acCurso.curNombre}" rendered="#{curDoc.view_bool}"/>
                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Especialidad"/>
                                        </f:facet>
                                        <h:outputText value="#{curDoc.acCurso.esp.espNombre}"/>

                                    </rich:column>
                                    <rich:column>
                                        <f:facet name="header">
                                            <h:outputText value="Estado Asignacion"/>
                                        </f:facet>
                                        <h:outputText value="#{curDoc.estadoAsignacion}" rendered="#{curDoc.view_bool}"/>
                                    </rich:column>
                                    <f:param id="p_hor_pos" value="#{curDoc.posicion}"/>
                                </rich:dataTable>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="mp7" width="700" height="490">
                <f:facet name="header">
                    <h:outputText id="titulo" value="#{managerHorario.tituloReporte}-#{managerHorario.n_NoAperturados}" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp7')" title="Cerrar"/>
                </f:facet>
                <h:form id="frmReporte">
                    <table>
                        <tr>
                            <td align="center" valign="middle">
                                <rich:panel id="pnlReporte" rendered="#{managerHorario.n_NoAperturados !=0?true:false}">
                                    <a4j:mediaOutput id="reporte" uriAttribute="data" style="width : 650px; height: 420px;" element="object" standby="cargando..." createContent="#{managerHorario.cargarReporteNoAperturado}" mimeType="application/pdf" value="#{managerHorario.valorRep}" />
                                </rich:panel>
                                <h:outputText id="lblReporte" value="NO SE ENCUENTRA REGISTROS" rendered="#{managerHorario.n_NoAperturados ==0?true:false}"/>
                            </td>
                        </tr>
                    </table>
                </h:form>

            </rich:modalPanel>
            <rich:modalPanel id="mp10" width="700" height="490">
                <f:facet name="header">
                    <h:outputText id="tituloNoAsi" value="#{managerHorario.tituloReporte}" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp10')" title="Cerrar"/>
                </f:facet>
                <h:form id="frmReporteNoAsi">
                    <table>
                        <tr>
                            <td align="center" valign="middle">
                                <rich:panel rendered="#{managerHorario.n_NoAsignado >0?true:false}">
                                    <a4j:mediaOutput id="reporteNoAsi" uriAttribute="data" style="width : 650px; height: 420px;" element="object" standby="cargando..." createContent="#{managerHorario.cargarReporteNoAsignado}" mimeType="application/pdf" value="#{managerHorario.valorRep}" cacheable="false" rendered="#{managerHorario.estadoAsignado}"/>
                                </rich:panel>
                                <h:outputText value="NO SE ENCUENTRA REGISTROS" rendered="#{managerHorario.n_NoAsignado ==0?true:false}"/>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="mp8" width="700" height="490" zindex="4000">
                <f:facet name="header">
                    <h:outputText id="tituloMat" value="#{managerHorario.tituloReporte}" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp8')" title="Cerrar"/>
                </f:facet>
                <h:form id="frmReporteApe">
                    <table>
                        <tr>
                            <td align="center" valign="middle">
                                <rich:panel rendered="#{managerHorario.n_Aperturados >0?true:false}">
                                    <a4j:mediaOutput id="reporteMat" uriAttribute="data" style="width : 650px; height: 420px;" element="object" standby="cargando..." createContent="#{managerHorario.cargarReporteAperturado}" mimeType="application/pdf" value="#{managerHorario.valorRep}"/>
                                </rich:panel>
                                <h:outputText value="NO SE ENCUENTRA REGISTROS" rendered="#{managerHorario.n_Aperturados ==0?true:false}"/>
                            </td>
                        </tr>
                    </table>
                </h:form>

            </rich:modalPanel>

            <rich:modalPanel id="mp9" width="700" height="490" zindex="4000">
                <f:facet name="header">
                    <h:outputText id="tituloAsi" value="#{managerHorario.tituloReporte}" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp9')" title="Cerrar"/>
                </f:facet>
                <h:form id="frmReporteAsi">
                    <table>
                        <tr>
                            <td align="center" valign="middle">
                                <rich:panel rendered="#{managerHorario.n_Asignado >0?true:false}">
                                    <a4j:mediaOutput id="reporteAsi" uriAttribute="data" style="width : 650px; height: 420px;" element="object" standby="cargando..." createContent="#{managerHorario.cargarReporteAsignado}" mimeType="application/pdf" value="#{managerHorario.valorRep}" cacheable="false"  />
                                </rich:panel>
                                <h:outputText value="NO SE ENCUENTRA REGISTROS" rendered="#{managerHorario.n_Asignado ==0?true:false}"/>
                            </td>
                        </tr>
                    </table>
                </h:form>

            </rich:modalPanel>

            <rich:modalPanel id="mp12" width="700" height="490">
                <f:facet name="header">
                    <h:outputText id="tituloNoDis" value="#{managerHorario.tituloReporte}" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp12')" title="Cerrar"/>
                </f:facet>
                <h:form id="frmReporteNoDis">
                    <table>
                        <tr>
                            <td align="center" valign="middle">
                                <rich:panel rendered="#{managerHorario.n_NoDocDisponible >0?true:false}">
                                    <a4j:mediaOutput id="reporteNoDis" uriAttribute="data" style="width : 650px; height: 420px;" element="object" standby="cargando..." createContent="#{managerHorario.cargarReporteNoDisDocente}" mimeType="application/pdf"  value="#{managerHorario.valorRep}" cacheable="false"/>
                                </rich:panel>
                                <h:outputText value="NO SE ENCUENTRA REGISTROS" rendered="#{managerHorario.n_NoDocDisponible ==0?true:false}"/>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>

            <rich:modalPanel id="mp11" width="700" height="490">
                <f:facet name="header">
                    <h:outputText id="tituloDis" value="#{managerHorario.tituloReporte}" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp11')" title="Cerrar"/>
                </f:facet>
                <h:form id="frmReporteDis">
                    <table>
                        <tr>
                            <td align="center" valign="middle">
                                <rich:panel rendered="#{managerHorario.n_DocDisponible >0?true:false}">
                                    <a4j:mediaOutput id="reporteDis" uriAttribute="data" style="width : 650px; height: 420px;" element="object" standby="cargando..." createContent="#{managerHorario.cargarReporteDisDocente}" mimeType="application/pdf" value="#{managerHorario.valorRep}" cacheable="false" rendered="#{managerHorario.estadoDocDisponible}"/>
                                </rich:panel>
                                <h:outputText value="NO SE ENCUENTRA REGISTROS" rendered="#{managerHorario.n_DocDisponible ==0?true:false}"/>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="mp13" width="700" height="490">
                <f:facet name="header">
                    <h:outputText id="tituloDisCur" value="#{managerHorario.tituloReporte}" />
                </f:facet>
                <f:facet name="controls">
                    <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp13')" title="Cerrar"/>
                </f:facet>
                <h:form id="frmReporteDisCur">
                    <table>
                        <tr>
                            <td align="center" valign="middle">
                                <rich:panel rendered="#{managerHorario.n_CurDisponibilidad >0?true:false}">
                                    <a4j:mediaOutput id="reporteDisCur" uriAttribute="data" style="width : 650px; height: 420px;" element="object" standby="cargando..." createContent="#{managerHorario.cargarReporteCursoSinDisp}" mimeType="application/pdf" value="#{managerHorario.valorRep}" cacheable="false" rendered="#{managerHorario.estadoCurDisponible}"/>
                                </rich:panel>
                                <h:outputText value="NO SE ENCUENTRA REGISTROS" rendered="#{managerHorario.n_CurDisponibilidad == 0?true:false}"/>
                            </td>
                        </tr>
                    </table>
                </h:form>
            </rich:modalPanel>


        </f:view>
    </body>
</html>
