/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.academica.managedBeans;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;
import net.uch.academica.hibernateSpringDao.AcAlumnoEstadoDAO;
import net.uch.academica.hibernateSpringDao.HSAlumnoDAO;
import net.uch.academica.hibernateSpringDao.HSMatriculaDAO;
import net.uch.administrativa.hibernateSpringDao.HSAlumnoTarifaDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.*;
import net.uch.solicitud.hibernateSpringDao.TsSolicitudPersonaDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSSemestreDAO;
import net.uch.util.Archivo;
import net.uch.util.BeanParametros;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

/**
 *
 * @author biblioteca
 */
public class MBAlumnoEstado {

    private int p_alu_id;
    private AcAlumnoEstado objAcAlumnoEstado;
    private String oncomplete;
    private String w_stado_alumno;
    private int p_sem_id;
    private boolean p_visualizar = false;
    private Archivo archivoUp = new Archivo();
    private File archivoLi;
    String barra = java.io.File.separator;
    private boolean flagDelete = false;
    private String w_mensaje;
    private SelectItem[] checkSemestres;
    private List<String> w_sem_ids;

    public MBAlumnoEstado() {
        this.objAcAlumnoEstado = new AcAlumnoEstado();
    }

    public AcAlumnoEstado getObjAcAlumnoEstado() {
        return objAcAlumnoEstado;
    }

    public void setObjAcAlumnoEstado(AcAlumnoEstado objAcAlumnoEstado) {
        this.objAcAlumnoEstado = objAcAlumnoEstado;
    }

    public List<String> getW_sem_ids() {
        return w_sem_ids;
    }

    public void setW_sem_ids(List<String> w_sem_ids) {
        this.w_sem_ids = w_sem_ids;
    }
    
    public int getP_alu_id() {
        return p_alu_id;
    }

    public void setP_alu_id(int p_alu_id) {
        this.p_alu_id = p_alu_id;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public String getW_stado_alumno() {
        return w_stado_alumno;
    }

    public void setW_stado_alumno(String w_stado_alumno) {
        this.w_stado_alumno = w_stado_alumno;
    }

    public int getP_sem_id() {
        return p_sem_id;
    }

    public void setP_sem_id(int p_sem_id) {
        this.p_sem_id = p_sem_id;
    }

    public boolean isP_visualizar() {
        return p_visualizar;
    }

    public void setP_visualizar(boolean p_visualizar) {
        this.p_visualizar = p_visualizar;
    }

    public Archivo getArchivoUp() {
        return archivoUp;
    }

    public void setArchivoUp(Archivo archivoUp) {
        this.archivoUp = archivoUp;
    }

    public File getArchivoLi() {
        return archivoLi;
    }

    public void setArchivoLi(File archivoLi) {
        this.archivoLi = archivoLi;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }

    public String getW_mensaje() {
        return w_mensaje;
    }

    public void setW_mensaje(String w_mensaje) {
        this.w_mensaje = w_mensaje;
    }

    public SelectItem[] getCheckSemestres() {
        //System.out.println("p_sem_id - "+p_sem_id);
        if(this.p_sem_id==0){
            this.checkSemestres= new SelectItem[1];
            this.checkSemestres[0] = new SelectItem(0, "[-Seleccione-]");
        }
        return checkSemestres;
    }

    public void setCheckSemestres(SelectItem[] checkSemestres) {
        this.checkSemestres = checkSemestres;
    }
    
    

    public void abrirReserva() throws Exception {
        this.setObjAcAlumnoEstado(new AcAlumnoEstado());
        this.getObjAcAlumnoEstado().setAluestEstado("075001");
        this.setP_visualizar(true);
        this.setOncomplete("");
        this.w_stado_alumno = "Reserva";
        getEstadoAlumno();

    }

    public void abrirRetiroCiclo() throws Exception {
        this.setObjAcAlumnoEstado(new AcAlumnoEstado());
        this.getObjAcAlumnoEstado().setAluestEstado("075002");
        this.setP_visualizar(false);
        this.setOncomplete("");
        this.w_stado_alumno = "Retiro del Ciclo";
        getEstadoAlumno();

    }

    public void abrirRetiroUniversitario() throws Exception {
        this.setObjAcAlumnoEstado(new AcAlumnoEstado());
        this.getObjAcAlumnoEstado().setAluestEstado("075003");
        this.setP_visualizar(false);
        this.setOncomplete("");
        this.w_stado_alumno = "Retiro Universitario";
        getEstadoAlumno();

    }

    public void getEstadoAlumno2() throws Exception {
        try {
            cargarSemestres();
            this.archivoUp = new Archivo();
            // HSAlumnoTarifaDAO daoTar = (HSAlumnoTarifaDAO) ServiceFinder.findBean("SpringHibernateDaoAlumnoTarifa");
            HSAlumnoDAO daoAlu = (HSAlumnoDAO) ServiceFinder.findBean("SpringHibernateDaoAlumno");
            HSSemestreDAO daoSem = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
            AcAlumnoEstadoDAO dao = (AcAlumnoEstadoDAO) ServiceFinder.findBean("AcAlumnoEstadoDAOHS");
            //  List<AdAlumnoTarifa> listaTar = daoTar.seleccionarAlumnoTarifa(this.getP_alu_id(), this.getP_sem_id());
            List<BeanParametros> listaParametro = new ArrayList<BeanParametros>();

            listaParametro.add(new BeanParametros("aluestActivo", "1", " and "));
            listaParametro.add(new BeanParametros("aluestEstado", this.objAcAlumnoEstado.getAluestEstado(), " and "));
            listaParametro.add(new BeanParametros("acAlumno.id", this.getP_alu_id(), " "));
            if (dao.findByProperties(listaParametro).size() > 0) {


                AcAlumnoEstado aluEstado = (AcAlumnoEstado) dao.findByProperties(listaParametro).get(0);
                List<AcAlumnoEstado> lAluEstado = new ArrayList<AcAlumnoEstado>();
                ResourceBundle rb = ResourceBundle.getBundle("net.uch.message.mensaje");
                List<AcSemestre> semestre = daoSem.seleccionarSemestreVigente();
                Integer valor = 1;
                System.out.println("aluEstado-> " + aluEstado.getAcSemestre().getSemNombre() + "\t -> " + aluEstado.getAluestPeriodos());
                for (int i = 0; i < semestre.size(); i++) {
                    if (aluEstado.getAcSemestre().getId() <= semestre.get(i).getId() && valor <= aluEstado.getAluestPeriodos()) {
                        AcAlumnoEstado acAlumnoEstado = new AcAlumnoEstado();
                        acAlumnoEstado.setAcSemestre(aluEstado.getAcSemestre());
                        acAlumnoEstado.setAcSemestreBak(semestre.get(i));
                        lAluEstado.add(acAlumnoEstado);
                        valor++;
                    }
                }

                this.getObjAcAlumnoEstado().setAcAlumno(daoAlu.seleccionarAlumno(this.getP_alu_id()));
                this.getObjAcAlumnoEstado().setAcSemestre(daoSem.getSemestre(this.getP_sem_id()));
                this.getObjAcAlumnoEstado().setAluestRegistro(new Date());
                this.getObjAcAlumnoEstado().setAluestActivo("1");
                this.setFlagDelete(false);
                //if
                //AcAlumnoEstado estado = (AcAlumnoEstado) dao.findAlumnoEstado(this.objAcAlumnoEstado);
                AcAlumnoEstado estado = null;
                if (lAluEstado.size() > 0) {
                    estado = aluEstado;
                }
                if (estado != null) {
                    this.setFlagDelete(true);
                    this.setObjAcAlumnoEstado(estado);
                    if(this.getObjAcAlumnoEstado().getSemIds()!=null){
                       String[] semestres=this.getObjAcAlumnoEstado().getSemIds().split(",");
                       this.setW_sem_ids(new ArrayList<String>());
                        for(int i=0; i<semestres.length; i++){
                            this.w_sem_ids.add(semestres[i].toString());
                            System.out.println("Semestres -> "+semestres[i].toString());
                        } 
                    }
                    
                    String ruta = rb.getString("carpeta.estado_matricula") + this.getObjAcAlumnoEstado().getAluestId();
                    cargarArchivo(ruta);
                }
                else{
                    this.getObjAcAlumnoEstado().setAcAlumno(daoAlu.seleccionarAlumno(this.getP_alu_id()));
                    this.getObjAcAlumnoEstado().setAcSemestre(daoSem.getSemestre(this.getP_sem_id()));
                    this.getObjAcAlumnoEstado().setAluestRegistro(new Date());
                    this.getObjAcAlumnoEstado().setAluestActivo("1");
                    this.setFlagDelete(false);
                }
            }
            
            System.out.println("nombre -> " + this.getObjAcAlumnoEstado().getAluestObservacion());
            this.setOncomplete("Richfaces.showModalPanel('mpEstadoAlu')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void getEstadoAlumno() throws Exception {
        try {
            this.archivoUp = new Archivo();
            this.w_sem_ids= new ArrayList<String>();
            cargarSemestres();
            AcAlumnoEstadoDAO dao = (AcAlumnoEstadoDAO) ServiceFinder.findBean("AcAlumnoEstadoDAOHS");
            HSAlumnoDAO daoAlu = (HSAlumnoDAO) ServiceFinder.findBean("SpringHibernateDaoAlumno");
            HSSemestreDAO daoSem = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
            List<AcEstadoSemestre> lista = dao.seleccionarEstado(this.getP_alu_id(), this.getP_sem_id());
            this.getObjAcAlumnoEstado().setAcAlumno(daoAlu.seleccionarAlumno(this.getP_alu_id()));
            this.getObjAcAlumnoEstado().setAcSemestre(daoSem.getSemestre(this.getP_sem_id()));
            this.getObjAcAlumnoEstado().setAluestRegistro(new Date());
            this.getObjAcAlumnoEstado().setAluestActivo("1");
            this.setFlagDelete(false);
            ResourceBundle rb = ResourceBundle.getBundle("net.uch.message.mensaje");
            AcAlumnoEstado acAlumnoEstado = new AcAlumnoEstado();
            if (lista.size() > 0) {
                acAlumnoEstado = lista.get(0).getAcAlumnoEstado();
                if (acAlumnoEstado.getAluestEstado().equals(this.getObjAcAlumnoEstado().getAluestEstado())) {
                    this.setObjAcAlumnoEstado(acAlumnoEstado);
                    List<AcEstadoSemestre> listaEs= new ArrayList<AcEstadoSemestre>(this.getObjAcAlumnoEstado().getAcEstadoSemestres());
                    //System.out.println("la cabntidad es -> "+listaEs.size());
                    for (int i = 0; i < listaEs.size(); i++) {
                        this.w_sem_ids.add(listaEs.get(i).getAcSemestre().getId().toString());
                        //System.out.println("valor es -> "+listaEs.get(i).toString());
                    }
                    this.setFlagDelete(true);
                    String ruta = rb.getString("carpeta.estado_matricula") + this.getObjAcAlumnoEstado().getAluestId();
                    cargarArchivo(ruta);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setOncomplete("Richfaces.showModalPanel('mpEstadoAlu')");
    }
    public void cargarArchivo(String ruta) {
        TsSolicitudPersonaDAO dao = (TsSolicitudPersonaDAO) ServiceFinder.findBean("TsSolicitudPersonaDAOHS");
        archivoUp = new Archivo();
        Archivo archi = new Archivo();
        //archi.setFile(dao.listarArchivos(ruta).get(0));
        List lista = dao.listarArchivos(ruta);
        System.out.println("cantidad de la lista -> " + lista.size());
        if (lista.size() > 0) {
            if (dao.listarArchivos(ruta).get(0) != null) {
                archi.setFile(dao.listarArchivos(ruta).get(0));
                archivoUp.setFile(archi.getFile());
                archivoUp.setName(archi.getFile().getName());
                archivoUp.setLength((int) archi.getFile().length());
            }
        }
    }

    public void listenerResolucion(UploadEvent event) throws Exception {
        UploadItem item = event.getUploadItem();
        System.out.println("item -> " + item.isTempFile());
        //archivoLi=item.getFile();
        archivoUp.setFile(item.getFile());
        archivoUp.setName(item.getFileName());
        System.out.println("getData() ->  " + item.getData());
        System.out.println("archivo Data -> " + archivoUp.getData());

    }

    public void uploadArchivo(UploadEvent event) {
    }

    public void grabarEstado2(ActionEvent event) {
        this.setOncomplete("");
        this.w_mensaje = "";
        String semestres="";
        for(int i=0; i<this.w_sem_ids.size(); i++){
            semestres=semestres+","+this.w_sem_ids.get(i).toString();
        }
        semestres=semestres.substring(1);
        //System.out.println("semestres -> "+);
        //String[] ids=semestres.split(",");
        HSAlumnoTarifaDAO daoTar = (HSAlumnoTarifaDAO) ServiceFinder.findBean("SpringHibernateDaoAlumnoTarifa");
        List<AdAlumnoTarifa> listaTar = daoTar.seleccionarAlumnoTarifa(this.getP_alu_id(), this.getP_sem_id());
        List<AdAlumnoTarifa> lista = new ArrayList<AdAlumnoTarifa>();

        for (int i = 0; i < listaTar.size(); i++) {
            if (listaTar.get(i).getAlutarEstado().equals("2")) {
                listaTar.get(i).setAlutarActivo("1");
                daoTar.actualizarAlumnoTarifa(listaTar.get(i));
            }

        }
        this.objAcAlumnoEstado.setSemIds(semestres);
        AcAlumnoEstadoDAO dao = (AcAlumnoEstadoDAO) ServiceFinder.findBean("AcAlumnoEstadoDAOHS");
        dao.attachDirty(this.objAcAlumnoEstado);
        //System.out.println("el valor del id -> " + this.objAcAlumnoEstado.getAluestId());
        ResourceBundle rb = ResourceBundle.getBundle("net.uch.message.mensaje");
        String ruta = rb.getString("carpeta.estado_matricula").concat(String.valueOf(this.objAcAlumnoEstado.getAluestId()));
        grabarArchivo(ruta, "", archivoUp.getFile(), archivoUp.getName());
        //  this.setOncomplete("alert('Registro grabado con exito'); Richfaces.hideModalPanel('mpEstadoAlu'); onclickSeleccionar();");
        this.w_mensaje = "El registro ha sido Grabado Correctamente";
        this.setOncomplete("Richfaces.hideModalPanel('mpEstadoAlu');Richfaces.showModalPanel('mpRecargar');");
    }
    public void grabarEstado(ActionEvent event) {
        this.setOncomplete("");
        this.w_mensaje = "";
        HSAlumnoTarifaDAO daoTar = (HSAlumnoTarifaDAO) ServiceFinder.findBean("SpringHibernateDaoAlumnoTarifa");
        HSMatriculaDAO datmat = (HSMatriculaDAO) ServiceFinder.findBean("SpringHibernateDaoMatricula");
        List<AdAlumnoTarifa> listaTar = daoTar.seleccionarAlumnoTarifa(this.getP_alu_id(), this.getP_sem_id());
        //List<AdAlumnoTarifa> lista = new ArrayList<AdAlumnoTarifa>();
        //List<AcMatricula> listaMat= datmat.seleccionarMatricularRegular(this.getP_alu_id(), this.getP_sem_id());
        //int cantidad = datmat.numeroMatriculadasId(this.getP_alu_id(), this.getP_sem_id());
            //if(cantidad>0){
            try {
               /* for (int i = 0; i < listaTar.size(); i++) {             
            if (listaTar.get(i).getAlutarEstado().equals("2")) {
                listaTar.get(i).setAlutarActivo("1");
                daoTar.actualizarAlumnoTarifa(listaTar.get(i));
                
            }else{
                listaTar.get(i).setAlutarActivo("0");
                daoTar.actualizarAlumnoTarifa(listaTar.get(i));
            }
        }    */  
            int idmat = datmat.seleccionarIdMatricularRegular(this.getP_alu_id(), this.getP_sem_id());
                if(idmat!=0)
                {
                    if (this.getObjAcAlumnoEstado().getAluestEstado() == "075001") 
                    {
                        datmat.ReservarMatricula(idmat);
                        daoTar.cambiarActivoReserva(this.getP_alu_id());
                    } else if (this.getObjAcAlumnoEstado().getAluestEstado() == "075002") {
                        datmat.RetiroCiclo(idmat);
                        daoTar.cambiarActivoRetiro(this.getP_alu_id(),this.getP_sem_id());
                    } else {
                        datmat.RetiroUniveridad(idmat);
                        daoTar.cambiarActivoRetiro(this.getP_alu_id(),this.getP_sem_id());
                    }
                }

        } catch (Exception e) {
            System.out.println("error actualizar estado ");
            e.printStackTrace();
        }
                
            /*int idmat=datmat.seleccionarIdMatricularRegular(this.getP_alu_id(), this.getP_sem_id()).getId();
            if(this.getObjAcAlumnoEstado().getAluestEstado()=="075001"){
            datmat.ReservarMatricula(idmat);
            }
            else if(this.getObjAcAlumnoEstado().getAluestEstado()=="075002"){
            datmat.RetiroCiclo(idmat);
            }
            else{
            datmat.RetiroUniveridad(idmat);
            }
            }*/
            
        List<AcEstadoSemestre> listaEstado = new ArrayList<AcEstadoSemestre>();
        for (int i = 0; i < this.w_sem_ids.size(); i++) {
            AcEstadoSemestre acEstadoSemestre = new AcEstadoSemestre();
            acEstadoSemestre.setAcAlumno(new AcAlumno(this.p_alu_id));
            acEstadoSemestre.setAcSemestre(new AcSemestre(Integer.parseInt(this.w_sem_ids.get(i).toString())));
            acEstadoSemestre.setAcAlumnoEstado(this.objAcAlumnoEstado);
            listaEstado.add(acEstadoSemestre);
        }
        if(!this.objAcAlumnoEstado.equals("075001")){
            AcEstadoSemestre acEstadoSemestre = new AcEstadoSemestre();
            acEstadoSemestre.setAcAlumno(new AcAlumno(this.p_alu_id));
            acEstadoSemestre.setAcSemestre(new AcSemestre(this.getP_sem_id()));
            acEstadoSemestre.setAcAlumnoEstado(this.objAcAlumnoEstado);
            listaEstado.add(acEstadoSemestre);
        }
        this.objAcAlumnoEstado.setAcEstadoSemestres(new HashSet<AcEstadoSemestre>(listaEstado));
        AcAlumnoEstadoDAO dao = (AcAlumnoEstadoDAO) ServiceFinder.findBean("AcAlumnoEstadoDAOHS");
        if (this.objAcAlumnoEstado.getAluestId() != null) {
            dao.eliminarEstado(this.objAcAlumnoEstado.getAluestId());
        }
        dao.attachDirty(this.objAcAlumnoEstado);
        ResourceBundle rb = ResourceBundle.getBundle("net.uch.message.mensaje");
        String ruta = rb.getString("carpeta.estado_matricula").concat(String.valueOf(this.objAcAlumnoEstado.getAluestId()));
        grabarArchivo(ruta, "", archivoUp.getFile(), archivoUp.getName());
        //  this.setOncomplete("alert('Registro grabado con exito'); Richfaces.hideModalPanel('mpEstadoAlu'); onclickSeleccionar();");
        this.w_mensaje = "El registro ha sido Grabado Correctamente";
        this.setOncomplete("Richfaces.hideModalPanel('mpEstadoAlu');Richfaces.showModalPanel('mpRecargar');");
    }
    
    public void grabarArchivo(String filePath, String carpeta, File file, String nom_archivo) {
        try {
            crearCarpeta(filePath);
            //System.out.println("renombrar archivo -> " + file.renameTo(new File(filePath + barra + nom_archivo)));

        } catch (Exception e) {
            System.out.println("error + -as ");
            e.printStackTrace();
        }

    }

    public void crearCarpeta(String filePath) {
        try {
            System.out.println("nombre de la carpeta -> " + filePath);
            File dir = new File(filePath);
            if (dir.exists()) {
                System.out.println("carpeta existe");
            } else {
                dir.mkdirs();
                System.out.println("carpeta creada con exito");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void descargarArchivo(ActionEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("descargar archivo");
        System.out.println("entro");
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        try {
            //RutaDao dao = (RutaDao)Class.forName("pe.uch.edu.daoImplement.RutaDaoImpl").newInstance();
            File file = this.archivoUp.getFile();
            System.out.println("archivo -> " + file.getName());
            ResourceBundle rb = ResourceBundle.getBundle("net.uch.message.mensaje");
            String ruta = rb.getString("carpeta.estado_matricula") + this.objAcAlumnoEstado.getAluestId() + barra + file.getName();

            //System.out.println("la ruta del archivo de descarga -> "+dao.rutaDocenteMateriales(paSec_id + barra + paString));
            FileInputStream archivo = new FileInputStream(ruta);
            int longitud = archivo.available();
            byte[] datos = new byte[longitud];
            OutputStream ouputStream = response.getOutputStream();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());

            int leido = archivo.read(datos);
            while (leido >= 0) {
                if (leido > 0) {
                    ouputStream.write(datos, 0, leido);
                }
                leido = archivo.read(datos);
            }
            ouputStream.flush();
            ouputStream.close();
            FacesContext.getCurrentInstance().responseComplete();
            archivo.close();
        } catch (Exception e) {
            System.out.println("carpeta de descarga " + e);

        }
    }

    public void eleiminarArchivo() {
        try {
            File file = this.archivoUp.getFile();
            System.out.println("archivo -> " + file.getName());
            ResourceBundle rb = ResourceBundle.getBundle("net.uch.message.mensaje");
            String ruta = rb.getString("carpeta.estado_matricula") + this.objAcAlumnoEstado.getAluestId() + barra + file.getName();
            File archi = new File(ruta);
            archi.delete();
            cargarArchivo(rb.getString("carpeta.estado_matricula") + this.objAcAlumnoEstado.getAluestId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarArchivo(OutputStream out, Object data) throws IOException, Exception, EOFException {

        InputStream input = new FileInputStream(this.archivoUp.getFile());

        int size = input.available();
        byte[] pdf = new byte[size];
        input.read(pdf);
        out.write(pdf);

        input.close();
        out.flush();
        out.close();
    }

    public void eliminarEstado() {
        this.w_mensaje = "";
        this.oncomplete = "";
        AcAlumnoEstadoDAO dao = (AcAlumnoEstadoDAO) ServiceFinder.findBean("AcAlumnoEstadoDAOHS");
        this.objAcAlumnoEstado.setAluestActivo("0");
        this.objAcAlumnoEstado.setAcEstadoSemestres(null);
        dao.attachDirty(this.objAcAlumnoEstado);
        eleiminarArchivo();
        this.w_mensaje = "Registro Eliminado";
        this.setOncomplete("Richfaces.hideModalPanel('mpEstadoAlu');Richfaces.showModalPanel('mpRecargar');");
    }
    
    public void cargarSemestres(){
        HSSemestreDAO dao = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
        AcSemestre semestre=dao.getSemestre(this.p_sem_id);
        List<AcSemestre> lista=dao.listarSemestreSuperiores(semestre.getSemFechaInicio());
        //System.out.println("cantidad semestre -> "+lista.size());
        this.checkSemestres=new SelectItem[lista.size()];
        for (int i = 0; i < this.checkSemestres.length; i++) {
            this.checkSemestres[i]=new SelectItem(lista.get(i).getId(), lista.get(i).getSemCodigo());
        }
    }
    
}