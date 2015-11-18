package net.uch.cursoLibre.managedBeans;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAcademicoClDao;
import net.uch.cursoLibre.hibernateSpringDao.HSSistemaEvaluacionCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAperturadoClDAO;
import net.uch.cursoLibre.managedBeans.beans.BeanClSisEvaPersonalizado;
import net.uch.mapping.*;
import net.uch.util.CommonDAO;
import net.uch.util.ConstantesWeb;
import net.uch.util.ObjNodeCLApertura;
import org.richfaces.component.UITree;
import org.richfaces.component.html.HtmlTree;
import org.richfaces.event.NodeSelectedEvent;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;

public class bTallerAperturado {

    //CAMPOS
    private TreeNode arbol;
    private String v_modulo = "";
    private String v_curso = "";
    private List<bTallerAperturado> talleres = new ArrayList<bTallerAperturado>();
    private List<bTallerAperturado> talleres_aperturados = new ArrayList<bTallerAperturado>();
    private String i_modulo = "";
    private String i_curso = "";
    private int talape_id;
    private String talape_descripcion;
    private int siseva_id;
    private int talape_num_horas;
    private boolean i_talape_aperturado;
    private String talape_aperturado;
    private int tal_id;
    private Date talape_fecha;
    private boolean i_talape_vigente;
    private String talape_vigente;
    private String talape_actual;
    private String talape_activo;
    private String tipo;
    private String oncomplete;
    private SelectItem[] sis_evaluaciones;
    private int v_id;
    private String v_codigo;
    private String v_descripcion;
    private String v_sis_evaluacion;
    private String v_imagen_aperturar;
    private String v_imagen_vigente;
    private String v_imagen_actual;
    private int v_cur_id;
    //Subtabla de Talleres aperturados
    private boolean verDetalle;
    private String m_imagen_mostrar;
    private String m_texto_mostrar;
    //General
    private List<ObjNodeCLApertura> nodos;
    private boolean personalizado = true;
    private String efecto;
    private List<BeanClSisEvaPersonalizado> n_sisEvaPer;
    private List<BeanClSisEvaPersonalizado> q_sisEvaPer;
    private BeanClSisEvaPersonalizado sep = new BeanClSisEvaPersonalizado();
    private String elim_mensaje;
    private boolean elim_band;
    private int elim_talape_id;
    private String elim_talape_desc;
    private String nombreNodo;
    private List<ClSisEvaPersonalizado> lstClSisEvaPerPlant;

    public void setLstClSisEvaPerPlant( List<ClSisEvaPersonalizado> lstClSisEvaPerPlant ) {
        this.lstClSisEvaPerPlant = lstClSisEvaPerPlant;
    }

    public List<ClSisEvaPersonalizado> getLstClSisEvaPerPlant() {
        if ( siseva_id != 0 ) {
            lstClSisEvaPerPlant = CommonDAO.getClSistemaEvaluacionDAO().listarSisEvaPerPlantilla( siseva_id, 0 );
        } else {
            lstClSisEvaPerPlant = new ArrayList<ClSisEvaPersonalizado>();
        }
        return lstClSisEvaPerPlant;
    }

    public String getNombreNodo() {
        return nombreNodo;
    }

    public void setNombreNodo( String nombreNodo ) {
        this.nombreNodo = nombreNodo;
    }

    //CONSTRUCTORES
    public bTallerAperturado() {
    }

    public bTallerAperturado( int p ) {
    }

    public List<ObjNodeCLApertura> getNodos() {
        return nodos;
    }

    public void setNodos( List<ObjNodeCLApertura> nodos ) {
        this.nodos = nodos;
    }

    public String getM_imagen_mostrar() {
        return m_imagen_mostrar;
    }

    public void setM_imagen_mostrar( String m_imagen_mostrar ) {
        this.m_imagen_mostrar = m_imagen_mostrar;
    }

    public String getM_texto_mostrar() {
        return m_texto_mostrar;
    }

    public void setM_texto_mostrar( String m_texto_mostrar ) {
        this.m_texto_mostrar = m_texto_mostrar;
    }

    public boolean isVerDetalle() {
        return verDetalle;
    }

    public void setVerDetalle( boolean verDetalle ) {
        this.verDetalle = verDetalle;
    }

    //METODOS
    private void agregarHijos( TreeNode node, int idPadre ) {
        int iSizeArb;
        ClArbolAcademico arb;

        HSArbolAcademicoClDao daoArbol = CommonDAO.getClArbolAcademicoDAO();

        List<ClArbolAcademico> lstArbol = daoArbol.listarArbolPorPadre( idPadre );

        iSizeArb = lstArbol.size();

        for ( int i = 0; i < iSizeArb; i++ ) {
            arb = lstArbol.get( i );
            if ( arb.getArbVisibleArbol().intValue() == 1 ) {
                if ( arb.getArbNivel() <= 3 ) {
                    TreeNodeImpl nodeImpl = new TreeNodeImpl();
                    TreeNodeImpl nodeImpl2 = new TreeNodeImpl();
                    //nodeImpl.setData(listaArbol.get(i).getArbDescripcion()+"*"+ listaArbol.get(i).getArbId());
                    nodeImpl.setData( "<div style='float: left;'>" + arb.getArbDescripcion() + "</div>" + "<div style='color:#FFFFFF;position: left;'>*" + lstArbol.get( i ).getArbId() + "</div>" );
                    nodeImpl2.setData( arb.getArbId() );

                    if ( arb.getArbNivel().intValue() == 1 ) {
                        node.getChild( arb.getArbInstitucion() ).addChild( arb.getArbId(), nodeImpl );
                    } else {
                        node.addChild( new Integer( i + 1 ), nodeImpl );
                    }
                    agregarHijos( nodeImpl, arb.getArbId() );
                }
            }
        }
    }

    private void cargarArbol() {
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
    /*
     * public void cargarArbol() { try { String estructura = armarEstructura();
     * StringBuffer buffer = new StringBuffer(estructura); ByteArrayInputStream
     * estructura_arbol = new
     * ByteArrayInputStream(buffer.toString().getBytes("ISO-8859-1"));
     * Properties properties = new Properties();
     * properties.load(estructura_arbol); arbol = new TreeNodeImpl();
     * agregarNodos(null, arbol, properties); } catch (IOException ioe) {
     * ioe.printStackTrace(); } catch (Exception e) { e.printStackTrace(); } }
     *
     * public String armarEstructura() throws Exception { String estructura =
     * ""; String estructura_area = ""; String estructura_modulo = ""; String
     * estructura_curso = ""; HSAreaDAO daoA = (HSAreaDAO)
     * ServiceFinder.findBean("SpringHibernateDaoCLArea"); HSModuloDAO daoM =
     * (HSModuloDAO) ServiceFinder.findBean("SpringHibernateDaoCLModulo");
     * HSCursoCLDAO daoC = (HSCursoCLDAO)
     * ServiceFinder.findBean("SpringHibernateDaoCLCurso"); List areas =
     * daoA.seleccionarArea("");
     *
     * List<ObjNodeCLApertura> lnodo = new ArrayList<ObjNodeCLApertura>(); for
     * (int i = 0; i < areas.size(); i++) { estructura_area += (i + 1) + "=" +
     * ((ClArea) areas.get(i)).getAreDescripcion() + "\n"; List modulos =
     * daoM.seleccionarModulos(((ClArea) areas.get(i)).getAreId(), ""); for (int
     * j = 0; j < modulos.size(); j++) { estructura_modulo += (i + 1) + "." + (j
     * + 1) + "=" + ((ClModulo) modulos.get(j)).getModDescripcion() + "\n"; List
     * cursos = daoC.seleccionarCursos(((ClModulo) modulos.get(j)).getModId());
     * for (int k = 0; k < cursos.size(); k++) { estructura_curso += (i + 1) +
     * "." + (j + 1) + "." + (k + 1) + "=" + ((ClCurso)
     * cursos.get(k)).getCurNombre() + "\n";
     *
     * lnodo.add(new ObjNodeCLApertura((i + 1) + ":" + (j + 1) + ":" + (k + 1),
     * ((ClCurso) cursos.get(k)).getCurId(), ((ClCurso)
     * cursos.get(k)).getCurNombre(), ((ClModulo)
     * modulos.get(j)).getModDescripcion())); } } } this.setNodos(new
     * ArrayList<ObjNodeCLApertura>(lnodo)); estructura = estructura_area +
     * estructura_modulo + estructura_curso; return estructura; }
     */

    public void agregarNodos( String path, TreeNode node, Properties properties ) {
        boolean end = false;
        int counter = 1;
        while ( !end ) {
            String key = path != null ? path + '.' + counter : String.valueOf( counter );
            String value = properties.getProperty( key );
            if ( value != null ) {
                TreeNodeImpl nodo = new TreeNodeImpl();
                nodo.setData( value );
                node.addChild( new Integer( counter ), nodo );
                agregarNodos( key, nodo, properties );
                counter++;
            } else {
                end = true;
            }
        }
    }

    private int buscarNodo( String id ) {
        for ( int i = 0; i < nodos.size(); i++ ) {
            ObjNodeCLApertura nodo = nodos.get( i );
            if ( nodo.getId().equalsIgnoreCase( id ) ) {
                return i;
            }
        }
        return -1;
    }

    public void seleccion( NodeSelectedEvent event ) {
        try {

            HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
            HtmlTree tree2 = (HtmlTree) event.getComponent();
            UITree tree = (UITree) event.getComponent();
            String a = tree.getRowKey().toString();
            int length = a.length();
            // System.out.println("tamaños: "+a.length());

            if ( length >= 3 ) {
                //System.out.println("a: "+a);
                nombreNodo = ((String) tree2.getRowData()) + "<";
//                System.out.println( "nodo: " + nombreNodo );
//                System.out.println( "el valor es -> " + devolverId( nombreNodo ) );
                this.setV_curso( dao.buscarCurso( devolverId( nombreNodo ) ).getArbDescripcion() );
                int id_mod = dao.buscarCurso( devolverId( nombreNodo ) ).getArbIdPadre();
                this.setV_modulo( dao.buscarModulo( id_mod ).getArbDescripcion() );

                /*
                 * int position = buscarNodo(a);
                 */

                /*
                 * ObjNodeCLApertura nodo = nodos.get(position);
                 */

                v_cur_id = devolverId( nombreNodo );
                cargarTalleres( v_cur_id );
            }
        } catch ( Exception e ) {
            System.err.println( "Error al seleccionar el nodo " + e.getMessage() );
        }
    }

    public void cargarTalleres( int cur_id ) throws Exception {
        //System.out.println("CURSO_ID: "+cur_id);
        this.setTalleres( new ArrayList<bTallerAperturado>() );



        HSArbolAperturadoClDAO daoTA = CommonDAO.getClArbolAperturadoClDAO();

        HSSistemaEvaluacionCLDAO daoSE = CommonDAO.getClSistemaEvaluacionDAO();

        HSArbolAcademicoClDao daoArbol = CommonDAO.getClArbolAcademicoDAO();



        List<ClArbolAcademico> lista_taller = daoArbol.seleccionarTalleres( cur_id );
        for ( ClArbolAcademico taller : lista_taller ) {
            bTallerAperturado tal = new bTallerAperturado();
            tal.setV_id( taller.getArbId() );
            tal.setV_codigo( taller.getArbCodigo() );
            tal.setV_descripcion( taller.getArbDescripcion() );

            List<ClArbolAperturado> lista_taller_aperturado = daoTA.seleccionarArbTalleresAperturadosPorTaller( taller.getArbId() );
            // System.out.println("TAMAÑO: "+lista_taller_aperturado.size());
            for ( ClArbolAperturado talape : lista_taller_aperturado ) {
                bTallerAperturado tal_ape = new bTallerAperturado();



                tal_ape.setV_id( talape.getClArbolAcademico().getArbId() );
                tal_ape.setTalape_id( talape.getArbapeId() );
                tal_ape.setTalape_descripcion( talape.getArbapeDescripcion() );
                tal_ape.setV_sis_evaluacion( daoSE.seleccionarSistemaEvaluacion( talape.getClSisEvaluacion().getSisevaId() ).getSisevaNombre() );
                tal_ape.setTalape_num_horas( talape.getArbapeNroHoras() );

                if ( talape.getArbapeAperturado().equalsIgnoreCase( "1" ) ) {
                    tal_ape.setV_imagen_aperturar( "/Imagenes/actions/abierto.png" );
                } else {
                    tal_ape.setV_imagen_aperturar( "/Imagenes/actions/cerrado.png" );
                }

                if ( talape.getArbapeVigente().equalsIgnoreCase( "1" ) ) {
                    tal_ape.setV_imagen_vigente( "/Imagenes/actions/activar.png" );
                } else {
                    tal_ape.setV_imagen_vigente( "/Imagenes/actions/desactivar.png" );
                }

                if ( talape.getArbapeActual().equalsIgnoreCase( "1" ) ) {
                    tal_ape.setV_imagen_actual( "/Imagenes/actions/activar.png" );
                } else {
                    tal_ape.setV_imagen_actual( "/Imagenes/actions/desactivar.png" );
                }
                tal.addTalleres_aperturados( tal_ape );
            }
            tal.setVerDetalle( false );
            tal.setM_imagen_mostrar( "/Imagenes/actions/down.png" );
            tal.setM_texto_mostrar( "Mostrar Detalle" );
            this.addTalleres( tal );
        }


        /*
         * List<ClTaller> lista_taller = daoT.seleccionarTalleres(cur_id); for
         * (ClTaller taller : lista_taller) { bTallerAperturado tal = new
         * bTallerAperturado(); tal.setV_id(taller.getTalId());
         * tal.setV_codigo(taller.getTalCodigo());
         * tal.setV_descripcion(taller.getTalDescripcion());
         *
         * List<ClTallerAperturado> lista_taller_aperturado =
         * daoTA.seleccionarTalleresAperturadosPorTaller(taller.getTalId()); for
         * (ClTallerAperturado talape : lista_taller_aperturado) {
         * bTallerAperturado tal_ape = new bTallerAperturado();
         *
         * tal_ape.setV_id(talape.getClTaller().getTalId());
         * tal_ape.setTalape_id(talape.getTalapeId());
         * tal_ape.setTalape_descripcion(talape.getTalapeDescripcion());
         * tal_ape.setV_sis_evaluacion(daoSE.seleccionarSistemaEvaluacion(talape.getSisevaId()).getSisevaNombre());
         * tal_ape.setTalape_num_horas(talape.getTalapeNumHoras());
         *
         * if (talape.getTalapeAperturado().equalsIgnoreCase("1")) {
         * tal_ape.setV_imagen_aperturar("/Imagenes/actions/abierto.png"); }
         * else {
         * tal_ape.setV_imagen_aperturar("/Imagenes/actions/cerrado.png"); }
         *
         * if (talape.getTalapeVigente().equalsIgnoreCase("1")) {
         * tal_ape.setV_imagen_vigente("/Imagenes/actions/activar.png"); } else
         * { tal_ape.setV_imagen_vigente("/Imagenes/actions/desactivar.png"); }
         *
         * if (talape.getTalapeActual().equalsIgnoreCase("1")) {
         * tal_ape.setV_imagen_actual("/Imagenes/actions/activar.png"); } else {
         * tal_ape.setV_imagen_actual("/Imagenes/actions/desactivar.png"); }
         * tal.addTalleres_aperturados(tal_ape); } tal.setVerDetalle(false);
         * tal.setM_imagen_mostrar("/Imagenes/actions/down.png");
         * tal.setM_texto_mostrar("Mostrar Detalle"); this.addTalleres(tal); }
         */
    }

    public int devolverId( String texto ) {
        int id = 0;
        int valor_ = texto.indexOf( "*" ) + 1;
        int valor__ = texto.indexOf( "<", valor_ );
        String numeroEs = texto.substring( valor_, valor__ );
        id = Integer.parseInt( numeroEs );
        return id;
    }

    public void mostrarDetalle( ActionEvent event ) throws Exception {
        int id_taller_tmp = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_taller_tmp" )).getValue().toString() );
        if ( !this.getTalleres().isEmpty() ) {
            for ( bTallerAperturado bTal : this.getTalleres() ) {
                if ( id_taller_tmp == bTal.getV_id() ) {
                    if ( bTal.isVerDetalle() ) {
                        bTal.setVerDetalle( false );
                        bTal.setM_imagen_mostrar( "/Imagenes/actions/down.png" );
                        bTal.setM_texto_mostrar( "Mostrar Detalle" );
                    } else {
                        bTal.setVerDetalle( true );
                        bTal.setM_imagen_mostrar( "/Imagenes/actions/up.png" );
                        bTal.setM_texto_mostrar( "Ocultar Detalle" );
                    }
                    break;
                }
            }
        }
    }

    public void aperturarTaller( ActionEvent event ) throws Exception {
        this.setOncomplete( "" );
        this.setI_modulo( this.getV_modulo() );
        this.setI_curso( this.getV_curso() );
        n_sisEvaPer = new ArrayList<BeanClSisEvaPersonalizado>();
        try {
            HSArbolAperturadoClDAO arbApeClDAO = CommonDAO.getClArbolAperturadoClDAO();
            int id_taller_aper_tmp = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_taller_aper_tmp" )).getValue().toString() );

            ClArbolAperturado talape = arbApeClDAO.seleccionarArbTallerAperturado( id_taller_aper_tmp );
            this.setTalape_id( talape.getArbapeId() );
            this.setTalape_descripcion( talape.getArbapeDescripcion() );
            this.setTalape_num_horas( talape.getArbapeNroHoras() );
            this.setTalape_fecha( talape.getArbapeFecha() );
            if ( talape.getArbapeAperturado().equals( "1" ) ) {
                this.setI_talape_aperturado( true );
            } else {
                this.setI_talape_aperturado( false );
            }
            this.setTalape_aperturado( talape.getArbapeAperturado() );
            if ( talape.getArbapeVigente().equals( "1" ) ) {
                this.setI_talape_vigente( true );
            } else {
                this.setI_talape_vigente( false );
            }
            this.setTalape_vigente( talape.getArbapeVigente() );
            this.setTalape_actual( talape.getArbapeActual() );
            this.setTalape_activo( talape.getArbapeActivo() );
            this.setTal_id( talape.getClArbolAcademico().getArbId() );
            this.setSis_evaluaciones( getSis_evaluaciones() );
            this.setSiseva_id( talape.getClSisEvaluacion().getSisevaId() );

            HSSistemaEvaluacionCLDAO daoSiseva =
                    (HSSistemaEvaluacionCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLSistemaEvaluacion" );

            List<ClSisEvaPersonalizado> lSisEvaPer = daoSiseva.seleccionarSisEvaPer_ClTalape( talape );
            for ( int i = 0; i < lSisEvaPer.size(); i++ ) {
                ClSisEvaPersonalizado siseva_per = lSisEvaPer.get( i );
                n_sisEvaPer.add( new BeanClSisEvaPersonalizado( siseva_per, n_sisEvaPer.size() ) );
            }
            sep = new BeanClSisEvaPersonalizado( this.getSiseva_id() );
        } catch ( NullPointerException e ) {
            HSArbolAcademicoClDao daoArbol = CommonDAO.getClArbolAcademicoDAO();

            int id_taller_tmp = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_taller_tmp" )).getValue().toString() );

            ClArbolAcademico tal = daoArbol.buscarTaller( id_taller_tmp );
            this.setTalape_id( 0 );
            this.setTalape_descripcion( tal.getArbDescripcion() );
            this.setTalape_num_horas( 0 );
            this.setTalape_fecha( new Date() );
            this.setI_talape_aperturado( true );
            this.setTalape_aperturado( "1" );
            this.setI_talape_vigente( true );
            this.setTalape_vigente( "1" );
            this.setTalape_actual( "1" );
            this.setTalape_activo( "1" );
            this.setTal_id( tal.getArbId() );
            this.setSis_evaluaciones( getSis_evaluaciones() );
            this.setSiseva_id( 0 );
            sep = new BeanClSisEvaPersonalizado( this.getSiseva_id() );
        }
        this.setPersonalizado( true );
        this.setOncomplete( "showDiv();Richfaces.showModalPanel('mpApertura');" );
    }

    public boolean edicionActivada() {
        for ( BeanClSisEvaPersonalizado bean : n_sisEvaPer ) {
            if ( bean.isEdit_active() ) {
                return true;
            }
        }
        return false;
    }

    public void guardarTaller( ActionEvent event ) throws Exception {
        boolean blBand;
        boolean blFlag;
        boolean blContiene;
        ClArbolAperturado talape;
        ClSisEvaluacion clSisEva;
        List<ClSisEvaDetalle> lstSisEvaDetalle;
        List<ClSisEvaPersonalizado> lstSisEvaPersonalizado;
        Iterator<ClSeccion> it;
        HSArbolAcademicoClDao daoArbol;
        HSSistemaEvaluacionCLDAO daoSE;
        HSSistemaEvaluacionCLDAO daoSEV;
        HSArbolAperturadoClDAO arbApeClDAO;
        Set<ClSeccion> talape_seccs;

        daoArbol = CommonDAO.getClArbolAcademicoDAO();

        this.setOncomplete( "" );
        arbApeClDAO = CommonDAO.getClArbolAperturadoClDAO();
        if ( this.getTalape_num_horas() == 0 && this.getSiseva_id() == 0 ) {
            this.setOncomplete( "javascript:alert('Debe ingresar un número de horas y un sistema de evaluación para Guardar.')" );
        } else if ( isPersonalizado() && n_sisEvaPer.isEmpty() ) {
            this.setOncomplete( "javascript:alert('Debe ingresar los Sistemas de Evaluacion personalizados para Guardar.')" );
        } else if ( edicionActivada() ) {
            this.setOncomplete( "javascript:alert('Termine de editar los Sistemas de Evaluacion personalizados.')" );
        } else {
            blFlag = true;
            if ( !this.isI_talape_aperturado() && this.getTalape_id() != 0 ) {
                talape_seccs = arbApeClDAO.seleccionarArbTallerAperturado( this.getTalape_id() ).getClSeccions();
                it = talape_seccs.iterator();
                blBand = false;
                while ( it.hasNext() && !blBand ) {
                    ClSeccion sec = it.next();
                    if ( sec.getSecActivo().equalsIgnoreCase( "1" ) ) {
                        blBand = true;
                        break;
                    }
                }
                if ( blBand ) {
                    this.setOncomplete( "javascript:alert('No se puede desaperturar el Taller ya que posee secciones activas.')" );
                    blFlag = false;
                }
            }
            if ( blFlag ) {
                daoSEV = CommonDAO.getClSistemaEvaluacionDAO();
                talape = new ClArbolAperturado();
                if ( this.getTalape_id() != 0 ) {
                    talape.setArbapeId( this.getTalape_id() );
                    this.setOncomplete( "javascript:alert('Actualización Satisfactoria.');Richfaces.hideModalPanel('mpApertura')" );
                } else {
                    talape.setArbapeId( null );
                    talape.setClSeccions( new LinkedHashSet<ClSeccion>() );
                    talape.setClSisEvaPersonalizados( new LinkedHashSet() );
                    this.setOncomplete( "javascript:alert('Apertura Satisfactoria.');Richfaces.hideModalPanel('mpApertura')" );
                }
                talape.setArbapeDescripcion( this.getTalape_descripcion().trim().toUpperCase() );
                clSisEva = daoSEV.seleccionarSistemaEvaluacion( this.getSiseva_id() );
                talape.setClSisEvaluacion( clSisEva );
                talape.setArbapeNroHoras( this.getTalape_num_horas() );
                if ( this.isI_talape_aperturado() ) {
                    talape.setArbapeAperturado( "1" );
                } else {
                    talape.setArbapeAperturado( "0" );
                }
                talape.setArbapeFecha( new Date() );
                if ( this.isI_talape_vigente() ) {
                    talape.setArbapeVigente( "1" );
                } else {
                    talape.setArbapeVigente( "0" );
                }
                talape.setArbapeActual( this.getTalape_actual() );
                talape.setArbapeActivo( this.getTalape_activo() );
                //System.out.println("ARB_ID+"+this.getTal_id());
//                talape.setClArbolAcademico( daoArbol.buscarTaller( this.getTal_id() ) );
                talape.setClArbolAcademico( new ClArbolAcademico( this.getTal_id() ) );

                arbApeClDAO.insertar_ActualizarArbTallerAperturado( talape );

                daoSE = CommonDAO.getClSistemaEvaluacionDAO();

                List<ClSisEvaPersonalizado> sis_eva_pers = new ArrayList<ClSisEvaPersonalizado>();

                if ( isPersonalizado() ) {
                    lstSisEvaDetalle = new ArrayList<ClSisEvaDetalle>( clSisEva.getClSisEvaDetalles() );

                    for ( ClSisEvaDetalle clSisEvaDet : lstSisEvaDetalle ) {
                        blContiene = false;
                        for ( BeanClSisEvaPersonalizado clSisEvaPer : n_sisEvaPer ) {
                            if ( !"2".equals( clSisEvaDet.getSisevaDetalleTipo() ) && clSisEvaPer.getDetalle() == clSisEvaDet.getSisevaDetalleId().intValue() ) {
                                blContiene = true;
                                break;
                            } else if ( "2".equals( clSisEvaDet.getSisevaDetalleTipo() ) ) {
                                break;
                            }
                        }

                        //EL PROMEDIO FINAL (2) NO SE DEBE PERSONALIZAR
                        int dOrdenPromFin = 1;
                        if ( (!"2".equals( clSisEvaDet.getSisevaDetalleTipo() ) && !blContiene) || "2".equals( clSisEvaDet.getSisevaDetalleTipo() ) ) {
                            BeanClSisEvaPersonalizado tmp = new BeanClSisEvaPersonalizado();

                            if ( "2".equals( clSisEvaDet.getSisevaDetalleTipo() ) ) {
                                for ( BeanClSisEvaPersonalizado sepAux : n_sisEvaPer ) {
                                    if ( sepAux.getOrden() > dOrdenPromFin ) {
                                        dOrdenPromFin = sepAux.getOrden();
                                    }
                                }
                                dOrdenPromFin++;
                            }
                            tmp.setOrden( dOrdenPromFin );
                            tmp.setPos( n_sisEvaPer.size() );
                            tmp.setAlu_tipo( null );
                            tmp.setCodigo( clSisEvaDet.getSisevaDetalleCodigo() );
                            tmp.setNombre( clSisEvaDet.getSisevaDetalleNombre() );
                            tmp.setPeso( clSisEvaDet.getSisevaDetallePeso().intValue() );
                            tmp.setExa_semana( "1" );
                            tmp.setDetallen( "" );
                            tmp.setDetalle( clSisEvaDet.getSisevaDetalleId() );
                            tmp.setId( 0 );
                            tmp.setId_siseva( clSisEvaDet.getSisevaDetalleId() );
                            tmp.setTalape_id( talape.getArbapeId() );

                            n_sisEvaPer.add( tmp );
                        }
                    }
//                    System.out.println( "check personalizado: si" );


                    for ( int i = 0; i < n_sisEvaPer.size(); i++ ) {
                        BeanClSisEvaPersonalizado tmp = n_sisEvaPer.get( i );
                        ClSisEvaPersonalizado siseva_per = new ClSisEvaPersonalizado();

                        if ( tmp.getId() != 0 ) {
                            siseva_per.setSisevaPerId( tmp.getId() );
                        }
//                        System.out.println( talape.getArbapeId() );
                        siseva_per.setSisevaPerNombre( tmp.getNombre() );
                        siseva_per.setSisevaPerExaSemana( tmp.getExa_semana() );
                        siseva_per.setClSisEvaDetalle( new ClSisEvaDetalle( tmp.getDetalle() ) );
                        siseva_per.setClArbolAperturado( talape );
                        siseva_per.setArbId( this.getTal_id() );
                        siseva_per.setSisevaCodigo( tmp.getCodigo() );
                        siseva_per.setSisevaPerPeso( tmp.getPeso() );
                        siseva_per.setSisevaPerOrden( tmp.getOrden() );
                        siseva_per.setSisevaPerActivo( "1" );
                        siseva_per.setClNotas( new LinkedHashSet<ClNota>() );

                        sis_eva_pers.add( siseva_per );
                    }

                } else if ( this.getTalape_id() == 0 || (talape != null && talape.getClSisEvaPersonalizados().isEmpty()) ) {
                    Set<ClSisEvaDetalle> siseva_detalles = daoSE.seleccionarSistemaEvaluacion( talape.getClSisEvaluacion().getSisevaId() ).getClSisEvaDetalles();
                    Iterator<ClSisEvaDetalle> it2 = siseva_detalles.iterator();
                    int i = 1;
                    while ( it2.hasNext() ) {
                        ClSisEvaDetalle siseva_det = it2.next();
                        ClSisEvaPersonalizado siseva_per = new ClSisEvaPersonalizado();

                        siseva_per.setSisevaPerNombre( siseva_det.getSisevaDetalleNombre() );
                        siseva_per.setSisevaPerExaSemana( "" );
                        siseva_per.setClSisEvaDetalle( siseva_det );
                        siseva_per.setClArbolAperturado( talape );
                        siseva_per.setSisevaCodigo( siseva_det.getSisevaDetalleCodigo() );
                        siseva_per.setSisevaPerPeso( 1d );
                        siseva_per.setSisevaPerOrden( i++ );
                        siseva_per.setSisevaPerActivo( "1" );
                        siseva_per.setClNotas( new LinkedHashSet<ClNota>() );
                        sis_eva_pers.add( siseva_per );
                    }
                }
                daoSE.insertarActualizar_SisEvaPersonalizado( sis_eva_pers );

                if ( q_sisEvaPer != null ) {
                    for ( int i = 0; i < q_sisEvaPer.size(); i++ ) {
                        BeanClSisEvaPersonalizado tmp = q_sisEvaPer.get( i );
                        daoSE.eliminarSistemaEvaluacionPersonalizado( tmp.getId() );
                    }
                }

                n_sisEvaPer = new ArrayList<BeanClSisEvaPersonalizado>();
                q_sisEvaPer = new ArrayList<BeanClSisEvaPersonalizado>();

                cargarTalleres( v_cur_id );
            }
        }
    }

    public void prepararEliminacionTalape( ActionEvent event ) {
        int id_taller_aper_tmp = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_taller_aper_tmp" )).getValue().toString() );

        HSArbolAperturadoClDAO arbApeClDAO = CommonDAO.getClArbolAperturadoClDAO();
        ClArbolAperturado talape = arbApeClDAO.seleccionarArbTallerAperturado( id_taller_aper_tmp );
        this.setElim_talape_id( talape.getArbapeId() );
        this.setElim_talape_desc( talape.getArbapeDescripcion() );

        if ( talape.getArbapeAperturado().equalsIgnoreCase( "1" ) ) {
            this.setElim_mensaje( "No se puede eliminar un taller que este aperturado." );
            this.setElim_band( false );
        } else {
            this.setElim_mensaje( "Se eliminara el taller aperturado." );
            this.setElim_band( true );
        }
    }

    public void eliminarTallerAperturado( ActionEvent event ) throws Exception {
        HSArbolAperturadoClDAO arbApeClDAO = CommonDAO.getClArbolAperturadoClDAO();
        arbApeClDAO.eliminarArbTallerAperturado( this.getElim_talape_id() );

        this.setElim_talape_id( 0 );
        this.setElim_talape_desc( "" );
        cargarTalleres( v_cur_id );
    }

    //GETTERS AND SETTERS
    public TreeNode getArbol() {
        if ( arbol == null ) {
            cargarArbol();
        }
        return arbol;
    }

    public void setArbol( TreeNode arbol ) {
        this.arbol = arbol;
    }

    public void addTalleres( bTallerAperturado tmp ) {
        this.talleres.add( tmp );
    }

    public List<bTallerAperturado> getTalleres() {
        return talleres;
    }

    public void setTalleres( List<bTallerAperturado> talleres ) {
        this.talleres = talleres;
    }

    public void addTalleres_aperturados( bTallerAperturado tmp ) {
        this.talleres_aperturados.add( tmp );
    }

    public List<bTallerAperturado> getTalleres_aperturados() {
        return talleres_aperturados;
    }

    public void setTalleres_aperturados( List<bTallerAperturado> talleres_aperturados ) {
        this.talleres_aperturados = talleres_aperturados;
    }

    public String getV_modulo() {
        return v_modulo;
    }

    public void setV_modulo( String v_modulo ) {
        this.v_modulo = v_modulo;
    }

    public String getV_curso() {
        return v_curso;
    }

    public void setV_curso( String v_curso ) {
        this.v_curso = v_curso;
    }

    public int getTalape_id() {
        return talape_id;
    }

    public void setTalape_id( int talape_id ) {
        this.talape_id = talape_id;
    }

    public String getTalape_descripcion() {
        return talape_descripcion;
    }

    public void setTalape_descripcion( String talape_descripcion ) {
        this.talape_descripcion = talape_descripcion;
    }

    public int getSiseva_id() {
        return siseva_id;
    }

    public void setSiseva_id( int siseva_id ) {
        this.siseva_id = siseva_id;
    }

    public int getTalape_num_horas() {
        return talape_num_horas;
    }

    public void setTalape_num_horas( int talape_num_horas ) {
        this.talape_num_horas = talape_num_horas;
    }

    public String getTalape_aperturado() {
        return talape_aperturado;
    }

    public void setTalape_aperturado( String talape_aperturado ) {
        this.talape_aperturado = talape_aperturado;
    }

    public int getTal_id() {
        return tal_id;
    }

    public void setTal_id( int tal_id ) {
        this.tal_id = tal_id;
    }

    public Date getTalape_fecha() {
        return talape_fecha;
    }

    public void setTalape_fecha( Date talape_fecha ) {
        this.talape_fecha = talape_fecha;
    }

    public String getTalape_vigente() {
        return talape_vigente;
    }

    public void setTalape_vigente( String talape_vigente ) {
        this.talape_vigente = talape_vigente;
    }

    public String getTalape_actual() {
        return talape_actual;
    }

    public void setTalape_actual( String talape_actual ) {
        this.talape_actual = talape_actual;
    }

    public String getTalape_activo() {
        return talape_activo;
    }

    public void setTalape_activo( String talape_activo ) {
        this.talape_activo = talape_activo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo( String tipo ) {
        this.tipo = tipo;
    }

    public SelectItem[] getSis_evaluaciones() {
        try {
            HSSistemaEvaluacionCLDAO dao =
                    (HSSistemaEvaluacionCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLSistemaEvaluacion" );
            List<ClSisEvaluacion> lista = dao.seleccionarSistemasEvaluacion();
            sis_evaluaciones = new SelectItem[ lista.size() + 1 ];
            for ( int i = 0; i < lista.size(); i++ ) {
                sis_evaluaciones[i + 1] = new SelectItem( lista.get( i ).getSisevaId(), lista.get( i ).getSisevaNombre() );
            }
        } catch ( Exception e ) {
            sis_evaluaciones = new SelectItem[ 1 ];
        } finally {
            sis_evaluaciones[0] = new SelectItem( 0, "[Seleccione]" );
        }

        return sis_evaluaciones;
    }

    public void setSis_evaluaciones( SelectItem[] sis_evaluaciones ) {
        this.sis_evaluaciones = sis_evaluaciones;
    }

    public String getV_descripcion() {
        return v_descripcion;
    }

    public void setV_descripcion( String v_descripcion ) {
        this.v_descripcion = v_descripcion;
    }

    public String getV_imagen_actual() {
        return v_imagen_actual;
    }

    public void setV_imagen_actual( String v_imagen_actual ) {
        this.v_imagen_actual = v_imagen_actual;
    }

    public String getV_sis_evaluacion() {
        return v_sis_evaluacion;
    }

    public void setV_sis_evaluacion( String v_sis_evaluacion ) {
        this.v_sis_evaluacion = v_sis_evaluacion;
    }

    public int getV_id() {
        return v_id;
    }

    /**
     * Taller_id
     *
     * @param v_id
     */
    public void setV_id( int v_id ) {
        this.v_id = v_id;
    }

    public String getV_codigo() {
        return v_codigo;
    }

    public void setV_codigo( String v_codigo ) {
        this.v_codigo = v_codigo;
    }

    public String getV_imagen_aperturar() {
        return v_imagen_aperturar;
    }

    public void setV_imagen_aperturar( String v_imagen_aperturar ) {
        this.v_imagen_aperturar = v_imagen_aperturar;
    }

    public String getV_imagen_vigente() {
        return v_imagen_vigente;
    }

    public void setV_imagen_vigente( String v_imagen_vigente ) {
        this.v_imagen_vigente = v_imagen_vigente;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete( String oncomplete ) {
        this.oncomplete = oncomplete;
    }

    public String getI_modulo() {
        return i_modulo;
    }

    public void setI_modulo( String i_modulo ) {
        this.i_modulo = i_modulo;
    }

    public String getI_curso() {
        return i_curso;
    }

    public void setI_curso( String i_curso ) {
        this.i_curso = i_curso;
    }

    public boolean isI_talape_aperturado() {
        return i_talape_aperturado;
    }

    public void setI_talape_aperturado( boolean i_talape_aperturado ) {
        this.i_talape_aperturado = i_talape_aperturado;
    }

    public boolean isI_talape_vigente() {
        return i_talape_vigente;
    }

    public void setI_talape_vigente( boolean i_talape_vigente ) {
        this.i_talape_vigente = i_talape_vigente;
    }

    public List<BeanClSisEvaPersonalizado> getN_sisEvaPer() {
        return n_sisEvaPer;
    }

    public void setN_sisEvaPer( List<BeanClSisEvaPersonalizado> n_sisEvaPer ) {
        this.n_sisEvaPer = n_sisEvaPer;
    }

    public List<BeanClSisEvaPersonalizado> getQ_sisEvaPer() {
        return q_sisEvaPer;
    }

    public void setQ_sisEvaPer( List<BeanClSisEvaPersonalizado> q_sisEvaPer ) {
        this.q_sisEvaPer = q_sisEvaPer;
    }

    public boolean isPersonalizado() {
        return personalizado;
    }

    public void setPersonalizado( boolean personalizado ) {
        this.personalizado = personalizado;
    }

    public String getEfecto() {
        return efecto;
    }

    public void setEfecto( String efecto ) {
        this.efecto = efecto;
    }

    public BeanClSisEvaPersonalizado getSep() {
        return sep;
    }

    public void setSep( BeanClSisEvaPersonalizado sep ) {
        this.sep = sep;
    }

    public void cambiarEfecto() {
        if ( this.isPersonalizado() ) {
            this.setEfecto( "showDiv()" );
        } else {
            this.setEfecto( "hideDiv({duration:0.7})" );
        }
    }

    public void cambioSisEvaluacion() {
        sep = new BeanClSisEvaPersonalizado( this.getSiseva_id() );
    }

    public void aplicarPlantilla( ActionEvent event ) {
        oncomplete = "alert('Aplicar plantilla')";
    }

    public void mostrarPlantilla( ActionEvent event ) {
        oncomplete = "Richfaces.showModalPanel('mpClSisEvaPerPlantilla')";
    }

    public void seleccionarPlantilla( ActionEvent event ) {
        if ( n_sisEvaPer == null ) {
            n_sisEvaPer = new ArrayList<BeanClSisEvaPersonalizado>();
        }
        for ( ClSisEvaPersonalizado sepTmp : lstClSisEvaPerPlant ) {
            BeanClSisEvaPersonalizado tmp = new BeanClSisEvaPersonalizado();
            tmp.setPos( n_sisEvaPer.size() );
            tmp.setCodigo( sepTmp.getSisevaCodigo() );
            tmp.setNombre( sepTmp.getSisevaPerNombre() );
            tmp.setOrden( sepTmp.getSisevaPerOrden() );
            tmp.setPeso( sepTmp.getSisevaPerPeso() );
            tmp.setExa_semana( sepTmp.getSisevaPerExaSemana() );
            tmp.setDetallen(  sepTmp.getClSisEvaDetalle().getSisevaDetalleNombre() );
            tmp.setDetalle( sepTmp.getClSisEvaDetalle().getSisevaDetalleId() );
//            tmp.setId( sepTmp.getSisevaPerId() );
            tmp.setId_siseva( sepTmp.getClSisEvaDetalle().getClSisEvaluacion().getSisevaId() );
            tmp.setTalape_id( talape_id );

            n_sisEvaPer.add( tmp );
            oncomplete = "Richfaces.hideModalPanel('mpClSisEvaPerPlantilla');";
        }
    }

    public void insertarSisEvaPer( ActionEvent event ) {
        this.setOncomplete( "" );
        UIParameter uip_talape_id = (UIParameter) event.getComponent().findComponent( "p_talape_id" );
        int p_talape_id = Integer.parseInt( String.valueOf( uip_talape_id.getValue() ) );

        if ( sep.getDetalle() == 0 ) {
            this.setOncomplete( "javascript:alert('Debe de escoger un tipo de promedio.');" );
        } else if ( sep.getCodigo().trim().length() == 0 ) {
            this.setOncomplete( "javascript:alert('Debe de ingresar el codigo.');" );
        } else if ( sep.getNombre().trim().length() == 0 ) {
            this.setOncomplete( "javascript:alert('Debe de ingresar el nombre.');" );
        } else if ( sep.getPeso() == 0 ) {
            this.setOncomplete( "javascript:alert('Debe de ingresar el peso de la nota.');" );
        } else if ( sep.getOrden() == 0 ) {
            this.setOncomplete( "javascript:alert('Debe de ingresar el Nº de orden.');" );
        } else {
            if ( n_sisEvaPer == null ) {
                n_sisEvaPer = new ArrayList<BeanClSisEvaPersonalizado>();
            }
            BeanClSisEvaPersonalizado tmp = new BeanClSisEvaPersonalizado();
            tmp.setPos( n_sisEvaPer.size() );
            tmp.setAlu_tipo( sep.getAlu_tipo() );
            tmp.setCodigo( sep.getCodigo() );
            tmp.setNombre( sep.getNombre() );
            tmp.setOrden( sep.getOrden() );
            tmp.setPeso( sep.getPeso() );
            tmp.setExa_semana( sep.getExa_semana() );
            tmp.setDetallen( sep.getNombre_Detalle( sep.getDetalle() ) );
            tmp.setDetalle( sep.getDetalle() );
            tmp.setId( sep.getId() );
            tmp.setId_siseva( sep.getId_siseva() );
            tmp.setTalape_id( p_talape_id );

            n_sisEvaPer.add( tmp );
            sep.limpiar();
        }
    }

    public void quitarSisEvaPer( ActionEvent event ) {
        UIParameter p2 = (UIParameter) event.getComponent().findComponent( "pos_det" );

        int pos_det = Integer.parseInt( String.valueOf( p2.getValue() ) );

        if ( q_sisEvaPer == null ) {
            q_sisEvaPer = new ArrayList<BeanClSisEvaPersonalizado>();
        }
        q_sisEvaPer.add( n_sisEvaPer.remove( pos_det ) );
        for ( int i = 0; i < n_sisEvaPer.size(); i++ ) {
            n_sisEvaPer.get( i ).setOrden( i + 1 );
            n_sisEvaPer.get( i ).setPos( i );
        }
    }

    public boolean isElim_band() {
        return elim_band;
    }

    public void setElim_band( boolean elim_band ) {
        this.elim_band = elim_band;
    }

    public String getElim_mensaje() {
        return elim_mensaje;
    }

    public void setElim_mensaje( String elim_mensaje ) {
        this.elim_mensaje = elim_mensaje;
    }

    public String getElim_talape_desc() {
        return elim_talape_desc;
    }

    public void setElim_talape_desc( String elim_talape_desc ) {
        this.elim_talape_desc = elim_talape_desc;
    }

    public int getElim_talape_id() {
        return elim_talape_id;
    }

    public void setElim_talape_id( int elim_talape_id ) {
        this.elim_talape_id = elim_talape_id;
    }
}
