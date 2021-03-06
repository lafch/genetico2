package net.uch.academica.managedBeans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.faces.component.UIParameter;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import net.uch.academica.hibernateSpringDao.HSContenidoTematicoDAO;
import net.uch.academica.hibernateSpringDao.HSPlanCurricularCursoDAO;
import net.uch.academica.hibernateSpringDao.HSPlanCurricularDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcContenidoDetalle;
import net.uch.mapping.AcContenidoTematico;
import net.uch.mapping.AcEspecialidad;
import net.uch.mapping.AcFacultad;
import net.uch.mapping.AcPlanCurso;
import net.uch.mapping.AcPlancurricular;
import net.uch.mapping.TbCatalogo;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSEspecialidadDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSFacultadDAO;
import net.uch.tablaSistema.managedBeans.bUsuario;

public class bUnidadTematica {

    private int b_facultad;
    private int b_facultad_u;
    //private static int b_facultad_aux;
    private int b_especialidad;
    private int b_especialidad_u;
    //private static int b_especialidad_aux;
    private int b_pcurricular;
    private int b_pcurricular_u;
    //private static int b_pcurricular_aux;
    private String b_ciclo;
    private int b_ciclo_u;
    private int b_ciclo_aux;
    private String ciclo_descripcion;
    private SelectItem[] comboCiclo_s;
    private String combo_ciclo_id_s = "-99";
    private SelectItem[] comboPlanCurricular_s;
    private String combo_plancurricular_id_s = "-99";
    private String plan_descripcion;
    private SelectItem[] comboFacultades_s;
    private String facultad_descripcion;
    private SelectItem[] comboEspecialidades_s;
    private String especialidad_descripcion;
    private String combo_especialidad_id_s = "-99";
    private String curso_descripcion = "";
    private int b_id;
    private int b_id_u;
    private String ciclo;
    private String nombre;
    private int creditos;
    private String tipo;
    private List listaDatos;
    private String ct_titulo;
    private String ct_titulo_u;
    private String ct_titulo_aux;
    private String ct_descripcion;
    private String ct_descripcion_u;
    private String ct_descripcion_aux;
    private int ct_semanas;
    private int ct_semanas_u;
    private int ct_semanas_aux;
    private boolean ct_vigente;
    private boolean ct_vigente_u;
    private boolean ct_vigente_aux;
    private int d_id;
    private int d_contem_id;
    private String d_contenido;
    private String d_contenido_aux;
    private String d_semana;
    private String d_semana1 = "1";
    private List listaDatosDetalle;
    public Set<AcContenidoDetalle> listaContenidoDetalle = new LinkedHashSet<AcContenidoDetalle>();
    public List<AcContenidoDetalle> listaContenidoDetalleEliminacion = new ArrayList<AcContenidoDetalle>();
    public Set<AcContenidoTematico> listaContenido = new LinkedHashSet<AcContenidoTematico>();
    private List<bUnidadTematica> detalle = new ArrayList<bUnidadTematica>();
    private List<bUnidadTematica> detalle_eliminacion = new ArrayList<bUnidadTematica>();
    private String editable = "false";
    private String ver = "true";
    private boolean editableb1 = true;
    private boolean editableb2 = false;
    private String mensajes = "";
    private String disabled = "false";
    private int contador_semana = 0;
    private int sw = 0;
    private int sw2 = 0;

    public bUnidadTematica() throws Exception {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession(false);
        bUsuario usu = (bUsuario) session1.getAttribute("usuario");
        if (usu != null) {
        } else {
            throw new Exception();
        }
    }

    public int getSw2() {
        return sw2;
    }

    public void setSw2(int sw2) {
        this.sw2 = sw2;
    }

    public int getSw() {
        return sw;
    }

    public void setSw(int sw) {
        this.sw = sw;
    }

    public String getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }

    public String getCt_titulo() {
        return ct_titulo;
    }

    public void setCt_titulo(String ct_titulo) {
        this.ct_titulo = ct_titulo;
    }

    public String getCt_descripcion() {
        return ct_descripcion;
    }

    public void setCt_descripcion(String ct_descripcion) {
        this.ct_descripcion = ct_descripcion;
    }

    public int getCt_semanas() {
        return ct_semanas;
    }

    public void setCt_semanas(int ct_semanas) {
        this.ct_semanas = ct_semanas;
    }

    public List getListaDatos() {
        return listaDatos;
    }

    public void setListaDatos(List listaDatos) {
        this.listaDatos = listaDatos;
    }

    public void setearFacultad_s() {
        this.setCombo_especialidad_id_s("" + this.getB_facultad());
        this.setCombo_plancurricular_id_s("-99");
        this.setCombo_ciclo_id_s("-99");
    }

    public void setearEspecialidad_s() {
        this.setCombo_plancurricular_id_s("" + this.getB_especialidad());
        this.setCombo_ciclo_id_s("-99");
    }

    public void setearPCurricular_s() {
        this.setCombo_ciclo_id_s("" + this.getB_pcurricular());
    }

    public void cambiarEspecialidad_s(int id) throws Exception, SQLException {
        HSEspecialidadDAO dao = (HSEspecialidadDAO) ServiceFinder.findBean("SpringHibernateDaoEspecialidad");
        List lista = dao.seleccionarEspecialidad(id);
        if (lista.size() != 0) {
            comboEspecialidades_s = new SelectItem[lista.size() + 1];
            for (int i = 0; i < comboEspecialidades_s.length - 1; i++) {
                comboEspecialidades_s[0] = new SelectItem(0, "[SELECCIONE]");
                comboEspecialidades_s[i + 1] = new SelectItem(((AcEspecialidad) lista.get(i)).getId(), ((AcEspecialidad) lista.get(i)).getEspNombre());
            }
        } else {
            comboEspecialidades_s = new SelectItem[1];
            comboEspecialidades_s[0] = new SelectItem(0, "[Seleccione]");
        }
        this.setComboEspecialidades_s(comboEspecialidades_s);
    }

    public void cambiarPlanCurricular_s(int id) throws SQLException, Exception {
        HSPlanCurricularDAO dao = (HSPlanCurricularDAO) ServiceFinder.findBean("SpringHibernateDaoPlanCurricular");
        List lista = null;
        lista = dao.seleccionarPlanActivo(id);
        if (lista.size() != 0) {
            comboPlanCurricular_s = new SelectItem[lista.size() + 1];
            comboPlanCurricular_s[0] = new SelectItem(0, "[SELECCIONE]");
            for (int i = 0; i < comboPlanCurricular_s.length - 1; i++) {
                comboPlanCurricular_s[i + 1] = new SelectItem(((AcPlancurricular) lista.get(i)).getId(), ((AcPlancurricular) lista.get(i)).getPlanDescripcion());
            }
        } else {
            comboPlanCurricular_s = new SelectItem[1];
            comboPlanCurricular_s[0] = new SelectItem(0, "[Seleccione]");
        }

    }

    public void cambiarCiclo(int id) throws SQLException, Exception {
        HSCatalogoDAO dao = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        List lista = dao.seleccionarCatalogo("006");
        HSEspecialidadDAO dao2 = (HSEspecialidadDAO) ServiceFinder.findBean("SpringHibernateDaoEspecialidad");
        List lista2 = dao2.seleccionarUnaEspecialidad(id);
        int tamano = lista.size();
        if (lista2.size() == 0) {
            comboCiclo_s = new SelectItem[1];
            comboCiclo_s[0] = new SelectItem(0, "[Seleccione]");
        } else {
            tamano = Integer.parseInt(((AcEspecialidad) lista2.get(0)).getEspNroperiodos()) + 1;
            comboCiclo_s = new SelectItem[tamano];
            comboCiclo_s[0] = new SelectItem(99, "TODOS");
            for (int k = 0; k < tamano - 1; k++) {
                comboCiclo_s[k + 1] = new SelectItem(((TbCatalogo) lista.get(k)).getCatCodigoGrupo().toString() + ((TbCatalogo) lista.get(k)).getCatCodigoElemento(), ((TbCatalogo) lista.get(k)).getCatDescripcionElemento());
            }
        }
    }

    public void Seleccionar() throws Exception, SQLException {
        int nivel_usuario = 0;
        int local_id = 0;
        listaDatos = new ArrayList();
        List lista = null;
        HSPlanCurricularCursoDAO dao = (HSPlanCurricularCursoDAO) ServiceFinder.findBean("SpringHibernateDaoPlanCurricularCurso");
        if (this.getB_pcurricular() != 0) {
            if (this.getB_ciclo().equals("99")) {
                lista = dao.seleccionarPlanCurricularCurso(this.getB_pcurricular());
            } else {
                lista = dao.seleccionarPlanCurricularCurso(this.getB_pcurricular(), this.getB_ciclo());
            }
            bUnidadTematica bUT;
            for (int i = 0; i < lista.size(); i++) {
                bUT = new bUnidadTematica();
                Object[] obj = new Object[lista.size()];
                obj = (Object[]) lista.get(i);
                bUT.setB_id(Integer.parseInt((obj[0].toString())));
                bUT.setCiclo((obj[4].toString()));
                bUT.setNombre((obj[2].toString()));
                bUT.setCreditos(Integer.parseInt((obj[5].toString())));
                bUT.setTipo((obj[6].toString()));
                listaDatos.add(bUT);
            }
        }
        this.setListaDatos(listaDatos);
    }

    public void Asignar(ActionEvent event) throws SQLException, Exception {
        this.setContador_semana(0);
        detalle.clear();
        this.setSw(0);
        this.setD_contenido("");
        listaContenidoDetalleEliminacion.clear();
        detalle_eliminacion.clear();
        int pc_id = 0;
        List list_ = null;
        UIParameter id = (UIParameter) event.getComponent().findComponent("p_id");
        this.setB_id_u(Integer.parseInt(id.getValue().toString()));
        HSContenidoTematicoDAO dao1 = (HSContenidoTematicoDAO) ServiceFinder.findBean("HibernateSpringDaoContenidoTematico");
        list_ = dao1.seleccionarDatosCT(Integer.parseInt(id.getValue().toString()));
        if (list_.size() != 0 && list_ != null) {
            Object[] obj = new Object[list_.size()];
            obj = (Object[]) list_.get(0);
            this.setPlan_descripcion(obj[3].toString());//Plan Curricular
            this.setEspecialidad_descripcion(obj[4].toString());//Especialidad
            this.setFacultad_descripcion(obj[5].toString());//Facultad
            this.setCiclo_descripcion(obj[6].toString());//Ciclo
            this.setCurso_descripcion(obj[7].toString());//Curso

        } else {
            this.setPlan_descripcion("");//Plan Curricular
            this.setEspecialidad_descripcion("");//Especialidad
            this.setFacultad_descripcion("");//Facultad
            this.setCiclo_descripcion("");//Ciclo
            this.setCurso_descripcion("");//Curso
        }
        pc_id = Integer.parseInt(id.getValue().toString());
        if (pc_id != 0) {
            listaDatosDetalle = new ArrayList();
            List lista = null;
            List lista1 = null;
            int contem_id = 0;
            String titulo = "";
            String descripcion = "";
            String nro_semanas = "";
            String vig = "";
            int aux_d = 0;
            HSContenidoTematicoDAO dao = (HSContenidoTematicoDAO) ServiceFinder.findBean("HibernateSpringDaoContenidoTematico");
            lista = dao.get_contem_id(pc_id);
            if (lista.size() > 0 && lista != null) {
                contem_id = ((AcContenidoTematico) lista.get(0)).getId();
                titulo = ((AcContenidoTematico) lista.get(0)).getContemTitulo();
                descripcion = ((AcContenidoTematico) lista.get(0)).getContemDescripcion();
                nro_semanas = ((AcContenidoTematico) lista.get(0)).getContemNroSemanas();
                vig = ((AcContenidoTematico) lista.get(0)).getContemVigente();
                //System.out.println("El numero de semanas es:"+nro_semanas);
                if (nro_semanas == null || nro_semanas.equals(null)) {
                    nro_semanas = "0";
                }
                aux_d = Integer.parseInt(nro_semanas) + 1;
                this.setD_semana1(String.valueOf(aux_d));
                lista1 = dao.listar_detalle(contem_id);
                this.setCt_titulo_u(titulo);
                this.setCt_descripcion_u(descripcion);
                this.setCt_semanas_u(Integer.parseInt(nro_semanas));
                this.setDisabled("true");
                if (vig.equals("0")) {
                    this.setCt_vigente_u(false);
                } else {
                    this.setCt_vigente_u(true);
                }
                if (lista1.size() > 0 && lista1 != null) {
                    bUnidadTematica bUT;
                    for (int i = 0; i < lista1.size(); i++) {
                        bUT = new bUnidadTematica();
                        bUT.setD_id(((AcContenidoDetalle) lista1.get(i)).getId());
                        bUT.setD_contenido(((AcContenidoDetalle) lista1.get(i)).getCondetContenido());
                        bUT.setD_semana(((AcContenidoDetalle) lista1.get(i)).getCondetSemana());
                        bUT.setD_contem_id((((AcContenidoDetalle) lista1.get(i)).getContem()).getId());
                        detalle.add(bUT);
                    }
                    this.setListaDatosDetalle(detalle);
                }
            } else {
                this.setCt_titulo_aux("");
                this.setCt_descripcion_aux("");
                this.setCt_semanas_aux(0);
                this.setCt_vigente_aux(false);
                this.setDisabled("false");
                this.setD_semana1("1");
            }
        }
    }

    public void Guardar() throws SQLException, Exception {
        int contem_id = 0;
        String nro_semanas = "";
        if (this.getCt_titulo_u().length() == 0 || this.getCt_descripcion_u().length() == 0) {
            if (this.getCt_titulo_u().length() == 0) {
                this.setMensajes("javascript:alert('El Titulo del Contenido Tematico no puede ir vacio')");
            } else {
                this.setMensajes("javascript:alert('La Descripcion del Contenido Tematico no puede ir vacio')");
            }
        } else {
            if (detalle_eliminacion.size() > 0) {
                HSContenidoTematicoDAO dao2 = (HSContenidoTematicoDAO) ServiceFinder.findBean("HibernateSpringDaoContenidoTematico");
                for (int l = 0; l < detalle_eliminacion.size(); l++) {
                    if (detalle_eliminacion.get(l).getD_id() > 0) {
                        AcContenidoDetalle contemdet = new AcContenidoDetalle();
                        AcContenidoTematico ct1 = new AcContenidoTematico();
                        ct1.setId(1);
                        contemdet.setId(((bUnidadTematica) detalle_eliminacion.get(l)).getD_id());
                        contemdet.setContem(ct1);
                        listaContenidoDetalleEliminacion.add(contemdet);
                    } else {
                    }

                }
                dao2.eliminarContenidoTematicoDetalle(listaContenidoDetalleEliminacion);
                listaContenidoDetalleEliminacion.clear();
                detalle_eliminacion.clear();
            }
            AcContenidoTematico ct = new AcContenidoTematico();
            AcPlanCurso pc = new AcPlanCurso();
            pc.setId(this.getB_id_u());
            ct.setContemTitulo(this.getCt_titulo_u());
            ct.setContemDescripcion(this.getCt_descripcion_u());
            ct.setPlancur(pc);
            if (this.isCt_vigente_u()) {
                ct.setContemVigente("1");
            } else {
                ct.setContemVigente("0");
            }
            ct.setContemActivo("1");
            List lista = null;
            List lista1 = null;
            HSContenidoTematicoDAO dao = (HSContenidoTematicoDAO) ServiceFinder.findBean("HibernateSpringDaoContenidoTematico");
            lista = dao.get_contem_id(this.getB_id_u());
            if (lista.size() > 0 && lista != null) {
                contem_id = ((AcContenidoTematico) lista.get(0)).getId();
                nro_semanas = ((AcContenidoTematico) lista.get(0)).getContemNroSemanas();
                ct.setId(contem_id);
            }
            for (int i = 0; i < detalle.size(); i++) {
                AcContenidoDetalle contemdet = new AcContenidoDetalle();
                if (((bUnidadTematica) detalle.get(i)).getD_id() > 0) {
                    contemdet.setId(((bUnidadTematica) detalle.get(i)).getD_id());
                }

                contemdet.setCondetContenido(((bUnidadTematica) detalle.get(i)).getD_contenido());
                contemdet.setContem(ct);
                if (Integer.parseInt(((bUnidadTematica) detalle.get(i)).getD_semana()) > 0) {
                    ct.setContemNroSemanas(((bUnidadTematica) detalle.get(i)).getD_semana());
                    contemdet.setCondetSemana(((bUnidadTematica) detalle.get(i)).getD_semana());
                } else {
                    ct.setContemNroSemanas(nro_semanas);
                    contemdet.setCondetSemana(nro_semanas);
                }

                contemdet.setContem(ct);
                listaContenidoDetalle.add(contemdet);
            }
            ct.setAcContenidoDetalles(listaContenidoDetalle);
            dao.insertarContenidoTematico(ct);
            this.setMensajes("javascript:alert('Se guardo Correctamente');Richfaces.hideModalPanel('nuevo')");
            listaContenidoDetalle.clear();
        }
        this.setSw2(0);
    }

    public void EditarFilaDetalle(ActionEvent event) {
        this.setEditable("true");
        this.setVer("false");
        this.setEditableb1(false);
        this.setEditableb2(true);
        this.setD_contenido_aux(this.getD_contenido());
    }

    public void Cancelar(ActionEvent event) {
        this.setEditable("false");
        this.setVer("true");
        this.setEditableb1(true);
        this.setEditableb2(false);
        this.setD_contenido(this.getD_contenido_aux());
    }

    public void Quitar() throws SQLException, Exception {
        int indice = 0;
        if (Integer.parseInt(this.getD_semana1()) > 1) {
            int contador_sem = Integer.parseInt(this.getD_semana1()) - 1;
            this.setD_semana1(String.valueOf(contador_sem));
            this.setContador_semana(contador_sem - 1);
            this.setSw2(1);
        }

        if (detalle.size() > 0) {
            indice = detalle.size() - 1;
            detalle_eliminacion.add(detalle.get(indice));
            detalle.remove(indice);
            this.setListaDatosDetalle(detalle);
        }
    }

    public void GEditar(ActionEvent event) throws SQLException, Exception {
        this.setEditable("false");
        this.setVer("true");
        this.setEditableb1(true);
        this.setEditableb2(false);
    }

    public void Agregar() throws SQLException, Exception {
        bUnidadTematica utematica = new bUnidadTematica();
        List lista = null;
        int contador_sem = this.getContador_semana();
        int contem_id = 0;
        String titulo = "";
        String descripcion = "";
        String nro_semanas = "";
        String vig = "";
        int aux_d = 0;
        HSContenidoTematicoDAO dao = (HSContenidoTematicoDAO) ServiceFinder.findBean("HibernateSpringDaoContenidoTematico");
        lista = dao.get_contem_id(this.getB_id_u());
        if (lista.size() > 0 && lista != null && this.getSw() == 0 && this.getSw2() == 0) {
            contem_id = ((AcContenidoTematico) lista.get(0)).getId();
            titulo = ((AcContenidoTematico) lista.get(0)).getContemTitulo();
            descripcion = ((AcContenidoTematico) lista.get(0)).getContemDescripcion();
            nro_semanas = ((AcContenidoTematico) lista.get(0)).getContemNroSemanas();

            if (nro_semanas == null || nro_semanas.equals(null)) {
                nro_semanas = "0";
            }
            vig = ((AcContenidoTematico) lista.get(0)).getContemVigente();
            contador_sem = Integer.parseInt(nro_semanas);
            this.setSw(1);

        }
        contador_sem++;
        this.setContador_semana(contador_sem);
        aux_d = contador_sem + 1;
        this.setD_semana1(String.valueOf(aux_d));
        utematica.setD_id(d_id);
        utematica.setD_semana(String.valueOf(contador_sem));
        utematica.setD_contenido(this.getD_contenido());
        detalle.add(utematica);
        this.setListaDatosDetalle(detalle);
        this.setD_contenido("");
    }

    public int getB_facultad() {
        return b_facultad;
    }

    public void setB_facultad(int b_facultad) {
        this.b_facultad = b_facultad;
    }

    public int getB_facultad_u() {
        return b_facultad_u;
    }

    public void setB_facultad_u(int b_facultad_u) {
        this.b_facultad_u = b_facultad_u;
    }

    public SelectItem[] getComboFacultades_s() throws SQLException, Exception {
        HSFacultadDAO dao = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
        List lista = dao.seleccionarFacultad("", "");
        comboFacultades_s = new SelectItem[1];
        comboFacultades_s[0] = new SelectItem(0, "[Seleccione]");
        if (lista.size() != 0) {
            comboFacultades_s = new SelectItem[lista.size() + 1];
            for (int i = 0; i < comboFacultades_s.length - 1; i++) {
                comboFacultades_s[0] = new SelectItem(0, "[Seleccione]");
                comboFacultades_s[i + 1] = new SelectItem(((AcFacultad) lista.get(i)).getId(), ((AcFacultad) lista.get(i)).getFacNombre());
            }
        }
        return comboFacultades_s;
    }

    public void setComboFacultades_s(SelectItem[] comboFacultades_s) {
        this.comboFacultades_s = comboFacultades_s;
    }

    public int getB_especialidad() {
        return b_especialidad;
    }

    public void setB_especialidad(int b_especialidad) {
        this.b_especialidad = b_especialidad;
    }

    public int getB_especialidad_u() {
        return b_especialidad_u;
    }

    public void setB_especialidad_u(int b_especialidad_u) {
        this.b_especialidad_u = b_especialidad_u;
    }

    public String getFacultad_descripcion() {
        return facultad_descripcion;
    }

    public void setFacultad_descripcion(String facultad_descripcion) {
        this.facultad_descripcion = facultad_descripcion;
    }

    public SelectItem[] getComboEspecialidades_s() throws SQLException, Exception {
        //System.out.println("entro al iffffffff");
        cambiarEspecialidad_s(Integer.parseInt(this.getCombo_especialidad_id_s()));
        return comboEspecialidades_s;
    }

    public void setComboEspecialidades_s(SelectItem[] comboEspecialidades_s) {
        this.comboEspecialidades_s = comboEspecialidades_s;
    }

    public String getCombo_especialidad_id_s() {
        return combo_especialidad_id_s;
    }

    public void setCombo_especialidad_id_s(String combo_especialidad_id_s) {
        this.combo_especialidad_id_s = combo_especialidad_id_s;
    }

    public int getB_pcurricular() {
        return b_pcurricular;
    }

    public void setB_pcurricular(int b_pcurricular) {
        this.b_pcurricular = b_pcurricular;
    }

    public int getB_pcurricular_u() {
        return b_pcurricular_u;
    }

    public void setB_pcurricular_u(int b_pcurricular_u) {
        this.b_pcurricular_u = b_pcurricular_u;
    }

    public SelectItem[] getComboPlanCurricular_s() throws SQLException, Exception {
        //System.out.println("entro al getComboPlanCurricular");
        cambiarPlanCurricular_s(Integer.parseInt(this.getCombo_plancurricular_id_s()));
        return comboPlanCurricular_s;
    }

    public void setComboPlanCurricular_s(SelectItem[] comboPlanCurricular_s) {
        this.comboPlanCurricular_s = comboPlanCurricular_s;
    }

    public String getCombo_plancurricular_id_s() {
        return combo_plancurricular_id_s;
    }

    public void setCombo_plancurricular_id_s(String combo_plancurricular_id_s) {
        this.combo_plancurricular_id_s = combo_plancurricular_id_s;
    }

    public String getB_ciclo() {
        return b_ciclo;
    }

    public void setB_ciclo(String b_ciclo) {
        this.b_ciclo = b_ciclo;
    }

    public int getB_ciclo_u() {
        this.setB_ciclo_u(this.getB_ciclo_aux());
        return b_ciclo_u;
    }

    public void setB_ciclo_u(int b_ciclo_u) {
        this.b_ciclo_u = b_ciclo_u;
    }

    public int getB_ciclo_aux() {
        return b_ciclo_aux;
    }

    public void setB_ciclo_aux(int b_ciclo_aux) {
        this.b_ciclo_aux = b_ciclo_aux;
    }

    public String getCombo_ciclo_id_s() {
        return combo_ciclo_id_s;
    }

    public void setCombo_ciclo_id_s(String combo_ciclo_id_s) {
        this.combo_ciclo_id_s = combo_ciclo_id_s;
    }

    public SelectItem[] getComboCiclo_s() throws SQLException, Exception {
        //System.out.println("entro al getComboCiclo_s");
        cambiarCiclo(Integer.parseInt(this.getCombo_ciclo_id_s()));
        return comboCiclo_s;
    }

    public void setComboCiclo_s(SelectItem[] comboCiclo_s) {
        this.comboCiclo_s = comboCiclo_s;
    }

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getB_id_u() {
        return b_id_u;
    }

    public void setB_id_u(int b_id_u) {
        this.b_id_u = b_id_u;
    }

    public boolean isCt_vigente() {
        return ct_vigente;
    }

    public void setCt_vigente(boolean ct_vigente) {
        this.ct_vigente = ct_vigente;
    }

    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }

    public String getD_contenido() {
        return d_contenido;
    }

    public void setD_contenido(String d_contenido) {
        this.d_contenido = d_contenido;
    }

    public String getD_semana() {
        return d_semana;
    }

    public void setD_semana(String d_semana) {
        this.d_semana = d_semana;
    }

    public List getListaDatosDetalle() throws SQLException, Exception {
        return listaDatosDetalle;
    }

    public void setListaDatosDetalle(List listaDatosDetalle) {
        //System.out.println("Seteando setListaDatosDetalle con "+listaDatosDetalle.size());
        this.listaDatosDetalle = listaDatosDetalle;
    }

    public String getCt_titulo_u() {
        return ct_titulo_u;
    }

    public void setCt_titulo_u(String ct_titulo_u) {
        this.ct_titulo_u = ct_titulo_u;
    }

    public String getCt_titulo_aux() {
        return ct_titulo_aux;
    }

    public void setCt_titulo_aux(String ct_titulo_aux) {
        this.ct_titulo_aux = ct_titulo_aux;
    }

    public String getCt_descripcion_u() {
        return ct_descripcion_u;
    }

    public void setCt_descripcion_u(String ct_descripcion_u) {
        this.ct_descripcion_u = ct_descripcion_u;
    }

    public String getCt_descripcion_aux() {
        return ct_descripcion_aux;
    }

    public void setCt_descripcion_aux(String ct_descripcion_aux) {
        this.ct_descripcion_aux = ct_descripcion_aux;
    }

    public int getCt_semanas_u() {

        return ct_semanas_u;
    }

    public void setCt_semanas_u(int ct_semanas_u) {
        this.ct_semanas_u = ct_semanas_u;
    }

    public int getCt_semanas_aux() {
        return ct_semanas_aux;
    }

    public void setCt_semanas_aux(int ct_semanas_aux) {
        this.ct_semanas_aux = ct_semanas_aux;
    }

    public boolean isCt_vigente_u() {

        return ct_vigente_u;
    }

    public void setCt_vigente_u(boolean ct_vigente_u) {
        this.ct_vigente_u = ct_vigente_u;
    }

    public boolean isCt_vigente_aux() {
        return ct_vigente_aux;
    }

    public void setCt_vigente_aux(boolean ct_vigente_aux) {
        this.ct_vigente_aux = ct_vigente_aux;
    }

    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public boolean isEditableb1() {
        return editableb1;
    }

    public void setEditableb1(boolean editableb1) {
        this.editableb1 = editableb1;
    }

    public boolean isEditableb2() {
        return editableb2;
    }

    public void setEditableb2(boolean editableb2) {
        this.editableb2 = editableb2;
    }

    public int getD_contem_id() {
        return d_contem_id;
    }

    public void setD_contem_id(int d_contem_id) {
        this.d_contem_id = d_contem_id;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getEspecialidad_descripcion() {
        return especialidad_descripcion;
    }

    public void setEspecialidad_descripcion(String especialidad_descripcion) {
        this.especialidad_descripcion = especialidad_descripcion;
    }

    public String getPlan_descripcion() {
        return plan_descripcion;
    }

    public void setPlan_descripcion(String plan_descripcion) {
        this.plan_descripcion = plan_descripcion;
    }

    public String getCiclo_descripcion() {
        return ciclo_descripcion;
    }

    public void setCiclo_descripcion(String ciclo_descripcion) {
        this.ciclo_descripcion = ciclo_descripcion;
    }

    public String getCurso_descripcion() {
        return curso_descripcion;
    }

    public void setCurso_descripcion(String curso_descripcion) {
        this.curso_descripcion = curso_descripcion;
    }

    public int getContador_semana() {
        return contador_semana;
    }

    public void setContador_semana(int contador_semana) {

        this.contador_semana = contador_semana;
    }

    public String getD_semana1() {
        return d_semana1;
    }

    public void setD_semana1(String d_semana1) {
        this.d_semana1 = d_semana1;
    }

    public String getD_contenido_aux() {
        return d_contenido_aux;
    }

    public void setD_contenido_aux(String d_contenido_aux) {
        this.d_contenido_aux = d_contenido_aux;
    }
}