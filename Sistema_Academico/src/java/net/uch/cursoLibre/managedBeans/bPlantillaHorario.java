/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.cursoLibre.managedBeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSPlantillaHorarioCLDAO;
import net.uch.cursoLibre.managedBeans.beans.MetodosCL;
import net.uch.mapping.ClPlantillaHorario;
import net.uch.mapping.ClPlantillaHorarioDet;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;

/**
 *
 * @author richard
 */
public class bPlantillaHorario {
    private String w_pla_descripcion;
    private String w_pla_id;
    private String v_descripcion_turno="";

    private ClPlantillaHorario plantilla= new ClPlantillaHorario();
    private List<ClPlantillaHorario> listaPlantilla= new ArrayList<ClPlantillaHorario>();
    private List<ClPlantillaHorarioDet> listaDetalle= new ArrayList<ClPlantillaHorarioDet>();
    private List<bPlantillaHorario> listaPlantillaHora = new ArrayList<bPlantillaHorario>();
    private String oncomplete;

    private SelectItem[] cboTurno;

    public SelectItem[] getCboTurno() {
        MetodosCL metodo=new MetodosCL();
        this.setCboTurno(metodo.listarCatalogoGrupo("065"));
        return cboTurno;
    }

    public void setCboTurno(SelectItem[] cboTurno) {
        this.cboTurno = cboTurno;
    }

    public List<bPlantillaHorario> getListaPlantillaHora() {
        return listaPlantillaHora;
    }

    public void setListaPlantillaHora(List<bPlantillaHorario> listaPlantillaHora) {
        this.listaPlantillaHora = listaPlantillaHora;
    }

    

    public String getV_descripcion_turno() {
        return v_descripcion_turno;
    }

    public void setV_descripcion_turno(String v_descripcion_turno) {
        this.v_descripcion_turno = v_descripcion_turno;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public List<ClPlantillaHorarioDet> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(List<ClPlantillaHorarioDet> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }
    
    public List<ClPlantillaHorario> getListaPlantilla() {
        return listaPlantilla;
    }

    public void setListaPlantilla(List<ClPlantillaHorario> listaPlantilla) {
        this.listaPlantilla = listaPlantilla;
    }

    public ClPlantillaHorario getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(ClPlantillaHorario plantilla) {
        this.plantilla = plantilla;
    }

    public String getW_pla_descripcion() {
        return w_pla_descripcion;
    }

    public void setW_pla_descripcion(String w_pla_descripcion) {
        this.w_pla_descripcion = w_pla_descripcion;
    }

    public String getW_pla_id() {
        return w_pla_id;
    }

    public void setW_pla_id(String w_pla_id) {
        this.w_pla_id = w_pla_id;
    }

    public void buscarPlantillaHorario(ActionEvent event){
        HSPlantillaHorarioCLDAO dao=(HSPlantillaHorarioCLDAO) ServiceFinder.findBean("SpringHibernateDaoPlantillaHorario");
        HSCatalogoDAO daoC = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        this.setListaPlantillaHora(new ArrayList<bPlantillaHorario>());
        if(this.getW_pla_descripcion().length()>=3){
            System.out.println("la descripcion es -> "+this.getW_pla_descripcion());
            
            List<ClPlantillaHorario> lista=dao.listarPlantilla(this.getW_pla_descripcion());
            for(int i=0; i<lista.size(); i++){
                bPlantillaHorario pla=new bPlantillaHorario();
                pla.getPlantilla().setPalTurno(lista.get(i).getPalTurno());
                pla.getPlantilla().setPlaActivo(lista.get(i).getPlaActivo());
                pla.getPlantilla().setPlaDescripcion(lista.get(i).getPlaDescripcion());
                pla.getPlantilla().setPlaId(lista.get(i).getPlaId());
                pla.getPlantilla().setUsuId(lista.get(i).getUsuId());
                pla.getPlantilla().setPlaFechaCreacion(lista.get(i).getPlaFechaCreacion());
                pla.setV_descripcion_turno(daoC.seleccionarDescripcion(lista.get(i).getPalTurno()));
                this.getListaPlantillaHora().add(pla);
            }
        }
    }

    public void editarPlantilla(ActionEvent event){
        limpiar();
        HSPlantillaHorarioCLDAO dao=(HSPlantillaHorarioCLDAO) ServiceFinder.findBean("SpringHibernateDaoPlantillaHorario");
        int p_plan_id=Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_plan_id")).getValue().toString());
        this.setPlantilla(dao.seleccionarPlantilla(p_plan_id));
        this.setOncomplete("javascript:Richfaces.showModalPanel('mpPlantilla');");
    }

    public void nuevaPlantilla(ActionEvent event){
        limpiar();
        this.getPlantilla().setPlaId(0);
        this.setOncomplete("javascript:Richfaces.showModalPanel('mpPlantilla');");
    }
    
    public void limpiar(){
        this.setOncomplete("");
        this.setPlantilla(new ClPlantillaHorario());
    }

    public void guardarPlantilla(ActionEvent event){
        HSPlantillaHorarioCLDAO dao=(HSPlantillaHorarioCLDAO) ServiceFinder.findBean("SpringHibernateDaoPlantillaHorario");
        validarPlantilla(this.getPlantilla());
        this.getPlantilla().setPlaActivo("1");
        if(getPlantilla().getPlaId()==0){
            dao.agregarPlantilla(this.getPlantilla());
        }
        else{
            dao.modificarPlantilla(this.getPlantilla());
        }
        this.setOncomplete("javascript:alert('se a guardado correctamente');Richfaces.hideModalPanel('mpPlantilla');");
        buscarPlantillaHorario(event);
    }

    public boolean  validarPlantilla(ClPlantillaHorario plantilla){
        
        if(plantilla.getPlaDescripcion().trim().length()<=0){
            this.setOncomplete("javascript:alert('Ingrese una descripcion correcta')");
            return false;
        }
        if(plantilla.getPalTurno().equals("0000000")){
            this.setOncomplete("javascript:alert('Seleccion un turno')");
            return false;
        }
        if(plantilla.getPlaFechaCreacion()==null){
            this.setOncomplete("javascript:alert('Ingrese una fecha correcta')");
            return false;
        }
        plantilla.setPlaActivo("1");
        return true;
    }

    public void eliminarPlantilla(ActionEvent event){
        HSPlantillaHorarioCLDAO dao=(HSPlantillaHorarioCLDAO) ServiceFinder.findBean("SpringHibernateDaoPlantillaHorario");
        int p_plan_id=Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_plan_id")).getValue().toString());
        dao.eliminarPlantilla(p_plan_id);
    }
    
    


}
