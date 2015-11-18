package net.uch.academica.managedBeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIParameter;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.uch.academica.hibernateSpringDao.HSCursoDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcCurso;
import net.uch.mapping.AcEspecialidad;
import net.uch.mapping.AcFacultad;
import net.uch.tablaSistema.hibernateSpringDao.HSEspecialidadDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSFacultadDAO;
import net.uch.tablaSistema.managedBeans.bUsuario;
import org.richfaces.event.DataScrollerEvent;

public class bCurso {

    public int b_id;
    public int b_id_u;
    public String b_codigo;
    public String b_codigo_u;
    public String b_nombre;
    public String b_nombre_u;
    public String b_activo;
    public List listaCursos;
    private SelectItem[] comboFacultades;
    private SelectItem[] comboEspecialidades;
    private int b_facultad;
    private int b_especialidad;
    private String b_especialidad_descripcion;
    private String b_facultad_descripcion;
    private int b_facultad_u;
    private int b_especialidad_u;
    private SelectItem[] comboEspecialidades_s;
    private SelectItem[] comboFacultades_s;
    private String oncomplete;
    
    private int b_curhorlab;
    private int b_curhorteo;

    public bCurso() throws Exception {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession(false);
        bUsuario usu = (bUsuario) session1.getAttribute("usuario");
        if (usu != null) {
            listaCursos = new ArrayList();
            Seleccionar();
        } else {
            throw new Exception();
        }
    }

    public int getB_especialidad_u() {
        return b_especialidad_u;
    }

    public void setB_especialidad_u(int b_especialidad_u) {
        this.b_especialidad_u = b_especialidad_u;
    }

    public int getB_facultad_u() {
        return b_facultad_u;
    }

    public void setB_facultad_u(int b_facultad_u) {
        this.b_facultad_u = b_facultad_u;
    }

    public int getB_curhorlab() {
        return b_curhorlab;
    }

    public void setB_curhorlab( int b_curhorlab ) {
        this.b_curhorlab = b_curhorlab;
    }

    public int getB_curhorteo() {
        return b_curhorteo;
    }

    public void setB_curhorteo( int b_curhorteo ) {
        this.b_curhorteo = b_curhorteo;
    }
    
    
    public SelectItem[] getComboFacultades() throws Exception {
        HSFacultadDAO dao = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
        List lista = dao.seleccionarFacultad("", "");
        comboFacultades = new SelectItem[1];
        comboFacultades[0] = new SelectItem(0, "[Seleccione]");
        if (lista.size() != 0) {
            comboFacultades = new SelectItem[lista.size() + 1];
            comboFacultades[0] = new SelectItem(0, "[Seleccione]");
            for (int i = 0; i < comboFacultades.length - 1; i++) {
                comboFacultades[i + 1] = new SelectItem(((AcFacultad) lista.get(i)).getId(), ((AcFacultad) lista.get(i)).getFacNombre());
            }
        }
        return comboFacultades;
    }

    public void setComboFacultades(SelectItem[] comboFacultades) {
        this.comboFacultades = comboFacultades;
    }

    public SelectItem[] getComboFacultades_s() throws Exception {
        HSFacultadDAO dao = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
        List lista = dao.seleccionarFacultad("", "");
        comboFacultades_s = new SelectItem[lista.size() + 1];
        comboFacultades_s[0] = new SelectItem(0, "TODOS");
        for (int i = 0; i < comboFacultades_s.length - 1; i++) {
            comboFacultades_s[i + 1] = new SelectItem(((AcFacultad) lista.get(i)).getId(), ((AcFacultad) lista.get(i)).getFacNombre());
        }
        return comboFacultades_s;
    }

    public void setComboFacultades_s(SelectItem[] comboFacultades_s) {
        this.comboFacultades_s = comboFacultades_s;
    }

    public SelectItem[] getComboEspecialidades() throws Exception {
        HSEspecialidadDAO dao = (HSEspecialidadDAO) ServiceFinder.findBean("SpringHibernateDaoEspecialidad");
        System.out.println("facu::::" + this.getB_facultad_u());
        List lista = dao.seleccionarEspecialidad(this.getB_facultad_u());
        if (lista.size() != 0) {
            comboEspecialidades = new SelectItem[lista.size()];
            for (int i = 0; i < comboEspecialidades.length; i++) {
                comboEspecialidades[i] = new SelectItem(((AcEspecialidad) lista.get(i)).getId(), ((AcEspecialidad) lista.get(i)).getEspNombre());
            }
        } else {
            comboEspecialidades = new SelectItem[1];
            comboEspecialidades[0] = new SelectItem(0, "[Seleccione]");
        }
        return comboEspecialidades;
    }

    public void setComboEspecialidades(SelectItem[] comboEspecialidades) {
        this.comboEspecialidades = comboEspecialidades;
    }

    public SelectItem[] getComboEspecialidades_s() throws Exception {
        HSEspecialidadDAO dao = (HSEspecialidadDAO) ServiceFinder.findBean("SpringHibernateDaoEspecialidad");
        System.out.println("facu::::" + this.getB_facultad());
        List lista = dao.seleccionarEspecialidad(this.getB_facultad());
        comboEspecialidades_s = new SelectItem[lista.size() + 1];
        comboEspecialidades_s[0] = new SelectItem(0, "TODOS");
        for (int i = 0; i < comboEspecialidades_s.length - 1; i++) {
            comboEspecialidades_s[i + 1] = new SelectItem(((AcEspecialidad) lista.get(i)).getId(), ((AcEspecialidad) lista.get(i)).getEspNombre());
        }
        return comboEspecialidades_s;
    }

    public void setComboEspecialidades_s(SelectItem[] comboEspecialidades_s) {
        this.comboEspecialidades_s = comboEspecialidades_s;
    }

    public List getListaCursos() {
        return listaCursos;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public void Nuevo() {
        this.setB_id_u(0);
        this.setB_codigo_u("");
        this.setB_nombre_u("");
        this.setB_especialidad_u(0);
        this.setB_facultad_u(0);
        this.setB_curhorlab(0);
        this.setB_curhorteo(0);
        comboEspecialidades = new SelectItem[1];
        comboEspecialidades[0] = new SelectItem(0, "[Seleccione]");
    }

    public String getB_codigo_u() {
        return b_codigo_u;
    }

    public void setB_codigo_u(String b_codigo_u) {
        this.b_codigo_u = b_codigo_u;
    }

    public String getB_nombre_u() {
        return b_nombre_u;
    }

    public void setB_nombre_u(String b_nombre_u) {
        this.b_nombre_u = b_nombre_u;
    }

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

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

    public String getB_activo() {
        return b_activo;
    }

    public void setB_activo(String b_activo) {
        this.b_activo = b_activo;
    }

    public void Enviar(ActionEvent event) {
        /*	if(this.getB_id()==0)
        Editar();
        else
        Grabar();
        }
        public String Grabar(){*/
        if (this.getB_codigo_u().length() == 0 || this.getB_nombre_u().length() == 0 || this.getB_facultad_u() == 0 || this.getB_especialidad_u() == 0  || this.getB_curhorteo()==0) {
            this.setOncomplete("javascript:alert('Ingrese los datos necesarios.')");
        } else {
            if(this.getB_curhorlab()<19 && this.getB_curhorteo()<19){
            HSCursoDAO dao = (HSCursoDAO) ServiceFinder.findBean("SpringHibernateDaoCurso");
            AcCurso curso = new AcCurso();
            System.out.println("Insertara" + this.getB_id_u());
            if (this.getB_id_u() != 0) {
                curso.setId(this.getB_id_u());
            }
            curso.setCurCodigo(this.getB_codigo_u());
            curso.setCurNombre(this.getB_nombre_u());
            AcEspecialidad esp = new AcEspecialidad();
            esp.setId(this.getB_especialidad_u());
            curso.setEsp(esp);
            curso.setCurActivo("1");
            
            curso.setCurHorLab(this.getB_curhorlab());
            curso.setCurHorTeo(this.getB_curhorteo());
            dao.insertarCurso(curso);
            this.setOncomplete("javascript:alert('Se creo un Curso correctamente.');Richfaces.hideModalPanel('mp')");
            }else{
                this.setOncomplete("javascript:alert('las horas de laboratorio y teoria deben ser menores que 18 horas')");
            }
                
            }
    }

    public void Seleccionar() throws Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        listaCursos = new ArrayList();
        HSCursoDAO dao = (HSCursoDAO) ServiceFinder.findBean("SpringHibernateDaoCurso");
        List lista = dao.seleccionarCurso(this.getB_codigo(), this.getB_nombre(), this.getB_facultad(), this.getB_especialidad());
        bCurso cursos;
        for (int i = 0; i < lista.size(); i++) {
            Object bC[] = (Object[]) lista.get(i);
            cursos = new bCurso();
            cursos.setB_id(Integer.parseInt("" + bC[0]));
            cursos.setB_nombre(bC[1].toString());
            cursos.setB_codigo(bC[2].toString());
            cursos.setB_especialidad(Integer.parseInt(bC[3].toString()));
            cursos.setB_especialidad_descripcion(bC[4].toString());
            cursos.setB_facultad(Integer.parseInt(bC[5].toString()));
            cursos.setB_facultad_descripcion(bC[6].toString());
            cursos.setB_curhorlab( Integer.parseInt(bC[8].toString()) );
            cursos.setB_curhorteo( Integer.parseInt(bC[9].toString()) );
            listaCursos.add(cursos);
        }
        this.setListaCursos(listaCursos);
    }

    public void EliminarFila(ActionEvent event) throws Exception {
        UIParameter delete = (UIParameter) event.getComponent().findComponent("delete");
        Eliminar(delete.getValue().toString());
    }

    public void Eliminar(String id) throws Exception {
        HSCursoDAO dao = (HSCursoDAO) ServiceFinder.findBean("SpringHibernateDaoCurso");
        dao.eliminarCurso(id);
    }

    public void setListaCursos(List listaCursos) {
        this.listaCursos = listaCursos;
    }

    public void EditarFila(ActionEvent event) {
        System.out.println("editando");
        b_id_u = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_id")).getValue().toString());
        b_codigo_u = ((UIParameter) event.getComponent().findComponent("p_codigo")).getValue().toString();
        b_nombre_u = ((UIParameter) event.getComponent().findComponent("p_nombre")).getValue().toString();
        b_especialidad_u = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_facultad")).getValue().toString());
        b_facultad_u = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_especialidad")).getValue().toString());
        b_curhorlab = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_horlab")).getValue().toString());
        b_curhorteo = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_horteo")).getValue().toString());
        System.out.println("/" + b_id_u + "/" + b_codigo_u + "/" + b_nombre_u + "/" + b_especialidad_u + "/" + b_facultad_u+"/"+b_curhorlab+"/"+b_curhorteo);
    }

    public int getB_id_u() {
        return b_id_u;
    }

    public void setB_id_u(int b_id_u) {
        this.b_id_u = b_id_u;
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

    public String getB_especialidad_descripcion() {
        return b_especialidad_descripcion;
    }

    public void setB_especialidad_descripcion(String b_especialidad_descripcion) {
        this.b_especialidad_descripcion = b_especialidad_descripcion;
    }

    public String getB_facultad_descripcion() {
        return b_facultad_descripcion;
    }

    public void setB_facultad_descripcion(String b_facultad_descripcion) {
        this.b_facultad_descripcion = b_facultad_descripcion;
    }

    public void onAction(ActionEvent actionEvent) {
        System.out.println((new StringBuilder()).append("TestBean.onAction() ").append(actionEvent).toString());
    }

    public void doScroll(DataScrollerEvent event) {
        String oldScrolVal = event.getOldScrolVal();
        String newScrolVal = event.getNewScrolVal();
    }
}