<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link type="text/css" href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Pagos</title>	
        <script type="text/javascript" language="JavaScript" src="../script/script.js"></script>
        <script type="text/javascript">
            function printObjectsSelected(output, sgcomponent,output2,nMonto,nAdeuda){
                output.innerHTML=sgcomponent.getSelectedItems().pluck('desc');
                output2.value=sgcomponent.getSelectedItems().pluck('id');
                nMonto.value=sgcomponent.getSelectedItems().pluck('monto');
                nAdeuda.innerHTML=sgcomponent.getSelectedItems().pluck('monto')
            }
        </script>
    </head>
    <body onload="document.getElementById('form1:codigoAlumno_cod').focus()" >
        <f:view >
            <jsp:include page="../TablaSistema/Menu.jsp"></jsp:include>
            <rich:panel style=" width : 100%;" >
                <f:facet name="FACULTADES">       
                </f:facet>							
                <h:form id="form1" style="width:100%">

                    <table width="100%" style="font-size:10px; font-family:verdana" border="0" cellspacing="0" cellpadding="0" >				
                        <tr>
                            <td ><strong>PAGOS</strong></td>
                            <td align="right" colspan="6">
                            </td>
                            <td>
                                <h:inputHidden value="#{managerPago.mostrar_accion_nuevo}"/>
                                <a4j:commandButton eventsQueue="foo"  id="b" accesskey="b"
                                                   action="#{managerPago.Seleccionar}"
                                                   image="/Imagenes/actions/viewmag.png" title="Buscar"
                                                   reRender="tablaMaster,paginacion" style="width:0px"/>
                                <a4j:commandButton title="Nuevo Pago" image="/Imagenes/actions/filenew.png"
                                                   accesskey="n" rendered="#{managerPago.mostrar_accion_nuevo}"
                                                   oncomplete="javascript:Richfaces.showModalPanel('mp3',{width:600, top:80})"
                                                   actionListener="#{managerPago.Nuevo}"
                                                   reRender="nConpag,nAdeuda,nTipoCliente,nClienteFac,nRazonSocial,nTiponj,nDniRuc,
                                                   nProcedencia,nNumComprobante,nCalendar3,nMontoPago,nValorNuevo,nSaldo,nDes,nNomAlu
                                                   ,nCodAlu,nIdAlu,nTipoComprobante,nCalendar5,nNumVou,nCodAge"/>
                            </td>
                            <td><a4j:commandButton image="/Imagenes/actions/fileprint.png"
                                               title="Imprimir Listado"
                                               actionListener="#{managerPago.ImprimirListado}"
                                               oncomplete="#{managerPago.alertaCaja}" reRender="reporteCaja" />
                            </td>
                            <td><a4j:commandButton eventsQueue="foo"  id="buscar"
                                               accesskey="b" action="#{managerPago.Seleccionar}"
                                               image="/Imagenes/actions/viewmag.png" title="Buscar"
                                               reRender="tablaMaster,paginacion"/>
                            </td>

                        </tr>
                        <tr>
                            <td colspan="10"><hr width="100%" size="1"></td>
                        </tr>	
                        <tr>
                            <td>Tipo de pago:</td>
                            <td>		        
                                <h:selectOneMenu value="#{managerPago.b_id_listaPag}" style="width : 180px;">
                                    <f:selectItems value="#{managerPago.comboCatalogo_listaPagos}"/>
                                    <a4j:support event="onchange" action="#{managerPago.cambiarFecha}" reRender="renderFecha,renderFecha0,renderrender"/>
                                </h:selectOneMenu>
                            </td>
                            <td width="40%">
                            </td>
                            <td  align="right" >Nro de Comprobante:</td>
                            <td align="right"><h:inputText style="width : 180px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Ap. Paterno:</td>
                            <td >		        
                                <h:inputText value="#{managerPago.b_apPaterno}" style="width : 180px;"/>
                            </td><td width="40%">
                            </td>
                            <td align="right">Ap. Materno:</td>
                            <td align="right">
                                <h:inputText value="#{managerPago.b_apMaterno}" style="width : 180px;"/>
                            </td>
                        </tr>	
                        <tr>
                            <td >C�digo de Alumno:</td>
                            <td >		        
                                <h:inputText id="codigoAlumno_cod" value="#{managerPago.b_codigo}" style="width : 180px;"/>
                                <h:inputHidden value="#{managerPago.cli_buscar}" />
                            </td><td width="40%">
                            </td>
                            <td align="right">Concepto de Pago:</td>
                            <td align="right">
                                <h:selectOneMenu value="#{managerPago.b_id_conpag}" style="width : 180px;">
                                    <f:selectItems value="#{managerPago.comboConceptoPago}"/>					
                                </h:selectOneMenu>
                            </td>
                        </tr>	
                        <tr>
                            <td >Estado:</td>
                            <td>
                                <h:selectOneMenu id="iIdTipo" value="#{managerPago.b_id_estado}" style="width : 180px;">
                                    <f:selectItems value="#{managerPago.comboCatalogo_estado}"/>					
                                </h:selectOneMenu>
                            </td><td width="40%">
                            </td>
                            <td align="right">Semestre:</td >
                            <td align="right">
                                <h:selectOneMenu  value="#{managerPago.b_id_sem}" style="width : 180px;">
                                    <f:selectItems value="#{managerPago.comboSemestre}"/>					
                                </h:selectOneMenu>
                            </td>
                            <td>
                            </td>
                        </tr>
                        <tr>
                            <td >Facultad:</td>
                            <td>
                                <h:selectOneMenu id="FacuBuscar" value="#{managerPago.b_id_fac}" style="width : 180px;">
                                    <f:selectItems value="#{managerPago.comboFacultad_buscar}" />						
                                    <a4j:support event="onchange" reRender="EspeBuscar"/>
                                </h:selectOneMenu>
                            </td><td width="40%">
                            </td>
                            <td align="right">Especialidad:</td>
                            <td align="right">
                                <h:selectOneMenu id="EspeBuscar" value="#{managerPago.b_id_esp}" style="width : 180px;">
                                    <f:selectItems value="#{managerPago.comboEspecialidad_buscar}" />					
                                </h:selectOneMenu>
                            </td>
                            <td>
                            </td>
                        </tr>
                        <tr>
                            <td ><h:inputHidden value="#{managerPago.renderFecha}" id="renderrender"/>
                                <h:outputText value="Desde:"  id="renderFecha0"/></td>
                            <td><rich:calendar id="renderFecha" inputStyle="width : 160px;" value="#{managerPago.b_fecha1}" datePattern="yyyy-MM-dd" /></td>
                            <td width="40%"></td>
                            <td align="right">Hasta:</td>
                            <td align="right"><rich:calendar inputStyle="width : 160px;"  id="fecha_det2"  value="#{managerPago.b_fecha2}" datePattern="yyyy-MM-dd"   /></td>
                            <td></td>
                        </tr>
                    </table>
                </h:form>  
                <h:form style="width : 100%;">
                    <rich:datascroller id="paginacion" align="right"  for="tablaMaster" maxPages="8"  style=" width : 100%;"/>
                    <rich:spacer height="20px"/>
                    <rich:dataTable id="tablaMaster" rows="10" 
                                    onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                    cellpadding="0" cellspacing="0" width="100%" border="0"
                                    value="#{managerPago.listaAlumno}" var="Alu">   
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="#{managerPago.cabecera1}"/>
                            </f:facet>

                            <h:outputText value="#{Alu.c_sem}"/>
                            <h:inputHidden value="#{Alu.c_fac}" />
                            <h:inputHidden value="#{Alu.c_id_nombre}"/>
                            <f:param value="#{Alu.comprobante_oculto_alutar}" id="o0"/>
                            <f:param value="#{Alu.comprobante_oculto}" id="o1"/>
                            <f:param value="#{Alu.nro_comprobante_oculto}" id="o2"/>
                            <f:param value="#{Alu.comprobante_fecha_oculto}" id="o3"/>
                            <f:param value="#{Alu.comprobante_nombre_oculto}" id="o4"/>
                            <f:param value="#{Alu.comprobante_monto_oculto}" id="o5"/>
                            <f:param value="#{Alu.comprobante_concepto_oculto}" id="o6"/>
                            <f:param value="#{Alu.comprobante_concepto_det_oculto}" id="o7"/>
                            <h:inputHidden value="#{Alu.nro_oculto}"/>
                            <h:inputHidden value="#{Alu.c_id_con_pag}"/>	
                            <h:inputHidden value="#{Alu.c_id_con_pag_det}"/>
                            <f:param value="#{Alu.flag_ver}" id="flag"/>	
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="#{managerPago.cabecera2}"/>
                            </f:facet>
                            <h:outputText value="#{Alu.c_esp}"/>
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Cod Alumno" />
                            </f:facet>
                            <h:outputText value="#{Alu.c_codigo}"/>
                            <h:inputHidden value="#{Alu.c_dni_ruc}"/>
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Apellidos y Nombres" />
                            </f:facet>

                            <h:outputText value="#{Alu.c_nombre}"/>
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Concepto" />
                            </f:facet>

                            <h:outputText value="#{Alu.c_con_pag_det}" />
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Monto" />
                            </f:facet>

                            <h:outputText value="#{Alu.c_monto}" style="font-size:14px; font-family:verdana"/>
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="F. Registro" />
                            </f:facet>

                            <h:outputText value="#{Alu.c_fecha_pag}" />
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="#{managerPago.cabecera3}"/>
                            </f:facet>

                            <h:outputText value="#{Alu.c_fecha_pro}" />
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="#{managerPago.cabecera4}"/>
                            </f:facet>

                            <h:outputText value="#{Alu.c_estado}" />
                        </rich:column>

                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Adeuda" />
                            </f:facet>

                            <h:outputText value="#{Alu.c_adeuda}" style="font-size:14px; font-family:verdana"/>
                        </rich:column>
                        <a4j:region rendered="false">										
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Saldo" />
                                </f:facet>

                                <h:outputText value="#{Alu.c_saldo}" />
                            </rich:column>
                        </a4j:region>

                        <rich:column style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="" />
                            </f:facet>
                            <h:outputText value="#{Alu.tipo_com}" rendered="#{Alu.view_print}"/>
                            <h:graphicImage value="#{Alu.imagen}" style="border-width: 0px;cursor: pointer" rendered="#{Alu.view_detail}">
                                <a4j:support event="onclick" actionListener="#{Alu.Ver}" reRender="tablaMaster"/>										
                            </h:graphicImage>
                            <h:graphicImage value="/Imagenes/actions/carnet.gif" style="border-width: 0px;cursor: pointer" onclick="abrir('ficha2','#{Alu.c_id_nombre}',0,0);" rendered="#{Alu.mostrar_ficha}"/>

                        </rich:column>

                        <rich:column  rendered="#{Alu.view_print}" style="text-align: center;">
                            <f:facet name="header">
                                <h:outputText value="" />
                            </f:facet>
                            <h:panelGrid columns="4" style="width: 100%;">
                                <h:graphicImage value="/Imagenes/actions/pencil.png" style="border-width: 0px;cursor: pointer" height="16" width="16" rendered="#{Alu.view_print}">
                                    <a4j:support event="onclick" oncomplete="javascript:Richfaces.showModalPanel('mpx3',{width:100, top:100})" actionListener="#{managerPago.EditarComprobante}"
                                                 reRender="zId,zNombre,zMonto,zNumCom,zFechaPag,zConPag,zConPagDet,zProcedencia"/>
                                </h:graphicImage>

                                <h:commandButton  image="/Imagenes/actions/anular_.gif"  title="Anular"  actionListener="#{managerPago.anularPago}" action="#{managerPago.Seleccionar}"  onclick="javascript:return (confirm('�Esta realmente seguro de Anular el comprobante?'));"/>

                                <h:commandButton  image="/Imagenes/actions/no.gif"  title="Eliminar"  actionListener="#{managerPago.eliminarPago}" action="#{managerPago.Seleccionar}"  onclick="javascript:return (confirm('�Esta realmente seguro de Eliminar el comprobante?'));"/>

                                <a4j:commandButton image="/Imagenes/actions/fileprint.png" style="border-width: 0px;cursor: pointer" title="Imprimir" actionListener="#{managerPago.ImprimirComprobante}" oncomplete="#{managerPago.jsImpresion}" />
                            </h:panelGrid>
                        </rich:column>

                        <rich:column rendered="#{Alu.mostrar_accion_personalizar}">
                            <f:facet name="header">
                                <h:outputText value="Compromiso de Pagos" />
                            </f:facet>
                            <a4j:commandButton value="COM" onclick="Richfaces.showModalPanel('mp_com',{width:800, top:50});" />
                        </rich:column>

                        <rich:column rendered="#{Alu.mostrar_accion_personalizar}">
                            <f:facet name="header">
                                <h:outputText value="Personalizar" />
                            </f:facet>
                            <a4j:commandButton image="#{Alu.imagenP2}" oncomplete="#{Alu.disabledP2}" actionListener="#{managerPago.Personalizar}" reRender="xId,xIdNombre,xIdConPag,xSem,xFac,xEsp,xCodigo,xNombre,xConPag,xConPagDet,xSaldo,xAdeuda,iTipoCliente,iNumComprobante,iDniRuc,nValorNuevo,PerMonto,PerFechaPag,PerFechaPro"/>
                            <f:param id="id_alumno" value="#{Alu.id_alumno}" />
                            <f:param id="id_z" value="#{Alu.compag_id}" />
                            <f:param id="id_x" value="#{Alu.c_id}" />
                            <f:param id="id_nombre_x" 	value="#{Alu.c_id_nombre}" />
                            <f:param id="id_con_pag_x"  value="#{Alu.c_id_con_pag}" />
                            <f:param id="id_con_pag_det_x"  value="#{Alu.c_id_con_pag_det}" />
                            <f:param id="sem_x" 		value="#{Alu.c_sem}" />
                            <f:param id="id_sem_x"   	value="#{Alu.c_id_sem}" />
                            <f:param id="fac_x"   		value="#{Alu.c_fac}" />
                            <f:param id="id_fac_x"   	value="#{Alu.c_id_fac}" />
                            <f:param id="esp_x"   		value="#{Alu.c_esp}" />
                            <f:param id="id_esp_x"   	value="#{Alu.c_id_esp}" />
                            <f:param id="codigo_x"   	value="#{Alu.c_codigo}" />
                            <f:param id="nombre_x"   	value="#{Alu.c_nombre}" />
                            <f:param id="con_pag_x"   	value="#{Alu.c_con_pag}" />
                            <f:param id="con_pag_det_x" value="#{Alu.c_con_pag_det}" />
                            <f:param id="monto_x"   	value="#{Alu.c_monto}" />
                            <f:param id="fecha_pag_x"   value="#{Alu.c_fecha_pag}" />
                            <f:param id="fecha_pro_x"   value="#{Alu.c_fecha_pro}" />
                            <f:param id="saldo_x"   	value="#{Alu.c_saldo}" />
                            <f:param id="adeuda_x"   	value="#{Alu.c_adeuda}" />
                            <f:param id="dni_ruc_x"   	value="#{Alu.c_dni_ruc}" />	
                            <f:param id="monto_per"   	value="#{Alu.c_monto}" />
                            <f:param id="fecha_pag_per"   value="#{Alu.c_fecha_pag}" />
                            <f:param id="fecha_pro_per"   value="#{Alu.c_fecha_pro}" />	
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="" />
                            </f:facet>
                            <a4j:commandButton  image="/Imagenes/actions/mat_extemp.gif"
                                                title="Cancelar Mat. Extemporanea"
                                                oncomplete="javascript:Richfaces.showModalPanel('modal2',{width:100, top:100})"
                                                actionListener="#{managerPago.matExtemporanea}"
                                                reRender="nro_oculto2,tipoComprobante_oculto2,fec_oculto2,monto_oculto2"/>
                        </rich:column>
                        <rich:column rendered="#{Alu.mostrar_accion_pagar}">
                            <f:facet name="header">
                                <h:outputText value="" />
                            </f:facet>
                            <a4j:commandButton image="#{Alu.imagenP}" rendered="#{managerPago.view_lock}"
                                               oncomplete="#{Alu.disabledP}" actionListener="#{managerPago.Pagar}"
                                               reRender="iId,iIdNombre,iIdConPag,iSem,iFac,iEsp,iCodigo,iNombre,iConPag,iConPagDet,
                                               iMonto,iFechaPag,iFechaPro,iSaldo,iAdeuda,iTipoCliente,iRazonSocial,iTiponj,
                                               iProcedencia,iNumComprobante,iCalendar3,iMontoPago,iMatExpo,chkMatExpo,
                                               iMoraPago,iClienteFac,iRazonSocial,iTipoCliente,iNumComprobante,iDniRuc,
                                               nValorNuevo,iTipoComprobante,iNumVou,iCalendar6,iCodAge"/>

                            <f:param id="id_s" 			value="#{Alu.c_id}" />
                            <f:param id="id_nombre_s" 	value="#{Alu.c_id_nombre}" />
                            <f:param id="id_con_pag_s"  value="#{Alu.c_id_con_pag}" />
                            <f:param id="sem_s" 		value="#{Alu.c_sem}" />
                            <f:param id="id_sem_s"   	value="#{Alu.c_id_sem}" />
                            <f:param id="fac_s"   		value="#{Alu.c_fac}" />
                            <f:param id="id_fac_s"   	value="#{Alu.c_id_fac}" />
                            <f:param id="esp_s"   		value="#{Alu.c_esp}" />
                            <f:param id="id_esp_s"   	value="#{Alu.c_id_esp}" />
                            <f:param id="codigo_s"   	value="#{Alu.c_codigo}" />
                            <f:param id="nombre_s"   	value="#{Alu.c_nombre}" />
                            <f:param id="con_pag_s"   	value="#{Alu.c_con_pag}" />
                            <f:param id="con_pag_det_s" value="#{Alu.c_con_pag_det}" />
                            <f:param id="monto_s"   	value="#{Alu.c_monto}" />
                            <f:param id="fecha_pag_s"   value="#{Alu.c_fecha_pag}" />
                            <f:param id="fecha_pro_s"   value="#{Alu.c_fecha_pro}" />
                            <f:param id="saldo_s"   	value="#{Alu.c_saldo}" />
                            <f:param id="adeuda_s"   	value="#{Alu.c_adeuda}" />
                            <f:param id="dni_ruc_s"   	value="#{Alu.c_dni_ruc}" />
                        </rich:column>
                        <rich:subTable  value="#{Alu.detalle_s}" var="det" rendered="#{Alu.ver}" id="subtable"
                                        onRowMouseOver="this.style.backgroundColor='#F8F8F8'"
                                        onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'">
                            <a4j:region rendered="false">
                                <rich:column>
                                    <f:facet name="header">		
                                        <h:outputText value="Id Com" />
                                    </f:facet>
                                    <h:outputText value="#{det.id_com_det}" />
                                </rich:column>
                            </a4j:region>
                            <rich:column>
                                <f:facet name="header">		
                                    <h:outputText value="Fecha de Pago" />
                                </f:facet>
                                <h:outputText value="#{det.fecha_pago_det}" />
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">		
                                    <h:outputText value="Nombre" />
                                </f:facet>
                                <h:outputText value="#{det.nombre_pago_det}" />
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">		
                                    <h:outputText value="Tipo de Comp." />
                                </f:facet>
                                <h:outputText value="#{det.tipo_com_det}" />
                                <h:inputHidden value="#{det.id_tipo_com_det}" />
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">		
                                    <h:outputText value="N� de Comp." />
                                </f:facet>
                                <h:outputText value="#{det.num_com_det}" />
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">		
                                    <h:outputText value="Monto de Pago" />
                                </f:facet>
                                <h:outputText value="#{det.monto_det}" />
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">		
                                    <h:outputText value="Tipo Pago" />
                                </f:facet>
                                <h:outputText value="#{det.tipo_det}" />
                            </rich:column>

                            <rich:column rendered="#{Alu.mostrar_accion_pagar}" style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value=""/>
                                </f:facet>	
                                <f:param id="id_z" value="#{det.id_com_det}" />
                                <f:param id="fecha_pag_z" value="#{det.fecha_pago_det}" />
                                <f:param id="nombre_z" value="#{det.nombre_pago_det}" />
                                <f:param id="num_com_z" value="#{det.num_com_det}" />
                                <f:param id="monto_z" value="#{det.monto_det}" />
                                <f:param id="con_pag_z"	value="#{Alu.c_con_pag}" />
                                <f:param id="con_pag_det_z" value="#{Alu.c_con_pag_det}" />
                                <f:param id="id_tipo_com_z" value="#{det.id_tipo_com_det}" />

                                <f:param id="tip_com_z" value="#{det.tip_com_det}" />
                                <f:param id="num_vou_z" value="#{det.num_vou_det}" />
                                <f:param id="fec_vou_z" value="#{det.fec_vou_det}" />
                                <f:param id="cod_age_z" value="#{det.cod_age_det}" />

                                <h:panelGrid columns="4" style="width: 100%;">
                                    <h:graphicImage value="/Imagenes/actions/pencil.png" style="border-width: 0px;cursor: pointer" height="16" width="16" title="Editar">
                                        <a4j:support event="onclick" oncomplete="#{det.disabledP3}" actionListener="#{managerPago.EditarComprobante}" reRender="zId,zNombre,zMonto,zNumCom,zFechaPag,zConPag,zConPagDet,zProcedencia,zTipoComprobante,zNumVou,zFecVou,zCodAge"/>
                                    </h:graphicImage>

                                    <h:commandButton  image="/Imagenes/actions/anular_.gif"  title="Anular"  actionListener="#{managerPago.anularPago}" action="#{managerPago.Seleccionar}"  onclick="javascript:return (confirm('�Esta realmente seguro de Anular el comprobante?'));"/>

                                    <h:commandButton  image="/Imagenes/actions/no.gif"  title="Eliminar"  actionListener="#{managerPago.eliminarPago}" action="#{managerPago.Seleccionar}"  onclick="javascript:return (confirm('�Esta realmente seguro de Eliminar el comprobante?'));"/>

                                    <a4j:commandButton image="/Imagenes/actions/fileprint.png" style="border-width: 0px;cursor: pointer" title="Imprimir" actionListener="#{managerPago.ImprimirComprobante}" oncomplete="#{managerPago.jsImpresion}" />
                                </h:panelGrid>
                            </rich:column>

                            <rich:column colspan="6"/>
                        </rich:subTable>		
                    </rich:dataTable>
                </h:form>

                <rich:modalPanel  id="modal" minHeight="200" minWidth="350" height="200" width="350" zindex="2000">
                    <f:facet name="header">
                        <h:outputText value="Registro de Pago-Carnet" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('modal')" title="Cerrar"/>
                    </f:facet>

                    <h:form>
                        <table width="100%" style="font-size:10px; font-family:verdana" border="0" cellspacing="0">
                            <tr>
                                <td align="right" colspan="2">
                                    <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar" action="#{managerPago.pagarCarnet}" reRender="oculto_" oncomplete="#{managerPago.oncomplete}"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <h:outputText value="Ingrese N� Comprobante"/></td><td>
                                    <h:inputText id="oculto_" value="#{managerPago.nro_oculto}" />
                                </td>
                            </tr>
                        </table>
                    </h:form>
                </rich:modalPanel>

                <rich:modalPanel  id="mp_com" minHeight="400" minWidth="550" 
                                  height="400" width="500"
                                  zindex="2000">
                    <f:facet name="header">
                        <h:outputText value="Compromisos de Pagos" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp_com')" title="Cerrar"/>
                    </f:facet>
                    Fecha de Nuevo Compromiso:<rich:calendar  datePattern="yyyy-MM-dd"  inputStyle="width : 160px;"/><br>

                    <rich:dataTable>
                        <rich:column>
                            <f:facet name="header">
                                Fecha de Vcto
                            </f:facet>
                            <h:outputText value=""/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                Eliminar
                            </f:facet>
                            <h:outputText value="Anular"/>
                        </rich:column>
                    </rich:dataTable>

                </rich:modalPanel>

                <rich:modalPanel  id="mpx2" minHeight="400" minWidth="550" height="400" width="500" zindex="2000">




                    <f:facet name="header">
                        <h:outputText value="Registro de Pagos" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpx2')" title="Cerrar"/>
                    </f:facet>

                    <h:form>				
                        <table width="100%" style="font-size:10px; font-family:verdana" border="0" cellspacing="0">
                            <tr>
                                <td width="30%"></td>
                                <td width="50%"></td>
                                <td width="20%"></td>
                            </tr>
                            <tr >
                                <td align="right" colspan="3">
                                    <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"  actionListener="#{managerPago.Actualizar2}" action="#{managerPago.Seleccionar}" reRender="tablaMaster" oncomplete="#{managerPago.oncomplete}"/>						
                                </td>
                            </tr>
                            <tr><td colspan="3"><hr size="1"></td></tr>
                            <tr>
                                <td>Semestre:</td>
                                <td>			
                                    <h:inputHidden  id="xId"   value="#{managerPago.c_id_pago}"    />
                                    <h:inputHidden  id="xIdNombre"   value="#{managerPago.c_id_nombre_pago}"    />
                                    <h:inputHidden  id="xIdConPag"  value="#{managerPago.c_id_con_pag_pago}"    />
                                    <h:inputHidden  id="xIdConPagDet"  value="#{managerPago.c_id_con_pag_det_u}"    />						
                                    <h:outputText id="xSem"  style="width:180px;" value="#{managerPago.c_sem_pago}"    />
                                </td>
                                <td align="center" rowspan="5">
                                    <hr width="100%" size="1">SALDO A FAVOR
                                    <h:outputText id="xSaldo" value="#{managerPago.c_saldo_pago}" />
                                    <hr width="100%" size="1">ADEUDA<br>
                                    <h:outputText id="xAdeuda" value="#{managerPago.c_adeuda_pago}" />
                                    <hr width="100%" size="1">
                                </td>
                            </tr>
                            <tr>
                                <td>Facultad:</td>
                                <td>									
                                    <h:outputText  id="xFac"  style="width:180px;" value="#{managerPago.c_fac_pago}"   />	
                                </td>
                            </tr>
                            <tr>
                                <td>Especialidad:</td>
                                <td><h:outputText  id="xEsp"  style="width:180px;" value="#{managerPago.c_esp_pago}"  />	
                                </td>
                            </tr>
                            <tr>
                                <td>C�digo:</td>
                                <td><h:outputText  id="xCodigo"  style="width:90px;" value="#{managerPago.c_codigo_pago}"   />	
                                </td>
                            </tr>
                            <tr>
                                <td>Nombre:</td>
                                <td><h:outputText  id="xNombre"  style="width:180px;" value="#{managerPago.c_nombre_pago}"   />
                                </td>
                            </tr>
                            <tr>
                                <td>Concepto de Pago:</td>
                                <td>									
                                    <h:outputText  id="xConPag"  style="width:180px;" value="#{managerPago.c_con_pag_pago}"   />
                                </td>
                            </tr>
                            <tr>
                                <td>Concepto de pago Detalle:</td>
                                <td>									
                                    <h:outputText  id="xConPagDet"  style="width:180px;" value="#{managerPago.c_con_pag_det_pago}"    />
                                </td>
                            </tr>
                            <tr><td colspan="3"><hr width="100%" size="1"></td></tr>
                            <tr>
                                <td>Monto:</td>
                                <td>									
                                    <h:inputText  id="PerMonto"  style="width:180px;" value="#{managerPago.per_monto}"    />
                                </td>
                            </tr>
                            <tr>
                                <td>F. de Pago:</td>
                                <td>									
                                    <a4j:outputPanel id="PerFechaPag" layout="block">
                                        <rich:calendar  value="#{managerPago.per_fecha_pag}" datePattern="yyyy-MM-dd"   inputStyle="width : 160px;"/>
                                    </a4j:outputPanel>
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>F. de Prorroga:</td>
                                <td>									
                                    <a4j:outputPanel id="PerFechaPro" layout="block">
                                        <rich:calendar  value="#{managerPago.per_fecha_pro}" datePattern="yyyy-MM-dd"   inputStyle="width : 160px;"/>
                                    </a4j:outputPanel>
                                </td>
                                <td></td>
                            </tr>
                        </table>
                    </h:form>
                </rich:modalPanel>




                <rich:modalPanel  id="mpx3" minHeight="400" minWidth="550" height="550" width="500" zindex="2000">

                    <f:facet name="header">
                        <h:outputText value="Editar Comprobante de Pagos" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpx3')" title="Cerrar"/>
                    </f:facet>

                    <h:form>				
                        <table width="100%" style="font-size:10px; font-family:verdana" cellspacing="0" border="0" cellpadding="0">
                            <tr>
                                <td width="30%"></td>
                                <td width="50%"></td>
                                <td width="20%"></td>
                            </tr>
                            <tr >
                                <td align="right" colspan="3">
                                    <a4j:commandButton   image="/Imagenes/actions/llave.png"  title="Guardar"  actionListener="#{managerPago.Actualizar4}" oncomplete="#{managerPago.oncomplete2}"/>

                                </td>
                            </tr>
                            <tr>
                                <td colspan="3"><hr size="1"></td>
                            </tr>

                            <tr>
                                <td>Concepto de Pago:</td>
                                <td>
                                    <h:outputText  id="zConPag"  style="width:180px;" value="#{managerPago.zc_con_pag_u}"   />
                                </td>
                            </tr>

                            <tr>
                                <td>Concepto de Pago - Det:</td>
                                <td>									
                                    <h:outputText  id="zConPagDet"  style="width:180px;" value="#{managerPago.zc_con_pag_det_u}"   />
                                </td>
                            </tr>

                            <tr>
                                <td>Id Comprobante:</td>
                                <td>									
                                    <h:outputText  id="zId"  style="width:180px;" value="#{managerPago.zc_id_u}"   />
                                </td>
                            </tr>

                            <tr>
                                <td>Nombre:</td>
                                <td>
                                    <h:outputText  id="zNombre"  style="width:180px;" value="#{managerPago.zc_nombre_u}"   />
                                </td>
                            </tr>
                            <tr>
                                <td>Monto de Pago:</td>
                                <td>									
                                    <h:outputText  id="zMonto"  style="width:180px;" value="#{managerPago.zc_monto_u}"   />
                                </td>
                            </tr>
                            <tr><td colspan="3"><hr width="100%" size="1"></td></tr>
                            <tr>
                                <td>N�mero de Comprobante:</td>
                                <td>									
                                    <h:inputText  id="zNumCom"  style="width:180px;" value="#{managerPago.zc_num_com_u}"    />
                                </td>
                            </tr>
                            <tr>
                                <td>F. de Pago:</td>
                                <td>									
                                    <a4j:outputPanel id="zFechaPag" layout="block">
                                        <rich:calendar  value="#{managerPago.zc_fecha_pag_u}" datePattern="yyyy-MM-dd"   inputStyle="width : 160px;"/>
                                    </a4j:outputPanel>
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Procedencia de Pago:</td>
                                <td>									
                                    <h:selectOneMenu id="zProcedencia" value="#{managerPago.zc_id_tipo_com_u}">
                                        <f:selectItems value="#{managerPago.comboCatalogo_procedencia2}"/>					
                                    </h:selectOneMenu>
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Tipo de Comprobante:</td>
                                <td colspan="2">
                                    <h:selectOneRadio id="zTipoComprobante" value="#{managerPago.zc_tip_com_u}">
                                        <f:selectItems value="#{managerPago.comboTipoDocumento_comprobante}"/>
                                    </h:selectOneRadio>
                                </td>
                            </tr>
                            <tr>
                                <td>Numero de Voucher:</td>
                                <td>
                                    <h:inputText  id="zNumVou"  style="width:180px;" value="#{managerPago.zc_num_vou_u}"    />
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Fecha de Voucher:</td>
                                <td>
                                    <a4j:outputPanel id="zFecVou" layout="block">
                                        <rich:calendar  value="#{managerPago.zc_fec_vou_u}" datePattern="yyyy-MM-dd"   inputStyle="width : 160px;"/>
                                    </a4j:outputPanel>
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Codigo de Agencia:</td>
                                <td>
                                    <h:inputText  id="zCodAge"  style="width:180px;" value="#{managerPago.zc_cod_age_u}"    />
                                </td>
                                <td></td>
                            </tr>
                        </table>
                    </h:form>




                </rich:modalPanel>

                <rich:modalPanel  id="mpx4" minHeight="200" minWidth="300" height="200" width="500" zindex="2000">




                    <f:facet name="header">
                        <h:outputText value="Confirmacion" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpx4')" title="Cerrar"/>
                    </f:facet>

                    <h:form>				
                        <table width="100%" style="font-size:10px; font-family:verdana" cellspacing="0" border="0" cellpadding="0">
                            <tr>
                                <td width="30%"></td>
                                <td width="50%"></td>
                                <td width="20%"></td>
                            </tr>
                            <tr >
                                <td align="right" colspan="3">
                                    <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"  actionListener="#{managerPago.Actualizar3}" action="#{managerPago.Seleccionar}" reRender="tablaMaster" oncomplete="#{managerPago.oncomplete}"/>

                                </td>
                            </tr>
                            <tr>
                                <td colspan="3"><hr size="1"></td>
                            </tr>

                            <tr>
                                <td align="center" colspan="3">IMPORTANTE</td>
                            </tr>
                            <tr>
                                <td bgcolor="#FFF000" colspan="3">
                                    Ud esta a punto de modificar datos reservados,<br/>
                                    esta Ud realmente seguro que desea hacer estos cambios.<br/>
                                    Si esta seguro proceda a guardar de lo contraria cerrar ventanas.<br/>
                                    Por cuestiones de seguridad el sistema guardar� su nombre de Usuario.
                                </td>
                            </tr>
                        </table>
                    </h:form>

                </rich:modalPanel>



                <rich:modalPanel  id="mp" minHeight="450" minWidth="550" height="510" width="500" zindex="2000">

                    <f:facet name="header">
                        <h:outputText value="Registro de Pagos" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp')" title="Cerrar"/>
                    </f:facet>

                    <h:form>				
                        <table width="100%" style="font-size:10px; font-family:verdana" cellspacing="0" border="0" cellpadding="0">
                            <tr>
                                <td width="30%"></td>
                                <td width="50%"></td>
                                <td width="20%"></td>
                            </tr>
                            <tr >
                                <td align="right" colspan="3">
                                    <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"  actionListener="#{managerPago.Insertar}" action="#{managerPago.Seleccionar}" reRender="tablaMaster" oncomplete="#{managerPago.oncomplete}"/>

                                </td>
                            </tr>
                            <tr>
                                <td colspan="3"><hr size="1"></td>
                            </tr>
                            <tr>
                                <td>Semestre:</td>
                                <td>			
                                    <h:inputHidden  id="iId"   value="#{managerPago.c_id_pago}"    />
                                    <h:inputHidden  id="iIdNombre"   value="#{managerPago.c_id_nombre_pago}"    />
                                    <h:inputHidden  id="iIdConPag"  value="#{managerPago.c_id_con_pag_pago}"    />		
                                    <h:inputHidden  id="iIdConPagDet"  value="#{managerPago.c_id_con_pag_det_u}"    />					
                                    <h:outputText id="iSem"  style="width:180px;" value="#{managerPago.c_sem_pago}"    />

                                </td>
                                <td align="center" rowspan="8">
                                    <hr width="100%" size="1">
                                    SALDO A FAVOR
                                    <h:outputText id="iSaldo" value="#{managerPago.c_saldo_pago}" />
                                    <hr width="100%" size="1">
                                    ADEUDA<br>
                                    <h:outputText id="iAdeuda" value="#{managerPago.c_adeuda_pago}" />
                                    <hr width="100%" size="1">
                                    F. DE PAGO
                                    <h:outputText id="iFechaPag"  style="width:180px;" value="#{managerPago.c_fecha_pag_pago}" >
                                        <f:convertDateTime pattern="yyyy-MM-dd"/>
                                    </h:outputText> 
                                    <hr width="100%" size="1">
                                    F. DE PRO.
                                    <h:outputText id="iFechaPro"  style="width:180px;" value="#{managerPago.c_fecha_pro_pago}">
                                        <f:convertDateTime pattern="yyyy-MM-dd"/>
                                    </h:outputText>
                                    <hr width="100%" size="1">
                                </td>
                            </tr>

                            <tr>
                                <td>Facultad:</td>
                                <td>									
                                    <h:outputText  id="iFac"  style="width:180px;" value="#{managerPago.c_fac_pago}"   />

                                </td>
                            </tr>
                            <tr>
                                <td>Especialidad:</td>
                                <td>									
                                    <h:outputText  id="iEsp"  style="width:180px;" value="#{managerPago.c_esp_pago}"  />

                                </td>
                            </tr>

                            <tr>
                                <td>C�digo:</td>
                                <td>									
                                    <h:outputText  id="iCodigo"  style="width:90px;" value="#{managerPago.c_codigo_pago}"   />

                                </td>
                            </tr>
                            <tr>
                                <td>Nombre:</td>
                                <td>									
                                    <h:outputText  id="iNombre"  style="width:180px;" value="#{managerPago.c_nombre_pago}"   />
                                </td>
                            </tr>
                            <tr>
                                <td>Concepto de Pago:</td>
                                <td>									
                                    <h:outputText  id="iConPag"  style="width:180px;" value="#{managerPago.c_con_pag_pago}"   />
                                </td>
                            </tr>
                            <tr>
                                <td>Concepto de pago Detalle:</td>
                                <td>									
                                    <h:outputText  id="iConPagDet"  style="width:180px;" value="#{managerPago.c_con_pag_det_pago}"    />
                                </td>
                            </tr>
                            <tr>
                                <td>Monto:</td>
                                <td>									
                                    <h:outputText  id="iMonto"  style="width:180px;" value="#{managerPago.c_monto_pago}"   />
                                </td>
                            </tr>
                            <tr><td colspan="3"><hr width="100%" size="1"></td></tr>
                            <tr>
                                <td>Tipo de Cliente:</td>
                                <td>									
                                    <h:selectOneMenu  id="iTipoCliente" value="#{managerPago.e_id_tipo_cliente}"  style="width:180px;">
                                        <a4j:support event="onchange" actionListener="#{managerPago.SeleccionarCliente}" oncomplete="#{managerPago.selCli}"  reRender="tablaSecundaria,barraSecundaria,iRazonSocial,iTiponj,iClienteFac,iDniRuc"/>
                                        <f:selectItems value="#{managerPago.comboCatalogo_tipo_cliente}"/>					
                                    </h:selectOneMenu>
                                    <h:inputHidden id="iClienteFac"  value="#{managerPago.e_cliente_fac}" />
                                    <a4j:region id="idEste" >								
                                        <h:graphicImage value="/Imagenes/actions/user.png" style="border-width: 0px;" height="16"  title="Alumno Actual">
                                            <a4j:support event="onclick"  actionListener="#{managerPago.Asignar2}" reRender="iTiponj,iRazonSocial,iClienteFac,iTipoCliente,iDniRuc"/>
                                        </h:graphicImage>						
                                    </a4j:region>
                                    <a4j:region id="idNuevo" >								
                                        <h:graphicImage value="/Imagenes/actions/user_gris.png" style="border-width: 0px;" height="16" title="Nuevo Cliente Externo">
                                            <a4j:support event="onclick"  actionListener="#{managerPago.Asignar3}" reRender="iTiponj,iRazonSocial,iClienteFac,iTipoCliente,iDniRuc"/>
                                        </h:graphicImage>						
                                    </a4j:region>
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Razon Social:</td>
                                <td>									
                                    <h:inputText id="iRazonSocial"  style="width:180px;"  value="#{managerPago.e_razon_social}"   />
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Tipo:</td>
                                <td>									
                                    <h:selectOneMenu id="iTiponj" value="#{managerPago.e_id_tipo_nj}"  style="width:180px;">
                                        <f:selectItems value="#{managerPago.comboCatalogo_tipo_nj}"/>					
                                    </h:selectOneMenu>
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>DNI / RUC:</td>
                                <td>									
                                    <h:inputText id="iDniRuc"  style="width:180px;"  value="#{managerPago.e_dni_ruc}"   />
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Procedencia de Pago:</td>
                                <td>									
                                    <h:selectOneMenu id="iProcedencia" value="#{managerPago.e_id_procedencia}"  style="width:180px;">
                                        <f:selectItems value="#{managerPago.comboCatalogo_procedencia}"/>					
                                    </h:selectOneMenu>
                                </td>
                                <td></td>
                            </tr>

                            <tr>
                                <td>Tipo de Comprobante:</td>
                                <td colspan="2">
                                    <h:selectOneRadio id="iTipoComprobante" value="#{managerPago.e_tip_com}">
                                        <f:selectItems value="#{managerPago.comboTipoDocumento_comprobante}"/>
                                    </h:selectOneRadio>
                                </td>
                            </tr>

                            <tr>
                                <td>N. de Comp.:</td>
                                <td>		
                                    <h:selectOneMenu id="iNumComprobante0" value="#{managerPago.e_num_comprobante0}">
                                        <f:selectItems value="#{managerPago.comboCatalogo_ReciboBloque}"/>
                                        <a4j:support event="onchange" action="#{managerPago.cambiaSerieE}" reRender="iNumComprobante"/>
                                    </h:selectOneMenu>&nbsp;-&nbsp;							
                                    <h:inputText id="iNumComprobante"  style="width:110px;" value="#{managerPago.e_num_comprobante}"   />
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>F. de Comp.:</td>
                                <td>									
                                    <a4j:outputPanel id="iCalendar3" layout="block">
                                        <rich:calendar  value="#{managerPago.e_fecha_comprobante}" datePattern="yyyy-MM-dd"   direction="top-right" inputStyle="width : 160px;"/>
                                    </a4j:outputPanel>
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Monto a Pagar:</td>
                                <td>									
                                    <h:inputText id="iMontoPago" style="width:180px;" value="#{managerPago.e_monto_pago}" />
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Mora:</td>
                                <td>									
                                    <h:inputText id="iMoraPago" style="width:180px;" value="#{managerPago.e_mora_pago}" />
                                    <h:selectBooleanCheckbox value="#{managerPago.e_mora_seleccion}" style="width:20px;"/> 
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Matricula Extemporanea:</td>
                                <td>									
                                    <h:inputText id="iMatExpo" style="width:180px;" value="#{managerPago.e_mat_expo}"  readonly="true" />
                                    <h:selectBooleanCheckbox id="chkMatExpo" value="#{managerPago.e_mat_expo_seleccion}" style="width:20px;"/>
                                </td>
                                <td></td>
                            </tr>

                            <tr>
                                <td>Nro. del Voucher:</td>
                                <td>									
                                    <h:inputText id="iNumVou" style="width:180px;" value="#{managerPago.e_num_vou}" />
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>F. del Voucher:</td>
                                <td>									
                                    <a4j:outputPanel id="iCalendar6" layout="block">
                                        <rich:calendar  value="#{managerPago.e_fec_vou}" datePattern="yyyy-MM-dd"   direction="top-right" inputStyle="width : 160px;"/>
                                    </a4j:outputPanel>
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Cod. de Agencia:</td>
                                <td>									
                                    <h:inputText id="iCodAge" style="width:180px;" value="#{managerPago.e_cod_age}" />
                                </td>
                                <td></td>
                            </tr>

                        </table>
                    </h:form>
                </rich:modalPanel>

                <rich:modalPanel  id="mp2" minHeight="250" minWidth="400" height="250" width="350" zindex="2000">
                    <f:facet name="header">
                        <h:outputText value="Busqueda de Clientes" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp2')" title="Cerrar"/>
                    </f:facet>



                    <h:form style=" width : 100%;">
                        <table style=" width : 100%;" cellspacing="0" border="0">
                            <tr>
                                <td >Ap. Paterno:</td>
                                <td >		        
                                    <h:inputText id="xunox" value="#{managerPago.xuno}" style="width : 180px;"/>
                                </td>
                                <td align="right">
                                    <a4j:commandButton image="/Imagenes/actions/viewmag.png" action="#{managerPago.funxuno}" reRender="tablaSecundaria,barraSecundaria,xunox" />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="5" style=" width : 100%">
                                    <p align="right"/>
                                    <rich:datascroller id="barraSecundaria" align="right"  for="tablaSecundaria" maxPages="5"  style=" width : 100%;"/>
                                </td>
                            </tr>
                        </table>

                        <rich:dataTable id="tablaSecundaria" rows="5"
                                        onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                        onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                        cellpadding="0" cellspacing="0"
                                        width="100%" border="0" value="#{managerPago.listaCliente}" var="Cli">
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Id" />
                                </f:facet>
                                <h:outputText value="#{Cli.cli_id}"/>	

                            </rich:column>


                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Nombres" />
                                </f:facet>

                                <h:outputText value="#{Cli.cli_nombre}"/>
                                <h:inputHidden value="#{Cli.cli_razon}"/>
                                <h:inputHidden value="#{Cli.cli_saldo}"/>
                            </rich:column>


                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Cargar" />
                                </f:facet>
                                <a4j:region id="Cargar" >								
                                    <h:graphicImage value="/Imagenes/actions/button_ok.png" style="border-width: 0px;" height="16" width="16">
                                        <a4j:support event="onclick" oncomplete="Richfaces.hideModalPanel('mp2')" actionListener="#{managerPago.Asignar}" reRender="nNomAlu,nCodAlu,nIdAlu,xunox,iTiponj,iRazonSocial,iClienteFac,iDniRuc,nTiponj,nRazonSocial,nClienteFac,nDniRuc,nSaldo" />
                                    </h:graphicImage>						
                                </a4j:region>
                                <f:param id="id_s_cli" value="#{Cli.cli_id}" />
                                <f:param id="nombre_s_cli" 	value="#{Cli.cli_nombre}" />
                                <f:param id="razon_s_cli" 	value="#{Cli.cli_razon}" />
                                <f:param id="dni_ruc_s_cli" 	value="#{Cli.cli_dni_ruc}" />
                                <f:param id="saldo_cli" 	value="#{Cli.cli_saldo}" />
                                <f:param id="codigo_cli" 	value="#{Cli.cli_codigo}" />
                            </rich:column>
                        </rich:dataTable>
                    </h:form>
                </rich:modalPanel>

                <rich:modalPanel  id="mp3" minHeight="400" minWidth="550"
                                  height="450" width="540" zindex="2000">

                    <f:facet name="header">
                        <h:outputText value="Registro de Nuevos Pagos" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mp3')" title="Cerrar"/>
                    </f:facet>

                    <h:form>				
                        <table width="100%" style="font-size:10px; font-family:verdana" cellspacing="0" border="0">
                            <tr>
                                <td width="20%"></td>
                                <td width="50%"></td>
                                <td width="30%"></td>
                            </tr>
                            <tr >
                                <td align="right" colspan="3">
                                    <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar"  actionListener="#{managerPago.Insertar_nuevo}" oncomplete="#{managerPago.noncomplete}"/>

                                </td>
                            </tr>
                            <tr>
                                <td colspan="3"><hr size="1"></td>
                            </tr>

                            <tr>
                                <td>Concepto de Pago:</td>
                                <td>	
                                    <h:inputHidden  id="nValorNuevo" value="#{managerPago.n_valor_nuevo}"   />
                                    <h:inputText id="cConpag" value="#{managerPago.suggestion}" style="width:180px" />

                                    <rich:suggestionbox id="suggestion"
                                                        for="cConpag" nothingLabel="Concepto No Encontrado"
                                                        suggestionAction="#{managerPago.autocomplete}" var="con"
                                                        tokens=","
                                                        height="150" width="260" cellpadding="2"
                                                        cellspacing="2"   shadowOpacity="4" shadowDepth="4"
                                                        minChars="3" rules="none" zindex="3500"
                                                        usingSuggestObjects="true"
                                                        onobjectchange="printObjectsSelected(#{rich:element('objects')}, #{rich:component('suggestion')},#{rich:element('nConpag')},#{rich:element('nMontoPago')},#{rich:element('nAdeuda')});"
                                                        onselect=""
                                                        >
                                        <rich:column>

                                            <h:outputText value="#{con.desc}" style="font-style:bold" />
                                        </rich:column>
                                        <rich:column>

                                            <h:outputText value="#{con.monto}" style="font-style:bold" />
                                        </rich:column>
                                    </rich:suggestionbox><br>
                                    <h:outputText id="objects" style="font-weight:bold"  />
                                    <h:inputHidden id="nConpag" value="#{managerPago.n_id_conpag}"  />                                    <% /*
                                                <h:selectOneMenu id="nConpag" value="#{managerPago.n_id_conpag}" style="width:180px;">
                                                <a4j:support event="onchange" actionListener="#{managerPago.SeleccionarConceptoPago_nuevo}"  reRender="nAdeuda,nMontoPago"/>
                                                <f:selectItems value="#{managerPago.comboConceptoPago_nuevo}"/>
                                                </h:selectOneMenu> */%>
                                </td>
                                <td align="center" rowspan="3">
                                    <hr width="100%" size="1">
                                    SALDO A FAVOR
                                    <h:outputText id="nSaldo" value="#{managerPago.n_saldo}" />
                                    <hr width="100%" size="1">
                                    MONTO A PAGAR<br>
                                    <h:outputText id="nAdeuda" value="#{managerPago.n_monto}" style="font-size:14px; font-family:verdana"/>
                                    <hr width="100%" size="1">
                                </td>
                            </tr>

                            <tr>
                                <td>Tipo de Cliente:</td>
                                <td>									
                                    <h:selectOneMenu  id="nTipoCliente" value="#{managerPago.n_id_tipo_cliente}" style="width:180px;">
                                        <a4j:support event="onchange" actionListener="#{managerPago.SeleccionarCliente_nuevo}" oncomplete="#{managerPago.selCli}"  reRender="tablaSecundaria,barraSecundaria,nRazonSocial,nTiponj,nClienteFac,nDniRuc"/>
                                        <f:selectItems value="#{managerPago.comboCatalogo_tipo_cliente_nuevo}"/>					
                                    </h:selectOneMenu>
                                    <h:inputHidden id="nClienteFac" value="#{managerPago.n_cliente_fac}" />							
                                    <h:graphicImage value="/Imagenes/actions/user_gris.png" style="border-width: 0px;" height="16" title="Nuevo Cliente Externo">
                                        <a4j:support event="onclick"  actionListener="#{managerPago.Asignar3_nuevo}" reRender="nTiponj,nRazonSocial,nClienteFac,nTipoCliente,nDniRuc,nSaldo"/>
                                    </h:graphicImage>						
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Razon Social:</td>
                                <td>									
                                    <h:inputText id="nRazonSocial"  style="width:180px;"  value="#{managerPago.n_razon_social}"   />
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Tipo:</td>
                                <td>									
                                    <h:selectOneMenu id="nTiponj" value="#{managerPago.n_id_tipo_nj}"  style="width:180px;">
                                        <f:selectItems value="#{managerPago.comboCatalogo_tipo_nj_nuevo}"/>
                                    </h:selectOneMenu>
                                </td>
                                <td></td>
                            </tr>

                            <tr>
                                <td colspan="3">
                                    <table>
                                        <tr>
                                            <td>DNI / RUC:</td>
                                            <td>									
                                                <h:inputText id="nDniRuc"  style="width:180px;"  value="#{managerPago.n_dni_ruc}"   />
                                            </td>
                                            <td></td>
                                            <td>Fecha del Voucher:</td>
                                            <td>
                                                <a4j:outputPanel id="nCalendar5" layout="block">
                                                    <rich:calendar  value="#{managerPago.n_fec_vou}" datePattern="yyyy-MM-dd"    direction="top-right" inputStyle="width : 120px;"/>
                                                </a4j:outputPanel>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Cod Alumno:</td>
                                            <td>									
                                                <h:inputText id="nCodAlu"  style="height : 22px; width : 180px;"  value="#{managerPago.n_cod_alu}"   />
                                                <a4j:region id="idNuser" >								
                                                    <h:graphicImage value="/Imagenes/actions/user.png" style="border-width: 0px;" height="16"  title="Alumno Actual">
                                                        <a4j:support event="onclick"  actionListener="#{managerPago.nAsignar2}" reRender="nCodAlu,nNomAlu,tablaSecundaria,nIdAlu"/>
                                                    </h:graphicImage>						
                                                </a4j:region>
                                                <a4j:region id="idNuserBuscar" >								
                                                    <h:graphicImage value="/Imagenes/actions/viewmag.png" style="border-width: 0px;" height="16"  title="Alumno Actual">
                                                        <a4j:support event="onclick"  actionListener="#{managerPago.nSeleccionarCliente_nuevo}" oncomplete="#{managerPago.selCli}" reRender="nCodAlu,nNomAlu,nIdAlu,tablaSecundaria"/>
                                                    </h:graphicImage>						
                                                </a4j:region>
                                                <h:inputHidden id="nIdAlu"    value="#{managerPago.n_id_alu}"/>
                                            </td>
                                            <td></td>
                                            <td>Nro. Voucher:</td>
                                            <td>
                                                <h:inputText id="nNumVou"  style="width:140px;"  value="#{managerPago.n_num_vou}"   />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Nombre Alumno:</td>
                                            <td>									
                                                <h:inputText id="nNomAlu"  style="width:180px;"  value="#{managerPago.n_nom_alu}"   />
                                            </td>
                                            <td></td>
                                            <td>Cod. Agencia:</td>
                                            <td>
                                                <h:inputText id="nCodAge"  style="width:140px;"  value="#{managerPago.n_cod_age}"   />
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>Procedencia de Pago:</td>
                                <td>									
                                    <h:selectOneMenu id="nProcedencia" value="#{managerPago.n_id_procedencia}" style="width:180px;">
                                        <f:selectItems value="#{managerPago.comboCatalogo_procedencia_nuevo}"/>					
                                    </h:selectOneMenu>
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Tipo de Documento:</td>
                                <td>
                                    <h:selectOneRadio id="nTipoComprobante" value="#{managerPago.n_tip_com}">
                                        <f:selectItems value="#{managerPago.comboTipoDocumento_comprobante}"/>
                                    </h:selectOneRadio>
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>N. de Comp.:</td>
                                <td><h:selectOneMenu id="nNumComprobante0" value="#{managerPago.n_num_comprobante0}">
                                        <f:selectItems value="#{managerPago.comboCatalogo_ReciboBloque}"/>	
                                        <a4j:support event="onchange" action="#{managerPago.cambiaSerieN}" reRender="nNumComprobante"/>
                                    </h:selectOneMenu>&nbsp;-&nbsp;							
                                    <h:inputText id="nNumComprobante"  style="width:110px;" value="#{managerPago.n_num_comprobante}" />
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>F. de Comp.:</td>
                                <td><a4j:outputPanel id="nCalendar3" layout="block">
                                        <rich:calendar  value="#{managerPago.n_fecha_comprobante}" datePattern="yyyy-MM-dd"    direction="top-right" inputStyle="width : 160px;"/>
                                    </a4j:outputPanel></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Descripcion:</td>
                                <td><h:inputTextarea id="nDes"  style="width:180px;"  value="#{managerPago.n_des}"/></td>

                            </tr>
                            <tr>
                                <td>Monto a Pagar:</td>
                                <td><h:inputText id="nMontoPago" style="width:180px;" value="#{managerPago.n_monto_pago}"/>
                                </td>
                                <td></td>
                            </tr>
                        </table>
                    </h:form>
                </rich:modalPanel>
                <rich:modalPanel  id="modal2" minHeight="200" minWidth="350" height="350" width="550" zindex="2000">
                    <f:facet name="header">
                        <h:outputText value="Registro de Matricula Extemporanea" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('modal2')" title="Cerrar"/>
                    </f:facet>

                    <h:form>
                        <table width="100%" style="font-size:10px; font-family:verdana" border="0" cellspacing="0">
                            <tr>
                                <td align="right" colspan="2">
                                    <a4j:commandButton   image="/Imagenes/actions/filesave.png"  title="Guardar" action="#{managerPago.pagarMatExtemporanea}" oncomplete="#{managerPago.oncomplete}"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Ingrese N� Comprobante:
                                </td>
                                <td>
                                    <h:inputText id="nro_oculto2" value="#{managerPago.nro_oculto2}" />
                                </td>
                            </tr>
                            <tr>
                                <td>Tipo de Comprobante:</td>
                                <td>
                                    <h:selectOneRadio id="tipoComprobante_oculto2" value="#{managerPago.tip_com_oculto2}">
                                        <f:selectItems value="#{managerPago.comboTipoDocumento_comprobante}"/>
                                    </h:selectOneRadio>
                                </td>
                            </tr>
                            <tr>
                                <td>Fecha de Pago:</td>
                                <td>
                                    <a4j:outputPanel id="fec_oculto2" layout="block">
                                        <rich:calendar  value="#{managerPago.fec_oculto2}" datePattern="yyyy-MM-dd"   inputStyle="width : 160px;"/>
                                    </a4j:outputPanel>
                                </td>
                            </tr>
                            <tr>
                                <td>Monto:</td>
                                <td>									
                                    <h:inputText  id="monto_oculto2"  style="width:180px;" value="#{managerPago.monto_oculto2}"    />
                                </td>
                            </tr>
                        </table>
                    </h:form>
                </rich:modalPanel>

                <rich:modalPanel id="mpReporte" width="700" height="490">
                    <f:facet name="header">
                        <h:outputText value="Reporte de Caja" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/Imagenes/actions/stop.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('mpReporte')" title="Cerrar"/>
                    </f:facet>
                    <h:form>
                        <table>
                            <tr>
                                <td align="center" valign="middle">
                                    <rich:panel>
                                        <a4j:mediaOutput id="reporteCaja" style="width : 650px; height: 420px;" border="0" element="object" type="application/pdf" cacheable="false" rendered="true" standby="cargando..."  createContent="#{managerPago.cargarReporte}"  mimeType="application/pdf" value="#{managerPago.valorReporte}"/>
                                    </rich:panel>
                                </td>
                            </tr>
                        </table>
                    </h:form>
                </rich:modalPanel>
            </rich:panel>			
        </f:view>
    </body>	
</html>  

