package net.uch.academica.managedBeans;

import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIParameter;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.uch.academica.hibernateSpringDao.*;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcCursoAperturado;
import net.uch.mapping.AcEspecialidad;
import net.uch.mapping.AcFacultad;
import net.uch.mapping.AcPlanCurso;
import net.uch.mapping.AcPlancurricular;
import net.uch.mapping.AcSeccion;
import net.uch.mapping.AcSeccionEspecialidad;
import net.uch.mapping.AcSemestre;
import net.uch.mapping.AcSisEvaDetalle;
import net.uch.mapping.AcSisEvaPersonalizado;
import net.uch.mapping.AcSisEvaluacion;
import net.uch.mapping.TbCatalogo;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSFacultadDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSSemestreDAO;
import net.uch.tablaSistema.managedBeans.bUsuario;
import net.uch.util.Archivo;
import net.uch.util.CommonDAO;
import net.uch.util.ObjNodeApertura;
import org.richfaces.component.UITree;
import org.richfaces.event.NodeSelectedEvent;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;
import org.richfaces.model.UploadItem;

public class bAperturaCursos {

    private TreeNode arbol;
    private List listaCursos;
    private String nodoSeleccionado;
    private int cur_id_s;
    public String cur_ciclo_s;
    private String cur_codigo_s;
    private String cur_nombre_s;
    private int curape_id_s;
    private int plancur_id_s;
    private int curape_id_aux; //static
    private String arbol_facultad;
    private String arbol_especialidad;
    private String arbol_ciclo;
    private String arbol_plan;
    private int sistemaEvaluacion_i;
//    public Object O[][][][] = new Object[20][20][20][20]; //static
    public SelectItem[] comboSistemaEvaluacion;
    private boolean apertura_i = true;
    private String ciclo_u;
    private int id_curape_u;
    private int id_u;
    private String codigo_u;
    private String nombre_u;
    private String apertura_u;
    private String siseva_u;
    private String ciclo_aux;
    private int id_aux; //static
    private String codigo_aux; //static
    private String nombre_aux; //static
    private boolean apertura_aux; //static
    private String siseva_aux; //static
    private SelectItem[] comboPromediosExamenes;
    private SelectItem[] comboSemestres;
    int semestre_f;
    private String cur_aperturado_s;
    private String cur_evaluacion_s;
    private String cur_evaluacion_id_s;
    private int cur_evaluacion_aux; //static
    private String cur_semestre_s;
    private String cur_semestre_s_d;
    int num_reg_sem = 5;
    private List<bAperturaCursos> detalle = new ArrayList<bAperturaCursos>(); //static
    private List<bAperturaCursos> detalle1 = new ArrayList<bAperturaCursos>(); //static
    private List tablaDetallePromedios;
    private List tablaSecciones;
    private int id_det = 0;
    private String codigo_i;
    private String nombre_i;
    private int promediosExamenes_i;
    private String peso_i = "1";
    private String semana_i;
    private int orden_i;
    public String activo_i;
    private int p_plancur_id_i;
    private String codigo_i_aux; //static
    private String nombre_i_aux; //static
    private String peso_i_aux; //static
    public String activo_i_aux; //static
    private int p_plancur_id_aux; //static
    private String editable = "false";
    private String view = "true";
    private boolean editable_bool = false;
    private boolean view_bool = true;
    private boolean visible = false;
    private boolean ver = false;
    public String efecto = "hideDiv({duration:0.7})";
    public boolean personalizado = false;
    public boolean personalizado_aux; //static
    public String efecto_aux = "hideDiv({duration:0.7})"; //static
    public String alert = "";
    public String siseva_det;
    public String cur_candado_s;
    private String codigo_sec;
    private String nombre_sec;
    private String vacantes_sec;
    private boolean w_descarga = false;
    private List listaSem;
    private List<ObjNodeApertura> nodos;
    private List<AcSeccionEspecialidad> listaSeccionEspecialidad = new ArrayList<AcSeccionEspecialidad>();
    private SelectItem[] cboSeccionEspe;
    private AcSeccionEspecialidad objAcSeccionEspecialidad = new AcSeccionEspecialidad();
    private int w_secEspeid = 0;
    private int w_espId = 0;
    private String w_ciclo = "";
    private String oncomplete = "";
    private Archivo archivoUp = new Archivo();
    //nuesvo sistema de evaluacion
    private SelectItem[] tipoNota;
    private SelectItem[] semestreEtapa;
    private String seTipoNota;
    private String agrupar_i; //almacenar lo ingresado manualmente 
    private String seSemestreEtapa;
    private String v_tipo_nota;
    private String v_semestre_etapa;
    private String agrupar_det_aux; //static
    private String tnota_det_aux; //static
    private String setapa_det_aux;

    public bAperturaCursos() throws SQLException, Exception {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession)context.getSession( false );
        bUsuario usu = (bUsuario)session1.getAttribute( "usuario" );
        if ( usu != null ) {
            HSSemestreDAO dao = (HSSemestreDAO)ServiceFinder.findBean( "SpringHibernateDaoSemestre" );
            listaSem = dao.seleccionarSemestreActivo();
        } else {
            throw new Exception();
        }
    }

    public boolean isW_descarga() {
        return w_descarga;
    }

    public void setW_descarga( boolean w_descarga ) {
        this.w_descarga = w_descarga;
    }

    public Archivo getArchivoUp() {
        return archivoUp;
    }

    public void setArchivoUp( Archivo archivoUp ) {
        this.archivoUp = archivoUp;
    }

    public bAperturaCursos( int param ) {
    }

    public List<ObjNodeApertura> getNodos() {
        return nodos;
    }

    public void setNodos( List<ObjNodeApertura> nodos ) {
        this.nodos = nodos;
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

    public List getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos( List listaCursos ) {
        this.listaCursos = listaCursos;
    }

    public String getNodoSeleccionado() {
        return nodoSeleccionado;
    }

    public void setNodoSeleccionado( String nodoSeleccionado ) {
        this.nodoSeleccionado = nodoSeleccionado;
    }

    public int getCur_id_s() {
        return cur_id_s;
    }

    public void setCur_id_s( int cur_id_s ) {
        this.cur_id_s = cur_id_s;
    }

    public String getCur_codigo_s() {
        return cur_codigo_s;
    }

    public void setCur_codigo_s( String cur_codigo_s ) {
        this.cur_codigo_s = cur_codigo_s;
    }

    public String getCur_nombre_s() {
        return cur_nombre_s;
    }

    public void setCur_nombre_s( String cur_nombre_s ) {
        this.cur_nombre_s = cur_nombre_s;
    }

    public int getCurape_id_s() {
        return curape_id_s;
    }

    public void setCurape_id_s( int curape_id_s ) {
        this.curape_id_s = curape_id_s;
    }

    public String getCur_ciclo_s() {
        return cur_ciclo_s;
    }

    public void setCur_ciclo_s( String cur_ciclo_s ) {
        this.cur_ciclo_s = cur_ciclo_s;
    }

    public String getCur_evaluacion_id_s() {
        return cur_evaluacion_id_s;
    }

    public void setCur_evaluacion_id_s( String cur_evaluacion_id_s ) {
        this.cur_evaluacion_id_s = cur_evaluacion_id_s;
    }

    public String getArbol_facultad() {
        return arbol_facultad;
    }

    public void setArbol_facultad( String arbol_facultad ) {
        this.arbol_facultad = arbol_facultad;
    }

    public String getArbol_especialidad() {
        return arbol_especialidad;
    }

    public void setArbol_especialidad( String arbol_especialidad ) {
        this.arbol_especialidad = arbol_especialidad;
    }

    public boolean isApertura_i() {
        if ( curape_id_aux != 0 ) {
            return apertura_aux;
        } else {
            return apertura_i;
        }
    }

    public void setApertura_i( boolean apertura_i ) {
        this.apertura_i = apertura_i;
    }

    public int getSistemaEvaluacion_i() {
        if ( curape_id_aux != 0 ) {
            return cur_evaluacion_aux;
        } else {
            return sistemaEvaluacion_i;
        }
    }

    public void setSistemaEvaluacion_i( int sistemaEvaluacion_i ) {
        this.sistemaEvaluacion_i = sistemaEvaluacion_i;
    }

    public String getCiclo_u() {
        if ( curape_id_aux != 0 ) {
            return ciclo_aux;
        } else {
            return ciclo_u;
        }
    }

    public void setCiclo_u( String ciclo_u ) {
        this.ciclo_u = ciclo_u;
    }

    public int getId_curape_u() {
        if ( curape_id_aux != 0 ) {
            return curape_id_aux;
        } else {
            return id_curape_u;
        }
    }

    public void setId_curape_u( int id_curape_u ) {
        this.id_curape_u = id_curape_u;
    }

    public int getId_u() {
        if ( curape_id_aux != 0 ) {
            return id_aux;
        } else {
            return id_u;
        }
    }

    public void setId_u( int id_u ) {
        this.id_u = id_u;
    }

    public String getCodigo_u() {
        if ( curape_id_aux != 0 ) {
            return codigo_aux;
        } else {
            return codigo_u;
        }
    }

    public void setCodigo_u( String codigo_u ) {
        this.codigo_u = codigo_u;
    }

    public String getNombre_u() {
        if ( curape_id_aux != 0 ) {
            return nombre_aux;
        } else {
            return nombre_u;
        }
    }

    public void setNombre_u( String nombre_u ) {
        this.nombre_u = nombre_u;
    }

    public String getApertura_u() {
        return apertura_u;
    }

    public void setApertura_u( String apertura_u ) {
        this.apertura_u = apertura_u;
    }

    public int getPromediosExamenes_i() {
        return promediosExamenes_i;
    }

    public void setPromediosExamenes_i( int promediosExamenes_i ) {
        this.promediosExamenes_i = promediosExamenes_i;
    }

    public String getSiseva_u() {
        if ( curape_id_aux != 0 ) {
            return siseva_aux;
        } else {
            return siseva_u;
        }
    }

    public void setSiseva_u( String siseva_u ) {
        this.siseva_u = siseva_u;
    }

    public int getSemestre_f() {
        return semestre_f;
    }

    public void setSemestre_f( int semestre_f ) {
        this.semestre_f = semestre_f;
    }

    public String getCur_aperturado_s() {
        return cur_aperturado_s;
    }

    public void setCur_aperturado_s( String cur_aperturado_s ) {
        this.cur_aperturado_s = cur_aperturado_s;
    }

    public String getCur_semestre_s() {
        return cur_semestre_s;
    }

    public void setCur_semestre_s( String cur_semestre_s ) {
        this.cur_semestre_s = cur_semestre_s;
    }

    public String getCur_evaluacion_s() {
        return cur_evaluacion_s;
    }

    public void setCur_evaluacion_s( String cur_evaluacion_s ) {
        this.cur_evaluacion_s = cur_evaluacion_s;
    }

    public String getArbol_ciclo() {
        return arbol_ciclo;
    }

    public void setArbol_ciclo( String arbol_ciclo ) {
        this.arbol_ciclo = arbol_ciclo;
    }

    public String getArbol_plan() {
        return arbol_plan;
    }

    public void setArbol_plan( String arbol_plan ) {
        this.arbol_plan = arbol_plan;
    }

    public String getCodigo_i() {
        return codigo_i;
    }

    public void setCodigo_i( String codigo_i ) {
        this.codigo_i = codigo_i;
    }

    public String getNombre_i() {
        return nombre_i;
    }

    public void setNombre_i( String nombre_i ) {
        this.nombre_i = nombre_i;
    }

    public String getPeso_i() {
        return peso_i;
    }

    public void setPeso_i( String peso_i ) {
        this.peso_i = peso_i;
    }

    public String getSemana_i() {
        return semana_i;
    }

    public void setSemana_i( String semana_i ) {
        this.semana_i = semana_i;
    }

    // Getters y Setter nuevo sistema de evaluacion
    public SelectItem[] getTipoNota() {
        if ( tipoNota == null ) {
            try {
                HSCatalogoDAO dao = (HSCatalogoDAO)ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );
                List<TbCatalogo> lista = dao.seleccionarCatalogo( "034" );
                tipoNota = new SelectItem[ lista.size() + 1 ];
                tipoNota[0] = new SelectItem( "000000", "[Seleccione]" );
                for ( int i = 0; i < lista.size(); i++ ) {
                    tipoNota[i + 1] = new SelectItem( lista.get( i ).getCatCodigoGrupo() + lista.get( i ).getCatCodigoElemento(), lista.get( i ).getCatDescripcionElemento() );
                }
            } catch ( Exception e ) {
                System.err.println( "Error al cargar los tipos de clase " + e.getMessage() );
                tipoNota = new SelectItem[ 1 ];
                tipoNota[0] = new SelectItem( "000000", "[Seleccione]" );
            }
        }
        return tipoNota;
    }

    public void setTipoNota( SelectItem[] tipoNota ) {
        this.tipoNota = tipoNota;
    }

    public SelectItem[] getSemestreEtapa() {
        if ( semestreEtapa == null ) {
            try {
                HSCatalogoDAO dao = (HSCatalogoDAO)ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );
                List<TbCatalogo> lista = dao.seleccionarCatalogo( "092" );
                semestreEtapa = new SelectItem[ lista.size() + 1 ];
                semestreEtapa[0] = new SelectItem( "000000", "[Seleccione]" );
                for ( int i = 0; i < lista.size(); i++ ) {
                    semestreEtapa[i + 1] = new SelectItem( lista.get( i ).getCatCodigoGrupo() + lista.get( i ).getCatCodigoElemento(), lista.get( i ).getCatDescripcionElemento() );
                }
            } catch ( Exception e ) {
                System.err.println( "Error al cargar los tipos de clase " + e.getMessage() );
                semestreEtapa = new SelectItem[ 1 ];
                semestreEtapa[0] = new SelectItem( "000000", "[Seleccione]" );
            }
        }
        return semestreEtapa;
    }

    public void setSemestreEtapa( SelectItem[] semestreEtapa ) {
        this.semestreEtapa = semestreEtapa;
    }

    public String getAgrupar_i() {
        return agrupar_i;
    }

    public void setAgrupar_i( String agrupar_i ) {
        this.agrupar_i = agrupar_i;
    }

    public String getSETipoNota() {
        return this.seTipoNota;
    }

    public void setSETipoNota( String seTipoNota ) {
        this.seTipoNota = seTipoNota;
    }

    public String getSESemestreEtapa() {
        return this.seSemestreEtapa;
    }

    public void setSESemestreEtapa( String seSemestreEtapa ) {
        this.seSemestreEtapa = seSemestreEtapa;
    }

    public String getV_tipo_nota() {
        return v_tipo_nota;
    }

    public void setV_tipo_nota( String v_tipo_nota ) {
        this.v_tipo_nota = v_tipo_nota;
    }

    public String getV_semestre_etapa() {
        return v_semestre_etapa;
    }

    public void setV_semestre_etapa( String v_semestre_etapa ) {
        this.v_semestre_etapa = v_semestre_etapa;
    }
    //FIN

    public List getTablaDetallePromedios() {
        if ( curape_id_aux != 0 ) {
            return view( detalle );
        } else {
            return view( tablaDetallePromedios );
        }
    }

    public List view( List<bAperturaCursos> detalle ) {
        List<bAperturaCursos> lista = new ArrayList<bAperturaCursos>();

        if ( detalle != null ) {
            for ( int j = 0; j < detalle.size(); j++ ) {
                if ( ( (bAperturaCursos)detalle.get( j ) ).getActivo_i().equals( "1" ) ) {
                    lista.add( detalle.get( j ) );
                }
            }
        }
        return lista;
    }

    public void setTablaDetallePromedios( List tablaDetallePromedios ) {
        this.tablaDetallePromedios = tablaDetallePromedios;
    }

    public int getId_det() {
        return id_det;
    }

    public void setId_det( int id_det ) {
        this.id_det = id_det;
    }

    public String getActivo_i() {
        return activo_i;
    }

    public void setActivo_i( String activo_i ) {
        this.activo_i = activo_i;
    }

    public String getEditable() {
        return editable;
    }

    public void setEditable( String editable ) {
        this.editable = editable;
    }

    public String getView() {
        return view;
    }

    public void setView( String view ) {
        this.view = view;
    }

    public boolean isEditable_bool() {
        return editable_bool;
    }

    public void setEditable_bool( boolean editable_bool ) {
        this.editable_bool = editable_bool;
    }

    public boolean isView_bool() {
        return view_bool;
    }

    public void setView_bool( boolean view_bool ) {
        this.view_bool = view_bool;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible( boolean visible ) {
        this.visible = visible;
    }

    public boolean isVer() {
        return ver;
    }

    public void setVer( boolean ver ) {
        this.ver = ver;
    }

    public String getEfecto() {
        if ( curape_id_aux != 0 ) {
            return efecto_aux;
        } else {
            return efecto;
        }
    }

    public void setEfecto( String efecto ) {
        this.efecto = efecto;
    }

    public boolean isPersonalizado() {
        if ( curape_id_aux != 0 ) {
            return personalizado_aux;
        } else {
            return personalizado;
        }
    }

    public void setPersonalizado( boolean personalizado ) {
        //curape_id_aux=0;
        this.personalizado = personalizado;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert( String alert ) {
        this.alert = alert;
    }

    public String getSiseva_det() {
        return siseva_det;
    }

    public void setSiseva_det( String siseva_det ) {
        this.siseva_det = siseva_det;
    }

    public String getCur_candado_s() {
        return cur_candado_s;
    }

    public void setCur_candado_s( String cur_candado_s ) {
        this.cur_candado_s = cur_candado_s;
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

    public String getVacantes_sec() {
        return vacantes_sec;
    }

    public void setVacantes_sec( String vacantes_sec ) {
        this.vacantes_sec = vacantes_sec;
    }

    public int getPlancur_id_s() {
        return plancur_id_s;
    }

    public void setPlancur_id_s( int plancur_id_s ) {
        this.plancur_id_s = plancur_id_s;
    }

    public int getP_plancur_id_i() {
        if ( curape_id_aux != 0 ) {
            return p_plancur_id_aux;
        } else {
            return p_plancur_id_i;
        }
    }

    public void setP_plancur_id_i( int p_plancur_id_i ) {
        this.p_plancur_id_i = p_plancur_id_i;
    }

    public void cambiarEfecto() {
        curape_id_aux = 0;
        if ( this.isPersonalizado() ) {
            efecto_aux = "showDiv()";
            this.setEfecto( "showDiv()" );
        } else {
            efecto_aux = "hideDiv({duration:0.7})";
            this.setEfecto( "hideDiv({duration:0.7})" );
        }
        curape_id_aux = -1;
    }

    public List getTablaSecciones() {
        if ( curape_id_aux != 0 ) {
            return view1( detalle1 );
        } else {
            return view1( tablaSecciones );
        }
    }

    public List view1( List<bAperturaCursos> detalle1 ) {
        List<bAperturaCursos> lista = new ArrayList<bAperturaCursos>();

        if ( detalle1 != null ) {
            for ( int j = 0; j < detalle1.size(); j++ ) {
                if ( ( (bAperturaCursos)detalle1.get( j ) ).getActivo_i().equals( "1" ) ) {
                    lista.add( detalle1.get( j ) );
                }
            }
        }
        return lista;
    }

    public void setTablaSecciones( List tablaSecciones ) {
        this.tablaSecciones = tablaSecciones;
    }

    public SelectItem[] getComboSemestres() throws Exception {
        HSSemestreDAO dao = (HSSemestreDAO)ServiceFinder.findBean( "SpringHibernateDaoSemestre" );
        List lista = dao.seleccionarSemestreVigente();
        int num = lista.size();
        comboSemestres = new SelectItem[ 1 ];
        comboSemestres[0] = new SelectItem( 0, "[Seleccione]" );
        if ( lista.size() != 0 ) {
            comboSemestres = new SelectItem[ lista.size() + 1 ];
            comboSemestres[0] = new SelectItem( 0, "[Seleccione]" );
            for ( int i = 0; i < comboSemestres.length - 1; i++ ) {
                comboSemestres[i + 1] = new SelectItem( ( (AcSemestre)lista.get( i ) ).getId(), ( (AcSemestre)lista.get( i ) ).getSemNombre() );
            }
        }
        return comboSemestres;
    }

    public void setComboSemestres( SelectItem[] comboSemestres ) {
        this.comboSemestres = comboSemestres;
    }

    public SelectItem[] getComboPromediosExamenes() throws Exception {
        HSSistemaEvaluacionDAO dao = (HSSistemaEvaluacionDAO)ServiceFinder.findBean( "SpringHibernateDaoSistemaEvaluacion" );
        List lista = dao.seleccionarSistemaEvaluacion( this.getSistemaEvaluacion_i() );
        Set<AcSisEvaDetalle> lista_detalle = new LinkedHashSet<AcSisEvaDetalle>();
        if ( lista.size() != 0 ) {
            lista_detalle = ( (AcSisEvaluacion)lista.get( 0 ) ).getAcSisEvaDetalles();
        }
        List list = Collections.synchronizedList( new LinkedList( lista_detalle ) );
        SelectItem[] cmbPromediosExamenes = new SelectItem[ list.size() ];
        if ( list.size() == 0 ) {
            cmbPromediosExamenes = new SelectItem[ 1 ];
            cmbPromediosExamenes[0] = new SelectItem( 0, "[Seleccione]" );
        } else {
            for ( int i = 0; i < cmbPromediosExamenes.length; i++ ) {
                cmbPromediosExamenes[i] = new SelectItem( ( (AcSisEvaDetalle)list.get( i ) ).getId(), ( (AcSisEvaDetalle)list.get( i ) ).getSisevaDetalleNombre() );
            }
        }
        return cmbPromediosExamenes;
    }

    public void setComboPromediosExamenes( SelectItem[] comboPromediosExamenes ) {
        this.comboPromediosExamenes = comboPromediosExamenes;
    }

    public SelectItem[] getComboSistemaEvaluacion() {
        HSSistemaEvaluacionDAO dao = (HSSistemaEvaluacionDAO)ServiceFinder.findBean( "SpringHibernateDaoSistemaEvaluacion" );
        List lista = dao.seleccionarComboSistemaEvaluacion();
        SelectItem[] cmbSistemaEvaluacion = new SelectItem[ lista.size() + 1 ];
        cmbSistemaEvaluacion[0] = new SelectItem( 0, "[Seleccione]" );
        for ( int i = 0; i < cmbSistemaEvaluacion.length - 1; i++ ) {
            cmbSistemaEvaluacion[i + 1] = new SelectItem( ( (AcSisEvaluacion)lista.get( i ) ).getId(), ( (AcSisEvaluacion)lista.get( i ) ).getSisevaNombre() );
        }
        return cmbSistemaEvaluacion;
    }

    public void setComboSistemaEvaluacion( SelectItem[] comboSistemaEvaluacion ) {
        this.comboSistemaEvaluacion = comboSistemaEvaluacion;
    }

    public int getOrden_i() {
        return orden_i;
    }

    public void setOrden_i( int orden_i ) {
        this.orden_i = orden_i;
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
            System.out.println( "No se pudo cargar el arbol" );
        } catch ( Exception e ) {
            System.out.println( e.getMessage() );
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
        String estructura = "";
        String estructura_facultades = "";
        String estructura_especialidades = "";
        String estructura_plan = "";
        String estructura_ciclo = "";
        HSCatalogoDAO daoCatalogo = (HSCatalogoDAO)ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );
        List<TbCatalogo> catalogoCiclos = daoCatalogo.seleccionarGrupo( "006" );
        HSFacultadDAO daoFacultad = (HSFacultadDAO)ServiceFinder.findBean( "SpringHibernateDaoFacultad" );
        List lFacultad = daoFacultad.seleccionarFacultad( "", "" );

        List<ObjNodeApertura> lNodo = new ArrayList<ObjNodeApertura>();
        for ( int i = 0; i < lFacultad.size(); i++ ) {
            estructura_facultades += ( i + 1 ) + "=" + ( (AcFacultad)lFacultad.get( i ) ).getFacNombre() + "\n";
            Set<AcEspecialidad> esp = new LinkedHashSet<AcEspecialidad>();
            esp = ( (AcFacultad)lFacultad.get( i ) ).getAcEspecialidads();
            List list = Collections.synchronizedList( new LinkedList( esp ) );
            for ( int j = 0; j < list.size(); j++ ) {
                estructura_especialidades += ( i + 1 ) + "." + ( j + 1 ) + "=" + ( (AcEspecialidad)list.get( j ) ).getEspNombre() + "\n";
                Set<AcPlancurricular> plan = new LinkedHashSet<AcPlancurricular>();
                HSPlanCurricularDAO dao0 = (HSPlanCurricularDAO)ServiceFinder.findBean( "SpringHibernateDaoPlanCurricular" );
                List list_plan = new ArrayList();
                list_plan = dao0.seleccionarPlanCurricular( ( (AcEspecialidad)list.get( j ) ).getId() );
                for ( int k = 0; k < list_plan.size(); k++ ) {
                    //System.out.println("***k:" + k);
                    estructura_plan += ( i + 1 ) + "." + ( j + 1 ) + "." + ( k + 1 ) + "=" + ( (AcPlancurricular)list_plan.get( k ) ).getPlanDescripcion() + "\n";
                    Set<AcPlanCurso> plancur = new LinkedHashSet<AcPlanCurso>();
                    plancur = ( (AcPlancurricular)list_plan.get( k ) ).getAcPlanCursos();
                    List pc = groupBy( Collections.synchronizedList( new LinkedList( plancur ) ) );
                    for ( int h = 0; h < pc.size(); h++ ) {
                        for ( int m = 0; m < catalogoCiclos.size(); m++ ) {
                            if ( ( ( (AcPlanCurso)pc.get( h ) ).getPlancurCiclo() ).equals( "006" + catalogoCiclos.get( m ).getCatCodigoElemento() ) ) {
                                estructura_ciclo += ( i + 1 ) + "." + ( j + 1 ) + "." + ( k + 1 ) + "." + ( h + 1 ) + "=" + catalogoCiclos.get( m ).getCatDescripcionElemento() + "\n";
                            }
                        }
//                        List indices = new ArrayList();
//                        indices.add(((AcPlancurricular) list_plan.get(k)).getId());
//                        indices.add(((AcPlanCurso) pc.get(h)).getPlancurCiclo());
//                        indices.add(((AcFacultad) lFacultad.get(i)).getFacNombre());
//                        indices.add(((AcEspecialidad) list.get(j)).getEspNombre());
//                        O[i + 1][j + 1][k + 1][h + 1] = indices;

                        lNodo.add( new ObjNodeApertura( ( i + 1 ) + ":" + ( j + 1 ) + ":" + ( k + 1 ) + ":" + ( h + 1 ),
                                ( (AcFacultad)lFacultad.get( i ) ).getFacNombre(),
                                ( (AcEspecialidad)list.get( j ) ).getEspNombre(),
                                ( (AcPlanCurso)pc.get( h ) ).getPlancurCiclo(),
                                String.valueOf( ( (AcPlancurricular)list_plan.get( k ) ).getId() ),
                                ( (AcEspecialidad)list.get( j ) ).getId() ) );
//                        System.out.println("termino for1");
                    }
//                    System.out.println("termino for2");
                }
//                System.out.println("termino for3");
            }
//            System.out.println("termino for4");
        }
//        System.out.println("termino for5");
        this.setNodos( new ArrayList<ObjNodeApertura>( lNodo ) );
        estructura = estructura_facultades + estructura_especialidades + estructura_plan + estructura_ciclo;
//        System.out.println("estructura" + estructura);
        return estructura;
    }

    public List groupBy( List lista ) {
        List grupo = new ArrayList();
        List resultado = new ArrayList();
        if ( lista.size() != 0 ) {
            String ciclo = ( (AcPlanCurso)lista.get( 0 ) ).getPlancurCiclo();
            llenar( ciclo, grupo, resultado );
            String flag = "0";
            grupo.add( ciclo );
            for ( int i = 0; i < lista.size(); i++ ) {
                ciclo = ( (AcPlanCurso)lista.get( i ) ).getPlancurCiclo();
                flag = "0";
                for ( int j = 0; j < grupo.size(); j++ ) {
                    if ( ciclo.equals( grupo.get( j ) ) ) {
                        flag = "1";
                    }
                }
                if ( flag.equals( "0" ) ) {
                    llenar( ciclo, grupo, resultado );
                }
            }
        }
        return resultado;
    }

    public void llenar( String ciclo, List grupo, List resultado ) {
        grupo.add( ciclo );
        AcPlanCurso plancur = new AcPlanCurso();
        plancur.setPlancurCiclo( ciclo );
        resultado.add( plancur );
    }

    private int buscarNodo( String id ) {
        for ( int i = 0; i < nodos.size(); i++ ) {
            ObjNodeApertura nodo = nodos.get( i );
            if ( nodo.getId().equalsIgnoreCase( id ) ) {
                return i;
            }
        }
        return -1;
    }

    public void Seleccion( NodeSelectedEvent event ) {
        try {
            UITree tree = (UITree)event.getComponent();
            String a = String.valueOf( tree.getRowKey() );
            nodoSeleccionado = (String)tree.getRowData();

            //System.out.println("nodo Seleccionado: " + nodoSeleccionado + " row key " + a + " long " + a.length());
            int length = a.length();
            if ( length >= 7 ) {
                int pos = buscarNodo( a );

                ObjNodeApertura nodo = nodos.get( pos );

                arbol_facultad = nodo.getArbol_facultad();
                arbol_especialidad = nodo.getArbol_especialidad();
                //System.out.println("especiliadad -> "+arbol_especialidad);
                arbol_ciclo = nodo.getArbol_ciclo();
                arbol_plan = nodo.getArbol_plan();
                HSSemestreDAO daoSemestre = (HSSemestreDAO)ServiceFinder.findBean( "SpringHibernateDaoSemestre" );
                semestre_f = Integer.parseInt( daoSemestre.seleccionarSemestreVigente( 0 ) );
                //System.out.println("plan ->  "+arbol_plan+" \t ciclo -> "+arbol_ciclo+"\t especialidad -> "+nodo.getEspId());
                this.setW_espId( nodo.getEspId() );
                this.setW_ciclo( arbol_ciclo );
                MostrarTabla( arbol_plan, arbol_ciclo );
            }
//            int index1 = Integer.parseInt(a.substring(0, 1));
//            int index2 = Integer.parseInt(a.substring(2, 3));
//            int index3 = Integer.parseInt(a.substring(4, 5));
//            int index4 = Integer.parseInt(a.substring(6, 7));
//
//            List lista = (List) O[index1][index2][index3][index4];
//
//            arbol_facultad = lista.get(2).toString();
//            arbol_especialidad = lista.get(3).toString();
//            arbol_ciclo = lista.get(1).toString();
//            arbol_plan = lista.get(0).toString();
//            HSSemestreDAO daoSemestre = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
//            semestre_f = Integer.parseInt(daoSemestre.seleccionarSemestreVigente(0));
//            MostrarTabla(lista.get(0).toString(), lista.get(1).toString());
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void MostrarTabla( String plan, String ciclo ) {
        HSPlanCurricularCursoDAO dao = (HSPlanCurricularCursoDAO)ServiceFinder.findBean( "SpringHibernateDaoPlanCurricularCurso" );
        try {
            List list = dao.seleccionarCursos( plan, ciclo );
            List aperturados = new ArrayList();
            for ( int i = 0; i < list.size(); i++ ) {
                bAperturaCursos apertura = new bAperturaCursos( 0 );
                apertura.setCur_id_s( ( ( (AcPlanCurso)list.get( i ) ).getCur().getId() ) );
                apertura.setCur_ciclo_s( nodoSeleccionado );
                apertura.setPlancur_id_s( ( (AcPlanCurso)list.get( i ) ).getId() );
                // apertura.setCur_codigo_s(((AcPlanCurso) list.get(i)).getCur().getCurCodigo());
                apertura.setCur_codigo_s( ( (AcPlanCurso)list.get( i ) ).getPlancurCodigo() );
                apertura.setW_descarga( leerArchivos( ( (AcPlanCurso)list.get( i ) ).getPlancurCodigo() ) );
                apertura.setCur_nombre_s( ( (AcPlanCurso)list.get( i ) ).getCur().getCurNombre() );
                HSAperturaCursosDAO dao2 = (HSAperturaCursosDAO)ServiceFinder.findBean( "SpringHibernateDaoCursoAperturado" );
                List lista_aperturados = dao2.verificarCursoAperturado( ( ( (AcPlanCurso)list.get( i ) ).getId() ), this.semestre_f );
                if ( lista_aperturados.size() > 0 ) {
                    if ( ( (AcCursoAperturado)lista_aperturados.get( 0 ) ).getCurapeAperturado().equals( "1" ) ) {
                        apertura.setCur_aperturado_s( "Si" );
                        apertura.setCur_candado_s( "/Imagenes/actions/abierto.png" );
                    } else {
                        apertura.setCur_aperturado_s( "No" );
                        apertura.setCur_candado_s( "/Imagenes/actions/cerrado.png" );
                    }
                    HSSistemaEvaluacionDAO dao3 = (HSSistemaEvaluacionDAO)ServiceFinder.findBean( "SpringHibernateDaoSistemaEvaluacion" );
                    List li = dao3.seleccionarSistemaEvaluacion( ( (AcCursoAperturado)lista_aperturados.get( 0 ) ).getSisevaId() );
                    if ( li.size() > 0 ) {
                        apertura.setCur_evaluacion_s( ( (AcSisEvaluacion)li.get( 0 ) ).getSisevaNombre() );
                    } else {
                        apertura.setCur_aperturado_s( "No Asignado" );
                    }
                    apertura.setCur_evaluacion_id_s( "" + ( (AcCursoAperturado)lista_aperturados.get( 0 ) ).getSisevaId() );
                    apertura.setCurape_id_s( ( (AcCursoAperturado)lista_aperturados.get( 0 ) ).getId() );
                } else {

                    apertura.setCur_aperturado_s( "No" );
                    apertura.setCur_candado_s( "/Imagenes/actions/cerrado.png" );
                    apertura.setCur_evaluacion_s( "No Especificado" );
                    apertura.setCur_evaluacion_id_s( "0" );
                    apertura.setCurape_id_s( -1 );
                }
                apertura.setCur_semestre_s( "" + this.semestre_f );
                for ( int w = 0; w < listaSem.size(); w++ ) {

                    if ( ( (AcSemestre)listaSem.get( w ) ).getId().toString().equals( this.semestre_f + "" ) ) {
                        apertura.setCur_semestre_s_d( ( (AcSemestre)listaSem.get( w ) ).getSemCodigo() );

                    }
                }
                aperturados.add( apertura );
            }

            this.setListaCursos( aperturados );
        } catch ( Exception e ) {
            System.out.println( "error de mostrar tabla -> " + e );
        }
    }

    public void Seleccion( ActionEvent event ) {
        try {
            MostrarTabla( "" + this.getArbol_plan(), "" + this.getArbol_ciclo() );
        } catch ( Exception e ) {
        }
    }

    public void Seleccion() {
        try {
            MostrarTabla( "" + this.getArbol_plan(), "" + this.getArbol_ciclo() );
        } catch ( Exception e ) {
        }
    }

    public void Detallar( ActionEvent event ) {
        detalle.clear();
        this.setEfecto( "showDiv()" );
        List a = new ArrayList();
        List b = new ArrayList();
        id_aux = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_id" ) ).getValue().toString() );
        codigo_aux = ( (UIParameter)event.getComponent().findComponent( "p_codigo" ) ).getValue().toString();
        nombre_aux = ( (UIParameter)event.getComponent().findComponent( "p_nombre" ) ).getValue().toString();
        ciclo_aux = ( (UIParameter)event.getComponent().findComponent( "p_ciclo" ) ).getValue().toString();
        curape_id_aux = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_id_curape" ) ).getValue().toString() );
        cur_evaluacion_aux = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_evaluacion" ) ).getValue().toString() );
        p_plancur_id_aux = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_plancur_id" ) ).getValue().toString() );
        if ( ( (UIParameter)event.getComponent().findComponent( "p_aperturado" ) ).getValue().toString().equals( "Si" ) ) {
            apertura_aux = true;
        } else {
            apertura_aux = false;
        }

        this.setSistemaEvaluacion_i( cur_evaluacion_aux );
        HSAperturaCursosDAO dao = (HSAperturaCursosDAO)ServiceFinder.findBean( "SpringHibernateDaoCursoAperturado" );
        List lista = dao.seleccionarUnCursosAperturado( curape_id_aux );
        Set<AcSisEvaPersonalizado> sisevaper = new LinkedHashSet<AcSisEvaPersonalizado>();
        efecto_aux = "hideDiv({duration:0.7})";
        personalizado_aux = false;
        if ( lista.size() != 0 ) {
            sisevaper = ( (AcCursoAperturado)lista.get( 0 ) ).getAcSisEvaPersonalizados();
            List list = Collections.synchronizedList( new LinkedList( sisevaper ) );
            if ( list.size() != 0 ) {
                efecto_aux = "showDiv()";
                personalizado_aux = true;
                bAperturaCursos curape;
                for ( int i = 0; i < list.size(); i++ ) {
                    curape = new bAperturaCursos( 0 );
                    curape.setSiseva_det( "" + cur_evaluacion_aux );
                    curape.setId_det( ( (AcSisEvaPersonalizado)list.get( i ) ).getId() );
                    curape.setCodigo_i( ( (AcSisEvaPersonalizado)list.get( i ) ).getSisevaCodigo() );
                    curape.setNombre_i( ( (AcSisEvaPersonalizado)list.get( i ) ).getSisevaPerNombre() );
                    curape.setPeso_i( "" + ( (AcSisEvaPersonalizado)list.get( i ) ).getSisevaPerPeso() );
                    curape.setPromediosExamenes_i( ( (AcSisEvaPersonalizado)list.get( i ) ).getSisevaDetalle().getId() );
                    curape.setSemana_i( ( (AcSisEvaPersonalizado)list.get( i ) ).getSisevaPerExaSemana() );
                    curape.setOrden_i( ( (AcSisEvaPersonalizado)list.get( i ) ).getSisevaPerOrden() );

                    curape.setAgrupar_i( ( (AcSisEvaPersonalizado)list.get( i ) ).getSisevaPerAgrupar() );
                    curape.setSETipoNota( ( (AcSisEvaPersonalizado)list.get( i ) ).getSisevaPerTipoNota() );
                    curape.setSESemestreEtapa( ( (AcSisEvaPersonalizado)list.get( i ) ).getSisevaPerSemestreEtapa() );

                    curape.setV_tipo_nota( CommonDAO.getTbCatalogoDAO().seleccionarDescripcion( curape.getSETipoNota() ) );
                    curape.setV_semestre_etapa( CommonDAO.getTbCatalogoDAO().seleccionarDescripcion( curape.getSESemestreEtapa() ) );

                    curape.setActivo_i( ( (AcSisEvaPersonalizado)list.get( i ) ).getSisevaPerActivo() );
                    if ( ( (AcSisEvaPersonalizado)list.get( i ) ).getSisevaPerActivo().equals( "1" ) ) {
                        a.add( curape );
                    }
                }
            }
        }
        detalle = a;
        Set<AcSeccion> seccion = new LinkedHashSet<AcSeccion>();
        if ( lista.size() != 0 ) {
            seccion = ( (AcCursoAperturado)lista.get( 0 ) ).getAcSeccions();
            List list1 = Collections.synchronizedList( new LinkedList( seccion ) );
            if ( list1.size() != 0 ) {
                bAperturaCursos curape1;
                for ( int i = 0; i < list1.size(); i++ ) {
                    curape1 = new bAperturaCursos( 0 );
                    curape1.setSiseva_det( "" + cur_evaluacion_aux );
                    curape1.setId_det( ( (AcSeccion)list1.get( i ) ).getId() );
                    curape1.setCodigo_sec( ( (AcSeccion)list1.get( i ) ).getSecCodigo() );
                    curape1.setNombre_sec( ( (AcSeccion)list1.get( i ) ).getSecNombre() );
                    curape1.setVacantes_sec( "" + ( (AcSeccion)list1.get( i ) ).getSecVacantes() );
                    curape1.setActivo_i( ( (AcSeccion)list1.get( i ) ).getSecActivo() );
                    b.add( curape1 );
                }
            }
        }
        detalle1 = b;

    }

    public void setearSistema() {
        curape_id_aux = 0;
        this.setSistemaEvaluacion_i( this.getSistemaEvaluacion_i() );
    }

    public void setearSistema( ActionEvent event ) {
        curape_id_aux = 0;
        cur_evaluacion_aux = this.getSistemaEvaluacion_i();
        this.setSistemaEvaluacion_i( this.getSistemaEvaluacion_i() );
    }

    public void Agregar() {
        int q = 0;
        this.setAlert( "" );
        for ( int i = 0; i < detalle.size(); i++ ) {
            if ( ( ( (bAperturaCursos)detalle.get( i ) ).getActivo_i().equals( "1" ) && !( ( (bAperturaCursos)detalle.get( i ) ).getSiseva_det().equals( "" + this.getSistemaEvaluacion_i() ) ) ) ) {
                q = 1;
            }
        }
        if ( q == 0 ) {
            bAperturaCursos aperturaCursos = new bAperturaCursos( 0 );
            aperturaCursos.setSiseva_det( "" + this.getSistemaEvaluacion_i() );
            aperturaCursos.setId_det( id_det );
            aperturaCursos.setPromediosExamenes_i( this.getPromediosExamenes_i() );
            aperturaCursos.setCodigo_i( this.getCodigo_i() );
            aperturaCursos.setNombre_i( this.getNombre_i() );
            aperturaCursos.setPeso_i( this.getPeso_i() );
            aperturaCursos.setSemana_i( this.getSemana_i() );
            aperturaCursos.setOrden_i( this.getOrden_i() );
            aperturaCursos.setActivo_i( "1" );

            aperturaCursos.setActivo_i( this.getAgrupar_i() );
            aperturaCursos.setSETipoNota( this.getSETipoNota() );
            aperturaCursos.setSESemestreEtapa( this.getSESemestreEtapa() );
            aperturaCursos.setV_tipo_nota( CommonDAO.getTbCatalogoDAO().seleccionarDescripcion( this.getSETipoNota() ) );
            aperturaCursos.setV_semestre_etapa( CommonDAO.getTbCatalogoDAO().seleccionarDescripcion( this.getSESemestreEtapa() ) );

            detalle.add( aperturaCursos );
            this.setTablaDetallePromedios( detalle );
            id_det--;
        } else {
            this.setTablaDetallePromedios( detalle );
            this.setAlert( "alert('No se puede asignar planes diferentes')" );
        }
    }

    public void Quitar( ActionEvent event ) {
        int variable = 0;
        UIParameter id = (UIParameter)event.getComponent().findComponent( "id_det" );
        bAperturaCursos aperturaCursos = new bAperturaCursos( 0 );
        aperturaCursos.setId_det( Integer.parseInt( id.getValue().toString() ) );
        for ( int i = 0; i < detalle.size(); i++ ) {
            if ( Integer.parseInt( id.getValue().toString() ) == ( (bAperturaCursos)detalle.get( i ) ).getId_det() ) {
                if ( Integer.parseInt( id.getValue().toString() ) > 0 ) {
                    ( (bAperturaCursos)detalle.get( i ) ).setActivo_i( "0" );
                } else {
                    detalle.remove( i );
                }
            }
        }
    }

    public void Edit( ActionEvent event ) {
        codigo_i_aux = this.getCodigo_i();
        nombre_i_aux = this.getNombre_i();
        peso_i_aux = this.getPeso_i();

        //agregados nuevo sistema de evaluacion
        agrupar_det_aux = this.getAgrupar_i();
        this.setV_tipo_nota( CommonDAO.getTbCatalogoDAO().seleccionarDescripcion( this.getSETipoNota() ) );
        this.setV_semestre_etapa( CommonDAO.getTbCatalogoDAO().seleccionarDescripcion( this.getSESemestreEtapa() ) );
        tnota_det_aux = this.getV_tipo_nota();
        setapa_det_aux = this.getV_semestre_etapa();

        this.setView( "false" );
        this.setEditable( "true" );
        this.setView_bool( false );
        this.setEditable_bool( true );
        this.setVisible( true );
    }

    public void Aceptar( ActionEvent event ) {
        //agregados nuevo sistema de evaluacion
        this.setV_tipo_nota( CommonDAO.getTbCatalogoDAO().seleccionarDescripcion( this.getSETipoNota() ) );
        this.setV_semestre_etapa( CommonDAO.getTbCatalogoDAO().seleccionarDescripcion( this.getSESemestreEtapa() ) );

        this.setView( "true" );
        this.setEditable( "false" );
        this.setView_bool( true );
        this.setEditable_bool( false );
        this.setVisible( false );
    }

    public void Cancelar( ActionEvent event ) {
        this.setCodigo_i( codigo_i_aux );
        this.setNombre_i( nombre_i_aux );
        this.setPeso_i( peso_i_aux );

        // agregados nuevo sistema de evaluacion
        this.setAgrupar_i( agrupar_det_aux );
        this.setV_tipo_nota( tnota_det_aux );
        this.setV_semestre_etapa( setapa_det_aux );


        this.setView( "true" );
        this.setEditable( "false" );
        this.setView_bool( true );
        this.setEditable_bool( false );
        this.setVisible( false );
    }

    public void Insertar( ActionEvent event ) throws Exception {
        curape_id_aux = 0;
        HSAperturaCursosDAO dao = (HSAperturaCursosDAO)ServiceFinder.findBean( "SpringHibernateDaoCursoAperturado" );
        AcCursoAperturado curape = new AcCursoAperturado();
        //System.out.println("this.getId_curape_u():" + this.getId_curape_u());
        if ( this.getId_curape_u() > 0 ) {
            curape.setId( this.getId_curape_u() );
        }
        curape.setSisevaId( this.getSistemaEvaluacion_i() );
        if ( this.isApertura_i() ) {
            curape.setCurapeAperturado( "1" );
        } else {
            curape.setCurapeAperturado( "0" );
        }
        curape.setCurapeActivo( "1" );
        AcSemestre sem = new AcSemestre();
        sem.setId( semestre_f );
        curape.setSem( sem );
        AcPlanCurso plancur = new AcPlanCurso();
        plancur.setId( this.getP_plancur_id_i() );
        curape.setPlancur( plancur );
        if ( this.isPersonalizado() || detalle.size() > 0 ) {
            Set<AcSisEvaPersonalizado> lista_detalle = new LinkedHashSet<AcSisEvaPersonalizado>();
            for ( int i = 0; i < detalle.size(); i++ ) {
                AcSisEvaPersonalizado sisevaper = new AcSisEvaPersonalizado();
                if ( ( (bAperturaCursos)detalle.get( i ) ).getId_det() > 0 ) {
                    sisevaper.setId( ( (bAperturaCursos)detalle.get( i ) ).getId_det() );
                }
                sisevaper.setSisevaCodigo( ( (bAperturaCursos)detalle.get( i ) ).getCodigo_i() );
                sisevaper.setSisevaPerPeso( ( (bAperturaCursos)detalle.get( i ) ).getPeso_i() );
                sisevaper.setSisevaPerNombre( ( (bAperturaCursos)detalle.get( i ) ).getNombre_i() );
                sisevaper.setSisevaPerActivo( ( (bAperturaCursos)detalle.get( i ) ).getActivo_i() );
                sisevaper.setSisevaPerExaSemana( ( (bAperturaCursos)detalle.get( i ) ).getSemana_i() );
                sisevaper.setSisevaPerOrden( ( (bAperturaCursos)detalle.get( i ) ).getOrden_i() );

                //agrupar nuevo sistema de evaluacion
                sisevaper.setSisevaPerAgrupar( ( (bAperturaCursos)detalle.get( i ) ).getAgrupar_i() );
                sisevaper.setSisevaPerTipoNota( ( (bAperturaCursos)detalle.get( i ) ).getSETipoNota() );
                sisevaper.setSisevaPerSemestreEtapa( ( (bAperturaCursos)detalle.get( i ) ).getSESemestreEtapa() );



                AcSisEvaDetalle sisevadet = new AcSisEvaDetalle();
                sisevadet.setId( ( (bAperturaCursos)detalle.get( i ) ).getPromediosExamenes_i() );
                sisevaper.setSisevaDetalle( sisevadet );
                sisevaper.setCurape( curape );
                lista_detalle.add( sisevaper );
            }
            curape.setAcSisEvaPersonalizados( lista_detalle );
        } else {
            Set<AcSisEvaPersonalizado> lista_detalle = new LinkedHashSet<AcSisEvaPersonalizado>();
            HSSistemaEvaluacionDAO dao1 = (HSSistemaEvaluacionDAO)ServiceFinder.findBean( "SpringHibernateDaoSistemaEvaluacion" );
            List lista = dao1.seleccionarSistemaEvaluacion( this.getSistemaEvaluacion_i() );
            Set<AcSisEvaDetalle> lis = new LinkedHashSet<AcSisEvaDetalle>();
            if ( lista.size() != 0 ) {
                lis = ( (AcSisEvaluacion)lista.get( 0 ) ).getAcSisEvaDetalles();
            }
            List list = Collections.synchronizedList( new LinkedList( lis ) );
            for ( int i = 0; i < list.size(); i++ ) {
                AcSisEvaPersonalizado sisevaper = new AcSisEvaPersonalizado();
                if ( ( (AcSisEvaDetalle)list.get( i ) ).getId() > 0 ) {
                    System.out.println( "los codigos son:" + ( (AcSisEvaDetalle)list.get( i ) ).getId() );

                    //sisevaper.setId(((AcSisEvaDetalle)list.get(i)).getId());
                }
                sisevaper.setSisevaCodigo( ( (AcSisEvaDetalle)list.get( i ) ).getSisevaDetalleCodigo() );
                sisevaper.setSisevaPerPeso( "" + ( (AcSisEvaDetalle)list.get( i ) ).getSisevaDetallePeso() );
                sisevaper.setSisevaPerNombre( ( (AcSisEvaDetalle)list.get( i ) ).getSisevaDetalleNombre() );
                sisevaper.setSisevaPerActivo( ( (AcSisEvaDetalle)list.get( i ) ).getSisevaDetalleActivo() );

                sisevaper.setSisevaPerTipoNota( ( (AcSisEvaDetalle)list.get( i ) ).getSisevaDetalleTipoNota() );
                sisevaper.setSisevaPerAgrupar( ( (AcSisEvaDetalle)list.get( i ) ).getSisevaDetalleAgrupar() );
                sisevaper.setSisevaPerSemestreEtapa( ( (AcSisEvaDetalle)list.get( i ) ).getSisevaDetalleSemestreEtapa() );

                sisevaper.setSisevaPerOrden( i + 1 );
                AcSisEvaDetalle sisevadet = new AcSisEvaDetalle();
                sisevadet.setId( ( (AcSisEvaDetalle)list.get( i ) ).getId() );
                sisevaper.setSisevaDetalle( sisevadet );
                sisevaper.setCurape( curape );

                lista_detalle.add( sisevaper );
            }
            curape.setAcSisEvaPersonalizados( lista_detalle );
        }

        Set<AcSeccion> lista_detalle = new LinkedHashSet<AcSeccion>();
        for ( int i = 0; i < detalle1.size(); i++ ) {
            AcSeccion seccion = new AcSeccion();
            if ( ( (bAperturaCursos)detalle1.get( i ) ).getId_det() > 0 ) {
                seccion.setId( ( (bAperturaCursos)detalle1.get( i ) ).getId_det() );
            }
            seccion.setSecCodigo( ( (bAperturaCursos)detalle1.get( i ) ).getCodigo_sec() );
            seccion.setSecVacantes( Integer.parseInt( ( (bAperturaCursos)detalle1.get( i ) ).getVacantes_sec() ) );
            seccion.setSecNombre( ( (bAperturaCursos)detalle1.get( i ) ).getNombre_sec() );
            seccion.setSecActivo( ( (bAperturaCursos)detalle1.get( i ) ).getActivo_i() );
            seccion.setCurape( curape );
            lista_detalle.add( seccion );
        }
        curape.setAcSeccions( lista_detalle );
        if ( this.getId_curape_u() == -1 ) {
            System.out.println( "Inserta" );
            dao.aperturarCurso( curape );
        } else {
            System.out.println( "Actualiza" );
            dao.actualizarCursoAperturado( curape );
        }
    }

    public void Agregar1() {
        boolean flag = true;
        this.setOncomplete( "" );
        for ( int i = 0; i < detalle1.size(); i++ ) {
            if ( this.getNombre_sec().equals( detalle1.get( i ).getNombre_sec() ) ) {
                flag = false;
                break;
            }
        }
        if ( flag ) {

            bAperturaCursos aperturaCursos = new bAperturaCursos( 0 );
            aperturaCursos.setCodigo_sec( this.getCodigo_sec() );
            aperturaCursos.setId_det( id_det );
            aperturaCursos.setNombre_sec( this.getNombre_sec() );
            aperturaCursos.setVacantes_sec( this.getVacantes_sec() );
            aperturaCursos.setActivo_i( "1" );
            detalle1.add( aperturaCursos );
            this.setTablaSecciones( detalle1 );
            id_det--;
        } else {
            this.setOncomplete( "javascript:alert('La Seccion ya existe')" );
        }
    }

    public void Quitar1( ActionEvent event ) {
        int variable = 0;
        UIParameter id = (UIParameter)event.getComponent().findComponent( "id_det1" );
        bAperturaCursos aperturaCursos = new bAperturaCursos( 0 );
        aperturaCursos.setId_det( Integer.parseInt( id.getValue().toString() ) );
        for ( int i = 0; i < detalle1.size(); i++ ) {
            if ( Integer.parseInt( id.getValue().toString() ) == ( (bAperturaCursos)detalle1.get( i ) ).getId_det() ) {
                if ( Integer.parseInt( id.getValue().toString() ) > 0 ) {
                    ( (bAperturaCursos)detalle1.get( i ) ).setActivo_i( "0" );
                } else {
                    detalle1.remove( i );
                }
            }
        }
    }

    public void Edit1( ActionEvent event ) {
        codigo_i_aux = this.getCodigo_sec();
        nombre_i_aux = this.getNombre_sec();
        peso_i_aux = this.getVacantes_sec();
        this.setView( "false" );
        this.setEditable( "true" );
        this.setView_bool( false );
        this.setEditable_bool( true );
        this.setVisible( true );
    }

    public void Aceptar1( ActionEvent event ) {
        this.setView( "true" );
        this.setEditable( "false" );
        this.setView_bool( true );
        this.setEditable_bool( false );
        this.setVisible( false );
    }

    public void Cancelar1( ActionEvent event ) {
        this.setCodigo_i( codigo_i_aux );
        this.setNombre_i( nombre_i_aux );
        this.setVacantes_sec( peso_i_aux );
        this.setView( "true" );
        this.setEditable( "false" );
        this.setView_bool( true );
        this.setEditable_bool( false );
        this.setVisible( false );
    }

    public String getCur_semestre_s_d() {
        return cur_semestre_s_d;
    }

    public void setCur_semestre_s_d( String cur_semestre_s_d ) {
        this.cur_semestre_s_d = cur_semestre_s_d;
    }

    public List<AcSeccionEspecialidad> getListaSeccionEspecialidad() {
        return listaSeccionEspecialidad;
    }

    public void setListaSeccionEspecialidad( List<AcSeccionEspecialidad> listaSeccionEspecialidad ) {
        this.listaSeccionEspecialidad = listaSeccionEspecialidad;
    }

    public AcSeccionEspecialidad getObjAcSeccionEspecialidad() {
        return objAcSeccionEspecialidad;
    }

    public void setObjAcSeccionEspecialidad( AcSeccionEspecialidad objAcSeccionEspecialidad ) {
        this.objAcSeccionEspecialidad = objAcSeccionEspecialidad;
    }

    public SelectItem[] getCboSeccionEspe() {
        HSSeccionEspecialidadDAO dao = (HSSeccionEspecialidadDAO)ServiceFinder.findBean( "SpringHibernateDaoSeccionEspecialidad" );
        List<AcSeccionEspecialidad> lista = dao.getListarSeccionPorEspecialidadCiclo( this.w_espId, this.w_ciclo );
        if ( lista.size() > 0 ) {
            cboSeccionEspe = new SelectItem[ lista.size() ];
            for ( int i = 0; i < lista.size(); i++ ) {
                cboSeccionEspe[i] = new SelectItem( lista.get( i ).getSecespId(), lista.get( i ).getSecespCodigo() );
            }
        } else {
            cboSeccionEspe = new SelectItem[ 1 ];
            cboSeccionEspe[0] = new SelectItem( "-1", "[-Seleccione-]" );
        }
        return cboSeccionEspe;
    }

    public void setCboSeccionEspe( SelectItem[] cboSeccionEspe ) {
        this.cboSeccionEspe = cboSeccionEspe;
    }

    public int getW_secEspeid() {
        return w_secEspeid;
    }

    public void setW_secEspeid( int w_secEspeid ) {
        this.w_secEspeid = w_secEspeid;
    }

    public String getW_ciclo() {
        return w_ciclo;
    }

    public void setW_ciclo( String w_ciclo ) {
        this.w_ciclo = w_ciclo;
    }

    public int getW_espId() {
        return w_espId;
    }

    public void setW_espId( int w_espId ) {
        this.w_espId = w_espId;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete( String oncomplete ) {
        this.oncomplete = oncomplete;
    }

    public void cargarCboSeccionEspecialidad( ActionEvent event ) {
        HSSeccionEspecialidadDAO dao = (HSSeccionEspecialidadDAO)ServiceFinder.findBean( "SpringHibernateDaoSeccionEspecialidad" );
        this.objAcSeccionEspecialidad = dao.findSeccionEspecialidad( this.getW_secEspeid() );
        this.codigo_sec = this.objAcSeccionEspecialidad.getSecespNombre();
        this.nombre_sec = this.objAcSeccionEspecialidad.getSecespCodigo();
        //System.out.println("codigo -> "+this.codigo_sec+" \t -> "+nombre_sec);
    }

    public boolean leerArchivos( String w_plancurCodigo ) {
        //this.setW_descarga(false);
        boolean flag = false;
        ResourceBundle rb = ResourceBundle.getBundle( "net.uch.message.mensaje" );
        String ruta = rb.getString( "carpeta.syllabus" ) + w_plancurCodigo + ".pdf";
        try {
            // FileInputStream archivo = new FileInputStream(ruta);
            File archivo = new File( ruta );
            flag = archivo.exists();
        } catch ( Exception ex ) {
            flag = false;
            Logger.getLogger( bAperturaCursos.class.getName() ).log( Level.SEVERE, null, ex );
        }
        return flag;
    }

    public void descargarArchivo( ActionEvent event ) {

        String w_nombreArchivo = ( (UIParameter)event.getComponent().findComponent( "p_codigo" ) ).getValue().toString();
        FacesContext context = FacesContext.getCurrentInstance();
        //System.out.println("descargar archivo");
        //System.out.println("entro");
        HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
        try {
            //RutaDao dao = (RutaDao)Class.forName("pe.uch.edu.daoImplement.RutaDaoImpl").newInstance();
            // File file=this.getListaArchivos().get(Integer.parseInt(p_indice_file));
            // System.out.println("archivo -> "+file.getName());
            ResourceBundle rb = ResourceBundle.getBundle( "net.uch.message.mensaje" );
            String ruta = rb.getString( "carpeta.syllabus" ) + w_nombreArchivo + ".pdf";

            //System.out.println("la ruta del archivo de descarga -> "+dao.rutaDocenteMateriales(paSec_id + barra + paString));
            FileInputStream archivo = new FileInputStream( ruta );
            int longitud = archivo.available();
            byte[] datos = new byte[ longitud ];
            OutputStream ouputStream = response.getOutputStream();
            //response.setHeader(ruta, view);
            response.setContentType( "application/pdf" );

            response.setHeader( "Content-Disposition", "attachment;filename=" + w_nombreArchivo + ".pdf" );

            int leido = archivo.read( datos );
            while ( leido >= 0 ) {
                if ( leido > 0 ) {
                    ouputStream.write( datos, 0, leido );
                }
                leido = archivo.read( datos );
            }
            ouputStream.flush();
            ouputStream.close();
            FacesContext.getCurrentInstance().responseComplete();
            archivo.close();
        } catch ( Exception e ) {
            System.out.println( "carpeta de descarga " + e );

        }
    }

    public void listenerSyllabus( UploadEvent event ) throws Exception {
        UploadItem item = event.getUploadItem();
        //System.out.println("item -> " + item.isTempFile());
        //archivoLi=item.getFile();
        //System.out.println("nombre del archivo -> "+this.cur_codigo_s);
        archivoUp.setFile( item.getFile() );
        archivoUp.setData( item.getData() );
        archivoUp.setName( item.getFileName() );
        // System.out.println("getFile() -> "+item.getFile());
        // System.out.println("getData() ->  " + item.getData());
        //System.out.println("archivo Data -> " + archivoUp.getData());
        grabarArchivo( item.getFile(), this.cur_codigo_s );

    }

    public void grabarArchivo( File file, String nom_archivo ) {
        try {
            ResourceBundle rb = ResourceBundle.getBundle( "net.uch.message.mensaje" );
            String ruta = rb.getString( "carpeta.syllabus" ) + nom_archivo + ".pdf";
            String ruta2 = rb.getString( "carpeta.syllabus" );
            crearCarpeta( ruta2 );
            System.out.println( "renombrar archivo -> " + file.renameTo( new File( ruta ) ) );

        } catch ( Exception e ) {
            System.out.println( "error + -as " );
            e.printStackTrace();
        }

    }

    public void crearCarpeta( String filePath ) {
        try {
            System.out.println( "nombre de la carpeta -> " + filePath );
            File dir = new File( filePath );
            if ( dir.exists() ) {
                System.out.println( "carpeta existe" );
            } else {
                dir.mkdirs();
                System.out.println( "carpeta creada con exito" );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}