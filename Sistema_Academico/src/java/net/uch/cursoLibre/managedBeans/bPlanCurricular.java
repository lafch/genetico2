package net.uch.cursoLibre.managedBeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAcademicoClDao;
import net.uch.cursoLibre.hibernateSpringDao.HSPlanCurricularDAO;
import net.uch.mapping.ClArbolAcademico;
import net.uch.mapping.ClPlancurricular;
import net.uch.util.CommonDAO;

public class bPlanCurricular {

    //CAMPOS
    private int pla_id;
    private String pla_codigo;
    private String pla_descripcion;
    private int mod_id;
    private String mod_descripcion;
    private String pla_activo;
    private String pla_actual;
    private String imagen_pla_actual;
    private String pla_vigente;
    private String imagen_pla_vigente;
    private String b_descripcion;
    private int b_are_id = Integer.MAX_VALUE;
    private SelectItem[] b_areas;
    private int b_mod_id;
    private SelectItem[] b_modulos;
    private List<bPlanCurricular> planes = new ArrayList<bPlanCurricular>();
    private String oncomplete;
    private String oncomplete2;
    private int i_pla_id;
    private String i_pla_codigo;
    private String i_pla_descripcion;
    private String edit_disable;
    private int i_are_id;
    private SelectItem[] i_areas;
    private int i_mod_id;
    private SelectItem[] i_modulos;
    private String i_pla_vigente;
    private SelectItem[] i_vigentes;
    private String i_pla_actual;

    //CONSTRUCTORES
    public bPlanCurricular() {
    }

    public bPlanCurricular(int p) {
    }

    //METODOS
    public void Seleccionar(ActionEvent event) throws Exception {
        this.setOncomplete2("");
        if (this.b_mod_id == 0) {
            this.setOncomplete2("javascript:alert('Seleccione algun parametro para buscar.');");
        } else {
            HSPlanCurricularDAO dao = (HSPlanCurricularDAO) ServiceFinder.findBean("SpringHibernateDaoCLPlanCurricular");
            this.setPlanes(new ArrayList<bPlanCurricular>());

            List lista = dao.seleccionarPlanCurricular(this.getB_mod_id(), this.getB_descripcion());
            if (!lista.isEmpty()) {
                for (int i = 0; i < lista.size(); i++) {
                    bPlanCurricular plan = new bPlanCurricular(i);
                    plan.setPla_id(((ClPlancurricular) lista.get(i)).getPlaId());
                    plan.setPla_codigo(((ClPlancurricular) lista.get(i)).getPlaCodigo());
                    plan.setPla_descripcion(((ClPlancurricular) lista.get(i)).getPlaDescripcion());
                    plan.setMod_id(((ClPlancurricular) lista.get(i)).getClArbolAcademico().getArbId());
                    plan.setMod_descripcion(((ClPlancurricular) lista.get(i)).getClArbolAcademico().getArbDescripcion());
                    plan.setPla_activo(((ClPlancurricular) lista.get(i)).getPlaActivo());
                    plan.setPla_actual(((ClPlancurricular) lista.get(i)).getPlaActual());
                    plan.setPla_vigente(((ClPlancurricular) lista.get(i)).getPlaVigente());
                    if (((ClPlancurricular) lista.get(i)).getPlaActual().equals("1")) {
                        plan.setImagen_pla_actual("/Imagenes/actions/activar.png");
                    } else {
                        plan.setImagen_pla_actual("/Imagenes/actions/desactivar.png");
                    }
                    if (((ClPlancurricular) lista.get(i)).getPlaVigente().equals("1")) {
                        plan.setImagen_pla_vigente("/Imagenes/actions/activar.png");
                    } else {
                        plan.setImagen_pla_vigente("/Imagenes/actions/desactivar.png");
                    }
                    this.getPlanes().add(plan);
                }
            }
            

            /*HSPlanCurricularDAO dao = (HSPlanCurricularDAO) ServiceFinder.findBean("SpringHibernateDaoCLPlanCurricular");
            this.setPlanes(new ArrayList<bPlanCurricular>());

            List lista = dao.seleccionarPlanCurricular(this.getB_mod_id(), this.getB_descripcion());
            if (!lista.isEmpty()) {
                for (int i = 0; i < lista.size(); i++) {
                    bPlanCurricular plan = new bPlanCurricular(i);
                    plan.setPla_id(((ClPlancurricular) lista.get(i)).getPlaId());
                    plan.setPla_codigo(((ClPlancurricular) lista.get(i)).getPlaCodigo());
                    plan.setPla_descripcion(((ClPlancurricular) lista.get(i)).getPlaDescripcion());
                    plan.setMod_id(((ClPlancurricular) lista.get(i)).getClModulo().getModId());
                    plan.setMod_descripcion(((ClPlancurricular) lista.get(i)).getClModulo().getModDescripcion());
                    plan.setPla_activo(((ClPlancurricular) lista.get(i)).getPlaActivo());
                    plan.setPla_actual(((ClPlancurricular) lista.get(i)).getPlaActual());
                    plan.setPla_vigente(((ClPlancurricular) lista.get(i)).getPlaVigente());
                    if (((ClPlancurricular) lista.get(i)).getPlaActual().equals("1")) {
                        plan.setImagen_pla_actual("/Imagenes/actions/activar.png");
                    } else {
                        plan.setImagen_pla_actual("/Imagenes/actions/desactivar.png");
                    }
                    if (((ClPlancurricular) lista.get(i)).getPlaVigente().equals("1")) {
                        plan.setImagen_pla_vigente("/Imagenes/actions/activar.png");
                    } else {
                        plan.setImagen_pla_vigente("/Imagenes/actions/desactivar.png");
                    }
                    this.getPlanes().add(plan);
                }
            }*/
        }
    }

    public void Nuevo(ActionEvent event) throws Exception {
        this.setOncomplete("");
        this.setI_pla_id(0);
        this.setI_pla_codigo("");
        this.setI_pla_descripcion("");
        this.setI_are_id(0);
        this.setI_modulos(getI_modulos());
        this.setI_mod_id(0);
        this.setI_vigentes(getI_vigentes());
        this.setI_pla_vigente("1");
        this.setI_pla_actual("1");
        this.setEdit_disable("false");
        this.setOncomplete("Richfaces.showModalPanel('mpUpdate')");
    }

    public void ActualizarPlanCurricular(ActionEvent event) throws Exception {
        this.setOncomplete("");
        int id_update = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_update")).getValue().toString());
        HSPlanCurricularDAO dao = (HSPlanCurricularDAO) ServiceFinder.findBean("SpringHibernateDaoCLPlanCurricular");
        ClPlancurricular plan = dao.buscarPlanCurricular(id_update);
        
        ClArbolAcademico l = dao.buscarModulo(plan.getClArbolAcademico().getArbId());

        this.setI_pla_id(plan.getPlaId());
        this.setI_pla_codigo(plan.getPlaCodigo());
        this.setI_pla_descripcion(plan.getPlaDescripcion());
        this.setI_are_id(l.getArbIdPadre());
        
        /*this.i_are_id=getI_are_id();*/
        System.out.println("ingreso area 1: "+this.i_are_id);
        System.out.println("Modulo: "+plan.getClArbolAcademico().getArbId());
        this.setI_vigentes(getI_vigentes());
        this.setI_pla_vigente(plan.getPlaVigente());
        this.setI_pla_actual(plan.getPlaActual());
        this.setEdit_disable("true");
        this.setOncomplete("Richfaces.showModalPanel('mpUpdate')");
        if (plan.getClArbolAcademico() == null) {
            this.setI_modulos(getI_modulos());
            this.setI_mod_id(0);
        } else {
            this.setI_modulos(getI_modulos());
            this.setI_mod_id(plan.getClArbolAcademico().getArbId());
            System.out.println("modulo ok");
        }
    }

    public void EliminarPlanCurricular(ActionEvent event) throws Exception {
        int id_delete = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_delete")).getValue().toString());
        HSPlanCurricularDAO dao = (HSPlanCurricularDAO) ServiceFinder.findBean("SpringHibernateDaoCLPlanCurricular");
        dao.eliminarPlanCurricular(id_delete);

        Seleccionar(event);
    }

    public void GuardarPlanCurricular(ActionEvent event) throws Exception {
        this.setOncomplete("");
        if (this.getI_pla_codigo().trim().length() > 0 && this.getI_pla_descripcion().trim().length() > 0 && this.getI_mod_id() > 0) {
            HSPlanCurricularDAO dao = (HSPlanCurricularDAO) ServiceFinder.findBean("SpringHibernateDaoCLPlanCurricular");
            ClArbolAcademico modulo = new ClArbolAcademico();
            modulo.setArbId(this.getI_mod_id());
            if (this.getI_pla_id() > 0) { // GUARDAR CAMBIOS DE PLAN CURRICULAR
                ClPlancurricular plan = new ClPlancurricular();
                plan.setPlaId(this.getI_pla_id());
                plan.setPlaCodigo(this.getI_pla_codigo().trim().toUpperCase());
                plan.setPlaDescripcion(this.getI_pla_descripcion().trim().toUpperCase());
                plan.setClArbolAcademico(modulo);
                plan.setPlaActivo("1");
                plan.setPlaActual(this.getI_pla_actual());
                plan.setPlaVigente(this.getI_pla_vigente());

                System.out.println("entrooooooooooooooooooo");
                dao.actualizarPlanCurricular(plan);
                System.out.println("salio");
                this.setOncomplete("javascript:alert('Actualización Satisfactoria.');Richfaces.hideModalPanel('mpUpdate')");
            } else { // NUEVO PLAN CURRICULAR
                ClPlancurricular plan = new ClPlancurricular();
                plan.setPlaCodigo(this.getI_pla_codigo().trim().toUpperCase());
                plan.setPlaDescripcion(this.getI_pla_descripcion().trim().toUpperCase());
                plan.setClArbolAcademico(modulo);
                /*plan.setClModulo(mod);*/
                plan.setPlaActivo("1");
                plan.setPlaActual(this.getI_pla_actual());
                plan.setPlaVigente(this.getI_pla_vigente());
                //plan.setClTallers(new LinkedHashSet<ClTaller>());

                dao.desactualizarPlanes(this.getI_mod_id());
                dao.insertarPlanCurricular(plan);
                this.setOncomplete("javascript:alert('Registro Satisfactorio.');Richfaces.hideModalPanel('mpUpdate')");
            }
        } else {
            this.setOncomplete("javascript:alert('Todos los campos son obligatorios.')");
        }
        Seleccionar(event);
    }

    //GETTERS AND SETTERS
    public int getPla_id() {
        return pla_id;
    }

    public void setPla_id(int pla_id) {
        this.pla_id = pla_id;
    }

    public String getPla_codigo() {
        return pla_codigo;
    }

    public void setPla_codigo(String pla_codigo) {
        this.pla_codigo = pla_codigo;
    }

    public String getPla_descripcion() {
        return pla_descripcion;
    }

    public void setPla_descripcion(String pla_descripcion) {
        this.pla_descripcion = pla_descripcion;
    }

    public int getMod_id() {
        return mod_id;
    }

    public void setMod_id(int mod_id) {
        this.mod_id = mod_id;
    }

    public String getMod_descripcion() {
        return mod_descripcion;
    }

    public void setMod_descripcion(String mod_descripcion) {
        this.mod_descripcion = mod_descripcion;
    }

    public String getPla_activo() {
        return pla_activo;
    }

    public void setPla_activo(String pla_activo) {
        this.pla_activo = pla_activo;
    }

    public String getPla_actual() {
        return pla_actual;
    }

    public void setPla_actual(String pla_actual) {
        this.pla_actual = pla_actual;
    }

    public String getPla_vigente() {
        return pla_vigente;
    }

    public void setPla_vigente(String pla_vigente) {
        this.pla_vigente = pla_vigente;
    }

    public String getB_descripcion() {
        return b_descripcion;
    }

    public void setB_descripcion(String b_descripcion) {
        this.b_descripcion = b_descripcion;
    }

    public int getB_are_id() {
        return b_are_id;
    }

    public void setB_are_id(int b_are_id) {
        this.b_are_id = b_are_id;
    }

    public SelectItem[] getB_areas() {
        try {
            HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
            List lista = dao.seleccionarArea("");
            if (lista.size() > 0) {
                b_areas = new SelectItem[lista.size() + 1];
                b_areas[0] = new SelectItem(0, "[Seleccione]");
                for (int i = 0; i < lista.size(); i++) {
                    b_areas[i + 1] = new SelectItem(((ClArbolAcademico) lista.get(i)).getArbId(), ((ClArbolAcademico) lista.get(i)).getArbDescripcion());
                }
            } else {
                b_areas = new SelectItem[1];
                b_areas[0] = new SelectItem(0, "[Seleccione]");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b_areas;
    }
   /* public SelectItem[] getB_areas() {
        if (b_areas == null) {
            try {
                HSAreaDAO dao = (HSAreaDAO) ServiceFinder.findBean("SpringHibernateDaoCLArea");
                List<ClArea> areas = dao.seleccionarArea("");
                b_areas = new SelectItem[areas.size() + 1];
                b_areas[0] = new SelectItem(Integer.MAX_VALUE, "[Seleccione]");
                for (int i = 0; i < areas.size(); i++) {
                    ClArea a = areas.get(i);
                    b_areas[i + 1] = new SelectItem(a.getAreId(), a.getAreDescripcion());
                }
            } catch (Exception e) {
                System.err.println("Error al cargar areas");
            }
        }
        return b_areas;
    }*/

    public void setB_areas(SelectItem[] b_areas) {
        this.b_areas = b_areas;
    }

    public void ver_modulos() {
        try {
            HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
            List<ClArbolAcademico> modulos = dao.seleccionarModulos(this.b_are_id, "");

            i_modulos = new SelectItem[modulos.size() + 1];
            for (int i = 0; i < modulos.size(); i++) {
                i_modulos[i + 1] = new SelectItem(modulos.get(i).getArbId(), modulos.get(i).getArbDescripcion());
            }
        } catch (Exception e) {
            i_modulos = new SelectItem[1];
        } finally {
            i_modulos[0] = new SelectItem(0, "[Seleccione]");
        }
    }

    public int getB_mod_id() {
        return b_mod_id;
    }

    public void setB_mod_id(int b_mod_id) {
        this.b_mod_id = b_mod_id;
    }

    public SelectItem[] getB_modulos() {
        ver_modulos();
        return i_modulos;
    }

    public void setB_modulos(SelectItem[] b_modulos) {
        this.b_modulos = b_modulos;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public String getImagen_pla_actual() {
        return imagen_pla_actual;
    }

    public void setImagen_pla_actual(String imagen_pla_actual) {
        this.imagen_pla_actual = imagen_pla_actual;
    }

    public String getImagen_pla_vigente() {
        return imagen_pla_vigente;
    }

    public void setImagen_pla_vigente(String imagen_pla_vigente) {
        this.imagen_pla_vigente = imagen_pla_vigente;
    }

    public List<bPlanCurricular> getPlanes() {
        return planes;
    }

    public void setPlanes(List<bPlanCurricular> planes) {
        this.planes = planes;
    }

    public int getI_pla_id() {
        return i_pla_id;
    }

    public void setI_pla_id(int i_pla_id) {
        this.i_pla_id = i_pla_id;
    }

    public String getI_pla_codigo() {
        return i_pla_codigo;
    }

    public void setI_pla_codigo(String i_pla_codigo) {
        this.i_pla_codigo = i_pla_codigo;
    }

    public String getI_pla_descripcion() {
        return i_pla_descripcion;
    }

    public void setI_pla_descripcion(String i_pla_descripcion) {
        this.i_pla_descripcion = i_pla_descripcion;
    }

    public String getEdit_disable() {
        return edit_disable;
    }

    public void setEdit_disable(String edit_disable) {
        this.edit_disable = edit_disable;
    }

    public int getI_are_id() {
        return i_are_id;
    }

    public void setI_are_id(int i_are_id) {
        this.i_are_id = i_are_id;
    }

    
     public SelectItem[] getI_areas() {
        try {
            HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
            List lista = dao.seleccionarArea("");
            if (lista.size() > 0) {
                i_areas = new SelectItem[lista.size() + 1];
                i_areas[0] = new SelectItem(0, "[Seleccione]");
                for (int i = 0; i < lista.size(); i++) {
                    i_areas[i + 1] = new SelectItem(((ClArbolAcademico) lista.get(i)).getArbId(), ((ClArbolAcademico) lista.get(i)).getArbDescripcion());
                }
            } else {
                i_areas = new SelectItem[1];
                i_areas[0] = new SelectItem(0, "[Seleccione]");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i_areas;
     }
    /*public SelectItem[] getI_areas() throws Exception {
        if (i_areas == null) {
            HSAreaDAO dao = (HSAreaDAO) ServiceFinder.findBean("SpringHibernateDaoCLArea");
            List<ClArea> areas = dao.seleccionarArea("");
            i_areas = new SelectItem[areas.size() + 1];
            i_areas[0] = new SelectItem(0, "[Seleccione]");
            for (int i = 0; i < areas.size(); i++) {
                ClArea a = areas.get(i);
                i_areas[i + 1] = new SelectItem(a.getAreId(), a.getAreDescripcion());
            }
        }
        return i_areas;
    }*/

    public void setI_areas(SelectItem[] i_areas) {
        this.i_areas = i_areas;
    }

    public int getI_mod_id() {
        return i_mod_id;
    }

    public void setI_mod_id(int i_mod_id) {
        this.i_mod_id = i_mod_id;
    }

    public SelectItem[] getI_modulos() throws Exception {
        if (this.i_are_id == 0) {
            System.out.println("ENTRO CON AREA VACIA");
            i_modulos = new SelectItem[1];
        } else {
            System.out.println("ENTRO CON ID_AREA");
            System.out.println("area: i "+this.i_are_id);
            HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
            List lista = dao.seleccionarModulos(this.i_are_id, "");
            System.out.println("Tamaño de lista: "+lista.size());
            i_modulos = new SelectItem[lista.size() + 1];

            for (int i = 0; i < lista.size(); i++) {
                System.out.println("MODULOS: "+((ClArbolAcademico) lista.get(i)).getArbDescripcion());
                i_modulos[i + 1] = new SelectItem(((ClArbolAcademico) lista.get(i)).getArbId(), ((ClArbolAcademico) lista.get(i)).getArbDescripcion());
            }
        }
        i_modulos[0] = new SelectItem(0, "[Seleccione]");
        return i_modulos;
        /*if (this.i_are_id == 0) {
            i_modulos = new SelectItem[1];
        } else {
            HSModuloDAO dao = (HSModuloDAO) ServiceFinder.findBean("SpringHibernateDaoCLModulo");
            List lista = dao.seleccionarModulos(this.i_are_id, "");
            i_modulos = new SelectItem[lista.size() + 1];

            for (int i = 0; i < lista.size(); i++) {
                i_modulos[i + 1] = new SelectItem(((ClModulo) lista.get(i)).getModId(), ((ClModulo) lista.get(i)).getModDescripcion());
            }
        }
        i_modulos[0] = new SelectItem(0, "[Seleccione]");
        return i_modulos;*/
    }

    public void setI_modulos(SelectItem[] i_modulos) {
        this.i_modulos = i_modulos;
    }

    public String getI_pla_vigente() {
        return i_pla_vigente;
    }

    public void setI_pla_vigente(String i_pla_vigente) {
        this.i_pla_vigente = i_pla_vigente;
    }

    public String getOncomplete2() {
        return oncomplete2;
    }

    public void setOncomplete2(String oncomplete2) {
        this.oncomplete2 = oncomplete2;
    }
    

    public SelectItem[] getI_vigentes() {
        i_vigentes = new SelectItem[2];
        i_vigentes[0] = new SelectItem("1", "VIGENTE");
        i_vigentes[1] = new SelectItem("0", "NO VIGENTE");

        return i_vigentes;
    }

    public void setI_vigentes(SelectItem[] i_vigentes) {
        this.i_vigentes = i_vigentes;
    }

    public String getI_pla_actual() {
        return i_pla_actual;
    }

    public void setI_pla_actual(String i_pla_actual) {
        this.i_pla_actual = i_pla_actual;
    }
}
