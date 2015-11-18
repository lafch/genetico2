/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSAlumnoCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSHorarioDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSMedioPublicidadCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSModuloDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSPublicoAlumnoCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSPublicoConsultaCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSPublicoConsultaDetalleCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSSeccionCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAperturadoClDAO;
import net.uch.cursoLibre.managedBeans.beans.BeanClAlumno;
import net.uch.cursoLibre.managedBeans.beans.BeanClConsulta;
import net.uch.cursoLibre.managedBeans.beans.MetodosCL;
import net.uch.mapping.ClAlumno;
import net.uch.mapping.ClHoraria;
import net.uch.mapping.ClMedioPublicidad;
import net.uch.mapping.ClMedioPublicidadDet;
import net.uch.mapping.ClModulo;
import net.uch.mapping.ClPublicoConsulta;
import net.uch.mapping.ClPublicoConsultaDetalle;
import net.uch.mapping.ClSeccion;
import net.uch.mapping.Sp_listarPublicoAlumno;
import net.uch.mapping.TbCatalogo;
import net.uch.mapping.TbDistrito;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSUbigeoDAO;
import net.uch.util.CommonDAO;

/**
 *
 * @author richard
 */
public class bakbInformes {

    private SelectItem[] cboPublicoAlumno;
    private String suggestion;
    private String w_datos;
    private String w_codigo;
    private int w_publico_id = 0;
    private String oncomplete;
    /********Inicio*********/
    // DATOS PERSONALES
    private SelectItem[] cboDepartamento;
    private SelectItem[] cboProvincia;
    private SelectItem[] cboDistrito;
    private String w_departamento = "000000";
    private String w_provincia = "000000";
    private String w_distrito = "000000";
    //INFORMACION
    private SelectItem[] cboInstituto;
    private SelectItem[] cboArea;
    private SelectItem[] cboModulo;
    private SelectItem[] cboTallerAperturado;
    private SelectItem[] cboSeccion;
    private SelectItem[] cboHorario;
    private SelectItem[] cboMedio;
    private SelectItem[] cboMedioDetalle;
    private String w_instituto;
    private String w_area = "0";
    private String w_modulo = "0";
    private String w_tallerAperturado = "0";
    private String w_seccion = "0";
    private String w_horario = "0";
    private String w_medio = "0";
    private String w_medio_det = "0";
    private String w_obs_medio = "";
    private boolean w_procedencia = true;
    private String w_obs_consulta = "";
    private String w_obs_consulta_editar = "";
    private Date w_fecha_contacto;
   // private BeanCLPublico alumno = new BeanCLPublico();
    //CONSULTAS REALIZADAS
    private int w_consulta_id;
    private List<BeanClConsulta> listaConsultas = new ArrayList<BeanClConsulta>();
    private List<ClHoraria> listaHoraria = new ArrayList<ClHoraria>();

    private SelectItem[] cbosexo;
    private SelectItem[] cboUnidad;
    private SelectItem[] cboFormaPago;
    private SelectItem[] cboParentesco;
    private SelectItem[] cboProcedencia;
    private SelectItem[] cboTipo;
    private SelectItem[] i_estructuras;
    private int i_estpag_id;

    public int getI_estpag_id() {
        return i_estpag_id;
    }

    public void setI_estpag_id(int i_estpag_id) {
        this.i_estpag_id = i_estpag_id;
    }

    

    public SelectItem[] getI_estructuras() {
        i_estructuras = new SelectItem[1];
        i_estructuras[0] = new SelectItem("0", "[Seleccione]");
        
        return i_estructuras;
    }

    public void setI_estructuras(SelectItem[] i_estructuras) {
        this.i_estructuras = i_estructuras;
    }
    
    private int w_consulta_id_edit = 0;
    private List<ClPublicoConsultaDetalle> listaConsultasDet = new ArrayList<ClPublicoConsultaDetalle>();

    public List<ClPublicoConsultaDetalle> getListaConsultasDet() {
        return listaConsultasDet;
    }

    public void setListaConsultasDet(List<ClPublicoConsultaDetalle> listaConsultasDet) {
        this.listaConsultasDet = listaConsultasDet;
    }

    public int getW_consulta_id_edit() {
        return w_consulta_id_edit;
    }

    public void setW_consulta_id_edit(int w_consulta_id_edit) {
        this.w_consulta_id_edit = w_consulta_id_edit;
    }

    

    public SelectItem[] getCboFormaPago() {
        MetodosCL metodos= new MetodosCL();
        cboFormaPago=metodos.listarCatalogoGrupo("041");
        return cboFormaPago;
    }

    public void setCboFormaPago(SelectItem[] cboFormaPago) {
        this.cboFormaPago = cboFormaPago;
    }

    public SelectItem[] getCboParentesco() {
        MetodosCL metodos= new MetodosCL();
        cboParentesco= metodos.listarCatalogoGrupo("040");
        return cboParentesco;
    }

    public void setCboParentesco(SelectItem[] cboParentesco) {
        this.cboParentesco = cboParentesco;
    }

    public SelectItem[] getCboProcedencia() {
        MetodosCL metodos= new MetodosCL();
        cboProcedencia = metodos.listarCatalogoGrupo("023");
        return cboProcedencia;
    }

    public void setCboProcedencia(SelectItem[] cboProcedencia) {
        this.cboProcedencia = cboProcedencia;
    }

    public SelectItem[] getCboTipo() {
        MetodosCL metodos= new MetodosCL();
        cboTipo=metodos.listarCatalogoGrupo("057");
        return cboTipo;
    }

    public void setCboTipo(SelectItem[] cboTipo) {
        this.cboTipo = cboTipo;
    }

    public SelectItem[] getCboUnidad() {
        MetodosCL metodos= new MetodosCL();
        cboUnidad=metodos.listarCatalogoGrupo("042");
        return cboUnidad;
    }

    public void setCboUnidad(SelectItem[] cboUnidad) {
        this.cboUnidad = cboUnidad;
    }

    public SelectItem[] getCbosexo() {
        MetodosCL metodos= new MetodosCL();
        cbosexo = metodos.listarCatalogoGrupo("004");
        return cbosexo;
    }

    public void setCbosexo(SelectItem[] cbosexo) {
        this.cbosexo = cbosexo;
    }



    /*Fin*/

    private BeanClAlumno alumnocl = new BeanClAlumno();

    public BeanClAlumno getAlumnocl() {
        return alumnocl;
    }

    public void setAlumnocl(BeanClAlumno alumnocl) {
        this.alumnocl = alumnocl;
    }

    //SETTERS Y GETTERS
    public String getW_medio() {
        return w_medio;
    }
    

    public String getW_medio_det() {
        return w_medio_det;
    }

    public String getW_obs_medio() {
        return w_obs_medio;
    }

    public String getW_obs_consulta() {
        return w_obs_consulta;
    }

    public String getW_obs_consulta_editar() {
        return w_obs_consulta_editar;
    }

    public boolean isW_procedencia() {
        return w_procedencia;
    }

    public Date getW_fecha_contacto() {
        return w_fecha_contacto;
    }

    public String getW_codigo() {
        return w_codigo;
    }

    public String getW_datos() {
        return w_datos;
    }

    public int getW_publico_id() {
        return w_publico_id;
    }

    public String getW_departamento() {
        return w_departamento;
    }

    public String getW_provincia() {
        return w_provincia;
    }

    public String getW_distrito() {
        return w_distrito;
    }

    public String getW_instituto() {
        return this.w_instituto;
    }

    public String getW_area() {
        return this.w_area;
    }

    public String getW_modulo() {
        return this.w_modulo;
    }

    public String getW_tallerAperturado() {
        return w_tallerAperturado;
    }

    public String getW_seccion() {
        return w_seccion;
    }

    public String getW_horario() {
        return w_horario;
    }

    public int getW_consulta_id() {
        return w_consulta_id;
    }

    public List<BeanClConsulta> getListaConsultas() {
        return this.listaConsultas;
    }

    public List<ClHoraria> getListaHoraria() {
        traerHorario();
        return listaHoraria;
    }

    public void setW_medio(String w_medio) {
        this.w_medio = w_medio;
    }

    public void setW_medio_det(String w_medio_det) {
        this.w_medio_det = w_medio_det;
    }

    public void setW_obs_medio(String w_obs_medio) {
        this.w_obs_medio = w_obs_medio;
    }

    public void setW_obs_consulta(String w_obs_consulta) {
        this.w_obs_consulta = w_obs_consulta;
    }

    public void setW_obs_consulta_editar(String w_obs_consulta_editar) {
        this.w_obs_consulta_editar = w_obs_consulta_editar;
    }

    public void setW_procedencia(boolean w_procedencia) {
        this.w_procedencia = w_procedencia;
    }

    public void setW_fecha_contacto(Date w_fecha_contacto) {
        this.w_fecha_contacto = w_fecha_contacto;
    }

    public void setW_codigo(String w_codigo) {
        this.w_codigo = w_codigo;
    }

    public void setW_datos(String w_datos) {
        this.w_datos = w_datos;
    }

    public void setW_publico_id(int w_publico_id) {
        this.w_publico_id = w_publico_id;
    }

    public void setW_departamento(String w_departamento) {
        this.w_departamento = w_departamento;
    }

    public void setW_provincia(String w_provincia) {
        this.w_provincia = w_provincia;
    }

    public void setW_distrito(String w_distrito) {
        this.w_distrito = w_distrito;
    }

    public void setW_instituto(String w_instituto) {
        this.w_instituto = w_instituto;
    }

    public void setW_area(String w_area) {
        this.w_area = w_area;
    }

    public void setW_modulo(String w_modulo) {
        this.w_modulo = w_modulo;
    }

    public void setW_tallerAperturado(String w_tallerAperturado) {
        this.w_tallerAperturado = w_tallerAperturado;
    }

    public void setW_seccion(String w_seccion) {
        this.w_seccion = w_seccion;
    }

    public void setW_horario(String w_horario) {
        this.w_horario = w_horario;
    }

    public void setW_consulta_id(int w_consulta_id) {
        this.w_consulta_id = w_consulta_id;
    }

    public void setListaConsultas(List<BeanClConsulta> listaConsultas) {
        this.listaConsultas = listaConsultas;
    }

    public void setListaHoraria(List<ClHoraria> listaHoraria) {
        this.listaHoraria = listaHoraria;
    }

    //-------------FIN SETTERS Y GETTERS
    public SelectItem[] getCboDepartamento() {
        try {
            HSUbigeoDAO dao = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
            List lista = dao.seleccionarDepartamento();
            cboDepartamento = new SelectItem[lista.size() + 1];
            cboDepartamento[0] = new SelectItem("000000", "[Seleccione]");
            for (int i = 0; i < (cboDepartamento.length - 1); i++) {
                cboDepartamento[i + 1] = new SelectItem(((TbDistrito) lista.get(i)).getId(), ((TbDistrito) lista.get(i)).getDisDes());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cboDepartamento;
    }

    public SelectItem[] getCboProvincia() {
        try {
            HSUbigeoDAO dao = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
            List<TbDistrito> lista = dao.seleccionarProvincia(this.getW_departamento());
            if (lista.size() != 0) {
                this.cboProvincia = new SelectItem[lista.size() + 1];
                this.cboProvincia[0] = new SelectItem("000000", "[Seleccione]");
                for (int i = 0; i < (this.cboProvincia.length - 1); i++) {
                    this.cboProvincia[i + 1] = new SelectItem(lista.get(i).getId(), lista.get(i).getDisDes());
                }
            } else {
                this.cboProvincia = new SelectItem[1];
                this.cboProvincia[0] = new SelectItem("000000", "[Seleccione]");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.cboProvincia;
    }

    public SelectItem[] getCboDistrito() {
        try {
            HSUbigeoDAO dao = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
            List<TbDistrito> lista = dao.seleccionarDistrito(this.getW_departamento(), this.getW_provincia());
            if (lista.size() != 0) {
                cboDistrito = new SelectItem[lista.size() + 1];
                cboDistrito[0] = new SelectItem("000000", "[Seleccione]");
                for (int i = 0; i < (cboDistrito.length - 1); i++) {
                    cboDistrito[i + 1] = new SelectItem(lista.get(i).getId(), lista.get(i).getDisDes());
                }
            } else {
                cboDistrito = new SelectItem[1];
                cboDistrito[0] = new SelectItem("000000", "[Seleccione]");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cboDistrito;
    }

    public SelectItem[] getCboInstituto() {
        try {
            HSCatalogoDAO dao = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List lista = dao.seleccionarGrupo("061");
            cboInstituto = new SelectItem[lista.size() + 1];
            cboInstituto[0] = new SelectItem("000000", "[Seleccione]");
            for (int i = 0; i < (cboInstituto.length - 1); i++) {
                cboInstituto[i + 1] = new SelectItem(((TbCatalogo) lista.get(i)).getId(), ((TbCatalogo) lista.get(i)).getCatDescripcionElemento());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cboInstituto;
    }

    public SelectItem[] getCboModulo() {
        try {

            HSModuloDAO dao = (HSModuloDAO) ServiceFinder.findBean("SpringHibernateDaoCLModulo");
            List lista = dao.listarModulos();
            cboModulo = new SelectItem[lista.size() + 1];
            cboModulo[0] = new SelectItem("0", "[Seleccione]");
            for (int i = 0; i < (cboModulo.length - 1); i++) {
                cboModulo[i + 1] = new SelectItem(((ClModulo) lista.get(i)).getModId(), ((ClModulo) lista.get(i)).getModDescripcion());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cboModulo;
    }

    /*public SelectItem[] getCboTallerAperturado() {
        try {
            HSTallerAperturadoDAO dao = (HSTallerAperturadoDAO) ServiceFinder.findBean("SpringHibernateDaoCLTallerAperturado");
            //POR REVISAR (#JTV)- 21/09/2012
            /*List<ClTallerAperturado> lista = dao.seleccionarTallerAperturadoxModulo(Integer.parseInt(this.getW_modulo()));
            if (!lista.isEmpty()) {
                this.cboTallerAperturado = new SelectItem[lista.size() + 1];
                this.cboTallerAperturado[0] = new SelectItem("0", "[Seleccione]");
                for (int i = 0; i < (this.cboTallerAperturado.length - 1); i++) {
                    this.cboTallerAperturado[i + 1] = new SelectItem(lista.get(i).getTalapeId(), lista.get(i).getTalapeDescripcion());
                }
            } else {
                this.cboTallerAperturado = new SelectItem[1];
                this.cboTallerAperturado[0] = new SelectItem("0", "[Seleccione]");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("DESCRIPCION DE ERROR Taller aperturado : " + e.getMessage());
        }
        return cboTallerAperturado;
    }*/

    public SelectItem[] getCboSeccion() {
        //SpringHibernateDaoCLSeccion
        try {
            HSSeccionCLDAO dao = (HSSeccionCLDAO) ServiceFinder.findBean("SpringHibernateDaoCLSeccion");
            List<ClSeccion> lista = dao.seleccionarSecciones(Integer.parseInt(this.getW_tallerAperturado()));
            if (lista.size() != 0) {
                this.cboSeccion = new SelectItem[lista.size() + 1];
                this.cboSeccion[0] = new SelectItem("0", "[Seleccione]");
                for (int i = 0; i < (this.cboSeccion.length - 1); i++) {
                    this.cboSeccion[i + 1] = new SelectItem(lista.get(i).getSecId(), lista.get(i).getSecNombre());
                }
            } else {
                this.cboSeccion = new SelectItem[1];
                this.cboSeccion[0] = new SelectItem("0", "[Seleccione]");
            }
        } catch (Exception e) {
            System.out.println("erro el cboSeccion -> " + e);
            e.printStackTrace();
        }
        return cboSeccion;
    }

    public SelectItem[] getCboHorario() {
        try {
            this.cboHorario = new SelectItem[1];
            this.cboHorario[0] = new SelectItem("0", "[Seleccione]");
            /*HSHorarioDAO dao = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoCLHorario");
            List<ClHoraria> lista = dao.seleccionarHorario(w_publico_id);
            if (lista.size() != 0) {
            this.cboHorario = new SelectItem[lista.size() + 1];
            this.cboHorario[0] = new SelectItem("0", "[Seleccione]");
            for (int i = 0; i < (this.cboHorario.length - 1); i++) {
            this.cboHorario[i + 1] = new SelectItem(lista.get(i).getHorId(), lista.get(i).get);
            }
            } else {
            this.cboCurso = new SelectItem[1];
            this.cboCurso[0] = new SelectItem("0", "[Seleccione]");
            }*/

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("DESCRIPCION DE ERROR : " + e.getMessage());
        }
        return this.cboHorario;
    }

    public SelectItem[] getCboArea() {
        return this.cboArea;
    }

    public SelectItem[] getCboMedio() {
        try {
            HSMedioPublicidadCLDAO dao = (HSMedioPublicidadCLDAO) ServiceFinder.findBean("SpringHibernateMedioPublicidad");
            List<ClMedioPublicidad> lista = dao.listarMedioPublicidad();
            cboMedio = new SelectItem[lista.size() + 1];
            cboMedio[0] = new SelectItem("0", "[Seleccione]");
            for (int i = 0; i < (cboMedio.length - 1); i++) {
                cboMedio[i + 1] = new SelectItem(lista.get(i).getIdPublicidad(), lista.get(i).getDescripcion());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cboMedio;
    }

    public SelectItem[] getCboMedioDetalle() {
        try {
            HSMedioPublicidadCLDAO dao = (HSMedioPublicidadCLDAO) ServiceFinder.findBean("SpringHibernateMedioPublicidad");
            List<ClMedioPublicidadDet> lista = dao.listarMediopublicidadDeta_x_med(Integer.parseInt(this.getW_medio()));
            if (lista.size() != 0) {
                cboMedioDetalle = new SelectItem[lista.size() + 1];
                cboMedioDetalle[0] = new SelectItem("0", "[Seleccione]");
                for (int i = 0; i < (cboMedioDetalle.length - 1); i++) {
                    cboMedioDetalle[i + 1] = new SelectItem(lista.get(i).getIdPubDet(), lista.get(i).getPubDetDes());
                }

            } else {
                cboMedioDetalle = new SelectItem[1];
                cboMedioDetalle[0] = new SelectItem("0", "[Seleccione]");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cboMedioDetalle;
    }

    public void setCboDepartamento(SelectItem[] cboDepartamento) {
        this.cboDepartamento = cboDepartamento;
    }

    public void setCboProvincia(SelectItem[] cboProvincia) {
        this.cboProvincia = cboProvincia;
    }

    public void setCboDistrito(SelectItem[] cboDistrito) {
        this.cboDistrito = cboDistrito;
    }

    public void setCboMedio(SelectItem[] cboMedio) {
        this.cboMedio = cboMedio;
    }

    public void setCboMedioDetalle(SelectItem[] cboMedioDetalle) {
        this.cboMedioDetalle = cboMedioDetalle;
    }

    public void listarMedion(ActionEvent event) {
        try {
            // HSMedioPublicidadCLDAO dao = (HSMedioPublicidadCLDAO) ServiceFinder.findBean("SpringHibernateMedioPublicidad");

            /*HSPublicoAlumnoCLDAO dao= (HSPublicoAlumnoCLDAO) ServiceFinder.findBean("SpringHibernatePublicoAlumno");
            //List<ClMedioPublicidad> lista=dao.listarMedioPublicidad();
            List<Sp_listarPublicoAlumno> lista=dao.listarALumnosPorDato("rich");
            for(int i=0; i<lista.size(); i++){
            System.out.println("lista -> "+lista.get(i).getDatos());
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

   /* public BeanCLPublico getAlumno() {
        return alumno;
    }

    public void setAlumno(BeanCLPublico alumno) {
        this.alumno = alumno;
    }*/

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public SelectItem[] getCboPublicoAlumno() {
        return cboPublicoAlumno;
    }

    public void setCboPublicoAlumno(SelectItem[] cboPublicoAlumno) {
        this.cboPublicoAlumno = cboPublicoAlumno;
    }

    public List<bakbInformes> autocomplete(Object suggestion) {

        List<bakbInformes> lista = new ArrayList<bakbInformes>();
        try {

            String ref = (String) suggestion;
            if (ref.length() == 5) {
                HSPublicoAlumnoCLDAO dao = (HSPublicoAlumnoCLDAO) ServiceFinder.findBean("SpringHibernateDaoPublicoAlumno");
                System.out.println("la letra es - "+ref);
                List<Sp_listarPublicoAlumno> listaAlumnos = dao.listarALumnosPorDato(ref);
                for (int i = 0; i < listaAlumnos.size(); i++) {
                    bakbInformes inf = new bakbInformes();
                    String codigo = " ";
                    if (listaAlumnos.get(i).getAlu_cod() != null) {
                        codigo = listaAlumnos.get(i).getAlu_cod();
                    }
                    inf.setW_codigo(codigo);
                    inf.setW_datos(listaAlumnos.get(i).getDatos());
                    inf.setW_publico_id(listaAlumnos.get(i).getPublico_id());
                    lista.add(inf);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void buscarAlumno(ActionEvent event) {
        try {
//            System.out.println("el valor del id es -> " + this.getW_publico_id());
            //HSPublicoAlumnoCLDAO dao = (HSPublicoAlumnoCLDAO) ServiceFinder.findBean("SpringHibernatePublicoAlumno");
            HSAlumnoCLDAO dao = (HSAlumnoCLDAO) ServiceFinder.findBean("SpringHibernateDaoCLAlumno");
            //ClPublicoAlumno alu = dao.datoPublicoAlumno(this.getW_publico_id());
            ClAlumno alu=dao.buscarAlumnoPorAluId(this.getW_publico_id());
            System.out.println("el alumno es -> "+this.getW_publico_id());
            /*int alu_id = Integer.parseInt(((HtmlInputHidden) event.getComponent().findComponent("idAL")).getValue().toString());
            System.out.println("el alumno es hidden -> "+alu_id);*/

            this.getAlumnocl().setAluApmaterno(alu.getAluApmaterno());
            this.getAlumnocl().setAluAppaterno(alu.getAluAppaterno());
            this.getAlumnocl().setAluCelular(alu.getAluCelular());
            this.getAlumnocl().setAluCodigo(alu.getAluCodigo());
            this.getAlumnocl().setAluCp(alu.getAluCp());
            this.getAlumnocl().setAluDireccion(alu.getAluDireccion());
            //this.getAlumnocl().setAluDistrito(alu.getAluDistrito());
            this.getAlumnocl().setAluDni(alu.getAluDni());
            this.getAlumnocl().setAluDuplicado(alu.getAluDuplicado());
            this.getAlumnocl().setAluFechaNacimiento(alu.getAluFechaNacimiento());
            this.getAlumnocl().setAluFormaPago(alu.getAluFormaPago());
            this.getAlumnocl().setAluId(this.getW_publico_id());
            this.getAlumnocl().setAluMail(alu.getAluMail());
            this.getAlumnocl().setAluNombreFamiliar(alu.getAluNombreFamiliar());
            this.getAlumnocl().setAluNombres(alu.getAluNombres());
            this.getAlumnocl().setAluParentesco(alu.getAluParentesco());
            this.getAlumnocl().setAluPassword(alu.getAluPassword());
            this.getAlumnocl().setAluProcedencia(alu.getAluProcedencia());
            this.getAlumnocl().setAluSexo(alu.getAluSexo());
            this.getAlumnocl().setAluTelefono(alu.getAluTelefono());
            this.getAlumnocl().setAluTemporal(alu.getAluTemporal());
            this.getAlumnocl().setAluTipo(alu.getAluTipo());
            this.getAlumnocl().setAluUnidad(alu.getAluUnidad());
            try {
                //this.setAlu_distrito(alu.getAluDistrito());
                this.getAlumnocl().setAluDistrito(alu.getAluDistrito());
                this.setW_departamento((alu.getAluDistrito().substring(0, 2) + "0000"));
                this.setW_provincia(alu.getAluDistrito().substring(0, 4)+"00");
            }
            catch (Exception e) {
                this.getAlumnocl().setAluDistrito("000000");
                this.setW_departamento("00");
                this.setW_provincia("0000");
            }

            traerConsultasRealizadas(this.getW_publico_id());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guardarDatoPublico(ActionEvent event) {
        try {
           /* this.setOncomplete("");
            HSPublicoAlumnoCLDAO dao = (HSPublicoAlumnoCLDAO) ServiceFinder.findBean("SpringHibernatePublicoAlumno");
            ClPublicoAlumno publico = new ClPublicoAlumno();
            publico.setAluCod(this.getAlumno().getAluCod());
            //publico.setAlumnosId("");
            //publico.setDistritoId(0);
            publico.setEmail(this.getAlumno().getEmail());
            publico.setMaterno(this.getAlumno().getMaterno());
            publico.setPaterno(this.getAlumno().getPaterno());
            publico.setNombres(this.getAlumno().getNombres());
            //publico.setPublicoId(this.getAlumno());
            publico.setTelCasa(this.getAlumno().getTelCasa());
            publico.setTelCelular(this.getAlumno().getTelCelular());
            //publico.se
            if (this.getW_publico_id() == 0) {
                publico.setDistritoId(0);
                publico.setPublicoId(0);
                publico.setAlumnosId("");
                dao.agregarPublicoAlumno(publico);
                this.setOncomplete("javascript:alert('Registro Agregado correctamente')");
            } else {
                publico.setPublicoId(this.getAlumno().getPublicoId());
                publico.setDistritoId(this.getAlumno().getDistritoId());
                publico.setAlumnosId(this.getAlumno().getAlumnosId());
                dao.modificarPublicoAlumno(publico);
                this.setOncomplete("javascript:alert('Registro Modificado correctamente')");
            }*/
            this.setOncomplete("");
            HSAlumnoCLDAO dao = (HSAlumnoCLDAO) ServiceFinder.findBean("SpringHibernateDaoCLAlumno");
            ClAlumno alumno=new ClAlumno();

            alumno.setAluApmaterno(this.getAlumnocl().getAluApmaterno());
            alumno.setAluAppaterno(this.getAlumnocl().getAluAppaterno());
            alumno.setAluCelular(this.getAlumnocl().getAluCelular());
            alumno.setAluCodigo(this.getAlumnocl().getAluCodigo());
            alumno.setAluCp(this.getAlumnocl().getAluCp());
            alumno.setAluDireccion(this.getAlumnocl().getAluDireccion());
            alumno.setAluDistrito(this.getAlumnocl().getAluDistrito());
            alumno.setAluDni(this.getAlumnocl().getAluDni());
            alumno.setAluDuplicado(this.getAlumnocl().getAluDuplicado());
            alumno.setAluFechaNacimiento(this.getAlumnocl().getAluFechaNacimiento());
            alumno.setAluFormaPago(this.getAlumnocl().getAluFormaPago());
            alumno.setAluId(this.getAlumnocl().getAluId());
            alumno.setAluMail(this.getAlumnocl().getAluMail());
            alumno.setAluNombreFamiliar(this.getAlumnocl().getAluNombreFamiliar());
            alumno.setAluNombres(this.getAlumnocl().getAluNombres());
            alumno.setAluParentesco(this.getAlumnocl().getAluParentesco());
            alumno.setAluPassword(this.getAlumnocl().getAluPassword());
            alumno.setAluProcedencia(this.getAlumnocl().getAluProcedencia());
            alumno.setAluSexo(this.getAlumnocl().getAluSexo());
            alumno.setAluTelefono(this.getAlumnocl().getAluTelefono());
            alumno.setAluTemporal(this.getAlumnocl().getAluTemporal());
            alumno.setAluTipo(this.getAlumnocl().getAluTipo());
            alumno.setAluUnidad(this.getAlumnocl().getAluUnidad());
            if(this.getW_publico_id()==0){
                  alumno.setAluPassword(this.getAlumnocl().getAluCodigo());
                  if(this.getAlumnocl().getAluCodigo().length()<3){
                      alumno.setAluCodigo(dao.maximoCodigo());
                  }
                  dao.insertarAlumnocl(alumno);
                  this.setOncomplete("javascript:alert('Registro Agregado correctamente')");
            }
            else{
                dao.modificarAlumnocl(alumno);
                this.setOncomplete("javascript:alert('Registro Modificado correctamente')");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void traerHorario() {
        try {
            HSHorarioDAO dao = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoCLHorario");
            List<ClHoraria> listaH = dao.seleccionarHorario(Integer.parseInt(this.getW_seccion()));
            this.listaHoraria = new ArrayList<ClHoraria>();
            for (int i = 0; i < listaH.size(); i++) {
                ClHoraria h = new ClHoraria();

//                h.setHorId(listaH.get(i));
                h.setNomDia(h.traerNomDiaxCodigo(listaH.get(i).getHorDia()));
                h.setHorHini(listaH.get(i).getHorHini());
                h.setHorHfin(listaH.get(i).getHorHfin());
                this.listaHoraria.add(h);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void traerConsultasRealizadas(int pub_id) {
        HSPublicoConsultaCLDAO dao = (HSPublicoConsultaCLDAO) ServiceFinder.findBean("SpringHibernateDaoPublicoConsulta");
        List<ClPublicoConsulta> c = dao.listarPublicoConsulta(pub_id);
        this.listaConsultas = new ArrayList<BeanClConsulta>();
        for (int i = 0; i < c.size(); i++) {
            BeanClConsulta consTmp = new BeanClConsulta(0);
            consTmp.setConsultaId(c.get(i).getConsultaId());
            consTmp.setNum(i + 1);
            consTmp.setNomArea((c.get(i)).getTipoArea());
            consTmp.setFechReg((c.get(i)).getFechaRegistro().toString());
            consTmp.setUsuCod((c.get(i)).getUsuCod());
            consTmp.setNomProcedencia((c.get(i)).getProcedencia().compareTo("1") == 0 ? "presencial" : "telefonico");
            consTmp.setImg_matricular((c.get(i)).getMatriculado() == 1 ? "/Imagenes/actions/ok.png" : "/Imagenes/actions/matricular.gif");
            consTmp.setImg_observacion("/Imagenes/actions/viewmag.png");
            consTmp.setTexto_matric((c.get(i)).getMatriculado() == 1 ? "El alumno ya esta matriculado o pre-matriculado" : "Matricular");
            consTmp.setTexto_observacion((c.get(i)).getObsConsulta());

//            consulta.setConsultaId(((ClPublicoConsulta) c.get(i)).getConsultaId());


            listaConsultas.add(consTmp);
        }
    }

    public void abrirMatricula() {
    }

    public void mostrarObervacion(ActionEvent event) throws Exception {
        int consulta_id = Integer.parseInt(((UIParameter) event.getComponent().findComponent("consulta_id")).getValue().toString());

        HSPublicoConsultaCLDAO dao = (HSPublicoConsultaCLDAO) ServiceFinder.findBean("SpringHibernateDaoPublicoConsulta");
        this.setW_obs_consulta_editar(dao.traerConsultaXConsultaId(consulta_id).getObsConsulta());
        this.setW_consulta_id_edit(consulta_id);
        this.setOncomplete("");
        this.setOncomplete("Richfaces.showModalPanel('mpObservacion')");
    }

    public void guardarConsultaPublico(ActionEvent event) {
        try {
            if (this.getW_publico_id() != 0) {
                this.setOncomplete("");
                HSPublicoConsultaCLDAO dao = (HSPublicoConsultaCLDAO) ServiceFinder.findBean("SpringHibernateDaoPublicoConsulta");
                ClPublicoConsulta nuevaConsulta = new ClPublicoConsulta();

                ClAlumno alu=new ClAlumno();
                alu.setAluId(this.getAlumnocl().getAluId());
                nuevaConsulta.setClAlumno(alu);
                nuevaConsulta.setConsultaId(0);
                nuevaConsulta.setArea("1");
                nuevaConsulta.setTipoArea(this.getW_modulo());
                nuevaConsulta.setDiasEstudio(0);
                nuevaConsulta.setHorarios(this.getW_horario());
                nuevaConsulta.setPublicidad(Integer.parseInt(this.getW_medio()));
                nuevaConsulta.setPubliDet(Integer.parseInt(this.getW_medio_det()));
                nuevaConsulta.setDetalleMedio(this.getW_obs_medio());
                nuevaConsulta.setFechaContacto(this.getW_fecha_contacto());
                nuevaConsulta.setHora("0");
                nuevaConsulta.setConsultado(0);
                nuevaConsulta.setMatriculado(0);
              //  nuevaConsulta.setUsuCod("prueba_sistemas");
                nuevaConsulta.setFechaRegistro(new Date());
                nuevaConsulta.setHoraRegistro(new Date());
                nuevaConsulta.setObsConsulta(this.getW_obs_consulta());
                nuevaConsulta.setProcedencia("1");
                nuevaConsulta.setSede(1);


                /*Set<ClPublicoConsultaDetalle> listObs = new LinkedHashSet<ClPublicoConsultaDetalle>();
                ClPublicoConsultaDetalle nuevaConsDet = new ClPublicoConsultaDetalle();
                nuevaConsDet.setConsultaDetalleId(0);
                nuevaConsDet.setObservacion(this.getW_obs_consulta());
                nuevaConsDet.setFecha(new Date());
                nuevaConsDet.setClPublicoConsulta(nuevaConsulta);
                listObs.add(nuevaConsDet);
                nuevaConsulta.setClPublicoConsultaDetalles(listObs);
                 */
                dao.agregarPublicoConsulta(nuevaConsulta);

                //Se trae el Id de la ultima consulta que se acaba de guardar
                List listaUltimaConsulta = dao.traerUltimaConsultaXPubId(this.getW_publico_id());
                Integer c = (Integer) listaUltimaConsulta.get(0);
                nuevaConsulta.setConsultaId(c);
               // ClPublicoConsultaDetalle conDet = new ClPublicoConsultaDetalle(0, nuevaConsulta, this.getW_obs_consulta(), new Date());
                //this.guardarPublicoConsultaDetalle(conDet);

                this.setOncomplete("javascript:alert('Consulta Agregado correctamente')");
                traerConsultasRealizadas(this.getW_publico_id());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guardarPublicoConsultaDetalle(ClPublicoConsultaDetalle consultaDetalle) {
        HSPublicoConsultaDetalleCLDAO dao = (HSPublicoConsultaDetalleCLDAO) ServiceFinder.findBean("SpringHibernateDaoPublicoConsultaDetalle");
        dao.guardarConsultaDetalle(consultaDetalle);
    }

    public void actualizarObsConsulta(ActionEvent event) {
        int consulta_id_edit = this.getW_consulta_id_edit();//Integer.parseInt(((UIParameter) event.getComponent().findComponent("consulta_id_edit")).getValue().toString());
        String nuevaObs = this.getW_obs_consulta_editar();//((UIParameter) event.getComponent().findComponent("txtaEdicionConsulta")).getValue().toString();

        HSPublicoConsultaCLDAO dao = (HSPublicoConsultaCLDAO) ServiceFinder.findBean("SpringHibernateDaoPublicoConsulta");
        dao.modificarPublicoConsulta(consulta_id_edit,nuevaObs);
       // guardarPublicoConsultaDetalle(new ClPublicoConsultaDetalle(0, new ClPublicoConsulta(consulta_id_edit), nuevaObs, new Date()));
        this.setOncomplete("javascript:alert('Consulta actualizada correctamente');Richfaces.hideModalPanel('mpObservacion')");
        traerConsultasRealizadas(this.getW_publico_id());

    }

    public void abrirMatricula(ActionEvent event){

        int consulta_id = Integer.parseInt(((UIParameter) event.getComponent().findComponent("consulta_id")).getValue().toString());
        try {

            HSPublicoConsultaCLDAO dao = (HSPublicoConsultaCLDAO) ServiceFinder.findBean("SpringHibernateDaoPublicoConsulta");
            ClPublicoConsulta consulta=dao.traerConsultaXConsultaId(consulta_id);
            HSArbolAperturadoClDAO daoT = CommonDAO.getClArbolAperturadoClDAO();
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
