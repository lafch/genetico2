/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.solicitud.managedBeans;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;
import net.uch.academica.hibernateSpringDao.HSAlumnoDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcAlumno;
import net.uch.mapping.TbCatalogo;
import net.uch.mapping.TsFut;
import net.uch.mapping.TsSolicitudPersona;
import net.uch.solicitud.hibernateSpringDao.TsFutDAO;
import net.uch.solicitud.hibernateSpringDao.TsSolicitudPersonaDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;

/**
 *
 * @author biblioteca
 */
public class MBRevisarSoli {
    private String oncomplete;
//    private List<TsSolicitudPersona> listaSolicitudPersona;
    private List<BeanRevisarSoli> listaSolicitudPersona;
    private TsSolicitudPersona objSolicitudPersona;
    private List<File> listaArchivos;
    private TsFut objFut;
    private Date w_fecha;//=new Date();
    private Date w_fecha_fin;
    private int p_indice=-1;
    private int p_solper_id=0;
    private BeanRevisarSoli objbBeanRevisarSoli;
    private SelectItem[] cboEstado;
    private SelectItem[] cboEstadow;
    private String w_estado;
    private int indice_file=-1;
    String barra=java.io.File.separator;
    
    public MBRevisarSoli() {
        this.listaSolicitudPersona= new ArrayList<BeanRevisarSoli>();
        this.objSolicitudPersona = new TsSolicitudPersona();
        this.objFut = new TsFut();
        this.listaArchivos = new ArrayList<File>();
        this.objbBeanRevisarSoli= new BeanRevisarSoli();
    }

    public List<File> getListaArchivos() {
        return listaArchivos;
    }

    public void setListaArchivos(List<File> listaArchivos) {
        this.listaArchivos = listaArchivos;
    }

    public Date getW_fecha_fin() {
        return w_fecha_fin;
    }

    public void setW_fecha_fin(Date w_fecha_fin) {
        this.w_fecha_fin = w_fecha_fin;
    }
    
    public List<BeanRevisarSoli> getListaSolicitudPersona() {
        return listaSolicitudPersona;
    }

    public void setListaSolicitudPersona(List<BeanRevisarSoli> listaSolicitudPersona) {
        this.listaSolicitudPersona = listaSolicitudPersona;
    }
    

    public TsFut getObjFut() {
        return objFut;
    }

    public void setObjFut(TsFut objFut) {
        this.objFut = objFut;
    }

    public TsSolicitudPersona getObjSolicitudPersona() {
        return objSolicitudPersona;
    }

    public void setObjSolicitudPersona(TsSolicitudPersona objSolicitudPersona) {
        this.objSolicitudPersona = objSolicitudPersona;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public Date getW_fecha() {
        return w_fecha;
    }

    public void setW_fecha(Date w_fecha) {
        this.w_fecha = w_fecha;
    }

    public int getP_indice() {
        return p_indice;
    }

    public void setP_indice(int p_indice) {
        this.p_indice = p_indice;
    }

    public BeanRevisarSoli getObjbBeanRevisarSoli() {
        return objbBeanRevisarSoli;
    }

    public void setObjbBeanRevisarSoli(BeanRevisarSoli objbBeanRevisarSoli) {
        this.objbBeanRevisarSoli = objbBeanRevisarSoli;
    }

    public SelectItem[] getCboEstado() {
        HSCatalogoDAO dao=(HSCatalogoDAO)ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        List<TbCatalogo> lista=dao.seleccionarGrupo("072");
        this.cboEstado= new SelectItem[lista.size()+1];
        this.cboEstado[0]= new SelectItem("0", "[-Seleccione-]");
        for(int i=0; i<(this.cboEstado.length - 1); i++){
            this.cboEstado[i+1]= new SelectItem(lista.get(i).getCatCodigoGrupo().concat(lista.get(i).getCatCodigoElemento()),lista.get(i).getCatDescripcionElemento());
        }
        return cboEstado;
    }

    public void setCboEstado(SelectItem[] cboEstado) {
        this.cboEstado = cboEstado;
    }


    public SelectItem[] getCboEstadow() {
        HSCatalogoDAO dao=(HSCatalogoDAO)ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        List<TbCatalogo> lista=dao.seleccionarGrupo("072");
        this.cboEstadow= new SelectItem[lista.size()+1];
        this.cboEstadow[0]= new SelectItem("0", "[-Seleccione-]");
        for(int i=0; i<(this.cboEstadow.length - 1); i++){
            this.cboEstadow[i+1]= new SelectItem(lista.get(i).getCatCodigoGrupo().concat(lista.get(i).getCatCodigoElemento()),lista.get(i).getCatDescripcionElemento());
        }
        return cboEstadow;
    }

    public void setCboEstadow(SelectItem[] cboEstadow) {
        this.cboEstadow = cboEstadow;
    }

    
    
    

    public int getP_solper_id() {
        return p_solper_id;
    }

    public void setP_solper_id(int p_solper_id) {
        this.p_solper_id = p_solper_id;
    }
    
    
    
    public void buscar() throws Exception{
        this.setListaSolicitudPersona(new ArrayList<BeanRevisarSoli>());
        TsSolicitudPersonaDAO dao=(TsSolicitudPersonaDAO) ServiceFinder.findBean("TsSolicitudPersonaDAOHS");
        HSAlumnoDAO daoA=(HSAlumnoDAO)ServiceFinder.findBean("SpringHibernateDaoAlumno");
        HSCatalogoDAO daoC=(HSCatalogoDAO)ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        TsSolicitudPersona solicitudPersona=new TsSolicitudPersona();
        solicitudPersona.setSolperFechaRegistro(this.getW_fecha());
        solicitudPersona.setSolperAprovada(this.getW_estado());
        //List<TsSolicitudPersona> lista=dao.findByExample(solicitudPersona);
        List<TsSolicitudPersona> lista=dao.findFechasEstado(this.w_fecha, this.w_fecha_fin, this.w_estado);
        //this.setListaSolicitudPersona();
        for(int i=0; i<lista.size(); i++){
            BeanRevisarSoli bean=new BeanRevisarSoli();
            AcAlumno alumno=daoA.seleccionarAlumno(lista.get(i).getSolperPersoId());
            bean.setBean_alumno(alumno.getAluAppaterno()+" "+alumno.getAluApmaterno()+" "+alumno.getAluNombres());
            bean.setTsSolicitudPersona(lista.get(i));
            bean.setBean_estado(daoC.seleccionarDescripcion(lista.get(i).getSolperAprovada()));
            bean.setBean_codigo(alumno.getAluCodigo());
            bean.setBean_especialidad(alumno.getEsp().getEspNombre());
            this.getListaSolicitudPersona().add(bean);
        }
    }
    
    public void seleccionar(){
        //this.setObjSolicitudPersona(new TsSolicitudPersona());
        //System.out.println("seleccionar");
        this.setOncomplete("");
        this.setObjbBeanRevisarSoli(new BeanRevisarSoli());
        //this.setObjSolicitudPersona(this.getListaSolicitudPersona().get(this.p_indice).getTsSolicitudPersona());
        this.setObjbBeanRevisarSoli(this.getListaSolicitudPersona().get(this.p_indice));
        
        this.setOncomplete("Richfaces.showModalPanel('mpModal')");
        //System.out.println("fin del modal");
    }
    
    public void grabar() throws Exception{
        this.setOncomplete("");
        TsSolicitudPersonaDAO dao=(TsSolicitudPersonaDAO) ServiceFinder.findBean("TsSolicitudPersonaDAOHS");
       if(this.getObjbBeanRevisarSoli().getTsSolicitudPersona().getSolperAprovada().equals("072002")){
           if(this.getObjbBeanRevisarSoli().getTsSolicitudPersona().getSolperFechaRevision()==null){
               this.getObjbBeanRevisarSoli().getTsSolicitudPersona().setSolperFechaRevision(new Date());
           }
       }
       else if(this.getObjbBeanRevisarSoli().getTsSolicitudPersona().getSolperAprovada().equals("072003")||
               this.getObjbBeanRevisarSoli().getTsSolicitudPersona().getSolperAprovada().equals("072003")){
           if(this.getObjbBeanRevisarSoli().getTsSolicitudPersona().getSolperFechaAprovacion()==null){
               this.getObjbBeanRevisarSoli().getTsSolicitudPersona().setSolperFechaAprovacion(new Date());
           }
       }
        dao.attachDirty(this.getObjbBeanRevisarSoli().getTsSolicitudPersona());
        buscar();
        this.setOncomplete("alert('Registro grabado Correctamente');Richfaces.hideModalPanel('mpModal')");
        
    }
    
    public void seleccionarFut(){
        this.setListaArchivos(new ArrayList<File>());
        this.setOncomplete("");
        this.setObjbBeanRevisarSoli(new BeanRevisarSoli());
        this.setObjFut(new TsFut());
        TsFutDAO dao=(TsFutDAO)ServiceFinder.findBean("TsFutDAOHS");
        this.setObjbBeanRevisarSoli(this.getListaSolicitudPersona().get(this.p_indice));
        List<TsFut> lista=dao.findByProperty("tsSolicitudPersona.solperId", this.p_solper_id);
        if(lista.size()>0){
            this.setObjFut(lista.get(0));
        }
        //System.out.println("indice -> "+0+"\t -> descripcion -> "+this.getObjFut().getFutDirigido());
        //System.out.println("valor -> "+this.getObjFut().getFutSolicito());
        this.p_indice=-1;
        this.p_solper_id=0;
        TsSolicitudPersonaDAO daoP=(TsSolicitudPersonaDAO) ServiceFinder.findBean("TsSolicitudPersonaDAOHS");
        ResourceBundle rb=ResourceBundle.getBundle("net.uch.message.mensaje");
	String ruta=rb.getString("carpeta.solicitud")+this.objFut.getFutId();
        this.setListaArchivos(daoP.listarArchivos(ruta));
        this.setOncomplete("Richfaces.showModalPanel('mpDetalle')");
        
    }
    
    private String w_msgSolicitud;

    public String getW_estado() {
        return w_estado;
    }

    public void setW_estado(String w_estado) {
        this.w_estado = w_estado;
    }
    
    public String getW_msgSolicitud() {
        return w_msgSolicitud;
    }

    public void setW_msgSolicitud(String w_msgSolicitud) {
        this.w_msgSolicitud = w_msgSolicitud;
    }
    private boolean w_visualizar=false;

    public boolean isW_visualizar() {
        return w_visualizar;
    }

    public void setW_visualizar(boolean w_visualizar) {
        this.w_visualizar = w_visualizar;
    }

    public int getIndice_file() {
        return indice_file;
    }

    public void setIndice_file(int indice_file) {
        this.indice_file = indice_file;
    }
    
    public void verificarSolicitud() throws Exception{
        this.setW_msgSolicitud("");
        this.setW_visualizar(false);
        this.setListaSolicitudPersona(listaSolicitudPersona);
        this.w_estado="072001";
        buscar();
        if(this.getListaSolicitudPersona().size()>0){
            this.w_msgSolicitud="HAY "+this.getListaSolicitudPersona().size()+" SOLICITUD(ES) POR REVISAR";
            this.setW_visualizar(true);
        }
        
    }
    
    public String revisarSolicitud(){
        return "successRevisarSolicitud";
    }
    
    public void descargarArchivo(ActionEvent event)
	{
		
		String p_indice_file = ((UIParameter)event.getComponent().findComponent("p_indice_file")).getValue().toString();
		FacesContext context = FacesContext.getCurrentInstance();
		//System.out.println("descargar archivo");
		//System.out.println("entro");
		HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
		try
		{
			//RutaDao dao = (RutaDao)Class.forName("pe.uch.edu.daoImplement.RutaDaoImpl").newInstance();
                    File file=this.getListaArchivos().get(Integer.parseInt(p_indice_file));
                    //System.out.println("archivo -> "+file.getName());
                    ResourceBundle rb=ResourceBundle.getBundle("net.uch.message.mensaje");
                    String ruta=rb.getString("carpeta.solicitud")+this.objFut.getFutId()+barra+file.getName();
                    
			//System.out.println("la ruta del archivo de descarga -> "+dao.rutaDocenteMateriales(paSec_id + barra + paString));
			FileInputStream archivo = new FileInputStream(ruta);
			int longitud = archivo.available();
			byte[] datos = new byte[longitud];
			OutputStream ouputStream = response.getOutputStream();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition","attachment;filename=" + file.getName());

			int leido = archivo.read(datos);
			while(leido >= 0)
			{
				if(leido > 0)
				{
					ouputStream.write(datos,0,leido);
				}
				leido = archivo.read(datos);
			}
			ouputStream.flush();
			ouputStream.close();
			FacesContext.getCurrentInstance().responseComplete();
			archivo.close();
		}
		catch(Exception e){
			System.out.println("carpeta de descarga "+e);
			
		}
	}
    
}
