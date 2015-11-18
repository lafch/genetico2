/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.asistenciaDocente.managedBeans;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.academica.hibernateSpringDao.HSDocenteDAO;
import net.uch.academica.hibernateSpringDao.HSTurnoDAO;
import net.uch.asistenciaDocente.hibernateSpringDao.HSAsistenciaDocenteDAO;
import net.uch.asistenciaDocente.managedBeans.beans.AsistenciaDocenteBean;
import net.uch.asistenciaDocente.managedBeans.beans.AsistenciaDocenteDiariaBean;
import net.uch.asistenciaDocente.managedBeans.beans.NuevaAsistenciaBean;
import net.uch.asistenciaDocente.managedBeans.beans.NuevaHoraAdicional;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.*;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSFacultadDAO;

/**
 *
 * @author cesardl
 */
public class bAsistenciaDocenteDiaria {

    private String dato;
    private String suggestion;
    private String suggestionha;
    private int b_doc_id;
    private String b_doc_codigo;
    private String b_doc_nombre;
    private Date b_session_fecha_ini=new Date();;
    private Date b_session_fecha_fin;
    private String b_tipasis_code;
    private SelectItem b_tipasis[];
    private String w_doc_nombre;
    private String w_doc_codigo;
    private int w_doc_id = 0;
    private String w_sec_nombre;
    private String w_cur_nombre;
    private SimpleDateFormat formatTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Date hora_ingre;
    private String doc_nombre;
    private String b_esp_nombre;
    private Date b_hora_Ini_2;
    private Date b_hora_Fin_2;
    private int s_doc_id;
    private int b_ses_id;
    private Date b_fecha_registro = new Date();
    private String b_sec_nombre;
    private String b_cur_nombre;
    private int b_plan_cur_id;
    private List<bAsistenciaDocenteDiaria> b_listar_Sessiones = new ArrayList<bAsistenciaDocenteDiaria>();
    private Date b_ses_horaIni = new Date();
    private Date b_ses_horaFin = new Date();
    //Nuevos atributos
    private Date b_ses_horaIngreso = new Date();
    private Date b_ses_horaSalida = new Date();
    private String b_aula;
    private int turno_id;
    private SelectItem comboFacultades[];
    private int b_idfacultad;
    private String b_facultad;
    private String b_escuela;
    private SelectItem b_cboTurno[];
    private SelectItem b_cboFacultades[];
    //cerramos los nuevoa atributos
    private AsistenciaDocenteBean asisDocente = new AsistenciaDocenteBean();
    private AsistenciaDocenteDiariaBean asisDocenteDiaria = new AsistenciaDocenteDiariaBean();
    private NuevaAsistenciaBean nuevAsisDocente = new NuevaAsistenciaBean();
    private NuevaHoraAdicional nuevaHoraAdicional = new NuevaHoraAdicional();
    
    private String oncomplete;

    //CONSTRUCTORES
    public bAsistenciaDocenteDiaria() {
    }

    public bAsistenciaDocenteDiaria(int p) {
    }

    public SelectItem[] getB_tipasis() {
        try {
            HSCatalogoDAO catalogoDAO = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List l = catalogoDAO.seleccionarCatalogo("050");
            b_tipasis = new SelectItem[l.size() + 1];

            for (int i = 0; i < l.size(); i++) {
                TbCatalogo cat = (TbCatalogo) l.get(i);
                b_tipasis[i + 1] = new SelectItem("050" + cat.getCatCodigoElemento(), cat.getCatDescripcionElemento());
            }
        } catch (Exception e) {
            b_tipasis = new SelectItem[1];
        } finally {
            b_tipasis[0] = new SelectItem(0, "[Seleccione]");
        }
        return b_tipasis;
    }
    

    public void setB_tipasis(SelectItem[] b_tipasis) {
        this.b_tipasis = b_tipasis;
    }

    public String getB_tipasis_code() {
        return b_tipasis_code;
    }

    public void setB_tipasis_code(String b_tipasis_code) {
        this.b_tipasis_code = b_tipasis_code;
    }

    public String getB_esp_nombre() {
        return b_esp_nombre;
    }

    public void setB_esp_nombre(String b_esp_nombre) {
        this.b_esp_nombre = b_esp_nombre;
    }

    public int getS_doc_id() {
        return s_doc_id;
    }

    public void setS_doc_id(int s_doc_id) {
        this.s_doc_id = s_doc_id;
    }

    public Date getB_hora_Fin_2() {
        return b_hora_Fin_2;
    }

    public void setB_hora_Fin_2(Date b_hora_Fin_2) {
        this.b_hora_Fin_2 = b_hora_Fin_2;
    }

    public Date getB_hora_Ini_2() {
        return b_hora_Ini_2;
    }

    public void setB_hora_Ini_2(Date b_hora_Ini_2) {
        this.b_hora_Ini_2 = b_hora_Ini_2;
    }

    public Date getB_session_fecha_ini() {
        return b_session_fecha_ini;
    }

    public void setB_session_fecha_ini(Date b_session_fecha_ini) {
        this.b_session_fecha_ini = b_session_fecha_ini;
    }

    public Date getB_session_fecha_fin() {
        return b_session_fecha_fin;
    }

    public void setB_session_fecha_fin(Date b_session_fecha_fin) {
        this.b_session_fecha_fin = b_session_fecha_fin;
    }
    private SelectItem[] cboDocente;

    public SelectItem[] getCboDocente() throws Exception {
        HSDocenteDAO dao2 = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");
        List<AcDocente> listaDocente = dao2.seleccionarDocente();

        cboDocente = new SelectItem[listaDocente.size()];
        for (int i = 0; i < cboDocente.length; i++) {
            cboDocente[i] = new SelectItem(listaDocente.get(i).getId(), listaDocente.get(i).getDocNombre());
        }
        return cboDocente;
    }

    public void setCboDocente(SelectItem[] cboDocente) {
        this.cboDocente = cboDocente;
    }

    public Date getHora_ingre() {
        return hora_ingre;
    }

    public void setHora_ingre(Date hora_ingre) {
        this.hora_ingre = hora_ingre;
    }

    public String getDoc_nombre() {
        return doc_nombre;
    }

    public void setDoc_nombre(String doc_nombre) {
        this.doc_nombre = doc_nombre;
    }

    public String getW_cur_nombre() {
        return w_cur_nombre;
    }

    public void setW_cur_nombre(String w_cur_nombre) {
        this.w_cur_nombre = w_cur_nombre;
    }

    public String getW_sec_nombre() {
        return w_sec_nombre;
    }

    public void setW_sec_nombre(String w_sec_nombre) {
        this.w_sec_nombre = w_sec_nombre;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public AsistenciaDocenteBean getAsisDocente() {
        return asisDocente;
    }

    public void setAsisDocente(AsistenciaDocenteBean asisDocente) {
        this.asisDocente = asisDocente;
    }

    public AsistenciaDocenteDiariaBean getAsisDocenteDiaria() {
        return asisDocenteDiaria;
    }

    public void setAsisDocenteDiaria(AsistenciaDocenteDiariaBean asisDocenteDiaria) {
        this.asisDocenteDiaria = asisDocenteDiaria;
    }
    
    public NuevaAsistenciaBean getNuevAsisDocente() {
        return nuevAsisDocente;
    }
    

    public void setNuevAsisDocente(NuevaAsistenciaBean nuevAsisDocente) {
        this.nuevAsisDocente = nuevAsisDocente;
    }
    
    public NuevaHoraAdicional getNuevaHoraAdicional() {
        return nuevaHoraAdicional;
    }
    

    public void setNuevaHoraAdicional(NuevaHoraAdicional nuevaHoraAdicional) {
        this.nuevaHoraAdicional = nuevaHoraAdicional;
    }

    public Date getB_ses_horaFin() {
        return b_ses_horaFin;
    }

    public void setB_ses_horaFin(Date b_ses_horaFin) {
        this.b_ses_horaFin = b_ses_horaFin;
    }

    public Date getB_ses_horaIni() {
        return b_ses_horaIni;
    }

    public void setB_ses_horaIni(Date b_ses_horaIni) {
        this.b_ses_horaIni = b_ses_horaIni;
    }

    public List<bAsistenciaDocenteDiaria> getB_listar_Sessiones() {
        return b_listar_Sessiones;
    }

    public void setB_listar_Sessiones(List<bAsistenciaDocenteDiaria> b_listar_Sessiones) {
        this.b_listar_Sessiones = b_listar_Sessiones;
    }

    public String getB_cur_nombre() {
        return b_cur_nombre;
    }

    public void setB_cur_nombre(String b_cur_nombre) {
        this.b_cur_nombre = b_cur_nombre;
    }

    public Date getB_fecha_registro() {
        return b_fecha_registro;
    }

    public void setB_fecha_registro(Date b_fecha_registro) {
        this.b_fecha_registro = b_fecha_registro;
    }

    public int getB_plan_cur_id() {
        return b_plan_cur_id;
    }

    public void setB_plan_cur_id(int b_plan_cur_id) {
        this.b_plan_cur_id = b_plan_cur_id;
    }

    public String getB_sec_nombre() {
        return b_sec_nombre;
    }

    public void setB_sec_nombre(String b_sec_nombre) {
        this.b_sec_nombre = b_sec_nombre;
    }

    public int getB_ses_id() {
        return b_ses_id;
    }

    public void setB_ses_id(int b_ses_id) {
        this.b_ses_id = b_ses_id;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
    public String getSuggestionha() {
        return suggestionha;
    }

    public void setSuggestionha(String suggestionha) {
        this.suggestionha = suggestionha;
    }

    public String getB_doc_nombre() {
        return b_doc_nombre;
    }

    public void setB_doc_nombre(String b_doc_nombre) {
        this.b_doc_nombre = b_doc_nombre;
    }

    public String getB_doc_codigo() {
        return b_doc_codigo;
    }

    public void setB_doc_codigo(String b_doc_codigo) {
        this.b_doc_codigo = b_doc_codigo;
    }

    public int getB_doc_id() {
        return b_doc_id;
    }

    public void setB_doc_id(int b_doc_id) {
        this.b_doc_id = b_doc_id;
    }

    public String getW_doc_nombre() {
        return w_doc_nombre;
    }

    public void setW_doc_nombre(String w_doc_nombre) {
        this.w_doc_nombre = w_doc_nombre;
    }

    public String getW_doc_codigo() {
        return w_doc_codigo;
    }

    public void setW_doc_codigo(String w_doc_codigo) {
        this.w_doc_codigo = w_doc_codigo;
    }

    public String getB_aula() {
        return b_aula;
    }

    public void setB_aula(String b_aula) {
        this.b_aula = b_aula;
    }
    
   
    public Date getB_ses_horaIngreso() {
        return b_ses_horaIngreso;
    }

    public void setB_ses_horaIngreso(Date b_ses_horaIngreso) {
        this.b_ses_horaIngreso = b_ses_horaIngreso;
    }
    
     public Date getB_ses_horaSalida() {
        return b_ses_horaSalida;
    }

    public void setB_ses_horaSalida(Date b_ses_horaSalida) {
        this.b_ses_horaSalida = b_ses_horaSalida;
    }
    
    public int getB_idfacultad() {
        return b_idfacultad;
    }

    public void setB_idfacultad(int b_idfacultad) {
        this.b_idfacultad = b_idfacultad;
    }

    public String getB_escuela() {
        return b_escuela;
    }

    public void setB_escuela(String b_escuela) {
        this.b_escuela = b_escuela;
    }

    public String getB_facultad() {
        return b_facultad;
    }

    public void setB_facultad(String b_facultad) {
        this.b_facultad = b_facultad;
    }
        
       
    public SelectItem[] getB_cboFacultades() {
        HSFacultadDAO dao = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
        List<AcFacultad> facultad = dao.seleccionarFacultadId();//sem_id=19
        b_cboFacultades = new SelectItem[facultad.size() + 1];
        b_cboFacultades[0] = new SelectItem(0, "[Seleccione]");
        for (int i = 0; i < b_cboFacultades.length - 1; i++) {
            b_cboFacultades[i + 1] = new SelectItem(facultad.get(i).getId(), facultad.get(i).getFacNombre());
        }
        return b_cboFacultades;
    }
    

    public void setB_cboFacultades(SelectItem[] b_cboFacultades) {
        this.b_cboFacultades = b_cboFacultades;
    }
    
    public SelectItem[] getB_cboTurno() {
        HSTurnoDAO dao = (HSTurnoDAO) ServiceFinder.findBean("HibernateSpringDaoTurno");
        List<AcTurno> turnos = dao.seleccionarTurno(19);//sem_id=19
        b_cboTurno = new SelectItem[turnos.size() + 1];
        b_cboTurno[0] = new SelectItem(0, "[Seleccione]");
        for (int i = 0; i < b_cboTurno.length - 1; i++) {
            b_cboTurno[i + 1] = new SelectItem(turnos.get(i).getId(), turnos.get(i).getTurNombre());
        }
        return b_cboTurno;
    }
    

    public void setB_cboTurno(SelectItem[] b_cboTurno) {
        this.b_cboTurno = b_cboTurno;
    }
    
    public int getTurno_id() {
        return turno_id;
    }

    public void setTurno_id(int turno_id) {
        this.turno_id = turno_id;
    }

    /**
     * Retorna el docIdBk
     * @return
     */
    public int getW_doc_id() {
        return w_doc_id;
    }

    /**
     * Setea el docIdBk
     * @param w_doc_id
     */
    public void setW_doc_id(int w_doc_id) {
        this.w_doc_id = w_doc_id;
    }

    public List<bAsistenciaDocenteDiaria> autocomplete(Object suggestion) {
        List<bAsistenciaDocenteDiaria> listaEmpleados = new ArrayList<bAsistenciaDocenteDiaria>();
        String ref = (String) suggestion;
        HSAsistenciaDocenteDAO dao = (HSAsistenciaDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoAsistenciaDocente");
        List empleados = dao.buscarDocente_x_dato1(ref);
        for (int i = 0; i < empleados.size(); i++) {
            bAsistenciaDocenteDiaria doc = new bAsistenciaDocenteDiaria(0);
            doc.setB_doc_codigo(((AcDocente) empleados.get(i)).getDocCodigo());
            doc.setB_doc_id(((AcDocente) empleados.get(i)).getId());
            doc.setB_doc_nombre(((AcDocente) empleados.get(i)).getDocNombre());
            listaEmpleados.add(doc);
        }
        return listaEmpleados;
    }
    
     public List<bAsistenciaDocenteDiaria> autocompleteDiaria(Object suggestion) {
        List<bAsistenciaDocenteDiaria> listaEmpleados = new ArrayList<bAsistenciaDocenteDiaria>();
        String ref = (String) suggestion;
        HSAsistenciaDocenteDAO dao = (HSAsistenciaDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoAsistenciaDocente");
        List empleados = dao.buscarDocente_x_dato1(ref);
//        System.out.println("entro al auto");
        for (int i = 0; i < empleados.size(); i++) {
            bAsistenciaDocenteDiaria doc = new bAsistenciaDocenteDiaria(0);
            doc.setB_doc_codigo(((AcDocente) empleados.get(i)).getDocCodigo());
            doc.setB_doc_id(((AcDocente) empleados.get(i)).getId());
            doc.setB_doc_nombre(((AcDocente) empleados.get(i)).getDocNombre());
            listaEmpleados.add(doc);
        }
        return listaEmpleados;
    }
    
    public List<bAsistenciaDocenteDiaria> autocompleteha(Object suggestionha) {
        List<bAsistenciaDocenteDiaria> listaEmpleados = new ArrayList<bAsistenciaDocenteDiaria>();
        String ref = (String) suggestionha;
        HSAsistenciaDocenteDAO dao = (HSAsistenciaDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoAsistenciaDocente");
        List empleados = dao.buscarDocente_x_dato(ref);
//        System.out.println("entro al auto");
        for (int i = 0; i < empleados.size(); i++) {
            bAsistenciaDocenteDiaria doc = new bAsistenciaDocenteDiaria(0);
            doc.setB_doc_codigo(((AcDocente) empleados.get(i)).getDocCodigo());
            doc.setB_doc_id(((AcDocente) empleados.get(i)).getId());
            //this.setS_doc_id(doc.getB_doc_id());
            doc.setB_doc_nombre(((AcDocente) empleados.get(i)).getDocNombre());
            listaEmpleados.add(doc);
        }
        return listaEmpleados;
    }

    /**
     * Muestra las sessiones de acuerdo a los parametros ingresados
     * @param event
     * @throws Exception
     */
    
    
    /*CREAMOS ASISTENCIA DOCENTE DIARIA*/
    public void mostrarAsistenciaDiaria(ActionEvent event) {
        try {
            HSAsistenciaDocenteDAO daoAsistencia = (HSAsistenciaDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoAsistenciaDocente");
            List<bAsistenciaDocenteDiaria> listaEmpleados = new ArrayList<bAsistenciaDocenteDiaria>();
            List<AsistenciaDocenteBean> empleados = daoAsistencia.listarAsistenciaDiaria_x_docenteFecha(this.getW_doc_id(), this.getB_session_fecha_ini(),this.turno_id,this.b_idfacultad);
            for (int i = 0; i < empleados.size(); i++) {
                bAsistenciaDocenteDiaria asis = new bAsistenciaDocenteDiaria(0);
                
                asis.setB_doc_id(empleados.get(i).getDoc_id());
                
                asis.setB_sec_nombre(empleados.get(i).getSec_nombre());
                asis.setDoc_nombre(empleados.get(i).getDoc_nombre());
                asis.setB_cur_nombre(empleados.get(i).getCur_nombre());
                asis.setB_aula(empleados.get(i).getAula());
                asis.setB_ses_horaIni(empleados.get(i).getHora_inicio());
                asis.setB_ses_horaFin(empleados.get(i).getHora_fin());
                asis.setB_ses_horaIngreso(empleados.get(i).getHora_ingreso());
                asis.setB_ses_horaSalida(empleados.get(i).getHora_salida());
                asis.setB_facultad(empleados.get(i).getFacultad());
                //asis.setB_escuela(empleados.get(i).getEscuela());
                asis.setB_doc_id(empleados.get(i).getDoc_id());
                listaEmpleados.add(asis);
                this.setW_doc_id(0);
                this.setW_doc_codigo("");
                this.setW_doc_nombre("");
                this.setSuggestion("");
            }
            this.setB_listar_Sessiones(listaEmpleados);
        } catch (Exception e) {
        }
    }
    
     public void seleccionarSesionDocente(ActionEvent event) throws Exception {
        HSAsistenciaDocenteDAO dao = (HSAsistenciaDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoAsistenciaDocente");
        HSDocenteDAO daoDoc = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");

        int p_ses_id = Integer.parseInt(((UIParameter) event.getComponent().findComponent("w_ses_id")).getValue().toString());
        String p_cur_nombre = ((UIParameter) event.getComponent().findComponent("p_cur_nombre")).getValue().toString();
        String p_sec_nombre = ((UIParameter) event.getComponent().findComponent("p_sec_nombre")).getValue().toString();
        int p_doc_id = Integer.parseInt(((UIParameter) event.getComponent().findComponent("w_doc_id")).getValue().toString());

        try {
            AcSesionEfectivaAsisDoc sesefeasisdoc = dao.listaMarcadoDeAsistencia_x_Docente(p_ses_id);
            AsistenciaDocenteBean asis = new AsistenciaDocenteBean();

            if (sesefeasisdoc instanceof AcSesionEfectivaAsisDoc) {
                asis.setDoc_id(sesefeasisdoc.getAcDocente().getId());
                asis.setDoc_codigo(sesefeasisdoc.getAcDocente().getDocCodigo());
                asis.setDoc_nombre(sesefeasisdoc.getAcDocente().getDocNombre());
                asis.setSes_id(sesefeasisdoc.getAcSesionAsistencia().getSesId());
                asis.setSec_nombre(sesefeasisdoc.getAcSesionAsistencia().getAcSeccion().getSecNombre());
                asis.setCur_nombre(sesefeasisdoc.getAcSesionAsistencia().getAcSeccion().getCurape().getPlancur().getCur().getCurNombre());
                asis.setEstasis_code(sesefeasisdoc.getSesefeasisdocTipo());
                asis.setSesefeasisdoc_fecha(sesefeasisdoc.getAcSesionAsistencia().getSesFechaRegistro());
                asis.setSesefeasisdoc_id(sesefeasisdoc.getSesefeasisdocId());

                Timestamp t1 = sesefeasisdoc.getSesefeasisdocRegistro();
                Timestamp t2 = sesefeasisdoc.getSesefeasisdocSalida();
                if (t1 != null) {
                    asis.setReg_hora(t1.getHours());
                    asis.setReg_min(t1.getMinutes());
                }
                if (t2 != null) {
                    asis.setSal_hora(t2.getHours());
                    asis.setSal_min(t2.getMinutes());
                }
                AcSesionAsistencia ses = dao.listarSessionAsistencia_x_sesid(p_ses_id);
                asis.setRegistro(ses.getSesFechaInicio());
                asis.setSalida(ses.getSesFechaFin());
                asis.setSesefeasisdoc_registro(sesefeasisdoc.getSesefeasisdocRegistro());
                asis.setSesefeasisdoc_salida(sesefeasisdoc.getSesefeasisdocSalida());
                asis.setSesefeasisdoc_obs(sesefeasisdoc.getSesefeasisdocObs());
            } else {
                AcDocente doc = daoDoc.seleccionarDocente(p_doc_id);
                AcSesionAsistencia ses = dao.listarSessionAsistencia_x_sesid(p_ses_id);
                asis.setRegistro(ses.getSesFechaInicio());
                asis.setSalida(ses.getSesFechaFin());
                asis.setSesefeasisdoc_fecha(ses.getSesFechaRegistro());
                asis.setDoc_id(doc.getId());
                asis.setDoc_codigo(doc.getDocCodigo());
                asis.setDoc_nombre(doc.getDocNombre());
                asis.setSes_id(p_ses_id);
                asis.setSec_nombre(p_sec_nombre);
                asis.setCur_nombre(p_cur_nombre);
            }
            this.setAsisDocente(asis);
            this.setOncomplete("Richfaces.showModalPanel('mpAsisDocenteDiario')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /*CERRAMOS SESION ASISTENCIA DIARIA DOCENTE*/
    
     public void guardarObervacionAsistenciaDiaria(ActionEvent event) {
        int p_ses_id = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_ses_id")).getValue().toString());
        int s_doc_aux_id = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_doc_id")).getValue().toString());
        int p_sesefec_id = Integer.parseInt(((UIParameter) event.getComponent().findComponent("p_sesefec_id")).getValue().toString());

        try {
            if (this.getAsisDocente().getEstasis_code().equals("0")) {
                this.setOncomplete("javascript:alert('SELECCIONE UN TIPO DE ASISTENCIA');");
            } 
            else {


                Date dateRegistro = new Date(this.getAsisDocente().getSesefeasisdoc_fecha().getTime());       
                Date dateSalida = new Date(this.getAsisDocente().getSesefeasisdoc_fecha().getTime());
                
                
                HSAsistenciaDocenteDAO daoADD = (HSAsistenciaDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoAsistenciaDocente");

                AcSesionEfectivaAsisDoc sefec = new AcSesionEfectivaAsisDoc();
                AcSesionAsistencia sesAsis=new AcSesionAsistencia();

                Timestamp tfRegistro = Timestamp.valueOf(formatTimeStamp.format(dateRegistro));
   
                sefec.setSesefeasisdocObs(this.getAsisDocente().getSesefeasisdoc_obs());
                sesAsis.setSesObservacion(this.getAsisDocente().getSesefeasisdoc_obs());

                daoADD.ingresarAsisteanDocente(sefec);
                this.mostrarAsistenciaDiaria(event);
                this.setOncomplete("javascript:alert('REGISTRO AGREGADO SATISFACTORIAMENTE');"
                        + "Richfaces.hideModalPanel('mpAsisDocenteDiario')");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void EliminarFila(ActionEvent event) throws Exception {
        System.out.println("entro EliminarFila");
        UIParameter delete = (UIParameter) event.getComponent().findComponent("delete");
        Eliminar(delete.getValue().toString());
    }

    public void Eliminar(String id) throws Exception {
        System.out.println("Entro a eliminar");
        HSAsistenciaDocenteDAO dao = (HSAsistenciaDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoAsistenciaDocente");
        dao.eliminarSesionDocente("" + id);

        this.setOncomplete("javascript:alert('REGISTRO ELIMINADO SATISFACCTORIAMENTE');"
                        + "Richfaces.hideModalPanel('mpAsisDocente')");

    }

    public void seleccionarSesion(ActionEvent event) throws Exception {
        this.setOncomplete("");

        int p_ses_id = Integer.parseInt(((UIParameter) event.getComponent().findComponent("w_ses_id")).getValue().toString());

        HSAsistenciaDocenteDAO daoAsistencia = (HSAsistenciaDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoAsistenciaDocente");
        HSDocenteDAO daoDocente = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");

        AcSesionAsistencia sesasis = daoAsistencia.listarSessionAsistencia_x_sesid(p_ses_id);

        AcDocente doc = (AcDocente) daoDocente.seleccionarDocente(sesasis.getDocId());
        this.setB_ses_id(p_ses_id);
        this.setB_doc_id(doc.getId());
        this.setS_doc_id(doc.getId());
        this.setB_doc_nombre(doc.getDocNombre());
        this.setW_doc_id(sesasis.getDocIdBk());
//        this.setB_sec_nombre(sesasis.getAcSeccion().getSecNombre());
//        this.setB_cur_nombre(sesasis.getAcSeccion().getCurape().getPlancur().getCur().getCurNombre());
        if (this.getB_tipasis_code().equalsIgnoreCase("050001")) {
            this.setB_cur_nombre(sesasis.getAcSeccion().getCurape().getPlancur().getCur().getCurNombre());
            this.setB_sec_nombre(sesasis.getAcSeccion().getSecNombre());
            this.setB_esp_nombre(sesasis.getAcSeccion().getCurape().getPlancur().getCur().getEsp().getEspNombre());
        } else if (this.getB_tipasis_code().equalsIgnoreCase("050002")) {
            String area = daoAsistencia.seleccionarArea(sesasis.getAcSeccion().getId().intValue()).getAreNombre();
            this.setB_cur_nombre(area);
            this.setB_sec_nombre("AREA");
            this.setB_esp_nombre(area);
        }

        this.setB_hora_Ini_2(sesasis.getSesFechaInicio());
        this.setB_hora_Fin_2(sesasis.getSesFechaFin());

        this.setOncomplete("Richfaces.showModalPanel('mpSessionAsis')");
    }

    public void limpiar() {
        nuevAsisDocente = new NuevaAsistenciaBean();
    }
    
    public void limpiarAdicional() {
        nuevaHoraAdicional = new NuevaHoraAdicional();
        /*this.b_doc_nombre="";
        this.b_doc_id=0;
        this.b_doc_codigo="";
        this.s_doc_id=0;
        this.suggestionha="";*/
    }

    public void validarNuevaSesion() {
        this.setOncomplete("");
        try {
            Date d1 = this.getNuevAsisDocente().getN_fsesion();

            Date d_reg = new Date(d1.getTime());
            d_reg.setHours(this.getNuevAsisDocente().getReg_hora());
            d_reg.setMinutes(this.getNuevAsisDocente().getReg_min());
            this.getNuevAsisDocente().setN_fsesion_ini(d_reg);

            Date d_sal = new Date(d1.getTime());
            d_sal.setHours(this.getNuevAsisDocente().getSal_hora());
            d_sal.setMinutes(this.getNuevAsisDocente().getSal_min());
            this.getNuevAsisDocente().setN_fsesion_fin(d_sal);

            String ts_reg = formatTimeStamp.format(d_reg);
            String ts_sal = formatTimeStamp.format(d_sal);

            if (this.getNuevAsisDocente().getN_doc_id() == 0) {
                this.setOncomplete("javascript:alert('SELECCIONE UN DOCENTE');");
            } else if (this.getNuevAsisDocente().getN_especialidad_code() == 0) {
                this.setOncomplete("javascript:alert('SELECCIONE UNA ESPECIALIDAD');");
            } else if (this.getNuevAsisDocente().getN_seccion_code().equalsIgnoreCase("0")) {
                this.setOncomplete("javascript:alert('SELECCIONE UNA SECCION');");
            } else if (this.getNuevAsisDocente().getN_curso_seccion() == 0) {
                this.setOncomplete("javascript:alert('SELECCIONE UN CURSO');");
            } else if (this.getNuevAsisDocente().getReg_hora() == 0 || this.getNuevAsisDocente().getSal_hora() == 0) {
                this.setOncomplete("javascript:alert('INGRESE HORAS');");
            } else if (ts_reg.compareTo(ts_sal) > 0) {
                this.setOncomplete("javascript:alert('LA HORA DE INICIO NO PUEDE SER MAYOR QUE LA DE FIN');");
            } else if (this.getNuevAsisDocente().getN_tipasis_code().equalsIgnoreCase("0")) {
                this.setOncomplete("javascript:alert('INGRESE UN TIPO DE ASISTENCIA');");
            } else if (this.getNuevAsisDocente().getN_tipses_code().equalsIgnoreCase("0")) {
                this.setOncomplete("javascript:alert('INGRESE UN TIPO DE SESION');");
            } else {
                nuevaSesion();
                this.setOncomplete("javascript:alert('SE INGRESO LA NUEVA SESSION');"
                        + "Richfaces.hideModalPanel('mpNuevaSesion')");
            }
        } catch (NullPointerException npe) {
            this.setOncomplete("javascript:alert('INGRESE FECHAS');");
        }
    }
    
    public void validarHoraAdicional() {
        this.setOncomplete("");
        try {
            Date d1 = this.getNuevaHoraAdicional().getN_fsesion();

            Date d_reg = new Date(d1.getTime());
            d_reg.setHours(this.getNuevaHoraAdicional().getReg_hora());
            d_reg.setMinutes(this.getNuevaHoraAdicional().getReg_min());
            this.getNuevaHoraAdicional().setN_fsesion_ini(d_reg);

            Date d_sal = new Date(d1.getTime());
            d_sal.setHours(this.getNuevaHoraAdicional().getSal_hora());
            d_sal.setMinutes(this.getNuevaHoraAdicional().getSal_min());
            this.getNuevaHoraAdicional().setN_fsesion_fin(d_sal);

            String ts_reg = formatTimeStamp.format(d_reg);
            String ts_sal = formatTimeStamp.format(d_sal);

            if (this.getNuevaHoraAdicional().getN_doc_id() == 0) {
                this.setOncomplete("javascript:alert('SELECCIONE UN DOCENTE');");
            } else
            /*if (this.getS_doc_id() == 0) {
                this.setOncomplete("javascript:alert('SELECCIONE UN DOCENTE');");
            } else*/
            if (this.getNuevaHoraAdicional().getN_especialidad_code() == 0) {
                this.setOncomplete("javascript:alert('SELECCIONE UNA ESPECIALIDAD');");
                
            }  else if (this.getNuevaHoraAdicional().getN_seccion_code().equalsIgnoreCase("0")) {
                this.setOncomplete("javascript:alert('SELECCIONE UNA SECCION');");
            }  else if (this.getNuevaHoraAdicional().getReg_hora() == 0 || this.getNuevaHoraAdicional().getSal_hora() == 0) {
                this.setOncomplete("javascript:alert('INGRESE HORAS');");
            } else if (ts_reg.compareTo(ts_sal) > 0) {
                this.setOncomplete("javascript:alert('LA HORA DE INICIO NO PUEDE SER MAYOR QUE LA DE FIN');");
            } else if (this.getNuevaHoraAdicional().getN_tipasis_code().equalsIgnoreCase("0")) {
                this.setOncomplete("javascript:alert('INGRESE UN TIPO DE ASISTENCIA');");
            } else if (this.getNuevaHoraAdicional().getN_tipses_code().equalsIgnoreCase("0")) {
                this.setOncomplete("javascript:alert('INGRESE UN TIPO DE SESION');");
            } else {
                horaAdicional();
                this.setOncomplete("javascript:alert('SE INGRESO LA NUEVA SESSION');"
                        + "Richfaces.hideModalPanel('mpHoraAdicional')");
            }
        } catch (NullPointerException npe) {
            this.setOncomplete("javascript:alert('INGRESE FECHAS');");
        }
    }

    private void nuevaSesion() {
        try {
            HSAsistenciaDocenteDAO dao = (HSAsistenciaDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoAsistenciaDocente");

            AcSesionAsistencia sesasis = new AcSesionAsistencia();

            sesasis.setAcSeccion(new AcSeccion(this.getNuevAsisDocente().getN_curso_seccion()));
            sesasis.setSesFechaSesion(this.getNuevAsisDocente().getN_fsesion());
            sesasis.setSesFechaRegistro(this.getNuevAsisDocente().getN_fsesion());
            sesasis.setSesFechaInicio(Timestamp.valueOf(formatTimeStamp.format(this.getNuevAsisDocente().getN_fsesion_ini())));
            sesasis.setSesFechaFin(Timestamp.valueOf(formatTimeStamp.format(this.getNuevAsisDocente().getN_fsesion_fin())));
            sesasis.setSesActivo("1");
            sesasis.setSesTimeTolerancia(20);
            sesasis.setSesTimeAntes(20);
            sesasis.setSesTimeTope(30);
            sesasis.setAulId(0);
            sesasis.setSesEstado("044001");
            sesasis.setSesFechaSalida(null);
            sesasis.setDocId(this.getNuevAsisDocente().getN_doc_id());
            sesasis.setDocIdBk(this.getNuevAsisDocente().getN_doc_id());
            sesasis.setSesTipo(this.getNuevAsisDocente().getN_tipses_code());
            sesasis.setSesTipoAsistencia(this.getNuevAsisDocente().getN_tipasis_code());

            dao.ingresarSesionAsistencia(sesasis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void horaAdicional() {
        try {
            HSAsistenciaDocenteDAO dao = (HSAsistenciaDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoAsistenciaDocente");

            AcSesionAsistencia sesasis = new AcSesionAsistencia();
            //this.getNuevAsisDocente().getN_curso_seccion()
            
            sesasis.setAcSeccion(new AcSeccion(this.getNuevaHoraAdicional().getN_curso_seccion()));
            sesasis.setSesFechaSesion(this.getNuevaHoraAdicional().getN_fsesion());
            sesasis.setSesFechaRegistro(this.getNuevaHoraAdicional().getN_fsesion());
            sesasis.setSesFechaInicio(Timestamp.valueOf(formatTimeStamp.format(this.getNuevaHoraAdicional().getN_fsesion_ini())));
            sesasis.setSesFechaFin(Timestamp.valueOf(formatTimeStamp.format(this.getNuevaHoraAdicional().getN_fsesion_fin())));
            sesasis.setSesActivo("1");
            sesasis.setSesTimeTolerancia(20);
            sesasis.setSesTimeAntes(20);
            sesasis.setSesTimeTope(30);
            sesasis.setAulId(0);
            sesasis.setSesEstado("044001");
            sesasis.setSesFechaSalida(null);
            sesasis.setDocId(this.getNuevaHoraAdicional().getN_doc_id());
            sesasis.setDocIdBk(this.getNuevaHoraAdicional().getN_doc_id());
            //sesasis.setDocId(this.getS_doc_id());
            //sesasis.setDocIdBk(this.getS_doc_id());
            sesasis.setSesTipo(this.getNuevaHoraAdicional().getN_tipses_code());
            sesasis.setSesTipoAsistencia(this.getNuevaHoraAdicional().getN_tipasis_code());

            dao.ingresarSesionAsistencia(sesasis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
