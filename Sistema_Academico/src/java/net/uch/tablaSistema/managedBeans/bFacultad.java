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
import net.uch.mapping.AcFacultad;
import net.uch.tablaSistema.hibernateSpringDao.HSFacultadDAO;

public class bFacultad {

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private int b_id;
    private String b_codigo;
    private String b_codigo_i;
    private String b_nombre;
    private String b_nombre_i;
    private java.util.Date b_creacion;
    private String b_resolucion;
    private String b_activo;
    private List listaFacultad;
    private String editable = "false";
    private String view = "true";
    private boolean visible = false;
    private String b_codigo_auxiliar;
    private String b_nombre_auxiliar;
    private Date fecha_auxiliar;
    private String b_resolucion_auxiliar;
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

    public java.util.Date getB_creacion() {
        return b_creacion;
    }

    public void setB_creacion(java.util.Date b_creacion) {
        this.b_creacion = b_creacion;
    }

    public String getB_resolucion() {
        return b_resolucion;
    }

    public void setB_resolucion(String b_resolucion) {
        this.b_resolucion = b_resolucion;
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

    public Date getFechaAuxiliar() {
        return fecha_auxiliar;
    }

    public void setFechaAuxiliar(Date fecha_auxiliar) {
        this.fecha_auxiliar = fecha_auxiliar;
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

    public String getB_resolucion_auxiliar() {
        return b_resolucion_auxiliar;
    }

    public void setB_resolucion_auxiliar(String b_resolucion_auxiliar) {
        this.b_resolucion_auxiliar = b_resolucion_auxiliar;
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
        this.setB_creacion(this.getFechaAuxiliar());
        this.setB_codigo(this.getB_codigo_auxiliar());
        this.setB_nombre(this.getB_nombre_auxiliar());
        this.setB_resolucion(this.getB_resolucion_auxiliar());
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
        HSFacultadDAO dao = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
        dao.eliminarFacultad("" + id);
    }

    public String Grabar() {
        if ((this.getB_codigo_i().trim()).length() == 0 || (this.getB_nombre_i().trim()).length() == 0 || this.getB_creacion() == null || (this.getB_resolucion().trim()).length() == 0) {
            this.setOncomplete("javascript:alert('Ingrese todos los datos Requeridos.')");
        } else {
            HSFacultadDAO dao = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
            AcFacultad facultad = new AcFacultad();
            facultad.setFacCodigo(this.getB_codigo_i());
            facultad.setFacNombre(this.getB_nombre_i());
            facultad.setFacFechacreacion(this.getB_creacion());
            facultad.setFacResolrectoral(this.getB_resolucion());
            facultad.setFacActivo("1");
            dao.insertarFacultad(facultad);
            this.setOncomplete("javascript:alert('Se creo una Facultad correctamente.');Richfaces.hideModalPanel('mp')");
        }
        return "ok";
    }

    public void Seleccionar() throws Exception {
        listaFacultad = new ArrayList();
        HSFacultadDAO dao = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
        List lista = dao.seleccionarFacultad(this.getB_codigo(), this.getB_nombre());
        bFacultad bF;
        for (int i = 0; i < lista.size(); i++) {
            bF = new bFacultad();
            bF.setB_id(((AcFacultad) lista.get(i)).getId());
            bF.setB_codigo(((AcFacultad) lista.get(i)).getFacCodigo());
            bF.setB_nombre(((AcFacultad) lista.get(i)).getFacNombre());
            bF.setB_creacion(((AcFacultad) lista.get(i)).getFacFechacreacion());
            bF.setB_resolucion(((AcFacultad) lista.get(i)).getFacResolrectoral());
            listaFacultad.add(bF);
        }
        this.setListaFacultad(listaFacultad);
    }

    public void EditarFila(ActionEvent event) {
        this.setFechaAuxiliar(this.getB_creacion());
        this.setB_codigo_auxiliar(this.getB_codigo());
        this.setB_nombre_auxiliar(this.getB_nombre());
        this.setB_resolucion_auxiliar(this.getB_resolucion());
        this.setView("false");
        this.setEditable("true");
        this.setEditable2(true);
        this.setVisible(true);
    }

    public void Editar(ActionEvent event) {
        HSFacultadDAO dao = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        UIParameter id = (UIParameter) event.getComponent().findComponent("id");
        UIParameter codigo = (UIParameter) event.getComponent().findComponent("codigo");
        UIParameter nombre = (UIParameter) event.getComponent().findComponent("nombre");
        UIParameter resolucion = (UIParameter) event.getComponent().findComponent("resolucion");
        UIParameter creacion = (UIParameter) event.getComponent().findComponent("creacion");

        if ((codigo.getValue().toString().trim()).length() == 0 || (nombre.getValue().toString().trim()).length() == 0 || (resolucion.getValue().toString().trim()).length() == 0 || (java.util.Date) creacion.getValue() == null) {
            this.setOncomplete("javascript:alert('Ingrese Datos.')");
        } else {
            AcFacultad facultad = new AcFacultad();
            facultad.setId(Integer.parseInt(id.getValue().toString()));
            facultad.setFacCodigo(codigo.getValue().toString());
            facultad.setFacNombre(nombre.getValue().toString());
            facultad.setFacResolrectoral(resolucion.getValue().toString());
            facultad.setFacFechacreacion((java.util.Date) creacion.getValue());
            facultad.setFacActivo("1");
            dao.actualizarFacultad(facultad);
            this.setView("true");
            this.setEditable("false");
            this.setEditable2(false);
            this.setVisible(false);
            this.setOncomplete("javascript:alert('Se Actualizaron los datos de la Facultad correctamente.')");
        }
    }

    public void Enviar1(ActionEvent event) throws Exception {
        Grabar();
    }
}
