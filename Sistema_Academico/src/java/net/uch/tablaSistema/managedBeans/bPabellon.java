package net.uch.tablaSistema.managedBeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcLocal;
import net.uch.mapping.AcPabellon;
import net.uch.metodo.local.MetodosAca;
import net.uch.tablaSistema.hibernateSpringDao.HSLocalDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSPabellonDAO;

public class bPabellon {

    public int b_id;
    public String b_des;
    public String b_des_i;
    public String b_des_auxiliar;
    public String b_nom;
    public String b_nom_i;
    public String b_nom_auxiliar;
    public String b_activo;
    private String editable = "false";
    private String view = "true";
    private boolean visible = false;
    private List listaPabellon;
    private SelectItem[] comboLocales;
    private String b_local;
    private String b_local_i;
    private String b_local_aux;
    private String b_local_f;
    private SelectItem[] comboLocales_f;
    private String oncomplete;
    private boolean editable2 = false;

    MetodosAca metodo=new MetodosAca();

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

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public String getB_des() {
        return b_des;
    }

    public void setB_des(String b_des) {
        this.b_des = b_des;
    }

    public String getB_activo() {
        return b_activo;
    }

    public void setB_activo(String b_activo) {
        this.b_activo = b_activo;
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
        this.setB_des(this.getB_des_auxiliar());
        this.setView("true");
        this.setEditable("false");
        this.setEditable2(false);
        this.setVisible(false);
    }

    public void EliminarFila(ActionEvent event) throws Exception {
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        UIParameter component = (UIParameter) event.getComponent().findComponent("delete");
        Eliminar(component.getValue().toString());

    }

    public void Eliminar(String id) throws Exception {
        HSPabellonDAO dao = (HSPabellonDAO) ServiceFinder.findBean("SpringHibernateDaoPabellon");
        dao.eliminarPabellon("" + id);
    }

    public String Grabar() {
        HSPabellonDAO dao = (HSPabellonDAO) ServiceFinder.findBean("SpringHibernateDaoPabellon");
        if ((this.getB_nom_i().trim()).length() == 0) {
            this.setOncomplete("javascript:alert('Ingrese todos los datos Requeridos.')");
        } else {
            AcPabellon pabellon = new AcPabellon();
            pabellon.setPabNombre(this.getB_nom_i());
            pabellon.setPabDescripcion(this.getB_des_i());
            AcLocal loc = new AcLocal();
            loc.setId(Integer.parseInt(this.getB_local_i()));
            pabellon.setLoc(loc);
            pabellon.setPabActivo("1");
            dao.insertarPabellon(pabellon);
            this.setOncomplete("javascript:alert('Se creo un Pabellon correctamente.');Richfaces.hideModalPanel('mp')");
        }
        return "ok";

    }

    public void Seleccionar() throws Exception {
        listaPabellon = new ArrayList();
        HSPabellonDAO dao = (HSPabellonDAO) ServiceFinder.findBean("SpringHibernateDaoPabellon");
        List lista = dao.seleccionarPabellon(this.getB_des(), Integer.parseInt(this.getB_local_f()));
        bPabellon bL;
        for (int i = 0; i < lista.size(); i++) {
            bL = new bPabellon();
            bL.setB_id(((AcPabellon) lista.get(i)).getId());
            bL.setB_nom(((AcPabellon) lista.get(i)).getPabNombre());
            bL.setB_des(((AcPabellon) lista.get(i)).getPabDescripcion());
            AcLocal loc = new AcLocal();
            loc = ((AcPabellon) lista.get(i)).getLoc();
            bL.setB_local("" + loc.getId());
            listaPabellon.add(bL);
        }
        this.setListaPabellon(listaPabellon);
    }

    public void EditarFila(ActionEvent event) {
        this.setB_des_auxiliar(this.getB_des());
        this.setB_nom_auxiliar(this.getB_nom());
        this.setView("false");
        this.setEditable("true");
        this.setEditable2(true);
        this.setVisible(true);
    }

    public void Editar(ActionEvent event) {
        HSPabellonDAO dao = (HSPabellonDAO) ServiceFinder.findBean("SpringHibernateDaoPabellon");
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        UIParameter id = (UIParameter) event.getComponent().findComponent("id");
        UIParameter nombre = (UIParameter) event.getComponent().findComponent("nombre");
        UIParameter descripcion = (UIParameter) event.getComponent().findComponent("descripcion");
        UIParameter local = (UIParameter) event.getComponent().findComponent("local");

        if (nombre.getValue().toString().trim().length() == 0) {
            this.setOncomplete("javascript:alert('Ingrese Datos.')");
        } else {
            AcPabellon pabellon = new AcPabellon();
            pabellon.setId(Integer.parseInt(id.getValue().toString()));
            pabellon.setPabNombre(nombre.getValue().toString());
            pabellon.setPabDescripcion(descripcion.getValue().toString());
            AcLocal loc = new AcLocal();
            loc.setId(Integer.parseInt(local.getValue().toString()));
            pabellon.setLoc(loc);
            pabellon.setPabActivo("1");
            dao.actualizarPabellon(pabellon);
            this.setView("true");
            this.setEditable("false");
            this.setEditable2(false);
            this.setVisible(false);
            this.setOncomplete("javascript:alert('Se Actualizaron los Datos del Pabellon correctamente.')");
        }
    }

    public String getB_des_i() {
        return b_des_i;
    }

    public void setB_des_i(String b_des_i) {
        this.b_des_i = b_des_i;
    }

    public String getB_nom() {
        return b_nom;
    }

    public void setB_nom(String b_nom) {
        this.b_nom = b_nom;
    }

    public String getB_nom_i() {
        return b_nom_i;
    }

    public void setB_nom_i(String b_nom_i) {
        this.b_nom_i = b_nom_i;
    }

    public String getB_nom_auxiliar() {
        return b_nom_auxiliar;
    }

    public void setB_nom_auxiliar(String b_nom_auxiliar) {
        this.b_nom_auxiliar = b_nom_auxiliar;
    }

    public List getListaPabellon() {
        return listaPabellon;
    }

    public void setListaPabellon(List listaPabellon) {
        this.listaPabellon = listaPabellon;
    }

    public String getB_des_auxiliar() {
        return b_des_auxiliar;
    }

    public void setB_des_auxiliar(String b_des_auxiliar) {
        this.b_des_auxiliar = b_des_auxiliar;
    }

    public void Enviar1(ActionEvent event) throws Exception {
        Grabar();
    }

    public SelectItem[] getComboLocales() throws Exception {
        /*HSLocalDAO dao = (HSLocalDAO) ServiceFinder.findBean("SpringHibernateDaoLocal");
        List lista = dao.seleccionarLocal("");
        SelectItem[] comboLocales = new SelectItem[lista.size()];
        for (int i = 0; i < comboLocales.length; i++) {
            comboLocales[i] = new SelectItem(((AcLocal) lista.get(i)).getId(), ((AcLocal) lista.get(i)).getLocDes());
        }*/
        comboLocales=metodo.listarSedes("Seleccione");
        return comboLocales;
    }

    public void setComboLocales(SelectItem[] comboLocales) {
        this.comboLocales = comboLocales;
    }

    public String getB_local() {
        return b_local;
    }

    public void setB_local(String b_local) {
        this.b_local = b_local;
    }

    public String getB_local_i() {
        return b_local_i;
    }

    public void setB_local_i(String b_local_i) {
        this.b_local_i = b_local_i;
    }

    public String getB_local_aux() {
        return b_local_aux;
    }

    public void setB_local_aux(String b_local_aux) {
        this.b_local_aux = b_local_aux;
    }

    public String getB_local_f() {
        return b_local_f;
    }

    public void setB_local_f(String b_local_f) {
        this.b_local_f = b_local_f;
    }

    public SelectItem[] getComboLocales_f() throws Exception {
       /* HSLocalDAO dao = (HSLocalDAO) ServiceFinder.findBean("SpringHibernateDaoLocal");
      fdf  List lista = dao.seleccionarLocal("");
        SelectItem[] comboLocales_f = new SelectItem[lista.size() + 1];
        comboLocales_f[0] = new SelectItem(0, "TODOS");
        for (int i = 0; i < comboLocales_f.length - 1; i++) {
            comboLocales_f[i + 1] = new SelectItem(((AcLocal) lista.get(i)).getId(), ((AcLocal) lista.get(i)).getLocDes());
        }*/
        comboLocales_f=metodo.listarSedes("Todos");
        return comboLocales_f;
    }

    public void setComboLocales_f(SelectItem[] comboLocales_f) {
        this.comboLocales_f = comboLocales_f;
    }
}
