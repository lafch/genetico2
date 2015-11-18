package net.uch.academica.managedBeans;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.faces.event.ActionEvent;
import net.uch.academica.hibernateSpringDao.HSAperturaCursosDAO;
import net.uch.academica.hibernateSpringDao.HSSeccionDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcCursoAperturado;
import net.uch.mapping.AcEspecialidad;
import net.uch.mapping.AcFacultad;
import net.uch.mapping.AcPlanCurso;
import net.uch.mapping.AcSeccion;
import net.uch.mapping.AcSemestre;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSFacultadDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSSemestreDAO;
import org.richfaces.component.UITree;
import org.richfaces.event.NodeSelectedEvent;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;
import javax.faces.application.Application;
import javax.faces.component.UIInput;
import javax.faces.component.UIParameter;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import net.uch.academica.hibernateSpringDao.HSMatriculaDAO;
import net.uch.academica.hibernateSpringDao.HSNotaDAO;
import net.uch.mapping.AcAlumno;
import net.uch.mapping.AcMatricula;
import net.uch.mapping.AcNota;
import net.uch.mapping.AcSisEvaPersonalizado;
import java.math.BigDecimal;
import java.util.Date;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.context.ExternalContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import net.uch.academica.hibernateSpringDao.HSActaDAO;
import net.uch.academica.hibernateSpringDao.HSAlumnoDAO;
import net.uch.academica.hibernateSpringDao.HSImportacionNotasDAO;
import net.uch.academica.hibernateSpringDao.HSNotasTemporalesDAO;
import net.uch.academica.hibernateSpringDao.HSSistemaEvaluacionDAO;

import net.uch.academica.hibernateSpringDao.HSAlumnoNotaDAO;
import net.uch.cursoLibre.managedBeans.beans.Column;
import net.uch.mapping.AcActa;
import net.uch.mapping.AcActaDetalle;
import net.uch.mapping.AcImportacionNotas;
import net.uch.mapping.AcNotasTemporales;
import net.uch.mapping.AcSisEvaDetalle;
import net.uch.mapping.AcSisEvaluacion;

import net.uch.mapping.*;
import net.uch.tablaSistema.managedBeans.bUsuario;

import net.uch.tablaSistema.managedBeans.bUsuario;
import net.uch.util.ObjNodeGnral;
import org.richfaces.component.html.HtmlSeparator;
import org.richfaces.component.html.HtmlSpacer;

public class bNota {

    private TreeNode arbol;
//    public Object O[][][][][][] = new Object[20][20][20][20][20][2]; //static
//    public Object P[][][] = new Object[20][20][20]; //static
    int r = 0;
    private String nodoSeleccionado;
    private List listaNotas;
    private int id_sec;
    private String codigo_sec;
    private String nombre_sec;
    private int vacantes_sec;
    private String cur_ape_nombre;
    private int id_hor;
    private int id_sec_horario;
    private int cur_aperturado;
    private String oncomplete;
    private int ide = 0;
    private int curape_id;
    private int id_cur_ape;
    private String cur_nombre;
    private HtmlPanelGrid htmlPanel;
    private HtmlPanelGrid htmlPanelActa;
    private int nro_columnas;
    private List list;
    private List lista;
    private List Hidden;
    private String nombre_sec1;
    private String nombre_acta;
    private String codigo_acta;
    private String numero_acta;
    private List promedios;
    private String curso_nombre;
    private String nombre_acta_final;
    private String codigo_acta_final;
    private String numero_acta_final;
    private List listaActas;
    private String prom_actividades;
    private String prom_actividades1;
    private String prom_actividades2;
    private String prom_investigacion;
    private String parciales;
    private String prom_lecturas;
    private String finales;
    private String final_finales;
    private String alumno_nombre_aFinal;
    private String alumno_codigo_aFinal;
    private int alumno_id;
    private boolean editable = false;
    private boolean view = true;
    private String sustitutorio;
    private String sustitutorio_auxiliar;
    private int sustitutorio_id;
    private int parciales_id;
    private int finales_id;
    private int prom_investigacion_id;
    private int prom_actividades_id;
    private int prom_actividades_id1;
    private int prom_actividades_id2;
    private int prom_lecturas_id;
    private int final_finales_id;
    private int parciales_det;
    private int finales_det;
    private int prom_investigacion_det;
    private int prom_actividades_det;
    private int prom_actividades_det1;
    private int prom_actividades_det2;
    private int prom_lecturas_det;
    private String tipo_det;
    //bloqueo web
    private boolean bloqueo;
    //persistir valor de Seleccion
    private int id_curape_v;
    private int id_semape_v;
    private List<ObjNodeGnral> nodos;
    private List<bNota> w_sistema_evaluacion = new ArrayList<bNota>();
    private int w_siseva_per_id;
    private String w_siseva_per_codigo;
    private String w_siseva_per_descripcion;
    private int w_sec_id;
    private int w_curape_id;
    private boolean w_marcar = false;
    private String w_mensaje;
    private SelectItem[] cbo_radio = new SelectItem[ 0 ];
    private String alu_codigo;
    private String alu_datos;
    private int alu_id;
    private String alu_nota;
    private String alu_codigoT;
    private String alu_datosT;
    private int alu_idT;
    private String alu_notaT;
    private int alu_conta;
    private int alu_contaT;
    private List listarAlumnoNota = new ArrayList();
    private List listarAlumnoNotaTem = new ArrayList();
    private String etapaSisEva;
    private boolean verEtapa;
    private SelectItem[] etapas=new SelectItem[0];
    List<AcAlumno> alumnos;
    private List<List<String>> lstAlumnosNotas = new ArrayList<List<String>>();
    private List<Column> lstColumnsNotas = new ArrayList<Column>();
    private List<Column> lstColumnsNotasAux = new ArrayList<Column>();
    List<AcSisEvaPersonalizado> seps;

    public String getAlu_datos() {
        return alu_datos;
    }

    public void setAlu_datos( String alu_datos ) {
        this.alu_datos = alu_datos;
    }

    public String getAlu_datosT() {
        return alu_datosT;
    }

    public void setAlu_datosT( String alu_datosT ) {
        this.alu_datosT = alu_datosT;
    }

    public int getAlu_conta() {
        return alu_conta;
    }

    public void setAlu_conta( int alu_conta ) {
        this.alu_conta = alu_conta;
    }

    public int getAlu_contaT() {
        return alu_contaT;
    }

    public void setAlu_contaT( int alu_contaT ) {
        this.alu_contaT = alu_contaT;
    }

    public List getListarAlumnoNota() {
        return listarAlumnoNota;
    }

    public void setListarAlumnoNota( List listarAlumnoNota ) {
        this.listarAlumnoNota = listarAlumnoNota;
    }

    public List getListarAlumnoNotaTem() {
        return listarAlumnoNotaTem;
    }

    public void setListarAlumnoNotaTem( List listarAlumnoNotaTem ) {
        this.listarAlumnoNotaTem = listarAlumnoNotaTem;
    }

    public String getAlu_codigoT() {
        return alu_codigoT;
    }

    public void setAlu_codigoT( String alu_codigoT ) {
        this.alu_codigoT = alu_codigoT;
    }

    public int getAlu_idT() {
        return alu_idT;
    }

    public void setAlu_idT( int alu_idT ) {
        this.alu_idT = alu_idT;
    }

    public String getAlu_nota() {
        return alu_nota;
    }

    public void setAlu_nota( String alu_nota ) {
        this.alu_nota = alu_nota;
    }

    public String getAlu_notaT() {
        return alu_notaT;
    }

    public void setAlu_notaT( String alu_notaT ) {
        this.alu_notaT = alu_notaT;
    }

    public String getAlu_codigo() {
        return alu_codigo;
    }

    public void setAlu_codigo( String alu_codigo ) {
        this.alu_codigo = alu_codigo;
    }

    public int getAlu_id() {
        return alu_id;
    }

    public void setAlu_id( int alu_id ) {
        this.alu_id = alu_id;
    }

    public SelectItem[] getCbo_radio() {
        return cbo_radio;
    }

    public void setCbo_radio( SelectItem[] cbo_radio ) {
        this.cbo_radio = cbo_radio;
    }

    public String getW_mensaje() {
        return w_mensaje;
    }

    public void setW_mensaje( String w_mensaje ) {
        this.w_mensaje = w_mensaje;
    }

    public boolean isW_marcar() {
        return w_marcar;
    }

    public void setW_marcar( boolean w_marcar ) {
        this.w_marcar = w_marcar;
    }

    public int getW_curape_id() {
        return w_curape_id;
    }

    public void setW_curape_id( int w_curape_id ) {
        this.w_curape_id = w_curape_id;
    }

    public int getW_sec_id() {
        return w_sec_id;
    }

    public void setW_sec_id( int w_sec_id ) {
        this.w_sec_id = w_sec_id;
    }

    public String getW_siseva_per_codigo() {
        return w_siseva_per_codigo;
    }

    public void setW_siseva_per_codigo( String w_siseva_per_codigo ) {
        this.w_siseva_per_codigo = w_siseva_per_codigo;
    }

    public String getW_siseva_per_descripcion() {
        return w_siseva_per_descripcion;
    }

    public void setW_siseva_per_descripcion( String w_siseva_per_descripcion ) {
        this.w_siseva_per_descripcion = w_siseva_per_descripcion;
    }

    public int getW_siseva_per_id() {
        return w_siseva_per_id;
    }

    public void setW_siseva_per_id( int w_siseva_per_id ) {
        this.w_siseva_per_id = w_siseva_per_id;
    }

    public List<bNota> getW_sistema_evaluacion() {
        return w_sistema_evaluacion;
    }

    public void setW_sistema_evaluacion( List<bNota> w_sistema_evaluacion ) {
        this.w_sistema_evaluacion = w_sistema_evaluacion;
    }

    public List<ObjNodeGnral> getNodos() {
        return nodos;
    }

    public void setNodos( List<ObjNodeGnral> nodos ) {
        this.nodos = nodos;
    }

    public String getNodoSeleccionado() {
        return nodoSeleccionado;
    }

    public void setNodoSeleccionado( String nodoSeleccionado ) {
        this.nodoSeleccionado = nodoSeleccionado;
    }

    public TreeNode getArbol() {
        if ( arbol == null ) {
            cargarArbol();
        }
        return arbol;
    }

    public void setArbol( TreeNode arbol ) {
        this.arbol = arbol;
    }

    public int getId_sec() {
        return id_sec;
    }

    public void setId_sec( int id_sec ) {
        this.id_sec = id_sec;
    }

    public String getCodigo_sec() {
        return codigo_sec;
    }

    public void setCodigo_sec( String codigo_sec ) {
        this.codigo_sec = codigo_sec;
    }

    public String getNombre_sec() {
        return nombre_sec;
    }

    public void setNombre_sec( String nombre_sec ) {
        this.nombre_sec = nombre_sec;
    }

    public int getVacantes_sec() {
        return vacantes_sec;
    }

    public void setVacantes_sec( int vacantes_sec ) {
        this.vacantes_sec = vacantes_sec;
    }

    public int getId_hor() {
        return id_hor;
    }

    public void setId_hor( int id_hor ) {
        this.id_hor = id_hor;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete( String oncomplete ) {
        this.oncomplete = oncomplete;
    }

    public int getId_sec_horario() {
        return id_sec_horario;
    }

    public void setId_sec_horario( int id_sec_horario ) {
        this.id_sec_horario = id_sec_horario;
    }

    public int getCurape_id() {
        return curape_id;
    }

    public void setCurape_id( int curape_id ) {
        this.curape_id = curape_id;
    }

    public int getId_cur_ape() {
        return id_cur_ape;
    }

    public void setId_cur_ape( int id_cur_ape ) {
        this.id_cur_ape = id_cur_ape;
    }

    public String getCur_nombre() {
        return cur_nombre;
    }

    public void setCur_nombre( String cur_nombre ) {
        this.cur_nombre = cur_nombre;
    }

    public String getCur_ape_nombre() {
        return cur_ape_nombre;
    }

    public void setCur_ape_nombre( String cur_ape_nombre ) {
        this.cur_ape_nombre = cur_ape_nombre;
    }

    public int getNro_columnas() {
        return nro_columnas;
    }

    public void setNro_columnas( int nro_columnas ) {
        this.nro_columnas = nro_columnas;
    }

    public HtmlPanelGrid getHtmlPanel() {
        return htmlPanel;
    }

    public void setHtmlPanel( HtmlPanelGrid htmlPanel ) {
        this.htmlPanel = htmlPanel;
    }

    public String getEtapaSisEva() {
        return etapaSisEva;
    }

    public void setEtapaSisEva( String etapaSisEva ) {
        this.etapaSisEva = etapaSisEva;
    }

    public boolean isVerEtapa() {
        return verEtapa;
    }

    public void setVerEtapa( boolean verEtapa ) {
        this.verEtapa = verEtapa;
    }

    public List<Column> getLstColumnsNotas() {
        return lstColumnsNotas;
    }

    public void setLstColumnsNotas( List<Column> lstColumnsNotas ) {
        this.lstColumnsNotas = lstColumnsNotas;
    }

    public List<Column> getLstColumnsNotasAux() {
        return lstColumnsNotasAux;
    }

    public void setLstColumnsNotasAux( List<Column> lstColumnsNotasAux ) {
        this.lstColumnsNotasAux = lstColumnsNotasAux;
    }

    public void Limpiar() {
        etapaSisEva = "";

    }

    public SelectItem[] getEtapas() {
        try {
            //System.out.println( id_cur_ape );
            if ( id_cur_ape != 0 ) {
                HSCatalogoDAO daoCatalogo = (HSCatalogoDAO)ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );
                List lista = daoCatalogo.seleccionarCatalogo( "092" );
                etapas = new SelectItem[ lista.size() + 1 ];
                etapas[0] = new SelectItem( "000000", "[-Selecione-]" );
                for ( int i = 0; i < etapas.length - 1; i++ ) {
                    etapas[i + 1] = new SelectItem( ( (TbCatalogo)lista.get( i ) ).getCatCodigoGrupo() + ( (TbCatalogo)lista.get( i ) ).getCatCodigoElemento(), ( (TbCatalogo)lista.get( i ) ).getCatDescripcionElemento() );
                }
                this.setVerEtapa( true );
            } else {
                etapas = new SelectItem[ 1 ];
                etapas[0] = new SelectItem( "000000", "[-Selecione-]" );
                this.setVerEtapa( false );
            }


        } catch ( Exception e ) {
            e.printStackTrace();
        }
        lstColumnsNotas = new ArrayList<Column>();
        lstColumnsNotasAux = new ArrayList<Column>();
        return etapas;
    }

    public void setEtapas( SelectItem[] etapas ) {
        this.etapas = etapas;
    }

    public void cargarArbol() {
        try {
            String estructura = ArmarEstructura();
            StringBuffer buffer = new StringBuffer( estructura );
            ByteArrayInputStream estructura_arbol = new ByteArrayInputStream( buffer.toString().getBytes( "ISO-8859-1" ) );
            Properties properties = new Properties();
            properties.load( estructura_arbol );
            arbol = new TreeNodeImpl();
            agregarNodos( null, arbol, properties );
        } catch ( IOException ioe ) {
//            System.out.println("No se pudo cargar el arbol");
        } catch ( Exception e ) {
        }
    }

    private void agregarNodos( String path, TreeNode node, Properties properties ) {
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

    public String ArmarEstructura() throws Exception {
        int b = 0;
        int m = 0;
//        O = new Object[10][10][10][10][20][2];
//        P = new Object[20][20][20];
        String estructura = "";
        String estructura_facultades = "";
        String estructura_especialidades = "";
        String estructura_curape = "";
        String estructura_semestres = "";
        HSFacultadDAO dao = (HSFacultadDAO)ServiceFinder.findBean( "SpringHibernateDaoFacultad" );
        List lFacultad = dao.seleccionarFacultad( "", "" );

        List<ObjNodeGnral> lNodo = new ArrayList<ObjNodeGnral>();
        if ( lFacultad.size() != 0 ) {
            for ( int i = 0; i < lFacultad.size(); i++ ) {
                estructura_facultades += ( i + 1 ) + "=" + ( (AcFacultad)lFacultad.get( i ) ).getFacNombre() + "\n";
                Set<AcEspecialidad> esp = new LinkedHashSet<AcEspecialidad>();
                esp = ( (AcFacultad)lFacultad.get( i ) ).getAcEspecialidads();
                List l = Collections.synchronizedList( new LinkedList( esp ) );
                if ( l.size() != 0 ) {
                    for ( int j = 0; j < l.size(); j++ ) {
                        estructura_especialidades += ( i + 1 ) + "." + ( j + 1 ) + "=" + ( (AcEspecialidad)l.get( j ) ).getEspNombre() + "\n";
                        HSSemestreDAO dao_s = (HSSemestreDAO)ServiceFinder.findBean( "SpringHibernateDaoSemestre" );
                        List lista_semestres = dao_s.seleccionarSemestreVigente();
                        if ( lista_semestres.size() != 0 ) {
                            for ( int w = 0; w < lista_semestres.size(); w++ ) {
                                estructura_semestres += ( i + 1 ) + "." + ( j + 1 ) + "." + ( w + 1 ) + "=" + ( (AcSemestre)lista_semestres.get( w ) ).getSemNombre() + "\n";
                                HSAperturaCursosDAO dao3 = (HSAperturaCursosDAO)ServiceFinder.findBean( "SpringHibernateDaoCursoAperturado" );
                                int f = ( (AcEspecialidad)l.get( j ) ).getId();
                                List lis = dao3.seleccionarAperturaporEspecialidad( f, ( (AcSemestre)lista_semestres.get( w ) ).getId() );
                                if ( lis.size() != 0 ) {
                                    List<Integer> ultimos_digitos = new ArrayList<Integer>();
                                    ultimos_digitos.add( -1 );
                                    int contado = 0;
                                    for ( int k = 0; k < lis.size(); k++ ) {
                                        m = contado;
                                        int a = Integer.parseInt( ( (AcCursoAperturado)lis.get( k ) ).getPlancur().getPlancurCiclo().toString().substring( 5, 6 ) );
                                        HSCatalogoDAO cat = (HSCatalogoDAO)ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );
                                        int y = verificarIndice( a, ultimos_digitos );
                                        estructura_curape += ( i + 1 ) + "." + ( j + 1 ) + "." + ( w + 1 ) + "." + y + "=" + cat.seleccionarDescripcion( ( (AcCursoAperturado)lis.get( k ) ).getPlancur().getPlancurCiclo() ) + "\n";
                                        if ( b != y ) {
                                            m = 0;
                                            contado = 0;
                                        }
                                        estructura_curape += ( i + 1 ) + "." + ( j + 1 ) + "." + ( w + 1 ) + "." + y + "." + ( m + 1 ) + "=" + ( (AcCursoAperturado)lis.get( k ) ).getPlancur().getCur().getCurNombre() + "\n";
                                        b = y;
//                                        O[i + 1][j + 1][w + 1][y][m + 1][0] = ((AcCursoAperturado) lis.get(k)).getId();
//                                        O[i + 1][j + 1][w + 1][y][m + 1][1] = ((AcCursoAperturado) lis.get(k)).getPlancur().getCur().getCurNombre();
//                                        P[i + 1][j + 1][w + 1] = ((AcSemestre) lista_semestres.get(w)).getId();

                                        lNodo.add( new ObjNodeGnral( ( i + 1 ) + ":" + ( j + 1 ) + ":" + ( w + 1 ) + ":" + y + ":" + ( m + 1 ),
                                                ( (AcCursoAperturado)lis.get( k ) ).getId(),
                                                ( (AcCursoAperturado)lis.get( k ) ).getPlancur().getCur().getCurNombre(),
                                                ( (AcSemestre)lista_semestres.get( w ) ).getId() ) );
                                        contado++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        this.setNodos( new ArrayList<ObjNodeGnral>( lNodo ) );
        estructura = estructura_facultades + estructura_especialidades + estructura_semestres + estructura_curape;
//        System.out.println(estructura);
        this.setListaNotas( null );
        MostrarTabla( 0, 0 );
        return estructura;
    }

    public int verificarIndice( int ultimo_digito, List<Integer> ultimos_digitos ) {
        int estado = 0;
        int grupo = 0;
        for ( int i = 0; i < ultimos_digitos.size(); i++ ) {
            if ( ultimos_digitos.get( i ) == ultimo_digito ) {
                estado = 1;
                grupo = i;
                break;
            }
        }
        if ( estado == 0 ) {
            ultimos_digitos.add( ultimo_digito );
            grupo = ultimos_digitos.size() - 1;
        }
        return grupo;
    }

    public void llenar( String ciclo, List grupo, List resultado ) {
        grupo.add( ciclo );
        AcPlanCurso plancur = new AcPlanCurso();
        plancur.setPlancurCiclo( ciclo );
        resultado.add( plancur );
    }

    private int buscarNodo( String id ) {
        for ( int i = 0; i < nodos.size(); i++ ) {
            ObjNodeGnral nodo = nodos.get( i );
            if ( nodo.getId().equalsIgnoreCase( id ) ) {
                return i;
            }
        }
        return -1;
    }

    public void Seleccion( NodeSelectedEvent event ) {
        try {
            UITree tree = (UITree)event.getComponent();
            String a = tree.getRowKey().toString();

            int length = a.length();
            if ( length >= 9 ) {
                int pos = buscarNodo( a );

                ObjNodeGnral nodo = nodos.get( pos );

                int id_curape = nodo.getCurape_id();
                cur_ape_nombre = nodo.getCur_nom();
                int id_semape = nodo.getSem_id();
                MostrarTabla( id_curape, id_semape );
            }
        } catch ( Exception e ) {
        }
    }

    public void MostrarTabla( int id_curape, int id_semape ) {
        HSSeccionDAO dao = (HSSeccionDAO)ServiceFinder.findBean( "HibernateSpringDaoSeccion" );
        List li = dao.seleccionarSecciones( id_curape );
//        System.out.println("CURAPE_ID:" + id_curape + " - " + li.size());
        List horarios = new ArrayList();
        List lst;
        for ( int i = 0; i < li.size(); i++ ) {
//            System.out.println("UNO ");
            bNota horario = new bNota();
//            System.out.println("DOS");
            lst = new ArrayList();
            horario.setId_sec( ( (AcSeccion)li.get( i ) ).getId() );
//            System.out.println("TRES ");
            horario.setCodigo_sec( ( (AcSeccion)li.get( i ) ).getSecCodigo() );
//            System.out.println("CUATRO ");
            horario.setNombre_sec( ( (AcSeccion)li.get( i ) ).getSecNombre() );
//            System.out.println("CINCO ");
            HSMatriculaDAO matricula = (HSMatriculaDAO)ServiceFinder.findBean( "SpringHibernateDaoMatricula" );
            //HSMatriculaDAO matricula2=(HSMatriculaDAO)ServiceFinder.findBean("SpringHibernateDaoMatricula");
//            System.out.println("SEIS ");
            lst = matricula.seleccionarMatriculas( ( (AcSeccion)li.get( i ) ).getId() );
//            System.out.println("SIETE ");
            /*
             * Estado de Bloque Web
             */
            /*TbBloqueoWeb tbw = (TbBloqueoWeb) matricula.GetBloqueoWeb(((AcSeccion) li.get(i)).getId()).get(0);
             System.out.println("OCHO ");
             if (tbw != null) {
             //           System.out.println("BLOQUEO WEB");
             if (tbw.getBloweb_estado().equals("038001")) {
             horario.setBloqueo(false);
             } else {
             horario.setBloqueo(true);
             }
             //         System.out.println("BLOQUEO WEB2");
             //   System.out.println("ENTROOO NO NULO 2" + horario.isBloqueo());

             }*/
            //System.out.println("NUEVE ");
            horario.setVacantes_sec( lst.size() );
            horario.setCurape_id( ( (AcCursoAperturado)( (AcSeccion)li.get( i ) ).getCurape() ).getId() );
            horarios.add( horario );
        }
        this.setListaNotas( horarios );
    }

    public List getListaNotas() {
        return listaNotas;
    }

    public void setListaNotas( List listaNotas ) {
        this.listaNotas = listaNotas;
    }

    public List<AcAlumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos( List<AcAlumno> alumnos ) {
        this.alumnos = alumnos;
    }

    public List<AcSisEvaPersonalizado> getSeps() {
        return seps;
    }

    public void setSeps( List<AcSisEvaPersonalizado> seps ) {
        this.seps = seps;
    }

    public void listarAlumnos( ActionEvent event ) throws Exception {
        List<Integer> lstSisEvaPers;
        HSAlumnoNotaDAO daoAlumno = (HSAlumnoNotaDAO)ServiceFinder.findBean( "SpringHibernateDaoAlumnoNota" );
        lstSisEvaPers = daoAlumno.listarSisEvaPerPorEtapa( etapaSisEva, id_sec );
        this.setAlumnos( daoAlumno.listarAlumnosPorCursoSisEvaPer( id_sec, lstSisEvaPers, etapaSisEva ) );
    }

    public void listarColumnas( ActionEvent event ) throws Exception {
        HSAlumnoNotaDAO daoAlumno;

        Column c;
        lstColumnsNotas = new ArrayList<Column>();

        c = new Column( "Nro." );
        lstColumnsNotas.add( c );
        c = new Column( "Código" );
        lstColumnsNotas.add( c );
        c = new Column( "Apellidos y Nombres" );
        lstColumnsNotas.add( c );
        try {
            try {
                System.out.println( "entro.ppp" );
                daoAlumno = (HSAlumnoNotaDAO)ServiceFinder.findBean( "SpringHibernateDaoAlumnoNota" );
                this.setSeps( daoAlumno.listarSisEvaPorSeccion( w_sec_id, this.getEtapaSisEva() ) );
            } catch ( Exception ex ) {
                seps = new ArrayList<AcSisEvaPersonalizado>();
            }

            for ( AcSisEvaPersonalizado sep : this.getSeps() ) {
                c = new Column( sep.getSisevaCodigo() );
                //lstDetalle.add( sep );
                lstColumnsNotas.add( c );
            }

        } catch ( Exception ex ) {
            ex.printStackTrace();
        }

        int iSizeAlum;
        int iSizeNotas;
        double dProm;
        AcAlumno alu;
        List<String> lstAlumNotAux;
        lstAlumnosNotas = new ArrayList<List<String>>();
        try {
            if ( this.getLstAlumnosNotas() != null ) {
                listarAlumnosPorSeccion();
                //lstAlumnosNotas = new ArrayList<List<String>>();
                iSizeAlum = this.getAlumnos().size();
                if ( iSizeAlum > 0 ) {
                    for ( int i = 0; i < iSizeAlum; i++ ) {
                        lstAlumNotAux = new ArrayList<String>();
                        alu = this.getAlumnos().get( i );
                        lstAlumNotAux.add( String.valueOf( i + 1 ) );
                        lstAlumNotAux.add( alu.getAluCodigo() );
                        lstAlumNotAux.add( alu.getV_nombreCompleto() );
                        iSizeNotas = alu.getV_lstNotas().size();
                        for ( int j = 0; j < iSizeNotas; j++ ) {
                            lstAlumNotAux.add( alu.getV_lstNotas().get( j ) );
                        }
                        //alumnos.add( alu );
                        lstAlumnosNotas.add( lstAlumNotAux );

                        //alumnos.add( alu );

                    }
                }
                lstAlumnosNotas2=lstAlumnosNotas;
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        this.setLstColumnsNotasAux( lstColumnsNotas );
    }

    public void listarAlumnosPorSeccion() throws Exception {
        HSAlumnoNotaDAO daoAlumno = (HSAlumnoNotaDAO)ServiceFinder.findBean( "SpringHibernateDaoAlumnoNota" );
        this.setAlumnos( daoAlumno.listarAlumnosPorCursoSisEvaPer( w_sec_id, null, etapaSisEva ) );
    }

    public List<List<String>> getLstAlumnosNotas() {
        return lstAlumnosNotas;
    }

    public void setLstAlumnosNotas( List<List<String>> lstAlumnosNotas ) {
        this.lstAlumnosNotas = lstAlumnosNotas;
    }
    
    private List<List<String>> lstAlumnosNotas2 = new ArrayList<List<String>>();
    public List<List<String>> getLstAlumnosNotas2() {
//        int iSizeAlum;
//        int iSizeNotas;
//        AcAlumno alu;
//        List<String> lstAlumNotAux;
//        try {
//            if ( lstAlumnosNotas2 != null ) {
//                listarAlumnosPorSeccion();
//                lstAlumnosNotas2 = new ArrayList<List<String>>();
//                iSizeAlum = alumnos.size();
//                if ( iSizeAlum > 0 ) {
//                    for ( int i = 0; i < iSizeAlum; i++ ) {
//                        lstAlumNotAux = new ArrayList<String>();
//                        alu = alumnos.get( i );
//                        lstAlumNotAux.add( String.valueOf( i + 1 ) );
//                        lstAlumNotAux.add( alu.getAluCodigo() );
//                        lstAlumNotAux.add( alu.getV_nombreCompleto() );
//                        iSizeNotas = alu.getV_lstNotas().size();
//                        for ( int j = 0; j < iSizeNotas; j++ ) {
//                            lstAlumNotAux.add( alu.getV_lstNotas().get( j ) );
//                        }
//
//                        lstAlumnosNotas2.add( lstAlumNotAux );
//
//                    }
//                }
//            }
//        } catch ( Exception ex ) {
//            ex.printStackTrace();
//        }
        return lstAlumnosNotas2;
    }
    
    public void setLstAlumnosNotas2( List<List<String>> lstAlumnosNotas2 ) {
        this.lstAlumnosNotas2 = lstAlumnosNotas2;
    }
    
    public void AsignarNotasOpc( ActionEvent event ) {
        this.setAlumnos( new ArrayList<AcAlumno>() );
        this.setEtapas( etapas );
        this.setEtapaSisEva( "000000");
        this.setW_siseva_per_id(0);
        lstColumnsNotas = new ArrayList<Column>();
        lstColumnsNotasAux = new ArrayList<Column>();
        lstAlumnosNotas= new ArrayList<List<String>>();
        id_sec_horario = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "id_seccion" ) ).getValue().toString() );
        id_cur_ape = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "id_cur_ape" ) ).getValue().toString() );
        int secId = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "id_seccion" ) ).getValue().toString() );
        this.setW_sec_id( secId );
    }

    public void AsignarNotas( ActionEvent event ) {
        id_sec_horario = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "id_seccion" ) ).getValue().toString() );
        id_cur_ape = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "id_cur_ape" ) ).getValue().toString() );
        ArmaCabeceras();
        ArmarTablaNotas( lista, list, id_sec_horario );
    }

    public void ArmaCabeceras() {
        HSAperturaCursosDAO dao = (HSAperturaCursosDAO)ServiceFinder.findBean( "SpringHibernateDaoCursoAperturado" );
        AcCursoAperturado curape = (AcCursoAperturado)dao.seleccionarUnCursosAperturado( id_cur_ape ).get( 0 );
        Set<AcSisEvaPersonalizado> sisevaper = curape.getAcSisEvaPersonalizados();
        list = Collections.synchronizedList( new LinkedList( sisevaper ) );
        HSMatriculaDAO dao1 = (HSMatriculaDAO)ServiceFinder.findBean( "SpringHibernateDaoMatricula" );
        lista = new ArrayList();
        lista = dao1.seleccionarMatriculas( id_sec_horario );
    }

    public void ArmarTablaNotas( List rowList, List columnList, int id_sec_horario ) {
        Hidden = new ArrayList();
        List childrenList = getHtmlPanel().getChildren();
        childrenList.clear();
        HtmlOutputText output;
        HtmlInputText input;
        HtmlInputHidden hidden;

        Application application = FacesContext.getCurrentInstance().getApplication();
        output = (HtmlOutputText)application.createComponent( HtmlOutputText.COMPONENT_TYPE );
        input = (HtmlInputText)application.createComponent( HtmlInputText.COMPONENT_TYPE );
        hidden = (HtmlInputHidden)application.createComponent( HtmlInputHidden.COMPONENT_TYPE );
        output.setValue( "APELLIDOS Y NOMBRES" );
        output.setStyle( "font-weight: bold;" );
        childrenList.add( output );
        nro_columnas = columnList.size() + 1;

        for ( int i = 0; i < columnList.size(); i++ ) {
            output = (HtmlOutputText)application.createComponent( HtmlOutputText.COMPONENT_TYPE );
            output.setValue( ( (AcSisEvaPersonalizado)columnList.get( i ) ).getSisevaCodigo() );
            output.setStyle( "font-weight: bold;" );
            childrenList.add( output );
        }

        HtmlSeparator hse;
        for ( int i = 0; i < columnList.size() + 1; i++ ) {
            hse = new HtmlSeparator();
            hse.setAlign( "center" );
            hse.setWidth( "100%" );
            hse.setHeight( "3px" );
            childrenList.add( hse );
        }

        HtmlSpacer hsp;
        for ( int i = 0; i < columnList.size() + 1; i++ ) {
            hsp = new HtmlSpacer();
            hsp.setWidth( "100%" );
            hsp.setHeight( "5px" );
            childrenList.add( hsp );
        }

        HSNotaDAO dao = (HSNotaDAO)ServiceFinder.findBean( "SpringHibernateDaoNota" );
        List l = dao.seleccionarNotas( id_sec_horario );
        String valor_nota = "0";
        int valor_id = 0;
        for ( int i = 0; i < rowList.size(); i++ ) {
            output = (HtmlOutputText)application.createComponent( HtmlOutputText.COMPONENT_TYPE );
            output.setValue( ( i + 1 ) + ".- " + ( (AcAlumno)( (AcMatricula)rowList.get( i ) ).getAlu() ).getAluAppaterno() + " " + ( (AcAlumno)( (AcMatricula)rowList.get( i ) ).getAlu() ).getAluApmaterno() + " " + ( (AcAlumno)( (AcMatricula)rowList.get( i ) ).getAlu() ).getAluNombres() );
            childrenList.add( output );
            for ( int j = 0; j < columnList.size(); j++ ) {
                input = (HtmlInputText)application.createComponent( HtmlInputText.COMPONENT_TYPE );
                hidden = (HtmlInputHidden)application.createComponent( HtmlInputHidden.COMPONENT_TYPE );
                valor_nota = "0";
                valor_id = 0;
                if ( l.size() != 0 ) {
                    for ( int k = 0; k < l.size(); k++ ) {
                        if ( ( (AcAlumno)( (AcNota)l.get( k ) ).getAlu() ).getId().toString().equals( ( (AcAlumno)( (AcMatricula)rowList.get( i ) ).getAlu() ).getId().toString() ) && ( (AcSisEvaPersonalizado)( (AcNota)l.get( k ) ).getSisevaPer() ).getId().toString().equals( ( (AcSisEvaPersonalizado)columnList.get( j ) ).getId().toString() ) ) {
                            valor_nota = "" + ( (AcNota)l.get( k ) ).getNotNota();
                            valor_id = ( (AcNota)l.get( k ) ).getId();
                        }
                    }
                }
                hidden.setId( "h" + ( (AcAlumno)( (AcMatricula)rowList.get( i ) ).getAlu() ).getId() + "-" + ( (AcSisEvaPersonalizado)columnList.get( j ) ).getId() );
                hidden.setValue( valor_id );
                if ( j % 2 == 0 ) {
                    input.setStyle( "width:35px;background-color: #ffffff" );
                    input.setId( "id" + ( (AcAlumno)( (AcMatricula)rowList.get( i ) ).getAlu() ).getId() + "-" + ( (AcSisEvaPersonalizado)columnList.get( j ) ).getId() );
                    input.setValue( valor_nota );
                } else {
                    input.setStyle( "width:35px;background-color: #bbbbbb" );
                    input.setId( "id" + ( (AcAlumno)( (AcMatricula)rowList.get( i ) ).getAlu() ).getId() + "-" + ( (AcSisEvaPersonalizado)columnList.get( j ) ).getId() );
                    input.setValue( valor_nota );
                }
                float nota = Float.parseFloat( valor_nota );
                if ( nota < 10.5 ) {
                    input.setStyle( "color: #cc0000;" + input.getStyle() );
                } else {
                    input.setStyle( "color: #000099;" + input.getStyle() );
                }
                childrenList.add( input );
                Hidden.add( hidden );
            }
        }
    }

    public void Insertar( ActionEvent event ) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession(false);
        bUsuario usu = (bUsuario) session1.getAttribute("usuario");                  
            int idusu=usu.getId_usu();
        List<AcNota> notas = new ArrayList<AcNota>();
        for ( int i = 0; i < lista.size(); i++ ) {
            for ( int j = 0; j < list.size(); j++ ) {
                String a = "id" + ( (AcAlumno)( (AcMatricula)lista.get( i ) ).getAlu() ).getId() + "-" + ( (AcSisEvaPersonalizado)list.get( j ) ).getId();
                String h = "h" + ( (AcAlumno)( (AcMatricula)lista.get( i ) ).getAlu() ).getId() + "-" + ( (AcSisEvaPersonalizado)list.get( j ) ).getId();
                String valor_nota = ( (UIInput)event.getComponent().findComponent( a ) ).getValue().toString();
                if ( valor_nota.trim().length() <= 0 ) {
                    valor_nota = "0";
                }
                int id_nota = 0;
                for ( int k = 0; k < Hidden.size(); k++ ) {
                    if ( ( (UIInput)Hidden.get( k ) ).getId().toString().equals( h ) ) {
                        id_nota = Integer.parseInt( ( (UIInput)Hidden.get( k ) ).getValue().toString() );
                    }
                }
                AcNota nota = new AcNota();
                if ( id_nota != 0 ) {
                    nota.setId( id_nota );
                }
                nota.setNotActivo( "1" );
                nota.setNotNota( BigDecimal.valueOf( Double.parseDouble( valor_nota ) ) );
                AcAlumno alu = new AcAlumno();
                alu.setId( ( (AcAlumno)( (AcMatricula)lista.get( i ) ).getAlu() ).getId() );
                nota.setAlu( alu );
                AcSisEvaPersonalizado sisevaper = new AcSisEvaPersonalizado();
                sisevaper.setId( ( (AcSisEvaPersonalizado)list.get( j ) ).getId() );
                nota.setSisevaPer( sisevaper );
                nota.setNotCreacion( new Date() );
                nota.setSecId( this.getId_sec_horario() );
                notas.add( nota );
            }
        }
        HSNotaDAO dao1 = (HSNotaDAO)ServiceFinder.findBean( "SpringHibernateDaoNota" );
        dao1.insertarNotas( notas );
       // dao1.insertarNotasTemporalUch(notas,idusu);
    }
    
     public String grabarNotas(ActionEvent event) throws Exception {
//        int iNumAlum;
//        int iNumAlumAprob;
//        int iNumAlumDesprob;
        HSAlumnoNotaDAO prome2 = (HSAlumnoNotaDAO)ServiceFinder.findBean( "SpringHibernateDaoAlumnoNota" );  
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession(false);
        bUsuario usu = (bUsuario) session1.getAttribute("usuario");
        int doc_id=usu.getId_usu();


        int b = prome2.grabarNotasNuevo( this.getAlumnos(), this.getLstAlumnosNotas(), w_sec_id, this.getSeps(), doc_id );
        if ( b > 0 ) {            
            int c = prome2.recalcularPromedio( this.getAlumnos(), w_sec_id, this.getSeps(), doc_id );
           
            prome2.generarPromedioN( w_sec_id );
            if ( this.getEtapaSisEva().equals( "092002" ) ) {
                prome2.calcularPromedioConsutitutorio( w_sec_id );
            }
            return "grabarNotasOK";
        } else {
            return "grabarNotasNOOK";
        }
    }

    public void PrepararPdf( ActionEvent event ) {
        nombre_sec1 = ( (UIParameter)event.getComponent().findComponent( "pdf" ) ).getValue().toString();
    }

    public void crearPdf( OutputStream out, Object data ) throws IOException {
        HSNotaDAO dao = (HSNotaDAO)ServiceFinder.findBean( "SpringHibernateDaoNota" );
        InputStream buffered = dao.imprimirTodo( (String)data );
        int size = buffered.available();
        byte[] pdf = new byte[ size ];
        buffered.read( pdf );
        buffered.close();
        out.write( pdf );
    }

    public String getNombre_sec1() {
        return nombre_sec1;
    }

    public void setNombre_sec1( String nombre_sec1 ) {
        this.nombre_sec1 = nombre_sec1;
    }

    public void AsignarNotasActas( ActionEvent event ) {
        id_sec_horario = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "id_seccion" ) ).getValue().toString() );
        id_cur_ape = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "id_cur_ape" ) ).getValue().toString() );

        AcCursoAperturado curape = ArmaCabecerasActa();

        ArmarTablaNotasActas( curape, lista, list, id_sec_horario );
    }

    public AcCursoAperturado ArmaCabecerasActa() {
        HSAperturaCursosDAO dao = (HSAperturaCursosDAO)ServiceFinder.findBean( "SpringHibernateDaoCursoAperturado" );
        AcCursoAperturado curape = (AcCursoAperturado)dao.seleccionarUnCursosAperturado( id_cur_ape ).get( 0 );
        Set<AcSisEvaPersonalizado> sisevaper = curape.getAcSisEvaPersonalizados();
        list = Collections.synchronizedList( new LinkedList( sisevaper ) );
        System.out.println( "dim: " + sisevaper.size() );

        ///////////////////////////////////agrega promedios
        HSMatriculaDAO dao1 = (HSMatriculaDAO)ServiceFinder.findBean( "SpringHibernateDaoMatricula" );
        lista = new ArrayList();
        lista = dao1.seleccionarMatriculas( id_sec_horario );
        return curape;
    }

    public void ArmarTablaNotasActas( AcCursoAperturado curape, List rowList, List columnList, int id_sec_horario ) {
        Hidden = new ArrayList();
        List childrenList = getHtmlPanelActa().getChildren();
        childrenList.clear();
        HtmlOutputText output;
        HtmlInputHidden hidden;
        Application application = FacesContext.getCurrentInstance().getApplication();
        output = (HtmlOutputText)application.createComponent( HtmlOutputText.COMPONENT_TYPE );
        hidden = (HtmlInputHidden)application.createComponent( HtmlInputHidden.COMPONENT_TYPE );
        output.setValue( "APELLIDOS Y NOMBRES" );
        childrenList.add( output );

        for ( int i = 0; i < columnList.size(); i++ ) {
            output = (HtmlOutputText)application.createComponent( HtmlOutputText.COMPONENT_TYPE );
            output.setValue( ( (AcSisEvaPersonalizado)columnList.get( i ) ).getSisevaCodigo() );
            childrenList.add( output );
        }


        ///////////////////////////////////agrega promedios
        HSSistemaEvaluacionDAO dao0 = (HSSistemaEvaluacionDAO)ServiceFinder.findBean( "SpringHibernateDaoSistemaEvaluacion" );
        List li0 = dao0.seleccionarSistemaEvaluacion( curape.getSisevaId() );

        Set<AcSisEvaDetalle> sisevadet = ( (AcSisEvaluacion)li0.get( 0 ) ).getAcSisEvaDetalles();
        List li = Collections.synchronizedList( new LinkedList( sisevadet ) );
        for ( int m = 0; m < li.size(); m++ ) {
            output = (HtmlOutputText)application.createComponent( HtmlOutputText.COMPONENT_TYPE );
            output.setValue( ( (AcSisEvaDetalle)li.get( m ) ).getSisevaDetalleCodigo() );
            childrenList.add( output );
        }

        output = (HtmlOutputText)application.createComponent( HtmlOutputText.COMPONENT_TYPE );
        output.setValue( ( (AcSisEvaluacion)li0.get( 0 ) ).getSisevaCodigo() );
        childrenList.add( output );
        //////////////////////////////////agrega promedios

        nro_columnas = columnList.size() + li0.size() + li.size() + 1;

        HSNotaDAO dao = (HSNotaDAO)ServiceFinder.findBean( "SpringHibernateDaoNota" );
        List l = dao.seleccionarNotas( id_sec_horario );
        String valor_nota = "0";
        //int valor_id=0;
        double promedio = 0;
        for ( int i = 0; i < rowList.size(); i++ ) {
            promedio = 0;
            output = (HtmlOutputText)application.createComponent( HtmlOutputText.COMPONENT_TYPE );
            output.setValue( ( i + 1 ) + ".- " + ( (AcAlumno)( (AcMatricula)rowList.get( i ) ).getAlu() ).getAluAppaterno() + " " + ( (AcAlumno)( (AcMatricula)rowList.get( i ) ).getAlu() ).getAluApmaterno() + " " + ( (AcAlumno)( (AcMatricula)rowList.get( i ) ).getAlu() ).getAluNombres() );
            childrenList.add( output );
            int indice = -1;
            float Y[] = new float[ li.size() ];
            float Z[] = new float[ 20 ];
            float Z1[] = new float[ 10 ];
            for ( int j = 0; j < columnList.size(); j++ ) {
                indice = -1;
                output = (HtmlOutputText)application.createComponent( HtmlOutputText.COMPONENT_TYPE );
                hidden = (HtmlInputHidden)application.createComponent( HtmlInputHidden.COMPONENT_TYPE );
                valor_nota = "0";
                //valor_id=0;
                if ( l.size() != 0 ) {
                    for ( int k = 0; k < l.size(); k++ ) {
                        if ( ( (AcAlumno)( (AcNota)l.get( k ) ).getAlu() ).getId().toString().equals( ( (AcAlumno)( (AcMatricula)rowList.get( i ) ).getAlu() ).getId().toString() ) && ( (AcSisEvaPersonalizado)( (AcNota)l.get( k ) ).getSisevaPer() ).getId().toString().equals( ( (AcSisEvaPersonalizado)columnList.get( j ) ).getId().toString() ) ) {
                            valor_nota = "" + ( (AcNota)l.get( k ) ).getNotNota();
                            indice = k;
                        }
                    }
                }
                hidden.setId( "hh" + ( (AcAlumno)( (AcMatricula)rowList.get( i ) ).getAlu() ).getId() + "-" + ( (AcSisEvaPersonalizado)columnList.get( j ) ).getId() );
                hidden.setValue( valor_nota );
                if ( j % 2 == 0 ) {
                    output.setId( "id" + ( (AcAlumno)( (AcMatricula)rowList.get( i ) ).getAlu() ).getId() + "-" + ( (AcSisEvaPersonalizado)columnList.get( j ) ).getId() );
                    if ( Integer.parseInt( valor_nota ) < 11 ) {
                        output.setStyle( "width:25px;color: red;background-color: #ffffff" );
                    } else {
                        output.setStyle( "width:25px;color: blue;background-color: #ffffff" );
                    }
                    output.setValue( valor_nota );
                } else {
                    output.setId( "id" + ( (AcAlumno)( (AcMatricula)rowList.get( i ) ).getAlu() ).getId() + "-" + ( (AcSisEvaPersonalizado)columnList.get( j ) ).getId() );
                    if ( Integer.parseInt( valor_nota ) < 11 ) {
                        output.setStyle( "width:25px;color: red;background-color: #ffffff" );
                    } else {
                        output.setStyle( "width:25px;color: blue;background-color: #ffffff" );
                    }
                    output.setValue( valor_nota );
                }
                childrenList.add( output );
                Hidden.add( hidden );


                for ( int m = 0; m < li.size(); m++ ) {
                    if ( indice >= 0 ) {
                        if ( ( (AcNota)l.get( indice ) ).getSisevaPer().getSisevaDetalle().getId().toString().equals( ( ( (AcSisEvaDetalle)li.get( m ) ).getId() ).toString() ) ) {
                            Y[m] = Y[m] + ( Float.parseFloat( ( (AcNota)l.get( indice ) ).getNotNota().toString() ) * Float.parseFloat( ( (AcNota)l.get( indice ) ).getSisevaPer().getSisevaPerPeso() ) );

                        }
                    }
                }



            }
            /////////promedios

            for ( int m = 0; m < li.size(); m++ ) {
                for ( int u = 0; u < columnList.size(); u++ ) {
                    if ( ( ( (AcSisEvaDetalle)li.get( m ) ).getId().toString() ).equals( ( ( (AcSisEvaPersonalizado)columnList.get( u ) ).getSisevaDetalle().getId() ).toString() ) ) {
                        Z[m] = Z[m] + Float.parseFloat( ( (AcSisEvaPersonalizado)columnList.get( u ) ).getSisevaPerPeso() );
                        Z1[m] = ( (AcSisEvaPersonalizado)columnList.get( u ) ).getSisevaDetalle().getSisevaDetallePeso();
                    }
                }
            }

            int numero_decimales = 1;
            double promedio_p = 0;
            AcActaDetalle det = new AcActaDetalle();
            for ( int m = 0; m < li.size(); m++ ) {
                promedio_p = ( Math.round( ( Y[m] / Z[m] ) * Math.pow( 10, numero_decimales ) ) ) / Math.pow( 10, numero_decimales );
                output = (HtmlOutputText)application.createComponent( HtmlOutputText.COMPONENT_TYPE );
                det.setActdetNota( Float.parseFloat( "" + promedio_p ) );
                det.setActdetTipoNota( "034001" );
                det.setActdetActivo( "1" );
                det.setAluId( ( (AcAlumno)( (AcMatricula)rowList.get( i ) ).getAlu() ).getId() );
                output.setValue( promedio_p );
                childrenList.add( output );
                promedio = promedio + ( ( Math.round( ( Y[m] / Z[m] ) * Math.pow( 10, numero_decimales ) ) ) / Math.pow( 10, numero_decimales ) ) * Z1[m];
            }
            float suma_pesos_siseva_detalle = 0;
            for ( int p = 0; p < Z1.length; p++ ) {
                suma_pesos_siseva_detalle = suma_pesos_siseva_detalle + Z1[p];
            }
            output = (HtmlOutputText)application.createComponent( HtmlOutputText.COMPONENT_TYPE );
            //long a=Math.round(promedio/((AcSisEvaluacion)li0.get(0)).getAcSisEvaDetalles().size());
            long a = Math.round( promedio / suma_pesos_siseva_detalle );
            if ( a < 11 ) {
                output.setStyle( "width:25px;color: red;background-color: #ffffff" );
            } else {
                output.setStyle( "width:25px;color: blue;background-color: #ffffff" );
            }
            output.setValue( a );
            childrenList.add( output );

        }
    }

    public void InsertarActa( ActionEvent event ) {
        List<AcNota> notas = new ArrayList<AcNota>();
        AcActa acta = new AcActa();
        AcActaDetalle detalle = new AcActaDetalle();
        acta.setActCodigo( this.getCodigo_acta() );
        acta.setActNombre( this.getNombre_acta() );
        acta.setActCreacion( new Date() );
        acta.setActNumero( this.getNumero_acta() );
        acta.setActActivo( "1" );
        AcSeccion sec = new AcSeccion();
        sec.setId( this.getId_sec_horario() );
        acta.setSec( sec );
        for ( int i = 0; i < lista.size(); i++ ) {
            for ( int j = 0; j < list.size(); j++ ) {
                String h = "hh" + ( (AcAlumno)( (AcMatricula)lista.get( i ) ).getAlu() ).getId() + "-" + ( (AcSisEvaPersonalizado)list.get( j ) ).getId();
                String valor_nota = ( (UIInput)event.getComponent().findComponent( h ) ).getValue().toString();
                int id_nota = 0;
                detalle.setActdetActivo( "1" );
                detalle.setActdetNota( Float.parseFloat( valor_nota ) );
                AcAlumno alu = new AcAlumno();
                detalle.setAluId( ( (AcAlumno)( (AcMatricula)lista.get( i ) ).getAlu() ).getId() );
                AcSisEvaPersonalizado sisevaper = new AcSisEvaPersonalizado();
                sisevaper.setId( ( (AcSisEvaPersonalizado)list.get( j ) ).getId() );
            }
        }
        HSActaDAO dao1 = (HSActaDAO)ServiceFinder.findBean( "SpringHibernateDaoActa" );
        dao1.insertarActa( acta );

    }

    public HtmlPanelGrid getHtmlPanelActa() {
        return htmlPanelActa;
    }

    public void setHtmlPanelActa( HtmlPanelGrid htmlPanelActa ) {
        this.htmlPanelActa = htmlPanelActa;
    }

    public String getNombre_acta() {
        return nombre_acta;
    }

    public void setNombre_acta( String nombre_acta ) {
        this.nombre_acta = nombre_acta;
    }

    public String getCodigo_acta() {
        return codigo_acta;
    }

    public void setCodigo_acta( String codigo_acta ) {
        this.codigo_acta = codigo_acta;
    }

    public String getNumero_acta() {
        return numero_acta;
    }

    public void setNumero_acta( String numero_acta ) {
        this.numero_acta = numero_acta;
    }

    /*Listado Actas*/
    public String getCurso_nombre() {
        return curso_nombre;
    }

    public void setCurso_nombre( String curso_nombre ) {
        this.curso_nombre = curso_nombre;
    }

    public String getNombre_acta_final() {
        return nombre_acta_final;
    }

    public void setNombre_acta_final( String nombre_acta_final ) {
        this.nombre_acta_final = nombre_acta_final;
    }

    public String getCodigo_acta_final() {
        return codigo_acta_final;
    }

    public void setCodigo_acta_final( String codigo_acta_final ) {
        this.codigo_acta_final = codigo_acta_final;
    }

    public String getNumero_acta_final() {
        return numero_acta_final;
    }

    public void setNumero_acta_final( String numero_acta_final ) {
        this.numero_acta_final = numero_acta_final;
    }

    public String getProm_actividades() {
        return prom_actividades;
    }

    public void setProm_actividades( String prom_actividades ) {
        this.prom_actividades = prom_actividades;
    }

    public String getProm_investigacion() {
        return prom_investigacion;
    }

    public void setProm_investigacion( String prom_investigacion ) {
        this.prom_investigacion = prom_investigacion;
    }

    public String getParciales() {
        return parciales;
    }

    public void setParciales( String parciales ) {
        this.parciales = parciales;
    }

    public String getFinales() {
        return finales;
    }

    public void setFinales( String finales ) {
        this.finales = finales;
    }

    public String getFinal_finales() {
        return final_finales;
    }

    public void setFinal_finales( String final_finales ) {
        this.final_finales = final_finales;
    }

    public String getAlumno_nombre_aFinal() {
        return alumno_nombre_aFinal;
    }

    public void setAlumno_nombre_aFinal( String alumno_nombre_aFinal ) {
        this.alumno_nombre_aFinal = alumno_nombre_aFinal;
    }

    public String getAlumno_codigo_aFinal() {
        return alumno_codigo_aFinal;
    }

    public void setAlumno_codigo_aFinal( String alumno_codigo_aFinal ) {
        this.alumno_codigo_aFinal = alumno_codigo_aFinal;
    }

    public List getListaActas() {
        return listaActas;
    }

    public void setListaActas( List listaActas ) {
        this.listaActas = listaActas;
    }

    public void ChangeWeb( ActionEvent event ) {
        id_sec_horario = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "id_seccion" ) ).getValue().toString() );
        HSMatriculaDAO matricula = (HSMatriculaDAO)ServiceFinder.findBean( "SpringHibernateDaoMatricula" );

        matricula.ChangeBloqueoWeb( id_sec_horario );
        //System.out.println("SECCION 1  " +id_sec_horario);
        this.MostrarTabla( this.id_curape_v, this.id_semape_v );
        //System.out.println("SECCION 2  " +id_sec_horario);
    }

    public void LlenarActa( ActionEvent event ) {
        int sec = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "id_seccion" ) ).getValue().toString() );
        id_sec_horario = sec;
        HSActaDAO dao = (HSActaDAO)ServiceFinder.findBean( "SpringHibernateDaoActa" );
        AcActa acta_susti = dao.seleccionarActa( sec, "036002" );
        AcActa acta_principal = dao.seleccionarActa( sec, "036001" );
        //}

        this.setCodigo_acta_final( acta_principal.getActCodigo() );
        this.setNombre_acta_final( acta_principal.getActNombre() );
        this.setNumero_acta_final( acta_principal.getActNumero() );

        Set<AcActaDetalle> acta_det = new LinkedHashSet<AcActaDetalle>();
        acta_det = acta_principal.getAcActaDetalles();
        List l = Collections.synchronizedList( new LinkedList( acta_det ) );

        Set<AcActaDetalle> acta_det_susti = new LinkedHashSet<AcActaDetalle>();
        List list_susti_det = new ArrayList();
        if ( acta_susti != null ) {
            acta_det_susti = acta_susti.getAcActaDetalles();
            list_susti_det = Collections.synchronizedList( new LinkedList( acta_det_susti ) );
        }
        bNota nota;
        HSSeccionDAO dao1 = (HSSeccionDAO)ServiceFinder.findBean( "HibernateSpringDaoSeccion" );
        HSSistemaEvaluacionDAO dao2 = (HSSistemaEvaluacionDAO)ServiceFinder.findBean( "SpringHibernateDaoSistemaEvaluacion" );
        List li = dao2.seleccionarSistemaEvaluacion( ( (AcSeccion)dao1.seleccionarSeccion( sec ).get( 0 ) ).getCurape().getSisevaId() );
        List l1 = null;
        for ( int k = 0; k < li.size(); k++ ) {
            Set<AcSisEvaDetalle> siseva_det = new LinkedHashSet<AcSisEvaDetalle>();
            siseva_det = ( (AcSisEvaluacion)li.get( k ) ).getAcSisEvaDetalles();
            l1 = Collections.synchronizedList( new LinkedList( siseva_det ) );
        }
        List lista_alumnos = dao.seleccionarAlumnosActa( sec );
        Set<AcActaDetalle> acta_det1 = new LinkedHashSet<AcActaDetalle>();
        List listaActa = new ArrayList();
        HSAlumnoDAO dao4 = (HSAlumnoDAO)ServiceFinder.findBean( "SpringHibernateDaoAlumno" );
        List l2 = dao4.seleccionarAlumnoSeccion( sec );
        for ( int i = 0; i < lista_alumnos.size(); i++ ) {
            nota = new bNota();

            nota.setSustitutorio_id( 0 );
            nota.setSustitutorio( "--" );

            nota.setFinales_id( 0 );
            nota.setFinales( "--" );

            nota.setParciales_id( 0 );
            nota.setParciales( "--" );

            /*nota.setProm_investigacion_id(0);
             nota.setProm_investigacion("--");*/

            /*nota.setProm_actividades_id(0);
             nota.setProm_actividades("--");*/

            nota.setFinal_finales_id( 0 );
            nota.setFinal_finales( "--" );

            /*nota.setProm_lecturas_id(0);
             nota.setProm_lecturas("--");*/

            /**
             * ***
             */
            nota.setProm_actividades_id1( 0 );
            nota.setProm_actividades1( "--" );

            nota.setProm_actividades_id2( 0 );
            nota.setProm_actividades2( "--" );
            /**
             * ***
             */
            for ( int j = 0; j < l.size(); j++ ) {
                if ( lista_alumnos.get( i ).toString().equals( ( (AcActaDetalle)l.get( j ) ).getAluId().toString() ) ) {
                    if ( ( (AcActaDetalle)l.get( j ) ).getActdetTipoNota().toString().equals( "034002" ) ) {
                        nota.setFinal_finales( ( (AcActaDetalle)l.get( j ) ).getActdetNota().toString() );
                        nota.setFinal_finales_id( ( (AcActaDetalle)l.get( j ) ).getId() );
                    }
                    if ( ( (AcActaDetalle)l.get( j ) ).getActdetTipoNota().toString().equals( "034003" ) ) {
                        nota.setParciales( "C" );
                        nota.setFinales( "C" );
                        //nota.setProm_investigacion("C");
                        //nota.setProm_actividades("C");
                        /**
                         * ***
                         */
                        nota.setProm_actividades1( "C" );
                        nota.setProm_actividades2( "C" );
                        /**
                         * ***
                         */
                        //nota.setProm_lecturas("C");
                        nota.setFinal_finales( ( (AcActaDetalle)l.get( j ) ).getActdetNota().toString() );
                    }
                    for ( int k = 0; k < list_susti_det.size(); k++ ) {
                        if ( ( (AcActaDetalle)list_susti_det.get( k ) ).getAluId().toString().equals( ( (AcActaDetalle)l.get( j ) ).getAluId().toString() ) ) {
                            if ( ( (AcActaDetalle)list_susti_det.get( k ) ).getActdetTipoNota().equals( "034002" ) ) {
                                nota.setFinal_finales( ( (AcActaDetalle)list_susti_det.get( k ) ).getActdetNota().toString() );
                                nota.setTipo_det( ( (AcActaDetalle)list_susti_det.get( k ) ).getActdetTipoNota().toString() );
                            }
                            if ( ( (AcActaDetalle)list_susti_det.get( k ) ).getActdetTipoNota().equals( "034004" ) ) {
                                nota.setSustitutorio( ( (AcActaDetalle)list_susti_det.get( k ) ).getActdetNota().toString() );
                                nota.setSustitutorio_id( ( (AcActaDetalle)list_susti_det.get( k ) ).getId() );
                            }
                        }
                    }
                    for ( int m = 0; m < l1.size(); m++ ) {
                        if ( ( (AcSisEvaDetalle)l1.get( m ) ).getId().equals( ( (AcActaDetalle)l.get( j ) ).getSisevaDetalleId() ) ) {
                            //System.out.println((((AcSisEvaDetalle)l1.get(m)).getSisevaDetalleCodigo()) + " ---"+((AcActaDetalle)list.get(j)).getActdetTipoNota() );
                            //System.out.println("SIST EVA : "+((AcSisEvaDetalle)l1.get(m)).getSisevaDetalleCodigo());
                            if ( ( ( (AcSisEvaDetalle)l1.get( m ) ).getSisevaDetalleCodigo() ).equals( "EP" ) ) {
                                nota.setParciales( ( (AcActaDetalle)l.get( j ) ).getActdetNota().toString() );
                                nota.setParciales_id( ( (AcActaDetalle)l.get( j ) ).getId() );
                                nota.setParciales_det( ( (AcActaDetalle)l.get( j ) ).getSisevaDetalleId() );
                                nota.setTipo_det( ( (AcActaDetalle)l.get( j ) ).getActdetTipoNota() );
                                //                          System.out.println((((AcSisEvaDetalle)l1.get(m)).getSisevaDetalleCodigo()) + " ---"+((AcActaDetalle)list.get(j)).getActdetTipoNota() );
                            } else if ( ( ( (AcSisEvaDetalle)l1.get( m ) ).getSisevaDetalleCodigo() ).equals( "EF" ) ) {
                                nota.setFinales( ( (AcActaDetalle)l.get( j ) ).getActdetNota().toString() );
                                nota.setFinales_id( ( (AcActaDetalle)l.get( j ) ).getId() );
                                nota.setFinales_det( ( (AcActaDetalle)l.get( j ) ).getSisevaDetalleId() );
                                nota.setTipo_det( ( (AcActaDetalle)l.get( j ) ).getActdetTipoNota() );
                            } /**
                             * ***MODIFICACIONES*****
                             */
                            else if ( ( ( (AcSisEvaDetalle)l1.get( m ) ).getSisevaDetalleCodigo() ).equals( "PA-1" ) ) {
                                nota.setProm_actividades1( ( (AcActaDetalle)l.get( j ) ).getActdetNota().toString() );
                                nota.setProm_actividades_id1( ( (AcActaDetalle)l.get( j ) ).getId() );
                                nota.setProm_actividades_det1( ( (AcActaDetalle)l.get( j ) ).getSisevaDetalleId() );
                                nota.setTipo_det( ( (AcActaDetalle)l.get( j ) ).getActdetTipoNota() );
                            } else if ( ( ( (AcSisEvaDetalle)l1.get( m ) ).getSisevaDetalleCodigo() ).equals( "PA-2" ) ) {
                                nota.setProm_actividades2( ( (AcActaDetalle)l.get( j ) ).getActdetNota().toString() );
                                nota.setProm_actividades_id2( ( (AcActaDetalle)l.get( j ) ).getId() );
                                nota.setProm_actividades_det2( ( (AcActaDetalle)l.get( j ) ).getSisevaDetalleId() );
                                nota.setTipo_det( ( (AcActaDetalle)l.get( j ) ).getActdetTipoNota() );
                            }
                            /**
                             * ***********************
                             */
                            /*else if((((AcSisEvaDetalle)l1.get(m)).getSisevaDetalleCodigo()).equals("PA")){
                             nota.setProm_actividades(((AcActaDetalle)list.get(j)).getActdetNota().toString());
                             nota.setProm_actividades_id(((AcActaDetalle)list.get(j)).getId());
                             nota.setProm_actividades_det(((AcActaDetalle)list.get(j)).getSisevaDetalleId());
                             nota.setTipo_det(((AcActaDetalle)list.get(j)).getActdetTipoNota());
                             }*/
                            /*else if((((AcSisEvaDetalle)l1.get(m)).getSisevaDetalleCodigo()).equals("PI")){
                             nota.setProm_investigacion(((AcActaDetalle)list.get(j)).getActdetNota().toString());
                             nota.setProm_investigacion_id(((AcActaDetalle)list.get(j)).getId());
                             nota.setProm_investigacion_det(((AcActaDetalle)list.get(j)).getSisevaDetalleId());
                             nota.setTipo_det(((AcActaDetalle)list.get(j)).getActdetTipoNota());
                             }*/
                            /*else if((((AcSisEvaDetalle)l1.get(m)).getSisevaDetalleCodigo()).equals("PL")){
                             nota.setProm_lecturas(((AcActaDetalle)list.get(j)).getActdetNota().toString());
                             nota.setProm_lecturas_id(((AcActaDetalle)list.get(j)).getId());
                             nota.setProm_lecturas_det(((AcActaDetalle)list.get(j)).getSisevaDetalleId());
                             nota.setTipo_det(((AcActaDetalle)list.get(j)).getActdetTipoNota());
                             }*/
                        }
                    }
                }
            }
            for ( int a = 0; a < l2.size(); a++ ) {
                if ( ( (AcAlumno)l2.get( a ) ).getId().toString().equals( lista_alumnos.get( i ).toString() ) ) {
                    nota.setAlumno_id( ( (AcAlumno)l2.get( a ) ).getId() );
                    nota.setAlumno_codigo_aFinal( ( (AcAlumno)l2.get( a ) ).getAluCodigo() );
                    nota.setAlumno_nombre_aFinal( ( (AcAlumno)l2.get( a ) ).getAluAppaterno() + " " + ( (AcAlumno)l2.get( a ) ).getAluApmaterno() + " " + ( (AcAlumno)l2.get( a ) ).getAluNombres() );
                }
            }
            listaActa.add( nota );
        }
        this.setListaActas( listaActa );
    }

    public void EditarFila( ActionEvent event ) {
        this.setSustitutorio_auxiliar( this.getSustitutorio() );
        this.setView( false );
        this.setEditable( true );
    }

    public void Editar( ActionEvent event ) {
        this.setView( true );
        this.setEditable( false );
    }

    public void Cancelar( ActionEvent event ) {
        this.setSustitutorio( this.getSustitutorio_auxiliar() );
        this.setView( true );
        this.setEditable( false );
    }

    public String getSustitutorio() {
        return sustitutorio;
    }

    public void setSustitutorio( String sustitutorio ) {
        this.sustitutorio = sustitutorio;
    }

    public String getSustitutorio_auxiliar() {
        return sustitutorio_auxiliar;
    }

    public void setSustitutorio_auxiliar( String sustitutorio_auxiliar ) {
        this.sustitutorio_auxiliar = sustitutorio_auxiliar;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable( boolean editable ) {
        this.editable = editable;
    }

    public boolean isView() {
        return view;
    }

    public void setView( boolean view ) {
        this.view = view;
    }

    public void InsertarSustitutorios( ActionEvent event ) {
        HSActaDAO dao = (HSActaDAO)ServiceFinder.findBean( "SpringHibernateDaoActa" );
        int sec = Integer.parseInt( ( (HtmlInputHidden)event.getComponent().findComponent( "id_sec0" ) ).getValue().toString() );
        AcActa acta_tmp = dao.seleccionarActa( sec, "036002" );
        AcActa acta = new AcActa();
        AcActa acta_d = new AcActa();
        if ( acta_tmp != null ) {
            acta_d.setId( acta_tmp.getId() );
            dao.eliminarActa( acta_d );
        }
        acta.setActTipo( "036002" );
        acta.setActCreacion( new Date() );
        AcSeccion seccion = new AcSeccion();
        seccion.setId( sec );
        acta.setSec( seccion );
        acta.setActNumero( "Numero-SA" );
        acta.setActCodigo( "Codigo-SA" );
        acta.setActNombre( "Nombre-SA" );
        acta.setActActivo( "1" );
        AcActaDetalle detalle;
        int count, count2;
        Set<AcActaDetalle> acta_detalle = new LinkedHashSet<AcActaDetalle>();
        for ( int i = 0; i < listaActas.size(); i++ ) {
            detalle = new AcActaDetalle();
            count = 0;
            count2 = 0;
            float nota[] = new float[ 10 ];
            float nota2[] = new float[ 10 ];

            if ( !( (bNota)listaActas.get( i ) ).getSustitutorio().equals( "--" ) && !( (bNota)listaActas.get( i ) ).getProm_actividades1().equals( "--" ) && !( (bNota)listaActas.get( i ) ).getProm_actividades1().equals( "C" ) ) {
                nota2[count2] = Float.parseFloat( ( (bNota)listaActas.get( i ) ).getProm_actividades1() );
                detalle.setActdetNota( Float.parseFloat( ( (bNota)listaActas.get( i ) ).getProm_actividades1() ) );
                //detalle.setActdetTipoNota(((bNota)listaActas.get(i)).getTipo_det());
                detalle.setActdetTipoNota( "034001" );
                detalle.setAluId( ( (bNota)listaActas.get( i ) ).getAlumno_id() );
                detalle.setAct( acta );
                detalle.setSisevaDetalleId( ( (bNota)listaActas.get( i ) ).getProm_actividades_det1() );
                detalle.setActdetActivo( "1" );
                acta_detalle.add( detalle );
                count2++;
                System.out.println( "COD:PA1 " + ( (bNota)listaActas.get( i ) ).getTipo_det() );
            }

            detalle = new AcActaDetalle();
            if ( !( (bNota)listaActas.get( i ) ).getSustitutorio().equals( "--" ) && !( (bNota)listaActas.get( i ) ).getProm_actividades2().equals( "--" ) && !( (bNota)listaActas.get( i ) ).getProm_actividades2().equals( "C" ) ) {
                nota2[count2] = Float.parseFloat( ( (bNota)listaActas.get( i ) ).getProm_actividades2() );
                detalle.setActdetNota( Float.parseFloat( ( (bNota)listaActas.get( i ) ).getProm_actividades2() ) );
                //detalle.setActdetTipoNota(((bNota)listaActas.get(i)).getTipo_det());
                detalle.setActdetTipoNota( "034001" );
                detalle.setAluId( ( (bNota)listaActas.get( i ) ).getAlumno_id() );
                detalle.setAct( acta );
                detalle.setSisevaDetalleId( ( (bNota)listaActas.get( i ) ).getProm_actividades_det2() );
                detalle.setActdetActivo( "1" );
                acta_detalle.add( detalle );
                count2++;
                System.out.println( "COD:PA2 " + ( (bNota)listaActas.get( i ) ).getTipo_det() );
            }

            detalle = new AcActaDetalle();
            if ( !( (bNota)listaActas.get( i ) ).getSustitutorio().equals( "--" ) && !( (bNota)listaActas.get( i ) ).getParciales().equals( "--" ) && !( (bNota)listaActas.get( i ) ).getParciales().equals( "C" ) ) {
                nota[count] = Float.parseFloat( ( (bNota)listaActas.get( i ) ).getParciales() );
                detalle.setActdetNota( Float.parseFloat( ( (bNota)listaActas.get( i ) ).getParciales() ) );
                //detalle.setActdetTipoNota(((bNota)listaActas.get(i)).getTipo_det());
                detalle.setActdetTipoNota( "034001" );
                detalle.setAluId( ( (bNota)listaActas.get( i ) ).getAlumno_id() );
                detalle.setAct( acta );
                detalle.setSisevaDetalleId( ( (bNota)listaActas.get( i ) ).getParciales_det() );
                detalle.setActdetActivo( "1" );
                acta_detalle.add( detalle );
                count++;
                System.out.println( "COD:EP " + ( (bNota)listaActas.get( i ) ).getTipo_det() );
            }

            detalle = new AcActaDetalle();
            if ( !( (bNota)listaActas.get( i ) ).getSustitutorio().equals( "--" ) && !( (bNota)listaActas.get( i ) ).getFinales().equals( "--" ) && !( (bNota)listaActas.get( i ) ).getFinales().equals( "C" ) ) {
                nota[count] = Float.parseFloat( ( (bNota)listaActas.get( i ) ).getFinales() );
                detalle.setActdetNota( Float.parseFloat( ( (bNota)listaActas.get( i ) ).getFinales() ) );
                //detalle.setActdetTipoNota(((bNota)listaActas.get(i)).getTipo_det());
                detalle.setActdetTipoNota( "034001" );
                detalle.setAluId( ( (bNota)listaActas.get( i ) ).getAlumno_id() );
                detalle.setAct( acta );
                detalle.setSisevaDetalleId( ( (bNota)listaActas.get( i ) ).getFinales_det() );
                detalle.setActdetActivo( "1" );
                acta_detalle.add( detalle );
                count++;
                System.out.println( "COD:EF " + ( (bNota)listaActas.get( i ) ).getTipo_det() );
            }

            detalle = new AcActaDetalle();
            if ( !( (bNota)listaActas.get( i ) ).getSustitutorio().equals( "--" ) ) {
                nota[count] = Float.parseFloat( ( (bNota)listaActas.get( i ) ).getSustitutorio() );
                detalle.setActdetNota( Float.parseFloat( ( (bNota)listaActas.get( i ) ).getSustitutorio() ) );
                detalle.setActdetTipoNota( "034004" );
                detalle.setAluId( ( (bNota)listaActas.get( i ) ).getAlumno_id() );
                detalle.setAct( acta );
                detalle.setSisevaDetalleId( 0 );
                detalle.setActdetActivo( "1" );
                acta_detalle.add( detalle );
                count++;
                detalle = new AcActaDetalle();
                if ( !( (bNota)listaActas.get( i ) ).getSustitutorio().equals( "--" ) && !( (bNota)listaActas.get( i ) ).getFinal_finales().equals( "--" ) && !( (bNota)listaActas.get( i ) ).getFinal_finales().equals( "C" ) ) {
                    double promedio_susti = 0;
                    double nota_minima = 21;
                    double nota_minima_aux = 0;
                    int numeral = 0;

                    for ( int w = 0; w < count; w++ ) {
                        nota_minima_aux = nota_minima;
                        nota_minima = Math.min( nota_minima, nota[w] );
                        if ( nota_minima < nota_minima_aux ) {
                            numeral = w;
                            System.out.println( "las notas:" + nota[w] );
                        }
                    }
                    System.out.println( "la minima: " + nota_minima );
                    System.out.println( "count: " + count );
                    System.out.println( "no se considera numeral: " + numeral );
                    for ( int ry = 0; ry < count; ry++ ) {
                        if ( ry != numeral ) {
                            promedio_susti = nota[ry] + promedio_susti;
                        }
                    }
                    for ( int rt = 0; rt < count2; rt++ ) {
                        promedio_susti = nota2[rt] + promedio_susti;
                    }
                    System.out.println( "promedio_susti:" + promedio_susti );
                    System.out.println( "promedio_FINAL:" + Float.parseFloat( "" + Math.round( promedio_susti / ( ( count - 1 ) + count2 ) ) ) );
                    detalle.setActdetNota( Float.parseFloat( "" + Math.round( promedio_susti / ( ( count - 1 ) + count2 ) ) ) );
                    detalle.setActdetTipoNota( "034002" );
                    detalle.setAluId( ( (bNota)listaActas.get( i ) ).getAlumno_id() );
                    detalle.setAct( acta );
                    detalle.setSisevaDetalleId( 0 );
                    detalle.setActdetActivo( "1" );
                    acta_detalle.add( detalle );
                }
            }
        }
        acta.setAcActaDetalles( acta_detalle );
        dao.insertarActa( acta );
    }

    public int getSustitutorio_id() {
        return sustitutorio_id;
    }

    public void setSustitutorio_id( int sustitutorio_id ) {
        this.sustitutorio_id = sustitutorio_id;
    }

    public int getParciales_id() {
        return parciales_id;
    }

    public void setParciales_id( int parciales_id ) {
        this.parciales_id = parciales_id;
    }

    public int getFinales_id() {
        return finales_id;
    }

    public void setFinales_id( int finales_id ) {
        this.finales_id = finales_id;
    }

    public int getProm_investigacion_id() {
        return prom_investigacion_id;
    }

    public void setProm_investigacion_id( int prom_investigacion_id ) {
        this.prom_investigacion_id = prom_investigacion_id;
    }

    public int getProm_actividades_id() {
        return prom_actividades_id;
    }

    public void setProm_actividades_id( int prom_actividades_id ) {
        this.prom_actividades_id = prom_actividades_id;
    }

    public int getFinal_finales_id() {
        return final_finales_id;
    }

    public void setFinal_finales_id( int final_finales_id ) {
        this.final_finales_id = final_finales_id;
    }

    public int getAlumno_id() {
        return alumno_id;
    }

    public void setAlumno_id( int alumno_id ) {
        this.alumno_id = alumno_id;
    }

    public int getParciales_det() {
        return parciales_det;
    }

    public void setParciales_det( int parciales_det ) {
        this.parciales_det = parciales_det;
    }

    public int getFinales_det() {
        return finales_det;
    }

    public void setFinales_det( int finales_det ) {
        this.finales_det = finales_det;
    }

    public int getProm_investigacion_det() {
        return prom_investigacion_det;
    }

    public void setProm_investigacion_det( int prom_investigacion_det ) {
        this.prom_investigacion_det = prom_investigacion_det;
    }

    public int getProm_actividades_det() {
        return prom_actividades_det;
    }

    public void setProm_actividades_det( int prom_actividades_det ) {
        this.prom_actividades_det = prom_actividades_det;
    }

    public String getTipo_det() {
        return tipo_det;
    }

    public void setTipo_det( String tipo_det ) {
        this.tipo_det = tipo_det;
    }

    public boolean isBloqueo() {
        return bloqueo;
    }

    public void setBloqueo( boolean bloqueo ) {
        this.bloqueo = bloqueo;
    }

    public String getProm_lecturas() {
        return prom_lecturas;
    }

    public void setProm_lecturas( String prom_lecturas ) {
        this.prom_lecturas = prom_lecturas;
    }

    public int getProm_lecturas_det() {
        return prom_lecturas_det;
    }

    public void setProm_lecturas_det( int prom_lecturas_det ) {
        this.prom_lecturas_det = prom_lecturas_det;
    }

    public int getProm_lecturas_id() {
        return prom_lecturas_id;
    }

    public void setProm_lecturas_id( int prom_lecturas_id ) {
        this.prom_lecturas_id = prom_lecturas_id;
    }

    /**
     * @return the prom_actividades1
     */
    public String getProm_actividades1() {
        return prom_actividades1;
    }

    /**
     * @param prom_actividades1 the prom_actividades1 to set
     */
    public void setProm_actividades1( String prom_actividades1 ) {
        this.prom_actividades1 = prom_actividades1;
    }

    /**
     * @return the prom_actividades2
     */
    public String getProm_actividades2() {
        return prom_actividades2;
    }

    /**
     * @param prom_actividades2 the prom_actividades2 to set
     */
    public void setProm_actividades2( String prom_actividades2 ) {
        this.prom_actividades2 = prom_actividades2;
    }

    /**
     * @return the prom_actividades_id1
     */
    public int getProm_actividades_id1() {
        return prom_actividades_id1;
    }

    /**
     * @param prom_actividades_id1 the prom_actividades_id1 to set
     */
    public void setProm_actividades_id1( int prom_actividades_id1 ) {
        this.prom_actividades_id1 = prom_actividades_id1;
    }

    /**
     * @return the prom_actividades_id2
     */
    public int getProm_actividades_id2() {
        return prom_actividades_id2;
    }

    /**
     * @param prom_actividades_id2 the prom_actividades_id2 to set
     */
    public void setProm_actividades_id2( int prom_actividades_id2 ) {
        this.prom_actividades_id2 = prom_actividades_id2;
    }

    /**
     * @return the prom_actividades_det1
     */
    public int getProm_actividades_det1() {
        return prom_actividades_det1;
    }

    /**
     * @param prom_actividades_det1 the prom_actividades_det1 to set
     */
    public void setProm_actividades_det1( int prom_actividades_det1 ) {
        this.prom_actividades_det1 = prom_actividades_det1;
    }

    /**
     * @return the prom_actividades_det2
     */
    public int getProm_actividades_det2() {
        return prom_actividades_det2;
    }

    /**
     * @param prom_actividades_det2 the prom_actividades_det2 to set
     */
    public void setProm_actividades_det2( int prom_actividades_det2 ) {
        this.prom_actividades_det2 = prom_actividades_det2;
    }

    public void SeleccionarSec( ActionEvent event ) {

        //int sec = Integer.parseInt(((HtmlInputHidden) event.getComponent().findComponent("id_sec0")).getValue().toString());
        w_sistema_evaluacion = new ArrayList();
        int curapeId = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "id_cur_ape" ) ).getValue().toString() );
        int secId = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "id_seccion" ) ).getValue().toString() );
        this.setW_sec_id( secId );
        this.setW_curape_id( curapeId );
//        System.out.println("el valor es: "+ curapeId);
        HSImportacionNotasDAO dao = (HSImportacionNotasDAO)ServiceFinder.findBean( "SpringHibernateDaoImportacionNotas" );
        List<AcSisEvaPersonalizado> lsisvaper = dao.listarSistemaEvaluacionPersonalizado_x_seccion( curapeId );
        //System.out.println("cantidad -> "+lista.size());
        for ( int i = 0; i < lsisvaper.size(); i++ ) {
            bNota importe = new bNota();
            //System.out.println("siseva_per_codigo -> "+lista.get(i).getSisevaCodigo()) ;
            List<AcImportacionNotas> blonota = dao.listarImportacionNota_x_seccion( secId, lsisvaper.get( i ).getId() );
            //System.out.println("el sec_id es -> "+secId+"   :: -> "+lista.get(i).getId());
            try {
                int canti = blonota.size();
//                System.out.println("la cantidad es " + canti);
                if ( blonota.size() > 0 ) {
                    if ( blonota.get( 0 ).getImpnotEstado().equals( "051002" ) ) {
                        setW_marcar( true );
                        setW_mensaje( "Ingresado" );
                    } else {
                        setW_marcar( false );
                        setW_mensaje( "No ingresado" );
                    }
                } else {
                    setW_marcar( false );
                    setW_mensaje( "No ingresado" );
                }
            } catch ( Exception e ) {

                setW_mensaje( "No ingresado" );
            }

            importe.setW_siseva_per_id( lsisvaper.get( i ).getId() );
            importe.setW_siseva_per_codigo( lsisvaper.get( i ).getSisevaCodigo() );
            importe.setW_siseva_per_descripcion( lsisvaper.get( i ).getSisevaPerNombre() );
            importe.setW_marcar( this.isW_marcar() );
            importe.setW_sec_id( this.getW_sec_id() );
            importe.setW_mensaje( w_mensaje );
            w_sistema_evaluacion.add( importe );

        }
        this.setW_sec_id( w_sec_id );
//        System.out.println("seleccion sec_id " + this.getW_sec_id());
    }

    public void bloquearNotas( ActionEvent event ) {
        this.oncomplete = "";
        try {


            // AcImportacionNotas impoNota=new AcImportacionNotas();
            HSImportacionNotasDAO dao = (HSImportacionNotasDAO)ServiceFinder.findBean( "SpringHibernateDaoImportacionNotas" );
            //int curapeId = Integer.parseInt(((UIParameter) event.getComponent().findComponent("w_curape_id")).getValue().toString());
            //int secId=Integer.parseInt(((UIParameter) event.getComponent().findComponent("w_sec_id")).getValue().toString());
            int secId = Integer.parseInt( ( (HtmlInputHidden)event.getComponent().findComponent( "w_sec_idbak" ) ).getValue().toString() );
            //int sisevaper_id=Integer.parseInt(((UIParameter) event.getComponent().findComponent("w_siseva_per_id")).getValue().toString());*/

//        System.out.println("el sec_id es ->  "+secId +"  ---  "+this.getW_sec_id());
            int canti = this.getW_sistema_evaluacion().size();
            for ( int i = 0; i < canti; i++ ) {
                AcImportacionNotas impoNota = new AcImportacionNotas();

                List<AcImportacionNotas> valor = dao.listarImportacionNota_x_seccion( secId, this.getW_sistema_evaluacion().get( i ).getW_siseva_per_id() );
                AcSeccion sec = new AcSeccion();
                AcSisEvaPersonalizado per = new AcSisEvaPersonalizado();
                sec.setId( secId );
                per.setId( this.getW_sistema_evaluacion().get( i ).getW_siseva_per_id() );
                if ( valor.size() > 0 ) {
//                System.out.println("modificar ");
                    impoNota.setAcSeccion( sec );
                    impoNota.setAcSisEvaPersonalizado( per );
                    impoNota.setImpnotActivo( "1" );
                    impoNota.setImpnotId( valor.get( 0 ).getImpnotId() );
//                System.out.println("el id es --> "+valor.get(0).getImpnotId());
                    if ( this.getW_sistema_evaluacion().get( i ).isW_marcar() ) {
                        impoNota.setImpnotEstado( "051002" );
                    } else {
                        impoNota.setImpnotEstado( "051001" );
                    }
                    dao.actualizarImportacionNota( impoNota );
                } else {
                    impoNota.setAcSeccion( sec );
                    impoNota.setAcSisEvaPersonalizado( per );
                    impoNota.setImpnotActivo( "1" );
                    impoNota.setImpnotId( 0 );
                    if ( this.getW_sistema_evaluacion().get( i ).isW_marcar() ) {
                        impoNota.setImpnotEstado( "051002" );
                    } else {
                        impoNota.setImpnotEstado( "051001" );
                    }
                    dao.agregarImportacionNota( impoNota );

                }
            }
            this.oncomplete = "javascript:alert('Registros actualizados correctamente');Richfaces.hideModalPanel('mpbloquea')";
            w_sistema_evaluacion = new ArrayList<bNota>();
        } catch ( Exception e ) {
            this.oncomplete = "javascript:alert('Verifique la modificacion')";
        }


    }

    public void seleccionarImporte( ActionEvent event ) {
        int curapeId = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "id_cur_ape" ) ).getValue().toString() );
        int secId = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "id_seccion" ) ).getValue().toString() );
        this.setW_sec_id( secId );
        this.setW_curape_id( curapeId );
        //System.out.println("el valor es: curaperturado  ->  "+ curapeId);
        // System.out.println("el valor es: sec_id  ->  "+ secId);
        HSImportacionNotasDAO dao = (HSImportacionNotasDAO)ServiceFinder.findBean( "SpringHibernateDaoImportacionNotas" );

        List<AcSisEvaPersonalizado> lsisvaper = dao.listarSistemaEvaluacionPersonalizado_x_seccion( curapeId );
        //System.out.println("cantidad -> "+lista.size());
        if ( lsisvaper.size() > 0 ) {
            cbo_radio = new SelectItem[ lsisvaper.size() ];

            for ( int i = 0; i < cbo_radio.length; i++ ) {
                bNota importe = new bNota();
                //System.out.println("siseva_per_codigo -> "+lista.get(i).getSisevaCodigo()) ;
                HSNotasTemporalesDAO dao2 = (HSNotasTemporalesDAO)ServiceFinder.findBean( "SpringHibernateDaoNotasTemporales" );
                List<AcNotasTemporales> notas_temporales = dao2.listarNotasTemporales( secId, lsisvaper.get( i ).getId() );
                //System.out.println("el sec_id es -> "+secId+"   :: -> "+lista.get(i).getId());
                try {
                    int canti = notas_temporales.size();
//                    System.out.println("la cantidad es " + canti);
                    if ( canti > 0 ) {
                        setW_mensaje( "Ingresado" );

                    } else {
                        setW_mensaje( "No ingresado" );
                    }
                } catch ( Exception e ) {

                    setW_mensaje( "No ingresado" );
                }
                cbo_radio[i] = new SelectItem( lsisvaper.get( i ).getId(), lsisvaper.get( i ).getSisevaPerNombre() + "  -  " + w_mensaje );
            }
            w_siseva_per_id = 0;
        } else {
            cbo_radio = new SelectItem[ 0 ];
        }
    }

    public void guardarImporte( ActionEvent event ) {
        oncomplete = "";
        if ( this.getW_siseva_per_id() > 0 ) {
            String mensaje = "";
            int secId = Integer.parseInt( ( (HtmlInputHidden)event.getComponent().findComponent( "w_sec_idbak2" ) ).getValue().toString() );
            // System.out.println("el sec_id -> "+secId);
            // System.out.println(" per_id -> "+this.getW_siseva_per_id());
            HSNotasTemporalesDAO dao = (HSNotasTemporalesDAO)ServiceFinder.findBean( "SpringHibernateDaoNotasTemporales" );
            // System.out.println(" -> "+dao.listarNotasTemporales(secId, this.getW_siseva_per_id()));
            List<AcNotasTemporales> notas_temporales = dao.listarNotasTemporales( secId, w_siseva_per_id );
            if ( notas_temporales.size() > 0 ) {
                for ( int i = 0; i < notas_temporales.size(); i++ ) {
                    AcNota nota = dao.buscarNotax_notaTemporal( notas_temporales.get( i ).getAluId(), notas_temporales.get( i ).getAcSeccion().getId(), notas_temporales.get( i ).getSisevaPerId() );
                    if ( nota != null ) {
                        // System.out.println("editar");
                        // System.out.println("la seccion es -> "+nota.getSecId());
                        nota.setNotNota( notas_temporales.get( i ).getNottemNota() );
                        // System.out.println("la nota es -> "+nota.getNotNota()+" alu_id "+nota.getAlu().getId()+" la nota -"+notas_temporales.get(i).getNottemNota());
                        // System.out.println("el id -> "+nota.getId());
                        // System.out.println("el sistema de evaluacio "+nota.getSisevaPer().getId());
                        try {
                            dao.modificarAcNotas( nota );
                        } catch ( Exception e ) {
                            e.printStackTrace();
                        }

                    } else {
                        System.out.println( "agregar" );
                        nota = new AcNota();
                        AcAlumno alu = new AcAlumno();
                        AcSisEvaPersonalizado per = new AcSisEvaPersonalizado();
                        alu.setId( notas_temporales.get( i ).getAluId() );
                        per.setId( notas_temporales.get( i ).getSisevaPerId() );
                        nota.setAlu( alu );
                        nota.setSisevaPer( per );
                        nota.setNotActivo( "1" );
                        nota.setNotCreacion( notas_temporales.get( i ).getNottemFecha() );
                        nota.setNotNota( notas_temporales.get( i ).getNottemNota() );
                        nota.setNotObservacion( "" );
                        nota.setSecId( notas_temporales.get( i ).getAcSeccion().getId() );
                        dao.agregarAcNotas( nota );
                    }
                }
                HSImportacionNotasDAO daoI = (HSImportacionNotasDAO)ServiceFinder.findBean( "SpringHibernateDaoImportacionNotas" );
                List<AcImportacionNotas> imporT = daoI.listarImportacionNota_x_seccion( secId, w_siseva_per_id );
                AcImportacionNotas impor = new AcImportacionNotas();
                AcSeccion sec = new AcSeccion();
                AcSisEvaPersonalizado per = new AcSisEvaPersonalizado();
                sec.setId( secId );
                per.setId( w_siseva_per_id );
                if ( imporT.size() > 0 ) {
//                System.out.println("modificar ");
                    impor.setAcSeccion( sec );
                    impor.setAcSisEvaPersonalizado( per );
                    impor.setImpnotActivo( "1" );
                    impor.setImpnotId( imporT.get( 0 ).getImpnotId() );
                    impor.setImpnotEstado( "051002" );
//                System.out.println("el id es --> "+valor.get(0).getImpnotId());                   
                    daoI.actualizarImportacionNota( impor );
                } else {
                    impor.setAcSeccion( sec );
                    impor.setAcSisEvaPersonalizado( per );
                    impor.setImpnotActivo( "1" );
                    impor.setImpnotId( 0 );
                    impor.setImpnotEstado( "051002" );
                    daoI.agregarImportacionNota( impor );
                }

                mensaje = "Los registros han sido importados con exito ;)";
            } else {
                mensaje = "No hay notas temporales";
            }
            oncomplete = "javascript:alert('" + mensaje + "');Richfaces.hideModalPanel('mpimporta')";
        } else {
            oncomplete = "javascript:alert('Seleccione el tipo de nota')";
        }


    }

    public void vistaPrevia( ActionEvent event ) {
        listarAlumnoNota = new ArrayList();
        oncomplete = "";
        if ( this.getW_siseva_per_id() > 0 ) {
            System.out.println( "el sisper_id -> " + this.getW_siseva_per_id() );
            int secId = Integer.parseInt( ( (HtmlInputHidden)event.getComponent().findComponent( "w_sec_idbak2" ) ).getValue().toString() );
            System.out.println( "el sec_id -> " + secId );
            //HSNotasTemporalesDAO dao = (HSNotasTemporalesDAO) ServiceFinder.findBean("SpringHibernateDaoNotasTemporales");
            HSAlumnoDAO dao = (HSAlumnoDAO)ServiceFinder.findBean( "SpringHibernateDaoAlumno" );
            HSNotasTemporalesDAO daoNT = (HSNotasTemporalesDAO)ServiceFinder.findBean( "SpringHibernateDaoNotasTemporales" );
            HSNotaDAO daoN = (HSNotaDAO)ServiceFinder.findBean( "SpringHibernateDaoNota" );
            List<AcAlumno> alumno = dao.listarAlumnoxSeccion( secId );
            List<AcNotasTemporales> notaT = daoNT.listarNotasTemporales( secId, w_siseva_per_id );
            //for(int i=0; i<alumno.size();i++)[]

            for ( int i = 0; i < alumno.size(); i++ ) {
                List<AcNota> nota = daoN.listarNotaxAlumno( alumno.get( i ).getId(), secId, w_siseva_per_id );
                bNota alu = new bNota();

                if ( nota.size() > 0 ) {
                    alu.setAlu_nota( nota.get( 0 ).getNotNota().toString() );
                } else {
                    alu.setAlu_nota( "" );
                }
                if ( notaT.size() > 0 ) {
                    for ( int j = 0; j < notaT.size(); j++ ) {
                        if ( alumno.get( i ).getId().intValue() == notaT.get( j ).getAluId().intValue() ) {
                            alu.setAlu_notaT( notaT.get( i ).getNottemNota().toString() );
                        }

                    }
                } else {
                    alu.setAlu_notaT( "" );
                }
                alu.setAlu_datos( alumno.get( i ).getAluAppaterno() + " " + alumno.get( i ).getAluApmaterno() + " " + alumno.get( i ).getAluNombres() );
                alu.setAlu_codigo( alumno.get( i ).getAluCodigo() );
                alu.setAlu_conta( i + 1 );
                alu.setAlu_id( alumno.get( i ).getId() );

                listarAlumnoNota.add( alu );


            }
            oncomplete = "Richfaces.showModalPanel('mpvista')";
        } else {
            oncomplete = "javascript:alert('Seleccione el tipo de nota')";
        }
    }

    public void actualizarActa( ActionEvent event ) {
        try {
            HSNotaDAO dao = (HSNotaDAO)ServiceFinder.findBean( "SpringHibernateDaoNota" );
            System.out.println( "Dao -> " + dao.listarNotaAlumno( 4725, 2086 ) );
            for ( int i = 0; i < dao.listarNotaAlumno( 4725, 2086 ).size(); i++ ) {
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }

    }
}