/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.administrativa.managedBeans;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.uch.academica.hibernateSpringDao.HSAlumnoDAO;
import net.uch.administrativa.hibernateSpringDao.HSEdicionEstructPagoDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcAlumno;
import net.uch.mapping.AcSemestre;
import net.uch.mapping.AdAlumnoTarifa;
import net.uch.mapping.AdConceptoPago;
import net.uch.mapping.AdEstructuraPagos;
import net.uch.mapping.AdEstructuraPagosDetalle;
import net.uch.mapping.AdPago;
import net.uch.mapping.AdProrroga;
import net.uch.mapping.TbUsuario;
import net.uch.tablaSistema.hibernateSpringDao.HSSemestreDAO;
import net.uch.tablaSistema.managedBeans.bUsuario;
import net.uch.util.Print;

/**
 *
 * @author cesardl
 */
public class bEdicionEstructPago {

    private int b_alu_id;
    private String b_codigo;
    private String b_paterno;
    private String b_materno;
    private String b_nombre;
    private String b_alumno;
    private String b_especialidad;
    private String b_semestreIngreso;
    private String b_semestreEstructura;
    private int b_semestreEstructura_id;
    private String b_sub_concepto;
    private float b_sub_monto;
    private int b_sub_alutar_id;
    private String b_sub_alutar_estado;
    private Date b_sub_fecha_pago;
    private Date b_sub_fecha_prorroga;
    private String b_sub_img_candado;
    private String b_sub_edit_disabled;
    private String b_sub_img_klipper;
    private String b_sub_img_calendar;
    private String b_sub_title;
    private String b_sub_msj;
    private List<Integer> b_alutarIds;
    private int b_alutarId;
    //Parametro de busqueda
    private SelectItem parametroSemestres[];
    private int parametroSemestre;
    //Input del modal nuevo
    private String modal_alumno;
    private int modal_Especialidad;
    private int modal_SemIngreso;
    private int modal_id;
    private SelectItem modalSemestres[];
    private int modalSemestre;
    private SelectItem modalPlantillas[];
    private int modalPlantilla;
    //Input del modal editar
    private Date modal_pago;
    private Date modal_prorroga;
    private float modal_monto;
    //Para el modal de la edicion d prorroga
    private int pro_v_alutarID;
    private String pro_v_desc_concepto;
    private Date pro_v_fecha_pago;
    private Date pro_v_fecha_prorroga;
    private Date pro_param_fecha;
    private String pro_param_coment;
    private String pro_img_quitar;
    private String pro_img_disable;
    private String pro_numero;
    private Date pro_fecha;
    private String pro_coment;
    private int pro_pos;
    private int pro_id;
    private Timestamp pro_creacion;
    private List<bEdicionEstructPago> proListaProrrogas;
    private List<bEdicionEstructPago> proListaProrrogasEliminados;
    //Para el reporte
    private String rep_alu_codigo;
    private int rep_sem_id;
    //Variables para los efectos
    private String oncomplete;
    private String imagenVer;
    private String estadoVer;
    private String sugAlumno;
    private String sugCodigo;
    private boolean mostrar = false;
    private SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    private List<bEdicionEstructPago> listaEstructura;
    private List<bEdicionEstructPago> subListaEstructura;
    private int tmpAlutar = 0;
    private int tmpEstado = 0;
    private int tmpCompBloq = 0;
    private static Timestamp tfNow;

    public bEdicionEstructPago() {
        tfNow = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

    public int getB_alu_id() {
        return b_alu_id;
    }

    public void setB_alu_id(int b_alu_id) {
        this.b_alu_id = b_alu_id;
    }

    public String getB_alumno() {
        this.setB_alumno(this.getB_paterno() + " " + this.getB_materno() + ", " + this.getB_nombre());
        return b_alumno;
    }

    public void setB_alumno(String b_alumno) {
        this.b_alumno = b_alumno;
    }

    public String getB_especialidad() {
        return b_especialidad;
    }

    public void setB_especialidad(String b_especialidad) {
        this.b_especialidad = b_especialidad;
    }

    public String getB_codigo() {
        return b_codigo;
    }

    public void setB_codigo(String b_codigo) {
        this.b_codigo = b_codigo;
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

    public String getB_paterno() {
        return b_paterno;
    }

    public void setB_paterno(String b_paterno) {
        this.b_paterno = b_paterno;
    }

    public String getB_semestreEstructura() {
        return b_semestreEstructura;
    }

    public void setB_semestreEstructura(String b_semestreEstructura) {
        this.b_semestreEstructura = b_semestreEstructura;
    }

    public String getB_sub_concepto() {
        return b_sub_concepto;
    }

    public void setB_sub_concepto(String b_sub_concepto) {
        this.b_sub_concepto = b_sub_concepto;
    }

    public float getB_sub_monto() {
        return b_sub_monto;
    }

    public void setB_sub_monto(float b_sub_monto) {
        this.b_sub_monto = b_sub_monto;
    }

    public int getB_sub_alutar_id() {
        return b_sub_alutar_id;
    }

    public void setB_sub_alutar_id(int b_sub_alutar_id) {
        this.b_sub_alutar_id = b_sub_alutar_id;
    }

    public int getB_alutarId() {
        return b_alutarId;
    }

    public void setB_alutarId(int b_alutarId) {
        this.b_alutarId = b_alutarId;
    }
    
    

    public String getB_sub_alutar_estado() {
        return b_sub_alutar_estado;
    }

    public void setB_sub_alutar_estado(String b_sub_alutar_estado) {
        this.b_sub_alutar_estado = b_sub_alutar_estado;
    }

    public Date getB_sub_fecha_pago() {
        return b_sub_fecha_pago;
    }

    public void setB_sub_fecha_pago(Date b_sub_fecha_pago) {
        this.b_sub_fecha_pago = b_sub_fecha_pago;
    }

    public Date getB_sub_fecha_prorroga() {
        return b_sub_fecha_prorroga;
    }

    public void setB_sub_fecha_prorroga(Date b_sub_fecha_prorroga) {
        this.b_sub_fecha_prorroga = b_sub_fecha_prorroga;
    }

    public String getB_sub_img_candado() {
        return b_sub_img_candado;
    }

    public void setB_sub_img_candado(String b_sub_img_candado) {
        this.b_sub_img_candado = b_sub_img_candado;
    }

    public String getB_sub_edit_disabled() {
        return b_sub_edit_disabled;
    }

    public void setB_sub_edit_disabled(String b_sub_edit_disabled) {
        this.b_sub_edit_disabled = b_sub_edit_disabled;
    }

    public String getB_sub_img_klipper() {
        return b_sub_img_klipper;
    }

    public void setB_sub_img_klipper(String b_sub_img_klipper) {
        this.b_sub_img_klipper = b_sub_img_klipper;
    }

    public String getB_sub_img_calendar() {
        return b_sub_img_calendar;
    }

    public void setB_sub_img_calendar(String b_sub_img_calendar) {
        this.b_sub_img_calendar = b_sub_img_calendar;
    }

    public String getB_sub_title() {
        return b_sub_title;
    }

    public void setB_sub_title(String b_sub_title) {
        this.b_sub_title = b_sub_title;
    }

    public String getB_sub_msj() {
        return b_sub_msj;
    }

    public void setB_sub_msj(String b_sub_msj) {
        this.b_sub_msj = b_sub_msj;
    }

    public List<Integer> getB_alutarIds() {
        return b_alutarIds;
    }

    public void setB_alutarIds(List<Integer> b_alutarIds) {
        this.b_alutarIds = b_alutarIds;
    }

    public String getB_semestreIngreso() {
        return b_semestreIngreso;
    }

    public void setB_semestreIngreso(String b_semestreIngreso) {
        this.b_semestreIngreso = b_semestreIngreso;
    }

    public int getB_semestreEstructura_id() {
        return b_semestreEstructura_id;
    }

    public void setB_semestreEstructura_id(int b_semestreEstructura_id) {
        this.b_semestreEstructura_id = b_semestreEstructura_id;
    }

    public List<bEdicionEstructPago> getListaEstructura() {
        return listaEstructura;
    }

    public void setListaEstructura(List<bEdicionEstructPago> listaEstructura) {
        this.listaEstructura = listaEstructura;
    }

    public List<bEdicionEstructPago> getSubListaEstructura() {
        return subListaEstructura;
    }

    public void setSubListaEstructura(List<bEdicionEstructPago> subListaEstructura) {
        this.subListaEstructura = subListaEstructura;
    }

    public SelectItem[] getParametroSemestres() {
        try {
            HSSemestreDAO dao = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
            List l = dao.seleccionarSemestreActivo();
            parametroSemestres = new SelectItem[l.size()];

            for (int i = 0; i < l.size(); i++) {
                Object obj = l.get(i);
                AcSemestre tmp = (AcSemestre) obj;
                parametroSemestres[i] = new SelectItem(tmp.getId(), tmp.getSemNombre());
            }
        } catch (Exception e) {
            parametroSemestres = new SelectItem[1];
            parametroSemestres[0] = new SelectItem(0, "[Seleccione]");
        }
        return parametroSemestres;
    }

    public void setParametroSemestres(SelectItem[] parametroSemestres) {
        this.parametroSemestres = parametroSemestres;
    }

    public int getParametroSemestre() {
        return parametroSemestre;
    }

    public void setParametroSemestre(int parametroSemestre) {
        this.parametroSemestre = parametroSemestre;
    }

    public int getModalPlantilla() {
        return modalPlantilla;
    }

    public void setModalPlantilla(int modalPlantilla) {
        this.modalPlantilla = modalPlantilla;
    }

    public float getModal_monto() {
        return modal_monto;
    }

    public void setModal_monto(float modal_monto) {
        this.modal_monto = modal_monto;
    }

    public int getPro_pos() {
        return pro_pos;
    }

    public void setPro_pos(int pro_pos) {
        this.pro_pos = pro_pos;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public Timestamp getPro_creacion() {
        return pro_creacion;
    }

    public void setPro_creacion(Timestamp pro_creacion) {
        this.pro_creacion = pro_creacion;
    }

    public Date getPro_fecha() {
        return pro_fecha;
    }

    public void setPro_fecha(Date pro_fecha) {
        this.pro_fecha = pro_fecha;
    }

    public String getPro_coment() {
        return pro_coment;
    }

    public void setPro_coment(String pro_coment) {
        this.pro_coment = pro_coment;
    }

    public String getPro_param_coment() {
        return pro_param_coment;
    }

    public void setPro_param_coment(String pro_param_coment) {
        this.pro_param_coment = pro_param_coment;
    }

    public String getPro_img_disable() {
        return pro_img_disable;
    }

    public void setPro_img_disable(String pro_img_disable) {
        this.pro_img_disable = pro_img_disable;
    }

    public String getPro_img_quitar() {
        return pro_img_quitar;
    }

    public void setPro_img_quitar(String pro_img_quitar) {
        this.pro_img_quitar = pro_img_quitar;
    }

    public int getTmpAlutar() {
        return tmpAlutar;
    }

    public void setTmpAlutar(int tmpAlutar) {
        this.tmpAlutar = tmpAlutar;
    }

    public int getTmpCompBloq() {
        return tmpCompBloq;
    }

    public void setTmpCompBloq(int tmpCompBloq) {
        this.tmpCompBloq = tmpCompBloq;
    }

    public int getTmpEstado() {
        return tmpEstado;
    }

    public void setTmpEstado(int tmpEstado) {
        this.tmpEstado = tmpEstado;
    }

    public void salvaCompromiso(ActionEvent event) {
        UIParameter alutarId = (UIParameter) event.getComponent().findComponent("alutar_id");
        UIParameter alutarPago = (UIParameter) event.getComponent().findComponent("alutar_pago");
        UIParameter alutarProrroga = (UIParameter) event.getComponent().findComponent("alutar_prorroga");
        UIParameter estDesc = (UIParameter) event.getComponent().findComponent("est_desc");

        this.setPro_v_alutarID(Integer.parseInt(String.valueOf(alutarId.getValue())));
        this.setPro_v_fecha_pago((Date) alutarPago.getValue());
        this.setPro_v_fecha_prorroga((Date) alutarProrroga.getValue());
        this.setPro_v_desc_concepto(String.valueOf(estDesc.getValue()));

        proListaProrrogas = new ArrayList<bEdicionEstructPago>();

        HSEdicionEstructPagoDAO dao =
                (HSEdicionEstructPagoDAO) ServiceFinder.findBean(
                "SpringHibernateDaoEdicionEstructura");

        List l = dao.listaProrrogas(this.getPro_v_alutarID());
        this.setTmpCompBloq(0);
        for (int i = 0; i < l.size(); i++) {
            AdProrroga prorroga = (AdProrroga) l.get(i);
            bEdicionEstructPago tmp = new bEdicionEstructPago();
            tmp.setPro_pos(i);
            tmp.setPro_numero(prorroga.getProNumero());
            tmp.setPro_fecha(prorroga.getProFecha());
            tmp.setPro_id(prorroga.getProId());
            tmp.setPro_creacion(prorroga.getProCreacion());
            tmp.setPro_coment(prorroga.getProComentario());

            String profecha = formatDate.format(prorroga.getProFecha());
            String hoy = formatDate.format(new Date());
            if (prorroga.getProCreacion() != null) {
                String creac = formatDate.format(prorroga.getProCreacion());
                if (creac.equalsIgnoreCase(hoy)) {
                    tmp.setPro_img_disable("false");
                    tmp.setPro_img_quitar("/Imagenes/actions/edit_remove.png");
                } else {
                    tmp.setPro_img_disable("true");
                    tmp.setPro_img_quitar("/Imagenes/actions/edit_remove_gris.png");
                    this.setTmpCompBloq(this.getTmpCompBloq() + 1);
                }
            } else {
                if (profecha.compareTo(hoy) >= 0) {
                    tmp.setPro_img_disable("false");
                    tmp.setPro_img_quitar("/Imagenes/actions/edit_remove.png");
                } else {
                    tmp.setPro_img_disable("true");
                    tmp.setPro_img_quitar("/Imagenes/actions/edit_remove_gris.png");
                    this.setTmpCompBloq(this.getTmpCompBloq() + 1);
                }
            }
            proListaProrrogas.add(tmp);
        }
    }

    public List<bEdicionEstructPago> getProListaProrrogas() {
        return proListaProrrogas;
    }

    public void setProListaProrrogas(List<bEdicionEstructPago> proListaProrrogas) {
        this.proListaProrrogas = proListaProrrogas;
    }

    public List<bEdicionEstructPago> getProListaProrrogasEliminados() {
        return proListaProrrogasEliminados;
    }

    public void setProListaProrrogasEliminados(List<bEdicionEstructPago> proListaProrrogasEliminados) {
        this.proListaProrrogasEliminados = proListaProrrogasEliminados;
    }

    public int getPro_v_alutarID() {
        return pro_v_alutarID;
    }

    public void setPro_v_alutarID(int pro_v_alutarID) {
        this.pro_v_alutarID = pro_v_alutarID;
    }

    public String getPro_v_desc_concepto() {
        return pro_v_desc_concepto;
    }

    public void setPro_v_desc_concepto(String pro_v_desc_concepto) {
        this.pro_v_desc_concepto = pro_v_desc_concepto;
    }

    public Date getPro_v_fecha_pago() {
        return pro_v_fecha_pago;
    }

    public void setPro_v_fecha_pago(Date pro_v_fecha_pago) {
        this.pro_v_fecha_pago = pro_v_fecha_pago;
    }

    public Date getPro_v_fecha_prorroga() {
        return pro_v_fecha_prorroga;
    }

    public void setPro_v_fecha_prorroga(Date pro_v_fecha_prorroga) {
        this.pro_v_fecha_prorroga = pro_v_fecha_prorroga;
    }

    public Date getPro_param_fecha() {
        return pro_param_fecha;
    }

    public void setPro_param_fecha(Date pro_param_fecha) {
        this.pro_param_fecha = pro_param_fecha;
    }

    public String getPro_numero() {
        return pro_numero;
    }

    public void setPro_numero(String pro_numero) {
        this.pro_numero = pro_numero;
    }

    public String getRep_alu_codigo() {
        return rep_alu_codigo;
    }

    public void setRep_alu_codigo(String rep_alu_codigo) {
        this.rep_alu_codigo = rep_alu_codigo;
    }

    public int getRep_sem_id() {
        return rep_sem_id;
    }

    public void setRep_sem_id(int rep_sem_id) {
        this.rep_sem_id = rep_sem_id;
    }

    public Date getModal_pago() {
        return modal_pago;
    }

    public void setModal_pago(Date modal_pago) {
        this.modal_pago = modal_pago;
    }

    public Date getModal_prorroga() {
        return modal_prorroga;
    }

    public void setModal_prorroga(Date modal_prorroga) {
        this.modal_prorroga = modal_prorroga;
    }

    public SelectItem[] getModalPlantillas() {
        parametrosEstructura();
        return modalPlantillas;
    }

    public void setModalPlantillas(SelectItem[] modalPlantillas) {
        this.modalPlantillas = modalPlantillas;
    }

    public int getModalSemestre() {
        return modalSemestre;
    }

    public void setModalSemestre(int modalSemestre) {
        this.modalSemestre = modalSemestre;
    }

    public SelectItem[] getModalSemestres() {
        try {
            HSSemestreDAO dao = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
            List l = dao.seleccionarSemestreActivo();
            modalSemestres = new SelectItem[l.size() + 1];

            for (int i = 0; i < l.size(); i++) {
                Object obj = l.get(i);
                AcSemestre tmp = (AcSemestre) obj;
                modalSemestres[i + 1] = new SelectItem(tmp.getId(), tmp.getSemNombre());
            }
        } catch (Exception e) {
            modalSemestres = new SelectItem[1];
        } finally {
            modalSemestres[0] = new SelectItem(0, "[Seleccione]");
        }
        return modalSemestres;
    }

    public void setModalSemestres(SelectItem[] modalSemestres) {
        this.modalSemestres = modalSemestres;
    }

    public void parametrosEstructura() {
        try {
            HSEdicionEstructPagoDAO dao =
                    (HSEdicionEstructPagoDAO) ServiceFinder.findBean(
                    "SpringHibernateDaoEdicionEstructura");

            List<AdEstructuraPagos> l = dao.listaEstructurasPago(this.getModalSemestre(), this.getModal_Especialidad(), this.getModal_SemIngreso());
            modalPlantillas = new SelectItem[l.size() + 1];
            for (int i = 0; i < l.size(); i++) {
                AdEstructuraPagos estpag = l.get(i);
                modalPlantillas[i + 1] = new SelectItem(estpag.getId(), estpag.getEstpagoNombre());
            }
        } catch (Exception e) {
            modalPlantillas = new SelectItem[1];
        } finally {
            modalPlantillas[0] = new SelectItem(0, "[Seleccione]");
        }
    }

    public String getModal_alumno() {
        return modal_alumno;
    }

    public void setModal_alumno(String modal_alumno) {
        this.modal_alumno = modal_alumno;
    }

    public int getModal_SemIngreso() {
        return modal_SemIngreso;
    }

    public void setModal_SemIngreso(int modal_SemIngreso) {
        this.modal_SemIngreso = modal_SemIngreso;
    }

    public int getModal_Especialidad() {
        return modal_Especialidad;
    }

    public void setModal_Especialidad(int modal_Especialidad) {
        this.modal_Especialidad = modal_Especialidad;
    }

    public int getModal_id() {
        return modal_id;
    }

    public void setModal_id(int modal_id) {
        this.modal_id = modal_id;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public String getImagenVer() {
        return imagenVer;
    }

    public void setImagenVer(String imagenVer) {
        this.imagenVer = imagenVer;
    }

    public String getEstadoVer() {
        return estadoVer;
    }

    public void setEstadoVer(String estadoVer) {
        this.estadoVer = estadoVer;
    }

    public String getSugAlumno() {
        return sugAlumno;
    }

    public void setSugAlumno(String sugAlumno) {
        this.sugAlumno = sugAlumno;
    }

    public String getSugCodigo() {
        return sugCodigo;
    }

    public void setSugCodigo(String sugCodigo) {
        this.sugCodigo = sugCodigo;
    }

    public boolean isMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }

    public void nuevo() {
//        System.out.println("nuevo mantenimiento");
        this.setModal_Especialidad(0);
        this.setModalSemestre(0);
        this.setModal_SemIngreso(0);
        this.setModalPlantilla(0);
        this.setModal_alumno("");
        this.setOncomplete("");
        this.setSugAlumno("");
        this.setTmpAlutar(0);
        this.setTmpEstado(0);
    }

    public void validarBusqueda() {
        this.setOncomplete("");
        if (this.getB_codigo().trim().length() == 0 && this.getB_paterno().trim().length() == 0
                && this.getB_materno().trim().length() == 0 && this.getB_nombre().trim().length() == 0) {
            this.setOncomplete("javascript:alert('Ingrese algun parametro para la busqueda');");
        } else if (this.getB_codigo().trim().length() > 0 && this.getB_codigo().trim().length() < 7) {
            this.setOncomplete("javascript:alert('Codigo muy corto para realizar busqueda');");
        } else if (this.getParametroSemestre() == 0) {
            this.setOncomplete("javascript:alert('Seleccione algun semestre');");
        } else {
            realizarBusqueda(this.getB_codigo(), this.getB_paterno(),
                    this.getB_materno(), this.getB_nombre(), this.getParametroSemestre());
        }
    }

    public void realizarBusqueda(String code, String paterno, String materno, String nombre, int semestre) {
        try {
            this.setListaEstructura(null);
            HSEdicionEstructPagoDAO dao =
                    (HSEdicionEstructPagoDAO) ServiceFinder.findBean(
                    "SpringHibernateDaoEdicionEstructura");

            List<AcAlumno> alumnos = dao.listaAlumnoEstructura(code, paterno, materno, nombre, semestre);
            List<bEdicionEstructPago> estructuras = new ArrayList<bEdicionEstructPago>();
            for (int i = 0; i < alumnos.size();) {
                AcAlumno alumno = alumnos.get(i);
                bEdicionEstructPago tmpMantEstPag = new bEdicionEstructPago();
                tmpMantEstPag.setB_alu_id(alumno.getId());
                tmpMantEstPag.setB_codigo(alumno.getAluCodigo());
                tmpMantEstPag.setB_paterno(alumno.getAluAppaterno());
                tmpMantEstPag.setB_materno(alumno.getAluApmaterno());
                tmpMantEstPag.setB_nombre(alumno.getAluNombres());
                tmpMantEstPag.setB_especialidad(alumno.getEsp().getEspNombre());
                tmpMantEstPag.setB_semestreIngreso(nombreSemestre(alumno.getSemId()));
                tmpMantEstPag.setB_semestreEstructura_id(this.getParametroSemestre());
                tmpMantEstPag.setB_semestreEstructura(nombreSemestre(semestre));
                tmpMantEstPag.setImagenVer("/Imagenes/actions/down.png");
                tmpMantEstPag.setEstadoVer("0");

                List lTarifas = dao.listaTarifasAlumno(semestre, alumno.getId());
                List<bEdicionEstructPago> subEstructuras = new ArrayList<bEdicionEstructPago>();
                List<Integer> vAtids = new ArrayList<Integer>();

                int j = 0;

                for (; j < lTarifas.size(); j++) {
                    Object object = lTarifas.get(j);
                    AdAlumnoTarifa alumnoTarifa = (AdAlumnoTarifa) object;
                    bEdicionEstructPago subTmp = new bEdicionEstructPago();
                    subTmp.setB_sub_alutar_id(alumnoTarifa.getId());
                    subTmp.setB_sub_alutar_estado(alumnoTarifa.getAlutarEstado());
                    subTmp.setB_sub_fecha_pago(alumnoTarifa.getAlutarFechaPago());
                    subTmp.setB_sub_fecha_prorroga(alumnoTarifa.getAlutarFechaProrroga());
                    subTmp.setB_sub_monto(alumnoTarifa.getAlutarMonto());
                    subTmp.setB_sub_concepto(alumnoTarifa.getEstpagdet().getEstpagdetNombre());

                    if (alumnoTarifa.getAlutarEstado().equals("2")) {
                        subTmp.setB_sub_img_candado("/Imagenes/actions/cerrado.png");
                        subTmp.setB_sub_title("Abrir pago");
                        subTmp.setB_sub_msj("de abrir el candado?");
                        subTmp.setB_sub_edit_disabled("true");
                        subTmp.setB_sub_img_klipper("/Imagenes/actions/klipper_dock_gris.png");
                        subTmp.setB_sub_img_calendar("/Imagenes/actions/calendar_gris.png");
                    } else {
                        subTmp.setB_sub_img_candado("/Imagenes/actions/abierto.png");
                        subTmp.setB_sub_title("Cerrar pago");
                        subTmp.setB_sub_msj("cerrar el candado? la siguiente accion se realizara bajo su responsabilidad?");
                        subTmp.setB_sub_edit_disabled("false");
                        subTmp.setB_sub_img_klipper("/Imagenes/actions/klipper_dock.png");
                        subTmp.setB_sub_img_calendar("/Imagenes/actions/calendar.gif");
                    }
                    subEstructuras.add(subTmp);
                    vAtids.add(alumnoTarifa.getId());
                }
                tmpMantEstPag.setSubListaEstructura(subEstructuras);
                tmpMantEstPag.setB_alutarIds(vAtids);
                estructuras.add(tmpMantEstPag);
                i += j;
            }
            this.setListaEstructura(new ArrayList<bEdicionEstructPago>(estructuras));
        } catch (Exception e) {
            System.err.println("falla buscando las estructuras de pago del alumno");
        }
    }

    public void salvaEditar(ActionEvent event) {
        Object alutar_monto = ((UIParameter) event.getComponent().findComponent("alutar_monto")).getValue();
        Object alutar_pago = ((UIParameter) event.getComponent().findComponent("alutar_pago")).getValue();
        Object alutar_prorroga = ((UIParameter) event.getComponent().findComponent("alutar_prorroga")).getValue();
        Object alutar_id = ((UIParameter) event.getComponent().findComponent("alutar_id")).getValue();

        this.setModal_monto(Float.parseFloat(String.valueOf(alutar_monto)));
        this.setModal_pago((Date) alutar_pago);
        this.setModal_prorroga((Date) alutar_prorroga);
        this.setTmpAlutar(Integer.parseInt(String.valueOf(alutar_id)));
    }

    public void editar() {
        this.setOncomplete("");
        try {
            HSEdicionEstructPagoDAO dao =
                    (HSEdicionEstructPagoDAO) ServiceFinder.findBean(
                    "SpringHibernateDaoEdicionEstructura");

            dao.actualizarDatosAlumnoTarifa(this.getTmpAlutar(), this.getModal_monto(),
                    this.getModal_pago(), this.getModal_prorroga());

            realizarBusqueda(this.getB_codigo(), this.getB_paterno(),
                    this.getB_materno(), this.getB_nombre(), this.getParametroSemestre());

            this.setOncomplete("javascript:alert('SE ACTUALIZO CORRECTAMENTE LOS DATOS');"
                    + "Richfaces.hideModalPanel('mpEditar')");
        } catch (Exception e) {
            System.err.println("error en el update de hibernate");
        }
    }

    public void elimParam(ActionEvent event) {
        UIParameter estpag_alutarIds = (UIParameter) event.getComponent().findComponent("estpag_alutarIds");
        List<Integer> vAlutarIds = (List<Integer>) estpag_alutarIds.getValue();

        this.setB_alutarIds(vAlutarIds);
    }
    
    public void elimParamPago(ActionEvent event) {
        UIParameter estpag_alutarId = (UIParameter) event.getComponent().findComponent("alutar_id_pag");
        //List<Integer> estpag_alutarIds = (List<Integer>) estpag_alutarIds.getValue();
        this.setB_alutarId(Integer.parseInt(String.valueOf(estpag_alutarId.getValue())));
    }

    public void eliminarEstructura() {
        try {
            this.setOncomplete("");
            HSEdicionEstructPagoDAO dao =
                    (HSEdicionEstructPagoDAO) ServiceFinder.findBean(
                    "SpringHibernateDaoEdicionEstructura");
            List<Integer> vAlutarIds = this.getB_alutarIds();
            //Suma los montos para un alutarId de AdPago
            float montoTotal = 0;
            for (int i = 0; i < vAlutarIds.size(); i++) {
                List ltmp = dao.listaPagosAlumno(vAlutarIds.get(i), 1);

                for (int j = 0; j < ltmp.size(); j++) {
                    AdPago adPago = (AdPago) ltmp.get(j);
                    montoTotal += adPago.getPagMonto();
                }
            }
            if (montoTotal == 0) {
                dao.eliminarEstructurasPagoAlumnoTarifa(vAlutarIds);

                realizarBusqueda(this.getB_codigo(), this.getB_paterno(),
                        this.getB_materno(), this.getB_nombre(), this.getParametroSemestre());

                this.setOncomplete("javascript:alert('Se eliminaron las estructuras, no habia pagos registrados');"
                        + "Richfaces.hideModalPanel('mpEliminar')");
            } else {
                this.setOncomplete("javascript:alert('No se puede eliminar, hay pagos realizados');"
                        + "Richfaces.hideModalPanel('mpEliminar')");
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar la estructura");
        }
    }

    public void mostrarDetalle(ActionEvent event) {
        UIParameter ui = (UIParameter) event.getComponent().findComponent("p_ver");
        String p_ver = String.valueOf(ui.getValue());
//        System.out.println("el id encontrado es :" + p_ver);
        if (p_ver.equals("0")) {
            this.setMostrar(true);
            this.setEstadoVer("1");
            this.setImagenVer("/Imagenes/actions/up.png");
        } else {
            this.setMostrar(false);
            this.setEstadoVer("0");
            this.setImagenVer("/Imagenes/actions/down.png");
        }
    }

    public void salvaParam(ActionEvent event) {
        UIParameter mensaje = (UIParameter) event.getComponent().findComponent("mensaje");
        UIParameter alutarId = (UIParameter) event.getComponent().findComponent("alutar_id");
        UIParameter alutarEstado = (UIParameter) event.getComponent().findComponent("alutar_estado");

        this.setTmpAlutar(Integer.parseInt(String.valueOf(alutarId.getValue())));
        this.setTmpEstado(Integer.parseInt(String.valueOf(alutarEstado.getValue())));
        this.setB_sub_msj(String.valueOf(mensaje.getValue()));
    }

    public void actualizarEstado() {
        this.setOncomplete("");

        HSEdicionEstructPagoDAO dao =
                (HSEdicionEstructPagoDAO) ServiceFinder.findBean(
                "SpringHibernateDaoEdicionEstructura");

        List ltmp = dao.listaPagosAlumno(this.getTmpAlutar(), 0);

        /**
         * Monto de AdPago, lo que ha cancelado
         */
        float montoTotal = 0;
        for (int i = 0; i < ltmp.size(); i++) {
            AdPago adPago = (AdPago) ltmp.get(i);
            montoTotal += adPago.getPagMonto();
        }
        String mensaje = "";
        if (this.getTmpEstado() == 2) {
            if (montoTotal == 0) {
                dao.actualizarEstadoPago(this.getTmpAlutar(), "0");
            } else {
                dao.actualizarEstadoPago(this.getTmpAlutar(), "1");
            }
            mensaje = "Se abre el candado";
        } else {
            /**
             * Monto que debe pagar, el que se genera en la Estructura
             */
            AdAlumnoTarifa tAlutar = dao.montoAlumnoTarifa(this.getTmpAlutar());
            float montoExiste = tAlutar.getAlutarMonto();
            if (montoTotal >= montoExiste) {
                mensaje = "se cerrara el candado, tiene los pagos al dia";
                dao.actualizarEstadoPago(this.getTmpAlutar(), "2");
            } else if (montoTotal > 0 && montoTotal < montoExiste) {
                mensaje = "Aun presenta deudas";
                dao.actualizarEstadoPago(this.getTmpAlutar(), "1");
            } else {
                mensaje = "No se puede cerrar el candado, presenta deudas";
            }
        }
        realizarBusqueda(this.getB_codigo(), this.getB_paterno(),
                this.getB_materno(), this.getB_nombre(), this.getParametroSemestre());

        this.setOncomplete("javascript:alert('" + mensaje + "');"
                + "Richfaces.hideModalPanel('mpCandado')");
    }
    
    public void eliminarPago() {
        this.setOncomplete("");
        try {
            HSEdicionEstructPagoDAO dao =
                    (HSEdicionEstructPagoDAO) ServiceFinder.findBean(
                    "SpringHibernateDaoEdicionEstructura");
            dao.eliminarPago(this.getB_alutarId());
            realizarBusqueda(this.getB_codigo(), this.getB_paterno(),
                    this.getB_materno(), this.getB_nombre(), this.getParametroSemestre());
            this.setOncomplete("javascript:alert('SE ELIMINO EL PAGO CORRECTAMENTE');"
                    + "Richfaces.hideModalPanel('mpEliminarPago')");
        } catch (Exception e) {
            System.err.println("error al eliminar el pago");
        }
    }
    
    public List<bEdicionEstructPago> autocomplete(Object suggest) {
        String ref = String.valueOf(suggest);
        List<bEdicionEstructPago> l = new ArrayList<bEdicionEstructPago>();

        try {
            HSEdicionEstructPagoDAO dao =
                    (HSEdicionEstructPagoDAO) ServiceFinder.findBean(
                    "SpringHibernateDaoEdicionEstructura");

            List alumnos = dao.listaAlumnoActivo(ref);
            for (int i = 0; i < alumnos.size(); i++) {
                Object object = alumnos.get(i);
                AcAlumno alu = (AcAlumno) object;
                bEdicionEstructPago tmp = new bEdicionEstructPago();
                tmp.setB_alu_id(alu.getId());
                tmp.setB_codigo(alu.getAluCodigo());
                tmp.setB_paterno(alu.getAluAppaterno());
                tmp.setB_materno(alu.getAluApmaterno());
                tmp.setB_nombre(alu.getAluNombres());
                tmp.setModal_Especialidad(alu.getEspIdIngreso());
                tmp.setModal_SemIngreso(alu.getSemId());
                l.add(tmp);
            }
        } catch (Exception e) {
        }
        return l;
    }

    public void validarEstructuras(ActionEvent event) {
        this.setOncomplete("");
        int aluId = this.getModal_id();
        int semIngreso = this.getModal_SemIngreso();
        int esp = this.getModal_Especialidad();
        int sem = this.getModalSemestre();

        try {
            HSEdicionEstructPagoDAO dao =
                    (HSEdicionEstructPagoDAO) ServiceFinder.findBean(
                    "SpringHibernateDaoEdicionEstructura");
            List lEstruc = dao.listaEstructurasExistentes(sem, esp, semIngreso);
            List lAlutar = dao.listaAlumnoTarifaExistentes(aluId, sem, esp);

            int iEstruc = lEstruc.size();
            int iAlutar = lAlutar.size();
            if (iEstruc == iAlutar) {
                this.setOncomplete("javascript:alert('ESTAN COMPLETAS LAS ESTRUCTURAS');"
                        + "Richfaces.hideModalPanel('mpEstructuraNuevo')");
            } else {
                AdEstructuraPagos estpag = dao.estructuraPagoPlantilla(this.getModalPlantilla());
                this.generarEstructuras(iEstruc, iAlutar, estpag, lAlutar);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generarEstructuras(int iEstpag, int iAlutar, AdEstructuraPagos estpag, List lAlutar) throws Exception {
        HSEdicionEstructPagoDAO dao =
                (HSEdicionEstructPagoDAO) ServiceFinder.findBean(
                "SpringHibernateDaoEdicionEstructura");

        HSAlumnoDAO daoA = (HSAlumnoDAO) ServiceFinder.findBean("SpringHibernateDaoAlumno");
        //AcAlumno alu=daoA.seleccionarAlumno(this.getModal_id());
        AcAlumno alu=daoA.seleccionarAlumno(this.getModal_id());

        if (iAlutar == 0) {
            Object[] tmp = estpag.getAdEstructuraPagosDetalles().toArray();
            for (int i = 0; i < tmp.length; i++) {
                AdEstructuraPagosDetalle estpagdet = (AdEstructuraPagosDetalle) tmp[i];

                AdAlumnoTarifa alumnoTarifa = new AdAlumnoTarifa();

                alumnoTarifa.setAlutarMonto(estpagdet.getEstpagdetMonto());
                alumnoTarifa.setAlutarMontoCopy(estpagdet.getEstpagdetMonto());
                alumnoTarifa.setAlutarFechaPago(estpagdet.getEstpagdetFechaPago());
                alumnoTarifa.setAlutarFechaProrroga(estpagdet.getEstpagdetFechaPago());
                alumnoTarifa.setAlutarActivo("1");
                alumnoTarifa.setAlutarEstado("0");
                alumnoTarifa.setConpag(new AdConceptoPago(estpagdet.getConpagId()));
                alumnoTarifa.setAlu(new AcAlumno(this.getModal_id()));
                alumnoTarifa.setEstpagdet(estpagdet);
                if (alu.getAluProcedencia() != null) {
                            if (alu.getAluProcedencia().equals("023001")
                                    || alu.getAluProcedencia().equals("023002")
                                    || alu.getAluProcedencia().equals("023003")
                                    || alu.getAluProcedencia().equals("023004")
                                    || alu.getAluProcedencia().equals("023014")
                                    || alu.getAluProcedencia().equals("023010")) {
                                if (alu.getAluMontoPago() > 0) {
                                    alumnoTarifa.setAlutarMonto(alu.getAluMontoPago());
                                    alumnoTarifa.setAlutarMontoCopy(alu.getAluMontoPago());
                                    if (estpagdet.getConpagId() == 1 && alu.getAluProcedencia().equals("023014") && alu.getSemId()!=9 ) {
                                        alumnoTarifa.setAlutarMonto(estpagdet.getEstpagdetMonto());
                                        alumnoTarifa.setAlutarMontoCopy(estpagdet.getEstpagdetMonto());
                                        alumnoTarifa.setAlutarEstado("0");
                                    }
                                    else if (estpagdet.getConpagId() == 1) {
                                        alumnoTarifa.setAlutarMonto(Float.parseFloat("0"));
                                        alumnoTarifa.setAlutarMontoCopy(Float.parseFloat("0"));
                                        alumnoTarifa.setAlutarEstado("2");
                                    }
                                }
                            }else{
                            if (alu.getAluMontoPago() > 0 &&
                                    estpagdet.getConpagId() != 1 ) {
                                alumnoTarifa.setAlutarMonto(alu.getAluMontoPago());
                                alumnoTarifa.setAlutarMontoCopy(alu.getAluMontoPago());
                            }
                            }
                        }

                dao.insertarAlumnoTarifa(alumnoTarifa);
            }
        } else {
//            System.out.println("RECALCULAMOS LAS ESTRUCTURAS QUE NO ESTAN EN LA BD");
            Object[] tmp = estpag.getAdEstructuraPagosDetalles().toArray();
            for (int i = 0; i < tmp.length; i++) {
                AdEstructuraPagosDetalle estpagdet = (AdEstructuraPagosDetalle) tmp[i];
                boolean encontrado = false;
                int j;
                for (j = 0; j < lAlutar.size() && !encontrado; j++) {
                    AdAlumnoTarifa alutar = (AdAlumnoTarifa) lAlutar.get(j);

                    int iat = alutar.getEstpagdet().getId();
                    int iep = estpagdet.getId();

                    if (iat == iep) {
                        encontrado = true;
                    }
                }
                if (!encontrado) {
                    AdEstructuraPagosDetalle tpd2 = (AdEstructuraPagosDetalle) tmp[i];

                    AdAlumnoTarifa alumnoTarifa = new AdAlumnoTarifa();

                    alumnoTarifa.setAlutarMonto(tpd2.getEstpagdetMonto());
                    alumnoTarifa.setAlutarMontoCopy(tpd2.getEstpagdetMonto());
                    alumnoTarifa.setAlutarFechaPago(tpd2.getEstpagdetFechaPago());
                    alumnoTarifa.setAlutarFechaProrroga(tpd2.getEstpagdetFechaPago());
                    alumnoTarifa.setAlutarActivo("1");
                    alumnoTarifa.setAlutarEstado("0");
                    alumnoTarifa.setConpag(new AdConceptoPago(tpd2.getConpagId()));
                    alumnoTarifa.setAlu(new AcAlumno(this.getModal_id()));
                    alumnoTarifa.setEstpagdet(tpd2);
                    if (alu.getAluProcedencia() != null) {
                            if (alu.getAluProcedencia().equals("023001")
                                    || alu.getAluProcedencia().equals("023002")
                                    || alu.getAluProcedencia().equals("023003")
                                    || alu.getAluProcedencia().equals("023004")
                                    || alu.getAluProcedencia().equals("023010")) {
                                if (alu.getAluMontoPago() > 0) {
                                    alumnoTarifa.setAlutarMonto(alu.getAluMontoPago());
                                    alumnoTarifa.setAlutarMontoCopy(alu.getAluMontoPago());
                                    if (estpagdet.getConpagId() == 1) {
                                        alumnoTarifa.setAlutarMonto(Float.parseFloat("0"));
                                        alumnoTarifa.setAlutarMontoCopy(Float.parseFloat("0"));
                                        alumnoTarifa.setAlutarEstado("2");
                                    }
                                }
                            }
                        }

                    dao.insertarAlumnoTarifa(alumnoTarifa);
                }
            }
        }

        this.setOncomplete("javascript:alert('SE GENERARON CORRECTAMENTE LAS ESTRUCTURAS');"
                + "Richfaces.hideModalPanel('mpEstructuraNuevo')");
    }

    private String nombreSemestre(int key) {
        for (int i = 0; i < parametroSemestres.length; i++) {
            SelectItem selectItem = parametroSemestres[i];
            if (Integer.parseInt(String.valueOf(selectItem.getValue())) == key) {
                return selectItem.getLabel();
            }
        }
        return "";
    }

    public void imprimir(ActionEvent event) {
        UIParameter aluCodigo = (UIParameter) event.getComponent().findComponent("alu_codigo");
        UIParameter aluSemestre = (UIParameter) event.getComponent().findComponent("alu_semestre");

        this.setRep_alu_codigo(String.valueOf(aluCodigo.getValue()));
        this.setRep_sem_id(Integer.parseInt(String.valueOf(aluSemestre.getValue())));
    }

    public void reporteMantEstPag(OutputStream out, Object data)
            throws IOException, EOFException, SQLException, JRException {
        if (data.equals("histPag")) {
            FacesContext context = FacesContext.getCurrentInstance();

            HashMap parametros = new HashMap();
            parametros.put("LOGO", context.getExternalContext().getResource("/Imagenes/actions/logo_p.jpg"));
            parametros.put("ALU_CODIGO", this.getRep_alu_codigo());
            parametros.put("SEM_ID", this.getRep_sem_id());

            String jasper = "/WEB-INF/Reportes/historial_pagos.jasper";

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

    public void agregarCompromiso() {
        this.setOncomplete("");
        String fech_pro = formatDate.format(this.getPro_v_fecha_prorroga());
//        System.out.println("la fecha de prorroga: " + fech_pro);
        if (this.getPro_param_fecha() != null) {
            String param_fecha = formatDate.format(this.getPro_param_fecha());
            if (param_fecha.compareTo(fech_pro) > 0) {
                boolean existe = false;
                for (int i = 0; i < proListaProrrogas.size(); i++) {
                    bEdicionEstructPago t = proListaProrrogas.get(i);
                    String fechaExiste = formatDate.format(t.getPro_fecha());
                    if (param_fecha.compareTo(fechaExiste) <= 0) {
                        existe = true;
                        break;
                    }
                }
                if (!existe) {
//                    System.out.println("ahora se agregara xq no existe la fecha y es mayor que hoy");
                    bEdicionEstructPago b = new bEdicionEstructPago();
                    b.setPro_fecha(this.getPro_param_fecha());
                    b.setPro_numero(String.valueOf(proListaProrrogas.size() + 1));
                    b.setPro_pos(proListaProrrogas.size());
                    b.setPro_coment(this.getPro_param_coment());
                    b.setPro_img_quitar("/Imagenes/actions/edit_remove.png");
                    b.setPro_creacion(tfNow);
                    proListaProrrogas.add(b);
                    this.setPro_param_fecha(null);
                    this.setPro_param_coment("");
                }
            }
        }
    }

    public void quitarCompromiso(ActionEvent event) {
        UIParameter uipos = (UIParameter) event.getComponent().findComponent("pos");
        UIParameter uicreac = (UIParameter) event.getComponent().findComponent("creac");
        int pos = Integer.parseInt(String.valueOf(uipos.getValue()));
        String creac = formatDate.format((Date) uicreac.getValue());
        String hoy = formatDate.format(new Date());

        if (proListaProrrogasEliminados == null) {
            proListaProrrogasEliminados = new ArrayList<bEdicionEstructPago>();
        }
        if (creac.equalsIgnoreCase(hoy)) {
//            System.out.println("la fecha de creacion es: " + creac + " y hoy estamos " + hoy);
            proListaProrrogasEliminados.add(proListaProrrogas.remove(pos));
            for (int i = 0; i < proListaProrrogas.size(); i++) {
                proListaProrrogas.get(i).setPro_numero("" + (i + 1));
                proListaProrrogas.get(i).setPro_pos(i);
//                System.out.println(proListaProrrogas.get(i).getPro_pos() + "\t " + proListaProrrogas.get(i).getPro_numero());
            }
        }
    }

    private int capturarUsuario() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
        int id_usu = ((bUsuario) session.getAttribute("usuario")).getId_usu();
        return id_usu;
    }

    public void guardarCompromiso() {
        try {
            HSEdicionEstructPagoDAO dao =
                    (HSEdicionEstructPagoDAO) ServiceFinder.findBean(
                    "SpringHibernateDaoEdicionEstructura");

            List<AdProrroga> progs = dao.listaProrrogas(this.getPro_v_alutarID());

//            System.out.println("hay para guardar " + proListaProrrogas.size());
//            System.out.println("se guardara para el alutarid: " + this.getPro_v_alutarID());

            bEdicionEstructPago bmp = new bEdicionEstructPago();
//            System.out.println("hay bloqueados: " + this.getTmpCompBloq());
//            System.out.println("hay bloqueados: " + stcCompBloq);

            if (this.getProListaProrrogasEliminados() != null) {
                List<bEdicionEstructPago> l = this.getProListaProrrogasEliminados();
                if (!l.isEmpty()) {
//                    System.out.println("se agregaron prorrogas para eliminar");
                    for (int j = 0; j < l.size(); j++) {
                        bmp = l.get(j);
//                        System.out.println("se eliminara el id: " + bmp.getPro_id());
                        dao.eliminarProrroga(bmp.getPro_id());
                    }
                }
            }
//            for (int i = stcCompBloq; i < proListaProrrogas.size(); i++) {
            for (int i = this.getTmpCompBloq(); i < proListaProrrogas.size(); i++) {
                bmp = proListaProrrogas.get(i);
                boolean existe = false;
//                for (int j = stcCompBloq; j < progs.size(); j++) {
                for (int j = this.getTmpCompBloq(); j < progs.size(); j++) {
                    AdProrroga pro = progs.get(j);
                    if (bmp.getPro_id() == pro.getProId()) {
                        existe = true;
                        j = progs.size();
                    }
                }
                if (!existe) {
                    AdProrroga prorroga = new AdProrroga();

//                    System.out.println("num: " + bmp.getPro_numero());
//                    System.out.println("fecha: " + bmp.getPro_fecha());
//                    System.out.println("com: " + bmp.getPro_coment());

                    prorroga.setProNumero(bmp.getPro_numero());
                    prorroga.setProCreacion(tfNow);
                    prorroga.setProFecha(bmp.getPro_fecha());
                    prorroga.setProComentario(bmp.getPro_coment());
                    prorroga.setProActivo("1");
                    prorroga.setAdAlumnoTarifa(new AdAlumnoTarifa(this.getPro_v_alutarID()));
                    prorroga.setTbUsuario(new TbUsuario(capturarUsuario()));

                    dao.insertarProrroga(prorroga);
                }
            }

            List l = dao.listaProrrogas(this.getPro_v_alutarID());
            for (int i = 0; i < l.size(); i++) {
                AdProrroga pro = (AdProrroga) l.get(i);
                dao.actualizarNumero(pro.getProId(), String.valueOf(i + 1));
            }
            proListaProrrogasEliminados = new ArrayList<bEdicionEstructPago>();
            this.setOncomplete("javascript:alert('Se actualizaron los compromisos');"
                    + "Richfaces.hideModalPanel('mpProrroga');");
        } catch (Exception e) {
        }
    }
}
