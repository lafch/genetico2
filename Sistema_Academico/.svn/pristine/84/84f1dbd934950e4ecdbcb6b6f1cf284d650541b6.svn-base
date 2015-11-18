package net.uch.academica.managedBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.component.UIParameter;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import net.uch.academica.hibernateSpringDao.HSDocenteDAO;
import net.uch.academica.hibernateSpringDao.HSHorarioDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcDocente;
import net.uch.mapping.TbCatalogo;
import net.uch.mapping.TbDistrito;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSSemestreDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSUbigeoDAO;
import net.uch.tablaSistema.managedBeans.bUsuario;

public class bDocente {

    private int b_id;
    private int b_id_u;
    private static int b_id_aux;
    private String b_codigo;
    private String b_codigo_u;
    private static String b_codigo_aux;
    private String b_nombre;
    private String b_nombre_u;
    private static String b_nombre_aux;
    private String b_correo;
    private String b_correo_u;
    private static String b_correo_aux;
    private String b_telefono;
    private String b_telefono_u;
    private static String b_telefono_aux;
    private String b_inicio;
    private Date b_inicio_u;
    private static Date b_inicio_aux;
    private String b_departamento_r;
    private String b_provincia_r;
    private String b_distrito_r;
    private static String b_departamento_aux_r;
    private static String b_provincia_aux_r;
    private static String b_distrito_aux_r;
    private String b_departamento_n;
    private String b_provincia_n;
    private String b_distrito_n;
    private static String b_departamento_aux_n;
    private static String b_provincia_aux_n;
    private static String b_distrito_aux_n;
    private String b_sexo;
    private String b_sexo_u = "004001";
    private static String b_sexo_aux;
    private String b_activo;
    private String b_activo_u;
    private static String b_activo_aux;
    private String combo_departamento_id = "0";
    private String combo_provincia_id = "00";
    private String combo_distrito_id = "0000";
    private String combo_departamento_id1 = "0";
    private String combo_provincia_id1 = "00";
    private static String combo_distrito_id1 = "0000";
    public static String estado = "0";
    public static String b_modificacion_aux = "0";
    private SelectItem[] comboDepartamentos;
    private SelectItem[] comboProvincias;
    private SelectItem[] comboDistrito;
    private SelectItem[] comboDepartamentos1;
    private SelectItem[] comboProvincias1;
    private SelectItem[] comboDistrito1;
    private List listaDocentes;
    private List listaDepartamentos;
    private List listaProvincias;
    private List listaDistritos;
    public SelectItem[] comboCatalogo;
    public String b_situacion;
    public String b_situacion_u;
    public static String b_situacion_aux;
    private SelectItem[] sexo;
    private String oncomplete;
    private String w_nombre;
    private String w_paterno;
    private String w_materno;
    private String w_dni;
    private String b_dni;
    private String b_paterno;
    private String b_materno;
    private String b_nombres;
    private String mensajeElimi = "";

    public String getMensajeElimi() {
        return mensajeElimi;
    }

    public void setMensajeElimi(String mensajeElimi) {
        this.mensajeElimi = mensajeElimi;
    }

    public String getB_dni() {
        return b_dni;
    }

    public void setB_dni(String b_dni) {
        this.b_dni = b_dni;
    }

    public String getB_materno() {
        return b_materno;
    }

    public void setB_materno(String b_materno) {
        this.b_materno = b_materno;
    }

    public String getB_nombres() {
        return b_nombres;
    }

    public void setB_nombres(String b_nombres) {
        this.b_nombres = b_nombres;
    }

    public String getB_paterno() {
        return b_paterno;
    }

    public void setB_paterno(String b_paterno) {
        this.b_paterno = b_paterno;
    }

    public String getW_dni() {
        return w_dni;
    }

    public void setW_dni(String w_dni) {
        this.w_dni = w_dni;
    }

    public String getW_materno() {
        return w_materno;
    }

    public void setW_materno(String w_materno) {
        this.w_materno = w_materno;
    }

    public String getW_nombre() {
        return w_nombre;
    }

    public void setW_nombre(String w_nombre) {
        this.w_nombre = w_nombre;
    }

    public String getW_paterno() {
        return w_paterno;
    }

    public void setW_paterno(String w_paterno) {
        this.w_paterno = w_paterno;
    }
    //private String

    public bDocente() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession(false);
        bUsuario usu = (bUsuario) session1.getAttribute("usuario");
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public void Nuevo() {
        this.setOncomplete("");
        //System.out.println("Entro a Nuevo");
        //this.setB_id_aux(0);
       /* setB_inicio_u(new Date());
        setB_codigo_u("");
        setB_nombre_u("");
        setB_telefono_u("");
        setB_correo_u("");*/

        this.setB_departamento_n("00");
        this.setB_departamento_r("00");
        //this.setB_id_u(0);
        this.setB_inicio_u(new Date());
        this.setB_codigo_u("");
        this.setB_nombre_u("");
        this.setB_telefono_u("");
        this.setB_correo_u("");
        this.setW_dni("");
        this.setW_materno("");
        this.setW_paterno("");
        this.setW_nombre("");

        setB_departamento_aux_n("00");
        setB_departamento_aux_r("00");
        this.setCombo_departamento_id("0");
        this.setCombo_provincia_id("00");
        this.setCombo_distrito_id("0000");
        this.setCombo_departamento_id1("0");
        this.setCombo_provincia_id1("00");
        setCombo_distrito_id1("0000");
        this.setB_sexo("0");
        this.setB_sexo_u("004001");

        this.setB_situacion_u("0");
        setB_situacion_aux("0");
        this.setOncomplete("javascript:Richfaces.showModalPanel('mpDocente')");
    }

    public String getB_situacion() {
        return b_situacion;
    }

    public void setB_situacion(String b_situacion) {
        this.b_situacion = b_situacion;
    }

    public SelectItem[] getComboCatalogo() throws Exception {
        HSCatalogoDAO dao = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        List lista = dao.seleccionarCatalogo("002");
        SelectItem[] comboCurso = new SelectItem[lista.size()];
        for (int i = 0; i < comboCurso.length; i++) {
            comboCurso[i] = new SelectItem(((TbCatalogo) lista.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista.get(i)).getCatDescripcionElemento());
        }
        return comboCurso;
    }

    public void setComboCurso(SelectItem[] comboCurso) {
        this.comboCatalogo = comboCurso;
    }

    public SelectItem[] getComboDepartamentos() throws Exception {
        HSUbigeoDAO dao = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
        List lista = dao.seleccionarDepartamento();
        SelectItem[] cmbDepartamentos = new SelectItem[lista.size()];
        for (int i = 0; i < cmbDepartamentos.length; i++) {
            cmbDepartamentos[i] = new SelectItem(((TbDistrito) lista.get(i)).getId(), ((TbDistrito) lista.get(i)).getDisDes());
        }
        return cmbDepartamentos;
    }

    public void setComboDepartamentos(SelectItem[] comboDepartamentos) {
        this.comboDepartamentos = comboDepartamentos;
    }

    public SelectItem[] getComboProvincias() throws Exception {
        //System.out.println("cambiar con codigo=" + this.getCombo_provincia_id());
        cambiarProvincia(this.getCombo_provincia_id());
        return comboProvincias;
    }

    public void setComboProvincias(SelectItem[] comboProvincias) {
        this.comboProvincias = comboProvincias;
    }

    public SelectItem[] getComboDistrito() throws Exception {
        //System.out.println("cambiar distritos=" + this.getCombo_distrito_id());
        //UNo antes el departamento se guarda en provincia_id y la provincia se guarda en distrito_id
        cambiarDistrito(this.getCombo_provincia_id(), this.getCombo_distrito_id());
        return comboDistrito;
    }

    public void setComboDistrito(SelectItem[] comboDistrito) {
        this.comboDistrito = comboDistrito;
    }

    public List getListaDepartamentos() {
        return listaDepartamentos;
    }

    public String getCombo_departamento_id() {
        return combo_departamento_id;
    }

    public void setCombo_departamento_id(String combo_departamento_id) {
        this.combo_departamento_id = combo_departamento_id;
    }

    public String getCombo_provincia_id() {
        return combo_provincia_id;
    }

    public void setCombo_provincia_id(String combo_provincia_id) {
        this.combo_provincia_id = combo_provincia_id;
    }

    public String getCombo_distrito_id() {
        return combo_distrito_id;
    }

    public void setCombo_distrito_id(String combo_distrito_id) {
        this.combo_distrito_id = combo_distrito_id;
    }

    public void setListaDepartamentos(List listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public List getListaProvincias() {
        return listaProvincias;
    }

    public void setListaProvincias(List listaProvincias) {
        this.listaProvincias = listaProvincias;
    }

    public List getListaDistritos() {
        return listaDistritos;
    }

    public void setListaDistritos(List listaDistritos) {
        this.listaDistritos = listaDistritos;
    }

    public List getListaDocentes() {
        return listaDocentes;
    }

    public void setListaDocentes(List listaDocentes) {
        this.listaDocentes = listaDocentes;
    }

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public int getB_id_u() {

        return b_id_u;
    }

    public void setB_id_u(int b_id_u) {
        this.b_id_u = b_id_u;
    }

    public String getB_codigo() {
        return b_codigo;
    }

    public void setB_codigo(String b_codigo) {
        this.b_codigo = b_codigo;
    }

    public String getB_nombre() {
        return b_nombre;
    }

    public void setB_nombre(String b_nombre) {
        this.b_nombre = b_nombre;
    }

    public String getB_correo() {

        return b_correo;
    }

    public void setB_correo(String b_correo) {
        this.b_correo = b_correo;
    }

    public String getB_telefono() {
        return b_telefono;
    }

    public void setB_telefono(String b_telefono) {
        this.b_telefono = b_telefono;
    }

    public String getB_activo() {
        return b_activo;
    }

    public void setB_activo(String b_activo) {
        this.b_activo = b_activo;
    }

    public String getB_codigo_u() {

        return b_codigo_u;
    }

    public void setB_codigo_u(String b_codigo_u) {
        this.b_codigo_u = b_codigo_u;
    }

    public String getB_nombre_u() {

        return b_nombre_u;
    }

    public void setB_nombre_u(String b_nombre_u) {
        this.b_nombre_u = b_nombre_u;
    }

    public String getB_correo_u() {

        return b_correo_u;
    }

    public void setB_correo_u(String b_correo_u) {
        this.b_correo_u = b_correo_u;
    }

    public String getB_telefono_u() {

        return b_telefono_u;
    }

    public void setB_telefono_u(String b_telefono_u) {
        this.b_telefono_u = b_telefono_u;
    }

    public String getB_activo_u() {
        return b_activo_u;
    }

    public void setB_activo_u(String b_activo_u) {
        this.b_activo_u = b_activo_u;
    }

    public void Enviar() {
        this.setMensajeElimi("");
        if (getB_modificacion_aux().equals("1")) {
            Editar();
        } else {
            Grabar();
        }
    }

    public String Grabar() {

        HSDocenteDAO dao = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");
        AcDocente docente = new AcDocente();
        docente.setId(this.getB_id());
        docente.setDocCodigo(this.getW_dni());
        docente.setDocAppaterno(this.getW_paterno());
        docente.setDocApmaterno(this.getW_materno());
        docente.setDocNombres(this.getW_nombre());
        docente.setDocNombre(this.getW_paterno() + " " + this.getW_materno() + " " + this.getW_nombre());
        docente.setDocCorreo(this.getB_correo_u());
        docente.setDocTelefono(this.getB_telefono_u());
        docente.setDocDni(this.getW_dni());
        docente.setDocPassword(this.getW_dni());
        String aux = "000000";
        if (!this.getB_distrito_r().equals("0")) {
            aux = this.getB_distrito_r();
        }
        docente.setDocResidencia(aux);

        String aux2 = "000000";
        if (!this.getB_distrito_n().equals("0")) {
            aux2 = this.getB_distrito_n();
        }
        docente.setDocNacimiento(aux2);
        docente.setDocPeriodoInicio(this.getB_inicio_u());
        docente.setDocSituacion(this.getB_situacion_u());
        docente.setDocSexo(this.getB_sexo_u());
        docente.setDocActivo("1");
        if (validar(docente, 2)) {
            dao.insertarDocente(docente);
            this.setOncomplete("javascript:alert('Se creo una Docente correctamente.');Richfaces.hideModalPanel('mpDocente')");
        }


        return "ok";
    }

    public void Seleccionar() throws Exception {
        //System.out.println("SELECCIONARRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
        this.setMensajeElimi("");
        listaDocentes = new ArrayList();
        HSDocenteDAO dao = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");
        List lista = dao.seleccionarDocente(this.getB_codigo(), this.getB_nombre());
        bDocente bC;
        for (int i = 0; i < lista.size(); i++) {
            bC = new bDocente();
            Object DOC[] = (Object[]) lista.get(i);
            bC.setB_id(Integer.parseInt(DOC[0].toString()));
            bC.setB_codigo(DOC[1].toString());
            bC.setB_codigo_u(DOC[1].toString());
            bC.setB_nombre(DOC[2].toString());
            bC.setB_nombre_u(DOC[2].toString());
            bC.setB_correo(DOC[3].toString());
            bC.setB_correo_u(DOC[3].toString());
            bC.setB_telefono(DOC[4].toString());
            bC.setB_telefono_u(DOC[4].toString());
            bC.setB_distrito_n(DOC[5].toString());
            bC.setB_distrito_r(DOC[6].toString());
            if (DOC[7] != null) {
                bC.setB_inicio(DOC[7].toString());
                bC.setB_inicio_u((Date) DOC[7]);
            }
            bC.setB_situacion(DOC[8].toString());
            bC.setB_situacion_u(DOC[8].toString());
            bC.setB_sexo(DOC[9].toString());
            bC.setB_sexo_u(DOC[9].toString());
            bC.setB_dni(DOC[10].toString());
            //System.out.println(DOC[10].toString() + " DNI");
            bC.setB_paterno(DOC[11].toString());
            bC.setB_materno(DOC[12].toString());
            bC.setB_nombres(DOC[13].toString());
            listaDocentes.add(bC);
        }
        this.setListaDocentes(listaDocentes);
    }

    public static String getEstado() {
        return estado;
    }

    public static void setEstado(String estado) {
        bDocente.estado = estado;
    }

    public static String getB_modificacion_aux() {
        return b_modificacion_aux;
    }

    public static void setB_modificacion_aux(String b_modificacion_aux) {
        bDocente.b_modificacion_aux = b_modificacion_aux;
    }

    public String getB_departamento_r() {
        if (getB_modificacion_aux().equals("1") && getEstado().equals("1")) {
            this.setB_departamento_r(getB_departamento_aux_r());
        }
        return b_departamento_r;
    }

    public void setB_departamento_r(String b_departamento_r) {
        this.b_departamento_r = b_departamento_r;
    }

    public String getB_provincia_r() {
        if (getB_modificacion_aux().equals("1") && getEstado().equals("1")) {
            this.setB_provincia_r(getB_provincia_aux_r());
        }
        return b_provincia_r;
    }

    public void setB_provincia_r(String b_provincia_r) {
        this.b_provincia_r = b_provincia_r;
    }

    public String getB_distrito_r() {
        if (getB_modificacion_aux().equals("1") && getEstado().equals("1")) {
            this.setB_distrito_r(getB_distrito_aux_r());
        }
        return b_distrito_r;
    }

    public void setB_distrito_r(String b_distrito_r) {
        this.b_distrito_r = b_distrito_r;
    }

    public String getB_departamento_n() {
        if (getB_modificacion_aux().equals("1") && getEstado().equals("1")) {
            this.setB_departamento_n(getB_departamento_aux_n());
        }
        return b_departamento_n;
    }

    public void setB_departamento_n(String b_departamento_n) {
        this.b_departamento_n = b_departamento_n;
    }

    public String getB_provincia_n() {
        if (getB_modificacion_aux().equals("1") && getEstado().equals("1")) {
            this.setB_provincia_n(getB_provincia_aux_n());
        }
        return b_provincia_n;
    }

    public String getB_sexo() {
        return b_sexo;
    }

    public void setB_sexo(String b_sexo) {
        this.b_sexo = b_sexo;
    }

    public void setB_provincia_n(String b_provincia_n) {
        this.b_provincia_n = b_provincia_n;
    }

    public String getB_distrito_n() {
        if (getB_modificacion_aux().equals("1") && getEstado().equals("1")) {
            this.setB_distrito_n(getB_distrito_aux_n());
        }
        return b_distrito_n;
    }

    public void setB_distrito_n(String b_distrito_n) {
        this.b_distrito_n = b_distrito_n;
    }

    public void EliminarFila(ActionEvent event) throws Exception {
        // System.out.println("ELIMINARAAAAAAAAAAAAAAAAAAAAAAAAAA");
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        UIParameter delete = (UIParameter) event.getComponent().findComponent("delete");
        // System.out.println("LLEGA CON:::::" + delete.getValue().toString());
        Eliminar(delete.getValue().toString());
    }

    public void Eliminar(String id) throws Exception {
        HSDocenteDAO dao = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");
        HSSemestreDAO dao_s = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
        HSHorarioDAO dao_h = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoHorario");
        //if(dao_h.SeleccionarHorarioPorDocente(id. ,9)>0{
        this.setMensajeElimi("");
        if (dao_h.SeleccionarHorarioPorDocente(Integer.parseInt(id), 9) == 0) {
            dao.eliminarDocente(id);
        } else {
            this.setMensajeElimi("El docente tiene horarios Asignados, no se puede eliminar");

        }

        //}

    }

    public SelectItem[] getSexo() throws Exception {
        HSCatalogoDAO dao = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        List lista = dao.seleccionarCatalogo("004");
        SelectItem[] sexxo = new SelectItem[lista.size()];
        for (int i = 0; i < sexxo.length; i++) {
            sexxo[i] = new SelectItem(((TbCatalogo) lista.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista.get(i)).getCatDescripcionElemento());
        }
        return sexxo;
    }

    public void setSexo(SelectItem[] sexo) {
        this.sexo = sexo;
    }

    public String getB_inicio() {
        return b_inicio;
    }

    public void setB_inicio(String b_inicio) {
        this.b_inicio = b_inicio;
    }

    public Date getB_inicio_u() {
        /*if (getB_modificacion_aux().equals("1")) {
        this.setB_inicio_u(getB_inicio_aux());
        }*/
        return b_inicio_u;
    }

    public void setB_inicio_u(Date b_inicio_u) {
        this.b_inicio_u = b_inicio_u;
    }

    public String getB_situacion_u() {
        /*if (getB_modificacion_aux().equals("1")) {
        setEstado("0");
        System.out.println("entroooooooooooooooooooooxxx" + getB_situacion_aux());
        this.setB_situacion_u(getB_situacion_aux());
        }*/
        return b_situacion_u;
    }

    public void setB_situacion_u(String b_situacion_u) {
        this.b_situacion_u = b_situacion_u;
    }

    public String getB_sexo_u() {
        /*if (getB_modificacion_aux().equals("1") && getEstado().equals("1")) {
        setEstado("0");
        System.out.println("Cambia Sexo con:" + getB_sexo_aux());
        this.setB_sexo_u(getB_sexo_aux());
        }*/
        return b_sexo_u;
    }

    public void setB_sexo_u(String b_sexo_u) {
        this.b_sexo_u = b_sexo_u;
    }

    public void EditarFila(ActionEvent event) {
        this.setOncomplete("");
        //  Nuevo();
        this.setMensajeElimi("");
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        UIParameter id = (UIParameter) event.getComponent().findComponent("p_id");

        try {
            HSDocenteDAO dao = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");
            AcDocente docente = dao.seleccionarDocente(Integer.parseInt(id.getValue().toString()));
            //System.out.println("no se renderiza -> " + Integer.parseInt(id.getValue().toString()));

            setB_id(Integer.parseInt(id.getValue().toString()));
            setB_codigo_u(docente.getDocCodigo());
            setB_nombre_u(docente.getDocNombre());
            setB_correo_u(docente.getDocCorreo());
            setB_telefono_u(docente.getDocTelefono());
            setB_departamento_r(docente.getDocResidencia().toString().substring(0, 2) + "0000");
            setB_departamento_n(docente.getDocNacimiento().toString().substring(0, 2) + "0000");
            setB_provincia_r(docente.getDocResidencia().toString().substring(0, 4) + "00");
            setB_provincia_n(docente.getDocNacimiento().toString().substring(0, 4) + "00");
            setB_distrito_n(docente.getDocResidencia().toString());
            setB_distrito_r(docente.getDocNacimiento().toString());
            setB_situacion_u(docente.getDocSituacion());
            setB_sexo_u(docente.getDocSexo());
            setB_modificacion_aux("1");
            setW_dni(docente.getDocDni());
            setW_paterno(docente.getDocAppaterno());
            setW_materno(docente.getDocApmaterno());
            setW_nombre(docente.getDocNombres());
            setB_inicio_u(docente.getDocPeriodoInicio());
            setEstado("1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setOncomplete("javascript:Richfaces.showModalPanel('mpDocente')");





    }

    public void Seleccionar1(ActionEvent event) throws Exception {
        Enviar();
    }

    public void Editar() {
        this.setMensajeElimi("");
        setB_modificacion_aux("0");
        HSDocenteDAO dao = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");
        AcDocente doc = new AcDocente();
        doc.setId(this.getB_id());
        doc.setDocDni(this.getW_dni());
        doc.setDocAppaterno(this.getW_paterno());
        doc.setDocApmaterno(this.getW_materno());
        doc.setDocNombres(this.getW_nombre());
        doc.setDocCodigo(this.getW_dni());
        doc.setDocNombre(this.getW_paterno() + " " + this.getW_materno() + " " + this.getW_nombre());
        doc.setDocCorreo(this.getB_correo_u());
        doc.setDocTelefono(this.getB_telefono_u());

        String aux3 = "000000";
        if (!this.getB_distrito_n().equals("0")) {
            aux3 = this.getB_distrito_n();
        }
        doc.setDocNacimiento(aux3);
        String aux4 = "000000";
        if (!this.getB_distrito_r().equals("0")) {
            aux4 = this.getB_distrito_r();
        }
        doc.setDocResidencia(aux4);
        doc.setDocSituacion(this.getB_situacion_u());
        doc.setDocPeriodoInicio(this.getB_inicio_u());
        doc.setDocSexo(this.getB_sexo_u());
        doc.setDocActivo("1");
        if (validar(doc, 1)) {
            dao.actualizarDocente(doc);
            this.setOncomplete("javascript:alert('Se Actualizaron los datos del Docente correctamente.');Richfaces.hideModalPanel('mpDocente')");
            try {
                Seleccionar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setB_modificacion_aux("1");
        }


    }

    public static int getB_id_aux() {
        return b_id_aux;
    }

    public static void setB_id_aux(int b_id_aux) {
        bDocente.b_id_aux = b_id_aux;
    }

    public static String getB_codigo_aux() {
        return b_codigo_aux;
    }

    public static void setB_codigo_aux(String b_codigo_aux) {
        bDocente.b_codigo_aux = b_codigo_aux;
    }

    public static String getB_nombre_aux() {
        return b_nombre_aux;
    }

    public static void setB_nombre_aux(String b_nombre_aux) {
        bDocente.b_nombre_aux = b_nombre_aux;
    }

    public static String getB_correo_aux() {
        return b_correo_aux;
    }

    public static void setB_correo_aux(String b_correo_aux) {
        bDocente.b_correo_aux = b_correo_aux;
    }

    public static String getB_telefono_aux() {
        return b_telefono_aux;
    }

    public static void setB_telefono_aux(String b_telefono_aux) {
        bDocente.b_telefono_aux = b_telefono_aux;
    }

    public static Date getB_inicio_aux() {
        return b_inicio_aux;
    }

    public static void setB_inicio_aux(Date b_inicio_aux) {
        bDocente.b_inicio_aux = b_inicio_aux;
    }

    public static String getB_distrito_aux_r() {
        return b_distrito_aux_r;
    }

    public static void setB_distrito_aux_r(String b_distrito_aux_r) {
        bDocente.b_distrito_aux_r = b_distrito_aux_r;
    }

    public static String getB_distrito_aux_n() {
        return b_distrito_aux_n;
    }

    public static void setB_distrito_aux_n(String b_distrito_aux_n) {
        bDocente.b_distrito_aux_n = b_distrito_aux_n;
    }

    public static String getB_sexo_aux() {
        return b_sexo_aux;
    }

    public static void setB_sexo_aux(String b_sexo_aux) {
        bDocente.b_sexo_aux = b_sexo_aux;
    }

    public static String getB_activo_aux() {
        return b_activo_aux;
    }

    public static void setB_activo_aux(String b_activo_aux) {
        bDocente.b_activo_aux = b_activo_aux;
    }

    public static String getB_situacion_aux() {
        return b_situacion_aux;
    }

    public static void setB_situacion_aux(String b_situacion_aux) {
        bDocente.b_situacion_aux = b_situacion_aux;
    }

    public void setComboCatalogo(SelectItem[] comboCatalogo) {
        this.comboCatalogo = comboCatalogo;
    }

    public SelectItem[] getComboDepartamentos1() throws Exception {
        HSUbigeoDAO dao = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
        List lista = dao.seleccionarDepartamento();
        SelectItem[] cmbDepartamentos = new SelectItem[lista.size()];
        for (int i = 0; i < cmbDepartamentos.length; i++) {
            cmbDepartamentos[i] = new SelectItem(((TbDistrito) lista.get(i)).getId(), ((TbDistrito) lista.get(i)).getDisDes());
        }
        return cmbDepartamentos;
    }

    public void setComboDepartamentos1(SelectItem[] comboDepartamentos1) {

        this.comboDepartamentos1 = comboDepartamentos1;
    }

    public SelectItem[] getComboProvincias1() throws Exception {
        //System.out.println("getComboProvincias1=" + this.getCombo_provincia_id1());
        cambiarProvincia1(this.getCombo_provincia_id1());
        return comboProvincias1;
    }

    public void setComboProvincias1(SelectItem[] comboProvincias1) {
        this.comboProvincias1 = comboProvincias1;
    }

    public SelectItem[] getComboDistrito1() throws Exception {
        ////*******naa
        cambiarDistrito1(this.getCombo_provincia_id1(), getCombo_distrito_id1());
        return comboDistrito1;
    }

    public void setComboDistrito1(SelectItem[] comboDistrito1) {
        this.comboDistrito1 = comboDistrito1;
    }

    public String getCombo_departamento_id1() {
        return combo_departamento_id1;
    }

    public void setCombo_departamento_id1(String combo_departamento_id1) {
        this.combo_departamento_id1 = combo_departamento_id1;
    }

    public String getCombo_provincia_id1() {
        return combo_provincia_id1;
    }

    public void setCombo_provincia_id1(String combo_provincia_id1) {
        this.combo_provincia_id1 = combo_provincia_id1;
    }

    public static String getCombo_distrito_id1() {
        return combo_distrito_id1;
    }

    public static void setCombo_distrito_id1(String combo_distrito_id1) {
        bDocente.combo_distrito_id1 = combo_distrito_id1;
    }

    public static String getB_departamento_aux_r() {
        return b_departamento_aux_r;
    }

    public static void setB_departamento_aux_r(String b_departamento_aux_r) {
        bDocente.b_departamento_aux_r = b_departamento_aux_r;
    }

    public static String getB_provincia_aux_r() {
        return b_provincia_aux_r;
    }

    public static void setB_provincia_aux_r(String b_provincia_aux_r) {
        bDocente.b_provincia_aux_r = b_provincia_aux_r;
    }

    public static String getB_departamento_aux_n() {
        return b_departamento_aux_n;
    }

    public static void setB_departamento_aux_n(String b_departamento_aux_n) {
        bDocente.b_departamento_aux_n = b_departamento_aux_n;
    }

    public static String getB_provincia_aux_n() {
        return b_provincia_aux_n;
    }

    public static void setB_provincia_aux_n(String b_provincia_aux_n) {
        bDocente.b_provincia_aux_n = b_provincia_aux_n;
    }

    public void setear() {
        setearDepartamento();
        setearProvincia();
        setearDepartamento_n();
        setearProvincia_n();
        setearDepartamento1();
        setearProvincia1();
    }

    public void setearDepartamento() {
        this.setCombo_provincia_id("" + this.getB_departamento_r());
    }

    public void setearProvincia() {
        this.setCombo_distrito_id("" + this.getB_provincia_r());
    }

    public void setearDepartamento_n() {
        //System.out.println("Settttttttttttttteando provincia con::::.........." + this.getB_departamento_n());
        this.setCombo_provincia_id("" + this.getB_departamento_n());
        setearProvinciaY();
    }

    public void setearProvinciaY() {
        this.setCombo_distrito_id("" + this.getCombo_provincia_id().substring(0, 3) + 101);
    }

    public void setearProvincia_n() {
        //System.out.println("Settttttttttttttteando distrito con::::.........." + this.getB_provincia_n());
        this.setCombo_distrito_id("" + this.getB_provincia_n());
    }

    ////*******cambie
    public void setearDepartamento1() {
        //System.out.println("Settttttttttttttteando1..........");
        this.setCombo_provincia_id1("" + this.getB_departamento_r());
        setearProvinciaX();
    }

    public void setearProvinciaX() {
        // System.out.println("jojojojo:" + this.getCombo_provincia_id1().substring(0, 3) + 101);
        setCombo_distrito_id1("" + this.getCombo_provincia_id1().substring(0, 3) + 101);
    }

    public void setearProvincia1() {
        //System.out.println("Settttttttttttttteando distrito1 con::::.........." + this.getB_provincia_r());
        setCombo_distrito_id1("" + this.getB_provincia_r());
    }

    public void cambiarProvincia1(String id) throws Exception {
        HSUbigeoDAO dao = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
        List lista = dao.seleccionarProvincia(id);
        if (lista.size() != 0) {
            comboProvincias1 = new SelectItem[lista.size()];
            for (int i = 0; i < comboProvincias1.length; i++) {
                comboProvincias1[i] = new SelectItem(((TbDistrito) lista.get(i)).getId(), ((TbDistrito) lista.get(i)).getDisDes());
            }
        } else {
            comboProvincias1 = new SelectItem[1];
            comboProvincias1[0] = new SelectItem(0, "[Seleccione]");
        }
        this.setComboProvincias1(comboProvincias1);
    }
////*******naa

    public void cambiarDistrito1(String id_Departamento, String id_Provincia) throws Exception {
        // System.out.println("este distrito es de departamento 1:" + id_Departamento + "//provincia 1" + id_Provincia);
        HSUbigeoDAO dao = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
        List lista = dao.seleccionarDistrito(id_Departamento, id_Provincia);
        if (lista.size() != 0) {
            comboDistrito1 = new SelectItem[lista.size()];
            for (int i = 0; i < comboDistrito1.length; i++) {
                comboDistrito1[i] = new SelectItem(((TbDistrito) lista.get(i)).getId(), ((TbDistrito) lista.get(i)).getDisDes());
            }
        } else {
            comboDistrito1 = new SelectItem[1];
            comboDistrito1[0] = new SelectItem(0, "[Seleccione]");
        }
        this.setComboDistrito1(comboDistrito1);
    }

    public void cambiarProvincia(String id) throws Exception {
        HSUbigeoDAO dao = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
        List lista = dao.seleccionarProvincia(id);
        if (lista.size() != 0) {
            comboProvincias = new SelectItem[lista.size()];
            for (int i = 0; i < comboProvincias.length; i++) {
                comboProvincias[i] = new SelectItem(((TbDistrito) lista.get(i)).getId(), ((TbDistrito) lista.get(i)).getDisDes());
            }
        } else {
            comboProvincias = new SelectItem[1];
            comboProvincias[0] = new SelectItem(0, "[Seleccione]");
        }
        this.setComboProvincias(comboProvincias);
    }

    public void cambiarDistrito(String id_Departamento, String id_Provincia) throws Exception {
        HSUbigeoDAO dao = (HSUbigeoDAO) ServiceFinder.findBean("SpringHibernateDaoUbigeo");
        List lista = dao.seleccionarDistrito(id_Departamento, id_Provincia);
        if (lista.size() != 0) {
            comboDistrito = new SelectItem[lista.size()];
            for (int i = 0; i < comboDistrito.length; i++) {
                comboDistrito[i] = new SelectItem(((TbDistrito) lista.get(i)).getId(), ((TbDistrito) lista.get(i)).getDisDes());
            }
        } else {
            comboDistrito = new SelectItem[1];
            comboDistrito[0] = new SelectItem(0, "[Seleccione]");
        }
        this.setComboDistrito(comboDistrito);
    }

    public boolean validar(AcDocente doc, int valor) {
        this.setMensajeElimi("");
        if (doc.getDocDni().length() != 8) {
            oncomplete = "";
            this.setOncomplete("javascript:alert('El Dni es incorrecto o no ha ingresado')");
            return false;
        } else {
            HSDocenteDAO dao = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");
            if (dao.buscarDocente(doc.getDocDni()) != null) {
                if (valor == 1) {
                    //return true;//
                    if (doc.getDocAppaterno().length() < 3) {
                        oncomplete = "";
                        this.setOncomplete("javascript:alert('Ingrese Apellido Paterno')");
                        return false;

                    }
                    if (doc.getDocApmaterno().length() < 3) {
                        oncomplete = "";
                        this.setOncomplete("javascript:alert('Ingrese Apellido Materno')");
                        return false;
                    }
                    if (doc.getDocNombres().length() < 3) {
                        oncomplete = "";
                        this.setOncomplete("javascript:alert('Ingrese Nombres')");
                        return false;
                    }
                    if (doc.getDocCorreo().length() < 10) {
                        oncomplete = "";
                        this.setOncomplete("javascript:alert('Ingrese Correo Electronico')");
                        return false;
                    }
                    if (doc.getDocTelefono().length() < 5) {
                        oncomplete = "";
                        this.setOncomplete("javascript:alert('Ingrese nro telefono o celular')");
                        return false;
                    }

                } else {
                    this.setOncomplete("javascript:alert('El Dni ya se encuentra registrado')");
                    return false;
                }

            } else {
                if (doc.getDocApmaterno().length() < 3) {
                    oncomplete = "";
                    this.setOncomplete("javascript:alert('Ingrese Apellido Paterno')");
                    return false;

                }
                if (doc.getDocApmaterno().length() < 3) {
                    oncomplete = "";
                    this.setOncomplete("javascript:alert('Ingrese Apellido Materno')");
                    return false;
                }
                if (doc.getDocNombres().length() < 3) {
                    oncomplete = "";
                    this.setOncomplete("javascript:alert('Ingrese Nombres')");
                    return false;
                }
                if (doc.getDocCorreo().length() < 10) {
                    oncomplete = "";
                    this.setOncomplete("javascript:alert('Ingrese Correo Electronico')");
                    return false;
                }
                if (doc.getDocTelefono().length() < 5) {
                    oncomplete = "";
                    this.setOncomplete("javascript:alert('Ingrese nro telefono o celular')");
                    return false;
                }

            }
        }


        return true;
    }
}