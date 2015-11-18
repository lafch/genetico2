/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import net.uch.cursoLibre.hibernateSpringDao.HSAlumnoCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSAlumnoTarifaCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAcademicoClDao;
import net.uch.cursoLibre.hibernateSpringDao.HSEstructuraPagoDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSHorarioDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSMatriculaCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSPlantillaHorarioCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSSeccionCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAperturadoClDAO;
import net.uch.cursoLibre.managedBeans.beans.BeanClAlumno;
import net.uch.cursoLibre.managedBeans.beans.BeanClHoraria;
import net.uch.cursoLibre.managedBeans.beans.BeanClSeccion;
import net.uch.cursoLibre.managedBeans.beans.BeanClSisEvaDetalle;
import net.uch.cursoLibre.utilsAcademico.Metodos;
import net.uch.mapping.*;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSLocalDAO;
import net.uch.tablaSistema.managedBeans.bUsuario;
import net.uch.util.CommonDAO;
import net.uch.util.CommonWeb;
import net.uch.util.Reporte;
import org.richfaces.component.UITree;
import org.richfaces.component.html.HtmlTree;
import org.richfaces.event.NodeSelectedEvent;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;

/**
 *
 * @author cesardl
 */
public class bSeccionCL {

    //General
    private boolean blEsAreaModular;
    private boolean blEsInformes;
    private String IdCentroPre;
    private int IdAreaPre;
    private int IdModuloPre;
    private int IdCursoPre;
    private int IdSeccionPre;
    private SelectItem[] cboCentrosPre;
    private SelectItem[] cboAreasPre;
    private SelectItem[] cboModulosPre;
    private SelectItem[] cboCursosPre;
    private SelectItem[] cboSeccionesPre;
    private int iAreaId;
    private int iDiaCobrar = 30;
    private int talape_id;
    private int talape_id_2;
    private int tal_dest_id = 0;
    private int sec_dest_id = 0;
    private int estpag_dest_id = 0;
    private int estpagdet_dest_id = 0;
    private int cur_id;
    private int mod_id;
    private int iTipoSeccion;//1: Continuación / 0:Inicio
    private String talape_nombre;
    private String cur_nombre;
    private String mod_nombre;
    private String oncomplete;
    private String style;
    private Date fechaIniPago = new Date();
    private String[] m_asMontosPagos;
    private SelectItem[] tal_dest_desc;
    private SelectItem[] sec_dest_desc;
    private SelectItem[] estpag_dest_desc;
    private SelectItem[] estpagdet_dest_desc;
    private SelectItem[] seTiposSeccion;
    private List<BeanClSeccion> listaSecciones;
    private List<ClSeccion> lstSeccionesPrecedentes;
    private List<ClEstructuraPagosDetalle> estpagdet_dest_desc2;
    //Detalle de horaria
    private BeanClHoraria horaria;
    private List<BeanClHoraria> nListaHoraria;
    private List<BeanClHoraria> qListaHoraria;
    //Detalles de seccion
    private int sec_id;
    private String sec_detalle;
    private BeanClSeccion seccion;
    private String mensaje_eliminar;
    private List<BeanClAlumno> listaAlumnosMatriculados;
    //Constantes
    private final int EXCEDE_CANTIDAD_ALUMNOS = 1;
    private final int PERTENECE_AL_MISMO_CURSO = 2;
    private String b_sec_id;
    private String tituloReporte = "";
    private String valorRep = "";
    private int tal_id;
    private TreeNode arbol = null;
    private List<String> selectedNodeChildren = new ArrayList<String>();
    private String nombreNodo;
    boolean ind = false;
    private float i_alutar_monto_aumento;
    private int sec_id_rep;
    private int mod_dest_id = 0;
    private String modo_id = "1";
    private SelectItem[] mod_dest_desc;
    private int sec_act_id = 0;

    private void agregarHijos( TreeNode node, int idPadre ) {
        int contNodes;
        int iSizeArb;
        ClArbolAcademico clArbol;
        HSArbolAcademicoClDao daoArbol;
        HSArbolAperturadoClDAO daoTA;
        List<ClArbolAcademico> listaArbol;
        List<ClArbolAperturado> listaTalape;
        TreeNodeImpl nodeImpl;

        daoArbol = CommonDAO.getClArbolAcademicoDAO();
        daoTA = CommonDAO.getClArbolAperturadoClDAO();

        listaArbol = daoArbol.listarArbolPorPadre( idPadre );

        contNodes = 0;
        iSizeArb = listaArbol.size();
        for ( int i = 0; i < iSizeArb; i++ ) {
            clArbol = listaArbol.get( i );
            if ( clArbol.getArbVisibleArbol().intValue() == 1 ) {

                nodeImpl = new TreeNodeImpl();

                listaTalape = daoTA.cargarArbTallerAperturado( clArbol.getArbId() );
                if ( !listaTalape.isEmpty() ) {

                    for ( int j = 0; j < listaTalape.size(); j++ ) {
                        contNodes++;

                        if ( listaTalape.get( j ).getArbapeDescripcion() != null ) {// esto se le puso xq EN LA TABLA ALGUNSO NO TIENEN DESCRIPCION
                            nodeImpl = new TreeNodeImpl();
                            nodeImpl.setData( "<div style='float: left;'>" + listaTalape.get( j ).getArbapeDescripcion() + "</div>" + "<div style='color:#FFFFFF;position: left;'>*" + listaTalape.get( j ).getArbapeId() + "</div>" );
                            node.addChild( contNodes, nodeImpl );
                            agregarHijos( nodeImpl, clArbol.getArbId() );
                        }


                    }
                } else {
                    if ( clArbol.getArbNivel() == 4 ) {
                        nodeImpl.setData( "<div style='float: left;'>" + clArbol.getArbDescripcion() + "</div>" + "<div style='color:#FFFFFF;position: left;'>*0</div>" );
                    } else {
                        nodeImpl.setData( "<div style='float: left;'>" + clArbol.getArbDescripcion() + "</div>" + "<div style='color:#FFFFFF;position: left;'>*" + listaArbol.get( i ).getArbId() + "</div>" );
                    }
                    if ( clArbol.getArbNivel().intValue() == 1 ) {
                        node.getChild( clArbol.getArbInstitucion() ).addChild( clArbol.getArbId(), nodeImpl );
                    } else {
                        node.addChild( new Integer( i + 1 ), nodeImpl );
                    }
//                node.addChild( new Integer( i + 1 ), nodeImpl );
                    agregarHijos( nodeImpl, clArbol.getArbId() );

                }
            }
        }
    }

    public void cargarArbol() {
        int iCantCat;
        String sCodCat;
        TbCatalogo cat;
        List<TbCatalogo> lstCatTipoInst;
        TreeNodeImpl arbCategoria;
        try {
            lstCatTipoInst = CommonDAO.getTbCatalogoDAO().seleccionarGrupo( "078" );
            arbol = new TreeNodeImpl();
            iCantCat = lstCatTipoInst.size();
            for ( int i = 0; i < iCantCat; i++ ) {
                cat = lstCatTipoInst.get( i );
                sCodCat = cat.getCatCodigoGrupo() + cat.getCatCodigoElemento();
                if ( !"078004".equals( sCodCat ) ) {
                    arbCategoria = new TreeNodeImpl();
                    arbol.addChild( sCodCat, arbCategoria );
                    arbol.getChild( sCodCat ).setData( cat.getCatDescripcionElemento() );
                }
            }

            agregarHijos( arbol, 0 );

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void seleccionarElemento( NodeSelectedEvent event ) {
        HtmlTree tree;
        String id;
        TreeNode currentNode;
        UITree tree2;

        Map.Entry<Object, TreeNode> entry;
        Iterator<Map.Entry<Object, TreeNode>> it;

        tree = (HtmlTree)event.getComponent();
        tree2 = (UITree)event.getComponent();
        id = tree2.getRowKey().toString();

        nombreNodo = ( (String)tree.getRowData() ) + "  -  " + id;

        this.setTalape_id( devolverId( nombreNodo ) );
        seleccionarTaller( this.getTalape_id() );

        selectedNodeChildren.clear();

        currentNode = tree.getTreeNode( tree.getRowKey() );
        if ( currentNode.isLeaf() ) {
            selectedNodeChildren.add( (String)currentNode.getData() );
        } else {
            it = currentNode.getChildren();
            while ( it != null && it.hasNext() ) {
                entry = it.next();
                selectedNodeChildren.add( entry.getValue().getData().toString() );
            }
        }
    }

    public TreeNode getTreeNode() {
        if ( arbol == null ) {
            cargarArbol();
        }
        return arbol;
    }

    public void addSecPre( ActionEvent event ) {
        ClSeccion clSec;
        if ( IdSeccionPre != 0 && !contieneSeccion( IdSeccionPre, lstSeccionesPrecedentes ) ) {
            clSec = CommonDAO.getClSeccionDAO().seleccionarSeccion( IdSeccionPre );
            lstSeccionesPrecedentes.add( clSec );
        }
    }

    public void delSecPre( ActionEvent event ) {
        int iIdSecDel;
        int iIndex;
//        BeanClSeccion clSec;
        ClSeccion clSec;

        iIdSecDel = CommonWeb.parseObjToInt( CommonWeb.getParamFromUIFacesContext( event, "id_sec_pre_del" ) );
        iIndex = -1;
        for ( int i = 0; i < lstSeccionesPrecedentes.size(); i++ ) {
            clSec = lstSeccionesPrecedentes.get( i );
            if ( clSec.getSecId().intValue() == iIdSecDel ) {
                iIndex = i;
                break;
            }
        }
        if ( iIndex >= 0 ) {
            lstSeccionesPrecedentes.remove( iIndex );
        }
    }

    private boolean contieneSeccion( int IdSeccionPre, List<ClSeccion> lstSeccionesPrecedentes ) {
        boolean blContiene;
        int iSize;
        blContiene = false;
        iSize = lstSeccionesPrecedentes.size();
        for ( int i = 0; i < iSize; i++ ) {
            if ( IdSeccionPre == lstSeccionesPrecedentes.get( i ).getSecId().intValue() ) {
                blContiene = true;
                break;
            }
        }
        return blContiene;
    }

    public boolean isEsInformes() {
        blEsInformes = CommonWeb.traerUsuarioLogeado().getRol_id() == 38;
        return blEsInformes;
    }

    public void setEsInformes( boolean blEsInformes ) {
        this.blEsInformes = blEsInformes;
    }

    public int getTipoSeccion() {
        return iTipoSeccion;
    }

    public void setTipoSeccion( int iTipoSeccion ) {
        this.iTipoSeccion = iTipoSeccion;
    }

    public int getIdAreaPre() {
        return IdAreaPre;
    }

    public void setIdAreaPre( int IdAreaPre ) {
        this.IdAreaPre = IdAreaPre;
    }

    public String getIdCentroPre() {
        return IdCentroPre;
    }

    public void setIdCentroPre( String IdCentroPre ) {
        this.IdCentroPre = IdCentroPre;
    }

    public int getIdCursoPre() {
        return IdCursoPre;
    }

    public void setIdCursoPre( int IdCursoPre ) {
        this.IdCursoPre = IdCursoPre;
    }

    public int getIdSeccionPre() {
        return IdSeccionPre;
    }

    public void setIdSeccionPre( int IdSeccionPre ) {
        this.IdSeccionPre = IdSeccionPre;
    }

    public int getIdModuloPre() {
        return IdModuloPre;
    }

    public void setIdModuloPre( int IdModuloPre ) {
        this.IdModuloPre = IdModuloPre;
    }

    public SelectItem[] getCboAreasPre() {
        if ( IdCentroPre != null && !"0".equals( IdCentroPre ) ) {
            cboAreasPre = CommonWeb.getCboArbolAreaInf( 0, IdCentroPre );
        } else {
            cboAreasPre = new SelectItem[ 1 ];
            cboAreasPre[0] = new SelectItem( "0", "[Seleccione]" );
            IdAreaPre = 0;
        }
        return cboAreasPre;
    }

    public void setCboAreasPre( SelectItem[] cboAreasPre ) {
        this.cboAreasPre = cboAreasPre;
    }

    public SelectItem[] getCboCentrosPre() {
        Metodos met = new Metodos();
        if ( cboCentrosPre == null ) {
            cboCentrosPre = met.cboCentros();
        }
        return cboCentrosPre;
    }

    public void setCboCentrosPre( SelectItem[] cboCentrosPre ) {
        this.cboCentrosPre = cboCentrosPre;
    }

    public SelectItem[] getCboCursosPre() {
        if ( !"0".equals( IdCentroPre ) && IdAreaPre != 0 && IdModuloPre != 0 ) {
            cboCursosPre = CommonWeb.getCboArbolModInf( IdModuloPre );
        } else {
            cboCursosPre = new SelectItem[ 1 ];
            cboCursosPre[0] = new SelectItem( "0", "[Seleccione]" );
            IdCursoPre = 0;
        }
        return cboCursosPre;
    }

    public void setCboCursosPre( SelectItem[] cboCursosPre ) {
        this.cboCursosPre = cboCursosPre;
    }

    public SelectItem[] getCboSeccionesPre() {
        Metodos met;
        if ( !"0".equals( IdCentroPre ) && IdAreaPre != 0 && IdModuloPre != 0 && IdCursoPre != 0 ) {
            met = new Metodos();
            cboSeccionesPre = met.cboSeccionesXCurso( IdCursoPre );
        } else {
            cboSeccionesPre = new SelectItem[ 1 ];
            cboSeccionesPre[0] = new SelectItem( "0", "[Seleccione]" );
            IdSeccionPre = 0;
        }
        return cboSeccionesPre;
    }

    public void setCboSeccionesPre( SelectItem[] cboSeccionesPre ) {
        this.cboSeccionesPre = cboSeccionesPre;
    }

    public SelectItem[] getCboModulosPre() {
        if ( !"0".equals( IdCentroPre ) && IdAreaPre != 0 ) {
            cboModulosPre = CommonWeb.getCboArbolModInf( IdAreaPre );
        } else {
            cboModulosPre = new SelectItem[ 1 ];
            cboModulosPre[0] = new SelectItem( "0", "[Seleccione]" );
            IdModuloPre = 0;
        }
        return cboModulosPre;
    }

    public void setCboModulosPre( SelectItem[] cboModulosPre ) {
        this.cboModulosPre = cboModulosPre;
    }

    public SelectItem[] getTiposSeccion() {
        return seTiposSeccion;
    }

    public void setTiposSeccion( SelectItem[] tiposSeccion ) {
        this.seTiposSeccion = tiposSeccion;
    }

    public String getNombreNodo() {
        return nombreNodo;
    }

    public void setNombreNodo( String nombreNodo ) {
        this.nombreNodo = nombreNodo;
    }

    public int devolverId( String texto ) {
        int id;
        int valor_;
        int valor__;
        String numeroEs;

        valor_ = texto.indexOf( "*" ) + 1;
        valor__ = texto.indexOf( "<", valor_ );
        numeroEs = texto.substring( valor_, valor__ );
        id = Integer.parseInt( numeroEs );

        return id;
    }

    public Date getFechaIniPago() {
        return fechaIniPago;
    }

    public void setFechaIniPago( Date fechaIniPago ) {
        this.fechaIniPago = fechaIniPago;
    }

    public String[] getMontosPagos() {
        return m_asMontosPagos;
    }

    public void setMontosPagos( String[] asMontosPagos ) {
        m_asMontosPagos = asMontosPagos;
    }

    public int getSec_act_id() {
        return sec_act_id;
    }

    public void setSec_act_id( int sec_act_id ) {
        this.sec_act_id = sec_act_id;
    }

    public String getModo_id() {
        return modo_id;
    }

    public void setModo_id( String modo_id ) {
        this.modo_id = modo_id;
    }

    public int getTal_id() {
        return tal_id;
    }

    public void setTal_id( int tal_id ) {
        this.tal_id = tal_id;
    }

    public String getB_sec_id() {
        return b_sec_id;
    }

    public void setB_sec_id( String b_sec_id ) {
        this.b_sec_id = b_sec_id;
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

    /**
     * Creates a new instance of bSeccionCL
     */
    public bSeccionCL() {
        this.talape_id = 0;
        this.estpagdet_dest_id = 0;
        this.seccion = new BeanClSeccion();
        this.horaria = new BeanClHoraria( 0 );
        this.listaSecciones = new ArrayList<BeanClSeccion>();
        this.nListaHoraria = new ArrayList<BeanClHoraria>();
        this.qListaHoraria = new ArrayList<BeanClHoraria>();
        cargarArbol();
    }

    public void seleccionarTaller( int arbId ) {
        String sProfesor;
        String sLocal;
        String sTurno;
        BeanClSeccion secTmp;
        ClArbolAcademico arbAcadCur;
        ClArbolAcademico arbAcadMod;
        ClArbolAcademico arbAcadTal;
        ClArbolAperturado arbApeTal;
        HSArbolAcademicoClDao daoArbol;
        HSArbolAperturadoClDAO daoTA;
        HSHorarioDAO daoClHor;
        HSLocalDAO daoLoc;
        HSPlantillaHorarioCLDAO daoPla;
        HSSeccionCLDAO daoSec;
        List<ClSeccion> lstSec;
        try {

            daoSec = CommonDAO.getClSeccionDAO();
            daoClHor = CommonDAO.getClHorarioDAO();
            daoPla = CommonDAO.getClPlantillaHorarioDAO();
            daoLoc = CommonDAO.getAcLocalDAO();
            daoTA = CommonDAO.getClArbolAperturadoClDAO();
            daoArbol = CommonDAO.getClArbolAcademicoDAO();

            //PARA OBTENER MODULO-CURSO-TALLER-TALLER APERTURADO DE LA SECCION
            arbApeTal = daoTA.seleccionarArbTallerAperturado( arbId );
            if ( arbApeTal != null ) {
                this.setTalape_id( arbApeTal.getArbapeId() );//TALLER APERTURADO
                //TALLER
                arbAcadTal = daoArbol.buscarArbolPorId( arbApeTal.getClArbolAcademico().getArbId() );
                this.setTal_id( arbAcadTal.getArbId() );//TALLER
                this.setTalape_nombre( arbAcadTal.getArbDescripcion() );
                //CURSO
                arbAcadCur = daoArbol.buscarArbolPorId( arbAcadTal.getArbIdPadre() );
                this.setCur_id( arbAcadCur.getArbId() );
                this.setCur_nombre( arbAcadCur.getArbDescripcion() );
                //MODULO
                arbAcadMod = daoArbol.buscarArbolPorId( arbAcadCur.getArbIdPadre() );
                this.setMod_id( arbAcadMod.getArbId() );
                this.setMod_nombre( arbAcadMod.getArbDescripcion() );

                //ÁREA
                blEsAreaModular = "1".equals( arbAcadMod.getArbAcadPadre().getArbAreaModular() );
                iAreaId = arbAcadMod.getArbIdPadre();
            } else {
                this.setTalape_id( 0 );//TALLER APERTURADO
                //TALLER
                this.setTal_id( 0 );//TALLER
                this.setTalape_nombre( "" );
                //CURSO
                this.setCur_id( 0 );
                this.setCur_nombre( "" );
                //MODULO
                this.setMod_id( 0 );
                this.setMod_nombre( "" );

                //ÁREA
                iAreaId = 0;
            }

            lstSec = daoSec.seleccionarSecciones( arbId );
            this.setTalape_id_2( arbId );
            this.listaSecciones = new ArrayList<BeanClSeccion>();
            for ( ClSeccion tmp_sec : lstSec ) {
                sProfesor = "No definido";
                secTmp = new BeanClSeccion( tmp_sec );
                if ( daoClHor.seleccionarHorario( tmp_sec.getSecId() ).isEmpty() ) {
                    secTmp.setV_imagen_horario( "/Imagenes/actions/horario_gris.png" );
                } else {
                    secTmp.setV_imagen_horario( "/Imagenes/actions/horario.png" );
                    sProfesor = daoClHor.seleccionarHorario( tmp_sec.getSecId() ).get( 0 ).getAcDocente().getDocNombre();
                }
                sLocal = "No definido";
                sTurno = "No definido";
                if ( tmp_sec.getLocId().intValue() > 0 ) {
                    sLocal = daoLoc.seleccionarLocal( tmp_sec.getLocId() ).getLocDes();
                }
                if ( tmp_sec.getPlaId().intValue() > 0 ) {
                    sTurno = daoPla.seleccionarPlantilla( tmp_sec.getPlaId() ).getPlaDescripcion();
                }
                secTmp.setCantMatriculados( daoClHor.matriculadosSeccion( tmp_sec.getSecId() ).size() );
                secTmp.setCantPreMatriculados( daoClHor.prematriculadosSeccion( tmp_sec.getSecId() ).size() );
                secTmp.setW_sede( sLocal );
                secTmp.setW_turno( sTurno );
                secTmp.setW_profesor( sProfesor );

                this.addListaSecciones( secTmp );

            }
        } catch ( Exception e ) {
            System.err.println( "Error al seleccionar los talleres " + e.getMessage() );
        }
    }

    public void seleccionarSeccion( ActionEvent event ) {
        int p_sec_id;
        HSSeccionCLDAO daoSec;
        System.out.println( "el valor del id es -> " + this.getTalape_id() );
        if ( this.getTalape_id() != 0 ) {
            this.setOncomplete( "" );
            try {
                p_sec_id = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_sec_id" ) ).getValue().toString() );
                daoSec = CommonDAO.getClSeccionDAO();
                this.seccion = new BeanClSeccion( daoSec.seleccionarSeccion( p_sec_id ) );
                if ( this.seccion.getSecTipo() != null && this.seccion.getSecTipo().intValue() == 2 ) {//2=Continuación
                    iTipoSeccion = this.seccion.getSecTipo();
                    lstSeccionesPrecedentes = daoSec.listarSeccionesPrecedentes( p_sec_id );
                } else {
                    iTipoSeccion = 1;
                    lstSeccionesPrecedentes = new ArrayList<ClSeccion>();
                }
            } catch ( NullPointerException npe ) {
                this.seccion = new BeanClSeccion( this.getTalape_id() );
                lstSeccionesPrecedentes = new ArrayList<ClSeccion>();
            }
            this.setOncomplete( "Richfaces.showModalPanel('mpSeccion',{top:100})" );
        } else {
            this.setOncomplete( "javascript:alert('El Taller en el cual desea crear la seccion no ha sido aperturado')" );
        }
    }

    public void verificarEliminacion( ActionEvent event ) {
        int p_sec_id = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_sec_id" ) ).getValue().toString() );
        String p_sec_detalle = ( (UIParameter)event.getComponent().findComponent( "p_sec_detalle" ) ).getValue().toString();

        HSSeccionCLDAO daoSec = CommonDAO.getClSeccionDAO();
        this.setOncomplete( "" );
        if ( daoSec.existenMatriculasSeccion( p_sec_id ) ) {
            this.setOncomplete( "javascript:alert('Es imposible eliminar la seccion debido a que registra alumnos.');" );
        } else {
            this.setSec_id( p_sec_id );
            this.setSec_detalle( p_sec_detalle );
            this.setOncomplete( "javascript:Richfaces.showModalPanel('mpEliminar');" );
        }
    }

    public void eliminarSeccion( ActionEvent event ) {
        CommonDAO.getClSeccionDAO().eliminarSeccion( this.getSec_id() );

        seleccionarTaller( this.getTalape_id() );

        this.setSec_id( 0 );
        this.setSec_detalle( "" );
    }

    public void insertarActualizarSeccion( ActionEvent event ) {
        String sMensaje = "";
        String sMsjError = "";
        String sTurno = "M";
        String[] sTurnos = { "M", "T", "N" };
        ClSeccion secTmp;
        HSSeccionCLDAO clSeccionDAO;
        iTipoSeccion = isEsInformes() ? 1 : iTipoSeccion;
        secTmp = this.getSeccion().getClSeccion();
        this.setOncomplete( sMensaje );
        secTmp.setClArbolAperturado( new ClArbolAperturado() );
        secTmp.setClArbolAcademico( new ClArbolAcademico() );
        secTmp.getClArbolAperturado().setArbapeId( this.getTalape_id_2() );
        secTmp.getClArbolAcademico().setArbId( this.getTal_id() );
        secTmp.getClArbolAperturado().getArbapeId();
        secTmp.setSecTipo( iTipoSeccion );
        secTmp.setSecFCreacion( new Timestamp( new Date().getTime() ) );


        sMsjError = this.seccion.esValido();
        
        if ( sMsjError.isEmpty() ) {
            if ( iTipoSeccion == 1 || ( iTipoSeccion == 2 && lstSeccionesPrecedentes != null && !lstSeccionesPrecedentes.isEmpty() ) ) {
                clSeccionDAO = CommonDAO.getClSeccionDAO();
                if ( this.seccion.getPlaId() != null && this.seccion.getPlaId().intValue() > 0 ) {
                    sTurno = sTurnos[ this.seccion.getPlaId() - 1];
                }

                secTmp.setSecTurno( sTurno );
                if ( this.seccion.getSecId().intValue() == 0 ) {
                    clSeccionDAO.insertarSeccion( secTmp );
//                    clSeccionDAO.extraerIdSeccionPadre();
                    //    System.out.println("ID : "+secTmp.getSecId()); Id Padre
                    // if(secTmp.getClArbolAcademico().getArbInstitucion())
                    //System.out.println( "MODULAR"+secTmp.getClArbolAcademico().getArbAreaModular());
                    /*
                     int iAreaIdAux;
                     ClArbolAcademico clArbAcad;
                     HSArbolAcademicoClDao daoClArbAcad;
                     List<ClArbolAcademico> lstArbMod;
                     if ( this.getMod_id() == 0 ) {
                     mod_dest_desc = new SelectItem[ 1 ];
                     } else {
                     daoClArbAcad = CommonDAO.getClArbolAcademicoDAO();
                     iAreaIdAux = daoClArbAcad.seleccionarArbol( this.getMod_id() ).getArbIdPadre();

                     lstArbMod = daoClArbAcad.listarArbolPorPadre( iAreaIdAux );
                     mod_dest_desc = new SelectItem[ lstArbMod.size() + 1 ];
                     for ( int i = 0; i < lstArbMod.size(); i++ ) {
                     clArbAcad = lstArbMod.get( i );
                     mod_dest_desc[i + 1] = new SelectItem( clArbAcad.getArbId(), clArbAcad.getArbDescripcion() );
                     }
                     }
                     mod_dest_desc[0] = new SelectItem( 0, "[Seleccione]" );
                     return mod_dest_desc;
                     */
                    sMensaje = "javascript:alert('Registro exitoso.');";
                } else {

                    clSeccionDAO.actualizarSeccion( secTmp );
                    if ( this.seccion.getSecGrupoId() != 0 ) {
                    }
                    sMensaje = "javascript:alert('Actualizacion exitosa.');";
                }
                if ( iTipoSeccion == 2 ) {
                    clSeccionDAO.registrarSeccionesPrecedentes( secTmp, lstSeccionesPrecedentes );
                }
                sMensaje = sMensaje.concat( "Richfaces.hideModalPanel('mpSeccion');" );
            } else {
                sMensaje = "javascript:alert('Debe colocar las secciones que preceden a esta continuación para la PREMATRICULA AUTOMÁTICA, o colocarla como Inicio');";
            }
        } else {
            sMensaje = "javascript:alert('" + sMsjError + "');";
        }
        this.setOncomplete( sMensaje );
        seleccionarTaller( this.getTalape_id() );
    }

    public void cargarHorario( ActionEvent event ) throws Exception {
        int iLocId;
        int iSecId;
        String sLocal;
        String sPabe;
        String sAula;
        String sSecDetalle;
        BeanClHoraria beanClHor;
        ClHoraria clHor;
        HSCatalogoDAO daoCat;
        HSHorarioDAO daoClHor;
        List<ClHoraria> lstHorarias;

        daoClHor = CommonDAO.getClHorarioDAO();
        daoCat = CommonDAO.getTbCatalogoDAO();

        this.setOncomplete( "" );
        iSecId = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_sec_id" ) ).getValue().toString() );
        sSecDetalle = ( (UIParameter)event.getComponent().findComponent( "p_sec_detalle" ) ).getValue().toString();
        iLocId = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_loc_id" ) ).getValue().toString() );
        if ( iLocId > 0 ) {
            this.setSec_id( iSecId );
            this.setSec_detalle( sSecDetalle );
            this.horaria = new BeanClHoraria( iSecId );
            this.horaria.setW_sed_id( iLocId );
            this.horaria.setClSisEvaDetDocente( new ClSisEvaDetalle() );
            this.nListaHoraria = new ArrayList<BeanClHoraria>();
            this.qListaHoraria = new ArrayList<BeanClHoraria>();
            lstHorarias = daoClHor.seleccionarHorario( iSecId );
            if ( !lstHorarias.isEmpty() ) {
                for ( int i = 0; i < lstHorarias.size(); i++ ) {
                    clHor = lstHorarias.get( i );
                    sLocal = clHor.getAcAula().getPab().getLoc().getLocDes();
                    sPabe = clHor.getAcAula().getPab().getPabDescripcion();
                    sAula = clHor.getAcAula().getAulDes();

                    clHor.getAcAula().setAulDes( sLocal.substring( 0, 3 ) + "-" + sPabe.substring( 0, 3 ) + "-" + sAula );

                    beanClHor = new BeanClHoraria( clHor );
                    beanClHor.setPosicion( i );
                    beanClHor.setV_hor_dia( daoCat.seleccionarDescripcion( clHor.getHorDia() ) );
                    beanClHor.setV_hor_tipo_clase( daoCat.seleccionarDescripcion( clHor.getHorTipoClase() ) );
                    beanClHor.setView_bool( true );
                    beanClHor.setEditable_bool( false );
                    beanClHor.setClSisEvaDetDocente( clHor.getClSisEvaDetDocente() == null ? new ClSisEvaDetalle() : clHor.getClSisEvaDetDocente() );

                    this.addnListaHoraria( beanClHor );
                }
            }
            this.setOncomplete( "javascript:Richfaces.showModalPanel('mpHoraria')" );
        } else {
            this.setOncomplete( "javascript:alert('Debe Asignar un local')" );
        }
    }

    public void agregarHorario( ActionEvent event ) {
        int iLocId;
        String sMsg;
        BeanClHoraria bClHorAux;

        this.setOncomplete( "" );
        sMsg = this.horaria.esValido();
        if ( sMsg.isEmpty() ) {
            for ( String sDiaCod : this.horaria.getLstDiasCod() ) {
                bClHorAux = new BeanClHoraria( this.sec_id );
                bClHorAux.setAcDocente( new AcDocente( horaria.getAcDocente().getId() ) );
                bClHorAux.setAcAula( new AcAula( horaria.getAcAula().getId() ) );
                bClHorAux.setW_pab_id( horaria.getW_pab_id() );
                bClHorAux.setHorTipoClase( horaria.getHorTipoClase() );
                bClHorAux.setInicio_h( horaria.getInicio_h() );
                bClHorAux.setInicio_m( horaria.getInicio_m() );
                bClHorAux.setMeridiano_inicio( horaria.getMeridiano_inicio() );
                bClHorAux.setFin_h( horaria.getFin_h() );
                bClHorAux.setFin_m( horaria.getFin_m() );
                bClHorAux.setMeridiano_fin( horaria.getMeridiano_fin() );

                bClHorAux.actualizarDatos( sec_id );
                bClHorAux.setPosicion( this.nListaHoraria.size() );
                bClHorAux.setV_hor_dia( CommonDAO.getTbCatalogoDAO().seleccionarDescripcion( sDiaCod ) );
                bClHorAux.setHorDia( sDiaCod );
                bClHorAux.setClSisEvaDetDocente( horaria.getClSisEvaDetDocente() );
                this.addnListaHoraria( bClHorAux );
            }
            iLocId = horaria.getW_sed_id();

            this.horaria = new BeanClHoraria( this.getSec_id() );
            this.horaria.setW_sed_id( iLocId );
            this.horaria.setClSisEvaDetDocente( new ClSisEvaDetalle() );
        } else {
            this.setOncomplete( "javascript:alert('" + sMsg + "')" );
        }
    }

    public void removerHorario( ActionEvent event ) {
        int iSizeHor;
        int iHorPosIndex;
        BeanClHoraria bClHorAux;

        iHorPosIndex = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_hor_pos" ) ).getValue().toString() );
        iSizeHor = nListaHoraria.size();
        for ( int i = iHorPosIndex + 1; i < iSizeHor; i++ ) {
            bClHorAux = nListaHoraria.get( i );
            bClHorAux.setPosicion( bClHorAux.getPosicion() - 1 );
        }
        this.addqListaHoraria( this.nListaHoraria.remove( iHorPosIndex ) );
    }

    public boolean edicionActivada() {
        for ( BeanClHoraria bean : nListaHoraria ) {
            if ( bean.isEdit_active() ) {
                return true;
            }
        }
        return false;
    }

    public void guardarHorarios( ActionEvent event ) {
        HSHorarioDAO daoClHor;
        List<Integer> lstIdsHorariosElim;
        List<ClHoraria> lstHorarios;

        this.setOncomplete( "" );
        if ( this.nListaHoraria.isEmpty() ) {
            this.setOncomplete( "javascript:alert('Debe ingresar los horarios para Guardar.')" );
        } else if ( edicionActivada() ) {
            this.setOncomplete( "javascript:alert('Termine de editar los horarios.')" );
        } else {
            daoClHor = CommonDAO.getClHorarioDAO();

            lstHorarios = new ArrayList<ClHoraria>();
            for ( BeanClHoraria tmp_horaria : nListaHoraria ) {
                lstHorarios.add( tmp_horaria.getClHoraria() );
            }
            daoClHor.insertarActualizarHorarios( lstHorarios );
            if ( !qListaHoraria.isEmpty() ) {
                lstIdsHorariosElim = new ArrayList<Integer>();
                for ( BeanClHoraria tmp_horaria : qListaHoraria ) {
                    lstIdsHorariosElim.add( tmp_horaria.getHorId() );
                }
                daoClHor.eliminarHorarios( lstIdsHorariosElim );
            }
            this.setOncomplete( "javascript:alert('Generacion de horarios satisfactoria.');"
                    + "Richfaces.hideModalPanel('mpHoraria');" );
            seleccionarTaller( this.getTalape_id() );
        }
    }

    public void cargarMatriculados( ActionEvent event ) throws Exception {
        int iSecId;
        int iTalId;
        int iMatId;
        String sFecMat = "";
        String sSecDetalle;
        BeanClAlumno beanClAlu;
        AdComprobantePago adCompPag;
        ClAlumno aluTmp;
        ClMatricula clMat;
        HSAlumnoCLDAO daoClAlum;
        HSHorarioDAO daoClHor;
        Iterator<ClMatricula> it;
        List<ClAlumno> lstAlumnos;
        List<ClAlumnoTarifa> lstAluTarIds;

        iSecId = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_sec_id" ) ).getValue().toString() );
        sec_id_rep = iSecId;
        this.setSec_act_id( iSecId );
        this.mod_dest_id = 0;
        sSecDetalle = ( (UIParameter)event.getComponent().findComponent( "p_sec_detalle" ) ).getValue().toString();
        iTalId = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_tal_id" ) ).getValue().toString() );
        this.setTal_id( iTalId );

        this.setSec_detalle( sSecDetalle );
        this.listaAlumnosMatriculados = new ArrayList<BeanClAlumno>();

        daoClHor = CommonDAO.getClHorarioDAO();
        lstAlumnos = daoClHor.matriculadosSeccion( iSecId );

        daoClAlum = CommonDAO.getClAlumnoDAO();

        if ( lstAlumnos.isEmpty() ) {
            this.setStyle( "background-color: gray;" );
        } else {
            this.setStyle( "" );
            this.sec_id = iSecId;
            this.sec_dest_id = 0;
            this.estpag_dest_id = 0;
            this.estpagdet_dest_id = 0;
            for ( int i = 0; i < lstAlumnos.size(); i++ ) {
                aluTmp = lstAlumnos.get( i );
                List<ClMatricula> lstMat = new ArrayList<ClMatricula>( aluTmp.getClMatriculas() );
                iMatId = lstMat.get( 0 ).getMatId();
                adCompPag = CommonDAO.getClAlumnoDAO().consultFechaMatricula( iMatId );
                if ( adCompPag != null ) {
                    SimpleDateFormat bFormatoFecha = new SimpleDateFormat( "yyyy-MM-dd" );
                    try {
                        sFecMat = bFormatoFecha.format( adCompPag.getCompagFechaCancelacion() );
                    } catch ( Exception ex ) {
                        sFecMat = "-";
                        ex.printStackTrace();
                    }
                }
                /*
                 it = aluTmp.getClMatriculas().iterator();
                 sFecMat = "-";
                 if ( it.hasNext() ) {
                 SimpleDateFormat bFormatoFecha = new SimpleDateFormat( "yyyy-MM-dd" );
                 clMat = it.next();
                 try {
                 sFecMat = bFormatoFecha.format( clMat.getMatFecha() );
                 } catch ( Exception ex ) {
                 sFecMat = "-";
                 ex.printStackTrace();
                 }
                 }*/

                lstAluTarIds = (ArrayList<ClAlumnoTarifa>)daoClAlum.consultDeudaALumno( aluTmp.getAluId() );

                beanClAlu = new BeanClAlumno( aluTmp );
                beanClAlu.setAlu_style( "font-size: 10px;" );
                beanClAlu.setAlu_obs( "" );
                beanClAlu.setFechaMatricula( sFecMat );

                //VERIFICAR SI JALÓ CURSO ANTERIOR, PRE REQUISITO (EJ. INGLES).
                if ( CommonDAO.getClNotasDAO().notaxAlumno( aluTmp.getAluId(), iSecId ) == null ) {//NO TIENE NOTA
                    beanClAlu.setAlu_obs( "Sin Nota" );
                    beanClAlu.setAluMatriculado( false );
                } else {// TIENE NOTA DESAPROBATORIA
                    beanClAlu.setAlu_obs( ( CommonDAO.getClNotasDAO().notaxAlumno( aluTmp.getAluId(), iSecId ) ).getNotNota().toString() );
                }
                if ( lstAluTarIds.size() > 0 ) {//PARA VER SI TIENE DEUDAS
                    for ( int j = 0; j < lstAluTarIds.size(); j++ ) {
                        if ( lstAluTarIds.get( j ).getAlutarFechaProrroga().before( new Date() ) ) {
                            beanClAlu.setAlu_style( "font-size: 10px; font-weight: bold; color: red;" );
                            beanClAlu.setAluMatriculado( false );
                        }
                    }
                }
                beanClAlu.setAluContador( i + 1 );

                this.addListaAlumnosMatriculados( beanClAlu );
            }
        }
        estpagdet_dest_desc2 = new ArrayList<ClEstructuraPagosDetalle>();
    }

    public void guardarMatriculados( ActionEvent event ) throws Exception {
        int iFlag;
        int iFlag2;
        int iCont;
        Calendar fechaPago = Calendar.getInstance();
        ClAlumno clAlu;
        ClAlumnoTarifa clAluTar;
        ClEstructuraPagosDetalle clEstPagDet;
        ClMatricula clMat;
        ClMatriculaTaller clMatTal;
        ClSeccion clSeccTmp;
        HSAlumnoTarifaCLDAO daoClAluTar;
        HSEstructuraPagoDAO daoClEstPag;
        HSSeccionCLDAO daoClSec;
        HSMatriculaCLDAO daoM;
        List<ClEstructuraPagosDetalle> lstEstPagDet;
        List<ClAlumnoTarifa> lstClAluTar;
        List<ClMatricula> lstClMatriculas;
        Set<ClMatriculaTaller> setMatTals;

        iFlag = 0;
        this.setOncomplete( "" );
        daoClSec = CommonDAO.getClSeccionDAO();
        daoClAluTar = CommonDAO.getClAlumnoTarifaDAO();
        clSeccTmp = daoClSec.seleccionarSeccion( this.getSec_dest_id() );
        if ( listaAlumnosMatriculados.size() > 0 ) {
            for ( int i = 0; i < listaAlumnosMatriculados.size(); i++ ) {
                if ( listaAlumnosMatriculados.get( i ).isAluMatriculado() ) {
                    iFlag++;
                    break;
                }
            }
        }
//        iFlag2 = 0;
//        if ( blEsAreaModular ) {
//            for ( ClEstructuraPagosDetalle epd : estpagdet_dest_desc2 ) {
//                if ( epd.isEst_verificar() ) {
//                    iFlag2++;
//                    break;
//                }
//            }
//        }

        if ( this.getSec_dest_id() == 0 ) {
            this.setOncomplete( "javascript:alert('Seleccione una seccion.');" );
        } else if ( iFlag == 0 ) {
            this.setOncomplete( "javascript:alert('Seleccione minimo un alumno');" );
        } else if ( validaciones( EXCEDE_CANTIDAD_ALUMNOS, clSeccTmp ) ) {
            this.setOncomplete( "javascript:alert('Muchos alumnos para matricular.');" );
//        } else if ( this.getModo_id().equals( "1" ) && ( ( this.getEstpagdet_dest_id() == 0 && !blEsAreaModular ) || ( iFlag2 == 0 && blEsAreaModular ) ) ) {
//            this.setOncomplete( "javascript:alert('Seleccione un Monto de pago.');" );
        } else if (this.getModo_id().equals( "1" ) && this.getEstpagdet_dest_id() == 0) {
            this.setOncomplete( "javascript:alert('Seleccione un Monto de pago.');" );
        } else {

            lstClMatriculas = new ArrayList<ClMatricula>();
            daoM = CommonDAO.getClMatriculaDAO();
            for ( BeanClAlumno bean_alumno : listaAlumnosMatriculados ) {
                if ( bean_alumno.isAluMatriculado() && !daoM.estaMatriculado( bean_alumno.getAluId(), clSeccTmp.getSecId() ) ) {
                    if ( this.getModo_id().equals( "1" ) ) {
                        clAlu = new ClAlumno( bean_alumno.getAluId() );

                        clMat = new ClMatricula( clAlu );
                        clMat.setMatActivo( "1" );
                        clMat.setMatCodigo( "MAT-BL" );
                        clMat.setMatFecha( new Timestamp( new Date().getTime() ) );
                        clMat.setMatTipo( "022005" );
                        clMat.setUsuId( capturarUsuario() );

                        setMatTals = new LinkedHashSet<ClMatriculaTaller>( 1 );
                        clMatTal = new ClMatriculaTaller();
                        clMatTal.setMattalActivo( "1" );
                        clMatTal.setMattalEstado( "1" );
                        clMatTal.setMattalObs( "Alumno matriculado por bloque" );
                        clMatTal.setClMatricula( clMat );
                        clMatTal.setClSeccion( clSeccTmp );
                        setMatTals.add( clMatTal );

                        clMat.setClMatriculaTallers( setMatTals );

                        lstClMatriculas.add( clMat );
                    }
                    if ( this.getModo_id().equals( "2" ) ) {
                        System.out.println( "CAMBIO DE SECCION" );
                        System.out.println( "sec_act_2: " + this.sec_act_id );
                        int matalu = daoM.BuscarMatricula( this.sec_act_id, bean_alumno.getAluId() ).getMatId();
                        System.out.println( "mat: " + matalu );

                        daoM.cambiarSeccionMatriculaTaller( matalu, this.getSec_dest_id() );
                        daoClAluTar.cambiarSeccionAlumnoTarifa( matalu, this.getSec_dest_id() );
                    }

                }
            }

            if ( this.getModo_id().equals( "1" ) ) {
                daoM.insertarMatriculas( lstClMatriculas );

                System.out.println( "Cantidad de matriculas: " + lstClMatriculas.size() );

                daoClEstPag = CommonDAO.getClEstructuraPagoDAO();
                //clEstPagDet = daoClEstPag.buscarEstructuraPagosDet( this.getEstpagdet_dest_id() );
                lstClAluTar = new ArrayList<ClAlumnoTarifa>();

                lstEstPagDet = new ArrayList<ClEstructuraPagosDetalle>();
//                if ( blEsAreaModular ) {
//                    for ( ClEstructuraPagosDetalle epd : estpagdet_dest_desc2 ) {
//                        if ( epd.isEst_verificar() ) {
//                            lstEstPagDet.add( epd );
//                        }
//                    }
//                } else {
                    clEstPagDet = daoClEstPag.buscarEstructuraPagosDet( this.getEstpagdet_dest_id() );
                    lstEstPagDet.add( clEstPagDet );
                    fechaIniPago = clSeccTmp.getSecFinicio();
//                }

                for ( ClMatricula matTmp : lstClMatriculas ) {
                    iCont = 0;
                    for ( ClEstructuraPagosDetalle epd : lstEstPagDet ) {
                        clAluTar = new ClAlumnoTarifa();

                        clAluTar.setAlutarActivo( "1" );
                        clAluTar.setAlutarAluTipo( "014003" );
                        clAluTar.setAlutarEstado( "0" );
//                        clAluTar.setAlutarFechaPago( clSeccTmp.getSecFinicio() );
                        clAluTar.setAlutarFechaPago( new Date() );
                        fechaPago.setTime( fechaIniPago );
                        fechaPago.add( Calendar.DAY_OF_MONTH, ( iDiaCobrar * ( iCont ) ) );
//                        clAluTar.setAlutarFechaProrroga( clSeccTmp.getSecFinicio() );
                        clAluTar.setAlutarFechaProrroga( fechaPago.getTime() );
                        clAluTar.setAlutarMonto( epd.getEstpagdetMonto() );
                        clAluTar.setAlutarMora( 0f );
                        clAluTar.setClAlumno( matTmp.getClAlumno() );
                        clAluTar.setClEstructuraPagosDetalle( epd );
                        clAluTar.setConpagId( epd.getAdConceptoPago().getId() );
                        clAluTar.setMatId( matTmp.getMatId() );
                        clAluTar.setSecId( clSeccTmp.getSecId() );

                        lstClAluTar.add( clAluTar );
                        iCont++;
                    }

                }

                daoClAluTar.generarAlumnosTarifa( lstClAluTar );
                this.setOncomplete( "javascript:alert('Alumnos matriculados con exito en el taller " + clSeccTmp.getClArbolAcademico().getArbDescripcion() + ".');"
                        + "Richfaces.hideModalPanel('mpMatriculados');" );
            } else {
                this.setOncomplete( "javascript:alert('Cambio de sección correcto: " + clSeccTmp.getClArbolAcademico().getArbDescripcion() + ".');"
                        + "Richfaces.hideModalPanel('mpMatriculados');" );
            }
            seleccionarTaller( this.getTalape_id() );
        }
    }

    private boolean validaciones( int caso, ClSeccion sec ) {
        int iCantAlumMatr;
        int iTotalAlum;
        HSHorarioDAO daoH;
        switch ( caso ) {
            case EXCEDE_CANTIDAD_ALUMNOS:
                daoH = CommonDAO.getClHorarioDAO();

                iCantAlumMatr = daoH.matriculadosSeccion( this.getSec_dest_id() ).size();
                iTotalAlum = getTotalParaMatricular() + iCantAlumMatr;
                return sec.getSecVacMax().intValue() < iTotalAlum;
            default:
                return false;
        }
    }

    public SelectItem[] getMod_dest_desc() {
        int iAreaIdAux;
        ClArbolAcademico clArbAcad;
        HSArbolAcademicoClDao daoClArbAcad;
        List<ClArbolAcademico> lstArbMod;
        if ( this.getMod_id() == 0 ) {
            mod_dest_desc = new SelectItem[ 1 ];
        } else {
            daoClArbAcad = CommonDAO.getClArbolAcademicoDAO();
            iAreaIdAux = daoClArbAcad.seleccionarArbol( this.getMod_id() ).getArbIdPadre();

            lstArbMod = daoClArbAcad.listarArbolPorPadre( iAreaIdAux );
            mod_dest_desc = new SelectItem[ lstArbMod.size() + 1 ];
            for ( int i = 0; i < lstArbMod.size(); i++ ) {
                clArbAcad = lstArbMod.get( i );
                mod_dest_desc[i + 1] = new SelectItem( clArbAcad.getArbId(), clArbAcad.getArbDescripcion() );
            }
        }
        mod_dest_desc[0] = new SelectItem( 0, "[Seleccione]" );
        return mod_dest_desc;
    }

    public void setMod_dest_desc( SelectItem[] mod_dest_desc ) {
        this.mod_dest_desc = mod_dest_desc;
    }

    public int getMod_dest_id() {
        return mod_dest_id;
    }

    public void setMod_dest_id( int mod_dest_id ) {
        this.mod_dest_id = mod_dest_id;
    }

    private int capturarUsuario() {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().
                getExternalContext().getSession( false );
        int id_usu = ( (bUsuario)session.getAttribute( "usuario" ) ).getId_usu();
        return id_usu;
    }

    public int getAreaId() {
        return iAreaId;
    }

    public int getDiaCobrar() {
        return iDiaCobrar;
    }

    public void setDiaCobrar( int iDiaCobrar ) {
        this.iDiaCobrar = iDiaCobrar;
    }

    public void setAreaId( int iAreaId ) {
        this.iAreaId = iAreaId;
    }

    public boolean isEsAreaModular() {
        return blEsAreaModular;
    }

    public void setEsAreaModular( boolean blEsAreaModular ) {
        this.blEsAreaModular = blEsAreaModular;
    }

    public int getTalape_id_2() {
        return talape_id_2;
    }

    public void setTalape_id_2( int talape_id_2 ) {
        this.talape_id_2 = talape_id_2;
    }

    public int getMod_id() {
        return mod_id;
    }

    public void setMod_id( int mod_id ) {
        this.mod_id = mod_id;
    }

    public int getCur_id() {
        return cur_id;
    }

    public void setCur_id( int cur_id ) {
        this.cur_id = cur_id;
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

    public String getTalape_nombre() {
        return talape_nombre;
    }

    public void setTalape_nombre( String talape_nombre ) {
        this.talape_nombre = talape_nombre;
    }

    public int getTalape_id() {
        return talape_id;
    }

    public void setTalape_id( int talape_id ) {
        this.talape_id = talape_id;
    }

    public void addListaSecciones( BeanClSeccion tmp ) {
        this.listaSecciones.add( tmp );
    }

    public List<BeanClSeccion> getListaSecciones() {
        return listaSecciones;
    }

    public void setListaSecciones( List<BeanClSeccion> listaSecciones ) {
        this.listaSecciones = listaSecciones;
    }

    public List<ClSeccion> getLstSeccionesPrecedentes() {
        if ( lstSeccionesPrecedentes == null ) {
            lstSeccionesPrecedentes = new ArrayList<ClSeccion>();
        }
        return lstSeccionesPrecedentes;
    }

    public void setLstSeccionesPrecedentes( List<ClSeccion> lstSeccionesPrecedentes ) {
        this.lstSeccionesPrecedentes = lstSeccionesPrecedentes;
    }

    public void addnListaHoraria( BeanClHoraria tmp ) {
        this.nListaHoraria.add( tmp );
    }

    public List<BeanClHoraria> getnListaHoraria() {
        return nListaHoraria;
    }

    public void setnListaHoraria( List<BeanClHoraria> nListaHoraria ) {
        this.nListaHoraria = nListaHoraria;
    }

    public void addqListaHoraria( BeanClHoraria tmp ) {
        this.qListaHoraria.add( tmp );
    }

    public List<BeanClHoraria> getqListaHoraria() {
        return qListaHoraria;
    }

    public void setqListaHoraria( List<BeanClHoraria> qListaHoraria ) {
        this.qListaHoraria = qListaHoraria;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete( String oncomplete ) {
        this.oncomplete = oncomplete;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle( String style ) {
        this.style = style;
    }

    public SelectItem[] getTal_dest_desc() throws Exception {
        ClArbolAcademico clArbAcadTal;
        HSArbolAperturadoClDAO daoClArbApe;
        List<ClArbolAcademico> lstArbAcadTal;

        if ( this.getMod_dest_id() == 0 ) {
            tal_dest_desc = new SelectItem[ 1 ];
        } else {
            daoClArbApe = CommonDAO.getClArbolAperturadoClDAO();
            lstArbAcadTal = daoClArbApe.findArbTallerXModuloNuevo( this.getMod_dest_id() );

            tal_dest_desc = new SelectItem[ lstArbAcadTal.size() + 1 ];
            for ( int i = 0; i < lstArbAcadTal.size(); i++ ) {
                clArbAcadTal = lstArbAcadTal.get( i );
                tal_dest_desc[i + 1] = new SelectItem( clArbAcadTal.getArbId(), clArbAcadTal.getArbDescripcion() );
            }
        }
        tal_dest_desc[0] = new SelectItem( 0, "[Seleccione]" );
        return tal_dest_desc;
    }

    public void setTal_dest_desc( SelectItem[] tal_dest_desc ) {
        this.tal_dest_desc = tal_dest_desc;
    }

    public int getTal_dest_id() {
        return tal_dest_id;
    }

    public void setTal_dest_id( int tal_dest_id ) {
        this.tal_dest_id = tal_dest_id;
    }

    public SelectItem[] getSec_dest_desc() throws Exception {
        ClSeccion clSeccion;
        HSSeccionCLDAO daoClSec;
        List<ClSeccion> lstSecciones;
        if ( this.getTal_dest_id() == 0 ) {
            sec_dest_desc = new SelectItem[ 1 ];
        } else {
            daoClSec = CommonDAO.getClSeccionDAO();
            lstSecciones = daoClSec.listarSeccionXTaller( this.getTal_dest_id() );

            sec_dest_desc = new SelectItem[ lstSecciones.size() + 1 ];
            for ( int i = 0; i < lstSecciones.size(); i++ ) {
                clSeccion = lstSecciones.get( i );
                sec_dest_desc[i + 1] = new SelectItem( clSeccion.getSecId(), clSeccion.getSecCodigo() + " | " + clSeccion.getSecNombre() );
            }
        }
        this.sec_dest_id = 0;
        sec_dest_desc[0] = new SelectItem( 0, "[Seleccione]" );
        return sec_dest_desc;
    }

    public void setSec_dest_desc( SelectItem[] sec_dest_desc ) {
        this.sec_dest_desc = sec_dest_desc;
    }

    public int getSec_dest_id() {
        return sec_dest_id;
    }

    public void setSec_dest_id( int sec_dest_id ) {
        this.sec_dest_id = sec_dest_id;
    }

    public SelectItem[] getEstpag_dest_desc() {
        ClEstructuraPagos clEstPag;
        HSEstructuraPagoDAO daoEP;
        List<ClEstructuraPagos> estpags;
        if ( this.getTal_dest_id() == 0 ) {
            estpag_dest_desc = new SelectItem[ 1 ];
        } else {

            daoEP = CommonDAO.getClEstructuraPagoDAO();
            estpags = daoEP.seleccionarEstructurasXModulo( this.getTal_dest_id() );
            estpag_dest_desc = new SelectItem[ estpags.size() + 1 ];
            for ( int i = 0; i < estpags.size(); i++ ) {
                clEstPag = estpags.get( i );
                estpag_dest_desc[i + 1] = new SelectItem( clEstPag.getEstpagId(), clEstPag.getEstpagNombre() );
            }
        }
        estpag_dest_desc[0] = new SelectItem( 0, "[Seleccione]" );
        return estpag_dest_desc;
    }

    public SelectItem[] getEstpagdet_dest_desc() {
        int iIndexSel;
        ClEstructuraPagosDetalle estpagdet;
        HSEstructuraPagoDAO daoEP;
        List<ClEstructuraPagosDetalle> estpagsdet;

        //iIndexSel = !blEsAreaModular ? 1 : 0;
        iIndexSel = 1;
        if ( this.getEstpag_dest_id() == 0 ) {
            estpagdet_dest_desc = new SelectItem[ iIndexSel ];
        } else {
            daoEP = CommonDAO.getClEstructuraPagoDAO();
            estpagsdet = daoEP.listarEstructuraDetalleBloque( this.getEstpag_dest_id() );

            estpagdet_dest_desc = new SelectItem[ estpagsdet.size() + iIndexSel ];
            for ( int i = 0; i < estpagsdet.size(); i++ ) {
                estpagdet = estpagsdet.get( i );
                estpagdet_dest_desc[i + iIndexSel] = new SelectItem( estpagdet.getEstpagdetId(), estpagdet.getEstpagdetNombre() + " | " + estpagdet.getEstpagdetMonto() );
            }
        }
//        if ( !blEsAreaModular ) {
//            estpagdet_dest_desc[0] = new SelectItem( 0, "[Seleccione]" );
//        }
        estpagdet_dest_desc[0] = new SelectItem( 0, "[Seleccione]" );
        System.out.println( "[getEstpagdet_dest_desc] >> EXIT" );
        return estpagdet_dest_desc;
    }

    public List<ClEstructuraPagosDetalle> getEstpagdet_dest_desc2() {
        return estpagdet_dest_desc2;
    }

    public void setEstpagdet_dest_desc2( List<ClEstructuraPagosDetalle> estpagdet_dest_desc2 ) {
        this.estpagdet_dest_desc2 = estpagdet_dest_desc2;
    }

    public void cargarEstructuraPagoDetalle( ActionEvent event ) {
        HSEstructuraPagoDAO estPagDAO = CommonDAO.getClEstructuraPagoDAO();
        if ( this.estpag_dest_id > 0 ) {
            this.setEstpagdet_dest_desc2( estPagDAO.listarEstructuraDetalle( this.estpag_dest_id ) );
        }

    }

    public void setEstpag_dest_desc( SelectItem[] estpag_dest_desc ) {
        this.estpag_dest_desc = estpag_dest_desc;
    }

    public void setEstpagdet_dest_desc( SelectItem[] estpagdet_dest_desc ) {
        this.estpagdet_dest_desc = estpagdet_dest_desc;
    }

    public int getEstpag_dest_id() {
        return estpag_dest_id;
    }

    public int getEstpagdet_dest_id() {
        return estpagdet_dest_id;
    }

    public void setEstpag_dest_id( int estpag_dest_id ) {
        this.estpag_dest_id = estpag_dest_id;
    }

    public void setEstpagdet_dest_id( int estpagdet_dest_id ) {
        this.estpagdet_dest_id = estpagdet_dest_id;
    }

    public int getSec_id() {
        return sec_id;
    }

    public void setSec_id( int sec_id ) {
        this.sec_id = sec_id;
    }

    public float getI_alutar_monto_aumento() {
        return i_alutar_monto_aumento;
    }

    public void setI_alutar_monto_aumento( float i_alutar_monto_aumento ) {
        this.i_alutar_monto_aumento = i_alutar_monto_aumento;
    }

    public String getSec_detalle() {
        return sec_detalle;
    }

    public void setSec_detalle( String sec_detalle ) {
        this.sec_detalle = sec_detalle;
    }

    public BeanClSeccion getSeccion() {
        return seccion;
    }

    public void setSeccion( BeanClSeccion seccion ) {
        this.seccion = seccion;
    }

    public BeanClHoraria getHoraria() {
        return horaria;
    }

    public void setHoraria( BeanClHoraria horaria ) {
        this.horaria = horaria;
    }

    public String getMensaje_eliminar() {
        return mensaje_eliminar;
    }

    public void setMensaje_eliminar( String mensaje_eliminar ) {
        this.mensaje_eliminar = mensaje_eliminar;
    }

    public int getTotalMatriculados() {
        if ( this.listaAlumnosMatriculados == null ) {
            return 0;
        } else {
            return this.listaAlumnosMatriculados.size();
        }
    }

    public int getTotalParaMatricular() {
        int total = 0;
        for ( BeanClAlumno tmp : listaAlumnosMatriculados ) {
            if ( tmp.isAluMatriculado() ) {
                total++;
            }
        }
        return total;
    }

    public void addListaAlumnosMatriculados( BeanClAlumno tmp ) {
        this.listaAlumnosMatriculados.add( tmp );
    }

    public List<BeanClAlumno> getListaAlumnosMatriculados() {
        return listaAlumnosMatriculados;
    }

    public void setListaAlumnosMatriculados( List<BeanClAlumno> listaAlumnosMatriculados ) {
        this.listaAlumnosMatriculados = listaAlumnosMatriculados;
    }

    public void ImprimirFicha( ActionEvent event ) throws Exception {
        this.setTituloReporte( "Ficha de Matricula" );
        this.setValorRep( "ficha" );
        this.setB_sec_id( ( (UIParameter)event.getComponent().findComponent( "p_sec_id" ) ).getValue().toString() );
    }

    public void ImprimirFichaMat( ActionEvent event ) throws Exception {
        this.setTituloReporte( "Ficha de Matricula" );
        this.setValorRep( "ficha" );
        this.setB_sec_id( "" + sec_id_rep );
    }

    public void cargarReporte( OutputStream out, Object data ) throws IOException, Exception, EOFException {
        Reporte reporte = new Reporte();
        FacesContext context = FacesContext.getCurrentInstance();
        HashMap parametros = new HashMap();
        parametros.put( "logo", context.getExternalContext().getResource( "/Imagenes/actions/logo_p.jpg" ) );
        parametros.put( "sec_id", this.getB_sec_id() );
        reporte.cargarReporte( out, data, parametros, "rep_listado_alumnos_seccion.jasper" );
    }

    public void cargarReporteMat( OutputStream out, Object data ) throws IOException, Exception, EOFException {
        Reporte reporte = new Reporte();
        FacesContext context = FacesContext.getCurrentInstance();
        HashMap parametros = new HashMap();
        parametros.put( "logo", context.getExternalContext().getResource( "/Imagenes/actions/logo_p.jpg" ) );
        parametros.put( "sec_id", this.getB_sec_id() );
        reporte.cargarReporte( out, data, parametros, "rep_listado_alumnos_mat_seccion.jasper" );
    }

    public void DeseleccionarTodos( ActionEvent event ) throws Exception {
        if ( this.listaAlumnosMatriculados.size() > 0 ) {
            for ( int i = 0; i < this.listaAlumnosMatriculados.size(); i++ ) {
                if ( this.listaAlumnosMatriculados.get( i ).isAluMatriculado() ) {
                    this.listaAlumnosMatriculados.get( i ).setAluMatriculado( false ); //id del combobox
                }
            }
        }
    }

    public void SeleccionarTodos( ActionEvent event ) throws Exception {
        if ( this.listaAlumnosMatriculados.size() > 0 ) {
            for ( int i = 0; i < this.listaAlumnosMatriculados.size(); i++ ) {
                if ( !( this.listaAlumnosMatriculados.get( i ).isAluMatriculado() ) ) {
                    this.listaAlumnosMatriculados.get( i ).setAluMatriculado( true ); //id del combobox
                }
            }
        }
    }

    public void SeleccionMultiple( ActionEvent event ) throws Exception {
        if ( this.listaAlumnosMatriculados.size() > 0 ) {
            for ( int i = 0; i < this.listaAlumnosMatriculados.size(); i++ ) {
                this.listaAlumnosMatriculados.get( i ).setAluMatriculado( ind ); //id del combobox
            }
        }
        ind = !ind;
    }
}