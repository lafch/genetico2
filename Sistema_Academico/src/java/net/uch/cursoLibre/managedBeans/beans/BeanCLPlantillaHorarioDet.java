/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.cursoLibre.managedBeans.beans;

import javax.faces.model.SelectItem;
import net.uch.cursoLibre.utilsAcademico.Metodos;
import net.uch.mapping.ClPlantillaHorarioDet;

/**
 *
 * @author richard
 */
public class BeanCLPlantillaHorarioDet extends ClPlantillaHorarioDet {

    private SelectItem[] cboMeridiano_ini;
    private SelectItem[] cboMeridiano_fin;
    private String w_mer_ini;
    private String w_mer_fin;

    private int w_fec_ini_hor;
    private int w_fec_fin_hor;
    private int w_fec_ini_min;
    private int w_fec_fin_min;
    private SelectItem[] cboDia;
    private SelectItem[] cboTipoClase;
    private String w_dia="000000";
    private String w_tipo_clase="000000";
    
    private String w_des_dia;
    private String w_des_tipoclase;

    public SelectItem[] getCboDia() {
        Metodos metodo=new Metodos();
        this.setCboDia(metodo.listarCatalogoGrupo("016","[Seleccione]"));
        return cboDia;
    }

    public void setCboDia(SelectItem[] cboDia) {
        this.cboDia = cboDia;
    }

    public SelectItem[] getCboMeridiano_fin() {
        Metodos metodo=new Metodos();
        this.setCboMeridiano_fin(metodo.listarCatalogoGrupo("008","[Seleccione]"));
        return cboMeridiano_fin;
    }

    public void setCboMeridiano_fin(SelectItem[] cboMeridiano_fin) {
        this.cboMeridiano_fin = cboMeridiano_fin;
    }

    public SelectItem[] getCboMeridiano_ini() {
        Metodos metodo=new Metodos();
         this.setCboMeridiano_ini(metodo.listarCatalogoGrupo("008","[Seleccione]"));
        return cboMeridiano_ini;
    }

    public void setCboMeridiano_ini(SelectItem[] cboMeridiano_ini) {
        this.cboMeridiano_ini = cboMeridiano_ini;
    }

    public SelectItem[] getCboTipoClase() {
        Metodos metodo=new Metodos();
        this.setCboTipoClase(metodo.listarCatalogoGrupo("018","[Seleccione]"));
        return cboTipoClase;
    }

    public void setCboTipoClase(SelectItem[] cboTipoClase) {
        this.cboTipoClase = cboTipoClase;
    }

    public String getW_des_dia() {
        return w_des_dia;
    }

    public void setW_des_dia(String w_des_dia) {
        this.w_des_dia = w_des_dia;
    }

    public String getW_des_tipoclase() {
        return w_des_tipoclase;
    }

    public void setW_des_tipoclase(String w_des_tipoclase) {
        this.w_des_tipoclase = w_des_tipoclase;
    }

    public String getW_dia() {
        return w_dia;
    }

    public void setW_dia(String w_dia) {
        this.w_dia = w_dia;
    }

    public int getW_fec_fin_hor() {
        return w_fec_fin_hor;
    }

    public void setW_fec_fin_hor(int w_fec_fin_hor) {
        this.w_fec_fin_hor = w_fec_fin_hor;
    }

    public int getW_fec_fin_min() {
        return w_fec_fin_min;
    }

    public void setW_fec_fin_min(int w_fec_fin_min) {
        this.w_fec_fin_min = w_fec_fin_min;
    }

    public int getW_fec_ini_hor() {
        return w_fec_ini_hor;
    }

    public void setW_fec_ini_hor(int w_fec_ini_hor) {
        this.w_fec_ini_hor = w_fec_ini_hor;
    }

    public int getW_fec_ini_min() {
        return w_fec_ini_min;
    }

    public void setW_fec_ini_min(int w_fec_ini_min) {
        this.w_fec_ini_min = w_fec_ini_min;
    }

    public String getW_mer_fin() {
        return w_mer_fin;
    }

    public void setW_mer_fin(String w_mer_fin) {
        this.w_mer_fin = w_mer_fin;
    }

    public String getW_mer_ini() {
        return w_mer_ini;
    }

    public void setW_mer_ini(String w_mer_ini) {
        this.w_mer_ini = w_mer_ini;
    }

    public String getW_tipo_clase() {
        return w_tipo_clase;
    }

    public void setW_tipo_clase(String w_tipo_clase) {
        this.w_tipo_clase = w_tipo_clase;
    }




}
