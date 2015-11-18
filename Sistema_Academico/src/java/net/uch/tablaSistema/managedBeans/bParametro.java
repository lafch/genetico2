package net.uch.tablaSistema.managedBeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIParameter;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcParametro;
import net.uch.tablaSistema.hibernateSpringDao.HSParametroDAO;

public class bParametro {

    public int b_id;
    public String b_codigo_filtro;
    public String b_valor_filtro;
    public String b_des;
    public String b_des_i;
    public String b_codigo;
    public String b_codigo_auxiliar;
    public String b_valor;
    public String b_valor_auxiliar;
    public String b_des_parametro;
    public String b_des_auxiliar;
    private int b_usu_id;

    public String b_activo;
    private List lstParametro;
    private String editable = "false";
    private String view = "true";
    private boolean visible = false;

    private String oncomplete;
    private boolean editable2 = false;

    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
    HttpSession session1 = (HttpSession) context.getSession(false);
    bUsuario usu = (bUsuario) session1.getAttribute("usuario");

    public void Nuevo() {
        this.setB_des_i("");
        this.setB_codigo("");
        this.setB_codigo_auxiliar("");
        this.setB_valor("");
        this.setB_valor_auxiliar("");
        this.setB_des("");
        this.setB_des_auxiliar("");
    }

    public bParametro() {
        //aula = new bAula();
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

    public List getLstParametro() {
        return lstParametro;
    }

    public void setLstParametro(List lstParametro) {
        this.lstParametro = lstParametro;
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

    public String getB_codigo_filtro() {
        return b_codigo_filtro;
    }

    public void setB_codigo_filtro(String b_codigo_filtro) {
        this.b_codigo_filtro = b_codigo_filtro;
    }

    public String getB_valor_filtro() {
        return b_valor_filtro;
    }

    public void setB_valor_filtro(String b_valor_filtro) {
        this.b_valor_filtro = b_valor_filtro;
    }

    public String getB_codigo() {
        return b_codigo;
    }

    public void setB_codigo(String b_codigo) {
        this.b_codigo = b_codigo;
    }

    public String getB_codigo_auxiliar() {
        return b_codigo_auxiliar;
    }

    public void setB_codigo_auxiliar(String b_codigo_auxiliar) {
        this.b_codigo_auxiliar = b_codigo_auxiliar;
    }

    public String getB_valor() {
        return b_valor;
    }

    public void setB_valor(String b_valor) {
        this.b_valor = b_valor;
    }

    public String getB_valor_auxiliar() {
        return b_valor_auxiliar;
    }

    public void setB_valor_auxiliar(String b_valor_auxiliar) {
        this.b_valor_auxiliar = b_valor_auxiliar;
    }

    public String getB_des_parametro() {
        return b_des_parametro;
    }

    public void setB_des_parametro(String b_des_parametro) {
        this.b_des_parametro = b_des_parametro;
    }

    public String getB_des_auxiliar() {
        return b_des_auxiliar;
    }

    public void setB_des_auxiliar(String b_des_auxiliar) {
        this.b_des_auxiliar = b_des_auxiliar;
    }

    public String getB_activo() {
        return b_activo;
    }

    public void setB_activo(String b_activo) {
        this.b_activo = b_activo;
    }

    public int getB_usu_id() {
        return b_usu_id;
    }

    public void setB_usu_id(int b_usu_id) {
        this.b_usu_id = b_usu_id;
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

    public String Grabar() {
        if ((this.getB_codigo().trim()).length() == 0) {
            this.setOncomplete("javascript:alert('Ingrese codigo')");
            return "fail";
        } else if ((this.getB_valor().trim()).length() <= 0) {
            this.setOncomplete("javascript:alert('Ingrese valor')");
            return "fail";
        } else {
            HSParametroDAO dao = (HSParametroDAO) ServiceFinder.findBean("SpringHibernateDaoParametro");
            AcParametro parCod = dao.buscarParametro(this.getB_codigo().toUpperCase());
            if (parCod != null) {
                this.setOncomplete("javascript:alert('Este codigo ya esta ingresado');");
                return "ok";
            } else {

                AcParametro par = new AcParametro();
                par.setId(this.getB_id());
                par.setParCod(this.getB_codigo().toUpperCase());
                par.setParVal(this.getB_valor().toUpperCase());
                par.setParDes(this.getB_des_i());
                par.setParUsuario(usu.getId_usu());
                par.setParActivo("1");
                dao.insertarParametro(par);
                this.setOncomplete("javascript:alert('Se creo un Parametro correctamente.');Richfaces.hideModalPanel('mp')");
                return "ok";

            }

        }
    }

    public void Seleccionar() throws Exception {
        lstParametro = new ArrayList();

        HSParametroDAO dao = (HSParametroDAO) ServiceFinder.findBean("SpringHibernateDaoParametro");
        List<AcParametro> lista = dao.seleccionarParametro(this.getB_codigo_filtro(), this.getB_valor_filtro());
        bParametro bA;
        for (int i = 0; i < lista.size(); i++) {

            AcParametro parametro = lista.get(i);
            bA = new bParametro();
            bA.setB_id(parametro.getId().intValue());
            bA.setB_codigo(parametro.getParCod());
            bA.setB_valor(parametro.getParVal());
            bA.setB_des(parametro.getParDes() == null ? "" : parametro.getParDes());
            bA.setB_usu_id(usu.getId_usu());
            
            lstParametro.add(bA);
        }
    }

    public void Cancelar(ActionEvent event) {
        this.setB_valor(this.getB_valor_auxiliar());
        this.setB_des(this.getB_des_auxiliar());
        this.setView("true");
        this.setEditable("false");
        this.setEditable2(false);
        this.setVisible(false);
    }

    public void EditarFila(ActionEvent event) {
        this.setB_des_auxiliar(this.getB_des());
        this.setB_valor_auxiliar(this.getB_valor());
        this.setView("false");
        this.setEditable("true");
        this.setEditable2(true);
        this.setVisible(true);
    }

    public void Editar(ActionEvent event) {
        UIParameter id = (UIParameter) event.getComponent().findComponent("id");
        UIParameter codigo = (UIParameter) event.getComponent().findComponent("codigo");
        UIParameter valor = (UIParameter) event.getComponent().findComponent("valor");
        UIParameter descripcion = (UIParameter) event.getComponent().findComponent("descripcion");
        UIParameter usuario = (UIParameter) event.getComponent().findComponent("usuario");

        if ((valor.getValue().toString().trim()).length() < 1) {
            this.setOncomplete("javascript:alert('Ingrese valor')");
        }else{
            int parId = Integer.parseInt(id.getValue().toString());
            AcParametro parametro = new AcParametro();
            parametro.setId(parId);
            parametro.setParCod(codigo.getValue().toString());
            parametro.setParDes(descripcion.getValue().toString() == null ? "" : descripcion.getValue().toString());
            parametro.setParUsuario(Integer.parseInt(usuario.getValue().toString()));

            parametro.setParVal(valor.getValue().toString());
            parametro.setParActivo("1");

            HSParametroDAO dao = (HSParametroDAO) ServiceFinder.findBean("SpringHibernateDaoParametro");
            dao.actualizarParametro(parametro);

            this.setView("true");
            this.setEditable("false");
            this.setEditable2(false);
            this.setVisible(false);
            this.setOncomplete("javascript:alert('Se Actualizaron los datos del Parametro correctamente.')");
        }
    }

    public void EliminarFila(ActionEvent event) throws Exception {
        UIParameter delete = (UIParameter) event.getComponent().findComponent("delete");
        Eliminar(Integer.parseInt(delete.getValue().toString()));
    }

    public void Eliminar(Integer id) throws Exception {
        HSParametroDAO dao = (HSParametroDAO) ServiceFinder.findBean("SpringHibernateDaoParametro");
        dao.eliminarParametro(id);
    }

    public String getB_des_i() {
        return b_des_i;
    }

    public void setB_des_i(String b_des_i) {
        this.b_des_i = b_des_i;
    }

    public void Enviar1(ActionEvent event) throws Exception {
        if (Grabar().equalsIgnoreCase("ok")) {
            Seleccionar();
        }
    }
}
