package net.uch.academica.managedBeans;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
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
import javax.servlet.http.HttpSession;
import net.uch.academica.hibernateSpringDao.HSAlumnoDAO;
import net.uch.academica.hibernateSpringDao.HSAlumnoDatoDAO;
import net.uch.academica.hibernateSpringDao.HSAsistenciaDAO;
import net.uch.academica.hibernateSpringDao.HSPlanCurricularDAO;
import net.uch.administrativa.hibernateSpringDao.HSAlumnoTarifaDAO;
import net.uch.administrativa.hibernateSpringDao.HSEstructuraPagoDAO;
import net.uch.administrativa.managedBeans.bEstructuraPago;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcAlumno;
import net.uch.mapping.AcAlumnoDato;
import net.uch.mapping.AcAlumnoDocumento;
import net.uch.mapping.AcEspecialidad;
import net.uch.mapping.AcFacultad;
import net.uch.mapping.AcPlancurricular;
import net.uch.mapping.AcSemestre;
import net.uch.mapping.AdAlumnoTarifa;
import net.uch.mapping.AdConceptoPago;
import net.uch.mapping.AdEstructuraPagos;
import net.uch.mapping.AdEstructuraPagosDetalle;
import net.uch.mapping.TbAsistencia;
import net.uch.mapping.TbCatalogo;
import net.uch.mapping.TbDistrito;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSEspecialidadDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSFacultadDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSSemestreDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSUbigeoDAO;
import net.uch.tablaSistema.managedBeans.bUsuario;

public class bAlumno {

    public String imagenGEP;
    public String oncompleteGEP;
    public boolean disabledGEP = false;
    public List<AdAlumnoTarifa> listaPubli = new ArrayList<AdAlumnoTarifa>();
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
    String semestre_actual;
    String plan_vigente;
    public SelectItem[] comboEspecialidad_buscar;
    public SelectItem[] comboFacultad_buscar;
    int b_id_alumno;
    String b_codigo_alumno;
    String b_paterno_alumno;
    String b_materno_alumno;
    String b_nombre_alumno;
    String b_dni_alumno;
    String b_mail_alumno;
    String b_id_tipo_alumno;
    String b_id_estado_alumno;
    String b_id_modalidad_alumno;
    int b_id_esp_act_alumno;
    int b_id_esp_ing_alumno;
    int b_id_semestre_alumno;
    String b_activo_alumno;
    int b_id_plan_act_alumno;
    int b_id_plan_ing_alumno;
    int b_id_fac_ing_alumno;
    int b_id_fac_act_alumno;
    private int mes = 0;
    private int anio = 0;
    private String oncomplete;
    String b_plan_act_alumno;
    String b_plan_ing_alumno;
    private String b_procedencia;
    private String b_procedencia_id;
    private String b_alu_familiar;
    private String b_alu_tipo_familiar;
    private String b_alu_forma_pago;
    private String b_alu_unidad;
    private float b_alu_monto_pago;
    private String w_cate_codigo;
    private Date w_ultima_fecha=new Date();

    public Date getW_ultima_fecha() {
        return w_ultima_fecha;
    }

    public void setW_ultima_fecha(Date w_ultima_fecha) {
        this.w_ultima_fecha = w_ultima_fecha;
    }



    public SelectItem[] comboCategoria;

    public SelectItem[] getComboCategoria() {
         try {
            HSCatalogoDAO dao = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List lista = dao.seleccionarGrupo("060");
            comboCategoria = new SelectItem[lista.size() + 1];
            comboCategoria[0] = new SelectItem("000000", "[Seleccione]");
            for (int i = 0; i < (comboCategoria.length - 1); i++) {
                comboCategoria[i + 1] = new SelectItem(((TbCatalogo) lista.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista.get(i)).getCatDescripcionElemento());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return comboCategoria;
    }

    public void setComboCategoria(SelectItem[] comboCategoria) {
        this.comboCategoria = comboCategoria;
    }

    public String getW_cate_codigo() {
        return w_cate_codigo;
    }

    public void setW_cate_codigo(String w_cate_codigo) {
        this.w_cate_codigo = w_cate_codigo;
    }




    public String getB_alu_familiar() {
        return b_alu_familiar;
    }

    public void setB_alu_familiar(String b_alu_familiar) {
        this.b_alu_familiar = b_alu_familiar;
    }

    public String getB_alu_forma_pago() {
        return b_alu_forma_pago;
    }

    public void setB_alu_forma_pago(String b_alu_forma_pago) {
        this.b_alu_forma_pago = b_alu_forma_pago;
    }

    public String getB_alu_tipo_familiar() {
        return b_alu_tipo_familiar;
    }

    public void setB_alu_tipo_familiar(String b_alu_tipo_familiar) {
        this.b_alu_tipo_familiar = b_alu_tipo_familiar;
    }

    public String getB_alu_unidad() {
        return b_alu_unidad;
    }

    public void setB_alu_unidad(String b_alu_unidad) {
        this.b_alu_unidad = b_alu_unidad;
    }
    private String direccion_lima_new;
    private String distrito_lima_new;
    private String colegio_direccion_new;
    private String lugar_nacimiento_new;
    private List listaAsistencia;
    private String a_lunes;
    private String a_martes;
    private String a_miercoles;
    private String a_jueves;
    private String a_viernes;
    private String a_sabado;
    private String a_domingo;
    private String b_mes;
    private String b_anio;
    private String h_lunes;
    private String h_martes;
    private String h_miercoles;
    private String h_jueves;
    private String h_viernes;
    private String h_sabado;
    private String h_domingo;
    private String e_lunes;
    private String e_martes;
    private String e_miercoles;
    private String e_jueves;
    private String e_viernes;
    private String e_sabado;
    private String e_domingo;
    public List lista3;
    public List lista2;
    private SelectItem[] comboTipoDocumento;
    private String tipoDocumento;
    private SelectItem[] comboDistritoLima;
    private String distritoLima;
    private SelectItem[] comboAniosPreparacion;
    private String aniosPreparacion;
    private SelectItem[] comboMesesPreparacion;
    private String mesesPreparacion;
    private SelectItem[] combosexo;
    private String sexo;
    private SelectItem[] comboDepartamentos;
    private String departamentos = "000000";
    private SelectItem[] comboProvincias;
    private String provincias = "000000";
    private SelectItem[] comboDistrito;
    private String distrito = "000000";
    private SelectItem[] comboDepartamentos1;
    private String departamentos1 = "000000";
    private SelectItem[] comboProvincias1;
    private String provincias1 = "000000";
    private SelectItem[] comboDistrito1;
    private String distrito1 = "000000";
    private SelectItem[] comboDistrito2;
    private String distrito2 = "000000";
    private SelectItem[] comboDistrito3;
    private String distrito3 = "000000";
    private SelectItem[] comboTipoColegio;
    private String tipoColegio;
    private SelectItem[] comboAcademias;
    private String academias;
    private SelectItem[] comboPreparacion;
    private String preparacion;
    private String b_departamento_col;
    private String b_provincia_col;
    private String b_distrito_col;
    private String b_departamento_nac;
    private String b_provincia_nac;
    private String b_distrito_nac;
    private String b_direccion_lima;
    private String b_telefono_fijo;
    private String b_academiaOtros;
    private String b_colegio;
    private String b_correo;
    private String b_telefono_celular;
    private String b_numeroDocumento;
    private Date b_nacimiento;
    private String procedencia;
    private SelectItem[] comboProcedencia;
    private SelectItem[] comboDocumentos;
    private String[] documentos;
    private SelectItem[] comboTipoFamiliar;
    private SelectItem[] comboFormaPago;
    private SelectItem[] comboUnidad;

    public bAlumno() throws SQLException, Exception {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession(false);
        bUsuario usu = (bUsuario) session1.getAttribute("usuario");
        if (usu != null) {
            HSFacultadDAO dao0 = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
            List lista0 = dao0.seleccionarFacultad("", "");
            comboFacultad_buscar = new SelectItem[lista0.size() + 1];
            comboFacultad_buscar[0] = new SelectItem(0, "Todas");
            for (int i = 0; i < comboFacultad_buscar.length - 1; i++) {
                comboFacultad_buscar[i + 1] = new SelectItem(((AcFacultad) lista0.get(i)).getId(), ((AcFacultad) lista0.get(i)).getFacNombre());
            }
            /*estado*/
            HSCatalogoDAO dao1 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List lista1 = dao1.seleccionarCatalogo("011");
            comboCatalogo_estado = new SelectItem[lista1.size()];
            for (int i = 0; i < comboCatalogo_estado.length; i++) {
                comboCatalogo_estado[i] = new SelectItem(((TbCatalogo) lista1.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista1.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista1.get(i)).getCatDescripcionElemento());
            }

            /*modalidad*/
            List lista15 = dao1.seleccionarCatalogo("010");
            comboCatalogo_moda = new SelectItem[lista15.size()];
            for (int i = 0; i < comboCatalogo_moda.length; i++) {
                comboCatalogo_moda[i] = new SelectItem(((TbCatalogo) lista15.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista15.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista15.get(i)).getCatDescripcionElemento());
            }
            /*TipoFamiliar*/
            List listatf = dao1.seleccionarCatalogo("040");
            comboTipoFamiliar = new SelectItem[listatf.size()];
            for (int i = 0; i < comboTipoFamiliar.length; i++) {
                comboTipoFamiliar[i] = new SelectItem(((TbCatalogo) listatf.get(i)).getCatCodigoGrupo() + ((TbCatalogo) listatf.get(i)).getCatCodigoElemento(), ((TbCatalogo) listatf.get(i)).getCatDescripcionElemento());
            }
            /*FormaPago*/
            List listafp = dao1.seleccionarCatalogo("041");
            comboFormaPago = new SelectItem[listafp.size()];
            for (int i = 0; i < comboFormaPago.length; i++) {
                comboFormaPago[i] = new SelectItem(((TbCatalogo) listafp.get(i)).getCatCodigoGrupo() + ((TbCatalogo) listafp.get(i)).getCatCodigoElemento(), ((TbCatalogo) listafp.get(i)).getCatDescripcionElemento());
            }
            /*UNidad*/
            List listauni = dao1.seleccionarCatalogo("042");
            comboUnidad = new SelectItem[listauni.size()];
            for (int i = 0; i < comboUnidad.length; i++) {
                comboUnidad[i] = new SelectItem(((TbCatalogo) listauni.get(i)).getCatCodigoGrupo() + ((TbCatalogo) listauni.get(i)).getCatCodigoElemento(), ((TbCatalogo) listauni.get(i)).getCatDescripcionElemento());
            }

            /**facultad ingreso*/
            HSFacultadDAO dao2 = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
            lista2 = dao2.seleccionarFacultad("", "");
            comboFacultades_ingreso = new SelectItem[lista2.size() + 1];
            comboFacultades_ingreso[0] = new SelectItem(0, "[Seleccione]");
            for (int i = 0; i < comboFacultades_ingreso.length - 1; i++) {
                comboFacultades_ingreso[i + 1] = new SelectItem(((AcFacultad) lista2.get(i)).getId(), ((AcFacultad) lista2.get(i)).getFacNombre());
            }
            /*tipo*/
            HSCatalogoDAO dao3 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            lista3 = dao3.seleccionarCatalogo("009");
            comboCatalogo_tipo = new SelectItem[lista3.size()];
            for (int i = 0; i < comboCatalogo_tipo.length; i++) {
                comboCatalogo_tipo[i] = new SelectItem(((TbCatalogo) lista3.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista3.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista3.get(i)).getCatDescripcionElemento());
            }
            /**/
            HSFacultadDAO dao4 = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
            List lista4 = dao4.seleccionarFacultad("", "");
            comboFacultades_actual = new SelectItem[lista4.size() + 1];
            comboFacultades_actual[0] = new SelectItem(0, "[Seleccione]");
            for (int i = 0; i < comboFacultades_actual.length - 1; i++) {
                comboFacultades_actual[i + 1] = new SelectItem(((AcFacultad) lista4.get(i)).getId(), ((AcFacultad) lista4.get(i)).getFacNombre());
            }
            /*Semestre Actual*/
            HSSemestreDAO dao5 = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
            List lista5 = dao5.seleccionarSemestreActual();
            semestre_actual = ((AcSemestre) lista5.get(0)).getSemNombre();

            /*Academias*/
            HSCatalogoDAO dao6 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List lista6 = dao6.seleccionarCatalogo("028");
            comboAcademias = new SelectItem[lista6.size()];
            for (int i = 0; i < comboAcademias.length; i++) {
                comboAcademias[i] = new SelectItem(((TbCatalogo) lista6.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista6.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista6.get(i)).getCatDescripcionElemento());
            }

            /*preparacion*/
            HSCatalogoDAO dao7 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List lista7 = dao7.seleccionarCatalogo("029");
            comboPreparacion = new SelectItem[lista7.size()];
            for (int i = 0; i < comboPreparacion.length; i++) {
                comboPreparacion[i] = new SelectItem(((TbCatalogo) lista7.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista7.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista7.get(i)).getCatDescripcionElemento());
            }

            /*Documentos*/
            HSCatalogoDAO dao8 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List lista8 = dao8.seleccionarCatalogo("031");
            comboDocumentos = new SelectItem[lista8.size()];
            for (int i = 0; i < comboDocumentos.length; i++) {
                comboDocumentos[i] = new SelectItem(((TbCatalogo) lista8.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista8.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista8.get(i)).getCatDescripcionElemento());
                System.out.println(comboDocumentos[i]);
            }
            /*Tipo de Colegio*/
            HSCatalogoDAO dao9 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List lista9 = dao9.seleccionarCatalogo("030");
            comboTipoColegio = new SelectItem[lista9.size()];
            for (int i = 0; i < comboTipoColegio.length; i++) {
                comboTipoColegio[i] = new SelectItem(((TbCatalogo) lista9.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista9.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista9.get(i)).getCatDescripcionElemento());
            }
            /*Procedencia*/
            /*HSCatalogoDAO dao10 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List lista10 = dao10.seleccionarCatalogo("023");
            comboProcedencia = new SelectItem[lista10.size()];
            for (int i = 0; i < comboProcedencia.length; i++) {
                comboProcedencia[i] = new SelectItem(((TbCatalogo) lista10.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista10.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista10.get(i)).getCatDescripcionElemento());
            }*/
            /*Semestres*/
            HSSemestreDAO dao11 = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
            List lista11 = dao11.seleccionarSemestreActivo();
            int num = lista11.size();
            if (num > num_reg_sem) {
                comboSemestre = new SelectItem[num];
            } else {
                comboSemestre = new SelectItem[lista11.size()];
            }
            for (int i = 0; i < comboSemestre.length; i++) {
                comboSemestre[i] = new SelectItem(((AcSemestre) lista11.get(i)).getId(), ((AcSemestre) lista11.get(i)).getSemNombre());
            }

            /*Departamentos1*/
            HSUbigeoDAO dao12 = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
            List lista12 = dao12.seleccionarDepartamento();
            comboDepartamentos1 = new SelectItem[lista12.size()];
            for (int i = 0; i < comboDepartamentos1.length; i++) {
                comboDepartamentos1[i] = new SelectItem(((TbDistrito) lista12.get(i)).getId(), ((TbDistrito) lista12.get(i)).getDisDes());
            }
            /*Departamentos*/
            HSUbigeoDAO dao13 = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
            List lista13 = dao13.seleccionarDepartamento();
            comboDepartamentos = new SelectItem[lista13.size()];
            for (int i = 0; i < comboDepartamentos.length; i++) {
                comboDepartamentos[i] = new SelectItem(((TbDistrito) lista13.get(i)).getId(), ((TbDistrito) lista13.get(i)).getDisDes());
            }
            /*Anios de Preparacion*/
            HSCatalogoDAO dao14 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List lista14 = dao14.seleccionarCatalogo("025");
            comboAniosPreparacion = new SelectItem[lista14.size()];
            for (int i = 0; i < comboAniosPreparacion.length; i++) {
                comboAniosPreparacion[i] = new SelectItem(((TbCatalogo) lista14.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista14.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista14.get(i)).getCatDescripcionElemento());
            }

            HSCatalogoDAO dao16 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List lista16 = dao16.seleccionarCatalogo("026");
            comboMesesPreparacion = new SelectItem[lista16.size()];
            for (int i = 0; i < comboMesesPreparacion.length; i++) {
                comboMesesPreparacion[i] = new SelectItem(((TbCatalogo) lista16.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista16.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista16.get(i)).getCatDescripcionElemento());
            }

        } else {
            throw new Exception();
        }
        System.out.println("TEMRINO");
    }

    public bAlumno(int param) {
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

    public String getB_procedencia() {
        return b_procedencia;
    }

    public void setB_procedencia(String b_procedencia) {
        this.b_procedencia = b_procedencia;
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
        return semestre_actual;
    }

    public void setSemestre_vigente(String semestre_actual) {
        this.semestre_actual = semestre_actual;
    }

    public SelectItem[] getComboFacultades_ingreso() throws Exception {
        return comboFacultades_ingreso;
    }

    public void setComboFacultades_ingreso(SelectItem[] comboFacultades) {
        this.comboFacultades_ingreso = comboFacultades;
    }

    public SelectItem[] getComboEspecialidades_ingreso() throws Exception {
        cambiarEspecialidad_ingreso(b_id_fac_ing_alumno);
        return comboEspecialidades_ingreso;
    }

    public void setComboEspecialidades_ingreso(SelectItem[] comboEspecialidades_ingreso) {
        this.comboEspecialidades_ingreso = comboEspecialidades_ingreso;
    }

    public void cambiarEspecialidad_ingreso(int id) throws Exception {
        HSEspecialidadDAO dao = (HSEspecialidadDAO) ServiceFinder.findBean("SpringHibernateDaoEspecialidad");
        List lista = dao.seleccionarEspecialidad(id);
        SelectItem[] cmbEspecialidades_ingreso = new SelectItem[lista.size() + 1];
        cmbEspecialidades_ingreso[0] = new SelectItem(0, "[Seleccione]");
        for (int i = 0; i < cmbEspecialidades_ingreso.length - 1; i++) {
            cmbEspecialidades_ingreso[i + 1] = new SelectItem(((AcEspecialidad) lista.get(i)).getId(), ((AcEspecialidad) lista.get(i)).getEspNombre());
        }
        this.setComboEspecialidades_ingreso(cmbEspecialidades_ingreso);
    }

    public SelectItem[] getComboPlan_ingreso() throws Exception {
        cambiarPlan_ingreso(b_id_esp_ing_alumno);
        return comboPlan_ingreso;
    }

    public void setComboPlan_ingreso(SelectItem[] comboPlan_ingreso) {
        this.comboPlan_ingreso = comboPlan_ingreso;
    }

    public void cambiarPlan_ingreso(int id) throws Exception {
        HSPlanCurricularDAO dao = (HSPlanCurricularDAO) ServiceFinder.findBean("SpringHibernateDaoPlanCurricular");
        List lista = dao.seleccionarPlanActivo(id);
        SelectItem[] cmbPlan_ingreso = new SelectItem[lista.size() + 1];
        cmbPlan_ingreso[0] = new SelectItem(0, "[Seleccione]");
        for (int i = 0; i < cmbPlan_ingreso.length - 1; i++) {
            cmbPlan_ingreso[i + 1] = new SelectItem(((AcPlancurricular) lista.get(i)).getId(), ((AcPlancurricular) lista.get(i)).getPlanDescripcion());
        }
        this.setComboPlan_ingreso(cmbPlan_ingreso);
    }

    public SelectItem[] getComboFacultades_actual() throws Exception {
        return comboFacultades_actual;
    }

    public void setComboFacultades_actual(SelectItem[] comboFacultades) {
        this.comboFacultades_actual = comboFacultades;
    }

    public SelectItem[] getComboEspecialidades_actual() throws Exception {
        cambiarEspecialidad_actual(b_id_fac_act_alumno);
        return comboEspecialidades_actual;
    }

    public void setComboEspecialidades_actual(SelectItem[] comboEspecialidades_actual) {
        this.comboEspecialidades_actual = comboEspecialidades_actual;
    }

    public void cambiarEspecialidad_actual(int id) throws Exception {
        HSEspecialidadDAO dao = (HSEspecialidadDAO) ServiceFinder.findBean("SpringHibernateDaoEspecialidad");
        List lista = dao.seleccionarEspecialidad(id);
        SelectItem[] cmbEspecialidades_actual = new SelectItem[lista.size() + 1];
        cmbEspecialidades_actual[0] = new SelectItem(0, "[Seleccione]");
        for (int i = 0; i < cmbEspecialidades_actual.length - 1; i++) {
            cmbEspecialidades_actual[i + 1] = new SelectItem(((AcEspecialidad) lista.get(i)).getId(), ((AcEspecialidad) lista.get(i)).getEspNombre());
        }
        this.setComboEspecialidades_actual(cmbEspecialidades_actual);
    }

    public SelectItem[] getComboPlan_actual() throws Exception {
        cambiarPlan_actual(b_id_esp_act_alumno);
        return comboPlan_actual;
    }

    public void setComboPlan_actual(SelectItem[] comboPlan_actual) {
        this.comboPlan_actual = comboPlan_actual;
    }

    public void cambiarPlan_actual(int id) throws Exception {
        HSPlanCurricularDAO dao = (HSPlanCurricularDAO) ServiceFinder.findBean("SpringHibernateDaoPlanCurricular");
        List lista = dao.seleccionarPlanActivo(id);
        SelectItem[] cmbPlanActual = new SelectItem[lista.size() + 1];
        cmbPlanActual[0] = new SelectItem(0, "[Seleccione]");
        for (int i = 0; i < cmbPlanActual.length - 1; i++) {
            cmbPlanActual[i + 1] = new SelectItem(((AcPlancurricular) lista.get(i)).getId(), ((AcPlancurricular) lista.get(i)).getPlanDescripcion());
        }
        this.setComboPlan_actual(cmbPlanActual);
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

    public String getB_dni_alumno() {
        return b_dni_alumno;
    }

    public void setB_dni_alumno(String b_dni_alumno) {
        this.b_dni_alumno = b_dni_alumno;
    }

    public String getB_mail_alumno() {
        return b_mail_alumno;
    }

    public void setB_mail_alumno(String b_mail_alumno) {
        this.b_mail_alumno = b_mail_alumno;
    }

    public String getB_id_tipo_alumno() {
        return b_id_tipo_alumno;
    }

    public void setB_id_tipo_alumno(String b_id_tipo_alumno) {
        this.b_id_tipo_alumno = b_id_tipo_alumno;
    }

    public String getB_id_estado_alumno() {
        return b_id_estado_alumno;
    }

    public void setB_id_estado_alumno(String b_id_estado_alumno) {
        this.b_id_estado_alumno = b_id_estado_alumno;
    }

    public String getB_id_modalidad_alumno() {
        return b_id_modalidad_alumno;
    }

    public void setB_id_modalidad_alumno(String b_id_modalidad_alumno) {
        this.b_id_modalidad_alumno = b_id_modalidad_alumno;
    }

    public int getB_id_esp_act_alumno() {
        return b_id_esp_act_alumno;
    }

    public void setB_id_esp_act_alumno(int b_id_esp_act_alumno) {
        this.b_id_esp_act_alumno = b_id_esp_act_alumno;
    }

    public int getB_id_esp_ing_alumno() {
        return b_id_esp_ing_alumno;
    }

    public void setB_id_esp_ing_alumno(int b_id_esp_ing_alumno) {
        this.b_id_esp_ing_alumno = b_id_esp_ing_alumno;
    }

    public int getB_id_semestre_alumno() {
        return b_id_semestre_alumno;
    }

    public void setB_id_semestre_alumno(int b_id_semestre_alumno) {
        this.b_id_semestre_alumno = b_id_semestre_alumno;
    }

    public String getB_activo_alumno() {
        return b_activo_alumno;
    }

    public void setB_activo_alumno(String b_activo_alumno) {
        this.b_activo_alumno = b_activo_alumno;
    }

    public int getB_id_plan_act_alumno() {
        return b_id_plan_act_alumno;
    }

    public void setB_id_plan_act_alumno(int b_id_plan_act_alumno) {
        this.b_id_plan_act_alumno = b_id_plan_act_alumno;
    }

    public int getB_id_plan_ing_alumno() {
        return b_id_plan_ing_alumno;
    }

    public void setB_id_plan_ing_alumno(int b_id_plan_ing_alumno) {
        this.b_id_plan_ing_alumno = b_id_plan_ing_alumno;
    }

    public int getB_id_fac_ing_alumno() {
        return b_id_fac_ing_alumno;
    }

    public void setB_id_fac_ing_alumno(int b_id_fac_ing_alumno) {
        this.b_id_fac_ing_alumno = b_id_fac_ing_alumno;
    }

    public int getB_id_fac_act_alumno() {
        return b_id_fac_act_alumno;
    }

    public void setB_id_fac_act_alumno(int b_id_fac_act_alumno) {
        this.b_id_fac_act_alumno = b_id_fac_act_alumno;
    }

    public String getB_plan_act_alumno() {
        return b_plan_act_alumno;
    }

    public void setB_plan_act_alumno(String b_plan_act_alumno) {
        this.b_plan_act_alumno = b_plan_act_alumno;
    }

    public String getB_plan_ing_alumno() {
        return b_plan_ing_alumno;
    }

    public void setB_plan_ing_alumno(String b_plan_ing_alumno) {
        this.b_plan_ing_alumno = b_plan_ing_alumno;
    }

    public void Nuevo() {
        Limpiar();
    }

    public SelectItem[] getComboFacultad_buscar() throws Exception {
        return comboFacultad_buscar;
    }

    public void setComboFacultad_buscar(SelectItem[] comboFacultad) {
        this.comboFacultad_buscar = comboFacultad;
    }

    public SelectItem[] getComboEspecialidad_buscar() throws Exception {
        cambiarEspecialidad_buscar(b_id_fac_act);
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
        HSCatalogoDAO daoCatalogo = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        HSEspecialidadDAO dao5 = (HSEspecialidadDAO) ServiceFinder.findBean("SpringHibernateDaoEspecialidad");
        List<AcEspecialidad> lista_especialidad_1 = dao5.seleccionarEspecialidad();
        List<TbCatalogo> lista_pro = daoCatalogo.seleccionarGrupo("023");
        List<TbCatalogo> lista_tipo_1 = daoCatalogo.seleccionarGrupo("009");
        List<AcAlumno> lAlumnos = dao.seleccionarAlumno(this.getB_codigo(), this.getB_paterno(), this.getB_materno(), this.getB_nombre(), this.getB_id_fac_act(), this.getB_id_esp_act());
        bAlumno bA;
//        Object[] O;
        for (int i = 0; i < lAlumnos.size(); i++) {
            bA = new bAlumno(0);
            bA.setB_id(lAlumnos.get(i).getId());
            bA.setB_codigo(lAlumnos.get(i).getAluCodigo());
            bA.setB_paterno(lAlumnos.get(i).getAluAppaterno());
            bA.setB_materno(lAlumnos.get(i).getAluApmaterno());
            bA.setB_nombre(lAlumnos.get(i).getAluNombres());
            bA.setB_dni(lAlumnos.get(i).getAluDni());
            bA.setB_mail(lAlumnos.get(i).getAluMail());
            bA.setB_id_tipo(lAlumnos.get(i).getAluTipo());
            for (int k = 0; k < lista_tipo_1.size(); k++) {
                if (("009" + lista_tipo_1.get(k).getCatCodigoElemento()).equals(lAlumnos.get(i).getAluTipo())) {
                    bA.setB_tipo(lista_tipo_1.get(k).getCatDescripcionElemento());
                }
            }
            bA.setB_id_modalidad(lAlumnos.get(i).getAluModalidad());
            bA.setB_id_estado(lAlumnos.get(i).getAluEstado());
            bA.setB_id_esp_act(lAlumnos.get(i).getEsp().getId());
            bA.setB_esp_act(lAlumnos.get(i).getEsp().getEspNombre());
            bA.setB_id_esp_ing(lAlumnos.get(i).getEspIdIngreso());
            bA.setB_id_semestre(lAlumnos.get(i).getSemId());
            bA.setB_activo(lAlumnos.get(i).getAluActivo());
            bA.setB_id_plan_ing(lAlumnos.get(i).getPlanIdIngreso());
            bA.setB_id_plan_act(lAlumnos.get(i).getPlanIdActual());
            bA.setB_procedencia_id(lAlumnos.get(i).getAluProcedencia());
            bA.setB_alu_familiar(lAlumnos.get(i).getAluFamiliar());
            bA.setB_alu_tipo_familiar(lAlumnos.get(i).getAluTipoFamiliar());
            bA.setB_alu_forma_pago(lAlumnos.get(i).getAluFormaPago());
            bA.setB_alu_unidad(lAlumnos.get(i).getAluUnidad());
            bA.setB_alu_monto_pago(lAlumnos.get(i).getAluMontoPago());
            for (int k = 0; k < lista_especialidad_1.size(); k++) {
                if (lista_especialidad_1.get(k).getFac().getId().equals(lAlumnos.get(i).getEsp().getId())) {
                    bA.setB_id_fac_act(lista_especialidad_1.get(k).getFac().getId());
                }
            }
            for (int k = 0; k < lista_especialidad_1.size(); k++) {
                if (lista_especialidad_1.get(k).getFac().getId().equals(lAlumnos.get(i).getEsp().getId())) {
                    bA.setB_id_fac_ing(lista_especialidad_1.get(k).getFac().getId());
                }
            }
            for (int k = 0; k < lista_pro.size(); k++) {
                if (("023" + lista_pro.get(k).getCatCodigoElemento()).equals(lAlumnos.get(i).getAluProcedencia())) {
                    bA.setB_procedencia(lista_pro.get(k).getCatDescripcionElemento());
                }
            }
            bA.setImagenGEP("/Imagenes/actions/programacion.png");
            bA.setDisabledGEP(false);
            listaAlumno.add(bA);
        }
        this.setListaAlumno(listaAlumno);
    }

    public void EliminarFila(ActionEvent event) throws Exception {
        UIParameter delete = (UIParameter) event.getComponent().findComponent("delete");
        Eliminar(delete.getValue().toString());
    }

    public void Eliminar(String id) throws Exception {
        HSAlumnoDAO dao = (HSAlumnoDAO) ServiceFinder.findBean("SpringHibernateDaoAlumno");
        dao.eliminarAlumno("" + id);
    }

    public void Limpiar() {
        b_id_alumno = 0;
        b_codigo_alumno = "";
        b_paterno_alumno = "";
        b_materno_alumno = "";
        b_nombre_alumno = "";
        b_dni_alumno = "";
        b_dni_alumno = "";
        b_mail_alumno = "";
        b_mail_alumno = "";
        b_id_tipo_alumno = "";
        b_plan_ing_alumno = "";
        b_plan_act_alumno = "";
        b_id_modalidad_alumno = "";
        b_id_estado_alumno = "";
        b_id_fac_ing_alumno = 0;
        b_id_esp_ing_alumno = 0;
        b_id_fac_act_alumno = 0;
        b_id_esp_act_alumno = 0;
        b_id_semestre_alumno = 0;
        b_id_plan_ing_alumno = 0;
        b_id_plan_act_alumno = 0;
        b_procedencia = "";
        b_alu_familiar = "";
        b_alu_tipo_familiar = "";
        b_alu_forma_pago = "";
        b_alu_unidad = "";
        b_alu_monto_pago = 0;
    }

    public void EditarFila(ActionEvent event) throws Exception {
        Limpiar();
        if (((UIParameter) event.getComponent().findComponent("p_dni")).getValue() == null) {
            b_dni_alumno = "";
        } else {
            b_dni_alumno = ((UIParameter) event.getComponent().findComponent("p_dni")).getValue().toString();
        }
        if (((UIParameter) event.getComponent().findComponent("p_mail")).getValue() == null) {
            b_mail_alumno = "";
        } else {
            b_mail_alumno = ((UIParameter) event.getComponent().findComponent("p_mail")).getValue().toString();
        }
        b_id_alumno = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_id")).getValue().toString());
        b_codigo_alumno = ((UIParameter) event.getComponent().findComponent("p_codigo")).getValue().toString();
        b_paterno_alumno = ((UIParameter) event.getComponent().findComponent("p_paterno")).getValue().toString();
        b_materno_alumno = ((UIParameter) event.getComponent().findComponent("p_materno")).getValue().toString();
        b_nombre_alumno = ((UIParameter) event.getComponent().findComponent("p_nombre")).getValue().toString();
        b_id_tipo_alumno = ((UIParameter) event.getComponent().findComponent("p_id_tipo")).getValue().toString();
        if (((UIParameter) event.getComponent().findComponent("p_plan_ing")).getValue() != null) {
            b_plan_ing_alumno = ((UIParameter) event.getComponent().findComponent("p_plan_ing")).getValue().toString();
        }
        if (((UIParameter) event.getComponent().findComponent("p_plan_act")).getValue() != null) {
            b_plan_act_alumno = ((UIParameter) event.getComponent().findComponent("p_plan_act")).getValue().toString();
        }
        b_id_modalidad_alumno = ((UIParameter) event.getComponent().findComponent("p_id_modalidad")).getValue().toString();
        b_id_estado_alumno = ((UIParameter) event.getComponent().findComponent("p_id_estado")).getValue().toString();
        b_id_fac_ing_alumno = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_id_fac_ing")).getValue().toString());
        b_id_esp_ing_alumno = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_id_esp_ing")).getValue().toString());
        b_id_fac_act_alumno = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_id_fac_act")).getValue().toString());
        b_id_esp_act_alumno = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_id_esp_act")).getValue().toString());
        b_id_semestre_alumno = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_id_semestre")).getValue().toString());
        b_id_plan_ing_alumno = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_id_plan_ing")).getValue().toString());
        b_id_plan_act_alumno = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_id_plan_act")).getValue().toString());
        b_procedencia = ((UIParameter) event.getComponent().findComponent("p_procedencia")).getValue().toString();
        b_alu_familiar = ((UIParameter) event.getComponent().findComponent("p_alu_familiar")).getValue().toString();
        b_alu_tipo_familiar = ((UIParameter) event.getComponent().findComponent("p_alu_tipo_familiar")).getValue().toString();
        b_alu_forma_pago = ((UIParameter) event.getComponent().findComponent("p_alu_forma_pago")).getValue().toString();
        b_alu_unidad = ((UIParameter) event.getComponent().findComponent("p_alu_unidad")).getValue().toString();
        b_alu_monto_pago = Float.parseFloat(((UIParameter) event.getComponent().findComponent("p_alu_monto_pago")).getValue().toString());

       // System.out.println("la procedencia  es -> "+b_procedencia);
        HSCatalogoDAO daoCatalogo = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");

        List<TbCatalogo> valor=daoCatalogo.selecionarFilaCatalogo(b_procedencia);
        this.setW_cate_codigo(valor.get(0).getCatValor());
       // System.out.println("el valor de la categoria -> "+valor.get(0).getCatValor());
       // System.out.println("el valor de la categoria -> "+valor.get(0).getCatDescripcionElemento());
    }

    public void Insertar(ActionEvent event) throws Exception {
        semestre_actual = "";
        HSAlumnoDAO dao = (HSAlumnoDAO) ServiceFinder.findBean("SpringHibernateDaoAlumno");
        if (b_id_alumno == 0) {
            if ((b_paterno_alumno.trim()).length() == 0 || (b_materno_alumno.trim()).length() == 0 || (b_nombre_alumno.trim()).length() == 0 || b_id_esp_ing_alumno <= 0 || b_id_plan_ing_alumno <= 0 || b_id_semestre_alumno <= 0 || b_id_plan_act_alumno <= 0 || b_id_esp_act_alumno <= 0) {
                this.setOncomplete("javascript:alert('Ingrese todos los datos Requeridos.')");
            } else {
                String codMax = dao.MaximoCodigo();
                int estado = 0;
                String codNuevo = "";
                if (Integer.parseInt(codMax) >= 9999 || codMax.equals("0000")) {
                    this.setOncomplete("javascript:alert('No se pudo ingresar al Alumno porque se ha agotado la numeracion de Codigos.Por favor Contactese con el area de Sistemas.')");
                    estado = 0;
                } else {
                    int codM = Integer.parseInt(codMax) + 1;
                    String c = codM + "";
                    String cd = "";
                    if (c.length() == 1) {
                        cd = "000" + c;
                    }
                    if (c.length() == 2) {
                        cd = "00" + c;
                    }
                    if (c.length() == 3) {
                        cd = "0" + c;
                    }
                    Date date = new Date();
                    String ano = String.format("%ty", date);
                    codNuevo = ano + "0" + b_id_esp_act_alumno + cd;
                    estado = 1;
                }
                if (estado == 1) {
                    AcAlumno Alu = new AcAlumno();
                    Alu.setId(b_id_alumno);
                    Alu.setAluCodigo(b_codigo_alumno);
                    Alu.setAluAppaterno(b_paterno_alumno);
                    Alu.setAluApmaterno(b_materno_alumno);
                    Alu.setAluNombres(b_nombre_alumno);
                    Alu.setAluDni(b_dni_alumno);
                    Alu.setAluMail(b_mail_alumno);
                    Alu.setAluTipo(b_id_tipo_alumno);
                    Alu.setAluModalidad(b_id_modalidad_alumno);
                    Alu.setAluEstado(b_id_estado_alumno);
                    Alu.setEspIdIngreso(b_id_esp_ing_alumno);
                    Alu.setSemId(b_id_semestre_alumno);
                    Alu.setPlanIdIngreso(b_id_plan_ing_alumno);
                    Alu.setPlanIdActual(b_id_plan_act_alumno);
                    AcEspecialidad esp = new AcEspecialidad();
                    esp.setId(b_id_esp_act_alumno);
                    Alu.setEsp(esp);
                    Alu.setAluFoto(null);
                    Alu.setAluActivo("1");
                    Alu.setAluProcedencia(b_procedencia);
                    Alu.setAluFamiliar(b_alu_familiar);
                    Alu.setAluTipoFamiliar(b_alu_tipo_familiar);
                    Alu.setAluFormaPago(b_alu_forma_pago);
                    Alu.setAluUnidad(b_alu_unidad);
                    Alu.setAluMontoPago(b_alu_monto_pago);
                    dao.insertarAlumno(Alu);
                    this.setOncomplete("javascript:alert('Se creo una Alumno correctamente.');Richfaces.hideModalPanel('mp')");
                }
            }
        } else {
            if ((b_paterno_alumno.trim()).length() == 0 || (b_materno_alumno.trim()).length() == 0 || (b_nombre_alumno.trim()).length() == 0 || b_id_esp_ing_alumno <= 0 || b_id_plan_ing_alumno <= 0 || b_id_semestre_alumno <= 0 || b_id_plan_act_alumno <= 0 || b_id_esp_act_alumno <= 0) {
                this.setOncomplete("javascript:alert('Ingrese todos los datos Requeridos.')");
            } else {
                HSAlumnoTarifaDAO daoAT = (HSAlumnoTarifaDAO) ServiceFinder.findBean("SpringHibernateDaoAlumnoTarifa");
                AcAlumno Alu = new AcAlumno();
                Alu.setId(b_id_alumno);
                Alu.setAluCodigo(b_codigo_alumno);
                Alu.setAluAppaterno(b_paterno_alumno);
                Alu.setAluApmaterno(b_materno_alumno);
                Alu.setAluNombres(b_nombre_alumno);
                Alu.setAluDni(b_dni_alumno);
                Alu.setAluMail(b_mail_alumno);
                Alu.setAluTipo(b_id_tipo_alumno);
                Alu.setAluModalidad(b_id_modalidad_alumno);
                Alu.setAluEstado(b_id_estado_alumno);
                Alu.setEspIdIngreso(b_id_esp_ing_alumno);
                List lista = dao.seleccionarUnAlumno(b_id_alumno);
                Alu.setAluFoto(((AcAlumno) lista.get(0)).getAluFoto());
                Alu.setSemId(b_id_semestre_alumno);
                Alu.setPlanIdIngreso(b_id_plan_ing_alumno);
                Alu.setPlanIdActual(b_id_plan_act_alumno);
                AcEspecialidad esp = new AcEspecialidad();
                esp.setId(b_id_esp_act_alumno);
                Alu.setEsp(esp);
                Alu.setAluActivo("1");
                Alu.setAluProcedencia(b_procedencia);
                Alu.setAluFamiliar(b_alu_familiar);
                Alu.setAluTipoFamiliar(b_alu_tipo_familiar);
                Alu.setAluFormaPago(b_alu_forma_pago);
                Alu.setAluUnidad(b_alu_unidad);
                Alu.setAluMontoPago(b_alu_monto_pago);
                dao.actualizarAlumno(Alu);
                this.setOncomplete("javascript:alert('Se Actualizaron los datos del Alumno correctamente.');Richfaces.hideModalPanel('mp')");
            }
        }
    }

    public void imagen(OutputStream out, Object data) throws IOException, Exception {
        HSAlumnoDAO dao = (HSAlumnoDAO) ServiceFinder.findBean("SpringHibernateDaoAlumno");
        List lista = dao.seleccionarUnAlumno((Integer) data);
        if (lista.size() != 0) {
            Blob imagen = ((AcAlumno) lista.get(0)).getAluFoto();
            BufferedImage bufferedImage = ImageIO.read(imagen.getBinaryStream());
            ImageIO.write(bufferedImage, "png", out);
        }
    }

    public SelectItem[] getComboTipoDocumento() throws Exception {
        HSCatalogoDAO dao = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        List<TbCatalogo> lista = dao.seleccionarCatalogo("027");
        comboTipoDocumento = new SelectItem[lista.size()];
        for (int i = 0; i < comboTipoDocumento.length; i++) {
            comboTipoDocumento[i] = new SelectItem(lista.get(i).getCatCodigoGrupo() + lista.get(i).getCatCodigoElemento(), lista.get(i).getCatDescripcionElemento());
        }
        return comboTipoDocumento;
    }

    public void setComboTipoDocumento(SelectItem[] comboTipoDocumento) {
        this.comboTipoDocumento = comboTipoDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public SelectItem[] getComboDistritoLima() throws Exception {
        if (comboDistritoLima == null) {
            HSUbigeoDAO dao = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
            List<TbDistrito> lista = dao.seleccionarDistrito("15", "1501");
            if (!lista.isEmpty()) {
                comboDistritoLima = new SelectItem[lista.size() + 1];
                comboDistritoLima[comboDistritoLima.length - 1] = new SelectItem("070101", "CALLAO");
                for (int i = 0; i < comboDistritoLima.length - 1; i++) {
                    comboDistritoLima[i] = new SelectItem(lista.get(i).getId(), lista.get(i).getDisDes());
                }
            } else {
                comboDistritoLima = new SelectItem[1];
                comboDistritoLima[0] = new SelectItem(0, "[Seleccione]");
            }
        }
        return comboDistritoLima;
    }

    public void setComboDistritoLima(SelectItem[] comboDistritoLima) {
        this.comboDistritoLima = comboDistritoLima;
    }

    public String getDistritoLima() {
        return distritoLima;
    }

    public void setDistritoLima(String distritoLima) {
        this.distritoLima = distritoLima;
    }

    public SelectItem[] getComboAniosPreparacion() throws Exception {
        return comboAniosPreparacion;
    }

    public void setComboAniosPreparacion(SelectItem[] comboAniosPreparacion) {
        this.comboAniosPreparacion = comboAniosPreparacion;
    }

    public String getAniosPreparacion() {

        return aniosPreparacion;
    }

    public void setAniosPreparacion(String aniosPreparacion) {
        this.aniosPreparacion = aniosPreparacion;
    }

    public SelectItem[] getComboMesesPreparacion() throws Exception {
        return comboMesesPreparacion;
    }

    public void setComboMesesPreparacion(SelectItem[] comboMesesPreparacion) {
        this.comboMesesPreparacion = comboMesesPreparacion;
    }

    public String getMesesPreparacion() {
        return mesesPreparacion;
    }

    public void setMesesPreparacion(String mesesPreparacion) {
        this.mesesPreparacion = mesesPreparacion;
    }

    public SelectItem[] getCombosexo() throws Exception {
        HSCatalogoDAO dao = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        List<TbCatalogo> lista = dao.seleccionarCatalogo("004");
        combosexo = new SelectItem[lista.size()];
        for (int i = 0; i < combosexo.length; i++) {
            combosexo[i] = new SelectItem(lista.get(i).getCatCodigoGrupo() + lista.get(i).getCatCodigoElemento(), lista.get(i).getCatDescripcionElemento());
        }
        return combosexo;
    }

    public void setCombosexo(SelectItem[] combosexo) {
        this.combosexo = combosexo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public SelectItem[] getComboDepartamentos() throws Exception {
        return comboDepartamentos;
    }

    public void setComboDepartamentos(SelectItem[] comboDepartamentos) {
        this.comboDepartamentos = comboDepartamentos;
    }

    public String getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(String departamentos) {
        this.departamentos = departamentos;
    }

    public SelectItem[] getComboProvincias() throws Exception {
        if (comboProvincias == null) {
            HSUbigeoDAO dao = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
            List<TbDistrito> lista = dao.seleccionarProvincia(this.getDepartamentos());
            if (!lista.isEmpty()) {
                comboProvincias = new SelectItem[lista.size()];
                for (int i = 0; i < comboProvincias.length; i++) {
                    comboProvincias[i] = new SelectItem(lista.get(i).getId(), lista.get(i).getDisDes());
                }
            } else {
                comboProvincias = new SelectItem[1];
                comboProvincias[0] = new SelectItem("000000", "[Seleccione]");
            }
        }
        return comboProvincias;
    }

    public void setComboProvincias(SelectItem[] comboProvincias) {
        this.comboProvincias = comboProvincias;
    }

    public String getProvincias() {
        return provincias;
    }

    public void setProvincias(String provincias) {
        this.provincias = provincias;
    }

    public SelectItem[] getComboDistrito() throws Exception {
        HSUbigeoDAO dao = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
        List<TbDistrito> lista = dao.seleccionarDistrito(this.getDepartamentos(), this.getProvincias());
        if (lista.size() != 0) {
            comboDistrito = new SelectItem[lista.size()];
            for (int i = 0; i < comboDistrito.length; i++) {
                comboDistrito[i] = new SelectItem(lista.get(i).getId(), lista.get(i).getDisDes());
            }
        } else {
            comboDistrito = new SelectItem[1];
            comboDistrito[0] = new SelectItem("000000", "[Seleccione]");
        }
        return comboDistrito;
    }

    public void setComboDistrito(SelectItem[] comboDistrito) {
        this.comboDistrito = comboDistrito;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public SelectItem[] getComboDepartamentos1() throws Exception {
        return comboDepartamentos1;
    }

    public void setComboDepartamentos1(SelectItem[] comboDepartamentos1) {
        this.comboDepartamentos1 = comboDepartamentos1;
    }

    public String getDepartamentos1() {
        return departamentos1;
    }

    public void setDepartamentos1(String departamentos1) {
        this.departamentos1 = departamentos1;
    }

    public SelectItem[] getComboProvincias1() throws Exception {
        HSUbigeoDAO dao = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
        List<TbDistrito> lista = dao.seleccionarProvincia(this.getDepartamentos1());
        if (lista.size() != 0) {
            comboProvincias1 = new SelectItem[lista.size()];
            for (int i = 0; i < comboProvincias1.length; i++) {
                comboProvincias1[i] = new SelectItem(lista.get(i).getId(), lista.get(i).getDisDes());
            }
        } else {
            comboProvincias1 = new SelectItem[1];
            comboProvincias1[0] = new SelectItem("000000", "[Seleccione]");
        }
        return comboProvincias1;
    }

    public void setComboProvincias1(SelectItem[] comboProvincias1) {
        this.comboProvincias1 = comboProvincias1;
    }

    public String getProvincias1() {
        return provincias1;
    }

    public void setProvincias1(String provincias1) {
        this.provincias1 = provincias1;
    }

    public SelectItem[] getComboDistrito1() throws Exception {
        HSUbigeoDAO dao = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
        List<TbDistrito> lista = dao.seleccionarDistrito(this.getDepartamentos1(), this.getProvincias1());
        if (lista.size() != 0) {
            comboDistrito1 = new SelectItem[lista.size()];
            for (int i = 0; i < comboDistrito1.length; i++) {
                comboDistrito1[i] = new SelectItem(lista.get(i).getId(), lista.get(i).getDisDes());
            }
        } else {
            comboDistrito1 = new SelectItem[1];
            comboDistrito1[0] = new SelectItem("000000", "[Seleccione]");
        }
        return comboDistrito1;
    }

    public void setComboDistrito1(SelectItem[] comboDistrito1) {
        this.comboDistrito1 = comboDistrito1;
    }

    public String getDistrito1() {
        return distrito1;
    }

    public void setDistrito1(String distrito1) {
        this.distrito1 = distrito1;
    }

    public SelectItem[] getComboAcademias() throws Exception {
        return comboAcademias;
    }

    public void setComboAcademias(SelectItem[] comboAcademias) {
        this.comboAcademias = comboAcademias;
    }

    public String getAcademias() {
        return academias;
    }

    public void setAcademias(String academias) {
        this.academias = academias;
    }

    public SelectItem[] getComboPreparacion() throws Exception {
        return comboPreparacion;
    }

    public void setComboPreparacion(SelectItem[] comboPreparacion) {
        this.comboPreparacion = comboPreparacion;
    }

    public String getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }

    public String getB_departamento_col() {
        return b_departamento_col;
    }

    public void setB_departamento_col(String b_departamento_col) {
        this.b_departamento_col = b_departamento_col;
    }

    public String getB_provincia_col() {
        return b_provincia_col;
    }

    public void setB_provincia_col(String b_provincia_col) {
        this.b_provincia_col = b_provincia_col;
    }

    public String getB_distrito_col() {
        return b_distrito_col;
    }

    public void setB_distrito_col(String b_distrito_col) {
        this.b_distrito_col = b_distrito_col;
    }

    public String getB_departamento_nac() {
        return b_departamento_nac;
    }

    public void setB_departamento_nac(String b_departamento_nac) {
        this.b_departamento_nac = b_departamento_nac;
    }

    public String getB_provincia_nac() {
        return b_provincia_nac;
    }

    public void setB_provincia_nac(String b_provincia_nac) {
        this.b_provincia_nac = b_provincia_nac;
    }

    public String getB_distrito_nac() {
        return b_distrito_nac;
    }

    public void setB_distrito_nac(String b_distrito_nac) {
        this.b_distrito_nac = b_distrito_nac;
    }

    public String getB_direccion_lima() {
        return b_direccion_lima;
    }

    public void setB_direccion_lima(String b_direccion_lima) {
        this.b_direccion_lima = b_direccion_lima;
    }

    public String getB_telefono_fijo() {
        return b_telefono_fijo;
    }

    public void setB_telefono_fijo(String b_telefono_fijo) {
        this.b_telefono_fijo = b_telefono_fijo;
    }

    public String getB_academiaOtros() {
        return b_academiaOtros;
    }

    public void setB_academiaOtros(String otros) {
        b_academiaOtros = otros;
    }

    public String getB_colegio() {
        return b_colegio;
    }

    public void setB_colegio(String b_colegio) {
        this.b_colegio = b_colegio;
    }

    public String getB_correo() {
        return b_correo;
    }

    public void setB_correo(String b_correo) {
        this.b_correo = b_correo;
    }

    public String getB_telefono_celular() {
        return b_telefono_celular;
    }

    public void setB_telefono_celular(String b_telefono_celular) {
        this.b_telefono_celular = b_telefono_celular;
    }

    public String getB_numeroDocumento() {
        return b_numeroDocumento;
    }

    public void setB_numeroDocumento(String documento) {
        b_numeroDocumento = documento;
    }

    public Date getB_nacimiento() {
        return b_nacimiento;
    }

    public void setB_nacimiento(Date b_nacimiento) {
        this.b_nacimiento = b_nacimiento;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public SelectItem[] getComboProcedencia() throws Exception {
         try {

            HSCatalogoDAO dao = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List lista = dao.seleccionarGrupoxValor(this.getW_cate_codigo());
            if (lista.size() != 0) {
                comboProcedencia = new SelectItem[lista.size() + 1];
                comboProcedencia[0] = new SelectItem("000000", "[Seleccione]");
                for (int i = 0; i < (comboProcedencia.length - 1); i++) {
                    comboProcedencia[i + 1] = new SelectItem(((TbCatalogo) lista.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista.get(i)).getCatDescripcionElemento());
                }
            } else {
                comboProcedencia = new SelectItem[1];
                comboProcedencia[0] = new SelectItem("000000", "[Seleccione]");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return comboProcedencia;
    }

    public void setComboProcedencia(SelectItem[] comboProcedencia) {
        this.comboProcedencia = comboProcedencia;
    }

    public SelectItem[] getComboTipoColegio() throws Exception {
        return comboTipoColegio;
    }

    public void setComboTipoColegio(SelectItem[] comboTipoColegio) {
        this.comboTipoColegio = comboTipoColegio;
    }

    public String getTipoColegio() {
        return tipoColegio;
    }

    public void setTipoColegio(String tipoColegio) {
        this.tipoColegio = tipoColegio;
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

    public String getDireccion_lima_new() {
        return direccion_lima_new;
    }

    public void setDireccion_lima_new(String direccion_lima_new) {
        this.direccion_lima_new = direccion_lima_new;
    }

    public String getDistrito_lima_new() {
        return distrito_lima_new;
    }

    public void setDistrito_lima_new(String distrito_lima_new) {
        this.distrito_lima_new = distrito_lima_new;
    }

    public String getColegio_direccion_new() {
        return colegio_direccion_new;
    }

    public void setColegio_direccion_new(String colegio_direccion_new) {
        this.colegio_direccion_new = colegio_direccion_new;
    }

    public String getLugar_nacimiento_new() {
        return lugar_nacimiento_new;
    }

    public void setLugar_nacimiento_new(String lugar_nacimiento_new) {
        this.lugar_nacimiento_new = lugar_nacimiento_new;
    }

    public void guardar() throws Exception {
        HSAlumnoDatoDAO dao = (HSAlumnoDatoDAO) ServiceFinder.findBean("HibernateSpringDaoTargetAlumnoDato");
        AcAlumnoDato aludat = new AcAlumnoDato();
        List alumnosDato = dao.seleccionarAlumnoDato(this.getB_id_alumno());
        if (alumnosDato.size() != 0) {
            aludat = ((AcAlumnoDato) alumnosDato.get(0));
        }
        aludat.setAluId(this.getB_id_alumno());
        aludat.setAluDatoResidenciaLima(this.getDistritoLima());
        aludat.setAluDatoUbicolegio(this.getDistrito1());
        aludat.setAluDatoAcademia(this.getAcademias());
        aludat.setAluDatoAcademiaOtro(this.getB_academiaOtros());
        aludat.setAluDatoAniosPrepa(this.getAniosPreparacion());
        aludat.setAluDatoMesesPrepa(this.getMesesPreparacion());
        aludat.setAluDatoColegio(this.getB_colegio());
        aludat.setAluDatoDireccion(this.getB_direccion_lima());
        aludat.setAluDatoEmail(this.getB_correo());
        aludat.setAluDatoFechaNacimiento(this.getB_nacimiento());
        aludat.setAluDatoLugarNacimiento(this.getDistrito());
        aludat.setAluDatoUbicolegio(this.getDistrito1());
        aludat.setAluDatoResidenciaLima(this.getDistritoLima());
        aludat.setAluDatoTdoc(this.getTipoDocumento());
        aludat.setAluDatoTelefonoC(this.getB_telefono_celular());
        aludat.setAluDatoTelefonoF(this.getB_telefono_fijo());
        aludat.setAluDatoTipoPrepa(this.getB_tipo());
        aludat.setAluDatoNrodoc(this.getB_numeroDocumento());
        aludat.setAluDatoSexo(this.getSexo());
        aludat.setAluDatoEstatal(this.getTipoColegio());
        aludat.setAluDatoActivo("1");
        aludat.setUsuModificacion(new Date());
        aludat.setUltFechaDoc(this.getW_ultima_fecha());

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession(false);
        bUsuario usu = (bUsuario) session1.getAttribute("usuario");
        aludat.setUsuId(usu.getId_usu());
        dao.eliminarDocumentos(b_id_alumno);
        AcAlumnoDocumento alu_doc = new AcAlumnoDocumento();

        for (int i = 0; i < documentos.length; i++) {
            alu_doc.setAludocActivo("1");
            AcAlumno alu = new AcAlumno();
            alu.setId(this.getB_id_alumno());
            alu_doc.setAlu(alu);
            alu_doc.setAludocDocumento(documentos[i]);
            dao.insertarDocumentos(alu_doc);
        }
        if (alumnosDato.size() == 0) {
            dao.insertarAlumnoDato(aludat);
        } else {
            dao.actualizarAlumnoDato(aludat);
        }


    }

    public void EditarFila2(ActionEvent event) {
        UIParameter id = (UIParameter) event.getComponent().findComponent("p_id");
        b_id_alumno = Integer.parseInt(id.getValue().toString());
        UIParameter paterno = (UIParameter) event.getComponent().findComponent("p_paterno");
        UIParameter materno = (UIParameter) event.getComponent().findComponent("p_materno");
        UIParameter nombre = (UIParameter) event.getComponent().findComponent("p_nombre");
        b_paterno_alumno = paterno.getValue().toString();
        b_materno_alumno = materno.getValue().toString();
        b_nombre_alumno = nombre.getValue().toString();
        academias = "";
        b_academiaOtros = "";
        aniosPreparacion = "";
        mesesPreparacion = "";
        b_colegio = "";
        b_direccion_lima = "";
        distritoLima = "";
        sexo = "";
        b_correo = "";
        tipoDocumento = "";
        b_telefono_celular = "";
        b_telefono_fijo = "";
        b_numeroDocumento = "";
        preparacion = "";
        b_nacimiento = null;
        departamentos = "000000";
        provincias = "000000";
        distrito = "000000";
        departamentos1 = "000000";
        provincias1 = "000000";
        distrito1 = "000000";
        tipoColegio = "";
        documentos = new String[1];
        documentos[0] = "000000";

        HSAlumnoDatoDAO dao = (HSAlumnoDatoDAO) ServiceFinder.findBean("HibernateSpringDaoTargetAlumnoDato");
        List lista = dao.seleccionarAlumnoDato(b_id_alumno);
        if (lista.size() != 0) {
            academias = ((AcAlumnoDato) lista.get(0)).getAluDatoAcademia().toString();
            b_academiaOtros = ((AcAlumnoDato) lista.get(0)).getAluDatoAcademiaOtro();
            aniosPreparacion = ((AcAlumnoDato) lista.get(0)).getAluDatoAniosPrepa();
            mesesPreparacion = ((AcAlumnoDato) lista.get(0)).getAluDatoMesesPrepa();
            b_colegio = ((AcAlumnoDato) lista.get(0)).getAluDatoColegio();
            b_direccion_lima = ((AcAlumnoDato) lista.get(0)).getAluDatoDireccion();
            distritoLima = ((AcAlumnoDato) lista.get(0)).getAluDatoResidenciaLima();
            sexo = ((AcAlumnoDato) lista.get(0)).getAluDatoSexo();
            b_correo = ((AcAlumnoDato) lista.get(0)).getAluDatoEmail();
            tipoDocumento = ((AcAlumnoDato) lista.get(0)).getAluDatoTdoc().toString();
            b_telefono_celular = ((AcAlumnoDato) lista.get(0)).getAluDatoTelefonoC();
            b_telefono_fijo = ((AcAlumnoDato) lista.get(0)).getAluDatoTelefonoF();
            b_numeroDocumento = ((AcAlumnoDato) lista.get(0)).getAluDatoNrodoc();
            preparacion = ((AcAlumnoDato) lista.get(0)).getAluDatoTipoPrepa();
            b_nacimiento = ((AcAlumnoDato) lista.get(0)).getAluDatoFechaNacimiento();
            departamentos = ((AcAlumnoDato) lista.get(0)).getAluDatoLugarNacimiento().substring(0, 2) + "0000";
            provincias = ((AcAlumnoDato) lista.get(0)).getAluDatoLugarNacimiento().substring(0, 4) + "00";
            distrito = ((AcAlumnoDato) lista.get(0)).getAluDatoLugarNacimiento();
            departamentos1 = ((AcAlumnoDato) lista.get(0)).getAluDatoUbicolegio().substring(0, 2) + "0000";
            provincias1 = ((AcAlumnoDato) lista.get(0)).getAluDatoUbicolegio().substring(0, 4) + "00";
            distrito1 = ((AcAlumnoDato) lista.get(0)).getAluDatoUbicolegio();
            tipoColegio = ((AcAlumnoDato) lista.get(0)).getAluDatoEstatal();
            w_ultima_fecha=((AcAlumnoDato) lista.get(0)).getUltFechaDoc();

            List lista_documentos = dao.seleccionarDocumentos(b_id_alumno);
            if (lista_documentos.size() != 0) {
                documentos = new String[lista_documentos.size()];
            }
            for (int i = 0; i < lista_documentos.size(); i++) {
                documentos[i] = ((AcAlumnoDocumento) lista_documentos.get(i)).getAludocDocumento();
            }
        }

    }

    public void InsertarEP(ActionEvent event) {
        HSAlumnoTarifaDAO daoAT = (HSAlumnoTarifaDAO) ServiceFinder.findBean("SpringHibernateDaoAlumnoTarifa");
        UIParameter id = (UIParameter) event.getComponent().findComponent("p_id");
        b_id_alumno = Integer.parseInt(id.getValue().toString());
        String numReg = daoAT.seleccionarEstructuraPagosAlumno(b_id_alumno);
        if (numReg.equals("1")) {
            this.setOncompleteGEP("javascript:alert('Estructura creada anteriormente')");
        } else {
            UIParameter id_esp_act = (UIParameter) event.getComponent().findComponent("p_id_esp_act");
            b_id_esp_act_alumno = Integer.parseInt(id_esp_act.getValue().toString());
            HSSemestreDAO daoSem = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
            int sem_id = Integer.parseInt(daoSem.seleccionarSemestreAct());

            HSAlumnoDAO dao = (HSAlumnoDAO) ServiceFinder.findBean("SpringHibernateDaoAlumno");
            List lista = dao.seleccionarAlumnoEstPagoUnico(b_id_alumno);

            HSEstructuraPagoDAO daoEst = (HSEstructuraPagoDAO) ServiceFinder.findBean("SpringHibernateDaoEstructuraPagos");


            List estpagos = daoEst.seleccionarEstructuraPagosEspSem(b_id_esp_act_alumno, sem_id);
            Set<AdEstructuraPagosDetalle> estdet = new LinkedHashSet<AdEstructuraPagosDetalle>();
            estdet = ((AdEstructuraPagos) estpagos.get(0)).getAdEstructuraPagosDetalles();
            List list = Collections.synchronizedList(new LinkedList(estdet));
            AdAlumnoTarifa alTa;
            bEstructuraPago al;
            if (lista.size() != 0) {
                for (int i = 0; i < lista.size(); i++) {
                    if (list.size() != 0) {
                        for (int j = 0; j < list.size(); j++) {
                            alTa = new AdAlumnoTarifa();
                            //alTa.setId(x);
                            AdEstructuraPagosDetalle estpagDet = new AdEstructuraPagosDetalle();
                            estpagDet.setId(((AdEstructuraPagosDetalle) list.get(j)).getId());
                            alTa.setEstpagdet(estpagDet);
                            AcAlumno alu = new AcAlumno();
                            alu.setId(((AcAlumno) lista.get(i)).getId());
                            alTa.setAlu(alu);
                            alTa.setAlutarMonto(((AdEstructuraPagosDetalle) list.get(j)).getEstpagdetMonto());
                            alTa.setAlutarMontoCopy(((AdEstructuraPagosDetalle) list.get(j)).getEstpagdetMonto());
                            AdConceptoPago con = new AdConceptoPago();
                            con.setId(((AdEstructuraPagosDetalle) list.get(j)).getConpagId());
                            alTa.setConpag(con);
                            alTa.setAlutarFechaPago(((AdEstructuraPagosDetalle) list.get(j)).getEstpagdetFechaPago());
                            alTa.setAlutarFechaProrroga(((AdEstructuraPagosDetalle) list.get(j)).getEstpagdetFechaPago());
                            alTa.setAlutarEstado("0");
                            alTa.setAlutarActivo("1");
                            listaPubli.add(alTa);
                        }
                    }
                }
                HSAlumnoTarifaDAO daoTarifa = (HSAlumnoTarifaDAO) ServiceFinder.findBean("SpringHibernateDaoAlumnoTarifa");
                daoTarifa.insertarTotal(listaPubli);
                this.setOncompleteGEP("javascript:alert('La estructura fue creada correctamente')");
            }
        }
    }

    public String getImagenGEP() {
        return imagenGEP;
    }

    public void setImagenGEP(String imagenGEP) {
        this.imagenGEP = imagenGEP;
    }

    public String getOncompleteGEP() {
        return oncompleteGEP;
    }

    public void setOncompleteGEP(String oncompleteGEP) {
        this.oncompleteGEP = oncompleteGEP;
    }

    public boolean isDisabledGEP() {
        return disabledGEP;
    }

    public void setDisabledGEP(boolean disabledGEP) {
        this.disabledGEP = disabledGEP;
    }

    public List<AdAlumnoTarifa> getListaPubli() {
        return listaPubli;
    }

    public void setListaPubli(List<AdAlumnoTarifa> listaPubli) {
        this.listaPubli = listaPubli;
    }

    public String getB_procedencia_id() {
        return b_procedencia_id;
    }

    public void setB_procedencia_id(String b_procedencia_id) {
        this.b_procedencia_id = b_procedencia_id;
    }

    public List getListaAsistencia() {
        return listaAsistencia;
    }

    public void setListaAsistencia(List listaAsistencia) {
        this.listaAsistencia = listaAsistencia;
    }

    public String getA_lunes() {
        return a_lunes;
    }

    public void setA_lunes(String a_lunes) {
        this.a_lunes = a_lunes;
    }

    public String getA_martes() {
        return a_martes;
    }

    public void setA_martes(String a_martes) {
        this.a_martes = a_martes;
    }

    public String getA_miercoles() {
        return a_miercoles;
    }

    public void setA_miercoles(String a_miercoles) {
        this.a_miercoles = a_miercoles;
    }

    public String getA_jueves() {
        return a_jueves;
    }

    public void setA_jueves(String a_jueves) {
        this.a_jueves = a_jueves;
    }

    public String getA_viernes() {
        return a_viernes;
    }

    public void setA_viernes(String a_viernes) {
        this.a_viernes = a_viernes;
    }

    public String getA_sabado() {
        return a_sabado;
    }

    public void setA_sabado(String a_sabado) {
        this.a_sabado = a_sabado;
    }

    public String getA_domingo() {
        return a_domingo;
    }

    public void setA_domingo(String a_domingo) {
        this.a_domingo = a_domingo;
    }

    public void MostrarAsistencia(ActionEvent event) throws ParseException {
        b_id_alumno = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_id")).getValue().toString());
        b_codigo_alumno = ((UIParameter) event.getComponent().findComponent("p_codigo")).getValue().toString();
        b_paterno_alumno = ((UIParameter) event.getComponent().findComponent("p_paterno")).getValue().toString();
        b_materno_alumno = ((UIParameter) event.getComponent().findComponent("p_materno")).getValue().toString();
        b_nombre_alumno = ((UIParameter) event.getComponent().findComponent("p_nombre")).getValue().toString();
        Date date_t = new Date();
        GregorianCalendar date = new GregorianCalendar(date_t.getYear(), date_t.getMonth() + 1, date_t.getDate());
        mes = date.get(GregorianCalendar.MONTH + 1);
        anio = date.get(GregorianCalendar.YEAR) + 1900 - 2;
        evaluaAsistencia();

    }

    public void evaluaAsistencia() throws ParseException {
        listaAsistencia = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date desde = sdf.parse("1" + "/" + mes + "/" + anio);
        Date hasta = sdf.parse("1" + "/" + mes + "/" + anio);
        GregorianCalendar date_desde = new GregorianCalendar(desde.getYear(), desde.getMonth(), desde.getDate());
        String A[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre", "Noviembre", "Diciembre"};
        int mex = date_desde.get(GregorianCalendar.MONTH);
        b_mes = A[mex];
        b_anio = "" + (date_desde.get(GregorianCalendar.YEAR) + 1900);
        desde = sdf.parse(1 + "/" + mes + "/" + anio);
        hasta = sdf.parse(1 + "/" + (mes + 1) + "/" + anio);
        List Asis = new ArrayList();
        HSAsistenciaDAO asis = (HSAsistenciaDAO) ServiceFinder.findBean("SpringHibernateDaoAsistencia");
        Asis = asis.seleccionarAsistencia(b_codigo_alumno, desde, hasta);
        int dia = 0;
        int semana = 0;
        bAlumno alu;
        for (int k = 1; k <= 6; k++) {
            alu = new bAlumno(0);
            llenarCalendario(k, date_desde, alu);
            for (int i = 0; i < Asis.size(); i++) {
                Date temp = sdf1.parse(((TbAsistencia) Asis.get(i)).getAsisDia().toString());
                GregorianCalendar fecha = new GregorianCalendar(temp.getYear() + 1900, temp.getMonth(), temp.getDate());
                dia = fecha.get(Calendar.DAY_OF_WEEK);
                semana = fecha.get(Calendar.WEEK_OF_MONTH);
                if (dia == 2 && semana == k) {
                    alu.setA_lunes(temp.getDate() + "");
                    alu.setH_lunes("Ingreso:" + temp.getHours() + ":" + temp.getMinutes() + ":" + temp.getSeconds());
                    alu.setE_lunes("color: blue;");
                } else if (dia == 3 && semana == k) {
                    alu.setA_martes(temp.getDate() + "");
                    alu.setH_martes("Ingreso:" + temp.getHours() + ":" + temp.getMinutes() + ":" + temp.getSeconds());
                    alu.setE_martes("color: blue;");
                } else if (dia == 4 && semana == k) {
                    alu.setA_miercoles(temp.getDate() + "");
                    alu.setH_miercoles("Ingreso:" + temp.getHours() + ":" + temp.getMinutes() + ":" + temp.getSeconds());
                    alu.setE_miercoles("color: blue;");
                } else if (dia == 5 && semana == k) {
                    alu.setA_jueves(temp.getDate() + "");
                    alu.setH_jueves("Ingreso:" + temp.getHours() + ":" + temp.getMinutes() + ":" + temp.getSeconds());
                    alu.setE_jueves("color: blue;");
                } else if (dia == 6 && semana == k) {
                    alu.setA_viernes(temp.getDate() + "");
                    alu.setH_viernes("Ingreso:" + temp.getHours() + ":" + temp.getMinutes() + ":" + temp.getSeconds());
                    alu.setE_viernes("color: blue;");
                } else if (dia == 7 && semana == k) {
                    alu.setA_sabado(temp.getDate() + "");
                    alu.setH_sabado("Ingreso:" + temp.getHours() + ":" + temp.getMinutes() + ":" + temp.getSeconds());
                    alu.setE_sabado("color: blue;");
                } else if (dia == 1 && semana == k) {
                    alu.setA_domingo(temp.getDate() + "");
                    alu.setH_domingo("Ingreso:" + temp.getHours() + ":" + temp.getMinutes() + ":" + temp.getSeconds());
                    alu.setE_domingo("color: blue;");
                }
            }
            listaAsistencia.add(alu);
        }
    }

    public void llenarCalendario(int nro_semana, GregorianCalendar date_desde, bAlumno semana) {
        GregorianCalendar fecha = new GregorianCalendar();
        int meses = date_desde.get(Calendar.MONTH);
        for (int i = 0; i <= 31; i++) {
            int dia = fecha.get(Calendar.DAY_OF_WEEK);
            int n_semana = fecha.get(Calendar.WEEK_OF_MONTH);
            int n_mes = fecha.get(Calendar.MONTH);
            if (dia == 2 && n_semana == nro_semana && meses == n_mes) {
                semana.setA_lunes("" + fecha.get(Calendar.DATE));
            } else if (dia == 3 && n_semana == nro_semana && meses == n_mes) {
                semana.setA_martes("" + fecha.get(Calendar.DATE));
            } else if (dia == 4 && n_semana == nro_semana && meses == n_mes) {
                semana.setA_miercoles("" + fecha.get(Calendar.DATE));
            } else if (dia == 5 && n_semana == nro_semana && meses == n_mes) {
                semana.setA_jueves("" + fecha.get(Calendar.DATE));
            } else if (dia == 6 && n_semana == nro_semana && meses == n_mes) {
                semana.setA_viernes("" + fecha.get(Calendar.DATE));
            } else if (dia == 7 && n_semana == nro_semana && meses == n_mes) {
                semana.setA_sabado("" + fecha.get(Calendar.DATE));
            } else if (dia == 1 && n_semana == nro_semana && meses == n_mes) {
                semana.setA_domingo("" + fecha.get(Calendar.DATE));
                semana.setE_domingo("color: red;");
            }
            fecha = new GregorianCalendar(date_desde.get(Calendar.YEAR) + 1900, date_desde.get(Calendar.MONTH), date_desde.get(Calendar.DATE) + i);
        }
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void aumentaMes(ActionEvent action) {
        mes++;
    }

    public void disminuyeMes(ActionEvent action) {
        mes--;
    }

    public void aumentaAnio(ActionEvent action) {
        anio++;
    }

    public void disminuyeAnio(ActionEvent action) {
        anio--;
    }

    public String getB_mes() {
        return b_mes;
    }

    public void setB_mes(String b_mes) {
        this.b_mes = b_mes;
    }

    public String getB_anio() {
        return b_anio;
    }

    public void setB_anio(String b_anio) {
        this.b_anio = b_anio;
    }

    public String getH_lunes() {
        return h_lunes;
    }

    public void setH_lunes(String h_lunes) {
        this.h_lunes = h_lunes;
    }

    public String getH_martes() {
        return h_martes;
    }

    public void setH_martes(String h_martes) {
        this.h_martes = h_martes;
    }

    public String getH_miercoles() {
        return h_miercoles;
    }

    public void setH_miercoles(String h_miercoles) {
        this.h_miercoles = h_miercoles;
    }

    public String getH_jueves() {
        return h_jueves;
    }

    public void setH_jueves(String h_jueves) {
        this.h_jueves = h_jueves;
    }

    public String getH_viernes() {
        return h_viernes;
    }

    public void setH_viernes(String h_viernes) {
        this.h_viernes = h_viernes;
    }

    public String getH_sabado() {
        return h_sabado;
    }

    public void setH_sabado(String h_sabado) {
        this.h_sabado = h_sabado;
    }

    public String getH_domingo() {
        return h_domingo;
    }

    public void setH_domingo(String h_domingo) {
        this.h_domingo = h_domingo;
    }

    public void imprimirAsistencia(ActionEvent event) {
        b_id_alumno = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_id")).getValue().toString());
        b_codigo_alumno = ((UIParameter) event.getComponent().findComponent("p_codigo")).getValue().toString();
    }

    public void reporte(OutputStream out, Object data) throws IOException, Exception {
        HSAlumnoDAO dao = (HSAlumnoDAO) ServiceFinder.findBean("SpringHibernateDaoAlumno");
        InputStream buffered = dao.imprimirAsistencia();
        int size = buffered.available();
        byte[] pdf = new byte[size];
        buffered.read(pdf);
        buffered.close();
        out.write(pdf);
    }

    public String getE_lunes() {
        return e_lunes;
    }

    public void setE_lunes(String e_lunes) {
        this.e_lunes = e_lunes;
    }

    public String getE_martes() {
        return e_martes;
    }

    public void setE_martes(String e_martes) {
        this.e_martes = e_martes;
    }

    public String getE_miercoles() {
        return e_miercoles;
    }

    public void setE_miercoles(String e_miercoles) {
        this.e_miercoles = e_miercoles;
    }

    public String getE_jueves() {
        return e_jueves;
    }

    public void setE_jueves(String e_jueves) {
        this.e_jueves = e_jueves;
    }

    public String getE_viernes() {
        return e_viernes;
    }

    public void setE_viernes(String e_viernes) {
        this.e_viernes = e_viernes;
    }

    public String getE_sabado() {
        return e_sabado;
    }

    public void setE_sabado(String e_sabado) {
        this.e_sabado = e_sabado;
    }

    public String getE_domingo() {
        return e_domingo;
    }

    public void setE_domingo(String e_domingo) {
        this.e_domingo = e_domingo;
    }

    public SelectItem[] getComboFormaPago() {
        return comboFormaPago;
    }

    public void setComboFormaPago(SelectItem[] comboFormaPago) {
        this.comboFormaPago = comboFormaPago;
    }

    public SelectItem[] getComboTipoFamiliar() {
        return comboTipoFamiliar;
    }

    public void setComboTipoFamiliar(SelectItem[] comboTipoFamiliar) {
        this.comboTipoFamiliar = comboTipoFamiliar;
    }

    public SelectItem[] getComboUnidad() {
        return comboUnidad;
    }

    public void setComboUnidad(SelectItem[] comboUnidad) {
        this.comboUnidad = comboUnidad;
    }

    public float getB_alu_monto_pago() {
        return b_alu_monto_pago;
    }

    public void setB_alu_monto_pago(float b_alu_monto_pago) {
        this.b_alu_monto_pago = b_alu_monto_pago;
    }
}