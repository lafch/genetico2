/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.solicitud.managedBeans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.faces.model.SelectItem;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.*;
import net.uch.solicitud.hibernateSpringDao.*;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;

/**
 *
 * @author biblioteca
 */
public class MBSolicitu {

    private List<TsSolicitud> listaSolicitu;
    private String w_solicitud;
    private String oncomplete;
    private TsSolicitud objSolicitud;
    private int p_indice=-1;
    private SelectItem[] cboTipo;
    private SelectItem[] cboArea;
    private List<TsRequisito> listaRequisito;
    private List<TsRequisitoSolicitud> listaRequisitoSolicitud;
    private List<TsRequisitoSolicitud> listaRequisitoSolicitudBak;
    private int p_indice_requi=-1;
    private int p_indice_req_sol=-1;
    private SelectItem[] cboResponsable;
    private int w_resp_id=0;
    public MBSolicitu() {
        this.listaSolicitu= new ArrayList<TsSolicitud>();
        this.objSolicitud=new TsSolicitud();
        this.objSolicitud.setTsResponsable(new TsResponsable());
        this.listaRequisito=new ArrayList<TsRequisito>();
        this.listaRequisitoSolicitud=new ArrayList<TsRequisitoSolicitud>();
        this.listaRequisitoSolicitudBak=new ArrayList<TsRequisitoSolicitud>();
    }

    public List<TsSolicitud> getListaSolicitu() {
        return listaSolicitu;
    }

    public void setListaSolicitu(List<TsSolicitud> listaSolicitu) {
        this.listaSolicitu = listaSolicitu;
    }

    public TsSolicitud getObjSolicitud() {
        return objSolicitud;
    }

    public void setObjSolicitud(TsSolicitud objSolicitud) {
        this.objSolicitud = objSolicitud;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public String getW_solicitud() {
        return w_solicitud;
    }

    public void setW_solicitud(String w_solicitud) {
        this.w_solicitud = w_solicitud;
    }

    public SelectItem[] getCboArea() {
        AdAreaInstitucionDAO dao=(AdAreaInstitucionDAO) ServiceFinder.findBean("AdAreaInstitucionDAOHS");
        List<AdAreaInstitucion> lista=dao.findAll();
        this.cboArea=new SelectItem[lista.size()+1];
        this.cboArea[0]=new SelectItem(0, "[-Seleccione-]");
        for(int i=0; i<(this.cboArea.length-1); i++){
            this.cboArea[i+1]=new SelectItem(lista.get(i).getAreinsId(), lista.get(i).getAreinsDescripcion());
        }
        return cboArea;
    }

    public void setCboArea(SelectItem[] cboArea) {
        this.cboArea = cboArea;
    }

    public SelectItem[] getCboTipo() {
        HSCatalogoDAO dao=(HSCatalogoDAO)ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        List<TbCatalogo> lista=dao.seleccionarGrupo("048");
        this.cboTipo= new SelectItem[lista.size()+1];
        this.cboTipo[0]= new SelectItem("0", "[-Seleccione-]");
        for(int i=0; i<(this.cboTipo.length - 1); i++){
            this.cboTipo[i+1]= new SelectItem(lista.get(i).getCatCodigoGrupo().concat(lista.get(i).getCatCodigoElemento()),lista.get(i).getCatDescripcionElemento());
        }
        return cboTipo;
    }

    public void setCboTipo(SelectItem[] cboTipo) {
        this.cboTipo = cboTipo;
    }

    public int getP_indice() {
        return p_indice;
    }

    public void setP_indice(int p_indice) {
        this.p_indice = p_indice;
    }

    public List<TsRequisito> getListaRequisito() {
        return listaRequisito;
    }

    public void setListaRequisito(List<TsRequisito> listaRequisito) {
        this.listaRequisito = listaRequisito;
    }

    public List<TsRequisitoSolicitud> getListaRequisitoSolicitud() {
        return listaRequisitoSolicitud;
    }

    public void setListaRequisitoSolicitud(List<TsRequisitoSolicitud> listaRequisitoSolicitud) {
        this.listaRequisitoSolicitud = listaRequisitoSolicitud;
    }

    public int getP_indice_req_sol() {
        return p_indice_req_sol;
    }

    public void setP_indice_req_sol(int p_indice_req_sol) {
        this.p_indice_req_sol = p_indice_req_sol;
    }

    public int getP_indice_requi() {
        return p_indice_requi;
    }

    public void setP_indice_requi(int p_indice_requi) {
        this.p_indice_requi = p_indice_requi;
    }

    public List<TsRequisitoSolicitud> getListaRequisitoSolicitudBak() {
        return listaRequisitoSolicitudBak;
    }

    public void setListaRequisitoSolicitudBak(List<TsRequisitoSolicitud> listaRequisitoSolicitudBak) {
        this.listaRequisitoSolicitudBak = listaRequisitoSolicitudBak;
    }

    public SelectItem[] getCboResponsable() {
        TsResponsableDAO dao=(TsResponsableDAO)ServiceFinder.findBean("TsResponsableDAOHS");
        List<TsResponsable> lista=dao.findAll();
        this.cboResponsable=new SelectItem[lista.size()+1];
        this.cboResponsable[0]= new SelectItem(0, "[-Seleccione-]");
        for(int i=0; i<(this.cboResponsable.length -1) ; i++){
            this.cboResponsable[i+1]=new SelectItem(lista.get(i).getRespId(), lista.get(i).getRespPaterno()+" "+lista.get(i).getRespMaterno()+" "+lista.get(i).getRespNombre());
        }
        return cboResponsable;
    }

    public void setCboResponsable(SelectItem[] cboResponsable) {
        this.cboResponsable = cboResponsable;
    }

    public int getW_resp_id() {
        return w_resp_id;
    }

    public void setW_resp_id(int w_resp_id) {
        this.w_resp_id = w_resp_id;
    }
    
    
    
    
    
    public void nuevo(){
        this.setW_resp_id(0);
        this.setOncomplete("");
        this.setObjSolicitud(new TsSolicitud());
        this.getObjSolicitud().setTsResponsable(new TsResponsable());
        this.setListaRequisitoSolicitud(new ArrayList<TsRequisitoSolicitud>());
        TsRequisitoDAO daoReq=(TsRequisitoDAO)ServiceFinder.findBean("TsRequisitoDAOHS");
        TsRequisito requisito=new TsRequisito();
        requisito.setReqDescripcion("");
        requisito.setReqActivo("1");
        this.setListaRequisito(daoReq.findByExample(requisito));
        this.getObjSolicitud().setAdAreaInstitucion(new AdAreaInstitucion());
        this.setOncomplete("Richfaces.showModalPanel('mpSolicitu')");
    }
    
    
    
    
    public void buscar(){
        this.setListaSolicitu(new ArrayList<TsSolicitud>());
        TsSolicitudDAO dao=(TsSolicitudDAO)ServiceFinder.findBean("TsSolicitudDAOHS");
        TsSolicitud objeto=new TsSolicitud();
        this.objSolicitud.setAdAreaInstitucion(new AdAreaInstitucion());
        objeto.setSolDescripcion(this.w_solicitud);
        objeto.setSolActivo("1");
        this.setListaSolicitu(dao.findByExample(objeto));
    }
    
    public void abrirDelete(){
         this.setOncomplete("");
        this.setObjSolicitud(new TsSolicitud());
        this.setObjSolicitud(listaSolicitu.get(this.p_indice));
        //System.out.println("p_indice -> "+p_indice+"\t id -> "+this.objSolicitud.getSolId());
        this.oncomplete="Richfaces.showModalPanel('mpDelete')";
    }
    
    public void seleccionar(){
        //System.out.println("ingreso al metodo seleccionar");
        this.setOncomplete("");
        this.setObjSolicitud(new TsSolicitud());
        this.getObjSolicitud().setTsResponsable(new TsResponsable());
        this.setListaRequisito(new ArrayList<TsRequisito>());
        this.setListaRequisitoSolicitud(new ArrayList<TsRequisitoSolicitud>());
        this.setObjSolicitud(this.getListaSolicitu().get(this.p_indice));
        if(this.objSolicitud.getTsResponsable()==null){
            this.objSolicitud.setTsResponsable(new TsResponsable());
        }
        TsRequisitoDAO daoReq=(TsRequisitoDAO)ServiceFinder.findBean("TsRequisitoDAOHS");
        TsRequisito requisito=new TsRequisito();
        requisito.setReqDescripcion("");
        requisito.setReqActivo("1");
        this.setListaRequisito(daoReq.findByExample(requisito));
        TsRequisitoSolicitudDAO reqSolDao=(TsRequisitoSolicitudDAO)ServiceFinder.findBean("TsRequisitoSolicitudDAOHS");
        this.setListaRequisitoSolicitud(reqSolDao.findByProperty("tsSolicitud.solId", this.objSolicitud.getSolId()));
        
        List<TsRequisito> lista=new ArrayList<TsRequisito>();
        //System.out.println("cantidad de requsol -> "+this.getListaRequisitoSolicitud().size());
        for(int i=0; i<this.listaRequisitoSolicitud.size(); i++){
            //lista.add(this.listaRequisitoSolicitud.get(i).getTsRequisito());
            for(int x=0; x<this.getListaRequisito().size(); x++){
                if(this.listaRequisito.get(x).getReqId().equals(this.listaRequisitoSolicitud.get(i).getTsRequisito().getReqId())){
                    lista.add(this.listaRequisito.get(x));
                }
            }
        }
       // System.out.println("antes de eliminar -> "+this.getListaRequisito().size());
        this.getListaRequisito().removeAll(lista);
       // this.getListaRequisito().r
        /*for(int i=0; i<this.getListaRequisito().size(); i++){
            for(int x=0; x=)
        }*/
        //System.out.println("despues de eliminar -> "+this.getListaRequisito().size());
        //System.out.println("entro a seleccionar");
       this.setOncomplete("Richfaces.showModalPanel('mpSolicitu')");
    }
    
    public void grabar(){
        //daoSo
        this.setOncomplete("");
        TsSolicitudDAO dao=(TsSolicitudDAO)ServiceFinder.findBean("TsSolicitudDAOHS");
        TsRequisitoSolicitudDAO daoR=(TsRequisitoSolicitudDAO)ServiceFinder.findBean("TsRequisitoSolicitudDAOHS");
        TsRequisitoSolicitud solre=new TsRequisitoSolicitud();
        solre.setTsSolicitud(new TsSolicitud(this.getObjSolicitud().getSolId()));
        if(this.getObjSolicitud().getSolId()!=null && this.getObjSolicitud().getSolId()>0){
            daoR.delete(solre);
        }
        this.objSolicitud.setSolActivo("1");
        this.objSolicitud.setTsRequisitoSolicituds(new HashSet(this.getListaRequisitoSolicitud()));
        //System.out.println("cantidad de registros -> "+this.getObjSolicitud().getTsRequisitoSolicituds().size());
        dao.attachDirty(this.getObjSolicitud());
        buscar();
        this.setOncomplete("Richfaces.hideModalPanel('mpSolicitu')");
    }
    
    public void eliminar(){
        TsSolicitudDAO dao=(TsSolicitudDAO)ServiceFinder.findBean("TsSolicitudDAOHS");
        this.setOncomplete("");
        
        boolean flag=dao.delete(this.objSolicitud);
        if(flag){
            buscar();
            this.setOncomplete("alert('El registro ha sido Eliminado'); Richfaces.hideModalPanel('mpDelete')");
        }
        else{
            this.setOncomplete("alert('No se puede Eliminar el registro'); Richfaces.hideModalPanel('mpDelete')");
        }
    }
    
    public void agregarItem(){
        this.setOncomplete("");
        TsRequisitoSolicitud requisitoSolicitud=new TsRequisitoSolicitud();
        requisitoSolicitud.setTsRequisito(this.getListaRequisito().get(this.p_indice_requi));
        requisitoSolicitud.setReqsolActivo("1");
        requisitoSolicitud.setTsSolicitud(this.getObjSolicitud());
        this.getListaRequisitoSolicitud().add(requisitoSolicitud);
        this.getListaRequisito().remove(this.p_indice_requi);
        //requisitoSolicitud=this.getListaRequisitoSolicitud().get(this.p_)
    }
    
    public void quitarItem(){
        this.setOncomplete("");
        TsRequisito requisito=new TsRequisito();
        requisito=this.getListaRequisitoSolicitud().get(this.p_indice_req_sol).getTsRequisito();
        this.getListaRequisito().add(requisito);
        this.getListaRequisitoSolicitud().remove(this.p_indice_req_sol);
    }
}
