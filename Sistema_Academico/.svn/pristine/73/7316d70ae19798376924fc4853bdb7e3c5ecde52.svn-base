/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.cursoLibre.hibernateSpringDao.HSHorarioDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAcademicoClDao;
import net.uch.cursoLibre.hibernateSpringDao.HSInicioDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSPlantillaHorarioCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSSeccionCLDAO;
import net.uch.cursoLibre.managedBeans.beans.BeanClHoraria;
import net.uch.cursoLibre.managedBeans.beans.BeanClSeccion;
import net.uch.mapping.AcLocal;
import net.uch.mapping.ClArbolAcademico;
import net.uch.mapping.ClHoraria;
import net.uch.mapping.ClInicio;
import net.uch.mapping.ClPlantillaHorario;
import net.uch.mapping.ClSeccion;
import net.uch.metodo.local.MetodosAca;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.util.CommonDAO;
import org.richfaces.component.UITree;
import org.richfaces.component.html.HtmlTree;
import org.richfaces.event.NodeSelectedEvent;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;

/**
 *
 * @author richard
 */
public class bMantenimientoInicio {

    private TreeNode arbol = null;
    private List<String> selectedNodeChildren = new ArrayList<String>();
    private String nombreNodo;
    private int arIdMod;
    private List<ClArbolAcademico> listaCursos = new ArrayList<ClArbolAcademico>();
    private ClInicio beanInicio = new ClInicio();
    private List<BeanClSeccion> listaSeccion = new ArrayList<BeanClSeccion>();
    private String oncomplete;
    private ClSeccion seccion = new ClSeccion();
    private SelectItem[] cboLocal;
    private SelectItem[] cboTurno;
    private int w_loc_id;
    private boolean w_apertura = false;
    private List<ClInicio> listaInicio = new ArrayList<ClInicio>();
    private int w_ini_id;

    /*
     * variables pa horario
     */
    private int h_sec_id;
    private String h_curso;
    private String h_sec_descripcion;
    private List<BeanClHoraria> nListaHoraria = new ArrayList<BeanClHoraria>();
    private List<BeanClHoraria> qListaHoraria = new ArrayList<BeanClHoraria>();
    private BeanClHoraria horaria = new BeanClHoraria( 0 );
    /*
     * 
     */

    /*
     * variable para guardar el indice de la seccion en el arreglo y tambien
     * para indicar de add o remplazar
     */
    private int b_indice_seccion;
    private boolean b_opcion;

    public boolean isB_opcion() {
        return b_opcion;
    }

    public void setB_opcion( boolean b_opcion ) {
        this.b_opcion = b_opcion;
    }

    public int getB_indice_seccion() {
        return b_indice_seccion;
    }

    public void setB_indice_seccion( int b_indice_seccion ) {
        this.b_indice_seccion = b_indice_seccion;
    }

    public String getH_curso() {
        return h_curso;
    }

    public void setH_curso( String h_curso ) {
        this.h_curso = h_curso;
    }

    public String getH_sec_descripcion() {
        return h_sec_descripcion;
    }

    public void setH_sec_descripcion( String h_sec_descripcion ) {
        this.h_sec_descripcion = h_sec_descripcion;
    }

    public int getH_sec_id() {
        return h_sec_id;
    }

    public void setH_sec_id( int h_sec_id ) {
        this.h_sec_id = h_sec_id;
    }

    public BeanClHoraria getHoraria() {
        return horaria;
    }

    public void setHoraria( BeanClHoraria horaria ) {
        this.horaria = horaria;
    }

    public List<BeanClHoraria> getnListaHoraria() {
        return nListaHoraria;
    }

    public void setnListaHoraria( List<BeanClHoraria> nListaHoraria ) {
        this.nListaHoraria = nListaHoraria;
    }

    public List<BeanClHoraria> getqListaHoraria() {
        return qListaHoraria;
    }

    public void setqListaHoraria( List<BeanClHoraria> qListaHoraria ) {
        this.qListaHoraria = qListaHoraria;
    }

    public void addnListaHoraria( BeanClHoraria tmp ) {
        this.nListaHoraria.add( tmp );
    }

    public void addqListaHoraria( BeanClHoraria tmp ) {
        this.qListaHoraria.add( tmp );
    }

    public int getW_ini_id() {
        return w_ini_id;
    }

    public void setW_ini_id( int w_ini_id ) {
        this.w_ini_id = w_ini_id;
    }

    public List<ClInicio> getListaInicio() {
        return listaInicio;
    }

    public void setListaInicio( List<ClInicio> listaInicio ) {
        this.listaInicio = listaInicio;
    }

    public boolean isW_apertura() {
        return w_apertura;
    }

    public void setW_apertura( boolean w_apertura ) {
        this.w_apertura = w_apertura;
    }

    public int getW_loc_id() {
        return w_loc_id;
    }

    public void setW_loc_id( int w_loc_id ) {
        this.w_loc_id = w_loc_id;
    }

    public SelectItem[] getCboLocal() throws SQLException {
        MetodosAca metodo = new MetodosAca();
        this.cboLocal = metodo.listarSedes( "Seleccione" );
        return cboLocal;
    }

    public void setCboLocal( SelectItem[] cboLocal ) {
        this.cboLocal = cboLocal;
    }

    public SelectItem[] getCboTurno() {
        HSPlantillaHorarioCLDAO dao = (HSPlantillaHorarioCLDAO) ServiceFinder.findBean( "SpringHibernateDaoPlantillaHorario" );
        List<ClPlantillaHorario> listaP = dao.listarPlantilla();
        System.out.println( "BeanClMantenimiento inicio -> cntidaad de la lista -> " + listaP.size() );
        this.cboTurno = new SelectItem[ listaP.size() + 1 ];
        this.cboTurno[0] = new SelectItem( 0, "[Seleccione]" );
        for ( int i = 0; i < (cboTurno.length - 1); i++ ) {
            this.cboTurno[i + 1] = new SelectItem( listaP.get( i ).getPlaId(), listaP.get( i ).getPlaDescripcion() );
        }
        return cboTurno;
    }

    public void setCboTurno( SelectItem[] cboTurno ) {
        this.cboTurno = cboTurno;
    }

    public ClSeccion getSeccion() {
        return seccion;
    }

    public void setSeccion( ClSeccion seccion ) {
        this.seccion = seccion;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete( String oncomplete ) {
        this.oncomplete = oncomplete;
    }

    public ClInicio getBeanInicio() {
        return beanInicio;
    }

    public void setBeanInicio( ClInicio beanInicio ) {
        this.beanInicio = beanInicio;
    }

    public List<ClArbolAcademico> getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos( List<ClArbolAcademico> listaCursos ) {
        this.listaCursos = listaCursos;
    }

    public List<BeanClSeccion> getListaSeccion() {
        return listaSeccion;
    }

    public void setListaSeccion( List<BeanClSeccion> listaSeccion ) {
        this.listaSeccion = listaSeccion;
    }

    public bMantenimientoInicio() {
        //cargarArbol();
    }

    public int getArIdMod() {
        return arIdMod;
    }

    public void setArIdMod( int arIdMod ) {
        this.arIdMod = arIdMod;
    }

    private void agregarHijos( TreeNode node, int idPadre ) {

        HSArbolAcademicoClDao daoArbol = CommonDAO.getClArbolAcademicoDAO();
        List<ClArbolAcademico> listaArbol = daoArbol.listarArbolPorPadre( idPadre );
        // int lista=listaArbol.size();
        String tipo;
        int ver = 0;
        for ( int i = 0; i < listaArbol.size(); i++ ) {
            TreeNodeImpl nodeImpl = new TreeNodeImpl();

            if ( listaArbol.get( i ).getArbTipo().equals( "069001" ) ) {
                nodeImpl.setData( "<div style='float: left;'>" + listaArbol.get( i ).getArbDescripcion() + "</div>" + "<div style='color:#FFFFFF;position: left;'>*" + listaArbol.get( i ).getArbId() + "</div>" );

                node.addChild( new Integer( i + 1 ), nodeImpl );
            } else {
                nodeImpl.setData( "<div style='float: left;'>" + listaArbol.get( i ).getArbDescripcion() + "</div>" + "<div style='color:#FFFFFF;position: left;'>*" + listaArbol.get( i ).getArbId() + "</div>" );
                node.addChild( new Integer( i + 1 ), nodeImpl );
                agregarHijos( nodeImpl, listaArbol.get( i ).getArbId() );

            }

            System.out.println( "agrego  de modos" );
            /*
             * boolean valor = true; while (valor) { TreeNodeImpl nodeImpl = new
             * TreeNodeImpl(); nodeImpl.setData("<div style='float: left;'>" +
             * listaArbol.get(i).getArbDescripcion() + "</div>" + "<div
             * style='color:#FFFFFF;position: left;'>*" +
             * listaArbol.get(i).getArbId() + "</div>");
             *
             * node.addChild(new Integer(i + 1), nodeImpl);
             * agregarHijos(nodeImpl, listaArbol.get(i).getArbId());
             *
             * if (listaArbol.get(i).getArbTipo().equals("069001")) { break; }
             *
             *
             * }
             */
        }


    }

    private void cargarArbol() {
        try {
            arbol = new TreeNodeImpl();
            agregarHijos( arbol, 0 );


        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void seleccionarElemento( NodeSelectedEvent event ) {
        HtmlTree tree = (HtmlTree) event.getComponent();
        //String nodos=tree.getRowKeyVar();
        UITree tree2 = (UITree) event.getComponent();
        String id = tree2.getRowKey().toString();
        nombreNodo = ((String) tree.getRowData()) + "  -  " + id;
        System.out.println( "el valor es -> " + devolverId( nombreNodo ) );
        this.setArIdMod( devolverId( nombreNodo ) );
        //seleccionarTaller(this.getArIdMod());
        seleccionarInicios( this.getArIdMod() );

        selectedNodeChildren.clear();
        //TreeNode currentNode = tree.getModelTreeNode(tree.getRowKey());
        TreeNode currentNode = tree.getTreeNode( tree.getRowKey() );
        if ( currentNode.isLeaf() ) {
            selectedNodeChildren.add( (String) currentNode.getData() );
        } else {
            Iterator<Map.Entry<Object, TreeNode>> it = currentNode.getChildren();
            while ( it != null && it.hasNext() ) {
                Map.Entry<Object, TreeNode> entry = it.next();
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

    public String getNombreNodo() {
        return nombreNodo;
    }

    public void setNombreNodo( String nombreNodo ) {
        this.nombreNodo = nombreNodo;
    }

    public int devolverId( String texto ) {
        int id = 0;
        int valor_ = texto.indexOf( "*" ) + 1;
        int valor__ = texto.indexOf( "<", valor_ );
        String numeroEs = texto.substring( valor_, valor__ );
        id = Integer.parseInt( numeroEs );
        return id;
    }

    public void seleccionarInicios( int arid_Mod ) {
        this.setListaInicio( new ArrayList<ClInicio>() );
        HSInicioDAO daoInicio = (HSInicioDAO) ServiceFinder.findBean( "SpringHibernateDaoInicio" );

        this.setListaInicio( daoInicio.listarIniciosporModulo( arid_Mod ) );
        //this.listaInicio.get(1).getAcLocal().
        //ClInicio ini;

        //1ini.getAcLocal().
    }

    public void nuevoInicio( ActionEvent event ) {
        this.setOncomplete( "" );
        this.setBeanInicio( new ClInicio() );
        this.getBeanInicio().setAcLocal( new AcLocal() );
        this.setW_apertura( false );

        this.getBeanInicio().setClArbolAcademico( new ClArbolAcademico( this.arIdMod ) );
        this.getBeanInicio().setIniActivo( "1" );
        this.setOncomplete( "javascript:Richfaces.showModalPanel('mpInicio');" );

    }

    public void nuevoInicioSeccion( ActionEvent event ) {
        this.setBeanInicio( new ClInicio() );
        this.setListaSeccion( new ArrayList<BeanClSeccion>() );
        this.setOncomplete( "" );
        this.setW_ini_id( 0 );
        HSInicioDAO daoInicio = (HSInicioDAO) ServiceFinder.findBean( "SpringHibernateDaoInicio" );
        HSSeccionCLDAO daoSec = (HSSeccionCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLSeccion" );
        int p_ini_id = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "p_ini_id" )).getValue().toString() );
        this.setBeanInicio( daoInicio.listarIniciosporId( p_ini_id ) );
        this.setW_ini_id( p_ini_id );
        List<ClSeccion> listaSec = daoSec.listarSeccionesPorInicio( p_ini_id );
        for ( int i = 0; i < listaSec.size(); i++ ) {
            this.listaSeccion.add( new BeanClSeccion( listaSec.get( i ) ) );
        }
        //this.setListaSeccion();
        if ( this.listaSeccion == null ) {
            this.setListaSeccion( new ArrayList<BeanClSeccion>() );
        }
        this.setListaCursos( new ArrayList<ClArbolAcademico>() );
        List<ClArbolAcademico> listaCurso = new ArrayList<ClArbolAcademico>();
        List<ClArbolAcademico> lista = new ArrayList<ClArbolAcademico>();
        if ( this.getArIdMod() > 0 ) {
            lista = agregarHijo( this.getArIdMod(), "069003", listaCurso );
        }
        this.setListaCursos( lista );
        this.setOncomplete( "javascript:Richfaces.showModalPanel('mpInicioDetalle');" );
    }

    public void nuevaSeccion( ActionEvent event ) {
        this.setOncomplete( "" );
        int con = 0;
        this.b_indice_seccion = 0;
        this.b_opcion = false;
        HSArbolAcademicoClDao daoArbol = CommonDAO.getClArbolAcademicoDAO();
        int idCurso = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "p_cur_id" )).getValue().toString() );
        ClArbolAcademico arbolAcade = daoArbol.seleccionarArbol( idCurso );
        //= 124;
        this.setOncomplete( "" );
        for ( int i = 0; i < this.listaSeccion.size(); i++ ) {
            if ( idCurso == this.listaSeccion.get( i ).getClArbolAcademico().getArbId() ) {
                con++;
            }
        }
        if ( con == 0 ) {
            this.setSeccion( new ClSeccion() );
            this.getSeccion().setClArbolAcademico( arbolAcade );
            System.out.println( "abrir seccion" );
            this.setOncomplete( "javascript:Richfaces.showModalPanel('mpSeccion');" );
        } else {
            this.setOncomplete( "javascript:alert('El curso ya esta asignado');" );
        }
        //List<ClSeccion> listaS=new ArrayList<ClSeccion>();
        //Set<ClSeccion> inicio_seccion=new LinkedHashSet<ClSeccion>();
    }
    int idSeccion = -1;

    public void agregarSeccion( ActionEvent event ) {
        if ( this.b_opcion ) {
            this.listaSeccion.set( b_indice_seccion, new BeanClSeccion( this.getSeccion() ) );

        } else {
            this.getSeccion().setSecId( idSeccion );
            //this.getSeccion().setClInicio( new ClInicio( this.w_ini_id ) );
            this.getListaSeccion().add( new BeanClSeccion( this.getSeccion() ) );
            idSeccion--;
        }
        this.setOncomplete( "javascript:Richfaces.hideModalPanel('mpSeccion');" );
    }

    public void guardarInicios( ActionEvent event ) {
        this.setOncomplete( "" );
        HSInicioDAO daoInicio = (HSInicioDAO) ServiceFinder.findBean( "SpringHibernateDaoInicio" );
        this.getBeanInicio().setClSeccions( null );
        this.getBeanInicio().setIniAperturado( "0" );
        if ( this.w_apertura ) {
            this.getBeanInicio().setIniAperturado( "1" );
        }
        daoInicio.guardarInicio( this.getBeanInicio() );
        this.setOncomplete( "javascript:Richfaces.hideModalPanel('mpInicio');" );
        seleccionarInicios( this.getArIdMod() );
    }

    public void guardarSecciones( ActionEvent event ) {
        this.setOncomplete( "" );
        HSSeccionCLDAO daoSec = (HSSeccionCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLSeccion" );
        List<ClSeccion> listaGuardar = new ArrayList<ClSeccion>();
        for ( int i = 0; i < this.listaSeccion.size(); i++ ) {
            // int arbid=this.listaSeccion.get(i).getse;
            this.listaSeccion.get( i ).setSecActivo( "1" );
            if ( this.listaSeccion.get( i ).getSecId() <= 0 ) {
                this.listaSeccion.get( i ).setSecId( null );
                //daoSec.insertarSeccion(this.listaSeccion.get(i));
            } /*
             * else { System.out.println("el id es -> " +
             * this.listaSeccion.get(i).getSecId()); ClSeccion sec =
             * this.listaSeccion.get(i); daoSec.actualizarSeccion(sec);
            }
             */
            this.listaSeccion.get( i ).setClHorarias( null );
            this.listaSeccion.get( i ).setClMatriculaTallers( null );
            this.listaSeccion.get( i ).setClNotas( null );
            // this.listaSeccion.get(i).getSeccion().setClArbolAcademico(new ClArbolAcademico(arbid));
            listaGuardar.add( this.listaSeccion.get( i ).getClSeccion() );

        }
        // daoSec.insertarSecciones(this.listaSeccion);
        daoSec.guardarSecciones( listaGuardar );
        this.setOncomplete( "mpInicioDetalle" );
    }

    public List<ClArbolAcademico> agregarHijo( int idPadre, String tipo, List<ClArbolAcademico> listaArbol ) {


        HSArbolAcademicoClDao daoArbol = CommonDAO.getClArbolAcademicoDAO();
        List<ClArbolAcademico> lista = daoArbol.listarArbolPorPadre( idPadre );
        //  System.out.println("el ultimo padre -> "+idPadre);
        for ( int i = 0; i < lista.size(); i++ ) {
            if ( lista.get( i ).getArbTipo().equals( tipo ) ) {
                listaArbol.add( lista.get( i ) );
            } else {
                agregarHijo( lista.get( i ).getArbId(), tipo, listaArbol );
            }
        }

        return listaArbol;
    }

    public void seleccionarInicioPorId( ActionEvent event ) {
        this.setW_apertura( false );
        this.setOncomplete( "" );
        this.setBeanInicio( new ClInicio() );

        int p_ini_id = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "p_ini_id" )).getValue().toString() );
        HSInicioDAO daoInicio = (HSInicioDAO) ServiceFinder.findBean( "SpringHibernateDaoInicio" );
        this.setBeanInicio( daoInicio.listarIniciosporId( p_ini_id ) );
        if ( this.getBeanInicio().getIniAperturado().equals( "1" ) ) {
            this.setW_apertura( true );
        }
        this.setOncomplete( "javascript:Richfaces.showModalPanel('mpInicio');" );
        //this.getListaSeccion().set(1, new BeanClSeccion());
    }


    /*
     * horarios
     */
    public void cargarHorario( ActionEvent event ) throws Exception {
        HSHorarioDAO daoH = (HSHorarioDAO) ServiceFinder.findBean( "SpringHibernateDaoCLHorario" );
        HSCatalogoDAO daoC = (HSCatalogoDAO) ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );
        this.setOncomplete( "" );
        int p_sec_id = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "p_sec_id" )).getValue().toString() );
        String p_curso = ((UIParameter) event.getComponent().findComponent( "p_curso" )).getValue().toString();
        String p_sec_nombre = ((UIParameter) event.getComponent().findComponent( "p_sec_nombre" )).getValue().toString();
        int p_loc_id = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "p_loc_id" )).getValue().toString() );
        if ( p_loc_id > 0 ) {
            this.setH_sec_id( p_sec_id );
            this.setH_curso( p_curso );
            this.setH_sec_descripcion( p_sec_nombre );
            this.horaria = new BeanClHoraria( p_sec_id );
            this.horaria.setW_sed_id( p_loc_id );
            this.nListaHoraria = new ArrayList<BeanClHoraria>();
            this.qListaHoraria = new ArrayList<BeanClHoraria>();
            List<ClHoraria> horarias = daoH.seleccionarHorario( p_sec_id );
            if ( !horarias.isEmpty() ) {
                for ( int i = 0; i < horarias.size(); i++ ) {
                    ClHoraria tmp_hor = horarias.get( i );
                    String local = tmp_hor.getAcAula().getPab().getLoc().getLocDes();
                    String pabe = tmp_hor.getAcAula().getPab().getPabDescripcion();
                    String aula = tmp_hor.getAcAula().getAulDes();
                    System.out.println( " el aula es " + local.substring( 0, 3 ) + "-" + pabe.substring( 0, 3 ) + "-" + aula );
                    System.out.println( "el aula xx -> " + horarias.get( i ).getAcAula().getAulDes() );
                    tmp_hor.getAcAula().setAulDes( local.substring( 0, 3 ) + "-" + pabe.substring( 0, 3 ) + "-" + aula );
                    BeanClHoraria tmp = new BeanClHoraria( tmp_hor );
                    tmp.setPosicion( i );
                    tmp.setV_hor_dia( daoC.seleccionarDescripcion( tmp_hor.getHorDia() ) );
                    tmp.setV_hor_tipo_clase( daoC.seleccionarDescripcion( tmp_hor.getHorTipoClase() ) );
                    tmp.setView_bool( true );
                    tmp.setEditable_bool( false );

                    this.addnListaHoraria( tmp );
                }
            }
            this.setOncomplete( "javascript:Richfaces.showModalPanel('mpHoraria')" );
        } else {
            this.setOncomplete( "javascript:alert('Debe Asignar un local')" );
        }
    }

    public void agregarHorario( ActionEvent event ) {
        String sMsg;
        this.setOncomplete( "" );
        sMsg = this.horaria.esValido();
        if ( sMsg.isEmpty() ) {
            int loc_id = this.horaria.getW_sed_id();
            this.horaria.actualizarDatos( this.getH_sec_id() );
            this.horaria.setPosicion( this.nListaHoraria.size() );
            this.addnListaHoraria( horaria );

            this.horaria = new BeanClHoraria( this.getH_sec_id() );
            this.horaria.setW_sed_id( loc_id );
        } else {
//            this.setOncomplete( "javascript:alert('Todos los campos son obligatorios.')" );
            this.setOncomplete( "javascript:alert('" + sMsg + "')" );
        }
    }

    public void removerHorario( ActionEvent event ) {
        int p_hor_pos = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "p_hor_pos" )).getValue().toString() );

        this.addqListaHoraria( nListaHoraria.get( p_hor_pos ) );
        this.nListaHoraria.remove( p_hor_pos );
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
        this.setOncomplete( "" );
        if ( this.nListaHoraria.isEmpty() ) {
            this.setOncomplete( "javascript:alert('Debe ingresar los horarios para Guardar.')" );
        } else if ( edicionActivada() ) {
            this.setOncomplete( "javascript:alert('Termine de editar los horarios.')" );
        } else {
            HSHorarioDAO daoH = (HSHorarioDAO) ServiceFinder.findBean( "SpringHibernateDaoCLHorario" );

            List<ClHoraria> lista_horarios = new ArrayList<ClHoraria>();
            for ( BeanClHoraria tmp_horaria : nListaHoraria ) {
                lista_horarios.add( tmp_horaria.getClHoraria() );
            }
            daoH.insertarActualizarHorarios( lista_horarios );
            if ( !qListaHoraria.isEmpty() ) {
                List<Integer> lista_hor_elim = new ArrayList<Integer>();
                for ( BeanClHoraria tmp_horaria : qListaHoraria ) {
                    if ( tmp_horaria.getHorId().intValue() >= 0 ) {
                        lista_hor_elim.add( tmp_horaria.getHorId() );
                    }
                }

                System.out.println( "la cantidad de los ids es -> " + lista_hor_elim );
                daoH.eliminarHorarios( lista_hor_elim );
            }
            this.setOncomplete( "javascript:alert('Generacion de horarios satisfactoria.');"
                    + "Richfaces.hideModalPanel('mpHoraria');" );
            //seleccionarTaller(this.getTalape_id());
        }
    }

    public void editarSeccion( ActionEvent event ) {
        this.setOncomplete( "" );
        this.b_indice_seccion = 0;
        this.b_opcion = false;
        int p_sec_id = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "p_sec_id" )).getValue().toString() );
        this.setSeccion( new ClSeccion() );
        for ( int i = 0; i < this.listaSeccion.size(); i++ ) {
            if ( p_sec_id == this.listaSeccion.get( i ).getSecId() ) {
                this.setSeccion( this.listaSeccion.get( i ).getClSeccion() );
                b_indice_seccion = i;
                b_opcion = true;
            }
        }
        this.setOncomplete( "javascript:Richfaces.showModalPanel('mpSeccion');" );
    }
}
