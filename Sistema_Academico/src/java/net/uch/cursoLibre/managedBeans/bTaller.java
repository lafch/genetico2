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

public class bTaller {

    //CAMPOS
    private int tal_id;
    private String tal_codigo;
    private String tal_descripcion;
    private String tal_numero;
    private String tal_activo;
    private int cur_id;
    private String cur_descripcion;
    private int pla_id;
    private String pla_descripcion;
    private int e_tal_id;
    private String e_tal_codigo;
    private String e_tal_descripcion;
    private String e_tal_numero;
    private boolean verDetalle;
    private String imagenDetalle;
    private String tituloDetalle;
    private int v_cur_id;
    private String v_cur_modulo;
    private String v_cur_codigo;
    private String v_cur_nombre;
    private SelectItem[] b_modulos;
    private SelectItem[] b_areas;
    private int b_mod_id;
    private int b_are_id = Integer.MAX_VALUE;
    private List<bTaller> cursos = new ArrayList<bTaller>();
    private List<bTaller> talleres = new ArrayList<bTaller>();
    private boolean tallerVer;
    private boolean tallerEditar;
    private String oncomplete;
    private String message;
    private String i_mod_descripcion;
    private int i_cur_id;
    private String i_cur_codigo;
    private String i_cur_nombre;
    private int i_pla_id;
    private String i_pla_descripcion;
    private List<bTaller> i_talleres = new ArrayList<bTaller>();
    private int i_tal_id;
    private String i_tal_codigo;
    private String i_tal_descripcion;
    private String i_tal_numero;
    private int tal_id_e;
    private String tal_codigo_e;
    private String tal_numero_e;
    private String tal_descripcion_e;
    private boolean e_editar;
    private boolean e_ver;
    int cont1 = 0;
    private List<Integer> ids_tal_delete = new ArrayList<Integer>();

    //CONSTRUCTORES
    public bTaller() {
    }

    public bTaller(int p) {
    }

    //METODOS
    public String Seleccionar() throws Exception {
        if (this.getB_mod_id() != 0) {
            HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
            HSPlanCurricularDAO dao_plan = (HSPlanCurricularDAO) ServiceFinder.findBean("SpringHibernateDaoCLPlanCurricular");
            this.setCursos(new ArrayList<bTaller>());
            System.out.println("MODULO: " + this.getB_mod_id());

            ClPlancurricular p = dao_plan.buscarPlan(this.getB_mod_id());

            /*System.out.println("Plan_id: "+p.getPlaId());*/

            List<ClArbolAcademico> lista_curso = dao.seleccionarCursos(this.getB_mod_id());
            if (!lista_curso.isEmpty()) {
                for (int i = 0; i < lista_curso.size(); i++) {
                    System.out.println("CURSOS_ID: " + lista_curso.get(i).getArbId());
                    System.out.println("CURSOS: " + lista_curso.get(i).getArbDescripcion());
                    /*System.out.println("Plan_id CURSO: "+p.getPlaId());*/
                    bTaller cur = new bTaller(i);
                    cur.setV_cur_id(lista_curso.get(i).getArbId());
                    cur.setV_cur_modulo(lista_curso.get(i).getArbDescripcion());//traer nombre del mudlo
                    cur.setV_cur_codigo(lista_curso.get(i).getArbCodigo());
                    cur.setV_cur_nombre(lista_curso.get(i).getArbDescripcion());
                    cur.setTalleres(new ArrayList<bTaller>());



                    List<ClArbolAcademico> lista_taller = dao.seleccionarTalleres(lista_curso.get(i).getArbId());
                    for (int j = 0; j < lista_taller.size(); j++) {
                        bTaller tal = new bTaller(j);
                        tal.setTal_id(lista_taller.get(j).getArbId());
                        tal.setTal_codigo(lista_taller.get(j).getArbCodigo());
                        tal.setTal_numero(lista_taller.get(j).getTalNumero());
                        tal.setTal_descripcion(lista_taller.get(j).getArbDescripcion());

                        if (p != null) {
                            System.out.println("Plan_id taller: " + p.getPlaId());
                            tal.setPla_id(p.getPlaId());
                            tal.setPla_descripcion(p.getPlaDescripcion());
                        }
                        /*System.out.println("Plan_id taller: "+p.getPlaId());
                        tal.setPla_id(p.getPlaId());
                        tal.setPla_descripcion(p.getPlaDescripcion());*/

                        tal.setTallerVer(true);
                        tal.setTallerEditar(false);

                        tal.setE_tal_id(0);
                        tal.setE_tal_codigo("");
                        tal.setE_tal_numero("");
                        tal.setE_tal_descripcion("");

                        cur.getTalleres().add(tal);
                    }
                    cur.setVerDetalle(false);
                    cur.setImagenDetalle("/Imagenes/actions/down.png");
                    cur.setTituloDetalle("Ver Talleres");

                    this.getCursos().add(cur);
                }

            } else {
                this.setCursos(new ArrayList<bTaller>());
            }







            /*HSCursoCLDAO daoCurso = (HSCursoCLDAO) ServiceFinder.findBean("SpringHibernateDaoCLCurso");
            HSTallerDAO daoTaller = (HSTallerDAO) ServiceFinder.findBean("SpringHibernateDaoCLTaller");
            this.setCursos(new ArrayList<bTaller>());
            
            List<ClCurso> lista_curso = daoCurso.seleccionarCursos(this.getB_mod_id());
            if (!lista_curso.isEmpty()) {
            for (int i = 0; i < lista_curso.size(); i++) {
            bTaller cur = new bTaller(i);
            cur.setV_cur_id(lista_curso.get(i).getCurId());
            cur.setV_cur_modulo(lista_curso.get(i).getClModulo().getModDescripcion());
            cur.setV_cur_codigo(lista_curso.get(i).getCurCodigo());
            cur.setV_cur_nombre(lista_curso.get(i).getCurNombre());
            
            cur.setTalleres(new ArrayList<bTaller>());
            System.out.println("CURSO_ID: "+lista_curso.get(i).getCurId());
            List<ClTaller> lista_taller = daoTaller.seleccionarTalleres(lista_curso.get(i).getCurId());
            for (int j = 0; j < lista_taller.size(); j++) {
            bTaller tal = new bTaller(j);
            tal.setTal_id(lista_taller.get(j).getTalId());
            tal.setTal_codigo(lista_taller.get(j).getTalCodigo());
            tal.setTal_numero(lista_taller.get(j).getTalNumero());
            tal.setTal_descripcion(lista_taller.get(j).getTalDescripcion());
            //tal.setCur_id(lista_taller.get(j).getClCurso().getCurId());
            tal.setPla_id(lista_taller.get(j).getClPlancurricular().getPlaId());
            tal.setPla_descripcion(lista_taller.get(j).getClPlancurricular().getPlaDescripcion());
            
            tal.setTallerVer(true);
            tal.setTallerEditar(false);
            
            tal.setE_tal_id(0);
            tal.setE_tal_codigo("");
            tal.setE_tal_numero("");
            tal.setE_tal_descripcion("");
            
            cur.getTalleres().add(tal);
            }
            
            cur.setVerDetalle(false);
            cur.setImagenDetalle("/Imagenes/actions/down.png");
            cur.setTituloDetalle("Ver Talleres");
            
            this.getCursos().add(cur);
            }
            }
            } else {
            this.setCursos(new ArrayList<bTaller>());*/
        }
        return "";
    }

    public void MostrarTalleres(ActionEvent event) throws Exception {
        int id_detalle = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_detalle")).getValue().toString());
        for (int i = 0; i < this.getCursos().size(); i++) {
            if (this.getCursos().get(i).getV_cur_id() == id_detalle) {
                if (this.getCursos().get(i).isVerDetalle()) {
                    this.getCursos().get(i).setVerDetalle(false);
                    this.getCursos().get(i).setImagenDetalle("/Imagenes/actions/down.png");
                    this.getCursos().get(i).setTituloDetalle("Ver Talleres");
                } else {
                    this.getCursos().get(i).setVerDetalle(true);
                    this.getCursos().get(i).setImagenDetalle("/Imagenes/actions/up.png");
                    this.getCursos().get(i).setTituloDetalle("Ocultar Talleres");
                }
                i = this.getCursos().size();
            }
        }
    }

    public void CargarTalleres(ActionEvent event) throws Exception {
        this.setOncomplete("");
        try {
            int id_talleres = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_talleres")).getValue().toString());
            /*HSCursoCLDAO daoCurso = (HSCursoCLDAO) ServiceFinder.findBean("SpringHibernateDaoCLCurso");
            HSTallerDAO daoTaller = (HSTallerDAO) ServiceFinder.findBean("SpringHibernateDaoCLTaller");*/
            HSPlanCurricularDAO daoPlancur = (HSPlanCurricularDAO) ServiceFinder.findBean("SpringHibernateDaoCLPlanCurricular");
            HSArbolAcademicoClDao daoArb = CommonDAO.getClArbolAcademicoDAO();

            ClArbolAcademico curso = daoArb.buscarCurso(id_talleres);

            this.setI_mod_descripcion("");// MODULO
            this.setI_cur_id(curso.getArbId());
            this.setI_cur_codigo(curso.getArbCodigo());
            this.setI_cur_nombre(curso.getArbDescripcion());

            ClPlancurricular p = daoPlancur.buscarPlan(this.getB_mod_id());

            this.setI_pla_id(p.getPlaId());
            this.setI_pla_descripcion(p.getPlaDescripcion());


            this.setI_tal_codigo("");
            this.setI_tal_numero("");
            this.setI_tal_descripcion(curso.getArbDescripcion());
            this.setI_tal_id(0);
            cont1 = 0;

            this.setIds_tal_delete(new ArrayList<Integer>());

            this.setI_talleres(new ArrayList<bTaller>());
            List<ClArbolAcademico> tmp_talleres = daoArb.seleccionarTalleres(curso.getArbId());

            if (tmp_talleres.size() > 0) {
                for (int i = 0; i < tmp_talleres.size(); i++) {
                    bTaller tal = new bTaller(i);
                    tal.setI_tal_id(tmp_talleres.get(i).getArbId());
                    tal.setI_tal_codigo(tmp_talleres.get(i).getArbCodigo());
                    tal.setI_tal_numero(tmp_talleres.get(i).getTalNumero());
                    tal.setI_tal_descripcion(tmp_talleres.get(i).getArbDescripcion());

                    tal.setTal_id_e(0);
                    tal.setTal_codigo_e("");
                    tal.setTal_numero_e("");
                    tal.setTal_descripcion_e("");

                    tal.setE_ver(true);
                    tal.setE_editar(false);

                    this.getI_talleres().add(tal);
                }
            }
            this.setOncomplete("Richfaces.showModalPanel('mpTaller')");
        } catch (Exception e) {
            this.setOncomplete("javascript:alert('Debe crear un plan curricular.')");
        }
    }

    public void GuardarDetalle(ActionEvent event) throws Exception {
        this.setMessage("");
        int id_detalle_update = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_detalle_update")).getValue().toString());
        
        HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();

        int flag = 0;
        for (int i = 0; i < this.getCursos().size(); i++) {
            for (int j = 0; j < this.getCursos().get(i).getTalleres().size(); j++) {
                if (this.getCursos().get(i).getTalleres().get(j).getTal_id() == id_detalle_update) {
                    if (this.getCursos().get(i).getTalleres().get(j).getE_tal_codigo().trim().length() > 0 && this.getCursos().get(i).getTalleres().get(j).getE_tal_numero().trim().length() > 0 && this.getCursos().get(i).getTalleres().get(j).getE_tal_descripcion().trim().length() > 0) {
                        ClArbolAcademico taller = new ClArbolAcademico();
                        
                        taller.setArbId(this.getCursos().get(i).getTalleres().get(j).getE_tal_id());
                        taller.setArbCodigo(this.getCursos().get(i).getTalleres().get(j).getE_tal_codigo().trim().toUpperCase());
                        taller.setArbDescripcion(this.getCursos().get(i).getTalleres().get(j).getE_tal_descripcion().trim().toUpperCase());
                        taller.setTalNumero(this.getCursos().get(i).getTalleres().get(j).getE_tal_numero().trim().toUpperCase());
                        
                        dao.actualizarTaller(taller);
                        
                        /*tal.setTalId(this.getCursos().get(i).getTalleres().get(j).getE_tal_id());
                        tal.setTalCodigo(this.getCursos().get(i).getTalleres().get(j).getE_tal_codigo().trim().toUpperCase());
                        tal.setTalNumero(this.getCursos().get(i).getTalleres().get(j).getE_tal_numero().trim().toUpperCase());
                        tal.setTalDescripcion(this.getCursos().get(i).getTalleres().get(j).getE_tal_descripcion().trim().toUpperCase());
                        ClPlancurricular plan = new ClPlancurricular();
                        plan.setPlaId(this.getCursos().get(i).getTalleres().get(j).getPla_id());
                        tal.setClPlancurricular(plan);
                        ClCurso cur = new ClCurso();
                        cur.setCurId(this.getCursos().get(i).getTalleres().get(j).getCur_id());
                        //tal.setClCurso(cur);
                        tal.setTalActivo("1");

                        
                        daoTaller.actualizarTaller(tal);*/

                        this.getCursos().get(i).getTalleres().get(j).setTal_id(this.getCursos().get(i).getTalleres().get(j).getE_tal_id());
                        this.getCursos().get(i).getTalleres().get(j).setTal_codigo(this.getCursos().get(i).getTalleres().get(j).getE_tal_codigo().trim().toUpperCase());
                        this.getCursos().get(i).getTalleres().get(j).setTal_numero(this.getCursos().get(i).getTalleres().get(j).getE_tal_numero().trim().toUpperCase());
                        this.getCursos().get(i).getTalleres().get(j).setTal_descripcion(this.getCursos().get(i).getTalleres().get(j).getE_tal_descripcion().trim().toUpperCase());

                        this.getCursos().get(i).getTalleres().get(j).setTallerVer(true);
                        this.getCursos().get(i).getTalleres().get(j).setTallerEditar(false);
                    } else {
                        this.setMessage("javascript:alert('Debe ingresar un codigo y un nombre para el Taller.')");
                    }

                    j = this.getCursos().get(i).getTalleres().size();
                    flag++;
                }
            }
        }
    }

    public void EditarDetalle(ActionEvent event) throws Exception {
        int id_detalle_update = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_detalle_update")).getValue().toString());
        int flag = 0;
        for (int i = 0; i < this.getCursos().size(); i++) {
            for (int j = 0; j < this.getCursos().get(i).getTalleres().size(); j++) {
                if (this.getCursos().get(i).getTalleres().get(j).getTal_id() == id_detalle_update) {
                    this.getCursos().get(i).getTalleres().get(j).setE_tal_id(this.getCursos().get(i).getTalleres().get(j).getTal_id());
                    this.getCursos().get(i).getTalleres().get(j).setE_tal_codigo(this.getCursos().get(i).getTalleres().get(j).getTal_codigo());
                    this.getCursos().get(i).getTalleres().get(j).setE_tal_numero(this.getCursos().get(i).getTalleres().get(j).getTal_numero());
                    this.getCursos().get(i).getTalleres().get(j).setE_tal_descripcion(this.getCursos().get(i).getTalleres().get(j).getTal_descripcion());

                    this.getCursos().get(i).getTalleres().get(j).setTallerVer(false);
                    this.getCursos().get(i).getTalleres().get(j).setTallerEditar(true);

                    j = this.getCursos().get(i).getTalleres().size();
                    flag++;
                }
            }
            if (flag > 0) {
                i = this.getCursos().size();
            }
        }
    }

    public void CancelarDetalle(ActionEvent event) throws Exception {
        int id_detalle_update = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_detalle_update")).getValue().toString());
        int flag = 0;
        for (int i = 0; i < this.getCursos().size(); i++) {
            for (int j = 0; j < this.getCursos().get(i).getTalleres().size(); j++) {
                if (this.getCursos().get(i).getTalleres().get(j).getTal_id() == id_detalle_update) {
                    this.getCursos().get(i).getTalleres().get(j).setTallerVer(true);
                    this.getCursos().get(i).getTalleres().get(j).setTallerEditar(false);

                    j = this.getCursos().get(i).getTalleres().size();
                    flag++;
                }
            }
            if (flag > 0) {
                i = this.getCursos().size();
            }
        }
    }

    public void EliminarDetalle(ActionEvent event) throws Exception {
        this.setMessage("");
        int id_detalle_delete = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_detalle_delete")).getValue().toString());
        
        HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
        if (dao.verificarEliminarTaller(id_detalle_delete).size() > 0) {
            this.setMessage("javascript:alert('Este Taller posee talleres Aperturados.')");
        } else {
            dao.eliminarTaller(id_detalle_delete);
            int flag = 0;
            for (int i = 0; i < this.getCursos().size(); i++) {
                for (int j = 0; j < this.getCursos().get(i).getTalleres().size(); j++) {
                    if (this.getCursos().get(i).getTalleres().get(j).getTal_id() == id_detalle_delete) {
                        this.getCursos().get(i).getTalleres().remove(j);

                        j = this.getCursos().get(i).getTalleres().size();
                        flag++;
                    }
                }
                if (flag > 0) {
                    i = this.getCursos().size();
                }
            }
        }
    }

    public void AgregarTaller(ActionEvent event) throws Exception {
        this.setMessage("");
        if (this.getI_tal_codigo().trim().length() > 0 && this.getI_tal_numero().trim().length() > 0 && this.getI_tal_descripcion().trim().length() > 0) {
            bTaller tal = new bTaller(0);
            tal.setI_tal_id(cont1);
            tal.setI_tal_codigo(this.getI_tal_codigo().trim().toUpperCase());
            tal.setI_tal_numero(this.getI_tal_numero().trim().toUpperCase());
            tal.setI_tal_descripcion(this.getI_tal_descripcion().trim().toUpperCase());

            tal.setTal_id_e(0);
            tal.setTal_codigo_e("");
            tal.setTal_numero_e("");
            tal.setTal_descripcion_e("");

            tal.setE_ver(true);
            tal.setE_editar(false);

            this.getI_talleres().add(tal);
            cont1--;

            this.setI_tal_codigo("");
            this.setI_tal_numero("");
        } else {
            this.setMessage("javascript:alert('Todos los campos son obligatorios.')");
        }
    }

    public void GuardarTaller(ActionEvent event) throws Exception {
        this.setMessage("");
        int id_tal_update = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_tal_update")).getValue().toString());
        for (int i = 0; i < this.getI_talleres().size(); i++) {
            if (this.getI_talleres().get(i).getI_tal_id() == id_tal_update) {
                if (this.getI_talleres().get(i).getTal_codigo_e().trim().length() > 0 && this.getI_talleres().get(i).getTal_numero_e().trim().length() > 0 && this.getI_talleres().get(i).getTal_descripcion_e().trim().length() > 0) {
                    this.getI_talleres().get(i).setI_tal_id(this.getI_talleres().get(i).getTal_id_e());
                    this.getI_talleres().get(i).setI_tal_codigo(this.getI_talleres().get(i).getTal_codigo_e().trim().toUpperCase());
                    this.getI_talleres().get(i).setI_tal_numero(this.getI_talleres().get(i).getTal_numero_e().trim().toUpperCase());
                    this.getI_talleres().get(i).setI_tal_descripcion(this.getI_talleres().get(i).getTal_descripcion_e().trim().toUpperCase());

                    this.getI_talleres().get(i).setE_editar(false);
                    this.getI_talleres().get(i).setE_ver(true);
                } else {
                    this.setMessage("javascript:alert('Debe ingresar un codigo y numero para Guardar.')");
                }

                i = this.getI_talleres().size();
            }
        }
    }

    public void EditarTaller(ActionEvent event) throws Exception {
        int id_tal_update = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_tal_update")).getValue().toString());
        for (int i = 0; i < this.getI_talleres().size(); i++) {
            if (this.getI_talleres().get(i).getI_tal_id() == id_tal_update) {
                this.getI_talleres().get(i).setTal_id_e(this.getI_talleres().get(i).getI_tal_id());
                this.getI_talleres().get(i).setTal_codigo_e(this.getI_talleres().get(i).getI_tal_codigo());
                this.getI_talleres().get(i).setTal_numero_e(this.getI_talleres().get(i).getI_tal_numero());
                this.getI_talleres().get(i).setTal_descripcion_e(this.getI_talleres().get(i).getI_tal_descripcion());

                this.getI_talleres().get(i).setE_ver(false);
                this.getI_talleres().get(i).setE_editar(true);

                i = this.getI_talleres().size();
            }
        }
    }

    public void CancelarTaller(ActionEvent event) throws Exception {
        int id_tal_update = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_tal_update")).getValue().toString());
        for (int i = 0; i < this.getI_talleres().size(); i++) {
            if (this.getI_talleres().get(i).getI_tal_id() == id_tal_update) {
                this.getI_talleres().get(i).setE_ver(true);
                this.getI_talleres().get(i).setE_editar(false);

                i = this.getI_talleres().size();
            }
        }
    }

    public void EliminarTaller(ActionEvent event) throws Exception {
        this.setMessage("");
        int id_tal_delete = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_tal_delete")).getValue().toString());
        
        HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
        if (dao.verificarEliminarTaller(id_tal_delete).size() > 0) {
            this.setMessage("javascript:alert('Este Taller posee talleres Aperturados.')");
        } else {
            dao.eliminarTaller(id_tal_delete);
            /*daoTaller.eliminarTaller(id_tal_delete);*/
            for (int i = 0; i < this.getI_talleres().size(); i++) {
                if (this.getI_talleres().get(i).getI_tal_id() == id_tal_delete) {
                    if (this.getI_talleres().get(i).getI_tal_id() > 0) {
                        this.getIds_tal_delete().add(this.getI_talleres().get(i).getI_tal_id());
                    }

                    this.getI_talleres().remove(i);
                    i = this.getI_talleres().size();
                }
            }
        }
    }

    public void GuardarTalleres(ActionEvent event) throws Exception {
        this.setOncomplete("");

        String inst="";
        HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();

        if (this.getI_talleres().size() > 0) {
            for (int i = 0; i < this.getI_talleres().size(); i++) { //ACTUALIZAR TALLER
                if (this.getI_talleres().get(i).getI_tal_id() > 0) {

                    ClArbolAcademico taller = new ClArbolAcademico();

                    taller.setArbId(this.getI_talleres().get(i).getI_tal_id());
                    taller.setArbCodigo(this.getI_talleres().get(i).getI_tal_codigo().trim().toUpperCase());
                    taller.setArbDescripcion(this.getI_talleres().get(i).getI_tal_descripcion().trim().toUpperCase());
                    taller.setArbActivo("1");
                    taller.setTalNumero(this.getI_talleres().get(i).getI_tal_numero().trim().toUpperCase());

                    dao.actualizarTaller(taller);
                } else { //NUEVO TALLER
                    
                    ClArbolAcademico taller = new ClArbolAcademico();
                    inst = dao.buscarArbolPorId(this.getI_cur_id()).getArbInstitucion();
                    

//                    List a = dao.obtenerUltimoTaller(this.getI_cur_id());
                    String nuevo_cod = "";
//                    if (!a.isEmpty()) {// SI YA TIENE TALLERES CREADOS
//
//                        for (int z = 0; z < 1; z++) {
//                            String cod = ((ClArbolAcademico) a.get(z)).getArbOpcion().substring(0, 8);
//                            String cod2 = ((ClArbolAcademico) a.get(z)).getArbOpcion().substring(8, 10);
//                            int num_cod = Integer.parseInt(cod2);
//                            num_cod++;
//                            if (num_cod < 10) {
//                                nuevo_cod = cod + "0" + num_cod;
//                            } else {
//                                nuevo_cod = cod + num_cod;
//                            }
//                        }
//                    } else {//SI NO TIENE NINGUN TALLER CREADO
//                        List<ClArbolAcademico> curso_opc = dao.obtenerDescripcionCurso(this.getI_cur_id());
//                        String opcion_curso = (curso_opc.get(0).getArbOpcion());
//                        nuevo_cod = opcion_curso + "01";
//                    }


                    taller.setArbId(this.getI_tal_id());
                    taller.setArbOpcion(nuevo_cod);
                    taller.setArbCodigo(this.getI_talleres().get(i).getI_tal_codigo().trim().toUpperCase());
                    taller.setArbDescripcion(this.getI_talleres().get(i).getI_tal_descripcion().trim().toUpperCase());
                    taller.setArbTipo("069002");
                    taller.setArbNivel(4);
                    taller.setArbActivo("1");
                    taller.setArbIdPadre(this.getI_cur_id());
                    taller.setArbFlag("0");
                    taller.setTalNumero(this.getI_talleres().get(i).getI_tal_numero().trim().toUpperCase());
                    taller.setArbInstitucion( inst);
                    taller.setArbVisible( 1 );

                    dao.insertarTaller(taller);
                }
            }
            if (this.getIds_tal_delete().size() > 0) {
                for (int i = 0; i < this.getIds_tal_delete().size(); i++) {
                    dao.eliminarTaller(this.getIds_tal_delete().get(i));
                }
            }
            this.setOncomplete("javascript:alert('Cambios guardados.');Richfaces.hideModalPanel('mpTaller')");
        } else if (this.getIds_tal_delete().size() > 0) {
            for (int i = 0; i < this.getIds_tal_delete().size(); i++) {
                dao.eliminarTaller(this.getIds_tal_delete().get(i));
            }
            this.setOncomplete("javascript:alert('Cambios guardados.');Richfaces.hideModalPanel('mpTaller')");
        } else {
            this.setOncomplete("javascript:alert('Ningun Cambio efectuado.');Richfaces.hideModalPanel('mpTaller')");
        }
    }

    //GETTERS AND SETTERS
    public int getTal_id() {
        return tal_id;
    }

    public void setTal_id(int tal_id) {
        this.tal_id = tal_id;
    }

    public String getTal_codigo() {
        return tal_codigo;
    }

    public void setTal_codigo(String tal_codigo) {
        this.tal_codigo = tal_codigo;
    }

    public String getTal_numero() {
        return tal_numero;
    }

    public void setTal_numero(String tal_numero) {
        this.tal_numero = tal_numero;
    }

    public String getTal_activo() {
        return tal_activo;
    }

    public void setTal_activo(String tal_activo) {
        this.tal_activo = tal_activo;
    }

    public int getCur_id() {
        return cur_id;
    }

    public void setCur_id(int cur_id) {
        this.cur_id = cur_id;
    }

    public String getCur_descripcion() {
        return cur_descripcion;
    }

    public void setCur_descripcion(String cur_descripcion) {
        this.cur_descripcion = cur_descripcion;
    }

    public int getPla_id() {
        return pla_id;
    }

    public void setPla_id(int pla_id) {
        this.pla_id = pla_id;
    }

    public String getPla_descripcion() {
        return pla_descripcion;
    }

    public void setPla_descripcion(String pla_descripcion) {
        this.pla_descripcion = pla_descripcion;
    }

    public SelectItem[] getB_areas() {
        if (b_areas == null) {
            try {
                HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
                List<ClArbolAcademico> areas = dao.seleccionarArea("");
                b_areas = new SelectItem[areas.size() + 1];
                b_areas[0] = new SelectItem(Integer.MAX_VALUE, "[Seleccione]");
                for (int i = 0; i < areas.size(); i++) {
                    ClArbolAcademico a = areas.get(i);
                    b_areas[i + 1] = new SelectItem(a.getArbId(), a.getArbDescripcion());
                }
            } catch (Exception e) {
                System.err.println("Error al cargar areas");
            }
        }
        return b_areas;
    }

    public void setB_areas(SelectItem[] b_areas) {
        this.b_areas = b_areas;
    }

    public void ver_modulos() {
        try {
            try {
                HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
                List<ClArbolAcademico> modulos = dao.seleccionarModulos(this.b_are_id, "");

                b_modulos = new SelectItem[modulos.size() + 1];
                for (int i = 0; i < modulos.size(); i++) {
                    b_modulos[i + 1] = new SelectItem(modulos.get(i).getArbId(), modulos.get(i).getArbDescripcion());
                }
            } catch (Exception e) {
                b_modulos = new SelectItem[1];
            } finally {
                b_modulos[0] = new SelectItem(0, "[Seleccione]");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SelectItem[] getB_modulos() {
        ver_modulos();
        return b_modulos;
    }

    public void setB_modulos(SelectItem[] b_modulos) {
        this.b_modulos = b_modulos;
    }

    public int getB_are_id() {
        return b_are_id;
    }

    public void setB_are_id(int b_are_id) {
        this.b_are_id = b_are_id;
    }

    public int getB_mod_id() {
        return b_mod_id;
    }

    public void setB_mod_id(int b_mod_id) {
        this.b_mod_id = b_mod_id;
    }

    public int getV_cur_id() {
        return v_cur_id;
    }

    public void setV_cur_id(int v_cur_id) {
        this.v_cur_id = v_cur_id;
    }

    public String getV_cur_modulo() {
        return v_cur_modulo;
    }

    public void setV_cur_modulo(String v_cur_modulo) {
        this.v_cur_modulo = v_cur_modulo;
    }

    public String getV_cur_codigo() {
        return v_cur_codigo;
    }

    public void setV_cur_codigo(String v_cur_codigo) {
        this.v_cur_codigo = v_cur_codigo;
    }

    public String getV_cur_nombre() {
        return v_cur_nombre;
    }

    public void setV_cur_nombre(String v_cur_nombre) {
        this.v_cur_nombre = v_cur_nombre;
    }

    public List<bTaller> getCursos() {
        return cursos;
    }

    public void setCursos(List<bTaller> cursos) {
        this.cursos = cursos;
    }

    public List<bTaller> getTalleres() {
        return talleres;
    }

    public void setTalleres(List<bTaller> talleres) {
        this.talleres = talleres;
    }

    public boolean isVerDetalle() {
        return verDetalle;
    }

    public void setVerDetalle(boolean verDetalle) {
        this.verDetalle = verDetalle;
    }

    public String getImagenDetalle() {
        return imagenDetalle;
    }

    public void setImagenDetalle(String imagenDetalle) {
        this.imagenDetalle = imagenDetalle;
    }

    public String getTituloDetalle() {
        return tituloDetalle;
    }

    public void setTituloDetalle(String tituloDetalle) {
        this.tituloDetalle = tituloDetalle;
    }

    public boolean isTallerVer() {
        return tallerVer;
    }

    public void setTallerVer(boolean tallerVer) {
        this.tallerVer = tallerVer;
    }

    public boolean isTallerEditar() {
        return tallerEditar;
    }

    public void setTallerEditar(boolean tallerEditar) {
        this.tallerEditar = tallerEditar;
    }

    public int getE_tal_id() {
        return e_tal_id;
    }

    public void setE_tal_id(int e_tal_id) {
        this.e_tal_id = e_tal_id;
    }

    public String getE_tal_codigo() {
        return e_tal_codigo;
    }

    public void setE_tal_codigo(String e_tal_codigo) {
        this.e_tal_codigo = e_tal_codigo;
    }

    public String getE_tal_numero() {
        return e_tal_numero;
    }

    public void setE_tal_numero(String e_tal_numero) {
        this.e_tal_numero = e_tal_numero;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getI_mod_descripcion() {
        return i_mod_descripcion;
    }

    public void setI_mod_descripcion(String i_mod_descripcion) {
        this.i_mod_descripcion = i_mod_descripcion;
    }

    public int getI_cur_id() {
        return i_cur_id;
    }

    public void setI_cur_id(int i_cur_id) {
        this.i_cur_id = i_cur_id;
    }

    public String getI_cur_codigo() {
        return i_cur_codigo;
    }

    public void setI_cur_codigo(String i_cur_codigo) {
        this.i_cur_codigo = i_cur_codigo;
    }

    public String getI_cur_nombre() {
        return i_cur_nombre;
    }

    public void setI_cur_nombre(String i_cur_nombre) {
        this.i_cur_nombre = i_cur_nombre;
    }

    public int getI_pla_id() {
        return i_pla_id;
    }

    public void setI_pla_id(int i_pla_id) {
        this.i_pla_id = i_pla_id;
    }

    public String getI_pla_descripcion() {
        return i_pla_descripcion;
    }

    public void setI_pla_descripcion(String i_pla_descripcion) {
        this.i_pla_descripcion = i_pla_descripcion;
    }

    public List<bTaller> getI_talleres() {
        return i_talleres;
    }

    public void setI_talleres(List<bTaller> i_talleres) {
        this.i_talleres = i_talleres;
    }

    public int getI_tal_id() {
        return i_tal_id;
    }

    public void setI_tal_id(int i_tal_id) {
        this.i_tal_id = i_tal_id;
    }

    public String getI_tal_codigo() {
        return i_tal_codigo;
    }

    public void setI_tal_codigo(String i_tal_codigo) {
        this.i_tal_codigo = i_tal_codigo;
    }

    public String getI_tal_numero() {
        return i_tal_numero;
    }

    public void setI_tal_numero(String i_tal_numero) {
        this.i_tal_numero = i_tal_numero;
    }

    public int getTal_id_e() {
        return tal_id_e;
    }

    public void setTal_id_e(int tal_id_e) {
        this.tal_id_e = tal_id_e;
    }

    public String getTal_codigo_e() {
        return tal_codigo_e;
    }

    public void setTal_codigo_e(String tal_codigo_e) {
        this.tal_codigo_e = tal_codigo_e;
    }

    public String getTal_numero_e() {
        return tal_numero_e;
    }

    public void setTal_numero_e(String tal_numero_e) {
        this.tal_numero_e = tal_numero_e;
    }

    public boolean isE_editar() {
        return e_editar;
    }

    public void setE_editar(boolean e_editar) {
        this.e_editar = e_editar;
    }

    public boolean isE_ver() {
        return e_ver;
    }

    public void setE_ver(boolean e_ver) {
        this.e_ver = e_ver;
    }

    public List<Integer> getIds_tal_delete() {
        return ids_tal_delete;
    }

    public void setIds_tal_delete(List<Integer> ids_tal_delete) {
        this.ids_tal_delete = ids_tal_delete;
    }

    public String getTal_descripcion() {
        return tal_descripcion;
    }

    public void setTal_descripcion(String tal_descripcion) {
        this.tal_descripcion = tal_descripcion;
    }

    public String getE_tal_descripcion() {
        return e_tal_descripcion;
    }

    public void setE_tal_descripcion(String e_tal_descripcion) {
        this.e_tal_descripcion = e_tal_descripcion;
    }

    public String getI_tal_descripcion() {
        return i_tal_descripcion;
    }

    public void setI_tal_descripcion(String i_tal_descripcion) {
        this.i_tal_descripcion = i_tal_descripcion;
    }

    public String getTal_descripcion_e() {
        return tal_descripcion_e;
    }

    public void setTal_descripcion_e(String tal_descripcion_e) {
        this.tal_descripcion_e = tal_descripcion_e;
    }
}
