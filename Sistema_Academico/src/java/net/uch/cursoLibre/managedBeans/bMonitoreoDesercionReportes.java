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
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.uch.cursoLibre.logicreport.UtilJasper;
import net.uch.cursoLibre.managedBeans.beans.BeanClAlumnoDesertor;
import net.uch.cursoLibre.managedBeans.beans.BeanReporte;
import net.uch.mapping.ClArbolAcademico;
import net.uch.mapping.ClObservacionDesercion;
import net.uch.mapping.TbCatalogo;
import net.uch.util.CommonDAO;
import net.uch.util.CommonWeb;

/**
 *
 * @author USUARIO
 */
public class bMonitoreoDesercionReportes {

    private int m_iAreaId;
    private int m_iModuloId;
    private int m_iCondicionId = 1;
    private String m_sInstId;
    private String m_sOncomplete;
    private BeanClAlumnoDesertor m_beanClAlumDeser;
    private SelectItem[] m_cboAreas;
    private SelectItem[] m_cboInstituciones;
    private SelectItem[] m_cboModulos;
    private SelectItem[] m_lstMotivos;
    private List<String> m_lstMotivosSelec = new ArrayList<String>();
    private List<BeanReporte> m_lstDesertores;
    private List<BeanReporte> m_lstCursosLlevados;
    private List<ClObservacionDesercion> m_lstObsLlamadas;
    private HashMap<String, String> m_hmParameters;

    /**
     * Creates a new instance of bMonitoreoDesercionReportes
     */
    public bMonitoreoDesercionReportes() {
    }

    public int getAreaId() {
        return m_iAreaId;
    }

    public void setAreaId( int iAreaId ) {
        this.m_iAreaId = iAreaId;
    }

    public int getModuloId() {
        return m_iModuloId;
    }

    public void setModuloId( int iModuloId ) {
        this.m_iModuloId = iModuloId;
    }

    public int getCondicionId() {
        return m_iCondicionId;
    }

    public void setCondicionId( int iCondicionId ) {
        this.m_iCondicionId = iCondicionId;
    }

    public String getInstId() {
        return m_sInstId;
    }

    public void setInstId( String sInstId ) {
        this.m_sInstId = sInstId;
    }

    public String getOncomplete() {
        return m_sOncomplete;
    }

    public void setOncomplete( String m_sOncomplete ) {
        this.m_sOncomplete = m_sOncomplete;
    }

    public SelectItem[] getCboAreas() {
        int iSizeAreas;
        ClArbolAcademico arbArea;
        List<ClArbolAcademico> lstAreas;
        m_cboAreas = new SelectItem[ 1 ];
        if ( m_sInstId != null ) {
            lstAreas = CommonDAO.getClArbolAcademicoDAO().AreasXInstitucion( m_sInstId );
            if ( lstAreas != null && !lstAreas.isEmpty() ) {
                m_cboAreas = new SelectItem[ lstAreas.size() + 1 ];
                iSizeAreas = lstAreas.size();
                for ( int i = 0; i < iSizeAreas; i++ ) {
                    arbArea = lstAreas.get( i );
                    m_cboAreas[ i + 1] = new SelectItem( arbArea.getArbId(), arbArea.getArbDescripcion() );
                }
            }
        }
        m_cboAreas[0] = new SelectItem( "0", "[Seleccione]" );
        return m_cboAreas;
    }

    public void setCboAreas( SelectItem[] cboAreas ) {
        this.m_cboAreas = cboAreas;
    }

    public SelectItem[] getCboInstituciones() {
        int iSizeInst;
        String sCodCat;
        TbCatalogo tbCatInst;
        List<TbCatalogo> lstCat;

        m_cboInstituciones = new SelectItem[ 1 ];
        if ( m_cboInstituciones == null || m_cboInstituciones.length == 1 ) {
            lstCat = CommonDAO.getTbCatalogoDAO().seleccionarGrupo( "078" );
            if ( lstCat != null && !lstCat.isEmpty() ) {
                iSizeInst = lstCat.size();
                m_cboInstituciones = new SelectItem[ iSizeInst ];
                for ( int i = 0; i < iSizeInst; i++ ) {
                    tbCatInst = lstCat.get( i );
                    sCodCat = tbCatInst.getCatCodigoGrupo() + tbCatInst.getCatCodigoElemento();
                    if ( !"078004".equals( sCodCat ) ) {
                        m_cboInstituciones[i + 1] = new SelectItem( sCodCat, tbCatInst.getCatDescripcionElemento() );
                    }
                }
            }
        }
        m_cboInstituciones[0] = new SelectItem( "000000", "[Seleccione]" );
        return m_cboInstituciones;
    }

    public void setCboInstituciones( SelectItem[] cboInstituciones ) {
        this.m_cboInstituciones = cboInstituciones;
    }

    public SelectItem[] getCboModulos() {
        int iSizeMod;
        ClArbolAcademico arbMod;
        List<ClArbolAcademico> lstModulos;
        m_cboModulos = new SelectItem[ 1 ];
        if ( m_iAreaId != 0 ) {
            lstModulos = CommonDAO.getClArbolAcademicoDAO().listarArbolPorPadre( m_iAreaId );
            if ( lstModulos != null && !lstModulos.isEmpty() ) {
                m_cboModulos = new SelectItem[ lstModulos.size() + 1 ];
                iSizeMod = lstModulos.size();
                for ( int i = 0; i < iSizeMod; i++ ) {
                    arbMod = lstModulos.get( i );
                    m_cboModulos[ i + 1] = new SelectItem( arbMod.getArbId(), arbMod.getArbDescripcion() );
                }
            }
        }
        m_cboModulos[0] = new SelectItem( "0", "[Seleccione]" );
        return m_cboModulos;
    }

    public void setCboModulos( SelectItem[] m_cboModulos ) {
        this.m_cboModulos = m_cboModulos;
    }

    public List<BeanReporte> getLstDesertores() {

        try {
//            if ( m_iModuloId != 0 ) {
            if ( !"000000".equals( m_sInstId ) && m_sInstId != null ) {
                m_lstDesertores = CommonDAO.getClAlumnoDesertorDAO().listarDesertoresCaptadosPorCaptar( "", m_iModuloId, m_iCondicionId, m_lstMotivosSelec );
            } else {
                m_lstDesertores = new ArrayList<BeanReporte>();
            }

        } catch ( Exception ex ) {
            ex.printStackTrace();
            m_lstDesertores = new ArrayList<BeanReporte>();
        }
        return m_lstDesertores;
    }

    public List<BeanReporte> getLstCursosLlevados() {
        return m_lstCursosLlevados;
    }

    public SelectItem[] getLstMotivos() {
        int iSize;
        TbCatalogo catMot;
        List<TbCatalogo> lstCatMotivos;
        if ( m_lstMotivos == null ) {
            lstCatMotivos = CommonDAO.getTbCatalogoDAO().seleccionarCatalogo( "084" );
            iSize = lstCatMotivos.size();
            m_lstMotivosSelec.clear();
            m_lstMotivosSelec.add( "0" );
            m_lstMotivos = new SelectItem[ iSize + 1 ];
            m_lstMotivos[ 0] = new SelectItem( "0", "[EN BLANCO]" );
            for ( int i = 1; i <= iSize; i++ ) {
                catMot = lstCatMotivos.get( i - 1 );
                m_lstMotivosSelec.add( catMot.getCatCodigoGrupo() + catMot.getCatCodigoElemento() );
                m_lstMotivos[i] = new SelectItem( catMot.getCatCodigoGrupo() + catMot.getCatCodigoElemento(), catMot.getCatDescripcionElemento() );
            }
        }

        return m_lstMotivos;
    }

    public void setLstMotivos( SelectItem[] lstMotivos ) {
        this.m_lstMotivos = lstMotivos;
    }

    public List<String> getLstMotivosSelec() {
        return m_lstMotivosSelec;
    }

    public void setLstMotivosSelec( List<String> lstMotivosSelec ) {
        this.m_lstMotivosSelec = lstMotivosSelec;
    }

    public void setLstCursosLlevados( List<BeanReporte> lstCursosLlevados ) {
        this.m_lstCursosLlevados = lstCursosLlevados;
    }

    public void setLstDesertores( List<BeanReporte> lstDesertores ) {
        this.m_lstDesertores = lstDesertores;
    }

    public List<ClObservacionDesercion> getLstObsLlamadas() {
        if( m_lstObsLlamadas == null ){
            m_lstObsLlamadas =  new ArrayList<ClObservacionDesercion>();
        }
        return m_lstObsLlamadas;
    }

    public void setLstObsLlamadas( List<ClObservacionDesercion> lstObsLlamadas ) {
        this.m_lstObsLlamadas = lstObsLlamadas;
    }

    public void listarCursosLlevados( ActionEvent event ) {
        int iAluId;
        iAluId = CommonWeb.parseObjToInt( CommonWeb.getParamFromUIFacesContext( event, "param_alu_id2" ) );
        m_lstCursosLlevados = CommonDAO.getClAlumnoDAO().listarCursosLlevados( iAluId );
        m_sOncomplete = "Richfaces.showModalPanel('mpLstCursos');";
    }
    public void listarLlamadas( ActionEvent event ) {
        int iAluId;
        int iSecId;
        iAluId = CommonWeb.parseObjToInt( CommonWeb.getParamFromUIFacesContext( event, "param_alu_id" ) );
        iSecId = CommonWeb.parseObjToInt( CommonWeb.getParamFromUIFacesContext( event, "param_sec_id" ) );
        m_lstObsLlamadas = CommonDAO.getClAlumnoDAO().listarLlamadas( iAluId, iSecId );
        m_sOncomplete = "Richfaces.showModalPanel('mpLstLlamadas');";
    }
    
    //REPORTES

    public void imprimirFicha( ActionEvent event ) throws Exception {
        setOncomplete( "" );

        if ( m_iModuloId != 0 ) {
            m_lstDesertores = CommonDAO.getClAlumnoDesertorDAO().listarDesertoresCaptadosPorCaptar( "pdf", m_iModuloId, m_iCondicionId, m_lstMotivosSelec );
        } else {
            m_lstDesertores = new ArrayList<BeanReporte>();
        }

        m_hmParameters = new HashMap<String, String>();
        m_hmParameters.put( "titulo", "REPORTE DE ESTUDIANTES DESERTORES" );
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
            jasperPrint = generateReporteDesertores();
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return jasperPrint;
    }

    public JasperPrint generateReporteDesertores() {
        String sMotivos;
        ClArbolAcademico arbAux;
        FastReportBuilder report;
        Style header;
        Style headerAlu;
        Style grupo;

        report = new FastReportBuilder();
        report.setLeftMargin( 30 ).
                setRightMargin( 30 ).
                setTopMargin( 30 ).
                setBottomMargin( 30 ).
                setDetailHeight( 20 ).
                setHeaderHeight( 15 ).
                setSubtitleHeight( 10 ).
                setPageSizeAndOrientation( Page.Page_A4_Landscape() );

        try {
            if ( !m_lstDesertores.isEmpty() ) {
                sMotivos = "";
                arbAux = CommonDAO.getClArbolAcademicoDAO().buscarArbolPorId( m_iAreaId );
                report.addAutoText( "CENTRO   : " + CommonDAO.getTbCatalogoDAO().seleccionarDescripcion( arbAux.getArbInstitucion() ), AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 300 );
                report.addAutoText( "ÁREA   : " + arbAux.getArbDescripcion(), AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 300 );
                arbAux = CommonDAO.getClArbolAcademicoDAO().buscarArbolPorId( m_iModuloId );
                report.addAutoText( "MODULO   : " + arbAux.getArbDescripcion(), AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 300 );
                report.addAutoText( "", AutoText.POSITION_HEADER, AutoText.ALIGMENT_RIGHT, 300 );
                report.addAutoText( "CONDICIÓN   : " + (m_iCondicionId == 0 ? "POR CAPTAR" : "CAPTADO"), AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 300 );
                for ( String sMot : m_lstMotivosSelec ) {
                    if ( !"0".equals( sMot ) ) {
                        sMotivos += CommonDAO.getTbCatalogoDAO().seleccionarDescripcion( sMot ) + " / ";
                    } else {
                        sMotivos += "[EN BLANCO] / ";
                    }
                }
                if ( !sMotivos.isEmpty() ) {
                    sMotivos = sMotivos.substring( 0, sMotivos.length() - 3 );
                }
                report.addAutoText( "MOTIVOS   : " + sMotivos, AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 300 );
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


            report.addColumn( "Nº", "expr1", String.class.getName(), 8, header );
            report.addColumn( "CÓDIGO", "expr2", String.class.getName(), 10, header );
            report.addColumn( "ALUMNO", "expr3", String.class.getName(), 45, headerAlu );
            report.addColumn( "CURSO", "expr7", String.class.getName(), 20, header );
            report.addColumn( "FEC. TÉRMINO", "expr8", String.class.getName(), 10, header );
            report.addColumn( "MOTIVOS", "expr14", String.class.getName(), 10, header );
            report.addColumn( "OBS. MOTIVOS", "expr15", String.class.getName(), 15, header );

            report.setUseFullPageWidth( true );

            DynamicReport dr = report.build();
            JRDataSource ds = new JRBeanCollectionDataSource( m_lstDesertores );

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
        
        if ( m_iModuloId != 0 ) {
            m_lstDesertores = CommonDAO.getClAlumnoDesertorDAO().listarDesertoresCaptadosPorCaptar( "pdf", m_iModuloId, m_iCondicionId, m_lstMotivosSelec );
        } else {
            m_lstDesertores = new ArrayList<BeanReporte>();
        }
        jasperPrint = generateReporteDesertores();

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
