/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSAlumnoCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSHorarioDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSSistemaEvaluacionCLDAO;
import net.uch.cursoLibre.logicreport.UtilJasper;
import net.uch.cursoLibre.managedBeans.beans.BeanCLAsistencia;
import net.uch.cursoLibre.managedBeans.beans.BeanClAlumno;
import net.uch.cursoLibre.managedBeans.beans.BeanReporte;
import net.uch.cursoLibre.managedBeans.beans.Column;
import net.uch.mapping.AcDocente;
import net.uch.mapping.ClArbolAcademico;
import net.uch.mapping.ClArbolAperturado;
import net.uch.mapping.ClHoraria;
import net.uch.mapping.ClSeccion;
import net.uch.util.CommonDAO;
import net.uch.util.CommonWeb;
import net.uch.utilAdministrativo.MetodosAdm;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author LUIS
 */
public class MBAsistenciaCL {

    private SelectItem[] cboSede;
    private SelectItem[] cboAno;
    private SelectItem[] cboArea;
    private SelectItem[] cboModulo;
    private SelectItem[] cboCurso;
    private SelectItem[] cboSeccion;
    private SelectItem[] cboAsignaturas;
    private boolean blModular = false;
    private int w_sed_id = 0;
    private String w_ano_id = "0";
    private int w_area_id = 0;
    private int w_mod_id = 0;
    private int w_cur_id = 0;
    private int w_sec_id = 0;
    private int w_alu_id = 0;
    private int inumero = 0;
    public  int  AlumId=0; 
    private String w_rad_id;
    private Date w_fecha_ini;
    private Date w_fecha_fin;
    private String w_horario;
    Boolean type;
    Boolean description;
    Integer maxValue;
    private String w_curso;
    private String w_docente;
    private int iIdAsignatura;
    List<BeanCLAsistencia> lstAlumnos;
    private HashMap<String, String> m_hmParameters;
    private ActionEvent event;
    
    
    private String m_sEstilo;

    public String getEstilo() {
        return m_sEstilo;
    }

    public void setEstilo( String sEstilo ) {
        this.m_sEstilo = sEstilo;
    }

    public boolean isBlModular() {
        return blModular;
    }

    public void setBlModular( boolean blModular ) {
        this.blModular = blModular;
    }

    public int getW_alu_id() {
        return w_alu_id;
    }

    public void setW_alu_id( int w_alu_id ) {
        this.w_alu_id = w_alu_id;
    }

    public int getIdAsignatura() {
        return iIdAsignatura;
    }

    public void setIdAsignatura( int idAsignatura ) {
        this.iIdAsignatura = idAsignatura;
    }

    public int getNumero() {
        return inumero++;
    }

    public void setNumero( int inumero ) {
        this.inumero = inumero;
    }

    public SelectItem[] getCboAsignaturas() {
        Object[] objObj;
        List lstSisEvaDetalles;
        lstSisEvaDetalles = CommonDAO.getClSistemaEvaluacionDAO().listarSisEvaDetallePorSecId( w_sec_id );

        cboAsignaturas = new SelectItem[ lstSisEvaDetalles.size() + 1 ];
        cboAsignaturas[0] = new SelectItem( "0", "[Seleccione Asignatura]" );
        for ( int i = 0; i < lstSisEvaDetalles.size(); i++ ) {
            objObj = (Object[])lstSisEvaDetalles.get( i );
            cboAsignaturas[i + 1] = new SelectItem( objObj[0], objObj[2].toString() );
        }
        return cboAsignaturas;
    }

    public void setCboAsignaturas( SelectItem[] cboAsignaturas ) {
        this.cboAsignaturas = cboAsignaturas;
    }

    public SelectItem[] getCboSede() throws SQLException {
        MetodosAdm metodo = new MetodosAdm();
        this.setCboSede( metodo.listarSedes( "[[--Seleccione--]]" ) );
        return cboSede;
    }

    public void setCboSede( SelectItem[] cboSede ) {
        this.cboSede = cboSede;
    }

    public SelectItem[] getCboAno() {
        int iAnio;

        iAnio = Calendar.getInstance().get( Calendar.YEAR );
        this.cboAno = new SelectItem[ 15 ];
        this.cboAno[0] = new SelectItem( 0, "[[--Seleccione--]]" );
        for ( int i = 0; i < ( this.cboAno.length - 1 ); i++ ) {
            this.cboAno[i + 1] = new SelectItem( iAnio - i, String.valueOf( iAnio - i ) );
        }
        return cboAno;
    }

    public void setCboAno( SelectItem[] cboAno ) {
        this.cboAno = cboAno;
    }

    public SelectItem[] getCboArea() {
        ClArbolAcademico arbArea;
        List<ClArbolAcademico> lstArbAreas = new ArrayList();

        try {

//            if ( !"".equals( w_ano_id ) && Integer.parseInt( w_ano_id ) > 0 && w_sed_id > 0 ) {
//                lstArbAreas = CommonDAO.getClArbolAcademicoDAO().listarAreaxAnoYSede( Integer.parseInt( w_ano_id ), w_sed_id, 0 );
            lstArbAreas = CommonDAO.getClArbolAcademicoDAO().listarArbolPorPadre( 0 );
//            }
            cboArea = new SelectItem[ lstArbAreas.size() + 1 ];
            cboArea[0] = new SelectItem( 0, "[[Seleccione Área]]" );
            for ( int i = 0; i < ( cboArea.length - 1 ); i++ ) {
                arbArea = lstArbAreas.get( i );
                cboArea[i + 1] = new SelectItem( arbArea.getArbId(), arbArea.getArbDescripcion() );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return cboArea;
    }

    public void setCboArea( SelectItem[] cboArea ) {
        this.cboArea = cboArea;
    }

    public SelectItem[] getCboModulo() {
        ClArbolAcademico arbMod;
        List<ClArbolAcademico> lstArbModulos = new ArrayList();
        try {
//            if ( w_area_id != 0 && !"".equals( w_ano_id ) && Integer.parseInt( w_ano_id ) > 0 && w_sed_id > 0 ) {
//                lstArbModulos = CommonDAO.getClArbolAcademicoDAO().listarModulosxAreaAnoYSede( w_area_id, Integer.parseInt( w_ano_id ), w_sed_id );
            if ( w_area_id != 0 ) {
                lstArbModulos = CommonDAO.getClArbolAcademicoDAO().listarArbolPorPadre( w_area_id );
            }
//            }
            cboModulo = new SelectItem[ lstArbModulos.size() + 1 ];
            cboModulo[0] = new SelectItem( 0, "[[Seleccione Modulo]]" );
            for ( int i = 0; i < ( cboModulo.length - 1 ); i++ ) {
                arbMod = lstArbModulos.get( i );
                cboModulo[i + 1] = new SelectItem( arbMod.getArbId(), arbMod.getArbDescripcion() );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return cboModulo;
    }

    public void setCboModulo( SelectItem[] cboModulo ) {
        this.cboModulo = cboModulo;
    }

    public SelectItem[] getCboCurso() {
        ClArbolAcademico arbCur;
        List<ClArbolAcademico> lstArbCursos = new ArrayList<ClArbolAcademico>();
        try {
//            if ( w_mod_id > 0 && w_area_id > 0 && w_sed_id > 0 && !"".equals( w_ano_id ) && Integer.parseInt( w_ano_id ) > 0 ) {
//                lstArbCursos = CommonDAO.getClArbolAcademicoDAO().listarCursosXModAnoYSede( w_mod_id, Integer.parseInt( w_ano_id ), w_sed_id );
            if ( w_mod_id != 0 ) {
                lstArbCursos = CommonDAO.getClArbolAcademicoDAO().listarArbolPorPadre( w_mod_id );
            }
//            }
            cboCurso = new SelectItem[ lstArbCursos.size() + 1 ];
            cboCurso[0] = new SelectItem( "0", "[[--Seleccione--]])" );
            for ( int i = 0; i < ( cboCurso.length - 1 ); i++ ) {
                arbCur = lstArbCursos.get( i );
                cboCurso[i + 1] = new SelectItem( arbCur.getArbId(), arbCur.getArbDescripcion() );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return cboCurso;
    }

    public void setCboCurso( SelectItem[] cboCurso ) {
        this.cboCurso = cboCurso;
    }

    public SelectItem[] getCboSeccion() {
        ClSeccion clSeccion;
        List<ClSeccion> lstSecciones = new ArrayList();
        try {
            if ( w_sed_id > 0 && w_cur_id > 0 && w_mod_id > 0 && !"".equals( w_ano_id ) && Integer.parseInt( w_ano_id ) > 0 ) {
                lstSecciones = CommonDAO.getClArbolAcademicoDAO().listarSeccionesXCursoAnoYSede( w_cur_id, Integer.parseInt( w_ano_id ), w_sed_id );
            }
            cboSeccion = new SelectItem[ lstSecciones.size() + 1 ];
            cboSeccion[0] = new SelectItem( 0, "[[--Seleccione--]]" );
            for ( int i = 0; i < ( cboSeccion.length - 1 ); i++ ) {
                clSeccion = lstSecciones.get( i );
                cboSeccion[i + 1] = new SelectItem( clSeccion.getSecId(), clSeccion.getSecNombre() );

            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return cboSeccion;
    }

    public void setCboSeccion( SelectItem[] cboSeccion ) {
        this.cboSeccion = cboSeccion;
    }

    public boolean isEsProgAux() {
        String sModular;
        try {
            sModular = CommonDAO.getClArbolAcademicoDAO().buscarArbolPorId( w_area_id ).getArbAreaModular();
        } catch ( Exception ex ) {
            sModular = "0";
        }
//        boolean blEsProgAux = w_area_id == ConstantesWeb.ID_AREA_PROG_AUX;
        boolean blEsProgAux = w_area_id > 0 && "1".equals( sModular );
        return blEsProgAux;
    }
    /*
     *
     */
    private List<BeanClAlumno> listaAlumno = new ArrayList<BeanClAlumno>();
    /*
     * variables tabla
     */
    private String oncomplete;

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete( String sOncomplete ) {
        this.oncomplete = sOncomplete;
    }

    public String getW_curso() {
        return w_curso;
    }

    public void setW_curso( String sWCurso ) {
        this.w_curso = sWCurso;
    }

    public String getW_docente() {
        return w_docente;
    }

    public void setW_docente( String sWDocente ) {
        this.w_docente = sWDocente;
    }

    public List<BeanClAlumno> getListaAlumno() {
        return listaAlumno;
    }

    public void setListaAlumno( List<BeanClAlumno> lstAlumno ) {
        this.listaAlumno = lstAlumno;
    }

    public String getW_ano_id() {
        return w_ano_id;
    }

    public void setW_ano_id( String w_ano_id ) {
        this.w_ano_id = w_ano_id;
    }

    public int getW_area_id() {
        return w_area_id;
    }

    public void setW_area_id( int w_area_id ) {
        this.w_area_id = w_area_id;
    }

    public int getW_cur_id() {
        return w_cur_id;
    }

    public void setW_cur_id( int w_cur_id ) {
        this.w_cur_id = w_cur_id;
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

    public String getW_horario() {
        return w_horario;
    }

    public void setW_horario( String w_horario ) {
        this.w_horario = w_horario;
    }

    public int getW_mod_id() {
        return w_mod_id;
    }

    public void setW_mod_id( int w_mod_id ) {
        this.w_mod_id = w_mod_id;
    }

    public String getW_rad_id() {
        return w_rad_id;
    }

    public void setW_rad_id( String w_rad_id ) {
        this.w_rad_id = w_rad_id == null || w_rad_id.trim().isEmpty() ? "0" : w_rad_id;
    }

    public int getW_sec_id() {
        return w_sec_id;
    }

    public void setW_sec_id( int w_sec_id ) {
        this.w_sec_id = w_sec_id;
    }

    public int getW_sed_id() {
        return w_sed_id;
    }

    public void setW_sed_id( int w_sed_id ) {
        this.w_sed_id = w_sed_id;
    }
    private List<BeanReporte> lstClases;
    private List<BeanReporte> m_lstSesiones;//columnas
    private Map<Long, BeanCLAsistencia> hmListaAlumnos;
    private Map<Long, BeanCLAsistencia> hmListaAsisAlumno;
    
    public List<BeanCLAsistencia> getLstAlumnos() {
        if ( hmListaAlumnos == null ) {
            hmListaAlumnos = new HashMap<Long, BeanCLAsistencia>();
        }
        inumero = 1;
        return new ArrayList<BeanCLAsistencia>( hmListaAlumnos.values() );
    }

    public void setLstAlumnos( List<BeanCLAsistencia> lstAlumnos ) {
    }
    
    public List<BeanCLAsistencia> getLstAsisAlumno() {
        if ( hmListaAsisAlumno == null ) {
            hmListaAsisAlumno = new HashMap<Long, BeanCLAsistencia>();
        }

        return new ArrayList<BeanCLAsistencia>( hmListaAsisAlumno.values() );
    }

    public void setLstAsisAlumno( List<BeanCLAsistencia> lstAlumnos ) {
    }

    public List<BeanReporte> getLstSesiones() {
        return m_lstSesiones;
    }

    public void setLstSesiones( List<BeanReporte> lstSesiones ) {
        this.m_lstSesiones = lstSesiones;
    }

    public void listaAsistenciaPorSeccion( ActionEvent event ) {
//        m_imodular="1".equals( clSeccion.getClArbolAcademico().getArbAcadPadre().getArbAcadPadre().getArbAcadPadre().getArbAreaModular() );
        BeanReporte bRep;
        List<BeanCLAsistencia> listaAlumnos;
        ArrayList<String> listadeCodigo;
        Long iIdAlum = 0L;
        try {
            ClSeccion clSeccion = CommonDAO.getClSeccionDAO().seleccionarSeccion( w_sec_id );
            blModular = "1".equals( clSeccion.getClArbolAcademico().getArbAcadPadre().getArbAcadPadre().getArbAcadPadre().getArbAreaModular() );

            m_lstSesiones = CommonDAO.getClAsistenciaDAO().listarSesionesPorSecId( w_sec_id );
            //  listaAlumnos = CommonDAO.getClAsistenciaDAO().listarAsitenciaPorSecId( w_sec_id, blModular );
            listaAlumnos = CommonDAO.getClAsistenciaDAO().listarAsitenciaPorSecId( w_sec_id );
            hmListaAlumnos = new HashMap<Long, BeanCLAsistencia>();
            int ind = 0;
            int size = m_lstSesiones.size();
            String codigo = "";
            String codigoA = "";
            listadeCodigo = new ArrayList<String>();
            for ( BeanCLAsistencia clAsis : listaAlumnos ) {
                iIdAlum = Long.parseLong( "" + clAsis.getAlumId() );
                if ( !hmListaAlumnos.containsKey( iIdAlum ) ) {
                    hmListaAlumnos.put( iIdAlum, clAsis );
                }
                if ( hmListaAlumnos.get( iIdAlum ).getHmAsistencias() == null ) {
                    hmListaAlumnos.get( iIdAlum ).setHmAsistencias( new HashMap<Integer, String>() );
                    for ( int i = 0; i < size; i++ ) {
                        hmListaAlumnos.get( iIdAlum ).getHmAsistencias().put( CommonWeb.parseObjToInt( m_lstSesiones.get( i ).getExpr2() ), "-" );
                    }
                }
                bRep = new BeanReporte();
                hmListaAlumnos.get( iIdAlum ).getHmAsistencias().put( clAsis.getSesId(), clAsis.getAlumAsistencia() );
            }

        } catch ( Exception ex ) {
            ex.printStackTrace();
            m_lstSesiones = new ArrayList<BeanReporte>();
        }
    }

    public void listaAsistenciaPorAlumno( ActionEvent event ) {
        BeanReporte bRep;
        List<BeanCLAsistencia> listaAlumnos;
        ArrayList<String> listadeCodigo;
        Long iIdAlum = 0L;
        try {
            ClSeccion clSeccion = CommonDAO.getClSeccionDAO().seleccionarSeccion( w_sec_id );
            blModular = "1".equals( clSeccion.getClArbolAcademico().getArbAcadPadre().getArbAcadPadre().getArbAcadPadre().getArbAreaModular() );

            m_lstSesiones = CommonDAO.getClAsistenciaDAO().listarSesionesPorSecId( w_sec_id );
            //  listaAlumnos = CommonDAO.getClAsistenciaDAO().listarAsitenciaPorSecId( w_sec_id, blModular );
            int p_alum_id = CommonWeb.parseObjToInt( CommonWeb.getParamFromUIFacesContext( event, "p_alum_id" ) );
            AlumId=p_alum_id;
            listaAlumnos = CommonDAO.getClAsistenciaDAO().listarAsitenciaPorAlumId( w_sec_id, blModular, p_alum_id );
            hmListaAsisAlumno = new HashMap<Long, BeanCLAsistencia>();
            int ind = 0;
            int size = m_lstSesiones.size();
            String codigo = "";
            String codigoA = "";
            listadeCodigo = new ArrayList<String>();
            for ( BeanCLAsistencia clAsis : listaAlumnos ) {
                if ( blModular ) {
                    codigoA = "" + clAsis.getAlumId() + clAsis.getSesId();
                    if ( listadeCodigo.contains( codigoA ) ) {
                        ind++;
                        iIdAlum = Long.parseLong( "" + clAsis.getAlumId() + ( ind ) );
                    } else {
                        ind = 0;
                        iIdAlum = Long.parseLong( "" + clAsis.getAlumId() + ind );

                    }
                    listadeCodigo.add( codigoA );

                } else {
                    iIdAlum = Long.parseLong( "" + clAsis.getAlumId() );
                }
                if ( !hmListaAsisAlumno.containsKey( iIdAlum ) ) {
                    hmListaAsisAlumno.put( iIdAlum, clAsis );
                }
                if ( hmListaAsisAlumno.get( iIdAlum ).getHmAsistencias() == null ) {
                    hmListaAsisAlumno.get( iIdAlum ).setHmAsistencias( new HashMap<Integer, String>() );
                    for ( int i = 0; i < size; i++ ) {
                        hmListaAsisAlumno.get( iIdAlum ).getHmAsistencias().put( CommonWeb.parseObjToInt( m_lstSesiones.get( i ).getExpr2() ), "-" );
                    }
                }
                bRep = new BeanReporte();
                hmListaAsisAlumno.get( iIdAlum ).getHmAsistencias().put( clAsis.getSesId(), clAsis.getAlumAsistencia() );

            }
            inumero = 0;

        } catch ( Exception ex ) {
            ex.printStackTrace();
            m_lstSesiones = new ArrayList<BeanReporte>();
        }
    }

    public void imprimirFicha( ActionEvent event ) throws Exception {
        setOncomplete( "" );
        m_hmParameters = new HashMap<String, String>();
        m_hmParameters.put( "titulo", "REPORTE ASISTENCIA DE ESTUDIANTES" );
        this.setOncomplete( "javascript:Richfaces.showModalPanel('mpReporte')" );
        if ( m_hmParameters != null ) {
        } else {
            this.setOncomplete( "javascript:alert('Hace falta especificar algunos parametros ..!!!')" );
        }
    }

    public void cargarReporte( OutputStream out, Object data ) throws IOException, Exception, EOFException {
        cargarReporteDynamic( out );
    }

    public void cargarReporteDynamic( OutputStream out ) throws IOException, Exception, EOFException {

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream( cargar_reporteDynamic(), buffer );
        byte[] bytes = buffer.toByteArray();
        InputStream input = new ByteArrayInputStream( bytes );
        int size = input.available();
        byte[] pdf = new byte[ size ];
        input.read( pdf );
        out.write( pdf );
        buffer.flush();
        buffer.close();
        input.close();
        out.flush();
        out.close();

    }

    private JasperPrint cargar_reporteDynamic() {
        JasperPrint jasperPrint;
        jasperPrint = null;
        try {
            jasperPrint = generateReporteAsistencia();
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return jasperPrint;
    }

    public JasperPrint generateReporteAsistencia() {
        String sMotivos;
        ClArbolAcademico arbAux;
        FastReportBuilder report;
        Style header;
        Style headerAlu;
        Style grupo;
        List<BeanReporte> lstAlumnosAsistencia;

        report = new FastReportBuilder();
        report.setLeftMargin( 30 ).
                setRightMargin( 30 ).
                setTopMargin( 30 ).
                setBottomMargin( 30 ).
                setDetailHeight( 20 ).
                setHeaderHeight( 15 ).
                setSubtitleHeight( 10 ).
                setPageSizeAndOrientation( Page.Page_A4_Portrait() );

        try {
            lstAlumnosAsistencia = CommonDAO.getClAsistenciaDAO().listarAsitenciaPorSecIdReporte( w_sec_id );
            ClSeccion clSeccion = CommonDAO.getClSeccionDAO().seleccionarSeccion( w_sec_id );
            if ( !lstAlumnosAsistencia.isEmpty() ) {
                sMotivos = "";
                arbAux = CommonDAO.getClArbolAcademicoDAO().buscarArbolPorId( w_area_id );
                report.addAutoText( "CENTRO   : " + CommonDAO.getTbCatalogoDAO().seleccionarDescripcion( arbAux.getArbInstitucion() ), AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 300 );
                report.addAutoText( "ÁREA   : " + arbAux.getArbDescripcion(), AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 300 );
                arbAux = CommonDAO.getClArbolAcademicoDAO().buscarArbolPorId( w_mod_id );
                report.addAutoText( "MODULO   : " + arbAux.getArbDescripcion(), AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 300 );
                report.addAutoText( "", AutoText.POSITION_HEADER, AutoText.ALIGMENT_RIGHT, 300 );
                report.addAutoText( "SECCION   : " + clSeccion.getSecNombre(), AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 400 );

                report.addAutoText( "", AutoText.POSITION_HEADER, AutoText.ALIGMENT_RIGHT, 300 );
            } else {
                report.addAutoText( "No hay aluymnos matriculados", AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 300 );
            }

            header = new Style();
            header.setBorder( Border.THIN );
            header.setHorizontalAlign( HorizontalAlign.CENTER );

            headerAlu = new Style();
            headerAlu.setBorder( Border.THIN );
            headerAlu.setHorizontalAlign( HorizontalAlign.LEFT );

            grupo = UtilJasper.createStyleOrientacion( HorizontalAlign.LEFT, 1 );




            report.addColumn( "CÓDIGO", "expr3", String.class.getName(), 10, header );
            report.addColumn( "Nombres y Apellidos", "expr5", String.class.getName(), 35, headerAlu );
            report.addColumn( "Fecha de Sesion", "expr4", String.class.getName(), 15, header );
            report.addColumn( "Asistencia", "expr6", String.class.getName(), 20, header );
//            report.addColumn( "MOTIVOS", "expr14", String.class.getName(), 10, header );
//            report.addColumn( "OBS. MOTIVOS", "expr15", String.class.getName(), 15, header );

            report.setUseFullPageWidth( true );

            DynamicReport dr = report.build();
            JRDataSource ds = new JRBeanCollectionDataSource(lstAlumnosAsistencia);

            JasperPrint jp = DynamicJasperHelper.generateJasperPrint( dr, new ClassicLayoutManager(), ds );

            return jp;

        } catch ( JRException e ) {
            e.printStackTrace();
        } catch ( ColumnBuilderException e ) {
            e.printStackTrace();
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return null;
    }
    public void exportToExcel() throws Exception {
        JasperPrint jasperPrint;
        JRXlsExporter exporter;
        String sFileName;
        String sFilePath;
        File outputFile;
        FileInputStream archivo;
        FileOutputStream fos;
        HttpServletResponse response;
        ServletOutputStream ouputStream;
        byte[] datos;
        String[] sheetNames = { "Hoja 1" };
        sFileName = "Reporte.xls";
        sFilePath = System.getProperty( "user.dir" ) + File.separator + sFileName;
        outputFile = new File( sFilePath );
        fos = new FileOutputStream( outputFile );
        

        jasperPrint = generateReporteAsistencia();

        exporter = new JRXlsExporter();

        //Indicamos el objeto JasperPrint que deseamos exportar
        exporter.setParameter( JRExporterParameter.JASPER_PRINT, jasperPrint );
        //Indicamos el fichero donde vamos a exportar el informe
        exporter.setParameter( JRExporterParameter.OUTPUT_STREAM, fos ); //and output stream
        //Indicamos si queremos una página por Sheet
        exporter.setParameter( JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE );
        //Indicamos si deseamos eliminar los espacios vacíos entre filas
        exporter.setParameter( JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE );
        //Indicamos si quremos mostrar una página en blanco como fondo
        exporter.setParameter( JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE );
        exporter.setParameter( JRXlsExporterParameter.SHEET_NAMES, sheetNames );
        exporter.exportReport();

        response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType( "application/vnd.ms-excel" );
        response.setHeader( "Content-Disposition", "attachment;filename=" + sFileName );
        response.addHeader( "Content-Type", "application/force-download" );

        archivo = new FileInputStream( sFilePath );
        datos = new byte[ archivo.available() ];
        archivo.read( datos );
        archivo.close();
        ouputStream = response.getOutputStream();
        ouputStream.write( datos );
        ouputStream.flush();
        ouputStream.close();
        FacesContext.getCurrentInstance().responseComplete();

        if ( new File( sFilePath ).delete() ) {
            System.out.println( "se borro el archivo" );
        }

    }
    
    // Reporte por estudiante
    
     public void imprimirFichaAlumno( ActionEvent event ) throws Exception {
        setOncomplete( "" );
        m_hmParameters = new HashMap<String, String>();
        m_hmParameters.put( "titulo2", "REPORTE ASISTENCIA DE ALUMNO" );
        this.setOncomplete( "javascript:Richfaces.showModalPanel('mpReporteAlumno')" );
        if ( m_hmParameters != null ) {
        } else {
            this.setOncomplete( "javascript:alert('Hace falta especificar algunos parametros ..!!!')" );
        }
    }

    public void cargarReporteAlumno( OutputStream out, Object data ) throws IOException, Exception, EOFException {
        cargarReporteDynamicAlumno( out );
    }

    public void cargarReporteDynamicAlumno( OutputStream out ) throws IOException, Exception, EOFException {

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream( cargar_reporteDynamicAlumno(), buffer );
        byte[] bytes = buffer.toByteArray();
        InputStream input = new ByteArrayInputStream( bytes );
        int size = input.available();
        byte[] pdf = new byte[ size ];
        input.read( pdf );
        out.write( pdf );
        buffer.flush();
        buffer.close();
        input.close();
        out.flush();
        out.close();

    }

    private JasperPrint cargar_reporteDynamicAlumno() {
        JasperPrint jasperPrint;
        jasperPrint = null;
        try {
            jasperPrint = generateReporteAsistenciaAlumno();
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return jasperPrint;
    }

    public JasperPrint generateReporteAsistenciaAlumno() {
        ClArbolAcademico arbAux;
        FastReportBuilder report;
        Style header;
        Style headerAlu;
        Style grupo;
        List<BeanReporte> lstAlumnosAsistencia;

        report = new FastReportBuilder();
        report.setLeftMargin( 30 ).
                setRightMargin( 30 ).
                setTopMargin( 30 ).
                setBottomMargin( 30 ).
                setDetailHeight( 20 ).
                setHeaderHeight( 15 ).
                setSubtitleHeight( 10 ).
                setPageSizeAndOrientation( Page.Page_A4_Portrait() );
        int p_alum_id = AlumId;

        try {
            
            lstAlumnosAsistencia = CommonDAO.getClAsistenciaDAO().listarAsitenciaPorAlumIdReporte( w_sec_id, p_alum_id );
            ClSeccion clSeccion = CommonDAO.getClSeccionDAO().seleccionarSeccion( w_sec_id );
            if ( !lstAlumnosAsistencia.isEmpty() ) {
                arbAux = CommonDAO.getClArbolAcademicoDAO().buscarArbolPorId( w_area_id );
                report.addAutoText( "CENTRO   : " + CommonDAO.getTbCatalogoDAO().seleccionarDescripcion( arbAux.getArbInstitucion() ), AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 300 );
                report.addAutoText( "ÁREA   : " + arbAux.getArbDescripcion(), AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 300 );
                arbAux = CommonDAO.getClArbolAcademicoDAO().buscarArbolPorId( w_mod_id );
                report.addAutoText( "MODULO   : " + arbAux.getArbDescripcion(), AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 300 );
                report.addAutoText( "", AutoText.POSITION_HEADER, AutoText.ALIGMENT_RIGHT, 300 );
                report.addAutoText( "SECCION   : " + clSeccion.getSecNombre(), AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 400 );

                report.addAutoText( "", AutoText.POSITION_HEADER, AutoText.ALIGMENT_RIGHT, 300 );
            } else {
                report.addAutoText( "No hay aluymnos matriculados", AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 300 );
            }

            header = new Style();
            header.setBorder( Border.THIN );
            header.setHorizontalAlign( HorizontalAlign.CENTER );

            headerAlu = new Style();
            headerAlu.setBorder( Border.THIN );
            headerAlu.setHorizontalAlign( HorizontalAlign.LEFT );

            grupo = UtilJasper.createStyleOrientacion( HorizontalAlign.LEFT, 1 );




            report.addColumn( "CÓDIGO", "expr3", String.class.getName(), 10, header );
            report.addColumn( "Nombres y Apellidos", "expr5", String.class.getName(), 35, headerAlu );
            report.addColumn( "Fecha de Sesion", "expr4", String.class.getName(), 15, header );
            report.addColumn( "Asistencia", "expr6", String.class.getName(), 20, header );
//            report.addColumn( "MOTIVOS", "expr14", String.class.getName(), 10, header );
//            report.addColumn( "OBS. MOTIVOS", "expr15", String.class.getName(), 15, header );

            report.setUseFullPageWidth( true );

            DynamicReport dr = report.build();
            JRDataSource ds = new JRBeanCollectionDataSource(lstAlumnosAsistencia);

            JasperPrint jp = DynamicJasperHelper.generateJasperPrint( dr, new ClassicLayoutManager(), ds );

            return jp;

        } catch ( JRException e ) {
            e.printStackTrace();
        } catch ( ColumnBuilderException e ) {
            e.printStackTrace();
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return null;
    }
    public void exportToExcelAlumno() throws Exception {
        JasperPrint jasperPrint;
        JRXlsExporter exporter;
        String sFileName;
        String sFilePath;
        File outputFile;
        FileInputStream archivo;
        FileOutputStream fos;
        HttpServletResponse response;
        ServletOutputStream ouputStream;
        byte[] datos;
        String[] sheetNames = { "Hoja 1" };
        sFileName = "Reporte.xls";
        sFilePath = System.getProperty( "user.dir" ) + File.separator + sFileName;
        outputFile = new File( sFilePath );
        fos = new FileOutputStream( outputFile );
        

        jasperPrint = generateReporteAsistencia();

        exporter = new JRXlsExporter();

        //Indicamos el objeto JasperPrint que deseamos exportar
        exporter.setParameter( JRExporterParameter.JASPER_PRINT, jasperPrint );
        //Indicamos el fichero donde vamos a exportar el informe
        exporter.setParameter( JRExporterParameter.OUTPUT_STREAM, fos ); //and output stream
        //Indicamos si queremos una página por Sheet
        exporter.setParameter( JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE );
        //Indicamos si deseamos eliminar los espacios vacíos entre filas
        exporter.setParameter( JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE );
        //Indicamos si quremos mostrar una página en blanco como fondo
        exporter.setParameter( JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE );
        exporter.setParameter( JRXlsExporterParameter.SHEET_NAMES, sheetNames );
        exporter.exportReport();

        response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType( "application/vnd.ms-excel" );
        response.setHeader( "Content-Disposition", "attachment;filename=" + sFileName );
        response.addHeader( "Content-Type", "application/force-download" );

        archivo = new FileInputStream( sFilePath );
        datos = new byte[ archivo.available() ];
        archivo.read( datos );
        archivo.close();
        ouputStream = response.getOutputStream();
        ouputStream.write( datos );
        ouputStream.flush();
        ouputStream.close();
        FacesContext.getCurrentInstance().responseComplete();

        if ( new File( sFilePath ).delete() ) {
            System.out.println( "se borro el archivo" );
        }

    }
    
    
}
