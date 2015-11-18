/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSHorarioDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSSeccionCLDAO;
import net.uch.cursoLibre.managedBeans.beans.BeanClAlumno;
import net.uch.cursoLibre.managedBeans.beans.BeanClHoraria;
import net.uch.cursoLibre.managedBeans.beans.BeanClSeccion;
import net.uch.mapping.ClHoraria;
import net.uch.mapping.ClSeccion;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.util.ObjNodeCLGnral;
import net.uch.util.Reporte;
import org.richfaces.component.UITree;
import org.richfaces.event.NodeSelectedEvent;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;

/**
 *
 * @author cesardl
 */
public class bSeccionCLbak {

    //General
    private int talape_id;
    private String talape_nombre;
    private int cur_id;
    private String cur_nombre;
    private int mod_id;
    private String mod_nombre;
    private List<BeanClSeccion> listaSecciones;
    private String oncomplete;
    private String style;
    //private int tal_dest_id;
    // private int sec_dest_id;
    // private int estpag_dest_id;
    //private SelectItem[] tal_dest_desc;
    // private SelectItem[] sec_dest_desc;
    //  private SelectItem[] estpag_dest_desc;
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
    private TreeNode arbol;
    private List<ObjNodeCLGnral> nodos;
    //Constantes
    // private final int EXCEDE_CANTIDAD_ALUMNOS = 1;
    //private final int PERTENECE_AL_MISMO_CURSO = 2;
    private String b_sec_id;
    private String tituloReporte = "";
    private String valorRep = "";
    private int tal_id;

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
    public bSeccionCLbak() {
        this.talape_id = 0;
        this.seccion = new BeanClSeccion();
        this.horaria = new BeanClHoraria( 0 );
        this.listaSecciones = new ArrayList<BeanClSeccion>();
        this.nListaHoraria = new ArrayList<BeanClHoraria>();
        this.qListaHoraria = new ArrayList<BeanClHoraria>();
    }

//    public void cargarArbol() {
//        try {
//            String estructura = armarEstructura();
//            StringBuffer buffer = new StringBuffer(estructura);
//            ByteArrayInputStream estructura_arbol = new ByteArrayInputStream(buffer.toString().getBytes("ISO-8859-1"));
//            Properties properties = new Properties();
//            properties.load(estructura_arbol);
//            arbol = new TreeNodeImpl();
//            agregarNodos(null, arbol, properties);
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
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

//    public String armarEstructura() throws Exception {
//        String estructura = "";
//        String estructura_area = "";
//        String estructura_modulo = "";
//        String estructura_curso = "";
//        String estructura_taller = "";
//        HSAreaDAO daoA = (HSAreaDAO) ServiceFinder.findBean("SpringHibernateDaoCLArea");
//        HSModuloDAO daoM = (HSModuloDAO) ServiceFinder.findBean("SpringHibernateDaoCLModulo");
//        HSCursoCLDAO daoC = (HSCursoCLDAO) ServiceFinder.findBean("SpringHibernateDaoCLCurso");
//        HSTallerAperturadoDAO daoTA = (HSTallerAperturadoDAO) ServiceFinder.findBean("SpringHibernateDaoCLTallerAperturado");
//        List areas = daoA.seleccionarArea("");
//
//        List<ObjNodeCLGnral> lnodo = new ArrayList<ObjNodeCLGnral>();
//        for (int i = 0; i < areas.size(); i++) {
//            List modulos = daoM.seleccionarModulos(((ClArea) areas.get(i)).getAreId(), "");
//            estructura_area += (i + 1) + "=" + ((ClArea) areas.get(i)).getAreDescripcion() + "\n";
//           // System.out.println("la estructura area +> "+estructura_area);
//            for (int j = 0; j < modulos.size(); j++) {
//                List<ClCurso> cursos = daoC.seleccionarCursos(((ClModulo) modulos.get(j)).getModId());
//                if (!cursos.isEmpty()) {
//                    for (int k = 0; k < cursos.size(); k++) {
//                        List<ClTallerAperturado> talleres = daoTA.seleccionarTallerAperturadoPorCurso(cursos.get(k).getCurId());
//                        if (!talleres.isEmpty()) {
//                            estructura_modulo += (i + 1) + "." + (j + 1) + "=" + ((ClModulo) modulos.get(j)).getModDescripcion() + "\n";
//                           // System.out.println("la estructura modulo +> "+estructura_modulo);
//                            estructura_curso += (i + 1) + "." + (j + 1) + "." + (k + 1) + "=" + cursos.get(k).getCurNombre() + "\n";
//                           // System.out.println("la estructura curso +> "+estructura_curso);
//                            for (int l = 0; l < talleres.size(); l++) {
//                                estructura_taller += (i + 1) + "." + (j + 1) + "." + (k + 1) + "." + (l + 1) + "=" + talleres.get(l).getTalapeDescripcion() + "\n";
//                                // System.out.println("la estructura taller +> "+estructura_taller);
//                                lnodo.add(new ObjNodeCLGnral((i + 1) + ":" + (j + 1) + ":" + (k + 1) + ":" + (l + 1),
//                                        talleres.get(l).getTalapeId(),
//                                        talleres.get(l).getTalapeDescripcion(),
//                                        cursos.get(k).getCurId(),
//                                        cursos.get(k).getCurNombre(),
//                                        ((ClModulo) modulos.get(j)).getModId(),
//                                        ((ClModulo) modulos.get(j)).getModDescripcion()));
//                                System.out.println("primer valor -> "+(i + 1) + ":" + (j + 1) + ":" + (k + 1) + ":" + (l + 1)+"\t"+talleres.get(l).getTalapeId()+"\t"+talleres.get(l).getTalapeDescripcion()+
//                                                "\t"+cursos.get(k).getCurId()+"\t"+cursos.get(k).getCurNombre()+"\t"+((ClModulo) modulos.get(j)).getModId()+"\t"+((ClModulo) modulos.get(j)).getModDescripcion());
//
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        this.nodos = new ArrayList<ObjNodeCLGnral>(lnodo);
//        estructura = estructura_area + estructura_modulo + estructura_curso + estructura_taller;
//       
//
//
//       // System.out.println("la estructura real es -> "+estructura);*/
//        return estructura;
//    }
    public ObjNodeCLGnral buscarNodo( String id ) {
        for ( int i = 0; i < nodos.size(); i++ ) {
            ObjNodeCLGnral nodo = nodos.get( i );
            if ( nodo.getId().equalsIgnoreCase( id ) ) {
                return nodo;
            }
        }
        return null;
    }

//    public TreeNode getArbol() {
//        if (arbol == null) {
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

    public void seleccion( NodeSelectedEvent event ) {
        try {
            UITree tree = (UITree) event.getComponent();
            String id = tree.getRowKey().toString();
            int length = id.length();

            if ( length >= 7 ) {
                ObjNodeCLGnral nodo = buscarNodo( id );

                this.setTalape_id( nodo.getTalape_id() );
                this.setTalape_nombre( nodo.getTalape_nombre() );
                this.setCur_id( nodo.getCur_id() );
                this.setCur_nombre( nodo.getCur_nombre() );
                this.setMod_id( nodo.getMod_id() );
                this.setMod_nombre( nodo.getMod_nombre() );

                seleccionarTaller( this.getTalape_id() );
            }
        } catch ( Exception e ) {
            System.err.println( "Error al seleccionar el nodo " + e.getMessage() );
        }
    }

    public void seleccionarTaller( int talape_id ) {
        try {
            HSSeccionCLDAO daoSec = (HSSeccionCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLSeccion" );
            HSHorarioDAO daoHor = (HSHorarioDAO) ServiceFinder.findBean( "SpringHibernateDaoCLHorario" );

            List<ClSeccion> lsec = daoSec.seleccionarSecciones( talape_id );
            this.listaSecciones = new ArrayList<BeanClSeccion>();
            for ( ClSeccion tmp_sec : lsec ) {
                BeanClSeccion tmp = new BeanClSeccion( tmp_sec );
                if ( daoHor.seleccionarHorario( tmp_sec.getSecId() ).isEmpty() ) {
                    tmp.setV_imagen_horario( "/Imagenes/actions/horario_gris.png" );
                } else {
                    tmp.setV_imagen_horario( "/Imagenes/actions/horario.png" );
                }
                tmp.setCantMatriculados( daoHor.matriculadosSeccion( tmp_sec.getSecId() ).size() );
                this.addListaSecciones( tmp );

            }
        } catch ( Exception e ) {
            System.err.println( "Error al seleccionar los talleres " + e.getMessage() );
        }
    }

    public void seleccionarSeccion( ActionEvent event ) {
        if ( this.talape_id != 0 ) {
            this.setOncomplete( "" );
            try {
                int p_sec_id = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "p_sec_id" )).getValue().toString() );
                HSSeccionCLDAO daoSec = (HSSeccionCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLSeccion" );

                this.seccion = new BeanClSeccion( daoSec.seleccionarSeccion( p_sec_id ) );
            } catch ( NullPointerException npe ) {
                this.seccion = new BeanClSeccion( this.talape_id );
            }
            this.setOncomplete( "Richfaces.showModalPanel('mpSeccion',{top:100})" );
        }
    }

    public void verificarEliminacion( ActionEvent event ) {
        int p_sec_id = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "p_sec_id" )).getValue().toString() );
        String p_sec_detalle = ((UIParameter) event.getComponent().findComponent( "p_sec_detalle" )).getValue().toString();

        HSSeccionCLDAO daoSec = (HSSeccionCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLSeccion" );
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
        HSSeccionCLDAO daoSec = (HSSeccionCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLSeccion" );
        daoSec.eliminarSeccion( this.getSec_id() );

        seleccionarTaller( this.getTalape_id() );

        this.setSec_id( 0 );
        this.setSec_detalle( "" );
    }

    public void insertarActualizarSeccion( ActionEvent event ) {
        String mensaje = "";
        String sMensajeError;
        this.setOncomplete( mensaje );
        sMensajeError = this.seccion.esValido();
        if ( sMensajeError.isEmpty() ) {
            HSSeccionCLDAO dao = (HSSeccionCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLSeccion" );
            if ( this.seccion.getSecId().intValue() == 0 ) {
                dao.insertarSeccion( this.seccion.getClSeccion() );
                mensaje = "javascript:alert('Registro exitoso.');";
            } else {
                dao.actualizarSeccion( this.seccion.getClSeccion() );
                mensaje = "javascript:alert('Actualizacion exitosa.');";
            }
            mensaje = mensaje.concat( "Richfaces.hideModalPanel('mpSeccion');" );
        } else {
            mensaje = "javascript:alert('" + sMensajeError + "');";
        }
        this.setOncomplete( mensaje );
        seleccionarTaller( this.getTalape_id() );
    }

    public void cargarHorario( ActionEvent event ) throws Exception {
        HSHorarioDAO daoH = (HSHorarioDAO) ServiceFinder.findBean( "SpringHibernateDaoCLHorario" );
        HSCatalogoDAO daoC = (HSCatalogoDAO) ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );

        int p_sec_id = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "p_sec_id" )).getValue().toString() );
        String p_sec_detalle = ((UIParameter) event.getComponent().findComponent( "p_sec_detalle" )).getValue().toString();

        this.setSec_id( p_sec_id );
        this.setSec_detalle( p_sec_detalle );
        this.horaria = new BeanClHoraria( p_sec_id );
        this.nListaHoraria = new ArrayList<BeanClHoraria>();
        this.qListaHoraria = new ArrayList<BeanClHoraria>();
        List<ClHoraria> horarias = daoH.seleccionarHorario( p_sec_id );
        if ( !horarias.isEmpty() ) {
            for ( int i = 0; i < horarias.size(); i++ ) {
                ClHoraria tmp_hor = horarias.get( i );

                BeanClHoraria tmp = new BeanClHoraria( tmp_hor );
                tmp.setPosicion( i );
                tmp.setV_hor_dia( daoC.seleccionarDescripcion( tmp_hor.getHorDia() ) );
                tmp.setV_hor_tipo_clase( daoC.seleccionarDescripcion( tmp_hor.getHorTipoClase() ) );
                tmp.setView_bool( true );
                tmp.setEditable_bool( false );

                this.addnListaHoraria( tmp );
            }
        }
    }

    public void agregarHorario( ActionEvent event ) {
        String sMsg;
        this.setOncomplete( "" );
        sMsg = this.horaria.esValido();
        if ( sMsg.isEmpty() ) {

            this.horaria.actualizarDatos( this.getSec_id() );
            this.horaria.setPosicion( this.nListaHoraria.size() );
            this.addnListaHoraria( horaria );

            this.horaria = new BeanClHoraria( this.getSec_id() );
        } else {
            this.setOncomplete( "javascript:alert('" + sMsg + "')" );
        }
    }

    public void removerHorario( ActionEvent event ) {
        int p_hor_pos = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "p_hor_pos" )).getValue().toString() );

        this.addqListaHoraria( this.nListaHoraria.remove( p_hor_pos ) );
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
                    if ( tmp_horaria.getHorId().intValue() <= 0 ) {
                        lista_hor_elim.add( tmp_horaria.getHorId() );
                    }
                }
                daoH.eliminarHorarios( lista_hor_elim );
            }
            this.setOncomplete( "javascript:alert('Generacion de horarios satisfactoria.');"
                    + "Richfaces.hideModalPanel('mpHoraria');" );
            seleccionarTaller( this.getTalape_id() );
        }
    }
    /*
     * public void cargarMatriculados(ActionEvent event) throws Exception { int
     * p_sec_id = Integer.parseInt(((UIParameter)
     * event.getComponent().findComponent("p_sec_id")).getValue().toString());
     * String p_sec_detalle = ((UIParameter)
     * event.getComponent().findComponent("p_sec_detalle")).getValue().toString();
     * int p_tal_id = Integer.parseInt(((UIParameter)
     * event.getComponent().findComponent("p_tal_id")).getValue().toString());
     * System.out.println("taller tal_id -> "+p_tal_id);
     * this.setTal_id(p_tal_id);
     *
     * this.setSec_detalle(p_sec_detalle); this.listaAlumnosMatriculados = new
     * ArrayList<BeanClAlumno>();
     *
     * HSHorarioDAO daoH = (HSHorarioDAO)
     * ServiceFinder.findBean("SpringHibernateDaoCLHorario"); List<ClAlumno>
     * lista = daoH.matriculadosSeccion(p_sec_id); if (lista.isEmpty()) {
     * this.setStyle("background-color: gray;"); } else { this.setStyle("");
     * this.sec_id = p_sec_id; this.sec_dest_id = 0; this.estpag_dest_id = 0;
     * for (int i = 0; i < lista.size(); i++) { ClAlumno tmp_alu = lista.get(i);
     * BeanClAlumno alu = new BeanClAlumno(tmp_alu); alu.setAluContador(i + 1);
     *
     * this.addListaAlumnosMatriculados(alu); } } }
     */
    /*
     * public void guardarMatriculados(ActionEvent event) throws Exception {
     * this.setOncomplete(""); HSSeccionCLDAO daoSec = (HSSeccionCLDAO)
     * ServiceFinder.findBean("SpringHibernateDaoCLSeccion"); ClSeccion tmp_sec
     * = daoSec.seleccionarSeccion(this.getSec_dest_id());
     *
     * if (this.getSec_dest_id() == 0) {
     * this.setOncomplete("javascript:alert('Seleccione una seccion.');"); }
     * else if (this.getEstpag_dest_id() == 0) {
     * this.setOncomplete("javascript:alert('Seleccione una estructura de
     * pago.');"); } else if (validaciones(EXCEDE_CANTIDAD_ALUMNOS, tmp_sec)) {
     * this.setOncomplete("javascript:alert('Muchos alumnos para
     * matricular.');"); } else if (validaciones(PERTENECE_AL_MISMO_CURSO,
     * tmp_sec)) { this.setOncomplete("javascript:alert('La seccion pertenece al
     * mismo curso.');"); } else { List<ClMatricula> matriculas = new
     * ArrayList<ClMatricula>(); HSMatriculaCLDAO daoM = (HSMatriculaCLDAO)
     * ServiceFinder.findBean("SpringHibernateDaoCLMatricula"); for
     * (BeanClAlumno bean_alumno : listaAlumnosMatriculados) { if
     * (bean_alumno.isAluMatriculado() &&
     * !daoM.estaMatriculado(bean_alumno.getAluId(), tmp_sec.getSecId())) {
     * ClAlumno alu = new ClAlumno(bean_alumno.getAluId());
     *
     * ClMatricula mat = new ClMatricula(alu); mat.setMatActivo("1");
     * mat.setMatCodigo("MAT-BL"); mat.setMatFecha(new Timestamp(new
     * Date().getTime())); mat.setMatTipo("022005");
     * mat.setUsuId(capturarUsuario());
     *
     * Set<ClMatriculaTaller> mattals = new LinkedHashSet<ClMatriculaTaller>(1);
     * ClMatriculaTaller mattal = new ClMatriculaTaller();
     * mattal.setMattalActivo("1"); mattal.setMattalEstado("1");
     * mattal.setMattalObs("Alumno matriculado por bloque");
     * mattal.setClMatricula(mat); mattal.setClSeccion(tmp_sec);
     * mattals.add(mattal);
     *
     * mat.setClMatriculaTallers(mattals);
     *
     * matriculas.add(mat); } }
     *
     * daoM.insertarMatriculas(matriculas);
     *
     * HSEstructuraPagoDAO daoEP = (HSEstructuraPagoDAO)
     * ServiceFinder.findBean("SpringHibernateDaoCLEstructuraPago");
     * List<ClEstructuraPagosDetalle> detalles =
     * daoEP.seleccionarDetalle(this.getEstpag_dest_id()); List<ClAlumnoTarifa>
     * alumnoTarifas = new ArrayList<ClAlumnoTarifa>(); int tmp_tal_id =
     * tmp_sec.getClTallerAperturado().getClTaller().getTalId(); for
     * (ClMatricula mat : matriculas) { for (ClEstructuraPagosDetalle estpagdet
     * : detalles) { if (estpagdet.getTalId() == tmp_tal_id) { ClAlumnoTarifa
     * alutar = new ClAlumnoTarifa(); alutar.setAlutarActivo("1");
     * alutar.setAlutarAluTipo("014003"); alutar.setAlutarEstado("0");
     * alutar.setAlutarFechaPago(tmp_sec.getSecFinicio());
     * alutar.setAlutarFechaProrroga(tmp_sec.getSecFinicio());
     * alutar.setAlutarMonto(estpagdet.getEstpagdetMonto());
     * alutar.setClAlumno(mat.getClAlumno());
     * alutar.setClEstructuraPagosDetalle(estpagdet);
     * alutar.setConpagId(estpagdet.getAdConceptoPago().getId());
     * alutar.setMatId(mat.getMatId()); alutar.setSecId(tmp_sec.getSecId());
     *
     * alumnoTarifas.add(alutar); break; } } } HSAlumnoTarifaCLDAO daoAT =
     * (HSAlumnoTarifaCLDAO)
     * ServiceFinder.findBean("SpringHibernateDaoCLAlumnoTarifa");
     * daoAT.generarAlumnosTarifa(alumnoTarifas);
     * this.setOncomplete("javascript:alert('Alumnos matriculados con exito en
     * el taller " + tmp_sec.getClTallerAperturado().getTalapeDescripcion() +
     * ".');" + "Richfaces.hideModalPanel('mpMatriculados');"); } }
     */
    /*
     * private boolean validaciones(int caso, ClSeccion sec) { switch (caso) {
     * case EXCEDE_CANTIDAD_ALUMNOS: HSHorarioDAO daoH = (HSHorarioDAO)
     * ServiceFinder.findBean("SpringHibernateDaoCLHorario");
     *
     * int alumnos_matriculados =
     * daoH.matriculadosSeccion(this.getSec_dest_id()).size(); int total_alumnos
     * = getTotalParaMatricular() + alumnos_matriculados; return
     * sec.getSecVacMax().intValue() < total_alumnos ? true : false;
     *
     * case PERTENECE_AL_MISMO_CURSO: int tmp_tal_id =
     * sec.getClTallerAperturado().getClTaller().getTalId(); return tmp_tal_id
     * == this.getTal_id() ? true : false;
     *
     * default: return false; } }
     */
    /*
     * private int capturarUsuario() { HttpSession session = (HttpSession)
     * FacesContext.getCurrentInstance().
     * getExternalContext().getSession(false); int id_usu = ((bUsuario)
     * session.getAttribute("usuario")).getId_usu(); return id_usu;
    }
     */

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
    /*
     * public SelectItem[] getTal_dest_desc() throws Exception { if
     * (this.getMod_id() == 0) { tal_dest_desc = new SelectItem[1]; } else {
     * HSTallerAperturadoDAO daoTA = (HSTallerAperturadoDAO)
     * ServiceFinder.findBean("SpringHibernateDaoCLTallerAperturado");
     * System.out.println("el listado del tal_id -> "+this.getTal_id());
     * List<ClTallerAperturado> talapes =
     * daoTA.seleccionarTalleresAperturadosPorModulo(this.getMod_id(),
     * this.getTal_id());
     *
     * tal_dest_desc = new SelectItem[talapes.size() + 1]; for (int i = 0; i <
     * talapes.size(); i++) { ClTallerAperturado talape = talapes.get(i);
     * tal_dest_desc[i + 1] = new SelectItem(talape.getClTaller().getTalId(),
     * talape.getTalapeDescripcion()); } } tal_dest_desc[0] = new SelectItem(0,
     * "[Seleccione]"); return tal_dest_desc; }
     *
     * public void setTal_dest_desc(SelectItem[] tal_dest_desc) {
     * this.tal_dest_desc = tal_dest_desc; }
     */
    /*
     * public int getTal_dest_id() { return tal_dest_id; }
     *
     * public void setTal_dest_id(int tal_dest_id) { this.tal_dest_id =
     * tal_dest_id;
    }
     */
    /*
     * public SelectItem[] getSec_dest_desc() throws Exception { if
     * (this.getTal_dest_id() == 0) { sec_dest_desc = new SelectItem[1]; } else
     * { HSSeccionCLDAO daoSec = (HSSeccionCLDAO)
     * ServiceFinder.findBean("SpringHibernateDaoCLSeccion"); List<ClSeccion>
     * secs = daoSec.listarSeccionXTaller(this.getTal_dest_id());
     *
     * sec_dest_desc = new SelectItem[secs.size() + 1]; for (int i = 0; i <
     * secs.size(); i++) { ClSeccion sec = secs.get(i); sec_dest_desc[i + 1] =
     * new SelectItem(sec.getSecId(), sec.getSecCodigo() + " | " +
     * sec.getSecNombre()); } } this.sec_dest_id = 0; sec_dest_desc[0] = new
     * SelectItem(0, "[Seleccione]"); return sec_dest_desc; }
     */
    /*
     * public void setSec_dest_desc(SelectItem[] sec_dest_desc) {
     * this.sec_dest_desc = sec_dest_desc; }
     *
     * public int getSec_dest_id() { return sec_dest_id; }
     *
     * public void setSec_dest_id(int sec_dest_id) { this.sec_dest_id =
     * sec_dest_id; }
     *
     * public SelectItem[] getEstpag_dest_desc() { if (this.getTal_dest_id() ==
     * 0) { estpag_dest_desc = new SelectItem[1]; } else { HSEstructuraPagoDAO
     * daoEP = (HSEstructuraPagoDAO)
     * ServiceFinder.findBean("SpringHibernateDaoCLEstructuraPago");
     * List<ClEstructuraPagos> estpags =
     * daoEP.seleccionarEstructurasXTaller(this.getTal_dest_id());
     *
     * estpag_dest_desc = new SelectItem[estpags.size() + 1]; for (int i = 0; i
     * < estpags.size(); i++) { ClEstructuraPagos estpag = estpags.get(i);
     * estpag_dest_desc[i + 1] = new SelectItem(estpag.getEstpagId(),
     * estpag.getEstpagNombre()); } } this.estpag_dest_id = 0;
     * estpag_dest_desc[0] = new SelectItem(0, "[Seleccione]"); return
     * estpag_dest_desc; }
     *
     * public void setEstpag_dest_desc(SelectItem[] estpag_dest_desc) {
     * this.estpag_dest_desc = estpag_dest_desc; }
     *
     * public int getEstpag_dest_id() { return estpag_dest_id; }
     *
     * public void setEstpag_dest_id(int estpag_dest_id) { this.estpag_dest_id =
     * estpag_dest_id; }
     */

    public int getSec_id() {
        return sec_id;
    }

    public void setSec_id( int sec_id ) {
        this.sec_id = sec_id;
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
        this.setB_sec_id( ((UIParameter) event.getComponent().findComponent( "p_sec_id" )).getValue().toString() );
    }

    public void cargarReporte( OutputStream out, Object data ) throws IOException, Exception, EOFException {
        Reporte reporte = new Reporte();
        FacesContext context = FacesContext.getCurrentInstance();
        HashMap parametros = new HashMap();
        parametros.put( "logo", context.getExternalContext().getResource( "/Imagenes/actions/logo_p.jpg" ) );
        parametros.put( "sec_id", this.getB_sec_id() );
        reporte.cargarReporte( out, data, parametros, "rep_listado_alumnos_seccion.jasper" );
    }
}
