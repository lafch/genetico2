/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.academica.managedBeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.academica.hibernateSpringDao.HSAlumnoDAO;
import net.uch.academica.hibernateSpringDao.HSApoderadoDAO;
import net.uch.academica.managedBeans.beans.BeanAuxiliar;
import net.uch.academica.managedBeans.beans.beanApoderado;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcAlumno;
import net.uch.mapping.AcApoderado;
import net.uch.mapping.AcApoderadoAlumno;
import net.uch.mapping.TbCatalogo;
import net.uch.mapping.TbDistrito;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSUbigeoDAO;

/**
 *
 * @author richard
 */
public class mbApoderado {

    private beanApoderado apoderado = new beanApoderado();
    private String oncomplete;
    private List<beanApoderado> listaApoderado = new ArrayList<beanApoderado>();
    private String w_apo_dni;
    private String w_apo_nombre;
    private String w_apo_paterno;
    private String w_apo_materno;

    /*distrito*/
    private SelectItem[] cboDepartamentos_n;
    private SelectItem[] cboProvincia_n;
    private SelectItem[] cboDistrito_n;
    String w_departamento = "000000";
    String w_provincia = "000000";
    /**/
    /*alumnos*/
    private List<BeanAuxiliar> listaAlumno = new ArrayList<BeanAuxiliar>();
    private List<BeanAuxiliar> listaAlumnoTem = new ArrayList<BeanAuxiliar>();
    private String datoAlumno;

    /**/
    private int w_apo_id;

    public int getW_apo_id() {
        return w_apo_id;
    }

    public void setW_apo_id(int w_apo_id) {
        this.w_apo_id = w_apo_id;
    }

    public String getDatoAlumno() {
        return datoAlumno;
    }

    public void setDatoAlumno(String datoAlumno) {
        this.datoAlumno = datoAlumno;
    }

    public List<BeanAuxiliar> getListaAlumnoTem() {
        return listaAlumnoTem;
    }

    public void setListaAlumnoTem(List<BeanAuxiliar> listaAlumnoTem) {
        this.listaAlumnoTem = listaAlumnoTem;
    }

    public List<BeanAuxiliar> getListaAlumno() {
        return listaAlumno;
    }

    public void setListaAlumno(List<BeanAuxiliar> listaAlumno) {
        this.listaAlumno = listaAlumno;
    }
    private SelectItem[] cboTipoFamiliar;

    public SelectItem[] getCboTipoFamiliar() {
        try {
            HSCatalogoDAO dao = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List<TbCatalogo> lista = dao.seleccionarGrupo("040");
            cboTipoFamiliar = new SelectItem[lista.size() + 1];
            cboTipoFamiliar[0] = new SelectItem("040000", "[Seleccione]");
            for (int i = 0; i < (cboTipoFamiliar.length - 1); i++) {
                cboTipoFamiliar[i + 1] = new SelectItem(lista.get(i).getCatCodigoGrupo() + lista.get(i).getCatCodigoElemento(), lista.get(i).getCatDescripcionElemento());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cboTipoFamiliar;
    }

    public void setCboTipoFamiliar(SelectItem[] cboTipoFamiliar) {
        this.cboTipoFamiliar = cboTipoFamiliar;
    }

    public SelectItem[] getCboDepartamentos_n() {
        try {
            HSUbigeoDAO dao = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
            List lista = dao.seleccionarDepartamento();
            cboDepartamentos_n = new SelectItem[lista.size() + 1];
            cboDepartamentos_n[0] = new SelectItem("000000", "[Seleccione]");
            for (int i = 0; i < (cboDepartamentos_n.length - 1); i++) {
                cboDepartamentos_n[i + 1] = new SelectItem(((TbDistrito) lista.get(i)).getId(), ((TbDistrito) lista.get(i)).getDisDes());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cboDepartamentos_n;
    }

    public void setCboDepartamentos_n(SelectItem[] cboDepartamentos_n) {
        this.cboDepartamentos_n = cboDepartamentos_n;
    }

    public SelectItem[] getCboDistrito_n() {
        try {
            HSUbigeoDAO dao = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
            List<TbDistrito> lista = dao.seleccionarDistrito(this.getW_departamento(), this.getW_provincia());
            if (lista.size() != 0) {
                cboDistrito_n = new SelectItem[lista.size() + 1];
                cboDistrito_n[0] = new SelectItem("000000", "[Seleccione]");
                for (int i = 0; i < (cboDistrito_n.length - 1); i++) {
                    cboDistrito_n[i + 1] = new SelectItem(lista.get(i).getId(), lista.get(i).getDisDes());
                }
            } else {
                cboDistrito_n = new SelectItem[1];
                cboDistrito_n[0] = new SelectItem("000000", "[Seleccione]");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cboDistrito_n;
    }

    public void setCboDistrito_n(SelectItem[] cboDistrito_n) {
        this.cboDistrito_n = cboDistrito_n;
    }

    public SelectItem[] getCboProvincia_n() {
        try {
            HSUbigeoDAO dao = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
            List<TbDistrito> lista = dao.seleccionarProvincia(this.getW_departamento());
            if (lista.size() != 0) {
                cboProvincia_n = new SelectItem[lista.size() + 1];
                cboProvincia_n[0] = new SelectItem("000000", "[Seleccione]");
                for (int i = 0; i < (cboProvincia_n.length - 1); i++) {
                    cboProvincia_n[i + 1] = new SelectItem(lista.get(i).getId(), lista.get(i).getDisDes());
                }
                System.out.println("esoty aqui");
            } else {
                System.out.println("pase aqui");
                cboProvincia_n = new SelectItem[1];
                cboProvincia_n[0] = new SelectItem("000000", "[Seleccione]");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cboProvincia_n;
    }

    public void setCboProvincia_n(SelectItem[] cboProvincia_n) {
        this.cboProvincia_n = cboProvincia_n;
    }

    public String getW_departamento() {
        return w_departamento;
    }

    public void setW_departamento(String w_departamento) {
        this.w_departamento = w_departamento;
    }

    public String getW_provincia() {
        return w_provincia;
    }

    public void setW_provincia(String w_provincia) {
        this.w_provincia = w_provincia;
    }

    public String getW_apo_dni() {
        return w_apo_dni;
    }

    public void setW_apo_dni(String w_apo_dni) {
        this.w_apo_dni = w_apo_dni;
    }

    public String getW_apo_materno() {
        return w_apo_materno;
    }

    public void setW_apo_materno(String w_apo_materno) {
        this.w_apo_materno = w_apo_materno;
    }

    public String getW_apo_nombre() {
        return w_apo_nombre;
    }

    public void setW_apo_nombre(String w_apo_nombre) {
        this.w_apo_nombre = w_apo_nombre;
    }

    public String getW_apo_paterno() {
        return w_apo_paterno;
    }

    public void setW_apo_paterno(String w_apo_paterno) {
        this.w_apo_paterno = w_apo_paterno;
    }

    public List<beanApoderado> getListaApoderado() {
        return listaApoderado;
    }

    public void setListaApoderado(List<beanApoderado> listaApoderado) {
        this.listaApoderado = listaApoderado;
    }

    public beanApoderado getApoderado() {
        return apoderado;
    }

    public void setApoderado(beanApoderado apoderado) {
        this.apoderado = apoderado;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public void buscarTodos(ActionEvent event) {
        HSApoderadoDAO dao = (HSApoderadoDAO) ServiceFinder.findBean("SpringHibernateDaoApoderado");
        HSCatalogoDAO daoCata = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        //System.out.println("datos -> " + this.getW_apo_dni() + " - " + this.getW_apo_paterno() + " - " + this.getW_apo_materno() + " - " + this.getW_apo_nombre());
        if (validarCampos()) {
            List<AcApoderado> lista = dao.listarApoderados(this.w_apo_dni, this.getW_apo_paterno(), this.getW_apo_materno(), this.getW_apo_nombre());
            List<beanApoderado> listaA = new ArrayList<beanApoderado>();
            //this.setListaApoderado(lista);
            for (int i = 0; i < lista.size(); i++) {
                beanApoderado apo = new beanApoderado();
                apo.setApoActivo(lista.get(i).getApoActivo());
                apo.setApoApmaterno(lista.get(i).getApoApmaterno());
                apo.setApoAppaterno(lista.get(i).getApoAppaterno());
                apo.setApoCelular(lista.get(i).getApoCelular());
                apo.setApoDireccion(lista.get(i).getApoDireccion());
                apo.setApoDistrito(lista.get(i).getApoDistrito());
                apo.setApoDni(lista.get(i).getApoDni());
                apo.setApoId(lista.get(i).getApoId());
                apo.setApoNombres(lista.get(i).getApoNombres());
                apo.setApoPassword(lista.get(i).getApoPassword());
                apo.setApoTelefono(lista.get(i).getApoTelefono());
                String tipo_familiar = daoCata.seleccionarDescripcion(lista.get(i).getTipoFamiliar());
                apo.setTipoFamiliar(tipo_familiar);
                listaA.add(apo);
            }
            this.setListaApoderado(listaA);
        }
    }

    public void seleccionarApoderado(ActionEvent event) {
        try {
            HSApoderadoDAO dao = (HSApoderadoDAO) ServiceFinder.findBean("SpringHibernateDaoApoderado");
            int apo_id = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_apo_id")).getValue().toString());
            AcApoderado apo = dao.datoApoderado(apo_id);

            if (apo != null) {
                this.getApoderado().setApoActivo(apo.getApoActivo());
                this.getApoderado().setApoApmaterno(apo.getApoApmaterno());
                this.getApoderado().setApoAppaterno(apo.getApoAppaterno());
                this.getApoderado().setApoCelular(apo.getApoCelular());
                this.getApoderado().setApoDireccion(apo.getApoDireccion());
                this.getApoderado().setApoDistrito(apo.getApoDistrito());
                this.getApoderado().setApoDni(apo.getApoDni());
                this.getApoderado().setApoId(apo.getApoId());
                this.getApoderado().setApoNombres(apo.getApoNombres());
                this.getApoderado().setApoPassword(apo.getApoPassword());
                this.getApoderado().setApoTelefono(apo.getApoTelefono());
                this.getApoderado().setTipoFamiliar(apo.getTipoFamiliar());

                this.setW_departamento(this.getApoderado().getApoDistrito().toString().substring(0, 2) + "0000");
                this.setW_provincia(this.getApoderado().getApoDistrito().toString().substring(0, 4) + "00");
                if (this.getApoderado().getApoDistrito().equals("000000")) {
                    this.setW_departamento("000000");
                    this.setW_provincia("000000");
                    //this.getDocente().setDocNacimiento("000000");
                }
            }

            this.setOncomplete("javascript:Richfaces.showModalPanel('mpApoderado');");
        } catch (Exception e) {
            System.out.println("error al seleccionar apoderado -> " + e);
            e.printStackTrace();
        }
    }

    public void guardarDatos(ActionEvent event) {
        try {
            this.setOncomplete("");

            HSApoderadoDAO dao = (HSApoderadoDAO) ServiceFinder.findBean("SpringHibernateDaoApoderado");
            AcApoderado apo = new AcApoderado();
            apo.setApoActivo(this.getApoderado().getApoActivo());
            apo.setApoApmaterno(this.getApoderado().getApoApmaterno());
            apo.setApoAppaterno(this.getApoderado().getApoAppaterno());
            apo.setApoCelular(this.getApoderado().getApoCelular());
            apo.setApoDireccion(this.getApoderado().getApoDireccion());
            apo.setApoDistrito(this.getApoderado().getApoDistrito());
            apo.setApoDni(this.getApoderado().getApoDni());
            apo.setApoId(this.getApoderado().getApoId());
            apo.setApoNombres(this.getApoderado().getApoNombres());
            apo.setApoPassword(this.getApoderado().getApoPassword());
            apo.setApoTelefono(this.getApoderado().getApoTelefono());
            apo.setTipoFamiliar(this.getApoderado().getTipoFamiliar());
            apo.setApoActivo("1");
            if (validadApoderado(apo)) {
                if (this.getApoderado().getApoId() > 0) {
                    dao.modificarApoderado(apo);
                    this.setOncomplete("javascript:alert('se modifica Correctamente');javascript:Richfaces.hideModalPanel('mpApoderado');");
                } else {
                    String mensaje="";
                    apo.setApoPassword(this.getApoderado().getApoDni());
                    if(dao.datoApoderadoxDni(this.getApoderado().getApoDni())==null){
                        dao.agregarApoderado(apo);
                        mensaje="javascript:alert('se agrego Correctamente');javascript:Richfaces.hideModalPanel('mpApoderado');";
                    }
                    else{
                        mensaje="javascript:alert('El nro de DNI ya esta registrado')";
                    }
                    this.setOncomplete(mensaje);
                }
            }

        } catch (Exception e) {
            System.out.println("error al agregar");
            e.printStackTrace();

        }
    }

    public void nuevoApoderado(ActionEvent event) {
        limpiar();
        this.getApoderado().setApoId(0);
        this.setOncomplete("javascript:Richfaces.showModalPanel('mpApoderado');");
    }

    public void eliminarSeleccion(ActionEvent event) {
        try {
            int apo_id = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_apo_id")).getValue().toString());
            HSApoderadoDAO dao = (HSApoderadoDAO) ServiceFinder.findBean("SpringHibernateDaoApoderado");
            dao.eliminarApoderado(apo_id);
            buscarTodos(event);
        } catch (Exception e) {
            System.out.println("error al eliminar");
            e.printStackTrace();
        }

    }

    public void buscarAlumnosporApoderado(ActionEvent event) {
        this.setDatoAlumno("");
        this.setW_apo_id(0);
        listaAlumnoTem = new ArrayList<BeanAuxiliar>();
        try {
            this.setOncomplete("");
            listaAlumno = new ArrayList<BeanAuxiliar>();
            HSApoderadoDAO dao = (HSApoderadoDAO) ServiceFinder.findBean("SpringHibernateDaoApoderado");
            int apo_id = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_apo_id")).getValue().toString());
            this.setW_apo_id(apo_id);
            List<AcApoderadoAlumno> lista = dao.listarAlumnosCargo(apo_id);
            List<BeanAuxiliar> listaT = new ArrayList<BeanAuxiliar>();
            for (int i = 0; i < lista.size(); i++) {
                BeanAuxiliar alu = new BeanAuxiliar();
                alu.setAux_codigo(lista.get(i).getAcAlumno().getAluCodigo());
                alu.setAux_id(lista.get(i).getAcAlumno().getId());
                alu.setAux_paterno(lista.get(i).getAcAlumno().getAluAppaterno());
                alu.setAux_materno(lista.get(i).getAcAlumno().getAluApmaterno());
                alu.setAux_nombres(lista.get(i).getAcAlumno().getAluNombres());
                alu.setAux_id_refe(lista.get(i).getAcAlumno().getId());
                //System.out.println("valor es -> "+lista.get(i).getAcAlumno().getAluAppaterno());
                listaT.add(alu);
            }
            //lista.get(0).getAcAlumno().getId();
            this.setListaAlumno(listaT);

            this.setOncomplete("javascript:Richfaces.showModalPanel('mpAlumno');");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buscarAlumnoPorDato(ActionEvent event) {
        this.setOncomplete("");
        try {
            if (validardato()) {
                HSAlumnoDAO dao = (HSAlumnoDAO) ServiceFinder.findBean("SpringHibernateDaoAlumno");
                List lista = dao.listarAlumnosporDato(this.getDatoAlumno());
                List<BeanAuxiliar> listaT = new ArrayList<BeanAuxiliar>();
                for (int i = 0; i < lista.size(); i++) {
                    //AcAlumno alumno=new AcAlumno();
                    Object alu[] = (Object[]) lista.get(i);
                    BeanAuxiliar alum = new BeanAuxiliar();
                    alum.setAux_codigo(alu[1].toString());
                    alum.setAux_id(Integer.parseInt(alu[0].toString()));
                    alum.setAux_paterno(alu[2].toString());
                    alum.setAux_materno(alu[3].toString());
                    alum.setAux_nombres(alu[4].toString());
                    alum.setAux_id_refe(Integer.parseInt(alu[0].toString()));
                    listaT.add(alum);
                }
                setListaAlumnoTem(listaT);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    int contador = 0;

    public void pasarDatos(ActionEvent event) {
        this.setOncomplete("");
        int v_alu_id = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_aux_id")).getValue().toString());
        String v_alu_paterno = ((UIParameter) event.getComponent().findComponent("p_aux_paterno")).getValue().toString();
        String v_alu_materno = ((UIParameter) event.getComponent().findComponent("p_aux_materno")).getValue().toString();
        String v_alu_nombres = ((UIParameter) event.getComponent().findComponent("p_aux_nombre")).getValue().toString();
        String v_alu_codigo = ((UIParameter) event.getComponent().findComponent("p_aux_codigo")).getValue().toString();
        int v_indice = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_indice")).getValue().toString());
        BeanAuxiliar aux = new BeanAuxiliar();
        aux.setAux_id(contador);
        aux.setAux_id_refe(v_alu_id);
        aux.setAux_materno(v_alu_materno);
        aux.setAux_nombres(v_alu_nombres);
        aux.setAux_paterno(v_alu_paterno);
        aux.setAux_codigo(v_alu_codigo);
        this.getListaAlumnoTem().remove(v_indice);
        this.getListaAlumno().add(aux);

        contador--;
    }

    public void quitarDatos(ActionEvent event) {
        this.setOncomplete("");
        int v_alu_id = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_aux_idbak")).getValue().toString());
        for (int i = 0; i < this.getListaAlumno().size(); i++) {
            if (v_alu_id == this.getListaAlumno().get(i).getAux_id()) {
                if(v_alu_id<1){
                    this.getListaAlumno().remove(i);
                }
                else{
                    this.setOncomplete("javascript:alert('El alumno ya esta registrado')");
                }
            }
        }
    }

    public void guardarAlumnosApo(ActionEvent event) {
        this.setOncomplete("");
        try {
            HSApoderadoDAO dao = (HSApoderadoDAO) ServiceFinder.findBean("SpringHibernateDaoApoderado");
            for (int i = 0; i < this.getListaAlumno().size(); i++) {

                int alu_id = this.getListaAlumno().get(i).getAux_id();
                if (alu_id < 1) {
                    AcAlumno alu = new AcAlumno();
                    alu.setId(this.getListaAlumno().get(i).getAux_id_refe());
                    AcApoderado apo = new AcApoderado();
                    System.out.println("apoderado -> " + this.getW_apo_id());
                    apo.setApoId(this.getW_apo_id());
                    AcApoderadoAlumno apoAlu = new AcApoderadoAlumno();
                    apoAlu.setAcAlumno(alu);
                    apoAlu.setAcApoderado(apo);
                    apoAlu.setApoaluActivo("1");
                    apoAlu.setApoaluId(0);
                    dao.agregarApoderadoAlumno(apoAlu);
                }
            }
            this.setOncomplete("javascript:alert('Registro guardado Correctamente');javascript:Richfaces.hideModalPanel('mpAlumno');");
            this.setW_apo_id(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void limpiar() {
        this.setOncomplete("");
        apoderado = new beanApoderado();
        this.setW_departamento("000000");
        this.setW_provincia("000000");
    }

    public boolean validarCampos() {
        if (this.getW_apo_dni().length() > 5 || this.getW_apo_paterno().length() >= 3
                || this.getW_apo_materno().length() >= 3 || this.getW_apo_nombre().length() >= 3) {
            oncomplete = "";
            return true;
        } else {
            this.setOncomplete("javascript:alert('Ingrese un dato')");
            return false;
        }

    }

    public boolean validardato() {
        if (this.getDatoAlumno().length() > 3) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validadApoderado(AcApoderado apoderado) {
        if (apoderado.getApoAppaterno().length() < 3) {
            this.setOncomplete("");
            this.setOncomplete("javascript:alert('Ingrese Apellido Paterno')");
            return false;
        }

        if (apoderado.getApoApmaterno().length() < 3) {
            this.setOncomplete("");
            this.setOncomplete("javascript:alert('Ingrese Apellido materno')");
            return false;
        }

        if (apoderado.getApoNombres().length() < 3) {
            this.setOncomplete("");
            this.setOncomplete("javascript:alert('Ingrese Nombres')");
            return false;
        }
        if (apoderado.getApoDni().length() < 3) {
            this.setOncomplete("");
            this.setOncomplete("javascript:alert('Ingrese Nro de DNI')");
            return false;
        }

        return true;
    }
}