/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.solicitud.managedBeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import net.uch.administrativa.hibernateSpringDao.HSConceptoPagoDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AdConceptoPago;
import net.uch.mapping.TbCatalogo;
import net.uch.mapping.TsRequisito;
import net.uch.solicitud.hibernateSpringDao.TsRequisitoDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;

/**
 *
 * @author Richard rondinel Bustamante
 */
public class MBRequisito {
    private List<TsRequisito> listaRequisito;
    private TsRequisito ObjRequisito;
    private int p_indice=-1;
    private String w_req_descripcion;
    private String oncomplete;
    private SelectItem[] cboTipo;
    private SelectItem[] cboProceso;
    private SelectItem[] cboDocumento;
    private SelectItem[] cboConcepto;
    private List<AdConceptoPago> listaConceptos;
    public MBRequisito() {
        listaRequisito= new ArrayList<TsRequisito>();
        listaConceptos= new ArrayList<AdConceptoPago>();
        ObjRequisito = new TsRequisito();
        w_req_descripcion="";
        cargarConceptos();
    }

    public TsRequisito getObjRequisito() {
        return ObjRequisito;
    }

    public void setObjRequisito(TsRequisito ObjRequisito) {
        this.ObjRequisito = ObjRequisito;
    }

    public List<TsRequisito> getListaRequisito() {
        return listaRequisito;
    }

    public void setListaRequisito(List<TsRequisito> listaRequisito) {
        this.listaRequisito = listaRequisito;
    }

    public int getP_indice() {
        return p_indice;
    }

    public void setP_indice(int p_indice) {
        this.p_indice = p_indice;
    }

    public String getW_req_descripcion() {
        return w_req_descripcion;
    }

    public void setW_req_descripcion(String w_req_descripcion) {
        this.w_req_descripcion = w_req_descripcion;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public List<AdConceptoPago> getListaConceptos() {
        return listaConceptos;
    }

    public void setListaConceptos(List<AdConceptoPago> listaConceptos) {
        this.listaConceptos = listaConceptos;
    }
    
    public SelectItem[] getCboProceso() {
        this.cboProceso= new SelectItem[4];
        this.cboProceso[0]= new SelectItem(0, "[-Seleccione-]");
        this.cboProceso[1]= new SelectItem(1, "[-DOCUMENTOS-]");
        this.cboProceso[2]= new SelectItem(2, "[-ESTADO PENSIONES-]");
        this.cboProceso[3]= new SelectItem(3, "[-CONCEPTO PAGO-]");
        
        return cboProceso;
    }

    public void setCboProceso(SelectItem[] cboProceso) {
        this.cboProceso = cboProceso;
    }

    public SelectItem[] getCboDocumento() {
        HSCatalogoDAO dao=(HSCatalogoDAO)ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        List<TbCatalogo> lista=dao.seleccionarGrupo("031");
        this.cboDocumento= new SelectItem[lista.size()+1];
        this.cboDocumento[0]= new SelectItem("0", "[-Seleccione-]");
        for(int i=0; i<(this.cboDocumento.length - 1); i++){
            this.cboDocumento[i+1]= new SelectItem(lista.get(i).getCatCodigoGrupo().concat(lista.get(i).getCatCodigoElemento()),lista.get(i).getCatDescripcionElemento());
        }
        return cboDocumento;
    }

    public void setCboDocumento(SelectItem[] cboDocumento) {
        this.cboDocumento = cboDocumento;
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

    public SelectItem[] getCboConcepto() {
        HSConceptoPagoDAO dao=(HSConceptoPagoDAO)ServiceFinder.findBean("SpringHibernateDaoConceptoPago");
        List<AdConceptoPago> lista=dao.getListarConceptoRubro("058002");
        this.cboConcepto=new SelectItem[lista.size()+1];
        this.cboConcepto[0]= new SelectItem(0, "[-Seleccione-]");
        for(int i=0; i<(this.cboConcepto.length-1); i++){
            this.cboConcepto[i+1]= new SelectItem(lista.get(i).getId(),lista.get(i).getConpagDescripcion());
        }
        return cboConcepto;
    }

    public void setCboConcepto(SelectItem[] cboConcepto) {
        this.cboConcepto = cboConcepto;
    }
    
    
    public void buscarRequisito(){
        this.setListaRequisito(new ArrayList<TsRequisito>());
        TsRequisitoDAO dao=(TsRequisitoDAO)ServiceFinder.findBean("TsRequisitoDAOHS");
        TsRequisito requisito=new TsRequisito();
        requisito.setReqDescripcion(this.w_req_descripcion);
        requisito.setReqActivo("1");
        this.setListaRequisito(dao.findByExample(requisito));
    }
    public void cargarConceptos(){
        this.setListaConceptos(new ArrayList<AdConceptoPago>());
        HSConceptoPagoDAO dao=(HSConceptoPagoDAO)ServiceFinder.findBean("SpringHibernateDaoConceptoPago");
        this.setListaConceptos(dao.getListarConceptoRubro("058002"));
        
    }
    
    public void seleccionar(){
        limpiar();
        this.setObjRequisito(listaRequisito.get(this.p_indice));
        this.setOncomplete("Richfaces.showModalPanel('mpRequisito')");
    }
    public void limpiar(){
        this.setOncomplete("");
        this.setObjRequisito(new TsRequisito());
    }
    
    public void nuevo(){
        this.limpiar();
        //System.out.println("ingreso al metodp");
        this.setOncomplete("Richfaces.showModalPanel('mpRequisito')");
    }
    
    public void grabar(){
        TsRequisitoDAO dao=(TsRequisitoDAO)ServiceFinder.findBean("TsRequisitoDAOHS");
        //System.out.println("codigo descriptio -> "+this.ObjRequisito.getReqCodigoDescriptivo());
        this.ObjRequisito.setReqActivo("1");
       
        dao.attachDirty(this.ObjRequisito);
        this.setOncomplete("alert('Registro grabado Correctamente');Richfaces.hideModalPanel('mpRequisito')");
        buscarRequisito();
    }
    
    public void eliminar(){
        TsRequisitoDAO dao=(TsRequisitoDAO)ServiceFinder.findBean("TsRequisitoDAOHS");
        this.setOncomplete("");
        
        boolean flag=dao.delete(this.ObjRequisito);
        if(flag){
            buscarRequisito();
            this.setOncomplete("alert('El registro ha sido Eliminado'); Richfaces.hideModalPanel('mpDelete')");
        }
        else{
            this.setOncomplete("alert('No se puede Eliminar el registro'); Richfaces.hideModalPanel('mpDelete')");
        }
    }
    
    public void abrirDelete(){
        this.setObjRequisito(new TsRequisito());
       
        this.setObjRequisito(listaRequisito.get(this.p_indice));
        // System.out.println("p_indice -> "+p_indice+"\t id -> "+this.ObjRequisito.getReqId());
        this.oncomplete="Richfaces.showModalPanel('mpDelete')";
    }
    
}
