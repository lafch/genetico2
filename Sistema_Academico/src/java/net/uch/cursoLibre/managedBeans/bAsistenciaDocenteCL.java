/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.academica.hibernateSpringDao.HSDocenteDAO;
import net.uch.asistenciaDocente.hibernateSpringDao.HSAsistenciaDocenteDAO;
import net.uch.asistenciaDocente.managedBeans.bAsistenciaDocente;
import net.uch.asistenciaDocente.managedBeans.beans.AsistenciaDocenteBean;
import net.uch.asistenciaDocente.managedBeans.beans.NuevaAsistenciaBean;
import net.uch.asistenciaDocente.managedBeans.beans.NuevaHoraAdicional;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.managedBeans.beans.AsistenciaDocenteCLBean;
import net.uch.cursoLibre.managedBeans.beans.BeanReporte;
import net.uch.mapping.*;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.util.CommonDAO;
import net.uch.util.CommonWeb;
import net.uch.util.ConstantesWeb;

/**
 *
 * @author USUARIO
 */
public class bAsistenciaDocenteCL {

    private int iSesId;
    private int b_doc_id;
    private String b_doc_codigo;
    private int s_doc_id;
    private int w_doc_id;
    private String w_doc_codigo;
    private String w_doc_nombre;
    private int docId;
    private String sCurNombre;
    private String b_doc_nombre;
    private String sEspNombre;
    private String sSecNombre;
    private String docCodigo;
    private String docNombre;
    private String oncomplete;
    private String oncompleteADD;
    private String suggestion;
    private Date fechaIni;
    private Date fechaFin;
    private Date horaIni2;
    private Date horaFin2;
    private AsistenciaDocenteBean asisDocente = new AsistenciaDocenteBean();
     private AsistenciaDocenteCLBean asisDocenteCL = new AsistenciaDocenteCLBean();
    private NuevaAsistenciaBean nuevAsisDocente = new NuevaAsistenciaBean();
    private NuevaHoraAdicional nuevaHoraAdicional = new NuevaHoraAdicional();
    private SelectItem[] cboDocente;
    private List<BeanReporte> lstSesiones;
    private List<bAsistenciaDocenteCL> b_listar_Sessiones = new ArrayList<bAsistenciaDocenteCL>();
    
    /*nuevo atributos*/
    private int b_ses_id;
    private String b_centro;
    private int b_centro_id;
    private String b_especialidad;
    private String b_seccion;
    private String b_curso;
    private Date b_ses_horaIni = new Date();
    private Date b_ses_horaFin = new Date();
    private Date b_ses_horaIngreso = new Date();
    private Date b_ses_horaSalida = new Date();
    private String b_sesEstadoDocTipo;
    private String b_visualizar;

    public String getCurNombre() {
        return sCurNombre;
    }

    public void setCurNombre( String b_cur_nombre ) {
        this.sCurNombre = b_cur_nombre;
    }

    public int getB_doc_id() {
        return b_doc_id;
    }

    public void setB_doc_id( int b_doc_id ) {
        this.b_doc_id = b_doc_id;
    }

    public String getB_doc_nombre() {
        return b_doc_nombre;
    }

    public void setB_doc_nombre( String b_doc_nombre ) {
        this.b_doc_nombre = b_doc_nombre;
    }

    public String getEspNombre() {
        return sEspNombre;
    }

    public void setEspNombre( String sEspNombre ) {
        this.sEspNombre = sEspNombre;
    }

    public String getSecNombre() {
        return sSecNombre;
    }

    public void setSecNombre( String sSecNombre ) {
        this.sSecNombre = sSecNombre;
    }

    public int getSesId() {
        return iSesId;
    }

    public void setSesId( int iSesId ) {
        this.iSesId = iSesId;
    }

    public int getS_doc_id() {
        return s_doc_id;
    }

    public void setS_doc_id( int s_doc_id ) {
        this.s_doc_id = s_doc_id;
    }

    public int getW_doc_id() {
        return w_doc_id;
    }

    public void setW_doc_id( int w_doc_id ) {
        this.w_doc_id = w_doc_id;
    }

    public String getW_doc_codigo() {
        return w_doc_codigo;
    }

    public void setW_doc_codigo(String w_doc_codigo) {
        this.w_doc_codigo = w_doc_codigo;
    }

    public String getW_doc_nombre() {
        return w_doc_nombre;
    }

    public void setW_doc_nombre(String w_doc_nombre) {
        this.w_doc_nombre = w_doc_nombre;
    }
       
    public String getB_centro() {
        return b_centro;
    }

    public void setB_centro(String b_centro) {
        this.b_centro = b_centro;
    }

    public String getB_curso() {
        return b_curso;
    }

    public void setB_curso(String b_curso) {
        this.b_curso = b_curso;
    }

    public String getB_especialidad() {
        return b_especialidad;
    }

    public void setB_especialidad(String b_especialidad) {
        this.b_especialidad = b_especialidad;
    }

    public String getB_seccion() {
        return b_seccion;
    }

    public void setB_seccion(String b_seccion) {
        this.b_seccion = b_seccion;
    }

    public Date getB_ses_horaFin() {
        return b_ses_horaFin;
    }

    public void setB_ses_horaFin(Date b_ses_horaFin) {
        this.b_ses_horaFin = b_ses_horaFin;
    }

    public Date getB_ses_horaIngreso() {
        return b_ses_horaIngreso;
    }

    public void setB_ses_horaIngreso(Date b_ses_horaIngreso) {
        this.b_ses_horaIngreso = b_ses_horaIngreso;
    }

    public Date getB_ses_horaIni() {
        return b_ses_horaIni;
    }

    public void setB_ses_horaIni(Date b_ses_horaIni) {
        this.b_ses_horaIni = b_ses_horaIni;
    }

    public Date getB_ses_horaSalida() {
        return b_ses_horaSalida;
    }

    public void setB_ses_horaSalida(Date b_ses_horaSalida) {
        this.b_ses_horaSalida = b_ses_horaSalida;
    }

    public int getB_ses_id() {
        return b_ses_id;
    }

    public void setB_ses_id(int b_ses_id) {
        this.b_ses_id = b_ses_id;
    }

    public Date getHoraFin2() {
        return horaFin2;
    }

    public void setHoraFin2( Date horaFin2 ) {
        this.horaFin2 = horaFin2;
    }

    public Date getHoraIni2() {
        return horaIni2;
    }

    public void setHoraIni2( Date horaIni2 ) {
        this.horaIni2 = horaIni2;
    }

    public String getB_sesEstadoDocTipo() {
        return b_sesEstadoDocTipo;
    }

    public void setB_sesEstadoDocTipo(String b_sesEstadoDocTipo) {
        this.b_sesEstadoDocTipo = b_sesEstadoDocTipo;
    }
    
    

    /**
     * Creates a new instance of bAsistenciaDocente
     */
    public bAsistenciaDocenteCL() {
        nuevAsisDocente = new NuevaAsistenciaBean();
    }
    //-- SETTERS Y GETTERS

    public int getDocId() {
        return docId;
    }

    public void setDocId( int docId ) {
        this.docId = docId;
    }

    public String getB_doc_codigo() {
        return b_doc_codigo;
    }

    public void setB_doc_codigo(String b_doc_codigo) {
        this.b_doc_codigo = b_doc_codigo;
    }
    
    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete( String oncomplete ) {
        this.oncomplete = oncomplete;
    }
    
    public String getOncompleteADD() {
        return oncompleteADD;
    }

    public void setOncompleteADD( String oncompleteADD ) {
        this.oncompleteADD = oncompleteADD;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion( String suggestion ) {
        this.suggestion = suggestion;
    }

    public String getDocCodigo() {
        return docCodigo;
    }

    public void setDocCodigo( String docCodigo ) {
        this.docCodigo = docCodigo;
    }

    public String getDocNombre() {
        return docNombre;
    }

    public void setDocNombre( String docNombre ) {
        this.docNombre = docNombre;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin( Date fechaFin ) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni( Date fechaIni ) {
        this.fechaIni = fechaIni;
    }

    public AsistenciaDocenteBean getAsisDocente() {
        return asisDocente;
    }

    public void setAsisDocente( AsistenciaDocenteBean asisDocente ) {
        this.asisDocente = asisDocente;
    }
    
    public AsistenciaDocenteCLBean getAsisDocenteCL() {
        return asisDocenteCL;
    }

    public void setAsisDocenteCL( AsistenciaDocenteCLBean asisDocenteCL ) {
        this.asisDocenteCL = asisDocenteCL;
    }
    
    public String getB_visualizar() {
        return b_visualizar;
    }

    public void setB_visualizar(String b_visualizar) {
        this.b_visualizar = b_visualizar;
    }

    public NuevaAsistenciaBean getNuevAsisDocente() {
        return nuevAsisDocente;
    }

    public void setNuevAsisDocente( NuevaAsistenciaBean nuevAsisDocente ) {
        this.nuevAsisDocente = nuevAsisDocente;
    }

    public NuevaHoraAdicional getNuevaHoraAdicional() {
        return nuevaHoraAdicional;
    }

    public void setNuevaHoraAdicional( NuevaHoraAdicional nuevaHoraAdicional ) {
        this.nuevaHoraAdicional = nuevaHoraAdicional;
    }

    public List<BeanReporte> getLstSesiones() {
        return lstSesiones;
    }

    public void setLstSesiones( List<BeanReporte> lstSesiones ) {
        this.lstSesiones = lstSesiones;
    }

    public SelectItem[] getCboDocente() {
        int iSizeDocs;
        AcDocente acDoc;
        HSDocenteDAO docenteDAO;
        List<AcDocente> lstDocentes;

        if ( cboDocente == null ) {
            try {
                docenteDAO = CommonDAO.getAcDocenteDAO();
                lstDocentes = docenteDAO.seleccionarDocente();
                iSizeDocs = lstDocentes.size();
                cboDocente = new SelectItem[ iSizeDocs ];
                for ( int i = 0; i < iSizeDocs; i++ ) {
                    acDoc = lstDocentes.get( i );
                    cboDocente[i] = new SelectItem( acDoc.getId(), acDoc.getDocNombre() );
                }
            } catch ( Exception ex ) {
                cboDocente = new SelectItem[ 1 ];
                cboDocente[0] = new SelectItem( "-1", "No se obtuvo listado." );
                ex.printStackTrace();
            }
        }
        return cboDocente;
    }

    public void setCboDocente( SelectItem[] cboDocente ) {
        this.cboDocente = cboDocente;
    }
    
    public List<bAsistenciaDocenteCL> getB_listar_Sessiones() {
        return b_listar_Sessiones;
    }

    public void setB_listar_Sessiones(List<bAsistenciaDocenteCL> b_listar_Sessiones) {
        this.b_listar_Sessiones = b_listar_Sessiones;
    }

    //--ACTIONS 
    public void limpiar() {
        nuevAsisDocente = new NuevaAsistenciaBean();
    }

    public void mostrarSessionAsistencia( ActionEvent event ) {
        String sFechaInicio;
        String sFechaFin;
        String sFechaRegistro;
        SimpleDateFormat dateFormat;
        AcSesionAsistencia sesAsis;
        BeanReporte asis;
        ClSeccion clSec;
        HSAsistenciaDocenteDAO daoAsistencia;
        HSDocenteDAO daoDocente;
        List<AcSesionAsistencia> lstSesAsist;
        try {

            daoAsistencia = CommonDAO.getAsistenciaDocenteDAO();
            daoDocente = CommonDAO.getAcDocenteDAO();
            lstSesiones = new ArrayList<BeanReporte>();

            lstSesAsist = daoAsistencia.listarSessionAsistencia_x_docenteFecha( this.docId, this.fechaIni,
                    this.fechaFin, ConstantesWeb.COD_SES_ASIST_CL );
            int iSizeSesAsist = lstSesAsist.size();
            for ( int i = 0; i < iSizeSesAsist; i++ ) {
                asis = new BeanReporte();
                sesAsis = lstSesAsist.get( i );

                clSec = CommonDAO.getClSeccionDAO().seleccionarSeccion( sesAsis.getAcSeccion().getId() );

                asis.setExpr1( sesAsis.getSesId() + "" );
                asis.setExpr2( daoDocente.seleccionarDocente( sesAsis.getDocId() ).getDocNombre() );
                dateFormat = new SimpleDateFormat( "dd/MM/yyyy" );
                sFechaRegistro = dateFormat.format( sesAsis.getSesFechaRegistro() );
                asis.setExpr3( sFechaRegistro );
                try {
                    asis.setExpr4( clSec.getSecNombre() );
                } catch ( Exception ex ) {
                    asis.setExpr4( "NO DETERMINADO" );
                    ex.printStackTrace();
                }
                try {
                    asis.setExpr5( clSec.getClArbolAcademico().getArbAcadPadre().getArbDescripcion() );
                } catch ( Exception ex ) {
                    asis.setExpr5( "NO DETERMINADO" );
                    ex.printStackTrace();
                }

                dateFormat = new SimpleDateFormat( "hh:mm" );
                sFechaInicio = dateFormat.format( sesAsis.getSesFechaInicio() );
                sFechaFin = dateFormat.format( sesAsis.getSesFechaFin() );
                asis.setExpr6( sFechaInicio );
                asis.setExpr7( sFechaFin );
                asis.setExpr8( sesAsis.getDocId() + "" );
                lstSesiones.add( asis );
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    public void seleccionarMarcaDocente( ActionEvent event ) {
        int paramDocId;
        int paramSesId;
        String paramCurNombre;
        String paramSecNombre;
        AcDocente acDoc;
        AcSesionAsistencia sesAsis;
        AcSesionEfectivaAsisDoc sesEfecAsisDoc;
        AsistenciaDocenteBean asisDoc;
        ClSeccion clSec;
        HSAsistenciaDocenteDAO asisDocDAO;
        HSDocenteDAO docDAO;
        Timestamp t1;
        Timestamp t2;

        paramDocId = CommonWeb.parseObjToInt( CommonWeb.getParamFromUIParameterUI( event, "w_doc_id" ) );
        paramSesId = CommonWeb.parseObjToInt( CommonWeb.getParamFromUIParameterUI( event, "w_ses_id" ) );
        paramCurNombre = CommonWeb.getParamFromUIParameterUI( event, "p_cur_nombre" );
        paramSecNombre = CommonWeb.getParamFromUIParameterUI( event, "p_sec_nombre" );

        asisDocDAO = CommonDAO.getAsistenciaDocenteDAO();
        docDAO = CommonDAO.getAcDocenteDAO();
        try {
            sesEfecAsisDoc = asisDocDAO.listaMarcadoDeAsistencia_x_Docente( paramSesId );
            asisDoc = new AsistenciaDocenteBean();

            if ( sesEfecAsisDoc instanceof AcSesionEfectivaAsisDoc ) {
                asisDoc.setDoc_id( sesEfecAsisDoc.getAcDocente().getId() );
                asisDoc.setDoc_codigo( sesEfecAsisDoc.getAcDocente().getDocCodigo() );
                asisDoc.setDoc_nombre( sesEfecAsisDoc.getAcDocente().getDocNombre() );
                asisDoc.setSes_id( sesEfecAsisDoc.getAcSesionAsistencia().getSesId() );

                clSec = CommonDAO.getClSeccionDAO().seleccionarSeccion( sesEfecAsisDoc.getAcSesionAsistencia().getAcSeccion().getId() );
                try {
                    asisDoc.setSec_nombre( clSec.getSecNombre() );
                } catch ( Exception ex ) {
                    asisDoc.setSec_nombre( "NO DETERMINADO" );
                    ex.printStackTrace();
                }

                try {
                    asisDoc.setCur_nombre( clSec.getClArbolAcademico().getArbAcadPadre().getArbDescripcion() );
                } catch ( Exception ex ) {
                    asisDoc.setCur_nombre( "NO DETERMINADO" );
                    ex.printStackTrace();
                }

                asisDoc.setEstasis_code( sesEfecAsisDoc.getSesefeasisdocTipo() );
                asisDoc.setSesefeasisdoc_fecha( sesEfecAsisDoc.getAcSesionAsistencia().getSesFechaRegistro() );
                asisDoc.setSesefeasisdoc_id( sesEfecAsisDoc.getSesefeasisdocId() );

                t1 = sesEfecAsisDoc.getSesefeasisdocRegistro();
                t2 = sesEfecAsisDoc.getSesefeasisdocSalida();
                if ( t1 != null ) {
                    asisDoc.setReg_hora( t1.getHours() );
                    asisDoc.setReg_min( t1.getMinutes() );
                }
                if ( t2 != null ) {
                    asisDoc.setSal_hora( t2.getHours() );
                    asisDoc.setSal_min( t2.getMinutes() );
                }
                sesAsis = asisDocDAO.listarSessionAsistencia_x_sesid( paramSesId );
                asisDoc.setRegistro( sesAsis.getSesFechaInicio() );
                asisDoc.setSalida( sesAsis.getSesFechaFin() );
                asisDoc.setSesefeasisdoc_registro( sesEfecAsisDoc.getSesefeasisdocRegistro() );
                asisDoc.setSesefeasisdoc_salida( sesEfecAsisDoc.getSesefeasisdocSalida() );
                asisDoc.setSesefeasisdoc_obs( sesEfecAsisDoc.getSesefeasisdocObs() );
            } else {
//                System.out.println("nuevo ingreso!");
                acDoc = docDAO.seleccionarDocente( paramDocId );
                sesAsis = asisDocDAO.listarSessionAsistencia_x_sesid( paramSesId );
                asisDoc.setRegistro( sesAsis.getSesFechaInicio() );
                asisDoc.setSalida( sesAsis.getSesFechaFin() );
                asisDoc.setSesefeasisdoc_fecha( sesAsis.getSesFechaRegistro() );
                asisDoc.setDoc_id( acDoc.getId() );
                asisDoc.setDoc_codigo( acDoc.getDocCodigo() );
                asisDoc.setDoc_nombre( acDoc.getDocNombre() );
                asisDoc.setSes_id( paramSesId );
                asisDoc.setSec_nombre( paramSecNombre );
                asisDoc.setCur_nombre( paramCurNombre );
            }
            asisDocente = asisDoc;
            oncomplete = "Richfaces.showModalPanel('mpAsisDocente')";
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
    
    /*MOSTRAMOS LA ASISTENCIA DIARIA*/
    public void mostrarAsistenciaDiaria( ActionEvent event ) {

        SimpleDateFormat dateFormat;

        //BeanReporte asis;
        HSAsistenciaDocenteDAO daoAsistencia;
        HSDocenteDAO daoDocente;
       
        try {
            lstSesiones = new ArrayList<BeanReporte>();

            daoAsistencia = (HSAsistenciaDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoAsistenciaDocente");
            List<bAsistenciaDocenteCL> listaEmpleados = new ArrayList<bAsistenciaDocenteCL>();
            List<AsistenciaDocenteCLBean> empleados = daoAsistencia.listarAsistenciaDiariaCl_x_docenteFecha(this.getW_doc_id(), this.fechaIni,0,this.getAsisDocenteCL().getCentro());
            
            int iSizeSesAsist = empleados.size();
            for ( int i = 0; i < iSizeSesAsist; i++ ) {
                //asis = new BeanReporte();
                bAsistenciaDocenteCL asis = new bAsistenciaDocenteCL();
                    
                asis.setB_ses_id(empleados.get(i).getSes_id());
                asis.setB_doc_id(empleados.get(i).getDoc_id());
                asis.setB_doc_nombre(empleados.get(i).getDoc_nombre());
                asis.setB_centro(empleados.get(i).getCentro());
                asis.setB_especialidad(empleados.get(i).getEspecialidad());
                asis.setB_seccion(empleados.get(i).getSec_nombre());
                asis.setB_curso(empleados.get(i).getCur_nombre());
                asis.setB_ses_horaIni(empleados.get(i).getHora_inicio());
                asis.setB_ses_horaFin(empleados.get(i).getHora_fin());
                asis.setB_ses_horaIngreso(empleados.get(i).getHora_ingreso());
                asis.setB_ses_horaSalida(empleados.get(i).getHora_salida());
                //lstSesiones.add( asis );
                HSAsistenciaDocenteDAO dao = (HSAsistenciaDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoAsistenciaDocente");
                AcSesionAsistencia ses = dao.listarSessionAsistencia_x_sesid(asis.getB_ses_id());
                if(!ses.getSesObservacion().equals("") ){
                    asis.setB_visualizar("true");
                }else{
                 asis.setB_visualizar("false");
                }
                listaEmpleados.add(asis);
                this.setW_doc_id(0);
                this.setW_doc_codigo("");
                this.setW_doc_nombre("");
                this.setSuggestion("");
               
            }
            this.setB_listar_Sessiones(listaEmpleados);
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }
    /*INGRESAMOS LA OBSERVACION AC_SESION_ASISTENCIA*/
    public void seleccionarMarcaObservacionDocente(ActionEvent event) throws Exception {
        HSAsistenciaDocenteDAO dao = (HSAsistenciaDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoAsistenciaDocente");
        HSDocenteDAO daoDoc = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");

        int p_ses_id = Integer.parseInt(((UIParameter) event.getComponent().findComponent("w_ses_id")).getValue().toString());
        String p_cur_nombre = ((UIParameter) event.getComponent().findComponent("p_cur_nombre")).getValue().toString();
        String p_sec_nombre = ((UIParameter) event.getComponent().findComponent("p_sec_nombre")).getValue().toString();
        int p_doc_id = Integer.parseInt(((UIParameter) event.getComponent().findComponent("w_doc_id")).getValue().toString());
        try {
            AcSesionEfectivaAsisDoc sesefeasisdoc = dao.listaMarcadoDeAsistencia_x_Docente(p_ses_id);
            AsistenciaDocenteCLBean asis = new AsistenciaDocenteCLBean();
            if (sesefeasisdoc instanceof AcSesionEfectivaAsisDoc) {

                asis.setDoc_id(sesefeasisdoc.getAcDocente().getId());
                asis.setDoc_codigo(sesefeasisdoc.getAcDocente().getDocCodigo());
                asis.setDoc_nombre(sesefeasisdoc.getAcDocente().getDocNombre());
                asis.setSes_id(sesefeasisdoc.getAcSesionAsistencia().getSesId());
                asis.setSec_nombre(sesefeasisdoc.getAcSesionAsistencia().getAcSeccion().getSecNombre());
                asis.setCur_nombre(sesefeasisdoc.getAcSesionAsistencia().getAcSeccion().getCurape().getPlancur().getCur().getCurNombre());
                asis.setSesefeasisdoc_fecha(sesefeasisdoc.getAcSesionAsistencia().getSesFechaRegistro());
                asis.setSesefeasisdoc_id(sesefeasisdoc.getSesefeasisdocId());
                      
                AcSesionAsistencia ses = dao.listarSessionAsistencia_x_sesid(p_ses_id);
                asis.setRegistro(ses.getSesFechaInicio());
                asis.setSesObservacion(ses.getSesObservacion());
                if(asis.getSesObservacion().equals("")){
                
                }
                asis.setSesEstadoDocTipo(ses.getSesEstadoDocTipo());
                asis.setEstasis_code_diario(ses.getSesEstadoDocTipo());
                asis.setCentro(this.getAsisDocenteCL().getCentro());
                System.out.println("XD ->"+ this.getAsisDocenteCL().getCentro());
                HSCatalogoDAO catalogoDAO = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
                this.setB_sesEstadoDocTipo(catalogoDAO.seleccionarDescripcion(ses.getSesEstadoDocTipo()));
            } else {
                AcDocente doc = daoDoc.seleccionarDocente(p_doc_id);
                AcSesionAsistencia ses = dao.listarSessionAsistencia_x_sesid(p_ses_id);
                asis.setRegistro(ses.getSesFechaInicio());
                asis.setSalida(ses.getSesFechaFin());
                asis.setSesefeasisdoc_fecha(ses.getSesFechaRegistro());
                asis.setDoc_id(doc.getId());
                asis.setDoc_codigo(doc.getDocCodigo());
                asis.setDoc_nombre(doc.getDocNombre());
                asis.setSes_id(p_ses_id);
                asis.setSec_nombre(p_sec_nombre);
                asis.setCur_nombre(p_cur_nombre);
                asis.setSesObservacion(ses.getSesObservacion());
                HSCatalogoDAO catalogoDAO = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
                this.setB_sesEstadoDocTipo(catalogoDAO.seleccionarDescripcion(ses.getSesEstadoDocTipo()));
                asis.setSesEstadoDocTipo(ses.getSesEstadoDocTipo());
                asis.setEstasis_code_diario(ses.getSesEstadoDocTipo());

            }
            this.setAsisDocenteCL(asis);
            this.setOncompleteADD("Richfaces.showModalPanel('mpAsisDocenteDiaria')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*CERRAMOS LA OBSERVACION TABLA AC_SESION_ASISTENCIA */
    /*GUARDAMOS LA OBSERVACION EN LA TABLA AC_SESION_ASISTENCIA*/
    public void guardarObservacionAsistencia(ActionEvent event) {
        int p_ses_id = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_ses_id")).getValue().toString());
        int s_doc_aux_id = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_doc_id")).getValue().toString());
        int p_sesefec_id = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_sesefec_id")).getValue().toString());
        String estadoDocTipo;
        try {
            if (this.getAsisDocenteCL().getEstasis_code_diario().equals("0")) {
                this.setOncompleteADD("javascript:alert('SELECCIONE UN TIPO DE ASISTENCIA');");
            }  else {
                HSAsistenciaDocenteDAO dao = (HSAsistenciaDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoAsistenciaDocente");
                AcSesionAsistencia sesAsis= new AcSesionAsistencia();
                sesAsis.setSesEstadoDocTipo(this.getAsisDocenteCL().getEstasis_code_diario());
                estadoDocTipo=this.getAsisDocenteCL().getEstasis_code_diario();
                String sesObservacion=this.getAsisDocenteCL().getSesObservacion();
                dao.actualizarObservacionSesionDocente(p_ses_id, estadoDocTipo, sesObservacion);
                this.mostrarAsistenciaDiaria(event);
                this.setOncompleteADD("javascript:alert('REGISTRO COMPLETO');"
                        + "Richfaces.hideModalPanel('mpAsisDocenteDiaria')");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /*CERRAMOS LA OBSERVACION DE LA TABLA AC_SESION_ASISTENCIA*/

    public void guardarMarcaAsistencia( ActionEvent event ) {
        int p_ses_id;
        int s_doc_aux_id;
        int p_sesefec_id;
        AcSesionEfectivaAsisDoc acSesEfec;
        Date dateRegistro;
        Date dateSalida;
        HSAsistenciaDocenteDAO asisDocDAO;
        SimpleDateFormat formatTimeStamp;
        Timestamp tfRegistro;
        Timestamp tfSalida;

        s_doc_aux_id = CommonWeb.parseObjToInt( CommonWeb.getParamFromUIParameterUI( event, "p_doc_id" ) );
        p_ses_id = CommonWeb.parseObjToInt( CommonWeb.getParamFromUIParameterUI( event, "p_ses_id" ) );
        p_sesefec_id = CommonWeb.parseObjToInt( CommonWeb.getParamFromUIParameterUI( event, "p_sesefec_id" ) );
        formatTimeStamp = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

        try {
            if ( asisDocente.getEstasis_code().equals( "0" ) ) {
                oncomplete = "javascript:alert('SELECCIONE UN TIPO DE ASISTENCIA');";
            } else if ( asisDocente.getReg_hora() == 0 && this.getAsisDocente().getReg_min() == 0 ) {
                oncomplete = "javascript:alert('INGRESE LA HORA DE INGRESO');";
            } else {

                dateRegistro = new Date( asisDocente.getSesefeasisdoc_fecha().getTime() );
                dateRegistro.setHours( asisDocente.getReg_hora() );
                dateRegistro.setMinutes( asisDocente.getReg_min() );

                dateSalida = new Date( asisDocente.getSesefeasisdoc_fecha().getTime() );
                dateSalida.setHours( asisDocente.getSal_hora() );
                dateSalida.setMinutes( asisDocente.getSal_min() );

                asisDocDAO = CommonDAO.getAsistenciaDocenteDAO();

                acSesEfec = new AcSesionEfectivaAsisDoc();

                if ( p_sesefec_id != 0 ) {
                    acSesEfec.setSesefeasisdocId( p_sesefec_id );
                }
                tfRegistro = Timestamp.valueOf( formatTimeStamp.format( dateRegistro ) );
                acSesEfec.setSesefeasisdocRegistro( tfRegistro );

                tfSalida = null;
                if ( asisDocente.getSal_hora() == 0 && asisDocente.getSal_min() == 0 ) {
                    acSesEfec.setSesefeasisdocSalida( tfSalida );
                } else {
                    tfSalida = Timestamp.valueOf( formatTimeStamp.format( dateSalida ) );
                    acSesEfec.setSesefeasisdocSalida( tfSalida );
                }
                acSesEfec.setSesefeasisdocObs( asisDocente.getSesefeasisdoc_obs() );
                acSesEfec.setSesefeasisdocActivo( "1" );
                acSesEfec.setSesefeasisdocTole( 0 );
                acSesEfec.setSesefeasisdocTipo( asisDocente.getEstasis_code() );
                acSesEfec.setAcSesionAsistencia( asisDocDAO.listarSessionAsistencia_x_sesid( p_ses_id ) );
                acSesEfec.setAcDocente( new AcDocente( s_doc_aux_id ) );

                asisDocDAO.ingresarAsisteanDocente( acSesEfec );
                this.mostrarSessionAsistencia( event );
                this.setOncomplete( "javascript:alert('REGISTRO COMPLETO');"
                        + "Richfaces.hideModalPanel('mpAsisDocente')" );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void seleccionarSesion( ActionEvent event ) {
        int iParamSesId;
        AcDocente doc;
        AcSesionAsistencia sesAsis;
        ClArbolAcademico arbAreaAux;
        ClArbolAcademico arbModAux;
        ClArbolAcademico arbCurAux;
        ClSeccion clSec;
        HSAsistenciaDocenteDAO daoAsistencia;
        HSDocenteDAO daoDocente;
        oncomplete = "";

        iParamSesId = CommonWeb.parseObjToInt( CommonWeb.getParamFromUIParameterUI( event, "w_ses_id" ) );

        daoAsistencia = CommonDAO.getAsistenciaDocenteDAO();
        daoDocente = CommonDAO.getAcDocenteDAO();

        sesAsis = daoAsistencia.listarSessionAsistencia_x_sesid( iParamSesId );

        clSec = CommonDAO.getClSeccionDAO().seleccionarSeccion( sesAsis.getAcSeccion().getId() );
        arbCurAux = clSec.getClArbolAcademico().getArbAcadPadre();
        arbModAux = arbCurAux.getArbAcadPadre();
        arbAreaAux = arbModAux.getArbAcadPadre();
        doc = (AcDocente)daoDocente.seleccionarDocente( sesAsis.getDocId() );

        iSesId = iParamSesId;
        b_doc_id = doc.getId();
        s_doc_id = doc.getId();
        b_doc_nombre = doc.getDocNombre();
        w_doc_id = sesAsis.getDocIdBk();
        sCurNombre = arbCurAux.getArbDescripcion();
        sSecNombre = clSec.getSecNombre();
        sEspNombre = arbAreaAux.getArbDescripcion() + " / " + arbModAux.getArbDescripcion();
        horaIni2 = sesAsis.getSesFechaInicio();
        horaFin2 = sesAsis.getSesFechaFin();

        oncomplete = "Richfaces.showModalPanel('mpSessionAsis')";
    }

    public void actualizarSesion( ActionEvent event ) {
        int s_cambio;
        int s_ses_id;
        int s_doc_ant;
        String tipo;
        HSAsistenciaDocenteDAO asistDocDAO;
        Object obj_inicio;
        Object obj_fin;

        s_ses_id = CommonWeb.parseObjToInt( CommonWeb.getParamFromUIParameterUI( event, "p_ses_id" ) );
        s_cambio = CommonWeb.parseObjToInt( CommonWeb.getParamFromUIParameterUI( event, "p_cambio" ) );
        s_doc_ant = CommonWeb.parseObjToInt( CommonWeb.getParamFromUIParameterUI( event, "p_doc_ant" ) );
        obj_inicio = ( (UIParameter)event.getComponent().findComponent( "p_inicio" ) ).getValue();
        obj_fin = ( (UIParameter)event.getComponent().findComponent( "p_fin" ) ).getValue();

        Date s_inicio = (Date)obj_inicio;
        Date s_fin = (Date)obj_fin;
        try {
            asistDocDAO = CommonDAO.getAsistenciaDocenteDAO();

            tipo = s_cambio != s_doc_ant ? "047002" : "047001";

            asistDocDAO.actualizarSesionDocente( s_ses_id, s_cambio, s_inicio, s_fin, tipo );

            this.mostrarSessionAsistencia( event );

            this.setOncomplete( "javascript:alert('SE ACTUALIZO CORRECTAMENTE LA SESSION');"
                    + "Richfaces.hideModalPanel('mpSessionAsis')" );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public List<AcDocente> autocomplete( Object suggestion ) {
        AcDocente acDocente;
        List<AcDocente> listaEmpleados = new ArrayList<AcDocente>();
        String ref = (String)suggestion;
        HSAsistenciaDocenteDAO dao = CommonDAO.getAsistenciaDocenteDAO() ;
        List<AcDocente> lstDocentes = dao.buscarDocente_x_dato( ref );
        for ( int i = 0; i < lstDocentes.size(); i++ ) {
            acDocente = new AcDocente( lstDocentes.get( i ).getId() );
            acDocente.setDocCodigo( lstDocentes.get( i ).getDocCodigo() );
            acDocente.setDocNombre( ( lstDocentes.get( i ) ).getDocNombre() );
            listaEmpleados.add( acDocente );
        }
        return listaEmpleados;
    }
    
    public List<AcDocente> autocomplete2( Object suggestion ) {
        AcDocente acDocente;
        List<AcDocente> listaEmpleados = new ArrayList<AcDocente>();
        String ref = (String)suggestion;
        HSAsistenciaDocenteDAO dao = CommonDAO.getAsistenciaDocenteDAO() ;
        List<AcDocente> lstDocentes = dao.buscarDocente_x_dato( ref );
        for ( int i = 0; i < lstDocentes.size(); i++ ) {
            acDocente = new AcDocente( lstDocentes.get( i ).getId() );
            acDocente.setDocCodigo( lstDocentes.get( i ).getDocCodigo() );
            acDocente.setDocNombre( ( lstDocentes.get( i ) ).getDocNombre() );
            listaEmpleados.add( acDocente );
        }
        return listaEmpleados;
    }
    
    public List<bAsistenciaDocenteCL> autocompleteDiario(Object suggestion) {
        List<bAsistenciaDocenteCL> listaEmpleados = new ArrayList<bAsistenciaDocenteCL>();
        String ref = (String) suggestion;
        HSAsistenciaDocenteDAO dao = (HSAsistenciaDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoAsistenciaDocente");
        //List empleados = dao.buscarDocente_x_dato1(ref);
        List empleados = dao.buscarDocente_x_dato(ref);
        for (int i = 0; i < empleados.size(); i++) {
            bAsistenciaDocenteCL doc = new bAsistenciaDocenteCL();
            doc.setB_doc_codigo(((AcDocente) empleados.get(i)).getDocCodigo());
            doc.setB_doc_id(((AcDocente) empleados.get(i)).getId());
            doc.setB_doc_nombre(((AcDocente) empleados.get(i)).getDocNombre());
            listaEmpleados.add(doc);
        }
        return listaEmpleados;
    }

    public void eliminarSesion( ActionEvent event ) {
        String sSesId;
        HSAsistenciaDocenteDAO asisDocDAO;

        sSesId = CommonWeb.getParamFromUIParameterUI( event, "delete" );
        try {
            asisDocDAO = CommonDAO.getAsistenciaDocenteDAO();
            asisDocDAO.eliminarSesionDocente( sSesId );
            mostrarSessionAsistencia( event );
            oncomplete = "javascript:alert('SE ACTUALIZO CORRECTAMENTE LA SESSION');"
                    + "Richfaces.hideModalPanel('mpSessionAsis')";
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void validarNuevaSesion() {
        String ts_reg;
        String ts_sal;
        Date d1;
        Date d_reg;
        Date d_sal;
        SimpleDateFormat formatTimeStamp;
        formatTimeStamp = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        this.setOncomplete( "" );
        try {
            d1 = this.getNuevAsisDocente().getN_fsesion();

            d_reg = new Date( d1.getTime() );
            d_reg.setHours( nuevAsisDocente.getReg_hora() );
            d_reg.setMinutes( nuevAsisDocente.getReg_min() );
            nuevAsisDocente.setN_fsesion_ini( d_reg );

            d_sal = new Date( d1.getTime() );
            d_sal.setHours( nuevAsisDocente.getSal_hora() );
            d_sal.setMinutes( nuevAsisDocente.getSal_min() );
            nuevAsisDocente.setN_fsesion_fin( d_sal );

            ts_reg = formatTimeStamp.format( d_reg );
            ts_sal = formatTimeStamp.format( d_sal );

            if ( nuevAsisDocente.getN_doc_id() == 0 ) {
                oncomplete = "javascript:alert('SELECCIONE UN DOCENTE');";
            } else if ( "0".equals( nuevAsisDocente.getCentro() ) ) {
                oncomplete = "javascript:alert('SELECCIONE UN CENTRO');";
            } else if ( nuevAsisDocente.getIdArea() == 0 ) {
                oncomplete = "javascript:alert('SELECCIONE UN ÁREA');";
            } else if ( nuevAsisDocente.getIdMod() == 0 ) {
                oncomplete = "javascript:alert('SELECCIONE UN MÓDULO');";
            } else if ( nuevAsisDocente.getIdCur() == 0 ) {
                oncomplete = "javascript:alert('SELECCIONE UN CURSO');";
            } else if ( nuevAsisDocente.getIdSec() == 0 ) {
                oncomplete = "javascript:alert('SELECCIONE UNA SECCIÓN');";
            } else if ( nuevAsisDocente.getReg_hora() == 0 || nuevAsisDocente.getSal_hora() == 0 ) {
                oncomplete = "javascript:alert('INGRESE HORAS');";
            } else if ( ts_reg.compareTo( ts_sal ) > 0 ) {
                oncomplete = "javascript:alert('LA HORA DE INICIO NO PUEDE SER MAYOR QUE LA DE FIN');";
            } else if ( "0".equalsIgnoreCase( nuevAsisDocente.getN_tipses_code() ) ) {
                oncomplete = "javascript:alert('INGRESE UN TIPO DE SESION');";
            } else {
                nuevaSesion();
                this.setOncomplete( "javascript:alert('SE INGRESO LA NUEVA SESSION');"
                        + "Richfaces.hideModalPanel('mpNuevaSesion')" );
            }
        } catch ( NullPointerException npe ) {
            this.setOncomplete( "javascript:alert('INGRESE FECHAS');" );
        }
    }

    /*validar horas adicionales*/
    public void validarHoraAdicional() {
        String ts_reg;
        String ts_sal;
        Date d1;
        Date d_reg;
        Date d_sal;
        SimpleDateFormat formatTimeStamp;
        formatTimeStamp = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        this.setOncomplete( "" );
        try {
            d1 = this.getNuevaHoraAdicional().getN_fsesion();

            d_reg = new Date( d1.getTime() );
            d_reg.setHours( nuevaHoraAdicional.getReg_hora() );
            d_reg.setMinutes( nuevaHoraAdicional.getReg_min() );
            nuevaHoraAdicional.setN_fsesion_ini( d_reg );

            d_sal = new Date( d1.getTime() );
            d_sal.setHours( nuevaHoraAdicional.getSal_hora() );
            d_sal.setMinutes( nuevaHoraAdicional.getSal_min() );
            nuevaHoraAdicional.setN_fsesion_fin( d_sal );

            ts_reg = formatTimeStamp.format( d_reg );
            ts_sal = formatTimeStamp.format( d_sal );

            if ( nuevaHoraAdicional.getN_doc_id() == 0 ) {
                oncomplete = "javascript:alert('SELECCIONE UN DOCENTE');";
            } else if ( "0".equals( nuevaHoraAdicional.getCentro() ) ) {
                oncomplete = "javascript:alert('SELECCIONE UN CENTRO');";
            } else if ( nuevaHoraAdicional.getIdArea() == 0 ) {
                oncomplete = "javascript:alert('SELECCIONE UN ÁREA');";
            } else if ( nuevaHoraAdicional.getIdMod() == 0 ) {
                oncomplete = "javascript:alert('SELECCIONE UN MÓDULO');";
            } else if ( nuevaHoraAdicional.getIdCur() == 0 ) {
                oncomplete = "javascript:alert('SELECCIONE UN CURSO');";
            } else if ( nuevaHoraAdicional.getIdSec() == 0 ) {
                oncomplete = "javascript:alert('SELECCIONE UNA SECCIÓN');";
            } else if ( nuevaHoraAdicional.getReg_hora() == 0 || nuevaHoraAdicional.getSal_hora() == 0 ) {
                oncomplete = "javascript:alert('INGRESE HORAS');";
            } else if ( ts_reg.compareTo( ts_sal ) > 0 ) {
                oncomplete = "javascript:alert('LA HORA DE INICIO NO PUEDE SER MAYOR QUE LA DE FIN');";
            } else if ( "0".equalsIgnoreCase( nuevaHoraAdicional.getN_tipses_code() ) ) {
                oncomplete = "javascript:alert('INGRESE UN TIPO DE SESION');";
            } else {
                horaAdicional();
                this.setOncomplete( "javascript:alert('SE INGRESO LA NUEVA SESSION');"
                        + "Richfaces.hideModalPanel('mpHoraAdicional')" );
            }
        } catch ( NullPointerException npe ) {
            this.setOncomplete( "javascript:alert('INGRESE FECHAS');" );
        }
    }

    private void nuevaSesion() {
        AcSesionAsistencia sesAsis;
        HSAsistenciaDocenteDAO asisDocDAO;
        SimpleDateFormat formatTimeStamp;
        formatTimeStamp = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        try {
            asisDocDAO = CommonDAO.getAsistenciaDocenteDAO();

            sesAsis = new AcSesionAsistencia();

            sesAsis.setAcSeccion( new AcSeccion( nuevAsisDocente.getIdSec() ) );
            sesAsis.setSesFechaSesion( nuevAsisDocente.getN_fsesion() );
            sesAsis.setSesFechaRegistro( nuevAsisDocente.getN_fsesion() );
            sesAsis.setSesFechaInicio( Timestamp.valueOf( formatTimeStamp.format( nuevAsisDocente.getN_fsesion_ini() ) ) );
            sesAsis.setSesFechaFin( Timestamp.valueOf( formatTimeStamp.format( nuevAsisDocente.getN_fsesion_fin() ) ) );
            sesAsis.setSesActivo( "1" );
            sesAsis.setSesTimeTolerancia( 20 );
            sesAsis.setSesTimeAntes( 20 );
            sesAsis.setSesTimeTope( 30 );
            sesAsis.setAulId( 0 );
            sesAsis.setSesEstado( "044001" );
            sesAsis.setSesFechaSalida( 10 );
            sesAsis.setDocId( nuevAsisDocente.getN_doc_id() );
            sesAsis.setDocIdBk( nuevAsisDocente.getN_doc_id() );
            sesAsis.setSesTipo( nuevAsisDocente.getN_tipses_code() );
            sesAsis.setSesTipoAsistencia( ConstantesWeb.COD_SES_ASIST_CL );
            sesAsis.setSesObservacion("");
            sesAsis.setSesEstadoDocTipo("046001");

            asisDocDAO.ingresarSesionAsistencia( sesAsis );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    private void horaAdicional() {
        SimpleDateFormat formatTimeStamp;
        formatTimeStamp = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        try {
            HSAsistenciaDocenteDAO dao = (HSAsistenciaDocenteDAO)ServiceFinder.findBean( "SpringHibernateDaoAsistenciaDocente" );

            AcSesionAsistencia sesasis = new AcSesionAsistencia();
            //this.getNuevAsisDocente().getN_curso_seccion()
            sesasis.setAcSeccion( new AcSeccion( nuevaHoraAdicional.getIdSec() ) );
            sesasis.setSesFechaSesion( nuevaHoraAdicional.getN_fsesion() );
            sesasis.setSesFechaRegistro( nuevaHoraAdicional.getN_fsesion() );
            sesasis.setSesFechaInicio( Timestamp.valueOf( formatTimeStamp.format( nuevaHoraAdicional.getN_fsesion_ini() ) ) );
            sesasis.setSesFechaFin( Timestamp.valueOf( formatTimeStamp.format( nuevaHoraAdicional.getN_fsesion_fin() ) ) );
            sesasis.setSesActivo( "1" );
            sesasis.setSesTimeTolerancia( 20 );
            sesasis.setSesTimeAntes( 20 );
            sesasis.setSesTimeTope( 30 );
            sesasis.setAulId( 0 );
            sesasis.setSesEstado( "044001" );
            sesasis.setSesFechaSalida( 10 );
            sesasis.setDocId( nuevaHoraAdicional.getN_doc_id() );
            sesasis.setDocIdBk( nuevaHoraAdicional.getN_doc_id() );
            sesasis.setSesTipo( nuevaHoraAdicional.getN_tipses_code() );
            sesasis.setSesTipoAsistencia( ConstantesWeb.COD_SES_ASIST_CL );
            sesasis.setSesObservacion("");
            sesasis.setSesEstadoDocTipo("046001");

            dao.ingresarSesionAsistencia( sesasis );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
