/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.cursoLibre.managedBeans;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSMedioPublicidadCLDAO;
import net.uch.mapping.ClMedioPublicidad;
import net.uch.mapping.ClMedioPublicidadDet;


/**
 *
 * @author Simion Richa R B
 */
public class bMantenimientoMedio {
    private String w_medio_descripcion;
    private String oncomplete;
    private int w_medio_id;
    private ClMedioPublicidad medioPublicidad= new ClMedioPublicidad();
    private ClMedioPublicidadDet medioPublicidaDet= new ClMedioPublicidadDet();
    private List<ClMedioPublicidad> listaMedioPublicidad= new ArrayList<ClMedioPublicidad>();
    private List<ClMedioPublicidadDet> listaMedioDetalle= new ArrayList<ClMedioPublicidadDet>();

    public List<ClMedioPublicidadDet> getListaMedioDetalle() {
        return listaMedioDetalle;
    }

    public void setListaMedioDetalle(List<ClMedioPublicidadDet> listaMedioDetalle) {
        this.listaMedioDetalle = listaMedioDetalle;
    }



    public List<ClMedioPublicidad> getListaMedioPublicidad() {
        return listaMedioPublicidad;
    }

    public void setListaMedioPublicidad(List<ClMedioPublicidad> listaMedioPublicidad) {
        this.listaMedioPublicidad = listaMedioPublicidad;
    }

    public ClMedioPublicidadDet getMedioPublicidaDet() {
        return medioPublicidaDet;
    }

    public void setMedioPublicidaDet(ClMedioPublicidadDet medioPublicidaDet) {
        this.medioPublicidaDet = medioPublicidaDet;
    }

    public ClMedioPublicidad getMedioPublicidad() {
        return medioPublicidad;
    }

    public void setMedioPublicidad(ClMedioPublicidad medioPublicidad) {
        this.medioPublicidad = medioPublicidad;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public String getW_medio_descripcion() {
        return w_medio_descripcion;
    }

    public void setW_medio_descripcion(String w_medio_descripcion) {
        this.w_medio_descripcion = w_medio_descripcion;
    }

    public int getW_medio_id() {
        return w_medio_id;
    }

    public void setW_medio_id(int w_medio_id) {
        this.w_medio_id = w_medio_id;
    }

  

    public void listarMediosDePublicidad(ActionEvent event){
       // System.out.println("el valor de la caja -> "+this.getW_medio_descripcion());
        HSMedioPublicidadCLDAO dao = (HSMedioPublicidadCLDAO) ServiceFinder.findBean("SpringHibernateMedioPublicidad");
       // System.out.println("el valor del listado -> "+dao.listarMedioPublicidad(this.getW_medio_descripcion()).size());
        this.setListaMedioPublicidad(dao.listarMedioPublicidad(this.getW_medio_descripcion()));
    }

    public void limpiarData(){
        this.setMedioPublicidad(new ClMedioPublicidad());
        this.getMedioPublicidad().setIdPublicidad(0);
        this.setMedioPublicidaDet(new ClMedioPublicidadDet());
        this.setListaMedioDetalle(new ArrayList<ClMedioPublicidadDet>());
        
    }

    public void nuevoRegistro(ActionEvent event){
        this.setOncomplete("");
        limpiarData();
        this.setOncomplete("javascript:Richfaces.showModalPanel('mpMedio');");
    }

    public void seleccionarMedio(ActionEvent event){
        this.setOncomplete("");
        limpiarData();
        HSMedioPublicidadCLDAO dao = (HSMedioPublicidadCLDAO) ServiceFinder.findBean("SpringHibernateMedioPublicidad");
        int p_med_id= Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_med_id")).getValue().toString());
        this.setMedioPublicidad(dao.seleccionarMedioPublicidad(p_med_id));
        this.setListaMedioDetalle(dao.listarMediopublicidadDeta_x_med(p_med_id));
        this.setOncomplete("javascript:Richfaces.showModalPanel('mpMedio');");
    }
    int contador=0;
    public void agregarTemporal(ActionEvent event){
        String mensaje="";
        this.setOncomplete("");
        if(this.getMedioPublicidaDet().getPubDetDes().length()>3){
            if(this.getMedioPublicidaDet().getPubDetOrd().intValue()>0){
                ClMedioPublicidadDet medio= new ClMedioPublicidadDet();
                medio.setIdPubDet(contador);
                medio.setPubDetDes(this.getMedioPublicidaDet().getPubDetDes());
                medio.setPubDetEst("1");
                medio.setPubDetOrd(this.getMedioPublicidaDet().getPubDetOrd());
                medio.setClMedioPublicidad(this.getMedioPublicidad());
                this.getListaMedioDetalle().add(medio);
               
                contador--;
                this.setMedioPublicidaDet(new ClMedioPublicidadDet());
            }
            else{
                mensaje="javascript:alert('Ingrese un Nro de orden mayor a 0')";
            }
        }
        else{
            mensaje="javascript:alert('Debe ingresar una descripcion')";
        }
        this.setOncomplete(mensaje);
        
    }

    public void guardarRegistros(ActionEvent event){
        this.setOncomplete("");
        System.out.println("entro a guardar");
        HSMedioPublicidadCLDAO dao = (HSMedioPublicidadCLDAO) ServiceFinder.findBean("SpringHibernateMedioPublicidad");
        
        if(this.getMedioPublicidad().getIdPublicidad().intValue()>0){
            System.out.println("entro a la modificacion ");
            dao.modificarMedioPublicidadsql(medioPublicidad);
            for(int i=0; i<this.getListaMedioDetalle().size(); i++){
            dao.guardarMedioPublicidadDet(this.getListaMedioDetalle().get(i));
            System.out.println("Guardando .. "+i+1);
            this.setListaMedioPublicidad(dao.listarMedioPublicidad(this.getW_medio_descripcion()));
            }
        }
        else{
            System.out.println("entro a la insercion ");
             Set<ClMedioPublicidadDet> medio_detalle = new LinkedHashSet<ClMedioPublicidadDet>();
                for(int i=0; i<this.getListaMedioDetalle().size(); i++){
                    ClMedioPublicidadDet detalle=new ClMedioPublicidadDet();
                    detalle.setClMedioPublicidad(this.getListaMedioDetalle().get(i).getClMedioPublicidad());
                    detalle.setIdPubDet(0);
                    detalle.setPubDetDes(this.getListaMedioDetalle().get(i).getPubDetDes());
                    detalle.setPubDetEst("1");
                    detalle.setPubDetOrd(this.getListaMedioDetalle().get(i).getPubDetOrd());
                    medio_detalle.add(detalle);
                    
                }
            this.getMedioPublicidad().setClMedioPublicidadDets(medio_detalle);

            dao.agregarMedioPublicidad(this.getMedioPublicidad());
        }
        
        this.setOncomplete("javascript:alert('se ha guardado correctamente');Richfaces.hideModalPanel('mpMedio');");
    }


}
