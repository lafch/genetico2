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
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.uch.cursoLibre.hibernateSpringDao.HSHorarioDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSAlumnoCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSSistemaEvaluacionCLDAO;
import net.uch.cursoLibre.managedBeans.beans.BeanClAlumno;
import net.uch.cursoLibre.managedBeans.beans.Column;
import net.uch.cursoLibre.utilsAcademico.Metodos;
import net.uch.mapping.ClArbolAcademico;
import net.uch.mapping.ClArbolAperturado;
import net.uch.mapping.ClHoraria;
import net.uch.mapping.ClSeccion;
import net.uch.mapping.TbCatalogo;
import net.uch.util.CommonDAO;
import net.uch.utilAdministrativo.MetodosAdm;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author Alumno
 */
public class bConsultaSeccion {

    private SelectItem[] cboSede;
    private SelectItem[] cboAno;
    private SelectItem[] cboMes;
    private SelectItem[] cboArea;
    private SelectItem[] cboModulo;
    private SelectItem[] cboCurso;
    private SelectItem[] cboDia;
    private SelectItem[] cboSeccion;
    private SelectItem[] rdoOpciones;
    private boolean blContieneSisEva = true;
    private int w_sed_id = 0;
    private String w_ano_id = "0";
    private String w_dia_id = "0";
    private int w_mes = 0;
    private int w_area_id = 0;
    private int w_mod_id = 0;
    private int w_cur_id = 0;
    private int w_sec_id = 0;
    private int w_rad_id;
    private Date w_fecha_ini;
    private Date w_fecha_fin;
    private String w_horario;
    private int w_contador = 0;
    private String v_visible = "true";
    Boolean type;
    Boolean description;
    Integer maxValue;
    private String sec_horario;
    private String sec_horario_corto;
    private String w_curso;
    private String w_docente;
    /*
     *
     */
    private List<BeanClAlumno> listaAlumno = new ArrayList<BeanClAlumno>();
    /*
     * variables tabla
     */
    List<Column> c_cabeceraColumna = new ArrayList<Column>();
    List<List<String>> c_dataColumna = new ArrayList<List<String>>();
    Integer c_cantiFilas = 0;
    List<List<String>> c_dataAlumno = new ArrayList<List<String>>();
    private String oncomplete;

    public void cambioSede( ActionEvent event ) {
        this.setW_ano_id( "0" );
        cambioAnio( null );
    }

    public void cambioAnio( ActionEvent event ) {
        this.setW_area_id( 0 );
        cambioArea( null );
    }

    public void cambioArea( ActionEvent event ) {
        w_mod_id = 0;
        w_cur_id = 0;
        w_sec_id = 0;
    }

    public void cambioModulo( ActionEvent event ) {
        w_cur_id = 0;
        w_sec_id = 0;
    }

    public void cambioCurso( ActionEvent event ) {
        w_sec_id = 0;
    }

    public SelectItem[] getCboDia () {
        try {

            List lstDias = CommonDAO.getTbCatalogoDAO().seleccionarCatalogo( "016" );
            if ( lstDias.size() > 0 ) {
                cboDia = new SelectItem[ lstDias.size() + 1 ];
                cboDia[0] = new SelectItem( 0, "[[-TODOS-]]" );
                for ( int i = 0; i < lstDias.size(); i++ ) {
                    cboDia[i + 1] = new SelectItem( ((TbCatalogo) lstDias.get( i )).getCatCodigoGrupo() + ((TbCatalogo) lstDias.get( i )).getCatCodigoElemento(), ((TbCatalogo) lstDias.get( i )).getCatDescripcionElemento() );
                }
            } else {
                cboDia = new SelectItem[ 1 ];
                cboDia[0] = new SelectItem( 0, "[[-TODOS-]]" );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        
        return cboDia;
    }

    public void setCboDia ( SelectItem[] cboDia ) {
        this.cboDia = cboDia;
    }

    public String getW_dia_id () {
        return w_dia_id;
    }

    public void setW_dia_id ( String w_dia_id ) {
        this.w_dia_id = w_dia_id;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete( String sOncomplete ) {
        this.oncomplete = sOncomplete;
    }

    public SelectItem[] getCboMes () {
        
        Metodos metodo = new Metodos();
        cboMes = metodo.cboMes();
        return cboMes;
    }

    public void setCboMes ( SelectItem[] cboMes ) {
        this.cboMes = cboMes;
    }

    public int getW_mes () {
        return w_mes;
    }

    public void setW_mes ( int w_mes ) {
        this.w_mes = w_mes;
    }

    public String getSec_horario() {
        return sec_horario;
    }

    public void setSec_horario( String sSecHorario ) {
        this.sec_horario = sSecHorario;
    }

    public String getSec_horario_corto() {
        return sec_horario_corto;
    }

    public void setSec_horario_corto( String sSecHorarioCorto ) {
        this.sec_horario_corto = sSecHorarioCorto;
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

    public bConsultaSeccion() {
        cargarData( 0 );
    }

    public Boolean getDescription() {
        return description;
    }

    public void setDescription( Boolean bDescription ) {
        this.description = bDescription;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue( Integer iMaxValue ) {
        this.maxValue = iMaxValue;
    }

    public Boolean getType() {
        return type;
    }

    public void setType( Boolean bType ) {
        this.type = bType;
    }

    public String getV_visible() {
        return v_visible;
    }

    public void setV_visible( String sVVisible ) {
        this.v_visible = sVVisible;
    }

    public int getW_contador() {
        return w_contador;
    }

    public void setW_contador( int iWContador ) {
        this.w_contador = iWContador;
    }

    public List<BeanClAlumno> getListaAlumno() {
        return listaAlumno;
    }

    public void setListaAlumno( List<BeanClAlumno> lstAlumno ) {
        this.listaAlumno = lstAlumno;
    }

    public boolean isContieneSisEva() {
        return blContieneSisEva;
    }

    public void setContieneSisEva( boolean blContieneSisEva ) {
        this.blContieneSisEva = blContieneSisEva;
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
        for ( int i = 0; i < (this.cboAno.length - 1); i++ ) {
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
            if ( !"".equals( w_ano_id ) && Integer.parseInt( w_ano_id ) > 0 && w_sed_id > 0 ) {
                lstArbAreas = CommonDAO.getClArbolAcademicoDAO().listarAreaxAnoYSede( Integer.parseInt( w_ano_id ), w_sed_id, 0 );
            }
            
            cboArea = new SelectItem[ lstArbAreas.size() + 1 ];
            cboArea[0] = new SelectItem( 0, "[[Seleccione Área]]" );
            for ( int i = 0; i < (cboArea.length - 1); i++ ) {
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
            if ( w_area_id != 0 && !"".equals( w_ano_id ) && Integer.parseInt( w_ano_id ) > 0 && w_sed_id > 0 ) {
                lstArbModulos = CommonDAO.getClArbolAcademicoDAO().listarModulosxAreaAnoYSede( w_area_id, Integer.parseInt( w_ano_id ), w_sed_id );
            }
            cboModulo = new SelectItem[ lstArbModulos.size() + 1 ];
            cboModulo[0] = new SelectItem( 0, "[[Seleccione Modulo]]" );
            for ( int i = 0; i < (cboModulo.length - 1); i++ ) {
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


            if ( w_mod_id > 0 && w_area_id > 0 && w_sed_id > 0 && !"".equals( w_ano_id ) && Integer.parseInt( w_ano_id ) > 0 ) {
                lstArbCursos = CommonDAO.getClArbolAcademicoDAO().listarCursosXModAnoYSede( w_mod_id, Integer.parseInt( w_ano_id ), w_sed_id );
            }
            cboCurso = new SelectItem[ lstArbCursos.size() + 1 ];
            cboCurso[0] = new SelectItem( "0", "[[--Seleccione--]])" );
            for ( int i = 0; i < (cboCurso.length - 1); i++ ) {
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
            for ( int i = 0; i < (cboSeccion.length - 1); i++ ) {
                clSeccion = lstSecciones.get( i );
                cboSeccion[i + 1] = new SelectItem( clSeccion.getSecId(), clSeccion.getSecNombre() );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return cboSeccion;
    }

    public void setCboSeccion( SelectItem[] cboSeccion ) {
        cboSeccion = cboSeccion;
    }

    public SelectItem[] getRdoOpciones() {
        rdoOpciones = new SelectItem[ 3 ];
        rdoOpciones[0] = new SelectItem( "1", "REGISTRO POR AULA" );
        rdoOpciones[1] = new SelectItem( "2", "INSCRITOS POR AULA" );
        rdoOpciones[2] = new SelectItem( "3", "REGISTRO AUXILIAR POR AULA" );
        return rdoOpciones;
    }

    public void setRdoOpciones( SelectItem[] rdoOpciones ) {
        rdoOpciones = rdoOpciones;
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

    public int getW_rad_id() {
        return w_rad_id;
    }

    public void setW_rad_id( int w_rad_id ) {
        this.w_rad_id = w_rad_id;
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

    public void seleccionarSeccion( ActionEvent event ) throws Exception {
        String sDescDia;
        SimpleDateFormat sdfHora;
        ClSeccion seccion;
        HSHorarioDAO horarioDAO;
        List<ClHoraria> lstHorario;
        List<ClHoraria> lstHorarioAux;

        listaAlumno = new ArrayList<BeanClAlumno>();

        horarioDAO = CommonDAO.getClHorarioDAO();
        lstHorario = horarioDAO.seleccionarHorario( w_sec_id );

        lstHorarioAux = horarioDAO.seleccionarHorario( w_sec_id );
        w_docente = lstHorarioAux.isEmpty() ? "No determinado" : lstHorarioAux.get( 0 ).getAcDocente().getDocNombre();
        sec_horario = "Horario no Definido";
        sec_horario_corto = "Horario no Definido";

        if ( !lstHorario.isEmpty() ) {
            sec_horario = "";
            sec_horario_corto = "";
        }

        for ( int i = 0; i < lstHorario.size(); i++ ) {
            sDescDia = CommonDAO.getTbCatalogoDAO().seleccionarDescripcion( lstHorario.get( i ).getHorDia() );
            sdfHora = new SimpleDateFormat( "hh:mm a" );
            sec_horario = sec_horario + " " + sDescDia + "  :  " + sdfHora.format( lstHorario.get( i ).getHorHini() ) + "  a  " + sdfHora.format( lstHorario.get( i ).getHorHfin() ) + "  <br />  ";
        }
        if ( !lstHorario.isEmpty() ) {
            this.sec_horario_corto = this.sec_horario.substring( 1, 15 );
        }

        if ( w_sec_id > 0 ) {
            seccion = CommonDAO.getClSeccionDAO().seleccionarSeccion( w_sec_id );
            this.setW_fecha_ini( seccion.getSecFinicio() );
            this.setW_fecha_fin( seccion.getSecFfin() );
            this.setW_curso( seccion.getClArbolAcademico().getArbDescripcion() );
        }
        cargarData( w_sec_id );

    }

    public void generarTabla( ActionEvent event ) {
    }

    public void cargarData( int iSecId ) {
        ClSeccion clSeccion;
        ClArbolAperturado clArbApertTall;
        HSAlumnoCLDAO clAlumnoDAO;
        HSSistemaEvaluacionCLDAO sisEvaDAO;
        Object[] obj;
        List lstAlumnosXSeccion;
        List lstSisEvaluacionXCurso;


        c_cabeceraColumna = new ArrayList<Column>();
        c_cabeceraColumna.add( new Column( "#" ) );
        c_cabeceraColumna.add( new Column( "Nro" ) );
        c_cabeceraColumna.add( new Column( "Codigo" ) );
        c_cabeceraColumna.add( new Column( "Alumno" ) );
        if ( iSecId == 0 ) {
            return;
        }
        clAlumnoDAO = CommonDAO.getClAlumnoDAO();

        sisEvaDAO = CommonDAO.getClSistemaEvaluacionDAO();
        lstSisEvaluacionXCurso = new ArrayList();
        try {

            clSeccion = CommonDAO.getClSeccionDAO().seleccionarSeccion( iSecId );
            clArbApertTall = clSeccion.getClArbolAperturado();
            if ( clArbApertTall != null ) {
                lstSisEvaluacionXCurso = sisEvaDAO.listarSistemaEvaluacionPorCurso( clArbApertTall.getArbapeId() );
                for ( int i = 0; i < lstSisEvaluacionXCurso.size(); i++ ) {
                    obj = (Object[]) lstSisEvaluacionXCurso.get( i );
                    c_cabeceraColumna.add( new Column( obj[1].toString() ) );
                }
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }

        lstAlumnosXSeccion = clAlumnoDAO.listarAlumnosPorSeccion( iSecId );
        c_cantiFilas = lstAlumnosXSeccion.size();
        c_dataColumna = new ArrayList<List<String>>();
        c_dataAlumno = new ArrayList<List<String>>();

        blContieneSisEva = !lstSisEvaluacionXCurso.isEmpty();
        
        for ( int i = 0; i < c_cantiFilas; i++ ) {
            obj = (Object[]) lstAlumnosXSeccion.get( i );
            c_dataColumna.add( llenarFila( i + 1, obj[0].toString() + " " + obj[1].toString() + " " + obj[2].toString(), obj[4].toString(),
                    lstSisEvaluacionXCurso, iSecId, Integer.parseInt( obj[3].toString() ) ) );
        }

    }

    private List<String> llenarFila( int iContador, String sDatos, String sCodigo, List lstSisEvaluacion, int iSecId, int iAluId ) {
        double dValor;
        NumberFormat numFormatter;
        Object[] aobjNota;
        Object[] aobjSisEva;
        List<String> lstAluStrings;
        List<String> lstStrings;
        List lstNotas;

        lstStrings = new ArrayList<String>();
        lstAluStrings = new ArrayList<String>();

        lstStrings.add( String.valueOf( iContador ) );
        lstStrings.add( sCodigo );
        lstStrings.add( sDatos );
        lstAluStrings.add( String.valueOf( iContador ) );
        lstAluStrings.add( sCodigo );
        lstAluStrings.add( String.valueOf( iAluId ) );

        numFormatter = new DecimalFormat( "##" );
        for ( int i = 0; i < lstSisEvaluacion.size(); i++ ) {
            aobjSisEva = (Object[]) lstSisEvaluacion.get( i );
            lstNotas = CommonDAO.getClNotasDAO().seleccionarNotaAlumnoSeccion( iAluId, iSecId, Integer.parseInt( aobjSisEva[0].toString() ) );
            if ( lstNotas != null && !lstNotas.isEmpty() ) {
                aobjNota = (Object[]) lstNotas.get( 0 );
                dValor = 0;
                if ( aobjNota.length > 0 ) {
                    dValor = Double.parseDouble( aobjNota[1].toString() );
                }
                lstStrings.add( numFormatter.format( dValor ) );
                lstAluStrings.add( aobjSisEva[0].toString() );
            }
        }

        c_dataAlumno.add( lstAluStrings );
        return lstStrings;
    }

    public List<Column> getC_cabeceraColumna() {
        return c_cabeceraColumna;
    }

    public Integer getC_cantiFilas() {
        return c_cantiFilas;
    }

    public void setC_cantiFilas( Integer c_cantiFilas ) {
        this.c_cantiFilas = c_cantiFilas;
    }

    public List<List<String>> getC_dataColumna() {
        return c_dataColumna;
    }

    public boolean filterMethod( Object aobj ) {
        Integer[] i = (Integer[]) aobj;
        if ( i[0] > maxValue ) {
            return false;
        }
        return true;
    }

    public void guardarNotas( ActionEvent event ) {
        int alu_id;
        double dNota;

        for ( int i = 0; i < c_dataColumna.size(); i++ ) {
            alu_id = 0;
            for ( int x = 0; x < (c_dataColumna.get( i ).size()); x++ ) {

                if ( x == 2 ) {
                    alu_id = Integer.parseInt( c_dataAlumno.get( i ).get( x ).toString() );
                }
                if ( x >= 3 ) {
                    dNota = Double.parseDouble( c_dataColumna.get( i ).get( x ) );
                    CommonDAO.getClNotasDAO().actualizarNota( w_sec_id, alu_id, Integer.parseInt( c_dataAlumno.get( i ).get( x ).toString() ), dNota );
                }
            }
        }
    }

//    public List<ClArbolAcademico> agregarHijo( int iIdPadre, String sTipo, List<ClArbolAcademico> listaArbol ) {
//        List<ClArbolAcademico> lstArbHijos;
//
//        lstArbHijos = CommonDAO.getClArbolAcademicoDAO().listarArbolPorPadre( iIdPadre );
//
//        for ( int i = 0; i < lstArbHijos.size(); i++ ) {
//            if ( lstArbHijos.get( i ).getArbTipo().equals( sTipo ) ) {
//                listaArbol.add( lstArbHijos.get( i ) );
//            } else {
//                agregarHijo( lstArbHijos.get( i ).getArbId(), sTipo, listaArbol );
//            }
//        }
//
//        return listaArbol;
//    }
    public void ImprimirFicha( ActionEvent event ) throws Exception {
        oncomplete = "javascript:Richfaces.showModalPanel('mp7')";
    }

    public void cargarReporte( OutputStream out, Object data ) throws IOException, Exception, EOFException {
        cargarReporteDynamic( out );
    }

    public void cargarReporteDynamic( OutputStream out ) throws IOException, Exception, EOFException {
        int iSize;
        ByteArrayOutputStream buffer;
        byte[] abytes;
        byte[] apdf;

        buffer = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream( cargar_reporteDynamic( w_rad_id ), buffer );
        abytes = buffer.toByteArray();
        InputStream input = new ByteArrayInputStream( abytes );
        iSize = input.available();
        apdf = new byte[ iSize ];
        input.read( apdf );
        out.write( apdf );
        buffer.flush();
        buffer.close();
        input.close();
        out.flush();
        out.close();

    }

    public JasperPrint cargar_reporteDynamic( int iIdReporte ) {
        String sNombreReporte;
        String[] sNombresReporte = { "",
            "rep_registro_por_aula.jasper",
            "rep_alumnos_inscritos_por_seccion.jasper",
            "rep_registro_por_aula_auxiliar.jasper" };
        Connection connection;
        DriverManagerDataSource dmds;
        FacesContext context;
        InputStream reportStream;
        JasperPrint jasperPrint;

        HashMap hmParams;

        try {

            sNombreReporte = sNombresReporte[iIdReporte];

            jasperPrint = null;
            if ( !sNombreReporte.isEmpty() ) {
                context = FacesContext.getCurrentInstance();
                reportStream = context.getExternalContext().getResourceAsStream( "/WEB-INF/Reportes/cursos_libres/" + sNombreReporte );

                dmds = (DriverManagerDataSource) ServiceFinder.findBean( "dataSource" );
                connection = dmds.getConnection();
                hmParams = new HashMap();
                hmParams.put( "p_sec_id", String.valueOf( w_sec_id ) );

                jasperPrint = JasperFillManager.fillReport( reportStream, hmParams, connection );
                connection.close();
            }

            return jasperPrint;

        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void seleccionarListaSecciones( ActionEvent event ) {

        if(w_sed_id>0 && !"0".equals(w_ano_id ) && w_area_id >0){
            
            System.out.println( "-----ENTRA A LISTAR----" );
            System.out.println( "sede: "+w_sed_id );
            System.out.println( "año: "+w_ano_id );
            System.out.println( "mes: "+ w_mes );
            System.out.println( "dias: "+ w_dia_id );
            System.out.println( "area:" + w_area_id);
            System.out.println( "modulo: "+ w_mod_id);
            System.out.println( "curso: "+ w_cur_id );
            
        }  
    }
}
