/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.uch.cursoLibre.hibernateSpringDao.HSHorarioDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSAlumnoCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAcademicoClDao;
import net.uch.cursoLibre.managedBeans.beans.BeanClAlumno;
import net.uch.cursoLibre.managedBeans.beans.BeanClAlumnosTarifa;
import net.uch.cursoLibre.managedBeans.beans.BeanClHoraria;
import net.uch.cursoLibre.managedBeans.beans.BeanClSeccion;
import net.uch.mapping.*;
import net.uch.util.CommonDAO;
import net.uch.util.CommonWeb;
import net.uch.util.ConstantesWeb;
import org.richfaces.component.html.HtmlSuggestionBox;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author richard
 */
public class bConsultaAcademica {

    private BeanClAlumno alumno;
    private SelectItem[] cboSede;
    private SelectItem[] cboModulo;
    private SelectItem[] cboCursos;
    private int w_cant_horas;
    private int w_cur_id;
    private int w_mod_id;
    private int w_sed_id;
    private Date w_fecha_ini = new Date();
    private Date w_fecha_fin = new Date();
    private String w_fecha_inis;
    private String w_fecha_fins;
    private String b_tipo_centro;
    private String b_articulo;

    /*
     * propiedades para el automcplete
     */
    private String w_alu_codigo;
    private String w_alu_datos;
    private int w_alu_id;
    private int w_con_id;
    /*
     * fin propiedades para el automcplete
     */
    private String m_sAluCodigoDeuda;
    private String m_sAluNombreDeuda;

    public String getAluCodigoDeuda() {
        return m_sAluCodigoDeuda;
    }

    public void setAluCodigoDeuda( String sAluCodigoDeuda ) {
        m_sAluCodigoDeuda = sAluCodigoDeuda;
    }

    public String getAluNombreDeuda() {
        return m_sAluNombreDeuda;
    }

    public void setAluNombreDeuda( String sAluNombreDeuda ) {
        m_sAluNombreDeuda = sAluNombreDeuda;
    }

    /*
     * atributos para lista de cursos
     */
    private int m_iSecId;
    private String b_sec_modulo;
    private String b_sec_nombre;
    private String b_sec_curso;
    private Date b_fecha_ini;
    private Date b_fecha_fin;
    private double b_sec_nota;
    private String w_cabeceraModulo;
    private String b_doc_nombre;
    private int b_contador;
    private String b_sede;
    private String b_estyloNota;
    private List<ClHoraria> m_lstHorarioSeccion;
    private List<BeanClHoraria> m_lstBeanHorarioSeccion;

    /*
     * fin
     */
    private String oncomplete;
    private SelectItem[] rdoOpciones;
    private String w_rad_id;
    private Calendar fechaInicio = Calendar.getInstance();
    private Calendar fechaFin = Calendar.getInstance();
    private String w_men_deuda;
    private List<bConsultaAcademica> m_lstSecciones = new ArrayList<bConsultaAcademica>();
    List<BeanClAlumnosTarifa> m_lstDeudasAlumno;

    //Control Programa de Auxiliares
    public boolean isEsProgAux() {
        String sModular;
        try{
            sModular = CommonDAO.getClArbolAcademicoDAO().buscarArbolPorId( w_mod_id ).getArbAcadPadre().getArbAreaModular();
        }catch(Exception ex){
            sModular = "0";
        }
//        boolean blEsProgAux = w_area_id == ConstantesWeb.ID_AREA_PROG_AUX;
        boolean blEsProgAux = w_mod_id > 0 && "1".equals( sModular );
        return blEsProgAux;
    }
    //------------------------------

    public bConsultaAcademica( int iSecId, String b_sec_modulo, String b_sec_nombre, String b_sec_curso,
            Date b_fecha_ini, Date b_fecha_fin, double b_sec_nota, int b_contador, String b_doc_nombre, int iSedId ) {
        String[] asSede = { "-", "O", "C" };
        this.m_iSecId = iSecId;
        this.b_sec_modulo = b_sec_modulo;
        this.b_sec_curso = b_sec_curso;
        this.b_sec_nombre = b_sec_nombre;
        this.b_fecha_ini = b_fecha_ini;
        this.b_fecha_fin = b_fecha_fin;
        this.b_sec_nota = b_sec_nota;
        this.b_contador = b_contador;
        this.b_doc_nombre = b_doc_nombre;
        this.b_sede = "";
        this.b_estyloNota = "color:red;";
        long mult=(long)Math.pow(10,0);
        b_sec_nota=(Math.round(b_sec_nota*mult))/(double)mult;
        if ( b_sec_nota >= 13 ) {
            this.b_estyloNota = "color:blue;";
        }
        if ( iSedId >= asSede.length ) {
            iSedId = 0;
        }
        b_sede = asSede[iSedId];
        oncomplete = "";
    }

    public List<BeanClAlumnosTarifa> getLstDeudasAlumno() {
        return m_lstDeudasAlumno;
    }

    public void setLstDeudasAlumno( List<BeanClAlumnosTarifa> lstDeudasAlumno ) {
        this.m_lstDeudasAlumno = lstDeudasAlumno;
    }

    public Date getFechaFin() {
        return fechaFin.getTime();
    }

    public void setFechaFin( Date fechaFin ) {
        this.fechaFin.setTime( fechaFin );
    }

    public Date getFechaInicio() {
        return fechaInicio.getTime();
    }

    public void setFechaInicio( Date fechaInicio ) {
        this.fechaInicio.setTime( fechaInicio );
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public String getB_articulo() {
        return b_articulo;
    }

    public void setB_articulo( String b_articulo ) {
        this.b_articulo = b_articulo;
    }

    public String getB_tipo_centro() {
        return b_tipo_centro;
    }

    public void setB_tipo_centro( String b_tipo_centro ) {
        this.b_tipo_centro = b_tipo_centro;
    }

    public int getW_cant_horas() {
        return w_cant_horas;
    }

    public void setW_cant_horas( int w_cant_horas ) {
        this.w_cant_horas = w_cant_horas;
    }

    public void setOncomplete( String oncomplete ) {
        this.oncomplete = oncomplete;
    }

    public String getW_fecha_fins() {
        return w_fecha_fins;
    }

    public void setW_fecha_fins( String w_fecha_fins ) {
        this.w_fecha_fins = w_fecha_fins;
    }

    public String getB_estyloNota() {
        return b_estyloNota;
    }

    public void setB_estyloNota( String b_estyloNota ) {
        this.b_estyloNota = b_estyloNota;
    }

    public String getW_fecha_inis() {
        return w_fecha_inis;
    }

    public void setW_fecha_inis( String w_fecha_inis ) {
        this.w_fecha_inis = w_fecha_inis;
    }

    public void setB_sede( String b_sede ) {
        this.b_sede = b_sede;
    }

    public String getB_sede() {
        return b_sede;
    }

    public List<ClHoraria> getLstHorarioSeccion() {
        return m_lstHorarioSeccion;
    }

    public void setLstHorarioSeccion( List<ClHoraria> lstHorarioSeccion ) {
        this.m_lstHorarioSeccion = lstHorarioSeccion;
    }

    public List<BeanClHoraria> getLstBeanHorarioSeccion() {
        return m_lstBeanHorarioSeccion;
    }

    public void setLstBeanHorarioSeccion( List<BeanClHoraria> lstBeanHorarioSeccion ) {
        this.m_lstBeanHorarioSeccion = lstBeanHorarioSeccion;
    }

    public String getW_men_deuda() {
        return w_men_deuda;
    }

    public void setW_men_deuda( String w_men_deuda ) {
        this.w_men_deuda = w_men_deuda;
    }

    public int getB_contador() {
        return b_contador;
    }

    public void setB_contador( int b_contador ) {
        this.b_contador = b_contador;
    }

    public String getB_doc_nombre() {
        return b_doc_nombre;
    }

    public void setB_doc_nombre( String b_doc_nombre ) {
        this.b_doc_nombre = b_doc_nombre;
    }

    public List<bConsultaAcademica> getLstSecciones() {
        return m_lstSecciones;
    }

    public void setLstSecciones( List<bConsultaAcademica> listaSeccion ) {
        this.m_lstSecciones = listaSeccion;
    }

    public SelectItem[] getRdoOpciones() {
        this.rdoOpciones = new SelectItem[ 4 ];
        this.rdoOpciones[0] = new SelectItem( "1", "BOLETA DE NOTAS" );
        this.rdoOpciones[1] = new SelectItem( "2", "CERTIFICADOS DE ESTUDIOS(Por Modulo)" );
        this.rdoOpciones[2] = new SelectItem( "4", "CERTIFICADOS DE ESTUDIOS(Por Curso)" );
        this.rdoOpciones[3] = new SelectItem( "3", "CONSTANCIA DE ESTUDIOS" );

        return rdoOpciones;
    }

    public String getW_rad_id() {
        return w_rad_id;
    }

    public void setW_rad_id( String w_rad_id ) {
        this.w_rad_id = w_rad_id == null || w_rad_id.trim().isEmpty() ? "0" : w_rad_id;
    }

    public void setRdoOpciones( SelectItem[] rdoOpciones ) {
        this.rdoOpciones = rdoOpciones;
    }

    public String getW_cabeceraModulo() {
        return w_cabeceraModulo;
    }

    public void setW_cabeceraModulo( String w_cabeceraModulo ) {
        this.w_cabeceraModulo = w_cabeceraModulo;
    }

    public Date getB_fecha_fin() {
        return b_fecha_fin;
    }

    public void setB_fecha_fin( Date b_fecha_fin ) {
        this.b_fecha_fin = b_fecha_fin;
    }

    public Date getB_fecha_ini() {
        return b_fecha_ini;
    }

    public void setB_fecha_ini( Date b_fecha_ini ) {
        this.b_fecha_ini = b_fecha_ini;
    }

    public String getB_sec_curso() {
        return b_sec_curso;
    }

    public void setB_sec_curso( String b_sec_curso ) {
        this.b_sec_curso = b_sec_curso;
    }

    public int getSecId() {
        return m_iSecId;
    }

    public void setSecId( int iSecId ) {
        this.m_iSecId = iSecId;
    }

    public String getB_sec_modulo() {
        return b_sec_modulo;
    }

    public void setB_sec_modulo( String b_sec_modulo ) {
        this.b_sec_modulo = b_sec_modulo;
    }

    public String getB_sec_nombre() {
        return b_sec_nombre;
    }

    public void setB_sec_nombre( String b_sec_nombre ) {
        this.b_sec_nombre = b_sec_nombre;
    }

    public double getB_sec_nota() {
        long mult=(long)Math.pow(10,0);
        b_sec_nota=(Math.round(b_sec_nota*mult))/(double)mult;
        return b_sec_nota;
    }

    public void setB_sec_nota( double b_sec_nota ) {
        this.b_sec_nota = b_sec_nota;
    }
    private String suggestion = "";

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion( String suggestion ) {
        this.suggestion = suggestion;
    }

    public bConsultaAcademica() {
    }

    public bConsultaAcademica( int alu_id, String alu_codigo, String alu_datos ) {
        this.w_alu_id = alu_id;
        this.w_alu_datos = alu_datos;
        this.w_alu_codigo = alu_codigo;
    }

    public BeanClAlumno getAlumno() {
        return alumno;
    }

    public void setAlumno( BeanClAlumno alumno ) {
        this.alumno = alumno;
    }

    public SelectItem[] getCboCursos() {
        int i = 0;
        Object[] objRegSec;
        List lstSecLlevadasPorModObj;
        cboCursos = new SelectItem[ 1 ];
        try {
            lstSecLlevadasPorModObj = CommonDAO.getClSeccionDAO().traerSeccionesValidasPorModuloId( w_mod_id, w_alu_id, 0 );
            if ( !lstSecLlevadasPorModObj.isEmpty() ) {
                cboCursos = new SelectItem[ lstSecLlevadasPorModObj.size() + 1 ];
                for ( Object obj : lstSecLlevadasPorModObj ) {
                    objRegSec = (Object[]) obj;
                    cboCursos[++i] = new SelectItem( objRegSec[0], objRegSec[7].toString() );
                }
            }
        } catch ( Exception ex ) {
        } finally {
            cboCursos[0] = new SelectItem( "0", "[Seleccione Curso]" );
        }
        return cboCursos;
    }

    public void setCboCursos( SelectItem[] cboCursos ) {
        this.cboCursos = cboCursos;
    }

    public SelectItem[] getCboModulo() {
        ClArbolAcademico arbModulo;
        HSArbolAcademicoClDao arbAcadDAO;
        List<ClArbolAcademico> lstModXAlum;
        try {
            arbAcadDAO = CommonDAO.getClArbolAcademicoDAO();
            lstModXAlum = arbAcadDAO.listarModulosxAlumno( this.getW_alu_id() );
            this.cboModulo = new SelectItem[ lstModXAlum.size() + 1 ];
            this.cboModulo[0] = new SelectItem( 0, "[Seleccione Modulo]" );
            for ( int i = 0; i < (this.cboModulo.length - 1); i++ ) {
                arbModulo = lstModXAlum.get( i );
                this.cboModulo[i + 1] = new SelectItem( arbModulo.getArbId(), arbModulo.getArbDescripcion() );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return cboModulo;
    }

    public void setCboModulo( SelectItem[] cboModulo ) {
        this.cboModulo = cboModulo;
    }

    public SelectItem[] getCboSede() {
        return cboSede;
    }

    public void setCboSede( SelectItem[] cboSede ) {
        this.cboSede = cboSede;
    }

    public String getW_alu_codigo() {
        return w_alu_codigo;
    }

    public void setW_alu_codigo( String w_alu_codigoo ) {
        this.w_alu_codigo = w_alu_codigoo;
    }

    public String getW_alu_datos() {
        return w_alu_datos;
    }

    public void setW_alu_datos( String w_alu_datos ) {
        this.w_alu_datos = w_alu_datos;
    }

    public int getW_alu_id() {
        return w_alu_id;
    }

    public void setW_alu_id( int w_alu_id ) {
        this.w_alu_id = w_alu_id;
    }
    
     public int getW_con_id() {
        return w_con_id;
    }

    public void setW_con_id( int w_con_id ) {
        this.w_con_id = w_con_id;
    }


    public Date getW_fecha_fin() {
        return w_fecha_fin;
    }

    public void setW_fecha_fin( Date w_fecha_fin ) {
        this.w_fecha_fin = w_fecha_fin;
    }

    public Date getW_fecha_ini() {
        return w_fecha_ini;
    }

    public void setW_fecha_ini( Date w_fecha_ini ) {
        this.w_fecha_ini = w_fecha_ini;
    }

    public int getW_cur_id() {
        return w_cur_id;
    }

    public void setW_cur_id( int w_cur_id ) {
        this.w_cur_id = w_cur_id;
    }

    public int getW_mod_id() {
        return w_mod_id;
    }

    public void setW_mod_id( int w_mod_id ) {
        this.w_mod_id = w_mod_id;
    }

    public int getW_sed_id() {
        return w_sed_id;
    }

    public void setW_sed_id( int w_sed_id ) {
        this.w_sed_id = w_sed_id;
    }

    public void mostrarDeudaAlumno( ActionEvent event ) throws Exception {
        int iAluId;
        String sNomAlumno;
        ClAlumno alu;
        HSAlumnoCLDAO aluClDAO;
//        List<BeanClAlumnosTarifa> lstBAluTar;
//
        m_lstDeudasAlumno = new ArrayList<BeanClAlumnosTarifa>();
        iAluId = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "iAluId" )).getValue().toString() );
        aluClDAO = CommonDAO.getClAlumnoDAO();
        alu = aluClDAO.buscarAlumnoPorAluId( iAluId );
        m_sAluCodigoDeuda = alu.getAluCodigo();
        m_sAluNombreDeuda = alu.getAluAppaterno() + " " + alu.getAluApmaterno() + " " + alu.getAluNombres();
        m_sAluNombreDeuda = m_sAluNombreDeuda.toUpperCase();

        m_lstDeudasAlumno = listarDeudadAlumnos( iAluId );
        this.setOncomplete( "Richfaces.showModalPanel('mpDeuda');" );

    }

    public List<BeanClAlumnosTarifa> listarDeudadAlumnos( int iAluId ) throws Exception {
        int i;
        double dSaldo;
        String sNomModulo;
        BeanClAlumnosTarifa bMontoDeuda;
        HSAlumnoCLDAO aluClDAO;
        HSArbolAcademicoClDao arbDAO;
        List<ClAlumnoTarifa> lstAlutarIds;
        List<BeanClAlumnosTarifa> lstBAlutar;
        List<AdPago> lstPagos;
        List<String> lstXMod;

        aluClDAO = CommonDAO.getClAlumnoDAO();
        lstBAlutar = new ArrayList<BeanClAlumnosTarifa>();
        lstAlutarIds = (ArrayList<ClAlumnoTarifa>) aluClDAO.consultDeudaALumno( iAluId );

        i = 1;
        for ( ClAlumnoTarifa alumnotar : lstAlutarIds ) {
            bMontoDeuda = new BeanClAlumnosTarifa();
            bMontoDeuda.setAlutar_orden( i + 1 );
            arbDAO = CommonDAO.getClArbolAcademicoDAO();
            lstXMod = (ArrayList<String>) arbDAO.buscarXSeccion( alumnotar.getSecId() );
            sNomModulo = "No Definido";
            if ( !lstXMod.isEmpty() ) {
                sNomModulo = lstXMod.get( 0 );
            }
            bMontoDeuda.setAlumnoTarifa( alumnotar );
            bMontoDeuda.setAlutar_style( "font-size: 10px;" );
            bMontoDeuda.setAlutar_fecha( alumnotar.getAlutarFechaProrroga().getTime() );
            if ( alumnotar.getAlutarFechaProrroga().before( new Date() ) ) {
                bMontoDeuda.setAlutar_style( "font-size: 10px; font-weight: bold; color: red;" );
            }

            if ( alumnotar.getAlutarEstado().equals( "1" ) ) { // cuando es 1
                lstPagos = aluClDAO.listaPagosAlumno( alumnotar.getAlutarId() );
                for ( AdPago pag : lstPagos ) {

                    System.out.println( "Pagó Parcialmente" );
                    bMontoDeuda.setDeu_modulo( sNomModulo );
                    bMontoDeuda.setDeu_curso( alumnotar.getClEstructuraPagosDetalle().getEstpagdetNombre() );//nombre de la deuda
                    bMontoDeuda.setDeu_montopagar( alumnotar.getAlutarMonto() + alumnotar.getAlutarMora() );
                    bMontoDeuda.setDeu_montopagado( pag.getPagMonto() );
                    dSaldo = (alumnotar.getAlutarMonto() + alumnotar.getAlutarMora()) - pag.getPagMonto();
                    bMontoDeuda.setDeu_saldo( dSaldo );
                }
            }
            lstBAlutar.add( bMontoDeuda );
        }
        return lstBAlutar;
    }

    public List<bConsultaAcademica> autocomplete( Object suggestion ) {
        String sCodigo;
        String sRef;
        List<Sp_listarPublicoAlumno> lstAlumnos;
        List<bConsultaAcademica> listaInf = new ArrayList<bConsultaAcademica>();
        try {

            sRef = suggestion.toString();
            if ( sRef.length() >= 3 ) {
                lstAlumnos = CommonDAO.getClPublicoAlumnoDAO().listarALumnosPorDato( sRef );
                for ( int i = 0; i < lstAlumnos.size(); i++ ) {
                    sCodigo = " ";
                    if ( lstAlumnos.get( i ).getAlu_cod() != null ) {
                        sCodigo = lstAlumnos.get( i ).getAlu_cod();
                    }
                    bConsultaAcademica inf = new bConsultaAcademica( lstAlumnos.get( i ).getPublico_id(), sCodigo, lstAlumnos.get( i ).getDatos() );
                    listaInf.add( inf );
                }
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return listaInf;
    }

    public void seleccionarAlumno( ActionEvent event ) {
        final HtmlSuggestionBox sbox;
        bConsultaAcademica autocompli;
        try {
            sbox = (HtmlSuggestionBox) event.getComponent().getParent();
            autocompli = ((List<bConsultaAcademica>) sbox.getValue()).get( sbox.getRowIndex() );
            alumno = buscarDatoAlumno( autocompli.getW_alu_id() );
            w_alu_id = autocompli.getW_alu_id();
            w_mod_id = 0;
            m_lstSecciones = new ArrayList<bConsultaAcademica>();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public BeanClAlumno buscarDatoAlumno( int iAluId ) {
        BeanClAlumno bAlumno;
        HSAlumnoCLDAO daoAlumno;
        List lstDeuda;

        daoAlumno = CommonDAO.getClAlumnoDAO();

        bAlumno = new BeanClAlumno( daoAlumno.buscarAlumnoPorAluId( iAluId ) );
        lstDeuda = daoAlumno.consultDeudaALumno( iAluId );

        w_men_deuda = lstDeuda.isEmpty() ? "" : "EL ALUMNO PRESENTA DEUDA";

        return bAlumno;
    }

    /**
     * Metodo que muestra todas las secciones a la que se matriculo el alumno
     * consultado, segun el modulo seleccionado.
     *
     * @param event evento onchange del combo "iModulo"
     */
    public void traerSeccionesMatriculadas( ActionEvent event ) {
        Double dNota;
        int iCont = 0;
        int iSizeSecc;
        int iSizeSisEvaDets;
        double dPromAsig;
        Date fInicio = null;
        Date fFin = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy/MM/dd" );
        BeanClSeccion clSeccion;
        HSArbolAcademicoClDao arbAcadDAO;
        HSHorarioDAO horDAO;
        String sDocente;
        bConsultaAcademica consultAcad;

        Object[] aoSisEvaDet;
        List<ClHoraria> lstHorario;
        List<BeanClSeccion> lstSecciones;
        List lstSisEvaDets;


        w_cabeceraModulo = "";
        if ( w_mod_id == 0 ) {
            m_lstSecciones.clear();
            return;
        }

        try {
            m_lstSecciones = new ArrayList<bConsultaAcademica>();

            arbAcadDAO = CommonDAO.getClArbolAcademicoDAO();
            horDAO = CommonDAO.getClHorarioDAO();

            lstSecciones = arbAcadDAO.listarSeccionesxAlumnoModulo( this.getW_alu_id(), this.getW_mod_id() );
            iSizeSecc = lstSecciones.size();
            if ( iSizeSecc > 0 ) {
                fInicio = lstSecciones.get( 0 ).getSecFinicio();
                fFin = lstSecciones.get( 0 ).getSecFfin();
            }

            for ( int i = 0; i < iSizeSecc; i++ ) {
                clSeccion = lstSecciones.get( i );
                if ( !isEsProgAux() ) {
                    dNota = CommonDAO.getClNotasDAO().obtenerPormedio( clSeccion.getSecId(), this.getW_alu_id() );
                    if ( dNota == null ) {
                        dNota = 0.01;
                    }
                    if ( clSeccion.getSecFinicio() != null
                            && fInicio != null
                            && clSeccion.getSecFinicio().before( fInicio ) ) {
                        fInicio = clSeccion.getSecFinicio();
                    }
                    if ( clSeccion.getSecFfin() != null
                            && fFin != null
                            && clSeccion.getSecFfin().after( fFin ) ) {
                        fFin = clSeccion.getSecFfin();
                    }
                    sDocente = "DOCENTE : No determinado";
                    w_cabeceraModulo = clSeccion.getNomCurso();
                    lstHorario = horDAO.seleccionarHorario( clSeccion.getSecId() );
                    if ( lstHorario != null && !lstHorario.isEmpty() ) {
                        sDocente = "DOCENTE : " + lstHorario.get( 0 ).getAcDocente().getDocNombre();
                    }
                    if(dNota != 0.01 ){
                    consultAcad = new bConsultaAcademica( clSeccion.getSecId(),
                            clSeccion.getNomModulo(), clSeccion.getSecNombre(), clSeccion.getNomCurso(),
                            clSeccion.getSecFinicio(), clSeccion.getSecFfin(), dNota, i + 1, sDocente, clSeccion.getLocId() );
                    
                    consultAcad.setLstHorarioSeccion( lstHorario );
                    
                    m_lstSecciones.add( consultAcad );
                    }
                } else {

                    lstSisEvaDets = CommonDAO.getClSistemaEvaluacionDAO().listarSisEvaDetallePorSecId( clSeccion.getSecId() );
                    iSizeSisEvaDets = lstSisEvaDets.size();
                    w_cabeceraModulo = CommonDAO.getClArbolAcademicoDAO().buscarArbolPorId( w_mod_id ).getArbDescripcion();
                    for ( int j = 0; j < iSizeSisEvaDets; j++ ) {
                        aoSisEvaDet = (Object[]) lstSisEvaDets.get( j );
                        dPromAsig = traerPromedioPorAsignaturaProgAux( Integer.parseInt( aoSisEvaDet[0].toString() ), clSeccion.getSecId() );
                        if(dPromAsig!=0){
                        consultAcad = new bConsultaAcademica( Integer.parseInt( aoSisEvaDet[0].toString() ),
                                clSeccion.getNomCurso(), clSeccion.getSecNombre(), aoSisEvaDet[2].toString(),
                                clSeccion.getSecFinicio(), clSeccion.getSecFfin(), dPromAsig, ++iCont, "", clSeccion.getLocId() );
                        m_lstSecciones.add( consultAcad );
                        }
                    }
                }
            }
            try {
                w_fecha_inis = dateFormat.format( fInicio );
                w_fecha_fins = dateFormat.format( fFin );
            } catch ( Exception ex ) {
                w_fecha_inis = "";
                w_fecha_fins = "";
            }


        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    private Double traerPromedioPorAsignaturaProgAux( int iIdSisEvaDet, int iSecId ) {
        int iSizeSisEvaPers;
        double dPeso;
        double dSumNotas;
        double dSumPeso;
        double dValorNota;
        double dProm;
        Object[] aoNota;
        Object[] aoSisEvaPer;
        List lstNotas;
        List lstSisEvalPerXDet;

        try {
            lstSisEvalPerXDet = CommonDAO.getClSistemaEvaluacionDAO().listarSistemaEvaPersonPorSeccionYDet( iSecId, iIdSisEvaDet );
            iSizeSisEvaPers = lstSisEvalPerXDet.size();
            dPeso = 0;
            dSumNotas = dSumPeso = 0;
            for ( int k = 0; k < iSizeSisEvaPers; k++ ) {
                aoSisEvaPer = (Object[]) lstSisEvalPerXDet.get( k );
                if( iSecId == 5354 && w_alu_id==22250 && Integer.parseInt( aoSisEvaPer[0].toString() ) >= 1649 && Integer.parseInt( aoSisEvaPer[0].toString() ) <= 1652){
                    System.out.println( "SEC_ID : " + iSecId + " / ALU_ID : " + w_alu_id + " / SISEVA_PER_ID : " + Integer.parseInt( aoSisEvaPer[0].toString() ) );
                }
                lstNotas = CommonDAO.getClNotasDAO().seleccionarNotaAlumnoSeccion( w_alu_id, iSecId, Integer.parseInt( aoSisEvaPer[0].toString() ) );
                if ( !lstNotas.isEmpty() ) {
                    aoNota = (Object[]) lstNotas.get( 0 );
                    dValorNota = 0;
                    if ( aoNota.length > 0 ) {
                        dPeso = Double.parseDouble( aoSisEvaPer[11].toString() );
                        dValorNota = Double.parseDouble( aoNota[1].toString() );
                    }
                } else {
                    dValorNota = 0;
                }
                dSumNotas += dValorNota * dPeso;
                dSumPeso += dPeso;
            }
            dProm = dSumPeso == 0 ? 0 : dSumNotas / dSumPeso;
        } catch ( Exception ex ) {
            ex.printStackTrace();
            dProm = 0;
        }
        return dProm;
    }

    public void mostrarHorarioSeccion( ActionEvent event ) throws Exception {
        int iSecId;
        BeanClHoraria bHorario;
        List<ClHoraria> lstHorario;
        try {
            iSecId = Integer.parseInt( CommonWeb.getParamFromUIFacesContext( event, "secId" ) );
        } catch ( Exception ex ) {
            iSecId = 0;
        }

        lstHorario = CommonDAO.getClHorarioDAO().seleccionarHorario( iSecId );
        if ( lstHorario != null ) {
            m_lstBeanHorarioSeccion = new ArrayList<BeanClHoraria>();
            for ( ClHoraria clHoraria : lstHorario ) {
                bHorario = new BeanClHoraria( clHoraria );
                bHorario.setV_hor_tipo_clase( CommonDAO.getTbCatalogoDAO().seleccionarDescripcion( clHoraria.getHorTipoClase() ) );
                bHorario.setV_hor_dia( CommonDAO.getTbCatalogoDAO().seleccionarDescripcion( clHoraria.getHorDia() ) );
                m_lstBeanHorarioSeccion.add( bHorario );
            }
            oncomplete = "Richfaces.showModalPanel('mpHorarioSeccion')";
        }
    }

    public void ImprimirFicha( ActionEvent event ) throws Exception {
        int iAreaId;
        String sTipoCentro;
        String sArticulo;
        ClArbolAcademico arbArea;
        HSArbolAcademicoClDao arbAcadDAO;

        oncomplete = "";
        b_articulo = "";
        b_tipo_centro = "";
        
        arbAcadDAO = CommonDAO.getClArbolAcademicoDAO();
        arbArea = arbAcadDAO.seleccionarArbol( this.getW_mod_id() );
        if ( arbArea == null ) {
            return;
        }

        
        if ( Integer.parseInt( w_rad_id ) == ConstantesWeb.RPT_CERT_EST_MODULAR ) {
            this.setOncomplete( "javascript:Richfaces.showModalPanel('mpEstablecerFechas')" );
        } else if ( Integer.parseInt( w_rad_id ) == ConstantesWeb.RPT_CERT_EST_POR_CURSO ) {
            this.setOncomplete( "javascript:Richfaces.showModalPanel('mpEstablecerCurso')" );
        } else {
            this.setOncomplete( "javascript:Richfaces.showModalPanel('mp7')" );
        }
        iAreaId = arbArea.getArbIdPadre();
        sArticulo = "el";
        sTipoCentro = CommonDAO.getTbCatalogoDAO().seleccionarDescripcion( arbArea.getArbInstitucion() );//sacar el centro
        this.setB_tipo_centro( sTipoCentro );
        this.setB_articulo( sArticulo );


    }
    
    public void ImprimirReporte( ActionEvent event ) throws Exception {
        oncomplete = "";
        int p_consulta_id = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "p_consulta_id" )).getValue().toString() );
        int p_alu_id = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "p_alu_id" )).getValue().toString() );
        this.setW_alu_id(p_alu_id);
        this.setW_con_id(p_consulta_id);
        this.w_rad_id="186";
        this.w_mod_id=1;
       this.setOncomplete( "javascript:Richfaces.showModalPanel('mp7')" );
    }

    public void continuarImpresionModular( ActionEvent event ) throws Exception {
        this.setOncomplete( "javascript:Richfaces.hideModalPanel('mpEstablecerFechas');Richfaces.showModalPanel('mp7')" );
    }

    public void continuarImpresionCurso( ActionEvent event ) throws Exception {
        if ( w_cur_id != 0 ) {
            this.setOncomplete( "javascript:Richfaces.hideModalPanel('mpEstablecerCurso');Richfaces.showModalPanel('mp7')" );
        } else {
            this.setOncomplete( "javascript:alert('Debe elegir un curso');" );
        }
    }

    public void cargarReporte( OutputStream out, Object data ) throws IOException, Exception, EOFException {

            cargarReporteDynamic( out );
        
    }

    public void cargarReporteDynamic( OutputStream out ) throws IOException, Exception, EOFException {
        byte[] bytes;
        byte[] abPdf;
        int iSize;
        ByteArrayOutputStream baos;
        InputStream is;
        
        baos = new ByteArrayOutputStream();

        JasperExportManager.exportReportToPdfStream( cargar_reporteDynamic( Integer.parseInt( this.w_rad_id ) ), baos );
        bytes = baos.toByteArray();
        is = new ByteArrayInputStream( bytes );
        iSize = is.available();
        abPdf = new byte[ iSize ];
        is.read( abPdf );
        out.write( abPdf );
        baos.flush();
        baos.close();
        is.close();
        out.flush();
        out.close();
    }

    public JasperPrint cargar_reporteDynamic( int idReporte ) {
        int i;
        int iAprob;
        int iDesaprob;
        int iSecId;
        String sNomReporte;

        String sFechaInicio;
        String sFechaFin;
        String sPromedio;
        
        Double dPromedio;

        bConsultaAcademica sConAcad;
        Calendar fechaActual;
        ClArbolAcademico clArbModuloCur;
        Connection conn;
        DriverManagerDataSource dmds;
        NumberFormat numFormat = new DecimalFormat( "##" );
        FacesContext fc;
        URL FileImagen3;
        InputStream reportStream;
        JasperPrint jasperPrint;
        HashMap hmParams;
        List lstSecLlevadasPorModObj;
        Double[] adNotas;
        Object[] aobjCurData;
        DateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd" );
        Date fecha;
        

        fechaActual = Calendar.getInstance();
        try {
            jasperPrint = null;
            hmParams = new HashMap();
            if ( w_mod_id > 0 && validarReporte( idReporte ) ) {
                switch ( idReporte ) {
                    case ConstantesWeb.RPT_BOLETA_NOTAS:
                        //jasperPrint =dina.generarReporteinscritosxSeccion(Integer.valueOf(this.getI_sec_id()));
                        iAprob = 0;
                        iDesaprob = 0;

                        for ( int j = 0; j < m_lstSecciones.size(); j++ ) {
                            sConAcad = m_lstSecciones.get( j );
                            if ( sConAcad.getB_sec_nota() > 10 ) {
                                iAprob++;
                            } else {
                                iDesaprob++;
                            }
                        }

                        sNomReporte = "rep_boleta_notas.jasper";
                        hmParams.put( "p_aprob", String.valueOf( iAprob ) );
                        hmParams.put( "p_desaprob", String.valueOf( iDesaprob ) );
                        hmParams.put( "p_total_cur", String.valueOf( iAprob + iDesaprob ) );
                        hmParams.put( "p_mod_id", String.valueOf( w_mod_id ) );
                        hmParams.put( "p_alu_id", String.valueOf( w_alu_id ) );
                        break;
                        /*ficha de matricula*/
                        case ConstantesWeb.RPT_FICHA_MATRICULA:
                        //jasperPrint =dina.generarReporteinscritosxSeccion(Integer.valueOf(this.getI_sec_id()));
                        sNomReporte = "informacion_referencial_uch.jasper";

                        hmParams.put( "p_con_id", String.valueOf( w_con_id ) );
                        hmParams.put( "alu_id", String.valueOf( w_alu_id ) );
                        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                        String filepath = ctx.getRealPath("/WEB-INF/Reportes/cursos_libres/"); 
                        String FileImagen=ctx.getRealPath("/Imagenes/actions/logo_p.jpg"); 
                         hmParams.put("SUBREPORT_DIR", filepath); 
                         hmParams.put("logo", FileImagen); 
                        break;
                        /*cerramos ficha de matricula*/
                    case ConstantesWeb.RPT_CERT_EST_MODULAR:
                        if ( fechaInicio != null ) {
                            sFechaInicio = fechaInicio.get( Calendar.DAY_OF_MONTH ) + " de " + ConstantesWeb.asNomMeses[fechaInicio.get( Calendar.MONTH )];
                        } else {
                            sFechaInicio = " -- ";
                        }
                        if ( fechaFin != null ) {
                            sFechaFin = fechaFin.get( Calendar.DAY_OF_MONTH ) + " de " + ConstantesWeb.asNomMeses[fechaFin.get( Calendar.MONTH )] + " del " + fechaFin.get( Calendar.YEAR );
                        } else {
                            sFechaFin = " -- ";
                        }

                        clArbModuloCur = CommonDAO.getClArbolAcademicoDAO().buscarArbolPorId( w_mod_id );

                        lstSecLlevadasPorModObj = CommonDAO.getClSeccionDAO().traerSeccionesValidasPorModuloId( w_mod_id, w_alu_id, 0 );
                        i = 0;
                        adNotas = new Double[ 0 ];
                        if ( !lstSecLlevadasPorModObj.isEmpty() ) {
                            adNotas = new Double[ lstSecLlevadasPorModObj.size() ];
                            for ( Object object : lstSecLlevadasPorModObj ) {
                                aobjCurData = (Object[]) object;
                                if ( aobjCurData[5] != null ) {
                                    adNotas[i++] = Double.parseDouble( aobjCurData[5].toString() );
                                } else {
                                    adNotas[i++] = 0.0;
                                }
                            }
                        }

                        dPromedio = (double) Math.round( CommonWeb.calcularPromedioSimple( adNotas ) );
                        sNomReporte = "rep_certificado_estudios_modular_nuevo.jasper";
                        hmParams.put( "p_mod_descripcion", clArbModuloCur.getArbDescripcion() );
                        hmParams.put( "p_tipo_centro", b_tipo_centro );
                        hmParams.put( "p_articulo", b_articulo );
                        hmParams.put( "p_alu_appaterno", alumno.getAluAppaterno() );
                        hmParams.put( "p_alu_apmaterno", alumno.getAluApmaterno() );
                        hmParams.put( "p_alu_nombres", alumno.getAluNombres() );
                        hmParams.put( "p_fecha_inicio", sFechaInicio );
                        hmParams.put( "p_fecha_fin", sFechaFin );
                        hmParams.put( "p_num_horas", w_cant_horas + " horas pedagógicas" );
                        hmParams.put( "p_con_nota", ", con nota " + numFormat.format( dPromedio ) );
                        hmParams.put( "p_resolucion", "080-2012-PCG-UCH" );
                        hmParams.put( "p_fecha_actual", "Lima, " + ConstantesWeb.asNomMeses[fechaActual.get( Calendar.MONTH )] + " de " + fechaActual.get( Calendar.YEAR ) );
                        hmParams.put( "p_alu_id", String.valueOf( w_alu_id ) );
                        hmParams.put( "p_arb_mod_id", String.valueOf( w_mod_id ) );
                        
                        break;
                    case ConstantesWeb.RPT_CERT_EST_POR_CURSO:
                        clArbModuloCur = CommonDAO.getClArbolAcademicoDAO().buscarArbolPorId( w_cur_id );
                        lstSecLlevadasPorModObj = CommonDAO.getClSeccionDAO().traerSeccionesValidasPorModuloId( w_mod_id, w_alu_id, w_cur_id );
                        aobjCurData = (Object[]) lstSecLlevadasPorModObj.get( 0 );
                        iSecId= Integer.parseInt(aobjCurData[3].toString());
                        fecha = (Date) formatter.parse( aobjCurData[4].toString() );
                        fechaInicio = Calendar.getInstance();
                        fechaInicio.setTime( fecha );

                        fecha = (Date) formatter.parse( aobjCurData[8].toString() );
                        fechaFin = Calendar.getInstance();
                        fechaFin.setTime( fecha );
                        if ( fechaInicio != null ) {
                            sFechaInicio = fechaInicio.get( Calendar.DAY_OF_MONTH ) + " de " + ConstantesWeb.asNomMeses[fechaInicio.get( Calendar.MONTH )];
                        } else {
                            sFechaInicio = " -- ";
                        }
                        if ( fechaFin != null ) {
                            sFechaFin = fechaFin.get( Calendar.DAY_OF_MONTH ) + " de " + ConstantesWeb.asNomMeses[fechaFin.get( Calendar.MONTH )] + " del " + fechaFin.get( Calendar.YEAR );
                        } else {
                            sFechaFin = " -- ";
                        }

                        sPromedio = aobjCurData[5] == null ? "-" : aobjCurData[5].toString();

                        sNomReporte = "rep_certificado_estudios_curso_nuevo.jasper";
                        hmParams.put( "p_cur_descripcion", clArbModuloCur != null ? clArbModuloCur.getArbDescripcion() : "" );
                        hmParams.put( "p_articulo", b_articulo );
                        hmParams.put("p_seccion",iSecId);
                        hmParams.put("p_id_modulo",w_mod_id);
                        hmParams.put( "p_tipo_centro", b_tipo_centro );
                        hmParams.put( "p_alu_appaterno", alumno.getAluAppaterno() );
                        hmParams.put( "p_alu_apmaterno", alumno.getAluApmaterno() );
                        hmParams.put( "p_alu_nombres", alumno.getAluNombres() );
                        hmParams.put( "p_alu_dni", alumno.getAluDni());
                        hmParams.put( "p_alu_sexo", alumno.getAluSexo());
                        hmParams.put( "p_fecha_inicio", sFechaInicio );
                        hmParams.put( "p_fecha_fin", sFechaFin );
                        hmParams.put( "p_num_horas", w_cant_horas + " horas pedagógicas" );
                        hmParams.put( "p_con_nota", ", con nota " + sPromedio );
                        hmParams.put( "p_resolucion", "080-2012-PCG-UCH" );
                        hmParams.put( "p_fecha_actual", "Los Olivos, " + ConstantesWeb.asNomMeses[fechaActual.get( Calendar.MONTH )] + " de " + fechaActual.get( Calendar.YEAR ) );
                        hmParams.put( "p_alu_id", String.valueOf( w_alu_id ) );
                        ServletContext ctx2 = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                        URL FileImagen2 = ctx2.getResource("/Imagenes/actions/logo_p.jpg" );
                        if(b_tipo_centro.equals("CENTRO DE IDIOMAS")){
                            FileImagen3 = ctx2.getResource("/Imagenes/actions/idiomas.jpg" );
                        }else if(b_tipo_centro.equals("CENTRO DE INFORMÁTICA")){
                            FileImagen3 = ctx2.getResource("/Imagenes/actions/inf.jpg" );
                        }else{
                            FileImagen3 = ctx2.getResource("/Imagenes/actions/cap.jpg" );
                        }
                        hmParams.put("p_logo2", FileImagen3); 
                        hmParams.put("p_logo", FileImagen2); 
                        break;

                    case ConstantesWeb.RPT_CONST_ESTUDIOS:
                        sNomReporte = "rep_constancia_estudio.jasper";
                        hmParams.put( "p_alu_id", String.valueOf( w_alu_id ) );
                        hmParams.put( "p_mod_id", String.valueOf( w_mod_id ) );
                        break;
                    default:
                        sNomReporte = "";
                        break;
                }
            } else {
                sNomReporte = "";
            }
            try {

                if ( !sNomReporte.isEmpty() ) {

                    fc = FacesContext.getCurrentInstance();
                    reportStream = fc.getExternalContext().getResourceAsStream( "/WEB-INF/Reportes/cursos_libres/" + sNomReporte );

                    dmds = (DriverManagerDataSource) ServiceFinder.findBean( "dataSource" );
                    conn = dmds.getConnection();
                    //loadParameters();

//                    jasperPrint = JasperFillManager.fillReport( reportStream, hmParams, conn );
                    jasperPrint = JasperFillManager.fillReport( reportStream, hmParams, conn );
                    conn.close();
                } else {
                    return null;
                }
            } catch ( Exception e ) {
                System.out.println( "EX " + e );
                System.out.println( "EX " + e.getMessage() );
                e.printStackTrace();
            }

            return jasperPrint;

        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean validarReporte( int iTipoReporte ) {
        boolean blValido = true;
        Object[] objRegSec;
        List lstSecLlevadasPorModObj;

        List<ClArbolAcademico> lstArbolAcadCursos;
        Map<Integer, ClArbolAcademico> hmArbolAcadCursos;
        Map<Integer, Object[]> hmCursosEstudiados;


        switch ( iTipoReporte ) {
            case ConstantesWeb.RPT_BOLETA_NOTAS:
                break;
            case ConstantesWeb.RPT_CERT_EST_MODULAR:
                hmArbolAcadCursos = new HashMap<Integer, ClArbolAcademico>();
                hmCursosEstudiados = new HashMap<Integer, Object[]>();
                lstArbolAcadCursos = CommonDAO.getClArbolAcademicoDAO().listarArbolPorPadre( w_mod_id );
                if ( !lstArbolAcadCursos.isEmpty() ) {
                    for ( ClArbolAcademico curso : lstArbolAcadCursos ) {
                        hmArbolAcadCursos.put( curso.getArbId(), curso );
                    }

                    lstSecLlevadasPorModObj = CommonDAO.getClSeccionDAO().traerSeccionesValidasPorModuloId( w_mod_id, w_alu_id, 0 );
                    if ( !lstSecLlevadasPorModObj.isEmpty() ) {
                        w_cant_horas = 0;
                        for ( Object obj : lstSecLlevadasPorModObj ) {
                            objRegSec = (Object[]) obj;
                            if ( objRegSec[5] == null ) {
                                blValido = false;
                                oncomplete = "Le falta nota en uno o más cursos.";
                                return blValido;
                            }
                            w_cant_horas += objRegSec[6] == null ? 0 : (Integer) objRegSec[6];
                            hmCursosEstudiados.put( (Integer) objRegSec[0], objRegSec );
                        }
                    }
                    for ( Integer iCurId : hmArbolAcadCursos.keySet() ) {
                        if ( !hmCursosEstudiados.containsKey( iCurId ) ) {
                            blValido = false;
                            oncomplete = "Debe completar todos los cursos del módulo para emitir este certificado.";
                            return blValido;
                        }
                    }

                }
                break;
            case ConstantesWeb.RPT_CERT_EST_POR_CURSO:
                break;
            case ConstantesWeb.RPT_CONST_ESTUDIOS:
                break;
            case ConstantesWeb.RPT_FICHA_MATRICULA:
                break;
        }
        return blValido;
    }
}
