package net.uch.tablaSistema.managedBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcEspecialidad;
import net.uch.mapping.AcFacultad;
import net.uch.mapping.TbCatalogo;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSEspecialidadDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSFacultadDAO;

public class bEspecialidad {

    private int b_id;
    private String b_nombre;
    private String b_nombre_i;
    private String b_nombre_auxiliar;
    private String b_codigo;
    private String b_codigo_i;
    private String b_numero_periodos;
    private String b_numero_periodos_i;
    private String b_tipo_periodos;
    private String b_tipo_periodos_i;
    private String b_codigo_auxiliar;
    private Date b_fechacreacion;
    private Date b_fechacreacion_auxiliar;
    private String b_resolrectoral;
    private String b_resolrectoral_auxiliar;
    private int facultad;
    private int b_idfacultad;
    private int b_idfacultad_auxiliar;
    private String b_activo;
    private SelectItem[] comboTipos;
    private SelectItem[] comboFacultades;
    private List listaEspecialidad;
    private String editable = "false";
    private String view = "true";
    private boolean visible = false;
    private String oncomplete;
    private boolean editable2 = false;

    public int getFacultad() {
        return facultad;
    }

    public void setFacultad(int facultad) {
        this.facultad = facultad;
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

    public SelectItem[] getComboFacultades() throws Exception {
        HSFacultadDAO dao = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
        List lista = dao.seleccionarFacultad("", "");
        SelectItem[] comboFacultades = new SelectItem[lista.size() + 1];
        comboFacultades[0] = new SelectItem(0, "TODOS");
        for (int i = 0; i < comboFacultades.length - 1; i++) {
            comboFacultades[i + 1] = new SelectItem(((AcFacultad) lista.get(i)).getId(), ((AcFacultad) lista.get(i)).getFacNombre());
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
        this.b_id = b_id;
    }

    public String getB_nombre() {
        return b_nombre;
    }

    public void setB_nombre(String b_nombre) {
        this.b_nombre = b_nombre;
    }

    public String getB_codigo() {
        return b_codigo;
    }

    public void setB_codigo(String b_codigo) {
        this.b_codigo = b_codigo;
    }

    public Date getB_fechacreacion() {
        return b_fechacreacion;
    }

    public void setB_fechacreacion(Date b_fechacreacion) {
        this.b_fechacreacion = b_fechacreacion;
    }

    public String getB_resolrectoral() {
        return b_resolrectoral;
    }

    public void setB_resolrectoral(String b_resolrectoral) {
        this.b_resolrectoral = b_resolrectoral;
    }

    public int getB_idfacultad() {
        return b_idfacultad;
    }

    public void setB_idfacultad(int b_idfacultad) {
        this.b_idfacultad = b_idfacultad;
    }

    public String getB_activo() {
        return b_activo;
    }

    public void setB_activo(String b_activo) {
        this.b_activo = b_activo;
    }

    public String getB_nombre_i() {
        return b_nombre_i;
    }

    public void setB_nombre_i(String b_nombre_i) {
        this.b_nombre_i = b_nombre_i;
    }

    public String getB_codigo_i() {
        return b_codigo_i;
    }

    public void setB_codigo_i(String b_codigo_i) {
        this.b_codigo_i = b_codigo_i;
    }

    public String Grabar() {
        if ((this.getB_codigo_i().trim()).length() == 0 || (this.getB_nombre_i().trim()).length() == 0 || (this.getB_resolrectoral().trim()).length() == 0 || this.getB_fechacreacion() == null || this.getB_idfacultad() <= 0 || (this.getB_numero_periodos().trim()).length() <= 0) {
            this.setOncomplete("javascript:alert('Ingrese todos los datos Requeridos.')");
        } else {
            HSEspecialidadDAO dao = (HSEspecialidadDAO) ServiceFinder.findBean("SpringHibernateDaoEspecialidad");
            AcEspecialidad especialidad = new AcEspecialidad();
            especialidad.setId(this.getB_id());
            especialidad.setEspCodigo(this.getB_codigo_i());
            especialidad.setEspNombre(this.getB_nombre_i());
            especialidad.setEspResolrectoral(this.getB_resolrectoral());
            especialidad.setEspFechacreacion(this.getB_fechacreacion());
            AcFacultad fac = new AcFacultad();
            fac.setId(this.getB_idfacultad());
            especialidad.setFac(fac);
            especialidad.setEspNroperiodos(this.getB_numero_periodos());
            especialidad.setEspTipoperiodo(this.getB_tipo_periodos());
            especialidad.setEspActivo("1");
            dao.insertarEspecialidad(especialidad);
            this.setOncomplete("javascript:alert('Se creo una Especialidad correctamente.');Richfaces.hideModalPanel('mp')");
        }
        return "ok";
    }

    public void Seleccionar() throws Exception {
        listaEspecialidad = new ArrayList();
        HSEspecialidadDAO dao = (HSEspecialidadDAO) ServiceFinder.findBean("SpringHibernateDaoEspecialidad");
        System.out.println("llego");
        List lista = dao.seleccionarEspecialidad(this.getB_codigo(), this.getB_nombre(), this.getFacultad());
        System.out.println("convierte a lista");
        bEspecialidad bE;
        for (int i = 0; i < lista.size(); i++) {
            bE = new bEspecialidad();
            System.out.println("empezara a convertir");
            bE.setB_id(((AcEspecialidad) lista.get(i)).getId());
            bE.setB_codigo(((AcEspecialidad) lista.get(i)).getEspCodigo());
            bE.setB_nombre(((AcEspecialidad) lista.get(i)).getEspNombre());
            bE.setB_fechacreacion(((AcEspecialidad) lista.get(i)).getEspFechacreacion());
            AcFacultad fac = new AcFacultad();
            fac = ((AcEspecialidad) lista.get(i)).getFac();
            bE.setB_idfacultad(fac.getId());
            bE.setB_resolrectoral(((AcEspecialidad) lista.get(i)).getEspResolrectoral());
            bE.setB_numero_periodos(((AcEspecialidad) lista.get(i)).getEspNroperiodos());
            bE.setB_tipo_periodos(((AcEspecialidad) lista.get(i)).getEspTipoperiodo());
            listaEspecialidad.add(bE);
        }
        System.out.println("setea lista");
        this.setListaEspecialidad(listaEspecialidad);
    }

    public void Cancelar(ActionEvent event) {
        this.setB_fechacreacion(this.getB_fechacreacion_auxiliar());
        this.setB_codigo(this.getB_codigo_auxiliar());
        this.setB_nombre(this.getB_nombre_auxiliar());
        this.setB_resolrectoral(this.getB_resolrectoral_auxiliar());
        this.setB_idfacultad(this.getB_idfacultad_auxiliar());
        this.setB_numero_periodos(this.getB_numero_periodos_i());
        this.setB_tipo_periodos(this.getB_tipo_periodos_i());
        this.setView("true");
        this.setEditable("false");
        this.setEditable2(false);
        this.setVisible(false);
    }

    public void EditarFila(ActionEvent event) {
        this.setB_fechacreacion_auxiliar(this.getB_fechacreacion());
        this.setB_codigo_auxiliar(this.getB_codigo());
        this.setB_nombre_auxiliar(this.getB_nombre());
        this.setB_resolrectoral_auxiliar(this.getB_resolrectoral());
        this.setB_idfacultad_auxiliar(this.getB_idfacultad());
        this.setB_numero_periodos_i(this.getB_numero_periodos());
        this.setB_tipo_periodos_i(this.getB_tipo_periodos());
        this.setView("false");
        this.setEditable("true");
        this.setEditable2(true);
        this.setVisible(true);
    }

    public void Editar(ActionEvent event) {
        System.out.println("actualizar");
        HSEspecialidadDAO dao = (HSEspecialidadDAO) ServiceFinder.findBean("SpringHibernateDaoEspecialidad");
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        UIParameter id = (UIParameter) event.getComponent().findComponent("id");
        UIParameter codigo = (UIParameter) event.getComponent().findComponent("codigo");
        UIParameter nombre = (UIParameter) event.getComponent().findComponent("nombre");
        UIParameter resolucion = (UIParameter) event.getComponent().findComponent("resolucion");
        UIParameter facultad = (UIParameter) event.getComponent().findComponent("facultad");
        UIParameter numero = (UIParameter) event.getComponent().findComponent("numero_periodos");
        UIParameter tipo = (UIParameter) event.getComponent().findComponent("tipo_periodos");
        UIParameter creacion = (UIParameter) event.getComponent().findComponent("creacion");

        if ((codigo.getValue().toString().trim()).length() == 0 || (nombre.getValue().toString().trim()).length() == 0 || (resolucion.getValue().toString().trim()).length() == 0 || (java.util.Date) creacion.getValue() == null || Integer.parseInt(facultad.getValue().toString()) == 0 || (numero.getValue().toString().trim()).length() == 0) {
            this.setOncomplete("javascript:alert('Ingrese Datos.')");
        } else {
            AcEspecialidad especialidad = new AcEspecialidad();
            especialidad.setId(Integer.parseInt(id.getValue().toString()));
            especialidad.setEspCodigo(codigo.getValue().toString());
            especialidad.setEspNombre(nombre.getValue().toString());
            especialidad.setEspResolrectoral(resolucion.getValue().toString());
            especialidad.setEspFechacreacion((java.util.Date) creacion.getValue());
            AcFacultad fac = new AcFacultad();
            fac.setId(Integer.parseInt(facultad.getValue().toString()));
            especialidad.setFac(fac);
            especialidad.setEspNroperiodos(numero.getValue().toString());
            especialidad.setEspTipoperiodo(tipo.getValue().toString());
            especialidad.setEspActivo("1");
            dao.actualizarEspecialidad(especialidad);
            this.setView("true");
            this.setEditable("false");
            this.setEditable2(false);
            this.setVisible(false);
            this.setOncomplete("javascript:alert('Se Actualizaron los datos de la Especialidad correctamente.')");
        }
    }

    public void EliminarFila(ActionEvent event) throws Exception {
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        UIParameter delete = (UIParameter) event.getComponent().findComponent("delete");
        Eliminar(delete.getValue().toString());

    }

    public void Eliminar(String id) throws Exception {
        HSEspecialidadDAO dao = (HSEspecialidadDAO) ServiceFinder.findBean("SpringHibernateDaoEspecialidad");
        dao.eliminarEspecialidad(id);
    }

    public List getListaEspecialidad() {
        return listaEspecialidad;
    }

    public void setListaEspecialidad(List listaEspecialidad) {
        this.listaEspecialidad = listaEspecialidad;
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

    public String getB_nombre_auxiliar() {
        return b_nombre_auxiliar;
    }

    public void setB_nombre_auxiliar(String b_nombre_auxiliar) {
        this.b_nombre_auxiliar = b_nombre_auxiliar;
    }

    public String getB_codigo_auxiliar() {
        return b_codigo_auxiliar;
    }

    public void setB_codigo_auxiliar(String b_codigo_auxiliar) {
        this.b_codigo_auxiliar = b_codigo_auxiliar;
    }

    public Date getB_fechacreacion_auxiliar() {
        return b_fechacreacion_auxiliar;
    }

    public void setB_fechacreacion_auxiliar(Date b_fechacreacion_auxiliar) {
        this.b_fechacreacion_auxiliar = b_fechacreacion_auxiliar;
    }

    public String getB_resolrectoral_auxiliar() {
        return b_resolrectoral_auxiliar;
    }

    public void setB_resolrectoral_auxiliar(String b_resolrectoral_auxiliar) {
        this.b_resolrectoral_auxiliar = b_resolrectoral_auxiliar;
    }

    public int getB_idfacultad_auxiliar() {
        return b_idfacultad_auxiliar;
    }

    public void setB_idfacultad_auxiliar(int b_idfacultad_auxiliar) {
        this.b_idfacultad_auxiliar = b_idfacultad_auxiliar;
    }

    public void Enviar1(ActionEvent event) throws Exception {
        Grabar();
    }

    public String getB_numero_periodos() {
        return b_numero_periodos;
    }

    public void setB_numero_periodos(String b_numero_periodos) {
        this.b_numero_periodos = b_numero_periodos;
    }

    public String getB_numero_periodos_i() {
        return b_numero_periodos_i;
    }

    public void setB_numero_periodos_i(String b_numero_periodos_i) {
        this.b_numero_periodos_i = b_numero_periodos_i;
    }

    public String getB_tipo_periodos() {
        return b_tipo_periodos;
    }

    public void setB_tipo_periodos(String b_tipo_periodos) {
        this.b_tipo_periodos = b_tipo_periodos;
    }

    public String getB_tipo_periodos_i() {
        return b_tipo_periodos_i;
    }

    public void setB_tipo_periodos_i(String b_tipo_periodos_i) {
        this.b_tipo_periodos_i = b_tipo_periodos_i;
    }

    public SelectItem[] getComboTipos() throws Exception {
        HSCatalogoDAO dao = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        List lista = dao.seleccionarCatalogo("005");
        SelectItem[] comboTipos = new SelectItem[lista.size()];
        for (int i = 0; i < comboTipos.length; i++) {
            comboTipos[i] = new SelectItem(((TbCatalogo) lista.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista.get(i)).getCatDescripcionElemento());
        }
        return comboTipos;
    }

    public void setComboTipos(SelectItem[] comboTipos) {
        this.comboTipos = comboTipos;
    }
}
