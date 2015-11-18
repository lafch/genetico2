/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.uch.cursoLibre.logicreport.ReportHistorialDeuda;
import net.uch.cursoLibre.logicreport.SubReportCantidadMatriculado;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSAlumnoCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSParametroReporteCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSReporteRolCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSSeccionCLDAO;
import net.uch.mapping.ClAlumno;
import net.uch.mapping.ClSeccion;
import net.uch.tablaSistema.managedBeans.bUsuario;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import net.uch.cursoLibre.logicreport.dynamicJasper;
import java.util.Date;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
//import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.uch.academica.hibernateSpringDao.HSDocenteDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAcademicoClDao;
import net.uch.cursoLibre.logicreport.*;
import net.uch.cursoLibre.managedBeans.beans.MetodosCL;
import net.uch.mapping.*;
import net.uch.util.CommonDAO;
import net.uch.util.ConstantesWeb;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author richard, cesardl
 */
public class bReporte {

    private ArrayList<bReporte> listReporte = new ArrayList<bReporte>();
    private int idReporte;
    private String descripcion;
    private String expresiones;
    private String visualizar;
    private String oncomplete;
    private boolean image;
    // prop. modal
    private String pop_descripcion;
    private boolean pop_centro;
    private boolean pop_area;
    private boolean pop_modulo;
    private boolean pop_curso;
    private boolean pop_taller;
    private boolean pop_seccion;
    private boolean pop_alumno;
    private boolean pop_fini;
    private boolean pop_ffin;
    private boolean pop_fini_rango_eval;
    private boolean pop_ffin_rango_eval;
    private boolean pop_fimpresion;
    private boolean pop_debe_nota;
    private boolean pop_docentes;
    private String sDebeNota;
    private int iIdDocente;
    private SelectItem[] asiDocentes;
    private String i_are_id = "0";
    private String i_centro_id = "0";
    private SelectItem[] i_centros;
    private SelectItem[] i_areas;
    private SelectItem[] i_modulos;
    private String i_mod_id = "0";
    private SelectItem[] i_talleres;
    private int i_tal_id = 0;
    private int i_cur_id = 0;
    private SelectItem[] i_cursos;
    private SelectItem[] i_secciones;
    private String i_sec_id;
    // para el atocomplete
    private List<bMatriculaCL> matriculas = new ArrayList<bMatriculaCL>();
    private String suggestion;
    private String w_datos;
    private String w_codigo;
    // parametros for reporte
    private HashMap parameters;
    private List<TbParametroReportes> listParameters;
    private String codigo_alumno;
    private String tituloReporte = "";
    private String valorRep = "";
    private String reporteJasper = "";
    private String tipoReporte = "";
    private int idParamReport;
    private Date fecha_ini;
    private Date fecha_fin;
    private Date fecha_ini_rango_eval;
    private Date fecha_fin_rango_eval;

    public bReporte() {
        setListReport();
    }

    public bReporte( int b ) {
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete( String oncomplete ) {
        this.oncomplete = oncomplete;
    }

    public ArrayList<bReporte> getReporte() {
        return listReporte;
    }

    public void setReporte( ArrayList<bReporte> reporte ) {
        this.listReporte = reporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion( String descripcion ) {
        this.descripcion = descripcion;
    }

    public String getExpresiones() {
        return expresiones;
    }

    public void setExpresiones( String expresiones ) {
        this.expresiones = expresiones;
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte( int idReporte ) {
        this.idReporte = idReporte;
    }

    public boolean getImage() {
        return image;
    }

    public void setImage( boolean image ) {
        this.image = image;
    }

    public String getVisualizar() {
        return visualizar;
    }

    public void setVisualizar( String visualizar ) {
        this.visualizar = visualizar;
    }

    public boolean isPop_alumno() {
        return pop_alumno;
    }

    public int getI_cur_id() {
        return i_cur_id;
    }

    public void setI_cur_id( int i_cur_id ) {
        this.i_cur_id = i_cur_id;
    }

    public List<TbParametroReportes> getListParameters() {
        return listParameters;
    }

    public void setListParameters( List<TbParametroReportes> listParameters ) {
        this.listParameters = listParameters;
    }

    public void setPop_alumno( boolean pop_alumno ) {
        this.pop_alumno = pop_alumno;
    }

    public String getPop_descripcion() {
        return pop_descripcion;
    }

    public void setPop_descripcion( String pop_descrpcion ) {
        this.pop_descripcion = pop_descrpcion;
    }

    public boolean isPop_area() {
        return pop_area;
    }

    public void setPop_area( boolean pop_area ) {
        this.pop_area = pop_area;
    }

    public boolean isPop_centro() {
        return pop_centro;
    }

    public void setPop_centro( boolean pop_centro ) {
        this.pop_centro = pop_centro;
    }

    public boolean isPop_modulo() {
        return pop_modulo;
    }

    public void setPop_modulo( boolean pop_modulo ) {
        this.pop_modulo = pop_modulo;
    }

    public boolean isPop_curso() {
        return pop_curso;
    }

    public void setPop_curso( boolean pop_curso ) {
        this.pop_curso = pop_curso;
    }

    public boolean isPop_ffin() {
        return pop_ffin;
    }

    public void setPop_ffin( boolean pop_ffin ) {
        this.pop_ffin = pop_ffin;
    }

    public boolean isPop_fini() {
        return pop_fini;
    }

    public void setPop_fini( boolean pop_fini ) {
        this.pop_fini = pop_fini;
    }

    public boolean isPop_ffin_rango_eval() {
        return pop_ffin_rango_eval;
    }

    public void setPop_ffin_rango_eval( boolean pop_ffin_rango_eval ) {
        this.pop_ffin_rango_eval = pop_ffin_rango_eval;
    }

    public boolean isPop_fini_rango_eval() {
        return pop_fini_rango_eval;
    }

    public void setPop_fini_rango_eval( boolean pop_fini_rango_eval ) {
        this.pop_fini_rango_eval = pop_fini_rango_eval;
    }

    public boolean isPop_fimpresion() {
        return pop_fimpresion;
    }

    public void setPop_fimpresion( boolean pop_fimpresion ) {
        this.pop_fimpresion = pop_fimpresion;
    }

    public boolean isPop_debe_nota() {
        return pop_debe_nota;
    }

    public void setPop_debe_nota( boolean pop_debe_nota ) {
        this.pop_debe_nota = pop_debe_nota;
    }

    public boolean isPop_docentes() {
        return pop_docentes;
    }

    public void setPop_docentes( boolean pop_docentes ) {
        this.pop_docentes = pop_docentes;
    }

    public SelectItem[] getDocentes() {
        int iSize = 0;
        AcDocente docente;
        HSDocenteDAO dao;
        List<AcDocente> lstDocentes;

        try {
            if ( asiDocentes == null || asiDocentes.length == 1 ) {
                dao = (HSDocenteDAO) ServiceFinder.findBean( "SpringHibernateDaoDocente" );
                lstDocentes = dao.seleccionarDocente();
                iSize = lstDocentes.size();
                asiDocentes = new SelectItem[ iSize + 1 ];
                for ( int i = 1; i <= iSize; i++ ) {
                    docente = lstDocentes.get( i - 1 );
                    asiDocentes[i] = new SelectItem( docente.getId(), docente.getDocNombre() );
                }
            }
        } catch ( Exception ex ) {
            asiDocentes = new SelectItem[ iSize + 1 ];
            ex.printStackTrace();
        } finally {
            asiDocentes[0] = new SelectItem( "0", "[Todos]" );
        }
        return asiDocentes;
    }

    public void setDocentes( SelectItem[] asiDocentes ) {
        this.asiDocentes = asiDocentes;
    }

    public int getIdDocente() {
        return iIdDocente;
    }

    public void setIdDocente( int iIdDocente ) {
        this.iIdDocente = iIdDocente;
    }

    public String getI_centro_id() {
        return i_centro_id;
    }

    public void setI_centro_id( String i_centro_id ) {
        this.i_centro_id = i_centro_id;
    }

    public String getI_are_id() {
        return i_are_id;
    }

    public void setI_are_id( String i_are_id ) {
        this.i_are_id = i_are_id;
    }

    public String getDebeNota() {
        return sDebeNota;
    }

    public void setDebeNota( String sDebeNota ) {
        this.sDebeNota = sDebeNota;
    }

    public void setI_modulos( SelectItem[] i_modulos ) {
        this.i_modulos = i_modulos;
    }

    public String getI_mod_id() {
        return i_mod_id;
    }

    public void setI_mod_id( String i_mod_id ) {
        this.i_mod_id = i_mod_id;
    }

    public boolean isPop_seccion() {
        return pop_seccion;
    }

    public void setPop_seccion( boolean pop_seccion ) {
        this.pop_seccion = pop_seccion;
    }

    public boolean isPop_taller() {
        return pop_taller;
    }

    public void setPop_taller( boolean pop_taller ) {
        this.pop_taller = pop_taller;
    }

    public int getI_tal_id() {
        return i_tal_id;
    }

    public void setI_tal_id( int i_tal_id ) {
        this.i_tal_id = i_tal_id;
    }

    public void setI_talleres( SelectItem[] i_talleres ) {
        this.i_talleres = i_talleres;
    }

    public String getI_sec_id() {
        return i_sec_id;
    }

    public void setI_sec_id( String i_sec_id ) {
        this.i_sec_id = i_sec_id;
    }

    public void setI_secciones( SelectItem[] i_secciones ) {
        this.i_secciones = i_secciones;
    }

    public String getW_codigo() {
        return w_codigo;
    }

    public void setW_codigo( String w_codigo ) {
        this.w_codigo = w_codigo;
    }

    public String getW_datos() {
        return w_datos;
    }

    public void setW_datos( String w_datos ) {
        this.w_datos = w_datos;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion( String w_suggestion ) {
        this.suggestion = w_suggestion;
    }

    public void setMatriculas( List<bMatriculaCL> matriculas ) {
        this.matriculas = matriculas;
    }

    public List<bMatriculaCL> getMatriculas() {
        return matriculas;
    }

    public String getCodigo_alumno() {
        return codigo_alumno;
    }

    public void setCodigo_alumno( String codigo_alumno ) {
        this.codigo_alumno = codigo_alumno;
    }

    public String getTituloReporte() {
        return tituloReporte;
    }

    public void setTituloReporte( String tituloReporte ) {
        this.tituloReporte = tituloReporte;
    }

    public String getValorRep() {
        return valorRep;
    }

    public void setValorRep( String valorRep ) {
        this.valorRep = valorRep;
    }

    public ArrayList<bReporte> getListReporte() {
        return listReporte;
    }

    public void setListReporte( ArrayList<bReporte> listReporte ) {
        this.listReporte = listReporte;
    }

    public String getReporteJasper() {
        return reporteJasper;
    }

    public void setReporteJasper( String reporteJasper ) {
        this.reporteJasper = reporteJasper;
    }

    public HashMap getParameters() {
        return parameters;
    }

    public void setParameters( HashMap parameters ) {
        this.parameters = parameters;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte( String tipoReporte ) {
        this.tipoReporte = tipoReporte;
    }

    public int getIdParamReport() {
        return idParamReport;
    }

    public void setIdParamReport( int idParamReport ) {
        this.idParamReport = idParamReport;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin( Date fecha_fin ) {
        this.fecha_fin = fecha_fin;
    }

    public Date getFecha_fin_rango_eval() {
        return fecha_fin_rango_eval;
    }

    public void setFecha_fin_rango_eval( Date fecha_fin_rango_eval ) {
        this.fecha_fin_rango_eval = fecha_fin_rango_eval;
    }

    public Date getFecha_ini_rango_eval() {
        return fecha_ini_rango_eval;
    }

    public void setFecha_ini_rango_eval( Date fecha_ini_rango_eval ) {
        this.fecha_ini_rango_eval = fecha_ini_rango_eval;
    }

    public Date getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini( Date fecha_ini ) {
        this.fecha_ini = fecha_ini;
    }

    public void setListReport() {
        ArrayList<bReporte> listReport = new ArrayList<bReporte>();
        HSReporteRolCLDAO report = (HSReporteRolCLDAO) ServiceFinder.findBean( "SpringHibernateDaoReporteCL" );
        List<TbReporteRol> list = report.getListRoles( capturarUsuario() );
        for ( int i = 0; i < list.size(); i++ ) {
            bReporte objReport = new bReporte( 0 );
            objReport.setIdReporte( list.get( i ).getTbReporte().getRepId() );
            objReport.setDescripcion( list.get( i ).getTbReporte().getRepDescripcion() );
            listReport.add( objReport );
        }
        setReporte( listReport );
    }

    public SelectItem[] getI_centros() throws Exception {
        int iSizeCent;
        TbCatalogo catCent;
        List<TbCatalogo> lstCatCentros;

        i_centros = new SelectItem[ 1 ];
        i_centros[0] = new SelectItem( "0", "[Seleccione]" );
        try {
            lstCatCentros = CommonDAO.getTbCatalogoDAO().seleccionarCatalogo( "078" );
            if ( lstCatCentros != null && !lstCatCentros.isEmpty() ) {
                iSizeCent = lstCatCentros.size();
                i_centros = new SelectItem[ iSizeCent ];
                i_centros[0] = new SelectItem( "0", "[Seleccione]" );
                for ( int i = 0; i < iSizeCent; i++ ) {
                    catCent = lstCatCentros.get( i );
                    if ( !"UCH".equals( catCent.getCatDescripcionElemento() ) ) {
                        i_centros[i + 1] = new SelectItem( catCent.getCatCodigoGrupo() + catCent.getCatCodigoElemento(), catCent.getCatDescripcionElemento() );
                    }
                }
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return i_centros;
    }

    public SelectItem[] getI_areas() throws Exception {
        ClArbolAcademico arbAcad;
        HSArbolAcademicoClDao arbAcadClDAO;
        List<ClArbolAcademico> lstAreas;
//        if ( i_areas == null ) {
        try {
            if ( "0".equals( i_centro_id ) ) {
                i_areas = new SelectItem[ 1 ];
            } else {
                arbAcadClDAO = CommonDAO.getClArbolAcademicoDAO();
                lstAreas = arbAcadClDAO.AreasXInstitucion( i_centro_id );
                i_areas = new SelectItem[ lstAreas.size() + 1 ];
                for ( int i = 0; i < lstAreas.size(); i++ ) {
                    arbAcad = lstAreas.get( i );
                    i_areas[i + 1] = new SelectItem( arbAcad.getArbId(), arbAcad.getArbDescripcion() );
                }
            }
            i_areas[0] = new SelectItem( "0", "[Seleccione]" );
        } catch ( Exception e ) {
            System.err.println( "Error al cargar areas" );
        }
//        }
        return i_areas;
    }

    public SelectItem[] getI_modulos() throws Exception {
        HSArbolAcademicoClDao arbAcadDAO;
        List<ClArbolAcademico> lstModulos;

        if ( "0".equals( this.i_are_id ) ) {
            i_modulos = new SelectItem[ 1 ];
        } else {
            arbAcadDAO = CommonDAO.getClArbolAcademicoDAO();
            lstModulos = arbAcadDAO.seleccionarModulos( Integer.parseInt( this.i_are_id.toString() ), "" );

            i_modulos = new SelectItem[ lstModulos.size() + 1 ];
            for ( int i = 0; i < lstModulos.size(); i++ ) {
                i_modulos[i + 1] = new SelectItem( lstModulos.get( i ).getArbId(), lstModulos.get( i ).getArbDescripcion() );
            }
        }
        i_modulos[0] = new SelectItem( "0", "[Seleccione]" );
        return i_modulos;
    }

    public SelectItem[] getI_cursos() {
        HSArbolAcademicoClDao arbAcadDAO;
        List<ClArbolAcademico> lstCursos;
        if ( this.i_mod_id.equals( "0" ) ) {
            i_cursos = new SelectItem[ 1 ];
        } else {
            try {
                arbAcadDAO = CommonDAO.getClArbolAcademicoDAO();
                lstCursos = arbAcadDAO.seleccionarCursos( Integer.parseInt( this.i_mod_id ) );

                i_cursos = new SelectItem[ lstCursos.size() + 1 ];
                for ( int i = 0; i < lstCursos.size(); i++ ) {
                    i_cursos[i + 1] = new SelectItem( lstCursos.get( i ).getArbId(), lstCursos.get( i ).getArbDescripcion() );
                }
            } catch ( Exception e ) {
                System.err.println( "Error al cargar areas" );
            }
        }
        i_cursos[0] = new SelectItem( 0, "[Seleccione]" );
        return i_cursos;
    }

    public SelectItem[] getI_talleres() throws Exception {
        HSArbolAcademicoClDao arbAcadDAO;
        List<ClArbolAcademico> lstTalleres;
        if ( this.i_cur_id == 0 ) {
            i_talleres = new SelectItem[ 1 ];
        } else {
            try {
                arbAcadDAO = CommonDAO.getClArbolAcademicoDAO();
                lstTalleres = arbAcadDAO.seleccionarTalleres( this.i_cur_id );

                i_talleres = new SelectItem[ lstTalleres.size() + 1 ];
                for ( int i = 0; i < lstTalleres.size(); i++ ) {
                    i_talleres[i + 1] = new SelectItem( lstTalleres.get( i ).getArbId(), lstTalleres.get( i ).getArbDescripcion() );
                }
            } catch ( Exception e ) {
                System.err.println( "Error al cargar areas" );
            }
        }
        i_talleres[0] = new SelectItem( 0, "[Seleccione]" );
        return i_talleres;
    }

    /*
     * public SelectItem[] getI_talleres() throws Exception { if
     * (this.i_mod_id.equals("")) { i_talleres = new SelectItem[1]; } } else {
     * HSTallerAperturadoDAO dao = (HSTallerAperturadoDAO)
     * ServiceFinder.findBean("SpringHibernateDaoCLTallerAperturado"); //POR
     * REVISAR (#JTV)- 21/09/2012 List<ClTallerAperturado> lista =
     * dao.seleccionarTallerxModulo(Integer.valueOf(this.i_mod_id)); i_talleres
     * = new SelectItem[lista.size() + 1]; for (int i = 0; i < lista.size();
     * i++) { i_talleres[i + 1] = new
     * SelectItem(lista.get(i).getTalapeId(),lista.get(i).getTalapeDescripcion());
     * } } i_talleres[0] = new SelectItem(0, "[Seleccione]"); return i_talleres;
     * }
     */
    public SelectItem[] getI_secciones() throws Exception {
        HSSeccionCLDAO seccionDAO;
        seccionDAO = (HSSeccionCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLSeccion" );

        if ( this.i_cur_id == 0 ) {
            i_secciones = new SelectItem[ 1 ];
        } else {
            HSSeccionCLDAO dao = (HSSeccionCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLSeccion" );
            List<ClSeccion> lista = seccionDAO.listarTodasSeccionesXCurso( i_cur_id );
            i_secciones = new SelectItem[ lista.size() + 1 ];
            for ( int i = 0; i < lista.size(); i++ ) {
                i_secciones[i + 1] = new SelectItem( lista.get( i ).getSecId(), lista.get( i ).getSecNombre() );
            }
        }
        i_secciones[0] = new SelectItem( 0, "[Seleccione]" );
        return i_secciones;
//        if ( this.i_tal_id == 0 ) {
//            i_secciones = new SelectItem[ 1 ];
//        } else {
//            HSSeccionCLDAO dao = (HSSeccionCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLSeccion" );
//            List<ClSeccion> lista = dao.seleccionarSeccionesxTaller( i_tal_id );
//            i_secciones = new SelectItem[ lista.size() + 1 ];
//            for ( int i = 0; i < lista.size(); i++ ) {
//                i_secciones[i + 1] = new SelectItem( lista.get( i ).getSecId(), lista.get( i ).getSecNombre() );
//            }
//        }
//        i_secciones[0] = new SelectItem( 0, "[Seleccione]" );
//        return i_secciones;
    }

    public List<bReporte> autocomplete( Object suggestion ) {

        String pref = (String) suggestion;
        List<bReporte> lAlumnos = new ArrayList<bReporte>();
        List<ClAlumno> alus;
        HSAlumnoCLDAO dao = (HSAlumnoCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLAlumno" );
        if ( pref.matches( "^\\d*" ) ) {
            try {
                alus = dao.seleccionarAlumnosPorCodigo( pref );
                if ( alus.size() > 0 ) {
                    for ( int i = 0; i < alus.size(); i++ ) {
                        ClAlumno alu = alus.get( i );
                        bReporte al = new bReporte( 0 );
                        al.setW_codigo( alu.getAluCodigo() );
                        al.setW_datos( alu.getAluAppaterno().toUpperCase().trim() + " " + alu.getAluApmaterno().toUpperCase().trim() + " " + alu.getAluNombres().toUpperCase().trim() );
                        lAlumnos.add( al );
                    }
                }
            } catch ( Exception ex ) {
                Logger.getLogger( bReporte.class.getName() ).log( Level.SEVERE, null, ex );
            }
        } else {
            try {
                alus = dao.seleccionarAlumnosPorApellidos( pref );
                if ( alus.size() > 0 ) {
                    for ( int i = 0; i < alus.size(); i++ ) {
                        ClAlumno alu = alus.get( i );
                        bReporte al = new bReporte( 0 );
                        al.setW_codigo( alu.getAluCodigo() );
                        al.setW_datos( alu.getAluAppaterno().toUpperCase().trim() + " " + alu.getAluApmaterno().toUpperCase().trim() + " " + alu.getAluNombres().toUpperCase().trim() );
                        lAlumnos.add( al );
                    }
                }
            } catch ( Exception ex ) {
                Logger.getLogger( bReporte.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }

        return lAlumnos;
    }

    public void setLimpiaParameter() {
        pop_alumno = false;
        pop_centro = false;
        pop_area = false;
        pop_modulo = false;
        pop_curso = false;
        pop_fini = false;
        pop_ffin = false;
        pop_fini_rango_eval = false;
        pop_ffin_rango_eval = false;
        pop_debe_nota = false;
        pop_docentes = false;
        pop_taller = false;
        pop_seccion = false;
        // esto e agregado
        i_are_id = "0";
        i_mod_id = "0";
        i_sec_id = "";
        i_tal_id = 0;
        codigo_alumno = "";
        suggestion = "";
        parameters = null;
        fecha_fin = null;
        fecha_ini = null;
    }

    public void showParameterReport( ActionEvent event ) throws Exception {

        setOncomplete( "" );
        setLimpiaParameter();
        idReporte = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "p_param_id" )).getValue().toString() );
        HSParametroReporteCLDAO param = (HSParametroReporteCLDAO) ServiceFinder.findBean( "SpringHibernateDaoParametroReporteCL" );
        listParameters = param.getListParam( idReporte );

        for ( TbParametroReportes p : listParameters ) {
            this.setPop_descripcion( p.getTbReporte().getRepDescripcion() );
            this.setReporteJasper( p.getTbReporte().getRepLink() );
            this.setTipoReporte( p.getTbReporte().getRepTipo() );
            this.setIdParamReport( p.getTbReporte().getRepId() );

            if ( p.getParRepParametroRepor().equals( "alu_codigo" ) ) {
                pop_alumno = true;
            }
            if ( p.getParRepParametroRepor().equals( "fecha_ini" ) ) {
                pop_fini = true;
            }
            if ( p.getParRepParametroRepor().equals( "fecha_fin" ) ) {
                pop_ffin = true;
            }
            if ( p.getParRepParametroRepor().equals( "fecha_ini_rango_eval" ) ) {
                pop_fini_rango_eval = true;
            }
            if ( p.getParRepParametroRepor().equals( "fecha_fin_rango_eval" ) ) {
                pop_ffin_rango_eval = true;
            }
            if ( p.getParRepParametroRepor().equals( "sec_id" ) ) {
                pop_centro = true;
                pop_area = true;
                pop_modulo = true;
                pop_curso = true;
//                pop_taller = true;
                pop_seccion = true;
            }
            if ( p.getParRepParametroRepor().equals( "mod_id" ) ) {
                pop_modulo = true;
            }
            if ( p.getTbReporte().getRepDescripcion().contains( "excel" ) ) {
                image = true;
            } else {
                image = false;
            }
            if ( "cur_id".equals( p.getParRepParametroRepor() ) ) {
                this.pop_curso = true;
            }
            if ( "debe".equals( p.getParRepParametroRepor() ) ) {
                this.pop_debe_nota = true;
            }
            if ( "doc_id".equals( p.getParRepParametroRepor() ) ) {
                this.pop_docentes = true;
            }
            if ( "area_id".equals( p.getParRepParametroRepor() ) ) {
                this.pop_area = true;
            }
            if ( "centro_id".equals( p.getParRepParametroRepor() ) ) {
                this.pop_centro = true;
            }
        }
        setOncomplete( "Richfaces.showModalPanel('mpParametro')" );

    }

    private int capturarUsuario() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession( false );
        //int id_usu = ((bUsuario) session.getAttribute( "usuario" )).getRol_id();
        int id_usu = ((bUsuario) session.getAttribute( "usuario" )).getId_usu();
        return id_usu;
    }

    public void ImprimirFicha( ActionEvent event ) throws Exception {
        setOncomplete( "" );
        setTituloReporte( "Ficha de Matricula" );
        setValorRep( "ficha" );
        HashMap xparameters = loadParameters();

        if ( xparameters != null ) {
            this.setOncomplete( "javascript:Richfaces.showModalPanel('mp7')" );
        } else {
            this.setOncomplete( "javascript:alert('Hace falta especificar algunos parametros ..!!!')" );
        }

    }

    public void cargarReporte( OutputStream out, Object data ) throws IOException, Exception, EOFException {
        if ( getTipoReporte().contains( "043" ) ) {
            HashMap xparameters = loadParameters();
            if ( xparameters != null ) {
                cargarReporteDynamic( out );
            }
        }
    }

    public void cargarReporteDynamic( OutputStream out ) throws IOException, Exception, EOFException {

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream( cargar_reporteDynamic( this.getIdParamReport() ), buffer );
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

    public JasperPrint cargar_reporteDynamic( int tipreport ) {

        try {
            JasperPrint jasperPrint = null;
            dynamicJasper dina = new dynamicJasper();

            switch ( tipreport ) {
                case 102:
                    jasperPrint = dina.generarReporteinscritosxSeccion( Integer.valueOf( this.getI_sec_id() ) );
                    break;
                case 103:
                    jasperPrint = dina.generarReportePreInscritosxSeccion( Integer.valueOf( this.getI_sec_id() ) );
                    break;
                case 104:
                    jasperPrint = dina.generarReporteinscritosxSeccion_datos( Integer.valueOf( this.getI_sec_id() ) );
                    break;
                case 105:
                    jasperPrint = dina.generarReporteMontoPago( Integer.valueOf( this.getI_sec_id() ) );
                    break;
                case 107:
                    SimpleDateFormat bFormatoFecha = new SimpleDateFormat( "yyyy-MM-dd" );
                    String fechaIni = bFormatoFecha.format( this.getFecha_ini() );
                    String fechaFin = bFormatoFecha.format( this.getFecha_fin() );
                    jasperPrint = dina.generarReporteMatriculadosFecha( Integer.valueOf( this.getI_sec_id() ), fechaIni, fechaFin );
                    break;
                case 118:
                    jasperPrint = ReportHistorialDeuda.generateReportMontoDeudaSeccion( Integer.valueOf( this.getI_sec_id() ) );
                    break;
                case 119:
                    jasperPrint = SubReportCantidadMatriculado.integrateHeaderAndSubReport( Integer.valueOf( this.getI_sec_id() ) );
                    break;
                case 120:
                    jasperPrint = ReporteHorario.generateReportNotasAula( Integer.valueOf( this.getI_sec_id() ) );
                    break;
                case 121:
                    jasperPrint = ReportHistorialCurso.integrateReport( this.getCodigo_alumno() );
                    break;
                case 122:
                    jasperPrint = ReporteRegistroNota.generateReportNotasAula( Integer.valueOf( this.getI_sec_id() ) );
                    break;
                /*case 170:
                    jasperPrint = ReportHistorialDeudaObs.generateReportMontoDeudaSeccionObs( Integer.valueOf( this.getI_sec_id() ) );
                    break;*/
               
                default:
                    FacesContext context = FacesContext.getCurrentInstance();
                    InputStream reportStream = context.getExternalContext().getResourceAsStream( "/WEB-INF/Reportes/cursos_libres/" + getReporteJasper() );

                    DriverManagerDataSource dmds = (DriverManagerDataSource) ServiceFinder.findBean( "dataSource" );
                    Connection connection = dmds.getConnection();
                    HashMap p = loadParameters();
                    jasperPrint = JasperFillManager.fillReport( reportStream, p, connection );
                    connection.close();
                    break;
            }

            return jasperPrint;

        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }

    }

    public HashMap loadParameters() {
        HashMap parametros = null;
        try {
            parametros = new HashMap();
            SimpleDateFormat bFormatoFecha = new SimpleDateFormat( "yyyy-MM-dd" );
            FacesContext context = FacesContext.getCurrentInstance();
            parametros.put( "logo", context.getExternalContext().getResource( "/Imagenes/actions/logo_p.jpg" ) );
            for ( TbParametroReportes p : listParameters ) {
                if ( p.getParRepParametroRepor().equals( "alu_codigo" ) ) {
                    if ( !this.getCodigo_alumno().trim().isEmpty() ) {
                        parametros.put( p.getParRepParametroRepor(), this.getCodigo_alumno() );
                    } else if ( p.getParRepRequerido().intValue() == 1 ) {
                        return null;
                    }
                } else if ( p.getParRepParametroRepor().equals( "fecha_ini" ) ) {
                    if ( this.getFecha_ini() != null ) {
                        String fechaIni = bFormatoFecha.format( this.getFecha_ini() );
                        parametros.put( p.getParRepParametroRepor(), fechaIni );
                    } else if ( p.getParRepRequerido().intValue() == 1 ) {
                        return null;
                    }
                } else if ( p.getParRepParametroRepor().equals( "fecha_fin" ) ) {
                    if ( p.getTbReporte().getRepId().intValue() == ConstantesWeb.ID_RPT_DOC_DEBEN_NOTAS ) {
                        if ( this.getFecha_fin() != null ) {
                            String fechaFin = bFormatoFecha.format( this.getFecha_fin() );
                            parametros.put( p.getParRepParametroRepor(), fechaFin );
//                            bFormatoFecha = new SimpleDateFormat( "yyyyMMdd" );
                        }
                    } else {
                        if ( this.getFecha_fin() != null ) {
                            String fechaFin = bFormatoFecha.format( this.getFecha_fin() );
                            parametros.put( p.getParRepParametroRepor(), fechaFin );
                        } else if ( p.getParRepRequerido().intValue() == 1 ) {
                            return null;
                        } else {
                            parametros.put( p.getParRepParametroRepor(), "" );
                        }
                    }
                } else if ( p.getParRepParametroRepor().equals( "fecha_ini_rango_eval" ) ) {
                    if ( fecha_ini_rango_eval != null ) {
                        String fechaIni = bFormatoFecha.format( fecha_ini_rango_eval );
                        parametros.put( p.getParRepParametroRepor(), fechaIni );
                    } else if ( p.getParRepRequerido().intValue() == 1 ) {
                        return null;
                    }
                } else if ( p.getParRepParametroRepor().equals( "fecha_fin_rango_eval" ) ) {
                    if ( fecha_fin_rango_eval != null ) {
                        Calendar fecha_fin_rango_evalAux = Calendar.getInstance();
                        fecha_fin_rango_evalAux.setTime( fecha_fin_rango_eval );
                        fecha_fin_rango_evalAux.add( Calendar.DATE, 1 );
                        String fechaFin = bFormatoFecha.format( fecha_fin_rango_evalAux.getTime() );
                        parametros.put( p.getParRepParametroRepor(), fechaFin );
                    } else if ( p.getParRepRequerido().intValue() == 1 ) {
                        return null;
                    }
                } else if ( p.getParRepParametroRepor().equals( "sec_id" ) ) {
                    if ( !this.getI_sec_id().trim().equals( "0" ) ) {
                        parametros.put( p.getParRepParametroRepor(), this.getI_sec_id() );
                    } else if ( p.getParRepRequerido().intValue() == 1 ) {
                        return null;
                    }
                } else if ( p.getParRepParametroRepor().equals( "mod_id" ) ) {
                    if ( !this.getI_mod_id().trim().equals( "0" ) || p.getParRepRequerido().intValue() == 0 ) {
                        parametros.put( p.getParRepParametroRepor(), this.getI_mod_id() );
                    } else if ( p.getParRepRequerido().intValue() == 1 ) {
                        return null;
                    }
                } else if ( "titulo".equals( p.getParRepParametroRepor() ) ) {
                    if ( p.getTbReporte() != null && p.getTbReporte().getRepId().intValue() == ConstantesWeb.ID_RPT_DOC_DEBEN_NOTAS ) {//Reporte Deben notas
                        String sTitulo = "DOCENTES QUE DEBEN NOTAS";
                        if ( "2".equals( sDebeNota ) ) {
                            sTitulo = "DOCENTES QUE YA INGRESARON NOTAS";
                        }
                        parametros.put( "titulo", sTitulo );
                    }
                } else if ( "debe".equals( p.getParRepParametroRepor() ) ) {
                    parametros.put( "debe", this.sDebeNota );
                } else if ( "centro_id".equals( p.getParRepParametroRepor() ) ) {
                    parametros.put( "centro_id", this.i_centro_id );
                } else if ( "area_id".equals( p.getParRepParametroRepor() ) ) {
                    parametros.put( "area_id", this.i_are_id );
                } else if ( "mod_id".equals( p.getParRepParametroRepor() ) ) {
                    parametros.put( "mod_id", this.i_mod_id );
                } else if ( "cur_id".equals( p.getParRepParametroRepor() ) ) {
                    parametros.put( "cur_id", this.i_cur_id + "" );
                } else if ( "doc_id".equals( p.getParRepParametroRepor() ) ) {
                    parametros.put( "doc_id", this.iIdDocente + "" );
                }
                /**
                 * debe area_id mod_id cur_id doc_id fecha_fin titulo
                 *
                 */
            }
        } catch ( MalformedURLException ex ) {
            Logger.getLogger( bReporte.class.getName() ).log( Level.SEVERE, null, ex );
        }
        return parametros;
    }

    public void generarArchivoDescargable() {

        try {
            if ( !this.getI_sec_id().equals( "0" ) ) {
                String nom_file = "Reporte.xls";
                FileInputStream archivo = null;
                switch ( this.getIdParamReport() ) {
                    case 103:
                        archivo = WriteExcel.writePreInscritosSeccion( nom_file, Integer.valueOf( this.getI_sec_id() ) );
                        break;
                    case 104:
                        archivo = WriteExcel.writeInscritosSeccion( nom_file, Integer.valueOf( this.getI_sec_id() ) );
                        break;
                    default:
                        return;
                }

                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                response.setContentType( "application/vnd.ms-excel" );
//            response.setHeader("Content-Disposition", "attachment;filename=" + nom_file.replaceFirst(".xls", "_" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".xls"));
                response.setHeader( "Content-Disposition", "attachment;filename=" + nom_file );
                response.addHeader( "Content-Type", "application/force-download" );

                byte[] datos = new byte[ archivo.available() ];
                archivo.read( datos );
                archivo.close();
                ServletOutputStream ouputStream = response.getOutputStream();
                ouputStream.write( datos );
                ouputStream.flush();
                ouputStream.close();
                FacesContext.getCurrentInstance().responseComplete();

            }
        } catch ( IOException ex ) {
            Logger.getLogger( bReporte.class.getName() ).log( Level.SEVERE, null, ex );
        }


    }

    public void generarArchivoDescargablePDF() {

        try {
            HashMap xparameters = loadParameters();
            if ( xparameters != null ) {

                String nom_file = "Reporte.pdf";
                String ruta_file = System.getProperty( "user.dir" ) + File.separator + nom_file;

                JasperPrint jp = cargar_reporteDynamic( this.getIdParamReport() );
                JasperExportManager.exportReportToPdfFile( jp, ruta_file );

                FileInputStream archivo = new FileInputStream( ruta_file );

                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                response.setContentType( "application/pdf" );
                response.setHeader( "Content-Disposition", "attachment;filename=" + nom_file );
                response.addHeader( "Content-Type", "application/force-download" );

                byte[] datos = new byte[ archivo.available() ];
                archivo.read( datos );
                archivo.close();
                ServletOutputStream ouputStream = response.getOutputStream();
                ouputStream.write( datos );
                ouputStream.flush();
                ouputStream.close();
                FacesContext.getCurrentInstance().responseComplete();

                if ( new File( ruta_file ).delete() ) {
                    System.out.println( "se borro el archivo" );
                }

            }
        } catch ( JRException ex ) {
            Logger.getLogger( bReporte.class.getName() ).log( Level.SEVERE, null, ex );
        } catch ( IOException ex ) {
            Logger.getLogger( bReporte.class.getName() ).log( Level.SEVERE, null, ex );
        }

    }

    public void exportToExcel() throws Exception {

        HashMap xparameters = loadParameters();
        if ( xparameters != null ) {
            JasperPrint jp = cargar_reporteDynamic( this.getIdParamReport() );

            //Creamos una instancia del objeto JRXlsExplorer que se encuentra en la librería jasperreports-X.X.X.jar
            JRXlsExporter exporter = new JRXlsExporter();
            //Si queremos definir varios Sheet lo hacemos en un array de cadenas que posteriormente la añadiremos por parámetros.
            String[] sheetNames = { "Hoja 1" };

            //Creamos el fichero de salida donde exportaremos el informe a Excel
            String nom_file = "Reporte.xls";
            String ruta_file = System.getProperty( "user.dir" ) + File.separator + nom_file;
            File outputFile = new File( ruta_file );
            FileOutputStream fos = new FileOutputStream( outputFile );

            //Indicamos el objeto JasperPrint que deseamos exportar
            exporter.setParameter( JRExporterParameter.JASPER_PRINT, jp );
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

            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType( "application/vnd.ms-excel" );
            response.setHeader( "Content-Disposition", "attachment;filename=" + nom_file );
            response.addHeader( "Content-Type", "application/force-download" );

            FileInputStream archivo = new FileInputStream( ruta_file );
            byte[] datos = new byte[ archivo.available() ];
            archivo.read( datos );
            archivo.close();
            ServletOutputStream ouputStream = response.getOutputStream();
            ouputStream.write( datos );
            ouputStream.flush();
            ouputStream.close();
            FacesContext.getCurrentInstance().responseComplete();

            if ( new File( ruta_file ).delete() ) {
                System.out.println( "se borro el archivo" );
            }

        }

    }

    public void exportToExcelContacto() throws Exception {

        HashMap xparameters = loadParameters();
        if ( xparameters != null ) {
            JasperPrint jp = cargar_reporteDynamic( this.getIdParamReport() );

            //Creamos una instancia del objeto JRXlsExplorer que se encuentra en la librería jasperreports-X.X.X.jar
            JRXlsExporter exporter = new JRXlsExporter();
            //Si queremos definir varios Sheet lo hacemos en un array de cadenas que posteriormente la añadiremos por parámetros.
            String[] sheetNames = { "Hoja 1" };

            //Creamos el fichero de salida donde exportaremos el informe a Excel
            String nom_file = "Reporte.xls";
            String ruta_file = System.getProperty( "user.dir" ) + File.separator + nom_file;
            File outputFile = new File( ruta_file );
            FileOutputStream fos = new FileOutputStream( outputFile );

            //Indicamos el objeto JasperPrint que deseamos exportar
            exporter.setParameter( JRExporterParameter.JASPER_PRINT, jp );
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

            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType( "application/vnd.ms-excel" );
            response.setHeader( "Content-Disposition", "attachment;filename=" + nom_file );
            response.addHeader( "Content-Type", "application/force-download" );

            FileInputStream archivo = new FileInputStream( ruta_file );
            byte[] datos = new byte[ archivo.available() ];
            archivo.read( datos );
            archivo.close();
            ServletOutputStream ouputStream = response.getOutputStream();
            ouputStream.write( datos );
            ouputStream.flush();
            ouputStream.close();
            FacesContext.getCurrentInstance().responseComplete();

            if ( new File( ruta_file ).delete() ) {
                System.out.println( "se borro el archivo" );
            }

        }

    }
}
