package net.uch.academica.managedBeans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.mapping.AcPlanCurso;
import net.uch.mapping.AcPreRequisitos;
import net.uch.mapping.AcEspecialidad;
import net.uch.mapping.AcFacultad;
import net.uch.mapping.AcPlancurricular;
import net.uch.mapping.TbCatalogo;
import net.uch.academica.hibernateSpringDao.HSPreRequisitosDAO;
import net.uch.academica.hibernateSpringDao.HSPlanCurricularDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSEspecialidadDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSFacultadDAO;

public class bPreRequisito {

    int c_id;
    String c_codigo;
    String c_nombre;
    String c_ciclo;
    String c_id_ciclo;
    String c_creditos;
    String c_tipo;
    String c_id_tipo;
    String c_facu;
    int c_id_facu;
    String c_espe;
    int c_id_espe;
    String c_plan;
    int c_id_plan;
    int c_valor = 1;
    int x_id;
    int x_id_curso;
    String x_curso;
    int x_id_req;
    String x_req;
    String x_ciclo;
    private boolean ver = false;
    public String flag_ver = "0";
    public String imagen = "/Imagenes/actions/down.png";
    private List detalle_s;
    int det_id;
    int det_id_curso;
    String det_curso;
    int det_id_req;
    String det_req;
    String det_ciclo;
    String det_detalle;
    String b_id_ciclo;
    int b_id_curso;
    private String b_idciclo; //static
    private int b_idplan; //static
    private String oncomplete;
    public int v_estado = 0; //static
    private int b_idf; //static
    private int b_ide; //static
    private int b_idp; //static
    public SelectItem[] comboEspecialidad_buscar;
    public SelectItem[] comboFacultad_buscar;
    public SelectItem[] comboPlan_buscar;
    public SelectItem[] comboCiclo;
    public SelectItem[] comboCurso;
    List listaPreRequisitos;
    public List listaCursos;
    String view = "true";
    String editable = "false";
    boolean visible = false;

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getC_codigo() {
        return c_codigo;
    }

    public void setC_codigo(String c_codigo) {
        this.c_codigo = c_codigo;
    }

    public String getC_nombre() {
        return c_nombre;
    }

    public void setC_nombre(String c_nombre) {
        this.c_nombre = c_nombre;
    }

    public String getC_ciclo() {
        return c_ciclo;
    }

    public void setC_ciclo(String c_ciclo) {
        this.c_ciclo = c_ciclo;
    }

    public String getC_id_ciclo() {
        return c_id_ciclo;
    }

    public void setC_id_ciclo(String c_id_ciclo) {
        this.c_id_ciclo = c_id_ciclo;
    }

    public String getC_creditos() {
        return c_creditos;
    }

    public void setC_creditos(String c_creditos) {
        this.c_creditos = c_creditos;
    }

    public String getC_tipo() {
        return c_tipo;
    }

    public void setC_tipo(String c_tipo) {
        this.c_tipo = c_tipo;
    }

    public String getC_id_tipo() {
        return c_id_tipo;
    }

    public void setC_id_tipo(String c_id_tipo) {
        this.c_id_tipo = c_id_tipo;
    }

    public String getC_facu() {
        return c_facu;
    }

    public void setC_facu(String c_facu) {
        this.c_facu = c_facu;
    }

    public int getC_id_facu() {
        return c_id_facu;
    }

    public void setC_id_facu(int c_id_facu) {
        this.c_id_facu = c_id_facu;
    }

    public String getC_espe() {
        return c_espe;
    }

    public void setC_espe(String c_espe) {
        this.c_espe = c_espe;
    }

    public int getC_id_espe() {
        return c_id_espe;
    }

    public void setC_id_espe(int c_id_espe) {
        this.c_id_espe = c_id_espe;
    }

    public String getC_plan() {
        return c_plan;
    }

    public void setC_plan(String c_plan) {
        this.c_plan = c_plan;
    }

    public int getC_id_plan() {
        return c_id_plan;
    }

    public void setC_id_plan(int c_id_plan) {
        this.c_id_plan = c_id_plan;
    }

    public int getC_valor() {
        return c_valor;
    }

    public void setC_valor(int c_valor) {
        this.c_valor = c_valor;
    }

    public String getB_id_ciclo() {
        return b_id_ciclo;
    }

    public void setB_id_ciclo(String b_id_ciclo) {
        this.b_id_ciclo = b_id_ciclo;
    }

    public int getB_id_curso() {
        return b_id_curso;
    }

    public void setB_id_curso(int b_id_curso) {
        this.b_id_curso = b_id_curso;
    }

    public int getX_id() {
        return x_id;
    }

    public void setX_id(int x_id) {
        this.x_id = x_id;
    }

    public int getX_id_curso() {
        return x_id_curso;
    }

    public void setX_id_curso(int x_id_curso) {
        this.x_id_curso = x_id_curso;
    }

    public String getX_curso() {
        return x_curso;
    }

    public void setX_curso(String x_curso) {
        this.x_curso = x_curso;
    }

    public int getX_id_req() {
        return x_id_req;
    }

    public void setX_id_req(int x_id_req) {
        this.x_id_req = x_id_req;
    }

    public String getX_req() {
        return x_req;
    }

    public void setX_req(String x_req) {
        this.x_req = x_req;
    }

    public String getX_ciclo() {
        return x_ciclo;
    }

    public void setX_ciclo(String x_ciclo) {
        this.x_ciclo = x_ciclo;
    }

    public List getListaPreRequisitos() {
        return listaPreRequisitos;
    }

    public void setListaPreRequisitos(List listaPreRequisitos) {
        this.listaPreRequisitos = listaPreRequisitos;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public int getV_estado() {
        return v_estado;
    }

    public void setV_estado(int v_estado) {
        this.v_estado = v_estado;
    }

    public boolean isVer() {
        return ver;
    }

    public void setVer(boolean ver) {
        this.ver = ver;
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

    public List getDetalle_s() {
        return detalle_s;
    }

    public void setDetalle_s(List detalle_s) {
        this.detalle_s = detalle_s;
    }

    public int getDet_id() {
        return det_id;
    }

    public void setDet_id(int det_id) {
        this.det_id = det_id;
    }

    public int getDet_id_curso() {
        return det_id_curso;
    }

    public void setDet_id_curso(int det_id_curso) {
        this.det_id_curso = det_id_curso;
    }

    public String getDet_curso() {
        return det_curso;
    }

    public void setDet_curso(String det_curso) {
        this.det_curso = det_curso;
    }

    public int getDet_id_req() {
        return det_id_req;
    }

    public void setDet_id_req(int det_id_req) {
        this.det_id_req = det_id_req;
    }

    public String getDet_req() {
        return det_req;
    }

    public void setDet_req(String det_req) {
        this.det_req = det_req;
    }

    public String getDet_ciclo() {
        return det_ciclo;
    }

    public void setDet_ciclo(String det_ciclo) {
        this.det_ciclo = det_ciclo;
    }

    public String getDet_detalle() {
        return det_detalle;
    }

    public void setDet_detalle(String det_detalle) {
        this.det_detalle = det_detalle;
    }

    public SelectItem[] getComboFacultad_buscar() throws Exception {
        HSFacultadDAO dao = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
        List lista = dao.seleccionarFacultad("", "");
        SelectItem[] comboFacultades = new SelectItem[lista.size() + 1];
        comboFacultades[0] = new SelectItem(0, "[Seleccione]");
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
        this.setC_id_plan(0);
        b_ide = 0;
        b_idp = 0;
        setearEspecialidad_actY();
        setearPlan_actY();
    }

    public void setearEspecialidad_actY() {
        this.setC_id_plan(0);
        b_idp = 0;
        b_ide = this.getC_id_espe();
    }

    public void setearPlan_actY() {
        b_idp = this.getC_id_plan();
    }

    public void setearEspecialidad_buscar() {
        b_ide = this.getC_id_espe();
        this.setC_id_plan(0);
        b_idp = 0;
        setearPlan_actY();
    }

    public SelectItem[] getComboEspecialidad_buscar() throws Exception {
        cambiarEspecialidad_buscar(b_idf);
        cambiarPlan_buscar(b_ide);
        return comboEspecialidad_buscar;
    }

    public void setComboEspecialidad_buscar(SelectItem[] comboEspecialidad_buscar) {
        this.comboEspecialidad_buscar = comboEspecialidad_buscar;
    }

    public void cambiarEspecialidad_buscar(int id) throws Exception {
        HSEspecialidadDAO dao = (HSEspecialidadDAO) ServiceFinder.findBean("SpringHibernateDaoEspecialidad");
        List lista = dao.seleccionarEspecialidad(id);
        SelectItem[] cmb_especialidad_buscar = new SelectItem[lista.size() + 1];
        cmb_especialidad_buscar[0] = new SelectItem(0, "[Seleccione]");
        for (int i = 0; i < cmb_especialidad_buscar.length - 1; i++) {
            cmb_especialidad_buscar[i + 1] = new SelectItem(((AcEspecialidad) lista.get(i)).getId(), ((AcEspecialidad) lista.get(i)).getEspNombre());
        }
        this.setComboEspecialidad_buscar(cmb_especialidad_buscar);
        cambiarPlan_buscar(b_ide);
    }

    public SelectItem[] getComboPlan_buscar() throws Exception {
        cambiarPlan_buscar(b_ide);
        return comboPlan_buscar;
    }

    public void setComboPlan_buscar(SelectItem[] comboPlan_buscar) {
        this.comboPlan_buscar = comboPlan_buscar;
    }

    public void cambiarPlan_buscar(int id) throws Exception {
        HSPlanCurricularDAO dao = (HSPlanCurricularDAO) ServiceFinder.findBean("SpringHibernateDaoPlanCurricular");
        List lista = dao.seleccionarPlanActivo(id);
        SelectItem[] cmb_plan_buscar = new SelectItem[lista.size() + 1];
        cmb_plan_buscar[0] = new SelectItem(0, "[Seleccione]");
        for (int i = 0; i < cmb_plan_buscar.length - 1; i++) {
            cmb_plan_buscar[i + 1] = new SelectItem(((AcPlancurricular) lista.get(i)).getId(), ((AcPlancurricular) lista.get(i)).getPlanDescripcion());
        }
        this.setComboPlan_buscar(cmb_plan_buscar);
    }
    //-----------

    //inicio para la seccion de listado, bajo los criterios de busqueda
    public void Seleccionar() throws Exception {
        listaPreRequisitos = new ArrayList();
        HSPreRequisitosDAO dao = (HSPreRequisitosDAO) ServiceFinder.findBean("SpringHibernateDaoPreRequisitos");
        List listax = dao.cursoDet(this.getC_id_plan());
        HSCatalogoDAO daoz = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        bPreRequisito bA;
        for (int i = 0; i < listax.size(); i++) {
            bA = new bPreRequisito();
            bA.setC_id(((AcPlanCurso) listax.get(i)).getId());
            bA.setC_codigo(((AcPlanCurso) listax.get(i)).getCur().getCurCodigo());
            bA.setC_nombre(((AcPlanCurso) listax.get(i)).getCur().getCurNombre());
            bA.setC_creditos(((AcPlanCurso) listax.get(i)).getPlancurCredito());
            bA.setC_id_ciclo(((AcPlanCurso) listax.get(i)).getPlancurCiclo());
            bA.setC_ciclo(daoz.seleccionarDescripcion(((AcPlanCurso) listax.get(i)).getPlancurCiclo()));
            bA.setC_id_plan(((AcPlanCurso) listax.get(i)).getPlan().getId());
            bA.setC_plan(((AcPlanCurso) listax.get(i)).getPlan().getPlanDescripcion());
            bA.setC_id_espe(((AcPlanCurso) listax.get(i)).getPlan().getEsp().getId());
            bA.setC_espe(((AcPlanCurso) listax.get(i)).getPlan().getEsp().getEspNombre());
            bA.setC_id_facu(((AcPlanCurso) listax.get(i)).getPlan().getEsp().getFac().getId());
            //bA.setC_facu(((AcPlanCurso)listax.get(i)).getPlan().getEsp().getFac().getFacNombre());
            bA.setC_id_tipo(((AcPlanCurso) listax.get(i)).getPlancurTipo());
            //bA.setC_tipo(((AcPlanCurso)listax.get(i)).getPlancurTipo());
            bA.setC_tipo(daoz.seleccionarDescripcion(((AcPlanCurso) listax.get(i)).getPlancurTipo()));
            bA.setC_valor(Integer.parseInt(daoz.seleccionarValor(((AcPlanCurso) listax.get(i)).getPlancurCiclo())));
            Set<AcPreRequisitos> sisevadet = new LinkedHashSet<AcPreRequisitos>();
            sisevadet = ((AcPlanCurso) listax.get(i)).getAcPreRequisitosByPlancur();
            List list = Collections.synchronizedList(new LinkedList(sisevadet));
            List a = new ArrayList();
            if (list.size() != 0) {
                bPreRequisito siseva;
                for (int j = 0; j < list.size(); j++) {
                    siseva = new bPreRequisito();
                    siseva.setDet_id(((AcPreRequisitos) list.get(j)).getId());
                    siseva.setDet_id_curso(((AcPreRequisitos) list.get(j)).getPlancur().getId());
                    siseva.setDet_curso(((AcPreRequisitos) list.get(j)).getPlancur().getCur().getCurNombre());
                    siseva.setDet_id_req(((AcPreRequisitos) list.get(j)).getPlancurIdRequisito().getId());
                    siseva.setDet_req(((AcPreRequisitos) list.get(j)).getPlancurIdRequisito().getCur().getCurNombre());
                    siseva.setDet_detalle(((AcPreRequisitos) list.get(j)).getPlancurIdRequisito().getPlancurCiclo());
                    siseva.setDet_ciclo(daoz.seleccionarDescripcion(((AcPreRequisitos) list.get(j)).getPlancurIdRequisito().getPlancurCiclo()));
                    if (((AcPreRequisitos) list.get(j)).getPreReqActivo().equals("1")) {
                        a.add(siseva);
                    }
                }
            }
            bA.setDetalle_s(a);
            listaPreRequisitos.add(bA);
        }
        this.setListaPreRequisitos(listaPreRequisitos);
    }

    public void EditarFila(ActionEvent event) {
        b_idciclo = "0";
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        UIParameter valor = (UIParameter) event.getComponent().findComponent("p_valor");
        UIParameter id = (UIParameter) event.getComponent().findComponent("p_id");
        UIParameter codigo = (UIParameter) event.getComponent().findComponent("p_codigo");
        UIParameter nombre = (UIParameter) event.getComponent().findComponent("p_nombre");
        UIParameter ciclo = (UIParameter) event.getComponent().findComponent("p_ciclo");
        UIParameter especialidad = (UIParameter) event.getComponent().findComponent("p_especialidad");
        UIParameter id_especialidad = (UIParameter) event.getComponent().findComponent("p_id_especialidad");
        //UIParameter facultad = (UIParameter) event.getComponent().findComponent("p_facultad");
        UIParameter plan = (UIParameter) event.getComponent().findComponent("p_plan");
        UIParameter id_plan = (UIParameter) event.getComponent().findComponent("p_id_plan");
        UIParameter id_ciclo = (UIParameter) event.getComponent().findComponent("p_id_ciclo");
        c_valor = Integer.parseInt(valor.getValue().toString());
        c_id = Integer.parseInt(id.getValue().toString());
        c_codigo = codigo.getValue().toString();
        c_nombre = nombre.getValue().toString();
        c_ciclo = ciclo.getValue().toString();
        c_espe = especialidad.getValue().toString();
        c_id_espe = Integer.parseInt(id_especialidad.getValue().toString());
        //c_facu=facultad.getValue().toString();
        c_plan = plan.getValue().toString();
        c_id_plan = Integer.parseInt(id_plan.getValue().toString());
        c_id_ciclo = id_ciclo.getValue().toString();
    }

    public SelectItem[] getComboCiclo() throws Exception {
        HSPreRequisitosDAO dao = (HSPreRequisitosDAO) ServiceFinder.findBean("SpringHibernateDaoPreRequisitos");
        List lista = dao.seleccionarCiclo("006", c_valor);

        HSEspecialidadDAO dao2 = (HSEspecialidadDAO) ServiceFinder.findBean("SpringHibernateDaoEspecialidad");
        List lista2 = dao2.seleccionarUnaEspecialidad(this.getC_id_espe());
        SelectItem[] cmb_ciclo = new SelectItem[lista.size()];
        int tamano = lista.size();
        if (lista2.size() == 0) {
            cmb_ciclo = new SelectItem[tamano + 1];
            cmb_ciclo[0] = new SelectItem("0", "[  ]");
        } else {
            int n = c_valor - 1;
            tamano = n;
            cmb_ciclo = new SelectItem[tamano + 1];
            cmb_ciclo[0] = new SelectItem("0", "[  ]");
        }
        for (int i = 0; i < cmb_ciclo.length - 1; i++) {
            cmb_ciclo[i + 1] = new SelectItem(((TbCatalogo) lista.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista.get(i)).getCatDescripcionElemento());
        }

        this.setComboCiclo(cmb_ciclo);
        return cmb_ciclo;
    }

    public void setComboCiclo(SelectItem[] comboCiclo) {
        this.comboCiclo = comboCiclo;
    }

    public void setearCiclo() {
        b_idciclo = this.getB_id_ciclo();
    }

    public SelectItem[] getComboCurso() throws Exception {
        cambiarCurso(this.getC_id_plan(), b_idciclo);
        return comboCurso;
    }

    public void setComboCurso(SelectItem[] comboCurso) {
        this.comboCurso = comboCurso;
    }

    public void cambiarCurso(int pId, String cId) throws Exception {
        HSPreRequisitosDAO dao = (HSPreRequisitosDAO) ServiceFinder.findBean("SpringHibernateDaoPreRequisitos");
        List lista = dao.seleccionarCurso(pId, cId);
        comboCurso = new SelectItem[lista.size() + 1];
        comboCurso[0] = new SelectItem(0, "[Seleccione]");
        for (int i = 0; i < comboCurso.length - 1; i++) {
            comboCurso[i + 1] = new SelectItem(((AcPlanCurso) lista.get(i)).getId(), ((AcPlanCurso) lista.get(i)).getCur().getCurNombre());
        }
        this.setComboCurso(comboCurso);
    }

    public void AgregarFila() throws Exception {
        this.setV_estado(1);
        List Grupos = new ArrayList();
        HSPreRequisitosDAO dao = (HSPreRequisitosDAO) ServiceFinder.findBean("SpringHibernateDaoPreRequisitos");
        AcPreRequisitos plancurso = new AcPreRequisitos();
        List lista = dao.verificarRepitencia(this.getC_id(), this.getB_id_curso());
        if (lista.size() == 0 && this.getB_id_curso() != 0) {
            plancurso.setId(this.getX_id());
            AcPlanCurso cur = new AcPlanCurso();
            cur.setId(this.getC_id());
            plancurso.setPlancur(cur);
            AcPlanCurso cur2 = new AcPlanCurso();
            cur2.setId(this.getB_id_curso());
            plancurso.setPlancurIdRequisito(cur2);
            plancurso.setPreReqActivo("1");
            dao.insertarPreRequisitos(plancurso);
            this.setListaCursos(Grupos);
            this.setOncomplete("javascript:alert('Se creo una prerequisito correctamente')");
        } else {
            this.setOncomplete("javascript:alert('El curso ya fue asignado como Pre Requisito')");
        }
    }

    public List getListaCursos() throws Exception {
        List Grupos = new ArrayList();
        bPreRequisito cursos;
        HSPreRequisitosDAO dao = (HSPreRequisitosDAO) ServiceFinder.findBean("SpringHibernateDaoPreRequisitos");
        List lista = dao.seleccionarPreReq(this.getC_id());
        this.setListaCursos(Grupos);
        for (int i = 0; i < lista.size(); i++) {
            Object bC[] = (Object[]) lista.get(i);
            cursos = new bPreRequisito();
            cursos.setX_id(Integer.parseInt(bC[0].toString()));
            cursos.setX_id_curso(Integer.parseInt(bC[1].toString()));
            cursos.setX_curso(bC[2].toString());
            cursos.setX_id_req(Integer.parseInt(bC[3].toString()));
            cursos.setX_req(bC[4].toString());
            cursos.setX_ciclo(bC[5].toString());
            Grupos.add(cursos);
        }
        this.setListaCursos(Grupos);
        return listaCursos;
    }

    public void setListaCursos(List listaCursos) {
        this.listaCursos = listaCursos;
    }

    public void QuitarFila(ActionEvent event) throws Exception {
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        UIParameter delete = (UIParameter) event.getComponent().findComponent("p_id1");
        Eliminar1(delete.getValue().toString());
    }

    public void Eliminar1(String id) throws Exception {
        HSPreRequisitosDAO dao = (HSPreRequisitosDAO) ServiceFinder.findBean("SpringHibernateDaoPreRequisitos");
        dao.eliminarPreRequisitos(id);
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
}