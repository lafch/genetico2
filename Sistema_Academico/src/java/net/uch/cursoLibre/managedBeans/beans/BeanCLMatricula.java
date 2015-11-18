/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans.beans;

import java.util.Date;

/**
 *
 * @author Jose Tejada
 */
public class BeanCLMatricula {

    private String i_curso;
    private String i_codigo_curso;
    private String i_seccion;
    private Date i_inicio;
    private Date i_fin;
    private int i_nro_mat;
    private int pre_i_nro_mat;
    private String i_nro_mat_style;
    private String i_tt_message;
    private boolean i_ver_message;
    private float i_monto_base;
    private float i_monto_pagar;
    private int i_sec_id;
    private int i_tal_id;
    private boolean i_ver;
    private float i_monto_editar;
    private boolean i_editar;
    private boolean i_agregar=false;
    private String i_modulo;
    private int i_sec_vac_max;
    private int cl_estpagdet_id;
    private int cl_conpag_id;
    private Date cl_fecha_pago;
    private Date cl_fecha_prorroga;
    private String cl_alutar_tipo;
    private String cl_observacion_monto;
    private int usu_id;
    
    private String i_seccion_cod;

    public int getUsu_id() {
        return usu_id;
    }

    public void setUsu_id(int usu_id) {
        this.usu_id = usu_id;
    }

    

    public String getCl_observacion_monto() {
        return cl_observacion_monto;
    }

    public void setCl_observacion_monto(String cl_observacion_monto) {
        this.cl_observacion_monto = cl_observacion_monto;
    }
    

    public String getCl_alutar_tipo() {
        return cl_alutar_tipo;
    }

    public void setCl_alutar_tipo(String cl_alutar_tipo) {
        this.cl_alutar_tipo = cl_alutar_tipo;
    }

    public Date getCl_fecha_prorroga() {
        return cl_fecha_prorroga;
    }

    public void setCl_fecha_prorroga(Date cl_fecha_prorroga) {
        this.cl_fecha_prorroga = cl_fecha_prorroga;
    }

    public Date getCl_fecha_pago() {
        return cl_fecha_pago;
    }

    public void setCl_fecha_pago(Date cl_fecha_pago) {
        this.cl_fecha_pago = cl_fecha_pago;
    }

    public int getCl_conpag_id() {
        return cl_conpag_id;
    }

    public void setCl_conpag_id(int cl_conpag_id) {
        this.cl_conpag_id = cl_conpag_id;
    }

    public int getCl_estpagdet_id() {
        return cl_estpagdet_id;
    }

    public void setCl_estpagdet_id(int cl_estpagdet_id) {
        this.cl_estpagdet_id = cl_estpagdet_id;
    }

    public boolean isI_agregar() {
        return i_agregar;
    }

    public void setI_agregar(boolean i_agregar) {
        this.i_agregar = i_agregar;
    }

    public String getI_codigo_curso() {
        return i_codigo_curso;
    }

    public void setI_codigo_curso(String i_codigo_curso) {
        this.i_codigo_curso = i_codigo_curso;
    }

    public String getI_curso() {
        return i_curso;
    }

    public void setI_curso(String i_curso) {
        this.i_curso = i_curso;
    }

    public boolean isI_editar() {
        return i_editar;
    }

    public void setI_editar(boolean i_editar) {
        this.i_editar = i_editar;
    }

    public Date getI_inicio() {
        return i_inicio;
    }

    public void setI_inicio(Date i_inicio) {
        this.i_inicio = i_inicio;
    }

    public String getI_modulo() {
        return i_modulo;
    }

    public void setI_modulo(String i_modulo) {
        this.i_modulo = i_modulo;
    }

    public float getI_monto_base() {
        return i_monto_base;
    }

    public void setI_monto_base(float i_monto_base) {
        this.i_monto_base = i_monto_base;
    }

    public float getI_monto_editar() {
        return i_monto_editar;
    }

    public void setI_monto_editar(float i_monto_editar) {
        this.i_monto_editar = i_monto_editar;
    }

    public float getI_monto_pagar() {
        return i_monto_pagar;
    }

    public void setI_monto_pagar(float i_monto_pagar) {
        this.i_monto_pagar = i_monto_pagar;
    }

    public int getI_nro_mat() {
        return i_nro_mat;
    }

    public void setI_nro_mat(int i_nro_mat) {
        this.i_nro_mat = i_nro_mat;
    }

    public String getI_nro_mat_style() {
        return i_nro_mat_style;
    }

    public void setI_nro_mat_style(String i_nro_mat_style) {
        this.i_nro_mat_style = i_nro_mat_style;
    }

    public int getI_tal_id() {
        return i_tal_id;
    }

    public void setI_tal_id(int i_tal_id) {
        this.i_tal_id = i_tal_id;
    }

    
    public int getI_sec_id() {
        return i_sec_id;
    }

    public void setI_sec_id(int i_sec_id) {
        this.i_sec_id = i_sec_id;
    }

    public String getI_seccion() {
        return i_seccion;
    }

    public void setI_seccion(String i_seccion) {
        this.i_seccion = i_seccion;
    }

    public int getI_sec_vac_max() {
        return i_sec_vac_max;
    }

    public void setI_sec_vac_max(int i_sec_vac_max) {
        this.i_sec_vac_max = i_sec_vac_max;
    }

    public String getI_tt_message() {
        return i_tt_message;
    }

    public void setI_tt_message(String i_tt_message) {
        this.i_tt_message = i_tt_message;
    }

    public boolean isI_ver() {
        return i_ver;
    }

    public void setI_ver(boolean i_ver) {
        this.i_ver = i_ver;
    }

    public boolean isI_ver_message() {
        return i_ver_message;
    }

    public void setI_ver_message(boolean i_ver_message) {
        this.i_ver_message = i_ver_message;
    }

    public BeanCLMatricula(int n) {
    }

    public Date getI_fin() {
        return i_fin;
    }

    public void setI_fin(Date i_fin) {
        this.i_fin = i_fin;
    }

    public int getPre_i_nro_mat() {
        return pre_i_nro_mat;
    }

    public void setPre_i_nro_mat(int pre_i_nro_mat) {
        this.pre_i_nro_mat = pre_i_nro_mat;
    }

    public String getI_seccion_cod() {
        return i_seccion_cod;
    }

    public void setI_seccion_cod( String i_seccion_cod ) {
        this.i_seccion_cod = i_seccion_cod;
    }
    
}
