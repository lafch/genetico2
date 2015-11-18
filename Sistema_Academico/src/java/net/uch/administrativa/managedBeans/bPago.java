package net.uch.administrativa.managedBeans;

import net.uch.administrativa.managedBeans.beans.BeanConceptoPago;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.faces.component.UIParameter;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import net.uch.academica.hibernateSpringDao.HSAlumnoDAO;
import net.uch.administrativa.hibernateSpringDao.HSAlumnoTarifaDAO;
import net.uch.administrativa.hibernateSpringDao.HSClienteDAO;
import net.uch.administrativa.hibernateSpringDao.HSConceptoPagoDAO;
import net.uch.administrativa.hibernateSpringDao.HSEstructuraPagoDAO;
import net.uch.administrativa.hibernateSpringDao.HSPagoDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcAlumno;
import net.uch.mapping.AcEspecialidad;
import net.uch.mapping.AcFacultad;
import net.uch.mapping.AcSemestre;
import net.uch.mapping.AdAlumnoTarifa;
import net.uch.mapping.AdClientes;
import net.uch.mapping.AdComprobantePago;
import net.uch.mapping.AdConceptoPago;
import net.uch.mapping.AdEstructuraPagosDetalle;
import net.uch.mapping.AdPago;
import net.uch.mapping.AdSaldo;
import net.uch.mapping.TbCatalogo;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSEspecialidadDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSFacultadDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSSemestreDAO;
import net.uch.tablaSistema.managedBeans.bUsuario;
import net.uch.util.Print;

public class bPago {

    private String suggestion;

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
    String e_num_comprobante0;
    String n_num_comprobante0;
    public SelectItem[] comboCatalogo_ReciboBloque;
    String n_con_pag_nuevo;
    int c_id_con_pag_det;
    int c_id_con_pag_det_u;
    float per_monto;
    Date per_fecha_pag;
    Date per_fecha_pro;
    public String imagenP2 = "/Imagenes/actions/editpaste.png";
    public String disabledP2 = "javascript:Richfaces.showModalPanel('mpx2',{width:100, top:100})";
    public String disabledP3 = "javascript:Richfaces.showModalPanel('mpx3',{width:100, top:100})";
    public SelectItem[] comboEspecialidad_buscar;
    public SelectItem[] comboFacultad_buscar;
    public SelectItem[] comboSemestre;
    public SelectItem[] comboConceptoPago;
    public SelectItem[] comboCatalogo_estado;
    public SelectItem[] comboCatalogo_listaPagos;
    int b_id_listaPag = 1;
    public SelectItem[] comboCatalogo_tipo_cliente;
    public SelectItem[] comboCatalogo_tipo_nj;
    public SelectItem[] comboCatalogo_procedencia;
    public SelectItem[] comboCatalogo_procedencia2;
    String e_id_tipo_cliente;
    String e_razon_social;
    String e_id_tipo_nj;
    String e_id_procedencia;
    String e_num_comprobante;
    Date e_fecha_comprobante;
    int e_cliente_fac;
    float e_monto_pago;
    String e_dni_ruc;
    int c_id;
    int c_id_nombre;
    int c_id_con_pag;
    String c_sem;
    int c_id_sem;
    String c_fac;
    int c_id_fac;
    String c_esp;
    int c_id_esp;
    String c_codigo;
    String c_nombre;
    String c_con_pag;
    String c_con_pag_det;
    float c_monto;
    Date c_fecha_pag;
    Date c_fecha_pro;
    String c_estado;
    float c_adeuda = 0;
    float c_saldo = 0;
    String c_dni_ruc;
    public Set<AdPago> listaPagos = new LinkedHashSet<AdPago>();
    int c_id_pago;
    int c_id_nombre_pago;
    int c_id_con_pag_pago;
    String c_sem_pago;
    int c_id_sem_pago;
    String c_fac_pago;
    int c_id_fac_pago;
    String c_esp_pago;
    int c_id_esp_pago;
    String c_codigo_pago;
    String c_nombre_pago;
    String c_con_pag_pago;
    String c_con_pag_det_pago;
    float c_monto_pago;
    Date c_fecha_pag_pago;
    Date c_fecha_pro_pago;
    String c_estado_pago;
    float c_adeuda_pago = 0;
    float c_saldo_pago = 0;
    String c_dni_ruc_pago;
    int zc_id_u;
    String zc_nombre_u;
    float zc_monto_u;
    String zc_num_com_u;
    Date zc_fecha_pag_u;
    String zc_con_pag_u;
    String zc_con_pag_det_u;
    String zc_id_tipo_com_u;
    int cli_id;
    String cli_nombre;
    String cli_razon;
    String cli_buscar;
    String cli_dni_ruc;
    String cli_codigo;
    String refrescar;
    float cli_saldo;
    String selCli = "";
    private List detalle_s;
    Date fecha_pago_det;
    float monto_det;
    private String tipo_det;
    int id_com_det;
    String num_com_det;
    String nombre_pago_det;
    String tipo_com_det;
    String id_tipo_com_det;
    /**********/
    String tip_com_det;
    String num_vou_det;
    Date fec_vou_det;
    String cod_age_det;
    /*********/
    int n_valor_nuevo = 0;
    int n_id_conpag;
    float n_monto;
    float n_saldo;
    String n_id_tipo_cliente;
    int n_cliente_fac;
    String n_razon_social;
    String n_id_tipo_nj;
    String n_dni_ruc;
    String n_id_procedencia;
    String n_num_comprobante;
    Date n_fecha_comprobante;
    float n_monto_pago;
    String n_des;
    String n_cod_alu;
    String n_nom_alu;
    int n_id_alu;
    public SelectItem[] comboCatalogo_tipo_cliente_nuevo;
    public SelectItem[] comboCatalogo_procedencia_nuevo;
    public SelectItem[] comboConceptoPago_nuevo;
    public SelectItem[] comboCatalogo_tipo_nj_nuevo;
    String semestre_vigente;
    String b_codigo;
    String b_compro;
    String b_estado;
    String b_dni_ruc;
    int b_id_sem;
    int b_id_conpag;
    int b_id_fac;
    int b_id_esp;
    String b_id_estado;
    List listaAlumno;
    List listaCliente;
    public String imagen = "/Imagenes/actions/down.png";
    private boolean ver = false;
    public String flag_ver = "0";
    public Date b_fecha1 = new Date();
    public Date b_fecha2 = new Date();
    String b_apMaterno;
    String b_apPaterno;
    private String oncomplete;
    private String oncomplete2;
    private String noncomplete;
    public String imagenP = "/Imagenes/actions/tesoreria.png";
    public String disabledP = "javascript:Richfaces.showModalPanel('mp',{width:100, top:50})";
    public String valor;
    int varx = 0;
    private String renderFecha = "true";
    private String mostrar_accion_pagar = "true";
    private String mostrar_accion_nuevo = "true";
    public List lista0;
    public List procedencias;
    public List tipoComprobante;
    private String cabecera1;
    private String cabecera2;
    private String cabecera3;
    private String cabecera4;
    private String view_lock;
    private String view_detail;
    private String ver_fechas;
    private float e_mora_pago;
    private boolean e_mora_seleccion = false;
    private float mora_diaria = 0;
    private float e_mat_expo;
    private boolean e_mat_expo_seleccion = false;
    private String view_print = "false";
    private String nro_comprobante = "0";
    private String mostrar_accion_personalizar;
    private String id_alumno;
    private String nro_oculto;
    public String id_alumno_1 = "";
    public String tipo_ = "";
    private String comprobante_oculto = "";
    private String nro_comprobante_oculto = "";
    private Date comprobante_fecha_oculto;
    private String comprobante_nombre_oculto = "";
    private String comprobante_monto_oculto = "";
    private String comprobante_concepto_oculto = "";
    private String comprobante_concepto_det_oculto = "";
    private String tipo_com = "";
    private String f1 = "";
    private String f2 = "";
    private String mostrar_ficha = "false";
    private String matricu_id;
    /*******************************************************************/
    private SelectItem[] comboTipoDocumento_comprobante;
    private String zc_tip_com_u;
    private Date zc_fec_vou_u;
    private String zc_num_vou_u;
    private String zc_cod_age_u;
    ///////////////////////////
    private String n_tip_com;
    private Date n_fec_vou;
    private String n_num_vou;
    private String n_cod_age;
    ///////////////////////////
    private String e_tip_com;
    private Date e_fec_vou;
    private String e_num_vou;
    private String e_cod_age;
    ///////////////////////////
    private String valorReporte = "";
    private String alertaCaja = "";
    private Integer pUsuID;
    private String pFecIni = "";
    private String pFecFin = "";
    private String jsImpresion;
/////////////////////
    private int id_alumno_2 = 0;
    private String nro_oculto2;
    private String tip_com_oculto2;
    private Date fec_oculto2;
    private float monto_oculto2;
    private int compag_id;
    private List<BeanConceptoPago> cp = new ArrayList();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public int getCompag_id() {
        return compag_id;
    }

    public void setCompag_id(int compag_id) {
        this.compag_id = compag_id;
    }

    public List<BeanConceptoPago> getCp() {
        return cp;
    }

    public void setCp(List<BeanConceptoPago> cp) {
        this.cp = cp;
    }

    public List<BeanConceptoPago> autocomplete(Object suggest) {
        String pref = (String) suggest;
        ArrayList<BeanConceptoPago> result = new ArrayList<BeanConceptoPago>();

        HSConceptoPagoDAO dao5 = (HSConceptoPagoDAO) ServiceFinder.findBean("SpringHibernateDaoConceptoPago");
        List lista5 = dao5.seleccionarConceptoPago();

        for (int i = 0; i < lista5.size(); i++) {
            AdConceptoPago acp = (AdConceptoPago) lista5.get(i);
            if (acp.getConpagDescripcion().toLowerCase().indexOf(pref.toLowerCase()) >= 0) {
                BeanConceptoPago pp = new BeanConceptoPago();
                pp.setId(acp.getId());
                pp.setDesc(acp.getConpagDescripcion());
                result.add(pp);
                pp.setMonto(acp.getConpagMonto());
                System.out.println(pp.getDesc());
            }
        }

        return result;
    }

    public bPago() throws Exception {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession(false);
        bUsuario usu = (bUsuario) session1.getAttribute("usuario");
        if (usu != null) {
            if (usu.getNivel().equals("0")) {
                mostrar_accion_nuevo = "false";
            }
            HSCatalogoDAO dao1 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List lista1 = dao1.seleccionarCatalogo("024");
            comboCatalogo_listaPagos = new SelectItem[lista1.size()];
            comboCatalogo_listaPagos[0] = new SelectItem(-1, "Todos");
            for (int i = 0; i < comboCatalogo_listaPagos.length; i++) {
                comboCatalogo_listaPagos[i] = new SelectItem(((TbCatalogo) lista1.get(i)).getCatValor(), ((TbCatalogo) lista1.get(i)).getCatDescripcionElemento());
            }

            HSCatalogoDAO dao2 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List lista2 = dao2.seleccionarCatalogo("013");
            comboCatalogo_estado = new SelectItem[lista2.size() + 1];
            comboCatalogo_estado[0] = new SelectItem(-1, "Todos");
            for (int i = 0; i < comboCatalogo_estado.length - 1; i++) {
                comboCatalogo_estado[i + 1] = new SelectItem(((TbCatalogo) lista2.get(i)).getCatValor(), ((TbCatalogo) lista2.get(i)).getCatDescripcionElemento());
            }

            HSFacultadDAO dao4 = (HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
            List lista4 = dao4.seleccionarFacultad("", "");
            comboFacultad_buscar = new SelectItem[lista4.size() + 1];
            comboFacultad_buscar[0] = new SelectItem(0, "Todas");
            for (int i = 0; i < comboFacultad_buscar.length - 1; i++) {
                comboFacultad_buscar[i + 1] = new SelectItem(((AcFacultad) lista4.get(i)).getId(), ((AcFacultad) lista4.get(i)).getFacNombre());
            }

            HSConceptoPagoDAO dao5 = (HSConceptoPagoDAO) ServiceFinder.findBean("SpringHibernateDaoConceptoPago");
            List lista5 = dao5.seleccionarConceptoPago();
            comboConceptoPago = new SelectItem[lista5.size() + 1];
            comboConceptoPago[0] = new SelectItem(0, "Todos");
            for (int i = 0; i < comboConceptoPago.length - 1; i++) {
                comboConceptoPago[i + 1] = new SelectItem(((AdConceptoPago) lista5.get(i)).getId(), (((AdConceptoPago) lista5.get(i)).getConpagDescripcion()));
            }

            HSSemestreDAO dao6 = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
            List lista6 = dao6.seleccionarSemestreActivo();
            comboSemestre = new SelectItem[lista6.size() + 1];
            comboSemestre[0] = new SelectItem(0, "Todos");
            for (int i = 0; i < comboSemestre.length - 1; i++) {
                comboSemestre[i + 1] = new SelectItem(((AcSemestre) lista6.get(i)).getId(), ((AcSemestre) lista6.get(i)).getSemNombre());
            }

            HSSemestreDAO dao = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
            semestre_vigente = dao.seleccionarSemestreVigente(1);

            HSCatalogoDAO daoCatalogo = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            procedencias = daoCatalogo.seleccionarGrupo("015");
            tipoComprobante = new ArrayList();
            tipoComprobante = daoCatalogo.seleccionarGrupo("037");
            lista0 = new ArrayList();
            lista0 = procedencias;

            HSCatalogoDAO dao7 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List lista7 = dao7.seleccionarCatalogo("014");
            comboCatalogo_tipo_cliente = new SelectItem[lista7.size() + 1];
            comboCatalogo_tipo_cliente[0] = new SelectItem(0, "[Seleccione]");
            for (int i = 0; i < comboCatalogo_tipo_cliente.length - 1; i++) {
                comboCatalogo_tipo_cliente[i + 1] = new SelectItem(((TbCatalogo) lista7.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista7.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista7.get(i)).getCatDescripcionElemento());
            }
            HSCatalogoDAO dao8 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List lista8 = dao8.seleccionarCatalogo("017");
            comboCatalogo_tipo_nj = new SelectItem[lista8.size() + 1];
            comboCatalogo_tipo_nj[0] = new SelectItem(0, "[Seleccione]");
            for (int i = 0; i < comboCatalogo_tipo_nj.length - 1; i++) {
                comboCatalogo_tipo_nj[i + 1] = new SelectItem(((TbCatalogo) lista8.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista8.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista8.get(i)).getCatDescripcionElemento());
            }

            HSConceptoPagoDAO dao9 = (HSConceptoPagoDAO) ServiceFinder.findBean("SpringHibernateDaoConceptoPago");
            List lista9 = dao9.seleccionarConceptoPagoGeneral();
            comboConceptoPago_nuevo = new SelectItem[lista9.size() + 1];
            comboConceptoPago_nuevo[0] = new SelectItem(0, "[Seleccione]");
            for (int i = 0; i < comboConceptoPago_nuevo.length - 1; i++) {
                comboConceptoPago_nuevo[i + 1] = new SelectItem(((AdConceptoPago) lista9.get(i)).getId(), (((AdConceptoPago) lista9.get(i)).getConpagDescripcion()));
            }

            comboCatalogo_procedencia = new SelectItem[procedencias.size() + 1];
            comboCatalogo_procedencia[0] = new SelectItem(0, "[Seleccione]");
            for (int i = 0; i < comboCatalogo_procedencia.length - 1; i++) {
                comboCatalogo_procedencia[i + 1] = new SelectItem(((TbCatalogo) procedencias.get(i)).getCatCodigoGrupo() + ((TbCatalogo) procedencias.get(i)).getCatCodigoElemento(), ((TbCatalogo) procedencias.get(i)).getCatDescripcionElemento());
            }


            comboCatalogo_procedencia2 = new SelectItem[procedencias.size()];
            for (int i = 0; i < comboCatalogo_procedencia2.length; i++) {
                comboCatalogo_procedencia2[i] = new SelectItem(((TbCatalogo) procedencias.get(i)).getCatCodigoGrupo() + ((TbCatalogo) procedencias.get(i)).getCatCodigoElemento(), ((TbCatalogo) procedencias.get(i)).getCatDescripcionElemento());
            }

            HSCatalogoDAO dao12 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List lista12 = dao12.seleccionarCatalogo("017");
            comboCatalogo_tipo_nj_nuevo = new SelectItem[lista12.size() + 1];
            comboCatalogo_tipo_nj_nuevo[0] = new SelectItem(0, "[Seleccione]");
            for (int i = 0; i < comboCatalogo_tipo_nj_nuevo.length - 1; i++) {
                comboCatalogo_tipo_nj_nuevo[i + 1] = new SelectItem(((TbCatalogo) lista12.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista12.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista12.get(i)).getCatDescripcionElemento());
            }

            HSCatalogoDAO dao13 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List lista13 = dao13.seleccionarCatalogo("014");
            comboCatalogo_tipo_cliente_nuevo = new SelectItem[lista13.size() + 1];
            comboCatalogo_tipo_cliente_nuevo[0] = new SelectItem(0, "[Seleccione]");
            for (int i = 0; i < comboCatalogo_tipo_cliente_nuevo.length - 1; i++) {
                comboCatalogo_tipo_cliente_nuevo[i + 1] = new SelectItem(((TbCatalogo) lista13.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista13.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista13.get(i)).getCatDescripcionElemento());
            }


            comboCatalogo_procedencia_nuevo = new SelectItem[procedencias.size() + 1];
            comboCatalogo_procedencia_nuevo[0] = new SelectItem(0, "[Seleccione]");
            for (int i = 0; i < comboCatalogo_procedencia_nuevo.length - 1; i++) {
                comboCatalogo_procedencia_nuevo[i + 1] = new SelectItem(((TbCatalogo) procedencias.get(i)).getCatCodigoGrupo() + ((TbCatalogo) procedencias.get(i)).getCatCodigoElemento(), ((TbCatalogo) procedencias.get(i)).getCatDescripcionElemento());
            }

            HSCatalogoDAO dao15 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List lista15 = dao15.seleccionarCatalogo("032");
            comboCatalogo_ReciboBloque = new SelectItem[lista15.size()];
            for (int i = 0; i < comboCatalogo_ReciboBloque.length; i++) {
                comboCatalogo_ReciboBloque[i] = new SelectItem(((TbCatalogo) lista15.get(i)).getCatDescripcionElemento(), ((TbCatalogo) lista15.get(i)).getCatDescripcionElemento());
            }
            cabecera1 = "Semestre";
            cabecera2 = "Especialidad";
            cabecera3 = "F. de Pro.";
            cabecera4 = "Est.";
            view_lock = "true";
            view_detail = "true";
            renderFecha = "false";

        } else {
            throw new Exception();
        }

    }

    public bPago(int parametro) {
    }

    public String getN_con_pag_nuevo() {
        return n_con_pag_nuevo;
    }

    public void setN_con_pag_nuevo(String n_con_pag_nuevo) {
        this.n_con_pag_nuevo = n_con_pag_nuevo;
    }

    public String getE_num_comprobante0() {
        return e_num_comprobante0;
    }

    public void setE_num_comprobante0(String e_num_comprobante0) {
        this.e_num_comprobante0 = e_num_comprobante0;
    }

    public String getN_num_comprobante0() {
        return n_num_comprobante0;
    }

    public void setN_num_comprobante0(String n_num_comprobante0) {
        this.n_num_comprobante0 = n_num_comprobante0;
    }

    public int getC_id_con_pag_det() {
        return c_id_con_pag_det;
    }

    public void setC_id_con_pag_det(int c_id_con_pag_det) {
        this.c_id_con_pag_det = c_id_con_pag_det;
    }

    public int getC_id_con_pag_det_u() {
        return c_id_con_pag_det_u;
    }

    public void setC_id_con_pag_det_u(int c_id_con_pag_det_u) {
        this.c_id_con_pag_det_u = c_id_con_pag_det_u;
    }

    public float getPer_monto() {
        return per_monto;
    }

    public void setPer_monto(float per_monto) {
        this.per_monto = per_monto;
    }

    public Date getPer_fecha_pag() {
        return per_fecha_pag;
    }

    public void setPer_fecha_pag(Date per_fecha_pag) {
        this.per_fecha_pag = per_fecha_pag;
    }

    public Date getPer_fecha_pro() {
        return per_fecha_pro;
    }

    public void setPer_fecha_pro(Date per_fecha_pro) {
        this.per_fecha_pro = per_fecha_pro;
    }

    public String getImagenP2() {
        return imagenP2;
    }

    public void setImagenP2(String imagenP2) {
        this.imagenP2 = imagenP2;
    }

    public String getDisabledP2() {
        return disabledP2;
    }

    public void setDisabledP2(String disabledP2) {
        this.disabledP2 = disabledP2;
    }

    public String getDisabledP3() {
        return disabledP3;
    }

    public void setDisabledP3(String disabledP3) {
        this.disabledP3 = disabledP3;
    }

    public int getZc_id_u() {
        return zc_id_u;
    }

    public void setZc_id_u(int zc_id_u) {
        this.zc_id_u = zc_id_u;
    }

    public String getZc_nombre_u() {
        return zc_nombre_u;
    }

    public void setZc_nombre_u(String zc_nombre_u) {
        this.zc_nombre_u = zc_nombre_u;
    }

    public float getZc_monto_u() {
        return zc_monto_u;
    }

    public void setZc_monto_u(float zc_monto_u) {
        this.zc_monto_u = zc_monto_u;
    }

    public String getZc_num_com_u() {
        return zc_num_com_u;
    }

    public void setZc_num_com_u(String zc_num_com_u) {
        this.zc_num_com_u = zc_num_com_u;
    }

    public Date getZc_fecha_pag_u() {
        return zc_fecha_pag_u;
    }

    public void setZc_fecha_pag_u(Date zc_fecha_pag_u) {
        this.zc_fecha_pag_u = zc_fecha_pag_u;
    }

    public String getZc_con_pag_u() {
        return zc_con_pag_u;
    }

    public void setZc_con_pag_u(String zc_con_pag_u) {
        this.zc_con_pag_u = zc_con_pag_u;
    }

    public String getZc_con_pag_det_u() {
        return zc_con_pag_det_u;
    }

    public void setZc_con_pag_det_u(String zc_con_pag_det_u) {
        this.zc_con_pag_det_u = zc_con_pag_det_u;
    }

    public String getZc_id_tipo_com_u() {
        return zc_id_tipo_com_u;
    }

    public void setZc_id_tipo_com_u(String zc_id_tipo_com_u) {
        this.zc_id_tipo_com_u = zc_id_tipo_com_u;
    }

    public String getNoncomplete() {
        return noncomplete;
    }

    public void setNoncomplete(String noncomplete) {
        this.noncomplete = noncomplete;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public String getOncomplete2() {
        return oncomplete2;
    }

    public void setOncomplete2(String oncomplete2) {
        this.oncomplete2 = oncomplete2;
    }

    public String getImagenP() {
        return imagenP;
    }

    public void setImagenP(String imagenP) {
        this.imagenP = imagenP;
    }

    public String getDisabledP() {
        return disabledP;
    }

    public void setDisabledP(String disabledP) {
        this.disabledP = disabledP;
    }

    public int getB_id_listaPag() {
        return b_id_listaPag;
    }

    public void setB_id_listaPag(int b_id_listaPag) {
        this.b_id_listaPag = b_id_listaPag;
    }

    public String getRefrescar() {
        return refrescar;
    }

    public void setRefrescar(String refrescar) {
        this.refrescar = refrescar;
    }

    public float getN_monto() {
        return n_monto;
    }

    public void setN_monto(float n_monto) {
        this.n_monto = n_monto;
    }

    public String getN_id_tipo_cliente() {
        return n_id_tipo_cliente;
    }

    public void setN_id_tipo_cliente(String n_id_tipo_cliente) {
        this.n_id_tipo_cliente = n_id_tipo_cliente;
    }

    public int getN_cliente_fac() {
        return n_cliente_fac;
    }

    public void setN_cliente_fac(int n_cliente_fac) {
        this.n_cliente_fac = n_cliente_fac;
    }

    public String getN_razon_social() {
        return n_razon_social;
    }

    public void setN_razon_social(String n_razon_social) {
        this.n_razon_social = n_razon_social;
    }

    public String getN_id_tipo_nj() {
        return n_id_tipo_nj;
    }

    public void setN_id_tipo_nj(String n_id_tipo_nj) {
        this.n_id_tipo_nj = n_id_tipo_nj;
    }

    public String getN_dni_ruc() {
        return n_dni_ruc;
    }

    public void setN_dni_ruc(String n_dni_ruc) {
        this.n_dni_ruc = n_dni_ruc;
    }

    public String getN_id_procedencia() {
        return n_id_procedencia;
    }

    public void setN_id_procedencia(String n_id_procedencia) {
        this.n_id_procedencia = n_id_procedencia;
    }

    public String getN_num_comprobante() {
        return n_num_comprobante;
    }

    public void setN_num_comprobante(String n_num_comprobante) {
        this.n_num_comprobante = n_num_comprobante;
    }

    public Date getN_fecha_comprobante() {
        return n_fecha_comprobante;
    }

    public void setN_fecha_comprobante(Date n_fecha_comprobante) {
        this.n_fecha_comprobante = n_fecha_comprobante;
    }

    public float getN_monto_pago() {
        return n_monto_pago;
    }

    public void setN_monto_pago(float n_monto_pago) {
        this.n_monto_pago = n_monto_pago;
    }

    public int getN_valor_nuevo() {
        return n_valor_nuevo;
    }

    public void setN_valor_nuevo(int n_valor_nuevo) {
        this.n_valor_nuevo = n_valor_nuevo;
    }

    public float getN_saldo() {
        return n_saldo;
    }

    public void setN_saldo(float n_saldo) {
        this.n_saldo = n_saldo;
    }

    public String getN_des() {
        return n_des;
    }

    public void setN_des(String n_des) {
        this.n_des = n_des;
    }

    public String getN_cod_alu() {
        return n_cod_alu;
    }

    public void setN_cod_alu(String n_cod_alu) {
        this.n_cod_alu = n_cod_alu;
    }

    public String getN_nom_alu() {
        return n_nom_alu;
    }

    public void setN_nom_alu(String n_nom_alu) {
        this.n_nom_alu = n_nom_alu;
    }

    public int getN_id_alu() {
        return n_id_alu;
    }

    public void setN_id_alu(int n_id_alu) {
        this.n_id_alu = n_id_alu;
    }
    //DECLARACION DE VARIBLES PRIVADAS

    public String getCli_buscar() {
        return cli_buscar;
    }

    public void setCli_buscar(String cli_buscar) {
        this.cli_buscar = cli_buscar;
    }

    public float getCli_saldo() {
        return cli_saldo;
    }

    public void setCli_saldo(float cli_saldo) {
        this.cli_saldo = cli_saldo;
    }

    public String getCli_codigo() {
        return cli_codigo;
    }

    public void setCli_codigo(String cli_codigo) {
        this.cli_codigo = cli_codigo;
    }
    //METODOS GET Y SET

    public String getB_codigo() {
        return b_codigo;
    }

    public void setB_codigo(String b_codigo) {
        this.b_codigo = b_codigo;
    }

    public String getB_compro() {
        return b_compro;
    }

    public void setB_compro(String b_compro) {
        this.b_compro = b_compro;
    }

    public String getB_estado() {
        return b_estado;
    }

    public void setB_estado(String b_estado) {
        this.b_estado = b_estado;
    }

    public int getB_id_sem() {
        return b_id_sem;
    }

    public void setB_id_sem(int b_id_sem) {
        this.b_id_sem = b_id_sem;
    }

    public int getB_id_conpag() {
        return b_id_conpag;
    }

    public void setB_id_conpag(int b_id_conpag) {
        this.b_id_conpag = b_id_conpag;
    }

    public int getB_id_fac() {
        return b_id_fac;
    }

    public void setB_id_fac(int b_id_fac) {
        this.b_id_fac = b_id_fac;
    }

    public int getB_id_esp() {
        return b_id_esp;
    }

    public void setB_id_esp(int b_id_esp) {
        this.b_id_esp = b_id_esp;
    }

    public String getB_id_estado() {
        return b_id_estado;
    }

    public void setB_id_estado(String b_id_estado) {
        this.b_id_estado = b_id_estado;
    }

    public String getC_dni_ruc() {
        return c_dni_ruc;
    }

    public void setC_dni_ruc(String c_dni_ruc) {
        this.c_dni_ruc = c_dni_ruc;
    }

    public List getListaAlumno() {
        return listaAlumno;
    }

    public void setListaAlumno(List listaAlumno) {
        this.listaAlumno = listaAlumno;
    }

    public List getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List listaCliente) {
        this.listaCliente = listaCliente;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isVer() {
        return ver;
    }

    public void setVer(boolean ver) {
        this.ver = ver;
    }

    public String getFlag_ver() {
        return flag_ver;
    }

    public void setFlag_ver(String flag_ver) {
        this.flag_ver = flag_ver;
    }

    public Date getB_fecha1() {
        return b_fecha1;
    }

    public void setB_fecha1(Date b_fecha1) {
        this.b_fecha1 = b_fecha1;
    }

    public Date getB_fecha2() {
        return b_fecha2;
    }

    public void setB_fecha2(Date b_fecha2) {
        this.b_fecha2 = b_fecha2;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getC_id_nombre() {
        return c_id_nombre;
    }

    public void setC_id_nombre(int c_id_nombre) {
        this.c_id_nombre = c_id_nombre;
    }

    public int getC_id_con_pag() {
        return c_id_con_pag;
    }

    public void setC_id_con_pag(int c_id_con_pag) {
        this.c_id_con_pag = c_id_con_pag;
    }

    public String getC_sem() {
        return c_sem;
    }

    public void setC_sem(String c_sem) {
        this.c_sem = c_sem;
    }

    public int getC_id_sem() {
        return c_id_sem;
    }

    public void setC_id_sem(int c_id_sem) {
        this.c_id_sem = c_id_sem;
    }

    public String getC_fac() {
        return c_fac;
    }

    public void setC_fac(String c_fac) {
        this.c_fac = c_fac;
    }

    public int getC_id_fac() {
        return c_id_fac;
    }

    public void setC_id_fac(int c_id_fac) {
        this.c_id_fac = c_id_fac;
    }

    public String getC_esp() {
        return c_esp;
    }

    public void setC_esp(String c_esp) {
        this.c_esp = c_esp;
    }

    public int getC_id_esp() {
        return c_id_esp;
    }

    public void setC_id_esp(int c_id_esp) {
        this.c_id_esp = c_id_esp;
    }

    public String getC_codigo() {
        return c_codigo;
    }

    public void setC_codigo(String c_codigo) {
        this.c_codigo = c_codigo;
    }

    public String getC_nombre() {
        return c_nombre;
    }

    public void setC_nombre(String c_nombre) {
        this.c_nombre = c_nombre;
    }

    public String getC_con_pag() {
        return c_con_pag;
    }

    public void setC_con_pag(String c_con_pag) {
        this.c_con_pag = c_con_pag;
    }

    public String getC_con_pag_det() {
        return c_con_pag_det;
    }

    public void setC_con_pag_det(String c_con_pag_det) {
        this.c_con_pag_det = c_con_pag_det;
    }

    public float getC_monto() {
        return c_monto;
    }

    public void setC_monto(float c_monto) {
        this.c_monto = c_monto;
    }

    public Date getC_fecha_pag() {
        return c_fecha_pag;
    }

    public void setC_fecha_pag(Date c_fecha_pag) {
        this.c_fecha_pag = c_fecha_pag;
    }

    public Date getC_fecha_pro() {
        return c_fecha_pro;
    }

    public void setC_fecha_pro(Date c_fecha_pro) {
        this.c_fecha_pro = c_fecha_pro;
    }

    public String getC_estado() {
        return c_estado;
    }

    public void setC_estado(String c_estado) {
        this.c_estado = c_estado;
    }

    public float getC_adeuda() {
        return c_adeuda;
    }

    public void setC_adeuda(float c_adeuda) {
        this.c_adeuda = c_adeuda;
    }

    public float getC_saldo() {
        return c_saldo;
    }

    public void setC_saldo(float c_saldo) {
        this.c_saldo = c_saldo;
    }

    public String getB_dni_ruc() {
        return b_dni_ruc;
    }

    public void setB_dni_ruc(String b_dni_ruc) {
        this.b_dni_ruc = b_dni_ruc;
    }

    public String getE_id_tipo_cliente() {
        return e_id_tipo_cliente;
    }

    public void setE_id_tipo_cliente(String e_id_tipo_cliente) {
        this.e_id_tipo_cliente = e_id_tipo_cliente;
    }

    public String getE_razon_social() {
        return e_razon_social;
    }

    public void setE_razon_social(String e_razon_social) {
        this.e_razon_social = e_razon_social;
    }

    public String getE_id_tipo_nj() {
        return e_id_tipo_nj;
    }

    public void setE_id_tipo_nj(String e_id_tipo_nj) {
        this.e_id_tipo_nj = e_id_tipo_nj;
    }

    public String getE_id_procedencia() {
        return e_id_procedencia;
    }

    public void setE_id_procedencia(String e_id_procedencia) {
        this.e_id_procedencia = e_id_procedencia;
    }

    public String getE_num_comprobante() {
        return e_num_comprobante;
    }

    public void setE_num_comprobante(String e_num_comprobante) {
        this.e_num_comprobante = e_num_comprobante;
    }

    public Date getE_fecha_comprobante() {
        return e_fecha_comprobante;
    }

    public void setE_fecha_comprobante(Date e_fecha_comprobante) {
        this.e_fecha_comprobante = e_fecha_comprobante;
    }

    public int getE_cliente_fac() {
        return e_cliente_fac;
    }

    public void setE_cliente_fac(int e_cliente_fac) {
        this.e_cliente_fac = e_cliente_fac;
    }

    public float getE_monto_pago() {
        return e_monto_pago;
    }

    public void setE_monto_pago(float e_monto_pago) {
        this.e_monto_pago = e_monto_pago;
    }

    public String getE_dni_ruc() {
        return e_dni_ruc;
    }

    public void setE_dni_ruc(String e_dni_ruc) {
        this.e_dni_ruc = e_dni_ruc;
    }

    public int getCli_id() {
        return cli_id;
    }

    public void setCli_id(int cli_id) {
        this.cli_id = cli_id;
    }

    public String getCli_nombre() {
        return cli_nombre;
    }

    public void setCli_nombre(String cli_nombre) {
        this.cli_nombre = cli_nombre;
    }

    public String getCli_razon() {
        return cli_razon;
    }

    public void setCli_razon(String cli_razon) {
        this.cli_razon = cli_razon;
    }

    public String getCli_dni_ruc() {
        return cli_dni_ruc;
    }

    public void setCli_dni_ruc(String cli_dni_ruc) {
        this.cli_dni_ruc = cli_dni_ruc;
    }

    public String getSelCli() {
        return selCli;
    }

    public void setSelCli(String selCli) {
        this.selCli = selCli;
    }

    public List getDetalle_s() {
        return detalle_s;
    }

    public void setDetalle_s(List detalle_s) {
        this.detalle_s = detalle_s;
    }

    public Date getFecha_pago_det() {
        return fecha_pago_det;
    }

    public void setFecha_pago_det(Date fecha_pago_det) {
        this.fecha_pago_det = fecha_pago_det;
    }

    public float getMonto_det() {
        return monto_det;
    }

    public void setMonto_det(float monto_det) {
        this.monto_det = monto_det;
    }

    public int getId_com_det() {
        return id_com_det;
    }

    public void setId_com_det(int id_com_det) {
        this.id_com_det = id_com_det;
    }

    public int getN_id_conpag() {
        return n_id_conpag;
    }

    public void setN_id_conpag(int n_id_conpag) {
        this.n_id_conpag = n_id_conpag;
    }

    public String getNum_com_det() {
        return num_com_det;
    }

    public void setNum_com_det(String num_com_det) {
        this.num_com_det = num_com_det;
    }

    public String getNombre_pago_det() {
        return nombre_pago_det;
    }

    public void setNombre_pago_det(String nombre_pago_det) {
        this.nombre_pago_det = nombre_pago_det;
    }

    public String getTipo_com_det() {
        return tipo_com_det;
    }

    public void setTipo_com_det(String tipo_com_det) {
        this.tipo_com_det = tipo_com_det;
    }

    public String getId_tipo_com_det() {
        return id_tipo_com_det;
    }

    public void setId_tipo_com_det(String id_tipo_com_det) {
        this.id_tipo_com_det = id_tipo_com_det;
    }

    public SelectItem[] getComboCatalogo_estado() throws Exception {
        return comboCatalogo_estado;
    }

    public void setComboCatalogo_estado(SelectItem[] comboCatalogo_estado) {
        this.comboCatalogo_estado = comboCatalogo_estado;
    }

    public SelectItem[] getComboSemestre() throws Exception {
        return comboSemestre;
    }

    public void setComboSemestre(SelectItem[] comboSemestre) {
        this.comboSemestre = comboSemestre;
    }

    public String getSemestre_vigente() throws Exception {
        return semestre_vigente;
    }

    public void setSemestre_vigente(String semestre_vigente) {
        this.semestre_vigente = semestre_vigente;
    }

    public SelectItem[] getComboConceptoPago() throws Exception {
        return comboConceptoPago;
    }

    public void setComboConceptoPago(SelectItem[] comboConceptoPago) {
        this.comboConceptoPago = comboConceptoPago;
    }

    public SelectItem[] getComboFacultad_buscar() throws Exception {
        return comboFacultad_buscar;
    }

    public void setComboFacultad_buscar(SelectItem[] comboFacultad) {
        this.comboFacultad_buscar = comboFacultad;
    }

    public SelectItem[] getComboEspecialidad_buscar() throws Exception {
        cambiarEspecialidad_buscar(b_id_fac);
        return comboEspecialidad_buscar;
    }

    public void setComboEspecialidad_buscar(SelectItem[] comboEspecialidad_buscar) {
        this.comboEspecialidad_buscar = comboEspecialidad_buscar;
    }

    public void cambiarEspecialidad_buscar(int id) throws Exception {
        HSEspecialidadDAO dao = (HSEspecialidadDAO) ServiceFinder.findBean("SpringHibernateDaoEspecialidad");
        List lista = dao.seleccionarEspecialidad(id);
        comboEspecialidad_buscar = new SelectItem[lista.size() + 1];
        comboEspecialidad_buscar[0] = new SelectItem(0, "[Seleccione]");
        for (int i = 0; i < comboEspecialidad_buscar.length - 1; i++) {
            comboEspecialidad_buscar[i + 1] = new SelectItem(((AcEspecialidad) lista.get(i)).getId(), ((AcEspecialidad) lista.get(i)).getEspNombre());
        }
        this.setComboEspecialidad_buscar(comboEspecialidad_buscar);
    }

    public SelectItem[] getComboCatalogo_listaPagos() throws Exception {
        return comboCatalogo_listaPagos;
    }

    public void setComboCatalogo_listaPagos(SelectItem[] comboCatalogo_listaPagos) {
        this.comboCatalogo_listaPagos = comboCatalogo_listaPagos;
    }

    public void Nuevo(ActionEvent event) throws Exception {
        HSPagoDAO daoP = (HSPagoDAO) ServiceFinder.findBean("SpringHibernateDaoPago");
        String numero = daoP.MaximoNroComprobante("001");
        String num0;
        String nr;
        if (numero.equals("0")) {
            num0 = "001";
            nr = "0000001";
        } else {
            num0 = numero.substring(0, 3);
            String num = numero.substring(4, 11);
            int numInt = 0;
            numInt = Integer.parseInt(num);
            numInt = numInt + 1;
            nr = numInt + "";
            for (int h = 1; h <= 7 - (numInt + "").length(); h++) {
                nr = "0" + nr;
            }
        }
        this.setN_valor_nuevo(1);
        this.setN_id_conpag(0);
        this.setN_monto(0);
        this.setN_saldo(0);
        this.setN_id_tipo_cliente("0");
        this.setN_cliente_fac(0);
        this.setN_razon_social("");
        this.setN_id_tipo_nj("0");
        this.setN_dni_ruc("");
        this.setN_id_procedencia("015002");
        this.setN_num_comprobante0(num0);
        this.setN_num_comprobante(nr);
        this.setN_fecha_comprobante(new Date());
        this.setN_monto_pago(0);
        this.setN_des("");
        this.setN_cod_alu("");
        this.setN_id_alu(0);
        this.setN_nom_alu("");
        /*****************/
        this.comboTipoDocumento_comprobante = getComboTipoDocumento_comprobante();
        this.setN_tip_com("039001");
        this.setN_num_vou("");
        this.setN_fec_vou(new Date());
        this.setN_cod_age("");
        /*****************/
    }

    public void cambiaSerieE() {
        System.out.println("numero_serie=" + e_num_comprobante0);
        setE_num_comprobante(serie(e_num_comprobante0));
    }

    public void cambiaSerieN() {
        System.out.println("numero_serie=" + n_num_comprobante0);
        setN_num_comprobante(serie(n_num_comprobante0));
    }

    public String serie(String serie) {
        HSPagoDAO daoP = (HSPagoDAO) ServiceFinder.findBean("SpringHibernateDaoPago");
        String numero = daoP.MaximoNroComprobante(serie);
        String nr;
        if (numero.equals("0")) {
            nr = "0000001";
        } else {
            if (numero.length() >= 11) {
                //System.out.println(numero.length());
                String num = numero.substring(4, 11);
                int numInt = 0;
                numInt = Integer.parseInt(num);
                numInt = numInt + 1;
                nr = numInt + "";
                for (int h = 1; h <= 7 - (numInt + "").length(); h++) {
                    nr = "0" + nr;
                }
            } else {
                nr = "0000000";
            }
        }
        return nr;
    }

    private boolean validarTextoVacios() {
        if (this.getB_codigo().trim().length() == 0
                && this.getB_apPaterno().trim().length() == 0
                && this.getB_apMaterno().trim().length() == 0) {
            System.out.println("todo vacio ¬¬");
            return false;
        }
        return true;
    }

    public void Seleccionar() throws Exception {
        if (validarTextoVacios()) {
            listaAlumno = new ArrayList();
            HSAlumnoTarifaDAO dao = (HSAlumnoTarifaDAO) ServiceFinder.findBean("SpringHibernateDaoAlumnoTarifa");
            HSAlumnoDAO daoAlu = (HSAlumnoDAO) ServiceFinder.findBean("SpringHibernateDaoAlumno");
            HSClienteDAO daoCli = (HSClienteDAO) ServiceFinder.findBean("SpringHibernateDaoCliente");
            HSEstructuraPagoDAO daoEstPagos = (HSEstructuraPagoDAO) ServiceFinder.findBean("SpringHibernateDaoEstructuraPagos");
            HSPagoDAO daoPago = (HSPagoDAO) ServiceFinder.findBean("SpringHibernateDaoPago");
            bPago pag;

            if (this.getB_id_listaPag() == 1) {
                this.setCabecera1("Semestre");
                this.setCabecera2("Especialidad");
                this.setCabecera3("F. de Pro.");
                this.setCabecera4("Est.");
                this.setView_detail("true");
                this.setView_lock("true");
                this.setView_print("false");
                List lista = dao.seleccionarAlumnoTarifa(this.getB_codigo(), this.getB_id_estado(), this.getB_id_esp(), this.getB_id_fac(), this.getB_id_conpag(), this.getB_apPaterno(), this.getB_apMaterno(), this.getB_id_sem());
                List listaEstructuraPagosDetalleGeneral = daoEstPagos.seleccionarEstructuraPagosDet();
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                HttpSession session1 = (HttpSession) context.getSession(false);
                bUsuario usu = (bUsuario) session1.getAttribute("usuario");
                for (int i = 0; i < lista.size(); i++) {
                    float pagy = 0;
                    pag = new bPago(0);
                    pag.setMostrar_ficha("false");
                    if (usu.getNivel().equals("0")) {
                        pag.setMostrar_accion_pagar("false");
                        pag.setMostrar_ficha("true");
                        pag.setMostrar_accion_personalizar("true");
                    }
                    pag.setC_id(((AdAlumnoTarifa) lista.get(i)).getId());
                    pag.setC_id_nombre(((AdAlumnoTarifa) lista.get(i)).getAlu().getId());
                    pag.setC_id_con_pag(((AdAlumnoTarifa) lista.get(i)).getConpag().getId());
                    pag.setComprobante_oculto("0");
                    for (int d = 0; d < listaEstructuraPagosDetalleGeneral.size(); d++) {
                        if ((((AdEstructuraPagosDetalle) listaEstructuraPagosDetalleGeneral.get(d)).getId()).equals(((AdAlumnoTarifa) lista.get(i)).getEstpagdet().getId())) {
                            pag.setC_sem(((AdEstructuraPagosDetalle) listaEstructuraPagosDetalleGeneral.get(d)).getEstpag().getSem().getSemCodigo());
                            pag.setC_id_sem(((AdEstructuraPagosDetalle) listaEstructuraPagosDetalleGeneral.get(d)).getEstpag().getSem().getId());
                            pag.setC_fac(((AdEstructuraPagosDetalle) listaEstructuraPagosDetalleGeneral.get(d)).getEstpag().getEsp().getFac().getFacNombre());
                            pag.setC_id_fac(((AdEstructuraPagosDetalle) listaEstructuraPagosDetalleGeneral.get(d)).getEstpag().getEsp().getFac().getId());
                            pag.setC_esp(((AdEstructuraPagosDetalle) listaEstructuraPagosDetalleGeneral.get(d)).getEstpag().getEsp().getEspNombre());
                            pag.setC_id_esp(((AdEstructuraPagosDetalle) listaEstructuraPagosDetalleGeneral.get(d)).getEstpag().getEsp().getId());
                            pag.setC_con_pag_det(((AdEstructuraPagosDetalle) listaEstructuraPagosDetalleGeneral.get(d)).getEstpagdetNombre());
                            pag.setC_id_con_pag_det(((AdEstructuraPagosDetalle) listaEstructuraPagosDetalleGeneral.get(d)).getId());
                        }
                    }
                    pagy = ((AdAlumnoTarifa) lista.get(i)).getAlutarMonto();
                    pag.setC_dni_ruc(((AdAlumnoTarifa) lista.get(i)).getAlu().getAluDni());
                    pag.setId_alumno(((AdAlumnoTarifa) lista.get(i)).getAlu().getId().toString());
                    pag.setC_codigo(((AdAlumnoTarifa) lista.get(i)).getAlu().getAluCodigo());
                    pag.setC_nombre(((AdAlumnoTarifa) lista.get(i)).getAlu().getAluAppaterno() + " " + ((AdAlumnoTarifa) lista.get(i)).getAlu().getAluApmaterno() + " " + ((AdAlumnoTarifa) lista.get(i)).getAlu().getAluNombres());
                    pag.setC_con_pag(((AdAlumnoTarifa) lista.get(i)).getConpag().getConpagDescripcion());
                    pag.setC_monto(((AdAlumnoTarifa) lista.get(i)).getAlutarMonto());
                    pag.setC_fecha_pag(((AdAlumnoTarifa) lista.get(i)).getAlutarFechaPago());
                    pag.setC_fecha_pro(((AdAlumnoTarifa) lista.get(i)).getAlutarFechaProrroga());
                    if ((((AdAlumnoTarifa) lista.get(i)).getAlutarEstado()).equals("2")) {
                        pag.setC_estado("PT");
                        pag.setImagenP("/Imagenes/actions/cerrado.png");
                        pag.setDisabledP("");
                        pag.setImagenP2("/Imagenes/actions/editpaste_gris.png");
                        pag.setDisabledP2("");
                    } else if ((((AdAlumnoTarifa) lista.get(i)).getAlutarEstado()).equals("1")) {
                        pag.setC_estado("PP");
                    } else if ((((AdAlumnoTarifa) lista.get(i)).getAlutarEstado()).equals("0")) {
                        pag.setC_estado("D");
                    }
                    List lstPagos = daoPago.seleccionarMostrarPagos(((AdAlumnoTarifa) lista.get(i)).getId());
                    float pagx = 0;
                    if (!lstPagos.isEmpty()) {
                        for (int k = 0; k < lstPagos.size(); k++) {
                            if (((AdPago) lstPagos.get(k)).getCompag().getCompagTipo().equals("037001") && ((AdPago) lstPagos.get(k)).getCompag().getCompagActivo().equals("1")) {
                                pagx = pagx + ((AdPago) lstPagos.get(k)).getPagMonto();
                            }
                        }
                    }
                    pag.setC_adeuda(pagy - pagx);
                    pag.setC_saldo(0);
                    List compagos = new ArrayList();
                    pag.setDetalle_s(compagos);
                    pag.setView_detail("true");
                    pag.setView_print("false");
                    pag.setView_lock("true");
                    listaAlumno.add(pag);
                }
            }
            if (this.getB_id_listaPag() == 2) {
                this.setCabecera1("Nro-Comprobante");
                this.setCabecera2("Tipo");
                this.setCabecera3("F. de Pago");
                this.setCabecera4("Pago");
                this.setView_detail("false");
                this.setView_print("true");
                this.setView_lock("false");
                List lista2 = daoPago.seleccionarMostrarPagosExternos(0, this.getB_fecha1(), this.getB_fecha2(), this.getB_id_conpag());
                if (!lista2.isEmpty()) {
                    for (int i = 0; i < lista2.size(); i++) {
                        pag = new bPago(0);
                        pag.setC_id(0);
                        pag.setC_id_nombre(0);
                        Set<AdPago> pagos = new LinkedHashSet<AdPago>();
                        pagos = ((AdComprobantePago) lista2.get(i)).getAdPagos();
                        List lPagos = Collections.synchronizedList(new LinkedList(pagos));
                        pag.setC_id_con_pag(((AdPago) lPagos.get(0)).getConpag().getId());
                        pag.setMostrar_accion_pagar("false");
                        pag.setComprobante_oculto(((AdComprobantePago) lista2.get(i)).getId().toString());
                        pag.setNro_comprobante_oculto(((AdComprobantePago) lista2.get(i)).getCompagNro());
                        pag.setComprobante_fecha_oculto(((AdComprobantePago) lista2.get(i)).getCompagFecha());
                        pag.setComprobante_monto_oculto(((AdComprobantePago) lista2.get(i)).getCompagMonto().toString());

                        pag.setComprobante_concepto_oculto(((AdPago) lPagos.get(0)).getConpag().getConpagDescripcion());
                        pag.setComprobante_concepto_det_oculto(((AdPago) lPagos.get(0)).getConpag().getConpagDescripcion());
                        pag.setC_con_pag(((AdPago) lPagos.get(0)).getConpag().getConpagDescripcion());
                        pag.setC_con_pag_det(((AdPago) lPagos.get(0)).getConpag().getConpagDescripcion());//para que la descripcion sea uniforme

                        pag.setC_monto(((AdPago) lPagos.get(0)).getConpag().getConpagMonto());
                        pag.setC_fecha_pag(((AdPago) lPagos.get(0)).getPagFecha());
                        pag.setC_adeuda(((AdPago) lPagos.get(0)).getConpag().getConpagMonto() - Float.parseFloat(((AdComprobantePago) lista2.get(i)).getCompagMonto().toString()));///monto que adeuda actulamente

                        pag.setC_id_sem(0);
                        pag.setC_fac("-");
                        pag.setC_id_fac(0);
                        pag.setC_id_esp(0);
                        pag.setCompag_id(((AdComprobantePago) lista2.get(i)).getId());
                        pag.setC_fecha_pro(((AdComprobantePago) lista2.get(i)).getCompagFecha());
                        for (int k = 0; k < tipoComprobante.size(); k++) {
                            if (((AdComprobantePago) lista2.get(i)).getCompagTipo().equals(((TbCatalogo) tipoComprobante.get(k)).getCatCodigoGrupo() + ((TbCatalogo) tipoComprobante.get(k)).getCatCodigoElemento())) {
                                pag.setTipo_com(((TbCatalogo) tipoComprobante.get(k)).getCatDescripcionElemento());
                            }
                        }
                        pag.setNro_comprobante("" + ((AdComprobantePago) lista2.get(i)).getId());
                        pag.setC_sem(((AdComprobantePago) lista2.get(i)).getCompagNro());
                        pag.setC_estado(((AdComprobantePago) lista2.get(i)).getCompagMonto().toString());
                        for (int r = 0; r < lista0.size(); r++) {
                            if (((AdComprobantePago) lista2.get(i)).getCompagProcedencia().equals(((TbCatalogo) lista0.get(r)).getCatCodigoGrupo() + ((TbCatalogo) lista0.get(r)).getCatCodigoElemento())) {
                                pag.setC_esp(((TbCatalogo) lista0.get(r)).getCatDescripcionElemento());
                            }
                        }
                        List lAlumno = daoAlu.seleccionarUnAlumno(((AdComprobantePago) lista2.get(i)).getCompagAlu());
                        if (!lAlumno.isEmpty()) {
                            pag.setC_dni_ruc(((AcAlumno) lAlumno.get(0)).getAluDni());
                            pag.setC_codigo(((AcAlumno) lAlumno.get(0)).getAluCodigo());
                            pag.setC_nombre(((AcAlumno) lAlumno.get(0)).getAluAppaterno() + " " + ((AcAlumno) lAlumno.get(0)).getAluApmaterno() + " " + ((AcAlumno) lAlumno.get(0)).getAluNombres());
                            pag.setComprobante_nombre_oculto(((AcAlumno) lAlumno.get(0)).getAluAppaterno() + " " + ((AcAlumno) lAlumno.get(0)).getAluApmaterno() + " " + ((AcAlumno) lAlumno.get(0)).getAluNombres());
                        } else {
                            pag.setC_codigo("-");
                            if (((AdComprobantePago) lista2.get(i)).getCompagClienteTipo().equals("014001")) {
                                List listCli = daoAlu.seleccionarUnAlumno(((AdComprobantePago) lista2.get(i)).getCompagCliente());
                                if (!listCli.isEmpty()) {
                                    pag.setC_nombre(((AcAlumno) listCli.get(0)).getAluAppaterno() + " " + ((AcAlumno) listCli.get(0)).getAluApmaterno() + " " + ((AcAlumno) listCli.get(0)).getAluNombres());
                                    pag.setComprobante_nombre_oculto(((AcAlumno) listCli.get(0)).getAluAppaterno() + " " + ((AcAlumno) listCli.get(0)).getAluApmaterno() + " " + ((AcAlumno) listCli.get(0)).getAluNombres());
                                } else {
                                    pag.setC_nombre("");
                                }
                            }
                            if (((AdComprobantePago) lista2.get(i)).getCompagClienteTipo().equals("014002")) {
                                List listCli = daoCli.seleccionarUnCliente(((AdComprobantePago) lista2.get(i)).getCompagCliente());
                                if (!listCli.isEmpty()) {
                                    pag.setC_nombre(((AdClientes) listCli.get(0)).getCliRazonSocial());
                                    pag.setComprobante_nombre_oculto(((AdClientes) listCli.get(0)).getCliRazonSocial());
                                } else {
                                    pag.setC_nombre("");
                                }
                            }
                            pag.setC_codigo("EXTERNO");
                        }
                        pag.setImagenP("/Imagenes/actions/cerrado.png");
                        pag.setDisabledP("");
                        pag.setImagenP2("/Imagenes/actions/editpaste_gris.png");
                        pag.setDisabledP2("");
                        pag.setC_saldo(0);
                        pag.setView_print("true");
                        List compagos = new ArrayList();
                        pag.setDetalle_s(compagos);
                        listaAlumno.add(pag);
                    }
                }
            }
            this.setListaAlumno(listaAlumno);
        }
    }

    public void Ver(ActionEvent event) throws Exception {
        UIParameter flag = (UIParameter) event.getComponent().findComponent("flag");
        if (flag.getValue().toString().equals("0")) {
            UIParameter id_alutar = (UIParameter) event.getComponent().findComponent("id_x");
            HSCatalogoDAO dao1 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            int mora = Integer.parseInt(dao1.seleccionarValor("021004"));
            int matexp = Integer.parseInt(dao1.seleccionarValor("021005"));
            int carne_bib = Integer.parseInt(dao1.seleccionarValor("021006"));
            HSCatalogoDAO daoCatalogo = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            procedencias = daoCatalogo.seleccionarGrupo("015");
            tipoComprobante = daoCatalogo.seleccionarGrupo("037");
            listaAlumno = new ArrayList();
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession session1 = (HttpSession) context.getSession(false);
            bUsuario usu = (bUsuario) session1.getAttribute("usuario");
            HSPagoDAO daoPago = (HSPagoDAO) ServiceFinder.findBean("SpringHibernateDaoPago");
            HSAlumnoDAO daoAlu = (HSAlumnoDAO) ServiceFinder.findBean("SpringHibernateDaoAlumno");
            HSClienteDAO daoCli = (HSClienteDAO) ServiceFinder.findBean("SpringHibernateDaoCliente");
            List listaPago = daoPago.seleccionarMostrarPagosMora(Integer.parseInt(id_alutar.getValue().toString()));
            List compagos = new ArrayList();
            if (!listaPago.isEmpty()) {
                bPago compag;
                for (int j = 0; j < listaPago.size(); j++) {
                    if (((AdPago) listaPago.get(j)).getCompag().getCompagActivo().equals("1")) {
                        compag = new bPago(0);
                        compag.setNum_com_det(((AdPago) listaPago.get(j)).getCompag().getCompagNro());
                        if ((((AdPago) listaPago.get(j)).getCompag().getCompagClienteTipo()).equals("014001")) {
                            List listCli1 = daoAlu.seleccionarUnAlumno(((AdPago) listaPago.get(j)).getCompag().getCompagCliente());
                            if (!listCli1.isEmpty()) {
                                compag.setNombre_pago_det(((AcAlumno) listCli1.get(0)).getAluAppaterno() + " " + ((AcAlumno) listCli1.get(0)).getAluApmaterno() + " " + ((AcAlumno) listCli1.get(0)).getAluNombres());
                            } else {
                                compag.setNombre_pago_det("");
                            }
                        }
                        if ((((AdPago) listaPago.get(j)).getCompag().getCompagClienteTipo()).equals("014002")) {
                            List listCli2 = daoCli.seleccionarUnCliente(((AdPago) listaPago.get(j)).getCompag().getCompagCliente());
                            if (!listCli2.isEmpty()) {
                                compag.setNombre_pago_det(((AdClientes) listCli2.get(0)).getCliRazonSocial());
                            } else {
                                compag.setNombre_pago_det("");
                            }
                        }
                        if (usu.getNivel().equals("0")) {
                            compag.setMostrar_accion_pagar("false");
                        }
                        compag.setMostrar_accion_personalizar("true");
                        compag.setMonto_det(((AdPago) listaPago.get(j)).getPagMonto());
                        for (int w = 0; w < tipoComprobante.size(); w++) {
                            if ((((TbCatalogo) tipoComprobante.get(w)).getCatCodigoGrupo() + ((TbCatalogo) tipoComprobante.get(w)).getCatCodigoElemento()).equals(((AdPago) listaPago.get(j)).getCompag().getCompagTipo())) {
                                compag.setTipo_det(((TbCatalogo) tipoComprobante.get(w)).getCatDescripcionElemento());
                            }
                        }
                        compag.setFecha_pago_det(((AdPago) listaPago.get(j)).getCompag().getCompagFecha());
                        compag.setId_com_det(((AdPago) listaPago.get(j)).getCompag().getId());
                        compag.setId_tipo_com_det(((AdPago) listaPago.get(j)).getCompag().getCompagProcedencia());
                        for (int w = 0; w < procedencias.size(); w++) {
                            if ((((TbCatalogo) procedencias.get(w)).getCatCodigoGrupo() + ((TbCatalogo) procedencias.get(w)).getCatCodigoElemento()).equals(((AdPago) listaPago.get(j)).getCompag().getCompagProcedencia())) {
                                compag.setTipo_com_det(((TbCatalogo) procedencias.get(w)).getCatDescripcionElemento());
                                if (mora == ((AdPago) listaPago.get(j)).getConpag().getId()) {
                                    compag.setTipo_com_det(((TbCatalogo) procedencias.get(w)).getCatDescripcionElemento() + "-Mora");
                                }
                                if (matexp == ((AdPago) listaPago.get(j)).getConpag().getId()) {
                                    compag.setTipo_com_det(((TbCatalogo) procedencias.get(w)).getCatDescripcionElemento() + "- Mat Extempo.");
                                }
                                if (carne_bib == ((AdPago) listaPago.get(j)).getConpag().getId()) {
                                    compag.setTipo_com_det(((TbCatalogo) procedencias.get(w)).getCatDescripcionElemento() + "- CARNE BIBLIO.");
                                }
                            }
                        }
                        compag.setTip_com_det(((AdPago) listaPago.get(j)).getCompag().getCompagTipDoc());
                        compag.setNum_vou_det(((AdPago) listaPago.get(j)).getCompag().getCompagVoucherNroOperacion());
                        compag.setFec_vou_det(((AdPago) listaPago.get(j)).getCompag().getCompagVoucherFecha());
                        compag.setCod_age_det(((AdPago) listaPago.get(j)).getCompag().getCompagVoucherCodAgencia());
                        compagos.add(compag);
                    }
                }
            }
            this.setDetalle_s(compagos);
            this.setVer(true);
            this.setFlag_ver("1");
            this.setImagen("/Imagenes/actions/up.png");
        } else {
            this.setVer(false);
            this.setFlag_ver("0");
            this.setImagen("/Imagenes/actions/down.png");
        }
    }

    public void carnet(ActionEvent event) {
        id_alumno_1 = (((UIParameter) event.getComponent().findComponent("id_alumno")).getValue().toString());
        tipo_ = "1";
    }

    public void mora(ActionEvent event) {
        id_alumno_1 = (((UIParameter) event.getComponent().findComponent("id_alumno")).getValue().toString());
        tipo_ = "2";
    }

    public void pagarCarnet() {
        if (this.getNro_oculto().length() != 0) {
            float monto_ = 0;
            int concepto_ = 0;
            if (tipo_.equals("1")) {
                monto_ = 20;
                concepto_ = 20;
            } else if (tipo_.equals("2")) {
                monto_ = 30;
                concepto_ = 23;
            }
            HSPagoDAO daoPago = (HSPagoDAO) ServiceFinder.findBean("SpringHibernateDaoPago");
            if (daoPago.seleccionarPagosExternos(Integer.parseInt(id_alumno_1), concepto_).isEmpty()) {
                AdPago pag = new AdPago();

                pag.setPagMonto(monto_);
                pag.setPagFecha(new Date());
                pag.setAlutarId(0);
                pag.setPagActivo("1");
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                HttpSession session1 = (HttpSession) context.getSession(false);
                bUsuario usu = (bUsuario) session1.getAttribute("usuario");
                pag.setUsuId(usu.getId_usu());
                AdConceptoPago con = new AdConceptoPago();
                con.setId(concepto_);
                pag.setConpag(con);
                AdComprobantePago compag = new AdComprobantePago();
                compag.setCompagTipo("037001");
                compag.setCompagDes("");
                compag.setCompagAlu(0);
                compag.setCompagCliente(Integer.parseInt(id_alumno_1));
                compag.setCompagClienteTipo("014001");
                compag.setCompagUsado("1");
                compag.setCompagProcedencia("015002");
                compag.setCompagCreacion(new Date());
                compag.setCompagActivo("1");
                compag.setCompagMonto(monto_);
                compag.setCompagFecha(new Date());
                compag.setCompagNro(this.getNro_oculto());
                pag.setCompag(compag);
                Set<AdPago> l = new LinkedHashSet<AdPago>();
                l.add(pag);
                compag.setAdPagos(l);
                int id_ = daoPago.insertarPago(compag);
                this.setOncomplete("javascript:alert('El Pago se efectuo Corectamente'),Richfaces.hideModalPanel('modal'),abrir('boleta','" + id_ + "',0,0)");
            } else {
                this.setOncomplete("javascript:alert('Ya registra pago por el concepto seleccionado')");
            }
        }
    }

    public void Pagar(ActionEvent event) throws Exception {
        e_mora_seleccion = false;
        HSPagoDAO daoP = (HSPagoDAO) ServiceFinder.findBean("SpringHibernateDaoPago");
        String numero = daoP.MaximoNroComprobante("001");
        String num0;
        String nr;
        if (numero.equals("0")) {
            num0 = "001";
            nr = "0000001";
        } else {
            num0 = numero.substring(0, 3);
            String num = numero.substring(4, 11);
            int numInt = 0;
            numInt = Integer.parseInt(num);
            numInt = numInt + 1;
            nr = numInt + "";
            for (int h = 1; h <= 7 - (numInt + "").length(); h++) {
                nr = "0" + nr;
            }
        }
        setE_id_procedencia("015002");
        setE_num_comprobante0(num0);
        setE_num_comprobante(nr);
        setE_fecha_comprobante(new Date());

        setN_valor_nuevo(0);
        setE_cliente_fac(0);
        setE_id_tipo_nj("0");
        setE_razon_social("");
        setE_dni_ruc("");
        setE_id_tipo_cliente("0");
        setE_monto_pago(0);
        setE_mora_pago(0);
        c_id_pago = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_s")).getValue().toString());
        c_id_nombre_pago = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_nombre_s")).getValue().toString());
        c_id_con_pag_pago = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_con_pag_s")).getValue().toString());
        c_sem_pago = ((UIParameter) event.getComponent().findComponent("sem_s")).getValue().toString();
        c_id_sem_pago = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_sem_s")).getValue().toString());
        c_fac_pago = ((UIParameter) event.getComponent().findComponent("fac_s")).getValue().toString();
        c_id_fac_pago = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_fac_s")).getValue().toString());
        c_esp_pago = ((UIParameter) event.getComponent().findComponent("esp_s")).getValue().toString();
        c_id_esp_pago = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_esp_s")).getValue().toString());
        c_codigo_pago = ((UIParameter) event.getComponent().findComponent("codigo_s")).getValue().toString();
        c_nombre_pago = ((UIParameter) event.getComponent().findComponent("nombre_s")).getValue().toString();
        c_con_pag_pago = ((UIParameter) event.getComponent().findComponent("con_pag_s")).getValue().toString();
        c_con_pag_det_pago = ((UIParameter) event.getComponent().findComponent("con_pag_det_s")).getValue().toString();
        c_monto_pago = Float.parseFloat(((UIParameter) event.getComponent().findComponent("monto_s")).getValue().toString());
        c_saldo_pago = Float.parseFloat(((UIParameter) event.getComponent().findComponent("saldo_s")).getValue().toString());
        c_adeuda_pago = Float.parseFloat(((UIParameter) event.getComponent().findComponent("adeuda_s")).getValue().toString());
        c_dni_ruc_pago = ((UIParameter) event.getComponent().findComponent("dni_ruc_s")).getValue().toString();
        try {
            Date fecha1 = dateFormat.parse(((UIParameter) event.getComponent().findComponent("fecha_pag_s")).getValue().toString());
            Date fecha2 = dateFormat.parse(((UIParameter) event.getComponent().findComponent("fecha_pro_s")).getValue().toString());
            c_fecha_pag_pago = fecha1;
            c_fecha_pro_pago = fecha2;
        } catch (Exception e) {
            System.out.println("Formato incorrecto de Fecha");
        }
        this.setE_monto_pago(c_adeuda_pago);
        Calendar cal_ini = Calendar.getInstance();
        cal_ini.setTime(c_fecha_pro_pago);
        Calendar cal_fin = Calendar.getInstance();
        cal_fin.setTime(new Date());
        Calendar cal = Calendar.getInstance();
        if (cal_fin.getTimeInMillis() > cal_ini.getTimeInMillis()) {
            HSCatalogoDAO dao1 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            HSConceptoPagoDAO dao2 = (HSConceptoPagoDAO) ServiceFinder.findBean("SpringHibernateDaoConceptoPago");

            mora_diaria = ((AdConceptoPago) dao2.seleccionarUnaConceptoPago(Integer.parseInt(dao1.seleccionarValor("021004"))).get(0)).getConpagMonto();
            cal.setTimeInMillis(cal_fin.getTimeInMillis() - cal_ini.getTimeInMillis());
            this.setE_mora_pago((cal.get(Calendar.DAY_OF_YEAR) - 1) * mora_diaria);
        } else {
            this.setE_mora_pago(0);
        }
        HSCatalogoDAO dao_11 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        HSConceptoPagoDAO dao_22 = (HSConceptoPagoDAO) ServiceFinder.findBean("SpringHibernateDaoConceptoPago");
        float mat_expo = 0;
        mat_expo = ((AdConceptoPago) dao_22.seleccionarUnaConceptoPago(Integer.parseInt(dao_11.seleccionarValor("021005"))).get(0)).getConpagMonto();
        System.out.println("XPO: " + c_con_pag_det_pago);
        this.setE_mat_expo(mat_expo);

        /***********************************/
        this.comboTipoDocumento_comprobante = getComboTipoDocumento_comprobante();
        this.setE_tip_com("039001");
        this.setE_num_vou("");
        this.setE_fec_vou(new Date());
        this.setE_cod_age("");
        /***********************************/
        this.setE_mat_expo_seleccion(false);
        /*if (c_con_pag_det_pago.trim().substring(0,3).equals("4to")){
        this.setE_mat_expo_seleccion(true);
        }else{
        this.setE_mat_expo_seleccion(false);
        }*/
    }

    public SelectItem[] getComboCatalogo_tipo_cliente() throws Exception {
        return comboCatalogo_tipo_cliente;
    }

    public void setComboCatalogo_tipo_cliente(SelectItem[] comboCatalogo_tipo_cliente) {
        this.comboCatalogo_tipo_cliente = comboCatalogo_tipo_cliente;
    }

    public SelectItem[] getComboCatalogo_tipo_nj() throws Exception {
        return comboCatalogo_tipo_nj;
    }

    public void setComboCatalogo_tipo_nj(SelectItem[] comboCatalogo_tipo_nj) {
        this.comboCatalogo_tipo_nj = comboCatalogo_tipo_nj;
    }

    public SelectItem[] getComboCatalogo_procedencia() throws Exception {
        return comboCatalogo_procedencia;
    }

    public void setComboCatalogo_procedencia(SelectItem[] comboCatalogo_procedencia) {
        this.comboCatalogo_procedencia = comboCatalogo_procedencia;
    }

    public void Eliminar(String id) throws Exception {
    }

    public void Insertar(ActionEvent event) throws Exception {
        int extra = 0;
        int varG = 1;
        int verId;
        int verificar = 0;
        Date fecha = new Date();
        String numero = "";
        Float PagMonto = null;
        String verComPago = "0";
        AdComprobantePago compag = new AdComprobantePago();
        AdPago pag = new AdPago();
        AdSaldo sal = new AdSaldo();
        if (this.getE_id_tipo_cliente().equals("0") || (this.getE_razon_social().trim()).length() == 0 || ("" + this.getE_id_tipo_nj()).equals("0") || ("" + this.getE_id_procedencia()).equals("0") || this.getE_monto_pago() <= 0 || this.getE_fecha_comprobante() == null || this.getE_num_comprobante().trim().length() == 0) {
            this.setOncomplete("javascript:alert('Ingrese todos los datos Requeridos.')");
        } else {
            HSClienteDAO daoNC = (HSClienteDAO) ServiceFinder.findBean("SpringHibernateDaoCliente");
            HSPagoDAO daoPago = (HSPagoDAO) ServiceFinder.findBean("SpringHibernateDaoPago");
            HSAlumnoTarifaDAO daoT = (HSAlumnoTarifaDAO) ServiceFinder.findBean("SpringHibernateDaoAlumnoTarifa");
            if (this.getE_cliente_fac() == 0) {
                int cl = 0;
                verificar = daoNC.seleccionarUnCliente(this.getE_razon_social());
                if (verificar != 0) {
                    verId = verificar;
                } else {
                    AdClientes nueCli = new AdClientes();
                    nueCli = new AdClientes();
                    nueCli.setId(cl);
                    nueCli.setCliRuc(this.getE_dni_ruc());
                    nueCli.setCliRazonSocial(this.getE_razon_social());
                    nueCli.setCliDireccion("");
                    nueCli.setCliTelefono("");
                    nueCli.setCliMail("");
                    nueCli.setCliTipo(this.getE_id_tipo_nj());
                    nueCli.setCliActivo("1");
                    daoNC.insertarCliente(nueCli);
                    verId = daoNC.seleccionarUnCliente(this.getE_razon_social());
                    if (verId != 0) {
                        this.setOncomplete("javascript:alert('Cliente no fue agregado corectamente')");
                    }
                }
            } else {
                verId = this.getE_cliente_fac();
            }
            if (this.getE_id_procedencia().equals("015001")) {
                numero = this.getE_num_comprobante0() + "-" + this.getE_num_comprobante();
                verComPago = daoPago.seleccionarUnComprobantePago(numero);
                if (!verComPago.equalsIgnoreCase("0")) {
                    this.setOncomplete("javascript:alert('El Comprobante con Número " + verComPago + " ya fue usado')");
                } else {
                    fecha = this.getE_fecha_comprobante();
                    numero = this.getE_num_comprobante0() + "-" + this.getE_num_comprobante();
                    if (this.getE_monto_pago() <= this.getC_adeuda_pago()) {
                        PagMonto = this.getE_monto_pago();
                    }
                    if (this.getE_monto_pago() > this.getC_adeuda_pago()) {
                        PagMonto = this.getC_adeuda_pago();
                        sal = new AdSaldo();
                        float resta = this.getE_monto_pago() - this.getC_adeuda_pago();//getC_adeuda_u

                        sal.setSalMonto(resta);
                        sal.setSalFechaIngresoMonto(new Date());
                        sal.setSalCreacion(new Date());
                        AcAlumno alu = new AcAlumno();
                        alu.setId(this.getC_id_nombre_pago());
                        sal.setAlu(alu);
                        extra = 1;
                    }
                    if (this.getE_fec_vou() == null || this.getE_num_vou().trim().length() == 0 || this.getE_cod_age().trim().length() == 0) {
                        this.setOncomplete("javascript:alert('Ingrese los campos faltantes.')");
                        varG = 0;
                    }
                }
            }
            if (verId != 0 && verComPago.equals("0")) {
                if (this.getE_id_procedencia().equals("015002")) {
                    fecha = this.getE_fecha_comprobante();
                    numero = this.getE_num_comprobante0() + "-" + this.getE_num_comprobante();
                    if (this.getE_monto_pago() > this.getC_adeuda_pago()) {
                        this.setOncomplete("javascript:alert('Ingrese correctamete el monto a pagar1')");
                        varG = 0;
                    }
                    if (this.getE_monto_pago() <= this.getC_adeuda_pago()) {
                        PagMonto = this.getE_monto_pago();
                    }
                }
                if (this.getE_id_procedencia().equals("015004")) {
                    fecha = this.getE_fecha_comprobante();
                    numero = this.getE_num_comprobante0() + "-" + this.getE_num_comprobante();
                    if (this.getE_monto_pago() > this.getC_adeuda_pago()) {
                        this.setOncomplete("javascript:alert('Ingrese correctamete el monto a pagar2')");
                        varG = 0;
                    }
                    if (this.getE_monto_pago() <= this.getC_adeuda_pago()) {
                        PagMonto = this.getE_monto_pago();
                    }
                }
                if (this.getE_id_procedencia().equals("015005")) {
                    fecha = this.getE_fecha_comprobante();
                    numero = this.getE_num_comprobante0() + "-" + this.getE_num_comprobante();
                    if (this.getE_monto_pago() > this.getC_adeuda_pago()) {
                        this.setOncomplete("javascript:alert('Ingrese correctamete el monto a pagar21')");
                        varG = 0;
                    }
                    if (this.getE_monto_pago() <= this.getC_adeuda_pago()) {
                        PagMonto = this.getE_monto_pago();
                    }
                }
                if (this.getE_id_procedencia().equals("015003")) {
                    fecha = new Date();

                    numero = "SAL0000000000";
                    if (this.getE_monto_pago() > this.getC_saldo_pago()) {
                        this.setOncomplete("javascript:alert('Ud No cuenta con saldo suficiente para Concretar este Pago')");
                        varG = 0;
                    } else {
                        if (this.getE_monto_pago() > this.getC_adeuda_pago()) {
                            this.setOncomplete("javascript:alert('Ingrese correctamente el monto a pagar3')");
                            varG = 0;
                        }
                        if (this.getE_monto_pago() <= this.getC_adeuda_pago()) {
                            PagMonto = this.getE_monto_pago();
                            sal = new AdSaldo();
                            float resta = -1 * this.getE_monto_pago();
                            sal.setSalMonto(resta);
                            sal.setSalFechaIngresoMonto(new Date());
                            sal.setSalCreacion(new Date());
                            AcAlumno alu = new AcAlumno();
                            alu.setId(this.getC_id_nombre_pago());
                            sal.setAlu(alu);
                            extra = 1;
                        }
                    }
                }
                if (varG == 1) { // Se puede proceder a grabar

                    listaPagos.clear();
                    pag = new AdPago();
                    pag.setPagMonto(PagMonto);
                    pag.setPagFecha(new Date());
                    pag.setAlutarId(this.getC_id_pago());
                    pag.setPagActivo("1");
                    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                    HttpSession session1 = (HttpSession) context.getSession(false);
                    bUsuario usu = (bUsuario) session1.getAttribute("usuario");
                    pag.setUsuId(usu.getId_usu());//AQUI SE INGRESARA EL ID DEL USUARIO DE SESSION

                    AdConceptoPago con = new AdConceptoPago();
                    con.setId(this.getC_id_con_pag_pago());
                    pag.setConpag(con);
                    compag = new AdComprobantePago();
                    compag.setCompagTipo("037001");
                    compag.setCompagDes("");
                    compag.setCompagAlu(0);
                    compag.setCompagCliente(verId);
                    compag.setCompagClienteTipo(this.getE_id_tipo_cliente());
                    compag.setCompagUsado("1");
                    compag.setCompagProcedencia(this.getE_id_procedencia());
                    compag.setCompagCreacion(new Date());
                    compag.setCompagActivo("1");
                    compag.setCompagMonto(this.getE_monto_pago());
                    compag.setCompagFecha(fecha);
                    compag.setCompagNro(numero);
                    /****************************/
                    compag.setCompagTipDoc(this.getE_tip_com());
                    compag.setCompagVoucherNroOperacion(this.getE_num_vou());
                    compag.setCompagVoucherFecha(this.getE_fec_vou());
                    compag.setCompagVoucherCodAgencia(this.getE_cod_age());
                    /****************************/
                    pag.setCompag(compag);
                    Set<AdPago> l = new LinkedHashSet<AdPago>();
                    l.add(pag);
                    if (this.isE_mora_seleccion()) {
                        if (this.getE_mora_pago() != 0) {
                            compag.setCompagMonto(this.getE_monto_pago() + this.getE_mora_pago());
                            AdPago pag1 = new AdPago();
                            pag1.setPagFecha(new Date());
                            pag1.setAlutarId(this.getC_id_pago());
                            pag1.setPagActivo("1");
                            pag1.setUsuId(usu.getId_usu());//AQUI SE INGRESARA EL ID DEL USUARIO DE SESSION

                            HSCatalogoDAO dao1 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
                            AdConceptoPago con1 = new AdConceptoPago();
                            con1.setId(Integer.parseInt(dao1.seleccionarValor("021004")));
                            pag1.setConpag(con1);
                            pag1.setCompag(compag);
                            pag1.setPagMonto(this.getE_mora_pago());
                            l.add(pag1);
                        }
                    }
                    if (this.isE_mat_expo_seleccion()) {
                        if (this.getE_mat_expo() != 0) {
                            /*
                             * para la captura de monto de un concepto de pagos*/
                            compag.setCompagMonto(compag.getCompagMonto() + this.getE_mat_expo());
                            AdPago pag1 = new AdPago();
                            pag1.setPagFecha(new Date());
                            pag1.setAlutarId(this.getC_id_pago());
                            pag1.setPagActivo("1");
                            pag1.setUsuId(usu.getId_usu());
                            HSCatalogoDAO dao1 = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
                            AdConceptoPago con1 = new AdConceptoPago();
                            con1.setId(Integer.parseInt(dao1.seleccionarValor("021005")));
                            pag1.setConpag(con1);
                            pag1.setCompag(compag);
                            pag1.setPagMonto(this.getE_mat_expo());
                            l.add(pag1);
                            /* Recuperar Monto y de Pagos de Carne*/
                        }
                    }
                    compag.setAdPagos(l);
                    int id_ = daoPago.insertarPago(compag);
                    if (extra == 1) {
                        daoPago.insertarSaldo(sal);
                    }
                    List listaPago = daoPago.seleccionarMostrarPagos(this.getC_id_pago());
                    float pagx = 0;
                    float tarifa_monto;
                    for (int k = 0; k < listaPago.size(); k++) {
                        if (((AdPago) listaPago.get(k)).getCompag().getCompagTipo().equals("037001") && ((AdPago) listaPago.get(k)).getCompag().getCompagActivo().equals("1")) {
                            pagx = pagx + ((AdPago) listaPago.get(k)).getPagMonto();
                        }
                    }
                    tarifa_monto = this.getC_monto_pago() - pagx;///monto que adeuda actulamente

                    if (tarifa_monto == 0) {
                        daoT.cambiarEstado(this.getC_id_pago(), "2");
                    }
                    if (tarifa_monto < this.getC_monto_pago() && tarifa_monto != 0) {
                        daoT.cambiarEstado(this.getC_id_pago(), "1");
                    }
                    if (tarifa_monto == this.getC_monto_pago()) {
                        daoT.cambiarEstado(this.getC_id_pago(), "0");
                    }
                    Date date = new Date();
                    String ano = String.format("%tY", date);
                    String mes = String.format("%tm", date);
                    String dia = String.format("%td", date);
                    String alerta = mandarComprobante(id_);
                    this.setOncomplete("javascript:alert('El Pago se efectuo Corectamente');javascript:Richfaces.hideModalPanel('mp');" + alerta);
                }
            }
        }

    }

    public void SeleccionarCliente(ActionEvent event) throws Exception {
        this.setSelCli("");
        setE_cliente_fac(0);
        setE_id_tipo_nj("0");
        setE_razon_social("");
        setE_dni_ruc("");
        listaCliente = new ArrayList();
        if (this.getE_id_tipo_cliente().equals("014001")) {
            HSAlumnoDAO dao = (HSAlumnoDAO) ServiceFinder.findBean("SpringHibernateDaoAlumno");
            List lista = dao.seleccionarAlumnoCliente(this.getXuno());
            bPago AluCli;
            for (int i = 0; i < lista.size(); i++) {
                AluCli = new bPago(0);
                AluCli.setCli_id(((AcAlumno) lista.get(i)).getId());
                AluCli.setCli_nombre(((AcAlumno) lista.get(i)).getAluAppaterno() + " " + ((AcAlumno) lista.get(i)).getAluApmaterno() + " " + ((AcAlumno) lista.get(i)).getAluNombres());
                AluCli.setCli_razon("017001");
                AluCli.setCli_dni_ruc(((AcAlumno) lista.get(i)).getAluDni());
                AluCli.setCli_codigo(((AcAlumno) lista.get(i)).getAluCodigo());
                listaCliente.add(AluCli);
            }
            this.setListaCliente(listaCliente);
        }
        if (this.getE_id_tipo_cliente().equals("014002")) {
            HSClienteDAO dao = (HSClienteDAO) ServiceFinder.findBean("SpringHibernateDaoCliente");
            List lista = dao.seleccionarCliente(this.getXuno());
            bPago AluCli;
            for (int i = 0; i < lista.size(); i++) {
                AluCli = new bPago(0);
                AluCli.setCli_id(((AdClientes) lista.get(i)).getId());
                AluCli.setCli_nombre(((AdClientes) lista.get(i)).getCliRazonSocial());
                AluCli.setCli_razon(((AdClientes) lista.get(i)).getCliTipo());
                AluCli.setCli_dni_ruc(((AdClientes) lista.get(i)).getCliRuc());
                AluCli.setCli_codigo("0000000");
                listaCliente.add(AluCli);
            }
            this.setListaCliente(listaCliente);
        }
        if (this.getE_id_tipo_cliente().equals("0")) {
            this.setSelCli("");
        } else {
            this.setSelCli("javascript:Richfaces.showModalPanel('mp2',{width:150, top:200})");
        }
        if (this.varx == 1) {
            setRefrescar("nNomAlu,nCodAlu,nIdAlu,xunox");
        } else {
            if (this.getN_valor_nuevo() == 0) {
                this.setRefrescar("iTiponj,iRazonSocial,iClienteFac,iDniRuc,xunox");
            }
            if (this.getN_valor_nuevo() == 1) {
                this.setRefrescar("nTiponj,nRazonSocial,nClienteFac,nDniRuc,nSaldo,xunox");
            }
        }
    }

    public void Asignar(ActionEvent event) throws Exception {
        int x = 100;
        cli_id = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_s_cli")).getValue().toString());
        cli_nombre = ((UIParameter) event.getComponent().findComponent("nombre_s_cli")).getValue().toString();
        cli_razon = ((UIParameter) event.getComponent().findComponent("razon_s_cli")).getValue().toString();
        cli_dni_ruc = ((UIParameter) event.getComponent().findComponent("dni_ruc_s_cli")).getValue().toString();
        cli_saldo = Float.parseFloat(((UIParameter) event.getComponent().findComponent("saldo_cli")).getValue().toString());
        cli_codigo = ((UIParameter) event.getComponent().findComponent("codigo_cli")).getValue().toString();
        if (varx == 1) {
            if (this.getN_valor_nuevo() == 0) {
                x = 10;
            }
            if (this.getN_valor_nuevo() == 1) {
                x = 20;
            }
        } else {
            if (this.getN_valor_nuevo() == 0) {
                x = 0;
            }
            if (this.getN_valor_nuevo() == 1) {
                x = 1;
            }
        }

        if (x == 0) {
            varx = 0;
            setE_cliente_fac(cli_id);
            setE_id_tipo_nj(cli_razon);
            setE_razon_social(cli_nombre);
            setE_dni_ruc(cli_dni_ruc);
            setXuno("");
            varx = 0;
            setRefrescar("iTiponj,iRazonSocial,iClienteFac,iDniRuc,xunox");
        }
        if (x == 1) {
            System.out.println("entro 0>>>><");
            varx = 0;
            this.setN_cliente_fac(cli_id);
            this.setN_id_tipo_nj(cli_razon);
            this.setN_razon_social(cli_nombre);
            this.setN_dni_ruc(cli_dni_ruc);
            this.setN_saldo(cli_saldo);
            this.setN_cod_alu(cli_codigo);
            this.setN_id_alu(cli_id);
            this.setXuno("");
            this.varx = 0;
            System.out.println("ssss " + this.getN_razon_social());
            this.setRefrescar("nTiponj,nRazonSocial,nClienteFac,nDniRuc,nSaldo,xunox");
        }
        if (varx == 1) {
            varx = 0;
            setN_cod_alu(cli_codigo);
            setN_nom_alu(cli_nombre);
            setN_id_alu(cli_id);
            setXuno("");
            varx = 0;
            setRefrescar("nNomAlu,nCodAlu,nIdAlu,xunox");
        }
    }

    public void Asignar2(ActionEvent event) throws Exception {
        setE_cliente_fac(this.getC_id_nombre_pago());
        setE_id_tipo_nj("017001");
        setE_razon_social(this.getC_nombre_pago());
        setE_dni_ruc(this.getC_dni_ruc_pago());
        setE_id_tipo_cliente("014001");
    }

    public void Asignar3(ActionEvent event) throws Exception {
        setE_cliente_fac(0);
        setE_id_tipo_nj("0");
        setE_razon_social("");
        setE_dni_ruc("");
        setE_id_tipo_cliente("014002");
    }

    public SelectItem[] getComboConceptoPago_nuevo() throws Exception {
        return comboConceptoPago_nuevo;
    }

    public void setComboConceptoPago_nuevo(SelectItem[] comboConceptoPago_nuevo) {
        this.comboConceptoPago_nuevo = comboConceptoPago_nuevo;
    }

    public SelectItem[] getComboCatalogo_tipo_cliente_nuevo() throws Exception {
        return comboCatalogo_tipo_cliente_nuevo;
    }

    public void setComboCatalogo_tipo_cliente_nuevo(SelectItem[] comboCatalogo_tipo_cliente_nuevo) {
        this.comboCatalogo_tipo_cliente_nuevo = comboCatalogo_tipo_cliente_nuevo;
    }

    public SelectItem[] getComboCatalogo_procedencia_nuevo() throws Exception {
        return comboCatalogo_procedencia_nuevo;
    }

    public void setComboCatalogo_procedencia_nuevo(SelectItem[] comboCatalogo_procedencia_nuevo) {
        this.comboCatalogo_procedencia_nuevo = comboCatalogo_procedencia_nuevo;
    }

    public SelectItem[] getComboCatalogo_tipo_nj_nuevo() throws Exception {
        return comboCatalogo_tipo_nj_nuevo;
    }

    public void setComboCatalogo_tipo_nj_nuevo(SelectItem[] comboCatalogo_tipo_nj_nuevo) {
        this.comboCatalogo_tipo_nj_nuevo = comboCatalogo_tipo_nj_nuevo;
    }

    public void SeleccionarCliente_nuevo(ActionEvent event) throws Exception {
        this.setSelCli("");
        setN_cliente_fac(0);
        setN_id_tipo_nj("0");
        setN_razon_social("");
        setN_dni_ruc("");
        listaCliente = new ArrayList();
        /*
        if (this.getN_id_tipo_cliente().equals("014001")) {
        HSAlumnoDAO dao = (HSAlumnoDAO) ServiceFinder.findBean("SpringHibernateDaoAlumno");
        HSAlumnoTarifaDAO daoSaldo = (HSAlumnoTarifaDAO) ServiceFinder.findBean("SpringHibernateDaoAlumnoTarifa");
        List lista = dao.seleccionarAlumnoCliente(this.getXuno());
        System.out.println(this.getXuno());
        bPago AluCli;
        for (int i = 0; i < lista.size(); i++) {
        AluCli = new bPago(0);
        AluCli.setCli_id(((AcAlumno) lista.get(i)).getId());
        AluCli.setCli_nombre(((AcAlumno) lista.get(i)).getAluAppaterno() + " " + ((AcAlumno) lista.get(i)).getAluApmaterno() + " " + ((AcAlumno) lista.get(i)).getAluNombres());
        AluCli.setCli_razon("017001");
        AluCli.setCli_dni_ruc(((AcAlumno) lista.get(i)).getAluDni());
        AluCli.setCli_codigo(((AcAlumno) lista.get(i)).getAluCodigo());
        List listaSaldo = daoSaldo.seleccionarUnSaldo(((AcAlumno) lista.get(i)).getId());
        float sum = 0;
        if (listaSaldo.size() != 0) {
        for (int j = 0; j < listaSaldo.size(); j++) {
        sum = sum + ((AdSaldo) listaSaldo.get(j)).getSalMonto();
        }
        } else {
        sum = 0;
        }
        AluCli.setCli_saldo(sum);
        listaCliente.add(AluCli);
        }
        this.setListaCliente(listaCliente);
        }
        if (this.getN_id_tipo_cliente().equals("014002")) {
        HSClienteDAO dao = (HSClienteDAO) ServiceFinder.findBean("SpringHibernateDaoCliente");
        List lista = dao.seleccionarCliente(this.getXuno());
        bPago AluCli;
        for (int i = 0; i < lista.size(); i++) {
        AluCli = new bPago(0);
        AluCli.setCli_id(((AdClientes) lista.get(i)).getId());
        AluCli.setCli_nombre(((AdClientes) lista.get(i)).getCliRazonSocial());
        AluCli.setCli_razon(((AdClientes) lista.get(i)).getCliTipo());
        AluCli.setCli_dni_ruc(((AdClientes) lista.get(i)).getCliRuc());
        AluCli.setCli_codigo("0000000");
        AluCli.setCli_saldo(0);
        listaCliente.add(AluCli);
        }
        this.setListaCliente(listaCliente);
        }*/
        if (this.getN_id_tipo_cliente().equals("0")) {
            this.setSelCli("");
        } else {
            this.setSelCli("javascript:Richfaces.showModalPanel('mp2',{width:150, top:200})");
        }
        if (this.varx == 1) {
            setRefrescar("nNomAlu,nCodAlu,nIdAlu,xunox");
        } else {
            if (this.getN_valor_nuevo() == 0) {
                this.setRefrescar("iTiponj,iRazonSocial,iClienteFac,iDniRuc,xunox");
            }
            if (this.getN_valor_nuevo() == 1) {
                this.setRefrescar("nTiponj,nRazonSocial,nClienteFac,nDniRuc,nSaldo,xunox");
            }
        }
    }

    public void Asignar3_nuevo(ActionEvent event) throws Exception {
        setN_cod_alu("");
        setN_cliente_fac(0);
        setN_id_tipo_nj("0");
        setN_razon_social("");
        setN_dni_ruc("");
        setN_id_tipo_cliente("014002");
        setN_saldo(0);
    }

    public void SeleccionarConceptoPago_nuevo(ActionEvent event) throws Exception {
        float nn = 0;
        setN_monto(0);
        String mm = "";
        if (this.getN_id_conpag() != 0) {
            HSConceptoPagoDAO dao = (HSConceptoPagoDAO) ServiceFinder.findBean("SpringHibernateDaoConceptoPago");
            List lista = dao.seleccionarUnaConceptoPago(this.getN_id_conpag());
            for (int i = 0; i < lista.size(); i++) {
                nn = ((AdConceptoPago) lista.get(i)).getConpagMonto();
                mm = ((AdConceptoPago) lista.get(i)).getConpagDescripcion();
            }
            this.setN_con_pag_nuevo(mm);
            this.setN_monto(nn);
            this.setN_monto_pago(nn);
        } else {
            this.setN_monto(nn);
            this.setN_monto_pago(nn);
        }
    }

    public void Insertar_nuevo(ActionEvent event) throws Exception {
        int extra = 0;
        int varG = 1;
        int verId;
        int verificar = 0;
        Date fecha = new Date();
        String numero = "";
        Float PagMonto = null;
        String verComPago = "0";
        AdComprobantePago compag = new AdComprobantePago();
        AdPago pag = new AdPago();
        AdSaldo sal = new AdSaldo();
        if (this.getN_id_tipo_cliente().equals("0") || (this.getN_razon_social().trim()).length() == 0 || ("" + this.getN_id_tipo_nj()).equals("0") || ("" + this.getN_id_procedencia()).equals("0") || this.getN_monto_pago() <= 0 || this.getN_fecha_comprobante() == null || this.getN_num_comprobante().trim().length() == 0) {
            this.setNoncomplete("javascript:alert('Ingrese todos los datos Requeridos.')");
        } else {
            HSClienteDAO daoNC = (HSClienteDAO) ServiceFinder.findBean("SpringHibernateDaoCliente");
            HSPagoDAO daoPago = (HSPagoDAO) ServiceFinder.findBean("SpringHibernateDaoPago");
            if (this.getN_cliente_fac() == 0) {
                int cl = 0;
                verificar = daoNC.seleccionarUnCliente(this.getN_razon_social());
                if (verificar != 0) {
                    verId = verificar;
                } else {
                    AdClientes nueCli = new AdClientes();
                    nueCli = new AdClientes();
                    nueCli.setId(cl);
                    nueCli.setCliRuc(this.getN_dni_ruc());
                    nueCli.setCliRazonSocial(this.getN_razon_social());
                    nueCli.setCliDireccion("");
                    nueCli.setCliTelefono("");
                    nueCli.setCliMail("");
                    nueCli.setCliTipo(this.getN_id_tipo_nj());
                    nueCli.setCliActivo("1");
                    daoNC.insertarCliente(nueCli);
                    verId = nueCli.getId();
                    /*
                    verId = daoNC.seleccionarUnCliente(this.getN_razon_social());*/
                    if (verId == 0) {
                        this.setNoncomplete("javascript:alert('Cliente no fue agregado corectamente')");
                    }
                }
            } else {
                verId = this.getN_cliente_fac();
            }
            if (this.getN_id_procedencia().equals("015001")) {
                numero = this.getN_num_comprobante0() + "-" + this.getN_num_comprobante();
                verComPago = daoPago.seleccionarUnComprobantePago(numero);
                if (!verComPago.equalsIgnoreCase("0")) {
                    this.setNoncomplete("javascript:alert('El Comprobante con Numero " + verComPago + " ya fue usado')");
                } else {
                    fecha = this.getN_fecha_comprobante();
                    numero = this.getN_num_comprobante0() + "-" + this.getN_num_comprobante();
                    /*if (this.getN_monto_pago() > this.getN_monto()) {
                    this.setNoncomplete("javascript:alert('Ingrese correctamete el monto a pagar')");
                    varG = 0;
                    }*/
                    //if (this.getN_monto_pago() < this.getN_monto()) {
                    if (this.getN_monto_pago() < 0) {
                        varG = 0;
                        this.setNoncomplete("javascript:alert('Ingrese correctamete el monto a pagar1.1')");
                    }
                    //if (this.getN_monto_pago() == this.getN_monto()) {
                    if (this.getN_monto_pago() >= 0) {
                        PagMonto = this.getN_monto_pago();
                    }
                    if (this.getN_fec_vou() == null || this.getN_num_vou().trim().length() == 0 || this.getN_cod_age().trim().length() == 0) {
                        this.setNoncomplete("javascript:alert('Ingrese los campos faltantes')");
                        varG = 0;
                    }
                }
            }
            if (verId != 0 && verComPago.equals("0")) {
                if (this.getN_id_procedencia().equals("015002")) {
                    fecha = this.getN_fecha_comprobante();
                    numero = this.getN_num_comprobante0() + "-" + this.getN_num_comprobante();
                    /*if (this.getN_monto_pago() > this.getN_monto()) {
                    this.setNoncomplete("javascript:alert('Ingrese correctamete el monto a pagar1.2')");
                    varG = 0;
                    }*/
                    //if (this.getN_monto_pago() < this.getN_monto()) {
                    if (this.getN_monto_pago() < 0) {
                        //varG=0;
                        //this.setNoncomplete("javascript:alert('Ingrese correctamete el monto a pagar1.3')");
                    }
                    //if (this.getN_monto_pago() <= this.getN_monto()) {
                    if (this.getN_monto_pago() > 0) {
                        PagMonto = this.getN_monto_pago();
                    }
                }
                if (this.getN_id_procedencia().equals("015004")) {
                    fecha = this.getN_fecha_comprobante();
                    numero = this.getN_num_comprobante0() + "-" + this.getN_num_comprobante();
                    /*if (this.getN_monto_pago() > this.getN_monto()) {
                    this.setNoncomplete("javascript:alert('Ingrese correctamete el monto a pagar1.4')");
                    varG = 0;
                    }*/
                    //if (this.getN_monto_pago() < this.getN_monto()) {
                    if (this.getN_monto_pago() < 0) {
                        varG = 0;
                        this.setNoncomplete("javascript:alert('Ingrese correctamete el monto a pagar1.5')");
                    }
                    //if (this.getN_monto_pago() == this.getN_monto()) {
                    if (this.getN_monto_pago() >= 0) {
                        PagMonto = this.getN_monto_pago();
                    }
                }
                if (this.getN_id_procedencia().equals("015003")) {
                    fecha = new Date();
                    numero = "SAL0000000000";
                    if (this.getN_monto_pago() > this.getN_saldo()) {
                        this.setNoncomplete("javascript:alert('Ud No cuenta con saldo suficiente para Concretar este Pago')");
                        varG = 0;
                    } else {
                        /*if (this.getN_monto_pago() > this.getN_monto()) {
                        this.setNoncomplete("javascript:alert('Ingrese correctamete el monto a pagar1.6')");
                        varG = 0;
                        }*/
                        //if (this.getN_monto_pago() < this.getN_monto()) {
                        if (this.getN_monto_pago() < 0) {
                            this.setNoncomplete("javascript:alert('Ingrese correctamete el monto a pagar1.7')");
                            varG = 0;
                        }
                        //if (this.getN_monto_pago() == this.getN_monto()) {
                        if (this.getN_monto_pago() >= 0) {
                            PagMonto = this.getN_monto_pago();
                            sal = new AdSaldo();
                            float resta = -1 * this.getN_monto_pago();
                            sal.setSalMonto(resta);
                            sal.setSalFechaIngresoMonto(new Date());
                            sal.setSalCreacion(new Date());
                            AcAlumno alu = new AcAlumno();
                            alu.setId(verId);
                            sal.setAlu(alu);
                            extra = 1;
                        }
                    }
                }

                //-----------
                if (varG == 1) {
                    listaPagos.clear();
                    pag = new AdPago();
                    pag.setPagMonto(PagMonto);
                    pag.setPagFecha(new Date());
                    pag.setAlutarId(0);
                    pag.setPagActivo("1");
                    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                    HttpSession session1 = (HttpSession) context.getSession(false);
                    bUsuario usu = (bUsuario) session1.getAttribute("usuario");
                    pag.setUsuId(usu.getId_usu());//AQUI SE INGRESARA EL ID DEL USUARIO DE SESSION

                    AdConceptoPago con = new AdConceptoPago();
                    con.setId(this.getN_id_conpag());
                    pag.setConpag(con);
                    compag = new AdComprobantePago();
                    compag.setCompagTipo("037001");
                    compag.setCompagDes(this.getN_des());
                    compag.setCompagAlu(this.getN_id_alu());
                    compag.setCompagCliente(verId);
                    compag.setCompagClienteTipo(this.getN_id_tipo_cliente());
                    compag.setCompagUsado("1");
                    compag.setCompagProcedencia(this.getN_id_procedencia());
                    compag.setCompagCreacion(new Date());
                    compag.setCompagActivo("1");
                    compag.setCompagMonto(this.getN_monto_pago());
                    compag.setCompagFecha(fecha);
                    compag.setCompagNro(numero);
                    /**********************************/
                    compag.setCompagTipDoc(this.getN_tip_com());
                    compag.setCompagVoucherFecha(this.getN_fec_vou());
                    compag.setCompagVoucherNroOperacion(this.getN_num_vou());
                    compag.setCompagVoucherCodAgencia(this.getN_cod_age());
                    /**********************************/
                    pag.setCompag(compag);
                    listaPagos.add(pag);
                    compag.setAdPagos(listaPagos);
                    int id_ = daoPago.insertarPago(compag);
                    if (extra == 1) {
                        daoPago.insertarSaldo(sal);
                    }
                    Date date = new Date();
                    String ano = String.format("%tY", date);
                    String mes = String.format("%tm", date);
                    String dia = String.format("%td", date);
                    String fechaIm = ano + "-" + mes + "-" + dia;
                    String alerta = mandarComprobante(id_);
                    this.setNoncomplete("javascript:alert('El Pago se efectuo Corectamente');javascript:Richfaces.hideModalPanel('mp3');" + alerta);
                    //this.setNoncomplete("javascript:alert('El Pago se efectuo Corectamente'),abrir('boleta','" + id_ + "',0,0),Richfaces.hideModalPanel('mp3')");

                }
            }
        }

    }

    public void nAsignar2(ActionEvent event) throws Exception {
        setN_cod_alu(this.getN_cod_alu());
        setN_nom_alu(this.getN_razon_social());
        setN_id_alu(this.getN_id_alu());
    }

    public void nSeleccionarCliente_nuevo(ActionEvent event) throws Exception {
        varx = 1;
        this.setSelCli("");
        setN_cod_alu("");
        setN_nom_alu("");
        setN_id_alu(0);
        listaCliente = new ArrayList();
        HSAlumnoDAO dao = (HSAlumnoDAO) ServiceFinder.findBean("SpringHibernateDaoAlumno");
        HSAlumnoTarifaDAO daoSaldo = (HSAlumnoTarifaDAO) ServiceFinder.findBean("SpringHibernateDaoAlumnoTarifa");
        List lista = dao.seleccionarAlumnoCliente(this.getXuno());
        bPago AluCli;
        for (int i = 0; i < lista.size(); i++) {
            AluCli = new bPago(0);
            AluCli.setCli_id(((AcAlumno) lista.get(i)).getId());
            AluCli.setCli_nombre(((AcAlumno) lista.get(i)).getAluAppaterno() + " " + ((AcAlumno) lista.get(i)).getAluApmaterno() + " " + ((AcAlumno) lista.get(i)).getAluNombres());
            AluCli.setCli_razon("017001");
            AluCli.setCli_dni_ruc(((AcAlumno) lista.get(i)).getAluDni());
            AluCli.setCli_codigo(((AcAlumno) lista.get(i)).getAluCodigo());
            List listaSaldo = daoSaldo.seleccionarUnSaldo(((AcAlumno) lista.get(i)).getId());
            float sum = 0;
            for (int j = 0; j < listaSaldo.size(); j++) {
                sum = sum + ((AdSaldo) listaSaldo.get(j)).getSalMonto();
            }
            AluCli.setCli_saldo(sum);
            listaCliente.add(AluCli);
        }
        this.setListaCliente(listaCliente);
        this.setSelCli("javascript:Richfaces.showModalPanel('mp2',{width:150, top:200})");
    }

    public String getB_apMaterno() {
        return b_apMaterno;
    }

    public void setB_apMaterno(String materno) {
        b_apMaterno = materno;
    }

    public String getB_apPaterno() {
        return b_apPaterno;
    }

    public void setB_apPaterno(String paterno) {
        b_apPaterno = paterno;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void boton() {
        this.valor = "hello";
    }

    public void Personalizar(ActionEvent event) throws Exception {
        setN_valor_nuevo(0);
        setE_cliente_fac(0);
        setE_id_tipo_nj("0");
        setE_razon_social("");
        setE_dni_ruc("");
        setE_id_tipo_cliente("0");
        setE_id_procedencia("0");
        setE_num_comprobante("");
        setE_fecha_comprobante(null);
        setE_monto_pago(0);
        setE_mora_pago(0);
        UIParameter saldo_r = (UIParameter) event.getComponent().findComponent("saldo_x");
        UIParameter adeuda_r = (UIParameter) event.getComponent().findComponent("adeuda_x");
        UIParameter dni_ruc_r = (UIParameter) event.getComponent().findComponent("dni_ruc_x");
        c_id_pago = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_x")).getValue().toString());
        c_id_nombre_pago = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_nombre_x")).getValue().toString());
        c_id_con_pag_pago = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_con_pag_x")).getValue().toString());
        c_id_con_pag_det = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_con_pag_det_x")).getValue().toString());
        c_sem_pago = ((UIParameter) event.getComponent().findComponent("sem_x")).getValue().toString();
        c_id_sem_pago = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_sem_x")).getValue().toString());
        c_fac_pago = ((UIParameter) event.getComponent().findComponent("fac_x")).getValue().toString();
        c_id_fac_pago = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_fac_x")).getValue().toString());
        c_esp_pago = ((UIParameter) event.getComponent().findComponent("esp_x")).getValue().toString();
        c_id_esp_pago = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_esp_x")).getValue().toString());
        c_codigo_pago = ((UIParameter) event.getComponent().findComponent("codigo_x")).getValue().toString();
        c_nombre_pago = ((UIParameter) event.getComponent().findComponent("nombre_x")).getValue().toString();
        c_con_pag_pago = ((UIParameter) event.getComponent().findComponent("con_pag_x")).getValue().toString();
        c_con_pag_det_pago = ((UIParameter) event.getComponent().findComponent("con_pag_det_x")).getValue().toString();
        c_monto_pago = Float.parseFloat(((UIParameter) event.getComponent().findComponent("monto_x")).getValue().toString());
        c_saldo_pago = Float.parseFloat(saldo_r.getValue().toString());
        c_adeuda_pago = Float.parseFloat(adeuda_r.getValue().toString());
        c_dni_ruc_pago = dni_ruc_r.getValue().toString();
        per_monto = Float.parseFloat(((UIParameter) event.getComponent().findComponent("monto_x")).getValue().toString());

        try {
            c_fecha_pag_pago = dateFormat.parse(((UIParameter) event.getComponent().findComponent("fecha_pag_x")).getValue().toString());
            c_fecha_pro_pago = dateFormat.parse(((UIParameter) event.getComponent().findComponent("fecha_pro_x")).getValue().toString());
            per_fecha_pag = dateFormat.parse(((UIParameter) event.getComponent().findComponent("fecha_pag_x")).getValue().toString());
            per_fecha_pro = dateFormat.parse(((UIParameter) event.getComponent().findComponent("fecha_pro_x")).getValue().toString());
        } catch (Exception e) {
            System.out.println("Formato incorrecto de Fecha");
        }
        this.setE_monto_pago(c_adeuda_pago);
    }

    public void Actualizar2(ActionEvent event) {
        int id_aluTar;
        id_aluTar = c_id_pago;
        int est = this.getC_id_con_pag_det();
        c_id_pago = 0;
        AdAlumnoTarifa Alupag = new AdAlumnoTarifa();
        AcAlumno Alu = new AcAlumno();
        AdConceptoPago ConP = new AdConceptoPago();
        if (this.getPer_fecha_pag() == null || this.getPer_fecha_pro() == null || this.getPer_monto() < 0) {
            this.setNoncomplete("javascript:alert('Ingrese todos los datos Requeridos.')");
        } else {
            HSAlumnoTarifaDAO daoAT = (HSAlumnoTarifaDAO) ServiceFinder.findBean("SpringHibernateDaoAlumnoTarifa");
            Alupag = new AdAlumnoTarifa();
            Alu = new AcAlumno();
            Alu.setId(this.getC_id_nombre_pago());

            ConP = new AdConceptoPago();
            ConP.setId(this.getC_id_con_pag_pago());

            Alupag.setId(id_aluTar);
            AdEstructuraPagosDetalle estpagDet = new AdEstructuraPagosDetalle();
            estpagDet.setId(est);
            Alupag.setEstpagdet(estpagDet);
            Alupag.setAlu(Alu);
            Alupag.setConpag(ConP);
            Alupag.setAlutarMonto(this.getPer_monto());
             Alupag.setAlutarMontoCopy(this.getPer_monto());
            Alupag.setAlutarFechaPago(this.getPer_fecha_pag());
            Alupag.setAlutarFechaProrroga(this.getPer_fecha_pro());
            Alupag.setAlutarEstado("0");
            Alupag.setAlutarActivo("1");
            daoAT.actualizarAlumnoTarifa(Alupag);
            this.setOncomplete("javascript:alert('La Actualizacion se efectuo Corectamente'),Richfaces.hideModalPanel('mpx2')");
        }
    }

    public void EditarComprobante(ActionEvent event) throws Exception {
        limpiarCampos();
        if (((UIParameter) event.getComponent().findComponent("o1")) == null) {
            System.out.println("como interno");
            this.setZc_id_u(Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_z")).getValue().toString()));
            this.setZc_nombre_u(((UIParameter) event.getComponent().findComponent("nombre_z")).getValue().toString());
            this.setZc_num_com_u(((UIParameter) event.getComponent().findComponent("num_com_z")).getValue().toString());
            this.setZc_monto_u(Float.parseFloat(((UIParameter) event.getComponent().findComponent("monto_z")).getValue().toString()));
            this.setZc_con_pag_u(((UIParameter) event.getComponent().findComponent("con_pag_z")).getValue().toString());
            this.setZc_con_pag_det_u(((UIParameter) event.getComponent().findComponent("con_pag_det_z")).getValue().toString());
            this.setZc_id_tipo_com_u(((UIParameter) event.getComponent().findComponent("id_tipo_com_z")).getValue().toString());
            /*****************************************************************/
            this.setZc_tip_com_u(((UIParameter) event.getComponent().findComponent("tip_com_z")).getValue().toString());
            this.setZc_num_vou_u(((UIParameter) event.getComponent().findComponent("num_vou_z")).getValue().toString());
            this.setZc_cod_age_u(((UIParameter) event.getComponent().findComponent("cod_age_z")).getValue().toString());
            /*****************************************************************/
            try {
                Date fecha1 = dateFormat.parse(((UIParameter) event.getComponent().findComponent("fecha_pag_z")).getValue().toString());
                Date fecha2 = dateFormat.parse(((UIParameter) event.getComponent().findComponent("fec_vou_z")).getValue().toString());
                zc_fecha_pag_u = fecha1;
                zc_fec_vou_u = fecha2;
                this.setZc_fecha_pag_u(zc_fecha_pag_u);
                this.setZc_fec_vou_u(zc_fec_vou_u);
            } catch (Exception e) {
                System.out.println("Formato incorrecto de Fecha");
            }
        } else {
            System.out.println("como externo");
            this.setZc_id_u(Integer.parseInt(((UIParameter) event.getComponent().findComponent("o1")).getValue().toString()));
            this.setZc_num_com_u(((UIParameter) event.getComponent().findComponent("o2")).getValue().toString());
            this.setZc_id_tipo_com_u("015002");
            try {
                Date f = dateFormat.parse(((UIParameter) event.getComponent().findComponent("o3")).getValue().toString());
                this.setZc_fecha_pag_u(f);
            } catch (Exception e) {
                System.out.println("Formato incorrecto de Fecha");
            }
            this.setZc_nombre_u(((UIParameter) event.getComponent().findComponent("o4")).getValue().toString());
            this.setZc_monto_u(Float.parseFloat(((UIParameter) event.getComponent().findComponent("o5")).getValue().toString()));
            this.setZc_con_pag_u(((UIParameter) event.getComponent().findComponent("o6")).getValue().toString());
            this.setZc_con_pag_det_u(((UIParameter) event.getComponent().findComponent("o7")).getValue().toString());
        }
    }

    public void Actualizar3(ActionEvent event) {
        int permiso = 0;
        if ((this.getZc_num_com_u().trim()).length() == 0 || this.getZc_fecha_pag_u() == null || this.getZc_tip_com_u().trim().length() == 0) {
            permiso = 0;
            this.setOncomplete("javascript:alert('Ingrese todos los datos Requeridos.')");
        } else {
            if (this.getZc_id_tipo_com_u().equals("015003")) {
                permiso = 0;
                this.setOncomplete("javascript:alert('No esta permitido el Cambio de Procedencia a Saldos')");
            } else if (this.getZc_id_tipo_com_u().equals("015001")) {
                if (this.getZc_num_vou_u().trim().length() == 0 || this.getZc_fec_vou_u() == null || this.getZc_cod_age_u().trim().length() == 0) {
                    permiso = 0;
                    this.setOncomplete("javascript:alert('Ingrese todos los campos requeridos.')");
                } else {
                    permiso = 1;
                }
            } else {
                permiso = 1;
            }
        }
        if (permiso == 1) {
            HSPagoDAO dao = (HSPagoDAO) ServiceFinder.findBean("SpringHibernateDaoPago");
            dao.ActualizarComprobantePago(zc_id_u, this.getZc_num_com_u(), this.getZc_fecha_pag_u(), this.getZc_id_tipo_com_u(), this.getZc_tip_com_u(), this.getZc_num_vou_u(), this.getZc_fec_vou_u(), this.getZc_cod_age_u());
            this.setOncomplete("javascript:alert('La Actualizacion se efectuo Correctamente'),Richfaces.hideModalPanel('mpx4'),Richfaces.hideModalPanel('mpx3')");
        }
    }

    public void limpiarCampos() {
        this.comboTipoDocumento_comprobante = getComboTipoDocumento_comprobante();
        this.zc_tip_com_u = "039001";
        this.zc_num_vou_u = "";
        this.zc_fec_vou_u = null;
        this.zc_cod_age_u = "";
    }

    public SelectItem[] getComboCatalogo_procedencia2() throws Exception {
        return comboCatalogo_procedencia2;
    }

    public void setComboCatalogo_procedencia2(SelectItem[] comboCatalogo_procedencia2) {
        this.comboCatalogo_procedencia2 = comboCatalogo_procedencia2;
    }

    public void Actualizar4(ActionEvent event) {
        this.setOncomplete2("javascript:Richfaces.showModalPanel('mpx4',{width:100, top:100})");
    }

    public void Imprimir(ActionEvent event) throws Exception {
        int Iid = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_sIm")).getValue().toString());
        String Ifac = ((UIParameter) event.getComponent().findComponent("fac_sIm")).getValue().toString();
        String Iesp = ((UIParameter) event.getComponent().findComponent("esp_sIm")).getValue().toString();
        String Icod = ((UIParameter) event.getComponent().findComponent("codigo_sIm")).getValue().toString();
        String Inom = ((UIParameter) event.getComponent().findComponent("nombre_sIm")).getValue().toString();
        String Icpa = ((UIParameter) event.getComponent().findComponent("con_pag_sIm")).getValue().toString();
        String Icpd = ((UIParameter) event.getComponent().findComponent("con_pag_det_sIm")).getValue().toString();
    }

    public int getC_id_pago() {
        return c_id_pago;
    }

    public void setC_id_pago(int c_id_pago) {
        this.c_id_pago = c_id_pago;
    }

    public int getC_id_nombre_pago() {
        return c_id_nombre_pago;
    }

    public void setC_id_nombre_pago(int c_id_nombre_pago) {
        this.c_id_nombre_pago = c_id_nombre_pago;
    }

    public int getC_id_con_pag_pago() {
        return c_id_con_pag_pago;
    }

    public void setC_id_con_pag_pago(int c_id_con_pag_pago) {
        this.c_id_con_pag_pago = c_id_con_pag_pago;
    }

    public String getC_sem_pago() {
        return c_sem_pago;
    }

    public void setC_sem_pago(String c_sem_pago) {
        this.c_sem_pago = c_sem_pago;
    }

    public int getC_id_sem_pago() {
        return c_id_sem_pago;
    }

    public void setC_id_sem_pago(int c_id_sem_pago) {
        this.c_id_sem_pago = c_id_sem_pago;
    }

    public String getC_fac_pago() {
        return c_fac_pago;
    }

    public void setC_fac_pago(String c_fac_pago) {
        this.c_fac_pago = c_fac_pago;
    }

    public int getC_id_fac_pago() {
        return c_id_fac_pago;
    }

    public void setC_id_fac_pago(int c_id_fac_pago) {
        this.c_id_fac_pago = c_id_fac_pago;
    }

    public String getC_esp_pago() {
        return c_esp_pago;
    }

    public void setC_esp_pago(String c_esp_pago) {
        this.c_esp_pago = c_esp_pago;
    }

    public int getC_id_esp_pago() {
        return c_id_esp_pago;
    }

    public void setC_id_esp_pago(int c_id_esp_pago) {
        this.c_id_esp_pago = c_id_esp_pago;
    }

    public String getC_codigo_pago() {
        return c_codigo_pago;
    }

    public void setC_codigo_pago(String c_codigo_pago) {
        this.c_codigo_pago = c_codigo_pago;
    }

    public String getC_nombre_pago() {
        return c_nombre_pago;
    }

    public void setC_nombre_pago(String c_nombre_pago) {
        this.c_nombre_pago = c_nombre_pago;
    }

    public String getC_con_pag_pago() {
        return c_con_pag_pago;
    }

    public void setC_con_pag_pago(String c_con_pag_pago) {
        this.c_con_pag_pago = c_con_pag_pago;
    }

    public String getC_con_pag_det_pago() {
        return c_con_pag_det_pago;
    }

    public void setC_con_pag_det_pago(String c_con_pag_det_pago) {
        this.c_con_pag_det_pago = c_con_pag_det_pago;
    }

    public float getC_monto_pago() {
        return c_monto_pago;
    }

    public void setC_monto_pago(float c_monto_pago) {
        this.c_monto_pago = c_monto_pago;
    }

    public Date getC_fecha_pag_pago() {
        return c_fecha_pag_pago;
    }

    public void setC_fecha_pag_pago(Date c_fecha_pag_pago) {
        this.c_fecha_pag_pago = c_fecha_pag_pago;
    }

    public Date getC_fecha_pro_pago() {
        return c_fecha_pro_pago;
    }

    public void setC_fecha_pro_pago(Date c_fecha_pro_pago) {
        this.c_fecha_pro_pago = c_fecha_pro_pago;
    }

    public String getC_estado_pago() {
        return c_estado_pago;
    }

    public void setC_estado_pago(String c_estado_pago) {
        this.c_estado_pago = c_estado_pago;
    }

    public float getC_adeuda_pago() {
        return c_adeuda_pago;
    }

    public void setC_adeuda_pago(float c_adeuda_pago) {
        this.c_adeuda_pago = c_adeuda_pago;
    }

    public float getC_saldo_pago() {
        return c_saldo_pago;
    }

    public void setC_saldo_pago(float c_saldo_pago) {
        this.c_saldo_pago = c_saldo_pago;
    }

    public String getC_dni_ruc_pago() {
        return c_dni_ruc_pago;
    }

    public void setC_dni_ruc_pago(String c_dni_ruc_pago) {
        this.c_dni_ruc_pago = c_dni_ruc_pago;
    }
    String xuno;

    public String getXuno() {
        return xuno;
    }

    public void setXuno(String xuno) {
        this.xuno = xuno;
    }

    public void funxuno() {
        listaCliente = new ArrayList();
        listaCliente.clear();
        HSAlumnoDAO dao = (HSAlumnoDAO) ServiceFinder.findBean("SpringHibernateDaoAlumno");
        HSAlumnoTarifaDAO daoSaldo = (HSAlumnoTarifaDAO) ServiceFinder.findBean("SpringHibernateDaoAlumnoTarifa");
        List lista = dao.seleccionarAlumnoCliente(this.getXuno());
        bPago AluCli;
        for (int i = 0; i < lista.size(); i++) {
            AluCli = new bPago(0);
            AluCli.setCli_id(((AcAlumno) lista.get(i)).getId());
            AluCli.setCli_nombre(((AcAlumno) lista.get(i)).getAluAppaterno() + " " + ((AcAlumno) lista.get(i)).getAluApmaterno() + " " + ((AcAlumno) lista.get(i)).getAluNombres());
            AluCli.setCli_razon("017001");
            AluCli.setCli_dni_ruc(((AcAlumno) lista.get(i)).getAluDni());
            AluCli.setCli_codigo(((AcAlumno) lista.get(i)).getAluCodigo());
            List listaSaldo = daoSaldo.seleccionarUnSaldo(((AcAlumno) lista.get(i)).getId());
            float sum = 0;
            if (!listaSaldo.isEmpty()) {
                for (int j = 0; j < listaSaldo.size(); j++) {
                    sum = sum + ((AdSaldo) listaSaldo.get(j)).getSalMonto();
                }
            }
            AluCli.setCli_saldo(sum);
            listaCliente.add(AluCli);
        }
        this.setListaCliente(listaCliente);
    }

    public SelectItem[] getComboCatalogo_ReciboBloque() throws Exception {
        return comboCatalogo_ReciboBloque;
    }

    public void setComboCatalogo_ReciboBloque(SelectItem[] comboCatalogo_ReciboBloque) {
        this.comboCatalogo_ReciboBloque = comboCatalogo_ReciboBloque;
    }

    public void cambiarFecha() {
        if (this.getRenderFecha().equals("true")) {
            this.setRenderFecha("false");
        } else {
            this.setRenderFecha("true");
        }
    }

    public String getMostrar_accion_pagar() {
        return mostrar_accion_pagar;
    }

    public void setMostrar_accion_pagar(String mostrar_accion_pagar) {
        this.mostrar_accion_pagar = mostrar_accion_pagar;
    }

    public String getMostrar_accion_nuevo() {
        return mostrar_accion_nuevo;
    }

    public void setMostrar_accion_nuevo(String mostrar_accion_nuevo) {
        this.mostrar_accion_nuevo = mostrar_accion_nuevo;
    }

    public String getCabecera1() {
        return cabecera1;
    }

    public void setCabecera1(String cabecera1) {
        this.cabecera1 = cabecera1;
    }

    public String getCabecera2() {
        return cabecera2;
    }

    public void setCabecera2(String cabecera2) {
        this.cabecera2 = cabecera2;
    }

    public String getCabecera3() {
        return cabecera3;
    }

    public void setCabecera3(String cabecera3) {
        this.cabecera3 = cabecera3;
    }

    public String getCabecera4() {
        return cabecera4;
    }

    public void setCabecera4(String cabecera4) {
        this.cabecera4 = cabecera4;
    }

    public String getView_detail() {
        return view_detail;
    }

    public void setView_detail(String view_detail) {
        this.view_detail = view_detail;
    }

    public String getView_lock() {
        return view_lock;
    }

    public void setView_lock(String view_lock) {
        this.view_lock = view_lock;
    }

    public String getVer_fechas() {
        return ver_fechas;
    }

    public void setVer_fechas(String ver_fechas) {
        this.ver_fechas = ver_fechas;
    }

    public String getRenderFecha() {
        return renderFecha;
    }

    public void setRenderFecha(String renderFecha) {
        this.renderFecha = renderFecha;
    }

    public float getE_mora_pago() {
        return e_mora_pago;
    }

    public void setE_mora_pago(float e_mora_pago) {
        this.e_mora_pago = e_mora_pago;
    }

    public boolean isE_mora_seleccion() {
        return e_mora_seleccion;
    }

    public void setE_mora_seleccion(boolean e_mora_seleccion) {
        this.e_mora_seleccion = e_mora_seleccion;
    }

    public String getView_print() {
        return view_print;
    }

    public void setView_print(String view_print) {
        this.view_print = view_print;
    }

    public String getNro_comprobante() {
        return nro_comprobante;
    }

    public void setNro_comprobante(String nro_comprobante) {
        this.nro_comprobante = nro_comprobante;
    }

    public String getMostrar_accion_personalizar() {
        return mostrar_accion_personalizar;
    }

    public void setMostrar_accion_personalizar(String mostrar_accion_personalizar) {
        this.mostrar_accion_personalizar = mostrar_accion_personalizar;
    }

    public String getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(String id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getNro_oculto() {
        return nro_oculto;
    }

    public void setNro_oculto(String nro_oculto) {
        this.nro_oculto = nro_oculto;
    }

    public String getComprobante_oculto() {
        return comprobante_oculto;
    }

    public void setComprobante_oculto(String comprobante_oculto) {
        this.comprobante_oculto = comprobante_oculto;
    }

    public String getNro_comprobante_oculto() {
        return nro_comprobante_oculto;
    }

    public void setNro_comprobante_oculto(String nro_comprobante_oculto) {
        this.nro_comprobante_oculto = nro_comprobante_oculto;
    }

    public Date getComprobante_fecha_oculto() {
        return comprobante_fecha_oculto;
    }

    public void setComprobante_fecha_oculto(Date comprobante_fecha_oculto) {
        this.comprobante_fecha_oculto = comprobante_fecha_oculto;
    }

    public String getComprobante_nombre_oculto() {
        return comprobante_nombre_oculto;
    }

    public void setComprobante_nombre_oculto(String comprobante_nombre_oculto) {
        this.comprobante_nombre_oculto = comprobante_nombre_oculto;
    }

    public String getComprobante_monto_oculto() {
        return comprobante_monto_oculto;
    }

    public void setComprobante_monto_oculto(String comprobante_monto_oculto) {
        this.comprobante_monto_oculto = comprobante_monto_oculto;
    }

    public String getComprobante_concepto_oculto() {
        return comprobante_concepto_oculto;
    }

    public void setComprobante_concepto_oculto(String comprobante_concepto_oculto) {
        this.comprobante_concepto_oculto = comprobante_concepto_oculto;
    }

    public String getComprobante_concepto_det_oculto() {
        return comprobante_concepto_det_oculto;
    }

    public void setComprobante_concepto_det_oculto(String comprobante_concepto_det_oculto) {
        this.comprobante_concepto_det_oculto = comprobante_concepto_det_oculto;
    }

    public void anularPago(ActionEvent event) {
        HSPagoDAO dao = (HSPagoDAO) ServiceFinder.findBean("SpringHibernateDaoPago");
        int id_com = 0;
        if (((UIParameter) event.getComponent().findComponent("o1")) == null) {
            id_com = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_z")).getValue().toString());
        } else {
            id_com = Integer.parseInt(((UIParameter) event.getComponent().findComponent("o1")).getValue().toString());
        }
        dao.anularComprobantePago(id_com);
        cambiarEstados(id_com, dao);
    }

    public void eliminarPago(ActionEvent event) {
        HSPagoDAO dao = (HSPagoDAO) ServiceFinder.findBean("SpringHibernateDaoPago");
        int id_com = 0;
        if (((UIParameter) event.getComponent().findComponent("o1")) == null) {
            id_com = Integer.parseInt(((UIParameter) event.getComponent().findComponent("id_z")).getValue().toString());
        } else {
            id_com = Integer.parseInt(((UIParameter) event.getComponent().findComponent("o1")).getValue().toString());
        }
        cambiarEstados(id_com, dao);
        dao.eliminarComprobantePago(id_com);
    }

    public void cambiarEstados(int id_com, HSPagoDAO dao) {
        HSAlumnoTarifaDAO daoT = (HSAlumnoTarifaDAO) ServiceFinder.findBean("SpringHibernateDaoAlumnoTarifa");
        List lista = dao.seleccionarUnComprobantePago(id_com);
        Set<AdPago> adPagos = ((AdComprobantePago) lista.get(0)).getAdPagos();
        List list = Collections.synchronizedList(new LinkedList(adPagos));
        for (int i = 0; i < list.size(); i++) {
            daoT.cambiarEstado(((AdPago) list.get(i)).getAlutarId(), "0");
        }
    }

    public String getTipo_det() {
        return tipo_det;
    }

    public void setTipo_det(String tipo_det) {
        this.tipo_det = tipo_det;
    }

    public String getTipo_com() {
        return tipo_com;
    }

    public void setTipo_com(String tipo_com) {
        this.tipo_com = tipo_com;
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public String getF2() {
        return f2;
    }

    public void setF2(String f2) {
        this.f2 = f2;
    }

    public void cambiarFormato(ActionEvent event) {
        try {
            f1 = (this.getB_fecha1().getYear() + 1900) + "-" + (this.getB_fecha1().getMonth() + 1) + "-" + (this.getB_fecha1().getDate());
            f2 = (this.getB_fecha2().getYear() + 1900) + "-" + (this.getB_fecha2().getMonth() + 1) + "-" + (this.getB_fecha2().getDate());
        } catch (Exception e) {
            System.out.println("Formato incorrecto de Fecha");
        }
    }

    public String getMostrar_ficha() {
        return mostrar_ficha;
    }

    public void setMostrar_ficha(String mostrar_ficha) {
        this.mostrar_ficha = mostrar_ficha;
    }

    public String getMatricu_id() {
        return matricu_id;
    }

    public void setMatricu_id(String matricu_id) {
        this.matricu_id = matricu_id;
    }

    public float getE_mat_expo() {
        return e_mat_expo;
    }

    public void setE_mat_expo(float e_mat_expo) {
        this.e_mat_expo = e_mat_expo;
    }

    public boolean isE_mat_expo_seleccion() {
        return e_mat_expo_seleccion;
    }

    public void setE_mat_expo_seleccion(boolean e_mat_expo_seleccion) {
        this.e_mat_expo_seleccion = e_mat_expo_seleccion;
    }

    public SelectItem[] getComboTipoDocumento_comprobante() {
        HSCatalogoDAO dao = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        List lista = dao.seleccionarCatalogo("039");
        comboTipoDocumento_comprobante = new SelectItem[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            comboTipoDocumento_comprobante[i] = new SelectItem(((TbCatalogo) lista.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista.get(i)).getCatDescripcionElemento());
        }
        return comboTipoDocumento_comprobante;
    }

    public void setComboTipoDocumento_comprobante(SelectItem[] comboTipoDocumento_comprobante) {
        this.comboTipoDocumento_comprobante = comboTipoDocumento_comprobante;
    }

    public Date getZc_fec_vou_u() {
        return zc_fec_vou_u;
    }

    public void setZc_fec_vou_u(Date zc_fec_vou_u) {
        this.zc_fec_vou_u = zc_fec_vou_u;
    }

    public String getZc_num_vou_u() {
        return zc_num_vou_u;
    }

    public void setZc_num_vou_u(String zc_num_vou_u) {
        this.zc_num_vou_u = zc_num_vou_u;
    }

    public String getZc_cod_age_u() {
        return zc_cod_age_u;
    }

    public void setZc_cod_age_u(String zc_cod_age_u) {
        this.zc_cod_age_u = zc_cod_age_u;
    }

    public String getZc_tip_com_u() {
        return zc_tip_com_u;
    }

    public void setZc_tip_com_u(String zc_tip_com_u) {
        this.zc_tip_com_u = zc_tip_com_u;
    }

    public String getTip_com_det() {
        return tip_com_det;
    }

    public void setTip_com_det(String tip_com_det) {
        this.tip_com_det = tip_com_det;
    }

    public String getNum_vou_det() {
        return num_vou_det;
    }

    public void setNum_vou_det(String num_vou_det) {
        this.num_vou_det = num_vou_det;
    }

    public Date getFec_vou_det() {
        return fec_vou_det;
    }

    public void setFec_vou_det(Date fec_vou_det) {
        this.fec_vou_det = fec_vou_det;
    }

    public String getCod_age_det() {
        return cod_age_det;
    }

    public void setCod_age_det(String cod_age_det) {
        this.cod_age_det = cod_age_det;
    }

    public String getN_tip_com() {
        return n_tip_com;
    }

    public void setN_tip_com(String n_tip_com) {
        this.n_tip_com = n_tip_com;
    }

    public Date getN_fec_vou() {
        return n_fec_vou;
    }

    public void setN_fec_vou(Date n_fec_vou) {
        this.n_fec_vou = n_fec_vou;
    }

    public String getN_num_vou() {
        return n_num_vou;
    }

    public void setN_num_vou(String n_num_vou) {
        this.n_num_vou = n_num_vou;
    }

    public String getN_cod_age() {
        return n_cod_age;
    }

    public void setN_cod_age(String n_cod_age) {
        this.n_cod_age = n_cod_age;
    }

    public String getE_tip_com() {
        return e_tip_com;
    }

    public void setE_tip_com(String e_tip_com) {
        this.e_tip_com = e_tip_com;
    }

    public Date getE_fec_vou() {
        return e_fec_vou;
    }

    public void setE_fec_vou(Date e_fec_vou) {
        this.e_fec_vou = e_fec_vou;
    }

    public String getE_num_vou() {
        return e_num_vou;
    }

    public void setE_num_vou(String e_num_vou) {
        this.e_num_vou = e_num_vou;
    }

    public String getE_cod_age() {
        return e_cod_age;
    }

    public void setE_cod_age(String e_cod_age) {
        this.e_cod_age = e_cod_age;
    }

    /*****************************************************************/
    /*****************************************************************/
    public void matExtemporanea(ActionEvent event) throws Exception {
        id_alumno_2 = Integer.valueOf(((UIParameter) event.getComponent().findComponent("id_alumno")).getValue().toString());

        this.setNro_oculto2("");

        this.comboTipoDocumento_comprobante = getComboTipoDocumento_comprobante();
        this.setTip_com_oculto2("039001");

        this.setFec_oculto2(new Date());

        HSConceptoPagoDAO conpag = (HSConceptoPagoDAO) ServiceFinder.findBean("SpringHibernateDaoConceptoPago");
        this.setMonto_oculto2(((AdConceptoPago) conpag.seleccionarUnaConceptoPago(23).get(0)).getConpagMonto());
    }

    public void pagarMatExtemporanea() throws Exception {
        if (this.getNro_oculto2().length() != 0) {
            float monto_ = this.getMonto_oculto2();

            HSPagoDAO daoPago = (HSPagoDAO) ServiceFinder.findBean("SpringHibernateDaoPago");
            //if(daoPago.seleccionarPagosExternos(id_alumno_2,23).size() == 0)
            //{
            AdPago pag = new AdPago();

            pag.setPagMonto(monto_);
            pag.setPagFecha(new Date());
            pag.setAlutarId(0);
            pag.setPagActivo("1");

            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession session1 = (HttpSession) context.getSession(false);
            bUsuario usu = (bUsuario) session1.getAttribute("usuario");
            pag.setUsuId(usu.getId_usu());

            AdConceptoPago con = new AdConceptoPago();
            con.setId(23);
            pag.setConpag(con);

            AdComprobantePago compag = new AdComprobantePago();
            compag.setCompagTipo("037001");
            compag.setCompagDes("");
            compag.setCompagAlu(0);
            compag.setCompagCliente(id_alumno_2);
            compag.setCompagClienteTipo("014001");
            compag.setCompagUsado("1");
            compag.setCompagProcedencia("015002");
            compag.setCompagCreacion(new Date());
            compag.setCompagActivo("1");
            compag.setCompagMonto(monto_);
            compag.setCompagFecha(this.getFec_oculto2());
            compag.setCompagNro(this.getNro_oculto2());
            compag.setCompagTipDoc(this.getTip_com_oculto2());
            pag.setCompag(compag);

            Set<AdPago> l = new LinkedHashSet<AdPago>();
            l.add(pag);
            compag.setAdPagos(l);

            int id_ = daoPago.insertarPago(compag);
            String alerta = mandarComprobante(id_);

            this.setOncomplete("javascript:alert('El Pago se efectuo Corectamente.');javascript:Richfaces.hideModalPanel('modal2');" + alerta);
            /*}
            else
            {
            this.setOncomplete("javascript:alert('Ya registra pago por matricula extemporanea.')");
            }*/
        }
    }

    public void ImprimirListado(ActionEvent event) throws Exception {
        if (this.getB_fecha1() == null || this.getB_fecha2() == null) {
            this.setAlertaCaja("javascript:alert('Debe ingresar las fechas para imprimir el reporte.')");
        } else {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            this.setPUsuID(((bUsuario) session.getAttribute("usuario")).getId_usu());
            this.setPFecIni(dateFormat.format(this.getB_fecha1()));
            this.setPFecFin(dateFormat.format(this.getB_fecha2()));
            this.setValorReporte("reporteCaja");
            this.setAlertaCaja("javascript:Richfaces.showModalPanel('mpReporte')");
        }
    }

    public void cargarReporte(OutputStream out, Object data) throws IOException, Exception, EOFException {
        if (data.toString().equalsIgnoreCase("reporteCaja")) {
            HashMap parametros = new HashMap();
            parametros.put("fec_ini", this.getPFecIni());
            parametros.put("fec_fin", this.getPFecFin());
            parametros.put("usu_id", this.getPUsuID());

            String jasper = "/WEB-INF/Reportes/cuadre_caja_diario.jasper";

            Print print = new Print();

            ByteArrayOutputStream buffer = print.cargar_reporte(jasper, parametros);
            byte[] bytes = buffer.toByteArray();
            InputStream input = new ByteArrayInputStream(bytes);

            int size = input.available();
            byte[] pdf = new byte[size];
            input.read(pdf);
            input.close();
            out.write(pdf);
        }
    }

    public void ImprimirComprobante(ActionEvent event) throws Exception {
        UIParameter id = (UIParameter) event.getComponent().findComponent("id_z");
        int id_com = Integer.valueOf(id.getValue().toString());
        String script = mandarComprobante(id_com);
        this.setJsImpresion(script);
    }

    public String mandarComprobante(int id_com) throws Exception {
        String complete = "";

        HSPagoDAO dao = (HSPagoDAO) ServiceFinder.findBean("SpringHibernateDaoPago");
        HSAlumnoDAO dao2 = (HSAlumnoDAO) ServiceFinder.findBean("SpringHibernateDaoAlumno");
        HSClienteDAO dao3 = (HSClienteDAO) ServiceFinder.findBean("SpringHibernateDaoCliente");
        HSAlumnoTarifaDAO dao4 = (HSAlumnoTarifaDAO) ServiceFinder.findBean("SpringHibernateDaoAlumnoTarifa");

        List compags = dao.seleccionarUnComprobantePago(id_com);
        AdComprobantePago compag = (AdComprobantePago) compags.get(0);

        DecimalFormat decimalFormat = new DecimalFormat("#,#00.00#;(-#,#00.00#)");

        String fecha = dateFormat.format(compag.getCompagFecha());

        float total = 0;
        String nombre = "";
        String año = fecha.substring(0, fecha.indexOf("-"));
        String fecha2 = fecha.substring((fecha.indexOf("-") + 1), fecha.length());
        String mes = fecha2.substring(0, fecha2.indexOf("-"));
        String dia = fecha2.substring((fecha2.indexOf("-") + 1), fecha2.length());

        Set<AdPago> pagos = new LinkedHashSet<AdPago>();
        pagos = compag.getAdPagos();
        List listPagos = Collections.synchronizedList(new LinkedList(pagos));

        if (compag.getCompagClienteTipo().equals("014001")) {
            nombre = ((AcAlumno) dao2.seleccionarUnAlumno(compag.getCompagCliente()).get(0)).getAluCodigo() + " - "
                    + ((AcAlumno) dao2.seleccionarUnAlumno(compag.getCompagCliente()).get(0)).getAluAppaterno() + " "
                    + ((AcAlumno) dao2.seleccionarUnAlumno(compag.getCompagCliente()).get(0)).getAluApmaterno() + "  "
                    + ((AcAlumno) dao2.seleccionarUnAlumno(compag.getCompagCliente()).get(0)).getAluNombres();
        } else {
            nombre = ((AdClientes) dao3.seleccionarUnCliente(compag.getCompagCliente()).get(0)).getCliRazonSocial();
        }

        String exportHtml = "<html>"
                + "<head>"
                + "<title></title>"
                + "<style type=\"text/css\" >"
                + ".fuente"
                + "{"
                + "font-family:sans-serif;"
                + "font-size:13px;"
                + "font-weight:bold;"
                + "}"
                + ".estilo_matcod"
                + "{"
                + "font-family: sans-serif;"
                + "font-size:13px;"
                + "font-weight:bold;"
                + "}"
                + "</style>"
                + "<script type=\"text/javascript\">"
                + "function hacerPrint()"
                + "{"
                + "print();"
                + "}"
                + "</script>"
                + "</head>"
                + "<body onload=\"hacerPrint();\" leftmargin=\"0\" topmargin=\"3\">"
                + "<div class=\"fuente\" style=\" position:absolute; left:10px; top:67px;\" >"
                + "<PRE>" + dia + "</PRE>"
                + "</div>"
                + "<div class=\"fuente\" style=\" position:absolute; left:80px; top:67px;\" >"
                + "<PRE>" + mes + "</PRE>"
                + "</div>"
                + "<div class=\"fuente\" style=\" position:absolute; left:168px; top:67px;\" >"
                + "<PRE>" + año + "</PRE>"
                + "</div>"
                + "<div class=\"fuente\" style=\" position:absolute; left:75px; top:85px; \">"
                + "<PRE>"
                + "" + nombre + ""
                + "</PRE>"
                + "</div>"
                + "<div class=\"fuente\" style=\" position:absolute; left:320px; top:100px; text-transform: lowercase\">"
                + "<pre>"
                + "" + compag.getCompagNro() + ""
                + "</pre>"
                + "</div>"
                + "<div class=\"fuente\" style=\" position:absolute; left:10px; top:150px;\">";
        for (int i = 0; i < listPagos.size(); i++) {
            AdPago pago = (AdPago) listPagos.get(i);

            if (pago.getConpag().getConpagTipo().equals("007001")) {
                if ((dao4.seleccionarUnAlumnoTarifa(pago.getAlutarId())).getAlutarEstado().equals("2")) {
                    exportHtml = exportHtml
                            + "<PRE>" + (dao4.seleccionarUnAlumnoTarifa(pago.getAlutarId())).getEstpagdet().getEstpagdetNombre() + " (CANCELADO)" + "</pre>";

                } else {
                    exportHtml = exportHtml
                            + "<PRE>" + (dao4.seleccionarUnAlumnoTarifa(pago.getAlutarId())).getEstpagdet().getEstpagdetNombre() + " (A CUENTA)" + "</pre>";

                }
            } else {
                exportHtml = exportHtml
                        + "<PRE>" + pago.getConpag().getConpagDescripcion() + "</pre>";
            }
        }
        exportHtml = exportHtml
                + "</div>"
                + "<div class=\"fuente\" style=\" position:absolute; left:428px; top:154px;\">";
        for (int i = 0; i < listPagos.size(); i++) {
            AdPago pago = (AdPago) listPagos.get(i);
            total = total + pago.getPagMonto();
            exportHtml = exportHtml
                    + "<PRE>" + decimalFormat.format(pago.getPagMonto()) + "</PRE>";
        }
        exportHtml = exportHtml
                + "</div>"
                + "<div class=\"fuente\" style=\"position:absolute; left:428px; top:260px\">"
                + "<PRE>" + decimalFormat.format(total) + "</PRE>"
                + "</div>"
                + "</body>"
                + "</html>";

        complete = "javascript:w=window.open('blank.html','_blank','');javascript:w.document.clear();javascript:w.document.write('" + exportHtml + "');javascript:w.document.close();";

        return complete;
    }

    public String getAlertaCaja() {
        return alertaCaja;
    }

    public void setAlertaCaja(String alertaCaja) {
        this.alertaCaja = alertaCaja;
    }

    public String getValorReporte() {
        return valorReporte;
    }

    public void setValorReporte(String valorReporte) {
        this.valorReporte = valorReporte;
    }

    public String getPFecIni() {
        return pFecIni;
    }

    public void setPFecIni(String pFecIni) {
        this.pFecIni = pFecIni;
    }

    public String getPFecFin() {
        return pFecFin;
    }

    public void setPFecFin(String pFecFin) {
        this.pFecFin = pFecFin;
    }

    public String getJsImpresion() {
        return jsImpresion;
    }

    public void setJsImpresion(String jsImpresion) {
        this.jsImpresion = jsImpresion;
    }

    public Integer getPUsuID() {
        return pUsuID;
    }

    public void setPUsuID(Integer pUsuID) {
        this.pUsuID = pUsuID;
    }

    public Date getFec_oculto2() {
        return fec_oculto2;
    }

    public void setFec_oculto2(Date fec_oculto2) {
        this.fec_oculto2 = fec_oculto2;
    }

    public int getId_alumno_2() {
        return id_alumno_2;
    }

    public void setId_alumno_2(int id_alumno_2) {
        this.id_alumno_2 = id_alumno_2;
    }

    public float getMonto_oculto2() {
        return monto_oculto2;
    }

    public void setMonto_oculto2(float monto_oculto2) {
        this.monto_oculto2 = monto_oculto2;
    }

    public String getNro_oculto2() {
        return nro_oculto2;
    }

    public void setNro_oculto2(String nro_oculto2) {
        this.nro_oculto2 = nro_oculto2;
    }

    public String getTip_com_oculto2() {
        return tip_com_oculto2;
    }

    public void setTip_com_oculto2(String tip_com_oculto2) {
        this.tip_com_oculto2 = tip_com_oculto2;
    }
}
