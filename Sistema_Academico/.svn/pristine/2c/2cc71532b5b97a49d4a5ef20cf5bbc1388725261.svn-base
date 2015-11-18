/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.cursoLibre.managedBeans;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSConsultaPublicoDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSPlantillaHorarioCLDAO;
import net.uch.cursoLibre.managedBeans.beans.BeansRptFechaContacto;
import net.uch.cursoLibre.managedBeans.beans.MetodosCL;
import net.uch.cursoLibre.utilsAcademico.Metodos;
import net.uch.mapping.ClConsultaPublico;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSUbigeoDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSUsuarioDAO;
import net.uch.utilAdministrativo.MetodosAdm;


/**
 *
 * @author Toshiba
 */
public class bFechaRegistro {
    private SelectItem[] cboDistrito;
    private SelectItem[] cboUsuario;
    private SelectItem[] cboMatriculado;
    private SelectItem[] cboProcedencia;
    private SelectItem[] cboArea;
    private SelectItem[] cboLocal;
    private String w_distrito;
    private int w_usu_id;
    private int w_mat_id=3;
    private String w_pro_id="000000";
    private int w_are_id;
    private int loc_id;
    private Date w_fecha_ini= new Date();
    private Date w_fecha_fin= new Date();

    private List<BeansRptFechaContacto> listaFechaRegistro= new ArrayList<BeansRptFechaContacto>();

    public List<BeansRptFechaContacto> getListaFechaRegistro() {
        return listaFechaRegistro;
    }

    public void setListaFechaRegistro(List<BeansRptFechaContacto> listaFechaRegistro) {
        this.listaFechaRegistro = listaFechaRegistro;
    }

    

    public SelectItem[] getCboArea() {
        Metodos metodo= new Metodos();
        this.setCboArea(metodo.getCboArea());
        return cboArea;
    }

    public void setCboArea(SelectItem[] cboArea) {
        this.cboArea = cboArea;
    }

    public SelectItem[] getCboDistrito() {
        MetodosCL metodo=new MetodosCL();
        this.setCboDistrito(metodo.listarDistrito("150000", "150100"));
        return cboDistrito;
    }

    public void setCboDistrito(SelectItem[] cboDistrito) {
        this.cboDistrito = cboDistrito;
    }

    public SelectItem[] getCboMatriculado() {
        this.cboMatriculado= new SelectItem[3];
        this.cboMatriculado[0]= new SelectItem(3, "[ TODOS ]");
        this.cboMatriculado[1]= new SelectItem(0, "[ NO MATRICULADO ]");//
        this.cboMatriculado[2]= new SelectItem(2, "[ MATRICULADO ]");
        return cboMatriculado;
    }

    public void setCboMatriculado(SelectItem[] cboMatriculado) {
        this.cboMatriculado = cboMatriculado;
    }

    public SelectItem[] getCboProcedencia() {
        Metodos metodo= new Metodos();
        this.setCboProcedencia(metodo.listarCatalogoGrupo("064", "[ TODOS ]"));
        return cboProcedencia;
    }

    public void setCboProcedencia(SelectItem[] cboProcedencia) {
        this.cboProcedencia = cboProcedencia;
    }

    public SelectItem[] getCboUsuario() {
        MetodosAdm metodo=new MetodosAdm();
        this.setCboUsuario(metodo.getCboUsuario("[ TODOS ]"));
        return cboUsuario;
    }

    public void setCboUsuario(SelectItem[] cboUsuario) {
        this.cboUsuario = cboUsuario;
    }

    public SelectItem[] getCboLocal() throws SQLException {
        MetodosAdm metodo= new MetodosAdm();
        this.setCboLocal(metodo.listarSedes("[ TODOS ]"));
        return cboLocal;
    }

    public void setCboLocal(SelectItem[] cboLocal) {
        this.cboLocal = cboLocal;
    }

    public int getLoc_id() {
        return loc_id;
    }

    public void setLoc_id(int loc_id) {
        this.loc_id = loc_id;
    }

    public int getW_are_id() {
        return w_are_id;
    }

    public void setW_are_id(int w_are_id) {
        this.w_are_id = w_are_id;
    }

    public String getW_distrito() {
        return w_distrito;
    }

    public void setW_distrito(String w_distrito) {
        this.w_distrito = w_distrito;
    }

    public Date getW_fecha_fin() {
        return w_fecha_fin;
    }

    public void setW_fecha_fin(Date w_fecha_fin) {
        this.w_fecha_fin = w_fecha_fin;
    }

    public Date getW_fecha_ini() {
        return w_fecha_ini;
    }

    public void setW_fecha_ini(Date w_fecha_ini) {
        this.w_fecha_ini = w_fecha_ini;
    }

    public int getW_mat_id() {
        return w_mat_id;
    }

    public void setW_mat_id(int w_mat_id) {
        this.w_mat_id = w_mat_id;
    }

    public String getW_pro_id() {
        return w_pro_id;
    }

    public void setW_pro_id(String w_pro_id) {
        this.w_pro_id = w_pro_id;
    }

    public int getW_usu_id() {
        return w_usu_id;
    }

    public void setW_usu_id(int w_usu_id) {
        this.w_usu_id = w_usu_id;
    }

    public void buscarConsultaPublico(ActionEvent event) throws ParseException, Exception{
        this.setListaFechaRegistro(new ArrayList<BeansRptFechaContacto>());
        HSConsultaPublicoDAO dao=(HSConsultaPublicoDAO) ServiceFinder.findBean("SpringHibernateDaoConsultaPublico");
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String TfechaI = format.format(this.w_fecha_ini);
            String TfechaF = format.format(this.w_fecha_fin);
            Date w_fechaI = sdf.parse(TfechaI);
            Date w_fechaF = sdf.parse(TfechaF);
        List<ClConsultaPublico> listaCon=dao.listarPublicoConsultaFechaRegistro(this.loc_id, this.w_are_id, this.w_mat_id, this.w_usu_id,
                                                                                this.w_distrito, this.w_pro_id, w_fechaI, w_fechaF);
        HSCatalogoDAO daoC = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        HSPlantillaHorarioCLDAO daoP=(HSPlantillaHorarioCLDAO) ServiceFinder.findBean("SpringHibernateDaoPlantillaHorario");
        HSUbigeoDAO ubi = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
        HSUsuarioDAO daoUsu=(HSUsuarioDAO)ServiceFinder.findBean("SpringHibernateDaoUsuario");
        System.out.println("la cantidad obtenida es -> "+listaCon.size());
        for(int i=0; i<listaCon.size(); i++){
            BeansRptFechaContacto contacto=new BeansRptFechaContacto();
            contacto.setNombreAlumno(listaCon.get(i).getClAlumno().getAluAppaterno()+" "+listaCon.get(i).getClAlumno().getAluApmaterno()+" "+listaCon.get(i).getClAlumno().getAluNombres());
            //contacto.setArea(listaCon.get(i).getClModulo().getClArea().getAreDescripcion());
            contacto.setHorario((daoP.seleccionarPlantilla(listaCon.get(i).getPlaId())).getPlaDescripcion());
            contacto.setMatriculado(listaCon.get(i).getConEstadoMatricula().equals("2") ? "SI" : "NO");
            contacto.setConsulta_id(listaCon.get(i).getConId());
            contacto.setFechContac(listaCon.get(i).getConFechaContacto());
            contacto.setProcedencia(daoC.seleccionarDescripcion(listaCon.get(i).getConProcedencia()));
            contacto.setTlfCelular(listaCon.get(i).getClAlumno().getAluCelular());
            contacto.setTlfFijo(listaCon.get(i).getClAlumno().getAluTelefono());
            contacto.setObservacion(listaCon.get(i).getConObservacionRegistro());
            String distrito=ubi.seleccionarDescripcion(listaCon.get(i).getClAlumno().getAluDistrito());
            contacto.setAlu_distrito("No definido");
            if(distrito.length()>0){
                contacto.setAlu_distrito(distrito);
            }
            contacto.setMed_medio(dao.seleccionarMedioPublicidad(listaCon.get(i).getDetmedId()).getPubDetDes());
            contacto.setMed_medio_detalle(dao.seleccionarMedioPublicidad(listaCon.get(i).getDetmedId()).getClMedioPublicidad().getDescripcion());
            contacto.setFechReg(listaCon.get(i).getConFechaRegistro());
            contacto.setUsu_usuario(daoUsu.verUsuario(listaCon.get(i).getUsuId()));
            contacto.setNumOrden(i+1);
            //contacto.setW_are_id(listaCon.get(i).getClModulo().getClArea().getAreId());
            this.getListaFechaRegistro().add(contacto);
        }
    }

    public void seleccionarporArea(ActionEvent event) throws ParseException, Exception{
        int p_are_id = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_are_id")).getValue().toString());
        this.setW_are_id(p_are_id);
        buscarConsultaPublico(event);
    }


}