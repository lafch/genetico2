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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.faces.application.Application;
import javax.faces.component.UIInput;
import javax.faces.component.UIParameter;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSAreaDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSCursoCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSMatriculaCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSModuloDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSNotasCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSSeccionCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSSistemaEvaluacionCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAperturadoClDAO;
import net.uch.cursoLibre.logicreport.ReporteRegistroNota;
import net.uch.mapping.ClAlumno;
import net.uch.mapping.ClArea;
import net.uch.mapping.ClCurso;
import net.uch.mapping.ClMatricula;
import net.uch.mapping.ClModulo;
import net.uch.mapping.ClNota;
import net.uch.mapping.ClSeccion;
import net.uch.mapping.ClSisEvaPersonalizado;
import net.uch.mapping.ClTallerAperturado;
import net.uch.util.ObjNodeCLGnral;
import org.richfaces.component.UITree;
import org.richfaces.component.html.HtmlSeparator;
import org.richfaces.component.html.HtmlSpacer;
import org.richfaces.event.NodeSelectedEvent;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;

/**
 *
 * @author cesardl
 */
public class bNotasCL {

    private int talape_id;
    private String talape_nombre;
    private String cur_nombre;
    private String mod_nombre;
    private String oncomplete;
    //#################################De la tabla
    private int sec_id;
    private String sec_cod;
    private String sec_nom;
    private Date sec_fini;
    private Date sec_ffin;
    private int mat_num;
    //#################################De la modal
    private HtmlPanelGrid htmlPanel;
    private int nro_columnas;
    private List hidden;
    private int p_sec_id;
    private String p_rep_value;
    private int p_talape_id;
    private List<ClSisEvaPersonalizado> columns;
    private List<ClMatricula> rows;
    //#################################De la pagina
    private List<bNotasCL> listaTalleres;
    private TreeNode arbol;
    private List<ObjNodeCLGnral> nodos;

    public bNotasCL() {
        this.p_rep_value = "";
    }

    //SETTERS Y GETTERS
//    public TreeNode getArbol() {
//        if ( arbol == null ) {
//            cargarArbol();
//        }
//        return arbol;
//    }
    public void setArbol( TreeNode arbol ) {
        this.arbol = arbol;
    }

    public List<ObjNodeCLGnral> getNodos() {
        return nodos;
    }

    public void setNodos( List<ObjNodeCLGnral> nodos ) {
        this.nodos = nodos;
    }

    public List<bNotasCL> getListaTalleres() {
        return listaTalleres;
    }

    public void setListaTalleres( List<bNotasCL> listaTalleres ) {
        this.listaTalleres = listaTalleres;
    }

    public int getTalape_id() {
        return talape_id;
    }

    public void setTalape_id( int talape_id ) {
        this.talape_id = talape_id;
    }

    public String getTalape_nombre() {
        return talape_nombre;
    }

    public void setTalape_nombre( String talape_nombre ) {
        this.talape_nombre = talape_nombre;
    }

    public String getCur_nombre() {
        return cur_nombre;
    }

    public void setCur_nombre( String cur_nombre ) {
        this.cur_nombre = cur_nombre;
    }

    public String getMod_nombre() {
        return mod_nombre;
    }

    public void setMod_nombre( String mod_nombre ) {
        this.mod_nombre = mod_nombre;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete( String oncomplete ) {
        this.oncomplete = oncomplete;
    }

    public Date getSec_fini() {
        return sec_fini;
    }

    public void setSec_fini( Date sec_fini ) {
        this.sec_fini = sec_fini;
    }

    public Date getSec_ffin() {
        return sec_ffin;
    }

    public void setSec_ffin( Date sec_ffin ) {
        this.sec_ffin = sec_ffin;
    }

    public int getMat_num() {
        return mat_num;
    }

    public void setMat_num( int mat_num ) {
        this.mat_num = mat_num;
    }

    public int getSec_id() {
        return sec_id;
    }

    public void setSec_id( int sec_id ) {
        this.sec_id = sec_id;
    }

    public String getSec_cod() {
        return sec_cod;
    }

    public void setSec_cod( String sec_cod ) {
        this.sec_cod = sec_cod;
    }

    public String getSec_nom() {
        return sec_nom;
    }

    public void setSec_nom( String sec_nom ) {
        this.sec_nom = sec_nom;
    }

    public List<ClSisEvaPersonalizado> getColumns() {
        return columns;
    }

    public void setColumns( List<ClSisEvaPersonalizado> columns ) {
        this.columns = columns;
    }

    public List<ClMatricula> getRows() {
        return rows;
    }

    public void setRows( List<ClMatricula> rows ) {
        this.rows = rows;
    }

    public HtmlPanelGrid getHtmlPanel() {
        return htmlPanel;
    }

    public void setHtmlPanel( HtmlPanelGrid htmlPanel ) {
        this.htmlPanel = htmlPanel;
    }

    public int getNro_columnas() {
        return nro_columnas;
    }

    public void setNro_columnas( int nro_columnas ) {
        this.nro_columnas = nro_columnas;
    }

    public int getP_sec_id() {
        return p_sec_id;
    }

    public void setP_sec_id( int p_sec_id ) {
        this.p_sec_id = p_sec_id;
    }

    public String getP_rep_value() {
        return p_rep_value;
    }

    public void setP_rep_value( String p_rep_value ) {
        this.p_rep_value = p_rep_value;
    }

    public int getP_talape_id() {
        return p_talape_id;
    }

    public void setP_talape_id( int p_talape_id ) {
        this.p_talape_id = p_talape_id;
    }

    //FIN SETTERS Y GETTERS
//    public void cargarArbol() {
//        String sEstructura;
//        StringBuffer buffer;
//        ByteArrayInputStream sEstructuraArbol;
//        Properties properties;
//        try {
//            arbol = new TreeNodeImpl();
//            sEstructura = armarEstructura();
//            buffer = new StringBuffer( sEstructura );
//            sEstructuraArbol = new ByteArrayInputStream( buffer.toString().getBytes( "ISO-8859-1" ) );
//            properties = new Properties();
//            properties.load( sEstructuraArbol );
//            arbol = new TreeNodeImpl();
//            agregarNodos( null, arbol, properties );
//        } catch ( IOException ioe ) {
//            ioe.printStackTrace();
//        } catch ( Exception e ) {
//            e.printStackTrace();
//        }
//    }
    public void agregarNodos( String sPath, TreeNode node, Properties properties ) {
        boolean blEnd = false;
        int iCon = 1;
        String sKey;
        String sValue;
        TreeNodeImpl nodo;
        while ( !blEnd ) {
            sKey = sPath != null ? sPath + '.' + iCon : String.valueOf( iCon );
            sValue = properties.getProperty( sKey );
            if ( sValue != null ) {
                nodo = new TreeNodeImpl();
                nodo.setData( sValue );
                node.addChild( new Integer( iCon ), nodo );
                agregarNodos( sKey, nodo, properties );
                iCon++;
            } else {
                blEnd = true;
            }
        }
    }

    public ObjNodeCLGnral buscarNodo( String id ) {
        ObjNodeCLGnral nodo;
        for ( int i = 0; i < nodos.size(); i++ ) {
            nodo = nodos.get( i );
            if ( nodo.getId().equalsIgnoreCase( id ) ) {
                return nodo;
            }
        }
        return null;
    }

    public void seleccion( NodeSelectedEvent event ) {
        int iLength;
        String sId;
        ObjNodeCLGnral nodo;
        UITree tree;
        try {
            tree = (UITree) event.getComponent();
            sId = tree.getRowKey().toString();
            iLength = sId.length();

            if ( iLength >= 7 ) {
                nodo = buscarNodo( sId );

                this.setTalape_id( nodo.getTalape_id() );
                this.setMod_nombre( nodo.getMod_nombre() );
                this.setCur_nombre( nodo.getCur_nombre() );
                this.setTalape_nombre( nodo.getTalape_nombre() );

                seleccionarTaller( this.getTalape_id() );
            }
        } catch ( Exception e ) {
            System.err.println( "Error al seleccionar el nodo " + e.getMessage() );
        }
    }

    public void seleccionarTaller( int talape_id ) {
        int iTotalMat;
        bNotasCL nota;
        ClSeccion seccion;
        HSMatriculaCLDAO matClDAO;
        HSSeccionCLDAO seccionClDAO;
        List<bNotasCL> lstNotasCl;
        List<ClSeccion> lstClSeccion;
        try {
            seccionClDAO = (HSSeccionCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLSeccion" );
            matClDAO = (HSMatriculaCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLMatricula" );

            lstClSeccion = seccionClDAO.seleccionarSecciones( talape_id );
            lstNotasCl = new ArrayList<bNotasCL>();

            for ( int i = 0; i < lstClSeccion.size(); i++ ) {
                seccion = lstClSeccion.get( i );

                nota = new bNotasCL();
                nota.setTalape_id( talape_id );
                nota.setSec_id( seccion.getSecId() );
                nota.setSec_cod( seccion.getSecCodigo() );
                nota.setSec_nom( seccion.getSecNombre() );
                nota.setSec_fini( seccion.getSecFinicio() );
                nota.setSec_ffin( seccion.getSecFfin() );

                iTotalMat = matClDAO.totalMatriculadosSeccion( seccion.getSecId(), talape_id, 0 ).size();
                nota.setMat_num( iTotalMat );

                lstNotasCl.add( i, nota );
            }
            this.setListaTalleres( lstNotasCl );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void asignarNotas( ActionEvent event ) {
        UIParameter uiSecId = (UIParameter) event.getComponent().findComponent( "p_sec_id" );
        UIParameter uiTalapeId = (UIParameter) event.getComponent().findComponent( "p_talape_id" );

        this.setP_sec_id( Integer.parseInt( String.valueOf( uiSecId.getValue() ) ) );
        this.setP_talape_id( Integer.parseInt( String.valueOf( uiTalapeId.getValue() ) ) );

        this.armarRegistro( this.getP_sec_id(), this.getP_talape_id() );
    }

    private void armarRegistro( int iSecId, int iTalapeId ) {
        HSMatriculaCLDAO matClDAO;
        HSSistemaEvaluacionCLDAO sisEvalClDAO;
        HSArbolAperturadoClDAO talApeDAO;
        List<ClSisEvaPersonalizado> lstSisEval;
        List<ClMatricula> lstMatXSeccion;

        matClDAO = (HSMatriculaCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLMatricula" );
        sisEvalClDAO = (HSSistemaEvaluacionCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLSistemaEvaluacion" );
        talApeDAO = (HSArbolAperturadoClDAO) ServiceFinder.findBean( "HibernateSpringDaoTargetCLTallerAperturado" );

        lstSisEval = sisEvalClDAO.seleccionarSisEvaPer_ClTalape( talApeDAO.seleccionarArbTallerAperturado( iTalapeId ) );

        lstMatXSeccion = matClDAO.totalMatriculadosSeccion( iSecId, iTalapeId, 1 );

        this.setRows( lstMatXSeccion );
        this.setColumns( lstSisEval );
        this.armarTablaNotas( lstMatXSeccion, lstSisEval, iSecId );
    }

    private void armarTablaNotas( List<ClMatricula> rows, List<ClSisEvaPersonalizado> columns, int iSecId ) {
        int iValorId;
        String sAluNombres;
        String sValorNota;
        Application app;
        ClAlumno clAlumno;
        ClMatricula clMatricula;
        ClNota clNota;
        HtmlInputHidden hih;
        HtmlInputText hit;
        HtmlOutputText hot;
        HtmlSeparator hse;
        HtmlSpacer hsp;
        HSNotasCLDAO notasClDAO;

        List lstChildrenList;
        List<ClNota> lstNotas;

        hidden = new ArrayList();

        lstChildrenList = getHtmlPanel().getChildren();
        lstChildrenList.clear();

        app = FacesContext.getCurrentInstance().getApplication();

        hot = (HtmlOutputText) app.createComponent( HtmlOutputText.COMPONENT_TYPE );

        hot.setValue( "APELLIDOS Y NOMBRES" );
        hot.setStyle( "font-weight: bold;width:350px" );
        lstChildrenList.add( hot );

        this.setNro_columnas( columns.size() + 1 );

        for ( int i = 0; i < columns.size(); i++ ) {
            hot = (HtmlOutputText) app.createComponent( HtmlOutputText.COMPONENT_TYPE );
            hot.setValue( columns.get( i ).getSisevaCodigo() );
            hot.setStyle( "font-weight: bold;" );
            lstChildrenList.add( hot );
        }
        for ( int i = 0; i < columns.size() + 1; i++ ) {
            hse = new HtmlSeparator();
            hse.setAlign( "center" );
            hse.setWidth( "100%" );
            hse.setHeight( "3px" );
            lstChildrenList.add( hse );
        }

        for ( int i = 0; i < columns.size() + 1; i++ ) {
            hsp = new HtmlSpacer();
            hsp.setWidth( "100%" );
            hsp.setHeight( "5px" );
            lstChildrenList.add( hsp );
        }


        notasClDAO = (HSNotasCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLNotas" );
        lstNotas = notasClDAO.seleccionarNotas( iSecId );


        for ( int i = 0; i < rows.size(); i++ ) {
            clMatricula = rows.get( i );

            clAlumno = clMatricula.getClAlumno();
            sAluNombres = clAlumno.getAluAppaterno() + " " + clAlumno.getAluApmaterno() + ", " + clAlumno.getAluNombres();

            hot = (HtmlOutputText) app.createComponent( HtmlOutputText.COMPONENT_TYPE );
            hot.setValue( (i + 1) + ".- " + sAluNombres );

            lstChildrenList.add( hot );
            for ( int j = 0; j < columns.size(); j++ ) {
                hit = (HtmlInputText) app.createComponent( HtmlInputText.COMPONENT_TYPE );
                hih = (HtmlInputHidden) app.createComponent( HtmlInputHidden.COMPONENT_TYPE );
                sValorNota = "0";
                iValorId = 0;
                if ( !lstNotas.isEmpty() ) {
                    for ( int k = 0; k < lstNotas.size(); k++ ) {
                        clNota = lstNotas.get( k );

                        if ( clNota.getClAlumno().getAluId().compareTo( rows.get( i ).getClAlumno().getAluId() ) == 0
                                && clNota.getClSisEvaPersonalizado().getSisevaPerId().compareTo( columns.get( j ).getSisevaPerId() ) == 0 ) {

                            sValorNota = String.valueOf( clNota.getNotNota() );
                            iValorId = clNota.getNotId();
                        }
                    }
                }

                hih.setId( "h" + rows.get( i ).getClAlumno().getAluId() + "-" + columns.get( j ).getSisevaPerId() );
                hih.setValue( iValorId );
                if ( j % 2 == 0 ) {
                    hit.setStyle( "width:35px;background-color: #ffffff" );
                } else {
                    hit.setStyle( "width:35px;background-color: #bbbbbb" );
                }
                hit.setId( "id" + rows.get( i ).getClAlumno().getAluId() + "-" + columns.get( j ).getSisevaPerId() );
                float nota = Float.parseFloat( sValorNota );
                if ( nota < 10.5 ) {
                    hit.setStyle( "color: #cc0000;" + hit.getStyle() );
                } else {
                    hit.setStyle( "color: #000099;" + hit.getStyle() );
                }
                hit.setValue( sValorNota );
                lstChildrenList.add( hit );
                hidden.add( hih );
            }
        }
    }

    public void guardarNotas( ActionEvent event ) {
        boolean band;
        int iIdNota;
        String sParamHidd1;
        String sParamHidd2;
        String sValorNota;
        ClNota clNota;
        ClSeccion clSeccion;
        HSNotasCLDAO notasClDAO;
        UIInput ui_hidden;
        List<ClNota> notas = new ArrayList<ClNota>();
        List<ClMatricula> lrow = this.getRows();
        List<ClSisEvaPersonalizado> lcol = this.getColumns();


        band = false;
        this.setOncomplete( "" );

        root:
        for ( int i = 0; i < lrow.size(); i++ ) {
            for ( int j = 0; j < lcol.size(); j++ ) {
                sParamHidd1 = "id" + lrow.get( i ).getClAlumno().getAluId() + "-" + lcol.get( j ).getSisevaPerId();
                sParamHidd2 = "h" + lrow.get( i ).getClAlumno().getAluId() + "-" + lcol.get( j ).getSisevaPerId();

                sValorNota = ((UIInput) event.getComponent().findComponent( sParamHidd1 )).getValue().toString();

                if ( Float.parseFloat( sValorNota ) > 150 ) {
                    band = true;
                    break root;
                } else {
                    if ( sValorNota.trim().length() <= 0 ) {
                        sValorNota = "0";
                    }
                    iIdNota = 0;
                    for ( int k = 0; k < hidden.size(); k++ ) {
                        ui_hidden = (UIInput) hidden.get( k );
                        if ( ui_hidden.getId().toString().equals( sParamHidd2 ) ) {
                            iIdNota = Integer.parseInt( ui_hidden.getValue().toString() );
                        }
                    }

                    clNota = new ClNota();
                    clSeccion = new ClSeccion( this.getP_sec_id() );
                    if ( iIdNota != 0 ) {
                        clNota.setNotId( iIdNota );
                    }
                    clNota.setNotActivo( "1" );
                    clNota.setNotNota( Float.parseFloat( sValorNota ) );
                    clNota.setClAlumno( lrow.get( i ).getClAlumno() );
                    clNota.setClSisEvaPersonalizado( lcol.get( j ) );
                    clNota.setNotCreacion( new Date() );
                    clNota.setClSeccion( clSeccion );

                    notas.add( clNota );
                }
            }
        }
        if ( band ) {
            this.setOncomplete( "javascript:alert('Error! notas mayores a 20');" );
        } else {
            notasClDAO = (HSNotasCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLNotas" );
            notasClDAO.insertarActualizarNotas( notas );
            this.setOncomplete( "javascript:alert('Actualizacion satisfactoria');"
                    + "Richfaces.hideModalPanel('mpNotasCL');" );
        }
    }

    public void imprimir( ActionEvent event ) {
        UIParameter ui_sec = (UIParameter) event.getComponent().findComponent( "p_sec_id" );

        this.setP_sec_id( Integer.parseInt( String.valueOf( ui_sec.getValue() ) ) );
        this.setP_rep_value( "notasCL" );

    }

    public void cargarReporte( OutputStream out, Object data )
            throws IOException, EOFException, SQLException, JRException {
        byte[] aBytes;
        byte[] abPdf;
        int iSize;
        ByteArrayOutputStream buffer;
        InputStream input;
        JasperPrint jasperPrint;

        if ( "notasCL".equals( data ) ) {

            jasperPrint = ReporteRegistroNota.generateReportNotasAula( Integer.valueOf( this.getP_sec_id() ) );
            buffer = new ByteArrayOutputStream();

            JasperExportManager.exportReportToPdfStream( jasperPrint, buffer );
            aBytes = buffer.toByteArray();

            input = new ByteArrayInputStream( aBytes );

            iSize = input.available();
            abPdf = new byte[ iSize ];
            input.read( abPdf );
            out.write( abPdf );

            buffer.flush();
            buffer.close();
            input.close();
            out.flush();
            out.close();
        }
        this.setP_rep_value( "" );
    }
}
