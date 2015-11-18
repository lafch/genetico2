package net.uch.academica.managedBeans;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIParameter;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import net.uch.academica.hibernateSpringDao.HSDisponibilidadDAO;
import net.uch.academica.hibernateSpringDao.HSDocenteDAO;
import net.uch.academica.hibernateSpringDao.HSTurnoDAO;
import net.uch.academica.hibernateSpringDao.HSTurnoDetalleDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcDisponibilidad;
import net.uch.mapping.AcDocente;
import net.uch.tablaSistema.hibernateSpringDao.HSSemestreDAO;
import net.uch.tablaSistema.managedBeans.bUsuario;
import org.springframework.dao.DataAccessException;

public class bDisponibilidad {

    private SelectItem[] comboTurno;
    private int turno_id;
    private String inicio_hor;
    private String fin_hor;
    private List listaDatos;
    private int id_hor;
    private static int indice = 0;
    private String lun_val = "";
    private String lun_val_aux = "";
    private String mar_val = "";
    private String mar_val_aux = "";
    private String mier_val = "";
    private String mier_val_aux = "";
    private String juev_val = "";
    private String juev_val_aux = "";
    private String vier_val = "";
    private String vier_val_aux = "";
    private String sab_val = "";
    private String sab_val_aux = "";
    private String dom_val = "";
    private String dom_val_aux = "";
    private String ruta_imagen1 = "";
    private String ruta_imagen2 = "";
    private String ruta_imagen3 = "";
    private String ruta_imagen4 = "";
    private String ruta_imagen5 = "";
    private String ruta_imagen6 = "";
    private String ruta_imagen7 = "";
    private boolean con_dis = false;
    ///
    private int dis_id;
    private String dis_observacion;
    private String dis_activo;
    private String dis_dia;
    private int dis_doc_id;
    private String mensaje = "";
    //public static List<AcDisponibilidad> listaDisponibilidad=new ArrayList<AcDisponibilidad>();
    private static List<bDisponibilidad> detalle = new ArrayList<bDisponibilidad>();
    public static Set<AcDisponibilidad> listaDisponibilidad = new LinkedHashSet<AcDisponibilidad>();

    public bDisponibilidad() throws SQLException, IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession(false);
        bUsuario usu = (bUsuario) session1.getAttribute("usuario");
        if (usu != null) {

            int sw = 0;
            String val = "";

            //System.out.println("Entro al constructor");
            HSDisponibilidadDAO dao1 = (HSDisponibilidadDAO) ServiceFinder.findBean("HibernateSpringDaoDisponibilidad");
            List lista = null;
            lista = dao1.verificar_conf_dis();
            if (lista != null) {
                for (int k = 0; k < lista.size(); k++) {
                    Object[] obj = new Object[lista.size()];
                    obj = (Object[]) lista.get(0);
                    val = obj[1].toString();
                }
                //System.out.println("El valor de val es:"+val);
                if (val.equals("1")) {
                    this.setCon_dis(false);
                } else {
                    this.setCon_dis(true);
                }
            }
        } else {
            try {
                throw new Exception();
            } catch (Exception ex) {
                Logger.getLogger(bDisponibilidad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getMier_val() {
        return mier_val;
    }

    public void setMier_val(String mier_val) {
        this.mier_val = mier_val;
    }

    public String getJuev_val() {
        return juev_val;
    }

    public void setJuev_val(String juev_val) {
        this.juev_val = juev_val;
    }

    public String getVier_val() {
        return vier_val;
    }

    public void setVier_val(String vier_val) {
        this.vier_val = vier_val;
    }

    public String getSab_val() {
        return sab_val;
    }

    public void setSab_val(String sab_val) {
        this.sab_val = sab_val;
    }

    public String getDom_val() {
        return dom_val;
    }

    public void setDom_val(String dom_val) {
        this.dom_val = dom_val;
    }

    public String getMar_val() {
        return mar_val;
    }

    public void setMar_val(String mar_val) {
        this.mar_val = mar_val;
    }

    public SelectItem[] getComboTurno() throws SQLException, DataAccessException, IOException {
        int sem_id = 0;
        HSSemestreDAO dao1 = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
        List lista_sem = dao1.seleccionarVigente();
        for (int j = 0; j < lista_sem.size(); j++) {
            sem_id = ((net.uch.mapping.AcSemestre) lista_sem.get(j)).getId();
        }
        //System.out.println("El semestre Vigente Actual es:"+sem_id);

        HSTurnoDAO dao = (HSTurnoDAO) ServiceFinder.findBean("HibernateSpringDaoTurno");
        List lista = dao.seleccionarTurno(sem_id);
        //System.out.println("El tamano de la lista que retorna es:"+lista.size());
        SelectItem[] cmbTurno = new SelectItem[lista.size() + 1];
        cmbTurno[0] = new SelectItem(99, "[----Seleccione----]");
        for (int i = 0; i < cmbTurno.length - 1; i++) {
            cmbTurno[i + 1] = new SelectItem(((net.uch.mapping.AcTurno) lista.get(i)).getId(), ((net.uch.mapping.AcTurno) lista.get(i)).getTurNombre());
        }

        return cmbTurno;
    }

    public void setComboTurno(SelectItem[] comboTurno) {
        this.comboTurno = comboTurno;
    }

    public int getTurno_id() {
        return turno_id;
    }

    public void setTurno_id(int turno_id) {
        //System.out.println("Setenado el valor de turno-id con el valor de"+turno_id);
        this.turno_id = turno_id;
    }

    public List getListaDatos() {
        return listaDatos;
    }

    public void setListaDatos(List listaDatos) {
        this.listaDatos = listaDatos;
    }

    public void setearTurnoDetalle() throws SQLException, IOException {
        this.setListaDatos(null);
        int id_turno = 0;
        String temp = "";
        id_turno = this.getTurno_id();
        int sw1 = 0;
        int sw2 = 0;
        int sw3 = 0;
        int sw4 = 0;
        int sw5 = 0;
        int sw6 = 0;
        int sw7 = 0;
        /*
        for(int k=0;k<detalle.size();k++)
        { detalle.remove(k);
        }
         */
        detalle.clear();
        setIndice(0);
        this.setMensaje("");

        System.out.println("Limpiando lista Disponibilidad;");
        listaDisponibilidad.clear();
        //System.out.println("El tamanod de detalle es:"+detalle.size());

        HSTurnoDetalleDAO dao = (HSTurnoDetalleDAO) ServiceFinder.findBean("HibernateSpringDaoTurnoDetalle");
        //System.out.println("El valor de turno es:"+id_turno);
        List lista = dao.seleccionarTurnoDetalle(id_turno);
        listaDatos = new ArrayList();
        bDisponibilidad bD;
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession(false);
        bUsuario usu = (bUsuario) session1.getAttribute("usuario");


        if (usu != null) {	//System.out.println("El valor del Usuario es:"+usu.getUsu_usu());
            //System.out.println("El valor del Usuario_id es:"+usu.getId_usu());
            //System.out.println("El valor del Tipo_Usuario_id es:"+usu.getUsu_tipo_id());

            for (int i = 0; i < lista.size(); i++) {	//Object obj[]= new Object[lista.size()];
                Object obj[] = (Object[]) lista.get(i);
                bD = new bDisponibilidad();
                bD.setId_hor(Integer.parseInt(obj[0].toString()));
                bD.setInicio_hor(obj[1].toString());

                //Verifico si tiene o no disponibilidad Asignada para ese horario
                HSDisponibilidadDAO dao1 = (HSDisponibilidadDAO) ServiceFinder.findBean("HibernateSpringDaoDisponibilidad");
                //sw=dao1.verificar_disponibilidad(usu.getUsu_tipo_id(),Integer.parseInt(obj[0].toString()));
                sw1 = dao1.verificar_disponibilidad(usu.getUsu_tipo_id(), Integer.parseInt(obj[0].toString()), "016001");
                if (sw1 == 1) {
                    bD.setLun_val("1");
                    bD.setLun_val_aux("1");
                    bD.setRuta_imagen1("/Imagenes/actions/salir.png");
                } else {
                    bD.setLun_val("0");
                    bD.setLun_val_aux("0");
                    bD.setRuta_imagen1("/Imagenes/actions/horario.png");
                }

                sw2 = dao1.verificar_disponibilidad(usu.getUsu_tipo_id(), Integer.parseInt(obj[0].toString()), "016002");
                if (sw2 == 1) {
                    bD.setMar_val("1");
                    bD.setMar_val_aux("1");
                    bD.setRuta_imagen2("/Imagenes/actions/salir.png");
                } else {
                    bD.setMar_val("0");
                    bD.setMar_val_aux("0");
                    bD.setRuta_imagen2("/Imagenes/actions/horario.png");
                }

                sw3 = dao1.verificar_disponibilidad(usu.getUsu_tipo_id(), Integer.parseInt(obj[0].toString()), "016003");
                if (sw3 == 1) {
                    bD.setMier_val("1");
                    bD.setMier_val_aux("1");
                    bD.setRuta_imagen3("/Imagenes/actions/salir.png");
                } else {
                    bD.setMier_val("0");
                    bD.setMier_val_aux("0");
                    bD.setRuta_imagen3("/Imagenes/actions/horario.png");
                }

                sw4 = dao1.verificar_disponibilidad(usu.getUsu_tipo_id(), Integer.parseInt(obj[0].toString()), "016004");
                if (sw4 == 1) {
                    bD.setJuev_val("1");
                    bD.setJuev_val_aux("1");
                    bD.setRuta_imagen4("/Imagenes/actions/salir.png");
                } else {
                    bD.setJuev_val("0");
                    bD.setJuev_val_aux("0");
                    bD.setRuta_imagen4("/Imagenes/actions/horario.png");
                }

                sw5 = dao1.verificar_disponibilidad(usu.getUsu_tipo_id(), Integer.parseInt(obj[0].toString()), "016005");
                if (sw5 == 1) {
                    bD.setVier_val("1");
                    bD.setVier_val_aux("1");
                    bD.setRuta_imagen5("/Imagenes/actions/salir.png");
                } else {
                    bD.setVier_val("0");
                    bD.setVier_val_aux("0");
                    bD.setRuta_imagen5("/Imagenes/actions/horario.png");
                }
                sw6 = dao1.verificar_disponibilidad(usu.getUsu_tipo_id(), Integer.parseInt(obj[0].toString()), "016006");
                if (sw6 == 1) {
                    bD.setSab_val("1");
                    bD.setSab_val_aux("1");
                    bD.setRuta_imagen6("/Imagenes/actions/salir.png");
                } else {
                    bD.setSab_val("0");
                    bD.setSab_val_aux("0");
                    bD.setRuta_imagen6("/Imagenes/actions/horario.png");
                }
                sw7 = dao1.verificar_disponibilidad(usu.getUsu_tipo_id(), Integer.parseInt(obj[0].toString()), "016007");
                if (sw7 == 1) {
                    bD.setDom_val("1");
                    bD.setDom_val_aux("1");
                    bD.setRuta_imagen7("/Imagenes/actions/salir.png");
                } else {
                    bD.setDom_val("0");
                    bD.setDom_val_aux("0");
                    bD.setRuta_imagen7("/Imagenes/actions/horario.png");
                }
                int j = i + 1;
                Object obj1[];
                if (j == lista.size()) {
                    obj1 = (Object[]) lista.get(i);
                } else {
                    obj1 = (Object[]) lista.get(j);
                }
                bD.setFin_hor(obj1[1].toString());

                listaDatos.add(bD);
            }
            this.setListaDatos(listaDatos);
            //System.out.println("El valor de turno_iddddddddddddddd es"+this.getTurno_id());

        } else {
            //System.out.println("EL objeto es nulo");
        }
    }

    public void Lunes(ActionEvent event) throws SQLException, IOException {	//System.out.println("El valor de turno_id es"+this.getId_hor());
        //System.out.println("Selecionnar Lunes");
        this.setMensaje("");
        int sw = 0;
        int indice_ = 0;
        int temp_indice = 0;
        int aux = 0;
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        UIParameter id_hora = (UIParameter) event.getComponent().findComponent("hor_id");
        UIParameter val_lunes = (UIParameter) event.getComponent().findComponent("v_l");
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession(false);
        bUsuario usu = (bUsuario) session1.getAttribute("usuario");
        if (usu != null) {
            HSDisponibilidadDAO dao = (HSDisponibilidadDAO) ServiceFinder.findBean("HibernateSpringDaoDisponibilidad");
            // System.out.println("El valor de Lunes es:"+val_lunes.getValue().toString());
            this.setLun_val_aux(val_lunes.getValue().toString());
            //Primero verifico si es el primer elemento que selecciono
            // System.out.println("El valor de Indice es:"+this.getIndice());
            if (detalle.size() < 1) {
                bDisponibilidad dis = new bDisponibilidad();
                dis.setTurno_id(Integer.parseInt(id_hora.getValue().toString()));
                //indice_=dis.getIndice()+1;
                //dis.setIndice(indice_);
                //System.out.println("El valor del indice es:"+indice_);
                //Verifico si para ese horario ya se encontraba selecionado o no
                if (getLun_val_aux().equalsIgnoreCase("0")) {
                    //System.out.println("Lo Adiciono a la Lista detalle con Lun_val 1");
                    dis.setLun_val_aux("1");
                    //System.out.println("Setenado Ruta imagen 1 con /Imagenes/actions/salir.png");
                    this.setRuta_imagen1("/Imagenes/actions/salir.png");
                } else {
                    //System.out.println("Lo Adiciono a la Lista detalle con Lun_val 0");
                    dis.setLun_val_aux("0");
                    this.setRuta_imagen1("/Imagenes/actions/horario.png");
                    //System.out.println("Setenado Ruta imagen 1 con /Imagenes/actions/horario.png");
                }
                dis.setDis_dia("016001");
                detalle.add(dis);

            } else {	//System.out.println("Entro al else");
                //Verifico si para ese horario ya se encontraba selecionado o no
                for (int k = 0; k < detalle.size(); k++) {
                    if (detalle.get(k).getTurno_id() == Integer.parseInt(id_hora.getValue().toString()) && detalle.get(k).getDis_dia().equalsIgnoreCase("016001")) {
                        sw = 1;
                        k = detalle.size();
                    } else {
                        temp_indice++;
                        sw = 0;
                    }

                }
                if (sw == 1) {
                    //System.out.println("Ese Elemento ya existe solo lo elimino");
                    //System.out.println("El indice del elemento a eliminar es:"+temp_indice);
                    detalle.remove(temp_indice);
                    //System.out.println("El valor de la rutaimagen  es"+this.getRuta_imagen1());
                    if (this.getRuta_imagen1().equalsIgnoreCase("/Imagenes/actions/salir.png")) {
                        this.setRuta_imagen1("/Imagenes/actions/horario.png");
                        //System.out.println("Setenado Ruta imagen 1 con /Imagenes/actions/horario.png");
                    } else {
                        this.setRuta_imagen1("/Imagenes/actions/salir.png");
                        //System.out.println("Setenado Ruta imagen 1 con /Imagenes/actions/salir.png");
                    }
                } else {
                    bDisponibilidad dis = new bDisponibilidad();
                    dis.setTurno_id(Integer.parseInt(id_hora.getValue().toString()));
                    indice = dis.getIndice() + 1;
                    //System.out.println("El valor del indice es:"+indice);
                    dis.setIndice(indice);
                    if (this.getLun_val_aux().toString() == "0") {
                        //System.out.println("Ese Elemento lo inserto a la lista inserto a la lista con Lun_val=1");
                        dis.setLun_val_aux("1");
                        this.setRuta_imagen1("/Imagenes/actions/salir.png");
                    } else {
                        //System.out.println("Ese Elemento lo inserto a la lista con Lun_val=0");
                        dis.setLun_val_aux("0");
                        this.setRuta_imagen1("/Imagenes/actions/horario.png");
                    }
                    dis.setDis_dia("016001");
                    detalle.add(dis);
                }
            }
        } else {
            //System.out.println("Objeto session es null");
        }

        //System.out.println("el valor de Usuario Id es:"+usu.getId());
    }

    public void Martes(ActionEvent event) throws SQLException, IOException {
        //System.out.println("Selecionnar Martes");
        this.setMensaje("");
        int sw = 0;
        int indice_ = 0;
        int temp_indice = 0;
        int aux = 0;
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        UIParameter id_hora = (UIParameter) event.getComponent().findComponent("hor_id");
        UIParameter val_martes = (UIParameter) event.getComponent().findComponent("v_m");
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession(false);
        bUsuario usu = (bUsuario) session1.getAttribute("usuario");
        if (usu != null) {
            HSDisponibilidadDAO dao = (HSDisponibilidadDAO) ServiceFinder.findBean("HibernateSpringDaoDisponibilidad");
            //System.out.println("El valor de Martes es:"+val_martes.getValue().toString());
            this.setMar_val_aux(val_martes.getValue().toString());
            //Primero verifico si es el primer elemento que selecciono
            //System.out.println("El valor de Indice es:"+this.getIndice());
            if (detalle.size() < 1) {
                bDisponibilidad dis = new bDisponibilidad();
                dis.setTurno_id(Integer.parseInt(id_hora.getValue().toString()));
                indice_ = dis.getIndice() + 1;
                dis.setIndice(indice_);
                System.out.println("El valor del indice es:" + indice_);
                //Verifico si para ese horario ya se encontraba selecionado o no
                if (this.getMar_val_aux().equalsIgnoreCase("0")) {
                    //System.out.println("Lo Adiciono a la Lista detalle con Mar_val 1");
                    dis.setMar_val_aux("1");
                    this.setRuta_imagen2("/Imagenes/actions/salir.png");
                } else {
                    //System.out.println("Lo Adiciono a la Lista detalle con Mar_val 0");
                    dis.setMar_val_aux("0");
                    this.setRuta_imagen2("/Imagenes/actions/horario.png");
                }
                dis.setDis_dia("016002");
                detalle.add(dis);
            } else {	//System.out.println("Entro al else");
                //Verifico si para ese horario ya se encontraba selecionado o no
                for (int k = 0; k < detalle.size(); k++) {
                    if (detalle.get(k).getTurno_id() == Integer.parseInt(id_hora.getValue().toString()) && detalle.get(k).getDis_dia().equalsIgnoreCase("016002")) {
                        System.out.println("Ya Existe");
                        System.out.println("El valor de k ES:" + k);
                        System.out.println(detalle.get(k).getTurno_id() + "=" + Integer.parseInt(id_hora.getValue().toString()));
                        System.out.println("El Dia dia es:");
                        System.out.println(detalle.get(k).getDis_dia());
                        sw = 1;
                        k = detalle.size();
                    } else {
                        temp_indice++;
                        sw = 0;
                    }

                }
                if (sw == 1) {
                    //System.out.println("Ese Elemto ya existe solo lo elimino");
                    //System.out.println("El indice del elemento a eliminar es:"+temp_indice);
                    detalle.remove(temp_indice);
                    //System.out.println("El valor de la rutaimagen  es"+this.getRuta_imagen2());
                    if (getRuta_imagen2().equalsIgnoreCase("/Imagenes/actions/salir.png")) {
                        this.setRuta_imagen2("/Imagenes/actions/horario.png");
                    } else {
                        this.setRuta_imagen2("/Imagenes/actions/salir.png");
                    }
                } else {
                    bDisponibilidad dis = new bDisponibilidad();
                    dis.setTurno_id(Integer.parseInt(id_hora.getValue().toString()));
                    indice = dis.getIndice() + 1;
                    //System.out.println("El valor del indice es:"+indice);
                    dis.setIndice(indice);
                    if (this.getMar_val_aux().toString() == "0") {
                        //System.out.println("Ese Elemto lo inserto a la lista inserto a la lista con Mar_val=1");
                        dis.setMar_val_aux("1");
                        this.setRuta_imagen2("/Imagenes/actions/salir.png");
                    } else {
                        //System.out.println("Ese Elemto lo inserto a la lista con Mar_val=0");
                        dis.setMar_val_aux("0");
                        this.setRuta_imagen2("/Imagenes/actions/horario.png");
                    }
                    dis.setDis_dia("016002");
                    detalle.add(dis);
                }
            }
        } else {
            System.out.println("Objeto session es null");
        }
    }

    public void Miercoles(ActionEvent event) throws SQLException, IOException {
        //System.out.println("Selecionnar Miercoles");
        this.setMensaje("");
        int sw = 0;
        int indice_ = 0;
        int temp_indice = 0;
        int aux = 0;
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        UIParameter id_hora = (UIParameter) event.getComponent().findComponent("hor_id");
        UIParameter val_miercoles = (UIParameter) event.getComponent().findComponent("v_mi");
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession(false);
        bUsuario usu = (bUsuario) session1.getAttribute("usuario");
        if (usu != null) {
            HSDisponibilidadDAO dao = (HSDisponibilidadDAO) ServiceFinder.findBean("HibernateSpringDaoDisponibilidad");
            //System.out.println("El valor de Miercoles es:"+val_miercoles.getValue().toString());
            this.setMier_val_aux(val_miercoles.getValue().toString());
            //Primero verifico si es el primer elemento que selecciono
            //System.out.println("El valor de Indice es:"+this.getIndice());
            if (detalle.size() < 1) {
                bDisponibilidad dis = new bDisponibilidad();
                dis.setTurno_id(Integer.parseInt(id_hora.getValue().toString()));
                indice_ = dis.getIndice() + 1;
                dis.setIndice(indice_);
                //System.out.println("El valor del indice es:"+indice_);
                //Verifico si para ese horario ya se encontraba selecionado o no
                if (this.getMier_val_aux().equalsIgnoreCase("0")) {
                    //System.out.println("Lo Adiciono a la Lista detalle con Mier_val 1");
                    dis.setMier_val_aux("1");
                    this.setRuta_imagen3("/Imagenes/actions/salir.png");
                } else {
                    //System.out.println("Lo Adiciono a la Lista detalle con Mier_val 0");
                    dis.setMier_val_aux("0");
                    this.setRuta_imagen3("/Imagenes/actions/horario.png");
                }
                dis.setDis_dia("016003");
                detalle.add(dis);
            } else {	//System.out.println("Entro al else");
                //Verifico si para ese horario ya se encontraba selecionado o no
                for (int k = 0; k < detalle.size(); k++) {
                    if (detalle.get(k).getTurno_id() == Integer.parseInt(id_hora.getValue().toString()) && detalle.get(k).getDis_dia() == "016003") {
                        sw = 1;
                        k = detalle.size();
                    } else {
                        temp_indice++;
                        sw = 0;
                    }

                }
                if (sw == 1) {
                    //System.out.println("Ese Elemto ya existe solo lo elimino");
                    //System.out.println("El indice del elemento a eliminar es:"+temp_indice);
                    detalle.remove(temp_indice);
                    //System.out.println("El valor de la rutaimagen  es"+this.getRuta_imagen3());
                    if (this.getRuta_imagen3() == "/Imagenes/actions/salir.png") {
                        this.setRuta_imagen3("/Imagenes/actions/horario.png");
                    } else {
                        this.setRuta_imagen3("/Imagenes/actions/salir.png");
                    }
                } else {
                    bDisponibilidad dis = new bDisponibilidad();
                    dis.setTurno_id(Integer.parseInt(id_hora.getValue().toString()));
                    indice = dis.getIndice() + 1;
                    //System.out.println("El valor del indice es:"+indice);
                    dis.setIndice(indice);
                    if (this.getMier_val_aux().toString() == "0") {
                        //System.out.println("Ese Elemto lo inserto a la lista inserto a la lista con Mier_val=1");
                        dis.setMier_val_aux("1");
                        this.setRuta_imagen3("/Imagenes/actions/salir.png");
                    } else {
                        //System.out.println("Ese Elemto lo inserto a la lista con Mier_val=0");
                        dis.setMier_val_aux("0");
                        this.setRuta_imagen3("/Imagenes/actions/horario.png");
                    }
                    dis.setDis_dia("016003");
                    detalle.add(dis);
                }
            }
        } else {
            System.out.println("Objeto session es null");
        }
    }

    public void Jueves(ActionEvent event) throws SQLException, IOException {
        //System.out.println("Selecionnar Jueves");
        this.setMensaje("");
        int sw = 0;
        int indice_ = 0;
        int temp_indice = 0;
        int aux = 0;
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        UIParameter id_hora = (UIParameter) event.getComponent().findComponent("hor_id");
        UIParameter val_jueves = (UIParameter) event.getComponent().findComponent("v_j");
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession(false);
        bUsuario usu = (bUsuario) session1.getAttribute("usuario");
        if (usu != null) {
            HSDisponibilidadDAO dao = (HSDisponibilidadDAO) ServiceFinder.findBean("HibernateSpringDaoDisponibilidad");
            // System.out.println("El valor de jueves es:"+val_jueves.getValue().toString());
            this.setJuev_val_aux(val_jueves.getValue().toString());
            //Primero verifico si es el primer elemento que selecciono
            //System.out.println("El valor de Indice es:"+this.getIndice());
            if (detalle.size() < 1) {
                bDisponibilidad dis = new bDisponibilidad();
                dis.setTurno_id(Integer.parseInt(id_hora.getValue().toString()));
                indice_ = dis.getIndice() + 1;
                dis.setIndice(indice_);
                //System.out.println("El valor del indice es:"+indice_);
                //Verifico si para ese horario ya se encontraba selecionado o no
                if (this.getJuev_val_aux().toString() == "0") {
                    //System.out.println("Lo Adiciono a la Lista detalle con Juev_val 1");
                    dis.setJuev_val_aux("1");
                    this.setRuta_imagen4("/Imagenes/actions/salir.png");
                } else {
                    //System.out.println("Lo Adiciono a la Lista detalle con Juev_val 0");
                    dis.setJuev_val_aux("0");
                    this.setRuta_imagen4("/Imagenes/actions/horario.png");
                }
                dis.setDis_dia("016004");
                detalle.add(dis);
            } else {	//System.out.println("Entro al else");
                //Verifico si para ese horario ya se encontraba selecionado o no
                for (int k = 0; k < detalle.size(); k++) {
                    if (detalle.get(k).getTurno_id() == Integer.parseInt(id_hora.getValue().toString()) && detalle.get(k).getDis_dia() == "016004") {
                        sw = 1;
                        k = detalle.size();
                    } else {
                        temp_indice++;
                        sw = 0;
                    }

                }
                if (sw == 1) {
                    //System.out.println("Ese Elemto ya existe solo lo elimino");
                    //System.out.println("El indice del elemento a eliminar es:"+temp_indice);
                    detalle.remove(temp_indice);
                    //System.out.println("El valor de la rutaimagen  es"+this.getRuta_imagen4());
                    if (this.getRuta_imagen4().equalsIgnoreCase("/Imagenes/actions/salir.png")) {
                        this.setRuta_imagen4("/Imagenes/actions/horario.png");
                    } else {
                        this.setRuta_imagen4("/Imagenes/actions/salir.png");
                    }
                } else {
                    bDisponibilidad dis = new bDisponibilidad();
                    dis.setTurno_id(Integer.parseInt(id_hora.getValue().toString()));
                    indice = dis.getIndice() + 1;
                    //System.out.println("El valor del indice es:"+indice);
                    dis.setIndice(indice);
                    if (this.getJuev_val_aux().toString() == "0") {
                        //System.out.println("Ese Elemto lo inserto a la lista inserto a la lista con Jueves_val=1");
                        dis.setJuev_val_aux("1");
                        this.setRuta_imagen4("/Imagenes/actions/salir.png");
                    } else {
                        //System.out.println("Ese Elemto lo inserto a la lista con Juev_val=0");
                        dis.setJuev_val_aux("0");
                        this.setRuta_imagen4("/Imagenes/actions/horario.png");
                    }
                    dis.setDis_dia("016004");
                    detalle.add(dis);
                }
            }
        } else {
            System.out.println("Objeto session es null");
        }
    }

    public void Viernes(ActionEvent event) throws SQLException, IOException {
        //System.out.println("Selecionnar Viernes");
        this.setMensaje("");
        int sw = 0;
        int indice_ = 0;
        int temp_indice = 0;
        int aux = 0;
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        UIParameter id_hora = (UIParameter) event.getComponent().findComponent("hor_id");
        UIParameter val_viernes = (UIParameter) event.getComponent().findComponent("v_v");
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession(false);
        bUsuario usu = (bUsuario) session1.getAttribute("usuario");
        if (usu != null) {
            HSDisponibilidadDAO dao = (HSDisponibilidadDAO) ServiceFinder.findBean("HibernateSpringDaoDisponibilidad");
            //System.out.println("El valor de viernes es:"+val_viernes.getValue().toString());
            this.setVier_val_aux(val_viernes.getValue().toString());
            //Primero verifico si es el primer elemento que selecciono
            //System.out.println("El valor de Indice es:"+this.getIndice());
            if (detalle.size() < 1) {
                bDisponibilidad dis = new bDisponibilidad();
                dis.setTurno_id(Integer.parseInt(id_hora.getValue().toString()));
                indice_ = dis.getIndice() + 1;
                dis.setIndice(indice_);
                //System.out.println("El valor del indice es:"+indice_);
                //Verifico si para ese horario ya se encontraba selecionado o no
                if (this.getVier_val_aux().toString() == "0") {
                    //System.out.println("Lo Adiciono a la Lista detalle con Vier_val 1");
                    dis.setVier_val_aux("1");
                    this.setRuta_imagen5("/Imagenes/actions/salir.png");
                } else {
                    //System.out.println("Lo Adiciono a la Lista detalle con Vier_val 0");
                    dis.setVier_val_aux("0");
                    this.setRuta_imagen5("/Imagenes/actions/horario.png");
                }
                dis.setDis_dia("016005");
                detalle.add(dis);
            } else {	//System.out.println("Entro al else");
                //Verifico si para ese horario ya se encontraba selecionado o no
                for (int k = 0; k < detalle.size(); k++) {
                    if (detalle.get(k).getTurno_id() == Integer.parseInt(id_hora.getValue().toString()) && detalle.get(k).getDis_dia().equalsIgnoreCase("016005")) {
                        sw = 1;
                        k = detalle.size();
                    } else {
                        temp_indice++;
                        sw = 0;
                    }

                }
                if (sw == 1) {
                    //System.out.println("Ese Elemto ya existe solo lo elimino");
                    //System.out.println("El indice del elemento a eliminar es:"+temp_indice);
                    detalle.remove(temp_indice);
                    //System.out.println("El valor de la rutaimagen  es"+this.getRuta_imagen5());
                    if (this.getRuta_imagen5() == "/Imagenes/actions/salir.png") {
                        this.setRuta_imagen5("/Imagenes/actions/horario.png");
                    } else {
                        this.setRuta_imagen5("/Imagenes/actions/salir.png");
                    }
                } else {
                    bDisponibilidad dis = new bDisponibilidad();
                    dis.setTurno_id(Integer.parseInt(id_hora.getValue().toString()));
                    indice = dis.getIndice() + 1;
                    //System.out.println("El valor del indice es:"+indice);
                    dis.setIndice(indice);
                    if (this.getVier_val_aux().toString() == "0") {
                        //System.out.println("Ese Elemto lo inserto a la lista inserto a la lista con Vier_val=1");
                        dis.setVier_val_aux("1");
                        this.setRuta_imagen5("/Imagenes/actions/salir.png");
                    } else {
                        //System.out.println("Ese Elemto lo inserto a la lista con Vier_val=0");
                        dis.setVier_val_aux("0");
                        this.setRuta_imagen5("/Imagenes/actions/horario.png");
                    }
                    dis.setDis_dia("016005");
                    detalle.add(dis);
                }
            }
        } else {
            System.out.println("Objeto session es null");
        }
    }

    public void Sabado(ActionEvent event) throws SQLException, IOException {
        System.out.println("Selecionnar Sabado");
        this.setMensaje("");
        int sw = 0;
        int indice_ = 0;
        int temp_indice = 0;
        int aux = 0;
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        UIParameter id_hora = (UIParameter) event.getComponent().findComponent("hor_id");
        UIParameter val_sabado = (UIParameter) event.getComponent().findComponent("v_s");
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession(false);
        bUsuario usu = (bUsuario) session1.getAttribute("usuario");
        if (usu != null) {
            HSDisponibilidadDAO dao = (HSDisponibilidadDAO) ServiceFinder.findBean("HibernateSpringDaoDisponibilidad");
            //System.out.println("El valor de SAbado es:"+val_sabado.getValue().toString());
            this.setSab_val_aux(val_sabado.getValue().toString());
            //Primero verifico si es el primer elemento que selecciono
            //System.out.println("El valor de Indice es:"+this.getIndice());
            if (detalle.size() < 1) {
                bDisponibilidad dis = new bDisponibilidad();
                dis.setTurno_id(Integer.parseInt(id_hora.getValue().toString()));
                indice_ = dis.getIndice() + 1;
                dis.setIndice(indice_);
                //System.out.println("El valor del indice es:"+indice_);
                //Verifico si para ese horario ya se encontraba selecionado o no
                if (this.getSab_val_aux().toString() == "0") {
                    //System.out.println("Lo Adiciono a la Lista detalle con Sab_val 1");
                    dis.setSab_val_aux("1");
                    this.setRuta_imagen6("/Imagenes/actions/salir.png");
                } else {
                    //System.out.println("Lo Adiciono a la Lista detalle con Sab_val 0");
                    dis.setSab_val_aux("0");
                    this.setRuta_imagen6("/Imagenes/actions/horario.png");
                }
                dis.setDis_dia("016006");
                detalle.add(dis);
            } else {	//System.out.println("Entro al else");
                //Verifico si para ese horario ya se encontraba selecionado o no
                for (int k = 0; k < detalle.size(); k++) {
                    if (detalle.get(k).getTurno_id() == Integer.parseInt(id_hora.getValue().toString()) && detalle.get(k).getDis_dia().equalsIgnoreCase("016006")) {
                        sw = 1;
                        k = detalle.size();
                    } else {
                        temp_indice++;
                        sw = 0;
                    }

                }
                if (sw == 1) {
                    //System.out.println("Ese Elemto ya existe solo lo elimino");
                    //System.out.println("El indice del elemento a eliminar es:"+temp_indice);
                    detalle.remove(temp_indice);
                    //System.out.println("El valor de la rutaimagen  es"+this.getRuta_imagen6());
                    if (this.getRuta_imagen6().equalsIgnoreCase("/Imagenes/actions/salir.png")) {
                        this.setRuta_imagen6("/Imagenes/actions/horario.png");
                    } else {
                        this.setRuta_imagen6("/Imagenes/actions/salir.png");
                    }
                } else {
                    bDisponibilidad dis = new bDisponibilidad();
                    dis.setTurno_id(Integer.parseInt(id_hora.getValue().toString()));
                    indice = dis.getIndice() + 1;
                    //System.out.println("El valor del indice es:"+indice);
                    dis.setIndice(indice);
                    if (this.getSab_val_aux().toString() == "0") {
                        //System.out.println("Ese Elemto lo inserto a la lista inserto a la lista con Sab_val=1");
                        dis.setSab_val_aux("1");
                        this.setRuta_imagen6("/Imagenes/actions/salir.png");
                    } else {
                        System.out.println("Ese Elemto lo inserto a la lista con Lun_val=0");
                        dis.setSab_val_aux("0");
                        this.setRuta_imagen6("/Imagenes/actions/horario.png");
                    }
                    dis.setDis_dia("016006");
                    detalle.add(dis);
                }
            }
        } else {
            System.out.println("Objeto session es null");
        }
    }

    public void Domingo(ActionEvent event) throws SQLException, IOException {
        //System.out.println("Selecionnar Domingo");
        this.setMensaje("");
        int sw = 0;
        int indice_ = 0;
        int temp_indice = 0;
        int aux = 0;
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        UIParameter id_hora = (UIParameter) event.getComponent().findComponent("hor_id");
        UIParameter val_domingo = (UIParameter) event.getComponent().findComponent("v_d");
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession(false);
        bUsuario usu = (bUsuario) session1.getAttribute("usuario");
        if (usu != null) {
            HSDisponibilidadDAO dao = (HSDisponibilidadDAO) ServiceFinder.findBean("HibernateSpringDaoDisponibilidad");
            //System.out.println("El valor de Domingo es:"+val_domingo.getValue().toString());
            this.setDom_val_aux(val_domingo.getValue().toString());
            //Primero verifico si es el primer elemento que selecciono
            //System.out.println("El valor de Indice es:"+this.getIndice());
            if (detalle.size() < 1) {
                bDisponibilidad dis = new bDisponibilidad();
                dis.setTurno_id(Integer.parseInt(id_hora.getValue().toString()));
                indice_ = dis.getIndice() + 1;
                dis.setIndice(indice_);
                //System.out.println("El valor del indice es:"+indice_);
                //Verifico si para ese horario ya se encontraba selecionado o no
                if (this.getDom_val_aux().toString() == "0") {
                    //System.out.println("Lo Adiciono a la Lista detalle con Dom_val 1");
                    dis.setDom_val_aux("1");
                    this.setRuta_imagen7("/Imagenes/actions/salir.png");
                } else {
                    //System.out.println("Lo Adiciono a la Lista detalle con Dom_val 0");
                    dis.setDom_val_aux("0");
                    this.setRuta_imagen7("/Imagenes/actions/horario.png");
                }
                dis.setDis_dia("016007");
                detalle.add(dis);
            } else {	//System.out.println("Entro al else");
                //Verifico si para ese horario ya se encontraba selecionado o no
                for (int k = 0; k < detalle.size(); k++) {
                    if (detalle.get(k).getTurno_id() == Integer.parseInt(id_hora.getValue().toString()) && detalle.get(k).getDis_dia() == "016007") {
                        sw = 1;
                        k = detalle.size();
                    } else {
                        temp_indice++;
                        sw = 0;
                    }

                }
                if (sw == 1) {
                    //System.out.println("Ese Elemto ya existe solo lo elimino");
                    //System.out.println("El indice del elemento a eliminar es:"+temp_indice);
                    detalle.remove(temp_indice);
                    //System.out.println("El valor de la rutaimagen  es"+this.getRuta_imagen7());
                    if (this.getRuta_imagen7().equalsIgnoreCase("/Imagenes/actions/salir.png")) {
                        this.setRuta_imagen7("/Imagenes/actions/horario.png");
                    } else {
                        this.setRuta_imagen7("/Imagenes/actions/salir.png");
                    }
                } else {
                    bDisponibilidad dis = new bDisponibilidad();
                    dis.setTurno_id(Integer.parseInt(id_hora.getValue().toString()));
                    indice = dis.getIndice() + 1;
                    //System.out.println("El valor del indice es:"+indice);
                    dis.setIndice(indice);
                    if (this.getDom_val_aux().toString() == "0") {
                        //System.out.println("Ese Elemto lo inserto a la lista inserto a la lista con Dom_val=1");
                        dis.setDom_val_aux("1");
                        this.setRuta_imagen7("/Imagenes/actions/salir.png");
                    } else {
                        //System.out.println("Ese Elemto lo inserto a la lista con Dom_val=0");
                        dis.setDom_val_aux("0");
                        this.setRuta_imagen7("/Imagenes/actions/horario.png");
                    }
                    dis.setDis_dia("016007");
                    detalle.add(dis);
                }
            }
        } else {
            System.out.println("Objeto session es null");
        }
    }

    public void Insertar(ActionEvent event) throws Exception {
        String page = "";
        //System.out.println("Entro al insertar");
        //System.out.println("El tamano de la lista Lunes para insertar es de_"+detalle.size());
        int sw = 0;
        int dis_id = 0;
        int tdet_id;
        String obs = "";
        int doc_id = 0;
        String dis_dia;
        String d_activo = "";
        HSDisponibilidadDAO dao = (HSDisponibilidadDAO) ServiceFinder.findBean("HibernateSpringDaoDisponibilidad");
        AcDisponibilidad Dispo = new AcDisponibilidad();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession(false);
        bUsuario usu = (bUsuario) session1.getAttribute("usuario");
        if (usu != null) {

            //this.agregar(dis_id, obs, turdet_id, doc_id, dis_activo, dis_dia)
            for (int i = 0; i < detalle.size(); i++) {
                tdet_id = detalle.get(i).getTurno_id();
                obs = "";
                doc_id = usu.getUsu_tipo_id();
                dis_dia = detalle.get(i).getDis_dia();
                List lista = null;
                lista = dao.get_iddis(usu.getUsu_tipo_id(), detalle.get(i).getTurno_id(), detalle.get(i).getDis_dia().toString());
                bDisponibilidad bD;
                if (lista != null) {
                    for (int l = 0; l < lista.size(); l++) {
                        bD = new bDisponibilidad();
                        Object[] obj = new Object[lista.size()];
                        obj = (Object[]) lista.get(l);
                        dis_id = Integer.parseInt(obj[0].toString());
                    }
                }
                if (detalle.get(i).getDis_dia().equalsIgnoreCase("016001")) {
                    if (detalle.get(i).getLun_val_aux().equalsIgnoreCase("0")) {
                        System.out.println("Actualizar");
                        d_activo = "0";
                    } else {
                        sw = dao.verificar_disponibilidad1(usu.getUsu_tipo_id(), detalle.get(i).getTurno_id(), detalle.get(i).getDis_dia().toString());
                        if (sw == 1) {
                            System.out.println("Actualizar");
                            d_activo = "1";
                        } else {
                            System.out.println("Insertar");
                            dis_id = 0;
                            d_activo = "1";
                        }

                    }
                }

                ///
                if (detalle.get(i).getDis_dia() == "016002") {
                    if (detalle.get(i).getMar_val_aux() == "0") {
                        System.out.println("Actualizar");
                        d_activo = "0";
                    } else {
                        sw = dao.verificar_disponibilidad1(usu.getUsu_tipo_id(), detalle.get(i).getTurno_id(), detalle.get(i).getDis_dia().toString());
                        if (sw == 1) {
                            System.out.println("Actualizar");
                            d_activo = "1";
                        } else {
                            System.out.println("Insertar");
                            dis_id = 0;
                            d_activo = "1";
                        }

                    }
                }
                ///
                if (detalle.get(i).getDis_dia().equalsIgnoreCase("016003")) {
                    if (detalle.get(i).getMier_val_aux().equalsIgnoreCase("0")) {
                        System.out.println("Actualizar");
                        d_activo = "0";
                    } else {
                        sw = dao.verificar_disponibilidad1(usu.getUsu_tipo_id(), detalle.get(i).getTurno_id(), detalle.get(i).getDis_dia().toString());
                        if (sw == 1) {
                            System.out.println("Actualizar");
                            d_activo = "1";
                        } else {
                            System.out.println("Insertar");
                            dis_id = 0;
                            d_activo = "1";
                        }

                    }
                }

                ///
                if (detalle.get(i).getDis_dia() == "016004") {
                    if (detalle.get(i).getJuev_val_aux() == "0") {
                        System.out.println("Actualizar");
                        d_activo = "0";
                    } else {
                        sw = dao.verificar_disponibilidad1(usu.getUsu_tipo_id(), detalle.get(i).getTurno_id(), detalle.get(i).getDis_dia().toString());
                        if (sw == 1) {
                            System.out.println("Actualizar");
                            d_activo = "1";
                        } else {
                            System.out.println("Insertar");
                            dis_id = 0;
                            d_activo = "1";
                        }

                    }
                }

                ///
                if (detalle.get(i).getDis_dia() == "016005") {
                    if (detalle.get(i).getVier_val_aux() == "0") {
                        System.out.println("Actualizar");
                        d_activo = "0";
                    } else {
                        sw = dao.verificar_disponibilidad1(usu.getUsu_tipo_id(), detalle.get(i).getTurno_id(), detalle.get(i).getDis_dia().toString());
                        if (sw == 1) {
                            System.out.println("Actualizar");
                            d_activo = "1";
                        } else {
                            System.out.println("Insertar");
                            dis_id = 0;
                            d_activo = "1";
                        }

                    }
                }


                ///

                if (detalle.get(i).getDis_dia().equalsIgnoreCase("016006")) {
                    if (detalle.get(i).getSab_val_aux().equalsIgnoreCase("0")) {
                        System.out.println("Actualizar");
                        d_activo = "0";
                    } else {
                        sw = dao.verificar_disponibilidad1(usu.getUsu_tipo_id(), detalle.get(i).getTurno_id(), detalle.get(i).getDis_dia().toString());
                        if (sw == 1) {
                            System.out.println("Actualizar");
                            d_activo = "1";
                        } else {
                            System.out.println("Insertar");
                            dis_id = 0;
                            d_activo = "1";
                        }

                    }
                }

                ///
                if (detalle.get(i).getDis_dia() == "016007") {
                    if (detalle.get(i).getDom_val_aux() == "0") {
                        System.out.println("Actualizar");
                        d_activo = "0";
                    } else {
                        sw = dao.verificar_disponibilidad1(usu.getUsu_tipo_id(), detalle.get(i).getTurno_id(), detalle.get(i).getDis_dia().toString());
                        if (sw == 1) {
                            System.out.println("Actualizar");
                            d_activo = "1";
                        } else {
                            System.out.println("Insertar");
                            dis_id = 0;
                            d_activo = "1";
                        }

                    }
                }
                ///
                agregar(dis_id, obs, tdet_id, doc_id, d_activo, dis_dia);
            }
            detalle.clear();
            setIndice(0);
            this.setMensaje("El Horario se guardo Correctamente");
            this.setTurno_id(-99);
            this.listaDatos.clear();
        } else {
            System.out.println("El objeto es nulo");
        }
        //page="Alert";
        //return page;
    }

    public void agregar(int dis_id, String obs, int turdet_id, int doc_id, String dis_activo, String dis_dia) {
        AcDisponibilidad disponibilidad = new AcDisponibilidad();
        AcDocente doc = new AcDocente();
        doc.setId(doc_id);
        System.out.println("EL dis_id antes del if es:" + dis_id);
        if (dis_id != 0) {
            System.out.println("EL dis_id a actualizar es:" + dis_id);
            disponibilidad.setId(dis_id);
        }
        disponibilidad.setDisObservacion(obs);
        disponibilidad.setTurdetId(turdet_id);
        disponibilidad.setDoc(doc);
        disponibilidad.setDisActivo(dis_activo);
        disponibilidad.setDisDia(dis_dia);
        listaDisponibilidad.add(disponibilidad);


        //listaHorario.add(horario);
        //System.out.println("listahorario:"+listaHorario.size());
        //contador++;
    }

    public String InsertarBD() throws Exception {
        String page = "";
        int doc_id = 0;
        AcDocente doc = new AcDocente();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession(false);
        bUsuario usu = (bUsuario) session1.getAttribute("usuario");
        System.out.println("Algo");
        doc_id = Integer.parseInt(String.valueOf(usu.getUsu_tipo_id()));
        System.out.println("Algo1");

        HSDocenteDAO dao = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");
        AcDocente docente = dao.seleccionarDocente(doc_id);

        doc.setId(docente.getId().intValue());
        System.out.println("1");
        doc.setDocCodigo(docente.getDocCodigo().toString());
        System.out.println("2");
        doc.setDocNombre(docente.getDocNombre().toString());
        System.out.println("3");
        //((AcDocente)docentes.get(0)).getDocFoto().toString()
        doc.setDocSituacion(docente.getDocSituacion().toString());
        System.out.println("4");
        doc.setDocPeriodoInicio(docente.getDocPeriodoInicio());
        System.out.println("5");
        doc.setDocNacimiento(docente.getDocNacimiento().toString());
        System.out.println("6");
        doc.setDocResidencia(docente.getDocResidencia().toString());
        System.out.println("7");
        doc.setDocTelefono(docente.getDocTelefono().toString());
        System.out.println("8");
        doc.setDocSexo(docente.getDocSexo().toString());
        System.out.println("9");
        doc.setDocCorreo(docente.getDocCorreo().toString());
        System.out.println("10");
        doc.setDocActivo(docente.getDocActivo().toString());
        System.out.println("11");
        //Para la Disponibilidad del Docente
        System.out.println("El tamano de la lista detalle es de " + listaDisponibilidad.size());
        doc.setAcDisponibilidads(listaDisponibilidad);
        System.out.println("12");
        //dao.insertarDisponibilidad(listaDisponibilidad);
        dao.InsertarDisponibilidad(doc);
        System.out.println("13");

        System.out.println("Limpiando lista Disponibilidad;");
        listaDisponibilidad.clear();
        page = "Alert";
        return page;
    }

    public String Volver() {
        String page = "volver";
        this.setMensaje("");
        //System.out.println("Volver a la pagina Anterior");
        this.setListaDatos(null);
        for (int k = 0; k < detalle.size(); k++) {
            detalle.remove(k);
        }
        this.setTurno_id(-99);
        return page;
    }

    public String getInicio_hor() {
        return inicio_hor;
    }

    public void setInicio_hor(String inicio_hor) {
        this.inicio_hor = inicio_hor;
    }

    public String getFin_hor() {
        return fin_hor;
    }

    public void setFin_hor(String fin_hor) {
        this.fin_hor = fin_hor;
    }

    public int getId_hor() {
        return id_hor;
    }

    public void setId_hor(int id_hor) {
        this.id_hor = id_hor;
    }

    public String getLun_val() {
        return lun_val;
    }

    public void setLun_val(String lun_val) {
        this.lun_val = lun_val;
    }

    public String getRuta_imagen1() {
        return ruta_imagen1;
    }

    public void setRuta_imagen1(String ruta_imagen) {
        this.ruta_imagen1 = ruta_imagen;
    }

    public String getRuta_imagen2() {
        return ruta_imagen2;
    }

    public void setRuta_imagen2(String ruta_imagen2) {
        this.ruta_imagen2 = ruta_imagen2;
    }

    public String getRuta_imagen3() {
        return ruta_imagen3;
    }

    public void setRuta_imagen3(String ruta_imagen3) {
        this.ruta_imagen3 = ruta_imagen3;
    }

    public String getRuta_imagen4() {
        return ruta_imagen4;
    }

    public void setRuta_imagen4(String ruta_imagen4) {
        this.ruta_imagen4 = ruta_imagen4;
    }

    public String getRuta_imagen5() {
        return ruta_imagen5;
    }

    public void setRuta_imagen5(String ruta_imagen5) {
        this.ruta_imagen5 = ruta_imagen5;
    }

    public String getRuta_imagen6() {
        return ruta_imagen6;
    }

    public void setRuta_imagen6(String ruta_imagen6) {
        this.ruta_imagen6 = ruta_imagen6;
    }

    public String getRuta_imagen7() {
        return ruta_imagen7;
    }

    public void setRuta_imagen7(String ruta_imagen7) {
        this.ruta_imagen7 = ruta_imagen7;
    }

    public String getLun_val_aux() {
        return lun_val_aux;
    }

    public void setLun_val_aux(String lun_val_aux) {
        this.lun_val_aux = lun_val_aux;
    }

    public static int getIndice() {
        return indice;
    }

    public static void setIndice(int indice) {
        bDisponibilidad.indice = indice;
    }

    public String getDis_observacion() {
        return dis_observacion;
    }

    public void setDis_observacion(String dis_observacion) {
        this.dis_observacion = dis_observacion;
    }

    public String getDis_activo() {
        return dis_activo;
    }

    public void setDis_activo(String dis_activo) {
        this.dis_activo = dis_activo;
    }

    public String getDis_dia() {
        return dis_dia;
    }

    public void setDis_dia(String dis_dia) {
        this.dis_dia = dis_dia;
    }

    public String getMar_val_aux() {
        return mar_val_aux;
    }

    public void setMar_val_aux(String mar_val_aux) {
        this.mar_val_aux = mar_val_aux;
    }

    public String getMier_val_aux() {
        return mier_val_aux;
    }

    public void setMier_val_aux(String mier_val_aux) {
        this.mier_val_aux = mier_val_aux;
    }

    public String getJuev_val_aux() {
        return juev_val_aux;
    }

    public void setJuev_val_aux(String juev_val_aux) {
        this.juev_val_aux = juev_val_aux;
    }

    public String getVier_val_aux() {
        return vier_val_aux;
    }

    public void setVier_val_aux(String vier_val_aux) {
        this.vier_val_aux = vier_val_aux;
    }

    public String getSab_val_aux() {
        return sab_val_aux;
    }

    public void setSab_val_aux(String sab_val_aux) {
        this.sab_val_aux = sab_val_aux;
    }

    public String getDom_val_aux() {
        return dom_val_aux;
    }

    public void setDom_val_aux(String dom_val_aux) {
        this.dom_val_aux = dom_val_aux;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        //System.out.println("Seteando el valor de mensaje con el valor de"+mensaje);
        this.mensaje = mensaje;
    }

    public boolean isCon_dis() {
        return con_dis;
    }

    public void setCon_dis(boolean con_dis) {
        this.con_dis = con_dis;
    }
}