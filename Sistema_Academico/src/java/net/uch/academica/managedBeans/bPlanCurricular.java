package net.uch.academica.managedBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.academica.hibernateSpringDao.HSPlanCurricularDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcEspecialidad;
import net.uch.mapping.AcFacultad;
import net.uch.mapping.AcPlancurricular;
import net.uch.tablaSistema.hibernateSpringDao.HSEspecialidadDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSFacultadDAO;

public class bPlanCurricular {

    private SelectItem[] comboFacultades;
    private SelectItem[] comboEspecialidades;
    private int b_id;
    private int b_id_u;
    private static int b_id_aux;
    private int b_facultad;
    private int b_facultad_u;
    private static int b_facultad_aux;
    private int b_especialidad;
    private int b_especialidad_u;
    private static int b_especialidad_aux;
    private String b_codigo;
    private String b_codigo_i;
    private static String b_codigo_aux;
    private String b_descripcion;
    private String b_descripcion_i;
    private static String b_descripcion_aux;
    private String b_creacion;
    private Date b_creacion_u;
    private static Date b_creacion_aux;
    private String b_resolucion;
    private String b_resolucion_u;
    private static String b_resolucion_aux;
    private String b_activo;
    private String combo_especialidad_id = "58";
    public static String estado = "0";
    private List listaPlanCurricular;
    public static String b_modificacion_aux = "0";
    private String combo_especialidad_id_s = "58";
    private SelectItem[] comboEspecialidades_s;
    private SelectItem[] comboFacultades_s;
    private String facultad_descripcion;
    private String especialidad_descripcion;
    private String oncomplete;

    public String getFacultad_descripcion() {
        return facultad_descripcion;
    }

    public void setFacultad_descripcion(String facultad_descripcion) {
        this.facultad_descripcion = facultad_descripcion;
    }

    public String getEspecialidad_descripcion() {
        return especialidad_descripcion;
    }

    public void setEspecialidad_descripcion(String especialidad_descripcion) {
        this.especialidad_descripcion = especialidad_descripcion;
    }

    public String getCombo_especialidad_id_s() {
        return combo_especialidad_id_s;
    }

    public void setCombo_especialidad_id_s(String combo_especialidad_id_s) {
        this.combo_especialidad_id_s = combo_especialidad_id_s;
    }

    public void Nuevo() {
        setB_id_aux(0);
        setB_facultad_aux(0);
        setB_especialidad_aux(0);
        setB_creacion_aux(null);
        setB_codigo_aux("");
        setB_descripcion_aux("");
        setB_resolucion_aux("");
        this.setB_id_u(0);
        this.setB_codigo_i("");
        this.setB_creacion_u(null);
        this.setB_descripcion_i("");
        this.setB_resolucion_u("");
        this.setB_facultad_u(0);
        this.setB_especialidad_u(0);
        setB_modificacion_aux("0");
        this.setCombo_especialidad_id("0");
        comboEspecialidades = new SelectItem[1];
        comboEspecialidades[0] = new SelectItem(0, "[Seleccione]");
    }

    public void setearFacultad() {
        this.setCombo_especialidad_id("" + this.getB_facultad_u());
    }

    public SelectItem[] getComboEspecialidades() throws Exception {
        cambiarEspecialidad(Integer.parseInt(this.getCombo_especialidad_id()));
        return comboEspecialidades;
    }

    public void setComboEspecialidades(SelectItem[] comboEspecialidades) {
        this.comboEspecialidades = comboEspecialidades;
    }

    public void cambiarEspecialidad(int id) throws Exception {
        HSEspecialidadDAO dao = (HSEspecialidadDAO) ServiceFinder.findBean("SpringHibernateDaoEspecialidad");
        List lista = dao.seleccionarEspecialidad(id);
        if (lista.size() != 0) {
            comboEspecialidades = new SelectItem[lista.size()];
            for (int i = 0; i < comboEspecialidades.length; i++) {
                comboEspecialidades[i] = new SelectItem(((AcEspecialidad) lista.get(i)).getId(), ((AcEspecialidad) lista.get(i)).getEspNombre());
            }
        } else {
            comboEspecialidades = new SelectItem[1];
            comboEspecialidades[0] = new SelectItem(0, "[Seleccione]");
        }
        this.setComboEspecialidades(comboEspecialidades);
    }

    public SelectItem[] getComboFacultades() throws Exception {
        HSFacultadDAO dao = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
        List lista = dao.seleccionarFacultad("", "");
        comboFacultades = new SelectItem[1];
        comboFacultades[0] = new SelectItem(0, "[Seleccione]");
        if (lista.size() != 0) {
            comboFacultades = new SelectItem[lista.size() + 1];
            comboFacultades[0] = new SelectItem(0, "Seleccione");
            for (int i = 0; i < comboFacultades.length - 1; i++) {
                comboFacultades[i + 1] = new SelectItem(((AcFacultad) lista.get(i)).getId(), ((AcFacultad) lista.get(i)).getFacNombre());
            }
        }
        return comboFacultades;
    }

    public void setComboFacultades(SelectItem[] comboFacultades) {
        this.comboFacultades = comboFacultades;
    }

    public int getB_id() {

        return b_id;
    }

    public void setB_id(int b_id) {
        System.out.println("hola");
        this.b_id = b_id;
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

    public String getB_codigo() {
        return b_codigo;
    }

    public void setB_codigo(String b_codigo) {
        this.b_codigo = b_codigo;
    }

    public String getB_descripcion() {
        return b_descripcion;
    }

    public void setB_descripcion(String b_descripcion) {
        this.b_descripcion = b_descripcion;
    }

    public String getB_creacion() {
        return b_creacion;
    }

    public void setB_creacion(String b_creacion) {
        this.b_creacion = b_creacion;
    }

    public String getB_resolucion() {
        return b_resolucion;
    }

    public void setB_resolucion(String b_resolucion) {
        this.b_resolucion = b_resolucion;
    }

    public String getB_activo() {
        return b_activo;
    }

    public void setB_activo(String b_activo) {
        this.b_activo = b_activo;
    }

    public List getListaPlanCurricular() {
        return listaPlanCurricular;
    }

    public void setListaPlanCurricular(List listaPlanCurricular) {
        this.listaPlanCurricular = listaPlanCurricular;
    }

    public String getB_codigo_i() {
        if (getB_modificacion_aux().equals("1")) {
            this.setB_codigo_i(getB_codigo_aux());
        }
        return b_codigo_i;
    }

    public void setB_codigo_i(String b_codigo_i) {
        this.b_codigo_i = b_codigo_i;
    }

    public String getB_descripcion_i() {
        if (getB_modificacion_aux().equals("1")) {
            System.out.println("descripcion:" + getB_descripcion_aux());
            this.setB_descripcion_i(getB_descripcion_aux());
        }
        return b_descripcion_i;
    }

    public void setB_descripcion_i(String b_descripcion_i) {
        this.b_descripcion_i = b_descripcion_i;
    }

    public String getCombo_especialidad_id() {
        return combo_especialidad_id;
    }

    public void setCombo_especialidad_id(String combo_especialidad_id) {
        this.combo_especialidad_id = combo_especialidad_id;
    }

    public int getB_id_u() {
        if (getB_modificacion_aux().equals("1")) {
            this.setB_id_u(getB_id_aux());
        }
        return b_id_u;
    }

    public void setB_id_u(int b_id_u) {
        this.b_id_u = b_id_u;
    }

    public static int getB_id_aux() {
        return b_id_aux;
    }

    public static void setB_id_aux(int b_id_aux) {
        bPlanCurricular.b_id_aux = b_id_aux;
    }

    public int getB_facultad_u() {
        if (getB_modificacion_aux().equals("1") && getEstado().equals("1")) {
            System.out.println("entrooooooooooooooooooooo1");
            this.setB_facultad_u(getB_facultad_aux());
        }
        return b_facultad_u;
    }

    public void setB_facultad_u(int b_facultad_u) {
        this.b_facultad_u = b_facultad_u;
    }

    public static int getB_facultad_aux() {
        return b_facultad_aux;
    }

    public static void setB_facultad_aux(int b_facultad_aux) {
        bPlanCurricular.b_facultad_aux = b_facultad_aux;
    }

    public int getB_especialidad_u() {
        if (getB_modificacion_aux().equals("1") && getEstado().equals("1")) {
            setEstado("0");
            System.out.println("entrooooooooooooooooooooo2");
            this.setB_especialidad_u(getB_especialidad_aux());
        }
        return b_especialidad_u;
    }

    public void setB_especialidad_u(int b_especialidad_u) {
        this.b_especialidad_u = b_especialidad_u;
    }

    public static int getB_especialidad_aux() {
        return b_especialidad_aux;
    }

    public static void setB_especialidad_aux(int b_especialidad_aux) {
        bPlanCurricular.b_especialidad_aux = b_especialidad_aux;
    }

    public static String getB_codigo_aux() {
        return b_codigo_aux;
    }

    public static void setB_codigo_aux(String b_codigo_aux) {
        bPlanCurricular.b_codigo_aux = b_codigo_aux;
    }

    public Date getB_creacion_u() {
        if (getB_modificacion_aux().equals("1")) {
            System.out.println("entro pidieron creacionnnnnnnn y seteara con:" + getB_creacion_aux());
            this.setB_creacion_u(getB_creacion_aux());
        }
        return b_creacion_u;
    }

    public void setB_creacion_u(Date b_creacion_u) {
        this.b_creacion_u = b_creacion_u;
    }

    public static Date getB_creacion_aux() {
        return b_creacion_aux;
    }

    public static void setB_creacion_aux(Date b_creacion_aux) {
        bPlanCurricular.b_creacion_aux = b_creacion_aux;
    }

    public String getB_resolucion_u() {
        if (getB_modificacion_aux().equals("1")) {
            this.setB_resolucion_u(getB_resolucion_aux());
        }
        return b_resolucion_u;
    }

    public void setB_resolucion_u(String b_resolucion_u) {
        this.b_resolucion_u = b_resolucion_u;
    }

    public static String getB_resolucion_aux() {
        return b_resolucion_aux;
    }

    public static void setB_resolucion_aux(String b_resolucion_aux) {
        bPlanCurricular.b_resolucion_aux = b_resolucion_aux;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public void Enviar() {
        if (getB_modificacion_aux().equals("1")) {
            Editar();
        } else {
            Grabar();
        }
    }

    public void Grabar() {
        if (this.getB_codigo_i().length() == 0 || this.getB_descripcion_i().length() == 0 || this.getB_facultad_u() == 0 || this.getB_especialidad_u() == 0) {
            this.setOncomplete("javascript:alert('Ingrese los datos necesarios.')");
        } else {
            HSPlanCurricularDAO dao = (HSPlanCurricularDAO) ServiceFinder.findBean("SpringHibernateDaoPlanCurricular");
            AcPlancurricular plancurricular = new AcPlancurricular();
            plancurricular.setId(this.getB_id());
            plancurricular.setPlanCodigo(this.getB_codigo_i());
            plancurricular.setPlanDescripcion(this.getB_descripcion_i());
            plancurricular.setPlanCreacion(this.getB_creacion_u());
            plancurricular.setPlanResolucion(this.getB_resolucion_u());
            AcEspecialidad esp = new AcEspecialidad();
            esp.setId(this.getB_especialidad_u());
            plancurricular.setEsp(esp);
            plancurricular.setPlanActivo("1");
            dao.insertarPlanCurricular(plancurricular);
            this.setOncomplete("javascript:alert('Se creo un Plan correctamente.');Richfaces.hideModalPanel('mp')");
        }
    }

    public void Seleccionar() throws Exception {
        listaPlanCurricular = new ArrayList();
        HSPlanCurricularDAO dao = (HSPlanCurricularDAO) ServiceFinder.findBean("SpringHibernateDaoPlanCurricular");
        List lista = dao.seleccionarPlanCurricular(this.getB_codigo(), this.getB_descripcion(), this.getB_facultad(), this.getB_especialidad());
        bPlanCurricular bPC;
        for (int i = 0; i < lista.size(); i++) {
            bPC = new bPlanCurricular();
            Object bC[] = (Object[]) lista.get(i);
            bPC.setB_id(Integer.parseInt("" + bC[0]));
            bPC.setB_codigo(bC[1].toString());
            bPC.setB_descripcion(bC[2].toString());
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            try {
                /*System.out.println("fecha:::::::::"+bC[3].toString());
                java.util.Date fecha = sdf.parse(bC[3].toString());
                System.out.println("fecha2222222:::::::::"+fecha);*/
                bPC.setB_creacion(bC[3].toString().substring(0, 10));
            } catch (Exception e) {
                System.out.println("Formato incorrecto de Fecha");
            }
            bPC.setB_facultad(Integer.parseInt(bC[4].toString()));
            bPC.setB_especialidad(Integer.parseInt(bC[5].toString()));
            bPC.setB_resolucion(bC[6].toString());
            System.out.println("holasssss" + bC[8].toString());
            bPC.setFacultad_descripcion(bC[8].toString());
            System.out.println("chaussss" + bC[9].toString());
            bPC.setEspecialidad_descripcion(bC[9].toString());
            listaPlanCurricular.add(bPC);
        }
        this.setListaPlanCurricular(listaPlanCurricular);
    }

    public void EliminarFila(ActionEvent event) throws Exception {
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        UIParameter delete = (UIParameter) event.getComponent().findComponent("delete");
        Eliminar(delete.getValue().toString());
    }

    public void Eliminar(String id) throws Exception {
        HSPlanCurricularDAO dao = (HSPlanCurricularDAO) ServiceFinder.findBean("SpringHibernateDaoPlanCurricular");
        dao.eliminarPlanCurricular(id);
    }

    public void CambiarCombo(ActionEvent event) throws Exception {
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        UIParameter combo = (UIParameter) event.getComponent().findComponent("combo");
        cambiarEspecialidad(Integer.parseInt(combo.getValue().toString()));
    }

    public void EditarFila(ActionEvent event) {
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        UIParameter id = (UIParameter) event.getComponent().findComponent("p_id");
        UIParameter codigo = (UIParameter) event.getComponent().findComponent("p_codigo");
        UIParameter descripcion = (UIParameter) event.getComponent().findComponent("p_descripcion");
        UIParameter facultad = (UIParameter) event.getComponent().findComponent("p_facultad");
        UIParameter especialidad = (UIParameter) event.getComponent().findComponent("p_especialidad");
        UIParameter resolucion = (UIParameter) event.getComponent().findComponent("p_resolucion");
        UIParameter creacion = (UIParameter) event.getComponent().findComponent("p_creacion");
        java.text.SimpleDateFormat sdf2 = new java.text.SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date fecha = sdf2.parse(creacion.getValue().toString());
            this.setB_creacion(creacion.getValue().toString());
            setB_creacion_aux(fecha);
        } catch (Exception e) {
            System.out.println("Formato incorrecto de Fechaxx");
        }
        this.setB_id(Integer.parseInt(id.getValue().toString()));
        this.setB_codigo(codigo.getValue().toString());
        this.setB_descripcion(descripcion.getValue().toString());
        this.setB_facultad(Integer.parseInt(facultad.getValue().toString()));
        this.setB_especialidad(Integer.parseInt(especialidad.getValue().toString()));
        this.setB_resolucion(resolucion.getValue().toString());
        setB_id_aux(Integer.parseInt(id.getValue().toString()));
        setB_codigo_aux(codigo.getValue().toString());
        setB_descripcion_aux(descripcion.getValue().toString());
        setB_facultad_aux(Integer.parseInt(facultad.getValue().toString()));
        setB_especialidad_aux(Integer.parseInt(especialidad.getValue().toString()));
        setB_resolucion_aux(resolucion.getValue().toString());
        setB_modificacion_aux("1");
        setEstado("1");
    }

    public void Editar() {
        setB_modificacion_aux("0");
        if (this.getB_codigo_i().length() == 0 || this.getB_descripcion_i().length() == 0 || this.getB_facultad_u() == 0 || this.getB_especialidad_u() == 0) {
            this.setOncomplete("javascript:alert('Ingrese los datos necesarios.')");
            setB_modificacion_aux("1");
        } else {
            HSPlanCurricularDAO dao = (HSPlanCurricularDAO) ServiceFinder.findBean("SpringHibernateDaoPlanCurricular");
            AcPlancurricular plan = new AcPlancurricular();
            plan.setId(this.getB_id_u());
            plan.setPlanCodigo(this.getB_codigo_i());
            plan.setPlanDescripcion(this.getB_descripcion_i());
            AcEspecialidad esp = new AcEspecialidad();
            esp.setId(this.getB_especialidad_u());
            plan.setEsp(esp);
            plan.setPlanResolucion(this.getB_resolucion_u());
            plan.setPlanCreacion(this.getB_creacion_u());
            plan.setPlanActivo("1");
            dao.actualizarPlanCurricular(plan);
            this.setOncomplete("javascript:alert('Se Actualizaron los datos del Plan correctamente.');Richfaces.hideModalPanel('mp')");
        }
    }

    public static String getB_modificacion_aux() {
        return b_modificacion_aux;
    }

    public static void setB_modificacion_aux(String b_modificacion_aux) {
        bPlanCurricular.b_modificacion_aux = b_modificacion_aux;
    }

    public static String getB_descripcion_aux() {
        return b_descripcion_aux;
    }

    public static void setB_descripcion_aux(String b_descripcion_aux) {
        bPlanCurricular.b_descripcion_aux = b_descripcion_aux;
    }

    public static String getEstado() {
        return estado;
    }

    public static void setEstado(String estado) {
        bPlanCurricular.estado = estado;
    }

    public void Enviar1(ActionEvent event) throws Exception {
        Enviar();
    }

    public void setearFacultad_s() {
        System.out.println("Settttttttttttttteano..........aaaaa");
        this.setCombo_especialidad_id_s("" + this.getB_facultad());
    }

    public void cambiarEspecialidad_s(int id) throws Exception {
        HSEspecialidadDAO dao = (HSEspecialidadDAO) ServiceFinder.findBean("SpringHibernateDaoEspecialidad");
        List lista = dao.seleccionarEspecialidad(id);
        if (lista.size() != 0) {
            comboEspecialidades_s = new SelectItem[lista.size() + 1];
            System.out.println("a1");
            for (int i = 0; i < comboEspecialidades_s.length - 1; i++) {
                comboEspecialidades_s[0] = new SelectItem(0, "TODOS");
                comboEspecialidades_s[i + 1] = new SelectItem(((AcEspecialidad) lista.get(i)).getId(), ((AcEspecialidad) lista.get(i)).getEspNombre());
            }
        } else {
            comboEspecialidades_s = new SelectItem[1];
            comboEspecialidades_s[0] = new SelectItem(0, "[Seleccione]");
        }
        this.setComboEspecialidades_s(comboEspecialidades_s);
    }

    public SelectItem[] getComboFacultades_s() throws Exception {
        HSFacultadDAO dao = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
        List lista = dao.seleccionarFacultad("", "");
        comboFacultades_s = new SelectItem[1];
        comboFacultades_s[0] = new SelectItem(0, "[Seleccione]");
        if (lista.size() != 0) {
            comboFacultades_s = new SelectItem[lista.size() + 1];
            for (int i = 0; i < comboFacultades_s.length - 1; i++) {
                comboFacultades_s[0] = new SelectItem(0, "TODOS");
                comboFacultades_s[i + 1] = new SelectItem(((AcFacultad) lista.get(i)).getId(), ((AcFacultad) lista.get(i)).getFacNombre());
            }
        }
        return comboFacultades_s;
    }

    public void setComboFacultades_s(SelectItem[] comboFacultades_s) {
        this.comboFacultades_s = comboFacultades_s;
    }

    public SelectItem[] getComboEspecialidades_s() throws Exception {
        System.out.println("entro al iffffffff");
        cambiarEspecialidad_s(Integer.parseInt(this.getCombo_especialidad_id_s()));
        return comboEspecialidades_s;
    }

    public void setComboEspecialidades_s(SelectItem[] comboEspecialidades_s) {
        this.comboEspecialidades_s = comboEspecialidades_s;
    }
}