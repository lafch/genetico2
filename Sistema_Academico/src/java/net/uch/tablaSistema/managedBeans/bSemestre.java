package net.uch.tablaSistema.managedBeans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcSemestre;
import net.uch.tablaSistema.hibernateSpringDao.HSSemestreDAO;

public class bSemestre {

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private int b_id;
    private String b_codigo;
    private String b_codigo_i;
    private String b_nombre;
    private String b_nombre_i;
    private Date b_inicio;
    private Date b_fin;
    private Date b_ret_inc;
    private boolean b_vigente;
    private boolean b_actual;
    private String b_vigente_d;
    private String b_actual_d;
    private String b_activo;
    private List listaFacultad;
    private boolean editable = false;
    private boolean view = true;
    private boolean visible = false;
    private String b_codigo_auxiliar;
    private String b_pensiones_auxiliar;
    private String b_nombre_auxiliar;
    private Date inicio_auxiliar;
    private Date fin_auxiliar;
    private boolean vigente_auxiliar;
    private boolean actual_auxiliar;
    private static String denegado = "false";
    private int numero_semanas;
    private int numero_semanas_i;
    private int numero_semanas_auxiliar;
    private String numero_pensiones;
    private String b_pensiones_i;
    private String oncomplete;
    private boolean editable2 = false;

    public String getB_codigo() {
        return b_codigo;
    }

    public void setB_codigo(String b_codigo) {
        this.b_codigo = b_codigo;
    }

    public String getB_nombre() {
        return b_nombre;
    }

    public void setB_nombre(String b_nombre) {
        this.b_nombre = b_nombre;
    }

    public java.util.Date getB_inicio() {
        return b_inicio;
    }

    public void setB_inicio(java.util.Date b_inicio) {
        this.b_inicio = b_inicio;
    }

    public java.util.Date getB_fin() {
        return b_fin;
    }

    public void setB_fin(java.util.Date b_fin) {
        this.b_fin = b_fin;
    }

    public boolean isB_vigente() {
        return b_vigente;
    }

    public void setB_vigente(boolean b_vigente) {
        this.b_vigente = b_vigente;
    }

    public List getListaFacultad() {
        return listaFacultad;
    }

    public void setListaFacultad(List listaFacultad) {
        this.listaFacultad = listaFacultad;
    }

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public String getB_activo() {
        return b_activo;
    }

    public void setB_activo(String activo) {
        b_activo = activo;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isB_actual() {
        return b_actual;
    }

    public void setB_actual(boolean b_actual) {
        this.b_actual = b_actual;
    }

    public String getB_codigo_i() {
        return b_codigo_i;
    }

    public void setB_codigo_i(String b_codigo_i) {
        this.b_codigo_i = b_codigo_i;
    }

    public String getB_nombre_i() {
        return b_nombre_i;
    }

    public void setB_nombre_i(String b_nombre_i) {
        this.b_nombre_i = b_nombre_i;
    }

    public Date getInicio_auxiliar() {
        return inicio_auxiliar;
    }

    public void setInicio_auxiliar(Date inicio_auxiliar) {
        this.inicio_auxiliar = inicio_auxiliar;
    }

    public Date getFin_auxiliar() {
        return fin_auxiliar;
    }

    public void setFin_auxiliar(Date fin_auxiliar) {
        this.fin_auxiliar = fin_auxiliar;
    }

    public String getB_codigo_auxiliar() {
        return b_codigo_auxiliar;
    }

    public void setB_codigo_auxiliar(String b_codigo_auxiliar) {
        this.b_codigo_auxiliar = b_codigo_auxiliar;
    }

    public String getB_nombre_auxiliar() {
        return b_nombre_auxiliar;
    }

    public void setB_nombre_auxiliar(String b_nombre_auxiliar) {
        this.b_nombre_auxiliar = b_nombre_auxiliar;
    }

    public boolean isActual_auxiliar() {
        return actual_auxiliar;
    }

    public void setActual_auxiliar(boolean actual_auxiliar) {
        this.actual_auxiliar = actual_auxiliar;
    }

    public boolean isVigente_auxiliar() {
        return vigente_auxiliar;
    }

    public void setVigente_auxiliar(boolean vigente_auxiliar) {
        this.vigente_auxiliar = vigente_auxiliar;
    }

    public String getB_pensiones_auxiliar() {
        return b_pensiones_auxiliar;
    }

    public void setB_pensiones_auxiliar(String b_pensiones_auxiliar) {
        this.b_pensiones_auxiliar = b_pensiones_auxiliar;
    }

    public int getNumero_semanas() {
        return numero_semanas;
    }

    public void setNumero_semanas(int numero_semanas) {
        this.numero_semanas = numero_semanas;
    }

    public int getNumero_semanas_i() {
        return numero_semanas_i;
    }

    public void setNumero_semanas_i(int numero_semanas_i) {
        this.numero_semanas_i = numero_semanas_i;
    }

    public int getNumero_semanas_auxiliar() {
        return numero_semanas_auxiliar;
    }

    public void setNumero_semanas_auxiliar(int numero_semanas_auxiliar) {
        this.numero_semanas_auxiliar = numero_semanas_auxiliar;
    }

    public String getNumero_pensiones() {
        return numero_pensiones;
    }

    public void setNumero_pensiones(String numero_pensiones) {
        this.numero_pensiones = numero_pensiones;
    }

    public String getB_pensiones_i() {
        return b_pensiones_i;
    }

    public void setB_pensiones_i(String b_pensiones_i) {
        this.b_pensiones_i = b_pensiones_i;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public boolean isEditable2() {
        return editable2;
    }

    public void setEditable2(boolean editable2) {
        this.editable2 = editable2;
    }

    public void Cancelar(ActionEvent event) {
        this.setB_inicio(this.getInicio_auxiliar());
        this.setB_fin(this.getFin_auxiliar());
        this.setB_codigo(this.getB_codigo_auxiliar());
        this.setB_nombre(this.getB_nombre_auxiliar());
        this.setB_vigente(this.isVigente_auxiliar());
        this.setNumero_pensiones(this.getB_pensiones_auxiliar());
        this.setNumero_semanas(this.getNumero_semanas_auxiliar());
        this.setB_actual(this.isActual_auxiliar());
        this.setView(true);
        this.setEditable(false);
        this.setEditable2(false);
        this.setVisible(false);
    }

    public void EliminarFila(ActionEvent event) throws Exception {
        Eliminar(((UIParameter) event.getComponent().findComponent("delete")).getValue().toString());
    }

    public void Eliminar(String id) throws Exception {
        HSSemestreDAO dao = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
        dao.eliminarSemestre("" + id);
    }

    public String Grabar() throws Exception {
        denegado = "false";
        if ((this.getB_codigo_i().trim()).length() == 0 || (this.getB_nombre_i().trim()).length() == 0 || this.getB_inicio() == null || this.getB_fin() == null || this.getNumero_semanas_i() <= 0 || (this.getB_pensiones_i().trim()).length() <= 0) {
            this.setOncomplete("javascript:alert('Ingrese todos los datos Requeridos.')");
        } else {
            HSSemestreDAO dao2 = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
            List lista = dao2.seleccionarSemestre("", "");
            bSemestre bS;
            if (this.isB_vigente() == true) {
                for (int i = 0; i < lista.size(); i++) {
                    bS = new bSemestre();
                    if (((AcSemestre) lista.get(i)).getSemActual().equals("1")) {
                        denegado = "true";
                        break;
                    } else {
                        denegado = "false";
                    }
                }
            }
            if (denegado == "false") {
                HSSemestreDAO dao = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
                AcSemestre semestre = new AcSemestre();
                semestre.setSemCodigo(this.getB_codigo_i());
                semestre.setSemNombre(this.getB_nombre_i());
                semestre.setSemFechaInicio(this.getB_inicio());
                semestre.setSemFechaFin(this.getB_fin());
                semestre.setSemNumSemanas(this.getNumero_semanas_i());
                if (this.isB_vigente() == true) {
                    semestre.setSemActual("1");
                } else {
                    semestre.setSemActual("0");
                }
                semestre.setSemPensiones(this.getB_pensiones_i());

                if (this.isB_actual() == true) {
                    semestre.setSemVigente("1");
                } else {
                    semestre.setSemVigente("0");
                }
                semestre.setSemActivo("1");
                dao.insertarSemestre(semestre);
                this.setOncomplete("javascript:alert('Se creo un Semestre correctamente.');Richfaces.hideModalPanel('mp')");
            } else {
                this.setOncomplete("javascript:alert('Solo puede existir un semestre actual')");
            }
        }

        return "ok";
    }

    public void Seleccionar() throws Exception {
        listaFacultad = new ArrayList();
        HSSemestreDAO dao = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
        List lista = dao.seleccionarSemestre(this.getB_codigo(), this.getB_nombre());
        bSemestre bS;
        for (int i = 0; i < lista.size(); i++) {
            bS = new bSemestre();
            bS.setB_id(((AcSemestre) lista.get(i)).getId());
            bS.setB_codigo(((AcSemestre) lista.get(i)).getSemCodigo());
            bS.setB_nombre(((AcSemestre) lista.get(i)).getSemNombre());
            bS.setB_inicio(((AcSemestre) lista.get(i)).getSemFechaInicio());
            bS.setB_fin(((AcSemestre) lista.get(i)).getSemFechaFin());
            bS.setB_ret_inc(((AcSemestre) lista.get(i)).getSemFechaRetInc());
            bS.setNumero_semanas(((AcSemestre) lista.get(i)).getSemNumSemanas());
            bS.setNumero_pensiones(((AcSemestre) lista.get(i)).getSemPensiones());
            if (((AcSemestre) lista.get(i)).getSemActual().equals("1")) {
                bS.setB_vigente(true);
                bS.setB_vigente_d("SI");
            } else {
                bS.setB_vigente(false);
                bS.setB_vigente_d("NO");
            }
            if (((AcSemestre) lista.get(i)).getSemVigente().equals("1")) {
                bS.setB_actual(true);
                bS.setB_actual_d("SI");
            } else {
                bS.setB_actual(false);
                bS.setB_actual_d("NO");
            }
            listaFacultad.add(bS);
        }
        this.setListaFacultad(listaFacultad);
    }

    public void EditarFila(ActionEvent event) {
        this.setInicio_auxiliar(this.getB_inicio());
        this.setFin_auxiliar(this.getB_fin());
        this.setB_codigo_auxiliar(this.getB_codigo());
        this.setB_nombre_auxiliar(this.getB_nombre());
        this.setNumero_semanas_auxiliar(this.getNumero_semanas());
        this.setVigente_auxiliar(this.isB_vigente());
        this.setActual_auxiliar(this.isB_actual());
        this.setB_pensiones_auxiliar(this.getNumero_pensiones());
        this.setView(false);
        this.setEditable(true);
        this.setEditable2(true);
        this.setVisible(true);
    }

    public void Editar(ActionEvent event) throws Exception {
        denegado = "false";
        HSSemestreDAO dao2 = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
        List lista = dao2.seleccionarSemestre("", "");
        bSemestre bS;
        UIParameter vigente = (UIParameter) event.getComponent().findComponent("resolucion");
        UIParameter actual = (UIParameter) event.getComponent().findComponent("actual");
        UIParameter id = (UIParameter) event.getComponent().findComponent("id");
        if (vigente.getValue().toString() == "true") {
            for (int i = 0; i < lista.size(); i++) {
                bS = new bSemestre();
                if (((AcSemestre) lista.get(i)).getSemActual().equals("1") && ((AcSemestre) lista.get(i)).getId() != Integer.parseInt(id.getValue().toString())) {
                    denegado = "true";
                    break;
                } else {
                    denegado = "false";
                }
            }
        }
        if (denegado == "false") {
            HSSemestreDAO dao = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
            Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            UIParameter codigo = (UIParameter) event.getComponent().findComponent("codigo");
            UIParameter nombre = (UIParameter) event.getComponent().findComponent("nombre");
            UIParameter semanas = (UIParameter) event.getComponent().findComponent("semanas");
            UIParameter inicio = (UIParameter) event.getComponent().findComponent("inicio");
            UIParameter fin = (UIParameter) event.getComponent().findComponent("fin");
            UIParameter pensiones = (UIParameter) event.getComponent().findComponent("pensiones");

            if ((codigo.getValue().toString().trim()).length() == 0 || (nombre.getValue().toString().trim()).length() == 0 || Integer.parseInt(semanas.getValue().toString()) <= 0 || (java.util.Date) inicio.getValue() == null || (java.util.Date) fin.getValue() == null || (pensiones.getValue().toString().trim()).length() == 0) {
                this.setOncomplete("javascript:alert('Ingrese Datos.')");
            } else {
                AcSemestre semestre = new AcSemestre();
                semestre.setId(Integer.parseInt(id.getValue().toString()));
                semestre.setSemCodigo(codigo.getValue().toString());
                semestre.setSemNombre(nombre.getValue().toString());
                if (Boolean.parseBoolean(vigente.getValue().toString()) == true) {
                    semestre.setSemActual("1");
                } else {
                    semestre.setSemActual("0");
                }
                semestre.setSemFechaInicio((java.util.Date) inicio.getValue());
                semestre.setSemFechaFin((java.util.Date) fin.getValue());
                if (Boolean.parseBoolean(actual.getValue().toString()) == true) {
                    semestre.setSemVigente("1");
                } else {
                    semestre.setSemVigente("0");
                }
                semestre.setSemNumSemanas(Integer.parseInt(semanas.getValue().toString()));
                semestre.setSemPensiones(pensiones.getValue().toString());
                semestre.setSemActivo("1");
                dao.actualizarSemestre(semestre);
                this.setView(true);
                this.setEditable(false);
                this.setEditable2(false);
                this.setVisible(false);
                this.setOncomplete("javascript:alert('Se Actualizaron los datos del Semestre correctamente.')");
            }
        } else {
            this.setOncomplete("javascript:alert('Solo puede existir un semestre actual')");
        }
    }

    public void Enviar1(ActionEvent event) throws Exception {
        Grabar();
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public String getB_vigente_d() {
        return b_vigente_d;
    }

    public void setB_vigente_d(String b_vigente_d) {
        this.b_vigente_d = b_vigente_d;
    }

    public String getB_actual_d() {
        return b_actual_d;
    }

    public void setB_actual_d(String b_actual_d) {
        this.b_actual_d = b_actual_d;
    }

    public Date getB_ret_inc() {
        return b_ret_inc;
    }

    public void setB_ret_inc(Date b_ret_inc) {
        this.b_ret_inc = b_ret_inc;
    }
    
}
