package net.uch.administrativa.managedBeans;

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
import net.uch.commonService.ServiceFinder;
import net.uch.administrativa.hibernateSpringDao.HSConceptoPagoDAO;
import net.uch.mapping.AdConceptoPago;
import net.uch.mapping.TbCatalogo;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.tablaSistema.managedBeans.bUsuario;

public class bConceptoPago {

    private int b_id;
    private String b_codigo;
    private String b_descripcion;
    private float b_monto;
    private String b_tipo;
    private String b_creacion;
    private String b_activo;
    private List listaConceptoPago;
    private String view = "true";
    private String editable = "false";
    private String b_des_auxiliar;
    private boolean visible = false;
    private String b_id_tipo;
    public static String b_modificacion_aux = "0";
    public SelectItem[] comboCatalogo;
    public SelectItem[] getComboCatalogo_buscar;
    private static int b_id_aux = 0;
    private static String b_codigo_aux;
    private static String b_descripcion_aux;
    private static float b_monto_aux;
    private static String b_tipo_aux;
    private static String b_id_tipo_aux;
    private static String b_creacion_aux;
    private static String b_activo_aux;
    private int b_id_u;
    private String b_codigo_u;
    private String b_descripcion_u;
    private float b_monto_u;
    private String b_tipo_u;
    private String b_id_tipo_u;
    private String b_creacion_u;
    private String b_activo_u;
    private static int b_id_r;
    private static String b_codigo_r;
    private static String b_descripcion_r;
    private static float b_monto_r;
    private static String b_tipo_r;
    private static String b_id_tipo_r;
    private static String b_creacion_r;
    private static String b_activo_r;

    public bConceptoPago() throws Exception {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession(false);
        bUsuario usu = (bUsuario) session1.getAttribute("usuario");
        if (usu != null) {
        } else {
            throw new Exception();
        }
    }

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public String getB_codigo() {
        return b_codigo;
    }

    public void setB_codigo(String b_codigo) {
        this.b_codigo = b_codigo;
    }

    public String getB_descripcion() {
        return b_descripcion;
    }

    public void setB_descripcion(String b_descripcion) {
        this.b_descripcion = b_descripcion;
    }

    public Float getB_monto() {
        return b_monto;
    }

    public void setB_monto(Float b_monto) {
        this.b_monto = b_monto;
    }

    public String getB_tipo() {
        return b_tipo;
    }

    public void setB_tipo(String b_tipo) {
        this.b_tipo = b_tipo;
    }

    public String getB_creacion() {
        return b_creacion;
    }

    public void setB_creacion(String b_creacion) {
        this.b_creacion = b_creacion;
    }

    public String getB_activo() {
        return b_activo;
    }

    public void setB_activo(String b_activo) {
        this.b_activo = b_activo;
    }

    public List getListaConceptoPago() {
        return listaConceptoPago;
    }

    public void setListaConceptoPago(List listaConceptoPago) {
        this.listaConceptoPago = listaConceptoPago;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }

    public String getB_des_auxiliar() {
        return b_des_auxiliar;
    }

    public void setB_des_auxiliar(String b_des_auxiliar) {
        this.b_des_auxiliar = b_des_auxiliar;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getB_id_tipo() {
        return b_id_tipo;
    }

    public void setB_id_tipo(String b_id_tipo) {
        this.b_id_tipo = b_id_tipo;
    }

    public int getB_id_u() {
        b_id_r = b_id_u;
        if (b_id_aux == 0) {
            return b_id_u;
        } else {
            return b_id_aux;
        }
    }

    public void setB_id_u(int b_id_u) {
        this.b_id_u = b_id_u;
    }

    public String getB_codigo_u() {
        b_codigo_r = b_codigo_u;
        if (b_id_aux == 0) {
            return b_codigo_u;
        } else {
            return b_codigo_aux;
        }
    }

    public void setB_codigo_u(String b_codigo_u) {
        this.b_codigo_u = b_codigo_u;
    }

    public String getB_descripcion_u() {
        b_descripcion_r = b_descripcion_u;
        if (b_id_aux == 0) {
            return b_descripcion_u;
        } else {
            return b_descripcion_aux;
        }
    }

    public void setB_descripcion_u(String b_descripcion_u) {
        this.b_descripcion_u = b_descripcion_u;
    }

    public float getB_monto_u() {
        b_monto_r = b_monto_u;
        if (b_id_aux == 0) {
            return b_monto_u;
        } else {
            return b_monto_aux;
        }
    }

    public void setB_monto_u(float b_monto_u) {
        this.b_monto_u = b_monto_u;
    }

    public String getB_tipo_u() {
        b_tipo_r = b_tipo_u;
        if (b_id_aux == 0) {
            return b_tipo_u;
        } else {
            return b_tipo_aux;
        }
    }

    public void setB_tipo_u(String b_tipo_u) {
        this.b_tipo_u = b_tipo_u;
    }

    public String getB_creacion_u() {
        b_creacion_r = b_creacion_u;
        if (b_id_aux == 0) {
            return b_creacion_u;
        } else {
            return b_creacion_aux;
        }
    }

    public void setB_creacion_u(String b_creacion_u) {
        this.b_creacion_u = b_creacion_u;
    }

    public String getB_activo_u() {
        if (b_id_aux == 0) {
            return b_activo_u;
        } else {
            return b_activo_aux;
        }
    }

    public void setB_activo_u(String b_activo_u) {
        this.b_activo_u = b_activo_u;
    }

    public String getB_id_tipo_u() {
        b_id_tipo_r = b_id_tipo_u;
        System.out.println("get id *******" + b_id_tipo_r);
        if (b_id_aux == 0) {
            return b_id_tipo_u;
        } else {
            System.out.println("else get id_tipo " + b_id_tipo_aux);
            return b_id_tipo_aux;
        }
    }

    public void setB_id_tipo_u(String b_id_tipo_u) {
        this.b_id_tipo_u = b_id_tipo_u;
    }

    public SelectItem[] getComboCatalogo() throws Exception {
        HSCatalogoDAO dao = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        List lista = dao.seleccionarCatalogo("007");
        SelectItem[] comboCurso = new SelectItem[lista.size()];
        for (int i = 0; i < comboCurso.length; i++) {
            comboCurso[i] = new SelectItem(((TbCatalogo) lista.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista.get(i)).getCatDescripcionElemento());
        }
        return comboCurso;
    }

    public SelectItem[] getComboCatalogo_buscar() throws Exception {
        HSCatalogoDAO dao = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        List lista = dao.seleccionarCatalogo("007");
        int num = lista.size() + 1;
        SelectItem[] comboCurso = new SelectItem[num];
        comboCurso[0] = new SelectItem(0, "TODOS");
        for (int i = 0; i < comboCurso.length - 1; i++) {
            comboCurso[i + 1] = new SelectItem(((TbCatalogo) lista.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lista.get(i)).getCatCodigoElemento(), ((TbCatalogo) lista.get(i)).getCatDescripcionElemento());
        }
        return comboCurso;
    }

    public void setComboCatalogo_buscar(SelectItem[] comboCatalogo) {
        this.comboCatalogo = comboCatalogo;
    }

    public void setComboCatalogo(SelectItem[] comboCatalogo) {
        this.comboCatalogo = comboCatalogo;
    }

    ///metodo para seleccionars
    public void Seleccionar() throws Exception {
        listaConceptoPago = new ArrayList();
        HSConceptoPagoDAO dao = (HSConceptoPagoDAO) ServiceFinder.findBean("SpringHibernateDaoConceptoPago");
        List lista = dao.seleccionarConceptoPago(this.getB_descripcion(), this.getB_codigo(), this.getB_tipo());
        bConceptoPago bE;
        Object[] O;
        for (int i = 0; i < lista.size(); i++) {
            O = (Object[]) lista.get(i);
            bE = new bConceptoPago();
            bE.setB_id(Integer.parseInt(O[0].toString()));
            bE.setB_codigo(O[1].toString());
            bE.setB_descripcion(O[2].toString());
            bE.setB_monto(Float.parseFloat(O[3].toString()));
            bE.setB_tipo(O[7].toString());
            if (O[5] != null) {
                bE.setB_creacion(O[5].toString());
            }
            bE.setB_activo(O[6].toString());
            bE.setB_id_tipo(O[4].toString());
            listaConceptoPago.add(bE);
        }
        this.setListaConceptoPago(listaConceptoPago);
    }

    //---para eliminar fila
    public void EliminarFila(ActionEvent event) throws Exception {
        System.out.println("entro a elimnar fila");
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        UIParameter delete = (UIParameter) event.getComponent().findComponent("delete");
        System.out.println("llama a eliminar");
        Eliminar(delete.getValue().toString());


    }
    //---para eliminar parte 2

    public void Eliminar(String id) throws Exception {
        System.out.println("entro a elimnar");
        HSConceptoPagoDAO dao = (HSConceptoPagoDAO) ServiceFinder.findBean("SpringHibernateDaoConceptoPago");
        dao.eliminarConceptoPago("" + id);
    }

    ///---
    public void Nuevo() {
        System.out.println("Entro a Nuevo");
        this.setB_id_u(0);
        b_id_aux = 0;
        this.setB_codigo_u("");
        b_codigo_aux = "";
        this.setB_descripcion_u("");
        b_descripcion_aux = "";
        b_monto_aux = 0;
        this.setB_monto_u(0);
        this.setB_tipo_u("0");
        this.setB_id_tipo_u("0");
        b_tipo_aux = "0";
        b_id_tipo_aux = "007001";
        this.setB_creacion_u(null);
        b_creacion_aux = null;
    }

    public void EditarFila(ActionEvent event) {
        //Nuevo();
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        UIParameter id = (UIParameter) event.getComponent().findComponent("p_id");
        UIParameter codigo = (UIParameter) event.getComponent().findComponent("p_codigo");
        UIParameter descripcion = (UIParameter) event.getComponent().findComponent("p_descripcion");
        UIParameter monto = (UIParameter) event.getComponent().findComponent("p_monto");
        UIParameter tipo = (UIParameter) event.getComponent().findComponent("p_tipo");
        UIParameter creacion = (UIParameter) event.getComponent().findComponent("p_creacion");
/*
        b_id_aux = Integer.parseInt(id.getValue().toString());
        b_codigo_aux = codigo.getValue().toString();
        b_descripcion_aux = descripcion.getValue().toString();
        b_monto_aux = Float.parseFloat(monto.getValue().toString());
        b_id_tipo_aux = tipo.getValue().toString();
        b_creacion_aux = creacion.getValue().toString();
*/
        this.setB_id_u(Integer.parseInt(id.getValue().toString()));
        this.setB_codigo_u(codigo.getValue().toString());
        this.setB_descripcion_u(descripcion.getValue().toString());
        this.setB_monto_u(Float.parseFloat(monto.getValue().toString()));
        this.setB_id_tipo_u(tipo.getValue().toString());
        this.setB_creacion_u(creacion.getValue().toString());

   /*
        System.out.println("id aux " + b_id_aux);
        System.out.println("id codigo " + b_codigo_aux);
        System.out.println("id descripcion " + b_descripcion_aux);
        System.out.println("id monto " + b_monto_aux);
        System.out.println("id tipo " + b_tipo_aux);
        System.out.println("id tipo " + b_id_tipo_aux);
        System.out.println("id tipo-------------1111111 " + this.getB_id_tipo());
        System.out.println("id creacion " + b_creacion_aux);
    */

    }

    public void Insertar(ActionEvent event) {
        HSConceptoPagoDAO dao = (HSConceptoPagoDAO) ServiceFinder.findBean("SpringHibernateDaoConceptoPago");
        if (b_id_aux == 0) {
            System.out.println("nieves nieves nieves ");
            AdConceptoPago ConPago = new AdConceptoPago();
            ConPago.setId(this.getB_id());
            ConPago.setConpagCodigo(b_codigo_u);
            ConPago.setConpagDescripcion(b_descripcion_u);
            ConPago.setConpagMonto(b_monto_u);
            ConPago.setConpagTipo(b_id_tipo_u);
            Date fecha = new Date();
            ConPago.setConpagCreacion(fecha);
            ConPago.setConpagActualizacion(fecha);
            ConPago.setConpagActivo("1");
            dao.insertarConceptoPago(ConPago);
        } else {
            AdConceptoPago ConPago = new AdConceptoPago();
            ConPago.setId(b_id_u);
            ConPago.setConpagCodigo(b_codigo_u);
            ConPago.setConpagDescripcion(b_descripcion_u);
            ConPago.setConpagMonto(b_monto_u);
            ConPago.setConpagTipo(b_id_tipo_u);
            Date fecha = new Date();

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            try {
                java.util.Date fecha2 = sdf.parse(b_creacion_u.toString());
                //  System.out.println("FECHA2B "+b_creacion_r.toString());
                // System.out.println("FECHA2B "+fecha2);
                ConPago.setConpagCreacion(fecha2);
            } catch (Exception e) {
                System.out.println("Formato incorrecto de Fechaxxxx");
            }
            ConPago.setConpagActualizacion(fecha);
            ConPago.setConpagActivo("1");
            dao.actualizarConceptoPago(ConPago);
        }
    }
}
