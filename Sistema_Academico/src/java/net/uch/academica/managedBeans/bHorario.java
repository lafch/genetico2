package net.uch.academica.managedBeans;

import Dao.Generacion_Dao;
import Entidades.Aula;
import Entidades.Curso;
import Entidades.Dia;
import Entidades.Horario;
import Entidades.HorasMaximo;
import Entidades.Profesor;
import Entidades.Profesor_Curso;
import Entidades.Profesor_Disponibilidad;
import Entidades_Aux.Aula_Arreglo;
import Entidades_Aux.Calificacion_Arreglo;
import Entidades_Aux.Curso_Arreglo;
import Entidades_Aux.Dia_Arreglo;
import Entidades_Aux.HorarioGenerado_Arreglo;
import Entidades_Aux.Horario_Arreglo;
import Entidades_Aux.HorasMaximo_Arreglo;
import Entidades_Aux.Individuo_Arreglo;
import Entidades_Aux.ProfesorCurso_Arreglo;
import Entidades_Aux.ProfesorDisponibilidad_Arreglo;
import Entidades_Aux.Profesor_Arreglo;
import Vistas.Ejec1;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIOutput;
import javax.faces.component.UIParameter;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.uch.academica.hibernateSpringDao.HSAperturaCursosDAO;
import net.uch.academica.hibernateSpringDao.HSDocenteCursoDAO;
import net.uch.academica.hibernateSpringDao.HSDocenteDAO;
import net.uch.academica.hibernateSpringDao.HSHorarioDAO;
import net.uch.academica.hibernateSpringDao.HSHorarioGenDAO;
import net.uch.academica.hibernateSpringDao.HSMatriculaDAO;
import net.uch.academica.hibernateSpringDao.HSPlanCurricularDAO;
import net.uch.academica.hibernateSpringDao.HSSeccionDAO;
import net.uch.academica.hibernateSpringDao.HSSeccionEspecialidadDAO;
import net.uch.academica.hibernateSpringDao.HSTurnoDAO;
import net.uch.academica.hibernateSpringDao.HSTurnoDetalleDAO;
import net.uch.academica.managedBeans.beans.BeanDocente;
import net.uch.academica.managedBeans.beans.BeanHorarioDispDoc;
import net.uch.academica.managedBeans.beans.CursoDocenteBean;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.managedBeans.beans.BeanReporte;
import net.uch.mapping.AcAula;
import net.uch.mapping.AcCursoAperturado;
import net.uch.mapping.AcDocente;
import net.uch.mapping.AcDocenteCurso;
import net.uch.mapping.AcEspecialidad;
import net.uch.mapping.AcFacultad;
import net.uch.mapping.AcHorario;
import net.uch.mapping.AcHorarioDispDocente;
import net.uch.mapping.AcHorarioGen;
import net.uch.mapping.AcPlancurricular;
import net.uch.mapping.AcSeccion;
import net.uch.mapping.AcSeccionEspecialidad;
import net.uch.mapping.AcSemestre;
import net.uch.mapping.AcTurno;
import net.uch.mapping.AcTurnoDetalle;
import net.uch.mapping.TbCatalogo;
import net.uch.tablaSistema.hibernateSpringDao.HSAulaDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSEspecialidadDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSFacultadDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSSemestreDAO;
import net.uch.tablaSistema.managedBeans.bUsuario;
import net.uch.util.CommonDAO;
import net.uch.util.ObjNodeGnral;
import net.uch.util.Reporte;
import org.richfaces.component.UITree;
import org.richfaces.event.NodeSelectedEvent;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;

public class bHorario {

    private TreeNode arbol;
    private String nodoSeleccionado;
    private List listaSecciones;
    private int id_sem;
    private int id_sec;
    private String codigo_sec;
    private String nombre_sec;
    private int vacantes_sec;
//    private String cur_ape_nombre;
    private int id_sem_horario;
    private List tablaHorario;
    private List tablaHorarioGenerado;
    private int id_hor;
    private String inicio_hor;
    private String fin_hor;
    private String lunes_hor = "";
    private String martes_hor = "";
    private String miercoles_hor = "";
    private String jueves_hor = "";
    private String viernes_hor = "";
    private String sabado_hor = "";
    private String domingo_hor = "";

    private String lunes_hor_tp = "";
    private String martes_hor_tp = "";
    private String miercoles_hor_tp = "";
    private String jueves_hor_tp = "";
    private String viernes_hor_tp = "";
    private String sabado_hor_tp = "";
    private String domingo_hor_tp = "";

    private int id_sec_horario;
    private SelectItem[] comboTurnos;
    private int turno_id;
    private int turnoGeneracion_id;
    public SelectItem[] comboAulas;

    public SelectItem[] comboTipos;
    public SelectItem[] comboTiposEditar;
    public SelectItem[] comboLaboratorio;
    public int aula_id;
    public int lab_id;
    public String tipo_id;
    public String tipo_idEditar;
    public int hordia_id;
    public int hordia_idEliminar;
    public int hordia_idAux;
    public int docente_id;
    public SelectItem[] comboDocentes;
    private String oncomplete;
    public int indice_id_lun = 0;
    public int indice_id_mar = 0;
    public int indice_id_mie = 0;
    public int indice_id_jue = 0;
    public int indice_id_vie = 0;
    public int indice_id_sab = 0;
    public int indice_id_dom = 0;

    private String vacantesOcupadas;
    private List<ObjNodeGnral> nodos;
    private String n_cur_aperturado;
    //############################################
    public static int ide = 0;
    public static List<AcHorario> listaHorario = new ArrayList<AcHorario>();
    public static List<AcHorario> listaHorarioEditar;
    public static List<AcHorario> listaHorarioEliminar = new ArrayList<AcHorario>();
//    public int aula_id_aux;
//    public String tipo_id_aux;
//    public int docente_id_aux;
//    private int tur_id_aux;
    //############################################
    private static int seccion_id_aux;
    private static int semestre_id_aux;
    //agregados para generador de horarios
    private SelectItem[] comboFacultades;
    private SelectItem[] comboEspecialidades;
    private SelectItem[] comboCiclos;
    private SelectItem[] comboSemestres;
    private SelectItem[] comboTurnos2;
    private SelectItem[] cmbTurnoDisponibilidad;
    private SelectItem[] cboSeccionEspe;
    private int b_facultad;
    private int b_especialidad;
    private String b_ciclos;
    private int b_cursos;
    private int b_facultad_u;
    private int b_especialidad_u;
    public int b_id_u;
    private String b_especialidad_descripcion;
    private String b_facultad_descripcion;
    private String b_ciclos_descripcion;
    private String b_cursos_descripcion;
    private boolean personalizado = true;
    private String efecto;
    int semestre_f;
    private int w_secEspeid = 0;
    //progress bar generacion de horarios
    private boolean buttonRendered = true;
    private boolean enabled = false;
    private Long startTime;

    private int idSemestre;
    private int idFacultad;
    private boolean vistaArbolTabla = false;
    private SelectItem[] cboSemestres;
    private SelectItem[] cboFacultadesPrincipal;
    private List<AcDocente> lstDocenteGeneracion = new ArrayList<AcDocente>();
    private List<BeanDocente> lstDocDisp = new ArrayList<BeanDocente>();
    private int doc_id;
    private String doc_detalle;
    private int turnoDisponibilidad_id;
    private List tablaHorarioDisponibilidad;

    private BeanHorarioDispDoc horaria;
    private List<BeanHorarioDispDoc> nListaHoraria;
    private List<BeanHorarioDispDoc> qListaHoraria;
    private static int doc_id_aux;

    public int indice_id_lun_dis = 0;
    public int indice_id_mar_dis = 0;
    public int indice_id_mie_dis = 0;
    public int indice_id_jue_dis = 0;
    public int indice_id_vie_dis = 0;
    public int indice_id_sab_dis = 0;
    public int indice_id_dom_dis = 0;

    private int id_hor_dis;
    private String inicio_hor_dis;
    private String fin_hor_dis;
    private String lunes_hor_dis = "";
    private String martes_hor_dis = "";
    private String miercoles_hor_dis = "";
    private String jueves_hor_dis = "";
    private String viernes_hor_dis = "";
    private String sabado_hor_dis = "";
    private String domingo_hor_dis = "";
    private int id_doc_horario;
    private String bColorCelda_Lunes = "";
    private String bColorCelda_Martes = "";
    private String bColorCelda_Miercoles = "";
    private String bColorCelda_Jueves = "";
    private String bColorCelda_Viernes = "";
    private String bColorCelda_Sabado = "";
    private String bColorCelda_Domingo = "";

    private String bColorCeldaGenerado_Lunes = "";
    private String bColorCeldaGenerado_Martes = "";
    private String bColorCeldaGenerado_Miercoles = "";
    private String bColorCeldaGenerado_Jueves = "";
    private String bColorCeldaGenerado_Viernes = "";
    private String bColorCeldaGenerado_Sabado = "";
    private String bColorCeldaGenerado_Domingo = "";
    private String bColorCeldaGenerado = "background-color:#FFF; color:#000";
    private final String parametroMensajeDisponibilidadDocente = "X";

    private String bColorCeldaHorario_Lunes = "";
    private String bColorCeldaHorario_Martes = "";
    private String bColorCeldaHorario_Miercoles = "";
    private String bColorCeldaHorario_Jueves = "";
    private String bColorCeldaHorario_Viernes = "";
    private String bColorCeldaHorario_Sabado = "";
    private String bColorCeldaHorario_Domingo = "";

    //Detalle de cursoDocente
    private CursoDocenteBean docenteCurso;
    private List<CursoDocenteBean> nLstDocenteCurso;
    private List<CursoDocenteBean> qLstDocenteCurso;
    List<Integer> lstIntegers;

    //cmbAulas
    private int b_aulas;
    public SelectItem[] comboAulasDisponibles;
    // combo de generaciones 
    private int b_generacion;
    private SelectItem[] cboGeneraciones;
    private boolean verGeneracion = false;

    private int icolor = 0;
    private int prof = 0;

    private int n_NoAperturados;
    private int n_Aperturados;
    private boolean estadoAperturado;

    private int n_NoAsignado;
    private int n_Asignado;
    private boolean estadoAsignado;

    private int n_NoDocDisponible;
    private int n_DocDisponible;
    private int n_CurDisponibilidad;
    private boolean estadoDocDisponible;
    private boolean estadoCurDisponible;

    private final String VARIABLE_TURNO_MANIANA = "TURNO MAÑANA";
    private final String VARIABLE_TURNO_NOCHE = "TURNO NOCHE";

    private String valorRep = "";
    private String tituloReporte = "";

    private String imagenDisDocente = "/Imagenes/actions/horario_gris.png";
    private int iCurso;
    private int iEspecialidad;
    private int iSemestre;

    private boolean b_horarioEstablecidoLunes = false;
    private boolean b_horarioEstablecidoMartes = false;
    private boolean b_horarioEstablecidoMiercoles = false;
    private boolean b_horarioEstablecidoJueves = false;
    private boolean b_horarioEstablecidoViernes = false;
    private boolean b_horarioEstablecidoSabado = false;
    private boolean b_horarioEstablecidoDomingo = false;

    private boolean b_horarioNuevoLunes = false;
    private boolean b_horarioNuevoMartes = false;
    private boolean b_horarioNuevoMiercoles = false;
    private boolean b_horarioNuevoJueves = false;
    private boolean b_horarioNuevoViernes = false;
    private boolean b_horarioNuevoSabado = false;
    private boolean b_horarioNuevoDomingo = false;

    private boolean b_verdadero = true;
    private boolean b_falso = false;

    private final String VARIABLE_HORARIO_NUEVO_ASIGNADO = "ASIGNADO";

    public bHorario() {
    }

    public boolean isB_verdadero() {
        return b_verdadero;
    }

    public void setB_verdadero(boolean b_verdadero) {
        this.b_verdadero = b_verdadero;
    }

    public boolean isB_falso() {
        return b_falso = false;
    }

    public void setB_falso(boolean b_falso) {
        this.b_falso = b_falso;
    }

    public boolean isB_horarioEstablecidoLunes() {
        return b_horarioEstablecidoLunes;
    }

    public void setB_horarioEstablecidoLunes(boolean b_horarioEstablecidoLunes) {
        this.b_horarioEstablecidoLunes = b_horarioEstablecidoLunes;
    }

    public boolean isB_horarioEstablecidoMartes() {
        return b_horarioEstablecidoMartes;
    }

    public void setB_horarioEstablecidoMartes(boolean b_horarioEstablecidoMartes) {
        this.b_horarioEstablecidoMartes = b_horarioEstablecidoMartes;
    }

    public boolean isB_horarioEstablecidoMiercoles() {
        return b_horarioEstablecidoMiercoles;
    }

    public void setB_horarioEstablecidoMiercoles(boolean b_horarioEstablecidoMiercoles) {
        this.b_horarioEstablecidoMiercoles = b_horarioEstablecidoMiercoles;
    }

    public boolean isB_horarioEstablecidoJueves() {
        return b_horarioEstablecidoJueves;
    }

    public void setB_horarioEstablecidoJueves(boolean b_horarioEstablecidoJueves) {
        this.b_horarioEstablecidoJueves = b_horarioEstablecidoJueves;
    }

    public boolean isB_horarioEstablecidoViernes() {
        return b_horarioEstablecidoViernes;
    }

    public void setB_horarioEstablecidoViernes(boolean b_horarioEstablecidoViernes) {
        this.b_horarioEstablecidoViernes = b_horarioEstablecidoViernes;
    }

    public boolean isB_horarioEstablecidoSabado() {
        return b_horarioEstablecidoSabado;
    }

    public void setB_horarioEstablecidoSabado(boolean b_horarioEstablecidoSabado) {
        this.b_horarioEstablecidoSabado = b_horarioEstablecidoSabado;
    }

    public boolean isB_horarioEstablecidoDomingo() {
        return b_horarioEstablecidoDomingo;
    }

    public void setB_horarioEstablecidoDomingo(boolean b_horarioEstablecidoDomingo) {
        this.b_horarioEstablecidoDomingo = b_horarioEstablecidoDomingo;
    }

    public boolean isB_horarioNuevoLunes() {
        return b_horarioNuevoLunes;
    }

    public void setB_horarioNuevoLunes(boolean b_horarioNuevoLunes) {
        this.b_horarioNuevoLunes = b_horarioNuevoLunes;
    }

    public boolean isB_horarioNuevoMartes() {
        return b_horarioNuevoMartes;
    }

    public void setB_horarioNuevoMartes(boolean b_horarioNuevoMartes) {
        this.b_horarioNuevoMartes = b_horarioNuevoMartes;
    }

    public boolean isB_horarioNuevoMiercoles() {
        return b_horarioNuevoMiercoles;
    }

    public void setB_horarioNuevoMiercoles(boolean b_horarioNuevoMiercoles) {
        this.b_horarioNuevoMiercoles = b_horarioNuevoMiercoles;
    }

    public boolean isB_horarioNuevoJueves() {
        return b_horarioNuevoJueves;
    }

    public void setB_horarioNuevoJueves(boolean b_horarioNuevoJueves) {
        this.b_horarioNuevoJueves = b_horarioNuevoJueves;
    }

    public boolean isB_horarioNuevoViernes() {
        return b_horarioNuevoViernes;
    }

    public void setB_horarioNuevoViernes(boolean b_horarioNuevoViernes) {
        this.b_horarioNuevoViernes = b_horarioNuevoViernes;
    }

    public boolean isB_horarioNuevoSabado() {
        return b_horarioNuevoSabado;
    }

    public void setB_horarioNuevoSabado(boolean b_horarioNuevoSabado) {
        this.b_horarioNuevoSabado = b_horarioNuevoSabado;
    }

    public boolean isB_horarioNuevoDomingo() {
        return b_horarioNuevoDomingo;
    }

    public void setB_horarioNuevoDomingo(boolean b_horarioNuevoDomingo) {
        this.b_horarioNuevoDomingo = b_horarioNuevoDomingo;
    }

    public int getiCurso() {
        return iCurso;
    }

    public void setiCurso(int iCurso) {
        this.iCurso = iCurso;
    }

    public int getiEspecialidad() {
        return iEspecialidad;
    }

    public void setiEspecialidad(int iEspecialidad) {
        this.iEspecialidad = iEspecialidad;
    }

    public int getiSemestre() {
        return iSemestre;
    }

    public void setiSemestre(int iSemestre) {
        this.iSemestre = iSemestre;
    }

    public void cargarDisponibilidad() throws Exception {
        HSDocenteDAO daoDOCHor = CommonDAO.getAcDocenteDAO();
        HSHorarioDAO dao = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoHorario");
        if (daoDOCHor.seleccionarHorario(this.getDocente_id()).isEmpty()) {
            this.setImagenDisDocente("/Imagenes/actions/horario_gris.png");
        } else {
            this.setImagenDisDocente("/Imagenes/actions/horario.png");
        }
        if (seccion_id_aux != 0) {
            String horas = "";
            List<BeanReporte> rptHorasDisponible = dao.numeroHorasDisponiblesSeccion(seccion_id_aux);
            for (BeanReporte beanReporte : rptHorasDisponible) {
                horas = beanReporte.getExpr1();
            }
            if (Integer.parseInt(horas) > 0) {
                this.setOncomplete("javascript:alert('Horas Del Curso No Asignadas:" + horas + "')");
                setearTurno();
            } else {
                this.setOncomplete("javascript:alert('DEBE ELIMINAR ALGUN REGISTRO DEL CURSO PARA PODER ASIGNAR')");
            }
        }

    }

    public String getImagenDisDocente() {
        return imagenDisDocente;
    }

    public void setImagenDisDocente(String imagenDisDocente) {
        this.imagenDisDocente = imagenDisDocente;
    }

    //reportes
    public String getTituloReporte() {
        return tituloReporte;
    }

    public void setTituloReporte(String tituloReporte) {
        this.tituloReporte = tituloReporte;
    }

    public String getValorRep() {
        return valorRep;
    }

    public void setValorRep(String valorRep) {
        this.valorRep = valorRep;
    }

    //reportes de cursos aperturados
    public int getN_NoAperturados() {
        return n_NoAperturados;
    }

    public void setN_NoAperturados(int n_NoAperturados) {
        this.n_NoAperturados = n_NoAperturados;
    }

    public int getN_Aperturados() {
        return n_Aperturados;
    }

    public void setN_Aperturados(int n_Aperturados) {
        this.n_Aperturados = n_Aperturados;
    }

    public boolean isEstadoAperturado() {
        return estadoAperturado;
    }

    public void setEstadoAperturado(boolean estadoAperturado) {
        this.estadoAperturado = estadoAperturado;
    }

    public int getN_NoAsignado() {
        return n_NoAsignado;
    }

    public void setN_NoAsignado(int n_NoAsignado) {
        this.n_NoAsignado = n_NoAsignado;
    }

    public int getN_Asignado() {
        return n_Asignado;
    }

    public void setN_Asignado(int n_Asignado) {
        this.n_Asignado = n_Asignado;
    }

    public boolean isEstadoAsignado() {
        return estadoAsignado;
    }

    public void setEstadoAsignado(boolean estadoAsignado) {
        this.estadoAsignado = estadoAsignado;
    }

    public int getN_NoDocDisponible() {
        return n_NoDocDisponible;
    }

    public void setN_NoDocDisponible(int n_NoDocDisponible) {
        this.n_NoDocDisponible = n_NoDocDisponible;
    }

    public int getN_DocDisponible() {
        return n_DocDisponible;
    }

    public void setN_DocDisponible(int n_DocDisponible) {
        this.n_DocDisponible = n_DocDisponible;
    }

    public int getN_CurDisponibilidad() {
        return n_CurDisponibilidad;
    }

    public void setN_CurDisponibilidad(int n_CurDisponibilidad) {
        this.n_CurDisponibilidad = n_CurDisponibilidad;
    }

    public boolean isEstadoDocDisponible() {
        return estadoDocDisponible;
    }

    public void setEstadoDocDisponible(boolean estadoDocDisponible) {
        this.estadoDocDisponible = estadoDocDisponible;
    }

    public boolean isEstadoCurDisponible() {
        return estadoCurDisponible;
    }

    public void setEstadoCurDisponible(boolean estadoCurDisponible) {
        this.estadoCurDisponible = estadoCurDisponible;
    }

    public void ImprimirCursoApertudo(ActionEvent event) throws Exception {
        if (this.getN_Aperturados() > 0) {
            this.setTituloReporte("Reporte Cursos Aperturado");
            this.setValorRep("ficha");
            this.setOncomplete("javascript:Richfaces.showModalPanel('mp8');");
        } else {
            this.setOncomplete("javascript:alert('NO SE ENCONTRO REGISTROS');");
        }
    }

    public void ImprimirCursoNoApertudo(ActionEvent event) throws Exception {
        if (this.getN_NoAperturados() > 0) {
            this.setTituloReporte("Reporte Cursos No Aperturado");
            this.setValorRep("ficha");
            this.setOncomplete("javascript:Richfaces.showModalPanel('mp7');");
        } else {
            this.setOncomplete("javascript:alert('NO SE ENCONTRO REGISTROS');");
        }
    }

    public void ImprimirCursoAsignado(ActionEvent event) throws Exception {
        if (this.getN_Asignado() > 0) {
            this.setTituloReporte("Reporte Cursos Asignado");
            this.setValorRep("ficha");
            this.setOncomplete("javascript:Richfaces.showModalPanel('mp9');");
        } else {
            this.setOncomplete("javascript:alert('NO SE ENCONTRO REGISTROS');");
        }
    }

    public void ImprimirCursoNoAsignado(ActionEvent event) throws Exception {
        if (this.getN_NoAsignado() > 0) {
            this.setTituloReporte("Reporte Cursos No Asignado");
            this.setValorRep("ficha");
            this.setOncomplete("javascript:Richfaces.showModalPanel('mp10');");
        } else {
            this.setOncomplete("javascript:alert('NO SE ENCONTRO REGISTROS');");
        }
    }

    public void ImprimirDocDisponible(ActionEvent event) throws Exception {
        if (this.getN_DocDisponible() > 0) {
            this.setTituloReporte("Reporte Docentes con disponibilidad");
            this.setValorRep("ficha");
            this.setOncomplete("javascript:Richfaces.showModalPanel('mp11');");
        } else {
            this.setOncomplete("javascript:alert('NO SE ENCONTRO REGISTROS');");
        }
    }

    public void ImprimirNoDocDisponible(ActionEvent event) throws Exception {
        if (this.getN_NoDocDisponible() > 0) {
            this.setTituloReporte("Reporte Docentenes sin disponibilidad");
            this.setValorRep("ficha");
            this.setOncomplete("javascript:Richfaces.showModalPanel('mp12');");
        } else {
            this.setOncomplete("javascript:alert('NO SE ENCONTRO REGISTROS');");
        }

    }

    public void ImprimirCursosSinDisponibilidad(ActionEvent event) throws Exception {
        if (this.getN_CurDisponibilidad() > 0) {
            this.setTituloReporte("Reporte Cursos Sin Disponibilidad de Turno");
            this.setValorRep("ficha");
            this.setOncomplete("javascript:Richfaces.showModalPanel('mp13');");
        } else {
            this.setOncomplete("javascript:alert('NO SE ENCONTRO REGISTROS');");
        }

    }

    public void cargarReporteAperturado(OutputStream out, Object data) throws IOException, Exception, EOFException {
        if (estadoAperturado) {
            int semestreParametro = this.getSemestre_f(); //semestre combo
            int especialidadParametro = this.getB_especialidad(); // especialidad combo
            int turnoParametro = this.getTurnoGeneracion_id(); // turno combo
            String cicloParametro = this.getB_ciclos(); // ciclo combo
            String turnoDescParametro = obtenerTurno(turnoParametro);
            if (!turnoDescParametro.equals("NULO")) {
                Reporte reporte = new Reporte();
                HashMap parametros = new HashMap();
                parametros.put("P_CICLO", cicloParametro);
                parametros.put("P_SEMESTRE", semestreParametro);
                parametros.put("P_DESTURNO", turnoDescParametro);
                parametros.put("P_ESPECIALIDAD", especialidadParametro);
                parametros.put("P_ESTADO", "1");
                reporte.cargarReporte(out, data, parametros, "generacionCursoAperturado.jasper");
            }
        }
    }

    public void cargarReporteNoAperturado(OutputStream out, Object data) throws IOException, Exception, EOFException {
        if (estadoAperturado) {
            Integer semestreParametro = this.getSemestre_f(); //semestre combo
            Integer especialidadParametro = this.getB_especialidad(); // especialidad combo
            Integer turnoParametro = this.getTurnoGeneracion_id(); // turno combo
            String cicloParametro = this.getB_ciclos(); // ciclo combo
            String turnoDescParametro = obtenerTurno(turnoParametro);
            if (!turnoDescParametro.equals("NULO")) {
                Reporte reporte = new Reporte();
                HashMap parametros = new HashMap();
                parametros.put("P_CICLO", cicloParametro);
                parametros.put("P_SEMESTRE", semestreParametro);
                parametros.put("P_DESTURNO", turnoDescParametro);
                parametros.put("P_ESPECIALIDAD", especialidadParametro);
                parametros.put("P_ESTADO", "0");
                reporte.cargarReporte(out, data, parametros, "generacionCursoAperturado.jasper");
            }
        }
    }

    public void cargarReporteAsignado(OutputStream out, Object data) throws IOException, Exception, EOFException {
        if (estadoAsignado) {
            int semestreParametro = this.getSemestre_f(); //semestre combo
            int especialidadParametro = this.getB_especialidad(); // especialidad combo
            int turnoParametro = this.getTurnoGeneracion_id(); // turno combo
            String cicloParametro = this.getB_ciclos(); // ciclo combo
            String turnoDescParametro = obtenerTurno(turnoParametro);
            if (!turnoDescParametro.equals("NULO")) {
                Reporte reporte = new Reporte();
                HashMap parametros = new HashMap();
                parametros.put("P_CICLO", cicloParametro);
                parametros.put("P_SEMESTRE", semestreParametro);
                parametros.put("P_DESTURNO", turnoDescParametro);
                parametros.put("P_ESPECIALIDAD", especialidadParametro);
                parametros.put("P_ESTADO", 1);
                reporte.cargarReporte(out, data, parametros, "generacionCursoAsignado.jasper");
            }
        }
    }

    public void cargarReporteNoAsignado(OutputStream out, Object data) throws IOException, Exception, EOFException {
        if (estadoAsignado) {
            Integer semestreParametro = this.getSemestre_f(); //semestre combo
            Integer especialidadParametro = this.getB_especialidad(); // especialidad combo
            Integer turnoParametro = this.getTurnoGeneracion_id(); // turno combo
            String cicloParametro = this.getB_ciclos(); // ciclo combo
            String turnoDescParametro = obtenerTurno(turnoParametro);
            if (!turnoDescParametro.equals("NULO")) {
                Reporte reporte = new Reporte();
                HashMap parametros = new HashMap();
                parametros.put("P_CICLO", cicloParametro);
                parametros.put("P_SEMESTRE", semestreParametro);
                parametros.put("P_DESTURNO", turnoDescParametro);
                parametros.put("P_ESPECIALIDAD", especialidadParametro);
                parametros.put("P_ESTADO", 0);
                reporte.cargarReporte(out, data, parametros, "generacionCursoAsignado.jasper");
            }
        }
    }

    public void cargarReporteDisDocente(OutputStream out, Object data) throws IOException, Exception, EOFException {
        if (estadoDocDisponible) {
            int semestreParametro = this.getSemestre_f(); //semestre combo
            int especialidadParametro = this.getB_especialidad(); // especialidad combo
            int turnoParametro = this.getTurnoGeneracion_id(); // turno combo
            String cicloParametro = this.getB_ciclos(); // ciclo combo
            String turnoDescParametro = obtenerTurno(turnoParametro);
            if (!turnoDescParametro.equals("NULO")) {
                Reporte reporte = new Reporte();
                HashMap parametros = new HashMap();
                parametros.put("P_CICLO", cicloParametro);
                parametros.put("P_SEMESTRE", semestreParametro);
                parametros.put("P_DESTURNO", turnoDescParametro);
                parametros.put("P_TURNO", turnoParametro);
                parametros.put("P_ESPECIALIDAD", especialidadParametro);
                parametros.put("P_ESTADO", 1);
                reporte.cargarReporte(out, data, parametros, "generacionDisponibleDocente.jasper");
            }
        }
    }

    public void cargarReporteCursoSinDisp(OutputStream out, Object data) throws IOException, Exception, EOFException {
        if (estadoCurDisponible) {
            int semestreParametro = this.getSemestre_f(); //semestre combo
            int especialidadParametro = this.getB_especialidad(); // especialidad combo
            int turnoParametro = this.getTurnoGeneracion_id(); // turno combo
            String cicloParametro = this.getB_ciclos(); // ciclo combo
            String turnoDescParametro = obtenerTurno(turnoParametro);
            if (!turnoDescParametro.equals("NULO")) {
                Reporte reporte = new Reporte();
                HashMap parametros = new HashMap();
                parametros.put("P_CICLO", cicloParametro);
                parametros.put("P_SEMESTRE", semestreParametro);
                parametros.put("P_DESTURNO", turnoDescParametro);
                parametros.put("P_TURNO", turnoParametro);
                parametros.put("P_ESPECIALIDAD", especialidadParametro);
                parametros.put("P_ESTADO", 1);
                reporte.cargarReporte(out, data, parametros, "cursosSinDisponibilidad.jasper");
            }
        }
    }

    public void cargarReporteNoDisDocente(OutputStream out, Object data) throws IOException, Exception, EOFException {
        if (estadoDocDisponible) {
            Integer semestreParametro = this.getSemestre_f(); //semestre combo
            Integer especialidadParametro = this.getB_especialidad(); // especialidad combo
            Integer turnoParametro = this.getTurnoGeneracion_id(); // turno combo
            String cicloParametro = this.getB_ciclos(); // ciclo combo
            String turnoDescParametro = obtenerTurno(turnoParametro);
            if (!turnoDescParametro.equals("NULO")) {
                Reporte reporte = new Reporte();
                HashMap parametros = new HashMap();
                parametros.put("P_CICLO", cicloParametro);
                parametros.put("P_SEMESTRE", semestreParametro);
                parametros.put("P_DESTURNO", turnoDescParametro);
                parametros.put("P_TURNO", turnoParametro);
                parametros.put("P_ESPECIALIDAD", especialidadParametro);
                parametros.put("P_ESTADO", 0);
                reporte.cargarReporte(out, data, parametros, "generacionDisponibleDocente.jasper");
            }
        }
    }

    //colores de horario oficial
    public String getbColorCeldaHorario_Lunes() {
        return bColorCeldaHorario_Lunes;
    }

    public void setbColorCeldaHorario_Lunes(String bColorCeldaHorario_Lunes) {
        this.bColorCeldaHorario_Lunes = bColorCeldaHorario_Lunes;
    }

    public String getbColorCeldaHorario_Martes() {
        return bColorCeldaHorario_Martes;
    }

    public void setbColorCeldaHorario_Martes(String bColorCeldaHorario_Martes) {
        this.bColorCeldaHorario_Martes = bColorCeldaHorario_Martes;
    }

    public String getbColorCeldaHorario_Miercoles() {
        return bColorCeldaHorario_Miercoles;
    }

    public void setbColorCeldaHorario_Miercoles(String bColorCeldaHorario_Miercoles) {
        this.bColorCeldaHorario_Miercoles = bColorCeldaHorario_Miercoles;
    }

    public String getbColorCeldaHorario_Jueves() {
        return bColorCeldaHorario_Jueves;
    }

    public void setbColorCeldaHorario_Jueves(String bColorCeldaHorario_Jueves) {
        this.bColorCeldaHorario_Jueves = bColorCeldaHorario_Jueves;
    }

    public String getbColorCeldaHorario_Viernes() {
        return bColorCeldaHorario_Viernes;
    }

    public void setbColorCeldaHorario_Viernes(String bColorCeldaHorario_Viernes) {
        this.bColorCeldaHorario_Viernes = bColorCeldaHorario_Viernes;
    }

    public String getbColorCeldaHorario_Sabado() {
        return bColorCeldaHorario_Sabado;
    }

    public void setbColorCeldaHorario_Sabado(String bColorCeldaHorario_Sabado) {
        this.bColorCeldaHorario_Sabado = bColorCeldaHorario_Sabado;
    }

    public String getbColorCeldaHorario_Domingo() {
        return bColorCeldaHorario_Domingo;
    }

    public void setbColorCeldaHorario_Domingo(String bColorCeldaHorario_Domingo) {
        this.bColorCeldaHorario_Domingo = bColorCeldaHorario_Domingo;
    }

    // generacion encontradas
    public String getbColorCeldaGenerado_Lunes() {
        return bColorCeldaGenerado_Lunes;
    }

    public void setbColorCeldaGenerado_Lunes(String bColorCeldaGenerado_Lunes) {
        this.bColorCeldaGenerado_Lunes = bColorCeldaGenerado_Lunes;
    }

    public String getbColorCeldaGenerado_Martes() {
        return bColorCeldaGenerado_Martes;
    }

    public void setbColorCeldaGenerado_Martes(String bColorCeldaGenerado_Martes) {
        this.bColorCeldaGenerado_Martes = bColorCeldaGenerado_Martes;
    }

    public String getbColorCeldaGenerado_Miercoles() {
        return bColorCeldaGenerado_Miercoles;
    }

    public void setbColorCeldaGenerado_Miercoles(String bColorCeldaGenerado_Miercoles) {
        this.bColorCeldaGenerado_Miercoles = bColorCeldaGenerado_Miercoles;
    }

    public String getbColorCeldaGenerado_Jueves() {
        return bColorCeldaGenerado_Jueves;
    }

    public void setbColorCeldaGenerado_Jueves(String bColorCeldaGenerado_Jueves) {
        this.bColorCeldaGenerado_Jueves = bColorCeldaGenerado_Jueves;
    }

    public String getbColorCeldaGenerado_Viernes() {
        return bColorCeldaGenerado_Viernes;
    }

    public void setbColorCeldaGenerado_Viernes(String bColorCeldaGenerado_Viernes) {
        this.bColorCeldaGenerado_Viernes = bColorCeldaGenerado_Viernes;
    }

    public String getbColorCeldaGenerado_Sabado() {
        return bColorCeldaGenerado_Sabado;
    }

    public void setbColorCeldaGenerado_Sabado(String bColorCeldaGenerado_Sabado) {
        this.bColorCeldaGenerado_Sabado = bColorCeldaGenerado_Sabado;
    }

    public String getbColorCeldaGenerado_Domingo() {
        return bColorCeldaGenerado_Domingo;
    }

    public void setbColorCeldaGenerado_Domingo(String bColorCeldaGenerado_Domingo) {
        this.bColorCeldaGenerado_Domingo = bColorCeldaGenerado_Domingo;
    }

    public String getbColorCeldaGenerado() {
        return bColorCeldaGenerado;
    }

    public void setbColorCeldaGenerado(String bColorCeldaGenerado) {
        this.bColorCeldaGenerado = bColorCeldaGenerado;
    }

    public int getB_generacion() {
        return b_generacion;
    }

    public void setB_generacion(int b_generacion) {
        this.b_generacion = b_generacion;
    }

    public SelectItem[] getCboGeneraciones() throws Exception {
        HSHorarioGenDAO dao = (HSHorarioGenDAO) ServiceFinder.findBean("HibernateSpringDaoTargetHorarioGen");
        List generacion = dao.seleccionarHorarioGen();
        cboGeneraciones = new SelectItem[generacion.size() + 1];
        cboGeneraciones[0] = new SelectItem(0, "[Seleccione]");
        for (int i = 0; i < cboGeneraciones.length - 1; i++) {
            cboGeneraciones[i + 1] = new SelectItem(generacion.get(i), "GENERACION " + generacion.get(i));
        }
        return cboGeneraciones;
    }

    public void setCboGeneraciones(SelectItem[] cboGeneraciones) {
        this.cboGeneraciones = cboGeneraciones;
    }

    public boolean isVerGeneracion() {
        return verGeneracion;
    }

    public void setVerGeneracion(boolean verGeneracion) {
        this.verGeneracion = verGeneracion;
    }

    //aulas disponibles
    public int getB_aulas() {
        return b_aulas;
    }

    public void setB_aulas(int b_aulas) {
        this.b_aulas = b_aulas;
    }

    public SelectItem[] getComboAulasDisponibles() throws Exception {
        int semestreParametro = this.getSemestre_f(); //semestre combo
        int especialidadParametro = this.getB_especialidad(); // especialidad combo
        int turnoParametro = this.getTurnoGeneracion_id();

        HSAulaDAO dao = (HSAulaDAO) ServiceFinder.findBean("SpringHibernateDaoAula");
        List<AcAula> aulas = dao.seleccionarAulaPorEspecialidad(especialidadParametro, semestreParametro, turnoParametro);
        comboAulasDisponibles = new SelectItem[aulas.size() + 1];
        comboAulasDisponibles[0] = new SelectItem(0, "[Seleccione]");
        for (int i = 0; i < comboAulasDisponibles.length - 1; i++) {
            comboAulasDisponibles[i + 1] = new SelectItem(aulas.get(i).getId(), aulas.get(i).getAulDes());
        }
        return comboAulasDisponibles;
    }

    public void setComboAulasDisponibles(SelectItem[] comboAulasDisponibles) {
        this.comboAulasDisponibles = comboAulasDisponibles;
    }

    //diponibilidad horaria
    public String getbColorCelda_Lunes() {
        return bColorCelda_Lunes;
    }

    public void setbColorCelda_Lunes(String bColorCelda_Lunes) {
        this.bColorCelda_Lunes = bColorCelda_Lunes;
    }

    public String getbColorCelda_Martes() {
        return bColorCelda_Martes;
    }

    public void setbColorCelda_Martes(String bColorCelda_Martes) {
        this.bColorCelda_Martes = bColorCelda_Martes;
    }

    public String getbColorCelda_Miercoles() {
        return bColorCelda_Miercoles;
    }

    public void setbColorCelda_Miercoles(String bColorCelda_Miercoles) {
        this.bColorCelda_Miercoles = bColorCelda_Miercoles;
    }

    public String getbColorCelda_Jueves() {
        return bColorCelda_Jueves;
    }

    public void setbColorCelda_Jueves(String bColorCelda_Jueves) {
        this.bColorCelda_Jueves = bColorCelda_Jueves;
    }

    public String getbColorCelda_Viernes() {
        return bColorCelda_Viernes;
    }

    public void setbColorCelda_Viernes(String bColorCelda_Viernes) {
        this.bColorCelda_Viernes = bColorCelda_Viernes;
    }

    public String getbColorCelda_Sabado() {
        return bColorCelda_Sabado;
    }

    public void setbColorCelda_Sabado(String bColorCelda_Sabado) {
        this.bColorCelda_Sabado = bColorCelda_Sabado;
    }

    public String getbColorCelda_Domingo() {
        return bColorCelda_Domingo;
    }

    public void setbColorCelda_Domingo(String bColorCelda_Domingo) {
        this.bColorCelda_Domingo = bColorCelda_Domingo;
    }

    public BeanHorarioDispDoc getHoraria() {
        return horaria;
    }

    public void setHoraria(BeanHorarioDispDoc horaria) {
        this.horaria = horaria;
    }

    public void addnListaHoraria(BeanHorarioDispDoc tmp) {
        this.nListaHoraria.add(tmp);
    }

    public List<BeanHorarioDispDoc> getnListaHoraria() {
        return nListaHoraria;
    }

    public void setnListaHoraria(List<BeanHorarioDispDoc> nListaHoraria) {
        this.nListaHoraria = nListaHoraria;
    }

    public void addqListaHoraria(BeanHorarioDispDoc tmp) {
        this.qListaHoraria.add(tmp);
    }

    public List<BeanHorarioDispDoc> getqListaHoraria() {
        return qListaHoraria;
    }

    public void setqListaHoraria(List<BeanHorarioDispDoc> qListaHoraria) {
        this.qListaHoraria = qListaHoraria;
    }

    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }

    public String getDoc_detalle() {
        return doc_detalle;
    }

    public void setDoc_detalle(String doc_detalle) {
        this.doc_detalle = doc_detalle;
    }

    public int getHordia_id() {
        return hordia_id;
    }

    public void setHordia_id(int hordia_id) {
        this.hordia_id = hordia_id;
    }

    public int getHordia_idEliminar() {
        return hordia_idEliminar;
    }

    public void setHordia_idEliminar(int hordia_idEliminar) {
        this.hordia_idEliminar = hordia_idEliminar;
    }

    public int getTurnoDisponibilidad_id() {
        return turnoDisponibilidad_id;
    }

    public void setTurnoDisponibilidad_id(int turnoDisponibilidad_id) {
        this.turnoDisponibilidad_id = turnoDisponibilidad_id;
    }

    public SelectItem[] getCmbTurnoDisponibilidad() {
        HSTurnoDAO dao = (HSTurnoDAO) ServiceFinder.findBean("HibernateSpringDaoTurno");
        List<AcTurno> turnos = dao.seleccionarTurno(this.getSemestre_f());
        cmbTurnoDisponibilidad = new SelectItem[turnos.size() + 1];
        cmbTurnoDisponibilidad[0] = new SelectItem(0, "[Seleccione]");
        for (int i = 0; i < cmbTurnoDisponibilidad.length - 1; i++) {
            cmbTurnoDisponibilidad[i + 1] = new SelectItem(turnos.get(i).getId(), turnos.get(i).getTurNombre());
        }
        return cmbTurnoDisponibilidad;
    }

    public void setCmbTurnoDisponibilidad(SelectItem[] cmbTurnoDisponibilidad) {
        this.cmbTurnoDisponibilidad = cmbTurnoDisponibilidad;
    }

    public void cargarHorarioDisponibilidad(ActionEvent event) throws Exception {
        int iDocId;
        String sDocDetalle;

        BeanHorarioDispDoc beanAcDocHor;
        AcHorarioDispDocente acHor;
        HSCatalogoDAO daoCat;
        HSDocenteDAO daoDocHor = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");
        List<AcHorarioDispDocente> lstHorDispDoc;
        daoCat = CommonDAO.getTbCatalogoDAO();
        this.setOncomplete("");
        iDocId = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_id")).getValue().toString());
        sDocDetalle = ((UIParameter) event.getComponent().findComponent("p_doc_detalle")).getValue().toString();
        this.setDoc_id(iDocId);
        doc_id_aux = this.getDoc_id();
        this.setDoc_detalle(sDocDetalle);
        this.horaria = new BeanHorarioDispDoc(iDocId);
        this.nListaHoraria = new ArrayList<BeanHorarioDispDoc>();
        this.qListaHoraria = new ArrayList<BeanHorarioDispDoc>();

        lstHorDispDoc = daoDocHor.seleccionarHorario(iDocId);

        if (!lstHorDispDoc.isEmpty()) {
            for (int i = 0; i < lstHorDispDoc.size(); i++) {
                acHor = lstHorDispDoc.get(i);
                beanAcDocHor = new BeanHorarioDispDoc(acHor);
                beanAcDocHor.setPosicion(i);
                beanAcDocHor.setV_hor_dia(daoCat.seleccionarDescripcion(acHor.getHorDia()));
                beanAcDocHor.setView_bool(true);
                beanAcDocHor.setEditable_bool(false);
                this.addnListaHoraria(beanAcDocHor);
            }
        }
        limpiar();
        mostrarTblHorarioDisponibilidad();
        this.setOncomplete("javascript:Richfaces.showModalPanel('mpHorariaDisponibilidad')");
    }

    public void cargarHorarioDisponibilidadPorDocente(ActionEvent event) throws Exception {
        int iDocId;

        BeanHorarioDispDoc beanAcDocHor;
        AcHorarioDispDocente acHor;
        HSCatalogoDAO daoCat;
        HSDocenteDAO daoDocHor = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");
        List<AcHorarioDispDocente> lstHorDispDoc;
        daoCat = CommonDAO.getTbCatalogoDAO();
        this.setOncomplete("");
        iDocId = this.getDocente_id();
        doc_id_aux = iDocId;
        this.horaria = new BeanHorarioDispDoc(iDocId);
        this.nListaHoraria = new ArrayList<BeanHorarioDispDoc>();
        this.qListaHoraria = new ArrayList<BeanHorarioDispDoc>();

        if (iDocId != 0) {
            lstHorDispDoc = daoDocHor.seleccionarHorario(iDocId);

            if (!lstHorDispDoc.isEmpty()) {
                for (int i = 0; i < lstHorDispDoc.size(); i++) {
                    acHor = lstHorDispDoc.get(i);
                    beanAcDocHor = new BeanHorarioDispDoc(acHor);
                    beanAcDocHor.setPosicion(i);
                    beanAcDocHor.setV_hor_dia(daoCat.seleccionarDescripcion(acHor.getHorDia()));
                    beanAcDocHor.setView_bool(true);
                    beanAcDocHor.setEditable_bool(false);
                    this.addnListaHoraria(beanAcDocHor);
                }
            }
            limpiar();
            mostrarTblHorarioDisponibilidad();
            this.setOncomplete("javascript:Richfaces.showModalPanel('mpHorariaDisponibilidadPorDocente')");
        } else {
            this.setOncomplete("javascript:alert('DEBE SELECCIONAR DOCENTE')");
        }
    }

    public void setearTurnoDisponibilidad() throws Exception {

        mostrarTblHorarioDisponibilidad();
    }

    public void mostrarTblHorarioDisponibilidad() throws Exception {

        int tur_id_aux = this.getTurnoDisponibilidad_id();
        int tiempo;
        HSTurnoDetalleDAO dao = (HSTurnoDetalleDAO) ServiceFinder.findBean("HibernateSpringDaoTurnoDetalle");
        HSTurnoDAO daoTurno = (HSTurnoDAO) ServiceFinder.findBean("HibernateSpringDaoTurno");
        AcTurno tmpTurno = daoTurno.obtenerTurno(tur_id_aux);
        bHorario horario;
        List lstTurnoDetalle = dao.seleccionarTurnoDetalle(tur_id_aux);
        List<bHorario> lstHorarioDisponilidad = new ArrayList<bHorario>();

        if (tmpTurno != null) {
            tiempo = daoTurno.obtenerTurno(tur_id_aux).getTurTiempoPeriodo();
        } else {
            tiempo = 0;
        }

        for (int i = 0; i < lstTurnoDetalle.size(); i++) {
            Object O[] = (Object[]) lstTurnoDetalle.get(i);
            horario = new bHorario();
            horario.setId_hor_dis(aInteger(O[0].toString()));
            horario.setInicio_hor_dis(O[1].toString());
            int j = i + 1;
            Object P[];
            if (j == lstTurnoDetalle.size()) {
                P = (Object[]) lstTurnoDetalle.get(i);
                P[1] = suma(P[1].toString(), tiempo);
            } else {
                P = (Object[]) lstTurnoDetalle.get(j);
            }
            horario.setFin_hor_dis(P[1].toString());
            llenarDiasDisponibilidad(horario, Integer.valueOf(O[0].toString()), doc_id_aux);
            lstHorarioDisponilidad.add(horario);
        }
        this.setTablaHorarioDispinibilidad(lstHorarioDisponilidad);
    }

    public List getTablaHorarioDisponibilidad() {
        return tablaHorarioDisponibilidad;
    }

    public void setTablaHorarioDispinibilidad(List tablaHorarioDisponibilidad) {
        this.tablaHorarioDisponibilidad = tablaHorarioDisponibilidad;
    }

    public int getIndice_id_lun_dis() {
        return indice_id_lun_dis;
    }

    public void setIndice_id_lun_dis(int indice_id_lun_dis) {
        this.indice_id_lun_dis = indice_id_lun_dis;
    }

    public int getIndice_id_mar_dis() {
        return indice_id_mar_dis;
    }

    public void setIndice_id_mar_dis(int indice_id_mar_dis) {
        this.indice_id_mar_dis = indice_id_mar_dis;
    }

    public int getIndice_id_mie_dis() {
        return indice_id_mie_dis;
    }

    public void setIndice_id_mie_dis(int indice_id_mie_dis) {
        this.indice_id_mie_dis = indice_id_mie_dis;
    }

    public int getIndice_id_jue_dis() {
        return indice_id_jue_dis;
    }

    public void setIndice_id_jue_dis(int indice_id_jue_dis) {
        this.indice_id_jue_dis = indice_id_jue_dis;
    }

    public int getIndice_id_vie_dis() {
        return indice_id_vie_dis;
    }

    public void setIndice_id_vie_dis(int indice_id_vie_dis) {
        this.indice_id_vie_dis = indice_id_vie_dis;
    }

    public int getIndice_id_sab_dis() {
        return indice_id_sab_dis;
    }

    public void setIndice_id_sab_dis(int indice_id_sab_dis) {
        this.indice_id_sab_dis = indice_id_sab_dis;
    }

    public int getIndice_id_dom_dis() {
        return indice_id_dom_dis;
    }

    public void setIndice_id_dom_dis(int indice_id_dom_dis) {
        this.indice_id_dom_dis = indice_id_dom_dis;
    }

    public String getLunes_hor_dis() {
        return lunes_hor_dis;
    }

    public void setLunes_hor_dis(String lunes_hor_dis) {
        this.lunes_hor_dis = lunes_hor_dis;
    }

    public String getMartes_hor_dis() {
        return martes_hor_dis;
    }

    public void setMartes_hor_dis(String martes_hor_dis) {
        this.martes_hor_dis = martes_hor_dis;
    }

    public String getMiercoles_hor_dis() {
        return miercoles_hor_dis;
    }

    public void setMiercoles_hor_dis(String miercoles_hor_dis) {
        this.miercoles_hor_dis = miercoles_hor_dis;
    }

    public String getJueves_hor_dis() {
        return jueves_hor_dis;
    }

    public void setJueves_hor_dis(String jueves_hor_dis) {
        this.jueves_hor_dis = jueves_hor_dis;
    }

    public String getViernes_hor_dis() {
        return viernes_hor_dis;
    }

    public void setViernes_hor_dis(String viernes_hor_dis) {
        this.viernes_hor_dis = viernes_hor_dis;
    }

    public String getSabado_hor_dis() {
        return sabado_hor_dis;
    }

    public void setSabado_hor_dis(String sabado_hor_dis) {
        this.sabado_hor_dis = sabado_hor_dis;
    }

    public String getDomingo_hor_dis() {
        return domingo_hor_dis;
    }

    public void setDomingo_hor_dis(String domingo_hor_dis) {
        this.domingo_hor_dis = domingo_hor_dis;
    }

    public void llenarDiasDisponibilidad(bHorario horario, Integer id_turno, int doc) {
        HSHorarioDAO dao = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoHorario");
        List<AcHorarioDispDocente> lstHorarioDisponibilidad = dao.seleccionarHorarioDocente(doc);
        for (int i = 0; i < lstHorarioDisponibilidad.size(); i++) {
            if ((lstHorarioDisponibilidad.get(i).getTurdet().getId()).compareTo(id_turno) == 0) {
                String dias = lstHorarioDisponibilidad.get(i).getHorDia();
                String mensaje = parametroMensajeDisponibilidadDocente;

                if (dias.equals("016001")) {
                    horario.setLunes_hor_dis(mensaje);
                    horario.setIndice_id_lun_dis(lstHorarioDisponibilidad.get(i).getHorId());
                    if (horario.getLunes_hor_dis().equals(parametroMensajeDisponibilidadDocente)) {
                        horario.setbColorCelda_Lunes("background-color:#6F97CE; border-color:#6F97CE; color:#FFF;");
                    } else {
                        horario.setbColorCelda_Lunes("");
                    }
                }
                if (dias.equals("016002")) {
                    horario.setMartes_hor_dis(mensaje);
                    horario.setIndice_id_mar_dis(lstHorarioDisponibilidad.get(i).getHorId());
                    if (horario.getMartes_hor_dis().equals(parametroMensajeDisponibilidadDocente)) {
                        horario.setbColorCelda_Martes("background-color:#6F97CE; border-color:#6F97CE; color:#FFF;");
                    } else {
                        horario.setbColorCelda_Martes("");
                    }
                }
                if (dias.equals("016003")) {
                    horario.setMiercoles_hor_dis(mensaje);
                    horario.setIndice_id_mie_dis(lstHorarioDisponibilidad.get(i).getHorId());
                    if (horario.getMiercoles_hor_dis().equals(parametroMensajeDisponibilidadDocente)) {
                        horario.setbColorCelda_Miercoles("background-color:#6F97CE; border-color:#6F97CE; color:#FFF;");
                    } else {
                        horario.setbColorCelda_Miercoles("");
                    }
                }
                if (dias.equals("016004")) {
                    horario.setJueves_hor_dis(mensaje);
                    horario.setIndice_id_jue_dis(lstHorarioDisponibilidad.get(i).getHorId());
                    if (horario.getJueves_hor_dis().equals(parametroMensajeDisponibilidadDocente)) {
                        horario.setbColorCelda_Jueves("background-color:#6F97CE; border-color:#6F97CE; color:#FFF;");
                    } else {
                        horario.setbColorCelda_Jueves("");
                    }
                }
                if (dias.equals("016005")) {
                    horario.setViernes_hor_dis(mensaje);
                    horario.setIndice_id_vie_dis(lstHorarioDisponibilidad.get(i).getHorId());
                    if (horario.getViernes_hor_dis().equals(parametroMensajeDisponibilidadDocente)) {
                        horario.setbColorCelda_Viernes("background-color:#6F97CE; border-color:#6F97CE; color:#FFF;");
                    } else {
                        horario.setbColorCelda_Viernes("");
                    }
                }
                if (dias.equals("016006")) {
                    horario.setSabado_hor_dis(mensaje);
                    horario.setIndice_id_sab_dis(lstHorarioDisponibilidad.get(i).getHorId());
                    if (horario.getSabado_hor_dis().equals(parametroMensajeDisponibilidadDocente)) {
                        horario.setbColorCelda_Sabado("background-color:#6F97CE; border-color:#6F97CE; color:#FFF;");
                    } else {
                        horario.setbColorCelda_Sabado("");
                    }
                }
                if (dias.equals("016007")) {
                    horario.setDomingo_hor_dis(mensaje);
                    horario.setIndice_id_dom_dis(lstHorarioDisponibilidad.get(i).getHorId());
                    if (horario.getDomingo_hor_dis().equals(parametroMensajeDisponibilidadDocente)) {
                        horario.setbColorCelda_Domingo("background-color:#6F97CE; border-color:#6F97CE; color:#FFF;");
                    } else {
                        horario.setbColorCelda_Domingo("");
                    }
                }
            }
        }
    }

    public int getId_doc_horario() {
        if (doc_id_aux != 0) {
            return doc_id_aux;
        } else {
            return id_doc_horario;
        }
    }

    public void setId_doc_horario(int id_doc_horario) {
        this.id_doc_horario = id_doc_horario;
    }

    public int getId_hor_dis() {
        return id_hor_dis;
    }

    public void setId_hor_dis(int id_hor_dis) {
        this.id_hor_dis = id_hor_dis;
    }

    public String getInicio_hor_dis() {
        return inicio_hor_dis;
    }

    public void setInicio_hor_dis(String inicio_hor_dis) {
        this.inicio_hor_dis = inicio_hor_dis;
    }

    public String getFin_hor_dis() {
        return fin_hor_dis;
    }

    public void setFin_hor_dis(String fin_hor_dis) {
        this.fin_hor_dis = fin_hor_dis;
    }

    // fin de la disponibilidad horaria
    // curso docente
    public void addnLstDocenteCurso(CursoDocenteBean tmp) {
        this.nLstDocenteCurso.add(tmp);
    }

    public List<CursoDocenteBean> getnLstDocenteCurso() {
        return nLstDocenteCurso;
    }

    public void setnLstDocenteCurso(List<CursoDocenteBean> nLstDocenteCurso) {
        this.nLstDocenteCurso = nLstDocenteCurso;
    }

    public void addqLstDocenteCurso(CursoDocenteBean tmp) {
        this.qLstDocenteCurso.add(tmp);
    }

    public List<CursoDocenteBean> getqLstDocenteCurso() {
        return qLstDocenteCurso;
    }

    public void setqLstDocenteCurso(List<CursoDocenteBean> qLstDocenteCurso) {
        this.qLstDocenteCurso = qLstDocenteCurso;
    }

    public CursoDocenteBean getDocenteCurso() {
        return docenteCurso;
    }

    public void setDocenteCurso(CursoDocenteBean docenteCurso) {
        this.docenteCurso = docenteCurso;
    }

    public void buscarAsignaturas(ActionEvent event) {
        try {
            int iDocId;
            String sDocDetalle;
            HSDocenteDAO dao = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");
            iDocId = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_id")).getValue().toString());
            sDocDetalle = ((UIParameter) event.getComponent().findComponent("p_doc_detalle")).getValue().toString();

//            listarEnlaces( p_id2 );
            CursoDocenteBean cursoDocenteBean;
            AcDocenteCurso acDocCurso;
            HSCatalogoDAO daoCat;
            HSDocenteCursoDAO daoDocCurso = (HSDocenteCursoDAO) ServiceFinder.findBean("SpringHibernateDaoDocenteCurso");
            List<AcDocenteCurso> lstDocenteCurso;

            sDocDetalle = ((UIParameter) event.getComponent().findComponent("p_doc_detalle")).getValue().toString();
            this.setOncomplete("");
            daoCat = CommonDAO.getTbCatalogoDAO();

            this.setDoc_id(iDocId);
            doc_id_aux = this.getDoc_id();
            this.setDoc_detalle(sDocDetalle);
            this.docenteCurso = new CursoDocenteBean(iDocId);
            this.nLstDocenteCurso = new ArrayList<CursoDocenteBean>();
            this.qLstDocenteCurso = new ArrayList<CursoDocenteBean>();

            lstDocenteCurso = daoDocCurso.listadoDocenteCurso(iDocId);

            if (!lstDocenteCurso.isEmpty()) {
                for (int i = 0; i < lstDocenteCurso.size(); i++) {
                    acDocCurso = lstDocenteCurso.get(i);
                    cursoDocenteBean = new CursoDocenteBean(acDocCurso);
                    cursoDocenteBean.setPosicion(i);
                    cursoDocenteBean.setV_asignacion(acDocCurso.getEstadoAsignacion());
                    cursoDocenteBean.setEstadoAsignacion(daoCat.seleccionarDescripcion(acDocCurso.getEstadoAsignacion()));
                    cursoDocenteBean.setView_bool(true);
                    cursoDocenteBean.setEditable_bool(false);
                    this.addnLstDocenteCurso(cursoDocenteBean);
                }
            }

            this.setOncomplete("javascript:Richfaces.showModalPanel('mpDocente');");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //fin de curso docente
    public int getIdSemestre() {
        return idSemestre;
    }

    public void setIdSemestre(int idSemestre) {
        this.idSemestre = idSemestre;
    }

    public int getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(int idFacultad) {
        this.idFacultad = idFacultad;
    }

    public boolean isVistaArbolTabla() {
        return vistaArbolTabla;
    }

    public void setVistaArbolTabla(boolean vistaArbolTabla) {
        this.vistaArbolTabla = vistaArbolTabla;
    }

    public SelectItem[] getCboSemestres() throws Exception {
        HSSemestreDAO dao = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
        List lista = dao.seleccionarSemestre("", "");
        cboSemestres = new SelectItem[1];
        cboSemestres[0] = new SelectItem(0, "[Seleccione]");
        if (0 != lista.size()) {
            cboSemestres = new SelectItem[lista.size()];
            for (int i = 0; i < cboSemestres.length; i++) {
                cboSemestres[i] = new SelectItem(((AcSemestre) lista.get(i)).getId(), ((AcSemestre) lista.get(i)).getSemNombre());
            }
        }
        return cboSemestres;
    }

    public void setCboSemestres(SelectItem[] cboSemestres) {
        this.cboSemestres = cboSemestres;
    }

    public SelectItem[] getCboFacultadesPrincipal() {
        HSFacultadDAO dao = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
        List lista = dao.seleccionarFacultad("", "");
        cboFacultadesPrincipal = new SelectItem[lista.size() + 1];
        cboFacultadesPrincipal[0] = new SelectItem(0, "TODOS");
        for (int i = 0; i < cboFacultadesPrincipal.length - 1; i++) {
            cboFacultadesPrincipal[i + 1] = new SelectItem(((AcFacultad) lista.get(i)).getId(), ((AcFacultad) lista.get(i)).getFacNombre());
        }
        return cboFacultadesPrincipal;
    }

    public void setCboFacultadesPrincipal(SelectItem[] cboFacultadesPrincipal) {
        this.cboFacultadesPrincipal = cboFacultadesPrincipal;
    }

    public List<ObjNodeGnral> getNodos() {
        return nodos;
    }

    public void setNodos(List<ObjNodeGnral> nodos) {
        this.nodos = nodos;
    }

    public String getN_cur_aperturado() {
        return n_cur_aperturado;
    }

    public void setN_cur_aperturado(String n_cur_aperturado) {
        this.n_cur_aperturado = n_cur_aperturado;
    }

    public int getId_sec_horario() {
        if (seccion_id_aux != 0) {
            return seccion_id_aux;
        } else {
            return id_sec_horario;
        }
    }

    public void setId_sec_horario(int id_sec_horario) {
        this.id_sec_horario = id_sec_horario;
    }

    public int getId_sem() {
        return id_sem;
    }

    public void setId_sem(int id_sem) {
        this.id_sem = id_sem;
    }

    public int getId_sem_horario() {
        if (seccion_id_aux != 0) {
            return semestre_id_aux;
        } else {
            return id_sem_horario;
        }
    }

    public void setId_sem_horario(int id_sem_horario) {
        this.id_sem_horario = id_sem_horario;
    }

    public String getNodoSeleccionado() {
        return nodoSeleccionado;
    }

    public void setNodoSeleccionado(String nodoSeleccionado) {
        this.nodoSeleccionado = nodoSeleccionado;
    }

    public List getListaSecciones() {
        return listaSecciones;
    }

    public void setListaSecciones(List listaSecciones) {
        this.listaSecciones = listaSecciones;
    }

    public TreeNode getArbol() {
        return arbol;
    }

    public void setArbol(TreeNode arbol) {
        this.arbol = arbol;
    }

    public int getId_sec() {
        return id_sec;
    }

    public void setId_sec(int id_sec) {
        this.id_sec = id_sec;
    }

    public String getCodigo_sec() {
        return codigo_sec;
    }

    public void setCodigo_sec(String codigo_sec) {
        this.codigo_sec = codigo_sec;
    }

    public String getNombre_sec() {
        return nombre_sec;
    }

    public void setNombre_sec(String nombre_sec) {
        this.nombre_sec = nombre_sec;
    }

    public int getVacantes_sec() {
        return vacantes_sec;
    }

    public void setVacantes_sec(int vacantes_sec) {
        this.vacantes_sec = vacantes_sec;
    }

    public int getId_hor() {
        return id_hor;
    }

    public void setId_hor(int id_hor) {
        this.id_hor = id_hor;
    }

    public String getInicio_hor() {
        return inicio_hor;
    }

    public void setInicio_hor(String inicio_hor) {
        this.inicio_hor = inicio_hor;
    }

    public String getFin_hor() {
        return fin_hor;
    }

    public void setFin_hor(String fin_hor) {
        this.fin_hor = fin_hor;
    }

    public String getLunes_hor() {
        return lunes_hor;
    }

    public void setLunes_hor(String lunes_hor) {
        this.lunes_hor = lunes_hor;
    }

    public String getMartes_hor() {
        return martes_hor;
    }

    public void setMartes_hor(String martes_hor) {
        this.martes_hor = martes_hor;
    }

    public String getMiercoles_hor() {
        return miercoles_hor;
    }

    public void setMiercoles_hor(String miercoles_hor) {
        this.miercoles_hor = miercoles_hor;
    }

    public String getJueves_hor() {
        return jueves_hor;
    }

    public void setJueves_hor(String jueves_hor) {
        this.jueves_hor = jueves_hor;
    }

    public String getViernes_hor() {
        return viernes_hor;
    }

    public void setViernes_hor(String viernes_hor) {
        this.viernes_hor = viernes_hor;
    }

    public String getSabado_hor() {
        return sabado_hor;
    }

    public void setSabado_hor(String sabado_hor) {
        this.sabado_hor = sabado_hor;
    }

    public String getDomingo_hor() {
        return domingo_hor;
    }

    public void setDomingo_hor(String domingo_hor) {
        this.domingo_hor = domingo_hor;
    }

    public String getLunes_hor_tp() {
        return lunes_hor_tp;
    }

    public void setLunes_hor_tp(String lunes_hor_tp) {
        this.lunes_hor_tp = lunes_hor_tp;
    }

    public String getMartes_hor_tp() {
        return martes_hor_tp;
    }

    public void setMartes_hor_tp(String martes_hor_tp) {
        this.martes_hor_tp = martes_hor_tp;
    }

    public String getMiercoles_hor_tp() {
        return miercoles_hor_tp;
    }

    public void setMiercoles_hor_tp(String miercoles_hor_tp) {
        this.miercoles_hor_tp = miercoles_hor_tp;
    }

    public String getJueves_hor_tp() {
        return jueves_hor_tp;
    }

    public void setJueves_hor_tp(String jueves_hor_tp) {
        this.jueves_hor_tp = jueves_hor_tp;
    }

    public String getViernes_hor_tp() {
        return viernes_hor_tp;
    }

    public void setViernes_hor_tp(String viernes_hor_tp) {
        this.viernes_hor_tp = viernes_hor_tp;
    }

    public String getSabado_hor_tp() {
        return sabado_hor_tp;
    }

    public void setSabado_hor_tp(String sabado_hor_tp) {
        this.sabado_hor_tp = sabado_hor_tp;
    }

    public String getDomingo_hor_tp() {
        return domingo_hor_tp;
    }

    public void setDomingo_hor_tp(String domingo_hor_tp) {
        this.domingo_hor_tp = domingo_hor_tp;
    }

    public int getTurno_id() {
        return turno_id;
    }

    public void setTurno_id(int turno_id) {
        this.turno_id = turno_id;
    }

    public int getTurnoGeneracion_id() {
        System.out.println("TURNo xXXX!!" + turnoGeneracion_id);
        return turnoGeneracion_id;
    }

    public void setTurnoGeneracion_id(int turnoGeneracion_id) {
        System.out.println("TURNo xXXX!!" + turnoGeneracion_id);
        this.turnoGeneracion_id = turnoGeneracion_id;
    }

    public int getDocente_id() {
        return docente_id;
    }

    public void setDocente_id(int docente_id) {
        this.docente_id = docente_id;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public boolean isPersonalizado() {
        return personalizado;
    }

    public void setPersonalizado(boolean personalizado) {
        this.personalizado = personalizado;
    }

    public String getEfecto() {
        return efecto;
    }

    public void setEfecto(String efecto) {
        this.efecto = efecto;
    }

    public void cambiarEfecto() {
        if (this.isPersonalizado()) {
            this.setEfecto("showDiv()");
        } else {
            this.setEfecto("hideDiv({duration:0.7})");
        }
    }

    public SelectItem[] getComboDocentes() throws Exception {
        HSDocenteDAO dao = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");
        List<AcDocente> docentes = dao.seleccionarDocentePorCurso(this.getiEspecialidad(), this.getiCurso());
        comboDocentes = new SelectItem[docentes.size() + 1];
        comboDocentes[0] = new SelectItem(0, "[Seleccione]");
        for (int i = 0; i < comboDocentes.length - 1; i++) {
            comboDocentes[i + 1] = new SelectItem(docentes.get(i).getId(), docentes.get(i).getDocNombre());
        }
        return comboDocentes;
    }

    public void setComboDocentes(SelectItem[] comboDocentes) {
        this.comboDocentes = comboDocentes;
    }

    public SelectItem[] getComboTurnos() {
        HSTurnoDAO dao = (HSTurnoDAO) ServiceFinder.findBean("HibernateSpringDaoTurno");
        List<AcTurno> turnos = dao.seleccionarTurno(semestre_id_aux);
        comboTurnos = new SelectItem[turnos.size() + 1];
        comboTurnos[0] = new SelectItem(0, "[Seleccione]");
        for (int i = 0; i < comboTurnos.length - 1; i++) {
            comboTurnos[i + 1] = new SelectItem(turnos.get(i).getId(), turnos.get(i).getTurNombre());
        }
        return comboTurnos;
    }

    public void setComboTurnos(SelectItem[] comboTurnos) {
        this.comboTurnos = comboTurnos;
    }

    public SelectItem[] getComboTurnos2() {
        HSTurnoDAO dao = (HSTurnoDAO) ServiceFinder.findBean("HibernateSpringDaoTurno");
        List<AcTurno> turnos = dao.seleccionarTurno(this.getSemestre_f());
        comboTurnos2 = new SelectItem[turnos.size() + 1];
        comboTurnos2[0] = new SelectItem(0, "[Seleccione]");
        for (int i = 0; i < comboTurnos2.length - 1; i++) {
            comboTurnos2[i + 1] = new SelectItem(turnos.get(i).getId(), turnos.get(i).getTurNombre());
        }
        return comboTurnos2;
    }

    public void setComboTurnos2(SelectItem[] comboTurnos2) {
        this.comboTurnos2 = comboTurnos2;
    }

    public SelectItem[] getComboAulas() throws Exception {
        HSAulaDAO dao = (HSAulaDAO) ServiceFinder.findBean("SpringHibernateDaoAula");
        List<AcAula> aulas = dao.seleccionarAulaSeccion(this.getId_sec());
        comboAulas = new SelectItem[aulas.size() + 1];
        comboAulas[0] = new SelectItem(0, "[Seleccione]");
        for (int i = 0; i < comboAulas.length - 1; i++) {
            comboAulas[i + 1] = new SelectItem(aulas.get(i).getId(), aulas.get(i).getAulDes());
        }
        return comboAulas;
    }

    public void setComboAulas(SelectItem[] comboAulas) {
        this.comboAulas = comboAulas;
    }

    public SelectItem[] getComboLaboratorio() throws Exception {
        if (this.getTipo_idEditar().equals("018003")) {
            HSAulaDAO daoAula = (HSAulaDAO) ServiceFinder.findBean("SpringHibernateDaoAula");
            List<AcAula> aulas = daoAula.listadoLaboratoriosDisponibles(this.getHordia_id());
            comboLaboratorio = new SelectItem[aulas.size() + 1];
            comboLaboratorio[0] = new SelectItem(0, "[Seleccione]");
            for (int i = 0; i < comboLaboratorio.length - 1; i++) {
                comboLaboratorio[i + 1] = new SelectItem(aulas.get(i).getId(), aulas.get(i).getAulDes());
            }
        } else {
            comboLaboratorio = new SelectItem[1];
            comboLaboratorio[0] = new SelectItem(0, "[Seleccione]");
        }
        return comboLaboratorio;
    }

    public void setComboLaboratorio(SelectItem[] comboLaboratorio) {
        this.comboLaboratorio = comboLaboratorio;
    }

    public int getLab_id() {
        return lab_id;
    }

    public void setLab_id(int lab_id) {
        this.lab_id = lab_id;
    }

    public SelectItem[] getComboTipos() throws Exception {
        HSCatalogoDAO dao = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        List<TbCatalogo> lista = dao.seleccionarCatalogo("018");
        SelectItem[] cmbTipos = new SelectItem[lista.size()];
        for (int i = 0; i < cmbTipos.length; i++) {
            TbCatalogo cat = lista.get(i);
            cmbTipos[i] = new SelectItem(cat.getCatCodigoGrupo() + cat.getCatCodigoElemento(),
                    cat.getCatDescripcionElemento());
        }
        return cmbTipos;
    }

    public void setComboTipos(SelectItem[] comboTipos) {
        this.comboTipos = comboTipos;
    }

    public SelectItem[] getComboTiposEditar() throws Exception {
        HSCatalogoDAO dao = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        List<TbCatalogo> lista = dao.seleccionarCatalogo("018");
        comboTiposEditar = new SelectItem[lista.size() + 1];
        comboTiposEditar[0] = new SelectItem("000000", "[Seleccione]");
        for (int i = 0; i < comboTiposEditar.length - 1; i++) {
            TbCatalogo cat = lista.get(i);
            comboTiposEditar[i + 1] = new SelectItem(cat.getCatCodigoGrupo() + cat.getCatCodigoElemento(),
                    cat.getCatDescripcionElemento());
        }
        return comboTiposEditar;
    }

    public void setComboTiposEditar(SelectItem[] comboTiposEditar) {
        this.comboTiposEditar = comboTiposEditar;
    }

    public int getAula_id() {
        return this.aula_id;
    }

    public void setAula_id(int aula_id) {
        this.aula_id = aula_id;
    }

    public String getTipo_id() {
        return this.tipo_id;
    }

    public void setTipo_id(String tipo_id) {
        this.tipo_id = tipo_id;
    }

    public String getTipo_idEditar() {
        return this.tipo_idEditar;
    }

    public void setTipo_idEditar(String tipo_idEditar) {
        this.tipo_idEditar = tipo_idEditar;
    }

    public int getIndice_id_lun() {
        return indice_id_lun;
    }

    public void setIndice_id_lun(int indice_id_lun) {
        this.indice_id_lun = indice_id_lun;
    }

    public int getIndice_id_mar() {
        return indice_id_mar;
    }

    public void setIndice_id_mar(int indice_id_mar) {
        this.indice_id_mar = indice_id_mar;
    }

    public int getIndice_id_mie() {
        return indice_id_mie;
    }

    public void setIndice_id_mie(int indice_id_mie) {
        this.indice_id_mie = indice_id_mie;
    }

    public int getIndice_id_jue() {
        return indice_id_jue;
    }

    public void setIndice_id_jue(int indice_id_jue) {
        this.indice_id_jue = indice_id_jue;
    }

    public int getIndice_id_vie() {
        return indice_id_vie;
    }

    public void setIndice_id_vie(int indice_id_vie) {
        this.indice_id_vie = indice_id_vie;
    }

    public int getIndice_id_sab() {
        return indice_id_sab;
    }

    public void setIndice_id_sab(int indice_id_sab) {
        this.indice_id_sab = indice_id_sab;
    }

    public int getIndice_id_dom() {
        return indice_id_dom;
    }

    public void setIndice_id_dom(int indice_id_dom) {
        this.indice_id_dom = indice_id_dom;
    }

    public void cargarArbol() {
        try {
            if (this.getIdSemestre() != 0 && this.getIdFacultad() != 0) {
                String estructura = ArmarEstructura();
                StringBuffer buffer = new StringBuffer(estructura);
                ByteArrayInputStream estructura_arbol = new ByteArrayInputStream(buffer.toString().getBytes("ISO-8859-1"));
                Properties properties = new Properties();
                properties.load(estructura_arbol);
                arbol = new TreeNodeImpl();
                agregarNodos(null, arbol, properties);
                setVistaArbolTabla(true);
            } else {
                setVistaArbolTabla(false);
                this.setOncomplete("javascript:alert('Formato incorrecto.')");
            }
        } catch (IOException ioe) {
            System.out.println("No se pudo cargar el arbol");
        } catch (Exception e) {
        }
    }

    private void agregarNodos(String path, TreeNode node, Properties properties) {
        boolean end = false;
        int counter = 1;
        while (!end) {
            String key = path != null ? path + '.' + counter : String.valueOf(counter);
            String value = properties.getProperty(key);
            if (value != null) {
                TreeNodeImpl nodo = new TreeNodeImpl();
                nodo.setData(value);
                node.addChild(new Integer(counter), nodo);
                agregarNodos(key, nodo, properties);
                counter++;
            } else {
                end = true;
            }
        }
    }

    public String ArmarEstructura() throws Exception {
        int b = 0;
        int m = 0;

        String estructura = "";
        String estructura_facultades = "";
        String estructura_especialidades = "";
        String estructura_curape = "";
        String estructura_semestres = "";
        HSSemestreDAO dao_s = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
        //List<AcSemestre> lista_semestres = dao_s.seleccionarSemestreVigente();
        List<AcSemestre> lista_semestres = dao_s.seleccionarSemestreVigenteH(this.getIdSemestre());
        HSFacultadDAO dao = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
        List<AcFacultad> lista_facultad = dao.seleccionarFacultadId(this.getIdFacultad());

        List<ObjNodeGnral> tmp_nodos = new ArrayList<ObjNodeGnral>();
        if (!lista_facultad.isEmpty()) {
            for (int i = 0; i < lista_facultad.size(); i++) {
                estructura_facultades += (i + 1) + "=" + ((AcFacultad) lista_facultad.get(i)).getFacNombre() + "\n";
                Set<AcEspecialidad> esp = new LinkedHashSet<AcEspecialidad>();
                esp = lista_facultad.get(i).getAcEspecialidads();
                List<AcEspecialidad> lista_especialidad = Collections.synchronizedList(new LinkedList(esp));

                if (!lista_especialidad.isEmpty()) {
                    for (int j = 0; j < lista_especialidad.size(); j++) {
                        estructura_especialidades += (i + 1) + "." + (j + 1) + "=" + ((AcEspecialidad) lista_especialidad.get(j)).getEspNombre() + "\n";

                        if (!lista_semestres.isEmpty()) {
                            for (int w = 0; w < lista_semestres.size(); w++) {
                                estructura_semestres += (i + 1) + "." + (j + 1) + "." + (w + 1) + "=" + lista_semestres.get(w).getSemNombre() + "\n";
                                HSAperturaCursosDAO daoCurso = (HSAperturaCursosDAO) ServiceFinder.findBean("SpringHibernateDaoCursoAperturado");
                                int f = lista_especialidad.get(j).getId();
                                List<AcCursoAperturado> lista_curso_aperturado = daoCurso.seleccionarAperturaporEspecialidad(f, lista_semestres.get(w).getId());

                                if (!lista_curso_aperturado.isEmpty()) {
                                    List<Integer> ultimos_digitos = new ArrayList<Integer>();
                                    ultimos_digitos.add(-1);
                                    int contador = 0;
                                    for (int k = 0; k < lista_curso_aperturado.size(); k++) {
                                        m = contador;
                                        int a = aInteger(lista_curso_aperturado.get(k).getPlancur().getPlancurCiclo().toString().substring(5, 6));
                                        HSCatalogoDAO cat = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
                                        int y = verificarIndice(a, ultimos_digitos);
                                        estructura_curape += (i + 1) + "." + (j + 1) + "." + (w + 1) + "." + y + "="
                                                + cat.seleccionarDescripcion(lista_curso_aperturado.get(k).getPlancur().getPlancurCiclo()) + "\n";
                                        if (b != y) {
                                            m = 0;
                                            contador = 0;
                                        }
                                        estructura_curape += (i + 1) + "." + (j + 1) + "." + (w + 1) + "." + y + "." + (m + 1) + "="
                                                + lista_curso_aperturado.get(k).getPlancur().getCur().getCurNombre() + "\n";
                                        b = y;
                                        tmp_nodos.add(new ObjNodeGnral((i + 1) + ":" + (j + 1) + ":" + (w + 1) + ":" + y + ":" + (m + 1),
                                                lista_curso_aperturado.get(k).getId(),
                                                lista_curso_aperturado.get(k).getPlancur().getCur().getCurNombre(),
                                                lista_semestres.get(w).getId()));
                                        contador++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        estructura = estructura_facultades + estructura_especialidades + estructura_semestres + estructura_curape;
        this.setListaSecciones(null);
        this.setNodos(new ArrayList<ObjNodeGnral>(tmp_nodos));
        return estructura;
    }

    public int verificarIndice(int ultimo_digito, List<Integer> ultimos_digitos) {
        int estado = 0;
        int grupo = 0;
        for (int i = 0; i < ultimos_digitos.size(); i++) {
            if (ultimos_digitos.get(i).intValue() == ultimo_digito) {
                estado = 1;
                grupo = i;
                break;
            }
        }
        if (estado == 0) {
            ultimos_digitos.add(ultimo_digito);
            grupo = ultimos_digitos.size() - 1;
        }
        return grupo;
    }

    private int buscarNodo(String id) {
        for (int i = 0; i < nodos.size(); i++) {
            ObjNodeGnral nodo = nodos.get(i);
            if (nodo.getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public void Seleccion(NodeSelectedEvent event) {
        try {
            UITree tree = (UITree) event.getComponent();
            String rowKey = tree.getRowKey().toString();

            int length = rowKey.length();
            if (length >= 9) {
                int pos = buscarNodo(rowKey);

                ObjNodeGnral nodo = nodos.get(pos);

                int id_curape = nodo.getCurape_id();
                int id_semape = nodo.getSem_id();
                String nombre_curape = nodo.getCur_nom();

                this.setiSemestre(id_semape);
                HSAperturaCursosDAO dao = (HSAperturaCursosDAO) ServiceFinder.findBean("HibernateSpringDaoTargetCursoAperturado");
                AcCursoAperturado curso = dao.seleccionarCursoAperturadoID(id_curape);
                this.setiCurso(curso.getPlancur().getCur().getId());
                int id_esp = curso.getPlancur().getCur().getEsp().getId();
                this.setiEspecialidad(id_esp);
                mostrarTablaSecciones(id_curape, id_semape, nombre_curape);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarTablaSecciones(int id_curape, int id_semape, String nombre_curape) throws Exception {
        HSSeccionDAO dao = (HSSeccionDAO) ServiceFinder.findBean("HibernateSpringDaoSeccion");
        List<AcSeccion> lista = dao.seleccionarSecciones(id_curape);
        List horarios = new ArrayList();
        for (int i = 0; i < lista.size(); i++) {
            HSMatriculaDAO matricula = (HSMatriculaDAO) ServiceFinder.findBean("SpringHibernateDaoMatricula");
            bHorario horario = new bHorario();
            horario.setId_sec(lista.get(i).getId());
            horario.setCodigo_sec(lista.get(i).getSecCodigo());
            horario.setNombre_sec(lista.get(i).getSecNombre());
            horario.setVacantes_sec(lista.get(i).getSecVacantes());
            horario.setVacantesOcupadas(String.valueOf(matricula.seleccionarMatriculas(lista.get(i).getId()).size()));
            horario.setId_sem(id_semape);

            horarios.add(horario);
        }
        this.setN_cur_aperturado(nombre_curape);
        this.setListaSecciones(horarios);
    }

    public void AsignarHorario(ActionEvent event) throws Exception {
        UIParameter id = (UIParameter) event.getComponent().findComponent("id_seccion");
        UIParameter id_semestre = (UIParameter) event.getComponent().findComponent("id_semestre");

        seccion_id_aux = aInteger(id.getValue().toString());
        semestre_id_aux = aInteger(id_semestre.getValue().toString());

        limpiar();
        mostrarTablaHorarios();
    }

    public void GenerarHorario(ActionEvent event) throws Exception {
        limpiar();
        lstDocDisp = new ArrayList<BeanDocente>();
        this.setB_facultad(0);
        this.setSemestre_f(0);
        this.setB_especialidad(0);
        this.setB_ciclos("0");
        //tablaHorario.clear();
        mostrarTablaHorarios2();
    }

    public void VerGenHor(ActionEvent event) throws Exception {
        UIParameter id = (UIParameter) event.getComponent().findComponent("id_seccion");
        UIParameter id_semestre = (UIParameter) event.getComponent().findComponent("id_semestre");

        seccion_id_aux = aInteger(id.getValue().toString());
        semestre_id_aux = aInteger(id_semestre.getValue().toString());

        limpiar();
        mostrarTablaHorarios();
    }

    public void limpiar() {
        listaHorario.clear();
        this.turnoDisponibilidad_id = 0;
        this.estadoAperturado = false;
        this.estadoAsignado = false;
        this.estadoDocDisponible = false;
        this.setN_Aperturados(0);
        this.setN_NoAperturados(0);
        this.setN_Asignado(0);
        this.setN_NoAsignado(0);
        this.setN_NoDocDisponible(0);
        this.setN_DocDisponible(0);
//        aula_id_aux = 0;
//        docente_id_aux = 0;
//        tur_id_aux = 0;
        this.aula_id = 0;
        this.docente_id = 0;
        this.turno_id = 0;
        this.turnoGeneracion_id = 0;
//        this.setEstadoAperturado(false);
//        this.setEstadoAsignado(false);
//        this.setEstadoDocDisponible(false);
//        tablaHorarioGenerado.clear();

        this.setVerGeneracion(false);

    }
    
    public void limpiarGeneracion() {
        listaHorario.clear();
        this.turnoDisponibilidad_id = 0;
        this.estadoAperturado = false;
        this.estadoAsignado = false;
        this.estadoDocDisponible = false;
        this.setB_aulas(0);
        this.setB_ciclos("000000");
        this.setB_especialidad(0);
        this.setB_facultad(0);
        this.setSemestre_f(0);
        this.setB_generacion(0);
        this.setTablaHorarioGenerado(null);
        this.setLstDocDisp(null);
        
        this.setN_Aperturados(0);
        this.setN_NoAperturados(0);
        this.setN_Asignado(0);
        this.setN_NoAsignado(0);
        this.setN_NoDocDisponible(0);
        this.setN_DocDisponible(0);
//        aula_id_aux = 0;
//        docente_id_aux = 0;
//        tur_id_aux = 0;
        this.aula_id = 0;
        this.docente_id = 0;
        this.turno_id = 0;
        this.turnoGeneracion_id = 0;
//        this.setEstadoAperturado(false);
//        this.setEstadoAsignado(false);
//        this.setEstadoDocDisponible(false);
//        tablaHorarioGenerado.clear();

        this.setVerGeneracion(false);

    }

    public void limpiarReporte() {
        listaHorario.clear();
        this.estadoAperturado = false;
        this.estadoAsignado = false;
        this.estadoDocDisponible = false;
        this.estadoCurDisponible = false;
        this.setN_Aperturados(0);
        this.setN_NoAperturados(0);
        this.setN_Asignado(0);
        this.setN_NoAsignado(0);
        this.setN_NoDocDisponible(0);
        this.setN_DocDisponible(0);

    }

    public void mostrarTablaHorarios() throws Exception {
        HSTurnoDetalleDAO dao = (HSTurnoDetalleDAO) ServiceFinder.findBean("HibernateSpringDaoTurnoDetalle");
        int tur_id_aux = this.getTurno_id();
        List lista = dao.seleccionarTurnoDetalle(tur_id_aux);
        HSTurnoDAO daoTurno = (HSTurnoDAO) ServiceFinder.findBean("HibernateSpringDaoTurno");

        int tiempo;
        AcTurno tmpTurno = daoTurno.obtenerTurno(tur_id_aux);
        if (tmpTurno != null) {
            tiempo = daoTurno.obtenerTurno(tur_id_aux).getTurTiempoPeriodo();
        } else {
            tiempo = 0;
        }
        List<bHorario> lista_horario = new ArrayList<bHorario>();
        bHorario horario;
        for (int i = 0; i < lista.size(); i++) {
            Object O[] = (Object[]) lista.get(i);
            horario = new bHorario();
            horario.setId_hor(aInteger(O[0].toString()));
            horario.setInicio_hor(O[1].toString());
            int j = i + 1;
            Object P[];
            if (j == lista.size()) {
                P = (Object[]) lista.get(i);
                P[1] = suma(P[1].toString(), tiempo);
            } else {
                P = (Object[]) lista.get(j);
            }
            horario.setFin_hor(P[1].toString());
            llenarDias(horario, Integer.valueOf(O[0].toString()), seccion_id_aux);
            lista_horario.add(horario);
        }
        this.setTablaHorario(lista_horario);
    }

    public void mostrarTablaHorarios2() throws Exception {
        HSTurnoDetalleDAO dao = (HSTurnoDetalleDAO) ServiceFinder.findBean("HibernateSpringDaoTurnoDetalle");
        int tur_id_aux = this.getTurnoGeneracion_id();

        List lista = dao.seleccionarTurnoDetalle(tur_id_aux);
        HSTurnoDAO daoTurno = (HSTurnoDAO) ServiceFinder.findBean("HibernateSpringDaoTurno");

        int tiempo;
        AcTurno tmpTurno = daoTurno.obtenerTurno(tur_id_aux);
        if (tmpTurno != null) {
            tiempo = daoTurno.obtenerTurno(tur_id_aux).getTurTiempoPeriodo();
        } else {
            tiempo = 0;
        }
        List<bHorario> lista_horario = new ArrayList<bHorario>();
        bHorario horario;
        asignarColor();
        for (int i = 0; i < lista.size(); i++) {
            Object O[] = (Object[]) lista.get(i);
            horario = new bHorario();
            horario.setId_hor(aInteger(O[0].toString()));
            horario.setInicio_hor(O[1].toString());
            int j = i + 1;
            Object P[];
            if (j == lista.size()) {
                P = (Object[]) lista.get(i);
                P[1] = suma(P[1].toString(), tiempo);
            } else {
                P = (Object[]) lista.get(j);
            }
            horario.setFin_hor(P[1].toString());
            llenarDiasGen(horario, Integer.valueOf(O[0].toString()), this.getB_generacion());
            lista_horario.add(horario);
        }
        this.setTablaHorarioGenerado(lista_horario);
    }

    private String suma(String hora, int minutos) {
        int minutos_t = aInteger(hora.substring(0, 2)) * 60 + aInteger(hora.substring(3, 5));
        int ho = (minutos_t + minutos) / 60;
        int mi = (minutos_t + minutos) % 60;
        return (ho + ":" + mi + ":00");
    }

    private void generarHorarioGenetico() {
        int tur_id_aux = this.getTurnoGeneracion_id();
        int sec_id = this.getW_secEspeid();
        geneticoTurnoSeccion(sec_id);
//        int sec_id = this.get
    }

    private void geneticoTurnoSeccion(int sec_id) {
//       List<AcDocente> lstDocentes = new ArrayList<AcDocente>();
//       List<Integer> lstIntegers = new ArrayList<Integer>();
//
//       lstIntegers.add(1);
//       lstIntegers.add(3);
//       lstIntegers.add(12);
//       lstIntegers.add(15);
//       lstIntegers.add(16);
//       lstIntegers.add(21);
//       HSDocenteDAO daoDocente = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");
//        for (Integer codCurso : lstIntegers) {
//            lstDocenteGeneracion.add(daoDocente.buscarDocente(codCurso));
//        }
//        buscarDocente();

        //datos de combos para la generacion
        int facultadParametro = this.getB_facultad(); //facultad combo
        int semestreParametro = this.getSemestre_f(); //semestre combo
        int especialidadParametro = this.getB_especialidad(); // especialidad combo
        int turnoParametro = this.getTurnoGeneracion_id(); // turno combo
        String cicloParametro = this.getB_ciclos(); // ciclo combo
        String turnoDesc = obtenerTurno(turnoParametro);
//        int seccionParametro = this.getW_secEspeid(); // seccion combo
        int aulaParametro = this.getB_aulas(); //aula comnbo
//        
        Aula_Arreglo aula_arreglo; // arreglo donde se guardan la informacion de aulas
        Curso_Arreglo curso_arreglo; // arreglo donde se guardan Curso
        Dia_Arreglo dia_arreglo; // arreglo donde se almacenan dias de dictado
        Horario_Arreglo horario_arreglo; // arreglo donde se guardan las horas de dictado
        Profesor_Arreglo profesor_arreglo; // arreglo donde se almacenan la informacion de cada profesor
        ProfesorCurso_Arreglo profcurso_arreglo; // arreglo donde se guarda el profesor y el curso que tiene asignado
        HorasMaximo_Arreglo horasmax_arreglo; // arreglo donde se almacena la hora y su representacion maxima
        Calificacion_Arreglo calificacion_arreglo; // arreglo donde se guardan los resultados de cada horario de aula generado
        Individuo_Arreglo individuo_arreglo;
        Generacion_Dao gd;
        ProfesorDisponibilidad_Arreglo profdisponibilidad_arreglo;
        aula_arreglo = new Aula_Arreglo();
        curso_arreglo = new Curso_Arreglo();
        dia_arreglo = new Dia_Arreglo();
        horario_arreglo = new Horario_Arreglo();
        profesor_arreglo = new Profesor_Arreglo();
        profcurso_arreglo = new ProfesorCurso_Arreglo();
        horasmax_arreglo = new HorasMaximo_Arreglo();
        calificacion_arreglo = new Calificacion_Arreglo();
        profdisponibilidad_arreglo = new ProfesorDisponibilidad_Arreglo();

        Aula aula1 = new Aula(1, String.valueOf(aulaParametro));

        /*REFERENCIA*/
//        Aula aula2 = new Aula(2, "302");
/*FIN REFERENCIA*/
        aula_arreglo.agregaAula(aula1);
        HSHorarioDAO dao = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoHorario");
        List<BeanReporte> lstDocentes = dao.listarDocentesPorCicloSemestreFacultad(semestreParametro,facultadParametro,especialidadParametro,cicloParametro, turnoParametro, turnoDesc);
        for (BeanReporte beanReporte : lstDocentes) {
            Profesor profesor = new Profesor(Integer.parseInt(beanReporte.getExpr1()), beanReporte.getExpr2());
            profesor_arreglo.agregaProfesor(profesor);
            System.out.println("Docente ID:" + profesor.getId() + " // Docente COD_ID" + profesor.getNombres());
        }

        /*REFERENCIA*/
    // agrego informacion de profesores
//        Profesor p1 = new Profesor(1, "Ayala");
//        profesor_arreglo.agregaProfesor(p1);
 /*FIN REFERENCIA*/
        // agrego informacion de disponibilidad de profesores
        List<BeanReporte> lstDisponibilidadDocente = dao.listarDisponibilidadDocentePorCicloSemestreFacultad(semestreParametro, facultadParametro, especialidadParametro, cicloParametro, turnoParametro, turnoDesc);
        HashMap<Integer, List<String>> hmOne = new LinkedHashMap<Integer, List<String>>();
        for (BeanReporte beanReporte : lstDisponibilidadDocente) {
            int key = Integer.parseInt(beanReporte.getExpr1());
            if (!hmOne.containsKey(key)) {
                hmOne.put(key, new LinkedList<String>());
                hmOne.get(key).add(beanReporte.getExpr2());
            } else {
                hmOne.get(key).add(beanReporte.getExpr2());
            }
        }

        for (Map.Entry<Integer, List<String>> entry : hmOne.entrySet()) {
            Integer idDocente = entry.getKey();
            List<String> lstDisponibilidad = entry.getValue();
            int codi_profe = profesor_arreglo.buscarProfesor(idDocente);
            String[] diaHora = new String[lstDisponibilidad.size()];
            lstDisponibilidad.toArray(diaHora);
            Profesor_Disponibilidad proDis = new Profesor_Disponibilidad(codi_profe, diaHora);
            profdisponibilidad_arreglo.agregaProfesor_Disponibilidad(proDis);
        }

        /*REFERENCIA*/
//        String[] dia_h_1 = {"11", "12", "13", "14", "15"};
//        Profesor_Disponibilidad pd1 = new Profesor_Disponibilidad(1, dia_h_1);
//        profdisponibilidad_arreglo.agregaProfesor_Disponibilidad(pd1);
/*FIN REFERENCIA*/
        // agrego informacion de cursos
        List<BeanReporte> lstCursos = dao.listarCursosPorCicloSemestreFacultad(semestreParametro, facultadParametro, especialidadParametro, cicloParametro, turnoDesc);
        for (BeanReporte beanReporte : lstCursos) {
            Curso curso = new Curso(Integer.parseInt(beanReporte.getExpr1()), beanReporte.getExpr3(), Integer.parseInt(beanReporte.getExpr4()));
            curso_arreglo.agregaCurso(curso);
        }

        /*REFERENCIA*/
//        Curso c1 = new Curso(1, "Artificial", 2);
//        curso_arreglo.agregaCurso(c1);
        /*FIN REFERENCIA*/
        // agrego horas de dictado
        List<BeanReporte> lstHorario = dao.listarHorarioPorSemestreTurno(semestre_f, turnoParametro);
        for (BeanReporte beanReporte : lstHorario) {
            Horario horario = new Horario(Integer.parseInt(beanReporte.getExpr1()), beanReporte.getExpr2());
            horario_arreglo.agregaHorario(horario);
        }

        //horarioCopado
//        List<BeanReporte> lstHorarioLabCopado = dao.listarHorarioLabCopadoPorTurno(turnoParametro);
//        for (BeanReporte beanReporte : lstHorarioLabCopado) {
//            Horario horario = new Horario(Integer.parseInt(beanReporte.getExpr1()), beanReporte.getExpr2());
//            horario_arreglo.agregaHorario(horario);
//        }

        /*REFERENCIA*/
//        Horario h1 = new Horario(1, "7:00-7:40");
//        horario_arreglo.agregaHorario(h1);
/*FIN REFERENCIA*/
        // agrego dias de dictado
        Dia d1 = new Dia(1, "Lunes");
        Dia d2 = new Dia(2, "Martes");
        Dia d3 = new Dia(3, "Miercoles");
        Dia d4 = new Dia(4, "Jueves");
        Dia d5 = new Dia(5, "Viernes");
        dia_arreglo.agregaDia(d1);
        dia_arreglo.agregaDia(d2);
        dia_arreglo.agregaDia(d3);
        dia_arreglo.agregaDia(d4);
        dia_arreglo.agregaDia(d5);
        // Enlazo Profesor con cursos de dictado
        for (BeanReporte beanReporte : lstCursos) {
            Profesor prof = profesor_arreglo.buscarProfesorporID_Aca(Integer.parseInt(beanReporte.getExpr2()));
            Curso cur = curso_arreglo.buscaCurso(Integer.parseInt(beanReporte.getExpr3()));
            Profesor_Curso profesorCurso = new Profesor_Curso(prof, cur);
            profcurso_arreglo.agregaProfesor_Curso(profesorCurso);
        }

        // Configuracion de cantidad de horas y valor maximo
        HorasMaximo hm0 = new HorasMaximo(0, 0);
        HorasMaximo hm1 = new HorasMaximo(1, 2);
        HorasMaximo hm2 = new HorasMaximo(2, 10);
        HorasMaximo hm3 = new HorasMaximo(3, 30);
        HorasMaximo hm4 = new HorasMaximo(4, 68);
        HorasMaximo hm5 = new HorasMaximo(5, 130);
        HorasMaximo hm6 = new HorasMaximo(6, 222);
        HorasMaximo hm7 = new HorasMaximo(7, 350);
        HorasMaximo hm8 = new HorasMaximo(8, 354);
        HorasMaximo hm9 = new HorasMaximo(9, 364);
        HorasMaximo hm10 = new HorasMaximo(10, 386);
        HorasMaximo hm11 = new HorasMaximo(11, 426);
        HorasMaximo hm12 = new HorasMaximo(12, 490);
        HorasMaximo hm13 = new HorasMaximo(13, 584);
        HorasMaximo hm14 = new HorasMaximo(14, 714);
        HorasMaximo hm15 = new HorasMaximo(15, 720);
        HorasMaximo hm16 = new HorasMaximo(16, 732);
        HorasMaximo hm17 = new HorasMaximo(17, 756);
        HorasMaximo hm18 = new HorasMaximo(18, 798);
        HorasMaximo hm19 = new HorasMaximo(19, 864);
        HorasMaximo hm20 = new HorasMaximo(20, 960);
        HorasMaximo hm21 = new HorasMaximo(21, 1092);
        HorasMaximo hm22 = new HorasMaximo(22, 1100);
        HorasMaximo hm23 = new HorasMaximo(23, 1114);
        HorasMaximo hm24 = new HorasMaximo(24, 1140);
        HorasMaximo hm25 = new HorasMaximo(25, 1184);
        HorasMaximo hm26 = new HorasMaximo(26, 1252);
        HorasMaximo hm27 = new HorasMaximo(27, 1350);
        HorasMaximo hm28 = new HorasMaximo(28, 1484);
        HorasMaximo hm29 = new HorasMaximo(29, 1494);
        HorasMaximo hm30 = new HorasMaximo(30, 1510);
        HorasMaximo hm31 = new HorasMaximo(31, 1538);
        HorasMaximo hm32 = new HorasMaximo(32, 1584);
        HorasMaximo hm33 = new HorasMaximo(33, 1654);
        HorasMaximo hm34 = new HorasMaximo(34, 1754);
        HorasMaximo hm35 = new HorasMaximo(35, 1890);

        horasmax_arreglo.agregaHorasMaximo(hm0);
        horasmax_arreglo.agregaHorasMaximo(hm1);
        horasmax_arreglo.agregaHorasMaximo(hm2);
        horasmax_arreglo.agregaHorasMaximo(hm3);
        horasmax_arreglo.agregaHorasMaximo(hm4);
        horasmax_arreglo.agregaHorasMaximo(hm5);
        horasmax_arreglo.agregaHorasMaximo(hm6);
        horasmax_arreglo.agregaHorasMaximo(hm7);
        horasmax_arreglo.agregaHorasMaximo(hm8);
        horasmax_arreglo.agregaHorasMaximo(hm9);
        horasmax_arreglo.agregaHorasMaximo(hm10);
        horasmax_arreglo.agregaHorasMaximo(hm11);
        horasmax_arreglo.agregaHorasMaximo(hm12);
        horasmax_arreglo.agregaHorasMaximo(hm13);
        horasmax_arreglo.agregaHorasMaximo(hm14);
        horasmax_arreglo.agregaHorasMaximo(hm15);
        horasmax_arreglo.agregaHorasMaximo(hm16);
        horasmax_arreglo.agregaHorasMaximo(hm17);
        horasmax_arreglo.agregaHorasMaximo(hm18);
        horasmax_arreglo.agregaHorasMaximo(hm19);
        horasmax_arreglo.agregaHorasMaximo(hm20);
        horasmax_arreglo.agregaHorasMaximo(hm21);
        horasmax_arreglo.agregaHorasMaximo(hm22);
        horasmax_arreglo.agregaHorasMaximo(hm23);
        horasmax_arreglo.agregaHorasMaximo(hm24);
        horasmax_arreglo.agregaHorasMaximo(hm25);
        horasmax_arreglo.agregaHorasMaximo(hm26);
        horasmax_arreglo.agregaHorasMaximo(hm27);
        horasmax_arreglo.agregaHorasMaximo(hm28);
        horasmax_arreglo.agregaHorasMaximo(hm29);
        horasmax_arreglo.agregaHorasMaximo(hm30);
        horasmax_arreglo.agregaHorasMaximo(hm31);
        horasmax_arreglo.agregaHorasMaximo(hm32);
        horasmax_arreglo.agregaHorasMaximo(hm33);
        horasmax_arreglo.agregaHorasMaximo(hm34);
        horasmax_arreglo.agregaHorasMaximo(hm35);

        // creo matriz donde se guardaran los horarios en aula 1 y 2
//        System.out.println("aula:" + aula_arreglo.Tamanio());
//        System.out.println("curso:" + curso_arreglo.Tamanio());
//        System.out.println("horario:" + horario_arreglo.Tamanio());
//        System.out.println("profesor:" + profesor_arreglo.Tamanio());
//        System.out.println("profe_curso:" + profcurso_arreglo.Tamanio());
//        System.out.println("horas maximas:" + aula_arreglo.Tamanio());
//        System.out.println("calificacion:" + calificacion_arreglo.Tamanio());
//        System.out.println("disponibilidad" + profdisponibilidad_arreglo.Tamanio());
//        System.out.println("dia" + dia_arreglo.Tamanio());
        Ejec1 o = new Ejec1(aula_arreglo, curso_arreglo, horario_arreglo, profesor_arreglo, profcurso_arreglo, horasmax_arreglo, calificacion_arreglo, profdisponibilidad_arreglo, dia_arreglo);
        String a = o.genera_horario();
        System.out.println(a);
        HSHorarioGenDAO daoHorarioGen = (HSHorarioGenDAO) ServiceFinder.findBean("SpringHibernateDaoHorarioGen");
        gd = new Generacion_Dao();

        HorarioGenerado_Arreglo lstHorariosGenerados = gd.traeHorariosGenerados(horario_arreglo, curso_arreglo, profesor_arreglo);
        List<AcHorarioGen> lstHorarioGen = new ArrayList<AcHorarioGen>();
        AcHorarioGen acHorarioGen;
        for (int i = 0; i < lstHorariosGenerados.Tamanio(); i++) {
            HSDocenteDAO daoDocente = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");
            HSTurnoDetalleDAO daoTurnoDetalle = (HSTurnoDetalleDAO) ServiceFinder.findBean("HibernateSpringDaoTurnoDetalle");
            HSAulaDAO daoAula = (HSAulaDAO) ServiceFinder.findBean("HibernateSpringDaoTargetAula");
            HSCatalogoDAO daoCatalogo = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            HSSeccionDAO daoCurso = (HSSeccionDAO) ServiceFinder.findBean("HibernateSpringDaoTargetSeccion");
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession session1 = (HttpSession) context.getSession(false);
            bUsuario usu = (bUsuario) session1.getAttribute("usuario");

            acHorarioGen = new AcHorarioGen();

            acHorarioGen.setDoc(daoDocente.buscarDocente(lstHorariosGenerados.getHorarioGenerado(i).getDocente()));

            acHorarioGen.setTurdet(daoTurnoDetalle.buscarAcTurnoDetalle(lstHorariosGenerados.getHorarioGenerado(i).getBloque_horario()));
            acHorarioGen.setAul(daoAula.buscarAula(aulaParametro));
            acHorarioGen.setHorDia("01600" + lstHorariosGenerados.getHorarioGenerado(i).getDia());
            acHorarioGen.setGeneracion(lstHorariosGenerados.getHorarioGenerado(i).getCod_horario_generado());
            acHorarioGen.setHorCreacion(lstHorariosGenerados.getHorarioGenerado(i).getFecha());
            acHorarioGen.setSec(daoCurso.buscarSeccionID(lstHorariosGenerados.getHorarioGenerado(i).getCurso()));
            acHorarioGen.setHorActivo("1");
            acHorarioGen.setHorTipoClase("018001");
            acHorarioGen.setUsuario_id(usu.getId_usu());
            lstHorarioGen.add(acHorarioGen);

            System.out.println("docente:" + lstHorariosGenerados.getHorarioGenerado(i).getDocente());
            System.out.println("tur_det:" + lstHorariosGenerados.getHorarioGenerado(i).getBloque_horario());
            System.out.println("Dia:" + lstHorariosGenerados.getHorarioGenerado(i).getDia());
            System.out.println("Generacion:" + lstHorariosGenerados.getHorarioGenerado(i).getCod_horario_generado());
            System.out.println("Curso:" + lstHorariosGenerados.getHorarioGenerado(i).getCurso());
        }

        if (!lstHorarioGen.isEmpty()) {
            daoHorarioGen.eliminarHorarios();
            daoHorarioGen.insertarHorarios(lstHorarioGen);
            this.setVerGeneracion(true);
        } else {
            this.setVerGeneracion(false);
        }

        lstIntegers = new ArrayList<Integer>();

        for (int i = 0; i < profesor_arreglo.Tamanio(); i++) {

            lstIntegers.add(Integer.parseInt(profesor_arreglo.getProfesor(i).getNombres()));

        }

//
//       lstIntegers.add(1);
//       lstIntegers.add(3);
//       lstIntegers.add(12);
//       lstIntegers.add(15);
//       lstIntegers.add(16);
//       lstIntegers.add(21);
//       HSDocenteDAO daoDocente = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");
//        for (Integer codCurso : lstIntegers) {
//            lstDocenteGeneracion.add(daoDocente.buscarDocente(codCurso));
//        }
//        buscarDocente();
    }

    public void buscarDocente() {
        try {
            List<BeanDocente> lstDocenteDisponibilidad = new ArrayList<BeanDocente>();
            HSDocenteDAO daoDOCHor = CommonDAO.getAcDocenteDAO();

            for (AcDocente beanDocente : lstDocenteGeneracion) {

                BeanDocente docenteAux = new BeanDocente();
                docenteAux.setId(beanDocente.getId());
                docenteAux.setDocCodigo(beanDocente.getDocCodigo());
                docenteAux.setDocDni(beanDocente.getDocDni());
                docenteAux.setDocNombre(beanDocente.getDocNombre());
                docenteAux.setDocCorreo(beanDocente.getDocCorreo());
                docenteAux.setDocTelefono(beanDocente.getDocTelefono());
                docenteAux.setDocSituacion(beanDocente.getDocSituacion());
                docenteAux.setDocTipo(beanDocente.getDocTipo());
                if (daoDOCHor.seleccionarHorario(docenteAux.getId()).isEmpty()) {
                    docenteAux.setImagen_horario("/Imagenes/actions/horario_gris.png");
                } else {
                    docenteAux.setImagen_horario("/Imagenes/actions/horario.png");
                    // sProfesor = daoClHor.seleccionarHorario( tmp_sec.getSecId() ).get( 0 ).getAcDocente().getDocNombre();
                }
                lstDocenteDisponibilidad.add(docenteAux);
            }

            this.setLstDocDisp(lstDocenteDisponibilidad);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void llenarDias(bHorario horario, Integer id_turno, int seccion) {
        HSHorarioDAO dao = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoHorario");
        HSCatalogoDAO daoCatalogo = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        List<AcHorario> horarios = dao.seleccionarHorarioSeccion(seccion);
        List<AcHorario> horariosLab;
        int horasLab = 0;
        for (int i = 0; i < horarios.size(); i++) {
            if ((horarios.get(i).getTurdet().getId()).compareTo(id_turno) == 0) {
                String dias = horarios.get(i).getHorDia();
                int seccionG = horarios.get(i).getSec().getId();
//                String mensaje = "id: " + horarios.get(i).getId()
//                        + " doc: " + horarios.get(i).getDoc().getDocNombre()
//                        + " aul:" + horarios.get(i).getAul().getAulDes()
//                        + " sec:" + horarios.get(i).getSec().getSecNombre()
//                        + " tur:" + horarios.get(i).getTurdet().getId()
//                        + " tipo:" + horarios.get(i).getHorTipoClase();
                String mensaje = horarios.get(i).getSec().getCurape().getPlancur().getCur().getCurNombre() + " / " + daoCatalogo.seleccionarDescripcion(horarios.get(i).getHorTipoClase());
                String mensajeTool = horarios.get(i).getDoc().getDocNombre();
                horasLab = horarios.get(i).getSec().getCurape().getPlancur().getCur().getCurHorLab();

                if (dias.equals("016001")) {
                    if (seccionG == seccion) {
                        horario.setB_horarioEstablecidoLunes(true);
                        horario.setbColorCeldaHorario_Lunes(buscarColorTipo(0));
                    } else {
                        horario.setB_horarioEstablecidoLunes(false);
                    }
                    horario.setLunes_hor(mensaje.toLowerCase());
                    horario.setLunes_hor_tp(mensajeTool.toLowerCase());
                    horario.setIndice_id_lun(horarios.get(i).getId());
                }
                if (dias.equals("016002")) {
                    if (seccionG == seccion) {
                        horario.setB_horarioEstablecidoMartes(true);
                        horario.setbColorCeldaHorario_Martes(buscarColorTipo(0));
                    } else {
                        horario.setB_horarioEstablecidoMartes(false);
                    }
                    horario.setMartes_hor(mensaje.toLowerCase());
                    horario.setMartes_hor_tp(mensajeTool.toLowerCase());
                    horario.setIndice_id_mar(horarios.get(i).getId());
                }
                if (dias.equals("016003")) {
                    if (seccionG == seccion) {
                        horario.setB_horarioEstablecidoMiercoles(true);
                        horario.setbColorCeldaHorario_Miercoles(buscarColorTipo(0));
                    } else {
                        horario.setB_horarioEstablecidoMiercoles(false);
                    }
                    horario.setMiercoles_hor(mensaje.toLowerCase());
                    horario.setMiercoles_hor_tp(mensajeTool.toLowerCase());
                    horario.setIndice_id_mie(horarios.get(i).getId());
                }
                if (dias.equals("016004")) {
                    if (seccionG == seccion) {
                        horario.setB_horarioEstablecidoJueves(true);
                        horario.setbColorCeldaHorario_Jueves(buscarColorTipo(0));
                    } else {
                        horario.setB_horarioEstablecidoJueves(false);
                    }
                    horario.setJueves_hor(mensaje.toLowerCase());
                    horario.setJueves_hor_tp(mensajeTool.toLowerCase());
                    horario.setIndice_id_jue(horarios.get(i).getId());
                }
                if (dias.equals("016005")) {
                    if (seccionG == seccion) {
                        horario.setB_horarioEstablecidoViernes(true);
                        horario.setbColorCeldaHorario_Viernes(buscarColorTipo(0));
                    } else {
                        horario.setB_horarioEstablecidoViernes(false);
                    }
                    horario.setViernes_hor(mensaje.toLowerCase());
                    horario.setViernes_hor_tp(mensajeTool.toLowerCase());
                    horario.setIndice_id_vie(horarios.get(i).getId());
                }
                if (dias.equals("016006")) {
                    if (seccionG == seccion) {
                        horario.setB_horarioEstablecidoSabado(true);
                        horario.setbColorCeldaHorario_Sabado(buscarColorTipo(0));
                    } else {
                        horario.setB_horarioEstablecidoSabado(false);
                    }
                    horario.setSabado_hor(mensaje.toLowerCase());
                    horario.setSabado_hor_tp(mensajeTool.toLowerCase());
                    horario.setIndice_id_sab(horarios.get(i).getId());
                }
                if (dias.equals("016007")) {
                    if (seccionG == seccion) {
                        horario.setB_horarioEstablecidoDomingo(true);
                        horario.setbColorCeldaHorario_Domingo(buscarColorTipo(0));
                    } else {
                        horario.setB_horarioEstablecidoDomingo(false);
                    }
                    horario.setDomingo_hor(mensaje.toLowerCase());
                    horario.setDomingo_hor_tp(mensajeTool.toLowerCase());
                    horario.setIndice_id_dom(horarios.get(i).getId());
                }
            }
        }
        if (horasLab > 0) {
            horariosLab = dao.HorarioDisponibleLaboratorio(seccion);
            for (int i = 0; i < horariosLab.size(); i++) {
                if ((horariosLab.get(i).getTurdet().getId()).compareTo(id_turno) == 0) {
                    String dias = horariosLab.get(i).getHorDia();
                    int seccionG = horariosLab.get(i).getSec().getId();
//                String mensaje = "id: " + horarios.get(i).getId()
//                        + " doc: " + horarios.get(i).getDoc().getDocNombre()
//                        + " aul:" + horarios.get(i).getAul().getAulDes()
//                        + " sec:" + horarios.get(i).getSec().getSecNombre()
//                        + " tur:" + horarios.get(i).getTurdet().getId()
//                        + " tipo:" + horarios.get(i).getHorTipoClase();
                    String mensaje = " LAB. DISP.";
                    if (dias.equals("016001")) {
                        if (seccionG == seccion) {
                            horario.setbColorCeldaHorario_Lunes(colorLaboratorioLibre());
                            horario.setLunes_hor(horario.getLunes_hor() + mensaje.toLowerCase());
                        }
                    }
                    if (dias.equals("016002")) {
                        if (seccionG == seccion) {
                            horario.setbColorCeldaHorario_Martes(colorLaboratorioLibre());
                            horario.setMartes_hor(horario.getMartes_hor() + mensaje.toLowerCase());
                        }
                    }
                    if (dias.equals("016003")) {
                        if (seccionG == seccion) {
                            horario.setbColorCeldaHorario_Miercoles(colorLaboratorioLibre());
                            horario.setMiercoles_hor(horario.getMiercoles_hor() + mensaje.toLowerCase());
                        }
                    }
                    if (dias.equals("016004")) {
                        if (seccionG == seccion) {
                            horario.setbColorCeldaHorario_Jueves(colorLaboratorioLibre());
                            horario.setJueves_hor(horario.getJueves_hor() + mensaje.toLowerCase());
                        }
                    }
                    if (dias.equals("016005")) {
                        if (seccionG == seccion) {
                            horario.setbColorCeldaHorario_Viernes(colorLaboratorioLibre());
                            horario.setViernes_hor(horario.getViernes_hor() + mensaje.toLowerCase());
                        }
                    }
                    if (dias.equals("016006")) {
                        if (seccionG == seccion) {
                            horario.setbColorCeldaHorario_Sabado(colorLaboratorioLibre());
                            horario.setSabado_hor(horario.getSabado_hor() + mensaje.toLowerCase());
                        }
                    }
                    if (dias.equals("016007")) {
                        if (seccionG == seccion) {
                            horario.setbColorCeldaHorario_Domingo(colorLaboratorioLibre());
                            horario.setLunes_hor(horario.getDomingo_hor() + mensaje.toLowerCase());
                        }
                    }
                }
            }
        }
        if (this.getDocente_id() != 0) {
            List<AcHorario> horariosNoDisponibleDocente = dao.HorarioNoDisponibleDocente(this.docente_id, seccion);
            List<AcHorarioDispDocente> horariosSiDisponibleDocente = dao.HorarioSiDisponibleDocente(this.docente_id, seccion, this.turno_id);
            for (int i = 0; i < horariosNoDisponibleDocente.size(); i++) {
                if ((horariosNoDisponibleDocente.get(i).getTurdet().getId()).compareTo(id_turno) == 0) {
                    String dias = horariosNoDisponibleDocente.get(i).getHorDia();
                    int seccionG = horariosNoDisponibleDocente.get(i).getSec().getId();

                    String mensaje = "/ ASIGNADO OTRA SECCION";
                    // horariosNoDisponibleDocente.get(i).getSec().getCurape().getPlancur().getCur().getCurNombre()+" / "+horarios.get(i).getHorTipoClase();

                    if (dias.equals("016001")) {
                        if (seccionG == seccion) {
                            horario.setB_horarioEstablecidoLunes(true);
                            horario.setLunes_hor(horario.getLunes_hor() + mensaje.toLowerCase());
                        } else {
                            horario.setB_horarioEstablecidoLunes(false);
                            horario.setLunes_hor(horario.getLunes_hor() + mensaje.toLowerCase());
                            horario.setbColorCeldaHorario_Lunes(buscarColorTipo(1));
                        }

                        horario.setIndice_id_lun(horarios.get(i).getId());
                    }
                    if (dias.equals("016002")) {
                        if (seccionG == seccion) {
                            horario.setB_horarioEstablecidoMartes(true);
                            horario.setMartes_hor(horario.getMartes_hor() + mensaje.toLowerCase());
                        } else {
                            horario.setB_horarioEstablecidoMartes(false);
                            horario.setMartes_hor(horario.getMartes_hor() + mensaje.toLowerCase());
                            horario.setbColorCeldaHorario_Martes(buscarColorTipo(1));
                        }
                        horario.setIndice_id_mar(horarios.get(i).getId());
                    }
                    if (dias.equals("016003")) {
                        if (seccionG == seccion) {
                            horario.setB_horarioEstablecidoMiercoles(true);
                            horario.setMiercoles_hor(horario.getMiercoles_hor() + mensaje.toLowerCase());
                        } else {
                            horario.setB_horarioEstablecidoMiercoles(false);
                            horario.setMiercoles_hor(mensaje.toLowerCase());
                            horario.setbColorCeldaHorario_Miercoles(buscarColorTipo(1));
                        }
                        horario.setIndice_id_mie(horarios.get(i).getId());
                    }
                    if (dias.equals("016004")) {
                        if (seccionG == seccion) {
                            horario.setB_horarioEstablecidoJueves(true);
                            horario.setJueves_hor(horario.getJueves_hor() + mensaje.toLowerCase());
                        } else {
                            horario.setB_horarioEstablecidoJueves(false);
                            horario.setJueves_hor(horario.getJueves_hor() + mensaje.toLowerCase());
                            horario.setbColorCeldaHorario_Jueves(buscarColorTipo(1));
                        }
                        horario.setIndice_id_jue(horarios.get(i).getId());
                    }
                    if (dias.equals("016005")) {
                        if (seccionG == seccion) {
                            horario.setB_horarioEstablecidoViernes(true);
                            horario.setViernes_hor(horario.getViernes_hor() + mensaje.toLowerCase());
                        } else {
                            horario.setB_horarioEstablecidoViernes(false);
                            horario.setViernes_hor(horario.getViernes_hor() + mensaje.toLowerCase());
                            horario.setbColorCeldaHorario_Viernes(buscarColorTipo(1));
                        }
                        horario.setIndice_id_vie(horarios.get(i).getId());
                    }
                    if (dias.equals("016006")) {
                        if (seccionG == seccion) {
                            horario.setB_horarioEstablecidoSabado(true);
                            horario.setSabado_hor(horario.getSabado_hor() + mensaje.toLowerCase());
                        } else {
                            horario.setB_horarioEstablecidoSabado(false);
                            horario.setSabado_hor(horario.getSabado_hor() + mensaje.toLowerCase());
                            horario.setbColorCeldaHorario_Sabado(buscarColorTipo(1));
                        }
                        horario.setIndice_id_sab(horarios.get(i).getId());
                    }
                    if (dias.equals("016007")) {
                        if (seccionG == seccion) {
                            horario.setB_horarioEstablecidoDomingo(true);
                            horario.setDomingo_hor(horario.getDomingo_hor() + mensaje.toLowerCase());
                        } else {
                            horario.setB_horarioEstablecidoDomingo(false);
                            horario.setDomingo_hor(horario.getDomingo_hor() + mensaje.toLowerCase());
                            horario.setbColorCeldaHorario_Domingo(buscarColorTipo(1));
                        }
                        horario.setIndice_id_dom(horarios.get(i).getId());
                    }
                }

            }

            for (int i = 0; i < horariosSiDisponibleDocente.size(); i++) {
                if ((horariosSiDisponibleDocente.get(i).getTurdet().getId()).compareTo(id_turno) == 0) {
                    String dias = horariosSiDisponibleDocente.get(i).getHorDia();

                    String mensaje = "DISPONIBLE";
                    // horariosNoDisponibleDocente.get(i).getSec().getCurape().getPlancur().getCur().getCurNombre()+" / "+horarios.get(i).getHorTipoClase();

                    if (dias.equals("016001")) {
                        horario.setB_horarioEstablecidoLunes(false);
                        horario.setB_horarioNuevoLunes(true);
                        horario.setLunes_hor(mensaje);
                        horario.setbColorCeldaHorario_Lunes(buscarColorTipo(2));
                        horario.setIndice_id_lun(horarios.get(i).getId());
                    }
                    if (dias.equals("016002")) {
                        horario.setB_horarioEstablecidoMartes(false);
                        horario.setB_horarioNuevoMartes(true);
                        horario.setMartes_hor(mensaje);
                        horario.setbColorCeldaHorario_Martes(buscarColorTipo(2));
                        horario.setIndice_id_mar(horarios.get(i).getId());
                    }
                    if (dias.equals("016003")) {
                        horario.setB_horarioEstablecidoMiercoles(false);
                        horario.setB_horarioNuevoMiercoles(true);
                        horario.setMiercoles_hor(mensaje);
                        horario.setbColorCeldaHorario_Miercoles(buscarColorTipo(2));
                        horario.setIndice_id_mie(horarios.get(i).getId());
                    }
                    if (dias.equals("016004")) {
                        horario.setB_horarioEstablecidoJueves(false);
                        horario.setB_horarioNuevoJueves(true);
                        horario.setJueves_hor(mensaje);
                        horario.setbColorCeldaHorario_Jueves(buscarColorTipo(2));
                        horario.setIndice_id_jue(horarios.get(i).getId());
                    }
                    if (dias.equals("016005")) {
                        horario.setB_horarioEstablecidoViernes(false);
                        horario.setB_horarioNuevoMiercoles(true);
                        horario.setViernes_hor(mensaje);
                        horario.setbColorCeldaHorario_Viernes(buscarColorTipo(2));

                        horario.setIndice_id_vie(horarios.get(i).getId());
                    }
                    if (dias.equals("016006")) {
                        horario.setB_horarioEstablecidoSabado(false);
                        horario.setB_horarioNuevoSabado(true);
                        horario.setSabado_hor(mensaje);
                        horario.setbColorCeldaHorario_Sabado(buscarColorTipo(2));
                        horario.setIndice_id_sab(horarios.get(i).getId());
                    }
                    if (dias.equals("016007")) {
                        horario.setB_horarioEstablecidoDomingo(false);
                        horario.setB_horarioNuevoDomingo(true);
                        horario.setDomingo_hor(mensaje);
                        horario.setbColorCeldaHorario_Domingo(buscarColorTipo(2));
                        horario.setIndice_id_dom(horarios.get(i).getId());
                    }
                }

            }

        }
    }

    public void llenarDiasGen(bHorario horario, Integer id_turno, int generacion) {
        HSHorarioGenDAO dao = (HSHorarioGenDAO) ServiceFinder.findBean("HibernateSpringDaoTargetHorarioGen");
        List<AcHorarioGen> horarios = dao.seleccionarHorarioGenPorNumeroGeneracion(generacion);
        for (int i = 0; i < horarios.size(); i++) {
            if ((horarios.get(i).getTurdet().getId()).compareTo(id_turno) == 0) {

                String dias = horarios.get(i).getHorDia();
                String mensaje = horarios.get(i).getDoc().getDocNombre()
                        + " / " + horarios.get(i).getSec().getCurape().getPlancur().getCur().getCurNombre();

                if (dias.equals("016001")) {
                    horario.setLunes_hor(mensaje);
                    horario.setbColorCeldaGenerado_Lunes(buscarColorAsignado(horarios.get(i).getDoc().getId()));
                    horario.setIndice_id_lun(horarios.get(i).getId());
                } else if (dias.equals("016002")) {
                    horario.setMartes_hor(mensaje);
                    horario.setbColorCeldaGenerado_Martes(buscarColorAsignado(horarios.get(i).getDoc().getId()));
                    horario.setIndice_id_mar(horarios.get(i).getId());
                } else if (dias.equals("016003")) {
                    horario.setMiercoles_hor(mensaje);
                    horario.setbColorCeldaGenerado_Miercoles(buscarColorAsignado(horarios.get(i).getDoc().getId()));
                    horario.setIndice_id_mie(horarios.get(i).getId());
                } else if (dias.equals("016004")) {
                    horario.setJueves_hor(mensaje);
                    horario.setbColorCeldaGenerado_Jueves(buscarColorAsignado(horarios.get(i).getDoc().getId()));
                    horario.setIndice_id_jue(horarios.get(i).getId());
                } else if (dias.equals("016005")) {
                    horario.setViernes_hor(mensaje);
                    horario.setbColorCeldaGenerado_Viernes(buscarColorAsignado(horarios.get(i).getDoc().getId()));
                    horario.setIndice_id_vie(horarios.get(i).getId());
                } else if (dias.equals("016006")) {
                    horario.setSabado_hor(mensaje);
                    horario.setbColorCeldaGenerado_Sabado(buscarColorAsignado(horarios.get(i).getDoc().getId()));
                    horario.setIndice_id_sab(horarios.get(i).getId());
                } else if (dias.equals("016007")) {
                    horario.setDomingo_hor(mensaje);
                    horario.setbColorCeldaGenerado_Domingo(buscarColorAsignado(horarios.get(i).getDoc().getId()));
                    horario.setIndice_id_dom(horarios.get(i).getId());
                }

            }
        }

    }

    public void asignarColor() {
        List<BeanDocente> lstDocentes = this.getLstDocDisp();
        int indice = 0;
        for (BeanDocente docente : lstDocentes) {
            docente.setColorAsignado(colorXProfesor(indice));
            indice++;
        }
    }

    public String buscarColorAsignado(int Profesor) {
        List<BeanDocente> lstDocentes = this.getLstDocDisp();
        String mensaje = "";
        for (BeanDocente docente : lstDocentes) {
            if (docente.getId() == Profesor) {
                mensaje = docente.getColorAsignado();
            }
        }
        return mensaje;
    }

    public String colorXProfesor(int Profesor) {
        String[] color = {"#CDFFCE", "#FFEEC3", "#FEFFBB", "#D9D7FE", "#D6F0FF", "#E4D5B8", "#D9D7FE", "#87A9D8", " #BCAF8", "#A6D1DF"};
        String cadena = "background-color:" + color[Profesor] + "; color:#000";
        return cadena;
    }

    public String colorLaboratorioLibre() {
        String cadena = "background-color:#6D1FE3; color:#FFF";
        return cadena;
    }

    public String buscarColorTipo(int tipo) {
        String[] color = {"#CDFFCE", "#FFEEC3", "#FEFFBB", "#D9D7FE", "#D6F0FF", "#E4D5B8", "#D9D7FE", "#87A9D8", " #BCAF8", "#A6D1DF"};
        String mensaje = "";
        // 1 CursoEditar
        // 2 Disponibilidad Docente
        // 3 Docente esta Dictando
        if (tipo == 0) {
            mensaje = "background-color:" + color[tipo] + "; color:#000";
        } else if (tipo == 1) {
            mensaje = "background-color:" + color[tipo] + "; color:#000";
        } else if (tipo == 2) {
            mensaje = "background-color:" + color[tipo] + "; color:#000";
        }
        return mensaje;
    }

    public List getTablaHorario() {
        return tablaHorario;
    }

    public void setTablaHorario(List tablaHorario) {
        this.tablaHorario = tablaHorario;
    }

    public List getTablaHorarioGenerado() {
        return tablaHorarioGenerado;
    }

    public void setTablaHorarioGenerado(List tablaHorarioGenerado) {
        this.tablaHorarioGenerado = tablaHorarioGenerado;
    }

    public void seleccionarLunes(ActionEvent event) {
        UIOutput lunes = (UIOutput) event.getComponent().findComponent("lunes");

        UIParameter id_turno = (UIParameter) event.getComponent().findComponent("param_id_turno");
        UIParameter ho_aula = (UIParameter) event.getComponent().findComponent("ho_aula");
        UIParameter ho_tipo = (UIParameter) event.getComponent().findComponent("ho_tipo");
        UIParameter ho_docente = (UIParameter) event.getComponent().findComponent("ho_docente");

        int aula_id_aux = aInteger(ho_aula.getValue().toString());
        String tipo_id_aux = ho_tipo.getValue().toString();
        int docente_id_aux = aInteger(ho_docente.getValue().toString());

        if (lunes.getValue().toString().equals("") || lunes.getValue().toString().equals("DISPONIBLE")) {
            System.out.println("identificador: " + ide);
            System.out.println("aula: " + aula_id_aux);
            System.out.println("tipo: " + tipo_id_aux);
            System.out.println("doc: " + docente_id_aux);

            int agregar = agregarHorario(ide, "016001", aula_id_aux, this.getId_sec_horario(),
                    aInteger(id_turno.getValue().toString()), tipo_id_aux, docente_id_aux, "1");
            if (agregar == 1) {
                this.setIndice_id_lun(ide + 1);
//                this.setLunes_hor("ide" + (ide + 1) + this.getN_cur_aperturado()
//                        + "<br>t:" + id_turno.getValue().toString()
//                        + "<br> a:" + aula_id_aux + "<br> s:" + this.getId_sec_horario()
//                        + "<br> tipo:" + tipo_id_aux + "<br> doc:" + docente_id_aux);
                this.setLunes_hor(VARIABLE_HORARIO_NUEVO_ASIGNADO);
            }
        } else {
            this.setLunes_hor("");
            quitarHorario(event, 1);
        }
    }

    public void seleccionarMartes(ActionEvent event) {
        UIOutput martes = (UIOutput) event.getComponent().findComponent("martes");

        UIParameter id_turno = (UIParameter) event.getComponent().findComponent("param_id_turno");
        UIParameter ho_aula = (UIParameter) event.getComponent().findComponent("ho_aula");
        UIParameter ho_tipo = (UIParameter) event.getComponent().findComponent("ho_tipo");
        UIParameter ho_docente = (UIParameter) event.getComponent().findComponent("ho_docente");

        int aula_id_aux = aInteger(ho_aula.getValue().toString());
        String tipo_id_aux = ho_tipo.getValue().toString();
        int docente_id_aux = aInteger(ho_docente.getValue().toString());

        if (martes.getValue().toString().equals("") || martes.getValue().toString().equals("DISPONIBLE")) {
            System.out.println("identificador: " + ide);
            System.out.println("aula: " + aula_id_aux);
            System.out.println("tipo: " + tipo_id_aux);
            System.out.println("doc: " + docente_id_aux);

            int agregar = agregarHorario(ide, "016002", aula_id_aux, this.getId_sec_horario(),
                    aInteger(id_turno.getValue().toString()), tipo_id_aux, docente_id_aux, "1");
            if (agregar == 1) {
                this.setIndice_id_mar(ide + 1);
//                this.setMartes_hor("ide" + (ide + 1) + this.getN_cur_aperturado()
//                        + "<br> t:" + id_turno.getValue().toString()
//                        + "<br> a:" + aula_id_aux + "<br> s:" + this.getId_sec_horario()
//                        + "<br> tipo:" + tipo_id_aux + "<br> doc:" + docente_id_aux);

                this.setMartes_hor(VARIABLE_HORARIO_NUEVO_ASIGNADO);
            }
        } else {
            this.setMartes_hor("");
            quitarHorario(event, 2);
        }
    }

    public void seleccionarMiercoles(ActionEvent event) {
        UIOutput miercoles = (UIOutput) event.getComponent().findComponent("miercoles");

        UIParameter id_turno = (UIParameter) event.getComponent().findComponent("param_id_turno");
        UIParameter ho_aula = (UIParameter) event.getComponent().findComponent("ho_aula");
        UIParameter ho_tipo = (UIParameter) event.getComponent().findComponent("ho_tipo");
        UIParameter ho_docente = (UIParameter) event.getComponent().findComponent("ho_docente");

        int aula_id_aux = aInteger(ho_aula.getValue().toString());
        String tipo_id_aux = ho_tipo.getValue().toString();
        int docente_id_aux = aInteger(ho_docente.getValue().toString());

        if (miercoles.getValue().toString().equals("") || miercoles.getValue().toString().equals("DISPONIBLE")) {
            System.out.println("identificador: " + ide);
            System.out.println("aula: " + aula_id_aux);
            System.out.println("tipo: " + tipo_id_aux);
            System.out.println("doc: " + docente_id_aux);

            int agregar = agregarHorario(ide, "016003", aula_id_aux, this.getId_sec_horario(),
                    aInteger(id_turno.getValue().toString()), tipo_id_aux, docente_id_aux, "1");
            if (agregar == 1) {
                this.setIndice_id_mie(ide + 1);
//                this.setMiercoles_hor("ide" + (ide + 1) + this.getN_cur_aperturado()
//                        + "<br>t:" + id_turno.getValue().toString()
//                        + "<br> a:" + aula_id_aux + "<br> s:" + this.getId_sec_horario()
//                        + "<br> tipo:" + tipo_id_aux + "<br> doc:" + docente_id_aux);
                this.setMiercoles_hor(VARIABLE_HORARIO_NUEVO_ASIGNADO);
            }
        } else {
            this.setMiercoles_hor("");
            quitarHorario(event, 3);
        }
    }

    public void seleccionarJueves(ActionEvent event) {
        UIOutput jueves = (UIOutput) event.getComponent().findComponent("jueves");

        UIParameter id_turno = (UIParameter) event.getComponent().findComponent("param_id_turno");
        UIParameter ho_aula = (UIParameter) event.getComponent().findComponent("ho_aula");
        UIParameter ho_tipo = (UIParameter) event.getComponent().findComponent("ho_tipo");
        UIParameter ho_docente = (UIParameter) event.getComponent().findComponent("ho_docente");

        int aula_id_aux = aInteger(ho_aula.getValue().toString());
        String tipo_id_aux = ho_tipo.getValue().toString();
        int docente_id_aux = aInteger(ho_docente.getValue().toString());

        if (jueves.getValue().toString().equals("") || jueves.getValue().toString().equals("DISPONIBLE")) {
            System.out.println("identificador: " + ide);
            System.out.println("aula: " + aula_id_aux);
            System.out.println("tipo: " + tipo_id_aux);
            System.out.println("doc: " + docente_id_aux);

            int agregar = agregarHorario(ide, "016004", aula_id_aux, this.getId_sec_horario(),
                    aInteger(id_turno.getValue().toString()), tipo_id_aux, docente_id_aux, "1");
            if (agregar == 1) {
                this.setIndice_id_jue(ide + 1);
//                this.setJueves_hor("ide" + (ide + 1) + this.getN_cur_aperturado()
//                        + "<br> t:" + id_turno.getValue().toString()
//                        + "<br> a:" + aula_id_aux + "<br> s:" + this.getId_sec_horario()
//                        + "<br> tipo:" + tipo_id_aux + "<br> doc:" + docente_id_aux);
                this.setJueves_hor(VARIABLE_HORARIO_NUEVO_ASIGNADO);
            }
        } else {
            this.setJueves_hor("");
            quitarHorario(event, 4);
        }
    }

    public void seleccionarLunesEditar(ActionEvent event) {
        listaHorarioEditar = new ArrayList<AcHorario>();
        UIParameter id_ident = (UIParameter) event.getComponent().findComponent("param_identificador_lunes");
        AcHorario hora = new AcHorario();
        if (aInteger(id_ident.getValue().toString()) > 0) {
            HSHorarioDAO horario = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoHorario");
            hora = horario.seleccionarUnHorario(aInteger(id_ident.getValue().toString()));
            agregarHorarioEditar(hora.getId(), hora.getHorDia(), hora.getAul().getId(),
                    hora.getSec().getId(), hora.getTurdet().getId(),
                    hora.getHorTipoClase(), hora.getDoc().getId(), "1");

        }
        this.setTipo_idEditar(hora.getHorTipoClase());
        this.setOncomplete("javascript:Richfaces.showModalPanel('mpTipoClase')");
    }

    public void seleccionarMartesEditar(ActionEvent event) {
        listaHorarioEditar = new ArrayList<AcHorario>();
        UIParameter id_ident = (UIParameter) event.getComponent().findComponent("param_identificador_martes");
        AcHorario hora = new AcHorario();
        if (aInteger(id_ident.getValue().toString()) > 0) {
            HSHorarioDAO horario = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoHorario");
            hora = horario.seleccionarUnHorario(aInteger(id_ident.getValue().toString()));
            agregarHorarioEditar(hora.getId(), hora.getHorDia(), hora.getAul().getId(),
                    hora.getSec().getId(), hora.getTurdet().getId(),
                    hora.getHorTipoClase(), hora.getDoc().getId(), "1");

        }
        this.setTipo_idEditar(hora.getHorTipoClase());
        this.setOncomplete("javascript:Richfaces.showModalPanel('mpTipoClase')");
    }

    public void seleccionarMiercolesEditar(ActionEvent event) {
        listaHorarioEditar = new ArrayList<AcHorario>();
        UIParameter id_ident = (UIParameter) event.getComponent().findComponent("param_identificador_miercoles");
        AcHorario hora = new AcHorario();
        if (aInteger(id_ident.getValue().toString()) > 0) {
            HSHorarioDAO horario = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoHorario");
            hora = horario.seleccionarUnHorario(aInteger(id_ident.getValue().toString()));
            agregarHorarioEditar(hora.getId(), hora.getHorDia(), hora.getAul().getId(),
                    hora.getSec().getId(), hora.getTurdet().getId(),
                    hora.getHorTipoClase(), hora.getDoc().getId(), "1");

        }
        this.setTipo_idEditar(hora.getHorTipoClase());
        this.setOncomplete("javascript:Richfaces.showModalPanel('mpTipoClase')");
    }

    public void seleccionarJuevesEditar(ActionEvent event) {
        listaHorarioEditar = new ArrayList<AcHorario>();
        UIParameter id_ident = (UIParameter) event.getComponent().findComponent("param_identificador_jueves");
        AcHorario hora = new AcHorario();
        if (aInteger(id_ident.getValue().toString()) > 0) {
            HSHorarioDAO horario = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoHorario");
            hora = horario.seleccionarUnHorario(aInteger(id_ident.getValue().toString()));
            agregarHorarioEditar(hora.getId(), hora.getHorDia(), hora.getAul().getId(),
                    hora.getSec().getId(), hora.getTurdet().getId(),
                    hora.getHorTipoClase(), hora.getDoc().getId(), "1");

        }
        this.setTipo_idEditar(hora.getHorTipoClase());
        this.setOncomplete("javascript:Richfaces.showModalPanel('mpTipoClase')");
    }

    public void seleccionarViernesEditar(ActionEvent event) throws Exception {
        listaHorarioEditar = new ArrayList<AcHorario>();
        UIParameter id_ident = (UIParameter) event.getComponent().findComponent("param_identificador_viernes");
        AcHorario hora = new AcHorario();
        if (aInteger(id_ident.getValue().toString()) > 0) {
            HSHorarioDAO horario = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoHorario");
            hora = horario.seleccionarUnHorario(aInteger(id_ident.getValue().toString()));
            agregarHorarioEditar(hora.getId(), hora.getHorDia(), hora.getAul().getId(),
                    hora.getSec().getId(), hora.getTurdet().getId(),
                    hora.getHorTipoClase(), hora.getDoc().getId(), "1");

        }
        this.setTipo_idEditar(hora.getHorTipoClase());
        setearTurno();
        this.setOncomplete("javascript:Richfaces.showModalPanel('mpTipoClase')");

    }

    public void seleccionarLunesEliminar(ActionEvent event) {
        listaHorarioEliminar = new ArrayList<AcHorario>();
        UIParameter id_ident = (UIParameter) event.getComponent().findComponent("param_identificador_lunes");
        this.setHordia_idEliminar(aInteger(id_ident.getValue().toString()));
        if (aInteger(id_ident.getValue().toString()) > 0) {
            HSHorarioDAO horario = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoHorario");
            AcHorario hora = horario.seleccionarUnHorario(aInteger(id_ident.getValue().toString()));
            agregarHorarioEliminar(hora.getId(), hora.getHorDia(), hora.getAul().getId(),
                    hora.getSec().getId(), hora.getTurdet().getId(),
                    hora.getHorTipoClase(), hora.getDoc().getId(), "0");

        }
        this.setOncomplete("javascript:Richfaces.showModalPanel('mpEliminarHorario')");
    }

    public void trasladarHorarioVentana(ActionEvent event) {
        int generacion = this.getB_generacion();
        if (generacion != 0) {
            this.setOncomplete("javascript:Richfaces.showModalPanel('mpTrasladarHorario')");
        } else {
            this.setOncomplete("javascript:alert('NO HA SELECCIONADO UNA GENERACION ');");
        }

    }

    public void seleccionarMartesEliminar(ActionEvent event) {
        listaHorarioEliminar = new ArrayList<AcHorario>();
        UIParameter id_ident = (UIParameter) event.getComponent().findComponent("param_identificador_martes");
        this.setHordia_idEliminar(aInteger(id_ident.getValue().toString()));
        if (aInteger(id_ident.getValue().toString()) > 0) {
            HSHorarioDAO horario = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoHorario");
            AcHorario hora = horario.seleccionarUnHorario(aInteger(id_ident.getValue().toString()));
            agregarHorarioEliminar(hora.getId(), hora.getHorDia(), hora.getAul().getId(),
                    hora.getSec().getId(), hora.getTurdet().getId(),
                    hora.getHorTipoClase(), hora.getDoc().getId(), "0");

        }
        this.setOncomplete("javascript:Richfaces.showModalPanel('mpEliminarHorario')");
    }

    public void seleccionarMiercolesEliminar(ActionEvent event) {
        listaHorarioEliminar = new ArrayList<AcHorario>();
        UIParameter id_ident = (UIParameter) event.getComponent().findComponent("param_identificador_miercoles");
        this.setHordia_idEliminar(aInteger(id_ident.getValue().toString()));
        if (aInteger(id_ident.getValue().toString()) > 0) {
            HSHorarioDAO horario = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoHorario");
            AcHorario hora = horario.seleccionarUnHorario(aInteger(id_ident.getValue().toString()));
            agregarHorarioEliminar(hora.getId(), hora.getHorDia(), hora.getAul().getId(),
                    hora.getSec().getId(), hora.getTurdet().getId(),
                    hora.getHorTipoClase(), hora.getDoc().getId(), "0");

        }
        this.setOncomplete("javascript:Richfaces.showModalPanel('mpEliminarHorario')");
    }

    public void seleccionarJuevesEliminar(ActionEvent event) {
        listaHorarioEliminar = new ArrayList<AcHorario>();
        UIParameter id_ident = (UIParameter) event.getComponent().findComponent("param_identificador_jueves");
        this.setHordia_idEliminar(aInteger(id_ident.getValue().toString()));
        if (aInteger(id_ident.getValue().toString()) > 0) {
            HSHorarioDAO horario = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoHorario");
            AcHorario hora = horario.seleccionarUnHorario(aInteger(id_ident.getValue().toString()));
            agregarHorarioEliminar(hora.getId(), hora.getHorDia(), hora.getAul().getId(),
                    hora.getSec().getId(), hora.getTurdet().getId(),
                    hora.getHorTipoClase(), hora.getDoc().getId(), "0");

        }
        this.setOncomplete("javascript:Richfaces.showModalPanel('mpEliminarHorario')");
    }

    public void seleccionarViernesEliminar(ActionEvent event) {
        listaHorarioEliminar = new ArrayList<AcHorario>();
        UIParameter id_ident = (UIParameter) event.getComponent().findComponent("param_identificador_viernes");
        this.setHordia_idEliminar(aInteger(id_ident.getValue().toString()));
        if (aInteger(id_ident.getValue().toString()) > 0) {
            HSHorarioDAO horario = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoHorario");
            AcHorario hora = horario.seleccionarUnHorario(aInteger(id_ident.getValue().toString()));
            agregarHorarioEliminar(hora.getId(), hora.getHorDia(), hora.getAul().getId(),
                    hora.getSec().getId(), hora.getTurdet().getId(),
                    hora.getHorTipoClase(), hora.getDoc().getId(), "0");

        }
        this.setOncomplete("javascript:Richfaces.showModalPanel('mpEliminarHorario')");
    }

    public int agregarHorarioEliminar(int identificador, String dia, int aula, int seccion, int turno, String tipo, int docente, String activo) {
        if (aula == 0) {
            this.setOncomplete("javascript:alert('Ingrese Aula.')");
            return 0;
        } else if (docente == 0) {
            this.setOncomplete("javascript:alert('Ingrese Docente.')");
            return 0;
        } else {
            AcHorario horario = new AcHorario();
            AcDocente doc = new AcDocente();
            doc.setId(docente);
            AcAula aul = new AcAula();
            aul.setId(aula);
            AcSeccion sec = new AcSeccion();
            sec.setId(seccion);
            AcTurnoDetalle turdet = new AcTurnoDetalle();
            turdet.setId(turno);
            horario.setId(identificador);
            horario.setAul(aul);
            horario.setHorActivo(activo);
            horario.setHorDia(dia);
            horario.setSec(sec);
            horario.setTurdet(turdet);
            horario.setHorCreacion(new Date());
            horario.setHorTipoClase(tipo);
            horario.setDoc(doc);
            listaHorarioEliminar.add(horario);

            ide--;
            this.setOncomplete("");
            return 1;
        }
    }

    public int agregarHorarioEditar(int identificador, String dia, int aula, int seccion, int turno, String tipo, int docente, String activo) {
        if (aula == 0) {
            this.setOncomplete("javascript:alert('Ingrese Aula.')");
            return 0;
        } else if (docente == 0) {
            this.setOncomplete("javascript:alert('Ingrese Docente.')");
            return 0;
        } else {
            AcHorario horario = new AcHorario();
            AcDocente doc = new AcDocente();
            doc.setId(docente);
            AcAula aul = new AcAula();
            aul.setId(aula);
            AcSeccion sec = new AcSeccion();
            sec.setId(seccion);
            AcTurnoDetalle turdet = new AcTurnoDetalle();
            turdet.setId(turno);
            horario.setId(identificador);
            horario.setAul(aul);
            horario.setHorActivo(activo);
            horario.setHorDia(dia);
            horario.setSec(sec);
            horario.setTurdet(turdet);
            horario.setHorCreacion(new Date());
            horario.setHorTipoClase(tipo);
            horario.setDoc(doc);
            listaHorarioEditar.add(horario);
            this.setHordia_id(identificador);

            System.out.println("identificador: " + identificador + "\tide: " + ide + "\tsize: " + listaHorarioEditar.size());
            ide--;
            return 1;
        }
    }

    public void InsertarTipoClase(ActionEvent event) {
        if (!this.getTipo_idEditar().equals("000000")) {
            int contarAula = 0;
            HSHorarioDAO dao = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoHorario");
            int iEdicion = encontrarIndiceEditar(this.getHordia_id());
            listaHorarioEditar.get(iEdicion).setHorTipoClase(this.getTipo_idEditar());
            if (("018003").equals(this.getTipo_idEditar())) {
                if (this.getLab_id() > 0) {
                    AcAula acAula = new AcAula(this.getLab_id());
                    listaHorarioEditar.get(iEdicion).setAul(acAula);
                }
                dao.insertarHorarios(prepararInsercion(listaHorarioEditar));
                this.setOncomplete("javascript:alert('SE GUARDO CORRECTAMENTE');Richfaces.hideModalPanel('mpTipoClase');");
            } else {
                if (this.aula_id > 0) {
                    AcAula acAula = new AcAula(this.aula_id);
                    listaHorarioEditar.get(iEdicion).setAul(acAula);
                    dao.insertarHorarios(prepararInsercion(listaHorarioEditar));
                    this.setOncomplete("javascript:alert('SE GUARDO CORRECTAMENTE');Richfaces.hideModalPanel('mpTipoClase');");
                } else {
                    this.setOncomplete("javascript:alert('DEBE SELECCIONAR UN AULA EN GENERAL');Richfaces.hideModalPanel('mpTipoClase');");
                }
            }

        } else {
            this.setOncomplete("javascript:alert('DEBE SELECCIONAR UN TIPO DE CLASE');");
        }
    }

    public void eliminarHorario(ActionEvent event) {
        HSHorarioDAO dao = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoHorario");
        dao.insertarHorarios(prepararInsercion(listaHorarioEliminar));
        try {
            setearTurno();
        } catch (Exception ex) {
            Logger.getLogger(bHorario.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setOncomplete("javascript:alert('SE ELIMINO EL HORARIO CORRECTAMENTE');"
                + "Richfaces.hideModalPanel('mpEliminarHorario')");
    }

    public void seleccionarViernes(ActionEvent event) {
        UIOutput viernes = (UIOutput) event.getComponent().findComponent("viernes");

        UIParameter id_turno = (UIParameter) event.getComponent().findComponent("param_id_turno");
        UIParameter ho_aula = (UIParameter) event.getComponent().findComponent("ho_aula");
        UIParameter ho_tipo = (UIParameter) event.getComponent().findComponent("ho_tipo");
        UIParameter ho_docente = (UIParameter) event.getComponent().findComponent("ho_docente");

        int aula_id_aux = aInteger(ho_aula.getValue().toString());
        String tipo_id_aux = ho_tipo.getValue().toString();
        int docente_id_aux = aInteger(ho_docente.getValue().toString());

        if (viernes.getValue().toString().equals("") || viernes.getValue().toString().equals("DISPONIBLE")) {
            System.out.println("identificador: " + ide);
            System.out.println("aula: " + aula_id_aux);
            System.out.println("tipo: " + tipo_id_aux);
            System.out.println("doc: " + docente_id_aux);

            int agregar = agregarHorario(ide, "016005", aula_id_aux, this.getId_sec_horario(),
                    aInteger(id_turno.getValue().toString()), tipo_id_aux, docente_id_aux, "1");
            if (agregar == 1) {
                this.setIndice_id_vie(ide + 1);
//                this.setViernes_hor("ide" + (ide + 1) + this.getN_cur_aperturado()
//                        + "<br> t:" + id_turno.getValue().toString()
//                        + "<br> a:" + aula_id_aux + "<br> s:" + this.getId_sec_horario()
//                        + "<br> tipo:" + tipo_id_aux + "<br> doc:" + docente_id_aux);
                this.setViernes_hor(VARIABLE_HORARIO_NUEVO_ASIGNADO);
            }
        } else {
            this.setViernes_hor("");
            quitarHorario(event, 5);
        }
    }

    public void seleccionarSabado(ActionEvent event) {
        UIOutput sabado = (UIOutput) event.getComponent().findComponent("sabado");

        UIParameter id_turno = (UIParameter) event.getComponent().findComponent("param_id_turno");
        UIParameter ho_aula = (UIParameter) event.getComponent().findComponent("ho_aula");
        UIParameter ho_tipo = (UIParameter) event.getComponent().findComponent("ho_tipo");
        UIParameter ho_docente = (UIParameter) event.getComponent().findComponent("ho_docente");

        int aula_id_aux = aInteger(ho_aula.getValue().toString());
        String tipo_id_aux = ho_tipo.getValue().toString();
        int docente_id_aux = aInteger(ho_docente.getValue().toString());

        if (sabado.getValue().toString().equals("") || sabado.getValue().toString().equals("DISPONIBLE")) {
            System.out.println("identificador: " + ide);
            System.out.println("aula: " + aula_id_aux);
            System.out.println("tipo: " + tipo_id_aux);
            System.out.println("doc: " + docente_id_aux);

            int agregar = agregarHorario(ide, "016006", aula_id_aux, this.getId_sec_horario(),
                    aInteger(id_turno.getValue().toString()), tipo_id_aux, docente_id_aux, "1");
            if (agregar == 1) {
                this.setIndice_id_sab(ide + 1);
//                this.setSabado_hor("ide" + (ide + 1) + this.getN_cur_aperturado()
//                        + "<br> t:" + id_turno.getValue().toString()
//                        + "<br> a:" + aula_id_aux + "<br> s:" + this.getId_sec_horario()
//                        + "<br> tipo:" + tipo_id_aux + "doc:" + docente_id_aux);
                this.setSabado_hor(VARIABLE_HORARIO_NUEVO_ASIGNADO);
            }
        } else {
            this.setSabado_hor("");
            quitarHorario(event, 6);
        }
    }

    public void seleccionarDomingo(ActionEvent event) {
        UIOutput domingo = (UIOutput) event.getComponent().findComponent("domingo");

        UIParameter id_turno = (UIParameter) event.getComponent().findComponent("param_id_turno");
        UIParameter ho_aula = (UIParameter) event.getComponent().findComponent("ho_aula");
        UIParameter ho_tipo = (UIParameter) event.getComponent().findComponent("ho_tipo");
        UIParameter ho_docente = (UIParameter) event.getComponent().findComponent("ho_docente");

        int aula_id_aux = aInteger(ho_aula.getValue().toString());
        String tipo_id_aux = ho_tipo.getValue().toString();
        int docente_id_aux = aInteger(ho_docente.getValue().toString());

        if (domingo.getValue().toString().equals("") || domingo.getValue().toString().equals("DISPONIBLE")) {
            System.out.println("identificador: " + ide);
            System.out.println("aula: " + aula_id_aux);
            System.out.println("tipo: " + tipo_id_aux);
            System.out.println("doc: " + docente_id_aux);

            int agregar = agregarHorario(ide, "016007", aula_id_aux, this.getId_sec_horario(),
                    aInteger(id_turno.getValue().toString()), tipo_id_aux, docente_id_aux, "1");
            if (agregar == 1) {
                this.setIndice_id_dom(ide + 1);
//                this.setDomingo_hor("ide" + (ide + 1) + this.getN_cur_aperturado()
//                        + "<br> t:" + id_turno.getValue().toString()
//                        + "<br> a:" + aula_id_aux + "<br> s:" + this.getId_sec_horario()
//                        + "<br> tipo:" + tipo_id_aux + "doc:" + docente_id_aux);
                this.setDomingo_hor(VARIABLE_HORARIO_NUEVO_ASIGNADO);
            }
        } else {
            this.setDomingo_hor("");
            quitarHorario(event, 7);
        }
    }

    public int agregarHorario(int identificador, String dia, int aula, int seccion, int turno, String tipo, int docente, String activo) {
        if (aula == 0) {
            this.setOncomplete("javascript:alert('Ingrese Aula.')");
            return 0;
        } else if (docente == 0) {
            this.setOncomplete("javascript:alert('Ingrese Docente.')");
            return 0;
        } else {
            AcHorario horario = new AcHorario();
            AcDocente doc = new AcDocente();
            doc.setId(docente);
            AcAula aul = new AcAula();
            aul.setId(aula);
            AcSeccion sec = new AcSeccion();
            sec.setId(seccion);
            AcTurnoDetalle turdet = new AcTurnoDetalle();
            turdet.setId(turno);
            horario.setId(identificador);
            horario.setAul(aul);
            horario.setHorActivo(activo);
            horario.setHorDia(dia);
            horario.setSec(sec);
            horario.setTurdet(turdet);
            horario.setHorCreacion(new Date());
            horario.setHorTipoClase(tipo);
            horario.setDoc(doc);
            listaHorario.add(horario);

            System.out.println("identificador: " + identificador + "\tide: " + ide + "\tsize: " + listaHorario.size());
            ide--;
            this.setOncomplete("");
            return 1;
        }
    }

    public void quitarHorario(ActionEvent event, int dia) {
        UIParameter id_ident;
        switch (dia) {
            case 1:
                id_ident = (UIParameter) event.getComponent().findComponent("param_identificador_lunes");
                break;
            case 2:
                id_ident = (UIParameter) event.getComponent().findComponent("param_identificador_martes");
                break;
            case 3:
                id_ident = (UIParameter) event.getComponent().findComponent("param_identificador_miercoles");
                break;
            case 4:
                id_ident = (UIParameter) event.getComponent().findComponent("param_identificador_jueves");
                break;
            case 5:
                id_ident = (UIParameter) event.getComponent().findComponent("param_identificador_viernes");
                break;
            case 6:
                id_ident = (UIParameter) event.getComponent().findComponent("param_identificador_sabado");
                break;
            default:
                id_ident = (UIParameter) event.getComponent().findComponent("param_identificador_domingo");
        }

        if (aInteger(id_ident.getValue().toString()) > 0) {
            HSHorarioDAO horario = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoHorario");
            AcHorario hora = horario.seleccionarUnHorario(aInteger(id_ident.getValue().toString()));
            agregarHorario(hora.getId(), hora.getHorDia(), hora.getAul().getId(),
                    hora.getSec().getId(), hora.getTurdet().getId(),
                    hora.getHorTipoClase(), hora.getDoc().getId(), "0");
        }
        if (aInteger(id_ident.getValue().toString()) <= 0) {
            listaHorario.remove(encontrarIndice(aInteger(id_ident.getValue().toString())));
        }
    }

    private int encontrarIndice(int identificador) {
        for (int i = 0; i < listaHorario.size(); i++) {
            if (listaHorario.get(i).getId() == identificador) {
                return i;
            }
        }
        return -1;
    }

    private int encontrarIndiceEditar(int identificador) {
        for (int i = 0; i < listaHorarioEditar.size(); i++) {
            if (listaHorarioEditar.get(i).getId() == identificador) {
                return i;
            }
        }
        return -1;
    }

    public void Insertar(ActionEvent event) {
        HSHorarioDAO dao = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoHorario");

//        for (int i = 0; i < listaHorario.size(); i++) {
//            System.out.println("\tid horario: " + listaHorario.get(i).getId());
//            System.out.println("\tsec nombre: " + listaHorario.get(i).getSec().getSecNombre());
//            System.out.println("\tturno detalle hora: " + listaHorario.get(i).getTurdet().getTurdetHora());
//            System.out.println("\tcreacion: " + listaHorario.get(i).getHorCreacion());
//            System.out.println("\thora dia: " + listaHorario.get(i).getHorDia());
//            System.out.println("\ttipo clase: " + listaHorario.get(i).getHorTipoClase());
//        }
        if (seccion_id_aux != 0) {
            String horas = "";
            List<BeanReporte> rptHorasDisponible = dao.numeroHorasDisponiblesSeccion(seccion_id_aux);
            for (BeanReporte beanReporte : rptHorasDisponible) {
                horas = beanReporte.getExpr1();
            }
            if (listaHorario.size() <= Integer.parseInt(horas)) {
                dao.insertarHorarios(prepararInsercion(listaHorario));
                try {
                    setearTurno();
                } catch (Exception ex) {
                    Logger.getLogger(bHorario.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.setOncomplete("javascript:alert('SE GUARDO CORRECTAMENTE')");
            } else {
                this.setOncomplete("javascript:alert('SOLO DEBE ASIGNAR " + horas + " PERIODOS')");
            }
        }

    }

    private List<AcHorario> prepararInsercion(List<AcHorario> lista_horarios) {
        List<AcHorario> lHorarios = new ArrayList<AcHorario>();
        AcHorario horario;
        for (int i = 0; i < lista_horarios.size(); i++) {
            horario = new AcHorario();
            if (lista_horarios.get(i).getId() > 0) {
                horario.setId(lista_horarios.get(i).getId());
            }
            horario.setAul(lista_horarios.get(i).getAul());
            horario.setDoc(lista_horarios.get(i).getDoc());
            horario.setHorActivo(lista_horarios.get(i).getHorActivo());
            horario.setHorTipoClase(lista_horarios.get(i).getHorTipoClase());
            horario.setHorDia(lista_horarios.get(i).getHorDia());
            horario.setTurdet(lista_horarios.get(i).getTurdet());
            horario.setSec(lista_horarios.get(i).getSec());
            horario.setHorCreacion(lista_horarios.get(i).getHorCreacion());
            lHorarios.add(horario);
        }
        return lHorarios;
    }

    public void setearTurno() throws Exception {
        mostrarTablaHorarios();
    }

    public void setearVerGenHor() throws Exception {
        mostrarTablaHorarios2();
    }

    public String getVacantesOcupadas() {
        return vacantesOcupadas;
    }

    public void setVacantesOcupadas(String vacantesOcupadas) {
        this.vacantesOcupadas = vacantesOcupadas;
    }

    private int aInteger(String cadena) {
        try {
            return Integer.parseInt(cadena.trim());
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    public int getB_facultad() {
        return b_facultad;
    }

    public void setB_facultad(int b_facultad) {
        this.b_facultad = b_facultad;
    }

    public int getB_especialidad() {
        return b_especialidad;
    }

    public void setB_especialidad(int b_especialidad) {
        this.b_especialidad = b_especialidad;
    }

    public String getB_ciclos() {
        return b_ciclos;
    }

    public void setB_ciclos(String b_ciclos) {
        this.b_ciclos = b_ciclos;
    }

    public int getSemestre_f() {
        return semestre_f;
    }

    public void setSemestre_f(int semestre_f) {
        this.semestre_f = semestre_f;
    }

    public int getW_secEspeid() {
        return w_secEspeid;
    }

    public void setW_secEspeid(int w_secEspeid) {
        this.w_secEspeid = w_secEspeid;
    }

    public SelectItem[] getCboSeccionEspe() {
        HSSeccionEspecialidadDAO dao = (HSSeccionEspecialidadDAO) ServiceFinder.findBean("SpringHibernateDaoSeccionEspecialidad");
        List<AcSeccionEspecialidad> lista = dao.getListarSeccionPorEspecialidadCiclo(this.b_especialidad, this.b_ciclos);
        if (lista.size() > 0) {
            cboSeccionEspe = new SelectItem[lista.size()];
            for (int i = 0; i < lista.size(); i++) {
                cboSeccionEspe[i] = new SelectItem(lista.get(i).getSecespId(), lista.get(i).getSecespCodigo());
            }
        } else {
            cboSeccionEspe = new SelectItem[1];
            cboSeccionEspe[0] = new SelectItem("-1", "[-Seleccione-]");
        }
        return cboSeccionEspe;
    }

    public void setCboSeccionEspe(SelectItem[] cboSeccionEspe) {
        this.cboSeccionEspe = cboSeccionEspe;
    }

    public SelectItem[] getComboFacultades() throws Exception {
        HSFacultadDAO dao = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
        List lista = dao.seleccionarFacultad("", "");
        comboFacultades = new SelectItem[lista.size() + 1];
        comboFacultades[0] = new SelectItem(0, "TODOS");
        for (int i = 0; i < comboFacultades.length - 1; i++) {
            comboFacultades[i + 1] = new SelectItem(((AcFacultad) lista.get(i)).getId(), ((AcFacultad) lista.get(i)).getFacNombre());
        }
        return comboFacultades;
    }

    public void setComboFacultades(SelectItem[] comboFacultades_s) {
        this.comboFacultades = comboFacultades_s;
    }

    public SelectItem[] getComboEspecialidades() throws Exception {
        HSEspecialidadDAO dao = (HSEspecialidadDAO) ServiceFinder.findBean("SpringHibernateDaoEspecialidad");
        System.out.println("facu::::" + this.getB_facultad());
        List lista = dao.seleccionarEspecialidad(this.getB_facultad());
        if (lista.size() != 0) {
            comboEspecialidades = new SelectItem[lista.size() + 1];
            comboEspecialidades[0] = new SelectItem(0, "Seleccione");
            for (int i = 0; i < comboEspecialidades.length - 1; i++) {
                comboEspecialidades[i + 1] = new SelectItem(((AcEspecialidad) lista.get(i)).getId(), ((AcEspecialidad) lista.get(i)).getEspNombre());
            }
        } else {
            comboEspecialidades = new SelectItem[1];
            comboEspecialidades[0] = new SelectItem(0, "[Seleccione]");
        }
        return comboEspecialidades;
    }

    public void setComboEspecialidades(SelectItem[] comboEspecialidades) {
        this.comboEspecialidades = comboEspecialidades;
    }

    public SelectItem[] getComboCiclos() throws Exception {
        // HSEspecialidadDAO dao = (HSEspecialidadDAO)ServiceFinder.findBean( "SpringHibernateDaoEspecialidad" );
        HSPlanCurricularDAO dao = (HSPlanCurricularDAO) ServiceFinder.findBean("SpringHibernateDaoPlanCurricular");
        List list_plan = dao.seleccionarPlanCurricular(this.getB_especialidad());
        HSCatalogoDAO daoCatalogo = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        List<TbCatalogo> catalogoCiclos;

        int contar = 0;
        if (!list_plan.isEmpty() && this.getSemestre_f() > 0 && this.getTurnoGeneracion_id() > 0) {
            for (int k = 0; k < list_plan.size(); k++) {
                //System.out.println("***k:" + k);
                catalogoCiclos = daoCatalogo.seleccionarCiclosXPlanCurricularGenerar(((AcPlancurricular) list_plan.get(k)).getId(), this.getSemestre_f(), this.getTurnoGeneracion_id());
                if (catalogoCiclos.size() != 0) {
                    comboCiclos = new SelectItem[catalogoCiclos.size() + 1];
                    comboCiclos[0] = new SelectItem(0, "Seleccione");
                    for (int m = 0; m < comboCiclos.length - 1; m++) {

                        comboCiclos[m + 1] = new SelectItem((catalogoCiclos.get(m).getCatCodigoGrupo()
                                + catalogoCiclos.get(m).getCatCodigoElemento()),
                                catalogoCiclos.get(m).getCatDescripcionElemento());
                    }
                } else {
                    comboCiclos = new SelectItem[1];
                    comboCiclos[0] = new SelectItem(0, "[Seleccione]");
                }
            }
//                    System.out.println("termino for2");
        } else {
            comboCiclos = new SelectItem[1];
            comboCiclos[0] = new SelectItem(0, "[Seleccione]");
        }
        return comboCiclos;
    }

    public SelectItem[] getComboSemestres() throws Exception {
        HSSemestreDAO dao = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
        List lista = dao.seleccionarSemestreVigente();
        int num = lista.size();
        comboSemestres = new SelectItem[1];
        comboSemestres[0] = new SelectItem(0, "[Seleccione]");
        if (lista.size() != 0) {
            comboSemestres = new SelectItem[lista.size() + 1];
            comboSemestres[0] = new SelectItem(0, "[Seleccione]");
            for (int i = 0; i < comboSemestres.length - 1; i++) {
                comboSemestres[i + 1] = new SelectItem(((AcSemestre) lista.get(i)).getId(), ((AcSemestre) lista.get(i)).getSemNombre());
            }
        }
        return comboSemestres;
    }

    public void setComboSemestres(SelectItem[] comboSemestres) {
        this.comboSemestres = comboSemestres;
    }

    public void setComboCiclos(SelectItem[] comboCiclos) {
        this.comboCiclos = comboCiclos;
    }

    public void mostrarHorario(ActionEvent event) throws Exception {
        this.personalizado = false;
        seccion_id_aux = this.getW_secEspeid();
        // cambiarEfecto();

        HSDocenteDAO daoDocente = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");
        lstDocenteGeneracion = new ArrayList<AcDocente>();
        for (Integer codCurso : lstIntegers) {
            lstDocenteGeneracion.add(daoDocente.buscarDocente(codCurso));
        }
        buscarDocente();
        setearVerGenHor();

        //oncomplete = "Richfaces.showModalPanel('mpClSisEvaPerPlantilla')";
    }

    public void trasladarHorario() throws Exception {
        int generacion = this.getB_generacion();
        if (generacion != 0) {
             descargarArchivo();
        }  
    }
    
     public void trasladarHorarioProceso(ActionEvent event) throws Exception {
        int generacion = this.getB_generacion();
        if (generacion != 0) {
            
            HSHorarioGenDAO dao = (HSHorarioGenDAO) ServiceFinder.findBean("HibernateSpringDaoTargetHorarioGen");
            List<AcHorarioGen> lstHorariosGenerado = dao.seleccionarHorarioGenPorNumeroGeneracion(generacion);
            List<AcHorario> lstHorarioOficial = new ArrayList<AcHorario>();
            for (AcHorarioGen acHorario : lstHorariosGenerado) {
                AcHorario aHorario = new AcHorario();
                aHorario.setAul(acHorario.getAul());
                aHorario.setDoc(acHorario.getDoc());
                aHorario.setHorActivo("1");
                aHorario.setHorCreacion(new Date());
                aHorario.setHorDia(acHorario.getHorDia());
                aHorario.setHorTipoClase(acHorario.getHorTipoClase());
                aHorario.setSec(acHorario.getSec());
                aHorario.setTurdet(acHorario.getTurdet());
                lstHorarioOficial.add(aHorario);
            }
            if (!lstHorarioOficial.isEmpty()) {
                HSHorarioDAO daoHorarioOficial = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoHorario");
                daoHorarioOficial.insertarHorarios(lstHorarioOficial);
                this.setOncomplete("javascript:alert('SE INGRESO CORRECTAMENTE');Richfaces.hideModalPanel('mpTrasladarHorario')");
                limpiarGeneracion();
//                return "javascript:alert('SE INGRESO CORRECTAMENTE');";

            }
        } else {
            this.setOncomplete("javascript:alert('NO HA SELECCIONADO UNA GENERACION ');Richfaces.hideModalPanel('mpTrasladarHorario')");
//            return "javascript:alert('NO HA SELECCIONADO UNA GENERACION ');";
        }
//        return "";
        //oncomplete = "Richfaces.showModalPanel('mpClSisEvaPerPlantilla')";
    }

    public void generarHorario(ActionEvent event) throws Exception {
        this.setOncomplete("");
        int facultadParametro = this.getB_facultad(); //facultad combo
        int semestreParametro = this.getSemestre_f(); //semestre combo
        int especialidadParametro = this.getB_especialidad(); // especialidad combo
        int turnoParametro = this.getTurnoGeneracion_id(); // turno combo
        String cicloParametro = this.getB_ciclos(); // ciclo combo
        String turnoDescParametro = obtenerTurno(turnoParametro);
        HSHorarioDAO dao = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoHorario");
        List<BeanReporte> lstNumeroAperturado = dao.cantidadCursosAperturados(cicloParametro, semestreParametro, especialidadParametro, turnoDescParametro);
        for (BeanReporte beanReporte : lstNumeroAperturado) {
            this.setN_Aperturados(Integer.parseInt(beanReporte.getExpr1()));
            this.setN_NoAperturados(Integer.parseInt(beanReporte.getExpr2()));
        }

        if (this.getN_NoAperturados() > 0) {
            this.setEstadoAperturado(true);
        } else {
            this.setEstadoAperturado(false);
            List<BeanReporte> lstNumeroAsignado = dao.cantidadCursosAsignados(cicloParametro, semestreParametro, especialidadParametro, turnoDescParametro);
            for (BeanReporte beanReporte : lstNumeroAsignado) {
                this.setN_Asignado(Integer.parseInt(beanReporte.getExpr1()));
                this.setN_NoAsignado(Integer.parseInt(beanReporte.getExpr2()));
            }
            if (this.getN_NoAsignado() > 0) {
                this.setEstadoAsignado(true);
            } else {
                this.setEstadoAsignado(false);
                List<BeanReporte> lstNumeroCurSinDisponibilidad = dao.cantidadCursosSinDisponibilidad(cicloParametro, semestreParametro, especialidadParametro, turnoParametro, turnoDescParametro);
                for (BeanReporte beanReporte2 : lstNumeroCurSinDisponibilidad) {
                    this.setN_CurDisponibilidad(Integer.parseInt(beanReporte2.getExpr1()));
                }
                if (this.getN_CurDisponibilidad() > 0) {
                    this.setEstadoCurDisponible(true);
                } else {
                    this.setEstadoCurDisponible(false);

                    boolean estado = dao.estadoCursoEntreDocentesDisponibles(cicloParametro, semestreParametro, especialidadParametro, turnoParametro, turnoDescParametro);
                    if (estado) {
                        List<BeanReporte> lstNumeroDocDisponibiliad = dao.cantidadDocentesDisponibilidad(cicloParametro, semestreParametro, especialidadParametro, turnoParametro, turnoDescParametro);
                        for (BeanReporte beanReporte : lstNumeroDocDisponibiliad) {
                            this.setN_DocDisponible(Integer.parseInt(beanReporte.getExpr1()));
                            this.setN_NoDocDisponible(Integer.parseInt(beanReporte.getExpr2()));
                        }
                    }
                    if (this.getN_NoDocDisponible() > 0) {
                        this.setEstadoDocDisponible(true);
                    } else {
                        this.setEstadoDocDisponible(false);
                        generarHorarioGenetico();
                    }
                }
            }

        }
    }

    public String obtenerTurno(int idTurno) {
        String variable = "";
        HSTurnoDAO daoTurno = (HSTurnoDAO) ServiceFinder.findBean("HibernateSpringDaoTurno");
        AcTurno tmpTurno = daoTurno.obtenerTurno(idTurno);
        if (tmpTurno != null) {
            if (tmpTurno.getTurNombre().equals(VARIABLE_TURNO_MANIANA)) {
                variable = "M";
            } else if (tmpTurno.getTurNombre().equals(VARIABLE_TURNO_NOCHE)) {
                variable = "N";
            } else {
                variable = "No";
            }
        } else {
            variable = "NULO";
        }
        return variable;
    }

    public String startProcess() {
        setEnabled(true);
        setButtonRendered(false);
        setStartTime(new Date().getTime());
        generarHorarioGenetico();
        return null;
    }

    public Long getCurrentValue() {
        if (isEnabled()) {
            Long current = (new Date().getTime() - startTime) / 1000;
            if (current > 100) {
                setButtonRendered(true);
            } else if (current.equals(0)) {
                return new Long(1);
            }
            return (new Date().getTime() - startTime) / 1000;
        }
        if (startTime == null) {
            return Long.valueOf(-1);
        } else {
            return Long.valueOf(101);
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public boolean isButtonRendered() {
        return buttonRendered;
    }

    public void setButtonRendered(boolean buttonRendered) {
        this.buttonRendered = buttonRendered;
    }

    public List<AcDocente> getLstDocenteGeneracion() {
        return lstDocenteGeneracion;
    }

    public void setLstDocenteGeneracion(List<AcDocente> lstDocenteGeneracion) {
        this.lstDocenteGeneracion = lstDocenteGeneracion;
    }

    public List<BeanDocente> getLstDocDisp() {
        return lstDocDisp;
    }

    public void setLstDocDisp(List<BeanDocente> lstDocDisp) {
        this.lstDocDisp = lstDocDisp;
    }

    public void descargarArchivo() throws Exception {
        Reporte reporteHorarioGenerado = new Reporte();
        HashMap parametros = new HashMap();
        parametros.put("num_gen", 1);
        reporteHorarioGenerado.cargarReporteHorarioGenerado(parametros, "rep_horario_general_generado.jasper");

    }

}
