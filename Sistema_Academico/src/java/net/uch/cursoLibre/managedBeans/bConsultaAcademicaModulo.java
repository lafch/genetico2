/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.*;
import java.util.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSAlumnoCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAcademicoClDao;
import net.uch.cursoLibre.managedBeans.beans.Column;
import net.uch.cursoLibre.utilsAcademico.Metodos;
import net.uch.mapping.ClArbolAcademico;
import net.uch.util.CommonDAO;
import net.uch.util.CommonWeb;
import net.uch.util.ConstantesWeb;
import net.uch.utilAdministrativo.MetodosAdm;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author Alumno
 */
public class bConsultaAcademicaModulo {

    private int w_sed_id = 0;
    private int w_mod_id = 0;
    private int w_cur_id = 0;
    private int w_mes = 0;
    private int w_are_id = 0;
    private String oncomplete;
    private String w_ano_id = "0";
    private String w_rad_id;
    private SelectItem[] cboSede;
    private SelectItem[] cboAno;
    private SelectItem[] cboModulo;
    private SelectItem[] cboArea;
    private SelectItem[] cboCurso;
    private SelectItem[] cboMes;
    private SelectItem[] rdoOpciones;
    List<Column> c_cabeceraColumna = new ArrayList<Column>();
    List<List<String>> c_dataColumna = new ArrayList<List<String>>();
    Integer c_cantiFilas = 0;
    List listaNombreCursos = new ArrayList();

    public bConsultaAcademicaModulo() {
        //m_log.info( "[bConsultaAcademicaModulo] << ENTER" );
        cargarData( new ArrayList<Integer>(), new ArrayList<ClArbolAcademico>() );
        //m_log.info( "[bConsultaAcademicaModulo] >> EXIT" );
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete( String oncomplete ) {
        this.oncomplete = oncomplete;
    }

    public boolean isEsProgAux() {
        String sModular;
        try{
            sModular = CommonDAO.getClArbolAcademicoDAO().buscarArbolPorId( w_are_id ).getArbAreaModular();
        }catch(Exception ex){
            sModular = "0";
        }
//        boolean blEsProgAux = w_area_id == ConstantesWeb.ID_AREA_PROG_AUX;
        boolean blEsProgAux = w_are_id > 0 && "1".equals( sModular );
        return blEsProgAux;
    }

    public SelectItem[] getCboAno() {
        //m_log.info( "[getCboAno] << ENTER" );
        Metodos metodo = new Metodos();
        cboAno = metodo.cboAnio();
        //m_log.info( "[getCboAno] >> EXIT" );
        return cboAno;
    }

    public void setCboAno( SelectItem[] cboAno ) {
        //m_log.info( "[setCboAno] << ENTER" );
        this.cboAno = cboAno;
        //m_log.info( "[setCboAno] >> EXIT" );
    }

    public SelectItem[] getCboCurso() {
        //m_log.info( "[getCboCurso] << ENTER" );
        int iSizeCursos;
        ClArbolAcademico clArbCur;
        List<ClArbolAcademico> lstArbCursos;
        try {
            if ( w_mod_id > 0 ) {
                lstArbCursos = CommonDAO.getClArbolAcademicoDAO().listarArbolPorPadre( w_mod_id );

                if ( lstArbCursos != null && !lstArbCursos.isEmpty() ) {
                    iSizeCursos = lstArbCursos.size();
                    cboCurso = new SelectItem[ iSizeCursos + 1 ];
                    cboCurso[0] = new SelectItem( "0", "[[--Seleccione--]])" );
                    for ( int i = 0; i < (cboCurso.length - 1); i++ ) {
                        clArbCur = lstArbCursos.get( i );
                        cboCurso[i + 1] = new SelectItem( clArbCur.getArbId(), clArbCur.getArbDescripcion() );
                    }
                }
            } else {
                cboCurso = new SelectItem[ 1 ];
                cboCurso[0] = new SelectItem( "0", "[Seleccionar curso]" );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        //m_log.info( "[getCboCurso] >> EXIT" );
        return cboCurso;
    }

    public void setCboCurso( SelectItem[] cboCurso ) {
        //m_log.info( "[setCboCurso] << ENTER" );
        this.cboCurso = cboCurso;
        //m_log.info( "[setCboCurso] >> EXIT" );
    }

    public SelectItem[] getCboMes() {
        //m_log.info( "[getCboMes] << ENTER" );
        Metodos metodo = new Metodos();
        cboMes = metodo.cboMes();
        //m_log.info( "[getCboMes] >> EXIT" );
        return cboMes;
    }

    public void setCboMes( SelectItem[] cboMes ) {
        //m_log.info( "[setCboMes] << ENTER" );
        this.cboMes = cboMes;
        //m_log.info( "[setCboMes] >> EXIT" );
    }

    public SelectItem[] getCboModulo() {
        //m_log.info( "[getCboModulo] << ENTER" );
        int iSizeAbrMod;
        ClArbolAcademico arbMod;
        List<ClArbolAcademico> lstArbModulo;

        if ( w_are_id != 0 ) {
            lstArbModulo = CommonDAO.getClArbolAcademicoDAO().listarArbolPorPadre( w_are_id );
            if ( lstArbModulo != null && !lstArbModulo.isEmpty() ) {
                iSizeAbrMod = lstArbModulo.size();
                cboModulo = new SelectItem[ iSizeAbrMod + 1 ];
                cboModulo[0] = new SelectItem( "0", "[Seleccionar módulo]" );
                for ( int i = 0; i < (cboModulo.length - 1); i++ ) {
                    arbMod = lstArbModulo.get( i );
                    cboModulo[i + 1] = new SelectItem( arbMod.getArbId(), arbMod.getArbDescripcion() );

                }
            }
        } else {
            cboModulo = new SelectItem[ 1 ];
            cboModulo[0] = new SelectItem( "0", "[Seleccionar módulo]" );
        }

        //m_log.info( "[getCboModulo] >> EXIT" );
        return cboModulo;
    }

    public void setCboModulo( SelectItem[] cboModulo ) {
        //m_log.info( "[setCboModulo] << ENTER" );
        this.cboModulo = cboModulo;
        //m_log.info( "[setCboModulo] >> EXIT" );
    }

    public SelectItem[] getCboArea() {
        //m_log.info( "[getCboArea] << ENTER" );
        int iSizeAbrArea;
        ClArbolAcademico arbArea;
        List<ClArbolAcademico> lstArbAreas;

        try {
            lstArbAreas = CommonDAO.getClArbolAcademicoDAO().listarArbolPorPadre( 0 );
            if ( lstArbAreas != null && !lstArbAreas.isEmpty() ) {
                iSizeAbrArea = lstArbAreas.size();
                cboArea = new SelectItem[ iSizeAbrArea + 1 ];
                cboArea[0] = new SelectItem( "0", "[Seleccionar área]" );
                for ( int i = 0; i < (cboArea.length - 1); i++ ) {
                    arbArea = lstArbAreas.get( i );
                    cboArea[i + 1] = new SelectItem( arbArea.getArbId(), arbArea.getArbDescripcion() );
                }
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        //m_log.info( "[getCboArea] >> EXIT" );
        return cboArea;
    }

    public void setCboArea( SelectItem[] cboArea ) {
        //m_log.info( "[setCboArea] << ENTER" );
        this.cboArea = cboArea;
        //m_log.info( "[setCboArea] >> EXIT" );
    }

    public int getW_are_id() {
        //m_log.info( "[getW_are_id] << ENTER" );
        //m_log.info( "[getW_are_id] >> EXIT" );
        return w_are_id;
    }

    public void setW_are_id( int w_are_id ) {
        //m_log.info( "[setW_are_id] << ENTER" );
        this.w_are_id = w_are_id;
        //m_log.info( "[setW_are_id] >> EXIT" );
    }

    public SelectItem[] getCboSede() throws SQLException {
        //m_log.info( "[getCboSede] << ENTER" );
        MetodosAdm metodo = new MetodosAdm();
        cboSede = metodo.listarSedes( "[[Seleccione]]" );
        //m_log.info( "[getCboSede] >> EXIT" );
        return cboSede;
    }

    public void setCboSede( SelectItem[] cboSede ) {
        //m_log.info( "[setCboSede] << ENTER" );
        this.cboSede = cboSede;
        //m_log.info( "[setCboSede] >> EXIT" );
    }

    public SelectItem[] getRdoOpciones() {
        //m_log.info( "[getRdoOpciones] << ENTER" );
        rdoOpciones = new SelectItem[ 2 ];
        rdoOpciones[0] = new SelectItem( 5, "CERTIFICADOS" );
        rdoOpciones[1] = new SelectItem( 6, "Prof. Adeudan registros" );
        //m_log.info( "[getRdoOpciones] >> EXIT" );
        return rdoOpciones;
    }

    public void setRdoOpciones( SelectItem[] rdoOpciones ) {
        //m_log.info( "[setRdoOpciones] << ENTER" );
        this.rdoOpciones = rdoOpciones;
        //m_log.info( "[setRdoOpciones] << EXIT" );
    }

    public String getW_ano_id() {
        //m_log.info( "[getW_ano_id] << ENTER" );
        //m_log.info( "[getW_ano_id] >> EXIT" );
        return w_ano_id;
    }

    public void setW_ano_id( String w_ano_id ) {
        //m_log.info( "[setW_ano_id] << ENTER" );
        //m_log.info( "[setW_ano_id] >> EXIT" );
        this.w_ano_id = w_ano_id;
    }

    public int getW_cur_id() {
        //m_log.info( "[getW_ano_id] << ENTER" );
        //m_log.info( "[getW_ano_id] >> EXIT" );
        return w_cur_id;
    }

    public void setW_cur_id( int w_cur_id ) {
        //m_log.info( "[setW_cur_id] << ENTER" );
        this.w_cur_id = w_cur_id;
        //m_log.info( "[setW_cur_id] >> EXIT" );
    }

    public int getW_mes() {
        //m_log.info( "[getW_mes] << ENTER" );
        //m_log.info( "[getW_mes] >> EXIT" );
        return w_mes;
    }

    public void setW_mes( int w_mes ) {
        //m_log.info( "[setW_mes] << ENTER" );
        this.w_mes = w_mes;
        //m_log.info( "[setW_mes] >> EXIT" );
    }

    public int getW_mod_id() {
        //m_log.info( "[getW_mod_id] << ENTER" );
        //m_log.info( "[getW_mod_id] >> EXIT" );
        return w_mod_id;
    }

    public void setW_mod_id( int w_mod_id ) {
        //m_log.info( "[setW_mod_id] << ENTER" );
        this.w_mod_id = w_mod_id;
        //m_log.info( "[setW_mod_id] >> EXIT" );
    }

    public String getW_rad_id() {
        //m_log.info( "[getW_rad_id] << ENTER" );
        //m_log.info( "[getW_rad_id] >> EXIT" );
        return w_rad_id;
    }

    public void setW_rad_id( String w_rad_id ) {
        //m_log.info( "[setW_rad_id] << ENTER" );
        this.w_rad_id = w_rad_id == null || w_rad_id.trim().isEmpty() ? "0" : w_rad_id;
        //m_log.info( "[setW_rad_id] >> EXIT" );
    }

    public int getW_sed_id() {
        //m_log.info( "[getW_sed_id] << ENTER" );
        //m_log.info( "[getW_sed_id] >> EXIT" );
        return w_sed_id;
    }

    public void setW_sed_id( int w_sed_id ) {
        //m_log.info( "[setW_sed_id] << ENTER" );
        this.w_sed_id = w_sed_id;
        //m_log.info( "[setW_sed_id] >> EXIT" );
    }

    public List<Column> getC_cabeceraColumna() {
        //m_log.info( "[getC_cabeceraColumna] << ENTER" );
        //m_log.info( "[getC_cabeceraColumna] >> EXIT" );
        return c_cabeceraColumna;
    }

    public void setC_cabeceraColumna( List<Column> c_cabeceraColumna ) {
        //m_log.info( "[setC_cabeceraColumna] << ENTER" );
        this.c_cabeceraColumna = c_cabeceraColumna;
        //m_log.info( "[setC_cabeceraColumna] >> EXIT" );
    }

    public Integer getC_cantiFilas() {
        //m_log.info( "[getC_cantiFilas] << ENTER" );
        //m_log.info( "[getC_cantiFilas] >> EXIT" );
        return c_cantiFilas;
    }

    public void setC_cantiFilas( Integer c_cantiFilas ) {
        //m_log.info( "[setC_cantiFilas] << ENTER" );
        this.c_cantiFilas = c_cantiFilas;
        //m_log.info( "[setC_cantiFilas] >> EXIT" );
    }

    public List<List<String>> getC_dataColumna() {
        //m_log.info( "[getC_dataColumna] << ENTER" );
        //m_log.info( "[getC_dataColumna] >> EXIT" );
        return c_dataColumna;
    }

    public void setC_dataColumna( List<List<String>> c_dataColumna ) {
        //m_log.info( "[setC_dataColumna] << ENTER" );
        this.c_dataColumna = c_dataColumna;
        //m_log.info( "[setC_dataColumna] >> EXIT" );
    }

    public void imprimirFicha( ActionEvent event ) throws Exception {
        int iRep = Integer.parseInt( w_rad_id );
        if ( iRep == ConstantesWeb.RPT_CERTIF_BLOQUE || iRep == ConstantesWeb.RPT_DOC_ADEUDAN_NOTA ) {
            this.setOncomplete( "javascript:Richfaces.showModalPanel('mpReporte')" );
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
        String sNomReporte;

        Connection conn;
        DriverManagerDataSource dmds;
        FacesContext fc;
        InputStream reportStream;
        JasperPrint jasperPrint;
        HashMap hmParams;
        try {
            jasperPrint = null;
            hmParams = new HashMap();
            switch ( idReporte ) {
                case ConstantesWeb.RPT_DOC_ADEUDAN_NOTA:
                    sNomReporte = "rpt_docentes_falta_nota.jasper";
                    break;
                default:
                    sNomReporte = "";
                    break;
            }
            try {

                if ( !sNomReporte.isEmpty() ) {

                    fc = FacesContext.getCurrentInstance();
                    reportStream = fc.getExternalContext().getResourceAsStream( "/WEB-INF/Reportes/cursos_libres/" + sNomReporte );

                    dmds = (DriverManagerDataSource) ServiceFinder.findBean( "dataSource" );
                    conn = dmds.getConnection();
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

    public void seleccionarAlumnos( ActionEvent event ) {
        //m_log.info( "[seleccionarAlumnos] << ENTER" );
        //m_log.info( "[seleccionarAlumnos] >> EXIT" );
    }

    public void cargarData( List<Integer> lstArbCurIds, List<ClArbolAcademico> lstNombre ) {
        //m_log.info( "[cargarData] << ENTER" );
        HSAlumnoCLDAO clAluDAO;
        Object[] obj;
        List lstAluXMod;

        c_cabeceraColumna = new ArrayList<Column>();
        c_cabeceraColumna.add( new Column( "#" ) );
        c_cabeceraColumna.add( new Column( "Nro" ) );
        c_cabeceraColumna.add( new Column( "Codigo" ) );
        c_cabeceraColumna.add( new Column( "            Alumno            " ) );
        if ( !lstNombre.isEmpty() ) {
            for ( int i = 0; i < lstNombre.size(); i++ ) {
                c_cabeceraColumna.add( new Column( "C" + (i + 1), lstNombre.get( i ).getArbDescripcion() ) );
            }
            c_cabeceraColumna.add( new Column( "Prom." ) );
        }

        lstAluXMod = new ArrayList();
        if ( lstArbCurIds.size() > 0 ) {
            clAluDAO = CommonDAO.getClAlumnoDAO();
            lstAluXMod = clAluDAO.listarAlumnosPorModulo( lstArbCurIds, Integer.parseInt( w_ano_id ), w_mes, w_sed_id );
        }
        c_cantiFilas = lstAluXMod.size();
        c_dataColumna = new ArrayList<List<String>>();
        for ( int i = 0; i < c_cantiFilas; i++ ) {
            obj = (Object[]) lstAluXMod.get( i );
            c_dataColumna.add( llenarFila( i + 1, obj[2].toString() + " " + obj[3].toString() + " " + obj[4].toString(), obj[1].toString(), 0, Integer.parseInt( obj[0].toString() ), lstArbCurIds ) );
        }
        //m_log.info( "[cargarData] >> EXIT" );
    }

    private List<String> llenarFila( int contador, String Datos, String codigo, int sec_id, int alu_id, List<Integer> lstArbCurIds ) {
        //m_log.info( "[llenarFila] << ENTER" );
        double dNota;
        double dPromedio;
        String sColorNota;
        String sNota;
        String sSlash;
        String sNotaAux;
        NumberFormat numFormat;
        DecimalFormatSymbols symbol = new DecimalFormatSymbols( Locale.US );
        Object[] obj;
        List<Integer> lstArbCurIdTmp;
        List<String> lista;
        List lstNotas;

        lista = new ArrayList<String>();
        lista.add( String.valueOf( contador ) );
        lista.add( codigo );
        lista.add( Datos );
        dPromedio = 0;
        numFormat = new DecimalFormat( "#.##", symbol );
        lstArbCurIdTmp = new ArrayList<Integer>();
        for ( int i = 0; i < lstArbCurIds.size(); i++ ) {
            lstArbCurIdTmp.clear();
            lstArbCurIdTmp.add( lstArbCurIds.get( i ) );
            if ( !isEsProgAux() ) {
                lstNotas = CommonDAO.getClNotasDAO().seleccionarNotasPorAlumnoModulo( alu_id, lstArbCurIdTmp );
            } else {
                lstNotas = CommonDAO.getClNotasDAO().seleccionarNotasPorAlumnoModuloProgAux( alu_id, lstArbCurIdTmp );
            }
            sNota = lstNotas.isEmpty() ? "-" : "";
            for ( int x = 0; x < lstNotas.size(); x++ ) {
                sSlash = x == 0 ? "" : " / ";
                obj = (Object[]) lstNotas.get( x );
                if ( Integer.parseInt( lstArbCurIds.get( i ).toString() ) == Integer.parseInt( obj[2].toString() ) ) {
                    sNotaAux = obj[0].toString();
                    dNota = Double.parseDouble( sNotaAux );
                    dPromedio += dNota;
                    sColorNota = dNota > 10 ? "blue" : "red";
                    sNota += sSlash + " <span style='color:" + sColorNota + "' >" + numFormat.format( Double.parseDouble( sNotaAux ) ) + "</span> ";
                }
            }
            lista.add( sNota );
        }
        if ( !lstArbCurIds.isEmpty() ) {
            sNota = "";
            dPromedio = Math.round( dPromedio / (double) lstArbCurIds.size() );
            sColorNota = dPromedio > 10 ? "blue" : "red";
            sNota += " <span style='color:" + sColorNota + "' >" + numFormat.format( dPromedio ) + "</span> ";
            lista.add( sNota );
        }
        //m_log.info( "[llenarFila] >> EXIT" );
        return lista;
    }

    public void seleccionarListaAlumnos( ActionEvent event ) {
        //m_log.info( "[seleccionarListaAlumnos] << ENTER" );
        int iSezeCursos;
        ClArbolAcademico clArbCur;
        List<Integer> indices = new ArrayList<Integer>();
        List<ClArbolAcademico> lstArbCursos;
        listaNombreCursos = new ArrayList();
        if ( w_mod_id > 0 && Integer.parseInt( w_ano_id ) > 0 && w_sed_id > 0 ) {
            lstArbCursos = CommonDAO.getClArbolAcademicoDAO().listarArbCursosPorModSedeSedeAnioMes( w_mod_id, w_cur_id, w_sed_id, Integer.parseInt( w_ano_id ), w_mes );
            if ( lstArbCursos != null && !lstArbCursos.isEmpty() ) {
                for ( int i = 0; i < lstArbCursos.size(); i++ ) {
                    indices.add( lstArbCursos.get( i ).getArbId() );
                }
                listaNombreCursos = lstArbCursos;
            }
            cargarData( indices, listaNombreCursos );
        }
        //m_log.info( "[seleccionarListaAlumnos] >> EXIT" );
    }

    public void limpiarData( ActionEvent event ) {
        //m_log.info( "[limpiarData] << ENTER" );
        c_cantiFilas = 0;
        w_mod_id = 0;
        w_cur_id = 0;
        c_cabeceraColumna = new ArrayList<Column>();
        c_dataColumna = new ArrayList<List<String>>();

        cargarData( new ArrayList<Integer>(), new ArrayList<ClArbolAcademico>() );
        //m_log.info( "[limpiarData] >> EXIT" );
    }
}
