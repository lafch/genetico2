/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.component.UIParameter;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import net.uch.administrativa.hibernateSpringDao.HSPagoDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.*;
import net.uch.cursoLibre.managedBeans.beans.BeanAutocompletar;
import net.uch.cursoLibre.managedBeans.beans.BeanCLConsultaPublico;
import net.uch.cursoLibre.managedBeans.beans.BeanCLMatricula;
import net.uch.cursoLibre.managedBeans.beans.BeanClAlumno;
import net.uch.cursoLibre.managedBeans.beans.MetodosCL;
import net.uch.mapping.*;
import net.uch.metodo.local.MetodosAca;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSEspecialidadDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSUbigeoDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSUsuarioDAO;
import net.uch.tablaSistema.managedBeans.bUsuario;
import net.uch.util.CommonDAO;

/**
 *
 * @author Peter
 */
public class bInformes {
    //-- FLAGS PARA VALIDAR #agregado
    //Inicialmente es false de tal forma que no me permite guardar hasta que lo indique

    private boolean wf_agregar_alumno = true;
    private boolean wf_editar_alumno = false;
    private boolean wf_ver_alumno = true;
    //-- FIN FLAGS PARA VALIDAR
    /**
     * ******BUSQUEDA AUTOCOMPLETE*************
     */
    private String w_suggestion;
    private String oncomplete;
    private String w_datos;
    private String w_codigo;
    private int w_publico_id = 0;
    /**
     * ******Inicio********
     */
    // DATOS PERSONALES
    private BeanClAlumno alumnocl_edit/*
             * = new BeanClAlumno()
             */;
    private BeanClAlumno alumnocl/*
             * = new BeanClAlumno()
             */;
    private SelectItem[] cboDepartamento;
    private SelectItem[] cboProvincia;
    private SelectItem[] cboDistrito;
    private String w_departamento = "000000";
    private String w_provincia = "000000";
    private String w_distrito = "000000";
    //INFORMACION
    private SelectItem[] cboInstituto;
    private SelectItem[] cboGradoInstruccion;
    private SelectItem[] cboOcupacion;
    private List<ClInformacionReferencial> listarReferencial = new ArrayList<ClInformacionReferencial>();
    private SelectItem[] cboArea;
    //private SelectItem[] cboModulo;
    //private SelectItem[] cboTallerAperturado;
    //private SelectItem[] cboSeccion;
    //private SelectItem[] cboHorario;
    private SelectItem[] b_especialidades;
    private SelectItem[] b_areas;
    private SelectItem[] b_modulos;
    private SelectItem[] b_cursos;
    private SelectItem[] b_taller;
    private int b_are_id;
    private int w_cur_id;
    private SelectItem[] cboMedio;
    private SelectItem[] cboMedio2;
    private SelectItem[] cboMedio3;
    private SelectItem[] cboWebCentros;
    private SelectItem[] cboPeriodicos;
//    private SelectItem[] cboMedioDetalle;
    // private String w_instituto;
    //private String w_area = "0";
    private int w_are_id = 0;
    private int w_esp_id = 0;
    //private String w_modulo = "0";
    //private String w_tallerAperturado = "0";
    // private String w_seccion = "0";
    //private String w_horario = "0";
    // private String w_medio = "0";
    //private String w_medio_det = "0";
    //private String w_obs_medio = "";
    private boolean w_procedencia = true;
    private String w_obs_consulta = "";
    private String w_obs_consulta_editar = "";
    private Date w_fecha_contacto;
    //CONSULTAS REALIZADAS
    //private int w_consulta_id;
    private int w_consulta_id_edit = 0;
    //private List<BeanClConsulta> listaConsultas/* = new ArrayList<BeanClConsulta>(0)*/;
    private List<ClPublicoConsultaDetalle> listaConsultasDet/*
             * = new ArrayList<ClPublicoConsultaDetalle>(0)
             */;
    //private List<ClHoraria> listaHoraria/* = new ArrayList<ClHoraria>(0)*/;
    private SelectItem[] cboSexo;
    private SelectItem[] cboUnidad;
    private SelectItem[] cboFormaPago;
    private SelectItem[] cboParentesco;
    private SelectItem[] cboProcedencia;
    private SelectItem[] cboTipo;
    private List<Integer> i_detalles;
    //PARA MATRICULAR
    // private ClPublicoConsulta consultaMat/* = new ClPublicoConsulta(0)*/;
    private boolean i_ver_cursos = false;
    private String i_modulo_mat = "0";
    private String i_modulo_mat_text = "";
    private int i_modulo_mat_id = 0;
    private String i_tallape_mat = "0";
    private SelectItem[] cboEstrPagMat;
    private String i_estpag_id = "0";
    private String i_mensaje_error = "";
    private boolean i_ver_message;
    private List<BeanCLMatricula> secciones;
    private double w_monto_cambiar;
    private String w_observacion_monto;
    private int bak_sec_id;
    private int bak_con_id;
    private String distrito;
    // private int w_idAperturado;
    //private String w_descripcionTaller;
    private String w_sugerencia;
    private int w_mod_id = 0;
    private int w_tall_id = 0;
    private int icurId = 0;
    private SelectItem[] cboInstitucion;
    private String iInstitucionId;
    private SelectItem[] cboCursoCons;
    private List<String> lstMedSelec = new ArrayList<String>();
    private List<String> lstMedSelecC2 = new ArrayList<String>();
    private List<String> lstMedSelecC3 = new ArrayList<String>();
    private List<String> lstWebCentrosSelec = new ArrayList<String>();
    private List<String> lstPeriodicosSelec = new ArrayList<String>();

    public List<String> getLstMedSelec() {
        return lstMedSelec;
    }

    public void setLstMedSelec( List<String> lstMedSelec ) {
        this.lstMedSelec = lstMedSelec;
    }

    public List<String> getLstMedSelecC2() {
        return lstMedSelecC2;
    }

    public void setLstMedSelecC2( List<String> lstMedSelecC2 ) {
        this.lstMedSelecC2 = lstMedSelecC2;
    }

    public List<String> getLstMedSelecC3() {
        return lstMedSelecC3;
    }

    public void setLstMedSelecC3( List<String> lstMedSelecC3 ) {
        this.lstMedSelecC3 = lstMedSelecC3;
    }

    public List<String> getLstWebCentrosSelec() {
        return lstWebCentrosSelec;
    }

    public void setLstWebCentrosSelec( List<String> lstWebCentrosSelec ) {
        this.lstWebCentrosSelec = lstWebCentrosSelec;
    }

    public List<String> getLstPeriodicosSelec() {
        return lstPeriodicosSelec;
    }

    public void setLstPeriodicosSelec( List<String> lstPeriodicosSelec ) {
        this.lstPeriodicosSelec = lstPeriodicosSelec;
    }

    public SelectItem[] getCboInstitucion() {
        try {

            List lstInstituciones = CommonDAO.getTbCatalogoDAO().seleccionarCatalogo( "078" );
            if ( lstInstituciones.size() > 0 ) {
                cboInstitucion = new SelectItem[ lstInstituciones.size() + 1 ];
                cboInstitucion[0] = new SelectItem( 0, "[Seleccione]" );
                for ( int i = 0; i < lstInstituciones.size(); i++ ) {
                    cboInstitucion[i + 1] = new SelectItem( ( (TbCatalogo)lstInstituciones.get( i ) ).getCatCodigoGrupo() + ( (TbCatalogo)lstInstituciones.get( i ) ).getCatCodigoElemento(), ( (TbCatalogo)lstInstituciones.get( i ) ).getCatDescripcionElemento() );
                }
            } else {
                cboInstitucion = new SelectItem[ 1 ];
                cboInstitucion[0] = new SelectItem( 0, "[Seleccione]" );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return cboInstitucion;
    }

    public void setCboInstitucion( SelectItem[] cboInstitucion ) {
        this.cboInstitucion = cboInstitucion;
    }

    //SETTER SY GETTERS
    public SelectItem[] getCboCursoCons() {
        try {
            //System.out.println( "cbo_mod_id: " + this.cbo_mod_id );
            //System.out.println( "w_mod_id: " + this.w_mod_id );
            if ( this.w_mod_id > 0 ) { //MOSTRAR TODOS LOS CURSOS DEL MODULO

                HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
                List<ClArbolAcademico> cursos = dao.seleccionarCursos( this.w_mod_id );
                cboCursoCons = new SelectItem[ cursos.size() + 1 ];
                cboCursoCons[0] = new SelectItem( 0, "[Seleccione]" );
                for ( int i = 0; i < cursos.size(); i++ ) {
                    ClArbolAcademico a = cursos.get( i );
                    cboCursoCons[i + 1] = new SelectItem( a.getArbId(), a.getArbDescripcion() );
                }
            } else {
                cboCursoCons = new SelectItem[ 1 ];
                cboCursoCons[0] = new SelectItem( 0, "[Seleccione]" );
            }
        } catch ( Exception e ) {
            System.err.println( "Error al cargar areas" );
        }

        return cboCursoCons;
    }

    public void setCboCursoCons( SelectItem[] cboCursoCons ) {
        this.cboCursoCons = cboCursoCons;
    }

    public String getiInstitucionId() {
        return iInstitucionId;
    }

    public void setiInstitucionId( String iInstitucionId ) {
        this.iInstitucionId = iInstitucionId;
    }

    public String getDistrito() {
        return distrito;
    }

    public int getIcurId() {
        return icurId;
    }

    public void setIcurId( int icurId ) {
        this.icurId = icurId;
    }

    public void setDistrito( String distrito ) {
        this.distrito = distrito;
    }

    public int getW_mod_id() {
        return w_mod_id;
    }

    public void setW_mod_id( int w_mod_id ) {
        this.w_mod_id = w_mod_id;
    }
    private BeanAutocompletar info = new BeanAutocompletar();

    /*
     * variables plantillas
     */
    private SelectItem[] cboPlantilla;
    // private int w_pla_id;
    private List<ClPlantillaHorarioDet> listaDetallePlantilla = new ArrayList<ClPlantillaHorarioDet>();
    /*
     * fin plantillas
     */
    private int w_pla_id = 1;
    /*
     *
     */
    private ClConsultaPublico consultaPublico = new ClConsultaPublico();
    private ClInformacionReferencial informacionReferencial = new ClInformacionReferencial();
    private List<BeanCLConsultaPublico> listabeanConsultaPublico = new ArrayList<BeanCLConsultaPublico>();
    private int cbo_mod_id = 0;
    private List<ClEstructuraPagosDetalle> listaEstructuraDetalle = new ArrayList<ClEstructuraPagosDetalle>();

    /*
     * variables para ocultar el pago
     */
    private String w_textoPago = "Mostrar detalle de pagos";
    private String w_imgPago = "/Imagenes/actions/down.png";
    private String w_ocultar_estructura = "false";
    private String w_ocultar_boton = "true";
    /*
     * fin oculatr
     */
    private int w_diaCobrar = 30;
    private Date fecha_inicio = new Date();
    private String w_mensaje_actual;
    private int w_consulta_id = 0;
    private SelectItem[] cboLocal;
    private String habilitar = "true";
    private String w_visualizar = "true";
    private int cur_id = 0;
    private int tall_id = 0;

    public int getCur_id() {
        return cur_id;
    }

    public void setCur_id( int cur_id ) {
        this.cur_id = cur_id;
    }

    public int getTall_id() {
        return tall_id;
    }

    public void setTall_id( int tall_id ) {
        this.tall_id = tall_id;
    }

    public String getW_visualizar() {
        return w_visualizar;
    }

    public void setW_visualizar( String w_visualizar ) {
        this.w_visualizar = w_visualizar;
    }

    public String getW_mensaje_actual() {
        return w_mensaje_actual;
    }

    public void setW_mensaje_actual( String w_mensaje_actual ) {
        this.w_mensaje_actual = w_mensaje_actual;
    }

    public String getHabilitar() {
        return habilitar;
    }

    public void setHabilitar( String habilitar ) {
        this.habilitar = habilitar;
    }

    public SelectItem[] getCboLocal() throws SQLException {
        MetodosAca metodo = new MetodosAca();
        cboLocal = metodo.listarSedes( "Seleccione" );
        return cboLocal;
    }

    public void setCboLocal( SelectItem[] cboLocal ) {
        this.cboLocal = cboLocal;
    }

    public int getW_consulta_id() {
        return w_consulta_id;
    }

    public void setW_consulta_id( int w_consulta_id ) {
        this.w_consulta_id = w_consulta_id;
    }

    public int getW_diaCobrar() {
        return w_diaCobrar;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio( Date fecha_inicio ) {
        this.fecha_inicio = fecha_inicio;
    }

    public void setW_diaCobrar( int w_diaCobrar ) {
        this.w_diaCobrar = w_diaCobrar;
    }

    public String getW_ocultar_boton() {
        return w_ocultar_boton;
    }

    public void setW_ocultar_boton( String w_ocultar_boton ) {
        this.w_ocultar_boton = w_ocultar_boton;
    }

    public String getW_imgPago() {
        return w_imgPago;
    }

    public void setW_imgPago( String w_imgPago ) {
        this.w_imgPago = w_imgPago;
    }

    public String getW_ocultar_estructura() {
        return w_ocultar_estructura;
    }

    public void setW_ocultar_estructura( String w_ocultar_estructura ) {
        this.w_ocultar_estructura = w_ocultar_estructura;
    }

    public String getW_textoPago() {
        return w_textoPago;
    }

    public void setW_textoPago( String w_textoPago ) {
        this.w_textoPago = w_textoPago;
    }

    public List<ClEstructuraPagosDetalle> getListaEstructuraDetalle() {


        return listaEstructuraDetalle;
    }

    public void setListaEstructuraDetalle( List<ClEstructuraPagosDetalle> listaEstructuraDetalle ) {
        this.listaEstructuraDetalle = listaEstructuraDetalle;
    }

    public int getW_pla_id() {
        return w_pla_id;
    }

    public void setW_pla_id( int w_pla_id ) {
        this.w_pla_id = w_pla_id;
    }

    public int getCbo_mod_id() {
        return cbo_mod_id;
    }

    public void setCbo_mod_id( int cbo_mod_id ) {
        this.cbo_mod_id = cbo_mod_id;
    }

    public List<BeanCLConsultaPublico> getListabeanConsultaPublico() {
        return listabeanConsultaPublico;
    }

    public void setListabeanConsultaPublico( List<BeanCLConsultaPublico> listabeanConsultaPublico ) {
        this.listabeanConsultaPublico = listabeanConsultaPublico;
    }

    public ClConsultaPublico getConsultaPublico() {
        return consultaPublico;
    }

    public void setConsultaPublico( ClConsultaPublico consultaPublico ) {
        this.consultaPublico = consultaPublico;
    }

    public ClInformacionReferencial getInformacionReferencial() {
        return informacionReferencial;
    }

    public void setInformacionReferencial( ClInformacionReferencial informacionReferencial ) {
        this.informacionReferencial = informacionReferencial;
    }

    public SelectItem[] getCboPlantilla() {

        HSPlantillaHorarioCLDAO dao = CommonDAO.getClPlantillaHorarioDAO();
        List<ClPlantillaHorario> listaP = dao.listarPlantilla();
        //System.out.println( "Informes -> cntidaad de la lista -> " + listaP.size() );
        this.cboPlantilla = new SelectItem[ listaP.size() + 1 ];
        this.cboPlantilla[0] = new SelectItem( 0, "[Seleccione]" );
        for ( int i = 0; i < ( cboPlantilla.length - 1 ); i++ ) {
            this.cboPlantilla[i + 1] = new SelectItem( listaP.get( i ).getPlaId(), listaP.get( i ).getPlaDescripcion() );
        }
        return this.cboPlantilla;
    }

    public void setCboPlantilla( SelectItem[] cboPlantilla ) {
        this.cboPlantilla = cboPlantilla;
    }

    public List<ClPlantillaHorarioDet> getListaDetallePlantilla() {
        HSPlantillaHorarioCLDAO dao = CommonDAO.getClPlantillaHorarioDAO();
        //System.out.println("el valor de la plantilla -> "+this.getConsultaPublico().getPlaId());
        try {


            List<ClPlantillaHorarioDet> listaDeta = dao.listarDetallePlantilla( this.getConsultaPublico().getPlaId() );

            if ( listaDeta != null ) {
                if ( this.getConsultaPublico().getPlaId() == 0 ) {
                    //System.out.println( "entro aqui valor 0" );
                    this.setListaDetallePlantilla( new ArrayList<ClPlantillaHorarioDet>() );
                } else {
                    //System.out.println( "entro aqui diferente 0" );
                    HSCatalogoDAO daoC = CommonDAO.getTbCatalogoDAO();
                    for ( int i = 0; i < listaDeta.size(); i++ ) {
                        listaDeta.get( i ).setPladetDia( daoC.seleccionarDescripcion( listaDeta.get( i ).getPladetDia() ) );
                    }
                    this.setListaDetallePlantilla( listaDeta );
                }
                this.getConsultaPublico().setAcLocal( new AcLocal() );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            System.out.println( "error -> " + e );
        }

        return listaDetallePlantilla;
    }

    public void setListaDetallePlantilla( List<ClPlantillaHorarioDet> listaDetallePlantilla ) {
        this.listaDetallePlantilla = listaDetallePlantilla;
    }
    /*
     * public int getW_pla_id() { return w_pla_id; }
     *
     * public void setW_pla_id(int w_pla_id) { this.w_pla_id = w_pla_id; }
     */

    public BeanAutocompletar getInfo() {
        return info;
    }

    public void setInfo( BeanAutocompletar info ) {
        this.info = info;
    }

    public String getW_sugerencia() {
        return w_sugerencia;
    }

    public void setW_sugerencia( String w_sugerencia ) {
        this.w_sugerencia = w_sugerencia;
    }

    /*
     * public String getW_descripcionTaller() { return w_descripcionTaller; }
     *
     * public void setW_descripcionTaller(String w_descripcionTaller) {
     * this.w_descripcionTaller = w_descripcionTaller; }
     *
     * public int getW_idAperturado() { return w_idAperturado; }
     *
     * public void setW_idAperturado(int w_idAperturado) { this.w_idAperturado =
     * w_idAperturado; }
     */
    public int getBak_con_id() {
        return bak_con_id;
    }

    public void setBak_con_id( int bak_con_id ) {
        this.bak_con_id = bak_con_id;
    }

    public int getBak_sec_id() {
        return bak_sec_id;
    }

    public void setBak_sec_id( int bak_sec_id ) {
        this.bak_sec_id = bak_sec_id;
    }

    public double getW_monto_cambiar() {
        return w_monto_cambiar;
    }

    public void setW_monto_cambiar( double w_monto_cambiar ) {
        this.w_monto_cambiar = w_monto_cambiar;
    }

    public String getW_observacion_monto() {
        return w_observacion_monto;
    }

    public void setW_observacion_monto( String w_observacion_monto ) {
        this.w_observacion_monto = w_observacion_monto;
    }

    //FIN PARA MATRICULAR
    public String getW_suggestion() {
        return w_suggestion;
    }

    public void setW_suggestion( String w_suggestion ) {
        this.w_suggestion = w_suggestion;
    }

    public List<ClPublicoConsultaDetalle> getListaConsultasDet() {
        return listaConsultasDet;
    }

    public void setListaConsultasDet( List<ClPublicoConsultaDetalle> listaConsultasDet ) {
        this.listaConsultasDet = listaConsultasDet;
    }

    public int getW_consulta_id_edit() {
        return w_consulta_id_edit;
    }

    public void setW_consulta_id_edit( int w_consulta_id_edit ) {
        this.w_consulta_id_edit = w_consulta_id_edit;
    }

    public SelectItem[] getCboFormaPago() {
        MetodosCL metodos = new MetodosCL();
        cboFormaPago = metodos.listarCatalogoGrupo( "041" );
        return cboFormaPago;
    }

    public void setCboFormaPago( SelectItem[] cboFormaPago ) {
        this.cboFormaPago = cboFormaPago;
    }

    public SelectItem[] getCboParentesco() {
        MetodosCL metodos = new MetodosCL();
        cboParentesco = metodos.listarCatalogoGrupo( "040" );
        return cboParentesco;
    }

    public void setCboParentesco( SelectItem[] cboParentesco ) {
        this.cboParentesco = cboParentesco;
    }

    public SelectItem[] getCboProcedencia() {
        MetodosCL metodos = new MetodosCL();
        cboProcedencia = metodos.listarCatalogoGrupo( "023" );
        return cboProcedencia;
    }

    public void setCboProcedencia( SelectItem[] cboProcedencia ) {
        this.cboProcedencia = cboProcedencia;
    }

    public SelectItem[] getCboSexo() {
        MetodosCL metodos = new MetodosCL();
        cboSexo = metodos.listarCatalogoGrupo( "004" );
        return cboSexo;
    }

    public void setCboSexo( SelectItem[] cboSexo ) {
        this.cboSexo = cboSexo;
    }

    public SelectItem[] getCboTipo() {
        MetodosCL metodos = new MetodosCL();
        cboTipo = metodos.listarCatalogoGrupo( "057" );
        return cboTipo;
    }

    public void setCboTipo( SelectItem[] cboTipo ) {
        this.cboTipo = cboTipo;
    }

    public SelectItem[] getCboUnidad() {
        MetodosCL metodos = new MetodosCL();
        cboUnidad = metodos.listarCatalogoGrupo( "042" );
        return cboUnidad;
    }

    public void setCboUnidad( SelectItem[] cboUnidad ) {
        this.cboUnidad = cboUnidad;
    }

    /*public SelectItem[] getCbosexo() {
     MetodosCL metodos = new MetodosCL();
     return cbosexo;
     }

     public void setCbosexo( SelectItem[] cbosexo ) {
     this.cbosexo = cbosexo;
     }*/
    /*
     * Fin
     */
    public BeanClAlumno getAlumnocl() {
        if ( this.alumnocl == null ) {
            this.alumnocl = new BeanClAlumno();
        }
        return this.alumnocl;
    }

    public void setAlumnocl( BeanClAlumno alumnocl ) {
        this.alumnocl = alumnocl;
    }

    public BeanClAlumno getAlumnocl_edit() {
        if ( this.alumnocl_edit == null ) {
            this.alumnocl_edit = new BeanClAlumno();
        }
        return this.alumnocl_edit;
    }

    public void setAlumnocl_edit( BeanClAlumno alumnocl_edit ) {
        this.alumnocl_edit = alumnocl_edit;
    }

    //SETTERS Y GETTERS
    public boolean isWf_agregar_alumno() {
        return wf_agregar_alumno;
    }

    public boolean isWf_editar_alumno() {
        return wf_editar_alumno;
    }

    public boolean isWf_ver_alumno() {
        return wf_ver_alumno;
    }

    /*
     * public String getW_medio() { return w_medio; }
     *
     * public String getW_medio_det() { return w_medio_det; }
     *
     * public String getW_obs_medio() { return w_obs_medio; }
     */
    public String getW_obs_consulta() {
        return w_obs_consulta;
    }

    public String getW_obs_consulta_editar() {
        return w_obs_consulta_editar;
    }

    public boolean isW_procedencia() {
        return w_procedencia;
    }

    public Date getW_fecha_contacto() {
        return w_fecha_contacto;
    }

    public String getW_codigo() {
        return w_codigo;
    }

    public String getW_datos() {
        return w_datos;
    }

    public int getW_publico_id() {
        return w_publico_id;
    }

    public String getW_departamento() {
        return w_departamento;
    }

    public String getW_provincia() {
        return w_provincia;
    }

    public String getW_distrito() {
        return w_distrito;
    }

    /*
     * public String getW_instituto() { return this.w_instituto; }
     *
     * public String getW_area() { return this.w_area; }
     *
     * public String getW_modulo() { return this.w_modulo; }
     */

    /*
     * public String getW_tallerAperturado() { return w_tallerAperturado; }
     */

    /*
     * public String getW_seccion() { return w_seccion; }
     *
     * public String getW_horario() { return w_horario; }
     *
     * public int getW_consulta_id() { return w_consulta_id; }
     *
     * /*public List<BeanClConsulta> getListaConsultas() { return
     * this.listaConsultas; }
     *
     * public List<ClHoraria> getListaHoraria() { traerHorario(); return
     * listaHoraria; }
     */
    public void setWf_agregar_alumno( boolean wf_agregar_alumno ) {
        this.wf_agregar_alumno = wf_agregar_alumno;
    }

    public void setWf_editar_alumno( boolean wf_editar_alumno ) {
        this.wf_editar_alumno = wf_editar_alumno;
    }

    public void setWf_ver_alumno( boolean wf_ver_alumno ) {
        this.wf_ver_alumno = wf_ver_alumno;
    }

    /*
     * public void setW_medio(String w_medio) { this.w_medio = w_medio; }
     *
     * public void setW_medio_det(String w_medio_det) { this.w_medio_det =
     * w_medio_det; }
     *
     * public void setW_obs_medio(String w_obs_medio) { this.w_obs_medio =
     * w_obs_medio; }
     */
    public void setW_obs_consulta( String w_obs_consulta ) {
        this.w_obs_consulta = w_obs_consulta;
    }

    public void setW_obs_consulta_editar( String w_obs_consulta_editar ) {
        this.w_obs_consulta_editar = w_obs_consulta_editar;
    }

    public void setW_procedencia( boolean w_procedencia ) {
        this.w_procedencia = w_procedencia;
    }

    public void setW_fecha_contacto( Date w_fecha_contacto ) {
        this.w_fecha_contacto = w_fecha_contacto;
    }

    public void setW_codigo( String w_codigo ) {
        this.w_codigo = w_codigo;
    }

    public void setW_datos( String w_datos ) {
        this.w_datos = w_datos;
    }

    public void setW_publico_id( int w_publico_id ) {
        this.w_publico_id = w_publico_id;
    }

    public void setW_departamento( String w_departamento ) {
        this.w_departamento = w_departamento;
    }

    public void setW_provincia( String w_provincia ) {
        this.w_provincia = w_provincia;
    }

    public void setW_distrito( String w_distrito ) {
        this.w_distrito = w_distrito;
    }

    /*
     * public void setW_instituto(String w_instituto) { this.w_instituto =
     * w_instituto; }
     *
     * public void setW_area(String w_area) { this.w_area = w_area; }
     *
     * public void setW_modulo(String w_modulo) { this.w_modulo = w_modulo; }
     *
     * public void setW_tallerAperturado(String w_tallerAperturado) {
     * this.w_tallerAperturado = w_tallerAperturado; }
     *
     * public void setW_seccion(String w_seccion) { this.w_seccion = w_seccion;
     * }
     *
     * public void setW_horario(String w_horario) { this.w_horario = w_horario;
     * }
     *
     * public void setW_consulta_id(int w_consulta_id) { this.w_consulta_id =
     * w_consulta_id; }
     *
     * /*public void setListaConsultas(List<BeanClConsulta> listaConsultas) {
     * this.listaConsultas = listaConsultas; }
     *
     * public void setListaHoraria(List<ClHoraria> listaHoraria) {
     * this.listaHoraria = listaHoraria; }
     */

    /*
     * SETTERS Y GETTERS DE DETALLE SECCIONES
     */
    public String getI_mensaje_error() {
        return i_mensaje_error;
    }

    public void setI_mensaje_error( String i_mensaje_error ) {
        this.i_mensaje_error = i_mensaje_error;
    }

    public boolean isI_ver_message() {
        return i_ver_message;
    }

    public void setI_ver_message( boolean i_ver_message ) {
        this.i_ver_message = i_ver_message;
    }

    public boolean isI_ver_cursos() {
        return i_ver_cursos;
    }

    public void setI_ver_cursos( boolean i_ver_cursos ) {
        this.i_ver_cursos = i_ver_cursos;
    }

    /*
     * FIN SETTERS Y GETTERS DE DETALLE SECCIONES
     */
    public SelectItem[] getCboDepartamento() {
        try {
            HSUbigeoDAO dao = CommonDAO.getUbigeoDAO();
            List listaDe = dao.seleccionarDepartamento();
            cboDepartamento = new SelectItem[ listaDe.size() + 1 ];
            cboDepartamento[0] = new SelectItem( "000000", "[Seleccione]" );
            for ( int i = 0; i < ( cboDepartamento.length - 1 ); i++ ) {
                cboDepartamento[i + 1] = new SelectItem( ( (TbDistrito)listaDe.get( i ) ).getId(), ( (TbDistrito)listaDe.get( i ) ).getDisDes() );
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return cboDepartamento;
    }

    public SelectItem[] getCboProvincia() {
        try {
            HSUbigeoDAO dao = CommonDAO.getUbigeoDAO();
            List<TbDistrito> listaPro = dao.seleccionarProvincia( this.getW_departamento() );
            if ( listaPro.size() != 0 ) {
                this.cboProvincia = new SelectItem[ listaPro.size() + 1 ];
                this.cboProvincia[0] = new SelectItem( "000000", "[Seleccione]" );
                for ( int i = 0; i < ( this.cboProvincia.length - 1 ); i++ ) {
                    this.cboProvincia[i + 1] = new SelectItem( listaPro.get( i ).getId(), listaPro.get( i ).getDisDes() );
                }
            } else {
                this.cboProvincia = new SelectItem[ 1 ];
                this.cboProvincia[0] = new SelectItem( "000000", "[Seleccione]" );
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return this.cboProvincia;
    }

    public SelectItem[] getCboDistrito() {
        try {
            HSUbigeoDAO dao = CommonDAO.getUbigeoDAO();
            List<TbDistrito> listaDistri = dao.seleccionarDistrito( this.getW_departamento(), this.getW_provincia() );
            if ( listaDistri.size() != 0 ) {
                cboDistrito = new SelectItem[ listaDistri.size() + 1 ];
                cboDistrito[0] = new SelectItem( "000000", "[Seleccione]" );
                for ( int i = 0; i < ( cboDistrito.length - 1 ); i++ ) {
                    cboDistrito[i + 1] = new SelectItem( listaDistri.get( i ).getId(), listaDistri.get( i ).getDisDes() );
                }
            } else {
                cboDistrito = new SelectItem[ 1 ];
                cboDistrito[0] = new SelectItem( "000000", "[Seleccione]" );
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return cboDistrito;
    }

    public SelectItem[] getCboInstituto() {
        try {
            HSCatalogoDAO dao = CommonDAO.getTbCatalogoDAO();
            List listaInst = dao.seleccionarGrupo( "061" );
            cboInstituto = new SelectItem[ listaInst.size() + 1 ];
            cboInstituto[0] = new SelectItem( "000000", "[Seleccione]" );
            for ( int i = 0; i < ( cboInstituto.length - 1 ); i++ ) {
                cboInstituto[i + 1] = new SelectItem( ( (TbCatalogo)listaInst.get( i ) ).getId(), ( (TbCatalogo)listaInst.get( i ) ).getCatDescripcionElemento() );
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return cboInstituto;
    }

    public SelectItem[] getCboGradoInstruccion() {
        try {
            HSCatalogoDAO dao = CommonDAO.getTbCatalogoDAO();
            List listaInst = dao.seleccionarGrupo( "062" );
            cboGradoInstruccion = new SelectItem[ listaInst.size() + 1 ];
            cboGradoInstruccion[0] = new SelectItem( "000000", "[Seleccione]" );
            for ( int i = 0; i < ( cboGradoInstruccion.length - 1 ); i++ ) {
//                cboGradoInstruccion[i + 1] = new SelectItem( ((TbCatalogo) listaInst.get( i )).getId(), ((TbCatalogo) listaInst.get( i )).getCatDescripcionElemento() );
                cboGradoInstruccion[i + 1] = new SelectItem( ( (TbCatalogo)listaInst.get( i ) ).getCatCodigoGrupo() + ( (TbCatalogo)listaInst.get( i ) ).getCatCodigoElemento(), ( (TbCatalogo)listaInst.get( i ) ).getCatDescripcionElemento() );
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return cboGradoInstruccion;
    }

    public SelectItem[] getCboOcupacion() {
        try {
            HSCatalogoDAO dao = CommonDAO.getTbCatalogoDAO();
            List listaInst = dao.seleccionarGrupo( "091" );
            cboOcupacion = new SelectItem[ listaInst.size() + 1 ];
            cboOcupacion[0] = new SelectItem( "000000", "[Seleccione]" );
            for ( int i = 0; i < ( cboOcupacion.length - 1 ); i++ ) {
//                cboOcupacion[i + 1] = new SelectItem( ((TbCatalogo) listaInst.get( i )).getId(), ((TbCatalogo) listaInst.get( i )).getCatDescripcionElemento() );
                cboOcupacion[i + 1] = new SelectItem( ( (TbCatalogo)listaInst.get( i ) ).getCatCodigoGrupo() + ( (TbCatalogo)listaInst.get( i ) ).getCatCodigoElemento(), ( (TbCatalogo)listaInst.get( i ) ).getCatDescripcionElemento() );
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return cboOcupacion;
    }

    /*
     * public SelectItem[] getCboModulo () { try {
     *
     * HSModuloDAO dao = (HSModuloDAO) ServiceFinder.findBean(
     * "SpringHibernateDaoCLModulo" ); List arreglo =
     * dao.listarModulosAperturados(); Object[] obj2; cboModulo = new
     * SelectItem[ arreglo.size() + 1 ]; cboModulo[0] = new SelectItem( "0",
     * "[Seleccione]" ); for ( int i = 0; i < (cboModulo.length - 1); i++ ) {
     * obj2 = (Object[]) arreglo.get( i ); cboModulo[i + 1] = new SelectItem(
     * Integer.parseInt( obj2[1].toString() ), obj2[3].toString() ); }
     *
     * } catch ( Exception e ) { e.printStackTrace(); }
     *
     * return cboModulo; }
     */
    public List<ClArbolAcademico> agregarModulo( int area, String tipo, List<ClArbolAcademico> listaArbol ) {


        HSArbolAcademicoClDao daoArbol = CommonDAO.getClArbolAcademicoDAO();
        List<ClArbolAcademico> lista = daoArbol.listarModulos();
        //  System.out.println("el ultimo padre -> "+idPadre);
        for ( int i = 0; i < lista.size(); i++ ) {
            if ( lista.get( i ).getArbTipo().equals( tipo ) ) {
                listaArbol.add( lista.get( i ) );
            } else {
                agregarModulo( area, tipo, listaArbol );
            }
        }

        return listaArbol;
    }


    /*
     * public SelectItem[] getCboModulo2() { // this.setW_mod_id(0); //
     * indiceArbId = new ArrayList<Integer>();
     *
     * List<ClArbolAcademico> listaModulo = new ArrayList<ClArbolAcademico>();
     * List<ClArbolAcademico> lista = agregarModulo( "069001", listaModulo);
     * System.out.println("area_id -> "+this.w_are_id+" \t cantidad registros ->
     * "+lista.size()); cboModulo = new SelectItem[lista.size() + 1];
     * cboModulo[0] = new SelectItem("0", "[Seleccione]"); for (int i = 0; i <
     * (cboModulo.length - 1); i++) { cboModulo[i + 1] = new
     * SelectItem(lista.get(i).getArbId(), lista.get(i).getArbDescripcion());
     *
     *
     * }
     * return cboModulo; }
     */

    /*
     * public SelectItem[] getCboTallerAperturado() { try {
     * HSTallerAperturadoDAO dao = (HSTallerAperturadoDAO)
     * ServiceFinder.findBean("SpringHibernateDaoCLTallerAperturado");
     * List<ClTallerAperturado> lista =
     * dao.seleccionarTallerAperturadoxModulo(Integer.parseInt(this.getW_modulo()));
     * if (lista.size() != 0) { this.cboTallerAperturado = new
     * SelectItem[lista.size() + 1]; this.cboTallerAperturado[0] = new
     * SelectItem("0", "[Seleccione]"); for (int i = 0; i <
     * (this.cboTallerAperturado.length - 1); i++) { this.cboTallerAperturado[i
     * + 1] = new SelectItem(lista.get(i).getTalapeId(),
     * lista.get(i).getTalapeDescripcion()); } } else { this.cboTallerAperturado
     * = new SelectItem[1]; this.cboTallerAperturado[0] = new SelectItem("0",
     * "[Seleccione]"); }
     *
     * } catch (Exception e) { e.printStackTrace();
     * System.out.println("DESCRIPCION DE ERROR Taller aperturado : " +
     * e.getMessage()); } return cboTallerAperturado; }
     */

    /*
     * public SelectItem[] getCboSeccion() { //SpringHibernateDaoCLSeccion
     *
     * //System.out.println("entro aqui el valor es ->
     * "+this.w_tallerAperturado); //System.out.println("entro aqqui cl 2 ->
     * "+this.w_idAperturado); try { HSSeccionCLDAO dao = (HSSeccionCLDAO)
     * ServiceFinder.findBean("SpringHibernateDaoCLSeccion"); //List<ClSeccion>
     * lista =
     * dao.seleccionarSecciones(Integer.parseInt(this.getW_tallerAperturado()));
     * List<ClSeccion> lista =
     * dao.seleccionarSecciones(this.getW_idAperturado()); //
     * System.out.println("cantidad de la lista -> "+lista.size()); if
     * (lista.size() != 0) { this.cboSeccion = new SelectItem[lista.size() + 1];
     * this.cboSeccion[0] = new SelectItem("0", "[Seleccione]"); for (int i = 0;
     * i < (this.cboSeccion.length - 1); i++) { this.cboSeccion[i + 1] = new
     * SelectItem(lista.get(i).getSecId(), lista.get(i).getSecNombre());
     * //System.out.println("el nombre de la seccion es ->
     * "+lista.get(i).getSecNombre()); } } else { this.cboSeccion = new
     * SelectItem[1]; this.cboSeccion[0] = new SelectItem("0", "[Seleccione]");
     * } } catch (Exception e) { System.out.println("erro el cboSeccion -> " +
     * e); e.printStackTrace(); } return cboSeccion; }
     */

    /*
     * public SelectItem[] getCboHorario() { try { this.cboHorario = new
     * SelectItem[1]; this.cboHorario[0] = new SelectItem("0", "[Seleccione]");
     * /*HSHorarioDAO dao = (HSHorarioDAO)
     * ServiceFinder.findBean("SpringHibernateDaoCLHorario"); List<ClHoraria>
     * lista = dao.seleccionarHorario(w_publico_id); if (lista.size() != 0) {
     * this.cboHorario = new SelectItem[lista.size() + 1]; this.cboHorario[0] =
     * new SelectItem("0", "[Seleccione]"); for (int i = 0; i <
     * (this.cboHorario.length - 1); i++) { this.cboHorario[i + 1] = new
     * SelectItem(lista.get(i).getHorId(), lista.get(i).get); } } else {
     * this.cboCurso = new SelectItem[1]; this.cboCurso[0] = new SelectItem("0",
     * "[Seleccione]"); }
     *
     * } catch (Exception e) { e.printStackTrace();
     * System.out.println("DESCRIPCION DE ERROR : " + e.getMessage()); } return
     * this.cboHorario; }
     */
    public SelectItem[] getCboArea() {
        return this.cboArea;
    }

    public SelectItem[] getCboMedio() {
        try {
            HSMedioPublicidadCLDAO dao = CommonDAO.getClMedioPublDAO();
            List<ClMedioPublicidad> listaMedio = dao.listarMedioPublicidadDet( "090001" );
            cboMedio = new SelectItem[ listaMedio.size() ];
            for ( int i = 0; i < cboMedio.length; i++ ) {
                cboMedio[i] = new SelectItem( listaMedio.get( i ).getIdPublicidad(), listaMedio.get( i ).getDescripcion() );
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return cboMedio;
    }

    public SelectItem[] getCboMedio2() {
        try {
            HSMedioPublicidadCLDAO dao = CommonDAO.getClMedioPublDAO();
            List<ClMedioPublicidad> listaMedio = dao.listarMedioPublicidadDet( "090002" );
            cboMedio2 = new SelectItem[ listaMedio.size() ];
            for ( int i = 0; i < cboMedio2.length; i++ ) {
                cboMedio2[i] = new SelectItem( listaMedio.get( i ).getIdPublicidad(), listaMedio.get( i ).getDescripcion() );
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return cboMedio2;
    }

    public SelectItem[] getCboMedio3() {
        try {
            HSMedioPublicidadCLDAO dao = CommonDAO.getClMedioPublDAO();
            List<ClMedioPublicidad> listaMedio = dao.listarMedioPublicidadDet( "090003" );
            cboMedio3 = new SelectItem[ listaMedio.size() ];
            for ( int i = 0; i < cboMedio3.length; i++ ) {
                cboMedio3[i] = new SelectItem( listaMedio.get( i ).getIdPublicidad(), listaMedio.get( i ).getDescripcion() );
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return cboMedio3;
    }

    public SelectItem[] getCboWebCentros() {
        try {
            HSMedioPublicidadCLDAO dao = CommonDAO.getClMedioPublDAO();
            List<ClMedioPublicidad> listaMedio = dao.listarMedioPublicidadDet( "090004" );
            cboWebCentros = new SelectItem[ listaMedio.size() ];
            for ( int i = 0; i < cboWebCentros.length; i++ ) {
                cboWebCentros[i] = new SelectItem( listaMedio.get( i ).getIdPublicidad(), listaMedio.get( i ).getDescripcion() );
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return cboWebCentros;
    }

    public SelectItem[] getCboPeriodicos() {
        try {
            HSMedioPublicidadCLDAO dao = CommonDAO.getClMedioPublDAO();
            List<ClMedioPublicidad> listaMedio = dao.listarMedioPublicidadDet( "090005" );
            cboPeriodicos = new SelectItem[ listaMedio.size() ];
            for ( int i = 0; i < cboPeriodicos.length; i++ ) {
                cboPeriodicos[i] = new SelectItem( listaMedio.get( i ).getIdPublicidad(), listaMedio.get( i ).getDescripcion() );
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return cboPeriodicos;
    }

//    public SelectItem[] getCboMedioDetalle() {
//        try {
//            HSMedioPublicidadCLDAO dao = (HSMedioPublicidadCLDAO) ServiceFinder.findBean( "SpringHibernateMedioPublicidad" );
//            List<ClMedioPublicidadDet> listaMedio = dao.listarMediopublicidadDeta_x_med( this.getConsultaPublico().getMedId() );
//            if ( listaMedio.size() != 0 ) {
//                cboMedioDetalle = new SelectItem[ listaMedio.size() + 1 ];
//                cboMedioDetalle[0] = new SelectItem( "0", "[Seleccione]" );
//                for ( int i = 0; i < (cboMedioDetalle.length - 1); i++ ) {
//                    cboMedioDetalle[i + 1] = new SelectItem( listaMedio.get( i ).getIdPubDet(), listaMedio.get( i ).getPubDetDes() );
//                }
//
//            } else {
//                cboMedioDetalle = new SelectItem[ 1 ];
//                cboMedioDetalle[0] = new SelectItem( "0", "[Seleccione]" );
//            }
//        } catch ( Exception e ) {
//            e.printStackTrace();
//        }
//        return cboMedioDetalle;
//    }
    public void setCboDepartamento( SelectItem[] cboDepartamento ) {
        this.cboDepartamento = cboDepartamento;
    }

    public void setCboProvincia( SelectItem[] cboProvincia ) {
        this.cboProvincia = cboProvincia;
    }

    public void setCboDistrito( SelectItem[] cboDistrito ) {
        this.cboDistrito = cboDistrito;
    }

    public void setCboArea( SelectItem[] cboArea ) {
        this.cboArea = cboArea;
    }

    /*
     * public void setCboHorario(SelectItem[] cboHorario) { this.cboHorario =
     * cboHorario; }
     */
    public void setCboInstituto( SelectItem[] cboInstituto ) {
        this.cboInstituto = cboInstituto;
    }

    public void setCboGradoInstruccion( SelectItem[] cboGradoInstruccion ) {
        this.cboGradoInstruccion = cboGradoInstruccion;
    }

    public void setCboOcupacion( SelectItem[] cboOcupacion ) {
        this.cboOcupacion = cboOcupacion;
    }

    /*
     * public void setCboModulo ( SelectItem[] cboModulo ) { this.cboModulo =
     * cboModulo; }
     */

    /*
     * public void setCboSeccion(SelectItem[] cboSeccion) { this.cboSeccion =
     * cboSeccion; }
     *
     * public void setCboTallerAperturado(SelectItem[] cboTallerAperturado) {
     * this.cboTallerAperturado = cboTallerAperturado; }
     */
    public void setCboMedio( SelectItem[] cboMedio ) {
        this.cboMedio = cboMedio;
    }

    public void setCboMedio2( SelectItem[] cboMedio2 ) {
        this.cboMedio2 = cboMedio2;
    }

    public void setCboMedio3( SelectItem[] cboMedio3 ) {
        this.cboMedio3 = cboMedio3;
    }

    public void setCboWebCentros( SelectItem[] cboWebCentros ) {
        this.cboWebCentros = cboWebCentros;
    }

    public void setCboPeriodicos( SelectItem[] cboPeriodicos ) {
        this.cboPeriodicos = cboPeriodicos;
    }

//    public void setCboMedioDetalle( SelectItem[] cboMedioDetalle ) {
//        this.cboMedioDetalle = cboMedioDetalle;
//    }
    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete( String oncomplete ) {
        this.oncomplete = oncomplete;
    }

    /*
     * public ClPublicoConsulta getConsultaMat() { if (this.consultaMat == null)
     * { this.consultaMat = new ClPublicoConsulta(); } return consultaMat; }
     *
     * public void setConsultaMat(ClPublicoConsulta consultaMat) {
     * this.consultaMat = consultaMat; }
     */
    public List<BeanCLMatricula> getSecciones() {
        return secciones;
    }

    public void setSecciones( List<BeanCLMatricula> secciones ) {
        this.secciones = secciones;
    }

    public List<Integer> getI_detalles() {
        return i_detalles;
    }

    public void setI_detalles( List<Integer> i_detalles ) {
        this.i_detalles = i_detalles;
    }

    public int getI_modulo_mat_id() {
        return i_modulo_mat_id;
    }

    public void setI_modulo_mat_id( int i_modulo_mat_id ) {
        this.i_modulo_mat_id = i_modulo_mat_id;
    }

    public String getI_modulo_mat() {
        return i_modulo_mat;
    }

    public void setI_modulo_mat( String i_modulo_mat ) {
        this.i_modulo_mat = i_modulo_mat;
    }

    public String getI_modulo_mat_text() {
        return i_modulo_mat_text;
    }

    public void setI_modulo_mat_text( String i_modulo_mat_text ) {
        this.i_modulo_mat_text = i_modulo_mat_text;
    }

    public String getI_tallape_mat() {
        return i_tallape_mat;
    }

    public void setI_tallape_mat( String w_tallape_mat ) {
        this.i_tallape_mat = w_tallape_mat;
    }

    public String getI_estpag_id() {
        return i_estpag_id;
    }

    public void setI_estpag_id( String w_estpag_id ) {
        this.i_estpag_id = w_estpag_id;
    }

    public bInformes() {
        this.setW_suggestion( "" );
        this.setW_datos( "" );
        this.setW_publico_id( 0 );
        this.setW_codigo( "" );
        this.setAlumnocl( new BeanClAlumno() );
        this.getConsultaPublico().setMedId( "0" );
        this.getConsultaPublico().setMedIdCentros( "0" );
        this.getConsultaPublico().setMedIdPeriodicos( "0" );
        this.getConsultaPublico().setPlaId( 0 );
        this.getConsultaPublico().setAcLocal( new AcLocal() );
        verConsultasActuales();

    }

    //-------------FIN SETTERS Y GETTERS
    public List<bInformes> autocomplete( Object suggestion ) {

        List<bInformes> listaInf = new ArrayList<bInformes>();
        try {

            String ref = (String)suggestion;
            if ( ref.length() == 5 ) {
                HSPublicoAlumnoCLDAO dao = CommonDAO.getClPublicoAlumnoDAO();
                List<Sp_listarPublicoAlumno> listaAlumnos = dao.listarALumnosPorDato( ref );
                for ( int i = 0; i < listaAlumnos.size(); i++ ) {
                    bInformes inf = new bInformes();
                    String codigo = " ";
                    if ( listaAlumnos.get( i ).getAlu_cod() != null ) {
                        codigo = listaAlumnos.get( i ).getAlu_cod();
                    }
                    inf.setW_codigo( codigo );
                    inf.setW_datos( listaAlumnos.get( i ).getDatos() );
                    inf.setW_publico_id( listaAlumnos.get( i ).getPublico_id() );
                    listaInf.add( inf );
                }
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return listaInf;
    }
    List<BeanAutocompletar> lista;

    public List<BeanAutocompletar> autocompletar( Object suggestionTaller ) throws Exception {
        lista = new ArrayList<BeanAutocompletar>();
        //System.out.println( "ingreso a auntocompletar" );
        String ref = (String)suggestionTaller;
        if ( ref.trim().length() > 2 ) {
            HSArbolAcademicoClDao daoMod = CommonDAO.getClArbolAcademicoDAO();
            List<ClArbolAcademico> listaT = daoMod.listarModulos();


            for ( int i = 0; i < listaT.size(); i++ ) {
                //bInformes inf=new bInformes();
                BeanAutocompletar auto = new BeanAutocompletar();
                auto.setAut_id( listaT.get( i ).getArbId() );
                auto.setAut_descripcion( listaT.get( i ).getArbDescripcion() );
                //System.out.println( "xxxxxxxxxxxxxxxxxxxxxxxxxxxx" + listaT.get( i ).getArbDescripcion() );
                //System.out.println("************************************************************************************");
                lista.add( auto );
            }
        }

        return lista;
    }

    public void buscarAlumno( ActionEvent event ) {
        this.setOncomplete( "" );
        mostrarDatos();
    }

    public void mostrarDatos() {
        try {
            if ( this.getW_publico_id() != 0 ) {
                HSAlumnoCLDAO dao = CommonDAO.getClAlumnoDAO();
                ClAlumno alu = dao.buscarAlumnoPorAluId( this.getW_publico_id() );

                this.setW_publico_id( alu.getAluId() );
                this.getAlumnocl().setAluApmaterno( alu.getAluApmaterno() );
                this.getAlumnocl().setAluAppaterno( alu.getAluAppaterno() );
                this.getAlumnocl().setAluNombres( alu.getAluNombres() );
                this.getAlumnocl().setAluFechaNacimiento( alu.getAluFechaNacimiento() );
                this.getAlumnocl().setAluSexo( alu.getAluSexo() );
                this.getAlumnocl().setAluDni( alu.getAluDni() );
                this.getAlumnocl().setAluDireccion( alu.getAluDireccion() );
                this.getAlumnocl().setAluTelefono( alu.getAluTelefono() );
                this.getAlumnocl().setAluCelular( alu.getAluCelular() );
                this.getAlumnocl().setAluCodigo( alu.getAluCodigo() );
                this.getAlumnocl().setAluId( alu.getAluId() );
                this.getAlumnocl().setAluMail( alu.getAluMail() );
                try {
                    this.setDistrito( dao.ObtenerDistrito( alu.getAluDistrito() ).getDisDes() );
                    this.getAlumnocl().setAluDistrito( alu.getAluDistrito() );
                    this.setW_departamento( ( alu.getAluDistrito().substring( 0, 2 ) + "0000" ) );
                    this.setW_provincia( alu.getAluDistrito().substring( 0, 4 ) + "00" );
                } catch ( Exception e ) {
                    this.getAlumnocl().setAluDistrito( "000000" );
                    this.setW_departamento( "00" );
                    this.setW_provincia( "0000" );
                }
                //this.traerConsultasRealizadas(this.getW_publico_id());
                this.listarConsultasRealizadas( this.getW_publico_id() );
            } else {
                this.setOncomplete( "javascript:alert('Error en la busqueda + buscarAlumno')" );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void nuevoAlumno( ActionEvent event ) {
        this.getAlumnocl_edit().setAluId( 0 );
        this.getAlumnocl_edit().setAluCodigo( "" );
        this.getAlumnocl_edit().setAluNombres( "" );
        this.getAlumnocl_edit().setAluAppaterno( "" );
        this.getAlumnocl_edit().setAluApmaterno( "" );
        this.getAlumnocl_edit().setAluDistrito( "" );
        this.getAlumnocl_edit().setAluCelular( "" );
        this.getAlumnocl_edit().setAluTelefono( "" );
        this.getAlumnocl_edit().setAluMail( "" );
        this.getAlumnocl_edit().setAluFechaNacimiento( new Date() );


        this.setOncomplete( "" );
        this.setOncomplete( "Richfaces.showModalPanel('mpNuevoAlumno')" );
    }

    public void editarAlumno( ActionEvent event ) {
        HSPagoDAO daoPago = (HSPagoDAO)ServiceFinder.findBean( "SpringHibernateDaoPago" );
        this.setHabilitar( "false" );
        if ( daoPago.listarPagosporAlumno( this.getAlumnocl().getAluId() ).size() > 0 ) {
            this.setHabilitar( "true" );
        }

        if ( this.getAlumnocl().getAluId() != 0 ) {
            this.getAlumnocl_edit().setAluId( this.getAlumnocl().getAluId() );
            this.getAlumnocl_edit().setAluCodigo( this.getAlumnocl().getAluCodigo() );
            this.getAlumnocl_edit().setAluNombres( this.getAlumnocl().getAluNombres() );
            this.getAlumnocl_edit().setAluAppaterno( this.getAlumnocl().getAluAppaterno() );
            this.getAlumnocl_edit().setAluApmaterno( this.getAlumnocl().getAluApmaterno() );
            this.getAlumnocl_edit().setAluFechaNacimiento( this.getAlumnocl().getAluFechaNacimiento() );
            this.getAlumnocl_edit().setAluSexo( this.getAlumnocl().getAluSexo() );
            this.getAlumnocl_edit().setAluDni( this.getAlumnocl().getAluDni() );
            this.getAlumnocl_edit().setAluDireccion( this.getAlumnocl().getAluDireccion() );
            this.getAlumnocl_edit().setAluDistrito( this.getAlumnocl().getAluDistrito() );
            this.getAlumnocl_edit().setAluCelular( this.getAlumnocl().getAluCelular() );
            this.getAlumnocl_edit().setAluTelefono( this.getAlumnocl().getAluTelefono() );
            this.getAlumnocl_edit().setAluMail( this.getAlumnocl().getAluMail() );
            if ( getAlumnocl().getAluDistrito().length() >= 2 ) {
                this.setW_departamento( getAlumnocl().getAluDistrito().substring( 0, 2 ) + "0000" );
                this.setW_provincia( getAlumnocl().getAluDistrito().substring( 0, 4 ) + "0000" );
                this.getAlumnocl_edit().setAluDistrito( this.getAlumnocl().getAluDistrito() );
            } else {
                this.setW_departamento( "000000" );
                this.setW_provincia( "000000" );
                this.getAlumnocl_edit().setAluDistrito( "000000" );
            }
            this.setOncomplete( "" );
            this.setOncomplete( "Richfaces.showModalPanel('mpNuevoAlumno')" );
            mostrarDatos();
        } else {
            this.setOncomplete( "javascript:alert('Debe seleccionar a un alumno + editarAlumno')" );
        }
    }

    public void agregarNuevaConsulta( ActionEvent event ) {
        limpiarData();
        if ( this.getAlumnocl().getAluId() != 0 ) {
            this.setOncomplete( "" );
            this.setOncomplete( "Richfaces.showModalPanel('mpNuevaConsulta')" );
            //this.getConsultaPublico().setConProcedencia("064001");
            this.getConsultaPublico().setPlaId( 0 );
            this.getConsultaPublico().setMedId( "0" );
            this.getConsultaPublico().setMedIdCentros( "0" );
            this.getConsultaPublico().setMedIdPeriodicos( "0" );
        } else {
            this.setOncomplete( "javascript:alert('Debe seleccionar a un alumno + agregarNuevaConsulta')" );
        }
    }

    public void guardarDatosAlu( ActionEvent event ) {
        try {
            if ( validarDatos( this.getAlumnocl_edit() ) ) {
                this.setOncomplete( "" );
                HSAlumnoCLDAO dao = CommonDAO.getClAlumnoDAO();
                ClAlumno alumno = new ClAlumno();

                //System.out.println( "id: " + this.getAlumnocl_edit().getAluId() );
                ClAlumno alu = dao.buscarAlumnoPorAluId( this.getAlumnocl_edit().getAluId() );
                //alumno=(ClAlumno)this.getAlumnocl();
                alumno.setAluId( this.getAlumnocl_edit().getAluId() );
                alumno.setAluApmaterno( this.getAlumnocl_edit().getAluApmaterno() );
                alumno.setAluAppaterno( this.getAlumnocl_edit().getAluAppaterno() );
                alumno.setAluNombres( this.getAlumnocl_edit().getAluNombres() );
                alumno.setAluCelular( this.getAlumnocl_edit().getAluCelular() );
                alumno.setAluTelefono( this.getAlumnocl_edit().getAluTelefono() );
                alumno.setAluDistrito( this.getAlumnocl_edit().getAluDistrito() );
                alumno.setAluMail( this.getAlumnocl_edit().getAluMail() );
                alumno.setAluFechaNacimiento( this.getAlumnocl_edit().getAluFechaNacimiento() );
                alumno.setAluDni( this.getAlumnocl_edit().getAluDni() );
                alumno.setAluSexo( this.getAlumnocl_edit().getAluSexo() );
                alumno.setAluDireccion( this.getAlumnocl_edit().getAluDireccion() );

                alumno.setAluPassword( this.getAlumnocl().getAluCodigo() );
                alumno.setAluCodigo( this.getAlumnocl().getAluCodigo() );
                alumno.setAluActivo( "1" );
                //alumno.setAluDni( alu.getAluDni() );
                alumno.setAluTipo( alu.getAluTipo() );
                alumno.setAluProcedencia( alu.getAluProcedencia() );
                alumno.setAluCp( alu.getAluCp() );
                //alumno.setAluSexo( alu.getAluSexo() );
                //alumno.setAluDireccion( alu.getAluDireccion() );
                //alumno.setAluFechaNacimiento( alu.getAluFechaNacimiento() );
                alumno.setAluUnidad( alu.getAluUnidad() );
                alumno.setAluFormaPago( alu.getAluFormaPago() );
                alumno.setAluNombreFamiliar( alu.getAluNombreFamiliar() );
                alumno.setAluParentesco( alu.getAluParentesco() );


                dao.modificarAlumnocl( alumno );
                this.setW_publico_id( this.getAlumnocl_edit().getAluId() );
                this.setOncomplete( "javascript:alert('Registro Modificado correctamente');Richfaces.hideModalPanel('mpNuevoAlumno')" );


                mostrarDatos();

            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    private boolean validarDatos( BeanClAlumno alumno ) {
        boolean sw = true;
        String msj = "";
        if ( alumno.getAluNombres().trim().isEmpty() ) {
            sw = false;
            msj = "javascript:alert('Debe ingresar un nombre válido')";
        }
        if ( alumno.getAluAppaterno().trim().isEmpty() ) {
            sw = false;
            msj = "javascript:alert('Debe ingresar un apellido paterno válido')";
        }
        if ( alumno.getAluApmaterno().trim().isEmpty() ) {
            sw = false;
            msj = "javascript:alert('Debe ingresar un apellido materno válido')";
        }
        this.setOncomplete( msj );
        return sw;
    }

    /*
     * private void traerHorario() { try { HSHorarioDAO dao = (HSHorarioDAO)
     * ServiceFinder.findBean("SpringHibernateDaoCLHorario"); List<ClHoraria>
     * listaH = dao.seleccionarHorario(Integer.parseInt(this.getW_seccion()));
     * this.listaHoraria = new ArrayList<ClHoraria>(); for (int i = 0; i <
     * listaH.size(); i++) { ClHoraria h = new ClHoraria();
     *
     * // h.setHorId(listaH.get(i));
     * h.setNomDia(h.traerNomDiaxCodigo(listaH.get(i).getHorDia()));
     * h.setHorHini(listaH.get(i).getHorHini());
     * h.setHorHfin(listaH.get(i).getHorHfin()); this.listaHoraria.add(h); } }
     * catch (Exception e) { e.printStackTrace(); } }
     */

    /*
     * private void traerConsultasRealizadas(int alu_id) {
     * HSPublicoConsultaCLDAO dao = (HSPublicoConsultaCLDAO)
     * ServiceFinder.findBean("SpringHibernateDaoPublicoConsulta");
     * System.out.println("entro a traer consultas -> ");
     * List<ClPublicoConsulta> c = dao.listarPublicoConsulta(alu_id);
     * System.out.println("el arreglo es :-> "+c); System.out.println("salio a
     * traer consultas -> "); this.listaConsultas = new
     * ArrayList<BeanClConsulta>(); HSUsuarioDAO
     * dao2=(HSUsuarioDAO)ServiceFinder.findBean("SpringHibernateDaoUsuario");
     * for (int i = 0; i < c.size(); i++) { BeanClConsulta consTmp = new
     * BeanClConsulta(0); consTmp.setConsultaId(c.get(i).getConsultaId());
     * consTmp.setNum(i + 1); consTmp.setNomArea(c.get(i).getTipoArea());
     * consTmp.setFechReg((c.get(i)).getFechaRegistro().toString()); //
     * consTmp.setUsuCod((c.get(i)).getUsuCod()); // consTmp.setUsuCod(12345);//
     * (dao2.verUsuario(c.get(i).getUsuCod()));
     * consTmp.setPromotor(dao2.verUsuario(c.get(i).getUsuCod()));
     * consTmp.setNomProcedencia((c.get(i)).getProcedencia().compareTo("1") == 0
     * ? "presencial" : "telefonico");
     * consTmp.setImg_matricular((c.get(i)).getMatriculado() == 1 ?
     * "/Imagenes/actions/ok.png" : "/Imagenes/actions/matricular.gif");
     * consTmp.setImg_observacion("/Imagenes/actions/viewmag.png");
     * consTmp.setTexto_matric((c.get(i)).getMatriculado() == 1 ? "El alumno ya
     * esta matriculado o pre-matriculado" : "Matricular");
     * consTmp.setTexto_observacion((c.get(i)).getObsConsulta());
     *
     * listaConsultas.add(consTmp); } }
     */
    public void listarConsultasRealizadas( int alu_id ) {
        try {
            HSConsultaPublicoDAO dao = CommonDAO.getClConsultaPublicoDAO();
            HSCatalogoDAO daoC = CommonDAO.getTbCatalogoDAO();
            HSUsuarioDAO daoU = CommonDAO.getTbUsuarioDAO();
            HSArbolAcademicoClDao daoArbol = CommonDAO.getClArbolAcademicoDAO();
            List<ClConsultaPublico> listaConsulta = dao.listarxAlumnoConsultaPublico( alu_id );
            this.setListabeanConsultaPublico( new ArrayList<BeanCLConsultaPublico>() );
            if ( listaConsulta != null ) {
                for ( int i = 0; i < listaConsulta.size(); i++ ) {
                    BeanCLConsultaPublico consulta = new BeanCLConsultaPublico();
                    consulta.setClAlumno( listaConsulta.get( i ).getClAlumno() );
                    consulta.setClArbolAcademico( listaConsulta.get( i ).getClArbolAcademico() );
                    //consulta.setClModulo( listaConsulta.get( i ).getClModulo() );
                    consulta.setImg_matricula( listaConsulta.get( i ).getConEstadoMatricula().equals( "0" ) ? "/Imagenes/actions/matricular.gif" : "/Imagenes/actions/ok.png" );
                    consulta.setCon_title( listaConsulta.get( i ).getConEstadoMatricula().equals( "0" ) ? "Matricular" : "El Alumno ya esta Matriculado" );
                    consulta.setCon_tipo_porcedencia( daoC.seleccionarDescripcion( listaConsulta.get( i ).getConProcedencia() ) );
                    consulta.setConFechaContacto( listaConsulta.get( i ).getConFechaContacto() );
                    consulta.setCon_usuario( daoU.verUsuario( listaConsulta.get( i ).getUsuId() ) );
                    consulta.setConId( listaConsulta.get( i ).getConId() );
                    consulta.setConObservacionRegistro( listaConsulta.get( i ).getConObservacionRegistro() );
                    consulta.setCon_contador( i + 1 );
                    consulta.setConEstadoMatricula( listaConsulta.get( i ).getConEstadoMatricula() );
                    consulta.setEspId( listaConsulta.get( i ).getEspId() );

                    if ( listaConsulta.get( i ).getAreaId() == null ) {//area
                        if ( listaConsulta.get( i ).getEspId() != null ) {
                            consulta.setArea( "CARR. PROF." );
                        } else {
                            consulta.setArea( "NO DETERMINADO" );
                        }
                        if ( listaConsulta.get( i ).getEspId() != null ) {
                            AcEspecialidad esp = (AcEspecialidad)CommonDAO.getAcEspecialidadDAO().seleccionarEspecialidadInformes( listaConsulta.get( i ).getEspId() ).get( 0 );
                            consulta.setArea( esp.getEspNombre() );
                        } else {
                            consulta.setArea( "NO DETERMINADO" );
                        }
                    } else {
                        consulta.setArea( daoArbol.buscarArbolPorId( listaConsulta.get( i ).getAreaId() ).getArbDescripcion() );
                    }
                    if ( listaConsulta.get( i ).getCurId() == null ) {//curso
                        if ( listaConsulta.get( i ).getEspId() != null ) {
                            consulta.setCurso( "" );
                        } else {
                            consulta.setCurso( "NO DETERMINADO" );
                        }
                    } else {
                        consulta.setCurso( daoArbol.buscarArbolPorId( listaConsulta.get( i ).getCurId() ).getArbDescripcion() );
                    }
                    if ( listaConsulta.get( i ).getClArbolAcademico() != null ) {
                        consulta.setModulo( listaConsulta.get( i ).getClArbolAcademico().getArbDescripcion() );
                    } else {
                        if ( listaConsulta.get( i ).getEspId() != null ) {
                            AcEspecialidad esp = (AcEspecialidad)CommonDAO.getAcEspecialidadDAO().seleccionarEspecialidadInformes( listaConsulta.get( i ).getEspId() ).get( 0 );
                            consulta.setModulo( esp.getEspNombre() );
                        } else {
                            consulta.setModulo( "NO DETERMINADO" );
                        }
                    }

                    this.getListabeanConsultaPublico().add( consulta );

                }

            }

        } catch ( Exception e ) {
            e.printStackTrace();
            System.out.println( "error -> " + e );
        }
    }

    public void mostrarObervacion( ActionEvent event ) throws Exception {
        int consulta_id = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "consulta_id" ) ).getValue().toString() );
        this.setW_consulta_id_edit( consulta_id );
        HSConsultaPublicoDAO dao = CommonDAO.getClConsultaPublicoDAO();
        this.setW_obs_consulta_editar( dao.seleccionarConsultaPublico( consulta_id ).getConObservacionRegistro() );
        this.setOncomplete( "" );
        this.setOncomplete( "Richfaces.showModalPanel('mpObservacion')" );
    }

    public void limpiarData() {

        this.setConsultaPublico( new ClConsultaPublico() );
        this.getConsultaPublico().setMedId( "0" );
        this.getConsultaPublico().setMedIdCentros( "0" );
        this.getConsultaPublico().setMedIdPeriodicos( "0" );
        this.getConsultaPublico().setPlaId( 0 );
        this.setiInstitucionId( "0" );
        this.getInformacionReferencial().setPersonaRecomendar( "" );
        this.getInformacionReferencial().setCorreoTelefono( "" );
        this.getInformacionReferencial().setOcupacionActual( "000000" );
        this.getInformacionReferencial().setGradoInstruccion( "000000" );
        this.getListarReferencial().clear();

        conReferencial = 0;
        this.setW_esp_id( 0 );
        this.setW_are_id( 0 );
        this.setW_mod_id( 0 );
        this.setIcurId( 0 );
        List<String> resetList = new ArrayList<String>();
        setLstMedSelec( resetList );
        setLstMedSelecC2( resetList );
        setLstMedSelecC3( resetList );
        setLstPeriodicosSelec( resetList );
        setLstWebCentrosSelec( resetList );


    }
    int conReferencial = 0;

    public void agregarReferencialdt( ActionEvent event ) {
        ClInformacionReferencial referencial = new ClInformacionReferencial();
        referencial.setIdReferencial( conReferencial );
        referencial.setPersonaRecomendar( "" );
        referencial.setCorreoTelefono( "" );
        referencial.setActivo( "1" );
        referencial.setGradoInstruccion( "000000" );
        referencial.setOcupacionActual( "000000" );
        referencial.setFechaCreacion( new Date() );
        referencial.setConId( 0 );
        this.getListarReferencial().add( referencial );
        conReferencial--;
    }

    public void quitarReferencial( ActionEvent event ) {
        int p_ref_id = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_ref_id" ) ).getValue().toString() );
        for ( int i = 0; i < this.getListarReferencial().size(); i++ ) {
            if ( this.getListarReferencial().get( i ).getIdReferencial() == p_ref_id && p_ref_id < 1 ) {
                this.getListarReferencial().remove( i );
            }
        }
    }

    public void guardarPublicoConsultaDetalle( ClPublicoConsultaDetalle consultaDetalle ) {
        HSPublicoConsultaDetalleCLDAO dao = (HSPublicoConsultaDetalleCLDAO)ServiceFinder.findBean( "SpringHibernateDaoPublicoConsultaDetalle" );
        dao.guardarConsultaDetalle( consultaDetalle );
    }

    public void actualizarObsConsulta( ActionEvent event ) {
        int consulta_id_edit = this.getW_consulta_id_edit();
        String nuevaObs = this.getW_obs_consulta_editar();
        HSConsultaPublicoDAO dao = CommonDAO.getClConsultaPublicoDAO();
        dao.modificarConsultaPublico( consulta_id_edit, nuevaObs, new Date() );
        ClPublicoConsultaDetalle detalle = new ClPublicoConsultaDetalle();
        ClConsultaPublico consulta = new ClConsultaPublico( consulta_id_edit );
        detalle.setClPublicoConsulta( consulta );
        detalle.setFecha( new Date() );
        detalle.setObservacion( nuevaObs );
        guardarPublicoConsultaDetalle( detalle );

        this.setOncomplete( "javascript:alert('Consulta actualizada correctamente');Richfaces.hideModalPanel('mpObservacion')" );
        this.listarConsultasRealizadas( this.getW_publico_id() );

    }

    public void CargarCursos( ActionEvent event ) {

        try {
            HSSeccionCLDAO daoS = CommonDAO.getClSeccionDAO();

            List<ClSeccion> lista_sec_modulo = daoS.listarSeccionesXCurso( this.w_tall_id );
            //System.out.println( "cantidad de secciones -> " + this.cbo_mod_id + "\t " + lista_sec_modulo.size() );

            if ( !lista_sec_modulo.isEmpty() ) {
                MetodosCL metodo = new MetodosCL();
                this.setSecciones( metodo.mostrarCursosLibresAperturador( lista_sec_modulo ) );
                this.setI_ver_cursos( true );
                this.setI_ver_message( false );
                cargarEstructuraPagoDetalle();
                //DeseleccionarTodos(event);
                // }
            } else {
                this.setI_mensaje_error( "No hay secciones abiertas o con horario asignado para proceder a matricular." );
                this.setI_ver_cursos( false );
                this.setI_ver_message( true );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            System.out.println( "error al cargar el mostrarCursos" );
        }
    }

    public SelectItem[] getCboEstrPagMat() {

        HSEstructuraPagoDAO estPadDao;
        estPadDao = CommonDAO.getClEstructuraPagoDAO();
        List<ClEstructuraPagos> listaPago = estPadDao.seleccionarEstructurasXModulo( this.w_tall_id );
        this.cboEstrPagMat = new SelectItem[ listaPago.size() + 1 ];
        this.cboEstrPagMat[0] = new SelectItem( 0, "[Seleccione]" );
        for ( int i = 0; i < ( this.cboEstrPagMat.length - 1 ); i++ ) {
            this.cboEstrPagMat[i + 1] = new SelectItem( listaPago.get( i ).getEstpagId(), listaPago.get( i ).getEstpagNombre() );
        }
        return cboEstrPagMat;
    }

    public void setCboEstrPagMat( SelectItem[] cboEstrPagMat ) {
        this.cboEstrPagMat = cboEstrPagMat;
    }

    public void MostrarCursos( ActionEvent event ) {
        this.i_estpag_id = "0";
        this.i_modulo_mat_id = 0;
        this.w_cur_id = 0;
        HSArbolAcademicoClDao daoArbol;
        HSConsultaPublicoDAO daoConsultaPub;
        try {


            this.setOncomplete( "" );
            int p_consul_id = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "consulta_id" ) ).getValue().toString() );
            this.setW_consulta_id( p_consul_id );

            daoConsultaPub = CommonDAO.getClConsultaPublicoDAO();
            //this.cur_id = daoConsultaPub.seleccionarConsultaPublico( p_consul_id ).getClArbolAcademico().getArbId();
            if ( daoConsultaPub.seleccionarConsultaPublico( p_consul_id ).getCurId() != null ) {
                this.cur_id = daoConsultaPub.seleccionarConsultaPublico( p_consul_id ).getCurId();// id_curso
            } else {
                this.cur_id = daoConsultaPub.seleccionarConsultaPublico( p_consul_id ).getClArbolAcademico().getArbId();// id_modulo
            }
            daoArbol = CommonDAO.getClArbolAcademicoDAO();
            int p_mod_id = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_mod_id" ) ).getValue().toString() );
            this.cbo_mod_id = p_mod_id;

            //System.out.println( "cbo_mod_id: " + p_mod_id );
            //System.out.println( "cur_id: " + this.cur_id + "   ---" );
            this.setI_modulo_mat_text( daoArbol.buscarArbolPorId( this.cbo_mod_id ).getArbAcadPadre().getArbDescripcion() );
            this.setI_modulo_mat( daoArbol.seleccionarArbol( this.cbo_mod_id ).getArbDescripcion() );
            //this.setI_modulo_mat_id( daoArbol.seleccionarArbol( this.cbo_mod_id ).getArbId() );this.cur_id
            this.setI_modulo_mat_id( daoArbol.seleccionarArbol( this.cur_id ).getArbId() );

            getCboEstrPagMat();

            HSSeccionCLDAO daoS = CommonDAO.getClSeccionDAO();

            List<ClSeccion> lista_sec_modulo = daoS.listarSeccionesXCurso( this.w_cur_id );
            //System.out.println( "cantidad de secciones -> " + this.cbo_mod_id + "\t " + lista_sec_modulo.size() );

            if ( !lista_sec_modulo.isEmpty() ) {
                MetodosCL metodo = new MetodosCL();
                this.setSecciones( metodo.mostrarCursosLibresAperturador( lista_sec_modulo ) );
                this.setI_ver_cursos( true );
                this.setI_ver_message( false );
                // }
            } else {
                this.setI_mensaje_error( "No hay secciones abiertas o con horario asignado para proceder a matricular." );
                this.setI_ver_cursos( false );
                this.setI_ver_message( true );
            }
            ocultarEstructura( event );
            this.setListaEstructuraDetalle( new ArrayList<ClEstructuraPagosDetalle>() );
            this.setOncomplete( "Richfaces.showModalPanel('mpRegistroMatricula')" );
        } catch ( Exception e ) {
            e.printStackTrace();
            System.out.println( "error al cargar el mostrarCursos" );
        }
    }

    public void MatricularSeleccionados( ActionEvent event ) throws Exception {


        this.setOncomplete( "" );
        if ( this.listaEstructuraDetalle.size() != 0 ) {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession session1 = (HttpSession)context.getSession( false );
            //List<ClEstructuraPagosDetalle> listaEstructura = this.getListaEstructuraDetalle();

            MetodosCL metodo = new MetodosCL();
            // String mensaje = metodo.matricularCursoLibre( this.getW_publico_id(), this.getSecciones(), listaEstructura, session1, "", this.getW_consulta_id(), this.getFecha_inicio(), this.getW_diaCobrar(), this.cbo_mod_id, 1 );
            String mensaje = metodo.matricularCursoLibre( this.getW_publico_id(), this.getSecciones(), this.listaEstructuraDetalle, session1, "", this.getW_consulta_id(), this.getFecha_inicio(), this.getW_diaCobrar(), this.cbo_mod_id, 1 );

            this.setOncomplete( mensaje );
            this.listarConsultasRealizadas( this.getW_publico_id() );
        } else {
            this.setOncomplete( "javascript:alert('Seleccione una estructura de pago')" );
        }
    }

    public void EditarMonto( ActionEvent event ) {
        this.setW_monto_cambiar( 0 );
        this.setW_observacion_monto( "" );
        int p_sec_id = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_sec_id" ) ).getValue().toString() );
        this.setBak_sec_id( p_sec_id );

        if ( this.getSecciones().size() > 0 ) {
            for ( int i = 0; i < this.getSecciones().size(); i++ ) {
                if ( this.getSecciones().get( i ).getI_sec_id() == p_sec_id ) {
                    this.getSecciones().get( i ).setI_monto_editar( this.getSecciones().get( i ).getI_monto_pagar() );
                    this.setW_observacion_monto( this.getSecciones().get( i ).getCl_observacion_monto() );

                    /*
                     * this.getSecciones().get(i).setI_ver(false);
                     * this.getSecciones().get(i).setI_editar(true);
                     */
                    this.setW_monto_cambiar( this.getSecciones().get( i ).getI_monto_pagar() );
                    i = this.getSecciones().size();
                }
            }
        }
    }

    public void GuardarMonto( ActionEvent event ) {
        // int p_sec_id = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_sec_id")).getValue().toString());
        String mensaje = "";
        //System.out.println( "el sec_id es -> " + this.getBak_sec_id() );
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession)context.getSession( false );
        bUsuario usu = (bUsuario)session1.getAttribute( "usuario" );
        if ( this.getSecciones().size() > 0 ) {
            for ( int i = 0; i < this.getSecciones().size(); i++ ) {
                if ( this.getSecciones().get( i ).getI_sec_id() == this.getBak_sec_id() ) {
                    if ( this.getW_observacion_monto().length() > 10 ) {
                        this.getSecciones().get( i ).setI_monto_pagar( (float)this.getW_monto_cambiar() );
                        this.getSecciones().get( i ).setCl_observacion_monto( this.getW_observacion_monto() );
                        this.getSecciones().get( i ).setUsu_id( usu.getId_usu() );
                        mensaje = "javascript:Richfaces.hideModalPanel('mpDetalleMonto')";
                        this.setBak_sec_id( 0 );
                    } else {
                        mensaje = "javascript:alert('Debe ingresar una observacion al cambio de monto')";
                    }

                    i = this.getSecciones().size();
                }
            }
        }

        this.setOncomplete( mensaje );
    }

    public void CancelarMonto( ActionEvent event ) {
        int p_sec_id = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_sec_id" ) ).getValue().toString() );
        if ( this.getSecciones().size() > 0 ) {
            for ( int i = 0; i < this.getSecciones().size(); i++ ) {
                if ( this.getSecciones().get( i ).getI_sec_id() == p_sec_id ) {
                    this.getSecciones().get( i ).setI_monto_pagar( this.getSecciones().get( i ).getI_monto_pagar() );
                    this.getSecciones().get( i ).setI_ver( true );
                    this.getSecciones().get( i ).setI_editar( false );
                    i = this.getSecciones().size();
                }
            }
        }
    }

    public void DeseleccionarTodos( ActionEvent event ) {
        if ( this.getSecciones().size() > 0 ) {
            for ( int i = 0; i < this.getSecciones().size(); i++ ) {
                if ( this.getSecciones().get( i ).isI_agregar() ) {
                    this.getSecciones().get( i ).setI_agregar( false );
                }
            }
        }
    }

    public void SeleccionarTodos( ActionEvent event ) {
        if ( this.getSecciones().size() > 0 ) {
            for ( int i = 0; i < this.getSecciones().size(); i++ ) {
                if ( !( this.getSecciones().get( i ).isI_agregar() ) ) {
                    this.getSecciones().get( i ).setI_agregar( true );
                }
            }
        }
    }

    /*
     * public void llenarSeccion ( ActionEvent event ) { try { final
     * HtmlSuggestionBox sbox = (HtmlSuggestionBox)
     * event.getComponent().getParent(); System.out.println( sbox );
     * System.out.println( sbox.getRowIndex() );
     *
     * BeanAutocompletar autocompli = ((List<BeanAutocompletar>)
     * sbox.getValue()).get( sbox.getRowIndex() ); System.out.println(
     * autocompli.getAut_descripcion() + "\t " + autocompli.getAut_id() );
     * this.getInfo().setAut_id( autocompli.getAut_id() );
     * this.getInfo().setAut_descripcion( autocompli.getAut_descripcion() );
     * ClModulo modulo = new ClModulo(); modulo.setModId( autocompli.getAut_id()
     * ); this.getConsultaPublico().setClModulo( modulo ); System.out.println(
     * "el modulo es -> " + autocompli.getAut_id() );
     *
     * } catch ( Exception e ) { e.printStackTrace(); System.out.println( "error
     * -> " + e ); } }
     */
    public String refrescarData() {
        System.out.println( "ingreso a refrescar" );
        System.out.println( "asas ->" + info.getAut_id() );
        // System.out.println("el valoir es -> "+this.getW_tallerAperturado());
        return "";
    }

    public void guardarConsultaPublicoNew( ActionEvent event ) {
        boolean blInfoUch;
        AcLocal acLocal;
        String sIdsMed;
        String sIdsMedC2;
        String sIdsMedC3;
        String sIdsWebCentros;
        String sIdsPeriodicos;
        Integer IdPadre;
        this.oncomplete = "";
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession session1 = (HttpSession)context.getSession( false );
            //System.out.println( "fecha contacto: " + this.getConsultaPublico().getConFechaContacto() );
            //si es informe para UCH
            blInfoUch = "078004".equals( iInstitucionId );

            acLocal = blInfoUch ? CommonDAO.getAcLocalDAO().seleccionarLocal( 1 ) : this.getConsultaPublico().getAcLocal();


            if ( this.icurId > 0 || blInfoUch ) {//curso
                if ( ( this.getConsultaPublico().getAcLocal().getId() != null && this.getConsultaPublico().getAcLocal().getId() > 0 ) || blInfoUch ) {//sede (olivos / colinial)
                    if ( this.getConsultaPublico().getPlaId() != null && this.getConsultaPublico().getPlaId() > 0 ) {//plantilla de horario
//                        if ( this.getConsultaPublico().getDetmedId() != null && this.getConsultaPublico().getDetmedId() > 0 && this.getConsultaPublico().getConObservacionMedio() != null && this.getConsultaPublico().getConObservacionMedio().length() >= 3 ) {//medio por el cual se informó
                        if ( this.getConsultaPublico().getConProcedencia() != null && this.getConsultaPublico().getConProcedencia().length() >= 6 && this.getConsultaPublico().getsConPrioridad() != null && this.getConsultaPublico().getsConPrioridad().length() >= 6 ) {
                            bUsuario usu = (bUsuario)session1.getAttribute( "usuario" );
                            this.getConsultaPublico().setEspId( blInfoUch ? w_esp_id : null );//especialidad en caso sea consulta de Carrera Prof.
                            this.getConsultaPublico().setAreaId( blInfoUch ? null : this.w_are_id );//area
                            this.getConsultaPublico().setClArbolAcademico( blInfoUch ? null : new ClArbolAcademico( this.w_mod_id ) );//modulo
                            this.getConsultaPublico().setCurId( blInfoUch ? null : this.icurId );//curso
                            if ( blInfoUch ) {
                                this.getConsultaPublico().setAcLocal( acLocal );//Si es consulta UCH -> Los Olivos
                            }
                            this.getConsultaPublico().setConActivo( "1" );
                            this.getConsultaPublico().setUsuId( usu.getId_usu() );
                            this.getConsultaPublico().setClAlumno( this.getAlumnocl() );
                            this.getConsultaPublico().setConEstadoMatricula( "0" );
                            this.getConsultaPublico().setConFechaRegistro( new Date() );
                            HSConsultaPublicoDAO dao = CommonDAO.getClConsultaPublicoDAO();
                            sIdsMed = "";
                            for ( int i = 0; i < lstMedSelec.size(); i++ ) {
                                sIdsMed += lstMedSelec.get( i );
                                if ( i + 1 < lstMedSelec.size() ) {
                                    sIdsMed += ",";
                                }
                            }

                            for ( int i = 0; i < lstMedSelecC2.size(); i++ ) {
                                if ( sIdsMed != "" ) {
                                    sIdsMed += "," + lstMedSelecC2.get( i );
                                } else {
                                    sIdsMed += lstMedSelecC2.get( i );
                                    if ( i + 1 < lstMedSelecC2.size() ) {
                                        sIdsMed += ",";
                                    }
                                }

                            }

                            for ( int i = 0; i < lstMedSelecC3.size(); i++ ) {
                                if ( sIdsMed != "" ) {
                                    sIdsMed += "," + lstMedSelecC3.get( i );
                                } else {
                                    sIdsMed += lstMedSelecC3.get( i );
                                    if ( i + 1 < lstMedSelecC3.size() ) {
                                        sIdsMed += ",";
                                    }
                                }

                            }

                            sIdsWebCentros = "";
                            for ( int i = 0; i < lstWebCentrosSelec.size(); i++ ) {
                                sIdsWebCentros += lstWebCentrosSelec.get( i );
                                if ( i + 1 < lstWebCentrosSelec.size() ) {
                                    sIdsWebCentros += ",";
                                }
                            }

                            sIdsPeriodicos = "";
                            for ( int i = 0; i < lstPeriodicosSelec.size(); i++ ) {
                                sIdsPeriodicos += lstPeriodicosSelec.get( i );
                                if ( i + 1 < lstPeriodicosSelec.size() ) {
                                    sIdsPeriodicos += ",";
                                }
                            }

                            this.getConsultaPublico().setMedId( sIdsMed );
                            this.getConsultaPublico().setMedIdCentros( sIdsWebCentros );
                            this.getConsultaPublico().setMedIdPeriodicos( sIdsPeriodicos );
                            dao.agregarConsultaPublico( this.getConsultaPublico() );

                            HSInformacionReferencialDAO daoReferncial = CommonDAO.getClInformacionReferencialDAO();
                            IdPadre = this.getConsultaPublico().getConId(); //obtenemos el id del padre

                            this.getInformacionReferencial().setConId( IdPadre );
                            this.getInformacionReferencial().setActivo( "1" );
                            //this.getInformacionReferencial().setPersonaRecomendar();
                            this.getInformacionReferencial().setFechaCreacion( new Date() );

                            for ( int i = 0; i < this.getListarReferencial().size(); i++ ) {
                                ClInformacionReferencial breferencial = this.getListarReferencial().get( i );
                                this.getInformacionReferencial().setPersonaRecomendar( this.getListarReferencial().get( i ).getPersonaRecomendar() );
                                this.getInformacionReferencial().setCorreoTelefono( this.getListarReferencial().get( i ).getCorreoTelefono() );
                                this.getInformacionReferencial().setGradoInstruccion( this.getListarReferencial().get( i ).getGradoInstruccion() );
                                this.getInformacionReferencial().setOcupacionActual( this.getListarReferencial().get( i ).getOcupacionActual() );

                                if ( breferencial.getIdReferencial() < 1 ) {
                                    daoReferncial.agregarInformacionReferencial( this.getInformacionReferencial() );
                                }
                            }


                            this.listarConsultasRealizadas( this.getAlumnocl().getAluId() );
                            this.setOncomplete( "javascript:alert('Consulta Agregado correctamente');Richfaces.hideModalPanel('mpNuevaConsulta')" );
                            limpiarData();
                        } else {
                            this.setOncomplete( "javascript:alert('Seleccione la Procedencia y Prioridad');" );
                        }
//                        } else {
//                            this.setOncomplete( "javascript:alert('Seleccione Detalle y Observacion de Medio');" );
//                        }
                    } else {
                        this.setOncomplete( "javascript:alert('Seleccione Horario');" );
                    }
                } else {
                    this.setOncomplete( "javascript:alert('Seleccione Sede');" );
                }
            } else {
                this.setOncomplete( "javascript:alert('Seleccione Curso');" );
            }
        } catch ( Exception e ) {
            System.out.println( "error al agregar -> " + e );
            e.printStackTrace();
        }
    }

    public void eliminarConsulta( ActionEvent event ) throws Exception {
        // System.out.println( "[eliminarAlumno] << ENTER" );
        String v_cons_id = ( (UIParameter)event.getComponent().findComponent( "p_cons_id" ) ).getValue().toString();
        HSConsultaPublicoDAO dao = CommonDAO.getClConsultaPublicoDAO();
        dao.eliminarConsultacl( v_cons_id );
        this.listarConsultasRealizadas( this.getW_publico_id() );
       
        //System.out.println( "[eliminarAlumno] >> EXIT" );
    }

    public void mostrarEstructura( ActionEvent event ) {
        this.setW_textoPago( "Ocultar detalle de pagos" );
        this.setW_imgPago( "/Imagenes/actions/down.png" );
        this.setW_ocultar_estructura( "true" );
        this.setW_ocultar_boton( "false" );
        //System.out.println( "ocultar" );
    }

    public void ocultarEstructura( ActionEvent event ) {
        this.setW_textoPago( "Mostrar detalle de pagos" );
        this.setW_imgPago( "/Imagenes/actions/up.png" );
        this.setW_ocultar_estructura( "false" );
        this.setW_ocultar_boton( "true" );
        //System.out.println( "mostrar" );
    }

    public void cancelarConsulta( ActionEvent event ) {
        this.setOncomplete( "" );
        limpiarData();
        this.setOncomplete( "javascript:Richfaces.hideModalPanel('mpNuevaConsulta')" );

    }

    public void verConsultasActuales() {
        this.setOncomplete( "" );
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession)context.getSession( false );
        bUsuario usu = (bUsuario)session1.getAttribute( "usuario" );
        HSConsultaPublicoDAO dao = CommonDAO.getClConsultaPublicoDAO();
        List<ClConsultaPublico> listaC = dao.seleccionarConsultaPublicoporFecha( new Date(), usu.getId_usu() );
        this.setW_visualizar( "false" );
        if ( listaC.size() > 0 ) {

            this.setW_mensaje_actual( "se ha encontrado " + listaC.size() + " alumnos para contactar el dia de Hoy" );
            this.setW_visualizar( "true" );

        }
    }

    public SelectItem[] getB_especialidades() {
        int i = 0;
        List<AcEspecialidad> lstEspecialidades;
        if ( "078004".equals( iInstitucionId ) ) {
            HSEspecialidadDAO espDAO;
            espDAO = CommonDAO.getAcEspecialidadDAO();
            lstEspecialidades = espDAO.seleccionarEspecialidad();
            b_especialidades = new SelectItem[ lstEspecialidades.size() + 1 ];
            b_especialidades[0] = new SelectItem( 0, "[Seleccione]" );
            for ( AcEspecialidad acEsp : lstEspecialidades ) {
                b_especialidades[ ++i] = new SelectItem( acEsp.getId(), acEsp.getEspNombre() );
            }
        } else {
            b_especialidades = new SelectItem[ 1 ];
            b_especialidades[0] = new SelectItem( 0, "[Seleccione]" );
        }
        return b_especialidades;
    }

    public void setB_especialidades( SelectItem[] b_especialidades ) {
        this.b_especialidades = b_especialidades;
    }

    /**
     * @return the w_are_id
     */
    public int getW_are_id() {
        return w_are_id;
    }

    public SelectItem[] getB_areas() {

        try {
            if ( !"0".equalsIgnoreCase( this.iInstitucionId ) ) {
                HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
                List<ClArbolAcademico> areas = dao.AreasXInstitucion( this.iInstitucionId );

                b_areas = new SelectItem[ areas.size() + 1 ];
                b_areas[0] = new SelectItem( Integer.MAX_VALUE, "[Seleccione]" );
                for ( int i = 0; i < areas.size(); i++ ) {
                    ClArbolAcademico a = areas.get( i );
                    b_areas[i + 1] = new SelectItem( a.getArbId(), a.getArbDescripcion() );
                }
            } else {
                b_areas = new SelectItem[ 1 ];
                b_areas[0] = new SelectItem( 0, "[Seleccione]" );
            }

        } catch ( Exception e ) {
            System.err.println( "Error al cargar areas" );
        }

        return b_areas;
    }

    public void setB_areas( SelectItem[] b_areas ) {
        this.b_areas = b_areas;
    }

    public SelectItem[] getB_cursos() {

        try {
            //System.out.println( "b_cursos cbo_mod_id: " + this.cbo_mod_id );
            if ( this.cbo_mod_id > 0 ) { //SOLO MOSTRARA EL CURSO EN EL CUAL PIDIO INFORME
                HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
                if ( dao.buscarArbolPorId( this.cur_id ).getArbNivel() == 3 ) {
                    b_cursos = new SelectItem[ 2 ];
                    b_cursos[0] = new SelectItem( 0, "[Seleccione]" );
                    b_cursos[1] = new SelectItem( dao.buscarArbolPorId( this.cur_id ).getArbId(), dao.buscarArbolPorId( this.cur_id ).getArbDescripcion() );
                } else {
                    List<ClArbolAcademico> cursos = dao.seleccionarCursos( this.cbo_mod_id );
                    b_cursos = new SelectItem[ cursos.size() + 1 ];
                    b_cursos[0] = new SelectItem( Integer.MAX_VALUE, "[Seleccione]" );
                    for ( int i = 0; i < cursos.size(); i++ ) {
                        ClArbolAcademico a = cursos.get( i );
                        b_cursos[i + 1] = new SelectItem( a.getArbId(), a.getArbDescripcion() );
                    }

                }
            } else {
                b_cursos = new SelectItem[ 1 ];
                b_cursos[0] = new SelectItem( 0, "[Seleccione]" );
            }
        } catch ( Exception e ) {
            System.err.println( "Error al cargar areas" );
        }

        return b_cursos;
    }

    public SelectItem[] getB_taller() {

        try {
            //System.out.println( "b_cursos cbo_mod_id: " + this.cbo_mod_id );
            if ( this.w_cur_id > 0 ) { //SOLO SI HA SELECCIONADO UN CURSO SE MOSTRARA UN TALLER FUE MODIFICADO POR ESTRUCTURA DE PAGO
                HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();

                List<ClArbolAcademico> taller = dao.seleccionarCursos( this.w_cur_id );
                b_taller = new SelectItem[ taller.size() + 1 ];
                b_taller[0] = new SelectItem( Integer.MAX_VALUE, "[Seleccione]" );
                for ( int i = 0; i < taller.size(); i++ ) {
                    ClArbolAcademico a = taller.get( i );
                    b_taller[i + 1] = new SelectItem( a.getArbId(), a.getArbDescripcion() );
                }

            } else {
                b_taller = new SelectItem[ 1 ];
                b_taller[0] = new SelectItem( 0, "[Seleccione]" );
            }
        } catch ( Exception e ) {
            System.err.println( "Error al cargar areas" );
        }

        return b_taller;
    }

    public void setB_cursos( SelectItem[] b_cursos ) {
        this.b_cursos = b_cursos;
    }

    public void setB_taller( SelectItem[] b_taller ) {
        this.b_taller = b_taller;
    }

    public void ver_modulos() {
        try {
            try {
                if ( this.w_are_id > 0 ) {
                    HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
                    List<ClArbolAcademico> modulos = dao.seleccionarModulosVisibles( this.w_are_id, "" );

                    b_modulos = new SelectItem[ modulos.size() + 1 ];
                    for ( int i = 0; i < modulos.size(); i++ ) {
                        b_modulos[i + 1] = new SelectItem( modulos.get( i ).getArbId(), modulos.get( i ).getArbDescripcion() );
                    }
                } else {
                    b_modulos = new SelectItem[ 1 ];
                    b_modulos[0] = new SelectItem( 0, "[Seleccione]" );
                }
            } catch ( Exception e ) {
                b_modulos = new SelectItem[ 1 ];
            } finally {
                b_modulos[0] = new SelectItem( 0, "[Seleccione]" );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public SelectItem[] getB_modulos() {
        ver_modulos();
        return b_modulos;
    }

    public void setB_modulos( SelectItem[] b_modulos ) {
        this.b_modulos = b_modulos;
    }

    /**
     * @param w_are_id the w_are_id to set
     */
    public void setW_are_id( int w_are_id ) {
        this.w_are_id = w_are_id;
    }

    public int getW_esp_id() {
        return w_esp_id;
    }

    public void setW_esp_id( int w_esp_id ) {
        this.w_esp_id = w_esp_id;
    }

//    public void listarEstructuraDetalle( ActionEvent event ) {
//        this.setListaEstructuraDetalle( new ArrayList<ClEstructuraPagosDetalle>() );
//        HSEstructuraPagoDAO dao = CommonDAO.getClEstructuraPagoDAO();
//        //System.out.println( "entro al pago" );
//        if ( Integer.parseInt( this.i_estpag_id ) > 0 ) {
//            //System.out.println( "lleno el pago" );
//            //this.setListaDetallePlantilla(dao.seleccionarDetalle(Integer.parseInt(this.i_estpag_id)));
//            this.setListaEstructuraDetalle( dao.listarEstructuraDetalle( Integer.parseInt( this.i_estpag_id ) ) );
//        }
//    }
    // cambio por asignacion de pagos 2014-09-12
    public void cargarEstructuraPagoDetalle() {
        this.setListaEstructuraDetalle( new ArrayList<ClEstructuraPagosDetalle>() );
        //HSEstructuraPagoDAO estPagDAO = (HSEstructuraPagoDAO)ServiceFinder.findBean( "SpringHibernateDaoCLEstructuraPago" );
        HSEstructuraPagoDAO dao = CommonDAO.getClEstructuraPagoDAO();
        if ( this.w_tall_id > 0 ) {
            this.setListaEstructuraDetalle( dao.listarEstructuraDetalle( this.w_tall_id ) );
        }

    }

    public int getW_cur_id() {
        return w_cur_id;
    }

    public void setW_cur_id( int w_cur_id ) {
        this.w_cur_id = w_cur_id;
    }

    public int getW_tall_id() {
        return w_tall_id;
    }

    public void setW_tall_id( int w_tall_id ) {
        this.w_tall_id = w_tall_id;
    }

    public List<ClInformacionReferencial> getListarReferencial() {
        return listarReferencial;
    }

    public void setListarReferencial( List<ClInformacionReferencial> listarReferencial ) {
        this.listarReferencial = listarReferencial;
    }
}
