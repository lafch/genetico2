package net.uch.academica.managedBeans;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.faces.component.UIParameter;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import net.uch.academica.hibernateSpringDao.*;
import net.uch.academica.managedBeans.beans.BeanCursosMatricular;
import net.uch.academica.managedBeans.beans.BeanDuplicadoHorario;
import net.uch.administrativa.hibernateSpringDao.HSAlumnoTarifaDAO;
import net.uch.administrativa.hibernateSpringDao.HSEstructuraPagoDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.*;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSEspecialidadDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSFacultadDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSSemestreDAO;
import net.uch.tablaSistema.managedBeans.bUsuario;
import net.uch.util.Print;

public class bMatricula {

    public int alumno_id;
    int num_reg_sem = 10;
    int num_reg_plan = 10;
    int b_id;
    String b_codigo;
    String b_paterno;
    String b_materno;
    String b_nombre;
    String b_dni;
    String b_mail;
    String b_tipo;
    String b_id_tipo;
    String b_modalidad;
    String b_id_modalidad;
    String b_estado;
    String b_id_estado;
    String b_esp_act;
    int b_id_esp_act;
    String b_esp_ing;
    int b_id_esp_ing;
    String b_semestre;
    int b_id_semestre;
    String b_activo;
    String b_plan_act;
    int b_id_plan_act;
    String b_plan_ing;
    int b_id_plan_ing;
    String b_fac_act;
    int b_id_fac_act;
    String b_fac_ing;
    int b_id_fac_ing;
    List listaAlumno;
    private String view = "true";
    private String editable = "false";
    private boolean visible = false;
    public SelectItem[] comboCatalogo_tipo;
    public SelectItem[] comboCatalogo_moda;
    public SelectItem[] comboCatalogo_estado;
    public SelectItem[] comboFacultades_ingreso;
    public SelectItem[] comboEspecialidades_ingreso;
    public SelectItem[] comboFacultades_actual;
    public SelectItem[] comboEspecialidades_actual;
    public SelectItem[] comboSemestre;
    public SelectItem[] comboPlan_ingreso;
    public SelectItem[] comboPlan_actual;
    
    private SelectItem[] comboDocumentos;
    private String[] documentos;
    
    private String semestre_actual;
    String semestre_seleccionado;
    int id_semestre_seleccionado;
    String plan_vigente;
    public SelectItem[] comboEspecialidad_buscar;
    public SelectItem[] comboFacultad_buscar;
    int b_id_u;
    int b_id_esp_act_u;
    int b_id_esp_ing_u;
    int b_id_semestre_u;
    String b_activo_u;
    int b_id_plan_act_u;
    int b_id_plan_ing_u;
    private String oncomplete;
    String b_plan_act_u;
    String b_plan_ing_u;
    private List listaCursos;
    private int mat_det_id;
    private String mat_det_codigo;
    private String mat_det_nombre;
    private String mat_det_ciclo;
    private String mat_det_creditos;
    private String mat_det_tipo;
    public int seccion;
    public SelectItem[] comboSecciones;
    public String prueba_faces;
    public String prueba_faces2;
    private int b_id_alumno;
    private String b_codigo_alumno;
    private String b_paterno_alumno;
    private String b_materno_alumno;
    private String b_nombre_alumno;
    private String b_id_tipo_alumno;
    private String b_plan_ing_alumno;
    private String b_plan_act_alumno;
    private int b_semestre_alumno;
    private int sem_id_alumno;
    private String b_estado_alumno;
    private boolean ver = false;
    private String flag_ver = "0";
    private String imagen = "/Imagenes/actions/down.png";
    private List detalle_s;
    private String detalle_codigoCurso;
    private String detalle_nombreCurso;
    private String detalle_creditosCurso;
    private String detalle_codigoSeccion;
    private String b_creditos_totales;
    private SelectItem[] comboFacultad;
    private String b_especialidad_alumno;
//    private List tiposMatricula;
    SelectItem[] comboFacultades;
    private SelectItem[] comboSemestres_Listado;
    private int semestre_Listado;
    private String b_periodo;
    public int semestre_lectivo;
    public List lista8;
    public List lista10;
    public List lista7;
    private String detalle_id;
    private List listaCiclo;
    private boolean script;
    private int matricu_id;
    private String mat_id;
    /**
     * ***************************************************
     */
    private boolean verDeuda;
    private boolean verMatricular;
    private boolean verRectificar;
    private boolean verEliminar;
    private boolean verImprimir1;
    private boolean verImprimir2;
    private boolean verReservar;
    private boolean verAlerta;
//////////////////////////////
    private String d_codigo_alu;
    private String d_nombre_alu;
    private List d_detalle;
    private String d_mensaje;
    private String d_oncomplete;
///////
    private String d_semestre;
    private String d_concepto_pag;
    private String d_fecha_pag;
    private String d_fecha_pro;
///////
    private String b_mat_tipo;
    private String mat_tipo;
////REPORTES////
    private String tituloReporte = "";
    private String valorRep = "";
    private int p_mat_id;
    private String w_tipo_estado;
//////////////////////////////
    private List<BeanDuplicadoHorario> listaDuplicadoHor = new ArrayList<BeanDuplicadoHorario>();

    public List<BeanDuplicadoHorario> getListaDuplicadoHor() {
        return listaDuplicadoHor;
    }

    public void setListaDuplicadoHor(List<BeanDuplicadoHorario> listaDuplicadoHor) {
        this.listaDuplicadoHor = listaDuplicadoHor;
    }

    public bMatricula() throws Exception {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession(false);
        bUsuario usu = (bUsuario) session1.getAttribute("usuario");
        if (usu != null) {
            HSCatalogoDAO dao8 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            lista8 = dao8.seleccionarCatalogo("022");

            HSCatalogoDAO dao10 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            lista10 = dao10.seleccionarCatalogo("012");

            HSFacultadDAO dao0 = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
            List lista0 = dao0.seleccionarFacultad("", "");
            comboFacultad = new SelectItem[lista0.size() + 1];
            comboFacultad[0] = new SelectItem(0, "Todas");
            for (int i = 0; i < comboFacultad.length - 1; i++) {
                comboFacultad[i + 1] = new SelectItem(((AcFacultad) lista0.get(i)).getId(), ((AcFacultad) lista0.get(i)).getFacNombre());
            }
//            HSCatalogoDAO dao1 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
//            tiposMatricula = new ArrayList();
//            tiposMatricula = dao1.seleccionarGrupo("022");

            HSFacultadDAO dao2 = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
            List lista2 = dao2.seleccionarFacultad("", "");
            comboFacultades = new SelectItem[lista2.size() + 1];
            comboFacultades[0] = new SelectItem(0, "[Seleccione]");
            for (int i = 0; i < comboFacultades.length - 1; i++) {
                comboFacultades[i + 1] = new SelectItem(((AcFacultad) lista2.get(i)).getId(), ((AcFacultad) lista2.get(i)).getFacNombre());
            }

            HSCatalogoDAO dao3 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List lista3 = dao3.seleccionarCatalogo("009");
            comboCatalogo_tipo = new SelectItem[lista3.size()];
            for (int i = 0; i < comboCatalogo_tipo.length; i++) {
                comboCatalogo_tipo[i] = new SelectItem(((TbCatalogo) lista3.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista3.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista3.get(i)).getCatDescripcionElemento());
            }
            
            /*Documentos*/
            HSCatalogoDAO daoD = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List listaD = daoD.seleccionarCatalogo("031");
            comboDocumentos = new SelectItem[listaD.size()];
            for (int i = 0; i < comboDocumentos.length; i++) {
                comboDocumentos[i] = new SelectItem(((TbCatalogo) listaD.get(i)).getCatCodigoGrupo() + ((TbCatalogo) listaD.get(i)).getCatCodigoElemento(), ((TbCatalogo) listaD.get(i)).getCatDescripcionElemento());
                //System.out.println(comboDocumentos[i]);
            }
            
            HSCatalogoDAO dao4 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List lista4 = dao4.seleccionarCatalogo("011");
            comboCatalogo_estado = new SelectItem[lista4.size()];
            for (int i = 0; i < comboCatalogo_estado.length; i++) {
                comboCatalogo_estado[i] = new SelectItem(((TbCatalogo) lista4.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista4.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista4.get(i)).getCatDescripcionElemento());
            }

            HSFacultadDAO dao5 = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
            List lista5 = dao5.seleccionarFacultad("", "");
            comboFacultades = new SelectItem[lista5.size() + 1];
            comboFacultades[0] = new SelectItem(0, "[Seleccione]");
            for (int i = 0; i < comboFacultades.length - 1; i++) {
                comboFacultades[i + 1] = new SelectItem(((AcFacultad) lista5.get(i)).getId(), ((AcFacultad) lista5.get(i)).getFacNombre());
            }

            HSCatalogoDAO dao6 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List lista6 = dao6.seleccionarCatalogo("010");
            comboCatalogo_moda = new SelectItem[lista6.size()];
            for (int i = 0; i < comboCatalogo_moda.length; i++) {
                comboCatalogo_moda[i] = new SelectItem(((TbCatalogo) lista6.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista6.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista6.get(i)).getCatDescripcionElemento());
            }

            HSCatalogoDAO dao9 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            listaCiclo = dao9.seleccionarCatalogo("006");


            HSSemestreDAO dao7 = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
            lista7 = dao7.seleccionarSemestreActivo();
            comboSemestres_Listado = new SelectItem[lista7.size()];
            for (int i = 0; i < comboSemestres_Listado.length; i++) {
                comboSemestres_Listado[i] = new SelectItem(((AcSemestre) lista7.get(i)).getId(), ((AcSemestre) lista7.get(i)).getSemNombre());
            }
            b_id_semestre = ((AcSemestre) dao7.seleccionarSemestreActual().get(0)).getId();
        } else {
            throw new Exception();
        }

    }

    public bMatricula(int param) {
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public String getB_codigo() {
        return b_codigo;
    }

    public void setB_codigo(String b_codigo) {
        this.b_codigo = b_codigo;
    }

    public String getB_paterno() {
        return b_paterno;
    }

    public void setB_paterno(String b_paterno) {
        this.b_paterno = b_paterno;
    }

    public String getB_materno() {
        return b_materno;
    }

    public void setB_materno(String b_materno) {
        this.b_materno = b_materno;
    }

    public String getB_nombre() {
        return b_nombre;
    }

    public void setB_nombre(String b_nombre) {
        this.b_nombre = b_nombre;
    }

    public String getB_dni() {
        return b_dni;
    }

    public void setB_dni(String b_dni) {
        this.b_dni = b_dni;
    }

    public String getB_mail() {
        return b_mail;
    }

    public void setB_mail(String b_mail) {
        this.b_mail = b_mail;
    }

    public String getB_tipo() {
        return b_tipo;
    }

    public void setB_tipo(String b_tipo) {
        this.b_tipo = b_tipo;
    }

    public String getB_id_tipo() {
        return b_id_tipo;
    }

    public void setB_id_tipo(String b_id_tipo) {
        this.b_id_tipo = b_id_tipo;
    }

    public String getB_modalidad() {
        return b_modalidad;
    }

    public void setB_modalidad(String b_modalidad) {
        this.b_modalidad = b_modalidad;
    }

    public String getB_id_modalidad() {
        return b_id_modalidad;
    }

    public void setB_id_modalidad(String b_id_modalidad) {
        this.b_id_modalidad = b_id_modalidad;
    }

    public String getB_estado() {
        return b_estado;
    }

    public void setB_estado(String b_estado) {
        this.b_estado = b_estado;
    }

    public String getB_id_estado() {
        return b_id_estado;
    }

    public void setB_id_estado(String b_id_estado) {
        this.b_id_estado = b_id_estado;
    }

    public String getB_esp_act() {
        return b_esp_act;
    }

    public void setB_esp_act(String b_esp_act) {
        this.b_esp_act = b_esp_act;
    }

    public int getB_id_esp_act() {
        return b_id_esp_act;
    }

    public void setB_id_esp_act(int b_id_esp_act) {
        this.b_id_esp_act = b_id_esp_act;
    }

    public String getB_esp_ing() {
        return b_esp_ing;
    }

    public void setB_esp_ing(String b_esp_ing) {
        this.b_esp_ing = b_esp_ing;
    }

    public int getB_id_esp_ing() {
        return b_id_esp_ing;
    }

    public void setB_id_esp_ing(int b_id_esp_ing) {
        this.b_id_esp_ing = b_id_esp_ing;
    }

    public String getB_semestre() {
        return b_semestre;
    }

    public void setB_semestre(String b_semestre) {
        this.b_semestre = b_semestre;
    }

    public int getB_id_semestre() {
        return b_id_semestre;
    }

    public void setB_id_semestre(int b_id_semestre) {
        this.b_id_semestre = b_id_semestre;
    }

    public String getB_activo() {
        return b_activo;
    }

    public void setB_activo(String b_activo) {
        this.b_activo = b_activo;
    }

    public String getB_plan_act() {
        return b_plan_act;
    }

    public void setB_plan_act(String b_plan_act) {
        this.b_plan_act = b_plan_act;
    }

    public int getB_id_plan_act() {
        return b_id_plan_act;
    }

    public void setB_id_plan_act(int b_id_plan_act) {
        this.b_id_plan_act = b_id_plan_act;
    }

    public String getB_plan_ing() {
        return b_plan_ing;
    }

    public void setB_plan_ing(String b_plan_ing) {
        this.b_plan_ing = b_plan_ing;
    }

    public int getB_id_plan_ing() {
        return b_id_plan_ing;
    }

    public void setB_id_plan_ing(int b_id_plan_ing) {
        this.b_id_plan_ing = b_id_plan_ing;
    }

    public String getB_fac_act() {
        return b_fac_act;
    }

    public void setB_fac_act(String b_fac_act) {
        this.b_fac_act = b_fac_act;
    }

    public int getB_id_fac_act() {
        return b_id_fac_act;
    }

    public void setB_id_fac_act(int b_id_fac_act) {
        this.b_id_fac_act = b_id_fac_act;
    }

    public String getB_fac_ing() {
        return b_fac_ing;
    }

    public void setB_fac_ing(String b_fac_ing) {
        this.b_fac_ing = b_fac_ing;
    }

    public int getB_id_fac_ing() {
        return b_id_fac_ing;
    }

    public void setB_id_fac_ing(int b_id_fac_ing) {
        this.b_id_fac_ing = b_id_fac_ing;
    }

    public List getListaAlumno() {
        return listaAlumno;
    }

    public void setListaAlumno(List listaAlumno) {
        this.listaAlumno = listaAlumno;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setB_plan_ing_u(String b_plan_ing_u) {
        this.b_plan_ing_u = b_plan_ing_u;
    }

    public void setB_id_esp_ing_u(int b_id_esp_ing_u) {
        this.b_id_esp_ing_u = b_id_esp_ing_u;
    }

    public String getB_activo_u() {
        return b_activo_u;
    }

    public void setB_activo_u(String b_activo_u) {
        this.b_activo_u = b_activo_u;
    }

    public SelectItem[] getComboCatalogo_tipo() throws Exception {
        return comboCatalogo_tipo;
    }

    public void setComboCatalogo_tipo(SelectItem[] comboCatalogo_tipo) {
        this.comboCatalogo_tipo = comboCatalogo_tipo;
    }

    public SelectItem[] getComboCatalogo_moda() throws Exception {
        return comboCatalogo_moda;
    }

    public void setComboCatalogo_moda(SelectItem[] comboCatalogo_moda) {
        this.comboCatalogo_moda = comboCatalogo_moda;
    }

    public SelectItem[] getComboCatalogo_estado() throws Exception {
        return comboCatalogo_estado;
    }

    public void setComboCatalogo_estado(SelectItem[] comboCatalogo_estado) {
        this.comboCatalogo_estado = comboCatalogo_estado;
    }

    public SelectItem[] getComboSemestre() throws Exception {
        return comboSemestre;
    }

    public void setComboSemestre(SelectItem[] comboSemestre) {
        this.comboSemestre = comboSemestre;
    }

    public String getSemestre_actual() throws Exception {
        HSSemestreDAO dao = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
        List lista = dao.seleccionarSemestreActual();
        //List lista = dao.listarSemestreId(b_semestre_alumno);
        semestre_actual = ((AcSemestre) lista.get(0)).getSemNombre();
        return semestre_actual;
    }

    public void setSemestre_vigente(String semestre_actual) {
        this.setSemestre_actual(semestre_actual);
    }

    public SelectItem[] getComboFacultades_ingreso() throws Exception {
        return comboFacultades;
    }

    public void setComboFacultades_ingreso(SelectItem[] comboFacultades) {
        this.comboFacultades_ingreso = comboFacultades;
    }

    public void setComboEspecialidades_ingreso(SelectItem[] comboEspecialidades_ingreso) {
        this.comboEspecialidades_ingreso = comboEspecialidades_ingreso;
    }

    public void setComboPlan_ingreso(SelectItem[] comboPlan_ingreso) {
        this.comboPlan_ingreso = comboPlan_ingreso;
    }

    public SelectItem[] getComboFacultades_actual() throws Exception {
        return comboFacultades;
    }

    public void setComboFacultades_actual(SelectItem[] comboFacultades) {
        this.comboFacultades_actual = comboFacultades;
    }

    public void Nuevo() {
    }

    public SelectItem[] getComboFacultad_buscar() throws Exception {
        return comboFacultad;
    }

    public void setComboFacultad_buscar(SelectItem[] comboFacultad) {
        this.comboFacultad_buscar = comboFacultad;
    }

    public SelectItem[] getComboEspecialidad_buscar() throws Exception {
        cambiarEspecialidad_buscar(this.getB_id_fac_act());
        return comboEspecialidad_buscar;
    }

    public void setComboEspecialidad_buscar(SelectItem[] comboEspecialidad_buscar) {
        this.comboEspecialidad_buscar = comboEspecialidad_buscar;
    }

    public void cambiarEspecialidad_buscar(int id) throws Exception {
        HSEspecialidadDAO dao = (HSEspecialidadDAO) ServiceFinder.findBean("SpringHibernateDaoEspecialidad");
        List lista = dao.seleccionarEspecialidad(id);
        comboEspecialidad_buscar = new SelectItem[lista.size() + 1];
        comboEspecialidad_buscar[0] = new SelectItem(0, "Todas");
        for (int i = 0; i < comboEspecialidad_buscar.length - 1; i++) {
            comboEspecialidad_buscar[i + 1] = new SelectItem(((AcEspecialidad) lista.get(i)).getId(), ((AcEspecialidad) lista.get(i)).getEspNombre());
        }
        this.setComboEspecialidades_ingreso(comboEspecialidad_buscar);
    }

    public void Seleccionar() throws Exception {
        listaAlumno = new ArrayList();
        HSAlumnoDAO dao = (HSAlumnoDAO) ServiceFinder.findBean("SpringHibernateDaoAlumno");
        HSAlumnoTarifaDAO tarifa = (HSAlumnoTarifaDAO) ServiceFinder.findBean("SpringHibernateDaoAlumnoTarifa");
        List lista = dao.seleccionarAlumno(this.getB_codigo(), this.getB_paterno(), this.getB_materno(), this.getB_nombre(), this.getB_id_fac_act(), this.getB_id_esp_act());
        AcAlumnoEstadoDAO daoEstado = (AcAlumnoEstadoDAO) ServiceFinder.findBean("AcAlumnoEstadoDAOHS");

       
            
        bMatricula bA;

        Calendar fecActual = Calendar.getInstance();
        fecActual.setTime(new Date());

        for (int i = 0; i < lista.size(); i++) {
            bA = new bMatricula(0);
            /*
             * AcAlumnoEstado estado=new AcAlumnoEstado();
             * estado.setAcSemestre(new AcSemestre(this.semestre_Listado));
             * estado.setAcAlumno(new
             * AcAlumno(((AcAlumno)lista.get(i)).getId()));
            estado.setAluestEstado("075001");
             */
            //  AcAlumnoEstado estado2=(AcAlumnoEstado) daoEstado.findAlumnoEstado(estado);
            //List<AcAlumnoEstado> listaEstado=daoEstado.findByProperty("acAlumno.id", ((AcAlumno)lista.get(i)).getId());
            //System.out.println("cantidad de lista -> "+listaEstado.size());
            bA.setW_tipo_estado("0");
            List<AcEstadoSemestre> listaEst = daoEstado.seleccionarEstado(((AcAlumno) lista.get(i)).getId(), this.semestre_Listado);
            AcAlumnoEstado acAlumnoEstado = new AcAlumnoEstado();
            if (listaEst.size() > 0) 
            {
                acAlumnoEstado = listaEst.get(0).getAcAlumnoEstado();
                if (acAlumnoEstado.getAluestEstado().equals("075001")) {
                    bA.setW_tipo_estado("1");
                } else if (acAlumnoEstado.getAluestEstado().equals("075002")) {
                    bA.setW_tipo_estado("2");
                } else if (acAlumnoEstado.getAluestEstado().equals("075003")) {
                    bA.setW_tipo_estado("3");
                }
            } else 
            {
                bA.setW_tipo_estado("0");
            }


            bA.setB_id(((AcAlumno) lista.get(i)).getId());
            bA.setB_codigo(((AcAlumno) lista.get(i)).getAluCodigo());
            bA.setB_paterno(((AcAlumno) lista.get(i)).getAluAppaterno());
            bA.setB_materno(((AcAlumno) lista.get(i)).getAluApmaterno());
            bA.setB_nombre(((AcAlumno) lista.get(i)).getAluNombres());
            bA.setB_dni(((AcAlumno) lista.get(i)).getAluDni());
            bA.setB_mail(((AcAlumno) lista.get(i)).getAluMail());
            bA.setB_id_tipo(((AcAlumno) lista.get(i)).getAluTipo());
            bA.setB_id_modalidad(((AcAlumno) lista.get(i)).getAluModalidad());
            sem_id_alumno=((AcAlumno) lista.get(i)).getSemId();
            bA.setB_id_estado(((AcAlumno) lista.get(i)).getAluEstado());
            bA.setB_id_esp_act(((AcAlumno) lista.get(i)).getEsp().getId());
            bA.setB_esp_act(((AcAlumno) lista.get(i)).getEsp().getEspNombre());
            bA.setB_id_esp_ing(((AcAlumno) lista.get(i)).getEspIdIngreso());
            bA.setB_activo(((AcAlumno) lista.get(i)).getAluActivo());
            bA.setB_id_plan_ing(((AcAlumno) lista.get(i)).getPlanIdIngreso());
            bA.setB_id_plan_act(((AcAlumno) lista.get(i)).getPlanIdActual());
            
             HSAlumnoDatoDAO daoD = (HSAlumnoDatoDAO) ServiceFinder.findBean("HibernateSpringDaoTargetAlumnoDato");
            List lista_documentos = daoD.seleccionarDocumentos(bA.getB_id());
            if (lista_documentos.size() != 0) {
                documentos = new String[lista_documentos.size()];
            }
            for (int j = 0; j < lista_documentos.size(); j++) {
                documentos[j] = ((AcAlumnoDocumento) lista_documentos.get(j)).getAludocDocumento();
            }

            HSMatriculaDAO matricula = (HSMatriculaDAO) ServiceFinder.findBean("SpringHibernateDaoMatricula");
            List listRegular = matricula.seleccionarMatricularRegular(((AcAlumno) lista.get(i)).getId(), this.getSemestre_Listado());
            List listReserva = matricula.seleccionarMatriculaReserva(((AcAlumno) lista.get(i)).getId(), this.getSemestre_Listado());
            List listPrematricula = matricula.seleccionarPrematricula(((AcAlumno) lista.get(i)).getId(), this.getSemestre_Listado());

            if (listRegular.size() > 0) {
                for (int q = 0; q < lista8.size(); q++) {
                    if ((((TbCatalogo) lista8.get(q)).getCatCodigoGrupo() + ((TbCatalogo) lista8.get(q)).getCatCodigoElemento()).equals(((AcMatricula) listRegular.get(0)).getMatTipo())) {
                        bA.setB_tipo(((TbCatalogo) lista8.get(q)).getCatDescripcionElemento());
                    }
                }
                bA.setMatricu_id(((AcMatricula) listRegular.get(0)).getId());
                bA.setB_mat_tipo(((AcMatricula) listRegular.get(0)).getMatTipo());

                bA.setVerDeuda(false);
                bA.setVerMatricular(false);
                bA.setVerRectificar(true);
                bA.setVerEliminar(true);
                bA.setVerImprimir1(true);
                bA.setVerImprimir2(true);
                bA.setVerReservar(true);
                bA.setVerAlerta(false);
            } else if (listReserva.size() > 0) {
                for (int q = 0; q < lista8.size(); q++) {
                    if ((((TbCatalogo) lista8.get(q)).getCatCodigoGrupo() + ((TbCatalogo) lista8.get(q)).getCatCodigoElemento()).equals(((AcMatricula) listReserva.get(0)).getMatTipo())) {
                        bA.setB_tipo(((TbCatalogo) lista8.get(q)).getCatDescripcionElemento());
                    }
                }
                bA.setMatricu_id(((AcMatricula) listReserva.get(0)).getId());
                bA.setB_mat_tipo(((AcMatricula) listReserva.get(0)).getMatTipo());

                bA.setVerDeuda(false);
                bA.setVerMatricular(false);
                bA.setVerRectificar(false);
                bA.setVerEliminar(true);
                bA.setVerImprimir1(false);
                bA.setVerImprimir2(false);
                bA.setVerReservar(false);
                bA.setVerAlerta(false);
            } else if (listPrematricula.size() > 0) {
                for (int q = 0; q < lista8.size(); q++) {
                    if ((((TbCatalogo) lista8.get(q)).getCatCodigoGrupo() + ((TbCatalogo) lista8.get(q)).getCatCodigoElemento()).equals(((AcMatricula) listPrematricula.get(0)).getMatTipo())) {
                        bA.setB_tipo(((TbCatalogo) lista8.get(q)).getCatDescripcionElemento());
                    }
                }
                bA.setMatricu_id(((AcMatricula) listPrematricula.get(0)).getId());
                bA.setB_mat_tipo(((AcMatricula) listPrematricula.get(0)).getMatTipo());

                bA.setVerDeuda(false);
                bA.setVerMatricular(true);
                bA.setVerRectificar(false);
                bA.setVerEliminar(false);
                bA.setVerImprimir1(false);
                bA.setVerImprimir2(false);
                bA.setVerReservar(false);
                bA.setVerAlerta(false);
            } else {
                bA.setB_tipo("No registra Matricula");
                bA.setMatricu_id(0);
                bA.setB_mat_tipo("0");

                bA.setVerDeuda(false);
                bA.setVerMatricular(true);
                bA.setVerRectificar(false);
                bA.setVerEliminar(false);
                bA.setVerImprimir1(false);
                bA.setVerImprimir2(false);
                bA.setVerReservar(false);
                bA.setVerAlerta(false);
            }

            if ((listRegular.size() > 0 && listReserva.size() > 0)
                    || (listReserva.size() > 0 && listPrematricula.size() > 0)
                    || (listRegular.size() > 0 && listPrematricula.size() > 0)) {
                bA.setVerAlerta(true);
            }

            for (int w = 0; w < lista7.size(); w++) {
                if (((AcSemestre) lista7.get(w)).getId().equals(this.getSemestre_Listado())) {
                    bA.setB_periodo(((AcSemestre) lista7.get(w)).getSemCodigo());
                    bA.setB_id_semestre(((AcSemestre) lista7.get(w)).getId());
                    this.setId_semestre_seleccionado(((AcSemestre) lista7.get(w)).getId());

                }
            }

            List lista3 = tarifa.seleccionarAlumnoTarifa(((AcAlumno) lista.get(i)).getId());
            if (lista3.size() > 0) {
                for (int j = 0; j < lista3.size(); j++) {
                    Calendar fecPro = Calendar.getInstance();
                    fecPro.setTime(((AdAlumnoTarifa) lista3.get(j)).getAlutarFechaProrroga());
                    if (!(((AdAlumnoTarifa) lista3.get(j)).getAlutarEstado().equals("2"))
                            && fecActual.getTimeInMillis() > fecPro.getTimeInMillis()) {
                        bA.setVerDeuda(true);
                        bA.setVerMatricular(false);
                        bA.setVerRectificar(false);
                        bA.setVerReservar(false);
                        break;
                    }
                }
            }

            detalle_s = new ArrayList();
            bMatricula matri = new bMatricula(0);
            detalle_s.add(matri);
            bA.setDetalle_s(detalle_s);
            listaAlumno.add(bA);
        }
        this.setListaAlumno(listaAlumno);
    }

    public void EditarFila(ActionEvent event) {
        mat_id = ((UIParameter) event.getComponent().findComponent("p_id_matricula")).getValue().toString();
        mat_tipo = ((UIParameter) event.getComponent().findComponent("p_mat_tipo")).getValue().toString();

        b_id_alumno = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_id")).getValue().toString());
        b_id_semestre = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_id_semestre")).getValue().toString());
        b_codigo_alumno = ((UIParameter) event.getComponent().findComponent("p_codigo")).getValue().toString();
        b_paterno_alumno = ((UIParameter) event.getComponent().findComponent("p_paterno")).getValue().toString();
        b_materno_alumno = ((UIParameter) event.getComponent().findComponent("p_materno")).getValue().toString();
        b_nombre_alumno = ((UIParameter) event.getComponent().findComponent("p_nombre")).getValue().toString();
        b_especialidad_alumno = (((UIParameter) event.getComponent().findComponent("p_id_esp_act")).getValue().toString());
        b_semestre_alumno = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_id_semestre")).getValue().toString());
        if (((UIParameter) event.getComponent().findComponent("p_plan_ing")).getValue() != null) {
            b_plan_ing_alumno = ((UIParameter) event.getComponent().findComponent("p_plan_ing")).getValue().toString();
        }
        if (((UIParameter) event.getComponent().findComponent("p_plan_act")).getValue() != null) {
            b_plan_act_alumno = ((UIParameter) event.getComponent().findComponent("p_plan_act")).getValue().toString();
        }
    }

    public void imagen(OutputStream out, Object data) throws IOException, Exception {
        HSAlumnoDAO dao = (HSAlumnoDAO) ServiceFinder.findBean("SpringHibernateDaoAlumno");

        AcAlumno alu = dao.seleccionarAlumno((Integer) data);
        if (alu != null) {
            java.sql.Blob alu_foto = alu.getAluFoto();
            BufferedImage bufferedImage = ImageIO.read(alu_foto.getBinaryStream());
            ImageIO.write(bufferedImage, "jpeg", out);
        }
    }

    public int getAlumno_id() {
        return alumno_id;
    }

    public void setAlumno_id(int alumno_id) {
        this.alumno_id = alumno_id;
    }

    public List<bMatricula> listarCursosMatricular(int aluId, int semId) {
        List<bMatricula> listaCur = new ArrayList<bMatricula>();
        listaCur.clear();
        HSMatriculaDAO dao = (HSMatriculaDAO) ServiceFinder.findBean("SpringHibernateDaoMatricula");
        // List matriculas = dao.seleccionarPrematricula(b_id_alumno, b_semestre_alumno);

        List<BeanCursosMatricular> lista = dao.listarCursosAmatricular(b_id_alumno, this.semestre_Listado);
        List lista_cursos = new ArrayList();
        bMatricula matri;
        for (int i = 0; i < lista.size(); i++) {
            matri = new bMatricula(0);
            matri.setMat_det_id(lista.get(i).getPlancurId());
            matri.setMat_det_codigo(lista.get(i).getPlancurCodigo());
            matri.setMat_det_nombre(lista.get(i).getCurNombre());
            matri.setMat_det_ciclo(lista.get(i).getCiclo());
            matri.setMat_det_creditos(lista.get(i).getPlancurCredito().toString());
            matri.setMat_det_tipo(lista.get(i).getPlanTipo());
            matri.setComboSecciones(llenarComboSecciones(lista.get(i).getPlancurId().intValue()));
            matri.setSeccion(lista.get(i).getSecId().intValue());
            lista_cursos.add(matri);

        }
        if (lista_cursos.size() > 0) {
            listaCur = lista_cursos;
        }
        return listaCur;
    }

    public void cursosAbiertos() throws Exception {
        this.setOncomplete("");
        HSAlumnoDAO daoA = (HSAlumnoDAO) ServiceFinder.findBean("SpringHibernateDaoAlumno");
        if (daoA.buscarAlumnoFicha(b_id_alumno) > 0) {

            /*
             * HSMatriculaDAO dao = (HSMatriculaDAO)
             * ServiceFinder.findBean("SpringHibernateDaoMatricula"); // List
             * matriculas = dao.seleccionarPrematricula(b_id_alumno,
             * b_semestre_alumno);
             *
             * List<BeanCursosMatricular>
             * lista=dao.listarCursosAmatricular(b_id_alumno,
             * this.semestre_Listado); List lista_cursos = new ArrayList();
             * bMatricula matri; for(int i=0; i<lista.size(); i++){ matri = new
             * bMatricula(0); matri.setMat_det_id(lista.get(i).getPlancurId());
             * matri.setMat_det_codigo(lista.get(i).getPlancurCodigo());
             * matri.setMat_det_nombre(lista.get(i).getCurNombre());
             * matri.setMat_det_ciclo(lista.get(i).getCiclo());
             * matri.setMat_det_creditos(lista.get(i).getPlancurCredito().toString());
             * matri.setMat_det_tipo(lista.get(i).getPlanTipo());
             * matri.setComboSecciones(llenarComboSecciones(lista.get(i).getPlancurId().intValue()));
             * matri.setSeccion(lista.get(i).getSecId().intValue());
             * lista_cursos.add(matri);
             *
             * }
             * /* List abiertos = cursosAbiertos(b_id_alumno,
             * b_especialidad_alumno);
             *
             * List lista_cursos = new ArrayList(); bMatricula matricula;
             *
             * if (matriculas.size() != 0) { List matriculados =
             * Collections.synchronizedList(new
             * LinkedList<AcMatriculaCurso>(((AcMatricula)
             * matriculas.get(0)).getAcMatriculaCursos()));
             *
             * if (abiertos.size() > matriculados.size()) { for (int j = 0; j <
             * abiertos.size(); j++) { matricula = new bMatricula(0);
             * matricula.setMat_det_id(((AcPlanCurso) abiertos.get(j)).getId());
             * matricula.setMat_det_codigo(((AcPlanCurso)
             * abiertos.get(j)).getCur().getCurCodigo());
             * matricula.setMat_det_nombre(((AcPlanCurso)
             * abiertos.get(j)).getCur().getCurNombre()); for (int k = 0; k <
             * listaCiclo.size(); k++) { if ((((TbCatalogo)
             * listaCiclo.get(k)).getCatCodigoGrupo() + ((TbCatalogo)
             * listaCiclo.get(k)).getCatCodigoElemento()).equals(((AcPlanCurso)
             * abiertos.get(j)).getPlancurCiclo())) {
             * matricula.setMat_det_ciclo(((TbCatalogo)
             * listaCiclo.get(k)).getCatDescripcionElemento()); } }
             * matricula.setMat_det_creditos(((AcPlanCurso)
             * abiertos.get(j)).getPlancurCredito()); for (int k = 0; k <
             * lista10.size(); k++) { if ((((TbCatalogo)
             * lista10.get(k)).getCatCodigoGrupo() + ((TbCatalogo)
             * lista10.get(k)).getCatCodigoElemento()).equals(((AcPlanCurso)
             * abiertos.get(j)).getPlancurTipo())) {
             * matricula.setMat_det_tipo(((TbCatalogo)
             * lista10.get(k)).getCatDescripcionElemento()); } }
             * matricula.setComboSecciones(llenarComboSecciones(((AcPlanCurso)
             * abiertos.get(j)).getId())); matricula.setSeccion(0);
             * lista_cursos.add(matricula); for (int i = 0; i <
             * matriculados.size(); i++) { if (("" + ((AcPlanCurso)
             * abiertos.get(j)).getId()).equals("" + ((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getId()))
             * { matricula = new bMatricula(0);
             * matricula.setMat_det_id(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getId());
             * matricula.setMat_det_codigo(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getCur().getCurCodigo());
             * matricula.setMat_det_nombre(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getCur().getCurNombre());
             * for (int k = 0; k < listaCiclo.size(); k++) { if ((((TbCatalogo)
             * listaCiclo.get(k)).getCatCodigoGrupo() + ((TbCatalogo)
             * listaCiclo.get(k)).getCatCodigoElemento()).equals(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getPlancurCiclo()))
             * { matricula.setMat_det_ciclo(((TbCatalogo)
             * listaCiclo.get(k)).getCatDescripcionElemento()); } }
             * matricula.setMat_det_creditos(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getPlancurCredito());
             * for (int k = 0; k < lista10.size(); k++) { if ((((TbCatalogo)
             * lista10.get(k)).getCatCodigoGrupo() + ((TbCatalogo)
             * lista10.get(k)).getCatCodigoElemento()).equals(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getPlancurTipo()))
             * { matricula.setMat_det_tipo(((TbCatalogo)
             * lista10.get(k)).getCatDescripcionElemento()); } }
             * matricula.setComboSecciones(llenarComboSecciones(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getId()));
             * matricula.setSeccion(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getId()); lista_cursos.set(j,
             * matricula); } } } } else { for (int i = 0; i <
             * matriculados.size(); i++) { matricula = new bMatricula(0);
             * matricula.setMat_det_id(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getId());
             * matricula.setMat_det_codigo(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getCur().getCurCodigo());
             * matricula.setMat_det_nombre(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getCur().getCurNombre());
             * for (int k = 0; k < listaCiclo.size(); k++) { if ((((TbCatalogo)
             * listaCiclo.get(k)).getCatCodigoGrupo() + ((TbCatalogo)
             * listaCiclo.get(k)).getCatCodigoElemento()).equals(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getPlancurCiclo()))
             * { matricula.setMat_det_ciclo(((TbCatalogo)
             * listaCiclo.get(k)).getCatDescripcionElemento()); } }
             * matricula.setMat_det_creditos(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getPlancurCredito());
             * for (int k = 0; k < lista10.size(); k++) { if ((((TbCatalogo)
             * lista10.get(k)).getCatCodigoGrupo() + ((TbCatalogo)
             * lista10.get(k)).getCatCodigoElemento()).equals(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getPlancurTipo()))
             * { matricula.setMat_det_tipo(((TbCatalogo)
             * lista10.get(k)).getCatDescripcionElemento()); } }
             * matricula.setComboSecciones(llenarComboSecciones(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getId()));
             * matricula.setSeccion(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getId());
             * lista_cursos.add(matricula); } } } else { for (int i = 0; i <
             * abiertos.size(); i++) { matricula = new bMatricula(0);
             * matricula.setMat_det_id(((AcPlanCurso) abiertos.get(i)).getId());
             * matricula.setMat_det_codigo(((AcPlanCurso)
             * abiertos.get(i)).getCur().getCurCodigo());
             * matricula.setMat_det_nombre(((AcPlanCurso)
             * abiertos.get(i)).getCur().getCurNombre()); for (int k = 0; k <
             * listaCiclo.size(); k++) { if ((((TbCatalogo)
             * listaCiclo.get(k)).getCatCodigoGrupo() + ((TbCatalogo)
             * listaCiclo.get(k)).getCatCodigoElemento()).equals(((AcPlanCurso)
             * abiertos.get(i)).getPlancurCiclo())) {
             * matricula.setMat_det_ciclo(((TbCatalogo)
             * listaCiclo.get(k)).getCatDescripcionElemento()); } }
             * matricula.setMat_det_creditos(((AcPlanCurso)
             * abiertos.get(i)).getPlancurCredito()); for (int k = 0; k <
             * lista10.size(); k++) { if ((((TbCatalogo)
             * lista10.get(k)).getCatCodigoGrupo() + ((TbCatalogo)
             * lista10.get(k)).getCatCodigoElemento()).equals(((AcPlanCurso)
             * abiertos.get(i)).getPlancurTipo())) {
             * matricula.setMat_det_tipo(((TbCatalogo)
             * lista10.get(k)).getCatDescripcionElemento()); } }
             * matricula.setComboSecciones(llenarComboSecciones(((AcPlanCurso)
             * abiertos.get(i)).getId())); lista_cursos.add(matricula); }
             *
             * }
             */
            this.setListaCursos(new ArrayList());
            this.getListaCursos().clear();
            this.setListaCursos(listarCursosMatricular(b_id_alumno, this.semestre_Listado));
            this.setOncomplete("javascript:Richfaces.showModalPanel('mp',{width:100, top:50})");
        } else {
            this.setOncomplete("javascript:alert('El Alumno no ah llenado su Ficha')");
        }
    }

    public void rectificarMatricula() throws Exception {
        HSMatriculaDAO dao = (HSMatriculaDAO) ServiceFinder.findBean("SpringHibernateDaoMatricula");
        List matriculas = dao.seleccionarMatricularRegular(b_id_alumno, b_semestre_alumno);
        //List lista_cursos = new ArrayList();
        this.setListaCursos(listarCursosMatricular(b_id_alumno, this.semestre_Listado));
        if (matriculas.size() != 0) {
            /*
             * List matriculados = Collections.synchronizedList(new
             * LinkedList<AcMatriculaCurso>(((AcMatricula)
             * matriculas.get(0)).getAcMatriculaCursos())); List abiertos =
             * cursosAbiertos(b_id_alumno, b_especialidad_alumno); bMatricula
             * matricula;
             *
             * if (abiertos.size() > matriculados.size()) { for (int j = 0; j <
             * abiertos.size(); j++) { matricula = new bMatricula(0);
             * matricula.setMat_det_id(((AcPlanCurso) abiertos.get(j)).getId());
             * matricula.setMat_det_codigo(((AcPlanCurso)
             * abiertos.get(j)).getCur().getCurCodigo());
             * matricula.setMat_det_nombre(((AcPlanCurso)
             * abiertos.get(j)).getCur().getCurNombre()); for (int k = 0; k <
             * listaCiclo.size(); k++) { if ((((TbCatalogo)
             * listaCiclo.get(k)).getCatCodigoGrupo() + ((TbCatalogo)
             * listaCiclo.get(k)).getCatCodigoElemento()).equals(((AcPlanCurso)
             * abiertos.get(j)).getPlancurCiclo())) {
             * matricula.setMat_det_ciclo(((TbCatalogo)
             * listaCiclo.get(k)).getCatDescripcionElemento()); } }
             * matricula.setMat_det_creditos(((AcPlanCurso)
             * abiertos.get(j)).getPlancurCredito()); for (int k = 0; k <
             * lista10.size(); k++) { if ((((TbCatalogo)
             * lista10.get(k)).getCatCodigoGrupo() + ((TbCatalogo)
             * lista10.get(k)).getCatCodigoElemento()).equals(((AcPlanCurso)
             * abiertos.get(j)).getPlancurTipo())) {
             * matricula.setMat_det_tipo(((TbCatalogo)
             * lista10.get(k)).getCatDescripcionElemento()); } }
             * matricula.setComboSecciones(llenarComboSecciones(((AcPlanCurso)
             * abiertos.get(j)).getId())); matricula.setSeccion(0);
             * lista_cursos.add(matricula); for (int i = 0; i <
             * matriculados.size(); i++) { if (("" + ((AcPlanCurso)
             * abiertos.get(j)).getId()).equals("" + ((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getId()))
             * { matricula = new bMatricula(0);
             * matricula.setMat_det_id(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getId());
             * matricula.setMat_det_codigo(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getCur().getCurCodigo());
             * matricula.setMat_det_nombre(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getCur().getCurNombre());
             * for (int k = 0; k < listaCiclo.size(); k++) { if ((((TbCatalogo)
             * listaCiclo.get(k)).getCatCodigoGrupo() + ((TbCatalogo)
             * listaCiclo.get(k)).getCatCodigoElemento()).equals(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getPlancurCiclo()))
             * { matricula.setMat_det_ciclo(((TbCatalogo)
             * listaCiclo.get(k)).getCatDescripcionElemento()); } }
             * matricula.setMat_det_creditos(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getPlancurCredito());
             * for (int k = 0; k < lista10.size(); k++) { if ((((TbCatalogo)
             * lista10.get(k)).getCatCodigoGrupo() + ((TbCatalogo)
             * lista10.get(k)).getCatCodigoElemento()).equals(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getPlancurTipo()))
             * { matricula.setMat_det_tipo(((TbCatalogo)
             * lista10.get(k)).getCatDescripcionElemento()); } }
             * matricula.setComboSecciones(llenarComboSecciones(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getId()));
             * matricula.setSeccion(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getId()); lista_cursos.set(j,
             * matricula); } } } } else { for (int i = 0; i <
             * matriculados.size(); i++) { matricula = new bMatricula(0);
             * matricula.setMat_det_id(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getId());
             * matricula.setMat_det_codigo(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getCur().getCurCodigo());
             * matricula.setMat_det_nombre(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getCur().getCurNombre());
             * for (int k = 0; k < listaCiclo.size(); k++) { if ((((TbCatalogo)
             * listaCiclo.get(k)).getCatCodigoGrupo() + ((TbCatalogo)
             * listaCiclo.get(k)).getCatCodigoElemento()).equals(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getPlancurCiclo()))
             * { matricula.setMat_det_ciclo(((TbCatalogo)
             * listaCiclo.get(k)).getCatDescripcionElemento()); } }
             * matricula.setMat_det_creditos(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getPlancurCredito());
             * for (int k = 0; k < lista10.size(); k++) { if ((((TbCatalogo)
             * lista10.get(k)).getCatCodigoGrupo() + ((TbCatalogo)
             * lista10.get(k)).getCatCodigoElemento()).equals(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getPlancurTipo()))
             * { matricula.setMat_det_tipo(((TbCatalogo)
             * lista10.get(k)).getCatDescripcionElemento()); } }
             * matricula.setComboSecciones(llenarComboSecciones(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getCurape().getPlancur().getId()));
             * matricula.setSeccion(((AcMatriculaCurso)
             * matriculados.get(i)).getSec().getId());
             * lista_cursos.add(matricula); }
            }
             */

            this.setOncomplete("javascript:Richfaces.showModalPanel('mp1',{width:100, top:50})");
        } else {
            this.setOncomplete("alert('El alumno no presenta matricula')");
        }

    }

    //public List<AcPlanCurso> cursosAbiertos(int alu_id, String especialidad) throws Exception {
        /*
     * HSPreRequisitosDAO dao = (HSPreRequisitosDAO)
     * ServiceFinder.findBean("SpringHibernateDaoPreRequisitos"); HSMatriculaDAO
     * dao2 = (HSMatriculaDAO)
     * ServiceFinder.findBean("SpringHibernateDaoMatricula"); List abiertos;
     * List libres; List aprobados = dao2.seleccionarcursosAprobados(alu_id);
     */
    /**
     * ****************************************
     */
    /*
     * List<Integer> ap = (List<Integer>) aprobados.get(0); List<Integer> da =
     * (List<Integer>) aprobados.get(2);
     *
     * System.out.println("IDS APROBADOS"); System.out.println("=============");
     * for (int i = 0; i < ap.size(); i++) { System.out.println("ID_" + (i + 1)
     * + " = " + ap.get(i).toString()); } System.out.println(" ");
     * System.out.println("IDS DESAPROBADOS");
     * System.out.println("================"); for (int i = 0; i < da.size();
     * i++) { System.out.println("ID_" + (i + 1) + " = " +
     * da.get(i).toString()); } System.out.println(" ");
     */
    /**
     * ****************************************
     */
    /*
     * List<Integer> pre_libres = new ArrayList<Integer>(); if (((List<Integer>)
     * (aprobados.get(0))).get(0) == -1) { abiertos =
     * filtro_aperturados(dao2.seleccionarcursosparaIngresantes(alu_id)); } else
     * { pre_libres.addAll((List<Integer>) (aprobados.get(0)));
     * pre_libres.addAll((List<Integer>) (aprobados.get(2)));
     *
     * abiertos = filtro_aperturados(dao.retonarCursosAbiertos((List<Integer>)
     * (aprobados.get(0)), (List<Integer>) aprobados.get(2)));
     */
    /**
     * ****************************************
     */
    /*
     * System.out.println("CURSOS ABIERTOS DESPUES DEL FILTRO");
     * System.out.println("=================================="); if
     * (abiertos.size() > 0) { for (int i = 0; i < abiertos.size(); i++) {
     * System.out.println("PlancurID_" + (i + 1) + " = " + ((AcPlanCurso)
     * abiertos.get(i)).getId()); System.out.println("Curso_" + (i + 1) + " = "
     * + ((AcPlanCurso) abiertos.get(i)).getCur().getCurNombre()); } } else {
     * System.out.println("No tiene cursos abiertos despues del filtro"); }
     * System.out.println(" ");
     */
    /**
     * ****************************************
     */
    /*
     * libres = filtro_aperturados(dao.retonarCursosLibres(pre_libres,
     * Integer.parseInt(especialidad)));
     *
     * /******************************************
     */
    /*
     * System.out.println("CURSOS LIBRES DESPUES DEL FILTRO");
     * System.out.println("=================================="); if
     * (libres.size() > 0) { for (int i = 0; i < libres.size(); i++) {
     * System.out.println("PlancurID_" + (i + 1) + " = " + ((AcPlanCurso)
     * libres.get(i)).getId()); System.out.println("Curso_" + (i + 1) + " = " +
     * ((AcPlanCurso) libres.get(i)).getCur().getCurNombre()); } } else {
     * System.out.println("No tiene cursos libres despues del filtro"); }
     * System.out.println(" ");
     */
    /**
     * ****************************************
     */
    /*
     * abiertos.addAll(libres);
     */
    /**
     * ****************************************
     */
    /*
     * System.out.println("CURSOS DESAPROBADOS ANTES DEL FILTRO");
     * System.out.println("===================================="); List jal =
     * (List<AcPlanCurso>) aprobados.get(1); if (jal.size() > 0) { for (int i =
     * 0; i < jal.size(); i++) { System.out.println("PlancurID_" + (i + 1) + " =
     * " + ((AcPlanCurso) jal.get(i)).getId()); System.out.println("Curso_" + (i
     * + 1) + " = " + ((AcPlanCurso) jal.get(i)).getCur().getCurNombre()); } }
     * else { System.out.println("No tiene cursos desaprobados antes del
     * filtro"); } System.out.println(" ");
     */
    /**
     * ****************************************
     */
    /*
     * List lis = filtro_aperturados((List<AcPlanCurso>) aprobados.get(1));
     */
    /**
     * ****************************************
     */
    /*
     * System.out.println("CURSOS DESAPROBADOS DESPUES DEL FILTRO");
     * System.out.println("======================================"); if
     * (lis.size() > 0) { for (int i = 0; i < lis.size(); i++) {
     * System.out.println("PlancurID_" + (i + 1) + " = " + ((AcPlanCurso)
     * lis.get(i)).getId()); System.out.println("Curso_" + (i + 1) + " = " +
     * ((AcPlanCurso) lis.get(i)).getCur().getCurNombre()); } } else {
     * System.out.println("No tiene cursos desaprobados despues del filtro"); }
     * System.out.println(" ");
     */
    /**
     * ****************************************
     */
    /*
     * if (lis.size() > 0) { abiertos.addAll(lis);
            }
     */
    //}
    /**
     * ****************************************
     */
    /*
     * System.out.println("CURSOS QUE PUEDE LLEVAR AL TERMINAR LA FUNCION");
     * System.out.println("=============================================="); if
     * (abiertos.size() > 0) { for (int i = 0; i < abiertos.size(); i++) {
     * System.out.println("PlancurID_" + (i + 1) + " = " + ((AcPlanCurso)
     * abiertos.get(i)).getId()); System.out.println("Curso_" + (i + 1) + " = "
     * + ((AcPlanCurso) abiertos.get(i)).getCur().getCurNombre()); } } else {
     * System.out.println("No tiene cursos que pueda llevar al terminan la
     * funcion"); } System.out.println(" ");
     */
    /**
     * ****************************************
     */
    //return abiertos;
    //  }
    public List<AcPlanCurso> filtro_aperturados(List<AcPlanCurso> cursos_totales) {
        List<AcPlanCurso> cursos = new ArrayList<AcPlanCurso>();
        HSAperturaCursosDAO dao = (HSAperturaCursosDAO) ServiceFinder.findBean("SpringHibernateDaoCursoAperturado");
        for (int i = 0; i < cursos_totales.size(); i++) {
            if (dao.verificarCursoAperturado(((AcPlanCurso) cursos_totales.get(i)).getId(), b_semestre_alumno).size() != 0) {
                cursos.add((AcPlanCurso) cursos_totales.get(i));
            }
        }
        return cursos;
    }

    public SelectItem[] llenarComboSecciones(int plancur_id) {
        HSSeccionDAO dao = (HSSeccionDAO) ServiceFinder.findBean("HibernateSpringDaoSeccion");
        HSMatriculaDAO dao2 = (HSMatriculaDAO) ServiceFinder.findBean("SpringHibernateDaoMatricula");
        List lista = dao.obtenerSecciones(plancur_id, b_semestre_alumno);
        //System.out.println("b_semestre_alumno "+ b_semestre_alumno);
        //List lista = dao.obtenerSecciones(plancur_id,  this.getId_semestre_seleccionado());

        SelectItem[] cmbSecciones = new SelectItem[lista.size() + 1];
        cmbSecciones[0] = new SelectItem(0, "No registra Matricula");
        for (int i = 0; i < cmbSecciones.length - 1; i++) {
            int matriculados = ((AcSeccion) lista.get(i)).getSecVacantes() - dao2.numeroMatriculadosporSeccionFun(((AcSeccion) lista.get(i)).getId());
            cmbSecciones[i + 1] = new SelectItem(((AcSeccion) lista.get(i)).getId(), ((AcSeccion) lista.get(i)).getSecNombre() + "-" + matriculados);
            // cmbSecciones[i + 1] = new SelectItem(((AcSeccion) lista.get(i)).getId(), ((AcSeccion) lista.get(i)).getSecNombre());
        }
        return cmbSecciones;
    }

    public List getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos(List listaCursos) {
        this.listaCursos = listaCursos;
    }

    public int getMat_det_id() {
        return mat_det_id;
    }

    public void setMat_det_id(int mat_det_id) {
        this.mat_det_id = mat_det_id;
    }

    public String getMat_det_codigo() {
        return mat_det_codigo;
    }

    public void setMat_det_codigo(String mat_det_codigo) {
        this.mat_det_codigo = mat_det_codigo;
    }

    public String getMat_det_nombre() {
        return mat_det_nombre;
    }

    public void setMat_det_nombre(String mat_det_nombre) {
        this.mat_det_nombre = mat_det_nombre;
    }

    public String getMat_det_ciclo() {
        return mat_det_ciclo;
    }

    public void setMat_det_ciclo(String mat_det_ciclo) {
        this.mat_det_ciclo = mat_det_ciclo;
    }

    public String getMat_det_creditos() {
        return mat_det_creditos;
    }

    public void setMat_det_creditos(String mat_det_creditos) {
        this.mat_det_creditos = mat_det_creditos;
    }

    public String getMat_det_tipo() {
        return mat_det_tipo;
    }

    public void setMat_det_tipo(String mat_det_tipo) {
        this.mat_det_tipo = mat_det_tipo;
    }

    public int getSeccion() {
        return seccion;
    }

    public void setSeccion(int seccion) {
        this.seccion = seccion;
    }

    public SelectItem[] getComboSecciones() {
        return comboSecciones;
    }

    public void setComboSecciones(SelectItem[] comboSecciones) {
        this.comboSecciones = comboSecciones;
    }

    public int getB_id_alumno() {
        return b_id_alumno;
    }

    public void setB_id_alumno(int b_id_alumno) {
        this.b_id_alumno = b_id_alumno;
    }

    public String getB_codigo_alumno() {
        return b_codigo_alumno;
    }

    public void setB_codigo_alumno(String b_codigo_alumno) {
        this.b_codigo_alumno = b_codigo_alumno;
    }

    public String getB_paterno_alumno() {
        return b_paterno_alumno;
    }

    public void setB_paterno_alumno(String b_paterno_alumno) {
        this.b_paterno_alumno = b_paterno_alumno;
    }

    public String getB_materno_alumno() {
        return b_materno_alumno;
    }

    public void setB_materno_alumno(String b_materno_alumno) {
        this.b_materno_alumno = b_materno_alumno;
    }

    public String getB_nombre_alumno() {
        return b_nombre_alumno;
    }

    public void setB_nombre_alumno(String b_nombre_alumno) {
        this.b_nombre_alumno = b_nombre_alumno;
    }

    public String getB_id_tipo_alumno() {
        return b_id_tipo_alumno;
    }

    public void setB_id_tipo_alumno(String b_id_tipo_alumno) {
        this.b_id_tipo_alumno = b_id_tipo_alumno;
    }

    public String getB_plan_ing_alumno() {
        return b_plan_ing_alumno;
    }

    public void setB_plan_ing_alumno(String b_plan_ing_alumno) {
        this.b_plan_ing_alumno = b_plan_ing_alumno;
    }

    public String getB_plan_act_alumno() {
        return b_plan_act_alumno;
    }

    public void setB_plan_act_alumno(String b_plan_act_alumno) {
        this.b_plan_act_alumno = b_plan_act_alumno;
    }

    public int getB_semestre_alumno() {
        return b_semestre_alumno;
    }

    public void setB_semestre_alumno(int b_semestre_alumno) {
        this.b_semestre_alumno = b_semestre_alumno;
    }

    public String getB_estado_alumno() {
        return b_estado_alumno;
    }

    public void setB_estado_alumno(String b_estado_alumno) {
        this.b_estado_alumno = b_estado_alumno;
    }

    public void Matricular(ActionEvent event) throws Exception {
        HSMatriculaDAO dao3 = (HSMatriculaDAO) ServiceFinder.findBean("SpringHibernateDaoMatricula");
        HSSeccionDAO daos = (HSSeccionDAO) ServiceFinder.findBean("HibernateSpringDaoTargetSeccion");
        HSHorarioDAO daoh = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoHorario");
        HSSemestreDAO dao7 = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
        //b_id_semestre = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_id_semestre")).getValue().toString());
        b_id_semestre = ((AcSemestre) dao7.seleccionarSemestreActual().get(0)).getId();

        AcSemestre semestre = new AcSemestre();
        semestre.setSemFechaRetInc(((AcSemestre) dao7.getSemestre(this.getId_semestre_seleccionado())).getSemFechaRetInc());
        
        AcMatricula matricula = new AcMatricula();
        matricula.setMatActivo("1");
        matricula.setMatFecha(new Date());

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession(false);
        bUsuario usu = (bUsuario) session1.getAttribute("usuario");
        matricula.setUsuId(usu.getId_usu());

        matricula.setSemId(this.getId_semestre_seleccionado());
        // System.out.println("Matriculado en el semestre: " + b_id_semestre);

        AcAlumno alumno = new AcAlumno();
        alumno.setId(this.getB_id_alumno());
        alumno.setSemId(sem_id_alumno);
        matricula.setAlu(alumno);

        Set<AcMatriculaCurso> matricula_cursos = new LinkedHashSet<AcMatriculaCurso>();
        String mensaje = "";
        // String vacante="";
        int vacantes_vigentes;
        int vacantes_utilizados;
        int vacantes;
        int contador = 0;
        int con = 0;
        float deuda_pension = 0;
        float monto_pagado = 0;
        float deuda_pagar = 0;
        String v_sec_ids = "";
        for (int i = 0; i < this.getListaCursos().size(); i++) {
            List<AcSeccion> listaSec = new ArrayList<AcSeccion>();
            if (((bMatricula) this.getListaCursos().get(i)).getSeccion() != 0) {
                AcMatriculaCurso matricurso = new AcMatriculaCurso();
                matricurso.setMat(matricula);
                matricurso.setMatcurActivo("1");

                AcSeccion sec;
                sec = new AcSeccion();
                sec.setId(((bMatricula) this.getListaCursos().get(i)).getSeccion());
                v_sec_ids = "," + ((bMatricula) this.getListaCursos().get(i)).getSeccion() + v_sec_ids;
                matricurso.setSec(sec);

                listaSec = daos.seleccionarSeccion(((bMatricula) this.getListaCursos().get(i)).getSeccion());
                //listaSec.get(0).setSecNombre(b_nombre);
                vacantes = listaSec.get(0).getSecVacantes();
                vacantes_utilizados = dao3.numeroMatriculadosporSeccion_sin_alumno(((bMatricula) this.getListaCursos().get(i)).getSeccion(), alumno.getId());
                vacantes_vigentes = vacantes - vacantes_utilizados;
                if (vacantes_vigentes <= 0) {
                    contador++;
                    mensaje = listaSec.get(0).getSecNombre() + " - " + listaSec.get(0).getCurape().getPlancur().getCur().getCurNombre();
                }
                matricula_cursos.add(matricurso);
            }
        }

        // System.out.println("los sec_id son1 +-> "+v_sec_ids);
        String v_sec = v_sec_ids.substring(1);
        // System.out.println("los sec_id son2 +-> "+v_sec);
        listaDuplicadoHor = daoh.duplicidadHoraria(v_sec);



        if (matricula_cursos.size() != 0) 
        {
            matricula.setMatTipo("022001");
            if (contador == 0) 
            {
//                if (listaDuplicadoHor.size() == 0) //verifica si trae cantidad con cruce de horario
                    if (listaDuplicadoHor.size() >= 0) 
                {
                    matricula.setAcMatriculaCursos(matricula_cursos);
                    // matricula.getAcMatriculaCursos().
                    
                    HSAlumnoTarifaDAO daotarifa = (HSAlumnoTarifaDAO) ServiceFinder.findBean("SpringHibernateDaoAlumnoTarifa");
                    //List<AdAlumnoTarifa> listaFinal = daotarifa.seleccionarAlumnoTarifaGeneral(matricula.getAlu().getId(), matricula.getSemId());
                    /*for (int i = 0; i < listaFinal.size(); i++) 
                    {
                        if (Integer.parseInt(listaFinal.get(i).getAlutarEstado()) == 2) 
                        {
                            con = con + 1;
                            monto_pagado = monto_pagado + listaFinal.get(i).getAlutarMontoCopy();
                        }
                        if (Integer.parseInt(listaFinal.get(i).getAlutarEstado()) != 2) 
                        {
                            deuda_pagar = deuda_pagar + listaFinal.get(i).getAlutarMontoCopy();
                        }
                        deuda_pension = deuda_pension  + listaFinal.get(i).getAlutarMontoCopy();
                        daotarifa.actualizarAlumnoTarifa(listaFinal.get(i));
                    }*/
                    
                    /*System.out.println("deuda total -> " + deuda_pension + " monto pagado -> " + monto_pagado + " deuda por cancelar -> " + con + " ->" + deuda_pagar);
                    
                    for (int i = 0; i < listaFinal.size(); i++) 
                    {
                        if (Integer.parseInt(listaFinal.get(i).getAlutarEstado()) != 2) 
                        {
                            listaFinal.get(i).setAlutarMatricula("1");
                            listaFinal.get(i).setAlutarMonto(deuda_pension+deuda_pagar);
                            listaFinal.get(i).setMatId(matricula.getId());
                            daotarifa.actualizarAlumnoTarifa(listaFinal.get(i));
                        }
                    }*/
                    
                    int tipoMatricula=1;
                    if (!mat_id.equals("0")) 
                    {
                        if (mat_tipo.equals("022001")) 
                        {
                            tipoMatricula=0; //0 ---> si es rectificacion
                        }
                    }
                    
                    dao3.registrarMatricula(matricula,semestre,tipoMatricula);// 1 ---> si es matricula
                    this.setOncomplete("javascript:alert('Se registro la matricula correctamente');Richfaces.hideModalPanel('mp')");

                    if (!mat_id.equals("0")) 
                    {
                        if (mat_tipo.equals("022001")) 
                        {
                            dao3.RectificarMatricula(Integer.parseInt(mat_id));
                            this.setOncomplete("javascript:alert('Se rectificó la matricula correctamente');Richfaces.hideModalPanel('mp1')");
                        } else if (mat_tipo.equals("022005")) 
                        {
                            dao3.RectificarPreMatricula(Integer.parseInt(mat_id));
                            this.setOncomplete("javascript:alert('Se registro la matricula correctamente');Richfaces.hideModalPanel('mp')");
                        }
                    }
                    //HSAlumnoTarifaDAO daotarifa=(HSAlumnoTarifaDAO) ServiceFinder.findBean("SpringHibernateDaoAlumnoTarifa");
                    List<AdAlumnoTarifa> lista = daotarifa.seleccionarAlumnoTarifa(matricula.getAlu().getId(), matricula.getSemId());
                    for (int i = 0; i < lista.size(); i++) 
                    {
                        lista.get(i).setAlutarMatricula("1");
                        lista.get(i).setMatId(matricula.getId());
                        //System.out.println("modificar -> "+lista.get(i).getId());
                        daotarifa.actualizarAlumnoTarifa(lista.get(i));
                    }

                } 
                else 
                {
                    this.setOncomplete("javascript:Richfaces.showModalPanel('dupHorario');");
                }
            } 
            else 
            {
                this.setOncomplete("javascript:alert('no hay vacantes para " + mensaje + "')");
            }
        } else 
        {
            this.setOncomplete("javascript:alert('Debe seleccionar las secciones correspondientes.')");
        }
    }

    public void ReservarMatricula(ActionEvent event) throws Exception {
        HSMatriculaDAO dao4 = (HSMatriculaDAO) ServiceFinder.findBean("SpringHibernateDaoMatricula");
        AcMatricula matricula = new AcMatricula();
        matricula.setMatActivo("1");
        matricula.setMatFecha(new Date());
        matricula.setMatCodigo("");
        matricula.setMatTipo("022002");

        int matricula_semestre = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_id_semestre")).getValue().toString());
        matricula.setSemId(matricula_semestre);

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession(false);
        bUsuario usu = (bUsuario) session1.getAttribute("usuario");
        matricula.setUsuId(usu.getId_usu());

        int id_alumno = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_id")).getValue().toString());
        AcAlumno alumno = new AcAlumno();
        alumno.setId(id_alumno);
        matricula.setAlu(alumno);

        dao4.registrarMatricula(matricula,2); //2 ---> si es reserva

        String matricula_id = ((UIParameter) event.getComponent().findComponent("p_id_matricula")).getValue().toString();
        if (!matricula_id.equals("0")) 
        {
            dao4.ReservarMatricula(Integer.parseInt(matricula_id));
        }
    }

    public void Imprimir() throws Exception {
        /*
         * Document document = new Document(PageSize.A4, 0, 0, 0, 0); try{
         * ByteArrayOutputStream buffer = new ByteArrayOutputStream();
         * //PdfWriter writer2 =PdfWriter.getInstance(document, new
         * FileOutputStream(new File("C:\\Prueba.pdf"))); PdfWriter writer
         * =PdfWriter.getInstance(document, buffer); HeaderFooter header = new
         * HeaderFooter(new Phrase("UNIVERSIDAD DE CIENCIAS Y HUMANIDADES"),
         * false); HeaderFooter footer =new HeaderFooter(new Phrase("Pag. "),
         * new Phrase(".")); document.setHeader(header);
         * document.setFooter(footer); document.open(); document.add(new
         * Paragraph("H�la ")); PdfContentByte cb = writer.getDirectContent();
         * cb.stroke(); cb.beginText(); BaseFont bf =
         * BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252,
         * BaseFont.NOT_EMBEDDED); cb.setFontAndSize(bf, 12); String text =
         * "Sample text for alignment";
         * cb.showTextAligned(PdfContentByte.ALIGN_CENTER, text + " Center",
         * 250, 700, 0); cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, text + "
         * Right", 250, 650, 0); cb.showTextAligned(PdfContentByte.ALIGN_LEFT,
         * text + " Left", 250, 600, 0); cb.setTextMatrix(0,0);
         * cb.showText("Cabecera de Texto en Impresin 0,0."); cb.endText();
         * document.close(); //byte[] bytes = buffer.toByteArray(); ///carga el
         * buffer del itext byte[] bytes = cargar_reporte().toByteArray();
         * InputStream in=new ByteArrayInputStream(bytes); imprime(in);
         * }catch(Exception ioe){ System.out.println("Error de generacion");
        }
         */

        HSMatriculaDAO dao = (HSMatriculaDAO) ServiceFinder.findBean("SpringHibernateDaoMatricula");
        dao.imprimirMatricula();

    }

    public void Ver(ActionEvent event) throws Exception {
        UIParameter flag = (UIParameter) event.getComponent().findComponent("flag");
        if (flag.getValue().toString().equals("0")) {
            this.setVer(true);
            this.setFlag_ver("1");
            this.setImagen("/Imagenes/actions/up.png");
        } else {
            this.setVer(false);
            this.setFlag_ver("0");
            this.setImagen("/Imagenes/actions/down.png");
        }
        verCursosMatriculados(event);
    }

    public String getFlag_ver() {
        return flag_ver;
    }

    public void setFlag_ver(String flag_ver) {
        this.flag_ver = flag_ver;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isVer() {
        return ver;
    }

    public void setVer(boolean ver) {
        this.ver = ver;
    }

    public String getDetalle_codigoCurso() {
        return detalle_codigoCurso;
    }

    public void setDetalle_codigoCurso(String detalle_codigoCurso) {
        this.detalle_codigoCurso = detalle_codigoCurso;
    }

    public String getDetalle_nombreCurso() {
        return detalle_nombreCurso;
    }

    public void setDetalle_nombreCurso(String detalle_nombreCurso) {
        this.detalle_nombreCurso = detalle_nombreCurso;
    }

    public String getDetalle_creditosCurso() {
        return detalle_creditosCurso;
    }

    public void setDetalle_creditosCurso(String detalle_creditosCurso) {
        this.detalle_creditosCurso = detalle_creditosCurso;
    }

    public String getDetalle_codigoSeccion() {
        return detalle_codigoSeccion;
    }

    public void setDetalle_codigoSeccion(String detalle_codigoSeccion) {
        this.detalle_codigoSeccion = detalle_codigoSeccion;
    }

    public List getDetalle_s() {
        return detalle_s;
    }

    public void setDetalle_s(List detalle_s) {
        this.detalle_s = detalle_s;
    }

    public String getB_creditos_totales() {
        return b_creditos_totales;
    }

    public void setB_creditos_totales(String b_creditos_totales) {
        this.b_creditos_totales = b_creditos_totales;
    }

    public String getB_especialidad_alumno() {
        return b_especialidad_alumno;
    }

    public void setB_especialidad_alumno(String b_especialidad_alumno) {
        this.b_especialidad_alumno = b_especialidad_alumno;
    }

    public String getW_tipo_estado() {
        return w_tipo_estado;
    }

    public void setW_tipo_estado(String w_tipo_estado) {
        this.w_tipo_estado = w_tipo_estado;
    }

    
    public void verCursosMatriculados(ActionEvent event) {
        HSSemestreDAO dao = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
        HSMatriculaDAO matricula = (HSMatriculaDAO) ServiceFinder.findBean("SpringHibernateDaoMatricula");

        bMatricula matriculacurso;
        int matricula_id = Integer.valueOf(((UIParameter) event.getComponent().findComponent("p_id_matricula")).getValue().toString());

        List lista = matricula.listarCursosPorMatricula(matricula_id);
        Set<AcMatriculaCurso> matriculaCurso = new LinkedHashSet<AcMatriculaCurso>();

        if (lista.size() != 0) {
            matriculaCurso = ((AcMatricula) lista.get(0)).getAcMatriculaCursos();
            List list = Collections.synchronizedList(new LinkedList(matriculaCurso));
            int creditos_t = 0;
            List a = null;
            if (list.size() != 0) {
                a = new ArrayList();
                for (int j = 0; j < list.size(); j++) {
                    creditos_t = creditos_t + Integer.parseInt(((AcMatriculaCurso) list.get(j)).getSec().getCurape().getPlancur().getPlancurCredito());
                    matriculacurso = new bMatricula(0);
                    matriculacurso.setDetalle_id(((AcMatriculaCurso) list.get(j)).getSec().getId().toString());
                    matriculacurso.setDetalle_nombreCurso(((AcMatriculaCurso) list.get(j)).getSec().getCurape().getPlancur().getCur().getCurNombre());
                    matriculacurso.setDetalle_codigoCurso(((AcMatriculaCurso) list.get(j)).getSec().getCurape().getPlancur().getPlancurCodigo());
                    matriculacurso.setDetalle_codigoSeccion(((AcMatriculaCurso) list.get(j)).getSec().getSecCodigo());
                    matriculacurso.setDetalle_creditosCurso(((AcMatriculaCurso) list.get(j)).getSec().getCurape().getPlancur().getPlancurCredito());
                    a.add(matriculacurso);
                }
            }
            this.setDetalle_s(a);
        }
    }

    /**
     * *******************************************************************
     */
    public void VerDetalleDeuda(ActionEvent event) throws Exception {
        HSAlumnoTarifaDAO dao = (HSAlumnoTarifaDAO) ServiceFinder.findBean("SpringHibernateDaoAlumnoTarifa");
        HSEstructuraPagoDAO dao2 = (HSEstructuraPagoDAO) ServiceFinder.findBean("SpringHibernateDaoEstructuraPagos");
        List listAlu = dao.seleccionarAlumnoTarifa(Integer.valueOf(((UIParameter) event.getComponent().findComponent("p_id")).getValue().toString()));
        Calendar fecActual = Calendar.getInstance();
        fecActual.setTime(new Date());
        List detalle;
        bMatricula bA;
        if (listAlu.size() > 0) {
            d_detalle = new ArrayList();
            detalle = new ArrayList();

            this.setD_codigo_alu(((AdAlumnoTarifa) listAlu.get(0)).getAlu().getAluCodigo());
            String nombre = ((AdAlumnoTarifa) listAlu.get(0)).getAlu().getAluAppaterno() + " "
                    + ((AdAlumnoTarifa) listAlu.get(0)).getAlu().getAluApmaterno() + ", "
                    + ((AdAlumnoTarifa) listAlu.get(0)).getAlu().getAluNombres();
            this.setD_nombre_alu(nombre);

            for (int k = 0; k < listAlu.size(); k++) {
                Calendar fecPro = Calendar.getInstance();
                fecPro.setTime(((AdAlumnoTarifa) listAlu.get(k)).getAlutarFechaProrroga());

                if (!(((AdAlumnoTarifa) listAlu.get(k)).getAlutarEstado().equals("2"))
                        && fecActual.getTimeInMillis() > fecPro.getTimeInMillis()) {
                    bA = new bMatricula(0);
                    int id = ((AdAlumnoTarifa) listAlu.get(k)).getEstpagdet().getId();
                    int id_epd = ((AdEstructuraPagosDetalle) dao2.seleccionarUnaEstructuraPagosDet(id).get(0)).getEstpag().getId();
                    bA.setD_semestre(((AdEstructuraPagos) dao2.seleccionarUnaEstructuraPagos(id_epd).get(0)).getSem().getSemCodigo());
                    bA.setD_concepto_pag(((AdAlumnoTarifa) listAlu.get(k)).getEstpagdet().getEstpagdetNombre());
                    bA.setD_fecha_pag(((AdAlumnoTarifa) listAlu.get(k)).getAlutarFechaPago().toString());
                    bA.setD_fecha_pro(((AdAlumnoTarifa) listAlu.get(k)).getAlutarFechaProrroga().toString());
                    detalle.add(bA);
                }
            }
            this.setD_detalle(detalle);
            this.setD_oncomplete("javascript:Richfaces.showModalPanel('mp6')");
        }
    }

    public void ImprimirFicha(ActionEvent event) throws Exception {
        this.setTituloReporte("Ficha de Matricula");
        this.setValorRep("ficha");
        this.setP_mat_id(Integer.valueOf(((UIParameter) event.getComponent().findComponent("p_id_matricula")).getValue().toString()));
    }

    public void ImprimirConsolidado(ActionEvent event) throws Exception {
        this.setTituloReporte("Consolidado de Matricula");
        this.setValorRep("consolidado");
        this.setP_mat_id(Integer.valueOf(((UIParameter) event.getComponent().findComponent("p_id_matricula")).getValue().toString()));
    }

    public void cargarReporte(OutputStream out, Object data) throws IOException, Exception, EOFException {
        if (data.toString().equalsIgnoreCase("ficha")) {
            FacesContext context = FacesContext.getCurrentInstance();

            HashMap parametros = new HashMap();
            parametros.put("logo", context.getExternalContext().getResource("/Imagenes/actions/logo_p.jpg"));
            parametros.put("mat_id", this.getP_mat_id());

            String jasper = "/WEB-INF/Reportes/ficha_matricula.jasper";

            Print print = new Print();

            ByteArrayOutputStream buffer = print.cargar_reporte(jasper, parametros);
            byte[] bytes = buffer.toByteArray();

            InputStream input = new ByteArrayInputStream(bytes);

            int size = input.available();
            byte[] pdf = new byte[size];
            input.read(pdf);
            out.write(pdf);

            buffer.flush();
            buffer.close();
            input.close();
            out.flush();
            out.close();
        } else if (data.toString().equalsIgnoreCase("consolidado")) {
            FacesContext context = FacesContext.getCurrentInstance();

            HashMap parametros = new HashMap();
            parametros.put("logo", context.getExternalContext().getResource("/Imagenes/actions/logo_p.jpg"));
            parametros.put("mat_id", this.getP_mat_id());
            parametros.put("subreport", ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/") + "/WEB-INF/Reportes/sub_consolidado_matricula.jasper");

            String jasper = "/WEB-INF/Reportes/consolidado_matricula.jasper";

            Print print = new Print();

            ByteArrayOutputStream buffer = print.cargar_reporte(jasper, parametros);

            byte[] bytes = buffer.toByteArray();

            InputStream input = new ByteArrayInputStream(bytes);

            int size = input.available();
            byte[] pdf = new byte[size];
            input.read(pdf);
            out.write(pdf);

            buffer.flush();
            buffer.close();
            input.close();
            out.flush();
            out.close();
        }
    }

    public String getB_periodo() {
        return b_periodo;
    }

    public void setB_periodo(String b_periodo) {
        this.b_periodo = b_periodo;
    }

    public SelectItem[] getComboSemestres_Listado() {
        return comboSemestres_Listado;
    }

    public void setComboSemestres_Listado(SelectItem[] comboSemestres_Listado) {
        this.comboSemestres_Listado = comboSemestres_Listado;
    }

    public int getSemestre_Listado() {
        return semestre_Listado;
    }

    public void setSemestre_Listado(int semestre_Listado) {
        this.semestre_Listado = semestre_Listado;
    }

    public String getDetalle_id() {
        return detalle_id;
    }

    public void setDetalle_id(String detalle_id) {
        this.detalle_id = detalle_id;
    }

    public boolean isScript() {
        return script;
    }

    public void setScript(boolean script) {
        this.script = script;
    }

    public void EliminarMatricula(ActionEvent event) throws Exception {
        HSMatriculaDAO matricula = (HSMatriculaDAO) ServiceFinder.findBean("SpringHibernateDaoMatricula");
        matricula.EliminarMatricula(Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_id_matricula")).getValue().toString()));
    }

    public int getMatricu_id() {
        return matricu_id;
    }

    public void setMatricu_id(int matricu_id) {
        this.matricu_id = matricu_id;
    }

    public String getMat_id() {
        return mat_id;
    }

    public void setMat_id(String mat_id) {
        this.mat_id = mat_id;
    }

    public String getD_codigo_alu() {
        return d_codigo_alu;
    }

    public void setD_codigo_alu(String d_codigo_alu) {
        this.d_codigo_alu = d_codigo_alu;
    }

    public String getD_nombre_alu() {
        return d_nombre_alu;
    }

    public void setD_nombre_alu(String d_nombre_alu) {
        this.d_nombre_alu = d_nombre_alu;
    }

    public List getD_detalle() {
        return d_detalle;
    }

    public void setD_detalle(List d_detalle) {
        this.d_detalle = d_detalle;
    }

    public String getD_mensaje() {
        return d_mensaje;
    }

    public void setD_mensaje(String d_mensaje) {
        this.d_mensaje = d_mensaje;
    }

    public String getD_oncomplete() {
        return d_oncomplete;
    }

    public void setD_oncomplete(String d_oncomplete) {
        this.d_oncomplete = d_oncomplete;
    }

    public String getD_semestre() {
        return d_semestre;
    }

    public void setD_semestre(String d_semestre) {
        this.d_semestre = d_semestre;
    }

    public String getD_concepto_pag() {
        return d_concepto_pag;
    }

    public void setD_concepto_pag(String d_concepto_pag) {
        this.d_concepto_pag = d_concepto_pag;
    }

    public String getD_fecha_pag() {
        return d_fecha_pag;
    }

    public void setD_fecha_pag(String d_fecha_pag) {
        this.d_fecha_pag = d_fecha_pag;
    }

    public String getD_fecha_pro() {
        return d_fecha_pro;
    }

    public void setD_fecha_pro(String d_fecha_pro) {
        this.d_fecha_pro = d_fecha_pro;
    }

    public boolean isVerDeuda() {
        return verDeuda;
    }

    public void setVerDeuda(boolean verDeuda) {
        this.verDeuda = verDeuda;
    }

    public boolean isVerMatricular() {
        return verMatricular;
    }

    public void setVerMatricular(boolean verMatricular) {
        this.verMatricular = verMatricular;
    }

    public boolean isVerRectificar() {
        return verRectificar;
    }

    public void setVerRectificar(boolean verRectificar) {
        this.verRectificar = verRectificar;
    }

    public boolean isVerEliminar() {
        return verEliminar;
    }

    public void setVerEliminar(boolean verEliminar) {
        this.verEliminar = verEliminar;
    }

    public boolean isVerImprimir1() {
        return verImprimir1;
    }

    public void setVerImprimir1(boolean verImprimir1) {
        this.verImprimir1 = verImprimir1;
    }

    public boolean isVerReservar() {
        return verReservar;
    }

    public void setVerReservar(boolean verReservar) {
        this.verReservar = verReservar;
    }

    public boolean isVerAlerta() {
        return verAlerta;
    }

    public void setVerAlerta(boolean verAlerta) {
        this.verAlerta = verAlerta;
    }

    public String getB_mat_tipo() {
        return b_mat_tipo;
    }

    public void setB_mat_tipo(String b_mat_tipo) {
        this.b_mat_tipo = b_mat_tipo;
    }

    public String getMat_tipo() {
        return mat_tipo;
    }

    public void setMat_tipo(String mat_tipo) {
        this.mat_tipo = mat_tipo;
    }

    public boolean isVerImprimir2() {
        return verImprimir2;
    }

    public void setVerImprimir2(boolean verImprimir2) {
        this.verImprimir2 = verImprimir2;
    }

    public String getTituloReporte() {
        return tituloReporte;
    }

    public void setTituloReporte(String tituloReporte) {
        this.tituloReporte = tituloReporte;
    }

    public int getP_mat_id() {
        return p_mat_id;
    }

    public void setP_mat_id(int p_mat_id) {
        this.p_mat_id = p_mat_id;
    }

    public String getValorRep() {
        return valorRep;
    }

    public void setValorRep(String valorRep) {
        this.valorRep = valorRep;
    }

    /**
     * @param semestre_actual the semestre_actual to set
     */
    public void setSemestre_actual(String semestre_actual) {
        this.semestre_actual = semestre_actual;
    }

    public String getSemestre_seleccionado() {
        HSSemestreDAO dao = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
        //List lista = dao.seleccionarSemestreActual();
        List lista = dao.listarSemestreId(this.getId_semestre_seleccionado());
        semestre_seleccionado = ((AcSemestre) lista.get(0)).getSemNombre();

        return semestre_seleccionado;
    }

    public void setSemestre_seleccionado(String semestre_seleccionado) {
        this.semestre_seleccionado = semestre_seleccionado;
    }

    public int getId_semestre_seleccionado() {
        return id_semestre_seleccionado;
    }

    public void setId_semestre_seleccionado(int id_semestre_seleccionado) {
        this.id_semestre_seleccionado = id_semestre_seleccionado;
    }
    
    public SelectItem[] getComboDocumentos() {
        return comboDocumentos;
    }

    public void setComboDocumentos(SelectItem[] comboDocumentos) {
        this.comboDocumentos = comboDocumentos;
    }

    public String[] getDocumentos() {
        return documentos;
    }

    public void setDocumentos(String[] documentos) {
        this.documentos = documentos;
    }

}