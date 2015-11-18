package net.uch.tablaSistema.managedBeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcLocal;
import net.uch.tablaSistema.hibernateSpringDao.HSLocalDAO;

public class bLocal {

    public int b_id;
    public String b_des;
    public String b_des_i;
    public String b_des_auxiliar;
    public String b_activo;
    private String editable = "false";
    private String view = "true";
    private boolean visible = false;
    private List listaLocal;
    private String oncomplete;
    private boolean editable2 = false;

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }

    public boolean isEditable2() {
        return editable2;
    }

    public void setEditable2(boolean editable2) {
        this.editable2 = editable2;
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
        HSLocalDAO dao = (HSLocalDAO) ServiceFinder.findBean("SpringHibernateDaoLocal");
        dao.eliminarLocal("" + id);
    }

    public String Grabar() {
        HSLocalDAO dao = (HSLocalDAO) ServiceFinder.findBean("SpringHibernateDaoLocal");
        if ((this.getB_des_i().trim()).length() == 0) {
            this.setOncomplete("javascript:alert('Ingrese todos los datos Requeridos.')");
        } else {
            AcLocal local = new AcLocal();
            local.setLocDes(this.getB_des_i());
            local.setLocActivo("1");
            dao.insertarLocal(local);
            this.setOncomplete("javascript:alert('Se creo un Local correctamente.');Richfaces.hideModalPanel('mp')");
        }
        return "ok";
    }

    public void Seleccionar() throws Exception {
        listaLocal = new ArrayList();
        HSLocalDAO dao = (HSLocalDAO) ServiceFinder.findBean("SpringHibernateDaoLocal");
        List lista = dao.seleccionarLocal(this.getB_des());
        bLocal bL;
        for (int i = 0; i < lista.size(); i++) {
            bL = new bLocal();
            bL.setB_id(((AcLocal) lista.get(i)).getId());
            bL.setB_des(((AcLocal) lista.get(i)).getLocDes());
            listaLocal.add(bL);
        }
        this.setListaLocal(listaLocal);
    }

    public void EditarFila(ActionEvent event) {
        this.setB_des_auxiliar(this.getB_des());
        this.setView("false");
        this.setEditable("true");
        this.setEditable2(true);
        this.setVisible(true);
    }

    public void Editar(ActionEvent event) {
        HSLocalDAO dao = (HSLocalDAO) ServiceFinder.findBean("SpringHibernateDaoLocal");
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        UIParameter id = (UIParameter) event.getComponent().findComponent("id");
        UIParameter descripcion = (UIParameter) event.getComponent().findComponent("descripcion");

        if ((descripcion.getValue().toString().trim()).length() == 0) {
            this.setOncomplete("javascript:alert('Ingrese Descripcion.')");
        } else {
            AcLocal local = new AcLocal();
            local.setId(Integer.parseInt(id.getValue().toString()));
            local.setLocDes(descripcion.getValue().toString());
            local.setLocActivo("1");
            dao.actualizarLocal(local);
            this.setView("true");
            this.setEditable("false");
            this.setEditable2(false);
            this.setVisible(false);
            this.setOncomplete("javascript:alert('Se Actualizo la Descripcion del Lacal correctamente.')");
        }
    }

    public String getB_des_i() {
        return b_des_i;
    }

    public void setB_des_i(String b_des_i) {
        this.b_des_i = b_des_i;
    }

    public List getListaLocal() {
        return listaLocal;
    }

    public void setListaLocal(List listaLocal) {
        this.listaLocal = listaLocal;
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
}
