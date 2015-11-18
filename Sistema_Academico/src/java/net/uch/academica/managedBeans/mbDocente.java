/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.academica.managedBeans;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.component.UIOutput;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.academica.hibernateSpringDao.HSCursoDAO;
import net.uch.academica.hibernateSpringDao.HSDocenteCursoDAO;
import net.uch.academica.hibernateSpringDao.HSDocenteDAO;
import net.uch.academica.hibernateSpringDao.HSHorarioDAO;
import net.uch.academica.hibernateSpringDao.HSPlanCurricularDAO;
import net.uch.academica.hibernateSpringDao.HSTurnoDAO;
import net.uch.academica.hibernateSpringDao.HSTurnoDetalleDAO;
import static net.uch.academica.managedBeans.bHorario.ide;
import static net.uch.academica.managedBeans.bHorario.listaHorario;
import net.uch.academica.managedBeans.beans.BeanDocente;
import net.uch.academica.managedBeans.beans.BeanHorarioDispDoc;
import net.uch.academica.managedBeans.beans.CursoDocenteBean;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.managedBeans.beans.BeanClHoraria;
import net.uch.mapping.AcAula;
import net.uch.mapping.AcCurso;
import net.uch.mapping.AcDocente;
import net.uch.mapping.AcDocenteCurso;
import net.uch.mapping.AcEspecialidad;
import net.uch.mapping.AcFacultad;
import net.uch.mapping.AcHorario;
import net.uch.mapping.AcHorarioDispDocente;
import net.uch.mapping.AcParametro;
import net.uch.mapping.AcPlancurricular;
import net.uch.mapping.AcSeccion;
import net.uch.mapping.AcSemestre;
import net.uch.mapping.AcTurno;
import net.uch.mapping.AcTurnoDetalle;
import net.uch.mapping.ClHoraria;
import net.uch.mapping.ClSisEvaDetalle;
import net.uch.mapping.TbCatalogo;
import net.uch.mapping.TbDistrito;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSEspecialidadDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSFacultadDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSParametroDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSSemestreDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSUbigeoDAO;
import net.uch.util.CommonDAO;
import net.uch.util.ConstantesWeb;
import net.uch.util.Reporte;

/**
 *
 * @author richard
 */
public class mbDocente {

    /**
     * Creates a new instance of mbDocente
     */
    private List<BeanDocente> listaDocente;
    private List<CursoDocenteBean> lstCursoDocente = new ArrayList<CursoDocenteBean>();
    private SelectItem[] cboDepartamentos_n;
    private SelectItem[] cboProvincia_n;
    private SelectItem[] cboDistrito_n;
    private SelectItem[] cboDepartamentos_r;
    private SelectItem[] cboProvincia_r;
    private SelectItem[] cboDistrito_r;
    private SelectItem[] cboSituacion;
    private SelectItem[] cboTipoDocente;
    private SelectItem[] b_cboFacultades;
    private SelectItem[] rdoSexo;
    private SelectItem[] comboTurnos2;
    private String doc_dni_w;
    private String doc_nombre_completo_w;
    private BeanDocente docente;
    private String w_depa_naci = "0000";
    private String w_prov_naci = "0000";
    private String w_depa_resi = "0000";
    private String w_prov_resi = "0000";
    private String oncomplete;
    private String mensaje = "";
    private SelectItem[] comboFacultades;
    private SelectItem[] comboEspecialidades;
    private SelectItem[] comboCiclos;
    private SelectItem[] comboCursos;

    private SelectItem[] comboFacultades2;
    private SelectItem[] comboEspecialidades2;
    private SelectItem[] comboCiclos2;
    private SelectItem[] comboCursos2;
    private SelectItem[] cmbAsignacion;
    private SelectItem[] cboDisponilidadA;
    private SelectItem[] cboCursosA;
    private int b_disponibilidadA;
    private int b_cursosA;

    private int b_facultad;
    private int b_especialidad;
    private String b_ciclos;
    private int b_cursos;

    private int b_facultad2;
    private int b_especialidad2;
    private String b_ciclos2 = "000000";
    private int b_cursos2;

    private String b_asignacion;
    private int b_facultad_u;
    private int b_especialidad_u;
    public int b_id_u;
    private String b_especialidad_descripcion;
    private String b_facultad_descripcion;
    private String b_ciclos_descripcion;
    private String b_cursos_descripcion;
    //Detalle de horaria
    private BeanHorarioDispDoc horaria;
    private List<BeanHorarioDispDoc> nListaHoraria;
    private List<BeanHorarioDispDoc> qListaHoraria;
    //Detalle de cursoDocente
    private CursoDocenteBean docenteCurso;
    private List<CursoDocenteBean> nLstDocenteCurso;
    private List<CursoDocenteBean> qLstDocenteCurso;
    // Detalle del docente
    private int doc_id;
    private String doc_detalle;
    public static List<AcHorarioDispDocente> listaHorario = new ArrayList<AcHorarioDispDocente>();
    public int indice_id_lun = 0;
    public int indice_id_mar = 0;
    public int indice_id_mie = 0;
    public int indice_id_jue = 0;
    public int indice_id_vie = 0;
    public int indice_id_sab = 0;
    public int indice_id_dom = 0;
    private String lunes_hor = "";
    private String martes_hor = "";
    private String miercoles_hor = "";
    private String jueves_hor = "";
    private String viernes_hor = "";
    private String sabado_hor = "";
    private String domingo_hor = "";
    private int turno_id;
    private int turno_id2;
    private SelectItem[] comboTurnos;
    private int sem_id;
    private SelectItem[] comboSemestres;
    private List tablaHorario;
    private int id_hor;
    private String inicio_hor;
    private String fin_hor;
    private static int doc_id_aux;
    private int id_doc_horario;
    public int docente_id;
    private String tituloReporte;
    private String valorRep;
    private Double docenteReporte_id;

    private String bColorCelda_Lunes = "";
    private String bColorCelda_Martes = "";
    private String bColorCelda_Miercoles = "";
    private String bColorCelda_Jueves = "";
    private String bColorCelda_Viernes = "";
    private String bColorCelda_Sabado = "";
    private String bColorCelda_Domingo = "";
    private final String parametroMensajeDisponibilidadDocente = "X";
    private final String estadoAsignacion = "097002";
    
    public Double getDocenteReporte_id() {
        return docenteReporte_id;
    }

    public void setDocenteReporte_id(Double docenteReporte_id) {
        this.docenteReporte_id = docenteReporte_id;
    }
    
    
    
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
    //usados para horario
    public BeanHorarioDispDoc getHoraria() {
        return horaria;
    }

    public void setHoraria(BeanHorarioDispDoc horaria) {
        this.horaria = horaria;
    }

    public CursoDocenteBean getDocenteCurso() {
        return docenteCurso;
    }

    public void setDocenteCurso(CursoDocenteBean docenteCurso) {
        this.docenteCurso = docenteCurso;
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

    //fin
    public int getB_id_u() {
        return b_id_u;
    }

    public void setB_id_u(int b_id_u) {
        this.b_id_u = b_id_u;
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

    public int getB_cursos() {
        return b_cursos;
    }

    public void setB_cursos(int b_cursos) {
        this.b_cursos = b_cursos;
    }

    public String getB_asignacion() {
        return b_asignacion;
    }

    public void setB_asignacion(String b_asignacion) {
        this.b_asignacion = b_asignacion;
    }

    public String getB_especialidad_descripcion() {
        return b_especialidad_descripcion;
    }

    public void setB_especialidad_descripcion(String b_especialidad_descripcion) {
        this.b_especialidad_descripcion = b_especialidad_descripcion;
    }

    public String getB_facultad_descripcion() {
        return b_facultad_descripcion;
    }

    public void setB_facultad_descripcion(String b_facultad_descripcion) {
        this.b_facultad_descripcion = b_facultad_descripcion;
    }

    public String getB_ciclos_descripcion() {
        return b_ciclos_descripcion;
    }

    public void setB_ciclos_descripcion(String b_ciclos_descripcion) {
        this.b_ciclos_descripcion = b_ciclos_descripcion;
    }

    public String getB_cursos_descripcion() {
        return b_cursos_descripcion;
    }

    public void setB_cursos_descripcion(String b_cursos_descripcion) {
        this.b_cursos_descripcion = b_cursos_descripcion;
    }

    public int getB_facultad_u() {
        return b_facultad_u;
    }

    public void setB_facultad_u(int b_facultad_u) {
        this.b_facultad_u = b_facultad_u;
    }

    public List<CursoDocenteBean> getLstCursoDocente() {
        if (lstCursoDocente == null) {
            lstCursoDocente = new ArrayList<CursoDocenteBean>();
        }
        return lstCursoDocente;
    }

    public void setLstCursoDocente(List<CursoDocenteBean> lstCursoDocente) {
        this.lstCursoDocente = lstCursoDocente;
    }

//    public void listarEnlaces( int id_docente ) {
//        if ( id_docente != 0 ) {
//            lstCursoDocente = CommonDAO.getAcDocenteDAO().listarCursoDocente( id_docente );
//        }
//    }
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
        if (list_plan.size() != 0) {
            for (int k = 0; k < list_plan.size(); k++) {
                //System.out.println("***k:" + k);
                catalogoCiclos = daoCatalogo.seleccionarCiclosXPlanCurricular(((AcPlancurricular) list_plan.get(k)).getId());
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

    public void setComboCiclos(SelectItem[] comboCiclos) {
        this.comboCiclos = comboCiclos;
    }

    public SelectItem[] getCmbAsignacion() throws Exception {
        if (cmbAsignacion == null) {
            try {
                HSCatalogoDAO dao = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
                List<TbCatalogo> lstCatAsignacion = dao.seleccionarCatalogo("097");
                cmbAsignacion = new SelectItem[lstCatAsignacion.size() + 1];
                cmbAsignacion[0] = new SelectItem("000000", "[Seleccione]");
                for (int i = 0; i < lstCatAsignacion.size(); i++) {
                    cmbAsignacion[i + 1] = new SelectItem(lstCatAsignacion.get(i).getCatCodigoGrupo() + lstCatAsignacion.get(i).getCatCodigoElemento(), lstCatAsignacion.get(i).getCatDescripcionElemento());
                }
            } catch (Exception e) {
                System.err.println("Error al cargar los asignacion " + e.getMessage());
                cmbAsignacion = new SelectItem[1];
                cmbAsignacion[0] = new SelectItem("000000", "[Seleccione]");
            }
        }
        return cmbAsignacion;
    }

    public void setCmbAsignacion(SelectItem[] cmbAsignacion) {
        this.cmbAsignacion = cmbAsignacion;
    }

    public SelectItem[] getCboDisponilidadA() {
        cboDisponilidadA = new SelectItem[3];
        cboDisponilidadA[0] = new SelectItem(0, "[Seleccione]");
        cboDisponilidadA[1] = new SelectItem(1, "Con Disponibilidad");
        cboDisponilidadA[2] = new SelectItem(2, "Sin Disponilidad");
        return cboDisponilidadA;
    }

    public void setCboDisponilidadA(SelectItem[] cboDisponilidadA) {
        this.cboDisponilidadA = cboDisponilidadA;
    }

    public SelectItem[] getCboCursosA() {
        cboCursosA = new SelectItem[3];
        cboCursosA[0] = new SelectItem(0, "[Seleccione]");
        cboCursosA[1] = new SelectItem(1, "Con Cursos");
        cboCursosA[2] = new SelectItem(2, "Sin Cursos");
        return cboCursosA;
    }
    
    public void limpiarCombos(){
        if(b_cursosA==0 || b_cursosA==2){
            this.setB_facultad2(0);
            this.setB_especialidad(0);
            this.setB_ciclos2("000000");
            this.setB_cursos2(0);
        }
    }
    
    public void limpiarCombos2(){
        if(b_disponibilidadA==0 || b_disponibilidadA==2){
            this.setSem_id(0);
            this.setTurno_id2(0);
        }
    }

    public void setCboCursosA(SelectItem[] cboCursosA) {
        this.cboCursosA = cboCursosA;
    }

    public int getB_disponibilidadA() {
        return b_disponibilidadA;
    }

    public void setB_disponibilidadA(int b_disponibilidadA) {
        this.b_disponibilidadA = b_disponibilidadA;
    }

    public int getB_cursosA() {

        return b_cursosA;
    }

    public void setB_cursosA(int b_cursosA) {
        this.b_cursosA = b_cursosA;
    }

    public SelectItem[] getComboCursos() throws Exception {
        // HSEspecialidadDAO dao = (HSEspecialidadDAO)ServiceFinder.findBean( "SpringHibernateDaoEspecialidad" );
        HSCursoDAO daoC = (HSCursoDAO) ServiceFinder.findBean("SpringHibernateDaoCurso");
        HSPlanCurricularDAO dao2 = (HSPlanCurricularDAO) ServiceFinder.findBean("SpringHibernateDaoPlanCurricular");

        List list_plan = dao2.seleccionarPlanCurricular(this.getB_especialidad());

        List<AcCurso> ListadoCursos;

        int contar = 0;
        if (!list_plan.isEmpty()) {
            for (int k = 0; k < list_plan.size(); k++) {
                //System.out.println("***k:" + k);
                ListadoCursos = daoC.listadoCurso((((AcPlancurricular) list_plan.get(k)).getId()), this.getB_ciclos());
                if (!ListadoCursos.isEmpty()) {
                    comboCursos = new SelectItem[ListadoCursos.size() + 1];
                    comboCursos[0] = new SelectItem(0, "Seleccione");
                    for (int m = 0; m < comboCursos.length-1; m++) {

                        comboCursos[m+1] = new SelectItem(ListadoCursos.get(m).getId(),
                                ListadoCursos.get(m).getCurNombre());
                    }
                } else {
                    comboCursos = new SelectItem[1];
                    comboCursos[0] = new SelectItem(0, "[Seleccione]");
                }
            }
//                    System.out.println("termino for2");
        } else {
            comboCursos = new SelectItem[1];
            comboCursos[0] = new SelectItem(0, "[Seleccione]");
        }
        return comboCursos;
    }

    public void setComboCursos(SelectItem[] comboCursos) {
        this.comboCursos = comboCursos;
    }

    public int getB_facultad2() {
        return b_facultad2;
    }

    public void setB_facultad2(int b_facultad2) {
        this.b_facultad2 = b_facultad2;
    }

    public int getB_especialidad2() {
        return b_especialidad2;
    }

    public void setB_especialidad2(int b_especialidad2) {
        this.b_especialidad2 = b_especialidad2;
    }

    public String getB_ciclos2() {
        return b_ciclos2;
    }

    public void setB_ciclos2(String b_ciclos2) {
        this.b_ciclos2 = b_ciclos2;
    }

    public int getB_cursos2() {
        return b_cursos2;
    }

    public void setB_cursos2(int b_cursos2) {
        this.b_cursos2 = b_cursos2;
    }

    public SelectItem[] getComboFacultades2() throws Exception {
        HSFacultadDAO dao = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
        List lista = dao.seleccionarFacultad("", "");
        comboFacultades2 = new SelectItem[lista.size() + 1];
        comboFacultades2[0] = new SelectItem(0, "TODOS");
        for (int i = 0; i < comboFacultades2.length - 1; i++) {
            comboFacultades2[i + 1] = new SelectItem(((AcFacultad) lista.get(i)).getId(), ((AcFacultad) lista.get(i)).getFacNombre());
        }
        return comboFacultades2;
    }

    public void setComboFacultades2(SelectItem[] comboFacultades2) {
        this.comboFacultades2 = comboFacultades2;
    }

    public SelectItem[] getComboEspecialidades2() throws Exception {
        HSEspecialidadDAO dao = (HSEspecialidadDAO) ServiceFinder.findBean("SpringHibernateDaoEspecialidad");
        List lista = dao.seleccionarEspecialidad(this.getB_facultad2());
        if (!lista.isEmpty()) {
            comboEspecialidades2 = new SelectItem[lista.size() + 1];
            comboEspecialidades2[0] = new SelectItem(0, "Seleccione");
            for (int i = 0; i < comboEspecialidades2.length - 1; i++) {
                comboEspecialidades2[i + 1] = new SelectItem(((AcEspecialidad) lista.get(i)).getId(), ((AcEspecialidad) lista.get(i)).getEspNombre());
            }
        } else {
            comboEspecialidades2 = new SelectItem[1];
            comboEspecialidades2[0] = new SelectItem(0, "[Seleccione]");
        }
        return comboEspecialidades2;
    }

    public void setComboEspecialidades2(SelectItem[] comboEspecialidades2) {
        this.comboEspecialidades2 = comboEspecialidades2;
    }

    public SelectItem[] getComboCiclos2() throws Exception {
        // HSEspecialidadDAO dao = (HSEspecialidadDAO)ServiceFinder.findBean( "SpringHibernateDaoEspecialidad" );
        HSPlanCurricularDAO dao = (HSPlanCurricularDAO) ServiceFinder.findBean("SpringHibernateDaoPlanCurricular");
        List list_plan = dao.seleccionarPlanCurricular(this.getB_especialidad2());
        HSCatalogoDAO daoCatalogo = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        List<TbCatalogo> catalogoCiclos;

        if (!list_plan.isEmpty()) {
            for (int k = 0; k < list_plan.size(); k++) {
                //System.out.println("***k:" + k);
                catalogoCiclos = daoCatalogo.seleccionarCiclosXPlanCurricular(((AcPlancurricular) list_plan.get(k)).getId());
                if (!catalogoCiclos.isEmpty()) {
                    comboCiclos2 = new SelectItem[catalogoCiclos.size() + 1];
                    comboCiclos2[0] = new SelectItem("000000", "Seleccione");
                    for (int m = 0; m < comboCiclos2.length - 1; m++) {

                        comboCiclos2[m + 1] = new SelectItem((catalogoCiclos.get(m).getCatCodigoGrupo()
                                + catalogoCiclos.get(m).getCatCodigoElemento()),
                                catalogoCiclos.get(m).getCatDescripcionElemento());
                    }
                } else {
                    comboCiclos2 = new SelectItem[1];
                    comboCiclos2[0] = new SelectItem("000000", "[Seleccione]");
                }
            }
//                    System.out.println("termino for2");
        } else {
            comboCiclos2 = new SelectItem[1];
            comboCiclos2[0] = new SelectItem("000000", "[Seleccione]");
        }
        return comboCiclos2;
    }

    public void setComboCiclos2(SelectItem[] comboCiclos2) {
        this.comboCiclos2 = comboCiclos2;
    }

    public SelectItem[] getComboCursos2() throws Exception {
        // HSEspecialidadDAO dao = (HSEspecialidadDAO)ServiceFinder.findBean( "SpringHibernateDaoEspecialidad" );
        HSCursoDAO daoC = (HSCursoDAO) ServiceFinder.findBean("SpringHibernateDaoCurso");
        HSPlanCurricularDAO dao2 = (HSPlanCurricularDAO) ServiceFinder.findBean("SpringHibernateDaoPlanCurricular");

        List list_plan = dao2.seleccionarPlanCurricular(this.getB_especialidad2());

        List<AcCurso> ListadoCursos;

        if (!list_plan.isEmpty() && this.getB_ciclos2() != null) {
            for (int k = 0; k < list_plan.size(); k++) {
                //System.out.println("***k:" + k);
                ListadoCursos = daoC.listadoCursoAsignado((((AcPlancurricular) list_plan.get(k)).getId()), this.getB_ciclos2());
                if (!ListadoCursos.isEmpty()) {
                    comboCursos2 = new SelectItem[ListadoCursos.size() + 1];
                    comboCursos2[0] = new SelectItem(0, "Seleccione");
                    for (int m = 0; m < comboCursos2.length - 1; m++) {

                        comboCursos2[m + 1] = new SelectItem(ListadoCursos.get(m).getId(),
                                ListadoCursos.get(m).getCurNombre());
                    }
                } else {
                    comboCursos2 = new SelectItem[1];
                    comboCursos2[0] = new SelectItem(0, "[Seleccione]");
                }
            }
//                    System.out.println("termino for2");
        } else {
            comboCursos2 = new SelectItem[1];
            comboCursos2[0] = new SelectItem(0, "[Seleccione]");
        }
        return comboCursos2;
    }

    public void setComboCursos2(SelectItem[] comboCursos2) {
        this.comboCursos2 = comboCursos2;
    }

//    public void enlazarCursoDocente( ActionEvent event ) {
//        CursoDocenteBean epds;
//        AcDocente docente;
//        AcCurso curso;
//        HSDocenteDAO estPagDAO;
//        HSCursoDAO estPagDAO2;
//        int p_id = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_id02" ) ).getValue().toString() );
//        oncomplete = "";
//        try {
//            estPagDAO = CommonDAO.getAcDocenteDAO();
//            estPagDAO2 = CommonDAO.getAcCursoDAO();
//            if ( p_id != 0 ) {
//                if ( estPagDAO.buscarCursoDocente( p_id, b_cursos ) != null ) {
//                    oncomplete = "alert( 'Ya existe una relacion entre este curso y docente' );Richfaces.hideModalPanel('mpConfirmarEnlazar');";
//                    return;
//                }
//
//                docente = estPagDAO.seleccionarDocente( p_id );
//                curso = (AcCurso)estPagDAO2.seleccionarCursoID( b_cursos );
//
//                epds = new CursoDocenteBean();
//                epds.setDocente( docente );
//                epds.setCurso( curso );
////            epds.setFechaCreacion( new java.sql.Date( Calendar.getInstance().getTimeInMillis() ) );
//                epds.setActivo( 1 );
//                if ( estPagDAO.insertarActualizarCurDoc( epds ) == 1 ) {
//                    oncomplete = "alert( 'Ingresado correctamente.' );";
//                    lstCursoDocente.add( epds );
//                } else {
//                    oncomplete = "alert( 'Error al ingresar curso seleccionado.' );";
//                }
//            } else {
//                oncomplete = "alert( 'Debe ingresar al docente para ingresar un lineamiento' );Richfaces.hideModalPanel('mpConfirmarEnlazar');";
//            }
//        } catch ( Exception ex ) {
//            ex.printStackTrace();
//            oncomplete = "alert( 'Error al ingresar curso seleccionado.' );";
//        }
//        oncomplete += "Richfaces.hideModalPanel('mpConfirmarEnlazar')";
//    }
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public SelectItem[] getCboSituacion() {
        HSCatalogoDAO dao = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        List lista = dao.seleccionarCatalogo("002");
        cboSituacion = new SelectItem[lista.size()];
        for (int i = 0; i < cboSituacion.length; i++) {
            cboSituacion[i] = new SelectItem(((TbCatalogo) lista.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista.get(i)).getCatDescripcionElemento());
        }
        return cboSituacion;
    }

    public void setCboSituacion(SelectItem[] cboSituacion) {
        this.cboSituacion = cboSituacion;
    }


    /*nuevoi codiho 21-02-2012*/
    public SelectItem[] getCboTipoDocente() {
        HSCatalogoDAO dao = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        List lista = dao.seleccionarCatalogo("071");
        cboTipoDocente = new SelectItem[lista.size()];
        for (int i = 0; i < cboTipoDocente.length; i++) {
            cboTipoDocente[i] = new SelectItem(((TbCatalogo) lista.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista.get(i)).getCatDescripcionElemento());
        }
        return cboTipoDocente;
    }

    public void setCboTipoDocente(SelectItem[] cboTipoDocente) {
        this.cboTipoDocente = cboTipoDocente;
    }

    /**/
    /*nuevoi codiho 21-02-2012*/
    public SelectItem[] getB_cboFacultades() {
        HSFacultadDAO dao = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
        List<AcFacultad> facultad = dao.seleccionarFacultadId();//sem_id=19
        b_cboFacultades = new SelectItem[facultad.size() + 1];
        b_cboFacultades[0] = new SelectItem(0, "[Seleccione]");
        for (int i = 0; i < b_cboFacultades.length - 1; i++) {
            b_cboFacultades[i + 1] = new SelectItem(facultad.get(i).getId(), facultad.get(i).getFacNombre());
        }
        return b_cboFacultades;
    }

    public void setB_cboFacultades(SelectItem[] b_cboFacultades) {
        this.b_cboFacultades = b_cboFacultades;
    }

    /**/
    public SelectItem[] getRdoSexo() {
        HSCatalogoDAO dao = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        List lista = dao.seleccionarCatalogo("004");
        rdoSexo = new SelectItem[lista.size()];
        for (int i = 0; i < rdoSexo.length; i++) {
            rdoSexo[i] = new SelectItem(((TbCatalogo) lista.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista.get(i)).getCatDescripcionElemento());
        }
        return rdoSexo;
    }

    public void setRdoSexo(SelectItem[] rdoSexo) {
        this.rdoSexo = rdoSexo;
    }
    
    public SelectItem[] getComboSemestres() throws Exception {
        HSSemestreDAO dao = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
        List lista = dao.seleccionarSemestreVigente();
        int num = lista.size();
        comboSemestres = new SelectItem[1];
        comboSemestres[0] = new SelectItem(0, "[Seleccione]");
        if (!lista.isEmpty()) {
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

    public int getSem_id() {
        return sem_id;
    }

    public void setSem_id(int sem_id) {
        this.sem_id = sem_id;
    }

    public int getTurno_id2() {
        return turno_id2;
    }

    public void setTurno_id2(int turno_id2) {
        this.turno_id2 = turno_id2;
    }
    
    
    
    public SelectItem[] getComboTurnos2() {
        HSTurnoDAO dao = (HSTurnoDAO) ServiceFinder.findBean("HibernateSpringDaoTurno");
        List<AcTurno> turnos = dao.seleccionarTurno(this.getSem_id());
        comboTurnos2 = new SelectItem[turnos.size() + 1];
        comboTurnos2[0] = new SelectItem(0, "[Seleccione]");
        for (int i = 0; i < comboTurnos2.length - 1; i++) {
            comboTurnos2[i + 1] = new SelectItem(turnos.get(i).getId(), turnos.get(i).getTurNombre());
        }
        return comboTurnos2;
    }

    public void setComboTurnos2(SelectItem[] comboTurnos) {
        this.comboTurnos = comboTurnos;
    }

    public String getW_depa_naci() {
        return w_depa_naci;
    }

    public void setW_depa_naci(String w_depa_naci) {
        this.w_depa_naci = w_depa_naci;
    }

    public String getW_depa_resi() {
        return w_depa_resi;
    }

    public void setW_depa_resi(String w_depa_resi) {
        this.w_depa_resi = w_depa_resi;
    }

    public String getW_prov_naci() {
        return w_prov_naci;
    }

    public void setW_prov_naci(String w_prov_naci) {
        this.w_prov_naci = w_prov_naci;
    }

    public String getW_prov_resi() {
        return w_prov_resi;
    }

    public void setW_prov_resi(String w_prov_resi) {
        this.w_prov_resi = w_prov_resi;
    }

    public BeanDocente getDocente() {
        return docente;
    }

    public void setDocente(BeanDocente docente) {
        this.docente = docente;
    }

    public String getDoc_dni_w() {
        return doc_dni_w;
    }

    public void setDoc_dni_w(String doc_dni_w) {
        this.doc_dni_w = doc_dni_w;
    }

    public String getDoc_nombre_completo_w() {
        return doc_nombre_completo_w;
    }

    public void setDoc_nombre_completo_w(String doc_nombre_completo_w) {
        this.doc_nombre_completo_w = doc_nombre_completo_w;
    }

    public SelectItem[] getCboDepartamentos_n() {
        try {
            HSUbigeoDAO dao = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
            List lista = dao.seleccionarDepartamento();
            cboDepartamentos_n = new SelectItem[lista.size() + 1];
            cboDepartamentos_n[0] = new SelectItem("000000", "[Seleccione]");
            for (int i = 0; i < (cboDepartamentos_n.length - 1); i++) {
                cboDepartamentos_n[i + 1] = new SelectItem(((TbDistrito) lista.get(i)).getId(), ((TbDistrito) lista.get(i)).getDisDes());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cboDepartamentos_n;
    }

    public void setCboDepartamentos_n(SelectItem[] cboDepartamentos_n) {
        this.cboDepartamentos_n = cboDepartamentos_n;
    }

    public SelectItem[] getCboDepartamentos_r() {
        try {
            HSUbigeoDAO dao = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
            List lista = dao.seleccionarDepartamento();
            cboDepartamentos_r = new SelectItem[lista.size() + 1];
            cboDepartamentos_r[0] = new SelectItem("000000", "[Seleccione]");
            for (int i = 0; i < (cboDepartamentos_r.length - 1); i++) {
                cboDepartamentos_r[i + 1] = new SelectItem(((TbDistrito) lista.get(i)).getId(), ((TbDistrito) lista.get(i)).getDisDes());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cboDepartamentos_r;
    }

    public void setCboDepartamentos_r(SelectItem[] cboDepartamentos_r) {
        this.cboDepartamentos_r = cboDepartamentos_r;
    }

    public SelectItem[] getCboDistrito_n() {
        try {
            HSUbigeoDAO dao = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
            List<TbDistrito> lista = dao.seleccionarDistrito(this.getW_depa_naci(), this.getW_prov_naci());
            if (lista.size() != 0) {
                cboDistrito_n = new SelectItem[lista.size() + 1];
                cboDistrito_n[0] = new SelectItem("000000", "[Seleccione]");
                for (int i = 0; i < (cboDistrito_n.length - 1); i++) {
                    cboDistrito_n[i + 1] = new SelectItem(lista.get(i).getId(), lista.get(i).getDisDes());
                }
            } else {
                cboDistrito_n = new SelectItem[1];
                cboDistrito_n[0] = new SelectItem("000000", "[Seleccione]");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cboDistrito_n;
    }

    public void setCboDistrito_n(SelectItem[] cboDistrito_n) {
        this.cboDistrito_n = cboDistrito_n;
    }

    public SelectItem[] getCboDistrito_r() {
        try {
            HSUbigeoDAO dao = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
            List<TbDistrito> lista = dao.seleccionarDistrito(this.getW_depa_resi(), this.getW_prov_resi());
            if (lista.size() != 0) {
                cboDistrito_r = new SelectItem[lista.size() + 1];
                cboDistrito_r[0] = new SelectItem("000000", "[Seleccione]");
                for (int i = 0; i < (cboDistrito_r.length - 1); i++) {
                    cboDistrito_r[i + 1] = new SelectItem(lista.get(i).getId(), lista.get(i).getDisDes());
                }
            } else {
                cboDistrito_r = new SelectItem[1];
                cboDistrito_r[0] = new SelectItem("000000", "[Seleccione]");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cboDistrito_r;
    }

    public void setCboDistrito_r(SelectItem[] cboDistrito_r) {
        this.cboDistrito_r = cboDistrito_r;
    }

    public SelectItem[] getCboProvincia_n() {
        try {
            HSUbigeoDAO dao = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
            List<TbDistrito> lista = dao.seleccionarProvincia(this.getW_depa_naci());
            if (lista.size() != 0) {
                cboProvincia_n = new SelectItem[lista.size() + 1];
                cboProvincia_n[0] = new SelectItem("000000", "[Seleccione]");
                for (int i = 0; i < (cboProvincia_n.length - 1); i++) {
                    cboProvincia_n[i + 1] = new SelectItem(lista.get(i).getId(), lista.get(i).getDisDes());
                }
            } else {
                cboProvincia_n = new SelectItem[1];
                cboProvincia_n[0] = new SelectItem("000000", "[Seleccione]");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cboProvincia_n;
    }

    public void setCboProvincia_n(SelectItem[] cboProvincia_n) {
        this.cboProvincia_n = cboProvincia_n;
    }

    public SelectItem[] getCboProvincia_r() {
        try {
            HSUbigeoDAO dao = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
            List<TbDistrito> lista = dao.seleccionarProvincia(this.getW_depa_resi());
            if (lista.size() != 0) {
                cboProvincia_r = new SelectItem[lista.size() + 1];
                cboProvincia_r[0] = new SelectItem("000000", "[Seleccione]");
                for (int i = 0; i < (cboProvincia_r.length - 1); i++) {
                    cboProvincia_r[i + 1] = new SelectItem(lista.get(i).getId(), lista.get(i).getDisDes());
                }
            } else {
                cboProvincia_r = new SelectItem[1];
                cboProvincia_r[0] = new SelectItem("000000", "[Seleccione]");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cboProvincia_r;
    }

    public void setCboProvincia_r(SelectItem[] cboProvincia_r) {
        this.cboProvincia_r = cboProvincia_r;
    }

    public List<BeanDocente> getListaDocente() {
        return listaDocente;
    }

    public void setListaDocente(List<BeanDocente> listaDocente) {
        this.listaDocente = listaDocente;
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

    public mbDocente() {
        /* iniciarCombosNaci();
         iniciarCombosResi();*/
        docente = new BeanDocente();
        this.horaria = new BeanHorarioDispDoc(0);
        this.nListaHoraria = new ArrayList<BeanHorarioDispDoc>();
        this.qListaHoraria = new ArrayList<BeanHorarioDispDoc>();
        this.nLstDocenteCurso = new ArrayList<CursoDocenteBean>();
        this.qLstDocenteCurso = new ArrayList<CursoDocenteBean>();
    }

    public void iniciarCombosNaci() {
        cboDepartamentos_n = new SelectItem[1];
        cboDepartamentos_n[0] = new SelectItem(0, "[Seleccione]");
        cboDistrito_n = new SelectItem[1];
        cboDistrito_n[0] = new SelectItem(0, "[Seleccione]");
        cboProvincia_n = new SelectItem[1];
        cboProvincia_n[0] = new SelectItem(0, "[Seleccione]");
    }

    public void iniciarCombosResi() {
        cboDepartamentos_r = new SelectItem[1];
        cboDepartamentos_r[0] = new SelectItem(0, "[Seleccione]");
        cboDistrito_r = new SelectItem[1];
        cboDistrito_r[0] = new SelectItem(0, "[Seleccione]");
        cboProvincia_r = new SelectItem[1];
        cboProvincia_r[0] = new SelectItem(0, "[Seleccione]");

    }

    public void buscarDocenteEvento(ActionEvent event) {
        buscarDocente();
    }

    public void buscarDocente() {

        if (this.getB_facultad2() != 0 && this.getB_especialidad2() == 0) {
            this.setOncomplete("javascript:alert('SELECCIONE UNA ESPECIALIDAD')");
        } else if (!this.getB_ciclos2().equals("000000") && this.getB_cursos2() == 0) {
            this.setOncomplete("javascript:alert('SELECCIONE UN CURSO')");
        } else {
            try {
                HSDocenteDAO dao = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");

                List<AcDocente> lista = dao.seleccionarDocenteHorario(this.getDoc_dni_w(), this.getDoc_nombre_completo_w(), this.getB_disponibilidadA(), this.getB_cursosA(), this.getB_ciclos2(), this.getB_cursos2(),this.getTurno_id2());
                List<BeanDocente> listaT = new ArrayList<BeanDocente>();
                HSDocenteDAO daoDOCHor = CommonDAO.getAcDocenteDAO();

                for (AcDocente beanDocente : lista) {

                    BeanDocente bC = new BeanDocente();
                    bC.setId(beanDocente.getId());
                    bC.setDocCodigo(beanDocente.getDocCodigo());
                    bC.setDocNombre(beanDocente.getDocNombre());
                    bC.setDocCorreo(beanDocente.getDocCorreo());
                    bC.setDocTelefono(beanDocente.getDocTelefono());
                    bC.setDocNacimiento(beanDocente.getDocNacimiento());
                    bC.setDocResidencia(beanDocente.getDocResidencia());
                    bC.setDocPeriodoInicio(beanDocente.getDocPeriodoInicio() == null ? new Date() : beanDocente.getDocPeriodoInicio());
                    bC.setDocSituacion(beanDocente.getDocSituacion());
                    bC.setDocSexo(beanDocente.getDocSexo());
                    bC.setDocDni(beanDocente.getDocDni());
                    bC.setDocAppaterno(beanDocente.getDocAppaterno());
                    bC.setDocApmaterno(beanDocente.getDocApmaterno());
                    bC.setDocNombres(beanDocente.getDocNombres());
                    bC.setDocTipo(beanDocente.getDocTipo());
                    if (daoDOCHor.seleccionarHorario(bC.getId()).isEmpty()) {
                        bC.setImagen_horario("/Imagenes/actions/horario_gris.png");
                    } else {
                        bC.setImagen_horario("/Imagenes/actions/horario.png");
                        // sProfesor = daoClHor.seleccionarHorario( tmp_sec.getSecId() ).get( 0 ).getAcDocente().getDocNombre();
                    }
                    listaT.add(bC);
                }
                this.setListaDocente(listaT);
                this.setOncomplete("");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private String getValString(Object obj) {
        String sVal;
        sVal = obj == null ? "" : obj.toString();
        return sVal;
    }

    public void limpiarData(ActionEvent event) {
        this.setOncomplete("");
        iniciarCombosNaci();
        iniciarCombosResi();
        BeanDocente doc = new BeanDocente();
        doc.setId(0);
        this.setMensaje("");
        this.setDocente(doc);
        this.setW_depa_naci("00");
        this.setW_depa_resi("00");
        this.setW_prov_naci("0000");
        this.setW_prov_resi("0000");
        this.setB_especialidad(0);
        this.setB_facultad(0);
        this.setB_ciclos("0");
        this.setB_cursos(0);
        this.setB_asignacion("000000");
        this.getLstCursoDocente().clear();
        this.setOncomplete("javascript:Richfaces.showModalPanel('mpDocente');");
    }

    public void buscarDocentes(ActionEvent event) {
        try {
            this.setOncomplete("");
            this.setMensaje("");
            HSDocenteDAO dao = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");
            Integer p_id2 = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_id2")).getValue().toString());
            AcDocente doc = dao.seleccionarDocente(p_id2);
            this.getDocente().setId(doc.getId());
            this.getDocente().setDocActivo(doc.getDocActivo());
            this.getDocente().setDocApmaterno(doc.getDocApmaterno());
            this.getDocente().setDocAppaterno(doc.getDocAppaterno());
            this.getDocente().setDocDni(doc.getDocDni());
            this.getDocente().setDocCorreo(doc.getDocCorreo());
            this.getDocente().setDocCodigo(doc.getDocCodigo());
            this.getDocente().setDocNacimiento(doc.getDocNacimiento());
            this.getDocente().setDocNombre(doc.getDocNombre());
            this.getDocente().setDocNombres(doc.getDocNombres());
            this.getDocente().setDocPassword(doc.getDocPassword());
            this.getDocente().setDocPeriodoInicio(doc.getDocPeriodoInicio());
            this.getDocente().setDocResidencia(doc.getDocResidencia());
            this.getDocente().setDocSexo(doc.getDocSexo());
            this.getDocente().setDocSituacion(doc.getDocSituacion());
            this.getDocente().setDocTelefono(doc.getDocTelefono());
            this.getDocente().setDocTipo(doc.getDocTipo());
            this.getDocente().setDocFacId(doc.getDocFacId());

            this.setW_depa_naci(doc.getDocNacimiento().toString().substring(0, 2) + "0000");
            this.setW_prov_naci(doc.getDocNacimiento().toString().substring(0, 4) + "00");
            this.setW_depa_resi(doc.getDocResidencia().toString().substring(0, 2) + "0000");
            this.setW_prov_resi(doc.getDocResidencia().toString().substring(0, 4) + "00");
            if (doc.getDocNacimiento().equals("000000")) {
                this.setW_depa_naci("000000");
                this.setW_prov_naci("000000");
            }

            if (doc.getDocResidencia().equals("000000")) {
                this.setW_depa_resi("000000");
                this.setW_prov_resi("000000");
            }

//            listarEnlaces( p_id2 );
            String sDocDetalle;
            CursoDocenteBean cursoDocenteBean;
            AcDocenteCurso acDocCurso;
            HSCatalogoDAO daoCat;
            HSDocenteCursoDAO daoDocCurso = (HSDocenteCursoDAO) ServiceFinder.findBean("SpringHibernateDaoDocenteCurso");
            List<AcDocenteCurso> lstDocenteCurso;

            sDocDetalle = ((UIParameter) event.getComponent().findComponent("p_doc_detalle")).getValue().toString();
            this.setOncomplete("");
            daoCat = CommonDAO.getTbCatalogoDAO();

            this.setDoc_id(p_id2);
            doc_id_aux = this.getDoc_id();
            this.setDoc_detalle(sDocDetalle);
            this.docenteCurso = new CursoDocenteBean(p_id2);
            this.nLstDocenteCurso = new ArrayList<CursoDocenteBean>();
            this.qLstDocenteCurso = new ArrayList<CursoDocenteBean>();

            lstDocenteCurso = daoDocCurso.listadoDocenteCurso(p_id2);

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

    public void cargarCursos() throws Exception {
        this.nLstDocenteCurso.clear();
        HSDocenteCursoDAO daoDocCurso = (HSDocenteCursoDAO) ServiceFinder.findBean("SpringHibernateDaoDocenteCurso");
        HSCatalogoDAO daoCat = CommonDAO.getTbCatalogoDAO();
        List<AcDocenteCurso> lstDocenteCurso;
        AcDocenteCurso acDocCurso;
        CursoDocenteBean cursoDocenteBean;
        lstDocenteCurso = daoDocCurso.listadoDocenteCurso(this.getDoc_id());

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
    }

    public void cargarHorario(ActionEvent event) throws Exception {
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
        // this.horaria.getAcDocente().setId( iDocId );
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
        mostrarTablaHorarios();
        buscarDocente();
        this.setOncomplete("javascript:Richfaces.showModalPanel('mpHoraria')");
        
        
    }

    public void agregarCurso(ActionEvent event) {
        int iDocId;
        String sMsg;
        CursoDocenteBean bClHorAux;
        HSCatalogoDAO daoCat;
        iDocId = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_id")).getValue().toString());
        this.setOncomplete("");
        daoCat = CommonDAO.getTbCatalogoDAO();

        sMsg = esValido();

        if (sMsg.isEmpty()) {

            bClHorAux = new CursoDocenteBean(iDocId);
            bClHorAux.setAcDocente(new AcDocente(docenteCurso.getAcDocente().getId()));
            bClHorAux.setAcCurso(new AcCurso(b_cursos));
            bClHorAux.setV_asignacion(b_asignacion);
            bClHorAux.setEstadoAsignacion(daoCat.seleccionarDescripcion(b_asignacion));
            bClHorAux.setActivo(1);
            bClHorAux.setFechaCreacion(new Timestamp(new Date().getTime()));
            bClHorAux.actualizarDatos(iDocId);
            bClHorAux.setPosicion(this.nLstDocenteCurso.size());
            this.addnLstDocenteCurso(bClHorAux);
        } else {
            this.setOncomplete("javascript:alert('" + sMsg + "')");
        }
    }

    public void guardarCursos(ActionEvent event) throws Exception {
        HSDocenteCursoDAO daoDocHor = (HSDocenteCursoDAO) ServiceFinder.findBean("SpringHibernateDaoDocenteCurso");
        List<Integer> lstIdsCursoDocenteElim;
        List<AcDocenteCurso> lstCursos;

        this.setOncomplete("");
        if (this.nLstDocenteCurso.isEmpty() && this.qLstDocenteCurso.isEmpty()) {
            this.setOncomplete("javascript:alert('No se a realizado ningún cambio')");
        } else if (edicionActivadaCurso()) {
            this.setOncomplete("javascript:alert('Termine de editar el curso a asignar.')");
        } else {
            if (!nLstDocenteCurso.isEmpty()) {
                lstCursos = new ArrayList<AcDocenteCurso>();
                int cantidadHoras=0;
                String mensaje = "";
                for (CursoDocenteBean tmp_horaria : nLstDocenteCurso) {
                    if(estadoAsignacion.equals(tmp_horaria.getAcDocenteCurso().getEstadoAsignacion())){
                        cantidadHoras=cantidadHoras+(tmp_horaria.getCursoDocenteBean().getAcCurso().getCurHorLab()+tmp_horaria.getCursoDocenteBean().getAcCurso().getCurHorTeo());
                    }
                    lstCursos.add(tmp_horaria.getCursoDocenteBean());
                }
                HSParametroDAO dao = (HSParametroDAO) ServiceFinder.findBean("SpringHibernateDaoParametro");
                AcParametro p = dao.buscarParametro(ConstantesWeb.PARAMETRO.CANT_MINIMA_ASIGNACION);
                AcParametro p2 = dao.buscarParametro(ConstantesWeb.PARAMETRO.CANT_MAX_ASIGNACION);
                int cantMin;
                int cantMax;
                if(p!=null){
                    cantMin = Integer.parseInt(p.getParVal());
                }else{
                    cantMin = Integer.parseInt(ConstantesWeb.PARAMETRO.DEFAULT_MINIMA_ASIGNACION);
                }
                if(p2!=null){
                    cantMax = Integer.parseInt(p2.getParVal());
                }else{
                    cantMax = Integer.parseInt(ConstantesWeb.PARAMETRO.DEFAULT_MAX_ASIGNACION);
                }
                if(cantidadHoras<cantMin){
                        mensaje= "Minimo debe asignar "+cantMin+" horas";
                        mensaje+=" Se guardo los cambios sadisfactoriamente";
                        daoDocHor.insertarActualizarCursoDocente(lstCursos);
                        this.setOncomplete("javascript:alert('"+mensaje+"');");
                }else{
                    if(cantidadHoras > cantMax){
                        mensaje="Maximo debe asignar"+cantMax+"horas \n";
                        this.setOncomplete("javascript:alert('"+mensaje+"');");
                    }else{
                        mensaje=" Se guardo los cambios sadisfactoriamente";
                        daoDocHor.insertarActualizarCursoDocente(lstCursos);
                        this.setOncomplete("javascript:alert('"+mensaje+"');");
                    }
                }
               
            }
            if (!qLstDocenteCurso.isEmpty()) {
                lstIdsCursoDocenteElim = new ArrayList<Integer>();
                for (CursoDocenteBean tmp_horaria : qLstDocenteCurso) {
                    lstIdsCursoDocenteElim.add(tmp_horaria.getCurdoc_id());
                }
                daoDocHor.eliminarCursosDocente(lstIdsCursoDocenteElim);
                this.setOncomplete("javascript:alert('Se guardo los cambios sadisfactoriamente.');"
                    + "Richfaces.hideModalPanel('mpHoraria');");
            }
            
            cargarCursos();
            //Seleccionar();
        }
    }

    public String esValido() {
        String sMsg = "";
        if (getDoc_id() == 0) {
            sMsg = "Seleccionar Docente";
        } else if (b_asignacion == null || "000000".equals(b_asignacion)) {
            sMsg = "Seleccionar un estado de asignacion";
        } else if (b_cursos == 0) {
            sMsg = "Seleccionar un curso";
        } else {
            for (CursoDocenteBean beanCursoDocente : this.nLstDocenteCurso) {
                if (beanCursoDocente.getAcCurso().getId() == b_cursos) {
                    sMsg = "este curso ya ha sido ingresado ingrese para este docente otro porfavor";
                }
            }
        }
        return sMsg;
    }

    public void removerCurso(ActionEvent event) {
        int iSizeHor;
        int iHorPosIndex;
        CursoDocenteBean bClHorAux;
        iHorPosIndex = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_hor_pos")).getValue().toString());
        iSizeHor = nLstDocenteCurso.size();
        for (int i = iHorPosIndex + 1; i < iSizeHor; i++) {
            bClHorAux = nLstDocenteCurso.get(i);
            bClHorAux.setPosicion(bClHorAux.getPosicion() - 1);
        }

        this.addqLstDocenteCurso(this.nLstDocenteCurso.remove(iHorPosIndex));
    }

    public void limpiar() {
        listaHorario.clear();
//        aula_id_aux = 0;
//        docente_id_aux = 0;
//        tur_id_aux = 0;
        this.turno_id = 0;
    }

//    public void agregarHorario( ActionEvent event ) {
//        String sMsg;
//        BeanHorarioDispDoc bClHorAux;
//        this.setOncomplete( "" );
//        this.horaria.getAcDocente().setId( this.getDoc_id() );
//        sMsg = this.horaria.esValido();
//        if ( sMsg.isEmpty() ) {
//            for ( String sDiaCod : this.horaria.getLstDiasCod() ) {
//                bClHorAux = new BeanHorarioDispDoc( this.doc_id );
//                bClHorAux.setAcDocente( new AcDocente( horaria.getAcDocente().getId() ) );
//                bClHorAux.setInicio_h( horaria.getInicio_h() );
//                bClHorAux.setInicio_m( horaria.getInicio_m() );
//                bClHorAux.setMeridiano_inicio( horaria.getMeridiano_inicio() );
//                bClHorAux.setFin_h( horaria.getFin_h() );
//                bClHorAux.setFin_m( horaria.getFin_m() );
//                bClHorAux.setMeridiano_fin( horaria.getMeridiano_fin() );
//                bClHorAux.actualizarDatos( doc_id );
//                bClHorAux.setPosicion( this.nListaHoraria.size() );
//                bClHorAux.setV_hor_dia( CommonDAO.getTbCatalogoDAO().seleccionarDescripcion( sDiaCod ) );
//                bClHorAux.setHorDia( sDiaCod );
//                this.addnListaHoraria( bClHorAux );
//            }
//
//            this.horaria = new BeanHorarioDispDoc( this.getDoc_id() );
//
//        } else {
//            this.setOncomplete( "javascript:alert('" + sMsg + "')" );
//        }
//    }
    public void removerHorario(ActionEvent event) {
        int iSizeHor;
        int iHorPosIndex;
        BeanHorarioDispDoc bClHorAux;

        iHorPosIndex = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_hor_pos")).getValue().toString());
        iSizeHor = nListaHoraria.size();
        for (int i = iHorPosIndex + 1; i < iSizeHor; i++) {
            bClHorAux = nListaHoraria.get(i);
            bClHorAux.setPosicion(bClHorAux.getPosicion() - 1);
        }
        this.addqListaHoraria(this.nListaHoraria.remove(iHorPosIndex));
    }

    public boolean edicionActivada() {
        for (BeanHorarioDispDoc bean : nListaHoraria) {
            if (bean.isEdit_active()) {
                return true;
            }
        }
        return false;
    }

    public boolean edicionActivadaCurso() {
        for (CursoDocenteBean bean : nLstDocenteCurso) {
            if (bean.isEdit_active()) {
                return true;
            }
        }
        return false;
    }

    public void guardarHorarios(ActionEvent event) throws Exception {
        HSDocenteDAO daoDocHor = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");
        List<Integer> lstIdsHorariosElim;
        List<AcHorarioDispDocente> lstHorarios;

        this.setOncomplete("");
        if (this.nListaHoraria.isEmpty()) {
            this.setOncomplete("javascript:alert('Debe ingresar los horarios para Guardar.')");
        } else if (edicionActivada()) {
            this.setOncomplete("javascript:alert('Termine de editar los horarios.')");
        } else {

            lstHorarios = new ArrayList<AcHorarioDispDocente>();
            for (BeanHorarioDispDoc tmp_horaria : nListaHoraria) {
                lstHorarios.add(tmp_horaria.getAcHorarioDispDocente());
            }
            daoDocHor.insertarActualizarHorarios(lstHorarios);
            if (!qListaHoraria.isEmpty()) {
                lstIdsHorariosElim = new ArrayList<Integer>();
                for (BeanHorarioDispDoc tmp_horaria : qListaHoraria) {
                    lstIdsHorariosElim.add(tmp_horaria.getHorId());
                }
                daoDocHor.eliminarHorarios(lstIdsHorariosElim);
            }
            this.setOncomplete("javascript:alert('Generacion de horarios satisfactoria.');"
                    + "Richfaces.hideModalPanel('mpHoraria');");
            //Seleccionar();
        }
    }

    public void eliminarDocente(ActionEvent event) {
        setMensaje("");
        HSDocenteDAO dao = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");
        String p_id2 = ((UIParameter) event.getComponent().findComponent("p_id2")).getValue().toString();
        HSHorarioDAO dao_h = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoHorario");

        if (dao_h.SeleccionarHorarioPorDocente(Integer.parseInt(p_id2), 9) == 0) {
            dao.eliminarDocente(p_id2.toString());
            buscarDocente();
        } else {
            setMensaje("El Docente tiene un horario asignado, no se puede eliminar");
        }

    }

    public void guardarDatos(ActionEvent event) {
        try {
            this.setOncomplete("");
            this.setMensaje("");
            HSDocenteDAO dao = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");
            int p_id = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_id")).getValue().toString());
            this.getDocente().setDocActivo("1");
            this.getDocente().setDocCodigo(this.getDocente().getDocDni());
            this.getDocente().setDocNombre(this.getDocente().getDocAppaterno() + " " + this.getDocente().getDocApmaterno() + " " + this.getDocente().getDocNombres());
            // this.getDocente().setDocPassword(this.getDocente().getDocDni());
            AcDocente doc = new AcDocente();
            doc.setId(p_id);
            doc.setDocActivo("1");
            doc.setDocApmaterno(this.getDocente().getDocApmaterno());
            doc.setDocAppaterno(this.getDocente().getDocAppaterno());
            doc.setDocCodigo(this.getDocente().getDocDni());
            doc.setDocCorreo(this.getDocente().getDocCorreo());
            doc.setDocDni(this.getDocente().getDocDni());
            doc.setDocNacimiento(this.getDocente().getDocNacimiento());
            doc.setDocNombre(this.getDocente().getDocAppaterno() + " " + this.getDocente().getDocApmaterno() + " " + this.getDocente().getDocNombres());
            doc.setDocNombres(this.getDocente().getDocNombres());
            //doc.setDocPassword(doc_dni_w);
            doc.setDocPeriodoInicio(this.getDocente().getDocPeriodoInicio());
            doc.setDocResidencia(this.getDocente().getDocResidencia());
            doc.setDocSexo(this.getDocente().getDocSexo());
            doc.setDocSituacion(this.getDocente().getDocSituacion());
            doc.setDocTelefono(this.getDocente().getDocTelefono());
            doc.setDocTipo(this.getDocente().getDocTipo());
            doc.setDocFacId(this.getDocente().getDocFacId());
            //String mensaje="";
            if (p_id > 0) {
                doc.setDocPassword(this.getDocente().getDocPassword());
                if (validarCampos(doc)) {
                    dao.actualizarDocente(doc);
                    this.setOncomplete("javascript:alert('Registro modificado con exito');Richfaces.hideModalPanel('mpDocente');");
                }

            } else {
                doc.setDocPassword(this.getDocente().getDocDni());
                //HSDocenteDAO daoDoce = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");
                if (doc.getDocDni().length() == 8) {
                    //if(this.getB_facultad()>0){
                    if (dao.buscarDocente(this.getDocente().getDocDni()) == null) {
                        if (validarCampos(doc)) {
                            dao.insertarDocente(doc);
                            this.setOncomplete("javascript:alert('Registro agregado con exito');Richfaces.hideModalPanel('mpDocente');");
                        }
                    } else {
                        this.setOncomplete("javascript:alert('El numero de DNI ya existe')");
                        //}
                        // else{
                        //  this.setOncomplete( "javascript:alert('Seleccione una facultad')" );
                    }
                } else {
                    this.setOncomplete("javascript:alert('Ingrese un dni valido')");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean validarCampos(AcDocente doc) {
        if (doc.getDocAppaterno().length() < 3) {
            oncomplete = "";
            this.setOncomplete("javascript:alert('Ingrese Apellido Paterno')");
            return false;

        }
        if (doc.getDocApmaterno().length() < 3) {
            oncomplete = "";
            this.setOncomplete("javascript:alert('Ingrese Apellido Materno')");
            return false;
        }
        if (doc.getDocNombres().length() < 3) {
            oncomplete = "";
            this.setOncomplete("javascript:alert('Ingrese Nombres')");
            return false;
        }
        if (isEmail(doc.getDocCorreo().trim())) {
            if (doc.getDocCorreo().length() < 10) {
                oncomplete = "";
                this.setOncomplete("javascript:alert('Ingrese Correo Electronico')");
                return false;
            }
        } else {
            this.setOncomplete("javascript:alert('Ingrese un correo valido')");
            return false;
        }
        if (doc.getDocTelefono().length() < 5) {
            oncomplete = "";
            this.setOncomplete("javascript:alert('Ingrese nro telefono o celular')");
            return false;
        }
        if ((doc.getDocSexo().trim()).isEmpty()) {
            this.setOncomplete("javascript:alert('Elija un sexo')");
            return false;
        }
        if ((doc.getDocNacimiento().trim()).isEmpty()) {
            this.setOncomplete("javascript:alert('Ingrese una fecha de nacimiento')");
            return false;
        }
        if (doc.getDocPeriodoInicio() != null && !doc.getDocPeriodoInicio().equals("")) {
            System.out.println(doc.getDocPeriodoInicio());
            this.setOncomplete("javascript:alert('Ingrese una fecha de nacimiento')");
            return false;
        }
        if (doc.getDocFacId() != 0) {
            this.setOncomplete("javascript:alert('Seleccione una facultad')");
            return false;
        }
        if (this.getB_facultad() != 0) {
            this.setOncomplete("javascript:alert('Seleccione una facultad')");
            return false;
        }

        return true;
    }

    public boolean isEmail(String correo) {
        //System.out.println( "[isEmail] << ENTER" );
        if (correo.isEmpty()) {
            //System.out.println( "[isEmail] >> (correo.isEmpty = true)EXIT" );
            return true;
        } else {
            Pattern pat;
            Matcher mat;
            String EMAIL_PATTERN
                    = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            //pat = Pattern.compile( "^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z]{2,9}.)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,9})$" );
            pat = Pattern.compile(EMAIL_PATTERN);
            mat = pat.matcher(correo);
            if (mat.find()) {
                //System.out.println( "[isEmail] >> (mat.find = true)EXIT" );
                //System.out.println( "[" + mat.group() + "]" );
                return true;
            } else {
                //System.out.println( "[isEmail] >> (mat.find = false)EXIT" );
                return false;
            }
        }

    }

    public void seleccionarLunes(ActionEvent event) {
        UIOutput lunes = (UIOutput) event.getComponent().findComponent("lunes");

        UIParameter id_turno = (UIParameter) event.getComponent().findComponent("param_id_turno");
        UIParameter id_doc = (UIParameter) event.getComponent().findComponent("ho_docente");

        int docente_id_aux = aInteger(id_doc.getValue().toString());

        if (lunes.getValue().toString().equals("")) {
            System.out.println("identificador: " + ide);
            System.out.println("doc: " + docente_id_aux);

            int agregar = agregarHorario(ide, "016001",
                    aInteger(id_turno.getValue().toString()), docente_id_aux, "1");
            if (agregar == 1) {
                this.setIndice_id_lun(ide + 1);
                this.setLunes_hor("X");
            }
        } else {
            this.setLunes_hor("");
            quitarHorario(event, 1);
        }
    }

    public void seleccionarMartes(ActionEvent event) {
        UIOutput martes = (UIOutput) event.getComponent().findComponent("martes");

        UIParameter id_turno = (UIParameter) event.getComponent().findComponent("param_id_turno");
        UIParameter id_doc = (UIParameter) event.getComponent().findComponent("ho_docente");
        int docente_id_aux = aInteger(id_doc.getValue().toString());

        if (martes.getValue().toString().equals("")) {
            System.out.println("identificador: " + ide);
            System.out.println("doc: " + docente_id_aux);

            int agregar = agregarHorario(ide, "016002",
                    aInteger(id_turno.getValue().toString()), docente_id_aux, "1");
            if (agregar == 1) {
                this.setIndice_id_mar(ide + 1);
                this.setMartes_hor("X");
            }
        } else {
            this.setMartes_hor("");
            quitarHorario(event, 2);
        }
    }

    public void seleccionarMiercoles(ActionEvent event) {
        UIOutput miercoles = (UIOutput) event.getComponent().findComponent("miercoles");

        UIParameter id_turno = (UIParameter) event.getComponent().findComponent("param_id_turno");
        UIParameter id_doc = (UIParameter) event.getComponent().findComponent("ho_docente");
        int docente_id_aux = aInteger(id_doc.getValue().toString());

        if (miercoles.getValue().toString().equals("")) {
            System.out.println("identificador: " + ide);
            System.out.println("doc: " + docente_id_aux);

            int agregar = agregarHorario(ide, "016003",
                    aInteger(id_turno.getValue().toString()), docente_id_aux, "1");
            if (agregar == 1) {
                this.setIndice_id_mie(ide + 1);
                this.setMiercoles_hor("X");
            }
        } else {
            this.setMiercoles_hor("");
            quitarHorario(event, 3);
        }
    }

    public void seleccionarJueves(ActionEvent event) {
        UIOutput jueves = (UIOutput) event.getComponent().findComponent("jueves");

        UIParameter id_turno = (UIParameter) event.getComponent().findComponent("param_id_turno");
        UIParameter id_doc = (UIParameter) event.getComponent().findComponent("ho_docente");
        int docente_id_aux = aInteger(id_doc.getValue().toString());

        if (jueves.getValue().toString().equals("")) {
            System.out.println("identificador: " + ide);
            System.out.println("doc: " + docente_id_aux);

            int agregar = agregarHorario(ide, "016004",
                    aInteger(id_turno.getValue().toString()), docente_id_aux, "1");
            if (agregar == 1) {
                this.setIndice_id_jue(ide + 1);
                this.setJueves_hor("X");
            }
        } else {
            this.setJueves_hor("");
            quitarHorario(event, 4);
        }
    }

    public void seleccionarViernes(ActionEvent event) {
        UIOutput viernes = (UIOutput) event.getComponent().findComponent("viernes");

        UIParameter id_turno = (UIParameter) event.getComponent().findComponent("param_id_turno");
        UIParameter id_doc = (UIParameter) event.getComponent().findComponent("ho_docente");
        int docente_id_aux = aInteger(id_doc.getValue().toString());

        if (viernes.getValue().toString().equals("")) {
            System.out.println("identificador: " + ide);
            System.out.println("doc: " + docente_id_aux);

            int agregar = agregarHorario(ide, "016005",
                    aInteger(id_turno.getValue().toString()), docente_id_aux, "1");
            if (agregar == 1) {
                this.setIndice_id_vie(ide + 1);
                this.setViernes_hor("X");
            }
        } else {
            this.setViernes_hor("");
            quitarHorario(event, 5);
        }
    }

    private int aInteger(String cadena) {
        try {
            return Integer.parseInt(cadena.trim());
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    public int agregarHorario(int identificador, String dia, int turno, int docente, String activo) {

        AcHorarioDispDocente horario = new AcHorarioDispDocente();
        AcDocente doc = new AcDocente();
        doc.setId(docente);

        AcTurnoDetalle turdet = new AcTurnoDetalle();
        turdet.setId(turno);
        horario.setHorId(identificador);
        horario.setHorActivo(activo);
        horario.setHorDia(dia);
        horario.setTurdet(turdet);
        horario.setAcDocente(doc);
        listaHorario.add(horario);
        System.out.println("identificador: " + identificador + "\tide: " + ide + "\tsize: " + listaHorario.size());
        ide--;
        this.setOncomplete("");
        return 1;

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
            AcHorarioDispDocente hora = horario.seleccionarUnHorario2(aInteger(id_ident.getValue().toString()));
            agregarHorario(hora.getHorId(), hora.getHorDia(), hora.getTurdet().getId(),
                    hora.getAcDocente().getId(), "0");
        }
        if (aInteger(id_ident.getValue().toString()) <= 0) {
            listaHorario.remove(encontrarIndice(aInteger(id_ident.getValue().toString())));
        }
    }

    public void Insertar(ActionEvent event) throws Exception {
        HSHorarioDAO dao = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoHorario");
        dao.insertarHorariosDispDoc(prepararInsercion(listaHorario));
        mostrarTablaHorarios();
        buscarDocente();
        this.setOncomplete("javascript:alert('SE GUARDO SADISFACTORIAMENTE');");
        listaHorario = new ArrayList<AcHorarioDispDocente>();
    }

    private List<AcHorarioDispDocente> prepararInsercion(List<AcHorarioDispDocente> lista_horarios) {
        List<AcHorarioDispDocente> lHorarios = new ArrayList<AcHorarioDispDocente>();
        AcHorarioDispDocente horario;
        for (int i = 0; i < lista_horarios.size(); i++) {
            horario = new AcHorarioDispDocente();
            if (lista_horarios.get(i).getHorId() > 0) {
                horario.setHorId(lista_horarios.get(i).getHorId());
            }
            horario.setAcDocente(lista_horarios.get(i).getAcDocente());
            horario.setHorActivo(lista_horarios.get(i).getHorActivo());
            horario.setHorDia(lista_horarios.get(i).getHorDia());
            horario.setTurdet(lista_horarios.get(i).getTurdet());
            lHorarios.add(horario);
        }
        return lHorarios;
    }

    private int encontrarIndice(int identificador) {
        for (int i = 0; i < listaHorario.size(); i++) {
            if (listaHorario.get(i).getHorId() == identificador) {
                return i;
            }
        }
        return -1;
    }

    public int getTurno_id() {
        System.out.println("turno--->" + turno_id);
        return turno_id;
    }

    public void setTurno_id(int turno_id) {
        this.turno_id = turno_id;
    }

    public SelectItem[] getComboTurnos() {
        HSTurnoDAO dao = (HSTurnoDAO) ServiceFinder.findBean("HibernateSpringDaoTurno");
        HSSemestreDAO daoSem = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
        List lista = daoSem.seleccionarSemestreVigente();
        List<AcTurno> turnos = dao.seleccionarTurno(((AcSemestre) lista.get(0)).getId());
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

    public void setearTurno() throws Exception {
        listaHorario.clear();
        mostrarTablaHorarios();
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
        List<mbDocente> lista_horario = new ArrayList<mbDocente>();
        mbDocente horario;
        for (int i = 0; i < lista.size(); i++) {
            Object O[] = (Object[]) lista.get(i);
            horario = new mbDocente();
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
            llenarDias(horario, Integer.valueOf(O[0].toString()), doc_id_aux);
            lista_horario.add(horario);
        }
        this.setTablaHorario(lista_horario);
    }

    public List getTablaHorario() {
        return tablaHorario;
    }

    public void setTablaHorario(List tablaHorario) {
        this.tablaHorario = tablaHorario;
    }

    public void llenarDias(mbDocente horario, Integer id_turno, int doc) {
        HSHorarioDAO dao = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoHorario");
        List<AcHorarioDispDocente> horarios = dao.seleccionarHorarioDocente(doc);
        
        for (int i = 0; i < horarios.size(); i++) {
            if ((horarios.get(i).getTurdet().getId()).compareTo(id_turno) == 0) {
                String dias = horarios.get(i).getHorDia();
                String mensaje2 = parametroMensajeDisponibilidadDocente;

                if (dias.equals("016001")) {
                    horario.setLunes_hor(mensaje2);
                    horario.setIndice_id_lun(horarios.get(i).getHorId());
                    horario.setbColorCelda_Lunes("background-color:#6F97CE; border-color:#6F97CE; color:#FFF;text-align:center");
                    
                }
                if (dias.equals("016002")) {
                    horario.setMartes_hor(mensaje2);
                    horario.setIndice_id_mar(horarios.get(i).getHorId());
                    horario.setbColorCelda_Martes("background-color:#6F97CE; border-color:#6F97CE; color:#FFF;text-align:center");
                }
                if (dias.equals("016003")) {
                    horario.setMiercoles_hor(mensaje2);
                    horario.setIndice_id_mie(horarios.get(i).getHorId());
                    horario.setbColorCelda_Miercoles("background-color:#6F97CE; border-color:#6F97CE; color:#FFF;text-align:center");
                }
                if (dias.equals("016004")) {
                    horario.setJueves_hor(mensaje2);
                    horario.setIndice_id_jue(horarios.get(i).getHorId());
                    horario.setbColorCelda_Jueves("background-color:#6F97CE; border-color:#6F97CE; color:#FFF;text-align:center");
                }
                if (dias.equals("016005")) {
                    horario.setViernes_hor(mensaje2);
                    horario.setIndice_id_vie(horarios.get(i).getHorId());
                    horario.setbColorCelda_Viernes("background-color:#6F97CE; border-color:#6F97CE; color:#FFF;text-align:center");
                }
                if (dias.equals("016006")) {
                    horario.setSabado_hor(mensaje2);
                    horario.setIndice_id_sab(horarios.get(i).getHorId());
                    horario.setbColorCelda_Sabado("background-color:#6F97CE; border-color:#6F97CE; color:#FFF;text-align:center");
                }
                if (dias.equals("016007")) {
                    horario.setDomingo_hor(mensaje2);
                    horario.setIndice_id_dom(horarios.get(i).getHorId());
                    horario.setbColorCelda_Domingo("background-color:#6F97CE; border-color:#6F97CE; color:#FFF; text-align:center");
                }
            }
        }
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

    private String suma(String hora, int minutos) {
        int minutos_t = aInteger(hora.substring(0, 2)) * 60 + aInteger(hora.substring(3, 5));
        int ho = (minutos_t + minutos) / 60;
        int mi = (minutos_t + minutos) % 60;
        return (ho + ":" + mi + ":00");
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

    public int getDocente_id() {
        return docente_id;
    }

    public void setDocente_id(int docente_id) {
        this.docente_id = docente_id;
    }
    
     public void ImprimirHistorialDocente(ActionEvent event) throws Exception {
        this.setTituloReporte("Reporte de Historial de Cursos Asignados");
        this.setValorRep("ficha");
        this.setDocenteReporte_id(Double.parseDouble(((UIParameter) event.getComponent().findComponent("p_id")).getValue().toString()));
    }
    
    public void ImprimirReporteCursosAsignado(ActionEvent event) throws Exception {
        this.setTituloReporte("Reporte Cursos Asignados");
        this.setValorRep("docentesCursoAsignado.jasper");
    }
    
    public void ImprimirReporteCursosNoAsignado(ActionEvent event) throws Exception {
        this.setTituloReporte("Reporte Cursos No Asignados");
        this.setValorRep("docentesSinCursoAsignado.jasper");
    }
    
    public void ImprimirReporteDocentesConDisponibilidad(ActionEvent event) throws Exception {
        this.setTituloReporte("Reporte Con Disponibilidad");
        this.setValorRep("docentesConDisponibilidad.jasper");
    }
    
    public void ImprimirReporteDocentesSinDisponibilidad(ActionEvent event) throws Exception {
        this.setTituloReporte("Reporte Sin Disponibilidad");
        this.setValorRep("docentesSinDisponibilidad.jasper");
    }
     
   public void cargarReporteHistorialDocente(OutputStream out, Object data) throws IOException, Exception, EOFException {
        if (this.getDocenteReporte_id()>0) {
                Reporte reporte = new Reporte();
                HashMap parametros = new HashMap();
                parametros.put("doc_id", this.getDocenteReporte_id());
                reporte.cargarReporte(out, data, parametros, "docenteCursoHistorial.jasper");
            
        }
    }
      
    public void cargarReportePdf(OutputStream out, Object data) throws IOException, Exception, EOFException {
                Reporte reporte = new Reporte();
                HashMap parametros = new HashMap();
                reporte.cargarReportePdf(out, data, parametros, "docenteCursoHistorial.jasper");
            
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

}
