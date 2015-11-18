package net.uch.administrativa.managedBeans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.faces.component.UIParameter;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import net.uch.academica.hibernateSpringDao.HSAlumnoDAO;
import net.uch.administrativa.hibernateSpringDao.HSAlumnoTarifaDAO;
import net.uch.administrativa.hibernateSpringDao.HSConceptoPagoDAO;
import net.uch.administrativa.hibernateSpringDao.HSEstructuraPagoDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcAlumno;
import net.uch.mapping.AcEspecialidad;
import net.uch.mapping.AcFacultad;
import net.uch.mapping.AcSemestre;
import net.uch.mapping.AdAlumnoTarifa;
import net.uch.mapping.AdConceptoPago;
import net.uch.mapping.AdEstructuraPagos;
import net.uch.mapping.AdEstructuraPagosDetalle;
import net.uch.tablaSistema.hibernateSpringDao.HSEspecialidadDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSFacultadDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSSemestreDAO;
import net.uch.tablaSistema.managedBeans.bUsuario;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class bEstructuraPago {

    public List<AdAlumnoTarifa> listaPubli = new ArrayList<AdAlumnoTarifa>(); //static
    private int id_s;
    private int id_i;
    private String codigo_s;
    private String codigo_i;
    private String nombre_s;
    private String nombre_i;
    private Date creacion_s;
    private Date creacion_i;
    private int esp_id_s;
    private int esp_id_i;
    private String esp_s;
    private String esp_i;
    private int fac_id_s;
    private int fac_id_i;
    private String fac_s;
    private String fac_i;
    private String sem_s;
    private String sem_i;
    private int sem_id_s;
    private int sem_id_i;
    private String sem_d;
    private int sem_id_d;
    private String des_i;
    private String oncomplete;
    private String oncomplete2;

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public String getOncomplete2() {
        return oncomplete2;
    }

    public void setOncomplete2(String oncomplete2) {
        this.oncomplete2 = oncomplete2;
    }
    private String vigente_s;
    private String vigente_i;
    private String publicado_s;
    private String publicado_i;
    private String activo_det;
    private String nombre_f;
    private List detalle_s;
    private int id_aux; //static
    private int id_aux2; //static
    private String nombre_aux; //static
    private String codigo_aux; //static
    private Date creacion_aux; //static
    private String vigente_aux; //static
    private String cod_det_aux; //static
    private String nom_det_aux; //static
    private int pes_det_aux; //static
    private String fac_aux; //static
    private String esp_aux; //static
    private int fac_id_aux; //static
    private int esp_id_aux; //static
    private String sem_aux; //static
    private String des_aux; //static
    private int sem_id_aux; //static
    private int sem_id_d_aux;
    private String sem_aux_d;
    private List tablaSisEva;
    private List tablaSisEvaDetalle;
    private int id_det = 0;
    private String nombre_det;
    private String codigo_det;
    private int peso_det;
    private int con_pag_id_det;
    private String con_pag_det;
    private Date fecha_det;
    private float monto_det;
    private float monto_ref_det;
    private String codigo_ref_det;
    private List<bEstructuraPago> detalle = new ArrayList<bEstructuraPago>(); //static
    private String editable = "false";
    private String view = "true";
    private boolean editable_bool = false;
    private boolean view_bool = true;
    private boolean visible = false;
    private boolean ver = false;
    public String flag_ver = "0";
    public String imagen = "/Imagenes/actions/down.png";
    public String ima_publi = "/Imagenes/actions/matricula.png";
    public String ima_publi2 = "/Imagenes/actions/preg.png";
    public String ima_edit = "/Imagenes/actions/editpaste.png";
    public String ima_delete = "/Imagenes/actions/no.png";
    public String disabled_publi = "false";
    public String disabled_edit = "javascript:Richfaces.showModalPanel('mp',{width:600, top:80})";
    public String disabled_delete = "false";
    public String mensaje_publi = "";
    public Boolean disabled_publi2 = false;
    //para la busqueda
    //vriables de tipo global
    int num_reg_sem = 10;//numero maximo de registros que se presentaran en el comcom de semestres;
    int c_id_facu;
    int c_id_espe;
    int c_id_semestre;
    public SelectItem[] comboFacultad_buscar;
    public SelectItem[] comboEspecialidad_buscar;
    public SelectItem[] comboSemestre;
    public SelectItem[] comboFacultad_i;
    public SelectItem[] comboEspecialidad_i;
    public SelectItem[] comboSemestre_i;
    private int b_idf; //static
    private int b_ide; //static
    private int i_idf; //static
    private int i_ide; //static
    private int esp_id_ix;
    private int sem_id_ix;
    private int fac_id_ix;
    private String des_ix;
    public SelectItem[] comboConceptoPago;

    public bEstructuraPago() throws Exception {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession(false);
        bUsuario usu = (bUsuario) session1.getAttribute("usuario");
        if (usu != null) {
        } else {
            throw new Exception();
        }
    }

    public int getId_s() {
        return id_s;
    }

    public void setId_s(int id_s) {
        this.id_s = id_s;
    }

    public int getId_i() {
        /*if(id_aux!=0)
        return id_aux;
        else*/
        return id_i;
    }

    public void setId_i(int id_i) {
        this.id_i = id_i;
    }

    public String getCodigo_s() {
        return codigo_s;
    }

    public void setCodigo_s(String codigo_s) {
        this.codigo_s = codigo_s;
    }

    public String getCodigo_i() {
        /*if(id_aux!=0)
        return codigo_aux;
        else*/
        return codigo_i;
    }

    public void setCodigo_i(String codigo_i) {
        this.codigo_i = codigo_i;
    }

    public String getNombre_s() {
        return nombre_s;
    }

    public void setNombre_s(String nombre_s) {
        this.nombre_s = nombre_s;
    }

    public String getNombre_i() {
        /*if(id_aux!=0)
        return nombre_aux;
        else*/
        return nombre_i;
    }

    public void setNombre_i(String nombre_i) {
        this.nombre_i = nombre_i;
    }

    public Date getCreacion_s() {
        return creacion_s;
    }

    public void setCreacion_s(Date creacion_s) {
        this.creacion_s = creacion_s;
    }

    public Date getCreacion_i() {
        return creacion_i;
    }

    public void setCreacion_i(Date creacion_i) {
        this.creacion_i = creacion_i;
    }

    public String getVigente_s() {
        return vigente_s;
    }

    public void setVigente_s(String vigente_s) {
        this.vigente_s = vigente_s;
    }

    public String getVigente_i() {
        /*if(id_aux!=0)
        return vigente_aux;
        else*/
        return vigente_i;
    }

    public void setVigente_i(String vigente_i) {
        this.vigente_i = vigente_i;
    }

    public String getNombre_f() {
        return nombre_f;
    }

    public void setNombre_f(String nombre_f) {
        this.nombre_f = nombre_f;
    }

    public List getTablaSisEva() {
        return tablaSisEva;
    }

    public void setTablaSisEva(List tablaSisEva) {
        this.tablaSisEva = tablaSisEva;
    }

    public int getId_det() {
        return id_det;
    }

    public void setId_det(int id_det) {
        this.id_det = id_det;
    }

    public String getNombre_det() {
        return nombre_det;
    }

    public void setNombre_det(String nombre_det) {
        this.nombre_det = nombre_det;
    }

    public int getPeso_det() {
        return peso_det;
    }

    public void setPeso_det(int peso_det) {
        this.peso_det = peso_det;
    }

    public List getTablaSisEvaDetalle() {
        if (id_aux != 0) {
            return view(detalle);
        } else {
            return view(tablaSisEvaDetalle);
        }
    }

    public List view(List<bEstructuraPago> detalle) {
        List<bEstructuraPago> lista = new ArrayList<bEstructuraPago>();
        if (detalle != null) {
            for (int j = 0; j < detalle.size(); j++) {
                if (((bEstructuraPago) detalle.get(j)).getActivo_det().equals("1")) {
                    lista.add(detalle.get(j));
                }
            }
        }
        return lista;
    }

    public void setTablaSisEvaDetalle(List tablaSisEvaDetalle) {
        this.tablaSisEvaDetalle = tablaSisEvaDetalle;
    }

    public List getDetalle_s() {
        return detalle_s;
    }

    public void setDetalle_s(List detalle_s) {
        this.detalle_s = detalle_s;
    }

    public String getCodigo_det() {
        return codigo_det;
    }

    public void setCodigo_det(String codigo_det) {
        this.codigo_det = codigo_det;
    }

    public String getActivo_det() {
        return activo_det;
    }

    public void setActivo_det(String activo_det) {
        this.activo_det = activo_det;
    }

    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVer() {
        return ver;
    }

    public void setVer(boolean ver) {
        this.ver = ver;
    }

    public boolean isEditable_bool() {
        return editable_bool;
    }

    public void setEditable_bool(boolean editable_bool) {
        this.editable_bool = editable_bool;
    }

    public boolean isView_bool() {
        return view_bool;
    }

    public void setView_bool(boolean view_bool) {
        this.view_bool = view_bool;
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

    public String getIma_publi() {
        return ima_publi;
    }

    public void setIma_publi(String ima_publi) {
        this.ima_publi = ima_publi;
    }

    public String getIma_publi2() {
        return ima_publi2;
    }

    public void setIma_publi2(String ima_publi2) {
        this.ima_publi2 = ima_publi2;
    }

    public String getDisabled_publi() {
        return disabled_publi;
    }

    public void setDisabled_publi(String disabled_publi) {
        this.disabled_publi = disabled_publi;
    }

    public String getIma_edit() {
        return ima_edit;
    }

    public void setIma_edit(String ima_edit) {
        this.ima_edit = ima_edit;
    }

    public String getIma_delete() {
        return ima_delete;
    }

    public void setIma_delete(String ima_delete) {
        this.ima_delete = ima_delete;
    }

    public String getDisabled_edit() {
        return disabled_edit;
    }

    public void setDisabled_edit(String disabled_edit) {
        this.disabled_edit = disabled_edit;
    }

    public String getDisabled_delete() {
        return disabled_delete;
    }

    public void setDisabled_delete(String disabled_delete) {
        this.disabled_delete = disabled_delete;
    }

    public String getMensaje_publi() {
        return mensaje_publi;
    }

    public void setMensaje_publi(String mensaje_publi) {
        this.mensaje_publi = mensaje_publi;
    }

    public Boolean getDisabled_publi2() {
        return disabled_publi2;
    }

    public void setDisabled_publi2(Boolean disabled_publi2) {
        this.disabled_publi2 = disabled_publi2;
    }

    public String getPublicado_s() {
        return publicado_s;
    }

    public void setPublicado_s(String publicado_s) {
        this.publicado_s = publicado_s;
    }

    public String getPublicado_i() {
        return publicado_i;
    }

    public void setPublicado_i(String publicado_i) {
        this.publicado_i = publicado_i;
    }

    public int getEsp_id_s() {
        return esp_id_s;
    }

    public void setEsp_id_s(int esp_id_s) {
        this.esp_id_s = esp_id_s;
    }

    public int getEsp_id_i() {
        /*if(id_aux!=0)
        return esp_id_aux;
        else*/
        return esp_id_i;
    }

    public void setEsp_id_i(int esp_id_i) {
        this.esp_id_i = esp_id_i;
    }

    public int getCon_pag_id_det() {
        return con_pag_id_det;
    }

    public void setCon_pag_id_det(int con_pag_id_det) {
        this.con_pag_id_det = con_pag_id_det;
    }

    public Date getFecha_det() {
        return fecha_det;
    }

    public void setFecha_det(Date fecha_det) {
        this.fecha_det = fecha_det;
    }

    public float getMonto_det() {
        return monto_det;
    }

    public void setMonto_det(float monto_det) {
        this.monto_det = monto_det;
    }

    public String getEsp_s() {
        return esp_s;
    }

    public void setEsp_s(String esp_s) {
        this.esp_s = esp_s;
    }

    public String getEsp_i() {
        /*if(id_aux!=0)
        return esp_aux;
        else*/
        return esp_i;
    }

    public void setEsp_i(String esp_i) {
        this.esp_i = esp_i;
    }

    public int getFac_id_s() {
        return fac_id_s;
    }

    public void setFac_id_s(int fac_id_s) {
        this.fac_id_s = fac_id_s;
    }

    public int getFac_id_i() {
        /*if(id_aux!=0)
        return fac_id_aux;
        else*/
        return fac_id_i;
    }

    public void setFac_id_i(int fac_id_i) {
        this.fac_id_i = fac_id_i;
    }

    public String getFac_s() {
        return fac_s;
    }

    public void setFac_s(String fac_s) {
        this.fac_s = fac_s;
    }

    public String getFac_i() {
        /*if(id_aux!=0)
        return fac_aux;
        else*/
        return fac_i;
    }

    public void setFac_i(String fac_i) {
        this.fac_i = fac_i;
    }

    public String getCon_pag_det() {
        return con_pag_det;
    }

    public void setCon_pag_det(String con_pag_det) {
        this.con_pag_det = con_pag_det;
    }

    public float getMonto_ref_det() {
        return monto_ref_det;
    }

    public void setMonto_ref_det(float monto_ref_det) {
        this.monto_ref_det = monto_ref_det;
    }

    public String getCodigo_ref_det() {
        return codigo_ref_det;
    }

    public void setCodigo_ref_det(String codigo_ref_det) {
        this.codigo_ref_det = codigo_ref_det;
    }

    public String getSem_i() {
        /*if(id_aux!=0)
        return sem_aux;
        else*/
        return sem_i;
    }

    public void setSem_i(String sem_i) {
        this.sem_i = sem_i;
    }

    public String getDes_i() {
        /*if(id_aux!=0)
        return des_aux;
        else*/
        return des_i;
    }

    public void setDes_i(String des_i) {
        this.des_i = des_i;
    }

    public String getSem_s() {
        return sem_s;
    }

    public void setSem_s(String sem_s) {
        this.sem_s = sem_s;
    }

    public int getSem_id_s() {
        return sem_id_s;
    }

    public void setSem_id_s(int sem_id_s) {
        this.sem_id_s = sem_id_s;
    }

    public int getSem_id_i() {
        /*if(id_aux!=0)
        return sem_id_aux;
        else*/
        return sem_id_i;
    }

    public void setSem_id_i(int sem_id_i) {
        this.sem_id_i = sem_id_i;
    }

    ///////para la busqueda
    public int getC_id_facu() {
        return c_id_facu;
    }

    public void setC_id_facu(int c_id_facu) {
        this.c_id_facu = c_id_facu;
    }

    public int getC_id_espe() {
        return c_id_espe;
    }

    public void setC_id_espe(int c_id_espe) {
        this.c_id_espe = c_id_espe;
    }

    public int getC_id_semestre() {
        return c_id_semestre;
    }

    public void setC_id_semestre(int c_id_semestre) {
        this.c_id_semestre = c_id_semestre;
    }

    public int getEsp_id_ix() {
        return esp_id_ix;
    }

    public void setEsp_id_ix(int esp_id_ix) {
        this.esp_id_ix = esp_id_ix;
    }

    public int getSem_id_ix() {
        return sem_id_ix;
    }

    public void setSem_id_ix(int sem_id_ix) {
        this.sem_id_ix = sem_id_ix;
    }

    public int getFac_id_ix() {
        return fac_id_ix;
    }

    public void setFac_id_ix(int fac_id_ix) {
        this.fac_id_ix = fac_id_ix;
    }

    public String getDes_ix() {
        return des_ix;
    }

    public void setDes_ix(String des_ix) {
        this.des_ix = des_ix;
    }

    public SelectItem[] getComboFacultad_buscar() throws Exception {
        HSFacultadDAO dao = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
        List lista = dao.seleccionarFacultad("", "");
        SelectItem[] comboFacultades = new SelectItem[lista.size() + 1];
        comboFacultades[0] = new SelectItem(0, "Todos");
        for (int i = 0; i < comboFacultades.length - 1; i++) {
            comboFacultades[i + 1] = new SelectItem(((AcFacultad) lista.get(i)).getId(), ((AcFacultad) lista.get(i)).getFacNombre());
        }
        return comboFacultades;
    }

    public void setComboFacultad_buscar(SelectItem[] comboFacultad) {
        this.comboFacultad_buscar = comboFacultad;
    }

    public void setearFacultad_buscar() {
        b_idf = this.getC_id_facu();
        this.setC_id_espe(0);
        b_ide = 0;
        setearEspecialidad_actY();
    }

    public void setearEspecialidad_actY() {
        b_ide = this.getC_id_espe();
    }

    public void setearEspecialidad_buscar() {
        b_ide = this.getC_id_espe();
    }

    public SelectItem[] getComboEspecialidad_buscar() throws Exception {
        cambiarEspecialidad_buscar(b_idf);
        return comboEspecialidad_buscar;
    }

    public void setComboEspecialidad_buscar(SelectItem[] comboEspecialidad_buscar) {
        this.comboEspecialidad_buscar = comboEspecialidad_buscar;
    }

    public void cambiarEspecialidad_buscar(int id) throws Exception {
        HSEspecialidadDAO dao = (HSEspecialidadDAO) ServiceFinder.findBean("SpringHibernateDaoEspecialidad");
        List lista = dao.seleccionarEspecialidad(id);
        SelectItem[] comboEspecialidad_buscar = new SelectItem[lista.size() + 1];
        comboEspecialidad_buscar[0] = new SelectItem(0, "[Seleccione]");
        for (int i = 0; i < comboEspecialidad_buscar.length - 1; i++) {
            comboEspecialidad_buscar[i + 1] = new SelectItem(((AcEspecialidad) lista.get(i)).getId(), ((AcEspecialidad) lista.get(i)).getEspNombre());
        }
        this.setComboEspecialidad_buscar(comboEspecialidad_buscar);
    }

    public SelectItem[] getComboSemestre() throws Exception {
        HSSemestreDAO dao = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
        List lista = dao.seleccionarSemestreActivo();
        int num = lista.size();
        SelectItem[] comboCurso;
        if (num > num_reg_sem) {
            comboCurso = new SelectItem[11];
        } else {
            comboCurso = new SelectItem[lista.size() + 1];
        }
        comboCurso[0] = new SelectItem(0, "Todos");
        for (int i = 0; i < comboCurso.length - 1; i++) {
            comboCurso[i + 1] = new SelectItem(((AcSemestre) lista.get(i)).getId(), ((AcSemestre) lista.get(i)).getSemNombre());
        }
        return comboCurso;
    }

    public void setComboSemestre(SelectItem[] comboSemestre) {
        this.comboSemestre = comboSemestre;
    }
    ///////

    public SelectItem[] getComboConceptoPago() throws Exception {
        HSConceptoPagoDAO dao = (HSConceptoPagoDAO) ServiceFinder.findBean("SpringHibernateDaoConceptoPago");
        List lista = dao.seleccionarConceptoPago();
        SelectItem[] comboCurso;
        comboCurso = new SelectItem[lista.size() + 1];
        comboCurso[0] = new SelectItem(0, "[Seleccionar]");
        for (int i = 0; i < comboCurso.length - 1; i++) {
            comboCurso[i + 1] = new SelectItem(((AdConceptoPago) lista.get(i)).getId(), (((AdConceptoPago) lista.get(i)).getConpagDescripcion() + " ... S/. " + ((AdConceptoPago) lista.get(i)).getConpagMonto()));
        }
        return comboCurso;
    }

    public void setComboConceptoPago(SelectItem[] comboConceptoPago) {
        this.comboConceptoPago = comboConceptoPago;
    }

    public void Nuevo() {
        for (int x = 0; x < detalle.size(); x++) {
            detalle.remove(x);
        }
        detalle.clear();
        id_aux = 0;
        id_aux2 = 0;
        detalle.clear();
        this.setId_i(0);
        this.setId_det(0);
        this.setDes_i("");
        this.setFac_id_ix(0);
        this.setFac_id_i(0);
        this.setSem_id_i(0);
        this.setSem_id_ix(0);
        this.setSem_id_d(0);
        this.setSem_d("0");
        this.setDes_ix("");
        fac_aux = "";
        esp_aux = "";
        fac_id_aux = 0;
        esp_id_aux = 0;
        sem_aux = "";
        sem_id_aux = 0;
        des_aux = "";
        sem_aux = "";
        sem_id_aux = 0;
        setearFacultad_i();
        this.setCon_pag_id_det(0);
        this.setMonto_det(0);
        this.setFecha_det(null);
        this.setNombre_det("");
        detalle.clear();
        if (this.getTablaSisEvaDetalle() != null) {
            this.getTablaSisEvaDetalle().clear();
        }
    }

    public void Seleccionar() throws Exception {
        HSEstructuraPagoDAO dao = (HSEstructuraPagoDAO) ServiceFinder.findBean("SpringHibernateDaoEstructuraPagos");
        HSEspecialidadDAO daoEsp = (HSEspecialidadDAO) ServiceFinder.findBean("SpringHibernateDaoEspecialidad");
        HSConceptoPagoDAO daoConPago = (HSConceptoPagoDAO) ServiceFinder.findBean("SpringHibernateDaoConceptoPago");

        tablaSisEva = new ArrayList();
        List lista = dao.seleccionarEstructuraPagos(this.getC_id_espe(), this.getC_id_semestre());
        bEstructuraPago evaluacion;
        for (int i = 0; i < lista.size(); i++) {
            evaluacion = new bEstructuraPago();
            evaluacion.setId_s(((AdEstructuraPagos) lista.get(i)).getId());
            evaluacion.setCodigo_s(((AdEstructuraPagos) lista.get(i)).getEstpagoNombre());

            evaluacion.setEsp_s(((AdEstructuraPagos) lista.get(i)).getEsp().getEspNombre());
            evaluacion.setEsp_id_s(((AdEstructuraPagos) lista.get(i)).getEsp().getId());
            evaluacion.setFac_id_s(((AdEstructuraPagos) lista.get(i)).getEsp().getFac().getId());
            evaluacion.setFac_s(((AdEstructuraPagos) lista.get(i)).getEsp().getFac().getFacNombre());

            evaluacion.setNombre_s(((AdEstructuraPagos) lista.get(i)).getEstpagoNombre());
            evaluacion.setSem_s(((AdEstructuraPagos) lista.get(i)).getSem().getSemNombre());
            evaluacion.setSem_id_s(((AdEstructuraPagos) lista.get(i)).getSem().getId());
            evaluacion.setSem_d(((AdEstructuraPagos) lista.get(i)).getSemIn().getSemNombre());

            evaluacion.setSem_id_d(((AdEstructuraPagos) lista.get(i)).getSemIn().getId());

            if (((AdEstructuraPagos) lista.get(i)).getEstpagoPublicado().equals("0")) {
                evaluacion.setPublicado_s("NO");
                //evaluacion.setMensaje_publi("javascript:alert('No se pudo Publicar esta Estructura de pagos')");
            } else {
                evaluacion.setPublicado_s("SI");
                evaluacion.setIma_publi("/Imagenes/actions/matricula_gris.png");
                evaluacion.setIma_publi2("/Imagenes/actions/preg_gris.png");
                evaluacion.setIma_edit("/Imagenes/actions/editpaste_gris.png");
                evaluacion.setIma_delete("/Imagenes/actions/no_gris.png");
                evaluacion.setDisabled_publi("true");
                evaluacion.setDisabled_publi2(true);
                evaluacion.setDisabled_edit("");
                evaluacion.setDisabled_delete("true");
                //evaluacion.setMensaje_publi("javascript:alert('Ingreso')");
                //evaluacion.setMensaje_publi("javascript:alert('La Estructura de Pagos fue Publicada Correctamente')");

            }
            Set<AdEstructuraPagosDetalle> sisevadet = new LinkedHashSet<AdEstructuraPagosDetalle>();
            sisevadet = ((AdEstructuraPagos) lista.get(i)).getAdEstructuraPagosDetalles();
            List list = Collections.synchronizedList(new LinkedList(sisevadet));
            List a = new ArrayList();
            if (list.size() != 0) {
                bEstructuraPago siseva;
                for (int j = 0; j < list.size(); j++) {
                    siseva = new bEstructuraPago();
                    siseva.setId_det(((AdEstructuraPagosDetalle) list.get(j)).getId());

                    siseva.setCon_pag_id_det(((AdEstructuraPagosDetalle) list.get(j)).getConpagId());
                    List listaConPago = daoConPago.seleccionarUnaConceptoPago(((AdEstructuraPagosDetalle) list.get(j)).getConpagId());
                    siseva.setCon_pag_det(((AdConceptoPago) listaConPago.get(0)).getConpagDescripcion());
                    siseva.setMonto_ref_det(((AdConceptoPago) listaConPago.get(0)).getConpagMonto());
                    siseva.setCodigo_ref_det(((AdConceptoPago) listaConPago.get(0)).getConpagCodigo());

                    siseva.setFecha_det((((AdEstructuraPagosDetalle) list.get(j)).getEstpagdetFechaPago()));
                    siseva.setMonto_det(((AdEstructuraPagosDetalle) list.get(j)).getEstpagdetMonto());
                    siseva.setActivo_det(((AdEstructuraPagosDetalle) list.get(j)).getEstpagdetActivo());
                    siseva.setNombre_det(((AdEstructuraPagosDetalle) list.get(j)).getEstpagdetNombre());
                    if (((AdEstructuraPagosDetalle) list.get(j)).getEstpagdetActivo().equals("1")) {
                        a.add(siseva);
                    }
                }
            }
            evaluacion.setDetalle_s(a);
            tablaSisEva.add(evaluacion);
        }
        this.setTablaSisEva(tablaSisEva);
    }

    public void Insertar(ActionEvent event) {
        id_aux = 0;
        if ((this.getDes_i().trim()).length() == 0 || this.getEsp_id_i() <= 0 || this.getSem_id_i() <= 0 || this.getSem_id_d() <= 0) {
            this.setOncomplete("javascript:alert('Ingrese todos los datos Requeridos.')");
        } else {
            HSEstructuraPagoDAO dao = (HSEstructuraPagoDAO) ServiceFinder.findBean("SpringHibernateDaoEstructuraPagos");
            AdEstructuraPagos estpag = new AdEstructuraPagos();
            estpag.setId(this.getId_i());
            estpag.setEstpagoNombre(this.getDes_i());
            AcEspecialidad espe = new AcEspecialidad();
            espe.setId(this.getEsp_id_i());
            estpag.setEsp(espe);
            estpag.setEstpagoPublicado("0");
            estpag.setEstpagActivo("1");
            AcSemestre sem = new AcSemestre();
            sem.setId(this.getSem_id_i());
            estpag.setSem(sem);
            AcSemestre semI = new AcSemestre();
            semI.setId(this.getSem_id_d());
            estpag.setSemIn(semI);
            Set<AdEstructuraPagosDetalle> lista_detalle = new LinkedHashSet<AdEstructuraPagosDetalle>();
            for (int i = 0; i < detalle.size(); i++) {
                AdEstructuraPagosDetalle estpagdet = new AdEstructuraPagosDetalle();
                if (((bEstructuraPago) detalle.get(i)).getId_det() > 0) {
                    estpagdet.setId(((bEstructuraPago) detalle.get(i)).getId_det());
                }
                estpagdet.setEstpagdetNombre(((bEstructuraPago) detalle.get(i)).getNombre_det());
                estpagdet.setConpagId(((bEstructuraPago) detalle.get(i)).getCon_pag_id_det());
                estpagdet.setEstpagdetMonto(((bEstructuraPago) detalle.get(i)).getMonto_det());
                estpagdet.setEstpagdetFechaPago(((bEstructuraPago) detalle.get(i)).getFecha_det());
                estpagdet.setEstpagdetActivo(((bEstructuraPago) detalle.get(i)).getActivo_det());
                estpagdet.setEstpag(estpag);
                lista_detalle.add(estpagdet);
            }
            estpag.setAdEstructuraPagosDetalles(lista_detalle);
            if (id_aux2 == 0) {
                dao.insertarEstructuraPagos(estpag);
                this.setOncomplete("javascript:alert('Se creo una Estructura de Pagos correctamente.');Richfaces.hideModalPanel('mp')");
            } else {
                dao.actualizarEstructuraPagos(estpag);
                this.setOncomplete("javascript:alert('Se Actualizaron los datos de la Estructura de Pagos correctamente.');Richfaces.hideModalPanel('mp')");
            }
        }
    }

    public void Editar(ActionEvent event) throws Exception {
        detalle.clear();
        this.setCon_pag_id_det(0);
        this.setMonto_det(0);
        this.setFecha_det(null);
        this.setNombre_det("");

        List a = new ArrayList();
        UIParameter id = (UIParameter) event.getComponent().findComponent("id_s");
        /*UIParameter fac=(UIParameter)event.getComponent().findComponent("fac_s");
        UIParameter esp=(UIParameter)event.getComponent().findComponent("esp_s");
        UIParameter fac_id=(UIParameter)event.getComponent().findComponent("fac_id_s");
        UIParameter esp_id=(UIParameter)event.getComponent().findComponent("esp_id_s");
        UIParameter sem=(UIParameter)event.getComponent().findComponent("sem_s");
        UIParameter sem_id=(UIParameter)event.getComponent().findComponent("sem_id_s");
        UIParameter des=(UIParameter)event.getComponent().findComponent("des_s");
        UIParameter view=(UIParameter)event.getComponent().findComponent("view_s");

        UIParameter sem_d=(UIParameter)event.getComponent().findComponent("sem_d");
        UIParameter sem_id_d=(UIParameter)event.getComponent().findComponent("sem_id_d");*/

        this.setView("false");
        this.setEditable("true");

        id_aux = Integer.parseInt(id.getValue().toString());
        id_aux2 = id_aux;
        /*
        id_aux2=Integer.parseInt(id.getValue().toString());
        fac_aux=fac.getValue().toString();
        esp_aux=esp.getValue().toString();
        fac_id_aux=Integer.parseInt(fac_id.getValue().toString());
        esp_id_aux=Integer.parseInt(esp_id.getValue().toString());
        sem_aux=sem.getValue().toString();
        sem_id_aux=Integer.parseInt(sem_id.getValue().toString());
        des_aux=des.getValue().toString();
        sem_aux_d =sem_d.getValue().toString();
        sem_id_d_aux =Integer.parseInt(sem_id_d.getValue().toString());
         */

        HSEstructuraPagoDAO dao = (HSEstructuraPagoDAO) ServiceFinder.findBean("SpringHibernateDaoEstructuraPagos");
        HSConceptoPagoDAO daoConPago = (HSConceptoPagoDAO) ServiceFinder.findBean("SpringHibernateDaoConceptoPago");
        List lista = dao.seleccionarUnaEstructuraPagos(id_aux);


        /* Llenar variable para mostrar estrutura de pagos*/
        AdEstructuraPagos ep = (AdEstructuraPagos) lista.get(0);
        this.setId_i(ep.getId());
        this.setFac_i(ep.getEsp().getFac().getId().toString());
        this.setFac_id_i(ep.getEsp().getFac().getId());
        this.setEsp_i(ep.getEsp().getId().toString());
        this.setEsp_id_i(ep.getEsp().getId());
        this.setSem_i(ep.getSem().getId().toString());
        this.setSem_id_i(ep.getSem().getId());
        this.setSem_d(ep.getSemIn().getId().toString());
        this.setSem_id_d(ep.getSemIn().getId());
        this.setDes_i(ep.getEstpagoNombre());

        /*Fin Llenar estrutura de pagos*/
        Set<AdEstructuraPagosDetalle> sisevadet = new LinkedHashSet<AdEstructuraPagosDetalle>();
        sisevadet = ((AdEstructuraPagos) lista.get(0)).getAdEstructuraPagosDetalles();
        List list = Collections.synchronizedList(new LinkedList(sisevadet));
        List<bEstructuraPago> listEstPagDetalle = new ArrayList();
        if (list.size() != 0) {
            //System.out.println("TAMANO: "+ list.size());
            bEstructuraPago siseva;
            for (int i = 0; i < list.size(); i++) {
                siseva = new bEstructuraPago();
                siseva.setId_det(((AdEstructuraPagosDetalle) list.get(i)).getId());

                siseva.setCon_pag_id_det(((AdEstructuraPagosDetalle) list.get(i)).getConpagId());
                List listaConPago = daoConPago.seleccionarUnaConceptoPago(((AdEstructuraPagosDetalle) list.get(i)).getConpagId());
                siseva.setCon_pag_det(((AdConceptoPago) listaConPago.get(0)).getConpagDescripcion());
                siseva.setMonto_ref_det(((AdConceptoPago) listaConPago.get(0)).getConpagMonto());
                siseva.setCodigo_ref_det(((AdConceptoPago) listaConPago.get(0)).getConpagCodigo());

                siseva.setFecha_det((((AdEstructuraPagosDetalle) list.get(i)).getEstpagdetFechaPago()));
                siseva.setMonto_det(((AdEstructuraPagosDetalle) list.get(i)).getEstpagdetMonto());
                siseva.setActivo_det(((AdEstructuraPagosDetalle) list.get(i)).getEstpagdetActivo());
                siseva.setNombre_det(((AdEstructuraPagosDetalle) list.get(i)).getEstpagdetNombre());
                if (((AdEstructuraPagosDetalle) list.get(i)).getEstpagdetActivo().equals("1")) {
                    a.add(siseva);
                    listEstPagDetalle.add(siseva);
                }
            }
        }
        detalle = a;
        //this.setTablaSisEvaDetalle(listEstPagDetalle);
    }

    public void Eliminar(ActionEvent event) {
        UIParameter id = (UIParameter) event.getComponent().findComponent("id_s");
        HSEstructuraPagoDAO dao = (HSEstructuraPagoDAO) ServiceFinder.findBean("SpringHibernateDaoEstructuraPagos");
        dao.eliminarEstructuraPagos(Integer.parseInt(id.getValue().toString()));
    }

    public void Agregar() throws Exception {
        if ((this.getNombre_det().trim()).length() != 0 || this.getCon_pag_id_det() > 0 || this.getFecha_det() != null || this.getMonto_det() > 0) {
            if ((this.getNombre_det().trim()).length() == 0 || this.getCon_pag_id_det() <= 0 || this.getFecha_det() == null || this.getMonto_det() <= 0) {
                this.setOncomplete2("javascript:alert('Ud tiene que Ingrese todos los datos, para Agregar un Concepto.')");
            } else {
                bEstructuraPago evaluacion = new bEstructuraPago();
                evaluacion.setId_det(id_det);
                evaluacion.setNombre_det(this.getNombre_det());
                evaluacion.setCon_pag_id_det(this.getCon_pag_id_det());

                HSConceptoPagoDAO dao = (HSConceptoPagoDAO) ServiceFinder.findBean("SpringHibernateDaoConceptoPago");
                List lista = dao.seleccionarUnaConceptoPago(this.getCon_pag_id_det());
                evaluacion.setCodigo_ref_det(((AdConceptoPago) lista.get(0)).getConpagCodigo());
                evaluacion.setCon_pag_det(((AdConceptoPago) lista.get(0)).getConpagDescripcion());
                evaluacion.setMonto_ref_det(((AdConceptoPago) lista.get(0)).getConpagMonto());

                evaluacion.setFecha_det(this.getFecha_det());

                evaluacion.setMonto_det(this.getMonto_det());
                evaluacion.setActivo_det("1");
                detalle.add(evaluacion);
                this.setTablaSisEvaDetalle(detalle);
                id_det--;
                this.setOncomplete2("javascript:alert('Se Agrego un Concepto de Pago mas.')");

            }
        } else {
            this.setOncomplete2("javascript:alert('Ud tiene que Ingrese todos los datos, para Agregar un Concepto.')");
        }
    }

    public void Quitar(ActionEvent event) throws Exception {
        int variable = 0;
        //  System.out.println("el valor del detalñle -> "+detalle.size());
        UIParameter id = (UIParameter) event.getComponent().findComponent("id_det");
        int idDet = Integer.parseInt(id.getValue().toString());
        // System.out.println("el id detalle es -> "+idDet);
        bEstructuraPago evaluacion = new bEstructuraPago();
        evaluacion.setId_det(Integer.parseInt(id.getValue().toString()));
        for (int i = 0; i < detalle.size(); i++) {
            int idEs = ((bEstructuraPago) detalle.get(i)).getId_det();
            //   System.out.println(" for *> "+idEs);
            // if(Integer.parseInt(id.getValue().toString())==((bEstructuraPago)detalle.get(i)).getId_det())
            if (idDet == idEs) {
                // System.out.println("entro al if -> "+((bEstructuraPago)detalle.get(i)).getId_det());
                if (Integer.parseInt(id.getValue().toString()) > 0) {
                    // System.out.println("entro al if ->");
                    ((bEstructuraPago) detalle.get(i)).setActivo_det("0");
                } else {
                    System.out.println("No entro al if ");
                    detalle.remove(i);
                }
            }
        }

    }

    public void Ver(ActionEvent event) {
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
    }

    ////para una nueva estructura de pagos
    public SelectItem[] getComboFacultad_i() throws Exception {
        HSFacultadDAO dao = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
        List lista = dao.seleccionarFacultad("", "");
        SelectItem[] comboFacultades = new SelectItem[lista.size() + 1];
        comboFacultades[0] = new SelectItem(0, "[Seleccione]");
        for (int i = 0; i < comboFacultades.length - 1; i++) {
            comboFacultades[i + 1] = new SelectItem(((AcFacultad) lista.get(i)).getId(), ((AcFacultad) lista.get(i)).getFacNombre());
        }
        i_idf = this.getFac_id_i();
        return comboFacultades;
    }

    public void setComboFacultad_i(SelectItem[] comboFacultad_i) {
        this.comboFacultad_i = comboFacultad_i;
    }

    public void setearFacultad_i() {
        id_aux = 0;
        i_idf = this.getFac_id_i();
        this.setEsp_id_i(0);
        i_ide = 0;
        setearEspecialidad_xi();
    }

    public void setearEspecialidad_xi() {
        id_aux = 0;
        i_ide = this.getEsp_id_i();
    }

    public SelectItem[] getComboEspecialidad_i() throws Exception {
        cambiarEspecialidad_i(i_idf);
        return comboEspecialidad_i;
    }

    public void setComboEspecialidad_i(SelectItem[] comboEspecialidad_i) {
        this.comboEspecialidad_i = comboEspecialidad_i;
    }

    public void cambiarEspecialidad_i(int id) throws Exception {
        HSEspecialidadDAO dao = (HSEspecialidadDAO) ServiceFinder.findBean("SpringHibernateDaoEspecialidad");
        List lista = dao.seleccionarEspecialidad(id);
        SelectItem[] comboEspecialidad_i = new SelectItem[lista.size() + 1];
        comboEspecialidad_i[0] = new SelectItem(0, "[Seleccione]");
        for (int i = 0; i < comboEspecialidad_i.length - 1; i++) {
            comboEspecialidad_i[i + 1] = new SelectItem(((AcEspecialidad) lista.get(i)).getId(), ((AcEspecialidad) lista.get(i)).getEspNombre());
        }
        this.setComboEspecialidad_i(comboEspecialidad_i);
    }

    public SelectItem[] getComboSemestre_i() throws Exception {
        HSSemestreDAO dao = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
        List lista = dao.seleccionarSemestreActivo();
        SelectItem[] comboCurso;
        comboCurso = new SelectItem[lista.size() + 1];
        comboCurso[0] = new SelectItem(0, "[Seleccione]");
        for (int i = 0; i < comboCurso.length - 1; i++) {
            comboCurso[i + 1] = new SelectItem(((AcSemestre) lista.get(i)).getId(), ((AcSemestre) lista.get(i)).getSemNombre());
        }
        return comboCurso;
    }

    public void setComboSemestre_i(SelectItem[] comboSemestre_i) {
        this.comboSemestre_i = comboSemestre_i;
    }

    ////publicar
    public void Publicar(ActionEvent event) throws Exception {
        int x = 0;
        int publicado = 0;
        UIParameter id = (UIParameter) event.getComponent().findComponent("id_s");
        UIParameter esp_id = (UIParameter) event.getComponent().findComponent("esp_id_s");

        UIParameter des = (UIParameter) event.getComponent().findComponent("des_s");
        UIParameter sem_id = (UIParameter) event.getComponent().findComponent("sem_id_s");
        UIParameter sem_id_id = (UIParameter) event.getComponent().findComponent("sem_id_d");
        HSAlumnoDAO dao = (HSAlumnoDAO) ServiceFinder.findBean("SpringHibernateDaoAlumno");
        HSEstructuraPagoDAO daoEst = (HSEstructuraPagoDAO) ServiceFinder.findBean("SpringHibernateDaoEstructuraPagos");
        //List lista = dao.seleccionarAlumnoEstPago(Integer.parseInt(esp_id.getValue().toString()), "009001", Integer.parseInt(sem_id_id.getValue().toString()));
        List lista = dao.seleccionarAlumnoEstPago(Integer.parseInt(esp_id.getValue().toString()), "009001", Integer.parseInt(sem_id_id.getValue().toString()),Integer.parseInt(sem_id.getValue().toString()));

        List lista2 = daoEst.seleccionarUnaEstructuraPagos(Integer.parseInt(id.getValue().toString()));
        Set<AdEstructuraPagosDetalle> estdet = new LinkedHashSet<AdEstructuraPagosDetalle>();
        estdet = ((AdEstructuraPagos) lista2.get(0)).getAdEstructuraPagosDetalles();
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

                        alTa.setAlutarEstado("0");
                        alTa.setAlutarMonto(((AdEstructuraPagosDetalle) list.get(j)).getEstpagdetMonto());
                        alTa.setAlutarMontoCopy(((AdEstructuraPagosDetalle) list.get(j)).getEstpagdetMonto());
                        if (((AcAlumno) lista.get(i)).getAluProcedencia() != null) {
                            if (((AcAlumno) lista.get(i)).getAluProcedencia().equals("023001")
                                    || ((AcAlumno) lista.get(i)).getAluProcedencia().equals("023002")
                                    || ((AcAlumno) lista.get(i)).getAluProcedencia().equals("023003")
                                    || ((AcAlumno) lista.get(i)).getAluProcedencia().equals("023004")
                                    || ((AcAlumno) lista.get(i)).getAluProcedencia().equals("023014")
                                    || ((AcAlumno) lista.get(i)).getAluProcedencia().equals("023010")) {
                                if (((AcAlumno) lista.get(i)).getAluMontoPago() > 0) {
                                    //System.out.println(((AcAlumno) lista.get(i)).getAluMontoPago());
                                    alTa.setAlutarMonto(((AcAlumno) lista.get(i)).getAluMontoPago());
                                    alTa.setAlutarMontoCopy(((AcAlumno) lista.get(i)).getAluMontoPago());
                                    if (((AdEstructuraPagosDetalle) list.get(j)).getConpagId() == 1 &&
                                            ((AcAlumno) lista.get(i)).getSemId()!=9 && ((AcAlumno) lista.get(i)).getAluProcedencia().equals("023014")) {
                                        alTa.setAlutarMonto(((AdEstructuraPagosDetalle) list.get(j)).getEstpagdetMonto());
                                        alTa.setAlutarMontoCopy(((AdEstructuraPagosDetalle) list.get(j)).getEstpagdetMonto());
                                        alTa.setAlutarEstado("0");

                                    }else  if (((AdEstructuraPagosDetalle) list.get(j)).getConpagId() == 1){
                                        alTa.setAlutarMonto(Float.parseFloat("0"));
                                        alTa.setAlutarMontoCopy(Float.parseFloat("0"));
                                        alTa.setAlutarEstado("2");
                                        }
                                }
                            }else{
                            if (((AcAlumno) lista.get(i)).getAluMontoPago() > 0 &&
                                    ((AdEstructuraPagosDetalle) list.get(j)).getConpagId() != 1 ) {
                                alTa.setAlutarMonto(((AcAlumno) lista.get(i)).getAluMontoPago());
                                alTa.setAlutarMontoCopy(((AcAlumno) lista.get(i)).getAluMontoPago());
                            }
                            }
                        }

                        AdConceptoPago con = new AdConceptoPago();
                        con.setId(((AdEstructuraPagosDetalle) list.get(j)).getConpagId());
                        alTa.setConpag(con);

                        alTa.setAlutarFechaPago(((AdEstructuraPagosDetalle) list.get(j)).getEstpagdetFechaPago());
                        alTa.setAlutarFechaProrroga(((AdEstructuraPagosDetalle) list.get(j)).getEstpagdetFechaPago());
                        alTa.setAlutarActivo("1");

                        listaPubli.add(alTa);
                    }
                }
            }
            HSAlumnoTarifaDAO daoTarifa = (HSAlumnoTarifaDAO) ServiceFinder.findBean("SpringHibernateDaoAlumnoTarifa");
            daoTarifa.insertarTotal(listaPubli);
            publicado = 1;
        }
        if (publicado == 1) {
            AdEstructuraPagos publi = new AdEstructuraPagos();
            publi.setId(Integer.parseInt(id.getValue().toString()));
            publi.setEstpagoNombre(des.getValue().toString());

            AcEspecialidad espe = new AcEspecialidad();
            espe.setId(Integer.parseInt(esp_id.getValue().toString()));
            publi.setEsp(espe);

            publi.setEstpagoPublicado("1");
            publi.setEstpagActivo("1");

            AcSemestre sem = new AcSemestre();
            sem.setId(Integer.parseInt(sem_id.getValue().toString()));
            publi.setSem(sem);
            AcSemestre sem2 = new AcSemestre();
            sem2.setId(Integer.parseInt(sem_id_id.getValue().toString()));
            publi.setSemIn(sem2);

            daoEst.actualizarEstructuraPagos(publi);
        }
        Seleccionar();
    }

    public String getSem_d() {
        return sem_d;
    }

    public void setSem_d(String sem_d) {
        this.sem_d = sem_d;
    }

    public int getSem_id_d() {
        return sem_id_d;
    }

    public void setSem_id_d(int sem_id_d) {
        this.sem_id_d = sem_id_d;
    }
}
